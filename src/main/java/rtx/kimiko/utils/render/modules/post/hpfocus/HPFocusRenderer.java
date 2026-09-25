/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Snippet
 *  com.mojang.blaze3d.platform.DepthTestFunction
 *  com.mojang.blaze3d.systems.CommandEncoder
 *  com.mojang.blaze3d.systems.GpuDevice
 *  com.mojang.blaze3d.systems.RenderPass
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.textures.GpuTexture
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  com.mojang.blaze3d.textures.TextureFormat
 *  com.mojang.blaze3d.vertex.VertexFormat$DrawMode
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.AbstractTexture
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.resource.ResourceManager
 *  net.minecraft.client.gl.SimpleFramebuffer
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.modules.post.hpfocus;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.util.OptionalInt;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourceManager;
import net.minecraft.client.gl.SimpleFramebuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.render.others.RenderSampler;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001,B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u0013\u0010\b\u001a\u00020\u0007H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u0003J\u000f\u0010\u000b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u000b\u0010\tJ\u001f\u0010\u000f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0003R\u0014\u0010\u0013\u001a\u00020\u00128\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0017R\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0018\u0010!\u001a\u0004\u0018\u00010 8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u0018\u0010$\u001a\u0004\u0018\u00010#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010&\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0016\u0010(\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010'R\u0016\u0010)\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010*R\u0016\u0010+\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u0010*\u00a8\u0006-"}, d2={"Lrtx/kimiko/utils/render/modules/post/hpfocus/HPFocusRenderer;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "requestCapture", "", "hasCapture", "()Z", "captureIfRequested", "ensureInitialized", "", "width", "height", "ensureResources", "(II)V", "closeResources", "", "TEXTURE_KEY", "Ljava/lang/String;", "Lnet/minecraft/Identifier;", "TEXTURE_ID", "Lnet/minecraft/Identifier;", "PIPELINE_ID", "COPY_SHADER", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lcom/mojang/blaze3d/textures/GpuTexture;", "sceneCopyTexture", "Lcom/mojang/blaze3d/textures/GpuTexture;", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "sceneCopyView", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "Lnet/minecraft/SimpleFramebuffer;", "outTarget", "Lnet/minecraft/SimpleFramebuffer;", "texWidth", "I", "texHeight", "captureRequested", "Z", "disabledAfterError", "SceneTexture", "rtx.kimiko:kimiko"})
public final class HPFocusRenderer {
    @NotNull
    public static final HPFocusRenderer INSTANCE = new HPFocusRenderer();
    @NotNull
    public static final String TEXTURE_KEY = "kimiko:hpfocus_scene";
    @NotNull
    private static final Identifier TEXTURE_ID;
    @NotNull
    private static final Identifier PIPELINE_ID;
    @NotNull
    private static final Identifier COPY_SHADER;
    @Nullable
    private static RenderPipeline pipeline;
    @Nullable
    private static GpuTexture sceneCopyTexture;
    @Nullable
    private static GpuTextureView sceneCopyView;
    @Nullable
    private static SimpleFramebuffer outTarget;
    private static int texWidth;
    private static int texHeight;
    private static boolean captureRequested;
    private static boolean hasCapture;
    private static boolean disabledAfterError;

    private HPFocusRenderer() {
    }

    @JvmStatic
    public static final void requestCapture() {
        if (!disabledAfterError) {
            captureRequested = true;
        }
    }

    @JvmStatic
    public static final boolean hasCapture() {
        return hasCapture && !disabledAfterError;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void captureIfRequested() {
        if (!captureRequested) {
            return;
        }
        captureRequested = false;
        if (disabledAfterError) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        Framebuffer framebuffer2 = mc.getFramebuffer();
        Intrinsics.checkNotNullExpressionValue((Object)framebuffer2, (String)"getMainRenderTarget(...)");
        Framebuffer main = framebuffer2;
        if (main.getColorAttachment() == null) {
            return;
        }
        int width = main.textureWidth;
        int height = main.textureHeight;
        if (width <= 0 || height <= 0 || !INSTANCE.ensureInitialized()) {
            return;
        }
        try {
            INSTANCE.ensureResources(width, height);
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder encoder = commandEncoder;
            GpuTexture gpuTexture = main.getColorAttachment();
            Intrinsics.checkNotNull((Object)gpuTexture);
            GpuTexture gpuTexture2 = sceneCopyTexture;
            Intrinsics.checkNotNull((Object)gpuTexture2);
            encoder.copyTextureToTexture(gpuTexture, gpuTexture2, 0, 0, 0, 0, 0, width, height);
            Supplier<String> supplier = HPFocusRenderer::captureIfRequested$lambda$0;
            SimpleFramebuffer simpleFramebuffer2 = outTarget;
            Intrinsics.checkNotNull((Object)simpleFramebuffer2);
            GpuTextureView gpuTextureView = simpleFramebuffer2.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView);
            AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
            Throwable throwable = null;
            try {
                RenderPass pass = (RenderPass)autoCloseable;
                boolean bl = false;
                RenderPipeline renderPipeline = pipeline;
                Intrinsics.checkNotNull((Object)renderPipeline);
                pass.setPipeline(renderPipeline);
                pass.bindTexture("Sampler0", sceneCopyView, RenderSampler.linear());
                pass.draw(0, 6);
                Unit unit = Unit.INSTANCE;
            }
            catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            }
            finally {
                AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
            }
            hasCapture = true;
        }
        catch (Throwable throwable) {
            disabledAfterError = true;
            hasCapture = false;
            INSTANCE.closeResources();
        }
    }

    private final boolean ensureInitialized() {
        boolean bl;
        if (pipeline != null) {
            return true;
        }
        try {
            pipeline = RenderPipelines.register((RenderPipeline)RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(PIPELINE_ID).withVertexShader(COPY_SHADER).withFragmentShader(COPY_SHADER).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withSampler("Sampler0").withoutBlend().withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build());
            bl = true;
        }
        catch (Throwable throwable) {
            disabledAfterError = true;
            pipeline = null;
            bl = false;
        }
        return bl;
    }

    private final void ensureResources(int width, int height) {
        SimpleFramebuffer target;
        if (sceneCopyTexture != null && outTarget != null && width == texWidth && height == texHeight) {
            return;
        }
        this.closeResources();
        sceneCopyTexture = RenderSystem.getDevice().createTexture(HPFocusRenderer::ensureResources$lambda$0, 5, TextureFormat.RGBA8, width, height, 1, 1);
        GpuDevice gpuDevice = RenderSystem.getDevice();
        GpuTexture gpuTexture = sceneCopyTexture;
        Intrinsics.checkNotNull((Object)gpuTexture);
        sceneCopyView = gpuDevice.createTextureView(gpuTexture);
        outTarget = target = new SimpleFramebuffer(TEXTURE_KEY, width, height, false);
        MinecraftClient.getInstance().getTextureManager().registerTexture(TEXTURE_ID, (AbstractTexture)new SceneTexture(target.getColorAttachment(), target.getColorAttachmentView()));
        texWidth = width;
        texHeight = height;
    }

    private final void closeResources() {
        GpuTextureView gpuTextureView = sceneCopyView;
        if (gpuTextureView != null) {
            gpuTextureView.close();
        }
        sceneCopyView = null;
        GpuTexture gpuTexture = sceneCopyTexture;
        if (gpuTexture != null) {
            gpuTexture.close();
        }
        sceneCopyTexture = null;
        SimpleFramebuffer simpleFramebuffer2 = outTarget;
        if (simpleFramebuffer2 != null) {
            simpleFramebuffer2.delete();
        }
        outTarget = null;
        texWidth = -1;
        texHeight = -1;
    }

    private static final String captureIfRequested$lambda$0() {
        return "kimiko:hpfocus_copy";
    }

    private static final String ensureResources$lambda$0() {
        return "kimiko:hpfocus_scene_copy";
    }

    static {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"hpfocus_scene");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        TEXTURE_ID = identifier2;
        Identifier identifier3 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"pipeline/post/hpfocus/copy");
        Intrinsics.checkNotNullExpressionValue((Object)identifier3, (String)"fromNamespaceAndPath(...)");
        PIPELINE_ID = identifier3;
        Identifier identifier4 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"post/hpfocus/copy");
        Intrinsics.checkNotNullExpressionValue((Object)identifier4, (String)"fromNamespaceAndPath(...)");
        COPY_SHADER = identifier4;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0011R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0012\u00a8\u0006\u0013"}, d2={"Lrtx/kimiko/utils/render/modules/post/hpfocus/HPFocusRenderer$SceneTexture;", "Lnet/minecraft/AbstractTexture;", "Lcom/mojang/blaze3d/textures/GpuTexture;", "gpuTexture", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "gpuTextureView", "<init>", "(Lcom/mojang/blaze3d/textures/GpuTexture;Lcom/mojang/blaze3d/textures/GpuTextureView;)V", "getTexture", "()Lcom/mojang/blaze3d/textures/GpuTexture;", "getTextureView", "()Lcom/mojang/blaze3d/textures/GpuTextureView;", "Lnet/minecraft/ResourceManager;", "resourceManager", "", "load", "(Lnet/minecraft/ResourceManager;)V", "Lcom/mojang/blaze3d/textures/GpuTexture;", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "rtx.kimiko:kimiko"})
    public static final class SceneTexture
    extends AbstractTexture {
        @Nullable
        private final GpuTexture gpuTexture;
        @Nullable
        private final GpuTextureView gpuTextureView;

        public SceneTexture(@Nullable GpuTexture gpuTexture, @Nullable GpuTextureView gpuTextureView) {
            this.gpuTexture = gpuTexture;
            this.gpuTextureView = gpuTextureView;
        }

        @NotNull
        public GpuTexture getGlTexture() {
            GpuTexture gpuTexture = this.gpuTexture;
            Intrinsics.checkNotNull((Object)gpuTexture);
            return gpuTexture;
        }

        @NotNull
        public GpuTextureView getGlTextureView() {
            GpuTextureView gpuTextureView = this.gpuTextureView;
            Intrinsics.checkNotNull((Object)gpuTextureView);
            return gpuTextureView;
        }

        public final void load(@Nullable ResourceManager resourceManager) {
        }
    }
}

