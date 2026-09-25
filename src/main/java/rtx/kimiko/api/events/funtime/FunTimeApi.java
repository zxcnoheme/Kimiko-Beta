/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.events.funtime;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import recovery.privacy.NetworkPolicy;
import rtx.kimiko.api.events.funtime.FunTimeApiException;
import rtx.kimiko.api.events.funtime.FunTimeEvent;
import rtx.kimiko.api.events.funtime.FunTimeMine;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.utils.net.Endpoints;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\t\u00a2\u0006\u0004\b\u000e\u0010\fJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0016\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/api/events/funtime/FunTimeApi;", "", "<init>", "()V", "", "baseUrl", "", "setBaseUrl", "(Ljava/lang/String;)V", "", "Lrtx/kimiko/api/events/funtime/FunTimeEvent;", "events", "()Ljava/util/List;", "Lrtx/kimiko/api/events/funtime/FunTimeMine;", "mines", "method", "Lcom/google/gson/JsonObject;", "request", "(Ljava/lang/String;)Lcom/google/gson/JsonObject;", "Ljava/net/http/HttpClient;", "http", "Ljava/net/http/HttpClient;", "Ljava/lang/String;", "Companion", "rtx.kimiko:kimiko"})
public final class FunTimeApi {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final HttpClient http;
    @NotNull
    private volatile String baseUrl;
    @JvmField
    @NotNull
    public static final FunTimeApi INSTANCE = new FunTimeApi();

    private FunTimeApi() {
        HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5L)).build();
        Intrinsics.checkNotNullExpressionValue((Object)httpClient, (String)"build(...)");
        this.http = httpClient;
        this.baseUrl = Endpoints.funtime();
    }

    public final void setBaseUrl(@Nullable String baseUrl) {
        CharSequence charSequence = baseUrl;
        if (!(charSequence == null || StringsKt.isBlank((CharSequence)charSequence))) {
            String trimmed = ((Object)StringsKt.trim((CharSequence)baseUrl)).toString();
            this.baseUrl = String.valueOf(trimmed).endsWith("/") ? trimmed : trimmed + "/";
        }
    }

    @NotNull
    public final List<FunTimeEvent> events() throws FunTimeApiException {
        JsonObject servers = FunTimeApi.Companion.servers(this.request("events"));
        ArrayList<FunTimeEvent> out = new ArrayList<FunTimeEvent>();
        for (Map.Entry<String, JsonElement> entry : servers.entrySet()) {
            String key = entry.getKey();
            JsonElement value = entry.getValue();
            int anarchy = FunTimeApi.Companion.anarchyOf(key);
            if (!value.isJsonArray()) continue;
            for (JsonElement element : value.getAsJsonArray()) {
                if (!element.isJsonObject()) continue;
                JsonObject o = element.getAsJsonObject();
                out.add(new FunTimeEvent(FunTimeApi.Companion.string(o, "name", I18n.tr("Событие")), anarchy, 0, FunTimeApi.Companion.string(o, "id", ""), FunTimeApi.Companion.string(o, "rarity", "")));
            }
        }
        return out;
    }

    @NotNull
    public final List<FunTimeMine> mines() throws FunTimeApiException {
        JsonObject servers = FunTimeApi.Companion.servers(this.request("mines"));
        ArrayList<FunTimeMine> out = new ArrayList<FunTimeMine>();
        for (Map.Entry<String, JsonElement> entry : servers.entrySet()) {
            String serverId = entry.getKey();
            JsonElement value = entry.getValue();
            if (!value.isJsonArray()) continue;
            for (JsonElement element : value.getAsJsonArray()) {
                if (!element.isJsonObject()) continue;
                JsonObject o = element.getAsJsonObject();
                out.add(new FunTimeMine(serverId, FunTimeApi.Companion.serverLabel(serverId), FunTimeApi.Companion.string(o, "name", ""), FunTimeApi.Companion.string(o, "rarity", ""), "", FunTimeApi.Companion.number(o, "refillAt", 0L)));
            }
        }
        return out;
    }

    private final JsonObject request(String method) throws FunTimeApiException {
        HttpResponse<String> httpResponse;
        String url = this.baseUrl + method;
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(url)).timeout(Duration.ofSeconds(10L)).header("Accept", "application/json").GET().build();
        try {
            httpResponse = NetworkPolicy.send(this.http, httpRequest, HttpResponse.BodyHandlers.ofString());
            Intrinsics.checkNotNull(httpResponse);
        }
        catch (InterruptedException interrupted) {
            Thread.currentThread().interrupt();
            throw new FunTimeApiException(-1, "interrupted", I18n.tr("Запрос прерван"));
        }
        catch (IOException io) {
            Object[] objectArray = new Object[]{io.getClass().getSimpleName()};
            throw new FunTimeApiException(-1, "network", I18n.tr("Сеть недоступна: %s", objectArray));
        }
        HttpResponse<String> response = httpResponse;
        JsonObject body = FunTimeApi.Companion.parse(response.body());
        if (response.statusCode() == 200) {
            return body;
        }
        throw new FunTimeApiException(response.statusCode(), "http", "HTTP " + response.statusCode());
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0006\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0011\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J)\u0010\u0015\u001a\u00020\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J)\u0010\u0018\u001a\u00020\u00172\b\u0010\u0013\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019R\u0019\u0010\u001c\u001a\u00020\u001a8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001d\u00a8\u0006\u001e"}, d2={"Lrtx/kimiko/api/events/funtime/FunTimeApi.Companion;", "", "<init>", "()V", "", "key", "serverLabel", "(Ljava/lang/String;)Ljava/lang/String;", "server", "", "anarchyOf", "(Ljava/lang/String;)I", "Lcom/google/gson/JsonObject;", "body", "servers", "(Lcom/google/gson/JsonObject;)Lcom/google/gson/JsonObject;", "json", "parse", "(Ljava/lang/String;)Lcom/google/gson/JsonObject;", "object_", "fallback", "string", "(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "", "number", "(Lcom/google/gson/JsonObject;Ljava/lang/String;J)J", "Lrtx/kimiko/api/events/funtime/FunTimeApi;", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/events/funtime/FunTimeApi;", "rtx.kimiko:kimiko"})
    @SourceDebugExtension(value={"SMAP\nFunTimeApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FunTimeApi.kt\nrtx/kimiko/api/events/funtime/FunTimeApi.Companion\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,167:1\n437#2:168\n513#2,5:169\n1#3:174\n*S KotlinDebug\n*F\n+ 1 FunTimeApi.kt\nrtx/kimiko/api/events/funtime/FunTimeApi.Companion\n*L\n135#1:168\n135#1:169,5\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        private final String serverLabel(String key) {
            CharSequence charSequence = key;
            if (charSequence == null || StringsKt.isBlank((CharSequence)charSequence)) {
                return "";
            }
            int n = this.anarchyOf(key);
            if (n <= 0) {
                return key;
            }
            String string = key;
            Locale locale = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
            String string2 = string.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toUpperCase(...)");
            String upper = string2;
            String kind = String.valueOf(upper).contains("LITE") ? I18n.tr("Лайт-Анархия") : I18n.tr("Анархия");
            String suffix = String.valueOf(upper).contains("NEW") ? " (1.21)" : "";
            return kind + " " + n + suffix;
        }

        /*
         * WARNING - void declaration
         */
        private final int anarchyOf(String server) {
            if (server == null) {
                return 0;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < server.length(); i++) {
                char c = server.charAt(i);
                if (Character.isDigit(c)) {
                    sb.append(c);
                }
            }
            Integer n2 = StringsKt.toIntOrNull(sb.toString());
            return n2 != null ? n2 : 0;
        }

        private final JsonObject servers(JsonObject body) {
            JsonObject jsonObject = body.getAsJsonObject("servers");
            if (jsonObject == null) {
                jsonObject = new JsonObject();
            }
            return jsonObject;
        }

        private final JsonObject parse(String json) {
            JsonObject jsonObject;
            try {
                String string = json;
                if (string == null) {
                    string = "";
                }
                JsonElement jsonElement = JsonParser.parseString((String)string);
                Intrinsics.checkNotNullExpressionValue((Object)jsonElement, (String)"parseString(...)");
                JsonElement element = jsonElement;
                jsonObject = element.isJsonObject() ? element.getAsJsonObject() : new JsonObject();
                Intrinsics.checkNotNull((Object)jsonObject);
            }
            catch (RuntimeException ignored) {
                jsonObject = new JsonObject();
            }
            return jsonObject;
        }

        private final String string(JsonObject object_, String key, String fallback) {
            if (object_ == null || !object_.has(key)) {
                return fallback;
            }
            JsonElement value = object_.get(key);
            if (value == null || !value.isJsonPrimitive()) {
                return fallback;
            }
            String text = value.getAsString().trim();
            return text.isEmpty() ? fallback : text;
        }

        private final long number(JsonObject object_, String key, long fallback) {
            if (object_ == null || !object_.has(key)) {
                return fallback;
            }
            JsonElement value = object_.get(key);
            if (value == null || !value.isJsonPrimitive() || !value.getAsJsonPrimitive().isNumber()) {
                return fallback;
            }
            return value.getAsLong();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

