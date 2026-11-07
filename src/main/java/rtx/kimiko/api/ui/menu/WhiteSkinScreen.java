package rtx.kimiko.api.ui.menu;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.PlayerSkinWidget;
import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.entity.player.SkinTextures;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import rtx.kimiko.api.ui.menu.alts.SkinLibrary;
import rtx.kimiko.api.ui.theme.ThemeManager;
import rtx.kimiko.utils.animations.Easings;
import rtx.kimiko.utils.animations.SmoothAnimation;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.sounds.SoundManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Гардероб скинов аккаунта — по образцу меню Quick Skin, в стиле белого меню Kimiko:
 * слева сетка библиотеки скинов, справа большой 3D-превью игрока.
 * Выбор плитки сразу показывает скин на модели; «Применить» назначает его аккаунту.
 */
public class WhiteSkinScreen extends Screen {
    private final Screen parent;
    private final String accountName;
    private final SmoothAnimation alpha = new SmoothAnimation();

    private float scaleFix = 1.0f;
    private float lastMouseX;
    private float lastMouseY;
    private float scroll;
    private float scrollTarget;

    private List<String> libs = new ArrayList<>();
    private String selectedKey;
    private String status;
    private boolean statusError;

    private float panelX;
    private float panelY;
    private float panelW;
    private float panelH;
    private float gridX;
    private float gridY;
    private float gridW;
    private float gridH;
    private float tile;
    private float tileGap;
    private int cols;

    private float fileX;
    private float fileY;
    private float fileW;
    private float fileH;
    private float urlFieldX;
    private float urlFieldY;
    private float urlFieldW;
    private float urlFieldH;
    private float urlBtnX;
    private float urlBtnY;
    private float urlBtnW;

    private float rightX;
    private float rightY;
    private float rightW;
    private float rightH;
    private float pvX;
    private float pvY;
    private float pvW;
    private float pvH;
    private float apX;
    private float apY;
    private float apW;
    private float apH;
    private float rmX;
    private float rmY;
    private float rmW;
    private float rmH;
    private float dlX;
    private float dlY;
    private float dlW;
    private float dlH;
    private float backX;
    private float backY;
    private float backW;
    private float backH;

    private String urlText = "";
    private boolean urlFocused;

    private PlayerSkinWidget skinWidget;
    private float skinWidgetScaleFix = -1.0f;

    public WhiteSkinScreen(Screen parent, String accountName) {
        super(Text.literal("WhiteSkins"));
        this.parent = parent;
        this.accountName = accountName == null ? "" : accountName;
    }

    @Override
    protected void init() {
        this.alpha.setValue(0.0);
        this.alpha.run(1.0, 0.4, Easings.QUAD_OUT, false);
        this.libs = SkinLibrary.libKeys();
        if (this.selectedKey != null && !this.libs.contains(this.selectedKey)) {
            this.selectedKey = null;
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
        float k = Math.max(0.85f, sh / 540.0f);

        float dt = Math.max(0.0f, Math.min(2.0f, delta));
        this.scroll += (this.scrollTarget - this.scroll) * Math.min(1.0f, dt * 12.0f);

        Render2D.beginFrame(context);

        WhiteMenuBackground.draw(sw, sh, a);

        int accent = ThemeManager.accent(255);

        float leftX = sw * 0.045f;
        float heroY = sh * 0.10f;
        Fonts.BOLD.draw("СКИНЫ", leftX, heroY, 9.5f * k, MenuColors.withAlpha(accent, (int) (255 * a)));
        Fonts.EXTRABOLD.draw("Гардероб скинов", leftX, heroY + 16.0f * k, 25.0f * k, MenuColors.rgba(255, 255, 255, (int) (255 * a)));
        Fonts.REGULAR.draw("Аккаунт: " + this.accountName, leftX, heroY + 16.0f * k + 25.0f * k + 8.0f * k, 11.0f * k,
                MenuColors.rgba(255, 255, 255, (int) (165 * a)));

        float top = heroY + 72.0f * k;
        float bottomMargin = 24.0f;

        // левая панель — библиотека
        this.panelW = Math.min(330.0f * k, sw * 0.40f);
        this.panelX = leftX;
        this.panelY = top;
        this.panelH = sh - top - bottomMargin;
        float pad = 12.0f * k;
        float panelRad = 12.0f * k;

        long now = System.currentTimeMillis();
        float wave = 0.5f + 0.5f * (float) Math.sin(now / 2600.0);
        float wave2 = 0.5f + 0.5f * (float) Math.sin(now / 4100.0 + 1.7);

        Render2D.rect(this.panelX, this.panelY, this.panelW, this.panelH, panelRad, MenuColors.rgba(11, 13, 19, (int) (238 * a)));
        Render2D.rect(this.panelX, this.panelY, this.panelW, this.panelH, panelRad,
                MenuColors.withAlpha(accent, (int) ((26 + 16 * wave) * a)),
                MenuColors.withAlpha(accent, (int) ((8 + 10 * wave2) * a)),
                MenuColors.withAlpha(accent, (int) ((6 + 8 * wave) * a)),
                MenuColors.withAlpha(accent, (int) ((22 + 14 * wave2) * a)));
        Render2D.outline(this.panelX, this.panelY, this.panelW, this.panelH, panelRad, 1.0f, MenuColors.rgba(255, 255, 255, (int) (26 * a)));

        Fonts.BOLD.draw("БИБЛИОТЕКА", this.panelX + pad, this.panelY + 10.0f * k, 8.5f * k, MenuColors.rgba(255, 255, 255, (int) (150 * a)));
        String count = this.libs.size() + " шт";
        float cw = Fonts.BOLD.width(count, 8.5f * k);
        Fonts.BOLD.draw(count, this.panelX + this.panelW - pad - cw, this.panelY + 10.0f * k, 8.5f * k,
                MenuColors.withAlpha(accent, (int) (200 * a)));

        // нижний бар импорта
        float barH = 30.0f * k;
        float barY = this.panelY + this.panelH - pad - barH;
        this.fileW = this.panelW - pad * 2.0f;
        this.fileH = barH;
        this.fileX = this.panelX + pad;
        this.fileY = barY;
        float urlRowY = barY - 6.0f * k - 26.0f * k;
        this.urlBtnW = 70.0f * k;
        this.urlFieldH = 26.0f * k;
        this.urlFieldW = this.panelW - pad * 2.0f - this.urlBtnW - 6.0f * k;
        this.urlFieldX = this.panelX + pad;
        this.urlFieldY = urlRowY;
        this.urlBtnX = this.urlFieldX + this.urlFieldW + 6.0f * k;
        this.urlBtnY = urlRowY;

        // сетка плиток
        this.gridX = this.panelX + pad;
        this.gridW = this.panelW - pad * 2.0f;
        this.gridY = this.panelY + 26.0f * k;
        this.gridH = this.urlFieldY - 8.0f * k - this.gridY;
        this.tile = 52.0f * k;
        this.tileGap = 7.0f * k;
        this.cols = Math.max(1, (int) ((this.gridW + this.tileGap) / (this.tile + this.tileGap)));

        float scrollMax = this.maxScroll();
        if (this.scrollTarget > scrollMax) {
            this.scrollTarget = scrollMax;
        } else if (this.scrollTarget < 0.0f) {
            this.scrollTarget = 0.0f;
        }

        Render2D.pushScissor(context, this.gridX, this.gridY, this.gridW, this.gridH);
        float gy = this.gridY - this.scroll;
        for (int i = 0; i < this.libs.size(); i++) {
            int col = i % this.cols;
            int row = i / this.cols;
            float tx = this.gridX + col * (this.tile + this.tileGap);
            float ty = gy + row * (this.tile + this.tileGap);
            if (ty + this.tile >= this.gridY && ty <= this.gridY + this.gridH) {
                this.drawTile(this.libs.get(i), tx, ty, a, k, accent);
            }
        }
        if (this.libs.isEmpty()) {
            String empty = "Библиотека пуста — добавь скины ниже";
            float ew = Fonts.REGULAR.width(empty, 9.0f * k);
            Fonts.REGULAR.draw(empty, this.gridX + (this.gridW - ew) / 2.0f, this.gridY + 18.0f * k, 9.0f * k,
                    MenuColors.rgba(255, 255, 255, (int) (120 * a)));
        }
        Render2D.popScissor(context);

        if (scrollMax > 0.0f) {
            float trackX = this.gridX + this.gridW - 3.0f * k;
            float trackH = this.gridH;
            float contentTotal = trackH + scrollMax;
            float thumbH = Math.max(18.0f * k, trackH * (trackH / contentTotal));
            float clamped = Math.max(0.0f, Math.min(scrollMax, this.scroll));
            float thumbY = this.gridY + (trackH - thumbH) * (clamped / scrollMax);
            Render2D.rect(trackX, this.gridY, 3.0f * k, trackH, 1.5f * k, MenuColors.rgba(255, 255, 255, (int) (16 * a)));
            Render2D.rect(trackX, thumbY, 3.0f * k, thumbH, 1.5f * k, MenuColors.rgba(255, 255, 255, (int) (95 * a)));
        }

        // кнопки импорта
        this.drawSmallButton("Добавить файл", this.fileX, this.fileY, this.fileW, this.fileH, a, k, false);

        Render2D.rect(this.urlFieldX, this.urlFieldY, this.urlFieldW, this.urlFieldH, 7.0f * k,
                MenuColors.rgba(28, 32, 42, (int) (200 * a)));
        Render2D.outline(this.urlFieldX, this.urlFieldY, this.urlFieldW, this.urlFieldH, 7.0f * k, 1.0f,
                MenuColors.rgba(255, 255, 255, (int) ((this.urlFocused ? 90 : 25) * a)));
        String shown = this.urlText.isEmpty() && !this.urlFocused ? "URL скина https://..." : this.urlText;
        int textCol = this.urlText.isEmpty() && !this.urlFocused
                ? MenuColors.rgba(255, 255, 255, (int) (80 * a))
                : MenuColors.rgba(255, 255, 255, (int) (255 * a));
        Fonts.REGULAR.draw(shown, this.urlFieldX + 8.0f * k, this.urlFieldY + (this.urlFieldH - 9.0f * k) / 2.0f, 9.0f * k, textCol);
        if (this.urlFocused && (System.currentTimeMillis() / 500L) % 2L == 0L) {
            float cx = this.urlFieldX + 8.0f * k + Fonts.REGULAR.width(shown, 9.0f * k);
            Render2D.rect(cx, this.urlFieldY + 6.0f * k, 1.0f, this.urlFieldH - 12.0f * k, MenuColors.rgba(255, 255, 255, (int) (220 * a)));
        }
        this.drawSmallButton("Загрузить", this.urlBtnX, this.urlBtnY, this.urlBtnW, this.urlFieldH, a, k, false);

        // правая панель — 3D-превью
        this.rightX = this.panelX + this.panelW + 16.0f * k;
        this.rightW = sw * 0.955f - this.rightX;
        this.rightY = top;
        this.rightH = this.panelH;

        Render2D.rect(this.rightX, this.rightY, this.rightW, this.rightH, panelRad, MenuColors.rgba(11, 13, 19, (int) (238 * a)));
        Render2D.rect(this.rightX, this.rightY, this.rightW, this.rightH, panelRad,
                MenuColors.withAlpha(accent, (int) ((26 + 16 * wave2) * a)),
                MenuColors.rgba(255, 255, 255, (int) ((6 + 6 * wave) * a)),
                MenuColors.withAlpha(accent, (int) ((10 + 8 * wave) * a)),
                MenuColors.withAlpha(accent, (int) ((22 + 14 * wave2) * a)));
        Render2D.outline(this.rightX, this.rightY, this.rightW, this.rightH, panelRad, 1.0f, MenuColors.rgba(255, 255, 255, (int) (26 * a)));

        this.pvW = Math.min(150.0f * k, this.rightW - pad * 2.0f);
        this.pvH = 200.0f * k;
        this.pvX = this.rightX + (this.rightW - this.pvW) / 2.0f;
        this.pvY = this.rightY + 14.0f * k;

        // подпись выбранного
        String label;
        if (this.selectedKey != null) {
            label = "Выбран: " + this.selectedKey;
        } else if (SkinLibrary.has(this.accountName)) {
            label = "Текущий скин аккаунта";
        } else {
            label = "Стив (по умолчанию)";
        }
        float lw = Fonts.SEMIBOLD.width(label, 10.0f * k);
        Fonts.SEMIBOLD.draw(label, this.rightX + (this.rightW - lw) / 2.0f, this.pvY + this.pvH + 6.0f * k, 10.0f * k,
                MenuColors.rgba(255, 255, 255, (int) (230 * a)));

        float btnTop = this.pvY + this.pvH + 24.0f * k;
        float btnH = 26.0f * k;
        this.apW = this.rightW - pad * 2.0f;
        this.apH = btnH;
        this.apX = this.rightX + pad;
        this.apY = btnTop;
        this.drawSmallButton("Применить к аккаунту", this.apX, this.apY, this.apW, this.apH, a, k, true);

        float halfW = (this.apW - 6.0f * k) / 2.0f;
        this.rmW = halfW;
        this.rmH = btnH;
        this.rmX = this.apX;
        this.rmY = this.apY + btnH + 6.0f * k;
        this.dlW = halfW;
        this.dlH = btnH;
        this.dlX = this.rmX + halfW + 6.0f * k;
        this.dlY = this.rmY;
        this.drawSmallButton("Снять скин", this.rmX, this.rmY, this.rmW, this.rmH, a, k, false);
        this.drawSmallButton("Удалить", this.dlX, this.dlY, this.dlW, this.dlH, a, k, false);

        if (this.status != null) {
            int col2 = this.statusError ? MenuColors.rgba(255, 130, 130, (int) (255 * a)) : MenuColors.withAlpha(accent, (int) (255 * a));
            Fonts.REGULAR.draw(this.status, this.apX, this.dlY + this.dlH + 7.0f * k, 9.0f * k, col2);
        }

        this.backW = 120.0f * k;
        this.backH = 26.0f * k;
        this.backX = this.rightX + (this.rightW - this.backW) / 2.0f;
        this.backY = this.rightY + this.rightH - pad - this.backH;
        this.drawSmallButton("Назад", this.backX, this.backY, this.backW, this.backH, a, k, false);

        Render2D.flush();

        // 3D-превью поверх: виджет живёт в GUI-координатах (после flush ничего не перерисует его область)
        this.ensureSkinWidget(mc);
        if (this.skinWidget != null) {
            this.skinWidget.setX((int) (this.pvX * this.scaleFix));
            this.skinWidget.setY((int) (this.pvY * this.scaleFix));
            try {
                this.skinWidget.render(context, (int) (mx * this.scaleFix), (int) (my * this.scaleFix), delta);
            } catch (Throwable ignored) {
            }
        }
    }

    private void drawTile(String key, float x, float y, float a, float k, int accent) {
        boolean hov = WhiteMenu.isHovered(this.lastMouseX, this.lastMouseY, x, y, this.tile, this.tile);
        boolean sel = key.equals(this.selectedKey);
        Render2D.rect(x, y, this.tile, this.tile, 9.0f * k, MenuColors.rgba(28 + (hov ? 12 : 0), 30 + (hov ? 12 : 0), 38 + (hov ? 14 : 0), (int) (200 * a)));
        // статичный мини-бюст по центру плитки: nearest-сэмплер = чёткие пиксели без мыла
        net.minecraft.util.Identifier thumb = SkinLibrary.thumbTexture(key);
        if (thumb != null) {
            float bh = this.tile * 0.82f;
            float bw = bh * 0.5f;
            float bx = x + (this.tile - bw) / 2.0f;
            float by = y + (this.tile - bh) / 2.0f;
            Render2D.imageUvNearest(thumb.toString(), bx, by, bw, bh, 0.0f, 0.5f,
                    0.0f, 0.0f, 1.0f, 1.0f, MenuColors.rgba(255, 255, 255, 255));
        }
        if (sel) {
            Render2D.outline(x + 0.7f * k, y + 0.7f * k, this.tile - 1.4f * k, this.tile - 1.4f * k, 9.0f * k, 2.2f,
                    MenuColors.withAlpha(accent, (int) (255 * a)));
            Render2D.outline(x + 2.2f * k, y + 2.2f * k, this.tile - 4.4f * k, this.tile - 4.4f * k, 8.0f * k, 1.0f,
                    MenuColors.rgba(255, 255, 255, (int) (80 * a)));
        } else if (hov) {
            Render2D.outline(x, y, this.tile, this.tile, 9.0f * k, 1.4f, MenuColors.rgba(255, 255, 255, (int) (120 * a)));
        }
    }

    /** Лениво создаёт 3D-виджет: выбранная плитка → скин аккаунта → Стив. */
    private void ensureSkinWidget(MinecraftClient mc) {
        if (mc.getLoadedEntityModels() == null) {
            return;
        }
        if (this.skinWidget == null || Math.abs(this.skinWidgetScaleFix - this.scaleFix) > 0.01f) {
            this.skinWidgetScaleFix = this.scaleFix;
            float fix = this.scaleFix;
            this.skinWidget = new PlayerSkinWidget(Math.max(60, (int) (this.pvW * fix)), Math.max(90, (int) (this.pvH * fix)),
                    mc.getLoadedEntityModels(), this::previewSkin);
        }
    }

    private SkinTextures previewSkin() {
        if (this.selectedKey != null) {
            SkinTextures lib = SkinLibrary.libSkin(this.selectedKey);
            if (lib != null) {
                return lib;
            }
        }
        SkinTextures own = SkinLibrary.skinFor(this.accountName);
        if (own != null) {
            return own;
        }
        return DefaultSkinHelper.getSteve();
    }

    private float maxScroll() {
        int rows = (this.libs.size() + this.cols - 1) / this.cols;
        float total = rows * (this.tile + this.tileGap) + 16.0f * k();
        return Math.max(0.0f, total - this.gridH);
    }

    private float k() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null || mc.getWindow() == null) {
            return 1.0f;
        }
        return Math.max(0.85f, ((float) mc.getWindow().getScaledHeight() / this.scaleFix) / 540.0f);
    }

    private void drawSmallButton(String text, float x, float y, float w, float h, float a, float k, boolean primary) {
        boolean hov = WhiteMenu.isHovered(this.lastMouseX, this.lastMouseY, x, y, w, h);
        int bg;
        if (primary) {
            bg = MenuColors.withAlpha(ThemeManager.accent(255), (int) ((hov ? 255 : 220) * a));
        } else {
            bg = MenuColors.rgba(28 + (hov ? 14 : 0), 30 + (hov ? 14 : 0), 38 + (hov ? 16 : 0), (int) (200 * a));
        }
        Render2D.rect(x, y, w, h, 7.0f * k, bg);
        if (!primary) {
            Render2D.outline(x, y, w, h, 7.0f * k, 1.0f, MenuColors.rgba(255, 255, 255, (int) (25 * a)));
        }
        float tw = Fonts.SEMIBOLD.width(text, 10.0f * k);
        Fonts.SEMIBOLD.draw(text, x + (w - tw) / 2.0f, y + (h - 10.0f * k) / 2.0f, 10.0f * k,
                MenuColors.rgba(255, 255, 255, (int) (255 * a)));
    }

    private boolean inGrid(float mx, float my) {
        return WhiteMenu.isHovered(mx, my, this.gridX, this.gridY, this.gridW, this.gridH);
    }

    /** Плитка под мышью или null. */
    private String tileAt(float mx, float my) {
        if (!inGrid(mx, my) || this.libs.isEmpty()) {
            return null;
        }
        int col = (int) ((mx - this.gridX) / (this.tile + this.tileGap));
        if (col < 0 || col >= this.cols) {
            return null;
        }
        float relY = my - this.gridY + this.scroll;
        int row = (int) (relY / (this.tile + this.tileGap));
        if (row < 0) {
            return null;
        }
        float inX = mx - this.gridX - col * (this.tile + this.tileGap);
        float inY = relY - row * (this.tile + this.tileGap);
        if (inX > this.tile || inY > this.tile) {
            return null;
        }
        int idx = row * this.cols + col;
        if (idx < 0 || idx >= this.libs.size()) {
            return null;
        }
        return this.libs.get(idx);
    }

    @Override
    public boolean mouseClicked(Click click, boolean doubled) {
        float mx = (float) (click.x() / this.scaleFix);
        float my = (float) (click.y() / this.scaleFix);

        if (click.button() == 0) {
            this.urlFocused = WhiteMenu.isHovered(mx, my, this.urlFieldX, this.urlFieldY, this.urlFieldW, this.urlFieldH);

            // 3D-превью: передаём клик виджету (вращение мышью)
            if (this.skinWidget != null) {
                float gx = this.pvX * this.scaleFix;
                float gy = this.pvY * this.scaleFix;
                float gw = this.pvW * this.scaleFix;
                float gh = this.pvH * this.scaleFix;
                if (click.x() >= gx && click.x() <= gx + gw && click.y() >= gy && click.y() <= gy + gh) {
                    boolean taken = false;
                    try {
                        taken = this.skinWidget.mouseClicked(click, doubled);
                    } catch (Throwable ignored) {
                    }
                    if (taken) {
                        return true;
                    }
                }
            }

            String t = this.tileAt(mx, my);
            if (t != null) {
                this.clickSound();
                this.selectedKey = t;
                this.status = null;
                return true;
            }

            if (WhiteMenu.isHovered(mx, my, this.fileX, this.fileY, this.fileW, this.fileH)) {
                this.clickSound();
                this.openFilePicker();
                return true;
            }
            if (WhiteMenu.isHovered(mx, my, this.urlBtnX, this.urlBtnY, this.urlBtnW, this.urlFieldH)) {
                this.clickSound();
                this.importUrl(this.urlText.trim());
                return true;
            }
            if (WhiteMenu.isHovered(mx, my, this.apX, this.apY, this.apW, this.apH)) {
                this.clickSound();
                if (this.selectedKey == null) {
                    this.status = "Сначала выбери скин в библиотеке";
                    this.statusError = true;
                } else {
                    String err = SkinLibrary.apply(this.accountName, this.selectedKey);
                    if (err == null) {
                        SkinHeads.invalidate(this.accountName);
                        this.status = "Скин применён к " + this.accountName;
                        this.statusError = false;
                    } else {
                        this.status = err;
                        this.statusError = true;
                    }
                }
                return true;
            }
            if (WhiteMenu.isHovered(mx, my, this.rmX, this.rmY, this.rmW, this.rmH)) {
                this.clickSound();
                String err = SkinLibrary.remove(this.accountName);
                if (err == null) {
                    SkinHeads.invalidate(this.accountName);
                    this.status = "Скин аккаунта снят";
                    this.statusError = false;
                } else {
                    this.status = err;
                    this.statusError = true;
                }
                return true;
            }
            if (WhiteMenu.isHovered(mx, my, this.dlX, this.dlY, this.dlW, this.dlH)) {
                this.clickSound();
                if (this.selectedKey == null) {
                    this.status = "Сначала выбери скин в библиотеке";
                    this.statusError = true;
                } else {
                    String err = SkinLibrary.deleteLib(this.selectedKey);
                    this.libs = SkinLibrary.libKeys();
                    if (err == null) {
                        this.selectedKey = null;
                        this.status = "Скин удалён из библиотеки";
                        this.statusError = false;
                    } else {
                        this.status = err;
                        this.statusError = true;
                    }
                }
                return true;
            }
            if (WhiteMenu.isHovered(mx, my, this.backX, this.backY, this.backW, this.backH)) {
                this.clickSound();
                MinecraftClient mc = MinecraftClient.getInstance();
                if (mc != null) {
                    mc.setScreen(this.parent);
                }
                return true;
            }
        }
        return super.mouseClicked(click, doubled);
    }

    @Override
    public boolean mouseDragged(Click click, double draggedX, double draggedY) {
        if (this.skinWidget != null) {
            try {
                if (this.skinWidget.mouseDragged(click, draggedX, draggedY)) {
                    return true;
                }
            } catch (Throwable ignored) {
            }
        }
        return super.mouseDragged(click, draggedX, draggedY);
    }

    @Override
    public boolean mouseReleased(Click click) {
        if (this.skinWidget != null) {
            try {
                this.skinWidget.mouseReleased(click);
            } catch (Throwable ignored) {
            }
        }
        return super.mouseReleased(click);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        float mx = (float) (mouseX / this.scaleFix);
        float my = (float) (mouseY / this.scaleFix);
        if (inGrid(mx, my)) {
            this.scrollTarget -= (float) (verticalAmount * 24.0f);
            if (this.scrollTarget < 0.0f) {
                this.scrollTarget = 0.0f;
            }
            float max = this.maxScroll();
            if (this.scrollTarget > max) {
                this.scrollTarget = max;
            }
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    /** Перетаскивание .png на окно — импорт в библиотеку. */
    @Override
    public void onFilesDropped(List<java.nio.file.Path> files) {
        for (java.nio.file.Path p : files) {
            if (p.toString().toLowerCase(Locale.ROOT).endsWith(".png")) {
                this.importPathAsync(p.toString());
                return;
            }
        }
    }

    @Override
    public boolean charTyped(net.minecraft.client.input.CharInput input) {
        if (this.urlFocused && this.urlText.length() < 256) {
            int cp = input.codepoint();
            if (cp >= 32 && cp <= 0xFFFF) {
                this.urlText += (char) cp;
                return true;
            }
            return true;
        }
        return super.charTyped(input);
    }

    @Override
    public boolean keyPressed(net.minecraft.client.input.KeyInput input) {
        int keyCode = input.key();
        if (this.urlFocused) {
            if (keyCode == GLFW.GLFW_KEY_BACKSPACE && !this.urlText.isEmpty()) {
                this.urlText = this.urlText.substring(0, this.urlText.length() - 1);
                return true;
            }
            if (keyCode == GLFW.GLFW_KEY_ENTER) {
                this.importUrl(this.urlText.trim());
                return true;
            }
            if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
                this.urlFocused = false;
                return true;
            }
        }
        return super.keyPressed(input);
    }

    private void importUrl(String url) {
        if (url.isEmpty()) {
            this.status = "Введи URL скина";
            this.statusError = true;
            return;
        }
        this.status = "Загрузка...";
        this.statusError = false;
        Thread worker = new Thread(() -> {
            SkinLibrary.Import res = SkinLibrary.importToLibrary(url);
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc == null) {
                return;
            }
            mc.execute(() -> {
                this.libs = SkinLibrary.libKeys();
                if (res.error() == null) {
                    this.selectedKey = res.key();
                    this.urlText = "";
                    this.status = "Скин добавлен: " + res.key();
                    this.statusError = false;
                } else {
                    this.status = res.error();
                    this.statusError = true;
                }
            });
        }, "Kimiko-SkinUrl");
        worker.setDaemon(true);
        worker.start();
    }

    private void importPathAsync(String path) {
        this.status = "Импорт...";
        this.statusError = false;
        Thread worker = new Thread(() -> {
            SkinLibrary.Import res = SkinLibrary.importToLibrary(path);
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc == null) {
                return;
            }
            mc.execute(() -> {
                this.libs = SkinLibrary.libKeys();
                if (res.error() == null) {
                    this.selectedKey = res.key();
                    this.status = "Скин добавлен: " + res.key();
                    this.statusError = false;
                } else {
                    this.status = res.error();
                    this.statusError = true;
                }
            });
        }, "Kimiko-SkinFile");
        worker.setDaemon(true);
        worker.start();
    }

    /** Нативный диалог выбора файла (LWJGL tinyfd, как в ванилле) в отдельном потоке; затем импорт. */
    private void openFilePicker() {
        Thread worker = new Thread(() -> {
            String path = null;
            try (org.lwjgl.system.MemoryStack stack = org.lwjgl.system.MemoryStack.stackPush()) {
                org.lwjgl.PointerBuffer filter = stack.mallocPointer(1);
                filter.put(stack.UTF8("*.png"));
                filter.flip();
                String file = org.lwjgl.util.tinyfd.TinyFileDialogs.tinyfd_openFileDialog(
                        "Выбери скин Minecraft (.png)", "", filter, "Скин Minecraft (*.png)", false);
                if (file != null && !file.isEmpty()) {
                    path = file;
                }
            } catch (Throwable t) {
                String msg = "Диалог недоступен: " + t.getMessage();
                MinecraftClient mc2 = MinecraftClient.getInstance();
                if (mc2 != null) {
                    mc2.execute(() -> {
                        this.status = msg;
                        this.statusError = true;
                    });
                }
            }
            if (path == null) {
                return;
            }
            String finalPath = path;
            MinecraftClient mc2 = MinecraftClient.getInstance();
            if (mc2 == null) {
                return;
            }
            mc2.execute(() -> {
                if (!finalPath.toLowerCase(Locale.ROOT).endsWith(".png")) {
                    this.status = "Нужен .png файл";
                    this.statusError = true;
                    return;
                }
                this.importPathAsync(finalPath);
            });
        }, "Kimiko-SkinPicker");
        worker.setDaemon(true);
        worker.start();
    }

    @Override
    public void close() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc != null) {
            mc.setScreen(this.parent);
        } else {
            super.close();
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
