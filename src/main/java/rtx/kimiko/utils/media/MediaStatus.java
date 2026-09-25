/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.media;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u0000 \b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\bB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\u0006j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/utils/media/MediaStatus;", "", "<init>", "(Ljava/lang/String;I)V", "", "playing", "()Z", "paused", "Companion", "CLOSED", "OPENED", "CHANGING", "STOPPED", "PLAYING", "PAUSED", "rtx.kimiko:kimiko"})
public enum MediaStatus {
        CLOSED,
        OPENED,
        CHANGING,
        STOPPED,
        PLAYING,
        PAUSED;
@NotNull
    public static final Companion Companion;
    @NotNull
    private static final MediaStatus[] VALUES;
    
    
    
    
    
    
    
    public final boolean playing() {
        return this == PLAYING;
    }

    public final boolean paused() {
        return this == PAUSED;
    }

    

    

    @NotNull
    public static EnumEntries<MediaStatus> getEntries() {
        return EnumEntriesKt.enumEntries(values());
    }

    @JvmStatic
    @NotNull
    public static final MediaStatus of(int index) {
        return Companion.of(index);
    }

            static {
        Companion = new Companion(null);
        VALUES = MediaStatus.values();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2={"Lrtx/kimiko/utils/media/MediaStatus.Companion;", "", "<init>", "()V", "", "index", "Lrtx/kimiko/utils/media/MediaStatus;", "Lkotlin/jvm/JvmStatic;", "of", "(I)Lrtx/kimiko/utils/media/MediaStatus;", "", "VALUES", "[Lrtx/kimiko/utils/media/MediaStatus;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final MediaStatus of(int index) {
            return index >= 0 && index < VALUES.length ? VALUES[index] : CLOSED;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

