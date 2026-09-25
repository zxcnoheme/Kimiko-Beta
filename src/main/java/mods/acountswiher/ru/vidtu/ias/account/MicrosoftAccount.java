package mods.acountswiher.ru.vidtu.ias.account;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.UUID;
import mods.acountswiher.ru.vidtu.ias.auth.handlers.LoginHandler;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class MicrosoftAccount implements Account {
    private final boolean insecure;
    @NotNull
    private final UUID uuid;
    @NotNull
    private final String name;
    private final byte[] data;

    @Contract(pure=true)
    public MicrosoftAccount(boolean insecure, @NotNull UUID uuid, @NotNull String name, byte[] data) {
        this.insecure = insecure;
        this.uuid = uuid;
        this.name = name;
        this.data = data;
    }

    @Override
    @Contract(pure=true)
    @NotNull
    public String type() {
        return "ias:microsoft_v1";
    }

    @Override
    @Contract(pure=true)
    @NotNull
    public String typeTipKey() {
        return "ias.accounts.tip.type.microsoft";
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
    public boolean canLogin() {
        return true;
    }

    @Override
    @Contract(pure=true)
    public boolean insecure() {
        return this.insecure;
    }

    @Override
    @Contract(pure=true)
    @NotNull
    public UUID skin() {
        return this.uuid;
    }

    public byte[] data() {
        return this.data;
    }

    @Override
    public void login(@NotNull LoginHandler handler) {
    }

    @Override
    public void write(@NotNull DataOutput out) throws IOException {
        out.writeBoolean(this.insecure);
        out.writeLong(this.uuid.getMostSignificantBits());
        out.writeLong(this.uuid.getLeastSignificantBits());
        out.writeUTF(this.name);
        if (this.data != null) {
            out.writeInt(this.data.length);
            out.write(this.data);
        } else {
            out.writeInt(0);
        }
    }

    @NotNull
    public static MicrosoftAccount read(@NotNull DataInput in) throws IOException {
        boolean insecure = in.readBoolean();
        UUID uuid = new UUID(in.readLong(), in.readLong());
        String name = in.readUTF();
        int len = in.readInt();
        byte[] data = new byte[len];
        if (len > 0) {
            in.readFully(data);
        }
        return new MicrosoftAccount(insecure, uuid, name, data);
    }
}
