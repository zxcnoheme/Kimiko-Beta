/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.client.util.Window
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Position
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.Identifier
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.lang.invoke.LambdaMetafactory;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Random;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.util.Window;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.impl.Visuals.portallive.PortalLiveCapture;
import rtx.kimiko.api.modules.impl.Visuals.portallive.PortalLiveClient;
import rtx.kimiko.api.modules.impl.Visuals.portallive.PortalLiveStreamTexture;
import rtx.kimiko.api.modules.impl.Visuals.portallive.PortalLiveView;
import rtx.kimiko.api.modules.impl.Visuals.portallive.PortalLiveVoice;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BindSetting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.modules.settings.impl.StringSetting;
import rtx.kimiko.api.ui.window.GuiShatterAnimation;
import rtx.kimiko.utils.animations.Easings;
import rtx.kimiko.utils.animations.GuiMotionAnimation;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;
import rtx.kimiko.utils.render.render2d.ClientPalette;
import rtx.kimiko.utils.render.render2d.RefreshRateThrottle;

@Feature(value={"portallive"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00c6\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u0086\u00012\u00020\u0001:\u0002\u0086\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0014\u00a2\u0006\u0004\b\b\u0010\u0003J\u001b\u0010\f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u0003J\u0017\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0003J\u000f\u0010\u0014\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0015J\u0017\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u001a\u0010\u0015J\u0015\u0010\u001c\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u001b\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\u0017\u0010\"\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b$\u0010\u0003J\u000f\u0010%\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b%\u0010\u0003J\u0017\u0010(\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020&H\u0002\u00a2\u0006\u0004\b(\u0010)J'\u0010,\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010+\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b,\u0010-J\u0017\u0010/\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b/\u00100J/\u00105\u001a\u00020\u00072\u0006\u00102\u001a\u0002012\u0006\u00104\u001a\u0002032\u0006\u0010'\u001a\u00020&2\u0006\u0010+\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b5\u00106J/\u00109\u001a\u00020\u00072\u0006\u00102\u001a\u0002012\u0006\u00104\u001a\u0002032\u0006\u00107\u001a\u00020\u000f2\u0006\u00108\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b9\u0010:J'\u0010<\u001a\u00020\u000f2\u0006\u0010;\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u00108\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b<\u0010=J/\u0010@\u001a\u00020\u00072\u0006\u00102\u001a\u0002012\u0006\u00104\u001a\u0002032\u0006\u0010>\u001a\u00020\u000f2\u0006\u0010?\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b@\u0010AJ/\u0010B\u001a\u00020\u00072\u0006\u00102\u001a\u0002012\u0006\u00104\u001a\u0002032\u0006\u0010>\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bB\u0010AJ\u001f\u0010E\u001a\u00020\u00072\u0006\u0010C\u001a\u00020\u001e2\u0006\u0010D\u001a\u00020&H\u0002\u00a2\u0006\u0004\bE\u0010FJ\u000f\u0010G\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bG\u0010\u0015R\u0014\u0010I\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u0010JR\u0014\u0010L\u001a\u00020K8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u0010MR\u0014\u0010O\u001a\u00020N8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bO\u0010PR\u0014\u0010Q\u001a\u00020K8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bQ\u0010MR\u0014\u0010S\u001a\u00020R8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bS\u0010TR\u0014\u0010V\u001a\u00020U8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bV\u0010WR\u0014\u0010X\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bX\u0010JR\u0014\u0010Z\u001a\u00020Y8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bZ\u0010[R\u0014\u0010\\\u001a\u00020K8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\\\u0010MR\u0014\u0010^\u001a\u00020]8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b^\u0010_R\u0014\u0010a\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\ba\u0010bR\u0016\u0010c\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010dR\u0016\u0010e\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\be\u0010dR\u0016\u0010f\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bf\u0010dR\u0016\u0010g\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bg\u0010hR\u0016\u0010i\u001a\u00020&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bi\u0010jR\u0016\u0010l\u001a\u00020k8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bl\u0010mR\u0016\u0010n\u001a\u00020k8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bn\u0010mR\u0016\u0010p\u001a\u00020o8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bp\u0010qR\u0016\u0010r\u001a\u00020k8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\br\u0010mR\u0014\u0010t\u001a\u00020s8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bt\u0010uR\u0014\u0010w\u001a\u00020v8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bw\u0010xR\u0018\u0010z\u001a\u0004\u0018\u00010y8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bz\u0010{R\u0016\u0010|\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b|\u0010dR\u0016\u0010}\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b}\u0010dR\u0016\u0010~\u001a\u00020k8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b~\u0010mR\u0016\u0010\u007f\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u007f\u0010hR\u0018\u0010\u0080\u0001\u001a\u00020&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0080\u0001\u0010jR\u0018\u0010\u0081\u0001\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0081\u0001\u0010hR\u0018\u0010\u0082\u0001\u001a\u00020&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0082\u0001\u0010jR\u0019\u0010\u0083\u0001\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0083\u0001\u0010\u0084\u0001R\u0018\u0010\u0085\u0001\u001a\u00020k8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0085\u0001\u0010m\u00ca\u0001\u0013\b\u0087\u0001\u0012\u000e\b\u0088\u0001\u0012\t\b\fJ\u0005\b\b(\u0089\u0001\u00a8\u0006\u008a\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/PortalLive;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "mirrorMode", "()Z", "", "onDisable", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "updateReanchor", "", "level", "applyResolution", "(I)V", "updateAdaptive", "adaptFps", "()I", "adaptQuality", "mirror", "presetFps", "(Z)I", "presetJpeg", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "Lnet/minecraft/Vec3d;", "freshAnchor", "()Lnet/minecraft/Vec3d;", "candidate", "anchorSpotClear", "(Lnet/minecraft/Vec3d;)Z", "stopHolding", "beginShatter", "", "alpha", "quadColor", "(F)I", "angle01", "phase", "rimVertexColor", "(FFI)I", "index", "gradientColor", "(I)I", "Lnet/minecraft/MatrixStack$Entry;", "pose", "Lnet/minecraft/VertexConsumer;", "buffer", "drawRimFan", "(Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/VertexConsumer;FI)V", "color", "mirrored", "drawPortalQuad", "(Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/VertexConsumer;IZ)V", "progress", "buildShardGeometry", "(FFZ)I", "vertices", "blurByte", "emitShards", "(Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/VertexConsumer;II)V", "emitShardRims", "cam", "camYaw", "updateVoiceSpatial", "(Lnet/minecraft/Vec3d;F)V", "accentColor", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "linkSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "viewMode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "portalKey", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "activationMode", "Lrtx/kimiko/api/modules/settings/impl/StringSetting;", "roomCode", "Lrtx/kimiko/api/modules/settings/impl/StringSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "voiceChat", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "portalSeparator", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "distance", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "qualityPreset", "Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveClient;", "client", "Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveClient;", "Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveVoice;", "voice", "Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveVoice;", "holding", "Z", "keyWasDown", "toggled", "anchor", "Lnet/minecraft/Vec3d;", "anchorYaw", "F", "", "nextConnectAttemptAt", "J", "lastStateSentAt", "", "typedRoom", "Ljava/lang/String;", "roomChangedAt", "Lrtx/kimiko/utils/animations/GuiMotionAnimation;", "motion", "Lrtx/kimiko/utils/animations/GuiMotionAnimation;", "Lrtx/kimiko/api/ui/window/GuiShatterAnimation$State;", "shatter", "Lrtx/kimiko/api/ui/window/GuiShatterAnimation$State;", "Ljava/nio/ByteBuffer;", "shardBuffer", "Ljava/nio/ByteBuffer;", "shatterHadVideo", "reanchoring", "reanchorStartMs", "reanchorFrom", "reanchorFromYaw", "reanchorTarget", "reanchorTargetYaw", "adaptLevel", "I", "nextAdaptAt", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "portallive", "rtx.kimiko:kimiko"})
public final class PortalLive
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting linkSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Связь"));
    @NotNull
    private final ModeSetting viewMode;
    @NotNull
    private final BindSetting portalKey;
    @NotNull
    private final ModeSetting activationMode;
    @NotNull
    private final StringSetting roomCode;
    @NotNull
    private final BooleanSetting voiceChat;
    @NotNull
    private final SeparatorSetting portalSeparator;
    @NotNull
    private final SliderSetting distance;
    @NotNull
    private final ModeSetting qualityPreset;
    @NotNull
    private final PortalLiveClient client;
    @NotNull
    private final PortalLiveVoice voice;
    private boolean holding;
    private boolean keyWasDown;
    private boolean toggled;
    @NotNull
    private Vec3d anchor;
    private float anchorYaw;
    private long nextConnectAttemptAt;
    private long lastStateSentAt;
    @NotNull
    private String typedRoom;
    private long roomChangedAt;
    @NotNull
    private final GuiMotionAnimation motion;
    @NotNull
    private final GuiShatterAnimation.State shatter;
    @Nullable
    private ByteBuffer shardBuffer;
    private boolean shatterHadVideo;
    private boolean reanchoring;
    private long reanchorStartMs;
    @NotNull
    private Vec3d reanchorFrom;
    private float reanchorFromYaw;
    @NotNull
    private Vec3d reanchorTarget;
    private float reanchorTargetYaw;
    private int adaptLevel;
    private long nextAdaptAt;
    @NotNull
    private static final String RELAY_HOST = "31.77.145.146";
    private static final int RELAY_PORT = 32119;
    private static final long CONNECT_RETRY_MS = 5000L;
    private static final long ROOM_SETTLE_MS = 1200L;
    private static final long STATE_RESEND_MS = 2000L;
    @NotNull
    private static final String QUALITY_LOW = "Низкое";
    @NotNull
    private static final String QUALITY_MEDIUM = "Среднее";
    @NotNull
    private static final String QUALITY_HIGH = "Высокое";
    private static final long FRAME_STALE_MS = 5000L;
    private static final float HALF_W = 0.55f;
    private static final float HALF_H = 0.95f;
    private static final float QUAD_EXT = 1.35f;
    private static final long TIME_LOOP_MS = 6283L;
    private static final float TAU = (float)Math.PI * 2;
    private static final int RIM_SEGMENTS = 48;
    private static final long REANCHOR_MS = 360L;
    private static final double CAMERA_LIFT = 0.55;
    @NotNull
    private static final int[][] STREAM_STEPS;
    private static final int ADAPT_MAX = 4;
    private static final long ADAPT_INTERVAL_MS = 500L;
    private static final float SHATTER_UNIT = 1000.0f;
    @NotNull
    private static final Random SHATTER_RANDOM;
    @NotNull
    private static final String MODE_OTHERS = "На других";
    @NotNull
    private static final String MODE_MIRROR = "Только на себя";
    @NotNull
    private static final String ACTIVATION_HOLD = "Зажатие";
    @NotNull
    private static final String ACTIVATION_TOGGLE = "Переключение";
    @JvmField
    @Nullable
    public static PortalLive INSTANCE;
    private static volatile boolean debugForce;

    public PortalLive() {
        super("Portal Live", "Живой портал: вы с тиммейтом видите миры друг друга в реальном времени.", Category.VISUALS);
        String[] stringArray = new String[]{MODE_OTHERS, MODE_MIRROR};
        this.viewMode = (ModeSetting)this.register((Setting)new ModeSetting("Режим", "На других — трансляция с тиммейтом. Только на себя — портал работает как зеркало без сети.", MODE_OTHERS, stringArray));
        this.portalKey = (BindSetting)this.register((Setting)new BindSetting("Клавиша портала", "Клавиша, открывающая портал.").setType(BindSetting.Type.HOLD));
        stringArray = new String[]{ACTIVATION_HOLD, ACTIVATION_TOGGLE};
        this.activationMode = (ModeSetting)this.register((Setting)new ModeSetting("Активация", "Зажатие — портал живёт пока держите клавишу. Переключение — нажали раз открыли, нажали ещё раз закрыли.", ACTIVATION_HOLD, stringArray));
        this.roomCode = (StringSetting)this.register((Setting)new StringSetting("Код комнаты", "Одинаковый код у вас и тиммейта связывает ваши порталы.", "", 24));
        this.voiceChat = (BooleanSetting)this.register((Setting)new BooleanSetting("Общаться с кентом", "Голосовая связь через портал: звук тиммейта исходит из портала, пока он открыт.", false));
        this.portalSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Портал"));
        this.distance = (SliderSetting)this.register((Setting)new SliderSetting("Дистанция", "Насколько далеко перед вами появляется портал.").range(1.5f, 4.0f).increment(0.1f).setValue(2.5f));
        stringArray = new String[]{QUALITY_LOW, QUALITY_MEDIUM, QUALITY_HIGH};
        this.qualityPreset = (ModeSetting)this.register((Setting)new ModeSetting("Качество", "Низкое — экономит FPS и интернет, Среднее — золотая середина, Высокое — максимум чёткости.", QUALITY_MEDIUM, stringArray));
        this.client = new PortalLiveClient();
        this.voice = new PortalLiveVoice();
        Vec3d vec3d2 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
        this.anchor = vec3d2;
        this.typedRoom = "";
        this.motion = new GuiMotionAnimation();
        this.shatter = GuiShatterAnimation.create();
        Vec3d vec3d3 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"ZERO");
        this.reanchorFrom = vec3d3;
        Vec3d vec3d4 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"ZERO");
        this.reanchorTarget = vec3d4;
        INSTANCE = this;
        this.client.setFrameConsumer(PortalLive::_init_$lambda$0);
        this.client.setVoiceConsumer(arg_0 -> PortalLive._init_$lambda$1(this, arg_0));
        this.roomCode.visibleWhen(() -> PortalLive._init_$lambda$2(this));
        this.voiceChat.visibleWhen(() -> PortalLive._init_$lambda$3(this));
    }

    private final boolean mirrorMode() {
        return debugForce || this.viewMode.is(MODE_MIRROR);
    }

    @Override
    protected void onDisable() {
        this.stopHolding();
        this.voice.stop();
        this.client.disconnect();
        PortalLiveCapture.clearRequest();
        PortalLiveCapture.shutdown();
        PortalLiveStreamTexture.shutdown();
        this.shatter.cancel();
        this.motion.snapClosed();
        this.toggled = false;
        this.keyWasDown = false;
    }

    /*
     * Unable to fully structure code
     */
    @EventHandler
    public final void onTick(@NotNull TickEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!event.isPre()) {
            return;
        }
        net.minecraft.client.network.ClientPlayerEntity player = this.mc.player;
        net.minecraft.client.world.ClientWorld level = this.mc.world;
        if (player == null || level == null) {
            this.stopHolding();
            return;
        }
        boolean mirror = this.mirrorMode();
        String room = this.roomCode.getText().trim();
        long now = System.currentTimeMillis();
        String nick = player.getGameProfile().name();
        if (!Intrinsics.areEqual((Object)room, (Object)this.typedRoom)) {
            this.typedRoom = room;
            this.roomChangedAt = now;
        }
        boolean linkWanted = !mirror && !room.isEmpty() && now - this.roomChangedAt >= 1200L;
        boolean joined = this.client.isConnected() || this.client.isConnecting();
        boolean stale = joined && (!Intrinsics.areEqual((Object)room, (Object)this.client.getRoom()) || !Intrinsics.areEqual((Object)nick, (Object)this.client.getName()));
        if (stale || (joined && !linkWanted && mirror)) {
            this.client.disconnect();
            joined = false;
            this.nextConnectAttemptAt = now;
        }
        if (linkWanted && !joined && now >= this.nextConnectAttemptAt) {
            this.client.connect("31.77.145.146", 32119, room, nick);
            this.nextConnectAttemptAt = now + 5000L;
        }
        boolean rawDown = PortalLive.debugForce || (this.portalKey.isBound() && this.portalKey.getValue().isDown(this.mc.getWindow()) && this.mc.currentScreen == null);
        boolean toggleMode = this.activationMode.is("Переключение");
        if (toggleMode) {
            if (rawDown && !this.keyWasDown) {
                this.toggled = !this.toggled;
            }
        } else {
            this.toggled = false;
        }
        this.keyWasDown = rawDown;
        boolean engaged = toggleMode ? this.toggled : rawDown;
        boolean held = (mirror || !room.isEmpty()) && engaged;
        if (held && !this.holding) {
            this.holding = true;
            if (this.motion.isClosing() && !this.motion.isCloseFinished()) {
                this.motion.resumeOpening();
                this.shatter.gather(0L);
                this.reanchoring = true;
                this.reanchorStartMs = now;
                this.reanchorFrom = this.anchor;
                this.reanchorFromYaw = this.anchorYaw;
                this.reanchorTarget = this.freshAnchor();
                this.reanchorTargetYaw = player.getYaw();
            } else {
                this.anchor = this.freshAnchor();
                this.anchorYaw = player.getYaw();
                this.reanchoring = false;
                this.shatter.cancel();
                this.motion.startOpening();
                this.adaptLevel = 0;
                this.nextAdaptAt = now + 500L;
                PortalLiveStreamTexture.reset();
                PortalLiveView.resetProbe();
            }
            if (!mirror) {
                this.client.sendActive(true);
            }
            this.lastStateSentAt = now;
        } else if (!held && this.holding) {
            this.stopHolding();
        }
        if (this.reanchoring && this.holding) {
            this.reanchorTarget = this.freshAnchor();
            this.reanchorTargetYaw = player.getYaw();
        }
        if (!mirror && this.holding && now - this.lastStateSentAt >= 2000L) {
            this.client.sendActive(true);
            this.lastStateSentAt = now;
        }
        boolean voiceWanted = !mirror && this.voiceChat.getValue();
        if (voiceWanted && !this.voice.isRunning()) {
            this.voice.start(it -> PortalLive.onTick$lambda$0(this, it));
        } else if (!voiceWanted && this.voice.isRunning()) {
            this.voice.stop();
        }
        this.voice.setActive(voiceWanted && this.holding && this.client.isPeerActive());
    }

    private final void updateReanchor() {
        if (!this.reanchoring) {
            return;
        }
        float t = MathHelper.clamp((float)((float)(System.currentTimeMillis() - this.reanchorStartMs) / 360.0f), (float)0.0f, (float)1.0f);
        float eased = (float)Easings.CUBIC_OUT.ease(t);
        this.anchor = new Vec3d(this.reanchorFrom.x + (this.reanchorTarget.x - this.reanchorFrom.x) * (double)eased, this.reanchorFrom.y + (this.reanchorTarget.y - this.reanchorFrom.y) * (double)eased, this.reanchorFrom.z + (this.reanchorTarget.z - this.reanchorFrom.z) * (double)eased);
        this.anchorYaw = this.reanchorFromYaw + PortalLive.Companion.wrapDegrees(this.reanchorTargetYaw - this.reanchorFromYaw) * eased;
        if (t >= 1.0f) {
            this.reanchoring = false;
        }
    }

    private final void applyResolution(int level) {
        int base = this.qualityPreset.is(QUALITY_HIGH) ? 0 : (this.qualityPreset.is(QUALITY_LOW) ? 3 : 1);
        int index = Math.min(((Object[])STREAM_STEPS).length - 1, base + Math.max(0, level));
        PortalLiveCapture.setResolution(STREAM_STEPS[index][0], STREAM_STEPS[index][1]);
    }

    private final void updateAdaptive() {
        long now = System.currentTimeMillis();
        if (now < this.nextAdaptAt) {
            return;
        }
        this.nextAdaptAt = now + 500L;
        double pressure = this.client.sendPressureMs();
        if (pressure <= 0.0) {
            return;
        }
        double interval = 1000.0 / (double)Math.max(1, this.adaptFps());
        if (pressure > interval * 1.5) {
            this.adaptLevel = Math.min(4, this.adaptLevel + 1);
        } else if (pressure < interval * 0.6) {
            this.adaptLevel = Math.max(0, this.adaptLevel - 1);
        }
    }

    private final int adaptFps() {
        int fps = this.presetFps(false);
        return this.adaptLevel >= 3 ? Math.max(10, fps / 2) : fps;
    }

    private final int adaptQuality() {
        return Math.max(35, this.presetJpeg() - this.adaptLevel * 8);
    }

    private final int presetFps(boolean mirror) {
        if (!mirror) {
            if (this.qualityPreset.is(QUALITY_HIGH)) {
                return 60;
            }
            if (this.qualityPreset.is(QUALITY_LOW)) {
                return 15;
            }
            return 30;
        }
        int hz = RefreshRateThrottle.hz();
        if (this.qualityPreset.is(QUALITY_HIGH)) {
            return hz;
        }
        if (this.qualityPreset.is(QUALITY_LOW)) {
            return Math.max(15, hz / 4);
        }
        return Math.max(30, hz / 2);
    }

    private final int presetJpeg() {
        if (this.qualityPreset.is(QUALITY_HIGH)) {
            return 85;
        }
        if (this.qualityPreset.is(QUALITY_LOW)) {
            return 50;
        }
        return 70;
    }

    public final void onWorldRender(@NotNull WorldRenderEvent event) {
        boolean shards;
        Camera camera2;
        float shatterProgress;
        boolean linked;
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        ClientPlayerEntity player = this.mc.player;
        ClientWorld level = this.mc.world;
        if (player == null || level == null || PortalLiveView.rendering()) {
            return;
        }
        this.updateReanchor();
        boolean mirror = this.mirrorMode();
        boolean bl = linked = this.holding && (mirror || this.client.isPeerActive());
        if (linked) {
            Vec3d vec3d2 = this.anchor.add(0.0, 0.55, 0.0);
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"add(...)");
            Vec3d lens = vec3d2;
            Vec3d vec3d3 = player.getCameraPosVec(event.getPartialTicks()).add(0.0, -0.5, 0.0);
            Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"add(...)");
            Vec3d focus = vec3d3;
            if (mirror) {
                this.applyResolution(0);
                PortalLiveCapture.requestMirror(this.presetFps(true), lens, focus);
            } else {
                this.updateAdaptive();
                this.applyResolution(this.adaptLevel);
                PortalLiveCapture.request(this.adaptFps(), this.adaptQuality(), lens, focus, arg_0 -> PortalLive.onWorldRender$lambda$0(this, arg_0));
            }
        } else {
            PortalLiveCapture.clearRequest();
        }
        this.motion.updateFrame();
        boolean closing = this.motion.isClosing();
        float f = shatterProgress = this.shatter.isActive() ? this.shatter.progress(this.motion.closeProgress()) : 0.0f;
        if (!(this.holding || closing && !this.motion.isCloseFinished())) {
            this.shatter.cancel();
            return;
        }
        long now = System.currentTimeMillis();
        float alpha = this.motion.alpha();
        float scale = this.motion.scale();
        if (scale <= 0.01f || closing && alpha <= 0.004f && shatterProgress <= 0.001f) {
            return;
        }
        if (event.getCamera() != null) {
            camera2 = event.getCamera();
        } else {
            Camera camera3 = this.mc.gameRenderer.getCamera();
            camera2 = camera3;
            Intrinsics.checkNotNullExpressionValue((Object)camera3, (String)"getMainCamera(...)");
        }
        Camera camera = camera2;
        Vec3d vec3d4 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"position(...)");
        Vec3d cam = vec3d4;
        if (this.voice.isRunning()) {
            float camYaw = camera.getYaw();
            this.updateVoiceSpatial(cam, camYaw);
        }
        MatrixStack stack = event.getStack();
        stack.push();
        stack.translate(this.anchor.x - cam.x, this.anchor.y - cam.y, this.anchor.z - cam.z);
        stack.multiply((Quaternionfc)new Quaternionf().rotationY((float)Math.toRadians(-this.anchorYaw)));
        stack.scale(scale, scale, scale);
        boolean video = mirror ? linked && now - PortalLiveCapture.mirrorFrameAtMs() < 5000L : linked && PortalLiveStreamTexture.hasFrame() && now - PortalLiveStreamTexture.lastFrameAtMs() < 5000L;
        VertexConsumerProvider.Immediate immediate2 = this.mc.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        VertexConsumerProvider.Immediate provider = immediate2;
        Identifier streamTexture = mirror ? PortalLiveCapture.MIRROR_TEXTURE_ID : PortalLiveStreamTexture.TEXTURE_ID;
        boolean bl2 = shards = this.shatter.isActive() && this.shatterHadVideo && shatterProgress > 0.001f;
        if (shards) {
            int built = this.buildShardGeometry(shatterProgress, alpha, mirror);
            int blurByte = Math.round(MathHelper.clamp((float)Math.max(shatterProgress, 1.0f - alpha), (float)0.0f, (float)1.0f) * 255.0f);
            RenderLayer renderLayer2 = ClientPipelines.PORTAL_SHARD.apply(streamTexture);
            Intrinsics.checkNotNullExpressionValue((Object)renderLayer2, (String)"apply(...)");
            RenderLayer shardType = renderLayer2;
            MatrixStack.Entry entry2 = stack.peek();
            Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
            VertexConsumer vertexConsumer2 = provider.getBuffer(shardType);
            Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
            this.emitShards(entry2, vertexConsumer2, built, blurByte);
            provider.draw(shardType);
            MatrixStack.Entry entry3 = stack.peek();
            Intrinsics.checkNotNullExpressionValue((Object)entry3, (String)"last(...)");
            VertexConsumer vertexConsumer3 = provider.getBuffer(ClientPipelines.PORTAL_SHARD_RIM);
            Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer3, (String)"getBuffer(...)");
            this.emitShardRims(entry3, vertexConsumer3, built, PortalLive.Companion.timePhase(now));
            provider.draw(ClientPipelines.PORTAL_SHARD_RIM);
        } else {
            RenderLayer renderLayer3 = video ? ClientPipelines.PORTAL_VIDEO.apply(streamTexture) : ClientPipelines.PORTAL_WAIT;
            Intrinsics.checkNotNull((Object)renderLayer3);
            RenderLayer type = renderLayer3;
            MatrixStack.Entry entry4 = stack.peek();
            Intrinsics.checkNotNullExpressionValue((Object)entry4, (String)"last(...)");
            VertexConsumer vertexConsumer4 = provider.getBuffer(type);
            Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer4, (String)"getBuffer(...)");
            this.drawPortalQuad(entry4, vertexConsumer4, this.quadColor(alpha), video && mirror);
            provider.draw(type);
            MatrixStack.Entry entry5 = stack.peek();
            Intrinsics.checkNotNullExpressionValue((Object)entry5, (String)"last(...)");
            VertexConsumer vertexConsumer5 = provider.getBuffer(ClientPipelines.PORTAL_RIM);
            Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer5, (String)"getBuffer(...)");
            this.drawRimFan(entry5, vertexConsumer5, alpha, PortalLive.Companion.timePhase(now));
            provider.draw(ClientPipelines.PORTAL_RIM);
        }
        stack.pop();
    }

    private final Vec3d freshAnchor() {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            Vec3d vec3d2 = Vec3d.ZERO;
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
            return vec3d2;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            Vec3d vec3d3 = Vec3d.ZERO;
            Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"ZERO");
            return vec3d3;
        }
        ClientWorld level = clientWorld3;
        Vec3d vec3d4 = player.getEyePos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"getEyePosition(...)");
        Vec3d eye = vec3d4;
        Vec3d vec3d5 = eye.add(0.0, -0.5, 0.0);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d5, (String)"add(...)");
        Vec3d focus = vec3d5;
        float yawRad = (float)Math.toRadians(player.getYaw());
        Vec3d dir = new Vec3d(-Math.sin(yawRad), 0.0, Math.cos(yawRad));
        double want = this.distance.getFloat();
        double[] dArray = new double[]{want, want * 0.8, want * 0.6, Math.max(1.2, want * 0.45)};
        double[] distances = dArray;
        double[] dArray2 = new double[]{0.0, 0.5, 1.0, 1.5};
        double[] lifts = dArray2;
        Vec3d firstClear = null;
        for (double dist : distances) {
            for (double lift : lifts) {
                Vec3d candidate = eye.add(dir.multiply(dist)).add(0.0, lift, 0.0);
                if (!this.anchorSpotClear(candidate)) continue;
                if (firstClear == null) {
                    firstClear = candidate;
                }
                Vec3d lens = candidate.add(0.0, 0.55, 0.0);
                if (!PortalLiveCapture.fullBodyVisible(this.mc, focus, lens)) continue;
                return candidate;
            }
        }
        Vec3d vec3d6 = firstClear;
        if (vec3d6 == null) {
            Vec3d vec3d7 = eye.add(dir.multiply(Math.max(1.2, want * 0.45)));
            vec3d6 = vec3d7;
            Intrinsics.checkNotNullExpressionValue((Object)vec3d7, (String)"add(...)");
        }
        return vec3d6;
    }

    private final boolean anchorSpotClear(Vec3d candidate) {
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return false;
        }
        ClientWorld level = clientWorld3;
        BlockPos blockPos2 = BlockPos.ofFloored((Position)((Position)candidate));
        Intrinsics.checkNotNullExpressionValue((Object)blockPos2, (String)"containing(...)");
        BlockPos center = blockPos2;
        BlockPos blockPos3 = BlockPos.ofFloored((Position)((Position)candidate.add(0.0, 0.55, 0.0)));
        Intrinsics.checkNotNullExpressionValue((Object)blockPos3, (String)"containing(...)");
        BlockPos lens = blockPos3;
        BlockPos blockPos4 = BlockPos.ofFloored((Position)((Position)candidate.add(0.0, (double)0.95f, 0.0)));
        Intrinsics.checkNotNullExpressionValue((Object)blockPos4, (String)"containing(...)");
        BlockPos top = blockPos4;
        BlockPos blockPos5 = BlockPos.ofFloored((Position)((Position)candidate.add(0.0, (double)-0.95f, 0.0)));
        Intrinsics.checkNotNullExpressionValue((Object)blockPos5, (String)"containing(...)");
        BlockPos bottom = blockPos5;
        return level.getBlockState(center).isAir() && level.getBlockState(lens).isAir() && level.getBlockState(top).isAir() && level.getBlockState(bottom).isAir();
    }

    private final void stopHolding() {
        if (!this.holding) {
            return;
        }
        this.holding = false;
        this.reanchoring = false;
        boolean mirror = this.mirrorMode();
        boolean video = mirror ? PortalLiveCapture.mirrorFrameAtMs() > 0L : PortalLiveStreamTexture.hasFrame();
        this.motion.startClosing();
        if (video) {
            this.shatterHadVideo = true;
            this.beginShatter();
        } else {
            this.shatterHadVideo = false;
            this.shatter.cancel();
        }
        PortalLiveCapture.clearRequest();
        this.client.sendActive(false);
    }

    private final void beginShatter() {
        if (GuiShatterAnimation.State.resume$default(this.shatter, 0.0f, 1, null)) {
            return;
        }
        float screenW = 1485.0f;
        float screenH = 2565.0f;
        float panelW = 1100.0f;
        float panelH = 1900.0f;
        this.shatter.begin((screenW - panelW) * 0.5f, (screenH - panelH) * 0.5f, panelW, panelH, 250.0f, screenW, screenH, SHATTER_RANDOM.nextLong());
    }

    private final int quadColor(float alpha) {
        int accent = this.accentColor();
        return Math.round(MathHelper.clamp((float)alpha, (float)0.0f, (float)1.0f) * 255.0f) << 24 | accent & 0xFFFFFF;
    }

    private final int rimVertexColor(float angle01, float alpha, int phase) {
        int color = this.gradientColor((int)(angle01 * 720.0f));
        int r = Math.round((float)(color >>> 16 & 0xFF) * alpha);
        int g = Math.round((float)(color >>> 8 & 0xFF) * alpha);
        int b = Math.round((float)(color & 0xFF) * alpha);
        return phase << 24 | r << 16 | g << 8 | b;
    }

    private final int gradientColor(int index) {
        int[] palette = ClientPalette.colors();
        if (palette != null && palette.length >= 2) {
            return PortalLive.Companion.paletteFade(8, index, palette);
        }
        InterfaceModule iface = InterfaceModule.Companion.getInstance();
        int first = 0;
        int second = 0;
        if (iface != null) {
            first = iface.clientPrimaryColorOpaque();
            second = iface.usesSecondClientColor() ? iface.clientSecondaryColorOpaque() : first;
        } else {
            first = -1;
            second = -1;
        }
        if (first == second) {
            return first;
        }
        return PortalLive.Companion.fade(8, index, first, second);
    }

    private final void drawRimFan(MatrixStack.Entry pose, VertexConsumer buffer, float alpha, int phase) {
        float w = 0.7425f;
        float h = 1.2825f;
        for (int i = 0; i < 48; ++i) {
            float t0 = (float)i / 48.0f;
            float t1 = (float)(i + 1) / 48.0f;
            float a0 = t0 * ((float)Math.PI * 2);
            float a1 = t1 * ((float)Math.PI * 2);
            float x0 = MathHelper.cos((double)a0);
            float y0 = MathHelper.sin((double)a0);
            float x1 = MathHelper.cos((double)a1);
            float y1 = MathHelper.sin((double)a1);
            int c0 = this.rimVertexColor(t0, alpha, phase);
            int c1 = this.rimVertexColor(t1, alpha, phase);
            buffer.vertex(pose, 0.0f, 0.0f, 0.0f).texture(0.0f, 0.0f).color(c0);
            buffer.vertex(pose, x0 * w, y0 * h, 0.0f).texture(x0 * 1.35f, y0 * 1.35f).color(c0);
            buffer.vertex(pose, x1 * w, y1 * h, 0.0f).texture(x1 * 1.35f, y1 * 1.35f).color(c1);
            buffer.vertex(pose, 0.0f, 0.0f, 0.0f).texture(0.0f, 0.0f).color(c1);
        }
    }

    private final void drawPortalQuad(MatrixStack.Entry pose, VertexConsumer buffer, int color, boolean mirrored) {
        float w = 0.7425f;
        float h = 1.2825f;
        float e = mirrored ? -1.35f : 1.35f;
        buffer.vertex(pose, -w, -h, 0.0f).texture(-e, -e).color(color);
        buffer.vertex(pose, -w, h, 0.0f).texture(-e, e).color(color);
        buffer.vertex(pose, w, h, 0.0f).texture(e, e).color(color);
        buffer.vertex(pose, w, -h, 0.0f).texture(e, -e).color(color);
    }

    private final int buildShardGeometry(float progress, float alpha, boolean mirrored) {
        ByteBuffer sBuf = this.shardBuffer;
        if (sBuf == null) {
            this.shardBuffer = sBuf = ByteBuffer.allocateDirect(44352).order(ByteOrder.nativeOrder());
        }
        float e = mirrored ? -1.35f : 1.35f;
        GuiShatterAnimation.Remap remap = new GuiShatterAnimation.Remap(-0.7425f, 1.2825f, 1.485f, -2.565f, -e, -e, 2.0f * e, 2.0f * e);
        return this.shatter.buildGeometry(sBuf, progress, alpha, 1.0f, true, remap);
    }

    private final void emitShards(MatrixStack.Entry pose, VertexConsumer buffer, int vertices, int blurByte) {
        ByteBuffer byteBuffer = this.shardBuffer;
        if (byteBuffer == null) {
            return;
        }
        ByteBuffer sBuf = byteBuffer;
        for (int i = 0; i < vertices; ++i) {
            int base = i * 24;
            float x = sBuf.getFloat(base);
            float y = sBuf.getFloat(base + 4);
            float z = sBuf.getFloat(base + 8);
            float u = sBuf.getFloat(base + 12);
            float v = sBuf.getFloat(base + 16);
            int a = sBuf.get(base + 23) & 0xFF;
            buffer.vertex(pose, x, y, z).texture(u, v).color(a << 24 | blurByte << 16);
        }
    }

    private final void emitShardRims(MatrixStack.Entry pose, VertexConsumer buffer, int vertices, int phase) {
        ByteBuffer byteBuffer = this.shardBuffer;
        if (byteBuffer == null) {
            return;
        }
        ByteBuffer sBuf = byteBuffer;
        for (int i = 0; i < vertices; ++i) {
            int base = i * 24;
            float x = sBuf.getFloat(base);
            float y = sBuf.getFloat(base + 4);
            float z = sBuf.getFloat(base + 8);
            float u = sBuf.getFloat(base + 12);
            float v = sBuf.getFloat(base + 16);
            int a = sBuf.get(base + 23) & 0xFF;
            float angle01 = (float)(Math.atan2(v, u) / (Math.PI * 2)) + 0.5f;
            int gradient = this.gradientColor((int)(angle01 * 720.0f));
            int r = (gradient >>> 16 & 0xFF) * a / 255;
            int g = (gradient >>> 8 & 0xFF) * a / 255;
            int b = (gradient & 0xFF) * a / 255;
            int color = phase << 24 | r << 16 | g << 8 | b;
            buffer.vertex(pose, x, y, z).texture(u, v).color(color);
        }
    }

    private final void updateVoiceSpatial(Vec3d cam, float camYaw) {
        double dx = this.anchor.x - cam.x;
        double dy = this.anchor.y - cam.y;
        double dz = this.anchor.z - cam.z;
        double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
        float volume = (float)MathHelper.clamp((double)(1.0 - (dist - 4.0) / 16.0), (double)0.0, (double)1.0);
        double horizontal = Math.sqrt(dx * dx + dz * dz);
        float pan = 0.0f;
        if (horizontal > 0.01) {
            float rightYaw = (float)Math.toRadians(camYaw + 90.0f);
            float rx = -MathHelper.sin((double)rightYaw);
            float rz = MathHelper.cos((double)rightYaw);
            pan = (float)((dx * (double)rx + dz * (double)rz) / horizontal);
        }
        float theta = (MathHelper.clamp((float)pan, (float)-1.0f, (float)1.0f) + 1.0f) * 0.7853982f;
        this.voice.setSpatial(MathHelper.cos((double)theta) * volume, MathHelper.sin((double)theta) * volume);
    }

    private final int accentColor() {
        InterfaceModule iface;
        InterfaceModule interfaceModule = iface = InterfaceModule.Companion.getInstance();
        return interfaceModule != null ? interfaceModule.clientPrimaryColorOpaque() : -1;
    }

    private static final void _init_$lambda$0(byte[] it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        PortalLiveStreamTexture.submit(it);
    }

    private static final void _init_$lambda$1(PortalLive this$0, byte[] it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        this$0.voice.onVoicePayload(it);
    }

    private static final Boolean _init_$lambda$2(PortalLive this$0) {
        return this$0.viewMode.is(MODE_OTHERS);
    }

    private static final Boolean _init_$lambda$3(PortalLive this$0) {
        return this$0.viewMode.is(MODE_OTHERS);
    }

    private static final void onTick$lambda$0(PortalLive this$0, byte[] it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        this$0.client.sendVoice(it);
    }

    private static final void onWorldRender$lambda$0(PortalLive this$0, byte[] it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        this$0.client.sendFrame(it);
    }

    @JvmStatic
    @Nullable
    public static final PortalLive getInstance() {
        return Companion.getInstance();
    }

    public static final boolean getDebugForce() {
        return Companion.getDebugForce();
    }

    public static final void setDebugForce(boolean bl) {
        Companion.setDebugForce(bl);
    }

    static {
        int[][] nArrayArray = new int[5][];
        int[] nArray = new int[]{1280, 720};
        nArrayArray[0] = nArray;
        nArray = new int[]{854, 480};
        nArrayArray[1] = nArray;
        nArray = new int[]{640, 360};
        nArrayArray[2] = nArray;
        nArray = new int[]{512, 288};
        nArrayArray[3] = nArray;
        nArray = new int[]{426, 240};
        nArrayArray[4] = nArray;
        STREAM_STEPS = nArrayArray;
        SHATTER_RANDOM = new Random();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ/\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0015\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010!R\u0014\u0010#\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b#\u0010!R\u0014\u0010$\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010\u001dR\u0014\u0010%\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010\u001dR\u0014\u0010&\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010\u001dR\u0014\u0010'\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010!R\u0014\u0010(\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010)R\u0014\u0010+\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010)R\u0014\u0010,\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010!R\u0014\u0010-\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010)R\u0014\u0010.\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010\u001fR\u0014\u0010/\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b/\u0010!R\u0014\u00101\u001a\u0002008\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b1\u00102R\u001a\u00104\u001a\b\u0012\u0004\u0012\u00020\u0013038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0014\u00106\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b6\u0010\u001fR\u0014\u00107\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b7\u0010!R\u0014\u00108\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b8\u0010)R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010<\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b<\u0010\u001dR\u0014\u0010=\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b=\u0010\u001dR\u0014\u0010>\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b>\u0010\u001dR\u0014\u0010?\u001a\u00020\u001b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b?\u0010\u001dR\u001d\u0010A\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b@\u00a2\u0006\u0006\n\u0004\bA\u0010BR,\u0010D\u001a\u00020C8\u0006@\u0006X\u0087\u000er\u0002\b\u0005\u00a2\u0006\u0018\n\u0004\bD\u0010E\u0012\u0004\bJ\u0010\u0003\u001a\u0004\bF\u0010G\"\u0004\bH\u0010I\u00a8\u0006K"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/PortalLive.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/PortalLive;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/PortalLive;", "", "value", "wrapDegrees", "(F)F", "", "speed", "index", "first", "second", "fade", "(IIII)I", "", "palette", "paletteFade", "(II[I)I", "", "now", "timePhase", "(J)I", "", "RELAY_HOST", "Ljava/lang/String;", "RELAY_PORT", "I", "CONNECT_RETRY_MS", "J", "ROOM_SETTLE_MS", "STATE_RESEND_MS", "QUALITY_LOW", "QUALITY_MEDIUM", "QUALITY_HIGH", "FRAME_STALE_MS", "HALF_W", "F", "HALF_H", "QUAD_EXT", "TIME_LOOP_MS", "TAU", "RIM_SEGMENTS", "REANCHOR_MS", "", "CAMERA_LIFT", "D", "", "STREAM_STEPS", "[[I", "ADAPT_MAX", "ADAPT_INTERVAL_MS", "SHATTER_UNIT", "Ljava/util/Random;", "SHATTER_RANDOM", "Ljava/util/Random;", "MODE_OTHERS", "MODE_MIRROR", "ACTIVATION_HOLD", "ACTIVATION_TOGGLE", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/PortalLive;", "", "debugForce", "Z", "getDebugForce", "()Z", "setDebugForce", "(Z)V", "getDebugForce$annotations", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final PortalLive getInstance() {
            PortalLive module = ModuleManager.Companion.get().get(PortalLive.class);
            PortalLive portalLive = module;
            if (portalLive == null) {
                portalLive = INSTANCE;
            }
            return portalLive;
        }

        public final boolean getDebugForce() {
            return debugForce;
        }

        public final void setDebugForce(boolean bl) {
            debugForce = bl;
        }

        @JvmStatic
        public static /* synthetic */ void getDebugForce$annotations() {
        }

        private final float wrapDegrees(float value) {
            float wrapped = value % 360.0f;
            if (wrapped >= 180.0f) {
                wrapped -= 360.0f;
            }
            if (wrapped < -180.0f) {
                wrapped += 360.0f;
            }
            return wrapped;
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

        private final int timePhase(long now) {
            return (int)(now % 6283L * 255L / 6283L);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

