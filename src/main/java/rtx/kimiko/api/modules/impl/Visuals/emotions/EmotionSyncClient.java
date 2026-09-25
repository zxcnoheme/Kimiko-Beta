/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.emotions;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Visuals.emotions.Emotion;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionRemoteState;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 :2\u00020\u0001:\u0002;:B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003JM\u0010\u0011\u001a\u00020\u00102\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0018\u0010\u0003J\r\u0010\u0019\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\u000e\u00a2\u0006\u0004\b\u001b\u0010\u001aJ\u0019\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0\u001c\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\r\u0010 \u001a\u00020\u0010\u00a2\u0006\u0004\b \u0010\u0003J\u0017\u0010\"\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\"\u0010#R\u0014\u0010%\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010(\u001a\u00020'8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b(\u0010)R \u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b+\u0010,R\u0018\u0010/\u001a\u00060-j\u0002`.8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0018\u00102\u001a\u0004\u0018\u0001018\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0016\u00104\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0005\u00106R\u0016\u0010\u0006\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0006\u00106R\u0016\u0010\u0007\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0007\u00106R\u0018\u0010\t\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\t\u00107R\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000b\u00108R\u0016\u0010\r\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\r\u00109R\u0016\u0010\u000f\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u00105\u00a8\u0006<"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionSyncClient;", "", "<init>", "()V", "", "identity", "username", "world", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;", "emotion", "", "startedAt", "", "speed", "", "looping", "", "setLocalState", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;JFZ)V", "host", "", "port", "connect", "(Ljava/lang/String;I)V", "disconnect", "isConnected", "()Z", "isConnecting", "", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionRemoteState;", "snapshot", "()Ljava/util/Map;", "push", "raw", "receive", "(Ljava/lang/String;)V", "Lcom/google/gson/Gson;", "gson", "Lcom/google/gson/Gson;", "Ljava/net/http/HttpClient;", "http", "Ljava/net/http/HttpClient;", "", "remote", "Ljava/util/Map;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "buffer", "Ljava/lang/StringBuilder;", "Ljava/net/http/WebSocket;", "socket", "Ljava/net/http/WebSocket;", "connecting", "Z", "Ljava/lang/String;", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;", "J", "F", "Companion", "Listener", "rtx.kimiko:kimiko"})
public final class EmotionSyncClient {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Gson gson = new Gson();
    @NotNull
    private final HttpClient http;
    @NotNull
    private final Map<String, EmotionRemoteState> remote;
    @NotNull
    private final StringBuilder buffer;
    @Nullable
    private volatile WebSocket socket;
    private volatile boolean connecting;
    @NotNull
    private volatile String identity;
    @NotNull
    private volatile String username;
    @NotNull
    private volatile String world;
    @Nullable
    private volatile Emotion emotion;
    private volatile long startedAt;
    private volatile float speed;
    private volatile boolean looping;

    public EmotionSyncClient() {
        HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(6L)).build();
        Intrinsics.checkNotNullExpressionValue((Object)httpClient, (String)"build(...)");
        this.http = httpClient;
        this.remote = new ConcurrentHashMap();
        this.buffer = new StringBuilder();
        this.identity = "";
        this.username = "";
        this.world = "";
        this.speed = 1.0f;
    }

    public final void setLocalState(@Nullable String identity, @Nullable String username, @Nullable String world, @Nullable Emotion emotion, long startedAt, float speed, boolean looping) {
        this.username = EmotionSyncClient.Companion.clean(username, 32);
        this.identity = EmotionSyncClient.Companion.clean(identity, 96);
        this.world = EmotionSyncClient.Companion.clean(world, 64);
        this.emotion = emotion;
        this.startedAt = startedAt;
        this.speed = Math.max(0.05f, speed);
        this.looping = looping;
    }

    public final void connect(@NotNull String string, int n) {
    }

    public final void disconnect() {
        this.remote.clear();
        WebSocket current = this.socket;
        this.socket = null;
        this.connecting = false;
        if (current != null) {
            try {
                current.sendClose(1000, "bye");
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public final boolean isConnected() {
        return this.socket != null;
    }

    public final boolean isConnecting() {
        return this.connecting;
    }

    @NotNull
    public final Map<String, EmotionRemoteState> snapshot() {
        return new HashMap<String, EmotionRemoteState>(this.remote);
    }

    public final void push() {
        WebSocket current = this.socket;
        if (current == null || StringsKt.isBlank((CharSequence)this.identity)) {
            return;
        }
        JsonObject p = new JsonObject();
        p.addProperty("t", "u");
        p.addProperty("i", this.identity);
        p.addProperty("n", this.username);
        p.addProperty("w", this.world);
        p.addProperty("e", this.emotion != null ? this.emotion.name() : "");
        p.addProperty("s", (Number)this.startedAt);
        p.addProperty("v", (Number)Float.valueOf(this.speed));
        p.addProperty("l", Boolean.valueOf(this.looping));
        current.sendText(this.gson.toJson((JsonElement)p), true);
    }

    private final void receive(String raw) {
        try {
            JsonElement item;
            Iterator iterator;
            JsonObject packet = JsonParser.parseString((String)raw).getAsJsonObject();
            Intrinsics.checkNotNull((Object)packet);
            if (!Intrinsics.areEqual((Object)"d", (Object)EmotionSyncClient.Companion.read(packet, "t"))) {
                return;
            }
            if (packet.has("p") && packet.get("p").isJsonArray()) {
                Iterator iterator2 = packet.getAsJsonArray("p").iterator();
                Intrinsics.checkNotNullExpressionValue((Object)iterator2, (String)"iterator(...)");
                iterator = iterator2;
                while (iterator.hasNext()) {
                    Emotion emotion;
                    item = (JsonElement)iterator.next();
                    if (!item.isJsonObject()) continue;
                    JsonObject p = item.getAsJsonObject();
                    Intrinsics.checkNotNull((Object)p);
                    String id = EmotionSyncClient.Companion.clean(EmotionSyncClient.Companion.read(p, "i"), 96);
                    String name = EmotionSyncClient.Companion.clean(EmotionSyncClient.Companion.read(p, "n"), 32);
                    try {
                        emotion = Emotion.valueOf(EmotionSyncClient.Companion.read(p, "e"));
                    }
                    catch (Exception ignored) {
                        continue;
                    }
                    Emotion emotion2 = emotion;
                    if (!(!StringsKt.isBlank((CharSequence)id)) || !(!StringsKt.isBlank((CharSequence)name))) continue;
                    this.remote.put(id, new EmotionRemoteState(id, name, EmotionSyncClient.Companion.clean(EmotionSyncClient.Companion.read(p, "w"), 64), emotion2, EmotionSyncClient.Companion.number(p, "s", 0L), (float)EmotionSyncClient.Companion.decimal(p, "v", 1.0), EmotionSyncClient.Companion.bool(p, "l")));
                }
            }
            if (packet.has("r") && packet.get("r").isJsonArray()) {
                Iterator iterator3 = packet.getAsJsonArray("r").iterator();
                Intrinsics.checkNotNullExpressionValue((Object)iterator3, (String)"iterator(...)");
                iterator = iterator3;
                while (iterator.hasNext()) {
                    item = (JsonElement)iterator.next();
                    if (!item.isJsonPrimitive()) continue;
                    this.remote.remove(EmotionSyncClient.Companion.clean(item.getAsString(), 96));
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private static final Unit connect$lambda$0(EmotionSyncClient this$0, WebSocket ws, Throwable error) {
        if (error != null) {
            this$0.connecting = false;
            this$0.socket = null;
            this$0.remote.clear();
        }
        return Unit.INSTANCE;
    }

    private static final void connect$lambda$1(Function2 $tmp0, Object p0, Object p1) {
        $tmp0.invoke(p0, p1);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ'\u0010\f\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\f\u0010\rJ'\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J!\u0010\u0017\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0016\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018\u00a8\u0006\u0019"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionSyncClient.Companion;", "", "<init>", "()V", "Lcom/google/gson/JsonObject;", "objectObj", "", "key", "read", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/String;", "", "fallback", "number", "(Lcom/google/gson/JsonObject;Ljava/lang/String;J)J", "", "decimal", "(Lcom/google/gson/JsonObject;Ljava/lang/String;D)D", "", "bool", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Z", "value", "", "max", "clean", "(Ljava/lang/String;I)Ljava/lang/String;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final String read(JsonObject objectObj, String key) {
            String string;
            if (objectObj.has(key) && objectObj.get(key).isJsonPrimitive()) {
                String string2 = objectObj.get(key).getAsString();
                string = string2;
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getAsString(...)");
            } else {
                string = "";
            }
            return string;
        }

        private final long number(JsonObject objectObj, String key, long fallback) {
            long l;
            try {
                l = objectObj.has(key) ? objectObj.get(key).getAsLong() : fallback;
            }
            catch (Exception ignored) {
                l = fallback;
            }
            return l;
        }

        private final double decimal(JsonObject objectObj, String key, double fallback) {
            double d;
            try {
                d = objectObj.has(key) ? objectObj.get(key).getAsDouble() : fallback;
            }
            catch (Exception ignored) {
                d = fallback;
            }
            return d;
        }

        private final boolean bool(JsonObject objectObj, String key) {
            return objectObj.has(key) && objectObj.get(key).getAsBoolean();
        }

        private final String clean(String value, int max) {
            if (value == null) {
                return "";
            }
            String trimmed = ((Object)StringsKt.trim((CharSequence)String.valueOf(value).replace("\u00a7", ""))).toString();
            String string = trimmed.substring(0, Math.min(max, trimmed.length()));
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            return string;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ-\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ-\u0010\u0014\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0016H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionSyncClient$Listener;", "Ljava/net/http/WebSocket$Listener;", "<init>", "(Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionSyncClient;)V", "Ljava/net/http/WebSocket;", "ws", "", "onOpen", "(Ljava/net/http/WebSocket;)V", "", "data", "", "last", "Ljava/util/concurrent/CompletionStage;", "onText", "(Ljava/net/http/WebSocket;Ljava/lang/CharSequence;Z)Ljava/util/concurrent/CompletionStage;", "", "status", "", "reason", "onClose", "(Ljava/net/http/WebSocket;ILjava/lang/String;)Ljava/util/concurrent/CompletionStage;", "", "error", "onError", "(Ljava/net/http/WebSocket;Ljava/lang/Throwable;)V", "rtx.kimiko:kimiko"})
    private final class Listener
    implements WebSocket.Listener {
        @Override
        public void onOpen(@NotNull WebSocket ws) {
            Intrinsics.checkNotNullParameter((Object)ws, (String)"ws");
            EmotionSyncClient.this.socket = ws;
            EmotionSyncClient.this.connecting = false;
            ws.request(1L);
            EmotionSyncClient.this.push();
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        @Nullable
        public CompletionStage<?> onText(@NotNull WebSocket ws, @NotNull CharSequence data, boolean last) {
            Intrinsics.checkNotNullParameter((Object)ws, (String)"ws");
            Intrinsics.checkNotNullParameter((Object)data, (String)"data");
            StringBuilder stringBuilder = EmotionSyncClient.this.buffer;
            EmotionSyncClient emotionSyncClient = EmotionSyncClient.this;
            StringBuilder stringBuilder2 = stringBuilder;
            synchronized (stringBuilder2) {
                boolean bl = false;
                emotionSyncClient.buffer.append(data);
                if (last) {
                    String string = emotionSyncClient.buffer.toString();
                    Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
                    emotionSyncClient.receive(string);
                    emotionSyncClient.buffer.setLength(0);
                }
                Unit unit = Unit.INSTANCE;
            }
            ws.request(1L);
            return null;
        }

        @Override
        @Nullable
        public CompletionStage<?> onClose(@NotNull WebSocket ws, int status, @NotNull String reason) {
            Intrinsics.checkNotNullParameter((Object)ws, (String)"ws");
            Intrinsics.checkNotNullParameter((Object)reason, (String)"reason");
            if (Intrinsics.areEqual((Object)EmotionSyncClient.this.socket, (Object)ws)) {
                EmotionSyncClient.this.socket = null;
            }
            EmotionSyncClient.this.connecting = false;
            EmotionSyncClient.this.remote.clear();
            return WebSocket.Listener.super.onClose(ws, status, reason);
        }

        @Override
        public void onError(@NotNull WebSocket ws, @NotNull Throwable error) {
            Intrinsics.checkNotNullParameter((Object)ws, (String)"ws");
            Intrinsics.checkNotNullParameter((Object)error, (String)"error");
            if (Intrinsics.areEqual((Object)EmotionSyncClient.this.socket, (Object)ws)) {
                EmotionSyncClient.this.socket = null;
            }
            EmotionSyncClient.this.connecting = false;
            EmotionSyncClient.this.remote.clear();
        }
    }
}

