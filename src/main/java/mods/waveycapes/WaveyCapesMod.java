/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.ClientModInitializer
 */
package mods.waveycapes;

import mods.waveycapes.WaveyCapesBase;
import net.fabricmc.api.ClientModInitializer;

public class WaveyCapesMod
extends WaveyCapesBase
implements ClientModInitializer {
    public static final WaveyCapesMod INSTANCE = new WaveyCapesMod();

    public void onInitializeClient() {
        this.init();
    }

    @Override
    public void initSupportHooks() {
        super.initSupportHooks();
    }

    @Override
    public void init() {
        super.init();
    }
}

