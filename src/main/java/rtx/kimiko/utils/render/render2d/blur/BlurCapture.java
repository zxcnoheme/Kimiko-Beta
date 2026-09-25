/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.client.gl.GpuSampler
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.blur;

import com.mojang.blaze3d.textures.GpuTextureView;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.client.gl.GpuSampler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\t\u0010\nJ\r\u0010\u000b\u001a\u00020\b\u00a2\u0006\u0004\b\u000b\u0010\u0003R\u001b\u0010\u000e\u001a\u00020\f8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\r\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0010\u001a\u00020\f8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\r\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000fR\u001b\u0010\u0011\u001a\u00020\f8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\r\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u000fR\u001b\u0010\u0012\u001a\u00020\f8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\r\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u000fR\u001b\u0010\u0014\u001a\u00020\u00138\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\r\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0016\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\r\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u001d\u0010\u0018\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\r\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "", "<init>", "()V", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "view", "Lnet/minecraft/GpuSampler;", "sampler", "", "bindBackdrop", "(Lcom/mojang/blaze3d/textures/GpuTextureView;Lnet/minecraft/GpuSampler;)V", "reset", "", "Lkotlin/jvm/JvmField;", "regionX", "F", "regionY", "regionW", "regionH", "Lnet/minecraft/TextureSetup;", "setup", "Lnet/minecraft/TextureSetup;", "backdropView", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "backdropSampler", "Lnet/minecraft/GpuSampler;", "rtx.kimiko:kimiko"})
public final class BlurCapture {
    @JvmField
    public float regionX;
    @JvmField
    public float regionY;
    @JvmField
    public float regionW = 1.0f;
    @JvmField
    public float regionH = 1.0f;
    @JvmField
    @NotNull
    public TextureSetup setup;
    @JvmField
    @Nullable
    public GpuTextureView backdropView;
    @JvmField
    @Nullable
    public GpuSampler backdropSampler;

    public BlurCapture() {
        TextureSetup textureSetup2 = TextureSetup.empty();
        Intrinsics.checkNotNullExpressionValue((Object)textureSetup2, (String)"noTexture(...)");
        this.setup = textureSetup2;
    }

    public final void bindBackdrop(@NotNull GpuTextureView view, @NotNull GpuSampler sampler) {
        Intrinsics.checkNotNullParameter((Object)view, (String)"view");
        Intrinsics.checkNotNullParameter((Object)sampler, (String)"sampler");
        this.backdropView = view;
        this.backdropSampler = sampler;
    }

    public final void reset() {
        this.regionX = 0.0f;
        this.regionY = 0.0f;
        this.regionW = 1.0f;
        this.regionH = 1.0f;
        TextureSetup textureSetup2 = TextureSetup.empty();
        Intrinsics.checkNotNullExpressionValue((Object)textureSetup2, (String)"noTexture(...)");
        this.setup = textureSetup2;
        this.backdropView = null;
        this.backdropSampler = null;
    }
}

