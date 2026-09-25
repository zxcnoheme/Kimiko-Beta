/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  net.minecraft.entity.LivingEntity
 */
package mods.waveycapes;

import lombok.Generated;
import mods.waveycapes.CapeNodeCollector;
import mods.waveycapes.CustomCapeRenderer;
import mods.waveycapes.delegate.PlayerDelegate;
import mods.waveycapes.support.AnimationSupport;
import mods.waveycapes.support.SupportManager;
import mods.waveycapes.versionless.ModBase;
import mods.waveycapes.versionless.nms.MinecraftPlayer;
import mods.waveycapes.versionless.util.Vector3;
import net.minecraft.entity.LivingEntity;

public abstract class WaveyCapesBase
extends ModBase {
    public static WaveyCapesBase INSTANCE;
    private final CapeNodeCollector capeNodeCollector = new CapeNodeCollector();
    private final CustomCapeRenderer renderer = new CustomCapeRenderer();

    public CapeNodeCollector getCapeNodeCollector() {
        return this.capeNodeCollector;
    }

    public CustomCapeRenderer getRenderer() {
        return this.renderer;
    }

    @Override
    public void init() {
        INSTANCE = this;
        super.init();
        this.initSupportHooks();
    }

    @Override
    public Vector3 applyModAnimations(MinecraftPlayer player, Vector3 pos) {
        for (AnimationSupport sup : SupportManager.animationSupport) {
            pos = sup.applyAnimationChanges((LivingEntity)((PlayerDelegate)player).getPlayer(), 0.0f, pos);
        }
        return pos;
    }

    @Override
    public void initSupportHooks() {
    }

    @Generated
    public static WaveyCapesBase getINSTANCE() {
        return INSTANCE;
    }
}

