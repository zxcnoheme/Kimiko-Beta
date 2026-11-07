package rtx.kimiko.api.modules.impl.Visuals.cosmetics;

import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.Kimiko;

import java.util.EnumMap;
import java.util.Map;

/**
 * Реестр 3D-превью косметик: лениво парсит модель и текстуру каждой косметики
 * (Bedrock geo.json, Blockbench bbmodel или рукотворная геометрия).
 */
public final class CosmeticPreviews {
    private static final Map<CosmeticType, PreviewModel> CACHE = new EnumMap<>(CosmeticType.class);

    private CosmeticPreviews() {
    }

    @Nullable
    public static PreviewModel model(CosmeticType type) {
        if (type == null) {
            return null;
        }
        if (CACHE.containsKey(type)) {
            return CACHE.get(type);
        }
        PreviewModel built = build(type);
        CACHE.put(type, built); // null тоже кэшируем — не повторяем неудачные загрузки
        return built;
    }

    /** Резолвит id плитки: встроенный CosmeticType или внешний "ext:&lt;категория&gt;/&lt;имя&gt;". */
    @Nullable
    public static PreviewModel byId(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        if (id.startsWith("ext:")) {
            ExternalCosmetic ext = ExternalCosmetic.Library.byId(id);
            return ext != null ? ext.preview() : null;
        }
        return model(CosmeticType.byId(id));
    }

    /** Сброс кэша (например, после смены ресурс-паков). */
    public static void invalidate() {
        CACHE.clear();
    }

    @Nullable
    private static PreviewModel build(CosmeticType type) {
        switch (type) {
            case WINGS_ROYAL: {
                return PreviewModel.parseGeoJson(id("models/wings/royal_mechanical_wings.geo.json"),
                        id("textures/entity/wings/royal_mechanical_wings.png"), 64.0f, 64.0f);
            }
            case WINGS_SERAPH: {
                return PreviewModel.parseGeoJson(id("models/wings/seraph_eye_wings.geo.json"),
                        id("textures/entity/wings/seraph_eye_wings.png"), 128.0f, 128.0f);
            }
            case WINGS_KAGUNE: {
                return PreviewModel.parseGeoJson(id("models/kagune/kaneki_kagune.geo.json"),
                        id("textures/entity/kagune/kaneki_kagune.png"), 64.0f, 64.0f);
            }
            case WINGS_FEATHERED: {
                return feathered();
            }
            case HEAD_CROWN: {
                // текстура короны — вертикальная лента из 80 кадров (64x5120), v-uv в пикселях ленты
                PreviewModel crown = PreviewModel.parseBbModel(id("models/crown/royal_square_crown.bbmodel"),
                        id("textures/entity/crown/royal_square_crown.png"), 64.0f, 64.0f);
                if (crown != null) {
                    crown.texHeight = 5120.0f;
                }
                return crown;
            }
            case HEAD_HALO: {
                return PreviewModel.parseBbModel(id("models/halo/golden_shard_halo.bbmodel"),
                        id("textures/entity/halo/golden_shard_halo.png"), 16.0f, 16.0f);
            }
            case HEAD_HAT: {
                return hat();
            }
            case SHOULDER_GOAT: {
                return PreviewModel.parseGeoJson(id("models/goat/shoulder_goat_pet.geo.json"),
                        id("textures/entity/goat/shoulder_goat_pet.png"), 64.0f, 64.0f);
            }
            case PET_FROG: {
                return PreviewModel.parseGeoJson(id("geckolib/models/frog/custom_pet.geo.json"),
                        id("textures/entity/frog/custom_pet.png"), 128.0f, 128.0f);
            }
            case PET_ROBOT: {
                return PreviewModel.parseGeoJson(id("geckolib/models/robot/robot.geo.json"),
                        id("textures/entity/robot/type_1.png"), 64.0f, 64.0f);
            }
            case PET_OWL: {
                return PreviewModel.parseGeoJson(id("geckolib/models/owl/owl_jump_rope.geo.json"),
                        id("textures/entity/owl/owl_jump_rope.png"), 64.0f, 64.0f);
            }
            case PET_CHEKUSHKA: {
                return PreviewModel.parseGeoJson(id("geckolib/models/chekushka/chekushka_pet.geo.json"),
                        id("textures/entity/chekushka/chekushka_pet.png"), 512.0f, 512.0f);
            }
            case PET_GOAT: {
                return PreviewModel.parseGeoJson(id("geckolib/models/goat/bigear_goat_pet.geo.json"),
                        id("textures/entity/goat/bigear_goat_pet.png"), 256.0f, 256.0f);
            }
            case PET_NIGHTMARE: {
                return PreviewModel.parseGeoJson(id("geckolib/models/nightmare_bb/nightmare_bb.geo.json"),
                        id("textures/entity/pet/nightmare_bb_body.png"), 16.0f, 16.0f);
            }
            case PET_UFO: {
                return PreviewModel.parseGeoJson(id("geckolib/models/ufo/ufo_pet.geo.json"),
                        id("textures/entity/ufo/ufo_pet.png"), 128.0f, 128.0f);
            }
            default: {
                return null;
            }
        }
    }

    /** Шляпа: 4 куба из FlugerHatModel, приведённые к y-вверх координатам. */
    private static PreviewModel hat() {
        PreviewModel m = new PreviewModel(id("textures/entity/hat/fluger_hat.png"), 32.0f, 32.0f);
        // поля тульи (поля + стенки), y_down -4..0 -> y_up 0..4
        m.addCube(-5.0f, 0.0f, -5.0f, 5.0f, 4.0f, 5.0f,
                10.0f, 16.0f, 20.0f, 20.0f,   // north
                20.0f, 16.0f, 10.0f, 20.0f,   // south
                10.0f, 20.0f, 28.0f, 0.0f,    // east (v инвертирован в исходнике: v1=20..v2=0)
                0.0f, 18.0f, 4.0f, 28.0f,     // west
                10.0f, 0.0f, 0.0f, 10.0f,     // up
                20.0f, 0.0f, 10.0f, 10.0f);   // down
        // нижняя кайма
        m.addCube(-5.2f, -0.2f, -5.2f, 5.2f, 2.7f, 5.2f,
                10.0f, 17.5f, 20.0f, 20.0f,
                20.0f, 17.5f, 10.0f, 20.0f,
                10.0f, 20.0f, 28.0f, 1.5f,
                1.5f, 18.0f, 4.0f, 28.0f,
                0.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 20.0f, 10.0f, 20.0f);
        // верх тульи (в исходнике наклонён на 12.5° — для превью без наклона)
        m.addCube(-4.0f, 3.0f, -3.5f, 4.0f, 7.0f, 4.5f,
                18.0f, 12.0f, 26.0f, 16.0f,
                26.0f, 12.0f, 18.0f, 16.0f,
                18.0f, 16.0f, 20.0f, 20.0f,
                20.0f, 24.0f, 12.0f, 20.0f,
                24.0f, 18.0f, 0.0f, 10.0f,
                0.0f, 0.0f, 0.0f, 0.0f);
        // граница тульи
        m.addCube(-3.0f, 7.0f, -2.5f, 3.0f, 8.0f, 3.5f,
                12.5f, 18.0f, 18.5f, 19.0f,
                18.5f, 18.0f, 12.5f, 19.0f,
                12.5f, 19.0f, 27.0f, 21.5f,
                21.0f, 22.5f, 18.5f, 21.0f,
                12.5f, 22.0f, 6.0f, 20.0f,
                0.0f, 26.0f, 12.0f, 26.0f);
        m.updateBoundsManual();
        return m;
    }

    /** Пернатые крылья: стилизованный силуэт из плоских квадов (белая текстура). */
    private static PreviewModel feathered() {
        PreviewModel m = new PreviewModel(id("images/white.png"), 8.0f, 8.0f);
        // правое крыло: три пера-полосы
        float[][] right1 = {{0.0f, 14.0f, 1.0f}, {7.0f, 16.0f, 2.0f}, {9.0f, 4.0f, 3.0f}, {2.0f, 2.0f, 2.0f}};
        float[][] right2 = {{2.0f, 6.0f, 2.5f}, {9.0f, 8.0f, 3.5f}, {10.0f, -2.0f, 4.0f}, {3.0f, -4.0f, 3.0f}};
        float[][] left1 = {{0.0f, 14.0f, 1.0f}, {-7.0f, 16.0f, 2.0f}, {-9.0f, 4.0f, 3.0f}, {-2.0f, 2.0f, 2.0f}};
        float[][] left2 = {{-2.0f, 6.0f, 2.5f}, {-9.0f, 8.0f, 3.5f}, {-10.0f, -2.0f, 4.0f}, {-3.0f, -4.0f, 3.0f}};
        m.addFlatQuad(right1, 0.0f, 0.0f, 8.0f, 8.0f, 0.0f, 0.35f, -0.94f);
        m.addFlatQuad(right2, 0.0f, 0.0f, 8.0f, 8.0f, 0.0f, 0.35f, -0.94f);
        m.addFlatQuad(left1, 0.0f, 0.0f, 8.0f, 8.0f, 0.0f, 0.35f, -0.94f);
        m.addFlatQuad(left2, 0.0f, 0.0f, 8.0f, 8.0f, 0.0f, 0.35f, -0.94f);
        m.updateBoundsManual();
        return m;
    }

    private static Identifier id(String path) {
        return Identifier.of(Kimiko.Companion.namespace(), path);
    }
}
