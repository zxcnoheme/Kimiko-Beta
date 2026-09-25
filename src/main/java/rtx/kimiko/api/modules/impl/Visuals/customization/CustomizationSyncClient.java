/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.customization;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.time.Duration;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0002UVB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J3\u0010\u000b\u001a\u00020\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0010\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0017\u0010\u0016J\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\n\u00a2\u0006\u0004\b\u001b\u0010\u0003J\u0017\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u0014J\u0019\u0010\u001f\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u001f\u0010\u0014J\u0017\u0010\"\u001a\u00020\n2\u0006\u0010!\u001a\u00020 H\u0002\u00a2\u0006\u0004\b\"\u0010#J\u001f\u0010'\u001a\u00020 2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b'\u0010(J\u001f\u0010)\u001a\u00020\u00042\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b)\u0010*J\u001f\u0010+\u001a\u00020\u00072\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b+\u0010,J\u0019\u0010.\u001a\u00020\u00042\b\u0010-\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b.\u0010/J\u0019\u00100\u001a\u00020\u00042\b\u0010-\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b0\u0010/J\u0019\u00101\u001a\u00020\u00042\b\u0010-\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b1\u0010/J\u0019\u00102\u001a\u00020\u00042\b\u0010-\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b2\u0010/J!\u00104\u001a\u00020\u00042\b\u0010-\u001a\u0004\u0018\u00010\u00042\u0006\u00103\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b4\u00105J\u001d\u00107\u001a\u00020\u0007*\u00020\u00042\b\u00106\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b7\u00108R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010=\u001a\u00020<8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u0010>R\u0014\u0010@\u001a\u00020?8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b@\u0010AR\u0014\u0010B\u001a\u00020?8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010AR\u0018\u0010E\u001a\u00060Cj\u0002`D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010FR \u0010H\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00180G8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u0010IR\u0018\u0010K\u001a\u0004\u0018\u00010J8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010LR\u0016\u0010M\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bM\u0010NR\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0005\u0010OR\u0016\u0010\u0006\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0006\u0010OR\u0016\u0010\t\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\t\u0010OR\u0016\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010NR \u0010Q\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040P8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bQ\u0010RR\u0014\u0010S\u001a\u00020\u000e8\u0002X\u0082D\u00a2\u0006\u0006\n\u0004\bS\u0010T\u00a8\u0006W"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/CustomizationSyncClient;", "", "<init>", "()V", "", "username", "headAccessory", "", "wings", "bodyModel", "", "setLocalState", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "host", "", "port", "connect", "(Ljava/lang/String;I)V", "reason", "disconnect", "(Ljava/lang/String;)V", "isConnected", "()Z", "isConnecting", "Lrtx/kimiko/api/modules/impl/Visuals/customization/CustomizationSyncClient$RemoteState;", "getRemoteState", "(Ljava/lang/String;)Lrtx/kimiko/api/modules/impl/Visuals/customization/CustomizationSyncClient$RemoteState;", "pushState", "type", "sendPacket", "rawPacket", "handlePacket", "Lcom/google/gson/JsonArray;", "states", "applyStates", "(Lcom/google/gson/JsonArray;)V", "Lcom/google/gson/JsonObject;", "objectObj", "key", "readArray", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonArray;", "readString", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/String;", "readBoolean", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Z", "value", "normalizeName", "(Ljava/lang/String;)Ljava/lang/String;", "normalizeKey", "normalizeAccessory", "normalizeBodyModel", "maxLength", "limit", "(Ljava/lang/String;I)Ljava/lang/String;", "other", "equalsIgnoreCase", "(Ljava/lang/String;Ljava/lang/String;)Z", "Lcom/google/gson/Gson;", "gson", "Lcom/google/gson/Gson;", "Ljava/net/http/HttpClient;", "httpClient", "Ljava/net/http/HttpClient;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "connected", "Ljava/util/concurrent/atomic/AtomicBoolean;", "connecting", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "packetBuffer", "Ljava/lang/StringBuilder;", "Ljava/util/concurrent/ConcurrentMap;", "remoteStates", "Ljava/util/concurrent/ConcurrentMap;", "Ljava/net/http/WebSocket;", "socket", "Ljava/net/http/WebSocket;", "manualClose", "Z", "Ljava/lang/String;", "Ljava/util/concurrent/ConcurrentHashMap;", "keyCache", "Ljava/util/concurrent/ConcurrentHashMap;", "KEY_CACHE_LIMIT", "I", "RemoteState", "SocketListener", "rtx.kimiko:kimiko"})
public final class CustomizationSyncClient {
    @NotNull
    private final Gson gson = new Gson();
    @NotNull
    private final HttpClient httpClient;
    @NotNull
    private final AtomicBoolean connected;
    @NotNull
    private final AtomicBoolean connecting;
    @NotNull
    private final StringBuilder packetBuffer;
    @NotNull
    private final ConcurrentMap<String, RemoteState> remoteStates;
    @Nullable
    private volatile WebSocket socket;
    private volatile boolean manualClose;
    @NotNull
    private volatile String username;
    @NotNull
    private volatile String headAccessory;
    @NotNull
    private volatile String bodyModel;
    private volatile boolean wings;
    @NotNull
    private final ConcurrentHashMap<String, String> keyCache;
    private final int KEY_CACHE_LIMIT;

    public CustomizationSyncClient() {
        HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(6L)).build();
        Intrinsics.checkNotNullExpressionValue((Object)httpClient, (String)"build(...)");
        this.httpClient = httpClient;
        this.connected = new AtomicBoolean();
        this.connecting = new AtomicBoolean();
        this.packetBuffer = new StringBuilder();
        this.remoteStates = new ConcurrentHashMap();
        this.username = "";
        this.headAccessory = "crown";
        this.bodyModel = "royal";
        this.keyCache = new ConcurrentHashMap();
        this.KEY_CACHE_LIMIT = 512;
    }

    public final void setLocalState(@Nullable String username, @Nullable String headAccessory, boolean wings, @Nullable String bodyModel) {
        this.username = this.normalizeName(username);
        this.headAccessory = this.normalizeAccessory(headAccessory);
        this.wings = wings;
        this.bodyModel = wings ? this.normalizeBodyModel(bodyModel) : "none";
    }

    public final synchronized void connect(@NotNull String string, int n) {
    }

    public final synchronized void disconnect(@NotNull String reason) {
        Intrinsics.checkNotNullParameter((Object)reason, (String)"reason");
        this.manualClose = true;
        this.connecting.set(false);
        this.connected.set(false);
        this.remoteStates.clear();
        WebSocket current = this.socket;
        this.socket = null;
        if (current != null) {
            try {
                current.sendClose(1000, this.limit(reason, 120));
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public final boolean isConnected() {
        return this.connected.get();
    }

    public final boolean isConnecting() {
        return this.connecting.get();
    }

    @Nullable
    public final RemoteState getRemoteState(@Nullable String username) {
        String string = username;
        if (string == null) {
            return (RemoteState)this.remoteStates.get(this.normalizeKey(null));
        }
        String raw = string;
        if (this.keyCache.size() > this.KEY_CACHE_LIMIT) {
            this.keyCache.clear();
        }
        return (RemoteState)this.remoteStates.get(this.keyCache.computeIfAbsent(raw, this::normalizeKey));
    }

    public final void pushState() {
        this.sendPacket("u");
    }

    private final void sendPacket(String type) {
        WebSocket current = this.socket;
        if (!this.connected.get() || current == null || StringsKt.isBlank((CharSequence)this.username)) {
            return;
        }
        JsonObject packet = new JsonObject();
        packet.addProperty("t", type);
        packet.addProperty("n", this.username);
        packet.addProperty("c", Boolean.valueOf(Intrinsics.areEqual((Object)"crown", (Object)this.headAccessory)));
        packet.addProperty("a", this.headAccessory);
        packet.addProperty("w", Boolean.valueOf(this.wings));
        packet.addProperty("b", this.bodyModel);
        current.sendText(this.gson.toJson((JsonElement)packet), true);
    }

    private final void handlePacket(String rawPacket) {
        if (rawPacket == null || StringsKt.isBlank((CharSequence)rawPacket)) {
            return;
        }
        try {
            JsonObject packet = JsonParser.parseString((String)rawPacket).getAsJsonObject();
            Intrinsics.checkNotNull((Object)packet);
            String type = this.readString(packet, "t");
            if (Intrinsics.areEqual((Object)"s", (Object)type)) {
                this.remoteStates.clear();
                this.applyStates(this.readArray(packet, "p"));
            } else if (Intrinsics.areEqual((Object)"d", (Object)type)) {
                this.applyStates(this.readArray(packet, "p"));
                Iterator iterator = this.readArray(packet, "r").iterator();
                Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
                Iterator iterator2 = iterator;
                while (iterator2.hasNext()) {
                    JsonElement element = (JsonElement)iterator2.next();
                    if (!element.isJsonPrimitive()) continue;
                    this.remoteStates.remove(this.normalizeKey(element.getAsString()));
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private final void applyStates(JsonArray states) {
        String localKey = this.normalizeKey(this.username);
        Iterator iterator = states.iterator();
        Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
        Iterator iterator2 = iterator;
        while (iterator2.hasNext()) {
            JsonElement element = (JsonElement)iterator2.next();
            if (!element.isJsonObject()) continue;
            JsonObject state = element.getAsJsonObject();
            Intrinsics.checkNotNull((Object)state);
            String name = this.normalizeName(this.readString(state, "n"));
            String key = this.normalizeKey(name);
            if (StringsKt.isBlank((CharSequence)key) || Intrinsics.areEqual((Object)key, (Object)localKey)) continue;
            String accessory = this.normalizeAccessory(this.readString(state, "a"));
            if (!state.has("a") && this.readBoolean(state, "c")) {
                accessory = "crown";
            }
            boolean wingsState = this.readBoolean(state, "w");
            String body = state.has("b") ? this.normalizeBodyModel(this.readString(state, "b")) : "royal";
            ((Map)this.remoteStates).put(key, new RemoteState(name, accessory, wingsState, wingsState ? body : "none"));
        }
    }

    private final JsonArray readArray(JsonObject objectObj, String key) {
        JsonArray jsonArray;
        if (objectObj.has(key) && objectObj.get(key).isJsonArray()) {
            JsonArray jsonArray2 = objectObj.getAsJsonArray(key);
            jsonArray = jsonArray2;
            Intrinsics.checkNotNullExpressionValue((Object)jsonArray2, (String)"getAsJsonArray(...)");
        } else {
            jsonArray = new JsonArray();
        }
        return jsonArray;
    }

    private final String readString(JsonObject objectObj, String key) {
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

    private final boolean readBoolean(JsonObject objectObj, String key) {
        return objectObj.has(key) && objectObj.get(key).isJsonPrimitive() && objectObj.get(key).getAsBoolean();
    }

    private final String normalizeName(String value) {
        String string = value;
        if (string == null || (string = ((Object)StringsKt.trim((CharSequence)string)).toString()) == null) {
            string = "";
        }
        return this.limit(string, 32);
    }

    private final String normalizeKey(String value) {
        String string = this.normalizeName(value);
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string2 = string.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
        return string2;
    }

    private final String normalizeAccessory(String value) {
        if (this.equalsIgnoreCase("crown", value)) {
            return "crown";
        }
        if (this.equalsIgnoreCase("hat", value)) {
            return "hat";
        }
        return this.equalsIgnoreCase("halo", value) ? "halo" : "none";
    }

    private final String normalizeBodyModel(String value) {
        if (this.equalsIgnoreCase("kagune", value)) {
            return "kagune";
        }
        if (this.equalsIgnoreCase("seraph", value)) {
            return "seraph";
        }
        return this.equalsIgnoreCase("feathered", value) ? "feathered" : "royal";
    }

    private final String limit(String value, int maxLength) {
        String string;
        if (value == null) {
            return "";
        }
        if (value.length() > maxLength) {
            String string2 = value.substring(0, maxLength);
            string = string2;
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
        } else {
            string = value;
        }
        return string;
    }

    private final boolean equalsIgnoreCase(String $this$equalsIgnoreCase, String other) {
        return StringsKt.equals((String)$this$equalsIgnoreCase, (String)other, (boolean)true);
    }

    private static final Unit connect$lambda$0(CustomizationSyncClient this$0, WebSocket webSocket, Throwable error) {
        if (error != null) {
            this$0.connecting.set(false);
            this$0.connected.set(false);
            this$0.remoteStates.clear();
        }
        return Unit.INSTANCE;
    }

    private static final void connect$lambda$1(Function2 $tmp0, Object p0, Object p1) {
        $tmp0.invoke(p0, p1);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0005\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0005\u00a2\u0006\u0004\b\f\u0010\u000bJ\r\u0010\r\u001a\u00020\u0005\u00a2\u0006\u0004\b\r\u0010\u000bJ\u0010\u0010\u000e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u000bJ\u0010\u0010\u0012\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u000fJ8\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u001b\u0010\u0016\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0019\u001a\u00020\u0018H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0011\u0010\u001b\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001b\u0010\u000fR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u0003\u0010\u000fR%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001e\u001a\u0004\b\u0004\u0010\u000fR%\u0010\u0006\u001a\u00020\u00058\u0007z\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b\u0006\u0010\u000bR%\u0010\u0007\u001a\u00020\u00028\u0007z\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b\u0007\u0010\u000f\u00a8\u0006 "}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/CustomizationSyncClient$RemoteState;", "", "", "username", "headAccessory", "", "wings", "bodyModel", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "crown", "()Z", "hat", "halo", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)Lrtx/kimiko/api/modules/impl/Visuals/customization/CustomizationSyncClient$RemoteState;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Lkotlin/jvm/JvmName;", "name", "Ljava/lang/String;", "Z", "rtx.kimiko:kimiko"})
    public static final class RemoteState {
        @NotNull
        private final String username;
        @NotNull
        private final String headAccessory;
        private final boolean wings;
        @NotNull
        private final String bodyModel;

        public RemoteState(@NotNull String username, @NotNull String headAccessory, boolean wings, @NotNull String bodyModel) {
            Intrinsics.checkNotNullParameter((Object)username, (String)"username");
            Intrinsics.checkNotNullParameter((Object)headAccessory, (String)"headAccessory");
            Intrinsics.checkNotNullParameter((Object)bodyModel, (String)"bodyModel");
            this.username = username;
            this.headAccessory = headAccessory;
            this.wings = wings;
            this.bodyModel = bodyModel;
        }

        @JvmName(name="username")
        @NotNull
        public final String username() {
            return this.username;
        }

        @JvmName(name="headAccessory")
        @NotNull
        public final String headAccessory() {
            return this.headAccessory;
        }

        @JvmName(name="wings")
        public final boolean wings() {
            return this.wings;
        }

        @JvmName(name="bodyModel")
        @NotNull
        public final String bodyModel() {
            return this.bodyModel;
        }

        public final boolean crown() {
            return Intrinsics.areEqual((Object)"crown", (Object)this.headAccessory);
        }

        public final boolean hat() {
            return Intrinsics.areEqual((Object)"hat", (Object)this.headAccessory);
        }

        public final boolean halo() {
            return Intrinsics.areEqual((Object)"halo", (Object)this.headAccessory);
        }

        @NotNull
        public final String component1() {
            return this.username;
        }

        @NotNull
        public final String component2() {
            return this.headAccessory;
        }

        public final boolean component3() {
            return this.wings;
        }

        @NotNull
        public final String component4() {
            return this.bodyModel;
        }

        @NotNull
        public final RemoteState copy(@NotNull String username, @NotNull String headAccessory, boolean wings, @NotNull String bodyModel) {
            Intrinsics.checkNotNullParameter((Object)username, (String)"username");
            Intrinsics.checkNotNullParameter((Object)headAccessory, (String)"headAccessory");
            Intrinsics.checkNotNullParameter((Object)bodyModel, (String)"bodyModel");
            return new RemoteState(username, headAccessory, wings, bodyModel);
        }

        public static /* synthetic */ RemoteState copy$default(RemoteState remoteState, String string, String string2, boolean bl, String string3, int n, Object object) {
            if ((n & 1) != 0) {
                string = remoteState.username;
            }
            if ((n & 2) != 0) {
                string2 = remoteState.headAccessory;
            }
            if ((n & 4) != 0) {
                bl = remoteState.wings;
            }
            if ((n & 8) != 0) {
                string3 = remoteState.bodyModel;
            }
            return remoteState.copy(string, string2, bl, string3);
        }

        @NotNull
        public String toString() {
            return "RemoteState(username=" + this.username + ", headAccessory=" + this.headAccessory + ", wings=" + this.wings + ", bodyModel=" + this.bodyModel + ")";
        }

        public int hashCode() {
            int result = this.username.hashCode();
            result = result * 31 + this.headAccessory.hashCode();
            result = result * 31 + Boolean.hashCode(this.wings);
            result = result * 31 + this.bodyModel.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RemoteState)) {
                return false;
            }
            RemoteState remoteState = (RemoteState)other;
            if (!Intrinsics.areEqual((Object)this.username, (Object)remoteState.username)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.headAccessory, (Object)remoteState.headAccessory)) {
                return false;
            }
            if (this.wings != remoteState.wings) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.bodyModel, (Object)remoteState.bodyModel);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ-\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ-\u0010\u0014\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0016H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/customization/CustomizationSyncClient$SocketListener;", "Ljava/net/http/WebSocket$Listener;", "<init>", "(Lrtx/kimiko/api/modules/impl/Visuals/customization/CustomizationSyncClient;)V", "Ljava/net/http/WebSocket;", "webSocket", "", "onOpen", "(Ljava/net/http/WebSocket;)V", "", "data", "", "last", "Ljava/util/concurrent/CompletionStage;", "onText", "(Ljava/net/http/WebSocket;Ljava/lang/CharSequence;Z)Ljava/util/concurrent/CompletionStage;", "", "statusCode", "", "reason", "onClose", "(Ljava/net/http/WebSocket;ILjava/lang/String;)Ljava/util/concurrent/CompletionStage;", "", "error", "onError", "(Ljava/net/http/WebSocket;Ljava/lang/Throwable;)V", "rtx.kimiko:kimiko"})
    private final class SocketListener
    implements WebSocket.Listener {
        @Override
        public void onOpen(@NotNull WebSocket webSocket) {
            Intrinsics.checkNotNullParameter((Object)webSocket, (String)"webSocket");
            if (CustomizationSyncClient.this.manualClose) {
                webSocket.sendClose(1000, "disabled");
                return;
            }
            CustomizationSyncClient.this.socket = webSocket;
            CustomizationSyncClient.this.connecting.set(false);
            CustomizationSyncClient.this.connected.set(true);
            webSocket.request(1L);
            CustomizationSyncClient.this.sendPacket("h");
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        @Nullable
        public CompletionStage<?> onText(@NotNull WebSocket webSocket, @NotNull CharSequence data, boolean last) {
            Intrinsics.checkNotNullParameter((Object)webSocket, (String)"webSocket");
            Intrinsics.checkNotNullParameter((Object)data, (String)"data");
            StringBuilder stringBuilder = CustomizationSyncClient.this.packetBuffer;
            CustomizationSyncClient customizationSyncClient = CustomizationSyncClient.this;
            StringBuilder stringBuilder2 = stringBuilder;
            synchronized (stringBuilder2) {
                boolean bl = false;
                customizationSyncClient.packetBuffer.append(data);
                if (last) {
                    String string = customizationSyncClient.packetBuffer.toString();
                    Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
                    String packet = string;
                    customizationSyncClient.packetBuffer.setLength(0);
                    customizationSyncClient.handlePacket(packet);
                }
                Unit unit = Unit.INSTANCE;
            }
            webSocket.request(1L);
            return null;
        }

        @Override
        @Nullable
        public CompletionStage<?> onClose(@NotNull WebSocket webSocket, int statusCode, @NotNull String reason) {
            Intrinsics.checkNotNullParameter((Object)webSocket, (String)"webSocket");
            Intrinsics.checkNotNullParameter((Object)reason, (String)"reason");
            if (Intrinsics.areEqual((Object)CustomizationSyncClient.this.socket, (Object)webSocket)) {
                CustomizationSyncClient.this.socket = null;
                CustomizationSyncClient.this.connected.set(false);
                CustomizationSyncClient.this.remoteStates.clear();
            }
            CustomizationSyncClient.this.connecting.set(false);
            return WebSocket.Listener.super.onClose(webSocket, statusCode, reason);
        }

        @Override
        public void onError(@NotNull WebSocket webSocket, @NotNull Throwable error) {
            Intrinsics.checkNotNullParameter((Object)webSocket, (String)"webSocket");
            Intrinsics.checkNotNullParameter((Object)error, (String)"error");
            if (Intrinsics.areEqual((Object)CustomizationSyncClient.this.socket, (Object)webSocket) || CustomizationSyncClient.this.socket == null) {
                CustomizationSyncClient.this.socket = null;
                CustomizationSyncClient.this.connected.set(false);
                CustomizationSyncClient.this.remoteStates.clear();
            }
            CustomizationSyncClient.this.connecting.set(false);
        }
    }
}

