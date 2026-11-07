package rtx.kimiko.api.ui.menu;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.AccessibilityOptionsScreen;
import net.minecraft.client.gui.screen.option.ChatOptionsScreen;
import net.minecraft.client.gui.screen.option.KeybindsScreen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import rtx.kimiko.api.ui.mainmenu.MenuSettings;
import rtx.kimiko.api.ui.theme.ThemeManager;
import rtx.kimiko.utils.animations.Easings;
import rtx.kimiko.utils.animations.SmoothAnimation;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.sounds.SoundManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Кастомное меню настроек Minecraft в стиле белого меню Kimiko.
 * Подменяется вместо ванильного OptionsScreen (mixin OptionsScreenMixin).
 * Быстрые опции — свои слайдеры/тумблеры поверх GameOptions;
 * сложные разделы (графика, управление и т.д.) открывают ванильные подэкраны.
 */
public class WhiteOptionsScreen extends Screen {
    private final Screen parent;
    private final SmoothAnimation alpha = new SmoothAnimation();

    private float scaleFix = 1.0f;
    private float lastMouseX;
    private float lastMouseY;

    // скролл
    private float scroll;
    private float scrollTarget;
    private float contentBottom;
    private float viewTop;
    private float viewH;
    private float lastDt = 0.033f;

    // геометрия панелей/кнопок (дизайн-пространство)
    private float backX;
    private float backY;
    private float backW;
    private float backH;
    private float doneX;
    private float doneY;
    private float doneW;
    private float doneH;

    private static final int SLIDERS = 5;
    private static final int TOGGLES = 4;
    private static final int NAVS = 6;
    private final float[] slX = new float[SLIDERS];
    private final float[] slY = new float[SLIDERS];
    private final float[] slW = new float[SLIDERS];
    private final float[] slH = new float[SLIDERS];
    private final float[] tgX = new float[TOGGLES];
    private final float[] tgY = new float[TOGGLES];
    private final float[] tgW = new float[TOGGLES];
    private final float[] tgH = new float[TOGGLES];
    private final List<float[]> navHits = new ArrayList<>();
    private int dragIdx = -1;

    public WhiteOptionsScreen(Screen parent) {
        super(Text.literal("KimikoOptions"));
        this.parent = parent;
    }

    private GameOptions options() {
        MinecraftClient mc = MinecraftClient.getInstance();
        return mc != null ? mc.options : null;
    }

    @Override
    protected void init() {
        this.alpha.setValue(0.0);
        this.alpha.run(1.0, 0.4, Easings.QUAD_OUT, false);
        this.scroll = 0.0f;
        this.scrollTarget = 0.0f;
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public void close() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc != null) {
            mc.setScreen(this.parent != null ? this.parent : new WhiteMenu());
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null || mc.getWindow() == null) {
            return;
        }
        this.lastDt = Math.max(0.0f, Math.min(2.0f, delta));
        float currentScale = (float) mc.getWindow().getScaleFactor();
        this.scaleFix = 2.0f / currentScale;
        this.lastMouseX = (float) (mouseX / this.scaleFix);
        this.lastMouseY = (float) (mouseY / this.scaleFix);
        WhiteMenuBackground.trackMouse(this.lastMouseX, this.lastMouseY);
        this.alpha.update();
        float a = (float) this.alpha.getValue();
        if (this.dragIdx >= 0) {
            this.applySlider(this.dragIdx, this.lastMouseX);
        }

        int sw = (int) (mc.getWindow().getScaledWidth() / this.scaleFix);
        int sh = (int) (mc.getWindow().getScaledHeight() / this.scaleFix);
        float k = Math.max(0.85f, sh / 540.0f);
        k = Math.max(0.62f, Math.min(2.2f, k));

        Render2D.beginFrame(context);

        WhiteMenuBackground.drawDarkBackdrop(sw, sh, a);
        Render2D.rect(0.0f, 0.0f, sw, sh, 0.0f,
                MenuColors.rgba(8, 10, 15, (int) (120 * a)),
                MenuColors.rgba(8, 10, 15, (int) (90 * a)),
                MenuColors.rgba(8, 10, 15, (int) (60 * a)),
                MenuColors.rgba(8, 10, 15, (int) (90 * a)));

        // ——— шапка
        float leftX = sw * 0.045f;
        float heroY = sh * 0.09f;
        Fonts.BOLD.draw("ИГРА", leftX, heroY, 9.5f * k,
                MenuColors.withAlpha(ThemeManager.accent(255), (int) (255 * a)));
        Fonts.EXTRABOLD.draw("Настройки Minecraft", leftX, heroY + 16.0f * k, 25.0f * k,
                MenuColors.rgba(255, 255, 255, (int) (255 * a)));
        Fonts.REGULAR.draw("Основное — здесь, тонкая настройка — в разделах ниже",
                leftX, heroY + 16.0f * k + 25.0f * k + 8.0f * k, 11.0f * k,
                MenuColors.rgba(255, 255, 255, (int) (165 * a)));

        // ——— назад
        float bw = 84.0f * k;
        float bh = 26.0f * k;
        this.backW = bw;
        this.backH = bh;
        this.backX = leftX;
        this.backY = heroY + 16.0f * k + 25.0f * k + 8.0f * k + 6.0f * k;
        this.drawNavCard(this.backX, this.backY, this.backW, this.backH, "Назад", a, k, false);

        // ——— панель
        float panelW = Math.min(660.0f * k, sw - 40.0f);
        float panelX = (sw - panelW) / 2.0f;
        float panelY = this.backY + bh + 18.0f * k;
        float panelH = sh - panelY - 26.0f * k;
        float panelRad = 14.0f * k;

        this.drawGlassPanel(panelX, panelY, panelW, panelH, panelRad, a, k);

        float pad = 14.0f * k;
        float contentX = panelX + pad;
        float contentW = panelW - pad * 2.0f;
        this.viewTop = panelY + pad;
        this.viewH = panelY + panelH - pad - this.viewTop;

        // скролл-догон
        float maxScroll = Math.max(0.0f, this.contentBottom - this.viewH);
        this.scrollTarget = Math.max(0.0f, Math.min(maxScroll, this.scrollTarget));
        this.scroll += (this.scrollTarget - this.scroll) * Math.min(1.0f, this.lastDt * 12.0f);

        Render2D.pushScissor(context, contentX, this.viewTop, contentW, this.viewH);

        float y = this.viewTop - this.scroll;
        this.navHits.clear();

        y = this.drawSection(contentX, contentW, y, a, k, "ВИД");
        y = this.drawSliderRow(contentX, contentW, y, a, k, 0, "Яркость", this.sliderValue01(0), this.sliderText(0));
        y = this.drawSliderRow(contentX, contentW, y, a, k, 1, "Поле зрения", this.sliderValue01(1), this.sliderText(1));
        y = this.drawSliderRow(contentX, contentW, y, a, k, 2, "Масштаб интерфейса", this.sliderValue01(2), this.sliderText(2));

        y = this.drawSection(contentX, contentW, y + 14.0f * k, a, k, "УПРАВЛЕНИЕ");
        y = this.drawSliderRow(contentX, contentW, y, a, k, 3, "Чувствительность мыши", this.sliderValue01(3), this.sliderText(3));
        y = this.drawToggleRow(contentX, contentW, y, a, k, 0, "Плавная камера");
        y = this.drawToggleRow(contentX, contentW, y, a, k, 1, "Автопрыжок");

        y = this.drawSection(contentX, contentW, y + 14.0f * k, a, k, "ЗВУК И ОКНО");
        y = this.drawSliderRow(contentX, contentW, y, a, k, 4, "Общая громкость", this.sliderValue01(4), this.sliderText(4));
        y = this.drawToggleRow(contentX, contentW, y, a, k, 2, "Полный экран");
        y = this.drawToggleRow(contentX, contentW, y, a, k, 3, "VSync");

        y = this.drawSection(contentX, contentW, y + 14.0f * k, a, k, "РАЗДЕЛЫ");
        y = this.drawNavGrid(contentX, contentW, y, a, k);

        this.contentBottom = y + 6.0f * k + this.scroll;

        Render2D.popScissor(context);

        // ——— готово
        float dw = 120.0f * k;
        float dh = 30.0f * k;
        this.doneW = dw;
        this.doneH = dh;
        this.doneX = panelX + panelW - dw - pad;
        this.doneY = panelY + panelH - dh - pad;
        this.drawDoneButton(this.doneX, this.doneY, this.doneW, this.doneH, a, k);

        Render2D.flush();
    }

    // ——— значения опций

    private float sliderValue01(int idx) {
        GameOptions o = this.options();
        if (o == null) {
            return 0.5f;
        }
        return switch (idx) {
            case 0 -> (float) (o.getGamma().getValue() / 1.0);
            case 1 -> (Math.round((Integer) o.getFov().getValue()) - 30.0f) / 80.0f;
            case 2 -> Math.round((Integer) o.getGuiScale().getValue()) / 4.0f;
            case 3 -> (float) (o.getMouseSensitivity().getValue() / 1.0);
            case 4 -> (float) (o.getSoundVolumeOption(SoundCategory.MASTER).getValue() / 1.0);
            default -> 0.5f;
        };
    }

    private String sliderText(int idx) {
        GameOptions o = this.options();
        if (o == null) {
            return "—";
        }
        return switch (idx) {
            case 0 -> Math.round(o.getGamma().getValue().floatValue() * 100.0f) + "%";
            case 1 -> {
                int fov = Math.round((Integer) o.getFov().getValue());
                yield fov == 70 ? "70 (Обычный)" : String.valueOf(fov);
            }
            case 2 -> {
                int s = Math.round((Integer) o.getGuiScale().getValue());
                yield s == 0 ? "Авто" : String.valueOf(s);
            }
            case 3 -> Math.round(o.getMouseSensitivity().getValue().floatValue() * 100.0f) + "%";
            case 4 -> Math.round(o.getSoundVolumeOption(SoundCategory.MASTER).getValue().floatValue() * 100.0f) + "%";
            default -> "—";
        };
    }

    private boolean toggleValue(int idx) {
        GameOptions o = this.options();
        if (o == null) {
            return false;
        }
        return switch (idx) {
            case 0 -> o.smoothCameraEnabled;
            case 1 -> o.getAutoJump().getValue();
            case 2 -> o.getFullscreen().getValue();
            case 3 -> o.getEnableVsync().getValue();
            default -> false;
        };
    }

    private void applySlider(int idx, float mx) {
        GameOptions o = this.options();
        if (o == null || this.slW[idx] <= 1.0f) {
            return;
        }
        float t = (mx - this.slX[idx]) / Math.max(1.0f, this.slW[idx]);
        t = Math.max(0.0f, Math.min(1.0f, t));
        switch (idx) {
            case 0 -> o.getGamma().setValue((double) t);
            case 1 -> o.getFov().setValue(Math.round(30.0f + t * 80.0f));
            case 2 -> o.getGuiScale().setValue(Math.round(t * 4.0f));
            case 3 -> o.getMouseSensitivity().setValue((double) t);
            case 4 -> o.getSoundVolumeOption(SoundCategory.MASTER).setValue((double) t);
            default -> {
            }
        }
    }

    private void toggleOption(int idx) {
        GameOptions o = this.options();
        if (o == null) {
            return;
        }
        switch (idx) {
            case 0 -> o.smoothCameraEnabled = !o.smoothCameraEnabled;
            case 1 -> o.getAutoJump().setValue(!o.getAutoJump().getValue());
            case 2 -> o.getFullscreen().setValue(!o.getFullscreen().getValue());
            case 3 -> o.getEnableVsync().setValue(!o.getEnableVsync().getValue());
            default -> {
            }
        }
    }

    private void openSection(int idx) {
        MinecraftClient mc = MinecraftClient.getInstance();
        GameOptions o = this.options();
        if (mc == null || o == null) {
            return;
        }
        try {
            switch (idx) {
                case 0 -> mc.setScreen(new VideoOptionsScreen(this, mc, o));
                case 1 -> mc.setScreen(new KeybindsScreen(this, o));
                case 2 -> mc.setScreen(new SoundOptionsScreen(this, o));
                case 3 -> mc.setScreen(new ChatOptionsScreen(this, o));
                case 4 -> mc.setScreen(new LanguageOptionsScreen(this, o, mc.getLanguageManager()));
                case 5 -> mc.setScreen(new AccessibilityOptionsScreen(this, o));
                default -> {
                }
            }
        } catch (Throwable t) {
            System.out.println("[KIMIKO] Не удалось открыть раздел настроек: " + t);
        }
    }

    // ——— отрисовка строк

    private float drawSection(float x, float w, float y, float a, float k, String title) {
        Render2D.rect(x, y + 1.0f * k, 2.5f * k, 9.0f * k, 1.25f * k,
                MenuColors.withAlpha(ThemeManager.accent(255), (int) (220 * a)));
        Fonts.BOLD.draw(title, x + 9.0f * k, y, 9.0f * k, MenuColors.rgba(255, 255, 255, (int) (150 * a)));
        return y + 18.0f * k;
    }

    private float drawSliderRow(float x, float w, float y, float a, float k, int idx, String name, float value01, String valueText) {
        Fonts.SEMIBOLD.draw(name, x, y, 10.0f * k, MenuColors.rgba(255, 255, 255, (int) (205 * a)));
        y += 15.0f * k;

        int accent = ThemeManager.accent(255);
        float trackY = y;
        float trackH = 6.0f * k;
        float labelW = Fonts.SEMIBOLD.width(valueText, 10.5f * k);
        float trackW = w - labelW - 14.0f * k;
        float frac = Math.max(0.0f, Math.min(1.0f, value01));

        boolean hov = this.dragIdx == idx
                || WhiteMenu.isHovered(this.lastMouseX, this.lastMouseY, x, trackY - 7.0f * k, w, trackH + 14.0f * k);

        this.slX[idx] = x;
        this.slY[idx] = trackY - 7.0f * k;
        this.slW[idx] = trackW;
        this.slH[idx] = trackH + 14.0f * k;

        Render2D.rect(x, trackY, trackW, trackH, trackH * 0.5f, MenuColors.rgba(255, 255, 255, (int) (16 * a)));
        float fillW = Math.max(trackH, trackW * frac);
        Render2D.rect(x, trackY, fillW, trackH, trackH * 0.5f,
                MenuColors.withAlpha(accent, (int) ((hov ? 255 : 225) * a)));

        float knobX = x + trackW * frac;
        float knobY = trackY + trackH * 0.5f;
        float knobR = 6.5f * k;
        if (hov) {
            Render2D.circle(knobX, knobY, knobR + 5.0f * k, 22, MenuColors.withAlpha(accent, (int) (46 * a)));
        }
        Render2D.circle(knobX, knobY, knobR, 22, MenuColors.rgba(255, 255, 255, (int) (255 * a)));
        Render2D.circle(knobX, knobY, knobR * 0.42f, 12, MenuColors.withAlpha(accent, (int) (255 * a)));

        Fonts.SEMIBOLD.draw(valueText, x + trackW + 14.0f * k, trackY + trackH * 0.5f - 5.5f * k, 10.5f * k,
                hov ? MenuColors.rgba(255, 255, 255, (int) (255 * a)) : MenuColors.rgba(255, 255, 255, (int) (185 * a)));

        return trackY + trackH + 14.0f * k;
    }

    private float drawToggleRow(float x, float w, float y, float a, float k, int idx, String name) {
        float rowH = 30.0f * k;
        this.tgX[idx] = x;
        this.tgY[idx] = y;
        this.tgW[idx] = w;
        this.tgH[idx] = rowH;

        boolean on = this.toggleValue(idx);
        boolean hov = WhiteMenu.isHovered(this.lastMouseX, this.lastMouseY, x, y, w, rowH);
        int accent = ThemeManager.accent(255);

        Render2D.rect(x, y, w, rowH, 8.0f * k, MenuColors.rgba(255, 255, 255, (int) ((hov ? 11 : 7) * a)));
        Fonts.SEMIBOLD.draw(name, x + 12.0f * k, y + (rowH - 10.0f * k) / 2.0f - 0.5f * k, 10.0f * k,
                MenuColors.rgba(255, 255, 255, (int) (215 * a)));

        // переключатель
        float sw = 34.0f * k;
        float sh = 16.0f * k;
        float sx = x + w - sw - 10.0f * k;
        float sy = y + (rowH - sh) / 2.0f;
        float frac = on ? 1.0f : 0.0f;
        Render2D.rect(sx, sy, sw, sh, sh * 0.5f,
                MenuColors.withAlpha(accent, (int) ((on ? 150 : 60) * a)));
        float knobR = sh * 0.5f - 2.0f * k;
        float knobX = on ? sx + sw - knobR - 2.0f * k : sx + knobR + 2.0f * k;
        float knobY = sy + sh * 0.5f;
        if (hov) {
            Render2D.circle(knobX, knobY, knobR + 3.0f * k, 16, MenuColors.rgba(255, 255, 255, (int) (30 * a)));
        }
        Render2D.circle(knobX, knobY, knobR, 16, MenuColors.rgba(255, 255, 255, (int) (245 * a)));

        return y + rowH + 6.0f * k;
    }

    private float drawNavGrid(float x, float w, float y, float a, float k) {
        String[] names = {"Графика", "Управление", "Звук", "Чат", "Язык", "Доступность"};
        float cardH = 30.0f * k;
        float gap = 8.0f * k;
        float cellW = (w - gap) / 2.0f;
        for (int i = 0; i < names.length; i++) {
            int col = i % 2;
            int row = i / 2;
            float cx = x + (cellW + gap) * col;
            float cy = y + (cardH + gap) * row;
            this.drawNavCard(cx, cy, cellW, cardH, names[i], a, k, true);
            this.navHits.add(new float[]{i, cx, cy, cellW, cardH});
        }
        return y + (cardH + gap) * ((names.length + 1) / 2) - gap;
    }

    private void drawNavCard(float x, float y, float w, float h, String text, float a, float k, boolean chevron) {
        boolean hov = WhiteMenu.isHovered(this.lastMouseX, this.lastMouseY, x, y, w, h);
        int accent = ThemeManager.accent(255);

        Render2D.rect(x, y, w, h, 8.0f * k, MenuColors.rgba(255, 255, 255, (int) ((hov ? 13 : 8) * a)));
        if (MenuSettings.liquidGlass()) {
            RectUtil.drawGlassCard(x, y, w, h, 8.0f * k, 8.0f * k, 8.0f * k, 8.0f * k, a * 0.55f, hov ? 0.5f : 0.0f);
        }
        Render2D.outline(x, y, w, h, 8.0f * k, 1.0f, MenuColors.rgba(255, 255, 255, (int) ((14 + (hov ? 16 : 0)) * a)));
        if (hov) {
            Render2D.rect(x + 1.5f * k, y + h - 2.4f * k, (w - 24.0f * k) * 0.6f, 1.6f * k, 0.8f * k,
                    MenuColors.withAlpha(accent, (int) (140 * a)));
        }

        float textW = Fonts.SEMIBOLD.width(text, 11.0f * k);
        float tx = chevron ? x + (w - textW - 10.0f * k) * 0.5f : x + (w - textW) * 0.5f;
        Fonts.SEMIBOLD.draw(text, tx, y + (h - 11.0f * k) / 2.0f - 0.5f * k, 11.0f * k,
                MenuColors.rgba(255, 255, 255, (int) (245 * a)));
        if (chevron) {
            // маленькая стрелка вправо
            float ax = tx + textW + 3.0f * k;
            float ay = y + h * 0.5f;
            Render2D.rect(ax, ay - 0.7f * k, 4.5f * k, 1.4f * k, 0.7f * k,
                    MenuColors.rgba(255, 255, 255, (int) (120 * a)));
            Render2D.rect(ax + 3.2f * k, ay - 2.4f * k, 1.4f * k, 1.4f * k, 0.7f * k,
                    MenuColors.rgba(255, 255, 255, (int) (120 * a)));
            Render2D.rect(ax + 3.2f * k, ay + 1.0f * k, 1.4f * k, 1.4f * k, 0.7f * k,
                    MenuColors.rgba(255, 255, 255, (int) (120 * a)));
        }
    }

    private void drawDoneButton(float x, float y, float w, float h, float a, float k) {
        boolean hov = WhiteMenu.isHovered(this.lastMouseX, this.lastMouseY, x, y, w, h);
        int accent = ThemeManager.accent(255);
        Render2D.rect(x, y, w, h, 8.0f * k, MenuColors.withAlpha(accent, (int) ((hov ? 235 : 205) * a)));
        float textW = Fonts.SEMIBOLD.width("Готово", 11.0f * k);
        Fonts.SEMIBOLD.draw("Готово", x + (w - textW) * 0.5f, y + (h - 11.0f * k) / 2.0f - 0.5f * k,
                11.0f * k, MenuColors.rgba(255, 255, 255, (int) (255 * a)));
    }

    private void drawGlassPanel(float x, float y, float w, float h, float rad, float a, float k) {
        if (MenuSettings.liquidGlass()) {
            RectUtil.drawGlassCard(x, y, w, h, rad, rad, rad, rad, a, 0.0f);
            Render2D.outline(x, y, w, h, rad, 1.0f, MenuColors.rgba(255, 255, 255, (int) (26 * a)));
        } else {
            Render2D.blur(x, y, w, h, rad, 26.0f);
            Render2D.rect(x, y, w, h, rad, MenuColors.rgba(11, 13, 19, (int) (205 * a)));
            Render2D.outline(x, y, w, h, rad, 1.0f, MenuColors.rgba(255, 255, 255, (int) (26 * a)));
        }
    }

    private void clickSound() {
        SoundManager.playSoundDirect(SoundManager.BUTTON_CLICK, 0.6f, 1.0f);
    }

    // ——— ввод

    @Override
    public boolean mouseClicked(Click click, boolean doubled) {
        float mx = (float) (click.x() / this.scaleFix);
        float my = (float) (click.y() / this.scaleFix);
        if (click.button() == 0) {
            if (this.backH > 0.0f && WhiteMenu.isHovered(mx, my, this.backX, this.backY, this.backW, this.backH)) {
                this.clickSound();
                this.close();
                return true;
            }
            if (this.doneH > 0.0f && WhiteMenu.isHovered(mx, my, this.doneX, this.doneY, this.doneW, this.doneH)) {
                this.clickSound();
                this.close();
                return true;
            }
            for (int i = 0; i < SLIDERS; i++) {
                if (this.slW[i] > 0.0f && WhiteMenu.isHovered(mx, my, this.slX[i], this.slY[i], this.slW[i], this.slH[i])) {
                    this.dragIdx = i;
                    this.applySlider(i, mx);
                    return true;
                }
            }
            for (int i = 0; i < TOGGLES; i++) {
                if (this.tgW[i] > 0.0f && WhiteMenu.isHovered(mx, my, this.tgX[i], this.tgY[i], this.tgW[i], this.tgH[i])) {
                    this.clickSound();
                    this.toggleOption(i);
                    return true;
                }
            }
            for (float[] hit : this.navHits) {
                if (WhiteMenu.isHovered(mx, my, hit[1], hit[2], hit[3], hit[4])) {
                    this.clickSound();
                    this.openSection((int) hit[0]);
                    return true;
                }
            }
        }
        return super.mouseClicked(click, doubled);
    }

    @Override
    public boolean mouseReleased(Click click) {
        this.dragIdx = -1;
        return super.mouseReleased(click);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        float maxScroll = Math.max(0.0f, this.contentBottom - this.viewH);
        this.scrollTarget = Math.max(0.0f, Math.min(maxScroll, this.scrollTarget - (float) verticalAmount * 28.0f));
        return true;
    }
}
