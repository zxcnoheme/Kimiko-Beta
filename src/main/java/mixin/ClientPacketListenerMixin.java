/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Hand
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.Items
 *  net.minecraft.world.World
 *  net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket
 *  net.minecraft.network.packet.s2c.play.PlaySoundFromEntityS2CPacket
 *  net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.sound.SoundEvent
 *  net.minecraft.client.network.ClientPlayNetworkHandler
 *  net.minecraft.component.DataComponentTypes
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package mixin;

import net.minecraft.util.Hand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.network.packet.s2c.play.PlaySoundFromEntityS2CPacket;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.component.DataComponentTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.impl.player.TotemPopEvent;
import rtx.kimiko.utils.entity.death.EntityDeathWatcher;
import rtx.kimiko.utils.entity.death.LocalDeathWatcher;

@Mixin(value={ClientPlayNetworkHandler.class})
public abstract class ClientPacketListenerMixin {
    @Inject(method={"onEntityStatus"}, at={@At(value="HEAD")}, require=0)
    private void kimiko$onEntityEvent(EntityStatusS2CPacket packet, CallbackInfo ci) {
        byte eventId = packet.getStatus();
        if (eventId != 35 && eventId != 3) {
            return;
        }
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world == null) {
            return;
        }
        Entity entity = packet.getEntity((World)mc.world);
        if (!(entity instanceof LivingEntity)) {
            return;
        }
        LivingEntity living = (LivingEntity)entity;
        if (eventId == 3) {
            LocalDeathWatcher.notifyDeathAnimation((Entity)living);
            return;
        }
        ItemStack totem = this.kimiko$findTotem(living);
        boolean enchanted = !totem.isEmpty() && totem.hasEnchantments();
        EventBus.get().post(new TotemPopEvent(living, enchanted));
    }

    @Inject(method={"onPlaySound"}, at={@At(value="TAIL")}, require=0)
    private void kimiko$onServerSound(PlaySoundS2CPacket packet, CallbackInfo ci) {
        EntityDeathWatcher.notifyServerSound(((SoundEvent)packet.getSound().value()).id(), packet.getX(), packet.getY(), packet.getZ());
    }

    @Inject(method={"onPlaySoundFromEntity"}, at={@At(value="TAIL")}, require=0)
    private void kimiko$onServerEntitySound(PlaySoundFromEntityS2CPacket packet, CallbackInfo ci) {
        EntityDeathWatcher.notifyServerEntitySound(((SoundEvent)packet.getSound().value()).id(), packet.getEntityId());
    }

    @Unique
    private ItemStack kimiko$findTotem(LivingEntity entity) {
        for (Hand hand : Hand.values()) {
            ItemStack stack = entity.getStackInHand(hand);
            if (!stack.contains(DataComponentTypes.DEATH_PROTECTION) && !stack.isOf(Items.TOTEM_OF_UNDYING)) continue;
            return stack;
        }
        return ItemStack.EMPTY;
    }
}

