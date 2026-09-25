/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBuffer
 *  com.mojang.blaze3d.buffers.GpuBufferSlice
 *  com.mojang.blaze3d.pipeline.BlendFunction
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Snippet
 *  com.mojang.blaze3d.platform.DepthTestFunction
 *  com.mojang.blaze3d.systems.CommandEncoder
 *  com.mojang.blaze3d.systems.RenderPass
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.systems.RenderSystem$ShapeIndexBuffer
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  com.mojang.blaze3d.vertex.VertexFormat
 *  com.mojang.blaze3d.vertex.VertexFormat$DrawMode
 *  com.mojang.blaze3d.vertex.VertexFormatElement
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  com.mojang.blaze3d.systems.ProjectionType
 *  net.minecraft.client.texture.AbstractTexture
 *  net.minecraft.client.gl.UniformType
 *  net.minecraft.client.render.ProjectionMatrix2
 *  net.minecraft.text.Text
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.render.BufferBuilder
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.font.TextRenderer.TextLayerType
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.VertexConsumerProvider
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.client.util.BufferAllocator
 *  net.minecraft.client.render.BuiltBuffer
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fStack
 *  org.joml.Matrix4fc
 *  org.joml.Vector3f
 *  org.joml.Vector3fc
 *  org.joml.Vector4f
 *  org.joml.Vector4fc
 */
package rtx.kimiko.utils.render.util.underhand;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import java.nio.ByteBuffer;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import com.mojang.blaze3d.systems.ProjectionType;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.render.ProjectionMatrix2;
import net.minecraft.text.Text;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.BufferAllocator;
import net.minecraft.client.render.BuiltBuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector4f;
import org.joml.Vector4fc;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.events.Event;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.impl.render.UnderHandRenderEvent;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfFont;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfFonts;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfQuadLayout;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfTextRenderer;
import rtx.kimiko.utils.render.others.RenderSampler;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000 S2\u00020\u0001:\u0001SB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0006\u00a2\u0006\u0004\b\t\u0010\u0003J=\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0012\u0010\u0013J?\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0019\u0010\u001aJW\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0019\u0010\u001fJ'\u0010 \u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0018\u001a\u00020\n\u00a2\u0006\u0004\b \u0010!J7\u0010$\u001a\u00020\u00062\b\u0010\u0017\u001a\u0004\u0018\u00010\"2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010#\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b$\u0010%J\u000f\u0010&\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b&\u0010\u0003J\u000f\u0010'\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b'\u0010\u0003JS\u00104\u001a\u00020\u00062\u0006\u0010)\u001a\u00020(2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00162\b\u0010,\u001a\u0004\u0018\u00010+2\u0006\u0010.\u001a\u00020-2\b\u00100\u001a\u0004\u0018\u00010/2\u0006\u00102\u001a\u0002012\u0006\u00103\u001a\u000201H\u0002\u00a2\u0006\u0004\b4\u00105J!\u00107\u001a\u0004\u0018\u0001062\u0006\u0010*\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b7\u00108J\u0019\u00109\u001a\u0004\u0018\u00010/2\u0006\u0010\u0015\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b9\u0010:J\u000f\u0010;\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b;\u0010\u0003R\u0014\u0010=\u001a\u00020<8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u0010>R\u0014\u0010@\u001a\u00020?8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b@\u0010AR \u0010C\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020?0B8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bC\u0010DR \u0010F\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020E0B8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bF\u0010DR0\u0010I\u001a\u001e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u0002060Gj\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u000206`H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u0010JR\u0018\u0010K\u001a\u0004\u0018\u00010E8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010LR\u0016\u0010N\u001a\u00020M8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bN\u0010OR\u0018\u0010P\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bP\u0010QR\u0014\u0010R\u001a\u00020?8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bR\u0010A\u00a8\u0006T"}, d2={"Lrtx/kimiko/utils/render/util/underhand/UnderHand2D;", "", "<init>", "()V", "Lnet/minecraft/Framebuffer;", "main", "", "begin", "(Lnet/minecraft/Framebuffer;)V", "barrier", "", "x", "y", "w", "h", "radius", "", "argb", "rect", "(FFFFFI)V", "Lrtx/kimiko/utils/render/fonts/Fonts;", "fontName", "", "text", "size", "msdfText", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;FFFI)V", "colorTopLeft", "colorTopRight", "colorBottomRight", "colorBottomLeft", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;FFFIIII)V", "msdfWidth", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;F)F", "Lnet/minecraft/Text;", "scale", "nativeText", "(Lnet/minecraft/Text;FFFI)V", "flush", "drawPending", "Lcom/mojang/blaze3d/systems/CommandEncoder;", "encoder", "key", "Lnet/minecraft/BuiltBuffer;", "mesh", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "texture", "Lcom/mojang/blaze3d/buffers/GpuBufferSlice;", "proj", "transforms", "drawMesh", "(Lcom/mojang/blaze3d/systems/CommandEncoder;Lnet/minecraft/Framebuffer;Ljava/lang/String;Lnet/minecraft/BuiltBuffer;Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/buffers/GpuBufferSlice;Lcom/mojang/blaze3d/buffers/GpuBufferSlice;)V", "Lcom/mojang/blaze3d/buffers/GpuBuffer;", "ensureVertexBuffer", "(Ljava/lang/String;I)Lcom/mojang/blaze3d/buffers/GpuBuffer;", "resolveFontTexture", "(Lrtx/kimiko/utils/render/fonts/Fonts;)Lcom/mojang/blaze3d/textures/GpuTextureView;", "discardBuilders", "Lnet/minecraft/ProjectionMatrix2;", "projection", "Lnet/minecraft/ProjectionMatrix2;", "Lnet/minecraft/BufferAllocator;", "rectBytes", "Lnet/minecraft/BufferAllocator;", "", "textArenas", "Ljava/util/Map;", "Lnet/minecraft/BufferBuilder;", "textBuilders", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "vertexBuffers", "Ljava/util/HashMap;", "rectBuilder", "Lnet/minecraft/BufferBuilder;", "", "active", "Z", "frameTarget", "Lnet/minecraft/Framebuffer;", "nativeBytes", "Companion", "rtx.kimiko:kimiko"})
public final class UnderHand2D {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ProjectionMatrix2 projection = new ProjectionMatrix2("kimiko_underhand", 1000.0f, 11000.0f, true);
    @NotNull
    private final BufferAllocator rectBytes = new BufferAllocator(65536);
    @NotNull
    private final Map<Fonts, BufferAllocator> textArenas = new EnumMap(Fonts.class);
    @NotNull
    private final Map<Fonts, BufferBuilder> textBuilders = new LinkedHashMap();
    @NotNull
    private final HashMap<String, GpuBuffer> vertexBuffers = new HashMap();
    @Nullable
    private BufferBuilder rectBuilder;
    private boolean active;
    @Nullable
    private Framebuffer frameTarget;
    @NotNull
    private final BufferAllocator nativeBytes = new BufferAllocator(65536);
    @NotNull
    private static final VertexFormat RECT_FORMAT;
    @NotNull
    private static final RenderPipeline RECT_PIPELINE;
    @NotNull
    private static final UnderHand2D INSTANCE;

    private UnderHand2D() {
    }

    private final void begin(Framebuffer main) {
        this.active = true;
        this.frameTarget = main;
        this.rectBuilder = null;
        this.textBuilders.clear();
    }

    public final void barrier() {
        if (!this.active) {
            return;
        }
        this.drawPending();
    }

    public final void rect(float x, float y, float w, float h, float radius, int argb) {
        if (!this.active || w <= 0.0f || h <= 0.0f || argb >>> 24 == 0) {
            return;
        }
        BufferBuilder builder = this.rectBuilder;
        if (builder == null) {
            this.rectBuilder = builder = new BufferBuilder(this.rectBytes, VertexFormat.DrawMode.QUADS, RECT_FORMAT);
        }
        float r = Math.min(radius, Math.min(w, h) * 0.5f);
        int hw = Math.min(Short.MAX_VALUE, Math.round(w * 8.0f));
        int hh = Math.min(Short.MAX_VALUE, Math.round(h * 8.0f));
        builder.vertex(x, y, r).texture(0.0f, 0.0f).overlay(hw, hh).color(argb);
        builder.vertex(x, y + h, r).texture(0.0f, h).overlay(hw, hh).color(argb);
        builder.vertex(x + w, y + h, r).texture(w, h).overlay(hw, hh).color(argb);
        builder.vertex(x + w, y, r).texture(w, 0.0f).overlay(hw, hh).color(argb);
    }

    public final void msdfText(@NotNull Fonts fontName, @Nullable String text, float x, float y, float size, int argb) {
        Intrinsics.checkNotNullParameter((Object)((Object)fontName), (String)"fontName");
        this.msdfText(fontName, text, x, y, size, argb, argb, argb, argb);
    }

    public final void msdfText(@NotNull Fonts fontName, @Nullable String text, float x, float y, float size, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft) {
        CharSequence charSequence;
        Intrinsics.checkNotNullParameter((Object)((Object)fontName), (String)"fontName");
        int alphaMask = colorTopLeft | colorTopRight | colorBottomRight | colorBottomLeft;
        if (!this.active || (charSequence = (CharSequence)text) == null || charSequence.length() == 0 || alphaMask >>> 24 == 0) {
            return;
        }
        MsdfFont msdfFont = MsdfFonts.get(fontName);
        if (msdfFont == null) {
            return;
        }
        MsdfFont font = msdfFont;
        BufferBuilder builder = this.textBuilders.computeIfAbsent(fontName, k -> new BufferBuilder(this.textArenas.computeIfAbsent(k, f -> new BufferAllocator(65536)), VertexFormat.DrawMode.QUADS, MsdfTextRenderer.MSDF_SHIMMER_VERTEX_FORMAT));
        MsdfQuadLayout.layout(font, text, x, y, size, colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft, (VertexConsumer)builder);
    }

    public final float msdfWidth(@NotNull Fonts fontName, @Nullable String text, float size) {
        Intrinsics.checkNotNullParameter((Object)((Object)fontName), (String)"fontName");
        return fontName.msdfWidth(text, size);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void nativeText(@Nullable Text text, float x, float y, float scale, int argb) {
        if (!this.active || text == null || argb >>> 24 == 0) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        float guiW = Position.Companion.screenWidth();
        float guiH = Position.Companion.screenHeight();
        if (guiW < 1.0f || guiH < 1.0f) {
            return;
        }
        this.drawPending();
        Matrix4fStack matrix4fStack = RenderSystem.getModelViewStack();
        Intrinsics.checkNotNullExpressionValue((Object)matrix4fStack, (String)"getModelViewStack(...)");
        Matrix4fStack modelView = matrix4fStack;
        modelView.pushMatrix();
        modelView.identity();
        try {
            RenderSystem.backupProjectionMatrix();
            RenderSystem.setProjectionMatrix((GpuBufferSlice)this.projection.set(guiW, guiH), (ProjectionType)ProjectionType.ORTHOGRAPHIC);
            Matrix4f pose = new Matrix4f().translation(x, y, -2000.0f).scale(scale, scale, 1.0f);
            VertexConsumerProvider.Immediate immediate2 = VertexConsumerProvider.immediate((BufferAllocator)this.nativeBytes);
            Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"immediate(...)");
            VertexConsumerProvider.Immediate buffer = immediate2;
            mc.textRenderer.draw(text, 0.0f, 0.0f, argb, false, pose, (VertexConsumerProvider)buffer, TextRenderer.TextLayerType.SEE_THROUGH, 0, 0xF000F0);
            buffer.draw();
        }
        catch (Throwable throwable) {
        }
        finally {
            RenderSystem.restoreProjectionMatrix();
            modelView.popMatrix();
        }
    }

    private final void flush() {
        this.drawPending();
        this.active = false;
        this.frameTarget = null;
    }

    private final void drawPending() {
        Framebuffer main = this.frameTarget;
        if (main == null || this.rectBuilder == null && this.textBuilders.isEmpty()) {
            return;
        }
        float guiW = Position.Companion.screenWidth();
        float guiH = Position.Companion.screenHeight();
        if (guiW < 1.0f || guiH < 1.0f) {
            this.discardBuilders();
            return;
        }
        try {
            GpuBufferSlice gpuBufferSlice = this.projection.set(guiW, guiH);
            Intrinsics.checkNotNullExpressionValue((Object)gpuBufferSlice, (String)"getBuffer(...)");
            GpuBufferSlice proj = gpuBufferSlice;
            GpuBufferSlice gpuBufferSlice2 = RenderSystem.getDynamicUniforms().write((Matrix4fc)new Matrix4f().translation(0.0f, 0.0f, -11000.0f), (Vector4fc)new Vector4f(1.0f, 1.0f, 1.0f, 1.0f), (Vector3fc)new Vector3f(), (Matrix4fc)new Matrix4f());
            Intrinsics.checkNotNullExpressionValue((Object)gpuBufferSlice2, (String)"writeTransform(...)");
            GpuBufferSlice transforms = gpuBufferSlice2;
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            CommandEncoder encoder = commandEncoder;
            BufferBuilder builder = this.rectBuilder;
            if (builder != null) {
                this.drawMesh(encoder, main, "rect", builder.endNullable(), RECT_PIPELINE, null, proj, transforms);
                this.rectBuilder = null;
            }
            for (Map.Entry<Fonts, BufferBuilder> entry : this.textBuilders.entrySet()) {
                Fonts key = entry.getKey();
                BufferBuilder value = entry.getValue();
                BuiltBuffer mesh = value.endNullable();
                GpuTextureView texture = this.resolveFontTexture(key);
                if (texture == null) {
                    BuiltBuffer builtBuffer2 = mesh;
                    if (builtBuffer2 != null) {
                        builtBuffer2.close();
                    }
                    continue;
                }
                this.drawMesh(encoder, main, "text_" + key.id(), mesh, MsdfTextRenderer.MSDF_PIPELINE, texture, proj, transforms);
            }
            this.textBuilders.clear();
        }
        catch (Throwable throwable) {
            this.discardBuilders();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void drawMesh(CommandEncoder encoder, Framebuffer main, String key, BuiltBuffer mesh, RenderPipeline pipeline, GpuTextureView texture, GpuBufferSlice proj, GpuBufferSlice transforms) {
        if (mesh == null) {
            return;
        }
        try {
            int indexCount = mesh.getDrawParameters().indexCount();
            ByteBuffer byteBuffer = mesh.getBuffer();
            Intrinsics.checkNotNullExpressionValue((Object)byteBuffer, (String)"vertexBuffer(...)");
            ByteBuffer vertices = byteBuffer;
            GpuBuffer vb = this.ensureVertexBuffer(key, vertices.remaining());
            if (vb == null) {
                return;
            }
            encoder.writeToBuffer(vb.slice(0L, (long)vertices.remaining()), vertices);
            RenderSystem.ShapeIndexBuffer shapeIndexBuffer2 = RenderSystem.getSequentialBuffer((VertexFormat.DrawMode)VertexFormat.DrawMode.QUADS);
            Intrinsics.checkNotNullExpressionValue((Object)shapeIndexBuffer2, (String)"getSequentialBuffer(...)");
            RenderSystem.ShapeIndexBuffer sequential = shapeIndexBuffer2;
            GpuBuffer gpuBuffer2 = sequential.getIndexBuffer(indexCount);
            Intrinsics.checkNotNullExpressionValue((Object)gpuBuffer2, (String)"getBuffer(...)");
            GpuBuffer indices = gpuBuffer2;
            Supplier<String> supplier = () -> UnderHand2D.drawMesh$lambda$0(key);
            GpuTextureView gpuTextureView = main.getColorAttachmentView();
            Intrinsics.checkNotNull((Object)gpuTextureView);
            AutoCloseable autoCloseable = (AutoCloseable)encoder.createRenderPass(supplier, gpuTextureView, OptionalInt.empty());
            Throwable throwable = null;
            try {
                RenderPass pass = (RenderPass)autoCloseable;
                boolean bl = false;
                pass.setPipeline(pipeline);
                pass.setVertexBuffer(0, vb);
                pass.setIndexBuffer(indices, sequential.getIndexType());
                pass.setUniform("Projection", proj);
                pass.setUniform("DynamicTransforms", transforms);
                if (texture != null) {
                    pass.bindTexture("Sampler0", texture, RenderSampler.linear());
                }
                pass.drawIndexed(0, 0, indexCount, 1);
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
        finally {
            mesh.close();
        }
    }

    private final GpuBuffer ensureVertexBuffer(String key, int size) {
        GpuBuffer buffer = this.vertexBuffers.get(key);
        if (buffer != null && !buffer.isClosed() && buffer.size() >= (long)size) {
            return buffer;
        }
        GpuBuffer gpuBuffer = buffer;
        if (gpuBuffer != null) {
            gpuBuffer.close();
        }
        int capacity = Math.max(size, 4096);
        GpuBuffer gpuBuffer2 = RenderSystem.getDevice().createBuffer(() -> UnderHand2D.ensureVertexBuffer$lambda$0(key), 40, (long)capacity);
        Intrinsics.checkNotNullExpressionValue((Object)gpuBuffer2, (String)"createBuffer(...)");
        GpuBuffer created = gpuBuffer2;
        ((Map)this.vertexBuffers).put(key, created);
        return created;
    }

    private final GpuTextureView resolveFontTexture(Fonts fontName) {
        MsdfFont msdfFont = MsdfFonts.get(fontName);
        if (msdfFont == null) {
            return null;
        }
        MsdfFont font = msdfFont;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        AbstractTexture abstractTexture3 = mc.getTextureManager().getTexture(font.atlasTexture());
        Intrinsics.checkNotNullExpressionValue((Object)abstractTexture3, (String)"getTexture(...)");
        AbstractTexture texture = abstractTexture3;
        return texture.getGlTextureView();
    }

    private final void discardBuilders() {
        BufferBuilder builder = this.rectBuilder;
        if (builder != null) {
            BuiltBuffer builtBuffer2 = builder.endNullable();
            if (builtBuffer2 != null) {
                builtBuffer2.close();
            }
            this.rectBuilder = null;
        }
        for (BufferBuilder value : this.textBuilders.values()) {
            BuiltBuffer builtBuffer3 = value.endNullable();
            if (builtBuffer3 == null) continue;
            builtBuffer3.close();
        }
        this.textBuilders.clear();
    }

    private static final String drawMesh$lambda$0(String $key) {
        return "kimiko:underhand_" + $key;
    }

    private static final String ensureVertexBuffer$lambda$0(String $key) {
        return "kimiko:underhand_vb_" + $key;
    }

    @JvmStatic
    public static final void renderNow() {
        Companion.renderNow();
    }

    static {
        VertexFormat vertexFormat = VertexFormat.builder().add("Position", VertexFormatElement.POSITION).add("UV0", VertexFormatElement.UV0).add("UV1", VertexFormatElement.UV1).add("Color", VertexFormatElement.COLOR).build();
        Intrinsics.checkNotNullExpressionValue((Object)vertexFormat, (String)"build(...)");
        RECT_FORMAT = vertexFormat;
        RenderPipeline renderPipeline = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(UnderHand2D.Companion.id("pipeline/underhand_rect")).withVertexShader(UnderHand2D.Companion.id("core/underhand_rect")).withFragmentShader(UnderHand2D.Companion.id("core/underhand_rect")).withVertexFormat(RECT_FORMAT, VertexFormat.DrawMode.QUADS).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline, (String)"build(...)");
        RECT_PIPELINE = renderPipeline;
        INSTANCE = new UnderHand2D();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/utils/render/util/underhand/UnderHand2D.Companion;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "renderNow", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "Lcom/mojang/blaze3d/vertex/VertexFormat;", "RECT_FORMAT", "Lcom/mojang/blaze3d/vertex/VertexFormat;", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "RECT_PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lrtx/kimiko/utils/render/util/underhand/UnderHand2D;", "INSTANCE", "Lrtx/kimiko/utils/render/util/underhand/UnderHand2D;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        public final void renderNow() {
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient mc = minecraftClient2;
            if (mc.player == null || mc.world == null || mc.options.hudHidden) {
                return;
            }
            Framebuffer framebuffer2 = mc.getFramebuffer();
            Intrinsics.checkNotNullExpressionValue((Object)framebuffer2, (String)"getMainRenderTarget(...)");
            Framebuffer main = framebuffer2;
            if (main.getColorAttachmentView() == null) {
                return;
            }
            UnderHand2D ctx = INSTANCE;
            ctx.begin(main);
            try {
                UnderHandRenderEvent cfr_ignored_0 = (UnderHandRenderEvent)EventBus.Companion.get().post((Event)new UnderHandRenderEvent(ctx));
            }
            finally {
                ctx.flush();
            }
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
}

