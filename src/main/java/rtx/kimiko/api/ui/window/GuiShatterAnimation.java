/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmOverloads
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.window;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.util.voronoi.VoronoiOfQuad;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002`aB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\u0007JK\u0010\u0012\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0013JS\u0010\u0012\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0014H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u0011H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0017\u0010\u0003J!\u0010\u001b\u001a\u00020\u00192\b\b\u0002\u0010\u0018\u001a\u00020\tH\u0007b\u0002\b\u0005b\u0002\b\u001a\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u001b\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u001b\u0010 \u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0014H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b \u0010!J\u0013\u0010\"\u001a\u00020\u0019H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\"\u0010#J\u0013\u0010$\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b$\u0010%J\u001b\u0010&\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b&\u0010'J\u0013\u0010(\u001a\u00020\u0019H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b(\u0010#J\u0013\u0010\u0015\u001a\u00020\u0014H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0015\u0010)J\u0013\u0010+\u001a\u00020*H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b+\u0010,J=\u00103\u001a\u0002022\b\u0010.\u001a\u0004\u0018\u00010-2\u0006\u0010&\u001a\u00020\t2\u0006\u0010/\u001a\u00020\t2\u0006\u00100\u001a\u00020\t2\u0006\u00101\u001a\u00020\u0019H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b3\u00104JG\u00103\u001a\u0002022\b\u0010.\u001a\u0004\u0018\u00010-2\u0006\u0010&\u001a\u00020\t2\u0006\u0010/\u001a\u00020\t2\u0006\u00100\u001a\u00020\t2\u0006\u00101\u001a\u00020\u00192\b\u00106\u001a\u0004\u0018\u000105H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b3\u00107JM\u0010=\u001a\u0002022\b\u0010.\u001a\u0004\u0018\u00010-2\u0006\u00108\u001a\u00020\t2\u0006\u00109\u001a\u00020\t2\u0006\u0010:\u001a\u00020\t2\u0006\u0010;\u001a\u00020\t2\u0006\u00100\u001a\u00020\t2\u0006\u0010<\u001a\u000202H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b=\u0010>J7\u0010@\u001a\u00020\u00112\u0006\u0010.\u001a\u00020-2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u00100\u001a\u00020\t2\u0006\u0010?\u001a\u000202H\u0002\u00a2\u0006\u0004\b@\u0010AJ\u0017\u0010C\u001a\u00020\t2\u0006\u0010B\u001a\u00020\tH\u0002\u00a2\u0006\u0004\bC\u0010'J\u0017\u0010D\u001a\u00020\t2\u0006\u0010B\u001a\u00020\tH\u0002\u00a2\u0006\u0004\bD\u0010'J\u0017\u0010E\u001a\u00020\t2\u0006\u0010B\u001a\u00020\tH\u0002\u00a2\u0006\u0004\bE\u0010'R\u0014\u0010F\u001a\u0002028\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bF\u0010GR\u0014\u0010H\u001a\u0002028\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bH\u0010GR\u0014\u0010I\u001a\u0002028\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bI\u0010GR\u0014\u0010J\u001a\u0002028\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bJ\u0010GR\u0014\u0010K\u001a\u0002028\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bK\u0010GR\u0014\u0010L\u001a\u0002028\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bL\u0010GR\u0014\u0010M\u001a\u0002028\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bM\u0010GR\u0014\u0010N\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bN\u0010OR\u0014\u0010P\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bP\u0010OR\u0014\u0010Q\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bQ\u0010OR\u0014\u0010R\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bR\u0010OR\u0014\u0010S\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bS\u0010OR\u0014\u0010T\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bT\u0010OR\u0014\u0010U\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bU\u0010OR\u0014\u0010V\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bV\u0010OR\u0014\u0010W\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bW\u0010OR\u0014\u0010X\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bX\u0010YR\u0014\u0010Z\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bZ\u0010OR\u0014\u0010\\\u001a\u00020[8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\\\u0010]R\u0014\u0010^\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b^\u0010_\u00a8\u0006b"}, d2={"Lrtx/kimiko/api/ui/window/GuiShatterAnimation;", "", "<init>", "()V", "Lrtx/kimiko/api/ui/window/GuiShatterAnimation$State;", "Lkotlin/jvm/JvmStatic;", "local", "()Lrtx/kimiko/api/ui/window/GuiShatterAnimation$State;", "create", "", "x", "y", "width", "height", "pad", "screenWidth", "screenHeight", "", "begin", "(FFFFFFF)V", "", "seed", "(FFFFFFFJ)V", "cancel", "closeProgress", "", "Lkotlin/jvm/JvmOverloads;", "resume", "(F)Z", "rebase", "(F)V", "durationNs", "gather", "(J)V", "isGathering", "()Z", "blurRadius", "()F", "progress", "(F)F", "isActive", "()J", "", "beginRect", "()[F", "Ljava/nio/ByteBuffer;", "out", "globalAlpha", "scale", "worldMode", "", "buildGeometry", "(Ljava/nio/ByteBuffer;FFFZ)I", "Lrtx/kimiko/api/ui/window/GuiShatterAnimation$Remap;", "remap", "(Ljava/nio/ByteBuffer;FFFZLrtx/kimiko/api/ui/window/GuiShatterAnimation$Remap;)I", "x0", "y0", "x1", "y1", "alpha255", "appendQuad", "(Ljava/nio/ByteBuffer;FFFFFI)I", "alpha", "putQuadVertex", "(Ljava/nio/ByteBuffer;FFFI)V", "value", "clampSigned", "easeOutCubic", "clamp01", "MAX_SHARDS", "I", "VERTEX_BYTES", "MAX_POLY_VERTICES", "MAX_VERTICES", "EXTRA_VERTICES", "MIN_COUNT", "MAX_COUNT", "RELAX_STRENGTH", "F", "SEED_JITTER", "MIN_DISTANCE", "EXPAND", "SPREAD", "MARGIN", "CURL_DEGREES", "CURL_DEPTH", "SCREEN_PERSPECTIVE", "GATHER_NS", "J", "MAX_BLUR_RADIUS", "Ljava/util/Random;", "RANDOM", "Ljava/util/Random;", "LOCAL", "Lrtx/kimiko/api/ui/window/GuiShatterAnimation$State;", "Remap", "State", "rtx.kimiko:kimiko"})
public final class GuiShatterAnimation {
    @NotNull
    public static final GuiShatterAnimation INSTANCE = new GuiShatterAnimation();
    public static final int MAX_SHARDS = 28;
    public static final int VERTEX_BYTES = 24;
    private static final int MAX_POLY_VERTICES = 24;
    public static final int MAX_VERTICES = 1848;
    public static final int EXTRA_VERTICES = 24;
    private static final int MIN_COUNT = 22;
    private static final int MAX_COUNT = 28;
    private static final float RELAX_STRENGTH = 0.45f;
    private static final float SEED_JITTER = 0.32f;
    private static final float MIN_DISTANCE = 0.68f;
    private static final float EXPAND = 0.34f;
    private static final float SPREAD = 0.028f;
    private static final float MARGIN = 0.004f;
    private static final float CURL_DEGREES = 26.0f;
    private static final float CURL_DEPTH = 0.1f;
    private static final float SCREEN_PERSPECTIVE = 0.9f;
    private static final long GATHER_NS = 360000000L;
    private static final float MAX_BLUR_RADIUS = 9.0f;
    @NotNull
    private static final Random RANDOM = new Random();
    @NotNull
    private static final State LOCAL = new State();

    private GuiShatterAnimation() {
    }

    @JvmStatic
    @NotNull
    public static final State local() {
        return LOCAL;
    }

    @JvmStatic
    @NotNull
    public static final State create() {
        return new State();
    }

    @JvmStatic
    public static final void begin(float x, float y, float width, float height, float pad, float screenWidth, float screenHeight) {
        LOCAL.begin(x, y, width, height, pad, screenWidth, screenHeight, RANDOM.nextLong());
    }

    @JvmStatic
    public static final void begin(float x, float y, float width, float height, float pad, float screenWidth, float screenHeight, long seed) {
        LOCAL.begin(x, y, width, height, pad, screenWidth, screenHeight, seed);
    }

    @JvmStatic
    public static final void cancel() {
        LOCAL.cancel();
    }

    @JvmStatic
    @JvmOverloads
    public static final boolean resume(float closeProgress) {
        return LOCAL.resume(closeProgress);
    }

    public static /* synthetic */ boolean resume$default(float f, int n, Object object) {
        if ((n & 1) != 0) {
            f = 0.0f;
        }
        return GuiShatterAnimation.resume(f);
    }

    @JvmStatic
    public static final void rebase(float closeProgress) {
        LOCAL.rebase(closeProgress);
    }

    @JvmStatic
    public static final void gather(long durationNs) {
        LOCAL.gather(durationNs);
    }

    @JvmStatic
    public static final boolean isGathering() {
        return LOCAL.isGathering();
    }

    @JvmStatic
    public static final float blurRadius() {
        return LOCAL.blurRadius();
    }

    @JvmStatic
    public static final float progress(float closeProgress) {
        return LOCAL.progress(closeProgress);
    }

    @JvmStatic
    public static final boolean isActive() {
        return LOCAL.isActive();
    }

    @JvmStatic
    public static final long seed() {
        return LOCAL.seed();
    }

    @JvmStatic
    @NotNull
    public static final float[] beginRect() {
        return LOCAL.beginRect();
    }

    @JvmStatic
    public static final int buildGeometry(@Nullable ByteBuffer out, float progress, float globalAlpha, float scale, boolean worldMode) {
        return LOCAL.buildGeometry(out, progress, globalAlpha, scale, worldMode, null);
    }

    @JvmStatic
    public static final int buildGeometry(@Nullable ByteBuffer out, float progress, float globalAlpha, float scale, boolean worldMode, @Nullable Remap remap) {
        return LOCAL.buildGeometry(out, progress, globalAlpha, scale, worldMode, remap);
    }

    @JvmStatic
    public static final int appendQuad(@Nullable ByteBuffer out, float x0, float y0, float x1, float y1, float scale, int alpha255) {
        if (out == null || x1 - x0 <= 5.0E-4f || y1 - y0 <= 5.0E-4f || alpha255 <= 0) {
            return 0;
        }
        int used = out.limit();
        if (used + 144 > out.capacity()) {
            return 0;
        }
        out.limit(out.capacity());
        out.position(used);
        float safeScale = scale > 1.0E-4f ? scale : 1.0f;
        int a = Math.min(255, Math.max(0, alpha255));
        INSTANCE.putQuadVertex(out, x0, y0, safeScale, a);
        INSTANCE.putQuadVertex(out, x0, y1, safeScale, a);
        INSTANCE.putQuadVertex(out, x1, y1, safeScale, a);
        INSTANCE.putQuadVertex(out, x0, y0, safeScale, a);
        INSTANCE.putQuadVertex(out, x1, y1, safeScale, a);
        INSTANCE.putQuadVertex(out, x1, y0, safeScale, a);
        out.flip();
        return 6;
    }

    private final void putQuadVertex(ByteBuffer out, float x, float y, float scale, int alpha) {
        out.putFloat(0.5f + (x - 0.5f) * scale);
        out.putFloat(0.5f + (y - 0.5f) * scale);
        out.putFloat(0.0f);
        out.putFloat(x);
        out.putFloat(1.0f - y);
        out.put((byte)-1);
        out.put((byte)-1);
        out.put((byte)-1);
        out.put((byte)alpha);
    }

    private final float clampSigned(float value) {
        return Math.max(-1.0f, Math.min(1.0f, value));
    }

    private final float easeOutCubic(float value) {
        float inverted = 1.0f - value;
        return 1.0f - inverted * inverted * inverted;
    }

    private final float clamp01(float value) {
        return Math.max(0.0f, Math.min(1.0f, value));
    }

    @JvmStatic
    @JvmOverloads
    public static final boolean resume() {
        return GuiShatterAnimation.resume$default(0.0f, 1, null);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u000eJ\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u000eJ\u0010\u0010\u0012\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u000eJ\u0010\u0010\u0013\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u000eJ\u0010\u0010\u0014\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u000eJ\u0010\u0010\u0015\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u000eJ`\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u001a\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0011\u0010\u001d\u001a\u00020\u001cH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0011\u0010 \u001a\u00020\u001fH\u00d6\u0081\u0004\u00a2\u0006\u0004\b \u0010!R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010$\u001a\u0004\b\u0003\u0010\u000eR%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010$\u001a\u0004\b\u0004\u0010\u000eR%\u0010\u0005\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010$\u001a\u0004\b\u0005\u0010\u000eR%\u0010\u0006\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010$\u001a\u0004\b\u0006\u0010\u000eR%\u0010\u0007\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010$\u001a\u0004\b\u0007\u0010\u000eR%\u0010\b\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010$\u001a\u0004\b\b\u0010\u000eR%\u0010\t\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010$\u001a\u0004\b\t\u0010\u000eR%\u0010\n\u001a\u00020\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010$\u001a\u0004\b\n\u0010\u000e\u00a8\u0006%"}, d2={"Lrtx/kimiko/api/ui/window/GuiShatterAnimation$Remap;", "", "", "posOffsetX", "posOffsetY", "posScaleX", "posScaleY", "uvOffsetX", "uvOffsetY", "uvScaleX", "uvScaleY", "<init>", "(FFFFFFFF)V", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(FFFFFFFF)Lrtx/kimiko/api/ui/window/GuiShatterAnimation$Remap;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "rtx.kimiko:kimiko"})
    public static final class Remap {
        private final float posOffsetX;
        private final float posOffsetY;
        private final float posScaleX;
        private final float posScaleY;
        private final float uvOffsetX;
        private final float uvOffsetY;
        private final float uvScaleX;
        private final float uvScaleY;

        public Remap(float posOffsetX, float posOffsetY, float posScaleX, float posScaleY, float uvOffsetX, float uvOffsetY, float uvScaleX, float uvScaleY) {
            this.posOffsetX = posOffsetX;
            this.posOffsetY = posOffsetY;
            this.posScaleX = posScaleX;
            this.posScaleY = posScaleY;
            this.uvOffsetX = uvOffsetX;
            this.uvOffsetY = uvOffsetY;
            this.uvScaleX = uvScaleX;
            this.uvScaleY = uvScaleY;
        }

        @JvmName(name="posOffsetX")
        public final float posOffsetX() {
            return this.posOffsetX;
        }

        @JvmName(name="posOffsetY")
        public final float posOffsetY() {
            return this.posOffsetY;
        }

        @JvmName(name="posScaleX")
        public final float posScaleX() {
            return this.posScaleX;
        }

        @JvmName(name="posScaleY")
        public final float posScaleY() {
            return this.posScaleY;
        }

        @JvmName(name="uvOffsetX")
        public final float uvOffsetX() {
            return this.uvOffsetX;
        }

        @JvmName(name="uvOffsetY")
        public final float uvOffsetY() {
            return this.uvOffsetY;
        }

        @JvmName(name="uvScaleX")
        public final float uvScaleX() {
            return this.uvScaleX;
        }

        @JvmName(name="uvScaleY")
        public final float uvScaleY() {
            return this.uvScaleY;
        }

        public final float component1() {
            return this.posOffsetX;
        }

        public final float component2() {
            return this.posOffsetY;
        }

        public final float component3() {
            return this.posScaleX;
        }

        public final float component4() {
            return this.posScaleY;
        }

        public final float component5() {
            return this.uvOffsetX;
        }

        public final float component6() {
            return this.uvOffsetY;
        }

        public final float component7() {
            return this.uvScaleX;
        }

        public final float component8() {
            return this.uvScaleY;
        }

        @NotNull
        public final Remap copy(float posOffsetX, float posOffsetY, float posScaleX, float posScaleY, float uvOffsetX, float uvOffsetY, float uvScaleX, float uvScaleY) {
            return new Remap(posOffsetX, posOffsetY, posScaleX, posScaleY, uvOffsetX, uvOffsetY, uvScaleX, uvScaleY);
        }

        public static /* synthetic */ Remap copy$default(Remap remap, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n, Object object) {
            if ((n & 1) != 0) {
                f = remap.posOffsetX;
            }
            if ((n & 2) != 0) {
                f2 = remap.posOffsetY;
            }
            if ((n & 4) != 0) {
                f3 = remap.posScaleX;
            }
            if ((n & 8) != 0) {
                f4 = remap.posScaleY;
            }
            if ((n & 0x10) != 0) {
                f5 = remap.uvOffsetX;
            }
            if ((n & 0x20) != 0) {
                f6 = remap.uvOffsetY;
            }
            if ((n & 0x40) != 0) {
                f7 = remap.uvScaleX;
            }
            if ((n & 0x80) != 0) {
                f8 = remap.uvScaleY;
            }
            return remap.copy(f, f2, f3, f4, f5, f6, f7, f8);
        }

        @NotNull
        public String toString() {
            return "Remap(posOffsetX=" + this.posOffsetX + ", posOffsetY=" + this.posOffsetY + ", posScaleX=" + this.posScaleX + ", posScaleY=" + this.posScaleY + ", uvOffsetX=" + this.uvOffsetX + ", uvOffsetY=" + this.uvOffsetY + ", uvScaleX=" + this.uvScaleX + ", uvScaleY=" + this.uvScaleY + ")";
        }

        public int hashCode() {
            int result = Float.hashCode(this.posOffsetX);
            result = result * 31 + Float.hashCode(this.posOffsetY);
            result = result * 31 + Float.hashCode(this.posScaleX);
            result = result * 31 + Float.hashCode(this.posScaleY);
            result = result * 31 + Float.hashCode(this.uvOffsetX);
            result = result * 31 + Float.hashCode(this.uvOffsetY);
            result = result * 31 + Float.hashCode(this.uvScaleX);
            result = result * 31 + Float.hashCode(this.uvScaleY);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Remap)) {
                return false;
            }
            Remap remap = (Remap)other;
            if (Float.compare(this.posOffsetX, remap.posOffsetX) != 0) {
                return false;
            }
            if (Float.compare(this.posOffsetY, remap.posOffsetY) != 0) {
                return false;
            }
            if (Float.compare(this.posScaleX, remap.posScaleX) != 0) {
                return false;
            }
            if (Float.compare(this.posScaleY, remap.posScaleY) != 0) {
                return false;
            }
            if (Float.compare(this.uvOffsetX, remap.uvOffsetX) != 0) {
                return false;
            }
            if (Float.compare(this.uvOffsetY, remap.uvOffsetY) != 0) {
                return false;
            }
            if (Float.compare(this.uvScaleX, remap.uvScaleX) != 0) {
                return false;
            }
            return Float.compare(this.uvScaleY, remap.uvScaleY) == 0;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001f\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0007\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u000f\u0010\u0010JM\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\u000b\u00a2\u0006\u0004\b\u001a\u0010\u0003J\u001d\u0010\u001e\u001a\u00020\u001c2\b\b\u0002\u0010\u001b\u001a\u00020\u0007H\u0007b\u0002\b\u001d\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0015\u0010 \u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u0007\u00a2\u0006\u0004\b \u0010\rJ\u0015\u0010\"\u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u0004\u00a2\u0006\u0004\b\"\u0010#J\r\u0010$\u001a\u00020\u001c\u00a2\u0006\u0004\b$\u0010%J\r\u0010&\u001a\u00020\u0007\u00a2\u0006\u0004\b&\u0010\tJ\u0015\u0010'\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007\u00a2\u0006\u0004\b'\u0010(J\r\u0010)\u001a\u00020\u001c\u00a2\u0006\u0004\b)\u0010%JA\u00102\u001a\u0002012\b\u0010+\u001a\u0004\u0018\u00010*2\u0006\u0010'\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u00072\u0006\u0010-\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u001c2\b\u00100\u001a\u0004\u0018\u00010/\u00a2\u0006\u0004\b2\u00103J\u0089\u0001\u0010@\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020*2\u0006\u00105\u001a\u0002042\u0006\u00106\u001a\u00020\u00072\u0006\u00107\u001a\u00020\u00072\u0006\u00108\u001a\u00020\u00072\u0006\u00109\u001a\u00020\u00072\u0006\u0010:\u001a\u00020\u00072\u0006\u0010;\u001a\u00020\u00072\u0006\u0010<\u001a\u00020\u00072\u0006\u0010=\u001a\u00020\u00072\u0006\u0010>\u001a\u00020\u00072\u0006\u0010?\u001a\u0002012\u0006\u0010-\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u001c2\b\u00100\u001a\u0004\u0018\u00010/H\u0002\u00a2\u0006\u0004\b@\u0010AR,\u0010F\u001a\u001a\u0012\b\u0012\u00060CR\u00020D0Bj\f\u0012\b\u0012\u00060CR\u00020D`E8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bF\u0010GR\u0016\u0010H\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bH\u0010IR\u0016\u0010J\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bJ\u0010IR\u0016\u0010K\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010IR\u0016\u0010L\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bL\u0010IR\u0016\u0010M\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bM\u0010NR\u0016\u0010O\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bO\u0010PR\u0016\u0010Q\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bQ\u0010PR\u0016\u0010R\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bR\u0010SR\u0016\u0010T\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bT\u0010SR\u0016\u0010U\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bU\u0010SR\u0016\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010SR\u0016\u0010V\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010SR\u0016\u0010W\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bW\u0010SR\u0016\u0010X\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bX\u0010SR\u0016\u0010Y\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bY\u0010SR\u0016\u0010Z\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bZ\u0010SR\u0016\u0010[\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b[\u0010NR\u0016\u0010\\\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\\\u0010PR\u0016\u0010]\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b]\u0010SR\u0016\u0010^\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b^\u0010SR\u0016\u0010_\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b_\u0010SR\u0016\u0010`\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b`\u0010SR\u0016\u0010a\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\ba\u0010SR\u0016\u0010b\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bb\u0010SR\u0016\u0010c\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010S\u00a8\u0006d"}, d2={"Lrtx/kimiko/api/ui/window/GuiShatterAnimation$State;", "", "<init>", "()V", "", "seed", "()J", "", "lastProgress", "()F", "value", "", "setProgress", "(F)V", "", "beginRect", "()[F", "x", "y", "width", "height", "pad", "screenWidth", "screenHeight", "begin", "(FFFFFFFJ)V", "cancel", "closeProgress", "", "Lkotlin/jvm/JvmOverloads;", "resume", "(F)Z", "rebase", "durationNs", "gather", "(J)V", "isGathering", "()Z", "blurRadius", "progress", "(F)F", "isActive", "Ljava/nio/ByteBuffer;", "out", "globalAlpha", "scale", "worldMode", "Lrtx/kimiko/api/ui/window/GuiShatterAnimation$Remap;", "remap", "", "buildGeometry", "(Ljava/nio/ByteBuffer;FFFZLrtx/kimiko/api/ui/window/GuiShatterAnimation$Remap;)I", "Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad$Vec2f;", "source", "offsetX", "offsetY", "pivotX", "pivotY", "yawSin", "yawCos", "pitchSin", "pitchCos", "bowlZ", "alpha", "putCurledVertex", "(Ljava/nio/ByteBuffer;Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad$Vec2f;FFFFFFFFFIFZLrtx/kimiko/api/ui/window/GuiShatterAnimation$Remap;)V", "Ljava/util/ArrayList;", "Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad$Polygon;", "Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad;", "Lkotlin/collections/ArrayList;", "shards", "Ljava/util/ArrayList;", "baseAngle", "[F", "baseRadius", "fade", "radial", "gathering", "Z", "gatherStartNs", "J", "gatherNanos", "gatherFrom", "F", "resumeFrom", "resumeBase", "centerX", "centerY", "halfExtentX", "halfExtentY", "aspect", "active", "currentSeed", "beginX", "beginY", "beginWidth", "beginHeight", "beginPad", "beginScreenWidth", "beginScreenHeight", "rtx.kimiko:kimiko"})
    public static final class State {
        @NotNull
        private final ArrayList<VoronoiOfQuad.Polygon> shards = new ArrayList();
        @NotNull
        private float[] baseAngle = new float[0];
        @NotNull
        private float[] baseRadius = new float[0];
        @NotNull
        private float[] fade = new float[0];
        @NotNull
        private float[] radial = new float[0];
        private boolean gathering;
        private long gatherStartNs;
        private long gatherNanos = 360000000L;
        private float gatherFrom;
        private float resumeFrom;
        private float resumeBase;
        private float lastProgress;
        private float centerX;
        private float centerY;
        private float halfExtentX = 1.0f;
        private float halfExtentY = 1.0f;
        private float aspect = 1.0f;
        private boolean active;
        private long currentSeed;
        private float beginX;
        private float beginY;
        private float beginWidth;
        private float beginHeight;
        private float beginPad;
        private float beginScreenWidth;
        private float beginScreenHeight;

        public final long seed() {
            return this.currentSeed;
        }

        public final float lastProgress() {
            return this.lastProgress;
        }

        public final void setProgress(float value) {
            this.lastProgress = INSTANCE.clamp01(value);
        }

        @NotNull
        public final float[] beginRect() {
            float[] fArray = new float[]{this.beginX, this.beginY, this.beginWidth, this.beginHeight, this.beginPad, this.beginScreenWidth, this.beginScreenHeight};
            return fArray;
        }

        public final void begin(float x, float y, float width, float height, float pad, float screenWidth, float screenHeight, long seed) {
            this.cancel();
            if (width <= 1.0f || height <= 1.0f || screenWidth <= 1.0f || screenHeight <= 1.0f) {
                return;
            }
            Random random = new Random(seed);
            this.currentSeed = seed;
            this.beginX = x;
            this.beginY = y;
            this.beginWidth = width;
            this.beginHeight = height;
            this.beginPad = pad;
            this.beginScreenWidth = screenWidth;
            this.beginScreenHeight = screenHeight;
            float sx = INSTANCE.clamp01(x / screenWidth);
            float sy = INSTANCE.clamp01(y / screenHeight);
            float sx2 = INSTANCE.clamp01((x + width) / screenWidth);
            float sy2 = INSTANCE.clamp01((y + height) / screenHeight);
            float nx = INSTANCE.clamp01((x - pad) / screenWidth - 0.004f);
            float ny = INSTANCE.clamp01((y - pad) / screenHeight - 0.004f);
            float nx2 = INSTANCE.clamp01((x + width + pad) / screenWidth + 0.004f);
            float ny2 = INSTANCE.clamp01((y + height + pad) / screenHeight + 0.004f);
            if (sx2 - sx <= 0.001f || sy2 - sy <= 0.001f) {
                return;
            }
            this.aspect = screenWidth / screenHeight;
            this.centerX = (sx + sx2) * 0.5f;
            this.centerY = (sy + sy2) * 0.5f;
            this.halfExtentX = Math.max(1.0E-4f, (sx2 - sx) * 0.5f * this.aspect);
            this.halfExtentY = Math.max(1.0E-4f, (sy2 - sy) * 0.5f);
            int count = 22 + random.nextInt(7);
            VoronoiOfQuad voronoi = VoronoiOfQuad.Companion.spread(nx, ny, nx2, ny2, sx, sy, sx2, sy2, count, this.aspect, 0.45f, 0.32f, 0.68f, random);
            for (VoronoiOfQuad.Polygon polygon : voronoi.getPolygons()) {
                if (this.shards.size() >= 28) break;
                if (polygon.list.size() < 3) continue;
                this.shards.add(polygon);
            }
            if (this.shards.isEmpty()) {
                return;
            }
            int size = this.shards.size();
            this.baseAngle = new float[size];
            this.baseRadius = new float[size];
            this.fade = new float[size];
            this.radial = new float[size];
            for (int i = 0; i < size; ++i) {
                VoronoiOfQuad.Vec2f center = this.shards.get((int)i).center;
                float dx = (center.x - this.centerX) * this.aspect;
                float dy = center.y - this.centerY;
                this.baseAngle[i] = (float)Math.atan2(dy, dx);
                this.baseRadius[i] = (float)Math.sqrt(dx * dx + dy * dy);
                this.fade[i] = 0.45f * random.nextFloat();
                this.radial[i] = 0.65f + 0.7f * random.nextFloat();
            }
            this.active = true;
        }

        public final void cancel() {
            this.shards.clear();
            this.active = false;
            this.gathering = false;
            this.resumeFrom = 0.0f;
            this.resumeBase = 0.0f;
            this.lastProgress = 0.0f;
        }

        @JvmOverloads
        public final boolean resume(float closeProgress) {
            if (!this.isActive()) {
                return false;
            }
            this.gathering = false;
            this.resumeFrom = INSTANCE.clamp01(this.lastProgress);
            this.resumeBase = INSTANCE.clamp01(closeProgress);
            return true;
        }

        public static /* synthetic */ boolean resume$default(State state, float f, int n, Object object) {
            if ((n & 1) != 0) {
                f = 0.0f;
            }
            return state.resume(f);
        }

        public final void rebase(float closeProgress) {
            if (!this.isActive()) {
                return;
            }
            this.gathering = false;
            this.resumeFrom = 0.0f;
            this.resumeBase = INSTANCE.clamp01(closeProgress);
            this.lastProgress = 0.0f;
        }

        public final void gather(long durationNs) {
            if (!this.isActive() || this.gathering) {
                return;
            }
            if (this.lastProgress <= 0.002f) {
                this.cancel();
                return;
            }
            this.gathering = true;
            this.gatherFrom = this.lastProgress;
            this.gatherNanos = Math.max(80000000L, durationNs <= 0L ? 360000000L : durationNs);
            this.gatherStartNs = System.nanoTime();
        }

        public final boolean isGathering() {
            return this.gathering && this.isActive();
        }

        public final float blurRadius() {
            return this.isActive() ? 9.0f * INSTANCE.clamp01(this.lastProgress) : 0.0f;
        }

        public final float progress(float closeProgress) {
            if (!this.isActive()) {
                return 0.0f;
            }
            if (this.gathering) {
                float t = (float)(System.nanoTime() - this.gatherStartNs) / (float)this.gatherNanos;
                if (t >= 1.0f) {
                    this.cancel();
                    return 0.0f;
                }
                this.lastProgress = this.gatherFrom * (1.0f - INSTANCE.easeOutCubic(t));
                return this.lastProgress;
            }
            float span = 1.0f - this.resumeBase;
            float advance = span <= 1.0E-4f ? 1.0f : INSTANCE.clamp01((INSTANCE.clamp01(closeProgress) - this.resumeBase) / span);
            this.lastProgress = INSTANCE.clamp01(this.resumeFrom + (1.0f - this.resumeFrom) * advance);
            return this.lastProgress;
        }

        public final boolean isActive() {
            return this.active && !((Collection)this.shards).isEmpty();
        }

        public final int buildGeometry(@Nullable ByteBuffer out, float progress, float globalAlpha, float scale, boolean worldMode, @Nullable Remap remap) {
            if (!this.isActive() || out == null) {
                return 0;
            }
            float p = INSTANCE.clamp01(progress);
            float push = INSTANCE.easeOutCubic(p);
            float safeScale = scale > 1.0E-4f ? scale : 1.0f;
            out.clear();
            int vertices = 0;
            int n = ((Collection)this.shards).size();
            for (int i = 0; i < n; ++i) {
                VoronoiOfQuad.Polygon original = this.shards.get(i);
                float shardAlpha = INSTANCE.clamp01(1.0f - p * (0.9f + this.fade[i])) * globalAlpha;
                if (shardAlpha <= 0.002f) continue;
                float radius = this.baseRadius[i] * (1.0f + 0.34f * this.radial[i] * push) + 0.028f * this.radial[i] * push;
                float pivotX = this.centerX + (float)Math.cos(this.baseAngle[i]) * radius / this.aspect;
                float pivotY = this.centerY + (float)Math.sin(this.baseAngle[i]) * radius;
                float offsetX = pivotX - original.center.x;
                float offsetY = pivotY - original.center.y;
                float edgeX = INSTANCE.clampSigned((original.center.x - this.centerX) * this.aspect / this.halfExtentX);
                float edgeY = INSTANCE.clampSigned((original.center.y - this.centerY) / this.halfExtentY);
                float yawAngle = (float)Math.toRadians(26.0f * edgeX * push);
                float pitchAngle = (float)Math.toRadians(-26.0f * edgeY * push);
                float bowlZ = 0.1f * (1.0f - Math.min(1.0f, edgeX * edgeX + edgeY * edgeY)) * push;
                float yawSin = (float)Math.sin(yawAngle);
                float yawCos = (float)Math.cos(yawAngle);
                float pitchSin = (float)Math.sin(pitchAngle);
                float pitchCos = (float)Math.cos(pitchAngle);
                List<VoronoiOfQuad.Vec2f> staticVertices = original.getAllVertices();
                int corners = Math.min(24, staticVertices.size());
                int alpha = MathKt.roundToInt((float)(shardAlpha * 255.0f));
                int t = 1;
                while (t + 1 < corners && vertices + 3 <= 1848) {
                    this.putCurledVertex(out, staticVertices.get(0), offsetX, offsetY, pivotX, pivotY, yawSin, yawCos, pitchSin, pitchCos, bowlZ, alpha, safeScale, worldMode, remap);
                    this.putCurledVertex(out, staticVertices.get(t), offsetX, offsetY, pivotX, pivotY, yawSin, yawCos, pitchSin, pitchCos, bowlZ, alpha, safeScale, worldMode, remap);
                    this.putCurledVertex(out, staticVertices.get(t + 1), offsetX, offsetY, pivotX, pivotY, yawSin, yawCos, pitchSin, pitchCos, bowlZ, alpha, safeScale, worldMode, remap);
                    vertices += 3;
                    ++t;
                }
            }
            out.flip();
            return vertices;
        }

        private final void putCurledVertex(ByteBuffer out, VoronoiOfQuad.Vec2f source, float offsetX, float offsetY, float pivotX, float pivotY, float yawSin, float yawCos, float pitchSin, float pitchCos, float bowlZ, int alpha, float scale, boolean worldMode, Remap remap) {
            float localX = (source.x + offsetX - pivotX) * this.aspect;
            float localY = source.y + offsetY - pivotY;
            float rotatedX = localX * yawCos;
            float rotatedZ = -localX * yawSin;
            float finalY = localY * pitchCos - rotatedZ * pitchSin;
            float finalZ = localY * pitchSin + rotatedZ * pitchCos + bowlZ;
            float posX = pivotX + rotatedX / this.aspect;
            float posY = pivotY + finalY;
            float posZ = finalZ;
            if (!worldMode) {
                float perspective = 1.0f / Math.max(0.2f, 1.0f + posZ * 0.9f);
                posX = 0.5f + (posX - 0.5f) * perspective;
                posY = 0.5f + (posY - 0.5f) * perspective;
                posZ = 0.0f;
            }
            float outX = 0.5f + (posX - 0.5f) * scale;
            float outY = 0.5f + (posY - 0.5f) * scale;
            float outZ = posZ * scale;
            float uvX = source.x;
            float uvY = 1.0f - source.y;
            if (remap != null) {
                outX = remap.posOffsetX() + outX * remap.posScaleX();
                outY = remap.posOffsetY() + outY * remap.posScaleY();
                outZ *= remap.posScaleY();
                uvX = remap.uvOffsetX() + uvX * remap.uvScaleX();
                uvY = remap.uvOffsetY() + uvY * remap.uvScaleY();
            }
            out.putFloat(outX);
            out.putFloat(outY);
            out.putFloat(outZ);
            out.putFloat(uvX);
            out.putFloat(uvY);
            out.put((byte)-1);
            out.put((byte)-1);
            out.put((byte)-1);
            out.put((byte)alpha);
        }

        @JvmOverloads
        public final boolean resume() {
            return State.resume$default(this, 0.0f, 1, null);
        }
    }
}

