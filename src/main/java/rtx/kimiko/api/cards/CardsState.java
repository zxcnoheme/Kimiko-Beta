/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.cards;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.cards.Card;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u0000 <2\u00020\u0001:\u0002=<B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0006\u00a2\u0006\u0004\b\t\u0010\bJ\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000e\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\n\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\n\u00a2\u0006\u0004\b\u0011\u0010\u000fJ\u0015\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\n\u00a2\u0006\u0004\b\u0012\u0010\u000fJ\u001d\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\n\u00a2\u0006\u0004\b\u0016\u0010\u000fJ\r\u0010\u0017\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0017\u0010\bJ\r\u0010\u0018\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0018\u0010\bJ\r\u0010\u0019\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0019\u0010\bR\u0019\u0010\u001c\u001a\u00020\u001a8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0019\u0010\u001f\u001a\u00020\u001e8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0019\u0010!\u001a\u00020\u001e8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b!\u0010 R\u0019\u0010\"\u001a\u00020\u001e8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b\"\u0010 R\u0019\u0010#\u001a\u00020\u001e8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b#\u0010 R\u0019\u0010$\u001a\u00020\u001e8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b$\u0010 R\u0019\u0010%\u001a\u00020\n8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0019\u0010'\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u001f\u0010*\u001a\b\u0012\u0004\u0012\u00020\n0)8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u001f\u0010-\u001a\b\u0012\u0004\u0012\u00020,0)8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b-\u0010+R\u0019\u0010.\u001a\u00020\n8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b.\u0010&R\u0019\u0010/\u001a\u00020\n8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b/\u0010&R\u0019\u00100\u001a\u00020\n8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b0\u0010&R\u0019\u00101\u001a\u00020\n8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b1\u0010&R\u0019\u00102\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b2\u0010(R\u0019\u00103\u001a\u00020\u001e8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b3\u0010 R\u0019\u00104\u001a\u00020\u001e8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b4\u0010 R\u0019\u00105\u001a\u00020\u001a8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b5\u0010\u001dR\u0019\u00106\u001a\u00020\u001a8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b6\u0010\u001dR\u0019\u00107\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b7\u0010(R\u0019\u00108\u001a\u00020\u001e8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b8\u0010 R\u0019\u00109\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b9\u0010(R\u0019\u0010:\u001a\u00020\n8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b:\u0010&R\u0019\u0010;\u001a\u00020\n8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b;\u0010&\u00a8\u0006>"}, d2={"Lrtx/kimiko/api/cards/CardsState;", "", "Lcom/google/gson/JsonObject;", "o", "<init>", "(Lcom/google/gson/JsonObject;)V", "", "youAttack", "()Z", "yourClock", "", "undefended", "()I", "rank", "rankOnTable", "(I)Z", "card", "canAttackWith", "canTransferWith", "slot", "canDefendWith", "(II)Z", "canDefendSomething", "canTake", "canDone", "youWon", "", "Lkotlin/jvm/JvmField;", "seq", "J", "", "room", "Ljava/lang/String;", "you", "opponentId", "opponentName", "opponentAvatar", "opponentCards", "I", "opponentOnline", "Z", "", "hand", "Ljava/util/List;", "Lrtx/kimiko/api/cards/CardsState$Pair;", "table", "deckCount", "trumpCard", "trumpSuit", "discardCount", "taking", "attacker", "actor", "deadlineAt", "deadlineTotal", "over", "winner", "draw", "scoreYou", "scoreOpponent", "Companion", "Pair", "rtx.kimiko:kimiko"})
public final class CardsState {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    public final long seq;
    @JvmField
    @NotNull
    public final String room;
    @JvmField
    @NotNull
    public final String you;
    @JvmField
    @NotNull
    public final String opponentId;
    @JvmField
    @NotNull
    public final String opponentName;
    @JvmField
    @NotNull
    public final String opponentAvatar;
    @JvmField
    public final int opponentCards;
    @JvmField
    public final boolean opponentOnline;
    @JvmField
    @NotNull
    public final List<Integer> hand;
    @JvmField
    @NotNull
    public final List<Pair> table;
    @JvmField
    public final int deckCount;
    @JvmField
    public final int trumpCard;
    @JvmField
    public final int trumpSuit;
    @JvmField
    public final int discardCount;
    @JvmField
    public final boolean taking;
    @JvmField
    @NotNull
    public final String attacker;
    @JvmField
    @NotNull
    public final String actor;
    @JvmField
    public final long deadlineAt;
    @JvmField
    public final long deadlineTotal;
    @JvmField
    public final boolean over;
    @JvmField
    @NotNull
    public final String winner;
    @JvmField
    public final boolean draw;
    @JvmField
    public final int scoreYou;
    @JvmField
    public final int scoreOpponent;

    private CardsState(JsonObject o) {
        this.seq = CardsState.Companion.jLong(o, "seq");
        this.room = CardsState.Companion.jStr(o, "room");
        this.you = CardsState.Companion.jStr(o, "you");
        JsonObject opp = o.has("opponent") && o.get("opponent").isJsonObject() ? o.getAsJsonObject("opponent") : new JsonObject();
        Intrinsics.checkNotNull((Object)opp);
        this.opponentId = CardsState.Companion.jStr(opp, "id");
        this.opponentName = CardsState.Companion.jStr(opp, "name");
        this.opponentAvatar = CardsState.Companion.jStr(opp, "avatar");
        this.opponentCards = (int)CardsState.Companion.jLong(opp, "cards");
        this.opponentOnline = !opp.has("online") || opp.get("online").getAsBoolean();
        this.hand = new ArrayList();
        if (o.has("hand") && o.get("hand").isJsonArray()) {
            Iterator iterator = o.getAsJsonArray("hand").iterator();
            Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
            Iterator iterator2 = iterator;
            while (iterator2.hasNext()) {
                JsonElement e = (JsonElement)iterator2.next();
                ((ArrayList)this.hand).add(e.getAsInt());
            }
        }
        this.table = new ArrayList();
        if (o.has("table") && o.get("table").isJsonArray()) {
            JsonArray arr = o.getAsJsonArray("table");
            Iterator iterator = arr.iterator();
            Intrinsics.checkNotNullExpressionValue((Object)iterator, (String)"iterator(...)");
            Iterator iterator3 = iterator;
            while (iterator3.hasNext()) {
                JsonElement e = (JsonElement)iterator3.next();
                JsonObject p = e.getAsJsonObject();
                int a = p.has("a") ? p.get("a").getAsInt() : -1;
                int d = p.has("d") && !p.get("d").isJsonNull() ? p.get("d").getAsInt() : -1;
                ((ArrayList)this.table).add(new Pair(a, d));
            }
        }
        this.deckCount = (int)CardsState.Companion.jLong(o, "deckCount");
        this.trumpCard = o.has("trumpCard") ? o.get("trumpCard").getAsInt() : -1;
        this.trumpSuit = (int)CardsState.Companion.jLong(o, "trumpSuit");
        this.discardCount = (int)CardsState.Companion.jLong(o, "discardCount");
        this.taking = o.has("taking") && o.get("taking").getAsBoolean();
        this.attacker = CardsState.Companion.jStr(o, "attacker");
        this.actor = CardsState.Companion.jStr(o, "actor");
        long remaining = CardsState.Companion.jLong(o, "deadline");
        this.deadlineAt = remaining > 0L ? System.currentTimeMillis() + remaining : 0L;
        this.deadlineTotal = remaining;
        this.over = o.has("over") && o.get("over").getAsBoolean();
        this.winner = CardsState.Companion.jStr(o, "winner");
        this.draw = o.has("draw") && o.get("draw").getAsBoolean();
        JsonObject score = o.has("score") && o.get("score").isJsonObject() ? o.getAsJsonObject("score") : new JsonObject();
        Intrinsics.checkNotNull((Object)score);
        this.scoreYou = (int)CardsState.Companion.jLong(score, "you");
        this.scoreOpponent = (int)CardsState.Companion.jLong(score, "opponent");
    }

    public final boolean youAttack() {
        return Intrinsics.areEqual((Object)this.you, (Object)this.attacker);
    }

    public final boolean yourClock() {
        return Intrinsics.areEqual((Object)this.you, (Object)this.actor);
    }

    public final int undefended() {
        int n = 0;
        for (Pair p : this.table) {
            if (p.defended()) continue;
            ++n;
        }
        return n;
    }

    public final boolean rankOnTable(int rank) {
        for (Pair p : this.table) {
            if (Card.rankOf(p.attack()) == rank) {
                return true;
            }
            if (!p.defended() || Card.rankOf(p.defense()) != rank) continue;
            return true;
        }
        return false;
    }

    public final boolean canAttackWith(int card) {
        if (this.over || !this.youAttack() || this.table.size() >= 6) {
            return false;
        }
        if (this.taking) {
            return this.rankOnTable(Card.rankOf(card));
        }
        if (this.undefended() + 1 > this.opponentCards) {
            return false;
        }
        return this.table.isEmpty() || this.rankOnTable(Card.rankOf(card));
    }

    public final boolean canTransferWith(int card) {
        if (this.over || this.youAttack() || this.taking || this.table.isEmpty() || this.table.size() >= 6) {
            return false;
        }
        if (this.undefended() != this.table.size()) {
            return false;
        }
        if (Card.rankOf(card) != Card.rankOf(this.table.get(0).attack())) {
            return false;
        }
        return this.table.size() + 1 <= this.opponentCards;
    }

    public final boolean canDefendWith(int slot, int card) {
        if (this.over || this.youAttack() || this.taking || slot < 0 || slot >= this.table.size()) {
            return false;
        }
        Pair p = this.table.get(slot);
        return !p.defended() && Card.beats(p.attack(), card, this.trumpSuit);
    }

    public final boolean canDefendSomething(int card) {
        int n = ((Collection)this.table).size();
        for (int i = 0; i < n; ++i) {
            if (!this.canDefendWith(i, card)) continue;
            return true;
        }
        return false;
    }

    public final boolean canTake() {
        return !this.over && !this.youAttack() && !this.taking && this.undefended() > 0;
    }

    public final boolean canDone() {
        if (this.over || !this.youAttack()) {
            return false;
        }
        if (this.taking) {
            return true;
        }
        return !((Collection)this.table).isEmpty() && this.undefended() == 0;
    }

    public final boolean youWon() {
        return this.over && !this.draw && Intrinsics.areEqual((Object)this.you, (Object)this.winner);
    }

    @JvmStatic
    @Nullable
    public static final CardsState fromJson(@NotNull JsonObject o) {
        return Companion.fromJson(o);
    }

    public /* synthetic */ CardsState(JsonObject o, DefaultConstructorMarker $constructor_marker) {
        this(o);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001f\u0010\f\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/cards/CardsState.Companion;", "", "<init>", "()V", "Lcom/google/gson/JsonObject;", "o", "Lrtx/kimiko/api/cards/CardsState;", "Lkotlin/jvm/JvmStatic;", "fromJson", "(Lcom/google/gson/JsonObject;)Lrtx/kimiko/api/cards/CardsState;", "", "k", "jStr", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/String;", "", "jLong", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)J", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final CardsState fromJson(@NotNull JsonObject o) {
            CardsState cardsState;
            Intrinsics.checkNotNullParameter((Object)o, (String)"o");
            try {
                cardsState = new CardsState(o, null);
            }
            catch (Throwable t) {
                cardsState = null;
            }
            return cardsState;
        }

        private final String jStr(JsonObject o, String k) {
            String string;
            if (o.has(k) && o.get(k).isJsonPrimitive()) {
                String string2 = o.get(k).getAsString();
                string = string2;
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getAsString(...)");
            } else {
                string = "";
            }
            return string;
        }

        private final long jLong(JsonObject o, String k) {
            long l;
            try {
                l = o.has(k) && o.get(k).isJsonPrimitive() ? o.get(k).getAsLong() : 0L;
            }
            catch (Throwable t) {
                l = 0L;
            }
            return l;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\u000bJ$\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u0010\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0012\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0012\u0010\u000bJ\u0011\u0010\u0014\u001a\u00020\u0013H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0015R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0003\u0010\u000bR%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u0004\u0010\u000b\u00a8\u0006\u0019"}, d2={"Lrtx/kimiko/api/cards/CardsState$Pair;", "", "", "attack", "defense", "<init>", "(II)V", "", "defended", "()Z", "component1", "()I", "component2", "copy", "(II)Lrtx/kimiko/api/cards/CardsState$Pair;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "I", "rtx.kimiko:kimiko"})
    public static final class Pair {
        private final int attack;
        private final int defense;

        public Pair(int attack, int defense) {
            this.attack = attack;
            this.defense = defense;
        }

        @JvmName(name="attack")
        public final int attack() {
            return this.attack;
        }

        @JvmName(name="defense")
        public final int defense() {
            return this.defense;
        }

        public final boolean defended() {
            return this.defense >= 0;
        }

        public final int component1() {
            return this.attack;
        }

        public final int component2() {
            return this.defense;
        }

        @NotNull
        public final Pair copy(int attack, int defense) {
            return new Pair(attack, defense);
        }

        public static /* synthetic */ Pair copy$default(Pair pair, int n, int n2, int n3, Object object) {
            if ((n3 & 1) != 0) {
                n = pair.attack;
            }
            if ((n3 & 2) != 0) {
                n2 = pair.defense;
            }
            return pair.copy(n, n2);
        }

        @NotNull
        public String toString() {
            return "Pair(attack=" + this.attack + ", defense=" + this.defense + ")";
        }

        public int hashCode() {
            int result = Integer.hashCode(this.attack);
            result = result * 31 + Integer.hashCode(this.defense);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Pair)) {
                return false;
            }
            Pair pair = (Pair)other;
            if (this.attack != pair.attack) {
                return false;
            }
            return this.defense == pair.defense;
        }
    }
}

