/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.text.Text
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.resource.ResourcePackManager
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.screen.multiplayer.ConnectScreen
 *  net.minecraft.client.gui.screen.option.OptionsScreen
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen
 *  net.minecraft.client.gui.screen.world.SelectWorldScreen
 *  net.minecraft.client.gui.screen.pack.PackScreen
 *  net.minecraft.client.network.ServerAddress
 *  net.minecraft.client.option.ServerList
 *  net.minecraft.client.network.ServerInfo
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui.mainmenu.pages;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.screen.pack.PackScreen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.option.ServerList;
import net.minecraft.client.network.ServerInfo;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.ui.ClientLanguage;
import rtx.kimiko.api.ui.mainmenu.MainMenuScreen;
import rtx.kimiko.api.ui.mainmenu.MenuPage;
import rtx.kimiko.api.ui.mainmenu.MenuStats;
import rtx.kimiko.api.ui.mainmenu.MenuTheme;
import rtx.kimiko.utils.profile.ProfileIdentity;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RoundedScissor;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\f\u0018\u0000 L2\u00020\u0001:\u0001LB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0003J?\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ/\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014Jw\u0010!\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\u001fH\u0002\u00a2\u0006\u0004\b!\u0010\"JW\u0010'\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\b2\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\u001fH\u0002\u00a2\u0006\u0004\b'\u0010(JW\u0010-\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\b2\u0006\u0010+\u001a\u00020\b2\u0006\u0010,\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\u001fH\u0002\u00a2\u0006\u0004\b-\u0010(J'\u0010/\u001a\u00020\u001f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010.\u001a\u00020\u0015H\u0016\u00a2\u0006\u0004\b/\u00100J\u0019\u00103\u001a\u00020\u00042\b\u00102\u001a\u0004\u0018\u000101H\u0002\u00a2\u0006\u0004\b3\u00104J\u0017\u00106\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u001fH\u0002\u00a2\u0006\u0004\b6\u00107R\u0014\u00109\u001a\u0002088\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b9\u0010:R\u0014\u0010;\u001a\u0002088\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b;\u0010:R\u0016\u0010<\u001a\u0002088\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u0010:R$\u0010?\u001a\u0012\u0012\u0004\u0012\u0002010=j\b\u0012\u0004\u0012\u000201`>8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u0010@R\u0016\u0010B\u001a\u00020A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0016\u0010D\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bD\u0010ER\u0016\u0010F\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bF\u0010ER\u0016\u0010G\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bG\u0010ER\u0016\u0010H\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bH\u0010ER\u0016\u0010I\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bI\u0010ER\u0016\u0010J\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bJ\u0010ER\u0016\u0010K\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010E\u00a8\u0006M"}, d2={"Lrtx/kimiko/api/ui/mainmenu/pages/PlayPage;", "Lrtx/kimiko/api/ui/mainmenu/MenuPage;", "<init>", "()V", "", "onShow", "Lnet/minecraft/DrawContext;", "graphics", "", "mouseX", "mouseY", "dt", "alpha", "appear", "render", "(Lnet/minecraft/DrawContext;FFFFF)V", "gx", "gy", "gw", "renderGreeting", "(FFFF)V", "", "index", "cx", "cy", "cw", "ch", "", "texture", "title", "subtitle", "", "interactive", "renderBigCard", "(IFFFFLjava/lang/String;Ljava/lang/String;Ljava/lang/String;FFFFZ)V", "qx", "qy", "qw", "qh", "renderQuickRow", "(FFFFFFFFZ)V", "sx", "sy", "sw", "sh", "renderServerPanel", "button", "mouseClicked", "(FFI)Z", "Lnet/minecraft/ServerInfo;", "data", "connect", "(Lnet/minecraft/ServerInfo;)V", "force", "refreshServers", "(Z)V", "", "cardHover", "[F", "quickHover", "serverHover", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "servers", "Ljava/util/ArrayList;", "", "serversStamp", "J", "leftW", "F", "rightX", "rightW", "cardH", "cardTop", "allServersY", "allServersHover", "Companion", "rtx.kimiko:kimiko"})
public final class PlayPage
extends MenuPage {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final float[] cardHover = new float[2];
    @NotNull
    private final float[] quickHover = new float[3];
    @NotNull
    private float[] serverHover = new float[0];
    @NotNull
    private final ArrayList<ServerInfo> servers = new ArrayList();
    private long serversStamp;
    private float leftW;
    private float rightX;
    private float rightW;
    private float cardH;
    private float cardTop;
    private float allServersY;
    private float allServersHover;
    @NotNull
    private static final String SINGLE_TEXTURE = "kimiko:images/mainmenu/singleplayer.png";
    @NotNull
    private static final String MULTI_TEXTURE = "kimiko:images/mainmenu/multiplayer.png";
    private static final int SERVER_LIMIT = 4;
    private static final float GREETING_H = 32.0f;

    @Override
    public void onShow() {
        this.refreshServers(true);
    }

    @Override
    public void render(@NotNull DrawContext graphics, float mouseX, float mouseY, float dt, float alpha, float appear) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        if (alpha <= 0.004f) {
            return;
        }
        this.refreshServers(false);
        this.leftW = this.w * 0.585f;
        this.rightX = this.x + this.leftW + MenuTheme.GAP;
        this.rightW = this.w - this.leftW - MenuTheme.GAP;
        float quickH = 30.0f;
        this.cardTop = this.y + 32.0f;
        this.cardH = (this.h - 32.0f - MenuTheme.GAP - quickH - MenuTheme.GAP) * 0.5f;
        boolean interactive = appear > 0.85f;
        float sg = MenuTheme.stagger(appear, 0, 6, 0.2f);
        float s0 = MenuTheme.stagger(appear, 1, 6, 0.2f);
        float s1 = MenuTheme.stagger(appear, 2, 6, 0.2f);
        float s2 = MenuTheme.stagger(appear, 3, 6, 0.2f);
        float s3 = MenuTheme.stagger(appear, 4, 6, 0.2f);
        this.renderGreeting(this.x, this.y + PlayPage.Companion.rise(sg) * 0.6f, this.w, alpha * sg);
        this.renderBigCard(0, this.x, this.cardTop + PlayPage.Companion.rise(s0), this.leftW, this.cardH, SINGLE_TEXTURE, I18n.tr("Одиночная игра"), I18n.tr("Твои миры"), mouseX, mouseY, dt, alpha * s0, interactive);
        this.renderBigCard(1, this.x, this.cardTop + this.cardH + MenuTheme.GAP + PlayPage.Companion.rise(s1), this.leftW, this.cardH, MULTI_TEXTURE, I18n.tr("Сетевая игра"), I18n.tr("Серверы и друзья"), mouseX, mouseY, dt, alpha * s1, interactive);
        this.renderQuickRow(this.x, this.y + this.h - quickH, this.leftW, quickH, mouseX, mouseY, dt, alpha * s2, interactive);
        this.renderServerPanel(this.rightX, this.cardTop + PlayPage.Companion.rise(s3), this.rightW, this.h - 32.0f, mouseX, mouseY, dt, alpha * s3, interactive);
    }

    private final void renderGreeting(float gx, float gy, float gw, float alpha) {
        if (alpha <= 0.004f) {
            return;
        }
        String string = ProfileIdentity.username(PlayPage.Companion.fallbackNick());
        if (string == null) {
            string = PlayPage.Companion.fallbackNick();
        }
        String nick = string;
        int hour = LocalTime.now().getHour();
        String greeting = MenuStats.launches() <= 1 ? I18n.tr("Добро пожаловать") : (hour < 5 ? I18n.tr("Всё ещё не спишь") : (hour < 12 ? I18n.tr("Доброе утро") : (hour < 18 ? I18n.tr("Добрый день") : I18n.tr("Добрый вечер"))));
        float size = 9.0f;
        Fonts.LIGHT.draw(greeting + ",", gx + 2.0f, gy + 3.0f, size, MenuTheme.inkSoft(alpha * 0.92f));
        float greetW = Fonts.LIGHT.width(greeting + ",", size);
        Fonts.SEMIBOLD.draw(nick, gx + 2.0f + greetW + 4.0f, gy + 3.0f, size, MenuTheme.white(250.0f, alpha));
        String rawDate = new SimpleDateFormat("EEEE, d MMMM", Locale.forLanguageTag(ClientLanguage.code())).format(new Date());
        String date = !rawDate.isEmpty() ? rawDate.substring(0, 1).toUpperCase(Locale.ROOT) + rawDate.substring(1) : "";
        float dateW = Fonts.MEDIUM.width(date, 4.8f);
        Fonts.MEDIUM.draw(date, gx + gw - dateW - 2.0f, gy + 6.5f, 4.8f, MenuTheme.inkMuted(alpha * 0.85f));
    }

    private final void renderBigCard(int index, float cx, float cy, float cw, float ch, String texture, String title, String subtitle, float mouseX, float mouseY, float dt, float alpha, boolean interactive) {
        if (alpha <= 0.004f) {
            return;
        }
        boolean hovered = interactive && this.inside(mouseX, mouseY, cx, cy, cw, ch);
        this.cardHover[index] = MenuTheme.approach(this.cardHover[index], hovered ? 1.0f : 0.0f, dt, 11.0f);
        float hover = this.cardHover[index];
        float radius = 13.0f;
        MenuTheme.card(cx, cy, cw, ch, radius, alpha, hover);
        if (Render2D.imageReady(texture)) {
            float zoom = 1.02f + hover * 0.045f;
            float imageAspect = 2.3300972f;
            float drawW = 0.0f;
            float drawH = 0.0f;
            if (imageAspect >= cw / ch) {
                drawH = ch * zoom;
                drawW = drawH * imageAspect;
            } else {
                drawW = cw * zoom;
                drawH = drawW / imageAspect;
            }
            float shiftX = (mouseX - (cx + cw * 0.5f)) / Math.max(1.0f, cw) * -7.0f * hover;
            float shiftY = (mouseY - (cy + ch * 0.5f)) / Math.max(1.0f, ch) * -5.0f * hover;
            float ix = cx + (cw - drawW) * 0.5f + shiftX;
            float iy = cy + (ch - drawH) * 0.5f + shiftY;
            RoundedScissor.push(cx, cy, cw, ch, radius, radius, radius, radius);
            Render2D.image(texture, ix, iy, drawW, drawH, 0.0f, MenuTheme.white(228.0f + 27.0f * hover, alpha));
            int shadeTop = MenuTheme.black(18.0f, alpha);
            int shadeBottom = MenuTheme.black(205.0f, alpha);
            Render2D.rect(cx, cy, cw, ch, radius, shadeTop, shadeTop, shadeBottom, shadeBottom);
            int sideLeft = MenuTheme.black(120.0f, alpha);
            Render2D.rect(cx, cy, cw * 0.55f, ch, radius, sideLeft, 0, 0, sideLeft);
            MenuTheme.spotlight(cx, cy, cw, ch, radius, mouseX, mouseY, hover, alpha);
            RoundedScissor.pop();
        }
        Render2D.outline(cx, cy, cw, ch, radius, 0.8f, MenuTheme.mix(MenuTheme.white(26.0f, alpha), MenuTheme.accent(150.0f, alpha), hover));
        float titleSize = Math.max(9.0f, Math.min(15.0f, ch * 0.15f));
        float subSize = Math.max(4.2f, titleSize * 0.4f);
        float textX = cx + 18.0f + hover * 3.0f;
        float subBaseY = cy + ch - 18.0f - subSize;
        float baseY = subBaseY - titleSize - 2.0f;
        Fonts.BOLD.draw(title, textX, baseY, titleSize, MenuTheme.white(255.0f, alpha));
        Fonts.MEDIUM.draw(subtitle, textX, subBaseY, subSize, MenuTheme.white(140.0f + 60.0f * hover, alpha));
        float arrowSize = 9.0f;
        float arrowW = Fonts.I2.msdfWidth("G", arrowSize);
        float arrowX = cx + cw - 20.0f - arrowW + hover * 5.0f;
        Fonts.I2.msdf("G", arrowX, subBaseY - arrowSize * 0.4f, arrowSize, MenuTheme.mix(MenuTheme.white(90.0f, alpha), MenuTheme.accentBright(255.0f, alpha), hover));
        if (hover > 0.01f) {
            Render2D.rect(cx + 18.0f, cy + ch - 10.0f, (cw - 36.0f) * hover, 0.9f, 0.45f, MenuTheme.accent(190.0f, alpha * hover));
        }
    }

    private final void renderQuickRow(float qx, float qy, float qw, float qh, float mouseX, float mouseY, float dt, float alpha, boolean interactive) {
        if (alpha <= 0.004f) {
            return;
        }
        String[] stringArray = new String[]{I18n.tr("Настройки"), I18n.tr("Ресурспаки"), I18n.tr("Выйти")};
        String[] labels = stringArray;
        String[] stringArray2 = new String[]{"л", "E", "п"};
        String[] glyphs = stringArray2;
        float gap = 6.0f;
        float itemW = (qw - gap * (float)(labels.length - 1)) / (float)labels.length;
        int n = labels.length;
        for (int i = 0; i < n; ++i) {
            float ix = qx + (float)i * (itemW + gap);
            boolean hovered = interactive && this.inside(mouseX, mouseY, ix, qy, itemW, qh);
            this.quickHover[i] = MenuTheme.approach(this.quickHover[i], hovered ? 1.0f : 0.0f, dt, 12.0f);
            float hover = this.quickHover[i];
            MenuTheme.card(ix, qy, itemW, qh, 9.0f, alpha * (0.55f + 0.3f * hover), hover);
            float glyphSize = 7.4f;
            float textSize = 5.1f;
            float glyphW = Fonts.I2.msdfWidth(glyphs[i], glyphSize);
            float textW = Fonts.SEMIBOLD.width(labels[i], textSize);
            float startX = ix + (itemW - (glyphW + 5.5f + textW)) * 0.5f;
            float centerY = qy + qh * 0.5f;
            int color = i == labels.length - 1 ? MenuTheme.mix(MenuTheme.white(140.0f, alpha), MenuTheme.fade(-36219, alpha), hover) : MenuTheme.white(150.0f + 90.0f * hover, alpha);
            Fonts.I2.msdf(glyphs[i], startX, centerY - glyphSize * 0.5f + 0.3f, glyphSize, color);
            Fonts.SEMIBOLD.draw(labels[i], startX + glyphW + 5.5f, centerY - textSize * 0.62f, textSize, color);
        }
    }

    /*
     * Unable to fully structure code
     */
    private final void renderServerPanel(float sx, float sy, float sw, float sh, float mouseX, float mouseY, float dt, float alpha, boolean interactive) {
        if (alpha <= 0.004f) {
            return;
        }
        MenuTheme.panel(sx, sy, sw, sh, MenuTheme.PANEL_RADIUS, alpha * 0.9f);
        float headerSize = 5.6f;
        String header = I18n.tr("БЫСТРЫЙ ВХОД");
        Fonts.BOLD.draw(header, sx + 14.0f, sy + 14.0f, headerSize, MenuTheme.ink(alpha * 0.9f));
        float glyphSize = 7.0f;
        Fonts.I2.msdf("й", sx + sw - 14.0f - Fonts.I2.msdfWidth("й", glyphSize), sy + 13.0f, glyphSize, MenuTheme.accent(190.0f, alpha));
        MenuTheme.hairline(sx + 14.0f, sy + 26.0f, sw - 28.0f, alpha * 0.7f);
        if (this.servers.isEmpty()) {
            String empty = I18n.tr("Пока нет сохранённых серверов");
            float size = 5.0f;
            float textW = Fonts.MEDIUM.width(empty, size);
            Fonts.MEDIUM.draw(empty, sx + (sw - textW) * 0.5f, sy + sh * 0.42f, size, MenuTheme.inkMuted(alpha));
        }
        if (this.serverHover.length != this.servers.size()) {
            this.serverHover = new float[this.servers.size()];
        }
        float rowH = 38.0f;
        float rowY = sy + 34.0f;
        int serverCount = this.servers.size();
        for (int i = 0; i < serverCount; ++i) {
            ServerInfo data = this.servers.get(i);
            float ry = rowY + (float)i * (rowH + 5.0f);
            if (ry + rowH > sy + sh - 32.0f) break;
            boolean hovered = interactive && this.inside(mouseX, mouseY, sx + 8.0f, ry, sw - 16.0f, rowH);
            this.serverHover[i] = MenuTheme.approach(this.serverHover[i], hovered ? 1.0f : 0.0f, dt, 12.0f);
            float hover = this.serverHover[i];
            if (hover > 0.008f) {
                Render2D.rect(sx + 8.0f, ry, sw - 16.0f, rowH, 8.0f, MenuTheme.white(14.0f * hover, alpha));
                Render2D.rect(sx + 8.0f, ry + rowH * 0.22f, 2.0f, rowH * 0.56f, 1.0f, MenuTheme.accent(220.0f, alpha * hover));
            }
            float iconSize = 20.0f;
            float iconX = sx + 16.0f;
            float iconY = ry + (rowH - iconSize) * 0.5f;
            Render2D.rect(iconX, iconY, iconSize, iconSize, 5.0f, MenuTheme.white(16.0f, alpha));
            String initial = (data.name == null || StringsKt.isBlank(data.name)) 
                ? "?" 
                : data.name.substring(0, 1).toUpperCase(Locale.ROOT);
            float initialW = Fonts.BOLD.width(initial, 7.0f);
            Fonts.BOLD.draw(initial, iconX + (iconSize - initialW) * 0.5f, iconY + iconSize * 0.5f - 4.3f, 7.0f, MenuTheme.accentBright(220.0f, alpha));
            float textX = iconX + iconSize + 8.0f;
            float maxW = sw - (textX - sx) - 16.0f;
            String v6 = data.name;
            if (v6 == null) {
                v6 = "";
            }
            String name = Companion.access$trim(PlayPage.Companion, v6, 6.0f, maxW);
            String v7 = data.address;
            if (v7 == null) {
                v7 = "";
            }
            String ip = Companion.access$trim(PlayPage.Companion, v7, 4.4f, maxW);
            Fonts.SEMIBOLD.draw(name, textX, ry + 9.0f, 6.0f, MenuTheme.ink(alpha));
            Fonts.MEDIUM.draw(ip, textX, ry + 20.0f, 4.4f, MenuTheme.inkMuted(alpha * (0.8f + 0.2f * hover)));
        }
        this.allServersY = sy + sh - 26.0f;
        boolean allHovered = interactive && this.inside(mouseX, mouseY, sx + 12.0f, this.allServersY, sw - 24.0f, 18.0f);
        this.allServersHover = MenuTheme.approach(this.allServersHover, allHovered ? 1.0f : 0.0f, dt, 12.0f);
        String all = I18n.tr("Все серверы");
        float allW = Fonts.SEMIBOLD.width(all, 4.8f);
        float arrowW = Fonts.I2.msdfWidth("G", 6.0f);
        float startX = sx + (sw - (allW + 5.0f + arrowW)) * 0.5f + this.allServersHover * 2.0f;
        if (this.allServersHover > 0.01f) {
            Render2D.rect(sx + 12.0f, this.allServersY, sw - 24.0f, 18.0f, 7.0f, MenuTheme.white(12.0f * this.allServersHover, alpha));
        }
        int allColor = MenuTheme.white(120.0f + 110.0f * this.allServersHover, alpha);
        Fonts.SEMIBOLD.draw(all, startX, this.allServersY + 6.2f, 4.8f, allColor);
        Fonts.I2.msdf("G", startX + allW + 5.0f, this.allServersY + 6.0f, 6.0f, allColor);
    }

    @Override
    public boolean mouseClicked(float mouseX, float mouseY, int button) {
        if (button != 0) {
            return false;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        MainMenuScreen parent = MainMenuScreen.Companion.instance();
        if (this.inside(mouseX, mouseY, this.x, this.cardTop, this.leftW, this.cardH)) {
            Sounds.play("gui_click");
            minecraft.setScreen((Screen)new SelectWorldScreen((Screen)parent));
            return true;
        }
        if (this.inside(mouseX, mouseY, this.x, this.cardTop + this.cardH + MenuTheme.GAP, this.leftW, this.cardH)) {
            Sounds.play("gui_click");
            minecraft.setScreen((Screen)new MultiplayerScreen((Screen)parent));
            return true;
        }
        float quickH = 30.0f;
        float quickY = this.y + this.h - quickH;
        if (this.inside(mouseX, mouseY, this.x, quickY, this.leftW, quickH)) {
            float gap = 6.0f;
            float itemW = (this.leftW - gap * 2.0f) / 3.0f;
            int index = (int)((mouseX - this.x) / (itemW + gap));
            Sounds.play("gui_click");
            switch (index) {
                case 0: {
                    minecraft.setScreen((Screen)new OptionsScreen((Screen)parent, minecraft.options));
                    break;
                }
                case 1: {
                    minecraft.setScreen((Screen)new PackScreen(minecraft.getResourcePackManager(), arg_0 -> PlayPage.mouseClicked$lambda$0(minecraft, parent, arg_0), minecraft.getResourcePackDir(), (Text)Text.translatable((String)"resourcePack.title")));
                    break;
                }
                default: {
                    minecraft.scheduleStop();
                }
            }
            return true;
        }
        if (this.inside(mouseX, mouseY, this.rightX + 12.0f, this.allServersY, this.rightW - 24.0f, 18.0f)) {
            Sounds.play("gui_click");
            minecraft.setScreen((Screen)new MultiplayerScreen((Screen)parent));
            return true;
        }
        if (!((Collection)this.servers).isEmpty() && mouseX >= this.rightX && mouseX <= this.rightX + this.rightW) {
            float rowH = 38.0f;
            float rowY = this.cardTop + 34.0f;
            int n = ((Collection)this.servers).size();
            for (int i = 0; i < n; ++i) {
                float ry = rowY + (float)i * (rowH + 5.0f);
                if (!this.inside(mouseX, mouseY, this.rightX + 8.0f, ry, this.rightW - 16.0f, rowH)) continue;
                this.connect(this.servers.get(i));
                return true;
            }
        }
        return false;
    }

    private final void connect(ServerInfo data) {
        block5: {
            block4: {
                if (data == null || data.address == null) break block4;
                String string = data.address;
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"ip");
                if (!StringsKt.isBlank((CharSequence)string)) break block5;
            }
            return;
        }
        Sounds.play("gui_click");
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        try {
            ConnectScreen.connect((Screen)MainMenuScreen.Companion.instance(), (MinecraftClient)minecraft, (ServerAddress)ServerAddress.parse((String)data.address), (ServerInfo)data, (boolean)false, null);
        }
        catch (Throwable ignored) {
            minecraft.setScreen((Screen)new MultiplayerScreen((Screen)MainMenuScreen.Companion.instance()));
        }
    }

    private final void refreshServers(boolean force) {
        long now = System.currentTimeMillis();
        if (!force && now - this.serversStamp < 4000L) {
            return;
        }
        this.serversStamp = now;
        try {
            ServerList list = new ServerList(MinecraftClient.getInstance());
            list.loadFile();
            this.servers.clear();
            int count = Math.min(4, list.size());
            for (int i = 0; i < count; ++i) {
                this.servers.add(list.get(i));
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private static final void mouseClicked$lambda$0(MinecraftClient $minecraft, MainMenuScreen $parent, ResourcePackManager repository) {
        Intrinsics.checkNotNullParameter((Object)repository, (String)"repository");
        $minecraft.options.refreshResourcePacks(repository);
        $minecraft.setScreen((Screen)$parent);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\t\u0010\nJ'\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0011R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/api/ui/mainmenu/pages/PlayPage.Companion;", "", "<init>", "()V", "", "fallbackNick", "()Ljava/lang/String;", "", "stagger", "rise", "(F)F", "text", "size", "maxWidth", "trim", "(Ljava/lang/String;FF)Ljava/lang/String;", "SINGLE_TEXTURE", "Ljava/lang/String;", "MULTI_TEXTURE", "", "SERVER_LIMIT", "I", "GREETING_H", "F", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
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

        private final float rise(float stagger) {
            return (1.0f - MenuTheme.ease(stagger)) * 18.0f;
        }

        private final String trim(String text, float size, float maxWidth) {
            if (Fonts.MEDIUM.width(text, size) <= maxWidth) {
                return text;
            }
            StringBuilder builder = new StringBuilder();
            char[] cArray = text.toCharArray();
            Intrinsics.checkNotNullExpressionValue((Object)cArray, (String)"toCharArray(...)");
            for (char c : cArray) {
                builder.append(c);
                if (!(Fonts.MEDIUM.width(builder + "…", size) > maxWidth)) continue;
                builder.setLength(Math.max(0, builder.length() - 1));
                break;
            }
            return builder + "…";
        }

        public static final /* synthetic */ String access$trim(Companion $this, String text, float size, float maxWidth) {
            return $this.trim(text, size, maxWidth);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

