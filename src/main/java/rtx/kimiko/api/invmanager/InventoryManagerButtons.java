/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function0
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.input.AbstractInput
 *  net.minecraft.screen.ScreenHandler
 *  net.minecraft.text.Text
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.font.TextRenderer
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.widget.PressableWidget
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.client.gui.screen.ingame.HandledScreen
 *  net.minecraft.client.gui.screen.narration.NarrationMessageBuilder
 *  net.minecraft.util.math.ColorHelper
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.invmanager;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.input.AbstractInput;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.util.math.ColorHelper;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.invmanager.InventoryArranger;
import rtx.kimiko.api.invmanager.InventoryManagerScreen;
import rtx.kimiko.api.invmanager.InventoryTemplates;
import rtx.kimiko.api.lang.I18n;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J3\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u000b\u0010\fJ3\u0010\r\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\n\u00a2\u0006\u0004\b\r\u0010\fJ\u001f\u0010\u0011\u001a\u00020\u00102\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0003\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/api/invmanager/InventoryManagerButtons;", "", "<init>", "()V", "", "x", "y", "width", "height", "Lnet/minecraft/PressableWidget;", "Lkotlin/jvm/JvmStatic;", "createSetsButton", "(IIII)Lnet/minecraft/PressableWidget;", "createArrangeButton", "Lnet/minecraft/HandledScreen;", "screen", "", "tickArranger", "(Lnet/minecraft/HandledScreen;)V", "", "canArrange", "()Z", "triggerArrange", "VanillaTextButton", "rtx.kimiko:kimiko"})
public final class InventoryManagerButtons {
    @NotNull
    public static final InventoryManagerButtons INSTANCE = new InventoryManagerButtons();

    private InventoryManagerButtons() {
    }

    @JvmStatic
    @NotNull
    public static final PressableWidget createSetsButton(int x, int y, int width, int height) {
        return new VanillaTextButton(x, y, width, height, (Function0<String>)((Function0)InventoryManagerButtons::createSetsButton$lambda$0), (Function0<Boolean>)((Function0)InventoryManagerButtons::createSetsButton$lambda$1), (Function0<Unit>)((Function0)InventoryManagerButtons::createSetsButton$lambda$2));
    }

    @JvmStatic
    @NotNull
    public static final PressableWidget createArrangeButton(int x, int y, int width, int height) {
        return new VanillaTextButton(
            x, y, width, height,
            InventoryManagerButtons::createArrangeButton$lambda$0,
            () -> INSTANCE.canArrange(),
            () -> {
                INSTANCE.triggerArrange();
                return Unit.INSTANCE;
            }
        );
    }

    @JvmStatic
    public static final void tickArranger(@NotNull HandledScreen<?> screen) {
        Intrinsics.checkNotNullParameter(screen, (String)"screen");
        if (InventoryArranger.isActive()) {
            InventoryArranger.tick(screen, InventoryTemplates.active());
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean canArrange() {
        try {
            InventoryTemplates.Template template = InventoryTemplates.active();
            if (template == null) {
                return false;
            }
            if (InventoryArranger.isActive()) {
                return true;
            }
            Screen screen = MinecraftClient.getInstance().currentScreen;
            if (!(screen instanceof HandledScreen)) return false;
            ScreenHandler screenHandler2 = ((HandledScreen)screen).getScreenHandler();
            Intrinsics.checkNotNullExpressionValue((Object)screenHandler2, (String)"getMenu(...)");
            if (!InventoryArranger.needsArrange(screenHandler2, template)) return false;
            return true;
        }
        catch (Throwable failure) {
            return false;
        }
    }

    private final void triggerArrange() {
        Screen screen = MinecraftClient.getInstance().currentScreen;
        if (screen instanceof HandledScreen) {
            InventoryArranger.start((HandledScreen)screen);
        }
    }

    private static final String createSetsButton$lambda$0() {
        return I18n.tr("Сеты");
    }

    private static final boolean createSetsButton$lambda$1() {
        return true;
    }

    private static final Unit createSetsButton$lambda$2() {
        MinecraftClient.getInstance().setScreen((Screen)new InventoryManagerScreen());
        return Unit.INSTANCE;
    }

    private static final String createArrangeButton$lambda$0() {
        return InventoryArranger.isActive() ? I18n.tr("Раскладываю…") : I18n.tr("Разложить");
    }

    public static final /* synthetic */ boolean access$canArrange(InventoryManagerButtons $this) {
        return $this.canArrange();
    }

    public static final /* synthetic */ void access$triggerArrange(InventoryManagerButtons $this) {
        $this.triggerArrange();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u0007\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u0007\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013J/\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0018H\u0014\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001cH\u0014\u00a2\u0006\u0004\b\u001e\u0010\u001fR\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010 R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010 R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\r\u0010 \u00a8\u0006!"}, d2={"Lrtx/kimiko/api/invmanager/InventoryManagerButtons$VanillaTextButton;", "Lnet/minecraft/PressableWidget;", "", "x", "y", "width", "height", "Lkotlin/Function0;", "", "label", "", "activeState", "", "action", "<init>", "(IIIILkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "Lnet/minecraft/AbstractInput;", "input", "onPress", "(Lnet/minecraft/AbstractInput;)V", "Lnet/minecraft/DrawContext;", "graphics", "mouseX", "mouseY", "", "partialTick", "renderContents", "(Lnet/minecraft/DrawContext;IIF)V", "Lnet/minecraft/NarrationMessageBuilder;", "output", "updateWidgetNarration", "(Lnet/minecraft/NarrationMessageBuilder;)V", "Lkotlin/jvm/functions/Function0;", "rtx.kimiko:kimiko"})
    private static final class VanillaTextButton
    extends PressableWidget {
        @NotNull
        private final Function0<String> label;
        @NotNull
        private final Function0<Boolean> activeState;
        @NotNull
        private final Function0<Unit> action;

        public VanillaTextButton(int x, int y, int width, int height, @NotNull Function0<String> label, @NotNull Function0<Boolean> activeState, @NotNull Function0<Unit> action) {
            super(x, y, width, height, (Text)Text.empty());
            this.label = label;
            this.activeState = activeState;
            this.action = action;
        }

        public void onPress(@NotNull AbstractInput input) {
            Intrinsics.checkNotNullParameter((Object)input, (String)"input");
            if (this.active) {
                this.action.invoke();
            }
        }

        protected void drawIcon(@NotNull DrawContext graphics, int mouseX, int mouseY, float partialTick) {
            String string;
            Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
            try {
                this.active = (Boolean)this.activeState.invoke();
                string = (String)this.label.invoke();
            }
            catch (Throwable failure) {
                this.active = false;
                string = "";
            }
            String text = string;
            this.drawButton(graphics);
            TextRenderer textRenderer2 = MinecraftClient.getInstance().textRenderer;
            Intrinsics.checkNotNullExpressionValue((Object)textRenderer2, (String)"font");
            TextRenderer font = textRenderer2;
            int textX = this.getX() + (this.getWidth() - font.getWidth(text)) / 2;
            int textY = this.getY() + (this.getHeight() - font.fontHeight) / 2 + 1;
            graphics.drawTextWithShadow(font, text, textX, textY, ColorHelper.withAlpha((int)(this.active ? 255 : 160), (int)0xFFFFFF));
        }

        protected void appendClickableNarrations(@NotNull NarrationMessageBuilder output) {
            Intrinsics.checkNotNullParameter((Object)output, (String)"output");
        }
    }
}

