/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Formatting
 *  net.minecraft.text.Text
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.Element
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.screen.ScreenTexts
 */
package mods.acountswiher.ru.vidtu.ias.screen;

import java.util.concurrent.CompletableFuture;
import mods.acountswiher.IasService;
import mods.acountswiher.ru.vidtu.ias.IAS;
import mods.acountswiher.ru.vidtu.ias.account.Account;
import mods.acountswiher.ru.vidtu.ias.auth.LoginData;
import mods.acountswiher.ru.vidtu.ias.auth.handlers.LoginHandler;
import mods.acountswiher.ru.vidtu.ias.screen.AccountScreen;
import mods.acountswiher.ru.vidtu.ias.screen.MultiLineLabelCompat;
import mods.acountswiher.ru.vidtu.ias.screen.PopupBox;
import mods.acountswiher.ru.vidtu.ias.screen.PopupButton;
import net.minecraft.util.Formatting;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.screen.ScreenTexts;

final class LoginPopupScreen
extends Screen
implements LoginHandler {
    private final AccountScreen parent;
    private final Account account;
    private final Object lock = new Object();
    private Text stage = Text.translatable((String)"ias.login.initializing").copy().formatted(Formatting.YELLOW);
    private MultiLineLabelCompat label = MultiLineLabelCompat.EMPTY;
    private PopupBox password;
    private CompletableFuture<String> passwordFuture;
    private MultiLineLabelCompat passwordTip = MultiLineLabelCompat.EMPTY;
    private MultiLineLabelCompat errorNote = MultiLineLabelCompat.EMPTY;
    private float error = Float.NaN;
    private boolean started;

    LoginPopupScreen(AccountScreen parent, Account account) {
        super((Text)Text.translatable((String)"ias.login"));
        this.parent = parent;
        this.account = account;
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
        this.addDrawableChild(new PopupButton(this.width / 2 - 75, this.height / 2 + 52, 150, 20, ScreenTexts.CANCEL, button -> this.close()));
        if (this.passwordFuture != null) {
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
        } else {
            this.password = null;
            this.passwordTip = MultiLineLabelCompat.EMPTY;
        }
        if (!this.started) {
            this.started = true;
            IAS.executor().execute(() -> this.account.login(this));
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
            Object object = this.lock;
            synchronized (object) {
                this.stage = Text.translatable((String)stage, (Object[])args).copy().formatted(Formatting.YELLOW);
                this.label = MultiLineLabelCompat.EMPTY;
            }
        });
    }

    @Override
    public CompletableFuture<String> password() {
        if (this.passwordFuture != null) {
            return this.passwordFuture;
        }
        this.passwordFuture = new CompletableFuture();
        this.passwordFuture.whenComplete((value, throwable) -> {
            this.password = null;
            this.passwordTip = MultiLineLabelCompat.EMPTY;
            this.passwordFuture = null;
            if (this.client != null) {
                this.client.execute(this::init);
            }
        });
        if (this.client != null) {
            this.client.execute(this::init);
        }
        return this.passwordFuture;
    }

    @Override
    public void success(LoginData data, boolean changed) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.execute(() -> {
            if (client.currentScreen != this) {
                return;
            }
            if (data == null) {
                this.close();
                return;
            }
            IasService.rememberLastAccount(this.account, data.online());
            IasService.apply(data);
            IasService.saveIfChanged(changed);
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
        if (this.passwordFuture != null && !this.passwordFuture.isDone()) {
            this.passwordFuture.complete(null);
        }
        if (this.client != null) {
            this.client.setScreen((Screen)this.parent);
        }
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
        if (this.passwordFuture != null && this.password != null) {
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
        if (this.password == null || this.passwordFuture == null) {
            return;
        }
        String value = this.password.getText();
        if (value.isBlank()) {
            return;
        }
        this.passwordFuture.complete(value);
    }
}

