/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.fabricmc.loader.api.FabricLoader
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.media;

import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.media.MediaNative;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u0013\u0010\b\u001a\u00020\u0007H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\tJ#\u0010\r\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0010\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/utils/media/MediaLog;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "start", "", "active", "()Z", "", "tag", "message", "note", "(Ljava/lang/String;Ljava/lang/String;)V", "ready", "Z", "attempted", "rtx.kimiko:kimiko"})
public final class MediaLog {
    @NotNull
    public static final MediaLog INSTANCE = new MediaLog();
    private static boolean ready;
    private static boolean attempted;

    private MediaLog() {
    }

    @JvmStatic
    public static final synchronized void start() {
    }

    @JvmStatic
    public static final boolean active() {
        return false;
    }

    @JvmStatic
    public static final void note(@NotNull String tag, @NotNull String message) {
    }
}

