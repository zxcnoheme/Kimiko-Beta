/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  kotlin.ranges.RangesKt
 *  net.minecraft.client.gui.Click
 *  net.minecraft.text.Text
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.text.MutableText
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.crosshair;

import java.awt.Color;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import net.minecraft.client.gui.Click;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.MutableText;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.modules.impl.Visuals.Crosshair;
import rtx.kimiko.api.ui.BaseScreen;
import rtx.kimiko.api.ui.settings.RenderHelper;
import rtx.kimiko.api.ui.settings.impl.BoolSetting;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.api.ui.window.PanelAnimation;
import rtx.kimiko.utils.animations.UiverseSwitchAnimation;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0018\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u0000 ]2\u00020\u0001:\u0001]B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0014\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0014\u00a2\u0006\u0004\b\u000b\u0010\nJ\u000f\u0010\f\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\f\u0010\nJ/\u0010\u0014\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0012H\u0014\u00a2\u0006\u0004\b\u0014\u0010\u0015JG\u0010 \u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b \u0010!JY\u0010(\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u00122\u0006\u0010&\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00122\b\u0010'\u001a\u0004\u0018\u00010\u0018H\u0002\u00a2\u0006\u0004\b(\u0010)J7\u0010*\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b*\u0010+J\u001f\u0010/\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020\u001cH\u0016\u00a2\u0006\u0004\b/\u00100J\u0017\u00101\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020,H\u0016\u00a2\u0006\u0004\b1\u00102J\u001f\u00105\u001a\u00020\b2\u0006\u00103\u001a\u00020\u00122\u0006\u00104\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b5\u00106J'\u0010:\u001a\u00020\b2\u0006\u00107\u001a\u00020\u000f2\u0006\u00108\u001a\u00020\u000f2\u0006\u00109\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b:\u0010;J\u001f\u0010<\u001a\u00020\u000f2\u0006\u00103\u001a\u00020\u00122\u0006\u00104\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b<\u0010=J\u000f\u0010>\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b>\u0010\nJ\u000f\u0010?\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b?\u0010@J\u000f\u0010A\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\bA\u0010@J\u000f\u0010B\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\bB\u0010@J\u000f\u0010C\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\bC\u0010@R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010DR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010ER\u0014\u0010G\u001a\u00020F8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010HR\u0014\u0010J\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010KR\u0014\u0010L\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u0010MR\u0014\u0010N\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bN\u0010MR\u0016\u0010O\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bO\u0010PR\u0016\u0010Q\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bQ\u0010PR\u0016\u0010R\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bR\u0010SR\u0016\u0010T\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bT\u0010UR\u0016\u0010V\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010UR\u0016\u0010W\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bW\u0010UR\u0016\u0010X\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bX\u0010UR\u0016\u0010Y\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bY\u0010UR\u0016\u0010[\u001a\u00020Z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b[\u0010\\\u00a8\u0006^"}, d2={"Lrtx/kimiko/api/ui/crosshair/CrosshairEditorScreen;", "Lrtx/kimiko/api/ui/BaseScreen;", "Lrtx/kimiko/api/modules/impl/Visuals/Crosshair;", "module", "Lnet/minecraft/Screen;", "parent", "<init>", "(Lrtx/kimiko/api/modules/impl/Visuals/Crosshair;Lnet/minecraft/Screen;)V", "", "onClosingOverlayDropped", "()V", "init", "onClose", "Lnet/minecraft/DrawContext;", "graphics", "", "mouseX0", "mouseY0", "", "partialTick", "renderScreen", "(Lnet/minecraft/DrawContext;IIF)V", "rx", "ry", "", "label", "Lrtx/kimiko/utils/animations/UiverseSwitchAnimation;", "toggleAnim", "", "on", "hover", "alpha", "drawToggleRow", "(FFLjava/lang/String;Lrtx/kimiko/utils/animations/UiverseSwitchAnimation;ZFF)V", "bx", "by", "bw", "bh", "text", "icon", "drawButton", "(Lnet/minecraft/DrawContext;FFFFLjava/lang/String;FFLjava/lang/String;)V", "drawBackLink", "(Lnet/minecraft/DrawContext;FFFF)V", "Lnet/minecraft/Click;", "event", "doubleClick", "mouseClicked", "(Lnet/minecraft/Click;Z)Z", "mouseReleased", "(Lnet/minecraft/Click;)Z", "mx", "my", "applyPaintAt", "(FF)V", "r", "c", "value", "setCell", "(IIZ)V", "cellAt", "(FF)I", "push", "winX", "()F", "winY", "gridX", "gridY", "Lrtx/kimiko/api/modules/impl/Visuals/Crosshair;", "Lnet/minecraft/Screen;", "", "grid", "[Z", "Lrtx/kimiko/api/ui/window/PanelAnimation;", "anim", "Lrtx/kimiko/api/ui/window/PanelAnimation;", "mirrorXAnim", "Lrtx/kimiko/utils/animations/UiverseSwitchAnimation;", "mirrorYAnim", "mirrorX", "Z", "mirrorY", "paintButton", "I", "hoverBack", "F", "hoverRow1", "hoverRow2", "hoverBtn1", "hoverBtn2", "", "lastNs", "J", "Companion", "rtx.kimiko:kimiko"})
public final class CrosshairEditorScreen
extends BaseScreen {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Crosshair module;
    @Nullable
    private final Screen parent;
    @NotNull
    private final boolean[] grid;
    @NotNull
    private final PanelAnimation anim;
    @NotNull
    private final UiverseSwitchAnimation mirrorXAnim;
    @NotNull
    private final UiverseSwitchAnimation mirrorYAnim;
    private boolean mirrorX;
    private boolean mirrorY;
    private int paintButton;
    private float hoverBack;
    private float hoverRow1;
    private float hoverRow2;
    private float hoverBtn1;
    private float hoverBtn2;
    private long lastNs;
    private static final int GRID = 15;
    private static final float CELL = 10.0f;
    private static final float PITCH = 11.0f;
    private static final float PAD = 12.0f;
    private static final float GRID_PANEL = 172.0f;
    private static final float RIGHT_W = 112.0f;
    private static final float WIN_W = 320.0f;
    private static final float PANEL_TOP = 30.0f;
    private static final float WIN_H = 214.0f;
    private static final float PREVIEW_LABEL_Y = 30.0f;
    private static final float PREVIEW_Y = 39.0f;
    private static final float PREVIEW_H = 50.0f;
    private static final float MIRROR_LABEL_Y = 97.0f;
    private static final float ROW_H = 13.0f;
    private static final float ROW1_Y = 106.0f;
    private static final float ROW2_Y = 123.0f;
    private static final float BTN_H = 16.0f;
    private static final float BTN1_Y = 145.0f;
    private static final float BTN2_Y = 167.0f;
    private static final float HINT_Y = 192.5f;

    public CrosshairEditorScreen(@NotNull Crosshair module, @Nullable Screen parent) {
        super((Text)Text.literal(""));
        this.module = module;
        this.parent = parent;
        this.grid = this.module.customGrid();
        this.anim = new PanelAnimation(true, false, 2, null);
        this.mirrorXAnim = new UiverseSwitchAnimation(true);
        this.mirrorYAnim = new UiverseSwitchAnimation(false);
        this.mirrorX = true;
        this.paintButton = -1;
        this.lastNs = System.nanoTime();
    }

    @Override
    protected void onClosingOverlayDropped() {
        this.anim.finish();
    }

    protected void init() {
        this.anim.setPanelRect(this.winX(), this.winY(), 320.0f, 214.0f);
        this.anim.open();
        Sounds.play("module_settings_open");
    }

    public void close() {
        if (!this.anim.isClosing()) {
            this.paintButton = -1;
            this.anim.setPanelRect(this.winX(), this.winY(), 320.0f, 214.0f);
            this.anim.close();
            Sounds.play("module_settings_close");
            BaseScreen.Companion.beginClosingOverlay(this);
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient minecraft = minecraftClient2;
            if (Intrinsics.areEqual((Object)minecraft.currentScreen, (Object)((Object)this))) {
                minecraft.setScreen(null);
            }
        }
    }

    @Override
    protected void renderScreen(@NotNull DrawContext graphics, int mouseX0, int mouseY0, float partialTick) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        this.anim.updateFrame();
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (this.anim.isCloseFinished()) {
            this.anim.finish();
            BaseScreen.Companion.cancelClosingOverlay(this);
            if (Intrinsics.areEqual((Object)mc.currentScreen, (Object)((Object)this)) || mc.currentScreen == null) {
                mc.setScreen(this.parent);
            }
            return;
        }
        boolean captured = this.anim.captureActive();
        float alpha = this.anim.contentAlpha();
        float scale = captured ? 1.0f : this.anim.motion().scale();
        float x = this.winX();
        float y = this.winY();
        float cx = x + 160.0f;
        float cy = y + 107.0f;
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        float rx = x + 12.0f + 172.0f + 12.0f;
        long now = System.nanoTime();
        float dt = Math.min(0.1f, (float)(now - this.lastNs) / 1.0E9f);
        this.lastNs = now;
        boolean interact = this.anim.canInteract();
        float backW = CrosshairEditorScreen.Companion.backWidth();
        float backX = x + 320.0f - 12.0f - backW;
        float backY = y - 13.0f;
        this.hoverBack = CrosshairEditorScreen.Companion.approach(this.hoverBack, interact && CrosshairEditorScreen.Companion.hit(mx, my, backX, backY, backW, 10.0f) ? 1.0f : 0.0f, dt);
        this.hoverRow1 = CrosshairEditorScreen.Companion.approach(this.hoverRow1, interact && CrosshairEditorScreen.Companion.hit(mx, my, rx, y + 106.0f, 112.0f, 13.0f) ? 1.0f : 0.0f, dt);
        this.hoverRow2 = CrosshairEditorScreen.Companion.approach(this.hoverRow2, interact && CrosshairEditorScreen.Companion.hit(mx, my, rx, y + 123.0f, 112.0f, 13.0f) ? 1.0f : 0.0f, dt);
        this.hoverBtn1 = CrosshairEditorScreen.Companion.approach(this.hoverBtn1, interact && CrosshairEditorScreen.Companion.hit(mx, my, rx, y + 145.0f, 112.0f, 16.0f) ? 1.0f : 0.0f, dt);
        this.hoverBtn2 = CrosshairEditorScreen.Companion.approach(this.hoverBtn2, interact && CrosshairEditorScreen.Companion.hit(mx, my, rx, y + 167.0f, 112.0f, 16.0f) ? 1.0f : 0.0f, dt);
        if (this.paintButton != -1 && interact) {
            this.applyPaintAt(mx, my);
        }
        Render2D.rect(-5.0f, -5.0f, mc.getWindow().getFramebufferWidth(), mc.getWindow().getFramebufferHeight(), 0.0f, CrosshairEditorScreen.Companion.color(0, 0, 0, 50, this.anim.dimAlpha()));
        this.anim.beginCaptureStratum(graphics);
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(cx, cy);
        graphics.getMatrices().scale(scale, scale);
        graphics.getMatrices().translate(-cx, -cy);
        RectUtil.drawClientRect(x, y, 320.0f, 214.0f, 12.0f, alpha, 6.0f);
        Fonts.SEMIBOLD.draw(I18n.tr("Редактор прицела"), x + 12.0f, y + 10.0f, 8.0f, CrosshairEditorScreen.Companion.color(255, 255, 255, 240, alpha));
        float titleW = Fonts.SEMIBOLD.width(I18n.tr("Редактор прицела"), 8.0f);
        Fonts.MEDIUM.draw("15\u00d715", x + 12.0f + titleW + 6.0f, y + 12.5f, 5.0f, CrosshairEditorScreen.Companion.color(255, 255, 255, 90, alpha));
        Render2D.rect(x + 12.0f, y + 25.4f, 296.0f, 0.6f, 0.0f, CrosshairEditorScreen.Companion.color(255, 255, 255, 16, alpha));
        RenderHelper.drawPanelBg(x + 12.0f, y + 30.0f, 172.0f, 172.0f, 8.0f, alpha);
        float gx = this.gridX();
        float gy = this.gridY();
        int hoverCell = interact ? this.cellAt(mx, my) : -1;
        int n = this.grid.length;
        for (int i = 0; i < n; ++i) {
            int r = i / 15;
            int c = i % 15;
            float gcx = gx + (float)c * 11.0f;
            float gcy = gy + (float)r * 11.0f;
            if (this.grid[i]) {
                Render2D.rect(gcx, gcy, 10.0f, 10.0f, 2.0f, CrosshairEditorScreen.Companion.color(255, 255, 255, 235, alpha));
            } else if (r == 7 || c == 7) {
                Render2D.rect(gcx, gcy, 10.0f, 10.0f, 2.0f, ClientAccent.accentFillAt(30.0f * alpha, gcx + 5.0f, gcy + 5.0f));
            } else {
                Render2D.rect(gcx, gcy, 10.0f, 10.0f, 2.0f, CrosshairEditorScreen.Companion.color(255, 255, 255, 10, alpha));
            }
            if (i != hoverCell) continue;
            if (!this.grid[i]) {
                Render2D.rect(gcx, gcy, 10.0f, 10.0f, 2.0f, CrosshairEditorScreen.Companion.color(255, 255, 255, 45, alpha));
            }
            Render2D.outlineClient$default(gcx, gcy, 10.0f, 10.0f, 2.0f, 0.7f, ClientAccent.accentBrightAt(180.0f * alpha, gcx + 5.0f, gcy + 5.0f), 0.0f, 128, null);
        }
        Fonts.MEDIUM.draw(I18n.tr("Превью"), rx, y + 30.0f, 5.0f, CrosshairEditorScreen.Companion.color(255, 255, 255, 130, alpha));
        RenderHelper.drawPanelBg(rx, y + 39.0f, 112.0f, 50.0f, 8.0f, alpha);
        if (alpha > 0.55f) {
            float pcy = y + 39.0f + 25.0f;
            this.module.renderGridStyled(this.grid, rx + 31.0f, pcy, 1.0f, 1.0f);
            this.module.renderGridStyled(this.grid, rx + 73.5f, pcy, 2.0f, 1.0f);
        }
        Fonts.MEDIUM.draw(I18n.tr("Симметрия"), rx, y + 97.0f, 5.0f, CrosshairEditorScreen.Companion.color(255, 255, 255, 130, alpha));
        this.drawToggleRow(rx, y + 106.0f, I18n.tr("Зеркало X"), this.mirrorXAnim, this.mirrorX, this.hoverRow1, alpha);
        this.drawToggleRow(rx, y + 123.0f, I18n.tr("Зеркало Y"), this.mirrorYAnim, this.mirrorY, this.hoverRow2, alpha);
        this.drawBackLink(graphics, backX, backY, this.hoverBack, alpha);
        this.drawButton(graphics, rx, y + 145.0f, 112.0f, 16.0f, I18n.tr("Крест"), this.hoverBtn1, alpha, null);
        this.drawButton(graphics, rx, y + 167.0f, 112.0f, 16.0f, I18n.tr("Очистить"), this.hoverBtn2, alpha, null);
        String hint = I18n.tr("ЛКМ — рисовать \u00b7 ПКМ — стирать");
        float hintW = Fonts.MEDIUM.width(hint, 5.0f);
        Fonts.MEDIUM.draw(hint, rx + (112.0f - hintW) * 0.5f, y + 192.5f, 5.0f, CrosshairEditorScreen.Companion.color(255, 255, 255, 110, alpha));
        graphics.getMatrices().popMatrix();
    }

    private final void drawToggleRow(float rx, float ry, String label, UiverseSwitchAnimation toggleAnim, boolean on, float hover, float alpha) {
        float radius = RenderHelper.effectiveCornerRadius(4.0f, 112.0f, 13.0f);
        if (hover > 0.01f) {
            Render2D.rect(rx, ry, 112.0f, 13.0f, radius, CrosshairEditorScreen.Companion.color(255, 255, 255, MathKt.roundToInt((float)(12.0f * hover)), alpha));
        }
        Fonts.MEDIUM.draw(label, rx + 5.0f, ry + 3.0f, 6.0f, CrosshairEditorScreen.Companion.color(255, 255, 255, 210, alpha));
        BoolSetting.Companion.drawToggle(rx + 112.0f - 19.5f - 4.0f, ry + 1.3000002f, 19.5f, 10.4f, toggleAnim, on, alpha);
    }

    private final void drawButton(DrawContext graphics, float bx, float by, float bw, float bh, String text, float hover, float alpha, String icon) {
        float radius = RenderHelper.effectiveCornerRadius(4.0f, bw, bh);
        RenderHelper.drawPanelBg(bx, by, bw, bh, 4.0f, alpha);
        if (hover > 0.01f) {
            Render2D.rect(bx, by, bw, bh, radius, ClientAccent.accentFillAt(60.0f * hover * alpha, bx + bw * 0.5f, by + bh * 0.5f));
        }
        int border = ColorEngine.lerpColor(CrosshairEditorScreen.Companion.color(255, 255, 255, 30, alpha), ClientAccent.accentBrightAt(150.0f * alpha, bx + bw * 0.5f, by + bh * 0.5f), hover);
        Render2D.outline(bx, by, bw, bh, radius, 0.6f, border);
        int textColor = ColorEngine.lerpColor(ClientAccent.accentSoftAt(220.0f * alpha, bx + bw * 0.5f, by + bh * 0.5f), CrosshairEditorScreen.Companion.color(255, 255, 255, 245, alpha), hover);
        float tw = Fonts.MEDIUM.width(text, 6.0f);
        if (icon == null) {
            Fonts.MEDIUM.draw(text, bx + (bw - tw) * 0.5f, by + (bh - 7.0f) * 0.5f, 6.0f, textColor);
            return;
        }
        float iconW = Fonts.I2.msdfWidth(icon, 6.0f);
        float groupW = iconW + 3.0f + tw;
        float groupX = bx + (bw - groupW) * 0.5f;
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(groupX + iconW, by + 5.5f);
        graphics.getMatrices().scale(-1.0f, 1.0f);
        Fonts.I2.msdf(icon, 0.0f, -3.5f, 6.0f, textColor);
        graphics.getMatrices().popMatrix();
        Fonts.MEDIUM.draw(text, groupX + iconW + 3.0f, by + (bh - 7.0f) * 0.5f, 6.0f, textColor);
    }

    private final void drawBackLink(DrawContext graphics, float bx, float by, float hover, float alpha) {
        int textColor = CrosshairEditorScreen.Companion.color(255, 255, 255, 150 + MathKt.roundToInt((float)(105.0f * hover)), alpha);
        float iconW = Fonts.I2.msdfWidth("G", 6.0f);
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(bx + iconW, by + 5.0f);
        graphics.getMatrices().scale(-1.0f, 1.0f);
        Fonts.I2.msdf("G", -2.0f, -3.0f, 6.0f, textColor);
        graphics.getMatrices().popMatrix();
        Fonts.MEDIUM.draw(I18n.tr("Назад"), bx + iconW + 5.0f, by + 1.7f, 6.0f, textColor);
    }

    public boolean mouseClicked(@NotNull Click event, boolean doubleClick) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!this.anim.canInteract()) {
            return true;
        }
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        float x = this.winX();
        float y = this.winY();
        float rx = x + 12.0f + 172.0f + 12.0f;
        float backW = CrosshairEditorScreen.Companion.backWidth();
        if (CrosshairEditorScreen.Companion.hit(mx, my, x + 320.0f - 12.0f - backW, y - 13.0f, backW, 10.0f)) {
            this.close();
            return true;
        }
        if (this.cellAt(mx, my) != -1 && (event.button() == 0 || event.button() == 1)) {
            this.paintButton = event.button();
            this.applyPaintAt(mx, my);
            return true;
        }
        if (CrosshairEditorScreen.Companion.hit(mx, my, rx, y + 106.0f, 112.0f, 13.0f)) {
            this.mirrorX = !this.mirrorX;
            return true;
        }
        if (CrosshairEditorScreen.Companion.hit(mx, my, rx, y + 123.0f, 112.0f, 13.0f)) {
            this.mirrorY = !this.mirrorY;
            return true;
        }
        if (CrosshairEditorScreen.Companion.hit(mx, my, rx, y + 145.0f, 112.0f, 16.0f)) {
            String preset = Crosshair.Companion.defaultGrid();
            int n = this.grid.length;
            for (int i = 0; i < n; ++i) {
                this.grid[i] = preset.charAt(i) == '1';
            }
            this.push();
            return true;
        }
        if (CrosshairEditorScreen.Companion.hit(mx, my, rx, y + 167.0f, 112.0f, 16.0f)) {
            Arrays.fill(this.grid, false);
            this.push();
            return true;
        }
        return super.mouseClicked(event, doubleClick);
    }

    public boolean mouseReleased(@NotNull Click event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        this.paintButton = -1;
        return super.mouseReleased(event);
    }

    private final void applyPaintAt(float mx, float my) {
        int cell = this.cellAt(mx, my);
        if (cell == -1) {
            return;
        }
        boolean value = this.paintButton != 1;
        int r = cell / 15;
        int c = cell % 15;
        this.setCell(r, c, value);
        if (this.mirrorX) {
            this.setCell(r, 14 - c, value);
        }
        if (this.mirrorY) {
            this.setCell(14 - r, c, value);
        }
        if (this.mirrorX && this.mirrorY) {
            this.setCell(14 - r, 14 - c, value);
        }
        this.push();
    }

    private final void setCell(int r, int c, boolean value) {
        boolean bl = 0 <= r ? r < 15 : false;
        if (bl) {
            boolean bl2 = 0 <= c ? c < 15 : false;
            if (bl2) {
                this.grid[r * 15 + c] = value;
            }
        }
    }

    private final int cellAt(float mx, float my) {
        float gx = this.gridX();
        float gy = this.gridY();
        int c = (int)Math.floor((mx - gx) / 11.0f);
        int r = (int)Math.floor((my - gy) / 11.0f);
        if (c < 0 || c >= 15 || r < 0 || r >= 15) {
            return -1;
        }
        return r * 15 + c;
    }

    private final void push() {
        this.module.setCustomGrid((boolean[])this.grid.clone());
    }

    private final float winX() {
        return Position.Companion.screenWidth() * 0.5f - 160.0f;
    }

    private final float winY() {
        return Position.Companion.screenHeight() * 0.5f - 107.0f;
    }

    private final float gridX() {
        return this.winX() + 12.0f + 4.0f;
    }

    private final float gridY() {
        return this.winY() + 30.0f + 4.0f;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001e\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J'\u0010\n\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ?\u0010\u0013\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J7\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b!\u0010 R\u0014\u0010\"\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010 R\u0014\u0010#\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b#\u0010 R\u0014\u0010$\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010 R\u0014\u0010%\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010 R\u0014\u0010&\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010 R\u0014\u0010'\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010 R\u0014\u0010(\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010 R\u0014\u0010)\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010 R\u0014\u0010*\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010 R\u0014\u0010+\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010 R\u0014\u0010,\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010 R\u0014\u0010-\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010 R\u0014\u0010.\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010 R\u0014\u0010/\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b/\u0010 R\u0014\u00100\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b0\u0010 R\u0014\u00101\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b1\u0010 R\u0014\u00102\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u0010 \u00a8\u00063"}, d2={"Lrtx/kimiko/api/ui/crosshair/CrosshairEditorScreen.Companion;", "", "<init>", "()V", "", "backWidth", "()F", "current", "target", "dt", "approach", "(FFF)F", "mx", "my", "x", "y", "w", "h", "", "hit", "(FFFFFF)Z", "", "r", "g", "b", "a", "mult", "color", "(IIIIF)I", "GRID", "I", "CELL", "F", "PITCH", "PAD", "GRID_PANEL", "RIGHT_W", "WIN_W", "PANEL_TOP", "WIN_H", "PREVIEW_LABEL_Y", "PREVIEW_Y", "PREVIEW_H", "MIRROR_LABEL_Y", "ROW_H", "ROW1_Y", "ROW2_Y", "BTN_H", "BTN1_Y", "BTN2_Y", "HINT_Y", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float backWidth() {
            return Fonts.MEDIUM.width(I18n.tr("Назад"), 6.0f) + 16.0f;
        }

        private final float approach(float current, float target, float dt) {
            return current + (target - current) * (1.0f - (float)Math.exp(-dt * 16.0f));
        }

        private final boolean hit(float mx, float my, float x, float y, float w, float h) {
            return mx >= x && mx <= x + w && my >= y && my <= y + h;
        }

        private final int color(int r, int g, int b, int a, float mult) {
            int fa = RangesKt.coerceIn((int)MathKt.roundToInt((float)((float)a * mult)), (int)0, (int)255);
            return fa <= 0 ? 0 : new Color(r, g, b, fa).getRGB();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

