package rtx.kimiko.api.ui.menu;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import rtx.kimiko.api.ui.mainmenu.MenuBackground;
import rtx.kimiko.api.ui.mainmenu.MenuBackgrounds;
import rtx.kimiko.api.ui.mainmenu.MenuSettings;
import rtx.kimiko.api.ui.theme.Theme;
import rtx.kimiko.api.ui.theme.ThemeManager;
import rtx.kimiko.utils.animations.Easings;
import rtx.kimiko.utils.animations.SmoothAnimation;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RoundedScissor;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.sounds.SoundManager;

import java.util.ArrayList;
import java.util.List;

public class WhiteMenuCustomizeScreen extends Screen {
    private static final int THEME_COLS = 7;
    private static final int BG_COLS = 3;

    private final Screen parent;
    private final SmoothAnimation alpha = new SmoothAnimation();

    private float scaleFix = 1.0f;
    private float lastMouseX;
    private float lastMouseY;
    private float scrollTarget;
    private float scroll;
    /** Позиция скролла переживает закрытие/открытие экрана настроек. */
    private static float savedScroll = -1.0f;
    /** Восстановить позицию, как только контент будет измерен (в init() контент ещё пуст). */
    private static boolean pendingRestore;
    private float contentHeight;
    private float viewH;
    private float backX;
    private float backY;
    private float backW;
    private float backH;

    private float scaleBottom;
    private float lookBottom;
    private float sliderX;
    private float sliderY;
    private float sliderW;
    private float sliderH;
    private boolean draggingScale;

    private final float[] gsX = new float[3];
    private final float[] gsY = new float[3];
    private final float[] gsW = new float[3];
    private final float[] gsH = new float[3];
    private int glassDragIdx = -1;

    private static final float SCALE_MIN = 0.75f;
    private static final float SCALE_MAX = 1.5f;

    private float appliedScale = -1.0f;
    private long lastScaleNanos = 0L;

    private final List<ThemeHit> themeHits = new ArrayList<>();
    private final List<BgHit> bgHits = new ArrayList<>();
    private final List<SegHit> lookHits = new ArrayList<>();
    private final List<SegHit> layoutHits = new ArrayList<>();

    private static final class SegHit {
        final String value;
        final float x;
        final float y;
        final float w;
        final float h;

        SegHit(String value, float x, float y, float w, float h) {
            this.value = value;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
    }

    public WhiteMenuCustomizeScreen(Screen parent) {
        super(Text.literal("KimikoCustomize"));
        this.parent = parent;
    }

    private static final class ThemeHit {
        final Theme theme;
        final float x;
        final float y;
        final float w;
        final float h;

        ThemeHit(Theme theme, float x, float y, float w, float h) {
            this.theme = theme;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
    }

    private static final class BgHit {
        final MenuBackground entry;
        final float x;
        final float y;
        final float w;
        final float h;

        BgHit(MenuBackground entry, float x, float y, float w, float h) {
            this.entry = entry;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
    }

    @Override
    protected void init() {
        this.alpha.setValue(0.0);
        this.alpha.run(1.0, 0.4, Easings.QUAD_OUT, false);
        // позиция скролла восстановится в render(), когда контент уже измерен
        if (savedScroll >= 0.0f) {
            pendingRestore = true;
        }
        try {
            MenuBackgrounds.rescan();
            MenuBackgrounds.preload();
            System.out.println("[Kimiko/Customize] backgrounds folder=" + MenuBackgrounds.folder()
                    + ", entries=" + MenuBackgrounds.all().size());
        } catch (Throwable ignored) {
        }
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
        float dt = Math.max(0.0f, Math.min(2.0f, delta));
        float currentScale = (float) mc.getWindow().getScaleFactor();
        this.scaleFix = 2.0f / currentScale;
        this.lastMouseX = (float) (mouseX / this.scaleFix);
        this.lastMouseY = (float) (mouseY / this.scaleFix);
        WhiteMenuBackground.trackMouse(this.lastMouseX, this.lastMouseY);
        this.alpha.update();
        float a = (float) this.alpha.getValue();
        if (this.draggingScale) {
            this.updateScaleDrag(this.lastMouseX);
        }
        if (this.glassDragIdx >= 0) {
            this.updateGlassDrag(this.glassDragIdx, this.lastMouseX);
        }
        int sw = (int) (mc.getWindow().getScaledWidth() / this.scaleFix);
        int sh = (int) (mc.getWindow().getScaledHeight() / this.scaleFix);
        float k = Math.max(0.85f, sh / 540.0f) * this.smoothScale();
        k = Math.max(0.62f, Math.min(2.2f, k));

        Render2D.beginFrame(context);

        WhiteMenuBackground.drawDarkBackdrop(sw, sh, a);

        // мягкое затемнение фона без резкой полосы
        Render2D.rect(0.0f, 0.0f, sw, sh, 0.0f,
                MenuColors.rgba(8, 10, 15, (int) (120 * a)),
                MenuColors.rgba(8, 10, 15, (int) (90 * a)),
                MenuColors.rgba(8, 10, 15, (int) (60 * a)),
                MenuColors.rgba(8, 10, 15, (int) (90 * a)));

        float leftX = sw * 0.045f;
        float heroY = sh * 0.10f;
        Fonts.BOLD.draw("ОФОРМЛЕНИЕ", leftX, heroY, 9.5f * k,
                MenuColors.withAlpha(ThemeManager.accent(255), (int) (255 * a)));
        Fonts.EXTRABOLD.draw("Настройки меню", leftX, heroY + 16.0f * k, 25.0f * k,
                MenuColors.rgba(255, 255, 255, (int) (255 * a)));
        Fonts.REGULAR.draw("Тема оформления и фон главного меню", leftX, heroY + 16.0f * k + 25.0f * k + 8.0f * k,
                11.0f * k, MenuColors.rgba(255, 255, 255, (int) (165 * a)));

        float panelW = Math.min(660.0f * k, sw - 40.0f);
        float panelX = (sw - panelW) / 2.0f;
        float panelY = heroY + 82.0f * k;
        float panelH = sh - panelY - 26.0f * k;
        float panelRad = 14.0f * k;

        this.drawGlassPanel(panelX, panelY, panelW, panelH, panelRad, a, k);

        float pad = 14.0f * k;
        float contentX = panelX + pad;
        float contentW = panelW - pad * 2.0f;
        float viewTop = panelY + pad;
        this.viewH = panelY + panelH - pad - viewTop;

        this.constrainScroll(dt);

        Render2D.pushScissor(context, contentX, viewTop, contentW, this.viewH);

        float y = viewTop - this.scroll;
        this.themeHits.clear();
        this.bgHits.clear();

        this.drawBackgroundsSection(contentX, contentW, y, a, k, viewTop, viewTop + this.viewH);
        float yAfterBg = this.bgBottom;
        this.drawThemesSection(contentX, contentW, yAfterBg + 18.0f * k, a, k, viewTop, viewTop + this.viewH);
        float yAfterThemes = this.themesBottom;
        this.drawScaleSection(contentX, contentW, yAfterThemes + 20.0f * k, a, k, viewTop, viewTop + this.viewH);
        float yAfterScale = this.scaleBottom;
        this.drawLookSection(contentX, contentW, yAfterScale + 16.0f * k, a, k, viewTop, viewTop + this.viewH);

        this.contentHeight = (this.lookBottom - (viewTop - this.scroll));

        // восстанавливаем прокрутку здесь: контент уже измерен, клампы больше не затрут позицию
        if (pendingRestore) {
            pendingRestore = false;
            float maxScroll = Math.max(0.0f, this.contentHeight - this.viewH);
            float restored = Math.max(0.0f, Math.min(maxScroll, savedScroll));
            this.scroll = restored;
            this.scrollTarget = restored;
        }
        savedScroll = this.scroll;

        Render2D.popScissor(context);

        this.drawScrollBar(panelX, panelW, viewTop, a, k);

        // кнопка «Назад» — справа сверху, на уровне заголовка «Настройки меню»
        this.backW = 130.0f * k;
        this.backH = 30.0f * k;
        this.backX = sw - this.backW - sw * 0.045f;
        this.backY = heroY + 13.5f * k;
        this.drawBackButton("Назад", this.backX, this.backY, this.backW, this.backH, a, k);

        Render2D.flush();
    }

    private float bgBottom;
    private float themesBottom;

    private void drawGlassPanel(float x, float y, float w, float h, float rad, float a, float k) {
        long now = System.currentTimeMillis();
        float wave = 0.5f + 0.5f * (float) Math.sin(now / 2600.0);
        float wave2 = 0.5f + 0.5f * (float) Math.sin(now / 4100.0 + 1.7);
        int accent = ThemeManager.accent(255);

        Render2D.blur(x, y, w, h, rad);
        Render2D.rect(x, y, w, h, rad, MenuColors.rgba(11, 13, 19, (int) (232 * a)));
        int[] tint = new int[]{
                MenuColors.withAlpha(accent, (int) ((34 + 20 * wave) * a)),
                MenuColors.withAlpha(accent, (int) ((10 + 14 * wave2) * a)),
                MenuColors.withAlpha(accent, (int) ((6 + 10 * wave) * a)),
                MenuColors.withAlpha(accent, (int) ((28 + 18 * wave2) * a))
        };
        Render2D.rect(x, y, w, h, rad, tint[0], tint[1], tint[2], tint[3]);
        Render2D.outline(x, y, w, h, rad, 1.0f, MenuColors.rgba(255, 255, 255, (int) (26 * a)));

        // переливающаяся полоска под заголовком панели
        float stripX = x + 14.0f * k;
        float stripY = y + 12.0f * k;
        float stripW = w - 28.0f * k;
        float sweep = (float) ((now % 4200L) / 4200.0);
        float sweepX = stripX + sweep * (stripW - 60.0f * k);
        Render2D.rect(stripX, stripY, stripW, 2.0f * k, 1.0f * k,
                MenuColors.withAlpha(accent, 0),
                MenuColors.withAlpha(accent, (int) (150 * a)),
                MenuColors.withAlpha(accent, (int) (150 * a)),
                MenuColors.withAlpha(accent, 0));
        Render2D.rect(sweepX, stripY - 1.5f * k, 60.0f * k, 5.0f * k, 2.5f * k,
                MenuColors.withAlpha(accent, 0),
                MenuColors.withAlpha(accent, (int) (190 * a)),
                MenuColors.withAlpha(accent, (int) (190 * a)),
                MenuColors.withAlpha(accent, 0));
    }

    private void drawBackgroundsSection(float x, float w, float y, float a, float k, float viewTop, float viewBottom) {
        Render2D.rect(x, y + 1.0f * k, 2.5f * k, 9.0f * k, 1.25f * k, MenuColors.withAlpha(ThemeManager.accent(255), (int) (220 * a)));
        Fonts.BOLD.draw("ФОН ГЛАВНОГО МЕНЮ", x + 9.0f * k, y, 9.0f * k, MenuColors.rgba(255, 255, 255, (int) (150 * a)));
        y += 16.0f * k;

        // свои фоны первыми, встроенные (Kimiko / Аврора / Глубина) — в самом низу
        List<MenuBackground> all = MenuBackgrounds.all();
        List<MenuBackground> list = new ArrayList<>();
        for (MenuBackground entry : all) {
            if (!entry.builtIn()) {
                list.add(entry);
            }
        }
        for (MenuBackground entry : all) {
            if (entry.builtIn()) {
                list.add(entry);
            }
        }
        float gap = 10.0f * k;
        float cardW = (w - gap * (BG_COLS - 1)) / BG_COLS;
        float cardH = cardW / 1.7778f;
        float cellH = cardH + 18.0f * k;
        String selectedId = MenuBackgrounds.selected() == null ? "" : MenuBackgrounds.selected().id();

        for (int i = 0; i < list.size(); i++) {
            int col = i % BG_COLS;
            int row = i / BG_COLS;
            float cx = x + col * (cardW + gap);
            float cy = y + row * (cellH + gap);

            if (cy + cardH < viewTop - 8.0f || cy > viewBottom + 8.0f) {
                continue;
            }

            MenuBackground entry = list.get(i);
            boolean sel = entry.id().equals(selectedId);
            boolean hov = WhiteMenu.isHovered(this.lastMouseX, this.lastMouseY, cx, cy, cardW, cardH);
            this.bgHits.add(new BgHit(entry, cx, cy, cardW, cardH));

            // скругление прямо на картинке: углы превью совпадают с формой обводки
            float cardRad = 10.0f * k;
            if (entry.procedural()) {
                RoundedScissor.push(cx, cy, cardW, cardH, cardRad, cardRad, cardRad, cardRad);
                WhiteMenuBackground.drawProceduralPreview(entry, cx, cy, cardW, cardH, a, this.lastMouseX, this.lastMouseY);
                RoundedScissor.pop();
            } else {
                // превью — резкая уменьшенная копия, без размытия
                String tex = entry.failed() ? null : entry.preview();
                if (tex != null) {
                    WhiteMenuBackground.drawCover(tex, cx, cy, cardW, cardH, entry.aspect(),
                            MenuColors.rgba(255, 255, 255, (int) (235 * a)), cardRad);
                } else {
                    // плейсхолдер с мягким пробегающим бликом, пока фон декодируется
                    Render2D.rect(cx, cy, cardW, cardH, cardRad, MenuColors.rgba(22, 25, 34, (int) (225 * a)));
                    Render2D.rect(cx, cy, cardW, cardH, cardRad,
                            MenuColors.rgba(255, 255, 255, (int) (5 * a)),
                            MenuColors.rgba(255, 255, 255, (int) (16 * a)),
                            MenuColors.rgba(255, 255, 255, (int) (10 * a)),
                            MenuColors.rgba(255, 255, 255, (int) (5 * a)));
                    if (entry.loading()) {
                        long now = System.currentTimeMillis();
                        float sweep = ((now % 1400L) / 1400.0f);
                        float baseX = cx + sweep * (cardW - 28.0f * k);
                        Render2D.rect(baseX, cy + cardH * 0.42f, 28.0f * k, cardH * 0.16f, cardH * 0.08f,
                                MenuColors.rgba(255, 255, 255, (int) (40 * a)),
                                MenuColors.rgba(255, 255, 255, (int) (10 * a)),
                                MenuColors.rgba(255, 255, 255, (int) (10 * a)),
                                MenuColors.rgba(255, 255, 255, (int) (40 * a)));
                    }
                }
            }

            // обводка: ховер — белая, выбранная — толстая рамка цвета клиента
            if (hov && !sel) {
                Render2D.outline(cx + 0.8f * k, cy + 0.8f * k, cardW - 1.6f * k, cardH - 1.6f * k, cardRad - 1.2f * k, 1.6f,
                        MenuColors.rgba(255, 255, 255, (int) (120 * a)));
            }
            if (sel) {
                int accent = ThemeManager.accent(255);
                Render2D.outline(cx + 1.2f * k, cy + 1.2f * k, cardW - 2.4f * k, cardH - 2.4f * k, cardRad - 1.8f * k, 2.6f,
                        MenuColors.withAlpha(accent, (int) (255 * a)));
                Render2D.outline(cx + 3.2f * k, cy + 3.2f * k, cardW - 6.4f * k, cardH - 6.4f * k, cardRad - 3.8f * k, 1.0f,
                        MenuColors.withAlpha(accent, (int) (80 * a)));
            }

            String name = this.ellipsize(entry.name(), cardW, 8.0f * k);
            float nw = Fonts.REGULAR.width(name, 8.0f * k);
            Fonts.REGULAR.draw(name, cx + (cardW - nw) / 2.0f, cy + cardH + 4.0f * k, 8.0f * k,
                    MenuColors.rgba(255, 255, 255, (int) ((sel ? 255 : 160) * a)));
        }

        int rows = (list.size() + BG_COLS - 1) / BG_COLS;
        this.bgBottom = y + rows * (cellH + gap);
    }

    private void drawThemesSection(float x, float w, float y, float a, float k, float viewTop, float viewBottom) {
        Render2D.rect(x, y + 1.0f * k, 2.5f * k, 9.0f * k, 1.25f * k, MenuColors.withAlpha(ThemeManager.accent(255), (int) (220 * a)));
        Fonts.BOLD.draw("ТЕМА ОФОРМЛЕНИЯ", x + 9.0f * k, y, 9.0f * k, MenuColors.rgba(255, 255, 255, (int) (150 * a)));
        y += 18.0f * k;

        Theme[] themes = Theme.values();
        float gap = 7.0f * k;
        float cellW = (w - gap * (THEME_COLS - 1)) / THEME_COLS;
        float swatch = Math.min(cellW - 6.0f * k, 46.0f * k);
        float cellH = swatch + 19.0f * k;
        Theme current = ThemeManager.current();
        int accent = ThemeManager.accent(255);

        for (int i = 0; i < themes.length; i++) {
            int col = i % THEME_COLS;
            int row = i / THEME_COLS;
            float cx = x + col * (cellW + gap);
            float cy = y + row * (cellH + gap);

            if (cy + swatch < viewTop - 8.0f || cy > viewBottom + 8.0f) {
                continue;
            }

            Theme theme = themes[i];
            boolean sel = theme == current;
            boolean hov = WhiteMenu.isHovered(this.lastMouseX, this.lastMouseY, cx, cy, cellW, cellH);
            this.themeHits.add(new ThemeHit(theme, cx, cy, cellW, cellH));

            float scx = cx + (cellW - swatch) / 2.0f;
            float scy = cy;
            float radius = swatch * 0.5f;

            Render2D.rect(scx - 2.0f * k, scy - 2.0f * k, swatch + 4.0f * k, swatch + 4.0f * k, radius + 2.0f * k,
                    MenuColors.rgba(10, 12, 18, (int) (170 * a)));

            int col1 = MenuColors.withAlpha(theme.accentRgb(), (int) (255 * a));
            int col2 = MenuColors.withAlpha(theme.gradientB(), (int) (255 * a));
            Render2D.rect(scx, scy, swatch, swatch, radius, col1, col2, col1, col2);

            if (hov && !sel) {
                Render2D.outline(scx - 3.0f * k, scy - 3.0f * k, swatch + 6.0f * k, swatch + 6.0f * k,
                        radius + 3.0f * k, 1.0f, MenuColors.rgba(255, 255, 255, (int) (110 * a)));
            }
            if (sel) {
                Render2D.outline(scx - 3.0f * k, scy - 3.0f * k, swatch + 6.0f * k, swatch + 6.0f * k,
                        radius + 3.0f * k, 1.8f, MenuColors.withAlpha(accent, (int) (255 * a)));
            }

            String name = this.ellipsize(theme.displayName(), cellW, 7.0f * k);
            float nw = Fonts.REGULAR.width(name, 7.0f * k);
            Fonts.REGULAR.draw(name, cx + (cellW - nw) / 2.0f, scy + swatch + 2.0f * k, 7.0f * k,
                    MenuColors.rgba(255, 255, 255, (int) ((sel ? 255 : 150) * a)));
        }

        int rows = (themes.length + THEME_COLS - 1) / THEME_COLS;
        this.themesBottom = y + rows * (cellH + gap);
    }

    private void drawScaleSection(float x, float w, float y, float a, float k, float viewTop, float viewBottom) {
        if (y + 12.0f * k < viewTop - 8.0f || y > viewBottom + 40.0f * k) {
            this.scaleBottom = y + 46.0f * k;
            return;
        }

        Render2D.rect(x, y + 1.0f * k, 2.5f * k, 9.0f * k, 1.25f * k, MenuColors.withAlpha(ThemeManager.accent(255), (int) (220 * a)));
        Fonts.BOLD.draw("РАЗМЕР ИНТЕРФЕЙСА", x + 9.0f * k, y, 9.0f * k, MenuColors.rgba(255, 255, 255, (int) (150 * a)));
        y += 20.0f * k;

        float scale = this.appliedScale > 0.0f ? this.appliedScale : MenuSettings.uiScale();
        int accent = ThemeManager.accent(255);

        float trackY = y;
        float trackH = 6.0f * k;
        int percent = Math.round(scale * 100.0f);
        String label = percent + "%";
        float labelW = Fonts.SEMIBOLD.width(label, 10.5f * k);
        float trackX = x;
        float trackW = w - labelW - 14.0f * k;
        float frac = (scale - SCALE_MIN) / (SCALE_MAX - SCALE_MIN);

        boolean hov = this.draggingScale
                || WhiteMenu.isHovered(this.lastMouseX, this.lastMouseY, trackX, trackY - 7.0f * k, trackW, trackH + 14.0f * k);

        // запоминаем геометрию для клика/драга
        this.sliderX = trackX;
        this.sliderY = trackY - 7.0f * k;
        this.sliderW = trackW;
        this.sliderH = trackH + 14.0f * k;

        Render2D.rect(trackX, trackY, trackW, trackH, trackH * 0.5f, MenuColors.rgba(255, 255, 255, (int) (16 * a)));
        float fillW = Math.max(trackH, trackW * Math.max(0.0f, Math.min(1.0f, frac)));
        Render2D.rect(trackX, trackY, fillW, trackH, trackH * 0.5f,
                MenuColors.withAlpha(accent, (int) ((hov ? 255 : 225) * a)));

        float knobX = trackX + trackW * Math.max(0.0f, Math.min(1.0f, frac));
        float knobY = trackY + trackH * 0.5f;
        float knobR = 6.5f * k;
        if (hov) {
            Render2D.circle(knobX, knobY, knobR + 5.0f * k, 22, MenuColors.withAlpha(accent, (int) (46 * a)));
        }
        Render2D.circle(knobX, knobY, knobR, 22, MenuColors.rgba(255, 255, 255, (int) (255 * a)));
        Render2D.circle(knobX, knobY, knobR * 0.42f, 12, MenuColors.withAlpha(accent, (int) (255 * a)));

        float labelY = trackY + trackH * 0.5f - 5.5f * k;
        Fonts.SEMIBOLD.draw(label, trackX + trackW + 14.0f * k, labelY, 10.5f * k,
                hov ? MenuColors.rgba(255, 255, 255, (int) (255 * a)) : MenuColors.rgba(255, 255, 255, (int) (185 * a)));

        String hint = "От 75% до 150% — насколько крупными будут элементы меню";
        Fonts.REGULAR.draw(hint, x, trackY + trackH + 7.0f * k, 8.0f * k, MenuColors.rgba(255, 255, 255, (int) (90 * a)));

        this.scaleBottom = trackY + trackH + 7.0f * k + 12.0f * k + 4.0f * k;
    }

    private void updateScaleDrag(float mx) {
        float t = (mx - this.sliderX) / Math.max(1.0f, this.sliderW);
        t = Math.max(0.0f, Math.min(1.0f, t));
        float value = SCALE_MIN + t * (SCALE_MAX - SCALE_MIN);
        MenuSettings.setUiScale((float) Math.round(value * 100.0f) / 100.0f);
    }

    private void saveQuietly() {
        try {
            MenuSettings.save();
        } catch (Throwable ignored) {
        }
    }

    private void drawLookSection(float x, float w, float y, float a, float k, float viewTop, float viewBottom) {
        if (y + 80.0f * k < viewTop - 8.0f || y > viewBottom + 300.0f * k) {
            this.lookBottom = y + 300.0f * k;
            return;
        }

        int accent = ThemeManager.accent(255);
        this.lookHits.clear();
        this.layoutHits.clear();

        // ——— ВИД ИНТЕРФЕЙСА
        Render2D.rect(x, y + 1.0f * k, 2.5f * k, 9.0f * k, 1.25f * k, MenuColors.withAlpha(accent, (int) (220 * a)));
        Fonts.BOLD.draw("ВИД ИНТЕРФЕЙСА", x + 9.0f * k, y, 9.0f * k, MenuColors.rgba(255, 255, 255, (int) (150 * a)));
        y += 16.0f * k;
        this.drawSegmentedRow(new String[]{"Обычный", "Liquid glass"}, MenuSettings.interfaceLook(), x, w, y, a, k);
        float rowH = 24.0f * k;
        float cell2 = (w - 6.0f * k) * 0.5f;
        this.lookHits.add(new SegHit("usual", x + 3.0f * k, y + 3.0f * k, cell2, rowH - 6.0f * k));
        this.lookHits.add(new SegHit("glass", x + 3.0f * k + cell2, y + 3.0f * k, cell2, rowH - 6.0f * k));
        float yAfterLook = y + rowH;

        // ——— КНОПКИ
        y = yAfterLook + 18.0f * k;
        Render2D.rect(x, y + 1.0f * k, 2.5f * k, 9.0f * k, 1.25f * k, MenuColors.withAlpha(accent, (int) (220 * a)));
        Fonts.BOLD.draw("КНОПКИ", x + 9.0f * k, y, 9.0f * k, MenuColors.rgba(255, 255, 255, (int) (150 * a)));
        y += 16.0f * k;
        String[] labels3 = {"Слева", "По центру", "Справа"};
        this.drawSegmentedRow(labels3, MenuSettings.buttonLayout(), x, w, y, a, k);
        float cell3 = (w - 6.0f * k) / 3.0f;
        for (int i = 0; i < 3; i++) {
            this.layoutHits.add(new SegHit(new String[]{"left", "center", "right"}[i],
                    x + 3.0f * k + cell3 * i, y + 3.0f * k, cell3, rowH - 6.0f * k));
        }

        // ——— СТЕКЛО КНОПОК (три ползунка)
        y = y + rowH + 18.0f * k;
        Render2D.rect(x, y + 1.0f * k, 2.5f * k, 9.0f * k, 1.25f * k, MenuColors.withAlpha(accent, (int) (220 * a)));
        Fonts.BOLD.draw("СТЕКЛО КНОПОК", x + 9.0f * k, y, 9.0f * k, MenuColors.rgba(255, 255, 255, (int) (150 * a)));
        y += 22.0f * k;
        y = this.drawGlassSlider(x, w, y, a, k, 0, "Акцент primary", MenuSettings.primaryAccent(),
                Math.round(MenuSettings.primaryAccent() * 100.0f) + "%",
                "Насколько ярко подкрашена главная кнопка");
        y = this.drawGlassSlider(x, w, y, a, k, 1, "Плотность стекла", MenuSettings.glassDensity(),
                Math.round(MenuSettings.glassDensity() * 100.0f) + "%",
                "Чем выше — тем плотнее и заметнее стекло");
        y = this.drawGlassSlider(x, w, y, a, k, 2, "Скругление кнопок", MenuSettings.buttonRadiusT(),
                Math.round(MenuSettings.buttonRadius()) + " px",
                "Радиус углов стеклянных кнопок");

        this.lookBottom = y + 4.0f * k;
    }

    /** Ползунок настроек стеклянных кнопок; возвращает координату низа строки. */
    private float drawGlassSlider(float x, float w, float y, float a, float k, int idx, String name, float value01, String valueText, String hint) {
        float trackY = y + 8.0f * k;
        float trackH = 6.0f * k;
        float labelW = Fonts.SEMIBOLD.width(valueText, 10.5f * k);
        float trackW = w - labelW - 14.0f * k;
        int accent = ThemeManager.accent(255);
        boolean hov = this.glassDragIdx == idx
                || WhiteMenu.isHovered(this.lastMouseX, this.lastMouseY, x, trackY - 7.0f * k, w, trackH + 14.0f * k);

        this.gsX[idx] = x;
        this.gsY[idx] = trackY - 7.0f * k;
        this.gsW[idx] = w;
        this.gsH[idx] = trackH + 14.0f * k;

        Fonts.SEMIBOLD.draw(name, x, y, 9.5f * k,
                hov ? MenuColors.rgba(255, 255, 255, (int) (225 * a)) : MenuColors.rgba(255, 255, 255, (int) (165 * a)));

        float t = Math.max(0.0f, Math.min(1.0f, value01));
        Render2D.rect(x, trackY, trackW, trackH, trackH * 0.5f, MenuColors.rgba(255, 255, 255, (int) (16 * a)));
        float fillW = Math.max(trackH, trackW * t);
        Render2D.rect(x, trackY, fillW, trackH, trackH * 0.5f,
                MenuColors.withAlpha(accent, (int) ((hov ? 255 : 225) * a)));
        float knobX = x + trackW * t;
        float knobY = trackY + trackH * 0.5f;
        float knobR = 6.5f * k;
        if (hov) {
            Render2D.circle(knobX, knobY, knobR + 5.0f * k, 22, MenuColors.withAlpha(accent, (int) (46 * a)));
        }
        Render2D.circle(knobX, knobY, knobR, 22, MenuColors.rgba(255, 255, 255, (int) (255 * a)));
        Render2D.circle(knobX, knobY, knobR * 0.42f, 12, MenuColors.withAlpha(accent, (int) (255 * a)));

        Fonts.SEMIBOLD.draw(valueText, x + trackW + 14.0f * k, trackY + trackH * 0.5f - 5.5f * k, 10.5f * k,
                hov ? MenuColors.rgba(255, 255, 255, (int) (255 * a)) : MenuColors.rgba(255, 255, 255, (int) (185 * a)));
        Fonts.REGULAR.draw(hint, x, trackY + trackH + 7.0f * k, 8.0f * k, MenuColors.rgba(255, 255, 255, (int) (90 * a)));

        return trackY + trackH + 7.0f * k + 12.0f * k;
    }

    private void updateGlassDrag(int idx, float mx) {
        float t = (mx - this.gsX[idx]) / Math.max(1.0f, this.gsW[idx]);
        t = Math.max(0.0f, Math.min(1.0f, t));
        float value = (float) Math.round(t * 100.0f) / 100.0f;
        if (idx == 0) {
            MenuSettings.setPrimaryAccent(value);
        } else if (idx == 1) {
            MenuSettings.setGlassDensity(value);
        } else {
            MenuSettings.setButtonRadiusT(value);
        }
    }

    /** Сегментированный переключатель (Обычный/Liquid glass, Слева/По центру/Справа). */
    private void drawSegmentedRow(String[] labels, String current, float x, float w, float y, float a, float k) {
        int accent = ThemeManager.accent(255);
        float rowH = 24.0f * k;
        float cell = (w - 6.0f * k) / labels.length;
        boolean isLook = labels.length == 2;

        Render2D.rect(x, y, w, rowH, rowH * 0.5f, MenuColors.rgba(255, 255, 255, (int) (10 * a)));
        Render2D.outline(x, y, w, rowH, rowH * 0.5f, 1.0f, MenuColors.rgba(255, 255, 255, (int) (14 * a)));

        for (int i = 0; i < labels.length; i++) {
            float sx = x + 3.0f * k + cell * i;
            float sw2 = cell - 2.0f * k;
            String value = isLook
                    ? (i == 0 ? "usual" : "glass")
                    : (i == 0 ? "left" : (i == 1 ? "center" : "right"));
            boolean sel = value.equals(current);
            boolean hov = WhiteMenu.isHovered(this.lastMouseX, this.lastMouseY, sx, y + 3.0f * k, sw2, rowH - 6.0f * k);

            if (sel) {
                Render2D.rect(sx, y + 3.0f * k, sw2, rowH - 6.0f * k, (rowH - 6.0f * k) * 0.5f,
                        MenuColors.withAlpha(accent, (int) (52 * a)));
                Render2D.outline(sx, y + 3.0f * k, sw2, rowH - 6.0f * k, (rowH - 6.0f * k) * 0.5f, 1.0f,
                        MenuColors.withAlpha(accent, (int) (215 * a)));
            } else if (hov) {
                Render2D.rect(sx, y + 3.0f * k, sw2, rowH - 6.0f * k, (rowH - 6.0f * k) * 0.5f,
                        MenuColors.rgba(255, 255, 255, (int) (10 * a)));
            }

            float tw = Fonts.SEMIBOLD.width(labels[i], 9.5f * k);
            Fonts.SEMIBOLD.draw(labels[i], sx + (sw2 - tw) / 2.0f, y + 3.0f * k + ((rowH - 6.0f * k) - 9.5f * k) / 2.0f,
                    9.5f * k,
                    sel ? MenuColors.rgba(255, 255, 255, (int) (255 * a)) : MenuColors.rgba(255, 255, 255, (int) (140 * a)));
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

    private void drawScrollBar(float panelX, float panelW, float viewTop, float a, float k) {
        float max = Math.max(0.0f, this.contentHeight - this.viewH);
        if (max <= 0.0f || this.contentHeight <= 0.0f) {
            return;
        }
        float trackX = panelX + panelW - 5.0f * k;
        float trackH = this.viewH;
        float thumbH = Math.max(24.0f * k, trackH * (this.viewH / this.contentHeight));
        float clamped = Math.max(0.0f, Math.min(max, this.scroll));
        float thumbY = viewTop + (trackH - thumbH) * (clamped / max);
        Render2D.rect(trackX, viewTop, 3.0f * k, trackH, 1.5f * k, MenuColors.rgba(255, 255, 255, (int) (16 * a)));
        Render2D.rect(trackX, thumbY, 3.0f * k, thumbH, 1.5f * k, MenuColors.rgba(255, 255, 255, (int) (110 * a)));
    }

    private void constrainScroll(float dt) {
        float max = Math.max(0.0f, this.contentHeight - this.viewH);
        if (this.scrollTarget > max) {
            this.scrollTarget += (max - this.scrollTarget) * Math.min(1.0f, dt * 6.0f);
        } else if (this.scrollTarget < 0.0f) {
            this.scrollTarget += (0.0f - this.scrollTarget) * Math.min(1.0f, dt * 6.0f);
        }
        this.scroll += (this.scrollTarget - this.scroll) * Math.min(1.0f, dt * 12.0f);
    }

    private void drawBackButton(String text, float x, float y, float w, float h, float a, float k) {
        boolean hov = WhiteMenu.isHovered(this.lastMouseX, this.lastMouseY, x, y, w, h);
        int accent = ThemeManager.accent(255);
        Render2D.rect(x, y, w, h, 8.0f * k,
                MenuColors.rgba(24 + (hov ? 12 : 0), 26 + (hov ? 12 : 0), 33 + (hov ? 14 : 0), (int) (205 * a)));
        Render2D.outline(x, y, w, h, 8.0f * k, 1.0f,
                hov ? MenuColors.withAlpha(accent, (int) (200 * a)) : MenuColors.rgba(255, 255, 255, (int) (26 * a)));
        float tw = Fonts.SEMIBOLD.width(text, 10.0f * k);
        Fonts.SEMIBOLD.draw(text, x + (w - tw) / 2.0f, y + (h - 10.0f * k) / 2.0f, 10.0f * k,
                MenuColors.rgba(255, 255, 255, (int) (255 * a)));
    }

    private String ellipsize(String text, float maxW, float size) {
        if (text == null) {
            return "";
        }
        if (Fonts.REGULAR.width(text, size) <= maxW) {
            return text;
        }
        String cut = text;
        while (cut.length() > 1 && Fonts.REGULAR.width(cut + "…", size) > maxW) {
            cut = cut.substring(0, cut.length() - 1);
        }
        return cut + "…";
    }

    @Override
    public boolean mouseClicked(Click click, boolean doubled) {
        float mx = (float) (click.x() / this.scaleFix);
        float my = (float) (click.y() / this.scaleFix);
        MinecraftClient mc = MinecraftClient.getInstance();

        if (click.button() == 0) {
            if (this.backH > 0.0f && WhiteMenu.isHovered(mx, my, this.backX, this.backY, this.backW, this.backH)) {
                this.clickSound();
                if (mc != null) {
                    mc.setScreen(this.parent);
                }
                return true;
            }
            if (this.sliderW > 0.0f && WhiteMenu.isHovered(mx, my, this.sliderX, this.sliderY, this.sliderW, this.sliderH)) {
                this.draggingScale = true;
                this.clickSound();
                this.updateScaleDrag(mx);
                return true;
            }
            for (int i = 0; i < 3; i++) {
                if (this.gsW[i] > 0.0f && WhiteMenu.isHovered(mx, my, this.gsX[i], this.gsY[i], this.gsW[i], this.gsH[i])) {
                    this.glassDragIdx = i;
                    this.clickSound();
                    this.updateGlassDrag(i, mx);
                    return true;
                }
            }
            for (SegHit hit : this.lookHits) {
                if (WhiteMenu.isHovered(mx, my, hit.x, hit.y, hit.w, hit.h)) {
                    if (!MenuSettings.interfaceLook().equals(hit.value)) {
                        this.clickSound();
                        MenuSettings.setInterfaceLook(hit.value);
                        this.saveQuietly();
                    }
                    return true;
                }
            }
            for (SegHit hit : this.layoutHits) {
                if (WhiteMenu.isHovered(mx, my, hit.x, hit.y, hit.w, hit.h)) {
                    if (!MenuSettings.buttonLayout().equals(hit.value)) {
                        this.clickSound();
                        MenuSettings.setButtonLayout(hit.value);
                        this.saveQuietly();
                    }
                    return true;
                }
            }
            for (ThemeHit hit : this.themeHits) {
                if (WhiteMenu.isHovered(mx, my, hit.x, hit.y, hit.w, hit.h)) {
                    this.clickSound();
                    ThemeManager.set(hit.theme);
                    return true;
                }
            }
            for (BgHit hit : this.bgHits) {
                if (WhiteMenu.isHovered(mx, my, hit.x, hit.y, hit.w, hit.h)) {
                    this.clickSound();
                    MenuBackgrounds.select(hit.entry);
                    this.scrollTarget = this.scroll;
                    return true;
                }
            }
        }
        return super.mouseClicked(click, doubled);
    }

    @Override
    public boolean mouseReleased(Click click) {
        boolean saved = false;
        if (this.draggingScale) {
            this.draggingScale = false;
            saved = true;
        }
        if (this.glassDragIdx >= 0) {
            this.glassDragIdx = -1;
            saved = true;
        }
        if (saved) {
            try {
                MenuSettings.save();
            } catch (Throwable ignored) {
            }
            return true;
        }
        return super.mouseReleased(click);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        this.scrollTarget -= (float) (verticalAmount * 34.0f);
        return true;
    }

    @Override
    public void close() {
        // запоминаем, где остановился просмотр
        savedScroll = this.scroll;
        pendingRestore = false;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc != null) {
            mc.setScreen(this.parent);
        } else {
            super.close();
        }
        try {
            MenuSettings.save();
        } catch (Throwable ignored) {
        }
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    private void clickSound() {
        try {
            SoundManager.playSoundDirect(SoundManager.BUTTON_CLICK, 0.6f, 1.0f);
        } catch (Throwable ignored) {
        }
    }
}
