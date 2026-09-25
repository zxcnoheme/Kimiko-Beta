/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.math.Box
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.util.world;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b!\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003JS\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000bH\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b\u0013\u0010\u0014JK\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000bH\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b\u0016\u0010\u0017JQ\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u00182\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000bH\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b\u0019\u0010\u001aJ?\u0010#\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b#\u0010$Jg\u0010,\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\b2\u0006\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020%2\u0006\u0010(\u001a\u00020%2\u0006\u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020%2\u0006\u0010+\u001a\u00020%2\u0006\u0010!\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b,\u0010-JA\u0010/\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\b0\u00182\u0006\u0010!\u001a\u00020\rH\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b/\u00100JK\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b\u0013\u00101JC\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b\u0016\u00102J7\u00104\u001a\u00020\u00112\u0006\u00103\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b4\u00105J7\u00106\u001a\u00020\u00112\u0006\u00103\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b6\u00105J\u0087\u0001\u0010=\u001a\u00020\u00112\u0006\u00103\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020%2\u0006\u0010(\u001a\u00020%2\u0006\u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020%2\u0006\u0010+\u001a\u00020%2\u0006\u00107\u001a\u00020%2\u0006\u00108\u001a\u00020%2\u0006\u00109\u001a\u00020%2\u0006\u0010:\u001a\u00020%2\u0006\u0010;\u001a\u00020%2\u0006\u0010<\u001a\u00020%2\u0006\u0010!\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b=\u0010>J_\u0010?\u001a\u00020\u00112\u0006\u00103\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020%2\u0006\u0010(\u001a\u00020%2\u0006\u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020%2\u0006\u0010+\u001a\u00020%2\u0006\u0010!\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b?\u0010@J7\u0010B\u001a\u00020\u00112\u0006\u00103\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010A\u001a\u00020\b2\u0006\u0010!\u001a\u00020\rH\u0002\u00a2\u0006\u0004\bB\u0010CR\u0014\u0010D\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bD\u0010E\u00a8\u0006F"}, d2={"Lrtx/kimiko/utils/render/util/world/WorldShapeRenderer;", "", "<init>", "()V", "Lnet/minecraft/VertexConsumerProvider$Immediate;", "provider", "Lnet/minecraft/MatrixStack;", "stack", "Lnet/minecraft/Vec3d;", "cameraPos", "center", "", "radius", "", "fillColor", "outlineColor", "lineWidth", "", "Lkotlin/jvm/JvmStatic;", "horizontalFilledCircle", "(Lnet/minecraft/VertexConsumerProvider$Immediate;Lnet/minecraft/MatrixStack;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;FIIF)V", "Lnet/minecraft/Box;", "box", "(Lnet/minecraft/VertexConsumerProvider$Immediate;Lnet/minecraft/MatrixStack;Lnet/minecraft/Vec3d;Lnet/minecraft/Box;IIF)V", "", "boxes", "(Lnet/minecraft/VertexConsumerProvider$Immediate;Lnet/minecraft/MatrixStack;Lnet/minecraft/Vec3d;Ljava/util/List;IIF)V", "Lnet/minecraft/VertexConsumer;", "c", "Lnet/minecraft/MatrixStack$Entry;", "pose", "cam", "b", "color", "half", "emitThickBoxEdges", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;Lnet/minecraft/Box;IF)V", "", "x0", "y0", "z0", "x1", "y1", "z1", "thickEdge", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;DDDDDDIF)V", "points", "trajectory", "(Lnet/minecraft/VertexConsumerProvider$Immediate;Lnet/minecraft/MatrixStack;Lnet/minecraft/Vec3d;Ljava/util/List;I)V", "(Lnet/minecraft/VertexConsumerProvider$Immediate;Lnet/minecraft/MatrixStack;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;FII)V", "(Lnet/minecraft/VertexConsumerProvider$Immediate;Lnet/minecraft/MatrixStack;Lnet/minecraft/Vec3d;Lnet/minecraft/Box;II)V", "consumer", "emitBoxFaces", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;Lnet/minecraft/Box;I)V", "emitBoxEdges", "x2", "y2", "z2", "x3", "y3", "z3", "quad", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;DDDDDDDDDDDDI)V", "edge", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;DDDDDDI)V", "worldPos", "lineVertex", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;I)V", "CIRCLE_SEGMENTS", "I", "rtx.kimiko:kimiko"})
public final class WorldShapeRenderer {
    @NotNull
    public static final WorldShapeRenderer INSTANCE = new WorldShapeRenderer();
    private static final int CIRCLE_SEGMENTS = 72;

    private WorldShapeRenderer() {
    }

    @JvmStatic
    public static final void horizontalFilledCircle(@NotNull VertexConsumerProvider.Immediate provider, @NotNull MatrixStack stack, @NotNull Vec3d cameraPos, @NotNull Vec3d center, float radius, int fillColor, int outlineColor, float lineWidth) {
        Intrinsics.checkNotNullParameter((Object)provider, (String)"provider");
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)cameraPos, (String)"cameraPos");
        Intrinsics.checkNotNullParameter((Object)center, (String)"center");
        if (lineWidth <= 1.0f || ColorEngine.alpha(outlineColor) <= 0) {
            WorldShapeRenderer.horizontalFilledCircle(provider, stack, cameraPos, center, radius, fillColor, outlineColor);
            return;
        }
        WorldShapeRenderer.horizontalFilledCircle(provider, stack, cameraPos, center, radius, fillColor, 0);
        if (radius <= 0.0f) {
            return;
        }
        float y = (float)center.y + 0.02f;
        float cx = (float)(center.x - cameraPos.x);
        float cy = y - (float)cameraPos.y;
        float cz = (float)(center.z - cameraPos.z);
        float half = lineWidth * 0.5f / 16.0f;
        float inner = Math.max(0.0f, radius - half);
        float outer = radius + half;
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        VertexConsumer vertexConsumer2 = provider.getBuffer(ClientPipelines.WORLD_PARTICLES_COLOR);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer ring = vertexConsumer2;
        for (int i = 0; i < 72; ++i) {
            float a0 = (float)(Math.PI * 2 * (double)i / (double)72);
            float a1 = (float)(Math.PI * 2 * (double)(i + 1) / (double)72);
            float c0 = MathHelper.cos((double)a0);
            float s0 = MathHelper.sin((double)a0);
            float c1 = MathHelper.cos((double)a1);
            float s1 = MathHelper.sin((double)a1);
            ring.vertex(pose, cx + c0 * inner, cy, cz + s0 * inner).color(outlineColor);
            ring.vertex(pose, cx + c0 * outer, cy, cz + s0 * outer).color(outlineColor);
            ring.vertex(pose, cx + c1 * outer, cy, cz + s1 * outer).color(outlineColor);
            ring.vertex(pose, cx + c1 * inner, cy, cz + s1 * inner).color(outlineColor);
        }
        provider.draw(ClientPipelines.WORLD_PARTICLES_COLOR);
    }

    @JvmStatic
    public static final void box(@NotNull VertexConsumerProvider.Immediate provider, @NotNull MatrixStack stack, @NotNull Vec3d cameraPos, @NotNull Box box, int fillColor, int outlineColor, float lineWidth) {
        Intrinsics.checkNotNullParameter((Object)provider, (String)"provider");
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)cameraPos, (String)"cameraPos");
        Intrinsics.checkNotNullParameter((Object)box, (String)"box");
        if (lineWidth <= 1.0f || ColorEngine.alpha(outlineColor) <= 0) {
            WorldShapeRenderer.box(provider, stack, cameraPos, box, fillColor, outlineColor);
            return;
        }
        WorldShapeRenderer.box(provider, stack, cameraPos, box, fillColor, 0);
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        VertexConsumer vertexConsumer2 = provider.getBuffer(ClientPipelines.WORLD_PARTICLES_COLOR);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer line = vertexConsumer2;
        float half = lineWidth * 0.5f / 16.0f;
        INSTANCE.emitThickBoxEdges(line, pose, cameraPos, box, outlineColor, half);
        provider.draw(ClientPipelines.WORLD_PARTICLES_COLOR);
    }

    @JvmStatic
    public static final void boxes(@NotNull VertexConsumerProvider.Immediate provider, @NotNull MatrixStack stack, @NotNull Vec3d cameraPos, @NotNull List<? extends Box> boxes, int fillColor, int outlineColor, float lineWidth) {
        VertexConsumer line;
        Intrinsics.checkNotNullParameter((Object)provider, (String)"provider");
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)cameraPos, (String)"cameraPos");
        Intrinsics.checkNotNullParameter(boxes, (String)"boxes");
        if (boxes.isEmpty() || ColorEngine.alpha(fillColor) <= 0 && ColorEngine.alpha(outlineColor) <= 0) {
            return;
        }
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        if (ColorEngine.alpha(fillColor) > 0) {
            VertexConsumer vertexConsumer2 = provider.getBuffer(ClientPipelines.WORLD_PARTICLES_COLOR);
            Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
            VertexConsumer fill = vertexConsumer2;
            for (Box box2 : boxes) {
                INSTANCE.emitBoxFaces(fill, pose, cameraPos, box2, fillColor);
            }
            provider.draw(ClientPipelines.WORLD_PARTICLES_COLOR);
        }
        if (ColorEngine.alpha(outlineColor) <= 0) {
            return;
        }
        if (lineWidth <= 1.0f) {
            VertexConsumer vertexConsumer3 = provider.getBuffer(ClientPipelines.TRAJECTORY_LINE);
            Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer3, (String)"getBuffer(...)");
            line = vertexConsumer3;
            for (Box box3 : boxes) {
                INSTANCE.emitBoxEdges(line, pose, cameraPos, box3, outlineColor);
            }
            provider.draw(ClientPipelines.TRAJECTORY_LINE);
            return;
        }
        VertexConsumer vertexConsumer4 = provider.getBuffer(ClientPipelines.WORLD_PARTICLES_COLOR);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer4, (String)"getBuffer(...)");
        line = vertexConsumer4;
        float half = lineWidth * 0.5f / 16.0f;
        for (Box box4 : boxes) {
            INSTANCE.emitThickBoxEdges(line, pose, cameraPos, box4, outlineColor, half);
        }
        provider.draw(ClientPipelines.WORLD_PARTICLES_COLOR);
    }

    private final void emitThickBoxEdges(VertexConsumer c, MatrixStack.Entry pose, Vec3d cam, Box b, int color, float half) {
        this.thickEdge(c, pose, cam, b.minX, b.minY, b.minZ, b.maxX, b.minY, b.minZ, color, half);
        this.thickEdge(c, pose, cam, b.maxX, b.minY, b.minZ, b.maxX, b.minY, b.maxZ, color, half);
        this.thickEdge(c, pose, cam, b.maxX, b.minY, b.maxZ, b.minX, b.minY, b.maxZ, color, half);
        this.thickEdge(c, pose, cam, b.minX, b.minY, b.maxZ, b.minX, b.minY, b.minZ, color, half);
        this.thickEdge(c, pose, cam, b.minX, b.maxY, b.minZ, b.maxX, b.maxY, b.minZ, color, half);
        this.thickEdge(c, pose, cam, b.maxX, b.maxY, b.minZ, b.maxX, b.maxY, b.maxZ, color, half);
        this.thickEdge(c, pose, cam, b.maxX, b.maxY, b.maxZ, b.minX, b.maxY, b.maxZ, color, half);
        this.thickEdge(c, pose, cam, b.minX, b.maxY, b.maxZ, b.minX, b.maxY, b.minZ, color, half);
        this.thickEdge(c, pose, cam, b.minX, b.minY, b.minZ, b.minX, b.maxY, b.minZ, color, half);
        this.thickEdge(c, pose, cam, b.maxX, b.minY, b.minZ, b.maxX, b.maxY, b.minZ, color, half);
        this.thickEdge(c, pose, cam, b.maxX, b.minY, b.maxZ, b.maxX, b.maxY, b.maxZ, color, half);
        this.thickEdge(c, pose, cam, b.minX, b.minY, b.maxZ, b.minX, b.maxY, b.maxZ, color, half);
    }

    private final void thickEdge(VertexConsumer c, MatrixStack.Entry pose, Vec3d cam, double x0, double y0, double z0, double x1, double y1, double z1, int color, float half) {
        double dx = x1 - x0;
        double dy = y1 - y0;
        double dz = z1 - z0;
        double len = Math.sqrt(dx * dx + dy * dy + dz * dz);
        if (len < 1.0E-6) {
            return;
        }
        double uy = dy / len;
        double mz = (z0 + z1) * 0.5;
        double vz = cam.z - mz;
        double uz = dz / len;
        double my = (y0 + y1) * 0.5;
        double vy = cam.y - my;
        double px = uy * vz - uz * vy;
        double mx = (x0 + x1) * 0.5;
        double vx = cam.x - mx;
        double ux = dx / len;
        double py = uz * vx - ux * vz;
        double pz = ux * vy - uy * vx;
        double pl = Math.sqrt(px * px + py * py + pz * pz);
        if (pl < 1.0E-6 && (pl = Math.sqrt((px = uy * 0.0 - uz * 1.0) * px + (py = uz * 0.0 - ux * 0.0) * py + (pz = ux * 1.0 - uy * 0.0) * pz)) < 1.0E-6) {
            px = 1.0;
            py = 0.0;
            pz = 0.0;
            pl = 1.0;
        }
        px = px / pl * (double)half;
        py = py / pl * (double)half;
        pz = pz / pl * (double)half;
        float ax = (float)(x0 - cam.x);
        float ay = (float)(y0 - cam.y);
        float az = (float)(z0 - cam.z);
        float bx = (float)(x1 - cam.x);
        float by = (float)(y1 - cam.y);
        float bz = (float)(z1 - cam.z);
        c.vertex(pose, (float)((double)ax - px), (float)((double)ay - py), (float)((double)az - pz)).color(color);
        c.vertex(pose, (float)((double)ax + px), (float)((double)ay + py), (float)((double)az + pz)).color(color);
        c.vertex(pose, (float)((double)bx + px), (float)((double)by + py), (float)((double)bz + pz)).color(color);
        c.vertex(pose, (float)((double)bx - px), (float)((double)by - py), (float)((double)bz - pz)).color(color);
    }

    @JvmStatic
    public static final void trajectory(@NotNull VertexConsumerProvider.Immediate provider, @NotNull MatrixStack stack, @NotNull Vec3d cameraPos, @NotNull List<? extends Vec3d> points, int color) {
        Intrinsics.checkNotNullParameter((Object)provider, (String)"provider");
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)cameraPos, (String)"cameraPos");
        Intrinsics.checkNotNullParameter(points, (String)"points");
        if (points.size() < 2 || ColorEngine.alpha(color) <= 0) {
            return;
        }
        VertexConsumer vertexConsumer2 = provider.getBuffer(ClientPipelines.TRAJECTORY_LINE);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer consumer = vertexConsumer2;
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        int segments = points.size() - 1;
        int n = points.size();
        for (int i = 1; i < n; ++i) {
            float fade = 1.0f - (float)i / (float)segments * 0.35f;
            int segmentColor = ColorEngine.multAlpha(color, fade);
            if (ColorEngine.alpha(segmentColor) <= 0) continue;
            INSTANCE.lineVertex(consumer, pose, cameraPos, points.get(i - 1), segmentColor);
            INSTANCE.lineVertex(consumer, pose, cameraPos, points.get(i), segmentColor);
        }
        provider.draw(ClientPipelines.TRAJECTORY_LINE);
    }

    @JvmStatic
    public static final void horizontalFilledCircle(@NotNull VertexConsumerProvider.Immediate provider, @NotNull MatrixStack stack, @NotNull Vec3d cameraPos, @NotNull Vec3d center, float radius, int fillColor, int outlineColor) {
        Intrinsics.checkNotNullParameter((Object)provider, (String)"provider");
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)cameraPos, (String)"cameraPos");
        Intrinsics.checkNotNullParameter((Object)center, (String)"center");
        if (radius <= 0.0f || ColorEngine.alpha(fillColor) <= 0 && ColorEngine.alpha(outlineColor) <= 0) {
            return;
        }
        float y = (float)center.y + 0.02f;
        float cx = (float)(center.x - cameraPos.x);
        float cy = y - (float)cameraPos.y;
        float cz = (float)(center.z - cameraPos.z);
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        if (ColorEngine.alpha(fillColor) > 0) {
            VertexConsumer vertexConsumer2 = provider.getBuffer(ClientPipelines.WORLD_PARTICLES_COLOR);
            Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
            VertexConsumer fill = vertexConsumer2;
            for (int i = 0; i < 72; ++i) {
                float a0 = (float)(Math.PI * 2 * (double)i / (double)72);
                float a1 = (float)(Math.PI * 2 * (double)(i + 1) / (double)72);
                float x0 = cx + MathHelper.cos((double)a0) * radius;
                float z0 = cz + MathHelper.sin((double)a0) * radius;
                float x1 = cx + MathHelper.cos((double)a1) * radius;
                float z1 = cz + MathHelper.sin((double)a1) * radius;
                fill.vertex(pose, cx, cy, cz).color(fillColor);
                fill.vertex(pose, x0, cy, z0).color(fillColor);
                fill.vertex(pose, x1, cy, z1).color(fillColor);
                fill.vertex(pose, cx, cy, cz).color(fillColor);
            }
            provider.draw(ClientPipelines.WORLD_PARTICLES_COLOR);
        }
        if (ColorEngine.alpha(outlineColor) > 0) {
            VertexConsumer vertexConsumer3 = provider.getBuffer(ClientPipelines.TRAJECTORY_LINE);
            Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer3, (String)"getBuffer(...)");
            VertexConsumer line = vertexConsumer3;
            Vec3d prev = null;
            for (int i = 0; i < 73; ++i) {
                float angle = (float)(Math.PI * 2 * (double)i / (double)72);
                Vec3d point = new Vec3d(center.x + (double)(MathHelper.cos((double)angle) * radius), (double)y, center.z + (double)(MathHelper.sin((double)angle) * radius));
                if (prev != null) {
                    INSTANCE.lineVertex(line, pose, cameraPos, prev, outlineColor);
                    INSTANCE.lineVertex(line, pose, cameraPos, point, outlineColor);
                }
                prev = point;
            }
            provider.draw(ClientPipelines.TRAJECTORY_LINE);
        }
    }

    @JvmStatic
    public static final void box(@NotNull VertexConsumerProvider.Immediate provider, @NotNull MatrixStack stack, @NotNull Vec3d cameraPos, @NotNull Box box, int fillColor, int outlineColor) {
        MatrixStack.Entry pose;
        Intrinsics.checkNotNullParameter((Object)provider, (String)"provider");
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)cameraPos, (String)"cameraPos");
        Intrinsics.checkNotNullParameter((Object)box, (String)"box");
        if (ColorEngine.alpha(fillColor) > 0) {
            VertexConsumer vertexConsumer2 = provider.getBuffer(ClientPipelines.WORLD_PARTICLES_COLOR);
            Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
            VertexConsumer fill = vertexConsumer2;
            MatrixStack.Entry entry2 = stack.peek();
            Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
            pose = entry2;
            INSTANCE.emitBoxFaces(fill, pose, cameraPos, box, fillColor);
            provider.draw(ClientPipelines.WORLD_PARTICLES_COLOR);
        }
        if (ColorEngine.alpha(outlineColor) > 0) {
            VertexConsumer vertexConsumer3 = provider.getBuffer(ClientPipelines.TRAJECTORY_LINE);
            Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer3, (String)"getBuffer(...)");
            VertexConsumer line = vertexConsumer3;
            MatrixStack.Entry entry3 = stack.peek();
            Intrinsics.checkNotNullExpressionValue((Object)entry3, (String)"last(...)");
            pose = entry3;
            INSTANCE.emitBoxEdges(line, pose, cameraPos, box, outlineColor);
            provider.draw(ClientPipelines.TRAJECTORY_LINE);
        }
    }

    private final void emitBoxFaces(VertexConsumer consumer, MatrixStack.Entry pose, Vec3d cameraPos, Box box, int color) {
        double minX = box.minX - cameraPos.x;
        double minY = box.minY - cameraPos.y;
        double minZ = box.minZ - cameraPos.z;
        double maxX = box.maxX - cameraPos.x;
        double maxY = box.maxY - cameraPos.y;
        double maxZ = box.maxZ - cameraPos.z;
        this.quad(consumer, pose, minX, minY, minZ, maxX, minY, minZ, maxX, minY, maxZ, minX, minY, maxZ, color);
        this.quad(consumer, pose, minX, maxY, maxZ, maxX, maxY, maxZ, maxX, maxY, minZ, minX, maxY, minZ, color);
        this.quad(consumer, pose, minX, minY, minZ, minX, maxY, minZ, maxX, maxY, minZ, maxX, minY, minZ, color);
        this.quad(consumer, pose, maxX, minY, maxZ, maxX, maxY, maxZ, minX, maxY, maxZ, minX, minY, maxZ, color);
        this.quad(consumer, pose, minX, minY, maxZ, minX, maxY, maxZ, minX, maxY, minZ, minX, minY, minZ, color);
        this.quad(consumer, pose, maxX, minY, minZ, maxX, maxY, minZ, maxX, maxY, maxZ, maxX, minY, maxZ, color);
    }

    private final void emitBoxEdges(VertexConsumer consumer, MatrixStack.Entry pose, Vec3d cameraPos, Box box, int color) {
        this.edge(consumer, pose, cameraPos, box.minX, box.minY, box.minZ, box.maxX, box.minY, box.minZ, color);
        this.edge(consumer, pose, cameraPos, box.maxX, box.minY, box.minZ, box.maxX, box.minY, box.maxZ, color);
        this.edge(consumer, pose, cameraPos, box.maxX, box.minY, box.maxZ, box.minX, box.minY, box.maxZ, color);
        this.edge(consumer, pose, cameraPos, box.minX, box.minY, box.maxZ, box.minX, box.minY, box.minZ, color);
        this.edge(consumer, pose, cameraPos, box.minX, box.maxY, box.minZ, box.maxX, box.maxY, box.minZ, color);
        this.edge(consumer, pose, cameraPos, box.maxX, box.maxY, box.minZ, box.maxX, box.maxY, box.maxZ, color);
        this.edge(consumer, pose, cameraPos, box.maxX, box.maxY, box.maxZ, box.minX, box.maxY, box.maxZ, color);
        this.edge(consumer, pose, cameraPos, box.minX, box.maxY, box.maxZ, box.minX, box.maxY, box.minZ, color);
        this.edge(consumer, pose, cameraPos, box.minX, box.minY, box.minZ, box.minX, box.maxY, box.minZ, color);
        this.edge(consumer, pose, cameraPos, box.maxX, box.minY, box.minZ, box.maxX, box.maxY, box.minZ, color);
        this.edge(consumer, pose, cameraPos, box.maxX, box.minY, box.maxZ, box.maxX, box.maxY, box.maxZ, color);
        this.edge(consumer, pose, cameraPos, box.minX, box.minY, box.maxZ, box.minX, box.maxY, box.maxZ, color);
    }

    private final void quad(VertexConsumer consumer, MatrixStack.Entry pose, double x0, double y0, double z0, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, int color) {
        consumer.vertex(pose, (float)x0, (float)y0, (float)z0).color(color);
        consumer.vertex(pose, (float)x1, (float)y1, (float)z1).color(color);
        consumer.vertex(pose, (float)x2, (float)y2, (float)z2).color(color);
        consumer.vertex(pose, (float)x3, (float)y3, (float)z3).color(color);
    }

    private final void edge(VertexConsumer consumer, MatrixStack.Entry pose, Vec3d cameraPos, double x0, double y0, double z0, double x1, double y1, double z1, int color) {
        this.lineVertex(consumer, pose, cameraPos, new Vec3d(x0, y0, z0), color);
        this.lineVertex(consumer, pose, cameraPos, new Vec3d(x1, y1, z1), color);
    }

    private final void lineVertex(VertexConsumer consumer, MatrixStack.Entry pose, Vec3d cameraPos, Vec3d worldPos, int color) {
        consumer.vertex(pose, (float)(worldPos.x - cameraPos.x), (float)(worldPos.y - cameraPos.y), (float)(worldPos.z - cameraPos.z)).color(color);
    }
}

