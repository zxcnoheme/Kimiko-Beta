/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui.theme;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.ui.theme.ThemeKt;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\u0010\b\n\u0002\b;\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001d\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\u0010\u0006\u001a\u00020\u0004\"\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bJ\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\tJ\r\u0010\n\u001a\u00020\u0005\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0005\u00a2\u0006\u0004\b\f\u0010\u000bJ\r\u0010\r\u001a\u00020\u0005\u00a2\u0006\u0004\b\r\u0010\u000bJ\r\u0010\u000e\u001a\u00020\u0005\u00a2\u0006\u0004\b\u000e\u0010\u000bJ\r\u0010\u000f\u001a\u00020\u0005\u00a2\u0006\u0004\b\u000f\u0010\u000bJ\r\u0010\u0010\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0012\u0010\u0011J\r\u0010\u0013\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0013\u0010\u000bJ\r\u0010\u0014\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0014\u0010\u000bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0015R\u0014\u0010\u0006\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0016R\u0014\u0010\u0012\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0018R\u0014\u0010\u001b\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0018R\u0014\u0010\u001c\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u0018R\u0014\u0010\u0013\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0018R\u0014\u0010\u0014\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0018j\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<j\u0002\b=j\u0002\b>j\u0002\b?\u00a8\u0006@"}, d2={"Lrtx/kimiko/api/ui/theme/Theme;", "", "", "displayName", "", "", "rawPalette", "<init>", "(Ljava/lang/String;ILjava/lang/String;[I)V", "()Ljava/lang/String;", "accentRgb", "()I", "accentBrightRgb", "accentSoftRgb", "accentFillRgb", "toggleOnRgb", "shades", "()[I", "palette", "gradientA", "gradientB", "Ljava/lang/String;", "[I", "accent", "I", "accentBright", "accentSoft", "accentFill", "toggleOn", "KIMIKO", "BLUEPINK", "BLUEGREEN", "DARKBLUE", "POLARIZE", "WATER", "VIOLET", "LILAC", "SUNRISE", "SUNSET", "SPRING", "SUMMER", "WINTER", "MIDNIGHT", "HALLOWEEN", "NEWYEAR", "VALENTINE", "FIRE", "EARTH", "ICE", "FOREST", "GALAXY", "DESERT", "GOLD", "EMERALD", "CORAL", "MINT", "PASTEL", "TEAL", "BLOODY", "NEON", "AUTUMN", "CHRISTMAS", "REVOLUT", "WATERMEL", "rtx.kimiko:kimiko"})
public enum Theme {
        KIMIKO("Клиентская", new int[]{9081843, 11838975}),
        BLUEPINK("Голубо-розовая", new int[]{4776676, 14045679}),
        BLUEGREEN("Сине-зелёная", new int[]{911192, 157920}),
        DARKBLUE("Тёмно-синяя", new int[]{4023774, 2166617}),
        POLARIZE("Чёрно-белая", new int[]{0xC8C8C8, 0x7B7B7B}),
        WATER("Водяная", new int[]{6134508, 737480}),
        VIOLET("Фиолетовая", new int[]{9443736, 4395888}),
        LILAC("Лавандовая", new int[]{12618202, 6892934}),
        SUNRISE("Рассветная", new int[]{16087094, 14039456}),
        SUNSET("Закатная", new int[]{16742912, 9317858}),
        SPRING("Весенняя", new int[]{11067491, 5679919}),
        SUMMER("Летняя", new int[]{16769625, 16754513}),
        WINTER("Зимняя", new int[]{14740220, 13623027}),
        MIDNIGHT("Полуночная", new int[]{991271, 2904932}),
        HALLOWEEN("Хэллоуинская", new int[]{16741656, 0x1A1A1A}),
        NEWYEAR("Новогодняя", new int[]{1981554, 12597547}),
        VALENTINE("Влюблённая", new int[]{16739229, 12862825}),
        FIRE("Огненная", new int[]{16765440, 0xFF0000}),
        EARTH("Земляная", new int[]{9132587, 4073251}),
        ICE("Ледяная", new int[]{10616782, 46299}),
        FOREST("Лесная", new int[]{1265171, 3046706}),
        GALAXY("Космическая", new int[]{986153, 9055202}),
        DESERT("Пустынная", new int[]{15254430, 12092939}),
        GOLD("Золотая", new int[]{16774839, 12092939}),
        EMERALD("Изумрудная", new int[]{5294200, 222768}),
        CORAL("Коралловая", new int[]{16744272, 15287402}),
        MINT("Мятная", new int[]{11993051, 2074234}),
        PASTEL("Пастельная", new int[]{16765404, 12710128}),
        TEAL("Бирюзовая", new int[]{1022862, 23639}),
        BLOODY("Кровавая", new int[]{12138082, 6757403}),
        NEON("Неоновая", new int[]{16720128, 59903}),
        AUTUMN("Осенняя", new int[]{59903, 12601856}),
        CHRISTMAS("Рождественская", new int[]{14617355, 0xDCCBCB}),
        REVOLUT("Неоновая революция", new int[]{5034078, 7881892}),
        WATERMEL("Арбузная", new int[]{13453107, 10796658});
@NotNull
    private final String displayName;
    @NotNull
    private final int[] rawPalette;
    @NotNull
    private final int[] palette;
    private final int accent;
    private final int accentBright;
    private final int accentSoft;
    private final int accentFill;
    private final int toggleOn;
    private final int gradientA;
    private final int gradientB;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private Theme(String displayName, int ... rawPalette) {
        this.displayName = displayName;
        this.rawPalette = rawPalette;
        int n2 = Math.max(1, this.rawPalette.length);
        int[] nArray = new int[n2];
        for (int i = 0; i < n2; i++) {
            nArray[i] = this.rawPalette[i] & 0xFFFFFF;
        }
        this.palette = nArray;
        this.accent = this.palette[0];
        this.accentBright = ThemeKt.access$themeLighten(this.palette[0], 0.65f, 1.15f, 0.1f);
        this.accentSoft = ThemeKt.access$themeLighten(this.palette[0], 0.45f, 1.25f, 0.15f);
        this.accentFill = ThemeKt.access$themeDarken(this.palette[0], 1.05f, 0.78f);
        this.toggleOn = ThemeKt.access$themeDarken(this.palette[0], 1.1f, 0.55f);
        this.gradientA = this.palette[0];
        this.gradientB = this.palette[this.palette.length - 1];
    }

    @NotNull
    public final String displayName() {
        return I18n.tr(this.displayName);
    }

    public final int accentRgb() {
        return this.accent;
    }

    public final int accentBrightRgb() {
        return this.accentBright;
    }

    public final int accentSoftRgb() {
        return this.accentSoft;
    }

    public final int accentFillRgb() {
        return this.accentFill;
    }

    public final int toggleOnRgb() {
        return this.toggleOn;
    }

    @NotNull
    public final int[] shades() {
        int[] nArray = new int[]{this.accent, this.accentBright, this.accentSoft, this.accentFill, this.toggleOn, this.gradientA, this.gradientB};
        return nArray;
    }

    @NotNull
    public final int[] palette() {
        return this.palette;
    }

    public final int gradientA() {
        return this.gradientA;
    }

    public final int gradientB() {
        return this.gradientB;
    }

    

    

    @NotNull
    public static EnumEntries<Theme> getEntries() {
        return EnumEntriesKt.enumEntries(values());
    }
}

