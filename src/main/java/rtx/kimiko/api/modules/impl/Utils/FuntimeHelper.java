/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.NoWhenBranchMatchedException
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.Items
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Direction
 *  net.minecraft.util.math.Box
 *  net.minecraft.util.hit.HitResult.Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.RaycastContext
 *  net.minecraft.world.RaycastContext.FluidHandling
 *  net.minecraft.world.RaycastContext.ShapeType
 *  net.minecraft.util.hit.BlockHitResult
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Box;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.RaycastContext;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.restrict.Server;
import rtx.kimiko.api.modules.restrict.ServerRule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.NumberSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.inventory.InventoryItems;
import rtx.kimiko.utils.render.util.world.WorldShapeRenderer;
import sigil.protect.Level;
import sigil.protect.Protect;

@ServerRule(mode=ServerRule.Mode.BLOCK, servers={Server.ST})
@Feature(value={"funtimehelper"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00b4\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 R2\u00020\u0001:\u0003STRB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0003b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ'\u0010\u0011\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0003b\u000e\b\r\u0012\n\b\u000e\u0012\u0006\b\n0\u000f8\u0010\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015JO\u0010!\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\nH\u0002\u00a2\u0006\u0004\b!\u0010\"JO\u0010%\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\nH\u0002\u00a2\u0006\u0004\b%\u0010\"JG\u0010&\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\nH\u0002\u00a2\u0006\u0004\b&\u0010'J?\u0010-\u001a\u0004\u0018\u00010\f2\u0006\u0010(\u001a\u00020\f2\u0006\u0010)\u001a\u00020\f2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\f0*H\u0003b\u000e\b\r\u0012\n\b\u000e\u0012\u0006\b\n0\u000f8,\u00a2\u0006\u0004\b-\u0010.JG\u00101\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\f2\u0006\u00100\u001a\u00020/2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\nH\u0002\u00a2\u0006\u0004\b1\u00102Jg\u00108\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\f2\u0006\u00104\u001a\u0002032\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001d2\u0006\u00105\u001a\u00020\u001d2\u0006\u00107\u001a\u0002062\u0006\u0010 \u001a\u00020\nH\u0003b\u000e\b\r\u0012\n\b\u000e\u0012\u0006\b\n0\u000f8\u0010\u00a2\u0006\u0004\b8\u00109R\u0014\u0010;\u001a\u00020:8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b;\u0010<R\u0014\u0010>\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b@\u0010?R\u0014\u0010B\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0014\u0010D\u001a\u00020:8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u0010<R\u0014\u0010F\u001a\u00020E8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bF\u0010GR\u0014\u0010H\u001a\u00020E8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u0010GR\u0014\u0010I\u001a\u00020:8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u0010<R\u0014\u0010J\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010?R\u0014\u0010K\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bK\u0010CR$\u0010N\u001a\u0012\u0012\u0004\u0012\u00020\f0Lj\b\u0012\u0004\u0012\u00020\f`M8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bN\u0010OR\u0018\u0010P\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bP\u0010Q\u00ca\u0001\u001e\bU\u0012\n\bV\u0012\u0006\b\n0W8X\u0012\u000e\bY\u0012\n\b\fJ\u0006\b\n0Z8[\u00ca\u0001\u0010\b\\\u0012\f\b\u000e\u0012\b\b\fJ\u0004\b\b(]\u00a8\u0006^"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/FuntimeHelper;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "event", "", "Lrtx/kimiko/api/events/EventHandler;", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "", "partialTicks", "Lnet/minecraft/Vec3d;", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "STD", "playerRenderPos", "(F)Lnet/minecraft/Vec3d;", "target", "smoothPosition", "(Lnet/minecraft/Vec3d;)Lnet/minecraft/Vec3d;", "Lnet/minecraft/VertexConsumerProvider$Immediate;", "provider", "Lnet/minecraft/MatrixStack;", "stack", "cameraPos", "center", "radius", "", "fill", "outline", "width", "renderRadius", "(Lnet/minecraft/VertexConsumerProvider$Immediate;Lnet/minecraft/MatrixStack;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;FIIF)V", "base", "expand", "renderTrapCube", "renderSnowPrediction", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;Lnet/minecraft/VertexConsumerProvider$Immediate;Lnet/minecraft/MatrixStack;Lnet/minecraft/Vec3d;IIF)V", "start", "velocity", "", "points", "MAX", "simulateLanding", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;Ljava/util/List;)Lnet/minecraft/Vec3d;", "Lnet/minecraft/PlayerEntity;", "player", "renderPlast", "(Lnet/minecraft/VertexConsumerProvider$Immediate;Lnet/minecraft/MatrixStack;Lnet/minecraft/Vec3d;Lnet/minecraft/PlayerEntity;IIF)V", "Lnet/minecraft/BlockPos;", "origin", "stepX", "", "flipZ", "renderSidePlast", "(Lnet/minecraft/VertexConsumerProvider$Immediate;Lnet/minecraft/MatrixStack;Lnet/minecraft/Vec3d;Lnet/minecraft/BlockPos;IIIZF)V", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "renderSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "showFill", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "showOutline", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "fillAlpha", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "colorsSeparator", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "zoneColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "trajectoryColor", "smoothSeparator", "smooth", "smoothSpeed", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "simulationPoints", "Ljava/util/ArrayList;", "smoothedPos", "Lnet/minecraft/Vec3d;", "Companion", "PreviewKind", "FuntimeItem", "Lrtx/kimiko/api/modules/restrict/ServerRule;", "mode", "Lrtx/kimiko/api/modules/restrict/ServerRule$Mode;", "BLOCK", "servers", "Lrtx/kimiko/api/modules/restrict/Server;", "ST", "Lrtx/kimiko/api/liteapi/Feature;", "funtimehelper", "rtx.kimiko:kimiko"})
public final class FuntimeHelper
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting renderSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Отображение"));
    @NotNull
    private final BooleanSetting showFill = (BooleanSetting)this.register((Setting)new BooleanSetting("Заливка", "Рисует полупрозрачную заливку зон.", true));
    @NotNull
    private final BooleanSetting showOutline = (BooleanSetting)this.register((Setting)new BooleanSetting("Контур", "Рисует контур зон.", true));
    @NotNull
    private final NumberSetting fillAlpha = (NumberSetting)this.register((Setting)new NumberSetting("Прозрачность заливки", "Прозрачность заливки зон.", 35.0, 5.0, 80.0, 1.0).visibleWhen(() -> FuntimeHelper.fillAlpha$lambda$0(this)));
    @NotNull
    private final SeparatorSetting colorsSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Цвета"));
    @NotNull
    private final ColorSetting zoneColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет зоны", "Цвет радиусов и зон предметов.", new Color(127, 242, 255, 220)));
    @NotNull
    private final ColorSetting trajectoryColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет траектории", "Цвет линии полёта снаряда.", new Color(185, 230, 255, 230)));
    @NotNull
    private final SeparatorSetting smoothSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Плавность"));
    @NotNull
    private final BooleanSetting smooth = (BooleanSetting)this.register((Setting)new BooleanSetting("Сглаживание", "Плавное движение зон за игроком.", true));
    @NotNull
    private final NumberSetting smoothSpeed = (NumberSetting)this.register((Setting)new NumberSetting("Скорость сглаживания", "Больше — быстрее догоняет позицию.", 0.35, 0.05, 1.0, 0.01).visibleWhen(() -> FuntimeHelper.smoothSpeed$lambda$0(this)));
    @NotNull
    private final ArrayList<Vec3d> simulationPoints = new ArrayList(301);
    @Nullable
    private Vec3d smoothedPos;
    private static final int MAX_SIMULATION_TICKS = 300;
    private static final double SNOW_GRAVITY = 0.03;
    private static final double SNOW_DRAG = 0.99;
    private static final double SNOW_SPEED = 1.5;

    public FuntimeHelper() {
        super("Funtime Helper", "Показывает радиусы FunTime-предметов в руке.", Category.UTILS);
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
        FuntimeItem item = FuntimeHelper.Companion.matchHeld((PlayerEntity)player);
        if (item == null) {
            this.smoothedPos = null;
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
        int fill = this.showFill.getValue() ? ColorEngine.multAlpha(this.zoneColor.getValue(), this.fillAlpha.getFloat() / 100.0f) : 0;
        int outline = this.showOutline.getValue() ? this.zoneColor.getValue() : 0;
        float width = 1.0f;
        Vec3d base = this.smoothPosition(this.playerRenderPos(event.getPartialTicks()));
        switch (WhenMappings.$EnumSwitchMapping$0[item.getKind().ordinal()]) {
            case 1: {
                Vec3d vec3d3 = base.add(0.0, 0.02, 0.0);
                Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"add(...)");
                this.renderRadius(provider, stack, cameraPos, vec3d3, item.getRadius(), fill, outline, width);
                break;
            }
            case 2: {
                this.renderTrapCube(provider, stack, cameraPos, base, item.getRadius(), fill, outline, width);
                break;
            }
            case 3: {
                this.renderPlast(provider, stack, cameraPos, (PlayerEntity)player, fill, outline, width);
                break;
            }
            case 4: {
                this.renderSnowPrediction(event, provider, stack, cameraPos, fill, outline, width);
                break;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    @Protect(value=Level.STD)
    private final Vec3d playerRenderPos(float partialTicks) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            Vec3d vec3d2 = Vec3d.ZERO;
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
            return vec3d2;
        }
        ClientPlayerEntity p = clientPlayerEntity2;
        double x = MathHelper.lerp((double)partialTicks, (double)p.lastRenderX, (double)p.getX());
        double y = MathHelper.lerp((double)partialTicks, (double)p.lastRenderY, (double)p.getY());
        double z = MathHelper.lerp((double)partialTicks, (double)p.lastRenderZ, (double)p.getZ());
        return new Vec3d(x, y, z);
    }

    private final Vec3d smoothPosition(Vec3d target) {
        Vec3d next;
        if (!this.smooth.getValue()) {
            this.smoothedPos = target;
            return target;
        }
        Vec3d current = this.smoothedPos;
        if (current == null || current.squaredDistanceTo(target) > 64.0) {
            this.smoothedPos = target;
            return target;
        }
        float t = this.smoothSpeed.getValue();
        Vec3d vec3d2 = current.add(target.subtract(current).multiply((double)t));
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"add(...)");
        this.smoothedPos = next = vec3d2;
        return next;
    }

    private final void renderRadius(VertexConsumerProvider.Immediate provider, MatrixStack stack, Vec3d cameraPos, Vec3d center, float radius, int fill, int outline, float width) {
        WorldShapeRenderer.horizontalFilledCircle(provider, stack, cameraPos, center, radius, fill, outline, width);
    }

    private final void renderTrapCube(VertexConsumerProvider.Immediate provider, MatrixStack stack, Vec3d cameraPos, Vec3d base, float expand, int fill, int outline, float width) {
        double cx = base.x;
        double cz = base.z;
        double minY = base.y + 1.0;
        Box core = new Box(cx - 0.5, minY, cz - 0.5, cx + 0.5, minY + 1.0, cz + 0.5);
        Box box2 = core.expand((double)expand);
        Intrinsics.checkNotNullExpressionValue((Object)box2, (String)"inflate(...)");
        Box box = box2;
        WorldShapeRenderer.box(provider, stack, cameraPos, box, fill, outline, width);
    }

    private final void renderSnowPrediction(WorldRenderEvent event, VertexConsumerProvider.Immediate provider, MatrixStack stack, Vec3d cameraPos, int fill, int outline, float width) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        Vec3d vec3d2 = player.getCameraPosVec(event.getPartialTicks());
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getEyePosition(...)");
        Vec3d eye = vec3d2;
        Vec3d vec3d3 = player.getRotationVector();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"getLookAngle(...)");
        Vec3d direction = vec3d3;
        if (direction.lengthSquared() < 1.0E-6) {
            return;
        }
        Vec3d vec3d4 = eye.add(direction.multiply(0.05));
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"add(...)");
        Vec3d start = vec3d4;
        Vec3d vec3d5 = direction.multiply(1.5);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d5, (String)"scale(...)");
        Vec3d velocity = vec3d5;
        Vec3d landing = this.simulateLanding(start, velocity, (List<Vec3d>)this.simulationPoints);
        WorldShapeRenderer.trajectory(provider, stack, cameraPos, (List<? extends Vec3d>)this.simulationPoints, this.trajectoryColor.getValue());
        if (landing != null) {
            WorldShapeRenderer.horizontalFilledCircle(provider, stack, cameraPos, landing, FuntimeItem.SNOW.getRadius(), fill, outline, width);
        }
    }

    @Protect(value=Level.MAX)
    private final Vec3d simulateLanding(Vec3d start, Vec3d velocity, List<Vec3d> points) {
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
        points.clear();
        points.add(start);
        Vec3d position = start;
        Vec3d motion = velocity;
        Vec3d landing = null;
        for (int i = 0; i < 300; ++i) {
            Vec3d next = position.add(motion);
            BlockHitResult hit = level.raycast(new RaycastContext(position, next, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, (Entity)player));
            if (hit != null && hit.getType() != HitResult.Type.MISS) {
                landing = hit.getPos();
                points.add(landing);
                break;
            }
            points.add(next);
            position = next;
            motion = motion.multiply(0.99).add(0.0, -0.03, 0.0);
            if (position.y < -64.0) break;
        }
        return landing;
    }

    private final void renderPlast(VertexConsumerProvider.Immediate provider, MatrixStack stack, Vec3d cameraPos, PlayerEntity player, int fill, int outline, float width) {
        BlockPos blockPos2 = player.getBlockPos();
        Intrinsics.checkNotNullExpressionValue((Object)blockPos2, (String)"blockPosition(...)");
        BlockPos playerPos = blockPos2;
        float yaw = MathHelper.wrapDegrees((float)player.getYaw());
        float pitch = player.getPitch();
        if (Math.abs(pitch) > 60.0f) {
            BlockPos blockPos3 = playerPos.up().offset(player.getHorizontalFacing(), 3);
            Intrinsics.checkNotNullExpressionValue((Object)blockPos3, (String)"relative(...)");
            BlockPos blockPos = blockPos3;
            Box box2 = Box.enclosing((BlockPos)blockPos.east(3).south(3).down(), (BlockPos)blockPos.west(2).north(2).up());
            Intrinsics.checkNotNullExpressionValue((Object)box2, (String)"encapsulatingFullBlocks(...)");
            Box box = box2;
            WorldShapeRenderer.box(provider, stack, cameraPos, box, fill, outline, width);
            return;
        }
        if (yaw <= -157.5f || yaw >= 157.5f) {
            BlockPos blockPos4 = playerPos.north(3).up();
            Intrinsics.checkNotNullExpressionValue((Object)blockPos4, (String)"above(...)");
            BlockPos blockPos = blockPos4;
            Box box3 = Box.enclosing((BlockPos)blockPos.down(2).east(3), (BlockPos)blockPos.up(3).west(2).south(2));
            Intrinsics.checkNotNullExpressionValue((Object)box3, (String)"encapsulatingFullBlocks(...)");
            Box box = box3;
            WorldShapeRenderer.box(provider, stack, cameraPos, box, fill, outline, width);
        } else if (yaw <= -112.5f) {
            BlockPos blockPos5 = playerPos.east(5).south().down();
            Intrinsics.checkNotNullExpressionValue((Object)blockPos5, (String)"below(...)");
            this.renderSidePlast(provider, stack, cameraPos, blockPos5, fill, outline, -1, true, width);
        } else if (yaw <= -67.5f) {
            BlockPos blockPos6 = playerPos.east(2).up();
            Intrinsics.checkNotNullExpressionValue((Object)blockPos6, (String)"above(...)");
            BlockPos blockPos = blockPos6;
            Box box4 = Box.enclosing((BlockPos)blockPos.down(2).south(3), (BlockPos)blockPos.up(3).north(2).east(2));
            Intrinsics.checkNotNullExpressionValue((Object)box4, (String)"encapsulatingFullBlocks(...)");
            Box box = box4;
            WorldShapeRenderer.box(provider, stack, cameraPos, box, fill, outline, width);
        } else if (yaw <= -22.5f) {
            BlockPos blockPos7 = playerPos.east(5).down();
            Intrinsics.checkNotNullExpressionValue((Object)blockPos7, (String)"below(...)");
            this.renderSidePlast(provider, stack, cameraPos, blockPos7, fill, outline, 1, false, width);
        } else if (yaw <= 22.5f) {
            BlockPos blockPos8 = playerPos.south(2).up();
            Intrinsics.checkNotNullExpressionValue((Object)blockPos8, (String)"above(...)");
            BlockPos blockPos = blockPos8;
            Box box5 = Box.enclosing((BlockPos)blockPos.down(2).east(3), (BlockPos)blockPos.up(3).west(2).south(2));
            Intrinsics.checkNotNullExpressionValue((Object)box5, (String)"encapsulatingFullBlocks(...)");
            Box box = box5;
            WorldShapeRenderer.box(provider, stack, cameraPos, box, fill, outline, width);
        } else if (yaw <= 67.5f) {
            BlockPos blockPos9 = playerPos.west(4).down();
            Intrinsics.checkNotNullExpressionValue((Object)blockPos9, (String)"below(...)");
            this.renderSidePlast(provider, stack, cameraPos, blockPos9, fill, outline, 1, true, width);
        } else if (yaw <= 112.5f) {
            BlockPos blockPos10 = playerPos.west(3).up();
            Intrinsics.checkNotNullExpressionValue((Object)blockPos10, (String)"above(...)");
            BlockPos blockPos = blockPos10;
            Box box6 = Box.enclosing((BlockPos)blockPos.down(2).south(3), (BlockPos)blockPos.up(3).north(2).east(2));
            Intrinsics.checkNotNullExpressionValue((Object)box6, (String)"encapsulatingFullBlocks(...)");
            Box box = box6;
            WorldShapeRenderer.box(provider, stack, cameraPos, box, fill, outline, width);
        } else if (yaw <= 157.5f) {
            BlockPos blockPos11 = playerPos.west(4).south().down();
            Intrinsics.checkNotNullExpressionValue((Object)blockPos11, (String)"below(...)");
            this.renderSidePlast(provider, stack, cameraPos, blockPos11, fill, outline, -1, false, width);
        }
    }

    @Protect(value=Level.STD)
    private final void renderSidePlast(VertexConsumerProvider.Immediate provider, MatrixStack stack, Vec3d cameraPos, BlockPos origin, int fill, int outline, int stepX, boolean flipZ, float width) {
        int zStep = flipZ ? stepX : -stepX;
        BlockPos a = origin;
        BlockPos blockPos2 = origin.offset(stepX > 0 ? Direction.EAST : Direction.WEST, 4).offset(zStep > 0 ? Direction.SOUTH : Direction.NORTH, 4).up(5);
        Intrinsics.checkNotNullExpressionValue((Object)blockPos2, (String)"above(...)");
        BlockPos b = blockPos2;
        Box box2 = Box.enclosing((BlockPos)a, (BlockPos)b);
        Intrinsics.checkNotNullExpressionValue((Object)box2, (String)"encapsulatingFullBlocks(...)");
        Box box = box2;
        WorldShapeRenderer.box(provider, stack, cameraPos, box, fill, outline, width);
    }

    private static final Boolean fillAlpha$lambda$0(FuntimeHelper this$0) {
        return this$0.showFill.getValue();
    }

    private static final Boolean smoothSpeed$lambda$0(FuntimeHelper this$0) {
        return this$0.smooth.getValue();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000e\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/FuntimeHelper.Companion;", "", "<init>", "()V", "Lnet/minecraft/PlayerEntity;", "player", "Lrtx/kimiko/api/modules/impl/Utils/FuntimeHelper$FuntimeItem;", "matchHeld", "(Lnet/minecraft/PlayerEntity;)Lrtx/kimiko/api/modules/impl/Utils/FuntimeHelper$FuntimeItem;", "", "MAX_SIMULATION_TICKS", "I", "", "SNOW_GRAVITY", "D", "SNOW_DRAG", "SNOW_SPEED", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final FuntimeItem matchHeld(PlayerEntity player) {
            FuntimeItem main = FuntimeItem.Companion.match(player.getMainHandStack());
            FuntimeItem funtimeItem = main;
            if (funtimeItem == null) {
                funtimeItem = FuntimeItem.Companion.match(player.getOffHandStack());
            }
            return funtimeItem;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0018\b\u0082\u0081\u0002\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0018B)\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\t\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001f\u00a8\u0006 "}, d2={"Lrtx/kimiko/api/modules/impl/Utils/FuntimeHelper$FuntimeItem;", "", "Lnet/minecraft/Item;", "item", "", "nameQuery", "Lrtx/kimiko/api/modules/impl/Utils/FuntimeHelper$PreviewKind;", "kind", "", "radius", "<init>", "(Ljava/lang/String;ILnet/minecraft/Item;Ljava/lang/String;Lrtx/kimiko/api/modules/impl/Utils/FuntimeHelper$PreviewKind;F)V", "Lnet/minecraft/Item;", "getItem", "()Lnet/minecraft/Item;", "Ljava/lang/String;", "getNameQuery", "()Ljava/lang/String;", "Lrtx/kimiko/api/modules/impl/Utils/FuntimeHelper$PreviewKind;", "getKind", "()Lrtx/kimiko/api/modules/impl/Utils/FuntimeHelper$PreviewKind;", "F", "getRadius", "()F", "Companion", "TRAP", "DISORIENTATION", "SUGAR", "FIRE_SWIRL", "GOD_AURA", "PLAST", "SNOW", "rtx.kimiko:kimiko"})
    private static enum FuntimeItem {
        TRAP(Items.NETHERITE_SCRAP, "трапка", PreviewKind.TRAP_CUBE, 1.99f),
        DISORIENTATION(Items.ENDER_EYE, "дезориентация", PreviewKind.RADIUS, 10.0f),
        SUGAR(Items.SUGAR, "явная", PreviewKind.RADIUS, 10.0f),
        FIRE_SWIRL(Items.FIRE_CHARGE, "огненный смерч", PreviewKind.RADIUS, 10.0f),
        GOD_AURA(Items.PHANTOM_MEMBRANE, "божья аура", PreviewKind.RADIUS, 10.0f),
        PLAST(Items.DRIED_KELP, "пласт", PreviewKind.PLAST, 0.0f),
        SNOW(Items.SNOWBALL, "снежок заморозка", PreviewKind.SNOW_PREDICTION, 4.0f);
@NotNull
        public static final Companion Companion;
        @NotNull
        private final Item item;
        @NotNull
        private final String nameQuery;
        @NotNull
        private final PreviewKind kind;
        private final float radius;

        private FuntimeItem(Item item, String nameQuery, PreviewKind kind, float radius) {
            this.item = item;
            this.nameQuery = nameQuery;
            this.kind = kind;
            this.radius = radius;
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
        public final PreviewKind getKind() {
            return this.kind;
        }

        public final float getRadius() {
            return this.radius;
        }

        @NotNull
        public static EnumEntries<FuntimeItem> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

        static {
            Companion = new Companion(null);
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/FuntimeHelper$FuntimeItem.Companion;", "", "<init>", "()V", "Lnet/minecraft/ItemStack;", "stack", "Lrtx/kimiko/api/modules/impl/Utils/FuntimeHelper$FuntimeItem;", "match", "(Lnet/minecraft/ItemStack;)Lrtx/kimiko/api/modules/impl/Utils/FuntimeHelper$FuntimeItem;", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            @Nullable
            public final FuntimeItem match(@Nullable ItemStack stack) {
                if (stack == null || stack.isEmpty()) {
                    return null;
                }
                for (FuntimeItem entry : FuntimeItem.getEntries()) {
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

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/FuntimeHelper$PreviewKind;", "", "<init>", "(Ljava/lang/String;I)V", "RADIUS", "TRAP_CUBE", "PLAST", "SNOW_PREDICTION", "rtx.kimiko:kimiko"})
    private static enum PreviewKind {
        RADIUS,
        TRAP_CUBE,
        PLAST,
        SNOW_PREDICTION;
@NotNull
        public static EnumEntries<PreviewKind> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }
    }

    @Metadata(mv={2, 4, 0}, k=3, xi=48)
    public static final class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] nArray = new int[PreviewKind.values().length];
            try {
                nArray[PreviewKind.RADIUS.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[PreviewKind.TRAP_CUBE.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[PreviewKind.PLAST.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[PreviewKind.SNOW_PREDICTION.ordinal()] = 4;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$0 = nArray;
        }
    }
}

