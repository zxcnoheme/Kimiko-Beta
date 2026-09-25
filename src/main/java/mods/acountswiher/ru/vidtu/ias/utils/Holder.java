/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package mods.acountswiher.ru.vidtu.ias.utils;

import java.util.Objects;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Holder<T> {
    private T value;

    @Contract(pure=true)
    public Holder() {
        this.value = null;
    }

    @Contract(pure=true)
    public Holder(T value) {
        this.value = value;
    }

    @Contract(pure=true)
    public T get() {
        return this.value;
    }

    public void set(T value) {
        this.value = value;
    }

    @Contract(value="null -> false", pure=true)
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Holder)) {
            return false;
        }
        Holder that = (Holder)obj;
        return Objects.equals(this.value, that.value);
    }

    @Contract(pure=true)
    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    @Contract(pure=true)
    @NotNull
    public String toString() {
        return "Holder{value=" + String.valueOf(this.value) + "}";
    }
}

