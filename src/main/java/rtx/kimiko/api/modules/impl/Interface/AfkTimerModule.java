package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;
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

/**
 * AFK Timer NEW: считает время без движения/поворота камеры; при достижении
 * лимита показывает мигающую жёлтую плашку.
 */
public class AfkTimerModule extends Module {
    private final SliderSetting warnMinutes;
    private double lastX;
    private double lastY;
    private double lastZ;
    private float lastYaw;
    private float lastPitch;
    private boolean positionInitialized;
    private long lastActivityMs;
    private boolean warned;

    public AfkTimerModule() {
        super("AFK Timer NEW", "Считает, сколько ты стоишь без движения, и предупреждает плашкой.", Category.DISPLAY);
        this.warnMinutes = (SliderSetting) this.register((Setting) new SliderSetting("Предупредить через (мин)", "Порог предупреждения в минутах.").setValue(5.0f).range(1.0f, 30.0f));
        this.positionInitialized = false;
        this.lastActivityMs = System.currentTimeMillis();
        this.warned = false;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled() || this.mc.player == null) {
            return;
        }
        double x = this.mc.player.getX();
        double y = this.mc.player.getY();
        double z = this.mc.player.getZ();
        float yaw = MathHelper.wrapDegrees(this.mc.player.getYaw());
        float pitch = this.mc.player.getPitch();

        if (!this.positionInitialized) {
            this.lastX = x;
            this.lastY = y;
            this.lastZ = z;
            this.lastYaw = yaw;
            this.lastPitch = pitch;
            this.positionInitialized = true;
            this.lastActivityMs = System.currentTimeMillis();
            this.warned = false;
            return;
        }

        double move = (x - this.lastX) * (x - this.lastX)
                + (y - this.lastY) * (y - this.lastY)
                + (z - this.lastZ) * (z - this.lastZ);
        float turn = Math.abs(yaw - this.lastYaw) + Math.abs(pitch - this.lastPitch);
        if (move > 1.0E-4 || turn > 0.05f) {
            this.lastActivityMs = System.currentTimeMillis();
            this.warned = false;
        }

        this.lastX = x;
        this.lastY = y;
        this.lastZ = z;
        this.lastYaw = yaw;
        this.lastPitch = pitch;
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null) {
            return;
        }
        long afkMs = System.currentTimeMillis() - this.lastActivityMs;
        long afkSeconds = afkMs / 1000L;
        long warnAfterMs = (long) (this.warnMinutes.getValue() * 60.0f * 1000.0f);
        if (afkMs < warnAfterMs) {
            return;
        }

        boolean blinkOn = (System.currentTimeMillis() / 500L) % 2L == 0L;
        int a = blinkOn ? 235 : 140;
        long minutes = afkSeconds / 60L;

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;

        String text = "Ты в АФК " + minutes + " мин";
        float cardW = Fonts.SEMIBOLD.width(text, 9.5f) + 24.0f;
        float cardH = 26.0f;
        float x = (w - cardW) * 0.5f;
        float y = 16.0f;

        Render2D.rect(x, y, cardW, cardH, 8.0f, MenuColors.rgba(26, 20, 8, a));
        Render2D.outline(x, y, cardW, cardH, 8.0f, 1.0f, MenuColors.rgba(255, 205, 90, (int) (a * 0.45f)));
        Fonts.SEMIBOLD.draw(text, x + (cardW - Fonts.SEMIBOLD.width(text, 9.5f)) * 0.5f, y + (cardH - 10.0f) * 0.5f, 9.5f, MenuColors.rgba(255, 215, 120, a));
        Render2D.flush();
    }
}
