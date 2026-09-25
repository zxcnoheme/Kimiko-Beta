/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package mods.acountswiher.ru.vidtu.ias.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Objects;
import mods.acountswiher.ru.vidtu.ias.account.Account;
import mods.acountswiher.ru.vidtu.ias.config.ServerMode;
import mods.acountswiher.ru.vidtu.ias.config.TextAlign;
import mods.acountswiher.ru.vidtu.ias.config.migrator.Migrator;
import mods.acountswiher.ru.vidtu.ias.utils.GSONUtils;
import mods.acountswiher.ru.vidtu.ias.utils.IUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class IASConfig {
    @NotNull
    private static final Gson GSON = new GsonBuilder().excludeFieldsWithModifiers(new int[]{128, 16}).create();
    public static boolean titleText = true;
    @Nullable
    public static String titleTextX = null;
    @Nullable
    public static String titleTextY = null;
    @Nullable
    public static TextAlign titleTextAlign = TextAlign.LEFT;
    public static boolean titleButton = true;
    @Nullable
    public static String titleButtonX = null;
    @Nullable
    public static String titleButtonY = null;
    public static boolean serversText = true;
    @Nullable
    public static String serversTextX = null;
    @Nullable
    public static String serversTextY = null;
    @Nullable
    public static TextAlign serversTextAlign = TextAlign.LEFT;
    public static boolean serversButton = true;
    @Nullable
    public static String serversButtonX = null;
    @Nullable
    public static String serversButtonY = null;
    public static boolean allowNoCrypt = false;
    public static boolean nickWarns = true;
    public static boolean unexpectedPigs = true;
    public static boolean barNick = false;
    @Nullable
    public static ServerMode server = ServerMode.AVAILABLE;
    public static boolean passwordEchoing = true;
    public static boolean restoreLastAccount = true;
    @Nullable
    public static String lastAccountName = null;
    @Nullable
    public static String lastAccountUuid = null;
    public static boolean lastAccountOnline = true;

    @Contract(pure=true)
    private IASConfig() {
    }

    public static void load(@NotNull Path path) {
        try {
            Path file = path.resolve("ias.json");
            if (!Files.isRegularFile(file, new LinkOption[0])) {
                IASConfig.save(path);
                return;
            }
            String value = Files.readString(file);
            JsonObject json = (JsonObject)GSON.fromJson(value, JsonObject.class);
            int version = json.has("version") ? GSONUtils.getIntOrThrow(json, "version") : 1;
            Migrator migrator = Migrator.fromVersion(version);
            if (migrator != null) {
                migrator.load(json);
                IASConfig.save(path);
                return;
            }
            GSON.fromJson((JsonElement)json, IASConfig.class);
        }
        catch (Throwable t) {
            throw new RuntimeException("Unable to load IAS config.", t);
        }
        finally {
            titleTextAlign = Objects.requireNonNullElse(titleTextAlign, TextAlign.LEFT);
            serversTextAlign = Objects.requireNonNullElse(serversTextAlign, TextAlign.LEFT);
            server = Objects.requireNonNullElse(server, ServerMode.AVAILABLE);
        }
    }

    public static void save(@NotNull Path path) {
        try {
            titleTextAlign = Objects.requireNonNullElse(titleTextAlign, TextAlign.LEFT);
            serversTextAlign = Objects.requireNonNullElse(serversTextAlign, TextAlign.LEFT);
            server = Objects.requireNonNullElse(server, ServerMode.AVAILABLE);
            Path file = path.resolve("ias.json");
            JsonObject json = (JsonObject)GSON.toJsonTree((Object)new IASConfig());
            json.addProperty("version", (Number)4);
            String value = GSON.toJson((JsonElement)json);
            Files.createDirectories(file.getParent(), new FileAttribute[0]);
            Files.writeString(file, (CharSequence)value, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE, StandardOpenOption.SYNC, StandardOpenOption.DSYNC);
        }
        catch (Throwable t) {
            throw new RuntimeException("Unable to save IAS config.", t);
        }
    }

    public static boolean useServerAuth() {
        if (server == null) {
            return IUtils.canUseSunServer();
        }
        return switch (server) {
            default -> throw new MatchException(null, null);
            case ServerMode.ALWAYS -> true;
            case ServerMode.NEVER -> false;
            case ServerMode.AVAILABLE -> IUtils.canUseSunServer();
        };
    }

    public static void rememberLastAccount(@NotNull Account account, boolean online) {
        lastAccountName = account.name();
        lastAccountUuid = account.uuid().toString();
        lastAccountOnline = online;
    }

    public static void clearLastAccount() {
        lastAccountName = null;
        lastAccountUuid = null;
        lastAccountOnline = true;
    }

    public static boolean matchesLastAccount(@Nullable Account account) {
        if (account == null || lastAccountName == null || lastAccountUuid == null) {
            return false;
        }
        return lastAccountName.equalsIgnoreCase(account.name()) && lastAccountUuid.equalsIgnoreCase(account.uuid().toString());
    }
}

