/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.Entity
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.render.entity.model.BipedEntityModel
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.emotions;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Visuals.emotions.Emotion;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionPose;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionStateHolder;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001FB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\n\u0010\u0003J\u0013\u0010\u000b\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000b\u0010\u0003J?\u0010\u0014\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0016\u0010\u0003J\u001b\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0010H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u001b\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0012H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001c\u0010\u0003J\u0015\u0010\u001d\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020\u0012H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001f\u0010 J\u0013\u0010!\u001a\u00020\u0010H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b!\u0010\"J\u0013\u0010#\u001a\u00020\u0010H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b#\u0010\"J%\u0010$\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010!\u001a\u00020\u0010H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b$\u0010%J\u0013\u0010&\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b&\u0010\u0003J'\u0010+\u001a\u00020\u00062\b\u0010(\u001a\u0004\u0018\u00010'2\b\u0010*\u001a\u0004\u0018\u00010)H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b+\u0010,J3\u00100\u001a\u00020\u00062\f\u0010.\u001a\b\u0012\u0002\b\u0003\u0018\u00010-2\b\u0010(\u001a\u0004\u0018\u00010'2\u0006\u0010/\u001a\u00020\u0010H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b0\u00101J\u0017\u00103\u001a\u00020\u00102\u0006\u00102\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b3\u00104R\u0014\u00105\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b5\u00106R\u0014\u00107\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b7\u00106R\u0014\u00109\u001a\u0002088\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b9\u0010:R\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010;R\u0016\u0010<\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u0010=R\u0016\u0010>\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010=R\u0016\u0010\u0011\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0011\u00106R\u0016\u0010\u0013\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010?R\u0018\u0010@\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b@\u0010;R\u0016\u0010A\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bA\u00106R \u0010D\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020C0B8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u0010E\u00a8\u0006G"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionPlayback;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;", "emotion", "", "Lkotlin/jvm/JvmStatic;", "play", "(Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;)V", "stop", "cancel", "Ljava/util/UUID;", "playerId", "", "startedAt", "", "speed", "", "looping", "setRemote", "(Ljava/util/UUID;Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;JFZ)V", "clearRemote", "value", "setSpeed", "(F)V", "setLooping", "(Z)V", "update", "active", "()Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;", "isPlaying", "()Z", "time", "()F", "weight", "beginPreview", "(Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;F)V", "endPreview", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionStateHolder;", "holder", "Lnet/minecraft/Entity;", "entity", "fill", "(Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionStateHolder;Lnet/minecraft/Entity;)V", "Lnet/minecraft/BipedEntityModel;", "model", "walkSpeed", "applyTo", "(Lnet/minecraft/BipedEntityModel;Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionStateHolder;F)V", "since", "elapsed", "(J)F", "FADE_SECONDS", "F", "WALK_FULL_SPEED", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionPose;", "POSE", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionPose;", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;", "startNanos", "J", "stopNanos", "Z", "preview", "previewTime", "", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionPlayback$RemotePlayback;", "REMOTE", "Ljava/util/Map;", "RemotePlayback", "rtx.kimiko:kimiko"})
public final class EmotionPlayback {
    @NotNull
    public static final EmotionPlayback INSTANCE = new EmotionPlayback();
    private static final float FADE_SECONDS = 0.25f;
    private static final float WALK_FULL_SPEED = 0.4f;
    @NotNull
    private static final EmotionPose POSE = new EmotionPose();
    @Nullable
    private static Emotion active;
    private static long startNanos;
    private static long stopNanos;
    private static float speed;
    private static boolean looping;
    @Nullable
    private static Emotion preview;
    private static float previewTime;
    @NotNull
    private static final Map<UUID, RemotePlayback> REMOTE;

    private EmotionPlayback() {
    }

    @JvmStatic
    public static final void play(@Nullable Emotion emotion) {
        if (emotion == null) {
            EmotionPlayback.stop();
            return;
        }
        active = emotion;
        startNanos = System.nanoTime();
        stopNanos = 0L;
    }

    @JvmStatic
    public static final void stop() {
        if (active == null || stopNanos != 0L) {
            return;
        }
        stopNanos = System.nanoTime();
    }

    @JvmStatic
    public static final void cancel() {
        active = null;
        startNanos = 0L;
        stopNanos = 0L;
    }

    @JvmStatic
    public static final void setRemote(@Nullable UUID playerId, @Nullable Emotion emotion, long startedAt, float speed, boolean looping) {
        if (playerId != null && emotion != null && startedAt > 0L) {
            REMOTE.put(playerId, new RemotePlayback(emotion, startedAt, Math.max(0.05f, speed), looping));
        }
    }

    @JvmStatic
    public static final void clearRemote() {
        REMOTE.clear();
    }

    @JvmStatic
    public static final void setSpeed(float value) {
        speed = Math.max(0.05f, value);
    }

    @JvmStatic
    public static final void setLooping(boolean value) {
        looping = value;
    }

    @JvmStatic
    public static final void update() {
        Emotion emotion = active;
        if (emotion == null) {
            return;
        }
        Emotion current = emotion;
        if (!looping && stopNanos == 0L && EmotionPlayback.time() >= current.duration()) {
            EmotionPlayback.stop();
        }
        EmotionPlayback.weight();
    }

    @JvmStatic
    @Nullable
    public static final Emotion active() {
        return active;
    }

    @JvmStatic
    public static final boolean isPlaying() {
        return active != null && stopNanos == 0L;
    }

    @JvmStatic
    public static final float time() {
        Emotion emotion = active;
        if (emotion == null) {
            return 0.0f;
        }
        Emotion current = emotion;
        return INSTANCE.elapsed(startNanos) * speed;
    }

    @JvmStatic
    public static final float weight() {
        Emotion emotion = active;
        if (emotion == null) {
            return 0.0f;
        }
        Emotion current = emotion;
        if (stopNanos != 0L && INSTANCE.elapsed(stopNanos) >= 0.25f) {
            EmotionPlayback.cancel();
            return 0.0f;
        }
        if (!looping && stopNanos == 0L && EmotionPlayback.time() >= current.duration()) {
            stopNanos = System.nanoTime();
        }
        float input = Math.min(1.0f, INSTANCE.elapsed(startNanos) / 0.25f);
        if (stopNanos == 0L) {
            return input;
        }
        return input * (1.0f - Math.min(1.0f, INSTANCE.elapsed(stopNanos) / 0.25f));
    }

    @JvmStatic
    public static final void beginPreview(@Nullable Emotion emotion, float time) {
        preview = emotion;
        previewTime = time;
    }

    @JvmStatic
    public static final void endPreview() {
        preview = null;
    }

    @JvmStatic
    public static final void fill(@Nullable EmotionStateHolder holder, @Nullable Entity entity) {
        Emotion act;
        RemotePlayback remote;
        if (holder == null) {
            return;
        }
        Emotion prev = preview;
        if (prev != null) {
            holder.kimiko$setEmotion(prev, previewTime, 1.0f);
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        ClientPlayerEntity player = mc.player;
        if (player != null && !Intrinsics.areEqual((Object)entity, (Object)player) && entity != null && (remote = REMOTE.get(entity.getUuid())) != null) {
            float time = Math.max(0.0f, (float)(System.currentTimeMillis() - remote.getStartedAt()) / 1000.0f * remote.getSpeed());
            if (remote.getLooping()) {
                time %= remote.getEmotion().duration();
            }
            if (remote.getLooping() || time < remote.getEmotion().duration()) {
                holder.kimiko$setEmotion(remote.getEmotion(), time, 1.0f);
                return;
            }
        }
        if ((act = active) == null || player == null || !Intrinsics.areEqual((Object)entity, (Object)player)) {
            holder.kimiko$setEmotion(null, 0.0f, 0.0f);
            return;
        }
        float weight = EmotionPlayback.weight();
        if (weight <= 0.0f) {
            holder.kimiko$setEmotion(null, 0.0f, 0.0f);
            return;
        }
        holder.kimiko$setEmotion(act, EmotionPlayback.time(), weight);
    }

    @JvmStatic
    public static final void applyTo(@Nullable BipedEntityModel<?> model, @Nullable EmotionStateHolder holder, float walkSpeed) {
        if (model == null || holder == null) {
            return;
        }
        Emotion emotion = holder.kimiko$getEmotion();
        if (emotion == null) {
            return;
        }
        Emotion emotion2 = emotion;
        float weight = holder.kimiko$getEmotionWeight();
        if (weight <= 0.001f) {
            return;
        }
        float walk = Math.min(1.0f, Math.max(0.0f, walkSpeed / 0.4f));
        POSE.reset();
        emotion2.apply(POSE, holder.kimiko$getEmotionTime());
        POSE.applyTo(model, weight, walk);
    }

    private final float elapsed(long since) {
        return (float)(System.nanoTime() - since) / 1.0E9f;
    }

    static {
        speed = 1.0f;
        REMOTE = new ConcurrentHashMap();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013J8\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\bH\u00c6\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0017\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0011\u0010\u001a\u001a\u00020\u0019H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0011\u0010\u001d\u001a\u00020\u001cH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001f\u001a\u0004\b \u0010\rR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010!\u001a\u0004\b\"\u0010\u000fR\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010#\u001a\u0004\b$\u0010\u0011R\u0017\u0010\t\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u0010%\u001a\u0004\b&\u0010\u0013\u00a8\u0006'"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionPlayback$RemotePlayback;", "", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;", "emotion", "", "startedAt", "", "speed", "", "looping", "<init>", "(Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;JFZ)V", "component1", "()Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;", "component2", "()J", "component3", "()F", "component4", "()Z", "copy", "(Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;JFZ)Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionPlayback$RemotePlayback;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;", "getEmotion", "J", "getStartedAt", "F", "getSpeed", "Z", "getLooping", "rtx.kimiko:kimiko"})
    private static final class RemotePlayback {
        @NotNull
        private final Emotion emotion;
        private final long startedAt;
        private final float speed;
        private final boolean looping;

        public RemotePlayback(@NotNull Emotion emotion, long startedAt, float speed, boolean looping) {
            Intrinsics.checkNotNullParameter((Object)((Object)emotion), (String)"emotion");
            this.emotion = emotion;
            this.startedAt = startedAt;
            this.speed = speed;
            this.looping = looping;
        }

        @NotNull
        public final Emotion getEmotion() {
            return this.emotion;
        }

        public final long getStartedAt() {
            return this.startedAt;
        }

        public final float getSpeed() {
            return this.speed;
        }

        public final boolean getLooping() {
            return this.looping;
        }

        @NotNull
        public final Emotion component1() {
            return this.emotion;
        }

        public final long component2() {
            return this.startedAt;
        }

        public final float component3() {
            return this.speed;
        }

        public final boolean component4() {
            return this.looping;
        }

        @NotNull
        public final RemotePlayback copy(@NotNull Emotion emotion, long startedAt, float speed, boolean looping) {
            Intrinsics.checkNotNullParameter((Object)((Object)emotion), (String)"emotion");
            return new RemotePlayback(emotion, startedAt, speed, looping);
        }

        public static /* synthetic */ RemotePlayback copy$default(RemotePlayback remotePlayback, Emotion emotion, long l, float f, boolean bl, int n, Object object) {
            if ((n & 1) != 0) {
                emotion = remotePlayback.emotion;
            }
            if ((n & 2) != 0) {
                l = remotePlayback.startedAt;
            }
            if ((n & 4) != 0) {
                f = remotePlayback.speed;
            }
            if ((n & 8) != 0) {
                bl = remotePlayback.looping;
            }
            return remotePlayback.copy(emotion, l, f, bl);
        }

        @NotNull
        public String toString() {
            return "RemotePlayback(emotion=" + this.emotion + ", startedAt=" + this.startedAt + ", speed=" + this.speed + ", looping=" + this.looping + ")";
        }

        public int hashCode() {
            int result = this.emotion.hashCode();
            result = result * 31 + Long.hashCode(this.startedAt);
            result = result * 31 + Float.hashCode(this.speed);
            result = result * 31 + Boolean.hashCode(this.looping);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RemotePlayback)) {
                return false;
            }
            RemotePlayback remotePlayback = (RemotePlayback)other;
            if (this.emotion != remotePlayback.emotion) {
                return false;
            }
            if (this.startedAt != remotePlayback.startedAt) {
                return false;
            }
            if (Float.compare(this.speed, remotePlayback.speed) != 0) {
                return false;
            }
            return this.looping == remotePlayback.looping;
        }
    }
}

