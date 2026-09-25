/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  lombok.Generated
 */
package mods.waveycapes.versionless;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import lombok.Generated;
import mods.waveycapes.versionless.config.Config;
import mods.waveycapes.versionless.nms.MinecraftPlayer;
import mods.waveycapes.versionless.util.Vector3;

public abstract class ModBase {
    public static Config config;
    public static boolean simulationBroken;
    private final File settingsFile = new File("config", "waveycapes.json");
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static ModBase INSTANCE;

    public void init() {
        INSTANCE = this;
        if (this.settingsFile.exists()) {
            try {
                config = (Config)this.gson.fromJson(new String(Files.readAllBytes(this.settingsFile.toPath()), StandardCharsets.UTF_8), Config.class);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        if (config == null) {
            config = new Config();
            this.writeConfig();
        } else if (ModBase.config.configVersion == 1) {
            ModBase.config.configVersion = 2;
            if (ModBase.config.gravity < 0) {
                ModBase.config.gravity *= -1;
            }
            this.writeConfig();
        }
    }

    public void writeConfig() {
        if (this.settingsFile.exists()) {
            this.settingsFile.delete();
        }
        try {
            Files.write(this.settingsFile.toPath(), this.gson.toJson((Object)config).getBytes(StandardCharsets.UTF_8), new OpenOption[0]);
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    public abstract void initSupportHooks();

    protected static boolean doesClassExist(String name) {
        try {
            if (Class.forName(name) != null) {
                return true;
            }
        }
        catch (ClassNotFoundException classNotFoundException) {
            // empty catch block
        }
        return false;
    }

    public abstract Vector3 applyModAnimations(MinecraftPlayer var1, Vector3 var2);

    @Generated
    public static ModBase getINSTANCE() {
        return INSTANCE;
    }

    static {
        simulationBroken = false;
    }
}

