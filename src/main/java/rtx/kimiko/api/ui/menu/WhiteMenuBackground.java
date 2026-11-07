package rtx.kimiko.api.ui.menu;

import rtx.kimiko.api.ui.mainmenu.MenuBackground;
import rtx.kimiko.api.ui.mainmenu.MenuBackgrounds;
import rtx.kimiko.api.ui.mainmenu.MenuSettings;
import rtx.kimiko.api.ui.theme.ThemeManager;
import rtx.kimiko.utils.render.render2d.Render2D;

public final class WhiteMenuBackground {
    public static final String BG = "kimiko:textures/menu/menu.png";

    // ——— интерактивность: параллакс от мыши, свечение за курсором, дрейфующие орбы
    private static float mouseX = -1.0f;
    private static float mouseY = -1.0f;
    private static float prevMX = -1.0f;
    private static float prevMY = -1.0f;
    private static float warpBoost = 1.0f;
    private static float smoothX;
    private static float smoothY;
    private static float parallaxAmp = 1.0f;
    private static float glowX;
    private static float glowY;
    private static boolean glowInit;
    private static long lastFrameNanos;
    private static float currentDt = 0.033f;

    /** Время между кадрами по реальному времени (сек), но не больше 0.05. */
    private static float frameDt() {
        long now = System.nanoTime();
        float dt = lastFrameNanos == 0L ? 0.033f : Math.min(0.05f, (float) ((now - lastFrameNanos) / 1.0E9));
        lastFrameNanos = now;
        return dt;
    }

    private WhiteMenuBackground() {
    }

    /** Обновляет «сырые» координаты мыши (вызывать перед отрисовкой фона). */
    public static void trackMouse(float mx, float my) {
        mouseX = mx;
        mouseY = my;
    }

    /** Плавно догоняет мышь: нормализованный параллакс и позиция свечения. */
    private static void updateInteractive(float sw, float sh) {
        currentDt = frameDt();
        parallaxAmp = 0.55f + MenuSettings.parallax() * 0.85f;

        float targetX = mouseX < 0.0f ? 0.0f : Math.max(-1.0f, Math.min(1.0f, (mouseX / Math.max(1.0f, sw) - 0.5f) * 2.0f));
        float targetY = mouseY < 0.0f ? 0.0f : Math.max(-1.0f, Math.min(1.0f, (mouseY / Math.max(1.0f, sh) - 0.5f) * 2.0f));
        smoothX += (targetX - smoothX) * Math.min(1.0f, currentDt * 4.5f);
        smoothY += (targetY - smoothY) * Math.min(1.0f, currentDt * 4.5f);

        float gx = mouseX < 0.0f ? sw * 0.5f : mouseX;
        float gy = mouseY < 0.0f ? sh * 0.5f : mouseY;
        if (!glowInit) {
            glowX = gx;
            glowY = gy;
            glowInit = true;
        }
        glowX += (gx - glowX) * Math.min(1.0f, currentDt * 9.0f);
        glowY += (gy - glowY) * Math.min(1.0f, currentDt * 9.0f);

        // скорость мыши — драйвер «Скорости» (варп ускоряется при движении)
        float mdx = mouseX >= 0.0f ? mouseX - prevMX : 0.0f;
        float mdy = mouseY >= 0.0f ? mouseY - prevMY : 0.0f;
        prevMX = mouseX >= 0.0f ? mouseX : prevMX;
        prevMY = mouseY >= 0.0f ? mouseY : prevMY;
        float moveSpeed = (float) Math.sqrt(mdx * mdx + mdy * mdy);
        float warpTarget = 1.0f + Math.min(2.6f, moveSpeed / 55.0f);
        warpBoost += (warpTarget - warpBoost) * Math.min(1.0f, currentDt * 6.0f);
    }

    public static void draw(float sw, float sh, float a) {
        MenuBackground bg = MenuBackgrounds.selected();
        updateInteractive(sw, sh);

        if (bg != null && bg.procedural()) {
            drawProceduralFullscreen(bg.source(), bg.kind(), sw, sh, a);
            return;
        }

        if (bg != null) {
            String tex = bg.texture();
            if (tex != null) {
                drawCoverParallax(tex, sw, sh, bg.aspect(), a);
                drawOrbs(sw, sh, a, false);
                drawCursorGlow(sh, a);
                return;
            }
            // пока полноразмерная текстура догружается — показываем готовое превью
            String fallbackTex = bg.preview();
            if (fallbackTex != null) {
                drawCoverParallax(fallbackTex, sw, sh, bg.aspect(), a);
                drawOrbs(sw, sh, a, false);
                drawCursorGlow(sh, a);
                return;
            }
        }

        drawPicture(sw, sh, a);
        drawOrbs(sw, sh, a, false);
        drawCursorGlow(sh, a);
    }

    /** Картинка с мягким параллаксом: фон сдвигается вслед за мышью. */
    private static void drawCoverParallax(String tex, float sw, float sh, float aspect, float a) {
        float ofsX = -smoothX * parallaxAmp * 16.0f;
        float ofsY = -smoothY * parallaxAmp * 10.0f;
        float x0 = Math.min(0.0f, ofsX) - 2.0f;
        float x1 = Math.max(sw, sw + ofsX) + 2.0f;
        float y0 = Math.min(0.0f, ofsY) - 2.0f;
        float y1 = Math.max(sh, sh + ofsY) + 2.0f;
        drawCover(tex, x0, y0, x1 - x0, y1 - y0, aspect, MenuColors.withAlpha(0xFFFFFFFF, (int) (255.0f * a)));
    }

    /** Два акцентных «светляка»: дрейфуют по своим траекториям и слегка тянутся к курсору. */
    private static void drawOrbs(float sw, float sh, float a, boolean strong) {
        long time = System.currentTimeMillis();
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;
        float boost = strong ? 1.45f : 1.0f;

        float ax = sw * (0.20f + 0.14f * (float) Math.sin(time / 4300.0)) + (glowX - sw * 0.5f) * 0.18f;
        float ay = sh * (0.34f + 0.16f * (float) Math.cos(time / 5400.0)) + (glowY - sh * 0.5f) * 0.18f;
        softCircle(ax, ay, sh * 0.16f * boost, accent, (int) (28 * a), (int) (6 * a));

        float bx = sw * (0.80f + 0.12f * (float) Math.cos(time / 3600.0)) + (glowX - sw * 0.5f) * 0.12f;
        float by = sh * (0.70f + 0.15f * (float) Math.sin(time / 4700.0)) + (glowY - sh * 0.5f) * 0.12f;
        softCircle(bx, by, sh * 0.20f * boost, accent, (int) (20 * a), (int) (5 * a));
    }

    /** Мягкое пятно света за курсором — фон «дышит» под мышью. */
    private static void drawCursorGlow(float sh, float a) {
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;
        softCircle(glowX, glowY, sh * 0.30f, accent, (int) (30 * a), (int) (6 * a));
    }

    /** Заполненный круг с плавным спадом к краям (несколько концентрических слоёв). */
    private static void softCircle(float cx, float cy, float radius, int accent, int centerAlpha, int edgeAlpha) {
        float[] sizes = {1.0f, 0.78f, 0.55f, 0.30f};
        for (int i = 0; i < sizes.length; i++) {
            float t = 1.0f - sizes[i];
            int alpha = edgeAlpha + (int) ((centerAlpha - edgeAlpha) * t);
            Render2D.circle(cx, cy, Math.max(1.0f, radius * sizes[i]), 36, MenuColors.withAlpha(accent, alpha));
        }
    }

    // ————————————————————————————————————————————————————————————
    // Процедурные интерактивные фоны (style:*)
    // ————————————————————————————————————————————————————————————

    /** Роутер полноэкранного процедурного фона. */
    private static void drawProceduralFullscreen(String source, MenuBackground.Kind kind, float sw, float sh, float a) {
        if (source != null && source.startsWith("style:")) {
            String style = source.substring(6);
            // тёмная база, одинаковая для всех стилей
            drawStyleBase(sw, sh, a);
            if ("fluid".equals(style)) {
                drawFluidFull(sw, sh, a);
            } else if ("grid".equals(style)) {
                drawGridFull(sw, sh, a);
            } else if ("bubbles".equals(style)) {
                drawBubblesFull(sw, sh, a);
            } else if ("streaks".equals(style)) {
                drawStreaksFull(sw, sh, a);
            } else if ("ripples".equals(style)) {
                drawRipplesFull(sw, sh, a);
            } else if ("storm".equals(style)) {
                drawStormFull(sw, sh, a);
            } else if ("orbit".equals(style)) {
                drawOrbitFull(sw, sh, a);
            } else if ("portal".equals(style)) {
                drawPortalFull(sw, sh, a);
            } else if ("rain".equals(style)) {
                drawRainFull(sw, sh, a);
            } else if ("warp".equals(style)) {
                drawWarpFull(sw, sh, a);
            } else if ("nebula".equals(style)) {
                drawNebulaFull(sw, sh, a);
            } else if ("matrix".equals(style)) {
                drawMatrixFull(sw, sh, a);
            } else if ("fireflies".equals(style)) {
                drawFirefliesFull(sw, sh, a);
            } else if ("abyss".equals(style)) {
                drawAbyssFull(sw, sh, a);
            } else if ("lava".equals(style)) {
                drawLavaFull(sw, sh, a);
            } else {
                drawGradient(sw, sh, a);
            }
            return;
        }
        if (kind == MenuBackground.Kind.AURORA) {
            drawAuroraFull(sw, sh, a);
            drawCursorGlow(sh, a);
            return;
        }
        drawGradient(sw, sh, a);
        drawOrbs(sw, sh, a, true);
        drawCursorGlow(sh, a);
    }

    /** Мягкий источник света: несколько концентрических слоёв — стабильно и быстро. */
    private static void glowPoint(float cx, float cy, float radius, int color, float alpha255) {
        if (alpha255 <= 0.5f || radius <= 0.5f) {
            return;
        }
        softCircle(cx, cy, radius, color & 0xFFFFFF, (int) Math.min(255.0f, alpha255), (int) (alpha255 * 0.18f));
    }

    /** Хвост с затуханием: серия сегментов от яркой головы к прозрачному концу. */
    private static void fadeTail(float hx, float hy, float tx, float ty, float thick, int color, float alpha255, int segs) {
        for (int i = 0; i < segs; i++) {
            float t0 = i / (float) segs;
            float t1 = (i + 1) / (float) segs;
            float ax = hx + (tx - hx) * t0;
            float ay = hy + (ty - hy) * t0;
            float bx = hx + (tx - hx) * t1;
            float by = hy + (ty - hy) * t1;
            float fade = 1.0f - t1;
            Render2D.line(ax, ay, bx, by, Math.max(0.5f, thick * fade),
                    MenuColors.withAlpha(color, (int) (alpha255 * fade * fade)));
        }
    }

    /** Тёмная подложка с глубиной: градиент, дыхание акцента снизу и трёхслойная параллакс-пыль. */
    private static final int DUST_COUNT = 46;
    private static final float[] dustX = new float[DUST_COUNT];
    private static final float[] dustY = new float[DUST_COUNT];
    private static final float[] dustZ = new float[DUST_COUNT];
    private static boolean dustInit;

    private static void drawStyleBase(float sw, float sh, float a) {
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;
        int top = ThemeManager.mix(MenuColors.rgba(9, 11, 16, 255), accent, 0.06f);
        int bottom = MenuColors.rgba(4, 5, 8, 255);
        Render2D.rect(0.0f, 0.0f, sw, sh, 0.0f, top, top, bottom, bottom);

        // «дыхание» акцента из глубины сцены
        long time = System.currentTimeMillis();
        float breath = 0.5f + 0.5f * (float) Math.sin(time / 5200.0);
        Render2D.rect(0.0f, sh * 0.42f, sw, sh * 0.58f, 0.0f,
                MenuColors.withAlpha(accent, 0),
                MenuColors.withAlpha(accent, 0),
                MenuColors.withAlpha(accent, (int) ((9.0f + 7.0f * breath) * a)),
                MenuColors.withAlpha(accent, (int) ((14.0f + 10.0f * breath) * a)));

        // параллакс-пыль: дальние слои медленнее, ближние крупнее и ярче
        if (!dustInit) {
            for (int i = 0; i < DUST_COUNT; i++) {
                dustX[i] = STK_RND.nextFloat();
                dustY[i] = STK_RND.nextFloat();
                dustZ[i] = STK_RND.nextFloat();
            }
            dustInit = true;
        }
        for (int i = 0; i < DUST_COUNT; i++) {
            float depth = 0.25f + 0.75f * dustZ[i];
            float drift = (time / (15000.0f + 10000.0f * dustZ[i])) % 1.0f;
            float px = dustX[i] * sw - smoothX * 26.0f * depth;
            float py = (((dustY[i] - drift) % 1.0f) + 1.0f) % 1.0f * sh - smoothY * 16.0f * depth;
            float tw = 0.5f + 0.5f * (float) Math.sin(time / (700.0f + dustZ[i] * 900.0f) + i * 1.7f);
            float s = 0.8f + 1.4f * depth;
            int alpha = (int) ((5.0f + 24.0f * depth * tw) * a);
            Render2D.rect(px, py, s, s, s * 0.5f,
                    i % 6 == 0 ? MenuColors.withAlpha(accent, alpha) : MenuColors.rgba(255, 255, 255, alpha));
        }
    }

    // ——— 1. «Жидкость»: слои плазмы догоняют курсор, сливаются мостами света и оставляют поток ———

    private static final int FLUID_BLOBS = 7;
    private static final float[] fluidX = new float[FLUID_BLOBS];
    private static final float[] fluidY = new float[FLUID_BLOBS];
    private static final float[] fluidVX = new float[FLUID_BLOBS];
    private static final float[] fluidVY = new float[FLUID_BLOBS];
    private static boolean fluidInit;
    private static final int FLUID_TRAIL = 12;
    private static final float[] fluidTX = new float[FLUID_TRAIL];
    private static final float[] fluidTY = new float[FLUID_TRAIL];
    private static int fluidTrailHead;

    private static void drawFluidFull(float sw, float sh, float a) {
        float dt = Math.max(0.005f, currentDt);
        if (!fluidInit) {
            for (int i = 0; i < FLUID_BLOBS; i++) {
                fluidX[i] = sw * 0.5f;
                fluidY[i] = sh * 0.5f;
                fluidVX[i] = 0.0f;
                fluidVY[i] = 0.0f;
            }
            for (int i = 0; i < FLUID_TRAIL; i++) {
                fluidTX[i] = sw * 0.5f;
                fluidTY[i] = sh * 0.5f;
            }
            fluidInit = true;
        }

        float mx = mouseX >= 0.0f ? mouseX : sw * 0.5f;
        float my = mouseY >= 0.0f ? mouseY : sh * 0.5f;
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;
        int tintA = ThemeManager.mix(accent, 0xFFFFFF, 0.35f);
        int tintB = ThemeManager.mix(accent, 0x1B2436, 0.45f);
        long time = System.currentTimeMillis();

        // поток: лента за курсором, втекающая в толщу
        float ldx = mx - fluidTX[fluidTrailHead];
        float ldy = my - fluidTY[fluidTrailHead];
        if (ldx * ldx + ldy * ldy > 9.0f) {
            fluidTrailHead = (fluidTrailHead + 1) % FLUID_TRAIL;
            fluidTX[fluidTrailHead] = mx;
            fluidTY[fluidTrailHead] = my;
        }
        for (int i = FLUID_TRAIL - 2; i >= 0; i--) {
            int i0 = (fluidTrailHead - i + FLUID_TRAIL) % FLUID_TRAIL;
            int i1 = (fluidTrailHead - i - 1 + FLUID_TRAIL) % FLUID_TRAIL;
            float fade = 1.0f - i / (float) FLUID_TRAIL;
            Render2D.line(fluidTX[i0], fluidTY[i0], fluidTX[i1], fluidTY[i1],
                    2.2f * fade + 0.5f, MenuColors.withAlpha(accent, (int) (34.0f * fade * a)));
        }

        // пружинная инерция пятен за курсором
        for (int i = 0; i < FLUID_BLOBS; i++) {
            float tx = mx + (float) Math.sin(time / (2600.0 + i * 730.0) + i * 1.7) * sw * idleSize(i);
            float ty = my + (float) Math.cos(time / (3100.0 + i * 610.0) + i * 2.3) * sh * idleSize(i);
            float lag = (1.6f + i * 0.85f) * dt;
            float nx = fluidX[i] + (tx - fluidX[i]) * Math.min(1.0f, lag);
            float ny = fluidY[i] + (ty - fluidY[i]) * Math.min(1.0f, lag);
            fluidVX[i] = (nx - fluidX[i]) / Math.max(0.001f, dt);
            fluidVY[i] = (ny - fluidY[i]) / Math.max(0.001f, dt);
            fluidX[i] = nx;
            fluidY[i] = ny;
        }

        // глубокая толща позади — медленная и тёмная, дышит параллаксом
        for (int i = 0; i < 3; i++) {
            glowPoint(fluidX[i] + smoothX * 14.0f, fluidY[i] + smoothY * 10.0f,
                    sh * (0.15f + 0.03f * i), tintB, 16.0f * a);
        }

        // «мосты»: сблизившиеся пятна стягиваются светящейся перемычкой
        for (int i = 0; i < FLUID_BLOBS; i++) {
            for (int j = i + 1; j < FLUID_BLOBS; j++) {
                float dx = fluidX[j] - fluidX[i];
                float dy = fluidY[j] - fluidY[i];
                float dist = (float) Math.sqrt(dx * dx + dy * dy);
                float span = (sh * (0.13f + 0.022f * i) + sh * (0.13f + 0.022f * j)) * 0.85f;
                if (dist < span && dist > 1.0f) {
                    float bond = 1.0f - dist / span;
                    Render2D.line(fluidX[i], fluidY[i], fluidX[j], fluidY[j],
                            2.0f + 5.0f * bond, MenuColors.withAlpha(tintA, (int) ((12.0f + 44.0f * bond) * a)));
                    if (bond > 0.45f) {
                        glowPoint((fluidX[i] + fluidX[j]) * 0.5f, (fluidY[i] + fluidY[j]) * 0.5f,
                                sh * 0.05f * bond, 0xFFFFFF, 44.0f * bond * a);
                    }
                }
            }
        }

        // пятна: свечение + ядро; быстрые пятна растягиваются по движению и брызгают искрами
        for (int i = 0; i < FLUID_BLOBS; i++) {
            float px = fluidX[i];
            float py = fluidY[i];
            float radius = sh * (0.13f + 0.022f * i);
            float speed = (float) Math.sqrt(fluidVX[i] * fluidVX[i] + fluidVY[i] * fluidVY[i]);
            float stretch = Math.min(1.8f, 1.0f + speed / 900.0f);
            int col = i % 3 == 0 ? tintA : accent;
            glowPoint(px, py, radius * stretch, col, 34.0f * a);
            Render2D.circle(px, py, radius * 0.34f, 18, MenuColors.withAlpha(col, (int) (40 * a)));
            if (speed > 420.0f && i % 2 == 0) {
                for (int s = 0; s < 3; s++) {
                    float ang = time / (240.0f + s * 90.0f) + i;
                    float sr = radius * (0.5f + 0.3f * s) + 6.0f;
                    Render2D.circle(px + (float) Math.cos(ang) * sr, py + (float) Math.sin(ang) * sr,
                            1.6f, 8, MenuColors.withAlpha(tintA, (int) (70 * a)));
                }
            }
        }
    }

    private static float idleSize(int i) {
        return 0.06f + 0.03f * i;
    }

    // ——— 2. «Сетка»: перспективный горизонт, электрическое поле и импульсы от курсора ———

    private static final int GRID_DOTS = 380;
    private static final float[] gridPX = new float[GRID_DOTS];
    private static final float[] gridPY = new float[GRID_DOTS];
    private static final float[] gridE = new float[GRID_DOTS];

    private static void drawGridFull(float sw, float sh, float a) {
        float dt = Math.max(0.005f, currentDt);
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;
        int tintA = ThemeManager.mix(accent, 0xFFFFFF, 0.4f);
        long time = System.currentTimeMillis();
        float mx = mouseX >= 0.0f ? mouseX : -9999.0f;
        float my = mouseY >= 0.0f ? mouseY : -9999.0f;

        // перспектива: горизонт дышит и смещается параллаксом
        float hy = sh * (0.58f + 0.02f * (float) Math.sin(time / 4100.0)) - smoothY * 18.0f;
        float vpx = sw * 0.5f - smoothX * 46.0f;
        glowPoint(vpx, hy, sh * 0.16f, accent, 26.0f * a);
        Render2D.rect(0.0f, hy, sw, 1.2f, 0.6f, MenuColors.withAlpha(accent, (int) (44 * a)));

        // лучи, сходящиеся к точке схода
        for (int i = -4; i <= 4; i++) {
            float side = Math.abs(i) / 4.0f;
            Render2D.line(vpx, hy, vpx + i * sw * 0.19f, sh + 8.0f, 1.1f,
                    MenuColors.rgba(255, 255, 255, (int) ((24.0f - 15.0f * side) * a)));
        }
        // горизонтальные ряды: у горизонта плотно и тускло, внизу редко и ярче
        for (int k = 1; k <= 7; k++) {
            float t = k / 7.0f;
            float rowY = hy + (sh - hy) * t * t;
            Render2D.rect(0.0f, rowY, sw, 1.0f, 0.5f,
                    MenuColors.rgba(255, 255, 255, (int) ((8.0f + 16.0f * t) * a)));
        }
        // дымка над горизонтом
        Render2D.rect(0.0f, hy - sh * 0.16f, sw, sh * 0.16f, 0.0f,
                MenuColors.withAlpha(accent, 0), MenuColors.withAlpha(accent, 0),
                MenuColors.withAlpha(accent, (int) (18 * a)), MenuColors.withAlpha(accent, (int) (18 * a)));

        // поле точек: дешёвые rect'ы, возбуждённые курсором — светящиеся круги
        float step = Math.max(48.0f, sw / 30.0f);
        float reach = Math.min(sw, sh) * 0.36f;
        float pulseR = mx > -999.0f ? (time % 1600L) / 1600.0f * reach * 1.6f : -1.0f;
        int idx = 0;
        for (float gy = sh * 0.05f; gy < sh && idx < GRID_DOTS; gy += step) {
            for (float gx = sw * 0.04f; gx < sw && idx < GRID_DOTS; gx += step) {
                float wave = 0.5f + 0.5f * (float) Math.sin(gx * 0.017f + gy * 0.023f + time / 1600.0);
                float dx = gx - mx;
                float dy = gy - my;
                float dist = (float) Math.sqrt(dx * dx + dy * dy);
                float inf = dist < reach ? 1.0f - dist / reach : 0.0f;
                inf *= inf;
                // бегущее от курсора кольцо-импульс подсвечивает точки на своём радиусе
                float ring = pulseR > 0.0f && Math.abs(dist - pulseR) < 70.0f
                        ? 1.0f - Math.abs(dist - pulseR) / 70.0f : 0.0f;
                float energy = Math.max(inf, ring * 0.85f);
                gridPX[idx] = gx;
                gridPY[idx] = gy;
                gridE[idx] += (energy - gridE[idx]) * Math.min(1.0f, dt * 9.0f);
                float e = gridE[idx];
                if (e > 0.05f) {
                    float pull = inf * 12.0f;
                    Render2D.circle(gx - dx / Math.max(1.0f, dist) * pull, gy - dy / Math.max(1.0f, dist) * pull,
                            1.1f + wave * 0.6f + e * 2.6f, 10,
                            MenuColors.withAlpha(inf >= ring ? accent : tintA, (int) ((70.0f + 165.0f * e) * a)));
                } else {
                    float size = 1.1f + wave * 0.6f;
                    Render2D.rect(gx - size * 0.5f, gy - size * 0.5f, size, size, size * 0.5f,
                            MenuColors.rgba(255, 255, 255, (int) ((10.0f + 12.0f * wave) * a)));
                }
                idx++;
            }
        }
        // электрические связи между соседними возбуждёнными точками
        for (int i = 0; i < idx; i++) {
            if (gridE[i] < 0.35f) {
                continue;
            }
            if (i % 22 != 21 && i + 1 < idx && gridE[i + 1] > 0.3f) {
                Render2D.line(gridPX[i], gridPY[i], gridPX[i + 1], gridPY[i + 1], 1.0f,
                        MenuColors.withAlpha(tintA, (int) (46.0f * Math.min(gridE[i], gridE[i + 1]) * a)));
            }
            if (i + 22 < idx && gridE[i + 22] > 0.3f) {
                Render2D.line(gridPX[i], gridPY[i], gridPX[i + 22], gridPY[i + 22], 1.0f,
                        MenuColors.withAlpha(tintA, (int) (46.0f * Math.min(gridE[i], gridE[i + 22]) * a)));
            }
        }
    }

    // ——— 3. «Пузыри»: три слоя глубины, ирисковая кромка, цепные лопания ———

    private static final int BUBBLE_COUNT = 26;
    private static final float[] bubX = new float[BUBBLE_COUNT];
    private static final float[] bubY = new float[BUBBLE_COUNT];
    private static final float[] bubR = new float[BUBBLE_COUNT];
    private static final float[] bubSpd = new float[BUBBLE_COUNT];
    private static final boolean[] bubAccent = new boolean[BUBBLE_COUNT];
    private static final int[] bubLayer = new int[BUBBLE_COUNT];
    // 0 = целый, >0 = прогресс лопания (0..1), <0 = задержка цепного лопания
    private static final float[] bubPop = new float[BUBBLE_COUNT];
    private static boolean bubInit;
    private static final java.util.Random BUB_RND = new java.util.Random();

    private static void drawBubblesFull(float sw, float sh, float a) {
        float dt = Math.max(0.005f, currentDt);
        if (!bubInit) {
            for (int i = 0; i < BUBBLE_COUNT; i++) {
                respawnBubble(i, BUB_RND.nextFloat());
                bubPop[i] = 0.0f;
            }
            bubInit = true;
        }

        float mx = mouseX >= 0.0f ? mouseX : -9999.0f;
        float my = mouseY >= 0.0f ? mouseY : -9999.0f;
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;
        int tintA = ThemeManager.mix(accent, 0xFFFFFF, 0.4f);
        float reach = Math.min(sw, sh) * 0.30f;
        long time = System.currentTimeMillis();

        // свет сверху: холодный столб и тёплый бок — источники для бликов
        glowPoint(sw * 0.30f, sh * 0.06f, sh * 0.20f, tintA, 26.0f * a);
        glowPoint(sw * 0.74f, sh * 0.10f, sh * 0.14f, accent, 20.0f * a);

        for (int i = 0; i < BUBBLE_COUNT; i++) {
            float bx = bubX[i] * sw;
            float by = bubY[i] * sh;
            float r = bubR[i] * Math.min(sw, sh);

            // пузырь «всплывает» с покачиванием
            bx += (float) Math.sin(time / (1500.0f + i * 137.0f) + i) * r * 0.22f;

            float off = 0.0f;
            if (bubPop[i] <= 0.0f) {
                float dx = bx - mx;
                float dy = by - my;
                float dist = (float) Math.sqrt(dx * dx + dy * dy);
                off = (1.0f - dist / Math.max(1.0f, reach)) * 30.0f;
                if (off > 0.0f && dist > 0.5f) {
                    bx += dx / dist * off;
                    by += dy / dist * off;
                }
                if (dist < r + 9.0f) {
                    // запускаем цепь: соседи рядом лопнут с задержкой
                    for (int j = 0; j < BUBBLE_COUNT; j++) {
                        if (j != i && bubPop[j] == 0.0f) {
                            float ndx = bubX[j] * sw - bx;
                            float ndy = bubY[j] * sh - by;
                            if (ndx * ndx + ndy * ndy < (r * 3.2f) * (r * 3.2f)) {
                                bubPop[j] = -0.35f;
                            }
                        }
                    }
                    bubPop[i] = 0.001f;
                }
            }

            int col = bubAccent[i] ? accent : 0xFFFFFF;
            float layerMul = bubLayer[i] == 0 ? 0.5f : (bubLayer[i] == 1 ? 0.8f : 1.0f);

            if (bubPop[i] > 0.0f) {
                // лопание: кольцо разлетается, ядро схлопывается, брызги + мини-рябь
                float age = Math.min(1.0f, bubPop[i]);
                float fade = 1.0f - age;
                float ringR = r * (1.0f + age * 1.9f);
                Render2D.arcRect(bx, by, ringR, 1.6f * fade + 0.4f, 0.0f, 360.0f,
                        MenuColors.withAlpha(col, (int) (85.0f * fade * a)));
                Render2D.arcRect(bx, by, ringR * 0.55f, 1.0f, 0.0f, 360.0f,
                        MenuColors.withAlpha(tintA, (int) (40.0f * fade * a)));
                Render2D.circle(bx, by, Math.max(1.0f, r * fade * 0.6f), 14,
                        MenuColors.withAlpha(col, (int) (26.0f * fade * a)));
                for (int d = 0; d < 5; d++) {
                    float dir = d * 1.256f + i * 0.4f;
                    float dd = ringR * (1.1f + 0.35f * age);
                    Render2D.circle(bx + (float) Math.cos(dir) * dd, by + (float) Math.sin(dir) * dd,
                            Math.max(0.7f, 2.4f * fade), 6, MenuColors.withAlpha(col, (int) (58.0f * fade * a)));
                }
                bubPop[i] += dt * 3.4f;
            } else if (bubPop[i] < 0.0f) {
                // ожидание цепного лопания
                bubPop[i] += dt * 3.0f;
            } else {
                // пузырь: кромка + внутренний объём + полумесяц-блик от «света сверху»
                int rim = (int) ((bubAccent[i] ? 58.0f : 44.0f) * layerMul * a);
                Render2D.arcRect(bx, by, r, 1.4f, 0.0f, 360.0f, MenuColors.withAlpha(col, rim));
                Render2D.circle(bx, by, r * 0.55f, 18, MenuColors.withAlpha(col, (int) (9 * a)));
                Render2D.circle(bx, by - r * 0.12f, r * 0.28f, 14, MenuColors.withAlpha(tintA, (int) (7 * a)));
                Render2D.arcRect(bx, by, r * 0.82f, Math.max(0.8f, r * 0.10f), -150.0f, 85.0f,
                        MenuColors.withAlpha(0xFFFFFF, (int) (38 * layerMul * a)));
            }

            float slow = off > 0.0f ? 0.45f : 1.0f;
            bubY[i] -= bubSpd[i] * slow * dt;
            if (bubPop[i] >= 1.0f || bubY[i] * sh < -r * 2.0f) {
                respawnBubble(i, 1.0f + BUB_RND.nextFloat() * 0.08f);
                bubPop[i] = 0.0f;
            }
        }
    }

    private static void respawnBubble(int i, float y) {
        int layer = i < 12 ? 0 : (i < 21 ? 1 : 2);
        bubLayer[i] = layer;
        float rMin = layer == 0 ? 0.010f : (layer == 1 ? 0.018f : 0.030f);
        float rMax = layer == 0 ? 0.020f : (layer == 1 ? 0.034f : 0.055f);
        float sMin = layer == 0 ? 0.03f : (layer == 1 ? 0.06f : 0.10f);
        float sMax = layer == 0 ? 0.06f : (layer == 1 ? 0.10f : 0.16f);
        bubX[i] = BUB_RND.nextFloat();
        bubY[i] = y;
        bubR[i] = rMin + BUB_RND.nextFloat() * (rMax - rMin);
        bubSpd[i] = sMin + BUB_RND.nextFloat() * (sMax - sMin);
        bubAccent[i] = BUB_RND.nextBoolean();
    }

    // ——— 4. «Кометы»: слои звёзд, созвездия, метеоры с гравитацией курсора и живой шлейф ———

    private static final int STREAK_COUNT = 14;
    private static final float[] stkX = new float[STREAK_COUNT];
    private static final float[] stkY = new float[STREAK_COUNT];
    private static final float[] stkSpd = new float[STREAK_COUNT];
    private static final float[] stkLen = new float[STREAK_COUNT];
    private static boolean stkInit;
    private static final float TRAIL_LEN = 22;
    private static final float[] trailX = new float[(int) TRAIL_LEN];
    private static final float[] trailY = new float[(int) TRAIL_LEN];
    private static int trailHead;
    private static final int STAR_COUNT = 44;
    private static final float[] starX = new float[STAR_COUNT];
    private static final float[] starY = new float[STAR_COUNT];
    private static final float[] starPhase = new float[STAR_COUNT];
    // созвездие: неподвижные звёзды, соединённые линиями
    private static final int CNST_COUNT = 8;
    private static final float[] cnstX = new float[CNST_COUNT];
    private static final float[] cnstY = new float[CNST_COUNT];
    private static boolean starInit;
    private static final java.util.Random STK_RND = new java.util.Random();

    private static void drawStreaksFull(float sw, float sh, float a) {
        float dt = Math.max(0.005f, currentDt);
        if (!stkInit) {
            for (int i = 0; i < STREAK_COUNT; i++) {
                stkX[i] = STK_RND.nextFloat() * sw;
                stkY[i] = STK_RND.nextFloat() * sh;
                stkSpd[i] = (100.0f + STK_RND.nextFloat() * 260.0f);
                stkLen[i] = 40.0f + STK_RND.nextFloat() * 120.0f;
            }
            for (int i = 0; i < STAR_COUNT; i++) {
                starX[i] = STK_RND.nextFloat();
                starY[i] = STK_RND.nextFloat();
                starPhase[i] = STK_RND.nextFloat() * 6.28f;
            }
            for (int i = 0; i < CNST_COUNT; i++) {
                cnstX[i] = 0.10f + (i * 0.317f) % 0.84f;
                cnstY[i] = 0.08f + ((i * 7 + 3) * 0.089f) % 0.62f;
            }
            for (int i = 0; i < TRAIL_LEN; i++) {
                trailX[i] = mouseX >= 0.0f ? mouseX : sw * 0.5f;
                trailY[i] = mouseY >= 0.0f ? mouseY : sh * 0.5f;
            }
            stkInit = true;
        }

        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;
        int tintA = ThemeManager.mix(accent, 0xFFFFFF, 0.45f);
        long time = System.currentTimeMillis();

        // звёздная пыль: два слоя rect-звёзд с мерцанием и параллаксом
        for (int i = 0; i < STAR_COUNT; i++) {
            float tw = 0.5f + 0.5f * (float) Math.sin(time / (900.0f + starPhase[i] * 600.0f) + starPhase[i]);
            float depth = i % 3 == 0 ? 0.85f : 0.4f;
            float sx = starX[i] * sw - smoothX * 22.0f * depth;
            float sy = starY[i] * sh * 0.92f - smoothY * 12.0f * depth;
            float s = 0.9f + tw * 0.9f + depth * 0.7f;
            Render2D.rect(sx, sy, s, s, s * 0.5f,
                    i % 5 == 0
                            ? MenuColors.withAlpha(tintA, (int) ((20.0f + 70.0f * tw) * a))
                            : MenuColors.rgba(255, 255, 255, (int) ((14.0f + 52.0f * tw) * a)));
        }

        // созвездие: звёзды-узлы и линии между близкими
        for (int i = 0; i < CNST_COUNT; i++) {
            float nx = cnstX[i] * sw - smoothX * 30.0f;
            float ny = cnstY[i] * sh - smoothY * 16.0f;
            for (int j = i + 1; j < CNST_COUNT; j++) {
                float jx = cnstX[j] * sw - smoothX * 30.0f;
                float jy = cnstY[j] * sh - smoothY * 16.0f;
                float ddx = jx - nx;
                float ddy = jy - ny;
                float dd = (float) Math.sqrt(ddx * ddx + ddy * ddy);
                if (dd < Math.min(sw, sh) * 0.26f) {
                    Render2D.line(nx, ny, jx, jy, 0.8f,
                            MenuColors.withAlpha(accent, (int) (18 * a)));
                }
            }
            float tw = 0.5f + 0.5f * (float) Math.sin(time / 1100.0f + i * 2.4f);
            Render2D.circle(nx, ny, 1.6f + tw * 1.1f, 10,
                    MenuColors.rgba(255, 255, 255, (int) ((60.0f + 80.0f * tw) * a)));
        }

        // шлейф за курсором
        if (mouseX >= 0.0f) {
            float lx = trailX[trailHead];
            float ly = trailY[trailHead];
            float dx = mouseX - lx;
            float dy = mouseY - ly;
            if (dx * dx + dy * dy > 9.0f) {
                trailHead = (trailHead + 1) % (int) TRAIL_LEN;
                trailX[trailHead] = mouseX;
                trailY[trailHead] = mouseY;
            }
            for (int i = 0; i < TRAIL_LEN - 1; i++) {
                int idx0 = (trailHead - i + (int) TRAIL_LEN) % (int) TRAIL_LEN;
                int idx1 = (trailHead - i - 1 + (int) TRAIL_LEN) % (int) TRAIL_LEN;
                float fade = 1.0f - i / TRAIL_LEN;
                Render2D.line(trailX[idx0], trailY[idx0], trailX[idx1], trailY[idx1],
                        3.6f * fade + 0.6f, MenuColors.withAlpha(accent, (int) (95.0f * fade * fade * a)));
            }
            glowPoint(mouseX, mouseY, 22.0f, accent, 40.0f * a);
            // искры вокруг курсора
            for (int s = 0; s < 5; s++) {
                float ang = time / (310.0f + s * 70.0f) + s * 1.9f;
                float sr = 26.0f + 10.0f * (float) Math.sin(time / (400.0f + s * 120.0f) + s);
                Render2D.circle(mouseX + (float) Math.cos(ang) * sr, mouseY + (float) Math.sin(ang) * sr * 0.8f,
                        1.3f, 6, MenuColors.withAlpha(tintA, (int) (60 * a)));
            }
        }

        // метеоры: у курсора гравитация — хвост изгибается к мыши
        float mx = mouseX >= 0.0f ? mouseX : sw * 0.5f;
        float my = mouseY >= 0.0f ? mouseY : sh * 0.5f;
        float mReach = Math.min(sw, sh) * 0.30f;
        for (int i = 0; i < STREAK_COUNT; i++) {
            float speed = stkSpd[i];
            stkX[i] += speed * 0.86f * dt;
            stkY[i] += speed * 0.5f * dt;
            if (stkX[i] - stkLen[i] > sw || stkY[i] - stkLen[i] > sh) {
                stkX[i] = -stkLen[i] * STK_RND.nextFloat();
                stkY[i] = STK_RND.nextFloat() * sh * 0.7f;
                stkSpd[i] = 100.0f + STK_RND.nextFloat() * 260.0f;
                stkLen[i] = 40.0f + STK_RND.nextFloat() * 120.0f;
            }
            float hx = stkX[i];
            float hy = stkY[i];
            // гравитационный изгиб головы к курсору
            float gdx = mx - hx;
            float gdy = my - hy;
            float gd = (float) Math.sqrt(gdx * gdx + gdy * gdy);
            float pull = gd < mReach ? (1.0f - gd / mReach) : 0.0f;
            pull *= pull;
            if (pull > 0.0f && gd > 0.5f) {
                hx += gdx / gd * pull * 26.0f;
                hy += gdy / gd * pull * 26.0f;
            }
            float baseAlpha = 0.35f + 0.5f * (stkSpd[i] / 360.0f);
            float lx = hx;
            float ly = hy;
            for (int s = 0; s < 4; s++) {
                float seg = stkLen[i] / 4.0f;
                float segFade = baseAlpha * (1.0f - s * 0.22f);
                int lineCol = i % 4 == 0
                        ? MenuColors.withAlpha(0xFFFFFF, (int) (segFade * 150.0f * a))
                        : MenuColors.withAlpha(accent, (int) (segFade * 175.0f * a));
                float ex = lx - seg;
                float ey = ly - seg * 0.58f;
                Render2D.line(lx, ly, ex, ey, 1.6f - s * 0.28f, lineCol);
                lx = ex;
                ly = ey;
            }
            // яркая голова
            if (i < 5) {
                glowPoint(hx, hy, 9.0f, accent, 70.0f * baseAlpha * a);
            }
            Render2D.circle(hx, hy, 1.8f, 8, MenuColors.rgba(255, 255, 255, (int) (200 * baseAlpha * a)));
        }
    }

    // ——— 5. «Рябь»: водная гладь — кольца волнуют поверхность из точек, капли падают с неба ———

    private static final int RIPPLE_MAX = 16;
    private static final float[] ripX = new float[RIPPLE_MAX];
    private static final float[] ripY = new float[RIPPLE_MAX];
    private static final float[] ripAge = new float[RIPPLE_MAX];
    private static final boolean[] ripAlive = new boolean[RIPPLE_MAX];
    private static int ripCursor = 0;
    private static float lastRipX = -1.0f;
    private static float lastRipY = -1.0f;
    private static long lastRipSpawn;
    // падающие капли: рождают всплеск и кольцо при касании воды
    private static final int DROP_MAX = 5;
    private static final float[] dropX = new float[DROP_MAX];
    private static final float[] dropY = new float[DROP_MAX];
    private static final float[] dropSpd = new float[DROP_MAX];
    private static final float[] dropLandY = new float[DROP_MAX];
    private static final boolean[] dropAlive = new boolean[DROP_MAX];
    private static long nextDropAt;
    private static final java.util.Random RIP_RND = new java.util.Random();

    private static void drawRipplesFull(float sw, float sh, float a) {
        float dt = Math.max(0.005f, currentDt);
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;
        int tintA = ThemeManager.mix(accent, 0xFFFFFF, 0.4f);
        long time = System.currentTimeMillis();

        // световая дорожка на воде от «луны»
        float moonX = sw * 0.68f - smoothX * 30.0f;
        glowPoint(moonX, sh * 0.10f, sh * 0.13f, tintA, 40.0f * a);
        Render2D.rect(moonX - 14.0f, sh * 0.52f, 28.0f, sh * 0.46f, 0.0f,
                MenuColors.withAlpha(tintA, 0), MenuColors.withAlpha(tintA, 0),
                MenuColors.withAlpha(tintA, (int) (26 * a)), MenuColors.withAlpha(tintA, (int) (14 * a)));

        // спавн от движения мыши: порог по дистанции и времени
        if (mouseX >= 0.0f) {
            float dx = mouseX - lastRipX;
            float dy = mouseY - lastRipY;
            if (lastRipX < 0.0f || (dx * dx + dy * dy > 4900.0f && time - lastRipSpawn > 140L)) {
                spawnRipple(mouseX, mouseY);
                lastRipX = mouseX;
                lastRipY = mouseY;
                lastRipSpawn = time;
            }
            // линза: под курсором вода собирает свет
            float breathe = 0.5f + 0.5f * (float) Math.sin(time / 1300.0);
            Render2D.arcRect(mouseX, mouseY, 20.0f + 26.0f * breathe, 1.6f, 0.0f, 360.0f,
                    MenuColors.withAlpha(accent, (int) ((26.0f + 34.0f * breathe) * a)));
            glowPoint(mouseX, mouseY, 24.0f, accent, 30.0f * a);
        }
        // авто-пульс, чтобы фон жил и без мыши
        if (time % 2100L < 16L) {
            spawnRipple(RIP_RND.nextFloat() * sw, sh * (0.55f + RIP_RND.nextFloat() * 0.4f));
        }

        // капли с неба: полоска падает, при касании воды — всплеск и кольцо
        if (time >= nextDropAt) {
            int di = -1;
            for (int i = 0; i < DROP_MAX; i++) {
                if (!dropAlive[i]) {
                    di = i;
                    break;
                }
            }
            if (di >= 0) {
                dropAlive[di] = true;
                dropX[di] = RIP_RND.nextFloat() * sw;
                dropY[di] = -20.0f;
                dropSpd[di] = sh * (0.9f + RIP_RND.nextFloat() * 0.7f);
                dropLandY[di] = sh * (0.56f + RIP_RND.nextFloat() * 0.4f);
            }
            nextDropAt = time + 500L + RIP_RND.nextInt(1200);
        }
        for (int i = 0; i < DROP_MAX; i++) {
            if (!dropAlive[i]) {
                continue;
            }
            dropY[i] += dropSpd[i] * dt;
            if (dropY[i] >= dropLandY[i]) {
                dropAlive[i] = false;
                spawnRipple(dropX[i], dropLandY[i]);
                // брызги вверх
                for (int d = 0; d < 3; d++) {
                    float dir = -1.35f + d * 0.5f;
                    Render2D.circle(dropX[i] + (float) Math.cos(dir) * 8.0f,
                            dropLandY[i] + (float) Math.sin(dir) * 8.0f - 4.0f,
                            1.4f, 6, MenuColors.withAlpha(tintA, (int) (90 * a)));
                }
                continue;
            }
            Render2D.line(dropX[i], dropY[i], dropX[i] - 1.5f, dropY[i] - 14.0f, 1.2f,
                    MenuColors.rgba(255, 255, 255, (int) (60 * a)));
        }

        // кольца: три дуги с разной толщиной + свежее ядро светится
        for (int i = 0; i < RIPPLE_MAX; i++) {
            if (!ripAlive[i]) {
                continue;
            }
            ripAge[i] += dt * 0.55f;
            if (ripAge[i] >= 1.0f) {
                ripAlive[i] = false;
                continue;
            }
            float age = ripAge[i];
            float fade = (1.0f - age) * (1.0f - age);
            float radius = age * Math.min(sw, sh) * 0.42f + 6.0f;
            Render2D.arcRect(ripX[i], ripY[i], radius, 2.0f * (1.0f - age * 0.5f), 0.0f, 360.0f,
                    MenuColors.withAlpha(accent, (int) (64.0f * fade * a)));
            Render2D.arcRect(ripX[i], ripY[i], radius * 0.6f, 1.2f, 0.0f, 360.0f,
                    MenuColors.withAlpha(accent, (int) (30.0f * fade * a)));
            Render2D.arcRect(ripX[i], ripY[i], radius * 1.22f, 1.0f, 0.0f, 360.0f,
                    MenuColors.withAlpha(tintA, (int) (18.0f * fade * a)));
            if (age < 0.22f) {
                glowPoint(ripX[i], ripY[i], 14.0f, tintA, 60.0f * fade * a);
            }
        }

        // поверхность воды: сетка точек, которые качает сумма всех волн
        float step = Math.max(50.0f, sw / 34.0f);
        for (float gy = sh * 0.54f; gy < sh; gy += step) {
            for (float gx = step * 0.5f; gx < sw; gx += step) {
                float wave = 0.0f;
                for (int i = 0; i < RIPPLE_MAX; i++) {
                    if (!ripAlive[i]) {
                        continue;
                    }
                    float dx = gx - ripX[i];
                    float dy = (gy - ripY[i]) * 1.9f; // перспектива: вода сжата по вертикали
                    float d = (float) Math.sqrt(dx * dx + dy * dy);
                    float front = ripAge[i] * Math.min(sw, sh) * 0.42f + 6.0f;
                    float band = (d - front) / 55.0f;
                    if (band > -1.2f && band < 1.2f) {
                        wave += (float) Math.exp(-band * band * 2.2f)
                                * (float) Math.sin(d * 0.05 - time / 260.0)
                                * (1.0f - ripAge[i]);
                    }
                }
                if (wave > -0.02f && wave < 0.02f) {
                    continue;
                }
                float lift = wave * 7.0f;
                float bright = Math.min(1.0f, Math.abs(wave) * 2.4f);
                int alpha = (int) ((8.0f + 46.0f * bright) * a);
                int col = wave > 0.0f ? tintA : 0xFFFFFF;
                Render2D.rect(gx - 1.1f, gy + lift - 1.1f, 2.2f, 2.2f, 1.1f,
                        MenuColors.withAlpha(col, alpha));
            }
        }
    }

    private static void spawnRipple(float x, float y) {
        int idx = ripCursor % RIPPLE_MAX;
        ripCursor++;
        ripX[idx] = x;
        ripY[idx] = y;
        ripAge[idx] = 0.0f;
        ripAlive[idx] = true;
    }

    // ——— 6. «Молния»: грозовое поле, ветвистые разряды к курсору, ударная волна и вспышки ———

    private static float boltAge = 2.0f;
    private static long nextBoltAt = 0L;
    private static final int BOLT_PTS = 9;
    private static final float[] boltX = new float[BOLT_PTS];
    private static final float[] boltY = new float[BOLT_PTS];
    // две ветки: [ветка][точка]
    private static final int BR_PTS = 3;
    private static final float[][] branchX = new float[2][BR_PTS];
    private static final float[][] branchY = new float[2][BR_PTS];
    private static float shockAge = 2.0f;
    private static float shockX;
    private static float shockY;
    private static final java.util.Random STM_RND = new java.util.Random();

    private static void drawStormFull(float sw, float sh, float a) {
        float dt = Math.max(0.005f, currentDt);
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;
        int cloudCol = ThemeManager.mix(accent, 0x39424F, 0.55f);
        long time = System.currentTimeMillis();

        // грозовой фронт: тяжёлые тучи с внутренним свечением
        float flash = boltAge < 1.0f ? (1.0f - boltAge) * (1.0f - boltAge) : 0.0f;
        for (int i = 0; i < 6; i++) {
            float clx = sw * (0.10f + 0.16f * i) + (float) Math.sin(time / (5200.0f + i * 900.0f)) * sw * 0.02f;
            float cly = sh * (0.05f + 0.035f * (i % 2)) + (float) Math.sin(time / (4100.0f + i * 700.0f)) * sh * 0.02f;
            float clr = sh * (0.16f + 0.045f * (i % 3));
            glowPoint(clx, cly, clr, cloudCol, (30.0f + 46.0f * flash) * a);
        }
        // мгновенная подсветка туч при разряде
        if (flash > 0.01f) {
            glowPoint(sw * 0.5f, sh * 0.04f, sh * 0.5f, 0xFFFFFF, 70.0f * flash * a);
        }

        // мелкий дождь-предвестник
        for (int i = 0; i < 10; i++) {
            float phase = (time / (2400.0f + i * 190.0f) + i * 0.161f) % 1.2f;
            float rdx = sw * ((i * 61 % 97) / 97.0f) - smoothX * 10.0f;
            float rdy = sh * phase;
            Render2D.line(rdx, rdy, rdx - 2.0f, rdy - sh * 0.045f, 0.9f,
                    MenuColors.rgba(255, 255, 255, (int) (16 * a)));
        }

        float tx = mouseX >= 0.0f ? mouseX : sw * (0.45f + 0.15f * (float) Math.sin(time / 3100.0));
        float ty = mouseY >= 0.0f ? mouseY : sh * 0.62f;

        if (nextBoltAt == 0L) {
            nextBoltAt = time + 900L;
        }
        if (time >= nextBoltAt) {
            // рваный путь от тучи к точке возле курсора; курсор притягивает разряды
            float sx = sw * (0.2f + STM_RND.nextFloat() * 0.6f);
            float sy = sh * 0.10f;
            float ix = tx + (STM_RND.nextFloat() - 0.5f) * sw * 0.10f;
            float iy = ty + (STM_RND.nextFloat() - 0.5f) * sh * 0.06f;
            for (int p = 0; p < BOLT_PTS; p++) {
                float t = p / (float) (BOLT_PTS - 1);
                boltX[p] = sx + (ix - sx) * t
                        + (p > 0 && p < BOLT_PTS - 1 ? (STM_RND.nextFloat() - 0.5f) * sw * 0.055f : 0.0f);
                boltY[p] = sy + (iy - sy) * t
                        + (p > 0 && p < BOLT_PTS - 1 ? (STM_RND.nextFloat() - 0.5f) * sh * 0.035f : 0.0f);
            }
            // ветки отходят от случайных точек основного канала
            for (int b = 0; b < 2; b++) {
                int anchor = 2 + STM_RND.nextInt(BOLT_PTS - 4);
                float dirX = (b == 0 ? -1.0f : 1.0f) * sw * 0.05f;
                float dirY = sh * 0.06f;
                branchX[b][0] = boltX[anchor];
                branchY[b][0] = boltY[anchor];
                for (int p = 1; p < BR_PTS; p++) {
                    branchX[b][p] = branchX[b][p - 1] + dirX / (BR_PTS - 1)
                            + (STM_RND.nextFloat() - 0.5f) * sw * 0.02f;
                    branchY[b][p] = branchY[b][p - 1] + dirY / (BR_PTS - 1)
                            + (STM_RND.nextFloat() - 0.5f) * sh * 0.02f;
                }
            }
            boltAge = 0.0f;
            shockAge = 0.0f;
            shockX = boltX[BOLT_PTS - 1];
            shockY = boltY[BOLT_PTS - 1];
            nextBoltAt = time + 1500L + STM_RND.nextInt(2400);
        }

        boltAge += dt * 4.2f;
        if (boltAge < 1.0f) {
            float p = 1.0f - boltAge;
            float fade = p * p;
            // основной канал: белая жила в акцентной оболочке
            for (int pnt = 0; pnt < BOLT_PTS - 1; pnt++) {
                float segFade = fade * (1.0f - pnt / (float) BOLT_PTS * 0.45f);
                Render2D.line(boltX[pnt], boltY[pnt], boltX[pnt + 1], boltY[pnt + 1],
                        2.6f - pnt * 0.16f, MenuColors.rgba(255, 255, 255, (int) (235.0f * segFade * a)));
                Render2D.line(boltX[pnt], boltY[pnt], boltX[pnt + 1], boltY[pnt + 1],
                        4.4f - pnt * 0.28f, MenuColors.withAlpha(accent, (int) (80.0f * segFade * a)));
            }
            // ветки
            for (int b = 0; b < 2; b++) {
                for (int pnt = 0; pnt < BR_PTS - 1; pnt++) {
                    float segFade = fade * 0.6f * (1.0f - pnt * 0.3f);
                    Render2D.line(branchX[b][pnt], branchY[b][pnt], branchX[b][pnt + 1], branchY[b][pnt + 1],
                            1.2f - pnt * 0.3f, MenuColors.rgba(255, 255, 255, (int) (150.0f * segFade * a)));
                }
            }
            // вспышка на месте удара и общая подсветка экрана
            glowPoint(boltX[BOLT_PTS - 1], boltY[BOLT_PTS - 1], sh * 0.16f, accent, 90.0f * fade * a);
            Render2D.rect(0.0f, 0.0f, sw, sh, 0.0f,
                    MenuColors.rgba(255, 255, 255, (int) (16.0f * fade * a)),
                    MenuColors.rgba(255, 255, 255, (int) (10.0f * fade * a)),
                    MenuColors.rgba(255, 255, 255, (int) (5.0f * fade * a)),
                    MenuColors.rgba(255, 255, 255, (int) (0 * a)));
        }

        // ударная волна от точки удара
        shockAge += dt * 1.6f;
        if (shockAge < 1.0f) {
            float sr = shockAge * Math.min(sw, sh) * 0.35f + 8.0f;
            Render2D.arcRect(shockX, shockY, sr, 1.8f * (1.0f - shockAge) + 0.5f, 0.0f, 360.0f,
                    MenuColors.withAlpha(accent, (int) (50.0f * (1.0f - shockAge) * (1.0f - shockAge) * a)));
        }
    }

    // ——— 7. «Орбиты»: звезда с короной, планеты со следами, комета и гравитация курсора ———

    private static final int ORB_COUNT = 12;
    private static final float[] orbAngle = new float[ORB_COUNT];
    private static final float[] orbSpd = new float[ORB_COUNT];
    private static final float[] orbRing = new float[ORB_COUNT];
    private static float cometAngle = 0.0f;
    private static boolean orbInit;

    private static void drawOrbitFull(float sw, float sh, float a) {
        float dt = Math.max(0.005f, currentDt);
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;
        int tintA = ThemeManager.mix(accent, 0xFFFFFF, 0.4f);
        if (!orbInit) {
            for (int i = 0; i < ORB_COUNT; i++) {
                orbAngle[i] = STK_RND.nextFloat() * 6.2832f;
                orbSpd[i] = (0.22f + STK_RND.nextFloat() * 0.5f) * (STK_RND.nextBoolean() ? 1.0f : -1.0f);
                orbRing[i] = i / (float) ORB_COUNT * 2.59f;
            }
            orbInit = true;
        }

        float cx = sw * 0.5f;
        float cy = sh * 0.56f;
        float mx = mouseX >= 0.0f ? mouseX : sw * 0.5f;
        float my = mouseY >= 0.0f ? mouseY : sh * 0.56f;
        float reach = Math.min(sw, sh) * 0.34f;
        float base = Math.min(sw, sh);

        // звезда: ядро, пульсирующая корона, вращающиеся сектора и горизонтальный блик
        long time = System.currentTimeMillis();
        float pulse = 0.5f + 0.5f * (float) Math.sin(time / 1600.0);
        glowPoint(cx, cy, base * 0.085f, tintA, 120.0f * a);
        Render2D.circle(cx, cy, 4.0f, 12, MenuColors.withAlpha(0xFFFFFF, (int) (230 * a)));
        float coronaDir = time / 2600.0f;
        Render2D.arcRect(cx, cy, 10.0f + 6.0f * pulse, 1.4f, coronaDir, 70.0f,
                MenuColors.withAlpha(accent, (int) ((40.0f + 50.0f * pulse) * a)));
        Render2D.arcRect(cx, cy, 13.5f + 6.0f * pulse, 1.0f, coronaDir + 3.6f, 55.0f,
                MenuColors.withAlpha(tintA, (int) ((24.0f + 34.0f * pulse) * a)));
        Render2D.rect(cx - base * 0.16f, cy - 0.6f, base * 0.32f, 1.2f, 0.6f,
                MenuColors.withAlpha(tintA, (int) ((16.0f + 22.0f * pulse) * a)));

        for (int i = 0; i < ORB_COUNT; i++) {
            float ringR = base * (0.14f + 0.09f * orbRing[i]);
            float angle = orbAngle[i];
            float px = cx + (float) Math.cos(angle) * ringR;
            float py = cy + (float) Math.sin(angle) * ringR * 0.92f;
            float dx = mx - px;
            float dy = my - py;
            float dist = (float) Math.sqrt(dx * dx + dy * dy);
            float pull = Math.max(0.0f, 1.0f - dist / reach);
            pull *= pull;
            if (dist > 0.5f) {
                px += dx * pull * 0.30f;
                py += dy * pull * 0.30f;
            }
            // след: три точки по дуге позади
            for (int s = 1; s <= 3; s++) {
                float ta = angle - orbSpd[i] * 0.14f * s;
                float bx = cx + (float) Math.cos(ta) * ringR;
                float by = cy + (float) Math.sin(ta) * ringR * 0.92f;
                if (dist > 0.5f) {
                    float bdx = mx - bx;
                    float bdy = my - by;
                    float bd = (float) Math.sqrt(bdx * bdx + bdy * bdy);
                    float bpull = bd < reach ? 1.0f - bd / reach : 0.0f;
                    bpull *= bpull;
                    bx += bdx * bpull * 0.30f;
                    by += bdy * bpull * 0.30f;
                }
                Render2D.circle(bx, by, Math.max(0.8f, 1.7f - s * 0.35f), 8,
                        MenuColors.withAlpha(accent, (int) ((16.0f + 30.0f * pull) * a / s)));
            }
            int col = i % 4 == 0 ? tintA : accent;
            float size = 2.0f + 1.6f * pull;
            Render2D.circle(px, py, size, 10, MenuColors.withAlpha(col, (int) ((70.0f + 150.0f * pull) * a)));
            Render2D.circle(px - size * 0.25f, py - size * 0.25f, size * 0.35f, 6,
                    MenuColors.withAlpha(0xFFFFFF, (int) (60 * a)));
            // искры при близком пролёте мимо курсора
            if (pull > 0.55f) {
                float sparkAng = time / 200.0f + i;
                Render2D.circle(px + (float) Math.cos(sparkAng) * (size + 4.0f),
                        py + (float) Math.sin(sparkAng) * (size + 4.0f), 1.2f, 6,
                        MenuColors.withAlpha(tintA, (int) (110 * a)));
            }

            orbAngle[i] += orbSpd[i] * dt * (pull > 0.15f ? 0.45f : 1.0f);
        }

        // комета: вытянутая орбита с длинным хвостом
        cometAngle += dt * 0.55f;
        float comR = base * 0.46f;
        float comX = cx + (float) Math.cos(cometAngle) * comR * 1.25f;
        float comY = cy + (float) Math.sin(cometAngle) * comR * 0.55f;
        fadeTail(comX, comY,
                cx + (float) Math.cos(cometAngle - 0.55f) * comR * 1.25f,
                cy + (float) Math.sin(cometAngle - 0.55f) * comR * 0.55f,
                2.0f, tintA, 90.0f * a, 5);
        Render2D.circle(comX, comY, 2.2f, 8, MenuColors.rgba(255, 255, 255, (int) (200 * a)));

        // бледные орбитальные кольца
        for (int j = 0; j < 3; j++) {
            float ringR = base * (0.14f + 0.27f * j);
            Render2D.arcRect(cx, cy, ringR, 1.0f, 0.0f, 360.0f,
                    MenuColors.rgba(255, 255, 255, (int) (12 * a)));
        }
    }

    // ——— 8. «Портал»: аккреционный диск, хроматическая кромка, засасывание частиц с краёв ———

    private static float portalPhase = 0.0f;
    private static float portalCX = -1.0f;
    private static float portalCY = -1.0f;
    private static final int PORTAL_INFLOW = 10;
    private static final float[] inflowAng = new float[PORTAL_INFLOW];
    private static final float[] inflowRad = new float[PORTAL_INFLOW];
    private static boolean inflowInit;
    private static float portalTick;

    private static void drawPortalFull(float sw, float sh, float a) {
        float dt = Math.max(0.005f, currentDt);
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;
        int tintA = ThemeManager.mix(accent, 0xFFFFFF, 0.45f);
        if (portalCX < 0.0f) {
            portalCX = sw * 0.5f;
            portalCY = sh * 0.55f;
        }
        if (!inflowInit) {
            for (int i = 0; i < PORTAL_INFLOW; i++) {
                inflowAng[i] = STK_RND.nextFloat() * 6.2832f;
                inflowRad[i] = 0.35f + STK_RND.nextFloat() * 0.65f;
            }
            inflowInit = true;
        }

        float tx = mouseX >= 0.0f ? mouseX : sw * 0.5f;
        float ty = mouseY >= 0.0f ? mouseY : sh * 0.55f;
        portalCX += (tx - portalCX) * Math.min(1.0f, dt * 0.9f);
        portalCY += (ty - portalCY) * Math.min(1.0f, dt * 0.9f);

        // раскрутка рядом с курсором
        float dCenter = (float) Math.sqrt((mouseX - portalCX) * (mouseX - portalCX) + (mouseY - portalCY) * (mouseY - portalCY));
        float inf = mouseX >= 0.0f ? Math.max(0.0f, 1.0f - dCenter / (Math.min(sw, sh) * 0.4f)) : 0.25f;
        float speed = 0.30f + inf * 1.6f;
        portalPhase += dt * speed;
        portalTick += dt * speed;

        // дыра: тёмный провал, хроматическая двойная кромка и внешнее гало
        float holeR = sh * 0.115f;
        glowPoint(portalCX, portalCY, holeR * 1.9f, accent, (14.0f + 30.0f * inf) * a);
        Render2D.circle(portalCX, portalCY, holeR, 36, MenuColors.rgba(4, 5, 8, (int) (235 * a)));
        Render2D.arcRect(portalCX, portalCY, holeR, 1.6f, 0.0f, 360.0f,
                MenuColors.withAlpha(accent, (int) ((40.0f + 55.0f * inf) * a)));
        Render2D.arcRect(portalCX, portalCY, holeR * 0.84f, 1.0f, 0.0f, 360.0f,
                MenuColors.withAlpha(tintA, (int) ((20.0f + 40.0f * inf) * a)));

        // линзовый блик: горизонтальная нить и короткая вертикаль через центр
        float flare = 0.5f + 0.5f * (float) Math.sin(time() * 900.0f);
        Render2D.rect(portalCX - holeR * 3.0f, portalCY - 0.7f, holeR * 6.0f, 1.4f, 0.7f,
                MenuColors.withAlpha(tintA, 0),
                MenuColors.withAlpha(0xFFFFFF, (int) ((26.0f + 30.0f * flare + 40.0f * inf) * a)),
                MenuColors.withAlpha(0xFFFFFF, (int) ((26.0f + 30.0f * flare + 40.0f * inf) * a)),
                MenuColors.withAlpha(tintA, 0));
        Render2D.rect(portalCX - 0.7f, portalCY - holeR * 1.5f, 1.4f, holeR * 3.0f, 0.7f,
                MenuColors.withAlpha(tintA, 0), MenuColors.withAlpha(tintA, (int) ((20.0f + 30.0f * inf) * a)),
                MenuColors.withAlpha(tintA, (int) ((20.0f + 30.0f * inf) * a)), MenuColors.withAlpha(tintA, 0));

        // три рукава спирали; у каждого свой оттенок
        for (int arm = 0; arm < 3; arm++) {
            int armCol = arm == 0 ? tintA : (arm == 1 ? accent : 0xFFFFFF);
            for (int k = 0; k < 18; k++) {
                float t = (portalPhase * 0.5f + arm / 3.0f + k * 0.055f) % 1.0f;
                float r = sh * (0.06f + 0.34f * (1.0f - t));
                float angle = t * 9.42f + arm * 2.094f + portalPhase * 1.2f;
                float dx2 = portalCX + (float) Math.cos(angle) * r;
                float dy2 = portalCY + (float) Math.sin(angle) * r * 0.96f;
                float fade = (1.0f - t) * (0.35f + 0.65f * (k / 18.0f));
                Render2D.circle(dx2, dy2, 1.3f + 1.9f * fade, 8,
                        MenuColors.withAlpha(armCol, (int) ((22.0f + 130.0f * fade) * a)));
            }
        }

        // засасывание: частицы с краёв экрана летят к порталу по спирали
        for (int i = 0; i < PORTAL_INFLOW; i++) {
            inflowRad[i] -= dt * (0.16f + 0.30f * inf) * (0.4f + inflowRad[i]);
            if (inflowRad[i] < 0.07f) {
                inflowRad[i] = 1.05f;
                inflowAng[i] = STK_RND.nextFloat() * 6.2832f;
            }
            float ang = inflowAng[i] + (1.05f - inflowRad[i]) * 3.4f;
            float r = Math.max(sw, sh) * 0.55f * inflowRad[i];
            float ix2 = portalCX + (float) Math.cos(ang) * r;
            float iy2 = portalCY + (float) Math.sin(ang) * r * 0.9f;
            float tailAng = ang + 0.35f;
            float tr = Math.max(sw, sh) * 0.55f * Math.min(1.05f, inflowRad[i] + 0.09f);
            Render2D.line(portalCX + (float) Math.cos(tailAng) * tr, portalCY + (float) Math.sin(tailAng) * tr * 0.9f,
                    ix2, iy2, 1.0f, MenuColors.withAlpha(accent, (int) ((20.0f + 50.0f * (1.0f - inflowRad[i])) * a)));
            if (inflowRad[i] < 0.35f) {
                Render2D.circle(ix2, iy2, 1.4f, 8, MenuColors.withAlpha(tintA, (int) (110 * a)));
            }
        }

        // «тик»: при быстрой раскрутке от кромки расходятся кольца
        if (portalTick > 1.35f) {
            portalTick = 0.0f;
        }
        if (inf > 0.2f && portalTick > 0.9f) {
            float tk = (portalTick - 0.9f) / 0.45f;
            Render2D.arcRect(portalCX, portalCY, holeR + tk * sh * 0.20f, 1.4f * (1.0f - tk) + 0.5f, 0.0f, 360.0f,
                    MenuColors.withAlpha(tintA, (int) (40.0f * (1.0f - tk) * inf * a)));
        }
    }

    private static long time() {
        return System.currentTimeMillis();
    }

    // ——— 9. «Ливень»: три слоя капель, порывы ветра, брызги с физикой, лужи с отражением ———

    private static final int RAIN_COUNT = 30;
    private static final float[] raX = new float[RAIN_COUNT];
    private static final float[] raY = new float[RAIN_COUNT];
    private static final float[] raSpd = new float[RAIN_COUNT];
    private static final float[] raLen = new float[RAIN_COUNT];
    private static boolean raInit;
    private static final int SPLASH_MAX = 10;
    private static final float[] splX = new float[SPLASH_MAX];
    private static final float[] splAge = new float[SPLASH_MAX];
    private static final boolean[] splAlive = new boolean[SPLASH_MAX];
    private static int splCursor = 0;
    // отлетающие капли брызг с гравитацией
    private static final int MIST_MAX = 14;
    private static final float[] mistX = new float[MIST_MAX];
    private static final float[] mistY = new float[MIST_MAX];
    private static final float[] mistVX = new float[MIST_MAX];
    private static final float[] mistVY = new float[MIST_MAX];
    private static final boolean[] mistAlive = new boolean[MIST_MAX];
    private static int mistCursor = 0;
    private static final java.util.Random RAIN_RND = new java.util.Random();

    private static void drawRainFull(float sw, float sh, float a) {
        float dt = Math.max(0.005f, currentDt);
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;
        int tintA = ThemeManager.mix(accent, 0xFFFFFF, 0.5f);
        if (!raInit) {
            for (int i = 0; i < RAIN_COUNT; i++) {
                raX[i] = RAIN_RND.nextFloat();
                raY[i] = RAIN_RND.nextFloat();
                raSpd[i] = 0.55f + RAIN_RND.nextFloat() * 0.65f;
                raLen[i] = 0.05f + RAIN_RND.nextFloat() * 0.075f;
            }
            raInit = true;
        }

        // порывы ветра: плавная волна поверх параллакса мыши
        float gust = (float) Math.sin(System.currentTimeMillis() / 2900.0) * 0.35f
                + (float) Math.sin(System.currentTimeMillis() / 1100.0) * 0.12f;
        float wind = smoothX * 0.55f + gust;
        float mx = mouseX >= 0.0f ? mouseX : -9999.0f;
        float my = mouseY >= 0.0f ? mouseY : -9999.0f;
        float baseline = sh * 0.94f;
        float reach = 110.0f;

        for (int i = 0; i < RAIN_COUNT; i++) {
            // слой: 0..13 дальние, 14..23 средние, 24..29 ближние
            int layer = i < 14 ? 0 : (i < 24 ? 1 : 2);
            float layerMul = layer == 0 ? 0.55f : (layer == 1 ? 0.85f : 1.15f);
            float px = raX[i] * sw;
            float py = raY[i] * sh;
            // наклон капли: ближние сильнее реагируют на ветер
            float dx = wind * 26.0f * (0.5f + 0.5f * layerMul);
            float dy = raLen[i] * sh * layerMul;

            // курсор — «зонтик»: раздвигает капли
            float ddx = px - mx;
            float ddy = py - my;
            float dist = (float) Math.sqrt(ddx * ddx + ddy * ddy);
            float push = (1.0f - dist / reach) * 24.0f;
            if (push > 0.0f && dist > 0.5f) {
                px += ddx / dist * push;
                py += ddy / dist * push;
            }

            int col = layer == 2 && i % 3 == 0 ? accent : (layer == 0 ? 0xFFFFFF : tintA);
            float alpha = (layer == 0 ? 18.0f : layer == 1 ? 26.0f : 36.0f)
                    + (push > 0.0f ? 30.0f * (1.0f - dist / reach) : 10.0f);
            Render2D.line(px, py, px - dx * raLen[i] * 3.2f, py - dy, layer == 2 ? 1.6f : 1.1f,
                    MenuColors.withAlpha(col, (int) (alpha * a)));

            raY[i] += raSpd[i] * layerMul * dt * 1.15f;
            if (raY[i] * sh > baseline) {
                if (layer != 0) {
                    int sIdx = splCursor++ % SPLASH_MAX;
                    splAlive[sIdx] = true;
                    splAge[sIdx] = 0.0f;
                    splX[sIdx] = px;
                    // брызги-капельки с гравитацией
                    int drops = layer == 2 ? 3 : 2;
                    for (int d = 0; d < drops; d++) {
                        int mIdx = mistCursor++ % MIST_MAX;
                        mistAlive[mIdx] = true;
                        mistX[mIdx] = px;
                        mistY[mIdx] = baseline - 2.0f;
                        mistVX[mIdx] = (RAIN_RND.nextFloat() - 0.5f) * 90.0f;
                        mistVY[mIdx] = -(60.0f + RAIN_RND.nextFloat() * 90.0f);
                    }
                }
                raY[i] = -0.05f - RAIN_RND.nextFloat() * 0.15f;
                raX[i] = RAIN_RND.nextFloat();
                raSpd[i] = 0.55f + RAIN_RND.nextFloat() * 0.65f;
                raLen[i] = 0.05f + RAIN_RND.nextFloat() * 0.075f;
            }
        }

        // линия земли и мерцающие лужи
        Render2D.rect(0.0f, baseline, sw, 1.0f, 0.5f, 0.5f, 0.5f, 0.5f,
                MenuColors.rgba(255, 255, 255, (int) (10 * a)));
        for (int i = 0; i < 4; i++) {
            float plX = sw * (0.14f + 0.24f * i);
            float plW = sw * (0.10f + 0.05f * (i % 2));
            float flick = 0.5f + 0.5f * (float) Math.sin(System.currentTimeMillis() / (1700.0f + i * 500.0f) + i * 2.1f);
            Render2D.rect(plX, baseline - 1.0f, plW, 3.0f, 1.5f, 1.5f, 3.0f, 3.0f,
                    MenuColors.withAlpha(tintA, (int) ((14.0f + 16.0f * flick) * a)));
        }
        // отражение курсора в воде
        if (mx > -999.0f && my < baseline) {
            float reflectW = 20.0f;
            float reflectH = Math.min(60.0f, baseline - my) * 0.8f;
            Render2D.rect(mx - reflectW * 0.5f, baseline - reflectH, reflectW, reflectH, reflectW * 0.5f,
                    MenuColors.withAlpha(tintA, 0), MenuColors.withAlpha(tintA, 0),
                    MenuColors.withAlpha(tintA, (int) (26 * a)), MenuColors.withAlpha(tintA, (int) (34 * a)));
        }

        // кольца-всплески
        for (int i = 0; i < SPLASH_MAX; i++) {
            if (!splAlive[i]) {
                continue;
            }
            splAge[i] += dt * 2.2f;
            if (splAge[i] >= 1.0f) {
                splAlive[i] = false;
                continue;
            }
            float ag = splAge[i];
            float fade = 1.0f - ag;
            Render2D.arcRect(splX[i], baseline - 1.0f, 2.0f + ag * 14.0f, 1.1f, 200.0f, 140.0f,
                    MenuColors.withAlpha(accent, (int) (36.0f * fade * a)));
        }
        // капельки брызг с гравитацией
        for (int i = 0; i < MIST_MAX; i++) {
            if (!mistAlive[i]) {
                continue;
            }
            mistVY[i] += 640.0f * dt;
            mistX[i] += mistVX[i] * dt;
            mistY[i] += mistVY[i] * dt;
            if (mistY[i] > baseline + 2.0f) {
                mistAlive[i] = false;
                continue;
            }
            Render2D.circle(mistX[i], mistY[i], 1.3f, 6, MenuColors.withAlpha(0xFFFFFF, (int) (70 * a)));
        }
    }

    // ——— 10. «Скорость»: гиперпрыжок — слои звёзд, кольца туннеля и вспышка при ускорении ———

    private static final int WARP_COUNT = 46;
    private static final float[] wAngle = new float[WARP_COUNT];
    private static final float[] wDist = new float[WARP_COUNT];
    private static final float[] wSpd = new float[WARP_COUNT];
    private static boolean warpInit;
    private static final java.util.Random WARP_RND = new java.util.Random();

    private static void drawWarpFull(float sw, float sh, float a) {
        float dt = Math.max(0.005f, currentDt);
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;
        int tintA = ThemeManager.mix(accent, 0xFFFFFF, 0.5f);
        if (!warpInit) {
            for (int i = 0; i < WARP_COUNT; i++) {
                wAngle[i] = WARP_RND.nextFloat() * 6.2832f;
                wDist[i] = WARP_RND.nextFloat();
                wSpd[i] = 0.12f + WARP_RND.nextFloat() * 0.35f;
            }
            warpInit = true;
        }

        float cx = glowX;
        float cy = glowY;
        float boost = Math.max(0.35f, warpBoost);
        float maxReach = Math.max(sw, sh) * 0.62f;
        long time = System.currentTimeMillis();

        // кольца туннеля: летят на зрителя, создавая ощущение движения вперёд
        for (int k = 0; k < 4; k++) {
            float phase = ((time / (2400.0f) + k * 0.25f) % 1.0f);
            float ringR = 20.0f + phase * maxReach * 0.8f;
            float ringFade = (1.0f - phase) * (1.0f - phase);
            Render2D.arcRect(cx, cy, ringR, 1.0f + 1.4f * (1.0f - phase), 0.0f, 360.0f,
                    MenuColors.withAlpha(accent, (int) ((6.0f + 26.0f * ringFade * boost / 2.2f) * a)));
        }

        // звёзды трёх слоёв глубины: дальние медленные и тонкие, ближние быстрые и толстые
        for (int i = 0; i < WARP_COUNT; i++) {
            int tier = i % 3;
            float tierMul = tier == 0 ? 0.5f : (tier == 1 ? 0.9f : 1.4f);
            wDist[i] += wSpd[i] * dt * boost * tierMul * (0.25f + wDist[i] * 1.35f);
            if (wDist[i] > 1.05f) {
                wAngle[i] = WARP_RND.nextFloat() * 6.2832f;
                wDist[i] = 0.02f + WARP_RND.nextFloat() * 0.04f;
                wSpd[i] = 0.12f + WARP_RND.nextFloat() * 0.35f;
            }
            float d = wDist[i];
            float ca = (float) Math.cos(wAngle[i]);
            float sa = (float) Math.sin(wAngle[i]);
            float x0 = cx + ca * d * maxReach;
            float y0 = cy + sa * d * maxReach;
            float tail = d * (0.06f + 0.055f * boost) * tierMul;
            float x1 = cx + ca * Math.max(0.0f, d - tail) * maxReach;
            float y1 = cy + sa * Math.max(0.0f, d - tail) * maxReach;

            int col = tier == 2 ? accent : (tier == 1 ? tintA : 0xFFFFFF);
            float fade = 0.15f + Math.min(1.0f, d * 1.7f);
            float thick = (tier == 2 ? 1.7f : tier == 1 ? 1.2f : 0.9f) + fade * 1.4f * Math.min(1.6f, boost);
            Render2D.line(x1, y1, x0, y0, thick,
                    MenuColors.withAlpha(col, (int) (72.0f * fade * a)));
            // у ближних звёзд на среднем удалении — яркая головка
            if (tier == 2 && d > 0.45f && d < 0.95f) {
                Render2D.circle(x0, y0, 1.6f, 8, MenuColors.rgba(255, 255, 255, (int) (150 * fade * a)));
            }
        }

        // центр варпа: свечение + крестовые лучи при разгоне
        glowPoint(cx, cy, 16.0f + 10.0f * boost, tintA, 60.0f * a);
        if (boost > 1.8f) {
            float flareA = Math.min(1.0f, boost - 1.8f);
            Render2D.rect(cx - 90.0f * flareA, cy - 0.8f, 180.0f * flareA, 1.6f, 0.8f,
                    MenuColors.withAlpha(tintA, 0), MenuColors.withAlpha(0xFFFFFF, (int) (90.0f * flareA * a)),
                    MenuColors.withAlpha(0xFFFFFF, (int) (90.0f * flareA * a)), MenuColors.withAlpha(tintA, 0));
            Render2D.rect(cx - 0.8f, cy - 60.0f * flareA, 1.6f, 120.0f * flareA, 0.8f,
                    MenuColors.withAlpha(tintA, 0), MenuColors.withAlpha(tintA, (int) (60.0f * flareA * a)),
                    MenuColors.withAlpha(tintA, (int) (60.0f * flareA * a)), MenuColors.withAlpha(tintA, 0));
        }

        // маркер точки варпа
        float pulse = 0.5f + 0.5f * (float) Math.sin(time / 700.0);
        Render2D.arcRect(cx, cy, 6.0f + 3.0f * pulse, 1.2f, 0.0f, 360.0f,
                MenuColors.withAlpha(accent, (int) ((24.0f + 44.0f * (boost / 3.6f)) * a)));
    }

    // ——— 11. «Туманность»: дышащие облака газа, звёзды проступают сквозь них, курсор разгоняет газ ———

    private static final int NEB_CLOUDS = 5;
    private static final float[] nebA = new float[NEB_CLOUDS];
    private static final float[] nebSpd = new float[NEB_CLOUDS];
    private static final float[] nebRad = new float[NEB_CLOUDS];
    private static boolean nebInit;

    private static void drawNebulaFull(float sw, float sh, float a) {
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;
        int tintA = ThemeManager.mix(accent, 0xFFFFFF, 0.45f);
        int tintB = ThemeManager.mix(accent, 0x1B2436, 0.4f);
        long time = System.currentTimeMillis();
        if (!nebInit) {
            for (int i = 0; i < NEB_CLOUDS; i++) {
                nebA[i] = STK_RND.nextFloat() * 6.2832f;
                nebSpd[i] = 0.05f + STK_RND.nextFloat() * 0.12f;
                nebRad[i] = 0.16f + STK_RND.nextFloat() * 0.14f;
            }
            nebInit = true;
        }

        float mx = mouseX >= 0.0f ? mouseX : sw * 0.5f;
        float my = mouseY >= 0.0f ? mouseY : sh * 0.5f;

        // глубокий космос: облака газа дрейфуют по эллипсам, параллакс по слоям
        for (int i = 0; i < NEB_CLOUDS; i++) {
            nebA[i] += (0.016f + nebSpd[i]) * currentDt;
            float depth = 0.4f + 0.6f * (i / (float) NEB_CLOUDS);
            float cx = sw * 0.5f + (float) Math.cos(nebA[i]) * sw * (0.18f + 0.05f * i) - smoothX * 30.0f * depth;
            float cy = sh * 0.5f + (float) Math.sin(nebA[i] * 1.3f) * sh * (0.14f + 0.04f * i) - smoothY * 18.0f * depth;
            float r = sh * nebRad[i] * (1.0f + 0.08f * (float) Math.sin(time / (3700.0f + i * 800.0f)));
            // курсор расталкивает газ: облако уходит от мыши
            float ddx = cx - mx;
            float ddy = cy - my;
            float d = (float) Math.sqrt(ddx * ddx + ddy * ddy);
            float push = d < sh * 0.42f ? (1.0f - d / (sh * 0.42f)) : 0.0f;
            if (push > 0.0f && d > 0.5f) {
                cx += ddx / d * push * 26.0f;
                cy += ddy / d * push * 26.0f;
            }
            int col = i % 3 == 0 ? tintB : (i % 3 == 1 ? accent : tintA);
            glowPoint(cx, cy, r, col, (18.0f + 10.0f * (float) Math.sin(time / (2900.0f + i * 640.0f))) * a);
        }

        // звёзды проступают: у курсора и в «просветах» между облаками они ярче
        for (int i = 0; i < 26; i++) {
            float sx = ((i * 83 % 97) / 97.0f) * sw - smoothX * 20.0f;
            float sy = ((i * 57 % 89) / 89.0f) * sh - smoothY * 14.0f;
            float tw = 0.5f + 0.5f * (float) Math.sin(time / (800.0f + i * 130.0f) + i);
            float ddx = sx - mx;
            float ddy = sy - my;
            float d = (float) Math.sqrt(ddx * ddx + ddy * ddy);
            float near = d < sh * 0.20f ? 1.0f - d / (sh * 0.20f) : 0.0f;
            float s = 0.9f + tw * 0.8f + near * 1.6f;
            if (i % 4 == 0) {
                Render2D.circle(sx, sy, s, 6, MenuColors.withAlpha(tintA, (int) ((40.0f + 150.0f * (tw * 0.5f + near)) * a)));
            } else {
                Render2D.rect(sx, sy, s, s, s * 0.5f, MenuColors.rgba(255, 255, 255, (int) ((16.0f + 70.0f * (tw * 0.6f + near)) * a)));
            }
        }

        // у курсора — рождение новой звезды с искрами
        if (mouseX >= 0.0f) {
            glowPoint(mouseX, mouseY, 20.0f, 0xFFFFFF, 34.0f * a);
            for (int s = 0; s < 4; s++) {
                float ang = time / (500.0f + s * 150.0f) + s * 1.7f;
                float sr = 14.0f + 6.0f * (float) Math.sin(time / (600.0f + s * 100.0f) + s);
                Render2D.circle(mouseX + (float) Math.cos(ang) * sr, mouseY + (float) Math.sin(ang) * sr,
                        1.2f, 6, MenuColors.withAlpha(tintA, (int) (80 * a)));
            }
        }
    }

    // ——— 12. «Код»: цифровой ливень — колонки символов, курсор подсвечивает и изгибает потоки ———

    private static final int MATRIX_COLS = 26;
    private static final float[] mtxPhase = new float[MATRIX_COLS];
    private static final float[] mtxSpd = new float[MATRIX_COLS];
    private static final float[] mtxLen = new float[MATRIX_COLS];
    private static boolean mtxInit;

    private static void drawMatrixFull(float sw, float sh, float a) {
        float dt = Math.max(0.005f, currentDt);
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;
        int tintA = ThemeManager.mix(accent, 0xFFFFFF, 0.55f);
        float mx = mouseX >= 0.0f ? mouseX : -9999.0f;
        float my = mouseY >= 0.0f ? mouseY : -9999.0f;
        long time = System.currentTimeMillis();
        if (!mtxInit) {
            for (int i = 0; i < MATRIX_COLS; i++) {
                mtxPhase[i] = STK_RND.nextFloat() * 1.4f;
                mtxSpd[i] = 0.16f + STK_RND.nextFloat() * 0.30f;
                mtxLen[i] = 0.18f + STK_RND.nextFloat() * 0.30f;
            }
            mtxInit = true;
        }

        // колонки: «голова» яркая, шлейф затухает; у курсора колонка изгибается и вспыхивает
        for (int i = 0; i < MATRIX_COLS; i++) {
            mtxPhase[i] += mtxSpd[i] * dt;
            if (mtxPhase[i] > 1.0f + mtxLen[i]) {
                mtxPhase[i] = -STK_RND.nextFloat() * 0.4f;
                mtxSpd[i] = 0.16f + STK_RND.nextFloat() * 0.30f;
                mtxLen[i] = 0.18f + STK_RND.nextFloat() * 0.30f;
            }
            float colX = sw * ((i + 0.5f) / MATRIX_COLS);
            // курсор отклоняет колонку
            float bend = 0.0f;
            float glow = 0.0f;
            if (mx > -999.0f) {
                float ddx = colX - mx;
                float adx = Math.abs(ddx);
                if (adx < 130.0f) {
                    bend = (1.0f - adx / 130.0f) * (ddx > 0 ? 1.0f : -1.0f) * 14.0f;
                    glow = 1.0f - adx / 130.0f;
                }
            }
            float head = mtxPhase[i];
            float tail = head - mtxLen[i];
            // шлейф: 6 сегментов с затуханием
            for (int s = 0; s < 6; s++) {
                float t0 = head - (mtxLen[i] * s) / 6.0f;
                float t1 = head - (mtxLen[i] * (s + 1)) / 6.0f;
                if (t1 < 0.0f) {
                    break;
                }
                float segFade = 1.0f - s * 0.16f;
                float y0 = t0 * sh;
                float y1 = Math.max(0.0f, t1 * sh);
                // изгиб: чем ближе к земле, тем сильнее
                float w0 = t0, w1 = t1;
                float x0 = colX + bend * (0.3f + 0.7f * t0);
                float x1b = colX + bend * (0.3f + 0.7f * t1);
                Render2D.line(x0, y0, x1b, y1, Math.max(0.8f, 1.6f * segFade),
                        MenuColors.withAlpha(s == 0 ? tintA : accent, (int) ((26.0f + 60.0f * segFade + 90.0f * glow * segFade) * a)));
            }
            // голова-«символ»: мерцающий квадратик
            if (head > 0.0f && head < 1.0f) {
                float hx = colX + bend;
                float hy = head * sh;
                Render2D.rect(hx - 1.6f, hy - 1.6f, 3.2f, 3.2f, 0.8f,
                        MenuColors.rgba(255, 255, 255, (int) ((160.0f + 90.0f * glow) * a)));
                if (glow > 0.3f) {
                    glowPoint(hx, hy, 8.0f, tintA, 50.0f * glow * a);
                }
            }
        }

        // горизонтальная сканирующая полоса
        float scanY = ((time / 6400.0f) % 1.0f) * sh;
        Render2D.rect(0.0f, scanY, sw, 1.0f, 0.5f, MenuColors.withAlpha(tintA, (int) (26 * a)));
        Render2D.rect(0.0f, scanY - 2.0f, sw, 1.0f, 0.5f, MenuColors.withAlpha(tintA, (int) (10 * a)));
    }

    // ——— 13. «Светлячки»: стая блуждающих огоньков, курсор их манит и вспыхивает вместе с ними ———

    private static final int FLY_COUNT = 22;
    private static final float[] flyX = new float[FLY_COUNT];
    private static final float[] flyY = new float[FLY_COUNT];
    private static final float[] flyVX = new float[FLY_COUNT];
    private static final float[] flyVY = new float[FLY_COUNT];
    private static final float[] flyPhase = new float[FLY_COUNT];
    private static boolean flyInit;

    private static void drawFirefliesFull(float sw, float sh, float a) {
        float dt = Math.max(0.005f, currentDt);
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;
        int tintA = ThemeManager.mix(accent, 0xFFFFD9, 0.5f); // тёплый огонёк
        long time = System.currentTimeMillis();
        if (!flyInit) {
            for (int i = 0; i < FLY_COUNT; i++) {
                flyX[i] = STK_RND.nextFloat() * sw;
                flyY[i] = STK_RND.nextFloat() * sh;
                flyVX[i] = (STK_RND.nextFloat() - 0.5f) * 60.0f;
                flyVY[i] = (STK_RND.nextFloat() - 0.5f) * 60.0f;
                flyPhase[i] = STK_RND.nextFloat() * 6.2832f;
            }
            flyInit = true;
        }
        float mx = mouseX >= 0.0f ? mouseX : sw * 0.5f;
        float my = mouseY >= 0.0f ? mouseY : sh * 0.5f;
        float attractR = Math.min(sw, sh) * 0.30f;

        for (int i = 0; i < FLY_COUNT; i++) {
            // блуждание: курсор мягко притягивает, дальше — свободный дрейф с завихрением
            float ddx = mx - flyX[i];
            float ddy = my - flyY[i];
            float d = (float) Math.sqrt(ddx * ddx + ddy * ddy);
            if (d < attractR && d > 1.0f) {
                float pull = (1.0f - d / attractR) * 120.0f;
                flyVX[i] += ddx / d * pull * dt;
                flyVY[i] += ddy / d * pull * dt;
            }
            // вихрь вокруг курсора: огоньки кружат
            if (d < attractR * 0.6f && d > 4.0f) {
                flyVX[i] += -ddy / d * 90.0f * dt;
                flyVY[i] += ddx / d * 90.0f * dt;
            }
            // затухание скорости и лёгкий шум
            flyVX[i] *= (1.0f - Math.min(1.0f, dt * 0.7f));
            flyVY[i] *= (1.0f - Math.min(1.0f, dt * 0.7f));
            flyVX[i] += (float) Math.sin(time / (1900.0f + i * 230.0f) + i) * 26.0f * dt;
            flyVY[i] += (float) Math.cos(time / (2100.0f + i * 190.0f) + i * 1.3f) * 26.0f * dt;
            float vmax = 150.0f;
            float spd = (float) Math.sqrt(flyVX[i] * flyVX[i] + flyVY[i] * flyVY[i]);
            if (spd > vmax) {
                flyVX[i] *= vmax / spd;
                flyVY[i] *= vmax / spd;
            }
            flyX[i] += flyVX[i] * dt;
            flyY[i] += flyVY[i] * dt;
            // отражение от краёв
            if (flyX[i] < 0.0f || flyX[i] > sw) {
                flyVX[i] *= -0.8f;
                flyX[i] = Math.max(1.0f, Math.min(sw - 1.0f, flyX[i]));
            }
            if (flyY[i] < 0.0f || flyY[i] > sh) {
                flyVY[i] *= -0.8f;
                flyY[i] = Math.max(1.0f, Math.min(sh - 1.0f, flyY[i]));
            }

            // вспышка: у курсора светлячки синхронизируются и горят ярче
            float near = d < attractR ? 1.0f - d / attractR : 0.0f;
            float blink = 0.5f + 0.5f * (float) Math.sin(time / 900.0f + flyPhase[i] * (1.0f - near * 0.8f));
            float glow = Math.max(blink, near * 0.9f);
            if (glow > 0.08f) {
                glowPoint(flyX[i], flyY[i], 5.0f + 4.0f * glow, tintA, (14.0f + 70.0f * glow) * a);
                Render2D.circle(flyX[i], flyY[i], 1.3f + glow, 8,
                        MenuColors.rgba(255, 244, 200, (int) ((90.0f + 165.0f * glow) * a)));
            }
            // короткий след
            fadeTail(flyX[i], flyY[i], flyX[i] - flyVX[i] * 0.12f, flyY[i] - flyVY[i] * 0.12f,
                    1.2f, tintA, 40.0f * glow * a, 3);
        }

        // у курсора — тёплое свечение-«лампа»
        if (mouseX >= 0.0f) {
            glowPoint(mouseX, mouseY, 18.0f, tintA, 40.0f * a);
        }
    }

    // ——— 14. «Бездна»: океанская глубина — планктон, медузы, курсор как прожектор ———

    private static final int ABYSS_PLANKTON = 30;
    private static final float[] abX = new float[ABYSS_PLANKTON];
    private static final float[] abY = new float[ABYSS_PLANKTON];
    private static final float[] abSpd = new float[ABYSS_PLANKTON];
    private static final float[] abDrift = new float[ABYSS_PLANKTON];
    private static boolean abInit;

    private static void drawAbyssFull(float sw, float sh, float a) {
        float dt = Math.max(0.005f, currentDt);
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;
        int teal = ThemeManager.mix(accent, 0x35E0D8, 0.6f);
        long time = System.currentTimeMillis();
        if (!abInit) {
            for (int i = 0; i < ABYSS_PLANKTON; i++) {
                abX[i] = STK_RND.nextFloat();
                abY[i] = STK_RND.nextFloat();
                abSpd[i] = 0.02f + STK_RND.nextFloat() * 0.05f;
                abDrift[i] = STK_RND.nextFloat() * 6.2832f;
            }
            abInit = true;
        }

        // столб света-прожектора сверху, следует за курсором
        float mx = mouseX >= 0.0f ? mouseX : sw * 0.5f;
        glowPoint(mx, sh * 0.06f, sh * 0.16f, teal, 44.0f * a);
        Render2D.rect(mx - 60.0f, 0.0f, 120.0f, sh * 0.9f, 0.0f,
                MenuColors.withAlpha(teal, 0), MenuColors.withAlpha(teal, (int) (12 * a)),
                MenuColors.withAlpha(teal, 0), MenuColors.withAlpha(teal, 0));

        // медузы: пульсирующие колокола с щупальцами, всплывают
        for (int j = 0; j < 3; j++) {
            float jx = sw * (0.22f + 0.28f * j) + (float) Math.sin(time / (5200.0f + j * 900.0f)) * sw * 0.03f - smoothX * 14.0f;
            float jy = sh * (0.24f + 0.2f * ((j * 7) % 3) / 3.0f) - ((time / (90.0f + j * 30.0f)) % (sh * 0.8f));
            jy = sh * 0.95f - (((time / (26000.0f + j * 7000.0f)) + j * 0.33f) % 1.0f) * sh * 0.85f;
            float pulse = 0.5f + 0.5f * (float) Math.sin(time / (1300.0f + j * 400.0f));
            float jr = 10.0f + 4.0f * pulse;
            float ddx = mx - jx;
            float ddy = jy - sh * 0.06f;
            float nearJ = Math.max(0.0f, 1.0f - Math.abs(jx - mx) / (sw * 0.25f));
            glowPoint(jx, jy, jr, teal, (26.0f + 40.0f * pulse + 30.0f * nearJ) * a);
            Render2D.arcRect(jx, jy, jr, 1.3f, 180.0f, 180.0f, MenuColors.withAlpha(teal, (int) ((60.0f + 60.0f * pulse) * a)));
            // щупальца: три волнистые линии
            for (int t = 0; t < 3; t++) {
                float tx = jx + (t - 1) * jr * 0.5f;
                Render2D.line(tx, jy + 2.0f, tx + (float) Math.sin(time / 700.0f + t + j) * 4.0f, jy + jr * 1.9f, 1.0f,
                        MenuColors.withAlpha(teal, (int) ((24.0f + 30.0f * pulse) * a)));
            }
        }

        // планктон: всплывает, светится ярче у луча прожектора
        for (int i = 0; i < ABYSS_PLANKTON; i++) {
            abY[i] -= abSpd[i] * dt;
            abDrift[i] += dt * 0.7f;
            if (abY[i] < -0.05f) {
                abY[i] = 1.05f;
                abX[i] = STK_RND.nextFloat();
            }
            float px = abX[i] * sw + (float) Math.sin(abDrift[i]) * 12.0f - smoothX * 8.0f;
            float py = abY[i] * sh;
            float inBeam = Math.max(0.0f, 1.0f - Math.abs(px - mx) / 120.0f);
            float tw = 0.5f + 0.5f * (float) Math.sin(time / (700.0f + i * 90.0f) + i);
            float s = 1.0f + tw * 0.8f;
            int alpha = (int) ((10.0f + 30.0f * tw + 90.0f * inBeam * tw) * a);
            Render2D.rect(px, py, s, s, s * 0.5f, MenuColors.withAlpha(inBeam > 0.4f ? teal : 0xFFFFFF, alpha));
        }

        // давящая тьма по краям
        Render2D.rect(0.0f, 0.0f, sw, sh, 0.0f,
                MenuColors.rgba(0, 0, 0, (int) (0 * a)), MenuColors.rgba(0, 0, 0, (int) (60 * a)),
                MenuColors.rgba(0, 0, 0, (int) (110 * a)), MenuColors.rgba(0, 0, 0, (int) (90 * a)));
    }

    // ——— 15. «Магма»: тектонические трещины светятся у курсора, угольки поднимаются ———

    private static final int LAVA_CRACKS = 7;
    private static final float[] lavaSeed = new float[LAVA_CRACKS];
    private static boolean lavaInit;
    private static final int EMBER_COUNT = 16;
    private static final float[] embX = new float[EMBER_COUNT];
    private static final float[] embY = new float[EMBER_COUNT];
    private static final float[] embSpd = new float[EMBER_COUNT];
    private static final boolean[] embAlive = new boolean[EMBER_COUNT];
    private static long nextEmberAt;

    private static void drawLavaFull(float sw, float sh, float a) {
        float dt = Math.max(0.005f, currentDt);
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;
        int hot = ThemeManager.mix(accent, 0xFF6A2A, 0.55f);
        int hotCore = ThemeManager.mix(hot, 0xFFE9B0, 0.55f);
        long time = System.currentTimeMillis();
        float mx = mouseX >= 0.0f ? mouseX : -9999.0f;
        float my = mouseY >= 0.0f ? mouseY : -9999.0f;
        if (!lavaInit) {
            for (int i = 0; i < LAVA_CRACKS; i++) {
                lavaSeed[i] = STK_RND.nextFloat() * 100.0f;
            }
            lavaInit = true;
        }

        // тлеющий жар снизу, дышит
        float breath = 0.5f + 0.5f * (float) Math.sin(time / 3400.0);
        glowPoint(sw * 0.5f, sh * 1.05f, sh * 0.42f, hot, (26.0f + 16.0f * breath) * a);

        // трещины: ломаные линии через экран; яркость сегмента = близость к курсору + пульс
        float reach = Math.min(sw, sh) * 0.26f;
        for (int c = 0; c < LAVA_CRACKS; c++) {
            float seed = lavaSeed[c];
            float y0 = sh * (0.12f + 0.76f * ((c * 13 % 10) / 10.0f));
            float x = -20.0f;
            float y = y0;
            float px2 = x;
            float py2 = y;
            for (int s = 1; s <= 12; s++) {
                x = -20.0f + (sw + 40.0f) * s / 12.0f;
                y = y0 + (float) Math.sin(seed + s * 1.1f) * sh * 0.055f
                        + (float) Math.sin(seed * 2.3f + s * 0.7f) * sh * 0.03f;
                // пульс жилы
                float beat = 0.5f + 0.5f * (float) Math.sin(time / (1200.0f + c * 260.0f) - s * 0.55f);
                // близость к курсору
                float segHeat = 0.0f;
                if (mx > -999.0f) {
                    float midX = (px2 + x) * 0.5f;
                    float midY = (py2 + y) * 0.5f;
                    float ddx = midX - mx;
                    float ddy = midY - my;
                    float d = (float) Math.sqrt(ddx * ddx + ddy * ddy);
                    segHeat = d < reach ? 1.0f - d / reach : 0.0f;
                    segHeat *= segHeat;
                }
                float heat = Math.max(beat * 0.5f, segHeat);
                if (heat > 0.06f) {
                    Render2D.line(px2, py2, x, y, 1.4f + heat * 2.2f, MenuColors.withAlpha(hot, (int) ((36.0f + 120.0f * heat) * a)));
                    Render2D.line(px2, py2, x, y, Math.max(0.6f, 0.7f * heat), MenuColors.withAlpha(hotCore, (int) ((30.0f + 200.0f * heat) * a)));
                    if (heat > 0.55f) {
                        glowPoint((px2 + x) * 0.5f, (py2 + y) * 0.5f, 8.0f + 10.0f * heat, hot, 70.0f * heat * a);
                    }
                }
                px2 = x;
                py2 = y;
            }
        }

        // угольки: рождаются из раскалённых участков, всплывают с покачиванием
        if (time >= nextEmberAt) {
            for (int i = 0; i < EMBER_COUNT; i++) {
                if (!embAlive[i]) {
                    embAlive[i] = true;
                    embX[i] = STK_RND.nextFloat() * sw;
                    embY[i] = sh * (0.6f + STK_RND.nextFloat() * 0.4f);
                    embSpd[i] = sh * (0.06f + STK_RND.nextFloat() * 0.10f);
                    break;
                }
            }
            nextEmberAt = time + 220L + STK_RND.nextInt(400);
        }
        for (int i = 0; i < EMBER_COUNT; i++) {
            if (!embAlive[i]) {
                continue;
            }
            embY[i] -= embSpd[i] * dt;
            float ex = embX[i] + (float) Math.sin(time / (800.0f + i * 140.0f) + i) * 9.0f;
            if (embY[i] < -10.0f) {
                embAlive[i] = false;
                continue;
            }
            // курсор задувает угольки
            if (mx > -999.0f) {
                float ddx = ex - mx;
                float ddy = embY[i] - my;
                float d = (float) Math.sqrt(ddx * ddx + ddy * ddy);
                if (d < 60.0f && d > 0.5f) {
                    ex += ddx / d * (60.0f - d) * 0.35f;
                    embY[i] += ddy / d * (60.0f - d) * 0.25f;
                }
            }
            float life = Math.min(1.0f, (sh - embY[i]) / (sh * 0.3f));
            float tw = 0.5f + 0.5f * (float) Math.sin(time / (240.0f + i * 70.0f) + i);
            Render2D.circle(ex, embY[i], 1.1f + tw * 0.8f, 6,
                    MenuColors.withAlpha(hotCore, (int) ((70.0f + 150.0f * tw) * (1.0f - life * 0.7f) * a)));
            glowPoint(ex, embY[i], 3.0f, hot, 40.0f * tw * (1.0f - life * 0.6f) * a);
        }

        // дымка жара поверх
        Render2D.rect(0.0f, sh * 0.55f, sw, sh * 0.45f, 0.0f,
                MenuColors.withAlpha(hot, 0), MenuColors.withAlpha(hot, 0),
                MenuColors.withAlpha(hot, (int) ((10.0f + 8.0f * breath) * a)), MenuColors.withAlpha(hot, (int) (18 * a)));
    }

    // ——— обновлённая «Аврора»: ленты тоже реагируют на мышь ———

    private static void drawAuroraFull(float sw, float sh, float a) {
        long now = System.currentTimeMillis();
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;

        int top = ThemeManager.mix(MenuColors.rgba(8, 10, 15, 255), accent, 0.16f);
        int bottom = MenuColors.withAlpha(accent, (int) (120 * a));
        Render2D.rect(0.0f, 0.0f, sw, sh, 0.0f, top, top, bottom, bottom);

        for (int band = 0; band < 3; band++) {
            float wave = 0.5f + 0.5f * (float) Math.sin(now / (2400.0f + band * 900.0f) + band * 2.1);
            wave += smoothX * 0.35f * Math.max(0.01f, 1.0f + Math.cos(now / (2600.0f + band * 640.0f)));
            float bandY = sh * (0.18f + 0.26f * wave + smoothY * 0.07f * (band - 1));
            float bandH = sh * (0.30f + 0.14f * wave);
            int bandCol = MenuColors.withAlpha(accent, (int) ((20.0f + 26.0f * wave) * a));
            Render2D.rect(0.0f, bandY, sw, bandH, 0.0f,
                    MenuColors.withAlpha(accent, 0), bandCol,
                    MenuColors.withAlpha(accent, (int) ((8.0f + 14.0f * wave) * a)),
                    MenuColors.withAlpha(accent, 0));
        }
    }

    /** Живое мини-превью процедурного фона для карточки в галерее. */
    public static void drawProceduralPreview(MenuBackground bg, float x, float y, float w, float h, float a,
                                             float px, float py) {
        String src = bg == null ? "" : (bg.source() == null ? "" : bg.source());
        float lm = (px - x) / Math.max(1.0f, w);
        float lmy = (py - y) / Math.max(1.0f, h);
        lm = Math.max(-1.0f, Math.min(2.0f, lm));
        lmy = Math.max(-1.0f, Math.min(2.0f, lmy));
        long time = System.currentTimeMillis();
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;
        int base = MenuColors.rgba(9, 11, 16, (int) (255 * a));

        if (src.startsWith("style:fluid")) {
            Render2D.rect(x, y, w, h, 0.0f, base, base, base, base);
            for (int i = 0; i < 5; i++) {
                float bxl = x + w * (0.5f + 0.30f * lm + 0.16f * (float) Math.sin(time / (1800.0f + i * 350.0f) + i));
                float byl = y + h * (0.5f + 0.30f * lmy + 0.16f * (float) Math.cos(time / (2100.0f + i * 400.0f) + i * 1.3f));
                softCircle(bxl, byl, h * (0.16f + 0.035f * i), accent, (int) (36 * a), (int) (7 * a));
            }
        } else if (src.startsWith("style:grid")) {
            Render2D.rect(x, y, w, h, 0.0f, base, base, base, base);
            float pcx = x + w * lm;
            float pcy = y + h * lmy;
            for (int gy = 0; gy <= 5; gy++) {
                for (int gxx = 0; gxx <= 8; gxx++) {
                    float dotX = x + 6.0f + gxx * (w - 12.0f) / 8.0f;
                    float dotY = y + 5.0f + gy * (h - 10.0f) / 5.0f;
                    float dx = dotX - pcx;
                    float dy = dotY - pcy;
                    float dist = (float) Math.sqrt(dx * dx + dy * dy);
                    float inf = Math.max(0.0f, 1.0f - dist / (h * 0.9f));
                    inf *= inf;
                    if (inf > 0.03f) {
                        Render2D.circle(dotX - dx / Math.max(1.0f, dist) * inf * 5.0f,
                                dotY - dy / Math.max(1.0f, dist) * inf * 5.0f,
                                1.2f + inf * 2.6f, 8, MenuColors.withAlpha(accent, (int) ((60.0f + 165.0f * inf) * a)));
                    }
                    Render2D.circle(dotX, dotY, 1.1f, 6, MenuColors.rgba(255, 255, 255, (int) (20 * a)));
                }
            }
        } else if (src.startsWith("style:bubbles")) {
            Render2D.rect(x, y, w, h, 0.0f, base, base, base, base);
            for (int i = 0; i < 8; i++) {
                float phase = (time / 3000.0f + i * 0.125f) % 1.0f;
                float bxl = x + (0.15f * (i % 4) + 0.2f) * w + (float) Math.sin(time / 1900.0f + i) * 5.0f;
                float byl = y + h * (1.05f - 1.2f * phase);
                float r = (2.5f + (i % 3) * 1.6f) * (0.4f + phase * 0.9f);
                int col = i % 2 == 0 ? accent : 0xFFFFFF;
                Render2D.arcRect(bxl, byl, r, 1.0f, 0.0f, 360.0f, MenuColors.withAlpha(col, (int) (40 * a)));
                Render2D.circle(bxl, byl, r * 0.5f, 10, MenuColors.withAlpha(col, (int) (9 * a)));
            }
        } else if (src.startsWith("style:streaks")) {
            Render2D.rect(x, y, w, h, 0.0f, base, base, base, base);
            for (int i = 0; i < 5; i++) {
                float phase = (time / (2600.0f + i * 500.0f) + i * 0.21f) % 1.0f;
                float sx = x + phase * (w * 1.3f) - w * 0.15f;
                float sy = y + (0.12f + 0.18f * i) * h + phase * h * 0.3f;
                Render2D.line(sx, sy, sx - w * 0.16f, sy - h * 0.12f, 1.2f,
                        MenuColors.withAlpha(accent, (int) (60.0f * a)));
            }
            for (int i = 0; i < 7; i++) {
                float tw = 0.5f + 0.5f * (float) Math.sin(time / 700.0f + i * 1.9f);
                Render2D.circle(x + (0.12f + (i * 37 % 76) / 100.0f) * w, y + (0.1f + (i * 53 % 80) / 100.0f) * h,
                        0.9f + tw, 6, MenuColors.rgba(255, 255, 255, (int) ((16.0f + 40.0f * tw) * a)));
            }
        } else if (src.startsWith("style:ripples")) {
            Render2D.rect(x, y, w, h, 0.0f, base, base, base, base);
            float pcx = x + w * lm;
            float pcy = y + h * lmy;
            for (int i = 0; i < 3; i++) {
                float phase = (time / 2400.0f + i * 0.33f) % 1.0f;
                float fade = (1.0f - phase) * (1.0f - phase);
                float r = phase * h * 0.55f + 2.0f;
                Render2D.arcRect(x + w * 0.5f, y + h * 0.5f, r, 1.2f, 0.0f, 360.0f,
                        MenuColors.withAlpha(accent, (int) (55.0f * fade * a)));
            }
            if (px >= x && px <= x + w && py >= y && py <= y + h) {
                float breathe = 0.5f + 0.5f * (float) Math.sin(time / 900.0f);
                Render2D.arcRect(pcx, pcy, 6.0f + 10.0f * breathe, 1.1f, 0.0f, 360.0f,
                        MenuColors.withAlpha(accent, (int) ((30.0f + 40.0f * breathe) * a)));
            }
        } else if (src.startsWith("style:storm")) {
            Render2D.rect(x, y, w, h, 0.0f, base, base, base, base);
            for (int i = 0; i < 3; i++) {
                float clx = x + w * (0.22f + 0.24f * i) + (float) Math.sin(time / (3400.0f + i * 700.0f)) * 3.0f;
                softCircle(clx, y + h * 0.12f, h * 0.20f, ThemeManager.mix(accent, 0x39424F, 0.5f), (int) (30 * a), (int) (9 * a));
            }
            float phase = (time % 2600) / 2600.0f;
            if (phase < 0.30f) {
                float pr = phase / 0.30f;
                float fade = (1.0f - pr) * (1.0f - pr);
                float bx0 = x + 0.30f * w;
                float by0 = y + 0.10f * h;
                Render2D.line(bx0, by0, bx0 + w * 0.08f, by0 + h * 0.18f * 0.4f, 1.1f, MenuColors.rgba(255, 255, 255, (int) (200.0f * fade * a)));
                Render2D.line(bx0 + w * 0.08f, by0 + h * 0.18f * 0.4f, bx0 + w * 0.02f, by0 + h * 0.22f, 1.0f, MenuColors.rgba(255, 255, 255, (int) (170.0f * fade * a)));
                Render2D.line(bx0 + w * 0.02f, by0 + h * 0.22f, bx0 + w * 0.10f, by0 + h * 0.40f, 0.9f, MenuColors.withAlpha(accent, (int) (150.0f * fade * a)));
                Render2D.rect(x, y, w, h, 0.0f,
                        MenuColors.rgba(255, 255, 255, 0),
                        MenuColors.rgba(255, 255, 255, (int) (16.0f * fade * a)),
                        MenuColors.rgba(255, 255, 255, (int) (8.0f * fade * a)),
                        MenuColors.rgba(255, 255, 255, 0));
            }
        } else if (src.startsWith("style:orbit")) {
            Render2D.rect(x, y, w, h, 0.0f, base, base, base, base);
            float ocx = x + w * 0.5f;
            float ocy = y + h * 0.5f;
            Render2D.circle(ocx, ocy, 1.8f, 8, MenuColors.withAlpha(accent, (int) (200 * a)));
            for (int j = 0; j < 2; j++) {
                float ringR = h * (0.16f + 0.16f * j);
                Render2D.arcRect(ocx, ocy, ringR, 0.9f, 0.0f, 360.0f, MenuColors.rgba(255, 255, 255, (int) (16 * a)));
                for (int s = 0; s < 3; s++) {
                    float ang = time / 1400.0f * (j % 2 == 0 ? 1.0f : -1.0f) + s * 2.094f + j * 0.6f;
                    float odx = ocx + (float) Math.cos(ang) * ringR;
                    float ody = ocy + (float) Math.sin(ang) * ringR;
                    Render2D.circle(odx, ody, 1.6f, 8, MenuColors.withAlpha(accent, (int) (130 * a)));
                }
            }
        } else if (src.startsWith("style:portal")) {
            Render2D.rect(x, y, w, h, 0.0f, base, base, base, base);
            float pcx = x + w * 0.5f;
            float pcy = y + h * 0.52f;
            float pr2 = time / 2400.0f;
            Render2D.circle(pcx, pcy, h * 0.12f, 20, MenuColors.rgba(4, 5, 8, (int) (230 * a)));
            Render2D.arcRect(pcx, pcy, h * 0.12f, 1.1f, 0.0f, 360.0f, MenuColors.withAlpha(accent, (int) (55 * a)));
            for (int arm = 0; arm < 2; arm++) {
                for (int k2 = 0; k2 < 12; k2++) {
                    float t = (pr2 * 0.5f + arm / 2.0f + k2 * 0.083f) % 1.0f;
                    float r = h * (0.09f + 0.30f * (1.0f - t));
                    float ang = t * 8.0f + arm * 3.1416f + pr2;
                    Render2D.circle(pcx + (float) Math.cos(ang) * r, pcy + (float) Math.sin(ang) * r,
                            0.9f + 1.3f * (1.0f - t), 6, MenuColors.withAlpha(accent, (int) ((20.0f + 95.0f * (1.0f - t)) * a)));
                }
            }
        } else if (src.startsWith("style:rain")) {
            Render2D.rect(x, y, w, h, 0.0f, base, base, base, base);
            float baseLine = y + h * 0.90f;
            for (int i = 0; i < 9; i++) {
                float phase = (time / 2000.0f + i * 0.157f) % 1.15f;
                float rdx = x + w * (0.10f + 0.82f * ((i * 37) % 100) / 100.0f);
                float rdy = y + h * phase;
                Render2D.line(rdx, rdy, rdx - 2.5f, rdy - h * 0.13f, 1.0f,
                        MenuColors.withAlpha(i % 3 == 0 ? accent : 0xFFFFFF, (int) (34 * a)));
            }
            Render2D.rect(x, baseLine, w, 1.0f, 0.5f, 0.5f, 0.5f, 0.5f, MenuColors.rgba(255, 255, 255, (int) (10 * a)));
            for (int i = 0; i < 3; i++) {
                float phase = (time / 1700.0f + i * 0.33f) % 1.0f;
                float fade = 1.0f - phase;
                Render2D.arcRect(x + w * (0.2f + 0.3f * i), baseLine, 1.5f + phase * 9.0f, 0.9f, 200.0f, 140.0f,
                        MenuColors.withAlpha(accent, (int) (40.0f * fade * a)));
            }
        } else if (src.startsWith("style:warp")) {
            Render2D.rect(x, y, w, h, 0.0f, base, base, base, base);
            float wcx = x + w * 0.5f + (float) Math.sin(time / 2400.0f) * w * 0.10f;
            float wcy = y + h * 0.5f + (float) Math.cos(time / 2600.0f) * h * 0.10f;
            for (int i = 0; i < 10; i++) {
                float phase = (time / 1300.0f + i * 0.1f) % 1.0f;
                float ang = i * 0.6283f + 0.2f;
                float ca = (float) Math.cos(ang);
                float sa = (float) Math.sin(ang);
                float mr = Math.max(w, h) * 0.62f;
                float d = 0.06f + phase * 0.85f;
                float tail = 0.07f * phase;
                Render2D.line(wcx + ca * (d - tail) * mr, wcy + sa * (d - tail) * mr,
                        wcx + ca * d * mr, wcy + sa * d * mr, 1.0f + phase * 1.2f,
                        MenuColors.withAlpha(i % 4 == 0 ? accent : 0xFFFFFF, (int) ((20.0f + 60.0f * phase) * a)));
            }
        } else if (src.startsWith("style:nebula")) {
            Render2D.rect(x, y, w, h, 0.0f, base, base, base, base);
            for (int i = 0; i < 3; i++) {
                float cx = x + w * (0.3f + 0.25f * i) + (float) Math.sin(time / (2600.0f + i * 700.0f)) * w * 0.06f + lm * w * 0.12f;
                float cy = y + h * (0.42f + 0.16f * (i % 2)) + (float) Math.cos(time / (3100.0f + i * 500.0f)) * h * 0.05f + lmy * h * 0.10f;
                softCircle(cx, cy, h * (0.22f + 0.05f * i), i == 1 ? accent : ThemeManager.mix(accent, 0x1B2436, 0.4f), (int) (26 * a), (int) (7 * a));
            }
            for (int i = 0; i < 7; i++) {
                float tw = 0.5f + 0.5f * (float) Math.sin(time / 800.0f + i * 2.1f);
                Render2D.rect(x + (0.08f + (i * 41 % 84) / 100.0f) * w, y + (0.1f + (i * 67 % 76) / 100.0f) * h,
                        1.2f + tw, 1.2f + tw, 0.6f, MenuColors.rgba(255, 255, 255, (int) ((20.0f + 60.0f * tw) * a)));
            }
        } else if (src.startsWith("style:matrix")) {
            Render2D.rect(x, y, w, h, 0.0f, base, base, base, base);
            int cols = 7;
            for (int i = 0; i < cols; i++) {
                float colX = x + w * ((i + 0.5f) / cols) + lm * 4.0f;
                float head = (time / (2200.0f + i * 420.0f) + i * 0.31f) % 1.3f;
                float tail = head - 0.32f;
                if (tail < 0.0f) {
                    tail = 0.0f;
                }
                Render2D.line(colX, y + tail * h, colX, y + Math.min(head, 1.0f) * h, 1.2f,
                        MenuColors.withAlpha(accent, (int) (52 * a)));
                if (head < 1.0f) {
                    Render2D.rect(colX - 1.4f, y + head * h - 1.4f, 2.8f, 2.8f, 0.7f,
                            MenuColors.rgba(255, 255, 255, (int) (170 * a)));
                }
            }
            float scanY = y + ((time / 5200.0f) % 1.0f) * h;
            Render2D.rect(x, scanY, w, 1.0f, 0.5f, MenuColors.rgba(255, 255, 255, (int) (20 * a)));
        } else if (src.startsWith("style:fireflies")) {
            Render2D.rect(x, y, w, h, 0.0f, base, base, base, base);
            int warm = ThemeManager.mix(accent, 0xFFD9A0, 0.6f);
            for (int i = 0; i < 6; i++) {
                float fx = x + w * (0.5f + 0.32f * (float) Math.sin(time / (1700.0f + i * 310.0f) + i * 1.9f)) + lm * w * 0.1f;
                float fy = y + h * (0.5f + 0.30f * (float) Math.cos(time / (2100.0f + i * 270.0f) + i * 1.1f)) + lmy * h * 0.1f;
                float blink = 0.5f + 0.5f * (float) Math.sin(time / 800.0f + i * 1.7f);
                Render2D.circle(fx, fy, 1.0f + blink * 1.4f, 6, MenuColors.withAlpha(warm, (int) ((50.0f + 150.0f * blink) * a)));
            }
            if (px >= x && px <= x + w && py >= y && py <= y + h) {
                softCircle(px, py, h * 0.24f, warm, (int) (36 * a), (int) (9 * a));
            }
        } else if (src.startsWith("style:abyss")) {
            Render2D.rect(x, y, w, h, 0.0f, base, base, base, base);
            int teal = ThemeManager.mix(accent, 0x35E0D8, 0.6f);
            float bx = x + w * 0.5f + lm * w * 0.14f;
            softCircle(bx, y + 2.0f, h * 0.16f, teal, (int) (34 * a), (int) (8 * a));
            for (int i = 0; i < 9; i++) {
                float phase = (time / (3000.0f + i * 260.0f) + i * 0.19f) % 1.1f;
                float pxx = x + w * ((i * 31 % 89) / 89.0f) + (float) Math.sin(time / 900.0f + i) * 3.0f;
                float pyy = y + h * (1.05f - phase);
                float tw = 0.5f + 0.5f * (float) Math.sin(time / 600.0f + i * 1.4f);
                Render2D.rect(pxx, pyy, 1.2f + tw, 1.2f + tw, 0.6f,
                        MenuColors.withAlpha(teal, (int) ((16.0f + 50.0f * tw) * a)));
            }
            float jy = y + h * (0.9f - ((time / 9000.0f) % 1.0f) * 0.7f);
            float pulse = 0.5f + 0.5f * (float) Math.sin(time / 1100.0f);
            Render2D.arcRect(bx, jy, 5.0f + pulse * 2.0f, 1.1f, 180.0f, 180.0f, MenuColors.withAlpha(teal, (int) (70 * a)));
        } else if (src.startsWith("style:lava")) {
            Render2D.rect(x, y, w, h, 0.0f, base, base, base, base);
            int hot = ThemeManager.mix(accent, 0xFF6A2A, 0.55f);
            int hotCore = ThemeManager.mix(hot, 0xFFE9B0, 0.55f);
            float beat = 0.5f + 0.5f * (float) Math.sin(time / 1400.0f);
            for (int c = 0; c < 3; c++) {
                float y0 = y + h * (0.25f + 0.3f * c);
                float lx = x - 3.0f;
                float ly = y0;
                for (int s = 1; s <= 6; s++) {
                    float nx = x + (w + 6.0f) * s / 6.0f;
                    float ny = y0 + (float) Math.sin(c * 7.0f + s * 1.2f) * h * 0.06f;
                    float seg = beat * (0.35f + 0.65f * ((s + c) % 3 == 0 ? 1.0f : 0.45f));
                    Render2D.line(lx, ly, nx, ny, 1.3f, MenuColors.withAlpha(hot, (int) ((30.0f + 90.0f * seg) * a)));
                    Render2D.line(lx, ly, nx, ny, 0.6f, MenuColors.withAlpha(hotCore, (int) ((40.0f + 150.0f * seg) * a)));
                    lx = nx;
                    ly = ny;
                }
            }
            for (int i = 0; i < 4; i++) {
                float phase = (time / (2400.0f + i * 300.0f) + i * 0.27f) % 1.0f;
                Render2D.circle(x + w * (0.2f + 0.2f * i) + (float) Math.sin(time / 700.0f + i) * 4.0f,
                        y + h * (1.0f - phase), 1.0f + phase, 6, MenuColors.withAlpha(hotCore, (int) ((60.0f + 80.0f * phase) * a)));
            }
        } else {
            // Аврора и Глубина — прежний градиент
            drawGradient(x, y, w, h, a);
        }
    }

    public static void drawPicture(float sw, float sh, float a) {
        drawCover(BG, 0.0f, 0.0f, sw, sh, 1.7778f, MenuColors.withAlpha(0xFFFFFFFF, (int) (255.0f * a)));
    }

    /** Тёмный фон для экрана «Оформление меню» — без картинки главного меню. */
    public static void drawDarkBackdrop(float sw, float sh, float a) {
        updateInteractive(sw, sh);
        long now = System.currentTimeMillis();
        float drift = 0.5f + 0.5f * (float) Math.sin(now / 5200.0);
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;

        int top = MenuColors.rgba(10, 11, 16, 255);
        int bottom = MenuColors.rgba(6, 7, 10, 255);
        Render2D.rect(0.0f, 0.0f, sw, sh, 0.0f, top, top, bottom, bottom);

        // очень деликатное дыхание акцента, фон остаётся тёмным
        Render2D.rect(0.0f, 0.0f, sw, sh, 0.0f,
                MenuColors.withAlpha(accent, 0),
                MenuColors.withAlpha(accent, (int) ((10 + 6 * drift) * a)),
                MenuColors.withAlpha(accent, (int) ((14 + 8 * drift) * a)),
                MenuColors.withAlpha(accent, (int) ((6 + 4 * drift) * a)));

        // лёгкое свечение за курсором, чтобы экран тоже был живым
        softCircle(glowX, glowY, sh * 0.34f, accent, (int) (13 * a), (int) (3 * a));
    }

    public static void drawGradient(float sw, float sh, float a) {
        long now = System.currentTimeMillis();
        float wave = 0.5f + 0.5f * (float) Math.sin(now / 2400.0);
        float wave2 = 0.5f + 0.5f * (float) Math.sin(now / 3800.0 + 2.1);
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;

        int top = ThemeManager.mix(MenuColors.rgba(8, 10, 15, 255), accent, 0.16f + 0.12f * wave);
        int bottom = MenuColors.withAlpha(accent, (int) ((120 + 60 * wave2) * a));

        Render2D.rect(0.0f, 0.0f, sw, sh, 0.0f, top, top, bottom, bottom);

        float bandY = sh * (0.25f + 0.55f * wave);
        float bandH = sh * 0.42f;
        Render2D.rect(0.0f, bandY, sw, bandH, 0.0f,
                MenuColors.withAlpha(accent, 0),
                MenuColors.withAlpha(accent, (int) (26 * a)),
                MenuColors.withAlpha(accent, (int) (34 * a)),
                MenuColors.withAlpha(accent, (int) (10 * a)));
    }

    /** Локальный градиент (для превью карточек в настройках). */
    public static void drawGradient(float x, float y, float w, float h, float a) {
        if (w <= 0.0f || h <= 0.0f) {
            return;
        }
        long now = System.currentTimeMillis();
        float wave = 0.5f + 0.5f * (float) Math.sin(now / 2400.0);
        float wave2 = 0.5f + 0.5f * (float) Math.sin(now / 3800.0 + 2.1);
        int accent = ThemeManager.accentOpaque() & 0xFFFFFF;

        int top = ThemeManager.mix(MenuColors.rgba(8, 10, 15, 255), accent, 0.16f + 0.12f * wave);
        int bottom = MenuColors.withAlpha(accent, (int) ((120 + 60 * wave2) * a));

        Render2D.rect(x, y, w, h, 0.0f, top, top, bottom, bottom);
    }

    public static void drawCover(String tex, float sw, float sh, float aspect, int color) {
        drawCover(tex, 0.0f, 0.0f, sw, sh, aspect, color);
    }

    public static void drawCover(String tex, float x, float y, float w, float h, float aspect, int color) {
        drawCover(tex, x, y, w, h, aspect, color, 0.0f);
    }

    /** Обложка со скруглением углов: картинка сама обрезается радиусом (у image-рендерера есть нативная поддержка). */
    public static void drawCover(String tex, float x, float y, float w, float h, float aspect, int color, float radius) {
        if (tex == null || aspect <= 0.0f || w <= 0.0f || h <= 0.0f) {
            return;
        }
        float u0 = 0.0f;
        float v0 = 0.0f;
        float u1 = 1.0f;
        float v1 = 1.0f;
        float rectAspect = w / h;
        if (rectAspect > aspect) {
            float visible = aspect / rectAspect;
            v0 = (1.0f - visible) * 0.5f;
            v1 = v0 + visible;
        } else {
            float visible = rectAspect / aspect;
            u0 = (1.0f - visible) * 0.5f;
            u1 = u0 + visible;
        }
        Render2D.imageUv(tex, x, y, w, h, Math.max(0.0f, radius), 0.0f, u0, v0, u1, v1, color);
    }
}
