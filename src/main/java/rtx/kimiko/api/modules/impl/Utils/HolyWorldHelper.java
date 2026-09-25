/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.Util
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.Items
 *  net.minecraft.util.hit.HitResult.Type
 *  net.minecraft.util.math.Vec3d
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
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.util.Util;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
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
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.input.MouseButtonEvent;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.NumberSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.inventory.InventoryItems;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"holyworldhelper"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00cc\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 {2\u00020\u0001:\u0004|}~{B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u0004H\u0015b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\t\u0010\u0003J\u001f\u0010\n\u001a\u00020\u0004H\u0015b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\n\u0010\u0003J\u001b\u0010\u000e\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0003b\u0002\b\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0011\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0010H\u0003b\u0002\b\r\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\u0014\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0013H\u0003b\u0002\b\r\u00a2\u0006\u0004\b\u0014\u0010\u0015J/\u0010\u001c\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ7\u0010\"\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020 H\u0002\u00a2\u0006\u0004\b\"\u0010#J_\u0010+\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020 2\u0006\u0010)\u001a\u00020(2\u0006\u0010\u001f\u001a\u00020\u001eH\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078*\u00a2\u0006\u0004\b+\u0010,JO\u0010.\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020\u001eH\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078*\u00a2\u0006\u0004\b.\u0010/JM\u00102\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u001a002\u0006\u0010\u001f\u001a\u00020\u001eH\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078*\u00a2\u0006\u0004\b2\u00103J7\u00105\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u00104\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b5\u00106J7\u00108\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u00107\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b8\u00106J/\u0010:\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u00109\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b:\u0010;J\u001f\u0010@\u001a\u00020\u00042\u0006\u0010=\u001a\u00020<2\u0006\u0010?\u001a\u00020>H\u0002\u00a2\u0006\u0004\b@\u0010AJ\u0017\u0010B\u001a\u00020\u00042\u0006\u0010!\u001a\u00020 H\u0002\u00a2\u0006\u0004\bB\u0010CJ!\u0010F\u001a\u0004\u0018\u00010\u001a2\u0006\u0010D\u001a\u00020\u001a2\u0006\u0010E\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\bF\u0010GJ'\u0010J\u001a\u00020(2\u0006\u0010H\u001a\u00020\u001aH\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078I\u00a2\u0006\u0004\bJ\u0010KJ\u0017\u0010L\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020 H\u0002\u00a2\u0006\u0004\bL\u0010MJ\u0017\u0010O\u001a\u00020\u001a2\u0006\u0010N\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\bO\u0010PJ\u000f\u0010Q\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bQ\u0010\u0003J\u001f\u0010S\u001a\u00020R2\u0006\u0010=\u001a\u00020<2\u0006\u0010&\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\bS\u0010TJ\u001f\u0010U\u001a\u00020 2\u0006\u0010\u0006\u001a\u00020 2\u0006\u0010N\u001a\u00020 H\u0002\u00a2\u0006\u0004\bU\u0010VJ\u001f\u0010Y\u001a\u00020\u001e2\u0006\u0010W\u001a\u00020\u001e2\u0006\u0010X\u001a\u00020 H\u0002\u00a2\u0006\u0004\bY\u0010ZR\u0014\u0010\\\u001a\u00020[8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\\\u0010]R\u0014\u0010_\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b_\u0010`R\u0014\u0010b\u001a\u00020a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bb\u0010cR\u0014\u0010d\u001a\u00020[8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bd\u0010]R\u0014\u0010f\u001a\u00020e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bf\u0010gR\u0014\u0010h\u001a\u00020e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bh\u0010gR$\u0010l\u001a\u0012\u0012\u0004\u0012\u00020j0ij\b\u0012\u0004\u0012\u00020j`k8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bl\u0010mR$\u0010n\u001a\u0012\u0012\u0004\u0012\u00020\u001a0ij\b\u0012\u0004\u0012\u00020\u001a`k8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bn\u0010mR\u0018\u0010p\u001a\u0004\u0018\u00010o8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bp\u0010qR\u0018\u0010r\u001a\u0004\u0018\u00010\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\br\u0010sR\u0018\u0010t\u001a\u0004\u0018\u00010\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bt\u0010sR\u0016\u0010u\u001a\u00020>8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bu\u0010vR\u0016\u0010w\u001a\u00020>8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bw\u0010vR\u0016\u0010x\u001a\u00020 8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bx\u0010yR\u0016\u0010z\u001a\u00020 8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bz\u0010y\u00ca\u0001\u0011\b\u007f\u0012\r\b\u0006\u0012\t\b\fJ\u0005\b\b(\u0080\u0001\u00a8\u0006\u0081\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "onEnable", "onDisable", "Lrtx/kimiko/api/events/impl/input/MouseButtonEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onMouse", "(Lrtx/kimiko/api/events/impl/input/MouseButtonEvent;)V", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "Lnet/minecraft/VertexConsumer;", "consumer", "Lnet/minecraft/MatrixStack$Entry;", "pose", "Lnet/minecraft/Vec3d;", "cameraPos", "renderHeldPreview", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;)V", "", "color", "", "partialTicks", "renderSnowPrediction", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;IF)V", "Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$Shape;", "shape", "center", "size", "", "floorY", "STD", "drawZone", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$Shape;Lnet/minecraft/Vec3d;FDI)V", "radius", "drawCircle", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;FI)V", "", "points", "drawPath", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;Ljava/util/List;I)V", "worldPos", "lineVertex", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;I)V", "end", "emitLandingMarker", "pos", "quadVertex", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;I)V", "Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$HolyWorldItem;", "item", "", "now", "activate", "(Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$HolyWorldItem;J)V", "updateSnowPrediction", "(F)V", "start", "velocity", "simulateLanding", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;)Lnet/minecraft/Vec3d;", "position", "MAX", "resolveFloorY", "(Lnet/minecraft/Vec3d;)D", "playerRenderPos", "(F)Lnet/minecraft/Vec3d;", "target", "smoothPreview", "(Lnet/minecraft/Vec3d;)Lnet/minecraft/Vec3d;", "clearState", "", "hasOtherPlayerIn", "(Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$HolyWorldItem;Lnet/minecraft/Vec3d;)Z", "approach", "(FF)F", "baseColor", "blend", "blendOccupied", "(IF)I", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "displaySeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "heldPreview", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "previewOpacity", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "colorSeparator", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "lineColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "playerInZoneColor", "Ljava/util/ArrayList;", "Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$ActiveZone;", "Lkotlin/collections/ArrayList;", "activeZones", "Ljava/util/ArrayList;", "simulationPoints", "Lnet/minecraft/ClientWorld;", "trackedLevel", "Lnet/minecraft/ClientWorld;", "smoothedPreview", "Lnet/minecraft/Vec3d;", "snowLanding", "lastActivationMs", "J", "lastFrameNanos", "frameDt", "F", "previewOccupiedBlend", "Companion", "Shape", "HolyWorldItem", "ActiveZone", "Lrtx/kimiko/api/liteapi/Feature;", "holyworldhelper", "rtx.kimiko:kimiko"})
public final class HolyWorldHelper
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting displaySeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Отображение"));
    @NotNull
    private final BooleanSetting heldPreview = (BooleanSetting)this.register((Setting)new BooleanSetting("Предпросмотр", "Показывает будущую зону, пока предмет находится в руке.", true));
    @NotNull
    private final NumberSetting previewOpacity = (NumberSetting)this.register((Setting)new NumberSetting("Прозрачность предпросмотра", "Яркость зоны, пока предмет находится в руке.", 0.45, 0.05, 1.0, 0.05).visibleWhen(() -> HolyWorldHelper.previewOpacity$lambda$0(this)));
    @NotNull
    private final SeparatorSetting colorSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Цвета"));
    @NotNull
    private final ColorSetting lineColor = (ColorSetting)this.register((Setting)new ColorSetting("Основной цвет", "Цвет линий зоны.", new Color(255, 181, 72, 235)));
    @NotNull
    private final ColorSetting playerInZoneColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет купола при игроке", "Цвет линий трапки, когда в её зоне находится другой игрок.", new Color(65, 220, 112, 255)));
    @NotNull
    private final ArrayList<ActiveZone> activeZones = new ArrayList();
    @NotNull
    private final ArrayList<Vec3d> simulationPoints = new ArrayList(301);
    @Nullable
    private ClientWorld trackedLevel;
    @Nullable
    private Vec3d smoothedPreview;
    @Nullable
    private Vec3d snowLanding;
    private long lastActivationMs;
    private long lastFrameNanos;
    private float frameDt;
    private float previewOccupiedBlend;
    private static final int MAX_SIMULATION_TICKS = 160;
    private static final int MAX_ACTIVE_ZONES = 8;
    private static final int CIRCLE_SEGMENTS = 64;
    @NotNull
    private static final int[][] CUBE_EDGES;
    private static final double SNOW_GRAVITY = 0.03;
    private static final double SNOW_DRAG = 0.99;
    private static final double SNOW_SPEED = 1.5;
    private static final long ACTIVATION_DEBOUNCE_MS = 90L;

    public HolyWorldHelper() {
        super("HolyWorld Helper", "Показывает зоны HolyWorld-предметов после использования.", Category.UTILS);
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onEnable() {
        this.trackedLevel = this.mc.world;
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onDisable() {
        this.clearState();
        this.trackedLevel = null;
    }

    @EventHandler
    private final void onMouse(MouseButtonEvent event) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        if (event.button != 1 || event.action != MouseButtonEvent.Action.PRESS || this.mc.world == null || this.mc.currentScreen != null) {
            return;
        }
        HolyWorldItem item = HolyWorldHelper.Companion.matchHeld((PlayerEntity)player);
        if (item == null || item.getDurationMs() <= 0L) {
            return;
        }
        long now = Util.getMeasuringTimeMs();
        if (now - this.lastActivationMs < 90L) {
            return;
        }
        this.lastActivationMs = now;
        this.activate(item, now);
    }

    @EventHandler
    private final void onTick(TickEvent event) {
        int n;
        if (!event.isPre()) {
            return;
        }
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
        if (!Intrinsics.areEqual((Object)this.trackedLevel, (Object)level)) {
            this.clearState();
            this.trackedLevel = level;
        }
        long now = Util.getMeasuringTimeMs();
        if (HolyWorldHelper.Companion.matchHeld((PlayerEntity)player) != HolyWorldItem.SNOW) {
            this.snowLanding = null;
            this.simulationPoints.clear();
        }
        for (int i = this.activeZones.size() - 1; i >= 0; --i) {
            ActiveZone zone = this.activeZones.get(i);
            if (now >= zone.getExpiresAt()) {
                this.activeZones.remove(i);
                continue;
            }
            if (!zone.getItem().getFollowsPlayer()) continue;
            HolyWorldItem holyWorldItem = zone.getItem();
            Vec3d vec3d2 = player.getEntityPos();
            zone.moveTo(HolyWorldHelper.Companion.zoneCenter(holyWorldItem, vec3d2));
        }
    }

    @EventHandler
    private final void onWorldRender(WorldRenderEvent event) {
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
        if (event.isPortalPass()) {
            return;
        }
        Camera camera2 = event.getCamera();
        if (camera2 == null) {
            Camera camera3 = this.mc.gameRenderer.getCamera();
            camera2 = camera3;
            Intrinsics.checkNotNullExpressionValue((Object)camera3, (String)"getMainCamera(...)");
        }
        Camera camera = camera2;
        Vec3d vec3d2 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d cameraPos = vec3d2;
        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate2 = this.mc.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        VertexConsumerProvider.Immediate provider = immediate2;
        VertexConsumer vertexConsumer2 = provider.getBuffer(ClientPipelines.TRAJECTORY_LINE);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer consumer = vertexConsumer2;
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        long frameNow = System.nanoTime();
        this.frameDt = this.lastFrameNanos == 0L ? 0.016f : Math.min((float)(frameNow - this.lastFrameNanos) * 1.0E-9f, 0.1f);
        this.lastFrameNanos = frameNow;
        if (this.heldPreview.getValue()) {
            this.renderHeldPreview(event, consumer, pose, cameraPos);
        } else {
            this.smoothedPreview = null;
        }
        int color = this.lineColor.getValue();
        Iterator<ActiveZone> iterator = this.activeZones.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<ActiveZone> iterator2 = iterator;
        while (iterator2.hasNext()) {
            ActiveZone zone = (ActiveZone) (iterator2.next());
            boolean occupied = this.hasOtherPlayerIn(zone.getItem(), zone.getCenter());
            zone.setOccupiedBlend(this.approach(zone.getOccupiedBlend(), occupied ? 1.0f : 0.0f));
            int zoneColor = this.blendOccupied(color, zone.getOccupiedBlend());
            Vec3d renderCenter = zone.getCenter();
            double renderFloorY = zone.getFloorY();
            if (zone.getItem().getFollowsPlayer()) {
                renderCenter = HolyWorldHelper.Companion.zoneCenter(zone.getItem(), this.playerRenderPos(event.getPartialTicks()));
                renderFloorY = renderCenter.y - (zone.getItem().getShape() == Shape.CUBE ? (double)zone.getItem().getSize() * 0.5 : 0.025);
            }
            this.drawZone(consumer, pose, cameraPos, zone.getItem().getShape(), renderCenter, zone.getItem().getSize(), renderFloorY, zoneColor);
        }
        provider.draw(ClientPipelines.TRAJECTORY_LINE);
        Vec3d landing = this.snowLanding;
        if (this.heldPreview.getValue() && landing != null && HolyWorldHelper.Companion.matchHeld((PlayerEntity)player) == HolyWorldItem.SNOW) {
            int markerColor = ColorEngine.multAlpha(this.lineColor.getValue(), this.previewOpacity.getFloat());
            VertexConsumer vertexConsumer3 = provider.getBuffer(ClientPipelines.WORLD_PARTICLES_COLOR);
            Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer3, (String)"getBuffer(...)");
            VertexConsumer marker = vertexConsumer3;
            this.emitLandingMarker(marker, pose, landing, cameraPos, markerColor);
            provider.draw(ClientPipelines.WORLD_PARTICLES_COLOR);
        }
    }

    private final void renderHeldPreview(WorldRenderEvent event, VertexConsumer consumer, MatrixStack.Entry pose, Vec3d cameraPos) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        HolyWorldItem item = HolyWorldHelper.Companion.matchHeld((PlayerEntity)player);
        if (item == null) {
            this.smoothedPreview = null;
            this.previewOccupiedBlend = 0.0f;
            return;
        }
        if (item == HolyWorldItem.SNOW) {
            this.smoothedPreview = null;
            this.previewOccupiedBlend = 0.0f;
            int snowColor = ColorEngine.multAlpha(this.lineColor.getValue(), this.previewOpacity.getFloat());
            this.renderSnowPrediction(consumer, pose, cameraPos, snowColor, event.getPartialTicks());
            return;
        }
        Vec3d base = this.smoothPreview(this.playerRenderPos(event.getPartialTicks()));
        double floorY = base.y + 0.012;
        Vec3d center = item.getFollowsPlayer() ? HolyWorldHelper.Companion.zoneCenter(item, base) : HolyWorldHelper.Companion.placedCenter(item, base.x, floorY, base.z);
        boolean occupied = this.hasOtherPlayerIn(item, center);
        this.previewOccupiedBlend = this.approach(this.previewOccupiedBlend, occupied ? 1.0f : 0.0f);
        int previewColor = ColorEngine.multAlpha(this.blendOccupied(this.lineColor.getValue(), this.previewOccupiedBlend), this.previewOpacity.getFloat());
        this.drawZone(consumer, pose, cameraPos, item.getShape(), center, item.getSize(), floorY, previewColor);
    }

    private final void renderSnowPrediction(VertexConsumer consumer, MatrixStack.Entry pose, Vec3d cameraPos, int color, float partialTicks) {
        this.updateSnowPrediction(partialTicks);
        this.drawPath(consumer, pose, cameraPos, (List<? extends Vec3d>)this.simulationPoints, color);
        Vec3d landing = this.snowLanding;
        if (landing != null) {
            Vec3d vec3d2 = landing.add(0.0, 0.02, 0.0);
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"add(...)");
            this.drawCircle(consumer, pose, cameraPos, vec3d2, 0.42f, color);
        }
    }

    @Protect(value=Level.STD)
    private final void drawZone(VertexConsumer consumer, MatrixStack.Entry pose, Vec3d cameraPos, Shape shape, Vec3d center, float size, double floorY, int color) {
        if (shape == Shape.CIRCLE) {
            this.drawCircle(consumer, pose, cameraPos, new Vec3d(center.x, floorY + 0.02, center.z), size, color);
            return;
        }
        double half = (double)size * 0.5;
        Vec3d[] corners = new Vec3d[]{
            center.add(-half, -half, -half), center.add(half, -half, -half),
            center.add(half, -half, half), center.add(-half, -half, half),
            center.add(-half, half, -half), center.add(half, half, -half),
            center.add(half, half, half), center.add(-half, half, half)
        };
        for (int[] edge : CUBE_EDGES) {
            Vec3d vec3d2 = corners[edge[0]];
            this.lineVertex(consumer, pose, cameraPos, vec3d2, color);
            Vec3d vec3d3 = corners[edge[1]];
            this.lineVertex(consumer, pose, cameraPos, vec3d3, color);
        }
    }

    @Protect(value=Level.STD)
    private final void drawCircle(VertexConsumer consumer, MatrixStack.Entry pose, Vec3d cameraPos, Vec3d center, float radius, int color) {
        for (int i = 0; i < 64; ++i) {
            float angleFrom = (float)Math.PI * 2 * (float)i / (float)64;
            float angleTo = (float)Math.PI * 2 * (float)(i + 1) / (float)64;
            double r = radius;
            Vec3d from = center.add(Math.cos(angleFrom) * r, 0.0, Math.sin(angleFrom) * r);
            Vec3d to = center.add(Math.cos(angleTo) * r, 0.0, Math.sin(angleTo) * r);
            this.lineVertex(consumer, pose, cameraPos, from, color);
            this.lineVertex(consumer, pose, cameraPos, to, color);
        }
    }

    @Protect(value=Level.STD)
    private final void drawPath(VertexConsumer consumer, MatrixStack.Entry pose, Vec3d cameraPos, List<? extends Vec3d> points, int color) {
        if (points.size() < 2) {
            return;
        }
        int segments = points.size() - 1;
        int n = points.size();
        for (int i = 1; i < n; ++i) {
            float fade = 1.0f - (float)i / (float)segments * 0.35f;
            int segmentColor = ColorEngine.multAlpha(color, fade);
            if (ColorEngine.alpha(segmentColor) <= 0) continue;
            this.lineVertex(consumer, pose, cameraPos, points.get(i - 1), segmentColor);
            this.lineVertex(consumer, pose, cameraPos, points.get(i), segmentColor);
        }
    }

    private final void lineVertex(VertexConsumer consumer, MatrixStack.Entry pose, Vec3d cameraPos, Vec3d worldPos, int color) {
        consumer.vertex(pose, (float)(worldPos.x - cameraPos.x), (float)(worldPos.y - cameraPos.y), (float)(worldPos.z - cameraPos.z)).color(color);
    }

    private final void emitLandingMarker(VertexConsumer consumer, MatrixStack.Entry pose, Vec3d end, Vec3d cameraPos, int color) {
        double size = 0.13;
        double y = end.y + 0.004;
        Vec3d vec3d2 = new Vec3d(end.x - size, y, end.z - size).subtract(cameraPos);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"subtract(...)");
        this.quadVertex(consumer, pose, vec3d2, color);
        Vec3d vec3d3 = new Vec3d(end.x + size, y, end.z - size).subtract(cameraPos);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"subtract(...)");
        this.quadVertex(consumer, pose, vec3d3, color);
        Vec3d vec3d4 = new Vec3d(end.x + size, y, end.z + size).subtract(cameraPos);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"subtract(...)");
        this.quadVertex(consumer, pose, vec3d4, color);
        Vec3d vec3d5 = new Vec3d(end.x - size, y, end.z + size).subtract(cameraPos);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d5, (String)"subtract(...)");
        this.quadVertex(consumer, pose, vec3d5, color);
    }

    private final void quadVertex(VertexConsumer consumer, MatrixStack.Entry pose, Vec3d pos, int color) {
        consumer.vertex(pose, (float)pos.x, (float)pos.y, (float)pos.z).color(color);
    }

    private final void activate(HolyWorldItem item, long now) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        Vec3d vec3d2 = player.getEntityPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d playerPos = vec3d2;
        double floorY = item.getFollowsPlayer() ? playerPos.y : this.resolveFloorY(playerPos);
        Vec3d center = item.getFollowsPlayer() ? HolyWorldHelper.Companion.zoneCenter(item, playerPos) : HolyWorldHelper.Companion.placedCenter(item, playerPos.x, floorY, playerPos.z);
        this.activeZones.removeIf(zone -> zone.getItem() == item && zone.getCenter().squaredDistanceTo(center) < 2.25);
        if (this.activeZones.size() >= 8) {
            this.activeZones.remove(0);
        }
        this.activeZones.add(new ActiveZone(item, center, floorY, now + item.getDurationMs()));
    }

    private final void updateSnowPrediction(float partialTicks) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        Vec3d vec3d2 = player.getRotationVec(partialTicks);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getViewVector(...)");
        Vec3d direction = vec3d2;
        if (direction.lengthSquared() < 1.0E-6) {
            this.snowLanding = null;
            this.simulationPoints.clear();
            return;
        }
        Vec3d vec3d3 = player.getCameraPosVec(partialTicks).add(direction.multiply(0.05));
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"add(...)");
        Vec3d start = vec3d3;
        Vec3d vec3d4 = direction.multiply(1.5);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"scale(...)");
        this.snowLanding = this.simulateLanding(start, vec3d4);
    }

    private final Vec3d simulateLanding(Vec3d start, Vec3d velocity) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return null;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return null;
        }
        ClientWorld level = clientWorld3;
        this.simulationPoints.clear();
        this.simulationPoints.add(start);
        Vec3d position = start;
        Vec3d motion = velocity;
        for (int i = 0; i < 160; ++i) {
            Vec3d next = position.add(motion);
            BlockHitResult hit = level.raycast(new RaycastContext(position, next, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, (Entity)player));
            if (hit != null && hit.getType() != HitResult.Type.MISS) {
                Vec3d landing = hit.getPos();
                this.simulationPoints.add(landing);
                return landing;
            }
            this.simulationPoints.add(next);
            position = next;
            motion = motion.multiply(0.99).add(0.0, -0.03, 0.0);
            if (position.y < -64.0) break;
        }
        return null;
    }

    @Protect(value=Level.MAX)
    private final double resolveFloorY(Vec3d position) {
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return position.y;
        }
        ClientWorld level = clientWorld3;
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return position.y;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        BlockHitResult blockHitResult2 = level.raycast(new RaycastContext(position.add(0.0, 0.75, 0.0), position.add(0.0, -8.0, 0.0), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, (Entity)player));
        Intrinsics.checkNotNullExpressionValue((Object)blockHitResult2, (String)"clip(...)");
        BlockHitResult hit = blockHitResult2;
        return hit.getType() != HitResult.Type.MISS ? hit.getPos().y + 0.012 : position.y;
    }

    private final Vec3d playerRenderPos(float partialTicks) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            Vec3d vec3d2 = Vec3d.ZERO;
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
            return vec3d2;
        }
        ClientPlayerEntity p = clientPlayerEntity2;
        return new Vec3d(MathHelper.lerp((double)partialTicks, (double)p.lastRenderX, (double)p.getX()), MathHelper.lerp((double)partialTicks, (double)p.lastRenderY, (double)p.getY()), MathHelper.lerp((double)partialTicks, (double)p.lastRenderZ, (double)p.getZ()));
    }

    private final Vec3d smoothPreview(Vec3d target) {
        Vec3d next;
        Vec3d current = this.smoothedPreview;
        if (current == null || current.squaredDistanceTo(target) > 64.0) {
            this.smoothedPreview = target;
            return target;
        }
        Vec3d vec3d2 = current.add(target.subtract(current).multiply(0.35));
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"add(...)");
        this.smoothedPreview = next = vec3d2;
        return next;
    }

    private final void clearState() {
        this.activeZones.clear();
        this.simulationPoints.clear();
        this.smoothedPreview = null;
        this.snowLanding = null;
        this.lastActivationMs = 0L;
        this.lastFrameNanos = 0L;
        this.previewOccupiedBlend = 0.0f;
    }

    private final boolean hasOtherPlayerIn(HolyWorldItem item, Vec3d center) {
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return false;
        }
        ClientWorld level = clientWorld3;
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return false;
        }
        ClientPlayerEntity self = clientPlayerEntity2;
        if (!item.isTrap()) {
            return false;
        }
        for (Object e : level.getPlayers()) {
            Intrinsics.checkNotNullExpressionValue(e, (String)"next(...)");
            AbstractClientPlayerEntity player = (AbstractClientPlayerEntity)e;
            if (Intrinsics.areEqual((Object)player, (Object)self) || player.isSpectator()) continue;
            Vec3d vec3d2 = player.getEntityPos();
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
            if (!HolyWorldHelper.Companion.zoneContains(item, center, vec3d2)) continue;
            return true;
        }
        return false;
    }

    private final float approach(float value, float target) {
        return value + (target - value) * Math.min(1.0f, this.frameDt * 6.0f);
    }

    private final int blendOccupied(int baseColor, float blend) {
        if (blend <= 0.001f) {
            return baseColor;
        }
        if (blend >= 0.999f) {
            return this.playerInZoneColor.getColor();
        }
        return ColorEngine.lerpColor(baseColor, this.playerInZoneColor.getColor(), blend);
    }

    private static final Boolean previewOpacity$lambda$0(HolyWorldHelper this$0) {
        return this$0.heldPreview.getValue();
    }

    private static final boolean activate$lambda$0(HolyWorldItem $item, Vec3d $center, ActiveZone zone) {
        Intrinsics.checkNotNullParameter((Object)zone, (String)"zone");
        return zone.getItem() == $item && zone.getCenter().squaredDistanceTo($center) < 2.25;
    }

    private static final boolean activate$lambda$1(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    static {
        int[][] nArrayArray = new int[12][];
        int[] nArray = new int[]{0, 1};
        nArrayArray[0] = nArray;
        nArray = new int[]{1, 2};
        nArrayArray[1] = nArray;
        nArray = new int[]{2, 3};
        nArrayArray[2] = nArray;
        nArray = new int[]{3, 0};
        nArrayArray[3] = nArray;
        nArray = new int[]{4, 5};
        nArrayArray[4] = nArray;
        nArray = new int[]{5, 6};
        nArrayArray[5] = nArray;
        nArray = new int[]{6, 7};
        nArrayArray[6] = nArray;
        nArray = new int[]{7, 4};
        nArrayArray[7] = nArray;
        nArray = new int[]{0, 4};
        nArrayArray[8] = nArray;
        nArray = new int[]{1, 5};
        nArrayArray[9] = nArray;
        nArray = new int[]{2, 6};
        nArrayArray[10] = nArray;
        nArray = new int[]{3, 7};
        nArrayArray[11] = nArray;
        CUBE_EDGES = nArrayArray;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0010\u0007\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u000eR\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0007\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0017\u0010\t\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\"\u0010\u001f\u001a\u00020\u001e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$\u00a8\u0006%"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$ActiveZone;", "", "Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$HolyWorldItem;", "item", "Lnet/minecraft/Vec3d;", "center", "", "floorY", "", "expiresAt", "<init>", "(Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$HolyWorldItem;Lnet/minecraft/Vec3d;DJ)V", "", "moveTo", "(Lnet/minecraft/Vec3d;)V", "Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$HolyWorldItem;", "getItem", "()Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$HolyWorldItem;", "Lnet/minecraft/Vec3d;", "getCenter", "()Lnet/minecraft/Vec3d;", "setCenter", "D", "getFloorY", "()D", "setFloorY", "(D)V", "J", "getExpiresAt", "()J", "", "occupiedBlend", "F", "getOccupiedBlend", "()F", "setOccupiedBlend", "(F)V", "rtx.kimiko:kimiko"})
    private static final class ActiveZone {
        @NotNull
        private final HolyWorldItem item;
        @NotNull
        private Vec3d center;
        private double floorY;
        private final long expiresAt;
        private float occupiedBlend;

        public ActiveZone(@NotNull HolyWorldItem item, @NotNull Vec3d center, double floorY, long expiresAt) {
            Intrinsics.checkNotNullParameter((Object)((Object)item), (String)"item");
            Intrinsics.checkNotNullParameter((Object)center, (String)"center");
            this.item = item;
            this.center = center;
            this.floorY = floorY;
            this.expiresAt = expiresAt;
        }

        @NotNull
        public final HolyWorldItem getItem() {
            return this.item;
        }

        @NotNull
        public final Vec3d getCenter() {
            return this.center;
        }

        public final void setCenter(@NotNull Vec3d vec3d2) {
            Intrinsics.checkNotNullParameter((Object)vec3d2, (String)"<set-?>");
            this.center = vec3d2;
        }

        public final double getFloorY() {
            return this.floorY;
        }

        public final void setFloorY(double d) {
            this.floorY = d;
        }

        public final long getExpiresAt() {
            return this.expiresAt;
        }

        public final float getOccupiedBlend() {
            return this.occupiedBlend;
        }

        public final void setOccupiedBlend(float f) {
            this.occupiedBlend = f;
        }

        public final void moveTo(@NotNull Vec3d center) {
            Intrinsics.checkNotNullParameter((Object)center, (String)"center");
            this.center = center;
            this.floorY = center.y - (this.item.getShape() == Shape.CUBE ? (double)this.item.getSize() * 0.5 : 0.025);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J/\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ'\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0015\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00188\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u00188\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001aR\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b#\u0010\"R\u0014\u0010$\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010\"R\u0014\u0010&\u001a\u00020%8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010'\u00a8\u0006("}, d2={"Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$HolyWorldItem;", "item", "", "x", "floorY", "z", "Lnet/minecraft/Vec3d;", "placedCenter", "(Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$HolyWorldItem;DDD)Lnet/minecraft/Vec3d;", "position", "zoneCenter", "(Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$HolyWorldItem;Lnet/minecraft/Vec3d;)Lnet/minecraft/Vec3d;", "center", "", "zoneContains", "(Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$HolyWorldItem;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;)Z", "Lnet/minecraft/PlayerEntity;", "player", "matchHeld", "(Lnet/minecraft/PlayerEntity;)Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$HolyWorldItem;", "", "MAX_SIMULATION_TICKS", "I", "MAX_ACTIVE_ZONES", "CIRCLE_SEGMENTS", "", "", "CUBE_EDGES", "[[I", "SNOW_GRAVITY", "D", "SNOW_DRAG", "SNOW_SPEED", "", "ACTIVATION_DEBOUNCE_MS", "J", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final Vec3d placedCenter(HolyWorldItem item, double x, double floorY, double z) {
            double y = item.getFloorAnchored() ? floorY + (double)item.getSize() * 0.5 : floorY;
            return new Vec3d(x, y, z);
        }

        private final Vec3d zoneCenter(HolyWorldItem item, Vec3d position) {
            Vec3d vec3d2;
            if (item.getShape() == Shape.CUBE) {
                Vec3d vec3d3 = position.add(0.0, (double)item.getSize() * 0.5, 0.0);
                vec3d2 = vec3d3;
                Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"add(...)");
            } else {
                Vec3d vec3d4 = position.add(0.0, 0.025, 0.0);
                vec3d2 = vec3d4;
                Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"add(...)");
            }
            return vec3d2;
        }

        private final boolean zoneContains(HolyWorldItem item, Vec3d center, Vec3d position) {
            if (item.getShape() == Shape.CIRCLE) {
                double dx = position.x - center.x;
                double dz = position.z - center.z;
                return dx * dx + dz * dz <= (double)(item.getSize() * item.getSize());
            }
            double half = (double)item.getSize() * 0.5;
            return Math.abs(position.x - center.x) <= half && Math.abs(position.y - center.y) <= (double)item.getSize() && Math.abs(position.z - center.z) <= half;
        }

        private final HolyWorldItem matchHeld(PlayerEntity player) {
            HolyWorldItem main = HolyWorldItem.Companion.match(player.getMainHandStack());
            HolyWorldItem holyWorldItem = main;
            if (holyWorldItem == null) {
                holyWorldItem = HolyWorldItem.Companion.match(player.getOffHandStack());
            }
            return holyWorldItem;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001f\b\u0082\u0081\u0002\u0018\u0000 %2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001%BI\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\f\u00a2\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\t\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u000b\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0017\u0010\r\u001a\u00020\f8\u0006\u00a2\u0006\f\n\u0004\b\r\u0010!\u001a\u0004\b\"\u0010#R\u0017\u0010\u000e\u001a\u00020\f8\u0006\u00a2\u0006\f\n\u0004\b\u000e\u0010!\u001a\u0004\b\u000e\u0010#R\u0017\u0010\u000f\u001a\u00020\f8\u0006\u00a2\u0006\f\n\u0004\b\u000f\u0010!\u001a\u0004\b$\u0010#j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*\u00a8\u0006+"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$HolyWorldItem;", "", "Lnet/minecraft/Item;", "item", "", "nameQuery", "Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$Shape;", "shape", "", "size", "", "durationMs", "", "followsPlayer", "isTrap", "floorAnchored", "<init>", "(Ljava/lang/String;ILnet/minecraft/Item;Ljava/lang/String;Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$Shape;FJZZZ)V", "Lnet/minecraft/Item;", "getItem", "()Lnet/minecraft/Item;", "Ljava/lang/String;", "getNameQuery", "()Ljava/lang/String;", "Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$Shape;", "getShape", "()Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$Shape;", "F", "getSize", "()F", "J", "getDurationMs", "()J", "Z", "getFollowsPlayer", "()Z", "getFloorAnchored", "Companion", "STUN", "SNOW", "EXPLOSIVE_THING", "TRAP", "EXPLOSIVE_TRAP", "rtx.kimiko:kimiko"})
    private static enum HolyWorldItem {
        STUN(Items.NETHER_STAR, "стан", Shape.CUBE, 30.0f, 15000L, false, false, false),
        SNOW(Items.SNOWBALL, "ком снега", Shape.CIRCLE, 0.0f, 0L, false, false, false),
        EXPLOSIVE_THING(Items.FIRE_CHARGE, "взрывная шту", Shape.CIRCLE, 3.0f, 0L, false, false, false),
        TRAP(Items.POPPED_CHORUS_FRUIT, "трапка", Shape.CUBE, 3.0f, 0L, false, true, true),
        EXPLOSIVE_TRAP(Items.PRISMARINE_SHARD, "взрывная трапка", Shape.CIRCLE, 5.0f, 0L, true, true, false);
@NotNull
        public static final Companion Companion;
        @NotNull
        private final Item item;
        @NotNull
        private final String nameQuery;
        @NotNull
        private final Shape shape;
        private final float size;
        private final long durationMs;
        private final boolean followsPlayer;
        private final boolean isTrap;
        private final boolean floorAnchored;

        private HolyWorldItem(Item item, String nameQuery, Shape shape, float size, long durationMs, boolean followsPlayer, boolean isTrap, boolean floorAnchored) {
            this.item = item;
            this.nameQuery = nameQuery;
            this.shape = shape;
            this.size = size;
            this.durationMs = durationMs;
            this.followsPlayer = followsPlayer;
            this.isTrap = isTrap;
            this.floorAnchored = floorAnchored;
        }

        @NotNull
        public final Item getItem() {
            return this.item;
        }

        @NotNull
        public final String getNameQuery() {
            return this.nameQuery;
        }

        @NotNull
        public final Shape getShape() {
            return this.shape;
        }

        public final float getSize() {
            return this.size;
        }

        public final long getDurationMs() {
            return this.durationMs;
        }

        public final boolean getFollowsPlayer() {
            return this.followsPlayer;
        }

        public final boolean isTrap() {
            return this.isTrap;
        }

        public final boolean getFloorAnchored() {
            return this.floorAnchored;
        }

        @NotNull
        public static EnumEntries<HolyWorldItem> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

        static {
            Companion = new Companion(null);
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$HolyWorldItem.Companion;", "", "<init>", "()V", "Lnet/minecraft/ItemStack;", "stack", "Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$HolyWorldItem;", "match", "(Lnet/minecraft/ItemStack;)Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$HolyWorldItem;", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            @Nullable
            public final HolyWorldItem match(@Nullable ItemStack stack) {
                if (stack == null || stack.isEmpty()) {
                    return null;
                }
                for (HolyWorldItem entry : HolyWorldItem.getEntries()) {
                    if (!stack.isOf(entry.getItem()) || !InventoryItems.nameContains(stack, entry.getNameQuery())) continue;
                    return entry;
                }
                return null;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/HolyWorldHelper$Shape;", "", "<init>", "(Ljava/lang/String;I)V", "CUBE", "CIRCLE", "rtx.kimiko:kimiko"})
    private static enum Shape {
        CUBE,
        CIRCLE;

        @NotNull
        public static EnumEntries<Shape> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }
    }
}

