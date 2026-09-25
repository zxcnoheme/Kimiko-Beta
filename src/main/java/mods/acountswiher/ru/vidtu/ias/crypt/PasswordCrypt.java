/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mods.acountswiher.ru.vidtu.ias.crypt.Crypt
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package mods.acountswiher.ru.vidtu.ias.crypt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.security.SecureRandom;
import mods.acountswiher.ru.vidtu.ias.crypt.Crypt;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PasswordCrypt
implements Crypt {
    @NotNull
    private final String password;

    @Contract(pure=true)
    public PasswordCrypt(@NotNull String password) {
        if (password.isBlank()) {
            throw new IllegalArgumentException("Password is blank.");
        }
        this.password = password;
    }

    @Contract(pure=true)
    @NotNull
    public String type() {
        return "ias:password_crypt_v1";
    }

    @Contract(value="-> null", pure=true)
    @Nullable
    public Crypt migrate() {
        return null;
    }

    @Contract(value="-> false", pure=true)
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
            byte[] data = Crypt.pbkdfAesEncrypt(decrypted, this.password, salt, iv);
            out.write(data);
            return out.toByteArray();
        } catch (Throwable t) {
            throw new RuntimeException("Unable to encrypt using PasswordCrypt.", t);
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
            byte[] data = in.readAllBytes();
            return Crypt.pbkdfAesDecrypt(data, this.password, salt, iv);
        } catch (Throwable t) {
            throw new RuntimeException("Unable to decrypt using PasswordCrypt.", t);
        }
    }

    @Contract(pure=true)
    @NotNull
    public String toString() {
        return "PasswordCrypt{password='[PASSWORD]'}";
    }
}

