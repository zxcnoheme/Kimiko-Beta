/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  net.minecraft.world.BlockView
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos.Mutable
 *  net.minecraft.util.math.Direction.Axis
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.shape.VoxelShape
 *  net.minecraft.block.BlockState
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.particles.lyrics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import net.minecraft.world.BlockView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.utils.media.LyricLine;
import rtx.kimiko.utils.media.LyricWord;
import rtx.kimiko.utils.media.Lyrics;
import rtx.kimiko.utils.media.MediaPlayer;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfTextGeometry;
import rtx.kimiko.utils.render.render3d.Text3D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00ca\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 z2\u00020\u0001:\u0006{|}~\u007fzB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\n\u00a2\u0006\u0004\b\r\u0010\u0003J7\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J?\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001f\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\u001f\u0010\"\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b\"\u0010#J7\u0010&\u001a\u00020\n2\u0006\u0010$\u001a\u00020\u001a2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020%2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b&\u0010'J\u0017\u0010)\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b)\u0010*JG\u00100\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020\u00122\u0006\u0010.\u001a\u00020\u00122\u0006\u0010/\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b0\u00101J/\u00108\u001a\u0002042\u0006\u00103\u001a\u0002022\u0006\u00105\u001a\u0002042\u0006\u00106\u001a\u0002042\u0006\u00107\u001a\u000204H\u0002\u00a2\u0006\u0004\b8\u00109J\u001f\u0010;\u001a\u00020\n2\u0006\u0010:\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b;\u0010<J\u0017\u0010>\u001a\u00020\u00122\u0006\u0010$\u001a\u00020=H\u0002\u00a2\u0006\u0004\b>\u0010?J\u001f\u0010@\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020%2\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b@\u0010AJ\u000f\u0010B\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\bB\u0010\u0017J\u0017\u0010D\u001a\u00020\u00122\u0006\u0010C\u001a\u000204H\u0002\u00a2\u0006\u0004\bD\u0010EJ'\u0010H\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\b2\u0006\u0010F\u001a\u00020\u00182\u0006\u0010G\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\bH\u0010IJ\u000f\u0010J\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\bJ\u0010\u0017JG\u0010Q\u001a\u00020\u001a2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010L\u001a\u00020K2\u0006\u0010N\u001a\u00020M2\u0006\u0010P\u001a\u00020O2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\bQ\u0010RR$\u0010U\u001a\u0012\u0012\u0004\u0012\u00020\u001a0Sj\b\u0012\u0004\u0012\u00020\u001a`T8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bU\u0010VR$\u0010W\u001a\u0012\u0012\u0004\u0012\u00020=0Sj\b\u0012\u0004\u0012\u00020=`T8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bW\u0010VR0\u0010Z\u001a\u001e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020+0Xj\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020+`Y8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bZ\u0010[R\u0014\u0010]\u001a\u00020\\8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b]\u0010^R\u0014\u0010`\u001a\u00020_8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b`\u0010aR\u001c\u0010c\u001a\b\u0012\u0004\u0012\u00020K0b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010dR\u0016\u0010e\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\be\u0010fR\u0016\u0010g\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bg\u0010hR\u0016\u0010i\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bi\u0010jR\u0016\u0010k\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bk\u0010jR\u0016\u0010l\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bl\u0010mR\u0016\u0010n\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bn\u0010mR\u0018\u0010p\u001a\u0004\u0018\u00010o8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bp\u0010qR\u0016\u0010r\u001a\u00020O8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\br\u0010sR\u0016\u0010t\u001a\u00020O8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bt\u0010sR\u0018\u0010v\u001a\u0004\u0018\u00010u8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bv\u0010wR\u0016\u0010x\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bx\u0010jR\u0016\u0010y\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\by\u0010h\u00a8\u0006\u0080\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles;", "", "<init>", "()V", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "event", "Lnet/minecraft/MinecraftClient;", "mc", "Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Options;", "options", "", "render", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;Lnet/minecraft/MinecraftClient;Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Options;)V", "reset", "Lnet/minecraft/Camera;", "camera", "", "time", "", "partialTicks", "collect", "(Lnet/minecraft/MinecraftClient;Lnet/minecraft/Camera;JFLrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Options;)V", "currentVocalGate", "()F", "", "title", "Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Held;", "announcement", "(Lnet/minecraft/MinecraftClient;Lnet/minecraft/Camera;Ljava/lang/String;JFLrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Options;)Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Held;", "", "index", "handoff", "(I)J", "alive", "trim", "(JI)V", "piece", "Lnet/minecraft/Vec3d;", "paint", "(Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Held;JLnet/minecraft/Vec3d;Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Options;Lnet/minecraft/MinecraftClient;)V", "value", "settle", "(F)F", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextGeometry$Layout;", "layout", "scale", "lineWidth", "lineHeight", "shatter", "(Lnet/minecraft/MinecraftClient;Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Held;Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextGeometry$Layout;FFFLrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Options;)V", "Lnet/minecraft/ClientWorld;", "level", "", "x", "y", "z", "groundBelow", "(Lnet/minecraft/ClientWorld;DDD)D", "delta", "stepDebris", "(FLrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Options;)V", "Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Debris;", "debrisFade", "(Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Debris;)F", "paintDebris", "(Lnet/minecraft/Vec3d;Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Options;)V", "halfFov", "distance", "visibleWidth", "(D)F", "text", "reach", "sideRoom", "(Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Options;Ljava/lang/String;F)F", "advance", "Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Fragment;", "fragment", "Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Timing;", "timing", "", "words", "create", "(Lnet/minecraft/MinecraftClient;Lnet/minecraft/Camera;Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Fragment;Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Timing;ZFLrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Options;)Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Held;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "held", "Ljava/util/ArrayList;", "debris", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "glyphCache", "Ljava/util/HashMap;", "Lnet/minecraft/BlockPos$Mutable;", "groundProbe", "Lnet/minecraft/BlockPos$Mutable;", "Ljava/util/Random;", "random", "Ljava/util/Random;", "", "fragments", "Ljava/util/List;", "presence", "F", "lastFrameNanos", "J", "lastIndex", "I", "spawnCount", "lastTrack", "Ljava/lang/String;", "announcedTrack", "Lrtx/kimiko/utils/media/Lyrics;", "lastLyrics", "Lrtx/kimiko/utils/media/Lyrics;", "lastWords", "Z", "lastModeCaptured", "Lrtx/kimiko/utils/render/fonts/Fonts;", "lastFont", "Lrtx/kimiko/utils/render/fonts/Fonts;", "lastVocalFrames", "vocalFramesStampMs", "Companion", "Options", "Debris", "Fragment", "Timing", "Held", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nLyricParticles.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LyricParticles.kt\nrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,896:1\n460#2,7:897\n*S KotlinDebug\n*F\n+ 1 LyricParticles.kt\nrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles\n*L\n480#1:897,7\n*E\n"})
public final class LyricParticles {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ArrayList<Held> held = new ArrayList();
    @NotNull
    private final ArrayList<Debris> debris = new ArrayList();
    @NotNull
    private final HashMap<Integer, MsdfTextGeometry.Layout> glyphCache = new HashMap();
    @NotNull
    private final BlockPos.Mutable groundProbe = new BlockPos.Mutable();
    @NotNull
    private final Random random = new Random();
    @NotNull
    private List<Fragment> fragments = CollectionsKt.emptyList();
    private float presence;
    private long lastFrameNanos;
    private int lastIndex = -1;
    private int spawnCount;
    @NotNull
    private String lastTrack = "";
    @NotNull
    private String announcedTrack = "";
    @Nullable
    private Lyrics lastLyrics;
    private boolean lastWords;
    private boolean lastModeCaptured;
    @Nullable
    private Fonts lastFont;
    private int lastVocalFrames = -1;
    private long vocalFramesStampMs;
    @NotNull
    public static final String MODE_LINES = "Строки";
    @NotNull
    public static final String MODE_WORDS = "Слова";
    @NotNull
    public static final String LAYOUT_ARC = "Стандарт";
    @NotNull
    public static final String LAYOUT_SCATTER = "Вразброс";
    @NotNull
    public static final String LAYOUT_CIRCLE = "360";
    private static final float RASTER = 32.0f;
    private static final float GRAY_LEVEL = 0.66f;
    private static final float HEAT_BOOST = 0.35f;
    private static final int PAIR_LENGTH = 4;
    private static final float LINE_STACK = 1.35f;
    private static final float FIT_MARGIN = 0.88f;
    @NotNull
    private static final float[] WORD_SIDES;
    @NotNull
    private static final float[] WORD_LIFTS;
    @NotNull
    private static final float[] WORD_REACH;
    private static final float GOLDEN_ANGLE = 137.5f;
    private static final float SCATTER_LIFT = 2.4f;
    private static final float SCATTER_NEAR = 0.7f;
    private static final float SCATTER_FAR = 1.45f;
    private static final float IN_SHARE = 0.22f;
    private static final float IN_CASCADE_SHARE = 0.3f;
    private static final float OUT_SHARE = 0.24f;
    private static final float OUT_CASCADE_SHARE = 0.26f;
    @NotNull
    private static final float[] LINE_BOUNDS;
    @NotNull
    private static final float[] WORD_BOUNDS;
    private static final long WORD_TAIL = 140L;
    private static final long LINE_MAX_VISIBLE = 7000L;
    private static final long WORD_MIN_VISIBLE = 650L;
    private static final long WORD_MAX_VISIBLE = 2600L;
    private static final long REWIND_GRACE = 500L;
    private static final long ANNOUNCE_VISIBLE = 2600L;
    private static final long ANNOUNCE_EXIT = 520L;
    private static final float ANNOUNCE_IN = 260.0f;
    private static final float ANNOUNCE_OUT = 260.0f;
    private static final float ANNOUNCE_CASCADE = 260.0f;
    private static final float WORD_LEAD = 60.0f;
    private static final float WORD_RISE_SHARE = 0.35f;
    private static final float WORD_RISE_MIN = 90.0f;
    private static final float WORD_RISE_MAX = 220.0f;
    private static final float WORD_FALL_SHARE = 0.55f;
    private static final float WORD_FALL_MIN = 140.0f;
    private static final float WORD_FALL_MAX = 320.0f;
    private static final float ENTER_DROP = 0.3f;
    private static final float EXIT_SINK = 0.34f;
    private static final float SETTLE_BACK = 1.1f;
    private static final float BLUR_IN = 0.2f;
    private static final float BLUR_OUT = 0.26f;
    private static final float BLUR_REFERENCE = 0.4f;
    private static final float GLOW_SPREAD = 0.34f;
    private static final float BOUNCE_HEIGHT = 0.16f;
    private static final float FADE_RANGE = 0.2f;
    private static final float CULL_FACTOR = 8.0f;
    private static final float PAUSE_FADE_MILLIS = 420.0f;
    private static final float PAUSE_BLUR = 0.5f;
    private static final float PAUSE_LIFT = 0.35f;
    private static final float MIN_ALPHA = 0.004f;
    private static final float SOFTNESS = 1.0f;
    private static final float GRAVITY = 14.0f;
    private static final float SETTLE_SECONDS = 0.9f;
    private static final long HEAT_TAIL = 420L;
    private static final long SHATTER_GRACE = 1600L;
    private static final int MAX_DEBRIS = 512;
    private static final int FALL_SEARCH = 24;
    private static final float SPREAD_SIDE = 0.55f;
    private static final float SPREAD_OUT = 0.9f;
    private static final float POP_BASE = -0.05f;
    private static final float POP_RANGE = 0.45f;
    private static final float PEEL_RATE = 3.2f;
    private static final float PEEL_SPREAD_MIN = 0.7f;
    private static final float PEEL_SPREAD_RANGE = 0.6f;
    private static final float PEEL_PUSH_MIN = 0.25f;
    private static final float PEEL_PUSH_RANGE = 0.6f;
    private static final float PEEL_SETTLE = 11.0f;
    private static final double GROUND_CLEARANCE = 0.012;
    private static final float BOUNCE = 0.34f;
    private static final float GROUND_DRAG = 0.55f;
    private static final float REST_SPEED = 1.1f;
    private static final float FALL_TIMEOUT = 9.0f;
    private static final float DEBRIS_FADE = 0.5f;
    private static final float FALL_STEP = 0.06f;
    private static final float FALL_SPAN_MAX = 2.2f;
    private static final float FALL_JITTER_SHARE = 0.3f;
    private static final float PEEL_PREVIEW = 0.13f;

    public final void render(@NotNull WorldRenderEvent event, @NotNull MinecraftClient mc, @NotNull Options options) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        Intrinsics.checkNotNullParameter((Object)mc, (String)"mc");
        Intrinsics.checkNotNullParameter((Object)options, (String)"options");
        if (event.isPortalPass() || mc.player == null || mc.world == null || mc.gameRenderer == null) {
            return;
        }
        if (!MediaPlayer.init()) {
            return;
        }
        MediaPlayer.tick();
        if (options.getFontName() != this.lastFont) {
            this.lastFont = options.getFontName();
            Iterator<Held> iterator = this.held.iterator();
            Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
            Iterator<Held> iterator2 = iterator;
            while (iterator2.hasNext()) {
                Held line = (Held) (iterator2.next());
                line.invalidate();
            }
        }
        MediaPlayer.setLyricsOffsetMillis(options.getSyncMillis());
        float delta = this.advance();
        this.presence = LyricParticles.Companion.approach(this.presence, MediaPlayer.isPlaying() ? 1.0f : 0.0f, delta);
        Camera camera2 = event.getCamera();
        if (camera2 == null) {
            Camera camera3 = mc.gameRenderer.getCamera();
            camera2 = camera3;
            Intrinsics.checkNotNullExpressionValue((Object)camera3, (String)"getMainCamera(...)");
        }
        Camera camera = camera2;
        long time = MediaPlayer.getLyricsTimeMillis();
        this.collect(mc, camera, time, event.getPartialTicks(), options);
        for (int index = this.held.size() - 1; -1 < index; --index) {
            Held piece = this.held.get(index);
            boolean expired = options.getFall() ? piece.getShattered() || time >= piece.getDeath() + 1600L : time >= piece.getDeath();
            if (!expired && time >= piece.getSpawn() - 500L) continue;
            this.held.remove(index);
        }
        this.stepDebris(delta, options);
        if (this.held.isEmpty() && this.debris.isEmpty() || this.presence <= 0.004f) {
            return;
        }
        Text3D.begin(event.getPositionMatrix(), event.getProjectionMatrix(), camera.getCameraPos(), 0.66f, 0.35f, options.getGlow(), 10.88f, 12.8f);
        for (Held piece : this.held) {
            Vec3d vec3d2 = camera.getCameraPos();
            this.paint(piece, time, vec3d2, options, mc);
        }
        Vec3d vec3d3 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"position(...)");
        this.paintDebris(vec3d3, options);
        Text3D.end(options.getThroughWalls());
    }

    public final void reset() {
        this.held.clear();
        this.debris.clear();
        this.glyphCache.clear();
        this.fragments = CollectionsKt.emptyList();
        this.presence = 0.0f;
        this.lastFrameNanos = 0L;
        this.lastIndex = -1;
        this.lastTrack = "";
        this.announcedTrack = "";
        this.lastLyrics = null;
        this.lastModeCaptured = false;
    }

    private final void collect(MinecraftClient mc, Camera camera, long time, float partialTicks, Options options) {
        int next;
        Fragment candidate;
        boolean freshMode;
        String track = MediaPlayer.getTrack().display();
        Lyrics lyrics = MediaPlayer.getLyrics();
        boolean words = options.getWords();
        boolean freshTrack = !Intrinsics.areEqual((Object)track, (Object)this.lastTrack);
        boolean freshLyrics = lyrics != this.lastLyrics;
        boolean bl = freshMode = !this.lastModeCaptured || this.lastWords != words;
        if (freshTrack || freshLyrics || freshMode) {
            this.lastTrack = track;
            this.lastLyrics = lyrics;
            this.lastWords = words;
            this.lastModeCaptured = true;
            this.lastIndex = -1;
            if (freshTrack || freshMode || !((Collection)this.fragments).isEmpty()) {
                this.held.clear();
            }
            List list = this.fragments = lyrics.isEmpty() || !lyrics.synced() ? CollectionsKt.emptyList() : LyricParticles.Companion.split(lyrics, words);
        }
        if (!Intrinsics.areEqual((Object)track, (Object)this.announcedTrack) && !StringsKt.isBlank((CharSequence)track) && MediaPlayer.getTrack().durationMillis() > 0L) {
            this.announcedTrack = track;
            this.held.add(this.announcement(mc, camera, track, time, partialTicks, options));
        }
        if (this.fragments.isEmpty()) {
            return;
        }
        if (this.lastIndex >= 0 && this.lastIndex < this.fragments.size() && time < this.fragments.get(this.lastIndex).getStartMillis() - 500L) {
            this.held.clear();
            this.lastIndex = -1;
        }
        int alive = Math.max(1, options.getLimit());
        while (this.lastIndex + 1 < this.fragments.size() && time >= (candidate = this.fragments.get(next = this.lastIndex + 1)).getStartMillis()) {
            this.lastIndex = next;
            Timing timing = LyricParticles.Companion.timing(candidate, this.handoff(next), words);
            if (time >= timing.getDeath()) continue;
            this.trim(time, alive);
            this.held.add(this.create(mc, camera, candidate, timing, words, partialTicks, options));
        }
    }

    private final float currentVocalGate() {
        float measured = MediaPlayer.vocalLevel();
        if (measured < 0.0f) {
            return 1.0f;
        }
        int frames = MediaPlayer.vocalFrames();
        long now = System.currentTimeMillis();
        if (frames != this.lastVocalFrames) {
            this.lastVocalFrames = frames;
            this.vocalFramesStampMs = now;
        } else if (now - this.vocalFramesStampMs > 1200L) {
            return 1.0f;
        }
        return Math.min(1.0f, 0.45f + measured * 1.2f);
    }

    private final Held announcement(MinecraftClient mc, Camera camera, String title, long time, float partialTicks, Options options) {
        Fragment fragment = new Fragment(title, CollectionsKt.emptyList(), time, time + 2600L);
        Timing timing = new Timing(time, time + 2600L, time + 2600L + 520L, 260.0f, 260.0f, 260.0f, 260.0f);
        return this.create(mc, camera, fragment, timing, false, partialTicks, options);
    }

    private final long handoff(int index) {
        if (index + 1 < this.fragments.size()) {
            return this.fragments.get(index + 1).getStartMillis();
        }
        Fragment last = this.fragments.get(index);
        return Math.max(last.getEndMillis(), last.getStartMillis() + 1L);
    }

    private final void trim(long time, int alive) {
        int live = 0;
        for (Held piece : this.held) {
            if (piece.getFadeFrom() <= time) continue;
            ++live;
        }
        while (live >= alive) {
            Held target = null;
            for (Held piece : this.held) {
                if (piece.getFadeFrom() <= time || target != null && piece.getSpawn() >= target.getSpawn()) continue;
                target = piece;
            }
            if (target == null) break;
            target.retire(time);
            --live;
        }
        while (this.held.size() > alive * 2) {
            this.held.remove(0);
        }
    }

    private final void paint(Held piece, long time, Vec3d camera, Options options, MinecraftClient mc) {
        float cull;
        double distanceZ;
        double distanceY;
        MsdfTextGeometry.Layout layout = MsdfTextGeometry.layout(options.getFontName(), piece.getFragment().getText(), 32.0f);
        if (layout.empty()) {
            return;
        }
        double distanceX = piece.getX() - camera.x;
        double range = Math.sqrt(distanceX * distanceX + (distanceY = piece.getY() - camera.y) * distanceY + (distanceZ = piece.getZ() - camera.z) * distanceZ);
        if (range > (double)(cull = options.getRadius() * 8.0f)) {
            return;
        }
        float lineHeight = layout.height();
        float lineWidth = Math.max(1.0f, layout.width());
        float fade = LyricParticles.Companion.clamp((float)(((double)cull - range) / (double)(cull * 0.2f)), 0.0f, 1.0f);
        float alpha = options.getOpacity() * fade * this.presence;
        if (alpha <= 0.004f) {
            return;
        }
        float scale = options.getSize() / lineHeight;
        float screenWidth = this.visibleWidth(piece.getReach()) * 0.88f;
        float worldWidth = lineWidth * scale;
        if (screenWidth > 0.0f && worldWidth > screenWidth) {
            scale *= screenWidth / worldWidth;
        }
        Text3D.plane(piece.getX(), piece.getY(), piece.getZ(), piece.getRightX(), 0.0f, piece.getRightZ(), 0.0f, 1.0f, 0.0f, scale, lineWidth * 0.5f, lineHeight * 0.5f);
        piece.measure(options.getFontName());
        float vocalGate = this.currentVocalGate();
        Timing timing = piece.getTiming();
        float blurScale = 1.0f * lineHeight;
        float lift = 0.16f * lineHeight;
        if (options.getFall() && time >= piece.shatterAt()) {
            if (!piece.getShattered()) {
                piece.setShattered(true);
                piece.setDeath(time);
                this.shatter(mc, piece, layout, scale, lineWidth, lineHeight, options);
            }
            return;
        }
        for (MsdfTextGeometry.Quad quad : layout.quads()) {
            float center = (quad.x0() + quad.x1()) * 0.5f;
            float phase = LyricParticles.Companion.clamp(center / lineWidth, 0.0f, 1.0f);
            float appear = LyricParticles.Companion.clamp(((float)time - ((float)piece.getSpawn() + phase * timing.getInCascade())) / timing.getInTime(), 0.0f, 1.0f);
            float vanish = options.getFall() ? 0.0f : LyricParticles.Companion.clamp(((float)time - ((float)piece.getFadeFrom() + phase * timing.getOutCascade())) / timing.getOutTime(), 0.0f, 1.0f);
            float fadeIn = 1.0f - (1.0f - appear) * (1.0f - appear) * (1.0f - appear);
            float settled = this.settle(appear);
            float leaving = vanish * vanish;
            float visible = fadeIn * (1.0f - leaving);
            if (visible <= 0.004f) continue;
            float rest = 1.0f - this.presence;
            float heat = piece.heat(center, time) * vocalGate;
            float shift = 0.3f * lineHeight * (1.0f - settled) - 0.34f * lineHeight * leaving + 0.35f * lineHeight * rest + lift * heat * (1.0f - leaving);
            float smear = (0.2f * (1.0f - appear) + 0.26f * vanish + 0.5f * rest) * blurScale;
            Text3D.glyph(layout, quad, 0.0f, shift, smear, heat, alpha * visible);
        }
    }

    private final float settle(float value) {
        float t = value - 1.0f;
        return 1.0f + 2.1f * t * t * t + 1.1f * t * t;
    }

    private final void shatter(MinecraftClient mc, Held piece, MsdfTextGeometry.Layout layout, float scale, float lineWidth, float lineHeight, Options options) {
        ClientWorld clientWorld3 = mc.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        float outX = -piece.getRightZ();
        float outZ = piece.getRightX();
        int count = layout.quads().size();
        float step = Math.min(0.06f, 2.2f / (float)Math.max(1, count - 1));
        for (int order = 0; order < count; ++order) {
            if (this.debris.size() >= 512) {
                return;
            }
            MsdfTextGeometry.Quad quad = layout.quads().get(order);
            float centerX = (quad.x0() + quad.x1()) * 0.5f;
            float centerY = (quad.y0() + quad.y1()) * 0.5f;
            float localX = centerX - lineWidth * 0.5f;
            float localY = lineHeight * 0.5f - centerY;
            double worldX = piece.getX() + (double)(piece.getRightX() * localX * scale);
            double worldY = piece.getY() + (double)(localY * scale);
            double worldZ = piece.getZ() + (double)(piece.getRightZ() * localX * scale);
            float sideways = (this.random.nextFloat() - 0.5f) * 0.55f;
            float outward = (0.25f + this.random.nextFloat() * 0.6f) * 0.9f;
            float peel = 3.2f * (0.7f + this.random.nextFloat() * 0.6f) * (this.random.nextBoolean() ? 1.0f : -1.0f);
            this.debris.add(new Debris(quad.codePoint(), worldX, worldY, worldZ, piece.getRightX() * sideways + outX * outward, -0.05f + this.random.nextFloat() * 0.45f, piece.getRightZ() * sideways + outZ * outward, piece.getRightX(), piece.getRightZ(), peel, (quad.y1() - quad.y0()) * 0.5f * scale, this.groundBelow(level, worldX, worldY, worldZ), scale, (float)order * step + this.random.nextFloat() * step * 0.3f));
        }
    }

    private final double groundBelow(ClientWorld level, double x, double y, double z) {
        int blockX = MathHelper.floor(x);
        int blockZ = MathHelper.floor(z);
        int startY = MathHelper.floor(y);
        int lowest = startY - 24;
        for (int blockY = startY; blockY >= lowest; --blockY) {
            this.groundProbe.set(blockX, blockY, blockZ);
            BlockState state = level.getBlockState((BlockPos)this.groundProbe);
            if (state.isAir()) continue;
            VoxelShape shape = state.getCollisionShape((BlockView)level, (BlockPos)this.groundProbe);
            if (shape.isEmpty()) continue;
            double top = (double)blockY + shape.getMax(Direction.Axis.Y);
            if (top <= y) {
                return top;
            }
        }
        return Double.NEGATIVE_INFINITY;
    }

    private final void stepDebris(float delta, Options options) {
        if (this.debris.isEmpty()) {
            return;
        }
        if (!options.getFall()) {
            this.debris.clear();
            return;
        }
        for (int index = this.debris.size() - 1; -1 < index; --index) {
            Debris piece = (Debris) (this.debris.get(index));
            if (piece.getDelay() > 0.0f) {
                piece.setDelay(piece.getDelay() - delta);
                float ready = LyricParticles.Companion.clamp(1.0f - piece.getDelay() / piece.getDelayFull(), 0.0f, 1.0f);
                piece.setSpin(0.13f * ready * ready * Math.signum(piece.getSpinRate()));
                continue;
            }
            piece.setAge(piece.getAge() + delta);
            if (piece.getGrounded()) {
                piece.setRest(piece.getRest() + delta);
                piece.setSpin(piece.getSpin() + (piece.getSpinTarget() - piece.getSpin()) * LyricParticles.Companion.clamp(11.0f * delta, 0.0f, 1.0f));
                piece.setY(piece.getGround() + piece.restHeight());
            } else {
                piece.setVelocityY(piece.getVelocityY() - 14.0f * delta);
                piece.setX(piece.getX() + (double)(piece.getVelocityX() * delta));
                piece.setY(piece.getY() + (double)(piece.getVelocityY() * delta));
                piece.setZ(piece.getZ() + (double)(piece.getVelocityZ() * delta));
                piece.setSpin(piece.getSpin() + piece.getSpinRate() * delta);
                double floor = piece.getGround() + piece.restHeight();
                if (!(piece.getGround() == Double.NEGATIVE_INFINITY) && piece.getY() <= floor) {
                    piece.setY(floor);
                    if (-piece.getVelocityY() <= 1.1f) {
                        piece.setGrounded(true);
                        piece.setVelocityX(0.0f);
                        piece.setVelocityY(0.0f);
                        piece.setVelocityZ(0.0f);
                        piece.setSpinRate(0.0f);
                        piece.setSpinTarget(LyricParticles.Companion.flatAngle(piece.getSpin()));
                    } else {
                        piece.setVelocityY(-piece.getVelocityY() * 0.34f);
                        piece.setVelocityX(piece.getVelocityX() * 0.55f);
                        piece.setVelocityZ(piece.getVelocityZ() * 0.55f);
                        piece.setSpinRate(0.0f);
                    }
                }
            }
            if (!(this.debrisFade(piece) <= 0.004f) || !piece.getGrounded() && !(piece.getAge() > 9.0f)) continue;
            this.debris.remove(index);
        }
    }

    private final float debrisFade(Debris piece) {
        float settled = piece.getGrounded() ? LyricParticles.Companion.clamp(1.0f - (piece.getRest() - 0.9f) / 0.5f, 0.0f, 1.0f) : 1.0f;
        float timeout = piece.getGrounded() ? 1.0f : LyricParticles.Companion.clamp((9.0f - piece.getAge()) / 0.5f, 0.0f, 1.0f);
        return Math.min(settled, timeout);
    }

    /*
     * WARNING - void declaration
     */
    private final void paintDebris(Vec3d camera, Options options) {
        if (this.debris.isEmpty()) {
            return;
        }
        this.glyphCache.clear();
        float cull = options.getRadius() * 8.0f;
        Iterator<Debris> iterator = this.debris.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Debris> iterator2 = iterator;
        while (iterator2.hasNext()) {
            MsdfTextGeometry.Layout layout;
            Object object;
            Map $this$getOrPut$iv = this.glyphCache;
            float alpha;
            double distanceZ;
            double distanceY;
            double distanceX;
            double range;
            Debris piece = (Debris) (iterator2.next());
            float fade = this.debrisFade(piece);
            if (fade <= 0.004f || (range = Math.sqrt((distanceX = piece.getX() - camera.x) * distanceX + (distanceY = piece.getY() - camera.y) * distanceY + (distanceZ = piece.getZ() - camera.z) * distanceZ)) > (double)cull || (alpha = options.getOpacity() * this.presence * fade * LyricParticles.Companion.clamp((float)(((double)cull - range) / (double)(cull * 0.2f)), 0.0f, 1.0f)) <= 0.004f) continue;
            Map map = this.glyphCache;
            Integer key$iv = piece.getCodePoint();
            boolean $i$f$getOrPut = false;
            Object value$iv = $this$getOrPut$iv.get(key$iv);
            if (value$iv == null) {
                boolean bl = false;
                Fonts fonts = options.getFontName();
                char[] cArray = Character.toChars(piece.getCodePoint());
                MsdfTextGeometry.Layout answer$iv = MsdfTextGeometry.layout(fonts, new String(cArray), 32.0f);
                $this$getOrPut$iv.put(key$iv, answer$iv);
                object = answer$iv;
            } else {
                object = value$iv;
            }
            if ((layout = (MsdfTextGeometry.Layout)object).empty()) continue;
            MsdfTextGeometry.Quad quad = layout.quads().get(0);
            float cos = (float)Math.cos(piece.getSpin());
            float sin = (float)Math.sin(piece.getSpin());
            Text3D.plane(piece.getX(), piece.getY(), piece.getZ(), piece.getRightX(), 0.0f, piece.getRightZ(), -piece.getRightZ() * sin, cos, piece.getRightX() * sin, piece.getScale(), (quad.x0() + quad.x1()) * 0.5f, (quad.y0() + quad.y1()) * 0.5f);
            Text3D.glyph(layout, quad, 0.0f, 0.0f, 0.0f, 0.0f, alpha);
        }
    }

    private final float halfFov() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        if (minecraft.getWindow() == null || minecraft.options == null) {
            return 0.0f;
        }
        int width = minecraft.getWindow().getFramebufferWidth();
        int height = minecraft.getWindow().getFramebufferHeight();
        if (width <= 0 || height <= 0) {
            return 0.0f;
        }
        double vertical = Math.toRadians(Math.max(30.0, (double)((Number)minecraft.options.getFov().getValue()).intValue()));
        double aspect = (double)width / (double)height;
        return (float)Math.atan(Math.tan(vertical * 0.5) * aspect);
    }

    private final float visibleWidth(double distance) {
        float half = this.halfFov();
        return half <= 0.0f ? 0.0f : (float)(2.0 * distance * Math.tan(half));
    }

    private final float sideRoom(Options options, String text, float reach) {
        float bound = (float)Math.toDegrees(this.halfFov()) * 0.88f;
        if (bound <= 0.0f || reach <= 0.0f) {
            return 0.0f;
        }
        MsdfTextGeometry.Layout layout = MsdfTextGeometry.layout(options.getFontName(), text, 32.0f);
        if (layout.empty()) {
            return 0.0f;
        }
        float worldWidth = layout.width() * (options.getSize() / layout.height());
        float visible = this.visibleWidth(reach) * 0.88f;
        if (visible > 0.0f && worldWidth > visible) {
            worldWidth = visible;
        }
        float half = (float)Math.toDegrees(Math.atan(worldWidth * 0.5f / reach));
        return Math.max(0.0f, bound - half);
    }

    private final float advance() {
        long now = System.nanoTime();
        float delta = this.lastFrameNanos == 0L ? 0.0f : LyricParticles.Companion.clamp((float)(now - this.lastFrameNanos) / 1.0E9f, 0.0f, 0.25f);
        this.lastFrameNanos = now;
        return delta;
    }

    private final Held create(MinecraftClient mc, Camera camera, Fragment fragment, Timing timing, boolean words, float partialTicks, Options options) {
        int n = this.spawnCount;
        this.spawnCount = n + 1;
        int index = n;
        int slot = Math.floorMod(index, WORD_SIDES.length);
        float offset = 0.0f;
        float lift = options.getHeight();
        float reach = options.getRadius();
        if (!words) {
            int row = Math.floorMod(index, Math.max(1, options.getLimit()));
            offset = (slot & 1) == 0 ? -1.0f : 1.0f;
            lift += (float)(options.getLimit() - 1 - row) * options.getSize() * 1.35f;
        } else if (Intrinsics.areEqual((Object)LAYOUT_CIRCLE, (Object)options.getLayout())) {
            lift += WORD_LIFTS[slot];
            reach *= WORD_REACH[slot];
            offset = Float.NaN;
        } else if (Intrinsics.areEqual((Object)LAYOUT_SCATTER, (Object)options.getLayout())) {
            offset = (this.random.nextFloat() - 0.5f) * 2.0f;
            lift += (this.random.nextFloat() - 0.5f) * 2.4f;
            reach *= 0.7f + this.random.nextFloat() * 0.75000006f;
        } else {
            offset = WORD_SIDES[slot];
            lift += WORD_LIFTS[slot];
            reach *= WORD_REACH[slot];
        }
        offset = Float.isNaN(offset) ? (float)index * 137.5f % 360.0f : offset * this.sideRoom(options, fragment.getText(), reach);
        float radians = (float)Math.toRadians(camera.getYaw() + offset);
        float directionX = -((float)Math.sin(radians));
        float directionZ = (float)Math.cos(radians);
        ClientPlayerEntity clientPlayerEntity2 = mc.player;
        if (clientPlayerEntity2 == null) {
            return new Held(fragment, timing, 0.0, 0.0, 0.0, 0.0f, 0.0f, 0.0f, 128, null);
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        Vec3d vec3d2 = player.getLerpedPos(partialTicks);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getPosition(...)");
        Vec3d base = vec3d2;
        return new Held(fragment, timing, base.x + (double)(directionX * reach), base.y + (double)lift, base.z + (double)(directionZ * reach), -directionZ, directionX, Math.max(0.5f, reach));
    }

    static {
        float[] fArray = new float[]{-0.4f, 0.52f, -0.86f, 1.0f, -0.19f, 0.31f, -0.69f, 0.78f};
        WORD_SIDES = fArray;
        fArray = new float[]{0.0f, 0.5f, -0.35f, 0.65f, 0.25f, -0.2f, 0.55f, -0.45f};
        WORD_LIFTS = fArray;
        fArray = new float[]{1.0f, 0.86f, 1.18f, 0.94f, 1.26f, 0.9f, 1.1f, 0.82f};
        WORD_REACH = fArray;
        fArray = new float[]{140.0f, 300.0f, 110.0f, 340.0f, 180.0f, 380.0f, 110.0f, 340.0f};
        LINE_BOUNDS = fArray;
        fArray = new float[]{70.0f, 170.0f, 40.0f, 120.0f, 110.0f, 230.0f, 40.0f, 140.0f};
        WORD_BOUNDS = fArray;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b?\n\u0002\u0010\u0006\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J%\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ'\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J'\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ'\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001aR\u0014\u0010 \u001a\u00020\u001f8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\u001f8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\"\u0010!R\u0014\u0010#\u001a\u00020\u001f8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b#\u0010!R\u0014\u0010$\u001a\u00020\u001f8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b$\u0010!R\u0014\u0010%\u001a\u00020\u001f8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b%\u0010!R\u0014\u0010&\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010'R\u0014\u0010)\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010'R\u0014\u0010+\u001a\u00020*8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010,R\u0014\u0010-\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010'R\u0014\u0010.\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010'R\u0014\u00100\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0014\u00102\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u00101R\u0014\u00103\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b3\u00101R\u0014\u00104\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u0010'R\u0014\u00105\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b5\u0010'R\u0014\u00106\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b6\u0010'R\u0014\u00107\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b7\u0010'R\u0014\u00108\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b8\u0010'R\u0014\u00109\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b9\u0010'R\u0014\u0010:\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b:\u0010'R\u0014\u0010;\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b;\u0010'R\u0014\u0010<\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u00101R\u0014\u0010=\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u00101R\u0014\u0010>\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u0010?R\u0014\u0010A\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bA\u0010?R\u0014\u0010B\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bB\u0010?R\u0014\u0010C\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bC\u0010?R\u0014\u0010D\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bD\u0010?R\u0014\u0010E\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bE\u0010?R\u0014\u0010F\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bF\u0010'R\u0014\u0010G\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bG\u0010'R\u0014\u0010H\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bH\u0010'R\u0014\u0010I\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bI\u0010'R\u0014\u0010J\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bJ\u0010'R\u0014\u0010K\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bK\u0010'R\u0014\u0010L\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bL\u0010'R\u0014\u0010M\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bM\u0010'R\u0014\u0010N\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bN\u0010'R\u0014\u0010O\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bO\u0010'R\u0014\u0010P\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bP\u0010'R\u0014\u0010Q\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bQ\u0010'R\u0014\u0010R\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bR\u0010'R\u0014\u0010S\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bS\u0010'R\u0014\u0010T\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bT\u0010'R\u0014\u0010U\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bU\u0010'R\u0014\u0010V\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bV\u0010'R\u0014\u0010W\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bW\u0010'R\u0014\u0010X\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bX\u0010'R\u0014\u0010Y\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bY\u0010'R\u0014\u0010Z\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bZ\u0010'R\u0014\u0010[\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b[\u0010'R\u0014\u0010\\\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\\\u0010'R\u0014\u0010]\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b]\u0010'R\u0014\u0010^\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b^\u0010'R\u0014\u0010_\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b_\u0010'R\u0014\u0010`\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b`\u0010'R\u0014\u0010a\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\ba\u0010?R\u0014\u0010b\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bb\u0010?R\u0014\u0010c\u001a\u00020*8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bc\u0010,R\u0014\u0010d\u001a\u00020*8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bd\u0010,R\u0014\u0010e\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\be\u0010'R\u0014\u0010f\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bf\u0010'R\u0014\u0010g\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bg\u0010'R\u0014\u0010h\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bh\u0010'R\u0014\u0010i\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bi\u0010'R\u0014\u0010j\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bj\u0010'R\u0014\u0010k\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bk\u0010'R\u0014\u0010l\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bl\u0010'R\u0014\u0010m\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bm\u0010'R\u0014\u0010n\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bn\u0010'R\u0014\u0010p\u001a\u00020o8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bp\u0010qR\u0014\u0010r\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\br\u0010'R\u0014\u0010s\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bs\u0010'R\u0014\u0010t\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bt\u0010'R\u0014\u0010u\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bu\u0010'R\u0014\u0010v\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bv\u0010'R\u0014\u0010w\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bw\u0010'R\u0014\u0010x\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bx\u0010'R\u0014\u0010y\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\by\u0010'R\u0014\u0010z\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bz\u0010'\u00a8\u0006{"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles.Companion;", "", "<init>", "()V", "", "spin", "flatAngle", "(F)F", "Lrtx/kimiko/utils/media/Lyrics;", "lyrics", "", "words", "", "Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Fragment;", "split", "(Lrtx/kimiko/utils/media/Lyrics;Z)Ljava/util/List;", "fragment", "", "handoff", "Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Timing;", "timing", "(Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Fragment;JZ)Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Timing;", "value", "low", "high", "clamp", "(FFF)F", "current", "target", "delta", "approach", "", "MODE_LINES", "Ljava/lang/String;", "MODE_WORDS", "LAYOUT_ARC", "LAYOUT_SCATTER", "LAYOUT_CIRCLE", "RASTER", "F", "GRAY_LEVEL", "HEAT_BOOST", "", "PAIR_LENGTH", "I", "LINE_STACK", "FIT_MARGIN", "", "WORD_SIDES", "[F", "WORD_LIFTS", "WORD_REACH", "GOLDEN_ANGLE", "SCATTER_LIFT", "SCATTER_NEAR", "SCATTER_FAR", "IN_SHARE", "IN_CASCADE_SHARE", "OUT_SHARE", "OUT_CASCADE_SHARE", "LINE_BOUNDS", "WORD_BOUNDS", "WORD_TAIL", "J", "LINE_MAX_VISIBLE", "WORD_MIN_VISIBLE", "WORD_MAX_VISIBLE", "REWIND_GRACE", "ANNOUNCE_VISIBLE", "ANNOUNCE_EXIT", "ANNOUNCE_IN", "ANNOUNCE_OUT", "ANNOUNCE_CASCADE", "WORD_LEAD", "WORD_RISE_SHARE", "WORD_RISE_MIN", "WORD_RISE_MAX", "WORD_FALL_SHARE", "WORD_FALL_MIN", "WORD_FALL_MAX", "ENTER_DROP", "EXIT_SINK", "SETTLE_BACK", "BLUR_IN", "BLUR_OUT", "BLUR_REFERENCE", "GLOW_SPREAD", "BOUNCE_HEIGHT", "FADE_RANGE", "CULL_FACTOR", "PAUSE_FADE_MILLIS", "PAUSE_BLUR", "PAUSE_LIFT", "MIN_ALPHA", "SOFTNESS", "GRAVITY", "SETTLE_SECONDS", "HEAT_TAIL", "SHATTER_GRACE", "MAX_DEBRIS", "FALL_SEARCH", "SPREAD_SIDE", "SPREAD_OUT", "POP_BASE", "POP_RANGE", "PEEL_RATE", "PEEL_SPREAD_MIN", "PEEL_SPREAD_RANGE", "PEEL_PUSH_MIN", "PEEL_PUSH_RANGE", "PEEL_SETTLE", "", "GROUND_CLEARANCE", "D", "BOUNCE", "GROUND_DRAG", "REST_SPEED", "FALL_TIMEOUT", "DEBRIS_FADE", "FALL_STEP", "FALL_SPAN_MAX", "FALL_JITTER_SHARE", "PEEL_PREVIEW", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float flatAngle(float spin) {
            float quarter = 1.5707964f;
            int steps = Math.round((spin - quarter) / (float)Math.PI);
            return quarter + (float)steps * (float)Math.PI;
        }

        private final List<Fragment> split(Lyrics lyrics, boolean words) {
            ArrayList<Fragment> result = new ArrayList<Fragment>();
            for (LyricLine line : lyrics.lines()) {
                int take;
                String text = line.text();
                if (StringsKt.isBlank((CharSequence)text)) continue;
                List<LyricWord> source = line.words();
                if (!words || source.isEmpty()) {
                    result.add(new Fragment(text, source, line.startMillis(), line.endMillis()));
                    continue;
                }
                for (int index = 0; index < source.size(); index += take) {
                    take = index + 1 < source.size() && source.get(index).text().length() <= 4 ? 2 : 1;
                    LyricWord first = source.get(index);
                    LyricWord last = source.get(index + take - 1);
                    ArrayList<LyricWord> chunk = new ArrayList<LyricWord>(take);
                    for (int offset = 0; offset < take; ++offset) {
                        LyricWord word = source.get(index + offset);
                        chunk.add(new LyricWord(word.startMillis(), word.endMillis(), word.text(), word.begin() - first.begin(), word.end() - first.begin()));
                    }
                    String string = text.substring(first.begin(), last.end());
                    Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
                    List<LyricWord> list = List.copyOf((Collection)chunk);
                    Intrinsics.checkNotNullExpressionValue(list, (String)"copyOf(...)");
                    result.add(new Fragment(string, list, first.startMillis(), last.endMillis()));
                }
            }
            List<Fragment> list = List.copyOf((Collection)result);
            Intrinsics.checkNotNullExpressionValue(list, (String)"copyOf(...)");
            return list;
        }

        private final Timing timing(Fragment fragment, long handoff, boolean words) {
            float[] bounds = words ? WORD_BOUNDS : LINE_BOUNDS;
            float span = Math.max(1.0f, (float)(fragment.getEndMillis() - fragment.getStartMillis()));
            float inTime = this.clamp(span * 0.22f, bounds[0], bounds[1]);
            float inCascade = this.clamp(span * 0.3f, bounds[2], bounds[3]);
            float outTime = this.clamp(span * 0.24f, bounds[4], bounds[5]);
            float outCascade = this.clamp(span * 0.26f, bounds[6], bounds[7]);
            long spawn = fragment.getStartMillis();
            int exit = Math.round(outTime + outCascade);
            long entrance = spawn + (long)Math.round(inTime + inCascade);
            long visibleEnd = words ? Math.min(Math.max(fragment.getEndMillis() + 140L, spawn + 650L), spawn + 2600L) : Math.min(handoff - (long)exit, spawn + 7000L);
            visibleEnd = Math.max(visibleEnd, entrance);
            return new Timing(spawn, visibleEnd, visibleEnd + (long)exit, inTime, inCascade, outTime, outCascade);
        }

        private final float clamp(float value, float low, float high) {
            return value < low ? low : (value > high ? high : value);
        }

        private final float approach(float current, float target, float delta) {
            if (delta <= 0.0f) {
                return current;
            }
            return current + (target - current) * (1.0f - (float)Math.exp(-delta * 1000.0f / 420.0f));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b9\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001Bw\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\b\u0012\u0006\u0010\r\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\b\u0012\u0006\u0010\u000f\u001a\u00020\b\u0012\u0006\u0010\u0010\u001a\u00020\u0004\u0012\u0006\u0010\u0011\u001a\u00020\b\u0012\u0006\u0010\u0012\u001a\u00020\b\u00a2\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u0016\"\u0004\b\u001c\u0010\u001dR\"\u0010\u0006\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0006\u0010\u001a\u001a\u0004\b\u001e\u0010\u0016\"\u0004\b\u001f\u0010\u001dR\"\u0010\u0007\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0007\u0010\u001a\u001a\u0004\b \u0010\u0016\"\u0004\b!\u0010\u001dR\"\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\t\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\"\u0010\n\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\n\u0010\"\u001a\u0004\b'\u0010$\"\u0004\b(\u0010&R\"\u0010\u000b\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\"\u001a\u0004\b)\u0010$\"\u0004\b*\u0010&R\u0017\u0010\f\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\f\u0010\"\u001a\u0004\b+\u0010$R\u0017\u0010\r\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\r\u0010\"\u001a\u0004\b,\u0010$R\"\u0010\u000e\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\"\u001a\u0004\b-\u0010$\"\u0004\b.\u0010&R\u0017\u0010\u000f\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\u000f\u0010\"\u001a\u0004\b/\u0010$R\u0017\u0010\u0010\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0010\u0010\u001a\u001a\u0004\b0\u0010\u0016R\u0017\u0010\u0011\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010\"\u001a\u0004\b1\u0010$R\"\u0010\u0012\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010\"\u001a\u0004\b2\u0010$\"\u0004\b3\u0010&R\u0017\u00104\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b4\u0010\"\u001a\u0004\b5\u0010$R\"\u00106\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b6\u0010\"\u001a\u0004\b7\u0010$\"\u0004\b8\u0010&R\"\u00109\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b9\u0010\"\u001a\u0004\b:\u0010$\"\u0004\b;\u0010&R\"\u0010<\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b<\u0010\"\u001a\u0004\b=\u0010$\"\u0004\b>\u0010&R\"\u0010?\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b?\u0010\"\u001a\u0004\b@\u0010$\"\u0004\bA\u0010&R\"\u0010C\u001a\u00020B8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\bE\u0010F\"\u0004\bG\u0010H\u00a8\u0006I"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Debris;", "", "", "codePoint", "", "x", "y", "z", "", "velocityX", "velocityY", "velocityZ", "rightX", "rightZ", "spinRate", "half", "ground", "scale", "delay", "<init>", "(IDDDFFFFFFFDFF)V", "restHeight", "()D", "I", "getCodePoint", "()I", "D", "getX", "setX", "(D)V", "getY", "setY", "getZ", "setZ", "F", "getVelocityX", "()F", "setVelocityX", "(F)V", "getVelocityY", "setVelocityY", "getVelocityZ", "setVelocityZ", "getRightX", "getRightZ", "getSpinRate", "setSpinRate", "getHalf", "getGround", "getScale", "getDelay", "setDelay", "delayFull", "getDelayFull", "spin", "getSpin", "setSpin", "spinTarget", "getSpinTarget", "setSpinTarget", "age", "getAge", "setAge", "rest", "getRest", "setRest", "", "grounded", "Z", "getGrounded", "()Z", "setGrounded", "(Z)V", "rtx.kimiko:kimiko"})
    private static final class Debris {
        private final int codePoint;
        private double x;
        private double y;
        private double z;
        private float velocityX;
        private float velocityY;
        private float velocityZ;
        private final float rightX;
        private final float rightZ;
        private float spinRate;
        private final float half;
        private final double ground;
        private final float scale;
        private float delay;
        private final float delayFull;
        private float spin;
        private float spinTarget;
        private float age;
        private float rest;
        private boolean grounded;

        public Debris(int codePoint, double x, double y, double z, float velocityX, float velocityY, float velocityZ, float rightX, float rightZ, float spinRate, float half, double ground, float scale, float delay) {
            this.codePoint = codePoint;
            this.x = x;
            this.y = y;
            this.z = z;
            this.velocityX = velocityX;
            this.velocityY = velocityY;
            this.velocityZ = velocityZ;
            this.rightX = rightX;
            this.rightZ = rightZ;
            this.spinRate = spinRate;
            this.half = half;
            this.ground = ground;
            this.scale = scale;
            this.delay = delay;
            this.delayFull = Math.max(this.delay, 1.0E-4f);
        }

        public final int getCodePoint() {
            return this.codePoint;
        }

        public final double getX() {
            return this.x;
        }

        public final void setX(double d) {
            this.x = d;
        }

        public final double getY() {
            return this.y;
        }

        public final void setY(double d) {
            this.y = d;
        }

        public final double getZ() {
            return this.z;
        }

        public final void setZ(double d) {
            this.z = d;
        }

        public final float getVelocityX() {
            return this.velocityX;
        }

        public final void setVelocityX(float f) {
            this.velocityX = f;
        }

        public final float getVelocityY() {
            return this.velocityY;
        }

        public final void setVelocityY(float f) {
            this.velocityY = f;
        }

        public final float getVelocityZ() {
            return this.velocityZ;
        }

        public final void setVelocityZ(float f) {
            this.velocityZ = f;
        }

        public final float getRightX() {
            return this.rightX;
        }

        public final float getRightZ() {
            return this.rightZ;
        }

        public final float getSpinRate() {
            return this.spinRate;
        }

        public final void setSpinRate(float f) {
            this.spinRate = f;
        }

        public final float getHalf() {
            return this.half;
        }

        public final double getGround() {
            return this.ground;
        }

        public final float getScale() {
            return this.scale;
        }

        public final float getDelay() {
            return this.delay;
        }

        public final void setDelay(float f) {
            this.delay = f;
        }

        public final float getDelayFull() {
            return this.delayFull;
        }

        public final float getSpin() {
            return this.spin;
        }

        public final void setSpin(float f) {
            this.spin = f;
        }

        public final float getSpinTarget() {
            return this.spinTarget;
        }

        public final void setSpinTarget(float f) {
            this.spinTarget = f;
        }

        public final float getAge() {
            return this.age;
        }

        public final void setAge(float f) {
            this.age = f;
        }

        public final float getRest() {
            return this.rest;
        }

        public final void setRest(float f) {
            this.rest = f;
        }

        public final boolean getGrounded() {
            return this.grounded;
        }

        public final void setGrounded(boolean bl) {
            this.grounded = bl;
        }

        public final double restHeight() {
            return (double)this.half * Math.abs(Math.cos(this.spin)) + 0.012;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\b\u0082\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0011J>\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007H\u00c6\u0001\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u001b\u0010\u0017\u001a\u00020\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0011\u0010\u001a\u001a\u00020\u0019H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0011\u0010\u001c\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\rR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001d\u001a\u0004\b\u001e\u0010\rR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b \u0010\u000fR\u0017\u0010\b\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\b\u0010!\u001a\u0004\b\"\u0010\u0011R\u0017\u0010\t\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\t\u0010!\u001a\u0004\b#\u0010\u0011\u00a8\u0006$"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Fragment;", "", "", "text", "", "Lrtx/kimiko/utils/media/LyricWord;", "words", "", "startMillis", "endMillis", "<init>", "(Ljava/lang/String;Ljava/util/List;JJ)V", "component1", "()Ljava/lang/String;", "component2", "()Ljava/util/List;", "component3", "()J", "component4", "copy", "(Ljava/lang/String;Ljava/util/List;JJ)Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Fragment;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Ljava/lang/String;", "getText", "Ljava/util/List;", "getWords", "J", "getStartMillis", "getEndMillis", "rtx.kimiko:kimiko"})
    private static final class Fragment {
        @NotNull
        private final String text;
        @NotNull
        private final List<LyricWord> words;
        private final long startMillis;
        private final long endMillis;

        public Fragment(@NotNull String text, @NotNull List<LyricWord> words, long startMillis, long endMillis) {
            Intrinsics.checkNotNullParameter((Object)text, (String)"text");
            Intrinsics.checkNotNullParameter(words, (String)"words");
            this.text = text;
            this.words = words;
            this.startMillis = startMillis;
            this.endMillis = endMillis;
        }

        @NotNull
        public final String getText() {
            return this.text;
        }

        @NotNull
        public final List<LyricWord> getWords() {
            return this.words;
        }

        public final long getStartMillis() {
            return this.startMillis;
        }

        public final long getEndMillis() {
            return this.endMillis;
        }

        @NotNull
        public final String component1() {
            return this.text;
        }

        @NotNull
        public final List<LyricWord> component2() {
            return this.words;
        }

        public final long component3() {
            return this.startMillis;
        }

        public final long component4() {
            return this.endMillis;
        }

        @NotNull
        public final Fragment copy(@NotNull String text, @NotNull List<LyricWord> words, long startMillis, long endMillis) {
            Intrinsics.checkNotNullParameter((Object)text, (String)"text");
            Intrinsics.checkNotNullParameter(words, (String)"words");
            return new Fragment(text, words, startMillis, endMillis);
        }

        public static /* synthetic */ Fragment copy$default(Fragment fragment, String string, List list, long l, long l2, int n, Object object) {
            if ((n & 1) != 0) {
                string = fragment.text;
            }
            if ((n & 2) != 0) {
                list = fragment.words;
            }
            if ((n & 4) != 0) {
                l = fragment.startMillis;
            }
            if ((n & 8) != 0) {
                l2 = fragment.endMillis;
            }
            return fragment.copy(string, list, l, l2);
        }

        @NotNull
        public String toString() {
            return "Fragment(text=" + this.text + ", words=" + this.words + ", startMillis=" + this.startMillis + ", endMillis=" + this.endMillis + ")";
        }

        public int hashCode() {
            int result = this.text.hashCode();
            result = result * 31 + ((Object)this.words).hashCode();
            result = result * 31 + Long.hashCode(this.startMillis);
            result = result * 31 + Long.hashCode(this.endMillis);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Fragment)) {
                return false;
            }
            Fragment fragment = (Fragment)other;
            if (!Intrinsics.areEqual((Object)this.text, (Object)fragment.text)) {
                return false;
            }
            if (!Intrinsics.areEqual(this.words, fragment.words)) {
                return false;
            }
            if (this.startMillis != fragment.startMillis) {
                return false;
            }
            return this.endMillis == fragment.endMillis;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b\t\b\u0002\u0018\u0000 H2\u00020\u0001:\u0001HBI\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\n\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u0019\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u001d\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0010\u00a2\u0006\u0004\b\u001e\u0010\u001fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010 \u001a\u0004\b!\u0010\"R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010#\u001a\u0004\b$\u0010%R\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010&\u001a\u0004\b'\u0010(R\u0017\u0010\b\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\b\u0010&\u001a\u0004\b)\u0010(R\u0017\u0010\t\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\t\u0010&\u001a\u0004\b*\u0010(R\u0017\u0010\u000b\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010+\u001a\u0004\b,\u0010-R\u0017\u0010\f\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\f\u0010+\u001a\u0004\b.\u0010-R\u0017\u0010\r\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\r\u0010+\u001a\u0004\b/\u0010-R\u0017\u00100\u001a\u00020\u00108\u0006\u00a2\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u0010\u0012R\"\u00103\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b3\u00101\u001a\u0004\b4\u0010\u0012\"\u0004\b5\u0010\u0018R\"\u00106\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b6\u00101\u001a\u0004\b7\u0010\u0012\"\u0004\b8\u0010\u0018R\"\u0010:\u001a\u0002098\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u0018\u0010A\u001a\u0004\u0018\u00010@8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bA\u0010BR\u0018\u0010C\u001a\u0004\u0018\u00010@8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bC\u0010BR\u0018\u0010D\u001a\u0004\u0018\u00010@8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bD\u0010BR\u0018\u0010E\u001a\u0004\u0018\u00010@8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bE\u0010BR\u0018\u0010F\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bF\u0010G\u00a8\u0006I"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Held;", "", "Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Fragment;", "fragment", "Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Timing;", "timing", "", "x", "y", "z", "", "rightX", "rightZ", "reach", "<init>", "(Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Fragment;Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Timing;DDDFFF)V", "", "shatterAt", "()J", "", "invalidate", "()V", "time", "retire", "(J)V", "Lrtx/kimiko/utils/render/fonts/Fonts;", "fontName", "measure", "(Lrtx/kimiko/utils/render/fonts/Fonts;)V", "center", "heat", "(FJ)F", "Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Fragment;", "getFragment", "()Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Fragment;", "Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Timing;", "getTiming", "()Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Timing;", "D", "getX", "()D", "getY", "getZ", "F", "getRightX", "()F", "getRightZ", "getReach", "spawn", "J", "getSpawn", "fadeFrom", "getFadeFrom", "setFadeFrom", "death", "getDeath", "setDeath", "", "shattered", "Z", "getShattered", "()Z", "setShattered", "(Z)V", "", "wordStart", "[F", "wordEnd", "wordRise", "wordFall", "measured", "Lrtx/kimiko/utils/render/fonts/Fonts;", "Companion", "rtx.kimiko:kimiko"})
    private static final class Held {
        @NotNull
        public static final Companion Companion = new Companion(null);
        @NotNull
        private final Fragment fragment;
        @NotNull
        private final Timing timing;
        private final double x;
        private final double y;
        private final double z;
        private final float rightX;
        private final float rightZ;
        private final float reach;
        private final long spawn;
        private long fadeFrom;
        private long death;
        private boolean shattered;
        @Nullable
        private float[] wordStart;
        @Nullable
        private float[] wordEnd;
        @Nullable
        private float[] wordRise;
        @Nullable
        private float[] wordFall;
        @Nullable
        private Fonts measured;

        public Held(@NotNull Fragment fragment, @NotNull Timing timing, double x, double y, double z, float rightX, float rightZ, float reach) {
            Intrinsics.checkNotNullParameter((Object)fragment, (String)"fragment");
            Intrinsics.checkNotNullParameter((Object)timing, (String)"timing");
            this.fragment = fragment;
            this.timing = timing;
            this.x = x;
            this.y = y;
            this.z = z;
            this.rightX = rightX;
            this.rightZ = rightZ;
            this.reach = reach;
            this.spawn = this.timing.getSpawn();
            this.fadeFrom = this.timing.getVisibleEnd();
            this.death = this.timing.getDeath();
        }

        public /* synthetic */ Held(Fragment fragment, Timing timing, double d, double d2, double d3, float f, float f2, float f3, int n, DefaultConstructorMarker defaultConstructorMarker) {
            this(fragment, timing, d, d2, d3, f, f2, ((n & 0x80) != 0 ? 1.0f : f3));
        }

        @NotNull
        public final Fragment getFragment() {
            return this.fragment;
        }

        @NotNull
        public final Timing getTiming() {
            return this.timing;
        }

        public final double getX() {
            return this.x;
        }

        public final double getY() {
            return this.y;
        }

        public final double getZ() {
            return this.z;
        }

        public final float getRightX() {
            return this.rightX;
        }

        public final float getRightZ() {
            return this.rightZ;
        }

        public final float getReach() {
            return this.reach;
        }

        public final long getSpawn() {
            return this.spawn;
        }

        public final long getFadeFrom() {
            return this.fadeFrom;
        }

        public final void setFadeFrom(long l) {
            this.fadeFrom = l;
        }

        public final long getDeath() {
            return this.death;
        }

        public final void setDeath(long l) {
            this.death = l;
        }

        public final boolean getShattered() {
            return this.shattered;
        }

        public final void setShattered(boolean bl) {
            this.shattered = bl;
        }

        public final long shatterAt() {
            long sung = this.fragment.getEndMillis() + 420L;
            return Math.max(this.fadeFrom, Math.min(sung, this.fadeFrom + 1600L));
        }

        public final void invalidate() {
            this.measured = null;
        }

        public final void retire(long time) {
            long from = Math.max(this.spawn, time);
            if (from >= this.fadeFrom) {
                return;
            }
            this.fadeFrom = from;
            this.death = from + (long)Math.round(this.timing.getOutTime() + this.timing.getOutCascade());
        }

        public final void measure(@NotNull Fonts fontName) {
            Intrinsics.checkNotNullParameter((Object)((Object)fontName), (String)"fontName");
            if (fontName == this.measured) {
                return;
            }
            this.measured = fontName;
            List<LyricWord> words = this.fragment.getWords();
            int count = words.size();
            float[] startArr = new float[count];
            float[] endArr = new float[count];
            float[] riseArr = new float[count];
            float[] fallArr = new float[count];
            String text = this.fragment.getText();
            for (int index = 0; index < count; ++index) {
                LyricWord word = words.get(index);
                String string = text.substring(0, word.begin());
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
                startArr[index] = MsdfTextGeometry.width(fontName, string, 32.0f);
                String string2 = text.substring(0, word.end());
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
                endArr[index] = MsdfTextGeometry.width(fontName, string2, 32.0f);
                float span = Math.max(1.0f, (float)word.durationMillis());
                riseArr[index] = MathHelper.clamp(span * 0.35f, 90.0f, 220.0f);
                fallArr[index] = MathHelper.clamp(span * 0.55f, 140.0f, 320.0f);
            }
            this.wordStart = startArr;
            this.wordEnd = endArr;
            this.wordRise = riseArr;
            this.wordFall = fallArr;
        }

        public final float heat(float center, long time) {
            List<LyricWord> words = this.fragment.getWords();
            if (this.wordStart == null) {
                return 0.0f;
            }
            float[] starts = this.wordStart;
            if (this.wordEnd == null) {
                return 0.0f;
            }
            float[] ends = this.wordEnd;
            if (this.wordRise == null) {
                return 0.0f;
            }
            float[] rises = this.wordRise;
            if (this.wordFall == null) {
                return 0.0f;
            }
            float[] falls = this.wordFall;
            int n = ((Collection)words).size();
            for (int index = 0; index < n; ++index) {
                if (center < starts[index] || center > ends[index]) continue;
                LyricWord word = words.get(index);
                float up = MathHelper.clamp(((float)time - ((float)word.startMillis() - 60.0f)) / rises[index], 0.0f, 1.0f);
                float down = MathHelper.clamp((float)(time - word.endMillis()) / falls[index], 0.0f, 1.0f);
                return Held.Companion.ease(up) * (1.0f - Held.Companion.ease(down));
            }
            return 0.0f;
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Held.Companion;", "", "<init>", "()V", "", "value", "ease", "(F)F", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            private final float ease(float value) {
                return value * value * (3.0f - 2.0f * value);
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b0\b\u0086\b\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\b\u0012\u0006\u0010\r\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\b\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0013\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u001e\u0010\u001dJ\u0010\u0010\u001f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001f\u0010\u0017J\u0010\u0010 \u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b \u0010\u001dJ\u0010\u0010!\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b!\u0010\u001dJ\u0010\u0010\"\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\"\u0010\u001dJ\u0010\u0010#\u001a\u00020\u000fH\u00c6\u0003\u00a2\u0006\u0004\b#\u0010$J\u0010\u0010%\u001a\u00020\u0011H\u00c6\u0003\u00a2\u0006\u0004\b%\u0010&J\u0010\u0010'\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b'\u0010\u0017J\u0088\u0001\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b(\u0010)J\u001b\u0010+\u001a\u00020\u00022\b\u0010*\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b+\u0010,J\u0011\u0010-\u001a\u00020\u000fH\u00d6\u0081\u0004\u00a2\u0006\u0004\b-\u0010$J\u0011\u0010.\u001a\u00020\u0004H\u00d6\u0081\u0004\u00a2\u0006\u0004\b.\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010/\u001a\u0004\b0\u0010\u0017R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u00101\u001a\u0004\b2\u0010\u0019R\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u00103\u001a\u0004\b4\u0010\u001bR\u0017\u0010\t\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u00105\u001a\u0004\b6\u0010\u001dR\u0017\u0010\n\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\n\u00105\u001a\u0004\b7\u0010\u001dR\u0017\u0010\u000b\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010/\u001a\u0004\b8\u0010\u0017R\u0017\u0010\f\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\f\u00105\u001a\u0004\b9\u0010\u001dR\u0017\u0010\r\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\r\u00105\u001a\u0004\b:\u0010\u001dR\u0017\u0010\u000e\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\u000e\u00105\u001a\u0004\b;\u0010\u001dR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006\u00a2\u0006\f\n\u0004\b\u0010\u0010<\u001a\u0004\b=\u0010$R\u0017\u0010\u0012\u001a\u00020\u00118\u0006\u00a2\u0006\f\n\u0004\b\u0012\u0010>\u001a\u0004\b?\u0010&R\u0017\u0010\u0013\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0013\u0010/\u001a\u0004\b@\u0010\u0017\u00a8\u0006A"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Options;", "", "", "words", "", "layout", "Lrtx/kimiko/utils/render/fonts/Fonts;", "fontName", "", "size", "opacity", "throughWalls", "glow", "radius", "height", "", "limit", "", "syncMillis", "fall", "<init>", "(ZLjava/lang/String;Lrtx/kimiko/utils/render/fonts/Fonts;FFZFFFIJZ)V", "component1", "()Z", "component2", "()Ljava/lang/String;", "component3", "()Lrtx/kimiko/utils/render/fonts/Fonts;", "component4", "()F", "component5", "component6", "component7", "component8", "component9", "component10", "()I", "component11", "()J", "component12", "copy", "(ZLjava/lang/String;Lrtx/kimiko/utils/render/fonts/Fonts;FFZFFFIJZ)Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Options;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "Z", "getWords", "Ljava/lang/String;", "getLayout", "Lrtx/kimiko/utils/render/fonts/Fonts;", "getFontName", "F", "getSize", "getOpacity", "getThroughWalls", "getGlow", "getRadius", "getHeight", "I", "getLimit", "J", "getSyncMillis", "getFall", "rtx.kimiko:kimiko"})
    public static final class Options {
        private final boolean words;
        @NotNull
        private final String layout;
        @NotNull
        private final Fonts fontName;
        private final float size;
        private final float opacity;
        private final boolean throughWalls;
        private final float glow;
        private final float radius;
        private final float height;
        private final int limit;
        private final long syncMillis;
        private final boolean fall;

        public Options(boolean words, @NotNull String layout, @NotNull Fonts fontName, float size, float opacity, boolean throughWalls, float glow, float radius, float height, int limit, long syncMillis, boolean fall) {
            Intrinsics.checkNotNullParameter((Object)layout, (String)"layout");
            Intrinsics.checkNotNullParameter((Object)((Object)fontName), (String)"fontName");
            this.words = words;
            this.layout = layout;
            this.fontName = fontName;
            this.size = size;
            this.opacity = opacity;
            this.throughWalls = throughWalls;
            this.glow = glow;
            this.radius = radius;
            this.height = height;
            this.limit = limit;
            this.syncMillis = syncMillis;
            this.fall = fall;
        }

        public final boolean getWords() {
            return this.words;
        }

        @NotNull
        public final String getLayout() {
            return this.layout;
        }

        @NotNull
        public final Fonts getFontName() {
            return this.fontName;
        }

        public final float getSize() {
            return this.size;
        }

        public final float getOpacity() {
            return this.opacity;
        }

        public final boolean getThroughWalls() {
            return this.throughWalls;
        }

        public final float getGlow() {
            return this.glow;
        }

        public final float getRadius() {
            return this.radius;
        }

        public final float getHeight() {
            return this.height;
        }

        public final int getLimit() {
            return this.limit;
        }

        public final long getSyncMillis() {
            return this.syncMillis;
        }

        public final boolean getFall() {
            return this.fall;
        }

        public final boolean component1() {
            return this.words;
        }

        @NotNull
        public final String component2() {
            return this.layout;
        }

        @NotNull
        public final Fonts component3() {
            return this.fontName;
        }

        public final float component4() {
            return this.size;
        }

        public final float component5() {
            return this.opacity;
        }

        public final boolean component6() {
            return this.throughWalls;
        }

        public final float component7() {
            return this.glow;
        }

        public final float component8() {
            return this.radius;
        }

        public final float component9() {
            return this.height;
        }

        public final int component10() {
            return this.limit;
        }

        public final long component11() {
            return this.syncMillis;
        }

        public final boolean component12() {
            return this.fall;
        }

        @NotNull
        public final Options copy(boolean words, @NotNull String layout, @NotNull Fonts fontName, float size, float opacity, boolean throughWalls, float glow, float radius, float height, int limit, long syncMillis, boolean fall) {
            Intrinsics.checkNotNullParameter((Object)layout, (String)"layout");
            Intrinsics.checkNotNullParameter((Object)((Object)fontName), (String)"fontName");
            return new Options(words, layout, fontName, size, opacity, throughWalls, glow, radius, height, limit, syncMillis, fall);
        }

        public static /* synthetic */ Options copy$default(Options options, boolean bl, String string, Fonts fonts, float f, float f2, boolean bl2, float f3, float f4, float f5, int n, long l, boolean bl3, int n2, Object object) {
            if ((n2 & 1) != 0) {
                bl = options.words;
            }
            if ((n2 & 2) != 0) {
                string = options.layout;
            }
            if ((n2 & 4) != 0) {
                fonts = options.fontName;
            }
            if ((n2 & 8) != 0) {
                f = options.size;
            }
            if ((n2 & 0x10) != 0) {
                f2 = options.opacity;
            }
            if ((n2 & 0x20) != 0) {
                bl2 = options.throughWalls;
            }
            if ((n2 & 0x40) != 0) {
                f3 = options.glow;
            }
            if ((n2 & 0x80) != 0) {
                f4 = options.radius;
            }
            if ((n2 & 0x100) != 0) {
                f5 = options.height;
            }
            if ((n2 & 0x200) != 0) {
                n = options.limit;
            }
            if ((n2 & 0x400) != 0) {
                l = options.syncMillis;
            }
            if ((n2 & 0x800) != 0) {
                bl3 = options.fall;
            }
            return options.copy(bl, string, fonts, f, f2, bl2, f3, f4, f5, n, l, bl3);
        }

        @NotNull
        public String toString() {
            return "Options(words=" + this.words + ", layout=" + this.layout + ", fontName=" + this.fontName + ", size=" + this.size + ", opacity=" + this.opacity + ", throughWalls=" + this.throughWalls + ", glow=" + this.glow + ", radius=" + this.radius + ", height=" + this.height + ", limit=" + this.limit + ", syncMillis=" + this.syncMillis + ", fall=" + this.fall + ")";
        }

        public int hashCode() {
            int result = Boolean.hashCode(this.words);
            result = result * 31 + this.layout.hashCode();
            result = result * 31 + this.fontName.hashCode();
            result = result * 31 + Float.hashCode(this.size);
            result = result * 31 + Float.hashCode(this.opacity);
            result = result * 31 + Boolean.hashCode(this.throughWalls);
            result = result * 31 + Float.hashCode(this.glow);
            result = result * 31 + Float.hashCode(this.radius);
            result = result * 31 + Float.hashCode(this.height);
            result = result * 31 + Integer.hashCode(this.limit);
            result = result * 31 + Long.hashCode(this.syncMillis);
            result = result * 31 + Boolean.hashCode(this.fall);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Options)) {
                return false;
            }
            Options options = (Options)other;
            if (this.words != options.words) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.layout, (Object)options.layout)) {
                return false;
            }
            if (this.fontName != options.fontName) {
                return false;
            }
            if (Float.compare(this.size, options.size) != 0) {
                return false;
            }
            if (Float.compare(this.opacity, options.opacity) != 0) {
                return false;
            }
            if (this.throughWalls != options.throughWalls) {
                return false;
            }
            if (Float.compare(this.glow, options.glow) != 0) {
                return false;
            }
            if (Float.compare(this.radius, options.radius) != 0) {
                return false;
            }
            if (Float.compare(this.height, options.height) != 0) {
                return false;
            }
            if (this.limit != options.limit) {
                return false;
            }
            if (this.syncMillis != options.syncMillis) {
                return false;
            }
            return this.fall == options.fall;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\b\u0082\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u000eJ\u0010\u0010\u0011\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0012J\u0010\u0010\u0015\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0012JV\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u0006H\u00c6\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u001a\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0011\u0010\u001d\u001a\u00020\u001cH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0011\u0010 \u001a\u00020\u001fH\u00d6\u0081\u0004\u00a2\u0006\u0004\b \u0010!R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\"\u001a\u0004\b#\u0010\u000eR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\"\u001a\u0004\b$\u0010\u000eR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\"\u001a\u0004\b%\u0010\u000eR\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010&\u001a\u0004\b'\u0010\u0012R\u0017\u0010\b\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\b\u0010&\u001a\u0004\b(\u0010\u0012R\u0017\u0010\t\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\t\u0010&\u001a\u0004\b)\u0010\u0012R\u0017\u0010\n\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\n\u0010&\u001a\u0004\b*\u0010\u0012\u00a8\u0006+"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Timing;", "", "", "spawn", "visibleEnd", "death", "", "inTime", "inCascade", "outTime", "outCascade", "<init>", "(JJJFFFF)V", "component1", "()J", "component2", "component3", "component4", "()F", "component5", "component6", "component7", "copy", "(JJJFFFF)Lrtx/kimiko/api/modules/impl/Visuals/particles/lyrics/LyricParticles$Timing;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "J", "getSpawn", "getVisibleEnd", "getDeath", "F", "getInTime", "getInCascade", "getOutTime", "getOutCascade", "rtx.kimiko:kimiko"})
    private static final class Timing {
        private final long spawn;
        private final long visibleEnd;
        private final long death;
        private final float inTime;
        private final float inCascade;
        private final float outTime;
        private final float outCascade;

        public Timing(long spawn, long visibleEnd, long death, float inTime, float inCascade, float outTime, float outCascade) {
            this.spawn = spawn;
            this.visibleEnd = visibleEnd;
            this.death = death;
            this.inTime = inTime;
            this.inCascade = inCascade;
            this.outTime = outTime;
            this.outCascade = outCascade;
        }

        public final long getSpawn() {
            return this.spawn;
        }

        public final long getVisibleEnd() {
            return this.visibleEnd;
        }

        public final long getDeath() {
            return this.death;
        }

        public final float getInTime() {
            return this.inTime;
        }

        public final float getInCascade() {
            return this.inCascade;
        }

        public final float getOutTime() {
            return this.outTime;
        }

        public final float getOutCascade() {
            return this.outCascade;
        }

        public final long component1() {
            return this.spawn;
        }

        public final long component2() {
            return this.visibleEnd;
        }

        public final long component3() {
            return this.death;
        }

        public final float component4() {
            return this.inTime;
        }

        public final float component5() {
            return this.inCascade;
        }

        public final float component6() {
            return this.outTime;
        }

        public final float component7() {
            return this.outCascade;
        }

        @NotNull
        public final Timing copy(long spawn, long visibleEnd, long death, float inTime, float inCascade, float outTime, float outCascade) {
            return new Timing(spawn, visibleEnd, death, inTime, inCascade, outTime, outCascade);
        }

        public static /* synthetic */ Timing copy$default(Timing timing, long l, long l2, long l3, float f, float f2, float f3, float f4, int n, Object object) {
            if ((n & 1) != 0) {
                l = timing.spawn;
            }
            if ((n & 2) != 0) {
                l2 = timing.visibleEnd;
            }
            if ((n & 4) != 0) {
                l3 = timing.death;
            }
            if ((n & 8) != 0) {
                f = timing.inTime;
            }
            if ((n & 0x10) != 0) {
                f2 = timing.inCascade;
            }
            if ((n & 0x20) != 0) {
                f3 = timing.outTime;
            }
            if ((n & 0x40) != 0) {
                f4 = timing.outCascade;
            }
            return timing.copy(l, l2, l3, f, f2, f3, f4);
        }

        @NotNull
        public String toString() {
            return "Timing(spawn=" + this.spawn + ", visibleEnd=" + this.visibleEnd + ", death=" + this.death + ", inTime=" + this.inTime + ", inCascade=" + this.inCascade + ", outTime=" + this.outTime + ", outCascade=" + this.outCascade + ")";
        }

        public int hashCode() {
            int result = Long.hashCode(this.spawn);
            result = result * 31 + Long.hashCode(this.visibleEnd);
            result = result * 31 + Long.hashCode(this.death);
            result = result * 31 + Float.hashCode(this.inTime);
            result = result * 31 + Float.hashCode(this.inCascade);
            result = result * 31 + Float.hashCode(this.outTime);
            result = result * 31 + Float.hashCode(this.outCascade);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Timing)) {
                return false;
            }
            Timing timing = (Timing)other;
            if (this.spawn != timing.spawn) {
                return false;
            }
            if (this.visibleEnd != timing.visibleEnd) {
                return false;
            }
            if (this.death != timing.death) {
                return false;
            }
            if (Float.compare(this.inTime, timing.inTime) != 0) {
                return false;
            }
            if (Float.compare(this.inCascade, timing.inCascade) != 0) {
                return false;
            }
            if (Float.compare(this.outTime, timing.outTime) != 0) {
                return false;
            }
            return Float.compare(this.outCascade, timing.outCascade) == 0;
        }
    }
}

