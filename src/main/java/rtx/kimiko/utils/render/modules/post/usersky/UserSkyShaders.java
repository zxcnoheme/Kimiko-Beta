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
package rtx.kimiko.utils.render.modules.post.usersky;

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

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J\u0013\u0010\n\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u0003J\u0019\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0010\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0011J'\u0010\u0014\u001a\u00020\u00132\b\u0010\u000f\u001a\u0004\u0018\u00010\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0016\u001a\u00020\u00132\b\u0010\u000f\u001a\u0004\u0018\u00010\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u0018\u001a\u00020\u00132\b\u0010\u000f\u001a\u0004\u0018\u00010\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0018\u0010\u0017J\u001d\u0010\u001a\u001a\u00020\f2\b\u0010\u0019\u001a\u0004\u0018\u00010\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001a\u0010\u0011J\u001d\u0010\u001c\u001a\u00020\f2\b\u0010\u001b\u001a\u0004\u0018\u00010\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001c\u0010\u0011J\u0013\u0010\u001d\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001f\u0010\u001eJ\u001b\u0010 \u001a\u0004\u0018\u00010\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\fH\u0002\u00a2\u0006\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\f8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b$\u0010#R\u0014\u0010%\u001a\u00020\f8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b%\u0010#R\u0014\u0010'\u001a\u00020&8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010*\u001a\u00020)8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010#\u00a8\u0006-"}, d2={"Lrtx/kimiko/utils/render/modules/post/usersky/UserSkyShaders;", "", "<init>", "()V", "Ljava/nio/file/Path;", "Lkotlin/jvm/JvmStatic;", "folder", "()Ljava/nio/file/Path;", "", "ensureFolder", "ensureExample", "", "", "list", "()Ljava/util/List;", "name", "load", "(Ljava/lang/String;)Ljava/lang/String;", "code", "", "save", "(Ljava/lang/String;Ljava/lang/String;)Z", "delete", "(Ljava/lang/String;)Z", "exists", "raw", "sanitizeName", "base", "uniqueName", "template", "()Ljava/lang/String;", "documentation", "file", "(Ljava/lang/String;)Ljava/nio/file/Path;", "NEWLINE", "Ljava/lang/String;", "EXTENSION", "EXAMPLE_NAME", "", "MAX_LISTED", "I", "", "MAX_FILE_BYTES", "J", "EXAMPLE_CODE", "rtx.kimiko:kimiko"})
public final class UserSkyShaders {
    @NotNull
    public static final UserSkyShaders INSTANCE = new UserSkyShaders();
    @NotNull
    private static final String NEWLINE = "\n";
    @NotNull
    public static final String EXTENSION = ".glsl";
    @NotNull
    public static final String EXAMPLE_NAME = "Пример - Реалистичное небо";
    private static final int MAX_LISTED = 128;
    private static final long MAX_FILE_BYTES = 262144L;
    @NotNull
    private static final String EXAMPLE_CODE = "// Kimiko Realistic Sky\n// API:\n// iTime\n// iResolution\n// skyDirection()\n// kimikoColor()\n// kimikoColor2()\n\nfloat hash(vec2 p)\n{\n    p = fract(p * vec2(234.34, 851.73));\n    p += dot(p, p + 23.45);\n    return fract(p.x * p.y);\n}\n\nfloat noise(vec2 p)\n{\n    vec2 i = floor(p);\n    vec2 f = fract(p);\n\n    float a = hash(i);\n    float b = hash(i + vec2(1.0,0.0));\n    float c = hash(i + vec2(0.0,1.0));\n    float d = hash(i + vec2(1.0,1.0));\n\n    vec2 u = f*f*(3.0-2.0*f);\n\n    return mix(a,b,u.x)\n         + (c-a)*u.y*(1.0-u.x)\n         + (d-b)*u.x*u.y;\n}\n\nfloat fbm(vec2 p)\n{\n    float f = 0.0;\n    float a = 0.5;\n\n    for(int i=0;i<6;i++)\n    {\n        f += a * noise(p);\n        p *= 2.02;\n        a *= 0.5;\n    }\n\n    return f;\n}\n\nvoid mainImage(out vec4 fragColor, in vec2 fragCoord)\n{\n    vec3 dir = normalize(skyDirection());\n\n    float h = clamp(dir.y*0.5+0.5,0.0,1.0);\n\n    vec3 horizon = kimikoColor();\n    vec3 zenith  = kimikoColor2();\n\n    vec3 col = mix(horizon, zenith, pow(h,1.45));\n\n    col += horizon * 0.18 * pow(1.0-h,4.5);\n\n    vec2 uv = dir.xz / max(dir.y + 0.25,0.25);\n\n    uv *= 0.55;\n    uv += vec2(iTime*0.003,-iTime*0.0015);\n\n    float cloud =\n        fbm(uv*2.0)\n        +0.5*fbm(uv*4.0)\n        +0.25*fbm(uv*8.0);\n\n    cloud /= 1.75;\n\n    cloud = smoothstep(0.58,0.82,cloud);\n\n    float fade = smoothstep(-0.05,0.35,dir.y);\n\n    vec3 cloudColor = mix(\n        vec3(0.92,0.93,0.95),\n        vec3(1.0),\n        h\n    );\n\n    col = mix(col, cloudColor, cloud * fade * 0.9);\n\n    vec3 sunDir = normalize(vec3(0.2,0.82,0.45));\n\n    float sun = max(dot(dir,sunDir),0.0);\n\n    col += vec3(1.0,0.88,0.65)\n        * pow(sun,1800.0)\n        * 20.0;\n\n    col += vec3(1.0,0.78,0.48)\n        * pow(sun,60.0)\n        * 0.45;\n\n    float rays = fbm(dir.xz*18.0+iTime*0.02);\n\n    col += vec3(1.0,0.85,0.6)\n        * pow(sun,14.0)\n        * rays\n        * 0.12;\n\n    col += vec3(0.05,0.08,0.12)\n        * pow(1.0-h,8.0);\n\n    col = pow(col, vec3(0.95));\n\n    fragColor = vec4(col,1.0);\n}\n";

    private UserSkyShaders() {
    }

    @JvmStatic
    @NotNull
    public static final Path folder() {
        Path path = FabricLoader.getInstance().getGameDir().resolve("kimiko").resolve("skyshaders");
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"resolve(...)");
        return path;
    }

    @JvmStatic
    public static final void ensureFolder() {
        try {
            Files.createDirectories(UserSkyShaders.folder(), new FileAttribute[0]);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @JvmStatic
    public static final void ensureExample() {
        if (!UserSkyShaders.exists(EXAMPLE_NAME)) {
            UserSkyShaders.save(EXAMPLE_NAME, EXAMPLE_CODE);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    @NotNull
    public static final List<String> list() {
        UserSkyShaders.ensureExample();
        ArrayList<String> names = new ArrayList<>();
        Path folder = UserSkyShaders.folder();
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
            UserSkyShaders.ensureFolder();
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
        String name = UserSkyShaders.sanitizeName(base);
        if (((CharSequence)name).length() == 0) {
            name = "shader";
        }
        if (!UserSkyShaders.exists(name)) {
            return name;
        }
        for (int i = 2; i < 1000; ++i) {
            String candidate = name + "_" + i;
            if (UserSkyShaders.exists(candidate)) continue;
            return candidate;
        }
        return name + "_" + System.nanoTime() % (long)100000;
    }

    @JvmStatic
    @NotNull
    public static final String template() {
        Object[] objectArray = new String[]{"// Кастомное небо Kimiko", "// API как на Shadertoy: iTime, iResolution, mainImage(fragColor, fragCoord)", "//", "// fragCoord экранный, как на Shadertoy - вставленные шейдеры работают 1-в-1.", "// Хочешь, чтобы картинка была натянута на небо панорамой - раскомментируй:", "// #define KIMIKO_PANORAMA", "// (у панорамы есть шов и точка схода в зените - для 2D-шейдеров выглядит плохо;", "// небо, которое стоит на месте, лучше писать через skyDirection(), как в примере)", "//", "// Доп. функции клиента:", "//   skyDirection()   - направление взгляда в мире (vec3)", "//   skyPanoramaUv()  - панорамные UV неба 0..1 (vec2)", "//   kimikoColor()    - основной цвет неба из настроек (vec3)", "//   kimikoColor2()   - второй цвет неба (vec3)", "", "void mainImage(out vec4 fragColor, in vec2 fragCoord) {", "    vec3 dir = skyDirection();", "    float h = clamp(dir.y * 0.5 + 0.5, 0.0, 1.0);", "", "    vec3 top = kimikoColor2() * 0.55;", "    vec3 bottom = kimikoColor() * 0.20;", "    vec3 col = mix(bottom, top, pow(h, 1.4));", "", "    float wave = sin(dir.x * 5.0 + iTime * 0.5) * sin(dir.z * 5.0 - iTime * 0.35);", "    col += kimikoColor() * 0.18 * smoothstep(0.15, 0.95, wave) * (1.0 - h * 0.6);", "", "    float sparkle = fract(sin(dot(floor(dir.xz * 90.0 / max(dir.y, 0.12)), vec2(12.9898, 78.233))) * 43758.5453);", "    col += vec3(step(0.997, sparkle)) * smoothstep(0.05, 0.4, dir.y);", "", "    fragColor = vec4(col, 1.0);", "}"};
        return String.join(NEWLINE, (CharSequence[]) objectArray);
    }

    @JvmStatic
    @NotNull
    public static final String documentation() {
        Object[] objectArray = new String[]{"Напиши GLSL-шейдер неба для майнкрафт-клиента Kimiko (Minecraft 1.21.11, OpenGL 3.2, GLSL 150).", "", "ФОРМАТ ОТВЕТА", "Верни ТОЛЬКО код шейдера, без markdown-заборов и пояснений вокруг.", "", "ЖЁСТКИЕ ПРАВИЛА", "1. Точка входа ровно одна: void mainImage(out vec4 fragColor, in vec2 fragCoord).", "2. НЕ пиши void main() — обёртку добавляет клиент, свой main() отклоняется валидатором.", "3. НЕ пиши #version, uniform, in/out, layout — всё это уже объявлено в обёртке.", "4. Это GLSL 150 (не GLSL ES, не HLSL): нет texture2D, все float-литералы с точкой (1.0, а не 1).", "5. Циклы с константной границей, массивы фиксированной длины.", "6. Только процедурная математика: своих текстур и семплеров подключить нельзя.", "7. Альфа игнорируется — небо непрозрачное, важен только fragColor.rgb.", "", "ЧТО ЭТО РИСУЕТ", "Шейдер полностью заменяет ванильное небо: он выполняется для каждого пикселя,", "где виден небосвод (там, где нет блоков). Солнце, луна и звёзды ванили не рисуются —", "если они нужны, рисуй их сам.", "", "ДВА СПОСОБА СЧИТАТЬ КАРТИНКУ", "A. Мировой (правильный для неба): бери направление взгляда через skyDirection().", "   Картинка тогда стоит на месте в мире, а не едет вместе с камерой.", "   dir.y — высота: 1.0 зенит, 0.0 горизонт, отрицательное — ниже горизонта.", "   dir.xz — азимут. Облака удобно делать проекцией dir.xz / max(dir.y, 0.1) на плоскость.", "B. Экранный (Shadertoy 1-в-1): используй fragCoord / iResolution.xy.", "   Так вставленные с Shadertoy шейдеры работают без правок, но картинка приклеена к экрану.", "", "ДОСТУПНОЕ API", "  iTime            float, секунды", "  iResolution      vec3, размер экрана", "  iFrame, iMouse, iTimeDelta — заглушки для совместимости с Shadertoy", "  skyDirection()   vec3 — нормализованное направление взгляда в мире", "  skyPanoramaUv()  vec2 0..1 — равнопрямоугольная развёртка неба", "  kimikoColor()    vec3 — основной цвет неба из настроек клиента", "  kimikoColor2()   vec3 — второй цвет неба", "  kimikoBrightness() float — множитель яркости из настроек (применяется и так, снаружи)", "", "  #define KIMIKO_PANORAMA — в начале файла заставляет fragCoord считаться", "  по панораме неба вместо экрана. У панорамы есть шов по азимуту и точка схода", "  в зените, поэтому для 2D-шейдеров она выглядит плохо: лучше писать через skyDirection().", "", "СТИЛЬ РЕЗУЛЬТАТА", "Небо должно быть плавным: резкие мигания на весь экран не нужны.", "Опирайся на kimikoColor()/kimikoColor2() как на основную палитру, чтобы шейдер", "жил в теме клиента. rgb можно выводить ярче 1.0 (до 4.0) — получится пересвет для солнца/звёзд.", "Не делай тяжёлых циклов рейтрейса: это фуллскрин-пас каждый кадр.", "", "ШАБЛОН", "void mainImage(out vec4 fragColor, in vec2 fragCoord) {", "    vec3 dir = skyDirection();", "    float h = clamp(dir.y * 0.5 + 0.5, 0.0, 1.0);", "    vec3 col = mix(kimikoColor(), kimikoColor2(), pow(h, 1.4));", "    fragColor = vec4(col, 1.0);", "}", "", "ЗАДАЧА", "Сделай на этой основе: <опиши сюда, какое небо ты хочешь>"};
        return String.join(NEWLINE, (CharSequence[]) objectArray);
    }

    private final Path file(String name) {
        String clean = UserSkyShaders.sanitizeName(name);
        if (((CharSequence)clean).length() == 0) {
            return null;
        }
        return UserSkyShaders.folder().resolve(clean + EXTENSION);
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

