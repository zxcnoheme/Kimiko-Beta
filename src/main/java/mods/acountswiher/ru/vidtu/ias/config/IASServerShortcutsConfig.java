/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  org.jetbrains.annotations.NotNull
 */
package mods.acountswiher.ru.vidtu.ias.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public final class IASServerShortcutsConfig {
    private static final int CURRENT_VERSION = 3;
    private static final ShortcutEntry BREAK_PROJECT = new ShortcutEntry("BreakProject", "mc.breakproject.pro", "prostotrainer");
    private static final ShortcutEntry BLOCK_TIME = new ShortcutEntry("BlockTime", "mc.blocktime.pro", "blocktime");
    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
    private static final String FILE_NAME = "ias-shortcuts.json";

    private IASServerShortcutsConfig() {
        throw new AssertionError((Object)"No instances.");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @NotNull
    public static List<ShortcutEntry> load(@NotNull Path configDirectory) {
        Path file = configDirectory.resolve(FILE_NAME);
        try {
            Files.createDirectories(configDirectory, new FileAttribute[0]);
            if (!Files.isRegularFile(file, LinkOption.NOFOLLOW_LINKS)) {
                List<ShortcutEntry> defaults = IASServerShortcutsConfig.defaultEntries();
                IASServerShortcutsConfig.save(file, defaults);
                return defaults;
            }
            try (BufferedReader reader222 = Files.newBufferedReader(file, StandardCharsets.UTF_8);){
                ShortcutFile shortcutFile = (ShortcutFile)GSON.fromJson((Reader)reader222, ShortcutFile.class);
                List<ShortcutEntry> entries = IASServerShortcutsConfig.sanitize(shortcutFile != null ? shortcutFile.servers : null);
                if (!entries.isEmpty()) {
                    List<ShortcutEntry> migrated = IASServerShortcutsConfig.migrate(entries);
                    if (shortcutFile.version < 3 || !migrated.equals(entries)) {
                        IASServerShortcutsConfig.save(file, migrated);
                    }
                    List<ShortcutEntry> list = migrated;
                    return list;
                }
            }
        }
        catch (Throwable reader222) {
            // empty catch block
        }
        List<ShortcutEntry> defaults = IASServerShortcutsConfig.defaultEntries();
        try {
            IASServerShortcutsConfig.save(file, defaults);
            return defaults;
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return defaults;
    }

    private static void save(@NotNull Path file, @NotNull List<ShortcutEntry> entries) throws Exception {
        ShortcutFile shortcutFile = new ShortcutFile();
        shortcutFile.version = 3;
        shortcutFile.servers = new ArrayList<ShortcutEntry>(entries);
        Files.createDirectories(file.getParent(), new FileAttribute[0]);
        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);){
            GSON.toJson((Object)shortcutFile, (Appendable)writer);
        }
    }

    @NotNull
    private static List<ShortcutEntry> sanitize(List<ShortcutEntry> entries) {
        ArrayList<ShortcutEntry> sanitized = new ArrayList<ShortcutEntry>();
        if (entries == null) {
            return sanitized;
        }
        for (ShortcutEntry entry : entries) {
            if (entry == null) continue;
            String name = IASServerShortcutsConfig.normalize(entry.name());
            String address = IASServerShortcutsConfig.normalize(entry.address());
            String icon = IASServerShortcutsConfig.normalize(entry.icon());
            if (name == null || address == null || icon == null) continue;
            sanitized.add(new ShortcutEntry(name, address, icon));
        }
        return sanitized;
    }

    @NotNull
    private static List<ShortcutEntry> migrate(@NotNull List<ShortcutEntry> entries) {
        ArrayList<ShortcutEntry> migrated = new ArrayList<ShortcutEntry>();
        for (ShortcutEntry entry : entries) {
            if (IASServerShortcutsConfig.isOldBreakProjectEntry(entry) || IASServerShortcutsConfig.isBlockTimeEntry(entry)) continue;
            migrated.add(entry);
        }
        migrated.add(BREAK_PROJECT);
        migrated.add(BLOCK_TIME);
        return migrated;
    }

    private static boolean isOldBreakProjectEntry(ShortcutEntry entry) {
        return entry.name().equalsIgnoreCase("ProstoTrainer") || entry.name().equalsIgnoreCase("Saturn-X") || entry.name().equalsIgnoreCase(BREAK_PROJECT.name()) || entry.address().equalsIgnoreCase("play.prostotrainer.space") || entry.address().equalsIgnoreCase("play.saturn-x.space") || entry.address().equalsIgnoreCase(BREAK_PROJECT.address());
    }

    private static boolean isBlockTimeEntry(ShortcutEntry entry) {
        return entry.name().equalsIgnoreCase(BLOCK_TIME.name()) || entry.address().equalsIgnoreCase(BLOCK_TIME.address());
    }

    private static String normalize(String value) {
        if (value == null) {
            return null;
        }
        String normalized = value.trim();
        return normalized.isEmpty() ? null : normalized;
    }

    @NotNull
    private static List<ShortcutEntry> defaultEntries() {
        return List.of(new ShortcutEntry("FunTime", "FunTime.su", "funtime"), new ShortcutEntry("ReallyWorld", "mc.reallyworld.ru", "reallyworld"), new ShortcutEntry("HollyWorld", "mc.hollyworld.ru", "hollyworld"), new ShortcutEntry("AresMine", "mc.aresmine.ru", "aresmine"), BREAK_PROJECT, BLOCK_TIME);
    }

    private static final class ShortcutFile {
        private int version;
        private List<ShortcutEntry> servers = new ArrayList<ShortcutEntry>();

        private ShortcutFile() {
        }
    }

    public record ShortcutEntry(String name, String address, String icon) {
    }
}

