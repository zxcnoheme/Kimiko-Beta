/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.entity.death;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.player.AttackEntityEvent;
import rtx.kimiko.utils.entity.death.EntityDeathDetector;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001.B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001b\u0010\r\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u000fH\u0007b\u0002\b\f\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0014\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0014\u0010\u0015J3\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0018H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001c\u0010\u001dJ#\u0010 \u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u001eH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b \u0010!J\u0013\u0010\"\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\"\u0010\u0003J\u0017\u0010#\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b#\u0010\u0015R\u0014\u0010%\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010&R$\u0010)\u001a\u0012\u0012\u0004\u0012\u00020\u00040'j\b\u0012\u0004\u0012\u00020\u0004`(8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010,\u001a\u00020+8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010-\u00a8\u0006/"}, d2={"Lrtx/kimiko/utils/entity/death/EntityDeathWatcher;", "", "<init>", "()V", "Lrtx/kimiko/utils/entity/death/EntityDeathWatcher$Listener;", "listener", "", "Lkotlin/jvm/JvmStatic;", "register", "(Lrtx/kimiko/utils/entity/death/EntityDeathWatcher$Listener;)V", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "Lrtx/kimiko/api/events/impl/player/AttackEntityEvent;", "onAttack", "(Lrtx/kimiko/api/events/impl/player/AttackEntityEvent;)V", "Lnet/minecraft/LivingEntity;", "victim", "notifyEntityDied", "(Lnet/minecraft/LivingEntity;)V", "Lnet/minecraft/Identifier;", "sound", "", "x", "y", "z", "notifyServerSound", "(Lnet/minecraft/Identifier;DDD)V", "", "entityId", "notifyServerEntitySound", "(Lnet/minecraft/Identifier;I)V", "clear", "dispatch", "Lrtx/kimiko/utils/entity/death/EntityDeathDetector;", "detector", "Lrtx/kimiko/utils/entity/death/EntityDeathDetector;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "listeners", "Ljava/util/ArrayList;", "", "subscribed", "Z", "Listener", "rtx.kimiko:kimiko"})
public final class EntityDeathWatcher {
    @NotNull
    public static final EntityDeathWatcher INSTANCE = new EntityDeathWatcher();
    @NotNull
    private static final EntityDeathDetector detector = new EntityDeathDetector(EntityDeathWatcher::detector$lambda$0);
    @NotNull
    private static final ArrayList<Listener> listeners = new ArrayList();
    private static boolean subscribed;

    private EntityDeathWatcher() {
    }

    @JvmStatic
    public static final void register(@NotNull Listener listener) {
        Intrinsics.checkNotNullParameter((Object)listener, (String)"listener");
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
        if (!subscribed) {
            subscribed = true;
            EventBus.Companion.get().subscribe(INSTANCE);
        }
    }

    @EventHandler
    public final void onTick(@NotNull TickEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!event.isPost()) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        if (minecraft.player == null || minecraft.world == null) {
            detector.clear();
            return;
        }
        double distance = 0.0;
        boolean aggressive = false;
        boolean any = false;
        Iterator<Listener> iterator = listeners.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Listener> iterator2 = iterator;
        while (iterator2.hasNext()) {
            Listener listener = (Listener) (iterator2.next());
            if (!listener.deathWatchActive()) continue;
            any = true;
            distance = Math.max(distance, listener.deathWatchDistance());
            aggressive = aggressive || listener.deathWatchAggressive();
        }
        if (!any) {
            detector.clear();
            return;
        }
        detector.tick(distance, aggressive);
    }

    @EventHandler
    public final void onAttack(@NotNull AttackEntityEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        Entity target = event.getTarget();
        if (target instanceof LivingEntity && target != MinecraftClient.getInstance().player) {
            detector.onAttack((LivingEntity)target);
        }
    }

    @JvmStatic
    public static final void notifyEntityDied(@Nullable LivingEntity victim) {
        if (victim == null) {
            return;
        }
        INSTANCE.dispatch(victim);
    }

    @JvmStatic
    public static final void notifyServerSound(@NotNull Identifier sound, double x, double y, double z) {
        Intrinsics.checkNotNullParameter((Object)sound, (String)"sound");
        detector.onServerSound(sound, x, y, z);
    }

    @JvmStatic
    public static final void notifyServerEntitySound(@NotNull Identifier sound, int entityId) {
        Intrinsics.checkNotNullParameter((Object)sound, (String)"sound");
        detector.onServerEntitySound(sound, entityId);
    }

    @JvmStatic
    public static final void clear() {
        detector.clear();
    }

    private final void dispatch(LivingEntity victim) {
        Iterator<Listener> iterator = listeners.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Listener> iterator2 = iterator;
        while (iterator2.hasNext()) {
            Listener listener = (Listener) (iterator2.next());
            if (!listener.deathWatchActive()) continue;
            listener.onEntityDeath(victim);
        }
    }

    private static final void detector$lambda$0(LivingEntity victim) {
        Intrinsics.checkNotNullParameter((Object)victim, (String)"victim");
        INSTANCE.dispatch(victim);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H&\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\b\u0010\u0004J\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH&\u00a2\u0006\u0004\b\f\u0010\r\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006\u000e\u00c0\u0006\u0001"}, d2={"Lrtx/kimiko/utils/entity/death/EntityDeathWatcher$Listener;", "", "", "deathWatchActive", "()Z", "", "deathWatchDistance", "()D", "deathWatchAggressive", "Lnet/minecraft/LivingEntity;", "victim", "", "onEntityDeath", "(Lnet/minecraft/LivingEntity;)V", "rtx.kimiko:kimiko"})
    public static interface Listener {
        public boolean deathWatchActive();

        public double deathWatchDistance();

        public boolean deathWatchAggressive();

        public void onEntityDeath(@NotNull LivingEntity var1);
    }
}

