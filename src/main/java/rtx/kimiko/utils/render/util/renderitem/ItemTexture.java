/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.util.Identifier
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.util.renderitem;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0080\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ$\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0016\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u000b\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/utils/render/util/renderitem/ItemTexture;", "", "Lnet/minecraft/Identifier;", "id", "Lnet/minecraft/TextureSetup;", "setup", "<init>", "(Lnet/minecraft/Identifier;Lnet/minecraft/TextureSetup;)V", "component1", "()Lnet/minecraft/Identifier;", "component2", "()Lnet/minecraft/TextureSetup;", "copy", "(Lnet/minecraft/Identifier;Lnet/minecraft/TextureSetup;)Lrtx/kimiko/utils/render/util/renderitem/ItemTexture;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/Identifier;", "getId", "Lnet/minecraft/TextureSetup;", "getSetup", "rtx.kimiko:kimiko"})
public final class ItemTexture {
    @NotNull
    private final Identifier id;
    @NotNull
    private final TextureSetup setup;

    public ItemTexture(@NotNull Identifier id, @NotNull TextureSetup setup) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        Intrinsics.checkNotNullParameter((Object)setup, (String)"setup");
        this.id = id;
        this.setup = setup;
    }

    @NotNull
    public final Identifier getId() {
        return this.id;
    }

    @NotNull
    public final TextureSetup getSetup() {
        return this.setup;
    }

    @NotNull
    public final Identifier component1() {
        return this.id;
    }

    @NotNull
    public final TextureSetup component2() {
        return this.setup;
    }

    @NotNull
    public final ItemTexture copy(@NotNull Identifier id, @NotNull TextureSetup setup) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        Intrinsics.checkNotNullParameter((Object)setup, (String)"setup");
        return new ItemTexture(id, setup);
    }

    public static /* synthetic */ ItemTexture copy$default(ItemTexture itemTexture, Identifier identifier2, TextureSetup textureSetup2, int n, Object object) {
        if ((n & 1) != 0) {
            identifier2 = itemTexture.id;
        }
        if ((n & 2) != 0) {
            textureSetup2 = itemTexture.setup;
        }
        return itemTexture.copy(identifier2, textureSetup2);
    }

    @NotNull
    public String toString() {
        return "ItemTexture(id=" + this.id + ", setup=" + this.setup + ")";
    }

    public int hashCode() {
        int result = this.id.hashCode();
        result = result * 31 + this.setup.hashCode();
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ItemTexture)) {
            return false;
        }
        ItemTexture itemTexture = (ItemTexture)other;
        if (!Intrinsics.areEqual((Object)this.id, (Object)itemTexture.id)) {
            return false;
        }
        return Intrinsics.areEqual((Object)this.setup, (Object)itemTexture.setup);
    }
}

