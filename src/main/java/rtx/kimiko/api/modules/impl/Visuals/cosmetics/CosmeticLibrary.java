package rtx.kimiko.api.modules.impl.Visuals.cosmetics;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Регистрация динамических текстур внешних косметик (base64 PNG из model.json).
 * Вызывать только с рендер-потока (первое обращение — из рендера плитки/игрока).
 */
public final class CosmeticLibrary {
    private static final Map<String, Identifier> TEXTURES = new ConcurrentHashMap<>();

    private CosmeticLibrary() {
    }

    @Nullable
    public static Identifier registerBase64(String key, String base64) {
        Identifier existing = TEXTURES.get(key);
        if (existing != null) {
            return existing;
        }
        try {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc == null || mc.getTextureManager() == null) {
                return null;
            }
            int comma = base64.indexOf(',');
            if (comma >= 0 && comma < 32 && base64.regionMatches(0, "data:", 0, 5)) {
                base64 = base64.substring(comma + 1);
            }
            byte[] bytes = Base64.getDecoder().decode(base64);
            NativeImage img = NativeImage.read(new java.io.ByteArrayInputStream(bytes));
            String safe = sanitize(key);
            Identifier id = Identifier.of("kimiko", "textures/" + safe + ".png");
            NativeImageBackedTexture tex = new NativeImageBackedTexture(() -> "Kimiko cosmetic " + safe, img);
            tex.upload();
            ensureView(tex);
            mc.getTextureManager().registerTexture(id, tex);
            TEXTURES.put(key, id);
            return id;
        } catch (Throwable t) {
            return null;
        }
    }

    private static String sanitize(String key) {
        StringBuilder sb = new StringBuilder(key.length());
        for (char c : key.toCharArray()) {
            sb.append((Character.isLetterOrDigit(c) || c == '/' || c == '_' || c == '-' || c == '.') ? c : '_');
        }
        return sb.toString();
    }

    /** Динамической текстуре нужен GPU-view — vanilla его сам не назначает (как в SkinLibrary). */
    private static void ensureView(NativeImageBackedTexture tex) {
        if (tex.getGlTextureView() == null && tex.getGlTexture() != null) {
            try {
                com.mojang.blaze3d.textures.GpuTextureView view =
                        com.mojang.blaze3d.systems.RenderSystem.getDevice().createTextureView(tex.getGlTexture());
                ((mixin.skins.AbstractTextureAccessor) (Object) tex).kimiko$setView(view);
            } catch (Throwable ignored) {
            }
        }
    }
}
