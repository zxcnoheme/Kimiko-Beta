/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  net.minecraft.client.render.entity.state.PlayerEntityRenderState
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.util.math.MatrixStack.Entry
 */
package mods.waveycapes;

import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;

public class CapeNodeCollector {
    private final List<CapeNode> capes = new ArrayList<CapeNode>();

    public void submitCape(PlayerEntityRenderState state, MatrixStack stack, int packedLight) {
        this.capes.add(new CapeNode(state, stack.peek().copy(), packedLight));
    }

    public void clear() {
        this.capes.clear();
    }

    @Generated
    public List<CapeNode> getCapes() {
        return this.capes;
    }

    public record CapeNode(PlayerEntityRenderState state, MatrixStack.Entry pose, int packedLight) {
    }
}

