/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.CommandEncoder
 *  com.mojang.blaze3d.systems.GpuDevice
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.textures.GpuTexture
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  com.mojang.blaze3d.textures.TextureFormat
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.AbstractTexture
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.modules.post.trailecho;

import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.Kimiko;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\"B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001f\u0010\r\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u0010\u001a\u00020\u000fH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u000f\u0010\u0011\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0003R\u0019\u0010\u0014\u001a\u00020\u00128\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0013\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001c\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001e\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001dR\u0016\u0010\u001f\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010!\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b!\u0010 \u00a8\u0006#"}, d2={"Lrtx/kimiko/utils/render/modules/post/trailecho/TrailEchoGlass;", "", "<init>", "()V", "Lnet/minecraft/Framebuffer;", "target", "", "Lkotlin/jvm/JvmStatic;", "capture", "(Lnet/minecraft/Framebuffer;)Z", "", "w", "h", "ensure", "(II)Z", "", "clear", "closeTexture", "Lnet/minecraft/Identifier;", "Lkotlin/jvm/JvmField;", "TEXTURE_ID", "Lnet/minecraft/Identifier;", "Lcom/mojang/blaze3d/textures/GpuTexture;", "sceneTexture", "Lcom/mojang/blaze3d/textures/GpuTexture;", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "sceneView", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "width", "I", "height", "registered", "Z", "disabledAfterError", "SceneTexture", "rtx.kimiko:kimiko"})
public final class TrailEchoGlass {
    @NotNull
    public static final TrailEchoGlass INSTANCE = new TrailEchoGlass();
    @JvmField
    @NotNull
    public static final Identifier TEXTURE_ID;
    @Nullable
    private static GpuTexture sceneTexture;
    @Nullable
    private static GpuTextureView sceneView;
    private static int width;
    private static int height;
    private static boolean registered;
    private static boolean disabledAfterError;

    private TrailEchoGlass() {
    }

    @JvmStatic
    public static final boolean capture(@Nullable Framebuffer target) {
        if (disabledAfterError || target == null || target.getColorAttachment() == null || target.textureWidth <= 0 || target.textureHeight <= 0) {
            return false;
        }
        try {
            if (!INSTANCE.ensure(target.textureWidth, target.textureHeight)) {
                return false;
            }
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder encoder = commandEncoder;
            GpuTexture gpuTexture = target.getColorAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture);
            GpuTexture gpuTexture2 = sceneTexture;
            Intrinsics.checkNotNull((Object)gpuTexture2);
            encoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, 0, 0, 0, 0, target.textureWidth, target.textureHeight);
            return true;
        }
        catch (Throwable throwable) {
            disabledAfterError = true;
            TrailEchoGlass.clear();
            return false;
        }
    }

    private final boolean ensure(int w, int h) {
        GpuDevice gpuDevice = RenderSystem.tryGetDevice();
        if (gpuDevice == null) {
            return false;
        }
        GpuDevice device = gpuDevice;
        if (sceneTexture != null && width == w && height == h && registered) {
            return true;
        }
        this.closeTexture();
        GpuTexture gpuTexture = device.createTexture(TrailEchoGlass::ensure$lambda$0, 5, TextureFormat.RGBA8, w, h, 1, 1);
        Intrinsics.checkNotNullExpressionValue((Object)gpuTexture, (String)"createTexture(...)");
        GpuTexture texture = gpuTexture;
        GpuTextureView gpuTextureView = device.createTextureView(texture);
        Intrinsics.checkNotNullExpressionValue((Object)gpuTextureView, (String)"createTextureView(...)");
        GpuTextureView view = gpuTextureView;
        sceneTexture = texture;
        sceneView = view;
        width = w;
        height = h;
        MinecraftClient.getInstance().getTextureManager().registerTexture(TEXTURE_ID, (AbstractTexture)new SceneTexture(texture, view));
        registered = true;
        return true;
    }

    @JvmStatic
    public static final void clear() {
        if (registered) {
            try {
                MinecraftClient.getInstance().getTextureManager().destroyTexture(TEXTURE_ID);
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            registered = false;
        }
        INSTANCE.closeTexture();
    }

    private final void closeTexture() {
        GpuTextureView gpuTextureView = sceneView;
        if (gpuTextureView != null) {
            gpuTextureView.close();
        }
        sceneView = null;
        GpuTexture gpuTexture = sceneTexture;
        if (gpuTexture != null) {
            gpuTexture.close();
        }
        sceneTexture = null;
        width = -1;
        height = -1;
    }

    private static final String ensure$lambda$0() {
        return "kimiko:trail_echo_scene";
    }

    static {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"trail_echo_scene");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        TEXTURE_ID = identifier2;
        width = -1;
        height = -1;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u000fR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/utils/render/modules/post/trailecho/TrailEchoGlass$SceneTexture;", "Lnet/minecraft/AbstractTexture;", "Lcom/mojang/blaze3d/textures/GpuTexture;", "gpuTexture", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "gpuView", "<init>", "(Lcom/mojang/blaze3d/textures/GpuTexture;Lcom/mojang/blaze3d/textures/GpuTextureView;)V", "getTexture", "()Lcom/mojang/blaze3d/textures/GpuTexture;", "getTextureView", "()Lcom/mojang/blaze3d/textures/GpuTextureView;", "", "close", "()V", "Lcom/mojang/blaze3d/textures/GpuTexture;", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "rtx.kimiko:kimiko"})
    private static final class SceneTexture
    extends AbstractTexture {
        @NotNull
        private final GpuTexture gpuTexture;
        @NotNull
        private final GpuTextureView gpuView;

        public SceneTexture(@NotNull GpuTexture gpuTexture, @NotNull GpuTextureView gpuView) {
            Intrinsics.checkNotNullParameter((Object)gpuTexture, (String)"gpuTexture");
            Intrinsics.checkNotNullParameter((Object)gpuView, (String)"gpuView");
            this.gpuTexture = gpuTexture;
            this.gpuView = gpuView;
        }

        @NotNull
        public GpuTexture getGlTexture() {
            return this.gpuTexture;
        }

        @NotNull
        public GpuTextureView getGlTextureView() {
            return this.gpuView;
        }

        public void close() {
        }
    }
}

