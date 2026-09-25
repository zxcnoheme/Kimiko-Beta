/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.input.KeyInput
 *  net.minecraft.text.Text
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.Element
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.screen.ScreenTexts
 *  net.minecraft.client.gui.tooltip.Tooltip
 *  org.lwjgl.glfw.GLFW
 */
package mods.acountswiher.ru.vidtu.ias.screen;

import java.time.Duration;
import mods.acountswiher.ru.vidtu.ias.account.Account;
import mods.acountswiher.ru.vidtu.ias.config.IASConfig;
import mods.acountswiher.ru.vidtu.ias.crypt.DummyCrypt;
import mods.acountswiher.ru.vidtu.ias.crypt.HardwareCrypt;
import mods.acountswiher.ru.vidtu.ias.screen.AccountScreen;
import mods.acountswiher.ru.vidtu.ias.screen.MicrosoftPopupScreen;
import mods.acountswiher.ru.vidtu.ias.screen.PopupButton;
import net.minecraft.client.input.KeyInput;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.client.gui.tooltip.Tooltip;
import org.lwjgl.glfw.GLFW;

final class MicrosoftCryptPopupScreen
extends Screen {
    private final AccountScreen parent;
    private final Account original;
    private PopupButton plain;

    MicrosoftCryptPopupScreen(AccountScreen parent, Account original) {
        super((Text)Text.translatable((String)"ias.microsoft"));
        this.parent = parent;
        this.original = original;
    }

    protected void init() {
        assert (this.client != null);
        this.clearChildren();
        this.parent.resize(this.width, this.height);
        PopupButton password = new PopupButton(this.width / 2 - 75, this.height / 2 - 36, 150, 20, (Text)Text.translatable((String)"ias.microsoft.password"), button -> this.client.setScreen((Screen)new MicrosoftPopupScreen(this.parent, this.original, null)));
        password.setTooltip(Tooltip.of((Text)Text.translatable((String)"ias.microsoft.password.tip")));
        password.setTooltipDelay(Duration.ofMillis(250L));
        password.color = new float[]{0.5f, 1.0f, 0.5f, 1.0f};
        this.addDrawableChild(password);
        PopupButton hardware = new PopupButton(this.width / 2 - 75, this.height / 2 - 12, 150, 20, (Text)Text.translatable((String)"ias.microsoft.hardware"), button -> this.client.setScreen((Screen)new MicrosoftPopupScreen(this.parent, this.original, HardwareCrypt.INSTANCE_V2)));
        hardware.setTooltip(Tooltip.of((Text)Text.translatable((String)"ias.microsoft.hardware.tip")));
        hardware.setTooltipDelay(Duration.ofMillis(250L));
        hardware.color = new float[]{1.0f, 1.0f, 0.5f, 1.0f};
        this.addDrawableChild(hardware);
        this.plain = new PopupButton(this.width / 2 - 75, this.height / 2 + 12, 150, 20, (Text)Text.translatable((String)"ias.microsoft.plain"), button -> this.client.setScreen((Screen)new MicrosoftPopupScreen(this.parent, this.original, DummyCrypt.INSTANCE)));
        if (IASConfig.allowNoCrypt) {
            this.plain.setTooltip(Tooltip.of((Text)Text.translatable((String)"ias.microsoft.plain.tip.off", (Object[])new Object[]{Text.translatable((String)"key.keyboard.left.alt"), Text.literal((String)String.valueOf(GLFW.glfwGetKeyName((int)89, (int)-1)))})));
        } else {
            this.plain.setTooltip(Tooltip.of((Text)Text.translatable((String)"ias.microsoft.plain.tip.no")));
        }
        this.plain.setTooltipDelay(Duration.ofMillis(250L));
        this.plain.color = new float[]{1.0f, 0.5f, 0.5f, 1.0f};
        this.plain.active = false;
        this.addDrawableChild(this.plain);
        this.addDrawableChild(new PopupButton(this.width / 2 - 75, this.height / 2 + 57, 150, 20, ScreenTexts.CANCEL, button -> this.close()));
    }

    public void close() {
        assert (this.client != null);
        this.client.setScreen((Screen)this.parent);
    }

    public boolean keyPressed(KeyInput event) {
        int keyCode = event.key();
        int scanCode = event.scancode();
        int modifiers = event.modifiers();
        if (keyCode == 89 && IASConfig.allowNoCrypt && this.plain != null && !this.plain.active && MinecraftClient.getInstance().isAltPressed()) {
            this.plain.active = true;
            this.plain.setTooltip(Tooltip.of((Text)Text.translatable((String)"ias.microsoft.plain.tip.on")));
            this.plain.setTooltipDelay(Duration.ofMillis(250L));
            this.plain.color = new float[]{1.0f, 0.25f, 0.25f, 0.0f};
        }
        return super.keyPressed(event);
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.getMatrices().pushMatrix();
        context.getMatrices().scale(2.0f, 2.0f);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 4, this.height / 4 - 39, -1);
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
        context.fill(centerX - 80, centerY - 80, centerX + 80, centerY + 80, -132112336);
        context.fill(centerX - 79, centerY - 81, centerX + 79, centerY - 80, -132112336);
        context.fill(centerX - 79, centerY + 80, centerX + 79, centerY + 81, -132112336);
    }
}

