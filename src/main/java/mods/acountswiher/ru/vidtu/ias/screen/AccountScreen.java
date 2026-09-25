/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.util.DefaultSkinHelper
 *  net.minecraft.client.input.KeyInput
 *  net.minecraft.util.Formatting
 *  net.minecraft.util.Util
 *  net.minecraft.text.Text
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.widget.TextFieldWidget
 *  net.minecraft.client.gui.Element
 *  net.minecraft.client.gui.screen.NoticeScreen
 *  net.minecraft.client.gui.screen.multiplayer.ConnectScreen
 *  net.minecraft.client.gui.widget.ButtonWidget
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.screen.ScreenTexts
 *  net.minecraft.client.network.ServerAddress
 *  net.minecraft.client.network.ServerInfo
 *  net.minecraft.client.network.ServerInfo$ServerType
 *  net.minecraft.client.gui.tooltip.Tooltip
 *  net.minecraft.client.gui.widget.PlayerSkinWidget
 */
package mods.acountswiher.ru.vidtu.ias.screen;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import mods.acountswiher.IasService;
import mods.acountswiher.ru.vidtu.ias.IAS;
import mods.acountswiher.ru.vidtu.ias.account.Account;
import mods.acountswiher.ru.vidtu.ias.account.OfflineAccount;
import mods.acountswiher.ru.vidtu.ias.auth.LoginData;
import mods.acountswiher.ru.vidtu.ias.config.IASServerShortcutsConfig;
import mods.acountswiher.ru.vidtu.ias.config.IASStorage;
import mods.acountswiher.ru.vidtu.ias.screen.AccountEntry;
import mods.acountswiher.ru.vidtu.ias.screen.AccountList;
import mods.acountswiher.ru.vidtu.ias.screen.AddPopupScreen;
import mods.acountswiher.ru.vidtu.ias.screen.DeletePopupScreen;
import mods.acountswiher.ru.vidtu.ias.screen.LoginPopupScreen;
import mods.acountswiher.ru.vidtu.ias.screen.ServerShortcutButton;
import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.client.input.KeyInput;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.NoticeScreen;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.PlayerSkinWidget;

public final class AccountScreen
extends Screen {
    private final Screen parent;
    private final List<ServerShortcut> serverShortcuts = new ArrayList<ServerShortcut>();
    private TextFieldWidget search;
    private AccountList list;
    private PlayerSkinWidget skin;
    private ButtonWidget login;
    private ButtonWidget offlineLogin;
    private ButtonWidget edit;
    private ButtonWidget delete;

    public AccountScreen(Screen parent) {
        super((Text)Text.translatable((String)"ias.accounts"));
        this.parent = parent;
    }

    protected void init() {
        assert (this.client != null);
        if (IAS.disabled()) {
            this.client.setScreen((Screen)new NoticeScreen(this::close, (Text)Text.translatable((String)"ias.disabled.title").copy().formatted(Formatting.RED), (Text)Text.translatable((String)"ias.disabled.text"), ScreenTexts.BACK, true));
            return;
        }
        if (!IASStorage.gameDisclaimerShown) {
            this.client.setScreen((Screen)new NoticeScreen(() -> {
                try {
                    IAS.gameDisclaimerShownStorage();
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
                this.client.setScreen((Screen)this);
            }, (Text)Text.translatable((String)"ias.disclaimer.title").copy().formatted(Formatting.YELLOW), (Text)Text.translatable((String)"ias.disclaimer.text"), ScreenTexts.CONTINUE, false));
            return;
        }
        this.search = (TextFieldWidget)this.addDrawableChild(new TextFieldWidget(this.textRenderer, this.width / 2 - 75, 11, 150, 20, this.search, (Text)Text.translatable((String)"ias.accounts.search")));
        this.search.setPlaceholder((Text)Text.translatable((String)"ias.accounts.search").copy().formatted(Formatting.DARK_GRAY));
        this.ensureServerShortcuts();
        this.addServerShortcutButtons();
        if (this.skin == null) {
            this.skin = new PlayerSkinWidget(85, 120, this.client.getLoadedEntityModels(), () -> {
                AccountEntry selected = this.list != null ? this.list.getSelected() : null;
                return selected != null ? this.list.skin(selected) : DefaultSkinHelper.getSkinTextures((UUID)Util.NIL_UUID);
            });
        }
        this.skin.setX(5);
        this.skin.setY(this.height / 2 - 60);
        this.addDrawableChild(this.skin);
        this.login = (ButtonWidget)this.addDrawableChild(ButtonWidget.builder((Text)Text.translatable((String)"ias.accounts.login"), button -> this.list.login(true)).dimensions(this.width / 2 - 154, this.height - 48, 100, 20).build());
        this.offlineLogin = (ButtonWidget)this.addDrawableChild(ButtonWidget.builder((Text)Text.translatable((String)"ias.accounts.offlineLogin"), button -> this.list.login(false)).dimensions(this.width / 2 - 154, this.height - 24, 100, 20).build());
        this.edit = (ButtonWidget)this.addDrawableChild(ButtonWidget.builder((Text)Text.translatable((String)"ias.accounts.edit"), button -> this.list.edit()).dimensions(this.width / 2 - 50, this.height - 48, 100, 20).build());
        this.delete = (ButtonWidget)this.addDrawableChild(ButtonWidget.builder((Text)Text.translatable((String)"ias.accounts.delete"), button -> this.list.delete(!MinecraftClient.getInstance().isShiftPressed())).dimensions(this.width / 2 - 50, this.height - 24, 100, 20).build());
        this.addDrawableChild(ButtonWidget.builder((Text)Text.translatable((String)"ias.accounts.add"), button -> this.list.add()).dimensions(this.width / 2 + 54, this.height - 48, 100, 20).build());
        this.addDrawableChild(ButtonWidget.builder((Text)ScreenTexts.BACK, button -> this.close()).dimensions(this.width / 2 + 54, this.height - 24, 100, 20).build());
        this.list = new AccountList(this, this.client, this.width, this.height - 24 - 24 - 4 - 34, 34, 12);
        this.addDrawableChild(this.list);
        this.search.setChangedListener(this.list::update);
        this.list.update(this.search.getText());
        this.updateSelected();
    }

    public void close() {
        assert (this.client != null);
        this.client.setScreen(this.parent);
    }

    public void removed() {
        super.removed();
        this.serverShortcuts.clear();
    }

    public boolean keyPressed(KeyInput event) {
        AccountEntry selected;
        int keyCode = event.key();
        int scanCode = event.scancode();
        int modifiers = event.modifiers();
        boolean shift = MinecraftClient.getInstance().isShiftPressed();
        boolean control = MinecraftClient.getInstance().isCtrlPressed();
        if (keyCode == 264 && shift || keyCode == 267) {
            this.list.swapDown(this.list.getSelected());
            return true;
        }
        if (keyCode == 265 && shift || keyCode == 266) {
            this.list.swapUp(this.list.getSelected());
            return true;
        }
        if (keyCode == 67 && control && (selected = this.list.getSelected()) != null && this.client != null) {
            Account account = selected.account();
            this.client.keyboard.setClipboard(shift ? account.uuid().toString() : account.name());
            return true;
        }
        if (super.keyPressed(event)) {
            return true;
        }
        if (keyCode == 257 || keyCode == 335) {
            this.list.login(!shift);
            return true;
        }
        if (keyCode == 261 || keyCode == 333) {
            this.list.delete(!shift);
            return true;
        }
        if (keyCode == 78 && control || keyCode == 334) {
            this.list.add();
            return true;
        }
        if (keyCode == 82 && control || keyCode == 332) {
            this.list.edit();
            return true;
        }
        return false;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 1, -1);
    }

    TextFieldWidget search() {
        return this.search;
    }

    void refresh() {
        this.list.update(this.search != null ? this.search.getText() : "");
        this.updateSelected();
    }

    void addOrReplace(Account original, Account replacement) {
        List<Account> accounts = IasService.accounts();
        int index = original != null ? accounts.indexOf(original) : -1;
        accounts.removeIf(Predicate.isEqual(replacement));
        if (original != null) {
            accounts.remove(original);
        }
        if (index >= 0 && index <= accounts.size()) {
            accounts.add(index, replacement);
        } else {
            accounts.add(replacement);
        }
        IasService.replaceRememberedAccount(original, replacement);
        IAS.saveStorage();
        this.refresh();
    }

    void login(Account account, boolean online) {
        if (!online || account instanceof OfflineAccount || !account.canLogin()) {
            IasService.rememberLastAccount(account, false);
            IasService.apply(new LoginData(account.name(), OfflineAccount.uuid(account.name()), "ias:offline", false));
            return;
        }
        assert (this.client != null);
        this.client.setScreen((Screen)new LoginPopupScreen(this, account));
    }

    void openEdit(Account account) {
        assert (this.client != null);
        this.client.setScreen((Screen)new AddPopupScreen(this, true, account));
    }

    void openDelete(Account account, boolean confirm) {
        if (!confirm) {
            IasService.remove(account);
            this.refresh();
            return;
        }
        assert (this.client != null);
        this.client.setScreen((Screen)new DeletePopupScreen(this, account));
    }

    void openAdd() {
        assert (this.client != null);
        this.client.setScreen((Screen)new AddPopupScreen(this, false, null));
    }

    void updateSelected() {
        AccountEntry selected;
        AccountEntry accountEntry = selected = this.list != null ? this.list.getSelected() : null;
        if (selected == null) {
            this.login.active = false;
            this.offlineLogin.active = false;
            this.edit.active = false;
            this.delete.active = false;
            this.login.setTooltip(null);
            this.skin.visible = false;
            return;
        }
        this.offlineLogin.active = true;
        this.edit.active = true;
        this.delete.active = true;
        if (selected.account().canLogin()) {
            this.login.active = true;
            this.login.setTooltip(null);
        } else {
            this.login.active = false;
            this.login.setTooltip(Tooltip.of((Text)Text.translatable((String)"ias.accounts.login.offline")));
            this.login.setTooltipDelay(Duration.ZERO);
        }
        this.skin.visible = true;
    }

    private void addServerShortcutButtons() {
        int startX = 8;
        int y = 8;
        int size = 18;
        int gap = 2;
        for (int i = 0; i < this.serverShortcuts.size(); ++i) {
            ServerShortcut shortcut = this.serverShortcuts.get(i);
            ServerShortcutButton shortcutButton = new ServerShortcutButton(startX + i * (size + gap), y, shortcut::textureId, shortcut.name(), pressedButton -> this.connectToShortcut(shortcut));
            shortcutButton.setTooltip(Tooltip.of((Text)Text.literal((String)shortcut.name())));
            shortcutButton.setTooltipDelay(Duration.ZERO);
            this.addDrawableChild(shortcutButton);
        }
    }

    private void ensureServerShortcuts() {
        if (!this.serverShortcuts.isEmpty()) {
            return;
        }
        for (IASServerShortcutsConfig.ShortcutEntry spec : IASServerShortcutsConfig.load(IAS.configDirectory())) {
            ServerShortcut shortcut = new ServerShortcut(spec.name(), spec.address(), this.textureId(spec.icon()));
            this.serverShortcuts.add(shortcut);
        }
    }

    private Identifier textureId(String icon) {
        return Identifier.of((String)"ias", (String)("textures/gui/server_shortcuts/" + icon + ".png"));
    }

    private void connectToShortcut(ServerShortcut shortcut) {
        assert (this.client != null);
        ServerInfo serverInfo = new ServerInfo(shortcut.name(), shortcut.address(), ServerInfo.ServerType.OTHER);
        try {
            ConnectScreen.connect((Screen)this, (MinecraftClient)this.client, (ServerAddress)ServerAddress.parse((String)shortcut.address()), (ServerInfo)serverInfo, (boolean)false, null);
        }
        catch (Throwable throwable) {
            this.client.setScreen((Screen)new NoticeScreen(() -> this.client.setScreen((Screen)this), (Text)Text.literal((String)shortcut.name()).copy().formatted(Formatting.RED), (Text)Text.translatable((String)"multiplayer.status.cannot_connect"), ScreenTexts.BACK, true));
        }
    }

    private static final class ServerShortcut {
        private final String name;
        private final String address;
        private final Identifier textureId;

        private ServerShortcut(String name, String address, Identifier textureId) {
            this.name = name;
            this.address = address;
            this.textureId = textureId;
        }

        private String name() {
            return this.name;
        }

        private String address() {
            return this.address;
        }

        private Identifier textureId() {
            return this.textureId;
        }
    }
}

