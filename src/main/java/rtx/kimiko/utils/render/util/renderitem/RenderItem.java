/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.systems.RenderPass
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.fabricmc.fabric.api.resource.v1.ResourceLoader
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.Identifier
 *  net.minecraft.resource.ResourceType
 *  net.minecraft.resource.ResourceManager
 *  net.minecraft.resource.ResourceReloader
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.resource.SynchronousResourceReloader
 *  net.minecraft.registry.Registries
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.util.renderitem;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderPass;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloader;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.resource.SynchronousResourceReloader;
import net.minecraft.registry.Registries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.util.renderitem.BuiltRenderItem;
import rtx.kimiko.utils.render.util.renderitem.CustomItemRenderer;
import rtx.kimiko.utils.render.util.renderitem.RenderItemOptions;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u001d\u0010\t\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u000b\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000b\u0010\u0003J5\u0010\u0012\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000eH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0013J?\u0010\u0012\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0016J5\u0010\u0012\u001a\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000eH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0019J=\u0010\u0012\u001a\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000eH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u001bJ?\u0010\u0012\u001a\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001d\u0010\u0003J\u0013\u0010\u001e\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001e\u0010\u0003J\u0013\u0010\u001f\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001f\u0010\u0003J\u001d\u0010#\u001a\u00020\"2\b\u0010!\u001a\u0004\u0018\u00010 H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b#\u0010$J\u001d\u0010'\u001a\u00020\u00042\b\u0010&\u001a\u0004\u0018\u00010%H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b'\u0010(J\u0013\u0010)\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b)\u0010\u0003J\u0019\u0010+\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010*H\u0002\u00a2\u0006\u0004\b+\u0010,J7\u0010-\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b-\u0010\u001bJ7\u00100\u001a\u00020\u00042\u0006\u0010/\u001a\u00020.2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b0\u00101J\u0019\u00103\u001a\u00020\u00172\b\u00102\u001a\u0004\u0018\u00010\fH\u0002\u00a2\u0006\u0004\b3\u00104J\u000f\u00106\u001a\u000205H\u0002\u00a2\u0006\u0004\b6\u00107J\u000f\u00108\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b8\u0010\u0003R\u0016\u00109\u001a\u00020\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b9\u0010:\u00a8\u0006;"}, d2={"Lrtx/kimiko/utils/render/util/renderitem/RenderItem;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "init", "Lnet/minecraft/DrawContext;", "graphics", "beginFrame", "(Lnet/minecraft/DrawContext;)V", "flush", "", "id", "", "x", "y", "size", "item", "(Ljava/lang/String;FFF)V", "Lrtx/kimiko/utils/render/util/renderitem/RenderItemOptions;", "options", "(Ljava/lang/String;FFFLrtx/kimiko/utils/render/util/renderitem/RenderItemOptions;)V", "Lnet/minecraft/ItemStack;", "stack", "(Lnet/minecraft/ItemStack;FFF)V", "alpha", "(Lnet/minecraft/ItemStack;FFFF)V", "(Lnet/minecraft/ItemStack;FFFLrtx/kimiko/utils/render/util/renderitem/RenderItemOptions;)V", "close", "beginGuiFrame", "prepareBuffers", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "", "isItemPipeline", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)Z", "Lcom/mojang/blaze3d/systems/RenderPass;", "renderPass", "bindParams", "(Lcom/mojang/blaze3d/systems/RenderPass;)V", "clearCaches", "Lrtx/kimiko/utils/render/util/renderitem/BuiltRenderItem;", "drawDecorations", "(Lrtx/kimiko/utils/render/util/renderitem/BuiltRenderItem;)V", "drawDurabilityBar", "", "count", "drawCount", "(IFFFF)V", "rawId", "stackOf", "(Ljava/lang/String;)Lnet/minecraft/ItemStack;", "Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer;", "renderer", "()Lrtx/kimiko/utils/render/util/renderitem/CustomItemRenderer;", "registerReloadListener", "reloadListenerRegistered", "Z", "rtx.kimiko:kimiko"})
public final class RenderItem {
    @NotNull
    public static final RenderItem INSTANCE = new RenderItem();
    private static boolean reloadListenerRegistered;

    private RenderItem() {
    }

    @JvmStatic
    public static final void init() {
        INSTANCE.registerReloadListener();
    }

    @JvmStatic
    public static final void beginFrame(@Nullable DrawContext graphics) {
        INSTANCE.renderer().beginFrame(graphics);
    }

    @JvmStatic
    public static final void flush() {
        INSTANCE.renderer().flush();
    }

    @JvmStatic
    public static final void item(@Nullable String id, float x, float y, float size) {
        RenderItem.item(INSTANCE.stackOf(id), x, y, size, RenderItemOptions.Companion.defaults());
    }

    @JvmStatic
    public static final void item(@Nullable String id, float x, float y, float size, @Nullable RenderItemOptions options) {
        RenderItem.item(INSTANCE.stackOf(id), x, y, size, options);
    }

    @JvmStatic
    public static final void item(@Nullable ItemStack stack, float x, float y, float size) {
        RenderItem.item(stack, x, y, size, RenderItemOptions.Companion.defaults());
    }

    @JvmStatic
    public static final void item(@Nullable ItemStack stack, float x, float y, float size, float alpha) {
        RenderItem.item(stack, x, y, size, RenderItemOptions.Companion.defaults().alpha(alpha));
    }

    @JvmStatic
    public static final void item(@Nullable ItemStack stack, float x, float y, float size, @Nullable RenderItemOptions options) {
        BuiltRenderItem item = new BuiltRenderItem(stack, x, y, size, options, 0);
        INSTANCE.renderer().enqueue(item);
        INSTANCE.drawDecorations(item);
    }

    @JvmStatic
    public static final void close() {
        CustomItemRenderer.Companion.closeInstance();
    }

    @JvmStatic
    public static final void beginGuiFrame() {
        INSTANCE.renderer().beginGuiFrame();
    }

    @JvmStatic
    public static final void prepareBuffers() {
        INSTANCE.renderer().prepareBuffers();
    }

    @JvmStatic
    public static final boolean isItemPipeline(@Nullable RenderPipeline pipeline) {
        return INSTANCE.renderer().isItemPipeline(pipeline);
    }

    @JvmStatic
    public static final void bindParams(@Nullable RenderPass renderPass) {
        INSTANCE.renderer().bindParams(renderPass);
    }

    @JvmStatic
    public static final void clearCaches() {
        INSTANCE.renderer().clearCaches();
    }

    private final void drawDecorations(BuiltRenderItem item) {
        if (item == null || !item.visible()) {
            return;
        }
        ItemStack stack = item.stack();
        RenderItemOptions options = item.options();
        if (options.showDurability() && stack.isItemBarVisible()) {
            this.drawDurabilityBar(stack, item.x(), item.y(), item.size(), options.alpha());
        }
        if (options.showCount() && stack.getCount() > 1) {
            this.drawCount(stack.getCount(), item.x(), item.y(), item.size(), options.alpha());
        }
    }

    private final void drawDurabilityBar(ItemStack stack, float x, float y, float size, float alpha) {
        float barWidth = Math.max(8.0f, size * 0.78f);
        float barHeight = Math.max(1.5f, size * 0.07f);
        float barX = x + (size - barWidth) * 0.5f;
        float barY = y + size - barHeight - Math.max(1.0f, size * 0.08f);
        float fill = Math.max(0.0f, Math.min(1.0f, (float)stack.getItemBarStep() / 13.0f));
        int barColor = stack.getItemBarColor();
        Render2D.rect(barX, barY, barWidth, barHeight, barHeight * 0.5f, ColorEngine.rgba(0, 0, 0, Math.round(150.0f * alpha)));
        Render2D.rect(barX, barY, Math.max(1.0f, barWidth * fill), barHeight, barHeight * 0.5f, ColorEngine.rgba(barColor >>> 16 & 0xFF, barColor >>> 8 & 0xFF, barColor & 0xFF, Math.round(240.0f * alpha)));
    }

    private final void drawCount(int count, float x, float y, float size, float alpha) {
        String text = String.valueOf(count);
        float textSize = Math.max(6.0f, size * 0.34f);
        float textWidth = Fonts.SEMI_BOLD.width(text, textSize);
        float textX = x + size - textWidth + Math.max(1.0f, size * 0.04f);
        float textY = y + size - textSize - Math.max(0.0f, size * 0.01f);
        int shadowAlpha = Math.round(170.0f * alpha);
        int textAlpha = Math.round(255.0f * alpha);
        Fonts.SEMI_BOLD.draw(text, textX + 1.0f, textY + 1.0f, textSize, ColorEngine.rgba(0, 0, 0, shadowAlpha));
        Fonts.SEMI_BOLD.draw(text, textX, textY, textSize, ColorEngine.rgba(255, 255, 255, textAlpha));
    }

    private final ItemStack stackOf(String rawId) {
        if (rawId == null || StringsKt.isBlank((CharSequence)rawId)) {
            return ItemStack.EMPTY;
        }
        String trimmed = rawId.trim();
        Identifier id = trimmed.indexOf(':') >= 0 ? Identifier.tryParse(trimmed) : Identifier.ofVanilla(trimmed);
        if (id == null) {
            return ItemStack.EMPTY;
        }
        Item item = Registries.ITEM.getOptionalValue(id).orElse(null);
        if (item == null) {
            return ItemStack.EMPTY;
        }
        ItemStack stack = item.getDefaultStack();
        return stack != null ? stack : ItemStack.EMPTY;
    }

    private final CustomItemRenderer renderer() {
        return CustomItemRenderer.Companion.getInstance();
    }

    private final void registerReloadListener() {
        if (reloadListenerRegistered) {
            return;
        }
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"render_item_cache");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        Identifier id = identifier2;
        ResourceLoader.get((ResourceType)ResourceType.CLIENT_RESOURCES).registerReloader(id, (ResourceReloader)((SynchronousResourceReloader)RenderItem::registerReloadListener$lambda$0));
        reloadListenerRegistered = true;
    }

    private static final void registerReloadListener$lambda$0(ResourceManager it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        RenderItem.clearCaches();
    }
}

