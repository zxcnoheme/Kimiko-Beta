/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.wrapoperation.Operation
 *  com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation
 *  com.llamalad7.mixinextras.sugar.Local
 *  net.minecraft.text.Text
 *  net.minecraft.scoreboard.ScoreboardObjective
 *  net.minecraft.scoreboard.Scoreboard
 *  net.minecraft.client.font.TextRenderer
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.hud.PlayerListHud
 *  net.minecraft.text.StringVisitable
 *  net.minecraft.client.network.PlayerListEntry
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.text.Text;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.text.StringVisitable;
import net.minecraft.client.network.PlayerListEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.modules.impl.Utils.Globals;
import rtx.kimiko.api.modules.impl.Visuals.BetterMinecraft;
import rtx.kimiko.utils.animations.TabListAnimationAccess;
import rtx.kimiko.utils.net.ClientPresence;
import rtx.kimiko.utils.render.others.TabBadgeRenderer;

@Mixin(value={PlayerListHud.class})
public abstract class PlayerTabOverlayMixin
implements TabListAnimationAccess {
    @Unique
    private static final long KIMIKO_TAB_ANIMATION_MS = 300L;
    @Unique
    private static final int KIMIKO_DECORATED_NAME_GAP = 4;
    @Unique
    private long kimiko$animationStart;
    @Unique
    private long kimiko$animationDuration;
    @Unique
    private float kimiko$animationFrom;
    @Unique
    private float kimiko$animationTarget;
    @Unique
    private boolean kimiko$visible;
    @Unique
    private boolean kimiko$scaledForAnimation;

    @Inject(method={"setVisible"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$trackVisibility(boolean visible, CallbackInfo ci) {
        if (visible != this.kimiko$visible) {
            float current = this.kimiko$currentScale();
            this.kimiko$visible = visible;
            this.kimiko$animationFrom = current;
            this.kimiko$animationTarget = visible ? 1.0f : 0.0f;
            this.kimiko$animationStart = System.currentTimeMillis();
            this.kimiko$animationDuration = Math.max(1L, (long)Math.round(300.0f * Math.abs(this.kimiko$animationTarget - this.kimiko$animationFrom)));
        }
    }

    @Inject(method={"render"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$beginTabAnimation(DrawContext graphics, int windowWidth, Scoreboard scoreboard, ScoreboardObjective objective, CallbackInfo ci) {
        this.kimiko$scaledForAnimation = BetterMinecraft.tabAnimationEnabled();
        if (!this.kimiko$scaledForAnimation) {
            return;
        }
        float scale = Math.max(0.01f, Math.min(1.15f, this.kimiko$currentScale()));
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate((float)windowWidth / 2.0f, 10.0f);
        graphics.getMatrices().scale(scale, scale);
        graphics.getMatrices().translate((float)(-windowWidth) / 2.0f, -10.0f);
    }

    @Inject(method={"render"}, at={@At(value="RETURN")}, require=0)
    private void kimiko$endTabAnimation(DrawContext graphics, int windowWidth, Scoreboard scoreboard, ScoreboardObjective objective, CallbackInfo ci) {
        if (this.kimiko$scaledForAnimation) {
            graphics.getMatrices().popMatrix();
            this.kimiko$scaledForAnimation = false;
        }
    }

    @WrapOperation(method={"render"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/font/TextRenderer;getWidth(Lnet/minecraft/text/StringVisitable;)I", ordinal=0)}, require=1)
    private int kimiko$includeBadgeInFullNameWidth(TextRenderer font, StringVisitable fullServerName, Operation<Integer> original, @Local PlayerListEntry playerInfo) {
        int fullServerNameWidth = (Integer)original.call(new Object[]{font, fullServerName});
        if (!PlayerTabOverlayMixin.kimiko$hasTabBadge(playerInfo)) {
            return fullServerNameWidth;
        }
        return fullServerNameWidth + PlayerTabOverlayMixin.kimiko$badgeWidth(playerInfo, fullServerName);
    }

    @WrapOperation(method={"render"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)V")}, require=1)
    private void kimiko$drawFullTabName(DrawContext graphics, TextRenderer font, Text fullServerName, int x, int y, int color, Operation<Void> original, @Local PlayerListEntry playerInfo) {
        if (!PlayerTabOverlayMixin.kimiko$hasTabBadge(playerInfo)) {
            original.call(new Object[]{graphics, font, fullServerName, x, y, color});
            return;
        }
        original.call(new Object[]{graphics, font, fullServerName, x + PlayerTabOverlayMixin.kimiko$badgeWidth(playerInfo, (StringVisitable)fullServerName), y, color});
        TabBadgeRenderer.drawBadge(graphics, font, x, y);
    }

    @Unique
    private static int kimiko$badgeWidth(PlayerListEntry playerInfo, StringVisitable fullServerName) {
        int width = TabBadgeRenderer.extraWidth();
        if (playerInfo == null || !(fullServerName instanceof Text)) {
            return width;
        }
        Text component = (Text)fullServerName;
        String profileName = playerInfo.getProfile().name().trim();
        String displayName = component.getString().trim();
        return width + (!profileName.isEmpty() && !displayName.equals(profileName) ? 4 : 0);
    }

    @Unique
    private static boolean kimiko$hasTabBadge(PlayerListEntry playerInfo) {
        return playerInfo != null && Globals.tabBadge() && ClientPresence.INSTANCE.isKimikoUser(playerInfo.getProfile().name());
    }

    @Override
    public boolean kimiko$shouldRenderClosingTab() {
        return BetterMinecraft.tabAnimationEnabled() && !this.kimiko$visible && this.kimiko$currentScale() > 0.01f;
    }

    @Unique
    private float kimiko$currentScale() {
        long duration = Math.max(1L, this.kimiko$animationDuration);
        float progress = Math.min(1.0f, (float)(System.currentTimeMillis() - this.kimiko$animationStart) / (float)duration);
        float eased = this.kimiko$animationTarget > this.kimiko$animationFrom ? this.kimiko$easeOutBack(progress) : this.kimiko$easeInBack(progress);
        return this.kimiko$animationFrom + (this.kimiko$animationTarget - this.kimiko$animationFrom) * eased;
    }

    @Unique
    private float kimiko$easeOutBack(float value) {
        float c1 = 1.70158f;
        float c3 = c1 + 1.0f;
        float t = value - 1.0f;
        return 1.0f + c3 * t * t * t + c1 * t * t;
    }

    @Unique
    private float kimiko$easeInBack(float value) {
        float c1 = 1.70158f;
        float c3 = c1 + 1.0f;
        return c3 * value * value * value - c1 * value * value;
    }
}

