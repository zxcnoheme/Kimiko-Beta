/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Pseudo
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin.compat;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.utils.render.modules.post.handsflame.IrisShaderCompat;

@Pseudo
@Mixin(targets={"net/irisshaders/iris/pathways/HandRenderer"}, remap=false)
public abstract class IrisHandRendererHandsMixin {
    @Inject(method={"renderSolid", "renderTranslucent"}, at={@At(value="INVOKE", target="Lcom/mojang/blaze3d/systems/RenderSystem;backupProjectionMatrix()V")}, remap=false, require=0)
    private void kimiko$beginHandsFlameDepthCapture(CallbackInfo ci) {
        IrisShaderCompat.beginHandDepthCapture();
    }

    @Inject(method={"renderSolid", "renderTranslucent"}, at={@At(value="TAIL")}, remap=false, require=0)
    private void kimiko$endHandsFlameDepthCapture(CallbackInfo ci) {
        IrisShaderCompat.endHandDepthCapture();
    }
}

