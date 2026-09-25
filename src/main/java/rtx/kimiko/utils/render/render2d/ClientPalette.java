/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  com.mojang.blaze3d.systems.RenderSystem
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.system.MemoryStack
 */
package rtx.kimiko.utils.render.render2d;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.systems.RenderSystem;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.MemoryStack;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.api.ui.theme.ThemeManager;
import rtx.kimiko.api.ui.theme.ThemeWave;
import rtx.kimiko.utils.render.render2d.GradientSweep;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\t\n\u0002\b\u0013\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J#\u0010\u000b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\r\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\r\u0010\fJ\u001b\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000f\u0010\u0010J+\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0013\u0010\u0017\u001a\u00020\u0016H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001b\u0010\u001aJ\u0013\u0010\u001c\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001c\u0010\u001aJ\u0013\u0010\u001d\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001d\u0010\u001aJ\u0013\u0010\u001f\u001a\u00020\u001eH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001f\u0010 J\u0013\u0010\"\u001a\u00020!H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\"\u0010\u0003J\u0019\u0010%\u001a\u00020\u001e2\b\u0010$\u001a\u0004\u0018\u00010#H\u0002\u00a2\u0006\u0004\b%\u0010&J\u0019\u0010(\u001a\u00020\u00042\b\u0010'\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b(\u0010)J'\u0010.\u001a\u00020!2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020\u00162\u0006\u0010-\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b.\u0010/J=\u00105\u001a\u00020!2\u0006\u00100\u001a\u00020\u00162\b\u00101\u001a\u0004\u0018\u00010\u00042\u0006\u00102\u001a\u00020\b2\u0006\u00103\u001a\u00020\b2\u0006\u00104\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b5\u00106JM\u00105\u001a\u00020!2\u0006\u00100\u001a\u00020\u00162\b\u00101\u001a\u0004\u0018\u00010\u00042\u0006\u00102\u001a\u00020\b2\u0006\u00103\u001a\u00020\b2\u0006\u00104\u001a\u00020\b2\u0006\u00107\u001a\u00020\b2\u0006\u00108\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b5\u00109J%\u0010;\u001a\u00020!2\b\u0010'\u001a\u0004\u0018\u00010\u00042\u0006\u0010:\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b;\u0010<J\u001f\u0010=\u001a\u00020!2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010:\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b=\u0010<J'\u0010A\u001a\u00020\u00162\u0006\u0010>\u001a\u00020\u00162\u0006\u0010?\u001a\u00020\u00162\u0006\u0010@\u001a\u00020\bH\u0002\u00a2\u0006\u0004\bA\u0010BJ\u001b\u0010C\u001a\u00020\u00162\u0006\u0010@\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\bC\u0010DJ+\u0010E\u001a\u00020\u00162\u0006\u0010@\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\bE\u0010FJ!\u0010G\u001a\u00020\u00162\b\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u0006\u0010@\u001a\u00020\bH\u0002\u00a2\u0006\u0004\bG\u0010HJ'\u0010J\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010@\u001a\u00020\b2\u0006\u0010I\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\bJ\u0010KJ'\u0010N\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010L\u001a\u00020\u00162\u0006\u0010M\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\bN\u0010OJ\u0013\u0010P\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\bP\u0010\u001aJ\u0015\u0010R\u001a\u0004\u0018\u00010QH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\bR\u0010SJ\u0013\u0010T\u001a\u00020!H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\bT\u0010\u0003J\u0017\u0010U\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020*H\u0002\u00a2\u0006\u0004\bU\u0010VJ\u0011\u0010W\u001a\u0004\u0018\u00010QH\u0002\u00a2\u0006\u0004\bW\u0010SR\u0014\u0010X\u001a\u00020\u00168\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bX\u0010YR\u0014\u0010Z\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bZ\u0010YR\u0014\u0010[\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b[\u0010YR\u0014\u0010\\\u001a\u00020\u00168\u0002X\u0082D\u00a2\u0006\u0006\n\u0004\b\\\u0010YR\u0014\u0010]\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b]\u0010YR\u0014\u0010^\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b^\u0010YR\u0014\u0010_\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b_\u0010YR\u0014\u0010`\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b`\u0010YR\u0014\u0010a\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\ba\u0010YR\u0014\u0010b\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bb\u0010YR\u0014\u0010c\u001a\u00020\u00168\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bc\u0010YR\u0014\u0010d\u001a\u00020\u00168\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bd\u0010YR\u0014\u0010e\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\be\u0010YR\u0014\u0010g\u001a\u00020f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bg\u0010hR\u0014\u0010i\u001a\u00020f8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bi\u0010hR\u0018\u0010R\u001a\u0004\u0018\u00010Q8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bR\u0010jR\u0018\u0010k\u001a\u0004\u0018\u00010*8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bk\u0010lR\u0014\u0010m\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bm\u0010nR\u0014\u0010o\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bo\u0010nR\u0014\u0010p\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bp\u0010nR\u0014\u0010q\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bq\u0010nR\u0016\u0010r\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\br\u0010sR\u0014\u0010t\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bt\u0010nR\u0016\u0010\u0017\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0017\u0010YR\u0016\u0010\u0019\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010uR\u0016\u0010v\u001a\u00020f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bv\u0010hR\u0016\u0010\u001b\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010uR\u0016\u0010\u001c\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u0010uR\u0016\u0010w\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bw\u0010uR\u0016\u0010\u001d\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010uR\u0014\u0010x\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bx\u0010n\u00a8\u0006y"}, d2={"Lrtx/kimiko/utils/render/render2d/ClientPalette;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "colors", "()[I", "", "designX", "designY", "colorsAt", "(FF)[I", "sampleShared", "alpha", "cornerColors", "(F)[I", "cornerColorsAt", "(FFF)[I", "cols", "cornerColorsFrom", "([IF)[I", "", "count", "()I", "phase", "()F", "styleId", "prevStyle", "closed", "", "isTransitioning", "()Z", "", "update", "Lrtx/kimiko/api/modules/impl/Interface/InterfaceModule;", "module", "themeWaveApplies", "(Lrtx/kimiko/api/modules/impl/Interface/InterfaceModule;)Z", "palette", "safePalette", "([I)[I", "Ljava/nio/ByteBuffer;", "data", "off", "rgb", "putColor", "(Ljava/nio/ByteBuffer;II)V", "slot", "colors6", "slotPhase", "slotStyleId", "slotClosed", "writeRemoteSlot", "(I[IFFF)V", "slotSweep", "slotPrevStyle", "(I[IFFFFF)V", "out", "resampleStops", "([I[I)V", "resample", "a", "b", "t", "mixRgb", "(IIF)I", "loopColor", "(F)I", "loopColorAt", "(FFF)I", "loopColorFrom", "([IF)I", "channel", "rampChannel", "([IFI)F", "idx", "k", "chan", "([III)F", "scrollPhase", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "buffer", "()Lcom/mojang/blaze3d/buffers/GpuBuffer;", "closeBuffer", "mainUploadChanged", "(Ljava/nio/ByteBuffer;)Z", "ensureBuffer", "MAX_STOPS", "I", "VEC4_FLOATS", "VEC4_BYTES", "MAX_LAYERS", "META2_OFFSET", "LAYER_STRIDE", "LAYER_STOPS_OFFSET", "LAYER_WAVE_OFFSET", "WAVE2_OFFSET", "UNIFORM_BYTES", "SLOT_STRIDE_BYTES", "SLOTS", "TOTAL_BYTES", "", "PERIOD_MS", "J", "SCROLL_PERIOD_MS", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "lastMainUpload", "Ljava/nio/ByteBuffer;", "displayed", "[I", "waveNewStops", "waveOldStops", "waveSampled", "waveActive", "Z", "fallback", "F", "lastMs", "lastStyleId", "scratchCornerColors", "rtx.kimiko:kimiko"})
public final class ClientPalette {
    @NotNull
    public static final ClientPalette INSTANCE = new ClientPalette();
    public static final int MAX_STOPS = 6;
    private static final int VEC4_FLOATS = 4;
    private static final int VEC4_BYTES = 16;
    private static final int MAX_LAYERS = 6;
    private static final int META2_OFFSET = 112;
    private static final int LAYER_STRIDE = 128;
    private static final int LAYER_STOPS_OFFSET = 128;
    private static final int LAYER_WAVE_OFFSET = 224;
    private static final int WAVE2_OFFSET = 240;
    private static final int UNIFORM_BYTES = 896;
    public static final int SLOT_STRIDE_BYTES = 896;
    public static final int SLOTS = 5;
    private static final int TOTAL_BYTES = 4480;
    private static final long PERIOD_MS = 24000L;
    public static final long SCROLL_PERIOD_MS = 2200L;
    @Nullable
    private static GpuBuffer buffer;
    @Nullable
    private static ByteBuffer lastMainUpload;
    @NotNull
    private static final int[] displayed;
    @NotNull
    private static final int[] waveNewStops;
    @NotNull
    private static final int[] waveOldStops;
    @NotNull
    private static final int[] waveSampled;
    private static boolean waveActive;
    @NotNull
    private static final int[] fallback;
    private static int count;
    private static float phase;
    private static long lastMs;
    private static float styleId;
    private static float prevStyle;
    private static float lastStyleId;
    private static float closed;
    @NotNull
    private static final int[] scratchCornerColors;

    private ClientPalette() {
    }

    @JvmStatic
    @NotNull
    public static final int[] colors() {
        return displayed;
    }

    @JvmStatic
    @NotNull
    public static final int[] colorsAt(float designX, float designY) {
        if (!waveActive) {
            return displayed;
        }
        int[] out = new int[6];
        INSTANCE.resample(INSTANCE.safePalette(ThemeManager.blendedPaletteAt(designX, designY)), out);
        return out;
    }

    private final int[] sampleShared(float designX, float designY) {
        if (!waveActive) {
            return displayed;
        }
        this.resample(this.safePalette(ThemeManager.blendedPaletteAt(designX, designY)), waveSampled);
        return waveSampled;
    }

    @JvmStatic
    @NotNull
    public static final int[] cornerColors(float alpha) {
        return INSTANCE.cornerColorsFrom(displayed, alpha);
    }

    @JvmStatic
    @NotNull
    public static final int[] cornerColorsAt(float alpha, float designX, float designY) {
        return INSTANCE.cornerColorsFrom(INSTANCE.sampleShared(designX, designY), alpha);
    }

    private final int[] cornerColorsFrom(int[] cols, float alpha) {
        int a = Math.round(Math.max(0.0f, Math.min(1.0f, alpha)) * 255.0f);
        float base = phase;
        ClientPalette.scratchCornerColors[0] = a << 24 | this.loopColorFrom(cols, base);
        ClientPalette.scratchCornerColors[1] = a << 24 | this.loopColorFrom(cols, base + 0.25f);
        ClientPalette.scratchCornerColors[2] = a << 24 | this.loopColorFrom(cols, base + 0.5f);
        ClientPalette.scratchCornerColors[3] = a << 24 | this.loopColorFrom(cols, base + 0.75f);
        return scratchCornerColors;
    }

    @JvmStatic
    public static final int count() {
        return count;
    }

    @JvmStatic
    public static final float phase() {
        return phase;
    }

    @JvmStatic
    public static final float styleId() {
        return styleId;
    }

    @JvmStatic
    public static final float prevStyle() {
        return prevStyle;
    }

    @JvmStatic
    public static final float closed() {
        return closed;
    }

    @JvmStatic
    public static final boolean isTransitioning() {
        return ClientAccent.isModeTransitioning();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void update() {
        boolean waveOn;
        int[] source = ClientAccent.blendedClientPalette();
        if (source == null || source.length == 0) {
            source = fallback;
        }
        INSTANCE.resample(source, displayed);
        count = 6;
        closed = ClientAccent.closedFactor();
        InterfaceModule module = InterfaceModule.Companion.getInstance();
        long now = System.currentTimeMillis();
        if (lastMs != 0L && module != null && module.clientColorMovement() && (phase = (phase + (float)(now - lastMs) / 24000.0f) % 1.0f) < 0.0f) {
            phase += 1.0f;
        }
        lastMs = now;
        InterfaceModule interfaceModule = module;
        float f = styleId = interfaceModule == null ? 1.0f : (float)interfaceModule.gradientStyleId();
        if (lastStyleId >= 0.0f && !(styleId == lastStyleId)) {
            prevStyle = lastStyleId;
            GradientSweep.trigger();
        }
        lastStyleId = styleId;
        waveActive = waveOn = INSTANCE.themeWaveApplies(module);
        float screenW = Math.max(1.0f, ThemeWave.screenPixelWidth());
        float screenH = Math.max(1.0f, ThemeWave.screenPixelHeight());
        float aspect = screenW / screenH;
        GpuBuffer gpuBuffer = INSTANCE.ensureBuffer();
        if (gpuBuffer == null) {
            return;
        }
        GpuBuffer buf = gpuBuffer;
        try {
            AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
            Throwable throwable = null;
            try {
                MemoryStack stack = (MemoryStack)autoCloseable;
                boolean bl = false;
                ByteBuffer data = stack.calloc(896);
                data.putFloat(0, count);
                data.putFloat(4, phase);
                data.putFloat(8, styleId);
                data.putFloat(12, GradientSweep.progress());
                data.putFloat(112, prevStyle);
                data.putFloat(116, closed);
                data.putFloat(120, ClientPalette.scrollPhase());
                if (waveOn) {
                    INSTANCE.resample(INSTANCE.safePalette(ThemeWave.basePalette()), waveOldStops);
                } else {
                    System.arraycopy(displayed, 0, waveOldStops, 0, 6);
                }
                for (int i = 0; i < 6; ++i) {
                    Intrinsics.checkNotNull((Object)data);
                    INSTANCE.putColor(data, (1 + i) * 16, waveOldStops[i]);
                }
                int layerCount = waveOn ? Math.min(MAX_LAYERS, ThemeWave.layerCount()) : 0;
                int n = MAX_LAYERS;
                for (int layer = 0; layer < n; ++layer) {
                    ByteBuffer byteBuffer;
                    int i;
                    int stopsBase = 128 + layer * 128;
                    int waveBase = 224 + layer * 128;
                    if (layer >= layerCount) {
                        for (i = 0; i < 6; ++i) {
                            Intrinsics.checkNotNull((Object)data);
                            INSTANCE.putColor(data, stopsBase + i * 16, waveOldStops[i]);
                        }
                        data.putFloat(waveBase + 12, 0.0f);
                        continue;
                    }
                    INSTANCE.resample(INSTANCE.safePalette(ThemeWave.layerPalette(layer)), waveNewStops);
                    for (i = 0; i < 6; ++i) {
                        Intrinsics.checkNotNull((Object)data);
                        INSTANCE.putColor(data, stopsBase + i * 16, waveNewStops[i]);
                    }
                    if (ThemeWave.layerTimed(layer)) {
                        data.putFloat(waveBase, 0.5f);
                        data.putFloat(waveBase + 4, 0.5f);
                        data.putFloat(waveBase + 8, ThemeWave.layerCoverage(layer));
                        byteBuffer = data.putFloat(waveBase + 12, 2.0f);
                        continue;
                    }
                    data.putFloat(waveBase, ThemeWave.layerCenterX(layer));
                    data.putFloat(waveBase + 4, ThemeWave.layerCenterY(layer));
                    data.putFloat(waveBase + 8, ThemeWave.layerSweptRadius(layer, aspect));
                    byteBuffer = data.putFloat(waveBase + 12, 1.0f);
                }
                data.putFloat(240, screenW);
                data.putFloat(244, screenH);
                data.putFloat(248, 0.018f);
                data.putFloat(252, 0.0f);
                data.position(0);
                Intrinsics.checkNotNull((Object)data);
                if (INSTANCE.mainUploadChanged(data)) {
                    RenderSystem.getDevice().createCommandEncoder().writeToBuffer(buf.slice(0L, 896L), data);
                }
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

    private final boolean themeWaveApplies(InterfaceModule module) {
        if (!ThemeManager.waveBound()) {
            return false;
        }
        if (module != null && !module.isThemeClientColor()) {
            return false;
        }
        return !ClientAccent.isModeTransitioning();
    }

    private final int[] safePalette(int[] palette) {
        return palette == null || palette.length == 0 ? fallback : palette;
    }

    private final void putColor(ByteBuffer data, int off, int rgb) {
        int c = rgb & 0xFFFFFF;
        data.putFloat(off, (float)(c >>> 16 & 0xFF) / 255.0f);
        data.putFloat(off + 4, (float)(c >>> 8 & 0xFF) / 255.0f);
        data.putFloat(off + 8, (float)(c & 0xFF) / 255.0f);
        data.putFloat(off + 12, 1.0f);
    }

    @JvmStatic
    public static final void writeRemoteSlot(int slot, @Nullable int[] colors6, float slotPhase, float slotStyleId, float slotClosed) {
        ClientPalette.writeRemoteSlot(slot, colors6, slotPhase, slotStyleId, slotClosed, -1.0f, slotStyleId);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void writeRemoteSlot(int slot, @Nullable int[] colors6, float slotPhase, float slotStyleId, float slotClosed, float slotSweep, float slotPrevStyle) {
        if (slot <= 0 || slot >= 5 || colors6 == null || colors6.length == 0) {
            return;
        }
        GpuBuffer gpuBuffer = INSTANCE.ensureBuffer();
        if (gpuBuffer == null) {
            return;
        }
        GpuBuffer buf = gpuBuffer;
        try {
            AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
            Throwable throwable = null;
            try {
                MemoryStack stack = (MemoryStack)autoCloseable;
                boolean bl = false;
                ByteBuffer data = stack.calloc(896);
                data.putFloat(0, 6.0f);
                data.putFloat(4, slotPhase);
                data.putFloat(8, slotStyleId);
                data.putFloat(12, slotSweep);
                data.putFloat(112, slotPrevStyle);
                data.putFloat(116, slotClosed);
                data.putFloat(120, ClientPalette.scrollPhase());
                for (int i = 0; i < 6; ++i) {
                    int c = colors6[Math.min(i, colors6.length - 1)];
                    Intrinsics.checkNotNull((Object)data);
                    INSTANCE.putColor(data, (1 + i) * 16, c);
                    int n = MAX_LAYERS;
                    for (int layer = 0; layer < n; ++layer) {
                        INSTANCE.putColor(data, 128 + layer * 128 + i * 16, c);
                    }
                }
                int n = MAX_LAYERS;
                for (int layer = 0; layer < n; ++layer) {
                    data.putFloat(224 + layer * 128 + 12, 0.0f);
                }
                data.putFloat(240, 1.0f);
                data.putFloat(244, 1.0f);
                data.position(0);
                RenderSystem.getDevice().createCommandEncoder().writeToBuffer(buf.slice((long)(slot * 896), 896L), data);
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

    @JvmStatic
    public static final void resampleStops(@Nullable int[] palette, @NotNull int[] out) {
        Intrinsics.checkNotNullParameter((Object)out, (String)"out");
        INSTANCE.resample(INSTANCE.safePalette(palette), out);
    }

    private final void resample(int[] palette, int[] out) {
        if (palette.length == 1) {
            int c = palette[0] & 0xFFFFFF;
            int n = out.length;
            for (int i = 0; i < n; ++i) {
                out[i] = c;
            }
            return;
        }
        int n = out.length;
        for (int i = 0; i < n; ++i) {
            float pos = out.length <= 1 ? 0.0f : (float)i / (float)(out.length - 1);
            float f = pos * (float)(palette.length - 1);
            int idx = (int)f;
            if (idx > palette.length - 2) {
                idx = palette.length - 2;
            }
            out[i] = this.mixRgb(palette[idx], palette[idx + 1], f - (float)idx);
        }
    }

    private final int mixRgb(int a, int b, float t) {
        float tt = t < 0.0f ? 0.0f : Math.min(t, 1.0f);
        int ar = a >> 16 & 0xFF;
        int ag = a >> 8 & 0xFF;
        int ab = a & 0xFF;
        int br = b >> 16 & 0xFF;
        int bg = b >> 8 & 0xFF;
        int bb = b & 0xFF;
        int r = Math.round((float)ar + (float)(br - ar) * tt);
        int g = Math.round((float)ag + (float)(bg - ag) * tt);
        int bl = Math.round((float)ab + (float)(bb - ab) * tt);
        return r << 16 | g << 8 | bl;
    }

    @JvmStatic
    public static final int loopColor(float t) {
        return INSTANCE.loopColorFrom(displayed, t);
    }

    @JvmStatic
    public static final int loopColorAt(float t, float designX, float designY) {
        return INSTANCE.loopColorFrom(INSTANCE.sampleShared(designX, designY), t);
    }

    private final int loopColorFrom(int[] cols, float t) {
        int i3;
        int i2;
        int n = count;
        if (cols == null || cols.length == 0) {
            return 0xFFFFFF;
        }
        if (n <= 1) {
            return cols[0] & 0xFFFFFF;
        }
        float tf = t - (float)Math.floor(t);
        float f = tf * (float)n;
        int i1 = Math.max(0, Math.min(n - 1, (int)Math.floor(f)));
        float u = Math.max(0.0f, Math.min(1.0f, f - (float)i1));
        int i0 = i1 - 1;
        if (i0 < 0) {
            i0 += n;
        }
        if ((i2 = i1 + 1) >= n) {
            i2 -= n;
        }
        if ((i3 = i1 + 2) >= n) {
            i3 -= n;
        }
        float u2 = u * u;
        float u3 = u2 * u;
        float tri = 0.5f - 0.5f * (float)Math.cos((float)Math.PI * 2 * tf);
        float closedFactor = Math.max(0.0f, Math.min(1.0f, closed));
        int out = 0;
        for (int k = 0; k < 3; ++k) {
            float c0 = this.chan(cols, i0, k);
            float c1 = this.chan(cols, i1, k);
            float c2 = this.chan(cols, i2, k);
            float c3 = this.chan(cols, i3, k);
            float cyclic = 0.5f * (2.0f * c1 + (-c0 + c2) * u + (2.0f * c0 - 5.0f * c1 + 4.0f * c2 - c3) * u2 + (-c0 + 3.0f * c1 - 3.0f * c2 + c3) * u3);
            float mirror = this.rampChannel(cols, tri, k);
            float v = mirror + (cyclic - mirror) * closedFactor;
            int b = Math.round(Math.max(0.0f, Math.min(1.0f, v)) * 255.0f);
            out |= b << 16 - k * 8;
        }
        return out;
    }

    private final float rampChannel(int[] cols, float t, int channel) {
        int n = count;
        if (n <= 1) {
            return this.chan(cols, 0, channel);
        }
        float tt = Math.max(0.0f, Math.min(1.0f, t));
        float f = tt * (float)(n - 1);
        int i = Math.max(0, Math.min(n - 1, (int)Math.floor(f)));
        int j = Math.min(i + 1, n - 1);
        float frac = Math.max(0.0f, Math.min(1.0f, f - (float)i));
        frac = frac * frac * (3.0f - 2.0f * frac);
        float a = this.chan(cols, i, channel);
        float b = this.chan(cols, j, channel);
        return a + (b - a) * frac;
    }

    private final float chan(int[] cols, int idx, int k) {
        int c = cols[Math.max(0, Math.min(cols.length - 1, idx))];
        return (float)(c >> 16 - k * 8 & 0xFF) / 255.0f;
    }

    @JvmStatic
    public static final float scrollPhase() {
        return (float)(System.currentTimeMillis() % 2200L) / 2200.0f;
    }

    @JvmStatic
    @Nullable
    public static final GpuBuffer buffer() {
        return INSTANCE.ensureBuffer();
    }

    @JvmStatic
    public static final void closeBuffer() {
        GpuBuffer gpuBuffer = buffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        buffer = null;
        lastMainUpload = null;
    }

    private final boolean mainUploadChanged(ByteBuffer data) {
        int size = data.remaining();
        ByteBuffer cache2 = lastMainUpload;
        if (cache2 != null && cache2.capacity() == size && data.mismatch(cache2) < 0) {
            return false;
        }
        if (cache2 == null || cache2.capacity() != size) {
            lastMainUpload = cache2 = ByteBuffer.allocate(size).order(data.order());
        }
        cache2.clear();
        cache2.put(data.duplicate());
        cache2.position(0);
        return true;
    }

    private final GpuBuffer ensureBuffer() {
        GpuBuffer current = buffer;
        if (current != null && !current.isClosed() && current.size() >= 4480L) {
            return current;
        }
        ClientPalette.closeBuffer();
        try {
            GpuBuffer created = RenderSystem.getDevice().createBuffer(ClientPalette::ensureBuffer$lambda$0, 136, 4480L);
            buffer = created;
            return created;
        }
        catch (RuntimeException ignored) {
            return null;
        }
    }

    private static final String ensureBuffer$lambda$0() {
        return "kimiko_client_palette";
    }

    static {
        int[] nArray = new int[]{0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF};
        displayed = nArray;
        nArray = new int[]{0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF};
        waveNewStops = nArray;
        nArray = new int[]{0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF};
        waveOldStops = nArray;
        nArray = new int[]{0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF};
        waveSampled = nArray;
        nArray = new int[]{0xFFFFFF};
        fallback = nArray;
        count = 1;
        lastStyleId = -1.0f;
        scratchCornerColors = new int[4];
    }
}

