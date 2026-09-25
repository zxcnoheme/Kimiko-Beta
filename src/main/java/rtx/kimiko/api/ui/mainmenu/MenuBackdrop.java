/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.mainmenu;

import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.ui.mainmenu.MenuBackground;
import rtx.kimiko.api.ui.mainmenu.MenuBackgrounds;
import rtx.kimiko.api.ui.mainmenu.MenuSettings;
import rtx.kimiko.api.ui.mainmenu.MenuTextures;
import rtx.kimiko.api.ui.mainmenu.MenuTheme;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0014\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003JK\u0010\u000f\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000f\u0010\u0010JC\u0010\u000f\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000f\u0010\u0011J1\u0010\u0014\u001a\u00020\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J/\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0015J'\u0010\u0017\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J'\u0010\u0019\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u0018J'\u0010\u001a\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u0018J'\u0010\u001b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u0018J'\u0010\u001c\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u0018J'\u0010\u001d\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u0018J/\u0010\u001e\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ'\u0010\"\u001a\u00020\u00042\u0006\u0010!\u001a\u00020 2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\"\u0010#J/\u0010&\u001a\u00020\u00042\u0006\u0010%\u001a\u00020$2\u0006\u0010!\u001a\u00020 2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b&\u0010'J\u001f\u0010(\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020$8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020$8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010+R\u0014\u0010-\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u0014\u00100\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0014\u00102\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u00101R\u0014\u00103\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b3\u00101R\u0014\u00104\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u00101R\u0014\u00105\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00101R\u0014\u00106\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b6\u00101R\u0014\u00107\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b7\u00101R\u0014\u00108\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00101R\u0014\u00109\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b9\u00101R\u0014\u0010:\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u00101R\u0014\u0010;\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b;\u00101R\u0016\u0010=\u001a\u00020<8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b=\u0010>R\u0018\u0010?\u001a\u0004\u0018\u00010 8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b?\u0010@R\u0018\u0010A\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bA\u0010BR\u0018\u0010C\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bC\u0010BR\u0016\u0010D\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bD\u0010.R\u0016\u0010E\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bE\u0010.R\u0016\u0010F\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bF\u0010.R\u0016\u0010G\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bG\u0010.R\u0016\u0010I\u001a\u00020H8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bI\u0010JR\u0016\u0010K\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010.R\u0016\u0010L\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bL\u0010.R\u0016\u0010M\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bM\u0010.\u00a8\u0006N"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuBackdrop;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "reset", "", "w", "h", "mouseX", "mouseY", "dt", "alpha", "intro", "render", "(FFFFFFF)V", "(FFFFFF)V", "Lrtx/kimiko/api/ui/mainmenu/MenuBackground;", "background", "drawLayer", "(Lrtx/kimiko/api/ui/mainmenu/MenuBackground;FFF)V", "drawImage", "drawDepth", "(FFF)V", "drawAurora", "drawDim", "drawCursorLight", "drawVignette", "drawGrain", "drawAtmosphere", "(FFFF)V", "Lrtx/kimiko/api/ui/mainmenu/MenuSettings$Atmosphere;", "mode", "seedParticles", "(Lrtx/kimiko/api/ui/mainmenu/MenuSettings$Atmosphere;FF)V", "", "index", "respawn", "(ILrtx/kimiko/api/ui/mainmenu/MenuSettings$Atmosphere;FF)V", "seedStars", "(FF)V", "PARTICLE_COUNT", "I", "STAR_COUNT", "FADE_SPEED", "F", "", "px", "[F", "py", "pvx", "pvy", "psize", "plife", "pseed", "starX", "starY", "starSize", "starPhase", "", "seeded", "Z", "seededFor", "Lrtx/kimiko/api/ui/mainmenu/MenuSettings$Atmosphere;", "current", "Lrtx/kimiko/api/ui/mainmenu/MenuBackground;", "incoming", "fade", "drift", "grainJitterX", "grainJitterY", "", "grainStamp", "J", "parallaxX", "parallaxY", "introZoom", "rtx.kimiko:kimiko"})
public final class MenuBackdrop {
    @NotNull
    public static final MenuBackdrop INSTANCE = new MenuBackdrop();
    private static final int PARTICLE_COUNT = 90;
    private static final int STAR_COUNT = 130;
    private static final float FADE_SPEED = 1.6f;
    @NotNull
    private static final float[] px = new float[90];
    @NotNull
    private static final float[] py = new float[90];
    @NotNull
    private static final float[] pvx = new float[90];
    @NotNull
    private static final float[] pvy = new float[90];
    @NotNull
    private static final float[] psize = new float[90];
    @NotNull
    private static final float[] plife = new float[90];
    @NotNull
    private static final float[] pseed = new float[90];
    @NotNull
    private static final float[] starX = new float[130];
    @NotNull
    private static final float[] starY = new float[130];
    @NotNull
    private static final float[] starSize = new float[130];
    @NotNull
    private static final float[] starPhase = new float[130];
    private static boolean seeded;
    @Nullable
    private static MenuSettings.Atmosphere seededFor;
    @Nullable
    private static MenuBackground current;
    @Nullable
    private static MenuBackground incoming;
    private static float fade;
    private static float drift;
    private static float grainJitterX;
    private static float grainJitterY;
    private static long grainStamp;
    private static float parallaxX;
    private static float parallaxY;
    private static float introZoom;

    private MenuBackdrop() {
    }

    @JvmStatic
    public static final void reset() {
        current = null;
        incoming = null;
        fade = 1.0f;
        parallaxX = 0.0f;
        parallaxY = 0.0f;
    }

    @JvmStatic
    public static final void render(float w, float h, float mouseX, float mouseY, float dt, float alpha, float intro) {
        introZoom = 1.0f + (1.0f - MenuTheme.clamp01(intro)) * 0.06f;
        MenuBackdrop.render(w, h, mouseX, mouseY, dt, alpha);
    }

    @JvmStatic
    public static final void render(float w, float h, float mouseX, float mouseY, float dt, float alpha) {
        MenuBackground selected = MenuBackgrounds.selected();
        if (current == null) {
            current = selected;
            incoming = null;
            fade = 1.0f;
        } else if (selected != current && selected != incoming) {
            incoming = selected;
            fade = 0.0f;
        }
        if (incoming != null && (fade += dt * 1.6f) >= 1.0f) {
            current = incoming;
            incoming = null;
            fade = 1.0f;
        }
        if (MenuSettings.drift()) {
            drift += dt * 0.055f;
        }
        float parallaxAmount = MenuSettings.parallax();
        float targetX = (mouseX / Math.max(1.0f, w) - 0.5f) * -13.0f * parallaxAmount;
        float targetY = (mouseY / Math.max(1.0f, h) - 0.5f) * -9.0f * parallaxAmount;
        parallaxX = MenuTheme.approach(parallaxX, targetX, dt, 5.0f);
        parallaxY = MenuTheme.approach(parallaxY, targetY, dt, 5.0f);
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f, MenuTheme.fade(-16316660, alpha));
        MenuBackground currentBackground = current;
        MenuBackground incomingBackground = incoming;
        if (incomingBackground != null && currentBackground != null) {
            INSTANCE.drawLayer(currentBackground, w, h, alpha * (1.0f - fade));
            INSTANCE.drawLayer(incomingBackground, w, h, alpha * fade);
        } else if (currentBackground != null) {
            INSTANCE.drawLayer(currentBackground, w, h, alpha);
        }
        INSTANCE.drawDim(w, h, alpha);
        INSTANCE.drawCursorLight(mouseX, mouseY, alpha);
        INSTANCE.drawAtmosphere(w, h, dt, alpha);
        INSTANCE.drawVignette(w, h, alpha);
        INSTANCE.drawGrain(w, h, alpha);
    }

    private final void drawLayer(MenuBackground background, float w, float h, float alpha) {
        if (background == null || alpha <= 0.004f) {
            return;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[background.kind().ordinal()]) {
            case 1: {
                this.drawAurora(w, h, alpha);
                break;
            }
            case 2: {
                this.drawDepth(w, h, alpha);
                break;
            }
            default: {
                this.drawImage(background, w, h, alpha);
            }
        }
    }

    private final void drawImage(MenuBackground background, float w, float h, float alpha) {
        String soft;
        String sharp = background.texture();
        if (sharp == null) {
            this.drawDepth(w, h, alpha);
            return;
        }
        float zoom = introZoom * (1.045f + (MenuSettings.drift() ? 0.028f * (float)Math.sin(drift * 1.31f) : 0.0f));
        float aspect = background.aspect();
        float drawW = 0.0f;
        float drawH = 0.0f;
        if (aspect >= w / Math.max(1.0f, h)) {
            drawH = h * zoom;
            drawW = drawH * aspect;
        } else {
            drawW = w * zoom;
            drawH = drawW / aspect;
        }
        float driftX = MenuSettings.drift() ? (float)Math.sin(drift * 0.74f) * 7.0f : 0.0f;
        float driftY = MenuSettings.drift() ? (float)Math.cos(drift * 0.51f) * 5.0f : 0.0f;
        float x = (w - drawW) * 0.5f + driftX + parallaxX;
        float y = (h - drawH) * 0.5f + driftY + parallaxY;
        int tint = MenuTheme.white(255.0f, alpha);
        Render2D.image(sharp, x, y, drawW, drawH, 0.0f, tint);
        float blur = MenuSettings.blur();
        if (blur > 0.004f && (soft = background.blurred()) != null) {
            Render2D.image(soft, x, y, drawW, drawH, 0.0f, MenuTheme.white(255.0f, alpha * blur));
        }
    }

    private final void drawDepth(float w, float h, float alpha) {
        int top = ColorEngine.lerpColor(-16119278, ClientAccent.accentOpaque(), 0.1f);
        int bottom = ColorEngine.lerpColor(-16382454, ClientAccent.accentOpaque(), 0.03f);
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f, MenuTheme.fade(top, alpha), MenuTheme.fade(top, alpha), MenuTheme.fade(bottom, alpha), MenuTheme.fade(bottom, alpha));
        float cx = w * 0.5f + parallaxX * 1.6f;
        float cy = h * 0.86f + parallaxY * 1.6f;
        float radius = Math.max(w, h) * 0.72f;
        for (int i = 0; i < 5; ++i) {
            float t = (float)i / 4.0f;
            float r = radius * (0.34f + t * 0.66f);
            Render2D.circle(cx, cy, r, r * 0.9f, MenuTheme.accent(26.0f * (1.0f - t), alpha * 0.75f));
        }
    }

    private final void drawAurora(float w, float h, float alpha) {
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f, MenuTheme.fade(-16316658, alpha), MenuTheme.fade(-16316658, alpha), MenuTheme.fade(-15987688, alpha), MenuTheme.fade(-15987688, alpha));
        this.seedStars(w, h);
        float now = (float)(System.currentTimeMillis() % 600000L) / 1000.0f;
        for (int i = 0; i < 130; ++i) {
            float twinkle = 0.45f + 0.55f * (float)Math.sin(now * 1.7f + starPhase[i]);
            float sx = starX[i] * w + parallaxX * 0.35f;
            float sy = starY[i] * h + parallaxY * 0.35f;
            Render2D.circle(sx, sy, starSize[i], starSize[i], MenuTheme.white(150.0f * twinkle, alpha));
        }
        int segments = 56;
        float step = w / (float)segments;
        for (int band = 0; band < 3; ++band) {
            float bandPhase = drift * (2.4f + (float)band * 0.7f) + (float)band * 2.1f;
            float baseY = h * (0.18f + (float)band * 0.12f);
            float amp = h * (0.07f + (float)band * 0.03f);
            float thickness = h * (0.16f + (float)band * 0.05f);
            float bandAlpha = alpha * (0.3f - (float)band * 0.07f);
            for (int i = 0; i < segments; ++i) {
                float t = (float)i / (float)segments;
                float wave = (float)Math.sin(t * 6.28318f * 1.4f + bandPhase) * 0.6f + (float)Math.sin(t * 6.28318f * 2.9f - bandPhase * 0.6f) * 0.4f;
                float y = baseY + wave * amp + parallaxY * 0.6f;
                float edge = (float)Math.sin(t * 3.14159f);
                int topColor = MenuTheme.accentAt(96.0f * edge, bandAlpha, (float)i * step, y);
                int bottomColor = MenuTheme.accentAt(0.0f, bandAlpha, (float)i * step, y + thickness);
                Render2D.rect((float)i * step + parallaxX * 0.5f, y, step + 0.6f, thickness, 0.0f, topColor, topColor, bottomColor, bottomColor);
            }
        }
    }

    private final void drawDim(float w, float h, float alpha) {
        float dim = MenuSettings.dim();
        if (dim <= 0.004f) {
            return;
        }
        int top = MenuTheme.black(190.0f * dim, alpha * 0.8f);
        int bottom = MenuTheme.black(230.0f * dim, alpha);
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f, top, top, bottom, bottom);
    }

    private final void drawCursorLight(float mouseX, float mouseY, float alpha) {
        for (int i = 0; i < 4; ++i) {
            float t = (float)i / 3.0f;
            float radius = 26.0f + t * 82.0f;
            Render2D.circle(mouseX, mouseY, radius, radius, MenuTheme.white(9.0f * (1.0f - t), alpha * 0.75f));
        }
    }

    private final void drawVignette(float w, float h, float alpha) {
        float strength = MenuSettings.vignette();
        if (strength <= 0.004f) {
            return;
        }
        String texture = MenuTextures.vignette();
        if (texture == null || !Render2D.imageReady(texture)) {
            return;
        }
        float over = Math.max(w, h) * 0.08f;
        Render2D.image(texture, -over, -over, w + over * 2.0f, h + over * 2.0f, 0.0f, MenuTheme.black(235.0f * strength, alpha));
    }

    private final void drawGrain(float w, float h, float alpha) {
        float strength = MenuSettings.grain();
        if (strength <= 0.004f) {
            return;
        }
        String texture = MenuTextures.noise();
        if (texture == null || !Render2D.imageReady(texture)) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - grainStamp > 45L) {
            grainStamp = now;
            grainJitterX = (float)Math.random();
            grainJitterY = (float)Math.random();
        }
        float tile = 88.0f;
        float u = w / tile;
        float v = h / tile;
        Render2D.imageUv(texture, 0.0f, 0.0f, w, h, 0.0f, 0.0f, grainJitterX, grainJitterY, grainJitterX + u, grainJitterY + v, MenuTheme.white(52.0f * strength, alpha));
    }

    private final void drawAtmosphere(float w, float h, float dt, float alpha) {
        MenuSettings.Atmosphere mode = MenuSettings.atmosphere();
        if (mode == MenuSettings.Atmosphere.NONE || alpha <= 0.01f) {
            return;
        }
        this.seedParticles(mode, w, h);
        block4: for (int i = 0; i < 90; ++i) {
            float[] fArray = px;
            int n = i;
            fArray[n] = fArray[n] + pvx[i] * dt;
            fArray = py;
            n = i;
            fArray[n] = fArray[n] + pvy[i] * dt;
            fArray = plife;
            n = i;
            fArray[n] = fArray[n] - dt;
            if (plife[i] <= 0.0f || py[i] < -20.0f || py[i] > h + 20.0f || px[i] < -20.0f || px[i] > w + 20.0f) {
                this.respawn(i, mode, w, h);
            }
            float fadeIn = MenuTheme.clamp01(plife[i] * 0.6f);
            float pulse = 0.55f + 0.45f * (float)Math.sin((double)pseed[i] + (double)System.currentTimeMillis() / 620.0);
            float x = px[i] + parallaxX * 0.8f;
            float y = py[i] + parallaxY * 0.8f;
            switch (WhenMappings.$EnumSwitchMapping$1[mode.ordinal()]) {
                case 1: {
                    Render2D.rect(x, y, 0.55f, psize[i] * 5.5f, 0.3f, MenuTheme.white(70.0f * fadeIn, alpha));
                    continue block4;
                }
                case 2: {
                    Render2D.circle(x, y, psize[i] * 1.9f, psize[i] * 1.9f, MenuTheme.accent(70.0f * fadeIn * pulse, alpha));
                    Render2D.circle(x, y, psize[i] * 0.7f, psize[i] * 0.7f, MenuTheme.white(180.0f * fadeIn * pulse, alpha));
                    continue block4;
                }
                default: {
                    Render2D.circle(x, y, psize[i], psize[i], MenuTheme.white(66.0f * fadeIn * pulse, alpha));
                }
            }
        }
    }

    private final void seedParticles(MenuSettings.Atmosphere mode, float w, float h) {
        if (seeded && seededFor == mode) {
            return;
        }
        seeded = true;
        seededFor = mode;
        Random random = new Random(2654435769L);
        for (int i = 0; i < 90; ++i) {
            this.respawn(i, mode, w, h);
            MenuBackdrop.px[i] = random.nextFloat() * w;
            MenuBackdrop.py[i] = random.nextFloat() * h;
            MenuBackdrop.plife[i] = 2.0f + random.nextFloat() * 9.0f;
        }
    }

    private final void respawn(int index, MenuSettings.Atmosphere mode, float w, float h) {
        float rx = (float)Math.random();
        float ry = (float)Math.random();
        MenuBackdrop.pseed[index] = (float)(Math.random() * 6.28318);
        switch (WhenMappings.$EnumSwitchMapping$1[mode.ordinal()]) {
            case 1: {
                MenuBackdrop.px[index] = rx * (w + 60.0f) - 30.0f;
                MenuBackdrop.py[index] = -10.0f - ry * h * 0.5f;
                MenuBackdrop.pvx[index] = -6.0f - (float)Math.random() * 5.0f;
                MenuBackdrop.pvy[index] = 150.0f + (float)Math.random() * 110.0f;
                MenuBackdrop.psize[index] = 0.7f + (float)Math.random() * 0.9f;
                MenuBackdrop.plife[index] = 4.0f + (float)Math.random() * 3.0f;
                break;
            }
            case 2: {
                MenuBackdrop.px[index] = rx * w;
                MenuBackdrop.py[index] = h * 0.35f + ry * h * 0.65f;
                MenuBackdrop.pvx[index] = (float)(Math.random() - 0.5) * 5.0f;
                MenuBackdrop.pvy[index] = -3.0f - (float)Math.random() * 9.0f;
                MenuBackdrop.psize[index] = 0.5f + (float)Math.random() * 0.8f;
                MenuBackdrop.plife[index] = 4.0f + (float)Math.random() * 6.0f;
                break;
            }
            default: {
                MenuBackdrop.px[index] = rx * w;
                MenuBackdrop.py[index] = ry * h;
                MenuBackdrop.pvx[index] = (float)(Math.random() - 0.5) * 4.5f;
                MenuBackdrop.pvy[index] = -1.5f - (float)Math.random() * 4.0f;
                MenuBackdrop.psize[index] = 0.35f + (float)Math.random() * 0.7f;
                MenuBackdrop.plife[index] = 6.0f + (float)Math.random() * 8.0f;
            }
        }
    }

    private final void seedStars(float w, float h) {
        if (starSize[0] > 0.0f) {
            return;
        }
        Random random = new Random(12648430L);
        for (int i = 0; i < 130; ++i) {
            MenuBackdrop.starX[i] = random.nextFloat();
            MenuBackdrop.starY[i] = random.nextFloat() * 0.72f;
            MenuBackdrop.starSize[i] = 0.28f + random.nextFloat() * 0.55f;
            MenuBackdrop.starPhase[i] = random.nextFloat() * 6.28318f;
        }
    }

    static {
        fade = 1.0f;
        introZoom = 1.0f;
    }

    @Metadata(mv={2, 4, 0}, k=3, xi=48)
    public static final class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] nArray = new int[MenuBackground.Kind.values().length];
            try {
                nArray[MenuBackground.Kind.AURORA.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[MenuBackground.Kind.GRADIENT.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$0 = nArray;
            nArray = new int[MenuSettings.Atmosphere.values().length];
            try {
                nArray[MenuSettings.Atmosphere.RAIN.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[MenuSettings.Atmosphere.SPARKS.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$1 = nArray;
        }
    }
}

