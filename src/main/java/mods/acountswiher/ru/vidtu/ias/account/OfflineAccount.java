/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package mods.acountswiher.ru.vidtu.ias.account;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;
import mods.acountswiher.ru.vidtu.ias.account.Account;
import mods.acountswiher.ru.vidtu.ias.auth.handlers.LoginHandler;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class OfflineAccount
implements Account {
    @NotNull
    private final String name;
    @NotNull
    private final UUID uuid;
    @Nullable
    private final UUID skin;

    @Contract(pure=true)
    public OfflineAccount(@NotNull String name, @Nullable UUID skin) {
        this.name = name;
        this.uuid = OfflineAccount.uuid(name);
        this.skin = skin;
    }

    @Override
    @Contract(pure=true)
    @NotNull
    public UUID uuid() {
        return this.uuid;
    }

    @Override
    @Contract(pure=true)
    @NotNull
    public String name() {
        return this.name;
    }

    @Override
    @Contract(pure=true)
    @NotNull
    public String type() {
        return "ias:offline_v2";
    }

    @Override
    @Contract(pure=true)
    @NotNull
    public String typeTipKey() {
        return "ias.accounts.tip.type.offline";
    }

    @Override
    @Contract(value="-> false", pure=true)
    public boolean canLogin() {
        return false;
    }

    @Override
    @Contract(value="-> false", pure=true)
    public boolean insecure() {
        return false;
    }

    @Override
    @Contract(pure=true)
    @NotNull
    public UUID skin() {
        return Objects.requireNonNullElse(this.skin, this.uuid);
    }

    @Override
    public void login(@NotNull LoginHandler handler) {
        handler.error(new UnsupportedOperationException("Offline account login: " + String.valueOf(this)));
    }

    @Override
    public void write(@NotNull DataOutput out) throws IOException {
        out.writeUTF(this.name);
        if (this.skin != null) {
            out.writeBoolean(true);
            out.writeLong(this.skin.getMostSignificantBits());
            out.writeLong(this.skin.getLeastSignificantBits());
        } else {
            out.writeBoolean(false);
        }
    }

    @Contract(value="null -> false", pure=true)
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OfflineAccount)) {
            return false;
        }
        OfflineAccount that = (OfflineAccount)obj;
        return Objects.equals(this.name, that.name);
    }

    @Contract(pure=true)
    public int hashCode() {
        return Objects.hashCode(this.name);
    }

    @Contract(pure=true)
    @NotNull
    public String toString() {
        return "OfflineAccount{name='" + this.name + "', uuid=" + String.valueOf(this.uuid) + "}";
    }

    @NotNull
    public static OfflineAccount readV1(DataInput in) throws IOException {
        String name = in.readUTF();
        return new OfflineAccount(name, null);
    }

    @NotNull
    public static OfflineAccount readV2(DataInput in) throws IOException {
        String name = in.readUTF();
        UUID skin = in.readBoolean() ? new UUID(in.readLong(), in.readLong()) : null;
        return new OfflineAccount(name, skin);
    }

    @Contract(pure=true)
    @NotNull
    public static UUID uuid(String name) {
        return UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(StandardCharsets.UTF_8));
    }
}

