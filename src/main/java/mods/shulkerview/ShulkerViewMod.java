/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback
 */
package mods.shulkerview;

import mods.shulkerview.tooltip.ShulkerPreviewClientTooltipComponent;
import mods.shulkerview.tooltip.ShulkerPreviewTooltipComponent;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;

public final class ShulkerViewMod {
    private static boolean initialized;

    private ShulkerViewMod() {
    }

    public static void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        TooltipComponentCallback.EVENT.register(data -> {
            if (data instanceof ShulkerPreviewTooltipComponent) {
                ShulkerPreviewTooltipComponent preview = (ShulkerPreviewTooltipComponent)data;
                return new ShulkerPreviewClientTooltipComponent(preview);
            }
            return null;
        });
    }
}

