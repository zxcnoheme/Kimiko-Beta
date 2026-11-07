package rtx.kimiko.api.modules.impl.Visuals.cosmetics;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Внешняя косметика из папки run/kimiko/cosmetics/&lt;папка&gt;/model.json
 * (формат SpaceVisuals: метаданные + base64-текстура + Bedrock geometry + animation).
 */
public final class ExternalCosmetic {
    /** Слот крепления на игроке. */
    /** Слот крепления на игроке. */
    public enum Slot {
        BACK, HEAD, PET, BODY, SHOULDER
    }

    /** true, если логическая категория входит в перечисленные. */
    public boolean inCat(String... categories) {
        for (String c : categories) {
            if (this.resourceCategory.equals(c)) {
                return true;
            }
        }
        return false;
    }

    public final String folder;
    public final String title;
    public final Slot slot;
    /** Адрес в ресурсах: "&lt;категория&gt;/&lt;имя&gt;". */
    public final String resourceCategory;
    private final String keyVal;
    public final BedrockModel model;
    public final float x;
    public final float y;
    public final float z;
    public final float scale;
    public final float yaw;
    public final float pitch;
    public final float roll;
    public final float previewY;
    public final float previewScale;
    @Nullable
    public final BedrockModel.Anim walkAnim;
    @Nullable
    public final BedrockModel.Anim idleAnim;
    @Nullable
    public final BedrockModel.Anim guiAnim;
    /** Смещение якоря в блоках: компенсация разницы конвенций авторинга. */
    public final float anchorYOffset;
    private final String textureData;
    private Identifier texture;
    private PreviewModel preview;

    private ExternalCosmetic(String folder, JsonObject meta, BedrockModel model, String textureData, Slot slotOverride) {
        this(folder, folder, meta, model, textureData, slotOverride, folder);
    }

    /** Полный конструктор: category/name — адрес в ресурсах, slotOverride — слот крепления, rawCategory — папка ресурса. */
    public ExternalCosmetic(String category, String name, JsonObject meta, BedrockModel model,
                            String textureData, Slot slotOverride, String rawCategory) {
        this.folder = category + "/" + name;
        this.keyVal = category + "/" + name;
        this.textureData = textureData;
        this.model = model;
        String rawTitle = str(meta, "name", name);
        this.title = prettify(rawTitle);
        int cat = (int) num(meta, "category", 1);
        float pos = num(meta, "pos", 1);
        if (slotOverride != null) {
            this.slot = slotOverride;
        } else if (cat == 3 || pos == 2) {
            this.slot = Slot.HEAD;
        } else if (cat == 2) {
            this.slot = Slot.PET;
        } else {
            this.slot = Slot.BACK;
        }
        this.resourceCategory = category;
        this.x = num(meta, "x", 0.0f);
        this.y = num(meta, "y", 0.0f);
        this.z = num(meta, "z", 0.0f);
        this.scale = num(meta, "scale", 1.0f);
        this.yaw = num(meta, "yaw", 0.0f);
        this.pitch = num(meta, "pitch", 0.0f);
        this.roll = num(meta, "roll", 0.0f);
        this.previewY = num(meta, "previewY", 0.0f);
        this.previewScale = num(meta, "previewScale", 1.0f);
        if (model != null) {
            this.walkAnim = model.findAnim("walk");
            this.idleAnim = model.findAnim("idle");
            this.guiAnim = model.findAnim("gui");
        } else {
            this.walkAnim = null;
            this.idleAnim = null;
            this.guiAnim = null;
        }
        this.anchorYOffset = computeAnchorYOffset(category, rawCategory);
    }

    /**
     * Якорное смещение (блоки). Геометрия в model.json — абсолютные координаты игрока
     * (ступни 0, Y-up, px); якорный фрейм после applyTransform сидит на пивоте части
     * (голова 24px, тело 12px), Y-down. Essential — чистый риг без меты:
     * смещение = −пивот/16. SpaceVisuals — мета уже tune'нута под их пайплайн.
     */
    private static float computeAnchorYOffset(String category, String rawCategory) {
        if (rawCategory.startsWith("essential_")) {
            return switch (category) {
                case "hat", "head", "face", "ears", "accessory" -> -1.5f;  // голова: −24px
                default -> -0.75f;                                          // тело: −12px
            };
        }
        return "hat".equals(category) ? 0.15f : -0.3f; // spacevisuals
    }

    public String key() {
        return this.keyVal;
    }

    /** Анимация для состояния игрока; фолбэк — любая имеющаяся. */
    @Nullable
    public BedrockModel.Anim animFor(boolean moving) {
        BedrockModel.Anim anim = moving ? this.walkAnim : this.idleAnim;
        if (anim == null) {
            anim = this.guiAnim;
        }
        if (anim == null && this.model != null && !this.model.animations.isEmpty()) {
            anim = this.model.animations.values().iterator().next();
        }
        return anim;
    }

    @Nullable
    public Identifier texture() {
        if (this.texture == null && this.textureData != null && !this.textureData.isEmpty()) {
            this.texture = CosmeticLibrary.registerBase64("cosmetic_ext/" + this.folder, this.textureData);
        }
        return this.texture;
    }

    @Nullable
    public synchronized PreviewModel preview() {
        if (this.preview == null && this.model != null && this.texture() != null) {
            this.preview = PreviewModel.fromBedrock(this.model, this.texture(),
                    this.previewY * 16.0f, this.previewScale);
        }
        return this.preview;
    }

    /** "pulse_Dark Wings" -> "Dark Wings"; "bro9i_Trident Wings" -> "Trident Wings". */
    private static String prettify(String name) {
        int us = name.indexOf('_');
        if (us > 0 && us < name.length() - 1 && name.indexOf(' ', us) > us) {
            return name.substring(us + 1).trim();
        }
        return name.trim();
    }

    private static float num(JsonObject obj, String key, float fallback) {
        try {
            if (obj == null) {
                return fallback;
            }
            com.google.gson.JsonElement el = obj.get(key);
            if (el == null) {
                return fallback;
            }
            if (el.isJsonPrimitive()) {
                return el.getAsFloat();
            }
            if (el.isJsonObject()) {
                // некоторые форматы кладут число в объект
                return num(el.getAsJsonObject(), "data", fallback);
            }
        } catch (Exception ignored) {
        }
        return fallback;
    }

    private static String str(JsonObject obj, String key, String fallback) {
        try {
            com.google.gson.JsonElement el = obj.get(key);
            return el != null && el.isJsonPrimitive() ? el.getAsString() : fallback;
        } catch (Exception e) {
            return fallback;
        }
    }

    /**
     * Устаревшая папка run/kimiko/cosmetics больше не используется — косметики вшиты в jar
     * (см. BundledCosmetics). Оставлен только реестр совместимости.
     */
    public static final class Library {
        private static List<ExternalCosmetic> ALL = new ArrayList<>();

        private Library() {
        }

        public static synchronized List<ExternalCosmetic> all() {
            return BundledCosmetics.all();
        }

        @Nullable
        public static ExternalCosmetic byId(String externalId) {
            return BundledCosmetics.byKey(externalId.startsWith("ext:") ? externalId.substring(4) : externalId);
        }
    }
}
