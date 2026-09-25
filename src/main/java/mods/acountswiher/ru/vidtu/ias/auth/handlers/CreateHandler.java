/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mods.acountswiher.ru.vidtu.ias.account.MicrosoftAccount
 *  org.jetbrains.annotations.NotNull
 */
package mods.acountswiher.ru.vidtu.ias.auth.handlers;

import mods.acountswiher.ru.vidtu.ias.account.MicrosoftAccount;
import org.jetbrains.annotations.NotNull;

public interface CreateHandler {
    public boolean cancelled();

    public void stage(@NotNull String var1, Object ... var2);

    public void success(@NotNull MicrosoftAccount var1);

    public void error(@NotNull Throwable var1);
}

