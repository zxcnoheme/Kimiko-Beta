/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod
 *  com.llamalad7.mixinextras.injector.wrapoperation.Operation
 *  com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.text.Text
 *  net.minecraft.scoreboard.ScoreboardObjective
 *  net.minecraft.scoreboard.Scoreboard
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.font.TextRenderer
 *  net.minecraft.client.gui.hud.InGameHud
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.hud.PlayerListHud
 *  net.minecraft.scoreboard.ScoreboardDisplaySlot
 *  net.minecraft.client.render.RenderTickCounter
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.scoreboard.ScoreboardDisplaySlot;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.drags.components.ScoreboardComp;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.impl.render.DrawEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.CustomHotbar;
import rtx.kimiko.api.modules.impl.Interface.PotionsModule;
import rtx.kimiko.api.modules.impl.Utils.StreamerMode;
import rtx.kimiko.api.modules.impl.Utils.guishare.RemoteGuiWorld;
import rtx.kimiko.api.modules.impl.Visuals.BetterMinecraft;
import rtx.kimiko.api.modules.impl.Visuals.Crosshair;
import rtx.kimiko.api.modules.impl.Visuals.ItemHighlight;
import rtx.kimiko.api.modules.impl.Visuals.NoRender;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionWheelOverlay;
import rtx.kimiko.api.ui.BaseScreen;
import rtx.kimiko.api.ui.UI;
import rtx.kimiko.api.ui.logo.LogoToy;
import rtx.kimiko.utils.animations.TabListAnimationAccess;
import rtx.kimiko.utils.render.others.LoadingVisualGuard;
import rtx.kimiko.utils.render.others.hud.HotbarItemAnimator;
import rtx.kimiko.utils.render.others.hud.VanillaHudTransform;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.util.warmup.Render2DWarmup;

@Mixin(value={InGameHud.class})
public abstract class GuiMixin {
    @Shadow
    @Final
    private MinecraftClient client;
    @Shadow
    @Final
    private PlayerListHud playerListHud;
    @Shadow
    @Final
    private static Identifier HOTBAR_TEXTURE;
    @Shadow
    @Final
    private static Identifier HOTBAR_SELECTION_TEXTURE;
    @Shadow
    @Final
    private static Identifier HOTBAR_OFFHAND_LEFT_TEXTURE;
    @Shadow
    @Final
    private static Identifier HOTBAR_OFFHAND_RIGHT_TEXTURE;
    @Unique
    private boolean kimiko$debugEarly;
    @Unique
    private boolean kimiko$scoreboardPushed;
    @Unique
    private float kimiko$left;
    @Unique
    private float kimiko$top;
    @Unique
    private float kimiko$right;
    @Unique
    private float kimiko$bottom;
    @Unique
    private boolean kimiko$boxStarted;

    @Shadow
    public abstract void renderDebugHud(DrawContext var1);

    @Inject(method={"render"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/InGameHud;renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", shift=At.Shift.BEFORE)})
    private void kimiko$renderHudUnderVanilla(DrawContext graphics, RenderTickCounter deltaTracker, CallbackInfo ci) {
        if (LoadingVisualGuard.shouldSuppressHud(this.client)) {
            return;
        }
        EventBus.get().post(new HudRenderEvent(graphics, deltaTracker));
        graphics.createNewRootLayer();
    }

    @Inject(method={"render"}, at={@At(value="HEAD")})
    private void kimiko$renderRemoteGuiPanels(DrawContext graphics, RenderTickCounter deltaTracker, CallbackInfo ci) {
        if (LoadingVisualGuard.shouldSuppressHud(this.client)) {
            return;
        }
        RemoteGuiWorld.renderPanels(graphics);
    }

    @Inject(method={"render"}, at={@At(value="RETURN")})
    private void kimiko$renderTopLayer(DrawContext graphics, RenderTickCounter deltaTracker, CallbackInfo ci) {
        if (LoadingVisualGuard.shouldSuppressHud(this.client)) {
            Render2DWarmup.runWarmupFrame(graphics);
            return;
        }
        EventBus.get().post(new DrawEvent(graphics, deltaTracker));
        UI.renderClosingPanelOverHud(graphics);
        BaseScreen.renderClosingOverlay(graphics);
        LogoToy.renderOverlay(graphics);
        EmotionWheelOverlay.render(graphics);
        if (UI.isOpen() && this.client.getDebugHud() != null && this.client.getDebugHud().shouldShowDebugHud()) {
            this.kimiko$debugEarly = true;
            this.renderDebugHud(graphics);
            this.kimiko$debugEarly = false;
        }
    }

    @Inject(method={"renderDebugHud"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$moveDebugUnderClickGui(DrawContext graphics, CallbackInfo ci) {
        if (!this.kimiko$debugEarly && UI.isOpen()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderStatusEffectOverlay"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$hideVanillaEffects(DrawContext graphics, RenderTickCounter deltaTracker, CallbackInfo ci) {
        PotionsModule potions = ModuleManager.get().get(PotionsModule.class);
        if (potions != null && potions.isEnabled()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderFood"}, at={@At(value="RETURN")}, require=0)
    private void kimiko$renderSaturation(DrawContext graphics, PlayerEntity player, int top, int right, CallbackInfo ci) {
        BetterMinecraft.renderSaturation(graphics, player, top, right);
    }

    @Inject(method={"renderHotbar"}, at={@At(value="HEAD")}, require=1)
    private void kimiko$renderCustomHotbar(DrawContext graphics, RenderTickCounter deltaTracker, CallbackInfo ci) {
        CustomHotbar module = CustomHotbar.getInstance();
        if (module != null && module.isEnabled()) {
            module.render(graphics);
        }
    }

    @Redirect(method={"renderHotbar"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIII)V"), require=1)
    private void kimiko$replaceHotbarSprites(DrawContext graphics, RenderPipeline pipeline, Identifier sprite, int x, int y, int width, int height) {
        if (CustomHotbar.isActive() && GuiMixin.isHotbarFrame(sprite)) {
            return;
        }
        graphics.drawGuiTexture(pipeline, sprite, x, y, width, height);
    }

    private static boolean isHotbarFrame(Identifier sprite) {
        return HOTBAR_TEXTURE.equals((Object)sprite) || HOTBAR_SELECTION_TEXTURE.equals((Object)sprite) || HOTBAR_OFFHAND_LEFT_TEXTURE.equals((Object)sprite) || HOTBAR_OFFHAND_RIGHT_TEXTURE.equals((Object)sprite);
    }

    @Inject(method={"renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$hideScoreboard(DrawContext graphics, RenderTickCounter deltaTracker, CallbackInfo ci) {
        if (NoRender.isActive("Таблица счёта")) {
            ci.cancel();
        }
    }

    @Inject(method={"renderBossBarHud"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$hideBossBar(DrawContext graphics, RenderTickCounter deltaTracker, CallbackInfo ci) {
        if (NoRender.isActive("Полоса босса")) {
            ci.cancel();
        }
    }

    @Inject(method={"renderCrosshair"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$hideVanillaCrosshair(DrawContext graphics, RenderTickCounter deltaTracker, CallbackInfo ci) {
        Crosshair crosshair = ModuleManager.get().get(Crosshair.class);
        if (crosshair != null && crosshair.isEnabled()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$beginScoreboard(DrawContext graphics, ScoreboardObjective objective, CallbackInfo ci) {
        this.kimiko$boxStarted = false;
        this.kimiko$scoreboardPushed = false;
        ScoreboardComp comp = ScoreboardComp.get();
        if (comp == null || !comp.hasLayout()) {
            return;
        }
        this.kimiko$scoreboardPushed = VanillaHudTransform.push(graphics, comp, comp.vanillaLeft(), comp.vanillaTop(), comp.scale());
    }

    @WrapOperation(method={"renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V")}, require=0)
    private void kimiko$measureScoreboard(DrawContext instance, int x1, int y1, int x2, int y2, int color, Operation<Void> original) {
        if (!this.kimiko$boxStarted) {
            this.kimiko$boxStarted = true;
            this.kimiko$left = x1;
            this.kimiko$top = y1;
            this.kimiko$right = x2;
            this.kimiko$bottom = y2;
        } else {
            this.kimiko$left = Math.min(this.kimiko$left, (float)x1);
            this.kimiko$top = Math.min(this.kimiko$top, (float)y1);
            this.kimiko$right = Math.max(this.kimiko$right, (float)x2);
            this.kimiko$bottom = Math.max(this.kimiko$bottom, (float)y2);
        }
        original.call(new Object[]{instance, x1, y1, x2, y2, color});
    }

    @Inject(method={"renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V"}, at={@At(value="RETURN")}, require=0)
    private void kimiko$endScoreboard(DrawContext graphics, ScoreboardObjective objective, CallbackInfo ci) {
        ScoreboardComp comp;
        if (this.kimiko$scoreboardPushed) {
            VanillaHudTransform.pop(graphics);
            this.kimiko$scoreboardPushed = false;
        }
        if ((comp = ScoreboardComp.get()) != null && this.kimiko$boxStarted) {
            comp.measured(this.kimiko$left, this.kimiko$top, this.kimiko$right - this.kimiko$left, this.kimiko$bottom - this.kimiko$top);
        }
    }

    @Inject(method={"renderPlayerList"}, at={@At(value="TAIL")}, require=0)
    private void kimiko$renderClosingTabList(DrawContext graphics, RenderTickCounter deltaTracker, CallbackInfo ci) {
        TabListAnimationAccess animation;
        PlayerListHud playerListHud2 = this.playerListHud;
        if (!(playerListHud2 instanceof TabListAnimationAccess) || !(animation = (TabListAnimationAccess)playerListHud2).kimiko$shouldRenderClosingTab()) {
            return;
        }
        if (this.client.world == null || this.client.options.playerListKey.isPressed()) {
            return;
        }
        Scoreboard scoreboard = this.client.world.getScoreboard();
        ScoreboardObjective objective = scoreboard.getObjectiveForSlot(ScoreboardDisplaySlot.LIST);
        this.playerListHud.render(graphics, graphics.getScaledWindowWidth(), scoreboard, objective);
    }

    @ModifyArg(method={"renderHotbar"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIII)V", ordinal=1), index=2, require=1)
    private int kimiko$animateHotbarSelection(int targetX) {
        return BetterMinecraft.animateHotbarSelectionX(targetX);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @WrapMethod(method={"renderMainHud"})
    private void kimiko$liftHotbarWithChat(DrawContext guiGraphics, RenderTickCounter deltaTracker, Operation<Void> original) {
        float offset = BetterMinecraft.chatHotbarLiftOffset();
        if (offset <= 0.01f) {
            original.call(new Object[]{guiGraphics, deltaTracker});
            HotbarItemAnimator.renderOverlay(guiGraphics);
            return;
        }
        guiGraphics.getMatrices().pushMatrix();
        guiGraphics.getMatrices().translate(0.0f, -offset);
        try {
            original.call(new Object[]{guiGraphics, deltaTracker});
            HotbarItemAnimator.renderOverlay(guiGraphics);
        }
        finally {
            guiGraphics.getMatrices().popMatrix();
        }
    }

    @WrapOperation(method={"renderHotbarItem"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawContext;drawItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;III)V")})
    private void kimiko$animateHotbarItem(DrawContext graphics, LivingEntity entity, ItemStack stack, int x, int y, int seed, Operation<Void> original) {
        if (CustomHotbar.isActive() && HotbarItemAnimator.captureItem(graphics, entity, stack, x, y, seed)) {
            return;
        }
        original.call(new Object[]{graphics, entity, stack, x, y, seed});
    }

    @WrapOperation(method={"renderHotbarItem"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawContext;drawStackOverlay(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;II)V")})
    private void kimiko$moveHotbarItemDecorations(DrawContext graphics, TextRenderer font, ItemStack stack, int x, int y, Operation<Void> original) {
        if (CustomHotbar.isActive() && HotbarItemAnimator.captureDecorations(graphics, stack, x, y)) {
            return;
        }
        original.call(new Object[]{graphics, font, stack, x, y});
    }

    @Inject(method={"renderHotbarItem"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$hotbarHighlightBackground(DrawContext graphics, int x, int y, RenderTickCounter deltaTracker, PlayerEntity player, ItemStack stack, int seed, CallbackInfo ci) {
        if (graphics == null || stack == null || stack.isEmpty()) {
            return;
        }
        ItemHighlight module = ItemHighlight.getInstance();
        if (module == null) {
            return;
        }
        int argb = module.backgroundFor(stack, true);
        if (argb != 0) {
            if (CustomHotbar.isActive()) {
                float nativeScale = (float)Render2DCoordinateSpace.guiScale() / Render2DCoordinateSpace.designGuiScale();
                graphics.getMatrices().pushMatrix();
                graphics.getMatrices().scale(nativeScale);
                Render2D.beginFrame(graphics);
                float[] frame = HotbarItemAnimator.highlightFrame(graphics, x, y, CustomHotbar.cellCornerRadius());
                if (frame != null) {
                    module.drawRoundedSlotBackground(frame[0], frame[1], frame[2], frame[3], frame[4], argb, 1.0f);
                } else {
                    module.drawRoundedSlotBackground(x, y, 16.0f, argb, 1.0f);
                }
                Render2D.flush();
                graphics.getMatrices().popMatrix();
            } else {
                module.drawSlotBackground(graphics, x, y, argb);
            }
        }
    }

    @WrapOperation(method={"renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIIZ)V")}, require=0)
    private void kimiko$scoreboardRank(DrawContext graphics, TextRenderer font, Text text, int x, int y, int color, boolean shadow, Operation<Void> original) {
        original.call(new Object[]{graphics, font, StreamerMode.applySelfRank(text), x, y, color, shadow});
    }
}

