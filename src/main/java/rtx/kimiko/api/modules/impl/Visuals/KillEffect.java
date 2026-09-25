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
 *  net.minecraft.entity.damage.DamageSource
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.mob.MobEntity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.passive.AnimalEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.util.Identifier
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Vector4f
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.player.AttackEntityEvent;
import rtx.kimiko.api.events.impl.render.DrawEvent;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.impl.Visuals.killeffect.KillEffectDeathMemoryTracker;
import rtx.kimiko.api.modules.impl.Visuals.killeffect.KillEffectDissolveEffect;
import rtx.kimiko.api.modules.impl.Visuals.killeffect.KillEffectEasing;
import rtx.kimiko.api.modules.impl.Visuals.killeffect.KillEffectParticleSystem;
import rtx.kimiko.api.modules.impl.Visuals.killeffect.KillEffectScanRenderer;
import rtx.kimiko.api.modules.impl.Visuals.killeffect.KillEffectSoundQueue;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.MultiSelectSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.entity.death.EntityDeathWatcher;
import rtx.kimiko.utils.render.modules.post.killdistortion.KillDistortionRenderer;
import rtx.kimiko.utils.render.others.RenderCompatibility;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.sounds.SoundManager;
import rtx.kimiko.utils.storage.friend.FriendUtils;
import rtx.kimiko.utils.time.StopWatch;

@Feature(value={"killeffect"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00fc\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u009e\u00012\u00020\u00012\u00020\u0002:\u0002\u009e\u0001B\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u000b\u0010\u0007J\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010J#\u0010\u0013\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0010J!\u0010\u0016\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0018H\u0007b\u0002\b\u001a\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u001d\u0010\u0004J\u001b\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001eH\u0007b\u0002\b\u001a\u00a2\u0006\u0004\b\u001f\u0010 J\u001b\u0010\"\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020!H\u0007b\u0002\b\u001a\u00a2\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\u000eH\u0014\u00a2\u0006\u0004\b$\u0010\u0004J\u001b\u0010&\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020%H\u0007b\u0002\b\u001a\u00a2\u0006\u0004\b&\u0010'J\u0017\u0010(\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020%H\u0002\u00a2\u0006\u0004\b(\u0010'J\u0017\u0010)\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020!H\u0002\u00a2\u0006\u0004\b)\u0010#J\u0017\u0010+\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b+\u0010\u0010J\u000f\u0010,\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b,\u0010\u0007J\u000f\u0010.\u001a\u00020-H\u0002\u00a2\u0006\u0004\b.\u0010/J\u000f\u00100\u001a\u00020-H\u0002\u00a2\u0006\u0004\b0\u0010/J\u000f\u00101\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b1\u0010\u0004J\u0017\u00103\u001a\u00020\u000e2\u0006\u00102\u001a\u00020-H\u0002\u00a2\u0006\u0004\b3\u00104J\u0019\u00105\u001a\u00020\u00052\b\u0010*\u001a\u0004\u0018\u00010\fH\u0002\u00a2\u0006\u0004\b5\u00106J\u000f\u00108\u001a\u000207H\u0002\u00a2\u0006\u0004\b8\u00109J\u0017\u0010;\u001a\u0002072\u0006\u0010:\u001a\u000207H\u0002\u00a2\u0006\u0004\b;\u0010<J\u001f\u0010;\u001a\u0002072\u0006\u0010:\u001a\u0002072\u0006\u0010=\u001a\u00020-H\u0002\u00a2\u0006\u0004\b;\u0010>J\u000f\u0010?\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b?\u0010\u0004J\u000f\u0010@\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b@\u0010\u0004J\u000f\u0010B\u001a\u00020AH\u0002\u00a2\u0006\u0004\bB\u0010CJ\u0017\u0010F\u001a\u00020D2\u0006\u0010E\u001a\u00020DH\u0002\u00a2\u0006\u0004\bF\u0010GJ\u000f\u0010H\u001a\u00020DH\u0002\u00a2\u0006\u0004\bH\u0010IJ\u000f\u0010K\u001a\u00020JH\u0002\u00a2\u0006\u0004\bK\u0010LJ\u000f\u0010=\u001a\u00020-H\u0002\u00a2\u0006\u0004\b=\u0010/J\u000f\u0010M\u001a\u00020DH\u0002\u00a2\u0006\u0004\bM\u0010IJ\u000f\u0010N\u001a\u00020DH\u0002\u00a2\u0006\u0004\bN\u0010IJ\u000f\u0010O\u001a\u00020DH\u0002\u00a2\u0006\u0004\bO\u0010IJ\u000f\u0010P\u001a\u00020DH\u0002\u00a2\u0006\u0004\bP\u0010IJ\u000f\u0010Q\u001a\u00020DH\u0002\u00a2\u0006\u0004\bQ\u0010IJ\u000f\u0010R\u001a\u00020DH\u0002\u00a2\u0006\u0004\bR\u0010IJ\u000f\u0010S\u001a\u00020DH\u0002\u00a2\u0006\u0004\bS\u0010IJ\u000f\u0010T\u001a\u00020DH\u0002\u00a2\u0006\u0004\bT\u0010IJ'\u0010X\u001a\u00020-2\u0006\u0010U\u001a\u0002072\u0006\u0010V\u001a\u0002072\u0006\u0010W\u001a\u000207H\u0002\u00a2\u0006\u0004\bX\u0010YR\u0014\u0010[\u001a\u00020Z8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b[\u0010\\R\u0014\u0010^\u001a\u00020]8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b^\u0010_R\u0014\u0010a\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\ba\u0010bR \u0010d\u001a\u000e\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u0002070c8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bd\u0010eR \u0010f\u001a\u000e\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u0002070c8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bf\u0010eR\u0014\u0010h\u001a\u00020g8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bh\u0010iR\u0014\u0010k\u001a\u00020j8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bk\u0010lR\u0014\u0010n\u001a\u00020m8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bn\u0010oR\u0014\u0010q\u001a\u00020p8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bq\u0010rR\u0014\u0010s\u001a\u00020m8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bs\u0010oR\u0014\u0010u\u001a\u00020t8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bu\u0010vR\u0014\u0010w\u001a\u00020t8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bw\u0010vR\u0014\u0010x\u001a\u00020t8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bx\u0010vR\u0014\u0010y\u001a\u00020t8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\by\u0010vR\u0014\u0010z\u001a\u00020t8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bz\u0010vR\u0014\u0010{\u001a\u00020m8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b{\u0010oR\u0014\u0010}\u001a\u00020|8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b}\u0010~R\u0014\u0010\u007f\u001a\u00020|8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u007f\u0010~R\u0016\u0010\u0080\u0001\u001a\u00020|8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0080\u0001\u0010~R\u0016\u0010\u0081\u0001\u001a\u00020|8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0081\u0001\u0010~R\u0016\u0010\u0082\u0001\u001a\u00020|8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0082\u0001\u0010~R\u0016\u0010\u0083\u0001\u001a\u00020|8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0083\u0001\u0010~R\u0016\u0010\u0084\u0001\u001a\u00020t8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0084\u0001\u0010vR\u0016\u0010\u0085\u0001\u001a\u00020m8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0085\u0001\u0010oR\u0016\u0010\u0086\u0001\u001a\u00020p8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0086\u0001\u0010rR\u0016\u0010\u0087\u0001\u001a\u00020t8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0087\u0001\u0010vR\u0018\u0010\u0089\u0001\u001a\u00030\u0088\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0089\u0001\u0010\u008a\u0001R\u0018\u0010\u008b\u0001\u001a\u00030\u0088\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u008b\u0001\u0010\u008a\u0001R\u0016\u0010\u008c\u0001\u001a\u00020m8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u008c\u0001\u0010oR\u0018\u0010\u008e\u0001\u001a\u00030\u008d\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u008e\u0001\u0010\u008f\u0001R\u0019\u0010\u0090\u0001\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0090\u0001\u0010\u0091\u0001R\u001c\u0010\u0093\u0001\u001a\u0005\u0018\u00010\u0092\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0093\u0001\u0010\u0094\u0001R\u0019\u0010\u0095\u0001\u001a\u00020-8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0095\u0001\u0010\u0096\u0001R\u0016\u0010\u0097\u0001\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0097\u0001\u0010bR\u0019\u0010\u0098\u0001\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0098\u0001\u0010\u0091\u0001R\u0016\u0010\u0099\u0001\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0099\u0001\u0010bR\u0019\u0010\u009a\u0001\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009a\u0001\u0010\u0091\u0001R\u001a\u0010\u009c\u0001\u001a\u00030\u009b\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u009c\u0001\u0010\u009d\u0001\u00ca\u0001\u0013\b\u009f\u0001\u0012\u000e\b\u00a0\u0001\u0012\t\b\fJ\u0005\b\b(\u00a1\u0001\u00a8\u0006\u00a2\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/KillEffect;", "Lrtx/kimiko/api/modules/Module;", "Lrtx/kimiko/utils/entity/death/EntityDeathWatcher$Listener;", "<init>", "()V", "", "deathWatchActive", "()Z", "", "deathWatchDistance", "()D", "deathWatchAggressive", "Lnet/minecraft/LivingEntity;", "victim", "", "onEntityDeath", "(Lnet/minecraft/LivingEntity;)V", "Lnet/minecraft/DamageSource;", "source", "handleDeath", "(Lnet/minecraft/LivingEntity;Lnet/minecraft/DamageSource;)V", "handleRememberedDeath", "localPlayerGotKill", "(Lnet/minecraft/LivingEntity;Lnet/minecraft/DamageSource;)Z", "Lrtx/kimiko/api/events/impl/player/AttackEntityEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onAttack", "(Lrtx/kimiko/api/events/impl/player/AttackEntityEvent;)V", "pruneAttackMemory", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "Lrtx/kimiko/api/events/impl/render/DrawEvent;", "onDraw", "(Lrtx/kimiko/api/events/impl/render/DrawEvent;)V", "onDisable", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "renderDistortion", "drawVignette", "entity", "onTrackedKill", "isDissolveMode", "", "cameraProgress", "()F", "cameraEnvelope", "scheduleCharmSounds", "effectSpeedMultiplier", "restartEffect", "(F)V", "matchesEffectTarget", "(Lnet/minecraft/LivingEntity;)Z", "", "effectDurationMs", "()J", "durationMs", "scaleDuration", "(J)J", "speedMultiplier", "(JF)J", "resetState", "resetVisuals", "Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDissolveEffect$Settings;", "dissolveSettings", "()Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDissolveEffect$Settings;", "", "seed", "dissolveColor", "(I)I", "accent", "()I", "", "colorPair", "()[I", "c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "totalMs", "attackMs", "releaseMs", "transientEnvelope", "(JJJ)F", "Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectSoundQueue;", "soundQueue", "Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectSoundQueue;", "Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectParticleSystem;", "particleSystem", "Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectParticleSystem;", "Lrtx/kimiko/utils/time/StopWatch;", "effectTimer", "Lrtx/kimiko/utils/time/StopWatch;", "", "recentlyAttacked", "Ljava/util/Map;", "recentlyTriggered", "Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDeathMemoryTracker;", "deathMemoryTracker", "Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDeathMemoryTracker;", "Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDissolveEffect;", "dissolveEffect", "Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDissolveEffect;", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "modeSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "effectMode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "scanSeparator", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "spikes", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "changeSaturation", "zoomEffect", "cameraShake", "distortion", "dissolveSeparator", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "dissolveParticles", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "dissolveParticleSize", "dissolveHold", "dissolveFade", "dissolveRise", "dissolveChaos", "dissolveThroughWalls", "colorsSeparator", "colorMode", "useSecondColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customSecondColor", "targetsSeparator", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "effectTargets", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "animate", "Z", "", "lastEffectMode", "Ljava/lang/String;", "activeEffectSpeedMultiplier", "F", "cameraTimer", "cameraAnimate", "distortionTimer", "distortionAnimate", "Lnet/minecraft/Vec3d;", "distortionCenter", "Lnet/minecraft/Vec3d;", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "killeffect", "rtx.kimiko:kimiko"})
public final class KillEffect
extends Module
implements EntityDeathWatcher.Listener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final KillEffectSoundQueue soundQueue = new KillEffectSoundQueue();
    @NotNull
    private final KillEffectParticleSystem particleSystem = new KillEffectParticleSystem();
    @NotNull
    private final StopWatch effectTimer = new StopWatch();
    @NotNull
    private final Map<Integer, Long> recentlyAttacked = new HashMap();
    @NotNull
    private final Map<Integer, Long> recentlyTriggered = new HashMap();
    @NotNull
    private final KillEffectDeathMemoryTracker deathMemoryTracker = new KillEffectDeathMemoryTracker(2500L, arg_0 -> KillEffect.deathMemoryTracker$lambda$0(this, arg_0));
    @NotNull
    private final KillEffectDissolveEffect dissolveEffect = new KillEffectDissolveEffect();
    @NotNull
    private final SeparatorSetting modeSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Основное"));
    @NotNull
    private final ModeSetting effectMode;
    @NotNull
    private final SeparatorSetting scanSeparator;
    @NotNull
    private final BooleanSetting spikes;
    @NotNull
    private final BooleanSetting changeSaturation;
    @NotNull
    private final BooleanSetting zoomEffect;
    @NotNull
    private final BooleanSetting cameraShake;
    @NotNull
    private final BooleanSetting distortion;
    @NotNull
    private final SeparatorSetting dissolveSeparator;
    @NotNull
    private final SliderSetting dissolveParticles;
    @NotNull
    private final SliderSetting dissolveParticleSize;
    @NotNull
    private final SliderSetting dissolveHold;
    @NotNull
    private final SliderSetting dissolveFade;
    @NotNull
    private final SliderSetting dissolveRise;
    @NotNull
    private final SliderSetting dissolveChaos;
    @NotNull
    private final BooleanSetting dissolveThroughWalls;
    @NotNull
    private final SeparatorSetting colorsSeparator;
    @NotNull
    private final ModeSetting colorMode;
    @NotNull
    private final BooleanSetting useSecondColor;
    @NotNull
    private final ColorSetting customColor;
    @NotNull
    private final ColorSetting customSecondColor;
    @NotNull
    private final SeparatorSetting targetsSeparator;
    @NotNull
    private final MultiSelectSetting effectTargets;
    private boolean animate;
    @Nullable
    private String lastEffectMode;
    private float activeEffectSpeedMultiplier;
    @NotNull
    private final StopWatch cameraTimer;
    private boolean cameraAnimate;
    @NotNull
    private final StopWatch distortionTimer;
    private boolean distortionAnimate;
    @NotNull
    private Vec3d distortionCenter;
    private static final long BASE_EFFECT_DURATION_MS = 5200L;
    private static final long SPIKE_DURATION_MS = 600L;
    @NotNull
    private static final Identifier VIGNETTE_TEXTURE;
    @NotNull
    private static final String TARGET_PLAYERS = "Игроки";
    @NotNull
    private static final String TARGET_FRIENDS = "Друзья";
    @NotNull
    private static final String TARGET_MOBS = "Мобы";
    @NotNull
    private static final String TARGET_ANIMALS = "Животные";
    @NotNull
    private static final String EFFECT_SCAN = "Сканирование";
    @NotNull
    private static final String EFFECT_DISSOLVE = "Распад";
    @NotNull
    private static final String COLOR_RAINBOW = "Радуга";
    @NotNull
    private static final String COLOR_CLIENT = "Клиент";
    @NotNull
    private static final String COLOR_CUSTOM = "Свой";
    private static final int DARK_SECOND_COLOR;
    private static final long ATTACK_MEMORY_MS = 2500L;
    private static final long TRIGGER_MEMORY_MS = 5000L;
    private static final double DEATH_WATCH_DISTANCE = 64.0;
    private static final float ZOOM_STRENGTH = 15.0f;
    private static final float SHAKE_STRENGTH = 250.0f;
    private static final long CAMERA_EFFECT_MS = 650L;
    private static final long DISTORTION_MS = 900L;
    @JvmField
    @Nullable
    public static KillEffect INSTANCE;

    public KillEffect() {
        super("Kill Effect", "Эффект сканирования при убийстве с виньеткой, волной и звуками.", Category.VISUALS);
        String[] stringArray = new String[]{EFFECT_SCAN, EFFECT_DISSOLVE};
        this.effectMode = (ModeSetting)this.register((Setting)new ModeSetting("Режим убийства", "Основной эффект, который проигрывается при убийстве.", EFFECT_SCAN, stringArray));
        this.scanSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting(EFFECT_SCAN));
        this.spikes = (BooleanSetting)this.register((Setting)new BooleanSetting("Шипы", "Показывает накладку из шипов, врывающуюся от краёв экрана во время эффекта убийства.", true));
        this.changeSaturation = (BooleanSetting)this.register((Setting)new BooleanSetting("Менять насыщенность", "Временно снижает насыщенность мира во время эффекта убийства.", false));
        this.zoomEffect = (BooleanSetting)this.register((Setting)new BooleanSetting("Приближение", "Плавно приближает и возвращает камеру при убийстве.", false));
        this.cameraShake = (BooleanSetting)this.register((Setting)new BooleanSetting("Тряска камеры", "Добавляет короткую тряску камеры при убийстве.", false));
        this.distortion = (BooleanSetting)this.register((Setting)new BooleanSetting("Искажение", "Расширяющаяся стеклянная ударная волна, искажающая мир от места убийства.", false));
        this.dissolveSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting(EFFECT_DISSOLVE));
        this.dissolveParticles = (SliderSetting)this.register((Setting)new SliderSetting("Частицы", "Количество светящихся частиц, из которых собирается модель убитого.").range(80.0f, 650.0f).increment(10.0f).setValue(280.0f));
        this.dissolveParticleSize = (SliderSetting)this.register((Setting)new SliderSetting("Размер частиц", "Размер светящейся частицы.").range(0.045f, 0.22f).increment(0.005f).setValue(0.115f));
        this.dissolveHold = (SliderSetting)this.register((Setting)new SliderSetting("Задержка", "Сколько миллисекунд модель держится целой перед испарением.").range(0.0f, 500.0f).increment(10.0f).setValue(200.0f));
        this.dissolveFade = (SliderSetting)this.register((Setting)new SliderSetting("Испарение", "Длительность испарения и растворения, в миллисекундах.").range(250.0f, 2400.0f).increment(25.0f).setValue(1050.0f));
        this.dissolveRise = (SliderSetting)this.register((Setting)new SliderSetting("Высота подъёма", "Насколько высоко поднимаются частицы во время испарения.").range(0.35f, 4.0f).increment(0.05f).setValue(1.75f));
        this.dissolveChaos = (SliderSetting)this.register((Setting)new SliderSetting("Хаос", "Насколько сильно частицы разлетаются от захваченной модели.").range(0.0f, 1.75f).increment(0.05f).setValue(0.75f));
        this.dissolveThroughWalls = (BooleanSetting)this.register((Setting)new BooleanSetting("Сквозь стены", "Рисовать распад модели сквозь блоки.", false));
        this.colorsSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Цвета"));
        stringArray = new String[]{COLOR_RAINBOW, COLOR_CLIENT, COLOR_CUSTOM};
        this.colorMode = (ModeSetting)this.register((Setting)new ModeSetting("Режим цвета", "Режим цвета эффекта убийства.", COLOR_CUSTOM, stringArray));
        this.useSecondColor = (BooleanSetting)this.register((Setting)new BooleanSetting("Второй цвет", "Использовать второй свой цвет.", false));
        this.customColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет", "Основной цвет эффекта убийства.", new Color(-50116, true)));
        this.customSecondColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет 2", "Второй цвет эффекта убийства.", new Color(ColorEngine.lerpColor(-50116, DARK_SECOND_COLOR, 0.7f), true)));
        this.targetsSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Цели"));
        stringArray = new String[]{TARGET_PLAYERS, TARGET_FRIENDS, TARGET_MOBS, TARGET_ANIMALS};
        MultiSelectSetting multiSelectSetting = new MultiSelectSetting("Цели эффекта", "Выберите, какие сущности запускают эффект убийства.").value(stringArray);
        stringArray = new String[]{TARGET_PLAYERS, TARGET_FRIENDS, TARGET_MOBS, TARGET_ANIMALS};
        this.effectTargets = (MultiSelectSetting)this.register((Setting)multiSelectSetting.selected(stringArray));
        this.activeEffectSpeedMultiplier = 1.0f;
        this.cameraTimer = new StopWatch();
        this.distortionTimer = new StopWatch();
        Vec3d vec3d2 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
        this.distortionCenter = vec3d2;
        INSTANCE = this;
        EntityDeathWatcher.register(this);
        this.scanSeparator.visible(() -> KillEffect._init_$lambda$0(this));
        this.spikes.visible(() -> KillEffect._init_$lambda$1(this));
        this.changeSaturation.visible(() -> KillEffect._init_$lambda$2(this));
        this.zoomEffect.visible(() -> KillEffect._init_$lambda$3(this));
        this.cameraShake.visible(() -> KillEffect._init_$lambda$4(this));
        this.distortion.visible(() -> KillEffect._init_$lambda$5(this));
        this.dissolveSeparator.visible(() -> KillEffect._init_$lambda$6(this));
        this.dissolveParticles.visible(() -> KillEffect._init_$lambda$7(this));
        this.dissolveParticleSize.visible(() -> KillEffect._init_$lambda$8(this));
        this.dissolveHold.visible(() -> KillEffect._init_$lambda$9(this));
        this.dissolveFade.visible(() -> KillEffect._init_$lambda$10(this));
        this.dissolveRise.visible(() -> KillEffect._init_$lambda$11(this));
        this.dissolveChaos.visible(() -> KillEffect._init_$lambda$12(this));
        this.dissolveThroughWalls.visible(() -> KillEffect._init_$lambda$13(this));
        this.useSecondColor.visibleWhen(() -> KillEffect._init_$lambda$14(this));
        this.customColor.visibleWhen(() -> KillEffect._init_$lambda$15(this));
        this.customSecondColor.visibleWhen(() -> KillEffect._init_$lambda$16(this));
    }

    @Override
    public boolean deathWatchActive() {
        return this.isEnabled();
    }

    @Override
    public double deathWatchDistance() {
        return 64.0;
    }

    @Override
    public boolean deathWatchAggressive() {
        return true;
    }

    @Override
    public void onEntityDeath(@NotNull LivingEntity victim) {
        Intrinsics.checkNotNullParameter((Object)victim, (String)"victim");
        this.handleDeath(victim, null);
    }

    private final void handleDeath(LivingEntity victim, DamageSource source) {
        ClientPlayerEntity player = this.mc.player;
        ClientWorld level = this.mc.world;
        if (player == null || level == null || victim == null || Intrinsics.areEqual((Object)victim, (Object)player)) {
            return;
        }
        if (!this.localPlayerGotKill(victim, source)) {
            return;
        }
        if (!this.matchesEffectTarget(victim)) {
            return;
        }
        this.recentlyAttacked.remove(victim.getId());
        this.deathMemoryTracker.forget(victim.getId());
        this.onTrackedKill(victim);
    }

    private final void handleRememberedDeath(LivingEntity victim) {
        if (!this.matchesEffectTarget(victim)) {
            return;
        }
        this.recentlyAttacked.remove(victim.getId());
        this.onTrackedKill(victim);
    }

    private final boolean localPlayerGotKill(LivingEntity victim, DamageSource source) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return false;
        }
        ClientPlayerEntity me = clientPlayerEntity2;
        Long hitAt = this.recentlyAttacked.get(victim.getId());
        if (hitAt != null && System.currentTimeMillis() - hitAt <= 2500L) {
            return true;
        }
        if (Intrinsics.areEqual((Object)victim.getPrimeAdversary(), (Object)me)) {
            return true;
        }
        return source != null && Intrinsics.areEqual((Object)source.getAttacker(), (Object)me);
    }

    @EventHandler
    public final void onAttack(@NotNull AttackEntityEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        ClientPlayerEntity player = this.mc.player;
        if (player == null || event.isSynthetic()) {
            return;
        }
        Entity target = event.getTarget();
        if (target instanceof LivingEntity && !Intrinsics.areEqual((Object)target, (Object)player)) {
            this.recentlyAttacked.put(((LivingEntity)target).getId(), System.currentTimeMillis());
            this.deathMemoryTracker.remember((LivingEntity)target, true);
            this.pruneAttackMemory();
        }
    }

    private final void pruneAttackMemory() {
        long cutoff = System.currentTimeMillis() - 2500L;
        this.recentlyAttacked.values().removeIf(ts -> ts < cutoff);
        long triggerCutoff = System.currentTimeMillis() - 5000L;
        this.recentlyTriggered.values().removeIf(ts -> ts < triggerCutoff);
    }

    @EventHandler
    public final void onTick(@NotNull TickEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!event.isPost()) {
            return;
        }
        if (this.mc.player == null || this.mc.world == null) {
            this.resetState();
            return;
        }
        String currentMode = this.effectMode.getSelected();
        if (this.lastEffectMode != null && !Intrinsics.areEqual((Object)this.lastEffectMode, (Object)currentMode)) {
            this.resetVisuals();
        }
        this.lastEffectMode = currentMode;
        this.pruneAttackMemory();
        this.deathMemoryTracker.tick();
        this.particleSystem.tick();
        this.soundQueue.tick();
    }

    @EventHandler
    public final void onDraw(@NotNull DrawEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        this.drawVignette(event);
    }

    @Override
    protected void onDisable() {
        this.resetState();
    }

    @EventHandler
    public final void onWorldRender(@NotNull WorldRenderEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!this.isEnabled() || event.isPortalPass()) {
            return;
        }
        if (this.isDissolveMode()) {
            if (!this.dissolveEffect.isIdle()) {
                this.dissolveEffect.render(event, this.dissolveSettings(), arg_0 -> KillEffect.onWorldRender$lambda$0(this, arg_0));
            }
            return;
        }
        if (RenderCompatibility.shouldDisableFragEffectScanShader() || KillEffectScanRenderer.isDisabledAfterError()) {
            this.renderDistortion(event);
            return;
        }
        KillEffectScanRenderer.render(event, this.activeEffectSpeedMultiplier, this.c1(), this.c2(), this.c3(), this.c4(), this.c5(), this.c6(), this.c7(), this.c8());
        this.renderDistortion(event);
    }

    private final void renderDistortion(WorldRenderEvent event) {
        if (!this.distortion.getValue() || !this.distortionAnimate || KillDistortionRenderer.isDisabledAfterError()) {
            return;
        }
        float progress = Math.min((float)this.distortionTimer.elapsedTime() / 900.0f, 1.0f);
        if (progress >= 1.0f) {
            this.distortionAnimate = false;
            return;
        }
        if (this.mc.player == null || event.getCamera() == null) {
            return;
        }
        Framebuffer target = this.mc.getFramebuffer();
        if (target == null || target.textureWidth <= 0 || target.textureHeight <= 0) {
            return;
        }
        Matrix4f posM = event.getPositionMatrix();
        Matrix4f projM = event.getProjectionMatrix();
        if (posM == null || projM == null) {
            return;
        }
        Vec3d vec3d2 = event.getCamera().getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d cam = vec3d2;
        Vector4f v = new Vector4f((float)(this.distortionCenter.x - cam.x), (float)(this.distortionCenter.y - cam.y), (float)(this.distortionCenter.z - cam.z), 1.0f);
        posM.transform(v);
        projM.transform(v);
        if (v.w <= 1.0E-4f) {
            return;
        }
        float u = v.x / v.w * 0.5f + 0.5f;
        float sv = v.y / v.w * 0.5f + 0.5f;
        float depth = v.z / v.w * 0.5f + 0.5f;
        float aspect = (float)target.textureWidth / (float)Math.max(1, target.textureHeight);
        float eased = KillEffectEasing.sineInOut(progress);
        float radius = eased * 1.4f;
        float halfWidth = 0.14f - 0.05f * progress;
        float strengthEnvelope = (float)Math.sin((double)progress * Math.PI);
        float strength = 0.055f * strengthEnvelope;
        float[] data = new float[]{u, sv, depth, aspect, radius, halfWidth, strength, progress * 6.0f};
        KillDistortionRenderer.apply(target, data);
    }

    private final void drawVignette(DrawEvent event) {
        float spikeProgress;
        if (this.isDissolveMode() || !this.spikes.getValue()) {
            return;
        }
        long spikeDuration = this.scaleDuration(600L);
        float f = spikeProgress = this.animate ? Math.min((float)this.effectTimer.elapsedTime() / (float)spikeDuration, 1.0f) : 0.0f;
        if (spikeProgress <= 0.0f || spikeProgress >= 1.0f) {
            return;
        }
        float alphaProgress = (float)Math.sin((double)spikeProgress * Math.PI);
        float settle = KillEffectEasing.quintOut(alphaProgress);
        float overscan = 1.0f - settle;
        float width = Position.Companion.screenWidth();
        float height = Position.Companion.screenHeight();
        float extendX = width * 0.75f * overscan;
        float extendY = height * 0.75f * overscan;
        int color = ColorEngine.withAlpha(ColorEngine.lerpColor(KillEffect.Companion.lighten(this.accent(), 0.22f), -1, spikeProgress), Math.round(150.0f * alphaProgress));
        Render2D.beginFrame(event.getGraphics());
        Render2D.image(VIGNETTE_TEXTURE.toString(), -extendX, -extendY, width + extendX * 2.0f, height + extendY * 2.0f, 0.0f, color);
        Render2D.flush();
    }

    private final void onTrackedKill(LivingEntity entity) {
        ClientPlayerEntity player = this.mc.player;
        if (!this.matchesEffectTarget(entity)) {
            return;
        }
        if (player == null || player.isDead()) {
            return;
        }
        long now = System.currentTimeMillis();
        Long triggeredAt = this.recentlyTriggered.get(entity.getId());
        if (triggeredAt != null && now - triggeredAt <= 5000L) {
            return;
        }
        this.recentlyTriggered.put(entity.getId(), now);
        if (this.isDissolveMode()) {
            this.dissolveEffect.queue(entity);
            return;
        }
        Vec3d vec3d2 = entity.getEyePos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getEyePosition(...)");
        Vec3d killPosition = vec3d2;
        float effectSpeed = this.speedMultiplier();
        this.restartEffect(effectSpeed);
        KillEffectScanRenderer.ping(killPosition, effectSpeed);
        this.particleSystem.spawnBurst(killPosition, 480, 900, 1.9f, 22, 0.012f);
        this.scheduleCharmSounds();
        if (this.zoomEffect.getValue() || this.cameraShake.getValue()) {
            this.cameraAnimate = true;
            this.cameraTimer.reset();
        }
        if (this.distortion.getValue()) {
            this.distortionCenter = killPosition;
            this.distortionAnimate = true;
            this.distortionTimer.reset();
        }
    }

    private final boolean isDissolveMode() {
        return this.effectMode.is(EFFECT_DISSOLVE);
    }

    private final float cameraProgress() {
        if (!this.cameraAnimate) {
            return 0.0f;
        }
        float progress = Math.min((float)this.cameraTimer.elapsedTime() / 650.0f, 1.0f);
        if (progress >= 1.0f) {
            this.cameraAnimate = false;
        }
        return progress;
    }

    private final float cameraEnvelope() {
        float progress = this.cameraProgress();
        if (progress <= 0.0f) {
            return 0.0f;
        }
        return (float)Math.sin((double)progress * Math.PI);
    }

    private final void scheduleCharmSounds() {
        long offset = this.scaleDuration(150L);
        this.soundQueue.schedule(SoundManager.FRAG_EFFECT_PULSE, 1.0f, 0L);
        this.soundQueue.schedule(SoundManager.FRAG_EFFECT_KNOCK_MAIN, 1.0f, offset);
        this.soundQueue.schedule(SoundManager.FRAG_EFFECT_SPARKS_COLLISION, 0.2f, offset * 2L);
        this.soundQueue.schedule(SoundManager.FRAG_EFFECT_ECHO_MAIN, 0.6f, offset * 3L);
    }

    private final void restartEffect(float effectSpeedMultiplier) {
        this.animate = true;
        this.activeEffectSpeedMultiplier = MathHelper.clamp((float)effectSpeedMultiplier, (float)0.25f, (float)2.0f);
        this.effectTimer.reset();
    }

    private final boolean matchesEffectTarget(LivingEntity entity) {
        ClientPlayerEntity player = this.mc.player;
        if (entity == null || Intrinsics.areEqual((Object)entity, (Object)player)) {
            return false;
        }
        if (entity instanceof PlayerEntity) {
            if (FriendUtils.isFriend((Entity)entity)) {
                return this.effectTargets.is(TARGET_FRIENDS);
            }
            return this.effectTargets.is(TARGET_PLAYERS);
        }
        if (entity instanceof AnimalEntity) {
            return this.effectTargets.is(TARGET_ANIMALS);
        }
        if (entity instanceof MobEntity) {
            return this.effectTargets.is(TARGET_MOBS);
        }
        return false;
    }

    private final long effectDurationMs() {
        return this.scaleDuration(5200L);
    }

    private final long scaleDuration(long durationMs) {
        return this.scaleDuration(durationMs, this.activeEffectSpeedMultiplier);
    }

    private final long scaleDuration(long durationMs, float speedMultiplier) {
        if (durationMs <= 0L) {
            return 0L;
        }
        return Math.max(1L, (long)((double)durationMs / (double)MathHelper.clamp((float)speedMultiplier, (float)0.25f, (float)2.0f)));
    }

    private final void resetState() {
        this.resetVisuals();
        this.recentlyAttacked.clear();
        this.recentlyTriggered.clear();
        this.deathMemoryTracker.clear();
    }

    private final void resetVisuals() {
        this.animate = false;
        this.cameraAnimate = false;
        this.distortionAnimate = false;
        this.activeEffectSpeedMultiplier = this.speedMultiplier();
        this.soundQueue.clear();
        this.particleSystem.clear();
        this.dissolveEffect.clear();
        KillEffectScanRenderer.clear();
        KillDistortionRenderer.clear();
    }

    private final KillEffectDissolveEffect.Settings dissolveSettings() {
        return new KillEffectDissolveEffect.Settings(Math.round(this.dissolveParticles.getFloat()), this.dissolveParticleSize.getFloat(), Math.round(this.dissolveHold.getFloat()), Math.round(this.dissolveFade.getFloat()), this.dissolveRise.getFloat(), this.dissolveChaos.getFloat(), this.dissolveThroughWalls.getValue());
    }

    private final int dissolveColor(int seed) {
        int angle = Math.floorMod((int)(System.currentTimeMillis() / 8L) + seed, 360);
        if (this.colorMode.is(COLOR_RAINBOW)) {
            return KillEffect.Companion.opaque(ColorEngine.rainbow(angle, 1.0f, 1.0f));
        }
        int[] pair = this.colorPair();
        if (pair[0] == pair[1]) {
            return KillEffect.Companion.opaque(pair[0]);
        }
        int folded = angle >= 180 ? 360 - angle : angle;
        return KillEffect.Companion.opaque(ColorEngine.lerpColor(pair[0], pair[1], (float)folded / 180.0f));
    }

    private final int accent() {
        if (this.colorMode.is(COLOR_RAINBOW)) {
            return KillEffect.Companion.opaque(KillEffect.Companion.rainbow());
        }
        int[] pair = this.colorPair();
        if (pair[0] == pair[1]) {
            return KillEffect.Companion.opaque(pair[0]);
        }
        return KillEffect.Companion.opaque(KillEffect.Companion.fade(pair[0], pair[1]));
    }

    private final int[] colorPair() {
        int firstColor = 0;
        int secondColor = 0;
        if (this.colorMode.is(COLOR_CLIENT)) {
            InterfaceModule iface = InterfaceModule.Companion.getInstance();
            if (iface != null) {
                firstColor = iface.clientPrimaryColorOpaque();
                secondColor = iface.usesSecondClientColor() ? iface.clientSecondaryColorOpaque() : firstColor;
            } else {
                secondColor = firstColor = -50116;
            }
        } else {
            firstColor = this.customColor.getColorOpaque();
            secondColor = this.useSecondColor.getValue() ? this.customSecondColor.getColorOpaque() : firstColor;
        }
        int[] nArray = new int[]{firstColor, secondColor};
        return nArray;
    }

    private final float speedMultiplier() {
        return 1.0f;
    }

    private final int c1() {
        return KillEffect.Companion.lighten(this.accent(), 0.45f);
    }

    private final int c2() {
        return KillEffect.Companion.lighten(this.accent(), 0.18f);
    }

    private final int c3() {
        return KillEffect.Companion.darken(this.accent(), 0.18f);
    }

    private final int c4() {
        return KillEffect.Companion.lighten(this.accent(), 0.6f);
    }

    private final int c5() {
        return KillEffect.Companion.lighten(this.accent(), 0.7f);
    }

    private final int c6() {
        return ColorEngine.lerpColor(this.accent(), -1, 0.28f);
    }

    private final int c7() {
        return KillEffect.Companion.darken(this.accent(), 0.08f);
    }

    private final int c8() {
        return KillEffect.Companion.darken(this.accent(), 0.35f);
    }

    private final float transientEnvelope(long totalMs, long attackMs, long releaseMs) {
        if (!this.animate) {
            return 0.0f;
        }
        long duration = this.effectDurationMs();
        long total = Math.max(1L, Math.min(duration, this.scaleDuration(totalMs)));
        long elapsed = Math.min(this.effectTimer.elapsedTime(), total);
        if (elapsed <= 0L) {
            return 0.0f;
        }
        long attack = Math.max(1L, Math.min(total, this.scaleDuration(attackMs)));
        long release = Math.max(1L, Math.min(total, this.scaleDuration(releaseMs)));
        long releaseStart = Math.max(total - release, attack);
        if (elapsed < attack) {
            return KillEffectEasing.sineOut((float)elapsed / (float)attack);
        }
        if (elapsed >= releaseStart) {
            float releaseProgress = MathHelper.clamp((float)((float)(elapsed - releaseStart) / (float)release), (float)0.0f, (float)1.0f);
            return 1.0f - KillEffectEasing.sineInOut(releaseProgress);
        }
        return 1.0f;
    }

    private static final void deathMemoryTracker$lambda$0(KillEffect this$0, LivingEntity victim) {
        Intrinsics.checkNotNullParameter((Object)victim, (String)"victim");
        this$0.handleRememberedDeath(victim);
    }

    private static final Boolean _init_$lambda$0(KillEffect this$0) {
        return this$0.effectMode.is(EFFECT_SCAN);
    }

    private static final Boolean _init_$lambda$1(KillEffect this$0) {
        return this$0.effectMode.is(EFFECT_SCAN);
    }

    private static final Boolean _init_$lambda$2(KillEffect this$0) {
        return this$0.effectMode.is(EFFECT_SCAN);
    }

    private static final Boolean _init_$lambda$3(KillEffect this$0) {
        return this$0.effectMode.is(EFFECT_SCAN);
    }

    private static final Boolean _init_$lambda$4(KillEffect this$0) {
        return this$0.effectMode.is(EFFECT_SCAN);
    }

    private static final Boolean _init_$lambda$5(KillEffect this$0) {
        return this$0.effectMode.is(EFFECT_SCAN);
    }

    private static final Boolean _init_$lambda$6(KillEffect this$0) {
        return this$0.effectMode.is(EFFECT_DISSOLVE);
    }

    private static final Boolean _init_$lambda$7(KillEffect this$0) {
        return this$0.effectMode.is(EFFECT_DISSOLVE);
    }

    private static final Boolean _init_$lambda$8(KillEffect this$0) {
        return this$0.effectMode.is(EFFECT_DISSOLVE);
    }

    private static final Boolean _init_$lambda$9(KillEffect this$0) {
        return this$0.effectMode.is(EFFECT_DISSOLVE);
    }

    private static final Boolean _init_$lambda$10(KillEffect this$0) {
        return this$0.effectMode.is(EFFECT_DISSOLVE);
    }

    private static final Boolean _init_$lambda$11(KillEffect this$0) {
        return this$0.effectMode.is(EFFECT_DISSOLVE);
    }

    private static final Boolean _init_$lambda$12(KillEffect this$0) {
        return this$0.effectMode.is(EFFECT_DISSOLVE);
    }

    private static final Boolean _init_$lambda$13(KillEffect this$0) {
        return this$0.effectMode.is(EFFECT_DISSOLVE);
    }

    private static final Boolean _init_$lambda$14(KillEffect this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean _init_$lambda$15(KillEffect this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean _init_$lambda$16(KillEffect this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM) && this$0.useSecondColor.getValue();
    }

    private static final int onWorldRender$lambda$0(KillEffect this$0, int seed) {
        return this$0.dissolveColor(seed);
    }

    @JvmStatic
    @Nullable
    public static final KillEffect getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    @Nullable
    public static final KillEffect getInstanceIfReady() {
        return Companion.getInstanceIfReady();
    }

    @JvmStatic
    public static final void notifyEntityDied(@Nullable LivingEntity victim, @Nullable DamageSource source) {
        Companion.notifyEntityDied(victim, source);
    }

    @JvmStatic
    public static final float getWorldSaturationMultiplier() {
        return Companion.getWorldSaturationMultiplier();
    }

    @JvmStatic
    public static final float getKillZoomFovScale() {
        return Companion.getKillZoomFovScale();
    }

    @JvmStatic
    public static final float getKillShakeDegrees() {
        return Companion.getKillShakeDegrees();
    }

    static {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"textures/effects/frag/lightarroundscreen_alpha.png");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        VIGNETTE_TEXTURE = identifier2;
        DARK_SECOND_COLOR = new Color(16, 16, 16, 75).getRGB();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\u0007J'\u0010\u000e\u001a\u00020\r2\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\u0011\u001a\u00020\u0010H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u0010H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0013\u0010\u0012J\u0013\u0010\u0014\u001a\u00020\u0010H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0014\u0010\u0012J\u000f\u0010\u0016\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u001f\u0010 \u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b \u0010!J\u001f\u0010\"\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\"\u0010!J\u0017\u0010#\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b#\u0010\u001eJ\u0017\u0010$\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b$\u0010\u001eJ\u0017\u0010%\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b%\u0010\u001eJ\u0017\u0010&\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b&\u0010\u001eR\u0014\u0010(\u001a\u00020'8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020'8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010)R\u0014\u0010,\u001a\u00020+8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u0014\u0010/\u001a\u00020.8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0014\u00101\u001a\u00020.8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b1\u00100R\u0014\u00102\u001a\u00020.8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u00100R\u0014\u00103\u001a\u00020.8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b3\u00100R\u0014\u00104\u001a\u00020.8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u00100R\u0014\u00105\u001a\u00020.8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b5\u00100R\u0014\u00106\u001a\u00020.8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b6\u00100R\u0014\u00107\u001a\u00020.8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b7\u00100R\u0014\u00108\u001a\u00020.8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b8\u00100R\u0014\u00109\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b9\u0010:R\u0014\u0010;\u001a\u00020'8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b;\u0010)R\u0014\u0010<\u001a\u00020'8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b<\u0010)R\u0014\u0010>\u001a\u00020=8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u0010AR\u0014\u0010B\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bB\u0010AR\u0014\u0010C\u001a\u00020'8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bC\u0010)R\u0014\u0010D\u001a\u00020'8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bD\u0010)R\u001d\u0010F\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\bE\u00a2\u0006\u0006\n\u0004\bF\u0010G\u00a8\u0006H"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/KillEffect.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/KillEffect;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/KillEffect;", "getInstanceIfReady", "Lnet/minecraft/LivingEntity;", "victim", "Lnet/minecraft/DamageSource;", "source", "", "notifyEntityDied", "(Lnet/minecraft/LivingEntity;Lnet/minecraft/DamageSource;)V", "", "getWorldSaturationMultiplier", "()F", "getKillZoomFovScale", "getKillShakeDegrees", "", "rainbow", "()I", "first", "second", "fade", "(II)I", "color", "opaque", "(I)I", "amount", "lighten", "(IF)I", "darken", "red", "green", "blue", "alpha", "", "BASE_EFFECT_DURATION_MS", "J", "SPIKE_DURATION_MS", "Lnet/minecraft/Identifier;", "VIGNETTE_TEXTURE", "Lnet/minecraft/Identifier;", "", "TARGET_PLAYERS", "Ljava/lang/String;", "TARGET_FRIENDS", "TARGET_MOBS", "TARGET_ANIMALS", "EFFECT_SCAN", "EFFECT_DISSOLVE", "COLOR_RAINBOW", "COLOR_CLIENT", "COLOR_CUSTOM", "DARK_SECOND_COLOR", "I", "ATTACK_MEMORY_MS", "TRIGGER_MEMORY_MS", "", "DEATH_WATCH_DISTANCE", "D", "ZOOM_STRENGTH", "F", "SHAKE_STRENGTH", "CAMERA_EFFECT_MS", "DISTORTION_MS", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/KillEffect;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final KillEffect getInstance() {
            KillEffect module = ModuleManager.Companion.get().get(KillEffect.class);
            KillEffect killEffect = module;
            if (killEffect == null) {
                killEffect = INSTANCE;
            }
            return killEffect;
        }

        @JvmStatic
        @Nullable
        public final KillEffect getInstanceIfReady() {
            KillEffect killEffect;
            try {
                killEffect = this.getInstance();
            }
            catch (RuntimeException ignored) {
                killEffect = null;
            }
            return killEffect;
        }

        @JvmStatic
        public final void notifyEntityDied(@Nullable LivingEntity victim, @Nullable DamageSource source) {
            KillEffect module = this.getInstanceIfReady();
            if (module == null || !module.isEnabled()) {
                return;
            }
            module.handleDeath(victim, source);
        }

        @JvmStatic
        public final float getWorldSaturationMultiplier() {
            KillEffect module = this.getInstanceIfReady();
            if (module == null || !module.isEnabled() || module.isDissolveMode() || !module.changeSaturation.getValue()) {
                return 1.0f;
            }
            float pulse = module.transientEnvelope(1950L, 260L, 760L);
            if (pulse <= 0.0f) {
                return 1.0f;
            }
            return MathHelper.clamp((float)(1.0f - pulse), (float)0.0f, (float)1.0f);
        }

        @JvmStatic
        public final float getKillZoomFovScale() {
            KillEffect module = this.getInstanceIfReady();
            if (module == null || !module.isEnabled() || module.isDissolveMode() || !module.zoomEffect.getValue()) {
                return 1.0f;
            }
            float envelope = module.cameraEnvelope();
            if (envelope <= 0.0f) {
                return 1.0f;
            }
            float fovReduction = 15.0f * envelope;
            float scale = 1.0f - fovReduction / 70.0f;
            return MathHelper.clamp((float)scale, (float)0.5f, (float)1.0f);
        }

        @JvmStatic
        public final float getKillShakeDegrees() {
            KillEffect module = this.getInstanceIfReady();
            if (module == null || !module.isEnabled() || module.isDissolveMode() || !module.cameraShake.getValue()) {
                return 0.0f;
            }
            float progress = module.cameraProgress();
            if (progress <= 0.0f) {
                return 0.0f;
            }
            float intensity = 2.5f;
            float t = (float)module.cameraTimer.elapsedTime() / 1000.0f;
            float wobble = (float)(Math.sin(t * 26.0f) * 0.65 + Math.sin(t * 16.5f) * 0.35);
            float decay = (1.0f - progress) * (1.0f - progress);
            return wobble * 0.8f * intensity * decay;
        }

        private final int rainbow() {
            int angle = (int)(System.currentTimeMillis() / 8L % 360L);
            return ColorEngine.rainbow(angle, 1.0f, 1.0f);
        }

        private final int fade(int first, int second) {
            int angle = (int)(System.currentTimeMillis() / 8L % 360L);
            angle = angle >= 180 ? 360 - angle : angle;
            return ColorEngine.lerpColor(first, second, (float)angle / 180.0f);
        }

        private final int opaque(int color) {
            return 0xFF000000 | color & 0xFFFFFF;
        }

        private final int lighten(int color, float amount) {
            float clamped = MathHelper.clamp((float)amount, (float)0.0f, (float)1.0f);
            int red = this.red(color);
            int green = this.green(color);
            int blue = this.blue(color);
            int alpha = this.alpha(color);
            return ColorEngine.rgba(MathHelper.clamp((int)Math.round((float)red + (float)(255 - red) * clamped), (int)0, (int)255), MathHelper.clamp((int)Math.round((float)green + (float)(255 - green) * clamped), (int)0, (int)255), MathHelper.clamp((int)Math.round((float)blue + (float)(255 - blue) * clamped), (int)0, (int)255), alpha);
        }

        private final int darken(int color, float amount) {
            float factor = 1.0f - MathHelper.clamp((float)amount, (float)0.0f, (float)1.0f);
            return ColorEngine.rgba(MathHelper.clamp((int)Math.round((float)this.red(color) * factor), (int)0, (int)255), MathHelper.clamp((int)Math.round((float)this.green(color) * factor), (int)0, (int)255), MathHelper.clamp((int)Math.round((float)this.blue(color) * factor), (int)0, (int)255), this.alpha(color));
        }

        private final int red(int color) {
            return color >>> 16 & 0xFF;
        }

        private final int green(int color) {
            return color >>> 8 & 0xFF;
        }

        private final int blue(int color) {
            return color & 0xFF;
        }

        private final int alpha(int color) {
            return color >>> 24 & 0xFF;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

