/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package mods.acountswiher.ru.vidtu.ias.auth.microsoft;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import mods.acountswiher.ru.vidtu.ias.IAS;
import mods.acountswiher.ru.vidtu.ias.auth.microsoft.fields.DeviceAuth;
import mods.acountswiher.ru.vidtu.ias.auth.microsoft.fields.MCProfile;
import mods.acountswiher.ru.vidtu.ias.auth.microsoft.fields.MSTokens;
import mods.acountswiher.ru.vidtu.ias.auth.microsoft.fields.XHashedToken;
import mods.acountswiher.ru.vidtu.ias.utils.GSONUtils;
import mods.acountswiher.ru.vidtu.ias.utils.exceptions.DevicePendingException;
import mods.acountswiher.ru.vidtu.ias.utils.exceptions.FriendlyException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import recovery.privacy.NetworkPolicy;

public final class MSAuth {
    @NotNull
    private static final HttpClient CLIENT = NetworkPolicy.redirects(HttpClient.newBuilder().connectTimeout(IAS.TIMEOUT).version(HttpClient.Version.HTTP_2), HttpClient.Redirect.NEVER).executor(IAS.executor()).build();
    @NotNull
    private static final HttpClient CLIENT_SYNC = NetworkPolicy.redirects(HttpClient.newBuilder().connectTimeout(IAS.TIMEOUT).version(HttpClient.Version.HTTP_2), HttpClient.Redirect.NEVER).executor(Runnable::run).build();

    @Contract(value="-> fail", pure=true)
    private MSAuth() {
        throw new AssertionError((Object)"No instances.");
    }

    @NotNull
    public static CompletableFuture<DeviceAuth> requestDac() {
        String payload = "client_id=54fd49e4-2103-4044-9603-2b028c814ec3&scope=XboxLive.signin%20XboxLive.offline_access";
        return NetworkPolicy.sendAsync(CLIENT, HttpRequest.newBuilder().uri(URI.create("https://login.microsoftonline.com/consumers/oauth2/v2.0/devicecode")).header("User-Agent", IAS.USER_AGENT).header("Accept", "application/json").header("Content-Type", "application/x-www-form-urlencoded").timeout(IAS.TIMEOUT).POST(HttpRequest.BodyPublishers.ofString(payload)).build(), HttpResponse.BodyHandlers.ofString()).thenApplyAsync(response -> {
            try {
                int status = response.statusCode();
                if (status != 200) {
                    throw new IllegalArgumentException("Invalid status code: " + status);
                }
                JsonObject json = (JsonObject)GSONUtils.GSON.fromJson((String)response.body(), JsonObject.class);
                Objects.requireNonNull(json, "Response is null");
                return DeviceAuth.fromJson(json);
            }
            catch (Throwable t) {
                String message = "Unable to request Device Auth Code (DAC) from (" + String.valueOf(response) + " with " + String.valueOf(response.headers()) + "): " + (String)response.body();
                throw new RuntimeException(message, t);
            }
        }, (Executor)IAS.executor());
    }

    @NotNull
    public static MSTokens dacToMsaMsr(@NotNull String code) {
        HttpResponse<String> response;
        String payload = "grant_type=urn:ietf:params:oauth:grant-type:device_code&client_id=54fd49e4-2103-4044-9603-2b028c814ec3&device_code=" + URLEncoder.encode(code, StandardCharsets.UTF_8);
        try {
            response = NetworkPolicy.send(CLIENT_SYNC, HttpRequest.newBuilder().uri(URI.create("https://login.microsoftonline.com/consumers/oauth2/v2.0/token")).header("User-Agent", IAS.USER_AGENT).header("Accept", "application/json").header("Content-Type", "application/x-www-form-urlencoded").timeout(IAS.TIMEOUT).POST(HttpRequest.BodyPublishers.ofString(payload)).build(), HttpResponse.BodyHandlers.ofString());
        }
        catch (Throwable t) {
            throw new RuntimeException("Unable to send DAC request.", t);
        }
        try {
            int status = response.statusCode();
            if (status != 200) {
                try {
                    JsonObject json = (JsonObject)GSONUtils.GSON.fromJson(response.body(), JsonObject.class);
                    String error = GSONUtils.getStringOrThrow(json, "error");
                    if ("authorization_declined".equals(error)) {
                        throw new FriendlyException("Cancelled: " + String.valueOf(json), "ias.error.cancel");
                    }
                    if ("authorization_pending".equals(error)) {
                        throw new DevicePendingException("Pending auth: " + String.valueOf(json));
                    }
                    throw new IllegalStateException("Not pending auth.");
                }
                catch (Throwable t) {
                    throw new IllegalArgumentException("Invalid status code: " + status, t);
                }
            }
            JsonObject json = (JsonObject)GSONUtils.GSON.fromJson(response.body(), JsonObject.class);
            Objects.requireNonNull(json, "Response is null");
            return MSTokens.fromJson(json);
        }
        catch (Throwable t) {
            Object message = "Unable to convert Device Auth Code (DAC) to Microsoft Access (MSA) and Microsoft Refresh (MSR) tokens (" + String.valueOf(response) + " with " + String.valueOf(response.headers()) + "): " + response.body();
            message = ((String)message).replace(code, "[DAC]");
            throw new RuntimeException((String)message, t);
        }
    }

    @NotNull
    public static CompletableFuture<MSTokens> msacToMsaMsr(@NotNull String code, @NotNull String redirect) {
        String payload = "client_id=54fd49e4-2103-4044-9603-2b028c814ec3&code=" + URLEncoder.encode(code, StandardCharsets.UTF_8) + "&grant_type=authorization_code&redirect_uri=" + URLEncoder.encode(redirect, StandardCharsets.UTF_8) + "&scope=XboxLive.signin%20XboxLive.offline_access";
        return NetworkPolicy.sendAsync(CLIENT, HttpRequest.newBuilder().uri(URI.create("https://login.live.com/oauth20_token.srf")).header("User-Agent", IAS.USER_AGENT).header("Accept", "application/json").header("Content-Type", "application/x-www-form-urlencoded").timeout(IAS.TIMEOUT).POST(HttpRequest.BodyPublishers.ofString(payload)).build(), HttpResponse.BodyHandlers.ofString()).thenApplyAsync(response -> {
            try {
                int status = response.statusCode();
                if (status != 200) {
                    throw new IllegalArgumentException("Invalid status code: " + status);
                }
                JsonObject json = (JsonObject)GSONUtils.GSON.fromJson((String)response.body(), JsonObject.class);
                Objects.requireNonNull(json, "Response is null");
                return MSTokens.fromJson(json);
            }
            catch (Throwable t) {
                Object message = "Unable to convert Microsoft Authentication Code (MSAC) to Microsoft Access (MSA) and Microsoft Refresh (MSR) tokens (" + String.valueOf(response) + " with " + String.valueOf(response.headers()) + "): " + (String)response.body();
                message = ((String)message).replace(code, "[MSAC]");
                throw new RuntimeException((String)message, t);
            }
        }, (Executor)IAS.executor());
    }

    @NotNull
    public static CompletableFuture<MSTokens> msrToMsaMsr(@NotNull String refresh) {
        String payload = "client_id=54fd49e4-2103-4044-9603-2b028c814ec3&refresh_token=" + URLEncoder.encode(refresh, StandardCharsets.UTF_8) + "&grant_type=refresh_token&scope=XboxLive.signin%20XboxLive.offline_access";
        return NetworkPolicy.sendAsync(CLIENT, HttpRequest.newBuilder().uri(URI.create("https://login.live.com/oauth20_token.srf")).header("User-Agent", IAS.USER_AGENT).header("Accept", "application/json").header("Content-Type", "application/x-www-form-urlencoded").timeout(IAS.TIMEOUT).POST(HttpRequest.BodyPublishers.ofString(payload)).build(), HttpResponse.BodyHandlers.ofString()).thenApplyAsync(response -> {
            try {
                int status = response.statusCode();
                if (status != 200) {
                    throw new IllegalArgumentException("Invalid status code: " + status);
                }
                JsonObject json = (JsonObject)GSONUtils.GSON.fromJson((String)response.body(), JsonObject.class);
                Objects.requireNonNull(json, "Response is null");
                return MSTokens.fromJson(json);
            }
            catch (Throwable t) {
                Object message = "Unable to convert Microsoft Refresh (MSR) token to Microsoft Access (MSA) and Microsoft Refresh (MSR) tokens (" + String.valueOf(response) + " with " + String.valueOf(response.headers()) + "): " + (String)response.body();
                message = ((String)message).replace(refresh, "[MSR]");
                throw new RuntimeException((String)message, t);
            }
        }, (Executor)IAS.executor());
    }

    @NotNull
    public static CompletableFuture<XHashedToken> msaToXbl(@NotNull String authToken) {
        JsonObject request = new JsonObject();
        JsonObject requestProperties = new JsonObject();
        requestProperties.addProperty("AuthMethod", "RPS");
        requestProperties.addProperty("SiteName", "user.auth.xboxlive.com");
        requestProperties.addProperty("RpsTicket", "d=" + authToken);
        request.add("Properties", (JsonElement)requestProperties);
        request.addProperty("RelyingParty", "http://auth.xboxlive.com");
        request.addProperty("TokenType", "JWT");
        String payload = GSONUtils.GSON.toJson((JsonElement)request);
        return NetworkPolicy.sendAsync(CLIENT, HttpRequest.newBuilder().uri(URI.create("https://user.auth.xboxlive.com/user/authenticate")).header("User-Agent", IAS.USER_AGENT).header("Content-Type", "application/json").header("Accept", "application/json").timeout(IAS.TIMEOUT).POST(HttpRequest.BodyPublishers.ofString(payload)).build(), HttpResponse.BodyHandlers.ofString()).thenApplyAsync(response -> {
            try {
                int status = response.statusCode();
                if (status != 200) {
                    throw new IllegalArgumentException("Invalid status code: " + status);
                }
                JsonObject json = (JsonObject)GSONUtils.GSON.fromJson((String)response.body(), JsonObject.class);
                Objects.requireNonNull(json, "Response is null");
                return XHashedToken.fromJson(json);
            }
            catch (Throwable t) {
                Object message = "Unable to convert Microsoft Access (MSA) token to Xbox Live (XBL) token (" + String.valueOf(response) + " with " + String.valueOf(response.headers()) + "): " + (String)response.body();
                message = ((String)message).replace(authToken, "[MSA]");
                throw new RuntimeException((String)message, t);
            }
        }, (Executor)IAS.executor());
    }

    @NotNull
    public static CompletableFuture<XHashedToken> xblToXsts(@NotNull String xbl, @Nullable String hash) {
        JsonObject request = new JsonObject();
        JsonObject requestProperties = new JsonObject();
        JsonArray requestUserTokens = new JsonArray();
        requestUserTokens.add(xbl);
        requestProperties.add("UserTokens", (JsonElement)requestUserTokens);
        requestProperties.addProperty("SandboxId", "RETAIL");
        request.add("Properties", (JsonElement)requestProperties);
        request.addProperty("RelyingParty", "rp://api.minecraftservices.com/");
        request.addProperty("TokenType", "JWT");
        String payload = GSONUtils.GSON.toJson((JsonElement)request);
        return NetworkPolicy.sendAsync(CLIENT, HttpRequest.newBuilder().uri(URI.create("https://xsts.auth.xboxlive.com/xsts/authorize")).header("User-Agent", IAS.USER_AGENT).header("Content-Type", "application/json").header("Accept", "application/json").timeout(IAS.TIMEOUT).POST(HttpRequest.BodyPublishers.ofString(payload)).build(), HttpResponse.BodyHandlers.ofString()).thenApplyAsync(response -> {
            try {
                int status = response.statusCode();
                if (status == 401) {
                    try {
                        JsonObject json = (JsonObject)GSONUtils.GSON.fromJson((String)response.body(), JsonObject.class);
                        long err = GSONUtils.getLongOrThrow(json, "XErr");
                        if (err == 2148916233L) {
                            throw new FriendlyException("XErr from 401 status: 2148916233 (No Xbox linked)", "ias.error.noXbox");
                        }
                        if (err == 2148916235L) {
                            throw new FriendlyException("XErr from 401 status: 2148916235 (Xbox not available)", "ias.error.xboxAvailable");
                        }
                        if (err == 2148916236L || err == 2148916237L || err == 2148916238L) {
                            throw new FriendlyException("XErr from 401 status: " + err + " (Non-adult)", "ias.error.xboxAdult");
                        }
                        throw new RuntimeException("Unknown XErr from 401 status: " + err);
                    }
                    catch (Throwable t) {
                        throw new IllegalArgumentException("Invalid status code: 401", t);
                    }
                }
                if (status != 200) {
                    throw new IllegalArgumentException("Invalid status code: " + status);
                }
                JsonObject json = (JsonObject)GSONUtils.GSON.fromJson((String)response.body(), JsonObject.class);
                Objects.requireNonNull(json, "Response is null");
                XHashedToken token = XHashedToken.fromJson(json);
                if (hash != null && !hash.equals(token.hash())) {
                    throw new IllegalStateException("Mismatching XBL and XSTS user hashes.");
                }
                return token;
            }
            catch (Throwable t) {
                Object message = "Unable to convert Xbox Live (XBL) token to Xbox Secure Token Service (XSTS) token (" + String.valueOf(response) + " with " + String.valueOf(response.headers()) + "): " + (String)response.body();
                message = ((String)message).replace(xbl, "[XBL]");
                if (hash != null) {
                    message = ((String)message).replace(hash, "[HASH]");
                }
                throw new RuntimeException((String)message, t);
            }
        }, (Executor)IAS.executor());
    }

    @NotNull
    public static CompletableFuture<String> xstsToMca(@NotNull String xsts, @NotNull String hash) {
        JsonObject request = new JsonObject();
        request.addProperty("identityToken", "XBL3.0 x=" + hash + ";" + xsts);
        String payload = GSONUtils.GSON.toJson((JsonElement)request);
        return NetworkPolicy.sendAsync(CLIENT, HttpRequest.newBuilder().uri(URI.create("https://api.minecraftservices.com/authentication/login_with_xbox")).header("User-Agent", IAS.USER_AGENT).header("Content-Type", "application/json").header("Accept", "application/json").timeout(IAS.TIMEOUT).POST(HttpRequest.BodyPublishers.ofString(payload)).build(), HttpResponse.BodyHandlers.ofString()).thenApplyAsync(response -> {
            try {
                int status = response.statusCode();
                if (status != 200) {
                    throw new IllegalArgumentException("Invalid status code: " + status);
                }
                JsonObject json = (JsonObject)GSONUtils.GSON.fromJson((String)response.body(), JsonObject.class);
                Objects.requireNonNull(json, "Response is null");
                return GSONUtils.getStringOrThrow(json, "access_token");
            }
            catch (Throwable t) {
                Object message = "Unable to convert Xbox Secure Token Service (XSTS) token to Minecraft Access (MCA) token (" + String.valueOf(response) + " with " + String.valueOf(response.headers()) + "): " + (String)response.body();
                message = ((String)message).replace(xsts, "[XSTS]");
                message = ((String)message).replace(hash, "[HASH]");
                throw new RuntimeException((String)message, t);
            }
        }, (Executor)IAS.executor());
    }

    @NotNull
    public static CompletableFuture<MCProfile> mcaToMcp(@NotNull String access) {
        return NetworkPolicy.sendAsync(CLIENT, HttpRequest.newBuilder().uri(URI.create("https://api.minecraftservices.com/minecraft/profile")).header("User-Agent", IAS.USER_AGENT).header("Authorization", "Bearer " + access).timeout(IAS.TIMEOUT).GET().build(), HttpResponse.BodyHandlers.ofString()).thenApplyAsync(response -> {
            try {
                int status = response.statusCode();
                if (status == 404) {
                    throw new FriendlyException("Profile 404", "ias.error.noProfile");
                }
                if (status != 200) {
                    throw new IllegalArgumentException("Invalid status code: " + status);
                }
                JsonObject json = (JsonObject)GSONUtils.GSON.fromJson((String)response.body(), JsonObject.class);
                Objects.requireNonNull(json, "Response is null");
                return MCProfile.fromJson(json);
            }
            catch (Throwable t) {
                Object message = "Unable to convert Minecraft Access (MCA) token to Minecraft Profile (MCP) (" + String.valueOf(response) + " with " + String.valueOf(response.headers()) + "): " + (String)response.body();
                message = ((String)message).replace(access, "[MCA]");
                throw new RuntimeException((String)message, t);
            }
        }, (Executor)IAS.executor());
    }

    @NotNull
    public static CompletableFuture<MCProfile> nameToMcp(@NotNull String name) {
        return NetworkPolicy.sendAsync(CLIENT, HttpRequest.newBuilder().uri(URI.create("https://api.mojang.com/users/profiles/minecraft/" + URLEncoder.encode(name, StandardCharsets.UTF_8))).header("User-Agent", IAS.USER_AGENT).timeout(IAS.TIMEOUT).GET().build(), HttpResponse.BodyHandlers.ofString()).thenApplyAsync(response -> {
            try {
                int status = response.statusCode();
                if (status != 200) {
                    throw new IllegalArgumentException("Invalid status code: " + status);
                }
                JsonObject json = (JsonObject)GSONUtils.GSON.fromJson((String)response.body(), JsonObject.class);
                Objects.requireNonNull(json, "Response is null");
                return MCProfile.fromJson(json);
            }
            catch (Throwable t) {
                throw new RuntimeException("Unable to obtain Minecraft profile by name '" + name + "' (" + String.valueOf(response) + " with " + String.valueOf(response.headers()) + "): " + (String)response.body(), t);
            }
        }, (Executor)IAS.executor());
    }
}

