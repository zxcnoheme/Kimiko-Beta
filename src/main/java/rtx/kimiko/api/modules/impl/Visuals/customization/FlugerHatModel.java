/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.command.OrderedRenderCommandQueue
 *  net.minecraft.client.render.RenderLayers
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.OverlayTexture
 *  net.minecraft.util.math.RotationAxis
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Quaternionfc
 */
package rtx.kimiko.api.modules.impl.Visuals.customization;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.util.Identifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionfc;
import rtx.kimiko.Kimiko;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J+\u0010\f\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u00a7\u0002\u00105\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u00132\u0006\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u00132\u0006\u0010'\u001a\u00020\u00132\u0006\u0010(\u001a\u00020\u00132\u0006\u0010)\u001a\u00020\u00132\u0006\u0010*\u001a\u00020\u00132\u0006\u0010+\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u00132\u0006\u0010/\u001a\u00020\u00132\u0006\u00100\u001a\u00020\u00132\u0006\u00101\u001a\u00020\u00132\u0006\u00103\u001a\u0002022\u0006\u00104\u001a\u000202H\u0002\u00a2\u0006\u0004\b5\u00106J\u00bf\u0001\u0010J\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\b2\u0006\u00107\u001a\u00020\u00132\u0006\u00108\u001a\u00020\u00132\u0006\u00109\u001a\u00020\u00132\u0006\u0010:\u001a\u00020\u00132\u0006\u0010;\u001a\u00020\u00132\u0006\u0010<\u001a\u00020\u00132\u0006\u0010=\u001a\u00020\u00132\u0006\u0010>\u001a\u00020\u00132\u0006\u0010?\u001a\u00020\u00132\u0006\u0010@\u001a\u00020\u00132\u0006\u0010A\u001a\u00020\u00132\u0006\u0010B\u001a\u00020\u00132\u0006\u0010C\u001a\u00020\u00132\u0006\u0010D\u001a\u00020\u00132\u0006\u0010E\u001a\u00020\u00132\u0006\u0010F\u001a\u00020\u00132\u0006\u0010G\u001a\u00020\u00132\u0006\u0010H\u001a\u00020\u00132\u0006\u0010I\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\bJ\u0010KJg\u0010Q\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010L\u001a\u00020\u00132\u0006\u0010M\u001a\u00020\u00132\u0006\u0010N\u001a\u00020\u00132\u0006\u0010O\u001a\u00020\u00132\u0006\u0010P\u001a\u00020\u00132\u0006\u0010G\u001a\u00020\u00132\u0006\u0010H\u001a\u00020\u00132\u0006\u0010I\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\bQ\u0010RR\u0014\u0010T\u001a\u00020S8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bT\u0010UR\u0014\u0010V\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bV\u0010WR\u0014\u0010X\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bX\u0010WR\u0014\u0010Y\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bY\u0010W\u00a8\u0006Z"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/FlugerHatModel;", "", "<init>", "()V", "Lnet/minecraft/MatrixStack;", "poseStack", "Lnet/minecraft/OrderedRenderCommandQueue;", "collector", "", "packedLight", "", "Lkotlin/jvm/JvmStatic;", "render", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/OrderedRenderCommandQueue;I)V", "Lnet/minecraft/VertexConsumer;", "consumer", "Lorg/joml/Matrix4f;", "matrix", "light", "", "minX", "minY", "minZ", "maxX", "maxY", "maxZ", "nU1", "nV1", "nU2", "nV2", "sU1", "sV1", "sU2", "sV2", "eU1", "eV1", "eU2", "eV2", "wU1", "wV1", "wU2", "wV2", "uU1", "uV1", "uU2", "uV2", "dU1", "dV1", "dU2", "dV2", "", "drawUp", "drawDown", "renderCube", "(Lnet/minecraft/VertexConsumer;Lorg/joml/Matrix4f;IFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFZZ)V", "x0", "y0", "z0", "x1", "y1", "z1", "x2", "y2", "z2", "x3", "y3", "z3", "u1", "v1", "u2", "v2", "nx", "ny", "nz", "quad", "(Lnet/minecraft/VertexConsumer;Lorg/joml/Matrix4f;IFFFFFFFFFFFFFFFFFFF)V", "x", "y", "z", "u", "v", "vertex", "(Lnet/minecraft/VertexConsumer;Lorg/joml/Matrix4f;IFFFFFFFF)V", "Lnet/minecraft/Identifier;", "TEXTURE", "Lnet/minecraft/Identifier;", "OFFSET_Y", "F", "SIZE", "UV_SCALE", "rtx.kimiko:kimiko"})
public final class FlugerHatModel {
    @NotNull
    public static final FlugerHatModel INSTANCE = new FlugerHatModel();
    @NotNull
    private static final Identifier TEXTURE;
    private static final float OFFSET_Y = -0.4f;
    private static final float SIZE = 1.0f;
    private static final float UV_SCALE = 32.0f;

    private FlugerHatModel() {
    }

    @JvmStatic
    public static final void render(@NotNull MatrixStack poseStack, @NotNull OrderedRenderCommandQueue collector, int packedLight) {
        Intrinsics.checkNotNullParameter((Object)poseStack, (String)"poseStack");
        Intrinsics.checkNotNullParameter((Object)collector, (String)"collector");
        poseStack.push();
        poseStack.translate(0.0f, -0.4f, 0.0f);
        float scale = 0.0625f;
        poseStack.scale(scale, scale, scale);
        poseStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(180.0f));
        collector.submitCustom(poseStack, RenderLayers.entityCutoutNoCull((Identifier)TEXTURE), (arg_0, arg_1) -> FlugerHatModel.render$lambda$0(packedLight, arg_0, arg_1));
        poseStack.pop();
    }

    private final void renderCube(VertexConsumer consumer, Matrix4f matrix, int light, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, float nU1, float nV1, float nU2, float nV2, float sU1, float sV1, float sU2, float sV2, float eU1, float eV1, float eU2, float eV2, float wU1, float wV1, float wU2, float wV2, float uU1, float uV1, float uU2, float uV2, float dU1, float dV1, float dU2, float dV2, boolean drawUp, boolean drawDown) {
        this.quad(consumer, matrix, light, maxX, minY, minZ, minX, minY, minZ, minX, maxY, minZ, maxX, maxY, minZ, nU1, nV1, nU2, nV2, 0.0f, 0.0f, -1.0f);
        this.quad(consumer, matrix, light, minX, minY, maxZ, maxX, minY, maxZ, maxX, maxY, maxZ, minX, maxY, maxZ, sU1, sV1, sU2, sV2, 0.0f, 0.0f, 1.0f);
        this.quad(consumer, matrix, light, minX, minY, minZ, minX, minY, maxZ, minX, maxY, maxZ, minX, maxY, minZ, wU1, wV1, wU2, wV2, -1.0f, 0.0f, 0.0f);
        this.quad(consumer, matrix, light, maxX, minY, maxZ, maxX, minY, minZ, maxX, maxY, minZ, maxX, maxY, maxZ, eU1, eV1, eU2, eV2, 1.0f, 0.0f, 0.0f);
        if (drawUp) {
            this.quad(consumer, matrix, light, minX, minY, minZ, maxX, minY, minZ, maxX, minY, maxZ, minX, minY, maxZ, uU1, uV1, uU2, uV2, 0.0f, -1.0f, 0.0f);
        }
        if (drawDown) {
            this.quad(consumer, matrix, light, minX, maxY, maxZ, maxX, maxY, maxZ, maxX, maxY, minZ, minX, maxY, minZ, dU1, dV1, dU2, dV2, 0.0f, 1.0f, 0.0f);
        }
    }

    private final void quad(VertexConsumer consumer, Matrix4f matrix, int light, float x0, float y0, float z0, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float u1, float v1, float u2, float v2, float nx, float ny, float nz) {
        float su1 = u1 / 32.0f;
        float sv1 = v1 / 32.0f;
        float su2 = u2 / 32.0f;
        float sv2 = v2 / 32.0f;
        this.vertex(consumer, matrix, light, x0, y0, z0, su1, sv1, nx, ny, nz);
        this.vertex(consumer, matrix, light, x1, y1, z1, su2, sv1, nx, ny, nz);
        this.vertex(consumer, matrix, light, x2, y2, z2, su2, sv2, nx, ny, nz);
        this.vertex(consumer, matrix, light, x3, y3, z3, su1, sv2, nx, ny, nz);
    }

    private final void vertex(VertexConsumer consumer, Matrix4f matrix, int light, float x, float y, float z, float u, float v, float nx, float ny, float nz) {
        consumer.vertex((Matrix4fc)matrix, x, y, z).color(255, 255, 255, 255).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(nx, ny, nz);
    }

    private static final void render$lambda$0(int $packedLight, MatrixStack.Entry pose, VertexConsumer consumer) {
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        Intrinsics.checkNotNullParameter((Object)consumer, (String)"consumer");
        MatrixStack tiltStack = new MatrixStack();
        Matrix4f matrix4f = pose.getPositionMatrix();
        Intrinsics.checkNotNullExpressionValue((Object)matrix4f, (String)"pose(...)");
        INSTANCE.renderCube(consumer, matrix4f, $packedLight, -5.0f, -4.0f, -5.0f, 5.0f, 0.0f, 5.0f, 10.0f, 16.0f, 20.0f, 20.0f, 20.0f, 16.0f, 10.0f, 20.0f, 28.0f, 0.0f, 18.0f, 4.0f, 28.0f, 8.0f, 18.0f, 12.0f, 10.0f, 0.0f, 0.0f, 10.0f, 10.0f, 20.0f, 0.0f, 10.0f, true, true);
        Matrix4f matrix4f2 = pose.getPositionMatrix();
        Intrinsics.checkNotNullExpressionValue((Object)matrix4f2, (String)"pose(...)");
        INSTANCE.renderCube(consumer, matrix4f2, $packedLight, -5.2f, -2.7f, -5.2f, 5.2f, 0.2f, 5.2f, 10.0f, 17.5f, 20.0f, 20.0f, 20.0f, 17.5f, 10.0f, 20.0f, 28.0f, 1.5f, 18.0f, 4.0f, 28.0f, 9.5f, 18.0f, 12.0f, 0.0f, 0.0f, 0.0f, 0.0f, 10.0f, 20.0f, 0.0f, 10.0f, false, true);
        tiltStack.translate(0.0f, -4.5f, 0.5f);
        tiltStack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(12.5f));
        tiltStack.translate(0.0f, 4.5f, -0.5f);
        Matrix4f tilted = new Matrix4f((Matrix4fc)pose.getPositionMatrix()).mul((Matrix4fc)tiltStack.peek().getPositionMatrix());
        Intrinsics.checkNotNull((Object)tilted);
        INSTANCE.renderCube(consumer, tilted, $packedLight, -4.0f, -7.0f, -3.5f, 4.0f, -3.0f, 4.5f, 18.0f, 12.0f, 26.0f, 16.0f, 26.0f, 12.0f, 18.0f, 16.0f, 20.0f, 20.0f, 28.0f, 24.0f, 12.0f, 20.0f, 20.0f, 24.0f, 18.0f, 0.0f, 10.0f, 8.0f, 0.0f, 0.0f, 0.0f, 0.0f, true, false);
        INSTANCE.renderCube(consumer, tilted, $packedLight, -3.0f, -8.0f, -2.5f, 3.0f, -7.0f, 3.5f, 12.5f, 18.0f, 18.5f, 19.0f, 18.5f, 18.0f, 12.5f, 19.0f, 27.0f, 21.5f, 21.0f, 22.5f, 18.5f, 21.0f, 12.5f, 22.0f, 6.0f, 20.0f, 0.0f, 26.0f, 12.0f, 26.0f, 6.0f, 20.0f, true, true);
    }

    static {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"textures/entity/hat/fluger_hat.png");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        TEXTURE = identifier2;
    }
}

