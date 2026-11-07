package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Слепая зона: если позади игрока стоит живая сущность, у края экрана
 * с соответствующей стороны мягко пульсирует красный сигнал.
 */
public class BlindSpotModule extends Module {
    private static final float BEHIND_COS = (float) Math.cos(Math.toRadians(140.0));

    private final SliderSetting radius;
    private float leftSignal;
    private float rightSignal;

    public BlindSpotModule() {
        super("Blind Spot NEW", "Красный сигнал у края экрана, когда позади кто-то стоит.", Category.VISUALS);
        this.radius = (SliderSetting) this.register((Setting) new SliderSetting("Радиус", "Дистанция обнаружения за спиной.").setValue(6.0f).range(3.0f, 10.0f));
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null) {
            this.leftSignal = 0.0f;
            this.rightSignal = 0.0f;
            return;
        }
        this.leftSignal = 0.0f;
        this.rightSignal = 0.0f;
        float range = this.radius.getValue();
        Vec3d look = this.mc.player.getRotationVec(1.0f);
        float yawRad = this.mc.player.getYaw() * ((float) Math.PI / 180.0f);
        // правый вектор игрока в горизонтальной плоскости
        double rightX = -MathHelper.cos(yawRad);
        double rightZ = -MathHelper.sin(yawRad);
        for (Entity e : world.getEntities()) {
            if (e == this.mc.player || !e.isAlive() || e.isSpectator() || !(e instanceof LivingEntity)) {
                continue;
            }
            double dx = e.getX() - this.mc.player.getX();
            double dz = e.getZ() - this.mc.player.getZ();
            double dist = Math.sqrt(dx * dx + dz * dz);
            if (dist > range || dist < 0.3) {
                continue;
            }
            double dot = (look.x * dx + look.z * dz) / dist;
            if (dot > BEHIND_COS) {
                continue;
            }
            double lateral = (dx * rightX + dz * rightZ) / dist;
            float strength = 1.0f - (float) (dist / range);
            if (lateral >= 0.0) {
                this.rightSignal = Math.max(this.rightSignal, strength);
            } else {
                this.leftSignal = Math.max(this.leftSignal, strength);
            }
        }
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || (this.leftSignal <= 0.0f && this.rightSignal <= 0.0f)) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float pulse = 0.6f + 0.4f * (float) Math.sin(System.currentTimeMillis() * 0.006);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        if (this.rightSignal > 0.0f) {
            this.drawEdge(w, h, true, this.rightSignal * pulse);
        }
        if (this.leftSignal > 0.0f) {
            this.drawEdge(w, h, false, this.leftSignal * pulse);
        }
        Render2D.flush();
    }

    /** Мягкая вертикальная полусигнальная полоса у края экрана. */
    private void drawEdge(float w, float h, boolean right, float strength) {
        float alpha = MathHelper.clamp(strength, 0.0f, 1.0f);
        float x = right ? w : 0.0f;
        float cy = h * 0.5f;
        // три слоя: широкий бледный, средний, узкий яркий — мягкий полукруг
        Render2D.rect(right ? x - 5.0f : x, cy - 55.0f, 5.0f, 110.0f, 2.5f, this.red(alpha * 0.22f));
        Render2D.rect(right ? x - 3.4f : x, cy - 40.0f, 3.4f, 80.0f, 1.7f, this.red(alpha * 0.45f));
        Render2D.rect(right ? x - 1.8f : x, cy - 26.0f, 1.8f, 52.0f, 0.9f, this.red(alpha * 0.8f));
    }

    private static int red(float alpha) {
        int a = (int) (MathHelper.clamp(alpha, 0.0f, 1.0f) * 255.0f);
        return (a << 24) | 0xFF4650;
    }
}
