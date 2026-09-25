/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Quaternionf
 *  org.joml.Vector3f
 */
package rtx.kimiko.utils.render.modules.targetesp;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.modules.targetesp.TargetEspColorProvider;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003Jc\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0007b\u0002\b\u0014\u00a2\u0006\u0004\b\u0015\u0010\u0016J_\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u001f\u0010 JG\u0010'\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020%H\u0002\u00a2\u0006\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020%8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020%8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010*R\u0014\u0010,\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010-\u00a8\u0006."}, d2={"Lrtx/kimiko/utils/render/modules/targetesp/TargetCircleRenderer;", "", "<init>", "()V", "Lnet/minecraft/VertexConsumerProvider$Immediate;", "provider", "Lnet/minecraft/MatrixStack;", "stack", "", "radius", "centerY", "tiltRad", "spinRad", "thickness", "alpha", "", "throughWalls", "Lrtx/kimiko/utils/render/modules/targetesp/TargetEspColorProvider;", "colors", "", "Lkotlin/jvm/JvmStatic;", "render", "(Lnet/minecraft/VertexConsumerProvider$Immediate;Lnet/minecraft/MatrixStack;FFFFFFZLrtx/kimiko/utils/render/modules/targetesp/TargetEspColorProvider;)V", "Lnet/minecraft/VertexConsumer;", "consumer", "Lnet/minecraft/MatrixStack$Entry;", "pose", "Lorg/joml/Vector3f;", "axisA", "axisB", "normal", "emitTube", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lorg/joml/Vector3f;Lorg/joml/Vector3f;Lorg/joml/Vector3f;FFFFLrtx/kimiko/utils/render/modules/targetesp/TargetEspColorProvider;)V", "a", "b", "c", "d", "", "color", "quad", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lorg/joml/Vector3f;Lorg/joml/Vector3f;Lorg/joml/Vector3f;Lorg/joml/Vector3f;I)V", "SEGMENTS", "I", "CROSS_SIDES", "TAPER_POWER", "F", "rtx.kimiko:kimiko"})
public final class TargetCircleRenderer {
    @NotNull
    public static final TargetCircleRenderer INSTANCE = new TargetCircleRenderer();
    private static final int SEGMENTS = 128;
    private static final int CROSS_SIDES = 6;
    private static final float TAPER_POWER = 0.8f;

    private TargetCircleRenderer() {
    }

    @JvmStatic
    public static final void render(@NotNull VertexConsumerProvider.Immediate provider, @NotNull MatrixStack stack, float radius, float centerY, float tiltRad, float spinRad, float thickness, float alpha, boolean throughWalls, @NotNull TargetEspColorProvider colors) {
        Intrinsics.checkNotNullParameter((Object)provider, (String)"provider");
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)colors, (String)"colors");
        if (alpha <= 0.02f || radius <= 0.0f || thickness <= 0.0f) {
            return;
        }
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        Quaternionf rot = new Quaternionf().rotateY(spinRad).rotateX(tiltRad);
        Vector3f axisA = rot.transform(new Vector3f(1.0f, 0.0f, 0.0f));
        Vector3f axisB = rot.transform(new Vector3f(0.0f, 0.0f, 1.0f));
        Vector3f normal = rot.transform(new Vector3f(0.0f, 1.0f, 0.0f));
        RenderLayer type = throughWalls ? ClientPipelines.TARGET_CIRCLE_NODEPTH : ClientPipelines.WORLD_PARTICLES_COLOR;
        VertexConsumer vertexConsumer2 = provider.getBuffer(type);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer consumer = vertexConsumer2;
        Intrinsics.checkNotNull((Object)axisA);
        Intrinsics.checkNotNull((Object)axisB);
        Intrinsics.checkNotNull((Object)normal);
        INSTANCE.emitTube(consumer, pose, axisA, axisB, normal, radius, centerY, thickness, alpha, colors);
        provider.draw(type);
    }

    private final void emitTube(VertexConsumer consumer, MatrixStack.Entry pose, Vector3f axisA, Vector3f axisB, Vector3f normal, float radius, float centerY, float thickness, float alpha, TargetEspColorProvider colors) {
        int n = 0;
        Vector3f[] vector3fArray = new Vector3f[6];
        while (n < 6) {
            int n2 = n++;
            vector3fArray[n2] = new Vector3f();
        }
        Vector3f[] ring = vector3fArray;
        int n3 = 0;
        Vector3f[] vector3fArray2 = new Vector3f[6];
        while (n3 < 6) {
            int n4 = n3++;
            vector3fArray2[n4] = new Vector3f();
        }
        Vector3f[] scratch = vector3fArray2;
        Vector3f[] prevRing = null;
        for (int i = 0; i < 129; ++i) {
            double theta = Math.PI * 2 * (double)i / (double)128;
            float cosT = (float)Math.cos(theta);
            float sinT = (float)Math.sin(theta);
            float dirX = axisA.x * cosT + axisB.x * sinT;
            float dirY = axisA.y * cosT + axisB.y * sinT;
            float dirZ = axisA.z * cosT + axisB.z * sinT;
            float cX = dirX * radius;
            float cY = centerY + dirY * radius;
            float cZ = dirZ * radius;
            float crossR = thickness * (float)Math.pow(Math.abs(sinT), 0.8f);
            Vector3f[] current = prevRing == scratch ? ring : scratch;
            for (int j = 0; j < 6; ++j) {
                double phi = Math.PI * 2 * (double)j / (double)6;
                float cosP = (float)Math.cos(phi);
                float sinP = (float)Math.sin(phi);
                float ox = crossR * (cosP * dirX + sinP * normal.x);
                float oy = crossR * (cosP * dirY + sinP * normal.y);
                float oz = crossR * (cosP * dirZ + sinP * normal.z);
                current[j].set(cX + ox, cY + oy, cZ + oz);
            }
            int color = colors.color((int)(Math.toDegrees(theta) * 2.0), alpha);
            Vector3f[] previous = prevRing;
            if (previous != null && ColorEngine.alpha(color) > 1) {
                for (int j = 0; j < 6; ++j) {
                    int n5 = (j + 1) % 6;
                    this.quad(consumer, pose, previous[j], previous[n5], current[n5], current[j], color);
                }
            }
            prevRing = current;
        }
    }

    private final void quad(VertexConsumer consumer, MatrixStack.Entry pose, Vector3f a, Vector3f b, Vector3f c, Vector3f d, int color) {
        consumer.vertex(pose, a.x, a.y, a.z).color(color);
        consumer.vertex(pose, b.x, b.y, b.z).color(color);
        consumer.vertex(pose, c.x, c.y, c.z).color(color);
        consumer.vertex(pose, d.x, d.y, d.z).color(color);
    }
}

