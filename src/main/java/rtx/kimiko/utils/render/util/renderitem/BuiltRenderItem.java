/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmOverloads
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.item.ItemStack
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.util.renderitem;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.util.renderitem.RenderItemOptions;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001BE\b\u0007\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u001a\u0002\b\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0011R%\u0010\u0005\u001a\u00020\u00048\u0007z\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0014\u001a\u0004\b\u0005\u0010\u0015R%\u0010\u0006\u001a\u00020\u00048\u0007z\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0014\u001a\u0004\b\u0006\u0010\u0015R%\u0010\u0007\u001a\u00020\u00048\u0007z\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0014\u001a\u0004\b\u0007\u0010\u0015R%\u0010\u000b\u001a\u00020\n8\u0007z\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010\u0016\u001a\u0004\b\u000b\u0010\u0017R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0003\u0010\u0019R%\u0010\t\u001a\u00020\b8\u0007z\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010\u001a\u001a\u0004\b\t\u0010\u001b\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/utils/render/util/renderitem/BuiltRenderItem;", "", "Lnet/minecraft/ItemStack;", "stack", "", "x", "y", "size", "Lrtx/kimiko/utils/render/util/renderitem/RenderItemOptions;", "options", "", "seed", "Lkotlin/jvm/JvmOverloads;", "<init>", "(Lnet/minecraft/ItemStack;FFFLrtx/kimiko/utils/render/util/renderitem/RenderItemOptions;I)V", "", "visible", "()Z", "Lkotlin/jvm/JvmName;", "name", "F", "()F", "I", "()I", "Lnet/minecraft/ItemStack;", "()Lnet/minecraft/ItemStack;", "Lrtx/kimiko/utils/render/util/renderitem/RenderItemOptions;", "()Lrtx/kimiko/utils/render/util/renderitem/RenderItemOptions;", "rtx.kimiko:kimiko"})
public final class BuiltRenderItem {
    private final float x;
    private final float y;
    private final float size;
    private final int seed;
    @NotNull
    private final ItemStack stack;
    @NotNull
    private final RenderItemOptions options;

    @JvmOverloads
    public BuiltRenderItem(@Nullable ItemStack stack, float x, float y, float size, @Nullable RenderItemOptions options, int seed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.seed = seed;
        ItemStack itemStack2 = stack;
        if (itemStack2 == null) {
            ItemStack itemStack3 = ItemStack.EMPTY;
            itemStack2 = itemStack3;
            Intrinsics.checkNotNullExpressionValue((Object)itemStack3, (String)"EMPTY");
        }
        this.stack = itemStack2;
        RenderItemOptions renderItemOptions = options;
        if (renderItemOptions == null) {
            renderItemOptions = RenderItemOptions.Companion.defaults();
        }
        this.options = renderItemOptions;
    }

    public /* synthetic */ BuiltRenderItem(ItemStack itemStack2, float f, float f2, float f3, RenderItemOptions renderItemOptions, int n, int n2, DefaultConstructorMarker defaultConstructorMarker) {
        this(itemStack2, f, f2, f3, (n2 & 0x10) != 0 ? null : renderItemOptions, (n2 & 0x20) != 0 ? 0 : n);
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

    @JvmName(name="seed")
    public final int seed() {
        return this.seed;
    }

    @JvmName(name="stack")
    @NotNull
    public final ItemStack stack() {
        return this.stack;
    }

    @JvmName(name="options")
    @NotNull
    public final RenderItemOptions options() {
        return this.options;
    }

    public final boolean visible() {
        return !this.stack.isEmpty() && this.size > 0.0f && this.options.alpha() > 0.0f && this.options.color() >>> 24 != 0;
    }

    @JvmOverloads
    public BuiltRenderItem(@Nullable ItemStack stack, float x, float y, float size, @Nullable RenderItemOptions options) {
        this(stack, x, y, size, options, 0, 32, null);
    }

    @JvmOverloads
    public BuiltRenderItem(@Nullable ItemStack stack, float x, float y, float size) {
        this(stack, x, y, size, null, 0, 48, null);
    }
}

