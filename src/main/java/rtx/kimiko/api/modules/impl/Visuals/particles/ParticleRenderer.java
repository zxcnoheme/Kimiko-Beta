/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.RenderLayers
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.util.math.RotationAxis
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 */
package rtx.kimiko.api.modules.impl.Visuals.particles;

import java.util.HashSet;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.Identifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.others.WorldVertex;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u0015\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ]\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0018\u00a2\u0006\u0004\b\u001a\u0010\u001bJ?\u0010 \u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0016H\u0000\u00a2\u0006\u0004\b\u001e\u0010\u001fJW\u0010#\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020!2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b#\u0010$J/\u0010*\u001a\u00020\u00042\u0006\u0010&\u001a\u00020%2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b*\u0010+J?\u0010/\u001a\u00020\u00042\u0006\u0010&\u001a\u00020%2\u0006\u0010(\u001a\u00020'2\u0006\u0010,\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b/\u00100R$\u00103\u001a\u0012\u0012\u0004\u0012\u00020!01j\b\u0012\u0004\u0012\u00020!`28\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b3\u00104\u00a8\u00065"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/ParticleRenderer;", "", "<init>", "()V", "", "clear", "Lnet/minecraft/VertexConsumerProvider$Immediate;", "provider", "flush", "(Lnet/minecraft/VertexConsumerProvider$Immediate;)V", "Lnet/minecraft/MatrixStack;", "stack", "Lnet/minecraft/Identifier;", "texture", "Lnet/minecraft/Vec3d;", "pos", "cameraPos", "Lorg/joml/Quaternionf;", "cameraRotation", "", "size", "rotation", "", "color", "", "bloom", "drawTexture", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/VertexConsumerProvider$Immediate;Lnet/minecraft/Identifier;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;Lorg/joml/Quaternionf;FFIZ)V", "renderPos", "scale", "drawBox$rtx_kimiko_kimiko", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/VertexConsumerProvider$Immediate;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;FI)V", "drawBox", "Lnet/minecraft/RenderLayer;", "renderType", "renderBillboard", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/VertexConsumerProvider$Immediate;Lnet/minecraft/RenderLayer;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;Lorg/joml/Quaternionf;FFI)V", "Lnet/minecraft/VertexConsumer;", "consumer", "Lnet/minecraft/MatrixStack$Entry;", "pose", "half", "addCube", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;FI)V", "x", "y", "z", "vertex", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;FFFI)V", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "usedRenderTypes", "Ljava/util/HashSet;", "rtx.kimiko:kimiko"})
public final class ParticleRenderer {
    @NotNull
    private final HashSet<RenderLayer> usedRenderTypes = new HashSet();

    public final void clear() {
        this.usedRenderTypes.clear();
    }

    public final void flush(@NotNull VertexConsumerProvider.Immediate provider) {
        Intrinsics.checkNotNullParameter((Object)provider, (String)"provider");
        Iterator<RenderLayer> iterator = this.usedRenderTypes.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<RenderLayer> iterator2 = iterator;
        while (iterator2.hasNext()) {
            RenderLayer type = (RenderLayer) (iterator2.next());
            provider.draw(type);
        }
        this.usedRenderTypes.clear();
    }

    public final void drawTexture(@NotNull MatrixStack stack, @NotNull VertexConsumerProvider.Immediate provider, @NotNull Identifier texture, @NotNull Vec3d pos, @NotNull Vec3d cameraPos, @NotNull Quaternionf cameraRotation, float size, float rotation, int color, boolean bloom) {
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)provider, (String)"provider");
        Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
        Intrinsics.checkNotNullParameter((Object)pos, (String)"pos");
        Intrinsics.checkNotNullParameter((Object)cameraPos, (String)"cameraPos");
        Intrinsics.checkNotNullParameter((Object)cameraRotation, (String)"cameraRotation");
        RenderLayer renderLayer2 = bloom ? ClientPipelines.WORLD_PARTICLES_GLOW.apply(texture) : RenderLayers.entityTranslucent((Identifier)texture);
        Intrinsics.checkNotNull((Object)renderLayer2);
        RenderLayer renderType = renderLayer2;
        this.renderBillboard(stack, provider, renderType, pos, cameraPos, cameraRotation, size, rotation, color);
    }

    public final void drawBox$rtx_kimiko_kimiko(@NotNull MatrixStack stack, @NotNull VertexConsumerProvider.Immediate provider, @NotNull Vec3d renderPos, @NotNull Vec3d cameraPos, float scale, int color) {
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)provider, (String)"provider");
        Intrinsics.checkNotNullParameter((Object)renderPos, (String)"renderPos");
        Intrinsics.checkNotNullParameter((Object)cameraPos, (String)"cameraPos");
        if (scale <= 0.001f || ColorEngine.alpha(color) <= 0) {
            return;
        }
        float boxScale = scale / 4.5f;
        RenderLayer renderType = ClientPipelines.WORLD_PARTICLES_COLOR;
        this.usedRenderTypes.add(renderType);
        VertexConsumer vertexConsumer2 = provider.getBuffer(renderType);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer consumer = vertexConsumer2;
        stack.push();
        stack.translate(renderPos.x - cameraPos.x, renderPos.y - cameraPos.y, renderPos.z - cameraPos.z);
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        this.addCube(consumer, pose, boxScale, color);
        stack.pop();
    }

    private final void renderBillboard(MatrixStack stack, VertexConsumerProvider.Immediate provider, RenderLayer renderType, Vec3d pos, Vec3d cameraPos, Quaternionf cameraRotation, float size, float rotation, int color) {
        if (size <= 0.001f || ColorEngine.alpha(color) <= 0) {
            return;
        }
        this.usedRenderTypes.add(renderType);
        VertexConsumer vertexConsumer2 = provider.getBuffer(renderType);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer consumer = vertexConsumer2;
        stack.push();
        stack.translate(pos.x - cameraPos.x, pos.y - cameraPos.y, pos.z - cameraPos.z);
        stack.multiply((Quaternionfc)cameraRotation);
        stack.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(rotation));
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        float half = size / 2.0f;
        WorldVertex.textured(consumer, pose, -half, -half, 0.0f, 0.0f, 0.0f, color);
        WorldVertex.textured(consumer, pose, half, -half, 0.0f, 1.0f, 0.0f, color);
        WorldVertex.textured(consumer, pose, half, half, 0.0f, 1.0f, 1.0f, color);
        WorldVertex.textured(consumer, pose, -half, half, 0.0f, 0.0f, 1.0f, color);
        stack.pop();
    }

    private final void addCube(VertexConsumer consumer, MatrixStack.Entry pose, float half, int color) {
        this.vertex(consumer, pose, -half, -half, -half, color);
        this.vertex(consumer, pose, half, -half, -half, color);
        this.vertex(consumer, pose, half, half, -half, color);
        this.vertex(consumer, pose, -half, half, -half, color);
        this.vertex(consumer, pose, -half, -half, half, color);
        this.vertex(consumer, pose, -half, half, half, color);
        this.vertex(consumer, pose, half, half, half, color);
        this.vertex(consumer, pose, half, -half, half, color);
        this.vertex(consumer, pose, -half, -half, -half, color);
        this.vertex(consumer, pose, -half, -half, half, color);
        this.vertex(consumer, pose, half, -half, half, color);
        this.vertex(consumer, pose, half, -half, -half, color);
        this.vertex(consumer, pose, -half, half, -half, color);
        this.vertex(consumer, pose, half, half, -half, color);
        this.vertex(consumer, pose, half, half, half, color);
        this.vertex(consumer, pose, -half, half, half, color);
        this.vertex(consumer, pose, -half, -half, -half, color);
        this.vertex(consumer, pose, -half, half, -half, color);
        this.vertex(consumer, pose, -half, half, half, color);
        this.vertex(consumer, pose, -half, -half, half, color);
        this.vertex(consumer, pose, half, -half, -half, color);
        this.vertex(consumer, pose, half, -half, half, color);
        this.vertex(consumer, pose, half, half, half, color);
        this.vertex(consumer, pose, half, half, -half, color);
    }

    private final void vertex(VertexConsumer consumer, MatrixStack.Entry pose, float x, float y, float z, int color) {
        consumer.vertex(pose, x, y, z).color(color);
    }
}

