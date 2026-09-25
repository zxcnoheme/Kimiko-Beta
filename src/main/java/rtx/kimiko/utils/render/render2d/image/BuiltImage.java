/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.image;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u0015\n\u0002\b2\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u0000 d2\u00020\u0001:\u0001dB\u00c1\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\r\u0012\u0006\u0010\u0011\u001a\u00020\r\u0012\u0006\u0010\u0012\u001a\u00020\u0004\u0012\u0006\u0010\u0013\u001a\u00020\u0004\u0012\u0006\u0010\u0014\u001a\u00020\u0004\u0012\u0006\u0010\u0015\u001a\u00020\u0004\u0012\u0006\u0010\u0016\u001a\u00020\u0004\u0012\u0006\u0010\u0017\u001a\u00020\u0004\u0012\u0006\u0010\u0018\u001a\u00020\u0004\u0012\u0006\u0010\u0019\u001a\u00020\u0004\u0012\u0006\u0010\u001a\u001a\u00020\u0004\u0012\u0006\u0010\u001c\u001a\u00020\u001b\u00a2\u0006\u0004\b\u001d\u0010\u001eB3\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\u001f\u001a\u00020\u0004\u00a2\u0006\u0004\b\u001d\u0010 B;\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\u001f\u001a\u00020\u0004\u0012\u0006\u0010!\u001a\u00020\r\u00a2\u0006\u0004\b\u001d\u0010\"BS\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\u001f\u001a\u00020\u0004\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\r\u0012\u0006\u0010\u0011\u001a\u00020\r\u00a2\u0006\u0004\b\u001d\u0010#Bk\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\r\u0012\u0006\u0010\u0011\u001a\u00020\r\u00a2\u0006\u0004\b\u001d\u0010$BC\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010%\u001a\u00020\u0004\u0012\u0006\u0010&\u001a\u00020\u0004\u0012\u0006\u0010\u001f\u001a\u00020\u0004\u0012\u0006\u0010!\u001a\u00020\r\u00a2\u0006\u0004\b\u001d\u0010'J\r\u0010\u001f\u001a\u00020\u0004\u00a2\u0006\u0004\b\u001f\u0010(J\r\u0010)\u001a\u00020\u001b\u00a2\u0006\u0004\b)\u0010*J\u0019\u0010-\u001a\u00020\u00002\n\u0010,\u001a\u00020+\"\u00020\r\u00a2\u0006\u0004\b-\u0010.J-\u0010/\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004\u00a2\u0006\u0004\b/\u00100J%\u00103\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u0004\u00a2\u0006\u0004\b3\u00104J\u0015\u00105\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0004\u00a2\u0006\u0004\b5\u00106J\r\u00107\u001a\u00020\u0000\u00a2\u0006\u0004\b7\u00108J/\u0010-\u001a\u00020\u00002\u0006\u00109\u001a\u00020\r2\u0006\u0010:\u001a\u00020\r2\u0006\u0010;\u001a\u00020\r2\u0006\u0010<\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b-\u0010=J\u0012\u0010>\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b>\u0010?J\u0010\u0010@\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b@\u0010(J\u0010\u0010A\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\bA\u0010(J\u0010\u0010B\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\bB\u0010(J\u0010\u0010C\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\bC\u0010(J\u0010\u0010D\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\bD\u0010(J\u0010\u0010E\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\bE\u0010(J\u0010\u0010F\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\bF\u0010(J\u0010\u0010G\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\bG\u0010(J\u0010\u0010H\u001a\u00020\rH\u00c6\u0003\u00a2\u0006\u0004\bH\u0010IJ\u0010\u0010J\u001a\u00020\rH\u00c6\u0003\u00a2\u0006\u0004\bJ\u0010IJ\u0010\u0010K\u001a\u00020\rH\u00c6\u0003\u00a2\u0006\u0004\bK\u0010IJ\u0010\u0010L\u001a\u00020\rH\u00c6\u0003\u00a2\u0006\u0004\bL\u0010IJ\u0010\u0010M\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\bM\u0010(J\u0010\u0010N\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\bN\u0010(J\u0010\u0010O\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\bO\u0010(J\u0010\u0010P\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\bP\u0010(J\u0010\u0010Q\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\bQ\u0010(J\u0010\u0010R\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\bR\u0010(J\u0010\u0010S\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\bS\u0010(J\u0010\u0010T\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\bT\u0010(J\u0010\u0010U\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\bU\u0010(J\u0010\u0010V\u001a\u00020\u001bH\u00c6\u0003\u00a2\u0006\u0004\bV\u0010*J\u00f8\u0001\u0010W\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u00042\b\b\u0002\u0010\u0015\u001a\u00020\u00042\b\b\u0002\u0010\u0016\u001a\u00020\u00042\b\b\u0002\u0010\u0017\u001a\u00020\u00042\b\b\u0002\u0010\u0018\u001a\u00020\u00042\b\b\u0002\u0010\u0019\u001a\u00020\u00042\b\b\u0002\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u001bH\u00c6\u0001\u00a2\u0006\u0004\bW\u0010XJ\u001b\u0010Z\u001a\u00020\u001b2\b\u0010Y\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\bZ\u0010[J\u0011\u0010\\\u001a\u00020\rH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\\\u0010IJ\u0011\u0010]\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b]\u0010?R'\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010`\u001a\u0004\b\u0003\u0010?R%\u0010\u0005\u001a\u00020\u00048\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010a\u001a\u0004\b\u0005\u0010(R%\u0010\u0006\u001a\u00020\u00048\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010a\u001a\u0004\b\u0006\u0010(R%\u0010\u0007\u001a\u00020\u00048\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010a\u001a\u0004\b\u0007\u0010(R%\u0010\b\u001a\u00020\u00048\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010a\u001a\u0004\b\b\u0010(R%\u0010\t\u001a\u00020\u00048\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010a\u001a\u0004\b\t\u0010(R%\u0010\n\u001a\u00020\u00048\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010a\u001a\u0004\b\n\u0010(R%\u0010\u000b\u001a\u00020\u00048\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010a\u001a\u0004\b\u000b\u0010(R%\u0010\f\u001a\u00020\u00048\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010a\u001a\u0004\b\f\u0010(R%\u0010\u000e\u001a\u00020\r8\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u0010b\u001a\u0004\b\u000e\u0010IR%\u0010\u000f\u001a\u00020\r8\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u0010b\u001a\u0004\b\u000f\u0010IR%\u0010\u0010\u001a\u00020\r8\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\u0010\u00a2\u0006\f\n\u0004\b\u0010\u0010b\u001a\u0004\b\u0010\u0010IR%\u0010\u0011\u001a\u00020\r8\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\u0011\u00a2\u0006\f\n\u0004\b\u0011\u0010b\u001a\u0004\b\u0011\u0010IR%\u0010\u0012\u001a\u00020\u00048\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\u0012\u00a2\u0006\f\n\u0004\b\u0012\u0010a\u001a\u0004\b\u0012\u0010(R%\u0010\u0013\u001a\u00020\u00048\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\u0013\u00a2\u0006\f\n\u0004\b\u0013\u0010a\u001a\u0004\b\u0013\u0010(R%\u0010\u0014\u001a\u00020\u00048\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\u0014\u00a2\u0006\f\n\u0004\b\u0014\u0010a\u001a\u0004\b\u0014\u0010(R%\u0010\u0015\u001a\u00020\u00048\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\u0015\u00a2\u0006\f\n\u0004\b\u0015\u0010a\u001a\u0004\b\u0015\u0010(R%\u0010\u0016\u001a\u00020\u00048\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\u0016\u00a2\u0006\f\n\u0004\b\u0016\u0010a\u001a\u0004\b\u0016\u0010(R%\u0010\u0017\u001a\u00020\u00048\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\u0017\u00a2\u0006\f\n\u0004\b\u0017\u0010a\u001a\u0004\b\u0017\u0010(R%\u0010\u0018\u001a\u00020\u00048\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\u0018\u00a2\u0006\f\n\u0004\b\u0018\u0010a\u001a\u0004\b\u0018\u0010(R%\u0010\u0019\u001a\u00020\u00048\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\u0019\u00a2\u0006\f\n\u0004\b\u0019\u0010a\u001a\u0004\b\u0019\u0010(R%\u0010\u001a\u001a\u00020\u00048\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\u001a\u00a2\u0006\f\n\u0004\b\u001a\u0010a\u001a\u0004\b\u001a\u0010(R%\u0010\u001c\u001a\u00020\u001b8\u0007z\f\b^\u0012\b\b_\u0012\u0004\b\b(\u001c\u00a2\u0006\f\n\u0004\b\u001c\u0010c\u001a\u0004\b\u001c\u0010*\u00a8\u0006e"}, d2={"Lrtx/kimiko/utils/render/render2d/image/BuiltImage;", "", "", "texture", "", "x", "y", "size", "radiusTL", "radiusTR", "radiusBR", "radiusBL", "smoothness", "", "colorTopLeft", "colorTopRight", "colorBottomRight", "colorBottomLeft", "u0", "v0", "u1", "v1", "rotationDegrees", "rotationOriginX", "rotationOriginY", "explicitWidth", "explicitHeight", "", "nearest", "<init>", "(Ljava/lang/String;FFFFFFFFIIIIFFFFFFFFFZ)V", "radius", "(Ljava/lang/String;FFFF)V", "color", "(Ljava/lang/String;FFFFI)V", "(Ljava/lang/String;FFFFIIII)V", "(Ljava/lang/String;FFFFFFFIIII)V", "width", "height", "(Ljava/lang/String;FFFFFI)V", "()F", "visible", "()Z", "", "colors", "withColors", "([I)Lrtx/kimiko/utils/render/render2d/image/BuiltImage;", "withUv", "(FFFF)Lrtx/kimiko/utils/render/render2d/image/BuiltImage;", "originX", "originY", "withRotation", "(FFF)Lrtx/kimiko/utils/render/render2d/image/BuiltImage;", "withSmoothness", "(F)Lrtx/kimiko/utils/render/render2d/image/BuiltImage;", "withNearest", "()Lrtx/kimiko/utils/render/render2d/image/BuiltImage;", "topLeft", "topRight", "bottomRight", "bottomLeft", "(IIII)Lrtx/kimiko/utils/render/render2d/image/BuiltImage;", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "()I", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "copy", "(Ljava/lang/String;FFFFFFFFIIIIFFFFFFFFFZ)Lrtx/kimiko/utils/render/render2d/image/BuiltImage;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "Lkotlin/jvm/JvmName;", "name", "Ljava/lang/String;", "F", "I", "Z", "Companion", "rtx.kimiko:kimiko"})
public final class BuiltImage {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    private final String texture;
    private final float x;
    private final float y;
    private final float size;
    private final float radiusTL;
    private final float radiusTR;
    private final float radiusBR;
    private final float radiusBL;
    private final float smoothness;
    private final int colorTopLeft;
    private final int colorTopRight;
    private final int colorBottomRight;
    private final int colorBottomLeft;
    private final float u0;
    private final float v0;
    private final float u1;
    private final float v1;
    private final float rotationDegrees;
    private final float rotationOriginX;
    private final float rotationOriginY;
    private final float explicitWidth;
    private final float explicitHeight;
    private final boolean nearest;
    @JvmField
    public static final int DEFAULT_COLOR = -1;
    public static final float DEFAULT_SMOOTHNESS = 0.0f;

    public BuiltImage(@Nullable String texture, float x, float y, float size, float radiusTL, float radiusTR, float radiusBR, float radiusBL, float smoothness, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, float u0, float v0, float u1, float v1, float rotationDegrees, float rotationOriginX, float rotationOriginY, float explicitWidth, float explicitHeight, boolean nearest) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.size = size;
        this.radiusTL = radiusTL;
        this.radiusTR = radiusTR;
        this.radiusBR = radiusBR;
        this.radiusBL = radiusBL;
        this.smoothness = smoothness;
        this.colorTopLeft = colorTopLeft;
        this.colorTopRight = colorTopRight;
        this.colorBottomRight = colorBottomRight;
        this.colorBottomLeft = colorBottomLeft;
        this.u0 = u0;
        this.v0 = v0;
        this.u1 = u1;
        this.v1 = v1;
        this.rotationDegrees = rotationDegrees;
        this.rotationOriginX = rotationOriginX;
        this.rotationOriginY = rotationOriginY;
        this.explicitWidth = explicitWidth;
        this.explicitHeight = explicitHeight;
        this.nearest = nearest;
    }

    @JvmName(name="texture")
    @Nullable
    public final String texture() {
        return this.texture;
    }

    @JvmName(name="x")
    public final float x() {
        return this.x;
    }

    @JvmName(name="y")
    public final float y() {
        return this.y;
    }

    @JvmName(name="size")
    public final float size() {
        return this.size;
    }

    @JvmName(name="radiusTL")
    public final float radiusTL() {
        return this.radiusTL;
    }

    @JvmName(name="radiusTR")
    public final float radiusTR() {
        return this.radiusTR;
    }

    @JvmName(name="radiusBR")
    public final float radiusBR() {
        return this.radiusBR;
    }

    @JvmName(name="radiusBL")
    public final float radiusBL() {
        return this.radiusBL;
    }

    @JvmName(name="smoothness")
    public final float smoothness() {
        return this.smoothness;
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

    @JvmName(name="u0")
    public final float u0() {
        return this.u0;
    }

    @JvmName(name="v0")
    public final float v0() {
        return this.v0;
    }

    @JvmName(name="u1")
    public final float u1() {
        return this.u1;
    }

    @JvmName(name="v1")
    public final float v1() {
        return this.v1;
    }

    @JvmName(name="rotationDegrees")
    public final float rotationDegrees() {
        return this.rotationDegrees;
    }

    @JvmName(name="rotationOriginX")
    public final float rotationOriginX() {
        return this.rotationOriginX;
    }

    @JvmName(name="rotationOriginY")
    public final float rotationOriginY() {
        return this.rotationOriginY;
    }

    @JvmName(name="explicitWidth")
    public final float explicitWidth() {
        return this.explicitWidth;
    }

    @JvmName(name="explicitHeight")
    public final float explicitHeight() {
        return this.explicitHeight;
    }

    @JvmName(name="nearest")
    public final boolean nearest() {
        return this.nearest;
    }

    public BuiltImage(@Nullable String texture, float x, float y, float size, float radius) {
        this(texture, x, y, size, radius, radius, radius, radius, 0.0f, DEFAULT_COLOR, DEFAULT_COLOR, DEFAULT_COLOR, DEFAULT_COLOR, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, false);
    }

    public BuiltImage(@Nullable String texture, float x, float y, float size, float radius, int color) {
        this(texture, x, y, size, radius, radius, radius, radius, 0.0f, color, color, color, color, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, false);
    }

    public BuiltImage(@Nullable String texture, float x, float y, float size, float radius, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft) {
        this(texture, x, y, size, radius, radius, radius, radius, 0.0f, colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, false);
    }

    public BuiltImage(@Nullable String texture, float x, float y, float size, float radiusTL, float radiusTR, float radiusBR, float radiusBL, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft) {
        this(texture, x, y, size, radiusTL, radiusTR, radiusBR, radiusBL, 0.0f, colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, false);
    }

    public BuiltImage(@Nullable String texture, float x, float y, float width, float height, float radius, int color) {
        this(texture, x, y, 0.0f, radius, radius, radius, radius, 0.0f, color, color, color, color, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, Math.max(0.0f, width), Math.max(0.0f, height), false);
    }

    public final float radius() {
        return this.radiusTL;
    }

    public final boolean visible() {
        CharSequence charSequence = this.texture;
        return !(charSequence == null || StringsKt.isBlank((CharSequence)charSequence)) && (this.size > 0.0f || this.explicitWidth > 0.0f && this.explicitHeight > 0.0f) && (BuiltImage.Companion.effectiveAlpha(this.colorTopLeft) > 0 || BuiltImage.Companion.effectiveAlpha(this.colorTopRight) > 0 || BuiltImage.Companion.effectiveAlpha(this.colorBottomRight) > 0 || BuiltImage.Companion.effectiveAlpha(this.colorBottomLeft) > 0);
    }

    @NotNull
    public final BuiltImage withColors(int ... colors) {
        Intrinsics.checkNotNullParameter((Object)colors, (String)"colors");
        if (colors.length == 0) {
            return this;
        }
        if (colors.length == 1) {
            return this.withColors(colors[0], colors[0], colors[0], colors[0]);
        }
        if (colors.length == 2) {
            return this.withColors(colors[0], colors[1], colors[1], colors[0]);
        }
        if (colors.length == 3) {
            return this.withColors(colors[0], colors[1], colors[2], colors[0]);
        }
        return this.withColors(colors[0], colors[1], colors[2], colors[3]);
    }

    @NotNull
    public final BuiltImage withUv(float u0, float v0, float u1, float v1) {
        return new BuiltImage(this.texture, this.x, this.y, this.size, this.radiusTL, this.radiusTR, this.radiusBR, this.radiusBL, this.smoothness, this.colorTopLeft, this.colorTopRight, this.colorBottomRight, this.colorBottomLeft, u0, v0, u1, v1, this.rotationDegrees, this.rotationOriginX, this.rotationOriginY, this.explicitWidth, this.explicitHeight, this.nearest);
    }

    @NotNull
    public final BuiltImage withRotation(float rotationDegrees, float originX, float originY) {
        return new BuiltImage(this.texture, this.x, this.y, this.size, this.radiusTL, this.radiusTR, this.radiusBR, this.radiusBL, this.smoothness, this.colorTopLeft, this.colorTopRight, this.colorBottomRight, this.colorBottomLeft, this.u0, this.v0, this.u1, this.v1, rotationDegrees, originX, originY, this.explicitWidth, this.explicitHeight, this.nearest);
    }

    @NotNull
    public final BuiltImage withSmoothness(float smoothness) {
        return new BuiltImage(this.texture, this.x, this.y, this.size, this.radiusTL, this.radiusTR, this.radiusBR, this.radiusBL, smoothness, this.colorTopLeft, this.colorTopRight, this.colorBottomRight, this.colorBottomLeft, this.u0, this.v0, this.u1, this.v1, this.rotationDegrees, this.rotationOriginX, this.rotationOriginY, this.explicitWidth, this.explicitHeight, this.nearest);
    }

    @NotNull
    public final BuiltImage withNearest() {
        if (this.nearest) {
            return this;
        }
        return new BuiltImage(this.texture, this.x, this.y, this.size, this.radiusTL, this.radiusTR, this.radiusBR, this.radiusBL, this.smoothness, this.colorTopLeft, this.colorTopRight, this.colorBottomRight, this.colorBottomLeft, this.u0, this.v0, this.u1, this.v1, this.rotationDegrees, this.rotationOriginX, this.rotationOriginY, this.explicitWidth, this.explicitHeight, true);
    }

    private final BuiltImage withColors(int topLeft, int topRight, int bottomRight, int bottomLeft) {
        return new BuiltImage(this.texture, this.x, this.y, this.size, this.radiusTL, this.radiusTR, this.radiusBR, this.radiusBL, this.smoothness, topLeft, topRight, bottomRight, bottomLeft, this.u0, this.v0, this.u1, this.v1, this.rotationDegrees, this.rotationOriginX, this.rotationOriginY, this.explicitWidth, this.explicitHeight, this.nearest);
    }

    @Nullable
    public final String component1() {
        return this.texture;
    }

    public final float component2() {
        return this.x;
    }

    public final float component3() {
        return this.y;
    }

    public final float component4() {
        return this.size;
    }

    public final float component5() {
        return this.radiusTL;
    }

    public final float component6() {
        return this.radiusTR;
    }

    public final float component7() {
        return this.radiusBR;
    }

    public final float component8() {
        return this.radiusBL;
    }

    public final float component9() {
        return this.smoothness;
    }

    public final int component10() {
        return this.colorTopLeft;
    }

    public final int component11() {
        return this.colorTopRight;
    }

    public final int component12() {
        return this.colorBottomRight;
    }

    public final int component13() {
        return this.colorBottomLeft;
    }

    public final float component14() {
        return this.u0;
    }

    public final float component15() {
        return this.v0;
    }

    public final float component16() {
        return this.u1;
    }

    public final float component17() {
        return this.v1;
    }

    public final float component18() {
        return this.rotationDegrees;
    }

    public final float component19() {
        return this.rotationOriginX;
    }

    public final float component20() {
        return this.rotationOriginY;
    }

    public final float component21() {
        return this.explicitWidth;
    }

    public final float component22() {
        return this.explicitHeight;
    }

    public final boolean component23() {
        return this.nearest;
    }

    @NotNull
    public final BuiltImage copy(@Nullable String texture, float x, float y, float size, float radiusTL, float radiusTR, float radiusBR, float radiusBL, float smoothness, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, float u0, float v0, float u1, float v1, float rotationDegrees, float rotationOriginX, float rotationOriginY, float explicitWidth, float explicitHeight, boolean nearest) {
        return new BuiltImage(texture, x, y, size, radiusTL, radiusTR, radiusBR, radiusBL, smoothness, colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft, u0, v0, u1, v1, rotationDegrees, rotationOriginX, rotationOriginY, explicitWidth, explicitHeight, nearest);
    }

    public static /* synthetic */ BuiltImage copy$default(BuiltImage builtImage, String string, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n, int n2, int n3, int n4, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, boolean bl, int n5, Object object) {
        if ((n5 & 1) != 0) {
            string = builtImage.texture;
        }
        if ((n5 & 2) != 0) {
            f = builtImage.x;
        }
        if ((n5 & 4) != 0) {
            f2 = builtImage.y;
        }
        if ((n5 & 8) != 0) {
            f3 = builtImage.size;
        }
        if ((n5 & 0x10) != 0) {
            f4 = builtImage.radiusTL;
        }
        if ((n5 & 0x20) != 0) {
            f5 = builtImage.radiusTR;
        }
        if ((n5 & 0x40) != 0) {
            f6 = builtImage.radiusBR;
        }
        if ((n5 & 0x80) != 0) {
            f7 = builtImage.radiusBL;
        }
        if ((n5 & 0x100) != 0) {
            f8 = builtImage.smoothness;
        }
        if ((n5 & 0x200) != 0) {
            n = builtImage.colorTopLeft;
        }
        if ((n5 & 0x400) != 0) {
            n2 = builtImage.colorTopRight;
        }
        if ((n5 & 0x800) != 0) {
            n3 = builtImage.colorBottomRight;
        }
        if ((n5 & 0x1000) != 0) {
            n4 = builtImage.colorBottomLeft;
        }
        if ((n5 & 0x2000) != 0) {
            f9 = builtImage.u0;
        }
        if ((n5 & 0x4000) != 0) {
            f10 = builtImage.v0;
        }
        if ((n5 & 0x8000) != 0) {
            f11 = builtImage.u1;
        }
        if ((n5 & 0x10000) != 0) {
            f12 = builtImage.v1;
        }
        if ((n5 & 0x20000) != 0) {
            f13 = builtImage.rotationDegrees;
        }
        if ((n5 & 0x40000) != 0) {
            f14 = builtImage.rotationOriginX;
        }
        if ((n5 & 0x80000) != 0) {
            f15 = builtImage.rotationOriginY;
        }
        if ((n5 & 0x100000) != 0) {
            f16 = builtImage.explicitWidth;
        }
        if ((n5 & 0x200000) != 0) {
            f17 = builtImage.explicitHeight;
        }
        if ((n5 & 0x400000) != 0) {
            bl = builtImage.nearest;
        }
        return builtImage.copy(string, f, f2, f3, f4, f5, f6, f7, f8, n, n2, n3, n4, f9, f10, f11, f12, f13, f14, f15, f16, f17, bl);
    }

    @NotNull
    public String toString() {
        return "BuiltImage(texture=" + this.texture + ", x=" + this.x + ", y=" + this.y + ", size=" + this.size + ", radiusTL=" + this.radiusTL + ", radiusTR=" + this.radiusTR + ", radiusBR=" + this.radiusBR + ", radiusBL=" + this.radiusBL + ", smoothness=" + this.smoothness + ", colorTopLeft=" + this.colorTopLeft + ", colorTopRight=" + this.colorTopRight + ", colorBottomRight=" + this.colorBottomRight + ", colorBottomLeft=" + this.colorBottomLeft + ", u0=" + this.u0 + ", v0=" + this.v0 + ", u1=" + this.u1 + ", v1=" + this.v1 + ", rotationDegrees=" + this.rotationDegrees + ", rotationOriginX=" + this.rotationOriginX + ", rotationOriginY=" + this.rotationOriginY + ", explicitWidth=" + this.explicitWidth + ", explicitHeight=" + this.explicitHeight + ", nearest=" + this.nearest + ")";
    }

    public int hashCode() {
        int result = this.texture == null ? 0 : this.texture.hashCode();
        result = result * 31 + Float.hashCode(this.x);
        result = result * 31 + Float.hashCode(this.y);
        result = result * 31 + Float.hashCode(this.size);
        result = result * 31 + Float.hashCode(this.radiusTL);
        result = result * 31 + Float.hashCode(this.radiusTR);
        result = result * 31 + Float.hashCode(this.radiusBR);
        result = result * 31 + Float.hashCode(this.radiusBL);
        result = result * 31 + Float.hashCode(this.smoothness);
        result = result * 31 + Integer.hashCode(this.colorTopLeft);
        result = result * 31 + Integer.hashCode(this.colorTopRight);
        result = result * 31 + Integer.hashCode(this.colorBottomRight);
        result = result * 31 + Integer.hashCode(this.colorBottomLeft);
        result = result * 31 + Float.hashCode(this.u0);
        result = result * 31 + Float.hashCode(this.v0);
        result = result * 31 + Float.hashCode(this.u1);
        result = result * 31 + Float.hashCode(this.v1);
        result = result * 31 + Float.hashCode(this.rotationDegrees);
        result = result * 31 + Float.hashCode(this.rotationOriginX);
        result = result * 31 + Float.hashCode(this.rotationOriginY);
        result = result * 31 + Float.hashCode(this.explicitWidth);
        result = result * 31 + Float.hashCode(this.explicitHeight);
        result = result * 31 + Boolean.hashCode(this.nearest);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltImage)) {
            return false;
        }
        BuiltImage builtImage = (BuiltImage)other;
        if (!Intrinsics.areEqual((Object)this.texture, (Object)builtImage.texture)) {
            return false;
        }
        if (Float.compare(this.x, builtImage.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, builtImage.y) != 0) {
            return false;
        }
        if (Float.compare(this.size, builtImage.size) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTL, builtImage.radiusTL) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTR, builtImage.radiusTR) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBR, builtImage.radiusBR) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBL, builtImage.radiusBL) != 0) {
            return false;
        }
        if (Float.compare(this.smoothness, builtImage.smoothness) != 0) {
            return false;
        }
        if (this.colorTopLeft != builtImage.colorTopLeft) {
            return false;
        }
        if (this.colorTopRight != builtImage.colorTopRight) {
            return false;
        }
        if (this.colorBottomRight != builtImage.colorBottomRight) {
            return false;
        }
        if (this.colorBottomLeft != builtImage.colorBottomLeft) {
            return false;
        }
        if (Float.compare(this.u0, builtImage.u0) != 0) {
            return false;
        }
        if (Float.compare(this.v0, builtImage.v0) != 0) {
            return false;
        }
        if (Float.compare(this.u1, builtImage.u1) != 0) {
            return false;
        }
        if (Float.compare(this.v1, builtImage.v1) != 0) {
            return false;
        }
        if (Float.compare(this.rotationDegrees, builtImage.rotationDegrees) != 0) {
            return false;
        }
        if (Float.compare(this.rotationOriginX, builtImage.rotationOriginX) != 0) {
            return false;
        }
        if (Float.compare(this.rotationOriginY, builtImage.rotationOriginY) != 0) {
            return false;
        }
        if (Float.compare(this.explicitWidth, builtImage.explicitWidth) != 0) {
            return false;
        }
        if (Float.compare(this.explicitHeight, builtImage.explicitHeight) != 0) {
            return false;
        }
        return this.nearest == builtImage.nearest;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0019\u0010\t\u001a\u00020\u00048\u0006X\u0087D\u0092\u0002\u0002\b\b\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\u000b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/utils/render/render2d/image/BuiltImage.Companion;", "", "<init>", "()V", "", "color", "effectiveAlpha", "(I)I", "Lkotlin/jvm/JvmField;", "DEFAULT_COLOR", "I", "", "DEFAULT_SMOOTHNESS", "F", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final int effectiveAlpha(int color) {
            return color >>> 24 & 0xFF;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

