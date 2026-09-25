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
package rtx.kimiko.utils.render.render2d.rectangle.recthalficon;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.render2d.rectangle.recthalficon.HalfIconRectangleRenderer;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b(\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u0000 `2\u00020\u0001:\u0001`B\u00d3\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0011\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u000b\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\u0006\u0010\u0017\u001a\u00020\u0002\u0012\u0006\u0010\u0018\u001a\u00020\u0002\u0012\u0006\u0010\u0019\u001a\u00020\u0002\u0012\u0006\u0010\u001a\u001a\u00020\u0002\u0012\u0006\u0010\u001b\u001a\u00020\u0002\u0012\u0006\u0010\u001c\u001a\u00020\u0002\u0012\u0006\u0010\u001e\u001a\u00020\u001d\u00a2\u0006\u0004\b\u001f\u0010 Bu\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010!\u001a\u00020\u0002\u0012\u0006\u0010\"\u001a\u00020\u000b\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0011\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u000b\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\u0006\u0010\u0017\u001a\u00020\u0002\u0012\u0006\u0010\u001c\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001f\u0010#J\u0015\u0010$\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u000b\u00a2\u0006\u0004\b$\u0010%J-\u0010&\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0002\u00a2\u0006\u0004\b&\u0010'J\u001d\u0010(\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0002\u00a2\u0006\u0004\b(\u0010)J-\u0010*\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001d\u00a2\u0006\u0004\b*\u0010+J\u0017\u0010/\u001a\u00020.2\b\u0010-\u001a\u0004\u0018\u00010,\u00a2\u0006\u0004\b/\u00100J\r\u00102\u001a\u000201\u00a2\u0006\u0004\b2\u00103J\r\u00104\u001a\u000201\u00a2\u0006\u0004\b4\u00103J\r\u00105\u001a\u000201\u00a2\u0006\u0004\b5\u00103J\u0010\u00106\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b6\u00107J\u0010\u00108\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b8\u00107J\u0010\u00109\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b9\u00107J\u0010\u0010:\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b:\u00107J\u0010\u0010;\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b;\u00107J\u0010\u0010<\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b<\u00107J\u0010\u0010=\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b=\u00107J\u0010\u0010>\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b>\u00107J\u0010\u0010?\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b?\u0010@J\u0010\u0010A\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\bA\u0010@J\u0010\u0010B\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\bB\u0010@J\u0010\u0010C\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\bC\u0010@J\u0010\u0010D\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bD\u00107J\u0012\u0010E\u001a\u0004\u0018\u00010\u0011H\u00c6\u0003\u00a2\u0006\u0004\bE\u0010FJ\u0012\u0010G\u001a\u0004\u0018\u00010\u0011H\u00c6\u0003\u00a2\u0006\u0004\bG\u0010FJ\u0010\u0010H\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bH\u00107J\u0010\u0010I\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\bI\u0010@J\u0010\u0010J\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bJ\u00107J\u0010\u0010K\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bK\u00107J\u0010\u0010L\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bL\u00107J\u0010\u0010M\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bM\u00107J\u0010\u0010N\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bN\u00107J\u0010\u0010O\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bO\u00107J\u0010\u0010P\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bP\u00107J\u0010\u0010Q\u001a\u00020\u001dH\u00c6\u0003\u00a2\u0006\u0004\bQ\u0010RJ\u008e\u0002\u0010S\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000b2\b\b\u0002\u0010\u0010\u001a\u00020\u00022\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00022\b\b\u0002\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u00022\b\b\u0002\u0010\u0017\u001a\u00020\u00022\b\b\u0002\u0010\u0018\u001a\u00020\u00022\b\b\u0002\u0010\u0019\u001a\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u00022\b\b\u0002\u0010\u001b\u001a\u00020\u00022\b\b\u0002\u0010\u001c\u001a\u00020\u00022\b\b\u0002\u0010\u001e\u001a\u00020\u001dH\u00c6\u0001\u00a2\u0006\u0004\bS\u0010TJ\u001b\u0010V\u001a\u0002012\b\u0010U\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\bV\u0010WJ\u0011\u0010X\u001a\u00020\u000bH\u00d6\u0081\u0004\u00a2\u0006\u0004\bX\u0010@J\u0011\u0010Y\u001a\u00020\u0011H\u00d6\u0081\u0004\u00a2\u0006\u0004\bY\u0010FR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\\\u001a\u0004\b\u0003\u00107R%\u0010\u0004\u001a\u00020\u00028\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010\\\u001a\u0004\b\u0004\u00107R%\u0010\u0005\u001a\u00020\u00028\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010\\\u001a\u0004\b\u0005\u00107R%\u0010\u0006\u001a\u00020\u00028\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\\\u001a\u0004\b\u0006\u00107R%\u0010\u0007\u001a\u00020\u00028\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010\\\u001a\u0004\b\u0007\u00107R%\u0010\b\u001a\u00020\u00028\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010\\\u001a\u0004\b\b\u00107R%\u0010\t\u001a\u00020\u00028\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010\\\u001a\u0004\b\t\u00107R%\u0010\n\u001a\u00020\u00028\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010\\\u001a\u0004\b\n\u00107R%\u0010\f\u001a\u00020\u000b8\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010]\u001a\u0004\b\f\u0010@R%\u0010\r\u001a\u00020\u000b8\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u0010]\u001a\u0004\b\r\u0010@R%\u0010\u000e\u001a\u00020\u000b8\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u0010]\u001a\u0004\b\u000e\u0010@R%\u0010\u000f\u001a\u00020\u000b8\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u0010]\u001a\u0004\b\u000f\u0010@R%\u0010\u0010\u001a\u00020\u00028\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\u0010\u00a2\u0006\f\n\u0004\b\u0010\u0010\\\u001a\u0004\b\u0010\u00107R'\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\u0012\u00a2\u0006\f\n\u0004\b\u0012\u0010^\u001a\u0004\b\u0012\u0010FR'\u0010\u0013\u001a\u0004\u0018\u00010\u00118\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\u0013\u00a2\u0006\f\n\u0004\b\u0013\u0010^\u001a\u0004\b\u0013\u0010FR%\u0010\u0014\u001a\u00020\u00028\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\u0014\u00a2\u0006\f\n\u0004\b\u0014\u0010\\\u001a\u0004\b\u0014\u00107R%\u0010\u0015\u001a\u00020\u000b8\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\u0015\u00a2\u0006\f\n\u0004\b\u0015\u0010]\u001a\u0004\b\u0015\u0010@R%\u0010\u0016\u001a\u00020\u00028\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\u0016\u00a2\u0006\f\n\u0004\b\u0016\u0010\\\u001a\u0004\b\u0016\u00107R%\u0010\u0017\u001a\u00020\u00028\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\u0017\u00a2\u0006\f\n\u0004\b\u0017\u0010\\\u001a\u0004\b\u0017\u00107R%\u0010\u0018\u001a\u00020\u00028\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\u0018\u00a2\u0006\f\n\u0004\b\u0018\u0010\\\u001a\u0004\b\u0018\u00107R%\u0010\u0019\u001a\u00020\u00028\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\u0019\u00a2\u0006\f\n\u0004\b\u0019\u0010\\\u001a\u0004\b\u0019\u00107R%\u0010\u001a\u001a\u00020\u00028\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\u001a\u00a2\u0006\f\n\u0004\b\u001a\u0010\\\u001a\u0004\b\u001a\u00107R%\u0010\u001b\u001a\u00020\u00028\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\u001b\u00a2\u0006\f\n\u0004\b\u001b\u0010\\\u001a\u0004\b\u001b\u00107R%\u0010\u001c\u001a\u00020\u00028\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\u001c\u00a2\u0006\f\n\u0004\b\u001c\u0010\\\u001a\u0004\b\u001c\u00107R%\u0010\u001e\u001a\u00020\u001d8\u0007z\f\bZ\u0012\b\b[\u0012\u0004\b\b(\u001e\u00a2\u0006\f\n\u0004\b\u001e\u0010_\u001a\u0004\b\u001e\u0010R\u00a8\u0006a"}, d2={"Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/BuiltHalfIconRectangle;", "", "", "x", "y", "width", "height", "radiusTopLeft", "radiusTopRight", "radiusBottomRight", "radiusBottomLeft", "", "colorTopLeft", "colorTopRight", "colorBottomRight", "colorBottomLeft", "smoothness", "", "fontName", "icon", "iconSize", "iconColor", "spacingX", "spacingY", "paddingX", "paddingY", "jitterX", "jitterY", "rotationMaxDegrees", "", "seed", "<init>", "(FFFFFFFFIIIIFLjava/lang/String;Ljava/lang/String;FIFFFFFFFJ)V", "radius", "color", "(FFFFFILjava/lang/String;Ljava/lang/String;FIFFF)V", "withBackground", "(I)Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/BuiltHalfIconRectangle;", "withIcon", "(IFFF)Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/BuiltHalfIconRectangle;", "withPadding", "(FF)Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/BuiltHalfIconRectangle;", "withRandomization", "(FFFJ)Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/BuiltHalfIconRectangle;", "Lnet/minecraft/DrawContext;", "graphics", "", "render", "(Lnet/minecraft/DrawContext;)V", "", "visible", "()Z", "backgroundVisible", "iconsVisible", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "()I", "component10", "component11", "component12", "component13", "component14", "()Ljava/lang/String;", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "()J", "copy", "(FFFFFFFFIIIIFLjava/lang/String;Ljava/lang/String;FIFFFFFFFJ)Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/BuiltHalfIconRectangle;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "Lkotlin/jvm/JvmName;", "name", "F", "I", "Ljava/lang/String;", "J", "Companion", "rtx.kimiko:kimiko"})
public final class BuiltHalfIconRectangle {
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
    private final int colorTopLeft;
    private final int colorTopRight;
    private final int colorBottomRight;
    private final int colorBottomLeft;
    private final float smoothness;
    @Nullable
    private final String fontName;
    @Nullable
    private final String icon;
    private final float iconSize;
    private final int iconColor;
    private final float spacingX;
    private final float spacingY;
    private final float paddingX;
    private final float paddingY;
    private final float jitterX;
    private final float jitterY;
    private final float rotationMaxDegrees;
    private final long seed;
    public static final float DEFAULT_SMOOTHNESS = 0.0f;
    public static final float DEFAULT_ICON_SIZE = 8.0f;
    public static final float DEFAULT_SPACING = 14.0f;
    public static final float DEFAULT_PADDING = 2.0f;

    public BuiltHalfIconRectangle(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, float smoothness, @Nullable String fontName, @Nullable String icon, float iconSize, int iconColor, float spacingX, float spacingY, float paddingX, float paddingY, float jitterX, float jitterY, float rotationMaxDegrees, long seed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radiusTopLeft = radiusTopLeft;
        this.radiusTopRight = radiusTopRight;
        this.radiusBottomRight = radiusBottomRight;
        this.radiusBottomLeft = radiusBottomLeft;
        this.colorTopLeft = colorTopLeft;
        this.colorTopRight = colorTopRight;
        this.colorBottomRight = colorBottomRight;
        this.colorBottomLeft = colorBottomLeft;
        this.smoothness = smoothness;
        this.fontName = fontName;
        this.icon = icon;
        this.iconSize = iconSize;
        this.iconColor = iconColor;
        this.spacingX = spacingX;
        this.spacingY = spacingY;
        this.paddingX = paddingX;
        this.paddingY = paddingY;
        this.jitterX = jitterX;
        this.jitterY = jitterY;
        this.rotationMaxDegrees = rotationMaxDegrees;
        this.seed = seed;
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

    @JvmName(name="colorTopLeft")
    public final int colorTopLeft() {
        return this.colorTopLeft;
    }

    @JvmName(name="colorTopRight")
    public final int colorTopRight() {
        return this.colorTopRight;
    }

    @JvmName(name="colorBottomRight")
    public final int colorBottomRight() {
        return this.colorBottomRight;
    }

    @JvmName(name="colorBottomLeft")
    public final int colorBottomLeft() {
        return this.colorBottomLeft;
    }

    @JvmName(name="smoothness")
    public final float smoothness() {
        return this.smoothness;
    }

    @JvmName(name="fontName")
    @Nullable
    public final String fontName() {
        return this.fontName;
    }

    @JvmName(name="icon")
    @Nullable
    public final String icon() {
        return this.icon;
    }

    @JvmName(name="iconSize")
    public final float iconSize() {
        return this.iconSize;
    }

    @JvmName(name="iconColor")
    public final int iconColor() {
        return this.iconColor;
    }

    @JvmName(name="spacingX")
    public final float spacingX() {
        return this.spacingX;
    }

    @JvmName(name="spacingY")
    public final float spacingY() {
        return this.spacingY;
    }

    @JvmName(name="paddingX")
    public final float paddingX() {
        return this.paddingX;
    }

    @JvmName(name="paddingY")
    public final float paddingY() {
        return this.paddingY;
    }

    @JvmName(name="jitterX")
    public final float jitterX() {
        return this.jitterX;
    }

    @JvmName(name="jitterY")
    public final float jitterY() {
        return this.jitterY;
    }

    @JvmName(name="rotationMaxDegrees")
    public final float rotationMaxDegrees() {
        return this.rotationMaxDegrees;
    }

    @JvmName(name="seed")
    public final long seed() {
        return this.seed;
    }

    public BuiltHalfIconRectangle(float x, float y, float width, float height, float radius, int color, @Nullable String fontName, @Nullable String icon, float iconSize, int iconColor, float spacingX, float spacingY, float rotationMaxDegrees) {
        this(x, y, width, height, radius, radius, radius, radius, color, color, color, color, 0.0f, fontName, icon, iconSize, iconColor, spacingX, spacingY, 2.0f, 2.0f, 0.0f, 0.0f, rotationMaxDegrees, 0L);
    }

    @NotNull
    public final BuiltHalfIconRectangle withBackground(int color) {
        return new BuiltHalfIconRectangle(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, color, color, color, color, this.smoothness, this.fontName, this.icon, this.iconSize, this.iconColor, this.spacingX, this.spacingY, this.paddingX, this.paddingY, this.jitterX, this.jitterY, this.rotationMaxDegrees, this.seed);
    }

    @NotNull
    public final BuiltHalfIconRectangle withIcon(int iconColor, float iconSize, float spacingX, float spacingY) {
        return new BuiltHalfIconRectangle(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.colorTopLeft, this.colorTopRight, this.colorBottomRight, this.colorBottomLeft, this.smoothness, this.fontName, this.icon, iconSize, iconColor, spacingX, spacingY, this.paddingX, this.paddingY, this.jitterX, this.jitterY, this.rotationMaxDegrees, this.seed);
    }

    @NotNull
    public final BuiltHalfIconRectangle withPadding(float paddingX, float paddingY) {
        return new BuiltHalfIconRectangle(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.colorTopLeft, this.colorTopRight, this.colorBottomRight, this.colorBottomLeft, this.smoothness, this.fontName, this.icon, this.iconSize, this.iconColor, this.spacingX, this.spacingY, paddingX, paddingY, this.jitterX, this.jitterY, this.rotationMaxDegrees, this.seed);
    }

    @NotNull
    public final BuiltHalfIconRectangle withRandomization(float rotationMaxDegrees, float jitterX, float jitterY, long seed) {
        return new BuiltHalfIconRectangle(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.colorTopLeft, this.colorTopRight, this.colorBottomRight, this.colorBottomLeft, this.smoothness, this.fontName, this.icon, this.iconSize, this.iconColor, this.spacingX, this.spacingY, this.paddingX, this.paddingY, jitterX, jitterY, rotationMaxDegrees, seed);
    }

    public final void render(@Nullable DrawContext graphics) {
        HalfIconRectangleRenderer.Companion.getInstance().draw(graphics, this);
    }

    public final boolean visible() {
        return this.backgroundVisible() || this.iconsVisible();
    }

    public final boolean backgroundVisible() {
        return this.width > 0.0f && this.height > 0.0f && ((this.colorTopLeft | this.colorTopRight | this.colorBottomRight | this.colorBottomLeft) >>> 24 & 0xFF) > 0;
    }

    public final boolean iconsVisible() {
        CharSequence charSequence;
        return this.width > 0.0f && this.height > 0.0f && this.fontName != null && !((charSequence = (CharSequence)this.icon) == null || charSequence.length() == 0) && this.iconSize > 0.0f && this.iconColor >>> 24 != 0;
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
        return this.colorTopLeft;
    }

    public final int component10() {
        return this.colorTopRight;
    }

    public final int component11() {
        return this.colorBottomRight;
    }

    public final int component12() {
        return this.colorBottomLeft;
    }

    public final float component13() {
        return this.smoothness;
    }

    @Nullable
    public final String component14() {
        return this.fontName;
    }

    @Nullable
    public final String component15() {
        return this.icon;
    }

    public final float component16() {
        return this.iconSize;
    }

    public final int component17() {
        return this.iconColor;
    }

    public final float component18() {
        return this.spacingX;
    }

    public final float component19() {
        return this.spacingY;
    }

    public final float component20() {
        return this.paddingX;
    }

    public final float component21() {
        return this.paddingY;
    }

    public final float component22() {
        return this.jitterX;
    }

    public final float component23() {
        return this.jitterY;
    }

    public final float component24() {
        return this.rotationMaxDegrees;
    }

    public final long component25() {
        return this.seed;
    }

    @NotNull
    public final BuiltHalfIconRectangle copy(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, float smoothness, @Nullable String fontName, @Nullable String icon, float iconSize, int iconColor, float spacingX, float spacingY, float paddingX, float paddingY, float jitterX, float jitterY, float rotationMaxDegrees, long seed) {
        return new BuiltHalfIconRectangle(x, y, width, height, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft, smoothness, fontName, icon, iconSize, iconColor, spacingX, spacingY, paddingX, paddingY, jitterX, jitterY, rotationMaxDegrees, seed);
    }

    public static /* synthetic */ BuiltHalfIconRectangle copy$default(BuiltHalfIconRectangle builtHalfIconRectangle, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n, int n2, int n3, int n4, float f9, String string, String string2, float f10, int n5, float f11, float f12, float f13, float f14, float f15, float f16, float f17, long l, int n6, Object object) {
        if ((n6 & 1) != 0) {
            f = builtHalfIconRectangle.x;
        }
        if ((n6 & 2) != 0) {
            f2 = builtHalfIconRectangle.y;
        }
        if ((n6 & 4) != 0) {
            f3 = builtHalfIconRectangle.width;
        }
        if ((n6 & 8) != 0) {
            f4 = builtHalfIconRectangle.height;
        }
        if ((n6 & 0x10) != 0) {
            f5 = builtHalfIconRectangle.radiusTopLeft;
        }
        if ((n6 & 0x20) != 0) {
            f6 = builtHalfIconRectangle.radiusTopRight;
        }
        if ((n6 & 0x40) != 0) {
            f7 = builtHalfIconRectangle.radiusBottomRight;
        }
        if ((n6 & 0x80) != 0) {
            f8 = builtHalfIconRectangle.radiusBottomLeft;
        }
        if ((n6 & 0x100) != 0) {
            n = builtHalfIconRectangle.colorTopLeft;
        }
        if ((n6 & 0x200) != 0) {
            n2 = builtHalfIconRectangle.colorTopRight;
        }
        if ((n6 & 0x400) != 0) {
            n3 = builtHalfIconRectangle.colorBottomRight;
        }
        if ((n6 & 0x800) != 0) {
            n4 = builtHalfIconRectangle.colorBottomLeft;
        }
        if ((n6 & 0x1000) != 0) {
            f9 = builtHalfIconRectangle.smoothness;
        }
        if ((n6 & 0x2000) != 0) {
            string = builtHalfIconRectangle.fontName;
        }
        if ((n6 & 0x4000) != 0) {
            string2 = builtHalfIconRectangle.icon;
        }
        if ((n6 & 0x8000) != 0) {
            f10 = builtHalfIconRectangle.iconSize;
        }
        if ((n6 & 0x10000) != 0) {
            n5 = builtHalfIconRectangle.iconColor;
        }
        if ((n6 & 0x20000) != 0) {
            f11 = builtHalfIconRectangle.spacingX;
        }
        if ((n6 & 0x40000) != 0) {
            f12 = builtHalfIconRectangle.spacingY;
        }
        if ((n6 & 0x80000) != 0) {
            f13 = builtHalfIconRectangle.paddingX;
        }
        if ((n6 & 0x100000) != 0) {
            f14 = builtHalfIconRectangle.paddingY;
        }
        if ((n6 & 0x200000) != 0) {
            f15 = builtHalfIconRectangle.jitterX;
        }
        if ((n6 & 0x400000) != 0) {
            f16 = builtHalfIconRectangle.jitterY;
        }
        if ((n6 & 0x800000) != 0) {
            f17 = builtHalfIconRectangle.rotationMaxDegrees;
        }
        if ((n6 & 0x1000000) != 0) {
            l = builtHalfIconRectangle.seed;
        }
        return builtHalfIconRectangle.copy(f, f2, f3, f4, f5, f6, f7, f8, n, n2, n3, n4, f9, string, string2, f10, n5, f11, f12, f13, f14, f15, f16, f17, l);
    }

    @NotNull
    public String toString() {
        return "BuiltHalfIconRectangle(x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", radiusTopLeft=" + this.radiusTopLeft + ", radiusTopRight=" + this.radiusTopRight + ", radiusBottomRight=" + this.radiusBottomRight + ", radiusBottomLeft=" + this.radiusBottomLeft + ", colorTopLeft=" + this.colorTopLeft + ", colorTopRight=" + this.colorTopRight + ", colorBottomRight=" + this.colorBottomRight + ", colorBottomLeft=" + this.colorBottomLeft + ", smoothness=" + this.smoothness + ", fontName=" + this.fontName + ", icon=" + this.icon + ", iconSize=" + this.iconSize + ", iconColor=" + this.iconColor + ", spacingX=" + this.spacingX + ", spacingY=" + this.spacingY + ", paddingX=" + this.paddingX + ", paddingY=" + this.paddingY + ", jitterX=" + this.jitterX + ", jitterY=" + this.jitterY + ", rotationMaxDegrees=" + this.rotationMaxDegrees + ", seed=" + this.seed + ")";
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
        result = result * 31 + Integer.hashCode(this.colorTopLeft);
        result = result * 31 + Integer.hashCode(this.colorTopRight);
        result = result * 31 + Integer.hashCode(this.colorBottomRight);
        result = result * 31 + Integer.hashCode(this.colorBottomLeft);
        result = result * 31 + Float.hashCode(this.smoothness);
        result = result * 31 + (this.fontName == null ? 0 : this.fontName.hashCode());
        result = result * 31 + (this.icon == null ? 0 : this.icon.hashCode());
        result = result * 31 + Float.hashCode(this.iconSize);
        result = result * 31 + Integer.hashCode(this.iconColor);
        result = result * 31 + Float.hashCode(this.spacingX);
        result = result * 31 + Float.hashCode(this.spacingY);
        result = result * 31 + Float.hashCode(this.paddingX);
        result = result * 31 + Float.hashCode(this.paddingY);
        result = result * 31 + Float.hashCode(this.jitterX);
        result = result * 31 + Float.hashCode(this.jitterY);
        result = result * 31 + Float.hashCode(this.rotationMaxDegrees);
        result = result * 31 + Long.hashCode(this.seed);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltHalfIconRectangle)) {
            return false;
        }
        BuiltHalfIconRectangle builtHalfIconRectangle = (BuiltHalfIconRectangle)other;
        if (Float.compare(this.x, builtHalfIconRectangle.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, builtHalfIconRectangle.y) != 0) {
            return false;
        }
        if (Float.compare(this.width, builtHalfIconRectangle.width) != 0) {
            return false;
        }
        if (Float.compare(this.height, builtHalfIconRectangle.height) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopLeft, builtHalfIconRectangle.radiusTopLeft) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopRight, builtHalfIconRectangle.radiusTopRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomRight, builtHalfIconRectangle.radiusBottomRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomLeft, builtHalfIconRectangle.radiusBottomLeft) != 0) {
            return false;
        }
        if (this.colorTopLeft != builtHalfIconRectangle.colorTopLeft) {
            return false;
        }
        if (this.colorTopRight != builtHalfIconRectangle.colorTopRight) {
            return false;
        }
        if (this.colorBottomRight != builtHalfIconRectangle.colorBottomRight) {
            return false;
        }
        if (this.colorBottomLeft != builtHalfIconRectangle.colorBottomLeft) {
            return false;
        }
        if (Float.compare(this.smoothness, builtHalfIconRectangle.smoothness) != 0) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.fontName, (Object)builtHalfIconRectangle.fontName)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.icon, (Object)builtHalfIconRectangle.icon)) {
            return false;
        }
        if (Float.compare(this.iconSize, builtHalfIconRectangle.iconSize) != 0) {
            return false;
        }
        if (this.iconColor != builtHalfIconRectangle.iconColor) {
            return false;
        }
        if (Float.compare(this.spacingX, builtHalfIconRectangle.spacingX) != 0) {
            return false;
        }
        if (Float.compare(this.spacingY, builtHalfIconRectangle.spacingY) != 0) {
            return false;
        }
        if (Float.compare(this.paddingX, builtHalfIconRectangle.paddingX) != 0) {
            return false;
        }
        if (Float.compare(this.paddingY, builtHalfIconRectangle.paddingY) != 0) {
            return false;
        }
        if (Float.compare(this.jitterX, builtHalfIconRectangle.jitterX) != 0) {
            return false;
        }
        if (Float.compare(this.jitterY, builtHalfIconRectangle.jitterY) != 0) {
            return false;
        }
        if (Float.compare(this.rotationMaxDegrees, builtHalfIconRectangle.rotationMaxDegrees) != 0) {
            return false;
        }
        return this.seed == builtHalfIconRectangle.seed;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0006\u00a8\u0006\n"}, d2={"Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/BuiltHalfIconRectangle.Companion;", "", "<init>", "()V", "", "DEFAULT_SMOOTHNESS", "F", "DEFAULT_ICON_SIZE", "DEFAULT_SPACING", "DEFAULT_PADDING", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

