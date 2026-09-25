/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.util.Identifier
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.image;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u000e\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0014\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0019\u0010\u0018J\u0010\u0010\u001a\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u001c\u0010\u001bJB\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007H\u00c6\u0001\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u001b\u0010 \u001a\u00020\f2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b \u0010!J\u0011\u0010\"\u001a\u00020\u0007H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\"\u0010\u001bJ\u0011\u0010$\u001a\u00020#H\u00d6\u0081\u0004\u00a2\u0006\u0004\b$\u0010%R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b&\u0012\b\b'\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010(\u001a\u0004\b\u0003\u0010\u0016R%\u0010\u0005\u001a\u00020\u00048\u0007z\f\b&\u0012\b\b'\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010)\u001a\u0004\b\u0005\u0010\u0018R%\u0010\u0006\u001a\u00020\u00048\u0007z\f\b&\u0012\b\b'\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010)\u001a\u0004\b\u0006\u0010\u0018R%\u0010\b\u001a\u00020\u00078\u0007z\f\b&\u0012\b\b'\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010*\u001a\u0004\b\b\u0010\u001bR%\u0010\t\u001a\u00020\u00078\u0007z\f\b&\u0012\b\b'\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010*\u001a\u0004\b\t\u0010\u001b\u00a8\u0006+"}, d2={"Lrtx/kimiko/utils/render/render2d/image/ImageTexture;", "", "Lnet/minecraft/Identifier;", "id", "Lnet/minecraft/TextureSetup;", "linearSetup", "nearestSetup", "", "width", "height", "<init>", "(Lnet/minecraft/Identifier;Lnet/minecraft/TextureSetup;Lnet/minecraft/TextureSetup;II)V", "", "nearest", "setup", "(Z)Lnet/minecraft/TextureSetup;", "", "size", "drawWidth", "(F)F", "drawHeight", "component1", "()Lnet/minecraft/Identifier;", "component2", "()Lnet/minecraft/TextureSetup;", "component3", "component4", "()I", "component5", "copy", "(Lnet/minecraft/Identifier;Lnet/minecraft/TextureSetup;Lnet/minecraft/TextureSetup;II)Lrtx/kimiko/utils/render/render2d/image/ImageTexture;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "Lnet/minecraft/Identifier;", "Lnet/minecraft/TextureSetup;", "I", "rtx.kimiko:kimiko"})
public final class ImageTexture {
    @NotNull
    private final Identifier id;
    @NotNull
    private final TextureSetup linearSetup;
    @NotNull
    private final TextureSetup nearestSetup;
    private final int width;
    private final int height;

    public ImageTexture(@NotNull Identifier id, @NotNull TextureSetup linearSetup, @NotNull TextureSetup nearestSetup, int width, int height) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        Intrinsics.checkNotNullParameter((Object)linearSetup, (String)"linearSetup");
        Intrinsics.checkNotNullParameter((Object)nearestSetup, (String)"nearestSetup");
        this.id = id;
        this.linearSetup = linearSetup;
        this.nearestSetup = nearestSetup;
        this.width = width;
        this.height = height;
    }

    @JvmName(name="id")
    @NotNull
    public final Identifier id() {
        return this.id;
    }

    @JvmName(name="linearSetup")
    @NotNull
    public final TextureSetup linearSetup() {
        return this.linearSetup;
    }

    @JvmName(name="nearestSetup")
    @NotNull
    public final TextureSetup nearestSetup() {
        return this.nearestSetup;
    }

    @JvmName(name="width")
    public final int width() {
        return this.width;
    }

    @JvmName(name="height")
    public final int height() {
        return this.height;
    }

    @NotNull
    public final TextureSetup setup(boolean nearest) {
        return nearest ? this.nearestSetup : this.linearSetup;
    }

    public final float drawWidth(float size) {
        if (this.width <= 0 || this.height <= 0 || this.width >= this.height) {
            return size;
        }
        return size * ((float)this.width / (float)this.height);
    }

    public final float drawHeight(float size) {
        if (this.width <= 0 || this.height <= 0 || this.height >= this.width) {
            return size;
        }
        return size * ((float)this.height / (float)this.width);
    }

    @NotNull
    public final Identifier component1() {
        return this.id;
    }

    @NotNull
    public final TextureSetup component2() {
        return this.linearSetup;
    }

    @NotNull
    public final TextureSetup component3() {
        return this.nearestSetup;
    }

    public final int component4() {
        return this.width;
    }

    public final int component5() {
        return this.height;
    }

    @NotNull
    public final ImageTexture copy(@NotNull Identifier id, @NotNull TextureSetup linearSetup, @NotNull TextureSetup nearestSetup, int width, int height) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        Intrinsics.checkNotNullParameter((Object)linearSetup, (String)"linearSetup");
        Intrinsics.checkNotNullParameter((Object)nearestSetup, (String)"nearestSetup");
        return new ImageTexture(id, linearSetup, nearestSetup, width, height);
    }

    public static /* synthetic */ ImageTexture copy$default(ImageTexture imageTexture, Identifier identifier2, TextureSetup textureSetup2, TextureSetup textureSetup3, int n, int n2, int n3, Object object) {
        if ((n3 & 1) != 0) {
            identifier2 = imageTexture.id;
        }
        if ((n3 & 2) != 0) {
            textureSetup2 = imageTexture.linearSetup;
        }
        if ((n3 & 4) != 0) {
            textureSetup3 = imageTexture.nearestSetup;
        }
        if ((n3 & 8) != 0) {
            n = imageTexture.width;
        }
        if ((n3 & 0x10) != 0) {
            n2 = imageTexture.height;
        }
        return imageTexture.copy(identifier2, textureSetup2, textureSetup3, n, n2);
    }

    @NotNull
    public String toString() {
        return "ImageTexture(id=" + this.id + ", linearSetup=" + this.linearSetup + ", nearestSetup=" + this.nearestSetup + ", width=" + this.width + ", height=" + this.height + ")";
    }

    public int hashCode() {
        int result = this.id.hashCode();
        result = result * 31 + this.linearSetup.hashCode();
        result = result * 31 + this.nearestSetup.hashCode();
        result = result * 31 + Integer.hashCode(this.width);
        result = result * 31 + Integer.hashCode(this.height);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImageTexture)) {
            return false;
        }
        ImageTexture imageTexture = (ImageTexture)other;
        if (!Intrinsics.areEqual((Object)this.id, (Object)imageTexture.id)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.linearSetup, (Object)imageTexture.linearSetup)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.nearestSetup, (Object)imageTexture.nearestSetup)) {
            return false;
        }
        if (this.width != imageTexture.width) {
            return false;
        }
        return this.height == imageTexture.height;
    }
}

