/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package mods.acountswiher.ru.vidtu.ias.auth.handlers;

import java.util.concurrent.CompletableFuture;
import mods.acountswiher.ru.vidtu.ias.auth.LoginData;
import org.jetbrains.annotations.NotNull;

public interface LoginHandler {
    public boolean cancelled();

    public void stage(@NotNull String var1, Object ... var2);

    @NotNull
    public CompletableFuture<String> password();

    public void success(@NotNull LoginData var1, boolean var2);

    public void error(@NotNull Throwable var1);
}

