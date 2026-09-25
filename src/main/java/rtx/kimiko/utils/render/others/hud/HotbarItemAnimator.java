/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  net.minecraft.client.item.ItemModelManager
 *  net.minecraft.client.render.item.ItemRenderState
 *  net.minecraft.client.gui.render.state.GuiRenderState
 *  net.minecraft.client.gui.render.state.special.SpecialGuiElementRenderState
 *  net.minecraft.client.render.item.KeyedItemRenderState
 *  net.minecraft.util.HeldItemContext
 *  net.minecraft.util.Hand
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.network.ClientPlayerEntity
 *  net.minecraft.item.ItemDisplayContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fc
 */
package rtx.kimiko.utils.render.others.hud;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.gui.render.state.special.SpecialGuiElementRenderState;
import net.minecraft.client.render.item.KeyedItemRenderState;
import net.minecraft.util.HeldItemContext;
import net.minecraft.util.Hand;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemDisplayContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.hud.HotbarItemPipState;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\bk\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002\u00af\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003JC\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\fH\u0007b\u0002\b\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\u000eH\u0007b\u0002\b\u000f\u00a2\u0006\u0004\b\u0012\u0010\u0003J\u0013\u0010\u0013\u001a\u00020\bH\u0007b\u0002\b\u000f\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\bH\u0007b\u0002\b\u000f\u00a2\u0006\u0004\b\u0015\u0010\u0014J\u001b\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\bH\u0007b\u0002\b\u000f\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\bH\u0007b\u0002\b\u000f\u00a2\u0006\u0004\b\u0019\u0010\u0014J\u001b\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\fH\u0007b\u0002\b\u000f\u00a2\u0006\u0004\b\u001b\u0010\u001cJ5\u0010#\u001a\u0004\u0018\u00010\"2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00062\u0006\u0010!\u001a\u00020\bH\u0007b\u0002\b\u000f\u00a2\u0006\u0004\b#\u0010$JC\u0010*\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020%2\u0006\u0010(\u001a\u00020'2\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u0006H\u0007b\u0002\b\u000f\u00a2\u0006\u0004\b*\u0010+J3\u0010,\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020'2\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u0006H\u0007b\u0002\b\u000f\u00a2\u0006\u0004\b,\u0010-J\u001b\u0010.\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u001dH\u0007b\u0002\b\u000f\u00a2\u0006\u0004\b.\u0010/J\u0017\u00100\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b0\u0010/J\u000f\u00101\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b1\u0010\u0003J'\u00103\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020'2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u00102\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b3\u00104J7\u00106\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010(\u001a\u00020'2\u0006\u00105\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u00102\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b6\u00107J'\u00108\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020'2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u00102\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b8\u00104J\u0017\u00109\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b9\u0010/J\u0017\u0010;\u001a\u00020\b2\u0006\u0010:\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b;\u0010<J\u0017\u0010=\u001a\u00020\b2\u0006\u0010:\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b=\u0010<J\u0017\u0010>\u001a\u00020\b2\u0006\u0010:\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b>\u0010<J\u0017\u0010?\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b?\u0010<J\u001f\u0010@\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b@\u0010AJ\u000f\u0010B\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\bB\u0010\u0003J\u001f\u0010D\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010C\u001a\u00020\bH\u0002\u00a2\u0006\u0004\bD\u0010EJ\u0017\u0010F\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bF\u0010GJ\u0017\u0010I\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020\bH\u0002\u00a2\u0006\u0004\bI\u0010JJ\u0017\u0010K\u001a\u00020\u000e2\u0006\u0010C\u001a\u00020\bH\u0002\u00a2\u0006\u0004\bK\u0010JJ'\u0010O\u001a\u00020\b2\u0006\u0010L\u001a\u00020\b2\u0006\u0010M\u001a\u00020\b2\u0006\u0010N\u001a\u00020\bH\u0002\u00a2\u0006\u0004\bO\u0010PJ/\u0010R\u001a\u00020\b2\u0006\u0010Q\u001a\u00020\b2\u0006\u0010M\u001a\u00020\b2\u0006\u0010C\u001a\u00020\b2\u0006\u0010N\u001a\u00020\bH\u0002\u00a2\u0006\u0004\bR\u0010SJ\u0017\u0010U\u001a\u00020\u00062\u0006\u0010T\u001a\u00020\bH\u0002\u00a2\u0006\u0004\bU\u0010VR\u0014\u0010W\u001a\u00020\u00068\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bW\u0010XR\u0014\u0010Y\u001a\u00020\u00068\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bY\u0010XR\u0014\u0010Z\u001a\u00020\u00068\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bZ\u0010XR\u0014\u0010[\u001a\u00020\u00068\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b[\u0010XR\u0014\u0010\\\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\\\u0010]R\u0014\u0010^\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b^\u0010]R\u0014\u0010_\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b_\u0010XR\u0014\u0010`\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b`\u0010XR\u0014\u0010a\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\ba\u0010XR\u0014\u0010b\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bb\u0010XR\u0014\u0010c\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bc\u0010XR\u0014\u0010d\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bd\u0010XR\u0014\u0010e\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\be\u0010XR\u0014\u0010f\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bf\u0010]R\u0014\u0010g\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bg\u0010]R\u0014\u0010h\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bh\u0010]R\u0014\u0010i\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bi\u0010]R\u0014\u0010j\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bj\u0010]R\u0014\u0010k\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bk\u0010]R\u0014\u0010l\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bl\u0010]R\u0014\u0010m\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bm\u0010]R\u0014\u0010n\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bn\u0010]R\u0014\u0010o\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bo\u0010]R\u0014\u0010p\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bp\u0010]R\u0014\u0010q\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bq\u0010]R\u0014\u0010r\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\br\u0010]R\u0014\u0010s\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bs\u0010]R\u0014\u0010t\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bt\u0010]R\u0014\u0010u\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bu\u0010]R\u0014\u0010v\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bv\u0010]R\u0014\u0010w\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bw\u0010]R\u0014\u0010x\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bx\u0010]R\u0014\u0010y\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\by\u0010]R\u0014\u0010z\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bz\u0010]R\u0014\u0010{\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b{\u0010]R\u0014\u0010|\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b|\u0010]R\u0014\u0010}\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b}\u0010]R\u0014\u0010~\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b~\u0010]R\u0014\u0010\u007f\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u007f\u0010]R\u0016\u0010\u0080\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0080\u0001\u0010]R\u0016\u0010\u0081\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0081\u0001\u0010]R\u0016\u0010\u0082\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0082\u0001\u0010]R\u0016\u0010\u0083\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0083\u0001\u0010]R\u0016\u0010\u0084\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0084\u0001\u0010]R\u0016\u0010\u0085\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0085\u0001\u0010]R\u0016\u0010\u0086\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0086\u0001\u0010]R\u0016\u0010\u0087\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0087\u0001\u0010]R\u0016\u0010\u0088\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0088\u0001\u0010]R\u0016\u0010\u0089\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0089\u0001\u0010]R\u0016\u0010\u008a\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u008a\u0001\u0010]R\u0016\u0010\u008b\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u008b\u0001\u0010]R\u0016\u0010\u008c\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u008c\u0001\u0010]R\u0016\u0010\u008d\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u008d\u0001\u0010]R\u0016\u0010\u008e\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u008e\u0001\u0010]R\u0016\u0010\u008f\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u008f\u0001\u0010]R\u0016\u0010\u0090\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0090\u0001\u0010]R\u0016\u0010\u0091\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0091\u0001\u0010]R\u0016\u0010\u0092\u0001\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0092\u0001\u0010]R\u001f\u0010\u0095\u0001\u001a\n\u0012\u0005\u0012\u00030\u0094\u00010\u0093\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0095\u0001\u0010\u0096\u0001R\u0019\u0010\u0097\u0001\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0097\u0001\u0010\u0098\u0001R\u0019\u0010\u0099\u0001\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0099\u0001\u0010\u0098\u0001R\u0017\u0010\u009a\u0001\u001a\u00020\"8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u009a\u0001\u0010\u009b\u0001R\u0019\u0010\u009c\u0001\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009c\u0001\u0010\u0098\u0001R\u001a\u0010\u009e\u0001\u001a\u00030\u009d\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009e\u0001\u0010\u009f\u0001R\u0018\u0010\u00a0\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a0\u0001\u0010]R\u0018\u0010\u00a1\u0001\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a1\u0001\u0010XR\u0018\u0010\u00a2\u0001\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a2\u0001\u0010XR\u001c\u0010\u00a4\u0001\u001a\u0005\u0018\u00010\u00a3\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a4\u0001\u0010\u00a5\u0001R\u0019\u0010\u00a6\u0001\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a6\u0001\u0010\u0098\u0001R\u0019\u0010\u00a7\u0001\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u00a7\u0001\u0010\u0098\u0001R\u0018\u0010\u00a8\u0001\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a8\u0001\u0010XR\u0018\u0010\u00a9\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00a9\u0001\u0010]R\u0018\u0010\u00aa\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00aa\u0001\u0010]R\u0018\u0010«\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b«\u0001\u0010]R\u0018\u0010\u00ac\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00ac\u0001\u0010]R\u0018\u0010\u00ad\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00ad\u0001\u0010]R\u0018\u0010\u00ae\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u00ae\u0001\u0010]R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0007\u0010XR\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\t\u0010]R\u0016\u0010\n\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\n\u0010]R\u0016\u0010\u000b\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000b\u0010]R\u0017\u0010\r\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\r\u0010\u0098\u0001\u00a8\u0006\u00b0\u0001"}, d2={"Lrtx/kimiko/utils/render/others/hud/HotbarItemAnimator;", "", "<init>", "()V", "Lnet/minecraft/PlayerEntity;", "player", "", "centerX", "", "pitch", "liftPixels", "tiltStrength", "", "useResponse", "", "Lkotlin/jvm/JvmStatic;", "beginFrame", "(Lnet/minecraft/PlayerEntity;IFFFZ)V", "reset", "panelWidth", "()F", "panelLeft", "slot", "slotCenterX", "(F)F", "selectorCenterX", "left", "offhandBoxX", "(Z)F", "Lnet/minecraft/DrawContext;", "graphics", "slotX", "slotY", "baseRadius", "", "highlightFrame", "(Lnet/minecraft/DrawContext;IIF)[F", "Lnet/minecraft/LivingEntity;", "entity", "Lnet/minecraft/ItemStack;", "stack", "seed", "captureItem", "(Lnet/minecraft/DrawContext;Lnet/minecraft/LivingEntity;Lnet/minecraft/ItemStack;III)Z", "captureDecorations", "(Lnet/minecraft/DrawContext;Lnet/minecraft/ItemStack;II)Z", "renderOverlay", "(Lnet/minecraft/DrawContext;)V", "submitItems", "drawDecorations", "top", "drawBar", "(Lnet/minecraft/ItemStack;FF)V", "partialTick", "drawCooldown", "(Lnet/minecraft/PlayerEntity;Lnet/minecraft/ItemStack;FFF)V", "drawCount", "drawIndicator", "index", "itemCenterX", "(I)F", "liftFor", "tiltFor", "swayWeight", "entryIndex", "(Lnet/minecraft/DrawContext;I)I", "clearDeferred", "step", "updateUse", "(Lnet/minecraft/PlayerEntity;F)V", "updateSwing", "(Lnet/minecraft/PlayerEntity;)V", "amplitude", "startPunch", "(F)V", "updatePunch", "value", "target", "speed", "harp", "(FFF)F", "current", "approach", "(FFFF)F", "alpha", "white", "(F)I", "SLOTS", "I", "OFFHAND", "ENTRIES", "ITEM_SIZE", "CELL_SIZE", "F", "OFFHAND_BOX", "PIP_BOX", "PIP_HALF", "VANILLA_STEP", "VANILLA_ORIGIN", "SLOT_INSET", "VANILLA_OFFHAND_LEFT", "VANILLA_OFFHAND_RIGHT", "PANEL_PADDING", "OFFHAND_GAP", "OFFHAND_ITEM_INSET", "TRACK_SPEED", "TRACK_DAMPING", "TRACK_SNAP", "MAX_FRAME_MILLIS", "SWAY_RANGE", "SWAY_LIFT", "SWAY_TILT", "TILT_EPSILON", "PUNCH_DECAY", "PUNCH_FREQUENCY", "PUNCH_LIFE", "PUNCH_USE", "PUNCH_RELEASE", "PUNCH_SWING", "PUNCH_ITEM_CHANGE", "PUNCH_LIFT_PIXELS", "PUNCH_TILT_DEGREES", "USE_ATTACK_SPEED", "USE_RELEASE_SPEED", "USE_PRESS_PIXELS", "FADE_SPEED", "HIGHLIGHT_SIZE", "COUNT_SIZE", "COUNT_OVERHANG", "COUNT_SHADOW", "BAR_INSET", "BAR_WIDTH", "BAR_BOTTOM", "BAR_HEIGHT", "COOLDOWN_ALPHA", "COOLDOWN_RADIUS", "DASH_WIDTH", "DASH_HEIGHT", "DASH_BASELINE", "DASH_HALO", "STREAK_HEIGHT", "STREAK_REFERENCE", "DASH_USE_GROWTH", "DASH_PUNCH_GROWTH", "DASH_ALPHA", "DASH_HALO_ALPHA", "STREAK_ALPHA", "", "Lrtx/kimiko/utils/render/others/hud/HotbarItemAnimator$DeferredSlot;", "deferred", "[Lrtx/kimiko/utils/render/others/hud/HotbarItemAnimator$DeferredSlot;", "hasDeferred", "Z", "offhandLeft", "frame", "[F", "active", "", "lastNanos", "J", "fade", "selected", "lastSelected", "Lnet/minecraft/Item;", "lastItem", "Lnet/minecraft/Item;", "lastUsing", "lastSwinging", "lastSwingTime", "usePower", "punchAmplitude", "punchElapsed", "punch", "trackPixels", "trackTarget", "DeferredSlot", "rtx.kimiko:kimiko"})
public final class HotbarItemAnimator {
    @NotNull
    public static final HotbarItemAnimator INSTANCE = new HotbarItemAnimator();
    public static final int SLOTS = 9;
    public static final int OFFHAND = 9;
    public static final int ENTRIES = 10;
    public static final int ITEM_SIZE = 16;
    public static final float CELL_SIZE = 20.0f;
    public static final float OFFHAND_BOX = 24.0f;
    private static final int PIP_BOX = 24;
    private static final int PIP_HALF = 12;
    private static final int VANILLA_STEP = 20;
    private static final int VANILLA_ORIGIN = -90;
    private static final int SLOT_INSET = 2;
    private static final int VANILLA_OFFHAND_LEFT = -117;
    private static final int VANILLA_OFFHAND_RIGHT = 101;
    private static final float PANEL_PADDING = 1.0f;
    private static final float OFFHAND_GAP = 6.0f;
    private static final float OFFHAND_ITEM_INSET = 4.0f;
    private static final float TRACK_SPEED = 0.055f;
    private static final float TRACK_DAMPING = 2.0f;
    private static final float TRACK_SNAP = 0.001f;
    private static final float MAX_FRAME_MILLIS = 50.0f;
    private static final float SWAY_RANGE = 34.0f;
    private static final float SWAY_LIFT = 2.0f;
    private static final float SWAY_TILT = 5.0f;
    private static final float TILT_EPSILON = 0.01f;
    private static final float PUNCH_DECAY = 9.5f;
    private static final float PUNCH_FREQUENCY = 33.0f;
    private static final float PUNCH_LIFE = 0.65f;
    private static final float PUNCH_USE = 0.18f;
    private static final float PUNCH_RELEASE = 0.14f;
    private static final float PUNCH_SWING = -0.1f;
    private static final float PUNCH_ITEM_CHANGE = 0.26f;
    private static final float PUNCH_LIFT_PIXELS = 12.0f;
    private static final float PUNCH_TILT_DEGREES = 20.0f;
    private static final float USE_ATTACK_SPEED = 24.0f;
    private static final float USE_RELEASE_SPEED = 11.0f;
    private static final float USE_PRESS_PIXELS = 0.6f;
    private static final float FADE_SPEED = 9.0f;
    private static final float HIGHLIGHT_SIZE = 16.0f;
    private static final float COUNT_SIZE = 7.0f;
    private static final float COUNT_OVERHANG = 1.0f;
    private static final float COUNT_SHADOW = 0.45f;
    private static final float BAR_INSET = 2.0f;
    private static final float BAR_WIDTH = 13.0f;
    private static final float BAR_BOTTOM = 3.0f;
    private static final float BAR_HEIGHT = 1.6f;
    private static final float COOLDOWN_ALPHA = 0.5f;
    private static final float COOLDOWN_RADIUS = 2.0f;
    private static final float DASH_WIDTH = 10.0f;
    private static final float DASH_HEIGHT = 1.2f;
    private static final float DASH_BASELINE = 3.5f;
    private static final float DASH_HALO = 1.6f;
    private static final float STREAK_HEIGHT = 1.6f;
    private static final float STREAK_REFERENCE = 16.0f;
    private static final float DASH_USE_GROWTH = 0.35f;
    private static final float DASH_PUNCH_GROWTH = 0.55f;
    private static final float DASH_ALPHA = 0.95f;
    private static final float DASH_HALO_ALPHA = 0.13f;
    private static final float STREAK_ALPHA = 0.55f;
    @NotNull
    private static final DeferredSlot[] deferred;
    private static boolean hasDeferred;
    private static boolean offhandLeft;
    @NotNull
    private static final float[] frame;
    private static boolean active;
    private static long lastNanos;
    private static float fade;
    private static int selected;
    private static int lastSelected;
    @Nullable
    private static Item lastItem;
    private static boolean lastUsing;
    private static boolean lastSwinging;
    private static int lastSwingTime;
    private static float usePower;
    private static float punchAmplitude;
    private static float punchElapsed;
    private static float punch;
    private static float trackPixels;
    private static float trackTarget;
    private static int centerX;
    private static float pitch;
    private static float liftPixels;
    private static float tiltStrength;
    private static boolean useResponse;

    private HotbarItemAnimator() {
    }

    @JvmStatic
    public static final void beginFrame(@NotNull PlayerEntity player, int centerX, float pitch, float liftPixels, float tiltStrength, boolean useResponse) {
        Intrinsics.checkNotNullParameter((Object)player, (String)"player");
        HotbarItemAnimator.centerX = centerX;
        HotbarItemAnimator.pitch = Math.max(16.0f, pitch);
        HotbarItemAnimator.liftPixels = Math.max(0.0f, liftPixels);
        HotbarItemAnimator.tiltStrength = Math.max(0.0f, tiltStrength);
        HotbarItemAnimator.useResponse = useResponse;
        long now = System.nanoTime();
        float millis = lastNanos == 0L ? 16.666666f : (float)((double)(now - lastNanos) / 1000000.0);
        lastNanos = now;
        float frameMillis = RangesKt.coerceIn((float)millis, (float)0.0f, (float)50.0f);
        float step = frameMillis / 1000.0f;
        selected = RangesKt.coerceIn((int)player.getInventory().getSelectedSlot(), (int)0, (int)8);
        trackTarget = selected * 20;
        Item item2 = player.getInventory().getStack(selected).getItem();
        Intrinsics.checkNotNullExpressionValue((Object)item2, (String)"getItem(...)");
        Item item = item2;
        if (lastSelected < 0) {
            lastSelected = selected;
            lastItem = item;
            trackPixels = trackTarget;
        } else {
            if (lastSelected != selected) {
                lastSelected = selected;
                lastItem = item;
            } else if (lastItem != item) {
                lastItem = item;
                INSTANCE.startPunch(0.26f);
            }
            trackPixels = INSTANCE.harp(trackPixels, trackTarget, frameMillis * 0.055f / 2.0f);
        }
        INSTANCE.updateUse(player, step);
        INSTANCE.updateSwing(player);
        INSTANCE.updatePunch(step);
        fade = INSTANCE.approach(fade, 1.0f, step, 9.0f);
        INSTANCE.clearDeferred();
        active = true;
    }

    @JvmStatic
    public static final void reset() {
        active = false;
        lastNanos = 0L;
        fade = 0.0f;
        lastSelected = -1;
        lastItem = null;
        lastUsing = false;
        lastSwinging = false;
        lastSwingTime = 0;
        usePower = 0.0f;
        punchAmplitude = 0.0f;
        punchElapsed = 0.65f;
        punch = 0.0f;
        trackPixels = 0.0f;
        trackTarget = 0.0f;
        for (DeferredSlot entry : deferred) {
            entry.clear();
        }
        hasDeferred = false;
    }

    @JvmStatic
    public static final float panelWidth() {
        return (float)9 * pitch + 2.0f;
    }

    @JvmStatic
    public static final float panelLeft() {
        return (float)centerX - HotbarItemAnimator.panelWidth() * 0.5f;
    }

    @JvmStatic
    public static final float slotCenterX(float slot) {
        return (float)centerX + (slot - 4.0f) * pitch;
    }

    @JvmStatic
    public static final float selectorCenterX() {
        return HotbarItemAnimator.slotCenterX(trackPixels / (float)20);
    }

    @JvmStatic
    public static final float offhandBoxX(boolean left) {
        return left ? HotbarItemAnimator.panelLeft() - 6.0f - 24.0f : HotbarItemAnimator.panelLeft() + HotbarItemAnimator.panelWidth() + 6.0f;
    }

    @JvmStatic
    @Nullable
    public static final float[] highlightFrame(@NotNull DrawContext graphics, int slotX, int slotY, float baseRadius) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        int index = INSTANCE.entryIndex(graphics, slotX);
        if (index < 0) {
            return null;
        }
        float cx = INSTANCE.itemCenterX(index);
        float cy = (float)slotY + 8.0f;
        HotbarItemAnimator.frame[0] = cx - 8.0f;
        HotbarItemAnimator.frame[1] = cy - 8.0f;
        HotbarItemAnimator.frame[2] = 16.0f;
        HotbarItemAnimator.frame[3] = 16.0f;
        HotbarItemAnimator.frame[4] = Math.min(baseRadius, 8.0f);
        return frame;
    }

    @JvmStatic
    public static final boolean captureItem(@NotNull DrawContext graphics, @NotNull LivingEntity entity, @NotNull ItemStack stack, int slotX, int slotY, int seed) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        Intrinsics.checkNotNullParameter((Object)entity, (String)"entity");
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        int index = INSTANCE.entryIndex(graphics, slotX);
        if (index < 0) {
            return false;
        }
        DeferredSlot entry = deferred[index];
        entry.setEntity(entity);
        entry.setStack(stack);
        entry.setY(slotY);
        entry.setSeed(seed);
        hasDeferred = true;
        return true;
    }

    @JvmStatic
    public static final boolean captureDecorations(@NotNull DrawContext graphics, @NotNull ItemStack stack, int slotX, int slotY) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        int index = INSTANCE.entryIndex(graphics, slotX);
        if (index < 0) {
            return false;
        }
        DeferredSlot entry = deferred[index];
        entry.setDecorationStack(stack);
        entry.setY(slotY);
        hasDeferred = true;
        return true;
    }

    @JvmStatic
    public static final void renderOverlay(@NotNull DrawContext graphics) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        if (!active) {
            return;
        }
        graphics.createNewRootLayer();
        INSTANCE.submitItems(graphics);
        graphics.createNewRootLayer();
        float nativeScale = (float)Render2DCoordinateSpace.guiScale() / Render2DCoordinateSpace.designGuiScale();
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().scale(nativeScale);
        Render2D.beginFrame(graphics);
        INSTANCE.drawDecorations();
        INSTANCE.drawIndicator(graphics);
        Render2D.flush();
        graphics.getMatrices().popMatrix();
        INSTANCE.clearDeferred();
    }

    private final void submitItems(DrawContext graphics) {
        if (!hasDeferred) {
            return;
        }
        ItemModelManager itemModelManager2 = MinecraftClient.getInstance().getItemModelManager();
        Intrinsics.checkNotNullExpressionValue((Object)itemModelManager2, (String)"getItemModelResolver(...)");
        ItemModelManager resolver = itemModelManager2;
        Intrinsics.checkNotNull((Object)graphics, (String)"null cannot be cast to non-null type mixin.accessor.GuiGraphicsExtractorAccessor");
        GuiRenderState renderState = ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState();
        for (int index = 0; index < 10; ++index) {
            DeferredSlot entry = deferred[index];
            LivingEntity entity = entry.getEntity();
            ItemStack stack = entry.getStack();
            if (entity == null || stack == null) continue;
            KeyedItemRenderState itemState = new KeyedItemRenderState();
            resolver.clearAndUpdate((ItemRenderState)itemState, stack, ItemDisplayContext.GUI, entity.getEntityWorld(), (HeldItemContext)entity, entry.getSeed());
            if (itemState.isEmpty()) continue;
            float cx = this.itemCenterX(index);
            Matrix3x2f pose = new Matrix3x2f((Matrix3x2fc)graphics.getMatrices()).translate(cx - (float)Math.round(cx), -this.liftFor(index));
            int x0 = Math.round(cx) - 12;
            int y0 = entry.getY() + 8 - 12;
            float f = this.tiltFor(index);
            Intrinsics.checkNotNull((Object)pose);
            renderState.addSpecialElement((SpecialGuiElementRenderState)new HotbarItemPipState(index, itemState, 16.0f, f, pose, x0, y0, x0 + 24, y0 + 24, null));
        }
    }

    private final void drawDecorations() {
        if (!hasDeferred) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        ClientPlayerEntity player = mc.player;
        float partialTick = mc.getRenderTickCounter().getTickProgress(true);
        for (int index = 0; index < 10; ++index) {
            DeferredSlot entry = deferred[index];
            ItemStack itemStack2 = entry.getDecorationStack();
            if (itemStack2 == null && (itemStack2 = entry.getStack()) == null) continue;
            ItemStack stack = itemStack2;
            float left = this.itemCenterX(index) - 8.0f;
            float top = (float)entry.getY() - this.liftFor(index);
            this.drawBar(stack, left, top);
            if (player != null) {
                this.drawCooldown((PlayerEntity)player, stack, partialTick, left, top);
            }
            this.drawCount(stack, left, top);
        }
    }

    private final void drawBar(ItemStack stack, float left, float top) {
        if (!stack.isItemBarVisible()) {
            return;
        }
        float x = left + 2.0f;
        float y = top + (float)16 - 3.0f - 1.6f;
        float fill = 13.0f * ((float)RangesKt.coerceIn((int)stack.getItemBarStep(), (int)0, (int)13) / 13.0f);
        Render2D.rect(x, y, 13.0f, 1.6f, 0.8f, -654311424);
        if (fill > 0.01f) {
            Render2D.rect(x, y, fill, 1.6f, 0.8f, 0xFF000000 | stack.getItemBarColor() & 0xFFFFFF);
        }
    }

    private final void drawCooldown(PlayerEntity player, ItemStack stack, float partialTick, float left, float top) {
        float percent = player.getItemCooldownManager().getCooldownProgress(stack, partialTick);
        if (percent <= 0.0f) {
            return;
        }
        float height = (float)16 * RangesKt.coerceIn((float)percent, (float)0.0f, (float)1.0f);
        Render2D.rect(left, top + (float)16 - height, 16.0f, height, 2.0f, this.white(0.5f));
    }

    private final void drawCount(ItemStack stack, float left, float top) {
        int count = stack.getCount();
        if (count <= 1) {
            return;
        }
        String text = String.valueOf(count);
        float width = Fonts.SEMIBOLD.width(text, 7.0f);
        float x = left + (float)16 + 1.0f - width;
        float y = top + (float)16 + 1.0f - 7.0f;
        Fonts.SEMIBOLD.draw(text, x + 0.45f, y + 0.45f, 7.0f, -1291845632);
        Fonts.SEMIBOLD.draw(text, x, y, 7.0f, -1);
    }

    private final void drawIndicator(DrawContext graphics) {
        float visibility = fade;
        if (visibility <= 0.01f) {
            return;
        }
        float centerY = (float)graphics.getScaledWindowHeight() - 3.5f;
        float current = HotbarItemAnimator.selectorCenterX();
        float target = HotbarItemAnimator.slotCenterX(selected);
        float kick = useResponse ? Math.max(0.0f, punch) : 0.0f;
        float growth = 1.0f + 0.35f * usePower + 0.55f * kick;
        float height = 1.2f * growth;
        float width = 10.0f * growth;
        float travel = Math.min(1.0f, Math.abs(trackTarget - trackPixels) / 16.0f);
        if (travel > 0.01f) {
            float left = Math.min(current, target);
            float right = Math.max(current, target);
            int head = this.white(0.55f * travel * visibility);
            int tail = this.white(0.0f);
            int leftColor = current > target ? tail : head;
            int rightColor = current > target ? head : tail;
            Render2D.rect(left, centerY - 0.8f, right - left, 1.6f, 0.8f, leftColor, rightColor, rightColor, leftColor);
        }
        Render2D.rect(current - width * 0.5f - 1.6f, centerY - height * 0.5f - 1.6f, width + 3.2f, height + 3.2f, (height + 3.2f) * 0.5f, this.white(0.13f * visibility));
        Render2D.rect(current - width * 0.5f, centerY - height * 0.5f, width, height, height * 0.5f, this.white(0.95f * visibility));
    }

    private final float itemCenterX(int index) {
        if (index == 9) {
            return HotbarItemAnimator.offhandBoxX(offhandLeft) + 4.0f + 8.0f;
        }
        return HotbarItemAnimator.slotCenterX(index);
    }

    private final float liftFor(int index) {
        if (index == 9) {
            return 0.0f;
        }
        float lift = this.swayWeight(index) * liftPixels;
        if (index == selected && useResponse) {
            lift += punch * 12.0f;
            lift -= usePower * 0.6f;
        }
        return lift;
    }

    private final float tiltFor(int index) {
        if (index == 9) {
            return 0.0f;
        }
        float offset = trackPixels - (float)(index * 20);
        float tilt = 0.0f;
        if (Math.abs(offset) > 0.001f) {
            tilt = -offset * 5.0f * tiltStrength * this.swayWeight(index);
        }
        if (index == selected && useResponse) {
            tilt += punch * 20.0f;
        }
        return Math.abs(tilt) < 0.01f ? 0.0f : tilt;
    }

    private final float swayWeight(int slot) {
        float distance = Math.abs((float)(slot * 20) - trackPixels);
        if (distance >= 34.0f) {
            return 0.0f;
        }
        float falloff = 1.0f - distance / 34.0f;
        return falloff * falloff * falloff;
    }

    private final int entryIndex(DrawContext graphics, int slotX) {
        if (!active) {
            return -1;
        }
        int vanillaCenter = graphics.getScaledWindowWidth() / 2;
        if (slotX == vanillaCenter + -117) {
            offhandLeft = true;
            return 9;
        }
        if (slotX == vanillaCenter + 101) {
            offhandLeft = false;
            return 9;
        }
        int offset = slotX - (vanillaCenter + -90 + 2);
        if (offset < 0 || offset % 20 != 0) {
            return -1;
        }
        int slot = offset / 20;
        return slot < 9 ? slot : -1;
    }

    private final void clearDeferred() {
        if (!hasDeferred) {
            return;
        }
        for (DeferredSlot entry : deferred) {
            entry.clear();
        }
        hasDeferred = false;
    }

    private final void updateUse(PlayerEntity player, float step) {
        boolean using;
        boolean bl = using = player.isUsingItem() && player.getActiveHand() == Hand.MAIN_HAND;
        if (using != lastUsing) {
            this.startPunch(using ? 0.18f : 0.14f);
            lastUsing = using;
        }
        usePower = this.approach(usePower, using ? 1.0f : 0.0f, step, using ? 24.0f : 11.0f);
    }

    private final void updateSwing(PlayerEntity player) {
        boolean swinging;
        boolean bl = swinging = player.handSwinging && player.preferredHand == Hand.MAIN_HAND;
        if (swinging && (!lastSwinging || player.handSwingTicks < lastSwingTime)) {
            this.startPunch(-0.1f);
        }
        lastSwinging = swinging;
        lastSwingTime = player.handSwingTicks;
    }

    private final void startPunch(float amplitude) {
        punchAmplitude = amplitude;
        punchElapsed = 0.0f;
    }

    private final void updatePunch(float step) {
        if (punchAmplitude == 0.0f || punchElapsed >= 0.65f) {
            punchAmplitude = 0.0f;
            punch = 0.0f;
            return;
        }
        punch = (punchElapsed += step) >= 0.65f ? 0.0f : punchAmplitude * (float)Math.exp(-punchElapsed * 9.5f) * (float)Math.sin(punchElapsed * 33.0f);
    }

    private final float harp(float value, float target, float speed) {
        float delta = target - value;
        float stride = delta * speed * 0.5f;
        float move = stride > 0.0f ? Math.max(speed, Math.min(delta, stride)) : Math.max(delta, Math.min(-speed * 0.5f, stride));
        float next = value + move;
        if (Math.abs(target - next) < 0.001f || delta > 0.0f && next > target || delta < 0.0f && next < target) {
            return target;
        }
        return next;
    }

    private final float approach(float current, float target, float step, float speed) {
        return current + (target - current) * (1.0f - (float)Math.exp(-step * speed));
    }

    private final int white(float alpha) {
        int value = (int)(RangesKt.coerceIn((float)alpha, (float)0.0f, (float)1.0f) * 255.0f);
        return value << 24 | 0xFFFFFF;
    }

    static {
        int n = 0;
        DeferredSlot[] deferredSlotArray = new DeferredSlot[10];
        while (n < 10) {
            int n2 = n++;
            deferredSlotArray[n2] = new DeferredSlot();
        }
        deferred = deferredSlotArray;
        frame = new float[5];
        lastSelected = -1;
        pitch = 20.0f;
        liftPixels = 2.0f;
        tiltStrength = 1.0f;
        useResponse = true;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003R$\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR$\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0014\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\"\u0010\u0018\u001a\u00020\u00178\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\"\u0010\u001e\u001a\u00020\u00178\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001e\u0010\u0019\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001d\u00a8\u0006!"}, d2={"Lrtx/kimiko/utils/render/others/hud/HotbarItemAnimator$DeferredSlot;", "", "<init>", "()V", "", "clear", "Lnet/minecraft/LivingEntity;", "entity", "Lnet/minecraft/LivingEntity;", "getEntity", "()Lnet/minecraft/LivingEntity;", "setEntity", "(Lnet/minecraft/LivingEntity;)V", "Lnet/minecraft/ItemStack;", "stack", "Lnet/minecraft/ItemStack;", "getStack", "()Lnet/minecraft/ItemStack;", "setStack", "(Lnet/minecraft/ItemStack;)V", "decorationStack", "getDecorationStack", "setDecorationStack", "", "y", "I", "getY", "()I", "setY", "(I)V", "seed", "getSeed", "setSeed", "rtx.kimiko:kimiko"})
    private static final class DeferredSlot {
        @Nullable
        private LivingEntity entity;
        @Nullable
        private ItemStack stack;
        @Nullable
        private ItemStack decorationStack;
        private int y;
        private int seed;

        @Nullable
        public final LivingEntity getEntity() {
            return this.entity;
        }

        public final void setEntity(@Nullable LivingEntity livingEntity2) {
            this.entity = livingEntity2;
        }

        @Nullable
        public final ItemStack getStack() {
            return this.stack;
        }

        public final void setStack(@Nullable ItemStack itemStack2) {
            this.stack = itemStack2;
        }

        @Nullable
        public final ItemStack getDecorationStack() {
            return this.decorationStack;
        }

        public final void setDecorationStack(@Nullable ItemStack itemStack2) {
            this.decorationStack = itemStack2;
        }

        public final int getY() {
            return this.y;
        }

        public final void setY(int n) {
            this.y = n;
        }

        public final int getSeed() {
            return this.seed;
        }

        public final void setSeed(int n) {
            this.seed = n;
        }

        public final void clear() {
            this.entity = null;
            this.stack = null;
            this.decorationStack = null;
        }
    }
}

