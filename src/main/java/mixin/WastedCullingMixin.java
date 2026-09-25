/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.WorldRenderer
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 */
package mixin;

import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import rtx.kimiko.api.modules.impl.Visuals.WastedDeath;

@Mixin(value={WorldRenderer.class}, priority=1100)
public abstract class WastedCullingMixin {
    @ModifyArg(method={"updateCamera"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/ChunkRenderingDataPreparer;updateSectionOcclusionGraph(ZLnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/Frustum;Ljava/util/List;Lit/unimi/dsi/fastutil/longs/LongOpenHashSet;)V"), index=0, require=0)
    private boolean kimiko$wastedDisableSmartCull(boolean smartCull) {
        return smartCull && !WastedDeath.isRunning();
    }
}

