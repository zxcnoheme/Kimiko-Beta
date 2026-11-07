package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Incoming Projectiles NEW: сканирует летящие к игроку стрелы, трезубцы,
 * снежки и жемчуг (вектор скорости смотрит на игрока) и рисует красный
 * сектор-дугу на краю экрана в сторону угрозы: центр — прямо впереди.
 */
public class ProjectileWarnModule extends Module {
    private static final int MAX_ENTITIES = 150;
    private static final double ALIGN_THRESHOLD = 0.92;
    private static final int MAX_MARKS = 5;

    private final SliderSetting range;
    private final List<Threat> threats = new ArrayList<>();

    public ProjectileWarnModule() {
        super("Incoming Projectiles NEW", "Красные дуги на краю экрана в сторону летящих в игрока снарядов.", Category.DISPLAY);
        this.range = (SliderSetting) this.register((Setting) new SliderSetting("Дистанция", "Радиус обнаружения снарядов (блоки).").setValue(16.0f).range(8.0f, 24.0f));
    }

    @Override
    protected void onDisable() {
        this.threats.clear();
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        this.threats.clear();
        if (this.mc.world == null || this.mc.player == null) {
            return;
        }
        Vec3d center = this.mc.player.getLerpedPos(1.0f).add(0.0, this.mc.player.getHeight() * 0.5, 0.0);
        float maxDist = this.range.getValue();
        int scanned = 0;
        for (Entity entity : this.mc.world.getEntities()) {
            if (scanned >= MAX_ENTITIES) {
                break;
            }
            scanned++;
            if (!(entity instanceof ArrowEntity)
                    && !(entity instanceof TridentEntity)
                    && !(entity instanceof SnowballEntity)
                    && !(entity instanceof EnderPearlEntity)) {
                continue;
            }
            Vec3d velocity = entity.getVelocity();
            if (velocity.lengthSquared() < 1.0E-4) {
                continue;
            }
            Vec3d toPlayer = center.subtract(entity.getLerpedPos(1.0f));
            double dist = toPlayer.length();
            if (dist < 0.5 || dist > maxDist) {
                continue;
            }
            double alignment = velocity.normalize().dotProduct(toPlayer.normalize());
            if (alignment <= ALIGN_THRESHOLD) {
                continue;
            }
            // горизонтальный угол между взглядом и направлением на снаряд
            double dx = entity.getX() - this.mc.player.getX();
            double dz = entity.getZ() - this.mc.player.getZ();
            float yawTo = (float) Math.toDegrees(Math.atan2(-dx, dz));
            float diff = MathHelper.wrapDegrees(yawTo - this.mc.player.getYaw());
            this.threats.add(new Threat(diff, (float) dist));
        }
        // ближайшие — первыми
        this.threats.sort((a, b) -> Float.compare(a.dist, b.dist));
        while (this.threats.size() > MAX_MARKS) {
            this.threats.remove(this.threats.size() - 1);
        }
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.threats.isEmpty() || this.mc.player == null) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float maxDist = this.range.getValue();

        for (Threat threat : this.threats) {
            // чем ближе снаряд, тем заметнее метка
            float closeness = 1.0f - MathHelper.clamp(threat.dist / maxDist, 0.0f, 1.0f);
            float intensity = 0.45f + 0.55f * closeness;

            float frac = MathHelper.clamp(threat.angleDiff / 70.0f, -1.0f, 1.0f);
            float cx = w * 0.5f + frac * (w * 0.42f);
            float cy = 4.0f;

            // тусклый ореол + яркий сектор, «смотрящий» вниз с верхнего края
            Render2D.arcRect(cx, cy, 26.0f, 1.4f, 90.0f - 34.0f, 68.0f,
                    MenuColors.rgba(255, 70, 70, (int) (70.0f * intensity)));
            Render2D.arcRect(cx, cy, 26.0f, 2.6f, 90.0f - 20.0f, 40.0f,
                    MenuColors.rgba(255, 70, 70, (int) (235.0f * intensity)));

            String label = (int) Math.ceil(threat.dist) + " бл.";
            float labelW = Fonts.SEMIBOLD.width(label, 6.5f);
            Fonts.SEMIBOLD.draw(label, cx - labelW * 0.5f, cy + 15.0f, 6.5f,
                    MenuColors.rgba(255, 130, 130, (int) (215.0f * intensity)));
        }
        Render2D.flush();
    }

    private static final class Threat {
        private final float angleDiff;
        private final float dist;

        private Threat(float angleDiff, float dist) {
            this.angleDiff = angleDiff;
            this.dist = dist;
        }
    }
}
