/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.math.MathHelper
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Visuals.particles;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.color.ColorEngine;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J;\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\f\u0010\rJ3\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\u0010\u0010\u0011J+\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0012H\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00168\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00168\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0018\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/ParticleColors;", "", "<init>", "()V", "", "speed", "index", "", "saturation", "brightness", "alpha", "Lkotlin/jvm/JvmStatic;", "rainbow", "(IIFFF)I", "first", "second", "fade", "(IIII)I", "", "palette", "paletteFade", "(II[I)I", "", "CLIENT", "Ljava/lang/String;", "RAINBOW", "CUSTOM", "rtx.kimiko:kimiko"})
public final class ParticleColors {
    @NotNull
    public static final ParticleColors INSTANCE = new ParticleColors();
    @NotNull
    public static final String CLIENT = "Клиент";
    @NotNull
    public static final String RAINBOW = "Радуга";
    @NotNull
    public static final String CUSTOM = "Свой";

    private ParticleColors() {
    }

    @JvmStatic
    public static final int rainbow(int speed, int index, float saturation, float brightness, float alpha) {
        int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
        int rgb = ColorEngine.rainbow(angle, saturation, brightness);
        return ColorEngine.rgba(rgb >>> 16 & 0xFF, rgb >>> 8 & 0xFF, rgb & 0xFF, Math.round(MathHelper.clamp((float)alpha, (float)0.0f, (float)1.0f) * 255.0f));
    }

    @JvmStatic
    public static final int fade(int speed, int index, int first, int second) {
        int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
        angle = angle >= 180 ? 360 - angle : angle;
        return ColorEngine.lerpColor(first, second, (float)angle / 180.0f);
    }

    @JvmStatic
    public static final int paletteFade(int speed, int index, @NotNull int[] palette) {
        Intrinsics.checkNotNullParameter((Object)palette, (String)"palette");
        int n = palette.length;
        int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
        float f = (float)angle / 360.0f * (float)n;
        int i = (int)f % n;
        int j = (i + 1) % n;
        int a = palette[i] | 0xFF000000;
        int b = palette[j] | 0xFF000000;
        return ColorEngine.lerpColor(a, b, f - (float)Math.floor(f)) | 0xFF000000;
    }
}

