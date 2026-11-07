/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.fabricmc.loader.api.FabricLoader
 *  net.minecraft.util.Util
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.mainmenu;

import java.lang.invoke.CallSite;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.ui.mainmenu.MenuBackground;
import rtx.kimiko.api.ui.mainmenu.MenuSettings;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\t\u0010\bJ\u0015\u0010\n\u001a\u0004\u0018\u00010\u0005H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0013\u0010\u0015\u001a\u00020\u0014H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u0011H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0017\u0010\u0003J\u0013\u0010\u0018\u001a\u00020\u0011H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0018\u0010\u0003J\u000f\u0010\u0019\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u0003J\u000f\u0010\u001a\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u0003J\u0017\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u0013J\u000f\u0010\u001c\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u0003J\u0017\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\u0017\u0010\"\u001a\u00020\f2\u0006\u0010!\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\"\u0010#R\u001a\u0010%\u001a\b\u0012\u0004\u0012\u00020\f0$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010(\u001a\u00020'8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010)R \u0010+\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b+\u0010,R$\u0010/\u001a\u0012\u0012\u0004\u0012\u00020\u00050-j\b\u0012\u0004\u0012\u00020\u0005`.8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0016\u00101\u001a\u00020'8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b1\u0010)R\u0016\u00102\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u00103\u00a8\u00064"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuBackgrounds;", "", "<init>", "()V", "", "Lrtx/kimiko/api/ui/mainmenu/MenuBackground;", "Lkotlin/jvm/JvmStatic;", "all", "()Ljava/util/List;", "user", "selected", "()Lrtx/kimiko/api/ui/mainmenu/MenuBackground;", "", "id", "byId", "(Ljava/lang/String;)Lrtx/kimiko/api/ui/mainmenu/MenuBackground;", "background", "", "select", "(Lrtx/kimiko/api/ui/mainmenu/MenuBackground;)V", "Ljava/nio/file/Path;", "folder", "()Ljava/nio/file/Path;", "openFolder", "rescan", "ensure", "registerBuiltIn", "add", "scan", "path", "", "supported", "(Ljava/nio/file/Path;)Z", "name", "stripExtension", "(Ljava/lang/String;)Ljava/lang/String;", "", "EXTENSIONS", "[Ljava/lang/String;", "", "RESCAN_INTERVAL_MS", "J", "Ljava/util/LinkedHashMap;", "REGISTRY", "Ljava/util/LinkedHashMap;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "BUILT_IN", "Ljava/util/ArrayList;", "lastScan", "initialised", "Z", "rtx.kimiko:kimiko"})
public final class MenuBackgrounds {
    @NotNull
    public static final MenuBackgrounds INSTANCE = new MenuBackgrounds();
    @NotNull
    private static final String[] EXTENSIONS;
    private static final long RESCAN_INTERVAL_MS = 2500L;
    @NotNull
    private static final LinkedHashMap<String, MenuBackground> REGISTRY;
    @NotNull
    private static final ArrayList<MenuBackground> BUILT_IN;
    private static long lastScan;
    private static boolean initialised;

    private MenuBackgrounds() {
    }

    @JvmStatic
    @NotNull
    public static final List<MenuBackground> all() {
        INSTANCE.ensure();
        return new ArrayList<MenuBackground>(REGISTRY.values());
    }

    @JvmStatic
    @NotNull
    public static final List<MenuBackground> user() {
        INSTANCE.ensure();
        ArrayList<MenuBackground> list = new ArrayList<MenuBackground>();
        Iterator<MenuBackground> iterator = REGISTRY.values().iterator();
        while (iterator.hasNext()) {
            MenuBackground background = (MenuBackground) (iterator.next());
            if (background.builtIn()) continue;
            list.add(background);
        }
        return list;
    }

    @JvmStatic
    @Nullable
    public static final MenuBackground selected() {
        INSTANCE.ensure();
        MenuBackground background = REGISTRY.get(MenuSettings.backgroundId());
        if (background != null) {
            return background;
        }
        return BUILT_IN.isEmpty() ? null : BUILT_IN.get(0);
    }

    @JvmStatic
    @Nullable
    public static final MenuBackground byId(@Nullable String id) {
        INSTANCE.ensure();
        return (MenuBackground)((Map)REGISTRY).get(id);
    }

    @JvmStatic
    public static final void select(@Nullable MenuBackground background) {
        if (background == null) {
            return;
        }
        MenuSettings.setBackgroundId(background.id());
        MenuSettings.save();
    }

    @JvmStatic
    @NotNull
    public static final Path folder() {
        Path path = FabricLoader.getInstance().getGameDir().resolve("kimiko").resolve("backgrounds");
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"resolve(...)");
        return path;
    }

    @JvmStatic
    public static final void openFolder() {
        try {
            Path path = MenuBackgrounds.folder();
            Files.createDirectories(path, new FileAttribute[0]);
            Util.getOperatingSystem().open(path);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    @JvmStatic
    public static final void rescan() {
        lastScan = 0L;
        INSTANCE.scan();
    }

    /** Запускает фоновую предзагрузку превью всех фонов (асинхронно, без фризов). */
    @JvmStatic
    public static final void preload() {
        INSTANCE.ensure();
        Iterator<MenuBackground> iterator = REGISTRY.values().iterator();
        while (iterator.hasNext()) {
            MenuBackground background = (MenuBackground) (iterator.next());
            if (background.procedural() || background.failed() || background.ready()) continue;
            background.preview();
        }
    }

    private final void ensure() {
        if (!initialised) {
            initialised = true;
            this.registerBuiltIn();
        }
        this.scan();
    }

    private final void registerBuiltIn() {
        this.add(new MenuBackground("storm", "Молния", MenuBackground.Kind.GRADIENT, "style:storm", true));
        this.add(new MenuBackground("orbit", "Орбиты", MenuBackground.Kind.GRADIENT, "style:orbit", true));
        this.add(new MenuBackground("portal", "Портал", MenuBackground.Kind.GRADIENT, "style:portal", true));
        this.add(new MenuBackground("rain", "Дождь", MenuBackground.Kind.GRADIENT, "style:rain", true));
        this.add(new MenuBackground("warp", "Скорость", MenuBackground.Kind.GRADIENT, "style:warp", true));
        this.add(new MenuBackground("fluid", "Жидкость", MenuBackground.Kind.GRADIENT, "style:fluid", true));
        this.add(new MenuBackground("grid", "Сетка", MenuBackground.Kind.GRADIENT, "style:grid", true));
        this.add(new MenuBackground("bubbles", "Пузыри", MenuBackground.Kind.GRADIENT, "style:bubbles", true));
        this.add(new MenuBackground("streaks", "Кометы", MenuBackground.Kind.GRADIENT, "style:streaks", true));
        this.add(new MenuBackground("ripples", "Рябь", MenuBackground.Kind.GRADIENT, "style:ripples", true));
        this.add(new MenuBackground("nebula", "Туманность", MenuBackground.Kind.GRADIENT, "style:nebula", true));
        this.add(new MenuBackground("matrix", "Код", MenuBackground.Kind.GRADIENT, "style:matrix", true));
        this.add(new MenuBackground("fireflies", "Светлячки", MenuBackground.Kind.GRADIENT, "style:fireflies", true));
        this.add(new MenuBackground("abyss", "Бездна", MenuBackground.Kind.GRADIENT, "style:abyss", true));
        this.add(new MenuBackground("lava", "Магма", MenuBackground.Kind.GRADIENT, "style:lava", true));
        this.add(new MenuBackground("kimiko", "Kimiko", MenuBackground.Kind.IMAGE, "kimiko:textures/menu/menu.png", true));
        this.add(new MenuBackground("aurora", "Аврора", MenuBackground.Kind.AURORA, "", true));
        this.add(new MenuBackground("gradient", "Глубина", MenuBackground.Kind.GRADIENT, "", true));
    }

    private final void add(MenuBackground background) {
        ((Map)REGISTRY).put(background.id(), background);
        if (background.builtIn()) {
            BUILT_IN.add(background);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void scan() {
        long now = System.currentTimeMillis();
        if (now - lastScan < 2500L) {
            return;
        }
        lastScan = now;
        Path folder = MenuBackgrounds.folder();
        List<Path> files = new ArrayList<>();
        if (Files.isDirectory(folder, new LinkOption[0])) {
            try (Stream<Path> stream = Files.list(folder)) {
                stream.filter(Files::isRegularFile)
                      .filter(this::supported)
                      .sorted()
                      .forEach(files::add);
            }
            catch (Throwable ignored) {
                return;
            }
        }
        List<String> present = new ArrayList<>();
        for (Path file : files) {
            String id = "user:" + file.getFileName();
            present.add(id);
            if (REGISTRY.containsKey(id)) continue;
            String name = this.translateName(this.stripExtension(file.getFileName().toString()));
            REGISTRY.put(id, new MenuBackground(id, name, MenuBackground.Kind.USER, file.toAbsolutePath().toString(), false));
        }
        REGISTRY.entrySet().removeIf(it -> !it.getValue().builtIn() && !present.contains(it.getKey()));
    }

    private final boolean supported(Path path) {
        String name = path.getFileName().toString().toLowerCase(Locale.ROOT);
        for (String extension : EXTENSIONS) {
            if (name.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    private final String stripExtension(String name) {
        int dot = name.lastIndexOf('.');
        if (dot <= 0) {
            return name;
        }
        return name.substring(0, dot);
    }

    /** Переводит имя файла фона на русский: точные совпадения + пословный словарь как запасной вариант. */
    private final String translateName(String raw) {
        if (raw == null || raw.isEmpty()) {
            return raw;
        }
        String key = raw.toLowerCase(Locale.ROOT).replaceAll("[\\s_+.]+", "-");
        String full = NAMES_RU.get(key);
        if (full != null) {
            return full;
        }
        StringBuilder sb = new StringBuilder();
        for (String part : key.split("-")) {
            if (part.isEmpty()) {
                continue;
            }
            String ru = WORDS_RU.get(part);
            sb.append(ru == null ? part : ru).append(' ');
        }
        String joined = sb.toString().trim();
        if (joined.isEmpty()) {
            return raw;
        }
        return Character.toUpperCase(joined.charAt(0)) + joined.substring(1);
    }

    private static final Map<String, String> NAMES_RU;
    private static final Map<String, String> WORDS_RU;

    private static void putRu(Map<String, String> map, String key, String value) {
        map.put(key, value);
    }

    static {
        String[] stringArray = new String[]{".png", ".jpg", ".jpeg"};
        EXTENSIONS = stringArray;
        REGISTRY = new LinkedHashMap();
        BUILT_IN = new ArrayList();

        Map<String, String> names = new LinkedHashMap();
        putRu(names, "ancient-blue-hall", "Древний синий зал");
        putRu(names, "astral-forest", "Астральный лес");
        putRu(names, "autumn-lake", "Осеннее озеро");
        putRu(names, "blossom-city", "Цветущий город");
        putRu(names, "blue-cavern", "Синяя пещера");
        putRu(names, "castle-garden", "Сад замка");
        putRu(names, "cave-lanterns", "Пещера фонарей");
        putRu(names, "cavern-wheel", "Колесо в пещере");
        putRu(names, "chain-cavern", "Цепная пещера");
        putRu(names, "cherry-village", "Вишнёвая деревня");
        putRu(names, "crimson-arcade", "Багровая аркада");
        putRu(names, "crystal-tree", "Кристальное дерево");
        putRu(names, "deep-cavern", "Глубокая пещера");
        putRu(names, "ember-cavern", "Тлеющая пещера");
        putRu(names, "enchanted-lake", "Волшебное озеро");
        putRu(names, "fire-temple", "Огненный храм");
        putRu(names, "floating-islands", "Парящие острова");
        putRu(names, "glowing-cavern", "Сияющая пещера");
        putRu(names, "golden-street", "Золотая улица");
        putRu(names, "lantern-gate", "Врата фонарей");
        putRu(names, "launcher-blue-cavern", "Синяя пещера лаунчера");
        putRu(names, "market-arcade", "Рыночная аркада");
        putRu(names, "modern-villa-day", "Современная вилла днём");
        putRu(names, "modern-villa-rain", "Современная вилла в дождь");
        putRu(names, "modern-villa-sunrise", "Современная вилла на рассвете");
        putRu(names, "moonlit-pavilion", "Лунный павильон");
        putRu(names, "mountain-lake", "Горное озеро");
        putRu(names, "nether-portal-hall", "Зал нижнего портала");
        putRu(names, "riverside-house", "Дом у реки");
        putRu(names, "sunrise-pagoda-lake", "Озеро с пагодой на рассвете");
        putRu(names, "sunrise-pagoda", "Пагода на рассвете");
        putRu(names, "sunset-fantasy-village", "Деревня фэнтези на закате");
        putRu(names, "sunset-ruins", "Руины на закате");
        putRu(names, "village-workshop", "Деревенская мастерская");
        putRu(names, "white-city", "Белый город");
        putRu(names, "winter-pagoda", "Зимняя пагода");
        NAMES_RU = names;

        Map<String, String> words = new LinkedHashMap();
        putRu(words, "ancient", "древний");
        putRu(words, "astral", "астральный");
        putRu(words, "autumn", "осенний");
        putRu(words, "blossom", "цветущий");
        putRu(words, "blue", "синий");
        putRu(words, "cave", "пещера");
        putRu(words, "cavern", "пещера");
        putRu(words, "castle", "замок");
        putRu(words, "chain", "цепной");
        putRu(words, "cherry", "вишнёвый");
        putRu(words, "city", "город");
        putRu(words, "crimson", "багровый");
        putRu(words, "crystal", "кристальный");
        putRu(words, "day", "день");
        putRu(words, "deep", "глубокий");
        putRu(words, "ember", "тлеющий");
        putRu(words, "enchanted", "волшебный");
        putRu(words, "fantasy", "фэнтези");
        putRu(words, "fire", "огненный");
        putRu(words, "floating", "парящий");
        putRu(words, "forest", "лес");
        putRu(words, "garden", "сад");
        putRu(words, "gate", "врата");
        putRu(words, "glowing", "сияющий");
        putRu(words, "golden", "золотой");
        putRu(words, "hall", "зал");
        putRu(words, "house", "дом");
        putRu(words, "islands", "острова");
        putRu(words, "lake", "озеро");
        putRu(words, "lantern", "фонарь");
        putRu(words, "lanterns", "фонари");
        putRu(words, "launcher", "лаунчер");
        putRu(words, "market", "рынок");
        putRu(words, "modern", "современный");
        putRu(words, "moonlit", "лунный");
        putRu(words, "mountain", "горный");
        putRu(words, "nether", "нижний");
        putRu(words, "night", "ночь");
        putRu(words, "pagoda", "пагода");
        putRu(words, "pavilion", "павильон");
        putRu(words, "portal", "портал");
        putRu(words, "rain", "дождь");
        putRu(words, "river", "река");
        putRu(words, "riverside", "приречной");
        putRu(words, "ruins", "руины");
        putRu(words, "sea", "море");
        putRu(words, "snowy", "снежный");
        putRu(words, "street", "улица");
        putRu(words, "sunrise", "рассвет");
        putRu(words, "sunset", "закат");
        putRu(words, "temple", "храм");
        putRu(words, "tree", "дерево");
        putRu(words, "villa", "вилла");
        putRu(words, "village", "деревня");
        putRu(words, "white", "белый");
        putRu(words, "winter", "зимний");
        putRu(words, "workshop", "мастерская");
        WORDS_RU = words;
    }
}

