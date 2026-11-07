package rtx.kimiko.api.ui.menu;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import rtx.kimiko.api.ui.mainmenu.MenuSettings;
import rtx.kimiko.api.ui.theme.ClientAccent;
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
 * Кастомное меню паузы (Esc в игре) в стиле белого меню Kimiko.
 * Подменяет ванильный GameMenuScreen (mixin GameMenuScreenMixin).
 */
public class WhitePauseScreen extends Screen {
    private final SmoothAnimation alpha = new SmoothAnimation();
    private float scaleFix = 1.0f;
    private float lastMouseX;
    private float lastMouseY;

    private static final class PauseBtn {
        final String text;
        final int style;
        final Runnable action;
        final SmoothAnimation hover = new SmoothAnimation();
        float x;
        float y;
        float w;
        float h;
        boolean hovered;

        PauseBtn(String text, int style, Runnable action) {
            this.text = text;
            this.style = style;
            this.action = action;
        }
    }

    private static final int STYLE_PRIMARY = 0;
    private static final int STYLE_GLASS = 1;
    private static final int STYLE_DANGER = 2;

    private final List<PauseBtn> buttons = new ArrayList<>();
    private float panelX;
    private float panelY;
    private float panelW;
    private float panelH;

    public WhitePauseScreen() {
        super(Text.literal("KimikoPause"));
    }

    @Override
    protected void init() {
        this.alpha.setValue(0.0);
        this.alpha.run(1.0, 0.35, Easings.QUAD_OUT, false);
        this.buttons.clear();
        MinecraftClient mc = MinecraftClient.getInstance();
        this.buttons.add(new PauseBtn("Вернуться в игру", STYLE_PRIMARY, () -> mc.setScreen(null)));
        this.buttons.add(new PauseBtn("Настройки", STYLE_GLASS, () -> mc.setScreen(new WhiteOptionsScreen(this))));
        if (mc.isInSingleplayer()) {
            this.buttons.add(new PauseBtn("Сохранить и выйти", STYLE_DANGER, mc::disconnectWithSavingScreen));
        } else {
            this.buttons.add(new PauseBtn("Отключиться от сервера", STYLE_DANGER, () -> mc.disconnect(Text.literal("Отключено"))));
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
        float currentScale = (float) mc.getWindow().getScaleFactor();
        this.scaleFix = 2.0f / currentScale;
        this.lastMouseX = (float) (mouseX / this.scaleFix);
        this.lastMouseY = (float) (mouseY / this.scaleFix);
        this.alpha.update();
        float a = (float) this.alpha.getValue();

        int sw = (int) (mc.getWindow().getScaledWidth() / this.scaleFix);
        int sh = (int) (mc.getWindow().getScaledHeight() / this.scaleFix);
        float k = Math.max(0.85f, sh / 540.0f);
        k = Math.max(0.62f, Math.min(2.2f, k));

        Render2D.beginFrame(context);

        // мягкое затемнение мира (не чёрное — чтобы игра читалась за стеклом)
        Render2D.rect(0.0f, 0.0f, sw, sh, 0.0f,
                MenuColors.rgba(6, 8, 12, (int) (110 * a)),
                MenuColors.rgba(6, 8, 12, (int) (80 * a)),
                MenuColors.rgba(6, 8, 12, (int) (55 * a)),
                MenuColors.rgba(6, 8, 12, (int) (80 * a)));

        float rise = (1.0f - a) * 14.0f * k;
        this.panelW = 300.0f * k;
        int count = this.buttons.size();
        float btnH = 34.0f * k;
        float gap = 7.0f * k;
        float pad = 16.0f * k;
        float headH = 54.0f * k;
        this.panelH = headH + (btnH + gap) * count + pad - gap + pad;
        this.panelX = (sw - this.panelW) / 2.0f;
        this.panelY = (sh - this.panelH) / 2.0f + rise;

        // панель
        if (MenuSettings.liquidGlass()) {
            RectUtil.drawGlassCard(this.panelX, this.panelY, this.panelW, this.panelH,
                    14.0f * k, 14.0f * k, 14.0f * k, 14.0f * k, a, 0.0f);
            Render2D.outline(this.panelX, this.panelY, this.panelW, this.panelH, 14.0f * k, 1.0f,
                    MenuColors.rgba(255, 255, 255, (int) (30 * a)));
        } else {
            Render2D.blur(this.panelX, this.panelY, this.panelW, this.panelH, 14.0f * k, 26.0f);
            Render2D.rect(this.panelX, this.panelY, this.panelW, this.panelH, 14.0f * k,
                    MenuColors.rgba(11, 13, 19, (int) (215 * a)));
            Render2D.outline(this.panelX, this.panelY, this.panelW, this.panelH, 14.0f * k, 1.0f,
                    MenuColors.rgba(255, 255, 255, (int) (26 * a)));
        }

        // заголовок
        float titleX = this.panelX + pad;
        float titleY = this.panelY + pad + 2.0f * k;
        Fonts.BOLD.draw("ПАУЗА", titleX, titleY, 9.0f * k,
                MenuColors.withAlpha(ThemeManager.accent(255), (int) (255 * a)));
        Fonts.EXTRABOLD.draw("Игра остановлена", titleX, titleY + 13.0f * k, 15.0f * k,
                MenuColors.rgba(255, 255, 255, (int) (245 * a)));

        // кнопки
        float bx = this.panelX + pad;
        float by = this.panelY + headH;
        for (PauseBtn btn : this.buttons) {
            btn.x = bx;
            btn.y = by;
            btn.w = this.panelW - pad * 2.0f;
            btn.h = btnH;
            btn.hovered = WhiteMenu.isHovered(this.lastMouseX, this.lastMouseY, btn.x, btn.y, btn.w, btn.h);
            btn.hover.update();
            btn.hover.run(btn.hovered ? 1.0 : 0.0, 0.16, Easings.QUAD_OUT, false);
            this.drawButton(btn, a, k);
            by += btnH + gap;
        }

        Render2D.flush();
    }

    private void drawButton(PauseBtn btn, float a, float k) {
        float hp = (float) btn.hover.getValue();
        float rad = 9.0f * k;
        float radG = MenuSettings.buttonRadius() * k;
        boolean glass = MenuSettings.liquidGlass();
        int accent = ThemeManager.accent(255);

        if (btn.style == STYLE_PRIMARY) {
            if (glass) {
                RectUtil.drawGlassCard(btn.x, btn.y, btn.w, btn.h, radG, radG, radG, radG, a, hp);
                float cx = btn.x + btn.w * 0.5f;
                float cy = btn.y + btn.h * 0.5f;
                float accTop = 20.0f + 80.0f * MenuSettings.primaryAccent();
                Render2D.rect(btn.x, btn.y, btn.w, btn.h, radG,
                        ClientAccent.gradientAAt((accTop + 26.0f * hp) * a, cx, cy),
                        ClientAccent.gradientAAt((accTop + 26.0f * hp) * a, cx, cy),
                        ClientAccent.gradientBAt((accTop * 0.7333f + 26.0f * hp) * a, cx, cy),
                        ClientAccent.gradientBAt((accTop * 0.7333f + 26.0f * hp) * a, cx, cy));
                Render2D.outlineClient(btn.x, btn.y, btn.w, btn.h, radG, radG, radG, radG, 0.6f,
                        MenuColors.rgba(255, 255, 255, (int) ((52 + 26 * hp) * a)),
                        MenuColors.rgba(255, 255, 255, (int) ((52 + 26 * hp) * a)),
                        MenuColors.rgba(255, 255, 255, (int) ((22 + 16 * hp) * a)),
                        MenuColors.rgba(255, 255, 255, (int) ((22 + 16 * hp) * a)),
                        hp);
            } else {
                Render2D.rect(btn.x, btn.y, btn.w, btn.h, rad, MenuColors.withAlpha(accent, (int) ((225 + 25 * hp) * a)));
                Render2D.rect(btn.x + 1.0f * k, btn.y + 1.0f * k, btn.w - 2.0f * k, btn.h * 0.42f,
                        rad - 1.0f * k, rad - 1.0f * k, 0.0f, 0.0f,
                        MenuColors.rgba(255, 255, 255, (int) (22 * a)));
            }
            float tw = Fonts.SEMIBOLD.width(btn.text, 11.5f * k);
            Fonts.SEMIBOLD.draw(btn.text, btn.x + (btn.w - tw) * 0.5f,
                    btn.y + (btn.h - 11.5f * k) / 2.0f - 0.5f * k, 11.5f * k,
                    MenuColors.rgba(255, 255, 255, (int) (255 * a)));
        } else {
            int rBase = btn.style == STYLE_DANGER ? 255 : 255;
            int gBase = btn.style == STYLE_DANGER ? 82 : 255;
            int bBase = btn.style == STYLE_DANGER ? 82 : 255;
            int bg = btn.style == STYLE_DANGER
                    ? MenuColors.rgba(rBase, gBase, bBase, (int) ((18 + hp * 26) * a))
                    : MenuColors.rgba(255, 255, 255, (int) ((8 + hp * 6) * a));
            int lineCol = btn.style == STYLE_DANGER
                    ? MenuColors.rgba(rBase, gBase, bBase, (int) ((90 + hp * 70) * a))
                    : MenuColors.rgba(255, 255, 255, (int) ((22 + hp * 26) * a));

            if (glass) {
                RectUtil.drawGlassCard(btn.x, btn.y, btn.w, btn.h, radG, radG, radG, radG, a * 0.8f, hp);
            }
            Render2D.rect(btn.x, btn.y, btn.w, btn.h, radG, bg);
            Render2D.outline(btn.x, btn.y, btn.w, btn.h, radG, 1.0f, lineCol);

            int textCol = btn.style == STYLE_DANGER
                    ? MenuColors.rgba(255, 120, 120, (int) ((200 + hp * 55) * a))
                    : MenuColors.rgba(255, 255, 255, (int) ((200 + hp * 55) * a));
            float tw = Fonts.SEMIBOLD.width(btn.text, 11.0f * k);
            Fonts.SEMIBOLD.draw(btn.text, btn.x + (btn.w - tw) * 0.5f,
                    btn.y + (btn.h - 11.0f * k) / 2.0f - 0.5f * k, 11.0f * k, textCol);
        }
    }

    @Override
    public boolean mouseClicked(Click click, boolean doubled) {
        float mx = (float) (click.x() / this.scaleFix);
        float my = (float) (click.y() / this.scaleFix);
        if (click.button() == 0) {
            for (PauseBtn btn : this.buttons) {
                if (WhiteMenu.isHovered(mx, my, btn.x, btn.y, btn.w, btn.h)) {
                    SoundManager.playSoundDirect(SoundManager.BUTTON_CLICK, 0.6f, 1.0f);
                    btn.action.run();
                    return true;
                }
            }
        }
        return super.mouseClicked(click, doubled);
    }
}
