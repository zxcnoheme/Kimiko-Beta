/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.math.MathKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils.guishare;

import java.awt.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.math.MathKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareThemeState;
import rtx.kimiko.utils.animations.Easing;
import rtx.kimiko.utils.animations.SmoothAnimation;
import rtx.kimiko.utils.color.ColorEngine;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u0015\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0006\n\u0002\b\t\u0018\u0000 O2\u00020\u0001:\u0001OB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000e\u0010\bJ\u0017\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0019\u0010\u0018J\u0015\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u001a\u0010\u0018J\u0015\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u001b\u0010\u0018J\u0015\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u001c\u0010\u0018J\u000f\u0010\u001e\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u001d\u0010!\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b!\u0010\"J\r\u0010#\u001a\u00020\u001d\u00a2\u0006\u0004\b#\u0010\u001fJ\r\u0010$\u001a\u00020\u0013\u00a2\u0006\u0004\b$\u0010%J\r\u0010&\u001a\u00020\u0013\u00a2\u0006\u0004\b&\u0010%J\r\u0010'\u001a\u00020\u0013\u00a2\u0006\u0004\b'\u0010%J\r\u0010)\u001a\u00020(\u00a2\u0006\u0004\b)\u0010*J\r\u0010+\u001a\u00020\u0013\u00a2\u0006\u0004\b+\u0010%J\r\u0010,\u001a\u00020\u0013\u00a2\u0006\u0004\b,\u0010%J\r\u0010-\u001a\u00020\u000f\u00a2\u0006\u0004\b-\u0010.J\r\u0010/\u001a\u00020\u000f\u00a2\u0006\u0004\b/\u0010.J\r\u00100\u001a\u00020\u0013\u00a2\u0006\u0004\b0\u0010%J\r\u00101\u001a\u00020\u0013\u00a2\u0006\u0004\b1\u0010%J\r\u00102\u001a\u00020\u0013\u00a2\u0006\u0004\b2\u0010%J\r\u00103\u001a\u00020\u0013\u00a2\u0006\u0004\b3\u0010%J\r\u00104\u001a\u00020\u0013\u00a2\u0006\u0004\b4\u0010%J\r\u00105\u001a\u00020\u0013\u00a2\u0006\u0004\b5\u0010%J\r\u00106\u001a\u00020\u0013\u00a2\u0006\u0004\b6\u0010%R\u0016\u00107\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b7\u00108R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010<\u001a\u0002098\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u0010;R\u0014\u0010=\u001a\u0002098\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u0010;R\u0016\u0010>\u001a\u00020(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0016\u0010@\u001a\u00020(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b@\u0010?R\u0016\u0010A\u001a\u00020(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bA\u0010?R\u0016\u0010B\u001a\u00020(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bB\u0010?R\u0016\u0010+\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u0010CR\u0016\u0010D\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bD\u0010ER\u0016\u0010F\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bF\u0010CR\u0016\u0010H\u001a\u00020G8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bH\u0010IR\u0014\u0010J\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010KR\u0014\u0010L\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u0010KR\u0014\u0010#\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010KR\u0016\u0010M\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bM\u0010N\u00a8\u0006P"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteTheme;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;", "state", "", "apply", "(Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;)V", "", "now", "update", "(J)V", "s", "refreshRainbow", "", "index", "shadeRgb", "(I)I", "", "alpha255", "shade", "(IF)I", "accent", "(F)I", "accentBright", "accentSoft", "gradientA", "gradientB", "", "effectivePalette", "()[I", "t", "gradientColor", "(FF)I", "palette6", "phase", "()F", "styleId", "closed", "", "isThemeMode", "()Z", "colorOffset", "glowBlend", "primaryColor", "()I", "secondaryColor", "cornerRadius", "backdropBlur", "refraction", "edgeStrength", "edgeSharpness", "glowIntensity", "glowRadius", "synced", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;", "Lrtx/kimiko/utils/animations/SmoothAnimation;", "colorMovementSpeed", "Lrtx/kimiko/utils/animations/SmoothAnimation;", "secondColorBlend", "glowBlendAnim", "movementTarget", "Z", "secondTarget", "glowTarget", "primed", "F", "lastMs", "J", "palettePhase", "", "huePhase", "D", "rainbowShades", "[I", "rainbowPalette", "lastSecondColor", "I", "Companion", "rtx.kimiko:kimiko"})
public final class RemoteTheme {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private GuiShareThemeState synced = GuiShareThemeState.DEFAULTS;
    @NotNull
    private final SmoothAnimation colorMovementSpeed = new SmoothAnimation();
    @NotNull
    private final SmoothAnimation secondColorBlend = new SmoothAnimation();
    @NotNull
    private final SmoothAnimation glowBlendAnim = new SmoothAnimation();
    private boolean movementTarget;
    private boolean secondTarget;
    private boolean glowTarget;
    private boolean primed;
    private float colorOffset;
    private long lastMs;
    private float palettePhase;
    private double huePhase;
    @NotNull
    private final int[] rainbowShades = new int[7];
    @NotNull
    private final int[] rainbowPalette = new int[9];
    @NotNull
    private final int[] palette6 = new int[6];
    private int lastSecondColor = -857872385;
    private static final long COLOR_MOVEMENT_PERIOD_MS = 2400L;
    private static final long MAX_COLOR_FRAME_DELTA_MS = 100L;
    private static final long PALETTE_PERIOD_MS = 24000L;
    private static final long RAINBOW_CYCLE_MS = 6000L;
    private static final long MAX_HUE_FRAME_DELTA_MS = 200L;
    private static final double COLOR_MOVEMENT_ACCEL_SECONDS = 0.45;
    private static final double COLOR_MOVEMENT_DECEL_SECONDS = 0.7;
    private static final double SECOND_COLOR_FADE_IN_SECONDS = 0.3;
    private static final double SECOND_COLOR_FADE_OUT_SECONDS = 0.42;
    private static final double GLOW_FADE_IN_SECONDS = 0.5;
    private static final double GLOW_FADE_OUT_SECONDS = 0.5;
    private static final float THEME_COLOR_ALPHA = 204.0f;
    @NotNull
    private static final Easing COLOR_EASING = RemoteTheme::COLOR_EASING$lambda$0;

    public final void apply(@Nullable GuiShareThemeState state) {
        GuiShareThemeState guiShareThemeState = state;
        if (guiShareThemeState == null) {
            guiShareThemeState = GuiShareThemeState.DEFAULTS;
        }
        this.synced = guiShareThemeState;
    }

    public final void update(long now) {
        GuiShareThemeState s = this.synced;
        long dt = this.lastMs == 0L ? 0L : Math.max(0L, now - this.lastMs);
        this.lastMs = now;
        if (!this.primed) {
            this.primed = true;
            this.movementTarget = s.movement;
            this.secondTarget = s.usesSecond;
            this.glowTarget = s.glow;
            this.colorMovementSpeed.set(s.movement ? 1.0 : 0.0);
            this.secondColorBlend.set(s.usesSecond ? 1.0 : 0.0);
            this.glowBlendAnim.set(s.glow ? 1.0 : 0.0);
        }
        if (s.movement != this.movementTarget) {
            this.colorMovementSpeed.run(s.movement ? 1.0 : 0.0, s.movement ? 0.45 : 0.7, COLOR_EASING, false);
            this.movementTarget = s.movement;
        }
        this.colorMovementSpeed.update();
        long colorDt = Math.min(dt, 100L);
        float speed = RemoteTheme.Companion.clamp01(this.colorMovementSpeed.get());
        this.colorOffset = RemoteTheme.Companion.wrap(this.colorOffset + (float)colorDt * speed / 2400.0f);
        if (s.usesSecond != this.secondTarget) {
            this.secondColorBlend.run(s.usesSecond ? 1.0 : 0.0, s.usesSecond ? 0.3 : 0.42, COLOR_EASING, false);
            this.secondTarget = s.usesSecond;
        }
        this.secondColorBlend.update();
        if (s.glow != this.glowTarget) {
            this.glowBlendAnim.run(s.glow ? 1.0 : 0.0, s.glow ? 0.5 : 0.5, COLOR_EASING, false);
            this.glowTarget = s.glow;
        }
        this.glowBlendAnim.update();
        if (s.movement) {
            this.palettePhase = RemoteTheme.Companion.wrap(this.palettePhase + (float)dt / 24000.0f);
        }
        long hueDt = Math.min(dt, 200L);
        this.huePhase += (double)((float)hueDt * Math.max(0.1f, s.rainbowSpeed)) / 6000.0;
        this.huePhase -= Math.floor(this.huePhase);
        if (s.mode == 2) {
            this.refreshRainbow(s);
        }
        if (s.usesSecond) {
            this.lastSecondColor = RemoteTheme.Companion.rgba(this.shadeRgb(6), 204.0f);
        }
        RemoteTheme.Companion.resample(this.effectivePalette(), this.palette6);
    }

    private final void refreshRainbow(GuiShareThemeState s) {
        int primary;
        float hue = (float)this.huePhase;
        float sat = RemoteTheme.Companion.clamp01(s.rainbowSaturation);
        float spread = s.rainbowSpread;
        this.rainbowShades[0] = primary = Color.HSBtoRGB(hue, sat, 1.0f) & 0xFFFFFF;
        this.rainbowShades[1] = Color.HSBtoRGB(hue, RemoteTheme.Companion.clamp01(sat * 0.7f), 1.0f) & 0xFFFFFF;
        this.rainbowShades[2] = Color.HSBtoRGB(hue, RemoteTheme.Companion.clamp01(sat * 0.5f), 1.0f) & 0xFFFFFF;
        this.rainbowShades[3] = Color.HSBtoRGB(hue, sat, 0.8f) & 0xFFFFFF;
        this.rainbowShades[4] = Color.HSBtoRGB(hue, sat, 0.6f) & 0xFFFFFF;
        this.rainbowShades[5] = primary;
        this.rainbowShades[6] = Color.HSBtoRGB(RemoteTheme.Companion.wrap(hue + spread), sat, 1.0f) & 0xFFFFFF;
        float paletteSpread = Math.min(1.0f, spread * 4.0f);
        int n = this.rainbowPalette.length;
        for (int i = 0; i < n; ++i) {
            float stopHue = hue + (float)i / (float)(n - 1) * paletteSpread;
            stopHue -= (float)Math.floor(stopHue);
            int rgb = Color.HSBtoRGB(stopHue, 1.0f, 1.0f) & 0xFFFFFF;
            this.rainbowPalette[i] = RemoteTheme.Companion.lerpWhite(rgb, sat);
        }
    }

    private final int shadeRgb(int index) {
        if (this.synced.mode == 2) {
            return this.rainbowShades[index];
        }
        int[] shades = this.synced.shades;
        return shades[Math.max(0, Math.min(shades.length - 1, index))] & 0xFFFFFF;
    }

    public final int shade(int index, float alpha255) {
        return RemoteTheme.Companion.rgba(this.shadeRgb(index), alpha255);
    }

    public final int accent(float alpha255) {
        return this.shade(0, alpha255);
    }

    public final int accentBright(float alpha255) {
        return this.shade(1, alpha255);
    }

    public final int accentSoft(float alpha255) {
        return this.shade(2, alpha255);
    }

    public final int gradientA(float alpha255) {
        return this.shade(5, alpha255);
    }

    public final int gradientB(float alpha255) {
        return this.shade(6, alpha255);
    }

    private final int[] effectivePalette() {
        if (this.synced.mode == 2) {
            return this.rainbowPalette;
        }
        int[] palette = this.synced.palette;
        if (palette.length == 0) {
            int[] nArray = new int[]{this.shadeRgb(5)};
            return nArray;
        }
        return palette;
    }

    public final int gradientColor(float t, float alpha255) {
        int[] palette = this.effectivePalette();
        if (palette.length <= 1) {
            return RemoteTheme.Companion.rgba(palette[0] & 0xFFFFFF, alpha255);
        }
        float f = RemoteTheme.Companion.clamp01(t) * (float)(palette.length - 1);
        int i = (int)Math.floor(f);
        if (i > palette.length - 2) {
            i = palette.length - 2;
        }
        return RemoteTheme.Companion.rgba(RemoteTheme.Companion.mixRgb(palette[i], palette[i + 1], f - (float)i), alpha255);
    }

    @NotNull
    public final int[] palette6() {
        return this.palette6;
    }

    public final float phase() {
        return this.palettePhase;
    }

    public final float styleId() {
        return this.synced.gradientStyleId;
    }

    public final float closed() {
        return this.synced.mode == 2 ? 1.0f : 0.0f;
    }

    public final boolean isThemeMode() {
        return this.synced.mode == 0;
    }

    public final float colorOffset() {
        return this.colorOffset;
    }

    public final float glowBlend() {
        return RemoteTheme.Companion.clamp01(this.glowBlendAnim.get());
    }

    public final int primaryColor() {
        return RemoteTheme.Companion.rgba(this.shadeRgb(5), 204.0f);
    }

    public final int secondaryColor() {
        return ColorEngine.lerpColor(this.primaryColor(), this.lastSecondColor, RemoteTheme.Companion.clamp01(this.secondColorBlend.get()));
    }

    public final float cornerRadius() {
        return this.synced.cornerRadius;
    }

    public final float backdropBlur() {
        return this.synced.backdropBlur;
    }

    public final float refraction() {
        return this.synced.refraction;
    }

    public final float edgeStrength() {
        return this.synced.edgeStrength;
    }

    public final float edgeSharpness() {
        return this.synced.edgeSharpness;
    }

    public final float glowIntensity() {
        return this.synced.glowIntensity;
    }

    public final float glowRadius() {
        return this.synced.glowRadius;
    }

    private static final double COLOR_EASING$lambda$0(double value) {
        return value * value * (3.0 - 2.0 * value);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ'\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0016\u0010\tJ\u0017\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u0019R\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001dR\u0014\u0010 \u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010\u001dR\u0014\u0010!\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b!\u0010\u001dR\u0014\u0010#\u001a\u00020\"8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\"8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010$R\u0014\u0010&\u001a\u00020\"8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010$R\u0014\u0010'\u001a\u00020\"8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010$R\u0014\u0010(\u001a\u00020\"8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010$R\u0014\u0010)\u001a\u00020\"8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010$R\u0014\u0010*\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010-\u001a\u00020,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b-\u0010.\u00a8\u0006/"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteTheme.Companion;", "", "<init>", "()V", "", "rgb", "", "sat", "lerpWhite", "(IF)I", "", "palette", "out", "", "resample", "([I[I)V", "a", "b", "t", "mixRgb", "(IIF)I", "alpha255", "rgba", "value", "wrap", "(F)F", "clamp01", "", "COLOR_MOVEMENT_PERIOD_MS", "J", "MAX_COLOR_FRAME_DELTA_MS", "PALETTE_PERIOD_MS", "RAINBOW_CYCLE_MS", "MAX_HUE_FRAME_DELTA_MS", "", "COLOR_MOVEMENT_ACCEL_SECONDS", "D", "COLOR_MOVEMENT_DECEL_SECONDS", "SECOND_COLOR_FADE_IN_SECONDS", "SECOND_COLOR_FADE_OUT_SECONDS", "GLOW_FADE_IN_SECONDS", "GLOW_FADE_OUT_SECONDS", "THEME_COLOR_ALPHA", "F", "Lrtx/kimiko/utils/animations/Easing;", "COLOR_EASING", "Lrtx/kimiko/utils/animations/Easing;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final int lerpWhite(int rgb, float sat) {
            float keep = this.clamp01(sat);
            int r = MathKt.roundToInt((float)((float)255 + (float)((rgb >> 16 & 0xFF) - 255) * keep));
            int g = MathKt.roundToInt((float)((float)255 + (float)((rgb >> 8 & 0xFF) - 255) * keep));
            int b = MathKt.roundToInt((float)((float)255 + (float)((rgb & 0xFF) - 255) * keep));
            return r << 16 | g << 8 | b;
        }

        private final void resample(int[] palette, int[] out) {
            if (palette.length == 1) {
                int c = palette[0] & 0xFFFFFF;
                int n = out.length;
                for (int i = 0; i < n; ++i) {
                    out[i] = c;
                }
                return;
            }
            int n = out.length;
            for (int i = 0; i < n; ++i) {
                float pos = out.length <= 1 ? 0.0f : (float)i / (float)(out.length - 1);
                float f = pos * (float)(palette.length - 1);
                int idx = (int)f;
                if (idx > palette.length - 2) {
                    idx = palette.length - 2;
                }
                out[i] = this.mixRgb(palette[idx], palette[idx + 1], f - (float)idx);
            }
        }

        private final int mixRgb(int a, int b, float t) {
            float tt = this.clamp01(t);
            int ar = a >> 16 & 0xFF;
            int ag = a >> 8 & 0xFF;
            int ab = a & 0xFF;
            int br = b >> 16 & 0xFF;
            int bg = b >> 8 & 0xFF;
            int bb = b & 0xFF;
            int r = MathKt.roundToInt((float)((float)ar + (float)(br - ar) * tt));
            int g = MathKt.roundToInt((float)((float)ag + (float)(bg - ag) * tt));
            int bl = MathKt.roundToInt((float)((float)ab + (float)(bb - ab) * tt));
            return r << 16 | g << 8 | bl;
        }

        private final int rgba(int rgb, float alpha255) {
            int a = Math.max(0, Math.min(255, MathKt.roundToInt((float)alpha255)));
            if (a <= 0) {
                return 0;
            }
            return a << 24 | rgb & 0xFFFFFF;
        }

        private final float wrap(float value) {
            if (!Float.isFinite(value)) {
                return 0.0f;
            }
            return value - (float)Math.floor(value);
        }

        private final float clamp01(float value) {
            return value < 0.0f ? 0.0f : Math.min(value, 1.0f);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

