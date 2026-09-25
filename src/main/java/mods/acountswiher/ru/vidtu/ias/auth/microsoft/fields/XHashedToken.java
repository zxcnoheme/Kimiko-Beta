/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 */
package mods.acountswiher.ru.vidtu.ias.auth.microsoft.fields;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import mods.acountswiher.ru.vidtu.ias.utils.GSONUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record XHashedToken(@NotNull String token, @NotNull String hash) {
    @Contract(value="_ -> new", pure=true)
    @NotNull
    public static XHashedToken fromJson(@NotNull JsonObject json) {
        try {
            String token = GSONUtils.getStringOrThrow(json, "Token");
            JsonObject displayClaims = GSONUtils.getObjectOrThrow(json, "DisplayClaims");
            JsonArray xui = GSONUtils.getArrayOrThrow(displayClaims, "xui");
            int size = xui.size();
            if (size != 1) {
                throw new IllegalArgumentException("Unexpected 'xui' size: " + size);
            }
            JsonElement xuiEntry = xui.get(0);
            if (!xuiEntry.isJsonObject()) {
                throw new IllegalArgumentException("Entry of 'xui' is not keyCodec JSON object: " + String.valueOf(xuiEntry));
            }
            JsonObject xuiJson = xuiEntry.getAsJsonObject();
            String uhs = GSONUtils.getStringOrThrow(xuiJson, "uhs");
            return new XHashedToken(token, uhs);
        }
        catch (Throwable t) {
            throw new JsonParseException("Unable to parse XHashedToken: " + String.valueOf(json), t);
        }
    }
}

