/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mods.acountswiher.ru.vidtu.ias.account.MicrosoftAccount
 *  mods.acountswiher.ru.vidtu.ias.crypt.Crypt
 *  net.minecraft.util.Formatting
 *  net.minecraft.util.Util
 *  net.minecraft.util.Util$OperatingSystem
 *  net.minecraft.text.Text
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.Element
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.screen.ScreenTexts
 *  org.lwjgl.glfw.GLFW
 */
package mods.acountswiher.ru.vidtu.ias.screen;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import mods.acountswiher.ru.vidtu.ias.IAS;
import mods.acountswiher.ru.vidtu.ias.account.Account;
import mods.acountswiher.ru.vidtu.ias.account.MicrosoftAccount;
import mods.acountswiher.ru.vidtu.ias.auth.handlers.CreateHandler;
import mods.acountswiher.ru.vidtu.ias.auth.microsoft.MSAuthClient;
import mods.acountswiher.ru.vidtu.ias.auth.microsoft.MSAuthServer;
import mods.acountswiher.ru.vidtu.ias.auth.microsoft.fields.DeviceAuth;
import mods.acountswiher.ru.vidtu.ias.config.IASConfig;
import mods.acountswiher.ru.vidtu.ias.crypt.Crypt;
import mods.acountswiher.ru.vidtu.ias.crypt.PasswordCrypt;
import mods.acountswiher.ru.vidtu.ias.screen.AccountScreen;
import mods.acountswiher.ru.vidtu.ias.screen.MultiLineLabelCompat;
import mods.acountswiher.ru.vidtu.ias.screen.PopupBox;
import mods.acountswiher.ru.vidtu.ias.screen.PopupButton;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.screen.ScreenTexts;
import org.lwjgl.glfw.GLFW;
import recovery.privacy.NetworkPolicy;

final class MicrosoftPopupScreen
extends Screen
implements CreateHandler {
    private final AccountScreen parent;
    private final Account original;
    private final Object lock = new Object();
    private Crypt crypt;
    private MSAuthClient authClient;
    private MSAuthServer authServer;
    private Text stage = Text.translatable((String)"ias.login.initializing").copy().formatted(Formatting.YELLOW);
    private MultiLineLabelCompat label = MultiLineLabelCompat.EMPTY;
    private PopupBox password;
    private MultiLineLabelCompat passwordTip = MultiLineLabelCompat.EMPTY;
    private MultiLineLabelCompat errorNote = MultiLineLabelCompat.EMPTY;
    private float error = Float.NaN;

    MicrosoftPopupScreen(AccountScreen parent, Account original, Crypt crypt) {
        super((Text)Text.translatable((String)"ias.login"));
        this.parent = parent;
        this.original = original;
        this.crypt = crypt;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void init() {
        assert (this.client != null);
        this.clearChildren();
        this.parent.resize(this.width, this.height);
        Object object = this.lock;
        synchronized (object) {
            this.label = MultiLineLabelCompat.EMPTY;
        }
        this.addDrawableChild(new PopupButton(this.width / 2 - 75, this.height / 2 + 52, 150, 20, ScreenTexts.BACK, button -> this.close()));
        if (this.crypt == null) {
            this.password = new PopupBox(this.textRenderer, this.width / 2 - 100, this.height / 2 - 5, 178, 20, this.password, (Text)Text.translatable((String)"ias.password"), this::submitPassword, true);
            this.password.setMaxLength(32);
            this.password.setPlaceholder((Text)Text.translatable((String)"ias.password.hint").copy().formatted(Formatting.DARK_GRAY));
            this.addDrawableChild(this.password);
            PopupButton enter = new PopupButton(this.width / 2 + 80, this.height / 2 - 5, 20, 20, (Text)Text.literal((String)">>"), button -> this.submitPassword());
            enter.active = !this.password.getText().isBlank();
            this.password.setChangedListener(value -> {
                enter.active = !value.isBlank();
            });
            this.addDrawableChild(enter);
            this.passwordTip = MultiLineLabelCompat.create(this.textRenderer, (Text)Text.translatable((String)"ias.password.tip").copy().formatted(Formatting.YELLOW), 320);
            return;
        }
        this.password = null;
        this.passwordTip = MultiLineLabelCompat.EMPTY;
        if (this.authClient == null && this.authServer == null) {
            IAS.executor().execute(() -> {
                if (IASConfig.useServerAuth()) {
                    this.startServerAuth();
                } else {
                    this.startClientAuth();
                }
            });
        }
    }

    @Override
    public boolean cancelled() {
        return this.client == null || this.client.currentScreen != this;
    }

    @Override
    public void stage(String stage, Object ... args) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.execute(() -> {
            if (client.currentScreen != this) {
                return;
            }
            if ("ias.login.processing".equals(stage)) {
                try {
                    long handle = client.getWindow().getHandle();
                    GLFW.glfwRequestWindowAttention((long)handle);
                    GLFW.glfwFocusWindow((long)handle);
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
            }
            Object object = this.lock;
            synchronized (object) {
                this.stage = Text.translatable((String)stage, (Object[])args).copy().formatted(Formatting.YELLOW);
                this.label = MultiLineLabelCompat.EMPTY;
            }
        });
    }

    @Override
    public void success(MicrosoftAccount account) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.execute(() -> {
            if (client.currentScreen != this) {
                return;
            }
            this.parent.addOrReplace(this.original, (Account)account);
            this.close();
        });
    }

    @Override
    public void error(Throwable error) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.execute(() -> {
            if (client.currentScreen != this) {
                return;
            }
            Object object = this.lock;
            synchronized (object) {
                this.stage = Text.translatable((String)"ias.error").copy().formatted(Formatting.RED);
                this.label = MultiLineLabelCompat.EMPTY;
                this.error = 1.0f;
            }
        });
    }

    public void close() {
        if (this.client != null) {
            this.client.setScreen((Screen)this.parent);
        }
    }

    public void removed() {
        super.removed();
        IAS.executor().execute(() -> {
            if (this.authClient != null) {
                this.authClient.close();
                this.authClient = null;
            }
            if (this.authServer != null) {
                this.authServer.close();
                this.authServer = null;
            }
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        MultiLineLabelCompat currentLabel;
        super.render(context, mouseX, mouseY, delta);
        context.getMatrices().pushMatrix();
        context.getMatrices().scale(2.0f, 2.0f);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 4, this.height / 4 - 37, -1);
        context.getMatrices().popMatrix();
        if (this.crypt == null && this.password != null) {
            context.drawCenteredTextWithShadow(this.textRenderer, this.password.getMessage(), this.width / 2, this.height / 2 - 15, -1);
            this.passwordTip.renderCentered(context, this.width / 2, this.height / 2 + 22);
            return;
        }
        Object object = this.lock;
        synchronized (object) {
            if (this.label == MultiLineLabelCompat.EMPTY) {
                this.label = MultiLineLabelCompat.create(this.textRenderer, this.stage, 240);
            }
            currentLabel = this.label;
        }
        currentLabel.renderCentered(context, this.width / 2, (this.height - currentLabel.getLineCount() * 9) / 2 - 4);
        if (Float.isFinite(this.error)) {
            if (this.errorNote == MultiLineLabelCompat.EMPTY) {
                this.errorNote = MultiLineLabelCompat.create(this.textRenderer, (Text)Text.translatable((String)"ias.error.note").copy().formatted(Formatting.AQUA), 245);
            }
            this.errorNote.renderCentered(context, this.width / 2, this.height / 2 + 87);
        }
    }

    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        if (this.parent != null) {
            this.parent.renderWithTooltip(context, 0, 0, delta);
            context.fill(0, 0, this.width, this.height, Integer.MIN_VALUE);
        } else {
            super.renderBackground(context, mouseX, mouseY, delta);
        }
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        context.fill(centerX - 125, centerY - 75, centerX + 125, centerY + 75, -132112336);
        context.fill(centerX - 124, centerY - 76, centerX + 124, centerY - 75, -132112336);
        context.fill(centerX - 124, centerY + 75, centerX + 124, centerY + 76, -132112336);
    }

    private void submitPassword() {
        if (this.password == null) {
            return;
        }
        String value = this.password.getText();
        if (value.isBlank()) {
            return;
        }
        this.crypt = new PasswordCrypt(value);
        this.password = null;
        this.passwordTip = MultiLineLabelCompat.EMPTY;
        this.init();
    }

    private void startClientAuth() {
        if (this.crypt == null || this.authClient != null || this.authServer != null) {
            return;
        }
        try {
            this.authClient = new MSAuthClient(this.crypt, this);
            ((CompletableFuture)this.authClient.start().thenAcceptAsync(this::openDeviceAuth, (Executor)MinecraftClient.getInstance())).exceptionally(throwable -> {
                this.error(new RuntimeException("Unable to handle client auth.", (Throwable)throwable));
                return null;
            });
        }
        catch (Throwable throwable2) {
            this.error(new RuntimeException("Unable to create client auth.", throwable2));
        }
    }

    private void startServerAuth() {
        if (this.crypt == null || this.authClient != null || this.authServer != null) {
            return;
        }
        try {
            this.authServer = new MSAuthServer(Text.translatable((String)"ias.login.done").getString(), this.crypt, this);
            ((CompletableFuture)CompletableFuture.runAsync(this.authServer, IAS.executor()).thenRunAsync(() -> {
                if (this.authServer == null) {
                    return;
                }
                String url = this.authServer.authUrl();
                this.stage("ias.login.link", new Object[0]);
                MicrosoftPopupScreen.privacy$openAllowedLink(Util.getOperatingSystem(), url);
                if (this.client != null) {
                    this.client.keyboard.setClipboard(url);
                }
            }, (Executor)MinecraftClient.getInstance())).exceptionally(throwable -> {
                this.error(new RuntimeException("Unable to handle server auth.", (Throwable)throwable));
                return null;
            });
        }
        catch (Throwable throwable2) {
            this.error(new RuntimeException("Unable to create server auth.", throwable2));
        }
    }

    private void openDeviceAuth(DeviceAuth auth) {
        if (auth == null) {
            return;
        }
        this.stage("ias.login.linkClient", Text.literal((String)auth.uri().toString()).copy().formatted(Formatting.GOLD), Text.literal((String)auth.user()).copy().formatted(Formatting.GOLD));
        MicrosoftPopupScreen.privacy$openAllowedLink(Util.getOperatingSystem(), auth.uri().toString());
        if (this.client != null) {
            this.client.keyboard.setClipboard(auth.user());
        }
    }

    private static /* synthetic */ void privacy$openAllowedLink(Util.OperatingSystem operatingSystem2, String string) {
        if (NetworkPolicy.allowedLink(string)) {
            operatingSystem2.open(string);
        }
    }
}

