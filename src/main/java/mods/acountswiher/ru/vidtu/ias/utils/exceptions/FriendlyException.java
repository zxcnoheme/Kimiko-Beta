/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package mods.acountswiher.ru.vidtu.ias.utils.exceptions;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class FriendlyException
extends RuntimeException {
    @NotNull
    private final String key;

    @Contract(pure=true)
    public FriendlyException(@NotNull String message, @NotNull String key) {
        super(message + " (friendly key: " + key + ")");
        this.key = key;
    }

    @Contract(pure=true)
    public FriendlyException(@NotNull String message, @Nullable Throwable cause, @NotNull String key) {
        super(message + " (friendly key: " + key + ")", cause);
        this.key = key;
    }

    @Contract(pure=true)
    @NotNull
    public String key() {
        return this.key;
    }

    @Contract(pure=true)
    @Nullable
    public static FriendlyException friendlyInChain(Throwable root) {
        Set dejaVu = Collections.newSetFromMap(new IdentityHashMap(8));
        for (int i = 0; i < 256 && root != null && dejaVu.add(root); root = root.getCause(), ++i) {
            if (!(root instanceof FriendlyException)) continue;
            FriendlyException fr = (FriendlyException)root;
            return fr;
        }
        return null;
    }
}

