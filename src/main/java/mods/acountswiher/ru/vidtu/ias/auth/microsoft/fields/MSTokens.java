/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 */
package mods.acountswiher.ru.vidtu.ias.auth.microsoft.fields;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import mods.acountswiher.ru.vidtu.ias.utils.GSONUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record MSTokens(@NotNull String access, @NotNull String refresh) {
    @Contract(value="_ -> new", pure=true)
    @NotNull
    public static MSTokens fromJson(@NotNull JsonObject json) {
        try {
            String access = GSONUtils.getStringOrThrow(json, "access_token");
            String refresh = GSONUtils.getStringOrThrow(json, "refresh_token");
            return new MSTokens(access, refresh);
        }
        catch (Throwable t) {
            throw new JsonParseException("Unable to parse MSTokens: " + String.valueOf(json), t);
        }
    }
}

