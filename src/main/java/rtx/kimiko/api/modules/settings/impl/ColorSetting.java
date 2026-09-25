/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.settings.impl;

import java.awt.Color;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.settings.Setting;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u001b\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006B#\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\u0005\u0010\tJ\u0017\u0010\f\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u000e\u0010\rJ'\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u001b\u001a\u00020\u00002\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u001d\u0010\u001d\u001a\u00020\u00002\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0016\u00a2\u0006\u0004\b\u001d\u0010\u001cJ\r\u0010\u001e\u001a\u00020\u000f\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\r\u0010 \u001a\u00020\u000f\u00a2\u0006\u0004\b \u0010\u001fJ\r\u0010!\u001a\u00020\u000f\u00a2\u0006\u0004\b!\u0010\u001fJ\r\u0010\"\u001a\u00020\u000f\u00a2\u0006\u0004\b\"\u0010\u001fJ\r\u0010#\u001a\u00020\n\u00a2\u0006\u0004\b#\u0010$J\u000f\u0010%\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b%\u0010$J\u000f\u0010&\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b&\u0010$J\u000f\u0010'\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b'\u0010$J\u000f\u0010(\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b(\u0010$R\u0016\u0010)\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010+\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u0010*R\u0016\u0010,\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010*R\u0016\u0010-\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b-\u0010*R\u0016\u0010.\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b.\u0010/R\u0016\u00100\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0016\u00102\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u0010/R\u0016\u00103\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u00101\u00a8\u00064"}, d2={"Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "Lrtx/kimiko/api/modules/settings/Setting;", "", "name", "description", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "Ljava/awt/Color;", "color", "(Ljava/lang/String;Ljava/lang/String;Ljava/awt/Color;)V", "", "argb", "value", "(I)Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "setColor", "", "h", "s", "b", "setHSB", "(FFF)Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "a", "setAlpha", "(F)Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "Ljava/util/function/Supplier;", "", "condition", "visible", "(Ljava/util/function/Supplier;)Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "visibleWhen", "getHue", "()F", "getSaturation", "getBrightness", "getAlpha", "getDefaultColor", "()I", "getColor", "getColorOpaque", "getValue", "rgb", "hue", "F", "saturation", "brightness", "alpha", "defaultColor", "I", "defaultCaptured", "Z", "cachedRgb", "rgbCacheValid", "rtx.kimiko:kimiko"})
public class ColorSetting
extends Setting {
    private float hue;
    private float saturation;
    private float brightness;
    private float alpha;
    private int defaultColor;
    private boolean defaultCaptured;
    private int cachedRgb;
    private boolean rgbCacheValid;

    public ColorSetting(@NotNull String name, @NotNull String description) {
        super(name, description);
        this.saturation = 1.0f;
        this.brightness = 1.0f;
        this.alpha = 1.0f;
        this.defaultColor = -1;
    }

    public ColorSetting(@NotNull String name, @NotNull String description, @Nullable Color color) {
        this(name, description);
        if (color != null) {
            this.setColor(color.getRGB());
        }
    }

    @NotNull
    public ColorSetting value(int argb) {
        this.setColor(argb);
        return this;
    }

    @NotNull
    public ColorSetting setColor(int argb) {
        int old = this.getColor();
        int a = argb >>> 24 & 0xFF;
        int r = argb >>> 16 & 0xFF;
        int g = argb >>> 8 & 0xFF;
        int b = argb & 0xFF;
        float[] hsb = Color.RGBtoHSB(r, g, b, null);
        this.hue = hsb[0];
        this.saturation = hsb[1];
        this.brightness = hsb[2];
        this.alpha = (float)a / 255.0f;
        this.rgbCacheValid = false;
        if (!this.defaultCaptured) {
            this.defaultColor = argb;
            this.defaultCaptured = true;
        }
        if (old != this.getColor()) {
            this.notifyChanged();
        }
        return this;
    }

    @NotNull
    public ColorSetting setHSB(float h, float s, float b) {
        int old = this.getColor();
        this.hue = h;
        this.saturation = s;
        this.brightness = b;
        this.rgbCacheValid = false;
        if (old != this.getColor()) {
            this.notifyChanged();
        }
        return this;
    }

    @NotNull
    public ColorSetting setAlpha(float a) {
        int old = this.getColor();
        this.alpha = RangesKt.coerceIn((float)a, (float)0.0f, (float)1.0f);
        if (old != this.getColor()) {
            this.notifyChanged();
        }
        return this;
    }

    @NotNull
    public ColorSetting visible(@NotNull Supplier<Boolean> condition) {
        Intrinsics.checkNotNullParameter(condition, (String)"condition");
        this.setVisibilityCondition(condition);
        return this;
    }

    @NotNull
    public ColorSetting visibleWhen(@NotNull Supplier<Boolean> condition) {
        Intrinsics.checkNotNullParameter(condition, (String)"condition");
        return this.visible(condition);
    }

    public final float getHue() {
        return this.hue;
    }

    public final float getSaturation() {
        return this.saturation;
    }

    public final float getBrightness() {
        return this.brightness;
    }

    public final float getAlpha() {
        return this.alpha;
    }

    public final int getDefaultColor() {
        return this.defaultColor;
    }

    public int getColor() {
        return Math.round(this.alpha * 255.0f) << 24 | this.rgb();
    }

    public int getColorOpaque() {
        return this.rgb() | 0xFF000000;
    }

    public int getValue() {
        return this.getColor();
    }

    private final int rgb() {
        if (!this.rgbCacheValid) {
            this.cachedRgb = Color.HSBtoRGB(this.hue, this.saturation, this.brightness) & 0xFFFFFF;
            this.rgbCacheValid = true;
        }
        return this.cachedRgb;
    }
}

