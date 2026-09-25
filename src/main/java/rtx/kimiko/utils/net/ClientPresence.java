/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.Regex
 *  kotlin.text.StringsKt
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.session.Session
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.net;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.session.Session;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import recovery.privacy.NetworkPolicy;
import rtx.kimiko.utils.net.Endpoints;
import rtx.kimiko.utils.profile.ProfileIdentity;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 $2\u00020\u0001:\u0001$B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003J\r\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0003J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\u0007\u00a2\u0006\u0004\b\r\u0010\tJ\u0017\u0010\u0010\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\u0004\b\u0013\u0010\u0011J\u000f\u0010\u0014\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0003R\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000b\u0010 R \u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0!8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010#\u00a8\u0006%"}, d2={"Lrtx/kimiko/utils/net/ClientPresence;", "", "<init>", "()V", "", "start", "stop", "", "isRunning", "()Z", "", "count", "()I", "hasOnline", "", "name", "isKimikoUser", "(Ljava/lang/String;)Z", "display", "isKimikoDisplay", "selfName", "()Ljava/lang/String;", "poll", "Ljava/net/http/HttpClient;", "httpClient", "Ljava/net/http/HttpClient;", "", "online", "Ljava/util/Set;", "Ljava/util/concurrent/ScheduledExecutorService;", "executor", "Ljava/util/concurrent/ScheduledExecutorService;", "I", "Ljava/util/concurrent/ConcurrentHashMap;", "nameKeyCache", "Ljava/util/concurrent/ConcurrentHashMap;", "Companion", "rtx.kimiko:kimiko"})
public final class ClientPresence {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final HttpClient httpClient;
    @NotNull
    private final Set<String> online;
    @Nullable
    private ScheduledExecutorService executor;
    private volatile int count;
    @NotNull
    private final ConcurrentHashMap<String, String> nameKeyCache;
    @JvmField
    @NotNull
    public static final ClientPresence INSTANCE = new ClientPresence();
    @NotNull
    private static final Regex TOKEN_REGEX = new Regex("[^a-z0-9_]+");
    private static final long POLL_MS = 2000L;

    private ClientPresence() {
        HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(1L)).build();
        Intrinsics.checkNotNullExpressionValue((Object)httpClient, (String)"build(...)");
        this.httpClient = httpClient;
        ConcurrentHashMap.KeySetView keySetView = ConcurrentHashMap.newKeySet();
        Intrinsics.checkNotNullExpressionValue(keySetView, (String)"newKeySet(...)");
        this.online = keySetView;
        this.nameKeyCache = new ConcurrentHashMap();
    }

    public final synchronized void start() {
    }

    public final synchronized void stop() {
        ScheduledExecutorService scheduledExecutorService = this.executor;
        if (scheduledExecutorService == null) {
            return;
        }
        ScheduledExecutorService service = scheduledExecutorService;
        service.shutdownNow();
        this.executor = null;
        this.online.clear();
        this.count = 0;
    }

    public final boolean isRunning() {
        return this.executor != null;
    }

    public final int count() {
        return this.count;
    }

    public final boolean hasOnline() {
        return !((Collection)this.online).isEmpty();
    }

    public final boolean isKimikoUser(@Nullable String name) {
        if (name == null || StringsKt.isBlank(name) || this.online.isEmpty()) {
            return false;
        }
        if (this.nameKeyCache.size() > 512) {
            this.nameKeyCache.clear();
        }
        return this.online.contains(this.nameKeyCache.computeIfAbsent(name, k -> k.trim().toLowerCase(Locale.ROOT)));
    }

    public final boolean isKimikoDisplay(@Nullable String display) {
        if (display == null || StringsKt.isBlank(display) || this.online.isEmpty()) {
            return false;
        }
        String normalized = display.toLowerCase(Locale.ROOT);
        for (String token : TOKEN_REGEX.split(normalized, 0)) {
            if (!token.isEmpty() && this.online.contains(token)) {
                return true;
            }
        }
        return false;
    }

    private final String selfName() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        Session session2 = mc.getSession();
        Intrinsics.checkNotNullExpressionValue((Object)session2, (String)"getUser(...)");
        Session user = session2;
        CharSequence charSequence = user.getUsername();
        if (!(charSequence == null || StringsKt.isBlank((CharSequence)charSequence))) {
            String string = user.getUsername();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getName(...)");
            return string;
        }
        String profileName = ProfileIdentity.username(null);
        if (profileName != null) {
            return profileName;
        }
        return "Player";
    }

    private final void poll() {
        try {
            String me = URLEncoder.encode(this.selfName(), StandardCharsets.UTF_8);
            int uid = ProfileIdentity.uid();
            URI uri = URI.create(Endpoints.irc() + "/api/presence?me=" + me + (String)(uid > 0 ? "&uid=" + uid : ""));
            HttpRequest request = HttpRequest.newBuilder(uri).timeout(Duration.ofSeconds(2L)).header("Accept", "application/json").GET().build();
            HttpResponse<String> response = NetworkPolicy.send(this.httpClient, request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                return;
            }
            JsonObject root = JsonParser.parseString((String)response.body()).getAsJsonObject();
            JsonElement arr = root.get("online");
            if (arr == null || !arr.isJsonArray()) {
                return;
            }
            JsonElement total = root.get("count");
            this.count = total != null && total.isJsonPrimitive() && total.getAsJsonPrimitive().isNumber() ? total.getAsInt() : arr.getAsJsonArray().size();
            Set fresh = ConcurrentHashMap.newKeySet();
            for (JsonElement element : arr.getAsJsonArray()) {
                if (!element.isJsonPrimitive()) continue;
                String name = element.getAsString().trim().toLowerCase(Locale.ROOT);
                if (!name.isEmpty()) {
                    fresh.add(name);
                }
            }
            this.online.clear();
            this.online.addAll(fresh);
        }
        catch (InterruptedException interrupted) {
            Thread.currentThread().interrupt();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private static final Thread start$lambda$0(Runnable r) {
        Thread thread = new Thread(r, "kimiko-presence");
        thread.setDaemon(true);
        return thread;
    }

    private static final void start$lambda$1(ClientPresence this$0) {
        this$0.poll();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/utils/net/ClientPresence.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/net/ClientPresence;", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/utils/net/ClientPresence;", "Lkotlin/text/Regex;", "TOKEN_REGEX", "Lkotlin/text/Regex;", "", "POLL_MS", "J", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

