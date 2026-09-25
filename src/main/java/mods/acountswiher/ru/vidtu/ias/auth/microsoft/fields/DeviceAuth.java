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
import java.net.URI;
import java.time.Duration;
import mods.acountswiher.ru.vidtu.ias.utils.GSONUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record DeviceAuth(@NotNull String device, @NotNull String user, @NotNull URI uri, @NotNull Duration expire, @NotNull Duration interval) {
    @Contract(value="_ -> new", pure=true)
    @NotNull
    public static DeviceAuth fromJson(@NotNull JsonObject json) {
        try {
            String device = GSONUtils.getStringOrThrow(json, "device_code");
            String user = GSONUtils.getStringOrThrow(json, "user_code");
            String rawVerificationUri = GSONUtils.getStringOrThrow(json, "verification_uri");
            URI uri = new URI(rawVerificationUri).parseServerAuthority();
            if (!"https".equals(uri.getScheme())) {
                throw new IllegalStateException("Invalid URL scheme: " + String.valueOf(uri));
            }
            long rawExpire = GSONUtils.getLongOrThrow(json, "expires_in");
            Duration expire = Duration.ofSeconds(rawExpire);
            if (expire.isNegative() || expire.isZero() || expire.toDays() > 2L) {
                throw new IllegalStateException("Invalid expire: " + String.valueOf(expire) + " (" + rawExpire + ")");
            }
            long rawInterval = GSONUtils.getLongOrThrow(json, "interval");
            Duration interval = Duration.ofSeconds(rawInterval);
            if (interval.isNegative() || interval.isZero() || interval.compareTo(expire) >= 0) {
                throw new IllegalStateException("Invalid interval (with expire of " + String.valueOf(expire) + ": " + String.valueOf(interval) + " (" + rawInterval + ")");
            }
            return new DeviceAuth(device, user, uri, expire, interval);
        }
        catch (Throwable t) {
            throw new JsonParseException("Unable to parse DeviceAuth: " + String.valueOf(json), t);
        }
    }
}

