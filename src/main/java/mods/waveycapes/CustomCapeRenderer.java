/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.entity.state.PlayerEntityRenderState
 *  net.minecraft.client.render.command.OrderedRenderCommandQueue
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.OverlayTexture
 *  net.minecraft.client.model.ModelPart
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 *  org.joml.Vector4f
 */
package mods.waveycapes;

import java.util.List;
import mods.waveycapes.CapeRenderer;
import mods.waveycapes.NMSUtil;
import mods.waveycapes.VanillaCapeRenderer;
import mods.waveycapes.WaveyCapesBase;
import mods.waveycapes.compat.PlayerWrapper;
import mods.waveycapes.compat.VertexConsumerUtil;
import mods.waveycapes.support.ModSupport;
import mods.waveycapes.support.SupportManager;
import mods.waveycapes.versionless.CapeHolder;
import mods.waveycapes.versionless.CapeMovement;
import mods.waveycapes.versionless.CapeStyle;
import mods.waveycapes.versionless.ModBase;
import mods.waveycapes.versionless.sim.BasicSimulation;
import mods.waveycapes.versionless.util.CapePoint;
import mods.waveycapes.versionless.util.Vector3;
import mods.waveycapes.versionless.util.Vector4;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.model.ModelPart;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector4f;
import rtx.kimiko.api.modules.impl.Visuals.BetterMinecraft;

public class CustomCapeRenderer {
    private static final float CAPE_WAVE_SPEED = 0.5f;
    private static final float CAPE_WAVE_STRENGTH = 3.5f;
    private static final int PART_COUNT = 16;
    private final ModelPart[] customCape = NMSUtil.buildCape(64, 64, x -> 0, y -> y);
    private static final float CAPE_WIDTH = 0.625f;
    private static final float CAPE_HEIGHT = 1.0f;
    private static final float CAPE_DEPTH = 0.0625f;
    private static final Vector3[] FRONT_A = new Vector3[16];
    private static final Vector3[] FRONT_B = new Vector3[16];
    private static final Vector3[] FRONT_C = new Vector3[16];
    private static final Vector3[] BACK_A = new Vector3[16];
    private static final Vector3[] BACK_B = new Vector3[16];
    private static final Vector3[] BACK_C = new Vector3[16];
    private static final Matrix4f[] POSITION_MATRICES = new Matrix4f[16];
    private static final Vector3[] FRONT_NORMALS = new Vector3[16];
    private static final Vector3[] BACK_NORMALS = new Vector3[16];
    private static final Vector3 NORMAL_TOP = new Vector3();
    private static final Vector3 NORMAL_BOTTOM = new Vector3();
    private static final Vector3 NORMAL_FACE = new Vector3();
    private static final Quaternionf ROT_SCRATCH = new Quaternionf();
    private static final float DEG_TO_RAD = (float)Math.PI / 180;
    private static final Vector4f TRANSFORM_A = new Vector4f();
    private static final Vector4f TRANSFORM_B = new Vector4f();
    private static final Vector4f TRANSFORM_C = new Vector4f();
    private static VanillaCapeRenderer vanillaCape;

    public void render(PlayerWrapper capeRenderInfo, MatrixStack poseStack, OrderedRenderCommandQueue submitNodeCollector, int packedLight, float delta) {
        CapeRenderer renderer = this.getCapeRenderer(capeRenderInfo);
        if (renderer == null) {
            return;
        }
        if (!this.prepareCape(capeRenderInfo)) {
            return;
        }
        RenderLayer renderType = renderer.getRenderType(capeRenderInfo);
        if (renderType == null) {
            return;
        }
        boolean smooth = ModBase.config.capeStyle == CapeStyle.SMOOTH && renderer.vanillaUvValues();
        submitNodeCollector.submitCustom(poseStack, renderType, (pose, bufferBuilder) -> {
            MatrixStack capePoseStack = CustomCapeRenderer.poseStackFrom(pose);
            if (smooth) {
                this.renderSmoothCape(capePoseStack, bufferBuilder, capeRenderInfo, delta, packedLight);
            } else {
                ModelPart[] parts = this.customCape;
                boolean blockyUnderWater = capeRenderInfo.getAvatar().isSubmergedInWater();
                BasicSimulation blockySimulation = ((CapeHolder)capeRenderInfo.getAvatar()).getSimulation();
                for (int part = 0; part < 16; ++part) {
                    ModelPart model = parts[part];
                    this.modifyPoseStack(capePoseStack, capeRenderInfo, delta, part, blockyUnderWater, blockySimulation);
                    renderer.render(capeRenderInfo, part, model, capePoseStack, bufferBuilder, packedLight, OverlayTexture.DEFAULT_UV);
                    capePoseStack.pop();
                }
            }
        });
    }

    private static MatrixStack poseStackFrom(MatrixStack.Entry pose) {
        MatrixStack poseStack = new MatrixStack();
        poseStack.peek().copy(pose);
        return poseStack;
    }

    private boolean prepareCape(PlayerWrapper capeRenderInfo) {
        CapeHolder holder = (CapeHolder)capeRenderInfo.getAvatar();
        if (holder == null) {
            return false;
        }
        if (ModBase.simulationBroken) {
            return true;
        }
        try {
            holder.updateSimulation(16);
        }
        catch (Throwable throwable) {
            ModBase.simulationBroken = true;
        }
        return true;
    }

    private boolean hasSimulation(PlayerWrapper capeRenderInfo) {
        if (ModBase.simulationBroken) {
            return false;
        }
        BasicSimulation simulation = ((CapeHolder)capeRenderInfo.getAvatar()).getSimulation();
        return simulation != null && !simulation.empty();
    }

    private void renderSmoothCape(MatrixStack poseStack, VertexConsumer bufferBuilder, PlayerWrapper capeRenderInfo, float delta, int light) {
        int part;
        float alpha = SupportManager.getAlphaSupplier().get().floatValue();
        boolean themed = capeRenderInfo.isLocalPlayer();
        Matrix4f[] positionMatrices = POSITION_MATRICES;
        Vector3[] frontNormalVecs = FRONT_NORMALS;
        Vector3[] backNormalVecs = BACK_NORMALS;
        boolean inverse = light == 0xF000F0;
        boolean underWater = capeRenderInfo.getAvatar().isSubmergedInWater();
        BasicSimulation simulation = ((CapeHolder)capeRenderInfo.getAvatar()).getSimulation();
        for (part = 0; part < 16; ++part) {
            this.modifyPoseStack(poseStack, capeRenderInfo, delta, part, underWater, simulation);
            positionMatrices[part].set((Matrix4fc)poseStack.peek().getPositionMatrix());
            int prev = Math.max(part - 1, 0);
            CustomCapeRenderer.getNormalVec(positionMatrices[prev], positionMatrices[prev], positionMatrices[part], FRONT_A[part], FRONT_B[part], FRONT_C[part], inverse, frontNormalVecs[part]);
            CustomCapeRenderer.getNormalVec(positionMatrices[prev], positionMatrices[prev], positionMatrices[part], BACK_A[part], BACK_B[part], BACK_C[part], inverse, backNormalVecs[part]);
            poseStack.pop();
        }
        for (part = 0; part < 16; ++part) {
            Vector3 normalVec;
            float maxV;
            float minV;
            float maxU;
            float minU;
            if (part == 0) {
                minU = 0.015625f;
                maxU = 0.171875f;
                minV = 0.0f;
                maxV = 0.03125f;
                normalVec = CustomCapeRenderer.getNormalVec(positionMatrices[0], positionMatrices[0], positionMatrices[0], new Vector3(0.3125f, 0.0f, 0.0f), new Vector3(-0.3125f, 0.0f, 0.0f), new Vector3(0.3125f, 0.0f, 0.0625f), light == 0xF000F0);
                VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[0], 0.3125f, 0.0f, 0.0f, maxU, maxV, OverlayTexture.DEFAULT_UV, light, normalVec.x, normalVec.y, normalVec.z, alpha, themed);
                VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[0], -0.3125f, 0.0f, 0.0f, minU, maxV, OverlayTexture.DEFAULT_UV, light, normalVec.x, normalVec.y, normalVec.z, alpha, themed);
                VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[0], -0.3125f, 0.0f, -0.0625f, minU, minV, OverlayTexture.DEFAULT_UV, light, normalVec.x, normalVec.y, normalVec.z, alpha, themed);
                VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[0], 0.3125f, 0.0f, -0.0625f, maxU, minV, OverlayTexture.DEFAULT_UV, light, normalVec.x, normalVec.y, normalVec.z, alpha, themed);
            }
            if (part == 15) {
                minU = 0.171875f;
                maxU = 0.328125f;
                minV = 0.0f;
                maxV = 0.03125f;
                normalVec = CustomCapeRenderer.getNormalVec(positionMatrices[part], positionMatrices[part], positionMatrices[part], new Vector3(0.3125f, 1.0f, -0.0625f), new Vector3(-0.3125f, 1.0f, -0.0625f), new Vector3(0.3125f, 1.0f, 0.0f), light == 0xF000F0);
                VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[part], 0.3125f, 1.0f, -0.0625f, maxU, minV, OverlayTexture.DEFAULT_UV, light, normalVec.x, normalVec.y, normalVec.z, alpha, themed);
                VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[part], -0.3125f, 1.0f, -0.0625f, minU, minV, OverlayTexture.DEFAULT_UV, light, normalVec.x, normalVec.y, normalVec.z, alpha, themed);
                VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[part], -0.3125f, 1.0f, 0.0f, minU, maxV, OverlayTexture.DEFAULT_UV, light, normalVec.x, normalVec.y, normalVec.z, alpha, themed);
                VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[part], 0.3125f, 1.0f, 0.0f, maxU, maxV, OverlayTexture.DEFAULT_UV, light, normalVec.x, normalVec.y, normalVec.z, alpha, themed);
            }
            minU = 0.0f;
            maxU = 0.015625f;
            minV = 0.03125f * (float)(part + 1);
            maxV = minV + 0.03125f;
            normalVec = CustomCapeRenderer.getNormalVec(positionMatrices[part], positionMatrices[part], positionMatrices[Math.max(part - 1, 0)], new Vector3(-0.3125f, (float)(part + 1) * 0.0625f, 0.0f), new Vector3(-0.3125f, (float)(part + 1) * 0.0625f, -0.0625f), new Vector3(-0.3125f, (float)part * 0.0625f, 0.0f), light == 0xF000F0);
            VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[part], -0.3125f, (float)(part + 1) * 0.0625f, 0.0f, minU, maxV, OverlayTexture.DEFAULT_UV, light, normalVec.x, normalVec.y, normalVec.z, alpha, themed);
            VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[part], -0.3125f, (float)(part + 1) * 0.0625f, -0.0625f, maxU, maxV, OverlayTexture.DEFAULT_UV, light, normalVec.x, normalVec.y, normalVec.z, alpha, themed);
            VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[Math.max(part - 1, 0)], -0.3125f, (float)part * 0.0625f, -0.0625f, maxU, minV, OverlayTexture.DEFAULT_UV, light, normalVec.x, normalVec.y, normalVec.z, alpha, themed);
            VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[Math.max(part - 1, 0)], -0.3125f, (float)part * 0.0625f, 0.0f, minU, minV, OverlayTexture.DEFAULT_UV, light, normalVec.x, normalVec.y, normalVec.z, alpha, themed);
            minU = 0.171875f;
            maxU = 0.1875f;
            normalVec = CustomCapeRenderer.getNormalVec(positionMatrices[part], positionMatrices[part], positionMatrices[Math.max(part - 1, 0)], new Vector3(0.3125f, (float)(part + 1) * 0.0625f, -0.0625f), new Vector3(0.3125f, (float)(part + 1) * 0.0625f, 0.0f), new Vector3(0.3125f, (float)part * 0.0625f, -0.0625f), light == 0xF000F0);
            VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[part], 0.3125f, (float)(part + 1) * 0.0625f, -0.0625f, minU, maxV, OverlayTexture.DEFAULT_UV, light, normalVec.x, normalVec.y, normalVec.z, alpha, themed);
            VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[part], 0.3125f, (float)(part + 1) * 0.0625f, 0.0f, maxU, maxV, OverlayTexture.DEFAULT_UV, light, normalVec.x, normalVec.y, normalVec.z, alpha, themed);
            VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[Math.max(part - 1, 0)], 0.3125f, (float)part * 0.0625f, 0.0f, maxU, minV, OverlayTexture.DEFAULT_UV, light, normalVec.x, normalVec.y, normalVec.z, alpha, themed);
            VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[Math.max(part - 1, 0)], 0.3125f, (float)part * 0.0625f, -0.0625f, minU, minV, OverlayTexture.DEFAULT_UV, light, normalVec.x, normalVec.y, normalVec.z, alpha, themed);
            minU = 0.015625f;
            maxU = 0.171875f;
            Vector3 normalVecTop = CustomCapeRenderer.average(frontNormalVecs[part], frontNormalVecs[Math.max(part - 1, 0)], NORMAL_TOP);
            Vector3 normalVecBottom = CustomCapeRenderer.average(frontNormalVecs[part], frontNormalVecs[Math.min(part + 1, 15)], NORMAL_BOTTOM);
            VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[Math.max(part - 1, 0)], 0.3125f, (float)part * 0.0625f, -0.0625f, maxU, minV, OverlayTexture.DEFAULT_UV, light, normalVecTop.x, normalVecTop.y, normalVecTop.z, alpha, themed);
            VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[Math.max(part - 1, 0)], -0.3125f, (float)part * 0.0625f, -0.0625f, minU, minV, OverlayTexture.DEFAULT_UV, light, normalVecTop.x, normalVecTop.y, normalVecTop.z, alpha, themed);
            VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[part], -0.3125f, (float)(part + 1) * 0.0625f, -0.0625f, minU, maxV, OverlayTexture.DEFAULT_UV, light, normalVecBottom.x, normalVecBottom.y, normalVecBottom.z, alpha, themed);
            VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[part], 0.3125f, (float)(part + 1) * 0.0625f, -0.0625f, maxU, maxV, OverlayTexture.DEFAULT_UV, light, normalVecBottom.x, normalVecBottom.y, normalVecBottom.z, alpha, themed);
            minU = 0.1875f;
            maxU = 0.34375f;
            normalVecTop = CustomCapeRenderer.average(backNormalVecs[part], backNormalVecs[Math.max(part - 1, 0)], NORMAL_TOP);
            normalVecBottom = CustomCapeRenderer.average(backNormalVecs[part], backNormalVecs[Math.min(part + 1, 15)], NORMAL_BOTTOM);
            VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[Math.max(part - 1, 0)], 0.3125f, (float)part * 0.0625f, 0.0f, minU, minV, OverlayTexture.DEFAULT_UV, light, normalVecTop.x, normalVecTop.y, normalVecTop.z, alpha, themed);
            VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[Math.max(part - 1, 0)], -0.3125f, (float)part * 0.0625f, 0.0f, maxU, minV, OverlayTexture.DEFAULT_UV, light, normalVecTop.x, normalVecTop.y, normalVecTop.z, alpha, themed);
            VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[part], -0.3125f, (float)(part + 1) * 0.0625f, 0.0f, maxU, maxV, OverlayTexture.DEFAULT_UV, light, normalVecBottom.x, normalVecBottom.y, normalVecBottom.z, alpha, themed);
            VertexConsumerUtil.addVertex(bufferBuilder, positionMatrices[part], 0.3125f, (float)(part + 1) * 0.0625f, 0.0f, minU, maxV, OverlayTexture.DEFAULT_UV, light, normalVecBottom.x, normalVecBottom.y, normalVecBottom.z, alpha, themed);
        }
    }

    private void modifyPoseStack(MatrixStack poseStack, PlayerWrapper capeRenderInfo, float h, int part, boolean underWater, BasicSimulation simulation) {
        if (WaveyCapesBase.config.capeMovement != CapeMovement.VANILLA && !ModBase.simulationBroken && simulation != null && !simulation.empty()) {
            this.modifyPoseStackSimulation(poseStack, capeRenderInfo, h, part, underWater, simulation);
            return;
        }
        PlayerEntityRenderState renderState = capeRenderInfo.getRenderState();
        poseStack.push();
        poseStack.translate(0.0, 0.0, 0.125);
        CustomCapeRenderer.rotateX(poseStack, 6.0f + renderState.field_53537 / 2.0f + renderState.field_53536 + this.getNatrualWindSwing(part, underWater));
        CustomCapeRenderer.rotateZ(poseStack, renderState.field_53538 / 2.0f);
        CustomCapeRenderer.rotateY(poseStack, 180.0f - renderState.field_53538 / 2.0f);
    }

    private static void rotateX(MatrixStack poseStack, float degrees) {
        poseStack.multiply((Quaternionfc)ROT_SCRATCH.rotationX(degrees * ((float)Math.PI / 180)));
    }

    private static void rotateY(MatrixStack poseStack, float degrees) {
        poseStack.multiply((Quaternionfc)ROT_SCRATCH.rotationY(degrees * ((float)Math.PI / 180)));
    }

    private static void rotateZ(MatrixStack poseStack, float degrees) {
        poseStack.multiply((Quaternionfc)ROT_SCRATCH.rotationZ(degrees * ((float)Math.PI / 180)));
    }

    private void modifyPoseStackSimulation(MatrixStack poseStack, PlayerWrapper capeRenderInfo, float delta, int part, boolean underWater, BasicSimulation simulation) {
        poseStack.push();
        poseStack.translate(0.0, 0.0, 0.125);
        List<? extends CapePoint> points = simulation.getPoints();
        CapePoint root = points.get(0);
        CapePoint current = points.get(part);
        float x = current.getLerpX(delta) - root.getLerpX(delta);
        if (x > 0.0f) {
            x = 0.0f;
        }
        float y = root.getLerpY(delta) - (float)part - current.getLerpY(delta);
        float z = root.getLerpZ(delta) - current.getLerpZ(delta);
        float sidewaysRotationOffset = 0.0f;
        float partRotation = this.getRotation(delta, part, simulation);
        float height = 0.0f;
        float naturalWindSwing = this.getNatrualWindSwing(part, underWater);
        CustomCapeRenderer.rotateX(poseStack, 6.0f + height + naturalWindSwing);
        CustomCapeRenderer.rotateZ(poseStack, sidewaysRotationOffset / 2.0f);
        CustomCapeRenderer.rotateY(poseStack, 180.0f - sidewaysRotationOffset / 2.0f);
        poseStack.translate(-z / 16.0f, y / 16.0f, x / 16.0f);
        poseStack.translate(0.0, 0.03, -0.03);
        poseStack.translate(0.0f, (float)part * 1.0f / 16.0f, (float)(part * 0 / 16));
        CustomCapeRenderer.rotateX(poseStack, -partRotation);
        poseStack.translate(0.0f, (float)(-part) * 1.0f / 16.0f, (float)(-part * 0 / 16));
        poseStack.translate(0.0, -0.03, 0.03);
    }

    private float getRotation(float delta, int part, BasicSimulation simulation) {
        if (part == 15) {
            return this.getRotation(delta, part - 1, simulation);
        }
        List<? extends CapePoint> points = simulation.getPoints();
        CapePoint a = points.get(part);
        CapePoint b = points.get(part + 1);
        float dx = b.getLerpX(delta) - a.getLerpX(delta);
        float dy = b.getLerpY(delta) - a.getLerpY(delta);
        return (float)(Math.toDegrees(Math.atan2(dx, dy)) + 180.0);
    }

    private double getAngle(Vector3 a, Vector3 b) {
        Vector3 angle = b.subtract(a);
        return Math.toDegrees(Math.atan2(angle.x, angle.y)) + 180.0;
    }

    private float getNatrualWindSwing(int part, boolean underwater) {
        if (!BetterMinecraft.capeWavesEnabled()) {
            return 0.0f;
        }
        long highlightedPart = (long)((double)System.currentTimeMillis() / (underwater ? 9.0 : 3.0) * 0.5) % 360L;
        float relativePart = (float)(part + 1) / 16.0f;
        return (float)(Math.sin(Math.toRadians(relativePart * 360.0f - (float)highlightedPart)) * 3.5);
    }

    private static Vector3 getNormalVec(Matrix4f matrix1, Matrix4f matrix2, Matrix4f matrix3, Vector3 vector1, Vector3 vector2, Vector3 vector3, boolean inverse) {
        return CustomCapeRenderer.getNormalVec(matrix1, matrix2, matrix3, vector1, vector2, vector3, inverse, NORMAL_FACE);
    }

    private static Vector3 getNormalVec(Matrix4f matrix1, Matrix4f matrix2, Matrix4f matrix3, Vector3 vector1, Vector3 vector2, Vector3 vector3, boolean inverse, Vector3 out) {
        TRANSFORM_A.set(vector1.x, vector1.y, vector1.z, 1.0f);
        matrix1.transform(TRANSFORM_A);
        TRANSFORM_B.set(vector2.x, vector2.y, vector2.z, 1.0f);
        matrix2.transform(TRANSFORM_B);
        TRANSFORM_C.set(vector3.x, vector3.y, vector3.z, 1.0f);
        matrix3.transform(TRANSFORM_C);
        float bx = CustomCapeRenderer.TRANSFORM_B.x - CustomCapeRenderer.TRANSFORM_A.x;
        float by = CustomCapeRenderer.TRANSFORM_B.y - CustomCapeRenderer.TRANSFORM_A.y;
        float bz = CustomCapeRenderer.TRANSFORM_B.z - CustomCapeRenderer.TRANSFORM_A.z;
        float cx = CustomCapeRenderer.TRANSFORM_C.x - CustomCapeRenderer.TRANSFORM_A.x;
        float cy = CustomCapeRenderer.TRANSFORM_C.y - CustomCapeRenderer.TRANSFORM_A.y;
        float cz = CustomCapeRenderer.TRANSFORM_C.z - CustomCapeRenderer.TRANSFORM_A.z;
        float nx = by * cz - bz * cy;
        float ny = bz * cx - bx * cz;
        float nz = bx * cy - by * cx;
        float length = (float)Math.sqrt(nx * nx + ny * ny + nz * nz);
        if (length < 1.0E-4f) {
            nx = 0.0f;
            ny = 0.0f;
            nz = 0.0f;
        } else {
            nx /= length;
            ny /= length;
            nz /= length;
        }
        if (inverse) {
            nx = -nx;
            ny = -ny;
            nz = -nz;
        }
        out.x = nx;
        out.y = ny;
        out.z = nz;
        return out;
    }

    private static Vector3 average(Vector3 first, Vector3 second, Vector3 out) {
        out.x = (first.x + second.x) * 0.5f;
        out.y = (first.y + second.y) * 0.5f;
        out.z = (first.z + second.z) * 0.5f;
        return out;
    }

    private static Vector4 transform(Matrix4f matrix, Vector4 vector) {
        Vector4f vector4f = matrix.transform(new Vector4f(vector.x, vector.y, vector.z, vector.w));
        return new Vector4(vector4f.x, vector4f.y, vector4f.z, vector4f.w);
    }

    private CapeRenderer getCapeRenderer(PlayerWrapper capeRenderInfo) {
        for (ModSupport support : SupportManager.getSupportedMods()) {
            if (!support.shouldBeUsed(capeRenderInfo)) continue;
            return support.getRenderer();
        }
        if (capeRenderInfo.getCapeTexture() == null || !capeRenderInfo.isCapeVisible()) {
            return null;
        }
        return vanillaCape;
    }

    static {
        for (int part = 0; part < 16; ++part) {
            float low = (float)part * 0.0625f;
            float high = (float)(part + 1) * 0.0625f;
            CustomCapeRenderer.FRONT_A[part] = new Vector3(0.3125f, low, -0.0625f);
            CustomCapeRenderer.FRONT_B[part] = new Vector3(-0.3125f, low, -0.0625f);
            CustomCapeRenderer.FRONT_C[part] = new Vector3(0.3125f, high, -0.0625f);
            CustomCapeRenderer.BACK_A[part] = new Vector3(0.3125f, high, 0.0f);
            CustomCapeRenderer.BACK_B[part] = new Vector3(-0.3125f, high, 0.0f);
            CustomCapeRenderer.BACK_C[part] = new Vector3(0.3125f, low, 0.0f);
            CustomCapeRenderer.POSITION_MATRICES[part] = new Matrix4f();
            CustomCapeRenderer.FRONT_NORMALS[part] = new Vector3();
            CustomCapeRenderer.BACK_NORMALS[part] = new Vector3();
        }
        vanillaCape = new VanillaCapeRenderer();
    }
}

