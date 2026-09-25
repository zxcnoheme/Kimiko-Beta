/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 */
package mods.acountswiher.ru.vidtu.ias.config;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum ServerMode {
    ALWAYS("ias.config.server.always"),
    AVAILABLE("ias.config.server.available"),
    NEVER("ias.config.server.never");

    @NotNull
    private final String key;

    @Contract(pure=true)
    private ServerMode(String key) {
        this.key = key;
    }

    @Contract(pure=true)
    @NotNull
    public String toString() {
        return this.key;
    }
}

