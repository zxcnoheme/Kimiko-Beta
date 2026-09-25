/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.liteapi;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u000b\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002\u001b\u001cB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J)\u0010\n\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\f\u001a\u00020\u0004H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\u000e\u0010\u000fJ#\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\u0013\u0010\u0014J#\u0010\u0017\u001a\u0004\u0018\u00010\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0016\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J#\u0010\u0019\u001a\u0004\u0018\u00010\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0016\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/api/liteapi/LiteApiCodec;", "", "<init>", "()V", "", "client", "", "features", "Lrtx/kimiko/api/liteapi/LiteApiCodec$Request;", "Lkotlin/jvm/JvmStatic;", "checkFeatures", "(Ljava/lang/String;Ljava/util/Collection;)Lrtx/kimiko/api/liteapi/LiteApiCodec$Request;", "raw", "Lrtx/kimiko/api/liteapi/LiteApiCodec$Incoming;", "parse", "(Ljava/lang/String;)Lrtx/kimiko/api/liteapi/LiteApiCodec$Incoming;", "Lcom/google/gson/JsonObject;", "payload", "", "blocklist", "(Lcom/google/gson/JsonObject;)Ljava/util/List;", "o", "k", "str", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/String;", "obj", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonObject;", "Request", "Incoming", "rtx.kimiko:kimiko"})
public final class LiteApiCodec {
    @NotNull
    public static final LiteApiCodec INSTANCE = new LiteApiCodec();

    private LiteApiCodec() {
    }

    @JvmStatic
    @NotNull
    public static final Request checkFeatures(@NotNull String client, @NotNull Collection<String> features) {
        Intrinsics.checkNotNullParameter((Object)client, (String)"client");
        Intrinsics.checkNotNullParameter(features, (String)"features");
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        String id = string;
        JsonArray arr = new JsonArray();
        for (String f : features) {
            arr.add(f);
        }
        JsonObject payload = new JsonObject();
        payload.addProperty("client", client);
        payload.add("features", (JsonElement)arr);
        JsonObject root = new JsonObject();
        root.addProperty("id", id);
        root.addProperty("method", "checkFeatures");
        root.add("payload", (JsonElement)payload);
        String string2 = root.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toString(...)");
        return new Request(id, string2);
    }

    @JvmStatic
    @Nullable
    public static final Incoming parse(@NotNull String raw) {
        Intrinsics.checkNotNullParameter((Object)raw, (String)"raw");
        JsonObject o = null;
        try {
            JsonElement el = JsonParser.parseString((String)raw);
            if (!el.isJsonObject()) {
                return null;
            }
            JsonObject jsonObject = el.getAsJsonObject();
            Intrinsics.checkNotNullExpressionValue((Object)jsonObject, (String)"getAsJsonObject(...)");
            o = jsonObject;
        }
        catch (RuntimeException ex) {
            return null;
        }
        JsonObject payload = INSTANCE.obj(o, "payload");
        if (!o.has("id") && o.has("event")) {
            return new Incoming(null, false, false, null, null, INSTANCE.str(o, "event"), payload);
        }
        String id = INSTANCE.str(o, "id");
        boolean ok = o.has("ok") && o.get("ok").isJsonPrimitive() && o.get("ok").getAsBoolean();
        String error = INSTANCE.str(o, "error");
        String message = INSTANCE.str(o, "message");
        return new Incoming(id, true, ok, error, message, null, payload);
    }

    @JvmStatic
    @NotNull
    public static final List<String> blocklist(@Nullable JsonObject payload) {
        ArrayList<String> out = new ArrayList<String>();
        if (payload == null || !payload.has("blocklist") || !payload.get("blocklist").isJsonArray()) {
            return out;
        }
        Iterator iterator = payload.getAsJsonArray("blocklist").iterator();
        Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
        Iterator iterator2 = iterator;
        while (iterator2.hasNext()) {
            JsonElement el = (JsonElement)iterator2.next();
            if (el == null || !el.isJsonPrimitive()) continue;
            out.add(el.getAsString());
        }
        return out;
    }

    private final String str(JsonObject o, String k) {
        return o != null && o.has(k) && o.get(k).isJsonPrimitive() ? o.get(k).getAsString() : null;
    }

    private final JsonObject obj(JsonObject o, String k) {
        return o != null && o.has(k) && o.get(k).isJsonObject() ? o.getAsJsonObject(k) : null;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u000fJ\u0010\u0010\u0013\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u000fJ\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0011J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0011J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0011J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\nH\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0018J`\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\nH\u00c6\u0001\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u001b\u0010\u001c\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0011\u0010\u001f\u001a\u00020\u001eH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001f\u0010 J\u0011\u0010!\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b!\u0010\u0011R'\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010$\u001a\u0004\b\u0003\u0010\u0011R%\u0010\u0005\u001a\u00020\u00048\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010%\u001a\u0004\b\u0005\u0010\u000fR%\u0010\u0006\u001a\u00020\u00048\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010%\u001a\u0004\b\u0006\u0010\u000fR'\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010$\u001a\u0004\b\u0007\u0010\u0011R'\u0010\b\u001a\u0004\u0018\u00010\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010$\u001a\u0004\b\b\u0010\u0011R'\u0010\t\u001a\u0004\u0018\u00010\u00028\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010$\u001a\u0004\b\t\u0010\u0011R'\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0007z\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010&\u001a\u0004\b\u000b\u0010\u0018\u00a8\u0006'"}, d2={"Lrtx/kimiko/api/liteapi/LiteApiCodec$Incoming;", "", "", "id", "", "isResponse", "ok", "error", "message", "event", "Lcom/google/gson/JsonObject;", "payload", "<init>", "(Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/google/gson/JsonObject;)V", "isPushEvent", "()Z", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "component6", "component7", "()Lcom/google/gson/JsonObject;", "copy", "(Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/google/gson/JsonObject;)Lrtx/kimiko/api/liteapi/LiteApiCodec$Incoming;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Lkotlin/jvm/JvmName;", "name", "Ljava/lang/String;", "Z", "Lcom/google/gson/JsonObject;", "rtx.kimiko:kimiko"})
    public static final class Incoming {
        @Nullable
        private final String id;
        private final boolean isResponse;
        private final boolean ok;
        @Nullable
        private final String error;
        @Nullable
        private final String message;
        @Nullable
        private final String event;
        @Nullable
        private final JsonObject payload;

        public Incoming(@Nullable String id, boolean isResponse, boolean ok, @Nullable String error, @Nullable String message, @Nullable String event, @Nullable JsonObject payload) {
            this.id = id;
            this.isResponse = isResponse;
            this.ok = ok;
            this.error = error;
            this.message = message;
            this.event = event;
            this.payload = payload;
        }

        @JvmName(name="id")
        @Nullable
        public final String id() {
            return this.id;
        }

        @JvmName(name="isResponse")
        public final boolean isResponse() {
            return this.isResponse;
        }

        @JvmName(name="ok")
        public final boolean ok() {
            return this.ok;
        }

        @JvmName(name="error")
        @Nullable
        public final String error() {
            return this.error;
        }

        @JvmName(name="message")
        @Nullable
        public final String message() {
            return this.message;
        }

        @JvmName(name="event")
        @Nullable
        public final String event() {
            return this.event;
        }

        @JvmName(name="payload")
        @Nullable
        public final JsonObject payload() {
            return this.payload;
        }

        public final boolean isPushEvent() {
            return !this.isResponse && this.event != null;
        }

        @Nullable
        public final String component1() {
            return this.id;
        }

        public final boolean component2() {
            return this.isResponse;
        }

        public final boolean component3() {
            return this.ok;
        }

        @Nullable
        public final String component4() {
            return this.error;
        }

        @Nullable
        public final String component5() {
            return this.message;
        }

        @Nullable
        public final String component6() {
            return this.event;
        }

        @Nullable
        public final JsonObject component7() {
            return this.payload;
        }

        @NotNull
        public final Incoming copy(@Nullable String id, boolean isResponse, boolean ok, @Nullable String error, @Nullable String message, @Nullable String event, @Nullable JsonObject payload) {
            return new Incoming(id, isResponse, ok, error, message, event, payload);
        }

        public static /* synthetic */ Incoming copy$default(Incoming incoming, String string, boolean bl, boolean bl2, String string2, String string3, String string4, JsonObject jsonObject, int n, Object object) {
            if ((n & 1) != 0) {
                string = incoming.id;
            }
            if ((n & 2) != 0) {
                bl = incoming.isResponse;
            }
            if ((n & 4) != 0) {
                bl2 = incoming.ok;
            }
            if ((n & 8) != 0) {
                string2 = incoming.error;
            }
            if ((n & 0x10) != 0) {
                string3 = incoming.message;
            }
            if ((n & 0x20) != 0) {
                string4 = incoming.event;
            }
            if ((n & 0x40) != 0) {
                jsonObject = incoming.payload;
            }
            return incoming.copy(string, bl, bl2, string2, string3, string4, jsonObject);
        }

        @NotNull
        public String toString() {
            return "Incoming(id=" + this.id + ", isResponse=" + this.isResponse + ", ok=" + this.ok + ", error=" + this.error + ", message=" + this.message + ", event=" + this.event + ", payload=" + this.payload + ")";
        }

        public int hashCode() {
            int result = this.id == null ? 0 : this.id.hashCode();
            result = result * 31 + Boolean.hashCode(this.isResponse);
            result = result * 31 + Boolean.hashCode(this.ok);
            result = result * 31 + (this.error == null ? 0 : this.error.hashCode());
            result = result * 31 + (this.message == null ? 0 : this.message.hashCode());
            result = result * 31 + (this.event == null ? 0 : this.event.hashCode());
            result = result * 31 + (this.payload == null ? 0 : this.payload.hashCode());
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Incoming)) {
                return false;
            }
            Incoming incoming = (Incoming)other;
            if (!Intrinsics.areEqual((Object)this.id, (Object)incoming.id)) {
                return false;
            }
            if (this.isResponse != incoming.isResponse) {
                return false;
            }
            if (this.ok != incoming.ok) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.error, (Object)incoming.error)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.message, (Object)incoming.message)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.event, (Object)incoming.event)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.payload, (Object)incoming.payload);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\t\u0010\bJ$\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0011\u0010\u0011\u001a\u00020\u0010H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0011\u0010\u0013\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\bR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0003\u0010\bR%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0004\u0010\b\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/api/liteapi/LiteApiCodec$Request;", "", "", "id", "json", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "component1", "()Ljava/lang/String;", "component2", "copy", "(Ljava/lang/String;Ljava/lang/String;)Lrtx/kimiko/api/liteapi/LiteApiCodec$Request;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Lkotlin/jvm/JvmName;", "name", "Ljava/lang/String;", "rtx.kimiko:kimiko"})
    public static final class Request {
        @NotNull
        private final String id;
        @NotNull
        private final String json;

        public Request(@NotNull String id, @NotNull String json) {
            Intrinsics.checkNotNullParameter((Object)id, (String)"id");
            Intrinsics.checkNotNullParameter((Object)json, (String)"json");
            this.id = id;
            this.json = json;
        }

        @JvmName(name="id")
        @NotNull
        public final String id() {
            return this.id;
        }

        @JvmName(name="json")
        @NotNull
        public final String json() {
            return this.json;
        }

        @NotNull
        public final String component1() {
            return this.id;
        }

        @NotNull
        public final String component2() {
            return this.json;
        }

        @NotNull
        public final Request copy(@NotNull String id, @NotNull String json) {
            Intrinsics.checkNotNullParameter((Object)id, (String)"id");
            Intrinsics.checkNotNullParameter((Object)json, (String)"json");
            return new Request(id, json);
        }

        public static /* synthetic */ Request copy$default(Request request, String string, String string2, int n, Object object) {
            if ((n & 1) != 0) {
                string = request.id;
            }
            if ((n & 2) != 0) {
                string2 = request.json;
            }
            return request.copy(string, string2);
        }

        @NotNull
        public String toString() {
            return "Request(id=" + this.id + ", json=" + this.json + ")";
        }

        public int hashCode() {
            int result = this.id.hashCode();
            result = result * 31 + this.json.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Request)) {
                return false;
            }
            Request request = (Request)other;
            if (!Intrinsics.areEqual((Object)this.id, (Object)request.id)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.json, (Object)request.json);
        }
    }
}

