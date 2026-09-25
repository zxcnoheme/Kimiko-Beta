/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui.mainmenu.pages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.ui.mainmenu.MenuPage;
import rtx.kimiko.api.ui.mainmenu.MenuStats;
import rtx.kimiko.api.ui.mainmenu.MenuTheme;
import rtx.kimiko.api.ui.module.DiscordAvatar;
import rtx.kimiko.api.ui.window.MainWindow;
import rtx.kimiko.utils.net.ClientPresence;
import rtx.kimiko.utils.profile.ProfileIdentity;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u001b\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0003J?\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ7\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J7\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u001a\u0010\u0015J7\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u001f\u0010\u0015R\u0016\u0010 \u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b \u0010!\u00a8\u0006#"}, d2={"Lrtx/kimiko/api/ui/mainmenu/pages/ProfilePage;", "Lrtx/kimiko/api/ui/mainmenu/MenuPage;", "<init>", "()V", "", "onShow", "Lnet/minecraft/DrawContext;", "graphics", "", "mouseX", "mouseY", "dt", "alpha", "appear", "render", "(Lnet/minecraft/DrawContext;FFFFF)V", "hx", "hy", "hw", "hh", "renderHero", "(FFFFF)V", "sx", "sy", "sw", "sh", "renderStats", "ix", "iy", "iw", "ih", "renderInfo", "glow", "F", "Companion", "rtx.kimiko:kimiko"})
public final class ProfilePage
extends MenuPage {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private float glow;

    @Override
    public void onShow() {
        MenuStats.touch();
    }

    @Override
    public void render(@NotNull DrawContext graphics, float mouseX, float mouseY, float dt, float alpha, float appear) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        if (alpha <= 0.004f) {
            return;
        }
        MenuStats.touch();
        this.glow = MenuTheme.approach(this.glow, 1.0f, dt, 3.0f);
        float heroH = Math.max(74.0f, Math.min(122.0f, this.h * 0.3f));
        float tileH = Math.max(46.0f, Math.min(66.0f, this.h * 0.155f));
        float infoH = this.h - heroH - tileH - MenuTheme.GAP * 2.0f;
        if (infoH < 52.0f) {
            float deficit = 52.0f - infoH;
            heroH = Math.max(70.0f, heroH - deficit * 0.6f);
            tileH = Math.max(42.0f, tileH - deficit * 0.4f);
            infoH = this.h - heroH - tileH - MenuTheme.GAP * 2.0f;
        }
        float s0 = MenuTheme.stagger(appear, 0, 4, 0.22f);
        float s1 = MenuTheme.stagger(appear, 1, 4, 0.22f);
        float s2 = MenuTheme.stagger(appear, 2, 4, 0.22f);
        this.renderHero(this.x, this.y + ProfilePage.Companion.rise(s0), this.w, heroH, alpha * s0);
        float tileY = this.y + heroH + MenuTheme.GAP + ProfilePage.Companion.rise(s1);
        this.renderStats(this.x, tileY, this.w, tileH, alpha * s1);
        float infoY = this.y + heroH + tileH + MenuTheme.GAP * 2.0f;
        if (infoH >= 44.0f) {
            this.renderInfo(this.x, infoY + ProfilePage.Companion.rise(s2), this.w, infoH, alpha * s2);
        }
    }

    private final void renderHero(float hx, float hy, float hw, float hh, float alpha) {
        String string;
        if (alpha <= 0.004f) {
            return;
        }
        MenuTheme.panel(hx, hy, hw, hh, MenuTheme.PANEL_RADIUS, alpha * 0.92f);
        float pulse = 0.5f + 0.5f * (float)Math.sin((double)System.currentTimeMillis() / 1400.0);
        float avatar = Math.max(40.0f, Math.min(62.0f, hh * 0.55f));
        float ax = hx + 22.0f;
        float ay = hy + (hh - avatar) * 0.5f;
        Render2D.circle(ax + avatar * 0.5f, ay + avatar * 0.5f, avatar * 0.5f + 6.0f + pulse * 2.0f, avatar * 0.5f + 6.0f, MenuTheme.accent(28.0f + 14.0f * pulse, alpha * this.glow));
        String texture = DiscordAvatar.texture();
        if (texture != null && Render2D.imageReady(texture)) {
            Render2D.image(texture, ax, ay, avatar, avatar, avatar * 0.5f, MenuTheme.white(255.0f, alpha));
        } else {
            Render2D.rect(ax, ay, avatar, avatar, avatar * 0.5f, MenuTheme.white(18.0f, alpha));
            float glyphSize = avatar * 0.48f;
            float glyphW = Fonts.MAINMENU.msdfWidth("e", glyphSize);
            Fonts.MAINMENU.msdf("e", ax + (avatar - glyphW) * 0.5f, ay + avatar * 0.25f, glyphSize, MenuTheme.white(110.0f, alpha));
        }
        Render2D.outlineClient$default(ax, ay, avatar, avatar, avatar * 0.5f, 1.1f, MenuTheme.accent(190.0f, alpha), 0.0f, 128, null);
        float nickSize = Math.max(10.0f, Math.min(15.0f, hh * 0.135f));
        float textX = ax + avatar + 18.0f;
        String string2 = ProfileIdentity.username(ProfilePage.Companion.fallbackNick());
        if (string2 == null) {
            string2 = ProfilePage.Companion.fallbackNick();
        }
        String nick = string2;
        Fonts.BOLD.draw(nick, textX, hy + hh * 0.25f, nickSize, MenuTheme.white(255.0f, alpha));
        int uid = ProfileIdentity.uid();
        String uidText = "ID " + (uid > 0 ? String.valueOf(uid) : "—");
        Fonts.MEDIUM.draw(uidText, textX, hy + hh * 0.25f + nickSize + 5.0f, 5.0f, MenuTheme.inkMuted(alpha));
        int online = ClientPresence.INSTANCE.count();
        float chipY = hy + hh * 0.25f + nickSize + 17.0f;
        if (online > 0) {
            Object[] objectArray = new Object[]{online};
            string = I18n.tr("%d игроков в сети", objectArray);
        } else {
            string = I18n.tr("нет соединения");
        }
        String chipText = string;
        float chipTextW = Fonts.SEMIBOLD.width(chipText, 4.6f);
        float chipW = chipTextW + 20.0f;
        Render2D.rect(textX, chipY, chipW, 14.0f, 7.0f, MenuTheme.white(14.0f, alpha));
        int dotColor = online > 0 ? MenuTheme.fade(-10755958, alpha) : MenuTheme.white(80.0f, alpha);
        Render2D.circle(textX + 8.0f, chipY + 7.0f, 2.0f, 2.0f, dotColor);
        Fonts.SEMIBOLD.draw(chipText, textX + 14.0f, chipY + 4.5f, 4.6f, MenuTheme.inkSoft(alpha));
        String badge = "Kimiko v1.5";
        float badgeW = Fonts.SEMIBOLD.width(badge, 5.0f);
        Fonts.SEMIBOLD.draw(badge, hx + hw - 22.0f - badgeW, hy + hh * 0.18f, 5.0f, MenuTheme.accent(210.0f, alpha));
        long total = MenuStats.totalSeconds();
        String hours = MenuStats.formatDuration(total);
        String hoursLabel = I18n.tr("в клиенте");
        float hoursSize = Math.max(8.0f, Math.min(11.0f, hh * 0.1f));
        float hoursW = Fonts.BOLD.width(hours, hoursSize);
        float labelW = Fonts.MEDIUM.width(hoursLabel, 4.6f);
        Fonts.BOLD.draw(hours, hx + hw - 22.0f - hoursW, hy + hh - hoursSize - 20.0f, hoursSize, MenuTheme.white(255.0f, alpha));
        Fonts.MEDIUM.draw(hoursLabel, hx + hw - 22.0f - labelW, hy + hh - 16.0f, 4.6f, MenuTheme.inkMuted(alpha));
    }

    private final void renderStats(float sx, float sy, float sw, float sh, float alpha) {
        if (alpha <= 0.004f) {
            return;
        }
        String[] stringArray = new String[]{I18n.tr("Запусков"), I18n.tr("Сессия"), I18n.tr("С нами с"), I18n.tr("Kimiko в сети")};
        String[] labels = stringArray;
        long first = MenuStats.firstLaunch();
        String since = first > 0L ? new SimpleDateFormat("dd.MM.yy", Locale.ROOT).format(new Date(first)) : "—";
        long session = MenuStats.sessionSeconds();
        String[] stringArray2 = new String[]{String.valueOf(MenuStats.launches()), MenuStats.formatDuration(session), since, String.valueOf(ClientPresence.INSTANCE.count())};
        String[] values = stringArray2;
        String[] stringArray3 = new String[]{"D", "z", "K", "й"};
        String[] glyphs = stringArray3;
        float gap = MenuTheme.GAP;
        float itemW = (sw - gap * (float)(labels.length - 1)) / (float)labels.length;
        int n = labels.length;
        for (int i = 0; i < n; ++i) {
            float ix = sx + (float)i * (itemW + gap);
            MenuTheme.card(ix, sy, itemW, sh, MenuTheme.CARD_RADIUS, alpha * 0.72f, 0.0f);
            float glyphSize = 8.0f;
            Fonts.I2.msdf(glyphs[i], ix + 12.0f, sy + 12.0f, glyphSize, MenuTheme.accent(160.0f, alpha));
            Fonts.BOLD.draw(values[i], ix + 12.0f, sy + sh - 27.0f, 10.0f, MenuTheme.white(250.0f, alpha));
            Fonts.MEDIUM.draw(labels[i], ix + 12.0f, sy + sh - 13.0f, 4.4f, MenuTheme.inkMuted(alpha));
        }
    }

    private final void renderInfo(float ix, float iy, float iw, float ih, float alpha) {
        float ry;
        if (alpha <= 0.004f || ih <= 20.0f) {
            return;
        }
        MenuTheme.panel(ix, iy, iw, ih, MenuTheme.PANEL_RADIUS, alpha * 0.88f);
        String title = I18n.tr("СИСТЕМА");
        Fonts.BOLD.draw(title, ix + 14.0f, iy + 13.0f, 5.6f, MenuTheme.ink(alpha * 0.9f));
        MenuTheme.hairline(ix + 14.0f, iy + 26.0f, iw - 28.0f, alpha * 0.7f);
        String[][] stringArrayArray = new String[4][];
        String[] stringArray = new String[]{I18n.tr("Клиент"), "Kimiko v1.5"};
        stringArrayArray[0] = stringArray;
        stringArray = new String[]{"Minecraft", MainWindow.getMinecraftVersionName()};
        stringArrayArray[1] = stringArray;
        stringArray = new String[]{"Java", System.getProperty("java.version", "—")};
        stringArrayArray[2] = stringArray;
        stringArray = new String[]{I18n.tr("Система"), System.getProperty("os.name", "—")};
        stringArrayArray[3] = stringArray;
        String[][] rows = stringArrayArray;
        float rowH = Math.min(22.0f, (ih - 40.0f) / (float)((Object[])rows).length);
        int n = ((Object[])rows).length;
        for (int i = 0; i < n && !((ry = iy + 34.0f + (float)i * rowH) + rowH > iy + ih - 4.0f); ++i) {
            Fonts.MEDIUM.draw(rows[i][0], ix + 14.0f, ry, 4.8f, MenuTheme.inkMuted(alpha));
            float valueW = Fonts.SEMIBOLD.width(rows[i][1], 4.8f);
            Fonts.SEMIBOLD.draw(rows[i][1], ix + iw - 14.0f - valueW, ry, 4.8f, MenuTheme.ink(alpha * 0.9f));
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/api/ui/mainmenu/pages/ProfilePage.Companion;", "", "<init>", "()V", "", "stagger", "rise", "(F)F", "", "fallbackNick", "()Ljava/lang/String;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float rise(float stagger) {
            return (1.0f - MenuTheme.ease(stagger)) * 18.0f;
        }

        private final String fallbackNick() {
            String string;
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient minecraft = minecraftClient2;
            if (minecraft.getSession() != null) {
                String string2 = minecraft.getSession().getUsername();
                string = string2;
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getName(...)");
            } else {
                string = "Player";
            }
            return string;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

