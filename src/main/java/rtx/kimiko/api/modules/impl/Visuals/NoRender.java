/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.MultiModeSetting;

@Feature(value={"norender"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0003R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\b\u00ca\u0001\u0010\b\n\u0012\f\b\u000b\u0012\b\b\fJ\u0004\b\b(\f\u00a8\u0006\r"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/NoRender;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "ensureSelectedDefaults", "Lrtx/kimiko/api/modules/settings/impl/MultiModeSetting;", "elements", "Lrtx/kimiko/api/modules/settings/impl/MultiModeSetting;", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "norender", "rtx.kimiko:kimiko"})
public final class NoRender
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final MultiModeSetting elements;
    @NotNull
    public static final String FIRE = "Огонь";
    @NotNull
    public static final String ENTITY_FIRE = "Огонь на сущностях";
    @NotNull
    public static final String CAMERA_SHAKE = "Тряска камеры";
    @NotNull
    public static final String VIEW_BOBBING = "Покачивание камеры";
    @NotNull
    public static final String FOV_DYNAMIC = "Динамика поля зрения";
    @NotNull
    public static final String SCOREBOARD = "Таблица счёта";
    @NotNull
    public static final String BOSS_BAR = "Полоса босса";
    @NotNull
    public static final String TOTEM = "Тотем";
    @NotNull
    public static final String GLOW = "Свечение";
    @NotNull
    public static final String PARTICLES = "Частицы";
    @NotNull
    private static final String[] DEFAULT_ELEMENTS;
    @JvmField
    @Nullable
    public static NoRender INSTANCE;

    public NoRender() {
        super("No Render", "Скрывает выбранные визуальные эффекты.", Category.VISUALS);
        String[] stringArray = new String[]{FIRE, ENTITY_FIRE, CAMERA_SHAKE, VIEW_BOBBING, FOV_DYNAMIC, SCOREBOARD, BOSS_BAR, GLOW, TOTEM, PARTICLES};
        String[] stringArray2 = stringArray;
        stringArray = DEFAULT_ELEMENTS;
        this.elements = (MultiModeSetting)this.register((Setting)new MultiModeSetting("Элементы", "Какие элементы рендера скрывать.", stringArray2, Arrays.copyOf(stringArray, stringArray.length)));
        INSTANCE = this;
    }

    private final void ensureSelectedDefaults() {
        if (this.elements.isSelectionEmpty()) {
            String[] stringArray = DEFAULT_ELEMENTS;
            this.elements.selected(Arrays.copyOf(stringArray, stringArray.length));
        }
    }

    @JvmStatic
    @Nullable
    public static final NoRender getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final boolean isActive(@NotNull String element) {
        return Companion.isActive(element);
    }

    static {
        String[] stringArray = new String[]{FIRE, ENTITY_FIRE, CAMERA_SHAKE, VIEW_BOBBING, GLOW};
        DEFAULT_ELEMENTS = stringArray;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u000eR\u0014\u0010\u0012\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u000eR\u0014\u0010\u0013\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u000eR\u0014\u0010\u0014\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u000eR\u0014\u0010\u0015\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u000eR\u0014\u0010\u0016\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u000eR\u0014\u0010\u0017\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u000eR\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u001d\u0010\u001c\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001d\u00a8\u0006\u001e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/NoRender.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/NoRender;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/NoRender;", "", "element", "", "isActive", "(Ljava/lang/String;)Z", "FIRE", "Ljava/lang/String;", "ENTITY_FIRE", "CAMERA_SHAKE", "VIEW_BOBBING", "FOV_DYNAMIC", "SCOREBOARD", "BOSS_BAR", "TOTEM", "GLOW", "PARTICLES", "", "DEFAULT_ELEMENTS", "[Ljava/lang/String;", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/NoRender;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final NoRender getInstance() {
            return INSTANCE;
        }

        @JvmStatic
        public final boolean isActive(@NotNull String element) {
            Intrinsics.checkNotNullParameter((Object)element, (String)"element");
            NoRender noRender = INSTANCE;
            if (noRender == null) {
                return false;
            }
            NoRender inst = noRender;
            if (!inst.isEnabled()) {
                return false;
            }
            inst.ensureSelectedDefaults();
            return inst.elements.isSelected(element);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

