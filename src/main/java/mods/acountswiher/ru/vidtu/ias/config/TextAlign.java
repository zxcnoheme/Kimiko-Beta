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

public enum TextAlign {
    LEFT("ias.config.textAlign.left"),
    CENTER("ias.config.textAlign.center"),
    RIGHT("ias.config.textAlign.right");

    @NotNull
    private final String key;

    @Contract(pure=true)
    private TextAlign(String key) {
        this.key = key;
    }

    @Contract(pure=true)
    @NotNull
    public String toString() {
        return this.key;
    }
}

