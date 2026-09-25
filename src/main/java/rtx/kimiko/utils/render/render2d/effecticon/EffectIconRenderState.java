/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  kotlin.Metadata
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
package rtx.kimiko.utils.render.render2d.effecticon;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
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
import rtx.kimiko.utils.render.render2d.effecticon.EffectIconQuad;
import rtx.kimiko.utils.render.render2d.effecticon.EffectIconRenderer;
import rtx.kimiko.utils.render.render2d.effecticon.EffectIconTexture;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010J?\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u001aH\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u001dH\u0016\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0011\u0010!\u001a\u0004\u0018\u00010 H\u0016\u00a2\u0006\u0004\b!\u0010\"J\u0011\u0010#\u001a\u0004\u0018\u00010 H\u0016\u00a2\u0006\u0004\b#\u0010\"R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010$R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010%R$\u0010(\u001a\u0012\u0012\u0004\u0012\u00020\b0&j\b\u0012\u0004\u0012\u00020\b`'8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010,\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010+R\u0016\u0010-\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b-\u0010+R\u0016\u0010.\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b.\u0010+\u00a8\u0006/"}, d2={"Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconRenderState;", "Lnet/minecraft/SimpleGuiElementRenderState;", "Lorg/joml/Matrix3x2f;", "pose", "Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconTexture;", "texture", "<init>", "(Lorg/joml/Matrix3x2f;Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconTexture;)V", "Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconQuad;", "icon", "", "add", "(Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconQuad;)V", "Lnet/minecraft/VertexConsumer;", "consumer", "buildVertices", "(Lnet/minecraft/VertexConsumer;)V", "", "x", "y", "u", "v", "", "color", "vertex", "(Lnet/minecraft/VertexConsumer;FFFFI)V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "()Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lnet/minecraft/TextureSetup;", "textureSetup", "()Lnet/minecraft/TextureSetup;", "Lnet/minecraft/ScreenRect;", "scissorArea", "()Lnet/minecraft/ScreenRect;", "bounds", "Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconTexture;", "Lorg/joml/Matrix3x2f;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "icons", "Ljava/util/ArrayList;", "minX", "F", "minY", "maxX", "maxY", "rtx.kimiko:kimiko"})
public final class EffectIconRenderState
implements SimpleGuiElementRenderState {
    @NotNull
    private final EffectIconTexture texture;
    @NotNull
    private final Matrix3x2f pose;
    @NotNull
    private final ArrayList<EffectIconQuad> icons;
    private float minX;
    private float minY;
    private float maxX;
    private float maxY;

    public EffectIconRenderState(@NotNull Matrix3x2f pose, @NotNull EffectIconTexture texture) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
        this.texture = texture;
        this.pose = PoseCache.snapshot(pose);
        this.icons = new ArrayList(16);
        this.minX = Float.MAX_VALUE;
        this.minY = Float.MAX_VALUE;
        this.maxX = -3.4028235E38f;
        this.maxY = -3.4028235E38f;
    }

    public final void add(@NotNull EffectIconQuad icon) {
        Intrinsics.checkNotNullParameter((Object)icon, (String)"icon");
        this.icons.add(icon);
        this.minX = Math.min(this.minX, icon.x);
        this.minY = Math.min(this.minY, icon.y);
        this.maxX = Math.max(this.maxX, icon.x + icon.size);
        this.maxY = Math.max(this.maxY, icon.y + icon.size);
    }

    public void setupVertices(@NotNull VertexConsumer consumer) {
        Intrinsics.checkNotNullParameter((Object)consumer, (String)"consumer");
        Iterator<EffectIconQuad> iterator = this.icons.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<EffectIconQuad> iterator2 = iterator;
        while (iterator2.hasNext()) {
            EffectIconQuad icon = (EffectIconQuad) (iterator2.next());
            float x0 = icon.x;
            float y0 = icon.y;
            float x1 = icon.x + icon.size;
            float y1 = icon.y + icon.size;
            int color = icon.color;
            this.vertex(consumer, x0, y0, icon.u0, icon.v0, color);
            this.vertex(consumer, x0, y1, icon.u0, icon.v1, color);
            this.vertex(consumer, x1, y1, icon.u1, icon.v1, color);
            this.vertex(consumer, x1, y0, icon.u1, icon.v0, color);
        }
    }

    private final void vertex(VertexConsumer consumer, float x, float y, float u, float v, int color) {
        consumer.vertex((Matrix3x2fc)this.pose, x, y).texture(u, v).color(color);
    }

    @NotNull
    public RenderPipeline pipeline() {
        return EffectIconRenderer.EFFECT_ICON_PIPELINE;
    }

    @NotNull
    public TextureSetup textureSetup() {
        return this.texture.setup;
    }

    @Nullable
    public ScreenRect scissorArea() {
        return null;
    }

    @Nullable
    public ScreenRect bounds() {
        if (this.icons.isEmpty()) {
            return new ScreenRect(0, 0, 1, 1);
        }
        int x = (int)Math.floor(this.minX);
        int y = (int)Math.floor(this.minY);
        int width = Math.max(1, (int)Math.ceil(this.maxX - this.minX));
        int height = Math.max(1, (int)Math.ceil(this.maxY - this.minY));
        return new ScreenRect(x, y, width, height).transformEachVertex((Matrix3x2fc)this.pose);
    }
}

