/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  mods.acountswiher.ru.vidtu.ias.account.MicrosoftAccount
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 */
package mods.acountswiher.ru.vidtu.ias.config.migrator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.UUID;
import java.util.function.Predicate;
import mods.acountswiher.ru.vidtu.ias.account.Account;
import mods.acountswiher.ru.vidtu.ias.account.MicrosoftAccount;
import mods.acountswiher.ru.vidtu.ias.account.OfflineAccount;
import mods.acountswiher.ru.vidtu.ias.config.IASConfig;
import mods.acountswiher.ru.vidtu.ias.config.IASStorage;
import mods.acountswiher.ru.vidtu.ias.config.TextAlign;
import mods.acountswiher.ru.vidtu.ias.config.migrator.Migrator;
import mods.acountswiher.ru.vidtu.ias.crypt.DummyCrypt;
import mods.acountswiher.ru.vidtu.ias.utils.GSONUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

final class MigratorV2
implements Migrator {
    MigratorV2() {
    }

    @Override
    public void load(@NotNull JsonObject json) {
        try {
            TextAlign titleTextAlign;
            int version = GSONUtils.getIntOrThrow(json, "version");
            if (version != 2) {
                throw new IllegalArgumentException("V2 has invalid version marker: " + version);
            }
            boolean titleText = json.has("titleScreenText") ? GSONUtils.getBooleanOrThrow(json, "titleScreenText") : IASConfig.titleText;
            String titleTextX = json.has("titleScreenTextX") ? GSONUtils.getStringOrThrow(json, "titleScreenTextX") : IASConfig.titleTextX;
            String titleTextY = json.has("titleScreenTextY") ? GSONUtils.getStringOrThrow(json, "titleScreenTextY") : IASConfig.titleTextY;
            String rawTitleTextAlign = json.has("titleScreenTextAlignment") ? GSONUtils.getStringOrThrow(json, "titleScreenTextAlignment").toUpperCase(Locale.ROOT) : null;
            try {
                titleTextAlign = rawTitleTextAlign != null ? TextAlign.valueOf(rawTitleTextAlign.toUpperCase(Locale.ROOT)) : IASConfig.titleTextAlign;
            }
            catch (Throwable ignored) {
                titleTextAlign = IASConfig.titleTextAlign;
            }
            boolean titleButton = json.has("titleScreenButton") ? GSONUtils.getBooleanOrThrow(json, "titleScreenButton") : IASConfig.titleButton;
            String titleButtonX = json.has("titleScreenButtonX") ? GSONUtils.getStringOrThrow(json, "titleScreenButtonX") : IASConfig.titleButtonX;
            String titleButtonY = json.has("titleScreenButtonY") ? GSONUtils.getStringOrThrow(json, "titleScreenButtonY") : IASConfig.titleButtonY;
            String serversButtonX = json.has("multiplayerScreenButtonX") ? GSONUtils.getStringOrThrow(json, "multiplayerScreenButtonX") : null;
            String serversButtonY = json.has("multiplayerScreenButtonY") ? GSONUtils.getStringOrThrow(json, "multiplayerScreenButtonY") : null;
            JsonArray rawAccounts = json.has("accounts") ? GSONUtils.getArrayOrThrow(json, "accounts") : new JsonArray(0);
            ArrayList<Account> accounts = new ArrayList<Account>(rawAccounts.size());
            for (JsonElement entry : rawAccounts) {
                Account account;
                JsonObject rawAccount = entry.getAsJsonObject();
                String type = GSONUtils.getStringOrThrow(rawAccount, "type");
                switch (type.toLowerCase(Locale.ROOT)) {
                    case "ias:microsoft": 
                    case "ru.vidtu.ias.account.microsoftaccount": {
                        byte[] data;
                        byte[] unencrypted;
                        UUID uuid = UUID.fromString(GSONUtils.getStringOrThrow(rawAccount, "uuid"));
                        String name = GSONUtils.getStringOrThrow(rawAccount, "name");
                        String accessToken = GSONUtils.getStringOrThrow(rawAccount, "accessToken");
                        String refreshToken = GSONUtils.getStringOrThrow(rawAccount, "refreshToken");
                        try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream(accessToken.length() + refreshToken.length() + 4);
                             DataOutputStream out = new DataOutputStream(byteOut);){
                            out.writeUTF(accessToken);
                            out.writeUTF(refreshToken);
                            unencrypted = byteOut.toByteArray();
                        }
                        try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream(unencrypted.length + 32);
                             DataOutputStream out = new DataOutputStream(byteOut);){
                            DummyCrypt crypt = DummyCrypt.INSTANCE;
                            byte[] encrypted = crypt.encrypt(unencrypted);
                            out.writeUTF(crypt.type());
                            out.write(encrypted);
                            data = byteOut.toByteArray();
                        }
                        account = new MicrosoftAccount(true, uuid, name, data);
                        break;
                    }
                    case "ias:offline": 
                    case "ru.vidtu.ias.account.offlineaccount": {
                        String name = GSONUtils.getStringOrThrow(rawAccount, "name");
                        account = new OfflineAccount(name, null);
                        break;
                    }
                    default: {
                        account = null;
                    }
                }
                if (account == null) {
                    continue;
                }
                accounts.add(account);
            }
            titleButtonX = titleButtonX == null ? null : titleButtonX.replace("w", "%width%").replace("W", "%width%").replace("h", "%height%").replace("H", "%height%");
            titleButtonY = titleButtonY == null ? null : titleButtonY.replace("w", "%width%").replace("W", "%width%").replace("h", "%height%").replace("H", "%height%");
            serversButtonX = serversButtonX == null ? null : serversButtonX.replace("w", "%width%").replace("W", "%width%").replace("h", "%height%").replace("H", "%height%");
            serversButtonY = serversButtonY == null ? null : serversButtonY.replace("w", "%width%").replace("W", "%width%").replace("h", "%height%").replace("H", "%height%");
            IASConfig.titleText = titleText;
            IASConfig.titleTextX = titleTextX;
            IASConfig.titleTextY = titleTextY;
            IASConfig.titleTextAlign = titleTextAlign;
            IASConfig.titleButton = titleButton;
            IASConfig.titleButtonX = titleButtonX;
            IASConfig.titleButtonY = titleButtonY;
            IASConfig.serversButtonX = serversButtonX;
            IASConfig.serversButtonY = serversButtonY;
            IASStorage.ACCOUNTS.addAll(accounts);
            HashSet set = new HashSet(IASStorage.ACCOUNTS.size());
            IASStorage.ACCOUNTS.removeIf(Predicate.not(set::add));
        }
        catch (Throwable t) {
            String redacted = OBFUSCATE_LOGS.matcher(String.valueOf(json)).replaceAll("$1[TOKEN]");
            throw new JsonParseException("Unable to migrate V2 config: " + redacted, t);
        }
    }

    @Contract(pure=true)
    @NotNull
    public String toString() {
        return "MigratorV2{}";
    }
}

