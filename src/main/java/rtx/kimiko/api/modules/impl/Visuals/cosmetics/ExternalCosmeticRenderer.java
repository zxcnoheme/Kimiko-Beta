package rtx.kimiko.api.modules.impl.Visuals.cosmetics;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Рендер внешних (вшитых) косметик на игроке — точная реплика референс-рендера Pulse
 * (CosmeticRenderer → GeckolibCosmeticRenderer → GeckoRenderHelper → GeoModelParser),
 * который работает с этими же model.json (SpaceVisuals).
 * <p>
 * Пайплайн (всё выверено по исходнику):
 * <ol>
 *   <li>якорь части (head/body applyTransform — уже применён вызывающим);</li>
 *   <li>Rz(180°);</li>
 *   <li>T(x, y+yOff, z) — мета в БЛОКАХ (yOff: HEAD +0.5, BODY −0.3);</li>
 *   <li>Ry(yaw)·Rx(pitch)·Rz(roll);</li>
 *   <li>S(scale/16);</li>
 *   <li>кости в px: T(−pos.x, pos.y, pos.z) → T(−pivot.x, pivot.y, pivot.z) →
 *       qZ(+z)·qY(−y)·qX(−x) → T(pivot.x, −pivot.y, −pivot.z); кубы — так же вокруг своего пивота;</li>
 *   <li>грани кубов — координаты с X→−X, UV = uv/textureSize (отрицательный uv_size = зеркало).</li>
 * </ol>
 */
public final class ExternalCosmeticRenderer {

    /** Смещение якоря в блоках — см. ExternalCosmetic.computeAnchorYOffset ( per-косметика). */
    private ExternalCosmeticRenderer() {
    }

    /**
     * Рисует косметику в точке крепления (трансформация якоря уже применена вызывающим).
     *
     * @param animTime время анимации, сек
     * @param moving   true — walk-анимация, false — idle
     */
    public static void render(@NotNull ExternalCosmetic cosmetic, @NotNull MatrixStack poseStack,
                              @NotNull OrderedRenderCommandQueue collector, int packedLight,
                              float animTime, boolean moving) {
        if (cosmetic.model == null) {
            return;
        }
        Identifier texture = cosmetic.texture();
        if (texture == null) {
            return;
        }
        BedrockModel.Anim anim = cosmetic.animFor(moving);
        collector.submitCustom(poseStack, RenderLayers.entityCutoutNoCull(texture), (pose, buffer) ->
                renderModel(cosmetic, anim, animTime, packedLight, pose, buffer));
    }

    private static void renderModel(ExternalCosmetic cosmetic, @Nullable BedrockModel.Anim anim,
                                    float time, int light, MatrixStack.Entry pose, VertexConsumer buffer) {
        MatrixStack stack = new MatrixStack();
        stack.peek().getPositionMatrix().set(pose.getPositionMatrix());
        stack.peek().getNormalMatrix().set(pose.getNormalMatrix());
        stack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180.0f));
        stack.translate(cosmetic.x, cosmetic.y + cosmetic.anchorYOffset, cosmetic.z);
        stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(cosmetic.yaw));
        stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(cosmetic.pitch));
        stack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(cosmetic.roll));
        float s = cosmetic.scale / 16.0f;
        stack.scale(s, s, s);
        for (BedrockModel.Bone root : cosmetic.model.rootBones) {
            renderBone(stack, buffer, root, anim, time, light,
                    cosmetic.model.textureWidth, cosmetic.model.textureHeight);
        }
    }

    private static void renderBone(MatrixStack stack, VertexConsumer buffer, BedrockModel.Bone bone,
                                   @Nullable BedrockModel.Anim anim, float time, int light,
                                   float texW, float texH) {
        stack.push();
        // позиция из анимации — до пивота, X с минусом (GeckoRenderHelper.translate)
        float[] apos = BedrockModel.sample(anim, bone.name, "position", time);
        if (apos != null) {
            stack.translate(-apos[0] / 16.0f, apos[1] / 16.0f, apos[2] / 16.0f);
        }
        float bx = bone.pivot != null ? bone.pivot[0] : 0.0f;
        float by = bone.pivot != null ? bone.pivot[1] : 0.0f;
        float bz = bone.pivot != null ? bone.pivot[2] : 0.0f;
        stack.translate(-bx / 16.0f, by / 16.0f, bz / 16.0f);
        // ротация: статика + анимация аддитивно; знаки X/Y инвертированы (конвенция GeoModelParser)
        float[] base = bone.rotation;
        float[] arot = BedrockModel.sample(anim, bone.name, "rotation", time);
        if (base != null || arot != null) {
            float rx = (base != null ? base[0] : 0.0f) + (arot != null ? arot[0] : 0.0f);
            float ry = (base != null ? base[1] : 0.0f) + (arot != null ? arot[1] : 0.0f);
            float rz = (base != null ? base[2] : 0.0f) + (arot != null ? arot[2] : 0.0f);
            stack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(rz));
            stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-ry));
            stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-rx));
        }
        stack.translate(bx / 16.0f, -by / 16.0f, -bz / 16.0f);
        for (BedrockModel.Cube cube : bone.cubes) {
            stack.push();
            if (cube.pivot != null && cube.rotation != null) {
                float cpx = cube.pivot[0];
                float cpy = cube.pivot[1];
                float cpz = cube.pivot[2];
                stack.translate(-cpx / 16.0f, cpy / 16.0f, cpz / 16.0f);
                stack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(cube.rotation[2]));
                stack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-cube.rotation[1]));
                stack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-cube.rotation[0]));
                stack.translate(cpx / 16.0f, -cpy / 16.0f, -cpz / 16.0f);
            }
            drawCube(stack.peek(), buffer, cube, light, texW, texH);
            stack.pop();
        }
        for (BedrockModel.Bone child : bone.children) {
            renderBone(stack, buffer, child, anim, time, light, texW, texH);
        }
        stack.pop();
    }

    /** Грани куба в координатах референса: X→−X, Y/Z — raw px, inflate расширяет обе стороны. */
    private static void drawCube(MatrixStack.Entry pose, VertexConsumer buffer, BedrockModel.Cube cube, int light,
                                 float texW, float texH) {
        float inf = cube.inflate;
        float xLow = -(cube.origin[0] + cube.size[0]) - inf;
        float xHigh = -cube.origin[0] + inf;
        float yLow = cube.origin[1] - inf;
        float yHigh = cube.origin[1] + cube.size[1] + inf;
        float zLow = cube.origin[2] - inf;
        float zHigh = cube.origin[2] + cube.size[2] + inf;
        for (var e : cube.faceUv.entrySet()) {
            float[] uv = e.getValue();
            float[] sz = cube.faceUvSize.get(e.getKey());
            float du = sz != null ? sz[0] / texW : 0.0f;
            float dv = sz != null ? sz[1] / texH : 0.0f;
            float u = uv[0] / texW;
            float v = uv[1] / texH;
            switch (e.getKey()) {
                case "north" -> quad(buffer, pose, light,
                        xHigh, yHigh, zLow, xLow, yHigh, zLow, xLow, yLow, zLow, xHigh, yLow, zLow,
                        u, v, u + du, v, u + du, v + dv, u, v + dv, 0.0f, 0.0f, -1.0f);
                case "south" -> quad(buffer, pose, light,
                        xLow, yHigh, zHigh, xHigh, yHigh, zHigh, xHigh, yLow, zHigh, xLow, yLow, zHigh,
                        u, v, u + du, v, u + du, v + dv, u, v + dv, 0.0f, 0.0f, 1.0f);
                case "east" -> quad(buffer, pose, light,
                        xHigh, yHigh, zHigh, xHigh, yHigh, zLow, xHigh, yLow, zLow, xHigh, yLow, zHigh,
                        u, v, u + du, v, u + du, v + dv, u, v + dv, 1.0f, 0.0f, 0.0f);
                case "west" -> quad(buffer, pose, light,
                        xLow, yHigh, zLow, xLow, yHigh, zHigh, xLow, yLow, zHigh, xLow, yLow, zLow,
                        u, v, u + du, v, u + du, v + dv, u, v + dv, -1.0f, 0.0f, 0.0f);
                case "up" -> quad(buffer, pose, light,
                        xLow, yHigh, zLow, xLow, yHigh, zHigh, xHigh, yHigh, zHigh, xHigh, yHigh, zLow,
                        u, v, u, v + dv, u + du, v + dv, u + du, v, 0.0f, 1.0f, 0.0f);
                case "down" -> quad(buffer, pose, light,
                        xHigh, yLow, zLow, xHigh, yLow, zHigh, xLow, yLow, zHigh, xLow, yLow, zLow,
                        u, v, u, v + dv, u + du, v + dv, u + du, v, 0.0f, -1.0f, 0.0f);
                default -> {
                }
            }
        }
    }

    private static void quad(VertexConsumer buffer, MatrixStack.Entry pose, int light,
                             float ax, float ay, float az, float bx, float by, float bz,
                             float cx, float cy, float cz, float dx, float dy, float dz,
                             float au, float av, float bu, float bv, float cu, float cv, float du, float dv,
                             float nx, float ny, float nz) {
        buffer.vertex(pose, ax, ay, az).color(-1).texture(au, av).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(pose, nx, ny, nz);
        buffer.vertex(pose, bx, by, bz).color(-1).texture(bu, bv).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(pose, nx, ny, nz);
        buffer.vertex(pose, cx, cy, cz).color(-1).texture(cu, cv).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(pose, nx, ny, nz);
        buffer.vertex(pose, dx, dy, dz).color(-1).texture(du, dv).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(pose, nx, ny, nz);
    }
}
