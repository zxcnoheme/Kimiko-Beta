/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Visuals.cosmetics;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import recovery.privacy.NetworkPolicy;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.utils.profile.ProfileIdentity;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0007\u0018\u0000 32\u00020\u0001:\u00013B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003J\r\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\r\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0006\u00a2\u0006\u0004\b\f\u0010\bJ\r\u0010\r\u001a\u00020\t\u00a2\u0006\u0004\b\r\u0010\u000bJ\r\u0010\u000e\u001a\u00020\u0006\u00a2\u0006\u0004\b\u000e\u0010\bJ\u001b\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fH\u0007b\u0002\b\u0011\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u0003J\u001f\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\u0017\u0010\"\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\"\u0010#J\u0019\u0010$\u001a\u0004\u0018\u00010\u00062\u0006\u0010!\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b$\u0010#J\u0017\u0010%\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b%\u0010#R\u001c\u0010(\u001a\n '*\u0004\u0018\u00010&0&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010,\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010+R\u0016\u0010.\u001a\u00020-8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b.\u0010/R\u0016\u00100\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0007\u00102R\u0016\u0010\n\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\n\u0010+R\u0016\u0010\f\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\f\u00102R\u0016\u0010\r\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\r\u0010+R\u0016\u0010\u000e\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000e\u00102\u00a8\u00064"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/cosmetics/CosmeticSiteState;", "", "<init>", "()V", "", "start", "", "headAccessory", "()Ljava/lang/String;", "", "wingsOn", "()Z", "bodyModel", "goatOn", "petKind", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "", "uid", "poll", "(I)V", "body", "apply", "(Ljava/lang/String;)V", "clear", "Lcom/google/gson/JsonObject;", "equipped", "category", "readId", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/String;", "id", "mapHead", "(Ljava/lang/String;)Ljava/lang/String;", "mapWings", "mapPet", "Ljava/net/http/HttpClient;", "kotlin.jvm.PlatformType", "httpClient", "Ljava/net/http/HttpClient;", "started", "Z", "requestInFlight", "", "nextPollAt", "J", "lastUid", "I", "Ljava/lang/String;", "Companion", "rtx.kimiko:kimiko"})
public final class CosmeticSiteState {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(6L)).build();
    private volatile boolean started;
    private volatile boolean requestInFlight;
    private long nextPollAt;
    private int lastUid = -1;
    @NotNull
    private volatile String headAccessory = "none";
    private volatile boolean wingsOn;
    @NotNull
    private volatile String bodyModel = "royal";
    private volatile boolean goatOn;
    @NotNull
    private volatile String petKind = "";
    @NotNull
    private static final CosmeticSiteState INSTANCE = new CosmeticSiteState();
    @NotNull
    private static final String BASE_URL = "https://kimiko.tech/api/cosmetics/state/";
    @NotNull
    private static final String PRODUCT = "kimiko";
    private static final long POLL_INTERVAL_MS = 2500L;

    private CosmeticSiteState() {
    }

    public final void start() {
    }

    @NotNull
    public final String headAccessory() {
        return this.headAccessory;
    }

    public final boolean wingsOn() {
        return this.wingsOn;
    }

    @NotNull
    public final String bodyModel() {
        return this.bodyModel;
    }

    public final boolean goatOn() {
        return this.goatOn;
    }

    @NotNull
    public final String petKind() {
        return this.petKind;
    }

    @EventHandler
    public final void onTick(@NotNull TickEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!event.isPre()) {
            return;
        }
        int uid = ProfileIdentity.uid();
        if (uid <= 0) {
            this.clear();
            this.lastUid = uid;
            return;
        }
        if (uid != this.lastUid) {
            this.lastUid = uid;
            this.nextPollAt = 0L;
        }
        long now = System.currentTimeMillis();
        if (this.requestInFlight || now < this.nextPollAt) {
            return;
        }
        this.nextPollAt = now + 2500L;
        this.poll(uid);
    }

    private final void poll(int uid) {
        HttpRequest httpRequest;
        try {
            httpRequest = HttpRequest.newBuilder(URI.create(BASE_URL + uid)).header("x-product", PRODUCT).timeout(Duration.ofSeconds(6L)).GET().build();
            Intrinsics.checkNotNull((Object)httpRequest);
        }
        catch (Exception ignored) {
            return;
        }
        HttpRequest request = httpRequest;
        this.requestInFlight = true;
        NetworkPolicy.sendAsync(this.httpClient, request, HttpResponse.BodyHandlers.ofString()).whenComplete((response, error) -> {
            try {
                if (error == null && response != null && response.statusCode() == 200) {
                    this.apply(response.body());
                }
            } catch (Throwable ignored) {
            } finally {
                this.requestInFlight = false;
            }
        });
    }

    private final void apply(String body) {
        JsonObject jsonObject;
        try {
            JsonObject root = JsonParser.parseString((String)body).getAsJsonObject();
            jsonObject = root.has("equipped") && root.get("equipped").isJsonObject() ? root.getAsJsonObject("equipped") : new JsonObject();
            Intrinsics.checkNotNull((Object)jsonObject);
        }
        catch (Exception ignored) {
            return;
        }
        JsonObject equipped = jsonObject;
        this.headAccessory = this.mapHead(this.readId(equipped, "head"));
        String wings = this.mapWings(this.readId(equipped, "wings"));
        this.wingsOn = wings != null;
        String string = wings;
        if (string == null) {
            string = "royal";
        }
        this.bodyModel = string;
        this.goatOn = Intrinsics.areEqual((Object)"shoulder-goat", (Object)this.readId(equipped, "shoulder"));
        this.petKind = this.mapPet(this.readId(equipped, "pets"));
    }

    private final void clear() {
        this.headAccessory = "none";
        this.wingsOn = false;
        this.bodyModel = "royal";
        this.goatOn = false;
        this.petKind = "";
    }

    private final String readId(JsonObject equipped, String category) {
        String string;
        if (equipped.has(category) && equipped.get(category).isJsonPrimitive()) {
            String string2 = equipped.get(category).getAsString();
            Intrinsics.checkNotNull((Object)string2);
            string = string2;
        } else {
            string = "";
        }
        return string;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final String mapHead(String id) {
        String string = id;
        switch (string.hashCode()) {
            case 1466119507: {
                if (string.equals("fluger-hat")) return "hat";
                return "none";
            }
            case -1847219584: {
                if (string.equals("golden-halo")) return "halo";
                return "none";
            }
            case 94935223: {
                if (!string.equals("crown")) return "none";
                return "crown";
            }
        }
        return "none";
    }

    private final String mapWings(String id) {
        return switch (id) {
            case "wings" -> "royal";
            case "seraph-wings" -> "seraph";
            case "feathered-wings" -> "feathered";
            case "kaneki-kagune" -> "kagune";
            default -> null;
        };
    }

    private final String mapPet(String id) {
        return switch (id) {
            case "frog" -> "frog";
            case "robot" -> "robot";
            case "owl-jump-rope" -> "owl";
            case "chekushka" -> "chekushka";
            case "bigear-goat" -> "goat";
            case "nightmare-bb" -> "nightmare_bb";
            case "ufo-pet" -> "ufo";
            default -> "";
        };
    }


    @JvmStatic
    @NotNull
    public static final CosmeticSiteState get() {
        return Companion.get();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010\tR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\fR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/cosmetics/CosmeticSiteState.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/cosmetics/CosmeticSiteState;", "Lkotlin/jvm/JvmStatic;", "get", "()Lrtx/kimiko/api/modules/impl/Visuals/cosmetics/CosmeticSiteState;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/cosmetics/CosmeticSiteState;", "", "BASE_URL", "Ljava/lang/String;", "PRODUCT", "", "POLL_INTERVAL_MS", "J", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final CosmeticSiteState get() {
            return INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

