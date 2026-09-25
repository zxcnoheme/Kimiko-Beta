/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.text.Text
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.widget.ButtonWidget
 *  net.minecraft.client.gui.widget.ButtonWidget$PressAction
 */
package mods.acountswiher.ru.vidtu.ias.screen;

import java.util.Locale;
import java.util.function.Supplier;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;

public final class ServerShortcutButton
extends ButtonWidget {
    private final Supplier<Identifier> textureSupplier;
    private final String label;

    public ServerShortcutButton(int x, int y, Supplier<Identifier> textureSupplier, String name, ButtonWidget.PressAction onPress) {
        super(x, y, 18, 18, net.minecraft.text.Text.empty(), onPress, DEFAULT_NARRATION_SUPPLIER);
        this.textureSupplier = textureSupplier;
        this.label = ServerShortcutButton.initials(name);
    }

    protected void drawIcon(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        int x = this.getX();
        int y = this.getY();
        this.drawButton(context);
        Identifier texture = this.textureSupplier.get();
        if (ServerShortcutButton.hasTexture(texture)) {
            context.drawTexture(RenderPipelines.GUI_TEXTURED, texture, x + 1, y + 1, 0.0f, 0.0f, 16, 16, 64, 64, 64, 64);
        } else {
            MinecraftClient mc = MinecraftClient.getInstance();
            context.drawCenteredTextWithShadow(mc.textRenderer, this.label, x + 9, y + 6, -1);
        }
    }

    private static boolean hasTexture(Identifier id) {
        if (id == null) {
            return false;
        }
        try {
            return MinecraftClient.getInstance().getResourceManager().getResource(id).isPresent();
        }
        catch (Throwable ignored) {
            return false;
        }
    }

    private static String initials(String name) {
        if (name == null || name.isBlank()) {
            return "?";
        }
        String s = name.trim();
        StringBuilder out = new StringBuilder(2);
        for (int i = 0; i < s.length() && out.length() < 2; ++i) {
            char c = s.charAt(i);
            if (!Character.isLetterOrDigit(c)) continue;
            out.append(Character.toUpperCase(c));
        }
        return out.length() == 0 ? s.substring(0, 1).toUpperCase(Locale.ROOT) : out.toString();
    }
}

