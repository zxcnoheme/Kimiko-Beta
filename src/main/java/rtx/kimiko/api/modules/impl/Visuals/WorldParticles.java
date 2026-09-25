/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Position
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Quaternionf
 *  org.joml.Vector3fc
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3fc;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.impl.Visuals.particles.FadeParticle;
import rtx.kimiko.api.modules.impl.Visuals.particles.ParticleCollision;
import rtx.kimiko.api.modules.impl.Visuals.particles.ParticleColors;
import rtx.kimiko.api.modules.impl.Visuals.particles.ParticleRenderer;
import rtx.kimiko.api.modules.impl.Visuals.particles.ParticleTexturePicker;
import rtx.kimiko.api.modules.impl.Visuals.particles.dashlines.DashLineField;
import rtx.kimiko.api.modules.impl.Visuals.particles.dashlines.DashLineRenderer;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.NumberSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.render2d.ClientPalette;

@Feature(value={"worldparticles"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00bc\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 l2\u00020\u0001:\u0001lB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\u0006\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u001b\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0003b\u0002\b\t\u00a2\u0006\u0004\b\n\u0010\u000bJG\u0010\u0017\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u0003J\u001b\u0010\u001b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u001aH\u0003b\u0002\b\t\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001d\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u000eH\u0016\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010\"\u001a\u00020 2\u0006\u0010!\u001a\u00020 H\u0002\u00a2\u0006\u0004\b\"\u0010#J\u001f\u0010%\u001a\u00020 2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010$\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b%\u0010&J\u0017\u0010*\u001a\u00020)2\u0006\u0010(\u001a\u00020'H\u0002\u00a2\u0006\u0004\b*\u0010+J\u000f\u0010,\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b,\u0010\u001fJ\u001f\u0010/\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u00102\u0006\u0010.\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b/\u00100R\u0014\u00102\u001a\u0002018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0014\u00105\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00106R\u0014\u00108\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010:\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u00109R\u0014\u0010;\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b;\u00109R\u0014\u0010<\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u00109R\u0014\u0010=\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u00109R\u0014\u0010>\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b>\u00109R\u0014\u0010?\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u00109R\u0014\u0010@\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b@\u00109R\u0014\u0010B\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0014\u0010D\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u00106R\u0014\u0010E\u001a\u0002018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u00103R\u0014\u0010F\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bF\u00109R\u0014\u0010G\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010CR\u0014\u0010H\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u00106R\u0014\u0010I\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u00109R\u0014\u0010J\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u00109R\u0014\u0010K\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bK\u00109R\u0014\u0010L\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u00109R\u0014\u0010M\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bM\u00106R\u0014\u0010N\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bN\u00109R\u0014\u0010O\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bO\u00109R\u0014\u0010P\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bP\u00109R\u0014\u0010Q\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bQ\u00109R\u0014\u0010R\u001a\u0002018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bR\u00103R\u0014\u0010S\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bS\u00106R\u0014\u0010T\u001a\u0002018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bT\u00103R\u0014\u0010U\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bU\u0010CR\u0014\u0010W\u001a\u00020V8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bW\u0010XR\u0014\u0010Y\u001a\u00020V8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bY\u0010XR\u001a\u0010[\u001a\b\u0012\u0004\u0012\u00020\f0Z8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b[\u0010\\R\u0014\u0010^\u001a\u00020]8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b^\u0010_R\u0014\u0010a\u001a\u00020`8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\ba\u0010bR\u0014\u0010d\u001a\u00020c8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bd\u0010eR\u0014\u0010g\u001a\u00020f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bg\u0010hR\u0016\u0010j\u001a\u00020i8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bj\u0010k\u00ca\u0001\u0010\bm\u0012\f\bn\u0012\b\b\fJ\u0004\b\b(o\u00a8\u0006p"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/WorldParticles;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "onEnable", "onDisable", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "Lrtx/kimiko/api/modules/impl/Visuals/particles/FadeParticle;", "particle", "", "power", "", "addX", "gravityPerTick", "addZ", "", "collide", "collisionGravity", "stepParticle", "(Lrtx/kimiko/api/modules/impl/Visuals/particles/FadeParticle;FDDDLjava/lang/String;F)V", "spawnParticles", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "renderDashLines", "fadeOutSeconds", "()F", "", "offset", "gradientColor", "(I)I", "alpha", "particleColor", "(Lrtx/kimiko/api/modules/impl/Visuals/particles/FadeParticle;F)I", "Lnet/minecraft/Vec3d;", "pos", "", "isInPlayerView", "(Lnet/minecraft/Vec3d;)Z", "cameraYaw", "min", "max", "randomRange", "(DD)D", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "mode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "dashSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "dashCount", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "dashLength", "dashThickness", "dashSpeed", "dashRadius", "dashSpread", "dashOpacity", "dashGlow", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "dashMotionOnly", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "appearanceSeparator", "display", "size", "scaleWithAlpha", "spawnSeparator", "count", "range", "rangeY", "lifetime", "motionSeparator", "motionPower", "gravity", "inclineX", "inclineZ", "collideMode", "colorSeparator", "colorMode", "useSecondColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customSecondColor", "", "particles", "Ljava/util/List;", "Ljava/util/Random;", "random", "Ljava/util/Random;", "Lrtx/kimiko/api/modules/impl/Visuals/particles/ParticleRenderer;", "renderer", "Lrtx/kimiko/api/modules/impl/Visuals/particles/ParticleRenderer;", "Lrtx/kimiko/api/modules/impl/Visuals/particles/dashlines/DashLineField;", "dashField", "Lrtx/kimiko/api/modules/impl/Visuals/particles/dashlines/DashLineField;", "Lrtx/kimiko/api/modules/impl/Visuals/particles/dashlines/DashLineRenderer;", "dashRenderer", "Lrtx/kimiko/api/modules/impl/Visuals/particles/dashlines/DashLineRenderer;", "", "lastFrameNanos", "J", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "worldparticles", "rtx.kimiko:kimiko"})
public final class WorldParticles
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ModeSetting mode;
    @NotNull
    private final SeparatorSetting dashSeparator;
    @NotNull
    private final NumberSetting dashCount;
    @NotNull
    private final NumberSetting dashLength;
    @NotNull
    private final NumberSetting dashThickness;
    @NotNull
    private final NumberSetting dashSpeed;
    @NotNull
    private final NumberSetting dashRadius;
    @NotNull
    private final NumberSetting dashSpread;
    @NotNull
    private final NumberSetting dashOpacity;
    @NotNull
    private final NumberSetting dashGlow;
    @NotNull
    private final BooleanSetting dashMotionOnly;
    @NotNull
    private final SeparatorSetting appearanceSeparator;
    @NotNull
    private final ModeSetting display;
    @NotNull
    private final NumberSetting size;
    @NotNull
    private final BooleanSetting scaleWithAlpha;
    @NotNull
    private final SeparatorSetting spawnSeparator;
    @NotNull
    private final NumberSetting count;
    @NotNull
    private final NumberSetting range;
    @NotNull
    private final NumberSetting rangeY;
    @NotNull
    private final NumberSetting lifetime;
    @NotNull
    private final SeparatorSetting motionSeparator;
    @NotNull
    private final NumberSetting motionPower;
    @NotNull
    private final NumberSetting gravity;
    @NotNull
    private final NumberSetting inclineX;
    @NotNull
    private final NumberSetting inclineZ;
    @NotNull
    private final ModeSetting collideMode;
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
    private final List<FadeParticle> particles;
    @NotNull
    private final Random random;
    @NotNull
    private final ParticleRenderer renderer;
    @NotNull
    private final DashLineField dashField;
    @NotNull
    private final DashLineRenderer dashRenderer;
    private long lastFrameNanos;
    @NotNull
    private static final String MODE_PARTICLES = "Частицы";
    @NotNull
    private static final String MODE_DASH = "Dash Lines";
    private static final int MAX_PARTICLES = 1500;
    private static final int DARK_SECOND_COLOR = new Color(16, 16, 16, 75).getRGB();

    public WorldParticles() {
        super("World Particles", "Рендерит красивые частицы вокруг вас.", Category.VISUALS);
        String[] stringArray = new String[]{MODE_PARTICLES, MODE_DASH};
        this.mode = (ModeSetting)this.register((Setting)new ModeSetting("Режим", "Что рисовать вокруг игрока.", MODE_PARTICLES, stringArray));
        this.dashSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting(MODE_DASH));
        this.dashCount = (NumberSetting)this.register((Setting)new NumberSetting("Плотность", "Сколько штрихов проносится вокруг вас.", 70.0, 10.0, 220.0, 5.0));
        this.dashLength = (NumberSetting)this.register((Setting)new NumberSetting("Длина", "Длина штрихов.", 100.0, 25.0, 300.0, 5.0));
        this.dashThickness = (NumberSetting)this.register((Setting)new NumberSetting("Толщина", "Толщина штрихов.", 100.0, 25.0, 300.0, 5.0));
        this.dashSpeed = (NumberSetting)this.register((Setting)new NumberSetting("Скорость", "Как быстро штрихи проносятся мимо вас.", 100.0, 30.0, 250.0, 5.0));
        this.dashRadius = (NumberSetting)this.register((Setting)new NumberSetting("Радиус", "Насколько широко штрихи расходятся вокруг вас.", 4.0, 1.0, 12.0, 0.5));
        this.dashSpread = (NumberSetting)this.register((Setting)new NumberSetting("Разброс наклона", "Насколько сильно штрихи отклоняются от направления движения.", 20.0, 0.0, 60.0, 1.0));
        this.dashOpacity = (NumberSetting)this.register((Setting)new NumberSetting("Прозрачность", "Насколько плотные штрихи.", 85.0, 5.0, 100.0, 1.0));
        this.dashGlow = (NumberSetting)this.register((Setting)new NumberSetting("Свечение", "Яркость неонового ядра на острие штриха.", 60.0, 0.0, 100.0, 1.0));
        this.dashMotionOnly = (BooleanSetting)this.register((Setting)new BooleanSetting("Только при движении", "Показывать штрихи только когда вы двигаетесь.", true));
        this.appearanceSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Внешний вид"));
        stringArray = ParticleTexturePicker.modeOptions();
        this.display = (ModeSetting)this.register((Setting)new ModeSetting("Отображать", "Какие текстуры частиц рисовать.", "Отображать всё", Arrays.copyOf(stringArray, stringArray.length)));
        this.size = (NumberSetting)this.register((Setting)new NumberSetting("Размер", "Размер частиц.", 150.0, 100.0, 200.0, 10.0));
        this.scaleWithAlpha = (BooleanSetting)this.register((Setting)new BooleanSetting("Скейл", "Масштаб частицы следует её прозрачности.", true));
        this.spawnSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Появление"));
        this.count = (NumberSetting)this.register((Setting)new NumberSetting("Количество", "Попыток появления частиц каждый тик.", 15.0, 1.0, 60.0, 1.0));
        this.range = (NumberSetting)this.register((Setting)new NumberSetting("Радиус спавна", "Горизонтальный разброс появления частиц.", 50.0, 10.0, 50.0, 1.0));
        this.rangeY = (NumberSetting)this.register((Setting)new NumberSetting("Высота спавна", "Максимальная высота появления над вами.", 30.0, 0.05, 30.0, 0.05));
        this.lifetime = (NumberSetting)this.register((Setting)new NumberSetting("Время существования", "Время проявления и угасания частицы в мс.", 800.0, 150.0, 1500.0, 10.0));
        this.motionSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Движение"));
        this.motionPower = (NumberSetting)this.register((Setting)new NumberSetting("Сила движения", "Множитель скорости полёта частиц.", 1.0, 0.1, 2.0, 0.1));
        this.gravity = (NumberSetting)this.register((Setting)new NumberSetting("Сила гравитации", "Постоянное ускорение частиц вниз или вверх.", 0.0, -10.0, 10.0, 1.0));
        this.inclineX = (NumberSetting)this.register((Setting)new NumberSetting("Наклон полёта по X", "Боковой снос частиц относительно взгляда.", 0.0, -17.5, 17.5, 0.5));
        this.inclineZ = (NumberSetting)this.register((Setting)new NumberSetting("Наклон полёта по Z", "Продольный снос частиц относительно взгляда.", 17.5, -17.5, 17.5, 0.5));
        stringArray = ParticleCollision.MODES;
        this.collideMode = (ModeSetting)this.register((Setting)new ModeSetting("При столкновении", "Поведение при столкновении с блоком.", "Отскок", Arrays.copyOf(stringArray, stringArray.length)));
        this.colorSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Цвет"));
        stringArray = new String[]{"Клиент", "Радуга", "Свой"};
        this.colorMode = (ModeSetting)this.register((Setting)new ModeSetting("Режим цвета", "Режим цвета частиц.", "Клиент", stringArray));
        this.useSecondColor = (BooleanSetting)this.register((Setting)new BooleanSetting("Второй цвет", "Использовать второй свой цвет.", false));
        this.customColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет", "Основной цвет частиц.", new Color(255, 255, 255, 255)));
        this.customSecondColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет 2", "Второй цвет частиц.", new Color(ColorEngine.lerpColor(-1, DARK_SECOND_COLOR, 0.7f), true)));
        this.particles = new ArrayList();
        this.random = new Random();
        this.renderer = new ParticleRenderer();
        this.dashField = new DashLineField();
        this.dashRenderer = new DashLineRenderer();
        this.useSecondColor.visibleWhen(() -> WorldParticles._init_$lambda$0(this));
        this.customColor.visibleWhen(() -> WorldParticles._init_$lambda$1(this));
        this.customSecondColor.visibleWhen(() -> WorldParticles._init_$lambda$2(this));
        this.appearanceSeparator.visibleWhen(() -> WorldParticles._init_$lambda$3(this));
        this.display.visibleWhen(() -> WorldParticles._init_$lambda$4(this));
        this.size.visibleWhen(() -> WorldParticles._init_$lambda$5(this));
        this.scaleWithAlpha.visibleWhen(() -> WorldParticles._init_$lambda$6(this));
        this.spawnSeparator.visibleWhen(() -> WorldParticles._init_$lambda$7(this));
        this.count.visibleWhen(() -> WorldParticles._init_$lambda$8(this));
        this.range.visibleWhen(() -> WorldParticles._init_$lambda$9(this));
        this.rangeY.visibleWhen(() -> WorldParticles._init_$lambda$10(this));
        this.lifetime.visibleWhen(() -> WorldParticles._init_$lambda$11(this));
        this.motionSeparator.visibleWhen(() -> WorldParticles._init_$lambda$12(this));
        this.motionPower.visibleWhen(() -> WorldParticles._init_$lambda$13(this));
        this.gravity.visibleWhen(() -> WorldParticles._init_$lambda$14(this));
        this.inclineX.visibleWhen(() -> WorldParticles._init_$lambda$15(this));
        this.inclineZ.visibleWhen(() -> WorldParticles._init_$lambda$16(this));
        this.collideMode.visibleWhen(() -> WorldParticles._init_$lambda$17(this));
        this.dashSeparator.visibleWhen(() -> WorldParticles._init_$lambda$18(this));
        this.dashCount.visibleWhen(() -> WorldParticles._init_$lambda$19(this));
        this.dashLength.visibleWhen(() -> WorldParticles._init_$lambda$20(this));
        this.dashThickness.visibleWhen(() -> WorldParticles._init_$lambda$21(this));
        this.dashSpeed.visibleWhen(() -> WorldParticles._init_$lambda$22(this));
        this.dashRadius.visibleWhen(() -> WorldParticles._init_$lambda$23(this));
        this.dashSpread.visibleWhen(() -> WorldParticles._init_$lambda$24(this));
        this.dashOpacity.visibleWhen(() -> WorldParticles._init_$lambda$25(this));
        this.dashGlow.visibleWhen(() -> WorldParticles._init_$lambda$26(this));
        this.dashMotionOnly.visibleWhen(() -> WorldParticles._init_$lambda$27(this));
    }

    @Override
    protected void onEnable() {
        this.particles.clear();
        this.dashField.clear();
        this.lastFrameNanos = 0L;
    }

    @Override
    protected void onDisable() {
        this.particles.clear();
        this.dashField.clear();
        this.lastFrameNanos = 0L;
    }

    @EventHandler
    private final void onTick(TickEvent event) {
        if (this.mode.is(MODE_DASH)) {
            if (event.isPre() && !((Collection)this.particles).isEmpty()) {
                this.particles.clear();
            }
            return;
        }
        if (event.isPre() && !((Collection)this.dashField.alive()).isEmpty()) {
            this.dashField.clear();
        }
        ClientPlayerEntity player = this.mc.player;
        ClientWorld level = this.mc.world;
        if (!event.isPre() || !this.isVisuallyActive() || level == null || player == null) {
            if (!event.isPre() || !this.isVisuallyActive()) {
                return;
            }
            this.particles.clear();
            return;
        }
        long now = System.currentTimeMillis();
        this.particles.removeIf(p -> p.isDead(now));
        float power = this.motionPower.getFloat();
        float gravityPerTick = this.gravity.getFloat() / 80.0f * 0.05f * power;
        float collisionGravity = MathHelper.clamp((float)(this.gravity.getFloat() / 10.0f), (float)0.0f, (float)1.0f);
        String collide = this.collideMode.getValue();
        float yaw = this.cameraYaw();
        double xY = Math.sin(Math.toRadians(yaw));
        double zY = -Math.cos(Math.toRadians(yaw));
        double xX = -Math.sin(Math.toRadians(yaw + 90.0f));
        double zX = Math.cos(Math.toRadians(yaw + 90.0f));
        double addX = (xY * (double)this.inclineZ.getFloat() / 50.0 + xX * (double)this.inclineX.getFloat() / 50.0) * 0.05 * (double)power;
        double addZ = (zY * (double)this.inclineZ.getFloat() / 50.0 + zX * (double)this.inclineX.getFloat() / 50.0) * 0.05 * (double)power;
        for (FadeParticle particle : this.particles) {
            this.stepParticle(particle, power, addX, gravityPerTick, addZ, collide, collisionGravity);
        }
        if (this.isEnabled()) {
            this.spawnParticles();
        }
    }

    private final void stepParticle(FadeParticle particle, float power, double addX, double gravityPerTick, double addZ, String collide, float collisionGravity) {
        particle.beginStep();
        if (!particle.holdCollide) {
            particle.posX += particle.motionX * (double)power;
            particle.posY += particle.motionY * (double)power;
            particle.posZ += particle.motionZ * (double)power;
            particle.motionX += addX;
            particle.motionY += gravityPerTick;
            particle.motionZ += addZ;
        }
        particle.motionY -= 6.0E-4;
        boolean cancelMove = ParticleCollision.apply(this.mc, particle, collide, collisionGravity);
        if (!cancelMove) {
            particle.posX += particle.motionX;
            particle.posY += particle.motionY;
            particle.posZ += particle.motionZ;
        }
    }

    private final void spawnParticles() {
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
        int attempts = this.count.getInt();
        float rangeValue = this.range.getFloat();
        float rangeYValue = Math.max(0.55f, this.rangeY.getFloat());
        float lifetimeValue = this.lifetime.getFloat();
        int i = 0;
        while (i < attempts && this.particles.size() < 1500) {
            double offsetX = this.randomRange(-((double)rangeValue), rangeValue);
            double offsetZ = this.randomRange(-((double)rangeValue), rangeValue);
            double offsetY = this.randomRange(0.5, rangeYValue);
            Vec3d pos = player.getEntityPos().add(offsetX, offsetY, offsetZ);
            if (!level.getBlockState(BlockPos.ofFloored((Position)pos)).isAir() || !this.isInPlayerView(pos)) {
                ++i;
                continue;
            }
            Vec3d motion = new Vec3d(this.randomRange(-0.04, 0.04), this.randomRange(0.0, 0.05), this.randomRange(-0.04, 0.04));
            float gradientT = attempts <= 1 ? 0.0f : (float)i / (float)(attempts - 1);
            this.particles.add(new FadeParticle(pos, motion, (float)this.randomRange(0.0, 180.0), lifetimeValue, ParticleTexturePicker.pick(this.display, this.random), gradientT));
            ++i;
        }
    }

    @EventHandler
    private final void onWorldRender(WorldRenderEvent event) {
        Camera camera2;
        if (!this.isVisuallyActive() || this.mc.world == null || this.mc.player == null || this.mc.gameRenderer == null) {
            this.lastFrameNanos = 0L;
            return;
        }
        if (this.mode.is(MODE_DASH)) {
            this.renderDashLines(event);
            return;
        }
        if (this.particles.isEmpty()) {
            return;
        }
        long now = System.currentTimeMillis();
        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate2 = this.mc.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        VertexConsumerProvider.Immediate provider = immediate2;
        if (event.getCamera() != null) {
            camera2 = event.getCamera();
        } else {
            Camera camera3 = this.mc.gameRenderer.getCamera();
            camera2 = camera3;
            Intrinsics.checkNotNullExpressionValue((Object)camera3, (String)"getMainCamera(...)");
        }
        Camera camera = camera2;
        Vec3d vec3d2 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d cameraPos = vec3d2;
        Quaternionf quaternionf = camera.getRotation();
        Intrinsics.checkNotNullExpressionValue((Object)quaternionf, (String)"rotation(...)");
        Quaternionf cameraRotation = quaternionf;
        Vector3fc vector3fc = camera.getHorizontalPlane();
        Intrinsics.checkNotNullExpressionValue((Object)vector3fc, (String)"forwardVector(...)");
        Vector3fc cameraForward = vector3fc;
        float partialTicks = MathHelper.clamp((float)event.getPartialTicks(), (float)0.0f, (float)1.0f);
        float worldSize = this.size.getFloat() * 0.012f;
        boolean scaleAlpha = this.scaleWithAlpha.getValue();
        float moduleFade = this.visualAlpha();
        this.renderer.clear();
        for (FadeParticle particle : this.particles) {
            float alpha = particle.alpha01(now) * moduleFade;
            if (alpha <= 0.004f) continue;
            Vec3d renderPos = particle.renderPos(partialTicks);
            double toX = renderPos.x - cameraPos.x;
            double toY = renderPos.y - cameraPos.y;
            double toZ = renderPos.z - cameraPos.z;
            if (toX * (double)cameraForward.x() + toY * (double)cameraForward.y() + toZ * (double)cameraForward.z() < (double)(-worldSize * 2.0f)) continue;
            int color = this.particleColor(particle, alpha);
            float renderSize = scaleAlpha ? worldSize * alpha : worldSize;
            this.renderer.drawTexture(stack, provider, particle.texture, renderPos, cameraPos, cameraRotation, renderSize, particle.rotationDeg, color, true);
        }
        this.renderer.flush(provider);
    }

    private final void renderDashLines(WorldRenderEvent event) {
        Camera camera2;
        long now = System.nanoTime();
        float frameSeconds = this.lastFrameNanos == 0L ? 0.0f : (float)(now - this.lastFrameNanos) / 1.0E9f;
        this.lastFrameNanos = now;
        if (event.getCamera() != null) {
            camera2 = event.getCamera();
        } else {
            Camera camera3 = this.mc.gameRenderer.getCamera();
            camera2 = camera3;
            Intrinsics.checkNotNullExpressionValue((Object)camera3, (String)"getMainCamera(...)");
        }
        Camera camera = camera2;
        Vec3d vec3d2 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d cameraPos = vec3d2;
        this.dashField.update(this.mc, cameraPos, frameSeconds, this.isEnabled() ? this.dashCount.getInt() : 0, this.dashRadius.getFloat(), this.dashLength.getFloat() / 100.0f, this.dashThickness.getFloat() / 100.0f, this.dashSpeed.getFloat() / 100.0f, this.dashSpread.getFloat(), this.dashMotionOnly.getValue());
        if (this.dashField.alive().isEmpty()) {
            return;
        }
        MatrixStack matrixStack2 = event.getStack();
        VertexConsumerProvider.Immediate immediate2 = this.mc.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        this.dashRenderer.render(matrixStack2, immediate2, this.dashField, cameraPos, this.gradientColor(0), this.gradientColor(150), this.dashOpacity.getFloat() / 100.0f * this.visualAlpha(), this.dashGlow.getFloat() / 100.0f * this.visualAlpha());
    }

    @Override
    public float fadeOutSeconds() {
        return this.mode.is(MODE_DASH) ? 0.9f : 1.2f;
    }

    private final int gradientColor(int offset) {
        if (this.colorMode.is("Радуга")) {
            return ParticleColors.rainbow(8, offset, 1.0f, 1.0f, 1.0f);
        }
        if (this.colorMode.is("Клиент")) {
            int[] palette = ClientPalette.colors();
            if (palette != null && palette.length >= 2) {
                return ParticleColors.paletteFade(8, offset, palette) | 0xFF000000;
            }
            InterfaceModule interfaceModule = InterfaceModule.Companion.getInstance();
            if (interfaceModule == null) {
                return -1;
            }
            InterfaceModule iface = interfaceModule;
            if (offset == 0 || !iface.usesSecondClientColor()) {
                return iface.clientPrimaryColorOpaque();
            }
            return iface.clientSecondaryColorOpaque();
        }
        if (offset == 0 || !this.useSecondColor.getValue()) {
            return this.customColor.getColor() | 0xFF000000;
        }
        return this.customSecondColor.getColor() | 0xFF000000;
    }

    private final int particleColor(FadeParticle particle, float alpha) {
        int index = (int)(particle.gradientT * 360.0f);
        if (this.colorMode.is("Радуга")) {
            return ParticleColors.rainbow(8, index, 1.0f, 1.0f, alpha);
        }
        int firstColor = 0;
        int secondColor = 0;
        if (this.colorMode.is("Клиент")) {
            int[] palette = ClientPalette.colors();
            if (palette != null && palette.length >= 2) {
                return ColorEngine.multAlpha(ParticleColors.paletteFade(8, index, palette), alpha);
            }
            InterfaceModule iface = InterfaceModule.Companion.getInstance();
            if (iface != null) {
                firstColor = iface.clientPrimaryColorOpaque();
                secondColor = iface.usesSecondClientColor() ? iface.clientSecondaryColorOpaque() : firstColor;
            } else {
                secondColor = firstColor = -1;
            }
        } else {
            firstColor = this.customColor.getColor();
            int n = secondColor = this.useSecondColor.getValue() ? this.customSecondColor.getColor() : this.customColor.getColor();
        }
        if (firstColor == secondColor) {
            return ColorEngine.multAlpha(firstColor, alpha);
        }
        return ColorEngine.multAlpha(ParticleColors.fade(8, index, firstColor, secondColor), alpha);
    }

    private final boolean isInPlayerView(Vec3d pos) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return false;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        Vec3d vec3d2 = Vec3d.fromPolar((float)player.getPitch(), (float)this.cameraYaw());
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"directionFromRotation(...)");
        Vec3d view = vec3d2;
        Vec3d vec3d3 = pos.subtract(player.getEntityPos()).normalize();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"normalize(...)");
        Vec3d direction = vec3d3;
        return view.dotProduct(direction) > 0.1;
    }

    private final float cameraYaw() {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return 0.0f;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        float yaw = player.getYaw();
        if (this.mc.options.getPerspective().isFrontView()) {
            yaw += 180.0f;
        }
        return yaw;
    }

    private final double randomRange(double min, double max) {
        return min + this.random.nextDouble() * (max - min);
    }

    private static final Boolean _init_$lambda$0(WorldParticles this$0) {
        return this$0.colorMode.is("Свой");
    }

    private static final Boolean _init_$lambda$1(WorldParticles this$0) {
        return this$0.colorMode.is("Свой");
    }

    private static final Boolean _init_$lambda$2(WorldParticles this$0) {
        return this$0.colorMode.is("Свой") && this$0.useSecondColor.getValue();
    }

    private static final Boolean _init_$lambda$3(WorldParticles this$0) {
        return this$0.mode.is(MODE_PARTICLES);
    }

    private static final Boolean _init_$lambda$4(WorldParticles this$0) {
        return this$0.mode.is(MODE_PARTICLES);
    }

    private static final Boolean _init_$lambda$5(WorldParticles this$0) {
        return this$0.mode.is(MODE_PARTICLES);
    }

    private static final Boolean _init_$lambda$6(WorldParticles this$0) {
        return this$0.mode.is(MODE_PARTICLES);
    }

    private static final Boolean _init_$lambda$7(WorldParticles this$0) {
        return this$0.mode.is(MODE_PARTICLES);
    }

    private static final Boolean _init_$lambda$8(WorldParticles this$0) {
        return this$0.mode.is(MODE_PARTICLES);
    }

    private static final Boolean _init_$lambda$9(WorldParticles this$0) {
        return this$0.mode.is(MODE_PARTICLES);
    }

    private static final Boolean _init_$lambda$10(WorldParticles this$0) {
        return this$0.mode.is(MODE_PARTICLES);
    }

    private static final Boolean _init_$lambda$11(WorldParticles this$0) {
        return this$0.mode.is(MODE_PARTICLES);
    }

    private static final Boolean _init_$lambda$12(WorldParticles this$0) {
        return this$0.mode.is(MODE_PARTICLES);
    }

    private static final Boolean _init_$lambda$13(WorldParticles this$0) {
        return this$0.mode.is(MODE_PARTICLES);
    }

    private static final Boolean _init_$lambda$14(WorldParticles this$0) {
        return this$0.mode.is(MODE_PARTICLES);
    }

    private static final Boolean _init_$lambda$15(WorldParticles this$0) {
        return this$0.mode.is(MODE_PARTICLES);
    }

    private static final Boolean _init_$lambda$16(WorldParticles this$0) {
        return this$0.mode.is(MODE_PARTICLES);
    }

    private static final Boolean _init_$lambda$17(WorldParticles this$0) {
        return this$0.mode.is(MODE_PARTICLES);
    }

    private static final Boolean _init_$lambda$18(WorldParticles this$0) {
        return this$0.mode.is(MODE_DASH);
    }

    private static final Boolean _init_$lambda$19(WorldParticles this$0) {
        return this$0.mode.is(MODE_DASH);
    }

    private static final Boolean _init_$lambda$20(WorldParticles this$0) {
        return this$0.mode.is(MODE_DASH);
    }

    private static final Boolean _init_$lambda$21(WorldParticles this$0) {
        return this$0.mode.is(MODE_DASH);
    }

    private static final Boolean _init_$lambda$22(WorldParticles this$0) {
        return this$0.mode.is(MODE_DASH);
    }

    private static final Boolean _init_$lambda$23(WorldParticles this$0) {
        return this$0.mode.is(MODE_DASH);
    }

    private static final Boolean _init_$lambda$24(WorldParticles this$0) {
        return this$0.mode.is(MODE_DASH);
    }

    private static final Boolean _init_$lambda$25(WorldParticles this$0) {
        return this$0.mode.is(MODE_DASH);
    }

    private static final Boolean _init_$lambda$26(WorldParticles this$0) {
        return this$0.mode.is(MODE_DASH);
    }

    private static final Boolean _init_$lambda$27(WorldParticles this$0) {
        return this$0.mode.is(MODE_DASH);
    }


    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\n\u00a8\u0006\f"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/WorldParticles.Companion;", "", "<init>", "()V", "", "MODE_PARTICLES", "Ljava/lang/String;", "MODE_DASH", "", "MAX_PARTICLES", "I", "DARK_SECOND_COLOR", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

