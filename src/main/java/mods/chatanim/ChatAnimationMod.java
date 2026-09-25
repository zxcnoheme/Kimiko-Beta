/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.loader.api.FabricLoader
 */
package mods.chatanim;

import java.io.File;
import mods.chatanim.config.ModConfig;
import net.fabricmc.loader.api.FabricLoader;

public final class ChatAnimationMod {
    public static final String MOD_ID = "chatanimation";
    public static final String CONFIG_FILE = "chatanimation.json";

    private ChatAnimationMod() {
    }

    public static void init() {
        File configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), CONFIG_FILE);
        ModConfig.getConfig().load(configFile);
    }
}

