package rtx.kimiko.api.modules.impl.Visuals.customization;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.ui.theme.ThemeManager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Косметика «Wings» — детализированные крылья с рёбрами в стиле разлома JumpCircle:
 * каждое ребро — изогнутая светящаяся лента (белое ядро + неоновый ореол цвета акцента),
 * между рёбрами — полупрозрачная перепонка, по краю — яркая контурная дуга.
 * Рисуется процедурно, эмиссивным слоем (светится, не зависит от освещения).
 */
public class FeatheredWingsRenderer {
    private static final Map<AbstractClientPlayerEntity, State> STATES = new ConcurrentHashMap<>();
    private static Identifier whiteTexture;
    private static boolean textureReady;

    /** Параметры крыла: широкий веер рёбер от вертикали до горизонтали. */
    private static final int RIBS = 5;
    private static final int SEGMENTS = 12;
    private static final float[] RIB_LEN = {13.0f, 16.0f, 18.5f, 17.0f, 14.0f};
    private static final float RIB_ROOT_W = 2.2f;
    private static final float RIB_TIP_W = 0.4f;

    private static class State {
        final long createdAt = System.nanoTime();
        long lastUpdate = createdAt;
        float flapPhase;
        float flapSpeed = 1.8f;
        float flapAmp = 5.0f;
        float attackPulse;
    }

    public static void preload() {
        ensureTexture();
    }

    public static void reset() {
        STATES.clear();
    }

    public static void render(AbstractClientPlayerEntity player, MatrixStack poseStack, OrderedRenderCommandQueue collector,
                              int light, boolean flying, float walkSpeed, float attackTime) {
        if (player == null || poseStack == null || collector == null) {
            return;
        }
        ensureTexture();
        if (!textureReady) {
            return;
        }
        State st = STATES.computeIfAbsent(player, it -> new State());
        updateState(st, flying, walkSpeed, attackTime);
        final float phase = st.flapPhase;
        final float amp = st.flapAmp;
        final float attack = st.attackPulse;
        final int accent = ThemeManager.accent(255);
        final int lightF = light;
        collector.submitCustom(poseStack, RenderLayers.entityTranslucent(whiteTexture),
                (pose, buffer) -> draw(pose, buffer, lightF, phase, amp, attack, accent));
    }

    private static void updateState(State st, boolean flying, float walkSpeed, float attackTime) {
        long now = System.nanoTime();
        float dt = Math.min(0.05f, (float) ((now - st.lastUpdate) / 1.0E9));
        st.lastUpdate = now;
        float targetSpeed = flying ? 5.2f : (walkSpeed > 0.05f ? 4.2f : 1.8f);
        float targetAmp = flying ? 16.0f : (walkSpeed > 0.05f ? 10.0f : 5.0f);
        float smooth = Math.min(1.0f, dt * 4.0f);
        st.flapSpeed += (targetSpeed - st.flapSpeed) * smooth;
        st.flapAmp += (targetAmp - st.flapAmp) * smooth;
        st.flapPhase += dt * st.flapSpeed * (float) Math.PI * 2.0f;
        // рывок крыльев при ударе рукой
        float targetPulse = Math.max(0.0f, attackTime) > 0.35f ? 1.0f : 0.0f;
        st.attackPulse += (targetPulse - st.attackPulse) * Math.min(1.0f, dt * 9.0f);
    }

    private static void draw(MatrixStack.Entry pose, VertexConsumer buffer, int light, float phase, float amp, float attack, int accent) {
        MatrixStack local = new MatrixStack();
        local.peek().getPositionMatrix().set(pose.getPositionMatrix());
        local.peek().getNormalMatrix().set(pose.getNormalMatrix());
        // пиксельное пространство модели, Y вверх-вниз как у ваниллы
        local.scale(0.0625f, -0.0625f, 0.0625f);
        local.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0f));
        // точка крепления: лопатки (2.5px ниже основания шеи; -Y здесь = вниз по телу)
        local.translate(0.0f, -2.5f, -3.5f);
        for (int side = 0; side < 2; side++) {
            local.push();
            if (side == 1) {
                local.scale(-1.0f, 1.0f, 1.0f);
            }
            drawWing(local.peek(), buffer, phase, amp, attack, accent, side == 0 ? 1.0f : -1.0f);
            local.pop();
        }
    }

    private static void drawWing(MatrixStack.Entry pose, VertexConsumer buffer, float phase, float amp, float attack, int accent, float zSign) {
        float flapBase = (float) Math.sin(phase) * amp;
        float fold = (float) Math.cos(phase) * 1.2f;

        // рёбра: точки центральных линий (общие для перепонки и лент).
        // Здесь +Y = ВВЕРХ, поэтому веер направлен вниз-наружу (отрицательные углы):
        // верхнее ребро круто вниз, нижние всё более горизонтально — широкий веер.
        float[][][] ribs = new float[RIBS][SEGMENTS + 1][3];
        float[] tipT = new float[RIBS];
        for (int i = 0; i < RIBS; i++) {
            float spreadBoost = attack * 0.10f;
            float angle = -0.42f - i * (0.30f + spreadBoost);
            float len = RIB_LEN[i] * (1.0f + attack * 0.06f);
            float sn = (float) Math.sin(angle);
            float cs = (float) Math.cos(angle);
            // корень ребра: разнос по верхнему краю спины, верхнее ребро выше
            float rootX = 0.5f + i * 1.15f;
            float rootY = 1.2f - i * 0.45f;
            // мах: общее колебание + волна от корня к концу (как у разлома)
            float rot = (float) Math.toRadians(flapBase * 0.6f) + (float) Math.sin(phase - i * 0.5) * (float) Math.toRadians(amp) * 0.25f;
            // квадратичная безье: корень -> кончик, с изгибом назад (-Z наружу от спины)
            float cX = rootX + cs * len * 0.5f;
            float cY = rootY + sn * len * 0.5f - 0.5f;
            float cZ = -2.4f;
            float tX = rootX + cs * len;
            float tY = rootY + sn * len - 1.0f;
            float tZ = -3.4f - i * 0.22f;
            for (int s = 0; s <= SEGMENTS; s++) {
                float t = (float) s / SEGMENTS;
                float it = 1.0f - t;
                float x = it * it * rootX + 2.0f * it * t * cX + t * t * tX;
                float y = it * it * rootY + 2.0f * it * t * cY + t * t * tY;
                float z = it * it * 0.0f + 2.0f * it * t * cZ + t * t * tZ;
                // мах вокруг точки крепления (в плоскости XY)
                float cos = (float) Math.cos(rot);
                float sin = (float) Math.sin(rot);
                float nx2 = x * cos - y * sin;
                float ny2 = x * sin + y * cos;
                x = nx2;
                y = ny2;
                // складывание по Z от корня к концу + колыхание перепонки
                float billow = (float) Math.sin(t * Math.PI) * 0.7f * zSign;
                z += fold * t - billow;
                ribs[i][s][0] = x;
                ribs[i][s][1] = y;
                ribs[i][s][2] = z;
            }
            tipT[i] = rot;
        }

        // перепонка между соседними рёбрами: полупрозрачная, тает к краю
        for (int i = 0; i < RIBS - 1; i++) {
            for (int s = 0; s < SEGMENTS; s++) {
                float t0 = (float) s / SEGMENTS;
                float t1 = (float) (s + 1) / SEGMENTS;
                float tm = (t0 + t1) * 0.5f;
                int alpha = (int) (46.0f * (1.0f - tm) + 10.0f);
                int col = withAlpha(accent, alpha);
                float[] a0 = ribs[i][s];
                float[] a1 = ribs[i][s + 1];
                float[] b0 = ribs[i + 1][s];
                float[] b1 = ribs[i + 1][s + 1];
                quad(buffer, pose, a0, a1, b1, b0, col);
            }
        }

        // рёбра: два слоя — широкий неоновый ореол + узкое белое ядро (стиль разлома)
        for (int i = 0; i < RIBS; i++) {
            float wave = (float) Math.sin(phase - i * 0.5 + 1.2) * 0.5f + 0.5f;
            int glow = withAlpha(accent, (int) (55.0f + 30.0f * wave));
            int core = withAlpha(0xFFFFFFFF, (int) (190.0f + 55.0f * wave));
            ribbon(buffer, pose, ribs[i], RIB_ROOT_W * 2.3f, RIB_TIP_W * 2.3f, glow);
            ribbon(buffer, pose, ribs[i], RIB_ROOT_W, RIB_TIP_W, core);
        }

        // контурная дуга по концам рёбер — «кромка» крыла
        ribbonThroughPoints(buffer, pose, tips(ribs), 0.55f, withAlpha(0xFFFFFFFF, 215));
    }

    private static float[][] tips(float[][][] ribs) {
        float[][] out = new float[RIBS][3];
        for (int i = 0; i < RIBS; i++) {
            out[i][0] = ribs[i][SEGMENTS][0];
            out[i][1] = ribs[i][SEGMENTS][1];
            out[i][2] = ribs[i][SEGMENTS][2];
        }
        return out;
    }

    /** Лента по ломаной: сужение от корня к концу, нормаль по Z. */
    private static void ribbon(VertexConsumer buffer, MatrixStack.Entry pose, float[][] line, float w0, float w1, int color) {
        for (int s = 0; s < SEGMENTS; s++) {
            float t0 = (float) s / SEGMENTS;
            float t1 = (float) (s + 1) / SEGMENTS;
            float w0h = lerp(w0, w1, t0) * 0.5f;
            float w1h = lerp(w0, w1, t1) * 0.5f;
            float[] p0 = line[s];
            float[] p1 = line[s + 1];
            float dx = p1[0] - p0[0];
            float dy = p1[1] - p0[1];
            float dl = (float) Math.sqrt(dx * dx + dy * dy);
            if (dl < 1.0E-5f) {
                continue;
            }
            // перпендикуляр в плоскости XY
            float nx = -dy / dl;
            float ny = dx / dl;
            quad(buffer, pose,
                    new float[]{p0[0] + nx * w0h, p0[1] + ny * w0h, p0[2]},
                    new float[]{p0[0] - nx * w0h, p0[1] - ny * w0h, p0[2]},
                    new float[]{p1[0] - nx * w1h, p1[1] - ny * w1h, p1[2]},
                    new float[]{p1[0] + nx * w1h, p1[1] + ny * w1h, p1[2]},
                    color);
        }
    }

    /** Лента через опорные точки (сглаживание Катмулла-Ромом) — контур крыла. */
    private static void ribbonThroughPoints(VertexConsumer buffer, MatrixStack.Entry pose, float[][] pts, float width, int color) {
        int n = pts.length;
        float[] prev = null;
        for (int i = 0; i < n - 1; i++) {
            float[] pA = pts[Math.max(0, i - 1)];
            float[] pB = pts[i];
            float[] pC = pts[i + 1];
            float[] pD = pts[Math.min(n - 1, i + 2)];
            int steps = 6;
            for (int s = 0; s < steps; s++) {
                float t0 = (float) s / steps;
                float t1 = (float) (s + 1) / steps;
                float[] q0 = catmull(pA, pB, pC, pD, t0);
                float[] q1 = catmull(pA, pB, pC, pD, t1);
                if (prev == null) {
                    prev = q0;
                }
                float dx = q1[0] - q0[0];
                float dy = q1[1] - q0[1];
                float dl = (float) Math.sqrt(dx * dx + dy * dy);
                if (dl < 1.0E-5f) {
                    continue;
                }
                float nx = -dy / dl;
                float ny = dx / dl;
                float h = width * 0.5f;
                quad(buffer, pose,
                        new float[]{q0[0] + nx * h, q0[1] + ny * h, q0[2]},
                        new float[]{q0[0] - nx * h, q0[1] - ny * h, q0[2]},
                        new float[]{q1[0] - nx * h, q1[1] - ny * h, q1[2]},
                        new float[]{q1[0] + nx * h, q1[1] + ny * h, q1[2]},
                        color);
            }
        }
    }

    private static float[] catmull(float[] p0, float[] p1, float[] p2, float[] p3, float t) {
        float t2 = t * t;
        float t3 = t2 * t;
        float[] out = new float[3];
        for (int c = 0; c < 3; c++) {
            out[c] = 0.5f * ((2.0f * p1[c]) + (-p0[c] + p2[c]) * t
                    + (2.0f * p0[c] - 5.0f * p1[c] + 4.0f * p2[c] - p3[c]) * t2
                    + (-p0[c] + 3.0f * p1[c] - 3.0f * p2[c] + p3[c]) * t3);
        }
        return out;
    }

    private static void quad(VertexConsumer buffer, MatrixStack.Entry pose, float[] a, float[] b, float[] c, float[] d, int color) {
        float u0 = 0.0f;
        float v0 = 0.0f;
        float u1 = 0.02f;
        float v1 = 0.02f;
        vertex(buffer, pose, a[0], a[1], a[2], u0, v0, color);
        vertex(buffer, pose, b[0], b[1], b[2], u0, v1, color);
        vertex(buffer, pose, c[0], c[1], c[2], u1, v1, color);
        vertex(buffer, pose, d[0], d[1], d[2], u1, v0, color);
    }

    private static void vertex(VertexConsumer buffer, MatrixStack.Entry pose, float x, float y, float z, float u, float v, int color) {
        buffer.vertex(pose, x, y, z).color(color).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(15728880).normal(pose, 0.0f, 0.0f, 1.0f);
    }

    private static int withAlpha(int color, int alpha) {
        return (color & 0x00FFFFFF) | (Math.max(0, Math.min(255, alpha)) << 24);
    }

    private static float lerp(float a, float b, float t) {
        return a + (b - a) * t;
    }

    /** Белая 1x1 текстура для эмиссивного слоя: цвет задаём вершинами. */
    private static void ensureTexture() {
        if (textureReady) {
            return;
        }
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null || mc.getTextureManager() == null) {
            return;
        }
        try {
            if (whiteTexture == null) {
                NativeImage img = new NativeImage(1, 1, true);
                img.setColorArgb(0, 0, 0xFFFFFFFF);
                NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko wings white", img);
                tex.upload();
                if (tex.getGlTextureView() == null && tex.getGlTexture() != null) {
                    com.mojang.blaze3d.textures.GpuTextureView view = com.mojang.blaze3d.systems.RenderSystem.getDevice().createTextureView(tex.getGlTexture());
                    ((mixin.skins.AbstractTextureAccessor) (Object) tex).kimiko$setView(view);
                }
                whiteTexture = Identifier.of(Kimiko.Companion.namespace(), "textures/wings/white.png");
                mc.getTextureManager().registerTexture(whiteTexture, tex);
            }
            textureReady = true;
        } catch (Throwable ignored) {
        }
    }
}
