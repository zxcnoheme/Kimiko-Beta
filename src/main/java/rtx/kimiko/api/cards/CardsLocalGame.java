/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.TypeIntrinsics
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  rtx.kimiko.api.ui.cards.CardsScreen
 */
package rtx.kimiko.api.cards;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.cards.Card;
import rtx.kimiko.api.cards.CardsAvatars;
import rtx.kimiko.api.cards.CardsState;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.ui.cards.CardsScreen;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010!\n\u0002\b(\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0015\n\u0002\b\u001b\u0018\u0000 n2\u00020\u0001:\u0001nB\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\b\u0010\u0005J\u000f\u0010\t\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\t\u0010\nJ%\u0010\u0010\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0014\u0010\nJ\u000f\u0010\u0015\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0015\u0010\nJ\u001d\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\f0\u00172\u0006\u0010\u0016\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u001f\u0010 \u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b \u0010!J'\u0010#\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b#\u0010$J\u001f\u0010%\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b%\u0010!J\u000f\u0010&\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b&\u0010'J\u000f\u0010(\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b(\u0010'J\u000f\u0010)\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b)\u0010\u0013J\u0015\u0010*\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\f\u00a2\u0006\u0004\b*\u0010\u001eJ\u001d\u0010+\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\f\u00a2\u0006\u0004\b+\u0010,J\u0015\u0010-\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\f\u00a2\u0006\u0004\b-\u0010\u001eJ\r\u0010.\u001a\u00020\u000e\u00a2\u0006\u0004\b.\u0010'J\r\u0010/\u001a\u00020\u000e\u00a2\u0006\u0004\b/\u0010'J\r\u00100\u001a\u00020\u000e\u00a2\u0006\u0004\b0\u0010'J\u001f\u00101\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b1\u0010!J'\u00102\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b2\u0010$J\u001f\u00103\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b3\u0010!J\u0017\u00104\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b4\u00105J\u000f\u00106\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b6\u0010\u0013J\u000f\u00107\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b7\u0010\u0013J\u000f\u00108\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b8\u0010\u0013J\u0017\u00109\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b9\u00105J\u000f\u0010:\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b:\u0010\u0013J\u001d\u0010;\u001a\u00020\u00072\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u0017H\u0002\u00a2\u0006\u0004\b;\u0010<J\u000f\u0010=\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b=\u0010\nJ\r\u0010>\u001a\u00020\u0007\u00a2\u0006\u0004\b>\u0010\u0013J\r\u0010?\u001a\u00020\f\u00a2\u0006\u0004\b?\u0010\u001bJ\u0015\u0010B\u001a\u00020\u00072\u0006\u0010A\u001a\u00020@\u00a2\u0006\u0004\bB\u0010CJ\u000f\u0010D\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\bD\u0010\u0013J\u0017\u0010E\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\bE\u0010\u0005J\u000f\u0010F\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\bF\u0010\u0013J\u000f\u0010G\u001a\u00020\fH\u0002\u00a2\u0006\u0004\bG\u0010\u001bJ\u000f\u0010H\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\bH\u0010\u0013J\u000f\u0010J\u001a\u0004\u0018\u00010I\u00a2\u0006\u0004\bJ\u0010KR\u0014\u0010M\u001a\u00020L8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bM\u0010NR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010OR\u001a\u0010P\u001a\b\u0012\u0004\u0012\u00020\f0\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bP\u0010QR\u001a\u0010R\u001a\b\u0012\u0004\u0012\u00020\f0\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bR\u0010QR\u001a\u0010S\u001a\b\u0012\u0004\u0012\u00020\f0\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bS\u0010QR\u001a\u0010U\u001a\b\u0012\u0004\u0012\u00020T0\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bU\u0010QR\u0016\u0010V\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010WR\u0016\u0010X\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bX\u0010WR\u0016\u0010Y\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bY\u0010WR\u0016\u0010Z\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bZ\u0010OR\u0016\u0010[\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b[\u0010\\R\u0016\u0010]\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b]\u0010WR\u0016\u0010^\u001a\u00020@8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b^\u0010_R\u0016\u0010`\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b`\u0010\\R\u0016\u0010a\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\ba\u0010\\R\u0016\u0010b\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bb\u0010OR\u0016\u0010c\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010WR\u0016\u0010d\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bd\u0010WR\u0016\u0010e\u001a\u00020@8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\be\u0010_R\u0016\u0010f\u001a\u00020@8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bf\u0010_R\u0016\u0010g\u001a\u00020@8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bg\u0010_R\u0016\u0010h\u001a\u00020@8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bh\u0010_R\u0016\u0010i\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bi\u0010WR\u0018\u0010j\u001a\u0004\u0018\u00010I8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bj\u0010kR\u0016\u0010l\u001a\u00020@8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bl\u0010_R\u0018\u0010m\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bm\u0010O\u00a8\u0006o"}, d2={"Lrtx/kimiko/api/cards/CardsLocalGame;", "", "", "yourName", "<init>", "(Ljava/lang/String;)V", "firstAttacker", "", "deal", "lowestTrumpHolder", "()Ljava/lang/String;", "", "", "hand", "", "trumpOnly", "lowestOf", "(Ljava/util/List;Z)I", "mutated", "()V", "actor", "defenderId", "id", "", "handOf", "(Ljava/lang/String;)Ljava/util/List;", "undefended", "()I", "rank", "rankOnTable", "(I)Z", "card", "canAttack", "(Ljava/lang/String;I)Z", "slot", "canDefend", "(Ljava/lang/String;II)Z", "canTransfer", "attackerCanAdd", "()Z", "defenderCanRespond", "maybeScheduleAutoTake", "attack", "defend", "(II)Z", "transfer", "take", "done", "rematch", "doAttack", "doDefend", "doTransfer", "doTake", "(Ljava/lang/String;)Z", "clearPending", "resolveTake", "resolveBito", "doDone", "refillAndEvaluate", "refill", "(Ljava/util/List;)V", "takeLocalNotice", "onPlayerEmote", "takeBotEmote", "", "now", "tick", "(J)V", "botFeedTake", "forcedAttack", "botDefend", "botTransferChoice", "botAttack", "Lrtx/kimiko/api/cards/CardsState;", "state", "()Lrtx/kimiko/api/cards/CardsState;", "Ljava/util/Random;", "random", "Ljava/util/Random;", "Ljava/lang/String;", "deck", "Ljava/util/List;", "yourHand", "botHand", "", "table", "trumpCard", "I", "trumpSuit", "discardCount", "attacker", "taking", "Z", "pendingKind", "resolveAt", "J", "over", "draw", "winner", "scoreYou", "scoreBot", "seq", "deadlineAt", "botActAt", "botEmoteAt", "botEmote", "cached", "Lrtx/kimiko/api/cards/CardsState;", "cachedSeq", "localNotice", "Companion", "rtx.kimiko:kimiko"})
public final class CardsLocalGame {
    @NotNull
    private static final Companion Companion = new Companion(null);
    @NotNull
    private final Random random = new Random();
    @NotNull
    private final String yourName;
    @NotNull
    private final List<Integer> deck;
    @NotNull
    private final List<Integer> yourHand;
    @NotNull
    private final List<Integer> botHand;
    @NotNull
    private final List<int[]> table;
    private int trumpCard;
    private int trumpSuit;
    private int discardCount;
    @NotNull
    private String attacker;
    private boolean taking;
    private int pendingKind;
    private long resolveAt;
    private boolean over;
    private boolean draw;
    @NotNull
    private String winner;
    private int scoreYou;
    private int scoreBot;
    private long seq;
    private long deadlineAt;
    private long botActAt;
    private long botEmoteAt;
    private int botEmote;
    @Nullable
    private CardsState cached;
    private long cachedSeq;
    @Nullable
    private String localNotice;
    @NotNull
    private static final String YOU = "local";
    @NotNull
    private static final String BOT = "bot";
    private static final long TURN_MS = 60000L;
    private static final long RESOLVE_DELAY_MS = 1100L;
    private static final long AUTO_TAKE_DELAY_MS = 900L;

    public CardsLocalGame(@Nullable String yourName) {
        this.yourName = yourName == null || StringsKt.isBlank((CharSequence)yourName) ? I18n.tr("Вы") : yourName;
        this.deck = new ArrayList();
        this.yourHand = new ArrayList();
        this.botHand = new ArrayList();
        this.table = new ArrayList();
        this.attacker = YOU;
        this.winner = "";
        this.botEmote = -1;
        this.cachedSeq = -1L;
        this.deal(null);
    }

    private final void deal(String firstAttacker) {
        int i;
        this.deck.clear();
        this.yourHand.clear();
        this.botHand.clear();
        this.table.clear();
        this.taking = false;
        this.pendingKind = 0;
        this.resolveAt = 0L;
        this.discardCount = 0;
        this.over = false;
        this.draw = false;
        this.winner = "";
        for (i = 0; i < 36; ++i) {
            this.deck.add(i);
        }
        Collections.shuffle(this.deck, this.random);
        for (i = 0; i < 6; ++i) {
            this.yourHand.add(this.deck.remove(0));
            this.botHand.add(this.deck.remove(0));
        }
        this.trumpCard = ((Number)this.deck.get(this.deck.size() - 1)).intValue();
        this.trumpSuit = Card.suitOf(this.trumpCard);
        String string = firstAttacker;
        if (string == null) {
            string = this.lowestTrumpHolder();
        }
        this.attacker = string;
        this.mutated();
    }

    private final String lowestTrumpHolder() {
        int botAny;
        int bot;
        int you = this.lowestOf(this.yourHand, true);
        if (you != (bot = this.lowestOf(this.botHand, true))) {
            return you < bot ? YOU : BOT;
        }
        if (you != Integer.MAX_VALUE) {
            return YOU;
        }
        int youAny = this.lowestOf(this.yourHand, false);
        if (youAny != (botAny = this.lowestOf(this.botHand, false))) {
            return youAny < botAny ? YOU : BOT;
        }
        return this.random.nextBoolean() ? YOU : BOT;
    }

    private final int lowestOf(List<Integer> hand, boolean trumpOnly) {
        int min = Integer.MAX_VALUE;
        Iterator<Integer> iterator = hand.iterator();
        while (iterator.hasNext()) {
            int c = ((Number)iterator.next()).intValue();
            if (trumpOnly && Card.suitOf(c) != this.trumpSuit) continue;
            min = Math.min(min, Card.rankOf(c));
        }
        return min;
    }

    private final void mutated() {
        long l = this.seq;
        this.seq = l + 1L;
        this.deadlineAt = System.currentTimeMillis() + 60000L;
        this.botActAt = System.currentTimeMillis() + (long)650 + (long)this.random.nextInt(750);
    }

    private final String actor() {
        if (this.over) {
            return "";
        }
        if (this.taking) {
            return this.attacker;
        }
        return this.undefended() > 0 ? this.defenderId() : this.attacker;
    }

    private final String defenderId() {
        return Intrinsics.areEqual((Object)this.attacker, (Object)YOU) ? BOT : YOU;
    }

    private final List<Integer> handOf(String id) {
        return Intrinsics.areEqual((Object)id, (Object)YOU) ? this.yourHand : this.botHand;
    }

    private final int undefended() {
        int n = 0;
        for (int[] p : this.table) {
            if (p[1] >= 0) continue;
            ++n;
        }
        return n;
    }

    private final boolean rankOnTable(int rank) {
        for (int[] p : this.table) {
            if (Card.rankOf(p[0]) == rank) {
                return true;
            }
            if (p[1] < 0 || Card.rankOf(p[1]) != rank) continue;
            return true;
        }
        return false;
    }

    private final boolean canAttack(String id, int card) {
        if (this.over || !Intrinsics.areEqual((Object)this.attacker, (Object)id) || this.table.size() >= 6) {
            return false;
        }
        if (!this.handOf(id).contains(card)) {
            return false;
        }
        if (this.taking) {
            return this.rankOnTable(Card.rankOf(card));
        }
        if (this.undefended() + 1 > this.handOf(this.defenderId()).size()) {
            return false;
        }
        return this.table.isEmpty() || this.rankOnTable(Card.rankOf(card));
    }

    private final boolean canDefend(String id, int slot, int card) {
        if (this.over || this.taking || Intrinsics.areEqual((Object)this.attacker, (Object)id) || slot < 0 || slot >= this.table.size()) {
            return false;
        }
        if (!this.handOf(id).contains(card)) {
            return false;
        }
        int[] p = this.table.get(slot);
        return p[1] < 0 && Card.beats(p[0], card, this.trumpSuit);
    }

    private final boolean canTransfer(String id, int card) {
        if (this.over || this.taking || Intrinsics.areEqual((Object)this.attacker, (Object)id) || this.table.isEmpty() || this.table.size() >= 6) {
            return false;
        }
        if (!this.handOf(id).contains(card)) {
            return false;
        }
        if (this.undefended() != this.table.size()) {
            return false;
        }
        if (Card.rankOf(card) != Card.rankOf(this.table.get(0)[0])) {
            return false;
        }
        return this.table.size() + 1 <= this.handOf(this.attacker).size();
    }

    private final boolean attackerCanAdd() {
        if (this.table.size() >= 6) {
            return false;
        }
        Iterator<Integer> iterator = this.handOf(this.attacker).iterator();
        while (iterator.hasNext()) {
            int c = ((Number)iterator.next()).intValue();
            if (!this.rankOnTable(Card.rankOf(c))) continue;
            return true;
        }
        return false;
    }

    private final boolean defenderCanRespond() {
        List<Integer> hand = this.handOf(this.defenderId());
        for (int[] p : this.table) {
            if (p[1] >= 0) continue;
            Iterator<Integer> iterator = hand.iterator();
            while (iterator.hasNext()) {
                int c = ((Number)iterator.next()).intValue();
                if (!Card.beats(p[0], c, this.trumpSuit)) continue;
                return true;
            }
        }
        if (!((Collection)this.table).isEmpty() && this.undefended() == this.table.size() && this.table.size() < 6 && this.table.size() + 1 <= this.handOf(this.attacker).size()) {
            int r = Card.rankOf(this.table.get(0)[0]);
            Iterator<Integer> iterator = hand.iterator();
            while (iterator.hasNext()) {
                int c = ((Number)iterator.next()).intValue();
                if (Card.rankOf(c) != r) continue;
                return true;
            }
        }
        return false;
    }

    private final void maybeScheduleAutoTake() {
        if (this.taking || this.over || this.undefended() == 0) {
            return;
        }
        if (this.defenderCanRespond()) {
            return;
        }
        this.pendingKind = 3;
        this.resolveAt = System.currentTimeMillis() + 900L;
    }

    public final boolean attack(int card) {
        return this.doAttack(YOU, card);
    }

    public final boolean defend(int slot, int card) {
        return this.doDefend(YOU, slot, card);
    }

    public final boolean transfer(int card) {
        return this.doTransfer(YOU, card);
    }

    public final boolean take() {
        return this.doTake(YOU);
    }

    public final boolean done() {
        return this.doDone(YOU);
    }

    public final boolean rematch() {
        if (!this.over) {
            return false;
        }
        String first = this.draw ? YOU : this.winner;
        this.deal(first);
        return true;
    }

    private final boolean doAttack(String id, int card) {
        if (!this.canAttack(id, card)) {
            return false;
        }
        this.handOf(id).remove(Integer.valueOf(card));
        this.table.add(new int[]{card, -1});
        if (this.taking && !this.attackerCanAdd()) {
            this.pendingKind = 2;
            this.resolveAt = System.currentTimeMillis() + 1100L;
        }
        this.maybeScheduleAutoTake();
        this.mutated();
        return true;
    }

    private final boolean doDefend(String id, int slot, int card) {
        if (!this.canDefend(id, slot, card)) {
            return false;
        }
        this.handOf(id).remove(Integer.valueOf(card));
        this.table.get(slot)[1] = card;
        if (this.undefended() == 0) {
            boolean canAdd = this.attackerCanAdd() && !this.handOf(this.defenderId()).isEmpty();
            if (!canAdd) {
                this.pendingKind = 1;
                this.resolveAt = System.currentTimeMillis() + 1100L;
            }
        }
        this.mutated();
        return true;
    }

    private final boolean doTransfer(String id, int card) {
        if (!this.canTransfer(id, card)) {
            return false;
        }
        this.handOf(id).remove(Integer.valueOf(card));
        this.table.add(new int[]{card, -1});
        this.attacker = id;
        this.maybeScheduleAutoTake();
        this.mutated();
        return true;
    }

    private final boolean doTake(String id) {
        if (this.over || this.taking || Intrinsics.areEqual((Object)this.attacker, (Object)id) || this.undefended() == 0) {
            return false;
        }
        this.clearPending();
        this.taking = true;
        if (!this.attackerCanAdd()) {
            this.resolveTake();
            return true;
        }
        this.mutated();
        return true;
    }

    private final void clearPending() {
        this.pendingKind = 0;
        this.resolveAt = 0L;
    }

    private final void resolveTake() {
        this.clearPending();
        List<Integer> hand = this.handOf(this.defenderId());
        for (int[] p : this.table) {
            hand.add(p[0]);
            if (p[1] < 0) continue;
            hand.add(p[1]);
        }
        this.table.clear();
        this.taking = false;
        this.refillAndEvaluate();
    }

    private final void resolveBito() {
        this.clearPending();
        for (int[] p : this.table) {
            this.discardCount += p[1] >= 0 ? 2 : 1;
        }
        this.table.clear();
        this.refillAndEvaluate();
        if (!this.over) {
            this.attacker = this.defenderId();
        }
    }

    private final boolean doDone(String id) {
        if (this.over || !Intrinsics.areEqual((Object)this.attacker, (Object)id)) {
            return false;
        }
        if (this.taking) {
            this.resolveTake();
            return true;
        }
        if (this.table.isEmpty() || this.undefended() > 0) {
            return false;
        }
        this.resolveBito();
        return true;
    }

    private final void refillAndEvaluate() {
        this.refill(this.handOf(this.attacker));
        this.refill(this.handOf(this.defenderId()));
        boolean youEmpty = this.yourHand.isEmpty();
        boolean botEmpty = this.botHand.isEmpty();
        if (this.deck.isEmpty() && (youEmpty || botEmpty)) {
            this.over = true;
            if (youEmpty && botEmpty) {
                this.draw = true;
            } else {
                String string = this.winner = youEmpty ? YOU : BOT;
                if (youEmpty) {
                    this.scoreYou++;
                } else {
                    this.scoreBot++;
                }
            }
        }
        this.mutated();
    }

    private final void refill(List<Integer> hand) {
        while (hand.size() < 6 && !((Collection)this.deck).isEmpty()) {
            hand.add(this.deck.remove(0));
        }
    }

    @Nullable
    public final String takeLocalNotice() {
        String n = this.localNotice;
        this.localNotice = null;
        return n;
    }

    public final void onPlayerEmote() {
        if (this.random.nextInt(100) < 45) {
            this.botEmoteAt = System.currentTimeMillis() + (long)1200 + (long)this.random.nextInt(1400);
        }
    }

    public final int takeBotEmote() {
        int e = this.botEmote;
        this.botEmote = -1;
        return e;
    }

    public final void tick(long now) {
        if (this.botEmoteAt > 0L && now >= this.botEmoteAt) {
            this.botEmoteAt = 0L;
            this.botEmote = this.random.nextInt(CardsScreen.EMOTES.length);
        }
        if (this.over) {
            return;
        }
        if (this.pendingKind > 0) {
            if (now >= this.resolveAt) {
                int kind = this.pendingKind;
                this.clearPending();
                switch (kind) {
                    case 3: {
                        String def = this.defenderId();
                        if (Intrinsics.areEqual((Object)def, (Object)YOU)) {
                            this.localNotice = I18n.tr("Вам нечем биться — карты взяты");
                        }
                        this.doTake(def);
                        break;
                    }
                    case 2: {
                        this.resolveTake();
                        break;
                    }
                    default: {
                        this.resolveBito();
                    }
                }
            }
            return;
        }
        String actor = this.actor();
        if (Intrinsics.areEqual((Object)actor, (Object)YOU)) {
            if (now > this.deadlineAt) {
                if (this.taking) {
                    this.doDone(YOU);
                } else if (this.undefended() > 0) {
                    this.doTake(YOU);
                } else if (this.table.isEmpty()) {
                    this.forcedAttack(YOU);
                } else {
                    this.doDone(YOU);
                }
            }
            return;
        }
        if (now < this.botActAt) {
            return;
        }
        if (this.taking) {
            this.botFeedTake();
        } else if (this.undefended() > 0) {
            this.botDefend();
        } else {
            this.botAttack();
        }
    }

    private final void botFeedTake() {
        ArrayList<Integer> hand = new ArrayList<Integer>(this.botHand);
        hand.sort(Comparator.comparingInt(c -> Card.sortValue(c, this.trumpSuit)));
        for (Integer card : hand) {
            if (Card.suitOf(card) == this.trumpSuit && this.deck.size() > 0 || !this.canAttack(BOT, card)) continue;
            this.doAttack(BOT, card);
            return;
        }
        this.doDone(BOT);
    }

    private final void forcedAttack(String id) {
        ArrayList<Integer> hand = new ArrayList<Integer>(this.handOf(id));
        hand.sort(Comparator.comparingInt(c -> Card.sortValue(c, this.trumpSuit)));
        for (Integer card : hand) {
            if (!this.doAttack(id, card)) continue;
            return;
        }
    }

    private final void botDefend() {
        int transferCard = this.botTransferChoice();
        if (transferCard >= 0 && this.doTransfer(BOT, transferCard)) {
            return;
        }
        ArrayList<int[]> plan = new ArrayList<int[]>();
        ArrayList<Integer> free = new ArrayList<Integer>(this.botHand);
        int n = this.table.size();
        for (int slot = 0; slot < n; ++slot) {
            int[] p = this.table.get(slot);
            if (p[1] >= 0) continue;
            int best = -1;
            int bestScore = Integer.MAX_VALUE;
            for (Integer card : free) {
                if (!Card.beats(p[0], card, this.trumpSuit)) continue;
                int score = (Card.suitOf(card) == this.trumpSuit ? 100 : 0) + Card.rankOf(card);
                if (score < bestScore) {
                    bestScore = score;
                    best = card;
                }
            }
            if (best < 0) {
                this.doTake(BOT);
                return;
            }
            free.remove(Integer.valueOf(best));
            plan.add(new int[]{slot, best});
        }
        if (!plan.isEmpty()) {
            int[] move = plan.get(0);
            this.doDefend(BOT, move[0], move[1]);
        }
    }

    private final int botTransferChoice() {
        int best = -1;
        Iterator<Integer> iterator = this.botHand.iterator();
        while (iterator.hasNext()) {
            boolean trump;
            int card = ((Number)iterator.next()).intValue();
            if (!this.canTransfer(BOT, card)) continue;
            boolean bl = trump = Card.suitOf(card) == this.trumpSuit;
            if (trump && (this.deck.size() > 0 || Card.rankOf(card) > 4) || best >= 0 && Card.sortValue(card, this.trumpSuit) >= Card.sortValue(best, this.trumpSuit)) continue;
            best = card;
        }
        if (best >= 0 && this.random.nextInt(100) < 85) {
            return best;
        }
        return -1;
    }

    private final void botAttack() {
        ArrayList<Integer> hand = new ArrayList<Integer>(this.botHand);
        hand.sort(Comparator.comparingInt(c -> Card.sortValue(c, this.trumpSuit)));
        if (this.table.isEmpty()) {
            for (Integer card : hand) {
                if (Card.suitOf(card) == this.trumpSuit && this.deck.size() > 4 && hand.size() > 1 || !this.doAttack(BOT, card)) continue;
                return;
            }
            this.forcedAttack(BOT);
            return;
        }
        for (Integer card : hand) {
            if (Card.suitOf(card) == this.trumpSuit && this.deck.size() > 2 || !this.canAttack(BOT, card)) continue;
            this.doAttack(BOT, card);
            return;
        }
        this.doDone(BOT);
    }

    @Nullable
    public final CardsState state() {
        if (this.cached != null && this.cachedSeq == this.seq) {
            return this.cached;
        }
        JsonObject o = new JsonObject();
        o.addProperty("seq", (Number)this.seq);
        o.addProperty("room", YOU);
        o.addProperty("you", YOU);
        JsonObject opp = new JsonObject();
        opp.addProperty("id", BOT);
        Object[] objectArray = new Object[]{this.yourName};
        opp.addProperty("name", I18n.tr("%s (бот)", objectArray));
        String selfAvatar = null;
        try {
            selfAvatar = CardsAvatars.selfUrl();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        if (selfAvatar != null) {
            opp.addProperty("avatar", selfAvatar);
        }
        opp.addProperty("cards", (Number)this.botHand.size());
        opp.addProperty("online", Boolean.valueOf(true));
        o.add("opponent", (JsonElement)opp);
        JsonArray hand = new JsonArray();
        Iterator<Integer> iterator = this.yourHand.iterator();
        while (iterator.hasNext()) {
            int c = ((Number)iterator.next()).intValue();
            hand.add((Number)c);
        }
        o.add("hand", (JsonElement)hand);
        JsonArray tab = new JsonArray();
        for (int[] p : this.table) {
            JsonObject pair = new JsonObject();
            pair.addProperty("a", (Number)p[0]);
            if (p[1] >= 0) {
                pair.addProperty("d", (Number)p[1]);
            }
            tab.add((JsonElement)pair);
        }
        o.add("table", (JsonElement)tab);
        o.addProperty("deckCount", (Number)this.deck.size());
        o.addProperty("trumpCard", (Number)(this.deck.isEmpty() ? -1 : this.trumpCard));
        o.addProperty("trumpSuit", (Number)this.trumpSuit);
        o.addProperty("discardCount", (Number)this.discardCount);
        o.addProperty("taking", Boolean.valueOf(this.taking));
        o.addProperty("attacker", this.attacker);
        o.addProperty("actor", this.actor());
        o.addProperty("deadline", (Number)(this.over ? 0L : Math.max(0L, this.deadlineAt - System.currentTimeMillis())));
        o.addProperty("over", Boolean.valueOf(this.over));
        o.addProperty("winner", this.winner);
        o.addProperty("draw", Boolean.valueOf(this.draw));
        JsonObject score = new JsonObject();
        score.addProperty("you", (Number)this.scoreYou);
        score.addProperty("opponent", (Number)this.scoreBot);
        o.add("score", (JsonElement)score);
        this.cached = CardsState.Companion.fromJson(o);
        this.cachedSeq = this.seq;
        return this.cached;
    }

    private static final int botFeedTake$lambda$0(CardsLocalGame this$0, Integer c) {
        Intrinsics.checkNotNull((Object)c);
        return Card.sortValue(c, this$0.trumpSuit);
    }

    private static final int botFeedTake$lambda$1(Function1 $tmp0, Object p0) {
        return ((Number)$tmp0.invoke(p0)).intValue();
    }

    private static final int forcedAttack$lambda$0(CardsLocalGame this$0, Integer c) {
        Intrinsics.checkNotNull((Object)c);
        return Card.sortValue(c, this$0.trumpSuit);
    }

    private static final int forcedAttack$lambda$1(Function1 $tmp0, Object p0) {
        return ((Number)$tmp0.invoke(p0)).intValue();
    }

    private static final int botAttack$lambda$0(CardsLocalGame this$0, Integer c) {
        Intrinsics.checkNotNull((Object)c);
        return Card.sortValue(c, this$0.trumpSuit);
    }

    private static final int botAttack$lambda$1(Function1 $tmp0, Object p0) {
        return ((Number)$tmp0.invoke(p0)).intValue();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\nR\u0014\u0010\f\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\n\u00a8\u0006\r"}, d2={"Lrtx/kimiko/api/cards/CardsLocalGame.Companion;", "", "<init>", "()V", "", "YOU", "Ljava/lang/String;", "BOT", "", "TURN_MS", "J", "RESOLVE_DELAY_MS", "AUTO_TAKE_DELAY_MS", "rtx.kimiko:kimiko"})
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

