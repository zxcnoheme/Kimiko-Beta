/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.NoWhenBranchMatchedException
 *  kotlin.collections.CollectionsKt
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ItemEntity
 *  net.minecraft.entity.projectile.PersistentProjectileEntity
 *  net.minecraft.entity.projectile.ProjectileEntity
 *  net.minecraft.entity.projectile.thrown.SnowballEntity
 *  net.minecraft.entity.projectile.thrown.EggEntity
 *  net.minecraft.entity.projectile.thrown.EnderPearlEntity
 *  net.minecraft.entity.projectile.TridentEntity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.Items
 *  net.minecraft.util.math.Box
 *  net.minecraft.util.hit.HitResult.Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.gui.DrawContext
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
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Vector4f
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Box;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.RaycastContext;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector4f;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.MultiSelectSetting;
import rtx.kimiko.api.modules.settings.impl.NumberSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;
import rtx.kimiko.utils.render.render2d.ClientPalette;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.shape.BuiltShape;
import rtx.kimiko.utils.render.util.renderitem.RenderItem;

@Feature(value={"predictions"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0082\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u008a\u00012\u00020\u0001:\u0010\u008b\u0001\u008c\u0001\u008d\u0001\u008e\u0001\u008f\u0001\u0090\u0001\u0091\u0001\u008a\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0014\u00a2\u0006\u0004\b\b\u0010\u0003J\u001b\u0010\f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0003b\u0002\b\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000eH\u0003b\u0002\b\u000b\u00a2\u0006\u0004\b\u000f\u0010\u0010JM\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00110\u001cH\u0002\u00a2\u0006\u0004\b\u001f\u0010 J'\u0010#\u001a\u00020\"2\u0006\u0010!\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b#\u0010$J5\u0010+\u001a\u00020\u00072\u0006\u0010&\u001a\u00020%2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020\u00112\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00110*H\u0002\u00a2\u0006\u0004\b+\u0010,J7\u0010/\u001a\u00020\u00112\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00110*2\u0006\u0010-\u001a\u00020\u00162\u0006\u0010)\u001a\u00020\u00112\b\u0010.\u001a\u0004\u0018\u00010\u0011H\u0002\u00a2\u0006\u0004\b/\u00100JW\u0010;\u001a\u00020\u00072\u0006\u00102\u001a\u0002012\u0006\u00104\u001a\u0002032\u0006\u00105\u001a\u00020\u00112\u0006\u00106\u001a\u00020\u00112\u0006\u0010)\u001a\u00020\u00112\u0006\u00107\u001a\u00020\u00112\u0006\u00108\u001a\u00020\u00112\u0006\u00109\u001a\u00020\u00162\u0006\u0010:\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b;\u0010<J?\u0010A\u001a\u00020\u00072\u0006\u00102\u001a\u0002012\u0006\u00104\u001a\u0002032\u0006\u0010=\u001a\u00020\u001a2\u0006\u0010>\u001a\u00020\u001a2\u0006\u0010?\u001a\u00020\u001a2\u0006\u0010@\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\bA\u0010BJO\u0010H\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010)\u001a\u00020\u00112\u0006\u0010C\u001a\u00020\u00162\u0006\u0010D\u001a\u00020\u00162\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00110*2\u0006\u0010E\u001a\u00020\u001e2\b\u0010G\u001a\u0004\u0018\u00010FH\u0002\u00a2\u0006\u0004\bH\u0010IJ\u001f\u0010M\u001a\u00020\u00072\u0006\u0010K\u001a\u00020J2\u0006\u0010L\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\bM\u0010NJ\u0017\u0010O\u001a\u00020\u00072\u0006\u0010K\u001a\u00020JH\u0002\u00a2\u0006\u0004\bO\u0010PJ\u0019\u0010Q\u001a\u0004\u0018\u00010F2\u0006\u0010!\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\bQ\u0010RJ\u000f\u0010\u001b\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b\u001b\u0010SJ\u0017\u0010U\u001a\u00020\u00162\u0006\u0010T\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bU\u0010VJ\u0017\u0010W\u001a\u00020\u00162\u0006\u0010-\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\bW\u0010XJ\u001f\u0010Y\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\bY\u0010ZJ\u001f\u0010]\u001a\u00020\u001e2\u0006\u0010\\\u001a\u00020[2\u0006\u0010\u0013\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b]\u0010^J\u0017\u0010_\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b_\u0010`J\u0017\u0010a\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\ba\u0010`J\u0019\u0010c\u001a\u0004\u0018\u00010b2\u0006\u0010!\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\bc\u0010dJ\u0017\u0010f\u001a\u00020\u00142\u0006\u0010e\u001a\u00020bH\u0002\u00a2\u0006\u0004\bf\u0010gR\u0014\u0010i\u001a\u00020h8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bi\u0010jR\u0014\u0010l\u001a\u00020k8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bl\u0010mR\u0014\u0010o\u001a\u00020n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bo\u0010pR\u0014\u0010q\u001a\u00020k8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bq\u0010mR\u0014\u0010s\u001a\u00020r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bs\u0010tR\u0014\u0010u\u001a\u00020h8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bu\u0010jR\u0014\u0010w\u001a\u00020v8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bw\u0010xR\u0014\u0010z\u001a\u00020y8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bz\u0010{R\u001a\u0010|\u001a\b\u0012\u0004\u0012\u00020\u00110\u001c8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b|\u0010}R!\u0010\u007f\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\"0~8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u007f\u0010\u0080\u0001R\u001e\u0010\u0082\u0001\u001a\t\u0012\u0004\u0012\u00020\u00160\u0081\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0082\u0001\u0010\u0083\u0001R$\u0010\u0085\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0016\u0012\u0005\u0012\u00030\u0084\u00010~8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0085\u0001\u0010\u0080\u0001R\u001c\u0010\u0086\u0001\u001a\b\u0012\u0004\u0012\u00020J0\u001c8\u0002X\u0082\u0004\u00a2\u0006\u0007\n\u0005\b\u0086\u0001\u0010}R\u0018\u0010\u0088\u0001\u001a\u00030\u0087\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0088\u0001\u0010\u0089\u0001\u00ca\u0001\u0013\b\u0092\u0001\u0012\u000e\b\u0093\u0001\u0012\t\b\fJ\u0005\b\b(\u0094\u0001\u00a8\u0006\u0095\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "fadeOutSeconds", "()F", "", "onDisable", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "Lrtx/kimiko/api/events/impl/render/HudRenderEvent;", "onHudRender", "(Lrtx/kimiko/api/events/impl/render/HudRenderEvent;)V", "Lnet/minecraft/Vec3d;", "start", "velocity", "Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$ProjectileProfile;", "profile", "", "maxTicks", "Lnet/minecraft/Entity;", "collider", "", "simulationLimitSqr", "", "points", "", "simulate", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$ProjectileProfile;ILnet/minecraft/Entity;DLjava/util/List;)Z", "entity", "Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$ProjectilePath;", "projectilePathFor", "(Lnet/minecraft/Entity;Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$ProjectileProfile;D)Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$ProjectilePath;", "Lnet/minecraft/VertexConsumerProvider$Immediate;", "provider", "Lnet/minecraft/MatrixStack;", "stack", "cameraPos", "", "drawPath", "(Lnet/minecraft/VertexConsumerProvider$Immediate;Lnet/minecraft/MatrixStack;Lnet/minecraft/Vec3d;Ljava/util/List;)V", "index", "previousOffset", "pathOffset", "(Ljava/util/List;ILnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;)Lnet/minecraft/Vec3d;", "Lnet/minecraft/VertexConsumer;", "consumer", "Lnet/minecraft/MatrixStack$Entry;", "pose", "from", "to", "fromOffset", "toOffset", "fromColor", "toColor", "emitRibbonSegment", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;II)V", "x", "y", "z", "rawColor", "vertex", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;DDDI)V", "entityId", "tick", "visible", "Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$MarkerVisual;", "visual", "addLandingMarker", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;Lnet/minecraft/Vec3d;IILjava/util/List;ZLrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$MarkerVisual;)V", "Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$LandingMarker;", "marker", "gradientIndex", "drawMarkerBackground", "(Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$LandingMarker;I)V", "drawMarkerItem", "(Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$LandingMarker;)V", "markerVisualFor", "(Lnet/minecraft/Entity;)Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$MarkerVisual;", "()D", "progress", "trajectoryColorAt", "(F)I", "markerColor", "(I)I", "shouldRenderLive", "(Lnet/minecraft/Entity;Lnet/minecraft/Vec3d;)Z", "Lnet/minecraft/ItemEntity;", "item", "isGroundedItem", "(Lnet/minecraft/ItemEntity;Lnet/minecraft/Vec3d;)Z", "isInRange", "(Lnet/minecraft/Entity;)Z", "isOwnedByPlayer", "Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$ProjectileTarget;", "targetFor", "(Lnet/minecraft/Entity;)Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$ProjectileTarget;", "target", "profileFor", "(Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$ProjectileTarget;)Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$ProjectileProfile;", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "generalSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "targets", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "landingMarker", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "markerItems", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "range", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "colorsSeparator", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "colorMode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "lineColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "simulationPoints", "Ljava/util/List;", "", "projectilePaths", "Ljava/util/Map;", "", "liveProjectileIds", "Ljava/util/Set;", "Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$LandingInterpolation;", "landingInterpolations", "landingMarkers", "Lorg/joml/Vector4f;", "projectionScratch", "Lorg/joml/Vector4f;", "Companion", "ProjectileTarget", "MarkerKind", "ProjectilePath", "ProjectileProfile", "MarkerVisual", "LandingMarker", "LandingInterpolation", "Lrtx/kimiko/api/liteapi/Feature;", "value", "predictions", "rtx.kimiko:kimiko"})
public final class SelfPredictions
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting generalSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Основное"));
    @NotNull
    private final MultiSelectSetting targets;
    @NotNull
    private final BooleanSetting landingMarker;
    @NotNull
    private final MultiSelectSetting markerItems;
    @NotNull
    private final NumberSetting range;
    @NotNull
    private final SeparatorSetting colorsSeparator;
    @NotNull
    private final ModeSetting colorMode;
    @NotNull
    private final ColorSetting lineColor;
    @NotNull
    private final List<Vec3d> simulationPoints;
    @NotNull
    private final Map<Integer, ProjectilePath> projectilePaths;
    @NotNull
    private final Set<Integer> liveProjectileIds;
    @NotNull
    private final Map<Integer, LandingInterpolation> landingInterpolations;
    @NotNull
    private final List<LandingMarker> landingMarkers;
    @NotNull
    private final Vector4f projectionScratch;
    private static final int MAX_SIMULATION_TICKS = 300;
    private static final float MARKER_RADIUS = 10.5f;
    private static final float MARKER_POINTER_HEIGHT = 7.0f;
    private static final float MARKER_ITEM_SIZE = 16.0f;
    @NotNull
    private static final ProjectileProfile PROFILE_PEARL = new ProjectileProfile(0.99, 0.03);
    @NotNull
    private static final ProjectileProfile PROFILE_ITEM = new ProjectileProfile(0.98, 0.04);
    @NotNull
    private static final ProjectileProfile PROFILE_ARROW = new ProjectileProfile(0.99, 0.05);
    @NotNull
    private static final String MARKER_PEARL = "Пёрл";
    @NotNull
    private static final String MARKER_SNOWBALL = "Снежок";
    @NotNull
    private static final String MARKER_ARROW = "Стрела";
    @NotNull
    private static final String MARKER_TRIDENT = "Трезубец";
    @NotNull
    private static final String MARKER_ITEM = "Предметы";
    @NotNull
    private static final String COLOR_CUSTOM = "Свой";
    @NotNull
    private static final String COLOR_CLIENT = "Клиент";

    public SelfPredictions() {
        super("Self Predictions", "Показывает траекторию и место падения летящих снарядов.", Category.VISUALS);
        String[] stringArray = new String[]{ProjectileTarget.PEARLS.getSettingName(), ProjectileTarget.ARROWS.getSettingName(), ProjectileTarget.TRIDENTS.getSettingName(), ProjectileTarget.ITEMS.getSettingName()};
        MultiSelectSetting multiSelectSetting = new MultiSelectSetting("Цели", "Объекты, отображаемые предсказаниями.").value(stringArray);
        stringArray = new String[]{ProjectileTarget.PEARLS.getSettingName(), ProjectileTarget.ARROWS.getSettingName(), ProjectileTarget.TRIDENTS.getSettingName(), ProjectileTarget.ITEMS.getSettingName()};
        this.targets = (MultiSelectSetting)this.register((Setting)multiSelectSetting.selected(stringArray));
        this.landingMarker = (BooleanSetting)this.register((Setting)new BooleanSetting("Метка приземления", "Показывает предмет и стрелку в точке падения.", true));
        stringArray = new String[]{MARKER_PEARL, MARKER_SNOWBALL, MARKER_ARROW, MARKER_TRIDENT, MARKER_ITEM};
        MultiSelectSetting multiSelectSetting2 = new MultiSelectSetting("Предметы в метке", "Типы снарядов, которые показывать предметом. Остальные рисуются квадратом.").value(stringArray);
        stringArray = new String[]{MARKER_PEARL, MARKER_SNOWBALL, MARKER_ARROW, MARKER_TRIDENT, MARKER_ITEM};
        this.markerItems = (MultiSelectSetting)this.register((Setting)multiSelectSetting2.selected(stringArray));
        this.range = (NumberSetting)this.register((Setting)new NumberSetting("Дальность", "Максимальная дистанция отрисовки предсказаний.", 128.0, 16.0, 256.0, 1.0));
        this.colorsSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Цвета"));
        stringArray = new String[]{COLOR_CLIENT, COLOR_CUSTOM};
        this.colorMode = (ModeSetting)this.register((Setting)new ModeSetting("Режим цвета", "Источник цвета линии и метки.", COLOR_CLIENT, stringArray));
        this.lineColor = (ColorSetting)this.register((Setting)new ColorSetting("Свой цвет линии", "Свой цвет линии предсказания.", new Color(127, 242, 255, 230)).visibleWhen(() -> SelfPredictions.lineColor$lambda$0(this)));
        this.simulationPoints = new ArrayList(301);
        this.projectilePaths = new HashMap();
        this.liveProjectileIds = new HashSet();
        this.landingInterpolations = new HashMap();
        this.landingMarkers = new ArrayList();
        this.projectionScratch = new Vector4f();
        this.markerItems.visible(() -> SelfPredictions._init_$lambda$0(this));
    }

    @Override
    public float fadeOutSeconds() {
        return 0.5f;
    }

    @Override
    protected void onDisable() {
        this.projectilePaths.clear();
        this.landingInterpolations.clear();
        this.landingMarkers.clear();
    }

    @EventHandler
    private final void onWorldRender(WorldRenderEvent event) {
        Camera camera2;
        if (event.isPortalPass()) {
            return;
        }
        this.landingMarkers.clear();
        ClientPlayerEntity player = this.mc.player;
        ClientWorld level = this.mc.world;
        if (player == null || level == null) {
            this.projectilePaths.clear();
            this.landingInterpolations.clear();
            return;
        }
        boolean landingEnabled = this.landingMarker.getValue();
        if (!landingEnabled) {
            this.landingInterpolations.clear();
        }
        double simulationLimitSqr = this.simulationLimitSqr();
        VertexConsumerProvider.Immediate immediate2 = this.mc.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        VertexConsumerProvider.Immediate provider = immediate2;
        if (event.getCamera() == null) {
            Camera camera3 = this.mc.gameRenderer.getCamera();
            camera2 = camera3;
            Intrinsics.checkNotNullExpressionValue((Object)camera3, (String)"getMainCamera(...)");
        } else {
            camera2 = event.getCamera();
        }
        Camera camera = camera2;
        Vec3d vec3d2 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d cameraPos = vec3d2;
        for (Entity entity : level.getEntities()) {
            ProjectileTarget target = this.targetFor(entity);
            if (target == null || !this.targets.isSelected(target.getSettingName()) || !this.isInRange(entity) || !this.isOwnedByPlayer(entity)) continue;
            Vec3d velocity = entity.getVelocity();
            if (!this.shouldRenderLive(entity, velocity)) continue;
            ProjectileProfile profile = this.profileFor(target);
            Vec3d start = entity.getLerpedPos(event.getPartialTicks());
            if (!player.canSee(entity)) continue;
            ProjectilePath path = this.projectilePathFor(entity, profile, simulationLimitSqr);
            this.liveProjectileIds.add(entity.getId());
            this.simulationPoints.clear();
            this.simulationPoints.add(start);
            int pathIndex = start.squaredDistanceTo((Vec3d)CollectionsKt.first(path.getPoints())) < 1.0E-8 ? 1 : 0;
            this.simulationPoints.addAll((Collection<Vec3d>)path.getPoints().subList(pathIndex, path.getPoints().size()));
            this.drawPath(provider, event.getStack(), cameraPos, this.simulationPoints);
            this.addLandingMarker(event, cameraPos, entity.getId(), entity.age, path.getPoints(), path.getHit() && landingEnabled, this.markerVisualFor(entity));
        }
        this.projectilePaths.keySet().removeIf(id -> !this.liveProjectileIds.contains(id));
        this.landingInterpolations.keySet().removeIf(id -> !this.liveProjectileIds.contains(id));
        this.liveProjectileIds.clear();
    }

    @EventHandler
    private final void onHudRender(HudRenderEvent event) {
        if (this.landingMarkers.isEmpty()) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        RenderItem.beginFrame(graphics);
        Render2D.beginFrame(graphics);
        int gradientIndex = 0;
        for (LandingMarker marker : this.landingMarkers) {
            this.drawMarkerBackground(marker, gradientIndex);
            gradientIndex += 45;
        }
        Render2D.flush();
        for (LandingMarker marker : this.landingMarkers) {
            this.drawMarkerItem(marker);
        }
        RenderItem.flush();
    }

    private final boolean simulate(Vec3d start, Vec3d velocity, ProjectileProfile profile, int maxTicks, Entity collider, double simulationLimitSqr, List<Vec3d> points) {
        points.clear();
        points.add(start);
        Vec3d position = start;
        Vec3d motion = velocity;
        boolean hit = false;
        ClientWorld level = this.mc.world;
        for (int i = 0; i < maxTicks; ++i) {
            Vec3d next = position.add(motion);
            BlockHitResult blockHit = level != null ? level.raycast(new RaycastContext(position, next, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, collider)) : null;
            if (blockHit != null && blockHit.getType() != HitResult.Type.MISS) {
                points.add(blockHit.getPos());
                hit = true;
                break;
            }
            points.add(next);
            position = next;
            motion = motion.multiply(profile.getDrag()).add(0.0, -profile.getGravity(), 0.0);
            if (position.y < -128.0 || position.squaredDistanceTo(start) > simulationLimitSqr) break;
        }
        return hit;
    }

    private final ProjectilePath projectilePathFor(Entity entity, ProjectileProfile profile, double simulationLimitSqr) {
        ProjectilePath cached = this.projectilePaths.get(entity.getId());
        if (cached == null || cached.getTick() != entity.age) {
            Vec3d vec3d2 = entity.getEntityPos();
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
            Vec3d vec3d3 = entity.getVelocity();
            Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"getDeltaMovement(...)");
            boolean hit = this.simulate(vec3d2, vec3d3, profile, 300, entity, simulationLimitSqr, this.simulationPoints);
            cached = new ProjectilePath(new ArrayList(this.simulationPoints), hit, entity.age);
            this.projectilePaths.put(entity.getId(), cached);
        }
        return cached;
    }

    private final void drawPath(VertexConsumerProvider.Immediate provider, MatrixStack stack, Vec3d cameraPos, List<? extends Vec3d> points) {
        if (points.size() < 2) {
            return;
        }
        VertexConsumer vertexConsumer2 = provider.getBuffer(ClientPipelines.WORLD_PARTICLES_COLOR);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer consumer = vertexConsumer2;
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        Vec3d previousPoint = (Vec3d)CollectionsKt.first(points);
        Vec3d previousOffset = this.pathOffset(points, 0, cameraPos, null);
        double fadeDistance = 0.0;
        float progressStep = 1.0f / (float)(points.size() - 1);
        int previousColor = ColorEngine.multAlpha(this.trajectoryColorAt(0.0f), 0.0f);
        int n = points.size();
        for (int i = 1; i < n; ++i) {
            Vec3d point = points.get(i);
            Vec3d offset = this.pathOffset(points, i, cameraPos, previousOffset);
            float fadeProgress = MathHelper.clamp((float)((float)((fadeDistance += previousPoint.distanceTo(point)) / 6.0)), (float)0.0f, (float)1.0f);
            float fade = fadeProgress * fadeProgress * (3.0f - 2.0f * fadeProgress);
            int pointColor = ColorEngine.multAlpha(this.trajectoryColorAt((float)i * progressStep), fade);
            if (ColorEngine.alpha(previousColor) > 0 || ColorEngine.alpha(pointColor) > 0) {
                this.emitRibbonSegment(consumer, pose, previousPoint, point, cameraPos, previousOffset, offset, previousColor, pointColor);
            }
            previousPoint = point;
            previousOffset = offset;
            previousColor = pointColor;
        }
        provider.draw(ClientPipelines.WORLD_PARTICLES_COLOR);
    }

    private final Vec3d pathOffset(List<? extends Vec3d> points, int index, Vec3d cameraPos, Vec3d previousOffset) {
        Vec3d vec3d2 = points.get(Math.min(points.size() - 1, index + 1)).subtract(points.get(Math.max(0, index - 1))).normalize();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"normalize(...)");
        Vec3d tangent = vec3d2;
        Vec3d vec3d3 = points.get(index).subtract(cameraPos).normalize();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"normalize(...)");
        Vec3d view = vec3d3;
        Vec3d vec3d4 = tangent.crossProduct(view);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"cross(...)");
        Vec3d right = vec3d4;
        if (right.lengthSquared() < 0.0025) {
            Vec3d vec3d5 = tangent.crossProduct(new Vec3d(0.0, 1.0, 0.0));
            Intrinsics.checkNotNullExpressionValue((Object)vec3d5, (String)"cross(...)");
            right = vec3d5;
            if (right.lengthSquared() < 1.0E-8) {
                Vec3d vec3d6 = tangent.crossProduct(new Vec3d(1.0, 0.0, 0.0));
                Intrinsics.checkNotNullExpressionValue((Object)vec3d6, (String)"cross(...)");
                right = vec3d6;
            }
        }
        if (previousOffset != null && right.dotProduct(previousOffset) < 0.0) {
            Vec3d vec3d7 = right.multiply(-1.0);
            Intrinsics.checkNotNullExpressionValue((Object)vec3d7, (String)"scale(...)");
            right = vec3d7;
        }
        double distance = points.get(index).distanceTo(cameraPos);
        double halfWidth = Math.max(0.006, Math.min(0.14, distance * 0.00115));
        Vec3d vec3d8 = right.normalize().multiply(halfWidth);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d8, (String)"scale(...)");
        return vec3d8;
    }

    private final void emitRibbonSegment(VertexConsumer consumer, MatrixStack.Entry pose, Vec3d from, Vec3d to, Vec3d cameraPos, Vec3d fromOffset, Vec3d toOffset, int fromColor, int toColor) {
        Vec3d vec3d2 = from.subtract(cameraPos);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"subtract(...)");
        Vec3d a = vec3d2;
        Vec3d vec3d3 = to.subtract(cameraPos);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"subtract(...)");
        Vec3d b = vec3d3;
        this.vertex(consumer, pose, a.x - fromOffset.x, a.y - fromOffset.y, a.z - fromOffset.z, fromColor);
        this.vertex(consumer, pose, a.x + fromOffset.x, a.y + fromOffset.y, a.z + fromOffset.z, fromColor);
        this.vertex(consumer, pose, b.x + toOffset.x, b.y + toOffset.y, b.z + toOffset.z, toColor);
        this.vertex(consumer, pose, b.x - toOffset.x, b.y - toOffset.y, b.z - toOffset.z, toColor);
    }

    private final void vertex(VertexConsumer consumer, MatrixStack.Entry pose, double x, double y, double z, int rawColor) {
        int color = ColorEngine.multAlpha(rawColor, this.visualAlpha());
        consumer.vertex(pose, (float)x, (float)y, (float)z).color(color);
    }

    private final void addLandingMarker(WorldRenderEvent event, Vec3d cameraPos, int entityId, int tick, List<? extends Vec3d> points, boolean visible, MarkerVisual visual) {
        if (!visible || points.isEmpty()) {
            return;
        }
        Vec3d target = (Vec3d)CollectionsKt.last(points);
        LandingInterpolation interpolation = this.landingInterpolations.get(entityId);
        if (interpolation == null) {
            interpolation = new LandingInterpolation(target, target, tick);
        } else if (interpolation.getTick() != tick) {
            interpolation = new LandingInterpolation(interpolation.getCurrent(), target, tick);
        }
        this.landingInterpolations.put(entityId, interpolation);
        float partialTick = MathHelper.clamp((float)event.getPartialTicks(), (float)0.0f, (float)1.0f);
        Vec3d vec3d2 = interpolation.getPrevious().lerp(interpolation.getCurrent(), (double)partialTick).subtract(cameraPos);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"subtract(...)");
        Vec3d pos = vec3d2;
        Vector4f projected = this.projectionScratch.set((float)pos.x, (float)pos.y, (float)pos.z, 1.0f);
        event.getPositionMatrix().transform(projected);
        event.getProjectionMatrix().transform(projected);
        if (projected.w <= 1.0E-4f) {
            return;
        }
        float screenWidth = Position.Companion.screenWidth();
        float screenHeight = Position.Companion.screenHeight();
        float x = (projected.x / projected.w * 0.5f + 0.5f) * screenWidth;
        float y = (1.0f - (projected.y / projected.w * 0.5f + 0.5f)) * screenHeight;
        float margin = 21.0f;
        if (!Float.isFinite(x) || !Float.isFinite(y) || x < -margin || x > screenWidth + margin || y < -margin || y > screenHeight + margin) {
            return;
        }
        ItemStack itemStack2 = visual != null && this.markerItems.isSelected(visual.getKind().getSettingName()) ? visual.getIcon().copyWithCount(1) : ItemStack.EMPTY;
        Intrinsics.checkNotNull((Object)itemStack2);
        ItemStack icon = itemStack2;
        this.landingMarkers.add(new LandingMarker(Math.round(x), Math.round(y), icon));
    }

    private final void drawMarkerBackground(LandingMarker marker, int gradientIndex) {
        float centerX = marker.getX();
        float centerY = (float)marker.getY() - 10.5f - 7.0f + 1.5f;
        float pointerTop = centerY + 10.5f - 1.0f;
        int c0 = this.markerColor(gradientIndex);
        int c90 = this.markerColor(gradientIndex + 90);
        int c180 = this.markerColor(gradientIndex + 180);
        int c270 = this.markerColor(gradientIndex + 270);
        float pointerLeftU = 0.23809524f;
        float pointerRightU = 0.7619048f;
        float pointerV = (pointerTop - (centerY - 10.5f)) / 21.0f;
        int pointerLeft = SelfPredictions.Companion.bilinearColor(c0, c90, c180, c270, pointerLeftU, pointerV);
        int pointerRight = SelfPredictions.Companion.bilinearColor(c0, c90, c180, c270, pointerRightU, pointerV);
        Render2D.shape(BuiltShape.Companion.downwardTriangle(centerX - 5.5f, pointerTop, 11.0f, 7.0f, pointerLeft).withSecondColor(pointerRight, 0.0f));
        Render2D.circle(centerX, centerY, 10.5f, ColorEngine.rgba(7, 10, 14, 255));
        Render2D.outline(centerX - 10.5f, centerY - 10.5f, 21.0f, 21.0f, 10.5f, 1.5f, c0, c90, c180, c270);
        if (marker.getIcon().isEmpty()) {
            Render2D.rect(centerX - 2.0f, centerY - 2.0f, 4.0f, 4.0f, 0.0f, c0, c90, c180, c270);
        }
    }

    private final void drawMarkerItem(LandingMarker marker) {
        if (marker.getIcon().isEmpty()) {
            return;
        }
        float centerY = (float)marker.getY() - 10.5f - 7.0f + 1.5f;
        RenderItem.item(marker.getIcon(), (float)marker.getX() - 8.0f, centerY - 8.0f, 16.0f);
    }

    private final MarkerVisual markerVisualFor(Entity entity) {
        if (entity instanceof EnderPearlEntity) {
            ItemStack itemStack2 = Items.ENDER_PEARL.getDefaultStack();
            Intrinsics.checkNotNullExpressionValue((Object)itemStack2, (String)"getDefaultInstance(...)");
            return new MarkerVisual(itemStack2, MarkerKind.PEARL);
        }
        if (entity instanceof SnowballEntity) {
            ItemStack itemStack3 = Items.SNOWBALL.getDefaultStack();
            Intrinsics.checkNotNullExpressionValue((Object)itemStack3, (String)"getDefaultInstance(...)");
            return new MarkerVisual(itemStack3, MarkerKind.SNOWBALL);
        }
        if (entity instanceof EggEntity) {
            ItemStack itemStack4 = Items.EGG.getDefaultStack();
            Intrinsics.checkNotNullExpressionValue((Object)itemStack4, (String)"getDefaultInstance(...)");
            return new MarkerVisual(itemStack4, MarkerKind.ITEM);
        }
        if (entity instanceof TridentEntity) {
            ItemStack itemStack5 = Items.TRIDENT.getDefaultStack();
            Intrinsics.checkNotNullExpressionValue((Object)itemStack5, (String)"getDefaultInstance(...)");
            return new MarkerVisual(itemStack5, MarkerKind.TRIDENT);
        }
        if (entity instanceof PersistentProjectileEntity) {
            ItemStack itemStack6 = Items.ARROW.getDefaultStack();
            Intrinsics.checkNotNullExpressionValue((Object)itemStack6, (String)"getDefaultInstance(...)");
            return new MarkerVisual(itemStack6, MarkerKind.ARROW);
        }
        if (entity instanceof ItemEntity) {
            ItemStack itemStack7 = ((ItemEntity)entity).getStack().copyWithCount(1);
            Intrinsics.checkNotNullExpressionValue((Object)itemStack7, (String)"copyWithCount(...)");
            return new MarkerVisual(itemStack7, MarkerKind.ITEM);
        }
        return null;
    }

    private final double simulationLimitSqr() {
        double value = this.range.getFloat();
        return value * value * 4.0;
    }

    private final int trajectoryColorAt(float progress) {
        if (this.colorMode.is(COLOR_CLIENT)) {
            return ClientAccent.gradientColor(progress, 230.0f);
        }
        return this.lineColor.getValue();
    }

    private final int markerColor(int index) {
        if (!this.colorMode.is(COLOR_CLIENT)) {
            return ColorEngine.withAlpha(this.lineColor.getValue(), 255);
        }
        int[] palette = ClientPalette.colors();
        if (palette != null && palette.length >= 2) {
            return SelfPredictions.Companion.paletteFade(8, index, palette);
        }
        InterfaceModule iface = InterfaceModule.Companion.getInstance();
        if (iface != null) {
            int second;
            int first = iface.clientPrimaryColorOpaque();
            int n = second = iface.usesSecondClientColor() ? iface.clientSecondaryColorOpaque() : first;
            if (first != second) {
                return SelfPredictions.Companion.fade(8, index, first, second) | 0xFF000000;
            }
            return first;
        }
        return ClientAccent.accentOpaque();
    }

    private final boolean shouldRenderLive(Entity entity, Vec3d velocity) {
        if (entity instanceof ItemEntity) {
            return !this.isGroundedItem((ItemEntity)entity, velocity) && velocity.lengthSquared() > 1.0E-5;
        }
        return velocity.lengthSquared() > 1.0E-5;
    }

    private final boolean isGroundedItem(ItemEntity item, Vec3d velocity) {
        if (item.isOnGround()) {
            return true;
        }
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return false;
        }
        ClientWorld level = clientWorld3;
        Box box2 = item.getBoundingBox();
        Intrinsics.checkNotNullExpressionValue((Object)box2, (String)"getBoundingBox(...)");
        Box box = box2;
        Vec3d from = new Vec3d(item.getX(), box.minY + 0.08, item.getZ());
        Vec3d to = new Vec3d(item.getX(), box.minY - 0.16, item.getZ());
        BlockHitResult blockHitResult2 = level.raycast(new RaycastContext(from, to, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, (Entity)item));
        Intrinsics.checkNotNullExpressionValue((Object)blockHitResult2, (String)"clip(...)");
        BlockHitResult hit = blockHitResult2;
        boolean blockUnderItem = hit.getType() != HitResult.Type.MISS;
        return blockUnderItem && velocity.y > -0.08;
    }

    private final boolean isInRange(Entity entity) {
        float maxRange = this.range.getFloat();
        ClientPlayerEntity player = this.mc.player;
        return maxRange <= 0.0f || player == null || player.squaredDistanceTo(entity) <= (double)(maxRange * maxRange);
    }

    private final boolean isOwnedByPlayer(Entity entity) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return false;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        Entity entity2 = entity;
        Entity owner = entity2 instanceof ItemEntity ? ((ItemEntity)entity).getOwner() : (entity2 instanceof ProjectileEntity ? ((ProjectileEntity)entity).getOwner() : null);
        return Intrinsics.areEqual((Object)owner, (Object)player);
    }

    private final ProjectileTarget targetFor(Entity entity) {
        if (entity instanceof EnderPearlEntity || entity instanceof SnowballEntity || entity instanceof EggEntity) {
            return ProjectileTarget.PEARLS;
        }
        if (entity instanceof TridentEntity) {
            return ProjectileTarget.TRIDENTS;
        }
        if (entity instanceof PersistentProjectileEntity) {
            return ProjectileTarget.ARROWS;
        }
        if (entity instanceof ItemEntity && !((ItemEntity)entity).getStack().isEmpty()) {
            return ProjectileTarget.ITEMS;
        }
        return null;
    }

    private final ProjectileProfile profileFor(ProjectileTarget target) {
        return switch (WhenMappings.$EnumSwitchMapping$0[target.ordinal()]) {
            case 1 -> PROFILE_PEARL;
            case 2 -> PROFILE_ITEM;
            case 3, 4 -> PROFILE_ARROW;
            default -> throw new NoWhenBranchMatchedException();
        };
    }

    private static final Boolean lineColor$lambda$0(SelfPredictions this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean _init_$lambda$0(SelfPredictions this$0) {
        return this$0.landingMarker.getValue();
    }

    private static final boolean onWorldRender$lambda$0(SelfPredictions this$0, Integer id) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        return !this$0.liveProjectileIds.contains(id);
    }

    private static final boolean onWorldRender$lambda$1(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final boolean onWorldRender$lambda$2(SelfPredictions this$0, Integer id) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        return !this$0.liveProjectileIds.contains(id);
    }

    private static final boolean onWorldRender$lambda$3(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J/\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\t\u0010\nJ'\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ?\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001bR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u001e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010 R\u0014\u0010\"\u001a\u00020\u001e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010 R\u0014\u0010$\u001a\u00020#8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0014\u0010&\u001a\u00020#8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010%R\u0014\u0010'\u001a\u00020#8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010%R\u0014\u0010(\u001a\u00020#8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010%R\u0014\u0010)\u001a\u00020#8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010%R\u0014\u0010*\u001a\u00020#8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010%R\u0014\u0010+\u001a\u00020#8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010%\u00a8\u0006,"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions.Companion;", "", "<init>", "()V", "", "speed", "index", "first", "second", "fade", "(IIII)I", "", "palette", "paletteFade", "(II[I)I", "topLeft", "topRight", "bottomRight", "bottomLeft", "", "u", "v", "bilinearColor", "(IIIIFF)I", "MAX_SIMULATION_TICKS", "I", "MARKER_RADIUS", "F", "MARKER_POINTER_HEIGHT", "MARKER_ITEM_SIZE", "Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$ProjectileProfile;", "PROFILE_PEARL", "Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$ProjectileProfile;", "PROFILE_ITEM", "PROFILE_ARROW", "", "MARKER_PEARL", "Ljava/lang/String;", "MARKER_SNOWBALL", "MARKER_ARROW", "MARKER_TRIDENT", "MARKER_ITEM", "COLOR_CUSTOM", "COLOR_CLIENT", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
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

        private final int bilinearColor(int topLeft, int topRight, int bottomRight, int bottomLeft, float u, float v) {
            int top = ColorEngine.lerpColor(topLeft, topRight, u);
            int bottom = ColorEngine.lerpColor(bottomLeft, bottomRight, u);
            return ColorEngine.lerpColor(top, bottom, v);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\t\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0006\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\r\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$LandingInterpolation;", "", "Lnet/minecraft/Vec3d;", "previous", "current", "", "tick", "<init>", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;I)V", "Lnet/minecraft/Vec3d;", "getPrevious", "()Lnet/minecraft/Vec3d;", "getCurrent", "I", "getTick", "()I", "rtx.kimiko:kimiko"})
    private static final class LandingInterpolation {
        @NotNull
        private final Vec3d previous;
        @NotNull
        private final Vec3d current;
        private final int tick;

        public LandingInterpolation(@NotNull Vec3d previous, @NotNull Vec3d current, int tick) {
            Intrinsics.checkNotNullParameter((Object)previous, (String)"previous");
            Intrinsics.checkNotNullParameter((Object)current, (String)"current");
            this.previous = previous;
            this.current = current;
            this.tick = tick;
        }

        @NotNull
        public final Vec3d getPrevious() {
            return this.previous;
        }

        @NotNull
        public final Vec3d getCurrent() {
            return this.current;
        }

        public final int getTick() {
            return this.tick;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\t\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0006\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\r\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$LandingMarker;", "", "", "x", "y", "Lnet/minecraft/ItemStack;", "icon", "<init>", "(IILnet/minecraft/ItemStack;)V", "I", "getX", "()I", "getY", "Lnet/minecraft/ItemStack;", "getIcon", "()Lnet/minecraft/ItemStack;", "rtx.kimiko:kimiko"})
    private static final class LandingMarker {
        private final int x;
        private final int y;
        @NotNull
        private final ItemStack icon;

        public LandingMarker(int x, int y, @NotNull ItemStack icon) {
            Intrinsics.checkNotNullParameter((Object)icon, (String)"icon");
            this.x = x;
            this.y = y;
            this.icon = icon;
        }

        public final int getX() {
            return this.x;
        }

        public final int getY() {
            return this.y;
        }

        @NotNull
        public final ItemStack getIcon() {
            return this.icon;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\f\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0006\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$MarkerKind;", "", "", "settingName", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "Ljava/lang/String;", "getSettingName", "()Ljava/lang/String;", "PEARL", "SNOWBALL", "ARROW", "TRIDENT", "ITEM", "rtx.kimiko:kimiko"})
    private static enum MarkerKind {
        PEARL("Пёрл"),
        SNOWBALL("Снежок"),
        ARROW("Стрела"),
        TRIDENT("Трезубец"),
        ITEM("Предметы");
@NotNull
        private final String settingName;
        
        
        
        
        
        
        private MarkerKind(String settingName) {
            this.settingName = settingName;
        }

        @NotNull
        public final String getSettingName() {
            return this.settingName;
        }

        

        

        @NotNull
        public static EnumEntries<MarkerKind> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$MarkerVisual;", "", "Lnet/minecraft/ItemStack;", "icon", "Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$MarkerKind;", "kind", "<init>", "(Lnet/minecraft/ItemStack;Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$MarkerKind;)V", "Lnet/minecraft/ItemStack;", "getIcon", "()Lnet/minecraft/ItemStack;", "Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$MarkerKind;", "getKind", "()Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$MarkerKind;", "rtx.kimiko:kimiko"})
    private static final class MarkerVisual {
        @NotNull
        private final ItemStack icon;
        @NotNull
        private final MarkerKind kind;

        public MarkerVisual(@NotNull ItemStack icon, @NotNull MarkerKind kind) {
            Intrinsics.checkNotNullParameter((Object)icon, (String)"icon");
            Intrinsics.checkNotNullParameter((Object)((Object)kind), (String)"kind");
            this.icon = icon;
            this.kind = kind;
        }

        @NotNull
        public final ItemStack getIcon() {
            return this.icon;
        }

        @NotNull
        public final MarkerKind getKind() {
            return this.kind;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001B%\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0006\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\b\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0014"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$ProjectilePath;", "", "", "Lnet/minecraft/Vec3d;", "points", "", "hit", "", "tick", "<init>", "(Ljava/util/List;ZI)V", "Ljava/util/List;", "getPoints", "()Ljava/util/List;", "Z", "getHit", "()Z", "I", "getTick", "()I", "rtx.kimiko:kimiko"})
    private static final class ProjectilePath {
        @NotNull
        private final List<Vec3d> points;
        private final boolean hit;
        private final int tick;

        public ProjectilePath(@NotNull List<Vec3d> points, boolean hit, int tick) {
            Intrinsics.checkNotNullParameter(points, "points");
            this.points = points;
            this.hit = hit;
            this.tick = tick;
        }

        @NotNull
        public final List<Vec3d> getPoints() {
            return this.points;
        }

        public final boolean getHit() {
            return this.hit;
        }

        public final int getTick() {
            return this.tick;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0007\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0007\u001a\u0004\b\n\u0010\t\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$ProjectileProfile;", "", "", "drag", "gravity", "<init>", "(DD)V", "D", "getDrag", "()D", "getGravity", "rtx.kimiko:kimiko"})
    private static final class ProjectileProfile {
        private final double drag;
        private final double gravity;

        public ProjectileProfile(double drag, double gravity) {
            this.drag = drag;
            this.gravity = gravity;
        }

        public final double getDrag() {
            return this.drag;
        }

        public final double getGravity() {
            return this.gravity;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0006\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f\u00a8\u0006\r"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/SelfPredictions$ProjectileTarget;", "", "", "settingName", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "Ljava/lang/String;", "getSettingName", "()Ljava/lang/String;", "PEARLS", "ARROWS", "TRIDENTS", "ITEMS", "rtx.kimiko:kimiko"})
    private static enum ProjectileTarget {
        PEARLS("Пёрлы"),
        ARROWS("Стрелы"),
        TRIDENTS("Трезубцы"),
        ITEMS("Предметы");
@NotNull
        private final String settingName;
        
        
        
        
        
        private ProjectileTarget(String settingName) {
            this.settingName = settingName;
        }

        @NotNull
        public final String getSettingName() {
            return this.settingName;
        }

        

        

        @NotNull
        public static EnumEntries<ProjectileTarget> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }

    @Metadata(mv={2, 4, 0}, k=3, xi=48)
    public static final class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] nArray = new int[ProjectileTarget.values().length];
            try {
                nArray[ProjectileTarget.PEARLS.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[ProjectileTarget.ITEMS.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[ProjectileTarget.ARROWS.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[ProjectileTarget.TRIDENTS.ordinal()] = 4;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$0 = nArray;
        }
    }
}

