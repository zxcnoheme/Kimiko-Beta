/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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
package rtx.kimiko.api.modules.impl.Visuals.portallive;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.ByteArrayOutputStream;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.ArrayDeque;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0006\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0012\u0018\u0000 a2\u00020\u0001:\u0002baB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00072\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u00a2\u0006\u0004\b\b\u0010\tJ\u001d\u0010\n\u001a\u00020\u00072\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u00a2\u0006\u0004\b\n\u0010\tJ1\u0010\u0011\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0013\u0010\u0003J\r\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0019\u0010\u0018J\r\u0010\u001a\u001a\u00020\u0014\u00a2\u0006\u0004\b\u001a\u0010\u0016J\r\u0010\u001b\u001a\u00020\u0014\u00a2\u0006\u0004\b\u001b\u0010\u0016J\r\u0010\u001c\u001a\u00020\u000b\u00a2\u0006\u0004\b\u001c\u0010\u0018J\u0015\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0014\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010!\u001a\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b!\u0010\"J\r\u0010$\u001a\u00020#\u00a2\u0006\u0004\b$\u0010%J\r\u0010&\u001a\u00020\r\u00a2\u0006\u0004\b&\u0010'J\u0017\u0010)\u001a\u00020\u00072\b\u0010(\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b)\u0010\"J\u000f\u0010*\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b*\u0010\u0003J\u0017\u0010,\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b,\u0010-J\u000f\u0010.\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b.\u0010\u0003J\u0019\u00100\u001a\u00020\u00072\b\u0010/\u001a\u0004\u0018\u00010\u000bH\u0002\u00a2\u0006\u0004\b0\u0010-J\u0017\u00102\u001a\u00020\u00072\u0006\u00101\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b2\u0010\"R\u001c\u00105\u001a\n 4*\u0004\u0018\u000103038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00106R\u0014\u00108\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010:\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u00109R\u0018\u0010=\u001a\u00060;j\u0002`<8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u0010>R\u0014\u0010@\u001a\u00020?8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b@\u0010AR\u0014\u0010B\u001a\u00020\u00018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u001a\u0010E\u001a\b\u0012\u0004\u0012\u00020\u000b0D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010FR\u001a\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00050D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010FR\u0018\u0010I\u001a\u0004\u0018\u00010H8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bI\u0010JR\u0016\u0010K\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010LR\u0016\u0010M\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bM\u0010LR\u0018\u0010N\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bN\u0010OR\u0016\u0010P\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bP\u0010LR\u0016\u0010R\u001a\u00020Q8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bR\u0010SR\u0016\u0010T\u001a\u00020#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bT\u0010UR\u0016\u0010V\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010WR\u0016\u0010X\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bX\u0010LR\u0016\u0010Y\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bY\u0010ZR\u0016\u0010[\u001a\u00020Q8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b[\u0010SR\u001e\u0010\\\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\\\u0010]R\u001e\u0010^\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b^\u0010]R\u0016\u0010_\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b_\u0010ZR\u0016\u0010`\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b`\u0010Z\u00a8\u0006c"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveClient;", "", "<init>", "()V", "Ljava/util/function/Consumer;", "", "consumer", "", "setFrameConsumer", "(Ljava/util/function/Consumer;)V", "setVoiceConsumer", "", "host", "", "port", "room", "name", "connect", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "disconnect", "", "isConnected", "()Z", "getRoom", "()Ljava/lang/String;", "getName", "isConnecting", "isPeerActive", "getPeerName", "active", "sendActive", "(Z)V", "jpeg", "sendFrame", "([B)V", "", "sendPressureMs", "()D", "droppedFrames", "()I", "opus", "sendVoice", "sendJoin", "text", "enqueueText", "(Ljava/lang/String;)V", "pump", "raw", "handleText", "data", "handleBinary", "Ljava/net/http/HttpClient;", "kotlin.jvm.PlatformType", "httpClient", "Ljava/net/http/HttpClient;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "connected", "Ljava/util/concurrent/atomic/AtomicBoolean;", "connecting", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "textBuffer", "Ljava/lang/StringBuilder;", "Ljava/io/ByteArrayOutputStream;", "binaryBuffer", "Ljava/io/ByteArrayOutputStream;", "sendLock", "Ljava/lang/Object;", "Ljava/util/ArrayDeque;", "pendingTexts", "Ljava/util/ArrayDeque;", "pendingVoice", "Ljava/net/http/WebSocket;", "socket", "Ljava/net/http/WebSocket;", "manualClose", "Z", "sendInFlight", "pendingFrame", "[B", "sendingFrame", "", "sendingStartedNs", "J", "sendMsEma", "D", "droppedFramesVal", "I", "peerActiveVal", "peerNameVal", "Ljava/lang/String;", "peerSeenAtMs", "frameConsumer", "Ljava/util/function/Consumer;", "voiceConsumer", "roomVal", "nameVal", "Companion", "SocketListener", "rtx.kimiko:kimiko"})
public final class PortalLiveClient {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(6L)).build();
    @NotNull
    private final AtomicBoolean connected = new AtomicBoolean(false);
    @NotNull
    private final AtomicBoolean connecting = new AtomicBoolean(false);
    @NotNull
    private final StringBuilder textBuffer = new StringBuilder();
    @NotNull
    private final ByteArrayOutputStream binaryBuffer = new ByteArrayOutputStream(65536);
    @NotNull
    private final Object sendLock = new Object();
    @NotNull
    private final ArrayDeque<String> pendingTexts = new ArrayDeque();
    @NotNull
    private final ArrayDeque<byte[]> pendingVoice = new ArrayDeque();
    @Nullable
    private volatile WebSocket socket;
    private volatile boolean manualClose;
    private volatile boolean sendInFlight;
    @Nullable
    private volatile byte[] pendingFrame;
    private volatile boolean sendingFrame;
    private volatile long sendingStartedNs;
    private volatile double sendMsEma;
    private volatile int droppedFramesVal;
    private volatile boolean peerActiveVal;
    @NotNull
    private volatile String peerNameVal = "";
    private volatile long peerSeenAtMs;
    @Nullable
    private volatile Consumer<byte[]> frameConsumer;
    @Nullable
    private volatile Consumer<byte[]> voiceConsumer;
    @NotNull
    private volatile String roomVal = "";
    @NotNull
    private volatile String nameVal = "";
    private static final int MAX_FRAME_BYTES = 524288;
    private static final int MAX_VOICE_BYTES = 4096;
    private static final int MAX_VOICE_QUEUE = 16;
    private static final byte KIND_VIDEO = 86;
    private static final byte KIND_VOICE = 65;

    public final void setFrameConsumer(@Nullable Consumer<byte[]> consumer) {
        this.frameConsumer = consumer;
    }

    public final void setVoiceConsumer(@Nullable Consumer<byte[]> consumer) {
        this.voiceConsumer = consumer;
    }

    public final synchronized void connect(@NotNull String string, int n, @Nullable String string2, @Nullable String string3) {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final synchronized void disconnect() {
        this.manualClose = true;
        this.connecting.set(false);
        this.connected.set(false);
        this.peerActiveVal = false;
        this.peerNameVal = "";
        WebSocket current = this.socket;
        this.socket = null;
        Object object = this.sendLock;
        synchronized (object) {
            boolean bl = false;
            this.pendingTexts.clear();
            this.pendingVoice.clear();
            this.pendingFrame = null;
            this.sendInFlight = false;
            Unit unit = Unit.INSTANCE;
        }
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
        return this.connected.get();
    }

    @NotNull
    public final String getRoom() {
        return this.roomVal;
    }

    @NotNull
    public final String getName() {
        return this.nameVal;
    }

    public final boolean isConnecting() {
        return this.connecting.get();
    }

    public final boolean isPeerActive() {
        return this.peerActiveVal && System.currentTimeMillis() - this.peerSeenAtMs < 10000L;
    }

    @NotNull
    public final String getPeerName() {
        return this.peerNameVal;
    }

    public final void sendActive(boolean active) {
        JsonObject packet = new JsonObject();
        packet.addProperty("t", "a");
        packet.addProperty("v", (Number)(active ? 1 : 0));
        String string = packet.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        this.enqueueText(string);
    }

    public final void sendFrame(@Nullable byte[] byArray) {
    }

    public final double sendPressureMs() {
        double ema = this.sendMsEma;
        if (this.sendingFrame) {
            double current = (double)(System.nanoTime() - this.sendingStartedNs) / 1000000.0;
            return Math.max(ema, current);
        }
        return ema;
    }

    public final int droppedFrames() {
        return this.droppedFramesVal;
    }

    public final void sendVoice(@Nullable byte[] byArray) {
    }

    private final void sendJoin() {
        JsonObject packet = new JsonObject();
        packet.addProperty("t", "j");
        packet.addProperty("r", this.roomVal);
        packet.addProperty("n", this.nameVal);
        String string = packet.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        this.enqueueText(string);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void enqueueText(String text) {
        if (!this.connected.get()) {
            return;
        }
        Object object = this.sendLock;
        synchronized (object) {
            boolean bl = false;
            this.pendingTexts.addLast(text);
            this.pump();
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void pump() {
        if (this.sendInFlight) {
            return;
        }
        WebSocket current = this.socket;
        if (current == null || !this.connected.get()) {
            this.pendingTexts.clear();
            this.pendingVoice.clear();
            this.pendingFrame = null;
            return;
        }
        CompletableFuture<WebSocket> stage = null;
        if (!this.pendingTexts.isEmpty()) {
            this.sendingFrame = false;
            stage = current.sendText(this.pendingTexts.pollFirst(), true);
        } else if (!this.pendingVoice.isEmpty()) {
            this.sendingFrame = false;
            stage = current.sendBinary(ByteBuffer.wrap(this.pendingVoice.pollFirst()), true);
        } else {
            byte[] frame = this.pendingFrame;
            if (frame != null) {
                this.pendingFrame = null;
                this.sendingFrame = true;
                this.sendingStartedNs = System.nanoTime();
                stage = current.sendBinary(ByteBuffer.wrap(frame), true);
            } else {
                return;
            }
        }
        this.sendInFlight = true;
        if (stage != null) {
            stage.whenComplete((ws, throwable) -> {
                synchronized (this.sendLock) {
                    if (this.sendingFrame) {
                        double ms = (double)(System.nanoTime() - this.sendingStartedNs) / 1000000.0;
                        this.sendMsEma = this.sendMsEma <= 0.0 ? ms : this.sendMsEma * 0.75 + ms * 0.25;
                        this.sendingFrame = false;
                    }
                    this.sendInFlight = false;
                    if (throwable == null) {
                        this.pump();
                    } else {
                        this.pendingTexts.clear();
                        this.pendingVoice.clear();
                        this.pendingFrame = null;
                    }
                }
            });
        }
    }

    private final void handleText(String raw) {
        CharSequence charSequence = raw;
        if (charSequence == null || StringsKt.isBlank((CharSequence)charSequence)) {
            return;
        }
        try {
            String type;
            JsonObject packet = JsonParser.parseString((String)raw).getAsJsonObject();
            String string = type = packet.has("t") ? packet.get("t").getAsString() : "";
            if (Intrinsics.areEqual((Object)"a", (Object)type)) {
                boolean bl = this.peerActiveVal = packet.has("v") && packet.get("v").getAsInt() > 0;
                if (packet.has("n")) {
                    String string2 = packet.get("n").getAsString();
                    Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getAsString(...)");
                    this.peerNameVal = string2;
                }
                this.peerSeenAtMs = System.currentTimeMillis();
            } else if (Intrinsics.areEqual((Object)"l", (Object)type)) {
                this.peerActiveVal = false;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private final void handleBinary(byte[] data) {
        block3: {
            Consumer<byte[]> consumer;
            byte[] payload;
            byte kind;
            block2: {
                Consumer<byte[]> consumer2;
                this.peerSeenAtMs = System.currentTimeMillis();
                if (data.length < 2 || data.length > 524288) {
                    return;
                }
                kind = data[0];
                payload = new byte[data.length - 1];
                System.arraycopy(data, 1, payload, 0, payload.length);
                if (kind != 86) break block2;
                Consumer<byte[]> consumer3 = consumer2 = this.frameConsumer;
                if (consumer3 == null) break block3;
                consumer3.accept(payload);
                break block3;
            }
            if (kind != 65) break block3;
            Consumer<byte[]> consumer4 = consumer = this.voiceConsumer;
            if (consumer4 != null) {
                consumer4.accept(payload);
            }
        }
    }

    private static final Unit connect$lambda$0(PortalLiveClient this$0, WebSocket webSocket, Throwable throwable) {
        if (throwable != null) {
            this$0.connecting.set(false);
            this$0.connected.set(false);
            this$0.socket = null;
        }
        return Unit.INSTANCE;
    }

    private static final void connect$lambda$1(Function2 $tmp0, Object p0, Object p1) {
        $tmp0.invoke(p0, p1);
    }


    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\u000b\u00a8\u0006\r"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveClient.Companion;", "", "<init>", "()V", "", "MAX_FRAME_BYTES", "I", "MAX_VOICE_BYTES", "MAX_VOICE_QUEUE", "", "KIND_VIDEO", "B", "KIND_VOICE", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ-\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ-\u0010\u0011\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0012J-\u0010\u0017\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0015H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u001f\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0019H\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001c\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveClient$SocketListener;", "Ljava/net/http/WebSocket$Listener;", "<init>", "(Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveClient;)V", "Ljava/net/http/WebSocket;", "webSocket", "", "onOpen", "(Ljava/net/http/WebSocket;)V", "", "data", "", "last", "Ljava/util/concurrent/CompletionStage;", "onText", "(Ljava/net/http/WebSocket;Ljava/lang/CharSequence;Z)Ljava/util/concurrent/CompletionStage;", "Ljava/nio/ByteBuffer;", "onBinary", "(Ljava/net/http/WebSocket;Ljava/nio/ByteBuffer;Z)Ljava/util/concurrent/CompletionStage;", "", "statusCode", "", "reason", "onClose", "(Ljava/net/http/WebSocket;ILjava/lang/String;)Ljava/util/concurrent/CompletionStage;", "", "error", "onError", "(Ljava/net/http/WebSocket;Ljava/lang/Throwable;)V", "rtx.kimiko:kimiko"})
    private final class SocketListener
    implements WebSocket.Listener {
        @Override
        public void onOpen(@NotNull WebSocket webSocket) {
            Intrinsics.checkNotNullParameter((Object)webSocket, (String)"webSocket");
            PortalLiveClient.this.socket = webSocket;
            PortalLiveClient.this.connecting.set(false);
            PortalLiveClient.this.connected.set(true);
            webSocket.request(1L);
            PortalLiveClient.this.sendJoin();
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        @Nullable
        public CompletionStage<?> onText(@NotNull WebSocket webSocket, @NotNull CharSequence data, boolean last) {
            Intrinsics.checkNotNullParameter((Object)webSocket, (String)"webSocket");
            Intrinsics.checkNotNullParameter((Object)data, (String)"data");
            StringBuilder stringBuilder = PortalLiveClient.this.textBuffer;
            PortalLiveClient portalLiveClient = PortalLiveClient.this;
            StringBuilder stringBuilder2 = stringBuilder;
            synchronized (stringBuilder2) {
                boolean bl = false;
                portalLiveClient.textBuffer.append(data);
                if (last) {
                    String string = portalLiveClient.textBuffer.toString();
                    Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
                    String packet = string;
                    portalLiveClient.textBuffer.setLength(0);
                    portalLiveClient.handleText(packet);
                }
                Unit unit = Unit.INSTANCE;
            }
            webSocket.request(1L);
            return null;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        @Nullable
        public CompletionStage<?> onBinary(@NotNull WebSocket webSocket, @NotNull ByteBuffer data, boolean last) {
            Intrinsics.checkNotNullParameter((Object)webSocket, (String)"webSocket");
            Intrinsics.checkNotNullParameter((Object)data, (String)"data");
            ByteArrayOutputStream byteArrayOutputStream = PortalLiveClient.this.binaryBuffer;
            PortalLiveClient portalLiveClient = PortalLiveClient.this;
            ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
            synchronized (byteArrayOutputStream2) {
                boolean bl = false;
                byte[] chunk = new byte[data.remaining()];
                data.get(chunk);
                if (portalLiveClient.binaryBuffer.size() + chunk.length <= 524288) {
                    portalLiveClient.binaryBuffer.write(chunk, 0, chunk.length);
                    if (last) {
                        byte[] frame = portalLiveClient.binaryBuffer.toByteArray();
                        portalLiveClient.binaryBuffer.reset();
                        Intrinsics.checkNotNull((Object)frame);
                        portalLiveClient.handleBinary(frame);
                    }
                } else {
                    portalLiveClient.binaryBuffer.reset();
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
            PortalLiveClient.this.connecting.set(false);
            PortalLiveClient.this.connected.set(false);
            PortalLiveClient.this.peerActiveVal = false;
            PortalLiveClient.this.socket = null;
            return WebSocket.Listener.super.onClose(webSocket, statusCode, reason);
        }

        @Override
        public void onError(@NotNull WebSocket webSocket, @NotNull Throwable error) {
            Intrinsics.checkNotNullParameter((Object)webSocket, (String)"webSocket");
            Intrinsics.checkNotNullParameter((Object)error, (String)"error");
            PortalLiveClient.this.connecting.set(false);
            PortalLiveClient.this.connected.set(false);
            PortalLiveClient.this.peerActiveVal = false;
            PortalLiveClient.this.socket = null;
        }
    }
}

