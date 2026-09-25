/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.BlendFunction
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Builder
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Snippet
 *  com.mojang.blaze3d.platform.DepthTestFunction
 *  com.mojang.blaze3d.vertex.VertexFormat
 *  com.mojang.blaze3d.vertex.VertexFormat$DrawMode
 *  com.mojang.blaze3d.vertex.VertexFormatElement
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gl.UniformType
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.core.pipeline;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.Kimiko;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003Ja\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0006\"\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\r2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0017R\u0019\u0010\u001a\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0019\u0010\u001c\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001bR\u0019\u0010\u001d\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001bR\u0019\u0010\u001e\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001bR\u0019\u0010\u001f\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001bR\u0019\u0010 \u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b \u0010\u001bR\u0019\u0010!\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b!\u0010\u001bR\u0019\u0010\"\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b\"\u0010\u001bR\u0019\u0010#\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b#\u0010\u001bR\u0019\u0010$\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b$\u0010\u001bR\u0019\u0010%\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b%\u0010\u001bR\u0014\u0010&\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010\u0017R\u0019\u0010'\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b'\u0010\u001bR\u0019\u0010(\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b(\u0010\u001bR\u001f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00100\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0019\u0010+\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b+\u0010\u001bR\u0019\u0010,\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b,\u0010\u001bR\u0019\u0010-\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b-\u0010\u001bR\u0019\u0010.\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b.\u0010\u001bR\u0019\u0010/\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b/\u0010\u001bR\u0014\u00100\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u0010\u0017R\u0019\u00101\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b1\u0010\u001bR\u0019\u00102\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b2\u0010\u001bR\u0019\u00103\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0019\u00a2\u0006\u0006\n\u0004\b3\u0010\u001b\u00a8\u00064"}, d2={"Lrtx/kimiko/utils/render/core/pipeline/ClientPipelines2D;", "", "<init>", "()V", "", "path", "", "uniformArrayNames", "Lcom/mojang/blaze3d/vertex/VertexFormat;", "vertexFormat", "Lcom/mojang/blaze3d/vertex/VertexFormat$DrawMode;", "mode", "shaderPath", "Lcom/mojang/blaze3d/pipeline/BlendFunction;", "blend", "samplers", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "standard", "(Ljava/lang/String;[Ljava/lang/String;Lcom/mojang/blaze3d/vertex/VertexFormat;Lcom/mojang/blaze3d/vertex/VertexFormat$DrawMode;Ljava/lang/String;Lcom/mojang/blaze3d/pipeline/BlendFunction;[Ljava/lang/String;)Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "RIPPLE_VERTEX_FORMAT", "Lcom/mojang/blaze3d/vertex/VertexFormat;", "SHIMMER_VERTEX_FORMAT", "Lkotlin/jvm/JvmField;", "ARC_DIVIDER", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "CIRCLE", "ARC_RECT", "LINE", "PICKER", "ZIPPY", "TABLE_PANEL", "RECT_HALFTONE", "OUTLINE_DEFAULT", "RIPPLE", "SHIMMER", "IMAGE_VERTEX_FORMAT", "IMAGE", "GLASS", "RECT_DEFAULT_PAGES", "[Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "OUTLINE_360", "VEIL_UNION", "SHAPE", "RADIAL_GLASS", "OUTLINE_GLASS", "HALF_ICON_VERTEX_FORMAT", "RECT_HALF_ICON", "SECTOR_MASK", "IMAGE_ADDITIVE", "rtx.kimiko:kimiko"})
public final class ClientPipelines2D {
    @NotNull
    public static final ClientPipelines2D INSTANCE = new ClientPipelines2D();
    @NotNull
    private static final VertexFormat RIPPLE_VERTEX_FORMAT;
    @NotNull
    private static final VertexFormat SHIMMER_VERTEX_FORMAT;
    @JvmField
    @NotNull
    public static final RenderPipeline ARC_DIVIDER;
    @JvmField
    @NotNull
    public static final RenderPipeline CIRCLE;
    @JvmField
    @NotNull
    public static final RenderPipeline ARC_RECT;
    @JvmField
    @NotNull
    public static final RenderPipeline LINE;
    @JvmField
    @NotNull
    public static final RenderPipeline PICKER;
    @JvmField
    @NotNull
    public static final RenderPipeline ZIPPY;
    @JvmField
    @NotNull
    public static final RenderPipeline TABLE_PANEL;
    @JvmField
    @NotNull
    public static final RenderPipeline RECT_HALFTONE;
    @JvmField
    @NotNull
    public static final RenderPipeline OUTLINE_DEFAULT;
    @JvmField
    @NotNull
    public static final RenderPipeline RIPPLE;
    @JvmField
    @NotNull
    public static final RenderPipeline SHIMMER;
    @NotNull
    private static final VertexFormat IMAGE_VERTEX_FORMAT;
    @JvmField
    @NotNull
    public static final RenderPipeline IMAGE;
    @JvmField
    @NotNull
    public static final RenderPipeline GLASS;
    @JvmField
    @NotNull
    public static final RenderPipeline[] RECT_DEFAULT_PAGES;
    @JvmField
    @NotNull
    public static final RenderPipeline OUTLINE_360;
    @JvmField
    @NotNull
    public static final RenderPipeline VEIL_UNION;
    @JvmField
    @NotNull
    public static final RenderPipeline SHAPE;
    @JvmField
    @NotNull
    public static final RenderPipeline RADIAL_GLASS;
    @JvmField
    @NotNull
    public static final RenderPipeline OUTLINE_GLASS;
    @NotNull
    private static final VertexFormat HALF_ICON_VERTEX_FORMAT;
    @JvmField
    @NotNull
    public static final RenderPipeline RECT_HALF_ICON;
    @JvmField
    @NotNull
    public static final RenderPipeline SECTOR_MASK;
    @JvmField
    @NotNull
    public static final RenderPipeline IMAGE_ADDITIVE;

    private ClientPipelines2D() {
    }

    @NotNull
    public final RenderPipeline standard(@NotNull String path, @NotNull String[] uniformArrayNames, @NotNull VertexFormat vertexFormat, @NotNull VertexFormat.DrawMode mode, @NotNull String shaderPath, @NotNull BlendFunction blend, @NotNull String[] samplers) {
        Intrinsics.checkNotNullParameter((Object)path, (String)"path");
        Intrinsics.checkNotNullParameter((Object)uniformArrayNames, (String)"uniformArrayNames");
        Intrinsics.checkNotNullParameter((Object)vertexFormat, (String)"vertexFormat");
        Intrinsics.checkNotNullParameter((Object)mode, (String)"mode");
        Intrinsics.checkNotNullParameter((Object)shaderPath, (String)"shaderPath");
        Intrinsics.checkNotNullParameter((Object)blend, (String)"blend");
        Intrinsics.checkNotNullParameter((Object)samplers, (String)"samplers");
        RenderPipeline.Builder builder = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(this.id("pipeline/" + path)).withVertexShader(this.id(shaderPath)).withFragmentShader(this.id(shaderPath)).withVertexFormat(vertexFormat, mode).withBlend(blend).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false);
        Intrinsics.checkNotNullExpressionValue((Object)builder, (String)"withCull(...)");
        RenderPipeline.Builder builder2 = builder;
        for (String sampler : samplers) {
            builder2.withSampler(sampler);
        }
        builder2.withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER);
        for (String name : uniformArrayNames) {
            builder2.withUniform(name, UniformType.UNIFORM_BUFFER);
        }
        RenderPipeline renderPipeline = builder2.build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline, (String)"build(...)");
        return renderPipeline;
    }

    public static /* synthetic */ RenderPipeline standard$default(ClientPipelines2D clientPipelines2D, String string, String[] stringArray, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode2, String object, BlendFunction blendFunction, String[] stringArray2, int n, Object object2) {
        if ((n & 4) != 0) {
            VertexFormat vertexFormat2 = VertexFormats.POSITION_COLOR_LINE_WIDTH;
            Intrinsics.checkNotNullExpressionValue((Object)vertexFormat2, (String)"POSITION_COLOR_LINE_WIDTH");
            vertexFormat = vertexFormat2;
        }
        if ((n & 8) != 0) {
            drawMode2 = VertexFormat.DrawMode.QUADS;
        }
        if ((n & 0x10) != 0) {
            object = "core/" + string;
        }
        if ((n & 0x20) != 0) {
            BlendFunction blendFunction2 = BlendFunction.TRANSLUCENT;
            Intrinsics.checkNotNullExpressionValue((Object)blendFunction2, (String)"TRANSLUCENT");
            blendFunction = blendFunction2;
        }
        if ((n & 0x40) != 0) {
            stringArray2 = new String[]{};
        }
        return clientPipelines2D.standard(string, stringArray, vertexFormat, drawMode2, (String)object, blendFunction, stringArray2);
    }

    private final Identifier id(String path) {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        return identifier2;
    }

    static {
        VertexFormat vertexFormat = VertexFormat.builder().add("Position", VertexFormatElement.POSITION).add("UV0", VertexFormatElement.UV0).add("LineWidth", VertexFormatElement.LINE_WIDTH).build();
        Intrinsics.checkNotNullExpressionValue((Object)vertexFormat, (String)"build(...)");
        RIPPLE_VERTEX_FORMAT = vertexFormat;
        VertexFormat vertexFormat2 = VertexFormat.builder().add("Position", VertexFormatElement.POSITION).add("UV0", VertexFormatElement.UV0).add("Color", VertexFormatElement.COLOR).add("LineWidth", VertexFormatElement.LINE_WIDTH).build();
        Intrinsics.checkNotNullExpressionValue((Object)vertexFormat2, (String)"build(...)");
        SHIMMER_VERTEX_FORMAT = vertexFormat2;
        String[] stringArray = new String[]{"ArcParamsArray"};
        ARC_DIVIDER = ClientPipelines2D.standard$default(INSTANCE, "arc_divider", stringArray, null, null, null, null, null, 124, null);
        stringArray = new String[]{"CircleParamsArray"};
        CIRCLE = ClientPipelines2D.standard$default(INSTANCE, "circle", stringArray, null, null, null, null, null, 124, null);
        stringArray = new String[]{"ArcRectParamsArray"};
        ARC_RECT = ClientPipelines2D.standard$default(INSTANCE, "arc_rect", stringArray, null, null, null, null, null, 124, null);
        stringArray = new String[]{"LineParamsArray"};
        LINE = ClientPipelines2D.standard$default(INSTANCE, "line", stringArray, null, null, null, null, null, 124, null);
        stringArray = new String[]{"PickerParamsArray"};
        PICKER = ClientPipelines2D.standard$default(INSTANCE, "picker", stringArray, null, null, null, null, null, 124, null);
        stringArray = new String[]{"ZippyParamsArray"};
        ZIPPY = ClientPipelines2D.standard$default(INSTANCE, "zippy", stringArray, null, null, null, null, null, 124, null);
        stringArray = new String[]{"TablePanelParamsArray"};
        TABLE_PANEL = ClientPipelines2D.standard$default(INSTANCE, "table_panel", stringArray, null, null, null, null, null, 124, null);
        stringArray = new String[]{"HalftoneRectangleParamsArray"};
        RECT_HALFTONE = ClientPipelines2D.standard$default(INSTANCE, "rect_halftone", stringArray, null, null, null, null, null, 124, null);
        stringArray = new String[]{"OutlineParamsArray", "PaletteParams"};
        OUTLINE_DEFAULT = ClientPipelines2D.standard$default(INSTANCE, "outline_default", stringArray, null, null, null, null, null, 124, null);
        stringArray = new String[]{"RippleParamsArray"};
        RIPPLE = ClientPipelines2D.standard$default(INSTANCE, "ripple", stringArray, RIPPLE_VERTEX_FORMAT, null, null, null, null, 120, null);
        stringArray = new String[]{"ShimmerParamsArray"};
        SHIMMER = ClientPipelines2D.standard$default(INSTANCE, "shimmer", stringArray, SHIMMER_VERTEX_FORMAT, null, null, null, null, 120, null);
        VertexFormat vertexFormat3 = VertexFormat.builder().add("Position", VertexFormatElement.POSITION).add("UV0", VertexFormatElement.UV0).add("Color", VertexFormatElement.COLOR).add("LineWidth", VertexFormatElement.LINE_WIDTH).build();
        Intrinsics.checkNotNullExpressionValue((Object)vertexFormat3, (String)"build(...)");
        IMAGE_VERTEX_FORMAT = vertexFormat3;
        stringArray = new String[]{"ImageParamsArray"};
        String[] stringArray2 = stringArray;
        stringArray = new String[]{"Sampler0"};
        IMAGE = ClientPipelines2D.standard$default(INSTANCE, "image", stringArray2, IMAGE_VERTEX_FORMAT, null, null, null, stringArray, 56, null);
        stringArray = new String[]{"GlassParamsArray", "PaletteParams", "SplitParams"};
        String[] stringArray3 = stringArray;
        stringArray = new String[]{"Sampler0"};
        GLASS = ClientPipelines2D.standard$default(INSTANCE, "glass", stringArray3, null, null, "ui/glass/glass", null, stringArray, 44, null);
        RenderPipeline[] renderPipelineArray = new RenderPipeline[16];
        for (int i = 0; i < 16; ++i) {
            String[] stringArray4 = new String[]{"RectangleParamsArray", "PaletteParams"};
            int n = i;
            renderPipelineArray[n] = ClientPipelines2D.standard$default(INSTANCE, (String)(n == 0 ? "rect_default" : "rect_default_" + n), stringArray4, null, null, "core/rect_default", null, null, 108, null);
        }
        RECT_DEFAULT_PAGES = renderPipelineArray;
        String[] stringArray5 = new String[]{"Outline360ParamsArray", "Outline360RangesArray"};
        OUTLINE_360 = ClientPipelines2D.standard$default(INSTANCE, "outline_360", stringArray5, null, null, null, null, null, 124, null);
        stringArray5 = new String[]{"VeilParamsArray"};
        VEIL_UNION = ClientPipelines2D.standard$default(INSTANCE, "veil_union", stringArray5, null, null, null, null, null, 124, null);
        stringArray5 = new String[]{"ShapeParamsArray", "PaletteParams", "SplitParams"};
        String[] stringArray6 = stringArray5;
        stringArray5 = new String[]{"Sampler0"};
        SHAPE = ClientPipelines2D.standard$default(INSTANCE, "shape", stringArray6, null, null, "ui/shape/shape", null, stringArray5, 44, null);
        stringArray5 = new String[]{"RadialGlassParamsArray", "PaletteParams"};
        String[] stringArray7 = stringArray5;
        stringArray5 = new String[]{"Sampler0"};
        RADIAL_GLASS = ClientPipelines2D.standard$default(INSTANCE, "radial_glass", stringArray7, null, null, "ui/radialglass/radialglass", null, stringArray5, 44, null);
        stringArray5 = new String[]{"GlassOutlineParamsArray"};
        String[] stringArray8 = stringArray5;
        stringArray5 = new String[]{"Sampler0"};
        OUTLINE_GLASS = ClientPipelines2D.standard$default(INSTANCE, "outline_glass", stringArray8, null, null, null, null, stringArray5, 60, null);
        VertexFormat vertexFormat4 = VertexFormat.builder().add("Position", VertexFormatElement.POSITION).add("UV0", VertexFormatElement.UV0).add("Color", VertexFormatElement.COLOR).add("LineWidth", VertexFormatElement.LINE_WIDTH).build();
        Intrinsics.checkNotNullExpressionValue((Object)vertexFormat4, (String)"build(...)");
        HALF_ICON_VERTEX_FORMAT = vertexFormat4;
        stringArray5 = new String[]{"RectHalfIconParamsArray"};
        String[] stringArray9 = stringArray5;
        stringArray5 = new String[]{"Sampler0"};
        RECT_HALF_ICON = ClientPipelines2D.standard$default(INSTANCE, "rect_half_icon", stringArray9, HALF_ICON_VERTEX_FORMAT, null, null, null, stringArray5, 56, null);
        stringArray5 = new String[]{"SectorMaskParamsArray"};
        String[] stringArray10 = stringArray5;
        stringArray5 = new String[]{"Sampler0"};
        SECTOR_MASK = ClientPipelines2D.standard$default(INSTANCE, "sector_mask", stringArray10, null, null, "ui/sectormask/sectormask", null, stringArray5, 44, null);
        stringArray5 = new String[]{"ImageParamsArray"};
        String[] stringArray11 = stringArray5;
        BlendFunction blendFunction = BlendFunction.LIGHTNING;
        Intrinsics.checkNotNullExpressionValue((Object)blendFunction, (String)"LIGHTNING");
        stringArray5 = new String[]{"Sampler0"};
        IMAGE_ADDITIVE = ClientPipelines2D.standard$default(INSTANCE, "image_additive", stringArray11, IMAGE_VERTEX_FORMAT, null, "core/image", blendFunction, stringArray5, 8, null);
    }
}

