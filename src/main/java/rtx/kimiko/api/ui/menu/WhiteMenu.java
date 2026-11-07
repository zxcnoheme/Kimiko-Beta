package rtx.kimiko.api.ui.menu;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.text.Text;
import rtx.kimiko.api.ui.menu.alts.WhiteAltManager;
import rtx.kimiko.api.ui.mainmenu.MenuBackgrounds;
import rtx.kimiko.api.ui.mainmenu.MenuSettings;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.api.ui.theme.ThemeManager;
import rtx.kimiko.utils.animations.Easings;
import rtx.kimiko.utils.animations.SmoothAnimation;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.glass.BuiltGlass;
import rtx.kimiko.utils.sounds.SoundManager;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WhiteMenu extends Screen {
    private final SmoothAnimation alpha = new SmoothAnimation();
    private final List<MenuBtn> buttons = new ArrayList<>();
    private final SmoothAnimation accHover = new SmoothAnimation();
    private float appliedScale = -1.0f;
    private long lastScaleNanos = 0L;

    private MenuBtn multiplayerBtn;
    private MenuBtn singleplayerBtn;
    private MenuBtn accountBtn;
    private MenuBtn customizeBtn;
    private MenuBtn optionsBtn;
    private MenuBtn quitBtn;

    private float scaleFix = 1.0f;
    private float lastMouseX;
    private float lastMouseY;

    private float lastWidgetX;
    private float lastWidgetY;
    private float lastWidgetW;
    private float lastWidgetH;
    private float lastAccX;
    private float lastAccY;
    private float lastAccW;
    private float lastAccH;

    public WhiteMenu() {
        super(Text.literal("KimikoMenu"));
    }

    @Override
    protected void init() {
        this.alpha.setValue(0.0);
        this.alpha.run(1.0, 0.45, Easings.QUAD_OUT, false);
        WhiteAltManager.get().bootstrap();
        try {
            MenuBackgrounds.preload();
        } catch (Throwable ignored) {
        }
        this.buildButtons();
    }

    private void buildButtons() {
        this.buttons.clear();
        MinecraftClient mc = MinecraftClient.getInstance();
        this.multiplayerBtn = new MenuBtn(MenuBtn.STYLE_PRIMARY, "Multiplayer",
                () -> mc.setScreen(new MultiplayerScreen(this)));
        this.singleplayerBtn = new MenuBtn(MenuBtn.STYLE_GLASS, "Singleplayer",
                () -> mc.setScreen(new SelectWorldScreen(this)));
        this.accountBtn = new MenuBtn(MenuBtn.STYLE_GLASS, "Сменить аккаунт",
                () -> mc.setScreen(new WhiteAltScreen(this)));
        this.customizeBtn = new MenuBtn(MenuBtn.STYLE_GLASS, "Оформление меню",
                () -> mc.setScreen(new WhiteMenuCustomizeScreen(this)));
        this.optionsBtn = new MenuBtn(MenuBtn.STYLE_SPLIT_OPTIONS, "Options...",
                () -> mc.setScreen(new OptionsScreen(this, mc.options)));
        this.quitBtn = new MenuBtn(MenuBtn.STYLE_SPLIT_QUIT, "Quit Game",
                () -> mc.scheduleStop());
        this.buttons.add(this.multiplayerBtn);
        this.buttons.add(this.singleplayerBtn);
        this.buttons.add(this.accountBtn);
        this.buttons.add(this.customizeBtn);
        this.buttons.add(this.optionsBtn);
        this.buttons.add(this.quitBtn);
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null || mc.getWindow() == null) {
            return;
        }
        float targetScale = 2.0f;
        float currentScale = (float) mc.getWindow().getScaleFactor();
        this.scaleFix = targetScale / currentScale;
        float mx = (float) (mouseX / this.scaleFix);
        float my = (float) (mouseY / this.scaleFix);
        this.lastMouseX = mx;
        this.lastMouseY = my;
        WhiteMenuBackground.trackMouse(mx, my);
        this.alpha.update();
        float a = (float) this.alpha.getValue();

        int sw = (int) (mc.getWindow().getScaledWidth() / this.scaleFix);
        int sh = (int) (mc.getWindow().getScaledHeight() / this.scaleFix);
        float k = Math.max(0.85f, sh / 540.0f) * this.smoothScale();
        k = Math.max(0.62f, Math.min(2.2f, k));

        Render2D.beginFrame(context);
        float rise = (1.0f - a) * 18.0f * k;

        WhiteMenuBackground.draw(sw, sh, a);
        this.drawBloops(k, a);

        String username;
        MinecraftClient mcClient = MinecraftClient.getInstance();
        if (mcClient != null && mcClient.getSession() != null && !mcClient.getSession().getUsername().isBlank()) {
            username = mcClient.getSession().getUsername();
        } else {
            username = "Kimiko";
        }
        if (mc.getSession() != null && mc.getSession().getUsername() != null && !mc.getSession().getUsername().isEmpty()) {
            username = mc.getSession().getUsername();
        }

        this.drawAccountCard(context, sw, sh, a, k, username);
        this.drawClockWidget(sw, sh, a, k);
        this.drawHeroSection(sw, sh, a, k, rise, username);

        Render2D.flush();
    }

    private void drawAccountCard(DrawContext context, int sw, int sh, float a, float k, String username) {
        float accX = 32.0f * k;
        float accY = 24.0f * k;
        float avSize = 30.0f * k;
        float avRad = 6.0f * k;

        // ник не длиннее 15 символов; панель расширяется под длинный ник
        String nick = username == null || username.isBlank() ? "Player" : username;
        if (nick.length() > 15) {
            nick = nick.substring(0, 15);
        }
        float nickW = Fonts.BOLD.width(nick, 11.0f * k);
        this.lastAccX = accX;
        this.lastAccY = accY;
        this.lastAccW = Math.max(avSize + 130.0f * k, avSize + 12.0f * k + nickW + 14.0f * k);
        this.lastAccH = avSize;

        boolean hov = WhiteMenu.isHovered(this.lastMouseX, this.lastMouseY, accX, accY, this.lastAccW, this.lastAccH);
        this.accHover.run(hov ? 1.0 : 0.0, 0.16, Easings.QUAD_OUT, false);
        this.accHover.update();
        float hp = (float) this.accHover.getValue();
        boolean glass = MenuSettings.liquidGlass();

        int accent = ThemeManager.accent(255);
        if (glass) {
            WhiteMenu.drawLiquidGlass(accX, accY, this.lastAccW, avSize, avRad, a, hp,
                    MenuColors.rgba(255, 255, 255, (int) (40 * a)), 0.40f, 9.0f * k);
        } else {
            Render2D.rect(accX, accY, this.lastAccW, avSize, avRad, MenuColors.rgba(255, 255, 255, (int) ((18 + hp * 14) * a)));
            Render2D.rect(accX, accY, this.lastAccW, avSize, avRad,
                    MenuColors.withAlpha(accent, (int) ((6 + 26 * hp) * a)),
                    MenuColors.withAlpha(accent, 0),
                    MenuColors.withAlpha(accent, 0),
                    MenuColors.withAlpha(accent, (int) ((4 + 18 * hp) * a)));
            Render2D.outline(accX, accY, this.lastAccW, avSize, avRad, 1.0f,
                    hov ? MenuColors.withAlpha(accent, (int) (170 * a)) : MenuColors.rgba(255, 255, 255, (int) ((32 + hp * 26) * a)));
        }

        MinecraftClient mc = MinecraftClient.getInstance();
        UUID uuid = null;
        if (mc != null && mc.getSession() != null) {
            try {
                uuid = mc.getSession().getUuidOrNull();
            } catch (Throwable ignored) {
            }
        }
        SkinHeads.drawHead(context, nick, uuid, accX, accY, avSize);

        // ник по вертикальному центру карточки, без подписи «Текущий аккаунт»
        float tx = accX + avSize + 10.0f * k;
        float nickY = accY + (avSize - 11.0f * k) / 2.0f;
        Fonts.BOLD.draw(nick, tx, nickY, 11.0f * k, MenuColors.rgba(255, 255, 255, (int) (255 * a)));
    }

    private void drawClockWidget(int sw, int sh, float a, float k) {
        float widgetW = 138.0f * k;
        float widgetH = 44.0f * k;
        float widgetX = sw - widgetW - 24.0f * k;
        float widgetY = 24.0f * k;
        float widgetRad = 12.0f * k;

        this.lastWidgetX = widgetX;
        this.lastWidgetY = widgetY;
        this.lastWidgetW = widgetW;
        this.lastWidgetH = widgetH;

        LocalTime now = LocalTime.now();
        int accent = ThemeManager.accent(255);
        boolean glass = MenuSettings.liquidGlass();

        if (glass) {
            WhiteMenu.drawLiquidGlass(widgetX, widgetY, widgetW, widgetH, widgetRad, a, 0.0f,
                    MenuColors.rgba(255, 255, 255, (int) (44 * a)), 0.42f, 9.0f * k);
        } else {
            Render2D.blur(widgetX, widgetY, widgetW, widgetH, widgetRad, 26.0f);
            Render2D.rect(widgetX, widgetY, widgetW, widgetH, widgetRad, MenuColors.rgba(11, 13, 19, (int) (200 * a)));
            Render2D.rect(widgetX, widgetY, widgetW, widgetH, widgetRad,
                    MenuColors.withAlpha(accent, (int) (22 * a)),
                    MenuColors.withAlpha(accent, (int) (8 * a)),
                    MenuColors.withAlpha(accent, (int) (12 * a)),
                    MenuColors.withAlpha(accent, (int) (20 * a)));
            Render2D.outline(widgetX, widgetY, widgetW, widgetH, widgetRad, 1.0f, MenuColors.rgba(255, 255, 255, (int) (28 * a)));
        }

        // ——— время и дата по центру виджета (без циферблата и полоски)
        float timeSize = 14.0f * k;
        float dateSize = 7.5f * k;
        float textX = widgetX + widgetW * 0.5f;
        String timeStr = String.format("%02d:%02d", now.getHour(), now.getMinute());
        float timeW = Fonts.EXTRABOLD.width(timeStr, timeSize);
        Fonts.EXTRABOLD.draw(timeStr, textX - timeW * 0.5f, widgetY + (widgetH - timeSize - dateSize - 3.0f * k) * 0.5f,
                timeSize, MenuColors.rgba(255, 255, 255, (int) (255 * a)));

        java.time.LocalDate date = java.time.LocalDate.now();
        String[] dayShorts = {"пн", "вт", "ср", "чт", "пт", "сб", "вс"};
        String[] monthShorts = {"янв", "фев", "мар", "апр", "мая", "июн", "июл", "авг", "сен", "окт", "ноя", "дек"};
        String dateStr = dayShorts[(date.getDayOfWeek().getValue() + 6) % 7] + ", "
                + date.getDayOfMonth() + " " + monthShorts[date.getMonthValue() - 1];
        float dateW = Fonts.REGULAR.width(dateStr, dateSize);
        Fonts.REGULAR.draw(dateStr, textX - dateW * 0.5f,
                widgetY + (widgetH - timeSize - dateSize - 3.0f * k) * 0.5f + timeSize + 3.0f * k,
                dateSize, MenuColors.rgba(255, 255, 255, (int) (150 * a)));
    }

    private void drawHeroSection(int sw, int sh, float a, float k, float rise, String username) {
        float leftX = sw * 0.045f;
        float heroY = sh * 0.32f + rise;

        String tag = "РАД ВИДЕТЬ, " + username.toUpperCase();
        Fonts.BOLD.draw(tag, leftX, heroY, 9.5f * k, MenuColors.withAlpha(ThemeManager.accent(255), (int) (255 * a)));

        float titleY = heroY + 16.0f * k;
        float titleSize = 25.0f * k;
        Fonts.EXTRABOLD.draw("Сборка собрана и прогрета —", leftX, titleY, titleSize, MenuColors.rgba(255, 255, 255, (int) (255 * a)));
        Fonts.EXTRABOLD.draw("это Kimiko.", leftX, titleY + titleSize + 5.0f * k, titleSize, MenuColors.rgba(255, 255, 255, (int) (255 * a)));

        float subY = titleY + titleSize * 2.0f + 16.0f * k;
        Fonts.REGULAR.draw("Куда сегодня — на сервер или в свой мир?", leftX, subY, 11.0f * k, MenuColors.rgba(255, 255, 255, (int) (165 * a)));

        float btnY = subY + 20.0f * k;
        float btnW = 270.0f * k;
        float btnH = 38.0f * k;
        float btnGap = 8.0f * k;

        // положение блока кнопок согласно настройке «Кнопки»
        String layout = MenuSettings.buttonLayout();
        float btnX;
        if ("center".equals(layout)) {
            btnX = (sw - btnW) / 2.0f;
        } else if ("right".equals(layout)) {
            btnX = sw - btnW - leftX;
        } else {
            btnX = leftX;
        }

        for (MenuBtn b : this.buttons) {
            b.update(this.lastMouseX, this.lastMouseY);
        }

        float y1 = btnY;
        this.multiplayerBtn.draw(btnX, y1, btnW, btnH, a, k);

        float y2 = y1 + btnH + btnGap;
        this.singleplayerBtn.draw(btnX, y2, btnW, btnH, a, k);

        float y3 = y2 + btnH + btnGap;
        this.accountBtn.draw(btnX, y3, btnW, btnH, a, k);

        float y4 = y3 + btnH + btnGap;
        this.customizeBtn.draw(btnX, y4, btnW, btnH, a, k);

        float y5 = y4 + btnH + btnGap;
        float wOptions = (btnW - 8.0f * k) * 0.55f;
        float wQuit = (btnW - 8.0f * k) * 0.45f;
        this.optionsBtn.draw(btnX, y5, wOptions, btnH, a, k);
        this.quitBtn.draw(btnX + wOptions + 8.0f * k, y5, wQuit, btnH, a, k);
    }

    @Override
    public boolean mouseClicked(Click click, boolean doubled) {
        float mx = (float) (click.x() / this.scaleFix);
        float my = (float) (click.y() / this.scaleFix);

        if (click.button() == 0) {
            MinecraftClient mc = MinecraftClient.getInstance();

            if (isHovered(mx, my, this.lastAccX, this.lastAccY, this.lastAccW, this.lastAccH)) {
                SoundManager.playSoundDirect(SoundManager.BUTTON_CLICK, 0.6f, 1.0f);
                spawnBloop(mx, my);
                if (mc != null) {
                    mc.setScreen(new WhiteAltScreen(this));
                }
                return true;
            }

            for (MenuBtn btn : this.buttons) {
                if (btn.isHovered()) {
                    SoundManager.playSoundDirect(SoundManager.BUTTON_CLICK, 0.6f, 1.0f);
                    spawnBloop(mx, my);
                    btn.onClick();
                    return true;
                }
            }
        }
        return super.mouseClicked(click, doubled);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    public static boolean isHovered(float mx, float my, float x, float y, float w, float h) {
        return mx >= x && mx <= x + w && my >= y && my <= y + h;
    }

    // ——— «БУЛЬК»: турбулентное желе-всплытие при клике по кнопке (Liquid glass) ———

    private static final int BLOOP_MAX = 10;
    private static final float[] bloopX = new float[BLOOP_MAX];
    private static final float[] bloopY = new float[BLOOP_MAX];
    private static final float[] bloopAge = new float[BLOOP_MAX];
    private static final float[] bloopPhase = new float[BLOOP_MAX];
    private static final boolean[] bloopAlive = new boolean[BLOOP_MAX];
    private static int bloopCursor = 0;
    private static long bloopNanos;

    /** Спавнит «бульк» в точке клика (только в режиме Liquid glass). */
    private static void spawnBloop(float x, float y) {
        if (!MenuSettings.liquidGlass()) {
            return;
        }
        int i = bloopCursor++ % BLOOP_MAX;
        bloopX[i] = x;
        bloopY[i] = y;
        bloopAge[i] = 0.0f;
        bloopPhase[i] = i * 0.73f;
        bloopAlive[i] = true;
    }

    /**
     * «Жидкое стекло»: glass-шейдер сам захватывает блюрнутый бэкдроп, прячет края
     * рефракцией (лупа), подсвечивает кромку френелем и слегка хроматит. tint — цвет
     * подливки, baseOp — базовая плотность, blurPx — радиус мороза, lift — 0..1 по ховеру.
     */
    private static void drawLiquidGlass(float x, float y, float w, float h, float rad, float a, float lift,
                                        int tintCol, float baseOp, float blurPx) {
        if (w <= 0.0f || h <= 0.0f || a <= 0.0f) {
            return;
        }
        float maxR = Math.min(w, h) * 0.5f;
        float r = Math.max(0.0f, Math.min(rad, maxR));
        float baseBlur = Math.max(2.0f, blurPx);
        float dens = 0.55f + 0.9f * MenuSettings.glassDensity(); // 0.5 → ×1.0, настройка «Плотность стекла»
        BuiltGlass glass = new BuiltGlass(
                x, y, w, h,
                r, r, r, r,
                tintCol,
                a * (0.42f + 0.20f * lift),
                55.0f,
                0xFFFFFFFF,
                a * (baseOp * dens + 0.14f * lift),
                true,
                0.26f + 0.14f * lift,
                0.22f,
                0.5f,
                0.0f
        ).withBlurRadius(baseBlur).withSecondColor(tintCol, 0.0f);
        Render2D.glass(glass);
    }

    /** Круги на воде от кликов: желе-кольца, ядро и капли. */
    private void drawBloops(float k, float a) {
        long now = System.nanoTime();
        float dt = bloopNanos == 0L ? 0.033f : Math.min(0.05f, (float) ((now - bloopNanos) / 1.0E9));
        bloopNanos = now;
        if (!MenuSettings.liquidGlass()) {
            return;
        }

        int accent = ThemeManager.accent(255);
        for (int i = 0; i < BLOOP_MAX; i++) {
            if (!bloopAlive[i]) {
                continue;
            }
            bloopAge[i] += dt * 1.5f;
            if (bloopAge[i] >= 1.0f) {
                bloopAlive[i] = false;
                continue;
            }
            float age = bloopAge[i];
            float fade = 1.0f - age;
            float R = (16.0f + age * 92.0f) * k;
            float cx = bloopX[i];
            float cy = bloopY[i] - age * 14.0f * k; // всплывает вверх, как пузырь

            // желе-кольцо: 8 дуг с турбулентной волной радиуса
            for (int s = 0; s < 8; s++) {
                float start = s * 45.0f;
                float angRad = (float) Math.toRadians(start + 22.5f);
                float wobble = 0.09f * (float) Math.sin(angRad * 3.0f + bloopPhase[i] + age * 5.0f)
                        + 0.05f * (float) Math.sin(angRad * 5.0f - age * 7.0f);
                float r = R * (1.0f + wobble);
                Render2D.arcRect(cx, cy, r, 1.8f * fade + 0.4f, start, 43.0f,
                        MenuColors.rgba(255, 255, 255, (int) ((26.0f + 64.0f * fade) * a)));
            }

            // ядро: пузырь сжимается
            float blobR = R * (0.52f - age * 0.30f);
            Render2D.circle(cx, cy, Math.max(1.0f, blobR), 22, MenuColors.rgba(255, 255, 255, (int) (16 * fade)));
            Render2D.circle(cx, cy, Math.max(1.0f, blobR * 0.4f), 14, MenuColors.rgba(255, 255, 255, (int) (26 * fade)));

            // пара капель разлетается
            for (int d = 0; d < 3; d++) {
                float dir = bloopPhase[i] + d * 2.094f;
                float dd = R * (0.55f + age * 0.85f);
                Render2D.circle(cx + (float) Math.cos(dir) * dd, cy + (float) Math.sin(dir) * dd * 0.7f,
                        Math.max(0.8f, 2.6f * k * fade), 8,
                        MenuColors.withAlpha(accent, (int) (70.0f * fade)));
            }
        }
    }

    /** Плавно догоняет целевой масштаб интерфейса — без резких скачков при перетаскивании ползунка. */
    private float smoothScale() {
        float target = MenuSettings.uiScale();
        long now = System.nanoTime();
        float dt = this.lastScaleNanos == 0L ? 0.033f : Math.min(0.05f, (float) ((now - this.lastScaleNanos) / 1.0E9));
        this.lastScaleNanos = now;
        if (this.appliedScale <= 0.0f) {
            this.appliedScale = target;
            return target;
        }
        this.appliedScale += (target - this.appliedScale) * Math.min(1.0f, dt * 7.0f);
        if (Math.abs(this.appliedScale - target) < 0.0005f) {
            this.appliedScale = target;
        }
        return this.appliedScale;
    }

    private static class MenuBtn {
        static final int STYLE_PRIMARY = 0;
        static final int STYLE_GLASS = 1;
        static final int STYLE_SPLIT_OPTIONS = 2;
        static final int STYLE_SPLIT_QUIT = 3;

        private final int style;
        private final String text;
        private final Runnable action;
        private final SmoothAnimation hoverAnim = new SmoothAnimation();

        private boolean hovered;
        private float lastX;
        private float lastY;
        private float lastW;
        private float lastH;

        MenuBtn(int style, String text, Runnable action) {
            this.style = style;
            this.text = text;
            this.action = action;
            this.hoverAnim.setValue(0.0);
        }

        void update(double mx, double my) {
            this.hovered = WhiteMenu.isHovered((float) mx, (float) my, this.lastX, this.lastY, this.lastW, this.lastH);
            this.hoverAnim.update();
            this.hoverAnim.run(this.hovered ? 1.0 : 0.0, 0.16, Easings.QUAD_OUT, false);
        }

        void draw(float x, float y, float w, float h, float a, float k) {
            this.lastX = x;
            this.lastY = y;
            this.lastW = w;
            this.lastH = h;

            float hp = (float) this.hoverAnim.getValue();
            float rad = 8.0f * k;
            float radG = MenuSettings.buttonRadius() * k; // скругление — настройка
            boolean glass = MenuSettings.liquidGlass();
            float dens = 0.55f + 0.9f * MenuSettings.glassDensity(); // 0.5 → ×1.0

            if (this.style == STYLE_PRIMARY) {
                if (glass) {
                    // жидкое стекло из клик-гуи (карточка включённого модуля) + настройка акцента
                    RectUtil.drawGlassCard(x, y, w, h, radG, radG, radG, radG, a * dens, hp);
                    float cx = x + w * 0.5f;
                    float cy = y + h * 0.5f;
                    float accTop = 20.0f + 80.0f * MenuSettings.primaryAccent(); // 0.5 → 60
                    Render2D.rect(x, y, w, h, radG, radG, radG, radG,
                            ClientAccent.gradientAAt((accTop + 26.0f * hp) * a, cx, cy),
                            ClientAccent.gradientAAt((accTop + 26.0f * hp) * a, cx, cy),
                            ClientAccent.gradientBAt((accTop * 0.7333f + 26.0f * hp) * a, cx, cy),
                            ClientAccent.gradientBAt((accTop * 0.7333f + 26.0f * hp) * a, cx, cy));
                    Render2D.outlineClient(x, y, w, h, radG, radG, radG, radG, 0.6f,
                            MenuColors.rgba(255, 255, 255, (int) ((52 + 26 * hp) * a)),
                            MenuColors.rgba(255, 255, 255, (int) ((52 + 26 * hp) * a)),
                            MenuColors.rgba(255, 255, 255, (int) ((22 + 16 * hp) * a)),
                            MenuColors.rgba(255, 255, 255, (int) ((22 + 16 * hp) * a)),
                            hp);
                } else {
                    int bgCol = MenuColors.withAlpha(ThemeManager.accent(255), (int) (255 * a));
                    Render2D.rect(x, y, w, h, rad, bgCol);
                    // мягкая тень снизу и лёгкий глянец сверху
                    Render2D.rect(x, y + h - 3.0f * k, w, 3.0f * k, 0.0f, 0.0f, rad, rad,
                            MenuColors.rgba(0, 0, 0, (int) (70 * a)));
                    Render2D.rect(x + 1.0f * k, y + 1.0f * k, w - 2.0f * k, h * 0.42f,
                            rad - 1.0f * k, rad - 1.0f * k, 0.0f, 0.0f,
                            MenuColors.rgba(255, 255, 255, (int) (24 * a)));
                }

                float textX = x + 18.0f * k;
                float textY = y + (h - 12.5f * k) / 2.0f - 0.5f * k;
                Fonts.SEMIBOLD.draw(this.text, textX, textY, 12.5f * k, MenuColors.rgba(255, 255, 255, (int) (255 * a)));
            } else {
                if (glass) {
                    // жидкое стекло из клик-гуи (карточка модуля): блик сверху + тонкая кромка
                    RectUtil.drawGlassCard(x, y, w, h, radG, radG, radG, radG, a * dens, hp);
                    Render2D.rect(x, y, w, h, radG, radG, radG, radG,
                            MenuColors.rgba(255, 255, 255, (int) ((7.0f + 5.0f * hp) * a)),
                            MenuColors.rgba(255, 255, 255, (int) ((7.0f + 5.0f * hp) * a)),
                            MenuColors.rgba(255, 255, 255, (int) (1.5f * a)),
                            MenuColors.rgba(255, 255, 255, (int) (1.5f * a)));
                    Render2D.outlineClient(x, y, w, h, radG, radG, radG, radG, 0.6f,
                            MenuColors.rgba(255, 255, 255, (int) ((30 + 22 * hp) * a)),
                            MenuColors.rgba(255, 255, 255, (int) ((30 + 22 * hp) * a)),
                            MenuColors.rgba(255, 255, 255, (int) ((10 + 8 * hp) * a)),
                            MenuColors.rgba(255, 255, 255, (int) ((10 + 8 * hp) * a)),
                            hp);
                } else {
                    int r = 28 + (int) (18 * hp);
                    int g = 30 + (int) (18 * hp);
                    int b = 38 + (int) (20 * hp);
                    int bgCol = MenuColors.rgba(r, g, b, (int) ((185 + hp * 45) * a));

                    Render2D.rect(x, y, w, h, rad, bgCol);
                    Render2D.outline(x, y, w, h, rad, 1.0f, MenuColors.rgba(255, 255, 255, (int) ((20 + hp * 32) * a)));
                    // акцентная линия при наведении
                    if (hp > 0.03f) {
                        float lw = (w - 24.0f * k) * Math.min(1.0f, hp * 1.35f);
                        Render2D.rect(x + 1.5f * k, y + h - 2.6f * k, lw, 1.8f * k, 0.9f * k,
                                MenuColors.withAlpha(ThemeManager.accent(255), (int) (150 * hp * a)));
                    }
                }

                if (this.style == STYLE_SPLIT_OPTIONS) {
                    float iconX = x + 12.0f * k;
                    float iconY = y + (h - 9.0f * k) / 2.0f;
                    Fonts.WHITE_ICONS.draw("U", iconX, iconY, 6.5f * k, MenuColors.rgba(255, 255, 255, (int) (245 * a)));
                    float textX = x + 26.0f * k;
                    float textY = y + (h - 11.5f * k) / 2.0f - 0.5f * k;
                    Fonts.SEMIBOLD.draw(this.text, textX, textY, 11.5f * k, MenuColors.rgba(255, 255, 255, (int) (255 * a)));
                } else if (this.style == STYLE_SPLIT_QUIT) {
                    float iconX = x + 12.0f * k;
                    float iconY = y + (h - 9.0f * k) / 2.0f;
                    Fonts.WHITE_ICONS.draw("W", iconX, iconY, 6.5f * k, MenuColors.rgba(255, 255, 255, (int) (245 * a)));
                    float textX = x + 26.0f * k;
                    float textY = y + (h - 11.5f * k) / 2.0f - 0.5f * k;
                    Fonts.SEMIBOLD.draw(this.text, textX, textY, 11.5f * k, MenuColors.rgba(255, 255, 255, (int) (255 * a)));
                } else {
                    float textX = x + 18.0f * k;
                    float textY = y + (h - 12.5f * k) / 2.0f - 0.5f * k;
                    Fonts.SEMIBOLD.draw(this.text, textX, textY, 12.5f * k, MenuColors.rgba(255, 255, 255, (int) (255 * a)));
                }
            }
        }

        boolean isHovered() {
            return this.hovered;
        }

        void onClick() {
            if (this.action != null) {
                this.action.run();
            }
        }
    }
}
