/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.session.Session
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.chat.messenger;

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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.session.Session;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import recovery.privacy.NetworkPolicy;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.notifications.Notifications;
import rtx.kimiko.utils.net.Endpoints;
import rtx.kimiko.utils.profile.ProfileIdentity;
import rtx.kimiko.utils.storage.RepositoryStorage;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u001e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u008e\u00012\u00020\u0001:\b\u008f\u0001\u0090\u0001\u0091\u0001\u008e\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003J\r\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0003J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0007\u00a2\u0006\u0004\b\n\u0010\tJ\r\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0007\u00a2\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0012\u001a\u00020\u0011\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0013\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001a\u001a\u00020\u00042\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00112\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0019\u0010\u001f\u001a\u00020\u00042\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0002\u00a2\u0006\u0004\b\u001f\u0010\u001bJ\u0019\u0010!\u001a\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010\u0018H\u0002\u00a2\u0006\u0004\b!\u0010\"J\u000f\u0010#\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b#\u0010\u0003J\u0017\u0010%\u001a\u0004\u0018\u00010\u00152\u0006\u0010$\u001a\u00020\u0011\u00a2\u0006\u0004\b%\u0010&J\u001f\u0010(\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00112\b\u0010'\u001a\u0004\u0018\u00010\u0018\u00a2\u0006\u0004\b(\u0010\u001eJ\u001d\u0010+\u001a\u00020\u00042\u000e\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010)\u00a2\u0006\u0004\b+\u0010,J\u001d\u0010-\u001a\u00020\u00042\u000e\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010)\u00a2\u0006\u0004\b-\u0010,J)\u0010/\u001a\u00020\u00072\b\u0010.\u001a\u0004\u0018\u00010\u00182\u0006\u0010$\u001a\u00020\u00112\u0006\u0010'\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b/\u00100J!\u00101\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00112\b\u0010.\u001a\u0004\u0018\u00010\u0018H\u0002\u00a2\u0006\u0004\b1\u00102J\u001f\u00104\u001a\u00020\u00072\u0006\u00103\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b4\u00105J\r\u00106\u001a\u00020\u0018\u00a2\u0006\u0004\b6\u00107J\u000f\u00108\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b8\u0010\u0003J\u0019\u00109\u001a\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010\u0018H\u0002\u00a2\u0006\u0004\b9\u0010\"J\u0017\u0010<\u001a\u00020\u00042\u0006\u0010;\u001a\u00020:H\u0002\u00a2\u0006\u0004\b<\u0010=J\u0017\u0010>\u001a\u00020\u00072\u0006\u0010;\u001a\u00020:H\u0002\u00a2\u0006\u0004\b>\u0010?J%\u0010B\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00182\u0006\u0010@\u001a\u00020\u000b2\u0006\u0010A\u001a\u00020\u0018\u00a2\u0006\u0004\bB\u0010CJ\r\u0010D\u001a\u00020\u0018\u00a2\u0006\u0004\bD\u00107J\u0017\u0010E\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u0018\u00a2\u0006\u0004\bE\u0010\u001bJ\u000f\u0010F\u001a\u00020:H\u0002\u00a2\u0006\u0004\bF\u0010GJ)\u0010K\u001a\u00020\u00042\u0006\u0010H\u001a\u00020:2\u0006\u0010I\u001a\u00020\u000b2\b\u0010J\u001a\u0004\u0018\u00010\u0018H\u0002\u00a2\u0006\u0004\bK\u0010LJ;\u0010P\u001a\u00020\u00042\u0006\u0010M\u001a\u00020\u00182\u0006\u0010H\u001a\u00020:2\u001a\u0010O\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0004\u0018\u00010NH\u0002\u00a2\u0006\u0004\bP\u0010QJ3\u0010S\u001a\u00020\u00042\b\u0010R\u001a\u0004\u0018\u00010\u00182\u001a\u0010O\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0004\u0018\u00010N\u00a2\u0006\u0004\bS\u0010TJU\u0010X\u001a\u00020\u00042\u0006\u0010U\u001a\u00020\u00182\u0006\u0010I\u001a\u00020\u000b2\b\u0010J\u001a\u0004\u0018\u00010\u00182\u0006\u0010V\u001a\u00020\u000b2\b\u0010W\u001a\u0004\u0018\u00010\u00182\u001a\u0010O\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0004\u0018\u00010N\u00a2\u0006\u0004\bX\u0010YJ1\u0010Z\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00112\u001a\u0010O\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0004\u0018\u00010N\u00a2\u0006\u0004\bZ\u0010[J;\u0010\\\u001a\u00020\u00042\u0006\u0010I\u001a\u00020\u000b2\b\u0010J\u001a\u0004\u0018\u00010\u00182\u001a\u0010O\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0004\u0018\u00010N\u00a2\u0006\u0004\b\\\u0010]JC\u0010_\u001a\u00020\u00042\u0006\u0010I\u001a\u00020\u000b2\b\u0010J\u001a\u0004\u0018\u00010\u00182\u0006\u0010^\u001a\u00020\u00182\u001a\u0010O\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0004\u0018\u00010N\u00a2\u0006\u0004\b_\u0010`JK\u0010c\u001a\u00020\u00042\u0006\u0010I\u001a\u00020\u000b2\b\u0010J\u001a\u0004\u0018\u00010\u00182\u0006\u0010a\u001a\u00020\u00182\u0006\u0010b\u001a\u00020\u00072\u001a\u0010O\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0004\u0018\u00010N\u00a2\u0006\u0004\bc\u0010dJ1\u0010e\u001a\u00020\u00042\u0006\u0010a\u001a\u00020\u00182\u001a\u0010O\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0004\u0018\u00010N\u00a2\u0006\u0004\be\u0010TJ\u001f\u0010g\u001a\u00020f2\u0006\u0010M\u001a\u00020\u00182\u0006\u0010H\u001a\u00020:H\u0002\u00a2\u0006\u0004\bg\u0010hJ\r\u0010j\u001a\u00020i\u00a2\u0006\u0004\bj\u0010kJ\r\u0010^\u001a\u00020\u0018\u00a2\u0006\u0004\b^\u00107J\r\u0010l\u001a\u00020\u0007\u00a2\u0006\u0004\bl\u0010\tJ\r\u0010m\u001a\u00020\u0011\u00a2\u0006\u0004\bm\u0010\u0013J\r\u0010n\u001a\u00020\u0011\u00a2\u0006\u0004\bn\u0010\u0013J\r\u0010o\u001a\u00020\u0007\u00a2\u0006\u0004\bo\u0010\tJ\u0017\u0010q\u001a\u00020\u00112\u0006\u0010p\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\bq\u0010rR\u0014\u0010t\u001a\u00020s8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bt\u0010uR\u0014\u0010w\u001a\u00020v8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bw\u0010xR$\u0010{\u001a\u0012\u0012\u0004\u0012\u00020\u00150yj\b\u0012\u0004\u0012\u00020\u0015`z8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b{\u0010|R\u0014\u0010\u0012\u001a\u00020v8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010xR\u001a\u0010~\u001a\b\u0012\u0004\u0012\u00020\u00180}8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b~\u0010\u007fR'\u0010\u0082\u0001\u001a\u0012\u0012\r\u0012\u000b \u0081\u0001*\u0004\u0018\u00010i0i0\u0080\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0017\u0010D\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\bD\u0010\u0084\u0001R\u0019\u0010\u0085\u0001\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0085\u0001\u0010\u0086\u0001R\u0019\u0010\u0087\u0001\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0087\u0001\u0010\u0086\u0001R\u001c\u0010\u0089\u0001\u001a\u0005\u0018\u00010\u0088\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0089\u0001\u0010\u008a\u0001R\u0019\u0010\u008b\u0001\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008b\u0001\u0010\u0086\u0001R\u0017\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\f\u0010\u008c\u0001R\u0019\u0010\u008d\u0001\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008d\u0001\u0010\u0086\u0001\u00a8\u0006\u0092\u0001"}, d2={"Lrtx/kimiko/api/chat/messenger/MessengerClient;", "", "<init>", "()V", "", "start", "stop", "", "isRunning", "()Z", "isConnected", "", "online", "()I", "value", "setChatOpen", "(Z)V", "", "version", "()J", "", "Lrtx/kimiko/api/chat/messenger/MessengerClient$Message;", "snapshot", "()Ljava/util/List;", "", "text", "send", "(Ljava/lang/String;)V", "replyTo", "sendReply", "(JLjava/lang/String;)V", "transmit", "name", "isBlocked", "(Ljava/lang/String;)Z", "ensureBlockedLoaded", "id", "byId", "(J)Lrtx/kimiko/api/chat/messenger/MessengerClient$Message;", "newText", "edit", "", "ids", "deleteForAll", "(Ljava/util/Collection;)V", "deleteLocal", "author", "applyEdit", "(Ljava/lang/String;JLjava/lang/String;)Z", "removeById", "(JLjava/lang/String;)Z", "user", "handleControl", "(Ljava/lang/String;Ljava/lang/String;)Z", "selfName", "()Ljava/lang/String;", "poll", "isMe", "Lcom/google/gson/JsonObject;", "root", "readSelf", "(Lcom/google/gson/JsonObject;)V", "applyRemoteDeletions", "(Lcom/google/gson/JsonObject;)Z", "durationMs", "peaks", "sendVoice", "(Ljava/lang/String;ILjava/lang/String;)V", "staffKey", "setStaffKey", "credentials", "()Lcom/google/gson/JsonObject;", "body", "targetUid", "targetName", "target", "(Lcom/google/gson/JsonObject;ILjava/lang/String;)V", "path", "Lkotlin/Function2;", "onDone", "call", "(Ljava/lang/String;Lcom/google/gson/JsonObject;Lkotlin/jvm/functions/Function2;)V", "key", "authStaff", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;)V", "action", "minutes", "reason", "moderate", "(Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;Lkotlin/jvm/functions/Function2;)V", "deleteForEveryone", "(JLkotlin/jvm/functions/Function2;)V", "purgeUser", "(ILjava/lang/String;Lkotlin/jvm/functions/Function2;)V", "role", "assignRole", "(ILjava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function2;)V", "prefixId", "revoke", "assignPrefix", "(ILjava/lang/String;Ljava/lang/String;ZLkotlin/jvm/functions/Function2;)V", "choosePrefix", "Lrtx/kimiko/api/chat/messenger/MessengerClient$Result;", "post", "(Ljava/lang/String;Lcom/google/gson/JsonObject;)Lrtx/kimiko/api/chat/messenger/MessengerClient$Result;", "Lrtx/kimiko/api/chat/messenger/MessengerClient$SelfState;", "state", "()Lrtx/kimiko/api/chat/messenger/MessengerClient$SelfState;", "isStaff", "muteLeft", "voiceMuteLeft", "isBanned", "until", "remaining", "(J)J", "Ljava/net/http/HttpClient;", "httpClient", "Ljava/net/http/HttpClient;", "Ljava/util/concurrent/atomic/AtomicLong;", "lastId", "Ljava/util/concurrent/atomic/AtomicLong;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "messages", "Ljava/util/ArrayList;", "", "blocked", "Ljava/util/Set;", "Ljava/util/concurrent/atomic/AtomicReference;", "kotlin.jvm.PlatformType", "self", "Ljava/util/concurrent/atomic/AtomicReference;", "Ljava/lang/String;", "staffKeyLoaded", "Z", "blockedLoaded", "Ljava/util/concurrent/ScheduledExecutorService;", "executor", "Ljava/util/concurrent/ScheduledExecutorService;", "connected", "I", "chatOpen", "Companion", "SelfState", "Message", "Result", "rtx.kimiko:kimiko"})
public final class MessengerClient {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final HttpClient httpClient;
    @NotNull
    private final AtomicLong lastId;
    @NotNull
    private final ArrayList<Message> messages;
    @NotNull
    private final AtomicLong version;
    @NotNull
    private final Set<String> blocked;
    @NotNull
    private final AtomicReference<SelfState> self;
    @NotNull
    private volatile String staffKey;
    private volatile boolean staffKeyLoaded;
    private volatile boolean blockedLoaded;
    @Nullable
    private ScheduledExecutorService executor;
    private volatile boolean connected;
    private volatile int online;
    private volatile boolean chatOpen;
    @JvmField
    @NotNull
    public static final MessengerClient INSTANCE = new MessengerClient();
    private static final long POLL_MS = 350L;
    private static final int MAX_MESSAGES = 200;
    @NotNull
    private static final String CTRL_EDIT = "@kmsg:edit:";
    @NotNull
    private static final String CTRL_DEL = "@kmsg:del:";
    @NotNull
    private static final String CTRL_REPLY = "@kmsg:re:";
    @NotNull
    private static final String STORE = "irc";

    private MessengerClient() {
        HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(1L)).build();
        Intrinsics.checkNotNullExpressionValue((Object)httpClient, (String)"build(...)");
        this.httpClient = httpClient;
        this.lastId = new AtomicLong(0L);
        this.messages = new ArrayList();
        this.version = new AtomicLong(0L);
        ConcurrentHashMap.KeySetView keySetView = ConcurrentHashMap.newKeySet();
        Intrinsics.checkNotNullExpressionValue(keySetView, (String)"newKeySet(...)");
        this.blocked = keySetView;
        this.self = new AtomicReference<SelfState>(SelfState.NONE);
        this.staffKey = "";
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
        this.connected = false;
        this.online = 0;
    }

    public final boolean isRunning() {
        return this.executor != null;
    }

    public final boolean isConnected() {
        return this.connected;
    }

    public final int online() {
        return this.online;
    }

    public final void setChatOpen(boolean value) {
        this.chatOpen = value;
    }

    public final long version() {
        return this.version.get();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public final List<Message> snapshot() {
        ArrayList<Message> arrayList = this.messages;
        synchronized (arrayList) {
            boolean bl = false;
            List list = new ArrayList(this.messages);
            return list;
        }
    }

    public final void send(@Nullable String text) {
        if (text == null || StringsKt.isBlank((CharSequence)text)) {
            return;
        }
        this.transmit(((Object)StringsKt.trim((CharSequence)text)).toString());
    }

    public final void sendReply(long replyTo, @Nullable String text) {
        if (text == null || StringsKt.isBlank((CharSequence)text)) {
            return;
        }
        if (replyTo <= 0L) {
            this.send(text);
            return;
        }
        this.transmit(CTRL_REPLY + replyTo + ":" + ((Object)StringsKt.trim((CharSequence)text)).toString());
    }

    private final void transmit(String text) {
        if (text == null || StringsKt.isBlank((CharSequence)text)) {
            return;
        }
        String name = this.selfName();
        Thread sender = new Thread(() -> MessengerClient.transmit$lambda$0(name, text, this), "kimiko-messenger-send");
        sender.setDaemon(true);
        sender.start();
    }

    private final boolean isBlocked(String name) {
        if (name == null || StringsKt.isBlank((CharSequence)name)) {
            return false;
        }
        this.ensureBlockedLoaded();
        String string = ((Object)StringsKt.trim((CharSequence)name)).toString().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toLowerCase(...)");
        return this.blocked.contains(string);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void ensureBlockedLoaded() {
        if (this.blockedLoaded) {
            return;
        }
        Set<String> set = this.blocked;
        synchronized (set) {
            boolean bl = false;
            if (this.blockedLoaded) {
                return;
            }
            try {
                JsonObject obj = RepositoryStorage.readObject(STORE);
                if (obj.has("blocked") && obj.get("blocked").isJsonArray()) {
                    for (JsonElement element : obj.getAsJsonArray("blocked")) {
                        if (!element.isJsonPrimitive()) continue;
                        String name = element.getAsString().trim().toLowerCase(Locale.ROOT);
                        if (name.length() > 0) {
                            this.blocked.add(name);
                        }
                    }
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            this.blockedLoaded = true;
            Unit unit = Unit.INSTANCE;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Nullable
    public final Message byId(long id) {
        synchronized (this.messages) {
            for (Message message : this.messages) {
                if (message.id() == id) {
                    return message;
                }
            }
        }
        return null;
    }

    public final void edit(long id, @Nullable String newText) {
        if (newText == null || StringsKt.isBlank((CharSequence)newText)) {
            return;
        }
        String trimmed = ((Object)StringsKt.trim((CharSequence)newText)).toString();
        if (!this.applyEdit(this.selfName(), id, trimmed)) {
            return;
        }
        this.version.incrementAndGet();
        this.transmit(CTRL_EDIT + id + ":" + trimmed);
    }

    public final void deleteForAll(@Nullable Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        Iterator<Long> iterator = ids.iterator();
        while (iterator.hasNext()) {
            long id = ((Number)iterator.next()).longValue();
            if (!this.removeById(id, this.selfName())) continue;
            this.transmit(CTRL_DEL + id);
        }
        this.version.incrementAndGet();
    }

    public final void deleteLocal(@Nullable Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        Iterator<Long> iterator = ids.iterator();
        while (iterator.hasNext()) {
            long id = ((Number)iterator.next()).longValue();
            this.removeById(id, null);
        }
        this.version.incrementAndGet();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private final boolean applyEdit(String author, long id, String newText) {
        synchronized (this.messages) {
            for (Message message : this.messages) {
                if (message.id() == id) {
                    if (author != null && !StringsKt.equals(message.user(), author, true)) {
                        return false;
                    }
                    message.setText(newText);
                    return true;
                }
            }
            return false;
        }
    }

    private final boolean removeById(long id, String author) {
        synchronized (this.messages) {
            for (int i = 0; i < this.messages.size(); i++) {
                Message message = this.messages.get(i);
                if (message.id() == id) {
                    if (author != null && !StringsKt.equals(message.user(), author, true)) {
                        return false;
                    }
                    this.messages.remove(i);
                    return true;
                }
            }
            return false;
        }
    }

    private final boolean handleControl(String user, String text) {
        if (String.valueOf(text).startsWith(CTRL_EDIT)) {
            String string = text.substring(11);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            String payload = string;
            int split = String.valueOf(payload).indexOf((char)':');
            if (split > 0) {
                try {
                    String string2 = payload.substring(0, split);
                    Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
                    long id = Long.parseLong(string2);
                    String string3 = payload.substring(split + 1);
                    Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"substring(...)");
                    String newText = string3;
                    if (!StringsKt.isBlank((CharSequence)newText) && this.applyEdit(user, id, newText)) {
                        this.version.incrementAndGet();
                    }
                }
                catch (NumberFormatException numberFormatException) {
                    // empty catch block
                }
            }
            return true;
        }
        if (String.valueOf(text).startsWith(CTRL_DEL)) {
            try {
                String string = text.substring(10);
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
                long id = Long.parseLong(((Object)StringsKt.trim((CharSequence)string)).toString());
                if (this.removeById(id, user)) {
                    this.version.incrementAndGet();
                }
            }
            catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
            return true;
        }
        return false;
    }

    @NotNull
    public final String selfName() {
        String profileName = ProfileIdentity.username(null);
        if (profileName != null) {
            return profileName;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        Session session2 = mc.getSession();
        Intrinsics.checkNotNullExpressionValue((Object)session2, (String)"getUser(...)");
        Session user = session2;
        if (user.getUsername() != null) {
            String string = user.getUsername();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getName(...)");
            if (!StringsKt.isBlank((CharSequence)string)) {
                String string2 = user.getUsername();
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getName(...)");
                return string2;
            }
        }
        return "Player";
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void poll() {
        try {
            JsonElement array;
            String me = URLEncoder.encode(this.selfName(), StandardCharsets.UTF_8);
            int uid = ProfileIdentity.uid();
            URI uri = URI.create(Endpoints.irc() + "/api/messages?since=" + this.lastId.get() + "&me=" + me + (String)(uid > 0 ? "&uid=" + uid : "") + (this.chatOpen ? "&chat=1" : ""));
            HttpRequest request = HttpRequest.newBuilder(uri).timeout(Duration.ofSeconds(2L)).header("Accept", "application/json").GET().build();
            HttpResponse<String> response = NetworkPolicy.send(this.httpClient, request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                this.connected = false;
                return;
            }
            JsonObject root = JsonParser.parseString((String)response.body()).getAsJsonObject();
            this.connected = true;
            Intrinsics.checkNotNull((Object)root);
            this.online = (int)MessengerClient.Companion.number(root, "online", 0L);
            this.readSelf(root);
            if (this.applyRemoteDeletions(root)) {
                this.version.incrementAndGet();
            }
            if ((array = root.get("messages")) == null || !array.isJsonArray()) {
                return;
            }
            long highest = this.lastId.get();
            boolean added = false;
            Iterator iterator = array.getAsJsonArray().iterator();
            Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
            Iterator iterator2 = iterator;
            while (iterator2.hasNext()) {
                JsonObject voice;
                JsonElement element = (JsonElement)iterator2.next();
                if (!element.isJsonObject()) continue;
                JsonObject object = element.getAsJsonObject();
                Intrinsics.checkNotNull((Object)object);
                long id = MessengerClient.Companion.number(object, "id", 0L);
                if (id <= 0L) continue;
                if (id > highest) {
                    highest = id;
                }
                String user = MessengerClient.Companion.string(object, "user", "Player");
                String text = MessengerClient.Companion.string(object, "text", "");
                JsonObject jsonObject = voice = object.has("voice") && object.get("voice").isJsonObject() ? object.getAsJsonObject("voice") : null;
                if (StringsKt.isBlank((CharSequence)text) && voice == null || this.handleControl(user, text) || this.isBlocked(user) && !this.isMe(user)) continue;
                long replyTo = 0L;
                if (text.startsWith(CTRL_REPLY)) {
                    String payload = text.substring(9);
                    int split = payload.indexOf(':');
                    if (split > 0) {
                        try {
                            String string = payload.substring(0, split);
                            long parsed = Long.parseLong(string);
                            String body = payload.substring(split + 1);
                            if (parsed > 0L && !StringsKt.isBlank((CharSequence)body)) {
                                replyTo = parsed;
                                text = body;
                            }
                        }
                        catch (NumberFormatException numberFormatException) {
                            // empty catch block
                        }
                    }
                }
                JsonObject jsonObject2 = voice;
                JsonObject jsonObject3 = voice;
                JsonObject jsonObject4 = voice;
                Message message = new Message(id, MessengerClient.Companion.number(object, "ts", System.currentTimeMillis()), user, text, MessengerClient.Companion.string(object, "to", ""), (int)MessengerClient.Companion.number(object, "uid", 0L), this.isMe(user), replyTo, MessengerClient.Companion.normalizePrefix(MessengerClient.Companion.string(object, "prefix", "")), MessengerClient.Companion.string(object, "role", "none"), jsonObject2 != null ? MessengerClient.Companion.string(jsonObject2, "id", "") : "", jsonObject3 != null ? (int)MessengerClient.Companion.number(jsonObject3, "dur", 0L) : 0, jsonObject4 != null ? MessengerClient.Companion.string(jsonObject4, "peaks", "") : "");
                ArrayList<Message> arrayList = this.messages;
                synchronized (arrayList) {
                    boolean bl = false;
                    this.messages.add(message);
                    while (this.messages.size() > 200) {
                        this.messages.remove(0);
                    }
                    Unit unit = Unit.INSTANCE;
                }
                added = true;
            }
            this.lastId.set(highest);
            if (added) {
                this.version.incrementAndGet();
            }
        }
        catch (InterruptedException interrupted) {
            Thread.currentThread().interrupt();
        }
        catch (Exception exception) {
            this.connected = false;
        }
    }

    private final boolean isMe(String name) {
        return name != null && StringsKt.equals((String)name, (String)this.selfName(), (boolean)true);
    }

    private final void readSelf(JsonObject root) {
        if (!root.has("me") || !root.get("me").isJsonObject()) {
            return;
        }
        JsonObject me = root.getAsJsonObject("me");
        ArrayList<String> owned = new ArrayList<String>();
        if (me.has("prefixes") && me.get("prefixes").isJsonArray()) {
            Iterator iterator = me.getAsJsonArray("prefixes").iterator();
            Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
            Iterator iterator2 = iterator;
            while (iterator2.hasNext()) {
                JsonElement element = (JsonElement)iterator2.next();
                if (!element.isJsonPrimitive()) continue;
                owned.add(element.getAsString());
            }
        }
        Intrinsics.checkNotNull((Object)me);
        SelfState next = new SelfState((int)MessengerClient.Companion.number(me, "uid", 0L), MessengerClient.Companion.string(me, "role", "none"), MessengerClient.Companion.normalizePrefix(MessengerClient.Companion.string(me, "prefix", "")), (List<String>)owned, me.has("banned") && me.get("banned").isJsonPrimitive() && me.get("banned").getAsBoolean(), MessengerClient.Companion.number(me, "banUntil", 0L), MessengerClient.Companion.string(me, "banReason", ""), MessengerClient.Companion.number(me, "muteUntil", 0L), MessengerClient.Companion.number(me, "voiceMuteUntil", 0L), me.has("needsKey") && me.get("needsKey").isJsonPrimitive() && me.get("needsKey").getAsBoolean());
        SelfState previous = this.self.getAndSet(next);
        if (!Intrinsics.areEqual((Object)previous.role(), (Object)next.role()) || !Intrinsics.areEqual((Object)previous.prefix(), (Object)next.prefix())) {
            this.version.incrementAndGet();
        }
    }

    private final boolean applyRemoteDeletions(JsonObject root) {
        if (!root.has("deleted") || !root.get("deleted").isJsonArray()) {
            return false;
        }
        boolean removed = false;
        Iterator iterator = root.getAsJsonArray("deleted").iterator();
        Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
        Iterator iterator2 = iterator;
        while (iterator2.hasNext()) {
            long l;
            JsonElement element = (JsonElement)iterator2.next();
            if (!element.isJsonPrimitive()) continue;
            try {
                l = element.getAsLong();
            }
            catch (Exception ignored) {
                continue;
            }
            long id = l;
            if (!this.removeById(id, null)) continue;
            removed = true;
        }
        return removed;
    }

    public final void sendVoice(@NotNull String id, int durationMs, @NotNull String peaks) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        Intrinsics.checkNotNullParameter((Object)peaks, (String)"peaks");
        String name = this.selfName();
        Thread sender = new Thread(() -> MessengerClient.sendVoice$lambda$0(id, durationMs, peaks, name, this), "kimiko-messenger-voice");
        sender.setDaemon(true);
        sender.start();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @NotNull
    public final String staffKey() {
        if (!this.staffKeyLoaded) {
            MessengerClient messengerClient = this;
            synchronized (messengerClient) {
                boolean bl = false;
                if (!this.staffKeyLoaded) {
                    String string;
                    MessengerClient messengerClient2;
                    MessengerClient messengerClient3 = this;
                    try {
                        messengerClient2 = messengerClient3;
                        JsonObject store = RepositoryStorage.readObject(STORE);
                        string = store.has("staffKey") && store.get("staffKey").isJsonPrimitive() ? store.get("staffKey").getAsString() : "";
                        Intrinsics.checkNotNull((Object)string);
                    }
                    catch (Exception store) {
                        messengerClient2 = messengerClient3;
                        string = "";
                    }
                    messengerClient2.staffKey = string;
                    this.staffKeyLoaded = true;
                }
                Unit unit = Unit.INSTANCE;
            }
        }
        return this.staffKey;
    }

    public final void setStaffKey(@Nullable String value) {
        String next;
        String string = value;
        String string2 = string != null ? ((Object)StringsKt.trim((CharSequence)string)).toString() : null;
        if (string2 == null) {
            string2 = "";
        }
        this.staffKey = next = string2;
        this.staffKeyLoaded = true;
        try {
            JsonObject store = RepositoryStorage.readObject(STORE);
            store.addProperty("staffKey", next);
            RepositoryStorage.write(STORE, store);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private final JsonObject credentials() {
        JsonObject body = new JsonObject();
        body.addProperty("uid", (Number)ProfileIdentity.uid());
        body.addProperty("user", this.selfName());
        body.addProperty("token", ProfileIdentity.token());
        String key = this.staffKey();
        if (((CharSequence)key).length() > 0) {
            body.addProperty("key", key);
        }
        return body;
    }

    private final void target(JsonObject body, int targetUid, String targetName) {
        body.addProperty("targetUid", (Number)targetUid);
        String string = targetName;
        if (string == null) {
            string = "";
        }
        body.addProperty("targetName", string);
    }

    private final void call(String path, JsonObject body, Function2<? super Boolean, ? super String, Unit> onDone) {
        Thread worker = new Thread(() -> MessengerClient.call$lambda$0(this, path, body, onDone), "kimiko-messenger-mod");
        worker.setDaemon(true);
        worker.start();
    }

    public final void authStaff(@Nullable String key, @Nullable Function2<? super Boolean, ? super String, Unit> onDone) {
        this.setStaffKey(key);
        this.call("/api/staff/auth", this.credentials(), onDone);
    }

    public final void moderate(@NotNull String action, int targetUid, @Nullable String targetName, int minutes, @Nullable String reason, @Nullable Function2<? super Boolean, ? super String, Unit> onDone) {
        Intrinsics.checkNotNullParameter((Object)action, (String)"action");
        JsonObject body = this.credentials();
        this.target(body, targetUid, targetName);
        body.addProperty("action", action);
        body.addProperty("minutes", (Number)minutes);
        String string = reason;
        if (string == null) {
            string = "";
        }
        body.addProperty("reason", string);
        this.call("/api/mod/punish", body, onDone);
    }

    public final void deleteForEveryone(long id, @Nullable Function2<? super Boolean, ? super String, Unit> onDone) {
        JsonObject body = this.credentials();
        body.addProperty("id", (Number)id);
        this.removeById(id, null);
        this.version.incrementAndGet();
        this.call("/api/mod/delete", body, onDone);
    }

    public final void purgeUser(int targetUid, @Nullable String targetName, @Nullable Function2<? super Boolean, ? super String, Unit> onDone) {
        JsonObject body = this.credentials();
        this.target(body, targetUid, targetName);
        this.call("/api/mod/purge", body, onDone);
    }

    public final void assignRole(int targetUid, @Nullable String targetName, @NotNull String role, @Nullable Function2<? super Boolean, ? super String, Unit> onDone) {
        Intrinsics.checkNotNullParameter((Object)role, (String)"role");
        JsonObject body = this.credentials();
        this.target(body, targetUid, targetName);
        body.addProperty("role", role);
        this.call("/api/mod/role", body, onDone);
    }

    public final void assignPrefix(int targetUid, @Nullable String targetName, @NotNull String prefixId, boolean revoke, @Nullable Function2<? super Boolean, ? super String, Unit> onDone) {
        Intrinsics.checkNotNullParameter((Object)prefixId, (String)"prefixId");
        JsonObject body = this.credentials();
        this.target(body, targetUid, targetName);
        body.addProperty("prefixId", prefixId);
        body.addProperty("action", revoke ? "revoke" : "grant");
        this.call("/api/mod/prefix", body, onDone);
    }

    public final void choosePrefix(@NotNull String prefixId, @Nullable Function2<? super Boolean, ? super String, Unit> onDone) {
        Intrinsics.checkNotNullParameter((Object)prefixId, (String)"prefixId");
        JsonObject body = new JsonObject();
        body.addProperty("uid", (Number)ProfileIdentity.uid());
        body.addProperty("token", ProfileIdentity.token());
        body.addProperty("prefixId", prefixId);
        this.call("/api/prefix/select", body, onDone);
    }

    private final Result post(String path, JsonObject body) {
        Result result;
        try {
            Result result2;
            JsonObject jsonObject;
            HttpRequest request = HttpRequest.newBuilder(URI.create(Endpoints.irc() + path)).timeout(Duration.ofSeconds(6L)).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(body.toString(), StandardCharsets.UTF_8)).build();
            HttpResponse<String> response = NetworkPolicy.send(this.httpClient, request, HttpResponse.BodyHandlers.ofString());
            try {
                jsonObject = JsonParser.parseString((String)response.body()).getAsJsonObject();
            }
            catch (Exception ignored) {
                jsonObject = null;
            }
            JsonObject root = jsonObject;
            if (response.statusCode() == 200) {
                JsonObject jsonObject2 = root;
                result2 = new Result(true, jsonObject2 != null ? MessengerClient.Companion.string(jsonObject2, "message", I18n.tr("Готово")) : I18n.tr("Готово"), "");
            } else {
                String string = response.body();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"body(...)");
                result2 = new Result(false, "", MessengerClient.Companion.serverError(string, response.statusCode()));
            }
            result = result2;
        }
        catch (InterruptedException interrupted) {
            Thread.currentThread().interrupt();
            result = new Result(false, "", I18n.tr("Чат недоступен"));
        }
        catch (Exception exception) {
            result = new Result(false, "", I18n.tr("Чат недоступен"));
        }
        return result;
    }

    @NotNull
    public final SelfState state() {
        SelfState selfState = this.self.get();
        Intrinsics.checkNotNullExpressionValue((Object)selfState, (String)"get(...)");
        return selfState;
    }

    @NotNull
    public final String role() {
        return this.self.get().role();
    }

    public final boolean isStaff() {
        return !Intrinsics.areEqual((Object)this.self.get().role(), (Object)"none");
    }

    public final long muteLeft() {
        return this.remaining(this.self.get().muteUntil());
    }

    public final long voiceMuteLeft() {
        return this.remaining(this.self.get().voiceMuteUntil());
    }

    public final boolean isBanned() {
        return this.self.get().banned();
    }

    private final long remaining(long until) {
        if (until <= 0L) {
            return 0L;
        }
        long left = until - System.currentTimeMillis();
        return left > 0L ? left : 0L;
    }

    private static final Thread start$lambda$0(Runnable r) {
        Thread thread = new Thread(r, "kimiko-messenger");
        thread.setDaemon(true);
        return thread;
    }

    private static final void start$lambda$1(MessengerClient this$0) {
        this$0.poll();
    }

    private static final void transmit$lambda$0(String $name, String $text, MessengerClient this$0) {
        try {
            HttpRequest request;
            HttpResponse<String> response;
            JsonObject body = new JsonObject();
            body.addProperty("user", $name);
            body.addProperty("prefix", "none");
            body.addProperty("text", $text);
            int uid = ProfileIdentity.uid();
            if (uid > 0) {
                body.addProperty("uid", (Number)uid);
            }
            if ((response = NetworkPolicy.send(this$0.httpClient, request = HttpRequest.newBuilder(URI.create(Endpoints.irc() + "/api/send")).timeout(Duration.ofSeconds(2L)).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(body.toString(), StandardCharsets.UTF_8)).build(), HttpResponse.BodyHandlers.ofString())).statusCode() != 200) {
                String string = response.body();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"body(...)");
                MessengerClient.Companion.notifyError(MessengerClient.Companion.serverError(string, response.statusCode()));
            }
        }
        catch (InterruptedException interrupted) {
            Thread.currentThread().interrupt();
        }
        catch (Exception exception) {
            MessengerClient.Companion.notifyError(I18n.tr("Чат недоступен"));
        }
    }

    private static final void sendVoice$lambda$0(String $id, int $durationMs, String $peaks, String $name, MessengerClient this$0) {
        Result result;
        JsonObject voice = new JsonObject();
        voice.addProperty("id", $id);
        voice.addProperty("dur", (Number)$durationMs);
        voice.addProperty("peaks", $peaks);
        JsonObject body = new JsonObject();
        body.addProperty("user", $name);
        body.addProperty("text", "");
        body.addProperty("kind", "voice");
        body.add("voice", (JsonElement)voice);
        int uid = ProfileIdentity.uid();
        if (uid > 0) {
            body.addProperty("uid", (Number)uid);
        }
        if (!(result = this$0.post("/api/send", body)).getOk()) {
            MessengerClient.Companion.notifyError(result.getError());
        }
    }

    private static final void call$lambda$0$0(Function2 $onDone, Result $result) {
        $onDone.invoke((Object)$result.getOk(), (Object)($result.getOk() ? $result.getMessage() : $result.getError()));
    }

    private static final void call$lambda$0(MessengerClient this$0, String $path, JsonObject $body, Function2 $onDone) {
        Result result = this$0.post($path, $body);
        if ($onDone != null) {
            MinecraftClient.getInstance().execute(() -> MessengerClient.call$lambda$0$0($onDone, result));
        } else if (!result.getOk()) {
            MessengerClient.Companion.notifyError(result.getError());
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0006\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J'\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J'\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019R\u0019\u0010\u001c\u001a\u00020\u001a8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010#R\u0014\u0010%\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010#R\u0014\u0010&\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010#\u00a8\u0006'"}, d2={"Lrtx/kimiko/api/chat/messenger/MessengerClient.Companion;", "", "<init>", "()V", "", "value", "normalizePrefix", "(Ljava/lang/String;)Ljava/lang/String;", "message", "", "notifyError", "(Ljava/lang/String;)V", "body", "", "status", "serverError", "(Ljava/lang/String;I)Ljava/lang/String;", "Lcom/google/gson/JsonObject;", "object", "key", "", "fallback", "number", "(Lcom/google/gson/JsonObject;Ljava/lang/String;J)J", "string", "(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "Lrtx/kimiko/api/chat/messenger/MessengerClient;", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/chat/messenger/MessengerClient;", "POLL_MS", "J", "MAX_MESSAGES", "I", "CTRL_EDIT", "Ljava/lang/String;", "CTRL_DEL", "CTRL_REPLY", "STORE", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final String normalizePrefix(String value) {
            String prefix;
            String string = value;
            String string2 = string != null ? ((Object)StringsKt.trim((CharSequence)string)).toString() : null;
            if (string2 == null) {
                string2 = "";
            }
            return ((CharSequence)(prefix = string2)).length() == 0 || Intrinsics.areEqual((Object)prefix, (Object)"none") ? "" : prefix;
        }

        private final void notifyError(String message) {
            MinecraftClient.getInstance().execute(() -> Companion.notifyError$lambda$0(message));
        }

        private final String serverError(String body, int status) {
            try {
                JsonObject obj = JsonParser.parseString((String)body).getAsJsonObject();
                if (obj.has("error") && obj.get("error").isJsonPrimitive()) {
                    String string = obj.get("error").getAsString();
                    Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getAsString(...)");
                    String message = ((Object)StringsKt.trim((CharSequence)string)).toString();
                    if (((CharSequence)message).length() > 0) {
                        return message;
                    }
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            Object[] objectArray = new Object[]{status};
            return I18n.tr("Не отправлено (HTTP %d)", objectArray);
        }

        private final long number(JsonObject object, String key, long fallback) {
            JsonElement value = object.get(key);
            if (value == null || !value.isJsonPrimitive() || !value.getAsJsonPrimitive().isNumber()) {
                return fallback;
            }
            return value.getAsLong();
        }

        private final String string(JsonObject object, String key, String fallback) {
            JsonElement value = object.get(key);
            if (value == null || !value.isJsonPrimitive()) {
                return fallback;
            }
            String string = value.getAsString();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getAsString(...)");
            return string;
        }

        private static final void notifyError$lambda$0(String $message) {
            Notifications.push(I18n.tr("Чат"), $message, 2400L);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u00002\u00020\u0001B{\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\t\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0013\u0010\u0014R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0003\u0010\u0018R%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u0004\u0010\u0018R%\u0010\u0006\u001a\u00020\u00058\u0007z\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0019\u001a\u0004\b\u0006\u0010\u001aR%\u0010\b\u001a\u00020\u00058\u0007z\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010\u0019\u001a\u0004\b\b\u0010\u001aR%\u0010\n\u001a\u00020\t8\u0007z\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010\u001b\u001a\u0004\b\n\u0010\u001cR%\u0010\f\u001a\u00020\u000b8\u0007z\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010\u001d\u001a\u0004\b\f\u0010\u001eR%\u0010\r\u001a\u00020\u00028\u0007z\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u0010\u0017\u001a\u0004\b\r\u0010\u0018R%\u0010\u000e\u001a\u00020\u00058\u0007z\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u0010\u0019\u001a\u0004\b\u000e\u0010\u001aR%\u0010\u000f\u001a\u00020\u00058\u0007z\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0019\u001a\u0004\b\u000f\u0010\u001aR%\u0010\u0010\u001a\u00020\u00058\u0007z\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0010\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0019\u001a\u0004\b\u0010\u0010\u001aR%\u0010\u0011\u001a\u00020\t8\u0007z\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0011\u00a2\u0006\f\n\u0004\b\u0011\u0010\u001b\u001a\u0004\b\u0011\u0010\u001cR%\u0010\u0012\u001a\u00020\u00058\u0007z\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0012\u00a2\u0006\f\n\u0004\b\u0012\u0010\u0019\u001a\u0004\b\u0012\u0010\u001aR\u001f\u0010\u001f\u001a\u00020\u000b8Gz\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u001f\u00a2\u0006\u0006\u001a\u0004\b\u001f\u0010\u001eR\u0014\u0010 \u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010\u0019R0\u0010!\u001a\u00020\u00058\u0007@\u0006X\u0086\u000ez\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(!\u00a2\u0006\u0012\n\u0004\b!\u0010\u0019\u001a\u0004\b!\u0010\u001a\"\u0004\b\"\u0010#R\u001f\u0010$\u001a\u00020\u000b8Gz\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b($\u00a2\u0006\u0006\u001a\u0004\b$\u0010\u001e\u00a8\u0006%"}, d2={"Lrtx/kimiko/api/chat/messenger/MessengerClient$Message;", "", "", "id", "ts", "", "user", "initialText", "to", "", "uid", "", "mine", "replyTo", "prefix", "role", "voiceId", "voiceDur", "voicePeaks", "<init>", "(JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;IZJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "Lkotlin/jvm/JvmName;", "name", "J", "()J", "Ljava/lang/String;", "()Ljava/lang/String;", "I", "()I", "Z", "()Z", "voice", "original", "text", "setText", "(Ljava/lang/String;)V", "edited", "rtx.kimiko:kimiko"})
    public static final class Message {
        private final long id;
        private final long ts;
        @NotNull
        private final String user;
        @NotNull
        private final String to;
        private final int uid;
        private final boolean mine;
        private final long replyTo;
        @NotNull
        private final String prefix;
        @NotNull
        private final String role;
        @NotNull
        private final String voiceId;
        private final int voiceDur;
        @NotNull
        private final String voicePeaks;
        @NotNull
        private final String original;
        @NotNull
        private volatile String text;

        public Message(long id, long ts, @NotNull String user, @NotNull String initialText, @NotNull String to, int uid, boolean mine, long replyTo, @NotNull String prefix, @NotNull String role, @NotNull String voiceId, int voiceDur, @NotNull String voicePeaks) {
            Intrinsics.checkNotNullParameter((Object)user, (String)"user");
            Intrinsics.checkNotNullParameter((Object)initialText, (String)"initialText");
            Intrinsics.checkNotNullParameter((Object)to, (String)"to");
            Intrinsics.checkNotNullParameter((Object)prefix, (String)"prefix");
            Intrinsics.checkNotNullParameter((Object)role, (String)"role");
            Intrinsics.checkNotNullParameter((Object)voiceId, (String)"voiceId");
            Intrinsics.checkNotNullParameter((Object)voicePeaks, (String)"voicePeaks");
            this.id = id;
            this.ts = ts;
            this.user = user;
            this.to = to;
            this.uid = uid;
            this.mine = mine;
            this.replyTo = replyTo;
            this.prefix = prefix;
            this.role = role;
            this.voiceId = voiceId;
            this.voiceDur = voiceDur;
            this.voicePeaks = voicePeaks;
            this.original = initialText;
            this.text = initialText;
        }

        public /* synthetic */ Message(long l, long l2, String string, String string2, String string3, int n, boolean bl, long l3, String string4, String string5, String string6, int n2, String string7, int n3, DefaultConstructorMarker defaultConstructorMarker) {
            this(l, l2, string, string2, string3, n, bl, l3, ((n3 & 0x100) != 0 ? "" : string4), ((n3 & 0x200) != 0 ? "none" : string5), ((n3 & 0x400) != 0 ? "" : string6), ((n3 & 0x800) != 0 ? 0 : n2), ((n3 & 0x1000) != 0 ? "" : string7));
        }

        @JvmName(name="id")
        public final long id() {
            return this.id;
        }

        @JvmName(name="ts")
        public final long ts() {
            return this.ts;
        }

        @JvmName(name="user")
        @NotNull
        public final String user() {
            return this.user;
        }

        @JvmName(name="to")
        @NotNull
        public final String to() {
            return this.to;
        }

        @JvmName(name="uid")
        public final int uid() {
            return this.uid;
        }

        @JvmName(name="mine")
        public final boolean mine() {
            return this.mine;
        }

        @JvmName(name="replyTo")
        public final long replyTo() {
            return this.replyTo;
        }

        @JvmName(name="prefix")
        @NotNull
        public final String prefix() {
            return this.prefix;
        }

        @JvmName(name="role")
        @NotNull
        public final String role() {
            return this.role;
        }

        @JvmName(name="voiceId")
        @NotNull
        public final String voiceId() {
            return this.voiceId;
        }

        @JvmName(name="voiceDur")
        public final int voiceDur() {
            return this.voiceDur;
        }

        @JvmName(name="voicePeaks")
        @NotNull
        public final String voicePeaks() {
            return this.voicePeaks;
        }

        @JvmName(name="voice")
        public final boolean voice() {
            return ((CharSequence)this.voiceId).length() > 0;
        }

        @JvmName(name="text")
        @NotNull
        public final String text() {
            return this.text;
        }

        public final void setText(@NotNull String string) {
            Intrinsics.checkNotNullParameter((Object)string, (String)"<set-?>");
            this.text = string;
        }

        @JvmName(name="edited")
        public final boolean edited() {
            return !Intrinsics.areEqual((Object)this.text, (Object)this.original);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\f\u001a\u0004\b\u000f\u0010\u000e\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/api/chat/messenger/MessengerClient$Result;", "", "", "ok", "", "message", "error", "<init>", "(ZLjava/lang/String;Ljava/lang/String;)V", "Z", "getOk", "()Z", "Ljava/lang/String;", "getMessage", "()Ljava/lang/String;", "getError", "rtx.kimiko:kimiko"})
    private static final class Result {
        private final boolean ok;
        @NotNull
        private final String message;
        @NotNull
        private final String error;

        public Result(boolean ok, @NotNull String message, @NotNull String error) {
            Intrinsics.checkNotNullParameter((Object)message, (String)"message");
            Intrinsics.checkNotNullParameter((Object)error, (String)"error");
            this.ok = ok;
            this.message = message;
            this.error = error;
        }

        public final boolean getOk() {
            return this.ok;
        }

        @NotNull
        public final String getMessage() {
            return this.message;
        }

        @NotNull
        public final String getError() {
            return this.error;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB_\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\u0006\u0010\u000e\u001a\u00020\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u000b\u0012\u0006\u0010\u0010\u001a\u00020\t\u00a2\u0006\u0004\b\u0011\u0010\u0012R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0003\u0010\u0016R%\u0010\u0005\u001a\u00020\u00048\u0007z\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0017\u001a\u0004\b\u0005\u0010\u0018R%\u0010\u0006\u001a\u00020\u00048\u0007z\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0017\u001a\u0004\b\u0006\u0010\u0018R+\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00078\u0007z\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010\u0019\u001a\u0004\b\b\u0010\u001aR%\u0010\n\u001a\u00020\t8\u0007z\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010\u001b\u001a\u0004\b\n\u0010\u001cR%\u0010\f\u001a\u00020\u000b8\u0007z\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010\u001d\u001a\u0004\b\f\u0010\u001eR%\u0010\r\u001a\u00020\u00048\u0007z\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u0010\u0017\u001a\u0004\b\r\u0010\u0018R%\u0010\u000e\u001a\u00020\u000b8\u0007z\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u0010\u001d\u001a\u0004\b\u000e\u0010\u001eR%\u0010\u000f\u001a\u00020\u000b8\u0007z\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u0010\u001d\u001a\u0004\b\u000f\u0010\u001eR%\u0010\u0010\u001a\u00020\t8\u0007z\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0010\u00a2\u0006\f\n\u0004\b\u0010\u0010\u001b\u001a\u0004\b\u0010\u0010\u001c\u00a8\u0006 "}, d2={"Lrtx/kimiko/api/chat/messenger/MessengerClient$SelfState;", "", "", "uid", "", "role", "prefix", "", "prefixes", "", "banned", "", "banUntil", "banReason", "muteUntil", "voiceMuteUntil", "needsKey", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/util/List;ZJLjava/lang/String;JJZ)V", "Lkotlin/jvm/JvmName;", "name", "I", "()I", "Ljava/lang/String;", "()Ljava/lang/String;", "Ljava/util/List;", "()Ljava/util/List;", "Z", "()Z", "J", "()J", "Companion", "rtx.kimiko:kimiko"})
    public static final class SelfState {
        @NotNull
        public static final Companion Companion = new Companion(null);
        private final int uid;
        @NotNull
        private final String role;
        @NotNull
        private final String prefix;
        @NotNull
        private final List<String> prefixes;
        private final boolean banned;
        private final long banUntil;
        @NotNull
        private final String banReason;
        private final long muteUntil;
        private final long voiceMuteUntil;
        private final boolean needsKey;
        @JvmField
        @NotNull
        public static final SelfState NONE = new SelfState(0, "none", "", CollectionsKt.emptyList(), false, 0L, "", 0L, 0L, false);

        public SelfState(int uid, @NotNull String role, @NotNull String prefix, @NotNull List<String> prefixes, boolean banned, long banUntil, @NotNull String banReason, long muteUntil, long voiceMuteUntil, boolean needsKey) {
            Intrinsics.checkNotNullParameter((Object)role, (String)"role");
            Intrinsics.checkNotNullParameter((Object)prefix, (String)"prefix");
            Intrinsics.checkNotNullParameter(prefixes, (String)"prefixes");
            Intrinsics.checkNotNullParameter((Object)banReason, (String)"banReason");
            this.uid = uid;
            this.role = role;
            this.prefix = prefix;
            this.prefixes = prefixes;
            this.banned = banned;
            this.banUntil = banUntil;
            this.banReason = banReason;
            this.muteUntil = muteUntil;
            this.voiceMuteUntil = voiceMuteUntil;
            this.needsKey = needsKey;
        }

        @JvmName(name="uid")
        public final int uid() {
            return this.uid;
        }

        @JvmName(name="role")
        @NotNull
        public final String role() {
            return this.role;
        }

        @JvmName(name="prefix")
        @NotNull
        public final String prefix() {
            return this.prefix;
        }

        @JvmName(name="prefixes")
        @NotNull
        public final List<String> prefixes() {
            return this.prefixes;
        }

        @JvmName(name="banned")
        public final boolean banned() {
            return this.banned;
        }

        @JvmName(name="banUntil")
        public final long banUntil() {
            return this.banUntil;
        }

        @JvmName(name="banReason")
        @NotNull
        public final String banReason() {
            return this.banReason;
        }

        @JvmName(name="muteUntil")
        public final long muteUntil() {
            return this.muteUntil;
        }

        @JvmName(name="voiceMuteUntil")
        public final long voiceMuteUntil() {
            return this.voiceMuteUntil;
        }

        @JvmName(name="needsKey")
        public final boolean needsKey() {
            return this.needsKey;
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/api/chat/messenger/MessengerClient$SelfState.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/chat/messenger/MessengerClient$SelfState;", "Lkotlin/jvm/JvmField;", "NONE", "Lrtx/kimiko/api/chat/messenger/MessengerClient$SelfState;", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }
}

