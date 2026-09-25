/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.ClientPlayerEntity
 *  net.minecraft.client.render.GameRenderer
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Quaternionf
 *  org.joml.Vector3fc
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3fc;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.player.AttackEntityEvent;
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
import rtx.kimiko.api.modules.impl.Visuals.particles.WorldParticleUtil;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.NumberSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.render2d.ClientPalette;

@Feature(value={"hitparticles"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00a8\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 V2\u00020\u0001:\u0001VB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\t\u0010\u0003J\u001b\u0010\r\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0003b\u0002\b\f\u00a2\u0006\u0004\b\r\u0010\u000eJ/\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001b\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u001cH\u0003b\u0002\b\f\u00a2\u0006\u0004\b\u001d\u0010\u001eJ'\u0010$\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\"H\u0002\u00a2\u0006\u0004\b$\u0010%J\u001b\u0010'\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020&H\u0003b\u0002\b\f\u00a2\u0006\u0004\b'\u0010(J\u001f\u0010+\u001a\u00020*2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010)\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b+\u0010,J\u001f\u0010/\u001a\u00020\u00122\u0006\u0010-\u001a\u00020\u00122\u0006\u0010.\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b/\u00100R\u0014\u00102\u001a\u0002018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0014\u00105\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00106R\u0014\u00108\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010;\u001a\u00020:8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b;\u0010<R\u0014\u0010=\u001a\u0002018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u00103R\u0014\u0010>\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b>\u00109R\u0014\u0010?\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u00109R\u0014\u0010@\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b@\u00106R\u0014\u0010A\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bA\u00109R\u0014\u0010B\u001a\u0002018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u00103R\u0014\u0010C\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bC\u00109R\u0014\u0010D\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u00109R\u0014\u0010E\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u00106R\u0014\u0010F\u001a\u0002018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bF\u00103R\u0014\u0010G\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u00106R\u0014\u0010H\u001a\u00020:8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u0010<R\u0014\u0010J\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010KR\u0014\u0010L\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u0010KR\u001a\u0010N\u001a\b\u0012\u0004\u0012\u00020\u001f0M8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bN\u0010OR\u0014\u0010Q\u001a\u00020P8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bQ\u0010RR\u0014\u0010T\u001a\u00020S8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bT\u0010U\u00ca\u0001\u0010\bW\u0012\f\bX\u0012\b\b\fJ\u0004\b\b(Y\u00a8\u0006Z"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/HitParticles;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "onEnable", "", "fadeOutSeconds", "()F", "onDisable", "Lrtx/kimiko/api/events/impl/player/AttackEntityEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onAttack", "(Lrtx/kimiko/api/events/impl/player/AttackEntityEvent;)V", "Lnet/minecraft/Vec3d;", "center", "pos", "", "motionSpeed", "volleyTwist", "initialMotion", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;DF)Lnet/minecraft/Vec3d;", "randomUnitDirection", "()Lnet/minecraft/Vec3d;", "delta", "horizontalDir", "(Lnet/minecraft/Vec3d;)Lnet/minecraft/Vec3d;", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "Lrtx/kimiko/api/modules/impl/Visuals/particles/FadeParticle;", "particle", "gravityValue", "", "collide", "stepParticle", "(Lrtx/kimiko/api/modules/impl/Visuals/particles/FadeParticle;FLjava/lang/String;)V", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "alpha", "", "particleColor", "(Lrtx/kimiko/api/modules/impl/Visuals/particles/FadeParticle;F)I", "min", "max", "randomRange", "(DD)D", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "appearanceSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "display", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "size", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "scaleWithAlpha", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "spawnSeparator", "count", "spread", "spawnDirection", "lifetime", "motionSeparator", "speed", "gravity", "collideMode", "colorSeparator", "colorMode", "useSecondColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customSecondColor", "", "particles", "Ljava/util/List;", "Ljava/util/Random;", "random", "Ljava/util/Random;", "Lrtx/kimiko/api/modules/impl/Visuals/particles/ParticleRenderer;", "renderer", "Lrtx/kimiko/api/modules/impl/Visuals/particles/ParticleRenderer;", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "hitparticles", "rtx.kimiko:kimiko"})
public final class HitParticles
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting appearanceSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Внешний вид"));
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
    private final NumberSetting spread;
    @NotNull
    private final ModeSetting spawnDirection;
    @NotNull
    private final NumberSetting lifetime;
    @NotNull
    private final SeparatorSetting motionSeparator;
    @NotNull
    private final NumberSetting speed;
    @NotNull
    private final NumberSetting gravity;
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
    private static final int MAX_PARTICLES = 1000;
    private static final int DARK_SECOND_COLOR = new Color(16, 16, 16, 75).getRGB();
    private static final double TWIST_TURN_PER_TICK = 0.09;
    @NotNull
    private static final String DIR_RANDOM = "Случайно";
    @NotNull
    private static final String DIR_FROM_TARGET = "От цели";
    @NotNull
    private static final String DIR_TO_TARGET = "К цели";
    @NotNull
    private static final String DIR_TWIST = "Закручивание";

    public HitParticles() {
        super("Hit Particles", "Добавляет красивые частицы от ударов.", Category.VISUALS);
        String[] stringArray = ParticleTexturePicker.modeOptions();
        this.display = (ModeSetting)this.register((Setting)new ModeSetting("Отображать", "Какие текстуры частиц рисовать.", "Отображать всё", Arrays.copyOf(stringArray, stringArray.length)));
        this.size = (NumberSetting)this.register((Setting)new NumberSetting("Размер", "Размер частиц.", 25.0, 15.0, 56.0, 1.0));
        this.scaleWithAlpha = (BooleanSetting)this.register((Setting)new BooleanSetting("Скейл", "Масштаб частицы следует её прозрачности.", true));
        this.spawnSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Появление"));
        this.count = (NumberSetting)this.register((Setting)new NumberSetting("Количество", "Частиц за один удар.", 56.0, 3.0, 56.0, 1.0));
        this.spread = (NumberSetting)this.register((Setting)new NumberSetting("Дополнение к дистанции", "Максимальный разброс появления вокруг цели.", 0.5, 0.5, 6.0, 0.5));
        stringArray = new String[]{DIR_RANDOM, DIR_FROM_TARGET, DIR_TO_TARGET, DIR_TWIST};
        this.spawnDirection = (ModeSetting)this.register((Setting)new ModeSetting("Пресет полёта", "Куда и как разлетаются частицы удара.", DIR_TWIST, stringArray));
        this.lifetime = (NumberSetting)this.register((Setting)new NumberSetting("Время существования", "Время проявления и угасания частицы в мс.", 2000.0, 350.0, 2000.0, 25.0));
        this.motionSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Движение"));
        this.speed = (NumberSetting)this.register((Setting)new NumberSetting("Начальная скорость", "Начальная скорость частиц.", 0.3, 0.05, 1.0, 0.05));
        this.gravity = (NumberSetting)this.register((Setting)new NumberSetting("Гравитация", "Коэффициент гравитации частиц, минус — вверх.", 0.2, -0.5, 1.0, 0.05));
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
        this.useSecondColor.visibleWhen(() -> HitParticles._init_$lambda$0(this));
        this.customColor.visibleWhen(() -> HitParticles._init_$lambda$1(this));
        this.customSecondColor.visibleWhen(() -> HitParticles._init_$lambda$2(this));
    }

    @Override
    protected void onEnable() {
        this.particles.clear();
    }

    @Override
    public float fadeOutSeconds() {
        return 0.5f;
    }

    @Override
    protected void onDisable() {
        this.particles.clear();
    }

    @EventHandler
    private final void onAttack(AttackEntityEvent event) {
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        if (!this.isEnabled()) {
            return;
        }
        Entity entity2 = event.getTarget();
        if (entity2 == null) {
            return;
        }
        Entity target = entity2;
        int spawnCount = this.count.getInt();
        float lifetimeValue = this.lifetime.getFloat();
        float spreadValue = this.spread.getFloat();
        float speedValue = this.speed.getFloat();
        Vec3d vec3d2 = target.getEyePos().add(0.0, (double)(-target.getStandingEyeHeight() / 2.0f), 0.0);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"add(...)");
        Vec3d center = vec3d2;
        float volleyTwist = this.spawnDirection.is(DIR_TWIST) ? (this.random.nextBoolean() ? 1.0f : -1.0f) : 0.0f;
        for (int i = 0; i < spawnCount && this.particles.size() < 1000; ++i) {
            Vec3d pos = WorldParticleUtil.randomSpawnPosition(this.mc, this.random, spreadValue, center);
            if (pos == null) continue;
            double motionSpeed = this.randomRange((double)speedValue / 1.5, (double)speedValue * 1.5);
            Vec3d motion = this.initialMotion(center, pos, motionSpeed, volleyTwist);
            float gradientT = spawnCount <= 1 ? 0.0f : (float)i / (float)(spawnCount - 1);
            FadeParticle particle = new FadeParticle(pos, motion, (float)this.randomRange(0.0, 180.0), lifetimeValue, ParticleTexturePicker.pick(this.display, this.random), gradientT);
            particle.twist = volleyTwist;
            this.particles.add(particle);
        }
    }

    private final Vec3d initialMotion(Vec3d center, Vec3d pos, double motionSpeed, float volleyTwist) {
        if (this.spawnDirection.is(DIR_FROM_TARGET)) {
            Vec3d vec3d2;
            Vec3d vec3d3 = pos.subtract(center);
            Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"subtract(...)");
            Vec3d out = vec3d3;
            if (out.lengthSquared() < 1.0E-4) {
                Vec3d vec3d4 = this.randomUnitDirection().multiply(motionSpeed);
                Intrinsics.checkNotNull((Object)vec3d4);
                vec3d2 = vec3d4;
            } else {
                Vec3d vec3d5 = out.normalize().multiply(motionSpeed);
                Intrinsics.checkNotNull((Object)vec3d5);
                vec3d2 = vec3d5;
            }
            return vec3d2;
        }
        if (this.spawnDirection.is(DIR_TO_TARGET)) {
            Vec3d vec3d6;
            Vec3d vec3d7 = center.subtract(pos);
            Intrinsics.checkNotNullExpressionValue((Object)vec3d7, (String)"subtract(...)");
            Vec3d in = vec3d7;
            if (in.lengthSquared() < 1.0E-4) {
                Vec3d vec3d8 = this.randomUnitDirection().multiply(motionSpeed);
                Intrinsics.checkNotNull((Object)vec3d8);
                vec3d6 = vec3d8;
            } else {
                Vec3d vec3d9 = in.normalize().multiply(motionSpeed);
                Intrinsics.checkNotNull((Object)vec3d9);
                vec3d6 = vec3d9;
            }
            return vec3d6;
        }
        if (this.spawnDirection.is(DIR_TWIST)) {
            Vec3d vec3d10 = pos.subtract(center);
            Intrinsics.checkNotNullExpressionValue((Object)vec3d10, (String)"subtract(...)");
            Vec3d radial = this.horizontalDir(vec3d10);
            Vec3d tangent = new Vec3d(-radial.z * (double)volleyTwist, 0.0, radial.x * (double)volleyTwist);
            Vec3d vec3d11 = new Vec3d(tangent.x * 0.85 + radial.x * 0.25, this.randomRange(0.15, 0.45), tangent.z * 0.85 + radial.z * 0.25).normalize();
            Intrinsics.checkNotNullExpressionValue((Object)vec3d11, (String)"normalize(...)");
            Vec3d dir = vec3d11;
            Vec3d vec3d12 = dir.multiply(motionSpeed);
            Intrinsics.checkNotNullExpressionValue((Object)vec3d12, (String)"scale(...)");
            return vec3d12;
        }
        Vec3d vec3d13 = this.randomUnitDirection().multiply(motionSpeed);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d13, (String)"scale(...)");
        return vec3d13;
    }

    private final Vec3d randomUnitDirection() {
        double yaw = this.randomRange(0.0, Math.PI * 2);
        double y = this.randomRange(-0.35, 0.75);
        double horizontal = Math.sqrt(Math.max(0.0, 1.0 - y * y));
        return new Vec3d(Math.cos(yaw) * horizontal, y, Math.sin(yaw) * horizontal);
    }

    private final Vec3d horizontalDir(Vec3d delta) {
        Vec3d flat = new Vec3d(delta.x, 0.0, delta.z);
        if (flat.lengthSquared() < 1.0E-4) {
            double angle = this.randomRange(0.0, Math.PI * 2);
            return new Vec3d(Math.cos(angle), 0.0, Math.sin(angle));
        }
        Vec3d vec3d2 = flat.normalize();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"normalize(...)");
        return vec3d2;
    }

    @EventHandler
    private final void onTick(TickEvent event) {
        if (!event.isPre() || !this.isVisuallyActive()) {
            return;
        }
        if (this.mc.world == null || this.mc.player == null) {
            this.particles.clear();
            return;
        }
        long now = System.currentTimeMillis();
        this.particles.removeIf(p -> p.isDead(now));
        float gravityValue = this.gravity.getFloat();
        String collide = this.collideMode.getSelected();
        for (FadeParticle particle : this.particles) {
            this.stepParticle(particle, gravityValue, collide);
        }
    }

    private final void stepParticle(FadeParticle particle, float gravityValue, String collide) {
        boolean cancelMove;
        particle.beginStep();
        if (!(particle.twist == 0.0f) && !particle.holdCollide) {
            double angle = 0.09 * (double)particle.twist;
            double cos = Math.cos(angle);
            double sin = Math.sin(angle);
            double mx = particle.motionX * cos - particle.motionZ * sin;
            double mz = particle.motionX * sin + particle.motionZ * cos;
            particle.motionX = mx;
            particle.motionZ = mz;
        }
        particle.motionY -= (double)gravityValue / 17.5;
        if (gravityValue > 0.0f && particle.motionY > 0.0) {
            particle.motionY /= 1.0 + (double)gravityValue * 0.05;
        }
        if (!(cancelMove = ParticleCollision.apply(this.mc, particle, collide, gravityValue))) {
            particle.posX += particle.motionX;
            particle.posY += particle.motionY;
            particle.posZ += particle.motionZ;
        }
    }

    @EventHandler
    private final void onWorldRender(WorldRenderEvent event) {
        ClientWorld level = this.mc.world;
        ClientPlayerEntity player = this.mc.player;
        GameRenderer gameRenderer2 = this.mc.gameRenderer;
        Intrinsics.checkNotNullExpressionValue((Object)gameRenderer2, (String)"gameRenderer");
        GameRenderer gameRenderer = gameRenderer2;
        if (!this.isVisuallyActive() || this.particles.isEmpty() || level == null || player == null) {
            return;
        }
        float moduleFade = this.visualAlpha();
        long now = System.currentTimeMillis();
        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate2 = this.mc.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        VertexConsumerProvider.Immediate provider = immediate2;
        Camera camera2 = event.getCamera();
        if (camera2 == null) {
            Camera camera3 = gameRenderer.getCamera();
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
        this.renderer.clear();
        for (FadeParticle particle : this.particles) {
            float alpha = particle.alpha01(now) * moduleFade;
            if (alpha <= 0.004f) continue;
            Vec3d renderPos = particle.renderPos(partialTicks).add(0.0, 0.2, 0.0);
            double toX = renderPos.x - cameraPos.x;
            double toY = renderPos.y - cameraPos.y;
            double toZ = renderPos.z - cameraPos.z;
            if ((float)(toX * (double)cameraForward.x() + toY * (double)cameraForward.y() + toZ * (double)cameraForward.z()) < -worldSize * 2.0f) continue;
            int color = this.particleColor(particle, alpha);
            float renderSize = scaleAlpha ? worldSize * alpha : worldSize;
            this.renderer.drawTexture(stack, provider, particle.texture, renderPos, cameraPos, cameraRotation, renderSize, particle.rotationDeg, color, true);
        }
        this.renderer.flush(provider);
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

    private final double randomRange(double min, double max) {
        return min + this.random.nextDouble() * (max - min);
    }

    private static final Boolean _init_$lambda$0(HitParticles this$0) {
        return this$0.colorMode.is("Свой");
    }

    private static final Boolean _init_$lambda$1(HitParticles this$0) {
        return this$0.colorMode.is("Свой");
    }

    private static final Boolean _init_$lambda$2(HitParticles this$0) {
        return this$0.colorMode.is("Свой") && this$0.useSecondColor.getValue();
    }


    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\rR\u0014\u0010\u0010\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\r\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/HitParticles.Companion;", "", "<init>", "()V", "", "MAX_PARTICLES", "I", "DARK_SECOND_COLOR", "", "TWIST_TURN_PER_TICK", "D", "", "DIR_RANDOM", "Ljava/lang/String;", "DIR_FROM_TARGET", "DIR_TO_TARGET", "DIR_TWIST", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

