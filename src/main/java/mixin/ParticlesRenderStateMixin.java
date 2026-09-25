/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.command.OrderedRenderCommandQueueImpl
 *  net.minecraft.client.render.SubmittableBatch
 *  net.minecraft.client.render.state.CameraRenderState
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.client.render.command.OrderedRenderCommandQueueImpl;
import net.minecraft.client.render.SubmittableBatch;
import net.minecraft.client.render.state.CameraRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.utils.render.modules.post.glowesp.GlowEspHook;

@Mixin(value={SubmittableBatch.class})
public class ParticlesRenderStateMixin {
    @Inject(method={"submit"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$glowBeforeParticles(OrderedRenderCommandQueueImpl submitNodeStorage, CameraRenderState cameraRenderState, CallbackInfo ci) {
        try {
            GlowEspHook.renderBeforeParticles();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }
}

