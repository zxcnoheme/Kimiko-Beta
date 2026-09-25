/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.OverlayTexture
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.others;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.OverlayTexture;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003JS\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0007b\u0002\b\u0011\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015\u00a8\u0006\u0016"}, d2={"Lrtx/kimiko/utils/render/others/WorldVertex;", "", "<init>", "()V", "Lnet/minecraft/VertexConsumer;", "consumer", "Lnet/minecraft/MatrixStack$Entry;", "pose", "", "x", "y", "z", "u", "v", "", "color", "", "Lkotlin/jvm/JvmStatic;", "textured", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;FFFFFI)V", "FULL_BRIGHT", "I", "rtx.kimiko:kimiko"})
public final class WorldVertex {
    @NotNull
    public static final WorldVertex INSTANCE = new WorldVertex();
    private static final int FULL_BRIGHT = 0xF000F0;

    private WorldVertex() {
    }

    @JvmStatic
    public static final void textured(@NotNull VertexConsumer consumer, @NotNull MatrixStack.Entry pose, float x, float y, float z, float u, float v, int color) {
        Intrinsics.checkNotNullParameter((Object)consumer, (String)"consumer");
        Intrinsics.checkNotNullParameter((Object)pose, (String)"pose");
        consumer.vertex(pose, x, y, z).texture(u, v).color(color).overlay(OverlayTexture.DEFAULT_UV).light(0xF000F0).normal(pose, 0.0f, 0.0f, 1.0f);
    }
}

