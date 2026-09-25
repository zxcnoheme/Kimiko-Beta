/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.textures.FilterMode
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.NativeImage
 *  net.minecraft.client.texture.NativeImageBackedTexture
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.client.gl.GpuSampler
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.fonts.core;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import java.awt.image.BufferedImage;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.client.gl.GpuSampler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 +2\u00060\u0001j\u0002`\u0002:\u0002,+B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\r\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\rJ\u001f\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0012J%\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\u0017\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\u0017H\u0016\u00a2\u0006\u0004\b\u001c\u0010\u001bR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010$R\u0016\u0010%\u001a\u00020\u00038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u0010\u001dR\u0016\u0010&\u001a\u00020\u00038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010\u001dR\u0016\u0010'\u001a\u00020\u00038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b'\u0010\u001dR\u0016\u0010)\u001a\u00020(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010*\u00a8\u0006-"}, d2={"Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "", "size", "<init>", "(I)V", "Lnet/minecraft/TextureSetup;", "textureSetup", "()Lnet/minecraft/TextureSetup;", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "textureView", "()Lcom/mojang/blaze3d/textures/GpuTextureView;", "()I", "width", "height", "Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage$Allocation;", "allocate", "(II)Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage$Allocation;", "Ljava/awt/image/BufferedImage;", "source", "atlasX", "atlasY", "", "copy", "(Ljava/awt/image/BufferedImage;II)V", "uploadIfDirty", "()V", "close", "I", "Lnet/minecraft/NativeImage;", "image", "Lnet/minecraft/NativeImage;", "Lnet/minecraft/NativeImageBackedTexture;", "texture", "Lnet/minecraft/NativeImageBackedTexture;", "Lnet/minecraft/TextureSetup;", "cursorX", "cursorY", "rowHeight", "", "dirty", "Z", "Companion", "Allocation", "rtx.kimiko:kimiko"})
public final class GlyphAtlasPage
implements AutoCloseable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final int size;
    @NotNull
    private final NativeImage image;
    @NotNull
    private final NativeImageBackedTexture texture;
    @NotNull
    private final TextureSetup textureSetup;
    private int cursorX;
    private int cursorY;
    private int rowHeight;
    private boolean dirty;
    private static int nextId;

    public GlyphAtlasPage(int size) {
        this.size = size;
        this.image = new NativeImage(this.size, this.size, true);
        this.texture = new NativeImageBackedTexture(GlyphAtlasPage::texture$lambda$0, this.image);
        TextureSetup textureSetup2 = TextureSetup.of((GpuTextureView)this.texture.getGlTextureView(), (GpuSampler)RenderSystem.getSamplerCache().get(FilterMode.LINEAR));
        Intrinsics.checkNotNullExpressionValue((Object)textureSetup2, (String)"singleTexture(...)");
        this.textureSetup = textureSetup2;
        this.cursorX = 1;
        this.cursorY = 1;
    }

    @NotNull
    public final TextureSetup textureSetup() {
        return this.textureSetup;
    }

    @NotNull
    public final GpuTextureView textureView() {
        GpuTextureView gpuTextureView = this.texture.getGlTextureView();
        Intrinsics.checkNotNullExpressionValue((Object)gpuTextureView, (String)"getTextureView(...)");
        return gpuTextureView;
    }

    public final int size() {
        return this.size;
    }

    @Nullable
    public final Allocation allocate(int width, int height) {
        int paddedWidth = width + 2;
        int paddedHeight = height + 2;
        if (paddedWidth > this.size || paddedHeight > this.size) {
            return null;
        }
        if (this.cursorX + paddedWidth > this.size) {
            this.cursorX = 1;
            this.cursorY += this.rowHeight;
            this.rowHeight = 0;
        }
        if (this.cursorY + paddedHeight > this.size) {
            return null;
        }
        int x = this.cursorX;
        int y = this.cursorY;
        this.cursorX += paddedWidth;
        this.rowHeight = Math.max(this.rowHeight, paddedHeight);
        return new Allocation(this, x, y, (float)x / (float)this.size, (float)y / (float)this.size, (float)(x + width) / (float)this.size, (float)(y + height) / (float)this.size);
    }

    public final void copy(@NotNull BufferedImage source, int atlasX, int atlasY) {
        Intrinsics.checkNotNullParameter((Object)source, (String)"source");
        int width = source.getWidth();
        int height = source.getHeight();
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                this.image.setColor(atlasX + x, atlasY + y, GlyphAtlasPage.Companion.argbToAbgr(source.getRGB(x, y)));
            }
        }
        this.dirty = true;
    }

    public final void uploadIfDirty() {
        if (!this.dirty) {
            return;
        }
        this.texture.upload();
        this.dirty = false;
    }

    @Override
    public void close() {
        this.texture.close();
    }

    private static final String texture$lambda$0() {
        int n = nextId;
        nextId = n + 1;
        return "kimiko_font_atlas_" + n;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0014J\u0010\u0010\u0017\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0014JV\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u0007H\u00c6\u0001\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u001b\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0011\u0010\u001e\u001a\u00020\u0004H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001e\u0010\u0011J\u0011\u0010 \u001a\u00020\u001fH\u00d6\u0081\u0004\u00a2\u0006\u0004\b \u0010!R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010$\u001a\u0004\b\u0003\u0010\u000fR%\u0010\u0005\u001a\u00020\u00048\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010%\u001a\u0004\b\u0005\u0010\u0011R%\u0010\u0006\u001a\u00020\u00048\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010%\u001a\u0004\b\u0006\u0010\u0011R%\u0010\b\u001a\u00020\u00078\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010&\u001a\u0004\b\b\u0010\u0014R%\u0010\t\u001a\u00020\u00078\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010&\u001a\u0004\b\t\u0010\u0014R%\u0010\n\u001a\u00020\u00078\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010&\u001a\u0004\b\n\u0010\u0014R%\u0010\u000b\u001a\u00020\u00078\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010&\u001a\u0004\b\u000b\u0010\u0014\u00a8\u0006'"}, d2={"Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage$Allocation;", "", "Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;", "page", "", "x", "y", "", "u0", "v0", "u1", "v1", "<init>", "(Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;IIFFFF)V", "component1", "()Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;", "component2", "()I", "component3", "component4", "()F", "component5", "component6", "component7", "copy", "(Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;IIFFFF)Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage$Allocation;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;", "I", "F", "rtx.kimiko:kimiko"})
    public static final class Allocation {
        @NotNull
        private final GlyphAtlasPage page;
        private final int x;
        private final int y;
        private final float u0;
        private final float v0;
        private final float u1;
        private final float v1;

        public Allocation(@NotNull GlyphAtlasPage page, int x, int y, float u0, float v0, float u1, float v1) {
            Intrinsics.checkNotNullParameter((Object)page, (String)"page");
            this.page = page;
            this.x = x;
            this.y = y;
            this.u0 = u0;
            this.v0 = v0;
            this.u1 = u1;
            this.v1 = v1;
        }

        @JvmName(name="page")
        @NotNull
        public final GlyphAtlasPage page() {
            return this.page;
        }

        @JvmName(name="x")
        public final int x() {
            return this.x;
        }

        @JvmName(name="y")
        public final int y() {
            return this.y;
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

        @NotNull
        public final GlyphAtlasPage component1() {
            return this.page;
        }

        public final int component2() {
            return this.x;
        }

        public final int component3() {
            return this.y;
        }

        public final float component4() {
            return this.u0;
        }

        public final float component5() {
            return this.v0;
        }

        public final float component6() {
            return this.u1;
        }

        public final float component7() {
            return this.v1;
        }

        @NotNull
        public final Allocation copy(@NotNull GlyphAtlasPage page, int x, int y, float u0, float v0, float u1, float v1) {
            Intrinsics.checkNotNullParameter((Object)page, (String)"page");
            return new Allocation(page, x, y, u0, v0, u1, v1);
        }

        public static /* synthetic */ Allocation copy$default(Allocation allocation, GlyphAtlasPage glyphAtlasPage, int n, int n2, float f, float f2, float f3, float f4, int n3, Object object) {
            if ((n3 & 1) != 0) {
                glyphAtlasPage = allocation.page;
            }
            if ((n3 & 2) != 0) {
                n = allocation.x;
            }
            if ((n3 & 4) != 0) {
                n2 = allocation.y;
            }
            if ((n3 & 8) != 0) {
                f = allocation.u0;
            }
            if ((n3 & 0x10) != 0) {
                f2 = allocation.v0;
            }
            if ((n3 & 0x20) != 0) {
                f3 = allocation.u1;
            }
            if ((n3 & 0x40) != 0) {
                f4 = allocation.v1;
            }
            return allocation.copy(glyphAtlasPage, n, n2, f, f2, f3, f4);
        }

        @NotNull
        public String toString() {
            return "Allocation(page=" + this.page + ", x=" + this.x + ", y=" + this.y + ", u0=" + this.u0 + ", v0=" + this.v0 + ", u1=" + this.u1 + ", v1=" + this.v1 + ")";
        }

        public int hashCode() {
            int result = this.page.hashCode();
            result = result * 31 + Integer.hashCode(this.x);
            result = result * 31 + Integer.hashCode(this.y);
            result = result * 31 + Float.hashCode(this.u0);
            result = result * 31 + Float.hashCode(this.v0);
            result = result * 31 + Float.hashCode(this.u1);
            result = result * 31 + Float.hashCode(this.v1);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Allocation)) {
                return false;
            }
            Allocation allocation = (Allocation)other;
            if (!Intrinsics.areEqual((Object)this.page, (Object)allocation.page)) {
                return false;
            }
            if (this.x != allocation.x) {
                return false;
            }
            if (this.y != allocation.y) {
                return false;
            }
            if (Float.compare(this.u0, allocation.u0) != 0) {
                return false;
            }
            if (Float.compare(this.v0, allocation.v0) != 0) {
                return false;
            }
            if (Float.compare(this.u1, allocation.u1) != 0) {
                return false;
            }
            return Float.compare(this.v1, allocation.v1) == 0;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage.Companion;", "", "<init>", "()V", "", "argb", "argbToAbgr", "(I)I", "nextId", "I", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final int argbToAbgr(int argb) {
            int alpha = argb >>> 24 & 0xFF;
            int red = argb >>> 16 & 0xFF;
            int green = argb >>> 8 & 0xFF;
            int blue = argb & 0xFF;
            return alpha << 24 | blue << 16 | green << 8 | red;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

