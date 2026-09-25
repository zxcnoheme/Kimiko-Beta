package rtx.kimiko.api.modules.impl.Visuals;

import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.SwordPickerSetting;

@Feature(value={"customswords"})
public final class CustomSwords
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final BooleanSetting selfOnly = (BooleanSetting)this.register((Setting)new BooleanSetting("Только свой", "Заменять модель меча только у своего игрока.", true));
    @NotNull
    private final ModeSetting weapon;
    @NotNull
    private final SwordPickerSetting picker = (SwordPickerSetting)this.register((Setting)new SwordPickerSetting("Выбор меча", "Сетка 3x3: выберите меч колесом мыши, клик — подтвердить."));
    @NotNull
    private static final String[] WEAPONS;
    @NotNull
    private static final String[] DISPLAY_WEAPONS;
    @JvmField
    @Nullable
    public static CustomSwords INSTANCE;
    @Nullable
    private static String previewWeapon;

    public CustomSwords() {
        super("Custom Swords", "Заменяет модели ванильных мечей.", Category.VISUALS);
        String[] stringArray = DISPLAY_WEAPONS;
        this.weapon = (ModeSetting)this.register((Setting)new ModeSetting("Оружие", "Модель, которая заменит ванильные мечи.", DISPLAY_WEAPONS[0], Arrays.copyOf(stringArray, stringArray.length)));
        INSTANCE = this;
    }

    public final boolean isSelfOnly() {
        return this.selfOnly.getValue();
    }

    @NotNull
    public final String getSelectedWeapon() {
        String selected = this.weapon.getSelected();
        int n = DISPLAY_WEAPONS.length;
        for (int i = 0; i < n; ++i) {
            if (!Intrinsics.areEqual((Object)DISPLAY_WEAPONS[i], (Object)selected)) continue;
            return WEAPONS[i];
        }
        String string = selected;
        if (string == null) {
            string = "";
        }
        return string;
    }

    public final int weaponCount() {
        return WEAPONS.length;
    }

    @NotNull
    public final String displayName(int index) {
        String string = DISPLAY_WEAPONS[Math.floorMod(index, DISPLAY_WEAPONS.length)];
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"DISPLAY_WEAPONS[Math.floorMod(index, DISPLAY_WEAPONS.length)]");
        return string;
    }

    @NotNull
    public final String weaponModel(int index) {
        String string = WEAPONS[Math.floorMod(index, WEAPONS.length)];
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"WEAPONS[Math.floorMod(index, WEAPONS.length)]");
        return string;
    }

    public final int currentWeaponIndex() {
        String selected = this.weapon.getSelected();
        int n = DISPLAY_WEAPONS.length;
        for (int i = 0; i < n; ++i) {
            if (!Intrinsics.areEqual((Object)DISPLAY_WEAPONS[i], (Object)selected)) continue;
            return i;
        }
        return 0;
    }

    public final void selectWeaponIndex(int index) {
        this.weapon.setSelected(DISPLAY_WEAPONS[Math.floorMod(index, DISPLAY_WEAPONS.length)]);
    }

    @JvmStatic
    @Nullable
    public static final CustomSwords getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    @Nullable
    public static final String preview() {
        return previewWeapon;
    }

    @JvmStatic
    public static final void setPreview(@Nullable String weapon) {
        previewWeapon = weapon;
    }

    @JvmStatic
    @NotNull
    public static final ItemStack previewStack(@NotNull ItemStack base, @NotNull String weapon) {
        Intrinsics.checkNotNullParameter((Object)base, (String)"base");
        Intrinsics.checkNotNullParameter((Object)weapon, (String)"weapon");
        ItemStack stack = base.copy();
        stack.set(DataComponentTypes.CUSTOM_MODEL_DATA, new CustomModelDataComponent(List.of(), List.of(), List.of(weapon), List.of()));
        return stack;
    }

    static {
String[] stringArray = new String[]{"Abominable Blade", "Abominable Great Saber", "Abominable Scythe", "Acidic Cleaver", "Amethyst Shuriken", "Ancient Royal Great Sword", "Aquatic Sacred Blade", "Arcanethyst", "Ashura's Blade", "Awakened Lichblade", "Blood Edge", "Bloody Death", "Bramblethorn", "Brimstone Claymore", "Carian Knight's Sword", "Chrono Blade", "Corrupted Mythic Blade", "Creation Splitter", "Crescent Rose", "Cyber Katana", "Cyber Mantis Blade", "Cyber Sword", "Cybernetic Chainsaw Blade", "Cybernetic Katana", "Cybernetic Knife", "Dainsleif", "Dark Blade", "Dark Cleaver", "Death Knight's Dagger", "Death Knight's Sword", "Demigod's Unholy Blade", "Demigod's Unholy Halberd", "Demon Lord's Great Axe", "Demon Lord's Sword", "Demonic Blade", "Demonic Cleaver", "Divine Axe Rhitta", "Divine Justice", "Divine Punisher", "Divine Reaper", "Dragon Slaying blade", "Edge Of The Astral Plane", "Emberblade", "Enigma", "Epic Sword", "Estoc", "Fallen God's Spear", "Fallen God's Sword", "Floral Longsword", "Floral Sabre", "Forest Guardian's Glaive", "Frost Axe", "Frost Blade", "Frost Scythe", "Hearthflame", "Hero Sword", "Holy Moonlight Sword", "Hornet's Needle", "Icewhisper", "Jade Halberd", "Katana", "Legendary Sword", "Longsword", "Magi Scythe", "Masamune", "Mjolnir", "Molten Blade", "Molten Sword", "Muramasa", "Mystical Spellblade", "Mythic Blade", "Ocean's Rage", "Partisan", "Pharaoh's Treasure", "Pheonix Grace", "Plague Longsword", "Power Fuse Hammer", "Power Fuse Sword", "Requiem of the Ninth Abyss", "Ribbon Cleaver", "Righteous Relic", "Rivers Of Blood", "Royal Chakram", "Royal Rapier", "Sabre", "Scissor Blade", "Sculk Cleaver", "Sculk Scythe", "Sculk Sword", "Sentinel's Will", "Silverine Blade", "Soul Claws", "Soul Collector", "Soul Devourer", "Soul Edge", "Soul Harvester", "Soul Stealer", "Soulrender", "Star's Edge", "Steel Sword", "Stop Sign", "Storm Bringer", "Storm's Edge", "Sunbreak", "Tengen's Blade", "Terra Blade", "Thousand Demon Daggers", "Thunder Bringer", "Thunderbrand", "True Excalibur", "Vampiric Needle", "Wakizashi", "Watcher Claymore", "Watching Warglaive", "Waxweaver", "Whisperwind", "Wickpiercer", "Wraith Scythe", "Yoru"};
        WEAPONS = stringArray;
        DISPLAY_WEAPONS = CustomSwords.Companion.displayNames();
    }

    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final CustomSwords getInstance() {
            CustomSwords module = ModuleManager.Companion.get().get(CustomSwords.class);
            CustomSwords customSwords = module;
            if (customSwords == null) {
                customSwords = INSTANCE;
            }
            return customSwords;
        }

        private final String[] displayNames() {
            int n = 0;
            int n2 = WEAPONS.length;
            String[] stringArray = new String[n2];
            while (n < n2) {
                int n3 = n++;
                stringArray[n3] = "";
            }
            String[] names = stringArray;
            n2 = WEAPONS.length;
            for (int i = 0; i < n2; ++i) {
                names[i] = String.valueOf(WEAPONS[i]).replace("'", "");
            }
            return names;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}