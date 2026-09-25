/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package mods.waveycapes.support;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;
import lombok.Generated;
import mods.waveycapes.support.AnimationSupport;
import mods.waveycapes.support.ModSupport;

public class SupportManager {
    public static Set<ModSupport> mods = new HashSet<ModSupport>();
    public static Set<AnimationSupport> animationSupport = new HashSet<AnimationSupport>();
    public static Supplier<Float> alphaSupplier = () -> Float.valueOf(1.0f);

    public static Set<ModSupport> getSupportedMods() {
        return mods;
    }

    @Generated
    public static Supplier<Float> getAlphaSupplier() {
        return alphaSupplier;
    }

    @Generated
    public static void setAlphaSupplier(Supplier<Float> alphaSupplier) {
        SupportManager.alphaSupplier = alphaSupplier;
    }
}

