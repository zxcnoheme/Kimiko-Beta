/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.chat.voice;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import recovery.privacy.NetworkPolicy;
import rtx.kimiko.api.chat.voice.VoiceNote;
import rtx.kimiko.api.chat.voice.VoiceNoteApi;
import rtx.kimiko.utils.net.Endpoints;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000=\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0004*\u0001\u0017\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u0010\u001a\u00020\u000fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0003R\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/chat/voice/VoiceNoteApi;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "base", "()Ljava/lang/String;", "", "blob", "upload", "([B)Ljava/lang/String;", "id", "download", "(Ljava/lang/String;)[B", "", "clearCache", "", "CACHE_ENTRIES", "I", "Ljava/net/http/HttpClient;", "http", "Ljava/net/http/HttpClient;", "rtx/kimiko/api/chat/voice/VoiceNoteApi$cache$1", "cache", "Lrtx/kimiko/api/chat/voice/VoiceNoteApi$cache$1;", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nVoiceNoteApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VoiceNoteApi.kt\nrtx/kimiko/api/chat/voice/VoiceNoteApi\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,79:1\n1#2:80\n*E\n"})
public final class VoiceNoteApi {
    @NotNull
    public static final VoiceNoteApi INSTANCE = new VoiceNoteApi();
    private static final int CACHE_ENTRIES = 12;
    @NotNull
    private static final HttpClient http;
    @NotNull
    private static final LinkedHashMap<String, byte[]> cache;

    private VoiceNoteApi() {
    }

    @JvmStatic
    @NotNull
    public static final String base() {
        char[] cArray = new char[]{'/'};
        return StringsKt.trimEnd((String)Endpoints.irc(), (char[])cArray);
    }

    @JvmStatic
    @Nullable
    public static final String upload(@NotNull byte[] blob) {
        String string;
        Intrinsics.checkNotNullParameter((Object)blob, (String)"blob");
        if (blob.length == 0 || blob.length > 1500000) {
            return null;
        }
        try {
            HttpRequest request = HttpRequest.newBuilder(URI.create(VoiceNoteApi.base() + "/api/voice")).timeout(Duration.ofSeconds(30L)).header("Content-Type", "application/octet-stream").POST(HttpRequest.BodyPublishers.ofByteArray(blob)).build();
            HttpResponse<String> response = NetworkPolicy.send(http, request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                return null;
            }
            JsonObject root = JsonParser.parseString((String)response.body()).getAsJsonObject();
            if (!root.has("id") || !root.get("id").isJsonPrimitive()) {
                return null;
            }
            String id = root.get("id").getAsString();
            string = VoiceNote.isId(id) ? id : null;
        }
        catch (Throwable t) {
            string = null;
        }
        return string;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    @Nullable
    public static final byte[] download(@NotNull String id) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        if (!VoiceNote.isId(id)) {
            return null;
        }
        synchronized (cache) {
            byte[] cached = cache.get(id);
            if (cached != null) {
                return cached;
            }
        }
        byte[] body;
        try {
            HttpRequest request = HttpRequest.newBuilder(URI.create(VoiceNoteApi.base() + "/api/voice/" + id)).timeout(Duration.ofSeconds(30L)).GET().build();
            HttpResponse<byte[]> response = NetworkPolicy.send(http, request, HttpResponse.BodyHandlers.ofByteArray());
            body = response.statusCode() != 200 ? null : response.body();
        }
        catch (Throwable t) {
            body = null;
        }
        if (body == null || body.length == 0) {
            return null;
        }
        synchronized (cache) {
            cache.put(id, body);
        }
        return body;
    }

    @JvmStatic
    public static final void clearCache() {
        synchronized (cache) {
            cache.clear();
        }
    }

    static {
        HttpClient httpClient = NetworkPolicy.redirects(HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(8L)), HttpClient.Redirect.NEVER).build();
        Intrinsics.checkNotNullExpressionValue((Object)httpClient, (String)"build(...)");
        http = httpClient;
        cache = new LinkedHashMap<String, byte[]>(){

            protected boolean removeEldestEntry(Map.Entry<String, byte[]> eldest) {
                Intrinsics.checkNotNullParameter(eldest, (String)"eldest");
                return this.size() > 12;
            }
        };
    }
}

