/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.client.util.DefaultSkinHelper
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.widget.EntryListWidget$Entry
 *  net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget
 *  net.minecraft.entity.player.SkinTextures
 *  org.jetbrains.annotations.Nullable
 */
package mods.acountswiher.ru.vidtu.ias.screen;

import com.mojang.authlib.GameProfile;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import mods.acountswiher.IasService;
import mods.acountswiher.ru.vidtu.ias.IAS;
import mods.acountswiher.ru.vidtu.ias.account.Account;
import mods.acountswiher.ru.vidtu.ias.config.IASStorage;
import mods.acountswiher.ru.vidtu.ias.screen.AccountEntry;
import mods.acountswiher.ru.vidtu.ias.screen.AccountScreen;
import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.client.gui.widget.AlwaysSelectedEntryListWidget;
import net.minecraft.entity.player.SkinTextures;
import org.jetbrains.annotations.Nullable;

final class AccountList
extends AlwaysSelectedEntryListWidget<AccountEntry> {
    private static final Map<UUID, SkinTextures> SKINS = new WeakHashMap<UUID, SkinTextures>(4);
    private final AccountScreen screen;

    AccountList(AccountScreen screen, MinecraftClient client, int width, int height, int offset, int itemHeight) {
        super(client, width, height, offset, itemHeight);
        this.screen = screen;
        this.update(this.screen.search() != null ? this.screen.search().getText() : "");
    }

    public int getRowWidth() {
        return Math.min(super.getRowWidth(), this.screen.width - 190);
    }

    public void setSelected(@Nullable AccountEntry entry) {
        super.setSelected(entry);
        this.screen.updateSelected();
    }

    @Nullable
    public AccountEntry getSelected() {
        return (AccountEntry)super.getSelectedOrNull();
    }

    void update(String query) {
        AccountEntry selected = this.getSelected();
        if (query == null || query.isBlank()) {
            this.replaceEntries(IASStorage.ACCOUNTS.stream().map(account -> new AccountEntry(this.client, this, (Account)account)).toList());
        } else {
            String lowerQuery = query.toLowerCase(Locale.ROOT);
            this.replaceEntries(IASStorage.ACCOUNTS.stream().filter(account -> account.name().toLowerCase(Locale.ROOT).contains(lowerQuery)).sorted((first, second) -> Boolean.compare(second.name().toLowerCase(Locale.ROOT).startsWith(lowerQuery), first.name().toLowerCase(Locale.ROOT).startsWith(lowerQuery))).map(account -> new AccountEntry(this.client, this, (Account)account)).toList());
        }
        this.setSelected(this.wirst$restoreSelection(selected));
        this.screen.updateSelected();
    }

    void login(boolean online) {
        AccountEntry selected = this.getSelected();
        if (selected != null) {
            this.screen.login(selected.account(), online);
        }
    }

    void edit() {
        AccountEntry selected = this.getSelected();
        if (selected != null) {
            this.screen.openEdit(selected.account());
        }
    }

    void delete(boolean confirm) {
        AccountEntry selected = this.getSelected();
        if (selected != null) {
            this.screen.openDelete(selected.account(), confirm);
        }
    }

    void add() {
        this.screen.openAdd();
    }

    SkinTextures skin(AccountEntry entry) {
        UUID uuid = entry.account().skin();
        SkinTextures skin = SKINS.get(uuid);
        if (skin != null) {
            return skin;
        }
        skin = DefaultSkinHelper.getSkinTextures((UUID)uuid);
        SKINS.put(uuid, skin);
        if (uuid.version() != 4) {
            return skin;
        }
        GameProfile profile = new GameProfile(entry.account().skin(), entry.account().name());
        ((CompletableFuture)this.client.getSkinProvider().fetchSkinTextures(profile).thenAcceptAsync(optional -> optional.ifPresent(loaded -> SKINS.put(uuid, (SkinTextures)loaded)), (Executor)this.client)).exceptionally(throwable -> null);
        return skin;
    }

    void swapUp(AccountEntry entry) {
        int index = this.children().indexOf((Object)entry);
        if (index < 0 || index >= IASStorage.ACCOUNTS.size()) {
            return;
        }
        int upIndex = index - 1;
        if (upIndex < 0) {
            return;
        }
        IASStorage.ACCOUNTS.set(index, IASStorage.ACCOUNTS.get(upIndex));
        IASStorage.ACCOUNTS.set(upIndex, entry.account());
        this.saveStorage();
        this.children().set(index, (AccountEntry)((Object)this.children().get(upIndex)));
        this.children().set(upIndex, entry);
        this.setSelected(entry);
    }

    void swapDown(AccountEntry entry) {
        int index = this.children().indexOf((Object)entry);
        if (index < 0 || index >= IASStorage.ACCOUNTS.size()) {
            return;
        }
        int downIndex = index + 1;
        if (downIndex >= this.children().size() || downIndex >= IASStorage.ACCOUNTS.size()) {
            return;
        }
        IASStorage.ACCOUNTS.set(index, IASStorage.ACCOUNTS.get(downIndex));
        IASStorage.ACCOUNTS.set(downIndex, entry.account());
        this.saveStorage();
        this.children().set(index, (AccountEntry)((Object)this.children().get(downIndex)));
        this.children().set(downIndex, entry);
        this.setSelected(entry);
    }

    private void saveStorage() {
        try {
            IAS.disclaimersStorage();
            IAS.saveStorage();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private AccountEntry wirst$restoreSelection(@Nullable AccountEntry previous) {
        if (previous != null) {
            for (AccountEntry entry : this.children()) {
                if (!entry.equals((Object)previous)) continue;
                return entry;
            }
        }
        for (AccountEntry entry : this.children()) {
            if (!IasService.isRememberedAccount(entry.account())) continue;
            return entry;
        }
        return null;
    }
}

