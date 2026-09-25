/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.player.PlayerInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.font.TextRenderer
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.screen.ChatScreen
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 */
package rtx.kimiko.api.drags.components;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import rtx.kimiko.api.drags.DragSystem;
import rtx.kimiko.api.drags.Draggable;
import rtx.kimiko.api.drags.components.HudHeaderRenderer;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.impl.Interface.InventoryModule;
import rtx.kimiko.api.modules.impl.Visuals.ItemHighlight;
import rtx.kimiko.utils.animations.Easing;
import rtx.kimiko.utils.animations.HudFadeAnimation;
import rtx.kimiko.utils.animations.SmoothAnimation;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 T2\u00020\u0001:\u0001TB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\n\u0010\tJ\u000f\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0014\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J/\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u001d\u0010\tJ\u000f\u0010\u001e\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u001e\u0010\tJ\u0017\u0010!\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u001fH\u0002\u00a2\u0006\u0004\b!\u0010\"J\u001f\u0010$\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b$\u0010%J'\u0010&\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b&\u0010'J\u000f\u0010(\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b(\u0010\u0003J\u000f\u0010)\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b)\u0010\tJ\u000f\u0010*\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b*\u0010\tJ7\u0010,\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b,\u0010-J\u001f\u0010/\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u001fH\u0002\u00a2\u0006\u0004\b/\u00100J7\u00101\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u001fH\u0002\u00a2\u0006\u0004\b1\u00102R\u0014\u00104\u001a\u0002038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0014\u00107\u001a\u0002068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b7\u00108R\u0014\u00109\u001a\u0002068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b9\u00108R\u0014\u0010:\u001a\u0002068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u00108R\u0016\u0010;\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b;\u0010<R\u001c\u0010?\u001a\b\u0012\u0004\u0012\u00020>0=8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b?\u0010@R\u0014\u0010B\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u001c\u0010E\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010>0D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010FR\u0014\u0010G\u001a\u0002068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u00108R\u0016\u0010H\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bH\u0010IR\u0016\u0010J\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bJ\u0010IR\u0016\u0010K\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010IR\u0016\u0010L\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bL\u0010<R\u0016\u0010N\u001a\u00020M8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bN\u0010OR\u0016\u0010P\u001a\u00020M8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bP\u0010OR\u0014\u0010R\u001a\u00020Q8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bR\u0010S\u00a8\u0006U"}, d2={"Lrtx/kimiko/api/drags/components/InventoryComp;", "Lrtx/kimiko/api/drags/Draggable;", "<init>", "()V", "", "displayName", "()Ljava/lang/String;", "", "width", "()F", "height", "", "isInteractive", "()Z", "Lnet/minecraft/DrawContext;", "graphics", "", "render", "(Lnet/minecraft/DrawContext;)V", "hasContent", "headerWidth", "computeTargetVisible", "(ZF)Z", "x", "y", "alpha", "dividerT", "drawDivider", "(FFFF)V", "contentOriginY", "titleWidth", "", "row", "rowHasItem", "(I)Z", "preview", "pres", "(IZ)F", "drawHeader", "(FFF)V", "updateHeaderIcon", "headerIconProgress", "headerMinWidth", "lastActiveRow", "drawSeparators", "(FFFIZ)V", "index", "cellY", "(FI)F", "drawItems", "(Lnet/minecraft/DrawContext;FFFI)V", "Lrtx/kimiko/utils/animations/HudFadeAnimation;", "visibility", "Lrtx/kimiko/utils/animations/HudFadeAnimation;", "Lrtx/kimiko/utils/animations/SmoothAnimation;", "widthAnimation", "Lrtx/kimiko/utils/animations/SmoothAnimation;", "dividerAlpha", "headerIconAnim", "lastHeaderIconVisible", "Z", "", "Lnet/minecraft/ItemStack;", "stacks", "Ljava/util/List;", "", "slotProgress", "[F", "", "shownStacks", "[Lnet/minecraft/ItemStack;", "extentAnimation", "currentWidth", "F", "currentHeight", "titleWidthCache", "everHadContent", "", "sizeCollapsedAtMs", "J", "lastNs", "Lrtx/kimiko/api/drags/components/HudHeaderRenderer;", "newHeaderRenderer", "Lrtx/kimiko/api/drags/components/HudHeaderRenderer;", "Companion", "rtx.kimiko:kimiko"})
public final class InventoryComp
extends Draggable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final HudFadeAnimation visibility = this.getHudFade();
    @NotNull
    private final SmoothAnimation widthAnimation = new SmoothAnimation();
    @NotNull
    private final SmoothAnimation dividerAlpha = new SmoothAnimation();
    @NotNull
    private final SmoothAnimation headerIconAnim = new SmoothAnimation();
    private boolean lastHeaderIconVisible;
    @NotNull
    private List<ItemStack> stacks = new ArrayList();
    @NotNull
    private final float[] slotProgress = new float[27];
    @NotNull
    private final ItemStack[] shownStacks = new ItemStack[27];
    @NotNull
    private final SmoothAnimation extentAnimation = new SmoothAnimation();
    private float currentWidth = 107.0f;
    private float currentHeight = 64.0f;
    private float titleWidthCache = -1.0f;
    private boolean everHadContent;
    private long sizeCollapsedAtMs;
    private long lastNs = System.nanoTime();
    @NotNull
    private final HudHeaderRenderer newHeaderRenderer = new HudHeaderRenderer("Inventory", "n", 8.2f);
    @NotNull
    private static final String TITLE_TEXT = "Inventory";
    private static final float TITLE_SIZE = 8.2f;
    private static final float PAD_X = 9.0f;
    @NotNull
    private static final String HEADER_ICON_GLYPH = "n";
    private static final float HEADER_ICON_SIZE = 7.0f;
    private static final float HEADER_ICON_GAP = 6.0f;
    private static final float HEADER_ICON_SLIDE = 5.0f;
    private static final double HEADER_ICON_IN_SECONDS = 0.25;
    private static final double HEADER_ICON_OUT_SECONDS = 0.2;
    private static final float DIVIDER_Y = 22.0f;
    private static final float DIVIDER_HEIGHT = 0.5f;
    private static final int DIVIDER_COLOR = -1997220937;
    private static final float RADIUS = 5.0f;
    private static final int ITEMS_PER_ROW = 9;
    private static final int SLOT_START = 9;
    private static final int SLOT_END = 36;
    private static final int TOTAL = 27;
    private static final int ROWS = 3;
    private static final float CELL = 11.0f;
    private static final float ITEM = 8.0f;
    private static final float ITEM_INSET = 0.75f;
    private static final float ORIGIN_X = 4.0f;
    private static final float ORIGIN_Y = 28.0f;
    private static final float PANEL_WIDTH = 107.0f;
    private static final int LINE_COLOR = -1;
    private static final float SLOT_RATE = 13.0f;
    private static final float MIN_P = 0.02f;
    private static final float HEADER_HEIGHT = 22.0f;
    private static final float HEADER_EXTRA_WIDTH = 14.0f;
    private static final double WIDTH_SECONDS = 0.24;
    private static final double DIVIDER_IN_SECONDS = 0.24;
    private static final double DIVIDER_OUT_SECONDS = 0.14;
    private static final double ROW_IN_SECONDS = 0.22;
    private static final double ROW_OUT_SECONDS = 0.18;
    private static final long FADE_OUT_DELAY_MS = 100L;
    private static final float MIN_DIVIDER_ALPHA = 0.003921569f;
    @NotNull
    private static final Easing SMOOTH = InventoryComp::SMOOTH$lambda$0;

    public InventoryComp() {
        super("inventory", 5.0f, 82.0f);
        this.visibility.set(0.0);
        this.widthAnimation.set(107.0);
        this.dividerAlpha.set(0.0);
        this.extentAnimation.set(0.0);
        this.headerIconAnim.set(0.0);
    }

    @Override
    @NotNull
    public String displayName() {
        return TITLE_TEXT;
    }

    @Override
    public float width() {
        return this.currentWidth;
    }

    @Override
    public float height() {
        return this.currentHeight;
    }

    @Override
    public boolean isInteractive() {
        return InventoryComp.Companion.componentEnabled();
    }

    @Override
    protected void render(@NotNull DrawContext graphics) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        boolean enabled = InventoryComp.Companion.componentEnabled();
        boolean dragMode = DragSystem.Companion.get().isDragModeActive();
        this.stacks = enabled ? InventoryComp.Companion.readInventory() : (List)new ArrayList();
        boolean hasItems = false;
        for (ItemStack stack : this.stacks) {
            if (stack.isEmpty()) continue;
            hasItems = true;
            break;
        }
        boolean chatOpen = MinecraftClient.getInstance().currentScreen instanceof ChatScreen;
        boolean preview = enabled && dragMode && !hasItems;
        boolean fullGrid = preview || enabled && chatOpen && !hasItems;
        int lastActiveRow = -1;
        for (int r = 0; r < 3; ++r) {
            if (!fullGrid && !this.rowHasItem(r) && !(this.pres(r, false) > 0.02f)) continue;
            lastActiveRow = r;
        }
        double extentTarget = lastActiveRow + 1;
        if (Math.abs(this.extentAnimation.getToValue() - extentTarget) > 0.01) {
            this.extentAnimation.run(extentTarget, extentTarget >= (double)this.extentAnimation.get() ? 0.22 : 0.18, SMOOTH, false);
        }
        this.extentAnimation.update();
        float extent = this.extentAnimation.get();
        boolean hasContent = enabled && (hasItems || dragMode || chatOpen);
        boolean dividerVisible = hasItems || fullGrid;
        this.dividerAlpha.run(dividerVisible ? 1.0 : 0.0, dividerVisible ? 0.24 : 0.14, SMOOTH, true);
        this.dividerAlpha.update();
        float dividerT = InventoryComp.Companion.smoothstep(this.dividerAlpha.get());
        float expandedHeight = this.contentOriginY() + extent * 11.0f + 3.0f;
        float present = Math.max(dividerT, Math.min(1.0f, extent));
        this.currentHeight = 22.0f + (expandedHeight - 22.0f) * present;
        this.updateHeaderIcon();
        float headerWidth = this.headerMinWidth();
        boolean heightCollapsed = extent < 0.05f && dividerT < 0.05f;
        float widthTarget = hasContent || !heightCollapsed ? 107.0f : headerWidth;
        this.widthAnimation.run(widthTarget, 0.24, SMOOTH, true);
        this.widthAnimation.update();
        this.currentWidth = Math.max(headerWidth, this.widthAnimation.get());
        boolean targetVisible = this.computeTargetVisible(hasContent, headerWidth);
        this.visibility.updateTarget(targetVisible);
        float alpha = this.visibility.get();
        boolean panelOpen = alpha >= 0.9f && this.currentWidth >= 106.0f;
        long now = System.nanoTime();
        float dt = Math.min(0.1f, (float)(now - this.lastNs) / 1.0E9f);
        this.lastNs = now;
        float k = 1.0f - (float)Math.exp(-dt * 13.0f);
        for (int i = 0; i < 27; ++i) {
            ItemStack itemStack2;
            if (i < this.stacks.size()) {
                itemStack2 = this.stacks.get(i);
            } else {
                ItemStack itemStack3 = ItemStack.EMPTY;
                itemStack2 = itemStack3;
                Intrinsics.checkNotNullExpressionValue((Object)itemStack3, (String)"EMPTY");
            }
            ItemStack cur = itemStack2;
            if (!cur.isEmpty()) {
                this.shownStacks[i] = cur;
            }
            float target = !cur.isEmpty() && panelOpen ? 1.0f : 0.0f;
            float[] fArray = this.slotProgress;
            int n = i;
            fArray[n] = fArray[n] + (target - this.slotProgress[i]) * k;
        }
        if (alpha <= 0.01f) {
            return;
        }
        float x = this.getX();
        float y = this.getY();
        float scale = 0.96f + alpha * 0.04f;
        float originX = x + this.currentWidth * 0.5f;
        float originY = y + this.currentHeight * 0.5f;
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(originX, originY);
        graphics.getMatrices().scale(scale);
        graphics.getMatrices().translate(-originX, -originY);
        Render2D.beginFrame(graphics);
        RectUtil.drawClientRect(x, y, this.currentWidth, this.currentHeight, 5.0f, alpha);
        this.drawHeader(x, y, alpha);
        this.drawDivider(x, y, alpha, dividerT);
        Render2D.flush();
        Render2D.beginFrame(graphics);
        Render2D.pushScissor(graphics, x, y, this.currentWidth, this.currentHeight);
        this.drawSeparators(x, y, alpha, lastActiveRow, fullGrid);
        Render2D.flush();
        this.drawItems(graphics, x, y, alpha, lastActiveRow);
        Render2D.popScissor(graphics);
        graphics.getMatrices().popMatrix();
    }

    private final boolean computeTargetVisible(boolean hasContent, float headerWidth) {
        boolean shrinking;
        if (hasContent) {
            this.everHadContent = true;
            this.sizeCollapsedAtMs = 0L;
            return true;
        }
        if (!this.everHadContent) {
            return false;
        }
        boolean bl = shrinking = this.extentAnimation.get() > 0.02f || this.dividerAlpha.get() > 0.003921569f || this.currentHeight > 22.5f || this.widthAnimation.get() > headerWidth + 0.5f;
        if (shrinking) {
            this.sizeCollapsedAtMs = 0L;
            return true;
        }
        if (this.sizeCollapsedAtMs == 0L) {
            this.sizeCollapsedAtMs = System.currentTimeMillis();
        }
        return System.currentTimeMillis() - this.sizeCollapsedAtMs < 100L;
    }

    private final void drawDivider(float x, float y, float alpha, float dividerT) {
        if (HudHeaderRenderer.Companion.isNewMode() || dividerT <= 0.003921569f) {
            return;
        }
        float maxWidth = Math.max(0.0f, this.currentWidth - 18.0f);
        float dividerWidth = maxWidth * dividerT;
        if (dividerWidth <= 0.25f) {
            return;
        }
        Render2D.rect(x + (this.currentWidth - dividerWidth) * 0.5f, y + 22.0f, dividerWidth, 0.5f, 0.0f, ColorEngine.multAlpha(-1997220937, alpha * dividerT));
    }

    private final float contentOriginY() {
        return HudHeaderRenderer.Companion.isNewMode() ? 21.0f : 28.0f;
    }

    private final float titleWidth() {
        if (this.titleWidthCache <= 0.0f) {
            this.titleWidthCache = Fonts.SEMIBOLD.msdfWidth(TITLE_TEXT, 8.2f);
        }
        return this.titleWidthCache;
    }

    private final boolean rowHasItem(int row) {
        int base = row * 9;
        for (int c = 0; c < 9; ++c) {
            int i = base + c;
            if (i >= 27 || i >= this.stacks.size() || this.stacks.get(i).isEmpty()) continue;
            return true;
        }
        return false;
    }

    private final float pres(int row, boolean preview) {
        if (preview) {
            return 1.0f;
        }
        float m = 0.0f;
        int base = row * 9;
        for (int c = 0; c < 9; ++c) {
            int i = base + c;
            if (i >= 27) continue;
            m = Math.max(m, this.slotProgress[i]);
        }
        return m;
    }

    private final void drawHeader(float x, float y, float alpha) {
        if (HudHeaderRenderer.Companion.isNewMode()) {
            this.newHeaderRenderer.render(x, y, this.currentWidth, 22.0f, alpha, this.headerIconProgress());
            return;
        }
        float iconT = this.headerIconProgress();
        float[] titleBounds = InventoryComp.Companion.textBounds(Fonts.SEMIBOLD, TITLE_TEXT, 8.2f);
        float titleVisualWidth = titleBounds[2] - titleBounds[0];
        float headerCenterY = y + 11.0f;
        float titleY = headerCenterY - (titleBounds[1] + titleBounds[3]) * 0.5f;
        float centeredX = x + (this.currentWidth - titleVisualWidth) * 0.5f - titleBounds[0];
        float leftX = x + 9.0f - titleBounds[0];
        float titleX = centeredX + (leftX - centeredX) * iconT;
        Fonts.SEMIBOLD.msdf(TITLE_TEXT, titleX, titleY, 8.2f, ColorEngine.multAlpha(InventoryComp.Companion.titleColor(titleX + (titleBounds[0] + titleBounds[2]) * 0.5f, headerCenterY), alpha));
        if (iconT > 0.003921569f) {
            float[] iconBounds = InventoryComp.Companion.textBounds(Fonts.KIMIKO, HEADER_ICON_GLYPH, 7.0f);
            float iconX = x + this.currentWidth - 9.0f - iconBounds[2] + 5.0f * (1.0f - iconT);
            float iconY = headerCenterY - (iconBounds[1] + iconBounds[3]) * 0.5f;
            Fonts.KIMIKO.msdf(HEADER_ICON_GLYPH, iconX, iconY, 7.0f, ColorEngine.multAlpha(InventoryComp.Companion.titleColor(iconX + (iconBounds[0] + iconBounds[2]) * 0.5f, headerCenterY), alpha * iconT * 0.8f));
        }
    }

    private final void updateHeaderIcon() {
        boolean iconVisible = InventoryComp.Companion.hudIconsEnabled();
        if (iconVisible != this.lastHeaderIconVisible) {
            this.headerIconAnim.run(iconVisible ? 1.0 : 0.0, iconVisible ? 0.25 : 0.2, SMOOTH, false);
            this.lastHeaderIconVisible = iconVisible;
        }
        this.headerIconAnim.update();
    }

    private final float headerIconProgress() {
        return Math.max(0.0f, Math.min(1.0f, this.headerIconAnim.get()));
    }

    private final float headerMinWidth() {
        float base = this.titleWidth() + 14.0f;
        if (HudHeaderRenderer.Companion.isNewMode()) {
            return Math.max(base, this.newHeaderRenderer.minimumWidth());
        }
        float iconT = this.headerIconProgress();
        if (iconT <= 0.001f) {
            return base;
        }
        float[] titleBounds = InventoryComp.Companion.textBounds(Fonts.SEMIBOLD, TITLE_TEXT, 8.2f);
        float[] iconBounds = InventoryComp.Companion.textBounds(Fonts.KIMIKO, HEADER_ICON_GLYPH, 7.0f);
        float withIcon = 18.0f + (titleBounds[2] - titleBounds[0]) + 6.0f + (iconBounds[2] - iconBounds[0]);
        return base + Math.max(0.0f, withIcon - base) * iconT;
    }

    private final void drawSeparators(float x, float y, float alpha, int lastActiveRow, boolean preview) {
        int row = 0;
        if (row <= lastActiveRow) {
            while (true) {
                float pr;
                float f = pr = row == lastActiveRow ? this.pres(row, preview) : 1.0f;
                if (!(pr <= 0.02f)) {
                    int vCol = ColorEngine.multAlpha(-1, alpha * 0.12f * pr);
                    int base = row * 9;
                    for (int c = 0; c < 8; ++c) {
                        int index = base + c;
                        Render2D.rect(InventoryComp.Companion.cellX(x, index) + 10.0f, this.cellY(y, index), 0.5f, 9.0f, vCol);
                    }
                    if (row < lastActiveRow) {
                        float prNext = row + 1 == lastActiveRow ? this.pres(row + 1, preview) : 1.0f;
                        int hCol = ColorEngine.multAlpha(-1, alpha * 0.12f * Math.min(pr, prNext));
                        for (int c = 0; c < 9; ++c) {
                            int index = base + c;
                            Render2D.rect(InventoryComp.Companion.cellX(x, index) - 0.5f, this.cellY(y, index) + 10.0f, 9.0f, 0.5f, hCol);
                        }
                    }
                }
                if (row == lastActiveRow) break;
                ++row;
            }
        }
    }

    private final float cellY(float y, int index) {
        return y + this.contentOriginY() + 1.0f + (float)(index / 9) * 11.0f;
    }

    private final void drawItems(DrawContext graphics, float x, float y, float alpha, int lastActiveRow) {
        TextRenderer textRenderer2 = MinecraftClient.getInstance().textRenderer;
        Intrinsics.checkNotNullExpressionValue((Object)textRenderer2, (String)"font");
        TextRenderer font = textRenderer2;
        float half = 4.0f;
        ItemHighlight highlight = ItemHighlight.Companion.getInstance();
        if (highlight != null && highlight.isEnabled()) {
            InterfaceModule interfaceModule;
            InterfaceModule interfaceModule2 = interfaceModule = InterfaceModule.Companion.getInstance();
            float outerRadius = interfaceModule2 == null ? 2.0f : interfaceModule2.rectCornerRadius.getFloat();
            outerRadius = Math.min(4.0f, Math.max(2.0f, outerRadius));
            Render2D.beginFrame(graphics);
            for (int i = 0; i < 27; ++i) {
                float p = this.slotProgress[i];
                ItemStack stack = this.shownStacks[i];
                if (p <= 0.02f || stack == null || stack.isEmpty()) continue;
                int color = highlight.backgroundFor(stack, false);
                boolean bottomRow = i / 9 == lastActiveRow;
                float bottomRight = bottomRow && i % 9 == 8 ? outerRadius : 2.0f;
                float bottomLeft = bottomRow && i % 9 == 0 ? outerRadius : 2.0f;
                highlight.drawRoundedSlotBackground(InventoryComp.Companion.cellX(x, i) + 0.75f, this.cellY(y, i) + 0.75f, 8.0f, color, alpha * p, 2.0f, 2.0f, bottomRight, bottomLeft);
            }
            Render2D.flush();
        }
        for (int i = 0; i < 27; ++i) {
            float appearance;
            ItemStack stack;
            float p = this.slotProgress[i];
            if (p <= 0.02f || (stack = this.shownStacks[i]) == null || stack.isEmpty() || (appearance = Math.max(0.0f, Math.min(1.0f, alpha * p))) <= 0.01f) continue;
            float sz = 8.0f * InventoryComp.Companion.easeOutBack(p);
            float cx = InventoryComp.Companion.cellX(x, i) + 0.75f + half;
            float cy = this.cellY(y, i) + 0.75f + half;
            float itemScale = sz / 16.0f * appearance;
            graphics.getMatrices().pushMatrix();
            Matrix3x2fStack matrix3x2fStack = graphics.getMatrices();
            Intrinsics.checkNotNullExpressionValue((Object)matrix3x2fStack, (String)"pose(...)");
            Render2DCoordinateSpace.applyGuiScaleIndependence((Matrix3x2f)matrix3x2fStack);
            graphics.getMatrices().translate(cx, cy);
            graphics.getMatrices().scale(itemScale, itemScale);
            graphics.getMatrices().translate(-8.0f, -8.0f);
            graphics.drawItem(stack, 0, 0);
            graphics.drawStackOverlay(font, stack, 0, 0);
            graphics.getMatrices().popMatrix();
        }
    }

    private static final double SMOOTH$lambda$0(double value) {
        return value * value * (3.0 - 2.0 * value);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0011\n\u0002\u0010\u0006\n\u0002\b\u001d\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0006J'\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u000eJ\u001f\u0010!\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010&R\u0014\u0010(\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010$R\u0014\u0010)\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010&R\u0014\u0010*\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010&R\u0014\u0010+\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010&R\u0014\u0010-\u001a\u00020,8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u0014\u0010/\u001a\u00020,8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b/\u0010.R\u0014\u00100\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b0\u0010&R\u0014\u00101\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b1\u0010&R\u0014\u00102\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0014\u00104\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u0010&R\u0014\u00105\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b5\u00103R\u0014\u00106\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b6\u00103R\u0014\u00107\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b7\u00103R\u0014\u00108\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b8\u00103R\u0014\u00109\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b9\u00103R\u0014\u0010:\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b:\u0010&R\u0014\u0010;\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b;\u0010&R\u0014\u0010<\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b<\u0010&R\u0014\u0010=\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b=\u0010&R\u0014\u0010>\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b>\u0010&R\u0014\u0010?\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b?\u0010&R\u0014\u0010@\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u00103R\u0014\u0010A\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bA\u0010&R\u0014\u0010B\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bB\u0010&R\u0014\u0010C\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bC\u0010&R\u0014\u0010D\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bD\u0010&R\u0014\u0010E\u001a\u00020,8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bE\u0010.R\u0014\u0010F\u001a\u00020,8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bF\u0010.R\u0014\u0010G\u001a\u00020,8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bG\u0010.R\u0014\u0010H\u001a\u00020,8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bH\u0010.R\u0014\u0010I\u001a\u00020,8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bI\u0010.R\u0014\u0010K\u001a\u00020J8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bK\u0010LR\u0014\u0010M\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bM\u0010&R\u0014\u0010O\u001a\u00020N8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bO\u0010P\u00a8\u0006Q"}, d2={"Lrtx/kimiko/api/drags/components/InventoryComp.Companion;", "", "<init>", "()V", "", "componentEnabled", "()Z", "", "Lnet/minecraft/ItemStack;", "readInventory", "()Ljava/util/List;", "", "value", "smoothstep", "(F)F", "x", "", "index", "cellX", "(FI)F", "hudIconsEnabled", "Lrtx/kimiko/utils/render/fonts/Fonts;", "font", "", "text", "size", "", "textBounds", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;F)[F", "t", "easeOutBack", "sampleX", "sampleY", "titleColor", "(FF)I", "TITLE_TEXT", "Ljava/lang/String;", "TITLE_SIZE", "F", "PAD_X", "HEADER_ICON_GLYPH", "HEADER_ICON_SIZE", "HEADER_ICON_GAP", "HEADER_ICON_SLIDE", "", "HEADER_ICON_IN_SECONDS", "D", "HEADER_ICON_OUT_SECONDS", "DIVIDER_Y", "DIVIDER_HEIGHT", "DIVIDER_COLOR", "I", "RADIUS", "ITEMS_PER_ROW", "SLOT_START", "SLOT_END", "TOTAL", "ROWS", "CELL", "ITEM", "ITEM_INSET", "ORIGIN_X", "ORIGIN_Y", "PANEL_WIDTH", "LINE_COLOR", "SLOT_RATE", "MIN_P", "HEADER_HEIGHT", "HEADER_EXTRA_WIDTH", "WIDTH_SECONDS", "DIVIDER_IN_SECONDS", "DIVIDER_OUT_SECONDS", "ROW_IN_SECONDS", "ROW_OUT_SECONDS", "", "FADE_OUT_DELAY_MS", "J", "MIN_DIVIDER_ALPHA", "Lrtx/kimiko/utils/animations/Easing;", "SMOOTH", "Lrtx/kimiko/utils/animations/Easing;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final boolean componentEnabled() {
            InventoryModule module = ModuleManager.Companion.get().get(InventoryModule.class);
            return module != null && module.isEnabled();
        }

        private final List<ItemStack> readInventory() {
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient mc = minecraftClient2;
            ArrayList<ItemStack> list = new ArrayList<ItemStack>(27);
            ClientPlayerEntity player = mc.player;
            if (player != null) {
                PlayerInventory playerInventory2 = player.getInventory();
                Intrinsics.checkNotNullExpressionValue((Object)playerInventory2, (String)"getInventory(...)");
                PlayerInventory inventory = playerInventory2;
                for (int i = 9; i < 36; ++i) {
                    list.add(inventory.getStack(i));
                }
            }
            return list;
        }

        private final float smoothstep(float value) {
            float t = Math.max(0.0f, Math.min(1.0f, value));
            return t * t * (3.0f - 2.0f * t);
        }

        private final float cellX(float x, int index) {
            return x + 4.0f + 1.0f + (float)(index % 9) * 11.0f;
        }

        private final boolean hudIconsEnabled() {
            InterfaceModule module = InterfaceModule.Companion.getInstance();
            return module != null && module.hudIcons.getValue();
        }

        private final float[] textBounds(Fonts font, String text, float size) {
            float[] bounds = font.msdfBounds(text, size);
            if (bounds != null && bounds.length >= 4 && bounds[3] - bounds[1] > 0.0f) {
                return bounds;
            }
            float[] fArray = new float[]{0.0f, 0.0f, font.msdfWidth(text, size), size};
            return fArray;
        }

        private final float easeOutBack(float t) {
            float c1 = 1.70158f;
            float c3 = c1 + 1.0f;
            float u = t - 1.0f;
            return 1.0f + c3 * u * u * u + c1 * u * u;
        }

        private final int titleColor(float sampleX, float sampleY) {
            InterfaceModule module = InterfaceModule.Companion.getInstance();
            int client = module == null ? -1 : module.clientPrimaryColorOpaqueAt(sampleX, sampleY);
            return ColorEngine.lerpColor(-1, client, 0.1f);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

