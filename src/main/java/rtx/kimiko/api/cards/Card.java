/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.cards;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0019\n\u0002\u0010\u0011\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\t\u0010\bJ\u001b\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\r\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\r\u0010\fJ+\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0017\u0010\u0015J\u001b\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0018\u0010\u0015J\u001b\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001b\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u001c\u0010\u001bJ\u001b\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u001d\u0010\u001bJ\u001b\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u001e\u0010\u001bJ\u0013\u0010\u001f\u001a\u00020\u0019H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u001f\u0010 J\u0013\u0010!\u001a\u00020\u0019H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b!\u0010 J\u0013\u0010\"\u001a\u00020\u0019H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\"\u0010 J\u0013\u0010#\u001a\u00020\u0019H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b#\u0010 J\u0013\u0010$\u001a\u00020\u0019H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b$\u0010 J\u0013\u0010%\u001a\u00020\u0019H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b%\u0010 J\u001b\u0010&\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b&\u0010\u001bJ\u001b\u0010'\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b'\u0010\u001bJ#\u0010(\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020\u00138\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020\u00198\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u0014\u0010.\u001a\u00020\u00198\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010-R\u0014\u0010/\u001a\u00020\u00198\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b/\u0010-R\u0014\u00100\u001a\u00020\u00198\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b0\u0010-R\u0014\u00101\u001a\u00020\u00198\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b1\u0010-R\u0014\u00102\u001a\u00020\u00198\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u0010-R\u001a\u00104\u001a\b\u0012\u0004\u0012\u00020\u0013038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u00105R\u001a\u00106\u001a\b\u0012\u0004\u0012\u00020\u0013038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b6\u00105R\u001a\u00107\u001a\b\u0012\u0004\u0012\u00020\u0013038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b7\u00105\u00a8\u00068"}, d2={"Lrtx/kimiko/api/cards/Card;", "", "<init>", "()V", "", "card", "Lkotlin/jvm/JvmStatic;", "suitOf", "(I)I", "rankOf", "", "isValid", "(I)Z", "isRed", "attack", "defense", "trumpSuit", "beats", "(III)Z", "", "label", "(I)Ljava/lang/String;", "suit", "suitSymbol", "suitName", "", "u0", "(I)F", "v0", "u1", "v1", "backU0", "()F", "backV0", "backU1", "backV1", "emblemU0", "emblemU1", "emblemV0", "emblemV1", "sortValue", "(II)I", "ATLAS", "Ljava/lang/String;", "RATIO", "F", "AW", "AH", "CW", "CH", "BACK_X", "", "RANK_LABELS", "[Ljava/lang/String;", "SUIT_SYMBOLS", "SUIT_NAMES", "rtx.kimiko:kimiko"})
public final class Card {
    @NotNull
    public static final Card INSTANCE = new Card();
    @NotNull
    public static final String ATLAS = "kimiko:textures/cards/atlas.png";
    public static final float RATIO = 1.3333334f;
    private static final float AW = 2048.0f;
    private static final float AH = 1024.0f;
    private static final float CW = 192.0f;
    private static final float CH = 256.0f;
    private static final float BACK_X = 1728.0f;
    @NotNull
    private static final String[] RANK_LABELS;
    @NotNull
    private static final String[] SUIT_SYMBOLS;
    @NotNull
    private static final String[] SUIT_NAMES;

    private Card() {
    }

    @JvmStatic
    public static final int suitOf(int card) {
        return card / 9;
    }

    @JvmStatic
    public static final int rankOf(int card) {
        return card % 9;
    }

    @JvmStatic
    public static final boolean isValid(int card) {
        return 0 <= card ? card < 36 : false;
    }

    @JvmStatic
    public static final boolean isRed(int card) {
        return Card.suitOf(card) <= 1;
    }

    @JvmStatic
    public static final boolean beats(int attack, int defense, int trumpSuit) {
        if (Card.suitOf(defense) == Card.suitOf(attack)) {
            return Card.rankOf(defense) > Card.rankOf(attack);
        }
        return Card.suitOf(defense) == trumpSuit && Card.suitOf(attack) != trumpSuit;
    }

    @JvmStatic
    @NotNull
    public static final String label(int card) {
        if (!Card.isValid(card)) {
            return "?";
        }
        return RANK_LABELS[Card.rankOf(card)] + SUIT_SYMBOLS[Card.suitOf(card)];
    }

    @JvmStatic
    @NotNull
    public static final String suitSymbol(int suit) {
        return (0 <= suit ? suit < 4 : false) ? SUIT_SYMBOLS[suit] : "?";
    }

    @JvmStatic
    @NotNull
    public static final String suitName(int suit) {
        return (0 <= suit ? suit < 4 : false) ? SUIT_NAMES[suit] : "?";
    }

    @JvmStatic
    public static final float u0(int card) {
        return (float)Card.rankOf(card) * 192.0f / 2048.0f;
    }

    @JvmStatic
    public static final float v0(int card) {
        return (float)Card.suitOf(card) * 256.0f / 1024.0f;
    }

    @JvmStatic
    public static final float u1(int card) {
        return ((float)Card.rankOf(card) * 192.0f + 192.0f) / 2048.0f;
    }

    @JvmStatic
    public static final float v1(int card) {
        return ((float)Card.suitOf(card) * 256.0f + 256.0f) / 1024.0f;
    }

    @JvmStatic
    public static final float backU0() {
        return 0.84375f;
    }

    @JvmStatic
    public static final float backV0() {
        return 0.0f;
    }

    @JvmStatic
    public static final float backU1() {
        return 0.9375f;
    }

    @JvmStatic
    public static final float backV1() {
        return 0.25f;
    }

    @JvmStatic
    public static final float emblemU0() {
        return 0.76171875f;
    }

    @JvmStatic
    public static final float emblemU1() {
        return 0.83203125f;
    }

    @JvmStatic
    public static final float emblemV0(int suit) {
        return ((float)suit * 256.0f + 56.0f) / 1024.0f;
    }

    @JvmStatic
    public static final float emblemV1(int suit) {
        return ((float)suit * 256.0f + 200.0f) / 1024.0f;
    }

    @JvmStatic
    public static final int sortValue(int card, int trumpSuit) {
        boolean trump = Card.suitOf(card) == trumpSuit;
        return (trump ? 100 : Card.suitOf(card) * 10) + Card.rankOf(card);
    }

    static {
        String[] stringArray = new String[]{"6", "7", "8", "9", "10", "В", "Д", "К", "Т"};
        RANK_LABELS = stringArray;
        stringArray = new String[]{"\u2665", "\u2666", "\u2663", "\u2660"};
        SUIT_SYMBOLS = stringArray;
        stringArray = new String[]{"черви", "бубны", "крести", "пики"};
        SUIT_NAMES = stringArray;
    }
}

