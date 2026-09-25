/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.party;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import recovery.privacy.NetworkPolicy;
import rtx.kimiko.api.party.PartyChat;
import rtx.kimiko.api.party.PartyInvite;
import rtx.kimiko.api.party.PartyMarker;
import rtx.kimiko.api.party.PartySnapshot;
import rtx.kimiko.utils.net.Endpoints;
import rtx.kimiko.utils.profile.ProfileIdentity;
import rtx.kimiko.utils.storage.RepositoryStorage;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00c6\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\u0018\u0000 \u007f2\u00020\u0001:\u0007\u0080\u0001\u0081\u0001\u0082\u0001\u007fB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003J\r\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u0017\u0010\t\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0012\u001a\u00020\u0011\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0013\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0019\u001a\u00020\u0018\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u0018\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0015\u0010!\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u0018\u00a2\u0006\u0004\b!\u0010\u001fJ\u001d\u0010$\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u000e\u00a2\u0006\u0004\b$\u0010%J\u0015\u0010'\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u0018\u00a2\u0006\u0004\b'\u0010\u001fJ\r\u0010(\u001a\u00020\u000e\u00a2\u0006\u0004\b(\u0010\u0010J\r\u0010)\u001a\u00020\u000e\u00a2\u0006\u0004\b)\u0010\u0010J\u0015\u0010*\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u0018\u00a2\u0006\u0004\b*\u0010\u001fJ\r\u0010+\u001a\u00020\u000e\u00a2\u0006\u0004\b+\u0010\u0010J5\u00103\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020,2\u0006\u0010/\u001a\u00020,2\u0006\u00100\u001a\u00020\u00182\u0006\u00102\u001a\u000201\u00a2\u0006\u0004\b3\u00104JE\u00108\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020,2\u0006\u0010/\u001a\u00020,2\u0006\u00105\u001a\u00020,2\u0006\u00106\u001a\u00020,2\u0006\u00107\u001a\u00020,2\u0006\u00100\u001a\u00020\u0018\u00a2\u0006\u0004\b8\u00109J\u001d\u0010=\u001a\u00020\u00042\u0006\u0010;\u001a\u00020:2\u0006\u0010<\u001a\u00020\u000e\u00a2\u0006\u0004\b=\u0010>J-\u0010C\u001a\u00020\u000e2\u0006\u0010?\u001a\u00020\u00182\u0014\u0010B\u001a\u0010\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020\u0004\u0018\u00010@H\u0002\u00a2\u0006\u0004\bC\u0010DJ\u0017\u0010E\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\bE\u0010\u001fJ\u0017\u0010H\u001a\u00020\u00042\b\u0010G\u001a\u0004\u0018\u00010F\u00a2\u0006\u0004\bH\u0010IJ\u000f\u0010J\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bJ\u0010\u0003J\u0017\u0010M\u001a\u00020\u00042\u0006\u0010L\u001a\u00020KH\u0002\u00a2\u0006\u0004\bM\u0010NJ\u000f\u0010O\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\bO\u0010PJ\u0017\u0010R\u001a\u00020\u00042\u0006\u0010Q\u001a\u00020FH\u0002\u00a2\u0006\u0004\bR\u0010IJ\u0017\u0010T\u001a\u00020\u00042\u0006\u0010S\u001a\u00020AH\u0002\u00a2\u0006\u0004\bT\u0010UR\u0014\u0010W\u001a\u00020V8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bW\u0010XR\"\u0010\u0012\u001a\u0010\u0012\f\u0012\n Z*\u0004\u0018\u00010\u00110\u00110Y8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010[R \u0010]\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u001a0\\8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b]\u0010^R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010_R\u0018\u0010b\u001a\u00060`j\u0002`a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bb\u0010cR\u0014\u0010e\u001a\u00020d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\be\u0010fR\u0014\u0010h\u001a\u00020g8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bh\u0010iR\u0018\u0010k\u001a\u0004\u0018\u00010j8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bk\u0010lR\u0018\u0010n\u001a\u0004\u0018\u00010m8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bn\u0010oR\u0018\u0010p\u001a\u0004\u0018\u00010K8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bp\u0010qR\u0016\u0010r\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\br\u0010sR\u0016\u0010t\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bt\u0010sR\u0018\u0010u\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bu\u0010vR\u0018\u0010w\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bw\u0010xR\u0016\u0010y\u001a\u00020:8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\by\u0010zR\u0016\u0010{\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b{\u0010sR\u0016\u0010|\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b|\u0010sR\u0018\u0010O\u001a\u0004\u0018\u00010\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bO\u0010}R\u0016\u0010~\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b~\u0010}\u00a8\u0006\u0083\u0001"}, d2={"Lrtx/kimiko/api/party/PartyClient;", "", "<init>", "()V", "", "start", "stop", "Lrtx/kimiko/api/party/PartyClient$PartyVoiceSink;", "sink", "setVoiceSink", "(Lrtx/kimiko/api/party/PartyClient$PartyVoiceSink;)V", "Lrtx/kimiko/api/party/PartyClient$PartySpitSink;", "setSpitSink", "(Lrtx/kimiko/api/party/PartyClient$PartySpitSink;)V", "", "isConnected", "()Z", "Lrtx/kimiko/api/party/PartySnapshot;", "snapshot", "()Lrtx/kimiko/api/party/PartySnapshot;", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lrtx/kimiko/api/party/PartyMarker;", "markers", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "", "id", "Lrtx/kimiko/api/party/PartyInvite;", "pendingInvite", "(Ljava/lang/String;)Lrtx/kimiko/api/party/PartyInvite;", "name", "create", "(Ljava/lang/String;)Z", "target", "inviteUser", "inviteId", "accept", "respondInvite", "(Ljava/lang/String;Z)Z", "text", "sendChat", "leave", "disband", "kick", "requestInfo", "", "x", "y", "z", "dim", "", "ttlMs", "sendPing", "(DDDLjava/lang/String;J)Z", "dx", "dy", "dz", "sendSpit", "(DDDDDDLjava/lang/String;)Z", "", "color", "rainbow", "setLocalColor", "(IZ)V", "type", "Lkotlin/Function1;", "Lcom/google/gson/JsonObject;", "fill", "send", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Z", "submitReliable", "", "frame", "sendVoice", "([B)V", "ensureConnected", "Ljava/net/http/WebSocket;", "s", "sendHello", "(Ljava/net/http/WebSocket;)V", "clientId", "()Ljava/lang/String;", "f", "handleVoice", "root", "handle", "(Lcom/google/gson/JsonObject;)V", "Ljava/net/http/HttpClient;", "http", "Ljava/net/http/HttpClient;", "Ljava/util/concurrent/atomic/AtomicReference;", "kotlin.jvm.PlatformType", "Ljava/util/concurrent/atomic/AtomicReference;", "", "invites", "Ljava/util/Map;", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "rx", "Ljava/lang/StringBuilder;", "Ljava/io/ByteArrayOutputStream;", "rxBin", "Ljava/io/ByteArrayOutputStream;", "Ljava/util/concurrent/atomic/AtomicInteger;", "voiceBacklog", "Ljava/util/concurrent/atomic/AtomicInteger;", "Ljava/util/concurrent/ScheduledExecutorService;", "executor", "Ljava/util/concurrent/ScheduledExecutorService;", "Ljava/util/concurrent/ExecutorService;", "outbound", "Ljava/util/concurrent/ExecutorService;", "socket", "Ljava/net/http/WebSocket;", "connected", "Z", "connecting", "voiceSink", "Lrtx/kimiko/api/party/PartyClient$PartyVoiceSink;", "spitSink", "Lrtx/kimiko/api/party/PartyClient$PartySpitSink;", "localColor", "I", "localRainbow", "hasLocalColor", "Ljava/lang/String;", "lastHelloName", "Companion", "PartyVoiceSink", "PartySpitSink", "Listener", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nPartyClient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PartyClient.kt\nrtx/kimiko/api/party/PartyClient\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,527:1\n1#2:528\n*E\n"})
public final class PartyClient {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final HttpClient http;
    @NotNull
    private final AtomicReference<PartySnapshot> snapshot;
    @NotNull
    private final Map<String, PartyInvite> invites;
    @NotNull
    private final CopyOnWriteArrayList<PartyMarker> markers;
    @NotNull
    private final StringBuilder rx;
    @NotNull
    private final ByteArrayOutputStream rxBin;
    @NotNull
    private final AtomicInteger voiceBacklog;
    @Nullable
    private ScheduledExecutorService executor;
    @Nullable
    private volatile ExecutorService outbound;
    @Nullable
    private volatile WebSocket socket;
    private volatile boolean connected;
    private volatile boolean connecting;
    @Nullable
    private volatile PartyVoiceSink voiceSink;
    @Nullable
    private volatile PartySpitSink spitSink;
    private volatile int localColor;
    private volatile boolean localRainbow;
    private volatile boolean hasLocalColor;
    @Nullable
    private volatile String clientId;
    @NotNull
    private volatile String lastHelloName;
    @JvmField
    @NotNull
    public static final PartyClient INSTANCE = new PartyClient();
    private static final int MAX_MARKERS = 64;
    private static final int MAX_VOICE_BACKLOG = 8;

    private PartyClient() {
        HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5L)).build();
        Intrinsics.checkNotNullExpressionValue((Object)httpClient, (String)"build(...)");
        this.http = httpClient;
        this.snapshot = new AtomicReference<PartySnapshot>(PartySnapshot.NONE);
        this.invites = new ConcurrentHashMap();
        this.markers = new CopyOnWriteArrayList();
        this.rx = new StringBuilder();
        this.rxBin = new ByteArrayOutputStream();
        this.voiceBacklog = new AtomicInteger();
        this.lastHelloName = "";
    }

    public final synchronized void start() {
    }

    public final synchronized void stop() {
        ScheduledExecutorService scheduledExecutorService = this.executor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
        }
        this.executor = null;
        ExecutorService executorService = this.outbound;
        if (executorService != null) {
            executorService.shutdownNow();
        }
        this.outbound = null;
        this.voiceBacklog.set(0);
        WebSocket s = this.socket;
        this.socket = null;
        this.connected = false;
        this.connecting = false;
        if (s != null) {
            try {
                s.sendClose(1000, "bye");
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
        this.markers.clear();
        this.invites.clear();
        this.snapshot.set(PartySnapshot.NONE);
    }

    public final void setVoiceSink(@Nullable PartyVoiceSink sink) {
        this.voiceSink = sink;
    }

    public final void setSpitSink(@Nullable PartySpitSink sink) {
        this.spitSink = sink;
    }

    public final boolean isConnected() {
        return this.connected;
    }

    @NotNull
    public final PartySnapshot snapshot() {
        PartySnapshot partySnapshot = this.snapshot.get();
        Intrinsics.checkNotNullExpressionValue((Object)partySnapshot, (String)"get(...)");
        return partySnapshot;
    }

    @NotNull
    public final CopyOnWriteArrayList<PartyMarker> markers() {
        return this.markers;
    }

    @Nullable
    public final PartyInvite pendingInvite(@NotNull String id) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        return this.invites.get(id);
    }

    public final boolean create(@NotNull String name) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        return this.send("create", o -> o.addProperty("name", name));
    }

    public final boolean inviteUser(@NotNull String target) {
        Intrinsics.checkNotNullParameter((Object)target, (String)"target");
        return this.send("invite", o -> o.addProperty("target", target));
    }

    public final boolean respondInvite(@NotNull String inviteId, boolean accept) {
        Intrinsics.checkNotNullParameter((Object)inviteId, (String)"inviteId");
        this.invites.remove(inviteId);
        return this.send("invite_response", o -> {
            o.addProperty("invite", inviteId);
            o.addProperty("accept", accept);
        });
    }

    public final boolean sendChat(@NotNull String text) {
        Intrinsics.checkNotNullParameter((Object)text, (String)"text");
        return this.send("chat", o -> o.addProperty("text", text));
    }

    public final boolean leave() {
        return this.send("leave", null);
    }

    public final boolean disband() {
        return this.send("disband", null);
    }

    public final boolean kick(@NotNull String target) {
        Intrinsics.checkNotNullParameter((Object)target, (String)"target");
        return this.send("kick", o -> o.addProperty("target", target));
    }

    public final boolean requestInfo() {
        return this.send("info", null);
    }

    public final boolean sendPing(double x, double y, double z, @NotNull String dim, long ttlMs) {
        Intrinsics.checkNotNullParameter((Object)dim, (String)"dim");
        return this.send("marker", o -> {
            o.addProperty("x", x);
            o.addProperty("y", y);
            o.addProperty("z", z);
            o.addProperty("dim", dim);
            o.addProperty("ttl", ttlMs);
        });
    }

    public final boolean sendSpit(double x, double y, double z, double dx, double dy, double dz, @NotNull String dim) {
        Intrinsics.checkNotNullParameter((Object)dim, (String)"dim");
        return this.send("spit", o -> {
            o.addProperty("x", x);
            o.addProperty("y", y);
            o.addProperty("z", z);
            o.addProperty("dx", dx);
            o.addProperty("dy", dy);
            o.addProperty("dz", dz);
            o.addProperty("dim", dim);
        });
    }

    public final void setLocalColor(int color, boolean rainbow) {
        this.localColor = color;
        this.localRainbow = rainbow;
        this.hasLocalColor = true;
        this.send("color", o -> {
            o.addProperty("color", color);
            o.addProperty("rainbow", rainbow);
        });
    }

    private final boolean send(String type, Consumer<JsonObject> fill) {
        if (this.socket == null || !this.connected) {
            return false;
        }
        JsonObject o = new JsonObject();
        o.addProperty("type", type);
        if (fill != null) {
            fill.accept(o);
        }
        String string = o.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        return this.submitReliable(string);
    }

    private final boolean submitReliable(String text) {
        boolean bl;
        ExecutorService executorService = this.outbound;
        if (executorService == null) {
            return false;
        }
        ExecutorService ex = executorService;
        try {
            ex.execute(() -> PartyClient.submitReliable$lambda$0(this, text));
            bl = true;
        }
        catch (Throwable t) {
            bl = false;
        }
        return bl;
    }

    public final void sendVoice(@Nullable byte[] frame) {
        ExecutorService ex = this.outbound;
        if (ex == null || this.socket == null || !this.connected || frame == null) {
            return;
        }
        if (this.voiceBacklog.get() >= 8) {
            return;
        }
        this.voiceBacklog.incrementAndGet();
        ByteBuffer buf = ByteBuffer.wrap(frame);
        try {
            ex.execute(() -> PartyClient.sendVoice$lambda$0(this, buf));
        }
        catch (Throwable t) {
            this.voiceBacklog.decrementAndGet();
        }
    }

    private final void ensureConnected() {
        if (this.connected) {
            WebSocket s = this.socket;
            if (s != null && !Intrinsics.areEqual((Object)PartyClient.Companion.resolveName(), (Object)this.lastHelloName)) {
                this.sendHello(s);
            }
            return;
        }
        if (this.connecting || this.socket != null) {
            return;
        }
        this.connecting = true;
        try {
            NetworkPolicy.webSocket(this.http.newWebSocketBuilder().connectTimeout(Duration.ofSeconds(8L)), URI.create(Endpoints.party()), new Listener()).whenComplete((ws, err) -> {
                this.connecting = false;
                if (err != null) {
                    this.connected = false;
                    this.socket = null;
                }
            });
        }
        catch (Throwable t) {
            this.connecting = false;
            this.connected = false;
            this.socket = null;
        }
    }

    private final void sendHello(WebSocket s) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        String name = PartyClient.Companion.resolveName();
        String uuid = "";
        try {
            if (mc.getSession() != null && mc.getSession().getUuidOrNull() != null) {
                String string = mc.getSession().getUuidOrNull().toString();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
                uuid = string;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        JsonObject o = new JsonObject();
        o.addProperty("type", "hello");
        o.addProperty("id", this.clientId());
        o.addProperty("name", name);
        o.addProperty("uuid", uuid);
        int uid = ProfileIdentity.uid();
        if (uid > 0) {
            o.addProperty("uid", (Number)uid);
        }
        String string = o.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        if (this.submitReliable(string)) {
            this.lastHelloName = name;
        }
    }

    private final synchronized String clientId() {
        block6: {
            int uid = ProfileIdentity.uid();
            if (uid > 0) {
                return String.valueOf(uid);
            }
            String string = this.clientId;
            if (string != null) {
                String it = string;
                boolean bl = false;
                return it;
            }
            try {
                String current;
                JsonObject store = RepositoryStorage.readObject("party");
                if (store.has("clientId") && store.get("clientId").isJsonPrimitive()) {
                    this.clientId = store.get("clientId").getAsString();
                }
                if ((current = this.clientId) == null || StringsKt.isBlank((CharSequence)current)) {
                    String generated;
                    String string2 = UUID.randomUUID().toString();
                    Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toString(...)");
                    this.clientId = generated = string2;
                    store.addProperty("clientId", generated);
                    RepositoryStorage.write("party", store);
                }
            }
            catch (Throwable t) {
                if (this.clientId != null) break block6;
                this.clientId = UUID.randomUUID().toString();
            }
        }
        String string = this.clientId;
        Intrinsics.checkNotNull((Object)string);
        return string;
    }

    private final void handleVoice(byte[] f) {
        PartyVoiceSink sink = this.voiceSink;
        if (sink == null || f.length < 4) {
            return;
        }
        int nameLen = f[0] & 0xFF;
        if (f.length < 1 + nameLen + 2) {
            return;
        }
        int n = 1;
        Charset charset = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue((Object)charset, (String)"UTF_8");
        Charset charset2 = charset;
        String from = new String(f, n, nameLen, charset2);
        int off = 1 + nameLen;
        int seq = (f[off] & 0xFF) << 8 | f[off + 1] & 0xFF;
        byte[] opus = Arrays.copyOfRange(f, off + 2, f.length);
        try {
            Intrinsics.checkNotNull((Object)opus);
            sink.accept(from, seq, opus);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private final void handle(JsonObject root) {
        switch (PartyClient.Companion.str(root, "type")) {
            case "welcome": {
                this.snapshot.set(PartySnapshot.Companion.fromJson(PartyClient.Companion.obj(root, "party")));
                break;
            }
            case "party": {
                PartySnapshot snap = PartySnapshot.Companion.fromJson(PartyClient.Companion.obj(root, "party"));
                this.snapshot.set(snap);
                if (!snap.exists()) {
                    this.markers.clear();
                }
                if (!PartyClient.Companion.bool(root, "info")) break;
                PartyClient.Companion.runGame(() -> PartyClient.handle$lambda$0(snap));
                break;
            }
            case "invite": {
                String id = PartyClient.Companion.str(root, "invite");
                if (!(((CharSequence)id).length() > 0)) break;
                String from = PartyClient.Companion.str(root, "from");
                String party = PartyClient.Companion.str(root, "party");
                long ttl = (long)PartyClient.Companion.num(root, "expires", 60000.0);
                this.invites.put(id, new PartyInvite(id, from, party, System.currentTimeMillis() + ttl));
                PartyClient.Companion.runGame(() -> PartyClient.handle$lambda$1(from, party, id));
                break;
            }
            case "notice": {
                String msg = PartyClient.Companion.str(root, "message");
                if (!(((CharSequence)msg).length() > 0)) break;
                String level = PartyClient.Companion.str(root, "level");
                PartyClient.Companion.runGame(() -> PartyClient.handle$lambda$2(level, msg));
                break;
            }
            case "chat": {
                String from = PartyClient.Companion.str(root, "from");
                String text = PartyClient.Companion.str(root, "text");
                if (!(((CharSequence)text).length() > 0)) break;
                PartyClient.Companion.runGame(() -> PartyClient.handle$lambda$3(from, text));
                break;
            }
            case "marker": {
                PartyMarker m = new PartyMarker(PartyClient.Companion.str(root, "id"), PartyClient.Companion.str(root, "from"), PartyClient.Companion.num(root, "x", 0.0), PartyClient.Companion.num(root, "y", 0.0), PartyClient.Companion.num(root, "z", 0.0), PartyClient.Companion.str(root, "dim"), System.currentTimeMillis(), (long)PartyClient.Companion.num(root, "ttl", 8000.0), (int)PartyClient.Companion.num(root, "color", 0.0), PartyClient.Companion.bool(root, "rainbow"));
                if (!m.finiteAndSafe()) break;
                this.markers.add(m);
                while (this.markers.size() > 64) {
                    this.markers.remove(0);
                }
                break;
            }
            case "marker_remove": {
                String id = PartyClient.Companion.str(root, "id");
                if (!(((CharSequence)id).length() > 0)) break;
                long now = System.currentTimeMillis();
                Iterator<PartyMarker> iterator = this.markers.iterator();
                Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
                Iterator<PartyMarker> ttl = iterator;
                while (ttl.hasNext()) {
                    PartyMarker m = ttl.next();
                    if (!Intrinsics.areEqual((Object)id, (Object)m.id())) continue;
                    m.forceExpireSoon(now);
                }
                break;
            }
            case "spit": {
                String from = PartyClient.Companion.str(root, "from");
                double x = PartyClient.Companion.num(root, "x", 0.0);
                double y = PartyClient.Companion.num(root, "y", 0.0);
                double z = PartyClient.Companion.num(root, "z", 0.0);
                double dx = PartyClient.Companion.num(root, "dx", 0.0);
                double dy = PartyClient.Companion.num(root, "dy", 0.0);
                double dz = PartyClient.Companion.num(root, "dz", 0.0);
                String dim = PartyClient.Companion.str(root, "dim");
                PartySpitSink sink = this.spitSink;
                if (sink == null || !(((CharSequence)from).length() > 0) || !(((CharSequence)dim).length() > 0)) break;
                PartyClient.Companion.runGame(() -> PartyClient.handle$lambda$4(sink, from, x, y, z, dx, dy, dz, dim));
                break;
            }
            case "error": {
                String msg = PartyClient.Companion.str(root, "message");
                if (!(((CharSequence)msg).length() > 0)) break;
                PartyClient.Companion.runGame(() -> PartyClient.handle$lambda$5(msg));
            }
        }
    }

    private static final Thread start$lambda$0(Runnable r) {
        Thread t = new Thread(r, "kimiko-party");
        t.setDaemon(true);
        return t;
    }

    private static final Thread start$lambda$1(Runnable r) {
        Thread t = new Thread(r, "kimiko-party-tx");
        t.setDaemon(true);
        return t;
    }

    private static final void start$lambda$2(PartyClient this$0) {
        this$0.ensureConnected();
    }

    private static final Unit create$lambda$0(String $name, JsonObject o) {
        Intrinsics.checkNotNullParameter((Object)o, (String)"o");
        o.addProperty("name", $name);
        return Unit.INSTANCE;
    }

    private static final Unit inviteUser$lambda$0(String $target, JsonObject o) {
        Intrinsics.checkNotNullParameter((Object)o, (String)"o");
        o.addProperty("target", $target);
        return Unit.INSTANCE;
    }

    private static final Unit respondInvite$lambda$0(String $inviteId, boolean $accept, JsonObject o) {
        Intrinsics.checkNotNullParameter((Object)o, (String)"o");
        o.addProperty("invite", $inviteId);
        o.addProperty("accept", Boolean.valueOf($accept));
        return Unit.INSTANCE;
    }

    private static final Unit sendChat$lambda$0(String $text, JsonObject o) {
        Intrinsics.checkNotNullParameter((Object)o, (String)"o");
        o.addProperty("text", $text);
        return Unit.INSTANCE;
    }

    private static final Unit kick$lambda$0(String $target, JsonObject o) {
        Intrinsics.checkNotNullParameter((Object)o, (String)"o");
        o.addProperty("target", $target);
        return Unit.INSTANCE;
    }

    private static final Unit sendPing$lambda$0(double $x, double $y, double $z, String $dim, long $ttlMs, JsonObject o) {
        Intrinsics.checkNotNullParameter((Object)o, (String)"o");
        o.addProperty("x", (Number)$x);
        o.addProperty("y", (Number)$y);
        o.addProperty("z", (Number)$z);
        o.addProperty("dim", $dim);
        o.addProperty("ttl", (Number)$ttlMs);
        return Unit.INSTANCE;
    }

    private static final Unit sendSpit$lambda$0(double $x, double $y, double $z, double $dx, double $dy, double $dz, String $dim, JsonObject o) {
        Intrinsics.checkNotNullParameter((Object)o, (String)"o");
        o.addProperty("x", (Number)$x);
        o.addProperty("y", (Number)$y);
        o.addProperty("z", (Number)$z);
        o.addProperty("dx", (Number)$dx);
        o.addProperty("dy", (Number)$dy);
        o.addProperty("dz", (Number)$dz);
        o.addProperty("dim", $dim);
        return Unit.INSTANCE;
    }

    private static final Unit setLocalColor$lambda$0(int $color, boolean $rainbow, JsonObject o) {
        Intrinsics.checkNotNullParameter((Object)o, (String)"o");
        o.addProperty("color", (Number)$color);
        o.addProperty("rainbow", Boolean.valueOf($rainbow));
        return Unit.INSTANCE;
    }

    private static final void submitReliable$lambda$0(PartyClient this$0, String $text) {
        WebSocket s = this$0.socket;
        if (s == null || !this$0.connected) {
            return;
        }
        try {
            s.sendText($text, true).get();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private static final void sendVoice$lambda$0(PartyClient this$0, ByteBuffer $buf) {
        this$0.voiceBacklog.decrementAndGet();
        WebSocket s = this$0.socket;
        if (s == null || !this$0.connected) {
            return;
        }
        try {
            s.sendBinary($buf, true).get();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private static final Unit ensureConnected$lambda$0(PartyClient this$0, WebSocket ws, Throwable err) {
        this$0.connecting = false;
        if (err != null) {
            this$0.connected = false;
            this$0.socket = null;
        }
        return Unit.INSTANCE;
    }

    private static final void ensureConnected$lambda$1(Function2 $tmp0, Object p0, Object p1) {
        $tmp0.invoke(p0, p1);
    }

    private static final void handle$lambda$0(PartySnapshot $snap) {
        PartyChat.renderInfo($snap);
    }

    private static final void handle$lambda$1(String $from, String $party, String $id) {
        PartyChat.printInvite($from, $party, $id);
    }

    private static final void handle$lambda$2(String $level, String $msg) {
        PartyChat.printNotice($level, $msg);
    }

    private static final void handle$lambda$3(String $from, String $text) {
        PartyChat.printChat($from, $text);
    }

    private static final void handle$lambda$4(PartySpitSink $sink, String $from, double $x, double $y, double $z, double $dx, double $dy, double $dz, String $dim) {
        $sink.accept($from, $x, $y, $z, $dx, $dy, $dz, $dim);
    }

    private static final void handle$lambda$5(String $msg) {
        PartyChat.printNotice("error", $msg);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ!\u0010\u000f\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000e\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J)\u0010\u0013\u001a\u00020\u00112\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J!\u0010\u0016\u001a\u00020\u00152\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000e\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J#\u0010\u0018\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000e\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019R\u0019\u0010\u001c\u001a\u00020\u001a8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b!\u0010 \u00a8\u0006\""}, d2={"Lrtx/kimiko/api/party/PartyClient.Companion;", "", "<init>", "()V", "", "resolveName", "()Ljava/lang/String;", "Ljava/lang/Runnable;", "r", "", "runGame", "(Ljava/lang/Runnable;)V", "Lcom/google/gson/JsonObject;", "o", "k", "str", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/String;", "", "def", "num", "(Lcom/google/gson/JsonObject;Ljava/lang/String;D)D", "", "bool", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Z", "obj", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonObject;", "Lrtx/kimiko/api/party/PartyClient;", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/party/PartyClient;", "", "MAX_MARKERS", "I", "MAX_VOICE_BACKLOG", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final String resolveName() {
            String profileName = ProfileIdentity.username(null);
            if (profileName != null) {
                return profileName;
            }
            MinecraftClient mc = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)mc, (String)"getInstance(...)");
            try {
                if (mc.player != null) {
                    ClientPlayerEntity clientPlayerEntity2 = mc.player;
                    Intrinsics.checkNotNull((Object)clientPlayerEntity2);
                    if (clientPlayerEntity2.getGameProfile() != null) {
                        ClientPlayerEntity clientPlayerEntity3 = mc.player;
                        Intrinsics.checkNotNull((Object)clientPlayerEntity3);
                        String n = clientPlayerEntity3.getGameProfile().name();
                        if (n != null && !StringsKt.isBlank((CharSequence)n)) {
                            return n;
                        }
                    }
                }
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            try {
                if (mc.getSession() != null && mc.getSession().getUsername() != null) {
                    String username = mc.getSession().getUsername();
                    if (!StringsKt.isBlank((CharSequence)username)) {
                        return username;
                    }
                }
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            return "Player";
        }

        private final void runGame(Runnable r) {
            MinecraftClient.getInstance().execute(r);
        }

        private final String str(JsonObject o, String k) {
            String string;
            if (o != null && o.has(k) && o.get(k).isJsonPrimitive()) {
                String string2 = o.get(k).getAsString();
                string = string2;
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getAsString(...)");
            } else {
                string = "";
            }
            return string;
        }

        private final double num(JsonObject o, String k, double def) {
            double d;
            try {
                d = o != null && o.has(k) && o.get(k).isJsonPrimitive() ? o.get(k).getAsDouble() : def;
            }
            catch (Throwable t) {
                d = def;
            }
            return d;
        }

        private final boolean bool(JsonObject o, String k) {
            boolean bl;
            try {
                bl = o != null && o.has(k) && o.get(k).isJsonPrimitive() && o.get(k).getAsBoolean();
            }
            catch (Throwable t) {
                bl = false;
            }
            return bl;
        }

        private final JsonObject obj(JsonObject o, String k) {
            return o != null && o.has(k) && o.get(k).isJsonObject() ? o.getAsJsonObject(k) : null;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ-\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ-\u0010\u0011\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0012J-\u0010\u0017\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0015H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u001f\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0019H\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001c\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/api/party/PartyClient$Listener;", "Ljava/net/http/WebSocket$Listener;", "<init>", "(Lrtx/kimiko/api/party/PartyClient;)V", "Ljava/net/http/WebSocket;", "ws", "", "onOpen", "(Ljava/net/http/WebSocket;)V", "", "data", "", "last", "Ljava/util/concurrent/CompletionStage;", "onText", "(Ljava/net/http/WebSocket;Ljava/lang/CharSequence;Z)Ljava/util/concurrent/CompletionStage;", "Ljava/nio/ByteBuffer;", "onBinary", "(Ljava/net/http/WebSocket;Ljava/nio/ByteBuffer;Z)Ljava/util/concurrent/CompletionStage;", "", "statusCode", "", "reason", "onClose", "(Ljava/net/http/WebSocket;ILjava/lang/String;)Ljava/util/concurrent/CompletionStage;", "", "error", "onError", "(Ljava/net/http/WebSocket;Ljava/lang/Throwable;)V", "rtx.kimiko:kimiko"})
    private final class Listener
    implements WebSocket.Listener {
        @Override
        public void onOpen(@NotNull WebSocket ws) {
            Intrinsics.checkNotNullParameter((Object)ws, (String)"ws");
            PartyClient.this.socket = ws;
            PartyClient.this.connected = true;
            PartyClient.this.sendHello(ws);
            if (PartyClient.this.hasLocalColor) {
                JsonObject o = new JsonObject();
                o.addProperty("type", "color");
                o.addProperty("color", (Number)PartyClient.this.localColor);
                o.addProperty("rainbow", Boolean.valueOf(PartyClient.this.localRainbow));
                String string = o.toString();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
                PartyClient.this.submitReliable(string);
            }
            ws.request(1L);
        }

        @Override
        @Nullable
        public CompletionStage<?> onText(@NotNull WebSocket ws, @NotNull CharSequence data, boolean last) {
            Intrinsics.checkNotNullParameter((Object)ws, (String)"ws");
            Intrinsics.checkNotNullParameter((Object)data, (String)"data");
            PartyClient.this.rx.append(data);
            if (last) {
                String string = PartyClient.this.rx.toString();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
                String full = string;
                PartyClient.this.rx.setLength(0);
                try {
                    JsonObject jsonObject = JsonParser.parseString((String)full).getAsJsonObject();
                    Intrinsics.checkNotNullExpressionValue((Object)jsonObject, (String)"getAsJsonObject(...)");
                    PartyClient.this.handle(jsonObject);
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
            }
            ws.request(1L);
            return null;
        }

        @Override
        @Nullable
        public CompletionStage<?> onBinary(@NotNull WebSocket ws, @NotNull ByteBuffer data, boolean last) {
            Intrinsics.checkNotNullParameter((Object)ws, (String)"ws");
            Intrinsics.checkNotNullParameter((Object)data, (String)"data");
            try {
                byte[] chunk = new byte[data.remaining()];
                data.get(chunk);
                PartyClient.this.rxBin.write(chunk);
            }
            catch (Throwable chunk) {
                // empty catch block
            }
            if (last) {
                byte[] full = PartyClient.this.rxBin.toByteArray();
                PartyClient.this.rxBin.reset();
                Intrinsics.checkNotNull((Object)full);
                PartyClient.this.handleVoice(full);
            }
            ws.request(1L);
            return null;
        }

        @Override
        @Nullable
        public CompletionStage<?> onClose(@NotNull WebSocket ws, int statusCode, @NotNull String reason) {
            Intrinsics.checkNotNullParameter((Object)ws, (String)"ws");
            Intrinsics.checkNotNullParameter((Object)reason, (String)"reason");
            PartyClient.this.connected = false;
            PartyClient.this.socket = null;
            PartyClient.this.rx.setLength(0);
            PartyClient.this.rxBin.reset();
            return null;
        }

        @Override
        public void onError(@NotNull WebSocket ws, @NotNull Throwable error) {
            Intrinsics.checkNotNullParameter((Object)ws, (String)"ws");
            Intrinsics.checkNotNullParameter((Object)error, (String)"error");
            PartyClient.this.connected = false;
            PartyClient.this.socket = null;
            PartyClient.this.rx.setLength(0);
            PartyClient.this.rxBin.reset();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u00e6\u0080\u0001\u0018\u00002\u00020\u0001JO\u0010\r\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\r\u0010\u000e\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006\u000f\u00c0\u0006\u0001"}, d2={"Lrtx/kimiko/api/party/PartyClient$PartySpitSink;", "", "", "from", "", "x", "y", "z", "dx", "dy", "dz", "dim", "", "accept", "(Ljava/lang/String;DDDDDDLjava/lang/String;)V", "rtx.kimiko:kimiko"})
    public static interface PartySpitSink {
        public void accept(@NotNull String var1, double var2, double var4, double var6, double var8, double var10, double var12, @NotNull String var14);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u00e6\u0080\u0001\u0018\u00002\u00020\u0001J'\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H&\u00a2\u0006\u0004\b\t\u0010\n\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006\u000b\u00c0\u0006\u0001"}, d2={"Lrtx/kimiko/api/party/PartyClient$PartyVoiceSink;", "", "", "from", "", "seq", "", "opus", "", "accept", "(Ljava/lang/String;I[B)V", "rtx.kimiko:kimiko"})
    public static interface PartyVoiceSink {
        public void accept(@NotNull String var1, int var2, @NotNull byte[] var3);
    }
}

