/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.render.state.special.SpecialGuiElementRenderState
 *  net.minecraft.client.render.item.KeyedItemRenderState
 *  net.minecraft.client.gui.ScreenRect
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fc
 */
package rtx.kimiko.utils.render.others.hud;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.render.state.special.SpecialGuiElementRenderState;
import net.minecraft.client.render.item.KeyedItemRenderState;
import net.minecraft.client.gui.ScreenRect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0014J\u000f\u0010\u0017\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0014J\u000f\u0010\u0018\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0011\u0010\u001c\u001a\u0004\u0018\u00010\u000fH\u0016\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0011\u0010\u001e\u001a\u0004\u0018\u00010\u000fH\u0016\u00a2\u0006\u0004\b\u001e\u0010\u001dR\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001f\u00a2\u0006\u0006\n\u0004\b\u0003\u0010 R\u0019\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001f\u00a2\u0006\u0006\n\u0004\b\u0005\u0010!R\u0019\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001f\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\"R\u0019\u0010\b\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001f\u00a2\u0006\u0006\n\u0004\b\b\u0010\"R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\n\u0010#R\u0014\u0010\u000b\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010 R\u0014\u0010\f\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\f\u0010 R\u0014\u0010\r\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\r\u0010 R\u0014\u0010\u000e\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000e\u0010 R\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0010\u0010$R\u0016\u0010%\u001a\u0004\u0018\u00010\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010$\u00a8\u0006&"}, d2={"Lrtx/kimiko/utils/render/others/hud/HotbarItemPipState;", "Lnet/minecraft/SpecialGuiElementRenderState;", "", "slot", "Lnet/minecraft/KeyedItemRenderState;", "itemState", "", "sizePixels", "tiltDegrees", "Lorg/joml/Matrix3x2f;", "poseVal", "x0Val", "y0Val", "x1Val", "y1Val", "Lnet/minecraft/ScreenRect;", "scissorAreaVal", "<init>", "(ILnet/minecraft/KeyedItemRenderState;FFLorg/joml/Matrix3x2f;IIIILnet/minecraft/ScreenRect;)V", "x0", "()I", "x1", "y0", "y1", "scale", "()F", "pose", "()Lorg/joml/Matrix3x2f;", "scissorArea", "()Lnet/minecraft/ScreenRect;", "bounds", "Lkotlin/jvm/JvmField;", "I", "Lnet/minecraft/KeyedItemRenderState;", "F", "Lorg/joml/Matrix3x2f;", "Lnet/minecraft/ScreenRect;", "boundsVal", "rtx.kimiko:kimiko"})
public final class HotbarItemPipState
implements SpecialGuiElementRenderState {
    @JvmField
    public final int slot;
    @JvmField
    @NotNull
    public final KeyedItemRenderState itemState;
    @JvmField
    public final float sizePixels;
    @JvmField
    public final float tiltDegrees;
    @NotNull
    private final Matrix3x2f poseVal;
    private final int x0Val;
    private final int y0Val;
    private final int x1Val;
    private final int y1Val;
    @Nullable
    private final ScreenRect scissorAreaVal;
    @Nullable
    private final ScreenRect boundsVal;

    /*
     * WARNING - void declaration
     */
    public HotbarItemPipState(int slot, @NotNull KeyedItemRenderState itemState, float sizePixels, float tiltDegrees, @NotNull Matrix3x2f poseVal, int x0Val, int y0Val, int x1Val, int y1Val, @Nullable ScreenRect scissorAreaVal) {
        Intrinsics.checkNotNullParameter((Object)itemState, (String)"itemState");
        Intrinsics.checkNotNullParameter((Object)poseVal, (String)"poseVal");
        this.slot = slot;
        this.itemState = itemState;
        this.sizePixels = sizePixels;
        this.tiltDegrees = tiltDegrees;
        this.poseVal = poseVal;
        this.x0Val = x0Val;
        this.y0Val = y0Val;
        this.x1Val = x1Val;
        this.y1Val = y1Val;
        this.scissorAreaVal = scissorAreaVal;
        ScreenRect box = new ScreenRect(this.x0Val, this.y0Val, this.x1Val - this.x0Val, this.y1Val - this.y0Val).transformEachVertex((Matrix3x2fc)this.poseVal);
        this.boundsVal = this.scissorAreaVal != null ? this.scissorAreaVal.intersection(box) : box;
    }

    public int x1() {
        return this.x0Val;
    }

    public int x2() {
        return this.x1Val;
    }

    public int y1() {
        return this.y0Val;
    }

    public int y2() {
        return this.y1Val;
    }

    public float scale() {
        return this.sizePixels;
    }

    @NotNull
    public Matrix3x2f pose() {
        return this.poseVal;
    }

    @Nullable
    public ScreenRect scissorArea() {
        return this.scissorAreaVal;
    }

    @Nullable
    public ScreenRect bounds() {
        return this.boundsVal;
    }
}

