/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.jdk7.AutoCloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.StringCompanionObject
 *  kotlin.text.Regex
 *  kotlin.text.StringsKt
 *  net.fabricmc.loader.api.FabricLoader
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.modules.post.handshader;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J\u0013\u0010\n\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u0003J\u0019\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0010\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0011J'\u0010\u0014\u001a\u00020\u00132\b\u0010\u000f\u001a\u0004\u0018\u00010\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0016\u001a\u00020\u00132\b\u0010\u000f\u001a\u0004\u0018\u00010\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u0018\u001a\u00020\u00132\b\u0010\u000f\u001a\u0004\u0018\u00010\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0018\u0010\u0017J\u001d\u0010\u001a\u001a\u00020\f2\b\u0010\u0019\u001a\u0004\u0018\u00010\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001a\u0010\u0011J\u001d\u0010\u001c\u001a\u00020\f2\b\u0010\u001b\u001a\u0004\u0018\u00010\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001c\u0010\u0011J\u0013\u0010\u001d\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001f\u0010\u001eJ\u001b\u0010 \u001a\u0004\u0018\u00010\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\fH\u0002\u00a2\u0006\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\f8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\f8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b$\u0010#R\u0014\u0010&\u001a\u00020%8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0014\u0010)\u001a\u00020(8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010#\u00a8\u0006,"}, d2={"Lrtx/kimiko/utils/render/modules/post/handshader/HandShaders;", "", "<init>", "()V", "Ljava/nio/file/Path;", "Lkotlin/jvm/JvmStatic;", "folder", "()Ljava/nio/file/Path;", "", "ensureFolder", "ensureExample", "", "", "list", "()Ljava/util/List;", "name", "load", "(Ljava/lang/String;)Ljava/lang/String;", "code", "", "save", "(Ljava/lang/String;Ljava/lang/String;)Z", "delete", "(Ljava/lang/String;)Z", "exists", "raw", "sanitizeName", "base", "uniqueName", "template", "()Ljava/lang/String;", "documentation", "file", "(Ljava/lang/String;)Ljava/nio/file/Path;", "EXTENSION", "Ljava/lang/String;", "EXAMPLE_NAME", "", "MAX_LISTED", "I", "", "MAX_FILE_BYTES", "J", "EXAMPLE_MARKER", "rtx.kimiko:kimiko"})
public final class HandShaders {
    @NotNull
    public static final HandShaders INSTANCE = new HandShaders();
    @NotNull
    public static final String EXTENSION = ".glsl";
    @NotNull
    public static final String EXAMPLE_NAME = "Пример - Волна по руке";
    private static final int MAX_LISTED = 128;
    private static final long MAX_FILE_BYTES = 262144L;
    @NotNull
    private static final String EXAMPLE_MARKER = ".example2";

    private HandShaders() {
    }

    @JvmStatic
    @NotNull
    public static final Path folder() {
        Path path = FabricLoader.getInstance().getGameDir().resolve("kimiko").resolve("handshaders");
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"resolve(...)");
        return path;
    }

    @JvmStatic
    public static final void ensureFolder() {
        try {
            Files.createDirectories(HandShaders.folder(), new FileAttribute[0]);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @JvmStatic
    public static final void ensureExample() {
        Path marker = HandShaders.folder().resolve(EXAMPLE_MARKER);
        try {
            if (Files.exists(marker, new LinkOption[0])) {
                return;
            }
        }
        catch (Exception ignored) {
            return;
        }
        if (!HandShaders.exists(EXAMPLE_NAME)) {
            HandShaders.save(EXAMPLE_NAME, HandShaders.template());
        }
        try {
            HandShaders.ensureFolder();
            Files.writeString(marker, (CharSequence)"", StandardCharsets.UTF_8, new OpenOption[0]);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    @NotNull
    public static final List<String> list() {
        HandShaders.ensureExample();
        ArrayList<String> names = new ArrayList<>();
        Path folder = HandShaders.folder();
        if (!Files.isDirectory(folder, new LinkOption[0])) {
            return names;
        }
        try (Stream<Path> stream = Files.list(folder)) {
            stream.filter(p -> Files.isRegularFile(p, new LinkOption[0]))
                  .map(p -> p.getFileName().toString())
                  .filter(n -> n.toLowerCase(Locale.ROOT).endsWith(EXTENSION))
                  .map(n -> n.substring(0, n.length() - EXTENSION.length()))
                  .sorted(String.CASE_INSENSITIVE_ORDER)
                  .limit(128L)
                  .forEach(names::add);
        } catch (Exception ignored) {
        }
        return names;
    }

    @JvmStatic
    @Nullable
    public static final String load(@Nullable String name) {
        String string;
        Path path = INSTANCE.file(name);
        if (path == null) {
            return null;
        }
        Path file = path;
        try {
            string = !Files.isRegularFile(file, new LinkOption[0]) || Files.size(file) > 262144L ? null : Files.readString(file, StandardCharsets.UTF_8);
        }
        catch (Exception ex) {
            string = null;
        }
        return string;
    }

    @JvmStatic
    public static final boolean save(@Nullable String name, @Nullable String code) {
        boolean bl;
        Path path = INSTANCE.file(name);
        if (path == null) {
            return false;
        }
        Path file = path;
        try {
            HandShaders.ensureFolder();
            String string = code;
            if (string == null) {
                string = "";
            }
            Files.writeString(file, (CharSequence)string, StandardCharsets.UTF_8, new OpenOption[0]);
            bl = true;
        }
        catch (Exception ex) {
            bl = false;
        }
        return bl;
    }

    @JvmStatic
    public static final boolean delete(@Nullable String name) {
        boolean bl;
        Path path = INSTANCE.file(name);
        if (path == null) {
            return false;
        }
        Path file = path;
        try {
            bl = Files.deleteIfExists(file);
        }
        catch (Exception ex) {
            bl = false;
        }
        return bl;
    }

    @JvmStatic
    public static final boolean exists(@Nullable String name) {
        Path file = INSTANCE.file(name);
        return file != null && Files.isRegularFile(file, new LinkOption[0]);
    }

    @JvmStatic
    @NotNull
    public static final String sanitizeName(@Nullable String raw) {
        if (raw == null) {
            return "";
        }
        CharSequence charSequence = ((Object)StringsKt.trim((CharSequence)raw)).toString();
        Regex regex = new Regex("[\\\\/:*?\"<>|]");
        String string = "_";
        String name = regex.replace(charSequence, string);
        charSequence = name;
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string2 = ((String)charSequence).toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
        if (String.valueOf(string2).endsWith(EXTENSION)) {
            String string3 = name.substring(0, name.length() - 5);
            Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"substring(...)");
            name = ((Object)StringsKt.trim((CharSequence)string3)).toString();
        }
        if (name.length() > 40) {
            String string4 = name.substring(0, 40);
            Intrinsics.checkNotNullExpressionValue((Object)string4, (String)"substring(...)");
            name = ((Object)StringsKt.trim((CharSequence)string4)).toString();
        }
        return name;
    }

    @JvmStatic
    @NotNull
    public static final String uniqueName(@Nullable String base) {
        String name = HandShaders.sanitizeName(base);
        if (((CharSequence)name).length() == 0) {
            name = "shader";
        }
        if (!HandShaders.exists(name)) {
            return name;
        }
        for (int i = 2; i < 1000; ++i) {
            String candidate = name + "_" + i;
            if (HandShaders.exists(candidate)) continue;
            return candidate;
        }
        return name + "_" + System.nanoTime() % (long)100000;
    }

    @JvmStatic
    @NotNull
    public static final String template() {
        Object[] objectArray = new String[]{"// Кастомный шейдер рук Kimiko", "// API как на Shadertoy: iTime, iResolution, mainImage(fragColor, fragCoord)", "// fragCoord - экранный, texCoord - 0..1 по экрану.", "//", "// Функции клиента:", "//   handMask()      - силуэт руки 0..1 (0 = мимо руки)", "//   handAlbedo()    - цвет руки, как её нарисовала игра (vec3)", "//   handColor()     - то же самое, но vec4 с альфой", "//   handUv()        - координаты, привязанные к камере: НЕ дрожат при повороте", "//   handViewPos()   - позиция пикселя руки в пространстве камеры (vec3, блоки)", "//   handBoxUv()     - 0..1 по экранному боксу руки (тянется при взмахе)", "//   handDepth()     - 0..1 глубина по силуэту (ближе к 1 - ближе к камере)", "//   sceneColor(uv)  - размытая сцена за руками (vec3)", "//   kimikoColor(), kimikoColor2() - цвета клиента", "//", "// Альфа результата = насколько шейдер перекрывает мир.", "// Умножай её на handMask(), иначе зальёшь весь экран.", "", "void mainImage(out vec4 fragColor, in vec2 fragCoord) {", "    float mask = handMask();", "    if (mask <= 0.003) {", "        fragColor = vec4(0.0);", "        return;", "    }", "", "    vec2 uv = handUv();", "    vec3 base = handAlbedo();", "", "    float wave = 0.5 + 0.5 * sin(uv.y * 18.0 - iTime * 3.0);", "    vec3 col = mix(base, kimikoColor(), 0.65);", "    col += kimikoColor2() * wave * 0.35;", "", "    fragColor = vec4(col, mask);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    @JvmStatic
    @NotNull
    public static final String documentation() {
        Object[] objectArray = new String[]{"Напиши GLSL-шейдер рук для майнкрафт-клиента Kimiko (Minecraft 1.21.11, OpenGL 3.2, GLSL 150).", "", "ФОРМАТ ОТВЕТА", "Верни ТОЛЬКО код шейдера, без markdown-заборов и пояснений вокруг.", "", "ЖЁСТКИЕ ПРАВИЛА", "1. Точка входа ровно одна: void mainImage(out vec4 fragColor, in vec2 fragCoord).", "2. НЕ пиши void main() — обёртку добавляет клиент, свой main() отклоняется валидатором.", "3. НЕ пиши #version, uniform, in/out, layout — всё это уже объявлено в обёртке.", "4. Это GLSL 150 (не GLSL ES, не HLSL): нет texture2D, нет textureLod без ext,", "   нет switch по float, все литералы float пиши с точкой (1.0, а не 1).", "5. Массивы фиксированной длины, циклы с константной границей.", "6. Пиши только процедурную математику: своих текстур и семплеров подключить нельзя.", "", "ЧТО ЭТО РИСУЕТ", "Шейдер выполняется одним фулскрин-пасом ПОВЕРХ уже отрисованных рук игрока от первого лица.", "Игровые руки лежат в отдельной текстуре, к ним есть доступ через функции ниже.", "Мир (сцена) уже нарисован под ними. Результат шейдера композитится на мир по альфе.", "", "СИСТЕМА КООРДИНАТ", "fragCoord — экранные пиксели, как на Shadertoy (0,0 в левом нижнем углу).", "iResolution.xy — размер экрана. Нормализованные координаты: vec2 uv = fragCoord / iResolution.xy.", "", "ДОСТУПНОЕ API", "  iTime            float, секунды", "  iResolution      vec3, размер экрана", "  iFrame, iMouse, iTimeDelta — заглушки для совместимости с Shadertoy", "  handMask()       float 0..1 — силуэт руки в текущем пикселе (0 = мимо руки)", "  handMaskAt(uv)   float — то же самое в произвольной точке экрана 0..1", "  handColor()      vec4 — цвет руки, как её нарисовала игра (rgb + альфа силуэта)", "  handColorAt(uv)  vec4 — то же в произвольной точке", "  handAlbedo()     vec3 — только rgb цвета руки", "  handUv()         vec2 — координаты, привязанные к пространству камеры;", "                   не дрожат при повороте головы, узор стоит на руке. ПО УМОЛЧАНИЮ БЕРИ ИХ", "  handViewPos()    vec3 — позиция пикселя руки в пространстве камеры, в блоках", "                   (x вправо, y вверх, z вперёд отрицательный). Основа для 3D-узоров", "  handBoxUv()      vec2 0..1 — по экранному боксу силуэта. Растягивается и плывёт", "                   при взмахе и смене предмета: бери только если нужна именно развёртка", "  handDepth()      float 0..1 — глубина по силуэту (больше = ближе к камере)", "  sceneColor(uv)   vec3 — размытая сцена (мир) за руками, uv 0..1 по экрану", "  kimikoColor()    vec3 — основной цвет из настроек клиента", "  kimikoColor2()   vec3 — второй цвет из настроек клиента", "", "АЛЬФА — САМОЕ ВАЖНОЕ", "fragColor.a = насколько шейдер перекрывает мир в этом пикселе.", "Всегда домножай альфу на handMask(), иначе зальёшь весь экран.", "Ранний выход обязателен для производительности:", "    float mask = handMask();", "    if (mask <= 0.003) { fragColor = vec4(0.0); return; }", "Свечение за пределами силуэта делай через handMaskAt() с офсетами (несколько выборок),", "а не через альфу на весь экран.", "rgb можно выводить ярче 1.0 (до 8.0) — получится пересвет/бум-эффект.", "", "СТИЛЬ РЕЗУЛЬТАТА", "Анимируй по iTime, но без эпилепсии. Не делай шейдер полностью непрозрачным белым:", "форма руки должна читаться. Опирайся на handAlbedo() для тени/объёма", "и на handUv() для узоров вдоль руки.", "", "ШАБЛОН", "void mainImage(out vec4 fragColor, in vec2 fragCoord) {", "    float mask = handMask();", "    if (mask <= 0.003) { fragColor = vec4(0.0); return; }", "    vec2 uv = handUv();", "    vec3 col = mix(handAlbedo(), kimikoColor(), 0.6);", "    col += kimikoColor2() * (0.5 + 0.5 * sin(uv.y * 18.0 - iTime * 3.0)) * 0.35;", "    fragColor = vec4(col, mask);", "}", "", "ЗАДАЧА", "Сделай на этой основе: <опиши сюда, какие руки ты хочешь>"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final Path file(String name) {
        String clean = HandShaders.sanitizeName(name);
        if (((CharSequence)clean).length() == 0) {
            return null;
        }
        return HandShaders.folder().resolve(clean + EXTENSION);
    }

    private static final boolean list$lambda$0$0(Path it) {
        return Files.isRegularFile(it, new LinkOption[0]);
    }

    private static final boolean list$lambda$0$1(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final String list$lambda$0$2(Path it) {
        return ((Object)it.getFileName()).toString();
    }

    private static final String list$lambda$0$3(Function1 $tmp0, Object p0) {
        return (String)$tmp0.invoke(p0);
    }

    private static final boolean list$lambda$0$4(String it) {
        Intrinsics.checkNotNull((Object)it);
        String string = it;
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string2 = string.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
        return String.valueOf(string2).endsWith(EXTENSION);
    }

    private static final boolean list$lambda$0$5(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final String list$lambda$0$6(String it) {
        Intrinsics.checkNotNull((Object)it);
        String string = it.substring(0, it.length() - 5);
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
        return string;
    }

    private static final String list$lambda$0$7(Function1 $tmp0, Object p0) {
        return (String)$tmp0.invoke(p0);
    }

    private static final Unit list$lambda$0$8(ArrayList $names, String it) {
        $names.add(it);
        return Unit.INSTANCE;
    }

    private static final void list$lambda$0$9(Function1 $tmp0, Object p0) {
        $tmp0.invoke(p0);
    }
}

