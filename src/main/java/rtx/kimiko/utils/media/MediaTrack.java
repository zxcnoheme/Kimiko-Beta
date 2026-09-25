/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.media;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\b\u0018\u0000 )2\u00020\u0001:\u0001)B?\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0014J\u0010\u0010\u0017\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0014J\u0010\u0010\u0018\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0018\u0010\u0014J\u0010\u0010\u0019\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0019\u0010\u0012J\u0010\u0010\u001a\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001a\u0010\u0014J\u0010\u0010\u001b\u001a\u00020\nH\u00c6\u0003\u00a2\u0006\u0004\b\u001b\u0010\u001cJV\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\nH\u00c6\u0001\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u001b\u0010 \u001a\u00020\u000e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b \u0010!J\u0011\u0010\"\u001a\u00020\u0007H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\"\u0010\u0012J\u0011\u0010#\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b#\u0010\u0014R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b$\u0012\b\b%\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010&\u001a\u0004\b\u0003\u0010\u0014R%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b$\u0012\b\b%\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010&\u001a\u0004\b\u0004\u0010\u0014R%\u0010\u0005\u001a\u00020\u00028\u0007z\f\b$\u0012\b\b%\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010&\u001a\u0004\b\u0005\u0010\u0014R%\u0010\u0006\u001a\u00020\u00028\u0007z\f\b$\u0012\b\b%\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010&\u001a\u0004\b\u0006\u0010\u0014R%\u0010\b\u001a\u00020\u00078\u0007z\f\b$\u0012\b\b%\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010'\u001a\u0004\b\b\u0010\u0012R%\u0010\t\u001a\u00020\u00028\u0007z\f\b$\u0012\b\b%\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010&\u001a\u0004\b\t\u0010\u0014R%\u0010\u000b\u001a\u00020\n8\u0007z\f\b$\u0012\b\b%\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010(\u001a\u0004\b\u000b\u0010\u001c\u00a8\u0006*"}, d2={"Lrtx/kimiko/utils/media/MediaTrack;", "", "", "title", "artist", "albumTitle", "albumArtist", "", "trackNumber", "appId", "", "durationMillis", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;J)V", "", "isEmpty", "()Z", "durationSeconds", "()I", "display", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "()J", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;J)Lrtx/kimiko/utils/media/MediaTrack;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "Lkotlin/jvm/JvmName;", "name", "Ljava/lang/String;", "I", "J", "Companion", "rtx.kimiko:kimiko"})
public final class MediaTrack {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String title;
    @NotNull
    private final String artist;
    @NotNull
    private final String albumTitle;
    @NotNull
    private final String albumArtist;
    private final int trackNumber;
    @NotNull
    private final String appId;
    private final long durationMillis;
    @JvmField
    @NotNull
    public static final MediaTrack EMPTY = new MediaTrack("", "", "", "", 0, "", 0L);

    public MediaTrack(@NotNull String title, @NotNull String artist, @NotNull String albumTitle, @NotNull String albumArtist, int trackNumber, @NotNull String appId, long durationMillis) {
        Intrinsics.checkNotNullParameter((Object)title, (String)"title");
        Intrinsics.checkNotNullParameter((Object)artist, (String)"artist");
        Intrinsics.checkNotNullParameter((Object)albumTitle, (String)"albumTitle");
        Intrinsics.checkNotNullParameter((Object)albumArtist, (String)"albumArtist");
        Intrinsics.checkNotNullParameter((Object)appId, (String)"appId");
        this.title = title;
        this.artist = artist;
        this.albumTitle = albumTitle;
        this.albumArtist = albumArtist;
        this.trackNumber = trackNumber;
        this.appId = appId;
        this.durationMillis = durationMillis;
    }

    @JvmName(name="title")
    @NotNull
    public final String title() {
        return this.title;
    }

    @JvmName(name="artist")
    @NotNull
    public final String artist() {
        return this.artist;
    }

    @JvmName(name="albumTitle")
    @NotNull
    public final String albumTitle() {
        return this.albumTitle;
    }

    @JvmName(name="albumArtist")
    @NotNull
    public final String albumArtist() {
        return this.albumArtist;
    }

    @JvmName(name="trackNumber")
    public final int trackNumber() {
        return this.trackNumber;
    }

    @JvmName(name="appId")
    @NotNull
    public final String appId() {
        return this.appId;
    }

    @JvmName(name="durationMillis")
    public final long durationMillis() {
        return this.durationMillis;
    }

    public final boolean isEmpty() {
        return ((CharSequence)this.title).length() == 0 && ((CharSequence)this.artist).length() == 0;
    }

    public final int durationSeconds() {
        return (int)(this.durationMillis / 1000L);
    }

    @NotNull
    public final String display() {
        if (((CharSequence)this.artist).length() == 0) {
            return this.title;
        }
        return this.artist + " - " + this.title;
    }

    @NotNull
    public final String component1() {
        return this.title;
    }

    @NotNull
    public final String component2() {
        return this.artist;
    }

    @NotNull
    public final String component3() {
        return this.albumTitle;
    }

    @NotNull
    public final String component4() {
        return this.albumArtist;
    }

    public final int component5() {
        return this.trackNumber;
    }

    @NotNull
    public final String component6() {
        return this.appId;
    }

    public final long component7() {
        return this.durationMillis;
    }

    @NotNull
    public final MediaTrack copy(@NotNull String title, @NotNull String artist, @NotNull String albumTitle, @NotNull String albumArtist, int trackNumber, @NotNull String appId, long durationMillis) {
        Intrinsics.checkNotNullParameter((Object)title, (String)"title");
        Intrinsics.checkNotNullParameter((Object)artist, (String)"artist");
        Intrinsics.checkNotNullParameter((Object)albumTitle, (String)"albumTitle");
        Intrinsics.checkNotNullParameter((Object)albumArtist, (String)"albumArtist");
        Intrinsics.checkNotNullParameter((Object)appId, (String)"appId");
        return new MediaTrack(title, artist, albumTitle, albumArtist, trackNumber, appId, durationMillis);
    }

    public static /* synthetic */ MediaTrack copy$default(MediaTrack mediaTrack, String string, String string2, String string3, String string4, int n, String string5, long l, int n2, Object object) {
        if ((n2 & 1) != 0) {
            string = mediaTrack.title;
        }
        if ((n2 & 2) != 0) {
            string2 = mediaTrack.artist;
        }
        if ((n2 & 4) != 0) {
            string3 = mediaTrack.albumTitle;
        }
        if ((n2 & 8) != 0) {
            string4 = mediaTrack.albumArtist;
        }
        if ((n2 & 0x10) != 0) {
            n = mediaTrack.trackNumber;
        }
        if ((n2 & 0x20) != 0) {
            string5 = mediaTrack.appId;
        }
        if ((n2 & 0x40) != 0) {
            l = mediaTrack.durationMillis;
        }
        return mediaTrack.copy(string, string2, string3, string4, n, string5, l);
    }

    @NotNull
    public String toString() {
        return "MediaTrack(title=" + this.title + ", artist=" + this.artist + ", albumTitle=" + this.albumTitle + ", albumArtist=" + this.albumArtist + ", trackNumber=" + this.trackNumber + ", appId=" + this.appId + ", durationMillis=" + this.durationMillis + ")";
    }

    public int hashCode() {
        int result = this.title.hashCode();
        result = result * 31 + this.artist.hashCode();
        result = result * 31 + this.albumTitle.hashCode();
        result = result * 31 + this.albumArtist.hashCode();
        result = result * 31 + Integer.hashCode(this.trackNumber);
        result = result * 31 + this.appId.hashCode();
        result = result * 31 + Long.hashCode(this.durationMillis);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MediaTrack)) {
            return false;
        }
        MediaTrack mediaTrack = (MediaTrack)other;
        if (!Intrinsics.areEqual((Object)this.title, (Object)mediaTrack.title)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.artist, (Object)mediaTrack.artist)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.albumTitle, (Object)mediaTrack.albumTitle)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.albumArtist, (Object)mediaTrack.albumArtist)) {
            return false;
        }
        if (this.trackNumber != mediaTrack.trackNumber) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.appId, (Object)mediaTrack.appId)) {
            return false;
        }
        return this.durationMillis == mediaTrack.durationMillis;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/utils/media/MediaTrack.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/media/MediaTrack;", "Lkotlin/jvm/JvmField;", "EMPTY", "Lrtx/kimiko/utils/media/MediaTrack;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

