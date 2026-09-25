/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmName
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
package rtx.kimiko.api.cards;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
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
import rtx.kimiko.api.cards.CardsAvatars;
import rtx.kimiko.api.cards.CardsInvite;
import rtx.kimiko.api.cards.CardsLocalGame;
import rtx.kimiko.api.cards.CardsState;
import rtx.kimiko.api.cards.CardsUser;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.utils.net.Endpoints;
import rtx.kimiko.utils.profile.ProfileIdentity;
import rtx.kimiko.utils.storage.RepositoryStorage;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00cc\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 v2\u00020\u0001:\u0004wxyvB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003J\r\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0003J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0010\u0010\tJ\r\u0010\u0011\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0011\u0010\u0003J\r\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u0015\u00a2\u0006\u0004\b\u001a\u0010\u0018J\u0017\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001b\u001a\u00020\u0012\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010 \u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u001b\u001a\u00020\u0012\u00a2\u0006\u0004\b \u0010!J\u001d\u0010$\u001a\u00020\u00042\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\"\u00a2\u0006\u0004\b$\u0010%J\u0017\u0010'\u001a\u00020\u00042\b\u0010#\u001a\u0004\u0018\u00010&\u00a2\u0006\u0004\b'\u0010(J\u0017\u0010)\u001a\u00020\u00042\b\u0010#\u001a\u0004\u0018\u00010&\u00a2\u0006\u0004\b)\u0010(J\u0015\u0010+\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\n\u00a2\u0006\u0004\b+\u0010,J\u001d\u0010/\u001a\u00020\u00072\u0006\u0010-\u001a\u00020\n2\u0006\u0010.\u001a\u00020\u0007\u00a2\u0006\u0004\b/\u00100J\u0015\u00103\u001a\u00020\u00072\u0006\u00102\u001a\u000201\u00a2\u0006\u0004\b3\u00104J\u001d\u00106\u001a\u00020\u00072\u0006\u00105\u001a\u0002012\u0006\u00102\u001a\u000201\u00a2\u0006\u0004\b6\u00107J\u0015\u00108\u001a\u00020\u00072\u0006\u00102\u001a\u000201\u00a2\u0006\u0004\b8\u00104J\r\u00109\u001a\u00020\u0007\u00a2\u0006\u0004\b9\u0010\tJ\r\u0010:\u001a\u00020\u0007\u00a2\u0006\u0004\b:\u0010\tJ\u0015\u0010<\u001a\u00020\u00072\u0006\u0010;\u001a\u000201\u00a2\u0006\u0004\b<\u00104J\r\u0010=\u001a\u00020\u0007\u00a2\u0006\u0004\b=\u0010\tJ\r\u0010>\u001a\u00020\u0007\u00a2\u0006\u0004\b>\u0010\tJ-\u0010C\u001a\u00020\u00072\u0006\u0010?\u001a\u00020\n2\u0014\u0010B\u001a\u0010\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020\u0004\u0018\u00010@H\u0002\u00a2\u0006\u0004\bC\u0010DJ\u0017\u0010F\u001a\u00020\u00072\u0006\u0010E\u001a\u00020\nH\u0002\u00a2\u0006\u0004\bF\u0010,J\u000f\u0010G\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bG\u0010\u0003J\u000f\u0010H\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bH\u0010\u0003J\u000f\u0010I\u001a\u00020\nH\u0002\u00a2\u0006\u0004\bI\u0010\fJ\u0017\u0010K\u001a\u00020\u00042\u0006\u0010J\u001a\u00020AH\u0002\u00a2\u0006\u0004\bK\u0010LR\u0014\u0010N\u001a\u00020M8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bN\u0010OR\u001c\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0P8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000e\u0010QR\u0014\u0010S\u001a\u00020R8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bS\u0010TR\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160U8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010VR \u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00190W8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001a\u0010XR\u001c\u0010Y\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0P8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bY\u0010QR\u001c\u0010Z\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0P8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bZ\u0010QR\u0018\u0010]\u001a\u00060[j\u0002`\\8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b]\u0010^R\u0018\u0010`\u001a\u0004\u0018\u00010_8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b`\u0010aR\u0018\u0010c\u001a\u0004\u0018\u00010b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010dR\u0018\u0010f\u001a\u0004\u0018\u00010e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bf\u0010gR\u0016\u0010h\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bh\u0010iR\u0016\u0010j\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bj\u0010iR\u0018\u0010I\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bI\u0010kR\u0016\u0010l\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bl\u0010kR\u0016\u0010m\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bm\u0010iR\u001e\u0010n\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bn\u0010oR\u0018\u0010p\u001a\u0004\u0018\u00010&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bp\u0010qR\u0018\u0010r\u001a\u0004\u0018\u00010&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\br\u0010qR\u0018\u0010t\u001a\u0004\u0018\u00010s8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bt\u0010u\u00a8\u0006z"}, d2={"Lrtx/kimiko/api/cards/CardsClient;", "", "<init>", "()V", "", "start", "stop", "", "isConnected", "()Z", "", "selfId", "()Ljava/lang/String;", "Lrtx/kimiko/api/cards/CardsState;", "state", "()Lrtx/kimiko/api/cards/CardsState;", "isLocalGame", "startLocalGame", "", "stateVersion", "()J", "", "Lrtx/kimiko/api/cards/CardsUser;", "online", "()Ljava/util/List;", "Lrtx/kimiko/api/cards/CardsInvite;", "invites", "newerThan", "Lrtx/kimiko/api/cards/CardsClient$Emote;", "takeEmote", "(J)Lrtx/kimiko/api/cards/CardsClient$Emote;", "Lrtx/kimiko/api/cards/CardsClient$Notice;", "takeNotice", "(J)Lrtx/kimiko/api/cards/CardsClient$Notice;", "Ljava/util/function/Consumer;", "listener", "setInviteListener", "(Ljava/util/function/Consumer;)V", "Ljava/lang/Runnable;", "setGameStartListener", "(Ljava/lang/Runnable;)V", "setRoomClosedListener", "targetId", "invite", "(Ljava/lang/String;)Z", "inviteId", "accept", "respondInvite", "(Ljava/lang/String;Z)Z", "", "card", "attack", "(I)Z", "slot", "defend", "(II)Z", "transfer", "take", "done", "n", "emote", "rematch", "leaveRoom", "type", "Lkotlin/Function1;", "Lcom/google/gson/JsonObject;", "fill", "send", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Z", "text", "submitReliable", "ensureConnected", "sendHello", "clientId", "root", "handle", "(Lcom/google/gson/JsonObject;)V", "Ljava/net/http/HttpClient;", "http", "Ljava/net/http/HttpClient;", "Ljava/util/concurrent/atomic/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicLong;", "stateVersionRef", "Ljava/util/concurrent/atomic/AtomicLong;", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Ljava/util/concurrent/CopyOnWriteArrayList;", "", "Ljava/util/Map;", "lastEmote", "lastNotice", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "rx", "Ljava/lang/StringBuilder;", "Ljava/util/concurrent/ScheduledExecutorService;", "executor", "Ljava/util/concurrent/ScheduledExecutorService;", "Ljava/util/concurrent/ExecutorService;", "outbound", "Ljava/util/concurrent/ExecutorService;", "Ljava/net/http/WebSocket;", "socket", "Ljava/net/http/WebSocket;", "connected", "Z", "connecting", "Ljava/lang/String;", "lastHelloName", "wasInRoom", "inviteListener", "Ljava/util/function/Consumer;", "gameStartListener", "Ljava/lang/Runnable;", "roomClosedListener", "Lrtx/kimiko/api/cards/CardsLocalGame;", "local", "Lrtx/kimiko/api/cards/CardsLocalGame;", "Companion", "Emote", "Notice", "Listener", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nCardsClient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CardsClient.kt\nrtx/kimiko/api/cards/CardsClient\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,534:1\n1#2:535\n*E\n"})
public final class CardsClient {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final HttpClient http;
    @NotNull
    private final AtomicReference<CardsState> state;
    @NotNull
    private final AtomicLong stateVersionRef;
    @NotNull
    private final CopyOnWriteArrayList<CardsUser> online;
    @NotNull
    private final Map<String, CardsInvite> invites;
    @NotNull
    private final AtomicReference<Emote> lastEmote;
    @NotNull
    private final AtomicReference<Notice> lastNotice;
    @NotNull
    private final StringBuilder rx;
    @Nullable
    private ScheduledExecutorService executor;
    @Nullable
    private volatile ExecutorService outbound;
    @Nullable
    private volatile WebSocket socket;
    private volatile boolean connected;
    private volatile boolean connecting;
    @Nullable
    private volatile String clientId;
    @NotNull
    private volatile String lastHelloName;
    private volatile boolean wasInRoom;
    @Nullable
    private volatile Consumer<CardsInvite> inviteListener;
    @Nullable
    private volatile Runnable gameStartListener;
    @Nullable
    private volatile Runnable roomClosedListener;
    @Nullable
    private volatile CardsLocalGame local;
    @JvmField
    @NotNull
    public static final CardsClient INSTANCE = new CardsClient();

    private CardsClient() {
        HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5L)).build();
        Intrinsics.checkNotNullExpressionValue((Object)httpClient, (String)"build(...)");
        this.http = httpClient;
        this.state = new AtomicReference();
        this.stateVersionRef = new AtomicLong();
        this.online = new CopyOnWriteArrayList();
        this.invites = new ConcurrentHashMap();
        this.lastEmote = new AtomicReference();
        this.lastNotice = new AtomicReference();
        this.rx = new StringBuilder();
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
        this.online.clear();
        this.invites.clear();
        this.state.set(null);
        this.local = null;
        this.stateVersionRef.incrementAndGet();
    }

    public final boolean isConnected() {
        return this.connected;
    }

    @NotNull
    public final String selfId() {
        return this.clientId();
    }

    @Nullable
    public final CardsState state() {
        CardsLocalGame lg = this.local;
        if (lg != null) {
            String notice;
            long now = System.currentTimeMillis();
            lg.tick(now);
            int botEmote = lg.takeBotEmote();
            if (botEmote >= 0) {
                this.lastEmote.set(new Emote("bot", botEmote, now));
            }
            if ((notice = lg.takeLocalNotice()) != null) {
                this.lastNotice.set(new Notice("info", notice, now));
            }
            return lg.state();
        }
        return this.state.get();
    }

    public final boolean isLocalGame() {
        return this.local != null;
    }

    public final void startLocalGame() {
        this.local = new CardsLocalGame(CardsClient.Companion.resolveName());
        this.invites.clear();
        this.send("busy", o -> o.addProperty("value", true));
        this.stateVersionRef.incrementAndGet();
    }

    public final long stateVersion() {
        return this.stateVersionRef.get();
    }

    @NotNull
    public final List<CardsUser> online() {
        return new ArrayList(this.online);
    }

    @NotNull
    public final List<CardsInvite> invites() {
        long now = System.currentTimeMillis();
        this.invites.values().removeIf(it -> it.expired(now));
        return new ArrayList<CardsInvite>(this.invites.values());
    }

    @Nullable
    public final Emote takeEmote(long newerThan) {
        Emote e = this.lastEmote.get();
        return e != null && e.at() > newerThan ? e : null;
    }

    @Nullable
    public final Notice takeNotice(long newerThan) {
        Notice n = this.lastNotice.get();
        return n != null && n.at() > newerThan ? n : null;
    }

    public final void setInviteListener(@Nullable Consumer<CardsInvite> listener) {
        this.inviteListener = listener;
    }

    public final void setGameStartListener(@Nullable Runnable listener) {
        this.gameStartListener = listener;
    }

    public final void setRoomClosedListener(@Nullable Runnable listener) {
        this.roomClosedListener = listener;
    }

    public final boolean invite(@NotNull String targetId) {
        Intrinsics.checkNotNullParameter((Object)targetId, (String)"targetId");
        return this.send("invite", o -> o.addProperty("target", targetId));
    }

    public final boolean respondInvite(@NotNull String inviteId, boolean accept) {
        Intrinsics.checkNotNullParameter((Object)inviteId, (String)"inviteId");
        this.invites.remove(inviteId);
        return this.send("invite_response", o -> {
            o.addProperty("invite", inviteId);
            o.addProperty("accept", accept);
        });
    }

    public final boolean attack(int card) {
        CardsLocalGame lg = this.local;
        if (lg != null) {
            return lg.attack(card);
        }
        return this.send("attack", o -> o.addProperty("card", card));
    }

    public final boolean defend(int slot, int card) {
        CardsLocalGame lg = this.local;
        if (lg != null) {
            return lg.defend(slot, card);
        }
        return this.send("defend", o -> {
            o.addProperty("slot", slot);
            o.addProperty("card", card);
        });
    }

    public final boolean transfer(int card) {
        CardsLocalGame lg = this.local;
        if (lg != null) {
            return lg.transfer(card);
        }
        return this.send("transfer", o -> o.addProperty("card", card));
    }

    public final boolean take() {
        CardsLocalGame lg = this.local;
        if (lg != null) {
            return lg.take();
        }
        return this.send("take", null);
    }

    public final boolean done() {
        CardsLocalGame lg = this.local;
        if (lg != null) {
            return lg.done();
        }
        return this.send("done", null);
    }

    public final boolean emote(int n) {
        CardsLocalGame lg = this.local;
        if (lg != null) {
            lg.onPlayerEmote();
            return true;
        }
        return this.send("emote", o -> o.addProperty("n", n));
    }

    public final boolean rematch() {
        CardsLocalGame lg = this.local;
        if (lg != null) {
            return lg.rematch();
        }
        return this.send("rematch", null);
    }

    public final boolean leaveRoom() {
        if (this.local != null) {
            this.local = null;
            this.send("busy", o -> o.addProperty("value", false));
            this.stateVersionRef.incrementAndGet();
            return true;
        }
        return this.send("leave", null);
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
            ex.execute(() -> CardsClient.submitReliable$lambda$0(this, text));
            bl = true;
        }
        catch (Throwable t) {
            bl = false;
        }
        return bl;
    }

    private final void ensureConnected() {
        if (this.connected) {
            WebSocket s = this.socket;
            if (s != null && !Intrinsics.areEqual((Object)CardsClient.Companion.resolveName(), (Object)this.lastHelloName)) {
                this.sendHello();
            }
            return;
        }
        if (this.connecting || this.socket != null) {
            return;
        }
        this.connecting = true;
        try {
            NetworkPolicy.webSocket(this.http.newWebSocketBuilder().connectTimeout(Duration.ofSeconds(8L)), URI.create(Endpoints.cards()), new Listener()).whenComplete((ws, err) -> {
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

    private final void sendHello() {
        int uid;
        JsonObject o = new JsonObject();
        o.addProperty("type", "hello");
        o.addProperty("id", this.clientId());
        String name = CardsClient.Companion.resolveName();
        o.addProperty("name", name);
        String avatar = CardsAvatars.selfUrl();
        if (avatar != null) {
            o.addProperty("avatar", avatar);
        }
        if ((uid = ProfileIdentity.uid()) > 0) {
            o.addProperty("uid", (Number)uid);
        }
        String string = o.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        if (this.submitReliable(string)) {
            this.lastHelloName = name;
            if (this.local != null) {
                this.send("busy", b -> b.addProperty("value", true));
            }
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

    private final void handle(JsonObject root) {
        block36: {
            String type = root.has("type") && root.get("type").isJsonPrimitive() ? root.get("type").getAsString() : "";
            String string = type;
            if (string == null) break block36;
            int n = -1;
            switch (string.hashCode()) {
                case 247578960: {
                    if (string.equals("room_closed")) {
                        n = 1;
                    }
                    break;
                }
                case 3165170: {
                    if (string.equals("game")) {
                        n = 2;
                    }
                    break;
                }
                case -1247128753: {
                    if (string.equals("rematch_offer")) {
                        n = 3;
                    }
                    break;
                }
                case -1012222381: {
                    if (string.equals("online")) {
                        n = 4;
                    }
                    break;
                }
                case 96633208: {
                    if (string.equals("emote")) {
                        n = 5;
                    }
                    break;
                }
                case -1183699191: {
                    if (string.equals("invite")) {
                        n = 6;
                    }
                    break;
                }
                case 96784904: {
                    if (string.equals("error")) {
                        n = 7;
                    }
                    break;
                }
                case 1233099618: {
                    if (string.equals("welcome")) {
                        n = 4;
                    }
                    break;
                }
                case -1504049131: {
                    if (string.equals("invite_gone")) {
                        n = 8;
                    }
                    break;
                }
                case -1039690024: {
                    if (string.equals("notice")) {
                        n = 7;
                    }
                    break;
                }
            }
            switch (n) {
                case 4: {
                    ArrayList<CardsUser> users = new ArrayList<CardsUser>();
                    if (root.has("users") || root.has("online")) {
                        JsonArray arr = root.has("users") ? root.getAsJsonArray("users") : root.getAsJsonArray("online");
                        Iterator iterator = arr.iterator();
                        Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
                        Iterator iterator2 = iterator;
                        while (iterator2.hasNext()) {
                            JsonElement e = (JsonElement)iterator2.next();
                            JsonObject u = e.getAsJsonObject();
                            String string2 = u.has("id") ? u.get("id").getAsString() : "";
                            Intrinsics.checkNotNull((Object)string2);
                            String string3 = string2;
                            string2 = u.has("name") ? u.get("name").getAsString() : "?";
                            Intrinsics.checkNotNull((Object)string2);
                            String string4 = string2;
                            boolean bl = u.has("game") && u.get("game").getAsBoolean();
                            string2 = u.has("avatar") && u.get("avatar").isJsonPrimitive() ? u.get("avatar").getAsString() : "";
                            Intrinsics.checkNotNull((Object)string2);
                            users.add(new CardsUser(string3, string4, bl, string2));
                        }
                    }
                    this.online.clear();
                    this.online.addAll((Collection<CardsUser>)users);
                    if (Intrinsics.areEqual((Object)"welcome", (Object)type) && (!root.has("room") || root.get("room").isJsonNull())) {
                        this.wasInRoom = false;
                        this.state.set(null);
                    }
                    this.stateVersionRef.incrementAndGet();
                    break;
                }
                case 6: {
                    String id = root.has("invite") ? root.get("invite").getAsString() : "";
                    JsonObject from = root.has("from") && root.get("from").isJsonObject() ? root.getAsJsonObject("from") : null;
                    Intrinsics.checkNotNull((Object)id);
                    if (!(((CharSequence)id).length() > 0) || from == null) break;
                    long ttl = root.has("expires") ? root.get("expires").getAsLong() : 45000L;
                    String string5 = from.has("id") ? from.get("id").getAsString() : "";
                    Intrinsics.checkNotNull((Object)string5);
                    String string6 = string5;
                    string5 = from.has("name") ? from.get("name").getAsString() : "?";
                    Intrinsics.checkNotNull((Object)string5);
                    String string7 = string5;
                    string5 = from.has("avatar") && from.get("avatar").isJsonPrimitive() ? from.get("avatar").getAsString() : "";
                    Intrinsics.checkNotNull((Object)string5);
                    CardsInvite invite = new CardsInvite(id, string6, string7, string5, System.currentTimeMillis() + ttl);
                    this.invites.put(id, invite);
                    this.stateVersionRef.incrementAndGet();
                    Consumer<CardsInvite> listener = this.inviteListener;
                    CardsClient.Companion.runGame(() -> CardsClient.handle$lambda$0(listener, invite));
                    break;
                }
                case 8: {
                    String id = root.has("invite") ? root.get("invite").getAsString() : "";
                    Intrinsics.checkNotNull((Object)id);
                    if (!(((CharSequence)id).length() > 0)) break;
                    this.invites.remove(id);
                    this.stateVersionRef.incrementAndGet();
                    break;
                }
                case 2: {
                    if (this.local != null) {
                        this.send("leave", null);
                        this.lastNotice.set(new Notice("info", I18n.tr("Приглашение отклонено — идёт игра с ботом"), System.currentTimeMillis()));
                        this.stateVersionRef.incrementAndGet();
                        return;
                    }
                    CardsState next = CardsState.Companion.fromJson(root);
                    if (next == null) break;
                    boolean started = !this.wasInRoom;
                    this.wasInRoom = true;
                    this.state.set(next);
                    this.stateVersionRef.incrementAndGet();
                    if (!started) break;
                    Runnable listener = this.gameStartListener;
                    CardsClient.Companion.runGame(() -> CardsClient.handle$lambda$1(listener));
                    break;
                }
                case 1: {
                    this.wasInRoom = false;
                    this.state.set(null);
                    this.stateVersionRef.incrementAndGet();
                    Runnable listener = this.roomClosedListener;
                    CardsClient.Companion.runGame(() -> CardsClient.handle$lambda$2(listener));
                    break;
                }
                case 5: {
                    int n2 = root.has("n") ? root.get("n").getAsInt() : 0;
                    String from = root.has("from") ? root.get("from").getAsString() : "";
                    Intrinsics.checkNotNull((Object)from);
                    this.lastEmote.set(new Emote(from, n2, System.currentTimeMillis()));
                    this.stateVersionRef.incrementAndGet();
                    break;
                }
                case 3: {
                    this.lastNotice.set(new Notice("info", I18n.tr("Соперник предлагает реванш!"), System.currentTimeMillis()));
                    this.stateVersionRef.incrementAndGet();
                    break;
                }
                case 7: {
                    String msg = root.has("message") ? root.get("message").getAsString() : "";
                    String level = root.has("level") ? root.get("level").getAsString() : "error";
                    Intrinsics.checkNotNull((Object)msg);
                    if (!(((CharSequence)msg).length() > 0)) break;
                    Intrinsics.checkNotNull((Object)level);
                    this.lastNotice.set(new Notice(level, msg, System.currentTimeMillis()));
                    this.stateVersionRef.incrementAndGet();
                }
            }
        }
    }

    private static final Thread start$lambda$0(Runnable r) {
        Thread t = new Thread(r, "kimiko-cards");
        t.setDaemon(true);
        return t;
    }

    private static final Thread start$lambda$1(Runnable r) {
        Thread t = new Thread(r, "kimiko-cards-tx");
        t.setDaemon(true);
        return t;
    }

    private static final void start$lambda$2(CardsClient this$0) {
        this$0.ensureConnected();
    }

    private static final Unit startLocalGame$lambda$0(JsonObject o) {
        Intrinsics.checkNotNullParameter((Object)o, (String)"o");
        o.addProperty("value", Boolean.valueOf(true));
        return Unit.INSTANCE;
    }

    private static final boolean invites$lambda$0(long $now, CardsInvite it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        return it.expired($now);
    }

    private static final boolean invites$lambda$1(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final Unit invite$lambda$0(String $targetId, JsonObject o) {
        Intrinsics.checkNotNullParameter((Object)o, (String)"o");
        o.addProperty("target", $targetId);
        return Unit.INSTANCE;
    }

    private static final Unit respondInvite$lambda$0(String $inviteId, boolean $accept, JsonObject o) {
        Intrinsics.checkNotNullParameter((Object)o, (String)"o");
        o.addProperty("invite", $inviteId);
        o.addProperty("accept", Boolean.valueOf($accept));
        return Unit.INSTANCE;
    }

    private static final Unit attack$lambda$0(int $card, JsonObject o) {
        Intrinsics.checkNotNullParameter((Object)o, (String)"o");
        o.addProperty("card", (Number)$card);
        return Unit.INSTANCE;
    }

    private static final Unit defend$lambda$0(int $slot, int $card, JsonObject o) {
        Intrinsics.checkNotNullParameter((Object)o, (String)"o");
        o.addProperty("slot", (Number)$slot);
        o.addProperty("card", (Number)$card);
        return Unit.INSTANCE;
    }

    private static final Unit transfer$lambda$0(int $card, JsonObject o) {
        Intrinsics.checkNotNullParameter((Object)o, (String)"o");
        o.addProperty("card", (Number)$card);
        return Unit.INSTANCE;
    }

    private static final Unit emote$lambda$0(int $n, JsonObject o) {
        Intrinsics.checkNotNullParameter((Object)o, (String)"o");
        o.addProperty("n", (Number)$n);
        return Unit.INSTANCE;
    }

    private static final Unit leaveRoom$lambda$0(JsonObject o) {
        Intrinsics.checkNotNullParameter((Object)o, (String)"o");
        o.addProperty("value", Boolean.valueOf(false));
        return Unit.INSTANCE;
    }

    private static final void submitReliable$lambda$0(CardsClient this$0, String $text) {
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

    private static final Unit ensureConnected$lambda$0(CardsClient this$0, WebSocket ws, Throwable err) {
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

    private static final Unit sendHello$lambda$0(JsonObject b) {
        Intrinsics.checkNotNullParameter((Object)b, (String)"b");
        b.addProperty("value", Boolean.valueOf(true));
        return Unit.INSTANCE;
    }

    private static final void handle$lambda$0(Consumer $listener, CardsInvite $invite) {
        block0: {
            Consumer consumer = $listener;
            if (consumer == null) break block0;
            consumer.accept($invite);
        }
    }

    private static final void handle$lambda$1(Runnable $listener) {
        block0: {
            Runnable runnable = $listener;
            if (runnable == null) break block0;
            runnable.run();
        }
    }

    private static final void handle$lambda$2(Runnable $listener) {
        block0: {
            Runnable runnable = $listener;
            if (runnable == null) break block0;
            runnable.run();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bR\u0019\u0010\u000e\u001a\u00020\f8\u0006X\u0087\u0004\u0092\u0002\u0002\b\r\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/api/cards/CardsClient.Companion;", "", "<init>", "()V", "", "resolveName", "()Ljava/lang/String;", "Ljava/lang/Runnable;", "r", "", "runGame", "(Ljava/lang/Runnable;)V", "Lrtx/kimiko/api/cards/CardsClient;", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/cards/CardsClient;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final String resolveName() {
            String profileName = ProfileIdentity.username(null);
            if (profileName != null) {
                return profileName;
            }
            MinecraftClient mc = MinecraftClient.getInstance();
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
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient mc = minecraftClient2;
            mc.execute(r);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ.\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00c6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u0016\u001a\u00020\u0004H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\rJ\u0011\u0010\u0017\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0017\u0010\u000bR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u0003\u0010\u000bR%\u0010\u0005\u001a\u00020\u00048\u0007z\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001b\u001a\u0004\b\u0005\u0010\rR%\u0010\u0007\u001a\u00020\u00068\u0007z\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001c\u001a\u0004\b\u0007\u0010\u000f\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/api/cards/CardsClient$Emote;", "", "", "from", "", "n", "", "at", "<init>", "(Ljava/lang/String;IJ)V", "component1", "()Ljava/lang/String;", "component2", "()I", "component3", "()J", "copy", "(Ljava/lang/String;IJ)Lrtx/kimiko/api/cards/CardsClient$Emote;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "Lkotlin/jvm/JvmName;", "name", "Ljava/lang/String;", "I", "J", "rtx.kimiko:kimiko"})
    public static final class Emote {
        @NotNull
        private final String from;
        private final int n;
        private final long at;

        public Emote(@NotNull String from, int n, long at) {
            Intrinsics.checkNotNullParameter((Object)from, (String)"from");
            this.from = from;
            this.n = n;
            this.at = at;
        }

        @JvmName(name="from")
        @NotNull
        public final String from() {
            return this.from;
        }

        @JvmName(name="n")
        public final int n() {
            return this.n;
        }

        @JvmName(name="at")
        public final long at() {
            return this.at;
        }

        @NotNull
        public final String component1() {
            return this.from;
        }

        public final int component2() {
            return this.n;
        }

        public final long component3() {
            return this.at;
        }

        @NotNull
        public final Emote copy(@NotNull String from, int n, long at) {
            Intrinsics.checkNotNullParameter((Object)from, (String)"from");
            return new Emote(from, n, at);
        }

        public static /* synthetic */ Emote copy$default(Emote emote, String string, int n, long l, int n2, Object object) {
            if ((n2 & 1) != 0) {
                string = emote.from;
            }
            if ((n2 & 2) != 0) {
                n = emote.n;
            }
            if ((n2 & 4) != 0) {
                l = emote.at;
            }
            return emote.copy(string, n, l);
        }

        @NotNull
        public String toString() {
            return "Emote(from=" + this.from + ", n=" + this.n + ", at=" + this.at + ")";
        }

        public int hashCode() {
            int result = this.from.hashCode();
            result = result * 31 + Integer.hashCode(this.n);
            result = result * 31 + Long.hashCode(this.at);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Emote)) {
                return false;
            }
            Emote emote = (Emote)other;
            if (!Intrinsics.areEqual((Object)this.from, (Object)emote.from)) {
                return false;
            }
            if (this.n != emote.n) {
                return false;
            }
            return this.at == emote.at;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ-\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ-\u0010\u0014\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0016H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/cards/CardsClient$Listener;", "Ljava/net/http/WebSocket$Listener;", "<init>", "(Lrtx/kimiko/api/cards/CardsClient;)V", "Ljava/net/http/WebSocket;", "ws", "", "onOpen", "(Ljava/net/http/WebSocket;)V", "", "data", "", "last", "Ljava/util/concurrent/CompletionStage;", "onText", "(Ljava/net/http/WebSocket;Ljava/lang/CharSequence;Z)Ljava/util/concurrent/CompletionStage;", "", "statusCode", "", "reason", "onClose", "(Ljava/net/http/WebSocket;ILjava/lang/String;)Ljava/util/concurrent/CompletionStage;", "", "error", "onError", "(Ljava/net/http/WebSocket;Ljava/lang/Throwable;)V", "rtx.kimiko:kimiko"})
    private final class Listener
    implements WebSocket.Listener {
        @Override
        public void onOpen(@NotNull WebSocket ws) {
            Intrinsics.checkNotNullParameter((Object)ws, (String)"ws");
            CardsClient.this.socket = ws;
            CardsClient.this.connected = true;
            CardsClient.this.sendHello();
            ws.request(1L);
        }

        @Override
        @Nullable
        public CompletionStage<?> onText(@NotNull WebSocket ws, @NotNull CharSequence data, boolean last) {
            Intrinsics.checkNotNullParameter((Object)ws, (String)"ws");
            Intrinsics.checkNotNullParameter((Object)data, (String)"data");
            CardsClient.this.rx.append(data);
            if (last) {
                String string = CardsClient.this.rx.toString();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
                String full = string;
                CardsClient.this.rx.setLength(0);
                try {
                    JsonObject jsonObject = JsonParser.parseString((String)full).getAsJsonObject();
                    Intrinsics.checkNotNullExpressionValue((Object)jsonObject, (String)"getAsJsonObject(...)");
                    CardsClient.this.handle(jsonObject);
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
        public CompletionStage<?> onClose(@NotNull WebSocket ws, int statusCode, @NotNull String reason) {
            Intrinsics.checkNotNullParameter((Object)ws, (String)"ws");
            Intrinsics.checkNotNullParameter((Object)reason, (String)"reason");
            CardsClient.this.connected = false;
            CardsClient.this.socket = null;
            CardsClient.this.rx.setLength(0);
            return null;
        }

        @Override
        public void onError(@NotNull WebSocket ws, @NotNull Throwable error) {
            Intrinsics.checkNotNullParameter((Object)ws, (String)"ws");
            Intrinsics.checkNotNullParameter((Object)error, (String)"error");
            CardsClient.this.connected = false;
            CardsClient.this.socket = null;
            CardsClient.this.rx.setLength(0);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\nJ\u0010\u0010\f\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ.\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00c6\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0015\u001a\u00020\u0014H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0011\u0010\u0017\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0017\u0010\nR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u0003\u0010\nR%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001a\u001a\u0004\b\u0004\u0010\nR%\u0010\u0006\u001a\u00020\u00058\u0007z\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001b\u001a\u0004\b\u0006\u0010\r\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/api/cards/CardsClient$Notice;", "", "", "level", "message", "", "at", "<init>", "(Ljava/lang/String;Ljava/lang/String;J)V", "component1", "()Ljava/lang/String;", "component2", "component3", "()J", "copy", "(Ljava/lang/String;Ljava/lang/String;J)Lrtx/kimiko/api/cards/CardsClient$Notice;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Lkotlin/jvm/JvmName;", "name", "Ljava/lang/String;", "J", "rtx.kimiko:kimiko"})
    public static final class Notice {
        @NotNull
        private final String level;
        @NotNull
        private final String message;
        private final long at;

        public Notice(@NotNull String level, @NotNull String message, long at) {
            Intrinsics.checkNotNullParameter((Object)level, (String)"level");
            Intrinsics.checkNotNullParameter((Object)message, (String)"message");
            this.level = level;
            this.message = message;
            this.at = at;
        }

        @JvmName(name="level")
        @NotNull
        public final String level() {
            return this.level;
        }

        @JvmName(name="message")
        @NotNull
        public final String message() {
            return this.message;
        }

        @JvmName(name="at")
        public final long at() {
            return this.at;
        }

        @NotNull
        public final String component1() {
            return this.level;
        }

        @NotNull
        public final String component2() {
            return this.message;
        }

        public final long component3() {
            return this.at;
        }

        @NotNull
        public final Notice copy(@NotNull String level, @NotNull String message, long at) {
            Intrinsics.checkNotNullParameter((Object)level, (String)"level");
            Intrinsics.checkNotNullParameter((Object)message, (String)"message");
            return new Notice(level, message, at);
        }

        public static /* synthetic */ Notice copy$default(Notice notice, String string, String string2, long l, int n, Object object) {
            if ((n & 1) != 0) {
                string = notice.level;
            }
            if ((n & 2) != 0) {
                string2 = notice.message;
            }
            if ((n & 4) != 0) {
                l = notice.at;
            }
            return notice.copy(string, string2, l);
        }

        @NotNull
        public String toString() {
            return "Notice(level=" + this.level + ", message=" + this.message + ", at=" + this.at + ")";
        }

        public int hashCode() {
            int result = this.level.hashCode();
            result = result * 31 + this.message.hashCode();
            result = result * 31 + Long.hashCode(this.at);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Notice)) {
                return false;
            }
            Notice notice = (Notice)other;
            if (!Intrinsics.areEqual((Object)this.level, (Object)notice.level)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.message, (Object)notice.message)) {
                return false;
            }
            return this.at == notice.at;
        }
    }
}

