/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.sound.SoundEvent
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.killeffect;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.sounds.SoundManager;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0014B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u000b\u001a\u00020\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\n\u00a2\u0006\u0004\b\r\u0010\u0003J\r\u0010\u000e\u001a\u00020\n\u00a2\u0006\u0004\b\u000e\u0010\u0003R$\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u00118\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectSoundQueue;", "", "<init>", "()V", "Lnet/minecraft/SoundEvent;", "sound", "", "volume", "", "delayMs", "", "schedule", "(Lnet/minecraft/SoundEvent;FJ)V", "tick", "clear", "Ljava/util/ArrayList;", "Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectSoundQueue$PendingSound;", "Lkotlin/collections/ArrayList;", "pendingSounds", "Ljava/util/ArrayList;", "PendingSound", "rtx.kimiko:kimiko"})
public final class KillEffectSoundQueue {
    @NotNull
    private final ArrayList<PendingSound> pendingSounds = new ArrayList();

    public final void schedule(@Nullable SoundEvent sound, float volume, long delayMs) {
        if (sound == null) {
            return;
        }
        if (delayMs <= 0L) {
            SoundManager.playSoundDirect(sound, volume, 1.0f);
            return;
        }
        this.pendingSounds.add(new PendingSound(sound, volume, System.currentTimeMillis() + delayMs));
    }

    public final void tick() {
        Iterator<PendingSound> iterator = this.pendingSounds.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<PendingSound> iterator2 = iterator;
        long now = System.currentTimeMillis();
        while (iterator2.hasNext()) {
            PendingSound sound = (PendingSound) (iterator2.next());
            if (now < sound.getPlayAt()) continue;
            SoundManager.playSoundDirect(sound.getSound(), sound.getVolume(), 1.0f);
            iterator2.remove();
        }
    }

    public final void clear() {
        this.pendingSounds.clear();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ.\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00c6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u0017\u001a\u00020\u0016H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0011\u0010\u001a\u001a\u00020\u0019H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010\rR\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010 \u001a\u0004\b!\u0010\u000f\u00a8\u0006\""}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectSoundQueue$PendingSound;", "", "Lnet/minecraft/SoundEvent;", "sound", "", "volume", "", "playAt", "<init>", "(Lnet/minecraft/SoundEvent;FJ)V", "component1", "()Lnet/minecraft/SoundEvent;", "component2", "()F", "component3", "()J", "copy", "(Lnet/minecraft/SoundEvent;FJ)Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectSoundQueue$PendingSound;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/SoundEvent;", "getSound", "F", "getVolume", "J", "getPlayAt", "rtx.kimiko:kimiko"})
    private static final class PendingSound {
        @NotNull
        private final SoundEvent sound;
        private final float volume;
        private final long playAt;

        public PendingSound(@NotNull SoundEvent sound, float volume, long playAt) {
            Intrinsics.checkNotNullParameter((Object)sound, (String)"sound");
            this.sound = sound;
            this.volume = volume;
            this.playAt = playAt;
        }

        @NotNull
        public final SoundEvent getSound() {
            return this.sound;
        }

        public final float getVolume() {
            return this.volume;
        }

        public final long getPlayAt() {
            return this.playAt;
        }

        @NotNull
        public final SoundEvent component1() {
            return this.sound;
        }

        public final float component2() {
            return this.volume;
        }

        public final long component3() {
            return this.playAt;
        }

        @NotNull
        public final PendingSound copy(@NotNull SoundEvent sound, float volume, long playAt) {
            Intrinsics.checkNotNullParameter((Object)sound, (String)"sound");
            return new PendingSound(sound, volume, playAt);
        }

        public static /* synthetic */ PendingSound copy$default(PendingSound pendingSound, SoundEvent soundEvent2, float f, long l, int n, Object object) {
            if ((n & 1) != 0) {
                soundEvent2 = pendingSound.sound;
            }
            if ((n & 2) != 0) {
                f = pendingSound.volume;
            }
            if ((n & 4) != 0) {
                l = pendingSound.playAt;
            }
            return pendingSound.copy(soundEvent2, f, l);
        }

        @NotNull
        public String toString() {
            return "PendingSound(sound=" + this.sound + ", volume=" + this.volume + ", playAt=" + this.playAt + ")";
        }

        public int hashCode() {
            int result = this.sound.hashCode();
            result = result * 31 + Float.hashCode(this.volume);
            result = result * 31 + Long.hashCode(this.playAt);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PendingSound)) {
                return false;
            }
            PendingSound pendingSound = (PendingSound)other;
            if (!Intrinsics.areEqual((Object)this.sound, (Object)pendingSound.sound)) {
                return false;
            }
            if (Float.compare(this.volume, pendingSound.volume) != 0) {
                return false;
            }
            return this.playAt == pendingSound.playAt;
        }
    }
}

