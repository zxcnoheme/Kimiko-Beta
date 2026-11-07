package rtx.kimiko.api.modules.impl.Visuals.cosmetics;

/**
 * Каталог косметик, встроенных в клиент.
 * Каждая запись — id (совместим с идентификаторами гардероба сайта), русское название и категория.
 * Новые косметики добавляются сюда + рендер в CosmeticLayer/CosmeticPreviews.
 */
public enum CosmeticType {
    // Категория «Крылья» — рендер со спины
    WINGS_ROYAL("royal", "Королевские", Cat.WINGS),
    WINGS_SERAPH("seraph", "Серафим", Cat.WINGS),
    WINGS_FEATHERED("feathered", "Пернатые", Cat.WINGS),
    WINGS_KAGUNE("kagune", "Кагунэ", Cat.WINGS),

    // Категория «Голова»
    HEAD_CROWN("crown", "Корона", Cat.HEAD),
    HEAD_HALO("halo", "Гало", Cat.HEAD),
    HEAD_HAT("hat", "Шляпа", Cat.HEAD),

    // Категория «Плечо»
    SHOULDER_GOAT("goat", "Козёл", Cat.SHOULDER),

    // Категория «Питомцы» (CustomPet, GeckoLib-модели)
    PET_FROG("frog", "Лягушонок", Cat.PETS),
    PET_ROBOT("robot", "Робот", Cat.PETS),
    PET_OWL("owl", "Сова", Cat.PETS),
    PET_CHEKUSHKA("chekushka", "Чекушка", Cat.PETS),
    PET_GOAT("goat", "Козлик", Cat.PETS),
    PET_NIGHTMARE("nightmare_bb", "Кошмарик", Cat.PETS),
    PET_UFO("ufo", "НЛО", Cat.PETS);

    public enum Cat {
        WINGS("Крылья"), HEAD("Голова"), SHOULDER("Плечо"), PETS("Питомцы");

        public final String title;

        Cat(String title) {
            this.title = title;
        }
    }

    public final String id;
    public final String title;
    public final Cat cat;

    CosmeticType(String id, String title, Cat cat) {
        this.id = id;
        this.title = title;
        this.cat = cat;
    }

    public static CosmeticType byId(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        for (CosmeticType t : values()) {
            if (t.id.equals(id)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Резолв id внутри конкретной категории: у разных косметик могут совпадать id
     * (например "goat" у плечевого козла и питомца), поэтому без категории
     * возвращается первая попавшаяся.
     */
    public static CosmeticType byId(String id, Cat cat) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        for (CosmeticType t : values()) {
            if (t.id.equals(id) && t.cat == cat) {
                return t;
            }
        }
        return byId(id);
    }
}
