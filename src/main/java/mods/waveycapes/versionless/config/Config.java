/*
 * Decompiled with CFR 0.152.
 */
package mods.waveycapes.versionless.config;

import mods.waveycapes.versionless.CapeMovement;
import mods.waveycapes.versionless.CapeStyle;
import mods.waveycapes.versionless.WindMode;

public class Config {
    public int configVersion = 2;
    public WindMode windMode = WindMode.NONE;
    public CapeStyle capeStyle = CapeStyle.SMOOTH;
    public CapeMovement capeMovement = CapeMovement.BASIC_SIMULATION_3D;
    public int gravity = 25;
    public int heightMultiplier = 6;
    public int straveMultiplier = 2;
}

