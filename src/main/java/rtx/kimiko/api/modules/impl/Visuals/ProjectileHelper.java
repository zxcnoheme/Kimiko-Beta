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
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ItemEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.projectile.PersistentProjectileEntity
 *  net.minecraft.entity.projectile.thrown.EnderPearlEntity
 *  net.minecraft.entity.projectile.TridentEntity
 *  net.minecraft.entity.projectile.thrown.PotionEntity
 *  net.minecraft.item.BowItem
 *  net.minecraft.item.CrossbowItem
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.Items
 *  net.minecraft.item.TridentItem
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Direction
 *  net.minecraft.util.math.Position
 *  net.minecraft.util.hit.HitResult.Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.registry.tag.FluidTags
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.RaycastContext
 *  net.minecraft.world.RaycastContext.FluidHandling
 *  net.minecraft.world.RaycastContext.ShapeType
 *  net.minecraft.util.hit.BlockHitResult
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Matrix4f
 *  org.joml.Vector4f
 *  org.lwjgl.opengl.GL11
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.TridentItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.RaycastContext;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.MultiSelectSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;
import rtx.kimiko.utils.render.render2d.Render2D;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"projectilehelper"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00ec\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 x2\u00020\u0001:\u0007yz{|}~xB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u0004H\u0017b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0014\u00a2\u0006\u0004\b\f\u0010\u0003J+\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0003b\u0002\b\u000fb\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\u0010\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0017H\u0003b\u0002\b\u000f\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ7\u0010%\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u00132\u0006\u0010\"\u001a\u00020!2\u0006\u0010$\u001a\u00020#H\u0002\u00a2\u0006\u0004\b%\u0010&J7\u0010)\u001a\u00020\u00132\u0006\u0010'\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u00132\u0006\u0010$\u001a\u00020#H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078(\u00a2\u0006\u0004\b)\u0010*J\u001f\u0010+\u001a\u00020\u000bH\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078(\u00a2\u0006\u0004\b+\u0010\u0003J9\u0010/\u001a\u0004\u0018\u00010.2\u0006\u0010,\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u001dH\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078(\u00a2\u0006\u0004\b/\u00100J7\u00109\u001a\u00020\u000b2\u0006\u00102\u001a\u0002012\u0006\u00104\u001a\u0002032\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u00106\u001a\u0002052\u0006\u00108\u001a\u000207H\u0002\u00a2\u0006\u0004\b9\u0010:J?\u0010=\u001a\u00020\u000b2\u0006\u00102\u001a\u0002012\u0006\u00104\u001a\u0002032\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010<\u001a\u00020;H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\u0010\u00a2\u0006\u0004\b=\u0010>JG\u0010?\u001a\u00020\u000b2\u0006\u00102\u001a\u0002012\u0006\u00104\u001a\u0002032\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010<\u001a\u00020;2\u0006\u00108\u001a\u000207H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\u0010\u00a2\u0006\u0004\b?\u0010@JG\u0010E\u001a\u00020\u000b2\u0006\u00102\u001a\u0002012\u0006\u00104\u001a\u0002032\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010A\u001a\u00020\u00132\u0006\u0010B\u001a\u00020\u00132\u0006\u0010D\u001a\u00020C2\u0006\u00108\u001a\u000207H\u0002\u00a2\u0006\u0004\bE\u0010FJ7\u0010I\u001a\u00020\u000b2\u0006\u00102\u001a\u0002012\u0006\u00104\u001a\u0002032\u0006\u0010G\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010H\u001a\u00020CH\u0002\u00a2\u0006\u0004\bI\u0010JJ\u001f\u0010M\u001a\u00020\u00132\u0006\u0010K\u001a\u00020\u00042\u0006\u0010L\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bM\u0010NJ\u0019\u0010P\u001a\u0004\u0018\u00010#2\u0006\u0010O\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\bP\u0010QJ#\u0010W\u001a\u0004\u0018\u00010V2\u0006\u0010S\u001a\u00020R2\b\u0010U\u001a\u0004\u0018\u00010TH\u0002\u00a2\u0006\u0004\bW\u0010XJ/\u0010Y\u001a\u00020!2\u0006\u0010S\u001a\u00020R2\u0006\u0010U\u001a\u00020TH\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078(\u00a2\u0006\u0004\bY\u0010ZJA\u0010_\u001a\u0004\u0018\u00010^2\u0006\u0010\\\u001a\u00020[2\u0006\u0010]\u001a\u00020[2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010G\u001a\u00020\u0013H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078(\u00a2\u0006\u0004\b_\u0010`R\u0014\u0010b\u001a\u00020a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bb\u0010cR\u0014\u0010e\u001a\u00020d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\be\u0010fR\u0014\u0010h\u001a\u00020g8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bh\u0010iR\u0014\u0010k\u001a\u00020j8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bk\u0010lR\u0014\u0010m\u001a\u00020j8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bm\u0010lR\u001a\u0010o\u001a\b\u0012\u0004\u0012\u0002050n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bo\u0010pR\u001a\u0010q\u001a\b\u0012\u0004\u0012\u00020;0n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bq\u0010pR\u001a\u0010s\u001a\b\u0012\u0004\u0012\u00020r0n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bs\u0010pR\u001a\u0010u\u001a\b\u0012\u0004\u0012\u00020t0n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bu\u0010pR\u0014\u0010v\u001a\u00020^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bv\u0010w\u00ca\u0001\u0011\b\u007f\u0012\r\b\u0006\u0012\t\b\fJ\u0005\b\b(\u0080\u0001\u00a8\u0006\u0081\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "fadeOutSeconds", "()F", "", "onDisable", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "STD", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "Lnet/minecraft/Vec3d;", "cameraPos", "collectLabels", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;Lnet/minecraft/Vec3d;)V", "Lrtx/kimiko/api/events/impl/render/HudRenderEvent;", "onHud", "(Lrtx/kimiko/api/events/impl/render/HudRenderEvent;)V", "partial", "predictFromHand", "(F)V", "Lnet/minecraft/Entity;", "shooter", "start", "motion", "", "isEntityProjectile", "Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$Profile;", "profile", "predict", "(Lnet/minecraft/Entity;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;ZLrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$Profile;)V", "prevPos", "MAX", "step", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$Profile;)Lnet/minecraft/Vec3d;", "gatherLiving", "from", "to", "Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$EntityHit;", "firstEntityInPath", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;Lnet/minecraft/Entity;)Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$EntityHit;", "Lnet/minecraft/VertexConsumer;", "consumer", "Lnet/minecraft/MatrixStack$Entry;", "pose", "Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$LineData;", "line", "", "radius", "emitLineTube", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$LineData;D)V", "Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$LandingData;", "data", "renderDisc", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$LandingData;)V", "emitCrossTube", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$LandingData;D)V", "a", "b", "", "color", "straightTube", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;ID)V", "pos", "rawColor", "vertex", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;I)V", "pitch", "yaw", "directionFromRotation", "(FF)Lnet/minecraft/Vec3d;", "entity", "profileFor", "(Lnet/minecraft/Entity;)Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$Profile;", "Lnet/minecraft/PlayerEntity;", "player", "Lnet/minecraft/ItemStack;", "stack", "Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$HandShot;", "handShot", "(Lnet/minecraft/PlayerEntity;Lnet/minecraft/ItemStack;)Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$HandShot;", "isUsing", "(Lnet/minecraft/PlayerEntity;Lnet/minecraft/ItemStack;)Z", "Lorg/joml/Matrix4f;", "positionMatrix", "projectionMatrix", "Lorg/joml/Vector4f;", "project", "(Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;)Lorg/joml/Vector4f;", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "visualSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "itemsToPredict", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "fromHand", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "lineWidth", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "indicatorSize", "", "linesToRender", "Ljava/util/List;", "landingData", "Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$Label;", "labels", "Lnet/minecraft/LivingEntity;", "livingScratch", "projectionScratch", "Lorg/joml/Vector4f;", "Companion", "Profile", "HandShot", "EntityHit", "LineData", "LandingData", "Label", "Lrtx/kimiko/api/liteapi/Feature;", "projectilehelper", "rtx.kimiko:kimiko"})
public final class ProjectileHelper
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting visualSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Настройка визуала"));
    @NotNull
    private final MultiSelectSetting itemsToPredict;
    @NotNull
    private final BooleanSetting fromHand;
    @NotNull
    private final SliderSetting lineWidth;
    @NotNull
    private final SliderSetting indicatorSize;
    @NotNull
    private final List<LineData> linesToRender;
    @NotNull
    private final List<LandingData> landingData;
    @NotNull
    private final List<Label> labels;
    @NotNull
    private final List<LivingEntity> livingScratch;
    @NotNull
    private final Vector4f projectionScratch;
    private static final int MAX_STEPS = 200;
    private static final float FONT_SIZE = 6.5f;
    private static final int CROSS_SIDES = 10;
    private static final float FADE_STEPS = 40.0f;
    @NotNull
    private static final float[] CROSS_COS = new float[10];
    @NotNull
    private static final float[] CROSS_SIN = new float[10];
    @NotNull
    private static final String PEARL = "Жемчуг эндера";
    @NotNull
    private static final String ARROW = "Стрелы";
    @NotNull
    private static final String TRIDENT = "Трезубец";
    @NotNull
    private static final String POTION = "Зелья";
    @NotNull
    private static final String ITEM = "Предметы";

    public ProjectileHelper() {
        super("Projectile Helper", "Отрисовывает траекторию полёта снарядов.", Category.VISUALS);
        String[] stringArray = new String[]{PEARL, ARROW, TRIDENT, POTION, ITEM};
        MultiSelectSetting multiSelectSetting = new MultiSelectSetting("Предметы для предсказания", "Какие снаряды предсказывать.").value(stringArray);
        stringArray = new String[]{PEARL, ARROW, TRIDENT, POTION};
        this.itemsToPredict = (MultiSelectSetting)this.register((Setting)multiSelectSetting.selected(stringArray));
        this.fromHand = (BooleanSetting)this.register((Setting)new BooleanSetting("Предсказание из рук", "Предсказывать снаряд в руке.", false));
        this.lineWidth = (SliderSetting)this.register((Setting)new SliderSetting("Толщина линии", "Толщина траектории.").range(0.1f, 0.3f).increment(0.05f).setValue(0.2f));
        this.indicatorSize = (SliderSetting)this.register((Setting)new SliderSetting("Размер индикатора", "Радиус круга приземления.").range(0.3f, 0.5f).increment(0.05f).setValue(0.4f));
        this.linesToRender = new ArrayList();
        this.landingData = new ArrayList();
        this.labels = new ArrayList();
        this.livingScratch = new ArrayList();
        this.projectionScratch = new Vector4f();
    }

    @Override
    @Protect(value=Level.CROWN)
    public float fadeOutSeconds() {
        return 0.5f;
    }

    @Override
    protected void onDisable() {
        this.linesToRender.clear();
        this.landingData.clear();
        this.labels.clear();
        this.livingScratch.clear();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventHandler
    @Protect(value=Level.STD)
    private final void onWorldRender(WorldRenderEvent event) {
        if (event.isPortalPass()) {
            return;
        }
        this.linesToRender.clear();
        this.landingData.clear();
        this.labels.clear();
        ClientPlayerEntity player = this.mc.player;
        ClientWorld level = this.mc.world;
        if (player == null || level == null) {
            return;
        }
        float partial = MathHelper.clamp((float)event.getPartialTicks(), (float)0.0f, (float)1.0f);
        this.gatherLiving();
        for (Entity entity : level.getEntities()) {
            Profile profile = this.profileFor(entity);
            if (profile == null || !this.itemsToPredict.isSelected(profile.getSetting())) continue;
            Vec3d motion = entity.getVelocity();
            if (motion == null || motion.lengthSquared() <= 1.0E-6) continue;
            Vec3d vec3d2 = entity.getLerpedPos(partial);
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getPosition(...)");
            this.predict(entity, vec3d2, motion, true, profile);
        }
        if (this.fromHand.getValue()) {
            this.predictFromHand(partial);
        }
        Camera camera = event.getCamera() == null ? this.mc.gameRenderer.getCamera() : event.getCamera();
        Intrinsics.checkNotNull((Object)camera);
        Vec3d cameraPos = camera.getCameraPos();
        VertexConsumerProvider.Immediate immediate2 = this.mc.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        VertexConsumerProvider.Immediate provider = immediate2;
        MatrixStack.Entry entry2 = event.getStack().peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        double radius = (double)this.lineWidth.getFloat() * 0.05;
        VertexConsumer vertexConsumer2 = provider.getBuffer(ClientPipelines.PROJECTILE_TRIS);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer tris = vertexConsumer2;
        for (LandingData data : this.landingData) {
            this.renderDisc(tris, pose, cameraPos, data);
        }
        VertexConsumer vertexConsumer3 = provider.getBuffer(ClientPipelines.TARGET_CIRCLE_NODEPTH);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer3, (String)"getBuffer(...)");
        VertexConsumer tubes = vertexConsumer3;
        for (LineData line : this.linesToRender) {
            this.emitLineTube(tubes, pose, cameraPos, line, radius);
        }
        for (LandingData data : this.landingData) {
            this.emitCrossTube(tubes, pose, cameraPos, data, radius * 0.7);
        }
        GL11.glEnable((int)2881);
        GL11.glHint((int)3155, (int)4354);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        try {
            provider.draw(ClientPipelines.PROJECTILE_TRIS);
            provider.draw(ClientPipelines.TARGET_CIRCLE_NODEPTH);
        }
        finally {
            GL11.glDisable((int)2881);
            GL11.glDisable((int)2848);
        }
        this.collectLabels(event, cameraPos);
    }

    private final void collectLabels(WorldRenderEvent event, Vec3d cameraPos) {
        Matrix4f positionMatrix = event.getPositionMatrix();
        Matrix4f projectionMatrix = event.getProjectionMatrix();
        float screenWidth = rtx.kimiko.api.drags.Position.Companion.screenWidth();
        float screenHeight = rtx.kimiko.api.drags.Position.Companion.screenHeight();
        for (LandingData data : this.landingData) {
            if (!data.isEntity()) continue;
            Vector4f projected = this.project(positionMatrix, projectionMatrix, cameraPos, data.getPos());
            if (projected == null) continue;
            float screenX = (projected.x / projected.w * 0.5f + 0.5f) * screenWidth;
            float screenY = (1.0f - (projected.y / projected.w * 0.5f + 0.5f)) * screenHeight;
            if (Float.isNaN(screenX) || Float.isNaN(screenY)) continue;
            this.labels.add(new Label(screenX, screenY, data.getTicks()));
        }
    }

    @EventHandler
    private final void onHud(HudRenderEvent event) {
        if (this.labels.isEmpty()) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        for (Label label : this.labels) {
            double seconds = (double)(label.getTicks() * 50) / 1000.0;
            Locale locale = Locale.US;
            String string = "%.1f";
            Object[] objectArray = new Object[]{seconds};
            String string2 = String.format(locale, string, Arrays.copyOf(objectArray, objectArray.length));
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"format(...)");
            String text = string2 + "s";
            float textWidth = Fonts.SEMIBOLD.msdfWidth(text, 6.5f);
            float boxWidth = textWidth + 8.0f;
            float boxX = label.getX() - boxWidth / 2.0f;
            float boxY = label.getY() - 7.25f;
            Render2D.rect(boxX, boxY + 3.0f, boxWidth, 8.5f, ColorEngine.rgba(0, 0, 0, 128));
            Fonts.SEMIBOLD.msdf(text, boxX + (boxWidth - textWidth) / 2.0f, boxY + 4.0f, 6.5f, -1);
        }
        Render2D.flush();
    }

    private final void predictFromHand(float partial) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        ItemStack itemStack2 = player.getMainHandStack();
        Intrinsics.checkNotNullExpressionValue((Object)itemStack2, (String)"getMainHandItem(...)");
        ItemStack stack = itemStack2;
        HandShot handShot = this.handShot((PlayerEntity)player, stack);
        if (handShot == null) {
            return;
        }
        HandShot shot = handShot;
        Vec3d look = this.directionFromRotation(player.getPitch(), player.getYaw());
        Vec3d vec3d2 = player.getCameraPosVec(partial).add(look.multiply(0.2));
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"add(...)");
        Vec3d start = vec3d2;
        Vec3d vec3d3 = look.multiply(shot.getSpeed()).add(0.0, player.getVelocity().y * 0.5, 0.0);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"add(...)");
        Vec3d motion = vec3d3;
        this.predict((Entity)player, start, motion, false, shot.getProfile());
    }

    private final void predict(Entity shooter, Vec3d start, Vec3d motion, boolean isEntityProjectile, Profile profile) {
        List points = new ArrayList();
        Vec3d pos = start;
        Vec3d vel = motion;
        int ticks = 0;
        Vec3d finalPos = pos;
        Vec3d normal = new Vec3d(0.0, 1.0, 0.0);
        boolean living = false;
        boolean stopped = false;
        for (int i = 0; i < 200; ++i) {
            BlockHitResult blockHit;
            ClientWorld level;
            points.add(pos);
            Vec3d prev = pos;
            Intrinsics.checkNotNullExpressionValue((Object)pos.add(vel), (String)"add(...)");
            ++ticks;
            EntityHit entityHit = this.firstEntityInPath(prev, pos, shooter);
            if (entityHit != null) {
                Vec3d vec3d2;
                finalPos = entityHit.getPoint();
                living = true;
                if (vel.lengthSquared() > 1.0E-9) {
                    Vec3d vec3d3 = vel.normalize().multiply(-1.0);
                    vec3d2 = vec3d3;
                    Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"scale(...)");
                } else {
                    vec3d2 = normal;
                }
                normal = vec3d2;
                stopped = true;
                break;
            }
            ClientWorld clientWorld3 = level = this.mc.world;
            BlockHitResult blockHitResult2 = blockHit = clientWorld3 != null ? clientWorld3.raycast(new RaycastContext(prev, pos, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, shooter)) : null;
            if (blockHit != null && blockHit.getType() != HitResult.Type.MISS) {
                Vec3d vec3d4 = blockHit.getPos();
                Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"getLocation(...)");
                finalPos = vec3d4;
                Direction direction2 = blockHit.getSide();
                Intrinsics.checkNotNullExpressionValue((Object)direction2, (String)"getDirection(...)");
                normal = ProjectileHelper.Companion.faceToNormal(direction2);
                stopped = true;
                break;
            }
            if (pos.y < -128.0) {
                finalPos = pos;
                stopped = true;
                break;
            }
            vel = this.step(prev, vel, profile);
        }
        if (!stopped) {
            finalPos = pos;
        }
        this.linesToRender.add(new LineData(points, finalPos));
        this.landingData.add(new LandingData(finalPos, ticks, isEntityProjectile, normal, living));
    }

    @Protect(value=Level.MAX)
    private final Vec3d step(Vec3d prevPos, Vec3d motion, Profile profile) {
        ClientWorld level = this.mc.world;
        boolean water = level != null && level.getFluidState(BlockPos.ofFloored((Position)((Position)prevPos))).isIn(FluidTags.WATER);
        double drag = water ? profile.getWaterDrag() : profile.getDrag();
        Vec3d vec3d2 = motion.multiply(drag).subtract(0.0, profile.getGravity(), 0.0);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"subtract(...)");
        return vec3d2;
    }

    @Protect(value=Level.MAX)
    private final void gatherLiving() {
        this.livingScratch.clear();
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        for (Object t : level.getEntities()) {
            Intrinsics.checkNotNullExpressionValue(t, (String)"next(...)");
            Entity entity = (Entity)t;
            if (!(entity instanceof LivingEntity) || !((LivingEntity)entity).isAlive()) continue;
            this.livingScratch.add((LivingEntity)entity);
        }
    }

    @Protect(value=Level.MAX)
    private final EntityHit firstEntityInPath(Vec3d from, Vec3d to, Entity shooter) {
        double best = Double.MAX_VALUE;
        LivingEntity found = null;
        Vec3d point = null;
        for (LivingEntity living : this.livingScratch) {
            if (Intrinsics.areEqual((Object)living, (Object)shooter)) continue;
            Optional<Vec3d> hit = living.getBoundingBox().expand(0.1).raycast(from, to);
            if (!hit.isPresent()) continue;
            Vec3d hitPoint = hit.get();
            double dist = from.squaredDistanceTo(hitPoint);
            if (dist < best) {
                best = dist;
                found = living;
                point = hitPoint;
            }
        }
        return found != null && point != null ? new EntityHit(found, point) : null;
    }

    private final void emitLineTube(VertexConsumer consumer, MatrixStack.Entry pose, Vec3d cameraPos, LineData line, double radius) {
        List<Vec3d> src = line.getPoints();
        int base = src.size();
        if (base == 0) {
            return;
        }
        int m = base + 1;
        if (m < 2) {
            return;
        }
        Vec3d[] p = new Vec3d[m];
        for (int k = 0; k < base; ++k) {
            p[k] = src.get(k);
        }
        p[base] = line.getFinalPos();
        Vec3d[] tangent = new Vec3d[m];
        for (int k = 0; k < m; ++k) {
            Vec3d a = (k > 0) ? p[k].subtract(p[k - 1]) : p[1].subtract(p[0]);
            Vec3d b = (k < m - 1) ? p[k + 1].subtract(p[k]) : p[k].subtract(p[k - 1]);
            Vec3d t = a.add(b);
            tangent[k] = t.lengthSquared() > 1.0E-9 ? t.normalize() : (b.lengthSquared() > 1.0E-9 ? b.normalize() : new Vec3d(0.0, 0.0, 1.0));
        }
        Vec3d frameNormal = ProjectileHelper.Companion.anyPerpendicular(tangent[0]).normalize();
        Vec3d[] prevRing = null;
        int[] prevColor = null;
        for (int k = 0; k < m; ++k) {
            Vec3d pk = p[k];
            Vec3d tk = tangent[k];
            if (k > 0) {
                Vec3d projected = frameNormal.subtract(tk.multiply(frameNormal.dotProduct(tk)));
                frameNormal = (projected.lengthSquared() > 1.0E-9)
                    ? projected.normalize()
                    : ProjectileHelper.Companion.anyPerpendicular(tk).normalize();
            }
            Vec3d binormal = tk.crossProduct(frameNormal).normalize();
            Vec3d toCamera = cameraPos.subtract(pk);
            double camLen = toCamera.length();
            Vec3d camDir = camLen > 1.0E-6 ? toCamera.multiply(1.0 / camLen) : new Vec3d(0.0, 1.0, 0.0);
            float envAlpha = ProjectileHelper.Companion.relakeAlpha(k);
            float gradientPos = (float)k / (float)(m - 1);
            int rgb = ClientAccent.gradientColor(gradientPos, 255.0f) & 0xFFFFFF;
            Vec3d[] ring = new Vec3d[10];
            int[] ringColor = new int[10];
            for (int j = 0; j < 10; ++j) {
                Vec3d offset = ProjectileHelper.Companion.ringOffset(frameNormal, binormal, j, radius);
                ring[j] = pk.add(offset);
                double facing = radius > 1.0E-9 ? MathHelper.clamp((double)(offset.dotProduct(camDir) / radius), (double)0.0, (double)1.0) : 0.0;
                float nearFactor = 1.0f - 0.5f * (float)facing;
                ringColor[j] = rgb | ProjectileHelper.Companion.alpha255(envAlpha * nearFactor) << 24;
            }
            if (prevRing != null && prevColor != null) {
                for (int j = 0; j < 10; ++j) {
                    int n = (j + 1) % 10;
                    Vec3d vec3d20 = prevRing[j];
                    Intrinsics.checkNotNull((Object)vec3d20);
                    this.vertex(consumer, pose, vec3d20, cameraPos, prevColor[j]);
                    Vec3d vec3d21 = prevRing[n];
                    Intrinsics.checkNotNull((Object)vec3d21);
                    this.vertex(consumer, pose, vec3d21, cameraPos, prevColor[n]);
                    Vec3d vec3d22 = ring[n];
                    Intrinsics.checkNotNull((Object)vec3d22);
                    this.vertex(consumer, pose, vec3d22, cameraPos, ringColor[n]);
                    Vec3d vec3d23 = ring[j];
                    Intrinsics.checkNotNull((Object)vec3d23);
                    this.vertex(consumer, pose, vec3d23, cameraPos, ringColor[j]);
                }
            }
            prevRing = ring;
            prevColor = ringColor;
        }
    }

    @Protect(value=Level.STD)
    private final void renderDisc(VertexConsumer consumer, MatrixStack.Entry pose, Vec3d cameraPos, LandingData data) {
        Vec3d center = data.getPos();
        Vec3d normal = data.getNormal();
        double radius = this.indicatorSize.getFloat();
        float spin = (float)(System.currentTimeMillis() % 4000L) / 4000.0f;
        int centerColor = ColorEngine.multAlpha(ClientAccent.gradientColor(ProjectileHelper.Companion.pingPong(spin), 255.0f), 0.55f);
        Vec3d vec3d2 = ProjectileHelper.Companion.anyPerpendicular(normal).normalize();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"normalize(...)");
        Vec3d axis1 = vec3d2;
        Vec3d vec3d3 = normal.crossProduct(axis1).normalize();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"normalize(...)");
        Vec3d axis2 = vec3d3;
        int segments = 48;
        for (int i = 0; i < segments; ++i) {
            double a1 = Math.PI * 2 * (double)i / (double)segments;
            double a2 = Math.PI * 2 * (double)(i + 1) / (double)segments;
            int c1 = ColorEngine.multAlpha(ClientAccent.gradientColor(ProjectileHelper.Companion.pingPong(spin + (float)i / (float)segments), 255.0f), 0.22f);
            int c2 = ColorEngine.multAlpha(ClientAccent.gradientColor(ProjectileHelper.Companion.pingPong(spin + (float)(i + 1) / (float)segments), 255.0f), 0.22f);
            Vec3d p1 = center.add(ProjectileHelper.Companion.ringOffset(axis1, axis2, a1, radius));
            Vec3d p2 = center.add(ProjectileHelper.Companion.ringOffset(axis1, axis2, a2, radius));
            this.vertex(consumer, pose, center, cameraPos, centerColor);
            this.vertex(consumer, pose, p1, cameraPos, c1);
            this.vertex(consumer, pose, p2, cameraPos, c2);
        }
    }

    @Protect(value=Level.STD)
    private final void emitCrossTube(VertexConsumer consumer, MatrixStack.Entry pose, Vec3d cameraPos, LandingData data, double radius) {
        Vec3d center = data.getPos();
        Vec3d normal = data.getNormal();
        int color = ColorEngine.multAlpha(ClientAccent.accentOpaque(), 0.6f);
        Vec3d vec3d2 = ProjectileHelper.Companion.anyPerpendicular(normal).normalize().multiply(0.15);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"scale(...)");
        Vec3d axis1 = vec3d2;
        Vec3d vec3d3 = normal.crossProduct(axis1).normalize().multiply(0.15);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"scale(...)");
        Vec3d axis2 = vec3d3;
        Vec3d vec3d4 = center.add(axis1);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"add(...)");
        Vec3d vec3d5 = center.subtract(axis1);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d5, (String)"subtract(...)");
        this.straightTube(consumer, pose, cameraPos, vec3d4, vec3d5, color, radius);
        Vec3d vec3d6 = center.add(axis2);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d6, (String)"add(...)");
        Vec3d vec3d7 = center.subtract(axis2);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d7, (String)"subtract(...)");
        this.straightTube(consumer, pose, cameraPos, vec3d6, vec3d7, color, radius);
    }

    private final void straightTube(VertexConsumer consumer, MatrixStack.Entry pose, Vec3d cameraPos, Vec3d a, Vec3d b, int color, double radius) {
        Vec3d vec3d2 = b.subtract(a);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"subtract(...)");
        Vec3d dir = vec3d2;
        if (dir.lengthSquared() < 1.0E-9) {
            return;
        }
        Vec3d vec3d3 = dir.normalize();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"normalize(...)");
        dir = vec3d3;
        Vec3d vec3d4 = ProjectileHelper.Companion.anyPerpendicular(dir).normalize();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"normalize(...)");
        Vec3d frameNormal = vec3d4;
        Vec3d vec3d5 = dir.crossProduct(frameNormal).normalize();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d5, (String)"normalize(...)");
        Vec3d binormal = vec3d5;
        for (int j = 0; j < 10; ++j) {
            int n = (j + 1) % 10;
            Vec3d oJ = ProjectileHelper.Companion.ringOffset(frameNormal, binormal, j, radius);
            Vec3d oN = ProjectileHelper.Companion.ringOffset(frameNormal, binormal, n, radius);
            Vec3d vec3d6 = a.add(oJ);
            Intrinsics.checkNotNullExpressionValue((Object)vec3d6, (String)"add(...)");
            this.vertex(consumer, pose, vec3d6, cameraPos, color);
            Vec3d vec3d7 = a.add(oN);
            Intrinsics.checkNotNullExpressionValue((Object)vec3d7, (String)"add(...)");
            this.vertex(consumer, pose, vec3d7, cameraPos, color);
            Vec3d vec3d8 = b.add(oN);
            Intrinsics.checkNotNullExpressionValue((Object)vec3d8, (String)"add(...)");
            this.vertex(consumer, pose, vec3d8, cameraPos, color);
            Vec3d vec3d9 = b.add(oJ);
            Intrinsics.checkNotNullExpressionValue((Object)vec3d9, (String)"add(...)");
            this.vertex(consumer, pose, vec3d9, cameraPos, color);
        }
    }

    private final void vertex(VertexConsumer consumer, MatrixStack.Entry pose, Vec3d pos, Vec3d cameraPos, int rawColor) {
        int color = ColorEngine.multAlpha(rawColor, this.visualAlpha());
        consumer.vertex(pose, (float)(pos.x - cameraPos.x), (float)(pos.y - cameraPos.y), (float)(pos.z - cameraPos.z)).color(color);
    }

    private final Vec3d directionFromRotation(float pitch, float yaw) {
        float pitchRad = pitch * ((float)Math.PI / 180);
        float yawRad = -yaw * ((float)Math.PI / 180);
        double cosPitch = Math.cos(pitchRad);
        Vec3d vec3d2 = new Vec3d(Math.sin(yawRad) * cosPitch, -Math.sin(pitchRad), Math.cos(yawRad) * cosPitch).normalize();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"normalize(...)");
        return vec3d2;
    }

    private final Profile profileFor(Entity entity) {
        if (entity instanceof EnderPearlEntity) {
            return Profile.PEARL;
        }
        if (entity instanceof TridentEntity) {
            return Profile.TRIDENT;
        }
        if (entity instanceof PersistentProjectileEntity) {
            return Profile.ARROW;
        }
        if (entity instanceof PotionEntity) {
            return Profile.POTION;
        }
        if (entity instanceof ItemEntity) {
            return Profile.ITEM;
        }
        return null;
    }

    private final HandShot handShot(PlayerEntity player, ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return null;
        }
        Item item2 = stack.getItem();
        Intrinsics.checkNotNullExpressionValue((Object)item2, (String)"getItem(...)");
        Item item = item2;
        if (item == Items.ENDER_PEARL) {
            return new HandShot(Profile.PEARL, 1.5);
        }
        if (item == Items.SPLASH_POTION || item == Items.LINGERING_POTION) {
            return new HandShot(Profile.POTION, 0.5);
        }
        if (item instanceof CrossbowItem) {
            return new HandShot(Profile.ARROW, 3.15);
        }
        if (item instanceof BowItem) {
            if (!this.isUsing(player, stack)) {
                return null;
            }
            float power = Math.max(0.05f, BowItem.getPullProgress((int)player.getItemUseTime()));
            return new HandShot(Profile.ARROW, 3.0 * (double)power);
        }
        if (item instanceof TridentItem) {
            if (!this.isUsing(player, stack)) {
                return null;
            }
            float force = MathHelper.clamp((float)((float)player.getItemUseTime() / 10.0f), (float)0.1f, (float)1.0f);
            return new HandShot(Profile.TRIDENT, 2.5 * (double)force);
        }
        return null;
    }

    @Protect(value=Level.MAX)
    private final boolean isUsing(PlayerEntity player, ItemStack stack) {
        return player.isUsingItem() && !player.getActiveItem().isEmpty() && player.getActiveItem().getItem() == stack.getItem();
    }

    @Protect(value=Level.MAX)
    private final Vector4f project(Matrix4f positionMatrix, Matrix4f projectionMatrix, Vec3d cameraPos, Vec3d pos) {
        Vector4f vec = this.projectionScratch.set((float)(pos.x - cameraPos.x), (float)(pos.y - cameraPos.y), (float)(pos.z - cameraPos.z), 1.0f);
        positionMatrix.transform(vec);
        projectionMatrix.transform(vec);
        if (vec.w <= 1.0E-4f) {
            return null;
        }
        return vec;
    }

    static {
        for (int i = 0; i < 10; ++i) {
            double a = Math.PI * 2 * (double)i / (double)10;
            ProjectileHelper.CROSS_COS[i] = (float)Math.cos(a);
            ProjectileHelper.CROSS_SIN[i] = (float)Math.sin(a);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J/\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ/\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010!R\u0014\u0010%\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010#R\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b)\u0010(R\u0014\u0010+\u001a\u00020*8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010,R\u0014\u0010-\u001a\u00020*8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010,R\u0014\u0010.\u001a\u00020*8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010,R\u0014\u0010/\u001a\u00020*8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b/\u0010,R\u0014\u00100\u001a\u00020*8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b0\u0010,\u00a8\u00061"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper.Companion;", "", "<init>", "()V", "", "x", "pingPong", "(F)F", "Lnet/minecraft/Vec3d;", "axis1", "axis2", "", "angle", "radius", "ringOffset", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;DD)Lnet/minecraft/Vec3d;", "", "side", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;ID)Lnet/minecraft/Vec3d;", "index", "relakeAlpha", "(I)F", "value", "alpha255", "(F)I", "normal", "anyPerpendicular", "(Lnet/minecraft/Vec3d;)Lnet/minecraft/Vec3d;", "Lnet/minecraft/Direction;", "face", "faceToNormal", "(Lnet/minecraft/Direction;)Lnet/minecraft/Vec3d;", "MAX_STEPS", "I", "FONT_SIZE", "F", "CROSS_SIDES", "FADE_STEPS", "", "CROSS_COS", "[F", "CROSS_SIN", "", "PEARL", "Ljava/lang/String;", "ARROW", "TRIDENT", "POTION", "ITEM", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float pingPong(float x) {
            float f = MathHelper.fractionalPart((float)x);
            return f < 0.5f ? f * 2.0f : (1.0f - f) * 2.0f;
        }

        private final Vec3d ringOffset(Vec3d axis1, Vec3d axis2, double angle, double radius) {
            double cos = Math.cos(angle);
            double sin = Math.sin(angle);
            Vec3d vec3d2 = axis1.multiply(radius * cos).add(axis2.multiply(radius * sin));
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"add(...)");
            return vec3d2;
        }

        private final Vec3d ringOffset(Vec3d axis1, Vec3d axis2, int side, double radius) {
            Vec3d vec3d2 = axis1.multiply((double)CROSS_COS[side] * radius).add(axis2.multiply((double)CROSS_SIN[side] * radius));
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"add(...)");
            return vec3d2;
        }

        private final float relakeAlpha(int index) {
            return MathHelper.clamp((float)((float)index / 40.0f), (float)0.0f, (float)1.0f);
        }

        private final int alpha255(float value) {
            return MathHelper.clamp((int)Math.round(value * 255.0f), (int)0, (int)255);
        }

        private final Vec3d anyPerpendicular(Vec3d normal) {
            Vec3d reference = Math.abs(normal.y) < 0.9 ? new Vec3d(0.0, 1.0, 0.0) : new Vec3d(1.0, 0.0, 0.0);
            Vec3d vec3d2 = reference.crossProduct(normal);
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"cross(...)");
            return vec3d2;
        }

        private final Vec3d faceToNormal(Direction face) {
            return switch (WhenMappings.$EnumSwitchMapping$0[face.ordinal()]) {
                case 1 -> new Vec3d(0.0, 1.0, 0.0);
                case 2 -> new Vec3d(0.0, -1.0, 0.0);
                case 3 -> new Vec3d(0.0, 0.0, -1.0);
                case 4 -> new Vec3d(0.0, 0.0, 1.0);
                case 5 -> new Vec3d(-1.0, 0.0, 0.0);
                case 6 -> new Vec3d(1.0, 0.0, 0.0);
                default -> throw new NoWhenBranchMatchedException();
            };
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @Metadata(mv={2, 4, 0}, k=3, xi=48)
        public static final class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] nArray = new int[Direction.values().length];
                try {
                    nArray[Direction.UP.ordinal()] = 1;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Direction.DOWN.ordinal()] = 2;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Direction.NORTH.ordinal()] = 3;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Direction.SOUTH.ordinal()] = 4;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Direction.WEST.ordinal()] = 5;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Direction.EAST.ordinal()] = 6;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                $EnumSwitchMapping$0 = nArray;
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$EntityHit;", "", "Lnet/minecraft/LivingEntity;", "entity", "Lnet/minecraft/Vec3d;", "point", "<init>", "(Lnet/minecraft/LivingEntity;Lnet/minecraft/Vec3d;)V", "Lnet/minecraft/LivingEntity;", "getEntity", "()Lnet/minecraft/LivingEntity;", "Lnet/minecraft/Vec3d;", "getPoint", "()Lnet/minecraft/Vec3d;", "rtx.kimiko:kimiko"})
    private static final class EntityHit {
        @NotNull
        private final LivingEntity entity;
        @NotNull
        private final Vec3d point;

        public EntityHit(@NotNull LivingEntity entity, @NotNull Vec3d point) {
            Intrinsics.checkNotNullParameter((Object)entity, (String)"entity");
            Intrinsics.checkNotNullParameter((Object)point, (String)"point");
            this.entity = entity;
            this.point = point;
        }

        @NotNull
        public final LivingEntity getEntity() {
            return this.entity;
        }

        @NotNull
        public final Vec3d getPoint() {
            return this.point;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$HandShot;", "", "Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$Profile;", "profile", "", "speed", "<init>", "(Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$Profile;D)V", "Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$Profile;", "getProfile", "()Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$Profile;", "D", "getSpeed", "()D", "rtx.kimiko:kimiko"})
    private static final class HandShot {
        @NotNull
        private final Profile profile;
        private final double speed;

        public HandShot(@NotNull Profile profile, double speed) {
            Intrinsics.checkNotNullParameter((Object)((Object)profile), (String)"profile");
            this.profile = profile;
            this.speed = speed;
        }

        @NotNull
        public final Profile getProfile() {
            return this.profile;
        }

        public final double getSpeed() {
            return this.speed;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\t\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0006\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\r\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$Label;", "", "", "x", "y", "", "ticks", "<init>", "(FFI)V", "F", "getX", "()F", "getY", "I", "getTicks", "()I", "rtx.kimiko:kimiko"})
    private static final class Label {
        private final float x;
        private final float y;
        private final int ticks;

        public Label(float x, float y, int ticks) {
            this.x = x;
            this.y = y;
            this.ticks = ticks;
        }

        public final float getX() {
            return this.x;
        }

        public final float getY() {
            return this.y;
        }

        public final int getTicks() {
            return this.ticks;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0002\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0006\u00a2\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0012\u001a\u0004\b\u0007\u0010\u0013R\u0017\u0010\b\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\f\u001a\u0004\b\u0014\u0010\u000eR\u0017\u0010\t\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0012\u001a\u0004\b\u0015\u0010\u0013\u00a8\u0006\u0016"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$LandingData;", "", "Lnet/minecraft/Vec3d;", "pos", "", "ticks", "", "isEntity", "normal", "living", "<init>", "(Lnet/minecraft/Vec3d;IZLnet/minecraft/Vec3d;Z)V", "Lnet/minecraft/Vec3d;", "getPos", "()Lnet/minecraft/Vec3d;", "I", "getTicks", "()I", "Z", "()Z", "getNormal", "getLiving", "rtx.kimiko:kimiko"})
    private static final class LandingData {
        @NotNull
        private final Vec3d pos;
        private final int ticks;
        private final boolean isEntity;
        @NotNull
        private final Vec3d normal;
        private final boolean living;

        public LandingData(@NotNull Vec3d pos, int ticks, boolean isEntity, @NotNull Vec3d normal, boolean living) {
            Intrinsics.checkNotNullParameter((Object)pos, (String)"pos");
            Intrinsics.checkNotNullParameter((Object)normal, (String)"normal");
            this.pos = pos;
            this.ticks = ticks;
            this.isEntity = isEntity;
            this.normal = normal;
            this.living = living;
        }

        @NotNull
        public final Vec3d getPos() {
            return this.pos;
        }

        public final int getTicks() {
            return this.ticks;
        }

        public final boolean isEntity() {
            return this.isEntity;
        }

        @NotNull
        public final Vec3d getNormal() {
            return this.normal;
        }

        public final boolean getLiving() {
            return this.living;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00038\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$LineData;", "", "", "Lnet/minecraft/Vec3d;", "points", "finalPos", "<init>", "(Ljava/util/List;Lnet/minecraft/Vec3d;)V", "Ljava/util/List;", "getPoints", "()Ljava/util/List;", "Lnet/minecraft/Vec3d;", "getFinalPos", "()Lnet/minecraft/Vec3d;", "rtx.kimiko:kimiko"})
    private static final class LineData {
        @NotNull
        private final List<Vec3d> points;
        @NotNull
        private final Vec3d finalPos;

        public LineData(@NotNull List<Vec3d> points, @NotNull Vec3d finalPos) {
            Intrinsics.checkNotNullParameter(points, (String)"points");
            Intrinsics.checkNotNullParameter((Object)finalPos, (String)"finalPos");
            this.points = points;
            this.finalPos = finalPos;
        }

        @NotNull
        public final List<Vec3d> getPoints() {
            return this.points;
        }

        @NotNull
        public final Vec3d getFinalPos() {
            return this.finalPos;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0013\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B)\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\b\u0010\tR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\r\u001a\u0004\b\u0010\u0010\u000fR\u0017\u0010\u0007\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\r\u001a\u0004\b\u0011\u0010\u000fj\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ProjectileHelper$Profile;", "", "", "setting", "", "drag", "gravity", "waterDrag", "<init>", "(Ljava/lang/String;ILjava/lang/String;DDD)V", "Ljava/lang/String;", "getSetting", "()Ljava/lang/String;", "D", "getDrag", "()D", "getGravity", "getWaterDrag", "PEARL", "ARROW", "TRIDENT", "POTION", "ITEM", "rtx.kimiko:kimiko"})
    private static enum Profile {
        PEARL("Жемчуг эндера", 0.99, 0.03, 0.8),
        ARROW("Стрелы", 0.99, 0.05, 0.6),
        TRIDENT("Трезубец", 0.99, 0.05, 0.99),
        POTION("Зелья", 0.99, 0.05, 0.8),
        ITEM("Предметы", 0.98, 0.04, 0.99);
@NotNull
        private final String setting;
        private final double drag;
        private final double gravity;
        private final double waterDrag;
        
        
        
        
        
        
        private Profile(String setting, double drag, double gravity, double waterDrag) {
            this.setting = setting;
            this.drag = drag;
            this.gravity = gravity;
            this.waterDrag = waterDrag;
        }

        @NotNull
        public final String getSetting() {
            return this.setting;
        }

        public final double getDrag() {
            return this.drag;
        }

        public final double getGravity() {
            return this.gravity;
        }

        public final double getWaterDrag() {
            return this.waterDrag;
        }

        

        

        @NotNull
        public static EnumEntries<Profile> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }
}

