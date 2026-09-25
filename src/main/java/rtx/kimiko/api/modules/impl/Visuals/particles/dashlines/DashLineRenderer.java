/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Visuals.particles.dashlines;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.modules.impl.Visuals.particles.dashlines.DashLine;
import rtx.kimiko.api.modules.impl.Visuals.particles.dashlines.DashLineField;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0014\n\u0002\b\u0007\u0018\u0000 :2\u00020\u0001:\u0001:B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003JM\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0013\u0010\u0014J'\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ/\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u001f\u0010 J/\u0010!\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b!\u0010 Jg\u0010+\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\f2\u0006\u0010)\u001a\u00020\f2\u0006\u0010*\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b+\u0010,J\u001f\u0010/\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b/\u00100J\u0017\u00101\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b1\u00102J\u0017\u00103\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b3\u00102R\u0014\u00105\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00106R\u0014\u00107\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b7\u00106R\u0014\u00108\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00106R\u0014\u00109\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b9\u00106\u00a8\u0006;"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/dashlines/DashLineRenderer;", "", "<init>", "()V", "Lnet/minecraft/MatrixStack;", "stack", "Lnet/minecraft/VertexConsumerProvider$Immediate;", "provider", "Lrtx/kimiko/api/modules/impl/Visuals/particles/dashlines/DashLineField;", "field", "Lnet/minecraft/Vec3d;", "cameraPos", "", "headColor", "tailColor", "", "opacity", "glowStrength", "", "render", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/VertexConsumerProvider$Immediate;Lrtx/kimiko/api/modules/impl/Visuals/particles/dashlines/DashLineField;Lnet/minecraft/Vec3d;IIFF)V", "Lrtx/kimiko/api/modules/impl/Visuals/particles/dashlines/DashLine;", "line", "fade", "", "build", "(Lrtx/kimiko/api/modules/impl/Visuals/particles/dashlines/DashLine;Lnet/minecraft/Vec3d;F)Z", "Lnet/minecraft/VertexConsumer;", "consumer", "Lnet/minecraft/MatrixStack$Entry;", "pose", "emitBody", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;II)V", "emitGlow", "station", "offsetA0", "offsetA1", "offsetB0", "offsetB1", "colorA0", "colorA1", "colorB1", "colorB0", "emitStrip", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;IFFFFIIII)V", "t", "belly", "profile", "(FF)F", "alongAlpha", "(F)F", "headGlow", "", "path", "[F", "sides", "widths", "alphas", "Companion", "rtx.kimiko:kimiko"})
public final class DashLineRenderer {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final float[] path = new float[21];
    @NotNull
    private final float[] sides = new float[21];
    @NotNull
    private final float[] widths = new float[7];
    @NotNull
    private final float[] alphas = new float[7];
    private static final int STEPS = 6;
    private static final float CORE_FRACTION = 0.34f;
    private static final float TAIL_KEEP = 0.16f;
    private static final float TAIL_ALPHA = 0.3f;
    private static final float GLOW_FLARE = 2.1f;
    private static final float GLOW_REACH = 0.55f;

    public final void render(@NotNull MatrixStack stack, @NotNull VertexConsumerProvider.Immediate provider, @NotNull DashLineField field, @NotNull Vec3d cameraPos, int headColor, int tailColor, float opacity, float glowStrength) {
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)provider, (String)"provider");
        Intrinsics.checkNotNullParameter((Object)field, (String)"field");
        Intrinsics.checkNotNullParameter((Object)cameraPos, (String)"cameraPos");
        List<DashLine> members = field.alive();
        if (members.isEmpty() || opacity <= 0.002f) {
            return;
        }
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        RenderLayer bodyLayer = ClientPipelines.WORLD_PARTICLES_COLOR;
        VertexConsumer vertexConsumer2 = provider.getBuffer(bodyLayer);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer body = vertexConsumer2;
        for (DashLine line : members) {
            float fade = line.fade() * opacity;
            if (fade <= 0.003f || !this.build(line, cameraPos, fade)) continue;
            this.emitBody(body, pose, headColor, tailColor);
        }
        provider.draw(bodyLayer);
        if (glowStrength <= 0.002f) {
            return;
        }
        RenderLayer glowLayer = ClientPipelines.DASH_LINES_GLOW;
        VertexConsumer vertexConsumer3 = provider.getBuffer(glowLayer);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer3, (String)"getBuffer(...)");
        VertexConsumer glow = vertexConsumer3;
        for (DashLine line : members) {
            float fade = line.fade() * glowStrength;
            if (fade <= 0.003f || !this.build(line, cameraPos, fade)) continue;
            this.emitGlow(glow, pose, headColor, tailColor);
        }
        provider.draw(glowLayer);
    }

    private final boolean build(DashLine line, Vec3d cameraPos, float fade) {
        float headX = (float)(line.getPosX() - cameraPos.x);
        float headY = (float)(line.getPosY() - cameraPos.y);
        float headZ = (float)(line.getPosZ() - cameraPos.z);
        boolean valid = false;
        for (int station = 0; station < 7; ++station) {
            float sz;
            float sy;
            float t = (float)station / (float)6;
            float reach = line.getLength() * t;
            float px = headX - line.getDirX() * reach;
            float py = headY - line.getDirY() * reach;
            float pz = headZ - line.getDirZ() * reach;
            int slot = station * 3;
            this.path[slot] = px;
            this.path[slot + 1] = py;
            this.path[slot + 2] = pz;
            float sx = line.getDirY() * pz - line.getDirZ() * py;
            float length = (float)Math.sqrt(sx * sx + (sy = line.getDirZ() * px - line.getDirX() * pz) * sy + (sz = line.getDirX() * py - line.getDirY() * px) * sz);
            if (length < 1.0E-5f) {
                sx = -line.getDirY();
                sy = line.getDirX();
                sz = 0.0f;
                length = (float)Math.sqrt(sx * sx + sy * sy);
                if (length < 1.0E-5f) {
                    sx = 1.0f;
                    sy = 0.0f;
                    sz = 0.0f;
                    length = 1.0f;
                }
            }
            this.sides[slot] = sx / length;
            this.sides[slot + 1] = sy / length;
            this.sides[slot + 2] = sz / length;
            this.widths[station] = line.getHalfWidth() * this.profile(t, line.getBellyBias());
            this.alphas[station] = MathHelper.clamp((float)(this.alongAlpha(t) * fade), (float)0.0f, (float)1.0f);
            if (!(this.alphas[station] > 0.003f)) continue;
            valid = true;
        }
        return valid;
    }

    private final void emitBody(VertexConsumer consumer, MatrixStack.Entry pose, int headColor, int tailColor) {
        for (int station = 0; station < 6; ++station) {
            float tTop = (float)station / (float)6;
            float tBottom = (float)(station + 1) / (float)6;
            if (this.alphas[station] <= 0.003f && this.alphas[station + 1] <= 0.003f) continue;
            int topColor = ColorEngine.lerpColor(headColor, tailColor, tTop);
            int bottomColor = ColorEngine.lerpColor(headColor, tailColor, tBottom);
            int topCore = ColorEngine.multAlpha(topColor, this.alphas[station]);
            int bottomCore = ColorEngine.multAlpha(bottomColor, this.alphas[station + 1]);
            int topEdge = topColor & 0xFFFFFF;
            int bottomEdge = bottomColor & 0xFFFFFF;
            float halfTop = this.widths[station];
            float halfBottom = this.widths[station + 1];
            float coreTop = halfTop * 0.34f;
            float coreBottom = halfBottom * 0.34f;
            this.emitStrip(consumer, pose, station, -halfTop, -coreTop, -halfBottom, -coreBottom, topEdge, topCore, bottomCore, bottomEdge);
            this.emitStrip(consumer, pose, station, -coreTop, coreTop, -coreBottom, coreBottom, topCore, topCore, bottomCore, bottomCore);
            this.emitStrip(consumer, pose, station, coreTop, halfTop, coreBottom, halfBottom, topCore, topEdge, bottomEdge, bottomCore);
        }
    }

    private final void emitGlow(VertexConsumer consumer, MatrixStack.Entry pose, int headColor, int tailColor) {
        for (int station = 0; station < 6; ++station) {
            float tTop = (float)station / (float)6;
            float tBottom = (float)(station + 1) / (float)6;
            float topGlow = this.alphas[station] * this.headGlow(tTop);
            float bottomGlow = this.alphas[station + 1] * this.headGlow(tBottom);
            if (topGlow <= 0.003f && bottomGlow <= 0.003f) continue;
            int topColor = ColorEngine.multAlpha(ColorEngine.lerpColor(headColor, tailColor, tTop), MathHelper.clamp((float)topGlow, (float)0.0f, (float)1.0f));
            int bottomColor = ColorEngine.multAlpha(ColorEngine.lerpColor(headColor, tailColor, tBottom), MathHelper.clamp((float)bottomGlow, (float)0.0f, (float)1.0f));
            int topEdge = topColor & 0xFFFFFF;
            int bottomEdge = bottomColor & 0xFFFFFF;
            float halfTop = this.widths[station] * 2.1f;
            float halfBottom = this.widths[station + 1] * 2.1f;
            this.emitStrip(consumer, pose, station, -halfTop, 0.0f, -halfBottom, 0.0f, topEdge, topColor, bottomColor, bottomEdge);
            this.emitStrip(consumer, pose, station, 0.0f, halfTop, 0.0f, halfBottom, topColor, topEdge, bottomEdge, bottomColor);
        }
    }

    private final void emitStrip(VertexConsumer consumer, MatrixStack.Entry pose, int station, float offsetA0, float offsetA1, float offsetB0, float offsetB1, int colorA0, int colorA1, int colorB1, int colorB0) {
        int a = station * 3;
        int b = (station + 1) * 3;
        consumer.vertex(pose, this.path[a] + this.sides[a] * offsetA0, this.path[a + 1] + this.sides[a + 1] * offsetA0, this.path[a + 2] + this.sides[a + 2] * offsetA0).color(colorA0);
        consumer.vertex(pose, this.path[a] + this.sides[a] * offsetA1, this.path[a + 1] + this.sides[a + 1] * offsetA1, this.path[a + 2] + this.sides[a + 2] * offsetA1).color(colorA1);
        consumer.vertex(pose, this.path[b] + this.sides[b] * offsetB1, this.path[b + 1] + this.sides[b + 1] * offsetB1, this.path[b + 2] + this.sides[b + 2] * offsetB1).color(colorB1);
        consumer.vertex(pose, this.path[b] + this.sides[b] * offsetB0, this.path[b + 1] + this.sides[b + 1] * offsetB0, this.path[b + 2] + this.sides[b + 2] * offsetB0).color(colorB0);
    }

    private final float profile(float t, float belly) {
        float rise = MathHelper.clamp((float)(t / Math.max(0.02f, belly)), (float)0.0f, (float)1.0f);
        float tip = rise * rise * (3.0f - 2.0f * rise);
        float rest = 1.0f - t;
        return tip * (0.16f + 0.84000003f * rest * rest);
    }

    private final float alongAlpha(float t) {
        float rest = 1.0f - t;
        return rest * (0.3f + 0.7f * rest);
    }

    private final float headGlow(float t) {
        float x = MathHelper.clamp((float)(1.0f - t / 0.55f), (float)0.0f, (float)1.0f);
        return x * x;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\n\u0010\tR\u0014\u0010\u000b\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\tR\u0014\u0010\f\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\tR\u0014\u0010\r\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\t\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/dashlines/DashLineRenderer.Companion;", "", "<init>", "()V", "", "STEPS", "I", "", "CORE_FRACTION", "F", "TAIL_KEEP", "TAIL_ALPHA", "GLOW_FLARE", "GLOW_REACH", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

