/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.decoration.EndCrystalEntity
 *  net.minecraft.particle.ParticleType
 *  net.minecraft.particle.ParticleTypes
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.network.packet.s2c.play.ExplosionS2CPacket
 *  net.minecraft.network.packet.s2c.play.ParticleS2CPacket
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket
 *  net.minecraft.sound.SoundEvents
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Vector4f
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.sound.SoundEvents;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector4f;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.network.PacketReceiveEvent;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.NumberSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.utils.render.modules.post.explosionwave.ExplosionWaveRenderer;
import rtx.kimiko.utils.render.others.ScreenShake;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"explosionwave"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00b8\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 T2\u00020\u0001:\u0004UVWTB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u0004H\u0017b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0014\u00a2\u0006\u0004\b\f\u0010\u0003J\u001b\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0003b\u0002\b\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0011J+\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0012H\u0003b\u0002\b\u000fb\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\u0013\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0003J\u0017\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010\u001d\u001a\u00020\u000bH\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\u001c\u00a2\u0006\u0004\b\u001d\u0010\u0003JG\u0010%\u001a\u00020\u000b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\u0010!\u001a\u0004\u0018\u00010 2\b\u0010\"\u001a\u0004\u0018\u00010 2\b\u0010$\u001a\u0004\u0018\u00010#H\u0007b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\u001c\u00a2\u0006\u0004\b%\u0010&R\u001a\u0010)\u001a\b\u0012\u0004\u0012\u00020(0'8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u001a\u0010-\u001a\b\u0012\u0004\u0012\u00020,0+8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u001a\u00100\u001a\b\u0012\u0004\u0012\u00020/0+8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u0010.R \u00103\u001a\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020/018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b3\u00104R\u0014\u00105\u001a\u00020 8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00106R\u0014\u00107\u001a\u00020 8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b7\u00106R\u0014\u00109\u001a\u0002088\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b9\u0010:R\u0014\u0010<\u001a\u00020;8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u0010=R\u0014\u0010?\u001a\u00020>8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u0010@R\u0014\u0010B\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0014\u0010D\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u0010CR\u0014\u0010E\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010CR\u0014\u0010F\u001a\u00020>8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bF\u0010@R\u0014\u0010G\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010CR\u0014\u0010H\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u0010CR\u0014\u0010J\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010KR\u0014\u0010L\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u0010CR\u0014\u0010M\u001a\u00020>8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bM\u0010@R\u0014\u0010N\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bN\u0010KR\u0014\u0010O\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bO\u0010CR\u0014\u0010P\u001a\u00020>8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bP\u0010@R\u0014\u0010Q\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bQ\u0010KR\u0014\u0010R\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bR\u0010CR\u0014\u0010S\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bS\u0010C\u00ca\u0001\u0010\bX\u0012\f\b\u0006\u0012\b\b\fJ\u0004\b\b(Y\u00a8\u0006Z"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ExplosionWave;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "fadeOutSeconds", "()F", "", "onDisable", "Lrtx/kimiko/api/events/impl/network/PacketReceiveEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onPacketReceive", "(Lrtx/kimiko/api/events/impl/network/PacketReceiveEvent;)V", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "STD", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "trackCrystals", "Lnet/minecraft/Vec3d;", "center", "", "nearCrystal", "(Lnet/minecraft/Vec3d;)Z", "MAX", "drainPending", "Lnet/minecraft/Framebuffer;", "renderTarget", "Lorg/joml/Matrix4f;", "positionMatrix", "projectionMatrix", "Lnet/minecraft/Camera;", "camera", "onAfterWorld", "(Lnet/minecraft/Framebuffer;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/Camera;)V", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Lrtx/kimiko/api/modules/impl/Visuals/ExplosionWave$PendingBlast;", "pending", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "", "Lrtx/kimiko/api/modules/impl/Visuals/ExplosionWave$Wave;", "waves", "Ljava/util/List;", "Lrtx/kimiko/api/modules/impl/Visuals/ExplosionWave$RecentBlast;", "recentBlasts", "", "", "crystalTracks", "Ljava/util/Map;", "invViewProj", "Lorg/joml/Matrix4f;", "viewProj", "Lorg/joml/Vector4f;", "projScratch", "Lorg/joml/Vector4f;", "", "uniformScratch", "[F", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "generalSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "waveTime", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "waveRadius", "maxDistance", "distortSeparator", "distortStrength", "waveThickness", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "chromatic", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "chromaticStrength", "flashSeparator", "flash", "flashStrength", "shakeSeparator", "shake", "shakeStrength", "shakeTime", "Companion", "PendingBlast", "RecentBlast", "Wave", "Lrtx/kimiko/api/liteapi/Feature;", "explosionwave", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nExplosionWave.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExplosionWave.kt\nrtx/kimiko/api/modules/impl/Visuals/ExplosionWave\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,325:1\n1#2:326\n*E\n"})
public final class ExplosionWave
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ConcurrentLinkedQueue<PendingBlast> pending = new ConcurrentLinkedQueue();
    @NotNull
    private final List<Wave> waves = new ArrayList();
    @NotNull
    private final List<RecentBlast> recentBlasts = new ArrayList();
    @NotNull
    private final Map<Integer, RecentBlast> crystalTracks = new HashMap();
    @NotNull
    private final Matrix4f invViewProj = new Matrix4f();
    @NotNull
    private final Matrix4f viewProj = new Matrix4f();
    @NotNull
    private final Vector4f projScratch = new Vector4f();
    @NotNull
    private final float[] uniformScratch = new float[120];
    @NotNull
    private final SeparatorSetting generalSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Основное"));
    @NotNull
    private final NumberSetting waveTime = (NumberSetting)this.register((Setting)new NumberSetting("Время волны", "Время жизни ударной волны.", 2000.0, 600.0, 3000.0, 50.0));
    @NotNull
    private final NumberSetting waveRadius = (NumberSetting)this.register((Setting)new NumberSetting("Радиус волны", "Максимальный радиус волны от взрыва ТНТ.", 15.0, 5.0, 30.0, 0.5));
    @NotNull
    private final NumberSetting maxDistance = (NumberSetting)this.register((Setting)new NumberSetting("Дистанция", "Максимальная дистанция до взрыва, на которой виден эффект.", 50.0, 10.0, 96.0, 1.0));
    @NotNull
    private final SeparatorSetting distortSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Искажение"));
    @NotNull
    private final NumberSetting distortStrength = (NumberSetting)this.register((Setting)new NumberSetting("Сила искажения", "Насколько сильно волна преломляет мир.", 3.0, 0.2, 3.0, 0.1));
    @NotNull
    private final NumberSetting waveThickness = (NumberSetting)this.register((Setting)new NumberSetting("Толщина", "Толщина оболочки волны (относительно радиуса).", 25.0, 10.0, 60.0, 1.0));
    @NotNull
    private final BooleanSetting chromatic = (BooleanSetting)this.register((Setting)new BooleanSetting("Хроматика", "Хроматическая аберрация на кромке волны.", true));
    @NotNull
    private final NumberSetting chromaticStrength = (NumberSetting)this.register((Setting)new NumberSetting("Сила хроматики", "Сила расщепления цвета на кромке.", 1.5, 0.2, 3.0, 0.1).visibleWhen(() -> ExplosionWave.chromaticStrength$lambda$0(this)));
    @NotNull
    private final SeparatorSetting flashSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Вспышка"));
    @NotNull
    private final BooleanSetting flash = (BooleanSetting)this.register((Setting)new BooleanSetting("Вспышка", "Короткий засвет экрана в момент детонации.", true));
    @NotNull
    private final NumberSetting flashStrength = (NumberSetting)this.register((Setting)new NumberSetting("Сила вспышки", "Яркость вспышки при детонации.", 50.0, 10.0, 100.0, 1.0).visibleWhen(() -> ExplosionWave.flashStrength$lambda$0(this)));
    @NotNull
    private final SeparatorSetting shakeSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Тряска"));
    @NotNull
    private final BooleanSetting shake = (BooleanSetting)this.register((Setting)new BooleanSetting("Тряска экрана", "Трясёт камеру при взрыве, сила зависит от дистанции.", true));
    @NotNull
    private final NumberSetting shakeStrength = (NumberSetting)this.register((Setting)new NumberSetting("Сила тряски", "Амплитуда тряски камеры вблизи взрыва.", 1.5, 0.2, 3.0, 0.1).visibleWhen(() -> ExplosionWave.shakeStrength$lambda$0(this)));
    @NotNull
    private final NumberSetting shakeTime = (NumberSetting)this.register((Setting)new NumberSetting("Время тряски", "Длительность затухания тряски.", 1000.0, 200.0, 1500.0, 50.0).visibleWhen(() -> ExplosionWave.shakeTime$lambda$0(this)));
    @JvmField
    @Nullable
    public static ExplosionWave INSTANCE;

    public ExplosionWave() {
        super("Explosion Wave", "Ударная волна и тряска экрана при взрыве.", Category.VISUALS);
        INSTANCE = this;
    }

    @Override
    @Protect(value=Level.CROWN)
    public float fadeOutSeconds() {
        return 0.5f;
    }

    @Override
    protected void onDisable() {
        this.pending.clear();
        this.waves.clear();
        this.recentBlasts.clear();
        this.crystalTracks.clear();
        ExplosionWaveRenderer.clear();
        ScreenShake.clear();
    }

    @EventHandler
    private final void onPacketReceive(PacketReceiveEvent event) {
        ExplosionS2CPacket explode = event.getPacketAs(ExplosionS2CPacket.class);
        if (explode != null) {
            if (!ExplosionWave.Companion.isWindBurst(explode)) {
                Vec3d vec3d2 = explode.center();
                Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"center(...)");
                this.pending.add(new PendingBlast(vec3d2, explode.radius()));
            }
            return;
        }
        PlaySoundS2CPacket sound = event.getPacketAs(PlaySoundS2CPacket.class);
        if (sound != null) {
            if (sound.getSound() != null && sound.getSound().value() == SoundEvents.ENTITY_GENERIC_EXPLODE.value()) {
                this.pending.add(new PendingBlast(new Vec3d(sound.getX(), sound.getY(), sound.getZ()), 4.0f));
            }
            return;
        }
        ParticleS2CPacket particles = event.getPacketAs(ParticleS2CPacket.class);
        if (particles != null && particles.getParameters() != null) {
            ParticleType particleType2 = particles.getParameters().getType();
            Intrinsics.checkNotNullExpressionValue((Object)particleType2, (String)"getType(...)");
            ParticleType type = particleType2;
            if (type == ParticleTypes.EXPLOSION_EMITTER) {
                this.pending.add(new PendingBlast(new Vec3d(particles.getX(), particles.getY(), particles.getZ()), 4.0f));
            } else if (type == ParticleTypes.EXPLOSION) {
                this.pending.add(new PendingBlast(new Vec3d(particles.getX(), particles.getY(), particles.getZ()), 2.0f));
            }
        }
    }

    @EventHandler
    @Protect(value=Level.STD)
    private final void onWorldRender(WorldRenderEvent event) {
        this.trackCrystals();
        this.drainPending();
        float life = Math.max(1.0f, this.waveTime.getFloat());
        for (int i = this.waves.size() - 1; -1 < i; --i) {
            Wave wave = this.waves.get(i);
            if (!(wave.progress(life) >= 1.0f) && ExplosionWave.Companion.isFiniteAndSafe(wave.getPos().x, wave.getPos().y, wave.getPos().z)) continue;
            this.waves.remove(i);
        }
    }

    private final void trackCrystals() {
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        long now = System.currentTimeMillis();
        for (Object t : level.getEntities()) {
            Intrinsics.checkNotNullExpressionValue(t, (String)"next(...)");
            Entity entity = (Entity)t;
            if (!(entity instanceof EndCrystalEntity)) continue;
            Map<Integer, RecentBlast> map = this.crystalTracks;
            Integer n = ((EndCrystalEntity)entity).getId();
            Vec3d vec3d2 = entity.getEntityPos();
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
            RecentBlast recentBlast = new RecentBlast(vec3d2, now);
            map.put(n, recentBlast);
        }
        this.crystalTracks.values().removeIf(track -> now - track.getTime() > 2000L);
    }

    private final boolean nearCrystal(Vec3d center) {
        for (RecentBlast track : this.crystalTracks.values()) {
            if (!(track.getPos().squaredDistanceTo(center) < 16.0)) continue;
            return true;
        }
        return false;
    }

    @Protect(value=Level.MAX)
    private final void drainPending() {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        long now = System.currentTimeMillis();
        this.recentBlasts.removeIf(recent -> now - recent.getTime() > 400L);
        PendingBlast currentBlast;
        while ((currentBlast = this.pending.poll()) != null) {
            float maxDist;
            double dist;
            if (!ExplosionWave.Companion.isFiniteAndSafe(currentBlast.getCenter().x, currentBlast.getCenter().y, currentBlast.getCenter().z) || (dist = player.getEntityPos().distanceTo(currentBlast.getCenter())) > (double)(maxDist = this.maxDistance.getFloat())) continue;
            boolean duplicate = false;
            for (RecentBlast recent : this.recentBlasts) {
                if (!(recent.getPos().squaredDistanceTo(currentBlast.getCenter()) < 12.25)) continue;
                duplicate = true;
                break;
            }
            if (duplicate || this.nearCrystal(currentBlast.getCenter())) continue;
            this.recentBlasts.add(new RecentBlast(currentBlast.getCenter(), now));
            float falloff = (float)Math.pow(1.0 - dist / (double)maxDist, 1.5);
            float power = Math.max(Math.min(currentBlast.getPower() / 4.0f, 2.5f), 0.6f);
            if (this.waves.size() >= 8) {
                this.waves.remove(0);
            }
            this.waves.add(new Wave(currentBlast.getCenter(), power, falloff));
            float amp;
            if (!this.shake.getValue() || !((amp = 3.5f * this.shakeStrength.getFloat() * falloff * power) > 0.05f)) continue;
            ScreenShake.add(amp, (int)this.shakeTime.getFloat(), 9.0f);
        }
    }

    @Protect(value=Level.MAX)
    public final void onAfterWorld(@Nullable Framebuffer renderTarget, @Nullable Matrix4f positionMatrix, @Nullable Matrix4f projectionMatrix, @Nullable Camera camera) {
        if (!this.isEnabled() || renderTarget == null || camera == null || this.waves.isEmpty()) {
            return;
        }
        if (renderTarget.textureWidth <= 0 || renderTarget.textureHeight <= 0) {
            return;
        }
        float life = Math.max(1.0f, this.waveTime.getFloat());
        float maxRadiusSetting = this.waveRadius.getFloat();
        float baseAmp = 0.022f * this.distortStrength.getFloat() * this.visualAlpha();
        float aspect = (float)renderTarget.textureWidth / (float)Math.max(1, renderTarget.textureHeight);
        Vec3d vec3d2 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d cam = vec3d2;
        this.invViewProj.set((Matrix4fc)projectionMatrix).mul((Matrix4fc)positionMatrix).invert();
        this.viewProj.set((Matrix4fc)projectionMatrix).mul((Matrix4fc)positionMatrix);
        float[] data = this.uniformScratch;
        int count = 0;
        float globalFlash = 0.0f;
        for (Wave wave : this.waves) {
            float fadeOut;
            if (count >= 8) break;
            float progress = wave.progress(life);
            if (progress < 0.0f || progress >= 1.0f) continue;
            float eased = 1.0f - (1.0f - progress) * (1.0f - progress);
            float maxRadius = maxRadiusSetting * wave.getPower();
            float radius = Math.max(eased * maxRadius, 0.05f);
            float thickness = Math.max(0.3f, maxRadius * this.waveThickness.getFloat() / 100.0f);
            float fadeIn = ExplosionWave.Companion.clamp(progress / 0.05f, 0.0f, 1.0f);
            float env = fadeIn * (fadeOut = ExplosionWave.Companion.clamp((1.0f - progress) / 0.4f, 0.0f, 1.0f)) * wave.getFalloff();
            if (env <= 0.001f) continue;
            float waveFlash = this.flash.getValue() ? Math.max(0.0f, 1.0f - progress / 0.12f) * wave.getFalloff() : 0.0f;
            globalFlash = Math.max(globalFlash, waveFlash * this.flashStrength.getFloat() / 100.0f);
            float cx = (float)(wave.getPos().x - cam.x);
            float cy = (float)(wave.getPos().y - cam.y);
            float cz = (float)(wave.getPos().z - cam.z);
            this.projScratch.set(cx, cy, cz, 1.0f);
            this.viewProj.transform(this.projScratch);
            float sx = 0.0f;
            float sy = 0.0f;
            float valid = 0.0f;
            if (this.projScratch.w > 0.001f) {
                sx = this.projScratch.x / this.projScratch.w * 0.5f + 0.5f;
                sy = this.projScratch.y / this.projScratch.w * 0.5f + 0.5f;
                valid = 1.0f;
            }
            int base = 24 + count * 12;
            data[base] = cx;
            data[base + 1] = cy;
            data[base + 2] = cz;
            data[base + 3] = radius;
            data[base + 4] = thickness;
            data[base + 5] = baseAmp * env;
            data[base + 6] = env;
            data[base + 7] = waveFlash;
            data[base + 8] = sx;
            data[base + 9] = sy;
            data[base + 10] = valid;
            data[base + 11] = (float)Math.sqrt(cx * cx + cy * cy + cz * cz);
            ++count;
        }
        if (count == 0) {
            return;
        }
        data[0] = count;
        data[1] = aspect;
        data[2] = this.chromatic.getValue() ? 0.35f * this.chromaticStrength.getFloat() : 0.0f;
        data[3] = globalFlash;
        data[4] = 0.35f;
        data[5] = 0.0f;
        data[6] = 0.0f;
        data[7] = 0.0f;
        this.invViewProj.get(data, 8);
        ExplosionWaveRenderer.apply(renderTarget, data);
    }

    private static final Boolean chromaticStrength$lambda$0(ExplosionWave this$0) {
        return this$0.chromatic.getValue();
    }

    private static final Boolean flashStrength$lambda$0(ExplosionWave this$0) {
        return this$0.flash.getValue();
    }

    private static final Boolean shakeStrength$lambda$0(ExplosionWave this$0) {
        return this$0.shake.getValue();
    }

    private static final Boolean shakeTime$lambda$0(ExplosionWave this$0) {
        return this$0.shake.getValue();
    }

    @JvmStatic
    @Nullable
    public static final ExplosionWave getInstance() {
        return Companion.getInstance();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ'\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u001aR\u001d\u0010\u001c\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001d\u00a8\u0006\u001e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ExplosionWave.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/ExplosionWave;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/ExplosionWave;", "Lnet/minecraft/ExplosionS2CPacket;", "packet", "", "isWindBurst", "(Lnet/minecraft/ExplosionS2CPacket;)Z", "", "v", "min", "max", "clamp", "(FFF)F", "", "x", "y", "z", "isFiniteAndSafe", "(DDD)Z", "value", "(D)Z", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/ExplosionWave;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final ExplosionWave getInstance() {
            ExplosionWave module = ModuleManager.Companion.get().get(ExplosionWave.class);
            ExplosionWave explosionWave = module;
            if (explosionWave == null) {
                explosionWave = INSTANCE;
            }
            return explosionWave;
        }

        private final boolean isWindBurst(ExplosionS2CPacket packet) {
            ParticleType type = packet.explosionParticle() != null ? packet.explosionParticle().getType() : null;
            return type == ParticleTypes.GUST || type == ParticleTypes.SMALL_GUST || type == ParticleTypes.GUST_EMITTER_SMALL || type == ParticleTypes.GUST_EMITTER_LARGE;
        }

        private final float clamp(float v, float min, float max) {
            return Math.max(min, Math.min(max, v));
        }

        private final boolean isFiniteAndSafe(double x, double y, double z) {
            return this.isFiniteAndSafe(x) && this.isFiniteAndSafe(y) && this.isFiniteAndSafe(z);
        }

        private final boolean isFiniteAndSafe(double value) {
            return Double.isFinite(value) && Math.abs(value) <= 3.0E7;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ$\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0016\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u000b\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ExplosionWave$PendingBlast;", "", "Lnet/minecraft/Vec3d;", "center", "", "power", "<init>", "(Lnet/minecraft/Vec3d;F)V", "component1", "()Lnet/minecraft/Vec3d;", "component2", "()F", "copy", "(Lnet/minecraft/Vec3d;F)Lrtx/kimiko/api/modules/impl/Visuals/ExplosionWave$PendingBlast;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/Vec3d;", "getCenter", "F", "getPower", "rtx.kimiko:kimiko"})
    private static final class PendingBlast {
        @NotNull
        private final Vec3d center;
        private final float power;

        public PendingBlast(@NotNull Vec3d center, float power) {
            Intrinsics.checkNotNullParameter((Object)center, (String)"center");
            this.center = center;
            this.power = power;
        }

        @NotNull
        public final Vec3d getCenter() {
            return this.center;
        }

        public final float getPower() {
            return this.power;
        }

        @NotNull
        public final Vec3d component1() {
            return this.center;
        }

        public final float component2() {
            return this.power;
        }

        @NotNull
        public final PendingBlast copy(@NotNull Vec3d center, float power) {
            Intrinsics.checkNotNullParameter((Object)center, (String)"center");
            return new PendingBlast(center, power);
        }

        public static /* synthetic */ PendingBlast copy$default(PendingBlast pendingBlast, Vec3d vec3d2, float f, int n, Object object) {
            if ((n & 1) != 0) {
                vec3d2 = pendingBlast.center;
            }
            if ((n & 2) != 0) {
                f = pendingBlast.power;
            }
            return pendingBlast.copy(vec3d2, f);
        }

        @NotNull
        public String toString() {
            return "PendingBlast(center=" + this.center + ", power=" + this.power + ")";
        }

        public int hashCode() {
            int result = this.center.hashCode();
            result = result * 31 + Float.hashCode(this.power);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PendingBlast)) {
                return false;
            }
            PendingBlast pendingBlast = (PendingBlast)other;
            if (!Intrinsics.areEqual((Object)this.center, (Object)pendingBlast.center)) {
                return false;
            }
            return Float.compare(this.power, pendingBlast.power) == 0;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ$\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0016\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u000b\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ExplosionWave$RecentBlast;", "", "Lnet/minecraft/Vec3d;", "pos", "", "time", "<init>", "(Lnet/minecraft/Vec3d;J)V", "component1", "()Lnet/minecraft/Vec3d;", "component2", "()J", "copy", "(Lnet/minecraft/Vec3d;J)Lrtx/kimiko/api/modules/impl/Visuals/ExplosionWave$RecentBlast;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/Vec3d;", "getPos", "J", "getTime", "rtx.kimiko:kimiko"})
    private static final class RecentBlast {
        @NotNull
        private final Vec3d pos;
        private final long time;

        public RecentBlast(@NotNull Vec3d pos, long time) {
            Intrinsics.checkNotNullParameter((Object)pos, (String)"pos");
            this.pos = pos;
            this.time = time;
        }

        @NotNull
        public final Vec3d getPos() {
            return this.pos;
        }

        public final long getTime() {
            return this.time;
        }

        @NotNull
        public final Vec3d component1() {
            return this.pos;
        }

        public final long component2() {
            return this.time;
        }

        @NotNull
        public final RecentBlast copy(@NotNull Vec3d pos, long time) {
            Intrinsics.checkNotNullParameter((Object)pos, (String)"pos");
            return new RecentBlast(pos, time);
        }

        public static /* synthetic */ RecentBlast copy$default(RecentBlast recentBlast, Vec3d vec3d2, long l, int n, Object object) {
            if ((n & 1) != 0) {
                vec3d2 = recentBlast.pos;
            }
            if ((n & 2) != 0) {
                l = recentBlast.time;
            }
            return recentBlast.copy(vec3d2, l);
        }

        @NotNull
        public String toString() {
            return "RecentBlast(pos=" + this.pos + ", time=" + this.time + ")";
        }

        public int hashCode() {
            int result = this.pos.hashCode();
            result = result * 31 + Long.hashCode(this.time);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RecentBlast)) {
                return false;
            }
            RecentBlast recentBlast = (RecentBlast)other;
            if (!Intrinsics.areEqual((Object)this.pos, (Object)recentBlast.pos)) {
                return false;
            }
            return this.time == recentBlast.time;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\t\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004\u00a2\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u000f\u001a\u0004\b\u0012\u0010\u0011R\u0017\u0010\u0014\u001a\u00020\u00138\u0006\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ExplosionWave$Wave;", "", "Lnet/minecraft/Vec3d;", "pos", "", "power", "falloff", "<init>", "(Lnet/minecraft/Vec3d;FF)V", "life", "progress", "(F)F", "Lnet/minecraft/Vec3d;", "getPos", "()Lnet/minecraft/Vec3d;", "F", "getPower", "()F", "getFalloff", "", "time", "J", "getTime", "()J", "rtx.kimiko:kimiko"})
    private static final class Wave {
        @NotNull
        private final Vec3d pos;
        private final float power;
        private final float falloff;
        private final long time;

        public Wave(@NotNull Vec3d pos, float power, float falloff) {
            Intrinsics.checkNotNullParameter((Object)pos, (String)"pos");
            this.pos = pos;
            this.power = power;
            this.falloff = falloff;
            this.time = System.currentTimeMillis();
        }

        @NotNull
        public final Vec3d getPos() {
            return this.pos;
        }

        public final float getPower() {
            return this.power;
        }

        public final float getFalloff() {
            return this.falloff;
        }

        public final long getTime() {
            return this.time;
        }

        public final float progress(float life) {
            return (float)(System.currentTimeMillis() - this.time) / life;
        }
    }
}

