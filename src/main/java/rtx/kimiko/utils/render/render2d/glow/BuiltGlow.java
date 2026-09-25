/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.glow;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.render2d.glow.GlowRenderer;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u0000 m2\u00020\u0001:\u0002nmB\u00c9\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u000b\u0012\u0006\u0010\u0011\u001a\u00020\u0002\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u0014\u001a\u00020\u000b\u0012\u0006\u0010\u0015\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\u0006\u0010\u0017\u001a\u00020\u000b\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u001c\u00a2\u0006\u0004\b\u001e\u0010\u001fBA\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010 \u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001e\u0010!BS\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\b\u0010 \u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001e\u0010\"J\u001d\u0010%\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u0002\u00a2\u0006\u0004\b%\u0010&J\r\u0010'\u001a\u00020\u0002\u00a2\u0006\u0004\b'\u0010(J\u001d\u0010)\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0002\u00a2\u0006\u0004\b)\u0010*J\u001f\u0010,\u001a\u00020\u00002\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010+\u001a\u00020\u000b\u00a2\u0006\u0004\b,\u0010-J\u001d\u00100\u001a\u00020\u00002\u0006\u0010.\u001a\u00020\u001c2\u0006\u0010/\u001a\u00020\u001c\u00a2\u0006\u0004\b0\u00101J\r\u00102\u001a\u00020\u0000\u00a2\u0006\u0004\b2\u00103J\u0015\u00104\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u000b\u00a2\u0006\u0004\b4\u00105J/\u0010:\u001a\u00020\u00002\b\u00107\u001a\u0004\u0018\u0001062\u0006\u0010$\u001a\u00020\u00022\u0006\u00108\u001a\u00020\u00022\u0006\u00109\u001a\u00020\u0002\u00a2\u0006\u0004\b:\u0010;J\u0017\u0010?\u001a\u00020>2\b\u0010=\u001a\u0004\u0018\u00010<\u00a2\u0006\u0004\b?\u0010@J\r\u0010A\u001a\u00020\u001c\u00a2\u0006\u0004\bA\u0010BJ\u0010\u0010C\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bC\u0010(J\u0010\u0010D\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bD\u0010(J\u0010\u0010E\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bE\u0010(J\u0010\u0010F\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bF\u0010(J\u0010\u0010G\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bG\u0010(J\u0010\u0010H\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bH\u0010(J\u0010\u0010I\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bI\u0010(J\u0010\u0010J\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bJ\u0010(J\u0010\u0010K\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\bK\u0010LJ\u0010\u0010M\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bM\u0010(J\u0010\u0010N\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bN\u0010(J\u0010\u0010O\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bO\u0010(J\u0010\u0010P\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\bP\u0010LJ\u0010\u0010Q\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bQ\u0010(J\u0012\u0010R\u001a\u0004\u0018\u00010\u0012H\u00c6\u0003\u00a2\u0006\u0004\bR\u0010SJ\u0010\u0010T\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\bT\u0010LJ\u0010\u0010U\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bU\u0010(J\u0010\u0010V\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bV\u0010(J\u0010\u0010W\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\bW\u0010LJ\u0012\u0010X\u001a\u0004\u0018\u00010\u0018H\u00c6\u0003\u00a2\u0006\u0004\bX\u0010YJ\u0010\u0010Z\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bZ\u0010(J\u0010\u0010[\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b[\u0010(J\u0010\u0010\\\u001a\u00020\u001cH\u00c6\u0003\u00a2\u0006\u0004\b\\\u0010BJ\u00fa\u0001\u0010]\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u000b2\b\b\u0002\u0010\u0011\u001a\u00020\u00022\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u00022\b\b\u0002\u0010\u0016\u001a\u00020\u00022\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u00022\b\b\u0002\u0010\u001b\u001a\u00020\u00022\b\b\u0002\u0010\u001d\u001a\u00020\u001cH\u00c6\u0001\u00a2\u0006\u0004\b]\u0010^J\u001b\u0010`\u001a\u00020\u001c2\b\u0010_\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b`\u0010aJ\u0011\u0010b\u001a\u00020\u000bH\u00d6\u0081\u0004\u00a2\u0006\u0004\bb\u0010LJ\u0011\u0010d\u001a\u00020cH\u00d6\u0081\u0004\u00a2\u0006\u0004\bd\u0010eR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010h\u001a\u0004\b\u0003\u0010(R%\u0010\u0004\u001a\u00020\u00028\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010h\u001a\u0004\b\u0004\u0010(R%\u0010\u0005\u001a\u00020\u00028\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010h\u001a\u0004\b\u0005\u0010(R%\u0010\u0006\u001a\u00020\u00028\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010h\u001a\u0004\b\u0006\u0010(R%\u0010\u0007\u001a\u00020\u00028\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010h\u001a\u0004\b\u0007\u0010(R%\u0010\b\u001a\u00020\u00028\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010h\u001a\u0004\b\b\u0010(R%\u0010\t\u001a\u00020\u00028\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010h\u001a\u0004\b\t\u0010(R%\u0010\n\u001a\u00020\u00028\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010h\u001a\u0004\b\n\u0010(R%\u0010\f\u001a\u00020\u000b8\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010i\u001a\u0004\b\f\u0010LR%\u0010\r\u001a\u00020\u00028\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u0010h\u001a\u0004\b\r\u0010(R%\u0010\u000e\u001a\u00020\u00028\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u0010h\u001a\u0004\b\u000e\u0010(R%\u0010\u000f\u001a\u00020\u00028\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u0010h\u001a\u0004\b\u000f\u0010(R%\u0010\u0010\u001a\u00020\u000b8\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\u0010\u00a2\u0006\f\n\u0004\b\u0010\u0010i\u001a\u0004\b\u0010\u0010LR%\u0010\u0011\u001a\u00020\u00028\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\u0011\u00a2\u0006\f\n\u0004\b\u0011\u0010h\u001a\u0004\b\u0011\u0010(R'\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\u0013\u00a2\u0006\f\n\u0004\b\u0013\u0010j\u001a\u0004\b\u0013\u0010SR%\u0010\u0014\u001a\u00020\u000b8\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\u0014\u00a2\u0006\f\n\u0004\b\u0014\u0010i\u001a\u0004\b\u0014\u0010LR%\u0010\u0015\u001a\u00020\u00028\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\u0015\u00a2\u0006\f\n\u0004\b\u0015\u0010h\u001a\u0004\b\u0015\u0010(R%\u0010\u0016\u001a\u00020\u00028\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\u0016\u00a2\u0006\f\n\u0004\b\u0016\u0010h\u001a\u0004\b\u0016\u0010(R%\u0010\u0017\u001a\u00020\u000b8\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\u0017\u00a2\u0006\f\n\u0004\b\u0017\u0010i\u001a\u0004\b\u0017\u0010LR'\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\u0019\u00a2\u0006\f\n\u0004\b\u0019\u0010k\u001a\u0004\b\u0019\u0010YR%\u0010\u001a\u001a\u00020\u00028\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\u001a\u00a2\u0006\f\n\u0004\b\u001a\u0010h\u001a\u0004\b\u001a\u0010(R%\u0010\u001b\u001a\u00020\u00028\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\u001b\u00a2\u0006\f\n\u0004\b\u001b\u0010h\u001a\u0004\b\u001b\u0010(R%\u0010\u001d\u001a\u00020\u001c8\u0007z\f\bf\u0012\b\bg\u0012\u0004\b\b(\u001d\u00a2\u0006\f\n\u0004\b\u001d\u0010l\u001a\u0004\b\u001d\u0010B\u00a8\u0006o"}, d2={"Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow;", "", "", "x", "y", "width", "height", "radiusTopLeft", "radiusTopRight", "radiusBottomRight", "radiusBottomLeft", "", "color", "intensity", "blurRadius", "alpha", "secondColor", "colorOffset", "", "spans", "spanCount", "leftAligned", "bottomAnchored", "splitIndex", "Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow$GlowPalette;", "palette", "waveFreq", "wavePhase", "", "waveEnabled", "<init>", "(FFFFFFFFIFFFIF[FIFFILrtx/kimiko/utils/render/render2d/glow/BuiltGlow$GlowPalette;FFZ)V", "radius", "(FFFFFIF)V", "(FFFF[FIFFF)V", "freq", "phase", "withRowWave", "(FF)Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow;", "effectivePad", "()F", "withSecondColor", "(IF)Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow;", "count", "withSpans", "([FI)Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow;", "isLeftAligned", "isBottomAnchored", "withAlignment", "(ZZ)Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow;", "withBoxesMode", "()Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow;", "withSplitIndex", "(I)Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow;", "", "colors", "styleId", "closed", "withPalette", "([IFFF)Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow;", "Lnet/minecraft/DrawContext;", "graphics", "", "render", "(Lnet/minecraft/DrawContext;)V", "visible", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "()I", "component10", "component11", "component12", "component13", "component14", "component15", "()[F", "component16", "component17", "component18", "component19", "component20", "()Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow$GlowPalette;", "component21", "component22", "component23", "copy", "(FFFFFFFFIFFFIF[FIFFILrtx/kimiko/utils/render/render2d/glow/BuiltGlow$GlowPalette;FFZ)Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "I", "[F", "Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow$GlowPalette;", "Z", "Companion", "GlowPalette", "rtx.kimiko:kimiko"})
public final class BuiltGlow {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final float x;
    private final float y;
    private final float width;
    private final float height;
    private final float radiusTopLeft;
    private final float radiusTopRight;
    private final float radiusBottomRight;
    private final float radiusBottomLeft;
    private final int color;
    private final float intensity;
    private final float blurRadius;
    private final float alpha;
    private final int secondColor;
    private final float colorOffset;
    @Nullable
    private final float[] spans;
    private final int spanCount;
    private final float leftAligned;
    private final float bottomAnchored;
    private final int splitIndex;
    @Nullable
    private final GlowPalette palette;
    private final float waveFreq;
    private final float wavePhase;
    private final boolean waveEnabled;
    private static final float DEFAULT_BLUR_RADIUS = 16.0f;
    private static final float DEFAULT_INTENSITY = 1.0f;
    private static final float MIN_PAD = 6.0f;

    public BuiltGlow(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, int color, float intensity, float blurRadius, float alpha, int secondColor, float colorOffset, @Nullable float[] spans, int spanCount, float leftAligned, float bottomAnchored, int splitIndex, @Nullable GlowPalette palette, float waveFreq, float wavePhase, boolean waveEnabled) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radiusTopLeft = radiusTopLeft;
        this.radiusTopRight = radiusTopRight;
        this.radiusBottomRight = radiusBottomRight;
        this.radiusBottomLeft = radiusBottomLeft;
        this.color = color;
        this.intensity = intensity;
        this.blurRadius = blurRadius;
        this.alpha = alpha;
        this.secondColor = secondColor;
        this.colorOffset = colorOffset;
        this.spans = spans;
        this.spanCount = spanCount;
        this.leftAligned = leftAligned;
        this.bottomAnchored = bottomAnchored;
        this.splitIndex = splitIndex;
        this.palette = palette;
        this.waveFreq = waveFreq;
        this.wavePhase = wavePhase;
        this.waveEnabled = waveEnabled;
    }

    public /* synthetic */ BuiltGlow(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n, float f9, float f10, float f11, int n2, float f12, float[] fArray, int n3, float f13, float f14, int n4, GlowPalette glowPalette, float f15, float f16, boolean bl, int n5, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, f5, f6, f7, f8, n, f9, f10, f11, n2, f12, fArray, n3, f13, f14, n4, glowPalette, (n5 & 0x100000) != 0 ? 0.0f : f15, (n5 & 0x200000) != 0 ? 0.0f : f16, (n5 & 0x400000) == 0 && bl);
    }

    @JvmName(name="x")
    public final float x() {
        return this.x;
    }

    @JvmName(name="y")
    public final float y() {
        return this.y;
    }

    @JvmName(name="width")
    public final float width() {
        return this.width;
    }

    @JvmName(name="height")
    public final float height() {
        return this.height;
    }

    @JvmName(name="radiusTopLeft")
    public final float radiusTopLeft() {
        return this.radiusTopLeft;
    }

    @JvmName(name="radiusTopRight")
    public final float radiusTopRight() {
        return this.radiusTopRight;
    }

    @JvmName(name="radiusBottomRight")
    public final float radiusBottomRight() {
        return this.radiusBottomRight;
    }

    @JvmName(name="radiusBottomLeft")
    public final float radiusBottomLeft() {
        return this.radiusBottomLeft;
    }

    @JvmName(name="color")
    public final int color() {
        return this.color;
    }

    @JvmName(name="intensity")
    public final float intensity() {
        return this.intensity;
    }

    @JvmName(name="blurRadius")
    public final float blurRadius() {
        return this.blurRadius;
    }

    @JvmName(name="alpha")
    public final float alpha() {
        return this.alpha;
    }

    @JvmName(name="secondColor")
    public final int secondColor() {
        return this.secondColor;
    }

    @JvmName(name="colorOffset")
    public final float colorOffset() {
        return this.colorOffset;
    }

    @JvmName(name="spans")
    @Nullable
    public final float[] spans() {
        return this.spans;
    }

    @JvmName(name="spanCount")
    public final int spanCount() {
        return this.spanCount;
    }

    @JvmName(name="leftAligned")
    public final float leftAligned() {
        return this.leftAligned;
    }

    @JvmName(name="bottomAnchored")
    public final float bottomAnchored() {
        return this.bottomAnchored;
    }

    @JvmName(name="splitIndex")
    public final int splitIndex() {
        return this.splitIndex;
    }

    @JvmName(name="palette")
    @Nullable
    public final GlowPalette palette() {
        return this.palette;
    }

    @JvmName(name="waveFreq")
    public final float waveFreq() {
        return this.waveFreq;
    }

    @JvmName(name="wavePhase")
    public final float wavePhase() {
        return this.wavePhase;
    }

    @JvmName(name="waveEnabled")
    public final boolean waveEnabled() {
        return this.waveEnabled;
    }

    @NotNull
    public final BuiltGlow withRowWave(float freq, float phase) {
        return BuiltGlow.copy$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0.0f, 0, 0.0f, null, 0, 0.0f, 0.0f, 0, null, freq, phase, freq > 0.0f, 1048575, null);
    }

    public final float effectivePad() {
        return Math.max(this.blurRadius, 6.0f);
    }

    public BuiltGlow(float x, float y, float width, float height, float radius, int color, float alpha) {
        this(x, y, width, height, radius, radius, radius, radius, color, 1.0f, 16.0f, alpha, color, 0.0f, null, 0, 1.0f, 0.0f, 0, null, 0.0f, 0.0f, false, 0x700000, null);
    }

    public BuiltGlow(float x, float y, float width, float height, @Nullable float[] radius, int color, float intensity, float blurRadius, float alpha) {
        this(x, y, width, height, BuiltGlow.Companion.radiusValue(radius, 0), BuiltGlow.Companion.radiusValue(radius, 1), BuiltGlow.Companion.radiusValue(radius, 2), BuiltGlow.Companion.radiusValue(radius, 3), color, intensity, blurRadius, alpha, color, 0.0f, null, 0, 1.0f, 0.0f, 0, null, 0.0f, 0.0f, false, 0x700000, null);
    }

    @NotNull
    public final BuiltGlow withSecondColor(int secondColor, float colorOffset) {
        return new BuiltGlow(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.color, this.intensity, this.blurRadius, this.alpha, secondColor, colorOffset, this.spans, this.spanCount, this.leftAligned, this.bottomAnchored, this.splitIndex, this.palette, 0.0f, 0.0f, false, 0x700000, null);
    }

    @NotNull
    public final BuiltGlow withSpans(@Nullable float[] spans, int count) {
        return new BuiltGlow(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.color, this.intensity, this.blurRadius, this.alpha, this.secondColor, this.colorOffset, spans, count, this.leftAligned, this.bottomAnchored, this.splitIndex, this.palette, 0.0f, 0.0f, false, 0x700000, null);
    }

    @NotNull
    public final BuiltGlow withAlignment(boolean isLeftAligned, boolean isBottomAnchored) {
        return new BuiltGlow(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.color, this.intensity, this.blurRadius, this.alpha, this.secondColor, this.colorOffset, this.spans, this.spanCount, isLeftAligned ? 1.0f : 0.0f, isBottomAnchored ? 1.0f : 0.0f, this.splitIndex, this.palette, 0.0f, 0.0f, false, 0x700000, null);
    }

    @NotNull
    public final BuiltGlow withBoxesMode() {
        return new BuiltGlow(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.color, this.intensity, this.blurRadius, this.alpha, this.secondColor, this.colorOffset, this.spans, this.spanCount, -1.0f, this.bottomAnchored, this.splitIndex, this.palette, 0.0f, 0.0f, false, 0x700000, null);
    }

    @NotNull
    public final BuiltGlow withSplitIndex(int splitIndex) {
        return new BuiltGlow(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.color, this.intensity, this.blurRadius, this.alpha, this.secondColor, this.colorOffset, this.spans, this.spanCount, this.leftAligned, this.bottomAnchored, splitIndex, this.palette, 0.0f, 0.0f, false, 0x700000, null);
    }

    @NotNull
    public final BuiltGlow withPalette(@Nullable int[] colors, float phase, float styleId, float closed) {
        return new BuiltGlow(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.color, this.intensity, this.blurRadius, this.alpha, this.secondColor, this.colorOffset, this.spans, this.spanCount, this.leftAligned, this.bottomAnchored, this.splitIndex, colors == null || colors.length == 0 ? null : new GlowPalette(colors, phase, styleId, closed), 0.0f, 0.0f, false, 0x700000, null);
    }

    public final void render(@Nullable DrawContext graphics) {
        GlowRenderer.Companion.getInstance().draw(graphics, this);
    }

    public final boolean visible() {
        return this.width > 0.0f && this.height > 0.0f && this.alpha > 0.0f && this.intensity > 0.0f && this.color >>> 24 != 0;
    }

    public final float component1() {
        return this.x;
    }

    public final float component2() {
        return this.y;
    }

    public final float component3() {
        return this.width;
    }

    public final float component4() {
        return this.height;
    }

    public final float component5() {
        return this.radiusTopLeft;
    }

    public final float component6() {
        return this.radiusTopRight;
    }

    public final float component7() {
        return this.radiusBottomRight;
    }

    public final float component8() {
        return this.radiusBottomLeft;
    }

    public final int component9() {
        return this.color;
    }

    public final float component10() {
        return this.intensity;
    }

    public final float component11() {
        return this.blurRadius;
    }

    public final float component12() {
        return this.alpha;
    }

    public final int component13() {
        return this.secondColor;
    }

    public final float component14() {
        return this.colorOffset;
    }

    @Nullable
    public final float[] component15() {
        return this.spans;
    }

    public final int component16() {
        return this.spanCount;
    }

    public final float component17() {
        return this.leftAligned;
    }

    public final float component18() {
        return this.bottomAnchored;
    }

    public final int component19() {
        return this.splitIndex;
    }

    @Nullable
    public final GlowPalette component20() {
        return this.palette;
    }

    public final float component21() {
        return this.waveFreq;
    }

    public final float component22() {
        return this.wavePhase;
    }

    public final boolean component23() {
        return this.waveEnabled;
    }

    @NotNull
    public final BuiltGlow copy(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, int color, float intensity, float blurRadius, float alpha, int secondColor, float colorOffset, @Nullable float[] spans, int spanCount, float leftAligned, float bottomAnchored, int splitIndex, @Nullable GlowPalette palette, float waveFreq, float wavePhase, boolean waveEnabled) {
        return new BuiltGlow(x, y, width, height, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, color, intensity, blurRadius, alpha, secondColor, colorOffset, spans, spanCount, leftAligned, bottomAnchored, splitIndex, palette, waveFreq, wavePhase, waveEnabled);
    }

    public static /* synthetic */ BuiltGlow copy$default(BuiltGlow builtGlow, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n, float f9, float f10, float f11, int n2, float f12, float[] fArray, int n3, float f13, float f14, int n4, GlowPalette glowPalette, float f15, float f16, boolean bl, int n5, Object object) {
        if ((n5 & 1) != 0) {
            f = builtGlow.x;
        }
        if ((n5 & 2) != 0) {
            f2 = builtGlow.y;
        }
        if ((n5 & 4) != 0) {
            f3 = builtGlow.width;
        }
        if ((n5 & 8) != 0) {
            f4 = builtGlow.height;
        }
        if ((n5 & 0x10) != 0) {
            f5 = builtGlow.radiusTopLeft;
        }
        if ((n5 & 0x20) != 0) {
            f6 = builtGlow.radiusTopRight;
        }
        if ((n5 & 0x40) != 0) {
            f7 = builtGlow.radiusBottomRight;
        }
        if ((n5 & 0x80) != 0) {
            f8 = builtGlow.radiusBottomLeft;
        }
        if ((n5 & 0x100) != 0) {
            n = builtGlow.color;
        }
        if ((n5 & 0x200) != 0) {
            f9 = builtGlow.intensity;
        }
        if ((n5 & 0x400) != 0) {
            f10 = builtGlow.blurRadius;
        }
        if ((n5 & 0x800) != 0) {
            f11 = builtGlow.alpha;
        }
        if ((n5 & 0x1000) != 0) {
            n2 = builtGlow.secondColor;
        }
        if ((n5 & 0x2000) != 0) {
            f12 = builtGlow.colorOffset;
        }
        if ((n5 & 0x4000) != 0) {
            fArray = builtGlow.spans;
        }
        if ((n5 & 0x8000) != 0) {
            n3 = builtGlow.spanCount;
        }
        if ((n5 & 0x10000) != 0) {
            f13 = builtGlow.leftAligned;
        }
        if ((n5 & 0x20000) != 0) {
            f14 = builtGlow.bottomAnchored;
        }
        if ((n5 & 0x40000) != 0) {
            n4 = builtGlow.splitIndex;
        }
        if ((n5 & 0x80000) != 0) {
            glowPalette = builtGlow.palette;
        }
        if ((n5 & 0x100000) != 0) {
            f15 = builtGlow.waveFreq;
        }
        if ((n5 & 0x200000) != 0) {
            f16 = builtGlow.wavePhase;
        }
        if ((n5 & 0x400000) != 0) {
            bl = builtGlow.waveEnabled;
        }
        return builtGlow.copy(f, f2, f3, f4, f5, f6, f7, f8, n, f9, f10, f11, n2, f12, fArray, n3, f13, f14, n4, glowPalette, f15, f16, bl);
    }

    @NotNull
    public String toString() {
        return "BuiltGlow(x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", radiusTopLeft=" + this.radiusTopLeft + ", radiusTopRight=" + this.radiusTopRight + ", radiusBottomRight=" + this.radiusBottomRight + ", radiusBottomLeft=" + this.radiusBottomLeft + ", color=" + this.color + ", intensity=" + this.intensity + ", blurRadius=" + this.blurRadius + ", alpha=" + this.alpha + ", secondColor=" + this.secondColor + ", colorOffset=" + this.colorOffset + ", spans=" + Arrays.toString(this.spans) + ", spanCount=" + this.spanCount + ", leftAligned=" + this.leftAligned + ", bottomAnchored=" + this.bottomAnchored + ", splitIndex=" + this.splitIndex + ", palette=" + this.palette + ", waveFreq=" + this.waveFreq + ", wavePhase=" + this.wavePhase + ", waveEnabled=" + this.waveEnabled + ")";
    }

    public int hashCode() {
        int result = Float.hashCode(this.x);
        result = result * 31 + Float.hashCode(this.y);
        result = result * 31 + Float.hashCode(this.width);
        result = result * 31 + Float.hashCode(this.height);
        result = result * 31 + Float.hashCode(this.radiusTopLeft);
        result = result * 31 + Float.hashCode(this.radiusTopRight);
        result = result * 31 + Float.hashCode(this.radiusBottomRight);
        result = result * 31 + Float.hashCode(this.radiusBottomLeft);
        result = result * 31 + Integer.hashCode(this.color);
        result = result * 31 + Float.hashCode(this.intensity);
        result = result * 31 + Float.hashCode(this.blurRadius);
        result = result * 31 + Float.hashCode(this.alpha);
        result = result * 31 + Integer.hashCode(this.secondColor);
        result = result * 31 + Float.hashCode(this.colorOffset);
        result = result * 31 + (this.spans == null ? 0 : Arrays.hashCode(this.spans));
        result = result * 31 + Integer.hashCode(this.spanCount);
        result = result * 31 + Float.hashCode(this.leftAligned);
        result = result * 31 + Float.hashCode(this.bottomAnchored);
        result = result * 31 + Integer.hashCode(this.splitIndex);
        result = result * 31 + (this.palette == null ? 0 : this.palette.hashCode());
        result = result * 31 + Float.hashCode(this.waveFreq);
        result = result * 31 + Float.hashCode(this.wavePhase);
        result = result * 31 + Boolean.hashCode(this.waveEnabled);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltGlow)) {
            return false;
        }
        BuiltGlow builtGlow = (BuiltGlow)other;
        if (Float.compare(this.x, builtGlow.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, builtGlow.y) != 0) {
            return false;
        }
        if (Float.compare(this.width, builtGlow.width) != 0) {
            return false;
        }
        if (Float.compare(this.height, builtGlow.height) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopLeft, builtGlow.radiusTopLeft) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopRight, builtGlow.radiusTopRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomRight, builtGlow.radiusBottomRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomLeft, builtGlow.radiusBottomLeft) != 0) {
            return false;
        }
        if (this.color != builtGlow.color) {
            return false;
        }
        if (Float.compare(this.intensity, builtGlow.intensity) != 0) {
            return false;
        }
        if (Float.compare(this.blurRadius, builtGlow.blurRadius) != 0) {
            return false;
        }
        if (Float.compare(this.alpha, builtGlow.alpha) != 0) {
            return false;
        }
        if (this.secondColor != builtGlow.secondColor) {
            return false;
        }
        if (Float.compare(this.colorOffset, builtGlow.colorOffset) != 0) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.spans, (Object)builtGlow.spans)) {
            return false;
        }
        if (this.spanCount != builtGlow.spanCount) {
            return false;
        }
        if (Float.compare(this.leftAligned, builtGlow.leftAligned) != 0) {
            return false;
        }
        if (Float.compare(this.bottomAnchored, builtGlow.bottomAnchored) != 0) {
            return false;
        }
        if (this.splitIndex != builtGlow.splitIndex) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.palette, (Object)builtGlow.palette)) {
            return false;
        }
        if (Float.compare(this.waveFreq, builtGlow.waveFreq) != 0) {
            return false;
        }
        if (Float.compare(this.wavePhase, builtGlow.wavePhase) != 0) {
            return false;
        }
        return this.waveEnabled == builtGlow.waveEnabled;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J!\u0010\t\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\fR\u0014\u0010\u000e\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\f\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow.Companion;", "", "<init>", "()V", "", "radius", "", "index", "", "radiusValue", "([FI)F", "DEFAULT_BLUR_RADIUS", "F", "DEFAULT_INTENSITY", "MIN_PAD", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float radiusValue(float[] radius, int index) {
            if (radius == null || index < 0 || index >= radius.length) {
                return 0.0f;
            }
            return radius[index];
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\rJ8\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u0017\u001a\u00020\u0016H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0011\u0010\u001a\u001a\u00020\u0019H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u0003\u0010\u000bR%\u0010\u0005\u001a\u00020\u00048\u0007z\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001f\u001a\u0004\b\u0005\u0010\rR%\u0010\u0006\u001a\u00020\u00048\u0007z\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b\u0006\u0010\rR%\u0010\u0007\u001a\u00020\u00048\u0007z\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001f\u001a\u0004\b\u0007\u0010\r\u00a8\u0006 "}, d2={"Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow$GlowPalette;", "", "", "colors", "", "phase", "styleId", "closed", "<init>", "([IFFF)V", "component1", "()[I", "component2", "()F", "component3", "component4", "copy", "([IFFF)Lrtx/kimiko/utils/render/render2d/glow/BuiltGlow$GlowPalette;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "[I", "F", "rtx.kimiko:kimiko"})
    public static final class GlowPalette {
        @NotNull
        private final int[] colors;
        private final float phase;
        private final float styleId;
        private final float closed;

        public GlowPalette(@NotNull int[] colors, float phase, float styleId, float closed) {
            Intrinsics.checkNotNullParameter((Object)colors, (String)"colors");
            this.colors = colors;
            this.phase = phase;
            this.styleId = styleId;
            this.closed = closed;
        }

        @JvmName(name="colors")
        @NotNull
        public final int[] colors() {
            return this.colors;
        }

        @JvmName(name="phase")
        public final float phase() {
            return this.phase;
        }

        @JvmName(name="styleId")
        public final float styleId() {
            return this.styleId;
        }

        @JvmName(name="closed")
        public final float closed() {
            return this.closed;
        }

        @NotNull
        public final int[] component1() {
            return this.colors;
        }

        public final float component2() {
            return this.phase;
        }

        public final float component3() {
            return this.styleId;
        }

        public final float component4() {
            return this.closed;
        }

        @NotNull
        public final GlowPalette copy(@NotNull int[] colors, float phase, float styleId, float closed) {
            Intrinsics.checkNotNullParameter((Object)colors, (String)"colors");
            return new GlowPalette(colors, phase, styleId, closed);
        }

        public static /* synthetic */ GlowPalette copy$default(GlowPalette glowPalette, int[] nArray, float f, float f2, float f3, int n, Object object) {
            if ((n & 1) != 0) {
                nArray = glowPalette.colors;
            }
            if ((n & 2) != 0) {
                f = glowPalette.phase;
            }
            if ((n & 4) != 0) {
                f2 = glowPalette.styleId;
            }
            if ((n & 8) != 0) {
                f3 = glowPalette.closed;
            }
            return glowPalette.copy(nArray, f, f2, f3);
        }

        @NotNull
        public String toString() {
            return "GlowPalette(colors=" + Arrays.toString(this.colors) + ", phase=" + this.phase + ", styleId=" + this.styleId + ", closed=" + this.closed + ")";
        }

        public int hashCode() {
            int result = Arrays.hashCode(this.colors);
            result = result * 31 + Float.hashCode(this.phase);
            result = result * 31 + Float.hashCode(this.styleId);
            result = result * 31 + Float.hashCode(this.closed);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof GlowPalette)) {
                return false;
            }
            GlowPalette glowPalette = (GlowPalette)other;
            if (!Intrinsics.areEqual((Object)this.colors, (Object)glowPalette.colors)) {
                return false;
            }
            if (Float.compare(this.phase, glowPalette.phase) != 0) {
                return false;
            }
            if (Float.compare(this.styleId, glowPalette.styleId) != 0) {
                return false;
            }
            return Float.compare(this.closed, glowPalette.closed) == 0;
        }
    }
}

