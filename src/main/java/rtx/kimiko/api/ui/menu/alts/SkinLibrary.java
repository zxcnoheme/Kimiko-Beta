package rtx.kimiko.api.ui.menu.alts;

import com.mojang.blaze3d.textures.GpuTextureView;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.entity.player.PlayerSkinType;
import net.minecraft.entity.player.SkinTextures;
import net.minecraft.util.AssetInfo;
import net.minecraft.util.Identifier;
import rtx.kimiko.utils.session.SessionChanger;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Локальная библиотека скинов (по образцу Quick Skin, своя реализация):
 * папка библиотеки  &lt;игра&gt;/kimiko/skins/library/&lt;имя&gt;.png — все импортированные скины;
 * папка назначения  &lt;игра&gt;/kimiko/skins/&lt;ник&gt;.png — скин конкретного аккаунта.
 * Назначенные скины подставляются в игровой конвейер через SkinProviderMixin,
 * поэтому работают и в меню (головы), и на самом игроке после входа в мир.
 */
public final class SkinLibrary {
    /** Скины, назначенные аккаунтам: ключ — ник. */
    private static final Map<String, SkinTextures> SKINS = new ConcurrentHashMap<>();
    private static final Map<String, Identifier> TEXTURES = new ConcurrentHashMap<>();
    /** Библиотека: ключ — имя файла без .png. */
    private static final Map<String, Identifier> LIB_TEXTURES = new ConcurrentHashMap<>();
    private static final Map<String, PlayerSkinType> LIB_MODELS = new ConcurrentHashMap<>();
    /** Миниатюры-бюсты для плиток библиотеки. */
    private static final Map<String, Identifier> THUMBS = new ConcurrentHashMap<>();
    private static volatile boolean scanned;
    private static volatile boolean libScanned;

    private SkinLibrary() {
    }

    private static Path dir() {
        try {
            return net.fabricmc.loader.api.FabricLoader.getInstance().getGameDir().resolve("kimiko").resolve("skins");
        } catch (Throwable ignored) {
            return null;
        }
    }

    private static Path libDir() {
        Path d = dir();
        return d == null ? null : d.resolve("library");
    }

    public static Path fileFor(String name) {
        Path d = dir();
        return d == null ? null : d.resolve(safeKey(name) + ".png");
    }

    public static Path libFileFor(String key) {
        Path d = libDir();
        return d == null ? null : d.resolve(safeKey(key) + ".png");
    }

    private static String safeKey(String name) {
        return name == null ? "" : name.trim().toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9_\\-.]", "_");
    }

    /** Сканирует папку назначения (скины аккаунтов) — один раз за сессию. */
    public static void ensureLoaded() {
        if (scanned) {
            return;
        }
        scanned = true;
        Path d = dir();
        if (d == null || !Files.isDirectory(d)) {
            return;
        }
        try (java.util.stream.Stream<Path> s = Files.list(d)) {
            s.filter(p -> p.getFileName().toString().toLowerCase(Locale.ROOT).endsWith(".png"))
                    .forEach(p -> {
                        String n = p.getFileName().toString();
                        registerFile(n.substring(0, n.length() - 4), p);
                    });
        } catch (Throwable ignored) {
        }
    }

    /** Сканирует библиотеку и регистрирует текстуры всех скинов (один раз за сессию). */
    public static void ensureLibLoaded() {
        if (libScanned) {
            return;
        }
        libScanned = true;
        Path d = libDir();
        if (d == null || !Files.isDirectory(d)) {
            return;
        }
        try (java.util.stream.Stream<Path> s = Files.list(d)) {
            s.filter(p -> p.getFileName().toString().toLowerCase(Locale.ROOT).endsWith(".png"))
                    .forEach(p -> {
                        String n = p.getFileName().toString();
                        registerLibFile(n.substring(0, n.length() - 4), p);
                    });
        } catch (Throwable ignored) {
        }
    }

    /** Есть ли у аккаунта свой скин. */
    public static boolean has(String name) {
        ensureLoaded();
        return name != null && SKINS.containsKey(safeKey(name));
    }

    /** Скин по имени профиля — его подхватывает SkinProviderMixin. */
    public static SkinTextures skinFor(String profileName) {
        ensureLoaded();
        return profileName == null ? null : SKINS.get(safeKey(profileName));
    }

    /** Отсортированный список имён скинов библиотеки. */
    public static List<String> libKeys() {
        ensureLibLoaded();
        List<String> keys = new ArrayList<>(LIB_TEXTURES.keySet());
        keys.sort(String.CASE_INSENSITIVE_ORDER);
        return keys;
    }

    public static Identifier libTexture(String key) {
        ensureLibLoaded();
        return key == null ? null : LIB_TEXTURES.get(safeKey(key));
    }

    public static PlayerSkinType libModel(String key) {
        ensureLibLoaded();
        return key == null ? PlayerSkinType.WIDE : LIB_MODELS.getOrDefault(safeKey(key), PlayerSkinType.WIDE);
    }

    /** TextureManager готов? */
    private static boolean clientReady() {
        MinecraftClient mc = MinecraftClient.getInstance();
        return mc != null && mc.getTextureManager() != null;
    }

    /**
     * Динамической текстуре нужен GPU-view: vanilla его назначает только своим текстурам,
     * поэтому создаём сами (иначе image-рендерер молча пропускает текстуру).
     */
    private static void ensureView(NativeImageBackedTexture tex) {
        if (tex.getGlTextureView() == null && tex.getGlTexture() != null) {
            try {
                GpuTextureView view = com.mojang.blaze3d.systems.RenderSystem.getDevice().createTextureView(tex.getGlTexture());
                ((mixin.skins.AbstractTextureAccessor) (Object) tex).kimiko$setView(view);
            } catch (Throwable ignored) {
            }
        }
    }

    /**
     * Растеризует статичный мини-бюст скина (голова, корпус, руки, ноги — передние грани)
     * в изображение 16x32 — для плиток библиотеки.
     * Семантика copyRect (по байткоду ваниллы): src.copyRect(dst, srcX, srcY, dstX, dstY, w, h, mirrorX, mirrorY) —
     * читает из src, пишет в dst (первый аргумент).
     */
    private static NativeImage makeBust(NativeImage skin, boolean legacy) {
        NativeImage out = new NativeImage(16, 32, true);
        // голова: передняя грань (8,8) 8x8
        skin.copyRect(out, 8, 8, 4, 0, 8, 8, false, false);
        // корпус: передняя грань (20,20) 8x12
        skin.copyRect(out, 20, 20, 4, 8, 8, 12, false, false);
        // правая рука: передняя грань (44,20) 4x12 — слева на бюсте
        skin.copyRect(out, 44, 20, 0, 8, 4, 12, false, false);
        // левая рука: у 64x64 своя зона (36,52) 4x12, у legacy — зеркало правой
        if (!legacy) {
            skin.copyRect(out, 36, 52, 12, 8, 4, 12, false, false);
        } else {
            skin.copyRect(out, 44, 20, 12, 8, 4, 12, true, false);
        }
        // ноги: передняя грань (4,20) 8x12
        skin.copyRect(out, 4, 20, 4, 20, 8, 12, false, false);
        return out;
    }

    /** Идентификатор миниатюры скина библиотеки (или null). */
    public static Identifier thumbTexture(String key) {
        ensureLibLoaded();
        return key == null ? null : THUMBS.get(safeKey(key));
    }

    public static SkinTextures libSkin(String key) {
        Identifier tex = libTexture(key);
        if (tex == null) {
            return null;
        }
        // asset-id без "textures/...png" — ваниль сам обернёт в texturePath
        return SkinTextures.create(new AssetInfo.TextureAssetInfo(Identifier.of("kimiko", "skinlib/" + safeKey(key))), null, null, libModel(key));
    }

    /** Итог импорта: error == null — успех, key — имя скина в библиотеке. */
    public record Import(String error, String key) {
    }

    /**
     * Импорт в библиотеку из URL (http/https) или локального пути.
     * Имя скина — из имени файла/URL; при совпадении перезаписывает старый.
     */
    public static Import importToLibrary(String source) {
        if (source == null || source.isBlank()) {
            return new Import("Пустой источник", null);
        }
        try {
            Path d = libDir();
            if (d == null) {
                return new Import("Не удалось определить папку", null);
            }
            Files.createDirectories(d);
            String src = source.trim().replace("\\", "/");
            String base;
            byte[] bytes;
            if (src.startsWith("http://") || src.startsWith("https://")) {
                HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();
                HttpRequest req = HttpRequest.newBuilder(URI.create(src))
                        .header("User-Agent", "Kimiko").GET().build();
                HttpResponse<byte[]> resp = client.send(req, HttpResponse.BodyHandlers.ofByteArray());
                if (resp.statusCode() != 200) {
                    return new Import("Сервер ответил " + resp.statusCode(), null);
                }
                bytes = resp.body();
                String path = URI.create(src).getPath();
                base = path == null ? "" : path.substring(path.lastIndexOf('/') + 1);
            } else {
                Path from = Path.of(src);
                if (!Files.isRegularFile(from)) {
                    return new Import("Файл не найден", null);
                }
                bytes = Files.readAllBytes(from);
                base = from.getFileName() == null ? "skin" : from.getFileName().toString();
            }
            if (base.toLowerCase(Locale.ROOT).endsWith(".png")) {
                base = base.substring(0, base.length() - 4);
            }
            String key = safeKey(base);
            if (key.isEmpty()) {
                key = "skin";
            }
            Path dst = d.resolve(key + ".png");
            Files.write(dst, bytes);
            // регистрация текстуры требует рендер-поток; из фонового потока — через execute
            final String keyF = key;
            final Path dstF = dst;
            String err;
            if (com.mojang.blaze3d.systems.RenderSystem.isOnRenderThread()) {
                err = registerLibFile(keyF, dstF);
            } else {
                MinecraftClient mc = MinecraftClient.getInstance();
                if (mc == null) {
                    err = "Клиент не готов";
                } else {
                    java.util.concurrent.CompletableFuture<String> f = new java.util.concurrent.CompletableFuture<>();
                    mc.execute(() -> f.complete(registerLibFile(keyF, dstF)));
                    err = f.join();
                }
            }
            if (err != null) {
                try {
                    Files.deleteIfExists(dst);
                } catch (Throwable ignored) {
                }
                return new Import(err, null);
            }
            return new Import(null, key);
        } catch (Throwable t) {
            return new Import(String.valueOf(t.getMessage() == null ? t : t.getMessage()), null);
        }
    }

    /** Назначает скин библиотеки аккаунту: копирует в его слот и регистрирует. @return null при успехе. */
    public static String apply(String account, String libKey) {
        Path src = libFileFor(libKey);
        if (src == null || !Files.isRegularFile(src)) {
            return "Скин не найден в библиотеке";
        }
        Path dst = fileFor(account);
        if (dst == null) {
            return "Не удалось определить папку";
        }
        try {
            Files.createDirectories(dst.getParent());
            Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
        } catch (Throwable t) {
            return String.valueOf(t.getMessage() == null ? t : t.getMessage());
        }
        return registerFile(account, dst);
    }

    /** Загружает PNG аккаунта и регистрирует текстуру + SkinTextures. @return null при успехе, иначе текст ошибки. */
    public static String registerFile(String name, Path png) {
        try {
            if (name == null || name.isBlank() || png == null || !Files.isRegularFile(png)) {
                return "Файл не найден";
            }
            NativeImage img;
            try (InputStream in = Files.newInputStream(png)) {
                img = NativeImage.read(in);
            }
            img = remapLegacy(img);
            if (img == null) {
                return "Размер скина не 64x64 и не 64x32";
            }
            PlayerSkinType model = detectModel(img);
            String key = safeKey(name);
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc == null || mc.getTextureManager() == null) {
                return "Клиент не готов";
            }
            // при повторном импорте счищаем старую текстуру, иначе регистрация поверх не сработает
            Identifier oldId = TEXTURES.get(key);
            if (oldId != null) {
                try {
                    mc.getTextureManager().destroyTexture(oldId);
                } catch (Throwable ignored) {
                }
                TEXTURES.remove(key);
                SKINS.remove(key);
            }
            // ванильный TextureAssetInfo оборачивает asset-id в "textures/<path>.png",
            // поэтому текстуру регистрируем под обёрнутым именем, а в AssetInfo даём голый id
            Identifier id = Identifier.of("kimiko", "textures/skin/" + key + ".png");
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko skin of " + key, img);
            tex.upload();
            ensureView(tex);
            mc.getTextureManager().registerTexture(id, tex);
            AssetInfo.TextureAsset asset = new AssetInfo.TextureAssetInfo(Identifier.of("kimiko", "skin/" + key));
            SkinTextures skin = SkinTextures.create(asset, null, null, model);
            SKINS.put(key, skin);
            TEXTURES.put(key, id);
            return null;
        } catch (Throwable t) {
            return String.valueOf(t.getMessage() == null ? t : t.getMessage());
        }
    }

    /** Регистрирует скин библиотеки: текстура + модель. @return null при успехе, иначе текст ошибки. */
    private static String registerLibFile(String key, Path png) {
        try {
            if (key == null || key.isBlank() || png == null || !Files.isRegularFile(png)) {
                return "Файл не найден";
            }
            NativeImage img;
            try (InputStream in = Files.newInputStream(png)) {
                img = NativeImage.read(in);
            }
            boolean legacy = img.getHeight() == 32;
            img = remapLegacy(img);
            if (img == null) {
                return "Размер скина не 64x64 и не 64x32";
            }
            PlayerSkinType model = detectModel(img);
            String k = safeKey(key);
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc == null || mc.getTextureManager() == null) {
                return "Клиент не готов";
            }
            Identifier oldId = LIB_TEXTURES.get(k);
            if (oldId != null) {
                try {
                    mc.getTextureManager().destroyTexture(oldId);
                } catch (Throwable ignored) {
                }
                LIB_TEXTURES.remove(k);
            }
            Identifier oldThumb = THUMBS.get(k);
            if (oldThumb != null) {
                try {
                    mc.getTextureManager().destroyTexture(oldThumb);
                } catch (Throwable ignored) {
                }
                THUMBS.remove(k);
            }
            Identifier id = Identifier.of("kimiko", "textures/skinlib/" + k + ".png");
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko skin lib " + k, img);
            tex.upload();
            ensureView(tex);
            mc.getTextureManager().registerTexture(id, tex);
            LIB_TEXTURES.put(k, id);
            LIB_MODELS.put(k, model);
            // статичная миниатюра-бюст для плитки
            try {
                NativeImage bust = makeBust(img, legacy);
                Identifier thumbId = Identifier.of("kimiko", "textures/skinthumb/" + k + ".png");
                NativeImageBackedTexture thumb = new NativeImageBackedTexture(() -> "Kimiko skin thumb " + k, bust);
                thumb.upload();
                ensureView(thumb);
                mc.getTextureManager().registerTexture(thumbId, thumb);
                THUMBS.put(k, thumbId);
            } catch (Throwable ignored) {
            }
            return null;
        } catch (Throwable t) {
            return String.valueOf(t.getMessage() == null ? t : t.getMessage());
        }
    }

    /** Удаляет скин аккаунта (назначение). @return null при успехе, иначе текст ошибки. */
    public static String remove(String name) {
        String key = safeKey(name);
        SKINS.remove(key);
        Identifier id = TEXTURES.remove(key);
        if (id != null) {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc != null && mc.getTextureManager() != null) {
                try {
                    mc.getTextureManager().destroyTexture(id);
                } catch (Throwable ignored) {
                }
            }
        }
        try {
            Path f = fileFor(name);
            if (f != null) {
                Files.deleteIfExists(f);
            }
        } catch (Throwable t) {
            return String.valueOf(t.getMessage() == null ? t : t.getMessage());
        }
        return null;
    }

    /** Удаляет скин из библиотеки (файл и текстуру). Если он был назначен аккаунтам — снимается. @return null при успехе. */
    public static String deleteLib(String key) {
        String k = safeKey(key);
        Identifier id = LIB_TEXTURES.remove(k);
        LIB_MODELS.remove(k);
        Identifier thumb = THUMBS.remove(k);
        if (thumb != null) {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc != null && mc.getTextureManager() != null) {
                try {
                    mc.getTextureManager().destroyTexture(thumb);
                } catch (Throwable ignored) {
                }
            }
        }
        if (id != null) {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc != null && mc.getTextureManager() != null) {
                try {
                    mc.getTextureManager().destroyTexture(id);
                } catch (Throwable ignored) {
                }
            }
        }
        // снять назначение у аккаунтов, чей файл — этот же скин (файлы совпадают по байтам не проверяем; назначение хранит свою копию)
        try {
            Path f = libFileFor(key);
            if (f != null) {
                Files.deleteIfExists(f);
            }
        } catch (Throwable t) {
            return String.valueOf(t.getMessage() == null ? t : t.getMessage());
        }
        return null;
    }

    /** Обновляет скин активного аккаунта: нужно после входа/смены скина, чтобы миксин подхватил по имени. */
    public static void applyToCurrent() {
        ensureLoaded();
        String current = null;
        try {
            current = SessionChanger.getCurrentUsername();
        } catch (Throwable ignored) {
        }
        if (current == null) {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc != null && mc.getSession() != null) {
                current = mc.getSession().getUsername();
            }
        }
        if (current != null) {
            has(current);
        }
    }

    /** 64x32 → 64x64: ванильная последовательность копий (dstX = srcX + смещение из ваниллы). */
    private static NativeImage remapLegacy(NativeImage img) {
        if (img.getHeight() == 64 && img.getWidth() == 64) {
            return img;
        }
        if (img.getWidth() != 64 || img.getHeight() != 32) {
            return null;
        }
        NativeImage out = new NativeImage(64, 64, true);
        // верхняя половина как есть
        img.copyRect(out, 0, 0, 0, 0, 64, 32, false, false);
        // нижние половины ног и рук — точная ванильная последовательность remapOldSkinToNew
        img.copyRect(out, 4, 16, 20, 48, 4, 4, true, false);
        img.copyRect(out, 8, 16, 24, 48, 4, 4, true, false);
        img.copyRect(out, 0, 20, 24, 52, 4, 12, true, false);
        img.copyRect(out, 4, 20, 20, 52, 4, 12, true, false);
        img.copyRect(out, 8, 20, 16, 52, 4, 12, true, false);
        img.copyRect(out, 12, 20, 28, 52, 4, 12, true, false);
        img.copyRect(out, 44, 16, 36, 48, 4, 4, true, false);
        img.copyRect(out, 48, 16, 40, 48, 4, 4, true, false);
        img.copyRect(out, 40, 20, 40, 52, 4, 12, true, false);
        img.copyRect(out, 44, 20, 36, 52, 4, 12, true, false);
        img.copyRect(out, 48, 20, 32, 52, 4, 12, true, false);
        img.copyRect(out, 52, 20, 44, 52, 4, 12, true, false);
        return out;
    }

    /** Определяет модель рук: тонкая, если верхняя часть руки на 64x64 прозрачна (x=50..52, y=16..20). */
    private static PlayerSkinType detectModel(NativeImage img) {
        try {
            for (int x = 50; x <= 52; x++) {
                for (int y = 16; y <= 20; y++) {
                    if ((img.getColorArgb(x, y) >>> 24) == 0) {
                        return PlayerSkinType.SLIM;
                    }
                }
            }
        } catch (Throwable ignored) {
        }
        return PlayerSkinType.WIDE;
    }
}
