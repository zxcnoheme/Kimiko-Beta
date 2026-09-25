/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.input.KeyInput
 *  net.minecraft.text.Text
 *  net.minecraft.text.Style
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.font.TextRenderer
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.widget.TextFieldWidget
 *  net.minecraft.text.MutableText
 *  net.minecraft.text.OrderedText
 */
package mods.acountswiher.ru.vidtu.ias.screen;

import mods.acountswiher.ru.vidtu.ias.config.IASConfig;
import net.minecraft.client.input.KeyInput;
import net.minecraft.text.Text;
import net.minecraft.text.Style;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.MutableText;
import net.minecraft.text.OrderedText;

final class PopupBox
extends TextFieldWidget {
    private final Runnable enterAction;
    private final boolean secure;

    PopupBox(TextRenderer textRenderer, int x, int y, int width, int height, Text title, Runnable enterAction, boolean secure) {
        super(textRenderer, x, y, width, height, title);
        this.enterAction = enterAction;
        this.secure = secure;
        this.wirst$installMask();
    }

    PopupBox(TextRenderer textRenderer, int x, int y, int width, int height, PopupBox inherit, Text title, Runnable enterAction, boolean secure) {
        this(textRenderer, x, y, width, height, title, enterAction, secure);
        if (inherit != null) {
            this.setText(inherit.getText());
        }
    }

    public void renderWidget(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        int x = this.getX();
        int y = this.getY();
        int width = this.getWidth();
        int height = this.getHeight();
        context.fill(x + 1, y + 1, x + width - 1, y + height - 1, -16777216);
        context.fill(x + 1, y, x + width - 1, y + 1, -1);
        context.fill(x + 1, y + height - 1, x + width - 1, y + height, -1);
        context.fill(x, y + 1, x + 1, y + height - 1, -1);
        context.fill(x + width - 1, y + 1, x + width, y + height - 1, -1);
        super.renderWidget(context, mouseX, mouseY, deltaTicks);
    }

    public boolean keyPressed(KeyInput event) {
        int keyCode = event.key();
        int scanCode = event.scancode();
        int modifiers = event.modifiers();
        if (this.enterAction != null && this.active && this.isFocused() && (keyCode == 257 || keyCode == 335)) {
            this.enterAction.run();
            return true;
        }
        if (this.secure && (keyCode == 67 && MinecraftClient.getInstance().isCtrlPressed() || keyCode == 88 && MinecraftClient.getInstance().isCtrlPressed())) {
            return true;
        }
        return super.keyPressed(event);
    }

    protected MutableText getNarrationMessage() {
        if (!this.secure) {
            return super.getNarrationMessage();
        }
        return Text.translatable((String)"gui.narrate.editBox", (Object[])new Object[]{this.getMessage(), Text.empty()});
    }

    public boolean drawsBackground() {
        return false;
    }

    private void wirst$installMask() {
        if (!this.secure) {
            return;
        }
        this.addFormatter((text, firstCharacterIndex) -> {
            if (!IASConfig.passwordEchoing) {
                return OrderedText.EMPTY;
            }
            return OrderedText.styledForwardsVisitedString((String)"*".repeat(text.length()), (Style)Style.EMPTY);
        });
    }
}

