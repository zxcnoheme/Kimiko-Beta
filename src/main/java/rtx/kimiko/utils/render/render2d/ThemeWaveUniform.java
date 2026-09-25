/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Builder
 *  com.mojang.blaze3d.systems.RenderPass
 *  com.mojang.blaze3d.systems.RenderSystem
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gl.UniformType
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.system.MemoryStack
 */
package rtx.kimiko.utils.render.render2d;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gl.UniformType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.MemoryStack;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.api.ui.theme.ThemeManager;
import rtx.kimiko.api.ui.theme.ThemeWave;
import rtx.kimiko.utils.render.render2d.ClientPalette;
import rtx.kimiko.utils.render.render2d.GradientSweep;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0016\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0013\u0010\u0012\u001a\u00020\u0011H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u000bH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0014\u0010\u0003J7\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001bH\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ'\u0010!\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b!\u0010\"J\u0019\u0010&\u001a\u00020%2\b\u0010$\u001a\u0004\u0018\u00010#H\u0002\u00a2\u0006\u0004\b&\u0010'J\u0011\u0010(\u001a\u0004\u0018\u00010\u000eH\u0002\u00a2\u0006\u0004\b(\u0010\u0010J\u0013\u0010)\u001a\u00020%H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b)\u0010*J\u0013\u0010+\u001a\u00020\u000bH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b+\u0010\u0003R\u0014\u0010-\u001a\u00020,8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u0014\u0010/\u001a\u00020\u00178\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0014\u00101\u001a\u00020\u00178\u0002X\u0082D\u00a2\u0006\u0006\n\u0004\b1\u00100R\u0014\u00102\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u00100R\u0014\u00103\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b3\u00100R\u0014\u00104\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u00100R\u0014\u00105\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b5\u00100R\u0014\u00106\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b6\u00100R\u0014\u00107\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b7\u00100R\u0014\u00108\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b8\u00100R\u0014\u00109\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b9\u00100R\u0014\u0010:\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b:\u00100R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010;R\u0016\u0010<\u001a\u00020%8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u0010=R\u0014\u0010>\u001a\u00020\u001b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020\u001b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b@\u0010?R\u0016\u0010\u0012\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0012\u0010A\u00a8\u0006B"}, d2={"Lrtx/kimiko/utils/render/render2d/ThemeWaveUniform;", "", "<init>", "()V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;", "builder", "Lkotlin/jvm/JvmStatic;", "declare", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;", "Lcom/mojang/blaze3d/systems/RenderPass;", "pass", "", "bind", "(Lcom/mojang/blaze3d/systems/RenderPass;)V", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "buffer", "()Lcom/mojang/blaze3d/buffers/GpuBuffer;", "", "guiScale", "()F", "update", "Ljava/nio/ByteBuffer;", "data", "", "block", "primary", "secondary", "", "stops", "writeBlock", "(Ljava/nio/ByteBuffer;III[I)V", "off", "rgb", "putColor", "(Ljava/nio/ByteBuffer;II)V", "Lrtx/kimiko/api/modules/impl/Interface/InterfaceModule;", "module", "", "waveApplies", "(Lrtx/kimiko/api/modules/impl/Interface/InterfaceModule;)Z", "ensureBuffer", "available", "()Z", "closeBuffer", "", "BLOCK", "Ljava/lang/String;", "STOPS", "I", "MAX_LAYERS", "VEC4_BYTES", "VEC4_COUNT", "TOTAL_BYTES", "IDX_META", "IDX_META2", "IDX_SCREEN", "IDX_WAVE", "IDX_BLOCK", "BLOCK_VEC4", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "createFailed", "Z", "baseStops", "[I", "layerStops", "F", "rtx.kimiko:kimiko"})
public final class ThemeWaveUniform {
    @NotNull
    public static final ThemeWaveUniform INSTANCE = new ThemeWaveUniform();
    @NotNull
    public static final String BLOCK = "ThemeWaveParams";
    public static final int STOPS = 6;
    private static final int MAX_LAYERS = 6;
    private static final int VEC4_BYTES = 16;
    private static final int VEC4_COUNT = 65;
    private static final int TOTAL_BYTES = 1040;
    private static final int IDX_META = 0;
    private static final int IDX_META2 = 1;
    private static final int IDX_SCREEN = 2;
    private static final int IDX_WAVE = 3;
    private static final int IDX_BLOCK = 9;
    private static final int BLOCK_VEC4 = 8;
    @Nullable
    private static GpuBuffer buffer;
    private static boolean createFailed;
    @NotNull
    private static final int[] baseStops;
    @NotNull
    private static final int[] layerStops;
    private static float guiScale;

    private ThemeWaveUniform() {
    }

    @JvmStatic
    @NotNull
    public static final RenderPipeline.Builder declare(@NotNull RenderPipeline.Builder builder) {
        Intrinsics.checkNotNullParameter((Object)builder, (String)"builder");
        RenderPipeline.Builder builder2 = builder.withUniform(BLOCK, UniformType.UNIFORM_BUFFER);
        Intrinsics.checkNotNullExpressionValue((Object)builder2, (String)"withUniform(...)");
        return builder2;
    }

    @JvmStatic
    public static final void bind(@Nullable RenderPass pass) {
        GpuBuffer buf = INSTANCE.ensureBuffer();
        if (pass != null && buf != null) {
            pass.setUniform(BLOCK, buf);
        }
    }

    @JvmStatic
    @Nullable
    public static final GpuBuffer buffer() {
        return INSTANCE.ensureBuffer();
    }

    @JvmStatic
    public static final float guiScale() {
        return guiScale;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void update() {
        GpuBuffer gpuBuffer = INSTANCE.ensureBuffer();
        if (gpuBuffer == null) {
            return;
        }
        GpuBuffer buf = gpuBuffer;
        InterfaceModule module = InterfaceModule.Companion.getInstance();
        int layerCount = INSTANCE.waveApplies(module) ? Math.min(MAX_LAYERS, ThemeWave.layerCount()) : 0;
        float screenW = Math.max(1.0f, ThemeWave.screenPixelWidth());
        float screenH = Math.max(1.0f, ThemeWave.screenPixelHeight());
        float aspect = screenW / screenH;
        guiScale = screenW / Math.max(1.0f, Position.Companion.screenWidth());
        int basePrimary = 0;
        int baseSecondary = 0;
        if (layerCount > 0) {
            basePrimary = ThemeWave.baseShade(5);
            baseSecondary = ThemeWave.baseShade(6);
            ClientPalette.resampleStops(ThemeWave.basePalette(), baseStops);
        } else {
            basePrimary = ClientAccent.gradientA(255.0f);
            baseSecondary = ClientAccent.gradientB(255.0f);
            System.arraycopy(ClientPalette.colors(), 0, baseStops, 0, 6);
        }
        try {
            AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
            Throwable throwable = null;
            try {
                MemoryStack stack = (MemoryStack)autoCloseable;
                boolean bl = false;
                ByteBuffer data = stack.calloc(1040);
                int meta = 0;
                data.putFloat(meta, 6.0f);
                data.putFloat(meta + 4, ClientPalette.phase());
                data.putFloat(meta + 8, ClientPalette.styleId());
                data.putFloat(meta + 12, GradientSweep.progress());
                int meta2 = 16;
                data.putFloat(meta2, ClientPalette.prevStyle());
                data.putFloat(meta2 + 4, ClientPalette.closed());
                data.putFloat(meta2 + 8, ClientPalette.scrollPhase());
                data.putFloat(meta2 + 12, layerCount);
                int screen = 32;
                data.putFloat(screen, screenW);
                data.putFloat(screen + 4, screenH);
                data.putFloat(screen + 8, 0.018f);
                data.putFloat(screen + 12, guiScale);
                Intrinsics.checkNotNull((Object)data);
                INSTANCE.writeBlock(data, 0, basePrimary, baseSecondary, baseStops);
                int n = MAX_LAYERS;
                for (int layer = 0; layer < n; ++layer) {
                    ByteBuffer byteBuffer;
                    int wave = (3 + layer) * 16;
                    if (layer >= layerCount) {
                        INSTANCE.writeBlock(data, 1 + layer, basePrimary, baseSecondary, baseStops);
                        data.putFloat(wave + 12, 0.0f);
                        continue;
                    }
                    ClientPalette.resampleStops(ThemeWave.layerPalette(layer), layerStops);
                    INSTANCE.writeBlock(data, 1 + layer, ThemeWave.layerShade(layer, 5), ThemeWave.layerShade(layer, 6), layerStops);
                    if (ThemeWave.layerTimed(layer)) {
                        data.putFloat(wave, 0.5f);
                        data.putFloat(wave + 4, 0.5f);
                        data.putFloat(wave + 8, ThemeWave.layerCoverage(layer));
                        byteBuffer = data.putFloat(wave + 12, 2.0f);
                        continue;
                    }
                    data.putFloat(wave, ThemeWave.layerCenterX(layer));
                    data.putFloat(wave + 4, ThemeWave.layerCenterY(layer));
                    data.putFloat(wave + 8, ThemeWave.layerSweptRadius(layer, aspect));
                    byteBuffer = data.putFloat(wave + 12, 1.0f);
                }
                data.position(0);
                RenderSystem.getDevice().createCommandEncoder().writeToBuffer(buf.slice(0L, 1040L), data);
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
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    private final void writeBlock(ByteBuffer data, int block, int primary, int secondary, int[] stops) {
        int base = (9 + block * 8) * 16;
        this.putColor(data, base, primary);
        this.putColor(data, base + 16, secondary);
        for (int i = 0; i < 6; ++i) {
            this.putColor(data, base + (2 + i) * 16, stops[i]);
        }
    }

    private final void putColor(ByteBuffer data, int off, int rgb) {
        int c = rgb & 0xFFFFFF;
        data.putFloat(off, (float)(c >>> 16 & 0xFF) / 255.0f);
        data.putFloat(off + 4, (float)(c >>> 8 & 0xFF) / 255.0f);
        data.putFloat(off + 8, (float)(c & 0xFF) / 255.0f);
        data.putFloat(off + 12, 1.0f);
    }

    private final boolean waveApplies(InterfaceModule module) {
        if (!ThemeManager.waveBound()) {
            return false;
        }
        if (module != null && !module.isThemeClientColor()) {
            return false;
        }
        return !ClientAccent.isModeTransitioning();
    }

    private final GpuBuffer ensureBuffer() {
        GpuBuffer current = buffer;
        if (current != null && !current.isClosed() && current.size() >= 1040L) {
            return current;
        }
        if (createFailed) {
            return null;
        }
        ThemeWaveUniform.closeBuffer();
        try {
            GpuBuffer created = RenderSystem.getDevice().createBuffer(ThemeWaveUniform::ensureBuffer$lambda$0, 136, 1040L);
            buffer = created;
            return created;
        }
        catch (RuntimeException ignored) {
            createFailed = true;
            return null;
        }
    }

    @JvmStatic
    public static final boolean available() {
        return INSTANCE.ensureBuffer() != null;
    }

    @JvmStatic
    public static final void closeBuffer() {
        createFailed = false;
        GpuBuffer gpuBuffer = buffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        buffer = null;
    }

    private static final String ensureBuffer$lambda$0() {
        return "kimiko_theme_wave";
    }

    static {
        baseStops = new int[6];
        layerStops = new int[6];
        guiScale = 1.0f;
    }
}

