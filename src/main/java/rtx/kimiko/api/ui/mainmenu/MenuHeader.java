/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui.mainmenu;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.ui.mainmenu.MenuTheme;
import rtx.kimiko.api.ui.module.DiscordAvatar;
import rtx.kimiko.api.ui.theme.AccentGradient;
import rtx.kimiko.utils.net.ClientPresence;
import rtx.kimiko.utils.profile.ProfileIdentity;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J5\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000b\u0010\fJ'\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJG\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0019\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001aR\u0016\u0010\u001c\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001aR\u0016\u0010\u001d\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001aR\u0016\u0010\u001e\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001a\u00a8\u0006 "}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuHeader;", "", "<init>", "()V", "", "x", "y", "h", "alpha", "appear", "", "renderBrand", "(FFFFF)V", "centerY", "drawVersionChip", "(FFF)V", "right", "mouseX", "mouseY", "dt", "drawProfile", "(FFFFFFF)V", "", "inProfile", "(FF)Z", "profileHover", "F", "profileX", "profileY", "profileW", "profileH", "Companion", "rtx.kimiko:kimiko"})
public final class MenuHeader {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private float profileHover;
    private float profileX;
    private float profileY;
    private float profileW;
    private float profileH;
    @NotNull
    private static final String BRAND_GLYPH = "x";
    private static final long SHIMMER_PERIOD_MS = 7200L;
    private static final long SHIMMER_LENGTH_MS = 1500L;

    public final void renderBrand(float x, float y, float h, float alpha, float appear) {
        float slide = MenuTheme.stagger(appear, 0, 3, 0.2f);
        float drop = (1.0f - MenuTheme.backOut(slide)) * -16.0f;
        float headAlpha = alpha * MenuTheme.clamp01(slide * 1.4f);
        if (headAlpha <= 0.004f) {
            return;
        }
        float centerY = y + drop + h * 0.5f;
        float iconSize = 13.0f;
        float nameSize = 12.5f;
        float gap = 5.5f;
        float iconW = Fonts.KIMIKO.msdfWidth(BRAND_GLYPH, iconSize);
        AccentGradient.msdfIcon(Fonts.KIMIKO, BRAND_GLYPH, x, centerY - iconSize * 0.5f + 0.5f, iconSize, 235.0f * headAlpha, 0.1f);
        float nameX = x + iconW + gap;
        String string = "Kimiko";
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string2 = string.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toUpperCase(...)");
        String name = string2;
        Fonts.SMALL_PIXEL.msdf(name, nameX, centerY - nameSize * 0.5f + 0.5f, nameSize, MenuTheme.white(255.0f, headAlpha));
        long cycle = System.currentTimeMillis() % 7200L;
        if (cycle < 1500L) {
            float progress = (float)cycle / 1500.0f;
            Fonts.SMALL_PIXEL.shimmer(name, nameX, centerY - nameSize * 0.5f + 0.5f, nameSize, progress, 0.22f, 1.5f, 0.85f * headAlpha);
        }
        float nameW = Fonts.SMALL_PIXEL.msdfWidth(name, nameSize);
        float chipX = nameX + nameW + 6.0f;
        this.drawVersionChip(chipX, centerY, headAlpha);
    }

    private final void drawVersionChip(float x, float centerY, float alpha) {
        String version = "v1.5";
        float size = 4.6f;
        float textW = Fonts.SEMIBOLD.width(version, size);
        float chipW = textW + 9.0f;
        float chipH = 10.5f;
        float chipY = centerY - chipH * 0.5f;
        Render2D.rect(x, chipY, chipW, chipH, chipH * 0.5f, MenuTheme.accent(38.0f, alpha));
        Render2D.outlineClient$default(x, chipY, chipW, chipH, chipH * 0.5f, 0.6f, MenuTheme.accent(70.0f, alpha), 0.0f, 128, null);
        Fonts.SEMIBOLD.draw(version, x + (chipW - textW) * 0.5f, chipY + chipH * 0.5f - size * 0.62f, size, MenuTheme.accentBright(240.0f, alpha));
    }

    private final void drawProfile(float right, float centerY, float mouseX, float mouseY, float dt, float alpha, float appear) {
        String string = ProfileIdentity.username(MenuHeader.Companion.defaultNick());
        if (string == null) {
            string = MenuHeader.Companion.defaultNick();
        }
        String nick = string;
        int online = ClientPresence.INSTANCE.count();
        float nickSize = 5.4f;
        float nickW = Fonts.SEMIBOLD.width(nick, nickSize);
        float statusSize = 4.2f;
        String status = online > 0 ? online + I18n.tr(" в сети") : I18n.tr("не в сети");
        float statusW = Fonts.MEDIUM.width(status, statusSize);
        float avatar = 15.0f;
        float textW = Math.max(nickW, statusW);
        this.profileW = avatar + 7.0f + textW + 15.0f;
        this.profileH = 23.0f;
        this.profileX = right - this.profileW;
        this.profileY = centerY - this.profileH * 0.5f;
        boolean hovered = appear > 0.9f && mouseX >= this.profileX && mouseX <= this.profileX + this.profileW && mouseY >= this.profileY && mouseY <= this.profileY + this.profileH;
        this.profileHover = MenuTheme.approach(this.profileHover, hovered ? 1.0f : 0.0f, dt, 12.0f);
        MenuTheme.card(this.profileX, this.profileY, this.profileW, this.profileH, this.profileH * 0.5f, alpha * (0.55f + 0.35f * this.profileHover), this.profileHover);
        float avatarX = this.profileX + 4.0f;
        float avatarY = centerY - avatar * 0.5f;
        String texture = DiscordAvatar.texture();
        if (texture != null && Render2D.imageReady(texture)) {
            Render2D.image(texture, avatarX, avatarY, avatar, avatar, avatar * 0.5f, MenuTheme.white(255.0f, alpha));
        } else {
            Render2D.rect(avatarX, avatarY, avatar, avatar, avatar * 0.5f, MenuTheme.white(22.0f, alpha));
            float glyphW = Fonts.MAINMENU.msdfWidth("e", 8.0f);
            Fonts.MAINMENU.msdf("e", avatarX + (avatar - glyphW) * 0.5f, avatarY + 3.5f, 8.0f, MenuTheme.white(120.0f, alpha));
        }
        Render2D.outlineClient$default(avatarX, avatarY, avatar, avatar, avatar * 0.5f, 0.7f, MenuTheme.accent(90.0f + 70.0f * this.profileHover, alpha), 0.0f, 128, null);
        float textX = avatarX + avatar + 6.0f;
        Fonts.SEMIBOLD.draw(nick, textX, centerY - 5.6f, nickSize, MenuTheme.ink(alpha));
        float dot = 1.5f;
        int dotColor = online > 0 ? MenuTheme.fade(-10755958, alpha) : MenuTheme.white(70.0f, alpha);
        Render2D.circle(textX + dot, centerY + 3.4f, dot, dot, dotColor);
        Fonts.MEDIUM.draw(status, textX + dot * 2.0f + 3.0f, centerY + 1.2f, statusSize, MenuTheme.inkMuted(alpha));
    }

    public final boolean inProfile(float mouseX, float mouseY) {
        return mouseX >= this.profileX && mouseX <= this.profileX + this.profileW && mouseY >= this.profileY && mouseY <= this.profileY + this.profileH;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\bR\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\u000b\u00a8\u0006\r"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuHeader.Companion;", "", "<init>", "()V", "", "defaultNick", "()Ljava/lang/String;", "BRAND_GLYPH", "Ljava/lang/String;", "", "SHIMMER_PERIOD_MS", "J", "SHIMMER_LENGTH_MS", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final String defaultNick() {
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient minecraft = minecraftClient2;
            if (minecraft.getSession() != null) {
                String string = minecraft.getSession().getUsername();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getName(...)");
                return string;
            }
            return "Player";
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

