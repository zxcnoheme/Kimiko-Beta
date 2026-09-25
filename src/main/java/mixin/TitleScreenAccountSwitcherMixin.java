/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.text.Text
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.font.TextRenderer
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.Element
 *  net.minecraft.client.gui.Drawable
 *  net.minecraft.client.gui.widget.ButtonWidget
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.client.gui.screen.TitleScreen
 *  net.minecraft.client.gui.Selectable
 *  net.minecraft.client.gui.widget.PressableTextWidget
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import java.util.ArrayList;
import java.util.List;
import mixin.ScreenAccessor;
import mods.acountswiher.ru.vidtu.ias.screen.AccountScreen;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.widget.PressableTextWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={TitleScreen.class})
public abstract class TitleScreenAccountSwitcherMixin
extends Screen {
    protected TitleScreenAccountSwitcherMixin(Text title) {
        super(title);
    }

    @Inject(method={"init"}, at={@At(value="TAIL")})
    private void kimiko$replaceRealmsWithAccountSwitcher(CallbackInfo ci) {
        TitleScreen screen = (TitleScreen)(Object)this;
        ScreenAccessor access = (ScreenAccessor)((Object)this);
        List<Drawable> drawables = access.getRenderables();
        List<Element> children = access.getChildren();
        List<Selectable> selectables = access.getNarratables();
        String realmsLabel = Text.translatable((String)"menu.online").getString();
        ButtonWidget realmsButton = null;
        for (Element child : children) {
            ButtonWidget button;
            if (!(child instanceof ButtonWidget) || !realmsLabel.equals((button = (ButtonWidget)child).getMessage().getString())) continue;
            realmsButton = button;
            break;
        }
        int x = this.width / 2 - 100;
        int y = this.height / 4 + 96;
        int width = 200;
        int height = 20;
        if (realmsButton != null) {
            x = realmsButton.getX();
            y = realmsButton.getY();
            width = realmsButton.getWidth();
            height = realmsButton.getHeight();
            drawables.remove(realmsButton);
            children.remove(realmsButton);
            selectables.remove(realmsButton);
        }
        ButtonWidget switcher = ButtonWidget.builder((Text)Text.literal((String)"Account Switcher"), elementCodec -> MinecraftClient.getInstance().setScreen((Screen)new AccountScreen((Screen)screen))).dimensions(x, y, width, height).build();
        this.addDrawableChild(switcher);
        for (Element child : new ArrayList<Element>(children)) {
            if (!(child instanceof PressableTextWidget)) continue;
            PressableTextWidget copyright = (PressableTextWidget)child;
            drawables.remove(copyright);
            children.remove(copyright);
            selectables.remove(copyright);
        }
    }

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)V"))
    private void kimiko$hideVersionText(DrawContext graphics, TextRenderer font, String text, int x, int y, int color) {
    }
}

