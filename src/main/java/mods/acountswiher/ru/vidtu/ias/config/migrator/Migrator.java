/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package mods.acountswiher.ru.vidtu.ias.config.migrator;

import com.google.gson.JsonObject;
import java.util.regex.Pattern;
import mods.acountswiher.ru.vidtu.ias.config.migrator.MigratorV1;
import mods.acountswiher.ru.vidtu.ias.config.migrator.MigratorV2;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Migrator {
    @NotNull
    public static final Pattern OBFUSCATE_LOGS = Pattern.compile("(\"?(?:accessToken|refreshToken)\"?\\s*:)\"?[^,\":{}\\[\\]]*\"?", 2);

    public void load(@NotNull JsonObject var1);

    @Contract(pure=true)
    @Nullable
    public static Migrator fromVersion(int version) {
        return switch (version) {
            case 1 -> new MigratorV1();
            case 2 -> new MigratorV2();
            case 3, 4 -> null;
            default -> throw new IllegalArgumentException("Unknown config version: " + version);
        };
    }
}

