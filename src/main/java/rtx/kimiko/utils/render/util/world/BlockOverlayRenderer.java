/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.math.Box
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.util.world;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001c\n\u0002\u0010\b\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001CB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003JK\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\fH\u0007b\u0002\b\u0011\u00a2\u0006\u0004\b\u0012\u0010\u0013J7\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJG\u0010\"\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020 H\u0002\u00a2\u0006\u0004\b\"\u0010#J?\u0010$\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b$\u0010%Jo\u0010,\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010&\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020 H\u0002\u00a2\u0006\u0004\b,\u0010-Jg\u0010.\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010&\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b.\u0010/J\u008f\u0001\u00106\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010&\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020\u001e2\u0006\u00100\u001a\u00020\u001e2\u0006\u00101\u001a\u00020\u001e2\u0006\u00102\u001a\u00020\u001e2\u0006\u00103\u001a\u00020\u001e2\u0006\u00104\u001a\u00020\u001e2\u0006\u00105\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b6\u00107J'\u0010;\u001a\u00020\u001e2\u0006\u00108\u001a\u00020\u001e2\u0006\u00109\u001a\u00020\u001e2\u0006\u0010:\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b;\u0010<R\u0014\u0010>\u001a\u00020=8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020=8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u0010?R\u0014\u0010A\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bA\u0010B\u00a8\u0006D"}, d2={"Lrtx/kimiko/utils/render/util/world/BlockOverlayRenderer;", "", "<init>", "()V", "Lnet/minecraft/VertexConsumerProvider$Immediate;", "provider", "Lnet/minecraft/MatrixStack;", "stack", "Lnet/minecraft/Vec3d;", "cameraPos", "Lnet/minecraft/Box;", "box", "", "outlineColors", "crossColors", "fillColors", "", "Lkotlin/jvm/JvmStatic;", "render", "(Lnet/minecraft/VertexConsumerProvider$Immediate;Lnet/minecraft/MatrixStack;Lnet/minecraft/Vec3d;Lnet/minecraft/Box;[I[I[I)V", "Lnet/minecraft/VertexConsumer;", "c", "Lnet/minecraft/MatrixStack$Entry;", "pose", "cam", "b", "Lrtx/kimiko/utils/render/util/world/BlockOverlayRenderer$Gradient;", "gradient", "emitFaces", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;Lnet/minecraft/Box;Lrtx/kimiko/utils/render/util/world/BlockOverlayRenderer$Gradient;)V", "", "radius", "", "dashed", "emitEdges", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;Lnet/minecraft/Box;Lrtx/kimiko/utils/render/util/world/BlockOverlayRenderer$Gradient;DZ)V", "emitFaceCrosses", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;Lnet/minecraft/Box;Lrtx/kimiko/utils/render/util/world/BlockOverlayRenderer$Gradient;D)V", "x0", "y0", "z0", "x1", "y1", "z1", "edge", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;DDDDDDLrtx/kimiko/utils/render/util/world/BlockOverlayRenderer$Gradient;DZ)V", "tube", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;DDDDDDLrtx/kimiko/utils/render/util/world/BlockOverlayRenderer$Gradient;D)V", "x2", "y2", "z2", "x3", "y3", "z3", "quad", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;DDDDDDDDDDDDLrtx/kimiko/utils/render/util/world/BlockOverlayRenderer$Gradient;)V", "from", "to", "progress", "lerp", "(DDD)D", "", "TUBE_SIDES", "I", "DASH_COUNT", "TAU", "D", "Gradient", "rtx.kimiko:kimiko"})
public final class BlockOverlayRenderer {
    @NotNull
    public static final BlockOverlayRenderer INSTANCE = new BlockOverlayRenderer();
    private static final int TUBE_SIDES = 6;
    private static final int DASH_COUNT = 10;
    private static final double TAU = Math.PI * 2;

    private BlockOverlayRenderer() {
    }

    @JvmStatic
    public static final void render(@NotNull VertexConsumerProvider.Immediate provider, @NotNull MatrixStack stack, @NotNull Vec3d cameraPos, @NotNull Box box, @NotNull int[] outlineColors, @NotNull int[] crossColors, @NotNull int[] fillColors) {
        Intrinsics.checkNotNullParameter((Object)provider, (String)"provider");
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)cameraPos, (String)"cameraPos");
        Intrinsics.checkNotNullParameter((Object)box, (String)"box");
        Intrinsics.checkNotNullParameter((Object)outlineColors, (String)"outlineColors");
        Intrinsics.checkNotNullParameter((Object)crossColors, (String)"crossColors");
        Intrinsics.checkNotNullParameter((Object)fillColors, (String)"fillColors");
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        VertexConsumer vertexConsumer2 = provider.getBuffer(ClientPipelines.BLOCK_OVERLAY);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer consumer = vertexConsumer2;
        Gradient outline = new Gradient(box, outlineColors);
        Gradient cross = new Gradient(box, crossColors);
        Gradient fill = new Gradient(box, fillColors);
        if (fill.visible()) {
            INSTANCE.emitFaces(consumer, pose, cameraPos, box, fill);
        }
        if (cross.visible()) {
            INSTANCE.emitFaceCrosses(consumer, pose, cameraPos, box, cross, 0.0045);
        }
        if (outline.visible()) {
            INSTANCE.emitEdges(consumer, pose, cameraPos, box, outline, 0.005, false);
        }
        if (cross.visible()) {
            INSTANCE.emitEdges(consumer, pose, cameraPos, box, cross, 0.018, true);
        }
        provider.draw(ClientPipelines.BLOCK_OVERLAY);
    }

    private final void emitFaces(VertexConsumer c, MatrixStack.Entry pose, Vec3d cam, Box b, Gradient gradient) {
        this.quad(c, pose, cam, b.minX, b.minY, b.minZ, b.maxX, b.minY, b.minZ, b.maxX, b.minY, b.maxZ, b.minX, b.minY, b.maxZ, gradient);
        this.quad(c, pose, cam, b.minX, b.maxY, b.maxZ, b.maxX, b.maxY, b.maxZ, b.maxX, b.maxY, b.minZ, b.minX, b.maxY, b.minZ, gradient);
        this.quad(c, pose, cam, b.minX, b.minY, b.minZ, b.minX, b.maxY, b.minZ, b.maxX, b.maxY, b.minZ, b.maxX, b.minY, b.minZ, gradient);
        this.quad(c, pose, cam, b.maxX, b.minY, b.maxZ, b.maxX, b.maxY, b.maxZ, b.minX, b.maxY, b.maxZ, b.minX, b.minY, b.maxZ, gradient);
        this.quad(c, pose, cam, b.minX, b.minY, b.maxZ, b.minX, b.maxY, b.maxZ, b.minX, b.maxY, b.minZ, b.minX, b.minY, b.minZ, gradient);
        this.quad(c, pose, cam, b.maxX, b.minY, b.minZ, b.maxX, b.maxY, b.minZ, b.maxX, b.maxY, b.maxZ, b.maxX, b.minY, b.maxZ, gradient);
    }

    private final void emitEdges(VertexConsumer c, MatrixStack.Entry pose, Vec3d cam, Box b, Gradient gradient, double radius, boolean dashed) {
        this.edge(c, pose, cam, b.minX, b.minY, b.minZ, b.maxX, b.minY, b.minZ, gradient, radius, dashed);
        this.edge(c, pose, cam, b.maxX, b.minY, b.minZ, b.maxX, b.minY, b.maxZ, gradient, radius, dashed);
        this.edge(c, pose, cam, b.maxX, b.minY, b.maxZ, b.minX, b.minY, b.maxZ, gradient, radius, dashed);
        this.edge(c, pose, cam, b.minX, b.minY, b.maxZ, b.minX, b.minY, b.minZ, gradient, radius, dashed);
        this.edge(c, pose, cam, b.minX, b.maxY, b.minZ, b.maxX, b.maxY, b.minZ, gradient, radius, dashed);
        this.edge(c, pose, cam, b.maxX, b.maxY, b.minZ, b.maxX, b.maxY, b.maxZ, gradient, radius, dashed);
        this.edge(c, pose, cam, b.maxX, b.maxY, b.maxZ, b.minX, b.maxY, b.maxZ, gradient, radius, dashed);
        this.edge(c, pose, cam, b.minX, b.maxY, b.maxZ, b.minX, b.maxY, b.minZ, gradient, radius, dashed);
        this.edge(c, pose, cam, b.minX, b.minY, b.minZ, b.minX, b.maxY, b.minZ, gradient, radius, dashed);
        this.edge(c, pose, cam, b.maxX, b.minY, b.minZ, b.maxX, b.maxY, b.minZ, gradient, radius, dashed);
        this.edge(c, pose, cam, b.maxX, b.minY, b.maxZ, b.maxX, b.maxY, b.maxZ, gradient, radius, dashed);
        this.edge(c, pose, cam, b.minX, b.minY, b.maxZ, b.minX, b.maxY, b.maxZ, gradient, radius, dashed);
    }

    private final void emitFaceCrosses(VertexConsumer c, MatrixStack.Entry pose, Vec3d cam, Box b, Gradient gradient, double radius) {
        this.tube(c, pose, cam, b.minX, b.minY, b.minZ, b.maxX, b.maxY, b.minZ, gradient, radius);
        this.tube(c, pose, cam, b.maxX, b.minY, b.minZ, b.minX, b.maxY, b.minZ, gradient, radius);
        this.tube(c, pose, cam, b.minX, b.minY, b.maxZ, b.maxX, b.maxY, b.maxZ, gradient, radius);
        this.tube(c, pose, cam, b.maxX, b.minY, b.maxZ, b.minX, b.maxY, b.maxZ, gradient, radius);
        this.tube(c, pose, cam, b.minX, b.minY, b.minZ, b.minX, b.maxY, b.maxZ, gradient, radius);
        this.tube(c, pose, cam, b.minX, b.minY, b.maxZ, b.minX, b.maxY, b.minZ, gradient, radius);
        this.tube(c, pose, cam, b.maxX, b.minY, b.minZ, b.maxX, b.maxY, b.maxZ, gradient, radius);
        this.tube(c, pose, cam, b.maxX, b.minY, b.maxZ, b.maxX, b.maxY, b.minZ, gradient, radius);
        this.tube(c, pose, cam, b.minX, b.minY, b.minZ, b.maxX, b.minY, b.maxZ, gradient, radius);
        this.tube(c, pose, cam, b.maxX, b.minY, b.minZ, b.minX, b.minY, b.maxZ, gradient, radius);
        this.tube(c, pose, cam, b.minX, b.maxY, b.minZ, b.maxX, b.maxY, b.maxZ, gradient, radius);
        this.tube(c, pose, cam, b.maxX, b.maxY, b.minZ, b.minX, b.maxY, b.maxZ, gradient, radius);
    }

    private final void edge(VertexConsumer c, MatrixStack.Entry pose, Vec3d cam, double x0, double y0, double z0, double x1, double y1, double z1, Gradient gradient, double radius, boolean dashed) {
        if (!dashed) {
            this.tube(c, pose, cam, x0, y0, z0, x1, y1, z1, gradient, radius);
            return;
        }
        for (int i = 0; i < 10; i += 2) {
            double start = (double)i / 10.0;
            double end = (double)(i + 1) / 10.0;
            this.tube(c, pose, cam, this.lerp(x0, x1, start), this.lerp(y0, y1, start), this.lerp(z0, z1, start), this.lerp(x0, x1, end), this.lerp(y0, y1, end), this.lerp(z0, z1, end), gradient, radius);
        }
    }

    private final void tube(VertexConsumer c, MatrixStack.Entry pose, Vec3d cam, double x0, double y0, double z0, double x1, double y1, double z1, Gradient gradient, double radius) {
        Vec3d direction = new Vec3d(x1 - x0, y1 - y0, z1 - z0);
        if (direction.lengthSquared() < 1.0E-8) {
            return;
        }
        Vec3d vec3d2 = direction.normalize();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"normalize(...)");
        direction = vec3d2;
        Vec3d reference = Math.abs(direction.y) < 0.9 ? new Vec3d(0.0, 1.0, 0.0) : new Vec3d(1.0, 0.0, 0.0);
        Vec3d vec3d3 = direction.crossProduct(reference).normalize().multiply(radius);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"scale(...)");
        Vec3d side = vec3d3;
        Vec3d vec3d4 = direction.crossProduct(side).normalize().multiply(radius);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"scale(...)");
        Vec3d up = vec3d4;
        for (int i = 0; i < 6; ++i) {
            double a0 = Math.PI * 2 * (double)i / (double)6;
            double a1 = Math.PI * 2 * (double)(i + 1) / (double)6;
            Vec3d o0 = side.multiply(Math.cos(a0)).add(up.multiply(Math.sin(a0)));
            Vec3d o1 = side.multiply(Math.cos(a1)).add(up.multiply(Math.sin(a1)));
            this.quad(c, pose, cam, x0 + o0.x, y0 + o0.y, z0 + o0.z, x0 + o1.x, y0 + o1.y, z0 + o1.z, x1 + o1.x, y1 + o1.y, z1 + o1.z, x1 + o0.x, y1 + o0.y, z1 + o0.z, gradient);
        }
    }

    private final void quad(VertexConsumer c, MatrixStack.Entry pose, Vec3d cam, double x0, double y0, double z0, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, Gradient gradient) {
        c.vertex(pose, (float)(x0 - cam.x), (float)(y0 - cam.y), (float)(z0 - cam.z)).color(gradient.color(x0, z0));
        c.vertex(pose, (float)(x1 - cam.x), (float)(y1 - cam.y), (float)(z1 - cam.z)).color(gradient.color(x1, z1));
        c.vertex(pose, (float)(x2 - cam.x), (float)(y2 - cam.y), (float)(z2 - cam.z)).color(gradient.color(x2, z2));
        c.vertex(pose, (float)(x3 - cam.x), (float)(y3 - cam.y), (float)(z3 - cam.z)).color(gradient.color(x3, z3));
    }

    private final double lerp(double from, double to, double progress) {
        return from + (to - from) * progress;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u000b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u000b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0013\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/utils/render/util/world/BlockOverlayRenderer$Gradient;", "", "Lnet/minecraft/Box;", "box", "", "colors", "<init>", "(Lnet/minecraft/Box;[I)V", "", "visible", "()Z", "", "x", "z", "", "color", "(DD)I", "[I", "centerX", "D", "centerZ", "rtx.kimiko:kimiko"})
    private static final class Gradient {
        @NotNull
        private final int[] colors;
        private final double centerX;
        private final double centerZ;

        public Gradient(@NotNull Box box, @NotNull int[] colors) {
            Intrinsics.checkNotNullParameter((Object)box, (String)"box");
            Intrinsics.checkNotNullParameter((Object)colors, (String)"colors");
            this.colors = colors;
            this.centerX = (box.minX + box.maxX) * 0.5;
            this.centerZ = (box.minZ + box.maxZ) * 0.5;
        }

        public final boolean visible() {
            for (int color : this.colors) {
                if (ColorEngine.alpha(color) <= 0) continue;
                return true;
            }
            return false;
        }

        public final int color(double x, double z) {
            if (this.colors.length == 1) {
                return this.colors[0];
            }
            double phase = Math.atan2(z - this.centerZ, x - this.centerX) / (Math.PI * 2) + 1.0;
            double scaled = (phase - Math.floor(phase)) * (double)this.colors.length;
            int index = (int)scaled % this.colors.length;
            int next = (index + 1) % this.colors.length;
            return ColorEngine.lerpColor(this.colors[index], this.colors[next], (float)(scaled - Math.floor(scaled)));
        }
    }
}

