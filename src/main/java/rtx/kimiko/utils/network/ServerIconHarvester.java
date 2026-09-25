/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.Regex
 *  kotlin.text.StringsKt
 *  net.fabricmc.loader.api.FabricLoader
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.network;

import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\n\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\r\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\r\u0010\u000eR\u0019\u0010\u0011\u001a\u00020\u000f8\u0006X\u0087D\u0092\u0002\u0002\b\u0010\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0013"}, d2={"Lrtx/kimiko/utils/network/ServerIconHarvester;", "", "<init>", "()V", "", "ipOrName", "", "pngBytes", "", "Lkotlin/jvm/JvmStatic;", "capture", "(Ljava/lang/String;[B)V", "s", "sanitize", "(Ljava/lang/String;)Ljava/lang/String;", "", "Lkotlin/jvm/JvmField;", "ENABLED", "Z", "rtx.kimiko:kimiko"})
public final class ServerIconHarvester {
    @NotNull
    public static final ServerIconHarvester INSTANCE = new ServerIconHarvester();
    @JvmField
    public static final boolean ENABLED = false;

    private ServerIconHarvester() {
    }

    @JvmStatic
    public static final void capture(@Nullable String ipOrName, @Nullable byte[] pngBytes) {
        if (!ENABLED || pngBytes == null || pngBytes.length == 0) {
            return;
        }
        try {
            Path dir = FabricLoader.getInstance().getGameDir().resolve("kimiko_server_icons");
            Files.createDirectories(dir, new FileAttribute[0]);
            Path file = dir.resolve(INSTANCE.sanitize(ipOrName) + ".png");
            Files.write(file, pngBytes, new OpenOption[0]);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private final String sanitize(String s) {
        if (s == null || s.isBlank()) {
            return "server";
        }
        String lower = s.trim().toLowerCase(Locale.ROOT);
        String out = lower.replaceAll("[^a-z0-9._-]", "_");
        return out.isBlank() ? "server" : out;
    }
}

