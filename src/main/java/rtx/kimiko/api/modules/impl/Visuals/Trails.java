/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.gl.Framebuffer
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
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Quaternionf
 *  org.joml.Vector3f
 *  org.joml.Vector4f
 *  rtx.kimiko.utils.render.modules.post.scarglass.ScarGlassRenderer
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.gl.Framebuffer;
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
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector4f;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.impl.Visuals.trails.EnergyTrail;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.math.MathUtils;
import rtx.kimiko.utils.render.modules.post.scarglass.ScarGlassRenderer;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;
import rtx.kimiko.utils.render.render2d.ClientPalette;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"trails"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00bc\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 g2\u00020\u0001:\u0007hijklmgB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\f\u001a\u00020\u0007H\u0017b\u000e\b\b\u0012\n\b\t\u0012\u0006\b\n0\n8\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0014\u00a2\u0006\u0004\b\u000f\u0010\u0003J+\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0003b\u0002\b\u0012b\u000e\b\b\u0012\n\b\t\u0012\u0006\b\n0\n8\u0013\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ/\u0010 \u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u0007H\u0003b\u000e\b\b\u0012\n\b\t\u0012\u0006\b\n0\n8\u001f\u00a2\u0006\u0004\b \u0010\u001eJ\u000f\u0010\"\u001a\u00020!H\u0002\u00a2\u0006\u0004\b\"\u0010#J'\u0010%\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020\u0004H\u0003b\u000e\b\b\u0012\n\b\t\u0012\u0006\b\n0\n8\u001f\u00a2\u0006\u0004\b%\u0010&J\u001f\u0010'\u001a\u00020\u000eH\u0003b\u000e\b\b\u0012\n\b\t\u0012\u0006\b\n0\n8\u001f\u00a2\u0006\u0004\b'\u0010\u0003J'\u0010(\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0003b\u000e\b\b\u0012\n\b\t\u0012\u0006\b\n0\n8\u0013\u00a2\u0006\u0004\b(\u0010\u0015R\u0014\u0010*\u001a\u00020)8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010-\u001a\u00020,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u0014\u00100\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0014\u00102\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u00101R\u0014\u00103\u001a\u00020)8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b3\u0010+R\u0014\u00104\u001a\u00020,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u0010.R\u0014\u00105\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00101R\u0014\u00106\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b6\u00101R\u0014\u00107\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b7\u00101R\u0014\u00108\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00101R\u0014\u00109\u001a\u00020)8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b9\u0010+R\u0014\u0010:\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u00101R\u0014\u0010;\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b;\u00101R\u0014\u0010<\u001a\u00020)8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u0010+R\u0014\u0010>\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020)8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b@\u0010+R\u0014\u0010A\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bA\u00101R\u0014\u0010B\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u00101R\u0014\u0010C\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bC\u00101R\u0014\u0010D\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u00101R\u0014\u0010E\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010?R\u0014\u0010F\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bF\u0010?R\u0014\u0010G\u001a\u00020)8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010+R\u0014\u0010H\u001a\u00020,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u0010.R\u0014\u0010I\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u0010?R\u0014\u0010K\u001a\u00020J8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bK\u0010LR\u0014\u0010M\u001a\u00020J8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bM\u0010LR\u001a\u0010P\u001a\b\u0012\u0004\u0012\u00020O0N8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bP\u0010QR\u0018\u0010S\u001a\u0004\u0018\u00010R8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bS\u0010TR\u001a\u0010V\u001a\b\u0012\u0004\u0012\u00020U0N8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bV\u0010QR\u0014\u0010X\u001a\u00020W8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bX\u0010YR\u0018\u0010[\u001a\u00060ZR\u00020\u00008\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b[\u0010\\R\u0014\u0010^\u001a\u00020]8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b^\u0010_R\u0014\u0010a\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\ba\u0010bR\u0018\u0010c\u001a\u0004\u0018\u00010R8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010TR\u0016\u0010d\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bd\u0010eR\u0016\u0010f\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bf\u0010e\u00ca\u0001\u0010\bn\u0012\f\b\t\u0012\b\b\fJ\u0004\b\b(o\u00a8\u0006p"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/Trails;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "colorVisible", "()Z", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "fadeOutSeconds", "()F", "", "onDisable", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "STD", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "", "now", "addPoint", "(J)V", "", "index", "alpha", "getColor", "(IF)I", "MAX", "getColorBase", "Lrtx/kimiko/api/modules/impl/Visuals/trails/EnergyTrail$Options;", "energyOptions", "()Lrtx/kimiko/api/modules/impl/Visuals/trails/EnergyTrail$Options;", "tail", "energyColor", "(Z)I", "spawnBurst", "renderScarBursts3D", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "generalSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "mode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "length", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "size", "shardSeparator", "dupSpawn", "dupBlur", "dupDensity", "dupAlpha", "dupTint", "dupGlassSeparator", "dupDistort", "dupReflect", "sparkSeparator", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "dupSparks", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "energySeparator", "energyLength", "energyShrink", "energySpread", "energyRise", "energyGlow", "energyWalls", "colorSeparator", "colorMode", "useSecondColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customSecondColor", "", "Lrtx/kimiko/api/modules/impl/Visuals/Trails$TailPoint;", "points", "Ljava/util/List;", "Lnet/minecraft/Vec3d;", "lastAdded", "Lnet/minecraft/Vec3d;", "Lrtx/kimiko/api/modules/impl/Visuals/Trails$ScarBurst;", "bursts", "", "scarGlassData", "[F", "Lrtx/kimiko/api/modules/impl/Visuals/Trails$ColorCtx;", "colorCtx", "Lrtx/kimiko/api/modules/impl/Visuals/Trails$ColorCtx;", "Lorg/joml/Matrix4f;", "scarViewProj", "Lorg/joml/Matrix4f;", "Lorg/joml/Vector4f;", "scarProjScratch", "Lorg/joml/Vector4f;", "lastBurstPos", "lastBurstMs", "J", "burstSeq", "Companion", "TailPoint", "ColorCtx", "ScarBurst", "Shard", "Spark", "Ribbon", "Lrtx/kimiko/api/liteapi/Feature;", "trails", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nTrails.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Trails.kt\nrtx/kimiko/api/modules/impl/Visuals/Trails\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,1349:1\n37#2,2:1350\n*S KotlinDebug\n*F\n+ 1 Trails.kt\nrtx/kimiko/api/modules/impl/Visuals/Trails\n*L\n960#1:1350,2\n*E\n"})
public final class Trails
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting generalSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Основное"));
    @NotNull
    private final ModeSetting mode;
    @NotNull
    private final SliderSetting length;
    @NotNull
    private final SliderSetting size;
    @NotNull
    private final SeparatorSetting shardSeparator;
    @NotNull
    private final ModeSetting dupSpawn;
    @NotNull
    private final SliderSetting dupBlur;
    @NotNull
    private final SliderSetting dupDensity;
    @NotNull
    private final SliderSetting dupAlpha;
    @NotNull
    private final SliderSetting dupTint;
    @NotNull
    private final SeparatorSetting dupGlassSeparator;
    @NotNull
    private final SliderSetting dupDistort;
    @NotNull
    private final SliderSetting dupReflect;
    @NotNull
    private final SeparatorSetting sparkSeparator;
    @NotNull
    private final BooleanSetting dupSparks;
    @NotNull
    private final SeparatorSetting energySeparator;
    @NotNull
    private final SliderSetting energyLength;
    @NotNull
    private final SliderSetting energyShrink;
    @NotNull
    private final SliderSetting energySpread;
    @NotNull
    private final SliderSetting energyRise;
    @NotNull
    private final BooleanSetting energyGlow;
    @NotNull
    private final BooleanSetting energyWalls;
    @NotNull
    private final SeparatorSetting colorSeparator;
    @NotNull
    private final ModeSetting colorMode;
    @NotNull
    private final BooleanSetting useSecondColor;
    @NotNull
    private final ColorSetting customColor;
    @NotNull
    private final ColorSetting customSecondColor;
    @NotNull
    private final List<TailPoint> points;
    @Nullable
    private Vec3d lastAdded;
    @NotNull
    private final List<ScarBurst> bursts;
    @NotNull
    private final float[] scarGlassData;
    @NotNull
    private final ColorCtx colorCtx;
    @NotNull
    private final Matrix4f scarViewProj;
    @NotNull
    private final Vector4f scarProjScratch;
    @Nullable
    private Vec3d lastBurstPos;
    private long lastBurstMs;
    private long burstSeq;
    @NotNull
    private static final Identifier GLOW_TEXTURE = rtx.kimiko.api.modules.impl.Visuals.Trails.Companion.access$id(Companion, "textures/particle/glow.png");
    private static final int CLIENT_COLOR_FIRST = ColorEngine.rgba(127, 242, 255, 255);
    private static final int CLIENT_COLOR_SECOND = ColorEngine.rgba(255, 50, 150, 255);
    private static final int DARK_SECOND_COLOR = new Color(16, 16, 16, 75).getRGB();
    @NotNull
    private static final String COLOR_RAINBOW = "Радуга";
    @NotNull
    private static final String COLOR_CLIENT = "Клиент";
    @NotNull
    private static final String COLOR_CUSTOM = "Свой";
    private static final float PIXEL = 0.0625f;
    @NotNull
    private static final String MODE_NORMAL = "Обычный";
    @NotNull
    private static final String MODE_DUPLICATES = "Осколки";
    @NotNull
    private static final String MODE_ENERGY = "Энергия";
    private static final float ENERGY_DENSITY = 1.25f;
    private static final int ENERGY_SAMPLES = 20;
    private static final float ENERGY_WIDTH = 2.5f;
    private static final float ENERGY_OPACITY = 0.05f;
    private static final long ENERGY_RAINBOW_SPEED = 32L;
    private static final int ENERGY_RAINBOW_SPAN = 120;
    private static final float ENERGY_TAIL_FADE = 0.6f;
    @NotNull
    private static final String SPAWN_SHARDS = "Осколки";
    @NotNull
    private static final String SPAWN_RIBBONS = "Линии";
    @NotNull
    private static final String SPAWN_BOTH = "Оба";
    private static final float SPARK_COUNT = 5.0f;
    private static final long FADE_IN_MS = 80L;
    private static final double LENGTH_TO_MS = 8.0;
    private static final double MIN_ADD_DISTANCE_SQ = 0.01;
    private static final int MAX_POINTS = 512;
    private static final double SCAR_SPAWN_DIST = 0.2;
    private static final long SCAR_MIN_INTERVAL_MS = 12L;
    @NotNull
    private static final Shard[] EMPTY_SHARDS = new Shard[0];
    @NotNull
    private static final Ribbon[] EMPTY_RIBBONS = new Ribbon[0];
    @NotNull
    private static final Spark[] EMPTY_SPARKS = new Spark[0];
    @NotNull
    private static final double[][] BODY_CUBOIDS;
    @NotNull
    private static final float[][] SHARD_FACES;
    private static final float SHARD_TOTAL_AREA;
    private static final float TAU = (float)Math.PI * 2;
    private static final int TRIG_BITS = 13;
    private static final int TRIG_SIZE = 8192;
    private static final int TRIG_MASK = 8191;
    private static final int TRIG_QUARTER = 2048;
    private static final float TRIG_SCALE = 1303.7972f;
    @NotNull
    private static final float[] SIN_LUT;
    @NotNull
    private static final float[] BEAM_BOW;

    public Trails() {
        super("Trails", "Оставляет след за вами (свечение или энергетические осколки).", Category.VISUALS);
        String[] stringArray = new String[]{MODE_NORMAL, "Осколки", MODE_ENERGY};
        this.mode = (ModeSetting)this.register((Setting)new ModeSetting("Режим", "Режим отрисовки следа.", MODE_NORMAL, stringArray));
        this.length = (SliderSetting)this.register((Setting)new SliderSetting("Длина", "Длина хвоста (Обычный) / время жизни осколков.").range(15.0f, 25.0f).increment(1.0f).setValue(16.0f));
        this.size = (SliderSetting)this.register((Setting)new SliderSetting("Размер", "Размер спрайта хвоста.").range(0.3f, 1.0f).increment(0.05f).setValue(0.55f).visible(() -> Trails.size$lambda$0(this)));
        this.shardSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Осколки").visible(() -> Trails.shardSeparator$lambda$0(this)));
        stringArray = new String[]{"Осколки", SPAWN_RIBBONS, SPAWN_BOTH};
        this.dupSpawn = (ModeSetting)this.register((Setting)new ModeSetting("Что спавнить", "Что рождать на вспышку: осколки, линии или оба.", "Осколки", stringArray));
        this.dupBlur = (SliderSetting)this.register((Setting)new SliderSetting("Длина осколков", "Длина энергетических осколков.").range(0.0f, 8.0f).increment(0.25f).setValue(3.0f).visible(() -> Trails.dupBlur$lambda$0(this)));
        this.dupDensity = (SliderSetting)this.register((Setting)new SliderSetting("Плотность", "Как часто рождаются вспышки. Выше = плотнее поток, но дороже по FPS (главный рычаг производительности).").range(1.0f, 10.0f).increment(1.0f).setValue(6.0f).visible(() -> Trails.dupDensity$lambda$0(this)));
        this.dupAlpha = (SliderSetting)this.register((Setting)new SliderSetting("Прозрачность", "Видимость дубликатов.").range(0.1f, 1.0f).increment(0.05f).setValue(0.7f).visible(() -> Trails.dupAlpha$lambda$0(this)));
        this.dupTint = (SliderSetting)this.register((Setting)new SliderSetting("Подкрашивание", "Сила подкраски осколков цветом (0 = белые, 100 = полный цвет).").range(0.0f, 100.0f).increment(1.0f).setValue(100.0f).visible(() -> Trails.dupTint$lambda$0(this)));
        this.dupGlassSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Стекло").visible(() -> Trails.dupGlassSeparator$lambda$0(this)));
        this.dupDistort = (SliderSetting)this.register((Setting)new SliderSetting("Искривление", "Сила стеклянного искривления пространства сквозь дубликаты.").range(0.0f, 3.0f).increment(0.1f).setValue(1.0f).visible(() -> Trails.dupDistort$lambda$0(this)));
        this.dupReflect = (SliderSetting)this.register((Setting)new SliderSetting("Отражение", "Сила отражения пространства (стекло).").range(0.0f, 1.0f).increment(0.05f).setValue(0.4f).visible(() -> Trails.dupReflect$lambda$0(this)));
        this.sparkSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Спарки").visible(() -> Trails.sparkSeparator$lambda$0(this)));
        this.dupSparks = (BooleanSetting)this.register((Setting)new BooleanSetting("Спарки", "Спавнить рой искр вокруг осколков.", true).visible(() -> Trails.dupSparks$lambda$0(this)));
        this.energySeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting(MODE_ENERGY).visible(() -> Trails.energySeparator$lambda$0(this)));
        this.energyLength = (SliderSetting)this.register((Setting)new SliderSetting("Длина", "Время жизни энергии, мс.").range(875.0f, 1625.0f).increment(25.0f).setValue(1200.0f));
        this.energyShrink = (SliderSetting)this.register((Setting)new SliderSetting("Сужение", "Насколько лента сужается к хвосту.").range(0.0f, 100.0f).increment(5.0f).setValue(35.0f));
        this.energySpread = (SliderSetting)this.register((Setting)new SliderSetting("Разброс", "Как сильно энергию уносит вихрем в стороны.").range(15.0f, 30.0f).increment(0.5f).setValue(16.0f));
        this.energyRise = (SliderSetting)this.register((Setting)new SliderSetting("Подъём", "Снос энергии по вертикали: вверх или вниз.").range(-12.0f, 12.0f).increment(0.5f).setValue(-0.5f));
        this.energyGlow = (BooleanSetting)this.register((Setting)new BooleanSetting("Свечение", "Складывать ленты аддитивно: ярче и горячее на пересечениях.", true));
        this.energyWalls = (BooleanSetting)this.register((Setting)new BooleanSetting("Сквозь стены", "Не прятать энергию за блоками.", false));
        this.colorSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Цвет"));
        stringArray = new String[]{COLOR_RAINBOW, COLOR_CLIENT, COLOR_CUSTOM};
        this.colorMode = (ModeSetting)this.register((Setting)new ModeSetting("Режим цвета", "Режим цвета хвоста.", COLOR_RAINBOW, stringArray));
        this.useSecondColor = (BooleanSetting)this.register((Setting)new BooleanSetting("Второй цвет", "Использовать второй свой цвет.", false));
        this.customColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет", "Основной цвет хвоста.", new Color(255, 255, 255, 255)));
        this.customSecondColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет 2", "Второй цвет хвоста.", new Color(ColorEngine.lerpColor(-1, DARK_SECOND_COLOR, 0.7f), true)));
        this.points = new ArrayList();
        this.bursts = new ArrayList();
        this.scarGlassData = new float[1164];
        this.colorCtx = new ColorCtx();
        this.scarViewProj = new Matrix4f();
        this.scarProjScratch = new Vector4f();
        this.dupSpawn.visibleWhen(() -> Trails._init_$lambda$0(this));
        this.dupSparks.visibleWhen(() -> Trails._init_$lambda$1(this));
        this.energyLength.visible(() -> Trails._init_$lambda$2(this));
        this.energyShrink.visible(() -> Trails._init_$lambda$3(this));
        this.energySpread.visible(() -> Trails._init_$lambda$4(this));
        this.energyRise.visible(() -> Trails._init_$lambda$5(this));
        this.energyGlow.visibleWhen(() -> Trails._init_$lambda$6(this));
        this.energyWalls.visibleWhen(() -> Trails._init_$lambda$7(this));
        this.length.visible(() -> Trails._init_$lambda$8(this));
        this.colorMode.visibleWhen(() -> Trails._init_$lambda$9(this));
        this.useSecondColor.visibleWhen(() -> Trails._init_$lambda$10(this));
        this.customColor.visibleWhen(() -> Trails._init_$lambda$11(this));
        this.customSecondColor.visibleWhen(() -> Trails._init_$lambda$12(this));
    }

    private final boolean colorVisible() {
        return this.mode.is(MODE_NORMAL) || this.mode.is("Осколки") || this.mode.is(MODE_ENERGY);
    }

    @Override
    @Protect(value=Level.CROWN)
    public float fadeOutSeconds() {
        if (this.mode.is(MODE_ENERGY)) {
            return Math.min(6.0f, this.energyLength.getFloat() / 1000.0f + 0.3f);
        }
        float tail = this.length.getFloat() * 8.0f / 1000.0f;
        float scar = this.length.getFloat() * 0.05f;
        return Math.min(6.0f, Math.max(tail, scar) + 0.3f);
    }

    @Override
    protected void onDisable() {
        this.points.clear();
        this.bursts.clear();
        ScarGlassRenderer.clear();
        EnergyTrail.clear();
        this.lastAdded = null;
        this.lastBurstPos = null;
        this.lastBurstMs = 0L;
    }

    @EventHandler
    @Protect(value=Level.STD)
    private final void onWorldRender(WorldRenderEvent event) {
        if (event.isPortalPass()) {
            return;
        }
        ClientPlayerEntity player = this.mc.player;
        ClientWorld level = this.mc.world;
        if (player == null || level == null) {
            this.points.clear();
            this.lastAdded = null;
            return;
        }
        if (this.mode.is(MODE_ENERGY)) {
            if (this.mc.options.getPerspective().isFirstPerson() && this.mc.getCameraEntity() == player) {
                return;
            }
            EnergyTrail.Options options = this.energyOptions();
            if (this.isEnabled()) {
                EnergyTrail.update((LivingEntity)player, event.getPartialTicks(), options);
            }
            EnergyTrail.render(event, options);
            return;
        }
        if (this.mode.is("Осколки")) {
            if (this.mc.options.getPerspective().isFirstPerson() && this.mc.getCameraEntity() == player) {
                return;
            }
            if (this.isEnabled()) {
                this.spawnBurst();
            }
            this.renderScarBursts3D(event);
            return;
        }
        if (this.mc.options.getPerspective().isFirstPerson() && this.mc.getCameraEntity() == player) {
            return;
        }
        long now = System.currentTimeMillis();
        long lifetime = Math.max(1L, Math.round((double)this.length.getFloat() * 8.0));
        if (this.isEnabled()) {
            this.addPoint(now);
        }
        Camera camera2 = event.getCamera() == null ? this.mc.gameRenderer.getCamera() : event.getCamera();
        Intrinsics.checkNotNull((Object)camera2);
        Camera camera = camera2;
        Vec3d vec3d2 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d cameraPos = vec3d2;
        Quaternionf quaternionf = camera.getRotation();
        Intrinsics.checkNotNullExpressionValue((Object)quaternionf, (String)"rotation(...)");
        Quaternionf cameraRotation = quaternionf;
        float half = this.size.getFloat() * 0.5f;
        Vector3f right = cameraRotation.transform(new Vector3f(1.0f, 0.0f, 0.0f));
        Vector3f up = cameraRotation.transform(new Vector3f(0.0f, 1.0f, 0.0f));
        float rx = right.x() * half;
        float ry = right.y() * half;
        float rz = right.z() * half;
        float ux = up.x() * half;
        float uy = up.y() * half;
        float uz = up.z() * half;
        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate2 = this.mc.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        VertexConsumerProvider.Immediate provider = immediate2;
        RenderLayer renderLayer2 = ClientPipelines.WORLD_PARTICLES_GLOW.apply(GLOW_TEXTURE);
        Intrinsics.checkNotNullExpressionValue((Object)renderLayer2, (String)"apply(...)");
        RenderLayer renderType = renderLayer2;
        VertexConsumer vertexConsumer2 = provider.getBuffer(renderType);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer consumer = vertexConsumer2;
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        boolean drew = false;
        int index = 0;
        Iterator<TailPoint> iterator = this.points.iterator();
        while (iterator.hasNext()) {
            TailPoint point = iterator.next();
            long lived = now - point.getSpawnMs();
            if (lived > lifetime) {
                iterator.remove();
                continue;
            }
            float age = MathHelper.clamp((float)((float)lived / (float)lifetime), (float)0.0f, (float)1.0f);
            float fadeIn = Math.min((float)lived / 80.0f, 1.0f);
            float alpha = fadeIn * (1.0f - age);
            if (alpha > 0.003921569f) {
                int color = this.getColor(index, alpha);
                float cx = (float)(point.getPos().x - cameraPos.x);
                float cy = (float)(point.getPos().y - cameraPos.y);
                float cz = (float)(point.getPos().z - cameraPos.z);
                Trails.Companion.quad(consumer, pose, cx, cy, cz, rx, ry, rz, ux, uy, uz, color);
                drew = true;
            }
            ++index;
        }
        if (drew) {
            provider.draw(renderType);
        }
    }

    private final void addPoint(long now) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        Vec3d vec3d2 = MathUtils.interpolate((Entity)player).add(0.0, (double)player.getHeight() * 0.5, 0.0);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"add(...)");
        Vec3d pos = vec3d2;
        if (!(Double.isFinite(pos.x) && Double.isFinite(pos.y) && Double.isFinite(pos.z))) {
            return;
        }
        Vec3d last = this.lastAdded;
        if (last != null && last.squaredDistanceTo(pos) < 0.01) {
            return;
        }
        this.points.add(new TailPoint(pos, now));
        this.lastAdded = pos;
        if (this.points.size() > 512) {
            this.points.subList(0, this.points.size() - 512).clear();
        }
    }

    private final int getColor(int index, float alpha) {
        int c = this.getColorBase(index, alpha);
        if (this.mode.is("Осколки")) {
            c = Trails.Companion.applyTint(c, MathHelper.clamp((float)(this.dupTint.getFloat() / 100.0f), (float)0.0f, (float)1.0f));
        }
        return c;
    }

    @Protect(value=Level.MAX)
    private final int getColorBase(int index, float alpha) {
        if (this.colorMode.is(COLOR_RAINBOW)) {
            return Companion.rainbow(8, index, 1.0f, 1.0f, alpha);
        }
        int firstColor = 0;
        int secondColor = 0;
        if (this.colorMode.is(COLOR_CLIENT)) {
            int[] palette = ClientPalette.colors();
            if (palette != null && palette.length >= 2) {
                return ColorEngine.multAlpha(Companion.paletteFade(8, index, palette), alpha);
            }
            InterfaceModule iface = InterfaceModule.Companion.getInstance();
            if (iface != null) {
                firstColor = iface.clientPrimaryColorOpaque();
                secondColor = iface.usesSecondClientColor() ? iface.clientSecondaryColorOpaque() : firstColor;
            } else {
                firstColor = Companion.getClientColor();
                secondColor = ColorEngine.lerpColor(firstColor, DARK_SECOND_COLOR, 0.7f);
            }
        } else {
            firstColor = this.customColor.getColor();
            int n = secondColor = this.useSecondColor.getValue() ? this.customSecondColor.getColor() : this.customColor.getColor();
        }
        if (firstColor == secondColor) {
            return ColorEngine.multAlpha(firstColor, alpha);
        }
        return ColorEngine.multAlpha(Companion.fade(8, index, firstColor, secondColor), alpha);
    }

    private final EnergyTrail.Options energyOptions() {
        int energyHead = this.energyColor(false);
        int energyTail = this.energyColor(true);
        if ((energyTail & 0xFFFFFF) == (energyHead & 0xFFFFFF)) {
            energyTail = ColorEngine.lerpColor(energyHead, DARK_SECOND_COLOR, 0.6f);
        }
        return new EnergyTrail.Options(1.25f, (int)this.energyLength.getFloat(), 20, 0.15625f, this.energyShrink.getFloat() / 100.0f, this.energySpread.getFloat() * 0.0625f, this.energyRise.getFloat() * 0.0625f, 0.0f, 0.05f, this.energyGlow.getValue(), this.energyWalls.getValue(), energyHead, energyTail);
    }

    @Protect(value=Level.MAX)
    private final int energyColor(boolean tail) {
        if (this.colorMode.is(COLOR_RAINBOW)) {
            int spin = (int)(System.currentTimeMillis() / 32L % 360L);
            int angle = Math.floorMod(spin + (tail ? 120 : 0), 360);
            return ColorEngine.rainbow(angle, 1.0f, 1.0f);
        }
        int first = 0;
        int second = 0;
        if (this.colorMode.is(COLOR_CLIENT)) {
            int[] palette = ClientPalette.colors();
            if (palette != null && palette.length >= 2) {
                return tail ? palette[palette.length / 2] : palette[0];
            }
            InterfaceModule iface = InterfaceModule.Companion.getInstance();
            if (iface != null) {
                first = iface.clientPrimaryColorOpaque();
                second = iface.usesSecondClientColor() ? iface.clientSecondaryColorOpaque() : ColorEngine.lerpColor(first, DARK_SECOND_COLOR, 0.6f);
            } else {
                first = Companion.getClientColor();
                second = ColorEngine.lerpColor(first, DARK_SECOND_COLOR, 0.6f);
            }
        } else {
            first = this.customColor.getColor();
            second = this.useSecondColor.getValue() ? this.customSecondColor.getColor() : ColorEngine.lerpColor(first, DARK_SECOND_COLOR, 0.6f);
        }
        return tail ? second : first;
    }

    @Protect(value=Level.MAX)
    private final void spawnBurst() {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        Vec3d p = MathUtils.interpolate((Entity)player);
        if (!(Double.isFinite(p.x) && Double.isFinite(p.y) && Double.isFinite(p.z))) {
            return;
        }
        long now = System.currentTimeMillis();
        Vec3d lastP = this.lastBurstPos;
        boolean moved = lastP == null || lastP.distanceTo(p) >= 0.2;
        long interval = Math.max(12L, (long)(200.0f / Math.max(1.0f, this.dupDensity.getFloat())));
        if (moved && now - this.lastBurstMs >= interval) {
            this.lastBurstMs = now;
            double mx = 0.0;
            double my = 0.0;
            double mz = 0.0;
            if (lastP != null) {
                mx = p.x - lastP.x;
                my = p.y - lastP.y;
                mz = p.z - lastP.z;
            } else {
                Vec3d vec3d2 = player.getVelocity();
                Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getDeltaMovement(...)");
                Vec3d v = vec3d2;
                mx = v.x;
                my = v.y;
                mz = v.z;
            }
            double mh = Math.sqrt(mx * mx + mz * mz);
            float dirX = mh > 1.0E-4 ? (float)(mx / mh) : 1.0f;
            float dirZ = mh > 1.0E-4 ? (float)(mz / mh) : 0.0f;
            float dirY = (float)MathHelper.clamp((double)(my / Math.max(mh, 0.05)), (double)-0.6, (double)0.6);
            long l = this.burstSeq;
            this.burstSeq = l + 1L;
            this.bursts.add(new ScarBurst(p.x, p.y, p.z, System.currentTimeMillis(), l, dirX, dirY, dirZ, player.bodyYaw, (float)mh));
            this.lastBurstPos = p;
            if (this.bursts.size() > 64) {
                this.bursts.remove(0);
            }
        }
    }

    @Protect(value=Level.STD)
    private final void renderScarBursts3D(WorldRenderEvent event) {
        long now = System.currentTimeMillis();
        long scarLifeMs = (long)(this.length.getFloat() * 50.0f);
        for (int i = this.bursts.size() - 1; -1 < i; --i) {
            if (now - this.bursts.get(i).getSpawnMs() <= scarLifeMs) continue;
            this.bursts.remove(i);
        }
        Camera camera2 = event.getCamera();
        if (camera2 == null) {
            return;
        }
        Camera camera = camera2;
        if (this.bursts.isEmpty()) {
            return;
        }
        Vec3d vec3d2 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d cam = vec3d2;
        double camX = cam.x;
        double camY = cam.y;
        double camZ = cam.z;
        VertexConsumerProvider.Immediate immediate2 = this.mc.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        VertexConsumerProvider.Immediate provider = immediate2;
        RenderLayer renderLayer2 = ClientPipelines.WORLD_PARTICLES_GLOW.apply(GLOW_TEXTURE);
        Intrinsics.checkNotNullExpressionValue((Object)renderLayer2, (String)"apply(...)");
        RenderLayer renderType = renderLayer2;
        VertexConsumer vertexConsumer2 = provider.getBuffer(renderType);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer consumer = vertexConsumer2;
        MatrixStack.Entry entry2 = event.getStack().peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        Matrix4f matrix4f = pose.getPositionMatrix();
        Intrinsics.checkNotNullExpressionValue((Object)matrix4f, (String)"pose(...)");
        Matrix4f viewM = matrix4f;
        float rgX = viewM.m00();
        float rgY = viewM.m10();
        float rgZ = viewM.m20();
        float ugX = viewM.m01();
        float ugY = viewM.m11();
        float ugZ = viewM.m21();
        ColorCtx colors = this.colorCtx;
        colors.update(now);
        float baseAlpha = this.dupAlpha.getFloat();
        float halfLen = 0.5f + this.dupBlur.getFloat() * 0.03f;
        boolean sparksOn = this.dupSparks.getValue();
        float sparkCount = 5.0f;
        float timeSec = (float)((double)(now % 100000L) / 1000.0);
        float w = timeSec * ((float)Math.PI * 2);
        int nParts = this.dupSpawn.is("Осколки") || this.dupSpawn.is(SPAWN_BOTH) ? 1 : 0;
        int nRibbons = this.dupSpawn.is(SPAWN_RIBBONS) || this.dupSpawn.is(SPAWN_BOTH) ? 1 : 0;
        int segCount = 0;
        boolean glassOn = this.dupDistort.getFloat() > 0.001f || this.dupReflect.getFloat() > 0.001f;
        float[] glass = glassOn ? this.scarGlassData : null;
        int bn = this.bursts.size();
        for (int bi = 0; bi < bn; ++bi) {
            ScarBurst b = this.bursts.get(bi);
            float t = (float)(now - b.getSpawnMs()) / (float)scarLifeMs;
            float env = MathHelper.clamp((float)(t / 0.15f), (float)0.0f, (float)1.0f) * (1.0f - MathHelper.clamp((float)((t - 0.55f) / 0.45f), (float)0.0f, (float)1.0f));
            if (env <= 0.01f) continue;
            b.build();
            float dX = b.getDirX();
            float dZ = b.getDirZ();
            float driftPos = t * t * 0.5f;
            float originX = (float)(b.getX() - camX) - dX * driftPos;
            float originY = (float)(b.getY() - camY);
            float originZ = (float)(b.getZ() - camZ) - dZ * driftPos;
            if (nParts > 0) {
                Shard[] shards = b.ensureShards(nParts);
                for (int j = 0; j < nParts; ++j) {
                    float bcz;
                    float bcy;
                    Shard s = shards[j];
                    float bcx = originX + s.getOx();
                    float camDist = (float)Math.sqrt(bcx * bcx + (bcy = originY + s.getOy()) * bcy + (bcz = originZ + s.getOz()) * bcz);
                    float nearFade = MathHelper.clamp((float)((camDist - halfLen - 0.2f) / 1.0f), (float)0.0f, (float)1.0f);
                    if (nearFade <= 0.0f) continue;
                    float hl = halfLen * s.getSizeMul();
                    float partAlpha = MathHelper.clamp((float)(env * baseAlpha * nearFade * s.getHeroMul()), (float)0.0f, (float)1.0f);
                    int color = colors.color(s.getColorIndex(), partAlpha);
                    segCount = Trails.Companion.emitBeam(consumer, pose, s, bcx, bcy, bcz, hl, color, glass, segCount, partAlpha, env * nearFade);
                    if (!sparksOn) continue;
                    int n = Math.max(1, Math.round(sparkCount * s.getSparkNBase()));
                    Spark[] sparks = b.ensureSparks(s, n);
                    for (int si = 0; si < n; ++si) {
                        Trails.Companion.emitSpark(consumer, pose, sparks[si], colors, b.getBaseHue(), rgX, rgY, rgZ, ugX, ugY, ugZ, bcx, bcy, bcz, dX, dZ, t, timeSec, w, baseAlpha, env * nearFade);
                    }
                }
            }
            if (nRibbons <= 0) continue;
            Ribbon[] ribbons = b.ensureRibbons(nRibbons);
            float growth = MathHelper.clamp((float)(t / 0.3f), (float)0.0f, (float)1.0f);
            for (int r = 0; r < nRibbons; ++r) {
                Ribbon rib = ribbons[r];
                float roy = originY + rib.getRoyOff();
                float rlen = halfLen * rib.getLenFactor() * rib.getSpeedScale() * growth;
                float rHalfW = rib.getHalfWBase() * (0.5f + 0.5f * growth);
                float rcd = (float)Math.sqrt(originX * originX + roy * roy + originZ * originZ);
                float rFade = MathHelper.clamp((float)((rcd - rlen - 0.3f) / 1.0f), (float)0.0f, (float)1.0f);
                if (rFade <= 0.0f) continue;
                float rStrength = MathHelper.clamp((float)(env * baseAlpha * 0.5f * rFade), (float)0.0f, (float)1.0f);
                int rColor = Trails.Companion.applyTint(colors.color(rib.getColorIndex(), rStrength), 0.85f);
                segCount = Trails.Companion.emitRibbon(consumer, pose, rib, originX, roy, originZ, rlen, rHalfW, rColor, glass, segCount, rStrength, env * rFade);
            }
        }
        if (segCount > 0 && glass != null) {
            Framebuffer target = this.mc.getFramebuffer();
            Matrix4f posM = event.getPositionMatrix();
            Matrix4f projM = event.getProjectionMatrix();
            if (target != null && posM != null && projM != null) {
                float aspect = (float)target.textureWidth / (float)Math.max(1, target.textureHeight);
                Integer n = (Integer)this.mc.options.getViewDistance().getValue();
                float far = Math.max(192.0f, (float)((n != null ? n : 8) + 1) * 16.0f);
                glass[0] = segCount;
                glass[1] = aspect;
                glass[2] = (float)((double)(System.currentTimeMillis() % 100000L) / 1000.0);
                glass[3] = 0.01f + this.dupDistort.getFloat() * 0.006f;
                glass[4] = 0.6f;
                glass[5] = this.dupReflect.getFloat();
                glass[6] = 0.2f;
                glass[7] = 0.105000004f / halfLen;
                glass[8] = 1.0f;
                glass[9] = 0.05f;
                glass[10] = far;
                glass[11] = 0.0f;
                Matrix4f matrix4f2 = this.scarViewProj.set((Matrix4fc)projM).mul((Matrix4fc)posM);
                Intrinsics.checkNotNullExpressionValue((Object)matrix4f2, (String)"mul(...)");
                Companion.projectScarSegments(glass, segCount, matrix4f2, this.scarProjScratch, 0.05f, far);
                ScarGlassRenderer.apply((Framebuffer)target, (float[])glass);
            }
        }
        provider.draw(renderType);
    }

    private static final Boolean size$lambda$0(Trails this$0) {
        return this$0.mode.is(MODE_NORMAL);
    }

    private static final Boolean shardSeparator$lambda$0(Trails this$0) {
        return this$0.mode.is("Осколки");
    }

    private static final Boolean dupBlur$lambda$0(Trails this$0) {
        return this$0.mode.is("Осколки");
    }

    private static final Boolean dupDensity$lambda$0(Trails this$0) {
        return this$0.mode.is("Осколки");
    }

    private static final Boolean dupAlpha$lambda$0(Trails this$0) {
        return this$0.mode.is("Осколки");
    }

    private static final Boolean dupTint$lambda$0(Trails this$0) {
        return this$0.mode.is("Осколки");
    }

    private static final Boolean dupGlassSeparator$lambda$0(Trails this$0) {
        return this$0.mode.is("Осколки");
    }

    private static final Boolean dupDistort$lambda$0(Trails this$0) {
        return this$0.mode.is("Осколки");
    }

    private static final Boolean dupReflect$lambda$0(Trails this$0) {
        return this$0.mode.is("Осколки");
    }

    private static final Boolean sparkSeparator$lambda$0(Trails this$0) {
        return this$0.mode.is("Осколки");
    }

    private static final Boolean dupSparks$lambda$0(Trails this$0) {
        return this$0.mode.is("Осколки");
    }

    private static final Boolean energySeparator$lambda$0(Trails this$0) {
        return this$0.mode.is(MODE_ENERGY);
    }

    private static final Boolean _init_$lambda$0(Trails this$0) {
        return this$0.mode.is("Осколки");
    }

    private static final Boolean _init_$lambda$1(Trails this$0) {
        return this$0.mode.is("Осколки");
    }

    private static final Boolean _init_$lambda$2(Trails this$0) {
        return this$0.mode.is(MODE_ENERGY);
    }

    private static final Boolean _init_$lambda$3(Trails this$0) {
        return this$0.mode.is(MODE_ENERGY);
    }

    private static final Boolean _init_$lambda$4(Trails this$0) {
        return this$0.mode.is(MODE_ENERGY);
    }

    private static final Boolean _init_$lambda$5(Trails this$0) {
        return this$0.mode.is(MODE_ENERGY);
    }

    private static final Boolean _init_$lambda$6(Trails this$0) {
        return this$0.mode.is(MODE_ENERGY);
    }

    private static final Boolean _init_$lambda$7(Trails this$0) {
        return this$0.mode.is(MODE_ENERGY);
    }

    private static final Boolean _init_$lambda$8(Trails this$0) {
        return !this$0.mode.is(MODE_ENERGY);
    }

    private static final Boolean _init_$lambda$9(Trails this$0) {
        return this$0.colorVisible();
    }

    private static final Boolean _init_$lambda$10(Trails this$0) {
        return this$0.colorVisible() && this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean _init_$lambda$11(Trails this$0) {
        return this$0.colorVisible() && this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean _init_$lambda$12(Trails this$0) {
        return this$0.colorVisible() && this$0.colorMode.is(COLOR_CUSTOM) && this$0.useSecondColor.getValue();
    }

    @JvmStatic
    public static final int getClientColor() {
        return Companion.getClientColor();
    }

    @JvmStatic
    public static final int rainbow(int speed, int index, float saturation, float brightness, float alpha) {
        return Companion.rainbow(speed, index, saturation, brightness, alpha);
    }

    @JvmStatic
    public static final int fade(int speed, int index, int first, int second) {
        return Companion.fade(speed, index, first, second);
    }

    @JvmStatic
    public static final int paletteFade(int speed, int index, @NotNull int[] palette) {
        return Companion.paletteFade(speed, index, palette);
    }

    @JvmStatic
    public static final void projectScarSegments(@NotNull float[] data, int segCount, @NotNull Matrix4f viewProj, @NotNull Vector4f v, float near, float far) {
        Companion.projectScarSegments(data, segCount, viewProj, v, near, far);
    }

    public static final /* synthetic */ Spark[] access$getEMPTY_SPARKS$cp() {
        return EMPTY_SPARKS;
    }

    static {
        BODY_CUBOIDS = new double[][]{
            {-0.25, 1.22, -0.25, 0.25, 1.72, 0.25},
            {-0.25, 0.72, -0.125, 0.25, 1.22, 0.125},
            {-0.43, 0.72, -0.105, -0.25, 1.22, 0.105},
            {0.25, 0.72, -0.105, 0.43, 1.22, 0.105},
            {-0.24, 0.0, -0.105, -0.02, 0.72, 0.105},
            {0.02, 0.0, -0.105, 0.24, 0.72, 0.105}
        };
        List<float[]> faces = new ArrayList<>();
        for (double[] c : BODY_CUBOIDS) {
            float x0 = (float)c[0];
            float y0 = (float)c[1];
            float z0 = (float)c[2];
            float x1 = (float)c[3];
            float y1 = (float)c[4];
            float z1 = (float)c[5];
            float dx = x1 - x0;
            float dy = y1 - y0;
            float dz = z1 - z0;
            faces.add(Trails.Companion.shardFace(x0, y0, z0, 0.0f, dy, 0.0f, 0.0f, 0.0f, dz, -1.0f, 0.0f, 0.0f));
            faces.add(Trails.Companion.shardFace(x1, y0, z0, 0.0f, dy, 0.0f, 0.0f, 0.0f, dz, 1.0f, 0.0f, 0.0f));
            faces.add(Trails.Companion.shardFace(x0, y0, z0, dx, 0.0f, 0.0f, 0.0f, 0.0f, dz, 0.0f, -1.0f, 0.0f));
            faces.add(Trails.Companion.shardFace(x0, y1, z0, dx, 0.0f, 0.0f, 0.0f, 0.0f, dz, 0.0f, 1.0f, 0.0f));
            faces.add(Trails.Companion.shardFace(x0, y0, z0, dx, 0.0f, 0.0f, 0.0f, dy, 0.0f, 0.0f, 0.0f, -1.0f));
            faces.add(Trails.Companion.shardFace(x0, y0, z1, dx, 0.0f, 0.0f, 0.0f, dy, 0.0f, 0.0f, 0.0f, 1.0f));
        }
        SHARD_FACES = faces.toArray(new float[0][]);
        float tot = 0.0f;
        for (float[] f : SHARD_FACES) {
            tot += f[12];
        }
        SHARD_TOTAL_AREA = tot;
        SIN_LUT = new float[8192];
        for (int i = 0; i < 8192; ++i) {
            Trails.SIN_LUT[i] = (float)Math.sin((double)i * (Math.PI * 2) / 8192.0);
        }
        BEAM_BOW = new float[4];
        for (int k = 0; k < 4; ++k) {
            float u = (float)k / 3.0f;
            Trails.BEAM_BOW[k] = (float)Math.sin((double)u * Math.PI);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u000f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\r\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0015R\u0016\u0010\u0017\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0012R\u0016\u0010\u0018\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0012R\u0016\u0010\u0019\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0012R\u0016\u0010\u001a\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001d\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001e\u00a8\u0006\u001f"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/Trails$ColorCtx;", "", "<init>", "(Lrtx/kimiko/api/modules/impl/Visuals/Trails;)V", "", "now", "", "update", "(J)V", "", "index", "base", "(I)I", "", "alpha", "color", "(IF)I", "mode", "I", "", "table", "[I", "palette", "first", "second", "wrapOff", "tintFactor", "F", "", "tintEnabled", "Z", "rtx.kimiko:kimiko"})
    private final class ColorCtx {
        private int mode;
        @Nullable
        private int[] table;
        @Nullable
        private int[] palette;
        private int first;
        private int second;
        private int wrapOff;
        private float tintFactor;
        private boolean tintEnabled;

        public final void update(long now) {
            this.tintFactor = MathHelper.clamp((float)(Trails.this.dupTint.getFloat() / 100.0f), (float)0.0f, (float)1.0f);
            this.tintEnabled = this.tintFactor < 0.999f;
            this.wrapOff = (int)(now / 8L % 360L);
            if (Trails.this.colorMode.is(Trails.COLOR_RAINBOW)) {
                this.mode = 0;
                this.table = ColorEngine.rainbowTable(1.0f, 1.0f);
            } else if (Trails.this.colorMode.is(Trails.COLOR_CLIENT)) {
                int[] pal = ClientPalette.colors();
                if (pal != null && pal.length >= 2) {
                    this.mode = 1;
                    this.palette = pal;
                } else {
                    InterfaceModule iface = InterfaceModule.Companion.getInstance();
                    if (iface != null) {
                        this.first = iface.clientPrimaryColorOpaque();
                        this.second = iface.usesSecondClientColor() ? iface.clientSecondaryColorOpaque() : this.first;
                    } else {
                        this.first = Companion.getClientColor();
                        this.second = ColorEngine.lerpColor(this.first, DARK_SECOND_COLOR, 0.7f);
                    }
                    this.mode = 2;
                }
            } else {
                this.first = Trails.this.customColor.getColor();
                this.second = Trails.this.useSecondColor.getValue() ? Trails.this.customSecondColor.getColor() : this.first;
                this.mode = 2;
            }
        }

        private final int base(int index) {
            int n;
            int angle = Math.floorMod(this.wrapOff + index, 360);
            switch (this.mode) {
                case 0: {
                    Intrinsics.checkNotNull((Object)this.table);
                    n = this.table[angle];
                    break;
                }
                case 1: {
                    Intrinsics.checkNotNull((Object)this.palette);
                    int[] pal = this.palette;
                    int n2 = pal.length;
                    float f = (float)angle / 360.0f * (float)n2;
                    int i = (int)f % n2;
                    int j = (i + 1) % n2;
                    int a = pal[i] | 0xFF000000;
                    int bcol = pal[j] | 0xFF000000;
                    n = ColorEngine.lerpColor(a, bcol, f - (float)Math.floor(f)) | 0xFF000000;
                    break;
                }
                default: {
                    if (this.first == this.second) {
                        return this.first;
                    }
                    int fold = angle >= 180 ? 360 - angle : angle;
                    n = ColorEngine.lerpColor(this.first, this.second, (float)fold / 180.0f);
                }
            }
            return n;
        }

        public final int color(int index, float alpha) {
            int c = ColorEngine.multAlpha(this.base(index), alpha);
            if (this.tintEnabled) {
                c = Companion.applyTint(c, this.tintFactor);
            }
            return c;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00a6\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u0014\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\t\n\u0002\b\u001f\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u0013\n\u0002\b\u000f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003Jo\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0016Jo\u0010%\u001a\u00020$2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\"H\u0002\u00a2\u0006\u0004\b%\u0010&J\u0013\u0010(\u001a\u00020\"H\u0007b\u0002\b'\u00a2\u0006\u0004\b(\u0010)J;\u0010/\u001a\u00020\"2\u0006\u0010*\u001a\u00020\"2\u0006\u0010+\u001a\u00020\"2\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0004H\u0007b\u0002\b'\u00a2\u0006\u0004\b/\u00100J3\u00103\u001a\u00020\"2\u0006\u0010*\u001a\u00020\"2\u0006\u0010+\u001a\u00020\"2\u0006\u00101\u001a\u00020\"2\u0006\u00102\u001a\u00020\"H\u0007b\u0002\b'\u00a2\u0006\u0004\b3\u00104J+\u00107\u001a\u00020\"2\u0006\u0010*\u001a\u00020\"2\u0006\u0010+\u001a\u00020\"2\u0006\u00106\u001a\u000205H\u0007b\u0002\b'\u00a2\u0006\u0004\b7\u00108JC\u0010A\u001a\u00020$2\u0006\u00109\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\"2\u0006\u0010<\u001a\u00020;2\u0006\u0010>\u001a\u00020=2\u0006\u0010?\u001a\u00020\u00042\u0006\u0010@\u001a\u00020\u0004H\u0007b\u0002\b'\u00a2\u0006\u0004\bA\u0010BJ'\u0010D\u001a\u00020\u00042\u0006\u0010C\u001a\u00020\u00042\u0006\u0010?\u001a\u00020\u00042\u0006\u0010@\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bD\u0010EJ\u001f\u0010H\u001a\u00020\"2\u0006\u0010F\u001a\u00020\"2\u0006\u0010G\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bH\u0010IJ\u0017\u0010M\u001a\u00020L2\u0006\u0010K\u001a\u00020JH\u0002\u00a2\u0006\u0004\bM\u0010NJq\u0010V\u001a\u00020\"2\u0006\u0010O\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010Q\u001a\u00020P2\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010R\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\"2\b\u0010S\u001a\u0004\u0018\u00010\u00112\u0006\u0010:\u001a\u00020\"2\u0006\u0010T\u001a\u00020\u00042\u0006\u0010U\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bV\u0010WJ\u007f\u0010`\u001a\u00020$2\u0006\u0010O\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010X\u001a\u00020\u00042\u0006\u0010Y\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020\u00042\u0006\u0010[\u001a\u00020\u00042\u0006\u0010\\\u001a\u00020\u00042\u0006\u0010]\u001a\u00020\u00042\u0006\u0010^\u001a\u00020\u00042\u0006\u0010_\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\"H\u0002\u00a2\u0006\u0004\b`\u0010aJy\u0010i\u001a\u00020\"2\u0006\u0010O\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010c\u001a\u00020b2\u0006\u0010d\u001a\u00020\u00042\u0006\u0010e\u001a\u00020\u00042\u0006\u0010f\u001a\u00020\u00042\u0006\u0010g\u001a\u00020\u00042\u0006\u0010h\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\"2\b\u0010S\u001a\u0004\u0018\u00010\u00112\u0006\u0010:\u001a\u00020\"2\u0006\u0010T\u001a\u00020\u00042\u0006\u0010U\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bi\u0010jJ»\u0001\u0010}\u001a\u00020$2\u0006\u0010O\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010l\u001a\u00020k2\n\u0010o\u001a\u00060mR\u00020n2\u0006\u0010p\u001a\u00020\"2\u0006\u0010q\u001a\u00020\u00042\u0006\u0010r\u001a\u00020\u00042\u0006\u0010s\u001a\u00020\u00042\u0006\u0010t\u001a\u00020\u00042\u0006\u0010u\u001a\u00020\u00042\u0006\u0010v\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010w\u001a\u00020\u00042\u0006\u0010x\u001a\u00020\u00042\u0006\u0010y\u001a\u00020\u00042\u0006\u0010z\u001a\u00020\u00042\u0006\u0010{\u001a\u00020\u00042\u0006\u0010|\u001a\u00020\u00042\u0006\u0010U\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b}\u0010~J\u008a\u0001\u0010\u0089\u0001\u001a\u00020$2\u0006\u0010O\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u007f\u001a\u00020\u00042\u0007\u0010\u0080\u0001\u001a\u00020\u00042\u0007\u0010\u0081\u0001\u001a\u00020\u00042\u0007\u0010\u0082\u0001\u001a\u00020\u00042\u0007\u0010\u0083\u0001\u001a\u00020\u00042\u0007\u0010\u0084\u0001\u001a\u00020\u00042\u0007\u0010\u0085\u0001\u001a\u00020\u00042\u0007\u0010\u0086\u0001\u001a\u00020\u00042\u0007\u0010\u0087\u0001\u001a\u00020\u00042\u0006\u0010h\u001a\u00020\u00042\u0007\u0010\u0088\u0001\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\"H\u0002\u00a2\u0006\u0005\b\u0089\u0001\u0010aJ\u001c\u0010\u008c\u0001\u001a\u00020\u00042\b\u0010\u008b\u0001\u001a\u00030\u008a\u0001H\u0002\u00a2\u0006\u0006\b\u008c\u0001\u0010\u008d\u0001R\u0017\u0010\u008e\u0001\u001a\u00020L8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u008e\u0001\u0010\u008f\u0001R\u0017\u0010\u0090\u0001\u001a\u00020\"8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0090\u0001\u0010\u0091\u0001R\u0017\u0010\u0092\u0001\u001a\u00020\"8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0092\u0001\u0010\u0091\u0001R\u0017\u0010\u0093\u0001\u001a\u00020\"8\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u0093\u0001\u0010\u0091\u0001R\u0017\u0010\u0094\u0001\u001a\u00020J8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0094\u0001\u0010\u0095\u0001R\u0017\u0010\u0096\u0001\u001a\u00020J8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0096\u0001\u0010\u0095\u0001R\u0017\u0010\u0097\u0001\u001a\u00020J8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0097\u0001\u0010\u0095\u0001R\u0017\u0010\u0098\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0098\u0001\u0010\u0099\u0001R\u0017\u0010\u009a\u0001\u001a\u00020J8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u009a\u0001\u0010\u0095\u0001R\u0017\u0010\u009b\u0001\u001a\u00020J8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u009b\u0001\u0010\u0095\u0001R\u0017\u0010\u009c\u0001\u001a\u00020J8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u009c\u0001\u0010\u0095\u0001R\u0017\u0010\u009d\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u009d\u0001\u0010\u0099\u0001R\u0017\u0010\u009e\u0001\u001a\u00020\"8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u009e\u0001\u0010\u0091\u0001R\u0017\u0010\u009f\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u009f\u0001\u0010\u0099\u0001R\u0017\u0010\u00a0\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00a0\u0001\u0010\u0099\u0001R\u0018\u0010\u00a1\u0001\u001a\u00030\u008a\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00a1\u0001\u0010\u00a2\u0001R\u0017\u0010\u00a3\u0001\u001a\u00020\"8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00a3\u0001\u0010\u0091\u0001R\u0017\u0010\u00a4\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00a4\u0001\u0010\u0099\u0001R\u0017\u0010\u00a5\u0001\u001a\u00020J8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00a5\u0001\u0010\u0095\u0001R\u0017\u0010\u00a6\u0001\u001a\u00020J8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00a6\u0001\u0010\u0095\u0001R\u0017\u0010\u00a7\u0001\u001a\u00020J8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00a7\u0001\u0010\u0095\u0001R\u0017\u0010\u00a8\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00a8\u0001\u0010\u0099\u0001R\u0018\u0010\u00a9\u0001\u001a\u00030\u008a\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00a9\u0001\u0010\u00a2\u0001R\u0018\u0010«\u0001\u001a\u00030\u00aa\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b«\u0001\u0010\u00ac\u0001R\u0018\u0010\u00ad\u0001\u001a\u00030\u00aa\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00ad\u0001\u0010\u00ac\u0001R\u0017\u0010\u00ae\u0001\u001a\u00020\"8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00ae\u0001\u0010\u0091\u0001R\u0018\u0010\u00af\u0001\u001a\u00030\u00aa\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00af\u0001\u0010\u00ac\u0001R\u0018\u0010\u00b0\u0001\u001a\u00030\u008a\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00b0\u0001\u0010\u00a2\u0001R\u001e\u0010\u00b2\u0001\u001a\t\u0012\u0004\u0012\u00020P0\u00b1\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b2\u0001\u0010\u00b3\u0001R\u001e\u0010\u00b4\u0001\u001a\t\u0012\u0004\u0012\u00020b0\u00b1\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b4\u0001\u0010\u00b5\u0001R\u001e\u0010\u00b6\u0001\u001a\t\u0012\u0004\u0012\u00020k0\u00b1\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b6\u0001\u0010\u00b7\u0001R\u001f\u0010\u00b9\u0001\u001a\n\u0012\u0005\u0012\u00030\u00b8\u00010\u00b1\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00b9\u0001\u0010\u00ba\u0001R\u001e\u0010»\u0001\u001a\t\u0012\u0004\u0012\u00020\u00110\u00b1\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b»\u0001\u0010\u00bc\u0001R\u0017\u0010\u00bd\u0001\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00bd\u0001\u0010\u0099\u0001R\u0017\u0010\u00be\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00be\u0001\u0010\u0099\u0001R\u0017\u0010\u00bf\u0001\u001a\u00020\"8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00bf\u0001\u0010\u0091\u0001R\u0017\u0010\u00c0\u0001\u001a\u00020\"8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00c0\u0001\u0010\u0091\u0001R\u0017\u0010\u00c1\u0001\u001a\u00020\"8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00c1\u0001\u0010\u0091\u0001R\u0017\u0010\u00c2\u0001\u001a\u00020\"8\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00c2\u0001\u0010\u0091\u0001R\u0017\u0010\u00c3\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00c3\u0001\u0010\u0099\u0001R\u0017\u0010\u00c4\u0001\u001a\u00020\u00118\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00c4\u0001\u0010\u00c5\u0001R\u0017\u0010\u00c6\u0001\u001a\u00020\u00118\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00c6\u0001\u0010\u00c5\u0001\u00a8\u0006\u00c7\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/Trails.Companion;", "", "<init>", "()V", "", "bx", "by", "bz", "ux", "uy", "uz", "vx", "vy", "vz", "nx", "ny", "nz", "", "shardFace", "(FFFFFFFFFFFF)[F", "radians", "fsin", "(F)F", "fcos", "Lnet/minecraft/VertexConsumer;", "consumer", "Lnet/minecraft/MatrixStack$Entry;", "pose", "cx", "cy", "cz", "rx", "ry", "rz", "", "color", "", "quad", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;FFFFFFFFFI)V", "Lkotlin/jvm/JvmStatic;", "getClientColor", "()I", "speed", "index", "saturation", "brightness", "alpha", "rainbow", "(IIFFF)I", "first", "second", "fade", "(IIII)I", "", "palette", "paletteFade", "(II[I)I", "data", "segCount", "Lorg/joml/Matrix4f;", "viewProj", "Lorg/joml/Vector4f;", "v", "near", "far", "projectScarSegments", "([FILorg/joml/Matrix4f;Lorg/joml/Vector4f;FF)V", "d", "linDepth", "(FFF)F", "argb", "tint", "applyTint", "(IF)I", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "c", "Lrtx/kimiko/api/modules/impl/Visuals/Trails$Shard;", "s", "halfLen", "glassData", "strength", "env", "emitBeam", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lrtx/kimiko/api/modules/impl/Visuals/Trails$Shard;FFFFI[FIFF)I", "hx", "hy", "hz", "wx", "wy", "wz", "u0", "u1", "emitBeamUv", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;FFFFFFFFFFFI)V", "Lrtx/kimiko/api/modules/impl/Visuals/Trails$Ribbon;", "rib", "ox", "oy", "oz", "len", "halfW", "emitRibbon", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lrtx/kimiko/api/modules/impl/Visuals/Trails$Ribbon;FFFFFI[FIFF)I", "Lrtx/kimiko/api/modules/impl/Visuals/Trails$Spark;", "sp", "Lrtx/kimiko/api/modules/impl/Visuals/Trails$ColorCtx;", "Lrtx/kimiko/api/modules/impl/Visuals/Trails;", "colors", "baseHue", "rgX", "rgY", "rgZ", "ugX", "ugY", "ugZ", "dirX", "dirZ", "t", "timeSec", "w", "baseAlpha", "emitSpark", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;Lrtx/kimiko/api/modules/impl/Visuals/Trails$Spark;Lrtx/kimiko/api/modules/impl/Visuals/Trails$ColorCtx;IFFFFFFFFFFFFFFFF)V", "rX", "rY", "rZ", "uX", "uY", "uZ", "px", "py", "pz", "halfH", "sparkQuad", "", "nInput", "hashf", "(J)F", "GLOW_TEXTURE", "Lnet/minecraft/Identifier;", "CLIENT_COLOR_FIRST", "I", "CLIENT_COLOR_SECOND", "DARK_SECOND_COLOR", "COLOR_RAINBOW", "Ljava/lang/String;", "COLOR_CLIENT", "COLOR_CUSTOM", "PIXEL", "F", "MODE_NORMAL", "MODE_DUPLICATES", "MODE_ENERGY", "ENERGY_DENSITY", "ENERGY_SAMPLES", "ENERGY_WIDTH", "ENERGY_OPACITY", "ENERGY_RAINBOW_SPEED", "J", "ENERGY_RAINBOW_SPAN", "ENERGY_TAIL_FADE", "SPAWN_SHARDS", "SPAWN_RIBBONS", "SPAWN_BOTH", "SPARK_COUNT", "FADE_IN_MS", "", "LENGTH_TO_MS", "D", "MIN_ADD_DISTANCE_SQ", "MAX_POINTS", "SCAR_SPAWN_DIST", "SCAR_MIN_INTERVAL_MS", "", "EMPTY_SHARDS", "[Lrtx/kimiko/api/modules/impl/Visuals/Trails$Shard;", "EMPTY_RIBBONS", "[Lrtx/kimiko/api/modules/impl/Visuals/Trails$Ribbon;", "EMPTY_SPARKS", "[Lrtx/kimiko/api/modules/impl/Visuals/Trails$Spark;", "", "BODY_CUBOIDS", "[[D", "SHARD_FACES", "[[F", "SHARD_TOTAL_AREA", "TAU", "TRIG_BITS", "TRIG_SIZE", "TRIG_MASK", "TRIG_QUARTER", "TRIG_SCALE", "SIN_LUT", "[F", "BEAM_BOW", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float[] shardFace(float bx, float by, float bz, float ux, float uy, float uz, float vx, float vy, float vz, float nx, float ny, float nz) {
            float cx = uy * vz - uz * vy;
            float cy = uz * vx - ux * vz;
            float cz = ux * vy - uy * vx;
            float area = (float)Math.sqrt(cx * cx + cy * cy + cz * cz);
            float[] fArray = new float[]{bx, by, bz, ux, uy, uz, vx, vy, vz, nx, ny, nz, area};
            return fArray;
        }

        private final float fsin(float radians) {
            return SIN_LUT[(int)Math.floor(radians * 1303.7972f) & 0x1FFF];
        }

        private final float fcos(float radians) {
            return SIN_LUT[(int)Math.floor(radians * 1303.7972f) + 2048 & 0x1FFF];
        }

        private final void quad(VertexConsumer consumer, MatrixStack.Entry pose, float cx, float cy, float cz, float rx, float ry, float rz, float ux, float uy, float uz, int color) {
            consumer.vertex(pose, cx - rx - ux, cy - ry - uy, cz - rz - uz).texture(0.0f, 0.0f).color(color);
            consumer.vertex(pose, cx - rx + ux, cy - ry + uy, cz - rz + uz).texture(0.0f, 1.0f).color(color);
            consumer.vertex(pose, cx + rx + ux, cy + ry + uy, cz + rz + uz).texture(1.0f, 1.0f).color(color);
            consumer.vertex(pose, cx + rx - ux, cy + ry - uy, cz + rz - uz).texture(1.0f, 0.0f).color(color);
        }

        @JvmStatic
        public final int getClientColor() {
            float wave = ((float)Math.sin((double)(System.currentTimeMillis() % 1000000L) / 520.0) + 1.0f) / 2.0f;
            return ColorEngine.lerpColor(CLIENT_COLOR_FIRST, CLIENT_COLOR_SECOND, wave);
        }

        @JvmStatic
        public final int rainbow(int speed, int index, float saturation, float brightness, float alpha) {
            int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
            int rgb = ColorEngine.rainbow(angle, saturation, brightness);
            return ColorEngine.rgba(rgb >>> 16 & 0xFF, rgb >>> 8 & 0xFF, rgb & 0xFF, Math.round(MathHelper.clamp((float)alpha, (float)0.0f, (float)1.0f) * 255.0f));
        }

        @JvmStatic
        public final int fade(int speed, int index, int first, int second) {
            int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
            angle = angle >= 180 ? 360 - angle : angle;
            return ColorEngine.lerpColor(first, second, (float)angle / 180.0f);
        }

        @JvmStatic
        public final int paletteFade(int speed, int index, @NotNull int[] palette) {
            Intrinsics.checkNotNullParameter((Object)palette, (String)"palette");
            int n = palette.length;
            int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
            float f = (float)angle / 360.0f * (float)n;
            int i = (int)f % n;
            int j = (i + 1) % n;
            int a = palette[i] | 0xFF000000;
            int b = palette[j] | 0xFF000000;
            return ColorEngine.lerpColor(a, b, f - (float)Math.floor(f)) | 0xFF000000;
        }

        @JvmStatic
        public final void projectScarSegments(@NotNull float[] data, int segCount, @NotNull Matrix4f viewProj, @NotNull Vector4f v, float near, float far) {
            Intrinsics.checkNotNullParameter((Object)data, (String)"data");
            Intrinsics.checkNotNullParameter((Object)viewProj, (String)"viewProj");
            Intrinsics.checkNotNullParameter((Object)v, (String)"v");
            for (int i = 0; i < segCount; ++i) {
                int o = 12 + i * 12;
                float strength = data[o + 3];
                float env = data[o + 7];
                v.set(data[o], data[o + 1], data[o + 2], 1.0f);
                viewProj.transform(v);
                boolean aOk = v.w > 1.0E-4f;
                float aUvx = 0.0f;
                float aUvy = 0.0f;
                float aLin = 0.0f;
                if (aOk) {
                    float iw = 1.0f / v.w;
                    aUvx = v.x * iw * 0.5f + 0.5f;
                    aUvy = v.y * iw * 0.5f + 0.5f;
                    aLin = this.linDepth(v.z * iw * 0.5f + 0.5f, near, far);
                }
                v.set(data[o + 4], data[o + 5], data[o + 6], 1.0f);
                viewProj.transform(v);
                boolean bOk = v.w > 1.0E-4f;
                float bUvx = 0.0f;
                float bUvy = 0.0f;
                float bLin = 0.0f;
                if (bOk) {
                    float iw = 1.0f / v.w;
                    bUvx = v.x * iw * 0.5f + 0.5f;
                    bUvy = v.y * iw * 0.5f + 0.5f;
                    bLin = this.linDepth(v.z * iw * 0.5f + 0.5f, near, far);
                }
                if (!aOk || !bOk) {
                    data[o + 7] = 0.0f;
                    continue;
                }
                data[o] = aUvx;
                data[o + 1] = aUvy;
                data[o + 2] = aLin;
                data[o + 3] = strength;
                data[o + 4] = bUvx;
                data[o + 5] = bUvy;
                data[o + 6] = bLin;
                data[o + 7] = env;
            }
        }

        private final float linDepth(float d, float near, float far) {
            float z = d * 2.0f - 1.0f;
            return 2.0f * near * far / (far + near - z * (far - near));
        }

        private final int applyTint(int argb, float tint) {
            if (tint >= 0.999f) {
                return argb;
            }
            int a = argb >>> 24 & 0xFF;
            int r = argb >>> 16 & 0xFF;
            int g = argb >>> 8 & 0xFF;
            int b = argb & 0xFF;
            float inv = 1.0f - tint;
            r = Math.round((float)r + (float)(255 - r) * inv);
            g = Math.round((float)g + (float)(255 - g) * inv);
            b = Math.round((float)b + (float)(255 - b) * inv);
            return a << 24 | r << 16 | g << 8 | b;
        }

        private final Identifier id(String path) {
            Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
            Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
            return identifier2;
        }

        private final int emitBeam(VertexConsumer c, MatrixStack.Entry pose, Shard s, float cx, float cy, float cz, float halfLen, int color, float[] glassData, int segCount, float strength, float env) {
            int mutableSegCount = segCount;
            int n = 3;
            float e1x = s.getBeamE1x();
            float e1z = s.getBeamE1z();
            float dirX = s.getBdx();
            float dirY = s.getBdy();
            float dirZ = s.getBdz();
            float curveX = s.getCurveX();
            float curveY = s.getCurveY();
            float curveZ = s.getCurveZ();
            float wid = s.getWd();
            float prevx = 0.0f;
            float prevy = 0.0f;
            float prevz = 0.0f;
            int k = 0;
            while (true) {
                float u = (float)k / (float)n;
                float dist = (u - 0.5f) * 2.0f * halfLen;
                float bow = BEAM_BOW[k];
                float px = cx + dirX * dist + curveX * bow;
                float py = cy + dirY * dist + curveY * bow;
                float pz = cz + dirZ * dist + curveZ * bow;
                if (k > 0) {
                    float mx = (prevx + px) * 0.5f;
                    float my = (prevy + py) * 0.5f;
                    float mz = (prevz + pz) * 0.5f;
                    float shx = (px - prevx) * 0.5f;
                    float shy = (py - prevy) * 0.5f;
                    float shz = (pz - prevz) * 0.5f;
                    float u0 = (float)(k - 1) / (float)n;
                    float u1 = (float)k / (float)n;
                    this.emitBeamUv(c, pose, mx, my, mz, shx, shy, shz, e1x * wid, 0.0f, e1z * wid, u0, u1, color);
                    this.emitBeamUv(c, pose, mx, my, mz, shx, shy, shz, 0.0f, wid, 0.0f, u0, u1, color);
                    if (glassData != null && mutableSegCount < 96) {
                        int o = 12 + mutableSegCount * 12;
                        glassData[o] = prevx;
                        glassData[o + 1] = prevy;
                        glassData[o + 2] = prevz;
                        glassData[o + 3] = strength;
                        glassData[o + 4] = px;
                        glassData[o + 5] = py;
                        glassData[o + 6] = pz;
                        glassData[o + 7] = env;
                        glassData[o + 8] = BEAM_BOW[k - 1];
                        glassData[o + 9] = BEAM_BOW[k];
                        glassData[o + 10] = 0.0f;
                        glassData[o + 11] = 0.0f;
                        ++mutableSegCount;
                    }
                }
                prevx = px;
                prevy = py;
                prevz = pz;
                if (k == n) break;
                ++k;
            }
            return mutableSegCount;
        }

        private final void emitBeamUv(VertexConsumer c, MatrixStack.Entry pose, float cx, float cy, float cz, float hx, float hy, float hz, float wx, float wy, float wz, float u0, float u1, int color) {
            c.vertex(pose, cx - hx - wx, cy - hy - wy, cz - hz - wz).texture(u0, 0.0f).color(color);
            c.vertex(pose, cx - hx + wx, cy - hy + wy, cz - hz + wz).texture(u0, 1.0f).color(color);
            c.vertex(pose, cx + hx + wx, cy + hy + wy, cz + hz + wz).texture(u1, 1.0f).color(color);
            c.vertex(pose, cx + hx - wx, cy + hy - wy, cz + hz - wz).texture(u1, 0.0f).color(color);
        }

        private final int emitRibbon(VertexConsumer c, MatrixStack.Entry pose, Ribbon rib, float ox, float oy, float oz, float len, float halfW, int color, float[] glassData, int segCount, float strength, float env) {
            int mutableSegCount = segCount;
            int n = 14;
            float dirX = rib.getBdx();
            float dirY = rib.getBdy();
            float dirZ = rib.getBdz();
            float perpX = rib.getE1x();
            float perpZ = rib.getE1z();
            float[] sVals = rib.getSVals();
            float[] swayOff = rib.getSwayOff();
            float[] liftOff = rib.getLiftOff();
            float[] taper = rib.getTaper();
            float prevx = 0.0f;
            float prevy = 0.0f;
            float prevz = 0.0f;
            float prevTaper = 0.0f;
            boolean has = false;
            float gcx = 0.0f;
            float gcy = 0.0f;
            float gcz = 0.0f;
            float gcTaper = 0.0f;
            boolean gcHas = false;
            int k = 0;
            while (true) {
                float along = sVals[k] * len;
                float sway = swayOff[k];
                float lift = liftOff[k];
                float px = ox + dirX * along + perpX * sway;
                float py = oy + dirY * along + lift;
                float pz = oz + dirZ * along + perpZ * sway;
                float tp = taper[k];
                if (has) {
                    float mx = (prevx + px) * 0.5f;
                    float my = (prevy + py) * 0.5f;
                    float mz = (prevz + pz) * 0.5f;
                    float sdx = px - prevx;
                    float sdy = py - prevy;
                    float sdz = pz - prevz;
                    float sdl = (float)Math.sqrt(sdx * sdx + sdy * sdy + sdz * sdz);
                    sdl = sdl > 1.0E-5f ? sdl : 1.0f;
                    sdx /= sdl;
                    sdy /= sdl;
                    sdz /= sdl;
                    float vdl = (float)Math.sqrt(mx * mx + my * my + mz * mz);
                    vdl = vdl > 1.0E-5f ? vdl : 1.0f;
                    float vdx = mx / vdl;
                    float vdy = my / vdl;
                    float vdz = mz / vdl;
                    float wax = sdy * vdz - sdz * vdy;
                    float way = sdz * vdx - sdx * vdz;
                    float waz = sdx * vdy - sdy * vdx;
                    float wal = (float)Math.sqrt(wax * wax + way * way + waz * waz);
                    wal = wal > 1.0E-5f ? wal : 1.0f;
                    float hp = halfW * Math.max(0.05f, prevTaper) / wal;
                    float hc = halfW * Math.max(0.05f, tp) / wal;
                    float pwx = wax * hp;
                    float pwy = way * hp;
                    float pwz = waz * hp;
                    float cwx = wax * hc;
                    float cwy = way * hc;
                    float cwz = waz * hc;
                    float u0 = (float)(k - 1) / (float)n;
                    float u1 = (float)k / (float)n;
                    c.vertex(pose, prevx - pwx, prevy - pwy, prevz - pwz).texture(u0, 0.0f).color(color);
                    c.vertex(pose, prevx + pwx, prevy + pwy, prevz + pwz).texture(u0, 1.0f).color(color);
                    c.vertex(pose, px + cwx, py + cwy, pz + cwz).texture(u1, 1.0f).color(color);
                    c.vertex(pose, px - cwx, py - cwy, pz - cwz).texture(u1, 0.0f).color(color);
                }
                if (glassData != null && (k == 0 || k == n / 2 || k == n)) {
                    if (gcHas && mutableSegCount < 96) {
                        int o = 12 + mutableSegCount * 12;
                        glassData[o] = gcx;
                        glassData[o + 1] = gcy;
                        glassData[o + 2] = gcz;
                        glassData[o + 3] = strength;
                        glassData[o + 4] = px;
                        glassData[o + 5] = py;
                        glassData[o + 6] = pz;
                        glassData[o + 7] = env;
                        glassData[o + 8] = gcTaper;
                        glassData[o + 9] = tp;
                        glassData[o + 10] = 0.0f;
                        glassData[o + 11] = 0.0f;
                        ++mutableSegCount;
                    }
                    gcx = px;
                    gcy = py;
                    gcz = pz;
                    gcTaper = tp;
                    gcHas = true;
                }
                prevx = px;
                prevy = py;
                prevz = pz;
                prevTaper = tp;
                has = true;
                if (k == n) break;
                ++k;
            }
            return mutableSegCount;
        }

        private final void emitSpark(VertexConsumer c, MatrixStack.Entry pose, Spark sp, ColorCtx colors, int baseHue, float rgX, float rgY, float rgZ, float ugX, float ugY, float ugZ, float cx, float cy, float cz, float dirX, float dirZ, float t, float timeSec, float w, float baseAlpha, float env) {
            float shimmer;
            float fadeOut;
            float orbR = sp.getOrbRBase() * (0.85f + t * 0.25f);
            float ang = timeSec * sp.getSpeed() + sp.getPhase();
            float ca = this.fcos(ang);
            float sa = this.fsin(ang);
            float bx = (sp.getPx0() * ca + sp.getQx() * sa) * orbR;
            float by = (sp.getPy0() * ca + sp.getQy() * sa) * orbR;
            float bz = (sp.getPz0() * ca + sp.getQz() * sa) * orbR;
            float px = cx + bx + this.fsin(w * sp.getHf() + sp.getPhx()) * sp.getHAmp();
            float py = cy + by + this.fsin(w * sp.getHf() * 1.3f + sp.getPhy()) * sp.getHAmp();
            float pz = cz + bz + this.fcos(w * sp.getHf() * 0.85f + sp.getPhz()) * sp.getHAmp();
            float blow = t * t * 0.55f;
            px -= dirX * blow;
            pz -= dirZ * blow;
            float fadeIn = MathHelper.clamp((float)((t - sp.getBorn()) / 0.1f), (float)0.0f, (float)1.0f);
            float salpha = MathHelper.clamp((float)(env * baseAlpha * fadeIn * (fadeOut = 1.0f - MathHelper.clamp((float)((t - (sp.getLifeEnd() - 0.2f)) / 0.2f), (float)0.0f, (float)1.0f)) * (shimmer = 0.82f + 0.18f * this.fsin(w * sp.getShimFreq() + sp.getShimPhase()))), (float)0.0f, (float)1.0f);
            if (salpha <= 0.02f) {
                return;
            }
            int coreCol = colors.color(baseHue + sp.getHueOff(), salpha);
            int haloCol = ColorEngine.multAlpha(this.applyTint(coreCol, 0.55f), 0.36f);
            int ribCol = ColorEngine.multAlpha(coreCol, 0.5f);
            float size = sp.getSize();
            this.sparkQuad(c, pose, rgX, rgY, rgZ, ugX, ugY, ugZ, px, py, pz, size * 3.4f, size * 3.4f, haloCol);
            this.sparkQuad(c, pose, rgX, rgY, rgZ, ugX, ugY, ugZ, px, py, pz, size * 1.9f, size * 1.9f, haloCol);
            this.sparkQuad(c, pose, rgX, rgY, rgZ, ugX, ugY, ugZ, px, py, pz, size, size, coreCol);
            float cr = sp.getCr();
            float sr = sp.getSr();
            float r2X = rgX * cr + ugX * sr;
            float r2Y = rgY * cr + ugY * sr;
            float r2Z = rgZ * cr + ugZ * sr;
            float u2X = -rgX * sr + ugX * cr;
            float u2Y = -rgY * sr + ugY * cr;
            float u2Z = -rgZ * sr + ugZ * cr;
            this.sparkQuad(c, pose, r2X, r2Y, r2Z, u2X, u2Y, u2Z, px, py, pz, size * 1.5f, size * 0.2f, ribCol);
            this.sparkQuad(c, pose, r2X, r2Y, r2Z, u2X, u2Y, u2Z, px, py, pz, size * 0.2f, size * 1.5f, ribCol);
        }

        private final void sparkQuad(VertexConsumer c, MatrixStack.Entry pose, float rX, float rY, float rZ, float uX, float uY, float uZ, float px, float py, float pz, float halfW, float halfH, int color) {
            float axX = rX * halfW;
            float axY = rY * halfW;
            float axZ = rZ * halfW;
            float ayX = uX * halfH;
            float ayY = uY * halfH;
            float ayZ = uZ * halfH;
            c.vertex(pose, px - axX - ayX, py - axY - ayY, pz - axZ - ayZ).texture(0.0f, 0.0f).color(color);
            c.vertex(pose, px - axX + ayX, py - axY + ayY, pz - axZ + ayZ).texture(0.0f, 1.0f).color(color);
            c.vertex(pose, px + axX + ayX, py + axY + ayY, pz + axZ + ayZ).texture(1.0f, 1.0f).color(color);
            c.vertex(pose, px + axX - ayX, py + axY - ayY, pz + axZ - ayZ).texture(1.0f, 0.0f).color(color);
        }

        private final float hashf(long nInput) {
            long n = nInput;
            n = (n ^ n >>> 33) * -1060830641787976819L;
            n ^= n >>> 33;
            return (float)(n >>> 8 & 0xFFFFFFL) / 1.6777216E7f;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public static final /* synthetic */ Identifier access$id(Companion $this, String path) {
            return $this.id(path);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u001e\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b\u0010\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\u000b\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\"\u0010\u000e\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u0006\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR\"\u0010\u0011\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\u0006\u001a\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\nR\"\u0010\u0014\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0014\u0010\u0006\u001a\u0004\b\u0015\u0010\b\"\u0004\b\u0016\u0010\nR\"\u0010\u0017\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0017\u0010\u0006\u001a\u0004\b\u0018\u0010\b\"\u0004\b\u0019\u0010\nR\"\u0010\u001a\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001a\u0010\u0006\u001a\u0004\b\u001b\u0010\b\"\u0004\b\u001c\u0010\nR\"\u0010\u001d\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001d\u0010\u0006\u001a\u0004\b\u001e\u0010\b\"\u0004\b\u001f\u0010\nR\"\u0010 \u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b \u0010\u0006\u001a\u0004\b!\u0010\b\"\u0004\b\"\u0010\nR\"\u0010$\u001a\u00020#8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\"\u0010+\u001a\u00020*8\u0006@\u0006X\u0086.\u00a2\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\"\u00101\u001a\u00020*8\u0006@\u0006X\u0086.\u00a2\u0006\u0012\n\u0004\b1\u0010,\u001a\u0004\b2\u0010.\"\u0004\b3\u00100R\"\u00104\u001a\u00020*8\u0006@\u0006X\u0086.\u00a2\u0006\u0012\n\u0004\b4\u0010,\u001a\u0004\b5\u0010.\"\u0004\b6\u00100R\"\u00107\u001a\u00020*8\u0006@\u0006X\u0086.\u00a2\u0006\u0012\n\u0004\b7\u0010,\u001a\u0004\b8\u0010.\"\u0004\b9\u00100\u00a8\u0006:"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/Trails$Ribbon;", "", "<init>", "()V", "", "royOff", "F", "getRoyOff", "()F", "setRoyOff", "(F)V", "lenFactor", "getLenFactor", "setLenFactor", "speedScale", "getSpeedScale", "setSpeedScale", "halfWBase", "getHalfWBase", "setHalfWBase", "bdx", "getBdx", "setBdx", "bdy", "getBdy", "setBdy", "bdz", "getBdz", "setBdz", "e1x", "getE1x", "setE1x", "e1z", "getE1z", "setE1z", "", "colorIndex", "I", "getColorIndex", "()I", "setColorIndex", "(I)V", "", "sVals", "[F", "getSVals", "()[F", "setSVals", "([F)V", "swayOff", "getSwayOff", "setSwayOff", "liftOff", "getLiftOff", "setLiftOff", "taper", "getTaper", "setTaper", "rtx.kimiko:kimiko"})
    private static final class Ribbon {
        private float royOff;
        private float lenFactor;
        private float speedScale;
        private float halfWBase;
        private float bdx;
        private float bdy;
        private float bdz;
        private float e1x;
        private float e1z;
        private int colorIndex;
        public float[] sVals;
        public float[] swayOff;
        public float[] liftOff;
        public float[] taper;

        public final float getRoyOff() {
            return this.royOff;
        }

        public final void setRoyOff(float f) {
            this.royOff = f;
        }

        public final float getLenFactor() {
            return this.lenFactor;
        }

        public final void setLenFactor(float f) {
            this.lenFactor = f;
        }

        public final float getSpeedScale() {
            return this.speedScale;
        }

        public final void setSpeedScale(float f) {
            this.speedScale = f;
        }

        public final float getHalfWBase() {
            return this.halfWBase;
        }

        public final void setHalfWBase(float f) {
            this.halfWBase = f;
        }

        public final float getBdx() {
            return this.bdx;
        }

        public final void setBdx(float f) {
            this.bdx = f;
        }

        public final float getBdy() {
            return this.bdy;
        }

        public final void setBdy(float f) {
            this.bdy = f;
        }

        public final float getBdz() {
            return this.bdz;
        }

        public final void setBdz(float f) {
            this.bdz = f;
        }

        public final float getE1x() {
            return this.e1x;
        }

        public final void setE1x(float f) {
            this.e1x = f;
        }

        public final float getE1z() {
            return this.e1z;
        }

        public final void setE1z(float f) {
            this.e1z = f;
        }

        public final int getColorIndex() {
            return this.colorIndex;
        }

        public final void setColorIndex(int n) {
            this.colorIndex = n;
        }

        @NotNull
        public final float[] getSVals() {
            if (this.sVals != null) {
                return this.sVals;
            }
            Intrinsics.throwUninitializedPropertyAccessException((String)"sVals");
            return null;
        }

        public final void setSVals(@NotNull float[] fArray) {
            Intrinsics.checkNotNullParameter((Object)fArray, (String)"<set-?>");
            this.sVals = fArray;
        }

        @NotNull
        public final float[] getSwayOff() {
            if (this.swayOff != null) {
                return this.swayOff;
            }
            Intrinsics.throwUninitializedPropertyAccessException((String)"swayOff");
            return null;
        }

        public final void setSwayOff(@NotNull float[] fArray) {
            Intrinsics.checkNotNullParameter((Object)fArray, (String)"<set-?>");
            this.swayOff = fArray;
        }

        @NotNull
        public final float[] getLiftOff() {
            if (this.liftOff != null) {
                return this.liftOff;
            }
            Intrinsics.throwUninitializedPropertyAccessException((String)"liftOff");
            return null;
        }

        public final void setLiftOff(@NotNull float[] fArray) {
            Intrinsics.checkNotNullParameter((Object)fArray, (String)"<set-?>");
            this.liftOff = fArray;
        }

        @NotNull
        public final float[] getTaper() {
            if (this.taper != null) {
                return this.taper;
            }
            Intrinsics.throwUninitializedPropertyAccessException((String)"taper");
            return null;
        }

        public final void setTaper(@NotNull float[] fArray) {
            Intrinsics.checkNotNullParameter((Object)fArray, (String)"<set-?>");
            this.taper = fArray;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u001c\b\u0002\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\t\u00a2\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0012\u001a\u00020\u0011\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ#\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00162\u0006\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\u0004\b\u001f\u0010 J\u001f\u0010#\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b#\u0010$J\u001b\u0010&\u001a\b\u0012\u0004\u0012\u00020%0\u00162\u0006\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\u0004\b&\u0010'J\u0017\u0010)\u001a\u00020%2\u0006\u0010(\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b)\u0010*R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010+\u001a\u0004\b,\u0010-R\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010+\u001a\u0004\b.\u0010-R\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010+\u001a\u0004\b/\u0010-R\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u00100\u001a\u0004\b1\u00102R\u0017\u0010\b\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\b\u00100\u001a\u0004\b3\u00102R\u0017\u0010\n\u001a\u00020\t8\u0006\u00a2\u0006\f\n\u0004\b\n\u00104\u001a\u0004\b5\u00106R\u0017\u0010\u000b\u001a\u00020\t8\u0006\u00a2\u0006\f\n\u0004\b\u000b\u00104\u001a\u0004\b7\u00106R\u0017\u0010\f\u001a\u00020\t8\u0006\u00a2\u0006\f\n\u0004\b\f\u00104\u001a\u0004\b8\u00106R\u0017\u0010\r\u001a\u00020\t8\u0006\u00a2\u0006\f\n\u0004\b\r\u00104\u001a\u0004\b9\u00106R\u0017\u0010\u000e\u001a\u00020\t8\u0006\u00a2\u0006\f\n\u0004\b\u000e\u00104\u001a\u0004\b:\u00106R\u0016\u0010<\u001a\u00020;8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u0010=R\"\u0010>\u001a\u00020\t8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b>\u00104\u001a\u0004\b?\u00106\"\u0004\b@\u0010AR\"\u0010B\u001a\u00020\t8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bB\u00104\u001a\u0004\bC\u00106\"\u0004\bD\u0010AR\"\u0010E\u001a\u00020\t8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bE\u00104\u001a\u0004\bF\u00106\"\u0004\bG\u0010AR\"\u0010H\u001a\u00020\t8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bH\u00104\u001a\u0004\bI\u00106\"\u0004\bJ\u0010AR\"\u0010K\u001a\u00020\u00148\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bK\u0010L\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u001c\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00170\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bQ\u0010RR\u0016\u0010S\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bS\u0010LR\u001c\u0010T\u001a\b\u0012\u0004\u0012\u00020%0\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bT\u0010UR\u0016\u0010V\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010L\u00a8\u0006W"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/Trails$ScarBurst;", "", "", "x", "y", "z", "", "spawnMs", "seed", "", "dirX", "dirY", "dirZ", "bodyYaw", "speed", "<init>", "(DDDJJFFFFF)V", "", "build", "()V", "", "count", "", "Lrtx/kimiko/api/modules/impl/Visuals/Trails$Shard;", "ensureShards", "(I)[Lrtx/kimiko/api/modules/impl/Visuals/Trails$Shard;", "j", "buildShard", "(I)Lrtx/kimiko/api/modules/impl/Visuals/Trails$Shard;", "s", "Lrtx/kimiko/api/modules/impl/Visuals/Trails$Spark;", "ensureSparks", "(Lrtx/kimiko/api/modules/impl/Visuals/Trails$Shard;I)[Lrtx/kimiko/api/modules/impl/Visuals/Trails$Spark;", "sparkSeed", "i", "buildSpark", "(JI)Lrtx/kimiko/api/modules/impl/Visuals/Trails$Spark;", "Lrtx/kimiko/api/modules/impl/Visuals/Trails$Ribbon;", "ensureRibbons", "(I)[Lrtx/kimiko/api/modules/impl/Visuals/Trails$Ribbon;", "r", "buildRibbon", "(I)Lrtx/kimiko/api/modules/impl/Visuals/Trails$Ribbon;", "D", "getX", "()D", "getY", "getZ", "J", "getSpawnMs", "()J", "getSeed", "F", "getDirX", "()F", "getDirY", "getDirZ", "getBodyYaw", "getSpeed", "", "built", "Z", "cs", "getCs", "setCs", "(F)V", "sn", "getSn", "setSn", "e1x", "getE1x", "setE1x", "e1z", "getE1z", "setE1z", "baseHue", "I", "getBaseHue", "()I", "setBaseHue", "(I)V", "shards", "[Lrtx/kimiko/api/modules/impl/Visuals/Trails$Shard;", "shardCount", "ribbons", "[Lrtx/kimiko/api/modules/impl/Visuals/Trails$Ribbon;", "ribbonCount", "rtx.kimiko:kimiko"})
    private static final class ScarBurst {
        private final double x;
        private final double y;
        private final double z;
        private final long spawnMs;
        private final long seed;
        private final float dirX;
        private final float dirY;
        private final float dirZ;
        private final float bodyYaw;
        private final float speed;
        private boolean built;
        private float cs;
        private float sn;
        private float e1x;
        private float e1z;
        private int baseHue;
        @NotNull
        private Shard[] shards;
        private int shardCount;
        @NotNull
        private Ribbon[] ribbons;
        private int ribbonCount;

        public ScarBurst(double x, double y, double z, long spawnMs, long seed, float dirX, float dirY, float dirZ, float bodyYaw, float speed) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.spawnMs = spawnMs;
            this.seed = seed;
            this.dirX = dirX;
            this.dirY = dirY;
            this.dirZ = dirZ;
            this.bodyYaw = bodyYaw;
            this.speed = speed;
            this.shards = EMPTY_SHARDS;
            this.ribbons = EMPTY_RIBBONS;
        }

        public final double getX() {
            return this.x;
        }

        public final double getY() {
            return this.y;
        }

        public final double getZ() {
            return this.z;
        }

        public final long getSpawnMs() {
            return this.spawnMs;
        }

        public final long getSeed() {
            return this.seed;
        }

        public final float getDirX() {
            return this.dirX;
        }

        public final float getDirY() {
            return this.dirY;
        }

        public final float getDirZ() {
            return this.dirZ;
        }

        public final float getBodyYaw() {
            return this.bodyYaw;
        }

        public final float getSpeed() {
            return this.speed;
        }

        public final float getCs() {
            return this.cs;
        }

        public final void setCs(float f) {
            this.cs = f;
        }

        public final float getSn() {
            return this.sn;
        }

        public final void setSn(float f) {
            this.sn = f;
        }

        public final float getE1x() {
            return this.e1x;
        }

        public final void setE1x(float f) {
            this.e1x = f;
        }

        public final float getE1z() {
            return this.e1z;
        }

        public final void setE1z(float f) {
            this.e1z = f;
        }

        public final int getBaseHue() {
            return this.baseHue;
        }

        public final void setBaseHue(int n) {
            this.baseHue = n;
        }

        public final void build() {
            if (this.built) {
                return;
            }
            float yawRad = (float)Math.toRadians(-this.bodyYaw);
            this.cs = (float)Math.cos(yawRad);
            this.sn = (float)Math.sin(yawRad);
            this.e1x = -this.dirZ;
            this.e1z = this.dirX;
            this.baseHue = (int)(this.seed * 73L % 360L);
            this.built = true;
        }

        @NotNull
        public final Shard[] ensureShards(int count) {
            if (this.shardCount >= count) {
                return this.shards;
            }
            if (this.shards.length < count) {
                Shard[] grown = new Shard[count];
                System.arraycopy(this.shards, 0, grown, 0, this.shardCount);
                this.shards = grown;
            }
            for (int j = this.shardCount; j < count; ++j) {
                this.shards[j] = this.buildShard(j);
            }
            this.shardCount = count;
            return this.shards;
        }

        private final Shard buildShard(int j) {
            int fi;
            Shard s = new Shard();
            long sp = this.seed * 2246822519L + (long)j * 668265263L;
            float rArea = Companion.hashf(sp) * SHARD_TOTAL_AREA;
            float accA = 0.0f;
            for (fi = 0; fi < ((Object[])SHARD_FACES).length - 1 && !(rArea < (accA += SHARD_FACES[fi][12])); ++fi) {
            }
            float[] face = SHARD_FACES[fi];
            float su = Companion.hashf(sp + 1L);
            float sv = Companion.hashf(sp + 2L);
            float lx = face[0] + face[3] * su + face[6] * sv + face[9] * 0.03f;
            float ly = face[1] + face[4] * su + face[7] * sv + face[10] * 0.03f;
            float lz = face[2] + face[5] * su + face[8] * sv + face[11] * 0.03f;
            float rx = lx * this.cs - lz * this.sn;
            float rz = lx * this.sn + lz * this.cs;
            float fwd = rx * this.dirX + rz * this.dirZ;
            if (fwd > 0.0f) {
                rx -= 2.0f * fwd * this.dirX;
                rz -= 2.0f * fwd * this.dirZ;
            }
            s.setOx(rx);
            s.setOy(ly);
            s.setOz(rz);
            boolean hero = Companion.hashf(sp + 5L) > 0.82f;
            s.setSizeMul(hero ? 1.7f : 0.55f + Companion.hashf(sp + 6L) * 0.5f);
            s.setWd(0.07f * (hero ? 1.45f : 0.65f + Companion.hashf(sp + 7L) * 0.4f));
            s.setHeroMul(hero ? 1.35f : 1.0f);
            s.setColorIndex(this.baseHue + (int)(((long)fi * 23L + (long)j * 40L) % 50L) - 25);
            float cE1 = (Companion.hashf(this.seed * 17L + (long)j * 23L) - 0.5f) * 0.08f;
            float cUp = (Companion.hashf(this.seed * 19L + (long)j * 29L) - 0.5f) * 0.08f;
            s.setCurveX(this.e1x * cE1);
            s.setCurveY(cUp);
            s.setCurveZ(this.e1z * cE1);
            float tiltY = MathHelper.clamp((float)(this.dirY + (Companion.hashf(sp + 8L) - 0.5f) * 0.7f), (float)-0.9f, (float)0.9f);
            float dl = (float)Math.sqrt(this.dirX * this.dirX + tiltY * tiltY + this.dirZ * this.dirZ);
            s.setBdx(this.dirX / dl);
            s.setBdy(tiltY / dl);
            s.setBdz(this.dirZ / dl);
            float hl2 = (float)Math.sqrt(s.getBdx() * s.getBdx() + s.getBdz() * s.getBdz());
            s.setBeamE1x(hl2 > 1.0E-4f ? -s.getBdz() / hl2 : 1.0f);
            s.setBeamE1z(hl2 > 1.0E-4f ? s.getBdx() / hl2 : 0.0f);
            s.setSparkSeed(this.seed + (long)j * 7919L);
            s.setSparkNBase(0.85f + Companion.hashf(s.getSparkSeed() * 747L) * 0.3f);
            return s;
        }

        @NotNull
        public final Spark[] ensureSparks(@NotNull Shard s, int count) {
            Intrinsics.checkNotNullParameter((Object)s, (String)"s");
            if (s.getSparkCount() >= count) {
                return s.getSparks();
            }
            if (s.getSparks().length < count) {
                Spark[] grown = new Spark[count];
                System.arraycopy(s.getSparks(), 0, grown, 0, s.getSparkCount());
                s.setSparks(grown);
            }
            for (int i = s.getSparkCount(); i < count; ++i) {
                s.getSparks()[i] = this.buildSpark(s.getSparkSeed(), i);
            }
            s.setSparkCount(count);
            return s.getSparks();
        }

        private final Spark buildSpark(long sparkSeed, int i) {
            Spark sp = new Spark();
            long sd = sparkSeed * 2654435761L + (long)i * 40503L;
            float a1 = Companion.hashf(sd) * ((float)Math.PI * 2);
            float cosT = Companion.hashf(sd + 1L) * 2.0f - 1.0f;
            float sinT = (float)Math.sqrt(Math.max(0.0f, 1.0f - cosT * cosT));
            float nx = (float)Math.cos(a1) * sinT;
            float ny = cosT;
            float nz = (float)Math.sin(a1) * sinT;
            float rx = Math.abs(ny) < 0.9f ? 0.0f : 1.0f;
            float ry = Math.abs(ny) < 0.9f ? 1.0f : 0.0f;
            float px0 = -nz * ry;
            float py0 = nz * rx;
            float pz0 = nx * ry - ny * rx;
            float pl = (float)Math.sqrt(px0 * px0 + py0 * py0 + pz0 * pz0);
            pl = pl > 1.0E-4f ? pl : 1.0f;
            float qx = ny * (pz0 /= pl) - nz * (py0 /= pl);
            float qy = nz * (px0 /= pl) - nx * pz0;
            float qz = nx * py0 - ny * px0;
            sp.setPx0(px0);
            sp.setPy0(py0);
            sp.setPz0(pz0);
            sp.setQx(qx);
            sp.setQy(qy);
            sp.setQz(qz);
            sp.setOrbRBase(0.2f + Companion.hashf(sd + 2L) * 0.1f);
            sp.setSpeed((0.5f + Companion.hashf(sd + 3L) * 1.8f) * (Companion.hashf(sd + 4L) > 0.5f ? 1.0f : -1.0f));
            sp.setPhase(Companion.hashf(sd + 5L) * ((float)Math.PI * 2));
            sp.setHf(0.8f + Companion.hashf(sd + 6L) * 1.0f);
            sp.setHAmp(0.05f + Companion.hashf(sd + 7L) * 0.05f);
            sp.setPhx(Companion.hashf(sd + 8L) * ((float)Math.PI * 2));
            sp.setPhy(Companion.hashf(sd + 9L) * ((float)Math.PI * 2));
            sp.setPhz(Companion.hashf(sd + 10L) * ((float)Math.PI * 2));
            sp.setBorn(Companion.hashf(sd + 17L) * 0.12f);
            sp.setLifeEnd(0.55f + Companion.hashf(sd + 18L) * 0.45f);
            sp.setShimFreq(1.2f + Companion.hashf(sd + 11L) * 2.0f);
            sp.setShimPhase(sp.getPhase() * 2.0f);
            sp.setSize(0.014f + Companion.hashf(sd + 12L) * 0.02f);
            sp.setHueOff((int)(Companion.hashf(sd + 16L) * 40.0f) - 20);
            float rot = Companion.hashf(sd + 15L) * ((float)Math.PI * 2);
            sp.setCr((float)Math.cos(rot));
            sp.setSr((float)Math.sin(rot));
            return sp;
        }

        @NotNull
        public final Ribbon[] ensureRibbons(int count) {
            if (this.ribbonCount >= count) {
                return this.ribbons;
            }
            if (this.ribbons.length < count) {
                Ribbon[] grown = new Ribbon[count];
                System.arraycopy(this.ribbons, 0, grown, 0, this.ribbonCount);
                this.ribbons = grown;
            }
            for (int r = this.ribbonCount; r < count; ++r) {
                this.ribbons[r] = this.buildRibbon(r);
            }
            this.ribbonCount = count;
            return this.ribbons;
        }

        private final Ribbon buildRibbon(int r) {
            Ribbon rib = new Ribbon();
            long rsd = this.seed * 99991L + (long)r * 40507L;
            rib.setRoyOff(0.35f + Companion.hashf(rsd) * 0.9f);
            rib.setLenFactor(1.7f + Companion.hashf(rsd + 1L) * 0.9f);
            rib.setSpeedScale(MathHelper.clamp((float)(this.speed / 0.35f), (float)0.45f, (float)1.3f));
            rib.setHalfWBase(0.04f + Companion.hashf(rsd + 2L) * 0.035f);
            float swayAmp = (0.15f + Companion.hashf(rsd + 3L) * 0.25f) * (r == 0 ? 1.0f : -1.0f);
            float swayPhase = Companion.hashf(rsd + 4L) * ((float)Math.PI * 2);
            float liftAmp = (Companion.hashf(rsd + 5L) - 0.3f) * 0.5f;
            rib.setColorIndex(this.baseHue + r * 20);
            float rTiltY = MathHelper.clamp((float)(this.dirY + (Companion.hashf(rsd + 6L) - 0.5f) * 0.5f), (float)-0.8f, (float)0.8f);
            float rdl = (float)Math.sqrt(this.dirX * this.dirX + rTiltY * rTiltY + this.dirZ * this.dirZ);
            rib.setBdx(-this.dirX / rdl);
            rib.setBdy(-rTiltY / rdl);
            rib.setBdz(-this.dirZ / rdl);
            rib.setE1x(this.e1x);
            rib.setE1z(this.e1z);
            int n = 14;
            rib.setSVals(new float[n + 1]);
            rib.setSwayOff(new float[n + 1]);
            rib.setLiftOff(new float[n + 1]);
            rib.setTaper(new float[n + 1]);
            int k = 0;
            while (true) {
                float s;
                rib.getSVals()[k] = s = (float)k / (float)n;
                rib.getSwayOff()[k] = (float)Math.sin((double)s * Math.PI * 1.6 + (double)swayPhase) * swayAmp;
                float taper = (float)Math.sin((double)s * Math.PI);
                rib.getLiftOff()[k] = taper * liftAmp;
                rib.getTaper()[k] = taper;
                if (k == n) break;
                ++k;
            }
            return rib;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b-\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\u000b\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\"\u0010\u000e\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u0006\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR\"\u0010\u0011\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\u0006\u001a\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\nR\"\u0010\u0014\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0014\u0010\u0006\u001a\u0004\b\u0015\u0010\b\"\u0004\b\u0016\u0010\nR\"\u0010\u0017\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0017\u0010\u0006\u001a\u0004\b\u0018\u0010\b\"\u0004\b\u0019\u0010\nR\"\u0010\u001a\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001a\u0010\u0006\u001a\u0004\b\u001b\u0010\b\"\u0004\b\u001c\u0010\nR\"\u0010\u001d\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001d\u0010\u0006\u001a\u0004\b\u001e\u0010\b\"\u0004\b\u001f\u0010\nR\"\u0010 \u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b \u0010\u0006\u001a\u0004\b!\u0010\b\"\u0004\b\"\u0010\nR\"\u0010#\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b#\u0010\u0006\u001a\u0004\b$\u0010\b\"\u0004\b%\u0010\nR\"\u0010&\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b&\u0010\u0006\u001a\u0004\b'\u0010\b\"\u0004\b(\u0010\nR\"\u0010)\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b)\u0010\u0006\u001a\u0004\b*\u0010\b\"\u0004\b+\u0010\nR\"\u0010,\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b,\u0010\u0006\u001a\u0004\b-\u0010\b\"\u0004\b.\u0010\nR\"\u0010/\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b/\u0010\u0006\u001a\u0004\b0\u0010\b\"\u0004\b1\u0010\nR\"\u00103\u001a\u0002028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b3\u00104\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\"\u0010:\u001a\u0002098\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\"\u0010@\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b@\u0010\u0006\u001a\u0004\bA\u0010\b\"\u0004\bB\u0010\nR(\u0010E\u001a\b\u0012\u0004\u0012\u00020D0C8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bE\u0010F\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\"\u0010K\u001a\u0002028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bK\u00104\u001a\u0004\bL\u00106\"\u0004\bM\u00108\u00a8\u0006N"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/Trails$Shard;", "", "<init>", "()V", "", "ox", "F", "getOx", "()F", "setOx", "(F)V", "oy", "getOy", "setOy", "oz", "getOz", "setOz", "bdx", "getBdx", "setBdx", "bdy", "getBdy", "setBdy", "bdz", "getBdz", "setBdz", "beamE1x", "getBeamE1x", "setBeamE1x", "beamE1z", "getBeamE1z", "setBeamE1z", "curveX", "getCurveX", "setCurveX", "curveY", "getCurveY", "setCurveY", "curveZ", "getCurveZ", "setCurveZ", "wd", "getWd", "setWd", "sizeMul", "getSizeMul", "setSizeMul", "heroMul", "getHeroMul", "setHeroMul", "", "colorIndex", "I", "getColorIndex", "()I", "setColorIndex", "(I)V", "", "sparkSeed", "J", "getSparkSeed", "()J", "setSparkSeed", "(J)V", "sparkNBase", "getSparkNBase", "setSparkNBase", "", "Lrtx/kimiko/api/modules/impl/Visuals/Trails$Spark;", "sparks", "[Lrtx/kimiko/api/modules/impl/Visuals/Trails$Spark;", "getSparks", "()[Lrtx/kimiko/api/modules/impl/Visuals/Trails$Spark;", "setSparks", "([Lrtx/kimiko/api/modules/impl/Visuals/Trails$Spark;)V", "sparkCount", "getSparkCount", "setSparkCount", "rtx.kimiko:kimiko"})
    private static final class Shard {
        private float ox;
        private float oy;
        private float oz;
        private float bdx;
        private float bdy;
        private float bdz;
        private float beamE1x;
        private float beamE1z;
        private float curveX;
        private float curveY;
        private float curveZ;
        private float wd;
        private float sizeMul;
        private float heroMul;
        private int colorIndex;
        private long sparkSeed;
        private float sparkNBase;
        @NotNull
        private Spark[] sparks = Trails.access$getEMPTY_SPARKS$cp();
        private int sparkCount;

        public final float getOx() {
            return this.ox;
        }

        public final void setOx(float f) {
            this.ox = f;
        }

        public final float getOy() {
            return this.oy;
        }

        public final void setOy(float f) {
            this.oy = f;
        }

        public final float getOz() {
            return this.oz;
        }

        public final void setOz(float f) {
            this.oz = f;
        }

        public final float getBdx() {
            return this.bdx;
        }

        public final void setBdx(float f) {
            this.bdx = f;
        }

        public final float getBdy() {
            return this.bdy;
        }

        public final void setBdy(float f) {
            this.bdy = f;
        }

        public final float getBdz() {
            return this.bdz;
        }

        public final void setBdz(float f) {
            this.bdz = f;
        }

        public final float getBeamE1x() {
            return this.beamE1x;
        }

        public final void setBeamE1x(float f) {
            this.beamE1x = f;
        }

        public final float getBeamE1z() {
            return this.beamE1z;
        }

        public final void setBeamE1z(float f) {
            this.beamE1z = f;
        }

        public final float getCurveX() {
            return this.curveX;
        }

        public final void setCurveX(float f) {
            this.curveX = f;
        }

        public final float getCurveY() {
            return this.curveY;
        }

        public final void setCurveY(float f) {
            this.curveY = f;
        }

        public final float getCurveZ() {
            return this.curveZ;
        }

        public final void setCurveZ(float f) {
            this.curveZ = f;
        }

        public final float getWd() {
            return this.wd;
        }

        public final void setWd(float f) {
            this.wd = f;
        }

        public final float getSizeMul() {
            return this.sizeMul;
        }

        public final void setSizeMul(float f) {
            this.sizeMul = f;
        }

        public final float getHeroMul() {
            return this.heroMul;
        }

        public final void setHeroMul(float f) {
            this.heroMul = f;
        }

        public final int getColorIndex() {
            return this.colorIndex;
        }

        public final void setColorIndex(int n) {
            this.colorIndex = n;
        }

        public final long getSparkSeed() {
            return this.sparkSeed;
        }

        public final void setSparkSeed(long l) {
            this.sparkSeed = l;
        }

        public final float getSparkNBase() {
            return this.sparkNBase;
        }

        public final void setSparkNBase(float f) {
            this.sparkNBase = f;
        }

        @NotNull
        public final Spark[] getSparks() {
            return this.sparks;
        }

        public final void setSparks(@NotNull Spark[] sparkArray) {
            Intrinsics.checkNotNullParameter((Object)sparkArray, (String)"<set-?>");
            this.sparks = sparkArray;
        }

        public final int getSparkCount() {
            return this.sparkCount;
        }

        public final void setSparkCount(int n) {
            this.sparkCount = n;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b<\n\u0002\u0010\b\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\u000b\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\"\u0010\u000e\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u0006\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR\"\u0010\u0011\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\u0006\u001a\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\nR\"\u0010\u0014\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0014\u0010\u0006\u001a\u0004\b\u0015\u0010\b\"\u0004\b\u0016\u0010\nR\"\u0010\u0017\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0017\u0010\u0006\u001a\u0004\b\u0018\u0010\b\"\u0004\b\u0019\u0010\nR\"\u0010\u001a\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001a\u0010\u0006\u001a\u0004\b\u001b\u0010\b\"\u0004\b\u001c\u0010\nR\"\u0010\u001d\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001d\u0010\u0006\u001a\u0004\b\u001e\u0010\b\"\u0004\b\u001f\u0010\nR\"\u0010 \u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b \u0010\u0006\u001a\u0004\b!\u0010\b\"\u0004\b\"\u0010\nR\"\u0010#\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b#\u0010\u0006\u001a\u0004\b$\u0010\b\"\u0004\b%\u0010\nR\"\u0010&\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b&\u0010\u0006\u001a\u0004\b'\u0010\b\"\u0004\b(\u0010\nR\"\u0010)\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b)\u0010\u0006\u001a\u0004\b*\u0010\b\"\u0004\b+\u0010\nR\"\u0010,\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b,\u0010\u0006\u001a\u0004\b-\u0010\b\"\u0004\b.\u0010\nR\"\u0010/\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b/\u0010\u0006\u001a\u0004\b0\u0010\b\"\u0004\b1\u0010\nR\"\u00102\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b2\u0010\u0006\u001a\u0004\b3\u0010\b\"\u0004\b4\u0010\nR\"\u00105\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b5\u0010\u0006\u001a\u0004\b6\u0010\b\"\u0004\b7\u0010\nR\"\u00108\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b8\u0010\u0006\u001a\u0004\b9\u0010\b\"\u0004\b:\u0010\nR\"\u0010;\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b;\u0010\u0006\u001a\u0004\b<\u0010\b\"\u0004\b=\u0010\nR\"\u0010>\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b>\u0010\u0006\u001a\u0004\b?\u0010\b\"\u0004\b@\u0010\nR\"\u0010B\u001a\u00020A8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bB\u0010C\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR\"\u0010H\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bH\u0010\u0006\u001a\u0004\bI\u0010\b\"\u0004\bJ\u0010\nR\"\u0010K\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bK\u0010\u0006\u001a\u0004\bL\u0010\b\"\u0004\bM\u0010\n\u00a8\u0006N"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/Trails$Spark;", "", "<init>", "()V", "", "px0", "F", "getPx0", "()F", "setPx0", "(F)V", "py0", "getPy0", "setPy0", "pz0", "getPz0", "setPz0", "qx", "getQx", "setQx", "qy", "getQy", "setQy", "qz", "getQz", "setQz", "orbRBase", "getOrbRBase", "setOrbRBase", "speed", "getSpeed", "setSpeed", "phase", "getPhase", "setPhase", "hf", "getHf", "setHf", "hAmp", "getHAmp", "setHAmp", "phx", "getPhx", "setPhx", "phy", "getPhy", "setPhy", "phz", "getPhz", "setPhz", "born", "getBorn", "setBorn", "lifeEnd", "getLifeEnd", "setLifeEnd", "shimFreq", "getShimFreq", "setShimFreq", "shimPhase", "getShimPhase", "setShimPhase", "size", "getSize", "setSize", "", "hueOff", "I", "getHueOff", "()I", "setHueOff", "(I)V", "cr", "getCr", "setCr", "sr", "getSr", "setSr", "rtx.kimiko:kimiko"})
    private static final class Spark {
        private float px0;
        private float py0;
        private float pz0;
        private float qx;
        private float qy;
        private float qz;
        private float orbRBase;
        private float speed;
        private float phase;
        private float hf;
        private float hAmp;
        private float phx;
        private float phy;
        private float phz;
        private float born;
        private float lifeEnd;
        private float shimFreq;
        private float shimPhase;
        private float size;
        private int hueOff;
        private float cr;
        private float sr;

        public final float getPx0() {
            return this.px0;
        }

        public final void setPx0(float f) {
            this.px0 = f;
        }

        public final float getPy0() {
            return this.py0;
        }

        public final void setPy0(float f) {
            this.py0 = f;
        }

        public final float getPz0() {
            return this.pz0;
        }

        public final void setPz0(float f) {
            this.pz0 = f;
        }

        public final float getQx() {
            return this.qx;
        }

        public final void setQx(float f) {
            this.qx = f;
        }

        public final float getQy() {
            return this.qy;
        }

        public final void setQy(float f) {
            this.qy = f;
        }

        public final float getQz() {
            return this.qz;
        }

        public final void setQz(float f) {
            this.qz = f;
        }

        public final float getOrbRBase() {
            return this.orbRBase;
        }

        public final void setOrbRBase(float f) {
            this.orbRBase = f;
        }

        public final float getSpeed() {
            return this.speed;
        }

        public final void setSpeed(float f) {
            this.speed = f;
        }

        public final float getPhase() {
            return this.phase;
        }

        public final void setPhase(float f) {
            this.phase = f;
        }

        public final float getHf() {
            return this.hf;
        }

        public final void setHf(float f) {
            this.hf = f;
        }

        public final float getHAmp() {
            return this.hAmp;
        }

        public final void setHAmp(float f) {
            this.hAmp = f;
        }

        public final float getPhx() {
            return this.phx;
        }

        public final void setPhx(float f) {
            this.phx = f;
        }

        public final float getPhy() {
            return this.phy;
        }

        public final void setPhy(float f) {
            this.phy = f;
        }

        public final float getPhz() {
            return this.phz;
        }

        public final void setPhz(float f) {
            this.phz = f;
        }

        public final float getBorn() {
            return this.born;
        }

        public final void setBorn(float f) {
            this.born = f;
        }

        public final float getLifeEnd() {
            return this.lifeEnd;
        }

        public final void setLifeEnd(float f) {
            this.lifeEnd = f;
        }

        public final float getShimFreq() {
            return this.shimFreq;
        }

        public final void setShimFreq(float f) {
            this.shimFreq = f;
        }

        public final float getShimPhase() {
            return this.shimPhase;
        }

        public final void setShimPhase(float f) {
            this.shimPhase = f;
        }

        public final float getSize() {
            return this.size;
        }

        public final void setSize(float f) {
            this.size = f;
        }

        public final int getHueOff() {
            return this.hueOff;
        }

        public final void setHueOff(int n) {
            this.hueOff = n;
        }

        public final float getCr() {
            return this.cr;
        }

        public final void setCr(float f) {
            this.cr = f;
        }

        public final float getSr() {
            return this.sr;
        }

        public final void setSr(float f) {
            this.sr = f;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/Trails$TailPoint;", "", "Lnet/minecraft/Vec3d;", "pos", "", "spawnMs", "<init>", "(Lnet/minecraft/Vec3d;J)V", "Lnet/minecraft/Vec3d;", "getPos", "()Lnet/minecraft/Vec3d;", "J", "getSpawnMs", "()J", "rtx.kimiko:kimiko"})
    private static final class TailPoint {
        @NotNull
        private final Vec3d pos;
        private final long spawnMs;

        public TailPoint(@NotNull Vec3d pos, long spawnMs) {
            Intrinsics.checkNotNullParameter((Object)pos, (String)"pos");
            this.pos = pos;
            this.spawnMs = spawnMs;
        }

        @NotNull
        public final Vec3d getPos() {
            return this.pos;
        }

        public final long getSpawnMs() {
            return this.spawnMs;
        }
    }
}

