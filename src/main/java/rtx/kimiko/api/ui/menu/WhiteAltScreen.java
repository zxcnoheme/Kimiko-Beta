package rtx.kimiko.api.ui.menu;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import rtx.kimiko.api.ui.menu.alts.SkinLibrary;
import rtx.kimiko.api.ui.menu.alts.WhiteAccount;
import rtx.kimiko.api.ui.menu.alts.WhiteAltManager;
import rtx.kimiko.api.ui.mainmenu.MenuSettings;
import rtx.kimiko.api.ui.theme.ThemeManager;
import rtx.kimiko.utils.animations.Easings;
import rtx.kimiko.utils.animations.SmoothAnimation;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.sounds.SoundManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class WhiteAltScreen extends Screen {
    private final Screen parent;
    private final SmoothAnimation alpha = new SmoothAnimation();

    private float scaleFix = 1.0f;
    private float lastMouseX;
    private float lastMouseY;

    private float appliedScale = -1.0f;
    private long lastScaleNanos = 0L;

    private String inputText = "";
    private boolean inputFocused;
    private float scroll;
    private float scrollTarget;

    private float fieldX;
    private float fieldY;
    private float fieldW;
    private float fieldH;
    private float addX;
    private float addY;
    private float addW;
    private float addH;
    private float randX;
    private float randY;
    private float randW;
    private float randH;
    private float backX;
    private float backY;
    private float backW;
    private float backH;
    private float listX;
    private float listY;
    private float listW;
    private float listH;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public WhiteAltScreen(Screen parent) {
        super(Text.literal("WhiteAlts"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        this.alpha.setValue(0.0);
        this.alpha.run(1.0, 0.4, Easings.QUAD_OUT, false);
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

        float dt = Math.max(0.0f, Math.min(2.0f, delta));
        this.scroll += (this.scrollTarget - this.scroll) * Math.min(1.0f, dt * 12.0f);

        Render2D.beginFrame(context);

        WhiteMenuBackground.draw(sw, sh, a);

        int accent = ThemeManager.accent(255);

        float leftX = sw * 0.045f;
        float heroY = sh * 0.10f;
        Fonts.BOLD.draw("АККАУНТЫ", leftX, heroY, 9.5f * k, MenuColors.withAlpha(accent, (int) (255 * a)));
        Fonts.EXTRABOLD.draw("Смена аккаунта", leftX, heroY + 16.0f * k, 25.0f * k, MenuColors.rgba(255, 255, 255, (int) (255 * a)));
        String active = WhiteAltManager.get().currentName();
        Fonts.REGULAR.draw("Активен: " + active, leftX, heroY + 16.0f * k + 25.0f * k + 8.0f * k, 11.0f * k, MenuColors.rgba(255, 255, 255, (int) (165 * a)));

        float panelW = Math.min(430.0f * k, sw - 40.0f);
        float panelX = (sw - panelW) / 2.0f;
        float panelY = heroY + 78.0f * k;
        float panelH = sh - panelY - 24.0f;
        float panelRad = 12.0f * k;

        // сплошная тёмная панель с мягким переливом акцента (без blur)
        long now = System.currentTimeMillis();
        float wave = 0.5f + 0.5f * (float) Math.sin(now / 2600.0);
        float wave2 = 0.5f + 0.5f * (float) Math.sin(now / 4100.0 + 1.7);

        Render2D.rect(panelX, panelY, panelW, panelH, panelRad, MenuColors.rgba(11, 13, 19, (int) (238 * a)));
        Render2D.rect(panelX, panelY, panelW, panelH, panelRad,
                MenuColors.withAlpha(accent, (int) ((26 + 16 * wave) * a)),
                MenuColors.withAlpha(accent, (int) ((8 + 10 * wave2) * a)),
                MenuColors.withAlpha(accent, (int) ((6 + 8 * wave) * a)),
                MenuColors.withAlpha(accent, (int) ((22 + 14 * wave2) * a)));
        Render2D.outline(panelX, panelY, panelW, panelH, panelRad, 1.0f, MenuColors.rgba(255, 255, 255, (int) (26 * a)));

        float pad = 12.0f * k;
        this.fieldW = panelW - pad * 3.0f - 150.0f * k;
        this.fieldH = 30.0f * k;
        this.fieldX = panelX + pad;
        this.fieldY = panelY + pad;
        this.addW = 72.0f * k;
        this.addH = this.fieldH;
        this.addX = this.fieldX + this.fieldW + 6.0f * k;
        this.addY = this.fieldY;
        this.randW = 72.0f * k;
        this.randH = this.fieldH;
        this.randX = this.addX + this.addW + 6.0f * k;
        this.randY = this.fieldY;

        Render2D.rect(this.fieldX, this.fieldY, this.fieldW, this.fieldH, 7.0f * k,
                MenuColors.rgba(28, 32, 42, (int) (200 * a)));
        Render2D.outline(this.fieldX, this.fieldY, this.fieldW, this.fieldH, 7.0f * k, 1.0f,
                MenuColors.rgba(255, 255, 255, (int) ((this.inputFocused ? 90 : 25) * a)));
        String shown = this.inputText.isEmpty() && !this.inputFocused ? "Никнейм..." : this.inputText;
        int textCol = this.inputText.isEmpty() && !this.inputFocused
                ? MenuColors.rgba(255, 255, 255, (int) (90 * a))
                : MenuColors.rgba(255, 255, 255, (int) (255 * a));
        Fonts.REGULAR.draw(shown, this.fieldX + 9.0f * k, this.fieldY + (this.fieldH - 10.0f * k) / 2.0f, 10.0f * k, textCol);
        if (this.inputFocused && (System.currentTimeMillis() / 500L) % 2L == 0L) {
            float cx = this.fieldX + 9.0f * k + Fonts.REGULAR.width(this.inputText, 10.0f * k);
            Render2D.rect(cx, this.fieldY + 7.0f * k, 1.0f, this.fieldH - 14.0f * k, MenuColors.rgba(255, 255, 255, (int) (220 * a)));
        }

        this.drawSmallButton("Добавить", this.addX, this.addY, this.addW, this.addH, a, k, true);
        this.drawSmallButton("Случайно", this.randX, this.randY, this.randW, this.randH, a, k, false);

        this.listX = panelX + pad;
        this.listY = this.fieldY + this.fieldH + 8.0f * k;
        this.listW = panelW - pad * 2.0f;
        this.listH = panelY + panelH - pad - this.listY;

        // мягкий clamp скролла (аккаунты могли удалить/добавить)
        float scrollMax = this.maxScroll();
        if (this.scrollTarget > scrollMax) {
            this.scrollTarget = scrollMax;
        } else if (this.scrollTarget < 0.0f) {
            this.scrollTarget = 0.0f;
        }

        List<WhiteAccount> accounts = new ArrayList<>(WhiteAltManager.get().getSorted());
        float rowH = 36.0f * k;
        float rowGap = 6.0f * k;

        Render2D.pushScissor(context, this.listX, this.listY, this.listW, this.listH);

        // без тёмной подложки — панель просвечивает между карточками, нет «чёрных щелей»
        float y = this.listY - this.scroll;

        for (WhiteAccount acc : accounts) {
            if (y + rowH >= this.listY && y <= this.listY + this.listH) {
                this.drawRow(context, acc, this.listX, y, this.listW, rowH, a, k);
            }
            y += rowH + rowGap;
        }
        if (accounts.isEmpty()) {
            String empty = "Список пуст — добавь ник выше";
            Fonts.REGULAR.draw(empty, this.listX + (this.listW - Fonts.REGULAR.width(empty, 10.0f * k)) / 2.0f,
                    this.listY + 20.0f * k, 10.0f * k, MenuColors.rgba(255, 255, 255, (int) (120 * a)));
        }

        Render2D.popScissor(context);

        // тонкий скролл-бар
        if (scrollMax > 0.0f) {
            float trackX = this.listX + this.listW - 3.0f * k;
            float trackH = this.listH;
            float contentTotal = trackH + scrollMax;
            float thumbH = Math.max(20.0f * k, trackH * (trackH / contentTotal));
            float clamped = Math.max(0.0f, Math.min(scrollMax, this.scroll));
            float thumbY = this.listY + (trackH - thumbH) * (clamped / scrollMax);
            Render2D.rect(trackX, this.listY, 3.0f * k, trackH, 1.5f * k, MenuColors.rgba(255, 255, 255, (int) (16 * a)));
            Render2D.rect(trackX, thumbY, 3.0f * k, thumbH, 1.5f * k, MenuColors.rgba(255, 255, 255, (int) (95 * a)));
        }

        this.backW = 120.0f * k;
        this.backH = 30.0f * k;
        this.backX = panelX + (panelW - this.backW) / 2.0f;
        this.backY = panelY + panelH + 8.0f * k;
        if (this.backY + this.backH < sh - 6.0f) {
            this.drawSmallButton("Назад", this.backX, this.backY, this.backW, this.backH, a, k, false);
        } else {
            this.backH = 0.0f;
        }

        Render2D.flush();
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

    private void drawRow(DrawContext context, WhiteAccount acc, float x, float y, float w, float h, float a, float k) {
        boolean hov = WhiteMenu.isHovered(this.lastMouseX, this.lastMouseY, x, y, w, h);
        boolean current = acc.name.equalsIgnoreCase(WhiteAltManager.get().currentName());
        if (current) {
            // активная карточка подсвечена акцентом
            Render2D.rect(x, y, w, h, 8.0f * k, MenuColors.rgba(34, 40, 50, (int) (205 * a)));
            Render2D.outline(x, y, w, h, 8.0f * k, 1.4f, MenuColors.withAlpha(ThemeManager.accent(255), (int) (200 * a)));
        } else {
            Render2D.rect(x, y, w, h, 8.0f * k, MenuColors.rgba(28 + (hov ? 12 : 0), 30 + (hov ? 12 : 0), 38 + (hov ? 14 : 0), (int) (185 * a)));
            Render2D.outline(x, y, w, h, 8.0f * k, 1.0f, MenuColors.rgba(255, 255, 255, (int) ((hov ? 60 : 20) * a)));
        }

        float headSize = 24.0f * k;
        float hx = x + 8.0f * k;
        float hy = y + (h - headSize) / 2.0f;
        UUID uuid = null;
        try {
            uuid = acc.offlineUuid();
        } catch (Throwable ignored) {
        }
        SkinHeads.drawHead(context, acc.name, uuid, hx, hy, headSize);

        float tx = hx + headSize + 8.0f * k;
        Fonts.SEMIBOLD.draw(acc.name, tx, y + 7.0f * k, 11.0f * k, MenuColors.rgba(255, 255, 255, (int) (255 * a)));
        String date = this.dateFormat.format(new Date(acc.created));
        Fonts.REGULAR.draw(date, tx, y + 21.0f * k, 8.0f * k, MenuColors.rgba(255, 255, 255, (int) (110 * a)));

        // кнопки справа: «Скин», звезда и «Удалить»
        float btnH = 22.0f * k;
        float delW = 62.0f * k;
        float starW = 24.0f * k;
        float skinW = 52.0f * k;
        float delX = x + w - delW - 5.0f * k;
        float starX = delX - starW - 6.0f * k;
        float skinX = starX - skinW - 6.0f * k;
        float btnY = y + (h - btnH) / 2.0f;

        boolean skinHov = WhiteMenu.isHovered(this.lastMouseX, this.lastMouseY, skinX, btnY, skinW, btnH);
        boolean hasSkin = SkinLibrary.has(acc.name);
        Render2D.rect(skinX, btnY, skinW, btnH, 6.0f * k,
                MenuColors.rgba(28 + (skinHov ? 16 : 0), 30 + (skinHov ? 16 : 0), 38 + (skinHov ? 20 : 0), (int) (215 * a)));
        Render2D.outline(skinX, btnY, skinW, btnH, 6.0f * k, 1.0f,
                hasSkin ? MenuColors.withAlpha(ThemeManager.accent(255), (int) ((skinHov ? 220 : 150) * a))
                        : MenuColors.rgba(255, 255, 255, (int) ((skinHov ? 110 : 25) * a)));
        float skinTw = Fonts.SEMIBOLD.width("Скин", 8.5f * k);
        Fonts.SEMIBOLD.draw("Скин", skinX + (skinW - skinTw) / 2.0f, btnY + (btnH - 8.5f * k) / 2.0f, 8.5f * k,
                hasSkin ? MenuColors.withAlpha(ThemeManager.accent(255), (int) (255 * a))
                        : MenuColors.rgba(255, 255, 255, (int) ((skinHov ? 255 : 170) * a)));

        boolean delHov = WhiteMenu.isHovered(this.lastMouseX, this.lastMouseY, delX, btnY, delW, btnH);
        Render2D.rect(delX, btnY, delW, btnH, 6.0f * k,
                MenuColors.rgba((delHov ? 120 : 88), (delHov ? 52 : 40), (delHov ? 52 : 44), (int) (215 * a)));
        Render2D.outline(delX, btnY, delW, btnH, 6.0f * k, 1.0f,
                MenuColors.rgba(255, 140, 140, (int) ((delHov ? 190 : 70) * a)));
        float delTw = Fonts.SEMIBOLD.width("Удалить", 8.5f * k);
        Fonts.SEMIBOLD.draw("Удалить", delX + (delW - delTw) / 2.0f, btnY + (btnH - 8.5f * k) / 2.0f, 8.5f * k,
                MenuColors.rgba(255, 210, 210, (int) (255 * a)));

        boolean starHov = WhiteMenu.isHovered(this.lastMouseX, this.lastMouseY, starX, btnY, starW, btnH);
        Render2D.rect(starX, btnY, starW, btnH, 6.0f * k,
                MenuColors.rgba(28 + (starHov ? 16 : 0), 30 + (starHov ? 16 : 0), 38 + (starHov ? 20 : 0), (int) (215 * a)));
        if (acc.favorite) {
            Render2D.outline(starX, btnY, starW, btnH, 6.0f * k, 1.0f, MenuColors.rgba(255, 210, 90, (int) (170 * a)));
        } else if (starHov) {
            Render2D.outline(starX, btnY, starW, btnH, 6.0f * k, 1.0f, MenuColors.rgba(255, 255, 255, (int) (110 * a)));
        }
        // звезда-иконка: пустая контуром, избранная — заполненная жёлтым; линейная фильтрация = сглаженные края
        float scx = starX + starW * 0.5f;
        float scy = btnY + btnH * 0.5f;
        int starCol = acc.favorite ? MenuColors.rgba(255, 210, 90, (int) (255 * a)) : MenuColors.rgba(255, 255, 255, (int) ((starHov ? 220 : 130) * a));
        float iconS = 16.0f * k;
        String starTex = acc.favorite ? "kimiko:textures/menu/star_fill.png" : "kimiko:textures/menu/star_line.png";
        Render2D.imageUv(starTex, scx - iconS * 0.5f, scy - iconS * 0.5f, iconS, iconS, 0.0f, 0.0f,
                0.001f, 0.001f, 0.999f, 0.999f, starCol);

        if (current) {
            String tag = "АКТИВЕН";
            float tagW = Fonts.BOLD.width(tag, 7.5f * k);
            float tagX = skinX - tagW - 10.0f * k;
            Render2D.rect(tagX - 5.0f * k, y + (h - 14.0f * k) / 2.0f, tagW + 10.0f * k, 14.0f * k, 4.0f * k,
                    MenuColors.withAlpha(ThemeManager.accent(255), (int) (230 * a)));
            Fonts.BOLD.draw(tag, tagX, y + (h - 7.5f * k) / 2.0f, 7.5f * k, MenuColors.rgba(255, 255, 255, (int) (255 * a)));
        }
    }

    @Override
    public boolean mouseClicked(Click click, boolean doubled) {
        float mx = (float) (click.x() / this.scaleFix);
        float my = (float) (click.y() / this.scaleFix);
        MinecraftClient mc = MinecraftClient.getInstance();

        if (click.button() == 0) {
            this.inputFocused = WhiteMenu.isHovered(mx, my, this.fieldX, this.fieldY, this.fieldW, this.fieldH);

            if (WhiteMenu.isHovered(mx, my, this.addX, this.addY, this.addW, this.addH)) {
                this.clickSound();
                WhiteAccount added = WhiteAltManager.get().add(this.inputText.trim());
                if (added != null) {
                    this.inputText = "";
                }
                return true;
            }
            if (WhiteMenu.isHovered(mx, my, this.randX, this.randY, this.randW, this.randH)) {
                this.clickSound();
                this.inputText = WhiteAltManager.get().randomName(this.inputText);
                return true;
            }
            if (this.backH > 0.0f && WhiteMenu.isHovered(mx, my, this.backX, this.backY, this.backW, this.backH)) {
                this.clickSound();
                if (mc != null) {
                    mc.setScreen(this.parent);
                }
                return true;
            }

            List<WhiteAccount> accounts = new ArrayList<>(WhiteAltManager.get().getSorted());
            float k = 1.0f;
            if (mc != null && mc.getWindow() != null) {
                k = Math.max(0.85f, ((float) mc.getWindow().getScaledHeight() / this.scaleFix) / 540.0f);
            }
            float rowH = 36.0f * k;
            float rowGap = 6.0f * k;
            float y = this.listY - this.scroll;
            for (WhiteAccount acc : accounts) {
                if (y + rowH >= this.listY && y <= this.listY + this.listH) {
                    // зоны кнопок совпадают с drawRow
                    float btnH = 22.0f * k;
                    float delW = 62.0f * k;
                    float starW = 24.0f * k;
                    float skinW = 52.0f * k;
                    float delX = this.listX + this.listW - delW - 5.0f * k;
                    float starX = delX - starW - 6.0f * k;
                    float skinX = starX - skinW - 6.0f * k;
                    float btnY = y + (rowH - btnH) / 2.0f;
                    if (WhiteMenu.isHovered(mx, my, skinX, btnY, skinW, btnH)) {
                        this.clickSound();
                        if (mc != null) {
                            mc.setScreen(new WhiteSkinScreen(this, acc.name));
                        }
                        return true;
                    }
                    if (WhiteMenu.isHovered(mx, my, delX, btnY, delW, btnH)) {
                        this.clickSound();
                        WhiteAltManager.get().remove(acc);
                        return true;
                    }
                    if (WhiteMenu.isHovered(mx, my, starX, btnY, starW, btnH)) {
                        this.clickSound();
                        WhiteAltManager.get().toggleFavorite(acc);
                        return true;
                    }
                    // клик по любому месту карточки, кроме кнопок, — вход в аккаунт
                    if (WhiteMenu.isHovered(mx, my, this.listX, y, this.listW, rowH)) {
                        this.clickSound();
                        WhiteAltManager.get().login(acc);
                        if (mc != null) {
                            mc.setScreen(this.parent);
                        }
                        return true;
                    }
                }
                y += rowH + rowGap;
            }
        }
        return super.mouseClicked(click, doubled);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
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

    private float maxScroll() {
        MinecraftClient mc = MinecraftClient.getInstance();
        float k = 1.0f;
        if (mc != null && mc.getWindow() != null) {
            k = Math.max(0.85f, ((float) mc.getWindow().getScaledHeight() / this.scaleFix) / 540.0f);
        }
        int count = WhiteAltManager.get().getSorted().size();
        if (count <= 0) {
            return 0.0f;
        }
        // резерв под низ, чтобы последние аккаунты целиком влезали при прокрутке
        float total = count * (36.0f * k + 6.0f * k) + 8.0f * k;
        return Math.max(0.0f, total - this.listH);
    }

    @Override
    public boolean charTyped(net.minecraft.client.input.CharInput input) {
        if (this.inputFocused && this.inputText.length() < 15) {
            int cp = input.codepoint();
            if (cp >= 0 && cp <= 0xFFFF) {
                char c = (char) cp;
                if (c == '_' || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
                    this.inputText += c;
                    return true;
                }
            }
        }
        return super.charTyped(input);
    }

    @Override
    public boolean keyPressed(net.minecraft.client.input.KeyInput input) {
        int keyCode = input.key();
        if (this.inputFocused) {
            if (keyCode == GLFW.GLFW_KEY_BACKSPACE && !this.inputText.isEmpty()) {
                this.inputText = this.inputText.substring(0, this.inputText.length() - 1);
                return true;
            }
            if (keyCode == GLFW.GLFW_KEY_ENTER) {
                WhiteAccount added = WhiteAltManager.get().add(this.inputText.trim());
                if (added != null) {
                    this.inputText = "";
                    this.clickSound();
                }
                return true;
            }
            if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
                this.inputFocused = false;
                return true;
            }
        }
        return super.keyPressed(input);
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
