/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.client.gui.render.state.GuiRenderState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.util.hit.HitResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.hit.EntityHitResult
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Vector4f
 *  rtx.kimiko.utils.render.modules.post.targetcircle.TargetCircleBloomRenderer
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.awt.Color;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector4f;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.player.AttackEntityEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.entity.EntityVisibility;
import rtx.kimiko.utils.render.modules.post.targetcircle.TargetCircleBloomRenderer;
import rtx.kimiko.utils.render.modules.targetesp.AuraGlowTargetEspRenderer;
import rtx.kimiko.utils.render.modules.targetesp.MarkerRenderState;
import rtx.kimiko.utils.render.modules.targetesp.TargetCircleRenderer;
import rtx.kimiko.utils.render.modules.targetesp.TargetEspColorProvider;
import rtx.kimiko.utils.render.modules.targetesp.TargetEspMath;
import rtx.kimiko.utils.render.modules.targetesp.TargetEspRenderContext;
import rtx.kimiko.utils.render.modules.targetesp.TargetWarheadSystem;
import rtx.kimiko.utils.render.render2d.ClientPalette;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Feature(value={"targetesp"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00aa\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 {2\u00020\u0001:\u0001{B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0003b\u0002\b\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000eH\u0003b\u0002\b\u000b\u00a2\u0006\u0004\b\u000f\u0010\u0010JG\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001bH\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ9\u0010!\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010 \u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b!\u0010\"J\u001b\u0010$\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020#H\u0003b\u0002\b\u000b\u00a2\u0006\u0004\b$\u0010%J\u0017\u0010(\u001a\u00020&2\u0006\u0010'\u001a\u00020&H\u0002\u00a2\u0006\u0004\b(\u0010)J7\u0010*\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b*\u0010+J\u0011\u0010,\u001a\u0004\u0018\u00010\u0015H\u0002\u00a2\u0006\u0004\b,\u0010-J\u0017\u0010.\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\b.\u0010/J\u001f\u00101\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u00100\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b1\u00102J\u000f\u00103\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b3\u0010\u0003R\u0014\u00105\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00106R\u0014\u00108\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010;\u001a\u00020:8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b;\u0010<R\u0014\u0010>\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b@\u0010?R\u0014\u0010A\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bA\u0010?R\u0014\u0010B\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010?R\u0014\u0010C\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bC\u0010?R\u0014\u0010D\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u0010?R\u0014\u0010E\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010?R\u0014\u0010F\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bF\u0010?R\u0014\u0010G\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010?R\u0014\u0010H\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u0010?R\u0014\u0010I\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u0010?R\u0014\u0010J\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010?R\u0014\u0010K\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bK\u0010?R\u0014\u0010L\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u00106R\u0014\u0010M\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bM\u00109R\u0014\u0010N\u001a\u00020:8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bN\u0010<R\u0014\u0010P\u001a\u00020O8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bP\u0010QR\u0014\u0010R\u001a\u00020O8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bR\u0010QR\u0014\u0010S\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bS\u00106R\u0014\u0010T\u001a\u00020:8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bT\u0010<R\u0014\u0010U\u001a\u00020:8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bU\u0010<R\u0014\u0010V\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bV\u0010?R\u0014\u0010W\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bW\u0010?R\u0014\u0010Y\u001a\u00020X8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bY\u0010ZR\u0014\u0010\\\u001a\u00020[8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\\\u0010]R\u0016\u0010^\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b^\u0010_R\u0016\u0010`\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b`\u0010_R\u0016\u0010a\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\ba\u0010bR\u0016\u0010c\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010bR\u0016\u0010d\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bd\u0010_R\u0016\u0010e\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\be\u0010_R\u0018\u0010f\u001a\u0004\u0018\u00010\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bf\u0010gR\u0018\u0010h\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bh\u0010iR\u0018\u0010j\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bj\u0010iR\u0016\u0010k\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bk\u0010lR\u0016\u0010m\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bm\u0010bR\u0016\u0010n\u001a\u00020&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bn\u0010oR\u0016\u0010p\u001a\u00020&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bp\u0010oR\u0016\u0010q\u001a\u00020&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bq\u0010oR\u0016\u0010r\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\br\u0010bR\u0016\u0010s\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bs\u0010_R\u0016\u0010t\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bt\u0010_R\u0016\u0010u\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bu\u0010_R\u0016\u0010v\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bv\u0010_R\u0016\u0010w\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bw\u0010lR\u0016\u0010x\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bx\u0010lR\u0016\u0010y\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\by\u0010_R\u0016\u0010z\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bz\u0010_\u00ca\u0001\u0010\b|\u0012\f\b}\u0012\b\b\fJ\u0004\b\b(~\u00a8\u0006\u007f"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/TargetESP;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "onDisable", "", "fadeOutSeconds", "()F", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "Lrtx/kimiko/api/events/impl/player/AttackEntityEvent;", "onAttack", "(Lrtx/kimiko/api/events/impl/player/AttackEntityEvent;)V", "Lnet/minecraft/MatrixStack;", "stack", "Lnet/minecraft/Vec3d;", "cameraPos", "Lnet/minecraft/LivingEntity;", "target", "deltaFrames", "sPos", "", "now", "", "visible", "renderWarheads", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/Vec3d;Lnet/minecraft/LivingEntity;FLnet/minecraft/Vec3d;JZ)V", "Lorg/joml/Matrix4f;", "projection", "projectMarker", "(Lnet/minecraft/MatrixStack;Lorg/joml/Matrix4f;Lnet/minecraft/Vec3d;Lnet/minecraft/LivingEntity;Lnet/minecraft/Vec3d;)V", "Lrtx/kimiko/api/events/impl/render/HudRenderEvent;", "onHudMarker", "(Lrtx/kimiko/api/events/impl/render/HudRenderEvent;)V", "", "index", "markerColor", "(I)I", "renderCircle", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/Vec3d;Lnet/minecraft/LivingEntity;FLnet/minecraft/Vec3d;)V", "hoveredTarget", "()Lnet/minecraft/LivingEntity;", "frameDelta", "(J)F", "rawAlpha", "resolveColor", "(IF)I", "clearRenderState", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "styleSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "styleMode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "markerAdditive", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "circleRadius", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "circleTilt", "circleSpeed", "circleThickness", "circleGlow", "warheadCount", "warheadDistance", "warheadSize", "warheadSpin", "warheadSpeed", "warheadShards", "warheadTint", "warheadDistort", "colorsSeparator", "colorMode", "useSecondColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customSecondColor", "displaySeparator", "onlyOnHit", "throughWalls", "circleHeight", "brightness", "Lrtx/kimiko/utils/render/modules/targetesp/TargetWarheadSystem;", "warheads", "Lrtx/kimiko/utils/render/modules/targetesp/TargetWarheadSystem;", "Lrtx/kimiko/utils/render/modules/targetesp/TargetWarheadSystem$Params;", "warheadParams", "Lrtx/kimiko/utils/render/modules/targetesp/TargetWarheadSystem$Params;", "markerScreenX", "F", "markerScreenY", "markerProjected", "Z", "markerSmoothed", "markerHurt", "markerFade", "renderedTarget", "Lnet/minecraft/LivingEntity;", "smoothedPos", "Lnet/minecraft/Vec3d;", "transitionStartPos", "switchStartMs", "J", "switching", "currentTargetId", "I", "hitTrackedId", "lastHurtTime", "targetHit", "alpha", "moduleFade", "hurtProgress", "chainImpactProgress", "lastSeenMs", "lastFrameMillis", "spinAngle", "spinSpeedCurrent", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "targetesp", "rtx.kimiko:kimiko"})
public final class TargetESP
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting styleSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Стиль"));
    @NotNull
    private final ModeSetting styleMode;
    @NotNull
    private final BooleanSetting markerAdditive;
    @NotNull
    private final SliderSetting circleRadius;
    @NotNull
    private final SliderSetting circleTilt;
    @NotNull
    private final SliderSetting circleSpeed;
    @NotNull
    private final SliderSetting circleThickness;
    @NotNull
    private final SliderSetting circleGlow;
    @NotNull
    private final SliderSetting warheadCount;
    @NotNull
    private final SliderSetting warheadDistance;
    @NotNull
    private final SliderSetting warheadSize;
    @NotNull
    private final SliderSetting warheadSpin;
    @NotNull
    private final SliderSetting warheadSpeed;
    @NotNull
    private final SliderSetting warheadShards;
    @NotNull
    private final SliderSetting warheadTint;
    @NotNull
    private final SliderSetting warheadDistort;
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
    private final SeparatorSetting displaySeparator;
    @NotNull
    private final BooleanSetting onlyOnHit;
    @NotNull
    private final BooleanSetting throughWalls;
    @NotNull
    private final SliderSetting circleHeight;
    @NotNull
    private final SliderSetting brightness;
    @NotNull
    private final TargetWarheadSystem warheads;
    @NotNull
    private final TargetWarheadSystem.Params warheadParams;
    private float markerScreenX;
    private float markerScreenY;
    private boolean markerProjected;
    private boolean markerSmoothed;
    private float markerHurt;
    private float markerFade;
    @Nullable
    private LivingEntity renderedTarget;
    @Nullable
    private Vec3d smoothedPos;
    @Nullable
    private Vec3d transitionStartPos;
    private long switchStartMs;
    private boolean switching;
    private int currentTargetId;
    private int hitTrackedId;
    private int lastHurtTime;
    private boolean targetHit;
    private float alpha;
    private float moduleFade;
    private float hurtProgress;
    private float chainImpactProgress;
    private long lastSeenMs;
    private long lastFrameMillis;
    private float spinAngle;
    private float spinSpeedCurrent;
    private static final int CLIENT_COLOR_FIRST = ColorEngine.rgba(127, 242, 255, 255);
    private static final int CLIENT_COLOR_SECOND = ColorEngine.rgba(255, 50, 150, 255);
    private static final int DARK_SECOND_COLOR = new Color(16, 16, 16, 75).getRGB();
    @NotNull
    private static final String COLOR_RAINBOW = "Радуга";
    @NotNull
    private static final String COLOR_CLIENT = "Клиент";
    @NotNull
    private static final String COLOR_CUSTOM = "Свой";
    @NotNull
    private static final String STYLE_AURA = "Аура";
    @NotNull
    private static final String STYLE_CIRCLE = "Окружность";
    @NotNull
    private static final String STYLE_IMAGE = "2D Картинка";
    @NotNull
    private static final String STYLE_WARHEADS = "Боеголовки";
    @NotNull
    private static final String MARKER_TEXTURE = "kimiko:textures/targetesp/marker.png";
    private static final float MARKER_SIZE = 100.0f;
    private static final long HIDE_DELAY_MS = 500L;
    private static final long SWITCH_BLEND_MS = 250L;
    @JvmField
    @Nullable
    public static TargetESP INSTANCE;

    public TargetESP() {
        super("Target ESP", "Подсвечивает последнюю атакованную цель.", Category.VISUALS);
        String[] stringArray = new String[]{STYLE_AURA, STYLE_CIRCLE, STYLE_IMAGE, STYLE_WARHEADS};
        this.styleMode = (ModeSetting)this.register((Setting)new ModeSetting("Стиль", "Внешний вид подсветки цели.", STYLE_AURA, stringArray));
        this.markerAdditive = (BooleanSetting)this.register((Setting)new BooleanSetting("Свечение картинки", "Складывает картинку с фоном: чёрный не рисуется, яркие места светятся.", true).visible(() -> TargetESP.markerAdditive$lambda$0(this)));
        this.circleRadius = (SliderSetting)this.register((Setting)new SliderSetting("Радиус", "Радиус круга.").range(1.3f, 1.5f).increment(0.01f).setValue(1.4f).visible(() -> TargetESP.circleRadius$lambda$0(this)));
        this.circleTilt = (SliderSetting)this.register((Setting)new SliderSetting("Наклон", "Наклон плоскости круга, градусы.").range(20.0f, 60.0f).increment(1.0f).setValue(40.0f).visible(() -> TargetESP.circleTilt$lambda$0(this)));
        this.circleSpeed = (SliderSetting)this.register((Setting)new SliderSetting("Скорость", "Скорость вращения круга.").range(1.0f, 3.0f).increment(0.1f).setValue(2.0f).visible(() -> TargetESP.circleSpeed$lambda$0(this)));
        this.circleThickness = (SliderSetting)this.register((Setting)new SliderSetting("Толщина", "Толщина полумесяцев.").range(1.5f, 2.5f).increment(0.05f).setValue(1.75f).visible(() -> TargetESP.circleThickness$lambda$0(this)));
        this.circleGlow = (SliderSetting)this.register((Setting)new SliderSetting("Сила свечения", "Сила блум-свечения.").range(1.0f, 3.0f).increment(0.1f).setValue(2.0f).visible(() -> TargetESP.circleGlow$lambda$0(this)));
        this.warheadCount = (SliderSetting)this.register((Setting)new SliderSetting("Количество", "Сколько боеголовок кружит вокруг цели.").range(5, 10).setValue(6.0f).visible(() -> TargetESP.warheadCount$lambda$0(this)));
        this.warheadDistance = (SliderSetting)this.register((Setting)new SliderSetting("Дистанция", "Отступ орбиты боеголовок от тела цели.").range(0.1f, 1.0f).increment(0.05f).setValue(0.35f).visible(() -> TargetESP.warheadDistance$lambda$0(this)));
        this.warheadSize = (SliderSetting)this.register((Setting)new SliderSetting("Размер", "Длина одной боеголовки.").range(0.3f, 0.45f).increment(0.01f).setValue(0.4f).visible(() -> TargetESP.warheadSize$lambda$0(this)));
        this.warheadSpin = (SliderSetting)this.register((Setting)new SliderSetting("Вращение", "Скорость полёта боеголовок по орбите.").range(0.3f, 0.7f).increment(0.05f).setValue(0.5f).visible(() -> TargetESP.warheadSpin$lambda$0(this)));
        this.warheadSpeed = (SliderSetting)this.register((Setting)new SliderSetting("Скорость полёта", "Скорость боеголовки при ударе, блоков в секунду.").range(8.0f, 40.0f).increment(1.0f).setValue(20.0f).visible(() -> TargetESP.warheadSpeed$lambda$0(this)));
        this.warheadShards = (SliderSetting)this.register((Setting)new SliderSetting("Осколки", "Сколько осколков даёт разбитая боеголовка.").range(8, 32).setValue(18.0f).visible(() -> TargetESP.warheadShards$lambda$0(this)));
        this.warheadTint = (SliderSetting)this.register((Setting)new SliderSetting("Подкрашивание", "Сила подкраски стекла цветом (0 = чистое стекло).").range(0.35f, 0.65f).increment(0.01f).setValue(0.45f).visible(() -> TargetESP.warheadTint$lambda$0(this)));
        this.warheadDistort = (SliderSetting)this.register((Setting)new SliderSetting("Искривление", "Сила преломления мира внутри стекла.").range(0.0f, 3.0f).increment(0.1f).setValue(1.2f).visible(() -> TargetESP.warheadDistort$lambda$0(this)));
        this.colorsSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Цвета"));
        stringArray = new String[]{COLOR_RAINBOW, COLOR_CLIENT, COLOR_CUSTOM};
        this.colorMode = (ModeSetting)this.register((Setting)new ModeSetting("Режим цвета", "Режим цвета подсветки цели.", COLOR_RAINBOW, stringArray));
        this.useSecondColor = (BooleanSetting)this.register((Setting)new BooleanSetting("Второй цвет", "Использовать второй свой цвет.", false));
        this.customColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет", "Основной цвет.", new Color(255, 255, 255, 255)));
        this.customSecondColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет 2", "Второй цвет.", new Color(ColorEngine.lerpColor(-1, DARK_SECOND_COLOR, 0.7f), true)));
        this.displaySeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Отображение"));
        this.onlyOnHit = (BooleanSetting)this.register((Setting)new BooleanSetting("Только при ударе", "Показывать подсветку цели только после нанесённого удара.", false));
        this.throughWalls = (BooleanSetting)this.register((Setting)new BooleanSetting("Сквозь стены", "Видеть подсветку цели сквозь стены.", false));
        this.circleHeight = (SliderSetting)this.register((Setting)new SliderSetting("Высота круга", "Высота светящегося кольца.").range(1.0f, 1.75f).increment(0.05f).setValue(1.0f).visible(() -> TargetESP.circleHeight$lambda$0(this)));
        this.brightness = (SliderSetting)this.register((Setting)new SliderSetting("Яркость", "Яркость свечения.").range(0.1f, 0.25f).increment(0.01f).setValue(0.15f).visible(() -> TargetESP.brightness$lambda$0(this)));
        this.warheads = new TargetWarheadSystem();
        this.warheadParams = new TargetWarheadSystem.Params();
        this.currentTargetId = Integer.MIN_VALUE;
        this.hitTrackedId = Integer.MIN_VALUE;
        this.moduleFade = 1.0f;
        this.lastFrameMillis = System.currentTimeMillis();
        this.spinSpeedCurrent = Float.NaN;
        INSTANCE = this;
        this.useSecondColor.visibleWhen(() -> TargetESP._init_$lambda$0(this));
        this.customColor.visibleWhen(() -> TargetESP._init_$lambda$1(this));
        this.customSecondColor.visibleWhen(() -> TargetESP._init_$lambda$2(this));
    }

    @Override
    protected void onDisable() {
        this.clearRenderState();
        TargetCircleBloomRenderer.clear();
    }

    @Override
    public float fadeOutSeconds() {
        return 0.5f;
    }

    @EventHandler
    private final void onWorldRender(WorldRenderEvent event) {
        LivingEntity target;
        LivingEntity hitEntity;
        LivingEntity livingEntity2;
        if (event.isPortalPass()) {
            return;
        }
        this.moduleFade = this.visualAlpha();
        if (this.moduleFade <= 0.001f) {
            return;
        }
        long now = System.currentTimeMillis();
        float deltaFrames = this.frameDelta(now);
        float partialTicks = event.getPartialTicks();
        LivingEntity liveTarget = this.hoveredTarget();
        if (liveTarget != null) {
            this.renderedTarget = liveTarget;
            this.lastSeenMs = now;
        }
        if ((livingEntity2 = liveTarget) == null) {
            livingEntity2 = this.renderedTarget;
        }
        if ((hitEntity = livingEntity2) != null) {
            if (hitEntity.getId() != this.hitTrackedId) {
                this.hitTrackedId = hitEntity.getId();
                this.targetHit = false;
                this.lastHurtTime = hitEntity.hurtTime;
            }
            if (hitEntity.hurtTime > this.lastHurtTime) {
                this.targetHit = true;
            }
            this.lastHurtTime = hitEntity.hurtTime;
        }
        boolean valid = (target = this.renderedTarget) != null && target.isAlive() && target.getHealth() > 0.0f && !EntityVisibility.isHidden((Entity)target);
        boolean hitGate = !this.onlyOnHit.getValue() || this.targetHit;
        boolean keepVisible = valid && hitGate && (liveTarget != null || now - this.lastSeenMs <= 500L);
        float alphaTarget = keepVisible ? 1.0f : 0.0f;
        float follow = Math.min(1.0f, (keepVisible ? 0.16f : 0.12f) * deltaFrames);
        this.alpha += (alphaTarget - this.alpha) * follow;
        if (Math.abs(alphaTarget - this.alpha) < 0.002f) {
            this.alpha = alphaTarget;
        }
        float fadeStep = deltaFrames * 16.666666f / 500.0f;
        this.markerFade = MathHelper.clamp((float)(this.markerFade + (keepVisible ? fadeStep : -fadeStep)), (float)0.0f, (float)1.0f);
        if (this.alpha <= 0.01f && this.markerFade <= 0.001f && !keepVisible && (!this.styleMode.is(STYLE_WARHEADS) || this.warheads.isIdle())) {
            this.clearRenderState();
            return;
        }
        if (valid && target != null) {
            Vec3d vec3d2 = target.getLerpedPos(partialTicks);
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getPosition(...)");
            Vec3d livePos = vec3d2;
            if (target.getId() != this.currentTargetId) {
                if (this.smoothedPos != null && this.currentTargetId != Integer.MIN_VALUE) {
                    this.transitionStartPos = this.smoothedPos;
                    this.switchStartMs = now;
                    this.switching = true;
                } else {
                    this.transitionStartPos = livePos;
                    this.switching = false;
                }
                this.currentTargetId = target.getId();
            }
            if (this.switching && this.transitionStartPos != null) {
                float progress = MathHelper.clamp((float)((float)(now - this.switchStartMs) / 250.0f), (float)0.0f, (float)1.0f);
                float eased = TargetEspMath.easeInOutQuad(progress);
                Vec3d vec3d3 = this.transitionStartPos;
                Intrinsics.checkNotNull((Object)vec3d3);
                this.smoothedPos = vec3d3.lerp(livePos, (double)eased);
                if (progress >= 1.0f) {
                    this.switching = false;
                    this.transitionStartPos = null;
                }
            } else {
                this.smoothedPos = livePos;
            }
        }
        Vec3d sPos = this.smoothedPos;
        if (target == null || sPos == null) {
            return;
        }
        this.hurtProgress = target.hurtTime > 0 ? (float)target.hurtTime / 10.0f : Math.max(0.0f, this.hurtProgress - 0.1f * deltaFrames);
        float impactTarget = target.hurtTime > 0 ? 1.0f : 0.0f;
        float impactStep = (impactTarget > this.chainImpactProgress ? 0.3f : 0.1f) * deltaFrames;
        this.chainImpactProgress = TargetEspMath.approach(this.chainImpactProgress, impactTarget, impactStep);
        Camera camera2 = event.getCamera() == null ? this.mc.gameRenderer.getCamera() : event.getCamera();
        Intrinsics.checkNotNull((Object)camera2);
        Camera camera = camera2;
        Vec3d vec3d4 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"position(...)");
        Vec3d cameraPos = vec3d4;
        MatrixStack stack = event.getStack();
        if (this.styleMode.is(STYLE_CIRCLE)) {
            this.renderCircle(stack, cameraPos, target, deltaFrames, sPos);
            return;
        }
        if (this.styleMode.is(STYLE_IMAGE)) {
            this.projectMarker(stack, event.getProjectionMatrix(), cameraPos, target, sPos);
            return;
        }
        if (this.styleMode.is(STYLE_WARHEADS)) {
            this.renderWarheads(stack, cameraPos, target, deltaFrames, sPos, now, keepVisible);
            return;
        }
        VertexConsumerProvider.Immediate immediate2 = this.mc.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        VertexConsumerProvider.Immediate provider = immediate2;
        stack.push();
        stack.translate(sPos.x - cameraPos.x, sPos.y - cameraPos.y, sPos.z - cameraPos.z);
        TargetEspRenderContext context = new TargetEspRenderContext(target, this.alpha, partialTicks, now, -1, -1, this.hurtProgress, this.chainImpactProgress, this.circleHeight.getFloat(), this.brightness.getFloat(), this.throughWalls.getValue());
        AuraGlowTargetEspRenderer.render(stack, provider, context, (arg_0, arg_1) -> TargetESP.onWorldRender$lambda$0(this, arg_0, arg_1));
        AuraGlowTargetEspRenderer.endBatch(provider, this.throughWalls.getValue());
        stack.pop();
    }

    @EventHandler
    private final void onAttack(AttackEntityEvent event) {
        if (!(this.isEnabled() && this.styleMode.is(STYLE_WARHEADS) && event.getTarget() instanceof LivingEntity)) {
            return;
        }
        this.warheads.requestLaunch();
    }

    private final void renderWarheads(MatrixStack stack, Vec3d cameraPos, LivingEntity target, float deltaFrames, Vec3d sPos, long now, boolean visible) {
        TargetWarheadSystem.Params p = this.warheadParams;
        p.setCount(this.warheadCount.getInt());
        p.setOrbitDistance(0.75f + (this.warheadDistance.getFloat() - 0.1f) / 0.9f);
        p.setSize(this.warheadSize.getFloat());
        p.setSpinSpeed(this.warheadSpin.getFloat());
        p.setAlpha(this.moduleFade);
        p.setVisible(visible);
        p.setGlassAlpha(this.moduleFade);
        p.setThroughWalls(this.throughWalls.getValue());
        p.setShardCount(this.warheadShards.getInt());
        p.setFlightSpeed(this.warheadSpeed.getFloat());
        p.setParamU(20.0f + MathHelper.clamp((float)(this.warheadDistort.getFloat() / 3.0f), (float)0.0f, (float)0.99f));
        p.setParamV((float)Math.floor(MathHelper.clamp((float)this.warheadTint.getFloat(), (float)0.0f, (float)1.0f) * 20.0f) + 0.33333334f);
        VertexConsumerProvider.Immediate immediate2 = this.mc.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        VertexConsumerProvider.Immediate provider = immediate2;
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        this.warheads.render(provider, entry2, cameraPos, sPos, Math.max(0.1f, target.getWidth()), Math.max(0.1f, target.getHeight()), now, deltaFrames / 60.0f, p, (arg_0, arg_1) -> TargetESP.renderWarheads$lambda$0(this, arg_0, arg_1));
    }

    private final void projectMarker(MatrixStack stack, Matrix4f projection, Vec3d cameraPos, LivingEntity target, Vec3d sPos) {
        float guiW = Position.Companion.screenWidth();
        float guiH = Position.Companion.screenHeight();
        if (guiW < 1.0f || guiH < 1.0f || projection == null) {
            this.markerProjected = false;
            return;
        }
        double anchorY = sPos.y + (double)((target.getStandingEyeHeight() + 0.4f) * 0.5f);
        Matrix4f mvp = new Matrix4f((Matrix4fc)projection).mul((Matrix4fc)stack.peek().getPositionMatrix());
        Vector4f v = mvp.transform(new Vector4f((float)(sPos.x - cameraPos.x), (float)(anchorY - cameraPos.y), (float)(sPos.z - cameraPos.z), 1.0f));
        if (v.w <= 1.0E-4f) {
            this.markerProjected = false;
            this.markerSmoothed = false;
            return;
        }
        float screenX = (v.x / v.w * 0.5f + 0.5f) * guiW;
        float screenY = (1.0f - (v.y / v.w * 0.5f + 0.5f)) * guiH;
        if (this.markerSmoothed) {
            float follow = target.isAlive() ? 0.5f : 0.0f;
            this.markerScreenX += (screenX - this.markerScreenX) * follow;
            this.markerScreenY += (screenY - this.markerScreenY) * follow;
        } else {
            this.markerScreenX = screenX;
            this.markerScreenY = screenY;
            this.markerSmoothed = true;
        }
        this.markerHurt = MathHelper.clamp((float)((float)Math.sin((double)target.hurtTime * 0.3141592653589793)), (float)0.0f, (float)1.0f);
        this.markerProjected = true;
    }

    @EventHandler
    private final void onHudMarker(HudRenderEvent event) {
        this.moduleFade = this.visualAlpha();
        if (!this.isVisuallyActive() || !this.styleMode.is(STYLE_IMAGE) || !this.markerProjected || this.markerFade * this.moduleFade <= 0.001f) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        long now = System.currentTimeMillis();
        double wave = (Math.sin((double)now / 500.0) + 1.0) / 2.0;
        float angle = MathHelper.clamp((float)((float)(wave * 90.0)), (float)0.0f, (float)90.0f);
        float scale = MathHelper.clamp((float)((float)(wave * 1.1)), (float)0.9f, (float)1.1f);
        float spin = MathHelper.clamp((float)((float)((Math.sin((double)now / 1000.0) + 1.0) / 2.0 * 360.0)), (float)0.0f, (float)360.0f);
        float sc = 1.0f;
        LivingEntity target = this.renderedTarget;
        ClientPlayerEntity player = this.mc.player;
        if (target != null && player != null) {
            sc = MathHelper.clamp((float)(1.0f - player.distanceTo((Entity)target) / 3.0f), (float)0.75f, (float)1.0f);
        }
        sc = scale + (sc - scale) * 0.5f;
        float size = 100.0f * scale * sc;
        float rotation = 45.0f - (angle - 45.0f) + spin;
        int c1 = this.markerColor(0);
        int c2 = this.markerColor(90);
        int c3 = this.markerColor(180);
        int c4 = this.markerColor(270);
        TextureSetup textureSetup2 = MarkerRenderState.Companion.resolveTexture(MARKER_TEXTURE);
        if (textureSetup2 == null) {
            return;
        }
        TextureSetup textureSetup = textureSetup2;
        Intrinsics.checkNotNull((Object)graphics, (String)"null cannot be cast to non-null type mixin.accessor.GuiGraphicsExtractorAccessor");
        GuiRenderState renderState = ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState();
        renderState.addSimpleElement((SimpleGuiElementRenderState)new MarkerRenderState(Render2DCoordinateSpace.pose(graphics), textureSetup, this.markerAdditive.getValue(), this.markerScreenX, this.markerScreenY, size, rotation, c1, c2, c3, c4));
    }

    private final int markerColor(int index) {
        int base;
        float fade = this.markerFade * this.moduleFade;
        if (this.colorMode.is(COLOR_RAINBOW)) {
            base = TargetESP.Companion.rainbow(10, index, 1.0f, 1.0f, fade);
        } else {
            int first = 0;
            int second = 0;
            if (this.colorMode.is(COLOR_CLIENT)) {
                first = ClientAccent.gradientA(255.0f);
                second = ClientAccent.gradientB(255.0f);
            } else {
                first = this.customColor.getColor() | 0xFF000000;
                second = this.useSecondColor.getValue() ? this.customSecondColor.getColor() | 0xFF000000 : first;
            }
            base = ColorEngine.multAlpha(TargetESP.Companion.exchuexLerp(index, first, second), fade);
        }
        if (this.markerHurt <= 0.001f) {
            return base;
        }
        int hurt = ColorEngine.rgba(255, 0, 0, Math.round(255.0f * fade));
        return ColorEngine.lerpColor(base, hurt, this.markerHurt);
    }

    private final void renderCircle(MatrixStack stack, Vec3d cameraPos, LivingEntity target, float deltaFrames, Vec3d sPos) {
        float bbWidth = Math.max(0.1f, target.getWidth());
        float bbHeight = Math.max(0.1f, target.getHeight());
        float ringRadius = Math.max(0.35f, (bbWidth * 0.5f + 0.3f) * this.circleRadius.getFloat());
        float centerY = bbHeight * 0.5f;
        float tiltRad = (float)Math.toRadians(this.circleTilt.getFloat());
        float targetSpeed = this.circleSpeed.getFloat();
        if (Float.isNaN(this.spinSpeedCurrent)) {
            this.spinSpeedCurrent = targetSpeed;
        } else {
            float accel = Math.min(1.0f, 0.08f * deltaFrames);
            this.spinSpeedCurrent += (targetSpeed - this.spinSpeedCurrent) * accel;
            if (Math.abs(targetSpeed - this.spinSpeedCurrent) < 0.001f) {
                this.spinSpeedCurrent = targetSpeed;
            }
        }
        this.spinAngle += this.spinSpeedCurrent * (deltaFrames / 60.0f);
        this.spinAngle %= (float)Math.PI * 2;
        float spinRad = this.spinAngle;
        float thickness = ringRadius * ((this.circleThickness.getFloat() - 1.0f) / 4.0f * 0.15f);
        Vec3d ringCenter = new Vec3d(sPos.x, sPos.y + (double)centerY, sPos.z);
        double dist = cameraPos.distanceTo(ringCenter);
        float effAlpha = this.alpha * (float)MathHelper.clamp((double)((dist - (double)ringRadius) / (double)Math.max(ringRadius, 0.01f)), (double)0.0, (double)1.0);
        if (effAlpha <= 0.02f) {
            return;
        }
        boolean tw = this.throughWalls.getValue();
        VertexConsumerProvider.Immediate immediate2 = this.mc.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        VertexConsumerProvider.Immediate provider = immediate2;
        stack.push();
        stack.translate(sPos.x - cameraPos.x, sPos.y - cameraPos.y, sPos.z - cameraPos.z);
        TargetEspColorProvider tubeColors = (arg_0, arg_1) -> TargetESP.renderCircle$lambda$0(this, arg_0, arg_1);
        TargetEspColorProvider bloomColors = (arg_0, arg_1) -> TargetESP.renderCircle$lambda$1(this, arg_0, arg_1);
        TargetCircleRenderer.render(provider, stack, ringRadius, centerY, tiltRad, spinRad, thickness, effAlpha, tw, tubeColors);
        float strength = this.circleGlow.getFloat() * this.moduleFade;
        if (strength > 0.001f && !TargetCircleBloomRenderer.isDisabledAfterError()) {
            TargetCircleBloomRenderer.apply((Framebuffer)this.mc.getFramebuffer(), (float)0.0f, (float)strength, (boolean)tw, arg_0 -> TargetESP.renderCircle$lambda$2(stack, ringRadius, centerY, tiltRad, spinRad, thickness, effAlpha, tw, bloomColors, arg_0));
        }
        stack.pop();
    }

    private final LivingEntity hoveredTarget() {
        HitResult hitResult = this.mc.crosshairTarget;
        if (!(hitResult instanceof EntityHitResult)) {
            return null;
        }
        Entity entity2 = ((EntityHitResult)hitResult).getEntity();
        Intrinsics.checkNotNullExpressionValue((Object)entity2, (String)"getEntity(...)");
        Entity entity = entity2;
        if (!(entity instanceof LivingEntity) || Intrinsics.areEqual((Object)entity, (Object)this.mc.player)) {
            return null;
        }
        if (!((LivingEntity)entity).isAlive() || ((LivingEntity)entity).isRemoved() || ((LivingEntity)entity).getHealth() <= 0.0f) {
            return null;
        }
        if (EntityVisibility.isHidden(entity)) {
            return null;
        }
        return (LivingEntity)entity;
    }

    private final float frameDelta(long now) {
        float deltaMs = now - this.lastFrameMillis;
        this.lastFrameMillis = now;
        deltaMs = Math.max(1.0f, Math.min(deltaMs, 100.0f));
        return deltaMs / 16.666666f;
    }

    private final int resolveColor(int index, float rawAlpha) {
        float alpha = rawAlpha * this.moduleFade;
        if (this.colorMode.is(COLOR_RAINBOW)) {
            return TargetESP.Companion.rainbow(8, index, 1.0f, 1.0f, alpha);
        }
        int firstColor = 0;
        int secondColor = 0;
        if (this.colorMode.is(COLOR_CLIENT)) {
            int[] palette = ClientPalette.colors();
            if (palette != null && palette.length >= 2) {
                return ColorEngine.multAlpha(TargetESP.Companion.paletteFade(8, index, palette), alpha);
            }
            InterfaceModule iface = InterfaceModule.Companion.getInstance();
            if (iface != null) {
                firstColor = iface.clientPrimaryColorOpaque();
                secondColor = iface.usesSecondClientColor() ? iface.clientSecondaryColorOpaque() : firstColor;
            } else {
                firstColor = TargetESP.Companion.getClientColor();
                secondColor = ColorEngine.lerpColor(firstColor, DARK_SECOND_COLOR, 0.7f);
            }
        } else {
            firstColor = this.customColor.getColor();
            int n = secondColor = this.useSecondColor.getValue() ? this.customSecondColor.getColor() : this.customColor.getColor();
        }
        if (firstColor == secondColor) {
            return ColorEngine.multAlpha(firstColor, alpha);
        }
        return ColorEngine.multAlpha(TargetESP.Companion.fade(8, index, firstColor, secondColor), alpha);
    }

    private final void clearRenderState() {
        this.renderedTarget = null;
        this.smoothedPos = null;
        this.transitionStartPos = null;
        this.switching = false;
        this.currentTargetId = Integer.MIN_VALUE;
        this.alpha = 0.0f;
        this.hurtProgress = 0.0f;
        this.chainImpactProgress = 0.0f;
        this.spinSpeedCurrent = Float.NaN;
        this.markerProjected = false;
        this.markerSmoothed = false;
        this.markerHurt = 0.0f;
        this.markerFade = 0.0f;
        this.warheads.clear();
    }

    private static final Boolean markerAdditive$lambda$0(TargetESP this$0) {
        return this$0.styleMode.is(STYLE_IMAGE);
    }

    private static final Boolean circleRadius$lambda$0(TargetESP this$0) {
        return this$0.styleMode.is(STYLE_CIRCLE);
    }

    private static final Boolean circleTilt$lambda$0(TargetESP this$0) {
        return this$0.styleMode.is(STYLE_CIRCLE);
    }

    private static final Boolean circleSpeed$lambda$0(TargetESP this$0) {
        return this$0.styleMode.is(STYLE_CIRCLE);
    }

    private static final Boolean circleThickness$lambda$0(TargetESP this$0) {
        return this$0.styleMode.is(STYLE_CIRCLE);
    }

    private static final Boolean circleGlow$lambda$0(TargetESP this$0) {
        return this$0.styleMode.is(STYLE_CIRCLE);
    }

    private static final Boolean warheadCount$lambda$0(TargetESP this$0) {
        return this$0.styleMode.is(STYLE_WARHEADS);
    }

    private static final Boolean warheadDistance$lambda$0(TargetESP this$0) {
        return this$0.styleMode.is(STYLE_WARHEADS);
    }

    private static final Boolean warheadSize$lambda$0(TargetESP this$0) {
        return this$0.styleMode.is(STYLE_WARHEADS);
    }

    private static final Boolean warheadSpin$lambda$0(TargetESP this$0) {
        return this$0.styleMode.is(STYLE_WARHEADS);
    }

    private static final Boolean warheadSpeed$lambda$0(TargetESP this$0) {
        return this$0.styleMode.is(STYLE_WARHEADS);
    }

    private static final Boolean warheadShards$lambda$0(TargetESP this$0) {
        return this$0.styleMode.is(STYLE_WARHEADS);
    }

    private static final Boolean warheadTint$lambda$0(TargetESP this$0) {
        return this$0.styleMode.is(STYLE_WARHEADS);
    }

    private static final Boolean warheadDistort$lambda$0(TargetESP this$0) {
        return this$0.styleMode.is(STYLE_WARHEADS);
    }

    private static final Boolean circleHeight$lambda$0(TargetESP this$0) {
        return this$0.styleMode.is(STYLE_AURA);
    }

    private static final Boolean brightness$lambda$0(TargetESP this$0) {
        return this$0.styleMode.is(STYLE_AURA);
    }

    private static final Boolean _init_$lambda$0(TargetESP this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean _init_$lambda$1(TargetESP this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean _init_$lambda$2(TargetESP this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM) && this$0.useSecondColor.getValue();
    }

    private static final int onWorldRender$lambda$0(TargetESP this$0, int index, float a) {
        return this$0.resolveColor(index, a);
    }

    private static final int renderWarheads$lambda$0(TargetESP this$0, int index, float a) {
        return this$0.resolveColor(index, a);
    }

    private static final int renderCircle$lambda$0(TargetESP this$0, int index, float a) {
        return TargetESP.Companion.mixWhite(this$0.resolveColor(index, a), 0.2f);
    }

    private static final int renderCircle$lambda$1(TargetESP this$0, int index, float a) {
        return TargetESP.Companion.mixWhite(this$0.resolveColor(index, a), 0.1f);
    }

    private static final void renderCircle$lambda$2(MatrixStack $stack, float $ringRadius, float $centerY, float $tiltRad, float $spinRad, float $thickness, float $effAlpha, boolean $tw, TargetEspColorProvider $bloomColors, VertexConsumerProvider.Immediate sp) {
        Intrinsics.checkNotNullParameter((Object)sp, (String)"sp");
        TargetCircleRenderer.render(sp, $stack, $ringRadius, $centerY, $tiltRad, $spinRad, $thickness, $effAlpha, $tw, $bloomColors);
    }

    @JvmStatic
    @Nullable
    public static final TargetESP getInstance() {
        return Companion.getInstance();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u0015\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\t\u0010\nJ7\u0010\u0011\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0015\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J/\u0010\u0017\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J'\u0010\u001b\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u001f\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010\"R\u0014\u0010$\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b$\u0010\"R\u0014\u0010&\u001a\u00020%8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020%8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010'R\u0014\u0010)\u001a\u00020%8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010'R\u0014\u0010*\u001a\u00020%8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010'R\u0014\u0010+\u001a\u00020%8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010'R\u0014\u0010,\u001a\u00020%8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010'R\u0014\u0010-\u001a\u00020%8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010'R\u0014\u0010.\u001a\u00020%8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010'R\u0014\u0010/\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0014\u00102\u001a\u0002018\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0014\u00104\u001a\u0002018\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u00103R\u001d\u00106\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b5\u00a2\u0006\u0006\n\u0004\b6\u00107\u00a8\u00068"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/TargetESP.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/TargetESP;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/TargetESP;", "", "getClientColor", "()I", "speed", "index", "", "saturation", "brightness", "alpha", "rainbow", "(IIFFF)I", "first", "second", "exchuexLerp", "(III)I", "fade", "(IIII)I", "", "palette", "paletteFade", "(II[I)I", "color", "t", "mixWhite", "(IF)I", "CLIENT_COLOR_FIRST", "I", "CLIENT_COLOR_SECOND", "DARK_SECOND_COLOR", "", "COLOR_RAINBOW", "Ljava/lang/String;", "COLOR_CLIENT", "COLOR_CUSTOM", "STYLE_AURA", "STYLE_CIRCLE", "STYLE_IMAGE", "STYLE_WARHEADS", "MARKER_TEXTURE", "MARKER_SIZE", "F", "", "HIDE_DELAY_MS", "J", "SWITCH_BLEND_MS", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/TargetESP;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final TargetESP getInstance() {
            TargetESP module = ModuleManager.Companion.get().get(TargetESP.class);
            TargetESP targetESP = module;
            if (targetESP == null) {
                targetESP = INSTANCE;
            }
            return targetESP;
        }

        private final int getClientColor() {
            float wave = ((float)Math.sin((double)(System.currentTimeMillis() % 1000000L) / 520.0) + 1.0f) / 2.0f;
            return ColorEngine.lerpColor(CLIENT_COLOR_FIRST, CLIENT_COLOR_SECOND, wave);
        }

        private final int rainbow(int speed, int index, float saturation, float brightness, float alpha) {
            int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
            int rgb = ColorEngine.rainbow(angle, saturation, brightness);
            return ColorEngine.rgba(rgb >>> 16 & 0xFF, rgb >>> 8 & 0xFF, rgb & 0xFF, Math.round(MathHelper.clamp((float)alpha, (float)0.0f, (float)1.0f) * 255.0f));
        }

        private final int exchuexLerp(int index, int first, int second) {
            int angle = (int)((System.currentTimeMillis() / 10L + (long)index) % 360L);
            angle = (angle >= 180 ? 360 - angle : angle) * 2;
            return ColorEngine.lerpColor(first, second, 1.0f - (float)angle / 360.0f);
        }

        private final int fade(int speed, int index, int first, int second) {
            int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
            angle = angle >= 180 ? 360 - angle : angle;
            return ColorEngine.lerpColor(first, second, (float)angle / 180.0f);
        }

        private final int paletteFade(int speed, int index, int[] palette) {
            int n = palette.length;
            int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
            float f = (float)angle / 360.0f * (float)n;
            int i = (int)f % n;
            int j = (i + 1) % n;
            int a = palette[i] | 0xFF000000;
            int b = palette[j] | 0xFF000000;
            return ColorEngine.lerpColor(a, b, f - (float)Math.floor(f)) | 0xFF000000;
        }

        private final int mixWhite(int color, float t) {
            int a = color >>> 24 & 0xFF;
            int r = color >>> 16 & 0xFF;
            int g = color >>> 8 & 0xFF;
            int b = color & 0xFF;
            r += Math.round((float)(255 - r) * t);
            g += Math.round((float)(255 - g) * t);
            b += Math.round((float)(255 - b) * t);
            return a << 24 | r << 16 | g << 8 | b;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

