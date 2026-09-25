/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.client.gui.Click
 *  net.minecraft.util.Util
 *  net.minecraft.text.Text
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.session.Session
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget$Entry
 *  net.minecraft.screen.ScreenTexts
 *  net.minecraft.text.OrderedText
 *  net.minecraft.client.gui.PlayerSkinDrawer
 *  net.minecraft.entity.player.SkinTextures
 */
package mods.acountswiher.ru.vidtu.ias.screen;

import java.util.List;
import java.util.Objects;
import mods.acountswiher.ru.vidtu.ias.account.Account;
import mods.acountswiher.ru.vidtu.ias.screen.AccountList;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.Click;
import net.minecraft.util.Util;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.session.Session;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.OrderedText;
import net.minecraft.client.gui.PlayerSkinDrawer;
import net.minecraft.entity.player.SkinTextures;

final class AccountEntry
extends AlwaysSelectedEntryListWidget.Entry<AccountEntry> {
    private static final Identifier UP_PLAIN = Identifier.of((String)"ias", (String)"up_plain");
    private static final Identifier UP_DISABLED = Identifier.of((String)"ias", (String)"up_disabled");
    private static final Identifier UP_FOCUS = Identifier.of((String)"ias", (String)"up_focus");
    private static final Identifier DOWN_PLAIN = Identifier.of((String)"ias", (String)"down_plain");
    private static final Identifier DOWN_DISABLED = Identifier.of((String)"ias", (String)"down_disabled");
    private static final Identifier DOWN_FOCUS = Identifier.of((String)"ias", (String)"down_focus");
    private static final Identifier WARNING_OFF = Identifier.of((String)"ias", (String)"warning_off");
    private static final Identifier WARNING_ON = Identifier.of((String)"ias", (String)"warning_on");
    private final MinecraftClient client;
    private final AccountList list;
    private final Account account;
    private final List<OrderedText> tooltip;
    private long clicked = Util.getMeasuringTimeMs();
    private long lastFree = System.nanoTime();

    AccountEntry(MinecraftClient client, AccountList list, Account account) {
        this.client = client;
        this.list = list;
        this.account = account;
        this.tooltip = List.of(ScreenTexts.composeGenericOptionText((Text)Text.translatable((String)"ias.accounts.tip.nick"), (Text)Text.literal((String)account.name())).asOrderedText(), ScreenTexts.composeGenericOptionText((Text)Text.translatable((String)"ias.accounts.tip.uuid"), (Text)Text.literal((String)account.uuid().toString())).asOrderedText(), ScreenTexts.composeGenericOptionText((Text)Text.translatable((String)"ias.accounts.tip.type"), (Text)Text.translatable((String)account.typeTipKey())).asOrderedText());
    }

    public void render(DrawContext context, int mouseX, int mouseY, boolean hovered, float delta) {
        String username;
        int x = this.getContentX();
        int y = this.getContentY();
        int width = this.getContentWidth();
        int height = this.getContentHeight();
        if (hovered) {
            if (System.nanoTime() - this.lastFree >= 500000000L) {
                context.drawOrderedTooltip(this.client.textRenderer, this.tooltip, mouseX, mouseY);
            }
        } else {
            this.lastFree = System.nanoTime();
        }
        SkinTextures skin = this.list.skin(this);
        PlayerSkinDrawer.draw((DrawContext)context, (SkinTextures)skin, (int)x, (int)y, (int)8);
        Session session = this.client.getSession();
        String string = username = session != null ? session.getUsername() : "";
        int color = !this.account.name().equalsIgnoreCase(username) ? -1 : (session != null && Objects.equals(this.account.uuid(), session.getUuidOrNull()) ? -16711936 : (this.account.name().equals(username) ? -256 : Short.MIN_VALUE));
        context.drawText(this.client.textRenderer, this.account.name(), x + 10, y, color, false);
        if (this.account.insecure()) {
            boolean warning = System.nanoTime() / 1000000000L % 2L == 0L;
            context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, warning ? WARNING_ON : WARNING_OFF, x - 6, y - 1, 2, 10);
            if (mouseX >= x - 10 && mouseX <= x && mouseY >= y && mouseY <= y + height) {
                context.drawTooltip(this.client.textRenderer, (Text)Text.translatable((String)"ias.accounts.tip.insecure"), mouseX, mouseY);
            }
        }
        if (this.equals(this.list.getFocused()) || this.equals((Object)this.list.getSelected())) {
            int upX = x + width - 28;
            Identifier upTexture = this == this.list.children().getFirst() ? UP_DISABLED : (mouseX >= upX && mouseY >= y && mouseX <= upX + 11 && mouseY <= y + height ? UP_FOCUS : UP_PLAIN);
            context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, upTexture, upX, y, 11, 7);
            int downX = x + width - 15;
            Identifier downTexture = this == this.list.children().getLast() ? DOWN_DISABLED : (mouseX >= downX && mouseY >= y && mouseX <= downX + 11 && mouseY <= y + height ? DOWN_FOCUS : DOWN_PLAIN);
            context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, downTexture, downX, y, 11, 7);
        }
    }

    public boolean mouseClicked(Click event, boolean doubleClick) {
        double mouseX = event.x();
        double mouseY = event.y();
        int button = event.button();
        this.list.setSelected(this);
        if (this.equals(this.list.getFocused()) || this.equals((Object)this.list.getSelected())) {
            int right = this.getContentRightEnd();
            int upX = right - 28;
            if (mouseX >= (double)upX && mouseX <= (double)(upX + 11)) {
                this.list.swapUp(this);
                return true;
            }
            int downX = right - 15;
            if (mouseX >= (double)downX && mouseX <= (double)(downX + 11)) {
                this.list.swapDown(this);
                return true;
            }
        }
        if (Util.getMeasuringTimeMs() - this.clicked < 250L) {
            this.list.login(!MinecraftClient.getInstance().isShiftPressed());
        }
        this.clicked = Util.getMeasuringTimeMs();
        return true;
    }

    public Text getNarration() {
        return Text.literal((String)this.account.name());
    }

    Account account() {
        return this.account;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccountEntry)) {
            return false;
        }
        AccountEntry that = (AccountEntry)((Object)obj);
        return Objects.equals(this.account, that.account);
    }

    public int hashCode() {
        return Objects.hashCode(this.account);
    }
}

