/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.rectangle.rectdefault;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.render2d.rectangle.rectdefault.DefaultRectangleRenderer;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b%\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u0000 a2\u00020\u0001:\u0001aB\u00e1\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0013\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\u0006\u0010\u0017\u001a\u00020\u0002\u0012\u0006\u0010\u0018\u001a\u00020\u0002\u0012\u0006\u0010\u0019\u001a\u00020\u000b\u0012\u0006\u0010\u001a\u001a\u00020\u0002\u0012\u0006\u0010\u001b\u001a\u00020\u0002\u0012\u0006\u0010\u001c\u001a\u00020\u0002\u0012\u0006\u0010\u001d\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001f\u0010 B9\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010!\u001a\u00020\u0002\u0012\u0006\u0010\"\u001a\u00020\u000b\u00a2\u0006\u0004\b\u001f\u0010#BQ\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\"\u001a\u00020\u000b\u00a2\u0006\u0004\b\u001f\u0010$Bq\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001f\u0010%J\u0015\u0010&\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0002\u00a2\u0006\u0004\b&\u0010'J\u0015\u0010(\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u000b\u00a2\u0006\u0004\b(\u0010)J%\u0010-\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u00022\u0006\u0010,\u001a\u00020\u0002\u00a2\u0006\u0004\b-\u0010.J\u0017\u00102\u001a\u0002012\b\u00100\u001a\u0004\u0018\u00010/\u00a2\u0006\u0004\b2\u00103J\r\u00105\u001a\u000204\u00a2\u0006\u0004\b5\u00106J\u0010\u00107\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b7\u00108J\u0010\u00109\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b9\u00108J\u0010\u0010:\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b:\u00108J\u0010\u0010;\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b;\u00108J\u0010\u0010<\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b<\u00108J\u0010\u0010=\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b=\u00108J\u0010\u0010>\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b>\u00108J\u0010\u0010?\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b?\u00108J\u0010\u0010@\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b@\u0010AJ\u0010\u0010B\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\bB\u0010AJ\u0010\u0010C\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\bC\u0010AJ\u0010\u0010D\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\bD\u0010AJ\u0010\u0010E\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bE\u00108J\u0010\u0010F\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bF\u00108J\u0010\u0010G\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bG\u00108J\u0010\u0010H\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bH\u00108J\u0010\u0010I\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bI\u00108J\u0010\u0010J\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bJ\u00108J\u0010\u0010K\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bK\u00108J\u0010\u0010L\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bL\u00108J\u0010\u0010M\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bM\u00108J\u0010\u0010N\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\bN\u0010AJ\u0010\u0010O\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bO\u00108J\u0010\u0010P\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bP\u00108J\u0010\u0010Q\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bQ\u00108J\u0010\u0010R\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bR\u00108J\u0010\u0010S\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bS\u00108J\u009e\u0002\u0010T\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000b2\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u00022\b\b\u0002\u0010\u0014\u001a\u00020\u00022\b\b\u0002\u0010\u0015\u001a\u00020\u00022\b\b\u0002\u0010\u0016\u001a\u00020\u00022\b\b\u0002\u0010\u0017\u001a\u00020\u00022\b\b\u0002\u0010\u0018\u001a\u00020\u00022\b\b\u0002\u0010\u0019\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u00022\b\b\u0002\u0010\u001b\u001a\u00020\u00022\b\b\u0002\u0010\u001c\u001a\u00020\u00022\b\b\u0002\u0010\u001d\u001a\u00020\u00022\b\b\u0002\u0010\u001e\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\bT\u0010UJ\u001b\u0010W\u001a\u0002042\b\u0010V\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\bW\u0010XJ\u0011\u0010Y\u001a\u00020\u000bH\u00d6\u0081\u0004\u00a2\u0006\u0004\bY\u0010AJ\u0011\u0010[\u001a\u00020ZH\u00d6\u0081\u0004\u00a2\u0006\u0004\b[\u0010\\R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010_\u001a\u0004\b\u0003\u00108R%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010_\u001a\u0004\b\u0004\u00108R%\u0010\u0005\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010_\u001a\u0004\b\u0005\u00108R%\u0010\u0006\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010_\u001a\u0004\b\u0006\u00108R%\u0010\u0007\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010_\u001a\u0004\b\u0007\u00108R%\u0010\b\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010_\u001a\u0004\b\b\u00108R%\u0010\t\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010_\u001a\u0004\b\t\u00108R%\u0010\n\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010_\u001a\u0004\b\n\u00108R%\u0010\f\u001a\u00020\u000b8\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010`\u001a\u0004\b\f\u0010AR%\u0010\r\u001a\u00020\u000b8\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u0010`\u001a\u0004\b\r\u0010AR%\u0010\u000e\u001a\u00020\u000b8\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u0010`\u001a\u0004\b\u000e\u0010AR%\u0010\u000f\u001a\u00020\u000b8\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u0010`\u001a\u0004\b\u000f\u0010AR%\u0010\u0010\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0010\u00a2\u0006\f\n\u0004\b\u0010\u0010_\u001a\u0004\b\u0010\u00108R%\u0010\u0011\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0011\u00a2\u0006\f\n\u0004\b\u0011\u0010_\u001a\u0004\b\u0011\u00108R%\u0010\u0012\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0012\u00a2\u0006\f\n\u0004\b\u0012\u0010_\u001a\u0004\b\u0012\u00108R%\u0010\u0013\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0013\u00a2\u0006\f\n\u0004\b\u0013\u0010_\u001a\u0004\b\u0013\u00108R%\u0010\u0014\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0014\u00a2\u0006\f\n\u0004\b\u0014\u0010_\u001a\u0004\b\u0014\u00108R%\u0010\u0015\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0015\u00a2\u0006\f\n\u0004\b\u0015\u0010_\u001a\u0004\b\u0015\u00108R%\u0010\u0016\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0016\u00a2\u0006\f\n\u0004\b\u0016\u0010_\u001a\u0004\b\u0016\u00108R%\u0010\u0017\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0017\u00a2\u0006\f\n\u0004\b\u0017\u0010_\u001a\u0004\b\u0017\u00108R%\u0010\u0018\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0018\u00a2\u0006\f\n\u0004\b\u0018\u0010_\u001a\u0004\b\u0018\u00108R%\u0010\u0019\u001a\u00020\u000b8\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0019\u00a2\u0006\f\n\u0004\b\u0019\u0010`\u001a\u0004\b\u0019\u0010AR%\u0010\u001a\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u001a\u00a2\u0006\f\n\u0004\b\u001a\u0010_\u001a\u0004\b\u001a\u00108R%\u0010\u001b\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u001b\u00a2\u0006\f\n\u0004\b\u001b\u0010_\u001a\u0004\b\u001b\u00108R%\u0010\u001c\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u001c\u00a2\u0006\f\n\u0004\b\u001c\u0010_\u001a\u0004\b\u001c\u00108R%\u0010\u001d\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u001d\u00a2\u0006\f\n\u0004\b\u001d\u0010_\u001a\u0004\b\u001d\u00108R%\u0010\u001e\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u001e\u00a2\u0006\f\n\u0004\b\u001e\u0010_\u001a\u0004\b\u001e\u00108\u00a8\u0006b"}, d2={"Lrtx/kimiko/utils/render/render2d/rectangle/rectdefault/BuiltRectangle;", "", "", "x", "y", "width", "height", "radiusTopLeft", "radiusTopRight", "radiusBottomRight", "radiusBottomLeft", "", "colorTopLeft", "colorTopRight", "colorBottomRight", "colorBottomLeft", "smoothness", "scissorX", "scissorY", "scissorWidth", "scissorHeight", "scissorRadiusTopLeft", "scissorRadiusTopRight", "scissorRadiusBottomRight", "scissorRadiusBottomLeft", "paletteMode", "paletteTint", "paletteAlpha", "scissorCos", "scissorSin", "scissorFade", "<init>", "(FFFFFFFFIIIIFFFFFFFFFIFFFFF)V", "radius", "color", "(FFFFFI)V", "(FFFFFFFFI)V", "(FFFFFFFFIIIIF)V", "withSmoothness", "(F)Lrtx/kimiko/utils/render/render2d/rectangle/rectdefault/BuiltRectangle;", "withColor", "(I)Lrtx/kimiko/utils/render/render2d/rectangle/rectdefault/BuiltRectangle;", "mode", "tint", "alpha", "withPaletteGradient", "(IFF)Lrtx/kimiko/utils/render/render2d/rectangle/rectdefault/BuiltRectangle;", "Lnet/minecraft/DrawContext;", "graphics", "", "render", "(Lnet/minecraft/DrawContext;)V", "", "visible", "()Z", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "()I", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "copy", "(FFFFFFFFIIIIFFFFFFFFFIFFFFF)Lrtx/kimiko/utils/render/render2d/rectangle/rectdefault/BuiltRectangle;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "I", "Companion", "rtx.kimiko:kimiko"})
public final class BuiltRectangle {
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
    private final float scissorX;
    private final float scissorY;
    private final float scissorWidth;
    private final float scissorHeight;
    private final float scissorRadiusTopLeft;
    private final float scissorRadiusTopRight;
    private final float scissorRadiusBottomRight;
    private final float scissorRadiusBottomLeft;
    private final int paletteMode;
    private final float paletteTint;
    private final float paletteAlpha;
    private final float scissorCos;
    private final float scissorSin;
    private final float scissorFade;
    public static final float DEFAULT_SMOOTHNESS = 0.7f;
    public static final int DEFAULT_COLOR = -1;
    public static final int PALETTE_NONE = 0;
    public static final int PALETTE_VERTICAL = 1;
    public static final int PALETTE_HORIZONTAL = 2;
    public static final int PALETTE_HORIZONTAL_LOOP = 3;

    public BuiltRectangle(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, float smoothness, float scissorX, float scissorY, float scissorWidth, float scissorHeight, float scissorRadiusTopLeft, float scissorRadiusTopRight, float scissorRadiusBottomRight, float scissorRadiusBottomLeft, int paletteMode, float paletteTint, float paletteAlpha, float scissorCos, float scissorSin, float scissorFade) {
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
        this.scissorX = scissorX;
        this.scissorY = scissorY;
        this.scissorWidth = scissorWidth;
        this.scissorHeight = scissorHeight;
        this.scissorRadiusTopLeft = scissorRadiusTopLeft;
        this.scissorRadiusTopRight = scissorRadiusTopRight;
        this.scissorRadiusBottomRight = scissorRadiusBottomRight;
        this.scissorRadiusBottomLeft = scissorRadiusBottomLeft;
        this.paletteMode = paletteMode;
        this.paletteTint = paletteTint;
        this.paletteAlpha = paletteAlpha;
        this.scissorCos = scissorCos;
        this.scissorSin = scissorSin;
        this.scissorFade = scissorFade;
    }

    public /* synthetic */ BuiltRectangle(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n, int n2, int n3, int n4, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, int n5, float f18, float f19, float f20, float f21, float f22, int n6, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, f5, f6, f7, f8, n, n2, n3, n4, f9, f10, f11, f12, f13, f14, f15, f16, f17, n5, f18, f19, f20, f21, (n6 & 0x4000000) != 0 ? 0.0f : f22);
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

    @JvmName(name="scissorX")
    public final float scissorX() {
        return this.scissorX;
    }

    @JvmName(name="scissorY")
    public final float scissorY() {
        return this.scissorY;
    }

    @JvmName(name="scissorWidth")
    public final float scissorWidth() {
        return this.scissorWidth;
    }

    @JvmName(name="scissorHeight")
    public final float scissorHeight() {
        return this.scissorHeight;
    }

    @JvmName(name="scissorRadiusTopLeft")
    public final float scissorRadiusTopLeft() {
        return this.scissorRadiusTopLeft;
    }

    @JvmName(name="scissorRadiusTopRight")
    public final float scissorRadiusTopRight() {
        return this.scissorRadiusTopRight;
    }

    @JvmName(name="scissorRadiusBottomRight")
    public final float scissorRadiusBottomRight() {
        return this.scissorRadiusBottomRight;
    }

    @JvmName(name="scissorRadiusBottomLeft")
    public final float scissorRadiusBottomLeft() {
        return this.scissorRadiusBottomLeft;
    }

    @JvmName(name="paletteMode")
    public final int paletteMode() {
        return this.paletteMode;
    }

    @JvmName(name="paletteTint")
    public final float paletteTint() {
        return this.paletteTint;
    }

    @JvmName(name="paletteAlpha")
    public final float paletteAlpha() {
        return this.paletteAlpha;
    }

    @JvmName(name="scissorCos")
    public final float scissorCos() {
        return this.scissorCos;
    }

    @JvmName(name="scissorSin")
    public final float scissorSin() {
        return this.scissorSin;
    }

    @JvmName(name="scissorFade")
    public final float scissorFade() {
        return this.scissorFade;
    }

    public BuiltRectangle(float x, float y, float width, float height, float radius, int color) {
        this(x, y, width, height, radius, radius, radius, radius, color, color, color, color, 0.7f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0x4000000, null);
    }

    public BuiltRectangle(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, int color) {
        this(x, y, width, height, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, color, color, color, color, 0.7f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0x4000000, null);
    }

    public BuiltRectangle(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, float smoothness) {
        this(x, y, width, height, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft, smoothness, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0x4000000, null);
    }

    @NotNull
    public final BuiltRectangle withSmoothness(float smoothness) {
        return new BuiltRectangle(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.colorTopLeft, this.colorTopRight, this.colorBottomRight, this.colorBottomLeft, smoothness, this.scissorX, this.scissorY, this.scissorWidth, this.scissorHeight, this.scissorRadiusTopLeft, this.scissorRadiusTopRight, this.scissorRadiusBottomRight, this.scissorRadiusBottomLeft, this.paletteMode, this.paletteTint, this.paletteAlpha, this.scissorCos, this.scissorSin, 0.0f, 0x4000000, null);
    }

    @NotNull
    public final BuiltRectangle withColor(int color) {
        return new BuiltRectangle(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, color, color, color, color, this.smoothness, this.scissorX, this.scissorY, this.scissorWidth, this.scissorHeight, this.scissorRadiusTopLeft, this.scissorRadiusTopRight, this.scissorRadiusBottomRight, this.scissorRadiusBottomLeft, this.paletteMode, this.paletteTint, this.paletteAlpha, this.scissorCos, this.scissorSin, 0.0f, 0x4000000, null);
    }

    @NotNull
    public final BuiltRectangle withPaletteGradient(int mode, float tint, float alpha) {
        return new BuiltRectangle(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.colorTopLeft, this.colorTopRight, this.colorBottomRight, this.colorBottomLeft, this.smoothness, this.scissorX, this.scissorY, this.scissorWidth, this.scissorHeight, this.scissorRadiusTopLeft, this.scissorRadiusTopRight, this.scissorRadiusBottomRight, this.scissorRadiusBottomLeft, mode, tint, alpha, this.scissorCos, this.scissorSin, 0.0f, 0x4000000, null);
    }

    public final void render(@Nullable DrawContext graphics) {
        DefaultRectangleRenderer.Companion.getInstance().draw(graphics, this);
    }

    public final boolean visible() {
        if (this.width <= 0.0f || this.height <= 0.0f) {
            return false;
        }
        if (this.paletteMode != 0) {
            return this.paletteAlpha > 0.0f;
        }
        return (this.colorTopLeft | this.colorTopRight | this.colorBottomRight | this.colorBottomLeft) >>> 24 != 0;
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

    public final float component14() {
        return this.scissorX;
    }

    public final float component15() {
        return this.scissorY;
    }

    public final float component16() {
        return this.scissorWidth;
    }

    public final float component17() {
        return this.scissorHeight;
    }

    public final float component18() {
        return this.scissorRadiusTopLeft;
    }

    public final float component19() {
        return this.scissorRadiusTopRight;
    }

    public final float component20() {
        return this.scissorRadiusBottomRight;
    }

    public final float component21() {
        return this.scissorRadiusBottomLeft;
    }

    public final int component22() {
        return this.paletteMode;
    }

    public final float component23() {
        return this.paletteTint;
    }

    public final float component24() {
        return this.paletteAlpha;
    }

    public final float component25() {
        return this.scissorCos;
    }

    public final float component26() {
        return this.scissorSin;
    }

    public final float component27() {
        return this.scissorFade;
    }

    @NotNull
    public final BuiltRectangle copy(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, float smoothness, float scissorX, float scissorY, float scissorWidth, float scissorHeight, float scissorRadiusTopLeft, float scissorRadiusTopRight, float scissorRadiusBottomRight, float scissorRadiusBottomLeft, int paletteMode, float paletteTint, float paletteAlpha, float scissorCos, float scissorSin, float scissorFade) {
        return new BuiltRectangle(x, y, width, height, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft, smoothness, scissorX, scissorY, scissorWidth, scissorHeight, scissorRadiusTopLeft, scissorRadiusTopRight, scissorRadiusBottomRight, scissorRadiusBottomLeft, paletteMode, paletteTint, paletteAlpha, scissorCos, scissorSin, scissorFade);
    }

    public static /* synthetic */ BuiltRectangle copy$default(BuiltRectangle builtRectangle, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n, int n2, int n3, int n4, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, int n5, float f18, float f19, float f20, float f21, float f22, int n6, Object object) {
        if ((n6 & 1) != 0) {
            f = builtRectangle.x;
        }
        if ((n6 & 2) != 0) {
            f2 = builtRectangle.y;
        }
        if ((n6 & 4) != 0) {
            f3 = builtRectangle.width;
        }
        if ((n6 & 8) != 0) {
            f4 = builtRectangle.height;
        }
        if ((n6 & 0x10) != 0) {
            f5 = builtRectangle.radiusTopLeft;
        }
        if ((n6 & 0x20) != 0) {
            f6 = builtRectangle.radiusTopRight;
        }
        if ((n6 & 0x40) != 0) {
            f7 = builtRectangle.radiusBottomRight;
        }
        if ((n6 & 0x80) != 0) {
            f8 = builtRectangle.radiusBottomLeft;
        }
        if ((n6 & 0x100) != 0) {
            n = builtRectangle.colorTopLeft;
        }
        if ((n6 & 0x200) != 0) {
            n2 = builtRectangle.colorTopRight;
        }
        if ((n6 & 0x400) != 0) {
            n3 = builtRectangle.colorBottomRight;
        }
        if ((n6 & 0x800) != 0) {
            n4 = builtRectangle.colorBottomLeft;
        }
        if ((n6 & 0x1000) != 0) {
            f9 = builtRectangle.smoothness;
        }
        if ((n6 & 0x2000) != 0) {
            f10 = builtRectangle.scissorX;
        }
        if ((n6 & 0x4000) != 0) {
            f11 = builtRectangle.scissorY;
        }
        if ((n6 & 0x8000) != 0) {
            f12 = builtRectangle.scissorWidth;
        }
        if ((n6 & 0x10000) != 0) {
            f13 = builtRectangle.scissorHeight;
        }
        if ((n6 & 0x20000) != 0) {
            f14 = builtRectangle.scissorRadiusTopLeft;
        }
        if ((n6 & 0x40000) != 0) {
            f15 = builtRectangle.scissorRadiusTopRight;
        }
        if ((n6 & 0x80000) != 0) {
            f16 = builtRectangle.scissorRadiusBottomRight;
        }
        if ((n6 & 0x100000) != 0) {
            f17 = builtRectangle.scissorRadiusBottomLeft;
        }
        if ((n6 & 0x200000) != 0) {
            n5 = builtRectangle.paletteMode;
        }
        if ((n6 & 0x400000) != 0) {
            f18 = builtRectangle.paletteTint;
        }
        if ((n6 & 0x800000) != 0) {
            f19 = builtRectangle.paletteAlpha;
        }
        if ((n6 & 0x1000000) != 0) {
            f20 = builtRectangle.scissorCos;
        }
        if ((n6 & 0x2000000) != 0) {
            f21 = builtRectangle.scissorSin;
        }
        if ((n6 & 0x4000000) != 0) {
            f22 = builtRectangle.scissorFade;
        }
        return builtRectangle.copy(f, f2, f3, f4, f5, f6, f7, f8, n, n2, n3, n4, f9, f10, f11, f12, f13, f14, f15, f16, f17, n5, f18, f19, f20, f21, f22);
    }

    @NotNull
    public String toString() {
        return "BuiltRectangle(x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", radiusTopLeft=" + this.radiusTopLeft + ", radiusTopRight=" + this.radiusTopRight + ", radiusBottomRight=" + this.radiusBottomRight + ", radiusBottomLeft=" + this.radiusBottomLeft + ", colorTopLeft=" + this.colorTopLeft + ", colorTopRight=" + this.colorTopRight + ", colorBottomRight=" + this.colorBottomRight + ", colorBottomLeft=" + this.colorBottomLeft + ", smoothness=" + this.smoothness + ", scissorX=" + this.scissorX + ", scissorY=" + this.scissorY + ", scissorWidth=" + this.scissorWidth + ", scissorHeight=" + this.scissorHeight + ", scissorRadiusTopLeft=" + this.scissorRadiusTopLeft + ", scissorRadiusTopRight=" + this.scissorRadiusTopRight + ", scissorRadiusBottomRight=" + this.scissorRadiusBottomRight + ", scissorRadiusBottomLeft=" + this.scissorRadiusBottomLeft + ", paletteMode=" + this.paletteMode + ", paletteTint=" + this.paletteTint + ", paletteAlpha=" + this.paletteAlpha + ", scissorCos=" + this.scissorCos + ", scissorSin=" + this.scissorSin + ", scissorFade=" + this.scissorFade + ")";
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
        result = result * 31 + Float.hashCode(this.scissorX);
        result = result * 31 + Float.hashCode(this.scissorY);
        result = result * 31 + Float.hashCode(this.scissorWidth);
        result = result * 31 + Float.hashCode(this.scissorHeight);
        result = result * 31 + Float.hashCode(this.scissorRadiusTopLeft);
        result = result * 31 + Float.hashCode(this.scissorRadiusTopRight);
        result = result * 31 + Float.hashCode(this.scissorRadiusBottomRight);
        result = result * 31 + Float.hashCode(this.scissorRadiusBottomLeft);
        result = result * 31 + Integer.hashCode(this.paletteMode);
        result = result * 31 + Float.hashCode(this.paletteTint);
        result = result * 31 + Float.hashCode(this.paletteAlpha);
        result = result * 31 + Float.hashCode(this.scissorCos);
        result = result * 31 + Float.hashCode(this.scissorSin);
        result = result * 31 + Float.hashCode(this.scissorFade);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltRectangle)) {
            return false;
        }
        BuiltRectangle builtRectangle = (BuiltRectangle)other;
        if (Float.compare(this.x, builtRectangle.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, builtRectangle.y) != 0) {
            return false;
        }
        if (Float.compare(this.width, builtRectangle.width) != 0) {
            return false;
        }
        if (Float.compare(this.height, builtRectangle.height) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopLeft, builtRectangle.radiusTopLeft) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopRight, builtRectangle.radiusTopRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomRight, builtRectangle.radiusBottomRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomLeft, builtRectangle.radiusBottomLeft) != 0) {
            return false;
        }
        if (this.colorTopLeft != builtRectangle.colorTopLeft) {
            return false;
        }
        if (this.colorTopRight != builtRectangle.colorTopRight) {
            return false;
        }
        if (this.colorBottomRight != builtRectangle.colorBottomRight) {
            return false;
        }
        if (this.colorBottomLeft != builtRectangle.colorBottomLeft) {
            return false;
        }
        if (Float.compare(this.smoothness, builtRectangle.smoothness) != 0) {
            return false;
        }
        if (Float.compare(this.scissorX, builtRectangle.scissorX) != 0) {
            return false;
        }
        if (Float.compare(this.scissorY, builtRectangle.scissorY) != 0) {
            return false;
        }
        if (Float.compare(this.scissorWidth, builtRectangle.scissorWidth) != 0) {
            return false;
        }
        if (Float.compare(this.scissorHeight, builtRectangle.scissorHeight) != 0) {
            return false;
        }
        if (Float.compare(this.scissorRadiusTopLeft, builtRectangle.scissorRadiusTopLeft) != 0) {
            return false;
        }
        if (Float.compare(this.scissorRadiusTopRight, builtRectangle.scissorRadiusTopRight) != 0) {
            return false;
        }
        if (Float.compare(this.scissorRadiusBottomRight, builtRectangle.scissorRadiusBottomRight) != 0) {
            return false;
        }
        if (Float.compare(this.scissorRadiusBottomLeft, builtRectangle.scissorRadiusBottomLeft) != 0) {
            return false;
        }
        if (this.paletteMode != builtRectangle.paletteMode) {
            return false;
        }
        if (Float.compare(this.paletteTint, builtRectangle.paletteTint) != 0) {
            return false;
        }
        if (Float.compare(this.paletteAlpha, builtRectangle.paletteAlpha) != 0) {
            return false;
        }
        if (Float.compare(this.scissorCos, builtRectangle.scissorCos) != 0) {
            return false;
        }
        if (Float.compare(this.scissorSin, builtRectangle.scissorSin) != 0) {
            return false;
        }
        return Float.compare(this.scissorFade, builtRectangle.scissorFade) == 0;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00078\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\n\u0010\tR\u0014\u0010\u000b\u001a\u00020\u00078\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\tR\u0014\u0010\f\u001a\u00020\u00078\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\f\u0010\tR\u0014\u0010\r\u001a\u00020\u00078\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\r\u0010\t\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/utils/render/render2d/rectangle/rectdefault/BuiltRectangle.Companion;", "", "<init>", "()V", "", "DEFAULT_SMOOTHNESS", "F", "", "DEFAULT_COLOR", "I", "PALETTE_NONE", "PALETTE_VERTICAL", "PALETTE_HORIZONTAL", "PALETTE_HORIZONTAL_LOOP", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

