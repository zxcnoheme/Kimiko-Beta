/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  net.minecraft.client.util.Window
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityType
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.projectile.LlamaSpitEntity
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.world.World
 *  net.minecraft.util.math.Box
 *  net.minecraft.util.hit.HitResult.Type
 *  net.minecraft.particle.ParticleEffect
 *  net.minecraft.particle.ParticleTypes
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.sound.SoundEvents
 *  net.minecraft.sound.SoundCategory
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.RaycastContext
 *  net.minecraft.world.RaycastContext.FluidHandling
 *  net.minecraft.world.RaycastContext.ShapeType
 *  net.minecraft.util.hit.BlockHitResult
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Matrix4f
 *  org.joml.Quaternionf
 *  org.joml.Vector3f
 *  org.joml.Vector4f
 */
package rtx.kimiko.api.modules.impl.Utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import net.minecraft.client.util.Window;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.LlamaSpitEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import net.minecraft.util.hit.HitResult;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.Identifier;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.RaycastContext;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector4f;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BindSetting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.SelectSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.party.PartyChat;
import rtx.kimiko.api.party.PartyClient;
import rtx.kimiko.api.party.PartyMarker;
import rtx.kimiko.api.party.voice.MicCapture;
import rtx.kimiko.api.party.voice.PartyVoice;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;
import rtx.kimiko.utils.render.render2d.Render2D;

@Feature(value={"party"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0090\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u0091\u00012\u00020\u0001:\u0004\u0092\u0001\u0091\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\u0006\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u000f\u0010\u0007\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0007\u0010\u0003J\u000f\u0010\b\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\b\u0010\u0003J\u000f\u0010\t\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\t\u0010\u0003J\u000f\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u001b\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0011H\u0003b\u0002\b\u0013\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0003J\u000f\u0010\u0017\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0003JO\u0010\"\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b\"\u0010#J\u0019\u0010&\u001a\u0004\u0018\u00010%2\u0006\u0010$\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b&\u0010'J\u0011\u0010)\u001a\u0004\u0018\u00010(H\u0002\u00a2\u0006\u0004\b)\u0010*J\u001b\u0010,\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020+H\u0003b\u0002\b\u0013\u00a2\u0006\u0004\b,\u0010-J\u000f\u0010.\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b.\u0010\u0003J?\u0010;\u001a\u00020:2\u0006\u00100\u001a\u00020/2\u0006\u00102\u001a\u0002012\u0006\u00104\u001a\u0002032\u0006\u00105\u001a\u00020(2\u0006\u00107\u001a\u0002062\u0006\u00109\u001a\u000208H\u0002\u00a2\u0006\u0004\b;\u0010<JW\u0010D\u001a\u00020\u00042\u0006\u00102\u001a\u0002012\u0006\u0010>\u001a\u00020=2\u0006\u00107\u001a\u0002062\u0006\u0010?\u001a\u00020\r2\u0006\u0010@\u001a\u00020:2\u0006\u0010A\u001a\u00020:2\u0006\u0010B\u001a\u00020:2\u0006\u0010C\u001a\u00020:2\u0006\u0010\u001c\u001a\u00020:H\u0002\u00a2\u0006\u0004\bD\u0010EJW\u0010M\u001a\u00020\u00042\u0006\u0010G\u001a\u00020F2\u0006\u0010H\u001a\u00020F2\u0006\u00105\u001a\u00020(2\u0006\u0010I\u001a\u00020:2\u0006\u0010J\u001a\u00020:2\u0006\u00107\u001a\u0002062\u0006\u0010K\u001a\u00020(2\u0006\u00109\u001a\u0002082\u0006\u0010L\u001a\u00020%H\u0002\u00a2\u0006\u0004\bM\u0010NJ%\u0010Q\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020OH\u0003b\f\b\u0013\u0012\b\bP\u0012\u0004\b\u0003\u0010\u0000\u00a2\u0006\u0004\bQ\u0010RJ\u001f\u0010W\u001a\u00020\u00042\u0006\u0010T\u001a\u00020S2\u0006\u0010V\u001a\u00020UH\u0002\u00a2\u0006\u0004\bW\u0010XR\u0014\u0010Z\u001a\u00020Y8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bZ\u0010[R\u0014\u0010\\\u001a\u00020Y8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\\\u0010[R\u0014\u0010^\u001a\u00020]8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b^\u0010_R\u0014\u0010a\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\ba\u0010bR\u0014\u0010d\u001a\u00020c8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bd\u0010eR\u0014\u0010g\u001a\u00020f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bg\u0010hR\u0014\u0010j\u001a\u00020i8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bj\u0010kR\u0014\u0010l\u001a\u00020c8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bl\u0010eR\u0014\u0010m\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bm\u0010bR\u0014\u0010o\u001a\u00020n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bo\u0010pR\u0014\u0010q\u001a\u00020f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bq\u0010hR\u0014\u0010r\u001a\u00020Y8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\br\u0010[R\u0014\u0010s\u001a\u00020]8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bs\u0010_R\u0014\u0010t\u001a\u00020]8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bt\u0010_R\u0014\u0010u\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bu\u0010bR\u0014\u0010v\u001a\u00020]8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bv\u0010_R\u0014\u0010w\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bw\u0010bR\u0014\u0010x\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bx\u0010bR\u0016\u0010y\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\by\u0010zR\u0016\u0010{\u001a\u0002088\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b{\u0010|R#\u0010\u007f\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010~0}8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u007f\u0010\u0080\u0001R\u0018\u0010\u0082\u0001\u001a\u00030\u0081\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0082\u0001\u0010\u0083\u0001R\u001e\u0010\u0085\u0001\u001a\t\u0012\u0004\u0012\u00020U0\u0084\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0085\u0001\u0010\u0086\u0001R\u001e\u0010\u0087\u0001\u001a\t\u0012\u0004\u0012\u00020U0\u0084\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0087\u0001\u0010\u0086\u0001R\u001f\u0010\u0089\u0001\u001a\n\u0012\u0005\u0012\u00030\u0088\u00010\u0084\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0089\u0001\u0010\u0086\u0001R\u0018\u0010\u008b\u0001\u001a\u00030\u008a\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u008b\u0001\u0010\u008c\u0001R\u0018\u0010\u008d\u0001\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u008d\u0001\u0010zR\u0018\u0010\u008e\u0001\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u008e\u0001\u0010zR\u0019\u0010\u008f\u0001\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u008f\u0001\u0010\u0090\u0001\u00ca\u0001\u0012\b\u0093\u0001\u0012\r\bP\u0012\t\b\fJ\u0005\b\b(\u0094\u0001\u00a8\u0006\u0095\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/Party;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "onVoiceToggle", "pushVoice", "onEnable", "onDisable", "pushColor", "", "broadcastRainbow", "()Z", "", "broadcastColor", "()I", "requestDeviceScan", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "onBindPressed", "spitForward", "", "from", "", "x", "y", "z", "dx", "dy", "dz", "dim", "onPartySpit", "(Ljava/lang/String;DDDDDDLjava/lang/String;)V", "name", "Lnet/minecraft/PlayerEntity;", "findPlayerByName", "(Ljava/lang/String;)Lnet/minecraft/PlayerEntity;", "Lnet/minecraft/Vec3d;", "raycastTarget", "()Lnet/minecraft/Vec3d;", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "resolveLabelOverlap", "Lnet/minecraft/MatrixStack;", "stack", "Lnet/minecraft/VertexConsumer;", "vc", "Lorg/joml/Quaternionf;", "camRot", "cam", "Lrtx/kimiko/api/party/PartyMarker;", "m", "", "now", "", "renderMarker", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/VertexConsumer;Lorg/joml/Quaternionf;Lnet/minecraft/Vec3d;Lrtx/kimiko/api/party/PartyMarker;J)F", "Lnet/minecraft/MatrixStack$Entry;", "pose", "seg", "radius", "half", "peak", "fade", "ring", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lrtx/kimiko/api/party/PartyMarker;IFFFFF)V", "Lorg/joml/Matrix4f;", "posM", "projM", "sw", "sh", "p", "player", "projectLabel", "(Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/Vec3d;FFLrtx/kimiko/api/party/PartyMarker;Lnet/minecraft/Vec3d;JLnet/minecraft/PlayerEntity;)V", "Lrtx/kimiko/api/events/impl/render/HudRenderEvent;", "value", "onHud", "(Lrtx/kimiko/api/events/impl/render/HudRenderEvent;)V", "Lnet/minecraft/DrawContext;", "g", "Lrtx/kimiko/api/modules/impl/Utils/Party$Label;", "l", "drawLabel", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/api/modules/impl/Utils/Party$Label;)V", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "spitBind", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "bind", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "duration", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "throughWalls", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "colorSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "colorMode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "voiceSeparator", "voiceEnabled", "Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "voiceDevice", "Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "voiceActivation", "voiceTalk", "voiceThreshold", "voiceVolume", "voiceAgc", "voiceMicGain", "voiceDenoise", "voiceMute", "voiceWarned", "Z", "nextDeviceScanAt", "J", "Ljava/util/concurrent/atomic/AtomicReference;", "", "pendingDevices", "Ljava/util/concurrent/atomic/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "deviceScanRunning", "Ljava/util/concurrent/atomic/AtomicBoolean;", "", "pending", "Ljava/util/List;", "labels", "Lnet/minecraft/LlamaSpitEntity;", "spits", "Lorg/joml/Vector4f;", "projScratch", "Lorg/joml/Vector4f;", "lastDown", "spitLastDown", "nextSpitId", "I", "Companion", "Label", "Lrtx/kimiko/api/liteapi/Feature;", "party", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nParty.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Party.kt\nrtx/kimiko/api/modules/impl/Utils/Party\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,740:1\n2068#2,2:741\n*S KotlinDebug\n*F\n+ 1 Party.kt\nrtx/kimiko/api/modules/impl/Utils/Party\n*L\n195#1:741,2\n*E\n"})
public final class Party
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final BindSetting spitBind = (BindSetting)this.register((Setting)new BindSetting("Плевок", "Плевок ламы туда, куда смотрите.").setKey(-1));
    @NotNull
    private final BindSetting bind = (BindSetting)this.register((Setting)new BindSetting("Метка", "Поставить метку туда, куда смотрите.").setKey(-1));
    @NotNull
    private final SliderSetting duration = (SliderSetting)this.register((Setting)new SliderSetting("Время метки", "Сколько секунд видна метка (для всех).").setValue(8.0f).range(1, 60).increment(1));
    @NotNull
    private final BooleanSetting throughWalls = (BooleanSetting)this.register((Setting)new BooleanSetting("Сквозь стены", "Видеть метку сквозь блоки.", true));
    @NotNull
    private final SeparatorSetting colorSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Цвет в команде"));
    @NotNull
    private final ModeSetting colorMode;
    @NotNull
    private final ColorSetting customColor;
    @NotNull
    private final SeparatorSetting voiceSeparator;
    @NotNull
    private final BooleanSetting voiceEnabled;
    @NotNull
    private final SelectSetting voiceDevice;
    @NotNull
    private final ModeSetting voiceActivation;
    @NotNull
    private final BindSetting voiceTalk;
    @NotNull
    private final SliderSetting voiceThreshold;
    @NotNull
    private final SliderSetting voiceVolume;
    @NotNull
    private final BooleanSetting voiceAgc;
    @NotNull
    private final SliderSetting voiceMicGain;
    @NotNull
    private final BooleanSetting voiceDenoise;
    @NotNull
    private final BooleanSetting voiceMute;
    private boolean voiceWarned;
    private long nextDeviceScanAt;
    @NotNull
    private final AtomicReference<List<String>> pendingDevices;
    @NotNull
    private final AtomicBoolean deviceScanRunning;
    @NotNull
    private final List<Label> pending;
    @NotNull
    private final List<Label> labels;
    @NotNull
    private final List<LlamaSpitEntity> spits;
    @NotNull
    private final Vector4f projScratch;
    private boolean lastDown;
    private boolean spitLastDown;
    private int nextSpitId;
    @NotNull
    private static final Identifier GLOW_TEXTURE;
    @NotNull
    private static final String COLOR_RAINBOW = "Радуга";
    @NotNull
    private static final String COLOR_CLIENT = "Клиент";
    @NotNull
    private static final String COLOR_CUSTOM = "Свой";
    private static final double LABEL_NEAR = 16.0;
    private static final double LABEL_FULL = 22.0;
    private static final int LABEL_BG = -435023336;
    private static final float IN_MS = 350.0f;
    private static final float OUT_MS = 300.0f;
    @NotNull
    private static final String V_PTT = "Кнопка";
    @NotNull
    private static final String V_VAD = "По голосу";
    private static final long DEVICE_SCAN_MS = 3000L;
    private static final float L_NAME_SIZE = 6.0f;
    private static final float L_DIST_SIZE = 6.5f;
    private static final float L_PAD_X = 5.0f;
    private static final float L_PAD_Y = 3.0f;
    private static final float L_GAP = 4.0f;
    private static final float L_DOT_R = 1.7f;
    private static final float L_DOT_GAP = 3.0f;
    private static final float L_ARROW_W = 8.0f;
    private static final float L_ARROW_H = 4.5f;
    private static final int L_DIST_COLOR = -4011820;
    private static final int DEFAULT_MARKER_COLOR = -14628609;

    public Party() {
        super("Party", "Метки для участников Party. Нажмите бинд, чтобы поставить ping.", Category.UTILS);
        String[] stringArray = new String[]{COLOR_RAINBOW, COLOR_CLIENT, COLOR_CUSTOM};
        this.colorMode = (ModeSetting)this.register((Setting)new ModeSetting("Режим цвета", "Цвет метки.", COLOR_RAINBOW, stringArray));
        this.customColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет", "Свой цвет метки.", new Color(0, 200, 255, 255)).visibleWhen(() -> Party.customColor$lambda$0(this)));
        this.voiceSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Голосовой чат"));
        this.voiceEnabled = (BooleanSetting)this.register((Setting)new BooleanSetting("Голос в Party", "Общаться голосом с участниками Party.", false));
        this.voiceDevice = (SelectSetting)this.register((Setting)new SelectSetting("Микрофон", "Устройство ввода для голоса. «Системный» — микрофон по умолчанию из настроек Windows.").options(MicCapture.devices()).visible(() -> Party.voiceDevice$lambda$0(this)));
        stringArray = new String[]{V_PTT, V_VAD};
        this.voiceActivation = (ModeSetting)this.register((Setting)new ModeSetting("Активация", "Как включается микрофон.", V_PTT, stringArray).visibleWhen(() -> Party.voiceActivation$lambda$0(this)));
        this.voiceTalk = (BindSetting)this.register((Setting)new BindSetting("Говорить", "Зажмите, чтобы говорить (push-to-talk).").setKey(86).visibleWhen(() -> Party.voiceTalk$lambda$0(this)));
        this.voiceThreshold = (SliderSetting)this.register((Setting)new SliderSetting("Порог голоса", "Чувствительность авто-активации (%): выше — нужен более чёткий голос.").setValue(50.0f).range(0, 100).increment(1).visible(() -> Party.voiceThreshold$lambda$0(this)));
        this.voiceVolume = (SliderSetting)this.register((Setting)new SliderSetting("Громкость голоса", "Громкость голоса тиммейтов (%).").setValue(100.0f).range(0, 200).increment(5).visible(() -> Party.voiceVolume$lambda$0(this)));
        this.voiceAgc = (BooleanSetting)this.register((Setting)new BooleanSetting("Авто-громкость (AGC)", "Автоматически выравнивать громкость микрофона.", true).visibleWhen(() -> Party.voiceAgc$lambda$0(this)));
        this.voiceMicGain = (SliderSetting)this.register((Setting)new SliderSetting("Чувствительность микрофона", "Усиление микрофона (%). Работает, когда AGC выключен.").setValue(100.0f).range(0, 200).increment(5).visible(() -> Party.voiceMicGain$lambda$0(this)));
        this.voiceDenoise = (BooleanSetting)this.register((Setting)new BooleanSetting("Шумоподавление", "Убирать фоновый шум микрофона (RNNoise).", true).visibleWhen(() -> Party.voiceDenoise$lambda$0(this)));
        this.voiceMute = (BooleanSetting)this.register((Setting)new BooleanSetting("Заглушить всех", "Не слышать голос тиммейтов.", false).visibleWhen(() -> Party.voiceMute$lambda$0(this)));
        this.pendingDevices = new AtomicReference<List<String>>(null);
        this.deviceScanRunning = new AtomicBoolean(false);
        this.pending = new ArrayList();
        this.labels = new ArrayList();
        this.spits = new ArrayList();
        this.projScratch = new Vector4f();
        this.nextSpitId = -2100000;
        this.colorMode.setChangeListener(() -> Party._init_$lambda$0(this));
        this.customColor.setChangeListener(() -> Party._init_$lambda$1(this));
        this.pushColor();
        this.voiceDevice.setChangeListener(() -> Party._init_$lambda$2(this));
        this.voiceEnabled.setChangeListener(() -> Party._init_$lambda$3(this));
        this.voiceActivation.setChangeListener(() -> Party._init_$lambda$4(this));
        this.voiceThreshold.setChangeListener(() -> Party._init_$lambda$5(this));
        this.voiceVolume.setChangeListener(() -> Party._init_$lambda$6(this));
        this.voiceAgc.setChangeListener(() -> Party._init_$lambda$7(this));
        this.voiceMicGain.setChangeListener(() -> Party._init_$lambda$8(this));
        this.voiceDenoise.setChangeListener(() -> Party._init_$lambda$9(this));
        this.voiceMute.setChangeListener(() -> Party._init_$lambda$10(this));
    }

    private final void onVoiceToggle() {
        if (this.voiceEnabled.getValue() && this.isEnabled()) {
            PartyVoice.INSTANCE.start();
            this.pushVoice();
        } else {
            PartyVoice.INSTANCE.setPttHeld(false);
            PartyVoice.INSTANCE.stop();
        }
    }

    private final void pushVoice() {
        PartyVoice.INSTANCE.setActivationMode(this.voiceActivation.is(V_VAD) ? PartyVoice.ActivationMode.VOICE : PartyVoice.ActivationMode.PTT);
        PartyVoice.INSTANCE.setVadSensitivity(this.voiceThreshold.getFloat() / 100.0f);
        PartyVoice.INSTANCE.setOutputGain(this.voiceVolume.getFloat() / 100.0f);
        PartyVoice.INSTANCE.setAgc(this.voiceAgc.getValue());
        PartyVoice.INSTANCE.setInputGain(this.voiceMicGain.getFloat() / 100.0f);
        PartyVoice.INSTANCE.setDenoise(this.voiceDenoise.getValue());
        PartyVoice.INSTANCE.setMuted(this.voiceMute.getValue());
    }

    @Override
    protected void onEnable() {
        PartyClient.INSTANCE.start();
        PartyClient.INSTANCE.setSpitSink((arg_0, arg_1, arg_2, arg_3, arg_4, arg_5, arg_6, arg_7) -> Party.onEnable$lambda$0(this, arg_0, arg_1, arg_2, arg_3, arg_4, arg_5, arg_6, arg_7));
        this.pushColor();
        this.voiceWarned = false;
        if (this.voiceEnabled.getValue()) {
            PartyVoice.INSTANCE.start();
            this.pushVoice();
        }
    }

    @Override
    protected void onDisable() {
        this.lastDown = false;
        this.spitLastDown = false;
        this.labels.clear();
        Iterable $this$forEach$iv = this.spits;
        boolean $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            Entity p0 = (Entity)element$iv;
            boolean bl = false;
            p0.discard();
        }
        this.spits.clear();
        PartyVoice.INSTANCE.setPttHeld(false);
        PartyVoice.INSTANCE.stop();
        PartyClient.INSTANCE.setSpitSink(null);
        PartyClient.INSTANCE.stop();
    }

    private final void pushColor() {
        PartyClient.INSTANCE.setLocalColor(this.broadcastColor(), this.broadcastRainbow());
    }

    private final boolean broadcastRainbow() {
        return this.colorMode.is(COLOR_RAINBOW);
    }

    private final int broadcastColor() {
        if (this.colorMode.is(COLOR_CLIENT)) {
            InterfaceModule iface;
            InterfaceModule interfaceModule = iface = InterfaceModule.Companion.getInstance();
            return interfaceModule != null ? interfaceModule.clientPrimaryColorOpaque() : -14628609;
        }
        return this.customColor.getColor() | 0xFF000000;
    }

    private final void requestDeviceScan() {
        if (!this.deviceScanRunning.compareAndSet(false, true)) {
            return;
        }
        Thread scanner = new Thread(() -> Party.requestDeviceScan$lambda$0(this), "Kimiko-Mic-Devices");
        scanner.setDaemon(true);
        scanner.start();
    }

    @EventHandler
    private final void onTick(TickEvent event) {
        if (!event.isPre()) {
            return;
        }
        if (this.voiceEnabled.getValue()) {
            String err;
            List scannedDevices;
            long now = System.currentTimeMillis();
            if (now >= this.nextDeviceScanAt) {
                this.nextDeviceScanAt = now + 3000L;
                this.requestDeviceScan();
            }
            if ((scannedDevices = (List)this.pendingDevices.getAndSet(null)) != null) {
                this.voiceDevice.options(scannedDevices);
            }
            if ((err = PartyVoice.INSTANCE.lastError()) != null && !this.voiceWarned) {
                this.voiceWarned = true;
                Object[] objectArray = new Object[]{err};
                PartyChat.printNotice("error", I18n.tr("Голос недоступен: %s", objectArray));
            }
            if (this.voiceActivation.is(V_PTT)) {
                Window window2 = this.mc.getWindow();
                Intrinsics.checkNotNullExpressionValue((Object)window2, (String)"getWindow(...)");
                Window window = window2;
                boolean canTalk = this.voiceTalk.isBound() && this.mc.currentScreen == null && this.mc.player != null;
                boolean down = canTalk && this.voiceTalk.getValue().isDown(window.getHandle());
                PartyVoice.INSTANCE.setPttHeld(down);
            }
        }
        Window window3 = this.mc.getWindow();
        Intrinsics.checkNotNullExpressionValue((Object)window3, (String)"getWindow(...)");
        Window window = window3;
        if (this.mc.currentScreen != null || this.mc.player == null || this.mc.world == null) {
            this.lastDown = false;
            this.spitLastDown = false;
            return;
        }
        this.spits.removeIf(spit -> {
            if (spit.isRemoved() || spit.age > 100) {
                spit.discard();
                return true;
            }
            return false;
        });
        if (!this.bind.isBound()) {
            boolean down = this.bind.getValue().isDown(window.getHandle());
            if (down && !this.lastDown) {
                this.onBindPressed();
            }
            this.lastDown = down;
        }
        if (!this.spitBind.isBound()) {
            this.spitLastDown = false;
            return;
        }
        boolean spitDown = this.spitBind.getValue().isDown(window.getHandle());
        if (spitDown && !this.spitLastDown) {
            this.spitForward();
        }
        this.spitLastDown = spitDown;
    }

    private final void onBindPressed() {
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        if (this.mc.player == null) {
            return;
        }
        Vec3d vec3d2 = this.raycastTarget();
        if (vec3d2 == null) {
            return;
        }
        Vec3d target = vec3d2;
        String string = level.getRegistryKey().getValue().toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        String dim = string;
        long ttl = (long)this.duration.getInt() * 1000L;
        if (!PartyClient.INSTANCE.sendPing(target.x, target.y, target.z, dim, ttl)) {
            PartyChat.printNotice("error", I18n.tr("Нет связи с Party-сервером."));
        }
    }

    private final void spitForward() {
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        Vec3d vec3d2 = player.getCameraPosVec(1.0f);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getEyePosition(...)");
        Vec3d start = vec3d2;
        Vec3d vec3d3 = player.getRotationVec(1.0f);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"getViewVector(...)");
        Vec3d direction = vec3d3;
        String string = level.getRegistryKey().getValue().toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        String dim = string;
        if (!PartyClient.INSTANCE.sendSpit(start.x, start.y, start.z, direction.x, direction.y, direction.z, dim)) {
            PartyChat.printNotice("error", I18n.tr("Нет связи с Party-сервером."));
        }
    }

    private final void onPartySpit(String from, double x, double y, double z, double dx, double dy, double dz, String dim) {
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        if (this.mc.player == null) {
            return;
        }
        if (!Intrinsics.areEqual((Object)level.getRegistryKey().getValue().toString(), (Object)dim)) {
            return;
        }
        PlayerEntity fromPlayer = this.findPlayerByName(from);
        Vec3d start = new Vec3d(x, y, z);
        Vec3d direction = new Vec3d(dx, dy, dz);
        if (direction.lengthSquared() < 1.0E-4) {
            return;
        }
        Vec3d vec3d2 = direction.normalize();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"normalize(...)");
        direction = vec3d2;
        LlamaSpitEntity spit = new LlamaSpitEntity(EntityType.LLAMA_SPIT, (World)level);
        int n = this.nextSpitId;
        this.nextSpitId = n + -1;
        spit.setId(n);
        if (this.nextSpitId < -2200000) {
            this.nextSpitId = -2100000;
        }
        if (fromPlayer != null) {
            spit.setOwner((Entity)fromPlayer);
        }
        spit.setPosition(start.x + direction.x * 0.35, start.y + direction.y * 0.35, start.z + direction.z * 0.35);
        spit.setVelocity(direction.x, direction.y, direction.z, 1.5f, 0.0f);
        level.addEntity((Entity)spit);
        this.spits.add(spit);
        Vec3d vec3d3 = spit.getVelocity();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"getDeltaMovement(...)");
        Vec3d movement = vec3d3;
        for (int i = 0; i < 7; ++i) {
            double scale = 0.4 + 0.1 * (double)i;
            level.addParticleClient((ParticleEffect)ParticleTypes.SPIT, spit.getX(), spit.getY(), spit.getZ(), movement.x * scale, movement.y, movement.z * scale);
        }
        float pitch = 1.0f + (spit.getRandom().nextFloat() - spit.getRandom().nextFloat()) * 0.2f;
        level.playSoundClient(start.x, start.y, start.z, SoundEvents.ENTITY_LLAMA_SPIT, SoundCategory.NEUTRAL, 1.0f, pitch, false);
    }

    private final PlayerEntity findPlayerByName(String name) {
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return null;
        }
        ClientWorld level = clientWorld3;
        for (Object e : level.getPlayers()) {
            Intrinsics.checkNotNullExpressionValue(e, (String)"next(...)");
            AbstractClientPlayerEntity player = (AbstractClientPlayerEntity)e;
            if (!StringsKt.equals((String)player.getGameProfile().name(), (String)name, (boolean)true) && !StringsKt.equals((String)player.getName().getString(), (String)name, (boolean)true)) continue;
            return (PlayerEntity)player;
        }
        return null;
    }

    private final Vec3d raycastTarget() {
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return null;
        }
        ClientWorld level = clientWorld3;
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return null;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        Vec3d vec3d2 = player.getCameraPosVec(1.0f);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getEyePosition(...)");
        Vec3d eye = vec3d2;
        Vec3d vec3d3 = player.getRotationVec(1.0f);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"getViewVector(...)");
        Vec3d look = vec3d3;
        double max = 200.0;
        Vec3d vec3d4 = eye.add(look.x * max, look.y * max, look.z * max);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"add(...)");
        Vec3d end = vec3d4;
        BlockHitResult blockHitResult2 = level.raycast(new RaycastContext(eye, end, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, (Entity)player));
        Intrinsics.checkNotNullExpressionValue((Object)blockHitResult2, (String)"clip(...)");
        BlockHitResult block = blockHitResult2;
        Vec3d vec3d5 = block.getType() != HitResult.Type.MISS ? block.getPos() : end;
        Intrinsics.checkNotNull((Object)vec3d5);
        Vec3d result = vec3d5;
        double closest = eye.distanceTo(result);
        Box box2 = player.getBoundingBox().stretch(look.x * max, look.y * max, look.z * max).expand(1.0);
        Intrinsics.checkNotNullExpressionValue((Object)box2, (String)"inflate(...)");
        for (Entity e2 : level.getOtherEntities((Entity)player, box2, ent -> ent.canHit() && !ent.isSpectator())) {
            double d;
            Optional<Vec3d> clip = e2.getBoundingBox().expand(0.25).raycast(eye, end);
            if (!clip.isPresent() || !((d = eye.distanceTo(clip.get())) < closest)) continue;
            closest = d;
            result = clip.get();
        }
        return result;
    }

    @EventHandler
    private final void onWorldRender(WorldRenderEvent event) {
        if (event.isPortalPass()) {
            return;
        }
        this.labels.clear();
        this.pending.clear();
        CopyOnWriteArrayList<PartyMarker> markers = PartyClient.INSTANCE.markers();
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        if (markers.isEmpty()) {
            return;
        }
        long now = System.currentTimeMillis();
        markers.removeIf(m -> m.expired(now));
        if (markers.isEmpty()) {
            return;
        }
        String string = level.getRegistryKey().getValue().toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        String dim = string;
        Camera camera2 = event.getCamera();
        if (camera2 == null) {
            Camera camera3 = this.mc.gameRenderer.getCamera();
            camera2 = camera3;
            Intrinsics.checkNotNullExpressionValue((Object)camera3, (String)"getMainCamera(...)");
        }
        Camera camera = camera2;
        Vec3d vec3d2 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d cam = vec3d2;
        Quaternionf quaternionf = camera.getRotation();
        Intrinsics.checkNotNullExpressionValue((Object)quaternionf, (String)"rotation(...)");
        Quaternionf camRot = quaternionf;
        Matrix4f posM = event.getPositionMatrix();
        Matrix4f projM = event.getProjectionMatrix();
        float sw = Position.Companion.screenWidth();
        float sh = Position.Companion.screenHeight();
        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate2 = this.mc.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        VertexConsumerProvider.Immediate provider = immediate2;
        RenderLayer renderLayer2 = (this.throughWalls.getValue() ? ClientPipelines.TARGET_ESP : ClientPipelines.TARGET_CHAIN).apply(GLOW_TEXTURE);
        Intrinsics.checkNotNullExpressionValue((Object)renderLayer2, (String)"apply(...)");
        RenderLayer layer = renderLayer2;
        VertexConsumer vertexConsumer2 = provider.getBuffer(layer);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer vc = vertexConsumer2;
        Iterator<PartyMarker> iterator = markers.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<PartyMarker> iterator2 = iterator;
        while (iterator2.hasNext()) {
            PartyMarker m = iterator2.next();
            if (!m.finiteAndSafe() || !Intrinsics.areEqual((Object)dim, (Object)m.dim())) continue;
            Intrinsics.checkNotNull((Object)m);
            float fade = this.renderMarker(stack, vc, camRot, cam, m, now);
            if (fade <= 0.02f) continue;
            this.projectLabel(posM, projM, cam, sw, sh, m, m.pos(), now, (PlayerEntity)player);
        }
        provider.draw(layer);
        this.resolveLabelOverlap();
    }

    private final void resolveLabelOverlap() {
        if (this.pending.size() <= 1) {
            this.labels.addAll((Collection<Label>)this.pending);
            return;
        }
        this.pending.sort(java.util.Comparator.comparingDouble(Label::getDistSqr));
        for (Label c : this.pending) {
            boolean clash = false;
            for (Label kept : this.labels) {
                if (!Party.Companion.overlaps(c, kept, 2.0f)) continue;
                clash = true;
                break;
            }
            if (clash) continue;
            this.labels.add(c);
        }
    }

    private final float renderMarker(MatrixStack stack, VertexConsumer vc, Quaternionf camRot, Vec3d cam, PartyMarker m, long now) {
        float outT;
        float inT = MathHelper.clamp((float)((float)m.ageMs(now) / 350.0f), (float)0.0f, (float)1.0f);
        float fade = inT * (outT = MathHelper.clamp((float)((float)m.remainingMs(now) / 300.0f), (float)0.0f, (float)1.0f));
        if (fade <= 0.01f) {
            return fade;
        }
        float pop = Math.min(Party.Companion.smooth(inT), Party.Companion.smooth(outT));
        float pulse = 0.97f + 0.03f * (float)Math.sin((double)now / 360.0);
        Vec3d p = m.pos();
        stack.push();
        stack.translate(p.x - cam.x, p.y - cam.y, p.z - cam.z);
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        float ringR = 0.78f * pop * pulse;
        float ringY = 0.1f;
        this.ring(vc, pose, m, 56, ringR, 0.4f, 0.16f, fade, ringY);
        this.ring(vc, pose, m, 64, ringR, 0.085f, 0.82f, fade, ringY + 0.004f);
        Party.Companion.skirt(vc, pose, m, 48, ringR, ringY, 0.3f * pop, fade);
        Vector3f camRight = camRot.transform(new Vector3f(1.0f, 0.0f, 0.0f));
        float rxh = camRight.x();
        float rzh = camRight.z();
        float rl = (float)Math.sqrt(rxh * rxh + rzh * rzh);
        if (rl < 1.0E-4f) {
            rxh = 1.0f;
            rzh = 0.0f;
        } else {
            rxh /= rl;
            rzh /= rl;
        }
        float beamH = 1.55f * pop;
        Party.Companion.shaft(vc, pose, m, rxh, rzh, 0.17f * pop, ringY, beamH, fade * 0.38f);
        Party.Companion.shaft(vc, pose, m, rxh, rzh, 0.07f * pop, ringY, beamH * 1.03f, fade * 0.95f);
        stack.pop();
        return fade;
    }

    private final void ring(VertexConsumer vc, MatrixStack.Entry pose, PartyMarker m, int seg, float radius, float half, float peak, float fade, float y) {
        float rIn = Math.max(0.01f, radius - half);
        float rOut = radius + half;
        for (int s = 0; s < seg; ++s) {
            double a0 = Math.toRadians((double)s * (360.0 / (double)seg));
            double a1 = Math.toRadians((double)(s + 1) * (360.0 / (double)seg));
            float c0 = (float)Math.cos(a0);
            float z0 = (float)Math.sin(a0);
            float c1 = (float)Math.cos(a1);
            float z1 = (float)Math.sin(a1);
            int mid = Party.Companion.markerColor(m, (int)((double)s * (360.0 / (double)seg)), fade * peak);
            if (ColorEngine.alpha(mid) <= 1) continue;
            int edge = ColorEngine.multAlpha(mid, 0.0f);
            Party.Companion.band(vc, pose, c0, z0, c1, z1, rIn, radius, y, edge, mid);
            Party.Companion.band(vc, pose, c0, z0, c1, z1, radius, rOut, y, mid, edge);
        }
    }

    private final void projectLabel(Matrix4f posM, Matrix4f projM, Vec3d cam, float sw, float sh, PartyMarker m, Vec3d p, long now, PlayerEntity player) {
        float outT;
        float anchorY = (float)(p.y + 2.1);
        Vector4f v = this.projScratch.set((float)(p.x - cam.x), (float)((double)anchorY - cam.y), (float)(p.z - cam.z), 1.0f);
        posM.transform(v);
        projM.transform(v);
        if (v.w <= 1.0E-4f) {
            return;
        }
        float cx = (v.x / v.w * 0.5f + 0.5f) * sw;
        float tipY = (1.0f - (v.y / v.w * 0.5f + 0.5f)) * sh;
        if (Float.isNaN(cx) || Float.isNaN(tipY)) {
            return;
        }
        double distSqr = player.squaredDistanceTo(p.x, p.y, p.z);
        double distBlocks = Math.sqrt(distSqr);
        float distFade = Party.Companion.smooth(MathHelper.clamp((float)((float)((distBlocks - 16.0) / 6.0)), (float)0.0f, (float)1.0f));
        float inT = MathHelper.clamp((float)((float)m.ageMs(now) / 350.0f), (float)0.0f, (float)1.0f);
        float alpha = inT * (outT = MathHelper.clamp((float)((float)m.remainingMs(now) / 300.0f), (float)0.0f, (float)1.0f)) * distFade;
        if (alpha <= 0.02f) {
            return;
        }
        float scale = 0.62f + 0.38f * Math.min(Party.Companion.smooth(inT), Party.Companion.smooth(outT));
        int meters = (int)Math.round(distBlocks);
        String name = m.from();
        float nameW = Fonts.SEMIBOLD.msdfWidth(name, 6.0f);
        Object[] objectArray = new Object[]{meters};
        float distW = Fonts.MEDIUM.msdfWidth(I18n.tr("%d м", objectArray), 6.5f);
        float boxW = 16.4f + nameW + 4.0f + distW;
        float boxH = Math.max(6.0f, 6.5f) + 6.0f;
        float boxX = cx - boxW * 0.5f;
        float boxY = tipY - 4.5f - boxH;
        boolean rb = m.rainbow();
        int colArgb = m.color() != 0 ? m.color() | 0xFF000000 : -14628609;
        this.pending.add(new Label(cx, boxX, boxY, boxW, boxH, name, meters, alpha, scale, distSqr, rb, colArgb));
    }

    @EventHandler(value=0)
    private final void onHud(HudRenderEvent event) {
        if (this.labels.isEmpty()) {
            return;
        }
        DrawContext g = event.getGraphics();
        for (Label l : this.labels) {
            this.drawLabel(g, l);
        }
    }

    private final void drawLabel(DrawContext g, Label l) {
        boolean scaled;
        boolean bl = scaled = l.getScale() < 0.999f;
        if (scaled) {
            float oy = l.getBoxY() + l.getBoxH() * 0.5f;
            g.getMatrices().pushMatrix();
            g.getMatrices().translate(l.getCx(), oy);
            g.getMatrices().scale(l.getScale(), l.getScale());
            g.getMatrices().translate(-l.getCx(), -oy);
        }
        int bg = ColorEngine.multAlpha(-435023336, l.getAlpha());
        int accent = l.getRainbow() ? Party.Companion.rainbowArgb(0, l.getAlpha()) : ColorEngine.multAlpha(l.getColorArgb(), l.getAlpha());
        int white = ColorEngine.multAlpha(-1, l.getAlpha());
        int distCol = ColorEngine.multAlpha(-4011820, l.getAlpha());
        Render2D.beginFrame(g);
        Render2D.rect(l.getBoxX(), l.getBoxY(), l.getBoxW(), l.getBoxH(), l.getBoxH() * 0.5f, bg);
        Party.Companion.downTriangle(l.getCx(), l.getBoxY() + l.getBoxH() - 0.5f, 8.0f, 4.5f, bg);
        Render2D.flush();
        Render2D.beginFrame(g);
        float midY = l.getBoxY() + l.getBoxH() * 0.5f;
        float x = l.getBoxX() + 5.0f;
        Render2D.circle(x + 1.7f, midY, 1.7f, accent);
        Fonts.SEMIBOLD.msdf(l.getName(), x += 6.4f, midY - 3.0f - 0.5f, 6.0f, white);
        Object[] objectArray = new Object[]{l.getMeters()};
        Fonts.MEDIUM.msdf(I18n.tr("%d м", objectArray), x += Fonts.SEMIBOLD.msdfWidth(l.getName(), 6.0f) + 4.0f, midY - 3.25f - 0.5f, 6.5f, distCol);
        Render2D.flush();
        if (scaled) {
            g.getMatrices().popMatrix();
        }
    }

    private static final Boolean customColor$lambda$0(Party this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean voiceDevice$lambda$0(Party this$0) {
        return this$0.voiceEnabled.getValue();
    }

    private static final Boolean voiceActivation$lambda$0(Party this$0) {
        return this$0.voiceEnabled.getValue();
    }

    private static final Boolean voiceTalk$lambda$0(Party this$0) {
        return this$0.voiceEnabled.getValue() && this$0.voiceActivation.is(V_PTT);
    }

    private static final Boolean voiceThreshold$lambda$0(Party this$0) {
        return this$0.voiceEnabled.getValue() && this$0.voiceActivation.is(V_VAD);
    }

    private static final Boolean voiceVolume$lambda$0(Party this$0) {
        return this$0.voiceEnabled.getValue();
    }

    private static final Boolean voiceAgc$lambda$0(Party this$0) {
        return this$0.voiceEnabled.getValue();
    }

    private static final Boolean voiceMicGain$lambda$0(Party this$0) {
        return this$0.voiceEnabled.getValue() && !this$0.voiceAgc.getValue();
    }

    private static final Boolean voiceDenoise$lambda$0(Party this$0) {
        return this$0.voiceEnabled.getValue();
    }

    private static final Boolean voiceMute$lambda$0(Party this$0) {
        return this$0.voiceEnabled.getValue();
    }

    private static final void _init_$lambda$0(Party this$0) {
        this$0.pushColor();
    }

    private static final void _init_$lambda$1(Party this$0) {
        this$0.pushColor();
    }

    private static final void _init_$lambda$2(Party this$0) {
        MicCapture.setDevice(this$0.voiceDevice.getSelected());
    }

    private static final void _init_$lambda$3(Party this$0) {
        this$0.onVoiceToggle();
    }

    private static final void _init_$lambda$4(Party this$0) {
        this$0.pushVoice();
    }

    private static final void _init_$lambda$5(Party this$0) {
        this$0.pushVoice();
    }

    private static final void _init_$lambda$6(Party this$0) {
        this$0.pushVoice();
    }

    private static final void _init_$lambda$7(Party this$0) {
        this$0.pushVoice();
    }

    private static final void _init_$lambda$8(Party this$0) {
        this$0.pushVoice();
    }

    private static final void _init_$lambda$9(Party this$0) {
        this$0.pushVoice();
    }

    private static final void _init_$lambda$10(Party this$0) {
        this$0.pushVoice();
    }

    private static final void onEnable$lambda$0(Party this$0, String from, double x, double y, double z, double dx, double dy, double dz, String dim) {
        Intrinsics.checkNotNullParameter((Object)from, (String)"from");
        Intrinsics.checkNotNullParameter((Object)dim, (String)"dim");
        this$0.onPartySpit(from, x, y, z, dx, dy, dz, dim);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static final void requestDeviceScan$lambda$0(Party this$0) {
        try {
            this$0.pendingDevices.set(MicCapture.devices());
        }
        catch (Throwable throwable) {
        }
        finally {
            this$0.deviceScanRunning.set(false);
        }
    }

    private static final boolean onTick$lambda$0(LlamaSpitEntity spit) {
        boolean bl;
        Intrinsics.checkNotNullParameter((Object)spit, (String)"spit");
        if (spit.isRemoved() || spit.age > 100) {
            spit.discard();
            bl = true;
        } else {
            bl = false;
        }
        return bl;
    }

    private static final boolean onTick$lambda$1(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final boolean raycastTarget$lambda$0(Entity ent) {
        Intrinsics.checkNotNullParameter((Object)ent, (String)"ent");
        return ent.canHit() && !ent.isSpectator();
    }

    private static final boolean raycastTarget$lambda$1(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final boolean onWorldRender$lambda$0(long $now, PartyMarker m) {
        return m.expired($now);
    }

    private static final boolean onWorldRender$lambda$1(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final int resolveLabelOverlap$lambda$0(Label a, Label b) {
        return Double.compare(a.getDistSqr(), b.getDistSqr());
    }

    private static final int resolveLabelOverlap$lambda$1(Function2 $tmp0, Object p0, Object p1) {
        return ((Number)$tmp0.invoke(p0, p1)).intValue();
    }

    static {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"textures/particle/glow.png");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        GLOW_TEXTURE = identifier2;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u000e\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJg\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJO\u0010#\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b#\u0010$JW\u0010)\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b)\u0010*J7\u00100\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u00072\u0006\u0010-\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b0\u00101J\u0017\u00103\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b3\u00104J'\u00106\u001a\u00020\u00172\u0006\u0010\b\u001a\u00020\u001d2\u0006\u00105\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b6\u00107J\u001f\u00108\u001a\u00020\u00172\u0006\u00105\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b8\u00109R\u0014\u0010;\u001a\u00020:8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b;\u0010<R\u0014\u0010>\u001a\u00020=8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020=8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u0010?R\u0014\u0010A\u001a\u00020=8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bA\u0010?R\u0014\u0010C\u001a\u00020B8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bC\u0010DR\u0014\u0010E\u001a\u00020B8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bE\u0010DR\u0014\u0010F\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bF\u0010GR\u0014\u0010H\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bH\u0010IR\u0014\u0010J\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bJ\u0010IR\u0014\u0010K\u001a\u00020=8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bK\u0010?R\u0014\u0010L\u001a\u00020=8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bL\u0010?R\u0014\u0010N\u001a\u00020M8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bN\u0010OR\u0014\u0010P\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bP\u0010IR\u0014\u0010Q\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bQ\u0010IR\u0014\u0010R\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bR\u0010IR\u0014\u0010S\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bS\u0010IR\u0014\u0010T\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bT\u0010IR\u0014\u0010U\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bU\u0010IR\u0014\u0010V\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bV\u0010IR\u0014\u0010W\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bW\u0010IR\u0014\u0010X\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bX\u0010IR\u0014\u0010Y\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bY\u0010GR\u0014\u0010Z\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bZ\u0010G\u00a8\u0006["}, d2={"Lrtx/kimiko/api/modules/impl/Utils/Party.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Utils/Party$Label;", "a", "b", "", "m", "", "overlaps", "(Lrtx/kimiko/api/modules/impl/Utils/Party$Label;Lrtx/kimiko/api/modules/impl/Utils/Party$Label;F)Z", "Lnet/minecraft/VertexConsumer;", "vc", "Lnet/minecraft/MatrixStack$Entry;", "pose", "c0", "z0", "c1", "z1", "rA", "rB", "y", "", "cA", "cB", "", "band", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;FFFFFFFII)V", "Lrtx/kimiko/api/party/PartyMarker;", "seg", "radius", "y0", "height", "fade", "skirt", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lrtx/kimiko/api/party/PartyMarker;IFFFF)V", "rxh", "rzh", "halfW", "alpha", "shaft", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lrtx/kimiko/api/party/PartyMarker;FFFFFF)V", "cx", "topY", "w", "h", "color", "downTriangle", "(FFFFI)V", "value", "smooth", "(F)F", "index", "markerColor", "(Lrtx/kimiko/api/party/PartyMarker;IF)I", "rainbowArgb", "(IF)I", "Lnet/minecraft/Identifier;", "GLOW_TEXTURE", "Lnet/minecraft/Identifier;", "", "COLOR_RAINBOW", "Ljava/lang/String;", "COLOR_CLIENT", "COLOR_CUSTOM", "", "LABEL_NEAR", "D", "LABEL_FULL", "LABEL_BG", "I", "IN_MS", "F", "OUT_MS", "V_PTT", "V_VAD", "", "DEVICE_SCAN_MS", "J", "L_NAME_SIZE", "L_DIST_SIZE", "L_PAD_X", "L_PAD_Y", "L_GAP", "L_DOT_R", "L_DOT_GAP", "L_ARROW_W", "L_ARROW_H", "L_DIST_COLOR", "DEFAULT_MARKER_COLOR", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final boolean overlaps(Label a, Label b, float m) {
            return a.getBoxX() - m < b.getBoxX() + b.getBoxW() + m && a.getBoxX() + a.getBoxW() + m > b.getBoxX() - m && a.getBoxY() - m < b.getBoxY() + b.getBoxH() + m && a.getBoxY() + a.getBoxH() + m > b.getBoxY() - m;
        }

        private final void band(VertexConsumer vc, MatrixStack.Entry pose, float c0, float z0, float c1, float z1, float rA, float rB, float y, int cA, int cB) {
            vc.vertex(pose, c0 * rA, y, z0 * rA).texture(0.5f, 0.5f).color(cA);
            vc.vertex(pose, c1 * rA, y, z1 * rA).texture(0.5f, 0.5f).color(cA);
            vc.vertex(pose, c1 * rB, y, z1 * rB).texture(0.5f, 0.5f).color(cB);
            vc.vertex(pose, c0 * rB, y, z0 * rB).texture(0.5f, 0.5f).color(cB);
        }

        private final void skirt(VertexConsumer vc, MatrixStack.Entry pose, PartyMarker m, int seg, float radius, float y0, float height, float fade) {
            int base = this.markerColor(m, 0, fade * 0.4f);
            if (ColorEngine.alpha(base) <= 1) {
                return;
            }
            float yTop = y0 + height;
            for (int s = 0; s < seg; ++s) {
                double a0 = Math.toRadians((double)s * (360.0 / (double)seg));
                double a1 = Math.toRadians((double)(s + 1) * (360.0 / (double)seg));
                float x0 = (float)Math.cos(a0) * radius;
                float z0 = (float)Math.sin(a0) * radius;
                float x1 = (float)Math.cos(a1) * radius;
                float z1 = (float)Math.sin(a1) * radius;
                int b = this.markerColor(m, (int)((double)s * (360.0 / (double)seg)), fade * 0.4f);
                int t = ColorEngine.multAlpha(b, 0.0f);
                vc.vertex(pose, x0, y0, z0).texture(0.5f, 0.5f).color(b);
                vc.vertex(pose, x1, y0, z1).texture(0.5f, 0.5f).color(b);
                vc.vertex(pose, x1, yTop, z1).texture(0.5f, 0.5f).color(t);
                vc.vertex(pose, x0, yTop, z0).texture(0.5f, 0.5f).color(t);
            }
        }

        private final void shaft(VertexConsumer vc, MatrixStack.Entry pose, PartyMarker m, float rxh, float rzh, float halfW, float y0, float height, float alpha) {
            int base = this.markerColor(m, 0, alpha);
            if (ColorEngine.alpha(base) <= 1) {
                return;
            }
            int top = ColorEngine.multAlpha(base, 0.0f);
            float rX = rxh * halfW;
            float rZ = rzh * halfW;
            float yTop = y0 + height;
            vc.vertex(pose, -rX, y0, -rZ).texture(0.0f, 0.5f).color(base);
            vc.vertex(pose, rX, y0, rZ).texture(1.0f, 0.5f).color(base);
            vc.vertex(pose, rX, yTop, rZ).texture(1.0f, 0.5f).color(top);
            vc.vertex(pose, -rX, yTop, -rZ).texture(0.0f, 0.5f).color(top);
        }

        private final void downTriangle(float cx, float topY, float w, float h, int color) {
            int steps = Math.max(4, Math.round(h * 2.0f));
            for (int i = 0; i < steps; ++i) {
                float t = (float)i / (float)steps;
                float ww = w * (1.0f - t);
                Render2D.rect(cx - ww * 0.5f, topY + h * t, ww, h / (float)steps + 0.7f, color);
            }
        }

        private final float smooth(float value) {
            float t = MathHelper.clamp((float)value, (float)0.0f, (float)1.0f);
            return t * t * t * (t * (t * 6.0f - 15.0f) + 10.0f);
        }

        private final int markerColor(PartyMarker m, int index, float alpha) {
            if (m.rainbow()) {
                return this.rainbowArgb(index, alpha);
            }
            int base = m.color() != 0 ? m.color() | 0xFF000000 : -14628609;
            return ColorEngine.multAlpha(base, alpha);
        }

        private final int rainbowArgb(int index, float alpha) {
            int angle = (int)((System.currentTimeMillis() / 8L + (long)index) % 360L);
            int rgb = ColorEngine.rainbow(angle, 1.0f, 1.0f);
            return ColorEngine.rgba(rgb >>> 16 & 0xFF, rgb >>> 8 & 0xFF, rgb & 0xFF, Math.round(MathHelper.clamp((float)alpha, (float)0.0f, (float)1.0f) * 255.0f));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001b\b\u0002\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0012\u001a\u00020\n\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0015\u001a\u0004\b\u0018\u0010\u0017R\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0015\u001a\u0004\b\u0019\u0010\u0017R\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0015\u001a\u0004\b\u001a\u0010\u0017R\u0017\u0010\u0007\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0015\u001a\u0004\b\u001b\u0010\u0017R\u0017\u0010\t\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u000b\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010\u001f\u001a\u0004\b \u0010!R\u0017\u0010\f\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\f\u0010\u0015\u001a\u0004\b\"\u0010\u0017R\u0017\u0010\r\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\r\u0010\u0015\u001a\u0004\b#\u0010\u0017R\u0017\u0010\u000f\u001a\u00020\u000e8\u0006\u00a2\u0006\f\n\u0004\b\u000f\u0010$\u001a\u0004\b%\u0010&R\u0017\u0010\u0011\u001a\u00020\u00108\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010'\u001a\u0004\b(\u0010)R\u0017\u0010\u0012\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\u0012\u0010\u001f\u001a\u0004\b*\u0010!\u00a8\u0006+"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/Party$Label;", "", "", "cx", "boxX", "boxY", "boxW", "boxH", "", "name", "", "meters", "alpha", "scale", "", "distSqr", "", "rainbow", "colorArgb", "<init>", "(FFFFFLjava/lang/String;IFFDZI)V", "F", "getCx", "()F", "getBoxX", "getBoxY", "getBoxW", "getBoxH", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "I", "getMeters", "()I", "getAlpha", "getScale", "D", "getDistSqr", "()D", "Z", "getRainbow", "()Z", "getColorArgb", "rtx.kimiko:kimiko"})
    private static final class Label {
        private final float cx;
        private final float boxX;
        private final float boxY;
        private final float boxW;
        private final float boxH;
        @NotNull
        private final String name;
        private final int meters;
        private final float alpha;
        private final float scale;
        private final double distSqr;
        private final boolean rainbow;
        private final int colorArgb;

        public Label(float cx, float boxX, float boxY, float boxW, float boxH, @NotNull String name, int meters, float alpha, float scale, double distSqr, boolean rainbow, int colorArgb) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            this.cx = cx;
            this.boxX = boxX;
            this.boxY = boxY;
            this.boxW = boxW;
            this.boxH = boxH;
            this.name = name;
            this.meters = meters;
            this.alpha = alpha;
            this.scale = scale;
            this.distSqr = distSqr;
            this.rainbow = rainbow;
            this.colorArgb = colorArgb;
        }

        public final float getCx() {
            return this.cx;
        }

        public final float getBoxX() {
            return this.boxX;
        }

        public final float getBoxY() {
            return this.boxY;
        }

        public final float getBoxW() {
            return this.boxW;
        }

        public final float getBoxH() {
            return this.boxH;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        public final int getMeters() {
            return this.meters;
        }

        public final float getAlpha() {
            return this.alpha;
        }

        public final float getScale() {
            return this.scale;
        }

        public final double getDistSqr() {
            return this.distSqr;
        }

        public final boolean getRainbow() {
            return this.rainbow;
        }

        public final int getColorArgb() {
            return this.colorArgb;
        }
    }
}

