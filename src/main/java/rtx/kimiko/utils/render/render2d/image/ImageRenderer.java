/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.textures.FilterMode
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.client.texture.AbstractTexture
 *  net.minecraft.client.texture.TextureManager
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
 */
package rtx.kimiko.utils.render.render2d.image;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.TextureManager;
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
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.render.modules.post.GuiRenderStateLayerAccessor;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.render2d.image.BuiltImage;
import rtx.kimiko.utils.render.render2d.image.ImageQuad;
import rtx.kimiko.utils.render.render2d.image.ImageRenderState;
import rtx.kimiko.utils.render.render2d.image.ImageTexture;
import rtx.kimiko.utils.render.util.scissor.ScissorUtil;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 -2\u00060\u0001j\u0002`\u0002:\u0004./0-B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0013\u0010\u0004J\r\u0010\u0014\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0014\u0010\u0004J#\u0010\u0015\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u001b\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u000eH\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001e\u001a\u00020\u0007\u00a2\u0006\u0004\b\u001e\u0010\u0004J\u000f\u0010\u001f\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u001f\u0010\u0004R0\u0010#\u001a\u001e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020!0 j\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020!`\"8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010$R0\u0010)\u001a\u001e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'0%j\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'`(8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0018\u0010+\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u0010,\u00a8\u00061"}, d2={"Lrtx/kimiko/utils/render/render2d/image/ImageRenderer;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "Lnet/minecraft/DrawContext;", "graphics", "", "beginFrame", "(Lnet/minecraft/DrawContext;)V", "Lrtx/kimiko/utils/render/render2d/image/BuiltImage;", "image", "enqueue", "(Lrtx/kimiko/utils/render/render2d/image/BuiltImage;)V", "", "path", "", "isTextureResolvable", "(Ljava/lang/String;)Z", "flush", "barrier", "submit", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/utils/render/render2d/image/BuiltImage;)V", "Lrtx/kimiko/utils/render/render2d/image/ImageTexture;", "resolveTexture", "(Ljava/lang/String;)Lrtx/kimiko/utils/render/render2d/image/ImageTexture;", "rawPath", "Lnet/minecraft/Identifier;", "resolveIdentifier", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "invalidate", "close", "Ljava/util/HashMap;", "Lrtx/kimiko/utils/render/render2d/image/ImageRenderer$CachedTexture;", "Lkotlin/collections/HashMap;", "textures", "Ljava/util/HashMap;", "Ljava/util/LinkedHashMap;", "Lrtx/kimiko/utils/render/render2d/image/ImageRenderer$FrameBatchKey;", "Lrtx/kimiko/utils/render/render2d/image/ImageRenderState;", "Lkotlin/collections/LinkedHashMap;", "frameBatches", "Ljava/util/LinkedHashMap;", "activeGraphics", "Lnet/minecraft/DrawContext;", "Companion", "FrameBatchKey", "PoseKey", "CachedTexture", "rtx.kimiko:kimiko"})
public final class ImageRenderer
implements AutoCloseable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final HashMap<Identifier, CachedTexture> textures = new HashMap();
    @NotNull
    private final LinkedHashMap<FrameBatchKey, ImageRenderState> frameBatches = new LinkedHashMap(32);
    @Nullable
    private DrawContext activeGraphics;
    @Nullable
    private static volatile ImageRenderer instance;
    private static boolean additiveMode;

    private ImageRenderer() {
    }

    public final void beginFrame(@Nullable DrawContext graphics) {
        if (this.activeGraphics != graphics) {
            this.frameBatches.clear();
        }
        this.activeGraphics = graphics;
    }

    public final void enqueue(@Nullable BuiltImage image) {
        this.submit(this.activeGraphics, image);
    }

    public final boolean isTextureResolvable(@Nullable String path) {
        return this.resolveTexture(path) != null;
    }

    public final void flush() {
        this.activeGraphics = null;
        this.frameBatches.clear();
    }

    public final void barrier() {
        this.frameBatches.clear();
    }

    private final void submit(DrawContext graphics, BuiltImage image) {
        float height;
        if (graphics == null || image == null || !image.visible()) {
            return;
        }
        ImageTexture imageTexture = this.resolveTexture(image.texture());
        if (imageTexture == null) {
            return;
        }
        ImageTexture texture = imageTexture;
        float width = image.explicitWidth() > 0.0f ? image.explicitWidth() : texture.drawWidth(image.size());
        float f = height = image.explicitHeight() > 0.0f ? image.explicitHeight() : texture.drawHeight(image.size());
        if (width <= 0.0f || height <= 0.0f) {
            return;
        }
        float maxRadius = Math.max(0.0f, Math.min(width, height) * 0.5f);
        ImageQuad quad = new ImageQuad(image.x(), image.y(), width, height, ImageRenderer.Companion.clamp(image.radiusTL(), 0.0f, maxRadius), ImageRenderer.Companion.clamp(image.radiusTR(), 0.0f, maxRadius), ImageRenderer.Companion.clamp(image.radiusBR(), 0.0f, maxRadius), ImageRenderer.Companion.clamp(image.radiusBL(), 0.0f, maxRadius), ImageRenderer.Companion.sanitizeSmoothness(image.smoothness()), ImageRenderer.Companion.normalizeColor(image.colorTopLeft()), ImageRenderer.Companion.normalizeColor(image.colorTopRight()), ImageRenderer.Companion.normalizeColor(image.colorBottomRight()), ImageRenderer.Companion.normalizeColor(image.colorBottomLeft()), ImageRenderer.Companion.clamp(image.u0(), 0.0f, 1.0f), ImageRenderer.Companion.clamp(image.v0(), 0.0f, 1.0f), ImageRenderer.Companion.clamp(image.u1(), 0.0f, 1.0f), ImageRenderer.Companion.clamp(image.v1(), 0.0f, 1.0f), Math.abs(image.rotationDegrees()) <= Float.MAX_VALUE ? image.rotationDegrees() : 0.0f, Math.abs(image.rotationOriginX()) <= Float.MAX_VALUE ? image.rotationOriginX() : 0.0f, Math.abs(image.rotationOriginY()) <= Float.MAX_VALUE ? image.rotationOriginY() : 0.0f, !image.nearest() && (image.u0() > 0.0f || image.v0() > 0.0f || image.u1() < 1.0f || image.v1() < 1.0f));
        try {
            GuiRenderState guiState = ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState();
            Intrinsics.checkNotNull((Object)guiState, (String)"null cannot be cast to non-null type rtx.kimiko.utils.render.modules.post.GuiRenderStateLayerAccessor");
            int layerSerial = ((GuiRenderStateLayerAccessor)guiState).kimiko$getLayerSerial();
            Matrix3x2f pose = Render2DCoordinateSpace.pose(graphics);
            ScreenRect scissorArea = ScissorUtil.current();
            FrameBatchKey key = new FrameBatchKey(guiState, layerSerial, texture.id(), image.nearest(), PoseKey.Companion.of(pose), scissorArea, additiveMode);
            ImageRenderState state = this.frameBatches.get(key);
            if (state == null) {
                ImageRenderState created = new ImageRenderState(pose, texture, scissorArea, image.nearest(), additiveMode);
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

    private final ImageTexture resolveTexture(String path) {
        Identifier identifier2 = this.resolveIdentifier(path);
        if (identifier2 == null) {
            return null;
        }
        Identifier id = identifier2;
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
        AbstractTexture abstractTexture3 = textureManager.getTexture(id);
        Intrinsics.checkNotNullExpressionValue((Object)abstractTexture3, (String)"getTexture(...)");
        AbstractTexture texture = abstractTexture3;
        if (texture.getGlTextureView() == null || texture.getGlTexture() == null) {
            return null;
        }
        GpuTextureView gpuTextureView = texture.getGlTextureView();
        Intrinsics.checkNotNullExpressionValue((Object)gpuTextureView, (String)"getTextureView(...)");
        GpuTextureView view = gpuTextureView;
        CachedTexture cached = this.textures.get(id);
        if (cached != null && cached.getTexture() == texture && cached.getView() == view) {
            return cached.getValue();
        }
        int width = Math.max(1, texture.getGlTexture().getWidth(0));
        int height = Math.max(1, texture.getGlTexture().getHeight(0));
        TextureSetup textureSetup2 = TextureSetup.of((GpuTextureView)view, (GpuSampler)RenderSystem.getSamplerCache().get(FilterMode.LINEAR));
        Intrinsics.checkNotNullExpressionValue((Object)textureSetup2, (String)"singleTexture(...)");
        TextureSetup linearSetup = textureSetup2;
        TextureSetup textureSetup3 = TextureSetup.of((GpuTextureView)view, (GpuSampler)RenderSystem.getSamplerCache().get(FilterMode.NEAREST));
        Intrinsics.checkNotNullExpressionValue((Object)textureSetup3, (String)"singleTexture(...)");
        TextureSetup nearestSetup = textureSetup3;
        ImageTexture value = new ImageTexture(id, linearSetup, nearestSetup, width, height);
        ((Map)this.textures).put(id, new CachedTexture(texture, view, value));
        return value;
    }

    private final Identifier resolveIdentifier(String rawPath) {
        CharSequence charSequence = rawPath;
        if (charSequence == null || StringsKt.isBlank((CharSequence)charSequence)) {
            return null;
        }
        Object path = String.valueOf(((Object)StringsKt.trim((CharSequence)rawPath)).toString()).replace((char)'\\', (char)'/');
        if (String.valueOf(path).startsWith("/")) {
            String string = ((String)path).substring(1);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            path = string;
        }
        if (String.valueOf(path).startsWith("assets/")) {
            String string = ((String)path).substring(7);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            path = string;
            int slash = String.valueOf(((CharSequence)path)).indexOf((char)'/');
            if (slash >= 0) {
                String string2 = ((String)path).substring(0, slash);
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
                String namespace = string2;
                String string3 = ((String)path).substring(slash + 1);
                Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"substring(...)");
                String resourcePath = string3;
                return Identifier.of((String)namespace, (String)resourcePath);
            }
        }
        if (String.valueOf(((CharSequence)path)).indexOf((char)':') >= 0) {
            return Identifier.tryParse((String)path);
        }
        if (!String.valueOf(path).startsWith("images/")) {
            path = "images/" + (String)path;
        }
        if (!String.valueOf(((CharSequence)path)).contains(".")) {
            path = (String)path + ".png";
        }
        return Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
    }

    public final void invalidate() {
        this.frameBatches.clear();
        this.textures.clear();
    }

    @Override
    public void close() {
        this.frameBatches.clear();
        this.textures.clear();
        this.activeGraphics = null;
    }

    @JvmStatic
    public static final void setAdditive(boolean value) {
        Companion.setAdditive(value);
    }

    @JvmStatic
    @NotNull
    public static final ImageRenderer getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final void closeInstance() {
        Companion.closeInstance();
    }

    public /* synthetic */ ImageRenderer(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ.\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00c6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u0017\u001a\u00020\u0016H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0011\u0010\u001a\u001a\u00020\u0019H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010\rR\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010 \u001a\u0004\b!\u0010\u000f\u00a8\u0006\""}, d2={"Lrtx/kimiko/utils/render/render2d/image/ImageRenderer$CachedTexture;", "", "Lnet/minecraft/AbstractTexture;", "texture", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "view", "Lrtx/kimiko/utils/render/render2d/image/ImageTexture;", "value", "<init>", "(Lnet/minecraft/AbstractTexture;Lcom/mojang/blaze3d/textures/GpuTextureView;Lrtx/kimiko/utils/render/render2d/image/ImageTexture;)V", "component1", "()Lnet/minecraft/AbstractTexture;", "component2", "()Lcom/mojang/blaze3d/textures/GpuTextureView;", "component3", "()Lrtx/kimiko/utils/render/render2d/image/ImageTexture;", "copy", "(Lnet/minecraft/AbstractTexture;Lcom/mojang/blaze3d/textures/GpuTextureView;Lrtx/kimiko/utils/render/render2d/image/ImageTexture;)Lrtx/kimiko/utils/render/render2d/image/ImageRenderer$CachedTexture;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/AbstractTexture;", "getTexture", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "getView", "Lrtx/kimiko/utils/render/render2d/image/ImageTexture;", "getValue", "rtx.kimiko:kimiko"})
    private static final class CachedTexture {
        @NotNull
        private final AbstractTexture texture;
        @NotNull
        private final GpuTextureView view;
        @NotNull
        private final ImageTexture value;

        public CachedTexture(@NotNull AbstractTexture texture, @NotNull GpuTextureView view, @NotNull ImageTexture value) {
            Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
            Intrinsics.checkNotNullParameter((Object)view, (String)"view");
            Intrinsics.checkNotNullParameter((Object)value, (String)"value");
            this.texture = texture;
            this.view = view;
            this.value = value;
        }

        @NotNull
        public final AbstractTexture getTexture() {
            return this.texture;
        }

        @NotNull
        public final GpuTextureView getView() {
            return this.view;
        }

        @NotNull
        public final ImageTexture getValue() {
            return this.value;
        }

        @NotNull
        public final AbstractTexture component1() {
            return this.texture;
        }

        @NotNull
        public final GpuTextureView component2() {
            return this.view;
        }

        @NotNull
        public final ImageTexture component3() {
            return this.value;
        }

        @NotNull
        public final CachedTexture copy(@NotNull AbstractTexture texture, @NotNull GpuTextureView view, @NotNull ImageTexture value) {
            Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
            Intrinsics.checkNotNullParameter((Object)view, (String)"view");
            Intrinsics.checkNotNullParameter((Object)value, (String)"value");
            return new CachedTexture(texture, view, value);
        }

        public static /* synthetic */ CachedTexture copy$default(CachedTexture cachedTexture, AbstractTexture abstractTexture3, GpuTextureView gpuTextureView, ImageTexture imageTexture, int n, Object object) {
            if ((n & 1) != 0) {
                abstractTexture3 = cachedTexture.texture;
            }
            if ((n & 2) != 0) {
                gpuTextureView = cachedTexture.view;
            }
            if ((n & 4) != 0) {
                imageTexture = cachedTexture.value;
            }
            return cachedTexture.copy(abstractTexture3, gpuTextureView, imageTexture);
        }

        @NotNull
        public String toString() {
            return "CachedTexture(texture=" + this.texture + ", view=" + this.view + ", value=" + this.value + ")";
        }

        public int hashCode() {
            int result = this.texture.hashCode();
            result = result * 31 + this.view.hashCode();
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
            if (!Intrinsics.areEqual((Object)this.view, (Object)cachedTexture.view)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.value, (Object)cachedTexture.value);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0013\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\r\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\r\u0010\u0003J\u0017\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J'\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eR\u0018\u0010\u001f\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010!\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b!\u0010\"\u00a8\u0006#"}, d2={"Lrtx/kimiko/utils/render/render2d/image/ImageRenderer.Companion;", "", "<init>", "()V", "", "value", "", "Lkotlin/jvm/JvmStatic;", "setAdditive", "(Z)V", "Lrtx/kimiko/utils/render/render2d/image/ImageRenderer;", "getInstance", "()Lrtx/kimiko/utils/render/render2d/image/ImageRenderer;", "closeInstance", "", "color", "normalizeColor", "(I)I", "", "min", "max", "clamp", "(FFF)F", "smoothness", "sanitizeSmoothness", "(F)F", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "instance", "Lrtx/kimiko/utils/render/render2d/image/ImageRenderer;", "additiveMode", "Z", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final void setAdditive(boolean value) {
            additiveMode = value;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        @NotNull
        public final ImageRenderer getInstance() {
            ImageRenderer local = null;
            local = instance;
            if (local == null) {
                Class<ImageRenderer> clazz = ImageRenderer.class;
                synchronized (clazz) {
                    boolean bl = false;
                    local = instance;
                    if (local == null) {
                        local = new ImageRenderer(null);
                        instance = local;
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            return local;
        }

        @JvmStatic
        public final void closeInstance() {
            ImageRenderer local = instance;
            if (local != null) {
                local.close();
                instance = null;
            }
        }

        private final int normalizeColor(int color) {
            return color;
        }

        private final float clamp(float value, float min, float max) {
            return Math.max(min, Math.min(max, value));
        }

        private final float sanitizeSmoothness(float smoothness) {
            if (!(Math.abs(smoothness) <= Float.MAX_VALUE)) {
                return 0.0f;
            }
            return Math.max(0.001f, smoothness);
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

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0082\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\u000e\u001a\u00020\b\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\nH\u00c6\u0003\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0012\u0010\u001b\u001a\u0004\u0018\u00010\fH\u00c6\u0003\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u001d\u0010\u0018JX\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u000e\u001a\u00020\bH\u00c6\u0001\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010!\u001a\u00020\b2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b!\u0010\"J\u0011\u0010#\u001a\u00020\u0004H\u00d6\u0081\u0004\u00a2\u0006\u0004\b#\u0010\u0014J\u0011\u0010%\u001a\u00020$H\u00d6\u0081\u0004\u00a2\u0006\u0004\b%\u0010&R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010'\u001a\u0004\b(\u0010\u0012R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010)\u001a\u0004\b*\u0010\u0014R\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010+\u001a\u0004\b,\u0010\u0016R\u0017\u0010\t\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u0010-\u001a\u0004\b.\u0010\u0018R\u0017\u0010\u000b\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010/\u001a\u0004\b0\u0010\u001aR\u0019\u0010\r\u001a\u0004\u0018\u00010\f8\u0006\u00a2\u0006\f\n\u0004\b\r\u00101\u001a\u0004\b2\u0010\u001cR\u0017\u0010\u000e\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\u000e\u0010-\u001a\u0004\b3\u0010\u0018\u00a8\u00064"}, d2={"Lrtx/kimiko/utils/render/render2d/image/ImageRenderer$FrameBatchKey;", "", "Lnet/minecraft/GuiRenderState;", "state", "", "layerSerial", "Lnet/minecraft/Identifier;", "texture", "", "nearest", "Lrtx/kimiko/utils/render/render2d/image/ImageRenderer$PoseKey;", "pose", "Lnet/minecraft/ScreenRect;", "scissorArea", "additive", "<init>", "(Lnet/minecraft/GuiRenderState;ILnet/minecraft/Identifier;ZLrtx/kimiko/utils/render/render2d/image/ImageRenderer$PoseKey;Lnet/minecraft/ScreenRect;Z)V", "component1", "()Lnet/minecraft/GuiRenderState;", "component2", "()I", "component3", "()Lnet/minecraft/Identifier;", "component4", "()Z", "component5", "()Lrtx/kimiko/utils/render/render2d/image/ImageRenderer$PoseKey;", "component6", "()Lnet/minecraft/ScreenRect;", "component7", "copy", "(Lnet/minecraft/GuiRenderState;ILnet/minecraft/Identifier;ZLrtx/kimiko/utils/render/render2d/image/ImageRenderer$PoseKey;Lnet/minecraft/ScreenRect;Z)Lrtx/kimiko/utils/render/render2d/image/ImageRenderer$FrameBatchKey;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/GuiRenderState;", "getState", "I", "getLayerSerial", "Lnet/minecraft/Identifier;", "getTexture", "Z", "getNearest", "Lrtx/kimiko/utils/render/render2d/image/ImageRenderer$PoseKey;", "getPose", "Lnet/minecraft/ScreenRect;", "getScissorArea", "getAdditive", "rtx.kimiko:kimiko"})
    private static final class FrameBatchKey {
        @NotNull
        private final GuiRenderState state;
        private final int layerSerial;
        @NotNull
        private final Identifier texture;
        private final boolean nearest;
        @NotNull
        private final PoseKey pose;
        @Nullable
        private final ScreenRect scissorArea;
        private final boolean additive;

        public FrameBatchKey(@NotNull GuiRenderState state, int layerSerial, @NotNull Identifier texture, boolean nearest, @NotNull PoseKey pose, @Nullable ScreenRect scissorArea, boolean additive) {
            Intrinsics.checkNotNullParameter((Object)state, (String)"state");
            Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
            Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
            this.state = state;
            this.layerSerial = layerSerial;
            this.texture = texture;
            this.nearest = nearest;
            this.pose = pose;
            this.scissorArea = scissorArea;
            this.additive = additive;
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

        public final boolean getNearest() {
            return this.nearest;
        }

        @NotNull
        public final PoseKey getPose() {
            return this.pose;
        }

        @Nullable
        public final ScreenRect getScissorArea() {
            return this.scissorArea;
        }

        public final boolean getAdditive() {
            return this.additive;
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

        public final boolean component4() {
            return this.nearest;
        }

        @NotNull
        public final PoseKey component5() {
            return this.pose;
        }

        @Nullable
        public final ScreenRect component6() {
            return this.scissorArea;
        }

        public final boolean component7() {
            return this.additive;
        }

        @NotNull
        public final FrameBatchKey copy(@NotNull GuiRenderState state, int layerSerial, @NotNull Identifier texture, boolean nearest, @NotNull PoseKey pose, @Nullable ScreenRect scissorArea, boolean additive) {
            Intrinsics.checkNotNullParameter((Object)state, (String)"state");
            Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
            Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
            return new FrameBatchKey(state, layerSerial, texture, nearest, pose, scissorArea, additive);
        }

        public static /* synthetic */ FrameBatchKey copy$default(FrameBatchKey frameBatchKey, GuiRenderState guiRenderState2, int n, Identifier identifier2, boolean bl, PoseKey poseKey, ScreenRect screenRect2, boolean bl2, int n2, Object object) {
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
                bl = frameBatchKey.nearest;
            }
            if ((n2 & 0x10) != 0) {
                poseKey = frameBatchKey.pose;
            }
            if ((n2 & 0x20) != 0) {
                screenRect2 = frameBatchKey.scissorArea;
            }
            if ((n2 & 0x40) != 0) {
                bl2 = frameBatchKey.additive;
            }
            return frameBatchKey.copy(guiRenderState2, n, identifier2, bl, poseKey, screenRect2, bl2);
        }

        @NotNull
        public String toString() {
            return "FrameBatchKey(state=" + this.state + ", layerSerial=" + this.layerSerial + ", texture=" + this.texture + ", nearest=" + this.nearest + ", pose=" + this.pose + ", scissorArea=" + this.scissorArea + ", additive=" + this.additive + ")";
        }

        public int hashCode() {
            int result = this.state.hashCode();
            result = result * 31 + Integer.hashCode(this.layerSerial);
            result = result * 31 + this.texture.hashCode();
            result = result * 31 + Boolean.hashCode(this.nearest);
            result = result * 31 + this.pose.hashCode();
            result = result * 31 + (this.scissorArea == null ? 0 : this.scissorArea.hashCode());
            result = result * 31 + Boolean.hashCode(this.additive);
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
            if (this.nearest != frameBatchKey.nearest) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.pose, (Object)frameBatchKey.pose)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.scissorArea, (Object)frameBatchKey.scissorArea)) {
                return false;
            }
            return this.additive == frameBatchKey.additive;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0082\b\u0018\u0000 %2\u00020\u0001:\u0001%B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\fJ\u0010\u0010\u000e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\fJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\fJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\fJ\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\fJL\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0019\u001a\u00020\u0018H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0011\u0010\u001c\u001a\u00020\u001bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010\fR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001e\u001a\u0004\b \u0010\fR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b!\u0010\fR\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b\"\u0010\fR\u0017\u0010\u0007\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b#\u0010\fR\u0017\u0010\b\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u001e\u001a\u0004\b$\u0010\f\u00a8\u0006&"}, d2={"Lrtx/kimiko/utils/render/render2d/image/ImageRenderer$PoseKey;", "", "", "m00", "m01", "m10", "m11", "m20", "m21", "<init>", "(FFFFFF)V", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "copy", "(FFFFFF)Lrtx/kimiko/utils/render/render2d/image/ImageRenderer$PoseKey;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "F", "getM00", "getM01", "getM10", "getM11", "getM20", "getM21", "Companion", "rtx.kimiko:kimiko"})
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

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2={"Lrtx/kimiko/utils/render/render2d/image/ImageRenderer$PoseKey.Companion;", "", "<init>", "()V", "Lorg/joml/Matrix3x2f;", "matrix", "Lrtx/kimiko/utils/render/render2d/image/ImageRenderer$PoseKey;", "of", "(Lorg/joml/Matrix3x2f;)Lrtx/kimiko/utils/render/render2d/image/ImageRenderer$PoseKey;", "rtx.kimiko:kimiko"})
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

