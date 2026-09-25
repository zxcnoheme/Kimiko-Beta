/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmOverloads
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.Items
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.awt.Color;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.inventory.SlotCategory;
import rtx.kimiko.utils.inventory.SlotItem;
import rtx.kimiko.utils.render.render2d.Render2D;

@Feature(value={"itemhighlight"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 <2\u00020\u0001:\u0002=<B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ'\u0010\u0013\u001a\u00020\u00112\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000fH\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\u0017H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ/\u0010#\u001a\u00020\n2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\u0006\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u0011\u00a2\u0006\u0004\b#\u0010$J5\u0010'\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u0017\u00a2\u0006\u0004\b'\u0010(JU\u0010'\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u00172\u0006\u0010)\u001a\u00020\u00172\u0006\u0010*\u001a\u00020\u00172\u0006\u0010+\u001a\u00020\u00172\u0006\u0010,\u001a\u00020\u0017\u00a2\u0006\u0004\b'\u0010-JE\u0010'\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u00172\u0006\u0010.\u001a\u00020\u00172\u0006\u0010/\u001a\u00020\u00172\u0006\u00100\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u0017\u00a2\u0006\u0004\b'\u00101R \u00105\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u000204028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00106R \u00108\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u000207028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00106R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u0010;\u00ca\u0001\u0010\b>\u0012\f\b?\u0012\b\b\fJ\u0004\b\b(@\u00a8\u0006A"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ItemHighlight;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "Lnet/minecraft/Item;", "item", "", "name", "Ljava/awt/Color;", "color", "", "addItem", "(Lnet/minecraft/Item;Ljava/lang/String;Ljava/awt/Color;)V", "Lnet/minecraft/ItemStack;", "stack", "", "isHotbar", "", "Lkotlin/jvm/JvmOverloads;", "backgroundFor", "(Lnet/minecraft/ItemStack;Z)I", "potionBackground", "(Lnet/minecraft/ItemStack;)I", "", "fadeOutSeconds", "()F", "rgb", "settingAlpha", "withOpacity", "(IF)I", "Lnet/minecraft/DrawContext;", "graphics", "x", "y", "argb", "drawSlotBackground", "(Lnet/minecraft/DrawContext;III)V", "size", "alpha", "drawRoundedSlotBackground", "(FFFIF)V", "radiusTopLeft", "radiusTopRight", "radiusBottomRight", "radiusBottomLeft", "(FFFIFFFFF)V", "width", "height", "radius", "(FFFFFIF)V", "", "Lrtx/kimiko/utils/inventory/SlotItem;", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "itemColors", "Ljava/util/Map;", "Lrtx/kimiko/api/modules/impl/Visuals/ItemHighlight$ItemEntry;", "itemEntries", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "opacity", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "Companion", "ItemEntry", "Lrtx/kimiko/api/liteapi/Feature;", "value", "itemhighlight", "rtx.kimiko:kimiko"})
public final class ItemHighlight
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Map<SlotItem, ColorSetting> itemColors = new EnumMap(SlotItem.class);
    @NotNull
    private final Map<Item, ItemEntry> itemEntries = new LinkedHashMap();
    @NotNull
    private final SliderSetting opacity = (SliderSetting)this.register((Setting)new SliderSetting("Прозрачность", "Непрозрачность фона.").range(0, 100).increment(1).setValue(80.0f));
    @JvmField
    @Nullable
    public static ItemHighlight INSTANCE;

    public ItemHighlight() {
        super("Item Highlight", "Подсвечивает фон ячеек для нужных предметов.", Category.VISUALS);
        INSTANCE = this;
        for (SlotItem item : SlotItem.values()) {
            ColorSetting setting = (ColorSetting)this.register((Setting)new ColorSetting(item.getDisplayName(), "Цвет фона для «" + item.getDisplayName() + "».", new Color(item.getDefaultColor() | 0xFF000000, true)));
            this.itemColors.put(item, setting);
        }
        this.register((Setting)new SeparatorSetting("Предметы"));
        Item item2 = Items.ENDER_PEARL;
        Intrinsics.checkNotNullExpressionValue((Object)item2, (String)"ENDER_PEARL");
        this.addItem(item2, "Эндер пёрл", new Color(0, 150, 0));
        Item item3 = Items.SNOWBALL;
        Intrinsics.checkNotNullExpressionValue((Object)item3, (String)"SNOWBALL");
        this.addItem(item3, "Снежок", new Color(100, 200, 255));
        Item item4 = Items.NETHERITE_SCRAP;
        Intrinsics.checkNotNullExpressionValue((Object)item4, (String)"NETHERITE_SCRAP");
        this.addItem(item4, "Трапка", new Color(200, 180, 150));
        Item item5 = Items.LANTERN;
        Intrinsics.checkNotNullExpressionValue((Object)item5, (String)"LANTERN");
        this.addItem(item5, "Светильник", new Color(255, 150, 0));
        Item item6 = Items.FIRE_CHARGE;
        Intrinsics.checkNotNullExpressionValue((Object)item6, (String)"FIRE_CHARGE");
        this.addItem(item6, "Взрывная т", new Color(255, 0, 0));
        Item item7 = Items.TOTEM_OF_UNDYING;
        Intrinsics.checkNotNullExpressionValue((Object)item7, (String)"TOTEM_OF_UNDYING");
        this.addItem(item7, "Тотем", new Color(255, 200, 0));
        Item item8 = Items.CROSSBOW;
        Intrinsics.checkNotNullExpressionValue((Object)item8, (String)"CROSSBOW");
        this.addItem(item8, "Арбалет", new Color(150, 100, 50));
        Item item9 = Items.NETHERITE_SWORD;
        Intrinsics.checkNotNullExpressionValue((Object)item9, (String)"NETHERITE_SWORD");
        this.addItem(item9, "Незеритовый меч", new Color(50, 50, 50));
        Item item10 = Items.CHORUS_FRUIT;
        Intrinsics.checkNotNullExpressionValue((Object)item10, (String)"CHORUS_FRUIT");
        this.addItem(item10, "Хорус", new Color(200, 100, 200));
        Item item11 = Items.SUGAR;
        Intrinsics.checkNotNullExpressionValue((Object)item11, (String)"SUGAR");
        this.addItem(item11, "Сахар", new Color(255, 255, 255));
        Item item12 = Items.PHANTOM_MEMBRANE;
        Intrinsics.checkNotNullExpressionValue((Object)item12, (String)"PHANTOM_MEMBRANE");
        this.addItem(item12, "Мембрана", new Color(200, 180, 150));
        Item item13 = Items.ENDER_EYE;
        Intrinsics.checkNotNullExpressionValue((Object)item13, (String)"ENDER_EYE");
        this.addItem(item13, "Око эндера", new Color(100, 0, 200));
        Item item14 = Items.DRIED_KELP;
        Intrinsics.checkNotNullExpressionValue((Object)item14, (String)"DRIED_KELP");
        this.addItem(item14, "Ламинария", new Color(100, 150, 50));
        Item item15 = Items.EXPERIENCE_BOTTLE;
        Intrinsics.checkNotNullExpressionValue((Object)item15, (String)"EXPERIENCE_BOTTLE");
        this.addItem(item15, "Пузырек опыта", new Color(0, 200, 100));
        Item item16 = Items.GOLDEN_APPLE;
        Intrinsics.checkNotNullExpressionValue((Object)item16, (String)"GOLDEN_APPLE");
        this.addItem(item16, "Золотое яблоко", new Color(255, 200, 0));
        Item item17 = Items.ENCHANTED_GOLDEN_APPLE;
        Intrinsics.checkNotNullExpressionValue((Object)item17, (String)"ENCHANTED_GOLDEN_APPLE");
        this.addItem(item17, "Чар. яблоко", new Color(255, 150, 0));
    }

    private final void addItem(Item item, String name, Color color) {
        BooleanSetting toggle = (BooleanSetting)this.register((Setting)new BooleanSetting(name, "Подсветка «" + name + "».", true));
        ColorSetting colorSetting = (ColorSetting)this.register((Setting)new ColorSetting(name + " цвет", "Цвет фона для «" + name + "».", new Color(color.getRGB() | 0xFF000000, true)).visibleWhen(() -> ItemHighlight.addItem$lambda$0(toggle)));
        this.itemEntries.put(item, new ItemEntry(toggle, colorSetting));
    }

    @JvmOverloads
    public final int backgroundFor(@Nullable ItemStack stack, boolean isHotbar) {
        if (!this.isVisuallyActive() || stack == null || stack.isEmpty()) {
            return 0;
        }
        int potion = this.potionBackground(stack);
        if (potion != 0) {
            return potion;
        }
        ItemEntry entry = this.itemEntries.get(stack.getItem());
        if (entry == null || !entry.getEnabled().getValue()) {
            return 0;
        }
        int rgb = entry.getColor().getColorOpaque() & 0xFFFFFF;
        return this.withOpacity(rgb, entry.getColor().getAlpha());
    }

    public static /* synthetic */ int backgroundFor$default(ItemHighlight itemHighlight, ItemStack itemStack2, boolean bl, int n, Object object) {
        if ((n & 2) != 0) {
            bl = false;
        }
        return itemHighlight.backgroundFor(itemStack2, bl);
    }

    private final int potionBackground(ItemStack stack) {
        SlotCategory slotCategory = SlotCategory.Companion.classify(stack);
        if (slotCategory == null) {
            return 0;
        }
        SlotCategory category = slotCategory;
        SlotItem slotItem = SlotItem.Companion.match(stack, category.getItemGroup());
        if (slotItem == null) {
            return 0;
        }
        SlotItem item = slotItem;
        ColorSetting setting = this.itemColors.get((Object)item);
        int rgb = 0;
        float settingAlpha = 0.0f;
        if (setting != null) {
            rgb = setting.getColorOpaque() & 0xFFFFFF;
            settingAlpha = setting.getAlpha();
        } else {
            rgb = category.getDefaultColor() & 0xFFFFFF;
            settingAlpha = 1.0f;
        }
        return this.withOpacity(rgb, settingAlpha);
    }

    @Override
    public float fadeOutSeconds() {
        return 0.4f;
    }

    private final int withOpacity(int rgb, float settingAlpha) {
        float slider = Math.max(0.0f, Math.min(1.0f, this.opacity.getFloat() / 100.0f)) * this.visualAlpha();
        int alpha = Math.round(255.0f * settingAlpha * slider);
        if (alpha <= 0) {
            return 0;
        }
        return alpha << 24 | rgb;
    }

    public final void drawSlotBackground(@Nullable DrawContext graphics, int x, int y, int argb) {
        if (graphics == null || argb == 0) {
            return;
        }
        graphics.fill(x, y, x + 16, y + 16, argb);
    }

    public final void drawRoundedSlotBackground(float x, float y, float size, int argb, float alpha) {
        if (argb == 0 || size <= 0.0f || alpha <= 0.0f) {
            return;
        }
        InterfaceModule module = InterfaceModule.Companion.getInstance();
        Object object = module;
        float radius = object != null && (object = ((InterfaceModule)object).rectCornerRadius) != null ? ((SliderSetting)object).getFloat() : 2.0f;
        radius = Math.min(radius, size * 0.5f);
        this.drawRoundedSlotBackground(x, y, size, argb, alpha, radius, radius, radius, radius);
    }

    public final void drawRoundedSlotBackground(float x, float y, float size, int argb, float alpha, float radiusTopLeft, float radiusTopRight, float radiusBottomRight, float radiusBottomLeft) {
        if (argb == 0 || size <= 0.0f || alpha <= 0.0f) {
            return;
        }
        float maxRadius = size * 0.5f;
        Render2D.rect(x, y, size, size, Math.min(radiusTopLeft, maxRadius), Math.min(radiusTopRight, maxRadius), Math.min(radiusBottomRight, maxRadius), Math.min(radiusBottomLeft, maxRadius), ColorEngine.multAlpha(argb, alpha));
    }

    public final void drawRoundedSlotBackground(float x, float y, float width, float height, float radius, int argb, float alpha) {
        if (argb == 0 || width <= 0.0f || height <= 0.0f || alpha <= 0.0f) {
            return;
        }
        float r = Math.max(0.0f, Math.min(radius, Math.min(width, height) * 0.5f));
        Render2D.rect(x, y, width, height, r, ColorEngine.multAlpha(argb, alpha));
    }

    @JvmOverloads
    public final int backgroundFor(@Nullable ItemStack stack) {
        return ItemHighlight.backgroundFor$default(this, stack, false, 2, null);
    }

    private static final Boolean addItem$lambda$0(BooleanSetting $toggle) {
        return $toggle.getValue();
    }

    @JvmStatic
    @Nullable
    public static final ItemHighlight getInstance() {
        return Companion.getInstance();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u001d\u0010\t\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\b\u00a2\u0006\u0006\n\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ItemHighlight.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/ItemHighlight;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/ItemHighlight;", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/ItemHighlight;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final ItemHighlight getInstance() {
            ItemHighlight module = ModuleManager.Companion.get().get(ItemHighlight.class);
            ItemHighlight itemHighlight = module;
            if (itemHighlight == null) {
                itemHighlight = INSTANCE;
            }
            return itemHighlight;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ$\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0016\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u000b\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ItemHighlight$ItemEntry;", "", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "enabled", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "color", "<init>", "(Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;Lrtx/kimiko/api/modules/settings/impl/ColorSetting;)V", "component1", "()Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "component2", "()Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "copy", "(Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;Lrtx/kimiko/api/modules/settings/impl/ColorSetting;)Lrtx/kimiko/api/modules/impl/Visuals/ItemHighlight$ItemEntry;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "getEnabled", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "getColor", "rtx.kimiko:kimiko"})
    private static final class ItemEntry {
        @NotNull
        private final BooleanSetting enabled;
        @NotNull
        private final ColorSetting color;

        public ItemEntry(@NotNull BooleanSetting enabled, @NotNull ColorSetting color) {
            Intrinsics.checkNotNullParameter((Object)enabled, (String)"enabled");
            Intrinsics.checkNotNullParameter((Object)color, (String)"color");
            this.enabled = enabled;
            this.color = color;
        }

        @NotNull
        public final BooleanSetting getEnabled() {
            return this.enabled;
        }

        @NotNull
        public final ColorSetting getColor() {
            return this.color;
        }

        @NotNull
        public final BooleanSetting component1() {
            return this.enabled;
        }

        @NotNull
        public final ColorSetting component2() {
            return this.color;
        }

        @NotNull
        public final ItemEntry copy(@NotNull BooleanSetting enabled, @NotNull ColorSetting color) {
            Intrinsics.checkNotNullParameter((Object)enabled, (String)"enabled");
            Intrinsics.checkNotNullParameter((Object)color, (String)"color");
            return new ItemEntry(enabled, color);
        }

        public static /* synthetic */ ItemEntry copy$default(ItemEntry itemEntry, BooleanSetting booleanSetting, ColorSetting colorSetting, int n, Object object) {
            if ((n & 1) != 0) {
                booleanSetting = itemEntry.enabled;
            }
            if ((n & 2) != 0) {
                colorSetting = itemEntry.color;
            }
            return itemEntry.copy(booleanSetting, colorSetting);
        }

        @NotNull
        public String toString() {
            return "ItemEntry(enabled=" + this.enabled + ", color=" + this.color + ")";
        }

        public int hashCode() {
            int result = this.enabled.hashCode();
            result = result * 31 + this.color.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ItemEntry)) {
                return false;
            }
            ItemEntry itemEntry = (ItemEntry)other;
            if (!Intrinsics.areEqual((Object)this.enabled, (Object)itemEntry.enabled)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.color, (Object)itemEntry.color);
        }
    }
}

