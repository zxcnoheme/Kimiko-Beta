package rtx.kimiko.api.modules.impl.Visuals.cosmetics;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.Kimiko;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Реестр встроенных косметик из ресурсов: assets/kimiko/cosmetics/&lt;категория&gt;/&lt;имя&gt;/model.json
 * (формат SpaceVisuals: метаданные + base64-текстура + Bedrock geometry + animation).
 * Категории ресурсов = слоты крепления: wings/hat/pet/cape/bodywear.
 */
public final class BundledCosmetics {
    /** Папки ресурсов = категории (порядок сканирования). */
    public static final String[] CATEGORIES = {
            "wings", "hat", "pet", "cape", "bodywear",
            "essential_wings", "essential_hat", "essential_head", "essential_back", "essential_pet",
            "essential_shoulders", "essential_cape", "essential_face", "essential_ears",
            "essential_accessory", "essential_full_body", "essential_arms", "essential_top",
            "essential_pants", "essential_shoes", "essential_skirt", "essential_effect"
    };

    /** Соответствие папки → логическая категория (для фильтрации по табам). */
    public static String logicalCategory(String folder) {
        if (folder.startsWith("essential_")) {
            return folder.substring("essential_".length());
        }
        return folder;
    }

    private static final Map<String, ExternalCosmetic> BY_KEY = new HashMap<>();
    private static List<ExternalCosmetic> ALL = new ArrayList<>();
    private static boolean scanned;

    private BundledCosmetics() {
    }

    /** Ленивое сканирование ресурсов (первый вызов — при открытии вкладки Cosmetic). */
    public static synchronized List<ExternalCosmetic> all() {
        if (!scanned) {
            scanned = true;
            scan();
        }
        return ALL;
    }

    /** id вида "wings/angel-wings". */
    @Nullable
    public static ExternalCosmetic byKey(String key) {
        if (key == null || key.isEmpty()) {
            return null;
        }
        for (ExternalCosmetic c : all()) {
            if (c.key().equals(key)) {
                return c;
            }
        }
        return null;
    }

    /** Соответствие категории ресурса слоту крепления. */
    public static ExternalCosmetic.Slot slotOf(String category) {
        return switch (category) {
            case "hat", "head", "face", "ears", "accessory" -> ExternalCosmetic.Slot.HEAD;
            case "pet" -> ExternalCosmetic.Slot.PET;
            case "shoulders" -> ExternalCosmetic.Slot.SHOULDER;
            case "full_body", "top", "pants", "shoes", "skirt", "arms", "effect" -> ExternalCosmetic.Slot.BODY;
            default -> ExternalCosmetic.Slot.BACK;
        };
    }

    private static void scan() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null || mc.getResourceManager() == null) {
            return;
        }
        List<ExternalCosmetic> found = new ArrayList<>();
        for (String category : CATEGORIES) {
            String logical = logicalCategory(category);
            List<String> names = listCategory(mc, category);
            for (String name : names) {
                try {
                    Identifier id = Identifier.of(Kimiko.Companion.namespace(), "cosmetics/" + category + "/" + name + "/model.json");
                    Resource resource = mc.getResourceManager().getResource(id).orElse(null);
                    if (resource == null) {
                        continue;
                    }
                    JsonObject meta;
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
                        meta = JsonParser.parseReader(reader).getAsJsonObject();
                    }
                    String textureData = meta.has("texture") && meta.get("texture").isJsonPrimitive()
                            ? meta.get("texture").getAsString() : null;
                    BedrockModel model = null;
                    if (meta.has("model") && meta.get("model").isJsonObject()) {
                        model = BedrockModel.parse(meta.getAsJsonObject("model"),
                                meta.has("animation") && meta.get("animation").isJsonObject()
                                        ? meta.getAsJsonObject("animation") : null);
                    }
                    if (model == null) {
                        continue;
                    }
                    ExternalCosmetic.Slot slot = slotOf(logical);
                    ExternalCosmetic cosmetic = new ExternalCosmetic(logical, name, meta, model, textureData, slot, category);
                    found.add(cosmetic);
                } catch (Exception ignored) {
                }
            }
        }
        BY_KEY.clear();
        ALL = found;
        for (ExternalCosmetic c : found) {
            BY_KEY.put(c.key(), c);
        }
    }

    /** Читает index.json категории (список имён). */
    private static List<String> listCategory(MinecraftClient mc, String category) {
        List<String> names = new ArrayList<>();
        try {
            Identifier id = Identifier.of(Kimiko.Companion.namespace(), "cosmetics/" + category + "/index.json");
            Resource resource = mc.getResourceManager().getResource(id).orElse(null);
            if (resource == null) {
                return names;
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
                com.google.gson.JsonArray arr = JsonParser.parseReader(reader).getAsJsonArray();
                for (com.google.gson.JsonElement el : arr) {
                    if (el.isJsonPrimitive()) {
                        names.add(el.getAsString());
                    }
                }
            }
        } catch (Exception ignored) {
        }
        return names;
    }

    /** Генерирует index.json для всех категорий (вызывать вручную при добавлении косметик). */
    public static void main(String[] args) throws Exception {
        java.nio.file.Path root = java.nio.file.Path.of("src/main/resources/assets/kimiko/cosmetics");
        for (String category : CATEGORIES) {
            List<String> names = new ArrayList<>();
            java.nio.file.Path dir = root.resolve(category);
            if (!java.nio.file.Files.isDirectory(dir)) {
                continue;
            }
            try (var stream = java.nio.file.Files.list(dir)) {
                stream.filter(java.nio.file.Files::isDirectory)
                        .map(p -> p.getFileName().toString())
                        .sorted(String.CASE_INSENSITIVE_ORDER)
                        .forEach(names::add);
            }
            java.nio.file.Path index = root.resolve(category + "/index.json");
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < names.size(); ++i) {
                if (i > 0) {
                    sb.append(',');
                }
                sb.append('"').append(names.get(i)).append('"');
            }
            sb.append(']');
            java.nio.file.Files.writeString(index, sb.toString(), StandardCharsets.UTF_8);
            System.out.println(category + ": " + names.size());
        }
    }
}
