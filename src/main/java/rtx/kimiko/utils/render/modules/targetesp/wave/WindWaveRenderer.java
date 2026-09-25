/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Snippet
 *  com.mojang.blaze3d.systems.CommandEncoder
 *  com.mojang.blaze3d.systems.RenderPass
 *  com.mojang.blaze3d.systems.RenderSystem
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gl.UniformType
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.util.Identifier
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.system.MemoryStack
 */
package rtx.kimiko.utils.render.modules.targetesp.wave;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.MemoryStack;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.modules.impl.Visuals.Ambience;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u001b\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0003J\u000f\u0010\u0010\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u0017\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001dR\u0018\u0010 \u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0016\u0010#\u001a\u00020\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010%\u001a\u00020\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u0010$R\u0016\u0010'\u001a\u00020&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010)\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010\u001b\u00a8\u0006*"}, d2={"Lrtx/kimiko/utils/render/modules/targetesp/wave/WindWaveRenderer;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "beginFrame", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "substitute", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lcom/mojang/blaze3d/systems/RenderPass;", "pass", "bindParams", "(Lcom/mojang/blaze3d/systems/RenderPass;)V", "init", "closeUniform", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "", "UNIFORM_SIZE", "I", "", "TIME_WRAP", "D", "solidPipeline", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "cutoutPipeline", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "uniformBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "", "disabledAfterError", "Z", "active", "", "lastNanos", "J", "phase", "rtx.kimiko:kimiko"})
public final class WindWaveRenderer {
    @NotNull
    public static final WindWaveRenderer INSTANCE = new WindWaveRenderer();
    private static final int UNIFORM_SIZE = 16;
    private static final double TIME_WRAP = 3600.0;
    @Nullable
    private static RenderPipeline solidPipeline;
    @Nullable
    private static RenderPipeline cutoutPipeline;
    @Nullable
    private static GpuBuffer uniformBuffer;
    private static boolean disabledAfterError;
    private static boolean active;
    private static long lastNanos;
    private static double phase;

    private WindWaveRenderer() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void beginFrame() {
        Ambience ambience = Ambience.Companion.getInstance();
        if (disabledAfterError || ambience == null || !ambience.isWindActive()) {
            active = false;
            lastNanos = 0L;
            return;
        }
        try {
            INSTANCE.init();
            if (disabledAfterError) {
                active = false;
                return;
            }
            long now = System.nanoTime();
            float dt = lastNanos == 0L ? 0.016f : Math.min((float)(now - lastNanos) * 1.0E-9f, 0.1f);
            lastNanos = now;
            double speed = 0.25 + (double)ambience.getWindSpeed() * 1.5;
            phase = (phase + (double)dt * speed) % 3600.0;
            AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
            Throwable throwable = null;
            try {
                MemoryStack stack = (MemoryStack)autoCloseable;
                boolean bl = false;
                ByteBuffer data = stack.calloc(16);
                data.putFloat(0, (float)phase);
                data.putFloat(4, ambience.getWindGrassStrength() * 2.0f);
                data.putFloat(8, ambience.getWindLeavesStrength() * 2.0f);
                data.putFloat(12, ambience.hasWindGusts() ? 0.04f : 0.0f);
                CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
                GpuBuffer gpuBuffer = uniformBuffer;
                Intrinsics.checkNotNull((Object)gpuBuffer);
                commandEncoder.writeToBuffer(gpuBuffer.slice(0L, 16L), data);
                Unit unit = Unit.INSTANCE;
            }
            catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            }
            finally {
                AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
            }
            active = true;
        }
        catch (Throwable throwable) {
            disabledAfterError = true;
            active = false;
            INSTANCE.closeUniform();
        }
    }

    @JvmStatic
    @NotNull
    public static final RenderPipeline substitute(@NotNull RenderPipeline pipeline) {
        Intrinsics.checkNotNullParameter((Object)pipeline, (String)"pipeline");
        GpuBuffer buffer = uniformBuffer;
        if (!active || buffer == null || buffer.isClosed()) {
            return pipeline;
        }
        if (pipeline == RenderPipelines.SOLID_TERRAIN && solidPipeline != null) {
            RenderPipeline renderPipeline = solidPipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            return renderPipeline;
        }
        if (pipeline == RenderPipelines.CUTOUT_TERRAIN && cutoutPipeline != null) {
            RenderPipeline renderPipeline = cutoutPipeline;
            Intrinsics.checkNotNull((Object)renderPipeline);
            return renderPipeline;
        }
        return pipeline;
    }

    @JvmStatic
    public static final void bindParams(@NotNull RenderPass pass) {
        Intrinsics.checkNotNullParameter((Object)pass, (String)"pass");
        GpuBuffer buffer = uniformBuffer;
        if (buffer != null && !buffer.isClosed()) {
            pass.setUniform("WaveParams", buffer);
        }
    }

    private final void init() {
        GpuBuffer buffer;
        if (solidPipeline == null || cutoutPipeline == null) {
            RenderPipeline.Snippet[] snippetArray = new RenderPipeline.Snippet[]{RenderPipelines.TERRAIN_SNIPPET};
            RenderPipeline renderPipeline = RenderPipeline.builder((RenderPipeline.Snippet[])snippetArray).withLocation(this.id("pipeline/wave_solid_terrain")).withVertexShader(this.id("core/terrain_wave")).withUniform("WaveParams", UniformType.UNIFORM_BUFFER).build();
            Intrinsics.checkNotNullExpressionValue((Object)renderPipeline, (String)"build(...)");
            RenderPipeline solid = renderPipeline;
            RenderPipeline.Snippet[] snippetArray2 = new RenderPipeline.Snippet[]{RenderPipelines.TERRAIN_SNIPPET};
            RenderPipeline renderPipeline2 = RenderPipeline.builder((RenderPipeline.Snippet[])snippetArray2).withLocation(this.id("pipeline/wave_cutout_terrain")).withVertexShader(this.id("core/terrain_wave")).withUniform("WaveParams", UniformType.UNIFORM_BUFFER).withShaderDefine("ALPHA_CUTOUT", 0.5f).build();
            Intrinsics.checkNotNullExpressionValue((Object)renderPipeline2, (String)"build(...)");
            RenderPipeline cutout = renderPipeline2;
            if (!RenderSystem.getDevice().precompilePipeline(solid).isValid() || !RenderSystem.getDevice().precompilePipeline(cutout).isValid()) {
                disabledAfterError = true;
                return;
            }
            solidPipeline = RenderPipelines.register((RenderPipeline)solid);
            cutoutPipeline = RenderPipelines.register((RenderPipeline)cutout);
        }
        if ((buffer = uniformBuffer) == null || buffer.isClosed()) {
            uniformBuffer = RenderSystem.getDevice().createBuffer(WindWaveRenderer::init$lambda$0, 136, 16L);
        }
    }

    private final void closeUniform() {
        GpuBuffer buffer = uniformBuffer;
        if (buffer != null) {
            try {
                if (!buffer.isClosed()) {
                    buffer.close();
                }
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            uniformBuffer = null;
        }
    }

    private final Identifier id(String path) {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        return identifier2;
    }

    private static final String init$lambda$0() {
        return "kimiko_wave_params";
    }
}

