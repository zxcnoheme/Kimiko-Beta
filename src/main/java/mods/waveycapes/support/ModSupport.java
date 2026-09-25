/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 */
package mods.waveycapes.support;

import mods.waveycapes.CapeRenderer;
import mods.waveycapes.compat.PlayerWrapper;
import net.minecraft.client.network.AbstractClientPlayerEntity;

public interface ModSupport {
    public boolean shouldBeUsed(PlayerWrapper var1);

    @Deprecated
    default public boolean shouldBeUsed(AbstractClientPlayerEntity player) {
        return false;
    }

    public CapeRenderer getRenderer();

    public boolean blockFeatureRenderer(Object var1);
}

