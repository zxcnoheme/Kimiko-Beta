/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package mods.acountswiher.ru.vidtu.ias.utils.exceptions;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DevicePendingException
extends RuntimeException {
    @Contract(pure=true)
    public DevicePendingException(@NotNull String message) {
        super(message);
    }

    @Contract(pure=true)
    public DevicePendingException(@NotNull String message, @Nullable Throwable cause) {
        super(message, cause);
    }
}

