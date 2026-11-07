package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Свечение XP-бара: при получении опыта ванильная полоска опыта
 * мягко подсвечивается пульсирующим зелёным ореолом.
 */
public class XpBarGlowModule extends Module {
    private static final long GLOW_MS = 800L;

    private final SliderSetting strength;
    private float lastProgress = -1.0f;
    private int lastLevel = Integer.MIN_VALUE;
    private long glowStart;

    public XpBarGlowModule() {
        super("XP Bar Glow NEW", "Зелёное пульсирующее свечение XP-бара при получении опыта.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Яркость свечения.").setValue(0.8f).range(0.2f, 1.0f));
        this.glowStart = 0L;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled() || this.mc.player == null) {
            return;
        }
        float progress = this.mc.player.experienceProgress;
        int level = this.mc.player.experienceLevel;
        if (this.lastLevel != Integer.MIN_VALUE) {
            boolean levelUp = level > this.lastLevel;
            boolean progressGained = level == this.lastLevel && progress > this.lastProgress + 1.0E-4f;
            if (levelUp || progressGained) {
                this.glowStart = System.currentTimeMillis();
            }
        }
        this.lastProgress = progress;
        this.lastLevel = level;
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.glowStart == 0L) {
            return;
        }
        long age = System.currentTimeMillis() - this.glowStart;
        if (age > GLOW_MS) {
            return;
        }
        float t = (float) age / (float) GLOW_MS;
        // пульс: затухание с синусоидальным дыханием
        float fade = 1.0f - t;
        float pulse = 0.6f + 0.4f * (float) Math.sin(age * Math.PI * 2.0 / 200.0);
        int alpha = (int) (150 * fade * pulse * this.strength.getValue());
        if (alpha <= 0) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        // ванильный XP-бар: 182 юнита шириной, y = h - 29, высота 5
        Render2D.rect(w * 0.5f - 91.0f, h - 29.0f, 182.0f, 5.0f, 0.0f, (alpha << 24) | 0x5ADC78);
        Render2D.flush();
    }
}
