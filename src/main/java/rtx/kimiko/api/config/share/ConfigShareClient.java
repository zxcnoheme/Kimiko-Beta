/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.functions.Function0
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.config.share;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import recovery.privacy.NetworkPolicy;
import rtx.kimiko.api.config.share.ConfigCodec;
import rtx.kimiko.api.config.share.ConfigIdentity;
import rtx.kimiko.api.config.share.SharedConfig;
import rtx.kimiko.utils.net.Endpoints;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 72\u00020\u0001:\u000287B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J#\u0010\f\u001a\u00020\u000b2\u0014\u0010\n\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0018\u00010\u0007\u00a2\u0006\u0004\b\f\u0010\rJ#\u0010\u000e\u001a\u00020\u000b2\u0014\u0010\n\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0018\u00010\u0007\u00a2\u0006\u0004\b\u000e\u0010\rJ'\u0010\u0011\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0012J9\u0010\u0018\u001a\u00020\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007\u00a2\u0006\u0004\b\u0018\u0010\u0019JC\u0010\u001a\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007\u00a2\u0006\u0004\b\u001a\u0010\u001bJ'\u0010\u001c\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\u001c\u0010\u0012J\u0017\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ3\u0010#\u001a\u00020\u000b\"\u0004\b\u0000\u0010 2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00072\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000!H\u0002\u00a2\u0006\u0004\b#\u0010$J\u0017\u0010&\u001a\u00020\u00142\u0006\u0010%\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b&\u0010'J\u001f\u0010)\u001a\u00020\u00142\u0006\u0010%\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b)\u0010*J\u0017\u0010,\u001a\u00020+2\u0006\u0010%\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b,\u0010-J\u0017\u0010/\u001a\u00020\u00142\u0006\u0010.\u001a\u00020+H\u0002\u00a2\u0006\u0004\b/\u00100R\u0014\u00102\u001a\u0002018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0014\u00105\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00106\u00a8\u00069"}, d2={"Lrtx/kimiko/api/config/share/ConfigShareClient;", "", "<init>", "()V", "", "identified", "()Z", "Lrtx/kimiko/api/config/share/ConfigShareClient$Callback;", "", "Lrtx/kimiko/api/config/share/SharedConfig;", "callback", "", "mine", "(Lrtx/kimiko/api/config/share/ConfigShareClient$Callback;)V", "owned", "", "code", "fetch", "(Ljava/lang/String;Lrtx/kimiko/api/config/share/ConfigShareClient$Callback;)V", "name", "Lcom/google/gson/JsonObject;", "configRoot", "", "modules", "publish", "(Ljava/lang/String;Lcom/google/gson/JsonObject;ILrtx/kimiko/api/config/share/ConfigShareClient$Callback;)V", "update", "(Ljava/lang/String;Ljava/lang/String;Lcom/google/gson/JsonObject;ILrtx/kimiko/api/config/share/ConfigShareClient$Callback;)V", "delete", "uid", "identity", "(I)Lcom/google/gson/JsonObject;", "T", "Lkotlin/Function0;", "work", "run", "(Lrtx/kimiko/api/config/share/ConfigShareClient$Callback;Lkotlin/jvm/functions/Function0;)V", "path", "get", "(Ljava/lang/String;)Lcom/google/gson/JsonObject;", "body", "post", "(Ljava/lang/String;Lcom/google/gson/JsonObject;)Lcom/google/gson/JsonObject;", "Ljava/net/http/HttpRequest$Builder;", "request", "(Ljava/lang/String;)Ljava/net/http/HttpRequest$Builder;", "builder", "send", "(Ljava/net/http/HttpRequest$Builder;)Lcom/google/gson/JsonObject;", "Ljava/net/http/HttpClient;", "http", "Ljava/net/http/HttpClient;", "Ljava/util/concurrent/ExecutorService;", "executor", "Ljava/util/concurrent/ExecutorService;", "Companion", "Callback", "rtx.kimiko:kimiko"})
public final class ConfigShareClient {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final HttpClient http;
    @NotNull
    private final ExecutorService executor;
    @JvmField
    @NotNull
    public static final ConfigShareClient INSTANCE = new ConfigShareClient();
    @NotNull
    private static final Duration TIMEOUT;

    private ConfigShareClient() {
        HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(4L)).build();
        Intrinsics.checkNotNullExpressionValue((Object)httpClient, (String)"build(...)");
        this.http = httpClient;
        ExecutorService executorService = Executors.newSingleThreadExecutor(ConfigShareClient::executor$lambda$0);
        Intrinsics.checkNotNullExpressionValue((Object)executorService, (String)"newSingleThreadExecutor(...)");
        this.executor = executorService;
    }

    public final boolean identified() {
        return ConfigIdentity.uid() > 0;
    }

    public final void mine(@Nullable Callback<List<SharedConfig>> callback) {
        int uid = ConfigIdentity.uid();
        if (uid <= 0) {
            ConfigShareClient.Companion.reply(callback, new ArrayList(), null);
            return;
        }
        this.run(callback, () -> ConfigShareClient.mine$lambda$0(this, uid));
    }

    public final void owned(@Nullable Callback<List<SharedConfig>> callback) {
        int uid = ConfigIdentity.uid();
        if (uid <= 0) {
            ConfigShareClient.Companion.reply(callback, new ArrayList(), null);
            return;
        }
        this.run(callback, () -> ConfigShareClient.owned$lambda$0(this, uid));
    }

    public final void fetch(@Nullable String code, @Nullable Callback<SharedConfig> callback) {
        String normalized = ConfigCodec.normalizeCode(code);
        if (((CharSequence)normalized).length() == 0) {
            ConfigShareClient.Companion.reply(callback, null, "Пустой код");
            return;
        }
        int uid = ConfigIdentity.uid();
        this.run(callback, () -> ConfigShareClient.fetch$lambda$0(this, normalized, uid));
    }

    public final void publish(@Nullable String name, @Nullable JsonObject configRoot, int modules, @Nullable Callback<SharedConfig> callback) {
        String payload = ConfigCodec.encode(configRoot);
        if (payload == null) {
            ConfigShareClient.Companion.reply(callback, null, "Не удалось упаковать конфиг");
            return;
        }
        int uid = ConfigIdentity.uid();
        if (uid <= 0) {
            ConfigShareClient.Companion.reply(callback, null, "Нужен профиль Kimiko");
            return;
        }
        JsonObject body = this.identity(uid);
        body.addProperty("name", name);
        body.addProperty("payload", payload);
        body.addProperty("modules", (Number)modules);
        this.run(callback, () -> ConfigShareClient.publish$lambda$0(this, body));
    }

    public final void update(@Nullable String code, @Nullable String name, @Nullable JsonObject configRoot, int modules, @Nullable Callback<SharedConfig> callback) {
        String normalized = ConfigCodec.normalizeCode(code);
        String payload = ConfigCodec.encode(configRoot);
        if (((CharSequence)normalized).length() == 0 || payload == null) {
            ConfigShareClient.Companion.reply(callback, null, "Некорректный конфиг");
            return;
        }
        int uid = ConfigIdentity.uid();
        if (uid <= 0) {
            ConfigShareClient.Companion.reply(callback, null, "Нужен профиль Kimiko");
            return;
        }
        JsonObject body = this.identity(uid);
        body.addProperty("name", name);
        body.addProperty("payload", payload);
        body.addProperty("modules", (Number)modules);
        this.run(callback, () -> ConfigShareClient.update$lambda$0(this, normalized, body));
    }

    public final void delete(@Nullable String code, @Nullable Callback<Boolean> callback) {
        String normalized = ConfigCodec.normalizeCode(code);
        int uid = ConfigIdentity.uid();
        if (((CharSequence)normalized).length() == 0 || uid <= 0) {
            ConfigShareClient.Companion.reply(callback, false, "Некорректный код");
            return;
        }
        this.run(callback, () -> ConfigShareClient.delete$lambda$0(this, normalized, uid));
    }

    private final JsonObject identity(int uid) {
        JsonObject body = new JsonObject();
        body.addProperty("uid", (Number)uid);
        body.addProperty("user", ConfigIdentity.username());
        body.addProperty("avatar", ConfigIdentity.avatarUrl());
        return body;
    }

    private final <T> void run(Callback<T> callback, Function0<? extends T> work) {
        this.executor.submit(() -> ConfigShareClient.run$lambda$0(work, callback));
    }

    private final JsonObject get(String path) {
        HttpRequest.Builder builder = this.request(path).header("Accept", "application/json").GET();
        Intrinsics.checkNotNullExpressionValue((Object)builder, (String)"GET(...)");
        return this.send(builder);
    }

    private final JsonObject post(String path, JsonObject body) {
        HttpRequest.Builder builder = this.request(path).header("Content-Type", "application/json; charset=utf-8").header("Accept", "application/json").POST(HttpRequest.BodyPublishers.ofString(body.toString(), StandardCharsets.UTF_8));
        Intrinsics.checkNotNullExpressionValue((Object)builder, (String)"POST(...)");
        return this.send(builder);
    }

    private final HttpRequest.Builder request(String path) {
        HttpRequest.Builder builder = HttpRequest.newBuilder(URI.create(Endpoints.configs() + path)).timeout(TIMEOUT);
        String token = ConfigIdentity.token();
        if (((CharSequence)token).length() > 0) {
            builder.header("X-Kimiko-Auth", token);
        }
        Intrinsics.checkNotNull((Object)builder);
        return builder;
    }

    private final JsonObject send(HttpRequest.Builder builder) {
        HttpResponse<String> response;
        try {
            response = NetworkPolicy.send(this.http, builder.build(), HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        JsonObject root = null;
        try {
            JsonObject jsonObject;
            JsonElement parsed = JsonParser.parseString((String)response.body());
            if (parsed != null && parsed.isJsonObject()) {
                JsonObject jsonObject2 = parsed.getAsJsonObject();
                jsonObject = jsonObject2;
                Intrinsics.checkNotNullExpressionValue((Object)jsonObject2, (String)"getAsJsonObject(...)");
            } else {
                jsonObject = new JsonObject();
            }
            root = jsonObject;
        }
        catch (Exception ex) {
            root = new JsonObject();
        }
        if (response.statusCode() != 200 || !ConfigShareClient.Companion.bool(root)) {
            JsonElement error = root.get("error");
            throw new IllegalStateException(error != null && error.isJsonPrimitive() ? error.getAsString() : "Сервер недоступен");
        }
        return root;
    }

    private static final Thread executor$lambda$0(Runnable r) {
        Thread thread = new Thread(r, "kimiko-config-share");
        thread.setDaemon(true);
        return thread;
    }

    private static final List mine$lambda$0(ConfigShareClient this$0, int $uid) {
        JsonObject root = this$0.get("/api/configs?uid=" + $uid);
        return ConfigShareClient.Companion.list(root);
    }

    private static final List owned$lambda$0(ConfigShareClient this$0, int $uid) {
        JsonObject root = this$0.get("/api/configs/owned?uid=" + $uid);
        return ConfigShareClient.Companion.list(root);
    }

    private static final SharedConfig fetch$lambda$0(ConfigShareClient this$0, String $normalized, int $uid) {
        JsonObject root = this$0.get("/api/config/" + $normalized + (String)($uid > 0 ? "?uid=" + $uid : ""));
        SharedConfig sharedConfig = SharedConfig.Companion.parse(ConfigShareClient.Companion.object(root, "config"));
        if (sharedConfig == null) {
            throw new IllegalStateException("Конфиг не найден");
        }
        return sharedConfig;
    }

    private static final SharedConfig publish$lambda$0(ConfigShareClient this$0, JsonObject $body) {
        JsonObject root = this$0.post("/api/configs", $body);
        SharedConfig sharedConfig = SharedConfig.Companion.parse(ConfigShareClient.Companion.object(root, "config"));
        if (sharedConfig == null) {
            throw new IllegalStateException("Сервер не вернул код");
        }
        return sharedConfig;
    }

    private static final SharedConfig update$lambda$0(ConfigShareClient this$0, String $normalized, JsonObject $body) {
        JsonObject root = this$0.post("/api/config/" + $normalized, $body);
        SharedConfig sharedConfig = SharedConfig.Companion.parse(ConfigShareClient.Companion.object(root, "config"));
        if (sharedConfig == null) {
            throw new IllegalStateException("Не удалось обновить");
        }
        return sharedConfig;
    }

    private static final boolean delete$lambda$0(ConfigShareClient this$0, String $normalized, int $uid) {
        HttpRequest.Builder builder = this$0.request("/api/config/" + $normalized + "?uid=" + $uid).DELETE();
        Intrinsics.checkNotNullExpressionValue((Object)builder, (String)"DELETE(...)");
        this$0.send(builder);
        return true;
    }

    private static final void run$lambda$0(Function0 $work, Callback $callback) {
        Object value = null;
        String error = null;
        try {
            value = $work.invoke();
        }
        catch (Exception ex) {
            error = ConfigShareClient.Companion.friendly(ex);
        }
        ConfigShareClient.Companion.reply($callback, value, error);
    }

    static {
        Duration duration = Duration.ofSeconds(8L);
        Intrinsics.checkNotNullExpressionValue((Object)duration, (String)"ofSeconds(...)");
        TIMEOUT = duration;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u00e6\u0080\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J#\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00018\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H&\u00a2\u0006\u0004\b\u0007\u0010\b\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006\t\u00c0\u0006\u0001"}, d2={"Lrtx/kimiko/api/config/share/ConfigShareClient$Callback;", "T", "", "value", "", "error", "", "accept", "(Ljava/lang/Object;Ljava/lang/String;)V", "rtx.kimiko:kimiko"})
    public static interface Callback<T> {
        public void accept(@Nullable T var1, @Nullable String var2);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J9\u0010\u000b\u001a\u00020\n\"\u0004\b\u0000\u0010\u00042\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00018\u00002\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u0010\u001a\u00020\b2\n\u0010\u000f\u001a\u00060\rj\u0002`\u000eH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J!\u0010\u0018\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u001d\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u0013\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u0019\u0010 \u001a\u00020\u001e8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001f\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010$\u00a8\u0006%"}, d2={"Lrtx/kimiko/api/config/share/ConfigShareClient.Companion;", "", "<init>", "()V", "T", "Lrtx/kimiko/api/config/share/ConfigShareClient$Callback;", "callback", "value", "", "error", "", "reply", "(Lrtx/kimiko/api/config/share/ConfigShareClient$Callback;Ljava/lang/Object;Ljava/lang/String;)V", "Ljava/lang/Exception;", "Lkotlin/Exception;", "ex", "friendly", "(Ljava/lang/Exception;)Ljava/lang/String;", "Lcom/google/gson/JsonObject;", "root", "", "bool", "(Lcom/google/gson/JsonObject;)Z", "key", "object", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonObject;", "", "Lrtx/kimiko/api/config/share/SharedConfig;", "list", "(Lcom/google/gson/JsonObject;)Ljava/util/List;", "Lrtx/kimiko/api/config/share/ConfigShareClient;", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/config/share/ConfigShareClient;", "Ljava/time/Duration;", "TIMEOUT", "Ljava/time/Duration;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final <T> void reply(Callback<T> callback, T value, String error) {
            if (callback == null) {
                return;
            }
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient minecraft = minecraftClient2;
            minecraft.execute(() -> Companion.reply$lambda$0(callback, value, error));
        }

        private final String friendly(Exception ex) {
            String message = ex.getMessage();
            if (message == null || StringsKt.isBlank((CharSequence)message)) {
                return "Сервер недоступен";
            }
            return switch (message) {
                case "not found" -> "Конфиг не найден";
                case "not owner" -> "Это не ваш конфиг";
                case "not granted" -> "Конфиг вам не выдан";
                case "limit reached" -> "В облаке уже 5 конфигов — удалите лишний";
                case "bad payload" -> "Конфиг слишком большой";
                case "uid required" -> "Нужен профиль Kimiko";
                case "auth required", "bad token" -> "Требуется вход через лаунчер";
                default -> message;
            };
        }

        private final boolean bool(JsonObject root) {
            boolean bl;
            JsonElement value = root.get("ok");
            try {
                bl = value != null && value.isJsonPrimitive() && value.getAsBoolean();
            }
            catch (Exception ex) {
                bl = false;
            }
            return bl;
        }

        private final JsonObject object(JsonObject root, String key) {
            JsonElement value = root.get(key);
            return value != null && value.isJsonObject() ? value.getAsJsonObject() : null;
        }

        private final List<SharedConfig> list(JsonObject root) {
            ArrayList<SharedConfig> out = new ArrayList<SharedConfig>();
            JsonElement array = root.get("configs");
            if (array == null || !array.isJsonArray()) {
                return out;
            }
            Iterator iterator = array.getAsJsonArray().iterator();
            Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
            Iterator iterator2 = iterator;
            while (iterator2.hasNext()) {
                SharedConfig parsed;
                JsonElement element = (JsonElement)iterator2.next();
                if (!element.isJsonObject() || (parsed = SharedConfig.Companion.parse(element.getAsJsonObject())) == null) continue;
                out.add(parsed);
            }
            return out;
        }

        private static final void reply$lambda$0(Callback $callback, Object $value, String $error) {
            $callback.accept($value, $error);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

