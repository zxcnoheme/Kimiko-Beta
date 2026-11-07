package rtx.kimiko.api.ui.menu;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import rtx.kimiko.utils.animations.Easings;
import rtx.kimiko.utils.animations.SmoothAnimation;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

public class WhiteLoading {
    private static WhiteLoading instance;

    private static final String[] LOADING_TEXTS = {
            "Запуск Kimiko",
            "Грею шейдеры",
            "Раскладываю интерфейс",
            "Почти готово"
    };

    private static final long TEXT_DISPLAY_DURATION = 2200L;
    private static final long TEXT_TRANSITION_DURATION = 400L;

    private final SmoothAnimation intro = new SmoothAnimation();

    private float animatedProgress;
    private float targetProgress;
    private float pulseTime;
    private long lastRenderTime;
    private boolean initialized;

    private int currentTextIndex;
    private float currentTextOffsetY = 1.0f;
    private float currentTextAlpha = 1.0f;
    private float newTextOffsetY = -12.0f;
    private float newTextAlpha;
    private long lastTextChangeTime;
    private boolean isTransitioning;
    private long transitionStartTime;

    private final Particle[] particles = new Particle[40];
    private float progressGlow;

    public WhiteLoading() {
        instance = this;
        this.intro.setValue(0.0);
        this.intro.run(1.0, 0.6, Easings.QUAD_OUT, false);
        this.lastTextChangeTime = Util.getMeasuringTimeMs();
        this.initParticles();
    }

    public static WhiteLoading get() {
        if (instance == null) {
            instance = new WhiteLoading();
        }
        return instance;
    }

    public void setProgress(float progress) {
        float p = progress;
        if (p < 0.0f) {
            p = 0.0f;
        }
        if (p > 1.0f) {
            p = 1.0f;
        }
        this.targetProgress = p;
    }

    private void initParticles() {
        for (int i = 0; i < this.particles.length; i++) {
            this.particles[i] = new Particle();
        }
    }

    private static class Particle {
        float x;
        float y;
        float speed;
        float size;
        float alpha;
        float drift;

        Particle() {
            this.reset(true);
        }

        void reset(boolean randomY) {
            this.x = (float) (Math.random() * 2000.0);
            this.y = randomY ? (float) (Math.random() * 1200.0) : 1200.0f + (float) (Math.random() * 100.0);
            this.speed = 8.0f + (float) (Math.random() * 25.0);
            this.size = 1.0f + (float) (Math.random() * 2.5f);
            this.alpha = 0.08f + (float) (Math.random() * 0.2);
            this.drift = -15.0f + (float) (Math.random() * 30.0);
        }

        void update(float dt) {
            this.y -= this.speed * dt;
            this.x += this.drift * dt;
            if (this.y < -20.0f) {
                this.reset(false);
            }
        }
    }

    public void render(DrawContext context) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null || mc.getWindow() == null) {
            return;
        }
        long currentTime = Util.getMeasuringTimeMs();
        if (!this.initialized) {
            this.lastRenderTime = currentTime;
            this.initialized = true;
        }
        float deltaTime = (currentTime - this.lastRenderTime) / 1000.0f;
        this.lastRenderTime = currentTime;
        if (deltaTime < 0.001f) {
            deltaTime = 0.001f;
        }
        if (deltaTime > 0.1f) {
            deltaTime = 0.1f;
        }

        this.intro.update();
        float opacity = (float) this.intro.getValue();
        if (opacity <= 0.0f) {
            return;
        }

        this.pulseTime += deltaTime;
        this.animatedProgress += (this.targetProgress - this.animatedProgress) * Math.min(1.0f, deltaTime * 5.0f);
        this.progressGlow += deltaTime * 3.0f;
        this.updateTextAnimation(currentTime);

        int sw = (int) Math.ceil((double) mc.getWindow().getFramebufferWidth() / 2.0);
        int sh = (int) Math.ceil((double) mc.getWindow().getFramebufferHeight() / 2.0);

        Render2D.beginFrame(context);

        WhiteMenuBackground.draw(sw, sh, opacity);
        Render2D.rect(0.0f, 0.0f, sw, sh, MenuColors.rgba(0, 0, 0, (int) (opacity * 0.3f * 255.0f)));

        this.renderParticles(sw, sh, opacity, deltaTime);
        this.renderLogo(sw, sh, opacity);
        this.renderSubtitle(sw, sh, opacity);
        this.renderProgressBar(sw, sh, opacity);
        this.renderLoadingText(sw, sh, opacity);
        this.renderBottomInfo(sw, sh, opacity);

        Render2D.flush();
    }

    private void updateTextAnimation(long currentTime) {
        if (!this.isTransitioning) {
            long elapsed = currentTime - this.lastTextChangeTime;
            if (elapsed >= TEXT_DISPLAY_DURATION) {
                this.isTransitioning = true;
                this.transitionStartTime = currentTime;
            }
        }
        if (this.isTransitioning) {
            long elapsed = currentTime - this.transitionStartTime;
            float raw = MathHelper.clamp((float) elapsed / (float) TEXT_TRANSITION_DURATION, 0.0f, 1.0f);
            float eased = 1.0f - (float) Math.pow(1.0f - raw, 3.0);
            this.currentTextOffsetY = 14.0f * eased;
            this.currentTextAlpha = MathHelper.clamp(1.0f - eased * 1.5f, 0.0f, 1.0f);
            this.newTextOffsetY = -12.0f * (1.0f - eased);
            this.newTextAlpha = MathHelper.clamp(eased * 1.3f, 0.0f, 1.0f);
            if (raw >= 1.0f) {
                this.isTransitioning = false;
                this.currentTextIndex = (this.currentTextIndex + 1) % LOADING_TEXTS.length;
                this.currentTextOffsetY = 0.0f;
                this.currentTextAlpha = 1.0f;
                this.newTextOffsetY = -12.0f;
                this.newTextAlpha = 0.0f;
                this.lastTextChangeTime = currentTime;
            }
        }
    }

    private void renderParticles(int sw, int sh, float opacity, float dt) {
        for (Particle p : this.particles) {
            p.update(dt);
            int alpha = (int) (p.alpha * opacity * 255.0f);
            if (alpha <= 0) {
                continue;
            }
            Render2D.rect(p.x, p.y, p.size, p.size, p.size / 2.0f, MenuColors.withAlpha(0xFFFFFFFF, alpha));
        }
    }

    private void renderLogo(int sw, int sh, float opacity) {
        float centerX = sw / 2.0f;
        float centerY = sh / 2.0f - 40.0f;
        int textAlpha = (int) (opacity * 255.0f);
        float fontSize = 44.0f;
        float breathe = (float) Math.sin(this.pulseTime * 1.2f) * 1.5f;

        String icon = "A";
        float iconW = Fonts.WHITE_ICONS.width(icon, fontSize);
        float iconH = fontSize;
        float iconX = centerX - iconW / 2.0f;
        float iconY = centerY - iconH / 2.0f + breathe;

        float glowPulse = 0.4f + 0.2f * (float) Math.sin(this.pulseTime * 1.5f);
        Fonts.WHITE_ICONS.draw(icon, iconX, iconY + 3.0f, fontSize, MenuColors.withAlpha(0xFFFF7276, (int) (textAlpha * glowPulse)));
        Fonts.WHITE_ICONS.draw(icon, iconX + 2.0f, iconY + 2.0f, fontSize, MenuColors.withAlpha(0xFF000000, textAlpha / 3));
        Fonts.WHITE_ICONS.draw(icon, iconX, iconY, fontSize, MenuColors.withAlpha(0xFFFFFFFF, textAlpha));
    }

    private void renderSubtitle(int sw, int sh, float opacity) {
        float centerX = sw / 2.0f;
        float subtitleY = sh / 2.0f - 8.0f;
        float fontSize = 9.0f;
        String subtitle = "K I M I K O";
        float subtitleW = Fonts.BOLD.width(subtitle, fontSize);

        float lineW = 30.0f;
        float lineY = subtitleY + fontSize / 2.0f;
        float gap = 8.0f;
        int lineColor = MenuColors.withAlpha(0xFFFF7276, (int) (opacity * 40.0f));
        Render2D.rect(centerX - subtitleW / 2.0f - gap - lineW, lineY, lineW, 0.5f, 0.0f, lineColor);
        Render2D.rect(centerX + subtitleW / 2.0f + gap, lineY, lineW, 0.5f, 0.0f, lineColor);

        Fonts.BOLD.draw(subtitle, centerX - subtitleW / 2.0f, subtitleY, fontSize, MenuColors.withAlpha(0xFFFFFFFF, (int) (opacity * 180.0f)));
    }

    private void renderProgressBar(int sw, int sh, float opacity) {
        float centerX = sw / 2.0f;
        float barY = sh / 2.0f + 12.0f;
        float barW = 180.0f;
        float barH = 3.0f;
        float barX = centerX - barW / 2.0f;

        Render2D.rect(barX, barY, barW, barH, 1.5f, MenuColors.withAlpha(0xFFFFFFFF, (int) (opacity * 60.0f)));

        if (this.animatedProgress > 0.0f) {
            float filledW = barW * MathHelper.clamp(this.animatedProgress, 0.0f, 1.0f);
            int a1 = (int) (opacity * 200.0f);
            int a2 = (int) (opacity * 140.0f);
            Render2D.rect(barX, barY, filledW, barH, 1.5f,
                    MenuColors.withAlpha(0xFFFF7276, a1),
                    MenuColors.withAlpha(0xFFFF9A76, a2),
                    MenuColors.withAlpha(0xFFFF9A76, a2),
                    MenuColors.withAlpha(0xFFFF7276, a1));

            float glowPos = (float) ((this.progressGlow * 0.3f) % 1.0);
            float glowX = barX + filledW * glowPos;
            float glowW = 20.0f;
            if (glowX + glowW > barX + filledW) {
                glowW = barX + filledW - glowX;
            }
            if (glowW > 0.0f) {
                Render2D.rect(glowX, barY, glowW, barH, 1.5f, MenuColors.withAlpha(0xFFFFFFFF, (int) (opacity * 80.0f)));
            }
        }

        String pct = (int) (this.animatedProgress * 100.0f) + "%";
        Fonts.BOLD.draw(pct, barX + barW + 8.0f, barY - 1.0f, 6.0f, MenuColors.withAlpha(0xFFFFFFFF, (int) (opacity * 120.0f)));
    }

    private void renderLoadingText(int sw, int sh, float opacity) {
        float fontSize = 10.0f;
        float baseY = sh / 2.0f + 30.0f;
        float centerX = sw / 2.0f;

        if (this.currentTextAlpha > 0.01f) {
            String currentText = LOADING_TEXTS[this.currentTextIndex];
            float currentWidth = Fonts.REGULAR.width(currentText, fontSize);
            Fonts.REGULAR.draw(currentText, centerX - currentWidth / 2.0f, baseY + this.currentTextOffsetY, fontSize,
                    MenuColors.withAlpha(0xFFFFFFFF, (int) (opacity * this.currentTextAlpha * 220.0f)));
        }
        if (this.isTransitioning && this.newTextAlpha > 0.01f) {
            int nextIndex = (this.currentTextIndex + 1) % LOADING_TEXTS.length;
            String nextText = LOADING_TEXTS[nextIndex];
            float nextWidth = Fonts.REGULAR.width(nextText, fontSize);
            Fonts.REGULAR.draw(nextText, centerX - nextWidth / 2.0f, baseY + this.newTextOffsetY, fontSize,
                    MenuColors.withAlpha(0xFFFFFFFF, (int) (opacity * this.newTextAlpha * 220.0f)));
        }
    }

    private void renderBottomInfo(int sw, int sh, float opacity) {
        float y = sh - 20.0f;
        float fontSize = 6.0f;
        Fonts.BOLD.draw("Kimiko · 1.21.11", 15.0f, y, fontSize, MenuColors.withAlpha(0xFFFFFFFF, (int) (opacity * 50.0f)));
        String copyright = "Kimiko";
        float copyrightW = Fonts.BOLD.width(copyright, fontSize);
        Fonts.BOLD.draw(copyright, sw - copyrightW - 15.0f, y, fontSize, MenuColors.withAlpha(0xFFFFFFFF, (int) (opacity * 50.0f)));
    }
}
