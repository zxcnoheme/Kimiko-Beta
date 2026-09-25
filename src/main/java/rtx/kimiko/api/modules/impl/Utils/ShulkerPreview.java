/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.item.Item.TooltipContext
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.tooltip.TooltipType
 *  net.minecraft.world.World
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.util.math.MathHelper
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Utils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import mods.shulkerview.ShulkerPreviewHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.world.World;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.DrawEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.util.renderitem.BuiltRenderItem;
import rtx.kimiko.utils.render.util.renderitem.CustomItemRenderer;

@Feature(value={"shulkerpreview"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u001b\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0003b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u000bH\u0003b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0012R\u0016\u0010\u0017\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018\u00ca\u0001\u0010\b\u001a\u0012\f\b\u001b\u0012\b\b\fJ\u0004\b\b(\u001c\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/ShulkerPreview;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "onDisable", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "Lrtx/kimiko/api/events/impl/render/DrawEvent;", "onDraw", "(Lrtx/kimiko/api/events/impl/render/DrawEvent;)V", "", "x", "w", "clampX", "(FF)F", "y", "h", "clampY", "", "wasCtrlPressed", "Z", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "shulkerpreview", "rtx.kimiko:kimiko"})
public final class ShulkerPreview
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private boolean wasCtrlPressed;
    private static final int COLS = 9;
    private static final int ROWS = 3;
    private static final float SLOT = 18.0f;
    private static final float PAD = 8.0f;
    private static final float OVERLAY_W = 178.0f;
    private static final float OVERLAY_H = 70.0f;

    public ShulkerPreview() {
        super("Shulker Preview", "Показывает содержимое шалкера. SHIFT — заглянуть, CTRL — закрепить.", Category.UTILS);
    }

    @Override
    protected void onDisable() {
        ShulkerPreviewHelper.unfreeze();
        this.wasCtrlPressed = false;
    }

    @EventHandler
    private final void onTick(TickEvent event) {
        if (!event.isPre() || this.mc.player == null) {
            return;
        }
        boolean ctrlNow = ShulkerPreviewHelper.ctrlKeyPressed();
        if (ctrlNow && !this.wasCtrlPressed) {
            ShulkerPreviewHelper.tryFreeze();
        } else if (!ctrlNow && this.wasCtrlPressed) {
            ShulkerPreviewHelper.unfreeze();
        }
        this.wasCtrlPressed = ctrlNow;
    }

    @EventHandler
    private final void onDraw(DrawEvent event) {
        int i;
        if (!ShulkerPreviewHelper.isFrozen()) {
            return;
        }
        ItemStack frozen = ShulkerPreviewHelper.getFrozenShulker();
        if (frozen == null || frozen.isEmpty()) {
            return;
        }
        DrawContext drawContext2 = event.getGraphics();
        if (drawContext2 == null) {
            return;
        }
        DrawContext graphics = drawContext2;
        List<ItemStack> items = ShulkerPreviewHelper.getItems(frozen);
        float mouseX = (float)this.mc.mouse.getX() * (float)this.mc.getWindow().getScaleFactor() / (float)this.mc.getWindow().getWidth() * (float)this.mc.getWindow().getScaledWidth();
        float mouseY = (float)this.mc.mouse.getY() * (float)this.mc.getWindow().getScaleFactor() / (float)this.mc.getWindow().getHeight() * (float)this.mc.getWindow().getScaledHeight();
        float ox = this.clampX(ShulkerPreviewHelper.getFrozenOverlayX(), 178.0f);
        float oy = this.clampY(ShulkerPreviewHelper.getFrozenOverlayY(), 70.0f);
        int shulkerAccent = ShulkerPreviewHelper.shulkerColor(frozen);
        int r = shulkerAccent >> 16 & 0xFF;
        int g = shulkerAccent >> 8 & 0xFF;
        int b = shulkerAccent & 0xFF;
        int bgColor = ColorEngine.rgba(Math.max(8, r / 6), Math.max(8, g / 6), Math.max(8, b / 6), 230);
        int borderColor = ColorEngine.rgba(Math.min(255, r + 60), Math.min(255, g + 60), Math.min(255, b + 60), 180);
        int borderInner = ColorEngine.rgba(Math.min(255, r + 30), Math.min(255, g + 30), Math.min(255, b + 30), 100);
        Render2D.beginFrame(graphics);
        Render2D.blur(ox, oy, 178.0f, 70.0f, 5.0f, 14.0f, 1.0f, bgColor);
        Render2D.rect(ox, oy, 178.0f, 70.0f, 5.0f, bgColor);
        Render2D.outline(ox, oy, 178.0f, 70.0f, 5.0f, 1.0f, borderColor);
        Render2D.outline(ox + 1.0f, oy + 1.0f, 176.0f, 68.0f, 4.0f, 0.5f, borderInner);
        ItemStack hoveredItem = null;
        int n = ((Collection)items).size();
        for (int i2 = 0; i2 < n; ++i2) {
            boolean hovered;
            float sx = ox + 8.0f + (float)(i2 % 9) * 18.0f;
            float sy = oy + 8.0f + (float)(i2 / 9) * 18.0f;
            boolean bl = hovered = mouseX >= sx - 1.0f && mouseX < sx + (float)17 && mouseY >= sy - 1.0f && mouseY < sy + (float)17;
            if (!hovered) continue;
            Render2D.rect(sx - 2.0f, sy - 2.0f, 20.0f, 20.0f, 3.0f, ColorEngine.rgba(255, 255, 255, 50));
            Render2D.outline(sx - 2.0f, sy - 2.0f, 20.0f, 20.0f, 3.0f, 0.75f, ColorEngine.rgba(255, 255, 255, 120));
            if (items.get(i2).isEmpty()) continue;
            hoveredItem = items.get(i2);
        }
        Render2D.flush();
        CustomItemRenderer itemRenderer = CustomItemRenderer.Companion.getInstance();
        itemRenderer.beginFrame(graphics);
        int sx = ((Collection)items).size();
        for (i = 0; i < sx; ++i) {
            ItemStack stack = items.get(i);
            if (stack.isEmpty()) continue;
            float sx2 = ox + 8.0f + (float)(i % 9) * 18.0f;
            float sy = oy + 8.0f + (float)(i / 9) * 18.0f;
            itemRenderer.enqueue(new BuiltRenderItem(stack, sx2, sy, 16.0f, null, 0, 48, null));
        }
        sx = ((Collection)items).size();
        for (i = 0; i < sx; ++i) {
            ItemStack stack = items.get(i);
            if (stack.isEmpty() || stack.getCount() <= 1) continue;
            int sx3 = MathKt.roundToInt((float)(ox + 8.0f + (float)(i % 9) * 18.0f));
            int sy = MathKt.roundToInt((float)(oy + 8.0f + (float)(i / 9) * 18.0f));
            graphics.drawStackOverlay(this.mc.textRenderer, stack, sx3, sy);
        }
        if (hoveredItem != null && ShulkerPreviewHelper.previewKeyPressed()) {
            List list = hoveredItem.getTooltip(Item.TooltipContext.create((World)((World)this.mc.world)), (PlayerEntity)this.mc.player, (TooltipType)(this.mc.options.advancedItemTooltips ? TooltipType.ADVANCED : TooltipType.BASIC));
            Intrinsics.checkNotNullExpressionValue((Object)list, (String)"getTooltipLines(...)");
            List lines = list;
            Optional optional = hoveredItem.getTooltipData();
            Intrinsics.checkNotNullExpressionValue((Object)optional, (String)"getTooltipImage(...)");
            Optional visual = optional;
            graphics.drawTooltip(this.mc.textRenderer, lines, visual, (int)mouseX, (int)mouseY);
        }
    }

    private final float clampX(float x, float w) {
        float sw = this.mc.getWindow().getScaledWidth();
        return MathHelper.clamp((float)x, (float)4.0f, (float)(sw - w - 4.0f));
    }

    private final float clampY(float y, float h) {
        float sh = this.mc.getWindow().getScaledHeight();
        return MathHelper.clamp((float)y, (float)4.0f, (float)(sh - h - 4.0f));
    }

    @JvmStatic
    public static final boolean enabled() {
        return Companion.enabled();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\nR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u000e\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/ShulkerPreview.Companion;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "enabled", "()Z", "", "COLS", "I", "ROWS", "", "SLOT", "F", "PAD", "OVERLAY_W", "OVERLAY_H", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final boolean enabled() {
            ShulkerPreview module = ModuleManager.Companion.get().get(ShulkerPreview.class);
            return module != null && module.isEnabled();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

