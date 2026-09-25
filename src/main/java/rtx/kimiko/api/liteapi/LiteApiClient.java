/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking$Context
 *  net.fabricmc.fabric.api.networking.v1.PacketSender
 *  net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.network.ClientPlayNetworkHandler
 *  net.minecraft.network.packet.CustomPayload
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.liteapi;

import com.google.gson.JsonObject;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.CustomPayload;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.liteapi.FeatureBlocklist;
import rtx.kimiko.api.liteapi.LiteApiCodec;
import rtx.kimiko.api.liteapi.packets.LiteApiPayload;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003J\r\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u000f\u0010\u0007\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\u0003J\u000f\u0010\b\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\u0003J\u0017\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\r\u0010\u0003R\u0016\u0010\u000f\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R \u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00120\u00118\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0016"}, d2={"Lrtx/kimiko/api/liteapi/LiteApiClient;", "", "<init>", "()V", "", "start", "stop", "onJoin", "onDisconnect", "", "raw", "handle", "(Ljava/lang/String;)V", "purgeExpired", "", "started", "Z", "", "", "pending", "Ljava/util/Map;", "Companion", "rtx.kimiko:kimiko"})
public final class LiteApiClient {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private volatile boolean started;
    @NotNull
    private final Map<String, Long> pending = new ConcurrentHashMap();
    @JvmField
    @NotNull
    public static final LiteApiClient INSTANCE = new LiteApiClient();
    @NotNull
    private static final String CLIENT_ID = Kimiko.Companion.namespace();
    private static final long TIMEOUT_MS = 10000L;

    private LiteApiClient() {
    }

    public final void start() {
        if (this.started) {
            return;
        }
        this.started = true;
        PayloadTypeRegistry.playC2S().register(LiteApiPayload.TYPE, LiteApiPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(LiteApiPayload.TYPE, LiteApiPayload.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(LiteApiPayload.TYPE, (arg_0, arg_1) -> LiteApiClient.start$lambda$0(this, arg_0, arg_1));
        ClientPlayConnectionEvents.JOIN.register((arg_0, arg_1, arg_2) -> LiteApiClient.start$lambda$1(this, arg_0, arg_1, arg_2));
        ClientPlayConnectionEvents.DISCONNECT.register((arg_0, arg_1) -> LiteApiClient.start$lambda$2(this, arg_0, arg_1));
    }

    public final void stop() {
        this.onDisconnect();
        this.started = false;
    }

    private final void onJoin() {
        Set<String> features = FeatureBlocklist.knownFeatures();
        if (features.isEmpty()) {
            return;
        }
        this.purgeExpired();
        LiteApiCodec.Request req = LiteApiCodec.checkFeatures(CLIENT_ID, (Collection<String>)features);
        this.pending.put(req.id(), System.currentTimeMillis());
        try {
            ClientPlayNetworking.send((CustomPayload)new LiteApiPayload(req.json()));
        }
        catch (RuntimeException ex) {
            this.pending.remove(req.id());
        }
    }

    private final void onDisconnect() {
        this.pending.clear();
        FeatureBlocklist.clear();
    }

    private final void handle(String raw) {
        LiteApiCodec.Incoming incoming = LiteApiCodec.parse(raw);
        if (incoming == null) {
            return;
        }
        LiteApiCodec.Incoming incoming2 = incoming;
        if (incoming2.isPushEvent()) {
            return;
        }
        String id = incoming2.id();
        if (id == null || this.pending.remove(id) == null) {
            return;
        }
        if (!incoming2.ok()) {
            return;
        }
        JsonObject payload = incoming2.payload();
        List<String> blocklist = LiteApiCodec.blocklist(payload);
        FeatureBlocklist.applyBlocklist((Collection<String>)blocklist);
    }

    private final void purgeExpired() {
        long now = System.currentTimeMillis();
        this.pending.entrySet().removeIf(it -> now - it.getValue() > 10000L);
    }

    private static final void start$lambda$0$0(LiteApiClient this$0, LiteApiPayload $payload) {
        this$0.handle($payload.json());
    }

    private static final void start$lambda$0(LiteApiClient this$0, LiteApiPayload payload, ClientPlayNetworking.Context context) {
        Intrinsics.checkNotNullParameter((Object)payload, (String)"payload");
        Intrinsics.checkNotNullParameter((Object)context, (String)"context");
        context.client().execute(() -> LiteApiClient.start$lambda$0$0(this$0, payload));
    }

    private static final void start$lambda$1(LiteApiClient this$0, ClientPlayNetworkHandler handler, PacketSender sender, MinecraftClient client) {
        Intrinsics.checkNotNullParameter((Object)handler, (String)"handler");
        Intrinsics.checkNotNullParameter((Object)sender, (String)"sender");
        Intrinsics.checkNotNullParameter((Object)client, (String)"client");
        this$0.onJoin();
    }

    private static final void start$lambda$2(LiteApiClient this$0, ClientPlayNetworkHandler handler, MinecraftClient client) {
        Intrinsics.checkNotNullParameter((Object)handler, (String)"handler");
        Intrinsics.checkNotNullParameter((Object)client, (String)"client");
        this$0.onDisconnect();
    }


    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/liteapi/LiteApiClient.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/liteapi/LiteApiClient;", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/liteapi/LiteApiClient;", "", "CLIENT_ID", "Ljava/lang/String;", "", "TIMEOUT_MS", "J", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

