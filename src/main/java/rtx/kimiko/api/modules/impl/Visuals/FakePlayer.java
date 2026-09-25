/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.damage.DamageSource
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.Entity.RemovalReason
 *  net.minecraft.entity.EquipmentSlot
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.Items
 *  net.minecraft.item.ItemConvertible
 *  net.minecraft.world.World
 *  net.minecraft.particle.ParticleEffect
 *  net.minecraft.particle.ParticleTypes
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.sound.SoundEvents
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.entity.attribute.EntityAttributes
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.OtherClientPlayerEntity
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ItemConvertible;
import net.minecraft.world.World;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.Event;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.player.AttackEntityEvent;
import rtx.kimiko.api.events.impl.player.TotemPopEvent;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.utils.chat.ChatMessage;

@Feature(value={"fakeplayer"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 *2\u00020\u0001:\u0002+*B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\u0006\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u001b\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0003b\u0002\b\t\u00a2\u0006\u0004\b\n\u0010\u000bJ%\u0010\u000e\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\fH\u0003b\f\b\t\u0012\b\b\r\u0012\u0004\b\u0003\u0010\b\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u000f\u0010\u0011\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0003J\u000f\u0010\u0012\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0003J\u000f\u0010\u0013\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0003J\u000f\u0010\u0014\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0003J\u000f\u0010\u0015\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0003J\u000f\u0010\u0016\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0003R\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0019R\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010 \u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0016\u0010\"\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\"\u0010!R\u0016\u0010$\u001a\u00020#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010&\u001a\u00020#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010%R\u0016\u0010(\u001a\u00020'8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010)\u00ca\u0001\u0010\b,\u0012\f\b\r\u0012\b\b\fJ\u0004\b\b(-\u00a8\u0006."}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/FakePlayer;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "onEnable", "onDisable", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "Lrtx/kimiko/api/events/impl/player/AttackEntityEvent;", "value", "onAttackEntity", "(Lrtx/kimiko/api/events/impl/player/AttackEntityEvent;)V", "spawnHitParticles", "spawn", "despawn", "move", "syncTotem", "triggerTotemPop", "popTotem", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "walk", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "rotate", "totem", "Lnet/minecraft/OtherClientPlayerEntity;", "fakePlayer", "Lnet/minecraft/OtherClientPlayerEntity;", "Lnet/minecraft/Vec3d;", "spawnPosition", "Lnet/minecraft/Vec3d;", "walkAxis", "", "spawnYaw", "F", "walkPhase", "", "hitsSinceTotem", "I", "Companion", "FakeRemotePlayer", "Lrtx/kimiko/api/liteapi/Feature;", "fakeplayer", "rtx.kimiko:kimiko"})
public final class FakePlayer
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final BooleanSetting walk = (BooleanSetting)this.register((Setting)new BooleanSetting("Ходьба", "Фейк-игрок ходит из стороны в сторону.", true));
    @NotNull
    private final BooleanSetting rotate = (BooleanSetting)this.register((Setting)new BooleanSetting("Поворот к игроку", "Фейк-игрок смотрит на тебя.", true));
    @NotNull
    private final BooleanSetting totem = (BooleanSetting)this.register((Setting)new BooleanSetting("Тотем", "Фейк-игрок держит тотем и ломает его каждые 3 удара.", false));
    @Nullable
    private OtherClientPlayerEntity fakePlayer;
    @NotNull
    private Vec3d spawnPosition;
    @NotNull
    private Vec3d walkAxis;
    private float spawnYaw;
    private float walkPhase;
    private int hitsSinceTotem;
    @NotNull
    private static final String[] NAMES;
    private static final float WALK_RADIUS = 2.0f;
    private static final float WALK_SPEED = 0.1f;
    private static final int ROTATION_LERP_TICKS = 3;

    public FakePlayer() {
        super("Fake Player", "Спавнит клиентского фейк-игрока для битья/теста. Только в одиночной игре.", Category.VISUALS);
        Vec3d vec3d2 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
        this.spawnPosition = vec3d2;
        Vec3d vec3d3 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"ZERO");
        this.walkAxis = vec3d3;
    }

    @Override
    protected void onEnable() {
        if (Companion.isLocalWorld()) {
            return;
        }
        ChatMessage.brandmessage(I18n.tr("FakePlayer работает только в одиночном мире."));
        this.mc.execute(() -> FakePlayer.onEnable$lambda$0(this));
    }

    @Override
    protected void onDisable() {
        this.despawn();
    }

    @EventHandler
    private final void onTick(TickEvent event) {
        if (!event.isPre()) {
            return;
        }
        if (!Companion.isLocalWorld()) {
            this.despawn();
            this.disable();
            return;
        }
        ClientPlayerEntity player = this.mc.player;
        ClientWorld level = this.mc.world;
        if (player == null || level == null) {
            this.fakePlayer = null;
            return;
        }
        OtherClientPlayerEntity currentFake = this.fakePlayer;
        if (currentFake == null || currentFake.isRemoved() || !Intrinsics.areEqual((Object)currentFake.getEntityWorld(), (Object)level)) {
            this.spawn();
        }
        this.syncTotem();
        this.move();
    }

    @EventHandler(value=4)
    private final void onAttackEntity(AttackEntityEvent event) {
        OtherClientPlayerEntity otherClientPlayerEntity2 = this.fakePlayer;
        if (otherClientPlayerEntity2 == null) {
            return;
        }
        OtherClientPlayerEntity currentFake = otherClientPlayerEntity2;
        if (!Intrinsics.areEqual((Object)event.getTarget(), (Object)currentFake)) {
            return;
        }
        event.cancel();
        ClientPlayerEntity player = this.mc.player;
        if (player != null) {
            player.attack((Entity)currentFake);
            this.spawnHitParticles();
            if (this.totem.getValue()) {
                ++this.hitsSinceTotem;
                if (this.hitsSinceTotem >= 3) {
                    this.triggerTotemPop();
                } else {
                    float health = currentFake.getMaxHealth() * (float)(3 - this.hitsSinceTotem) / 3.0f;
                    currentFake.setHealth(Math.max(1.0f, health));
                }
            }
        }
    }

    private final void spawnHitParticles() {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        OtherClientPlayerEntity otherClientPlayerEntity2 = this.fakePlayer;
        if (otherClientPlayerEntity2 == null) {
            return;
        }
        OtherClientPlayerEntity currentFake = otherClientPlayerEntity2;
        double damage = player.getAttributeValue(EntityAttributes.ATTACK_DAMAGE);
        int count = Math.max(1, (int)(damage * 0.5));
        for (int i = 0; i < count; ++i) {
            level.addParticleClient((ParticleEffect)ParticleTypes.DAMAGE_INDICATOR, currentFake.getX(), currentFake.getBodyY(0.5), currentFake.getZ(), level.random.nextGaussian() * 0.1, 0.0, level.random.nextGaussian() * 0.1);
        }
    }

    private final void spawn() {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        String name = NAMES[random.nextInt(NAMES.length)] + random.nextInt(10, 100);
        FakeRemotePlayer entity = new FakeRemotePlayer(level, new GameProfile(UUID.randomUUID(), name));
        entity.setId(-random.nextInt(1000000, 2000000));
        Vec3d vec3d2 = player.getEntityPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        this.spawnPosition = vec3d2;
        this.spawnYaw = player.getYaw();
        float yawRadians = this.spawnYaw * ((float)Math.PI / 180);
        this.walkAxis = new Vec3d((double)(-MathHelper.cos((double)yawRadians)), 0.0, (double)(-MathHelper.sin((double)yawRadians)));
        this.walkPhase = 0.0f;
        entity.refreshPositionAndAngles(this.spawnPosition.x, this.spawnPosition.y, this.spawnPosition.z, this.spawnYaw, 0.0f);
        entity.headYaw = this.spawnYaw;
        entity.setBodyYaw(this.spawnYaw);
        level.addEntity((Entity)entity);
        this.fakePlayer = entity;
        this.hitsSinceTotem = 0;
        this.syncTotem();
    }

    private final void despawn() {
        OtherClientPlayerEntity otherClientPlayerEntity2 = this.fakePlayer;
        if (otherClientPlayerEntity2 == null) {
            return;
        }
        OtherClientPlayerEntity currentFake = otherClientPlayerEntity2;
        World world2 = currentFake.getEntityWorld();
        Intrinsics.checkNotNullExpressionValue((Object)world2, (String)"level(...)");
        World world = world2;
        if (world instanceof ClientWorld) {
            ((ClientWorld)world).removeEntity(currentFake.getId(), Entity.RemovalReason.DISCARDED);
        }
        this.fakePlayer = null;
    }

    private final void move() {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        OtherClientPlayerEntity otherClientPlayerEntity2 = this.fakePlayer;
        if (otherClientPlayerEntity2 == null) {
            return;
        }
        OtherClientPlayerEntity currentFake = otherClientPlayerEntity2;
        Vec3d targetPosition = this.spawnPosition;
        float yaw = this.spawnYaw;
        float pitch = 0.0f;
        if (this.walk.getValue()) {
            this.walkPhase += 0.1f;
            Vec3d vec3d2 = this.spawnPosition.add(this.walkAxis.multiply((double)(MathHelper.sin((double)this.walkPhase) * 2.0f)));
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"add(...)");
            targetPosition = vec3d2;
            double sign = (double)MathHelper.cos((double)this.walkPhase) >= 0.0 ? 1.0 : -1.0;
            Vec3d vec3d3 = this.walkAxis.multiply(sign);
            Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"scale(...)");
            Vec3d direction = vec3d3;
            yaw = (float)Math.toDegrees(MathHelper.atan2((double)(-direction.x), (double)direction.z));
        }
        if (this.rotate.getValue()) {
            double deltaX = player.getX() - currentFake.getX();
            double deltaY = player.getEyeY() - currentFake.getEyeY();
            double deltaZ = player.getZ() - currentFake.getZ();
            yaw = (float)Math.toDegrees(MathHelper.atan2((double)deltaZ, (double)deltaX)) - 90.0f;
            pitch = (float)(-Math.toDegrees(MathHelper.atan2((double)deltaY, (double)Math.hypot(deltaX, deltaZ))));
        }
        currentFake.getInterpolator().refreshPositionAndAngles(targetPosition, yaw, pitch);
        currentFake.updateTrackedHeadRotation(yaw, 3);
    }

    private final void syncTotem() {
        ItemStack itemStack2;
        OtherClientPlayerEntity otherClientPlayerEntity2 = this.fakePlayer;
        if (otherClientPlayerEntity2 == null) {
            return;
        }
        OtherClientPlayerEntity currentFake = otherClientPlayerEntity2;
        if (this.totem.getValue()) {
            itemStack2 = new ItemStack((ItemConvertible)Items.TOTEM_OF_UNDYING);
        } else {
            ItemStack itemStack3 = ItemStack.EMPTY;
            itemStack2 = itemStack3;
            Intrinsics.checkNotNullExpressionValue((Object)itemStack3, (String)"EMPTY");
        }
        currentFake.equipStack(EquipmentSlot.OFFHAND, itemStack2);
        if (!this.totem.getValue()) {
            this.hitsSinceTotem = 0;
            currentFake.setHealth(currentFake.getMaxHealth());
        }
    }

    private final void triggerTotemPop() {
        OtherClientPlayerEntity otherClientPlayerEntity2 = this.fakePlayer;
        if (otherClientPlayerEntity2 == null) {
            return;
        }
        OtherClientPlayerEntity currentFake = otherClientPlayerEntity2;
        this.hitsSinceTotem = 0;
        currentFake.setHealth(1.0f);
        this.popTotem();
        currentFake.setHealth(currentFake.getMaxHealth());
    }

    private final void popTotem() {
        OtherClientPlayerEntity otherClientPlayerEntity2 = this.fakePlayer;
        if (otherClientPlayerEntity2 == null) {
            return;
        }
        OtherClientPlayerEntity currentFake = otherClientPlayerEntity2;
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        ItemStack stack = new ItemStack((ItemConvertible)Items.TOTEM_OF_UNDYING);
        currentFake.equipStack(EquipmentSlot.OFFHAND, stack);
        this.mc.particleManager.addEmitter((Entity)currentFake, (ParticleEffect)ParticleTypes.TOTEM_OF_UNDYING, 30);
        level.playSoundClient(currentFake.getX(), currentFake.getY(), currentFake.getZ(), SoundEvents.ITEM_TOTEM_USE, currentFake.getSoundCategory(), 1.0f, 1.0f, false);
        EventBus.Companion.get().post((Event)new TotemPopEvent((LivingEntity)currentFake, false));
    }

    private static final void onEnable$lambda$0(FakePlayer this$0) {
        this$0.disable();
    }

    @JvmStatic
    public static final boolean isLocalWorld() {
        return Companion.isLocalWorld();
    }

    @JvmStatic
    public static final boolean isFakePlayer(@NotNull Entity entity) {
        return Companion.isFakePlayer(entity);
    }

    static {
        String[] stringArray = new String[]{"Steve", "Alex", "Herobrine", "Nagibator", "Vitalik", "Sanya", "Dimon", "Leha", "KolyaPRO", "Artem", "Nikita", "Timoha", "Zhenya", "MaksFX", "Vladik"};
        NAMES = stringArray;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u000bR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/FakePlayer.Companion;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "isLocalWorld", "()Z", "Lnet/minecraft/Entity;", "entity", "isFakePlayer", "(Lnet/minecraft/Entity;)Z", "", "", "NAMES", "[Ljava/lang/String;", "", "WALK_RADIUS", "F", "WALK_SPEED", "", "ROTATION_LERP_TICKS", "I", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final boolean isLocalWorld() {
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient minecraft = minecraftClient2;
            return minecraft.world != null && minecraft.isIntegratedServerRunning();
        }

        @JvmStatic
        public final boolean isFakePlayer(@NotNull Entity entity) {
            Intrinsics.checkNotNullParameter((Object)entity, (String)"entity");
            return entity instanceof FakeRemotePlayer;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/FakePlayer$FakeRemotePlayer;", "Lnet/minecraft/OtherClientPlayerEntity;", "Lnet/minecraft/ClientWorld;", "level", "Lcom/mojang/authlib/GameProfile;", "profile", "<init>", "(Lnet/minecraft/ClientWorld;Lcom/mojang/authlib/GameProfile;)V", "Lnet/minecraft/DamageSource;", "source", "", "hurtClient", "(Lnet/minecraft/DamageSource;)Z", "rtx.kimiko:kimiko"})
    private static final class FakeRemotePlayer
    extends OtherClientPlayerEntity {
        public FakeRemotePlayer(@NotNull ClientWorld level, @NotNull GameProfile profile) {
            super(level, profile);
        }

        public boolean clientDamage(@NotNull DamageSource source) {
            Intrinsics.checkNotNullParameter((Object)source, (String)"source");
            this.onDamaged(source);
            return true;
        }
    }
}

