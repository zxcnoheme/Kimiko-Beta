/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 */
package rtx.kimiko.api.ui.theme;

import java.awt.Color;
import kotlin.Metadata;

@Metadata(mv={2, 4, 0}, k=2, xi=48, d1={"\u0000\u0016\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0014\n\u0002\b\n\u001a\u0017\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u001a\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\b\u001a/\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0000H\u0002\u00a2\u0006\u0004\b\f\u0010\r\u001a'\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0010"}, d2={"", "v", "themeClamp01", "(F)F", "", "rgb", "", "themeToHsb", "(I)[F", "satMul", "valMul", "valAdd", "themeLighten", "(IFFF)I", "themeDarken", "(IFF)I", "rtx.kimiko:kimiko"})
public final class ThemeKt {
    private static final float themeClamp01(float v) {
        return v < 0.0f ? 0.0f : (v > 1.0f ? 1.0f : v);
    }

    private static final float[] themeToHsb(int rgb) {
        float[] hsb = new float[3];
        Color.RGBtoHSB(rgb >> 16 & 0xFF, rgb >> 8 & 0xFF, rgb & 0xFF, hsb);
        return hsb;
    }

    private static final int themeLighten(int rgb, float satMul, float valMul, float valAdd) {
        float[] hsb = ThemeKt.themeToHsb(rgb);
        return Color.HSBtoRGB(hsb[0], ThemeKt.themeClamp01(hsb[1] * satMul), ThemeKt.themeClamp01(hsb[2] * valMul + valAdd)) & 0xFFFFFF;
    }

    private static final int themeDarken(int rgb, float satMul, float valMul) {
        float[] hsb = ThemeKt.themeToHsb(rgb);
        return Color.HSBtoRGB(hsb[0], ThemeKt.themeClamp01(hsb[1] * satMul), hsb[2] * valMul) & 0xFFFFFF;
    }

    public static final /* synthetic */ int access$themeLighten(int rgb, float satMul, float valMul, float valAdd) {
        return ThemeKt.themeLighten(rgb, satMul, valMul, valAdd);
    }

    public static final /* synthetic */ int access$themeDarken(int rgb, float satMul, float valMul) {
        return ThemeKt.themeDarken(rgb, satMul, valMul);
    }
}

