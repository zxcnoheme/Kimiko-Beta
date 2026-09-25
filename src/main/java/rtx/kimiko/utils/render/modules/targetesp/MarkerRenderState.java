/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.BlendFunction
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Snippet
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.textures.FilterMode
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.AbstractTexture
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.client.gl.GpuSampler
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.gui.ScreenRect
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fc
 */
package rtx.kimiko.utils.render.modules.targetesp;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.gl.GpuSampler;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.gui.ScreenRect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;
import rtx.kimiko.utils.render.render2d.PoseCache;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0005\u0018\u0000 ,2\u00020\u0001:\u0001,B_\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\r\u0012\u0006\u0010\u0011\u001a\u00020\r\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0014H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0019H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u001cJ\u0011\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0016\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0011\u0010 \u001a\u0004\u0018\u00010\u001dH\u0016\u00a2\u0006\u0004\b \u0010\u001fR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010!R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\"R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010#R\u0014\u0010%\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010&R\u0014\u0010)\u001a\u00020(8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010 \u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010+\u00a8\u0006-"}, d2={"Lrtx/kimiko/utils/render/modules/targetesp/MarkerRenderState;", "Lnet/minecraft/SimpleGuiElementRenderState;", "Lorg/joml/Matrix3x2f;", "pose", "Lnet/minecraft/TextureSetup;", "textureSetup", "", "additive", "", "centerX", "centerY", "size", "rotationDegrees", "", "colorTopLeft", "colorTopRight", "colorBottomRight", "colorBottomLeft", "<init>", "(Lorg/joml/Matrix3x2f;Lnet/minecraft/TextureSetup;ZFFFFIIII)V", "Lnet/minecraft/VertexConsumer;", "consumer", "", "buildVertices", "(Lnet/minecraft/VertexConsumer;)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "()Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "()Lnet/minecraft/TextureSetup;", "Lnet/minecraft/ScreenRect;", "scissorArea", "()Lnet/minecraft/ScreenRect;", "bounds", "Lnet/minecraft/TextureSetup;", "Z", "Lorg/joml/Matrix3x2f;", "", "xs", "[F", "ys", "", "colors", "[I", "Lnet/minecraft/ScreenRect;", "Companion", "rtx.kimiko:kimiko"})
public final class MarkerRenderState
implements SimpleGuiElementRenderState {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final TextureSetup textureSetup;
    private final boolean additive;
    @NotNull
    private final Matrix3x2f pose;
    @NotNull
    private final float[] xs;
    @NotNull
    private final float[] ys;
    @NotNull
    private final int[] colors;
    @NotNull
    private final ScreenRect bounds;
    @JvmField
    @NotNull
    public static final RenderPipeline PIPELINE_ADDITIVE;
    @JvmField
    @NotNull
    public static final RenderPipeline PIPELINE_TRANSLUCENT;
    @NotNull
    private static final float[] CORNER_X;
    @NotNull
    private static final float[] CORNER_Y;
    @NotNull
    private static final float[] CORNER_U;
    @NotNull
    private static final float[] CORNER_V;

    public MarkerRenderState(@NotNull Matrix3x2f pose, @NotNull TextureSetup textureSetup, boolean additive, float centerX, float centerY, float size, float rotationDegrees, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)textureSetup, (String)"textureSetup");
        this.textureSetup = textureSetup;
        this.additive = additive;
        this.pose = PoseCache.snapshot(pose);
        this.xs = new float[4];
        this.ys = new float[4];
        this.colors = new int[4];
        float half = size * 0.5f;
        double radians = Math.toRadians(rotationDegrees);
        float cos = (float)Math.cos(radians);
        float sin = (float)Math.sin(radians);
        float minX = Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float maxX = -3.4028235E38f;
        float maxY = -3.4028235E38f;
        for (int i = 0; i < 4; ++i) {
            float dx = CORNER_X[i] * half;
            float dy = CORNER_Y[i] * half;
            this.xs[i] = centerX + dx * cos - dy * sin;
            this.ys[i] = centerY + dx * sin + dy * cos;
            minX = Math.min(minX, this.xs[i]);
            minY = Math.min(minY, this.ys[i]);
            maxX = Math.max(maxX, this.xs[i]);
            maxY = Math.max(maxY, this.ys[i]);
        }
        this.colors[0] = colorTopLeft;
        this.colors[1] = colorBottomLeft;
        this.colors[2] = colorBottomRight;
        this.colors[3] = colorTopRight;
        ScreenRect screenRect2 = new ScreenRect((int)Math.floor(minX), (int)Math.floor(minY), Math.max(1, (int)Math.ceil(maxX - minX)), Math.max(1, (int)Math.ceil(maxY - minY))).transformEachVertex((Matrix3x2fc)this.pose);
        Intrinsics.checkNotNullExpressionValue((Object)screenRect2, (String)"transformMaxBounds(...)");
        this.bounds = screenRect2;
    }

    public void setupVertices(@NotNull VertexConsumer consumer) {
        Intrinsics.checkNotNullParameter((Object)consumer, (String)"consumer");
        for (int i = 0; i < 4; ++i) {
            consumer.vertex((Matrix3x2fc)this.pose, this.xs[i], this.ys[i]).texture(CORNER_U[i], CORNER_V[i]).color(this.colors[i]);
        }
    }

    @NotNull
    public RenderPipeline pipeline() {
        return this.additive ? PIPELINE_ADDITIVE : PIPELINE_TRANSLUCENT;
    }

    @NotNull
    public TextureSetup textureSetup() {
        return this.textureSetup;
    }

    @Nullable
    public ScreenRect scissorArea() {
        return null;
    }

    @Nullable
    public ScreenRect bounds() {
        return this.bounds;
    }

    @JvmStatic
    @Nullable
    public static final TextureSetup resolveTexture(@NotNull String path) {
        return Companion.resolveTexture(path);
    }

    static {
        RenderPipeline.Snippet[] snippetArray = new RenderPipeline.Snippet[]{RenderPipelines.POSITION_TEX_COLOR_SNIPPET};
        RenderPipeline renderPipeline = RenderPipeline.builder((RenderPipeline.Snippet[])snippetArray).withLocation(Identifier.of((String)"kimiko", (String)"pipeline/targetesp_marker_additive")).withBlend(BlendFunction.LIGHTNING).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline, (String)"build(...)");
        PIPELINE_ADDITIVE = renderPipeline;
        snippetArray = new RenderPipeline.Snippet[]{RenderPipelines.POSITION_TEX_COLOR_SNIPPET};
        RenderPipeline renderPipeline2 = RenderPipeline.builder((RenderPipeline.Snippet[])snippetArray).withLocation(Identifier.of((String)"kimiko", (String)"pipeline/targetesp_marker")).withBlend(BlendFunction.TRANSLUCENT).withCull(false).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline2, (String)"build(...)");
        PIPELINE_TRANSLUCENT = renderPipeline2;
        float[] fArray = new float[]{-1.0f, -1.0f, 1.0f, 1.0f};
        CORNER_X = fArray;
        fArray = new float[]{-1.0f, 1.0f, 1.0f, -1.0f};
        CORNER_Y = fArray;
        fArray = new float[]{0.0f, 0.0f, 1.0f, 1.0f};
        CORNER_U = fArray;
        fArray = new float[]{0.0f, 1.0f, 1.0f, 0.0f};
        CORNER_V = fArray;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tR\u0019\u0010\f\u001a\u00020\n8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000b\u00a2\u0006\u0006\n\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\u00020\n8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000b\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\rR\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0011R\u0014\u0010\u0014\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0011\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/utils/render/modules/targetesp/MarkerRenderState.Companion;", "", "<init>", "()V", "", "path", "Lnet/minecraft/TextureSetup;", "Lkotlin/jvm/JvmStatic;", "resolveTexture", "(Ljava/lang/String;)Lnet/minecraft/TextureSetup;", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lkotlin/jvm/JvmField;", "PIPELINE_ADDITIVE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "PIPELINE_TRANSLUCENT", "", "CORNER_X", "[F", "CORNER_Y", "CORNER_U", "CORNER_V", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final TextureSetup resolveTexture(@NotNull String path) {
            Intrinsics.checkNotNullParameter((Object)path, (String)"path");
            Identifier identifier2 = Identifier.tryParse((String)path);
            if (identifier2 == null) {
                return null;
            }
            Identifier id = identifier2;
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            if (minecraftClient2 == null) {
                return null;
            }
            MinecraftClient minecraft = minecraftClient2;
            AbstractTexture abstractTexture3 = minecraft.getTextureManager().getTexture(id);
            if (abstractTexture3 == null) {
                return null;
            }
            AbstractTexture texture = abstractTexture3;
            GpuTextureView gpuTextureView = texture.getGlTextureView();
            Intrinsics.checkNotNull((Object)gpuTextureView);
            return TextureSetup.of((GpuTextureView)gpuTextureView, (GpuSampler)RenderSystem.getSamplerCache().get(FilterMode.LINEAR));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

