/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mods.acountswiher.ru.vidtu.ias.account.MicrosoftAccount
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 */
package mods.acountswiher.ru.vidtu.ias.account;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.UUID;
import mods.acountswiher.ru.vidtu.ias.account.MicrosoftAccount;
import mods.acountswiher.ru.vidtu.ias.account.OfflineAccount;
import mods.acountswiher.ru.vidtu.ias.auth.handlers.LoginHandler;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface Account {
    @Contract(pure=true)
    @NotNull
    public String type();

    @Contract(pure=true)
    @NotNull
    public String typeTipKey();

    @Contract(pure=true)
    @NotNull
    public UUID uuid();

    @Contract(pure=true)
    @NotNull
    public String name();

    @Contract(pure=true)
    public boolean canLogin();

    @Contract(pure=true)
    public boolean insecure();

    @Contract(pure=true)
    @NotNull
    public UUID skin();

    public void login(@NotNull LoginHandler var1);

    public void write(@NotNull DataOutput var1) throws IOException;

    public static void writeTyped(@NotNull DataOutput out, @NotNull Account account) throws IOException {
        String type = account.type();
        out.writeUTF(type);
        account.write(out);
    }

    @NotNull
    public static Account readTyped(@NotNull DataInput in) throws IOException {
        String type;
        return switch (type = in.readUTF()) {
            case "ias:offline_v1" -> OfflineAccount.readV1(in);
            case "ias:offline_v2" -> OfflineAccount.readV2(in);
            case "ias:microsoft_v1" -> MicrosoftAccount.read((DataInput)in);
            default -> throw new IllegalArgumentException("Unknown account type: " + type);
        };
    }
}

