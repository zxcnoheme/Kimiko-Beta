/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.hud.BossBarHud
 *  net.minecraft.client.gui.hud.ClientBossBar
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import java.util.Map;
import java.util.UUID;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.client.gui.hud.ClientBossBar;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.drags.components.BossBarComp;
import rtx.kimiko.utils.render.others.hud.VanillaHudTransform;

@Mixin(value={BossBarHud.class})
public abstract class BossHealthOverlayMoveMixin {
    @Unique
    private static final int KIMIKO_BAR_WIDTH = 182;
    @Unique
    private static final int KIMIKO_FIRST_Y = 12;
    @Unique
    private static final int KIMIKO_STEP = 19;
    @Unique
    private static final int KIMIKO_BAR_HEIGHT = 5;
    @Unique
    private static final int KIMIKO_NAME_LIFT = 9;
    @Shadow
    @Final
    private Map<UUID, ClientBossBar> bossBars;
    @Unique
    private boolean kimiko$bossPushed;

    @Inject(method={"render"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$beginBoss(DrawContext graphics, CallbackInfo ci) {
        this.kimiko$bossPushed = false;
        BossBarComp comp = BossBarComp.get();
        if (comp == null || this.bossBars.isEmpty()) {
            return;
        }
        int guiWidth = graphics.getScaledWindowWidth();
        int guiHeight = graphics.getScaledWindowHeight();
        int visible = 0;
        int y = 12;
        for (int i = 0; i < this.bossBars.size(); ++i) {
            ++visible;
            if ((y += 19) >= guiHeight / 3) break;
        }
        float left = guiWidth / 2 - 91;
        float top = 3.0f;
        float bottom = 12 + (visible - 1) * 19 + 5;
        boolean hadLayout = comp.hasLayout();
        comp.measured(left, top, 182.0f, bottom - top);
        if (!hadLayout) {
            return;
        }
        this.kimiko$bossPushed = VanillaHudTransform.push(graphics, comp, left, top, comp.scale());
    }

    @Inject(method={"render"}, at={@At(value="RETURN")}, require=0)
    private void kimiko$endBoss(DrawContext graphics, CallbackInfo ci) {
        if (this.kimiko$bossPushed) {
            VanillaHudTransform.pop(graphics);
            this.kimiko$bossPushed = false;
        }
    }
}

