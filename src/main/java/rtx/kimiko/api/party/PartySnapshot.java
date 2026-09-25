/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.party;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.party.PartyMember;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B1\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\f\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u000f\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0010J\u000f\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0010J\r\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0011J\u0013\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0004\b\t\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0016R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u0016R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0017R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0018\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/party/PartySnapshot;", "", "", "name", "leader", "", "max", "", "Lrtx/kimiko/api/party/PartyMember;", "members", "<init>", "(Ljava/lang/String;Ljava/lang/String;ILjava/util/List;)V", "", "exists", "()Z", "inParty", "()Ljava/lang/String;", "()I", "()Ljava/util/List;", "playerName", "isLeader", "(Ljava/lang/String;)Z", "Ljava/lang/String;", "I", "Ljava/util/List;", "Companion", "rtx.kimiko:kimiko"})
public final class PartySnapshot {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    private final String name;
    @Nullable
    private final String leader;
    private final int max;
    @NotNull
    private final List<PartyMember> members;
    @JvmField
    @NotNull
    public static final PartySnapshot NONE;

    public PartySnapshot(@Nullable String name, @Nullable String leader, int max, @NotNull List<PartyMember> members) {
        Intrinsics.checkNotNullParameter(members, (String)"members");
        this.name = name;
        this.leader = leader;
        this.max = max;
        this.members = members;
    }

    public final boolean exists() {
        return this.name != null;
    }

    public final boolean inParty() {
        return this.exists();
    }

    @Nullable
    public final String name() {
        return this.name;
    }

    @Nullable
    public final String leader() {
        return this.leader;
    }

    public final int max() {
        return this.max;
    }

    @NotNull
    public final List<PartyMember> members() {
        return this.members;
    }

    public final boolean isLeader(@Nullable String playerName) {
        return this.leader != null && playerName != null && StringsKt.equals((String)this.leader, (String)playerName, (boolean)true);
    }

    @JvmStatic
    @NotNull
    public static final PartySnapshot fromJson(@Nullable JsonObject party) {
        return Companion.fromJson(party);
    }

    static {
        List<PartyMember> list = Collections.emptyList();
        Intrinsics.checkNotNullExpressionValue(list, (String)"emptyList(...)");
        NONE = new PartySnapshot(null, null, 10, list);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tR\u0019\u0010\u000b\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\b\n\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2={"Lrtx/kimiko/api/party/PartySnapshot.Companion;", "", "<init>", "()V", "Lcom/google/gson/JsonObject;", "party", "Lrtx/kimiko/api/party/PartySnapshot;", "Lkotlin/jvm/JvmStatic;", "fromJson", "(Lcom/google/gson/JsonObject;)Lrtx/kimiko/api/party/PartySnapshot;", "Lkotlin/jvm/JvmField;", "NONE", "Lrtx/kimiko/api/party/PartySnapshot;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final PartySnapshot fromJson(@Nullable JsonObject party) {
            if (party == null || party.isJsonNull() || !party.has("name") || party.get("name").isJsonNull()) {
                return NONE;
            }
            String name = party.get("name").getAsString();
            String leader = party.has("leader") ? party.get("leader").getAsString() : "";
            int max = party.has("max") && party.get("max").isJsonPrimitive() ? party.get("max").getAsInt() : 10;
            ArrayList<PartyMember> members = new ArrayList<PartyMember>();
            if (party.has("members") && party.get("members").isJsonArray()) {
                JsonArray arr = party.getAsJsonArray("members");
                Iterator iterator = arr.iterator();
                Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
                Iterator iterator2 = iterator;
                while (iterator2.hasNext()) {
                    boolean on;
                    JsonElement el = (JsonElement)iterator2.next();
                    if (!el.isJsonObject()) continue;
                    JsonObject m = el.getAsJsonObject();
                    String mn = m.has("name") ? m.get("name").getAsString() : "?";
                    boolean ld = m.has("leader") && m.get("leader").getAsBoolean();
                    boolean bl = on = m.has("online") && m.get("online").getAsBoolean();
                    String wr = m.has("world") ? m.get("world").getAsString() : (m.has("dim") ? m.get("dim").getAsString() : "");
                    double x = m.has("x") ? m.get("x").getAsDouble() : 0.0;
                    double y = m.has("y") ? m.get("y").getAsDouble() : 0.0;
                    double z = m.has("z") ? m.get("z").getAsDouble() : 0.0;
                    float hp = m.has("hp") ? m.get("hp").getAsFloat() : 20.0f;
                    Intrinsics.checkNotNull((Object)mn);
                    Intrinsics.checkNotNull((Object)wr);
                    members.add(new PartyMember(mn, ld, on, wr, x, y, z, hp));
                }
            }
            return new PartySnapshot(name, leader, max, (List<PartyMember>)members);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

