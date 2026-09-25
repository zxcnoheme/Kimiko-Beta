/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.input.KeyInput
 *  net.minecraft.util.Formatting
 *  net.minecraft.text.Text
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.Element
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.screen.ScreenTexts
 *  net.minecraft.client.gui.tooltip.Tooltip
 */
package mods.acountswiher.ru.vidtu.ias.screen;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.Executor;
import mods.acountswiher.ru.vidtu.ias.account.Account;
import mods.acountswiher.ru.vidtu.ias.account.OfflineAccount;
import mods.acountswiher.ru.vidtu.ias.auth.microsoft.MSAuth;
import mods.acountswiher.ru.vidtu.ias.screen.AccountScreen;
import mods.acountswiher.ru.vidtu.ias.screen.PopupBox;
import mods.acountswiher.ru.vidtu.ias.screen.PopupButton;
import net.minecraft.client.input.KeyInput;
import net.minecraft.util.Formatting;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.client.gui.tooltip.Tooltip;

final class OfflinePopupScreen
extends Screen {
    private final AccountScreen parent;
    private final Account original;
    private PopupBox name;
    private PopupButton done;
    private boolean locked;

    OfflinePopupScreen(AccountScreen parent, Account original) {
        super((Text)Text.translatable((String)"ias.offline"));
        this.parent = parent;
        this.original = original;
    }

    protected void init() {
        assert (this.client != null);
        this.clearChildren();
        this.parent.resize(this.width, this.height);
        this.name = new PopupBox(this.textRenderer, this.width / 2 - 75, this.height / 2 - 5, 148, 20, (Text)Text.translatable((String)"ias.offline.nick"), this::save, false);
        this.name.setMaxLength(16);
        this.name.setPlaceholder((Text)Text.literal((String)(this.original != null ? this.original.name() : "Steve")).copy().formatted(Formatting.DARK_GRAY));
        if (this.original != null) {
            this.name.setText(this.original.name());
        }
        this.name.setChangedListener(value -> this.updateButton(false));
        this.addDrawableChild(this.name);
        this.done = new PopupButton(this.width / 2 - 75, this.height / 2 + 27, 74, 20, ScreenTexts.DONE, button -> this.save());
        this.done.color = new float[]{1.0f, 0.5f, 0.5f, 1.0f};
        this.addDrawableChild(this.done);
        this.addDrawableChild(new PopupButton(this.width / 2 + 1, this.height / 2 + 27, 74, 20, ScreenTexts.CANCEL, button -> this.close()));
        this.updateButton(true);
    }

    public void close() {
        this.client.setScreen((Screen)this.parent);
    }

    public boolean keyPressed(KeyInput event) {
        int keyCode = event.key();
        int scanCode = event.scancode();
        int modifiers = event.modifiers();
        boolean result = super.keyPressed(event);
        if (keyCode == 342 || keyCode == 346) {
            this.updateButton(false);
        }
        return result;
    }

    public boolean keyReleased(KeyInput event) {
        int keyCode = event.key();
        int scanCode = event.scancode();
        int modifiers = event.modifiers();
        boolean result = super.keyReleased(event);
        if (keyCode == 342 || keyCode == 346) {
            this.updateButton(false);
        }
        return result;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.getMatrices().pushMatrix();
        context.getMatrices().scale(2.0f, 2.0f);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 4, this.height / 4 - 24, -1);
        context.getMatrices().popMatrix();
        context.drawCenteredTextWithShadow(this.textRenderer, (Text)Text.translatable((String)"ias.offline.nick"), this.width / 2, this.height / 2 - 15, -1);
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

    private void save() {
        if (this.name == null) {
            return;
        }
        String value = this.name.getText().trim();
        if (value.isBlank()) {
            return;
        }
        int length = value.length();
        if (length < 3 || length > 16) {
            this.parent.addOrReplace(this.original, new OfflineAccount(value, null));
            this.close();
            return;
        }
        for (int i = 0; i < length; ++i) {
            int c = value.codePointAt(i);
            if (c == 95 || c >= 48 && c <= 57 || c >= 97 && c <= 122 || c >= 65 && c <= 90) continue;
            this.parent.addOrReplace(this.original, new OfflineAccount(value, null));
            this.close();
            return;
        }
        this.locked = true;
        this.updateButton(false);
        MSAuth.nameToMcp(value).whenCompleteAsync((profile, throwable) -> {
            UUID skin = profile != null ? profile.uuid() : null;
            this.parent.addOrReplace(this.original, new OfflineAccount(value, skin));
            if (this.client != null) {
                this.client.setScreen((Screen)this.parent);
            }
        }, (Executor)this.client);
    }

    private void updateButton(boolean instant) {
        if (this.done == null || this.name == null) {
            return;
        }
        if (this.locked) {
            this.done.active = false;
            this.name.active = false;
            this.done.setTooltip(null);
            this.done.color = new float[]{0.5f, 0.5f, 0.5f, instant ? 1.0f : 0.0f};
            return;
        }
        String value = this.name.getText();
        this.name.active = true;
        if (value.isBlank()) {
            this.done.active = false;
            this.done.setTooltip(Tooltip.of((Text)Text.translatable((String)"ias.offline.nick.blank")));
            this.done.setTooltipDelay(Duration.ZERO);
            this.done.color = new float[]{1.0f, 0.5f, 0.5f, instant ? 1.0f : 0.0f};
            return;
        }
        int length = value.length();
        boolean alt = MinecraftClient.getInstance().isAltPressed();
        this.done.active = true;
        if (length < 3) {
            this.done.active = alt;
            this.done.setTooltip(Tooltip.of((Text)Text.translatable((String)"ias.offline.nick.short", (Object[])new Object[]{Text.translatable((String)"key.keyboard.left.alt")})));
            this.done.setTooltipDelay(Duration.ZERO);
            this.done.color = new float[]{alt ? 0.75f : 1.0f, alt ? 0.75f : 1.0f, 0.25f, instant ? 1.0f : 0.0f};
            return;
        }
        if (length > 16) {
            this.done.active = alt;
            this.done.setTooltip(Tooltip.of((Text)Text.translatable((String)"ias.offline.nick.long", (Object[])new Object[]{Text.translatable((String)"key.keyboard.left.alt")})));
            this.done.setTooltipDelay(Duration.ZERO);
            this.done.color = new float[]{alt ? 0.75f : 1.0f, alt ? 0.75f : 1.0f, 0.25f, instant ? 1.0f : 0.0f};
            return;
        }
        for (int i = 0; i < length; ++i) {
            int c = value.codePointAt(i);
            if (c == 95 || c >= 48 && c <= 57 || c >= 97 && c <= 122 || c >= 65 && c <= 90) continue;
            this.done.active = alt;
            this.done.setTooltip(Tooltip.of((Text)Text.translatable((String)"ias.offline.nick.chars", (Object[])new Object[]{Character.toString(c), Text.translatable((String)"key.keyboard.left.alt")})));
            this.done.setTooltipDelay(Duration.ZERO);
            this.done.color = new float[]{alt ? 0.75f : 1.0f, alt ? 0.75f : 1.0f, 0.25f, instant ? 1.0f : 0.0f};
            return;
        }
        this.done.setTooltip(null);
        this.done.color = new float[]{0.5f, 1.0f, 0.5f, instant ? 1.0f : 0.0f};
    }
}

