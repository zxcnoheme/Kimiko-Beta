package rtx.kimiko.api.ui.menu;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.entity.player.SkinTextures;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import rtx.kimiko.api.ui.menu.alts.SkinLibrary;
import rtx.kimiko.api.ui.theme.ThemeManager;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class SkinHeads {
    private static final Map<String, SkinTextures> CACHE = new ConcurrentHashMap<>();
    private static final java.util.Set<String> PENDING = ConcurrentHashMap.newKeySet();

    private SkinHeads() {
    }

    public static void drawHead(DrawContext context, String username, UUID uuid, float x, float y, float size) {        if (size <= 0.0f) {
            return;
        }
        String key = username == null ? "player" : username.toLowerCase(Locale.ROOT);
        SkinTextures skin = CACHE.get(key);
        if (skin != null) {
            try {
                Identifier texture = skin.body().texturePath();
                if (texture != null) {
                    String tex = texture.toString();
                    if (Render2D.imageReady(tex)) {
                        // Голова (base layer): область (8,8)-(16,16) на скине
                        Render2D.imageUvNearest(tex, x, y, size, size, size * 0.22f, 0.5f,
                                0.125f, 0.125f, 0.25f, 0.25f, MenuColors.rgba(255, 255, 255, 255));
                        return;
                    }
                }
            } catch (Throwable ignored) {
            }
        } else {
            fetchHead(username, uuid, key);
        }
        // Плейсхолдер: светлая плитка с первой буквой ника — видно сразу, пока скин не загрузился
        float rad = size * 0.22f;
        int accent = ThemeManager.accent(255);
        Render2D.rect(x, y, size, size, rad, MenuColors.rgba(88, 98, 118, 255));
        Render2D.rect(x, y, size, size, rad,
                MenuColors.rgba(255, 255, 255, 26), MenuColors.rgba(255, 255, 255, 60),
                MenuColors.rgba(255, 255, 255, 34), MenuColors.rgba(255, 255, 255, 18));
        Render2D.outline(x, y, size, size, rad, 1.0f, MenuColors.withAlpha(accent, 200));
        String initial = username == null || username.isEmpty() ? "?" : username.substring(0, 1).toUpperCase(Locale.ROOT);
        float fs = size * 0.52f;
        float w = Fonts.BOLD.width(initial, fs);
        Fonts.BOLD.draw(initial, x + (size - w) / 2.0f, y + (size - fs) / 2.0f, fs, MenuColors.rgba(255, 255, 255, 255));
    }

    /** Сбрасывает кэш головы — после импорта/удаления скина. */
    public static void invalidate(String username) {
        if (username != null) {
            CACHE.remove(username.toLowerCase(Locale.ROOT));
        }
    }

    private static void fetchHead(String username, UUID uuid, String key) {
        if (!PENDING.add(key)) {
            return;
        }
        // свой скин из библиотеки Kimiko — мгновенно, без сети
        SkinTextures local = SkinLibrary.skinFor(username);
        if (local != null) {
            CACHE.put(key, local);
            PENDING.remove(key);
            return;
        }
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null || username == null) {
            PENDING.remove(key);
            return;
        }
        try {
            UUID id = uuid != null ? uuid : UUID.nameUUIDFromBytes(("OfflinePlayer:" + username).getBytes(java.nio.charset.StandardCharsets.UTF_8));
            mc.getSkinProvider().fetchSkinTextures(new GameProfile(id, username)).thenAcceptAsync(optional -> {
                optional.ifPresent(loaded -> CACHE.put(key, loaded));
                PENDING.remove(key);
            }, mc).exceptionally(throwable -> {
                PENDING.remove(key);
                return null;
            });
        } catch (Throwable ignored) {
            PENDING.remove(key);
        }
    }
}
