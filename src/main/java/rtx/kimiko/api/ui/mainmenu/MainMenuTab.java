/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.mainmenu;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.utils.render.fonts.Fonts;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0086\u0081\u0002\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000fB)\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0007\u0010\u000bJ\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\fJ\r\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0006\u0010\u000bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\rR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\rR\u0014\u0010\u0007\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\rj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MainMenuTab;", "", "", "ru", "Lrtx/kimiko/utils/render/fonts/Fonts;", "font", "glyph", "hint", "<init>", "(Ljava/lang/String;ILjava/lang/String;Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;Ljava/lang/String;)V", "title", "()Ljava/lang/String;", "()Lrtx/kimiko/utils/render/fonts/Fonts;", "Ljava/lang/String;", "Lrtx/kimiko/utils/render/fonts/Fonts;", "Companion", "PLAY", "BACKGROUNDS", "COSMETICS", "PROFILE", "ACCOUNTS", "NEWS", "SETTINGS", "rtx.kimiko:kimiko"})
public enum MainMenuTab {
        PLAY("Играть", Fonts.I2, "O", "Начать игру"),
        BACKGROUNDS("Фоны", Fonts.KIMIKO, "p", "Оформление меню"),
        COSMETICS("Косметика", Fonts.I2, "е", "Твой облик"),
        PROFILE("Профиль", Fonts.MAINMENU, "e", "Статистика"),
        ACCOUNTS("Аккаунты", Fonts.I2, "p", "Смена аккаунта"),
        NEWS("Новости", Fonts.I2, "U", "Что нового"),
        SETTINGS("Настройки", Fonts.I2, "л", "Клиент и меню");
@NotNull
    public static final Companion Companion;
    @NotNull
    private final String ru;
    @NotNull
    private final Fonts font;
    @NotNull
    private final String glyph;
    @NotNull
    private final String hint;
    
    
    
    
    
    
    
    
    private MainMenuTab(String ru, Fonts font, String glyph, String hint) {
        this.ru = ru;
        this.font = font;
        this.glyph = glyph;
        this.hint = hint;
    }

    @NotNull
    public final String title() {
        return I18n.tr(this.ru);
    }

    @NotNull
    public final String hint() {
        return I18n.tr(this.hint);
    }

    @NotNull
    public final Fonts font() {
        return this.font;
    }

    @NotNull
    public final String glyph() {
        return this.glyph;
    }

    

    

    @NotNull
    public static EnumEntries<MainMenuTab> getEntries() {
        return EnumEntriesKt.enumEntries(values());
    }

    @JvmStatic
    @NotNull
    public static final MainMenuTab of(@Nullable String name) {
        return Companion.of(name);
    }

            static {
        Companion = new Companion(null);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MainMenuTab.Companion;", "", "<init>", "()V", "", "name", "Lrtx/kimiko/api/ui/mainmenu/MainMenuTab;", "Lkotlin/jvm/JvmStatic;", "of", "(Ljava/lang/String;)Lrtx/kimiko/api/ui/mainmenu/MainMenuTab;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final MainMenuTab of(@Nullable String name) {
            if (name == null) {
                return PLAY;
            }
            for (MainMenuTab tab : MainMenuTab.getEntries()) {
                if (!Intrinsics.areEqual((Object)tab.name(), (Object)name)) continue;
                return tab;
            }
            return PLAY;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

