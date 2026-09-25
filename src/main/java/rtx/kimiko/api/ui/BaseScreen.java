/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.text.Text
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.screen.Screen
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.modules.post.guilayerblur.GuiLayerBlurRenderer;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\b&\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0011\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0014\u00a2\u0006\u0004\b\u0007\u0010\bJ-\u0010\u0010\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0010\u0010\u0011J/\u0010\u0012\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u000eH\u0014\u00a2\u0006\u0004\b\u0013\u0010\u0014J/\u0010\u0015\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eH$\u00a2\u0006\u0004\b\u0015\u0010\u0011J/\u0010\u0016\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0011J\u000f\u0010\u0018\u001a\u00020\u0017H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/api/ui/BaseScreen;", "Lnet/minecraft/Screen;", "Lnet/minecraft/Text;", "title", "<init>", "(Lnet/minecraft/Text;)V", "", "onClosingOverlayDropped", "()V", "Lnet/minecraft/DrawContext;", "graphics", "", "mouseX", "mouseY", "", "partialTick", "render", "(Lnet/minecraft/DrawContext;IIF)V", "renderScoped", "uiZoom", "()F", "renderScreen", "renderBackground", "", "isPauseScreen", "()Z", "Companion", "rtx.kimiko:kimiko"})
public abstract class BaseScreen
extends Screen {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    private static BaseScreen closingOverlay;

    protected BaseScreen(@NotNull Text title) {
        super(title);
    }

    protected void onClosingOverlayDropped() {
    }

    public final void render(@NotNull DrawContext graphics, int mouseX, int mouseY, float partialTick) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        this.renderScoped(graphics, mouseX, mouseY, partialTick);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void renderScoped(DrawContext graphics, int mouseX, int mouseY, float partialTick) {
        float previousZoom = Render2DCoordinateSpace.pushUiZoom(this.uiZoom());
        try {
            Render2D.beginFrame(graphics);
            this.renderScreen(graphics, mouseX, mouseY, partialTick);
            Render2D.flush();
            GuiLayerBlurRenderer.markPanelEnd(graphics);
        }
        finally {
            Render2DCoordinateSpace.popUiZoom(previousZoom);
        }
    }

    protected float uiZoom() {
        return 1.0f;
    }

    protected abstract void renderScreen(@NotNull DrawContext var1, int var2, int var3, float var4);

    public void renderBackground(@NotNull DrawContext graphics, int mouseX, int mouseY, float partialTick) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
    }

    public boolean shouldPause() {
        return false;
    }

    @JvmStatic
    public static final void beginClosingOverlay(@Nullable BaseScreen screen) {
        Companion.beginClosingOverlay(screen);
    }

    @JvmStatic
    public static final void cancelClosingOverlay(@Nullable BaseScreen screen) {
        Companion.cancelClosingOverlay(screen);
    }

    @JvmStatic
    public static final boolean hasClosingOverlay() {
        return Companion.hasClosingOverlay();
    }

    @JvmStatic
    public static final void dropClosingOverlay() {
        Companion.dropClosingOverlay();
    }

    @JvmStatic
    public static final void renderClosingOverlay(@NotNull DrawContext graphics) {
        Companion.renderClosingOverlay(graphics);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001d\u0010\n\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\n\u0010\tJ\u0013\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000e\u0010\u0003J\u001b\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u000fH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/api/ui/BaseScreen.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/ui/BaseScreen;", "screen", "", "Lkotlin/jvm/JvmStatic;", "beginClosingOverlay", "(Lrtx/kimiko/api/ui/BaseScreen;)V", "cancelClosingOverlay", "", "hasClosingOverlay", "()Z", "dropClosingOverlay", "Lnet/minecraft/DrawContext;", "graphics", "renderClosingOverlay", "(Lnet/minecraft/DrawContext;)V", "closingOverlay", "Lrtx/kimiko/api/ui/BaseScreen;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final void beginClosingOverlay(@Nullable BaseScreen screen) {
            closingOverlay = screen;
        }

        @JvmStatic
        public final void cancelClosingOverlay(@Nullable BaseScreen screen) {
            if (closingOverlay == screen) {
                closingOverlay = null;
            }
        }

        @JvmStatic
        public final boolean hasClosingOverlay() {
            return closingOverlay != null;
        }

        @JvmStatic
        public final void dropClosingOverlay() {
            block0: {
                BaseScreen overlay = closingOverlay;
                closingOverlay = null;
                BaseScreen baseScreen = overlay;
                if (baseScreen == null) break block0;
                baseScreen.onClosingOverlayDropped();
            }
        }

        @JvmStatic
        public final void renderClosingOverlay(@NotNull DrawContext graphics) {
            Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
            BaseScreen baseScreen = closingOverlay;
            if (baseScreen == null) {
                return;
            }
            BaseScreen overlay = baseScreen;
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient minecraft = minecraftClient2;
            if (minecraft.currentScreen != null || minecraft.world == null || minecraft.options.hudHidden) {
                this.dropClosingOverlay();
                return;
            }
            overlay.renderScoped(graphics, 0, 0, 0.0f);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

