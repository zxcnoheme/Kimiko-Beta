/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 */
package mods.acountswiher.ru.vidtu.ias.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class GSONUtils {
    @NotNull
    public static final Gson GSON = new Gson();

    @Contract(value="-> fail", pure=true)
    private GSONUtils() {
        throw new AssertionError((Object)"No instances.");
    }

    @Contract(pure=true)
    public static boolean getBooleanOrThrow(@NotNull JsonObject json, @NotNull String key) {
        try {
            return json.get(key).getAsBoolean();
        }
        catch (Throwable t) {
            throw new JsonParseException("Expected to have boolean '" + key + "': " + String.valueOf(json), t);
        }
    }

    @Contract(pure=true)
    public static int getIntOrThrow(@NotNull JsonObject json, @NotNull String key) {
        try {
            return json.get(key).getAsInt();
        }
        catch (Throwable t) {
            throw new JsonParseException("Expected to have int '" + key + "': " + String.valueOf(json), t);
        }
    }

    @Contract(pure=true)
    public static long getLongOrThrow(@NotNull JsonObject json, @NotNull String key) {
        try {
            return json.get(key).getAsLong();
        }
        catch (Throwable t) {
            throw new JsonParseException("Expected to have long '" + key + "': " + String.valueOf(json), t);
        }
    }

    @Contract(pure=true)
    @NotNull
    public static String getStringOrThrow(@NotNull JsonObject json, @NotNull String key) {
        try {
            return json.get(key).getAsString();
        }
        catch (Throwable t) {
            throw new JsonParseException("Expected to have string '" + key + "': " + String.valueOf(json), t);
        }
    }

    @Contract(pure=true)
    @NotNull
    public static JsonObject getObjectOrThrow(@NotNull JsonObject json, @NotNull String key) {
        try {
            return json.get(key).getAsJsonObject();
        }
        catch (Throwable t) {
            throw new JsonParseException("Expected to have object '" + key + "': " + String.valueOf(json), t);
        }
    }

    @Contract(pure=true)
    @NotNull
    public static JsonArray getArrayOrThrow(@NotNull JsonObject json, @NotNull String key) {
        try {
            return json.get(key).getAsJsonArray();
        }
        catch (Throwable t) {
            throw new JsonParseException("Expected to have array '" + key + "': " + String.valueOf(json), t);
        }
    }
}

