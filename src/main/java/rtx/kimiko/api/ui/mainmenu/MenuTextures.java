/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.textures.GpuTexture
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  com.mojang.blaze3d.textures.TextureFormat
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  kotlin.text.StringsKt
 *  net.minecraft.client.texture.NativeImage
 *  net.minecraft.client.texture.AbstractTexture
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.resource.ResourceManager
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.mainmenu;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.StringsKt;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourceManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.ui.mainmenu.MenuTheme;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u001bB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\u0007J\u000f\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\n\u0010\u0003J\u000f\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u000e\u0010\rJ\u001f\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0015R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0017R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0017R\u0016\u0010\u0019\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuTextures;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "vignette", "()Ljava/lang/String;", "noise", "", "ensure", "Lnet/minecraft/NativeImage;", "buildVignette", "()Lnet/minecraft/NativeImage;", "buildNoise", "path", "image", "upload", "(Ljava/lang/String;Lnet/minecraft/NativeImage;)Ljava/lang/String;", "", "VIGNETTE_SIZE", "I", "NOISE_SIZE", "Ljava/lang/String;", "", "failed", "Z", "SimpleTexture", "rtx.kimiko:kimiko"})
public final class MenuTextures {
    @NotNull
    public static final MenuTextures INSTANCE = new MenuTextures();
    private static final int VIGNETTE_SIZE = 256;
    private static final int NOISE_SIZE = 256;
    @Nullable
    private static String vignette;
    @Nullable
    private static String noise;
    private static boolean failed;

    private MenuTextures() {
    }

    @JvmStatic
    @Nullable
    public static final String vignette() {
        INSTANCE.ensure();
        return vignette;
    }

    @JvmStatic
    @Nullable
    public static final String noise() {
        INSTANCE.ensure();
        return noise;
    }

    private final void ensure() {
        if (failed || vignette != null && noise != null) {
            return;
        }
        try {
            if (vignette == null) {
                vignette = this.upload("menu/vignette", this.buildVignette());
            }
            if (noise == null) {
                noise = this.upload("menu/noise", this.buildNoise());
            }
        }
        catch (Throwable ignored) {
            failed = true;
        }
    }

    private final NativeImage buildVignette() {
        NativeImage image = new NativeImage(256, 256, false);
        float half = 128.0f;
        for (int y = 0; y < 256; ++y) {
            for (int x = 0; x < 256; ++x) {
                float dx = ((float)x + 0.5f - half) / half;
                float dy = ((float)y + 0.5f - half) / half;
                float d = (float)Math.sqrt(dx * dx + dy * dy) / 1.4142f;
                float t = MenuTheme.clamp01((d - 0.34f) / 0.66f);
                float shaped = t * t * (1.35f - 0.35f * t);
                int alpha = MathKt.roundToInt((float)(MenuTheme.clamp01(shaped) * 255.0f));
                image.setColorArgb(x, y, alpha << 24);
            }
        }
        return image;
    }

    private final NativeImage buildNoise() {
        NativeImage image = new NativeImage(256, 256, false);
        Random random = new Random(334567L);
        for (int y = 0; y < 256; ++y) {
            for (int x = 0; x < 256; ++x) {
                int v = 110 + random.nextInt(146);
                int a = 40 + random.nextInt(120);
                image.setColorArgb(x, y, a << 24 | v << 16 | v << 8 | v);
            }
        }
        return image;
    }

    private final String upload(String path, NativeImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        GpuTexture gpuTexture = RenderSystem.getDevice().createTexture(() -> MenuTextures.upload$lambda$0(path), 5, TextureFormat.RGBA8, w, h, 1, 1);
        Intrinsics.checkNotNullExpressionValue((Object)gpuTexture, (String)"createTexture(...)");
        GpuTexture texture = gpuTexture;
        RenderSystem.getDevice().createCommandEncoder().writeToTexture(texture, image);
        GpuTextureView gpuTextureView = RenderSystem.getDevice().createTextureView(texture);
        Intrinsics.checkNotNullExpressionValue((Object)gpuTextureView, (String)"createTextureView(...)");
        GpuTextureView view = gpuTextureView;
        image.close();
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        Identifier id = identifier2;
        MinecraftClient.getInstance().getTextureManager().registerTexture(id, (AbstractTexture)new SimpleTexture(texture, view));
        String string = id.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        return string;
    }

    private static final String upload$lambda$0(String $path) {
        return "kimiko_" + String.valueOf($path).replace((char)'/', (char)'_');
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0011R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0012\u00a8\u0006\u0013"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuTextures$SimpleTexture;", "Lnet/minecraft/AbstractTexture;", "Lcom/mojang/blaze3d/textures/GpuTexture;", "texture", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "view", "<init>", "(Lcom/mojang/blaze3d/textures/GpuTexture;Lcom/mojang/blaze3d/textures/GpuTextureView;)V", "getTexture", "()Lcom/mojang/blaze3d/textures/GpuTexture;", "getTextureView", "()Lcom/mojang/blaze3d/textures/GpuTextureView;", "Lnet/minecraft/ResourceManager;", "resourceManager", "", "load", "(Lnet/minecraft/ResourceManager;)V", "Lcom/mojang/blaze3d/textures/GpuTexture;", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "rtx.kimiko:kimiko"})
    public static final class SimpleTexture
    extends AbstractTexture {
        @NotNull
        private final GpuTexture texture;
        @NotNull
        private final GpuTextureView view;

        public SimpleTexture(@NotNull GpuTexture texture, @NotNull GpuTextureView view) {
            Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
            Intrinsics.checkNotNullParameter((Object)view, (String)"view");
            this.texture = texture;
            this.view = view;
        }

        @NotNull
        public GpuTexture getGlTexture() {
            return this.texture;
        }

        @NotNull
        public GpuTextureView getGlTextureView() {
            return this.view;
        }

        public final void load(@NotNull ResourceManager resourceManager) {
            Intrinsics.checkNotNullParameter((Object)resourceManager, (String)"resourceManager");
        }
    }
}

