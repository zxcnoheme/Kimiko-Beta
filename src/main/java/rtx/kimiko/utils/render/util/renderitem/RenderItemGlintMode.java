/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.NoWhenBranchMatchedException
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.item.ItemRenderState.Glint
 *  net.minecraft.item.ItemStack
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.util.renderitem;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u000b\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0000\u00a2\u0006\u0004\b\t\u0010\nJ!\u0010\u000b\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\bH\u0000\u00a2\u0006\u0004\b\t\u0010\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/utils/render/util/renderitem/RenderItemGlintMode;", "", "<init>", "(Ljava/lang/String;I)V", "Lnet/minecraft/ItemStack;", "stack", "Lnet/minecraft/ItemRenderState$Glint;", "foilType", "", "enabled$rtx_kimiko_kimiko", "(Lnet/minecraft/ItemStack;Lnet/minecraft/ItemRenderState$Glint;)Z", "enabled", "foil", "(Lnet/minecraft/ItemStack;Z)Z", "AUTO", "OFF", "ON", "rtx.kimiko:kimiko"})
public enum RenderItemGlintMode {
        AUTO,
        OFF,
        ON;
public final boolean enabled$rtx_kimiko_kimiko(@Nullable ItemStack stack, @NotNull ItemRenderState.Glint foilType) {
        Intrinsics.checkNotNullParameter((Object)foilType, (String)"foilType");
        return this.enabled$rtx_kimiko_kimiko(stack, foilType != ItemRenderState.Glint.NONE);
    }

    public final boolean enabled$rtx_kimiko_kimiko(@Nullable ItemStack stack, boolean foil) {
        return switch (this) {
            case ON -> true;
            case OFF -> false;
            case AUTO -> (stack != null && (stack.hasGlint() || foil));
        };
    }

    @NotNull
    public static EnumEntries<RenderItemGlintMode> getEntries() {
        return EnumEntriesKt.enumEntries(values());
    }
}

