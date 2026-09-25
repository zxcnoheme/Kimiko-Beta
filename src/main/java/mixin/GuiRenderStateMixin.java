/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.render.state.GuiRenderState
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import java.util.List;
import net.minecraft.client.gui.render.state.GuiRenderState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.ui.UI;
import rtx.kimiko.utils.render.modules.post.GuiRenderStateLayerAccessor;

@Mixin(value={GuiRenderState.class})
public abstract class GuiRenderStateMixin
implements GuiRenderStateLayerAccessor {
    @Unique
    private int kimiko$layerSerial;
    @Shadow
    private int blurLayer;
    @Shadow
    @Final
    private List<?> rootLayers;

    @Inject(method={"applyBlur"}, at={@At(value="HEAD")}, cancellable=true)
    private void kimiko$mergeDuplicateBlurMark(CallbackInfo ci) {
        if (this.blurLayer != Integer.MAX_VALUE) {
            this.blurLayer = this.rootLayers.size() - 1;
            UI.requestVanillaBlurAtSplit();
            ci.cancel();
        }
    }

    @Override
    public int kimiko$getLayerSerial() {
        return this.kimiko$layerSerial;
    }

    @Inject(method={"createNewRootLayer"}, at={@At(value="RETURN")})
    private void kimiko$trackNextStratum(CallbackInfo ci) {
        ++this.kimiko$layerSerial;
    }

    @Inject(method={"goUpLayer"}, at={@At(value="RETURN")})
    private void kimiko$trackUpLayer(CallbackInfo ci) {
        ++this.kimiko$layerSerial;
    }

    @Inject(method={"clear"}, at={@At(value="HEAD")})
    private void kimiko$resetLayerSerial(CallbackInfo ci) {
        this.kimiko$layerSerial = 0;
    }
}

