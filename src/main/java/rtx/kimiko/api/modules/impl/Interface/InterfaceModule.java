/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.math.MathKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Interface;

import java.awt.Color;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.math.MathKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.NumberSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.api.ui.theme.ThemeManager;

@Feature(value={"interfacemodule"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 J2\u00020\u0001:\u0001JB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\u0006J\r\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\nJ\r\u0010\u000b\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000b\u0010\u0006J\r\u0010\f\u001a\u00020\u0004\u00a2\u0006\u0004\b\f\u0010\u0006J\r\u0010\r\u001a\u00020\u0004\u00a2\u0006\u0004\b\r\u0010\u0006J\r\u0010\u000e\u001a\u00020\b\u00a2\u0006\u0004\b\u000e\u0010\nJ\r\u0010\u000f\u001a\u00020\b\u00a2\u0006\u0004\b\u000f\u0010\nJ\u001d\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0015\u0010\u0014J\r\u0010\u0016\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0006J\r\u0010\u0017\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0006J\r\u0010\u0018\u001a\u00020\b\u00a2\u0006\u0004\b\u0018\u0010\nJ\r\u0010\u0019\u001a\u00020\b\u00a2\u0006\u0004\b\u0019\u0010\nJ\u001d\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010\u00a2\u0006\u0004\b\u001a\u0010\u0014J\u001d\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010\u00a2\u0006\u0004\b\u001b\u0010\u0014J\r\u0010\u001d\u001a\u00020\u001c\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b\u001f\u0010\u001eR\u0014\u0010 \u001a\u00020\u001c8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0019\u0010$\u001a\u00020\"8\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0019\u0010*\u001a\u00020)8\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010(R\u0019\u0010-\u001a\u00020)8\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b-\u0010+R\u0019\u0010.\u001a\u00020)8\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b.\u0010+R\u0014\u0010/\u001a\u00020&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u0010(R\u0019\u00100\u001a\u00020\"8\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b0\u0010%R\u0019\u00101\u001a\u00020\"8\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b1\u0010%R\u0019\u00102\u001a\u00020)8\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b2\u0010+R\u0019\u00103\u001a\u00020)8\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b3\u0010+R\u0019\u00104\u001a\u00020)8\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b4\u0010+R\u0019\u00106\u001a\u0002058\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b6\u00107R\u0019\u00109\u001a\u0002088\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b9\u0010:R\u0019\u0010;\u001a\u0002058\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b;\u00107R\u0019\u0010<\u001a\u0002088\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b<\u0010:R\u0014\u0010=\u001a\u00020&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u0010(R\u0019\u0010>\u001a\u00020)8\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b>\u0010+R\u0019\u0010?\u001a\u00020)8\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b?\u0010+R\u0014\u0010@\u001a\u00020&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b@\u0010(R\u0019\u0010A\u001a\u0002088\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\bA\u0010:R\u0019\u0010B\u001a\u00020)8\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\bB\u0010+R\u0019\u0010C\u001a\u00020)8\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\bC\u0010+R\u0014\u0010D\u001a\u00020&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u0010(R\u0019\u0010E\u001a\u0002088\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\bE\u0010:R\u0019\u0010F\u001a\u00020\"8\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\bF\u0010%R\u0019\u0010G\u001a\u0002088\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\bG\u0010:R\u0019\u0010H\u001a\u0002088\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\bH\u0010:R\u0019\u0010I\u001a\u0002088\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\bI\u0010:\u00ca\u0001\u0010\bK\u0012\f\bL\u0012\b\b\fJ\u0004\b\b(M\u00a8\u0006N"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/InterfaceModule;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "defaultEnabled", "()Z", "usesNewHudHeader", "", "gradientStyleId", "()I", "isThemeClientColor", "isCustomClientColor", "isRainbowClientColor", "clientPrimaryColor", "clientSecondaryColor", "", "screenX", "screenY", "clientPrimaryColorAt", "(FF)I", "clientSecondaryColorAt", "usesSecondClientColor", "clientColorMovement", "clientPrimaryColorOpaque", "clientSecondaryColorOpaque", "clientPrimaryColorOpaqueAt", "clientSecondaryColorOpaqueAt", "", "clientPalette", "()[I", "getRainbowPalette", "rainbowPalette", "[I", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lkotlin/jvm/JvmField;", "hudMode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "rectLayout", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "rectCornerRadius", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "rectBackdrop", "rectBackdropBlur", "rectRefractionStrength", "rectColors", "gradientStyle", "clientColorMode", "rainbowSpeed", "rainbowSpread", "rainbowSaturation", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "rectColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "rectUseSecondColor", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "rectSecondColor", "rectColorMovement", "rectRefraction", "rectEdgeStrength", "rectEdgeSharpness", "rectGlowSeparator", "rectGlow", "rectGlowIntensity", "rectGlowRadius", "miscSeparator", "hudIcons", "dragStyle", "dragJitter", "dragWaves", "dragTilt", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "interfacemodule", "rtx.kimiko:kimiko"})
public final class InterfaceModule
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final int[] rainbowPalette = new int[9];
    @JvmField
    @NotNull
    public final ModeSetting hudMode;
    @NotNull
    private final SeparatorSetting rectLayout;
    @JvmField
    @NotNull
    public final NumberSetting rectCornerRadius;
    @NotNull
    private final SeparatorSetting rectBackdrop;
    @JvmField
    @NotNull
    public final NumberSetting rectBackdropBlur;
    @JvmField
    @NotNull
    public final NumberSetting rectRefractionStrength;
    @NotNull
    private final SeparatorSetting rectColors;
    @JvmField
    @NotNull
    public final ModeSetting gradientStyle;
    @JvmField
    @NotNull
    public final ModeSetting clientColorMode;
    @JvmField
    @NotNull
    public final NumberSetting rainbowSpeed;
    @JvmField
    @NotNull
    public final NumberSetting rainbowSpread;
    @JvmField
    @NotNull
    public final NumberSetting rainbowSaturation;
    @JvmField
    @NotNull
    public final ColorSetting rectColor;
    @JvmField
    @NotNull
    public final BooleanSetting rectUseSecondColor;
    @JvmField
    @NotNull
    public final ColorSetting rectSecondColor;
    @JvmField
    @NotNull
    public final BooleanSetting rectColorMovement;
    @NotNull
    private final SeparatorSetting rectRefraction;
    @JvmField
    @NotNull
    public final NumberSetting rectEdgeStrength;
    @JvmField
    @NotNull
    public final NumberSetting rectEdgeSharpness;
    @NotNull
    private final SeparatorSetting rectGlowSeparator;
    @JvmField
    @NotNull
    public final BooleanSetting rectGlow;
    @JvmField
    @NotNull
    public final NumberSetting rectGlowIntensity;
    @JvmField
    @NotNull
    public final NumberSetting rectGlowRadius;
    @NotNull
    private final SeparatorSetting miscSeparator;
    @JvmField
    @NotNull
    public final BooleanSetting hudIcons;
    @JvmField
    @NotNull
    public final ModeSetting dragStyle;
    @JvmField
    @NotNull
    public final BooleanSetting dragJitter;
    @JvmField
    @NotNull
    public final BooleanSetting dragWaves;
    @JvmField
    @NotNull
    public final BooleanSetting dragTilt;
    @NotNull
    public static final String HUD_MODE_OLD = "Старый";
    @NotNull
    public static final String HUD_MODE_NEW = "Новый";
    @NotNull
    public static final String CLIENT_COLOR_THEMES = "Темы";
    @NotNull
    public static final String CLIENT_COLOR_CUSTOM = "Свой";
    @NotNull
    public static final String CLIENT_COLOR_RAINBOW = "Радуга";
    @NotNull
    public static final String GRADIENT_HORIZONTAL = "Горизонтальный";
    @NotNull
    public static final String GRADIENT_BLOBS = "Жидкие пятна";
    @NotNull
    public static final String GRADIENT_SQUARE = "По квадрату";
    private static final float THEME_COLOR_ALPHA = 204.0f;
    @Nullable
    private static InterfaceModule companionInstance;

    public InterfaceModule() {
        super("Interface", "Общий стиль (стекло, свечение, цвета) для всех элементов интерфейса.", Category.DISPLAY);
        String[] stringArray = new String[]{HUD_MODE_OLD, HUD_MODE_NEW};
        this.hudMode = (ModeSetting)this.register((Setting)new ModeSetting("Режим худа", "Строение шапки HUD-элементов. «Старый» сохраняет текущий вид, «Новый» добавляет акцентную плашку и декоративные иконки.", HUD_MODE_OLD, stringArray));
        this.rectLayout = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Клиент-рект"));
        this.rectCornerRadius = (NumberSetting)this.register((Setting)new NumberSetting("Скругление углов", "Радиус скругления углов общих прямоугольников интерфейса.", 7.0, 2.0, 10.0, 1.0));
        this.rectBackdrop = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Фон"));
        this.rectBackdropBlur = (NumberSetting)this.register((Setting)new NumberSetting("Размытие фона", "Радиус размытия фона за прямоугольниками интерфейса.", 18.0, 0.0, 64.0, 1.0));
        this.rectRefractionStrength = (NumberSetting)this.register((Setting)new NumberSetting("Преломление", "Насколько сильно стекло искажает размытый фон.", 0.3, 0.0, 0.8, 0.01));
        this.rectColors = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Цвета"));
        stringArray = new String[]{GRADIENT_HORIZONTAL, GRADIENT_BLOBS, GRADIENT_SQUARE};
        this.gradientStyle = (ModeSetting)this.register((Setting)new ModeSetting("Градиент", "Способ отрисовки градиента в клиент-ректах. «Горизонтальный» — бегущий перелив по горизонтали; «Жидкие пятна» — дрейфующие цветовые капли; «По квадрату» — цвета по 4 углам.", GRADIENT_BLOBS, stringArray));
        stringArray = new String[]{CLIENT_COLOR_THEMES, CLIENT_COLOR_CUSTOM, CLIENT_COLOR_RAINBOW};
        this.clientColorMode = (ModeSetting)this.register((Setting)new ModeSetting("Цвет клиента", "Источник акцентного цвета клиента. «Themes» следует активной теме GUI; «Custom» использует цвета ниже; «Rainbow» прокручивает яркий анимированный спектр.", CLIENT_COLOR_THEMES, stringArray));
        this.rainbowSpeed = (NumberSetting)this.register((Setting)new NumberSetting("Скорость радуги", "Как быстро радуга прокручивает оттенки.", 1.0, 0.1, 4.0, 0.1).visibleWhen(() -> InterfaceModule.rainbowSpeed$lambda$0(this)));
        this.rainbowSpread = (NumberSetting)this.register((Setting)new NumberSetting("Разброс радуги", "Расстояние между оттенками на концах градиента — больше значение охватывает больше спектра.", 0.18, 0.02, 0.5, 0.01).visibleWhen(() -> InterfaceModule.rainbowSpread$lambda$0(this)));
        this.rainbowSaturation = (NumberSetting)this.register((Setting)new NumberSetting("Насыщенность радуги", "Насыщенность цвета радуги.", 0.85, 0.3, 1.0, 0.01).visibleWhen(() -> InterfaceModule.rainbowSaturation$lambda$0(this)));
        this.rectColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет", "Основной оттенок стекла и цвет края.", new Color(-857872385, true)).visibleWhen(() -> InterfaceModule.rectColor$lambda$0(this)));
        this.rectUseSecondColor = (BooleanSetting)this.register((Setting)new BooleanSetting("Второй цвет", "Включает второй цвет для углового градиента.", false).visibleWhen(() -> InterfaceModule.rectUseSecondColor$lambda$0(this)));
        this.rectSecondColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет 2", "Второй цвет градиента стекла.", new Color(-855690602, true)).visibleWhen(() -> InterfaceModule.rectSecondColor$lambda$0(this)));
        this.rectColorMovement = (BooleanSetting)this.register((Setting)new BooleanSetting("Движение цвета", "Анимирует двухцветный градиент вокруг углов прямоугольника.", false).visibleWhen(() -> InterfaceModule.rectColorMovement$lambda$0(this)));
        this.rectRefraction = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Край стекла"));
        this.rectEdgeStrength = (NumberSetting)this.register((Setting)new NumberSetting("Сила края", "Насколько сильно цвет края подмешивается в стекло.", 0.18, 0.0, 1.0, 0.01));
        this.rectEdgeSharpness = (NumberSetting)this.register((Setting)new NumberSetting("Резкость края", "Чем выше значение, тем тоньше и резче блик.", 55.0, 2.0, 100.0, 1.0).visibleWhen(() -> InterfaceModule.rectEdgeSharpness$lambda$0(this)));
        this.rectGlowSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Свечение"));
        this.rectGlow = (BooleanSetting)this.register((Setting)new BooleanSetting("Свечение", "Рисует мягкое свечение вокруг прямоугольников интерфейса.", false));
        this.rectGlowIntensity = (NumberSetting)this.register((Setting)new NumberSetting("Яркость свечения", "Яркость свечения.", 0.6, 0.0, 2.0, 0.05).visibleWhen(() -> InterfaceModule.rectGlowIntensity$lambda$0(this)));
        this.rectGlowRadius = (NumberSetting)this.register((Setting)new NumberSetting("Радиус свечения", "Насколько далеко расходится свечение.", 15.0, 15.0, 70.0, 1.0).visibleWhen(() -> InterfaceModule.rectGlowRadius$lambda$0(this)));
        this.miscSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Прочее"));
        this.hudIcons = (BooleanSetting)this.register((Setting)new BooleanSetting("Иконки", "Показывает иконку справа в заголовке HUD-элементов, а название сдвигает влево.", false));
        stringArray = new String[]{"Обычный", "Проекция"};
        this.dragStyle = (ModeSetting)this.register((Setting)new ModeSetting("Перетаскивание", "Обычное сразу двигает элемент, проекция сначала показывает новое место.", "Проекция", stringArray));
        this.dragJitter = (BooleanSetting)this.register((Setting)new BooleanSetting("Тряска при перетаскивании", "Лёгкое дрожание элемента, пока его перетаскивают. Выключите для статичного перетаскивания.", true).visibleWhen(() -> InterfaceModule.dragJitter$lambda$0(this)));
        this.dragWaves = (BooleanSetting)this.register((Setting)new BooleanSetting("Волны при перетаскивании", "Показывает шейдерные волны позади зажатого элемента.", true).visibleWhen(() -> InterfaceModule.dragWaves$lambda$0(this)));
        this.dragTilt = (BooleanSetting)this.register((Setting)new BooleanSetting("Наклон при перетаскивании", "Плавно наклоняет элемент в сторону движения при перетаскивании.", true).visibleWhen(() -> InterfaceModule.dragTilt$lambda$0(this)));
        companionInstance = this;
    }

    @Override
    public boolean defaultEnabled() {
        return true;
    }

    public final boolean usesNewHudHeader() {
        return this.hudMode.is(HUD_MODE_NEW);
    }

    public final int gradientStyleId() {
        if (this.gradientStyle.is(GRADIENT_BLOBS)) {
            return 1;
        }
        return this.gradientStyle.is(GRADIENT_SQUARE) ? 2 : 0;
    }

    public final boolean isThemeClientColor() {
        return this.clientColorMode.is(CLIENT_COLOR_THEMES);
    }

    public final boolean isCustomClientColor() {
        return this.clientColorMode.is(CLIENT_COLOR_CUSTOM);
    }

    public final boolean isRainbowClientColor() {
        return this.clientColorMode.is(CLIENT_COLOR_RAINBOW);
    }

    public final int clientPrimaryColor() {
        return ClientAccent.gradientA(204.0f);
    }

    public final int clientSecondaryColor() {
        return ClientAccent.gradientB(204.0f);
    }

    public final int clientPrimaryColorAt(float screenX, float screenY) {
        return ClientAccent.gradientAAt(204.0f, screenX, screenY);
    }

    public final int clientSecondaryColorAt(float screenX, float screenY) {
        return ClientAccent.gradientBAt(204.0f, screenX, screenY);
    }

    public final boolean usesSecondClientColor() {
        return this.isThemeClientColor() || this.isRainbowClientColor() || this.rectUseSecondColor.getValue();
    }

    public final boolean clientColorMovement() {
        return this.usesSecondClientColor() && this.rectColorMovement.getValue();
    }

    public final int clientPrimaryColorOpaque() {
        return 0xFF000000 | this.clientPrimaryColor() & 0xFFFFFF;
    }

    public final int clientSecondaryColorOpaque() {
        return 0xFF000000 | this.clientSecondaryColor() & 0xFFFFFF;
    }

    public final int clientPrimaryColorOpaqueAt(float screenX, float screenY) {
        return 0xFF000000 | this.clientPrimaryColorAt(screenX, screenY) & 0xFFFFFF;
    }

    public final int clientSecondaryColorOpaqueAt(float screenX, float screenY) {
        return 0xFF000000 | this.clientSecondaryColorAt(screenX, screenY) & 0xFFFFFF;
    }

    @NotNull
    public final int[] clientPalette() {
        int[] nArray;
        int[] palette;
        if (this.isRainbowClientColor()) {
            return this.getRainbowPalette();
        }
        if (this.isThemeClientColor() && (palette = ThemeManager.blendedPalette()) != null && !(palette.length == 0)) {
            return palette;
        }
        if (this.rectUseSecondColor.getValue()) {
            nArray = new int[]{this.rectColor.getColor() & 0xFFFFFF, this.rectSecondColor.getColor() & 0xFFFFFF};
            return nArray;
        }
        nArray = new int[]{this.rectColor.getColor() & 0xFFFFFF};
        return nArray;
    }

    private final int[] getRainbowPalette() {
        float sat = this.rainbowSaturation.getFloat();
        float spread = Math.min(1.0f, this.rainbowSpread.getFloat() * 4.0f);
        float phase = ClientAccent.rainbowBaseHue();
        int n = this.rainbowPalette.length;
        for (int i = 0; i < n; ++i) {
            float hue = phase + (float)i / (float)(n - 1) * spread;
            hue -= (float)Math.floor(hue);
            int rgb = Color.HSBtoRGB(hue, 1.0f, 1.0f) & 0xFFFFFF;
            this.rainbowPalette[i] = InterfaceModule.Companion.lerpWhite(rgb, sat);
        }
        return this.rainbowPalette;
    }

    private static final Boolean rainbowSpeed$lambda$0(InterfaceModule this$0) {
        return this$0.isRainbowClientColor();
    }

    private static final Boolean rainbowSpread$lambda$0(InterfaceModule this$0) {
        return this$0.isRainbowClientColor();
    }

    private static final Boolean rainbowSaturation$lambda$0(InterfaceModule this$0) {
        return this$0.isRainbowClientColor();
    }

    private static final Boolean rectColor$lambda$0(InterfaceModule this$0) {
        return this$0.isCustomClientColor();
    }

    private static final Boolean rectUseSecondColor$lambda$0(InterfaceModule this$0) {
        return this$0.isCustomClientColor();
    }

    private static final Boolean rectSecondColor$lambda$0(InterfaceModule this$0) {
        return this$0.isCustomClientColor() && this$0.rectUseSecondColor.getValue();
    }

    private static final Boolean rectColorMovement$lambda$0(InterfaceModule this$0) {
        return this$0.usesSecondClientColor();
    }

    private static final Boolean rectEdgeSharpness$lambda$0(InterfaceModule this$0) {
        return this$0.rectEdgeStrength.getFloat() > 0.0f;
    }

    private static final Boolean rectGlowIntensity$lambda$0(InterfaceModule this$0) {
        return this$0.rectGlow.getValue();
    }

    private static final Boolean rectGlowRadius$lambda$0(InterfaceModule this$0) {
        return this$0.rectGlow.getValue();
    }

    private static final Boolean dragJitter$lambda$0(InterfaceModule this$0) {
        return this$0.dragStyle.is("Проекция");
    }

    private static final Boolean dragWaves$lambda$0(InterfaceModule this$0) {
        return this$0.dragStyle.is("Проекция");
    }

    private static final Boolean dragTilt$lambda$0(InterfaceModule this$0) {
        return this$0.dragStyle.is("Обычный");
    }

    @JvmStatic
    @Nullable
    public static final InterfaceModule getInstance() {
        return Companion.getInstance();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\f\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000e8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u000e8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u000e8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0010R\u0014\u0010\u0013\u001a\u00020\u000e8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0010R\u0014\u0010\u0014\u001a\u00020\u000e8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0010R\u0014\u0010\u0015\u001a\u00020\u000e8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0010R\u0014\u0010\u0016\u001a\u00020\u000e8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0010R\u0014\u0010\u0017\u001a\u00020\u000e8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0010R\u0014\u0010\u0018\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001b\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/InterfaceModule.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Interface/InterfaceModule;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Interface/InterfaceModule;", "", "rgb", "", "sat", "lerpWhite", "(IF)I", "", "HUD_MODE_OLD", "Ljava/lang/String;", "HUD_MODE_NEW", "CLIENT_COLOR_THEMES", "CLIENT_COLOR_CUSTOM", "CLIENT_COLOR_RAINBOW", "GRADIENT_HORIZONTAL", "GRADIENT_BLOBS", "GRADIENT_SQUARE", "THEME_COLOR_ALPHA", "F", "companionInstance", "Lrtx/kimiko/api/modules/impl/Interface/InterfaceModule;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final InterfaceModule getInstance() {
            return companionInstance;
        }

        private final int lerpWhite(int rgb, float sat) {
            float keep = Math.max(0.0f, Math.min(1.0f, sat));
            int r = MathKt.roundToInt((float)((float)255 + (float)((rgb >> 16 & 0xFF) - 255) * keep));
            int g = MathKt.roundToInt((float)((float)255 + (float)((rgb >> 8 & 0xFF) - 255) * keep));
            int b = MathKt.roundToInt((float)((float)255 + (float)((rgb & 0xFF) - 255) * keep));
            return r << 16 | g << 8 | b;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

