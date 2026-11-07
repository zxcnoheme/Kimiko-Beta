package rtx.kimiko.api.modules.impl.Visuals.cosmetics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import rtx.kimiko.api.config.ConfigManager;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Выбор косметик по категориям (одна активная на категорию) с сохранением в config/kimiko/cosmetics.json.
 * Заменяет локальные настройки и синхронизацию удалённого модуля Customization.
 */
public final class CosmeticSettings {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILE = "cosmetics.json";
    private static final CosmeticSettings INSTANCE = new CosmeticSettings();

    /** категория -> id косметики ("" = ничего не надето). */
    private final Map<CosmeticType.Cat, String> selected = new EnumMap<>(CosmeticType.Cat.class);
    /** доп. ключи вкладки (табы без категории CosmeticType). */
    private final Map<String, String> extra = new HashMap<>();

    private CosmeticSettings() {
        load();
    }

    public static CosmeticSettings get() {
        return INSTANCE;
    }

    /** id косметики в категории (может быть "" — ничего не выбрано). */
    public String selected(CosmeticType.Cat cat) {
        String id = this.selected.get(cat);
        return id == null ? "" : id;
    }

    /** Выбор по ключу таба вкладки Cosmetic ("tab0".."tab4"). */
    public String tabSelected(String tabKey) {
        CosmeticType.Cat cat = tabCat(tabKey);
        if (cat != null) {
            return this.selected(cat);
        }
        String id = this.extra.get(tabKey);
        return id == null ? "" : id;
    }

    public void tabSelect(String tabKey, String id) {
        CosmeticType.Cat cat = tabCat(tabKey);
        if (cat != null) {
            this.select(cat, id);
        } else {
            // табы вне категорий CosmeticType (Наспине) храним под своим ключом
            this.extra.put(tabKey, id);
            this.save();
        }
    }

    public void tabClear(String tabKey) {
        CosmeticType.Cat cat = tabCat(tabKey);
        if (cat != null) {
            this.clear(cat);
        } else {
            this.extra.put(tabKey, "");
            this.save();
        }
    }

    private static CosmeticType.Cat tabCat(String tabKey) {
        return switch (tabKey) {
            case "tab0" -> CosmeticType.Cat.WINGS;
            case "tab1" -> CosmeticType.Cat.HEAD;
            case "tab2" -> CosmeticType.Cat.SHOULDER;
            case "tab3" -> CosmeticType.Cat.PETS;
            default -> null;
        };
    }

    public CosmeticType selectedType(CosmeticType.Cat cat) {
        return CosmeticType.byId(this.selected(cat), cat);
    }

    public void select(CosmeticType.Cat cat, String id) {
        this.selected.put(cat, id == null ? "" : id);
        this.save();
    }

    public void clear(CosmeticType.Cat cat) {
        this.select(cat, "");
    }

    public boolean hasAny() {
        for (CosmeticType.Cat cat : CosmeticType.Cat.values()) {
            if (!this.selected(cat).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /** Надеты ли крылья у игрока (только локальный игрок; чужие больше не синхронизируются). */
    public static boolean wingsActiveFor(AbstractClientPlayerEntity player) {
        if (player == null) {
            return false;
        }
        net.minecraft.client.MinecraftClient mc = net.minecraft.client.MinecraftClient.getInstance();
        if (mc == null || mc.player == null || player != mc.player) {
            return false;
        }
        return !INSTANCE.selected(CosmeticType.Cat.WINGS).isEmpty();
    }

    public String wingsId() {
        return this.selected(CosmeticType.Cat.WINGS);
    }

    public String headId() {
        return this.selected(CosmeticType.Cat.HEAD);
    }

    public String shoulderId() {
        return this.selected(CosmeticType.Cat.SHOULDER);
    }

    /** id питомца для CustomPet ("" = питомца нет; внешние питомцы пока не поддерживаются). */
    public String petId() {
        String id = this.selected(CosmeticType.Cat.PETS);
        return id.startsWith("ext:") ? "" : id;
    }

    /** Выбор таба «Крылья» (может быть внешним "ext:..."). */
    public String wingsSelection() {
        return this.tabSelected("tab0");
    }

    /** Выбор таба «Голова» (может быть внешним "ext:..."). */
    public String headSelection() {
        return this.tabSelected("tab1");
    }

    /** Выбор таба «Плечо» (может быть внешним "ext:..."). */
    public String shoulderSelection() {
        return this.tabSelected("tab2");
    }

    /** Выбор таба «Питомцы» (может быть внешним "ext:..."). */
    public String petSelection() {
        return this.tabSelected("tab3");
    }

    /** Выбор таба «Наспине» (плащи/рюкзаки, внешние). */
    public String backSelection() {
        return this.tabSelected("tab4");
    }

    /** Выбор таба «Одежда» (внешние). */
    public String clothingSelection() {
        return this.tabSelected("tab5");
    }

    /** Выбор таба «Эффекты» (внешние). */
    public String effectSelection() {
        return this.tabSelected("tab6");
    }

    private Path file() {
        return ConfigManager.Companion.systemConfigDirectory().resolve(FILE);
    }

    private void load() {
        try {
            Path path = this.file();
            if (!Files.exists(path)) {
                return;
            }
            String body = Files.readString(path, StandardCharsets.UTF_8);
            JsonObject root = JsonParser.parseString(body).getAsJsonObject();
            for (CosmeticType.Cat cat : CosmeticType.Cat.values()) {
                if (root.has(cat.name()) && root.get(cat.name()).isJsonPrimitive()) {
                    this.selected.put(cat, root.get(cat.name()).getAsString());
                }
            }
            if (root.has("extra") && root.get("extra").isJsonObject()) {
                JsonObject ex = root.getAsJsonObject("extra");
                for (Map.Entry<String, com.google.gson.JsonElement> e : ex.entrySet()) {
                    this.extra.put(e.getKey(), e.getValue().getAsString());
                }
            }
        } catch (Exception ignored) {
        }
    }

    private void save() {
        try {
            JsonObject root = new JsonObject();
            for (CosmeticType.Cat cat : CosmeticType.Cat.values()) {
                root.addProperty(cat.name(), this.selected(cat));
            }
            if (!this.extra.isEmpty()) {
                JsonObject ex = new JsonObject();
                for (Map.Entry<String, String> e : this.extra.entrySet()) {
                    ex.addProperty(e.getKey(), e.getValue());
                }
                root.add("extra", ex);
            }
            Path path = this.file();
            Files.createDirectories(path.getParent());
            Files.writeString(path, GSON.toJson(root), StandardCharsets.UTF_8);
        } catch (Exception ignored) {
        }
    }
}
