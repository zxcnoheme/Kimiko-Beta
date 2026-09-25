/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.Hand
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.decoration.EndCrystalEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.util.hit.HitResult
 *  net.minecraft.util.hit.EntityHitResult
 *  net.minecraft.client.network.ClientPlayerInteractionManager
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Hand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.restrict.Server;
import rtx.kimiko.api.modules.restrict.ServerRule;

@ServerRule(mode=ServerRule.Mode.ONLY, servers={Server.RW, Server.SATURN})
@Feature(value={"crystaloptimizer"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\t\u00ca\u0001&\b\n\u0012\n\b\u000b\u0012\u0006\b\n0\f8\r\u0012\u0016\b\u000e\u0012\u0012\b\fJ\u0006\b\n0\u000f8\u0010J\u0006\b\n0\u000f8\u0011\u00ca\u0001\u0010\b\u0012\u0012\f\b\u0013\u0012\b\b\fJ\u0004\b\b(\u0014\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/CrystalOptimizer;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "Lrtx/kimiko/api/modules/restrict/ServerRule;", "mode", "Lrtx/kimiko/api/modules/restrict/ServerRule$Mode;", "ONLY", "servers", "Lrtx/kimiko/api/modules/restrict/Server;", "RW", "SATURN", "Lrtx/kimiko/api/liteapi/Feature;", "value", "crystaloptimizer", "rtx.kimiko:kimiko"})
public final class CrystalOptimizer
extends Module {
    public CrystalOptimizer() {
        super("Crystal Optimizer", "Убирает задержку разбивания энд-кристаллов при удержании атаки.", Category.UTILS);
    }

    @EventHandler
    public final void onTick(@NotNull TickEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!event.isPre()) {
            return;
        }
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        ClientPlayerInteractionManager clientPlayerInteractionManager2 = this.mc.interactionManager;
        if (clientPlayerInteractionManager2 == null) {
            return;
        }
        ClientPlayerInteractionManager gameMode = clientPlayerInteractionManager2;
        if (this.mc.world == null || this.mc.currentScreen != null) {
            return;
        }
        if (!this.mc.options.attackKey.isPressed()) {
            return;
        }
        HitResult hit = this.mc.crosshairTarget;
        if (!(hit instanceof EntityHitResult)) {
            return;
        }
        Entity entity2 = ((EntityHitResult)hit).getEntity();
        EndCrystalEntity endCrystalEntity2 = entity2 instanceof EndCrystalEntity ? (EndCrystalEntity)entity2 : null;
        if (endCrystalEntity2 == null) {
            return;
        }
        EndCrystalEntity crystal = endCrystalEntity2;
        player.resetTicksSince();
        gameMode.attackEntity((PlayerEntity)player, (Entity)crystal);
        player.swingHand(Hand.MAIN_HAND);
    }
}

