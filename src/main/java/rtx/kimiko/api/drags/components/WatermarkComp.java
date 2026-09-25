/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.ranges.RangesKt
 *  kotlin.text.StringsKt
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.session.Session
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Matrix3x2f
 */
package rtx.kimiko.api.drags.components;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.session.Session;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import rtx.kimiko.api.drags.Draggable;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.WatermarkModule;
import rtx.kimiko.api.modules.settings.impl.SelectSetting;
import rtx.kimiko.api.ui.settings.Setting;
import rtx.kimiko.api.ui.settings.impl.BoolSetting;
import rtx.kimiko.utils.animations.HudFadeAnimation;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.media.MediaPlayer;
import rtx.kimiko.utils.media.MediaTrack;
import rtx.kimiko.utils.profile.ProfileIdentity;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.render2d.blur.BlurCapture;
import rtx.kimiko.utils.render.render2d.blur.BlurFramebuffer;
import rtx.kimiko.utils.render.render2d.blur.BuiltBlur;
import rtx.kimiko.utils.render.util.scissor.ScissorUtil;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u001a\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u0083\u00012\u00020\u0001:\u0002\u0083\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0007\u0010\u0006J\u000f\u0010\t\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0014\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0012H\u0014\u00a2\u0006\u0004\b\u0015\u0010\u0016J'\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ/\u0010 \u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b \u0010!J}\u00100\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\"2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\b0$2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u00042\u0006\u0010.\u001a\u00020-2\u0006\u0010/\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b0\u00101J\u0017\u00103\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b3\u00104J'\u00106\u001a\u00020\u00042\u0006\u0010.\u001a\u00020-2\u0006\u0010/\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b6\u00107J\u001f\u00109\u001a\u00020-2\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u00108\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b9\u0010:J?\u0010=\u001a\u00020\u00142\u0006\u0010;\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010<\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b=\u0010>J7\u0010?\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010<\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b?\u0010@JQ\u0010C\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010<\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00042\b\u0010A\u001a\u0004\u0018\u00010\b2\u0006\u0010B\u001a\u00020&2\u0006\u0010;\u001a\u00020\bH\u0002\u00a2\u0006\u0004\bC\u0010DJ9\u0010F\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\b\u0010A\u001a\u0004\u0018\u00010\b2\u0006\u0010B\u001a\u00020&2\u0006\u0010E\u001a\u00020-H\u0002\u00a2\u0006\u0004\bF\u0010GJ\u0017\u0010J\u001a\u00020\u000f2\u0006\u0010I\u001a\u00020HH\u0002\u00a2\u0006\u0004\bJ\u0010KJ\u000f\u0010L\u001a\u00020-H\u0002\u00a2\u0006\u0004\bL\u0010MJ\u0019\u0010O\u001a\u00020\b2\b\b\u0002\u0010N\u001a\u00020-H\u0002\u00a2\u0006\u0004\bO\u0010PJ\u0017\u0010R\u001a\u00020\u00142\u0006\u0010Q\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bR\u0010SJ\u000f\u0010T\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\bT\u0010\u0003J\u000f\u0010U\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bU\u0010\u0011J\u000f\u0010V\u001a\u00020&H\u0002\u00a2\u0006\u0004\bV\u0010WJ?\u0010X\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\"2\u0006\u0010;\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00042\u0006\u0010E\u001a\u00020-H\u0002\u00a2\u0006\u0004\bX\u0010YJ\u000f\u0010Z\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\bZ\u0010\u0003J\u000f\u0010[\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b[\u0010\u0011R\u0014\u0010]\u001a\u00020\\8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b]\u0010^R\u0016\u0010_\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b_\u0010`R\u0016\u0010a\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\ba\u0010bR\u0016\u0010c\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010`R\u0016\u0010d\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bd\u0010`R\u0014\u0010e\u001a\u00020&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\be\u0010fR\u0014\u0010g\u001a\u00020&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bg\u0010fR\u0016\u0010h\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bh\u0010`R\u0016\u0010i\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bi\u0010jR\u0016\u0010k\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bk\u0010`R\u0016\u0010l\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bl\u0010jR\u0016\u0010m\u001a\u00020H8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bm\u0010nR\u0016\u0010o\u001a\u00020H8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bo\u0010nR\u0016\u0010p\u001a\u00020-8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bp\u0010qR\u0016\u0010r\u001a\u00020-8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\br\u0010qR\u0016\u0010s\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bs\u0010bR\u0016\u0010t\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bt\u0010bR\u0016\u0010u\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bu\u0010`R\u0016\u0010v\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bv\u0010`R\u0016\u0010w\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bw\u0010`R\u0016\u0010x\u001a\u00020H8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bx\u0010nR\u0016\u0010y\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\by\u0010bR\u0014\u0010{\u001a\u00020z8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b{\u0010|R%\u0010\u007f\u001a\u0012\u0012\u0004\u0012\u00020-0}j\b\u0012\u0004\u0012\u00020-`~8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u007f\u0010\u0080\u0001R\u0018\u0010\u0081\u0001\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0081\u0001\u0010bR\u0018\u0010\u0082\u0001\u001a\u00020H8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0082\u0001\u0010n\u00a8\u0006\u0084\u0001"}, d2={"Lrtx/kimiko/api/drags/components/WatermarkComp;", "Lrtx/kimiko/api/drags/Draggable;", "<init>", "()V", "", "width", "()F", "height", "", "displayName", "()Ljava/lang/String;", "", "Lrtx/kimiko/api/ui/settings/Setting;", "buildHudSettings", "()Ljava/util/List;", "", "isInteractive", "()Z", "Lnet/minecraft/DrawContext;", "graphics", "", "render", "(Lnet/minecraft/DrawContext;)V", "anchorY", "scaleY", "scaleVerticallyFrom", "(Lnet/minecraft/DrawContext;FF)V", "Lorg/joml/Matrix3x2f;", "slotPose", "x", "y", "alpha", "drawLoaderBrand", "(Lorg/joml/Matrix3x2f;FFF)V", "Lrtx/kimiko/utils/render/fonts/Fonts;", "font", "", "glyphs", "", "baseWidths", "baseSize", "size", "elementLeft", "spanOffset", "elementWidth", "", "slice", "backgroundPosition", "drawLoaderSpan", "(Lrtx/kimiko/utils/render/fonts/Fonts;[Ljava/lang/String;[FFFFFFFFIFF)V", "t", "loaderEase", "(F)F", "localX", "loaderFactor", "(IFF)F", "factor", "adaptiveInk", "(FF)I", "text", "phase", "drawMarquee", "(Ljava/lang/String;FFFFF)V", "drawMediaFace", "(FFFFF)V", "texture", "uv", "drawIconMarquee", "(FFFFFLjava/lang/String;[FLjava/lang/String;)V", "color", "drawIcon", "(FFLjava/lang/String;[FI)V", "", "now", "advanceCycle", "(J)Z", "activePayload", "()I", "payload", "activePayloadText", "(I)Ljava/lang/String;", "active", "updateTransition", "(Z)V", "updateCache", "mediaReady", "coverUv", "()[F", "drawTrackedAdaptive", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;FFFI)V", "updateWidth", "shouldShow", "Lrtx/kimiko/utils/animations/HudFadeAnimation;", "visibility", "Lrtx/kimiko/utils/animations/HudFadeAnimation;", "boxWidth", "F", "metricsReady", "Z", "brandWidth", "brandIconWidth", "brandGlyphWidths", "[F", "brandIconGlyphWidths", "timeSlotWidth", "cachedName", "Ljava/lang/String;", "nameSlotWidth", "timeText", "timeSecond", "J", "cycleStartedAt", "cycleIndex", "I", "marqueePayload", "payloadChangePending", "lastActive", "faceFrom", "faceTarget", "faceProgress", "transitionStartedAt", "wasVisible", "Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "textBackdropCapture", "Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "payloads", "Ljava/util/ArrayList;", "mediaReadyCached", "mediaReadyAt", "Companion", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nWatermarkComp.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WatermarkComp.kt\nrtx/kimiko/api/drags/components/WatermarkComp\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,765:1\n1#2:766\n*E\n"})
public final class WatermarkComp
extends Draggable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final HudFadeAnimation visibility = this.getHudFade();
    private float boxWidth = 96.0f;
    private boolean metricsReady;
    private float brandWidth;
    private float brandIconWidth;
    @NotNull
    private final float[] brandGlyphWidths = new float[BRAND_GLYPHS.length];
    @NotNull
    private final float[] brandIconGlyphWidths = new float[BRAND_ICON_GLYPHS.length];
    private float timeSlotWidth;
    @NotNull
    private String cachedName = "";
    private float nameSlotWidth;
    @NotNull
    private String timeText = "0:00 AM";
    private long timeSecond = -1L;
    private long cycleStartedAt = System.currentTimeMillis();
    private int cycleIndex;
    private int marqueePayload;
    private boolean payloadChangePending;
    private boolean lastActive;
    private float faceFrom;
    private float faceTarget;
    private float faceProgress;
    private long transitionStartedAt = System.nanoTime();
    private boolean wasVisible;
    @NotNull
    private final BlurCapture textBackdropCapture = new BlurCapture();
    @NotNull
    private final ArrayList<Integer> payloads = new ArrayList(2);
    private boolean mediaReadyCached;
    private long mediaReadyAt;
    private static final long MEDIA_READY_CACHE_MS = 50L;
    private static final float H = 26.0f;
    private static final float PAD_X = 10.0f;
    private static final float MIN_GAP = 10.0f;
    private static final float GAP = 8.0f;
    private static final float SHADOW_OFFSET = 3.0f;
    private static final float FONT = 8.0f;
    private static final float LOADER_FONT = 16.0f;
    private static final float TRACK_EM = 0.1f;
    private static final float LOADER_ICON = 18.0f;
    private static final float DOT_RADIUS = 1.0f;
    private static final float DOT_Y_ADJUST = 0.5f;
    private static final float RADIUS = 7.0f;
    private static final float TEXT_Y_ADJUST = -0.5f;
    private static final float LOADER_WIDTH_EM = 7.3f;
    private static final float SCISSOR_EPSILON = 0.05f;
    private static final int LOADER_SLICES = 9;
    private static final int LOADER_CENTER_SLICE = 4;
    private static final long LOADER_PERIOD_MS = 2000L;
    private static final float LOADER_SCROLL = 1.0f;
    private static final float CSS_MARGIN_CENTER_FACTOR = 0.5f;
    private static final float LOADER_X1 = 0.1f;
    private static final float LOADER_Y1 = 0.6f;
    private static final float LOADER_X2 = 0.9f;
    private static final float LOADER_Y2 = 0.4f;
    @NotNull
    private static final float[] LOADER_SCALE;
    @NotNull
    private static final float[] LOADER_OPACITY;
    @NotNull
    private static final float[] LOADER_MARGIN_EM;
    @NotNull
    private static final float[] LOADER_GRAD_START;
    @NotNull
    private static final float[] LOADER_GRAD_END;
    private static final float PAYLOAD_BLUR_RADIUS = 18.0f;
    private static final float FACE_MS = 500.0f;
    private static final long MARQUEE_MS = 4000L;
    private static final long CALM_MS = 10000L;
    private static final long ACTIVE_MS = 6500L;
    private static final int PAYLOAD_USER = 0;
    private static final int PAYLOAD_TIME = 1;
    private static final int PAYLOAD_MEDIA = 2;
    private static final float COVER = 10.0f;
    private static final float COVER_GAP = 5.0f;
    private static final float COVER_Y_ADJUST = 0.5f;
    private static final float MEDIA_TEXT_Y_ADJUST = 0.0f;
    @NotNull
    private static final String MEDIA_NOTE_GLYPH = "N";
    @NotNull
    private static final String BRAND_ICON_GLYPH = "x";
    @NotNull
    private static final String[] BRAND_GLYPHS;
    @NotNull
    private static final String[] BRAND_ICON_GLYPHS;
    @NotNull
    private static final String FALLBACK_NAME = "Player";
    private static final float MIN_ALPHA = 0.003921569f;
    @NotNull
    private static final DateTimeFormatter TIME_FORMAT;

    public WatermarkComp() {
        super("watermark", 5.0f, 5.0f);
        this.visibility.set(0.0);
    }

    @Override
    public float width() {
        return this.boxWidth + 3.0f;
    }

    @Override
    public float height() {
        return 29.0f;
    }

    @Override
    @NotNull
    public String displayName() {
        return "Watermark";
    }

    @Override
    @NotNull
    protected List<Setting> buildHudSettings() {
        ArrayList<Setting> list = new ArrayList<Setting>((Collection)super.buildHudSettings());
        WatermarkModule module = ModuleManager.Companion.get().get(WatermarkModule.class);
        if (module != null) {
            list.add(new rtx.kimiko.api.ui.settings.impl.SelectSetting(module.displayMode));
            list.add(new BoolSetting(module.showMedia));
        }
        return list;
    }

    @Override
    public boolean isInteractive() {
        return this.shouldShow();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected void render(@NotNull DrawContext graphics) {
        float staticAlpha;
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        boolean targetVisible = this.shouldShow();
        long now = System.currentTimeMillis();
        if (targetVisible && !this.wasVisible) {
            this.cycleStartedAt = now;
            this.cycleIndex = 0;
            this.marqueePayload = 0;
            this.payloadChangePending = false;
            this.lastActive = false;
            this.faceFrom = 0.0f;
            this.faceTarget = 0.0f;
            this.faceProgress = 0.0f;
            this.transitionStartedAt = System.nanoTime();
        }
        this.wasVisible = targetVisible;
        this.visibility.updateTarget(targetVisible);
        float alpha = this.visibility.get();
        if (targetVisible && alpha <= 0.01f) {
            alpha = 0.01f;
        }
        if (alpha <= 0.01f && !targetVisible) {
            return;
        }
        this.updateCache();
        this.updateWidth();
        this.updateTransition(this.advanceCycle(now));
        float scale = 0.92f + alpha * 0.08f;
        float originX = this.getX() + this.width() * 0.5f;
        float originY = this.getY() + this.height() * 0.5f;
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(originX, originY);
        graphics.getMatrices().scale(scale);
        graphics.getMatrices().translate(-originX, -originY);
        Render2D.beginFrame(graphics);
        float btnX = this.getX() + 3.0f;
        float btnY = this.getY() + 3.0f;
        float rectRadius = RectUtil.clientWindowRadius(7.0f, this.boxWidth, 26.0f);
        RectUtil.drawClientRect(btnX, btnY, this.boxWidth, 26.0f, rectRadius, alpha);
        BlurFramebuffer.Companion.getInstance().requestBackdropSample(graphics, new BuiltBlur(btnX, btnY, this.boxWidth, 26.0f, rectRadius, 1.0f, 18.0f).withColor(-1), this.textBackdropCapture);
        Matrix3x2f slotPose = Render2DCoordinateSpace.pose(graphics);
        float activeAlpha = alpha * this.faceProgress;
        if (activeAlpha > 0.003921569f) {
            ScissorUtil.push(slotPose, btnX, btnY, this.boxWidth, 26.0f);
            graphics.getMatrices().pushMatrix();
            try {
                this.scaleVerticallyFrom(graphics, btnY + 26.0f, this.faceProgress);
                int payload = this.activePayload();
                float phase = (float)(System.currentTimeMillis() % 4000L) / 4000.0f;
                if (payload == 2) {
                    this.drawMediaFace(btnX, btnY, this.boxWidth, phase, activeAlpha);
                } else {
                    this.drawMarquee(this.activePayloadText(payload), btnX, btnY, this.boxWidth, phase, activeAlpha);
                }
            }
            finally {
                graphics.getMatrices().popMatrix();
                ScissorUtil.pop();
            }
        }
        if ((staticAlpha = alpha * (1.0f - this.faceProgress)) > 0.003921569f) {
            Render2D.flush();
            Render2D.beginFrame(graphics);
            ScissorUtil.push(slotPose, btnX, btnY, this.boxWidth, 26.0f);
            graphics.getMatrices().pushMatrix();
            try {
                this.scaleVerticallyFrom(graphics, btnY, 1.0f - this.faceProgress);
                this.drawLoaderBrand(slotPose, btnX, btnY, staticAlpha);
            }
            finally {
                graphics.getMatrices().popMatrix();
                ScissorUtil.pop();
            }
        }
        Render2D.flush();
        graphics.getMatrices().popMatrix();
    }

    private final void scaleVerticallyFrom(DrawContext graphics, float anchorY, float scaleY) {
        graphics.getMatrices().translate(0.0f, anchorY);
        graphics.getMatrices().scale(1.0f, RangesKt.coerceIn((float)scaleY, (float)0.0f, (float)1.0f));
        graphics.getMatrices().translate(0.0f, -anchorY);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void drawLoaderBrand(Matrix3x2f slotPose, float x, float y, float alpha) {
        float centerX = x + this.boxWidth * 0.5f;
        float centerY = y + 13.0f;
        float linearPhase = (float)(System.nanoTime() / 1000000L % 2000L) / (float)2000L;
        float phase = this.loaderEase(linearPhase);
        float backgroundPosition = -0.98f + 2.0f * phase;
        float loaderWidth = Math.min(116.8f, RangesKt.coerceAtLeast((float)(this.boxWidth - 20.0f), (float)1.0f));
        float loaderLeft = centerX - loaderWidth * 0.5f;
        ScissorUtil.push(slotPose, loaderLeft, y, loaderWidth, 26.0f);
        try {
            int previousClipRight = Integer.MIN_VALUE;
            for (int i = 0; i < 9; ++i) {
                int clipRightPx;
                float sliceAlpha = alpha * LOADER_OPACITY[i];
                float scale = LOADER_SCALE[i];
                float textSize = 16.0f * scale;
                float iconSize = 18.0f * scale;
                float scaledTextW = this.brandWidth * scale;
                float scaledIconW = this.brandIconWidth * scale;
                float scaledGroupW = Math.max(scaledTextW + 8.0f * scale + scaledIconW, 1.0f);
                float margin = LOADER_MARGIN_EM[i] * textSize * 0.5f;
                float elementLeft = centerX - scaledGroupW * 0.5f + margin;
                float spanOffset = (phase * 2.0f - 1.0f) * scaledGroupW * 1.0f;
                float rawClipLeft = elementLeft + scaledGroupW * (float)i / 9.0f;
                float rawClipRight = rawClipLeft + scaledGroupW / 9.0f;
                int clipLeftPx = i == 0 ? (int)Math.round((double)rawClipLeft) : previousClipRight;
                previousClipRight = clipRightPx = Math.max(clipLeftPx + 1, (int)Math.round((double)rawClipRight));
                float clipLeft = clipLeftPx;
                float clipWidth = clipRightPx - clipLeftPx;
                float textX = elementLeft + spanOffset;
                float iconX = textX + scaledTextW + 8.0f * scale;
                float textY = centerY - textSize * 0.5f + -0.5f * scale;
                float iconY = centerY - iconSize * 0.5f + 0.5f * scale;
                ScissorUtil.push(slotPose, clipLeft, y, clipWidth + 0.05f, 26.0f);
                try {
                    this.drawLoaderSpan(Fonts.SMALL_PIXEL, BRAND_GLYPHS, this.brandGlyphWidths, 16.0f, textX, textY, textSize, elementLeft, spanOffset, scaledGroupW, i, backgroundPosition, sliceAlpha);
                    this.drawLoaderSpan(Fonts.KIMIKO, BRAND_ICON_GLYPHS, this.brandIconGlyphWidths, 18.0f, iconX, iconY, iconSize, elementLeft, spanOffset, scaledGroupW, i, backgroundPosition, sliceAlpha);
                    continue;
                }
                finally {
                    ScissorUtil.pop();
                }
            }
        }
        finally {
            ScissorUtil.pop();
        }
    }

    private final void drawLoaderSpan(Fonts font, String[] glyphs, float[] baseWidths, float baseSize, float x, float y, float size, float elementLeft, float spanOffset, float elementWidth, int slice, float backgroundPosition, float alpha) {
        float cursor = x;
        float widthScale = size / baseSize;
        int n = glyphs.length;
        for (int index = 0; index < n; ++index) {
            String glyph = glyphs[index];
            float glyphWidth = baseWidths[index] * widthScale;
            float leftFactor = this.loaderFactor(slice, backgroundPosition, (cursor - spanOffset - elementLeft) / elementWidth);
            float rightFactor = this.loaderFactor(slice, backgroundPosition, (cursor + glyphWidth - spanOffset - elementLeft) / elementWidth);
            int left = this.adaptiveInk(alpha, leftFactor);
            int right = this.adaptiveInk(alpha, rightFactor);
            Render2D.msdfAdaptiveText(font, glyph, cursor, y, size, left, right, right, left, this.textBackdropCapture);
            cursor += glyphWidth;
        }
    }

    private final float loaderEase(float t) {
        if (t <= 0.0f) {
            return 0.0f;
        }
        if (t >= 1.0f) {
            return 1.0f;
        }
        float u = 0.0f;
        u = t;
        int n = 5;
        for (int i = 0; i < n; ++i) {
            int it = i;
            boolean bl = false;
            float x = WatermarkComp.Companion.bezier(u, 0.1f, 0.9f);
            float d = WatermarkComp.Companion.bezierDerivative(u, 0.1f, 0.9f);
            if (!(Math.abs(d) > 1.0E-5f)) continue;
            u = RangesKt.coerceIn((float)(u - (x - t) / d), (float)0.0f, (float)1.0f);
        }
        return WatermarkComp.Companion.bezier(u, 0.6f, 0.4f);
    }

    private final float loaderFactor(int slice, float backgroundPosition, float localX) {
        if (slice == 4) {
            return 1.0f;
        }
        float start = LOADER_GRAD_START[slice];
        float end = LOADER_GRAD_END[slice];
        float rawQ = 0.5f * (localX + backgroundPosition);
        float q = rawQ - (float)Math.floor(rawQ);
        float t = RangesKt.coerceIn((float)((q - start) / (end - start)), (float)0.0f, (float)1.0f);
        return slice < 4 ? 1.0f - t : t;
    }

    private final int adaptiveInk(float alpha, float factor) {
        int a = RangesKt.coerceIn((int)Math.round(RangesKt.coerceIn((float)alpha, (float)0.0f, (float)1.0f) * 255.0f), (int)0, (int)255);
        int c = RangesKt.coerceIn((int)Math.round(RangesKt.coerceIn((float)factor, (float)0.0f, (float)1.0f) * 255.0f), (int)0, (int)255);
        return a << 24 | c << 16 | c << 8 | c;
    }

    private final void drawMarquee(String text, float x, float y, float width, float phase, float alpha) {
        if (alpha <= 0.003921569f) {
            return;
        }
        int color = ColorEngine.multAlpha(-1, alpha);
        float textWidth = WatermarkComp.Companion.trackedWidth(text, 8.0f);
        float unit = textWidth + 8.0f + 2.0f + 8.0f;
        if (unit <= 0.0f) {
            return;
        }
        float textY = y + 9.0f + -0.5f;
        float iconY = y + 13.0f + 0.5f;
        float limit = x + width;
        for (float cursor = x - unit * phase; cursor < limit; cursor += unit) {
            this.drawTrackedAdaptive(Fonts.MEDIUM, text, cursor, textY, 8.0f, color);
            float iconX = cursor + textWidth + 8.0f;
            Render2D.circleAdaptive(iconX + 1.0f, iconY, 1.0f, color, this.textBackdropCapture);
        }
    }

    private final void drawMediaFace(float x, float y, float width, float phase, float alpha) {
        MediaTrack track = MediaPlayer.getTrack();
        if (track.isEmpty()) {
            this.drawMarquee(this.activePayloadText(0), x, y, width, phase, alpha);
            return;
        }
        Identifier identifier2 = MediaPlayer.getCover();
        this.drawIconMarquee(x, y, width, phase, alpha, identifier2 != null ? identifier2.toString() : null, this.coverUv(), track.display());
    }

    private final void drawIconMarquee(float x, float y, float width, float phase, float alpha, String texture, float[] uv, String text) {
        if (alpha <= 0.003921569f) {
            return;
        }
        int color = ColorEngine.multAlpha(-1, alpha);
        float textWidth = WatermarkComp.Companion.trackedWidth(text, 8.0f);
        float span = 15.0f + textWidth + 8.0f + 2.0f + 8.0f;
        if (span <= 0.0f) {
            return;
        }
        float unit = (float)Math.ceil(span);
        float textY = y + 9.0f + -0.5f + 0.0f;
        float centerY = y + 13.0f;
        float iconY = Math.round(centerY - 5.0f + 0.5f);
        float limit = x + width;
        for (float cursor = x - unit * phase; cursor < limit; cursor += unit) {
            float blockX = Math.round(cursor);
            this.drawIcon(blockX, iconY, texture, uv, color);
            float textX = blockX + 10.0f + 5.0f;
            this.drawTrackedAdaptive(Fonts.MEDIUM, text, textX, textY, 8.0f, color);
            float dotX = textX + textWidth + 8.0f;
            Render2D.circleAdaptive(dotX + 1.0f, centerY + 0.5f, 1.0f, color, this.textBackdropCapture);
        }
    }

    private final void drawIcon(float x, float y, String texture, float[] uv, int color) {
        if (texture != null) {
            Render2D.imageUv(texture, x, y, 10.0f, 10.0f, 5.0f, 0.0f, uv[0], uv[1], uv[2], uv[3], color);
            return;
        }
        float glyphWidth = Fonts.MEDIA_ICONS.msdfWidth(MEDIA_NOTE_GLYPH, 10.0f);
        Render2D.msdfAdaptiveText(Fonts.MEDIA_ICONS, MEDIA_NOTE_GLYPH, x + (10.0f - glyphWidth) * 0.5f, y, 10.0f, color, this.textBackdropCapture);
    }

    private final boolean advanceCycle(long now) {
        boolean active;
        long elapsed = now - this.cycleStartedAt;
        long total = 16500L;
        if (elapsed >= total) {
            long jumps = elapsed / total;
            this.cycleStartedAt += jumps * total;
            int payloadCount = RangesKt.coerceAtLeast((int)this.payloads.size(), (int)1);
            this.cycleIndex = ((this.cycleIndex + (int)jumps) % payloadCount + payloadCount) % payloadCount;
            this.payloadChangePending = true;
            elapsed = now - this.cycleStartedAt;
        }
        boolean bl = active = elapsed >= 10000L;
        if (active && this.payloadChangePending) {
            int n;
            if (this.payloads.isEmpty()) {
                n = 0;
            } else {
                Integer n2 = this.payloads.get(this.cycleIndex % this.payloads.size());
                Intrinsics.checkNotNull((Object)n2);
                n = ((Number)n2).intValue();
            }
            this.marqueePayload = n;
            this.payloadChangePending = false;
        }
        return active;
    }

    private final int activePayload() {
        return this.marqueePayload;
    }

    private final String activePayloadText(int payload) {
        if (this.payloads.isEmpty()) {
            CharSequence charSequence;
            CharSequence charSequence2 = this.cachedName;
            if (charSequence2.length() == 0) {
                boolean bl = false;
                charSequence = FALLBACK_NAME;
            } else {
                charSequence = charSequence2;
            }
            return "USER  " + charSequence;
        }
        return switch (payload) {
            case 0 -> {
                CharSequence name = this.cachedName.length() == 0 ? FALLBACK_NAME : this.cachedName;
                yield "USER  " + name;
            }
            case 1 -> "TIME  " + this.timeText;
            case 2 -> MediaPlayer.getTrack().display();
            default -> {
                CharSequence name = this.cachedName.length() == 0 ? FALLBACK_NAME : this.cachedName;
                yield "USER  " + name;
            }
        };
    }

    static /* synthetic */ String activePayloadText$default(WatermarkComp watermarkComp, int n, int n2, Object object) {
        if ((n2 & 1) != 0) {
            n = watermarkComp.activePayload();
        }
        return watermarkComp.activePayloadText(n);
    }

    private final void updateTransition(boolean active) {
        if (active != this.lastActive) {
            this.lastActive = active;
            this.faceFrom = this.faceProgress;
            this.faceTarget = active ? 1.0f : 0.0f;
            this.transitionStartedAt = System.nanoTime();
        }
        float elapsedMs = (float)(System.nanoTime() - this.transitionStartedAt) / 1000000.0f;
        float faceT = RangesKt.coerceIn((float)(elapsedMs / 500.0f), (float)0.0f, (float)1.0f);
        this.faceProgress = this.faceFrom + (this.faceTarget - this.faceFrom) * WatermarkComp.Companion.brutalEase(faceT);
    }

    private final void updateCache() {
        String string;
        long second;
        String name;
        if (!this.metricsReady) {
            int index;
            this.brandWidth = 0.0f;
            this.brandIconWidth = Fonts.KIMIKO.msdfWidth(BRAND_ICON_GLYPH, 18.0f);
            int n = BRAND_GLYPHS.length;
            for (index = 0; index < n; ++index) {
                float glyphWidth;
                this.brandGlyphWidths[index] = glyphWidth = Fonts.SMALL_PIXEL.msdfWidth(BRAND_GLYPHS[index], 16.0f);
                this.brandWidth += glyphWidth;
            }
            n = BRAND_ICON_GLYPHS.length;
            for (index = 0; index < n; ++index) {
                this.brandIconGlyphWidths[index] = Fonts.KIMIKO.msdfWidth(BRAND_ICON_GLYPHS[index], 18.0f);
            }
            this.nameSlotWidth = WatermarkComp.Companion.trackedWidth("USER  Player", 8.0f);
            this.timeSlotWidth = WatermarkComp.Companion.trackedWidth("TIME  00:00 AM", 8.0f);
            this.metricsReady = true;
        }
        if (!Intrinsics.areEqual((Object)(name = WatermarkComp.Companion.playerName()), (Object)this.cachedName)) {
            this.cachedName = name;
            this.nameSlotWidth = WatermarkComp.Companion.trackedWidth("USER  " + name, 8.0f);
        }
        if ((second = System.currentTimeMillis() / 1000L) != this.timeSecond) {
            this.timeSecond = second;
            String string2 = LocalTime.now().format(TIME_FORMAT);
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"format(...)");
            this.timeText = string2;
        }
        WatermarkModule module = ModuleManager.Companion.get().get(WatermarkModule.class);
        this.payloads.clear();
        String mode = module != null && module.displayMode != null ? module.displayMode.getSelected() : null;
        if (Intrinsics.areEqual((Object)mode, (Object)"Никнейм")) {
            this.payloads.add(0);
        } else if (Intrinsics.areEqual((Object)mode, (Object)"Время")) {
            this.payloads.add(1);
        } else {
            this.payloads.add(0);
            this.payloads.add(1);
        }
        if (module != null && module.showMedia.getValue() && this.mediaReady()) {
            this.payloads.add(2);
        }
    }

    private final boolean mediaReady() {
        long now = System.currentTimeMillis();
        long l = now - this.mediaReadyAt;
        boolean bl = 0L <= l ? l < 50L : false;
        if (bl) {
            return this.mediaReadyCached;
        }
        this.mediaReadyAt = now;
        this.mediaReadyCached = MediaPlayer.isAvailable() && !MediaPlayer.isAdvertisement() && MediaPlayer.isPlaying() && !MediaPlayer.getTrack().isEmpty();
        return this.mediaReadyCached;
    }

    private final float[] coverUv() {
        float u0 = 0.0f;
        float v0 = 0.0f;
        float u1 = 1.0f;
        float v1 = 1.0f;
        int width = MediaPlayer.getCoverWidth();
        int height = MediaPlayer.getCoverHeight();
        if (width > 0 && height > 0) {
            if (width > height) {
                float crop = (1.0f - (float)height / (float)width) * 0.5f;
                u0 = crop;
                u1 = 1.0f - crop;
            } else if (height > width) {
                float crop = (1.0f - (float)width / (float)height) * 0.5f;
                v0 = crop;
                v1 = 1.0f - crop;
            }
        }
        return new float[]{u0, v0, u1, v1};
    }

    private final void drawTrackedAdaptive(Fonts font, String text, float x, float y, float size, int color) {
        int count;
        float cursor = x;
        for (int i = 0; i < text.length(); i += count) {
            count = Character.charCount(text.codePointAt(i));
            String glyph = text.substring(i, i + count);
            Render2D.msdfAdaptiveText(font, glyph, cursor, y, size, color, this.textBackdropCapture);
            cursor += font.msdfWidth(glyph, size) + size * 0.1f;
        }
    }

    private final void updateWidth() {
        float payload = Math.max(this.nameSlotWidth, this.timeSlotWidth);
        float staticNeed = 20.0f + this.brandWidth + 10.0f + this.brandIconWidth;
        this.boxWidth = Math.max(staticNeed, 20.0f + payload);
    }

    private final boolean shouldShow() {
        WatermarkModule module = ModuleManager.Companion.get().get(WatermarkModule.class);
        return module != null && module.isEnabled();
    }

    static {
        LOADER_SCALE = new float[]{0.5f, 0.625f, 0.7692308f, 0.90909094f, 1.0f, 0.90909094f, 0.7692308f, 0.625f, 0.5f};
        LOADER_OPACITY = new float[]{0.6f, 0.7f, 0.8f, 0.9f, 1.0f, 0.9f, 0.8f, 0.7f, 0.6f};
        LOADER_MARGIN_EM = new float[]{-2.1f, -0.98f, -0.33f, -0.05f, 0.0f, 0.05f, 0.33f, 0.98f, 2.1f};
        LOADER_GRAD_START = new float[]{0.04f, 0.09f, 0.15f, 0.2f, 0.0f, 0.29f, 0.34f, 0.39f, 0.45f};
        LOADER_GRAD_END = new float[]{0.07f, 0.13f, 0.18f, 0.23f, 0.0f, 0.32f, 0.37f, 0.42f, 0.48f};
        BRAND_GLYPHS = new String[]{"K", "i", "m", "i", "k", "o"};
        BRAND_ICON_GLYPHS = new String[]{BRAND_ICON_GLYPH};
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);
        Intrinsics.checkNotNullExpressionValue((Object)dateTimeFormatter, (String)"ofPattern(...)");
        TIME_FORMAT = dateTimeFormatter;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0014\n\u0002\b\u0015\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ'\u0010\u000f\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J'\u0010\u0011\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0013R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0019R\u0014\u0010\u001c\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u0019R\u0014\u0010\u001d\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u0019R\u0014\u0010\u001e\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u0019R\u0014\u0010\u001f\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u0019R\u0014\u0010 \u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010\u0019R\u0014\u0010!\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b!\u0010\u0019R\u0014\u0010\"\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010\u0019R\u0014\u0010#\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b#\u0010\u0019R\u0014\u0010$\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010\u0019R\u0014\u0010%\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010\u0019R\u0014\u0010&\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010\u0019R\u0014\u0010'\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010\u0019R\u0014\u0010)\u001a\u00020(8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020(8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010*R\u0014\u0010,\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010\u0017R\u0014\u0010-\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010\u0019R\u0014\u0010.\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010\u0019R\u0014\u0010/\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b/\u0010\u0019R\u0014\u00100\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b0\u0010\u0019R\u0014\u00101\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b1\u0010\u0019R\u0014\u00102\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u0010\u0019R\u0014\u00104\u001a\u0002038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0014\u00106\u001a\u0002038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b6\u00105R\u0014\u00107\u001a\u0002038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b7\u00105R\u0014\u00108\u001a\u0002038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00105R\u0014\u00109\u001a\u0002038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b9\u00105R\u0014\u0010:\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b:\u0010\u0019R\u0014\u0010;\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b;\u0010\u0019R\u0014\u0010<\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b<\u0010\u0017R\u0014\u0010=\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b=\u0010\u0017R\u0014\u0010>\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b>\u0010\u0017R\u0014\u0010?\u001a\u00020(8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b?\u0010*R\u0014\u0010@\u001a\u00020(8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u0010*R\u0014\u0010A\u001a\u00020(8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bA\u0010*R\u0014\u0010B\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bB\u0010\u0019R\u0014\u0010C\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bC\u0010\u0019R\u0014\u0010D\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bD\u0010\u0019R\u0014\u0010E\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bE\u0010\u0019R\u0014\u0010F\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bF\u0010GR\u0014\u0010H\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bH\u0010GR\u001a\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00040I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010KR\u001a\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00040I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u0010KR\u0014\u0010M\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bM\u0010GR\u0014\u0010N\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bN\u0010\u0019R\u0014\u0010P\u001a\u00020O8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bP\u0010Q\u00a8\u0006R"}, d2={"Lrtx/kimiko/api/drags/components/WatermarkComp.Companion;", "", "<init>", "()V", "", "text", "", "size", "trackedWidth", "(Ljava/lang/String;F)F", "t", "brutalEase", "(F)F", "p1", "p2", "bezier", "(FFF)F", "bezierDerivative", "playerName", "()Ljava/lang/String;", "gameName", "", "MEDIA_READY_CACHE_MS", "J", "H", "F", "PAD_X", "MIN_GAP", "GAP", "SHADOW_OFFSET", "FONT", "LOADER_FONT", "TRACK_EM", "LOADER_ICON", "DOT_RADIUS", "DOT_Y_ADJUST", "RADIUS", "TEXT_Y_ADJUST", "LOADER_WIDTH_EM", "SCISSOR_EPSILON", "", "LOADER_SLICES", "I", "LOADER_CENTER_SLICE", "LOADER_PERIOD_MS", "LOADER_SCROLL", "CSS_MARGIN_CENTER_FACTOR", "LOADER_X1", "LOADER_Y1", "LOADER_X2", "LOADER_Y2", "", "LOADER_SCALE", "[F", "LOADER_OPACITY", "LOADER_MARGIN_EM", "LOADER_GRAD_START", "LOADER_GRAD_END", "PAYLOAD_BLUR_RADIUS", "FACE_MS", "MARQUEE_MS", "CALM_MS", "ACTIVE_MS", "PAYLOAD_USER", "PAYLOAD_TIME", "PAYLOAD_MEDIA", "COVER", "COVER_GAP", "COVER_Y_ADJUST", "MEDIA_TEXT_Y_ADJUST", "MEDIA_NOTE_GLYPH", "Ljava/lang/String;", "BRAND_ICON_GLYPH", "", "BRAND_GLYPHS", "[Ljava/lang/String;", "BRAND_ICON_GLYPHS", "FALLBACK_NAME", "MIN_ALPHA", "Ljava/time/format/DateTimeFormatter;", "TIME_FORMAT", "Ljava/time/format/DateTimeFormatter;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float trackedWidth(String text, float size) {
            int count;
            float width = 0.0f;
            for (int i = 0; i < text.length(); i += count) {
                count = Character.charCount(text.codePointAt(i));
                String string = text.substring(i, i + count);
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
                width += Fonts.MEDIUM.msdfWidth(string, size) + size * 0.1f;
            }
            return width;
        }

        private final float brutalEase(float t) {
            if (t <= 0.0f) {
                return 0.0f;
            }
            if (t >= 1.0f) {
                return 1.0f;
            }
            float u = 0.0f;
            u = t;
            int n = 5;
            for (int i = 0; i < n; ++i) {
                int it = i;
                boolean bl = false;
                float x = Companion.bezier(u, 0.85f, 0.15f);
                float d = Companion.bezierDerivative(u, 0.85f, 0.15f);
                if (!(Math.abs(d) > 1.0E-5f)) continue;
                u = RangesKt.coerceIn((float)(u - (x - t) / d), (float)0.0f, (float)1.0f);
            }
            return this.bezier(u, 0.0f, 1.0f);
        }

        private final float bezier(float t, float p1, float p2) {
            float oneMinus = 1.0f - t;
            return 3.0f * oneMinus * oneMinus * t * p1 + 3.0f * oneMinus * t * t * p2 + t * t * t;
        }

        private final float bezierDerivative(float t, float p1, float p2) {
            float oneMinus = 1.0f - t;
            return 3.0f * oneMinus * oneMinus * p1 + 6.0f * oneMinus * t * (p2 - p1) + 3.0f * t * t * (1.0f - p2);
        }

        private final String playerName() {
            String string = ProfileIdentity.username(this.gameName());
            if (string == null) {
                string = WatermarkComp.FALLBACK_NAME;
            }
            return string;
        }

        private final String gameName() {
            String name;
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient mc = minecraftClient2;
            Session session2 = mc.getSession();
            Intrinsics.checkNotNullExpressionValue((Object)session2, (String)"getUser(...)");
            Session user = session2;
            String string = user.getUsername();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getName(...)");
            if (!StringsKt.isBlank((CharSequence)string)) {
                String string2 = user.getUsername();
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getName(...)");
                return string2;
            }
            ClientPlayerEntity player = mc.player;
            if (player != null && (name = player.getGameProfile().name()) != null && !StringsKt.isBlank((CharSequence)name)) {
                return name;
            }
            return WatermarkComp.FALLBACK_NAME;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

