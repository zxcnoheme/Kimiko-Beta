/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.media;

import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u00a2\u0006\u0004\b\f\u0010\rB7\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\f\u0010\u000eJ\r\u0010\u000f\u001a\u00020\t\u00a2\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\t\u00a2\u0006\u0004\b\u0011\u0010\u0010J\r\u0010\u0012\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0013J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0013J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0013J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0013J\u0010\u0010\u0018\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b\u001a\u0010\u0010J\u0010\u0010\u001b\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b\u001b\u0010\u0010J\\\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\tH\u00c6\u0001\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001f\u001a\u00020\t2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001f\u0010 J\u0011\u0010\"\u001a\u00020!H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\"\u0010#J\u0011\u0010$\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b$\u0010\u0013R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b%\u0012\b\b\u0004\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010&\u001a\u0004\b\u0003\u0010\u0013R'\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0007z\f\b%\u0012\b\b\u0004\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010&\u001a\u0004\b\u0004\u0010\u0013R'\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0007z\f\b%\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010&\u001a\u0004\b\u0005\u0010\u0013R'\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0007z\f\b%\u0012\b\b\u0004\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010&\u001a\u0004\b\u0006\u0010\u0013R%\u0010\b\u001a\u00020\u00078\u0007z\f\b%\u0012\b\b\u0004\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010'\u001a\u0004\b\b\u0010\u0019R%\u0010\n\u001a\u00020\t8\u0007z\f\b%\u0012\b\b\u0004\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010(\u001a\u0004\b\n\u0010\u0010R%\u0010\u000b\u001a\u00020\t8\u0007z\f\b%\u0012\b\b\u0004\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010(\u001a\u0004\b\u000b\u0010\u0010\u00a8\u0006)"}, d2={"Lrtx/kimiko/utils/media/RadioStation;", "", "", "id", "name", "genre", "url", "", "frequency", "", "online", "builtIn", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;FZZ)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;F)V", "valid", "()Z", "broadcast", "dial", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "()F", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;FZZ)Lrtx/kimiko/utils/media/RadioStation;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Lkotlin/jvm/JvmName;", "Ljava/lang/String;", "F", "Z", "rtx.kimiko:kimiko"})
public final class RadioStation {
    @NotNull
    private final String id;
    @Nullable
    private final String name;
    @Nullable
    private final String genre;
    @Nullable
    private final String url;
    private final float frequency;
    private final boolean online;
    private final boolean builtIn;

    public RadioStation(@NotNull String id, @Nullable String name, @Nullable String genre, @Nullable String url, float frequency, boolean online, boolean builtIn) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.url = url;
        this.frequency = frequency;
        this.online = online;
        this.builtIn = builtIn;
    }

    @JvmName(name="id")
    @NotNull
    public final String id() {
        return this.id;
    }

    @JvmName(name="name")
    @Nullable
    public final String name() {
        return this.name;
    }

    @JvmName(name="genre")
    @Nullable
    public final String genre() {
        return this.genre;
    }

    @JvmName(name="url")
    @Nullable
    public final String url() {
        return this.url;
    }

    @JvmName(name="frequency")
    public final float frequency() {
        return this.frequency;
    }

    @JvmName(name="online")
    public final boolean online() {
        return this.online;
    }

    @JvmName(name="builtIn")
    public final boolean builtIn() {
        return this.builtIn;
    }

    public RadioStation(@NotNull String id, @Nullable String name, @Nullable String genre, @Nullable String url, float frequency) {
        this(id, name, genre, url, frequency, true, true);
    }

    public final boolean valid() {
        CharSequence charSequence = this.url;
        return !(charSequence == null || StringsKt.isBlank((CharSequence)charSequence)) && !((charSequence = (CharSequence)this.name) == null || StringsKt.isBlank((CharSequence)charSequence));
    }

    public final boolean broadcast() {
        return this.frequency <= 108.0f;
    }

    @NotNull
    public final String dial() {
        Locale locale = Locale.ROOT;
        String string = "%.1f";
        Object[] objectArray = new Object[]{Float.valueOf(this.frequency)};
        String string2 = String.format(locale, string, Arrays.copyOf(objectArray, objectArray.length));
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"format(...)");
        return string2 + (this.broadcast() ? " FM" : " NET");
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @Nullable
    public final String component2() {
        return this.name;
    }

    @Nullable
    public final String component3() {
        return this.genre;
    }

    @Nullable
    public final String component4() {
        return this.url;
    }

    public final float component5() {
        return this.frequency;
    }

    public final boolean component6() {
        return this.online;
    }

    public final boolean component7() {
        return this.builtIn;
    }

    @NotNull
    public final RadioStation copy(@NotNull String id, @Nullable String name, @Nullable String genre, @Nullable String url, float frequency, boolean online, boolean builtIn) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        return new RadioStation(id, name, genre, url, frequency, online, builtIn);
    }

    public static /* synthetic */ RadioStation copy$default(RadioStation radioStation, String string, String string2, String string3, String string4, float f, boolean bl, boolean bl2, int n, Object object) {
        if ((n & 1) != 0) {
            string = radioStation.id;
        }
        if ((n & 2) != 0) {
            string2 = radioStation.name;
        }
        if ((n & 4) != 0) {
            string3 = radioStation.genre;
        }
        if ((n & 8) != 0) {
            string4 = radioStation.url;
        }
        if ((n & 0x10) != 0) {
            f = radioStation.frequency;
        }
        if ((n & 0x20) != 0) {
            bl = radioStation.online;
        }
        if ((n & 0x40) != 0) {
            bl2 = radioStation.builtIn;
        }
        return radioStation.copy(string, string2, string3, string4, f, bl, bl2);
    }

    @NotNull
    public String toString() {
        return "RadioStation(id=" + this.id + ", name=" + this.name + ", genre=" + this.genre + ", url=" + this.url + ", frequency=" + this.frequency + ", online=" + this.online + ", builtIn=" + this.builtIn + ")";
    }

    public int hashCode() {
        int result = this.id.hashCode();
        result = result * 31 + (this.name == null ? 0 : this.name.hashCode());
        result = result * 31 + (this.genre == null ? 0 : this.genre.hashCode());
        result = result * 31 + (this.url == null ? 0 : this.url.hashCode());
        result = result * 31 + Float.hashCode(this.frequency);
        result = result * 31 + Boolean.hashCode(this.online);
        result = result * 31 + Boolean.hashCode(this.builtIn);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RadioStation)) {
            return false;
        }
        RadioStation radioStation = (RadioStation)other;
        if (!Intrinsics.areEqual((Object)this.id, (Object)radioStation.id)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.name, (Object)radioStation.name)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.genre, (Object)radioStation.genre)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.url, (Object)radioStation.url)) {
            return false;
        }
        if (Float.compare(this.frequency, radioStation.frequency) != 0) {
            return false;
        }
        if (this.online != radioStation.online) {
            return false;
        }
        return this.builtIn == radioStation.builtIn;
    }
}

