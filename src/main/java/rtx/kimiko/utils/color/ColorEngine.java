/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  kotlin.text.StringsKt
 *  net.minecraft.util.math.MathHelper
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.color;

import java.awt.Color;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0015\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\t\u0010\bJ\u001b\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\n\u0010\bJ\u001b\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u000b\u0010\bJ3\u0010\f\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\f\u0010\rJ#\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u000e\u0010\u000fJ#\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0010H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0012\u0010\u0013J+\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0010H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u001d\u0010\u001b\u001a\u00020\u00042\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u001b\u0010\u001cJ+\u0010 \u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u0010H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b \u0010!J#\u0010#\u001a\u00020\"2\u0006\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u0010H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b#\u0010$J;\u0010(\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u0010H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b(\u0010)J\u001f\u0010+\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b+\u0010\u000fJ\u0017\u0010-\u001a\u00020\"2\u0006\u0010,\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b-\u0010.J\u0017\u0010/\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b/\u0010\bJ\u0017\u00100\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b0\u00101J\u0017\u00102\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b2\u0010\bR\u0014\u00103\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b3\u00104R\u0014\u00105\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b5\u00104R\u0014\u00106\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b6\u00104R\u0014\u00107\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b7\u00104R\u0014\u00108\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b8\u00104R\u0014\u00109\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b9\u00104R\u0014\u0010:\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b:\u00104R\u0014\u0010;\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b;\u00104R\u0014\u0010<\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b<\u00104R \u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\"0=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b>\u0010?\u00a8\u0006@"}, d2={"Lrtx/kimiko/utils/color/ColorEngine;", "", "<init>", "()V", "", "color", "Lkotlin/jvm/JvmStatic;", "alpha", "(I)I", "red", "green", "blue", "rgba", "(IIII)I", "withAlpha", "(II)I", "", "factor", "multAlpha", "(IF)I", "from", "to", "progress", "lerpColor", "(IIF)I", "", "value", "hex", "(Ljava/lang/String;)I", "degrees", "saturation", "brightness", "rainbow", "(IFF)I", "", "rainbowTable", "(FF)[I", "periodMs", "index", "opacity", "astolfo", "(IIFFF)I", "offsetDegrees", "phase", "key", "bakeRainbow", "(I)[I", "assumeOpaque", "quantize", "(F)I", "channel", "WHITE", "I", "DARK", "ACCENT", "ACCENT_DEEP", "RED", "GREEN", "YELLOW", "ORANGE", "RAINBOW_STEPS", "Ljava/util/concurrent/ConcurrentHashMap;", "RAINBOW_CACHE", "Ljava/util/concurrent/ConcurrentHashMap;", "rtx.kimiko:kimiko"})
public final class ColorEngine {
    @NotNull
    public static final ColorEngine INSTANCE = new ColorEngine();
    public static final int WHITE = -1;
    public static final int DARK = -15066598;
    public static final int ACCENT = -8231726;
    public static final int ACCENT_DEEP = -10797100;
    public static final int RED = -49088;
    public static final int GREEN = -12517568;
    public static final int YELLOW = -192;
    public static final int ORANGE = -32736;
    public static final int RAINBOW_STEPS = 360;
    @NotNull
    private static final ConcurrentHashMap<Integer, int[]> RAINBOW_CACHE = new ConcurrentHashMap();

    private ColorEngine() {
    }

    @JvmStatic
    public static final int alpha(int color) {
        return color >>> 24 & 0xFF;
    }

    @JvmStatic
    public static final int red(int color) {
        return color >>> 16 & 0xFF;
    }

    @JvmStatic
    public static final int green(int color) {
        return color >>> 8 & 0xFF;
    }

    @JvmStatic
    public static final int blue(int color) {
        return color & 0xFF;
    }

    @JvmStatic
    public static final int rgba(int red, int green, int blue, int alpha) {
        return INSTANCE.channel(alpha) << 24 | INSTANCE.channel(red) << 16 | INSTANCE.channel(green) << 8 | INSTANCE.channel(blue);
    }

    @JvmStatic
    public static final int withAlpha(int color, int alpha) {
        return color & 0xFFFFFF | INSTANCE.channel(alpha) << 24;
    }

    @JvmStatic
    public static final int multAlpha(int color, float factor) {
        float safeFactor = Math.abs(factor) <= Float.MAX_VALUE ? factor : 1.0f;
        return ColorEngine.withAlpha(color, Math.round((float)ColorEngine.alpha(INSTANCE.assumeOpaque(color)) * safeFactor));
    }

    @JvmStatic
    public static final int lerpColor(int from, int to, float progress) {
        float t = Math.abs(progress) <= Float.MAX_VALUE ? RangesKt.coerceIn((float)progress, (float)0.0f, (float)1.0f) : 0.0f;
        int fromAlpha = ColorEngine.alpha(INSTANCE.assumeOpaque(from));
        int toAlpha = ColorEngine.alpha(INSTANCE.assumeOpaque(to));
        return ColorEngine.rgba(Math.round((float)ColorEngine.red(from) + (float)(ColorEngine.red(to) - ColorEngine.red(from)) * t), Math.round((float)ColorEngine.green(from) + (float)(ColorEngine.green(to) - ColorEngine.green(from)) * t), Math.round((float)ColorEngine.blue(from) + (float)(ColorEngine.blue(to) - ColorEngine.blue(from)) * t), Math.round((float)fromAlpha + (float)(toAlpha - fromAlpha) * t));
    }

    @JvmStatic
    public static final int hex(@Nullable String value) {
        String string = value;
        if (string == null || (string = ((Object)StringsKt.trim((CharSequence)string)).toString()) == null || (string = StringsKt.removePrefix((String)string, (CharSequence)"#")) == null) {
            return -1;
        }
        String digits = string;
        Long l = StringsKt.toLongOrNull((String)digits, (int)16);
        if (l == null) {
            return -1;
        }
        long parsed = l;
        return switch (digits.length()) {
            case 6 -> (int)parsed | 0xFF000000;
            case 8 -> (int)parsed;
            default -> -1;
        };
    }

    @JvmStatic
    public static final int rainbow(int degrees, float saturation, float brightness) {
        return ColorEngine.rainbowTable(saturation, brightness)[Math.floorMod(degrees, 360)];
    }

    @JvmStatic
    @NotNull
    public static final int[] rainbowTable(float saturation, float brightness) {
        int[] nArray = RAINBOW_CACHE.computeIfAbsent(INSTANCE.quantize(saturation) << 8 | INSTANCE.quantize(brightness), INSTANCE::bakeRainbow);
        Intrinsics.checkNotNullExpressionValue((Object)nArray, (String)"computeIfAbsent(...)");
        return nArray;
    }

    @JvmStatic
    public static final int astolfo(int periodMs, int index, float saturation, float brightness, float opacity) {
        int hue = INSTANCE.phase(periodMs, index) + index * 90;
        return ColorEngine.withAlpha(ColorEngine.rainbow(hue, saturation, brightness), Math.round(opacity * 255.0f));
    }

    private final int phase(int periodMs, int offsetDegrees) {
        return (int)((System.currentTimeMillis() / (long)Math.max(1, periodMs) + (long)offsetDegrees) % (long)360);
    }

    private final int[] bakeRainbow(int key) {
        float saturation = (float)(key >>> 8) / 255.0f;
        float brightness = (float)(key & 0xFF) / 255.0f;
        int n = 0;
        int[] nArray = new int[360];
        while (n < 360) {
            int n2 = n++;
            nArray[n2] = Color.HSBtoRGB((float)n2 / 360.0f, saturation, brightness) & 0xFFFFFF;
        }
        return nArray;
    }

    private final int assumeOpaque(int color) {
        return ColorEngine.alpha(color) == 0 && (color & 0xFFFFFF) != 0 ? color | 0xFF000000 : color;
    }

    private final int quantize(float value) {
        return Math.round(MathHelper.clamp((float)value, (float)0.0f, (float)1.0f) * 255.0f);
    }

    private final int channel(int value) {
        return MathHelper.clamp((int)value, (int)0, (int)255);
    }

    private static final int[] rainbowTable$lambda$0(Function1 $tmp0, Object p0) {
        return (int[])$tmp0.invoke(p0);
    }

    public static final /* synthetic */ int[] access$bakeRainbow(ColorEngine $this, int key) {
        return $this.bakeRainbow(key);
    }
}

