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
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mods.acountswiher.ru.vidtu.ias.utils.GSONUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record MCProfile(@NotNull UUID uuid, @NotNull String name) {
    @NotNull
    private static final Pattern UUID_DASHLESS = Pattern.compile("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})");
    @NotNull
    private static final String UUID_DASHED = "$1-$2-$3-$4-$5";

    @Contract(value="_ -> new", pure=true)
    @NotNull
    public static MCProfile fromJson(@NotNull JsonObject json) {
        try {
            String id = GSONUtils.getStringOrThrow(json, "id");
            String name = GSONUtils.getStringOrThrow(json, "name");
            Matcher matcher = UUID_DASHLESS.matcher(id);
            if (!matcher.matches()) {
                throw new IllegalStateException("Invalid UUID: " + id);
            }
            id = matcher.replaceAll(UUID_DASHED);
            UUID uuid = UUID.fromString(id);
            return new MCProfile(uuid, name);
        }
        catch (Throwable t) {
            throw new JsonParseException("Unable to parse MCProfile: " + String.valueOf(json), t);
        }
    }
}

