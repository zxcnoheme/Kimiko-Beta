/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Position
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.util.math.ChunkSectionPos
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.client.option.Perspective
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.awt.Color;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.network.PacketEvent;
import rtx.kimiko.api.events.impl.player.TotemPopEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.entity.death.LocalDeathWatcher;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.modules.post.wasted.WastedState;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.sounds.SoundManager;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"wasted"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00ac\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 U2\u00020\u0001:\u0001UB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0007\u00a2\u0006\u0004\b\n\u0010\tJ\r\u0010\u000b\u001a\u00020\u0007\u00a2\u0006\u0004\b\u000b\u0010\tJ\r\u0010\f\u001a\u00020\u0007\u00a2\u0006\u0004\b\f\u0010\tJ\r\u0010\r\u001a\u00020\u0007\u00a2\u0006\u0004\b\r\u0010\tJ\r\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0003J\u001b\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0003b\u0002\b\u0015\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0018H\u0003b\u0002\b\u0015\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u001b\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u001bH\u0003b\u0002\b\u0015\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u001eH\u0003b\u0002\b\u0015\u00a2\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b!\u0010\u0003J\u000f\u0010\"\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\"\u0010\u0006J\u000f\u0010#\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b#\u0010\u0003J)\u0010*\u001a\u00020\u00042\b\u0010%\u001a\u0004\u0018\u00010$H\u0003b\u000e\b&\u0012\n\b'\u0012\u0006\b\n0(8)\u00a2\u0006\u0004\b*\u0010+J\u001f\u0010-\u001a\u00020\u0011H\u0015b\u000e\b&\u0012\n\b'\u0012\u0006\b\n0(8,\u00a2\u0006\u0004\b-\u0010\u0003J\u001f\u0010.\u001a\u00020\u0011H\u0015b\u000e\b&\u0012\n\b'\u0012\u0006\b\n0(8,\u00a2\u0006\u0004\b.\u0010\u0003J'\u00102\u001a\u00020\u00112\u0006\u00100\u001a\u00020/H\u0007b\u000e\b&\u0012\n\b'\u0012\u0006\b\n0(81\u00a2\u0006\u0004\b2\u00103R\u0014\u00105\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00106R\u0014\u00108\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010;\u001a\u00020:8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b;\u0010<R\u0014\u0010>\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010@\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b@\u00106R\u0014\u0010B\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0014\u0010D\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u00109R\u0014\u0010E\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u00109R\u0014\u0010F\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bF\u0010CR\u0014\u0010G\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u00106R\u0014\u0010H\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u0010CR\u0014\u0010I\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u00109R\u0018\u0010K\u001a\u0004\u0018\u00010J8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010LR\u0016\u0010M\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bM\u0010NR\u0016\u0010P\u001a\u00020O8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bP\u0010QR\u0016\u0010R\u001a\u00020O8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bR\u0010QR\u0018\u0010S\u001a\u0004\u0018\u00010$8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bS\u0010T\u00ca\u0001\u0010\bV\u0012\f\b'\u0012\b\b\fJ\u0004\b\b(W\u00a8\u0006X"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/WastedDeath;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "orbitEnabled", "()Z", "", "orbitSpeedValue", "()F", "orbitDistanceValue", "orbitYaw", "orbitPitch", "orbitDistanceNow", "Lnet/minecraft/Vec3d;", "orbitPosition", "()Lnet/minecraft/Vec3d;", "", "updateDetach", "Lrtx/kimiko/api/events/impl/render/HudRenderEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onHudRender", "(Lrtx/kimiko/api/events/impl/render/HudRenderEvent;)V", "Lrtx/kimiko/api/events/impl/network/PacketEvent;", "onPacket", "(Lrtx/kimiko/api/events/impl/network/PacketEvent;)V", "Lrtx/kimiko/api/events/impl/player/TotemPopEvent;", "onTotemPop", "(Lrtx/kimiko/api/events/impl/player/TotemPopEvent;)V", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "finishRespawn", "awaitingRespawn", "restoreCamera", "Lnet/minecraft/Screen;", "screen", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "MAX", "beginFrom", "(Lnet/minecraft/Screen;)Z", "CROWN", "onEnable", "onDisable", "Lnet/minecraft/DrawContext;", "graphics", "STD", "renderOverlay", "(Lnet/minecraft/DrawContext;)V", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "generalSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "duration", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "label", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "textColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "cameraSeparator", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "orbit", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "orbitSpeed", "orbitDistance", "autoRespawn", "soundSeparator", "playSound", "soundVolume", "Lnet/minecraft/Perspective;", "savedCameraType", "Lnet/minecraft/Perspective;", "respawnPending", "Z", "", "lastFinishedAtMs", "J", "respawnWaitUntilMs", "pendingDeathScreen", "Lnet/minecraft/Screen;", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "wasted", "rtx.kimiko:kimiko"})
public final class WastedDeath
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting generalSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Эффект"));
    @NotNull
    private final SliderSetting duration = (SliderSetting)this.register((Setting)new SliderSetting("Длительность", "Сколько секунд держится эффект смерти.").range(2.0f, 10.0f).increment(1.0f).setValue(6.0f));
    @NotNull
    private final ModeSetting label;
    @NotNull
    private final ColorSetting textColor;
    @NotNull
    private final SeparatorSetting cameraSeparator;
    @NotNull
    private final BooleanSetting orbit;
    @NotNull
    private final SliderSetting orbitSpeed;
    @NotNull
    private final SliderSetting orbitDistance;
    @NotNull
    private final BooleanSetting autoRespawn;
    @NotNull
    private final SeparatorSetting soundSeparator;
    @NotNull
    private final BooleanSetting playSound;
    @NotNull
    private final SliderSetting soundVolume;
    @Nullable
    private Perspective savedCameraType;
    private boolean respawnPending;
    private long lastFinishedAtMs;
    private long respawnWaitUntilMs;
    @Nullable
    private Screen pendingDeathScreen;
    private static final float TEXT_Y_LIFT = 5.0f;
    private static final double CORPSE_EYE_HEIGHT = 0.7;
    private static final double DETACH_DISTANCE_SQ = 9.0;
    private static final long RETRIGGER_GUARD_MS = 4000L;
    private static final long RESPAWN_WAIT_MS = 4000L;
    @JvmField
    @Nullable
    public static WastedDeath INSTANCE;

    public WastedDeath() {
        super("Wasted", "Кинематографичная смерть в стиле GTA V.", Category.VISUALS);
        String[] stringArray = new String[]{"WASTED", "ТЫ УМЕР", "Нет"};
        this.label = (ModeSetting)this.register((Setting)new ModeSetting("Надпись", "Что писать на экране.", "WASTED", stringArray));
        this.textColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет надписи", "Цвет текста на экране смерти.", new Color(178, 20, 24, 255)));
        this.cameraSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Камера"));
        this.orbit = (BooleanSetting)this.register((Setting)new BooleanSetting("Облёт камеры", "Камера кинематографично облетает тело.", true));
        this.orbitSpeed = (SliderSetting)this.register((Setting)new SliderSetting("Скорость облёта", "Градусов в секунду.").range(0.0f, 60.0f).increment(1.0f).setValue(14.0f).visible(() -> WastedDeath.orbitSpeed$lambda$0(this)));
        this.orbitDistance = (SliderSetting)this.register((Setting)new SliderSetting("Дальность", "Насколько далеко отлетает камера.").range(15.0f, 90.0f).increment(5.0f).setValue(45.0f).visible(() -> WastedDeath.orbitDistance$lambda$0(this)));
        this.autoRespawn = (BooleanSetting)this.register((Setting)new BooleanSetting("Возрождать после эффекта", "Сразу возрождает и убирает ванильное меню смерти.", true));
        this.soundSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Звук"));
        this.playSound = (BooleanSetting)this.register((Setting)new BooleanSetting("Звук смерти", "Проигрывать звук из GTA V.", true));
        this.soundVolume = (SliderSetting)this.register((Setting)new SliderSetting("Громкость", "Громкость звука смерти.").range(10.0f, 100.0f).increment(5.0f).setValue(70.0f).visible(() -> WastedDeath.soundVolume$lambda$0(this)));
        INSTANCE = this;
    }

    public final boolean orbitEnabled() {
        return this.orbit.getValue();
    }

    public final float orbitSpeedValue() {
        return this.orbitSpeed.getFloat();
    }

    public final float orbitDistanceValue() {
        return this.orbitDistance.getFloat() / 10.0f;
    }

    public final float orbitYaw() {
        return WastedState.startYaw() + (float)WastedState.elapsedMs() / 1000.0f * this.orbitSpeed.getFloat();
    }

    public final float orbitPitch() {
        return 22.0f + 26.0f * WastedDeath.Companion.ease();
    }

    public final float orbitDistanceNow() {
        return 2.0f + (this.orbitDistanceValue() - 2.0f) * WastedDeath.Companion.ease();
    }

    @NotNull
    public final Vec3d orbitPosition() {
        Vec3d anchor = WastedState.anchor();
        double yaw = Math.toRadians(this.orbitYaw());
        double pitch = Math.toRadians(this.orbitPitch());
        double distance = this.orbitDistanceNow();
        double cosPitch = Math.cos(pitch);
        double dirX = -Math.sin(yaw) * cosPitch;
        double dirY = -Math.sin(pitch);
        double dirZ = Math.cos(yaw) * cosPitch;
        return new Vec3d(anchor.x - dirX * distance, anchor.y + 0.7 - dirY * distance, anchor.z - dirZ * distance);
    }

    private final void updateDetach() {
        ClientPlayerEntity player = this.mc.player;
        ClientWorld level = this.mc.world;
        if (player == null || level == null || !this.orbit.getValue()) {
            WastedState.setDetached(false);
            return;
        }
        Vec3d anchor = WastedState.anchor();
        if (!player.isAlive() || anchor == Vec3d.ZERO) {
            WastedState.setDetached(false);
            return;
        }
        boolean moved = player.getEntityPos().squaredDistanceTo(anchor) > 9.0;
        BlockPos blockPos2 = BlockPos.ofFloored((Position)((Position)anchor));
        Intrinsics.checkNotNullExpressionValue((Object)blockPos2, (String)"containing(...)");
        BlockPos pos = blockPos2;
        boolean loaded = level.getChunkManager().isChunkLoaded(ChunkSectionPos.getSectionCoord((int)pos.getX()), ChunkSectionPos.getSectionCoord((int)pos.getZ()));
        WastedState.setDetached(moved && loaded);
    }

    @EventHandler
    private final void onHudRender(HudRenderEvent event) {
        if (Companion.isRunning()) {
            this.renderOverlay(event.getGraphics());
        }
    }

    @EventHandler
    private final void onPacket(PacketEvent event) {
        if (event.isReceive()) {
            LocalDeathWatcher.onPacket(event.getPacket());
        }
    }

    @EventHandler
    private final void onTotemPop(TotemPopEvent event) {
        LocalDeathWatcher.notifyTotem((Entity)event.getEntity());
    }

    @EventHandler
    private final void onTick(TickEvent event) {
        if (!event.isPre()) {
            return;
        }
        if (LocalDeathWatcher.poll()) {
            this.beginFrom(null);
        }
        if (WastedState.isActive()) {
            if (this.mc.player == null || this.mc.world == null) {
                WastedState.stop();
            } else {
                this.updateDetach();
            }
        }
        if (!WastedState.isActive()) {
            this.restoreCamera();
            this.finishRespawn();
        }
    }

    private final void finishRespawn() {
        boolean hardcore;
        if (!this.respawnPending) {
            return;
        }
        this.respawnPending = false;
        this.lastFinishedAtMs = System.currentTimeMillis();
        LocalDeathWatcher.suppress(4000L);
        Screen deathScreen = this.pendingDeathScreen;
        this.pendingDeathScreen = null;
        ClientPlayerEntity player = this.mc.player;
        if (player == null || player.isAlive()) {
            return;
        }
        ClientWorld clientWorld3 = this.mc.world;
        hardcore = clientWorld3 != null && clientWorld3.getLevelProperties() != null && clientWorld3.getLevelProperties().isHardcore();
        if (this.autoRespawn.getValue() && !hardcore) {
            this.respawnWaitUntilMs = System.currentTimeMillis() + 4000L;
            player.requestRespawn();
        } else if (deathScreen != null) {
            this.mc.setScreen(deathScreen);
        }
    }

    private final boolean awaitingRespawn() {
        if (this.respawnWaitUntilMs == 0L) {
            return false;
        }
        ClientPlayerEntity player = this.mc.player;
        if (player == null || player.isAlive() || System.currentTimeMillis() > this.respawnWaitUntilMs) {
            this.respawnWaitUntilMs = 0L;
            return false;
        }
        return true;
    }

    private final void restoreCamera() {
        Perspective cam = this.savedCameraType;
        if (cam != null && this.mc.options != null) {
            this.mc.options.setPerspective(cam);
        }
        this.savedCameraType = null;
    }

    @Protect(value=Level.MAX)
    private final boolean beginFrom(Screen screen) {
        ClientPlayerEntity player = this.mc.player;
        if (!this.isEnabled() || player == null) {
            return false;
        }
        if (WastedState.isActive()) {
            if (screen != null && this.pendingDeathScreen == null) {
                this.pendingDeathScreen = screen;
            }
            return true;
        }
        if (System.currentTimeMillis() - this.lastFinishedAtMs < 4000L) {
            if (screen != null && this.respawnPending && this.pendingDeathScreen == null) {
                this.pendingDeathScreen = screen;
            }
            return false;
        }
        this.pendingDeathScreen = screen;
        LocalDeathWatcher.markHandled();
        WastedState.begin(LocalDeathWatcher.deathPosition(), LocalDeathWatcher.deathYaw(), (long)(this.duration.getFloat() * (float)1000L));
        this.respawnPending = true;
        this.respawnWaitUntilMs = 0L;
        if (this.orbit.getValue()) {
            this.savedCameraType = this.mc.options.getPerspective();
            this.mc.options.setPerspective(Perspective.THIRD_PERSON_BACK);
        }
        if (this.playSound.getValue()) {
            SoundManager.playSoundDirect(SoundManager.WASTED, this.soundVolume.getFloat() / 100.0f, 1.0f);
        }
        return true;
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onEnable() {
        LocalDeathWatcher.reset();
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onDisable() {
        WastedState.stop();
        this.restoreCamera();
        this.respawnPending = false;
        this.respawnWaitUntilMs = 0L;
        this.pendingDeathScreen = null;
        LocalDeathWatcher.reset();
    }

    @Protect(value=Level.STD)
    public final void renderOverlay(@NotNull DrawContext graphics) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        float alpha = WastedState.textAlpha();
        if (alpha <= 0.01f || this.label.is("Нет")) {
            return;
        }
        String text = this.label.is("ТЫ УМЕР") ? I18n.tr("ТЫ УМЕР") : "WASTED";
        float screenW = rtx.kimiko.api.drags.Position.Companion.screenWidth();
        float screenH = rtx.kimiko.api.drags.Position.Companion.screenHeight();
        Render2D.beginFrame(graphics);
        float pop = Math.min(1.0f, alpha * 1.15f);
        float size = 34.0f * (1.06f - 0.06f * pop);
        float textWidth = Fonts.BLACK.width(text, size);
        float centerY = screenH * 0.5f;
        float bandHeight = size * 1.5f;
        float bandY = centerY - bandHeight * 0.5f;
        Render2D.rect(0.0f, bandY, screenW, bandHeight, 0.0f, WastedDeath.Companion.color(24, 24, 24, 150, alpha));
        Render2D.rect(0.0f, bandY - 1.2f, screenW, 1.2f, 0.0f, WastedDeath.Companion.color(0, 0, 0, 90, alpha));
        Render2D.rect(0.0f, bandY + bandHeight, screenW, 1.2f, 0.0f, WastedDeath.Companion.color(0, 0, 0, 90, alpha));
        int base = this.textColor.getColorOpaque();
        int red = base >> 16 & 0xFF;
        int green = base >> 8 & 0xFF;
        int blue = base & 0xFF;
        float textY = centerY - size * 0.5f - 5.0f;
        Fonts.BLACK.draw(text, (screenW - textWidth) * 0.5f + 1.5f, textY + 1.5f, size, WastedDeath.Companion.color(0, 0, 0, 120, alpha));
        Fonts.BLACK.draw(text, (screenW - textWidth) * 0.5f, textY, size, WastedDeath.Companion.color(red, green, blue, 255, alpha));
        Render2D.flush();
    }

    private static final Boolean orbitSpeed$lambda$0(WastedDeath this$0) {
        return this$0.orbit.getValue();
    }

    private static final Boolean orbitDistance$lambda$0(WastedDeath this$0) {
        return this$0.orbit.getValue();
    }

    private static final Boolean soundVolume$lambda$0(WastedDeath this$0) {
        return this$0.playSound.getValue();
    }

    @JvmStatic
    @Nullable
    public static final WastedDeath getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final boolean isRunning() {
        return Companion.isRunning();
    }

    @JvmStatic
    public static final boolean interceptDeathScreen(@NotNull Screen screen) {
        return Companion.interceptDeathScreen(screen);
    }

    @JvmStatic
    public static final boolean blocksDeathScreen() {
        return Companion.blocksDeathScreen();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000f\u0010\nJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J7\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001e\u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010\u001fR\u0014\u0010\"\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010#R\u001d\u0010&\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b%\u00a2\u0006\u0006\n\u0004\b&\u0010'\u00a8\u0006("}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/WastedDeath.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/WastedDeath;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/WastedDeath;", "", "isRunning", "()Z", "Lnet/minecraft/Screen;", "screen", "interceptDeathScreen", "(Lnet/minecraft/Screen;)Z", "blocksDeathScreen", "", "ease", "()F", "", "r", "g", "b", "a", "mult", "color", "(IIIIF)I", "TEXT_Y_LIFT", "F", "", "CORPSE_EYE_HEIGHT", "D", "DETACH_DISTANCE_SQ", "", "RETRIGGER_GUARD_MS", "J", "RESPAWN_WAIT_MS", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/WastedDeath;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final WastedDeath getInstance() {
            WastedDeath module = ModuleManager.Companion.get().get(WastedDeath.class);
            WastedDeath wastedDeath = module;
            if (wastedDeath == null) {
                wastedDeath = INSTANCE;
            }
            return wastedDeath;
        }

        @JvmStatic
        public final boolean isRunning() {
            WastedDeath module = this.getInstance();
            return module != null && module.isEnabled() && WastedState.isActive();
        }

        @JvmStatic
        public final boolean interceptDeathScreen(@NotNull Screen screen) {
            Intrinsics.checkNotNullParameter((Object)screen, (String)"screen");
            WastedDeath wastedDeath = this.getInstance();
            if (wastedDeath == null) {
                return false;
            }
            WastedDeath module = wastedDeath;
            if (!module.isEnabled()) {
                return false;
            }
            LocalDeathWatcher.notifyDeathScreen();
            return module.beginFrom(screen);
        }

        @JvmStatic
        public final boolean blocksDeathScreen() {
            WastedDeath wastedDeath = this.getInstance();
            if (wastedDeath == null) {
                return false;
            }
            WastedDeath module = wastedDeath;
            if (!module.isEnabled()) {
                return false;
            }
            return WastedState.isActive() || module.awaitingRespawn();
        }

        private final float ease() {
            float t = RangesKt.coerceIn((float)((float)WastedState.elapsedMs() / 2200.0f), (float)0.0f, (float)1.0f);
            return t * t * (3.0f - 2.0f * t);
        }

        private final int color(int r, int g, int b, int a, float mult) {
            int fa = RangesKt.coerceIn((int)Math.round((float)a * mult), (int)0, (int)255);
            return fa <= 0 ? 0 : new Color(r, g, b, fa).getRGB();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

