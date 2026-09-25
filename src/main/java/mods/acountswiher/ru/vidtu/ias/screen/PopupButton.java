/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.text.Text
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.gui.widget.ButtonWidget
 *  net.minecraft.client.gui.widget.ButtonWidget$PressAction
 */
package mods.acountswiher.ru.vidtu.ias.screen;

import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.gui.widget.ButtonWidget;

final class PopupButton
extends ButtonWidget {
    float[] color = new float[]{1.0f, 1.0f, 1.0f, 0.0f};
    private float currentRed = 1.0f;
    private float currentGreen = 1.0f;
    private float currentBlue = 1.0f;
    private float multiplier = 1.0f;

    PopupButton(int x, int y, int width, int height, net.minecraft.text.Text text, ButtonWidget.PressAction onPress) {
        super(x, y, width, height, text, onPress, DEFAULT_NARRATION_SUPPLIER);
    }

    protected void drawIcon(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        float shade = 0.78f;
        this.multiplier = this.isSelected() && this.active ? MathHelper.clamp((float)(this.multiplier - deltaTicks * 0.25f), (float)0.75f, (float)1.0f) : MathHelper.clamp((float)(this.multiplier + deltaTicks * 0.25f), (float)0.75f, (float)1.0f);
        if (this.color[3] != 0.0f) {
            this.currentRed = this.color[0];
            this.currentGreen = this.color[1];
            this.currentBlue = this.color[2];
            this.color[3] = 0.0f;
        }
        this.currentRed += (this.color[0] - this.currentRed) * deltaTicks;
        this.currentGreen += (this.color[1] - this.currentGreen) * deltaTicks;
        this.currentBlue += (this.color[2] - this.currentBlue) * deltaTicks;
        int r = (int)(this.multiplier * 255.0f * shade * this.currentRed);
        int g = (int)(this.multiplier * 255.0f * shade * this.currentGreen);
        int elementCodec = (int)(this.multiplier * 255.0f * shade * this.currentBlue);
        int color = 0xFF000000 | r << 16 | g << 8 | elementCodec;
        int x = this.getX();
        int y = this.getY();
        int width = this.getWidth();
        int height = this.getHeight();
        context.fill(x, y + 1, x + width, y + height - 1, color);
        context.fill(x + 1, y, x + width - 1, y + 1, color);
        context.fill(x + 1, y + height - 1, x + width - 1, y + height, color);
        int textColor = this.active ? -1 : -6250336;
        context.drawCenteredTextWithShadow(MinecraftClient.getInstance().textRenderer, this.getMessage(), x + width / 2, y + height / 2 - 4, textColor);
    }
}

