/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mods.acountswiher.ru.vidtu.ias.crypt.Crypt
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.jetbrains.annotations.Unmodifiable
 */
package mods.acountswiher.ru.vidtu.ias.crypt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Objects;
import mods.acountswiher.ru.vidtu.ias.crypt.Crypt;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

public final class HardwareCrypt
implements Crypt {
    @NotNull
    public static final HardwareCrypt INSTANCE_V2 = new HardwareCrypt(2, "ias:hardware_crypt_v2", null);
    @NotNull
    public static final HardwareCrypt INSTANCE_V1 = new HardwareCrypt(1, "ias:hardware_crypt_v1", INSTANCE_V2);
    private static final byte @NotNull [] EMPTY_MAC = new byte[0];
    @NotNull
    private static final @Unmodifiable List<String> ENV = List.of("COMPUTERNAME", "PROCESSOR_ARCHITECTURE", "PROCESSOR_REVISION", "PROCESSOR_IDENTIFIER", "PROCESSOR_LEVEL", "NUMBER_OF_PROCESSORS", "OS", "USERNAME", "USERDOMAIN", "USERDOMAIN_ROAMINGPROFILE", "APPDATA", "HOMEPATH", "LOGONSERVER", "LOCALAPPDATA", "TEMP", "TMP", "MINECRAFT_IN_GAME_ACCOUNT_SWITCHER_VERY_NERDY_SYSTEM_ENV");
    @NotNull
    private static final @Unmodifiable List<String> PROPS = List.of("java.io.tmpdir", "native.encoding", "user.name", "user.home", "user.country", "sun.io.unicode.encoding", "stderr.encoding", "sun.cpu.endian", "sun.cpu.isalist", "sun.jnu.encoding", "stdout.encoding", "sun.arch.data.model", "user.language", "user.variant", "minecraft.inGameAccountSwitcher.veryNerdySystemProperty");
    private final int version;
    @NotNull
    private final String type;
    @Nullable
    private final HardwareCrypt migrate;

    @Contract(pure=true)
    private HardwareCrypt(int version, @NotNull String type, @Nullable HardwareCrypt migrate) {
        this.version = version;
        this.type = type;
        this.migrate = migrate;
    }

    @Contract(pure=true)
    @NotNull
    public String type() {
        return this.type;
    }

    @Contract(pure=true)
    @Nullable
    public HardwareCrypt migrate() {
        return this.migrate;
    }

    @Contract(pure=true)
    public boolean insecure() {
        return false;
    }

    @Contract(pure=true)
    public byte @NotNull [] encrypt(byte @NotNull [] decrypted) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            SecureRandom random = SecureRandom.getInstanceStrong();
            byte[] salt = new byte[128];
            random.nextBytes(salt);
            out.write(salt);
            byte[] iv = new byte[16];
            random.nextBytes(iv);
            out.write(iv);
            String pwd = this.hardwarePassword();
            byte[] data = Crypt.pbkdfAesEncrypt(decrypted, pwd, salt, iv);
            out.write(data);
            return out.toByteArray();
        } catch (Throwable t) {
            throw new RuntimeException("Unable to encrypt using HardwareCrypt.", t);
        }
    }

    @Contract(pure=true)
    public byte @NotNull [] decrypt(byte @NotNull [] encrypted) {
        try (ByteArrayInputStream in = new ByteArrayInputStream(encrypted)) {
            byte[] salt = new byte[128];
            int read = in.read(salt);
            if (read != 128) {
                throw new EOFException("Not enough salt bytes: " + read);
            }
            byte[] iv = new byte[16];
            read = in.read(iv);
            if (read != 16) {
                throw new EOFException("Not enough IV bytes: " + read);
            }
            String pwd = this.hardwarePassword();
            byte[] data = in.readAllBytes();
            return Crypt.pbkdfAesDecrypt(data, pwd, salt, iv);
        } catch (Throwable t) {
            throw new RuntimeException("Unable to decrypt using HardwareCrypt.", t);
        }
    }

    @Contract(value="null -> false", pure=true)
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HardwareCrypt)) {
            return false;
        }
        HardwareCrypt that = (HardwareCrypt)obj;
        return this.version == that.version && Objects.equals(this.type, that.type) && Objects.equals(this.migrate, that.migrate);
    }

    @Contract(pure=true)
    public int hashCode() {
        int hash = 1;
        hash = 31 * hash + Integer.hashCode(this.version);
        hash = 31 * hash + Objects.hashCode(this.type);
        hash = 31 * hash + Objects.hashCode(this.migrate);
        return hash;
    }

    @Contract(pure=true)
    @NotNull
    public String toString() {
        return "HardwareCrypt{version=" + this.version + ", type='" + this.type + "', migrate=" + String.valueOf(this.migrate) + "}";
    }

    @Contract(pure=true)
    @NotNull
    private String hardwarePassword() {
        throw new UnsupportedOperationException("Hardware fingerprinting or microphone access disabled in privacy build");
    }
}

