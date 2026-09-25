/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmOverloads
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 */
package rtx.kimiko.utils.render.render2d.outline.outlinedefault;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import rtx.kimiko.utils.render.core.frame.EngineFrame;
import rtx.kimiko.utils.render.render2d.outline.outlinedefault.DefaultOutlineBatch;
import rtx.kimiko.utils.render.render2d.outline.outlinedefault.DefaultOutlineRenderState;
import rtx.kimiko.utils.render.util.scissor.ScissorUtil;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b%\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u0000 a2\u00020\u0001:\u0001aB\u00e5\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\f\u0012\u0006\u0010\u0010\u001a\u00020\f\u0012\u0006\u0010\u0011\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0013\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\u0006\u0010\u0017\u001a\u00020\u0002\u0012\u0006\u0010\u0018\u001a\u00020\u0002\u0012\u0006\u0010\u0019\u001a\u00020\u0002\u0012\u0006\u0010\u001a\u001a\u00020\u0002\u0012\u0006\u0010\u001b\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001f\u0010 By\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\f\u0012\u0006\u0010\u0010\u001a\u00020\f\u0012\u0006\u0010\u0011\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001f\u0010!BA\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\"\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010#\u001a\u00020\f\u00a2\u0006\u0004\b\u001f\u0010$BY\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010#\u001a\u00020\f\u00a2\u0006\u0004\b\u001f\u0010%J%\u0010)\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u00022\b\b\u0002\u0010'\u001a\u00020\u0002H\u0007b\u0002\b(\u00a2\u0006\u0004\b)\u0010*J\u0015\u0010+\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0002\u00a2\u0006\u0004\b+\u0010,J\u0015\u0010-\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\f\u00a2\u0006\u0004\b-\u0010.J\u0017\u00102\u001a\u0002012\b\u00100\u001a\u0004\u0018\u00010/\u00a2\u0006\u0004\b2\u00103J\r\u00105\u001a\u000204\u00a2\u0006\u0004\b5\u00106J\u0010\u00107\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b7\u00108J\u0010\u00109\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b9\u00108J\u0010\u0010:\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b:\u00108J\u0010\u0010;\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b;\u00108J\u0010\u0010<\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b<\u00108J\u0010\u0010=\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b=\u00108J\u0010\u0010>\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b>\u00108J\u0010\u0010?\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b?\u00108J\u0010\u0010@\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b@\u00108J\u0010\u0010A\u001a\u00020\fH\u00c6\u0003\u00a2\u0006\u0004\bA\u0010BJ\u0010\u0010C\u001a\u00020\fH\u00c6\u0003\u00a2\u0006\u0004\bC\u0010BJ\u0010\u0010D\u001a\u00020\fH\u00c6\u0003\u00a2\u0006\u0004\bD\u0010BJ\u0010\u0010E\u001a\u00020\fH\u00c6\u0003\u00a2\u0006\u0004\bE\u0010BJ\u0010\u0010F\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bF\u00108J\u0010\u0010G\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bG\u00108J\u0010\u0010H\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bH\u00108J\u0010\u0010I\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bI\u00108J\u0010\u0010J\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bJ\u00108J\u0010\u0010K\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bK\u00108J\u0010\u0010L\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bL\u00108J\u0010\u0010M\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bM\u00108J\u0010\u0010N\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bN\u00108J\u0010\u0010O\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bO\u00108J\u0010\u0010P\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bP\u00108J\u0010\u0010Q\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bQ\u00108J\u0010\u0010R\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bR\u00108J\u0010\u0010S\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\bS\u00108J\u009e\u0002\u0010T\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\f2\b\b\u0002\u0010\u0011\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u00022\b\b\u0002\u0010\u0014\u001a\u00020\u00022\b\b\u0002\u0010\u0015\u001a\u00020\u00022\b\b\u0002\u0010\u0016\u001a\u00020\u00022\b\b\u0002\u0010\u0017\u001a\u00020\u00022\b\b\u0002\u0010\u0018\u001a\u00020\u00022\b\b\u0002\u0010\u0019\u001a\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u00022\b\b\u0002\u0010\u001b\u001a\u00020\u00022\b\b\u0002\u0010\u001c\u001a\u00020\u00022\b\b\u0002\u0010\u001d\u001a\u00020\u00022\b\b\u0002\u0010\u001e\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\bT\u0010UJ\u001b\u0010W\u001a\u0002042\b\u0010V\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\bW\u0010XJ\u0011\u0010Y\u001a\u00020\fH\u00d6\u0081\u0004\u00a2\u0006\u0004\bY\u0010BJ\u0011\u0010[\u001a\u00020ZH\u00d6\u0081\u0004\u00a2\u0006\u0004\b[\u0010\\R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010_\u001a\u0004\b\u0003\u00108R%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010_\u001a\u0004\b\u0004\u00108R%\u0010\u0005\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010_\u001a\u0004\b\u0005\u00108R%\u0010\u0006\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010_\u001a\u0004\b\u0006\u00108R%\u0010\u0007\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010_\u001a\u0004\b\u0007\u00108R%\u0010\b\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010_\u001a\u0004\b\b\u00108R%\u0010\t\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010_\u001a\u0004\b\t\u00108R%\u0010\n\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010_\u001a\u0004\b\n\u00108R%\u0010\u000b\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010_\u001a\u0004\b\u000b\u00108R%\u0010\r\u001a\u00020\f8\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u0010`\u001a\u0004\b\r\u0010BR%\u0010\u000e\u001a\u00020\f8\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u0010`\u001a\u0004\b\u000e\u0010BR%\u0010\u000f\u001a\u00020\f8\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u0010`\u001a\u0004\b\u000f\u0010BR%\u0010\u0010\u001a\u00020\f8\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0010\u00a2\u0006\f\n\u0004\b\u0010\u0010`\u001a\u0004\b\u0010\u0010BR%\u0010\u0011\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0011\u00a2\u0006\f\n\u0004\b\u0011\u0010_\u001a\u0004\b\u0011\u00108R%\u0010\u0012\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0012\u00a2\u0006\f\n\u0004\b\u0012\u0010_\u001a\u0004\b\u0012\u00108R%\u0010\u0013\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0013\u00a2\u0006\f\n\u0004\b\u0013\u0010_\u001a\u0004\b\u0013\u00108R%\u0010\u0014\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0014\u00a2\u0006\f\n\u0004\b\u0014\u0010_\u001a\u0004\b\u0014\u00108R%\u0010\u0015\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0015\u00a2\u0006\f\n\u0004\b\u0015\u0010_\u001a\u0004\b\u0015\u00108R%\u0010\u0016\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0016\u00a2\u0006\f\n\u0004\b\u0016\u0010_\u001a\u0004\b\u0016\u00108R%\u0010\u0017\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0017\u00a2\u0006\f\n\u0004\b\u0017\u0010_\u001a\u0004\b\u0017\u00108R%\u0010\u0018\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0018\u00a2\u0006\f\n\u0004\b\u0018\u0010_\u001a\u0004\b\u0018\u00108R%\u0010\u0019\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u0019\u00a2\u0006\f\n\u0004\b\u0019\u0010_\u001a\u0004\b\u0019\u00108R%\u0010\u001a\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u001a\u00a2\u0006\f\n\u0004\b\u001a\u0010_\u001a\u0004\b\u001a\u00108R%\u0010\u001b\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u001b\u00a2\u0006\f\n\u0004\b\u001b\u0010_\u001a\u0004\b\u001b\u00108R%\u0010\u001c\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u001c\u00a2\u0006\f\n\u0004\b\u001c\u0010_\u001a\u0004\b\u001c\u00108R%\u0010\u001d\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u001d\u00a2\u0006\f\n\u0004\b\u001d\u0010_\u001a\u0004\b\u001d\u00108R%\u0010\u001e\u001a\u00020\u00028\u0007z\f\b]\u0012\b\b^\u0012\u0004\b\b(\u001e\u00a2\u0006\f\n\u0004\b\u001e\u0010_\u001a\u0004\b\u001e\u00108\u00a8\u0006b"}, d2={"Lrtx/kimiko/utils/render/render2d/outline/outlinedefault/BuiltOutline;", "", "", "x", "y", "width", "height", "radiusTopLeft", "radiusTopRight", "radiusBottomRight", "radiusBottomLeft", "thickness", "", "colorTopLeft", "colorTopRight", "colorBottomRight", "colorBottomLeft", "smoothness", "scissorX", "scissorY", "scissorWidth", "scissorHeight", "scissorRadiusTopLeft", "scissorRadiusTopRight", "scissorRadiusBottomRight", "scissorRadiusBottomLeft", "scissorCos", "scissorSin", "paletteStrength", "colorOffset", "scissorFade", "<init>", "(FFFFFFFFFIIIIFFFFFFFFFFFFFF)V", "(FFFFFFFFFIIIIF)V", "radius", "color", "(FFFFFFI)V", "(FFFFFFFFFI)V", "offset", "strength", "Lkotlin/jvm/JvmOverloads;", "withPaletteGradient", "(FF)Lrtx/kimiko/utils/render/render2d/outline/outlinedefault/BuiltOutline;", "withSmoothness", "(F)Lrtx/kimiko/utils/render/render2d/outline/outlinedefault/BuiltOutline;", "withColor", "(I)Lrtx/kimiko/utils/render/render2d/outline/outlinedefault/BuiltOutline;", "Lnet/minecraft/DrawContext;", "graphics", "", "render", "(Lnet/minecraft/DrawContext;)V", "", "visible", "()Z", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "()I", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "copy", "(FFFFFFFFFIIIIFFFFFFFFFFFFFF)Lrtx/kimiko/utils/render/render2d/outline/outlinedefault/BuiltOutline;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "I", "Companion", "rtx.kimiko:kimiko"})
public final class BuiltOutline {
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
    private final float thickness;
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
    private final float scissorCos;
    private final float scissorSin;
    private final float paletteStrength;
    private final float colorOffset;
    private final float scissorFade;
    public static final float DEFAULT_SMOOTHNESS = 0.5f;
    public static final int DEFAULT_COLOR = -1;

    public BuiltOutline(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, float thickness, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, float smoothness, float scissorX, float scissorY, float scissorWidth, float scissorHeight, float scissorRadiusTopLeft, float scissorRadiusTopRight, float scissorRadiusBottomRight, float scissorRadiusBottomLeft, float scissorCos, float scissorSin, float paletteStrength, float colorOffset, float scissorFade) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radiusTopLeft = radiusTopLeft;
        this.radiusTopRight = radiusTopRight;
        this.radiusBottomRight = radiusBottomRight;
        this.radiusBottomLeft = radiusBottomLeft;
        this.thickness = thickness;
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
        this.scissorCos = scissorCos;
        this.scissorSin = scissorSin;
        this.paletteStrength = paletteStrength;
        this.colorOffset = colorOffset;
        this.scissorFade = scissorFade;
    }

    public /* synthetic */ BuiltOutline(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int n, int n2, int n3, int n4, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, float f20, float f21, float f22, float f23, int n5, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, f5, f6, f7, f8, f9, n, n2, n3, n4, f10, f11, f12, f13, f14, f15, f16, f17, f18, f19, f20, (n5 & 0x1000000) != 0 ? 0.0f : f21, (n5 & 0x2000000) != 0 ? 0.0f : f22, (n5 & 0x4000000) != 0 ? 0.0f : f23);
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

    @JvmName(name="thickness")
    public final float thickness() {
        return this.thickness;
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

    @JvmName(name="scissorCos")
    public final float scissorCos() {
        return this.scissorCos;
    }

    @JvmName(name="scissorSin")
    public final float scissorSin() {
        return this.scissorSin;
    }

    @JvmName(name="paletteStrength")
    public final float paletteStrength() {
        return this.paletteStrength;
    }

    @JvmName(name="colorOffset")
    public final float colorOffset() {
        return this.colorOffset;
    }

    @JvmName(name="scissorFade")
    public final float scissorFade() {
        return this.scissorFade;
    }

    @JvmOverloads
    @NotNull
    public final BuiltOutline withPaletteGradient(float offset, float strength) {
        return BuiltOutline.copy$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0, 0, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, strength, offset, 0.0f, 0x4FFFFFF, null);
    }

    public static /* synthetic */ BuiltOutline withPaletteGradient$default(BuiltOutline builtOutline, float f, float f2, int n, Object object) {
        if ((n & 2) != 0) {
            f2 = 1.0f;
        }
        return builtOutline.withPaletteGradient(f, f2);
    }

    public BuiltOutline(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, float thickness, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, float smoothness) {
        this(x, y, width, height, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, thickness, colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft, smoothness, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0x7000000, null);
    }

    public BuiltOutline(float x, float y, float width, float height, float radius, float thickness, int color) {
        this(x, y, width, height, radius, radius, radius, radius, thickness, color, color, color, color, 0.5f);
    }

    public BuiltOutline(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, float thickness, int color) {
        this(x, y, width, height, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, thickness, color, color, color, color, 0.5f);
    }

    @NotNull
    public final BuiltOutline withSmoothness(float smoothness) {
        return new BuiltOutline(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.thickness, this.colorTopLeft, this.colorTopRight, this.colorBottomRight, this.colorBottomLeft, smoothness);
    }

    @NotNull
    public final BuiltOutline withColor(int color) {
        return new BuiltOutline(this.x, this.y, this.width, this.height, this.radiusTopLeft, this.radiusTopRight, this.radiusBottomRight, this.radiusBottomLeft, this.thickness, color, color, color, color, this.smoothness);
    }

    public final void render(@Nullable DrawContext graphics) {
        if (!this.visible()) {
            return;
        }
        EngineFrame.submitWith(graphics, (Matrix3x2f pose) -> new DefaultOutlineRenderState(pose, DefaultOutlineBatch.normalize(this), ScissorUtil.current()));
    }

    public final boolean visible() {
        int colors = this.colorTopLeft | this.colorTopRight | this.colorBottomRight | this.colorBottomLeft;
        return this.width > 0.0f && this.height > 0.0f && this.thickness > 0.0f && colors >>> 24 != 0;
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

    public final float component9() {
        return this.thickness;
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
        return this.smoothness;
    }

    public final float component15() {
        return this.scissorX;
    }

    public final float component16() {
        return this.scissorY;
    }

    public final float component17() {
        return this.scissorWidth;
    }

    public final float component18() {
        return this.scissorHeight;
    }

    public final float component19() {
        return this.scissorRadiusTopLeft;
    }

    public final float component20() {
        return this.scissorRadiusTopRight;
    }

    public final float component21() {
        return this.scissorRadiusBottomRight;
    }

    public final float component22() {
        return this.scissorRadiusBottomLeft;
    }

    public final float component23() {
        return this.scissorCos;
    }

    public final float component24() {
        return this.scissorSin;
    }

    public final float component25() {
        return this.paletteStrength;
    }

    public final float component26() {
        return this.colorOffset;
    }

    public final float component27() {
        return this.scissorFade;
    }

    @NotNull
    public final BuiltOutline copy(float x, float y, float width, float height, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft, float thickness, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, float smoothness, float scissorX, float scissorY, float scissorWidth, float scissorHeight, float scissorRadiusTopLeft, float scissorRadiusTopRight, float scissorRadiusBottomRight, float scissorRadiusBottomLeft, float scissorCos, float scissorSin, float paletteStrength, float colorOffset, float scissorFade) {
        return new BuiltOutline(x, y, width, height, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, thickness, colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft, smoothness, scissorX, scissorY, scissorWidth, scissorHeight, scissorRadiusTopLeft, scissorRadiusTopRight, scissorRadiusBottomRight, scissorRadiusBottomLeft, scissorCos, scissorSin, paletteStrength, colorOffset, scissorFade);
    }

    public static /* synthetic */ BuiltOutline copy$default(BuiltOutline builtOutline, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int n, int n2, int n3, int n4, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, float f20, float f21, float f22, float f23, int n5, Object object) {
        if ((n5 & 1) != 0) {
            f = builtOutline.x;
        }
        if ((n5 & 2) != 0) {
            f2 = builtOutline.y;
        }
        if ((n5 & 4) != 0) {
            f3 = builtOutline.width;
        }
        if ((n5 & 8) != 0) {
            f4 = builtOutline.height;
        }
        if ((n5 & 0x10) != 0) {
            f5 = builtOutline.radiusTopLeft;
        }
        if ((n5 & 0x20) != 0) {
            f6 = builtOutline.radiusTopRight;
        }
        if ((n5 & 0x40) != 0) {
            f7 = builtOutline.radiusBottomRight;
        }
        if ((n5 & 0x80) != 0) {
            f8 = builtOutline.radiusBottomLeft;
        }
        if ((n5 & 0x100) != 0) {
            f9 = builtOutline.thickness;
        }
        if ((n5 & 0x200) != 0) {
            n = builtOutline.colorTopLeft;
        }
        if ((n5 & 0x400) != 0) {
            n2 = builtOutline.colorTopRight;
        }
        if ((n5 & 0x800) != 0) {
            n3 = builtOutline.colorBottomRight;
        }
        if ((n5 & 0x1000) != 0) {
            n4 = builtOutline.colorBottomLeft;
        }
        if ((n5 & 0x2000) != 0) {
            f10 = builtOutline.smoothness;
        }
        if ((n5 & 0x4000) != 0) {
            f11 = builtOutline.scissorX;
        }
        if ((n5 & 0x8000) != 0) {
            f12 = builtOutline.scissorY;
        }
        if ((n5 & 0x10000) != 0) {
            f13 = builtOutline.scissorWidth;
        }
        if ((n5 & 0x20000) != 0) {
            f14 = builtOutline.scissorHeight;
        }
        if ((n5 & 0x40000) != 0) {
            f15 = builtOutline.scissorRadiusTopLeft;
        }
        if ((n5 & 0x80000) != 0) {
            f16 = builtOutline.scissorRadiusTopRight;
        }
        if ((n5 & 0x100000) != 0) {
            f17 = builtOutline.scissorRadiusBottomRight;
        }
        if ((n5 & 0x200000) != 0) {
            f18 = builtOutline.scissorRadiusBottomLeft;
        }
        if ((n5 & 0x400000) != 0) {
            f19 = builtOutline.scissorCos;
        }
        if ((n5 & 0x800000) != 0) {
            f20 = builtOutline.scissorSin;
        }
        if ((n5 & 0x1000000) != 0) {
            f21 = builtOutline.paletteStrength;
        }
        if ((n5 & 0x2000000) != 0) {
            f22 = builtOutline.colorOffset;
        }
        if ((n5 & 0x4000000) != 0) {
            f23 = builtOutline.scissorFade;
        }
        return builtOutline.copy(f, f2, f3, f4, f5, f6, f7, f8, f9, n, n2, n3, n4, f10, f11, f12, f13, f14, f15, f16, f17, f18, f19, f20, f21, f22, f23);
    }

    @NotNull
    public String toString() {
        return "BuiltOutline(x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", radiusTopLeft=" + this.radiusTopLeft + ", radiusTopRight=" + this.radiusTopRight + ", radiusBottomRight=" + this.radiusBottomRight + ", radiusBottomLeft=" + this.radiusBottomLeft + ", thickness=" + this.thickness + ", colorTopLeft=" + this.colorTopLeft + ", colorTopRight=" + this.colorTopRight + ", colorBottomRight=" + this.colorBottomRight + ", colorBottomLeft=" + this.colorBottomLeft + ", smoothness=" + this.smoothness + ", scissorX=" + this.scissorX + ", scissorY=" + this.scissorY + ", scissorWidth=" + this.scissorWidth + ", scissorHeight=" + this.scissorHeight + ", scissorRadiusTopLeft=" + this.scissorRadiusTopLeft + ", scissorRadiusTopRight=" + this.scissorRadiusTopRight + ", scissorRadiusBottomRight=" + this.scissorRadiusBottomRight + ", scissorRadiusBottomLeft=" + this.scissorRadiusBottomLeft + ", scissorCos=" + this.scissorCos + ", scissorSin=" + this.scissorSin + ", paletteStrength=" + this.paletteStrength + ", colorOffset=" + this.colorOffset + ", scissorFade=" + this.scissorFade + ")";
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
        result = result * 31 + Float.hashCode(this.thickness);
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
        result = result * 31 + Float.hashCode(this.scissorCos);
        result = result * 31 + Float.hashCode(this.scissorSin);
        result = result * 31 + Float.hashCode(this.paletteStrength);
        result = result * 31 + Float.hashCode(this.colorOffset);
        result = result * 31 + Float.hashCode(this.scissorFade);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BuiltOutline)) {
            return false;
        }
        BuiltOutline builtOutline = (BuiltOutline)other;
        if (Float.compare(this.x, builtOutline.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, builtOutline.y) != 0) {
            return false;
        }
        if (Float.compare(this.width, builtOutline.width) != 0) {
            return false;
        }
        if (Float.compare(this.height, builtOutline.height) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopLeft, builtOutline.radiusTopLeft) != 0) {
            return false;
        }
        if (Float.compare(this.radiusTopRight, builtOutline.radiusTopRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomRight, builtOutline.radiusBottomRight) != 0) {
            return false;
        }
        if (Float.compare(this.radiusBottomLeft, builtOutline.radiusBottomLeft) != 0) {
            return false;
        }
        if (Float.compare(this.thickness, builtOutline.thickness) != 0) {
            return false;
        }
        if (this.colorTopLeft != builtOutline.colorTopLeft) {
            return false;
        }
        if (this.colorTopRight != builtOutline.colorTopRight) {
            return false;
        }
        if (this.colorBottomRight != builtOutline.colorBottomRight) {
            return false;
        }
        if (this.colorBottomLeft != builtOutline.colorBottomLeft) {
            return false;
        }
        if (Float.compare(this.smoothness, builtOutline.smoothness) != 0) {
            return false;
        }
        if (Float.compare(this.scissorX, builtOutline.scissorX) != 0) {
            return false;
        }
        if (Float.compare(this.scissorY, builtOutline.scissorY) != 0) {
            return false;
        }
        if (Float.compare(this.scissorWidth, builtOutline.scissorWidth) != 0) {
            return false;
        }
        if (Float.compare(this.scissorHeight, builtOutline.scissorHeight) != 0) {
            return false;
        }
        if (Float.compare(this.scissorRadiusTopLeft, builtOutline.scissorRadiusTopLeft) != 0) {
            return false;
        }
        if (Float.compare(this.scissorRadiusTopRight, builtOutline.scissorRadiusTopRight) != 0) {
            return false;
        }
        if (Float.compare(this.scissorRadiusBottomRight, builtOutline.scissorRadiusBottomRight) != 0) {
            return false;
        }
        if (Float.compare(this.scissorRadiusBottomLeft, builtOutline.scissorRadiusBottomLeft) != 0) {
            return false;
        }
        if (Float.compare(this.scissorCos, builtOutline.scissorCos) != 0) {
            return false;
        }
        if (Float.compare(this.scissorSin, builtOutline.scissorSin) != 0) {
            return false;
        }
        if (Float.compare(this.paletteStrength, builtOutline.paletteStrength) != 0) {
            return false;
        }
        if (Float.compare(this.colorOffset, builtOutline.colorOffset) != 0) {
            return false;
        }
        return Float.compare(this.scissorFade, builtOutline.scissorFade) == 0;
    }

    @JvmOverloads
    @NotNull
    public final BuiltOutline withPaletteGradient(float offset) {
        return BuiltOutline.withPaletteGradient$default(this, offset, 0.0f, 2, null);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/utils/render/render2d/outline/outlinedefault/BuiltOutline.Companion;", "", "<init>", "()V", "", "DEFAULT_SMOOTHNESS", "F", "", "DEFAULT_COLOR", "I", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

