/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 */
package mods.acountswiher.ru.vidtu.ias.auth;

import java.util.UUID;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record LoginData(@NotNull String name, @NotNull UUID uuid, @NotNull String token, boolean online) {
    @Override
    @Contract(pure=true)
    @NotNull
    public String toString() {
        return "LoginData{name='" + this.name + "', uuid=" + String.valueOf(this.uuid) + ", token=[TOKEN], online=" + this.online + "}";
    }
}

