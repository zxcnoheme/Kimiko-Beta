/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.text.Text
 *  net.minecraft.client.font.TextRenderer
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.text.StringVisitable
 *  net.minecraft.text.OrderedText
 */
package mods.acountswiher.ru.vidtu.ias.screen;

import java.util.List;
import net.minecraft.text.Text;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.OrderedText;

public final class MultiLineLabelCompat {
    public static final MultiLineLabelCompat EMPTY = new MultiLineLabelCompat(null, List.of());
    private final TextRenderer font;
    private final List<OrderedText> lines;

    private MultiLineLabelCompat(TextRenderer font, List<OrderedText> lines) {
        this.font = font;
        this.lines = lines;
    }

    public static MultiLineLabelCompat create(TextRenderer font, Text text, int width) {
        return new MultiLineLabelCompat(font, font.wrapLines((StringVisitable)text, width));
    }

    public int getLineCount() {
        return this.lines.size();
    }

    public void renderCentered(DrawContext graphics, int x, int y) {
        if (this.font == null) {
            return;
        }
        int yy = y;
        for (OrderedText line : this.lines) {
            graphics.drawCenteredTextWithShadow(this.font, line, x, yy, -1);
            yy += 9;
        }
    }
}

