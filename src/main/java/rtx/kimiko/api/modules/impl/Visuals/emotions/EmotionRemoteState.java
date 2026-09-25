/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.emotions;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Visuals.emotions.Emotion;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0011J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\nH\u00c6\u0003\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\fH\u00c6\u0003\u00a2\u0006\u0004\b\u001a\u0010\u001bJX\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\fH\u00c6\u0001\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001f\u001a\u00020\f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001f\u0010 J\u0011\u0010\"\u001a\u00020!H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\"\u0010#J\u0011\u0010$\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b$\u0010\u0011R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b%\u0012\b\b&\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010'\u001a\u0004\b\u0003\u0010\u0011R%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b%\u0012\b\b&\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010'\u001a\u0004\b\u0004\u0010\u0011R%\u0010\u0005\u001a\u00020\u00028\u0007z\f\b%\u0012\b\b&\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010'\u001a\u0004\b\u0005\u0010\u0011R'\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0007z\f\b%\u0012\b\b&\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010(\u001a\u0004\b\u0007\u0010\u0015R%\u0010\t\u001a\u00020\b8\u0007z\f\b%\u0012\b\b&\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010)\u001a\u0004\b\t\u0010\u0017R%\u0010\u000b\u001a\u00020\n8\u0007z\f\b%\u0012\b\b&\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010*\u001a\u0004\b\u000b\u0010\u0019R%\u0010\r\u001a\u00020\f8\u0007z\f\b%\u0012\b\b&\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u0010+\u001a\u0004\b\r\u0010\u001b\u00a8\u0006,"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionRemoteState;", "", "", "identityKey", "minecraftUsername", "world", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;", "emotion", "", "startedAt", "", "speed", "", "looping", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;JFZ)V", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "()Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;", "component5", "()J", "component6", "()F", "component7", "()Z", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;JFZ)Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionRemoteState;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Lkotlin/jvm/JvmName;", "name", "Ljava/lang/String;", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;", "J", "F", "Z", "rtx.kimiko:kimiko"})
public final class EmotionRemoteState {
    @NotNull
    private final String identityKey;
    @NotNull
    private final String minecraftUsername;
    @NotNull
    private final String world;
    @Nullable
    private final Emotion emotion;
    private final long startedAt;
    private final float speed;
    private final boolean looping;

    public EmotionRemoteState(@NotNull String identityKey, @NotNull String minecraftUsername, @NotNull String world, @Nullable Emotion emotion, long startedAt, float speed, boolean looping) {
        Intrinsics.checkNotNullParameter((Object)identityKey, (String)"identityKey");
        Intrinsics.checkNotNullParameter((Object)minecraftUsername, (String)"minecraftUsername");
        Intrinsics.checkNotNullParameter((Object)world, (String)"world");
        this.identityKey = identityKey;
        this.minecraftUsername = minecraftUsername;
        this.world = world;
        this.emotion = emotion;
        this.startedAt = startedAt;
        this.speed = speed;
        this.looping = looping;
    }

    @JvmName(name="identityKey")
    @NotNull
    public final String identityKey() {
        return this.identityKey;
    }

    @JvmName(name="minecraftUsername")
    @NotNull
    public final String minecraftUsername() {
        return this.minecraftUsername;
    }

    @JvmName(name="world")
    @NotNull
    public final String world() {
        return this.world;
    }

    @JvmName(name="emotion")
    @Nullable
    public final Emotion emotion() {
        return this.emotion;
    }

    @JvmName(name="startedAt")
    public final long startedAt() {
        return this.startedAt;
    }

    @JvmName(name="speed")
    public final float speed() {
        return this.speed;
    }

    @JvmName(name="looping")
    public final boolean looping() {
        return this.looping;
    }

    @NotNull
    public final String component1() {
        return this.identityKey;
    }

    @NotNull
    public final String component2() {
        return this.minecraftUsername;
    }

    @NotNull
    public final String component3() {
        return this.world;
    }

    @Nullable
    public final Emotion component4() {
        return this.emotion;
    }

    public final long component5() {
        return this.startedAt;
    }

    public final float component6() {
        return this.speed;
    }

    public final boolean component7() {
        return this.looping;
    }

    @NotNull
    public final EmotionRemoteState copy(@NotNull String identityKey, @NotNull String minecraftUsername, @NotNull String world, @Nullable Emotion emotion, long startedAt, float speed, boolean looping) {
        Intrinsics.checkNotNullParameter((Object)identityKey, (String)"identityKey");
        Intrinsics.checkNotNullParameter((Object)minecraftUsername, (String)"minecraftUsername");
        Intrinsics.checkNotNullParameter((Object)world, (String)"world");
        return new EmotionRemoteState(identityKey, minecraftUsername, world, emotion, startedAt, speed, looping);
    }

    public static /* synthetic */ EmotionRemoteState copy$default(EmotionRemoteState emotionRemoteState, String string, String string2, String string3, Emotion emotion, long l, float f, boolean bl, int n, Object object) {
        if ((n & 1) != 0) {
            string = emotionRemoteState.identityKey;
        }
        if ((n & 2) != 0) {
            string2 = emotionRemoteState.minecraftUsername;
        }
        if ((n & 4) != 0) {
            string3 = emotionRemoteState.world;
        }
        if ((n & 8) != 0) {
            emotion = emotionRemoteState.emotion;
        }
        if ((n & 0x10) != 0) {
            l = emotionRemoteState.startedAt;
        }
        if ((n & 0x20) != 0) {
            f = emotionRemoteState.speed;
        }
        if ((n & 0x40) != 0) {
            bl = emotionRemoteState.looping;
        }
        return emotionRemoteState.copy(string, string2, string3, emotion, l, f, bl);
    }

    @NotNull
    public String toString() {
        return "EmotionRemoteState(identityKey=" + this.identityKey + ", minecraftUsername=" + this.minecraftUsername + ", world=" + this.world + ", emotion=" + this.emotion + ", startedAt=" + this.startedAt + ", speed=" + this.speed + ", looping=" + this.looping + ")";
    }

    public int hashCode() {
        int result = this.identityKey.hashCode();
        result = result * 31 + this.minecraftUsername.hashCode();
        result = result * 31 + this.world.hashCode();
        result = result * 31 + (this.emotion == null ? 0 : this.emotion.hashCode());
        result = result * 31 + Long.hashCode(this.startedAt);
        result = result * 31 + Float.hashCode(this.speed);
        result = result * 31 + Boolean.hashCode(this.looping);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EmotionRemoteState)) {
            return false;
        }
        EmotionRemoteState emotionRemoteState = (EmotionRemoteState)other;
        if (!Intrinsics.areEqual((Object)this.identityKey, (Object)emotionRemoteState.identityKey)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.minecraftUsername, (Object)emotionRemoteState.minecraftUsername)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.world, (Object)emotionRemoteState.world)) {
            return false;
        }
        if (this.emotion != emotionRemoteState.emotion) {
            return false;
        }
        if (this.startedAt != emotionRemoteState.startedAt) {
            return false;
        }
        if (Float.compare(this.speed, emotionRemoteState.speed) != 0) {
            return false;
        }
        return this.looping == emotionRemoteState.looping;
    }
}

