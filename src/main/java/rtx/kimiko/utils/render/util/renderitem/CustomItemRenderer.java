/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  com.mojang.blaze3d.pipeline.BlendFunction
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Snippet
 *  com.mojang.blaze3d.platform.DepthTestFunction
 *  com.mojang.blaze3d.systems.RenderPass
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.textures.FilterMode
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  com.mojang.blaze3d.vertex.VertexFormat
 *  com.mojang.blaze3d.vertex.VertexFormat$DrawMode
 *  com.mojang.blaze3d.vertex.VertexFormatElement
 *  kotlin.Metadata
 *  kotlin.NoWhenBranchMatchedException
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.comparisons.ComparisonsKt
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.client.texture.AbstractTexture
 *  net.minecraft.client.render.item.ItemRenderState
 *  net.minecraft.client.render.item.ItemRenderState.Glint
 *  net.minecraft.client.render.item.ItemRenderState.LayerRenderState
 *  net.minecraft.client.texture.TextureManager
 *  net.minecraft.client.gl.UniformType
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.client.gui.render.state.GuiRenderState
 *  net.minecraft.util.HeldItemContext
 *  net.minecraft.client.gl.GpuSampler
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraft.util.math.Direction
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.util.math.Vector2f
 *  net.minecraft.client.render.model.BakedQuad
 *  net.minecraft.client.render.model.json.Transformation
 *  net.minecraft.item.ItemDisplayContext
 *  net.minecraft.component.ComponentMap
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Vector3f
 *  org.joml.Vector3fc
 *  org.lwjgl.system.MemoryStack
 */
package rtx.kimiko.utils.render.util.renderitem;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import mixin.accessor.ItemLayerRenderStateAccessor;
import mixin.accessor.ItemStackRenderStateAccessor;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.util.HeldItemContext;
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.util.math.Direction;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector2f;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.json.Transformation;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.component.ComponentMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.lwjgl.system.MemoryStack;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.render.modules.post.GuiRenderStateLayerAccessor;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.util.renderitem.BuiltRenderItem;
import rtx.kimiko.utils.render.util.renderitem.CachedItemGeometry;
import rtx.kimiko.utils.render.util.renderitem.CachedItemQuad;
import rtx.kimiko.utils.render.util.renderitem.CustomItemRenderer;
import rtx.kimiko.utils.render.util.renderitem.ItemTexture;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0087\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0013*\u0001m\u0018\u0000 \u0080\u00012\u00060\u0001j\u0002`\u0002:\u0010\u0081\u0001\u0082\u0001\u0083\u0001\u0084\u0001\u0085\u0001\u0086\u0001\u0087\u0001\u0080\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0007\u00a2\u0006\u0004\b\u000e\u0010\u0004J\r\u0010\u000f\u001a\u00020\u0007\u00a2\u0006\u0004\b\u000f\u0010\u0004J\u0017\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u00a2\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0019\u0010\u0004J\u0017\u0010\u001f\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001aH\u0000\u00a2\u0006\u0004\b\u001d\u0010\u001eJ#\u0010 \u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b \u0010!J\u001f\u0010%\u001a\u00020$2\u0006\u0010#\u001a\u00020\"2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b%\u0010&J'\u0010*\u001a\u00020$2\u0006\u0010#\u001a\u00020\"2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b*\u0010+J\u001d\u0010/\u001a\u00020\u00122\f\u0010.\u001a\b\u0018\u00010,R\u00020-H\u0002\u00a2\u0006\u0004\b/\u00100J3\u00106\u001a\u00020\u00072\f\u00103\u001a\b\u0012\u0004\u0012\u000202012\u0006\u00105\u001a\u0002042\f\u0010.\u001a\b\u0018\u00010,R\u00020-H\u0002\u00a2\u0006\u0004\b6\u00107Jg\u0010G\u001a\u00020\u00072\u0006\u00109\u001a\u0002082\u0006\u0010:\u001a\u00020\u001c2\u0006\u0010<\u001a\u00020;2\u0006\u0010>\u001a\u00020=2\u0006\u0010@\u001a\u00020?2\u0006\u0010B\u001a\u00020A2\u0006\u0010C\u001a\u00020\u001a2\u0006\u0010D\u001a\u00020\u001a2\u0006\u0010E\u001a\u00020\u001a2\u0006\u0010F\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\bG\u0010HJ\u001f\u0010I\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\bI\u0010!J7\u0010P\u001a\u0002022\u0006\u0010B\u001a\u00020J2\u0006\u0010L\u001a\u00020K2\u0006\u0010M\u001a\u00020\u001c2\u0006\u0010N\u001a\u00020\u00122\u0006\u0010O\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\bP\u0010QJ\u001f\u0010S\u001a\u00020A2\u0006\u0010B\u001a\u0002022\u0006\u00105\u001a\u00020RH\u0002\u00a2\u0006\u0004\bS\u0010TJ\u0017\u0010W\u001a\u00020K2\u0006\u0010V\u001a\u00020UH\u0002\u00a2\u0006\u0004\bW\u0010XJ\u001b\u0010[\u001a\u0004\u0018\u00010?2\b\u0010Z\u001a\u0004\u0018\u00010YH\u0002\u00a2\u0006\u0004\b[\u0010\\J\u0011\u0010^\u001a\u0004\u0018\u00010]H\u0002\u00a2\u0006\u0004\b^\u0010_J\u0011\u0010`\u001a\u0004\u0018\u00010]H\u0002\u00a2\u0006\u0004\b`\u0010_J\u0017\u0010c\u001a\u00020b2\u0006\u0010(\u001a\u00020aH\u0002\u00a2\u0006\u0004\bc\u0010dJ\u000f\u0010e\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\be\u0010\u0004J\u000f\u0010f\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\bf\u0010\u0004J\r\u0010g\u001a\u00020\u0007\u00a2\u0006\u0004\bg\u0010\u0004R0\u0010k\u001a\u001e\u0012\u0004\u0012\u00020Y\u0012\u0004\u0012\u00020i0hj\u000e\u0012\u0004\u0012\u00020Y\u0012\u0004\u0012\u00020i`j8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bk\u0010lR\u0014\u0010n\u001a\u00020m8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bn\u0010oR \u0010s\u001a\u000e\u0012\u0004\u0012\u00020q\u0012\u0004\u0012\u00020r0p8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bs\u0010tR\u0014\u0010v\u001a\u00020u8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bv\u0010wR\u0016\u0010x\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bx\u0010yR\u0018\u0010z\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bz\u0010{R\u0018\u0010|\u001a\u0004\u0018\u00010]8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b|\u0010}R\u0016\u0010~\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b~\u0010\u007f\u00a8\u0006\u0088\u0001"}, d2={"Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "Lnet/minecraft/DrawContext;", "graphics", "", "beginFrame", "(Lnet/minecraft/DrawContext;)V", "Lrtx/kimiko/utils/render/util/renderitem/BuiltRenderItem;", "item", "enqueue", "(Lrtx/kimiko/utils/render/util/renderitem/BuiltRenderItem;)V", "flush", "beginGuiFrame", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "", "isItemPipeline", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)Z", "Lcom/mojang/blaze3d/systems/RenderPass;", "renderPass", "bindParams", "(Lcom/mojang/blaze3d/systems/RenderPass;)V", "prepareBuffers", "", "glintStrength", "", "reserve$rtx_kimiko_kimiko", "(F)I", "reserve", "submit", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/utils/render/util/renderitem/BuiltRenderItem;)V", "Lnet/minecraft/MinecraftClient;", "minecraft", "Lrtx/kimiko/utils/render/util/renderitem/CachedItemGeometry;", "resolveGeometry", "(Lnet/minecraft/MinecraftClient;Lrtx/kimiko/utils/render/util/renderitem/BuiltRenderItem;)Lrtx/kimiko/utils/render/util/renderitem/CachedItemGeometry;", "Lnet/minecraft/ItemStack;", "stack", "seed", "buildGeometry", "(Lnet/minecraft/MinecraftClient;Lnet/minecraft/ItemStack;I)Lrtx/kimiko/utils/render/util/renderitem/CachedItemGeometry;", "Lnet/minecraft/ItemRenderState$LayerRenderState;", "Lnet/minecraft/ItemRenderState;", "layer", "hasSpecialRenderer", "(Lnet/minecraft/ItemRenderState$LayerRenderState;)Z", "", "Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$RawItemQuad;", "output", "Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$Bounds;", "bounds", "collectLayerGeometry", "(Ljava/util/List;Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$Bounds;Lnet/minecraft/ItemRenderState$LayerRenderState;)V", "Lnet/minecraft/GuiRenderState;", "guiState", "layerSerial", "Lorg/joml/Matrix3x2f;", "pose", "Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$PoseKey;", "poseKey", "Lrtx/kimiko/utils/render/util/renderitem/ItemTexture;", "texture", "Lrtx/kimiko/utils/render/util/renderitem/CachedItemQuad;", "quad", "x", "y", "size", "color", "submitQuad", "(Lnet/minecraft/GuiRenderState;ILorg/joml/Matrix3x2f;Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$PoseKey;Lrtx/kimiko/utils/render/util/renderitem/ItemTexture;Lrtx/kimiko/utils/render/util/renderitem/CachedItemQuad;FFFIF)V", "submitSpecialItem", "Lnet/minecraft/BakedQuad;", "Lorg/joml/Matrix4f;", "transform", "tint", "foil", "shadeModel", "buildRawQuad", "(Lnet/minecraft/BakedQuad;Lorg/joml/Matrix4f;IZZ)Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$RawItemQuad;", "Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$NormalizedBounds;", "buildCachedQuad", "(Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$RawItemQuad;Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$NormalizedBounds;)Lrtx/kimiko/utils/render/util/renderitem/CachedItemQuad;", "Lmixin/accessor/ItemLayerRenderStateAccessor;", "accessor", "layerTransform", "(Lmixin/accessor/ItemLayerRenderStateAccessor;)Lorg/joml/Matrix4f;", "Lnet/minecraft/Identifier;", "atlas", "resolveTexture", "(Lnet/minecraft/Identifier;)Lrtx/kimiko/utils/render/util/renderitem/ItemTexture;", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "ensureParamsBuffer", "()Lcom/mojang/blaze3d/buffers/GpuBuffer;", "ensureWritableParamsBuffer", "Lorg/lwjgl/system/MemoryStack;", "Ljava/nio/ByteBuffer;", "buildUniformData", "(Lorg/lwjgl/system/MemoryStack;)Ljava/nio/ByteBuffer;", "closeParamsBuffer", "close", "clearCaches", "Ljava/util/HashMap;", "Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$CachedTexture;", "Lkotlin/collections/HashMap;", "textures", "Ljava/util/HashMap;", "rtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$geometryCache$1", "geometryCache", "Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$geometryCache$1;", "Ljava/util/LinkedHashMap;", "Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$FrameBatchKey;", "Lrtx/kimiko/utils/render/util/renderitem/ItemRenderState;", "frameBatches", "Ljava/util/LinkedHashMap;", "", "preparedGlints", "[F", "preparedQuadCount", "I", "activeGraphics", "Lnet/minecraft/DrawContext;", "paramsBuffer", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "paramsDirty", "Z", "Companion", "FrameBatchKey", "PoseKey", "CachedTexture", "GeometryCacheKey", "RawItemQuad", "Bounds", "NormalizedBounds", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nCustomItemRenderer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CustomItemRenderer.kt\nrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,707:1\n1174#2,2:708\n*S KotlinDebug\n*F\n+ 1 CustomItemRenderer.kt\nrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer\n*L\n202#1:708,2\n*E\n"})
public final class CustomItemRenderer
implements AutoCloseable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final HashMap<Identifier, CachedTexture> textures = new HashMap();
    @NotNull
    private final LinkedHashMap<GeometryCacheKey, CachedItemGeometry> geometryCache = new LinkedHashMap<GeometryCacheKey, CachedItemGeometry>(){

        protected boolean removeEldestEntry(Map.Entry<GeometryCacheKey, CachedItemGeometry> eldest) {
            Intrinsics.checkNotNullParameter(eldest, (String)"eldest");
            return this.size() > 512;
        }
    };
    @NotNull
    private final LinkedHashMap<FrameBatchKey, ItemRenderState> frameBatches = new LinkedHashMap(32);
    @NotNull
    private final float[] preparedGlints = new float[4096];
    private int preparedQuadCount;
    @Nullable
    private DrawContext activeGraphics;
    @Nullable
    private GpuBuffer paramsBuffer;
    private boolean paramsDirty;
    @NotNull
    private static final int[] EMPTY_TINTS = new int[0];
    private static final int MAX_ITEM_QUADS = 4096;
    private static final int MAX_GEOMETRY_CACHE_ENTRIES = 512;
    private static final int FLOATS_PER_PARAM = 4;
    private static final int UNIFORM_BYTES = 65536;
    private static final float MIN_VISIBLE_AREA = 2.0E-6f;
    private static final float MIN_BOUNDS_SPAN = 1.0E-4f;
    private static final float PRESERVED_MODEL_SCALE_CAP = 1.08f;
    private static final float PRESERVED_MODEL_DEPTH_EPSILON = 0.02f;
    private static final float SHADE_UP = 0.98f;
    private static final float SHADE_SIDE_LIGHT = 0.9f;
    private static final float SHADE_SIDE = 0.84f;
    private static final float SHADE_SIDE_DARK = 0.78f;
    private static final float SHADE_DOWN = 0.72f;
    @Nullable
    private static volatile CustomItemRenderer instance;
    @NotNull
    private static final VertexFormat ITEM_VERTEX_FORMAT;
    @JvmField
    @NotNull
    public static final RenderPipeline ITEM_PIPELINE;

    private CustomItemRenderer() {
    }

    public final void beginFrame(@Nullable DrawContext graphics) {
        if (this.activeGraphics != graphics) {
            this.frameBatches.clear();
        }
        this.activeGraphics = graphics;
    }

    public final void enqueue(@Nullable BuiltRenderItem item) {
        this.submit(this.activeGraphics, item);
    }

    public final void flush() {
        this.activeGraphics = null;
        this.frameBatches.clear();
    }

    public final void beginGuiFrame() {
        this.preparedQuadCount = 0;
        this.paramsDirty = false;
    }

    public final boolean isItemPipeline(@Nullable RenderPipeline pipeline) {
        return pipeline == ITEM_PIPELINE;
    }

    public final void bindParams(@Nullable RenderPass renderPass) {
        if (renderPass == null || this.preparedQuadCount == 0) {
            return;
        }
        GpuBuffer buffer = this.ensureParamsBuffer();
        if (buffer != null) {
            renderPass.setUniform("ItemParamsArray", buffer);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void prepareBuffers() {
        if (this.preparedQuadCount == 0 || !this.paramsDirty) {
            return;
        }
        GpuBuffer gpuBuffer = this.ensureWritableParamsBuffer();
        if (gpuBuffer == null) {
            return;
        }
        GpuBuffer buffer = gpuBuffer;
        try {
            AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
            Throwable throwable = null;
            try {
                MemoryStack stack = (MemoryStack)autoCloseable;
                boolean bl = false;
                Intrinsics.checkNotNull((Object)stack);
                ByteBuffer uniformData = this.buildUniformData(stack);
                RenderSystem.getDevice().createCommandEncoder().writeToBuffer(buffer.slice(0L, (long)uniformData.remaining()), uniformData);
                this.paramsDirty = false;
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
        catch (RuntimeException ignored) {
            this.paramsDirty = true;
        }
    }

    public final int reserve$rtx_kimiko_kimiko(float glintStrength) {
        int index = this.preparedQuadCount;
        if (index == 4096) {
            return -1;
        }
        this.preparedGlints[index] = glintStrength;
        int n = this.preparedQuadCount;
        this.preparedQuadCount = n + 1;
        this.paramsDirty = true;
        return index;
    }

    private final void submit(DrawContext graphics, BuiltRenderItem item) {
        if (graphics == null || item == null || !item.visible()) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        if (minecraft.getItemModelManager() == null) {
            return;
        }
        try {
            GuiRenderState guiState = ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState();
            Intrinsics.checkNotNull((Object)guiState, (String)"null cannot be cast to non-null type rtx.kimiko.utils.render.modules.post.GuiRenderStateLayerAccessor");
            int layerSerial = ((GuiRenderStateLayerAccessor)guiState).kimiko$getLayerSerial();
            Matrix3x2f pose = Render2DCoordinateSpace.pose(graphics);
            PoseKey poseKey = PoseKey.Companion.of(pose);
            CachedItemGeometry geometry = this.resolveGeometry(minecraft, item);
            if (geometry.getSpecialRenderer()) {
                this.submitSpecialItem(graphics, item);
                return;
            }
            for (CachedItemQuad cachedQuad : geometry.getQuads()) {
                ItemTexture texture = this.resolveTexture(cachedQuad.getAtlas());
                if (texture == null) continue;
                float glintStrength = item.options().glintMode().enabled$rtx_kimiko_kimiko(item.stack(), cachedQuad.getFoil()) ? item.options().glintStrength() : 0.0f;
                int color = CustomItemRenderer.Companion.multiplyColor(item.options().color(), cachedQuad.getTint(), item.options().alpha());
                this.submitQuad(guiState, layerSerial, pose, poseKey, texture, cachedQuad, item.x(), item.y(), item.size(), color, glintStrength);
            }
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    private final CachedItemGeometry resolveGeometry(MinecraftClient minecraft, BuiltRenderItem item) {
        GeometryCacheKey key = GeometryCacheKey.Companion.of(item.stack(), item.seed());
        CachedItemGeometry cached = (CachedItemGeometry)this.geometryCache.get((Object)key);
        if (cached != null) {
            return cached;
        }
        CachedItemGeometry geometry = this.buildGeometry(minecraft, item.stack(), item.seed());
        if (!geometry.getAnimated()) {
            ((Map)this.geometryCache).put(key, geometry);
        }
        return geometry;
    }

    private final CachedItemGeometry buildGeometry(MinecraftClient minecraft, ItemStack stack, int seed) {
        net.minecraft.client.render.item.ItemRenderState renderState = new net.minecraft.client.render.item.ItemRenderState();
        minecraft.getItemModelManager().clearAndUpdate(renderState, stack, ItemDisplayContext.GUI, (World)minecraft.world, (HeldItemContext)minecraft.player, seed);
        ItemStackRenderStateAccessor stateAccessor = (ItemStackRenderStateAccessor)renderState;
        net.minecraft.client.render.item.ItemRenderState.LayerRenderState[] layers = stateAccessor.kimiko$getLayers();
        int activeLayerCount = Math.min(stateAccessor.kimiko$getActiveLayerCount(), layers.length);
        for (int i = 0; i < activeLayerCount; ++i) {
            if (!this.hasSpecialRenderer(layers[i])) continue;
            return new CachedItemGeometry(CollectionsKt.emptyList(), renderState.isAnimated(), true);
        }
        ArrayList<RawItemQuad> rawQuads = new ArrayList<>(Math.max(8, activeLayerCount * 8));
        Bounds bounds = new Bounds();
        for (int i = 0; i < activeLayerCount; ++i) {
            this.collectLayerGeometry(rawQuads, bounds, layers[i]);
        }
        if (rawQuads.size() > 1) {
            rawQuads.sort(Comparator.comparingDouble(RawItemQuad::getDepth));
        }
        NormalizedBounds normalizedBounds = NormalizedBounds.Companion.of(bounds);
        ArrayList<CachedItemQuad> quads = new ArrayList<>(rawQuads.size());
        for (RawItemQuad rawQuad : rawQuads) {
            quads.add(this.buildCachedQuad(rawQuad, normalizedBounds));
        }
        List<CachedItemQuad> list = List.copyOf(quads);
        return new CachedItemGeometry(list, renderState.isAnimated(), false);
    }

    private final boolean hasSpecialRenderer(net.minecraft.client.render.item.ItemRenderState.LayerRenderState layer) {
        return layer != null && ((ItemLayerRenderStateAccessor)layer).kimiko$getSpecialRenderer() != null;
    }

    private final void collectLayerGeometry(List<RawItemQuad> output, Bounds bounds, net.minecraft.client.render.item.ItemRenderState.LayerRenderState layer) {
        if (layer == null) {
            return;
        }
        List list = layer.getQuads();
        Intrinsics.checkNotNullExpressionValue((Object)list, (String)"prepareQuadList(...)");
        List quads = list;
        if (quads.isEmpty()) {
            return;
        }
        ItemLayerRenderStateAccessor accessor = (ItemLayerRenderStateAccessor)layer;
        net.minecraft.client.render.item.ItemRenderState.Glint foilType = accessor.kimiko$getFoilType();
        boolean foil = foilType != net.minecraft.client.render.item.ItemRenderState.Glint.NONE;
        int[] nArray = accessor.kimiko$getTintLayers();
        if (nArray == null) {
            nArray = EMPTY_TINTS;
        }
        int[] tints = nArray;
        Matrix4f transform = this.layerTransform(accessor);
        boolean preserveModelScale = accessor.kimiko$getUsesBlockLight();
        if (preserveModelScale) {
            bounds.markPreserveModelScale();
        }
        for (Object e : quads) {
            Intrinsics.checkNotNullExpressionValue(e, (String)"next(...)");
            BakedQuad quad = (BakedQuad)e;
            RawItemQuad rawQuad = this.buildRawQuad(quad, transform, CustomItemRenderer.Companion.tintColor(quad, tints), foil, preserveModelScale);
            if (rawQuad.getArea() < 2.0E-6f) continue;
            bounds.include(rawQuad.getX0(), rawQuad.getY0(), rawQuad.getZ0());
            bounds.include(rawQuad.getX1(), rawQuad.getY1(), rawQuad.getZ1());
            bounds.include(rawQuad.getX2(), rawQuad.getY2(), rawQuad.getZ2());
            bounds.include(rawQuad.getX3(), rawQuad.getY3(), rawQuad.getZ3());
            output.add(rawQuad);
        }
    }

    private final void submitQuad(GuiRenderState guiState, int layerSerial, Matrix3x2f pose, PoseKey poseKey, ItemTexture texture, CachedItemQuad quad, float x, float y, float size, int color, float glintStrength) {
        FrameBatchKey key = new FrameBatchKey(guiState, layerSerial, texture.getId(), poseKey);
        ItemRenderState state = this.frameBatches.get(key);
        if (state == null) {
            ItemRenderState created = new ItemRenderState(pose, texture);
            created.add(quad, x, y, size, color, glintStrength);
            ((Map)this.frameBatches).put(key, created);
            guiState.addSimpleElement((SimpleGuiElementRenderState)created);
        } else {
            state.add(quad, x, y, size, color, glintStrength);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void submitSpecialItem(DrawContext graphics, BuiltRenderItem item) {
        float scale = item.size() / 16.0f;
        if (scale <= 0.0f) {
            return;
        }
        graphics.getMatrices().pushMatrix();
        try {
            Matrix3x2fStack matrix3x2fStack = graphics.getMatrices();
            Intrinsics.checkNotNullExpressionValue((Object)matrix3x2fStack, (String)"pose(...)");
            Render2DCoordinateSpace.applyGuiScaleIndependence((Matrix3x2f)matrix3x2fStack);
            graphics.getMatrices().translate(item.x(), item.y());
            graphics.getMatrices().scale(scale);
            graphics.drawItem(item.stack(), 0, 0, item.seed());
        }
        finally {
            graphics.getMatrices().popMatrix();
        }
    }

    private final RawItemQuad buildRawQuad(BakedQuad quad, Matrix4f transform, int tint, boolean foil, boolean shadeModel) {
        Vector3fc vector3fc = quad.position0();
        Intrinsics.checkNotNullExpressionValue((Object)vector3fc, (String)"position0(...)");
        Vector3fc p0 = vector3fc;
        Vector3fc vector3fc2 = quad.position1();
        Intrinsics.checkNotNullExpressionValue((Object)vector3fc2, (String)"position1(...)");
        Vector3fc p1 = vector3fc2;
        Vector3fc vector3fc3 = quad.position2();
        Intrinsics.checkNotNullExpressionValue((Object)vector3fc3, (String)"position2(...)");
        Vector3fc p2 = vector3fc3;
        Vector3fc vector3fc4 = quad.position3();
        Intrinsics.checkNotNullExpressionValue((Object)vector3fc4, (String)"position3(...)");
        Vector3fc p3 = vector3fc4;
        Vector3f t0 = CustomItemRenderer.Companion.transformPosition(p0, transform);
        Vector3f t1 = CustomItemRenderer.Companion.transformPosition(p1, transform);
        Vector3f t2 = CustomItemRenderer.Companion.transformPosition(p2, transform);
        Vector3f t3 = CustomItemRenderer.Companion.transformPosition(p3, transform);
        long uv0 = quad.packedUV0();
        long uv1 = quad.packedUV1();
        long uv2 = quad.packedUV2();
        long uv3 = quad.packedUV3();
        float depth = (t0.z + t1.z + t2.z + t3.z) * 0.25f;
        int shadedTint = shadeModel ? CustomItemRenderer.Companion.shadeColor(tint, CustomItemRenderer.Companion.faceShade(quad.face())) : tint;
        return new RawItemQuad(quad.sprite().getAtlasId(), t0.x, t0.y, Vector2f.getX((long)uv0), Vector2f.getY((long)uv0), t1.x, t1.y, Vector2f.getX((long)uv1), Vector2f.getY((long)uv1), t2.x, t2.y, Vector2f.getX((long)uv2), Vector2f.getY((long)uv2), t3.x, t3.y, Vector2f.getX((long)uv3), Vector2f.getY((long)uv3), shadedTint, foil, depth, CustomItemRenderer.Companion.projectedArea(t0.x, t0.y, t1.x, t1.y, t2.x, t2.y, t3.x, t3.y), t0.z, t1.z, t2.z, t3.z);
    }

    private final CachedItemQuad buildCachedQuad(RawItemQuad quad, NormalizedBounds bounds) {
        return new CachedItemQuad(quad.getAtlas(), bounds.x(quad.getX0()), bounds.y(quad.getY0()), quad.getU0(), quad.getV0(), bounds.x(quad.getX1()), bounds.y(quad.getY1()), quad.getU1(), quad.getV1(), bounds.x(quad.getX2()), bounds.y(quad.getY2()), quad.getU2(), quad.getV2(), bounds.x(quad.getX3()), bounds.y(quad.getY3()), quad.getU3(), quad.getV3(), quad.getTint(), quad.getFoil());
    }

    private final Matrix4f layerTransform(ItemLayerRenderStateAccessor accessor) {
        MatrixStack.Entry pose = new MatrixStack.Entry();
        Transformation transformation2 = accessor.kimiko$getItemTransform();
        if (transformation2 == null) {
            Transformation transformation3 = Transformation.IDENTITY;
            transformation2 = transformation3;
            Intrinsics.checkNotNullExpressionValue((Object)transformation3, (String)"NO_TRANSFORM");
        }
        Transformation transform = transformation2;
        transform.apply(false, pose);
        return new Matrix4f((Matrix4fc)pose.getPositionMatrix());
    }

    private final ItemTexture resolveTexture(Identifier atlas) {
        if (atlas == null) {
            return null;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        if (minecraftClient2 == null) {
            return null;
        }
        MinecraftClient minecraft = minecraftClient2;
        TextureManager textureManager2 = minecraft.getTextureManager();
        if (textureManager2 == null) {
            return null;
        }
        TextureManager textureManager = textureManager2;
        AbstractTexture texture = textureManager.getTexture(atlas);
        if (texture == null || texture.getGlTextureView() == null) {
            return null;
        }
        CachedTexture cached = this.textures.get(atlas);
        if (cached != null && cached.getTexture() == texture) {
            return cached.getValue();
        }
        GpuTextureView gpuTextureView = texture.getGlTextureView();
        Intrinsics.checkNotNull((Object)gpuTextureView);
        TextureSetup textureSetup2 = TextureSetup.of((GpuTextureView)gpuTextureView, (GpuSampler)RenderSystem.getSamplerCache().get(FilterMode.NEAREST));
        Intrinsics.checkNotNullExpressionValue((Object)textureSetup2, (String)"singleTexture(...)");
        TextureSetup setup = textureSetup2;
        ItemTexture value = new ItemTexture(atlas, setup);
        ((Map)this.textures).put(atlas, new CachedTexture(texture, value));
        return value;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final GpuBuffer ensureParamsBuffer() {
        GpuBuffer gpuBuffer;
        if (!this.paramsDirty && this.paramsBuffer != null) {
            return this.paramsBuffer;
        }
        this.prepareBuffers();
        if (!this.paramsDirty && this.paramsBuffer != null) {
            return this.paramsBuffer;
        }
        this.closeParamsBuffer();
        AutoCloseable autoCloseable = (AutoCloseable)MemoryStack.stackPush();
        Throwable throwable = null;
        try {
            GpuBuffer created;
            MemoryStack stack = (MemoryStack)autoCloseable;
            boolean bl = false;
            Intrinsics.checkNotNull((Object)stack);
            ByteBuffer uniformData = this.buildUniformData(stack);
            GpuBuffer gpuBuffer2 = RenderSystem.getDevice().createBuffer(CustomItemRenderer::ensureParamsBuffer$lambda$0$0, 128, uniformData);
            Intrinsics.checkNotNullExpressionValue((Object)gpuBuffer2, (String)"createBuffer(...)");
            this.paramsBuffer = created = gpuBuffer2;
            this.paramsDirty = false;
            gpuBuffer = created;
        }
        catch (Throwable throwable2) {
            throwable = throwable2;
            throw throwable2;
        }
        finally {
            AutoCloseableKt.closeFinally((AutoCloseable)autoCloseable, (Throwable)throwable);
        }
        return gpuBuffer;
    }

    private final GpuBuffer ensureWritableParamsBuffer() {
        GpuBuffer current = this.paramsBuffer;
        if (current != null && !current.isClosed() && current.size() >= 65536L) {
            return current;
        }
        this.closeParamsBuffer();
        try {
            GpuBuffer created = RenderSystem.getDevice().createBuffer(CustomItemRenderer::ensureWritableParamsBuffer$lambda$0, 136, 65536L);
            this.paramsBuffer = created;
            return created;
        }
        catch (RuntimeException ignored) {
            return null;
        }
    }

    private final ByteBuffer buildUniformData(MemoryStack stack) {
        ByteBuffer data = stack.calloc(65536);
        float time = (float)(System.nanoTime() % 30000000000L) / 1.0E9f;
        int n = this.preparedQuadCount;
        for (int i = 0; i < n; ++i) {
            int offset = i * 4 * 4;
            data.putFloat(offset, this.preparedGlints[i]);
            data.putFloat(offset + 4, time);
            data.putFloat(offset + 8, 0.0f);
            data.putFloat(offset + 12, 0.0f);
        }
        data.position(0);
        Intrinsics.checkNotNull((Object)data);
        return data;
    }

    private final void closeParamsBuffer() {
        GpuBuffer gpuBuffer = this.paramsBuffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        this.paramsBuffer = null;
    }

    @Override
    public void close() {
        this.frameBatches.clear();
        this.preparedQuadCount = 0;
        this.geometryCache.clear();
        this.textures.clear();
        this.activeGraphics = null;
        this.closeParamsBuffer();
    }

    public final void clearCaches() {
        this.geometryCache.clear();
        this.textures.clear();
    }

    private static final String ensureParamsBuffer$lambda$0$0() {
        return "kimiko_item_params";
    }

    private static final String ensureWritableParamsBuffer$lambda$0() {
        return "kimiko_item_params";
    }

    @JvmStatic
    @NotNull
    public static final CustomItemRenderer getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final void closeInstance() {
        Companion.closeInstance();
    }

    public /* synthetic */ CustomItemRenderer(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    static {
        VertexFormat vertexFormat = VertexFormat.builder().add("Position", VertexFormatElement.POSITION).add("UV0", VertexFormatElement.UV0).add("Color", VertexFormatElement.COLOR).add("LineWidth", VertexFormatElement.LINE_WIDTH).build();
        Intrinsics.checkNotNullExpressionValue((Object)vertexFormat, (String)"build(...)");
        ITEM_VERTEX_FORMAT = vertexFormat;
        RenderPipeline renderPipeline = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(CustomItemRenderer.Companion.id("pipeline/item")).withVertexShader(CustomItemRenderer.Companion.id("core/item")).withFragmentShader(CustomItemRenderer.Companion.id("core/item")).withSampler("Sampler0").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withUniform("ItemParamsArray", UniformType.UNIFORM_BUFFER).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).withVertexFormat(ITEM_VERTEX_FORMAT, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline, (String)"build(...)");
        ITEM_PIPELINE = renderPipeline;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001c\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J%\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\b\u00a2\u0006\u0004\b\u000e\u0010\u0003J\r\u0010\u000f\u001a\u00020\u000b\u00a2\u0006\u0004\b\u000f\u0010\rR\"\u0010\u0010\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\"\u0010\u0016\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R\"\u0010\u0019\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0019\u0010\u0011\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R\"\u0010\u001c\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001c\u0010\u0011\u001a\u0004\b\u001d\u0010\u0013\"\u0004\b\u001e\u0010\u0015R\"\u0010\u001f\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001f\u0010\u0011\u001a\u0004\b \u0010\u0013\"\u0004\b!\u0010\u0015R\"\u0010\"\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\"\u0010\u0011\u001a\u0004\b#\u0010\u0013\"\u0004\b$\u0010\u0015R\u0016\u0010%\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u0010&\u00a8\u0006'"}, d2={"Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$Bounds;", "", "<init>", "()V", "", "x", "y", "z", "", "include", "(FFF)V", "", "defined", "()Z", "markPreserveModelScale", "preserveModelScale", "minX", "F", "getMinX", "()F", "setMinX", "(F)V", "minY", "getMinY", "setMinY", "minZ", "getMinZ", "setMinZ", "maxX", "getMaxX", "setMaxX", "maxY", "getMaxY", "setMaxY", "maxZ", "getMaxZ", "setMaxZ", "preserveModelScaleFlag", "Z", "rtx.kimiko:kimiko"})
    private static final class Bounds {
        private float minX = Float.MAX_VALUE;
        private float minY = Float.MAX_VALUE;
        private float minZ = Float.MAX_VALUE;
        private float maxX = -3.4028235E38f;
        private float maxY = -3.4028235E38f;
        private float maxZ = -3.4028235E38f;
        private boolean preserveModelScaleFlag;

        public final float getMinX() {
            return this.minX;
        }

        public final void setMinX(float f) {
            this.minX = f;
        }

        public final float getMinY() {
            return this.minY;
        }

        public final void setMinY(float f) {
            this.minY = f;
        }

        public final float getMinZ() {
            return this.minZ;
        }

        public final void setMinZ(float f) {
            this.minZ = f;
        }

        public final float getMaxX() {
            return this.maxX;
        }

        public final void setMaxX(float f) {
            this.maxX = f;
        }

        public final float getMaxY() {
            return this.maxY;
        }

        public final void setMaxY(float f) {
            this.maxY = f;
        }

        public final float getMaxZ() {
            return this.maxZ;
        }

        public final void setMaxZ(float f) {
            this.maxZ = f;
        }

        public final void include(float x, float y, float z) {
            this.minX = Math.min(this.minX, x);
            this.minY = Math.min(this.minY, y);
            this.minZ = Math.min(this.minZ, z);
            this.maxX = Math.max(this.maxX, x);
            this.maxY = Math.max(this.maxY, y);
            this.maxZ = Math.max(this.maxZ, z);
        }

        public final boolean defined() {
            return this.minX <= this.maxX && this.minY <= this.maxY;
        }

        public final void markPreserveModelScale() {
            this.preserveModelScaleFlag = true;
        }

        public final boolean preserveModelScale() {
            return this.preserveModelScaleFlag || this.maxZ - this.minZ > 0.02f;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ$\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0016\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u000b\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$CachedTexture;", "", "Lnet/minecraft/AbstractTexture;", "texture", "Lrtx/kimiko/utils/render/util/renderitem/ItemTexture;", "value", "<init>", "(Lnet/minecraft/AbstractTexture;Lrtx/kimiko/utils/render/util/renderitem/ItemTexture;)V", "component1", "()Lnet/minecraft/AbstractTexture;", "component2", "()Lrtx/kimiko/utils/render/util/renderitem/ItemTexture;", "copy", "(Lnet/minecraft/AbstractTexture;Lrtx/kimiko/utils/render/util/renderitem/ItemTexture;)Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$CachedTexture;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/AbstractTexture;", "getTexture", "Lrtx/kimiko/utils/render/util/renderitem/ItemTexture;", "getValue", "rtx.kimiko:kimiko"})
    private static final class CachedTexture {
        @NotNull
        private final AbstractTexture texture;
        @NotNull
        private final ItemTexture value;

        public CachedTexture(@NotNull AbstractTexture texture, @NotNull ItemTexture value) {
            Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
            Intrinsics.checkNotNullParameter((Object)value, (String)"value");
            this.texture = texture;
            this.value = value;
        }

        @NotNull
        public final AbstractTexture getTexture() {
            return this.texture;
        }

        @NotNull
        public final ItemTexture getValue() {
            return this.value;
        }

        @NotNull
        public final AbstractTexture component1() {
            return this.texture;
        }

        @NotNull
        public final ItemTexture component2() {
            return this.value;
        }

        @NotNull
        public final CachedTexture copy(@NotNull AbstractTexture texture, @NotNull ItemTexture value) {
            Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
            Intrinsics.checkNotNullParameter((Object)value, (String)"value");
            return new CachedTexture(texture, value);
        }

        public static /* synthetic */ CachedTexture copy$default(CachedTexture cachedTexture, AbstractTexture abstractTexture3, ItemTexture itemTexture, int n, Object object) {
            if ((n & 1) != 0) {
                abstractTexture3 = cachedTexture.texture;
            }
            if ((n & 2) != 0) {
                itemTexture = cachedTexture.value;
            }
            return cachedTexture.copy(abstractTexture3, itemTexture);
        }

        @NotNull
        public String toString() {
            return "CachedTexture(texture=" + this.texture + ", value=" + this.value + ")";
        }

        public int hashCode() {
            int result = this.texture.hashCode();
            result = result * 31 + this.value.hashCode();
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
            return Intrinsics.areEqual((Object)this.value, (Object)cachedTexture.value);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J'\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b\u001f\u0010 JO\u0010)\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b)\u0010*J/\u0010/\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b/\u00100J\u0019\u00103\u001a\u00020\u001a2\b\u00102\u001a\u0004\u0018\u000101H\u0002\u00a2\u0006\u0004\b3\u00104J\u001f\u00106\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00152\u0006\u00105\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b6\u00107J'\u0010;\u001a\u00020\u00152\u0006\u00108\u001a\u00020\u00152\u0006\u00109\u001a\u00020\u00152\u0006\u0010:\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b;\u0010<J\u0017\u0010@\u001a\u00020?2\u0006\u0010>\u001a\u00020=H\u0002\u00a2\u0006\u0004\b@\u0010AR\u0014\u0010B\u001a\u00020\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0014\u0010D\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bD\u0010ER\u0014\u0010F\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bF\u0010ER\u0014\u0010G\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bG\u0010ER\u0014\u0010H\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bH\u0010ER\u0014\u0010I\u001a\u00020\u001a8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bI\u0010JR\u0014\u0010K\u001a\u00020\u001a8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bK\u0010JR\u0014\u0010L\u001a\u00020\u001a8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bL\u0010JR\u0014\u0010M\u001a\u00020\u001a8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bM\u0010JR\u0014\u0010N\u001a\u00020\u001a8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bN\u0010JR\u0014\u0010O\u001a\u00020\u001a8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bO\u0010JR\u0014\u0010P\u001a\u00020\u001a8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bP\u0010JR\u0014\u0010Q\u001a\u00020\u001a8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bQ\u0010JR\u0014\u0010R\u001a\u00020\u001a8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bR\u0010JR\u0018\u0010S\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bS\u0010TR\u0014\u0010V\u001a\u00020U8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bV\u0010WR\u0019\u0010Z\u001a\u00020X8\u0006X\u0087\u0004\u0092\u0002\u0002\bY\u00a2\u0006\u0006\n\u0004\bZ\u0010[\u00a8\u0006\\"}, d2={"Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer;", "", "closeInstance", "Lorg/joml/Vector3fc;", "position", "Lorg/joml/Matrix4f;", "transform", "Lorg/joml/Vector3f;", "transformPosition", "(Lorg/joml/Vector3fc;Lorg/joml/Matrix4f;)Lorg/joml/Vector3f;", "Lnet/minecraft/BakedQuad;", "quad", "", "tints", "", "tintColor", "(Lnet/minecraft/BakedQuad;[I)I", "base", "tint", "", "alphaMultiplier", "multiplyColor", "(IIF)I", "color", "normalizeColor", "(I)I", "x0", "y0", "x1", "y1", "x2", "y2", "x3", "y3", "projectedArea", "(FFFFFFFF)F", "ax", "ay", "bx", "by", "cross", "(FFFF)F", "Lnet/minecraft/Direction;", "direction", "faceShade", "(Lnet/minecraft/Direction;)F", "shade", "shadeColor", "(IF)I", "value", "min", "max", "clampInt", "(III)I", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "EMPTY_TINTS", "[I", "MAX_ITEM_QUADS", "I", "MAX_GEOMETRY_CACHE_ENTRIES", "FLOATS_PER_PARAM", "UNIFORM_BYTES", "MIN_VISIBLE_AREA", "F", "MIN_BOUNDS_SPAN", "PRESERVED_MODEL_SCALE_CAP", "PRESERVED_MODEL_DEPTH_EPSILON", "SHADE_UP", "SHADE_SIDE_LIGHT", "SHADE_SIDE", "SHADE_SIDE_DARK", "SHADE_DOWN", "instance", "Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer;", "Lcom/mojang/blaze3d/vertex/VertexFormat;", "ITEM_VERTEX_FORMAT", "Lcom/mojang/blaze3d/vertex/VertexFormat;", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lkotlin/jvm/JvmField;", "ITEM_PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        @NotNull
        public final CustomItemRenderer getInstance() {
            CustomItemRenderer local = null;
            local = instance;
            if (local == null) {
                Class<CustomItemRenderer> clazz = CustomItemRenderer.class;
                synchronized (clazz) {
                    boolean bl = false;
                    local = instance;
                    if (local == null) {
                        local = new CustomItemRenderer(null);
                        instance = local;
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            return local;
        }

        @JvmStatic
        public final void closeInstance() {
            CustomItemRenderer local = instance;
            if (local != null) {
                local.close();
                instance = null;
            }
        }

        private final Vector3f transformPosition(Vector3fc position, Matrix4f transform) {
            Vector3f vector3f = new Vector3f(position).mulPosition((Matrix4fc)transform);
            Intrinsics.checkNotNullExpressionValue((Object)vector3f, (String)"mulPosition(...)");
            return vector3f;
        }

        private final int tintColor(BakedQuad quad, int[] tints) {
            int tintIndex;
            int tint = -1;
            if (quad.hasTint() && (tintIndex = quad.tintIndex()) >= 0 && tintIndex < tints.length) {
                tint = this.normalizeColor(tints[tintIndex]);
            }
            return tint;
        }

        private final int multiplyColor(int base, int tint, float alphaMultiplier) {
            int normalizedBase = this.normalizeColor(base);
            int normalizedTint = this.normalizeColor(tint);
            int a = Math.round((float)(normalizedBase >>> 24 & 0xFF) * ((float)(normalizedTint >>> 24 & 0xFF) / 255.0f) * alphaMultiplier);
            int r = (normalizedBase >>> 16 & 0xFF) * (normalizedTint >>> 16 & 0xFF) / 255;
            int g = (normalizedBase >>> 8 & 0xFF) * (normalizedTint >>> 8 & 0xFF) / 255;
            int b = (normalizedBase & 0xFF) * (normalizedTint & 0xFF) / 255;
            return this.clampInt(a, 0, 255) << 24 | r << 16 | g << 8 | b;
        }

        private final int normalizeColor(int color) {
            if ((color & 0xFF000000) == 0 && (color & 0xFFFFFF) != 0) {
                return color | 0xFF000000;
            }
            return color;
        }

        private final float projectedArea(float x0, float y0, float x1, float y1, float x2, float y2, float x3, float y3) {
            return Math.abs(this.cross(x0, y0, x1, y1) + this.cross(x1, y1, x2, y2) + this.cross(x2, y2, x3, y3) + this.cross(x3, y3, x0, y0)) * 0.5f;
        }

        private final float cross(float ax, float ay, float bx, float by) {
            return ax * by - bx * ay;
        }

        private final float faceShade(Direction direction) {
            if (direction == null) {
                return 0.84f;
            }
            return switch (WhenMappings.$EnumSwitchMapping$0[direction.ordinal()]) {
                case 1 -> 0.98f;
                case 2 -> 0.72f;
                case 3 -> 0.9f;
                case 4 -> 0.84f;
                case 5, 6 -> 0.78f;
                default -> throw new NoWhenBranchMatchedException();
            };
        }

        private final int shadeColor(int color, float shade) {
            int normalized = this.normalizeColor(color);
            int a = normalized >>> 24 & 0xFF;
            int r = this.clampInt(Math.round((float)(normalized >>> 16 & 0xFF) * shade), 0, 255);
            int g = this.clampInt(Math.round((float)(normalized >>> 8 & 0xFF) * shade), 0, 255);
            int b = this.clampInt(Math.round((float)(normalized & 0xFF) * shade), 0, 255);
            return a << 24 | r << 16 | g << 8 | b;
        }

        private final int clampInt(int value, int min, int max) {
            return Math.max(min, Math.min(max, value));
        }

        private final Identifier id(String path) {
            Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
            Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
            return identifier2;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @Metadata(mv={2, 4, 0}, k=3, xi=48)
        public static final class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] nArray = new int[Direction.values().length];
                try {
                    nArray[Direction.UP.ordinal()] = 1;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Direction.DOWN.ordinal()] = 2;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Direction.NORTH.ordinal()] = 3;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Direction.SOUTH.ordinal()] = 4;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Direction.EAST.ordinal()] = 5;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Direction.WEST.ordinal()] = 6;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                $EnumSwitchMapping$0 = nArray;
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013J8\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\bH\u00c6\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0011\u0010\u001a\u001a\u00020\u0004H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001a\u0010\u000fJ\u0011\u0010\u001c\u001a\u00020\u001bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010\rR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010 \u001a\u0004\b!\u0010\u000fR\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\"\u001a\u0004\b#\u0010\u0011R\u0017\u0010\t\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u0010$\u001a\u0004\b%\u0010\u0013\u00a8\u0006&"}, d2={"Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$FrameBatchKey;", "", "Lnet/minecraft/GuiRenderState;", "state", "", "layerSerial", "Lnet/minecraft/Identifier;", "texture", "Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$PoseKey;", "pose", "<init>", "(Lnet/minecraft/GuiRenderState;ILnet/minecraft/Identifier;Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$PoseKey;)V", "component1", "()Lnet/minecraft/GuiRenderState;", "component2", "()I", "component3", "()Lnet/minecraft/Identifier;", "component4", "()Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$PoseKey;", "copy", "(Lnet/minecraft/GuiRenderState;ILnet/minecraft/Identifier;Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$PoseKey;)Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$FrameBatchKey;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/GuiRenderState;", "getState", "I", "getLayerSerial", "Lnet/minecraft/Identifier;", "getTexture", "Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$PoseKey;", "getPose", "rtx.kimiko:kimiko"})
    private static final class FrameBatchKey {
        @NotNull
        private final GuiRenderState state;
        private final int layerSerial;
        @NotNull
        private final Identifier texture;
        @NotNull
        private final PoseKey pose;

        public FrameBatchKey(@NotNull GuiRenderState state, int layerSerial, @NotNull Identifier texture, @NotNull PoseKey pose) {
            Intrinsics.checkNotNullParameter((Object)state, (String)"state");
            Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
            Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
            this.state = state;
            this.layerSerial = layerSerial;
            this.texture = texture;
            this.pose = pose;
        }

        @NotNull
        public final GuiRenderState getState() {
            return this.state;
        }

        public final int getLayerSerial() {
            return this.layerSerial;
        }

        @NotNull
        public final Identifier getTexture() {
            return this.texture;
        }

        @NotNull
        public final PoseKey getPose() {
            return this.pose;
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
            return this.texture;
        }

        @NotNull
        public final PoseKey component4() {
            return this.pose;
        }

        @NotNull
        public final FrameBatchKey copy(@NotNull GuiRenderState state, int layerSerial, @NotNull Identifier texture, @NotNull PoseKey pose) {
            Intrinsics.checkNotNullParameter((Object)state, (String)"state");
            Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
            Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
            return new FrameBatchKey(state, layerSerial, texture, pose);
        }

        public static /* synthetic */ FrameBatchKey copy$default(FrameBatchKey frameBatchKey, GuiRenderState guiRenderState2, int n, Identifier identifier2, PoseKey poseKey, int n2, Object object) {
            if ((n2 & 1) != 0) {
                guiRenderState2 = frameBatchKey.state;
            }
            if ((n2 & 2) != 0) {
                n = frameBatchKey.layerSerial;
            }
            if ((n2 & 4) != 0) {
                identifier2 = frameBatchKey.texture;
            }
            if ((n2 & 8) != 0) {
                poseKey = frameBatchKey.pose;
            }
            return frameBatchKey.copy(guiRenderState2, n, identifier2, poseKey);
        }

        @NotNull
        public String toString() {
            return "FrameBatchKey(state=" + this.state + ", layerSerial=" + this.layerSerial + ", texture=" + this.texture + ", pose=" + this.pose + ")";
        }

        public int hashCode() {
            int result = this.state.hashCode();
            result = result * 31 + Integer.hashCode(this.layerSerial);
            result = result * 31 + this.texture.hashCode();
            result = result * 31 + this.pose.hashCode();
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
            if (!Intrinsics.areEqual((Object)this.texture, (Object)frameBatchKey.texture)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.pose, (Object)frameBatchKey.pose);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0082\b\u0018\u0000 #2\u00020\u0001:\u0001#B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0010J8\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006H\u00c6\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0018\u001a\u00020\u0006H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0018\u0010\u0010J\u0011\u0010\u001a\u001a\u00020\u0019H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010\u000eR\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010 \u001a\u0004\b!\u0010\u0010R\u0017\u0010\b\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\b\u0010 \u001a\u0004\b\"\u0010\u0010\u00a8\u0006$"}, d2={"Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$GeometryCacheKey;", "", "Lnet/minecraft/Item;", "item", "Lnet/minecraft/ComponentMap;", "components", "", "count", "seed", "<init>", "(Lnet/minecraft/Item;Lnet/minecraft/ComponentMap;II)V", "component1", "()Lnet/minecraft/Item;", "component2", "()Lnet/minecraft/ComponentMap;", "component3", "()I", "component4", "copy", "(Lnet/minecraft/Item;Lnet/minecraft/ComponentMap;II)Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$GeometryCacheKey;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/Item;", "getItem", "Lnet/minecraft/ComponentMap;", "getComponents", "I", "getCount", "getSeed", "Companion", "rtx.kimiko:kimiko"})
    private static final class GeometryCacheKey {
        @NotNull
        public static final Companion Companion = new Companion(null);
        @NotNull
        private final Item item;
        @NotNull
        private final ComponentMap components;
        private final int count;
        private final int seed;

        public GeometryCacheKey(@NotNull Item item, @NotNull ComponentMap components, int count, int seed) {
            Intrinsics.checkNotNullParameter((Object)item, (String)"item");
            Intrinsics.checkNotNullParameter((Object)components, (String)"components");
            this.item = item;
            this.components = components;
            this.count = count;
            this.seed = seed;
        }

        @NotNull
        public final Item getItem() {
            return this.item;
        }

        @NotNull
        public final ComponentMap getComponents() {
            return this.components;
        }

        public final int getCount() {
            return this.count;
        }

        public final int getSeed() {
            return this.seed;
        }

        @NotNull
        public final Item component1() {
            return this.item;
        }

        @NotNull
        public final ComponentMap component2() {
            return this.components;
        }

        public final int component3() {
            return this.count;
        }

        public final int component4() {
            return this.seed;
        }

        @NotNull
        public final GeometryCacheKey copy(@NotNull Item item, @NotNull ComponentMap components, int count, int seed) {
            Intrinsics.checkNotNullParameter((Object)item, (String)"item");
            Intrinsics.checkNotNullParameter((Object)components, (String)"components");
            return new GeometryCacheKey(item, components, count, seed);
        }

        public static /* synthetic */ GeometryCacheKey copy$default(GeometryCacheKey geometryCacheKey, Item item2, ComponentMap componentMap2, int n, int n2, int n3, Object object) {
            if ((n3 & 1) != 0) {
                item2 = geometryCacheKey.item;
            }
            if ((n3 & 2) != 0) {
                componentMap2 = geometryCacheKey.components;
            }
            if ((n3 & 4) != 0) {
                n = geometryCacheKey.count;
            }
            if ((n3 & 8) != 0) {
                n2 = geometryCacheKey.seed;
            }
            return geometryCacheKey.copy(item2, componentMap2, n, n2);
        }

        @NotNull
        public String toString() {
            return "GeometryCacheKey(item=" + this.item + ", components=" + this.components + ", count=" + this.count + ", seed=" + this.seed + ")";
        }

        public int hashCode() {
            int result = this.item.hashCode();
            result = result * 31 + this.components.hashCode();
            result = result * 31 + Integer.hashCode(this.count);
            result = result * 31 + Integer.hashCode(this.seed);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof GeometryCacheKey)) {
                return false;
            }
            GeometryCacheKey geometryCacheKey = (GeometryCacheKey)other;
            if (!Intrinsics.areEqual((Object)this.item, (Object)geometryCacheKey.item)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.components, (Object)geometryCacheKey.components)) {
                return false;
            }
            if (this.count != geometryCacheKey.count) {
                return false;
            }
            return this.seed == geometryCacheKey.seed;
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$GeometryCacheKey.Companion;", "", "<init>", "()V", "Lnet/minecraft/ItemStack;", "stack", "", "seed", "Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$GeometryCacheKey;", "of", "(Lnet/minecraft/ItemStack;I)Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$GeometryCacheKey;", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final GeometryCacheKey of(@NotNull ItemStack stack, int seed) {
                Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
                Item item2 = stack.getItem();
                Intrinsics.checkNotNullExpressionValue((Object)item2, (String)"getItem(...)");
                ComponentMap componentMap2 = stack.getImmutableComponents();
                Intrinsics.checkNotNullExpressionValue((Object)componentMap2, (String)"immutableComponents(...)");
                return new GeometryCacheKey(item2, componentMap2, stack.getCount(), seed);
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u000e\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u0015\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\r\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002\u00a2\u0006\u0004\b\r\u0010\fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u000eR\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u000eR\u0014\u0010\u0005\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u000e\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$NormalizedBounds;", "", "", "minX", "maxY", "scale", "offsetX", "offsetY", "<init>", "(FFFFF)V", "value", "x", "(F)F", "y", "F", "Companion", "rtx.kimiko:kimiko"})
    private static final class NormalizedBounds {
        @NotNull
        public static final Companion Companion = new Companion(null);
        private final float minX;
        private final float maxY;
        private final float scale;
        private final float offsetX;
        private final float offsetY;

        public NormalizedBounds(float minX, float maxY, float scale, float offsetX, float offsetY) {
            this.minX = minX;
            this.maxY = maxY;
            this.scale = scale;
            this.offsetX = offsetX;
            this.offsetY = offsetY;
        }

        public final float x(float value) {
            return this.offsetX + (value - this.minX) * this.scale;
        }

        public final float y(float value) {
            return this.offsetY + (this.maxY - value) * this.scale;
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2={"Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$NormalizedBounds.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$Bounds;", "bounds", "Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$NormalizedBounds;", "of", "(Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$Bounds;)Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$NormalizedBounds;", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final NormalizedBounds of(@NotNull Bounds bounds) {
                Intrinsics.checkNotNullParameter((Object)bounds, (String)"bounds");
                if (!bounds.defined()) {
                    return new NormalizedBounds(0.0f, 1.0f, 1.0f, 0.0f, 0.0f);
                }
                float width = Math.max(1.0E-4f, bounds.getMaxX() - bounds.getMinX());
                float height = Math.max(1.0E-4f, bounds.getMaxY() - bounds.getMinY());
                float span = Math.max(width, height);
                if (bounds.preserveModelScale()) {
                    float scale = Math.min(1.0f / span, 1.08f);
                    return new NormalizedBounds((bounds.getMinX() + bounds.getMaxX()) * 0.5f, (bounds.getMinY() + bounds.getMaxY()) * 0.5f, scale, 0.5f, 0.5f);
                }
                float scale = 1.0f / span;
                return new NormalizedBounds(bounds.getMinX(), bounds.getMaxY(), scale, (1.0f - width * scale) * 0.5f, (1.0f - height * scale) * 0.5f);
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0082\b\u0018\u0000 %2\u00020\u0001:\u0001%B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\fJ\u0010\u0010\u000e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\fJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\fJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\fJ\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\fJL\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0019\u001a\u00020\u0018H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0011\u0010\u001c\u001a\u00020\u001bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010\fR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001e\u001a\u0004\b \u0010\fR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b!\u0010\fR\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b\"\u0010\fR\u0017\u0010\u0007\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b#\u0010\fR\u0017\u0010\b\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u001e\u001a\u0004\b$\u0010\f\u00a8\u0006&"}, d2={"Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$PoseKey;", "", "", "m00", "m01", "m10", "m11", "m20", "m21", "<init>", "(FFFFFF)V", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "copy", "(FFFFFF)Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$PoseKey;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "F", "getM00", "getM01", "getM10", "getM11", "getM20", "getM21", "Companion", "rtx.kimiko:kimiko"})
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

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2={"Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$PoseKey.Companion;", "", "<init>", "()V", "Lorg/joml/Matrix3x2f;", "matrix", "Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$PoseKey;", "of", "(Lorg/joml/Matrix3x2f;)Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$PoseKey;", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final PoseKey of(@NotNull Matrix3x2f matrix) {
                Intrinsics.checkNotNullParameter((Object)matrix, (String)"matrix");
                return new PoseKey(matrix.m00(), matrix.m01(), matrix.m10(), matrix.m11(), matrix.m20(), matrix.m21());
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b,\n\u0002\u0010\u000e\n\u0002\b \b\u0082\b\u0018\u00002\u00020\u0001B\u00d1\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\u0006\u0010\u000e\u001a\u00020\u0004\u0012\u0006\u0010\u000f\u001a\u00020\u0004\u0012\u0006\u0010\u0010\u001a\u00020\u0004\u0012\u0006\u0010\u0011\u001a\u00020\u0004\u0012\u0006\u0010\u0012\u001a\u00020\u0004\u0012\u0006\u0010\u0013\u001a\u00020\u0004\u0012\u0006\u0010\u0014\u001a\u00020\u0004\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u0012\u0006\u0010\u0019\u001a\u00020\u0004\u0012\u0006\u0010\u001a\u001a\u00020\u0004\u0012\u0006\u0010\u001b\u001a\u00020\u0004\u0012\u0006\u0010\u001c\u001a\u00020\u0004\u0012\u0006\u0010\u001d\u001a\u00020\u0004\u0012\u0006\u0010\u001e\u001a\u00020\u0004\u00a2\u0006\u0004\b\u001f\u0010 J\u0012\u0010!\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b#\u0010$J\u0010\u0010%\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b%\u0010$J\u0010\u0010&\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b&\u0010$J\u0010\u0010'\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b'\u0010$J\u0010\u0010(\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b(\u0010$J\u0010\u0010)\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b)\u0010$J\u0010\u0010*\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b*\u0010$J\u0010\u0010+\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b+\u0010$J\u0010\u0010,\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b,\u0010$J\u0010\u0010-\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b-\u0010$J\u0010\u0010.\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b.\u0010$J\u0010\u0010/\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b/\u0010$J\u0010\u00100\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b0\u0010$J\u0010\u00101\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b1\u0010$J\u0010\u00102\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b2\u0010$J\u0010\u00103\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b3\u0010$J\u0010\u00104\u001a\u00020\u0015H\u00c6\u0003\u00a2\u0006\u0004\b4\u00105J\u0010\u00106\u001a\u00020\u0017H\u00c6\u0003\u00a2\u0006\u0004\b6\u00107J\u0010\u00108\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b8\u0010$J\u0010\u00109\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b9\u0010$J\u0010\u0010:\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b:\u0010$J\u0010\u0010;\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b;\u0010$J\u0010\u0010<\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b<\u0010$J\u0010\u0010=\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b=\u0010$J\u008c\u0002\u0010>\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00042\b\b\u0002\u0010\u0011\u001a\u00020\u00042\b\b\u0002\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u00042\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u0019\u001a\u00020\u00042\b\b\u0002\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u001b\u001a\u00020\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u00042\b\b\u0002\u0010\u001d\u001a\u00020\u00042\b\b\u0002\u0010\u001e\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b>\u0010?J\u001b\u0010A\u001a\u00020\u00172\b\u0010@\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\bA\u0010BJ\u0011\u0010C\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\bC\u00105J\u0011\u0010E\u001a\u00020DH\u00d6\u0081\u0004\u00a2\u0006\u0004\bE\u0010FR\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010G\u001a\u0004\bH\u0010\"R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010I\u001a\u0004\bJ\u0010$R\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010I\u001a\u0004\bK\u0010$R\u0017\u0010\u0007\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010I\u001a\u0004\bL\u0010$R\u0017\u0010\b\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\b\u0010I\u001a\u0004\bM\u0010$R\u0017\u0010\t\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\t\u0010I\u001a\u0004\bN\u0010$R\u0017\u0010\n\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\n\u0010I\u001a\u0004\bO\u0010$R\u0017\u0010\u000b\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010I\u001a\u0004\bP\u0010$R\u0017\u0010\f\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\f\u0010I\u001a\u0004\bQ\u0010$R\u0017\u0010\r\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\r\u0010I\u001a\u0004\bR\u0010$R\u0017\u0010\u000e\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u000e\u0010I\u001a\u0004\bS\u0010$R\u0017\u0010\u000f\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u000f\u0010I\u001a\u0004\bT\u0010$R\u0017\u0010\u0010\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0010\u0010I\u001a\u0004\bU\u0010$R\u0017\u0010\u0011\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010I\u001a\u0004\bV\u0010$R\u0017\u0010\u0012\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0012\u0010I\u001a\u0004\bW\u0010$R\u0017\u0010\u0013\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0013\u0010I\u001a\u0004\bX\u0010$R\u0017\u0010\u0014\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0014\u0010I\u001a\u0004\bY\u0010$R\u0017\u0010\u0016\u001a\u00020\u00158\u0006\u00a2\u0006\f\n\u0004\b\u0016\u0010Z\u001a\u0004\b[\u00105R\u0017\u0010\u0018\u001a\u00020\u00178\u0006\u00a2\u0006\f\n\u0004\b\u0018\u0010\\\u001a\u0004\b]\u00107R\u0017\u0010\u0019\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0019\u0010I\u001a\u0004\b^\u0010$R\u0017\u0010\u001a\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u001a\u0010I\u001a\u0004\b_\u0010$R\u0017\u0010\u001b\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u001b\u0010I\u001a\u0004\b`\u0010$R\u0017\u0010\u001c\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u001c\u0010I\u001a\u0004\ba\u0010$R\u0017\u0010\u001d\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u001d\u0010I\u001a\u0004\bb\u0010$R\u0017\u0010\u001e\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u001e\u0010I\u001a\u0004\bc\u0010$\u00a8\u0006d"}, d2={"Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$RawItemQuad;", "", "Lnet/minecraft/Identifier;", "atlas", "", "x0", "y0", "u0", "v0", "x1", "y1", "u1", "v1", "x2", "y2", "u2", "v2", "x3", "y3", "u3", "v3", "", "tint", "", "foil", "depth", "area", "z0", "z1", "z2", "z3", "<init>", "(Lnet/minecraft/Identifier;FFFFFFFFFFFFFFFFIZFFFFFF)V", "component1", "()Lnet/minecraft/Identifier;", "component2", "()F", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "()I", "component19", "()Z", "component20", "component21", "component22", "component23", "component24", "component25", "copy", "(Lnet/minecraft/Identifier;FFFFFFFFFFFFFFFFIZFFFFFF)Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer$RawItemQuad;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/Identifier;", "getAtlas", "F", "getX0", "getY0", "getU0", "getV0", "getX1", "getY1", "getU1", "getV1", "getX2", "getY2", "getU2", "getV2", "getX3", "getY3", "getU3", "getV3", "I", "getTint", "Z", "getFoil", "getDepth", "getArea", "getZ0", "getZ1", "getZ2", "getZ3", "rtx.kimiko:kimiko"})
    private static final class RawItemQuad {
        @Nullable
        private final Identifier atlas;
        private final float x0;
        private final float y0;
        private final float u0;
        private final float v0;
        private final float x1;
        private final float y1;
        private final float u1;
        private final float v1;
        private final float x2;
        private final float y2;
        private final float u2;
        private final float v2;
        private final float x3;
        private final float y3;
        private final float u3;
        private final float v3;
        private final int tint;
        private final boolean foil;
        private final float depth;
        private final float area;
        private final float z0;
        private final float z1;
        private final float z2;
        private final float z3;

        public RawItemQuad(@Nullable Identifier atlas, float x0, float y0, float u0, float v0, float x1, float y1, float u1, float v1, float x2, float y2, float u2, float v2, float x3, float y3, float u3, float v3, int tint, boolean foil, float depth, float area, float z0, float z1, float z2, float z3) {
            this.atlas = atlas;
            this.x0 = x0;
            this.y0 = y0;
            this.u0 = u0;
            this.v0 = v0;
            this.x1 = x1;
            this.y1 = y1;
            this.u1 = u1;
            this.v1 = v1;
            this.x2 = x2;
            this.y2 = y2;
            this.u2 = u2;
            this.v2 = v2;
            this.x3 = x3;
            this.y3 = y3;
            this.u3 = u3;
            this.v3 = v3;
            this.tint = tint;
            this.foil = foil;
            this.depth = depth;
            this.area = area;
            this.z0 = z0;
            this.z1 = z1;
            this.z2 = z2;
            this.z3 = z3;
        }

        @Nullable
        public final Identifier getAtlas() {
            return this.atlas;
        }

        public final float getX0() {
            return this.x0;
        }

        public final float getY0() {
            return this.y0;
        }

        public final float getU0() {
            return this.u0;
        }

        public final float getV0() {
            return this.v0;
        }

        public final float getX1() {
            return this.x1;
        }

        public final float getY1() {
            return this.y1;
        }

        public final float getU1() {
            return this.u1;
        }

        public final float getV1() {
            return this.v1;
        }

        public final float getX2() {
            return this.x2;
        }

        public final float getY2() {
            return this.y2;
        }

        public final float getU2() {
            return this.u2;
        }

        public final float getV2() {
            return this.v2;
        }

        public final float getX3() {
            return this.x3;
        }

        public final float getY3() {
            return this.y3;
        }

        public final float getU3() {
            return this.u3;
        }

        public final float getV3() {
            return this.v3;
        }

        public final int getTint() {
            return this.tint;
        }

        public final boolean getFoil() {
            return this.foil;
        }

        public final float getDepth() {
            return this.depth;
        }

        public final float getArea() {
            return this.area;
        }

        public final float getZ0() {
            return this.z0;
        }

        public final float getZ1() {
            return this.z1;
        }

        public final float getZ2() {
            return this.z2;
        }

        public final float getZ3() {
            return this.z3;
        }

        @Nullable
        public final Identifier component1() {
            return this.atlas;
        }

        public final float component2() {
            return this.x0;
        }

        public final float component3() {
            return this.y0;
        }

        public final float component4() {
            return this.u0;
        }

        public final float component5() {
            return this.v0;
        }

        public final float component6() {
            return this.x1;
        }

        public final float component7() {
            return this.y1;
        }

        public final float component8() {
            return this.u1;
        }

        public final float component9() {
            return this.v1;
        }

        public final float component10() {
            return this.x2;
        }

        public final float component11() {
            return this.y2;
        }

        public final float component12() {
            return this.u2;
        }

        public final float component13() {
            return this.v2;
        }

        public final float component14() {
            return this.x3;
        }

        public final float component15() {
            return this.y3;
        }

        public final float component16() {
            return this.u3;
        }

        public final float component17() {
            return this.v3;
        }

        public final int component18() {
            return this.tint;
        }

        public final boolean component19() {
            return this.foil;
        }

        public final float component20() {
            return this.depth;
        }

        public final float component21() {
            return this.area;
        }

        public final float component22() {
            return this.z0;
        }

        public final float component23() {
            return this.z1;
        }

        public final float component24() {
            return this.z2;
        }

        public final float component25() {
            return this.z3;
        }

        @NotNull
        public final RawItemQuad copy(@Nullable Identifier atlas, float x0, float y0, float u0, float v0, float x1, float y1, float u1, float v1, float x2, float y2, float u2, float v2, float x3, float y3, float u3, float v3, int tint, boolean foil, float depth, float area, float z0, float z1, float z2, float z3) {
            return new RawItemQuad(atlas, x0, y0, u0, v0, x1, y1, u1, v1, x2, y2, u2, v2, x3, y3, u3, v3, tint, foil, depth, area, z0, z1, z2, z3);
        }

        public static /* synthetic */ RawItemQuad copy$default(RawItemQuad rawItemQuad, Identifier identifier2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, int n, boolean bl, float f17, float f18, float f19, float f20, float f21, float f22, int n2, Object object) {
            if ((n2 & 1) != 0) {
                identifier2 = rawItemQuad.atlas;
            }
            if ((n2 & 2) != 0) {
                f = rawItemQuad.x0;
            }
            if ((n2 & 4) != 0) {
                f2 = rawItemQuad.y0;
            }
            if ((n2 & 8) != 0) {
                f3 = rawItemQuad.u0;
            }
            if ((n2 & 0x10) != 0) {
                f4 = rawItemQuad.v0;
            }
            if ((n2 & 0x20) != 0) {
                f5 = rawItemQuad.x1;
            }
            if ((n2 & 0x40) != 0) {
                f6 = rawItemQuad.y1;
            }
            if ((n2 & 0x80) != 0) {
                f7 = rawItemQuad.u1;
            }
            if ((n2 & 0x100) != 0) {
                f8 = rawItemQuad.v1;
            }
            if ((n2 & 0x200) != 0) {
                f9 = rawItemQuad.x2;
            }
            if ((n2 & 0x400) != 0) {
                f10 = rawItemQuad.y2;
            }
            if ((n2 & 0x800) != 0) {
                f11 = rawItemQuad.u2;
            }
            if ((n2 & 0x1000) != 0) {
                f12 = rawItemQuad.v2;
            }
            if ((n2 & 0x2000) != 0) {
                f13 = rawItemQuad.x3;
            }
            if ((n2 & 0x4000) != 0) {
                f14 = rawItemQuad.y3;
            }
            if ((n2 & 0x8000) != 0) {
                f15 = rawItemQuad.u3;
            }
            if ((n2 & 0x10000) != 0) {
                f16 = rawItemQuad.v3;
            }
            if ((n2 & 0x20000) != 0) {
                n = rawItemQuad.tint;
            }
            if ((n2 & 0x40000) != 0) {
                bl = rawItemQuad.foil;
            }
            if ((n2 & 0x80000) != 0) {
                f17 = rawItemQuad.depth;
            }
            if ((n2 & 0x100000) != 0) {
                f18 = rawItemQuad.area;
            }
            if ((n2 & 0x200000) != 0) {
                f19 = rawItemQuad.z0;
            }
            if ((n2 & 0x400000) != 0) {
                f20 = rawItemQuad.z1;
            }
            if ((n2 & 0x800000) != 0) {
                f21 = rawItemQuad.z2;
            }
            if ((n2 & 0x1000000) != 0) {
                f22 = rawItemQuad.z3;
            }
            return rawItemQuad.copy(identifier2, f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, n, bl, f17, f18, f19, f20, f21, f22);
        }

        @NotNull
        public String toString() {
            return "RawItemQuad(atlas=" + this.atlas + ", x0=" + this.x0 + ", y0=" + this.y0 + ", u0=" + this.u0 + ", v0=" + this.v0 + ", x1=" + this.x1 + ", y1=" + this.y1 + ", u1=" + this.u1 + ", v1=" + this.v1 + ", x2=" + this.x2 + ", y2=" + this.y2 + ", u2=" + this.u2 + ", v2=" + this.v2 + ", x3=" + this.x3 + ", y3=" + this.y3 + ", u3=" + this.u3 + ", v3=" + this.v3 + ", tint=" + this.tint + ", foil=" + this.foil + ", depth=" + this.depth + ", area=" + this.area + ", z0=" + this.z0 + ", z1=" + this.z1 + ", z2=" + this.z2 + ", z3=" + this.z3 + ")";
        }

        public int hashCode() {
            int result = this.atlas == null ? 0 : this.atlas.hashCode();
            result = result * 31 + Float.hashCode(this.x0);
            result = result * 31 + Float.hashCode(this.y0);
            result = result * 31 + Float.hashCode(this.u0);
            result = result * 31 + Float.hashCode(this.v0);
            result = result * 31 + Float.hashCode(this.x1);
            result = result * 31 + Float.hashCode(this.y1);
            result = result * 31 + Float.hashCode(this.u1);
            result = result * 31 + Float.hashCode(this.v1);
            result = result * 31 + Float.hashCode(this.x2);
            result = result * 31 + Float.hashCode(this.y2);
            result = result * 31 + Float.hashCode(this.u2);
            result = result * 31 + Float.hashCode(this.v2);
            result = result * 31 + Float.hashCode(this.x3);
            result = result * 31 + Float.hashCode(this.y3);
            result = result * 31 + Float.hashCode(this.u3);
            result = result * 31 + Float.hashCode(this.v3);
            result = result * 31 + Integer.hashCode(this.tint);
            result = result * 31 + Boolean.hashCode(this.foil);
            result = result * 31 + Float.hashCode(this.depth);
            result = result * 31 + Float.hashCode(this.area);
            result = result * 31 + Float.hashCode(this.z0);
            result = result * 31 + Float.hashCode(this.z1);
            result = result * 31 + Float.hashCode(this.z2);
            result = result * 31 + Float.hashCode(this.z3);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RawItemQuad)) {
                return false;
            }
            RawItemQuad rawItemQuad = (RawItemQuad)other;
            if (!Intrinsics.areEqual((Object)this.atlas, (Object)rawItemQuad.atlas)) {
                return false;
            }
            if (Float.compare(this.x0, rawItemQuad.x0) != 0) {
                return false;
            }
            if (Float.compare(this.y0, rawItemQuad.y0) != 0) {
                return false;
            }
            if (Float.compare(this.u0, rawItemQuad.u0) != 0) {
                return false;
            }
            if (Float.compare(this.v0, rawItemQuad.v0) != 0) {
                return false;
            }
            if (Float.compare(this.x1, rawItemQuad.x1) != 0) {
                return false;
            }
            if (Float.compare(this.y1, rawItemQuad.y1) != 0) {
                return false;
            }
            if (Float.compare(this.u1, rawItemQuad.u1) != 0) {
                return false;
            }
            if (Float.compare(this.v1, rawItemQuad.v1) != 0) {
                return false;
            }
            if (Float.compare(this.x2, rawItemQuad.x2) != 0) {
                return false;
            }
            if (Float.compare(this.y2, rawItemQuad.y2) != 0) {
                return false;
            }
            if (Float.compare(this.u2, rawItemQuad.u2) != 0) {
                return false;
            }
            if (Float.compare(this.v2, rawItemQuad.v2) != 0) {
                return false;
            }
            if (Float.compare(this.x3, rawItemQuad.x3) != 0) {
                return false;
            }
            if (Float.compare(this.y3, rawItemQuad.y3) != 0) {
                return false;
            }
            if (Float.compare(this.u3, rawItemQuad.u3) != 0) {
                return false;
            }
            if (Float.compare(this.v3, rawItemQuad.v3) != 0) {
                return false;
            }
            if (this.tint != rawItemQuad.tint) {
                return false;
            }
            if (this.foil != rawItemQuad.foil) {
                return false;
            }
            if (Float.compare(this.depth, rawItemQuad.depth) != 0) {
                return false;
            }
            if (Float.compare(this.area, rawItemQuad.area) != 0) {
                return false;
            }
            if (Float.compare(this.z0, rawItemQuad.z0) != 0) {
                return false;
            }
            if (Float.compare(this.z1, rawItemQuad.z1) != 0) {
                return false;
            }
            if (Float.compare(this.z2, rawItemQuad.z2) != 0) {
                return false;
            }
            return Float.compare(this.z3, rawItemQuad.z3) == 0;
        }
    }
}

