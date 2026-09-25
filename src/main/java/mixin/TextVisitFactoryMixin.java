/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.text.TextVisitFactory
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 */
package mixin;

import net.minecraft.text.TextVisitFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.impl.render.TextFactoryEvent;

@Mixin(value={TextVisitFactory.class})
public class TextVisitFactoryMixin {
    @ModifyArg(method={"visitFormatted(Ljava/lang/String;ILnet/minecraft/text/Style;Lnet/minecraft/text/CharacterVisitor;)Z"}, at=@At(value="INVOKE", target="Lnet/minecraft/text/TextVisitFactory;visitFormatted(Ljava/lang/String;ILnet/minecraft/text/Style;Lnet/minecraft/text/Style;Lnet/minecraft/text/CharacterVisitor;)Z", ordinal=0), index=0, require=0)
    private static String kimiko$adjustText(String text) {
        EventBus bus = EventBus.get();
        if (!bus.hasListeners(TextFactoryEvent.class)) {
            return text;
        }
        TextFactoryEvent event = bus.post(new TextFactoryEvent(text));
        return event.getText();
    }
}

