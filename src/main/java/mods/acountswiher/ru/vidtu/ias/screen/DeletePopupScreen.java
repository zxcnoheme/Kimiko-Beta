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
import mods.acountswiher.IasService;
import mods.acountswiher.ru.vidtu.ias.account.Account;
import mods.acountswiher.ru.vidtu.ias.screen.AccountScreen;
import mods.acountswiher.ru.vidtu.ias.screen.MultiLineLabelCompat;
import mods.acountswiher.ru.vidtu.ias.screen.PopupButton;
import net.minecraft.text.Text;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.client.gui.tooltip.Tooltip;

final class DeletePopupScreen
extends Screen {
    private final AccountScreen parent;
    private final Account account;
    private MultiLineLabelCompat label = MultiLineLabelCompat.EMPTY;

    DeletePopupScreen(AccountScreen parent, Account account) {
        super((Text)Text.translatable((String)"ias.delete"));
        this.parent = parent;
        this.account = account;
    }

    protected void init() {
        assert (this.client != null);
        this.clearChildren();
        this.parent.resize(this.width, this.height);
        this.label = MultiLineLabelCompat.create(this.textRenderer, (Text)Text.translatable((String)"ias.delete.confirm", (Object[])new Object[]{this.account.name()}), 150);
        PopupButton delete = new PopupButton(this.width / 2 - 75, this.height / 2 + 27, 74, 20, this.title, button -> {
            IasService.remove(this.account);
            this.parent.refresh();
            this.close();
        });
        delete.setTooltip(Tooltip.of((Text)Text.translatable((String)"ias.delete.hint", (Object[])new Object[]{Text.translatable((String)"key.keyboard.left.shift")})));
        delete.setTooltipDelay(Duration.ofMillis(250L));
        delete.color = new float[]{1.0f, 0.5f, 0.5f, 1.0f};
        this.addDrawableChild(delete);
        this.addDrawableChild(new PopupButton(this.width / 2 + 1, this.height / 2 + 27, 74, 20, ScreenTexts.CANCEL, button -> this.close()));
    }

    public void close() {
        this.client.setScreen((Screen)this.parent);
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.getMatrices().pushMatrix();
        context.getMatrices().scale(2.0f, 2.0f);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 4, this.height / 4 - 24, -1);
        context.getMatrices().popMatrix();
        this.label.renderCentered(context, this.width / 2, (this.height - this.label.getLineCount() * 9) / 2 - 4);
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

