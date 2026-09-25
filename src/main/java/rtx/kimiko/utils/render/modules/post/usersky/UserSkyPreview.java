/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.systems.CommandEncoder
 *  com.mojang.blaze3d.systems.GpuDevice
 *  com.mojang.blaze3d.systems.RenderPass
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.textures.FilterMode
 *  com.mojang.blaze3d.textures.GpuTexture
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  com.mojang.blaze3d.textures.TextureFormat
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.AbstractTexture
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.lwjgl.system.MemoryStack
 */
package rtx.kimiko.utils.render.modules.post.usersky;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import java.nio.ByteBuffer;
import java.util.OptionalInt;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.lwjgl.system.MemoryStack;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.modules.impl.Visuals.Ambience;
import rtx.kimiko.utils.render.modules.post.usersky.UserSkyProgram;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001JB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001d\u0010\n\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\n\u0010\tJ\u0013\u0010\u000b\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000b\u0010\u0003J\u0015\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u000f\u001a\u00020\u000eH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000f\u0010\u0010J#\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0016\u0010\u0003J\u0013\u0010\u0017\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0017\u0010\u0003J\u0013\u0010\u0018\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0018\u0010\u0003J\u0017\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u001d\u0010\u0010J\u000f\u0010\u001e\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u0003R\u0014\u0010\u001f\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020!8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b$\u0010#R\u0014\u0010&\u001a\u00020%8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010#R\u0014\u0010*\u001a\u00020)8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0018\u0010-\u001a\u0004\u0018\u00010,8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u0018\u00100\u001a\u0004\u0018\u00010/8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0018\u00103\u001a\u0004\u0018\u0001028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u00104R\u0018\u00106\u001a\u0004\u0018\u0001058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b6\u00107R\u0018\u00109\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b9\u0010:R\u0018\u0010;\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b;\u0010 R\u0018\u0010<\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u0010 R\u0016\u0010=\u001a\u00020)8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b=\u0010+R\u0016\u0010>\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0016\u0010\u000f\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010?R\u0016\u0010@\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b@\u0010AR\u0016\u0010B\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bB\u0010AR\u0016\u0010C\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bC\u0010?R\u0014\u0010E\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010FR\u0014\u0010G\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010FR\u0014\u0010H\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u0010FR\u0014\u0010I\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u0010F\u00a8\u0006K"}, d2={"Lrtx/kimiko/utils/render/modules/post/usersky/UserSkyPreview;", "", "<init>", "()V", "", "code", "", "Lkotlin/jvm/JvmStatic;", "setCode", "(Ljava/lang/String;)V", "compileNow", "requestFrame", "error", "()Ljava/lang/String;", "", "hasFrame", "()Z", "", "deltaYaw", "deltaPitch", "rotate", "(FF)V", "resetView", "reset", "renderPending", "Lcom/mojang/blaze3d/systems/CommandEncoder;", "encoder", "writeUniforms", "(Lcom/mojang/blaze3d/systems/CommandEncoder;)V", "ensureResources", "register", "TEXTURE", "Ljava/lang/String;", "", "WIDTH", "I", "HEIGHT", "Lnet/minecraft/Identifier;", "TEXTURE_ID", "Lnet/minecraft/Identifier;", "UNIFORM_SIZE", "", "COMPILE_DELAY_MS", "J", "Lcom/mojang/blaze3d/textures/GpuTexture;", "texture", "Lcom/mojang/blaze3d/textures/GpuTexture;", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "textureView", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "uniformBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "Lrtx/kimiko/utils/render/modules/post/usersky/UserSkyPreview$PreviewTexture;", "registered", "Lrtx/kimiko/utils/render/modules/post/usersky/UserSkyPreview$PreviewTexture;", "Lrtx/kimiko/utils/render/modules/post/usersky/UserSkyProgram;", "program", "Lrtx/kimiko/utils/render/modules/post/usersky/UserSkyProgram;", "compiledCode", "pendingCode", "pendingSinceMs", "renderRequested", "Z", "yaw", "F", "pitch", "manualView", "Lorg/joml/Matrix4f;", "PROJ", "Lorg/joml/Matrix4f;", "VIEW", "VIEW_PROJ", "INV_VIEW_PROJ", "PreviewTexture", "rtx.kimiko:kimiko"})
public final class UserSkyPreview {
    @NotNull
    public static final UserSkyPreview INSTANCE = new UserSkyPreview();
    @NotNull
    public static final String TEXTURE = "kimiko:usersky_preview";
    public static final int WIDTH = 480;
    public static final int HEIGHT = 270;
    @NotNull
    private static final Identifier TEXTURE_ID;
    private static final int UNIFORM_SIZE = 208;
    private static final long COMPILE_DELAY_MS = 400L;
    @Nullable
    private static GpuTexture texture;
    @Nullable
    private static GpuTextureView textureView;
    @Nullable
    private static GpuBuffer uniformBuffer;
    @Nullable
    private static PreviewTexture registered;
    @Nullable
    private static UserSkyProgram program;
    @Nullable
    private static String compiledCode;
    @Nullable
    private static String pendingCode;
    private static long pendingSinceMs;
    private static boolean renderRequested;
    private static boolean hasFrame;
    private static float yaw;
    private static float pitch;
    private static boolean manualView;
    @NotNull
    private static final Matrix4f PROJ;
    @NotNull
    private static final Matrix4f VIEW;
    @NotNull
    private static final Matrix4f VIEW_PROJ;
    @NotNull
    private static final Matrix4f INV_VIEW_PROJ;

    private UserSkyPreview() {
    }

    @JvmStatic
    public static final void setCode(@Nullable String code) {
        if (code == null) {
            return;
        }
        if (Intrinsics.areEqual((Object)code, (Object)compiledCode) || Intrinsics.areEqual((Object)code, (Object)pendingCode)) {
            return;
        }
        pendingCode = code;
        pendingSinceMs = System.currentTimeMillis();
    }

    @JvmStatic
    public static final void compileNow(@Nullable String code) {
        pendingCode = null;
        compiledCode = code;
        program = UserSkyProgram.Companion.compile(code);
    }

    @JvmStatic
    public static final void requestFrame() {
        String pending = pendingCode;
        if (pending != null && System.currentTimeMillis() - pendingSinceMs >= 400L) {
            UserSkyPreview.compileNow(pending);
        }
        renderRequested = true;
    }

    @JvmStatic
    @Nullable
    public static final String error() {
        UserSkyProgram userSkyProgram = program;
        return userSkyProgram != null ? userSkyProgram.error() : null;
    }

    @JvmStatic
    public static final boolean hasFrame() {
        return hasFrame;
    }

    @JvmStatic
    public static final void rotate(float deltaYaw, float deltaPitch) {
        manualView = true;
        yaw += deltaYaw;
        pitch = Math.clamp(pitch + deltaPitch, -1.45f, 1.45f);
    }

    @JvmStatic
    public static final void resetView() {
        manualView = false;
        pitch = 0.32f;
    }

    @JvmStatic
    public static final void reset() {
        program = null;
        compiledCode = null;
        pendingCode = null;
        renderRequested = false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void renderPending() {
        if (!renderRequested) {
            return;
        }
        renderRequested = false;
        UserSkyProgram current = program;
        if (current == null || current.hasError()) {
            return;
        }
        RenderPipeline pipeline = current.ensure();
        if (pipeline == null || !INSTANCE.ensureResources()) {
            return;
        }
        try {
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder encoder = commandEncoder;
            INSTANCE.writeUniforms(encoder);
            Supplier<String> supplier = UserSkyPreview::renderPending$lambda$0;
            GpuTextureView gpuTextureView = textureView;
            Intrinsics.checkNotNull((Object)gpuTextureView);
            AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
            Throwable throwable = null;
            try {
                RenderPass pass = (RenderPass)autoCloseable;
                boolean bl = false;
                pass.setPipeline(pipeline);
                GpuBuffer gpuBuffer = uniformBuffer;
                Intrinsics.checkNotNull((Object)gpuBuffer);
                pass.setUniform("SkyParams", gpuBuffer);
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
            hasFrame = true;
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void writeUniforms(CommandEncoder encoder) {
        float time = (float)((double)(System.currentTimeMillis() % 20000000L) / 1000.0);
        if (!manualView) {
            yaw = time * 0.045f;
        }
        PROJ.identity().perspective((float)Math.toRadians(70.0), 1.7777778f, 0.05f, 100.0f);
        VIEW.identity().rotateX(pitch).rotateY(yaw);
        VIEW_PROJ.set((Matrix4fc)PROJ).mul((Matrix4fc)VIEW);
        INV_VIEW_PROJ.set((Matrix4fc)VIEW_PROJ).invert();
        int color = 0;
        color = -10813546;
        int color2 = 0;
        color2 = -8889601;
        float brightness = 0.0f;
        brightness = 1.0f;
        float gradientMode = 0.0f;
        float useClientColor = 0.0f;
        useClientColor = 1.0f;
        Ambience ambience = Ambience.Companion.getInstance();
        if (ambience != null) {
            color = ambience.skyColorRGB();
            color2 = ambience.skyColor2RGB();
            brightness = ambience.skyBrightness();
            gradientMode = ambience.skyGradientMode();
            useClientColor = ambience.skyUsesClientColor() ? 1.0f : 0.0f;
        }
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            ByteBuffer data = stack.calloc(208);
            INV_VIEW_PROJ.get(0, data);
            data.putFloat(64, time);
            data.putFloat(68, 480.0f);
            data.putFloat(72, 270.0f);
            data.putFloat(76, brightness);
            data.putFloat(80, (float)(color >> 16 & 0xFF) / 255.0f);
            data.putFloat(84, (float)(color >> 8 & 0xFF) / 255.0f);
            data.putFloat(88, (float)(color & 0xFF) / 255.0f);
            data.putFloat(92, gradientMode);
            data.putFloat(96, (float)(color2 >> 16 & 0xFF) / 255.0f);
            data.putFloat(100, (float)(color2 >> 8 & 0xFF) / 255.0f);
            data.putFloat(104, (float)(color2 & 0xFF) / 255.0f);
            data.putFloat(108, 3.0f);
            VIEW_PROJ.get(128, data);
            data.putFloat(192, useClientColor);
            data.position(0);
            GpuBuffer gpuBuffer = uniformBuffer;
            Intrinsics.checkNotNull((Object)gpuBuffer);
            encoder.writeToBuffer(gpuBuffer.slice(0L, 208L), data);
            Unit unit = Unit.INSTANCE;
        }
        catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
    }

    private final boolean ensureResources() {
        boolean bl;
        GpuDevice gpuDevice = RenderSystem.tryGetDevice();
        if (gpuDevice == null) {
            return false;
        }
        GpuDevice device = gpuDevice;
        try {
            GpuBuffer uniform;
            GpuTexture current = texture;
            if (current == null || current.isClosed()) {
                GpuTexture gpuTexture = texture = device.createTexture(UserSkyPreview::ensureResources$lambda$0, 12, TextureFormat.RGBA8, 480, 270, 1, 1);
                Intrinsics.checkNotNull((Object)gpuTexture);
                textureView = device.createTextureView(gpuTexture);
                hasFrame = false;
                this.register();
            }
            if ((uniform = uniformBuffer) == null || uniform.isClosed()) {
                uniformBuffer = device.createBuffer(UserSkyPreview::ensureResources$lambda$1, 136, 208L);
            }
            bl = true;
        }
        catch (Throwable throwable) {
            bl = false;
        }
        return bl;
    }

    private final void register() {
        PreviewTexture preview;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        if (minecraft.getTextureManager() == null) {
            return;
        }
        if (registered != null) {
            minecraft.getTextureManager().destroyTexture(TEXTURE_ID);
        }
        registered = preview = new PreviewTexture();
        minecraft.getTextureManager().registerTexture(TEXTURE_ID, (AbstractTexture)preview);
    }

    private static final String renderPending$lambda$0() {
        return TEXTURE;
    }

    private static final String ensureResources$lambda$0() {
        return TEXTURE;
    }

    private static final String ensureResources$lambda$1() {
        return "kimiko:usersky_preview_uniforms";
    }

    static {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"usersky_preview");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        TEXTURE_ID = identifier2;
        pitch = 0.32f;
        PROJ = new Matrix4f();
        VIEW = new Matrix4f();
        VIEW_PROJ = new Matrix4f();
        INV_VIEW_PROJ = new Matrix4f();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0003\u00a8\u0006\u0006"}, d2={"Lrtx/kimiko/utils/render/modules/post/usersky/UserSkyPreview$PreviewTexture;", "Lnet/minecraft/AbstractTexture;", "<init>", "()V", "", "close", "rtx.kimiko:kimiko"})
    private static final class PreviewTexture
    extends AbstractTexture {
        public PreviewTexture() {
            this.glTexture = texture;
            this.glTextureView = textureView;
            this.sampler = RenderSystem.getSamplerCache().get(FilterMode.LINEAR);
        }

        public void close() {
        }
    }
}

