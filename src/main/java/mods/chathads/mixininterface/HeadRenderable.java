/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package mods.chathads.mixininterface;

import mods.chathads.HeadData;
import org.jetbrains.annotations.NotNull;

public interface HeadRenderable {
    @NotNull
    public HeadData chatheads$getHeadData();

    public void chatheads$setHeadData(@NotNull HeadData var1);
}

