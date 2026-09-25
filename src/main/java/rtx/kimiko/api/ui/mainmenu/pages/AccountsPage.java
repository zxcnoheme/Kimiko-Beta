/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui.mainmenu.pages;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mods.acountswiher.ru.vidtu.ias.screen.AccountScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.ui.mainmenu.MainMenuScreen;
import rtx.kimiko.api.ui.mainmenu.MenuControls;
import rtx.kimiko.api.ui.mainmenu.MenuPage;
import rtx.kimiko.api.ui.mainmenu.MenuTheme;
import rtx.kimiko.utils.profile.ProfileIdentity;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 '2\u00020\u0001:\u0001'B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0003J?\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ7\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015JW\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ'\u0010 \u001a\u00020\u001a2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016\u00a2\u0006\u0004\b \u0010!R\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010%\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u0010&\u00a8\u0006("}, d2={"Lrtx/kimiko/api/ui/mainmenu/pages/AccountsPage;", "Lrtx/kimiko/api/ui/mainmenu/MenuPage;", "<init>", "()V", "", "onShow", "Lnet/minecraft/DrawContext;", "graphics", "", "mouseX", "mouseY", "dt", "alpha", "appear", "render", "(Lnet/minecraft/DrawContext;FFFFF)V", "cx", "cy", "cw", "ch", "renderCurrent", "(FFFFF)V", "ax", "ay", "aw", "ah", "", "interactive", "renderActions", "(FFFFFFFFZ)V", "", "button", "mouseClicked", "(FFI)Z", "Lrtx/kimiko/api/ui/mainmenu/MenuControls$Button;", "manage", "Lrtx/kimiko/api/ui/mainmenu/MenuControls$Button;", "pulse", "F", "Companion", "rtx.kimiko:kimiko"})
public final class AccountsPage
extends MenuPage {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final MenuControls.Button manage = new MenuControls.Button(I18n.tr("Менеджер аккаунтов"), true).icon(Fonts.MAINMENU, "e");
    private float pulse;

    @Override
    public void onShow() {
        this.manage.label(I18n.tr("Менеджер аккаунтов"));
    }

    @Override
    public void render(@NotNull DrawContext graphics, float mouseX, float mouseY, float dt, float alpha, float appear) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        if (alpha <= 0.004f) {
            return;
        }
        this.pulse += dt;
        boolean interactive = appear > 0.85f;
        float s0 = MenuTheme.stagger(appear, 0, 3, 0.24f);
        float s1 = MenuTheme.stagger(appear, 1, 3, 0.24f);
        float cardH = 132.0f;
        float cardY = this.y + (1.0f - MenuTheme.ease(s0)) * 18.0f;
        this.renderCurrent(this.x, cardY, this.w, cardH, alpha * s0);
        float hintY = cardY + cardH + MenuTheme.GAP + (1.0f - MenuTheme.ease(s1)) * 14.0f;
        float hintH = Math.max(60.0f, this.y + this.h - hintY);
        this.renderActions(this.x, hintY, this.w, hintH, mouseX, mouseY, dt, alpha * s1, interactive);
    }

    private final void renderCurrent(float cx, float cy, float cw, float ch, float alpha) {
        float uuidSize;
        String string;
        if (alpha <= 0.004f) {
            return;
        }
        MenuTheme.panel(cx, cy, cw, ch, MenuTheme.PANEL_RADIUS, alpha * 0.92f);
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        String string2 = minecraft.getSession() != null ? minecraft.getSession().getUsername() : "Player";
        Intrinsics.checkNotNull((Object)string2);
        String name = string2;
        String uuid = "—";
        try {
            if (minecraft.getSession() != null) {
                String string3 = minecraft.getSession().getUuidOrNull().toString();
                Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toString(...)");
                uuid = string3;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        if ((string = ProfileIdentity.username(name)) == null) {
            string = name;
        }
        String profile = string;
        float head = 54.0f;
        float hx = cx + 22.0f;
        float hy = cy + (ch - head) * 0.5f;
        float glow = 0.5f + 0.5f * (float)Math.sin(this.pulse * 1.3f);
        Render2D.rect(hx - 4.0f, hy - 4.0f, head + 8.0f, head + 8.0f, 12.0f, MenuTheme.accent(20.0f + 12.0f * glow, alpha));
        String skin = AccountsPage.Companion.skinTexture(minecraft);
        if (skin != null && Render2D.imageReady(skin)) {
            Render2D.imageUvNearest(skin, hx, hy, head, head, 8.0f, 0.0f, 0.125f, 0.125f, 0.25f, 0.25f, MenuTheme.white(255.0f, alpha));
            Render2D.imageUvNearest(skin, hx - 1.5f, hy - 1.5f, head + 3.0f, head + 3.0f, 9.0f, 0.0f, 0.625f, 0.125f, 0.75f, 0.25f, MenuTheme.white(210.0f, alpha));
        } else {
            Render2D.rect(hx, hy, head, head, 8.0f, MenuTheme.white(16.0f, alpha));
        }
        float textX = hx + head + 18.0f;
        Fonts.BOLD.draw(name, textX, cy + 34.0f, 14.0f, MenuTheme.white(255.0f, alpha));
        String profileLabel = I18n.tr("Профиль Kimiko: ") + profile;
        Fonts.MEDIUM.draw(profileLabel, textX, cy + 56.0f, 4.8f, MenuTheme.inkMuted(alpha));
        String uuidLabel = "UUID " + uuid;
        float maxW = cw - (textX - cx) - 24.0f;
        for (uuidSize = 4.3f; Fonts.MEDIUM.width(uuidLabel, uuidSize) > maxW && uuidSize > 3.0f; uuidSize -= 0.15f) {
        }
        Fonts.MEDIUM.draw(uuidLabel, textX, cy + 70.0f, uuidSize, MenuTheme.inkFaint(alpha));
        String chip = I18n.tr("АКТИВЕН");
        float chipW = Fonts.SEMIBOLD.width(chip, 4.4f) + 16.0f;
        Render2D.rect(cx + cw - chipW - 22.0f, cy + 22.0f, chipW, 13.0f, 6.5f, MenuTheme.accent(40.0f, alpha));
        Fonts.SEMIBOLD.draw(chip, cx + cw - chipW - 22.0f + 8.0f, cy + 26.0f, 4.4f, MenuTheme.accentBright(235.0f, alpha));
    }

    private final void renderActions(float ax, float ay, float aw, float ah, float mouseX, float mouseY, float dt, float alpha, boolean interactive) {
        if (alpha <= 0.004f) {
            return;
        }
        MenuTheme.panel(ax, ay, aw, ah, MenuTheme.PANEL_RADIUS, alpha * 0.88f);
        String title = I18n.tr("СМЕНА АККАУНТА");
        Fonts.BOLD.draw(title, ax + 16.0f, ay + 15.0f, 5.6f, MenuTheme.ink(alpha * 0.9f));
        String text = I18n.tr("Храни несколько аккаунтов и переключайся без перезапуска игры.");
        Fonts.MEDIUM.draw(text, ax + 16.0f, ay + 32.0f, 4.8f, MenuTheme.inkMuted(alpha));
        float buttonW = 150.0f;
        float buttonH = 23.0f;
        this.manage.bounds(ax + aw - buttonW - 16.0f, ay + ah * 0.5f - buttonH * 0.5f, buttonW, buttonH);
        this.manage.render(mouseX, mouseY, dt, alpha, interactive);
    }

    @Override
    public boolean mouseClicked(float mouseX, float mouseY, int button) {
        if (button == 0 && this.manage.contains(mouseX, mouseY)) {
            this.manage.tap();
            Sounds.play("gui_click");
            MinecraftClient.getInstance().setScreen((Screen)new AccountScreen(MainMenuScreen.Companion.instance()));
            return true;
        }
        return false;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2={"Lrtx/kimiko/api/ui/mainmenu/pages/AccountsPage.Companion;", "", "<init>", "()V", "Lnet/minecraft/MinecraftClient;", "minecraft", "", "skinTexture", "(Lnet/minecraft/MinecraftClient;)Ljava/lang/String;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final String skinTexture(MinecraftClient minecraft) {
            try {
                if (minecraft != null && minecraft.player != null) {
                    ClientPlayerEntity clientPlayerEntity2 = minecraft.player;
                    Intrinsics.checkNotNull((Object)clientPlayerEntity2);
                    return clientPlayerEntity2.getSkin().body().texturePath().toString();
                }
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            return "minecraft:textures/entity/player/wide/steve.png";
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

