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
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.custompet.sync;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Visuals.custompet.CustomPetVariant;
import rtx.kimiko.api.modules.impl.Visuals.custompet.sync.CustomPetRemoteState;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001:\u0001lB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u008f\u0001\u0010\u001a\u001a\u00020\u00192\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u000f\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001d\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\n\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010!\u001a\u00020\u00192\b\u0010 \u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b!\u0010\"J\r\u0010#\u001a\u00020\r\u00a2\u0006\u0004\b#\u0010$J\r\u0010%\u001a\u00020\r\u00a2\u0006\u0004\b%\u0010$J\u0019\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020'0&\u00a2\u0006\u0004\b(\u0010)J\r\u0010*\u001a\u00020\u0019\u00a2\u0006\u0004\b*\u0010\u0003J\u000f\u0010+\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b+\u0010$J\u000f\u0010,\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\b,\u0010\u0003J\u0017\u0010.\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b.\u0010\"J\u0019\u00100\u001a\u00020\u00192\b\u0010/\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b0\u0010\"J\u0017\u00104\u001a\u0002032\u0006\u00102\u001a\u000201H\u0002\u00a2\u0006\u0004\b4\u00105J\u0017\u00106\u001a\u0002032\u0006\u00102\u001a\u000201H\u0002\u00a2\u0006\u0004\b6\u00105J\u0017\u00108\u001a\u00020\u00192\u0006\u00107\u001a\u000203H\u0002\u00a2\u0006\u0004\b8\u00109J\u001f\u0010;\u001a\u00020\u00192\u0006\u00107\u001a\u0002032\u0006\u0010:\u001a\u000203H\u0002\u00a2\u0006\u0004\b;\u0010<J\u001f\u0010=\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b=\u0010>J!\u0010A\u001a\u00020\u00042\b\u0010?\u001a\u0004\u0018\u00010\u00042\u0006\u0010@\u001a\u00020\nH\u0002\u00a2\u0006\u0004\bA\u0010BJ'\u0010F\u001a\u00020\u00042\u0006\u0010C\u001a\u0002012\u0006\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bF\u0010GJ'\u0010H\u001a\u00020\r2\u0006\u0010C\u001a\u0002012\u0006\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bH\u0010IJ/\u0010K\u001a\u00020\u000f2\u0006\u0010C\u001a\u0002012\u0006\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bK\u0010LJ/\u0010M\u001a\u00020\u00132\u0006\u0010C\u001a\u0002012\u0006\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\bM\u0010NR\u0014\u0010P\u001a\u00020O8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bP\u0010QR\u001c\u0010T\u001a\n S*\u0004\u0018\u00010R0R8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bT\u0010UR\u0014\u0010W\u001a\u00020V8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bW\u0010XR\u0014\u0010Y\u001a\u00020V8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bY\u0010XR\u0018\u0010\\\u001a\u00060Zj\u0002`[8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\\\u0010]R \u0010_\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020'0^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b_\u0010`R\u0018\u0010b\u001a\u0004\u0018\u00010a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bb\u0010cR\u0016\u0010d\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bd\u0010eR\u0016\u0010f\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bf\u0010gR\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0005\u0010gR\u0016\u0010\u0006\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0006\u0010gR\u0016\u0010\u0007\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0007\u0010gR\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\t\u0010hR\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000b\u0010iR\u0016\u0010\f\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\f\u0010gR\u0016\u0010\u000e\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000e\u0010eR\u0016\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0010\u0010jR\u0016\u0010\u0011\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0011\u0010jR\u0016\u0010\u0012\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0012\u0010jR\u0016\u0010\u0014\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0014\u0010kR\u0016\u0010\u0015\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0015\u0010eR\u0016\u0010\u0016\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0016\u0010eR\u0016\u0010\u0017\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0017\u0010eR\u0016\u0010\u0018\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0018\u0010j\u00a8\u0006m"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/sync/CustomPetSyncClient;", "", "<init>", "()V", "", "profileUsername", "minecraftUsername", "world", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;", "variant", "", "robotType", "petKind", "", "active", "", "x", "y", "z", "", "yaw", "moving", "umbrella", "airborne", "animationSpeed", "", "setLocalState", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;ILjava/lang/String;ZDDDFZZZD)V", "host", "port", "connect", "(Ljava/lang/String;I)V", "reason", "disconnect", "(Ljava/lang/String;)V", "isConnected", "()Z", "isConnecting", "", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/sync/CustomPetRemoteState;", "snapshotRemoteStates", "()Ljava/util/Map;", "pushState", "hasLocalIdentity", "sendHello", "type", "sendPacket", "rawPacket", "handlePacket", "Lcom/google/gson/JsonObject;", "packet", "Lcom/google/gson/JsonArray;", "readPets", "(Lcom/google/gson/JsonObject;)Lcom/google/gson/JsonArray;", "readRemoved", "pets", "applyFullSnapshot", "(Lcom/google/gson/JsonArray;)V", "removed", "applyDelta", "(Lcom/google/gson/JsonArray;Lcom/google/gson/JsonArray;)V", "buildIdentityKey", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "value", "maxLength", "normalize", "(Ljava/lang/String;I)Ljava/lang/String;", "objectJson", "shortKey", "longKey", "readString", "(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "readBoolean", "(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)Z", "fallback", "readDouble", "(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;D)D", "readFloat", "(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;F)F", "Lcom/google/gson/Gson;", "gson", "Lcom/google/gson/Gson;", "Ljava/net/http/HttpClient;", "kotlin.jvm.PlatformType", "httpClient", "Ljava/net/http/HttpClient;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "connected", "Ljava/util/concurrent/atomic/AtomicBoolean;", "connecting", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "packetBuffer", "Ljava/lang/StringBuilder;", "Ljava/util/concurrent/ConcurrentMap;", "remoteStates", "Ljava/util/concurrent/ConcurrentMap;", "Ljava/net/http/WebSocket;", "socket", "Ljava/net/http/WebSocket;", "manualClose", "Z", "identityKey", "Ljava/lang/String;", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;", "I", "D", "F", "SocketListener", "rtx.kimiko:kimiko"})
public final class CustomPetSyncClient {
    @NotNull
    private final Gson gson = new Gson();
    private final HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(6L)).build();
    @NotNull
    private final AtomicBoolean connected = new AtomicBoolean(false);
    @NotNull
    private final AtomicBoolean connecting = new AtomicBoolean(false);
    @NotNull
    private final StringBuilder packetBuffer = new StringBuilder();
    @NotNull
    private final ConcurrentMap<String, CustomPetRemoteState> remoteStates = new ConcurrentHashMap();
    @Nullable
    private volatile WebSocket socket;
    private volatile boolean manualClose;
    @NotNull
    private volatile String identityKey = "";
    @NotNull
    private volatile String profileUsername = "";
    @NotNull
    private volatile String minecraftUsername = "";
    @NotNull
    private volatile String world = "";
    @NotNull
    private volatile CustomPetVariant variant = CustomPetVariant.NITWIT;
    private volatile int robotType;
    @NotNull
    private volatile String petKind = "frog";
    private volatile boolean active;
    private volatile double x;
    private volatile double y;
    private volatile double z;
    private volatile float yaw;
    private volatile boolean moving;
    private volatile boolean umbrella;
    private volatile boolean airborne;
    private volatile double animationSpeed = 1.0;

    public final void setLocalState(@Nullable String profileUsername, @Nullable String minecraftUsername, @Nullable String world, @Nullable CustomPetVariant variant, int robotType, @Nullable String petKind, boolean active, double x, double y, double z, float yaw, boolean moving, boolean umbrella, boolean airborne, double animationSpeed) {
        this.profileUsername = this.normalize(profileUsername, 48);
        this.minecraftUsername = this.normalize(minecraftUsername, 32);
        this.world = this.normalize(world, 64);
        this.identityKey = this.buildIdentityKey(this.profileUsername, this.minecraftUsername);
        CustomPetVariant customPetVariant = variant;
        if (customPetVariant == null) {
            customPetVariant = CustomPetVariant.NITWIT;
        }
        this.variant = customPetVariant;
        this.robotType = robotType;
        CharSequence charSequence = petKind;
        this.petKind = charSequence == null || StringsKt.isBlank((CharSequence)charSequence) ? "frog" : petKind;
        this.active = active;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.moving = moving;
        this.umbrella = umbrella;
        this.airborne = airborne;
        this.animationSpeed = animationSpeed <= 0.0 ? 1.0 : animationSpeed;
    }

    public final synchronized void connect(@NotNull String string, int n) {
    }

    public final synchronized void disconnect(@Nullable String reason) {
        this.manualClose = true;
        this.connecting.set(false);
        this.connected.set(false);
        this.remoteStates.clear();
        WebSocket current = this.socket;
        this.socket = null;
        if (current != null) {
            try {
                current.sendClose(1000, this.normalize(reason, 120));
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

    @NotNull
    public final Map<String, CustomPetRemoteState> snapshotRemoteStates() {
        return new HashMap(this.remoteStates);
    }

    public final void pushState() {
        this.sendPacket("state");
    }

    private final boolean hasLocalIdentity() {
        return !StringsKt.isBlank((CharSequence)this.profileUsername) && !StringsKt.isBlank((CharSequence)this.minecraftUsername) && !StringsKt.isBlank((CharSequence)this.identityKey);
    }

    private final void sendHello() {
        this.sendPacket("hello");
    }

    private final void sendPacket(String type) {
        WebSocket current = this.socket;
        if (!this.connected.get() || current == null || !this.hasLocalIdentity()) {
            return;
        }
        JsonObject packet = new JsonObject();
        packet.addProperty("t", Intrinsics.areEqual((Object)"hello", (Object)type) ? "h" : "u");
        packet.addProperty("i", this.identityKey);
        packet.addProperty("p", this.profileUsername);
        packet.addProperty("n", this.minecraftUsername);
        packet.addProperty("w", this.world);
        packet.addProperty("v", this.variant.name());
        packet.addProperty("rt", (Number)this.robotType);
        packet.addProperty("k", this.petKind);
        packet.addProperty("a", Boolean.valueOf(this.active));
        packet.addProperty("x", (Number)this.x);
        packet.addProperty("y", (Number)this.y);
        packet.addProperty("z", (Number)this.z);
        packet.addProperty("r", (Number)Float.valueOf(this.yaw));
        packet.addProperty("m", Boolean.valueOf(this.moving));
        packet.addProperty("u", Boolean.valueOf(this.umbrella));
        packet.addProperty("b", Boolean.valueOf(this.airborne));
        packet.addProperty("s", (Number)this.animationSpeed);
        current.sendText(this.gson.toJson((JsonElement)packet), true);
    }

    private final void handlePacket(String rawPacket) {
        CharSequence charSequence = rawPacket;
        if (charSequence == null || StringsKt.isBlank((CharSequence)charSequence)) {
            return;
        }
        try {
            JsonObject packet = JsonParser.parseString((String)rawPacket).getAsJsonObject();
            Intrinsics.checkNotNull((Object)packet);
            String type = this.readString(packet, "t", "type");
            if (StringsKt.equals((String)"s", (String)type, (boolean)true) || StringsKt.equals((String)"snapshot", (String)type, (boolean)true)) {
                this.applyFullSnapshot(this.readPets(packet));
            } else if (StringsKt.equals((String)"d", (String)type, (boolean)true) || StringsKt.equals((String)"delta", (String)type, (boolean)true)) {
                this.applyDelta(this.readPets(packet), this.readRemoved(packet));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private final JsonArray readPets(JsonObject packet) {
        if (packet.has("p") && packet.get("p").isJsonArray()) {
            JsonArray jsonArray = packet.getAsJsonArray("p");
            Intrinsics.checkNotNullExpressionValue((Object)jsonArray, (String)"getAsJsonArray(...)");
            return jsonArray;
        }
        if (packet.has("pets") && packet.get("pets").isJsonArray()) {
            JsonArray jsonArray = packet.getAsJsonArray("pets");
            Intrinsics.checkNotNullExpressionValue((Object)jsonArray, (String)"getAsJsonArray(...)");
            return jsonArray;
        }
        return new JsonArray();
    }

    private final JsonArray readRemoved(JsonObject packet) {
        if (packet.has("r") && packet.get("r").isJsonArray()) {
            JsonArray jsonArray = packet.getAsJsonArray("r");
            Intrinsics.checkNotNullExpressionValue((Object)jsonArray, (String)"getAsJsonArray(...)");
            return jsonArray;
        }
        if (packet.has("removed") && packet.get("removed").isJsonArray()) {
            JsonArray jsonArray = packet.getAsJsonArray("removed");
            Intrinsics.checkNotNullExpressionValue((Object)jsonArray, (String)"getAsJsonArray(...)");
            return jsonArray;
        }
        return new JsonArray();
    }

    private final void applyFullSnapshot(JsonArray pets) {
        this.remoteStates.clear();
        this.applyDelta(pets, new JsonArray());
    }

    private final void applyDelta(JsonArray pets, JsonArray removed) {
        HashMap<String, CustomPetRemoteState> updates = new HashMap<>();
        Iterator iterator = pets.iterator();
        Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
        Iterator iterator2 = iterator;
        while (iterator2.hasNext()) {
            JsonElement jsonElement = (JsonElement)iterator2.next();
            if (!jsonElement.isJsonObject()) continue;
            JsonObject pet = jsonElement.getAsJsonObject();
            Intrinsics.checkNotNull((Object)pet);
            String profileUsername = this.normalize(this.readString(pet, "p", "profileUsername"), 48);
            String minecraftUsername = this.normalize(this.readString(pet, "n", "minecraftUsername"), 32);
            String identityKey = this.normalize(this.readString(pet, "i", "identityKey"), 96);
            if (StringsKt.isBlank((CharSequence)identityKey)) {
                identityKey = this.buildIdentityKey(profileUsername, minecraftUsername);
            }
            CustomPetVariant variant = CustomPetVariant.Companion.fromSerializedName(this.readString(pet, "v", "variant"));
            int robotType = (int)this.readDouble(pet, "rt", "robotType", 0.0);
            String petKind = this.normalize(this.readString(pet, "k", "petKind"), 12);
            boolean active = this.readBoolean(pet, "a", "active");
            double x = this.readDouble(pet, "x", "x", 0.0);
            double y = this.readDouble(pet, "y", "y", 0.0);
            double z = this.readDouble(pet, "z", "z", 0.0);
            float yaw = this.readFloat(pet, "r", "yaw", 0.0f);
            boolean moving = this.readBoolean(pet, "m", "moving");
            boolean umbrella = this.readBoolean(pet, "u", "umbrella");
            boolean airborne = this.readBoolean(pet, "b", "airborne");
            double animationSpeed = this.readDouble(pet, "s", "animationSpeed", 1.0);
            if (StringsKt.isBlank((CharSequence)identityKey) || StringsKt.isBlank((CharSequence)profileUsername) || StringsKt.isBlank((CharSequence)minecraftUsername)) continue;
            updates.put(identityKey, new CustomPetRemoteState(identityKey, profileUsername, minecraftUsername, variant, robotType, active, x, y, z, yaw, moving, umbrella, airborne, animationSpeed, petKind));
        }
        this.remoteStates.putAll(updates);
        Iterator iterator3 = removed.iterator();
        Intrinsics.checkNotNullExpressionValue((Object)iterator3, (String)"iterator(...)");
        iterator2 = iterator3;
        while (iterator2.hasNext()) {
            String identityKey;
            JsonElement jsonElement = (JsonElement)iterator2.next();
            if (!jsonElement.isJsonPrimitive() || !(!StringsKt.isBlank((CharSequence)(identityKey = this.normalize(jsonElement.getAsString(), 96))))) continue;
            this.remoteStates.remove(identityKey);
        }
    }

    private final String buildIdentityKey(String profileUsername, String minecraftUsername) {
        if (StringsKt.isBlank((CharSequence)profileUsername) || StringsKt.isBlank((CharSequence)minecraftUsername)) {
            return "";
        }
        return profileUsername + "|" + minecraftUsername;
    }

    private final String normalize(String value, int maxLength) {
        if (value == null) {
            return "";
        }
        String normalized = ((Object)StringsKt.trim((CharSequence)value)).toString();
        if (normalized.length() > maxLength) {
            String string = normalized.substring(0, maxLength);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            return string;
        }
        return normalized;
    }

    private final String readString(JsonObject objectJson, String shortKey, String longKey) {
        if (objectJson.has(shortKey) && objectJson.get(shortKey).isJsonPrimitive()) {
            String string = objectJson.get(shortKey).getAsString();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getAsString(...)");
            return string;
        }
        if (objectJson.has(longKey) && objectJson.get(longKey).isJsonPrimitive()) {
            String string = objectJson.get(longKey).getAsString();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getAsString(...)");
            return string;
        }
        return "";
    }

    private final boolean readBoolean(JsonObject objectJson, String shortKey, String longKey) {
        if (objectJson.has(shortKey) && objectJson.get(shortKey).isJsonPrimitive()) {
            return objectJson.get(shortKey).getAsBoolean();
        }
        if (objectJson.has(longKey) && objectJson.get(longKey).isJsonPrimitive()) {
            return objectJson.get(longKey).getAsBoolean();
        }
        return false;
    }

    private final double readDouble(JsonObject objectJson, String shortKey, String longKey, double fallback) {
        if (objectJson.has(shortKey) && objectJson.get(shortKey).isJsonPrimitive()) {
            return objectJson.get(shortKey).getAsDouble();
        }
        if (objectJson.has(longKey) && objectJson.get(longKey).isJsonPrimitive()) {
            return objectJson.get(longKey).getAsDouble();
        }
        return fallback;
    }

    private final float readFloat(JsonObject objectJson, String shortKey, String longKey, float fallback) {
        if (objectJson.has(shortKey) && objectJson.get(shortKey).isJsonPrimitive()) {
            return objectJson.get(shortKey).getAsFloat();
        }
        if (objectJson.has(longKey) && objectJson.get(longKey).isJsonPrimitive()) {
            return objectJson.get(longKey).getAsFloat();
        }
        return fallback;
    }

    private static final Unit connect$lambda$0(CustomPetSyncClient this$0, WebSocket webSocket, Throwable throwable) {
        if (throwable != null) {
            this$0.connecting.set(false);
            this$0.connected.set(false);
            this$0.socket = null;
            this$0.remoteStates.clear();
            return Unit.INSTANCE;
        }
        this$0.socket = webSocket;
        return Unit.INSTANCE;
    }

    private static final void connect$lambda$1(Function2 $tmp0, Object p0, Object p1) {
        $tmp0.invoke(p0, p1);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ-\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ-\u0010\u0014\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0016H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/custompet/sync/CustomPetSyncClient$SocketListener;", "Ljava/net/http/WebSocket$Listener;", "<init>", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/sync/CustomPetSyncClient;)V", "Ljava/net/http/WebSocket;", "webSocket", "", "onOpen", "(Ljava/net/http/WebSocket;)V", "", "data", "", "last", "Ljava/util/concurrent/CompletionStage;", "onText", "(Ljava/net/http/WebSocket;Ljava/lang/CharSequence;Z)Ljava/util/concurrent/CompletionStage;", "", "statusCode", "", "reason", "onClose", "(Ljava/net/http/WebSocket;ILjava/lang/String;)Ljava/util/concurrent/CompletionStage;", "", "error", "onError", "(Ljava/net/http/WebSocket;Ljava/lang/Throwable;)V", "rtx.kimiko:kimiko"})
    private final class SocketListener
    implements WebSocket.Listener {
        @Override
        public void onOpen(@NotNull WebSocket webSocket) {
            Intrinsics.checkNotNullParameter((Object)webSocket, (String)"webSocket");
            CustomPetSyncClient.this.socket = webSocket;
            CustomPetSyncClient.this.connecting.set(false);
            CustomPetSyncClient.this.connected.set(true);
            webSocket.request(1L);
            CustomPetSyncClient.this.sendHello();
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        @Nullable
        public CompletionStage<?> onText(@NotNull WebSocket webSocket, @NotNull CharSequence data, boolean last) {
            Intrinsics.checkNotNullParameter((Object)webSocket, (String)"webSocket");
            Intrinsics.checkNotNullParameter((Object)data, (String)"data");
            StringBuilder stringBuilder = CustomPetSyncClient.this.packetBuffer;
            CustomPetSyncClient customPetSyncClient = CustomPetSyncClient.this;
            StringBuilder stringBuilder2 = stringBuilder;
            synchronized (stringBuilder2) {
                boolean bl = false;
                customPetSyncClient.packetBuffer.append(data);
                if (last) {
                    String string = customPetSyncClient.packetBuffer.toString();
                    Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
                    String packet = string;
                    customPetSyncClient.packetBuffer.setLength(0);
                    customPetSyncClient.handlePacket(packet);
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
            CustomPetSyncClient.this.connecting.set(false);
            CustomPetSyncClient.this.connected.set(false);
            CustomPetSyncClient.this.socket = null;
            CustomPetSyncClient.this.remoteStates.clear();
            return WebSocket.Listener.super.onClose(webSocket, statusCode, reason);
        }

        @Override
        public void onError(@NotNull WebSocket webSocket, @NotNull Throwable error) {
            Intrinsics.checkNotNullParameter((Object)webSocket, (String)"webSocket");
            Intrinsics.checkNotNullParameter((Object)error, (String)"error");
            CustomPetSyncClient.this.connecting.set(false);
            CustomPetSyncClient.this.connected.set(false);
            CustomPetSyncClient.this.socket = null;
            if (!CustomPetSyncClient.this.manualClose) {
                CustomPetSyncClient.this.remoteStates.clear();
            }
        }
    }
}

