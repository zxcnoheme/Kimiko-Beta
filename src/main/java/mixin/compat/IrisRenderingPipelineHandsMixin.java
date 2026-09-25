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
@Mixin(targets={"net/irisshaders/iris/pipeline/IrisRenderingPipeline"}, remap=false)
public abstract class IrisRenderingPipelineHandsMixin {
    @Inject(method={"finalizeLevelRendering"}, at={@At(value="TAIL")}, remap=false, require=0)
    private void kimiko$renderHandsFlameAfterIrisFinalPass(CallbackInfo ci) {
        IrisShaderCompat.renderHandsFlameAfterIrisFinalPass();
    }
}

