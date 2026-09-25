/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui.theme;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.ui.theme.Theme;
import rtx.kimiko.api.ui.theme.ThemeManager;
import rtx.kimiko.utils.render.modules.post.themeshock.ThemeShockwaveRenderer;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0006\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001}B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u000f\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u0007H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\tJ\u0013\u0010\u000b\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000b\u0010\u0003J\u0013\u0010\f\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\u0003J\u001f\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0000\u00a2\u0006\u0004\b\u0010\u0010\u0011J?\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0017H\u0000\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001d\u001a\u00020\u0004H\u0000\u00a2\u0006\u0004\b\u001c\u0010\u0003J\u000f\u0010\u001e\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u0003J\u0013\u0010\u001f\u001a\u00020\u0017H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001f\u0010 J\u0013\u0010\"\u001a\u00020!H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\"\u0010#J\u0013\u0010$\u001a\u00020\rH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b$\u0010%J\u001b\u0010'\u001a\u00020!2\u0006\u0010&\u001a\u00020!H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b'\u0010(J#\u0010*\u001a\u00020!2\u0006\u0010)\u001a\u00020!2\u0006\u0010&\u001a\u00020!H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b*\u0010+J\u001b\u0010,\u001a\u00020\r2\u0006\u0010&\u001a\u00020!H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b,\u0010-J\u001b\u0010.\u001a\u00020\u00172\u0006\u0010&\u001a\u00020!H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b.\u0010/J\u001b\u00100\u001a\u00020\u00132\u0006\u0010&\u001a\u00020!H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b0\u00101J\u001b\u00102\u001a\u00020\u00132\u0006\u0010&\u001a\u00020!H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b2\u00101J\u001b\u00103\u001a\u00020\u00132\u0006\u0010&\u001a\u00020!H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b3\u00101J#\u00105\u001a\u00020\u00132\u0006\u0010&\u001a\u00020!2\u0006\u00104\u001a\u00020\u0013H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b5\u00106J\u001b\u00107\u001a\u00020!2\u0006\u0010&\u001a\u00020!H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b7\u0010(J+\u0010:\u001a\u00020!2\u0006\u0010&\u001a\u00020!2\u0006\u00108\u001a\u00020\u00132\u0006\u00109\u001a\u00020\u0013H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b:\u0010;J/\u0010=\u001a\u00020!2\u0006\u0010&\u001a\u00020!2\u0006\u00108\u001a\u00020\u00132\u0006\u00109\u001a\u00020\u00132\u0006\u0010<\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b=\u0010>J\u0013\u0010?\u001a\u00020\rH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b?\u0010%J#\u0010@\u001a\u00020\r2\u0006\u00108\u001a\u00020\u00132\u0006\u00109\u001a\u00020\u0013H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b@\u0010AJ'\u0010B\u001a\u00020\r2\u0006\u00108\u001a\u00020\u00132\u0006\u00109\u001a\u00020\u00132\u0006\u0010<\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\bB\u0010CJ\u0017\u0010E\u001a\u00020\u00132\u0006\u0010)\u001a\u00020DH\u0002\u00a2\u0006\u0004\bE\u0010FJ'\u0010G\u001a\u00020\u00132\u0006\u0010)\u001a\u00020D2\u0006\u00108\u001a\u00020\u00132\u0006\u00109\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\bG\u0010HJ\u0017\u0010I\u001a\u00020\u00132\u0006\u0010)\u001a\u00020DH\u0002\u00a2\u0006\u0004\bI\u0010FJ\u0017\u0010J\u001a\u00020\u00132\u0006\u0010)\u001a\u00020DH\u0002\u00a2\u0006\u0004\bJ\u0010FJ\u001b\u0010L\u001a\u00020\u00132\u0006\u0010K\u001a\u00020\u0013H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\bL\u0010MJ'\u0010N\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u00104\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\bN\u0010OJ/\u0010P\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010I\u001a\u00020\u00132\u0006\u00104\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\bP\u0010QJ\u0013\u00104\u001a\u00020\u0013H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b4\u0010RJ'\u0010S\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010I\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\bS\u0010OJ'\u0010W\u001a\u00020\u00132\u0006\u0010T\u001a\u00020\u00132\u0006\u0010U\u001a\u00020\u00132\u0006\u0010V\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\bW\u0010OJ'\u0010[\u001a\u00020X2\u0006\u0010V\u001a\u00020X2\u0006\u0010Y\u001a\u00020X2\u0006\u0010Z\u001a\u00020XH\u0002\u00a2\u0006\u0004\b[\u0010\\J\u001f\u0010^\u001a\u00020X2\u0006\u0010]\u001a\u00020X2\u0006\u0010Z\u001a\u00020XH\u0002\u00a2\u0006\u0004\b^\u0010_J!\u0010a\u001a\u00020!2\b\u0010\u000f\u001a\u0004\u0018\u00010\r2\u0006\u0010`\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\ba\u0010bJ'\u0010e\u001a\u00020!2\u0006\u0010c\u001a\u00020!2\u0006\u0010d\u001a\u00020!2\u0006\u0010K\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\be\u0010fJ\u0013\u0010g\u001a\u00020\u0013H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\bg\u0010RJ\u0013\u0010h\u001a\u00020\u0013H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\bh\u0010RJ\u0017\u0010j\u001a\u00020\u00132\u0006\u0010i\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\bj\u0010MR\u0014\u0010k\u001a\u00020!8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bk\u0010lR\u0014\u0010m\u001a\u00020\u00138\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bm\u0010nR\u0014\u0010o\u001a\u00020\u00138\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bo\u0010nR\u0014\u0010p\u001a\u00020\u00138\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bp\u0010nR\u0014\u0010q\u001a\u00020\u00138\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bq\u0010nR\u0014\u0010r\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\br\u0010sR\u0014\u0010t\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bt\u0010nR$\u0010w\u001a\u0012\u0012\u0004\u0012\u00020D0uj\b\u0012\u0004\u0012\u00020D`v8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bw\u0010xR\u0016\u0010y\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\by\u0010zR\u0016\u0010$\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010zR\u0016\u0010\n\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\n\u0010sR\u0016\u0010{\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b{\u0010|\u00a8\u0006~"}, d2={"Lrtx/kimiko/api/ui/theme/ThemeWave;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "beginFrame", "", "now", "()J", "frameNanos", "cancel", "demoteSpatial", "", "shades", "palette", "reset$rtx_kimiko_kimiko", "([I[I)V", "reset", "", "cx", "cy", "startNanos", "", "timed", "push$rtx_kimiko_kimiko", "([I[IFFJZ)V", "push", "prune$rtx_kimiko_kimiko", "prune", "mergeFront", "hasSpatialLayers", "()Z", "", "layerCount", "()I", "basePalette", "()[I", "index", "baseShade", "(I)I", "layer", "layerShade", "(II)I", "layerPalette", "(I)[I", "layerTimed", "(I)Z", "layerCenterX", "(I)F", "layerCenterY", "layerCoverage", "aspect", "layerSweptRadius", "(IF)F", "shadeGlobal", "designX", "designY", "shadeAtPos", "(IFF)I", "positional", "resolveShade", "(IFFZ)I", "paletteGlobal", "paletteAtPos", "(FF)[I", "resolvePalette", "(FFZ)[I", "Lrtx/kimiko/api/ui/theme/ThemeWave$Layer;", "globalCoverage", "(Lrtx/kimiko/api/ui/theme/ThemeWave$Layer;)F", "coverageAt", "(Lrtx/kimiko/api/ui/theme/ThemeWave$Layer;FF)F", "progress", "timedProgress", "t", "easeInOutSine", "(F)F", "maxRadius", "(FFF)F", "sweptRadius", "(FFFF)F", "()F", "areaProgress", "edge0", "edge1", "x", "smoothstep", "", "y", "r", "quarterArea", "(DDD)D", "u", "arcIntegral", "(DD)D", "p", "samplePalette", "([IF)I", "a", "b", "rgbLerp", "(IIF)I", "screenPixelWidth", "screenPixelHeight", "v", "clamp01", "MAX_LAYERS", "I", "DURATION_SECONDS", "F", "THICKNESS", "FEATHERING", "COLOR_FEATHER", "TIMED_MS", "J", "BAND_CENTER", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "layers", "Ljava/util/ArrayList;", "baseShades", "[I", "frameHookSeen", "Z", "Layer", "rtx.kimiko:kimiko"})
public final class ThemeWave {
    @NotNull
    public static final ThemeWave INSTANCE = new ThemeWave();
    public static final int MAX_LAYERS = 6;
    public static final float DURATION_SECONDS = 1.15f;
    public static final float THICKNESS = 0.075f;
    public static final float FEATHERING = 0.1f;
    public static final float COLOR_FEATHER = 0.018f;
    private static final long TIMED_MS = 500L;
    private static final float BAND_CENTER = 0.087500006f;
    @NotNull
    private static final ArrayList<Layer> layers = new ArrayList();
    @NotNull
    private static int[] baseShades = Theme.KIMIKO.shades();
    @NotNull
    private static int[] basePalette = Theme.KIMIKO.palette();
    private static long frameNanos = System.nanoTime();
    private static boolean frameHookSeen;

    private ThemeWave() {
    }

    @JvmStatic
    public static final void beginFrame() {
        frameNanos = System.nanoTime();
        frameHookSeen = true;
    }

    private final long now() {
        return frameHookSeen ? frameNanos : System.nanoTime();
    }

    @JvmStatic
    public static final long frameNanos() {
        return INSTANCE.now();
    }

    @JvmStatic
    public static final void cancel() {
        ThemeManager.clearPendingWave();
        INSTANCE.reset$rtx_kimiko_kimiko(ThemeManager.current().shades(), ThemeManager.current().palette());
        ThemeShockwaveRenderer.cancel();
    }

    @JvmStatic
    public static final void demoteSpatial() {
        long start = INSTANCE.now();
        Iterator<Layer> iterator = layers.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Layer> iterator2 = iterator;
        while (iterator2.hasNext()) {
            Layer layer = (Layer) (iterator2.next());
            if (layer.getTimed()) continue;
            layer.setTimed(true);
            layer.setStartNanos(start);
        }
    }

    public final void reset$rtx_kimiko_kimiko(@NotNull int[] shades, @NotNull int[] palette) {
        Intrinsics.checkNotNullParameter((Object)shades, (String)"shades");
        Intrinsics.checkNotNullParameter((Object)palette, (String)"palette");
        layers.clear();
        baseShades = shades;
        basePalette = palette;
    }

    public final void push$rtx_kimiko_kimiko(@NotNull int[] shades, @NotNull int[] palette, float cx, float cy, long startNanos, boolean timed) {
        Intrinsics.checkNotNullParameter((Object)shades, (String)"shades");
        Intrinsics.checkNotNullParameter((Object)palette, (String)"palette");
        this.prune$rtx_kimiko_kimiko();
        while (layers.size() >= 6) {
            this.mergeFront();
        }
        Layer layer = new Layer();
        layer.setShades(shades);
        layer.setPalette(palette);
        layer.setCx(this.clamp01(cx));
        layer.setCy(this.clamp01(cy));
        layer.setStartNanos(startNanos);
        layer.setTimed(timed);
        layers.add(layer);
    }

    public final void prune$rtx_kimiko_kimiko() {
        while (!((Collection)layers).isEmpty()) {
            Layer layer = layers.get(0);
            Intrinsics.checkNotNullExpressionValue((Object)layer, (String)"get(...)");
            if (!(this.globalCoverage(layer) >= 1.0f)) break;
            this.mergeFront();
        }
    }

    private final void mergeFront() {
        Layer layer = layers.remove(0);
        Intrinsics.checkNotNullExpressionValue((Object)layer, (String)"removeAt(...)");
        Layer layer2 = layer;
        float t = this.globalCoverage(layer2);
        if (t >= 1.0f) {
            baseShades = layer2.getShades();
            basePalette = layer2.getPalette();
            return;
        }
        if (t <= 0.0f) {
            return;
        }
        int[] shades = new int[baseShades.length];
        int n = shades.length;
        for (int i = 0; i < n; ++i) {
            shades[i] = this.rgbLerp(baseShades[i], layer2.getShades()[i], t);
        }
        int n2 = Math.max(basePalette.length, layer2.getPalette().length);
        int[] palette = new int[n2];
        for (int i = 0; i < n2; ++i) {
            float p = n2 <= 1 ? 0.0f : (float)i / (float)(n2 - 1);
            palette[i] = this.rgbLerp(this.samplePalette(basePalette, p), this.samplePalette(layer2.getPalette(), p), t);
        }
        baseShades = shades;
        basePalette = palette;
    }

    @JvmStatic
    public static final boolean hasSpatialLayers() {
        INSTANCE.prune$rtx_kimiko_kimiko();
        Iterator<Layer> iterator = layers.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Layer> iterator2 = iterator;
        while (iterator2.hasNext()) {
            Layer layer = (Layer) (iterator2.next());
            if (layer.getTimed()) continue;
            return true;
        }
        return false;
    }

    @JvmStatic
    public static final int layerCount() {
        INSTANCE.prune$rtx_kimiko_kimiko();
        return layers.size();
    }

    @JvmStatic
    @NotNull
    public static final int[] basePalette() {
        INSTANCE.prune$rtx_kimiko_kimiko();
        return basePalette;
    }

    @JvmStatic
    public static final int baseShade(int index) {
        INSTANCE.prune$rtx_kimiko_kimiko();
        return baseShades[index];
    }

    @JvmStatic
    public static final int layerShade(int layer, int index) {
        return layer >= 0 && layer < layers.size() ? layers.get(layer).getShades()[index] : baseShades[index];
    }

    @JvmStatic
    @NotNull
    public static final int[] layerPalette(int index) {
        return index >= 0 && index < layers.size() ? layers.get(index).getPalette() : basePalette;
    }

    @JvmStatic
    public static final boolean layerTimed(int index) {
        return index >= 0 && index < layers.size() && layers.get(index).getTimed();
    }

    @JvmStatic
    public static final float layerCenterX(int index) {
        return index >= 0 && index < layers.size() ? layers.get(index).getCx() : 0.5f;
    }

    @JvmStatic
    public static final float layerCenterY(int index) {
        return index >= 0 && index < layers.size() ? layers.get(index).getCy() : 0.5f;
    }

    @JvmStatic
    public static final float layerCoverage(int index) {
        float f;
        if (index >= 0 && index < layers.size()) {
            Layer layer = layers.get(index);
            Intrinsics.checkNotNullExpressionValue((Object)layer, (String)"get(...)");
            f = INSTANCE.globalCoverage(layer);
        } else {
            f = 1.0f;
        }
        return f;
    }

    @JvmStatic
    public static final float layerSweptRadius(int index, float aspect) {
        if (index < 0 || index >= layers.size()) {
            return 0.0f;
        }
        Layer layer = layers.get(index);
        Intrinsics.checkNotNullExpressionValue((Object)layer, (String)"get(...)");
        Layer layer2 = layer;
        return INSTANCE.sweptRadius(layer2.getCx(), layer2.getCy(), INSTANCE.progress(layer2), aspect);
    }

    @JvmStatic
    public static final int shadeGlobal(int index) {
        INSTANCE.prune$rtx_kimiko_kimiko();
        return INSTANCE.resolveShade(index, -1.0f, -1.0f, false);
    }

    @JvmStatic
    public static final int shadeAtPos(int index, float designX, float designY) {
        INSTANCE.prune$rtx_kimiko_kimiko();
        return INSTANCE.resolveShade(index, designX, designY, true);
    }

    private final int resolveShade(int index, float designX, float designY, boolean positional) {
        int packed = baseShades[index];
        float r = packed >>> 16 & 0xFF;
        float g = packed >>> 8 & 0xFF;
        float b = packed & 0xFF;
        Iterator<Layer> iterator = layers.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Layer> iterator2 = iterator;
        while (iterator2.hasNext()) {
            Layer layer = iterator2.next();
            float t = positional ? this.coverageAt(layer, designX, designY) : this.globalCoverage(layer);
            if (t <= 0.0f) continue;
            int target = layer.getShades()[index];
            r += ((float)(target >>> 16 & 0xFF) - r) * t;
            g += ((float)(target >>> 8 & 0xFF) - g) * t;
            b += ((float)(target & 0xFF) - b) * t;
        }
        return MathKt.roundToInt((float)r) << 16 | MathKt.roundToInt((float)g) << 8 | MathKt.roundToInt((float)b);
    }

    @JvmStatic
    @NotNull
    public static final int[] paletteGlobal() {
        INSTANCE.prune$rtx_kimiko_kimiko();
        return INSTANCE.resolvePalette(-1.0f, -1.0f, false);
    }

    @JvmStatic
    @NotNull
    public static final int[] paletteAtPos(float designX, float designY) {
        INSTANCE.prune$rtx_kimiko_kimiko();
        return INSTANCE.resolvePalette(designX, designY, true);
    }

    private final int[] resolvePalette(float designX, float designY, boolean positional) {
        if (layers.isEmpty()) {
            return basePalette;
        }
        int n = basePalette.length;
        for (Layer layer : layers) {
            n = Math.max(n, layer.getPalette().length);
        }
        int[] out = new int[n];
        for (int i = 0; i < n; ++i) {
            float p = n <= 1 ? 0.0f : (float)i / (float)(n - 1);
            int c = this.samplePalette(basePalette, p);
            for (Layer layer : layers) {
                float t = positional ? this.coverageAt(layer, designX, designY) : this.globalCoverage(layer);
                if (t <= 0.0f) continue;
                c = this.rgbLerp(c, this.samplePalette(layer.getPalette(), p), t);
            }
            out[i] = c;
        }
        return out;
    }

    private final float globalCoverage(Layer layer) {
        if (layer.getTimed()) {
            return this.timedProgress(layer);
        }
        return this.areaProgress(layer.getCx(), layer.getCy(), this.progress(layer));
    }

    private final float coverageAt(Layer layer, float designX, float designY) {
        if (layer.getTimed()) {
            return this.timedProgress(layer);
        }
        float aspect = ThemeWave.aspect();
        float normX = Render2DCoordinateSpace.normalizedDesignX(designX);
        float normY = Render2DCoordinateSpace.normalizedDesignY(designY);
        float dx = (normX - layer.getCx()) * aspect;
        float dy = 1.0f - normY - layer.getCy();
        float dist = (float)Math.sqrt(dx * dx + dy * dy);
        float r = this.sweptRadius(layer.getCx(), layer.getCy(), this.progress(layer), aspect);
        return 1.0f - this.smoothstep(r - 0.018f, r + 0.018f, dist);
    }

    private final float progress(Layer layer) {
        float t = (float)(this.now() - layer.getStartNanos()) / 1.0E9f / 1.15f;
        return t <= 0.0f ? 0.0f : Math.min(t, 1.0f);
    }

    private final float timedProgress(Layer layer) {
        float t = (float)(this.now() - layer.getStartNanos()) / 1000000.0f / (float)500L;
        if (t <= 0.0f) {
            return 0.0f;
        }
        if (t >= 1.0f) {
            return 1.0f;
        }
        return t * t * (3.0f - 2.0f * t);
    }

    @JvmStatic
    public static final float easeInOutSine(float t) {
        return 0.5f - 0.5f * (float)Math.cos((double)INSTANCE.clamp01(t) * Math.PI);
    }

    private final float maxRadius(float cx, float cy, float aspect) {
        float mx = Math.max(cx, 1.0f - cx) * aspect;
        float my = Math.max(cy, 1.0f - cy);
        return (float)Math.sqrt(mx * mx + my * my) + 0.075f + 0.1f + 0.25f;
    }

    private final float sweptRadius(float cx, float cy, float progress, float aspect) {
        return ThemeWave.easeInOutSine(progress) * this.maxRadius(cx, cy, aspect) - 0.087500006f;
    }

    @JvmStatic
    public static final float aspect() {
        float w = ThemeWave.screenPixelWidth();
        float h = ThemeWave.screenPixelHeight();
        if (h <= 0.0f) {
            return 1.0f;
        }
        return Math.max(0.01f, w / h);
    }

    private final float areaProgress(float cx, float cy, float progress) {
        float aspect = ThemeWave.aspect();
        double r = this.sweptRadius(cx, cy, progress, aspect);
        if (r <= 0.0) {
            return 0.0f;
        }
        double x = cx * aspect;
        double y = cy;
        double area = this.quarterArea(x, y, r) + this.quarterArea((double)aspect - x, y, r) + this.quarterArea(x, 1.0 - y, r) + this.quarterArea((double)aspect - x, 1.0 - y, r);
        return this.clamp01((float)(area / (double)aspect));
    }

    private final float smoothstep(float edge0, float edge1, float x) {
        if (edge1 <= edge0) {
            return x < edge0 ? 0.0f : 1.0f;
        }
        float t = this.clamp01((x - edge0) / (edge1 - edge0));
        return t * t * (3.0f - 2.0f * t);
    }

    private final double quarterArea(double x, double y, double r) {
        if (x <= 0.0 || y <= 0.0 || r <= 0.0) {
            return 0.0;
        }
        double a = Math.min(x, r);
        double u0 = y >= r ? 0.0 : Math.sqrt(Math.max(0.0, r * r - y * y));
        double lo = Math.min(a, u0);
        double area = y * lo;
        if (a > lo) {
            area += this.arcIntegral(a, r) - this.arcIntegral(lo, r);
        }
        return area;
    }

    private final double arcIntegral(double u, double r) {
        double c = Math.max(-1.0, Math.min(1.0, u / r));
        return 0.5 * (u * Math.sqrt(Math.max(0.0, r * r - u * u)) + r * r * Math.asin(c));
    }

    private final int samplePalette(int[] palette, float p) {
        if (palette == null || palette.length == 0) {
            return 0;
        }
        if (palette.length == 1) {
            return palette[0] & 0xFFFFFF;
        }
        float f = this.clamp01(p) * (float)(palette.length - 1);
        int i = (int)Math.floor(f);
        int j = Math.min(i + 1, palette.length - 1);
        return this.rgbLerp(palette[i], palette[j], f - (float)i);
    }

    private final int rgbLerp(int a, int b, float t) {
        float tt = this.clamp01(t);
        int ar = a >> 16 & 0xFF;
        int ag = a >> 8 & 0xFF;
        int ab = a & 0xFF;
        int br = b >> 16 & 0xFF;
        int bg = b >> 8 & 0xFF;
        int bb = b & 0xFF;
        int r = MathKt.roundToInt((float)((float)ar + (float)(br - ar) * tt));
        int g = MathKt.roundToInt((float)((float)ag + (float)(bg - ag) * tt));
        int bl = MathKt.roundToInt((float)((float)ab + (float)(bb - ab) * tt));
        return r << 16 | g << 8 | bl;
    }

    @JvmStatic
    public static final float screenPixelWidth() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.getFramebuffer() != null && mc.getFramebuffer().textureWidth > 0) {
            return mc.getFramebuffer().textureWidth;
        }
        if (mc.getWindow() != null && mc.getWindow().getFramebufferWidth() > 0) {
            return mc.getWindow().getFramebufferWidth();
        }
        return 1920.0f;
    }

    @JvmStatic
    public static final float screenPixelHeight() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.getFramebuffer() != null && mc.getFramebuffer().textureHeight > 0) {
            return mc.getFramebuffer().textureHeight;
        }
        if (mc.getWindow() != null && mc.getWindow().getFramebufferHeight() > 0) {
            return mc.getWindow().getFramebufferHeight();
        }
        return 1080.0f;
    }

    private final float clamp01(float v) {
        return v < 0.0f ? 0.0f : (v > 1.0f ? 1.0f : v);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086.\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\u000b\u001a\u00020\u00048\u0006@\u0006X\u0086.\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\"\u0010\u000f\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\"\u0010\u0015\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R\"\u0010\u0019\u001a\u00020\u00188\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\"\u0010 \u001a\u00020\u001f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%\u00a8\u0006&"}, d2={"Lrtx/kimiko/api/ui/theme/ThemeWave$Layer;", "", "<init>", "()V", "", "shades", "[I", "getShades", "()[I", "setShades", "([I)V", "palette", "getPalette", "setPalette", "", "cx", "F", "getCx", "()F", "setCx", "(F)V", "cy", "getCy", "setCy", "", "startNanos", "J", "getStartNanos", "()J", "setStartNanos", "(J)V", "", "timed", "Z", "getTimed", "()Z", "setTimed", "(Z)V", "rtx.kimiko:kimiko"})
    private static final class Layer {
        public int[] shades;
        public int[] palette;
        private float cx;
        private float cy;
        private long startNanos;
        private boolean timed;

        @NotNull
        public final int[] getShades() {
            if (this.shades != null) {
                return this.shades;
            }
            Intrinsics.throwUninitializedPropertyAccessException((String)"shades");
            return null;
        }

        public final void setShades(@NotNull int[] nArray) {
            Intrinsics.checkNotNullParameter((Object)nArray, (String)"<set-?>");
            this.shades = nArray;
        }

        @NotNull
        public final int[] getPalette() {
            if (this.palette != null) {
                return this.palette;
            }
            Intrinsics.throwUninitializedPropertyAccessException((String)"palette");
            return null;
        }

        public final void setPalette(@NotNull int[] nArray) {
            Intrinsics.checkNotNullParameter((Object)nArray, (String)"<set-?>");
            this.palette = nArray;
        }

        public final float getCx() {
            return this.cx;
        }

        public final void setCx(float f) {
            this.cx = f;
        }

        public final float getCy() {
            return this.cy;
        }

        public final void setCy(float f) {
            this.cy = f;
        }

        public final long getStartNanos() {
            return this.startNanos;
        }

        public final void setStartNanos(long l) {
            this.startNanos = l;
        }

        public final boolean getTimed() {
            return this.timed;
        }

        public final void setTimed(boolean bl) {
            this.timed = bl;
        }
    }
}

