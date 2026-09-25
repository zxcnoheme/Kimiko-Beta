/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.gui.ScreenRect
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fc
 */
package rtx.kimiko.utils.render.render2d.rectangle.recthalficon;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.gui.ScreenRect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;
import rtx.kimiko.utils.render.render2d.PoseCache;
import rtx.kimiko.utils.render.render2d.rectangle.recthalficon.BuiltHalfIconRectangle;
import rtx.kimiko.utils.render.render2d.rectangle.recthalficon.HalfIconRectangleBatch;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001:\u000256B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J#\u0010\u000e\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016JG\u0010\u001f\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\u000f\u0010\"\u001a\u00020!H\u0016\u00a2\u0006\u0004\b\"\u0010#J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010$J\u0011\u0010&\u001a\u0004\u0018\u00010%H\u0016\u00a2\u0006\u0004\b&\u0010'J\u0011\u0010(\u001a\u0004\u0018\u00010%H\u0016\u00a2\u0006\u0004\b(\u0010'R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010)R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010*R$\u0010.\u001a\u0012\u0012\u0004\u0012\u00020,0+j\b\u0012\u0004\u0012\u00020,`-8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b.\u0010/R\u0016\u00100\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0016\u00102\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u00101R\u0016\u00103\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u00101R\u0016\u00104\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u00101\u00a8\u00067"}, d2={"Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderState;", "Lnet/minecraft/SimpleGuiElementRenderState;", "Lorg/joml/Matrix3x2f;", "pose", "Lnet/minecraft/TextureSetup;", "textureSetup", "<init>", "(Lorg/joml/Matrix3x2f;Lnet/minecraft/TextureSetup;)V", "Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/BuiltHalfIconRectangle;", "rectangle", "", "Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderState$IconQuad;", "quads", "", "add", "(Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/BuiltHalfIconRectangle;Ljava/util/List;)V", "", "empty", "()Z", "Lnet/minecraft/VertexConsumer;", "consumer", "buildVertices", "(Lnet/minecraft/VertexConsumer;)V", "", "x", "y", "u", "v", "", "color", "batchIndex", "vertex", "(Lnet/minecraft/VertexConsumer;FFFFII)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "()Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "()Lnet/minecraft/TextureSetup;", "Lnet/minecraft/ScreenRect;", "scissorArea", "()Lnet/minecraft/ScreenRect;", "bounds", "Lnet/minecraft/TextureSetup;", "Lorg/joml/Matrix3x2f;", "Ljava/util/ArrayList;", "Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderState$PatternBatch;", "Lkotlin/collections/ArrayList;", "batches", "Ljava/util/ArrayList;", "minX", "F", "minY", "maxX", "maxY", "IconQuad", "PatternBatch", "rtx.kimiko:kimiko"})
public final class HalfIconRectangleRenderState
implements SimpleGuiElementRenderState {
    @NotNull
    private final TextureSetup textureSetup;
    @NotNull
    private final Matrix3x2f pose;
    @NotNull
    private final ArrayList<PatternBatch> batches;
    private float minX;
    private float minY;
    private float maxX;
    private float maxY;

    public HalfIconRectangleRenderState(@NotNull Matrix3x2f pose, @NotNull TextureSetup textureSetup) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)textureSetup, (String)"textureSetup");
        this.textureSetup = textureSetup;
        this.pose = PoseCache.snapshot(pose);
        this.batches = new ArrayList(8);
        this.minX = Float.MAX_VALUE;
        this.minY = Float.MAX_VALUE;
        this.maxX = -3.4028235E38f;
        this.maxY = -3.4028235E38f;
    }

    public final void add(@NotNull BuiltHalfIconRectangle rectangle, @NotNull List<IconQuad> quads) {
        Intrinsics.checkNotNullParameter((Object)rectangle, (String)"rectangle");
        Intrinsics.checkNotNullParameter(quads, (String)"quads");
        if (quads.isEmpty()) {
            return;
        }
        this.batches.add(new PatternBatch(rectangle, quads));
        this.minX = Math.min(this.minX, rectangle.x());
        this.minY = Math.min(this.minY, rectangle.y());
        this.maxX = Math.max(this.maxX, rectangle.x() + rectangle.width());
        this.maxY = Math.max(this.maxY, rectangle.y() + rectangle.height());
    }

    public final boolean empty() {
        return this.batches.isEmpty();
    }

    public void setupVertices(@NotNull VertexConsumer consumer) {
        Intrinsics.checkNotNullParameter((Object)consumer, (String)"consumer");
        Iterator<PatternBatch> iterator = this.batches.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<PatternBatch> iterator2 = iterator;
        while (iterator2.hasNext()) {
            PatternBatch batch = (PatternBatch) (iterator2.next());
            int batchIndex = HalfIconRectangleBatch.INSTANCE.reserve(batch.getRectangle());
            if (batchIndex < 0) continue;
            for (IconQuad quad : batch.getQuads()) {
                this.vertex(consumer, quad.x0(), quad.y0(), quad.u0(), quad.v0(), quad.color(), batchIndex);
                this.vertex(consumer, quad.x1(), quad.y1(), quad.u1(), quad.v1(), quad.color(), batchIndex);
                this.vertex(consumer, quad.x2(), quad.y2(), quad.u2(), quad.v2(), quad.color(), batchIndex);
                this.vertex(consumer, quad.x3(), quad.y3(), quad.u3(), quad.v3(), quad.color(), batchIndex);
            }
        }
    }

    private final void vertex(VertexConsumer consumer, float x, float y, float u, float v, int color, int batchIndex) {
        consumer.vertex((Matrix3x2fc)this.pose, x, y).texture(u, v).color(color).lineWidth((float)(batchIndex + 1));
    }

    @NotNull
    public RenderPipeline pipeline() {
        return HalfIconRectangleBatch.PIPELINE;
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
        if (this.batches.isEmpty()) {
            return new ScreenRect(0, 0, 1, 1);
        }
        return new ScreenRect((int)Math.floor(this.minX), (int)Math.floor(this.minY), Math.max(1, (int)Math.ceil(this.maxX - this.minX)), Math.max(1, (int)Math.ceil(this.maxY - this.minY))).transformEachVertex((Matrix3x2fc)this.pose);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u008f\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0019\u0010\u0018J\u0010\u0010\u001a\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001a\u0010\u0018J\u0010\u0010\u001b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001b\u0010\u0018J\u0010\u0010\u001c\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001c\u0010\u0018J\u0010\u0010\u001d\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001d\u0010\u0018J\u0010\u0010\u001e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001e\u0010\u0018J\u0010\u0010\u001f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001f\u0010\u0018J\u0010\u0010 \u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b \u0010\u0018J\u0010\u0010!\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b!\u0010\u0018J\u0010\u0010\"\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\"\u0010\u0018J\u0010\u0010#\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b#\u0010\u0018J\u0010\u0010$\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b$\u0010\u0018J\u0010\u0010%\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b%\u0010\u0018J\u0010\u0010&\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b&\u0010\u0018J\u0010\u0010'\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b'\u0010\u0018J\u0010\u0010(\u001a\u00020\u0013H\u00c6\u0003\u00a2\u0006\u0004\b(\u0010)J\u00ba\u0001\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u00022\b\b\u0002\u0010\u0014\u001a\u00020\u0013H\u00c6\u0001\u00a2\u0006\u0004\b*\u0010+J\u001b\u0010.\u001a\u00020-2\b\u0010,\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b.\u0010/J\u0011\u00100\u001a\u00020\u0013H\u00d6\u0081\u0004\u00a2\u0006\u0004\b0\u0010)J\u0011\u00102\u001a\u000201H\u00d6\u0081\u0004\u00a2\u0006\u0004\b2\u00103R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b4\u0012\b\b5\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u00106\u001a\u0004\b\u0003\u0010\u0018R%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b4\u0012\b\b5\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u00106\u001a\u0004\b\u0004\u0010\u0018R%\u0010\u0005\u001a\u00020\u00028\u0007z\f\b4\u0012\b\b5\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u00106\u001a\u0004\b\u0005\u0010\u0018R%\u0010\u0006\u001a\u00020\u00028\u0007z\f\b4\u0012\b\b5\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u00106\u001a\u0004\b\u0006\u0010\u0018R%\u0010\u0007\u001a\u00020\u00028\u0007z\f\b4\u0012\b\b5\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u00106\u001a\u0004\b\u0007\u0010\u0018R%\u0010\b\u001a\u00020\u00028\u0007z\f\b4\u0012\b\b5\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u00106\u001a\u0004\b\b\u0010\u0018R%\u0010\t\u001a\u00020\u00028\u0007z\f\b4\u0012\b\b5\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u00106\u001a\u0004\b\t\u0010\u0018R%\u0010\n\u001a\u00020\u00028\u0007z\f\b4\u0012\b\b5\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u00106\u001a\u0004\b\n\u0010\u0018R%\u0010\u000b\u001a\u00020\u00028\u0007z\f\b4\u0012\b\b5\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u00106\u001a\u0004\b\u000b\u0010\u0018R%\u0010\f\u001a\u00020\u00028\u0007z\f\b4\u0012\b\b5\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u00106\u001a\u0004\b\f\u0010\u0018R%\u0010\r\u001a\u00020\u00028\u0007z\f\b4\u0012\b\b5\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u00106\u001a\u0004\b\r\u0010\u0018R%\u0010\u000e\u001a\u00020\u00028\u0007z\f\b4\u0012\b\b5\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u00106\u001a\u0004\b\u000e\u0010\u0018R%\u0010\u000f\u001a\u00020\u00028\u0007z\f\b4\u0012\b\b5\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u00106\u001a\u0004\b\u000f\u0010\u0018R%\u0010\u0010\u001a\u00020\u00028\u0007z\f\b4\u0012\b\b5\u0012\u0004\b\b(\u0010\u00a2\u0006\f\n\u0004\b\u0010\u00106\u001a\u0004\b\u0010\u0010\u0018R%\u0010\u0011\u001a\u00020\u00028\u0007z\f\b4\u0012\b\b5\u0012\u0004\b\b(\u0011\u00a2\u0006\f\n\u0004\b\u0011\u00106\u001a\u0004\b\u0011\u0010\u0018R%\u0010\u0012\u001a\u00020\u00028\u0007z\f\b4\u0012\b\b5\u0012\u0004\b\b(\u0012\u00a2\u0006\f\n\u0004\b\u0012\u00106\u001a\u0004\b\u0012\u0010\u0018R%\u0010\u0014\u001a\u00020\u00138\u0007z\f\b4\u0012\b\b5\u0012\u0004\b\b(\u0014\u00a2\u0006\f\n\u0004\b\u0014\u00107\u001a\u0004\b\u0014\u0010)\u00a8\u00068"}, d2={"Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderState$IconQuad;", "", "", "x0", "y0", "u0", "v0", "x1", "y1", "u1", "v1", "x2", "y2", "u2", "v2", "x3", "y3", "u3", "v3", "", "color", "<init>", "(FFFFFFFFFFFFFFFFI)V", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "()I", "copy", "(FFFFFFFFFFFFFFFFI)Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderState$IconQuad;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "I", "rtx.kimiko:kimiko"})
    public static final class IconQuad {
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
        private final int color;

        public IconQuad(float x0, float y0, float u0, float v0, float x1, float y1, float u1, float v1, float x2, float y2, float u2, float v2, float x3, float y3, float u3, float v3, int color) {
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
            this.color = color;
        }

        @JvmName(name="x0")
        public final float x0() {
            return this.x0;
        }

        @JvmName(name="y0")
        public final float y0() {
            return this.y0;
        }

        @JvmName(name="u0")
        public final float u0() {
            return this.u0;
        }

        @JvmName(name="v0")
        public final float v0() {
            return this.v0;
        }

        @JvmName(name="x1")
        public final float x1() {
            return this.x1;
        }

        @JvmName(name="y1")
        public final float y1() {
            return this.y1;
        }

        @JvmName(name="u1")
        public final float u1() {
            return this.u1;
        }

        @JvmName(name="v1")
        public final float v1() {
            return this.v1;
        }

        @JvmName(name="x2")
        public final float x2() {
            return this.x2;
        }

        @JvmName(name="y2")
        public final float y2() {
            return this.y2;
        }

        @JvmName(name="u2")
        public final float u2() {
            return this.u2;
        }

        @JvmName(name="v2")
        public final float v2() {
            return this.v2;
        }

        @JvmName(name="x3")
        public final float x3() {
            return this.x3;
        }

        @JvmName(name="y3")
        public final float y3() {
            return this.y3;
        }

        @JvmName(name="u3")
        public final float u3() {
            return this.u3;
        }

        @JvmName(name="v3")
        public final float v3() {
            return this.v3;
        }

        @JvmName(name="color")
        public final int color() {
            return this.color;
        }

        public final float component1() {
            return this.x0;
        }

        public final float component2() {
            return this.y0;
        }

        public final float component3() {
            return this.u0;
        }

        public final float component4() {
            return this.v0;
        }

        public final float component5() {
            return this.x1;
        }

        public final float component6() {
            return this.y1;
        }

        public final float component7() {
            return this.u1;
        }

        public final float component8() {
            return this.v1;
        }

        public final float component9() {
            return this.x2;
        }

        public final float component10() {
            return this.y2;
        }

        public final float component11() {
            return this.u2;
        }

        public final float component12() {
            return this.v2;
        }

        public final float component13() {
            return this.x3;
        }

        public final float component14() {
            return this.y3;
        }

        public final float component15() {
            return this.u3;
        }

        public final float component16() {
            return this.v3;
        }

        public final int component17() {
            return this.color;
        }

        @NotNull
        public final IconQuad copy(float x0, float y0, float u0, float v0, float x1, float y1, float u1, float v1, float x2, float y2, float u2, float v2, float x3, float y3, float u3, float v3, int color) {
            return new IconQuad(x0, y0, u0, v0, x1, y1, u1, v1, x2, y2, u2, v2, x3, y3, u3, v3, color);
        }

        public static /* synthetic */ IconQuad copy$default(IconQuad iconQuad, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, int n, int n2, Object object) {
            if ((n2 & 1) != 0) {
                f = iconQuad.x0;
            }
            if ((n2 & 2) != 0) {
                f2 = iconQuad.y0;
            }
            if ((n2 & 4) != 0) {
                f3 = iconQuad.u0;
            }
            if ((n2 & 8) != 0) {
                f4 = iconQuad.v0;
            }
            if ((n2 & 0x10) != 0) {
                f5 = iconQuad.x1;
            }
            if ((n2 & 0x20) != 0) {
                f6 = iconQuad.y1;
            }
            if ((n2 & 0x40) != 0) {
                f7 = iconQuad.u1;
            }
            if ((n2 & 0x80) != 0) {
                f8 = iconQuad.v1;
            }
            if ((n2 & 0x100) != 0) {
                f9 = iconQuad.x2;
            }
            if ((n2 & 0x200) != 0) {
                f10 = iconQuad.y2;
            }
            if ((n2 & 0x400) != 0) {
                f11 = iconQuad.u2;
            }
            if ((n2 & 0x800) != 0) {
                f12 = iconQuad.v2;
            }
            if ((n2 & 0x1000) != 0) {
                f13 = iconQuad.x3;
            }
            if ((n2 & 0x2000) != 0) {
                f14 = iconQuad.y3;
            }
            if ((n2 & 0x4000) != 0) {
                f15 = iconQuad.u3;
            }
            if ((n2 & 0x8000) != 0) {
                f16 = iconQuad.v3;
            }
            if ((n2 & 0x10000) != 0) {
                n = iconQuad.color;
            }
            return iconQuad.copy(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, n);
        }

        @NotNull
        public String toString() {
            return "IconQuad(x0=" + this.x0 + ", y0=" + this.y0 + ", u0=" + this.u0 + ", v0=" + this.v0 + ", x1=" + this.x1 + ", y1=" + this.y1 + ", u1=" + this.u1 + ", v1=" + this.v1 + ", x2=" + this.x2 + ", y2=" + this.y2 + ", u2=" + this.u2 + ", v2=" + this.v2 + ", x3=" + this.x3 + ", y3=" + this.y3 + ", u3=" + this.u3 + ", v3=" + this.v3 + ", color=" + this.color + ")";
        }

        public int hashCode() {
            int result = Float.hashCode(this.x0);
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
            result = result * 31 + Integer.hashCode(this.color);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof IconQuad)) {
                return false;
            }
            IconQuad iconQuad = (IconQuad)other;
            if (Float.compare(this.x0, iconQuad.x0) != 0) {
                return false;
            }
            if (Float.compare(this.y0, iconQuad.y0) != 0) {
                return false;
            }
            if (Float.compare(this.u0, iconQuad.u0) != 0) {
                return false;
            }
            if (Float.compare(this.v0, iconQuad.v0) != 0) {
                return false;
            }
            if (Float.compare(this.x1, iconQuad.x1) != 0) {
                return false;
            }
            if (Float.compare(this.y1, iconQuad.y1) != 0) {
                return false;
            }
            if (Float.compare(this.u1, iconQuad.u1) != 0) {
                return false;
            }
            if (Float.compare(this.v1, iconQuad.v1) != 0) {
                return false;
            }
            if (Float.compare(this.x2, iconQuad.x2) != 0) {
                return false;
            }
            if (Float.compare(this.y2, iconQuad.y2) != 0) {
                return false;
            }
            if (Float.compare(this.u2, iconQuad.u2) != 0) {
                return false;
            }
            if (Float.compare(this.v2, iconQuad.v2) != 0) {
                return false;
            }
            if (Float.compare(this.x3, iconQuad.x3) != 0) {
                return false;
            }
            if (Float.compare(this.y3, iconQuad.y3) != 0) {
                return false;
            }
            if (Float.compare(this.u3, iconQuad.u3) != 0) {
                return false;
            }
            if (Float.compare(this.v3, iconQuad.v3) != 0) {
                return false;
            }
            return this.color == iconQuad.color;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\t\u0010\nJ\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ*\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0011\u0010\u0014\u001a\u00020\u0013H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u0017\u001a\u00020\u0016H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0019\u001a\u0004\b\u001a\u0010\nR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001b\u001a\u0004\b\u001c\u0010\f\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderState$PatternBatch;", "", "Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/BuiltHalfIconRectangle;", "rectangle", "", "Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderState$IconQuad;", "quads", "<init>", "(Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/BuiltHalfIconRectangle;Ljava/util/List;)V", "component1", "()Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/BuiltHalfIconRectangle;", "component2", "()Ljava/util/List;", "copy", "(Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/BuiltHalfIconRectangle;Ljava/util/List;)Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/HalfIconRectangleRenderState$PatternBatch;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lrtx/kimiko/utils/render/render2d/rectangle/recthalficon/BuiltHalfIconRectangle;", "getRectangle", "Ljava/util/List;", "getQuads", "rtx.kimiko:kimiko"})
    private static final class PatternBatch {
        @NotNull
        private final BuiltHalfIconRectangle rectangle;
        @NotNull
        private final List<IconQuad> quads;

        public PatternBatch(@NotNull BuiltHalfIconRectangle rectangle, @NotNull List<IconQuad> quads) {
            Intrinsics.checkNotNullParameter((Object)rectangle, (String)"rectangle");
            Intrinsics.checkNotNullParameter(quads, (String)"quads");
            this.rectangle = rectangle;
            this.quads = quads;
        }

        @NotNull
        public final BuiltHalfIconRectangle getRectangle() {
            return this.rectangle;
        }

        @NotNull
        public final List<IconQuad> getQuads() {
            return this.quads;
        }

        @NotNull
        public final BuiltHalfIconRectangle component1() {
            return this.rectangle;
        }

        @NotNull
        public final List<IconQuad> component2() {
            return this.quads;
        }

        @NotNull
        public final PatternBatch copy(@NotNull BuiltHalfIconRectangle rectangle, @NotNull List<IconQuad> quads) {
            Intrinsics.checkNotNullParameter((Object)rectangle, (String)"rectangle");
            Intrinsics.checkNotNullParameter(quads, (String)"quads");
            return new PatternBatch(rectangle, quads);
        }

        public static /* synthetic */ PatternBatch copy$default(PatternBatch patternBatch, BuiltHalfIconRectangle builtHalfIconRectangle, List list, int n, Object object) {
            if ((n & 1) != 0) {
                builtHalfIconRectangle = patternBatch.rectangle;
            }
            if ((n & 2) != 0) {
                list = patternBatch.quads;
            }
            return patternBatch.copy(builtHalfIconRectangle, list);
        }

        @NotNull
        public String toString() {
            return "PatternBatch(rectangle=" + this.rectangle + ", quads=" + this.quads + ")";
        }

        public int hashCode() {
            int result = this.rectangle.hashCode();
            result = result * 31 + ((Object)this.quads).hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PatternBatch)) {
                return false;
            }
            PatternBatch patternBatch = (PatternBatch)other;
            if (!Intrinsics.areEqual((Object)this.rectangle, (Object)patternBatch.rectangle)) {
                return false;
            }
            return Intrinsics.areEqual(this.quads, patternBatch.quads);
        }
    }
}

