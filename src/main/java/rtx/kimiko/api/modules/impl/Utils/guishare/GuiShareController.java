/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.collections.SetsKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.Regex
 *  kotlin.text.StringsKt
 *  net.minecraft.entity.PlayerLikeEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.ServerInfo
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 *  net.minecraft.client.network.ClientPlayerEntity
 *  net.minecraft.client.render.RenderTickCounter
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils.guishare;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.minecraft.entity.PlayerLikeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Utils.Globals;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiHoldPose;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareClient;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareLocalSnapshot;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareRemoteState;
import rtx.kimiko.api.modules.impl.Utils.guishare.RemoteGuiPanel;
import rtx.kimiko.api.modules.impl.Utils.guishare.RemoteGuiPanelRenderer;
import rtx.kimiko.utils.profile.ProfileIdentity;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00b0\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ!\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000e\u0010\u000fJ'\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0018\u0010\u0003J\u001f\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u001d\u0010 \u001a\u00020\u00132\b\u0010\u001f\u001a\u0004\u0018\u00010\u001eH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b \u0010!J-\u0010 \u001a\u00020\u00132\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\"H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b \u0010%J9\u0010)\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010'\u001a\u0004\u0018\u00010&2\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\"2\u0006\u0010(\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b)\u0010*J\u001f\u0010-\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010,\u001a\u00020+H\u0002\u00a2\u0006\u0004\b-\u0010.J%\u00100\u001a\u0004\u0018\u00010&2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010/\u001a\u0004\u0018\u00010+H\u0002\u00a2\u0006\u0004\b0\u00101J\u0017\u00102\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b2\u00103J\u001f\u00106\u001a\u00020+2\u0006\u00104\u001a\u00020+2\u0006\u00105\u001a\u00020+H\u0002\u00a2\u0006\u0004\b6\u00107J\u0017\u00108\u001a\u00020+2\u0006\u0010(\u001a\u00020+H\u0002\u00a2\u0006\u0004\b8\u00109J\u0017\u0010:\u001a\u00020+2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b:\u0010;J\u0017\u0010<\u001a\u00020+2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b<\u0010;J\u0017\u0010=\u001a\u00020+2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b=\u0010;J\u0017\u0010?\u001a\u00020+2\u0006\u0010>\u001a\u00020+H\u0002\u00a2\u0006\u0004\b?\u00109J\u0017\u0010B\u001a\u00020A2\u0006\u0010@\u001a\u00020+H\u0002\u00a2\u0006\u0004\bB\u0010CJ\u0017\u0010D\u001a\u00020+2\u0006\u0010@\u001a\u00020+H\u0002\u00a2\u0006\u0004\bD\u00109R\u0014\u0010E\u001a\u00020+8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bE\u0010FR\u0014\u0010G\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bG\u0010HR\u0014\u0010J\u001a\u00020I8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bJ\u0010KR\u0014\u0010L\u001a\u00020I8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bL\u0010KR\u0014\u0010M\u001a\u00020I8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bM\u0010KR\u0014\u0010N\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bN\u0010OR\u001a\u0010Q\u001a\b\u0012\u0004\u0012\u00020+0P8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bQ\u0010RR\u0014\u0010T\u001a\u00020S8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bT\u0010UR0\u0010X\u001a\u001e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\r0Vj\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\r`W8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bX\u0010YR\u0016\u0010Z\u001a\u00020I8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bZ\u0010KR\u0016\u0010[\u001a\u00020I8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b[\u0010KR\u0018\u0010]\u001a\u0004\u0018\u00010\\8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b]\u0010^R\u0016\u0010_\u001a\u00020A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b_\u0010`R\u0016\u0010a\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\ba\u0010bR\u0016\u0010c\u001a\u00020\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010dR\u0016\u0010e\u001a\u00020\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\be\u0010dR\u0014\u0010g\u001a\u00020f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bg\u0010h\u00a8\u0006i"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareController;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Utils/Globals;", "module", "", "Lkotlin/jvm/JvmStatic;", "tick", "(Lrtx/kimiko/api/modules/impl/Utils/Globals;)V", "", "max", "", "Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiPanel;", "renderablePanels", "(I)Ljava/util/List;", "Lnet/minecraft/MinecraftClient;", "mc", "panel", "Lnet/minecraft/Vec3d;", "eye", "", "liveDistanceToSqr", "(Lnet/minecraft/MinecraftClient;Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiPanel;Lnet/minecraft/Vec3d;)D", "reset", "Lnet/minecraft/PlayerLikeEntity;", "entity", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiHoldPose$HoldTarget;", "holdTargetFor", "(Lnet/minecraft/PlayerLikeEntity;)Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiHoldPose$HoldTarget;", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareRemoteState;", "state", "liveAnchorFor", "(Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareRemoteState;)Lnet/minecraft/Vec3d;", "", "yaw", "pitch", "(Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareRemoteState;FF)Lnet/minecraft/Vec3d;", "Lnet/minecraft/PlayerEntity;", "player", "fallback", "heldAnchor", "(Lnet/minecraft/MinecraftClient;Lnet/minecraft/PlayerEntity;FFLnet/minecraft/Vec3d;)Lnet/minecraft/Vec3d;", "", "selfKey", "tickPanels", "(Lnet/minecraft/MinecraftClient;Ljava/lang/String;)V", "username", "findLivePlayer", "(Lnet/minecraft/MinecraftClient;Ljava/lang/String;)Lnet/minecraft/PlayerEntity;", "captureAnchor", "(Lnet/minecraft/MinecraftClient;)V", "profileUsername", "minecraftUsername", "buildIdentityKey", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "resolveProfileUsername", "(Ljava/lang/String;)Ljava/lang/String;", "resolveMinecraftUsername", "(Lnet/minecraft/MinecraftClient;)Ljava/lang/String;", "currentWorldId", "serverScope", "address", "stripPort", "host", "", "isLiteralAddress", "(Ljava/lang/String;)Z", "registrableDomain", "SYNC_HOST", "Ljava/lang/String;", "SYNC_PORT", "I", "", "CONNECT_RETRY_MS", "J", "OPEN_PUSH_MS", "CLOSED_HEARTBEAT_MS", "ANCHOR_DISTANCE", "D", "", "MULTI_PART_SUFFIXES", "Ljava/util/Set;", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareClient;", "client", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareClient;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "panels", "Ljava/util/HashMap;", "nextConnectAttemptAt", "nextPushAt", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareLocalSnapshot;", "lastSnapshot", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareLocalSnapshot;", "lastOpen", "Z", "anchor", "Lnet/minecraft/Vec3d;", "anchorYaw", "F", "anchorPitch", "Lkotlin/text/Regex;", "LITERAL_IPV4", "Lkotlin/text/Regex;", "rtx.kimiko:kimiko"})
public final class GuiShareController {
    @NotNull
    public static final GuiShareController INSTANCE = new GuiShareController();
    @NotNull
    private static final String SYNC_HOST = "31.77.145.146";
    private static final int SYNC_PORT = 32118;
    private static final long CONNECT_RETRY_MS = 5000L;
    private static final long OPEN_PUSH_MS = 100L;
    private static final long CLOSED_HEARTBEAT_MS = 2500L;
    private static final double ANCHOR_DISTANCE = 1.55;
    @NotNull
    private static final Set<String> MULTI_PART_SUFFIXES;
    @NotNull
    private static final GuiShareClient client;
    @NotNull
    private static final HashMap<String, RemoteGuiPanel> panels;
    private static long nextConnectAttemptAt;
    private static long nextPushAt;
    @Nullable
    private static GuiShareLocalSnapshot lastSnapshot;
    private static boolean lastOpen;
    @NotNull
    private static Vec3d anchor;
    private static float anchorYaw;
    private static float anchorPitch;
    @NotNull
    private static final Regex LITERAL_IPV4;

    private GuiShareController() {
    }

    @JvmStatic
    public static final void tick(@Nullable Globals globals) {
    }

    @JvmStatic
    @NotNull
    public static final List<RemoteGuiPanel> renderablePanels(int max) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.player == null || mc.world == null || panels.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        ClientPlayerEntity clientPlayerEntity2 = mc.player;
        Intrinsics.checkNotNull((Object)clientPlayerEntity2);
        Vec3d vec3d2 = clientPlayerEntity2.getEyePos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getEyePosition(...)");
        Vec3d eye = vec3d2;
        String world = INSTANCE.currentWorldId(mc);
        double maxDistanceSqr = 2304.0;
        ArrayList<RemoteGuiPanel> list = new ArrayList<RemoteGuiPanel>();
        for (RemoteGuiPanel panel : panels.values()) {
            GuiShareRemoteState state = panel.state();
            PlayerEntity owner = INSTANCE.findLivePlayer(mc, state.minecraftUsername);
            if (!panel.isRenderable() || StringsKt.isBlank((CharSequence)world) || !Intrinsics.areEqual(world, state.world) || owner == null || Intrinsics.areEqual(owner, mc.player) || !(owner.getEyePos().squaredDistanceTo(eye) <= maxDistanceSqr)) continue;
            list.add(panel);
        }
        list.sort(Comparator.comparingDouble((RemoteGuiPanel p) -> INSTANCE.liveDistanceToSqr(mc, p, eye)));
        if (list.size() > max) {
            list = new ArrayList<RemoteGuiPanel>(list.subList(0, max));
        }
        list.sort(Comparator.comparingDouble((RemoteGuiPanel p) -> INSTANCE.liveDistanceToSqr(mc, p, eye)).reversed());
        return list;
    }

    private final double liveDistanceToSqr(MinecraftClient mc, RemoteGuiPanel panel, Vec3d eye) {
        PlayerEntity owner = this.findLivePlayer(mc, panel.state().minecraftUsername);
        return owner != null ? owner.getEyePos().squaredDistanceTo(eye) : Double.POSITIVE_INFINITY;
    }

    @JvmStatic
    public static final void reset() {
        client.disconnect("reset");
        panels.clear();
        lastSnapshot = null;
        lastOpen = false;
        nextPushAt = 0L;
    }

    @JvmStatic
    @Nullable
    public static final GuiHoldPose.HoldTarget holdTargetFor(@Nullable PlayerLikeEntity entity) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (entity == null) {
            return null;
        }
        if (Intrinsics.areEqual((Object)entity, (Object)mc.player)) {
            return null;
        }
        if (panels.isEmpty()) {
            return null;
        }
        String string = entity.getName().getString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getString(...)");
        String name = string;
        if (StringsKt.isBlank((CharSequence)name)) {
            return null;
        }
        Iterator<RemoteGuiPanel> iterator = panels.values().iterator();
        while (iterator.hasNext()) {
            RemoteGuiPanel panel = (RemoteGuiPanel) (iterator.next());
            if (!panel.isRenderable()) continue;
            GuiShareRemoteState state = panel.state();
            PlayerEntity owner = INSTANCE.findLivePlayer(mc, state.minecraftUsername);
            if (!Intrinsics.areEqual((Object)owner, (Object)entity) || !Intrinsics.areEqual((Object)INSTANCE.currentWorldId(mc), (Object)state.world) || !StringsKt.equals((String)state.minecraftUsername, (String)name, (boolean)true)) continue;
            return new GuiHoldPose.HoldTarget(panel.resolvedAnchor(), panel.resolvedYaw(), panel.resolvedPitch(), Math.min(1.0f, panel.scale()), panel.contentAlpha());
        }
        return null;
    }

    @JvmStatic
    @NotNull
    public static final Vec3d liveAnchorFor(@Nullable GuiShareRemoteState state) {
        GuiShareRemoteState guiShareRemoteState = state;
        GuiShareRemoteState guiShareRemoteState2 = state;
        return GuiShareController.liveAnchorFor(state, guiShareRemoteState != null ? guiShareRemoteState.yaw : 0.0f, guiShareRemoteState2 != null ? guiShareRemoteState2.pitch : 0.0f);
    }

    @JvmStatic
    @NotNull
    public static final Vec3d liveAnchorFor(@Nullable GuiShareRemoteState state, float yaw, float pitch) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.world == null || state == null) {
            return state != null && state.anchor != null ? state.anchor : Vec3d.ZERO;
        }
        PlayerEntity player = INSTANCE.findLivePlayer(mc, state.minecraftUsername);
        if (player != null) {
            return INSTANCE.heldAnchor(mc, player, yaw, pitch, state.anchor);
        }
        return state.anchor;
    }

    private final Vec3d heldAnchor(MinecraftClient mc, PlayerEntity player, float yaw, float pitch, Vec3d fallback) {
        if (player == null) {
            return fallback;
        }
        RenderTickCounter renderTickCounter2 = mc.getRenderTickCounter();
        float partialTick = renderTickCounter2 != null ? renderTickCounter2.getTickProgress(true) : 1.0f;
        Vec3d vec3d2 = Vec3d.fromPolar((float)pitch, (float)yaw);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"directionFromRotation(...)");
        Vec3d direction = vec3d2;
        Vec3d vec3d3 = player.getCameraPosVec(partialTick).add(direction.multiply(1.05)).subtract(0.0, 0.2, 0.0);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"subtract(...)");
        return vec3d3;
    }

    private final void tickPanels(MinecraftClient mc, String selfKey) {
        Map<String, GuiShareRemoteState> remote = client.snapshotRemoteStates();
        String world = this.currentWorldId(mc);
        for (GuiShareRemoteState state : remote.values()) {
            if (StringsKt.equals((String)state.identityKey, (String)selfKey, (boolean)true)) continue;
            PlayerEntity owner = this.findLivePlayer(mc, state.minecraftUsername);
            if (StringsKt.isBlank((CharSequence)world) || !Intrinsics.areEqual((Object)world, (Object)state.world) || owner == null || Intrinsics.areEqual((Object)owner, (Object)mc.player)) {
                RemoteGuiPanel foreign;
                RemoteGuiPanel remoteGuiPanel = foreign = panels.get(state.identityKey);
                if (remoteGuiPanel != null) {
                    remoteGuiPanel.markClosed();
                }
                continue;
            }
            RemoteGuiPanel panel = panels.get(state.identityKey);
            if (panel == null) {
                if (!state.open) continue;
                panel = new RemoteGuiPanel(state);
                ((Map)panels).put(state.identityKey, panel);
            }
            panel.applyState(state);
        }
        panels.entrySet().removeIf(entry -> {
            if (!remote.containsKey(entry.getKey())) {
                entry.getValue().markClosed();
            }
            return entry.getValue().isFinished();
        });
        Set<String> set = panels.keySet();
        Intrinsics.checkNotNullExpressionValue(set, (String)"<get-keys>(...)");
        RemoteGuiPanelRenderer.pruneIdentities(set);
    }

    private final PlayerEntity findLivePlayer(MinecraftClient mc, String username) {
        Object object;
        MinecraftClient minecraftClient2 = mc;
        if ((minecraftClient2 != null ? minecraftClient2.world : null) == null || (object = (CharSequence)username) == null || StringsKt.isBlank((CharSequence)object)) {
            return null;
        }
        ClientWorld clientWorld3 = mc.world;
        Intrinsics.checkNotNull((Object)clientWorld3);
        for (AbstractClientPlayerEntity player : clientWorld3.getPlayers()) {
            if (player.isRemoved()) continue;
            String playerName = player.getGameProfile().name();
            if (playerName == null || !StringsKt.equals(playerName, username, true)) continue;
            return (PlayerEntity)player;
        }
        return null;
    }

    private final void captureAnchor(MinecraftClient mc) {
        ClientPlayerEntity clientPlayerEntity2 = mc.player;
        Intrinsics.checkNotNull((Object)clientPlayerEntity2);
        Vec3d vec3d2 = clientPlayerEntity2.getEyePos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getEyePosition(...)");
        Vec3d eyePos = vec3d2;
        ClientPlayerEntity clientPlayerEntity3 = mc.player;
        Intrinsics.checkNotNull((Object)clientPlayerEntity3);
        Vec3d vec3d3 = clientPlayerEntity3.getRotationVector();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"getLookAngle(...)");
        Vec3d look = vec3d3;
        Vec3d vec3d4 = eyePos.add(look.multiply(1.55));
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"add(...)");
        anchor = vec3d4;
        ClientPlayerEntity clientPlayerEntity4 = mc.player;
        Intrinsics.checkNotNull((Object)clientPlayerEntity4);
        anchorYaw = clientPlayerEntity4.getYaw();
        ClientPlayerEntity clientPlayerEntity5 = mc.player;
        Intrinsics.checkNotNull((Object)clientPlayerEntity5);
        anchorPitch = clientPlayerEntity5.getPitch();
    }

    private final String buildIdentityKey(String profileUsername, String minecraftUsername) {
        return profileUsername + "|" + minecraftUsername;
    }

    private final String resolveProfileUsername(String fallback) {
        try {
            String name = ProfileIdentity.username(fallback);
            CharSequence charSequence = name;
            if (!(charSequence == null || StringsKt.isBlank((CharSequence)charSequence))) {
                return ((Object)StringsKt.trim((CharSequence)name)).toString();
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return fallback;
    }

    private final String resolveMinecraftUsername(MinecraftClient mc) {
        if (mc.player == null) {
            return "";
        }
        ClientPlayerEntity clientPlayerEntity2 = mc.player;
        Intrinsics.checkNotNull((Object)clientPlayerEntity2);
        String name = clientPlayerEntity2.getGameProfile().name();
        String string = name;
        if (string == null || (string = ((Object)StringsKt.trim((CharSequence)string)).toString()) == null) {
            string = "";
        }
        return string;
    }

    private final String currentWorldId(MinecraftClient mc) {
        try {
            if (mc.world != null) {
                String scope;
                ClientWorld clientWorld3 = mc.world;
                Intrinsics.checkNotNull((Object)clientWorld3);
                String string = clientWorld3.getRegistryKey().getValue().toString();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
                String dimension = string;
                String server = this.serverScope(mc);
                if (StringsKt.isBlank((CharSequence)server)) {
                    return "singleplayer|" + dimension;
                }
                String string2 = scope = server + "\u0000" + dimension;
                Charset charset = StandardCharsets.UTF_8;
                Intrinsics.checkNotNullExpressionValue((Object)charset, (String)"UTF_8");
                byte[] byArray = string2.getBytes(charset);
                Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"getBytes(...)");
                String string3 = UUID.nameUUIDFromBytes(byArray).toString();
                Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toString(...)");
                return string3;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return "";
    }

    private final String serverScope(MinecraftClient mc) {
        String string;
        if (mc.getCurrentServerEntry() == null) {
            string = "";
        } else {
            ServerInfo serverInfo2 = mc.getCurrentServerEntry();
            Intrinsics.checkNotNull((Object)serverInfo2);
            String string2 = serverInfo2.address;
            string = string2;
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"ip");
        }
        String raw = string;
        String string3 = ((Object)StringsKt.trim((CharSequence)raw)).toString();
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string4 = string3.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string4, (String)"toLowerCase(...)");
        String host = this.stripPort(string4);
        while (String.valueOf(host).endsWith(".")) {
            Intrinsics.checkNotNullExpressionValue((Object)host.substring(0, host.length() - 1), (String)"substring(...)");
        }
        if (StringsKt.isBlank((CharSequence)host) || this.isLiteralAddress(host)) {
            return host;
        }
        return this.registrableDomain(host);
    }

    private final String stripPort(String address) {
        if (address.startsWith("[")) {
            int end = address.indexOf(']');
            if (end > 1) {
                return address.substring(1, end);
            }
            return address;
        }
        int colon = address.indexOf(':');
        if (colon < 0 || colon != address.lastIndexOf(':')) {
            return address;
        }
        return address.substring(0, colon);
    }

    private final boolean isLiteralAddress(String host) {
        if (String.valueOf(host).indexOf((char)':') >= 0) {
            return true;
        }
        return LITERAL_IPV4.matches((CharSequence)host);
    }

    private final String registrableDomain(String host) {
        List<String> labels = Arrays.asList(host.split(java.util.regex.Pattern.quote(".")));
        if (labels.size() <= 2) {
            return host;
        }
        String lastTwo = labels.get(labels.size() - 2) + "." + labels.get(labels.size() - 1);
        if (MULTI_PART_SUFFIXES.contains(lastTwo)) {
            return labels.get(labels.size() - 3) + "." + lastTwo;
        }
        return lastTwo;
    }

    private static final double renderablePanels$lambda$0(MinecraftClient $mc, Vec3d $eye, RemoteGuiPanel panel) {
        Intrinsics.checkNotNull((Object)panel);
        return INSTANCE.liveDistanceToSqr($mc, panel, $eye);
    }

    private static final double renderablePanels$lambda$1(Function1 $tmp0, Object p0) {
        return ((Number)$tmp0.invoke(p0)).doubleValue();
    }

    private static final double renderablePanels$lambda$2(MinecraftClient $mc, Vec3d $eye, RemoteGuiPanel panel) {
        Intrinsics.checkNotNull((Object)panel);
        return INSTANCE.liveDistanceToSqr($mc, panel, $eye);
    }

    private static final double renderablePanels$lambda$3(Function1 $tmp0, Object p0) {
        return ((Number)$tmp0.invoke(p0)).doubleValue();
    }

    private static final boolean tickPanels$lambda$0(Map $remote, Map.Entry entry) {
        Intrinsics.checkNotNullParameter((Object)entry, (String)"entry");
        if (!$remote.containsKey(entry.getKey())) {
            ((RemoteGuiPanel)entry.getValue()).markClosed();
        }
        return ((RemoteGuiPanel)entry.getValue()).isFinished();
    }

    private static final boolean tickPanels$lambda$1(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    static {
        MULTI_PART_SUFFIXES = Set.of(
            "co.uk", "org.uk", "me.uk", "ac.uk", "com.br", "com.au", "co.jp", "co.kr", "co.nz", "co.za",
            "com.ua", "net.ua", "org.ua", "com.ru", "net.ru", "org.ru", "pp.ru", "msk.ru", "spb.ru",
            "com.pl", "com.tr", "com.mx", "com.ar", "co.il", "com.cn", "co.in"
        );
        client = new GuiShareClient();
        panels = new HashMap();
        Vec3d vec3d2 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
        anchor = vec3d2;
        LITERAL_IPV4 = new Regex("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
    }
}

