/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 */
package mods.chatanim.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public final class ModConfig {
    private static ModConfig instance;
    private static final Gson GSON;
    private transient File configFile;
    public boolean enableMessageAnimation = true;
    public boolean enableTextFieldAnimation = true;
    public boolean removeMessageIndicator = true;
    public int fadeTimeMessage = 150;
    public int fadeTimeTextField = 170;

    public static ModConfig getConfig() {
        if (instance == null) {
            instance = new ModConfig();
        }
        return instance;
    }

    public void load(File configFile) {
        this.configFile = configFile;
        if (!configFile.exists()) {
            this.save();
            return;
        }
        try (FileReader reader = new FileReader(configFile);){
            ModConfig loaded = (ModConfig)GSON.fromJson((Reader)reader, ModConfig.class);
            if (loaded != null) {
                instance = loaded;
                ModConfig.instance.configFile = configFile;
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    public void save() {
        if (this.configFile == null) {
            return;
        }
        try (FileWriter writer = new FileWriter(this.configFile);){
            GSON.toJson((Object)this, (Appendable)writer);
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    static {
        GSON = new GsonBuilder().setPrettyPrinting().create();
    }
}

