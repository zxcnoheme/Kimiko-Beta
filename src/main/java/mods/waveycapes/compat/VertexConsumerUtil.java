/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.VertexConsumer
 *  org.joml.Matrix4f
 *  org.joml.Vector3f
 */
package mods.waveycapes.compat;

import net.minecraft.client.render.VertexConsumer;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import rtx.kimiko.utils.render.others.cape.CapeVertexColor;

public final class VertexConsumerUtil {
    private static final Vector3f POSITION_SCRATCH = new Vector3f();

    private VertexConsumerUtil() {
    }

    public static void addVertex(VertexConsumer consumer, Matrix4f matrix, float x, float y, float z, float u, float v, int overlay, int light, float normalX, float normalY, float normalZ, float alpha, boolean themed) {
        matrix.transformPosition(x, y, z, POSITION_SCRATCH);
        consumer.vertex(VertexConsumerUtil.POSITION_SCRATCH.x, VertexConsumerUtil.POSITION_SCRATCH.y, VertexConsumerUtil.POSITION_SCRATCH.z).color(themed ? CapeVertexColor.atUv(u, v, alpha) : CapeVertexColor.plain(alpha)).texture(u, v).overlay(overlay).light(light).normal(normalX, normalY, normalZ);
    }
}

