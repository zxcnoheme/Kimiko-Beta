/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.text.Text
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.Element
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.screen.ScreenTexts
 *  net.minecraft.client.gui.tooltip.Tooltip
 */
package mods.acountswiher.ru.vidtu.ias.screen;

import java.time.Duration;
import mods.acountswiher.ru.vidtu.ias.account.Account;
import mods.acountswiher.ru.vidtu.ias.screen.AccountScreen;
import mods.acountswiher.ru.vidtu.ias.screen.MicrosoftCryptPopupScreen;
import mods.acountswiher.ru.vidtu.ias.screen.OfflinePopupScreen;
import mods.acountswiher.ru.vidtu.ias.screen.PopupButton;
import net.minecraft.text.Text;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.client.gui.tooltip.Tooltip;

final class AddPopupScreen
extends Screen {
    private final AccountScreen parent;
    private final boolean edit;
    private final Account original;

    AddPopupScreen(AccountScreen parent, boolean edit, Account original) {
        super((Text)Text.translatable((String)(edit ? "ias.edit" : "ias.add")));
        this.parent = parent;
        this.edit = edit;
        this.original = original;
    }

    protected void init() {
        assert (this.client != null);
        this.clearChildren();
        this.parent.resize(this.width, this.height);
        PopupButton microsoft = new PopupButton(this.width / 2 - 75, this.height / 2 - 24, 150, 20, (Text)Text.translatable((String)"ias.add.microsoft"), button -> this.client.setScreen((Screen)new MicrosoftCryptPopupScreen(this.parent, this.original)));
        microsoft.setTooltip(Tooltip.of((Text)Text.translatable((String)"ias.add.microsoft.tip")));
        microsoft.setTooltipDelay(Duration.ofMillis(250L));
        microsoft.color = new float[]{0.5f, 1.0f, 0.5f, 1.0f};
        this.addDrawableChild(microsoft);
        PopupButton offline = new PopupButton(this.width / 2 - 75, this.height / 2, 150, 20, (Text)Text.translatable((String)"ias.add.offline"), button -> this.client.setScreen((Screen)new OfflinePopupScreen(this.parent, this.original)));
        offline.setTooltip(Tooltip.of((Text)Text.translatable((String)"ias.add.offline.tip")));
        offline.setTooltipDelay(Duration.ofMillis(250L));
        offline.color = new float[]{1.0f, 0.5f, 0.5f, 1.0f};
        this.addDrawableChild(offline);
        this.addDrawableChild(new PopupButton(this.width / 2 - 75, this.height / 2 + 27, 150, 20, ScreenTexts.CANCEL, button -> this.close()));
    }

    public void close() {
        assert (this.client != null);
        this.client.setScreen((Screen)this.parent);
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.getMatrices().pushMatrix();
        context.getMatrices().scale(2.0f, 2.0f);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 4, this.height / 4 - 24, -1);
        context.getMatrices().popMatrix();
    }

    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        if (this.parent != null) {
            this.parent.renderWithTooltip(context, 0, 0, delta);
            context.fill(0, 0, this.width, this.height, Integer.MIN_VALUE);
        } else {
            super.renderBackground(context, mouseX, mouseY, delta);
        }
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        context.fill(centerX - 80, centerY - 50, centerX + 80, centerY + 50, -132112336);
        context.fill(centerX - 79, centerY - 51, centerX + 79, centerY - 50, -132112336);
        context.fill(centerX - 79, centerY + 50, centerX + 79, centerY + 51, -132112336);
    }
}

