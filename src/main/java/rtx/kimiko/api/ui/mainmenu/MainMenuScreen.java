/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.client.input.CharInput
 *  net.minecraft.client.input.KeyInput
 *  net.minecraft.client.gui.Click
 *  net.minecraft.text.Text
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.text.MutableText
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  rtx.kimiko.api.ui.mainmenu.pages.NewsPage
 *  rtx.kimiko.api.ui.mainmenu.pages.SettingsPage
 */
package rtx.kimiko.api.ui.mainmenu;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.client.input.CharInput;
import net.minecraft.client.input.KeyInput;
import net.minecraft.client.gui.Click;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.MutableText;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.ui.BaseScreen;
import rtx.kimiko.api.ui.mainmenu.MainMenuTab;
import rtx.kimiko.api.ui.mainmenu.MenuBackdrop;
import rtx.kimiko.api.ui.mainmenu.MenuDock;
import rtx.kimiko.api.ui.mainmenu.MenuFooter;
import rtx.kimiko.api.ui.mainmenu.MenuHeader;
import rtx.kimiko.api.ui.mainmenu.MenuPage;
import rtx.kimiko.api.ui.mainmenu.MenuRipples;
import rtx.kimiko.api.ui.mainmenu.MenuSettings;
import rtx.kimiko.api.ui.mainmenu.MenuStats;
import rtx.kimiko.api.ui.mainmenu.MenuTheme;
import rtx.kimiko.api.ui.mainmenu.pages.AccountsPage;
import rtx.kimiko.api.ui.mainmenu.pages.BackgroundsPage;
import rtx.kimiko.api.ui.mainmenu.pages.CosmeticsPage;
import rtx.kimiko.api.ui.mainmenu.pages.NewsPage;
import rtx.kimiko.api.ui.mainmenu.pages.PlayPage;
import rtx.kimiko.api.ui.mainmenu.pages.ProfilePage;
import rtx.kimiko.api.ui.mainmenu.pages.SettingsPage;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u0000 E2\u00020\u0001:\u0001EB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0014\u00a2\u0006\u0004\b\b\u0010\u0003J\u000f\u0010\t\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\t\u0010\u0003J/\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0014\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0017\u001a\u00020\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u0019H\u0016\u00a2\u0006\u0004\b\u001f\u0010 J\u0017\u0010!\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001cH\u0016\u00a2\u0006\u0004\b!\u0010\"J/\u0010&\u001a\u00020\u00192\u0006\u0010\r\u001a\u00020#2\u0006\u0010\u000e\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#H\u0016\u00a2\u0006\u0004\b&\u0010'J\u0017\u0010)\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020(H\u0016\u00a2\u0006\u0004\b)\u0010*J\u0017\u0010,\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020+H\u0016\u00a2\u0006\u0004\b,\u0010-J\u000f\u0010.\u001a\u00020\u0019H\u0016\u00a2\u0006\u0004\b.\u0010\u001bR \u00101\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002000/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b1\u00102R\u0014\u00104\u001a\u0002038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0014\u00107\u001a\u0002068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b7\u00108R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0005\u0010<R\u0018\u0010=\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b=\u0010<R\u0016\u0010>\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0016\u0010@\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b@\u0010?R\u0016\u0010A\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bA\u0010?R\u0016\u0010C\u001a\u00020B8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bC\u0010D\u00a8\u0006F"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MainMenuScreen;", "Lrtx/kimiko/api/ui/BaseScreen;", "<init>", "()V", "Lrtx/kimiko/api/ui/mainmenu/MainMenuTab;", "tab", "()Lrtx/kimiko/api/ui/mainmenu/MainMenuTab;", "", "init", "onClose", "Lnet/minecraft/DrawContext;", "graphics", "", "mouseX", "mouseY", "", "partialTick", "renderScreen", "(Lnet/minecraft/DrawContext;IIF)V", "dt", "updatePageTransition", "(F)V", "next", "select", "(Lrtx/kimiko/api/ui/mainmenu/MainMenuTab;)V", "", "interactive", "()Z", "Lnet/minecraft/Click;", "event", "doubleClick", "mouseClicked", "(Lnet/minecraft/Click;Z)Z", "mouseReleased", "(Lnet/minecraft/Click;)Z", "", "horizontal", "vertical", "mouseScrolled", "(DDDD)Z", "Lnet/minecraft/KeyInput;", "keyPressed", "(Lnet/minecraft/KeyInput;)Z", "Lnet/minecraft/CharInput;", "charTyped", "(Lnet/minecraft/CharInput;)Z", "isPauseScreen", "", "Lrtx/kimiko/api/ui/mainmenu/MenuPage;", "pages", "Ljava/util/Map;", "Lrtx/kimiko/api/ui/mainmenu/MenuDock;", "dock", "Lrtx/kimiko/api/ui/mainmenu/MenuDock;", "Lrtx/kimiko/api/ui/mainmenu/MenuHeader;", "header", "Lrtx/kimiko/api/ui/mainmenu/MenuHeader;", "Lrtx/kimiko/api/ui/mainmenu/MenuFooter;", "footer", "Lrtx/kimiko/api/ui/mainmenu/MenuFooter;", "Lrtx/kimiko/api/ui/mainmenu/MainMenuTab;", "pendingTab", "appear", "F", "pageAlpha", "pageAppear", "", "lastNanos", "J", "Companion", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nMainMenuScreen.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MainMenuScreen.kt\nrtx/kimiko/api/ui/mainmenu/MainMenuScreen\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,233:1\n37#2,2:234\n37#2,2:236\n*S KotlinDebug\n*F\n+ 1 MainMenuScreen.kt\nrtx/kimiko/api/ui/mainmenu/MainMenuScreen\n*L\n181#1:234,2\n189#1:236,2\n*E\n"})
public final class MainMenuScreen
extends BaseScreen {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Map<MainMenuTab, MenuPage> pages;
    @NotNull
    private final MenuDock dock;
    @NotNull
    private final MenuHeader header;
    @NotNull
    private final MenuFooter footer;
    @NotNull
    private MainMenuTab tab;
    @Nullable
    private MainMenuTab pendingTab;
    private float appear;
    private float pageAlpha;
    private float pageAppear;
    private long lastNanos;
    private static final float INTRO_SECONDS = 0.72f;
    private static final float PAGE_FADE_SECONDS = 0.13f;
    @Nullable
    private static MainMenuScreen instance;

    private MainMenuScreen() {
        super((Text)Text.literal("Kimiko"));
        this.pages = new EnumMap(MainMenuTab.class);
        this.dock = new MenuDock();
        this.header = new MenuHeader();
        this.footer = new MenuFooter();
        this.tab = MainMenuTab.PLAY;
        this.pageAlpha = 1.0f;
        this.pages.put(MainMenuTab.PLAY, new PlayPage());
        this.pages.put(MainMenuTab.BACKGROUNDS, new BackgroundsPage());
        this.pages.put(MainMenuTab.COSMETICS, new CosmeticsPage());
        this.pages.put(MainMenuTab.PROFILE, new ProfilePage());
        this.pages.put(MainMenuTab.ACCOUNTS, new AccountsPage());
        this.pages.put(MainMenuTab.NEWS, (MenuPage)new NewsPage());
        this.pages.put(MainMenuTab.SETTINGS, (MenuPage)new SettingsPage());
        this.tab = MainMenuTab.Companion.of(MenuSettings.lastTab());
    }

    @NotNull
    public final MainMenuTab tab() {
        return this.tab;
    }

    protected void init() {
        block1: {
            this.lastNanos = System.nanoTime();
            if (!MenuSettings.intro()) {
                this.appear = 1.0f;
            }
            this.pageAlpha = 1.0f;
            this.pageAppear = this.appear;
            MenuStats.touch();
            MenuPage menuPage = this.pages.get((Object)this.tab);
            if (menuPage == null) break block1;
            menuPage.onShow();
        }
    }

    public void close() {
        MenuSettings.setLastTab(this.tab.name());
        MenuSettings.save();
        super.close();
    }

    @Override
    protected void renderScreen(@NotNull DrawContext graphics, int mouseX, int mouseY, float partialTick) {
        boolean scaled;
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        long now = System.nanoTime();
        float dt = Math.min(0.1f, (float)(now - this.lastNanos) / 1.0E9f);
        this.lastNanos = now;
        float w = Position.Companion.screenWidth();
        float h = Position.Companion.screenHeight();
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        MenuTheme.updateMetrics(h);
        this.appear = Math.min(1.0f, this.appear + dt / 0.72f);
        float intro = MenuTheme.ease(this.appear);
        MenuBackdrop.render(w, h, mx, my, dt, 1.0f, intro);
        float zoom = 1.0f + (1.0f - intro) * 0.035f;
        boolean bl = scaled = zoom > 1.0005f;
        if (scaled) {
            graphics.getMatrices().pushMatrix();
            graphics.getMatrices().translate(w * 0.5f, h * 0.5f);
            graphics.getMatrices().scale(zoom, zoom);
            graphics.getMatrices().translate(-w * 0.5f, -h * 0.5f);
        }
        float pad = MenuTheme.PAD;
        float railX = pad * 0.35f;
        float headerX = railX + MenuTheme.RAIL_W + pad * 0.5f;
        float headerW = w - headerX - pad;
        this.header.renderBrand(headerX, pad * 0.35f, MenuTheme.TOP_H, 1.0f, this.appear);
        this.dock.layout(railX, MenuTheme.TOP_H + pad * 0.5f, h - MenuTheme.TOP_H - pad * 1.5f);
        this.dock.render(this.tab, mx, my, dt, 1.0f, this.appear);
        this.updatePageTransition(dt);
        MenuRipples.render(dt, intro);
        if (scaled) {
            graphics.getMatrices().popMatrix();
        }
    }

    private final void updatePageTransition(float dt) {
        MainMenuTab pending = this.pendingTab;
        if (pending != null) {
            this.pageAlpha -= dt / 0.13f;
            if (this.pageAlpha <= 0.0f) {
                this.pageAlpha = 0.0f;
                MenuPage menuPage = this.pages.get((Object)this.tab);
                if (menuPage != null) {
                    menuPage.onHide();
                }
                this.tab = pending;
                this.pendingTab = null;
                this.pageAppear = 0.0f;
                MenuPage menuPage2 = this.pages.get((Object)this.tab);
                if (menuPage2 != null) {
                    menuPage2.onShow();
                }
                MenuSettings.setLastTab(this.tab.name());
                MenuSettings.save();
            }
            return;
        }
        this.pageAlpha = Math.min(1.0f, this.pageAlpha + dt / 0.13f);
        this.pageAppear = Math.min(1.0f, this.pageAppear + dt / 0.46f);
    }

    public final void select(@Nullable MainMenuTab next) {
        if (next == null || next == this.tab || next == this.pendingTab) {
            return;
        }
        this.pendingTab = next;
        Sounds.play("select_category");
    }

    private final boolean interactive() {
        return this.appear > 0.92f;
    }

    public boolean mouseClicked(@NotNull Click event, boolean doubleClick) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!this.interactive()) {
            return true;
        }
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        if (event.button() == 0) {
            MainMenuTab hit = this.dock.hit(mx, my);
            MenuRipples.spawn(mx, my, hit != null);
            if (hit != null) {
                this.select(hit);
                return true;
            }
            if (this.dock.inExit(mx, my)) {
                Sounds.play("gui_close");
                MinecraftClient.getInstance().scheduleStop();
                return true;
            }
        }
        return super.mouseClicked(event, doubleClick);
    }

    public boolean mouseReleased(@NotNull Click event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        return super.mouseReleased(event);
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double horizontal, double vertical) {
        if (!this.interactive()) {
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, horizontal, vertical);
    }

    public boolean keyPressed(@NotNull KeyInput event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!this.interactive()) {
            return true;
        }
        int key = event.key();
        if (key >= 49 && key <= 55) {
            MainMenuTab[] tabs = MainMenuTab.values();
            int index = key - 49;
            if (index < tabs.length) {
                this.select(tabs[index]);
                return true;
            }
        }
        if (key == 258) {
            MainMenuTab[] tabs = MainMenuTab.values();
            int index = 0;
            int n = tabs.length;
            for (int i = 0; i < n; ++i) {
                if (tabs[i] != this.tab) continue;
                index = i;
                break;
            }
            this.select(tabs[(index + 1) % tabs.length]);
            return true;
        }
        if (key == 256) {
            return true;
        }
        return super.keyPressed(event);
    }

    public boolean charTyped(@NotNull CharInput event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        return super.charTyped(event);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @JvmStatic
    @NotNull
    public static final MainMenuScreen instance() {
        return Companion.instance();
    }

    @JvmStatic
    public static final boolean isOpen() {
        return Companion.isOpen();
    }

    public /* synthetic */ MainMenuScreen(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\rR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u000f\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MainMenuScreen.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/ui/mainmenu/MainMenuScreen;", "Lkotlin/jvm/JvmStatic;", "instance", "()Lrtx/kimiko/api/ui/mainmenu/MainMenuScreen;", "", "isOpen", "()Z", "", "INTRO_SECONDS", "F", "PAGE_FADE_SECONDS", "Lrtx/kimiko/api/ui/mainmenu/MainMenuScreen;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final MainMenuScreen instance() {
            if (instance == null) {
                instance = new MainMenuScreen(null);
            }
            MainMenuScreen mainMenuScreen = instance;
            Intrinsics.checkNotNull((Object)((Object)mainMenuScreen));
            return mainMenuScreen;
        }

        @JvmStatic
        public final boolean isOpen() {
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient minecraft = minecraftClient2;
            return minecraft.currentScreen instanceof MainMenuScreen;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

