/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.util.Util
 *  net.minecraft.util.Util.OperatingSystem
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui.mainmenu.pages;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.util.Util;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import recovery.privacy.NetworkPolicy;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.modules.impl.Visuals.cosmetics.CosmeticSiteState;
import rtx.kimiko.api.ui.mainmenu.MenuControls;
import rtx.kimiko.api.ui.mainmenu.MenuPage;
import rtx.kimiko.api.ui.mainmenu.MenuTheme;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0006\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0003J?\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ'\u0010\u0013\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001c\u00a8\u0006\u001e"}, d2={"Lrtx/kimiko/api/ui/mainmenu/pages/CosmeticsPage;", "Lrtx/kimiko/api/ui/mainmenu/MenuPage;", "<init>", "()V", "", "onShow", "Lnet/minecraft/DrawContext;", "graphics", "", "mouseX", "mouseY", "dt", "alpha", "appear", "render", "(Lnet/minecraft/DrawContext;FFFFF)V", "", "button", "", "mouseClicked", "(FFI)Z", "Lrtx/kimiko/api/ui/mainmenu/MenuControls$Button;", "site", "Lrtx/kimiko/api/ui/mainmenu/MenuControls$Button;", "", "slotHover", "[F", "breathe", "F", "Companion", "rtx.kimiko:kimiko"})
public final class CosmeticsPage
extends MenuPage {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final MenuControls.Button site = new MenuControls.Button(I18n.tr("Открыть гардероб"), true).icon(Fonts.I2, "Z");
    @NotNull
    private final float[] slotHover = new float[4];
    private float breathe;
    @NotNull
    private static final String SITE = "https://kimiko.tech";

    @Override
    public void onShow() {
        this.site.label(I18n.tr("Открыть гардероб"));
    }

    @Override
    public void render(@NotNull DrawContext graphics, float mouseX, float mouseY, float dt, float alpha, float appear) {
        float textSize;
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        if (alpha <= 0.004f) {
            return;
        }
        this.breathe += dt;
        boolean interactive = appear > 0.85f;
        CosmeticSiteState state = CosmeticSiteState.Companion.get();
        String head = CosmeticsPage.Companion.pretty(state.headAccessory());
        String body = state.wingsOn() ? CosmeticsPage.Companion.pretty(state.bodyModel()) : CosmeticsPage.Companion.empty();
        String shoulder = state.goatOn() ? I18n.tr("Коза") : CosmeticsPage.Companion.empty();
        String pet = CosmeticsPage.Companion.pretty(state.petKind());
        String[] stringArray = new String[]{I18n.tr("Голова"), I18n.tr("Спина"), I18n.tr("Плечо"), I18n.tr("Питомец")};
        String[] titles = stringArray;
        String[] stringArray2 = new String[]{head, body, shoulder, pet};
        String[] values = stringArray2;
        String[] stringArray3 = new String[]{"е", "x", "l", "r"};
        String[] glyphs = stringArray3;
        Fonts[] fontsArray = new Fonts[]{Fonts.I2, Fonts.KIMIKO, Fonts.I2, Fonts.I2};
        Fonts[] glyphFonts = fontsArray;
        float s1 = MenuTheme.stagger(appear, 1, 3, 0.24f);
        float slotsH = this.h * 0.56f;
        float gap = MenuTheme.GAP;
        float slotW = (this.w - gap * 3.0f) / 4.0f;
        for (int i = 0; i < 4; ++i) {
            float wobble;
            float sx = this.x + (float)i * (slotW + gap);
            float stagger = MenuTheme.stagger(appear, i, 6, 0.18f);
            float slotAlpha = alpha * stagger;
            if (slotAlpha <= 0.004f) continue;
            float sy = this.y + (1.0f - MenuTheme.backOut(stagger)) * 22.0f;
            boolean hovered = interactive && this.inside(mouseX, mouseY, sx, sy, slotW, slotsH);
            this.slotHover[i] = MenuTheme.approach(this.slotHover[i], hovered ? 1.0f : 0.0f, dt, 12.0f);
            float hover = this.slotHover[i];
            boolean filled = !Intrinsics.areEqual((Object)values[i], (Object)CosmeticsPage.Companion.empty());
            MenuTheme.card(sx, sy, slotW, slotsH, MenuTheme.CARD_RADIUS, slotAlpha * 0.8f, hover * 0.7f);
            float centerX = sx + slotW * 0.5f;
            float ringR = Math.min(slotW, slotsH) * 0.24f;
            float ringY = sy + slotsH * 0.38f;
            float f = wobble = filled ? (float)Math.sin(this.breathe * 1.6f + (float)i) * 1.6f : 0.0f;
            if (filled) {
                Render2D.circle(centerX, ringY + wobble, ringR + 5.0f, ringR + 5.0f, MenuTheme.accent(24.0f + 16.0f * hover, slotAlpha));
            }
            Render2D.circle(centerX, ringY + wobble, ringR, ringR, MenuTheme.black(90.0f, slotAlpha));
            Render2D.circleOutline(centerX, ringY + wobble, ringR, 0.8f, filled ? MenuTheme.accent(170.0f, slotAlpha) : MenuTheme.white(30.0f, slotAlpha));
            float glyphSize = ringR * 0.95f;
            float glyphW = glyphFonts[i].msdfWidth(glyphs[i], glyphSize);
            glyphFonts[i].msdf(glyphs[i], centerX - glyphW * 0.5f, ringY + wobble - glyphSize * 0.5f, glyphSize, filled ? MenuTheme.accentBright(230.0f, slotAlpha) : MenuTheme.white(55.0f, slotAlpha));
            float titleW = Fonts.MEDIUM.width(titles[i], 4.6f);
            Fonts.MEDIUM.draw(titles[i], centerX - titleW * 0.5f, sy + slotsH - 34.0f, 4.6f, MenuTheme.inkMuted(slotAlpha));
            float valueSize = 6.2f;
            String value = values[i];
            float valueW = Fonts.SEMIBOLD.width(value, valueSize);
            while (valueW > slotW - 14.0f && valueSize > 4.0f) {
                valueW = Fonts.SEMIBOLD.width(value, valueSize -= 0.3f);
            }
            Fonts.SEMIBOLD.draw(value, centerX - valueW * 0.5f, sy + slotsH - 22.0f, valueSize, filled ? MenuTheme.ink(slotAlpha) : MenuTheme.inkFaint(slotAlpha));
        }
        float panelY = this.y + slotsH + gap + (1.0f - MenuTheme.ease(s1)) * 16.0f;
        float panelH = Math.max(50.0f, this.y + this.h - panelY);
        float panelAlpha = alpha * s1;
        if (panelAlpha <= 0.004f) {
            return;
        }
        MenuTheme.panel(this.x, panelY, this.w, panelH, MenuTheme.PANEL_RADIUS, panelAlpha * 0.9f);
        String title = I18n.tr("ГАРДЕРОБ");
        Fonts.BOLD.draw(title, this.x + 16.0f, panelY + 15.0f, 5.6f, MenuTheme.ink(panelAlpha * 0.9f));
        float buttonW = Math.min(150.0f, this.w * 0.3f);
        float textMax = this.w - 32.0f - buttonW - 18.0f;
        String text = I18n.tr("Косметика лежит в инвентаре аккаунта. Меняй её на сайте.");
        for (textSize = 4.8f; Fonts.MEDIUM.width(text, textSize) > textMax && textSize > 3.6f; textSize -= 0.15f) {
        }
        Fonts.MEDIUM.draw(text, this.x + 16.0f, panelY + 32.0f, textSize, MenuTheme.inkMuted(panelAlpha));
        float siteText = 4.6f;
        Fonts.SEMIBOLD.draw(String.valueOf(SITE).replace("https://", ""), this.x + 16.0f, panelY + 46.0f, siteText, MenuTheme.accent(210.0f, panelAlpha));
        float buttonH = 22.0f;
        this.site.bounds(this.x + this.w - buttonW - 16.0f, panelY + panelH * 0.5f - buttonH * 0.5f, buttonW, buttonH);
        this.site.render(mouseX, mouseY, dt, panelAlpha, interactive);
    }

    @Override
    public boolean mouseClicked(float mouseX, float mouseY, int button) {
        if (button == 0 && this.site.contains(mouseX, mouseY)) {
            this.site.tap();
            Sounds.play("gui_click");
            try {
                CosmeticsPage.privacy$openAllowedLink(Util.getOperatingSystem(), SITE);
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            return true;
        }
        return false;
    }

    private static /* synthetic */ void privacy$openAllowedLink(Util.OperatingSystem operatingSystem2, String string) {
        if (NetworkPolicy.allowedLink(string)) {
            operatingSystem2.open(string);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0006\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000b\u00a8\u0006\f"}, d2={"Lrtx/kimiko/api/ui/mainmenu/pages/CosmeticsPage.Companion;", "", "<init>", "()V", "", "raw", "pretty", "(Ljava/lang/String;)Ljava/lang/String;", "empty", "()Ljava/lang/String;", "SITE", "Ljava/lang/String;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final String pretty(String raw) {
            if (raw == null || StringsKt.isBlank((CharSequence)raw) || StringsKt.equals((String)raw, (String)"none", (boolean)true) || StringsKt.equals((String)raw, (String)"off", (boolean)true)) {
                return this.empty();
            }
            String cleaned = ((Object)StringsKt.trim((CharSequence)String.valueOf(raw).replace((char)'_', (char)' '))).toString();
            char c = Character.toUpperCase(cleaned.charAt(0));
            String string = cleaned.substring(1);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            String string2 = string;
            return c + string2;
        }

        private final String empty() {
            return I18n.tr("пусто");
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

