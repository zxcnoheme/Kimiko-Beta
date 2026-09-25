/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Element
 *  net.minecraft.client.gui.Drawable
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.client.gui.Selectable
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package mixin;

import java.util.List;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.Selectable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={Screen.class})
public interface ScreenAccessor {
    @Accessor(value="drawables")
    public List<Drawable> getRenderables();

    @Accessor(value="children")
    public List<Element> getChildren();

    @Accessor(value="selectables")
    public List<Selectable> getNarratables();
}

