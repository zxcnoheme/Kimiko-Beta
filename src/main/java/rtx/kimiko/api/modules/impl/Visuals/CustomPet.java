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
 *  kotlin.text.StringsKt
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.Entity.RemovalReason
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.world.World
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.player.AttackEntityEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Visuals.cosmetics.CosmeticSiteState;
import rtx.kimiko.api.modules.impl.Visuals.custompet.CustomPetVariant;
import rtx.kimiko.api.modules.impl.Visuals.custompet.control.CustomPetFollowerController;
import rtx.kimiko.api.modules.impl.Visuals.custompet.entity.CustomPetEntity;
import rtx.kimiko.api.modules.impl.Visuals.custompet.sync.CustomPetRemoteState;
import rtx.kimiko.api.modules.impl.Visuals.custompet.sync.CustomPetSyncClient;
import rtx.kimiko.utils.profile.ProfileIdentity;
import sigil.protect.Level;
import sigil.protect.Protect;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00a2\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\b\u0018\u0000 \u0088\u00012\u00020\u0001:\u0002\u0088\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u0004H\u0015b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\t\u0010\u0003J+\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\fb\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0010H\u0007b\u0002\b\f\u00a2\u0006\u0004\b\u0011\u0010\u0012J)\u0010\u0015\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\r\u00a2\u0006\u0004\b\u0015\u0010\u0016J)\u0010\u0017\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\r\u00a2\u0006\u0004\b\u0017\u0010\u0016J)\u0010\u0018\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\r\u00a2\u0006\u0004\b\u0018\u0010\u0016J\u001f\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ'\u0010 \u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u001eH\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\r\u00a2\u0006\u0004\b \u0010!J\u001f\u0010\"\u001a\u00020\u0004H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\r\u00a2\u0006\u0004\b\"\u0010\u0003J\u0017\u0010%\u001a\u00020\u00132\u0006\u0010$\u001a\u00020#H\u0002\u00a2\u0006\u0004\b%\u0010&J'\u0010'\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0013H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b'\u0010\u0016J\u001b\u0010+\u001a\u0004\u0018\u00010*2\b\u0010)\u001a\u0004\u0018\u00010(H\u0002\u00a2\u0006\u0004\b+\u0010,J\u001d\u0010/\u001a\u00020\u00042\f\u0010.\u001a\b\u0012\u0004\u0012\u00020(0-H\u0002\u00a2\u0006\u0004\b/\u00100J\u000f\u00101\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b1\u0010\u0003J\u0019\u00102\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0002\u00a2\u0006\u0004\b2\u0010\u0016J\u000f\u00103\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b3\u0010\u0003J\u0017\u00105\u001a\u0002042\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b5\u00106J\u0017\u00107\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b7\u0010\u0016J#\u0010:\u001a\u00020(2\b\u00108\u001a\u0004\u0018\u00010(2\b\u00109\u001a\u0004\u0018\u00010(H\u0002\u00a2\u0006\u0004\b:\u0010;J\u001f\u0010=\u001a\u00020(H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078<\u00a2\u0006\u0004\b=\u0010>J\u001f\u0010?\u001a\u00020(H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078<\u00a2\u0006\u0004\b?\u0010>J\u001f\u0010@\u001a\u00020(H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078<\u00a2\u0006\u0004\b@\u0010>J\u001f\u0010A\u001a\u00020\u001eH\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078<\u00a2\u0006\u0004\bA\u0010BJ\u000f\u0010C\u001a\u000204H\u0002\u00a2\u0006\u0004\bC\u0010DJ\u000f\u0010E\u001a\u000204H\u0002\u00a2\u0006\u0004\bE\u0010DJ\u000f\u0010F\u001a\u000204H\u0002\u00a2\u0006\u0004\bF\u0010DJ\u000f\u0010G\u001a\u000204H\u0002\u00a2\u0006\u0004\bG\u0010DJ\u000f\u0010H\u001a\u000204H\u0002\u00a2\u0006\u0004\bH\u0010DJ\u000f\u0010I\u001a\u00020(H\u0002\u00a2\u0006\u0004\bI\u0010>J\u0011\u0010J\u001a\u0004\u0018\u00010\u0013H\u0002\u00a2\u0006\u0004\bJ\u0010KJ\u000f\u0010M\u001a\u00020LH\u0002\u00a2\u0006\u0004\bM\u0010NJ\u000f\u0010O\u001a\u00020(H\u0002\u00a2\u0006\u0004\bO\u0010>J\u000f\u0010P\u001a\u00020LH\u0002\u00a2\u0006\u0004\bP\u0010NJ\u001f\u0010S\u001a\u00020(2\u0006\u0010Q\u001a\u00020(2\u0006\u0010R\u001a\u00020(H\u0002\u00a2\u0006\u0004\bS\u0010;R\u0014\u0010U\u001a\u00020T8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bU\u0010VR \u0010X\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00130W8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bX\u0010YR \u0010Z\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00190W8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bZ\u0010YR\u0014\u0010\\\u001a\u00020[8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\\\u0010]R\u0016\u0010^\u001a\u0002048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b^\u0010_R\u0016\u0010`\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b`\u0010aR\u0016\u0010b\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bb\u0010aR\u0016\u0010c\u001a\u00020L8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010dR\u0016\u0010e\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\be\u0010aR\u0016\u0010f\u001a\u00020L8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bf\u0010dR\u0016\u0010g\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bg\u0010aR\u0016\u0010h\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bh\u0010aR\u0016\u0010i\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bi\u0010aR\u0016\u0010j\u001a\u00020L8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bj\u0010dR\u0016\u0010k\u001a\u00020L8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bk\u0010dR\u0016\u0010l\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bl\u0010aR\u0016\u0010m\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bm\u0010aR\u0016\u0010n\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bn\u0010aR\u0016\u0010o\u001a\u00020L8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bo\u0010dR\u0016\u0010p\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bp\u0010aR\u0016\u0010q\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bq\u0010aR\u0016\u0010r\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\br\u0010aR\u0016\u0010s\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bs\u0010aR\u0016\u0010t\u001a\u00020L8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bt\u0010dR\u0016\u0010u\u001a\u00020(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bu\u0010vR\u0016\u0010w\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bw\u0010xR\u0016\u0010y\u001a\u00020(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\by\u0010vR\u0016\u0010z\u001a\u0002048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bz\u0010_R\u0016\u0010{\u001a\u0002048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b{\u0010_R\u0016\u0010}\u001a\u00020|8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b}\u0010~R\u0016\u0010\u007f\u001a\u00020|8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u007f\u0010~R\u0018\u0010\u0080\u0001\u001a\u00020|8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0080\u0001\u0010~R\u001a\u0010\u0082\u0001\u001a\u00030\u0081\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\b\n\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0018\u0010\u0084\u0001\u001a\u0002048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0084\u0001\u0010_R\u0018\u0010\u0085\u0001\u001a\u0002048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0085\u0001\u0010_R\u0018\u0010\u0086\u0001\u001a\u0002048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0086\u0001\u0010_R\u0018\u0010\u0087\u0001\u001a\u00020|8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0087\u0001\u0010~\u00a8\u0006\u0089\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/CustomPet;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "onDisable", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "STD", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "Lrtx/kimiko/api/events/impl/player/AttackEntityEvent;", "onAttackEntity", "(Lrtx/kimiko/api/events/impl/player/AttackEntityEvent;)V", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;", "pet", "tickUfoBehaviors", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;)V", "tickGoatBehaviors", "tickChekushkaBehaviors", "", "min", "max", "randomWindow", "(JJ)J", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;", "selectedVariant", "tickSync", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;)V", "tickRemotePets", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/sync/CustomPetRemoteState;", "remoteState", "ensureRemotePet", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/sync/CustomPetRemoteState;)Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;", "applyRemoteUfoBeam", "", "username", "Lnet/minecraft/PlayerEntity;", "findPlayerByName", "(Ljava/lang/String;)Lnet/minecraft/PlayerEntity;", "", "activeIdentityKeys", "removeInactiveRemotePets", "(Ljava/util/Set;)V", "clearRemotePets", "removeRemotePetEntity", "resetAll", "", "hasSignificantPoseChange", "(Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;)Z", "capturePublishedPose", "profileUsername", "minecraftUsername", "buildIdentityKey", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "MAX", "currentWorldId", "()Ljava/lang/String;", "resolveProfileUsername", "resolveMinecraftUsername", "getSelectedVariant", "()Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;", "isOwlSelected", "()Z", "isChekushkaSelected", "isGoatSelected", "isNightmareBbSelected", "isUfoSelected", "getSelectedPetKind", "activeLocalPet", "()Lrtx/kimiko/api/modules/impl/Visuals/custompet/entity/CustomPetEntity;", "", "getSelectedRobotType", "()I", "resolveSyncHost", "resolveSyncPort", "encodedPayload", "encodedKey", "decodeHiddenValue", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/control/CustomPetFollowerController;", "localController", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/control/CustomPetFollowerController;", "", "remotePets", "Ljava/util/Map;", "remoteUfoCalmSince", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/sync/CustomPetSyncClient;", "syncClient", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/sync/CustomPetSyncClient;", "petPathBroken", "Z", "nextConnectAttemptAt", "J", "nextSyncPushAt", "chekushkaTargetId", "I", "chekushkaTargetExpireAt", "chekushkaCombatTargetId", "chekushkaCombatUntil", "nextChekushkaLoveAt", "nextChekushkaSipAt", "lastOwnerHurtTime", "ufoTargetId", "ufoTargetExpireAt", "ufoAbductUntil", "ufoCalmSince", "goatTargetId", "goatTargetExpireAt", "nextGoatHeadbuttAt", "nextGoatLoveAt", "nextGoatScreamAt", "lastGoatOwnerHurtTime", "lastIdentityKey", "Ljava/lang/String;", "lastPublishedVariant", "Lrtx/kimiko/api/modules/impl/Visuals/custompet/CustomPetVariant;", "lastPublishedPetKind", "lastPublishedShareState", "hasPublishedPose", "", "lastPublishedX", "D", "lastPublishedY", "lastPublishedZ", "", "lastPublishedYaw", "F", "lastPublishedMoving", "lastPublishedUmbrella", "lastPublishedAirborne", "lastPublishedAnimationSpeed", "Companion", "rtx.kimiko:kimiko"})
public final class CustomPet
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final CustomPetFollowerController localController = new CustomPetFollowerController();
    @NotNull
    private final Map<String, CustomPetEntity> remotePets = new HashMap();
    @NotNull
    private final Map<String, Long> remoteUfoCalmSince = new HashMap();
    @NotNull
    private final CustomPetSyncClient syncClient = new CustomPetSyncClient();
    private boolean petPathBroken;
    private long nextConnectAttemptAt;
    private long nextSyncPushAt;
    private int chekushkaTargetId;
    private long chekushkaTargetExpireAt;
    private int chekushkaCombatTargetId;
    private long chekushkaCombatUntil;
    private long nextChekushkaLoveAt;
    private long nextChekushkaSipAt;
    private int lastOwnerHurtTime;
    private int ufoTargetId;
    private long ufoTargetExpireAt;
    private long ufoAbductUntil;
    private long ufoCalmSince;
    private int goatTargetId;
    private long goatTargetExpireAt;
    private long nextGoatHeadbuttAt;
    private long nextGoatLoveAt;
    private long nextGoatScreamAt;
    private int lastGoatOwnerHurtTime;
    @NotNull
    private String lastIdentityKey = "";
    @NotNull
    private CustomPetVariant lastPublishedVariant = CustomPetVariant.NITWIT;
    @NotNull
    private String lastPublishedPetKind = "frog";
    private boolean lastPublishedShareState = true;
    private boolean hasPublishedPose;
    private double lastPublishedX;
    private double lastPublishedY;
    private double lastPublishedZ;
    private float lastPublishedYaw;
    private boolean lastPublishedMoving;
    private boolean lastPublishedUmbrella;
    private boolean lastPublishedAirborne;
    private double lastPublishedAnimationSpeed = 1.0;
    private static final long CONNECT_RETRY_MS = 5000L;
    private static final long SHARED_STATE_PUSH_MS = 80L;
    private static final long SHARED_HEARTBEAT_MS = 1000L;
    private static final long HIDDEN_STATE_PUSH_MS = 1000L;
    private static final double POSITION_SYNC_THRESHOLD_SQR = 0.0036;
    private static final float YAW_SYNC_THRESHOLD = 2.0f;
    private static final double ANIMATION_SYNC_THRESHOLD = 0.1;
    private static final long CHEKUSHKA_COMBAT_HOLD_MS = 2500L;
    private static final long UFO_ABDUCT_HOLD_MS = 3200L;
    private static final long UFO_BEAM_WARMUP_MS = 2600L;
    private static final long UFO_BEAM_PHASE_MS = 9000L;
    private static final long UFO_SCAN_PHASE_MS = 11000L;
    private static final long UFO_REST_PHASE_MS = 7000L;
    @JvmField
    @Nullable
    public static CustomPet INSTANCE;

    public CustomPet() {
        super("Custom Pet", "Клиентский питомец-компаньон рядом с игроком", Category.VISUALS);
        INSTANCE = this;
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onDisable() {
        this.resetAll();
    }

    @EventHandler
    @Protect(value=Level.STD)
    public final void onTick(@NotNull TickEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!event.isPre()) {
            return;
        }
        ClientPlayerEntity player = this.mc.player;
        ClientWorld level = this.mc.world;
        if (player == null || level == null) {
            this.resetAll();
            return;
        }
        if (this.petPathBroken) {
            return;
        }
        if (((CharSequence)CustomPet.Companion.site().petKind()).length() == 0) {
            this.localController.reset();
            try {
                this.tickRemotePets();
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            return;
        }
        try {
            CustomPetEntity localPet;
            CustomPetVariant selectedVariant = this.getSelectedVariant();
            boolean owl = this.isOwlSelected();
            boolean chekushka = this.isChekushkaSelected();
            boolean goat = this.isGoatSelected();
            boolean nightmareBb = this.isNightmareBbSelected();
            boolean ufo = this.isUfoSelected();
            this.localController.tick((PlayerEntity)player, selectedVariant, owl, chekushka, goat, nightmareBb, ufo);
            CustomPetEntity customPetEntity = localPet = this.localController.getPet();
            if (customPetEntity != null) {
                customPetEntity.setRobotType(this.getSelectedRobotType());
            }
            this.tickChekushkaBehaviors(localPet);
            this.tickGoatBehaviors(localPet);
            this.tickUfoBehaviors(localPet);
            this.tickSync(selectedVariant);
            this.tickRemotePets();
        }
        catch (Throwable throwable) {
            this.petPathBroken = true;
            try {
                this.resetAll();
            }
            catch (Throwable throwable2) {
                // empty catch block
            }
        }
    }

    @EventHandler
    public final void onAttackEntity(@NotNull AttackEntityEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        Entity target = event.getTarget();
        if (!(target instanceof LivingEntity) || Intrinsics.areEqual((Object)target, (Object)player)) {
            return;
        }
        CustomPetEntity localPet = this.activeLocalPet();
        if (localPet == null || localPet.isRemoved()) {
            return;
        }
        long now = System.currentTimeMillis();
        if (this.isChekushkaSelected() && localPet.isChekushka()) {
            this.chekushkaTargetId = ((LivingEntity)target).getId();
            this.chekushkaTargetExpireAt = now + 8000L;
            this.chekushkaCombatTargetId = ((LivingEntity)target).getId();
            this.chekushkaCombatUntil = now + 2500L;
            return;
        }
        if (this.isUfoSelected() && localPet.isUfo()) {
            this.ufoTargetId = ((LivingEntity)target).getId();
            this.ufoTargetExpireAt = now + 8000L;
            this.ufoAbductUntil = now + 3200L;
            return;
        }
        if (this.isGoatSelected() && localPet.isGoat()) {
            this.goatTargetId = ((LivingEntity)target).getId();
            this.goatTargetExpireAt = now + 8000L;
            if (now >= this.nextGoatHeadbuttAt) {
                localPet.triggerGoatAction(CustomPetEntity.GoatAction.HEADBUTT);
                this.nextGoatHeadbuttAt = now + 2500L;
            }
        }
    }

    @Protect(value=Level.STD)
    private final void tickUfoBehaviors(CustomPetEntity pet) {
        long calmFor;
        boolean calm;
        ClientPlayerEntity player = this.mc.player;
        ClientWorld level = this.mc.world;
        if (pet == null || !pet.isUfo() || pet.isRemoved() || player == null || level == null) {
            this.ufoTargetId = 0;
            this.ufoAbductUntil = 0L;
            this.ufoCalmSince = 0L;
            return;
        }
        long now = System.currentTimeMillis();
        if (this.ufoTargetId != 0) {
            if (now > this.ufoTargetExpireAt) {
                this.ufoTargetId = 0;
            } else {
                Entity target = level.getEntityById(this.ufoTargetId);
                if (target instanceof LivingEntity && (((LivingEntity)target).isDead() || ((LivingEntity)target).isRemoved())) {
                    pet.triggerUfoSpin();
                    this.ufoTargetId = 0;
                }
            }
        }
        if (now < this.ufoAbductUntil) {
            pet.setUfoBeamMode(CustomPetEntity.UfoBeamMode.ABDUCT);
            this.ufoCalmSince = 0L;
            return;
        }
        boolean playerStill = player.getVelocity().horizontalLength() < 0.025 && Math.abs(player.getVelocity().y) < 0.08 && !player.isGliding();
        boolean bl = calm = playerStill && !pet.isUfoGrounded() && !pet.isUfoTakingOff() && !pet.hasUfoTrick();
        if (!calm) {
            this.ufoCalmSince = 0L;
            pet.setUfoBeamMode(CustomPetEntity.UfoBeamMode.OFF);
            return;
        }
        if (this.ufoCalmSince == 0L) {
            this.ufoCalmSince = now;
        }
        if ((calmFor = now - this.ufoCalmSince) < 2600L) {
            pet.setUfoBeamMode(CustomPetEntity.UfoBeamMode.OFF);
            return;
        }
        long cyclePosition = (calmFor - 2600L) % 27000L;
        if (cyclePosition < 9000L) {
            pet.setUfoBeamMode(CustomPetEntity.UfoBeamMode.BEAM);
        } else if (cyclePosition < 20000L) {
            pet.setUfoBeamMode(CustomPetEntity.UfoBeamMode.SCAN);
        } else {
            pet.setUfoBeamMode(CustomPetEntity.UfoBeamMode.OFF);
        }
    }

    @Protect(value=Level.STD)
    private final void tickGoatBehaviors(CustomPetEntity pet) {
        boolean calm;
        ClientPlayerEntity player = this.mc.player;
        ClientWorld level = this.mc.world;
        if (pet == null || !pet.isGoat() || pet.isRemoved() || player == null || level == null) {
            this.goatTargetId = 0;
            this.lastGoatOwnerHurtTime = 0;
            return;
        }
        long now = System.currentTimeMillis();
        pet.setGoatPose(player.isSleeping());
        int hurtTime = player.hurtTime;
        if (hurtTime > this.lastGoatOwnerHurtTime && hurtTime >= 9) {
            pet.triggerGoatAction(CustomPetEntity.GoatAction.HURT);
        }
        this.lastGoatOwnerHurtTime = hurtTime;
        if (this.goatTargetId != 0) {
            if (now > this.goatTargetExpireAt) {
                this.goatTargetId = 0;
            } else {
                Entity target = level.getEntityById(this.goatTargetId);
                if (target instanceof LivingEntity && (((LivingEntity)target).isDead() || ((LivingEntity)target).isRemoved())) {
                    pet.triggerGoatAction(CustomPetEntity.GoatAction.CELEBRATE);
                    this.goatTargetId = 0;
                }
            }
        }
        boolean bl = calm = !pet.hasGoatAction() && !pet.isPetMoving() && !pet.isAirborneMode() && !pet.isTouchingWater();
        if (this.nextGoatLoveAt == 0L) {
            this.nextGoatLoveAt = now + this.randomWindow(40000L, 80000L);
        }
        if (this.nextGoatScreamAt == 0L) {
            this.nextGoatScreamAt = now + this.randomWindow(90000L, 200000L);
        }
        if (calm && now >= this.nextGoatLoveAt && pet.squaredDistanceTo((Entity)player) < 12.25) {
            pet.triggerGoatAction(CustomPetEntity.GoatAction.LOVE);
            this.nextGoatLoveAt = now + this.randomWindow(40000L, 80000L);
        }
        if (calm && !pet.hasGoatAction() && now >= this.nextGoatScreamAt) {
            pet.triggerGoatAction(CustomPetEntity.GoatAction.SCREAM);
            this.nextGoatScreamAt = now + this.randomWindow(90000L, 200000L);
        }
    }

    @Protect(value=Level.STD)
    private final void tickChekushkaBehaviors(CustomPetEntity pet) {
        boolean calm;
        Entity combatTarget;
        ClientPlayerEntity player = this.mc.player;
        ClientWorld level = this.mc.world;
        if (pet == null || !pet.isChekushka() || pet.isRemoved() || player == null || level == null) {
            this.chekushkaTargetId = 0;
            this.lastOwnerHurtTime = 0;
            return;
        }
        long now = System.currentTimeMillis();
        int hurtTime = player.hurtTime;
        if (hurtTime > this.lastOwnerHurtTime && hurtTime >= 9) {
            pet.triggerChekushkaAction(CustomPetEntity.ChekushkaAction.HURT);
        }
        this.lastOwnerHurtTime = hurtTime;
        if (this.chekushkaTargetId != 0) {
            if (now > this.chekushkaTargetExpireAt) {
                this.chekushkaTargetId = 0;
            } else {
                Entity target = level.getEntityById(this.chekushkaTargetId);
                if (target instanceof LivingEntity && (((LivingEntity)target).isDead() || ((LivingEntity)target).isRemoved())) {
                    pet.triggerChekushkaAction(CustomPetEntity.ChekushkaAction.CELEBRATE);
                    this.chekushkaTargetId = 0;
                }
            }
        }
        Entity entity2 = combatTarget = now < this.chekushkaCombatUntil && this.chekushkaCombatTargetId != 0 ? level.getEntityById(this.chekushkaCombatTargetId) : null;
        if (combatTarget instanceof LivingEntity && !((LivingEntity)combatTarget).isRemoved() && !((LivingEntity)combatTarget).isDead()) {
            pet.setChekushkaCombat(true, ((LivingEntity)combatTarget).getX(), ((LivingEntity)combatTarget).getY(), ((LivingEntity)combatTarget).getZ());
        } else {
            pet.setChekushkaCombat(false, 0.0, 0.0, 0.0);
            this.chekushkaCombatTargetId = 0;
        }
        boolean bl = calm = !pet.hasChekushkaAction() && !pet.isChekushkaCombat() && !pet.isPetMoving() && !pet.isAirborneMode() && !pet.isTouchingWater();
        if (this.nextChekushkaLoveAt == 0L) {
            this.nextChekushkaLoveAt = now + this.randomWindow(45000L, 90000L);
        }
        if (this.nextChekushkaSipAt == 0L) {
            this.nextChekushkaSipAt = now + this.randomWindow(150000L, 300000L);
        }
        if (calm && now >= this.nextChekushkaLoveAt && pet.squaredDistanceTo((Entity)player) < 12.25) {
            pet.triggerChekushkaAction(CustomPetEntity.ChekushkaAction.LOVE);
            this.nextChekushkaLoveAt = now + this.randomWindow(45000L, 90000L);
        }
        if (calm && !pet.hasChekushkaAction() && now >= this.nextChekushkaSipAt) {
            pet.triggerChekushkaAction(CustomPetEntity.ChekushkaAction.SIP);
            this.nextChekushkaSipAt = now + this.randomWindow(150000L, 300000L);
        }
    }

    private final long randomWindow(long min, long max) {
        return min + (long)(Math.random() * (double)(max - min));
    }

    @Protect(value=Level.STD)
    private final void tickSync(CustomPetVariant selectedVariant) {
        boolean shouldPush;
        String profileUsername = this.resolveProfileUsername();
        String minecraftUsername = this.resolveMinecraftUsername();
        if (StringsKt.isBlank((CharSequence)profileUsername) || StringsKt.isBlank((CharSequence)minecraftUsername)) {
            this.syncClient.disconnect("missing_identity");
            this.clearRemotePets();
            return;
        }
        CustomPetEntity localPet = this.activeLocalPet();
        if (localPet == null || localPet.isRemoved()) {
            return;
        }
        boolean shareEnabled = true;
        String petKind = this.getSelectedPetKind();
        String identityKey = this.buildIdentityKey(profileUsername, minecraftUsername);
        boolean poseChanged = this.hasSignificantPoseChange(localPet);
        this.syncClient.setLocalState(profileUsername, minecraftUsername, this.currentWorldId(), selectedVariant, this.getSelectedRobotType(), petKind, shareEnabled, localPet.getX(), localPet.getY(), localPet.getZ(), localPet.getYaw(), localPet.isPetMoving(), localPet.shouldUseUmbrella(), localPet.isAirborneMode(), localPet.getCurrentAnimationSpeed());
        long now = System.currentTimeMillis();
        if (!this.syncClient.isConnected() && !this.syncClient.isConnecting() && now >= this.nextConnectAttemptAt) {
            this.syncClient.connect(this.resolveSyncHost(), this.resolveSyncPort());
            this.nextConnectAttemptAt = now + 5000L;
        }
        boolean bl = shouldPush = now >= this.nextSyncPushAt || !Intrinsics.areEqual((Object)identityKey, (Object)this.lastIdentityKey) || selectedVariant != this.lastPublishedVariant || !Intrinsics.areEqual((Object)petKind, (Object)this.lastPublishedPetKind) || shareEnabled != this.lastPublishedShareState || poseChanged;
        if (shouldPush) {
            this.syncClient.pushState();
            this.lastIdentityKey = identityKey;
            this.lastPublishedVariant = selectedVariant;
            this.lastPublishedPetKind = petKind;
            this.lastPublishedShareState = shareEnabled;
            this.capturePublishedPose(localPet);
            this.nextSyncPushAt = now + (poseChanged ? 80L : 1000L);
        }
    }

    @Protect(value=Level.STD)
    private final void tickRemotePets() {
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        ClientPlayerEntity localPlayer = this.mc.player;
        Map<String, CustomPetRemoteState> remoteStates = this.syncClient.snapshotRemoteStates();
        String localIdentityKey = this.buildIdentityKey(this.resolveProfileUsername(), this.resolveMinecraftUsername());
        Set activeIdentityKeys = new HashSet();
        for (CustomPetRemoteState remoteState : remoteStates.values()) {
            PlayerEntity owner;
            if (!remoteState.active() || StringsKt.equals((String)remoteState.identityKey(), (String)localIdentityKey, (boolean)true) || (owner = this.findPlayerByName(remoteState.minecraftUsername())) == null || Intrinsics.areEqual((Object)owner, (Object)localPlayer) || owner.isRemoved()) continue;
            CustomPetEntity remotePet = this.ensureRemotePet(remoteState);
            remotePet.applyNetworkState(remoteState);
            activeIdentityKeys.add(remoteState.identityKey());
        }
        this.removeInactiveRemotePets(activeIdentityKeys);
    }

    private final CustomPetEntity ensureRemotePet(CustomPetRemoteState remoteState) {
        ClientWorld clientWorld3 = this.mc.world;
        Intrinsics.checkNotNull((Object)clientWorld3);
        ClientWorld level = clientWorld3;
        boolean wantOwl = remoteState.isOwl();
        boolean wantChekushka = remoteState.isChekushka();
        boolean wantGoat = remoteState.isGoat();
        boolean wantNightmareBb = remoteState.isNightmareBb();
        boolean wantUfo = remoteState.isUfo();
        CustomPetEntity pet = this.remotePets.get(remoteState.identityKey());
        if (pet != null && Intrinsics.areEqual((Object)pet.getEntityWorld(), (Object)level) && !pet.isRemoved() && pet.isOwl() == wantOwl && pet.isChekushka() == wantChekushka && pet.isGoat() == wantGoat && pet.isNightmareBb() == wantNightmareBb && pet.isUfo() == wantUfo) {
            pet.setPetVariant(remoteState.variant());
            this.applyRemoteUfoBeam(pet);
            return pet;
        }
        if (pet != null) {
            this.removeRemotePetEntity(pet);
        }
        CustomPetEntity newPet = new CustomPetEntity((World)level);
        newPet.setOwl(wantOwl);
        newPet.setChekushka(wantChekushka);
        newPet.setGoat(wantGoat);
        newPet.setNightmareBb(wantNightmareBb);
        newPet.setUfo(wantUfo);
        newPet.setPetVariant(remoteState.variant());
        newPet.applyNetworkState(remoteState);
        newPet.snapTo(remoteState.position(), remoteState.yaw());
        this.applyRemoteUfoBeam(newPet);
        level.addEntity((Entity)newPet);
        this.remotePets.put(remoteState.identityKey(), newPet);
        return newPet;
    }

    @Protect(value=Level.CROWN)
    private final void applyRemoteUfoBeam(CustomPetEntity pet) {
        if (!pet.isUfo()) {
            return;
        }
        if (pet.isPetMoving()) {
            this.remoteUfoCalmSince.remove(pet.getUuid().toString());
            pet.setUfoBeamMode(CustomPetEntity.UfoBeamMode.OFF);
            return;
        }
        long now = System.currentTimeMillis();
        Long l = this.remoteUfoCalmSince.computeIfAbsent(pet.getUuid().toString(), k -> now);
        Intrinsics.checkNotNullExpressionValue((Object)l, (String)"computeIfAbsent(...)");
        long since = ((Number)l).longValue();
        long calmFor = now - since;
        if (calmFor < 2600L) {
            pet.setUfoBeamMode(CustomPetEntity.UfoBeamMode.OFF);
            return;
        }
        long cyclePosition = (calmFor - 2600L) % 27000L;
        if (cyclePosition < 9000L) {
            pet.setUfoBeamMode(CustomPetEntity.UfoBeamMode.BEAM);
        } else if (cyclePosition < 20000L) {
            pet.setUfoBeamMode(CustomPetEntity.UfoBeamMode.SCAN);
        } else {
            pet.setUfoBeamMode(CustomPetEntity.UfoBeamMode.OFF);
        }
    }

    private final PlayerEntity findPlayerByName(String username) {
        ClientWorld level = this.mc.world;
        CharSequence charSequence = username;
        if (charSequence == null || StringsKt.isBlank((CharSequence)charSequence) || level == null) {
            return null;
        }
        for (AbstractClientPlayerEntity player : level.getPlayers()) {
            if (player.isRemoved()) continue;
            String playerName = player.getGameProfile().name();
            if (playerName == null || !StringsKt.equals(playerName, username, true)) continue;
            return (PlayerEntity)player;
        }
        return null;
    }

    private final void removeInactiveRemotePets(Set<String> activeIdentityKeys) {
        this.remotePets.entrySet().removeIf(entry -> {
            if (activeIdentityKeys.contains(entry.getKey())) {
                return false;
            }
            this.remoteUfoCalmSince.remove(entry.getValue().getUuid().toString());
            this.removeRemotePetEntity(entry.getValue());
            return true;
        });
    }

    private final void clearRemotePets() {
        for (CustomPetEntity pet : this.remotePets.values()) {
            this.removeRemotePetEntity(pet);
        }
        this.remotePets.clear();
        this.remoteUfoCalmSince.clear();
    }

    private final void removeRemotePetEntity(CustomPetEntity pet) {
        if (pet == null || pet.isRemoved()) {
            return;
        }
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 != null) {
            clientWorld3.removeEntity(pet.getId(), Entity.RemovalReason.DISCARDED);
        }
        pet.discard();
    }

    private final void resetAll() {
        this.localController.reset();
        this.clearRemotePets();
        this.syncClient.disconnect("reset");
        this.nextConnectAttemptAt = 0L;
        this.nextSyncPushAt = 0L;
        this.chekushkaTargetId = 0;
        this.chekushkaTargetExpireAt = 0L;
        this.chekushkaCombatTargetId = 0;
        this.chekushkaCombatUntil = 0L;
        this.nextChekushkaLoveAt = 0L;
        this.nextChekushkaSipAt = 0L;
        this.lastOwnerHurtTime = 0;
        this.ufoTargetId = 0;
        this.ufoTargetExpireAt = 0L;
        this.ufoAbductUntil = 0L;
        this.ufoCalmSince = 0L;
        this.goatTargetId = 0;
        this.goatTargetExpireAt = 0L;
        this.nextGoatHeadbuttAt = 0L;
        this.nextGoatLoveAt = 0L;
        this.nextGoatScreamAt = 0L;
        this.lastGoatOwnerHurtTime = 0;
        this.lastIdentityKey = "";
        this.lastPublishedVariant = this.getSelectedVariant();
        this.lastPublishedShareState = true;
        this.hasPublishedPose = false;
        this.lastPublishedX = 0.0;
        this.lastPublishedY = 0.0;
        this.lastPublishedZ = 0.0;
        this.lastPublishedYaw = 0.0f;
        this.lastPublishedMoving = false;
        this.lastPublishedUmbrella = false;
        this.lastPublishedAirborne = false;
        this.lastPublishedAnimationSpeed = 1.0;
    }

    private final boolean hasSignificantPoseChange(CustomPetEntity pet) {
        double dz;
        double dy;
        if (!this.hasPublishedPose) {
            return true;
        }
        double dx = pet.getX() - this.lastPublishedX;
        if (dx * dx + (dy = pet.getY() - this.lastPublishedY) * dy + (dz = pet.getZ() - this.lastPublishedZ) * dz >= 0.0036) {
            return true;
        }
        float yawDelta = Math.abs(MathHelper.wrapDegrees((float)(pet.getYaw() - this.lastPublishedYaw)));
        if (yawDelta >= 2.0f) {
            return true;
        }
        if (pet.isPetMoving() != this.lastPublishedMoving || pet.shouldUseUmbrella() != this.lastPublishedUmbrella || pet.isAirborneMode() != this.lastPublishedAirborne) {
            return true;
        }
        return Math.abs(pet.getCurrentAnimationSpeed() - this.lastPublishedAnimationSpeed) >= 0.1;
    }

    private final void capturePublishedPose(CustomPetEntity pet) {
        this.hasPublishedPose = true;
        this.lastPublishedX = pet.getX();
        this.lastPublishedY = pet.getY();
        this.lastPublishedZ = pet.getZ();
        this.lastPublishedYaw = pet.getYaw();
        this.lastPublishedMoving = pet.isPetMoving();
        this.lastPublishedUmbrella = pet.shouldUseUmbrella();
        this.lastPublishedAirborne = pet.isAirborneMode();
        this.lastPublishedAnimationSpeed = pet.getCurrentAnimationSpeed();
    }

    private final String buildIdentityKey(String profileUsername, String minecraftUsername) {
        if (profileUsername == null || minecraftUsername == null) {
            return "";
        }
        String normalizedProfile = ((Object)StringsKt.trim((CharSequence)profileUsername)).toString();
        String normalizedMinecraft = ((Object)StringsKt.trim((CharSequence)minecraftUsername)).toString();
        if (((CharSequence)normalizedProfile).length() == 0 || ((CharSequence)normalizedMinecraft).length() == 0) {
            return "";
        }
        return normalizedProfile + "|" + normalizedMinecraft;
    }

    @Protect(value=Level.MAX)
    private final String currentWorldId() {
        try {
            if (this.mc.world != null) {
                ClientWorld clientWorld3 = this.mc.world;
                Intrinsics.checkNotNull((Object)clientWorld3);
                String string = clientWorld3.getRegistryKey().getValue().toString();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
                return string;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return "";
    }

    @Protect(value=Level.MAX)
    private final String resolveProfileUsername() {
        String string = ProfileIdentity.username(this.resolveMinecraftUsername());
        if (string == null) {
            string = "";
        }
        return string;
    }

    @Protect(value=Level.MAX)
    private final String resolveMinecraftUsername() {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return "";
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        String username = player.getGameProfile().name();
        Intrinsics.checkNotNull((Object)username);
        return ((Object)StringsKt.trim((CharSequence)username)).toString();
    }

    @Protect(value=Level.MAX)
    private final CustomPetVariant getSelectedVariant() {
        return Intrinsics.areEqual((Object)"robot", (Object)CustomPet.Companion.site().petKind()) ? CustomPetVariant.ROBOT : CustomPetVariant.NITWIT;
    }

    private final boolean isOwlSelected() {
        return Intrinsics.areEqual((Object)"owl", (Object)CustomPet.Companion.site().petKind());
    }

    private final boolean isChekushkaSelected() {
        return Intrinsics.areEqual((Object)"chekushka", (Object)CustomPet.Companion.site().petKind());
    }

    private final boolean isGoatSelected() {
        return Intrinsics.areEqual((Object)"goat", (Object)CustomPet.Companion.site().petKind());
    }

    private final boolean isNightmareBbSelected() {
        return Intrinsics.areEqual((Object)"nightmare_bb", (Object)CustomPet.Companion.site().petKind());
    }

    private final boolean isUfoSelected() {
        return Intrinsics.areEqual((Object)"ufo", (Object)CustomPet.Companion.site().petKind());
    }

    private final String getSelectedPetKind() {
        String kind = CustomPet.Companion.site().petKind();
        return ((CharSequence)kind).length() == 0 ? "frog" : kind;
    }

    private final CustomPetEntity activeLocalPet() {
        return this.localController.getPet();
    }

    private final int getSelectedRobotType() {
        return 0;
    }

    private final String resolveSyncHost() {
        return this.decodeHiddenValue("SWgpCDEz8s/3/76DuA==", "ZWlGbQ==");
    }

    private final int resolveSyncPort() {
        String rawPort = this.decodeHiddenValue("YkgyLR8=", "TkpC");
        return Integer.parseInt(rawPort);
    }

    private final String decodeHiddenValue(String encodedPayload, String encodedKey) {
        byte[] payload = Base64.getDecoder().decode(encodedPayload);
        byte[] key = Base64.getDecoder().decode(encodedKey);
        byte[] result = new byte[payload.length];
        int n = payload.length;
        for (int index = 0; index < n; ++index) {
            result[index] = (byte)(payload[index] ^ key[index % key.length] ^ index * 17 + 31);
        }
        Charset charset = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue((Object)charset, (String)"UTF_8");
        Charset charset2 = charset;
        return new String(result, charset2);
    }

    @JvmStatic
    @Nullable
    public static final CustomPet getInstance() {
        return Companion.getInstance();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\rR\u0014\u0010\u0010\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\rR\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0013R\u0014\u0010\u0018\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\rR\u0014\u0010\u0019\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\rR\u0014\u0010\u001a\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\rR\u0014\u0010\u001b\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\rR\u0014\u0010\u001c\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\rR\u0014\u0010\u001d\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\rR\u001d\u0010\u001f\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 \u00a8\u0006!"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/CustomPet.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/CustomPet;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/CustomPet;", "Lrtx/kimiko/api/modules/impl/Visuals/cosmetics/CosmeticSiteState;", "site", "()Lrtx/kimiko/api/modules/impl/Visuals/cosmetics/CosmeticSiteState;", "", "CONNECT_RETRY_MS", "J", "SHARED_STATE_PUSH_MS", "SHARED_HEARTBEAT_MS", "HIDDEN_STATE_PUSH_MS", "", "POSITION_SYNC_THRESHOLD_SQR", "D", "", "YAW_SYNC_THRESHOLD", "F", "ANIMATION_SYNC_THRESHOLD", "CHEKUSHKA_COMBAT_HOLD_MS", "UFO_ABDUCT_HOLD_MS", "UFO_BEAM_WARMUP_MS", "UFO_BEAM_PHASE_MS", "UFO_SCAN_PHASE_MS", "UFO_REST_PHASE_MS", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/CustomPet;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final CustomPet getInstance() {
            CustomPet module = ModuleManager.Companion.get().get(CustomPet.class);
            CustomPet customPet = module;
            if (customPet == null) {
                customPet = INSTANCE;
            }
            return customPet;
        }

        private final CosmeticSiteState site() {
            return CosmeticSiteState.Companion.get();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

