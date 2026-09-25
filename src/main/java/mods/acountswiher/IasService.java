/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.loader.api.FabricLoader
 */
package mods.acountswiher;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import mods.acountswiher.ru.vidtu.ias.IAS;
import mods.acountswiher.ru.vidtu.ias.account.Account;
import mods.acountswiher.ru.vidtu.ias.account.OfflineAccount;
import mods.acountswiher.ru.vidtu.ias.auth.LoginData;
import mods.acountswiher.ru.vidtu.ias.auth.handlers.LoginHandler;
import mods.acountswiher.ru.vidtu.ias.config.IASConfig;
import mods.acountswiher.ru.vidtu.ias.config.IASStorage;
import net.fabricmc.loader.api.FabricLoader;
import rtx.kimiko.utils.session.SessionChanger;

public final class IasService {
    private static boolean initialized;

    private IasService() {
    }

    public static synchronized void ensureInitialized() {
        if (initialized) {
            return;
        }
        IAS.init(FabricLoader.getInstance().getGameDir(), FabricLoader.getInstance().getConfigDir());
        IasService.restoreLastAccount();
        initialized = true;
    }

    public static synchronized void close() {
        if (!initialized) {
            return;
        }
        IAS.close();
        initialized = false;
    }

    public static List<Account> accounts() {
        IasService.ensureInitialized();
        return IASStorage.ACCOUNTS;
    }

    public static void addOffline(String name) {
        IasService.ensureInitialized();
        IASStorage.ACCOUNTS.removeIf(account -> {
            OfflineAccount offline;
            return account instanceof OfflineAccount && (offline = (OfflineAccount)account).name().equalsIgnoreCase(name);
        });
        IASStorage.ACCOUNTS.add(new OfflineAccount(name, null));
        IAS.saveStorage();
    }

    public static void remove(Account account) {
        IasService.ensureInitialized();
        IASStorage.ACCOUNTS.remove(account);
        IAS.saveStorage();
        if (IASConfig.matchesLastAccount(account)) {
            IASConfig.clearLastAccount();
            IAS.saveConfig();
        }
    }

    public static void saveIfChanged(boolean changed) {
        if (changed) {
            IAS.saveStorage();
        }
    }

    public static void apply(LoginData data) {
        SessionChanger.applyLoginData(data);
    }

    public static void rememberLastAccount(Account account, boolean online) {
        IasService.ensureInitialized();
        IASConfig.rememberLastAccount(account, online);
        IAS.saveConfig();
    }

    public static void replaceRememberedAccount(Account original, Account replacement) {
        IasService.ensureInitialized();
        if (!IASConfig.matchesLastAccount(original)) {
            return;
        }
        IASConfig.rememberLastAccount(replacement, IASConfig.lastAccountOnline);
        IAS.saveConfig();
    }

    public static boolean isRememberedAccount(Account account) {
        IasService.ensureInitialized();
        return IASConfig.matchesLastAccount(account);
    }

    private static void restoreLastAccount() {
        if (!IASConfig.restoreLastAccount || IASConfig.lastAccountName == null || IASConfig.lastAccountUuid == null) {
            return;
        }
        final Account account = IASStorage.ACCOUNTS.stream().filter(IASConfig::matchesLastAccount).findFirst().orElse(null);
        if (account == null) {
            return;
        }
        if (!IASConfig.lastAccountOnline || account instanceof OfflineAccount || !account.canLogin()) {
            SessionChanger.applyLoginData(new LoginData(account.name(), OfflineAccount.uuid(account.name()), "ias:offline", false));
            return;
        }
        account.login(new LoginHandler(){

            @Override
            public boolean cancelled() {
                return false;
            }

            @Override
            public void stage(String stage, Object ... args) {
            }

            @Override
            public CompletableFuture<String> password() {
                return CompletableFuture.completedFuture(null);
            }

            @Override
            public void success(LoginData data, boolean changed) {
                if (data == null) {
                    return;
                }
                SessionChanger.applyLoginData(data);
                if (changed) {
                    IAS.saveStorage();
                }
                IasService.rememberLastAccount(account, true);
            }

            @Override
            public void error(Throwable error) {
            }
        });
    }
}

