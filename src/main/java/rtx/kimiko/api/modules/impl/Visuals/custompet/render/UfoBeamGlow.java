/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Visuals.custompet.render;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003JC\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\bH\u0007b\u0002\b\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0011J7\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J?\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u001f\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u000b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u000b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u000b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001dR\u0014\u0010 \u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010\u001dR\u0014\u0010!\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b#\u0010\"R\u0014\u0010$\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010\"R\u0014\u0010%\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010\"R\u0014\u0010&\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010\"R\u0014\u0010'\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010\"\u00a8\u0006("}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/render/UfoBeamGlow;", "", "<init>", "()V", "Lnet/minecraft/VertexConsumer;", "consumer", "Lnet/minecraft/MatrixStack$Entry;", "pose", "", "level", "stretch", "", "rgb", "ageInTicks", "", "Lkotlin/jvm/JvmStatic;", "render", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;FFIF)V", "intensity", "spin", "emitShaft", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;FIF)V", "poolY", "emitPool", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;FIFF)V", "alpha", "color", "(IF)I", "COLOR_BEAM", "I", "COLOR_ABDUCT", "COLOR_SCAN", "SEGMENTS", "TOP_Y", "F", "POOL_Y", "TOP_RADIUS", "BOTTOM_RADIUS", "POOL_INNER", "POOL_OUTER", "rtx.kimiko:kimiko"})
public final class UfoBeamGlow {
    @NotNull
    public static final UfoBeamGlow INSTANCE = new UfoBeamGlow();
    public static final int COLOR_BEAM = 5236991;
    public static final int COLOR_ABDUCT = 8847284;
    public static final int COLOR_SCAN = 14086911;
    private static final int SEGMENTS = 20;
    private static final float TOP_Y = -0.02f;
    private static final float POOL_Y = -0.7675f;
    private static final float TOP_RADIUS = 0.12f;
    private static final float BOTTOM_RADIUS = 0.4f;
    private static final float POOL_INNER = 0.24f;
    private static final float POOL_OUTER = 0.66f;

    private UfoBeamGlow() {
    }

    @JvmStatic
    public static final void render(@NotNull VertexConsumer consumer, @NotNull MatrixStack.Entry pose, float level, float stretch, int rgb, float ageInTicks) {
        Intrinsics.checkNotNullParameter((Object)consumer, (String)"consumer");
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        if (level <= 0.01f) {
            return;
        }
        float pulse = 0.86f + 0.14f * (float)Math.sin(ageInTicks * 0.18f);
        float intensity = MathHelper.clamp((float)level, (float)0.0f, (float)1.4f) * pulse;
        float poolY = -0.7675f + 0.032f / Math.max(stretch, 0.25f);
        float spin = ageInTicks * 0.021f;
        INSTANCE.emitShaft(consumer, pose, intensity, rgb, spin);
        INSTANCE.emitPool(consumer, pose, intensity, rgb, poolY, spin);
    }

    private final void emitShaft(VertexConsumer consumer, MatrixStack.Entry pose, float intensity, int rgb, float spin) {
        int topColor = this.color(rgb, intensity * 0.42f);
        int bottomColor = this.color(rgb, intensity * 0.16f);
        for (int i = 0; i < 20; ++i) {
            float a0 = spin + (float)(Math.PI * 2 * (double)i / (double)20);
            float a1 = spin + (float)(Math.PI * 2 * (double)(i + 1) / (double)20);
            float cos0 = (float)Math.cos(a0);
            float sin0 = (float)Math.sin(a0);
            float cos1 = (float)Math.cos(a1);
            float sin1 = (float)Math.sin(a1);
            consumer.vertex(pose, cos0 * 0.12f, -0.02f, sin0 * 0.12f).color(topColor);
            consumer.vertex(pose, cos1 * 0.12f, -0.02f, sin1 * 0.12f).color(topColor);
            consumer.vertex(pose, cos1 * 0.4f, -0.7675f, sin1 * 0.4f).color(bottomColor);
            consumer.vertex(pose, cos0 * 0.4f, -0.7675f, sin0 * 0.4f).color(bottomColor);
        }
    }

    private final void emitPool(VertexConsumer consumer, MatrixStack.Entry pose, float intensity, int rgb, float poolY, float spin) {
        int center = this.color(rgb, intensity * 0.62f);
        int middle = this.color(rgb, intensity * 0.34f);
        int edge = this.color(rgb, 0.0f);
        for (int i = 0; i < 20; ++i) {
            float a0 = -spin + (float)(Math.PI * 2 * (double)i / (double)20);
            float a1 = -spin + (float)(Math.PI * 2 * (double)(i + 1) / (double)20);
            float cos0 = (float)Math.cos(a0);
            float sin0 = (float)Math.sin(a0);
            float cos1 = (float)Math.cos(a1);
            float sin1 = (float)Math.sin(a1);
            consumer.vertex(pose, 0.0f, poolY, 0.0f).color(center);
            consumer.vertex(pose, 0.0f, poolY, 0.0f).color(center);
            consumer.vertex(pose, cos1 * 0.24f, poolY, sin1 * 0.24f).color(middle);
            consumer.vertex(pose, cos0 * 0.24f, poolY, sin0 * 0.24f).color(middle);
            consumer.vertex(pose, cos0 * 0.24f, poolY, sin0 * 0.24f).color(middle);
            consumer.vertex(pose, cos1 * 0.24f, poolY, sin1 * 0.24f).color(middle);
            consumer.vertex(pose, cos1 * 0.66f, poolY, sin1 * 0.66f).color(edge);
            consumer.vertex(pose, cos0 * 0.66f, poolY, sin0 * 0.66f).color(edge);
        }
    }

    private final int color(int rgb, float alpha) {
        int a = MathHelper.clamp((int)Math.round(alpha * 255.0f), (int)0, (int)255);
        return a << 24 | rgb & 0xFFFFFF;
    }
}

