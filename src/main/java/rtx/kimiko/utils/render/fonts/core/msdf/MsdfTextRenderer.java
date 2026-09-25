/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.BlendFunction
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Snippet
 *  com.mojang.blaze3d.platform.DepthTestFunction
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.textures.FilterMode
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  com.mojang.blaze3d.vertex.VertexFormat
 *  com.mojang.blaze3d.vertex.VertexFormat$DrawMode
 *  com.mojang.blaze3d.vertex.VertexFormatElement
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  kotlin.text.StringsKt
 *  net.minecraft.client.texture.AbstractTexture
 *  net.minecraft.client.texture.TextureManager
 *  net.minecraft.client.gl.UniformType
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.client.gui.render.state.GuiRenderState
 *  net.minecraft.client.gl.GpuSampler
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.ScreenRect
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package rtx.kimiko.utils.render.fonts.core.msdf;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.ScreenRect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.fonts.core.msdf.BuiltMsdfText;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfFont;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfFonts;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfGlyph;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfTextRenderState;
import rtx.kimiko.utils.render.modules.post.GuiRenderStateLayerAccessor;
import rtx.kimiko.utils.render.others.RoundedScissor;
import rtx.kimiko.utils.render.render2d.PoseCache;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.render2d.blur.BlurCapture;
import rtx.kimiko.utils.render.util.scissor.ScissorUtil;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00bc\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 r2\u00060\u0001j\u0002`\u0002:\u0004sturB\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0004J\u0017\u0010\t\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0004\b\r\u0010\u000eJ!\u0010\u0011\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\u0004\b\u0011\u0010\u0012J7\u0010\u0018\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001b\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001a\u001a\u00020\u0013\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001d\u001a\u00020\u0005\u00a2\u0006\u0004\b\u001d\u0010\u0004J)\u0010\"\u001a\u00020\u00132\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\u0010\f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\u0013\u00a2\u0006\u0004\b\"\u0010#J)\u0010%\u001a\u00020$2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\u0010\f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\u0013\u00a2\u0006\u0004\b%\u0010&Jm\u0010-\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010(\u001a\u00020'2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00132\u0006\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020\u00132\u0006\u0010+\u001a\u00020'2\b\u0010,\u001a\u0004\u0018\u00010\u000fH\u0002\u00a2\u0006\u0004\b-\u0010.JQ\u00106\u001a\u00020\u00052\u0006\u00100\u001a\u00020/2\u0006\u00102\u001a\u0002012\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020'2\u0006\u00104\u001a\u0002032\u0006\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020\u00132\b\u00105\u001a\u0004\u0018\u00010$H\u0002\u00a2\u0006\u0004\b6\u00107J'\u00108\u001a\u00020$2\u0006\u00102\u001a\u0002012\u0006\u0010\f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b8\u00109J\u001f\u0010<\u001a\u0002032\u0006\u0010:\u001a\u0002032\u0006\u0010;\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b<\u0010=J'\u0010?\u001a\u0002032\u0006\u0010:\u001a\u0002032\u0006\u0010>\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b?\u0010@J\u0019\u0010D\u001a\u0004\u0018\u00010C2\u0006\u0010B\u001a\u00020AH\u0002\u00a2\u0006\u0004\bD\u0010EJ\u000f\u0010F\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\bF\u0010\u0004J\u000f\u0010G\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\bG\u0010\u0004J\u000f\u0010H\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\bH\u0010\u0004R0\u0010L\u001a\u001e\u0012\u0004\u0012\u00020J\u0012\u0004\u0012\u00020/0Ij\u000e\u0012\u0004\u0012\u00020J\u0012\u0004\u0012\u00020/`K8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u0010MR0\u0010P\u001a\u001e\u0012\u0004\u0012\u00020J\u0012\u0004\u0012\u0002030Nj\u000e\u0012\u0004\u0012\u00020J\u0012\u0004\u0012\u000203`O8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bP\u0010QR\u0018\u0010S\u001a\u0004\u0018\u00010R8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bS\u0010TR\u0016\u0010U\u001a\u0002038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bU\u0010VR\u0018\u0010W\u001a\u0004\u0018\u00010A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bW\u0010XR\u0018\u0010Z\u001a\u0004\u0018\u00010Y8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bZ\u0010[R\u0016\u0010\\\u001a\u00020'8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\\\u0010]R\u0016\u0010^\u001a\u0002038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b^\u0010VR\u0016\u0010_\u001a\u00020'8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b_\u0010]R\u0016\u0010`\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b`\u0010aR\u0016\u0010b\u001a\u00020'8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bb\u0010]R\u0018\u0010c\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010dR\u0018\u0010f\u001a\u0004\u0018\u00010e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bf\u0010gR\u0018\u0010h\u001a\u0004\u0018\u00010/8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bh\u0010iR0\u0010k\u001a\u001e\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020j0Nj\u000e\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020j`O8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bk\u0010QR$\u0010n\u001a\u0012\u0012\u0004\u0012\u00020A0lj\b\u0012\u0004\u0012\u00020A`m8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bn\u0010oR\u0018\u0010p\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bp\u0010q\u00a8\u0006v"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderer;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "", "rememberBatchSizes", "Lnet/minecraft/DrawContext;", "graphics", "beginFrame", "(Lnet/minecraft/DrawContext;)V", "Lrtx/kimiko/utils/render/fonts/core/msdf/BuiltMsdfText;", "text", "enqueue", "(Lrtx/kimiko/utils/render/fonts/core/msdf/BuiltMsdfText;)V", "Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "capture", "enqueueAdaptive", "(Lrtx/kimiko/utils/render/fonts/core/msdf/BuiltMsdfText;Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;)V", "", "progress", "halfWidth", "intensity", "opacity", "enqueueShimmer", "(Lrtx/kimiko/utils/render/fonts/core/msdf/BuiltMsdfText;FFFF)V", "phase", "enqueueWave", "(Lrtx/kimiko/utils/render/fonts/core/msdf/BuiltMsdfText;F)V", "flush", "Lrtx/kimiko/utils/render/fonts/Fonts;", "fontName", "", "size", "width", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;F)F", "", "glyphBounds", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;F)[F", "", "shimmer", "wave", "wavePhase", "adaptiveBackground", "backgroundCapture", "submit", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/utils/render/fonts/core/msdf/BuiltMsdfText;ZFFFFZFZLrtx/kimiko/utils/render/render2d/blur/BlurCapture;)V", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderState;", "state", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfFont;", "font", "", "shimmerParams", "localClip", "layoutInto", "(Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderState;Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfFont;Lrtx/kimiko/utils/render/fonts/core/msdf/BuiltMsdfText;ZIZF[F)V", "measureLines", "(Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfFont;Ljava/lang/String;F)[F", "color", "k", "scaleAlpha", "(IF)I", "x", "fadeColor", "(IFLrtx/kimiko/utils/render/fonts/core/msdf/BuiltMsdfText;)I", "Lnet/minecraft/Identifier;", "id", "Lnet/minecraft/TextureSetup;", "resolveTexture", "(Lnet/minecraft/Identifier;)Lnet/minecraft/TextureSetup;", "close", "clearCaches", "kimikoResetBatchCache", "Ljava/util/LinkedHashMap;", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderer$FrameBatchKey;", "Lkotlin/collections/LinkedHashMap;", "frameBatches", "Ljava/util/LinkedHashMap;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "batchSizes", "Ljava/util/HashMap;", "Lnet/minecraft/GuiRenderState;", "lastGuiState", "Lnet/minecraft/GuiRenderState;", "lastLayerSerial", "I", "lastAtlas", "Lnet/minecraft/Identifier;", "Lnet/minecraft/ScreenRect;", "lastScissorArea", "Lnet/minecraft/ScreenRect;", "lastShimmer", "Z", "lastShimmerParams", "lastWave", "lastWavePhase", "F", "lastAdaptiveBackground", "lastBackgroundCapture", "Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "Lorg/joml/Matrix3x2f;", "lastPose", "Lorg/joml/Matrix3x2f;", "lastState", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderState;", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderer$CachedTexture;", "textures", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "missingTextureWarned", "Ljava/util/HashSet;", "activeGraphics", "Lnet/minecraft/DrawContext;", "Companion", "FrameBatchKey", "PoseKey", "CachedTexture", "rtx.kimiko:kimiko"})
public final class MsdfTextRenderer
implements AutoCloseable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final LinkedHashMap<FrameBatchKey, MsdfTextRenderState> frameBatches = new LinkedHashMap(32);
    @NotNull
    private final HashMap<FrameBatchKey, Integer> batchSizes = new HashMap(64);
    @Nullable
    private GuiRenderState lastGuiState;
    private int lastLayerSerial;
    @Nullable
    private Identifier lastAtlas;
    @Nullable
    private ScreenRect lastScissorArea;
    private boolean lastShimmer;
    private int lastShimmerParams;
    private boolean lastWave;
    private float lastWavePhase;
    private boolean lastAdaptiveBackground;
    @Nullable
    private BlurCapture lastBackgroundCapture;
    @Nullable
    private Matrix3x2f lastPose;
    @Nullable
    private MsdfTextRenderState lastState;
    @NotNull
    private final HashMap<Identifier, CachedTexture> textures = new HashMap();
    @NotNull
    private final HashSet<Identifier> missingTextureWarned = new HashSet();
    @Nullable
    private DrawContext activeGraphics;
    private static final int MAX_REMEMBERED_BATCHES = 512;
    private static final Logger LOGGER = LoggerFactory.getLogger((String)"Kimiko/MSDF");
    @Nullable
    private static volatile MsdfTextRenderer instance;
    @JvmField
    @NotNull
    public static final VertexFormat MSDF_SHIMMER_VERTEX_FORMAT;
    @JvmField
    @NotNull
    public static final RenderPipeline MSDF_PIPELINE;
    @JvmField
    @NotNull
    public static final RenderPipeline MSDF_ADAPTIVE_PIPELINE;
    @JvmField
    @NotNull
    public static final RenderPipeline MSDF_SHIMMER_PIPELINE;
    @JvmField
    @NotNull
    public static final RenderPipeline MSDF_WAVE_PIPELINE;

    private MsdfTextRenderer() {
    }

    private final void rememberBatchSizes() {
        if (this.batchSizes.size() > 512) {
            this.batchSizes.clear();
        }
        for (Map.Entry<FrameBatchKey, MsdfTextRenderState> entry : this.frameBatches.entrySet()) {
            FrameBatchKey key = entry.getKey();
            MsdfTextRenderState state = entry.getValue();
            int count = state.glyphCount();
            if (count <= 0) continue;
            this.batchSizes.put(key, count);
        }
    }

    public final void beginFrame(@Nullable DrawContext graphics) {
        if (this.activeGraphics != graphics) {
            this.rememberBatchSizes();
            this.frameBatches.clear();
            this.kimikoResetBatchCache();
        }
        this.activeGraphics = graphics;
    }

    public final void enqueue(@Nullable BuiltMsdfText text) {
        this.submit(this.activeGraphics, text, false, 0.0f, 0.0f, 0.0f, 0.0f, false, 0.0f, false, null);
    }

    public final void enqueueAdaptive(@Nullable BuiltMsdfText text, @Nullable BlurCapture capture) {
        this.submit(this.activeGraphics, text, false, 0.0f, 0.0f, 0.0f, 0.0f, false, 0.0f, true, capture);
    }

    public final void enqueueShimmer(@Nullable BuiltMsdfText text, float progress, float halfWidth, float intensity, float opacity) {
        this.submit(this.activeGraphics, text, true, progress, halfWidth, intensity, opacity, false, 0.0f, false, null);
    }

    public final void enqueueWave(@Nullable BuiltMsdfText text, float phase) {
        this.submit(this.activeGraphics, text, false, 0.0f, 0.0f, 0.0f, 0.0f, true, phase, false, null);
    }

    public final void flush() {
        this.activeGraphics = null;
        this.rememberBatchSizes();
        this.frameBatches.clear();
        this.kimikoResetBatchCache();
    }

    public final float width(@Nullable Fonts fontName, @Nullable String text, float size) {
        MsdfFont font;
        MsdfFont msdfFont = font = MsdfFonts.get(fontName);
        return msdfFont != null ? msdfFont.width(text, size) : 0.0f;
    }

    @NotNull
    public final float[] glyphBounds(@Nullable Fonts fontName, @Nullable String text, float size) {
        MsdfFont font = MsdfFonts.get(fontName);
        if (font != null) {
            float[] fArray = font.glyphBounds(text, size);
            if (fArray != null) {
                return fArray;
            }
        }
        return new float[]{0.0f, 0.0f};
    }

    private final void submit(DrawContext graphics, BuiltMsdfText text, boolean shimmer, float progress, float halfWidth, float intensity, float opacity, boolean wave, float wavePhase, boolean adaptiveBackground, BlurCapture backgroundCapture) {
        if (graphics == null || text == null || !text.visible()) {
            return;
        }
        MsdfFont msdfFont = MsdfFonts.get(text.font());
        if (msdfFont == null) {
            return;
        }
        MsdfFont font = msdfFont;
        TextureSetup textureSetup = this.resolveTexture(font.atlasTexture());
        if (textureSetup == null) {
            if (this.missingTextureWarned.add(font.atlasTexture())) {
                LOGGER.warn("[MSDF] Atlas texture not resolvable: {}", (Object)font.atlasTexture());
            }
            return;
        }
        try {
            Matrix3x2f pose = Render2DCoordinateSpace.pose(graphics);
            GpuTextureView gpuTextureView = MinecraftClient.getInstance().getTextureManager().getTexture(font.atlasTexture()).getGlTextureView();
            Intrinsics.checkNotNullExpressionValue((Object)gpuTextureView, (String)"getTextureView(...)");
            GpuTextureView atlasView = gpuTextureView;
            GuiRenderState guiState = ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState();
            Intrinsics.checkNotNull((Object)guiState, (String)"null cannot be cast to non-null type rtx.kimiko.utils.render.modules.post.GuiRenderStateLayerAccessor");
            int layerSerial = ((GuiRenderStateLayerAccessor)guiState).kimiko$getLayerSerial();
            ScreenRect scissorArea = ScissorUtil.current();
            int shimmerParams = shimmer ? MsdfTextRenderer.Companion.packShimmerParams(progress, halfWidth, intensity, opacity) : 0;
            MsdfTextRenderState state = null;
            MsdfTextRenderState cachedState = this.lastState;
            if (cachedState != null && this.lastGuiState == guiState && this.lastLayerSerial == layerSerial && this.lastAtlas == font.atlasTexture() && this.lastScissorArea == scissorArea && this.lastShimmer == shimmer && this.lastShimmerParams == shimmerParams && this.lastWave == wave && this.lastWavePhase == wavePhase && this.lastAdaptiveBackground == adaptiveBackground && this.lastBackgroundCapture == backgroundCapture && this.lastPose != null && Intrinsics.areEqual((Object)this.lastPose, (Object)pose)) {
                state = cachedState;
            } else {
                FrameBatchKey key = new FrameBatchKey(guiState, layerSerial, font.atlasTexture(), PoseKey.Companion.of(pose), scissorArea, shimmer, shimmerParams, wave, wavePhase, adaptiveBackground, backgroundCapture);
                MsdfTextRenderState existing = this.frameBatches.get(key);
                if (existing == null) {
                    Integer n = this.batchSizes.get(key);
                    existing = new MsdfTextRenderState(pose, textureSetup, scissorArea, shimmer, wave, font.atlasWidth() <= 0 ? 0.0f : 4.0f / (float)font.atlasWidth(), n != null ? n : 4, adaptiveBackground, atlasView, backgroundCapture);
                    ((Map)this.frameBatches).put(key, existing);
                    guiState.addPreparedTextElement((SimpleGuiElementRenderState)existing);
                }
                state = existing;
                this.lastGuiState = guiState;
                this.lastLayerSerial = layerSerial;
                this.lastAtlas = font.atlasTexture();
                this.lastScissorArea = scissorArea;
                this.lastShimmer = shimmer;
                this.lastShimmerParams = shimmerParams;
                this.lastWave = wave;
                this.lastWavePhase = wavePhase;
                this.lastAdaptiveBackground = adaptiveBackground;
                this.lastBackgroundCapture = backgroundCapture;
                this.lastPose = PoseCache.snapshot(pose);
                this.lastState = state;
            }
            this.layoutInto(state, font, text, shimmer, shimmerParams, wave, wavePhase, RoundedScissor.localClipFor(pose));
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    private final void layoutInto(MsdfTextRenderState state, MsdfFont font, BuiltMsdfText text, boolean shimmer, int shimmerParams, boolean wave, float wavePhase, float[] localClip) {
        String string = text.text();
        if (string == null) {
            return;
        }
        String s = string;
        state.reserve(s.length());
        float size = text.size();
        float originX = text.x();
        float baseline = text.y() + font.ascent(size);
        float lineHeight = font.lineHeight(size);
        float[] lineWidths = this.measureLines(font, s, size);
        float penX = originX;
        float penBaseline = baseline;
        int lineIndex = 0;
        int prev = -1;
        int i = 0;
        while (i < s.length()) {
            int cp = s.codePointAt(i);
            i += Character.charCount(cp);
            if (cp == 10) {
                penX = originX;
                penBaseline += lineHeight;
                ++lineIndex;
                prev = -1;
                continue;
            }
            MsdfGlyph glyph = font.glyph(cp);
            if (prev != -1) {
                penX += font.kerning(prev, cp) * size;
            }
            if (glyph.drawable()) {
                float x0 = penX + glyph.planeLeft() * size;
                float x1 = penX + glyph.planeRight() * size;
                float y0 = penBaseline - glyph.planeTop() * size;
                float y1 = penBaseline - glyph.planeBottom() * size;
                float lineWidth = lineIndex < lineWidths.length ? lineWidths[lineIndex] : 1.0f;
                float l0 = 0.0f;
                float l1 = 0.0f;
                int topLeftColor = 0;
                int topRightColor = 0;
                int bottomRightColor = 0;
                int bottomLeftColor = 0;
                if (wave) {
                    l0 = wavePhase;
                    l1 = wavePhase;
                    topLeftColor = this.fadeColor(text.colorTopLeft(), x0, text);
                    topRightColor = this.fadeColor(text.colorTopRight(), x1, text);
                    bottomRightColor = this.fadeColor(text.colorBottomRight(), x1, text);
                    bottomLeftColor = this.fadeColor(text.colorBottomLeft(), x0, text);
                } else if (shimmer) {
                    int tint = text.colorTopLeft();
                    l0 = MsdfTextRenderer.Companion.packLineColor(MsdfTextRenderer.Companion.clamp01((x0 - originX) / lineWidth), tint);
                    l1 = MsdfTextRenderer.Companion.packLineColor(MsdfTextRenderer.Companion.clamp01((x1 - originX) / lineWidth), tint);
                    topLeftColor = shimmerParams;
                    topRightColor = shimmerParams;
                    bottomRightColor = shimmerParams;
                    bottomLeftColor = shimmerParams;
                } else {
                    l0 = 0.0f;
                    l1 = 0.0f;
                    topLeftColor = this.fadeColor(text.colorTopLeft(), x0, text);
                    topRightColor = this.fadeColor(text.colorTopRight(), x1, text);
                    bottomRightColor = this.fadeColor(text.colorBottomRight(), x1, text);
                    bottomLeftColor = this.fadeColor(text.colorBottomLeft(), x0, text);
                }
                float gu0 = glyph.u0();
                float gv0 = glyph.v0();
                float gu1 = glyph.u1();
                float gv1 = glyph.v1();
                boolean visible = true;
                if (localClip != null) {
                    float cx0 = localClip[0];
                    float cy0 = localClip[1];
                    float cx1 = localClip[2];
                    float cy1 = localClip[3];
                    float spanX = x1 - x0;
                    float spanY = y1 - y0;
                    if (x1 <= cx0 || x0 >= cx1 || y1 <= cy0 || y0 >= cy1 || spanX <= 0.0f || spanY <= 0.0f) {
                        visible = false;
                    } else {
                        float clippedX0 = Math.max(x0, cx0);
                        float clippedX1 = Math.min(x1, cx1);
                        float clippedY0 = Math.max(y0, cy0);
                        float clippedY1 = Math.min(y1, cy1);
                        float uSpan = gu1 - gu0;
                        float vSpan = gv1 - gv0;
                        gu0 += (clippedX0 - x0) / spanX * uSpan;
                        gu1 -= (x1 - clippedX1) / spanX * uSpan;
                        gv0 += (clippedY0 - y0) / spanY * vSpan;
                        gv1 -= (y1 - clippedY1) / spanY * vSpan;
                        x0 = clippedX0;
                        x1 = clippedX1;
                        y0 = clippedY0;
                        y1 = clippedY1;
                        float fade = RoundedScissor.localFadeTop();
                        if (fade > 0.0f) {
                            float t = RangesKt.coerceIn((float)(((y0 + y1) * 0.5f - cy0) / fade), (float)0.0f, (float)1.0f);
                            float k = t * t * (3.0f - 2.0f * t);
                            topLeftColor = this.scaleAlpha(topLeftColor, k);
                            topRightColor = this.scaleAlpha(topRightColor, k);
                            bottomRightColor = this.scaleAlpha(bottomRightColor, k);
                            bottomLeftColor = this.scaleAlpha(bottomLeftColor, k);
                        }
                    }
                }
                if (visible) {
                    state.addGlyph(x0, y0, x1, y1, gu0, gv0, gu1, gv1, topLeftColor, topRightColor, bottomRightColor, bottomLeftColor, l0, l1, text.rotationDegrees(), text.rotationOriginX(), text.rotationOriginY());
                }
            }
            penX += glyph.advance() * size;
            prev = cp;
        }
    }

    private final float[] measureLines(MsdfFont font, String text, float size) {
        if (!String.valueOf(text).contains("\n")) {
            float width = Math.max(font.width(text, size), 1.0f);
            return new float[]{width};
        }
        int lines = 1;
        int n = ((CharSequence)text).length();
        for (int i = 0; i < n; ++i) {
            if (text.charAt(i) != '\n') continue;
            ++lines;
        }
        float[] widths2 = new float[lines];
        int line = 0;
        int prev = -1;
        int i = 0;
        while (i < text.length()) {
            int n2;
            int cp = text.codePointAt(i);
            i += Character.charCount(cp);
            if (cp == 10) {
                widths2[line] = Math.max(widths2[line], 1.0f);
                ++line;
                prev = -1;
                continue;
            }
            MsdfGlyph glyph = font.glyph(cp);
            if (prev != -1) {
                n2 = line;
                widths2[n2] = widths2[n2] + font.kerning(prev, cp) * size;
            }
            n2 = line;
            widths2[n2] = widths2[n2] + glyph.advance() * size;
            prev = cp;
        }
        int n3 = widths2.length;
        for (int i2 = 0; i2 < n3; ++i2) {
            widths2[i2] = Math.max(widths2[i2], 1.0f);
        }
        return widths2;
    }

    private final int scaleAlpha(int color, float k) {
        if (k >= 0.999f) {
            return color;
        }
        int a = color >>> 24 & 0xFF;
        return Math.round((float)a * k) << 24 | color & 0xFFFFFF;
    }

    private final int fadeColor(int color, float x, BuiltMsdfText text) {
        float edgeAlpha;
        if (!text.hasHorizontalFade()) {
            return color;
        }
        float alpha = 1.0f;
        if (text.fadeLeftStrength() > 0.001f) {
            edgeAlpha = MsdfTextRenderer.Companion.smoothstep(MsdfTextRenderer.Companion.clamp01((x - text.fadeLeftX()) / text.fadeWidth()));
            alpha = Math.min(alpha, 1.0f - text.fadeLeftStrength() * (1.0f - edgeAlpha));
        }
        if (text.fadeRightStrength() > 0.001f) {
            edgeAlpha = MsdfTextRenderer.Companion.smoothstep(MsdfTextRenderer.Companion.clamp01((text.fadeRightX() - x) / text.fadeWidth()));
            alpha = Math.min(alpha, 1.0f - text.fadeRightStrength() * (1.0f - edgeAlpha));
        }
        int originalAlpha = color >>> 24 & 0xFF;
        return Math.round((float)originalAlpha * alpha) << 24 | color & 0xFFFFFF;
    }

    private final TextureSetup resolveTexture(Identifier id) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        TextureManager textureManager2 = mc.getTextureManager();
        Intrinsics.checkNotNullExpressionValue((Object)textureManager2, (String)"getTextureManager(...)");
        TextureManager tm = textureManager2;
        AbstractTexture abstractTexture3 = tm.getTexture(id);
        Intrinsics.checkNotNullExpressionValue((Object)abstractTexture3, (String)"getTexture(...)");
        AbstractTexture texture = abstractTexture3;
        if (texture.getGlTextureView() == null) {
            return null;
        }
        CachedTexture cached = this.textures.get(id);
        if (cached != null && cached.getTexture() == texture) {
            return cached.getSetup();
        }
        TextureSetup textureSetup2 = TextureSetup.of((GpuTextureView)texture.getGlTextureView(), (GpuSampler)RenderSystem.getSamplerCache().get(FilterMode.LINEAR));
        Intrinsics.checkNotNullExpressionValue((Object)textureSetup2, (String)"singleTexture(...)");
        TextureSetup setup = textureSetup2;
        ((Map)this.textures).put(id, new CachedTexture(texture, setup));
        return setup;
    }

    @Override
    public void close() {
        this.clearCaches();
    }

    private final void clearCaches() {
        this.activeGraphics = null;
        this.frameBatches.clear();
        this.kimikoResetBatchCache();
        this.textures.clear();
        MsdfFonts.clear();
    }

    private final void kimikoResetBatchCache() {
        this.lastGuiState = null;
        this.lastAtlas = null;
        this.lastScissorArea = null;
        this.lastAdaptiveBackground = false;
        this.lastBackgroundCapture = null;
        this.lastPose = null;
        this.lastState = null;
    }

    @JvmStatic
    @NotNull
    public static final MsdfTextRenderer getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final void closeInstance() {
        Companion.closeInstance();
    }

    @JvmStatic
    public static final void clearResourceCaches() {
        Companion.clearResourceCaches();
    }

    public /* synthetic */ MsdfTextRenderer(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    static {
        VertexFormat vertexFormat = VertexFormat.builder().add("Position", VertexFormatElement.POSITION).add("UV0", VertexFormatElement.UV0).add("Color", VertexFormatElement.COLOR).add("LineWidth", VertexFormatElement.LINE_WIDTH).build();
        Intrinsics.checkNotNullExpressionValue((Object)vertexFormat, (String)"build(...)");
        MSDF_SHIMMER_VERTEX_FORMAT = vertexFormat;
        RenderPipeline renderPipeline = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(MsdfTextRenderer.Companion.id("pipeline/msdf_text")).withVertexShader(MsdfTextRenderer.Companion.id("core/msdf_text")).withFragmentShader(MsdfTextRenderer.Companion.id("core/msdf_text")).withVertexFormat(MSDF_SHIMMER_VERTEX_FORMAT, VertexFormat.DrawMode.QUADS).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withSampler("Sampler0").build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline, (String)"build(...)");
        MSDF_PIPELINE = renderPipeline;
        RenderPipeline renderPipeline2 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(MsdfTextRenderer.Companion.id("pipeline/msdf_text_adaptive")).withVertexShader(MsdfTextRenderer.Companion.id("core/msdf_text")).withFragmentShader(MsdfTextRenderer.Companion.id("core/msdf_text_adaptive")).withVertexFormat(MSDF_SHIMMER_VERTEX_FORMAT, VertexFormat.DrawMode.QUADS).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withSampler("Sampler0").withSampler("Sampler1").build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline2, (String)"build(...)");
        MSDF_ADAPTIVE_PIPELINE = renderPipeline2;
        RenderPipeline renderPipeline3 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(MsdfTextRenderer.Companion.id("pipeline/msdf_shimmer")).withVertexShader(MsdfTextRenderer.Companion.id("core/msdf_shimmer")).withFragmentShader(MsdfTextRenderer.Companion.id("core/msdf_shimmer")).withVertexFormat(MSDF_SHIMMER_VERTEX_FORMAT, VertexFormat.DrawMode.QUADS).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withSampler("Sampler0").build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline3, (String)"build(...)");
        MSDF_SHIMMER_PIPELINE = renderPipeline3;
        RenderPipeline renderPipeline4 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(MsdfTextRenderer.Companion.id("pipeline/msdf_wave")).withVertexShader(MsdfTextRenderer.Companion.id("core/msdf_wave")).withFragmentShader(MsdfTextRenderer.Companion.id("core/msdf_wave")).withVertexFormat(MSDF_SHIMMER_VERTEX_FORMAT, VertexFormat.DrawMode.QUADS).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withSampler("Sampler0").build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline4, (String)"build(...)");
        MSDF_WAVE_PIPELINE = renderPipeline4;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ$\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0016\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u000b\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderer$CachedTexture;", "", "Lnet/minecraft/AbstractTexture;", "texture", "Lnet/minecraft/TextureSetup;", "setup", "<init>", "(Lnet/minecraft/AbstractTexture;Lnet/minecraft/TextureSetup;)V", "component1", "()Lnet/minecraft/AbstractTexture;", "component2", "()Lnet/minecraft/TextureSetup;", "copy", "(Lnet/minecraft/AbstractTexture;Lnet/minecraft/TextureSetup;)Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderer$CachedTexture;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/AbstractTexture;", "getTexture", "Lnet/minecraft/TextureSetup;", "getSetup", "rtx.kimiko:kimiko"})
    private static final class CachedTexture {
        @NotNull
        private final AbstractTexture texture;
        @NotNull
        private final TextureSetup setup;

        public CachedTexture(@NotNull AbstractTexture texture, @NotNull TextureSetup setup) {
            Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
            Intrinsics.checkNotNullParameter((Object)setup, (String)"setup");
            this.texture = texture;
            this.setup = setup;
        }

        @NotNull
        public final AbstractTexture getTexture() {
            return this.texture;
        }

        @NotNull
        public final TextureSetup getSetup() {
            return this.setup;
        }

        @NotNull
        public final AbstractTexture component1() {
            return this.texture;
        }

        @NotNull
        public final TextureSetup component2() {
            return this.setup;
        }

        @NotNull
        public final CachedTexture copy(@NotNull AbstractTexture texture, @NotNull TextureSetup setup) {
            Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
            Intrinsics.checkNotNullParameter((Object)setup, (String)"setup");
            return new CachedTexture(texture, setup);
        }

        public static /* synthetic */ CachedTexture copy$default(CachedTexture cachedTexture, AbstractTexture abstractTexture3, TextureSetup textureSetup2, int n, Object object) {
            if ((n & 1) != 0) {
                abstractTexture3 = cachedTexture.texture;
            }
            if ((n & 2) != 0) {
                textureSetup2 = cachedTexture.setup;
            }
            return cachedTexture.copy(abstractTexture3, textureSetup2);
        }

        @NotNull
        public String toString() {
            return "CachedTexture(texture=" + this.texture + ", setup=" + this.setup + ")";
        }

        public int hashCode() {
            int result = this.texture.hashCode();
            result = result * 31 + this.setup.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CachedTexture)) {
                return false;
            }
            CachedTexture cachedTexture = (CachedTexture)other;
            if (!Intrinsics.areEqual((Object)this.texture, (Object)cachedTexture.texture)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.setup, (Object)cachedTexture.setup);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J\u0013\u0010\n\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u0003J/\u0010\u0011\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001bJ\u0017\u0010 \u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u001c\u0010&\u001a\n %*\u0004\u0018\u00010$0$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0018\u0010(\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0019\u0010,\u001a\u00020*8\u0006X\u0087\u0004\u0092\u0002\u0002\b+\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u0019\u0010/\u001a\u00020.8\u0006X\u0087\u0004\u0092\u0002\u0002\b+\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0019\u00101\u001a\u00020.8\u0006X\u0087\u0004\u0092\u0002\u0002\b+\u00a2\u0006\u0006\n\u0004\b1\u00100R\u0019\u00102\u001a\u00020.8\u0006X\u0087\u0004\u0092\u0002\u0002\b+\u00a2\u0006\u0006\n\u0004\b2\u00100R\u0019\u00103\u001a\u00020.8\u0006X\u0087\u0004\u0092\u0002\u0002\b+\u00a2\u0006\u0006\n\u0004\b3\u00100\u00a8\u00064"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderer.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderer;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderer;", "", "closeInstance", "clearResourceCaches", "", "progress", "halfWidth", "intensity", "opacity", "", "packShimmerParams", "(FFFF)I", "linePos", "color", "packLineColor", "(FI)F", "value", "toByte", "(F)I", "clamp01", "(F)F", "smoothstep", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "MAX_REMEMBERED_BATCHES", "I", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "LOGGER", "Lorg/slf4j/Logger;", "instance", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderer;", "Lcom/mojang/blaze3d/vertex/VertexFormat;", "Lkotlin/jvm/JvmField;", "MSDF_SHIMMER_VERTEX_FORMAT", "Lcom/mojang/blaze3d/vertex/VertexFormat;", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "MSDF_PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "MSDF_ADAPTIVE_PIPELINE", "MSDF_SHIMMER_PIPELINE", "MSDF_WAVE_PIPELINE", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        @NotNull
        public final MsdfTextRenderer getInstance() {
            MsdfTextRenderer local = null;
            local = instance;
            if (local == null) {
                Class<MsdfTextRenderer> clazz = MsdfTextRenderer.class;
                synchronized (clazz) {
                    boolean bl = false;
                    local = instance;
                    if (local == null) {
                        local = new MsdfTextRenderer(null);
                        instance = local;
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            return local;
        }

        @JvmStatic
        public final void closeInstance() {
            MsdfTextRenderer local = instance;
            if (local != null) {
                local.close();
                instance = null;
            }
        }

        @JvmStatic
        public final void clearResourceCaches() {
            MsdfTextRenderer local = instance;
            if (local != null) {
                local.clearCaches();
                return;
            }
            MsdfFonts.clear();
        }

        private final int packShimmerParams(float progress, float halfWidth, float intensity, float opacity) {
            int a = this.toByte(opacity);
            int r = this.toByte((progress + 0.5f) / 2.0f);
            int g = this.toByte(halfWidth);
            int b = this.toByte(intensity);
            return a << 24 | r << 16 | g << 8 | b;
        }

        private final float packLineColor(float linePos, int color) {
            int lq = Math.round(this.clamp01(linePos) * 63.0f);
            int rq = Math.round((float)(color >>> 16 & 0xFF) / 255.0f * 63.0f);
            int gq = Math.round((float)(color >>> 8 & 0xFF) / 255.0f * 63.0f);
            int bq = Math.round((float)(color & 0xFF) / 255.0f * 63.0f);
            return lq + bq * 64 + gq * 4096 + rq * 262144;
        }

        private final int toByte(float value) {
            return Math.round(this.clamp01(value) * 255.0f);
        }

        private final float clamp01(float value) {
            return Math.max(0.0f, Math.min(1.0f, value));
        }

        private final float smoothstep(float value) {
            return value * value * (3.0f - 2.0f * value);
        }

        private final Identifier id(String path) {
            Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
            Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
            return identifier2;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000e\n\u0002\b\u0016\b\u0082\b\u0018\u00002\u00020\u0001Bc\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u0004\u0012\u0006\u0010\u000f\u001a\u00020\f\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0012\u001a\u00020\f\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0012\u0010\u001f\u001a\u0004\u0018\u00010\nH\u00c6\u0003\u00a2\u0006\u0004\b\u001f\u0010 J\u0010\u0010!\u001a\u00020\fH\u00c6\u0003\u00a2\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b#\u0010\u001aJ\u0010\u0010$\u001a\u00020\fH\u00c6\u0003\u00a2\u0006\u0004\b$\u0010\"J\u0010\u0010%\u001a\u00020\u0010H\u00c6\u0003\u00a2\u0006\u0004\b%\u0010&J\u0010\u0010'\u001a\u00020\fH\u00c6\u0003\u00a2\u0006\u0004\b'\u0010\"J\u0012\u0010(\u001a\u0004\u0018\u00010\u0013H\u00c6\u0003\u00a2\u0006\u0004\b(\u0010)J\u0082\u0001\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\f2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u00c6\u0001\u00a2\u0006\u0004\b*\u0010+J\u001b\u0010-\u001a\u00020\f2\b\u0010,\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b-\u0010.J\u0011\u0010/\u001a\u00020\u0004H\u00d6\u0081\u0004\u00a2\u0006\u0004\b/\u0010\u001aJ\u0011\u00101\u001a\u000200H\u00d6\u0081\u0004\u00a2\u0006\u0004\b1\u00102R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u00103\u001a\u0004\b4\u0010\u0018R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u00105\u001a\u0004\b6\u0010\u001aR\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u00107\u001a\u0004\b8\u0010\u001cR\u0017\u0010\t\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u00109\u001a\u0004\b:\u0010\u001eR\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010;\u001a\u0004\b<\u0010 R\u0017\u0010\r\u001a\u00020\f8\u0006\u00a2\u0006\f\n\u0004\b\r\u0010=\u001a\u0004\b>\u0010\"R\u0017\u0010\u000e\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u000e\u00105\u001a\u0004\b?\u0010\u001aR\u0017\u0010\u000f\u001a\u00020\f8\u0006\u00a2\u0006\f\n\u0004\b\u000f\u0010=\u001a\u0004\b@\u0010\"R\u0017\u0010\u0011\u001a\u00020\u00108\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010A\u001a\u0004\bB\u0010&R\u0017\u0010\u0012\u001a\u00020\f8\u0006\u00a2\u0006\f\n\u0004\b\u0012\u0010=\u001a\u0004\bC\u0010\"R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006\u00a2\u0006\f\n\u0004\b\u0014\u0010D\u001a\u0004\bE\u0010)\u00a8\u0006F"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderer$FrameBatchKey;", "", "Lnet/minecraft/GuiRenderState;", "state", "", "layerSerial", "Lnet/minecraft/Identifier;", "atlas", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderer$PoseKey;", "pose", "Lnet/minecraft/ScreenRect;", "scissorArea", "", "shimmer", "shimmerParams", "wave", "", "wavePhase", "adaptiveBackground", "Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "backgroundCapture", "<init>", "(Lnet/minecraft/GuiRenderState;ILnet/minecraft/Identifier;Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderer$PoseKey;Lnet/minecraft/ScreenRect;ZIZFZLrtx/kimiko/utils/render/render2d/blur/BlurCapture;)V", "component1", "()Lnet/minecraft/GuiRenderState;", "component2", "()I", "component3", "()Lnet/minecraft/Identifier;", "component4", "()Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderer$PoseKey;", "component5", "()Lnet/minecraft/ScreenRect;", "component6", "()Z", "component7", "component8", "component9", "()F", "component10", "component11", "()Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "copy", "(Lnet/minecraft/GuiRenderState;ILnet/minecraft/Identifier;Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderer$PoseKey;Lnet/minecraft/ScreenRect;ZIZFZLrtx/kimiko/utils/render/render2d/blur/BlurCapture;)Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderer$FrameBatchKey;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/GuiRenderState;", "getState", "I", "getLayerSerial", "Lnet/minecraft/Identifier;", "getAtlas", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderer$PoseKey;", "getPose", "Lnet/minecraft/ScreenRect;", "getScissorArea", "Z", "getShimmer", "getShimmerParams", "getWave", "F", "getWavePhase", "getAdaptiveBackground", "Lrtx/kimiko/utils/render/render2d/blur/BlurCapture;", "getBackgroundCapture", "rtx.kimiko:kimiko"})
    private static final class FrameBatchKey {
        @NotNull
        private final GuiRenderState state;
        private final int layerSerial;
        @NotNull
        private final Identifier atlas;
        @NotNull
        private final PoseKey pose;
        @Nullable
        private final ScreenRect scissorArea;
        private final boolean shimmer;
        private final int shimmerParams;
        private final boolean wave;
        private final float wavePhase;
        private final boolean adaptiveBackground;
        @Nullable
        private final BlurCapture backgroundCapture;

        public FrameBatchKey(@NotNull GuiRenderState state, int layerSerial, @NotNull Identifier atlas, @NotNull PoseKey pose, @Nullable ScreenRect scissorArea, boolean shimmer, int shimmerParams, boolean wave, float wavePhase, boolean adaptiveBackground, @Nullable BlurCapture backgroundCapture) {
            Intrinsics.checkNotNullParameter((Object)state, (String)"state");
            Intrinsics.checkNotNullParameter((Object)atlas, (String)"atlas");
            Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
            this.state = state;
            this.layerSerial = layerSerial;
            this.atlas = atlas;
            this.pose = pose;
            this.scissorArea = scissorArea;
            this.shimmer = shimmer;
            this.shimmerParams = shimmerParams;
            this.wave = wave;
            this.wavePhase = wavePhase;
            this.adaptiveBackground = adaptiveBackground;
            this.backgroundCapture = backgroundCapture;
        }

        @NotNull
        public final GuiRenderState getState() {
            return this.state;
        }

        public final int getLayerSerial() {
            return this.layerSerial;
        }

        @NotNull
        public final Identifier getAtlas() {
            return this.atlas;
        }

        @NotNull
        public final PoseKey getPose() {
            return this.pose;
        }

        @Nullable
        public final ScreenRect getScissorArea() {
            return this.scissorArea;
        }

        public final boolean getShimmer() {
            return this.shimmer;
        }

        public final int getShimmerParams() {
            return this.shimmerParams;
        }

        public final boolean getWave() {
            return this.wave;
        }

        public final float getWavePhase() {
            return this.wavePhase;
        }

        public final boolean getAdaptiveBackground() {
            return this.adaptiveBackground;
        }

        @Nullable
        public final BlurCapture getBackgroundCapture() {
            return this.backgroundCapture;
        }

        @NotNull
        public final GuiRenderState component1() {
            return this.state;
        }

        public final int component2() {
            return this.layerSerial;
        }

        @NotNull
        public final Identifier component3() {
            return this.atlas;
        }

        @NotNull
        public final PoseKey component4() {
            return this.pose;
        }

        @Nullable
        public final ScreenRect component5() {
            return this.scissorArea;
        }

        public final boolean component6() {
            return this.shimmer;
        }

        public final int component7() {
            return this.shimmerParams;
        }

        public final boolean component8() {
            return this.wave;
        }

        public final float component9() {
            return this.wavePhase;
        }

        public final boolean component10() {
            return this.adaptiveBackground;
        }

        @Nullable
        public final BlurCapture component11() {
            return this.backgroundCapture;
        }

        @NotNull
        public final FrameBatchKey copy(@NotNull GuiRenderState state, int layerSerial, @NotNull Identifier atlas, @NotNull PoseKey pose, @Nullable ScreenRect scissorArea, boolean shimmer, int shimmerParams, boolean wave, float wavePhase, boolean adaptiveBackground, @Nullable BlurCapture backgroundCapture) {
            Intrinsics.checkNotNullParameter((Object)state, (String)"state");
            Intrinsics.checkNotNullParameter((Object)atlas, (String)"atlas");
            Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
            return new FrameBatchKey(state, layerSerial, atlas, pose, scissorArea, shimmer, shimmerParams, wave, wavePhase, adaptiveBackground, backgroundCapture);
        }

        public static /* synthetic */ FrameBatchKey copy$default(FrameBatchKey frameBatchKey, GuiRenderState guiRenderState2, int n, Identifier identifier2, PoseKey poseKey, ScreenRect screenRect2, boolean bl, int n2, boolean bl2, float f, boolean bl3, BlurCapture blurCapture, int n3, Object object) {
            if ((n3 & 1) != 0) {
                guiRenderState2 = frameBatchKey.state;
            }
            if ((n3 & 2) != 0) {
                n = frameBatchKey.layerSerial;
            }
            if ((n3 & 4) != 0) {
                identifier2 = frameBatchKey.atlas;
            }
            if ((n3 & 8) != 0) {
                poseKey = frameBatchKey.pose;
            }
            if ((n3 & 0x10) != 0) {
                screenRect2 = frameBatchKey.scissorArea;
            }
            if ((n3 & 0x20) != 0) {
                bl = frameBatchKey.shimmer;
            }
            if ((n3 & 0x40) != 0) {
                n2 = frameBatchKey.shimmerParams;
            }
            if ((n3 & 0x80) != 0) {
                bl2 = frameBatchKey.wave;
            }
            if ((n3 & 0x100) != 0) {
                f = frameBatchKey.wavePhase;
            }
            if ((n3 & 0x200) != 0) {
                bl3 = frameBatchKey.adaptiveBackground;
            }
            if ((n3 & 0x400) != 0) {
                blurCapture = frameBatchKey.backgroundCapture;
            }
            return frameBatchKey.copy(guiRenderState2, n, identifier2, poseKey, screenRect2, bl, n2, bl2, f, bl3, blurCapture);
        }

        @NotNull
        public String toString() {
            return "FrameBatchKey(state=" + this.state + ", layerSerial=" + this.layerSerial + ", atlas=" + this.atlas + ", pose=" + this.pose + ", scissorArea=" + this.scissorArea + ", shimmer=" + this.shimmer + ", shimmerParams=" + this.shimmerParams + ", wave=" + this.wave + ", wavePhase=" + this.wavePhase + ", adaptiveBackground=" + this.adaptiveBackground + ", backgroundCapture=" + this.backgroundCapture + ")";
        }

        public int hashCode() {
            int result = this.state.hashCode();
            result = result * 31 + Integer.hashCode(this.layerSerial);
            result = result * 31 + this.atlas.hashCode();
            result = result * 31 + this.pose.hashCode();
            result = result * 31 + (this.scissorArea == null ? 0 : this.scissorArea.hashCode());
            result = result * 31 + Boolean.hashCode(this.shimmer);
            result = result * 31 + Integer.hashCode(this.shimmerParams);
            result = result * 31 + Boolean.hashCode(this.wave);
            result = result * 31 + Float.hashCode(this.wavePhase);
            result = result * 31 + Boolean.hashCode(this.adaptiveBackground);
            result = result * 31 + (this.backgroundCapture == null ? 0 : this.backgroundCapture.hashCode());
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FrameBatchKey)) {
                return false;
            }
            FrameBatchKey frameBatchKey = (FrameBatchKey)other;
            if (!Intrinsics.areEqual((Object)this.state, (Object)frameBatchKey.state)) {
                return false;
            }
            if (this.layerSerial != frameBatchKey.layerSerial) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.atlas, (Object)frameBatchKey.atlas)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.pose, (Object)frameBatchKey.pose)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.scissorArea, (Object)frameBatchKey.scissorArea)) {
                return false;
            }
            if (this.shimmer != frameBatchKey.shimmer) {
                return false;
            }
            if (this.shimmerParams != frameBatchKey.shimmerParams) {
                return false;
            }
            if (this.wave != frameBatchKey.wave) {
                return false;
            }
            if (Float.compare(this.wavePhase, frameBatchKey.wavePhase) != 0) {
                return false;
            }
            if (this.adaptiveBackground != frameBatchKey.adaptiveBackground) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.backgroundCapture, (Object)frameBatchKey.backgroundCapture);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0082\b\u0018\u0000 %2\u00020\u0001:\u0001%B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\fJ\u0010\u0010\u000e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\fJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\fJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\fJ\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\fJL\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0019\u001a\u00020\u0018H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0011\u0010\u001c\u001a\u00020\u001bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010\fR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001e\u001a\u0004\b \u0010\fR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b!\u0010\fR\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b\"\u0010\fR\u0017\u0010\u0007\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b#\u0010\fR\u0017\u0010\b\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u001e\u001a\u0004\b$\u0010\f\u00a8\u0006&"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderer$PoseKey;", "", "", "m00", "m01", "m10", "m11", "m20", "m21", "<init>", "(FFFFFF)V", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "copy", "(FFFFFF)Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderer$PoseKey;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "F", "getM00", "getM01", "getM10", "getM11", "getM20", "getM21", "Companion", "rtx.kimiko:kimiko"})
    private static final class PoseKey {
        @NotNull
        public static final Companion Companion = new Companion(null);
        private final float m00;
        private final float m01;
        private final float m10;
        private final float m11;
        private final float m20;
        private final float m21;

        public PoseKey(float m00, float m01, float m10, float m11, float m20, float m21) {
            this.m00 = m00;
            this.m01 = m01;
            this.m10 = m10;
            this.m11 = m11;
            this.m20 = m20;
            this.m21 = m21;
        }

        public final float getM00() {
            return this.m00;
        }

        public final float getM01() {
            return this.m01;
        }

        public final float getM10() {
            return this.m10;
        }

        public final float getM11() {
            return this.m11;
        }

        public final float getM20() {
            return this.m20;
        }

        public final float getM21() {
            return this.m21;
        }

        public final float component1() {
            return this.m00;
        }

        public final float component2() {
            return this.m01;
        }

        public final float component3() {
            return this.m10;
        }

        public final float component4() {
            return this.m11;
        }

        public final float component5() {
            return this.m20;
        }

        public final float component6() {
            return this.m21;
        }

        @NotNull
        public final PoseKey copy(float m00, float m01, float m10, float m11, float m20, float m21) {
            return new PoseKey(m00, m01, m10, m11, m20, m21);
        }

        public static /* synthetic */ PoseKey copy$default(PoseKey poseKey, float f, float f2, float f3, float f4, float f5, float f6, int n, Object object) {
            if ((n & 1) != 0) {
                f = poseKey.m00;
            }
            if ((n & 2) != 0) {
                f2 = poseKey.m01;
            }
            if ((n & 4) != 0) {
                f3 = poseKey.m10;
            }
            if ((n & 8) != 0) {
                f4 = poseKey.m11;
            }
            if ((n & 0x10) != 0) {
                f5 = poseKey.m20;
            }
            if ((n & 0x20) != 0) {
                f6 = poseKey.m21;
            }
            return poseKey.copy(f, f2, f3, f4, f5, f6);
        }

        @NotNull
        public String toString() {
            return "PoseKey(m00=" + this.m00 + ", m01=" + this.m01 + ", m10=" + this.m10 + ", m11=" + this.m11 + ", m20=" + this.m20 + ", m21=" + this.m21 + ")";
        }

        public int hashCode() {
            int result = Float.hashCode(this.m00);
            result = result * 31 + Float.hashCode(this.m01);
            result = result * 31 + Float.hashCode(this.m10);
            result = result * 31 + Float.hashCode(this.m11);
            result = result * 31 + Float.hashCode(this.m20);
            result = result * 31 + Float.hashCode(this.m21);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PoseKey)) {
                return false;
            }
            PoseKey poseKey = (PoseKey)other;
            if (Float.compare(this.m00, poseKey.m00) != 0) {
                return false;
            }
            if (Float.compare(this.m01, poseKey.m01) != 0) {
                return false;
            }
            if (Float.compare(this.m10, poseKey.m10) != 0) {
                return false;
            }
            if (Float.compare(this.m11, poseKey.m11) != 0) {
                return false;
            }
            if (Float.compare(this.m20, poseKey.m20) != 0) {
                return false;
            }
            return Float.compare(this.m21, poseKey.m21) == 0;
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderer$PoseKey.Companion;", "", "<init>", "()V", "Lorg/joml/Matrix3x2f;", "m", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderer$PoseKey;", "of", "(Lorg/joml/Matrix3x2f;)Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextRenderer$PoseKey;", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final PoseKey of(@NotNull Matrix3x2f m) {
                Intrinsics.checkNotNullParameter((Object)m, (String)"m");
                return new PoseKey(m.m00(), m.m01(), m.m10(), m.m11(), m.m20(), m.m21());
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }
}

