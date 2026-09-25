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
 *  net.minecraft.client.texture.AbstractTexture
 *  net.minecraft.client.texture.Sprite
 *  net.minecraft.client.texture.SpriteAtlasTexture
 *  net.minecraft.util.Atlases
 *  net.minecraft.client.gl.UniformType
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.client.gui.render.state.GuiRenderState
 *  net.minecraft.client.gl.GpuSampler
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.hud.InGameHud
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 */
package rtx.kimiko.utils.render.render2d.effecticon;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.Atlases;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.render.modules.post.GuiRenderStateLayerAccessor;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.render2d.effecticon.BuiltEffectIcon;
import rtx.kimiko.utils.render.render2d.effecticon.EffectIconQuad;
import rtx.kimiko.utils.render.render2d.effecticon.EffectIconRenderState;
import rtx.kimiko.utils.render.render2d.effecticon.EffectIconTexture;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 (2\u00060\u0001j\u0002`\u0002:\u0004)*+(B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0007\u00a2\u0006\u0004\b\u000e\u0010\u0004J\r\u0010\u000f\u001a\u00020\u0007\u00a2\u0006\u0004\b\u000f\u0010\u0004J#\u0010\u0010\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u001b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u0004R0\u0010\u001e\u001a\u001e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u001c0\u001bj\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u001c`\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR0\u0010$\u001a\u001e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\"0 j\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\"`#8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0018\u0010&\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010'\u00a8\u0006,"}, d2={"Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderer;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "Lnet/minecraft/DrawContext;", "graphics", "", "beginFrame", "(Lnet/minecraft/DrawContext;)V", "Lrtx/kimiko/utils/render/render2d/effecticon/BuiltEffectIcon;", "icon", "enqueue", "(Lrtx/kimiko/utils/render/render2d/effecticon/BuiltEffectIcon;)V", "flush", "barrier", "submit", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/utils/render/render2d/effecticon/BuiltEffectIcon;)V", "Lnet/minecraft/Sprite;", "resolveSprite", "(Lrtx/kimiko/utils/render/render2d/effecticon/BuiltEffectIcon;)Lnet/minecraft/Sprite;", "Lnet/minecraft/Identifier;", "atlasLocation", "Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconTexture;", "resolveTexture", "(Lnet/minecraft/Identifier;)Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconTexture;", "close", "Ljava/util/HashMap;", "Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderer$CachedTexture;", "Lkotlin/collections/HashMap;", "textures", "Ljava/util/HashMap;", "Ljava/util/LinkedHashMap;", "Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderer$FrameBatchKey;", "Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderState;", "Lkotlin/collections/LinkedHashMap;", "frameBatches", "Ljava/util/LinkedHashMap;", "activeGraphics", "Lnet/minecraft/DrawContext;", "Companion", "FrameBatchKey", "PoseKey", "CachedTexture", "rtx.kimiko:kimiko"})
public final class EffectIconRenderer
implements AutoCloseable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final HashMap<Identifier, CachedTexture> textures = new HashMap();
    @NotNull
    private final LinkedHashMap<FrameBatchKey, EffectIconRenderState> frameBatches = new LinkedHashMap(16);
    @Nullable
    private DrawContext activeGraphics;
    @Nullable
    private static volatile EffectIconRenderer instance;
    @NotNull
    private static final VertexFormat EFFECT_ICON_VERTEX_FORMAT;
    @JvmField
    @NotNull
    public static final RenderPipeline EFFECT_ICON_PIPELINE;

    private EffectIconRenderer() {
    }

    public final void beginFrame(@Nullable DrawContext graphics) {
        if (this.activeGraphics != graphics) {
            this.frameBatches.clear();
        }
        this.activeGraphics = graphics;
    }

    public final void enqueue(@Nullable BuiltEffectIcon icon) {
        this.submit(this.activeGraphics, icon);
    }

    public final void flush() {
        this.activeGraphics = null;
        this.frameBatches.clear();
    }

    public final void barrier() {
        this.frameBatches.clear();
    }

    private final void submit(DrawContext graphics, BuiltEffectIcon icon) {
        if (graphics == null || icon == null || icon.effect == null || !icon.effect.hasKeyAndValue() || icon.size <= 0.0f || (icon.color >>> 24 & 0xFF) == 0 && (icon.color & 0xFFFFFF) == 0) {
            return;
        }
        Sprite sprite2 = this.resolveSprite(icon);
        if (sprite2 == null) {
            return;
        }
        Sprite sprite = sprite2;
        EffectIconTexture effectIconTexture = this.resolveTexture(sprite.getAtlasId());
        if (effectIconTexture == null) {
            return;
        }
        EffectIconTexture texture = effectIconTexture;
        EffectIconQuad quad = new EffectIconQuad(icon.x, icon.y, icon.size, sprite.getMinU(), sprite.getMinV(), sprite.getMaxU(), sprite.getMaxV(), EffectIconRenderer.Companion.normalizeColor(icon.color));
        try {
            GuiRenderState guiState = ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState();
            Intrinsics.checkNotNull((Object)guiState, (String)"null cannot be cast to non-null type rtx.kimiko.utils.render.modules.post.GuiRenderStateLayerAccessor");
            int layerSerial = ((GuiRenderStateLayerAccessor)guiState).kimiko$getLayerSerial();
            Matrix3x2f pose = Render2DCoordinateSpace.pose(graphics);
            FrameBatchKey key = new FrameBatchKey(guiState, layerSerial, texture.id, PoseKey.Companion.of(pose));
            EffectIconRenderState state = this.frameBatches.get(key);
            if (state == null) {
                EffectIconRenderState created = new EffectIconRenderState(pose, texture);
                created.add(quad);
                ((Map)this.frameBatches).put(key, created);
                guiState.addSimpleElement((SimpleGuiElementRenderState)created);
            } else {
                state.add(quad);
            }
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    private final Sprite resolveSprite(BuiltEffectIcon icon) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        if (icon.effect == null) {
            return null;
        }
        SpriteAtlasTexture spriteAtlasTexture2 = minecraft.getAtlasManager().getAtlasTexture(Atlases.GUI);
        Intrinsics.checkNotNullExpressionValue((Object)spriteAtlasTexture2, (String)"getAtlasOrThrow(...)");
        SpriteAtlasTexture atlas = spriteAtlasTexture2;
        Identifier identifier2 = InGameHud.getEffectTexture(icon.effect);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"getMobEffectSprite(...)");
        Identifier spriteId = identifier2;
        return atlas.getSprite(spriteId);
    }

    private final EffectIconTexture resolveTexture(Identifier atlasLocation) {
        if (atlasLocation == null) {
            return null;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        if (minecraft.getTextureManager() == null) {
            return null;
        }
        AbstractTexture abstractTexture3 = minecraft.getTextureManager().getTexture(atlasLocation);
        Intrinsics.checkNotNullExpressionValue((Object)abstractTexture3, (String)"getTexture(...)");
        AbstractTexture texture = abstractTexture3;
        if (texture.getGlTextureView() == null) {
            return null;
        }
        CachedTexture cached = this.textures.get(atlasLocation);
        if (cached != null && cached.texture == texture) {
            return cached.value;
        }
        TextureSetup textureSetup2 = TextureSetup.of((GpuTextureView)texture.getGlTextureView(), (GpuSampler)RenderSystem.getSamplerCache().get(FilterMode.NEAREST));
        Intrinsics.checkNotNullExpressionValue((Object)textureSetup2, (String)"singleTexture(...)");
        TextureSetup setup = textureSetup2;
        EffectIconTexture value = new EffectIconTexture(atlasLocation, setup);
        ((Map)this.textures).put(atlasLocation, new CachedTexture(texture, value));
        return value;
    }

    @Override
    public void close() {
        this.frameBatches.clear();
        this.textures.clear();
        this.activeGraphics = null;
    }

    @JvmStatic
    @NotNull
    public static final EffectIconRenderer getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final void closeInstance() {
        Companion.closeInstance();
    }

    public /* synthetic */ EffectIconRenderer(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    static {
        VertexFormat vertexFormat = VertexFormat.builder().add("Position", VertexFormatElement.POSITION).add("UV0", VertexFormatElement.UV0).add("Color", VertexFormatElement.COLOR).build();
        Intrinsics.checkNotNullExpressionValue((Object)vertexFormat, (String)"build(...)");
        EFFECT_ICON_VERTEX_FORMAT = vertexFormat;
        RenderPipeline renderPipeline = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(EffectIconRenderer.Companion.id("pipeline/effect_icon")).withVertexShader(EffectIconRenderer.Companion.id("core/effect_icon")).withFragmentShader(EffectIconRenderer.Companion.id("core/effect_icon")).withVertexFormat(EFFECT_ICON_VERTEX_FORMAT, VertexFormat.DrawMode.QUADS).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).withSampler("Sampler0").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline, (String)"build(...)");
        EFFECT_ICON_PIPELINE = renderPipeline;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\b\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\tR\u0019\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\b\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\n\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderer$CachedTexture;", "", "Lnet/minecraft/AbstractTexture;", "texture", "Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconTexture;", "value", "<init>", "(Lnet/minecraft/AbstractTexture;Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconTexture;)V", "Lkotlin/jvm/JvmField;", "Lnet/minecraft/AbstractTexture;", "Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconTexture;", "rtx.kimiko:kimiko"})
    private static final class CachedTexture {
        @JvmField
        @NotNull
        public final AbstractTexture texture;
        @JvmField
        @NotNull
        public final EffectIconTexture value;

        public CachedTexture(@NotNull AbstractTexture texture, @NotNull EffectIconTexture value) {
            Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
            Intrinsics.checkNotNullParameter((Object)value, (String)"value");
            this.texture = texture;
            this.value = value;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J\u0017\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0019\u0010\u001a\u001a\u00020\u00188\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001b\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderer.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderer;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderer;", "", "closeInstance", "", "color", "normalizeColor", "(I)I", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "instance", "Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderer;", "Lcom/mojang/blaze3d/vertex/VertexFormat;", "EFFECT_ICON_VERTEX_FORMAT", "Lcom/mojang/blaze3d/vertex/VertexFormat;", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lkotlin/jvm/JvmField;", "EFFECT_ICON_PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        @NotNull
        public final EffectIconRenderer getInstance() {
            EffectIconRenderer local = null;
            local = instance;
            if (local == null) {
                Class<EffectIconRenderer> clazz = EffectIconRenderer.class;
                synchronized (clazz) {
                    boolean bl = false;
                    local = instance;
                    if (local == null) {
                        local = new EffectIconRenderer(null);
                        instance = local;
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            return local;
        }

        @JvmStatic
        public final void closeInstance() {
            EffectIconRenderer local = instance;
            if (local != null) {
                local.close();
                instance = null;
            }
        }

        private final int normalizeColor(int color) {
            if ((color & 0xFF000000) == 0 && (color & 0xFFFFFF) != 0) {
                return color | 0xFF000000;
            }
            return color;
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

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013J8\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\bH\u00c6\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0011\u0010\u001a\u001a\u00020\u0004H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001a\u0010\u000fJ\u0011\u0010\u001c\u001a\u00020\u001bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010\rR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010 \u001a\u0004\b!\u0010\u000fR\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\"\u001a\u0004\b#\u0010\u0011R\u0017\u0010\t\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u0010$\u001a\u0004\b%\u0010\u0013\u00a8\u0006&"}, d2={"Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderer$FrameBatchKey;", "", "Lnet/minecraft/GuiRenderState;", "state", "", "layerSerial", "Lnet/minecraft/Identifier;", "texture", "Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderer$PoseKey;", "pose", "<init>", "(Lnet/minecraft/GuiRenderState;ILnet/minecraft/Identifier;Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderer$PoseKey;)V", "component1", "()Lnet/minecraft/GuiRenderState;", "component2", "()I", "component3", "()Lnet/minecraft/Identifier;", "component4", "()Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderer$PoseKey;", "copy", "(Lnet/minecraft/GuiRenderState;ILnet/minecraft/Identifier;Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderer$PoseKey;)Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderer$FrameBatchKey;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/GuiRenderState;", "getState", "I", "getLayerSerial", "Lnet/minecraft/Identifier;", "getTexture", "Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderer$PoseKey;", "getPose", "rtx.kimiko:kimiko"})
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

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0082\b\u0018\u0000 %2\u00020\u0001:\u0001%B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\fJ\u0010\u0010\u000e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\fJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\fJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\fJ\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\fJL\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0019\u001a\u00020\u0018H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0011\u0010\u001c\u001a\u00020\u001bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010\fR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001e\u001a\u0004\b \u0010\fR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b!\u0010\fR\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b\"\u0010\fR\u0017\u0010\u0007\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b#\u0010\fR\u0017\u0010\b\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u001e\u001a\u0004\b$\u0010\f\u00a8\u0006&"}, d2={"Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderer$PoseKey;", "", "", "m00", "m01", "m10", "m11", "m20", "m21", "<init>", "(FFFFFF)V", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "copy", "(FFFFFF)Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderer$PoseKey;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "F", "getM00", "getM01", "getM10", "getM11", "getM20", "getM21", "Companion", "rtx.kimiko:kimiko"})
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

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2={"Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderer$PoseKey.Companion;", "", "<init>", "()V", "Lorg/joml/Matrix3x2f;", "matrix", "Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderer$PoseKey;", "of", "(Lorg/joml/Matrix3x2f;)Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderer$PoseKey;", "rtx.kimiko:kimiko"})
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
}

