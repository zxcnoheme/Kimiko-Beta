/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  net.minecraft.client.input.KeyInput
 *  net.minecraft.text.Text
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.gui.Element
 *  net.minecraft.client.gui.screen.multiplayer.ConnectScreen
 *  net.minecraft.client.gui.widget.ButtonWidget
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.text.MutableText
 *  net.minecraft.text.StringVisitable
 *  net.minecraft.text.OrderedText
 *  net.minecraft.client.network.ServerAddress
 *  net.minecraft.client.network.ServerInfo
 *  net.minecraft.client.network.CookieStorage
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.vanilla;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import net.minecraft.client.input.KeyInput;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.MutableText;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.OrderedText;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.network.CookieStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.utils.network.FunTimeJoinGuard;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 A2\u00020\u0001:\u0002BAB3\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0014\u00a2\u0006\u0004\b\u000e\u0010\u000fJ/\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0015H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0019H\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\u001f\u0010\u000fJ\u000f\u0010 \u001a\u00020\rH\u0002\u00a2\u0006\u0004\b \u0010\u000fJ\u000f\u0010!\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b!\u0010\u000fR\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0002\u0010\"R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010#R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010$R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010%R\u0016\u0010\n\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\n\u0010&R\u0014\u0010(\u001a\u00020'8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0014\u0010+\u001a\u00020*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b+\u0010,R\u001a\u0010/\u001a\b\u0012\u0004\u0012\u00020.0-8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u00100R0\u00104\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u0002020-01j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002020-`38\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0016\u00106\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b6\u00107R\u0016\u00108\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b8\u00107R\u0016\u00109\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b9\u00107R\u0016\u0010:\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b:\u00107R\u0016\u0010;\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b;\u00107R\u0016\u0010<\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u00107R\u0016\u0010=\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b=\u00107R\u0016\u0010>\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u00107R\u0016\u0010?\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b?\u00107R\u0016\u0010@\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b@\u00107\u00a8\u0006C"}, d2={"Lrtx/kimiko/api/ui/vanilla/FunTimeWarningScreen;", "Lnet/minecraft/Screen;", "parent", "Lnet/minecraft/ServerAddress;", "serverAddress", "Lnet/minecraft/ServerInfo;", "serverData", "", "quickPlay", "Lnet/minecraft/CookieStorage;", "transferState", "<init>", "(Lnet/minecraft/Screen;Lnet/minecraft/ServerAddress;Lnet/minecraft/ServerInfo;ZLnet/minecraft/CookieStorage;)V", "", "init", "()V", "Lnet/minecraft/DrawContext;", "graphics", "", "mouseX", "mouseY", "", "partialTick", "render", "(Lnet/minecraft/DrawContext;IIF)V", "Lnet/minecraft/KeyInput;", "keyEvent", "keyPressed", "(Lnet/minecraft/KeyInput;)Z", "shouldCloseOnEsc", "()Z", "onClose", "leave", "proceed", "Lnet/minecraft/Screen;", "Lnet/minecraft/ServerAddress;", "Lnet/minecraft/ServerInfo;", "Z", "Lnet/minecraft/CookieStorage;", "", "address", "Ljava/lang/String;", "Lnet/minecraft/Text;", "subtitle", "Lnet/minecraft/Text;", "", "Lrtx/kimiko/api/ui/vanilla/FunTimeWarningScreen$Paragraph;", "paragraphs", "Ljava/util/List;", "Ljava/util/ArrayList;", "Lnet/minecraft/OrderedText;", "Lkotlin/collections/ArrayList;", "body", "Ljava/util/ArrayList;", "panelX", "I", "panelY", "panelWidth", "panelHeight", "titleY", "subtitleY", "addressY", "separatorY", "bodyY", "buttonsY", "Companion", "Paragraph", "rtx.kimiko:kimiko"})
public final class FunTimeWarningScreen
extends Screen {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Screen parent;
    @NotNull
    private final ServerAddress serverAddress;
    @Nullable
    private final ServerInfo serverData;
    private final boolean quickPlay;
    @Nullable
    private final CookieStorage transferState;
    @NotNull
    private final String address;
    @NotNull
    private final Text subtitle;
    @NotNull
    private final List<Paragraph> paragraphs;
    @NotNull
    private final ArrayList<List<OrderedText>> body;
    private int panelX;
    private int panelY;
    private int panelWidth;
    private int panelHeight;
    private int titleY;
    private int subtitleY;
    private int addressY;
    private int separatorY;
    private int bodyY;
    private int buttonsY;
    private static final int PANEL_MAX_WIDTH = 360;
    private static final int PANEL_PADDING = 18;
    private static final int ACCENT_BAR = 3;
    private static final int TITLE_TOP_GAP = 15;
    private static final int TITLE_HEIGHT = 15;
    private static final int TITLE_BOTTOM_GAP = 7;
    private static final int LINE_STEP = 11;
    private static final int PARAGRAPH_GAP = 7;
    private static final int SECTION_GAP = 10;
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_GAP = 8;
    private static final float TITLE_SCALE = 1.5f;
    private static final int SEPARATOR_COLOR = 0x30FFFFFF;
    private static final int TITLE_COLOR = -14756;
    private static final int SUBTITLE_COLOR = -855307;
    private static final int ADDRESS_COLOR = -7564384;
    private static final int BODY_COLOR = -3159082;
    private static final int DANGER_COLOR = -34202;
    private static final int MUTED_COLOR = -6646874;

    public FunTimeWarningScreen(@NotNull Screen parent, @NotNull ServerAddress serverAddress, @Nullable ServerInfo serverData, boolean quickPlay, @Nullable CookieStorage transferState) {
        super((Text)Text.literal("Warning"));
        this.parent = parent;
        this.serverAddress = serverAddress;
        this.serverData = serverData;
        this.quickPlay = quickPlay;
        this.transferState = transferState;
        this.body = new ArrayList();
        FunTimeJoinGuard.Match match = FunTimeJoinGuard.matched(this.serverAddress, this.serverData, this.transferState != null);
        String display = match == null ? I18n.tr("этому серверу") : match.display();
        this.address = match == null ? "" : match.address();
        this.subtitle = Text.literal(I18n.tr("Вы подключаетесь к серверу %s", display));
        this.paragraphs = List.of(
            new Paragraph(Text.literal(I18n.tr("За игру на %s с клиентом Kimiko вы можете получить бан.", display)), -34202),
            new Paragraph(Text.literal(I18n.tr("Сейчас администрация разрабатывает собственный API для клиентов. ") + I18n.tr("Как только его закончат, мы свяжемся с ними и получим одобрение.")), -3159082),
            new Paragraph(Text.literal(I18n.tr("Заявку мы уже подавали — нам ответили, что её не рассматривают.")), -3159082),
            new Paragraph(Text.literal(I18n.tr("Продолжая, вы играете на свой страх и риск.")), -6646874)
        );
    }

    protected void init() {
        int cursor;
        int panel = MathHelper.clamp((int)(this.width - 40), (int)200, (int)360);
        int contentWidth = panel - 36;
        this.body.clear();
        for (Paragraph paragraph : this.paragraphs) {
            this.body.add(this.textRenderer.wrapLines((StringVisitable)paragraph.getText(), contentWidth));
        }
        this.titleY = cursor = 18;
        this.subtitleY = cursor += 22;
        this.addressY = cursor += 11;
        this.separatorY = cursor += 21;
        this.bodyY = cursor += 11;
        Iterator<List<OrderedText>> iterator = this.body.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<List<OrderedText>> iterator2 = iterator;
        while (iterator2.hasNext()) {
            List<OrderedText> paragraph3 = (List<OrderedText>) (iterator2.next());
            cursor += paragraph3.size() * 11 + 7;
        }
        this.buttonsY = cursor += 10;
        this.panelWidth = panel;
        this.panelHeight = cursor += 38;
        this.panelX = (this.width - this.panelWidth) / 2;
        this.panelY = Math.min(Math.max(4, (this.height - this.panelHeight) / 2), this.height - this.panelHeight - 4);
        int n = Math.min(150, (contentWidth - 8) / 2);
        int buttonsX = this.panelX + (this.panelWidth - n * 2 - 8) / 2;
        int buttonsTop = this.panelY + this.buttonsY;
        this.addDrawableChild(ButtonWidget.builder((Text)((Text)Text.literal((String)I18n.tr("Выйти"))), arg_0 -> FunTimeWarningScreen.init$lambda$0(this, arg_0)).dimensions(buttonsX, buttonsTop, n, 20).build());
        this.addDrawableChild(ButtonWidget.builder((Text)((Text)Text.literal((String)I18n.tr("Продолжить"))), arg_0 -> FunTimeWarningScreen.init$lambda$1(this, arg_0)).dimensions(buttonsX + n + 8, buttonsTop, n, 20).build());
    }

    public void render(@NotNull DrawContext graphics, int mouseX, int mouseY, float partialTick) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        int left = this.panelX;
        int top = this.panelY;
        int centerX = left + this.panelWidth / 2;
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().scale(1.5f, 1.5f);
        graphics.drawCenteredTextWithShadow(this.textRenderer, this.title, MathKt.roundToInt((float)((float)centerX / 1.5f)), MathKt.roundToInt((float)((float)(top + this.titleY) / 1.5f)), -14756);
        graphics.getMatrices().popMatrix();
        graphics.drawCenteredTextWithShadow(this.textRenderer, this.subtitle, centerX, top + this.subtitleY, -855307);
        if (((CharSequence)this.address).length() > 0) {
            graphics.drawCenteredTextWithShadow(this.textRenderer, (Text)Text.literal((String)this.address), centerX, top + this.addressY, -7564384);
        }
        graphics.fill(left + 18, top + this.separatorY, left + this.panelWidth - 18, top + this.separatorY + 1, 0x30FFFFFF);
        int lineY = top + this.bodyY;
        int n = ((Collection)this.body).size();
        for (int i = 0; i < n; ++i) {
            int color = this.paragraphs.get(i).getColor();
            for (OrderedText line : this.body.get(i)) {
                graphics.drawCenteredTextWithShadow(this.textRenderer, line, centerX, lineY, color);
                lineY += 11;
            }
            lineY += 7;
        }
        super.render(graphics, mouseX, mouseY, partialTick);
    }

    public boolean keyPressed(@NotNull KeyInput keyEvent) {
        Intrinsics.checkNotNullParameter((Object)keyEvent, (String)"keyEvent");
        if (keyEvent.isEscape()) {
            this.leave();
            return true;
        }
        return super.keyPressed(keyEvent);
    }

    public boolean shouldCloseOnEsc() {
        return false;
    }

    public void close() {
        this.leave();
    }

    private final void leave() {
        MinecraftClient minecraftClient2 = this.client;
        Intrinsics.checkNotNull((Object)minecraftClient2);
        minecraftClient2.setScreen(this.parent);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void proceed() {
        FunTimeJoinGuard.bypass = true;
        try {
            ServerInfo serverInfo2 = this.serverData;
            Intrinsics.checkNotNull((Object)serverInfo2);
            ConnectScreen.connect((Screen)this.parent, (MinecraftClient)this.client, (ServerAddress)this.serverAddress, (ServerInfo)serverInfo2, (boolean)this.quickPlay, (CookieStorage)this.transferState);
        }
        finally {
            FunTimeJoinGuard.bypass = false;
        }
    }

    private static final void init$lambda$0(FunTimeWarningScreen this$0, ButtonWidget it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        this$0.leave();
    }

    private static final void init$lambda$1(FunTimeWarningScreen this$0, ButtonWidget it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        this$0.proceed();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u0007\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\u0006R\u0014\u0010\f\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u0006R\u0014\u0010\u000e\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0006R\u0014\u0010\u0010\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0006R\u0014\u0010\u0016\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0006R\u0014\u0010\u0017\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0006R\u0014\u0010\u0018\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0006R\u0014\u0010\u0019\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0006R\u0014\u0010\u001a\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0006R\u0014\u0010\u001b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0006\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/api/ui/vanilla/FunTimeWarningScreen.Companion;", "", "<init>", "()V", "", "PANEL_MAX_WIDTH", "I", "PANEL_PADDING", "ACCENT_BAR", "TITLE_TOP_GAP", "TITLE_HEIGHT", "TITLE_BOTTOM_GAP", "LINE_STEP", "PARAGRAPH_GAP", "SECTION_GAP", "BUTTON_WIDTH", "BUTTON_HEIGHT", "BUTTON_GAP", "", "TITLE_SCALE", "F", "SEPARATOR_COLOR", "TITLE_COLOR", "SUBTITLE_COLOR", "ADDRESS_COLOR", "BODY_COLOR", "DANGER_COLOR", "MUTED_COLOR", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/ui/vanilla/FunTimeWarningScreen$Paragraph;", "", "Lnet/minecraft/Text;", "text", "", "color", "<init>", "(Lnet/minecraft/Text;I)V", "Lnet/minecraft/Text;", "getText", "()Lnet/minecraft/Text;", "I", "getColor", "()I", "rtx.kimiko:kimiko"})
    private static final class Paragraph {
        @NotNull
        private final Text text;
        private final int color;

        public Paragraph(@NotNull Text text, int color) {
            Intrinsics.checkNotNullParameter((Object)text, (String)"text");
            this.text = text;
            this.color = color;
        }

        @NotNull
        public final Text getText() {
            return this.text;
        }

        public final int getColor() {
            return this.color;
        }
    }
}

