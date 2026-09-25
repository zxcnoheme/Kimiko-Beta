/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.render.Camera;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.player.JumpEvent;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.NumberSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.math.MathUtils;
import rtx.kimiko.utils.render.modules.post.jumpdistort.JumpDistortRenderer;
import rtx.kimiko.utils.render.modules.post.jumpsouls.JumpSoulsRenderer;
import rtx.kimiko.utils.render.render2d.ClientPalette;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"jumpeffects"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00b6\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 p2\u00020\u0001:\u0002qpB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\u0006J\u000f\u0010\n\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u0011\u001a\u00020\fH\u0015b\u000e\b\r\u0012\n\b\u000e\u0012\u0006\b\n0\u000f8\u0010\u00a2\u0006\u0004\b\u0011\u0010\u0003J\u001b\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0012H\u0003b\u0002\b\u0014\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0017H\u0003b\u0002\b\u0014\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ?\u0010&\u001a\u00020\f2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\u0010!\u001a\u0004\u0018\u00010 2\b\u0010\"\u001a\u0004\u0018\u00010 2\b\u0010#\u001a\u0004\u0018\u00010 2\b\u0010%\u001a\u0004\u0018\u00010$\u00a2\u0006\u0004\b&\u0010'JW\u0010-\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020 2\u0006\u0010%\u001a\u00020$2\u0006\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020\t2\u0006\u0010+\u001a\u00020\tH\u0003b\u000e\b\r\u0012\n\b\u000e\u0012\u0006\b\n0\u000f8,\u00a2\u0006\u0004\b-\u0010.J\u000f\u0010/\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b/\u0010\u0006J'\u00101\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\tH\u0003b\u000e\b\r\u0012\n\b\u000e\u0012\u0006\b\n0\u000f80\u00a2\u0006\u0004\b1\u00102J/\u00106\u001a\u0002032\u0006\u00104\u001a\u0002032\u0006\u00105\u001a\u00020\tH\u0003b\u000e\b\r\u0012\n\b\u000e\u0012\u0006\b\n0\u000f80\u00a2\u0006\u0004\b6\u00107R\u001a\u0010:\u001a\b\u0012\u0004\u0012\u000209088\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010<\u001a\u00020 8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u0010=R\u0014\u0010?\u001a\u00020>8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u0010@R\u0014\u0010A\u001a\u00020>8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bA\u0010@R\u0014\u0010C\u001a\u00020B8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bC\u0010DR\u0014\u0010F\u001a\u00020E8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bF\u0010GR\u0014\u0010I\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u0010JR\u0014\u0010K\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bK\u0010JR\u0014\u0010L\u001a\u00020E8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u0010GR\u0014\u0010M\u001a\u00020B8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bM\u0010DR\u0014\u0010N\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bN\u0010JR\u0014\u0010O\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bO\u0010JR\u0014\u0010P\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bP\u0010JR\u0014\u0010R\u001a\u00020Q8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bR\u0010SR\u0014\u0010T\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bT\u0010JR\u0014\u0010U\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bU\u0010JR\u0014\u0010V\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bV\u0010JR\u0014\u0010W\u001a\u00020Q8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bW\u0010SR\u0014\u0010X\u001a\u00020B8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bX\u0010DR\u0014\u0010Y\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bY\u0010JR\u0014\u0010Z\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bZ\u0010JR\u0014\u0010[\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b[\u0010JR\u0014\u0010\\\u001a\u00020Q8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\\\u0010SR\u0014\u0010]\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b]\u0010JR\u0014\u0010^\u001a\u00020B8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b^\u0010DR\u0014\u0010_\u001a\u00020Q8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b_\u0010SR\u0014\u0010`\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b`\u0010JR\u0014\u0010a\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\ba\u0010JR\u0014\u0010b\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bb\u0010JR\u0014\u0010c\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bc\u0010JR\u0014\u0010d\u001a\u00020B8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bd\u0010DR\u0014\u0010e\u001a\u00020Q8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\be\u0010SR\u0014\u0010f\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bf\u0010JR\u0014\u0010g\u001a\u00020B8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bg\u0010DR\u0014\u0010h\u001a\u00020E8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bh\u0010GR\u0014\u0010i\u001a\u00020Q8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bi\u0010SR\u0014\u0010k\u001a\u00020j8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bk\u0010lR\u0014\u0010m\u001a\u00020j8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bm\u0010lR\u0016\u0010n\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bn\u0010o\u00ca\u0001\u0010\br\u0012\f\b\u000e\u0012\b\b\fJ\u0004\b\b(s\u00a8\u0006t"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/JumpCircle;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "colorsApply", "()Z", "isNormal", "isLeeches", "", "fadeOutSeconds", "()F", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "onDisable", "Lrtx/kimiko/api/events/impl/player/JumpEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onJump", "(Lrtx/kimiko/api/events/impl/player/JumpEvent;)V", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "Lnet/minecraft/Entity;", "entity", "addCircleForEntity", "(Lnet/minecraft/Entity;)V", "Lnet/minecraft/Framebuffer;", "renderTarget", "Lorg/joml/Matrix4f;", "positionMatrix", "projectionMatrix", "bobFreeProjectionMatrix", "Lnet/minecraft/Camera;", "camera", "onAfterWorld", "(Lnet/minecraft/Framebuffer;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/Camera;)V", "", "now", "maxTimeValue", "maxRadius", "STD", "renderLeeches", "(Lnet/minecraft/Framebuffer;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/Camera;JFF)V", "isStaticPalette", "MAX", "getEasing", "(F)F", "", "index", "rawAlpha", "getColor", "(IF)I", "", "Lrtx/kimiko/api/modules/impl/Visuals/JumpCircle$JumpRenderer;", "circles", "Ljava/util/List;", "invViewProj", "Lorg/joml/Matrix4f;", "", "uniformScratch", "[F", "soulsScratch", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "generalSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "style", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "maxTime", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "range", "easingMode", "leechSeparator", "leechCount", "leechChaos", "leechWidth", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "leechGlowEnabled", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "leechGlow", "leechGlowHeight", "leechCore", "leechSparks", "distortSeparator", "distortStrength", "distortThickness", "distortSaturation", "distortWarp", "distortWarpStrength", "glowSeparator", "distortGlow", "distortGlowHeight", "distortGlowWidth", "distortGlowTint", "distortGlowAlpha", "tintSeparator", "distortTint", "distortTintStrength", "colorSeparator", "colorMode", "useSecondColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customSecondColor", "moduleFade", "F", "Companion", "JumpRenderer", "Lrtx/kimiko/api/liteapi/Feature;", "jumpeffects", "rtx.kimiko:kimiko"})
public final class JumpCircle
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final List<JumpRenderer> circles = new ArrayList();
    @NotNull
    private final Matrix4f invViewProj = new Matrix4f();
    @NotNull
    private final float[] uniformScratch = new float[476];
    @NotNull
    private final float[] soulsScratch = new float[412];
    @NotNull
    private final SeparatorSetting generalSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Основное"));
    @NotNull
    private final ModeSetting style;
    @NotNull
    private final NumberSetting maxTime;
    @NotNull
    private final NumberSetting range;
    @NotNull
    private final ModeSetting easingMode;
    @NotNull
    private final SeparatorSetting leechSeparator;
    @NotNull
    private final NumberSetting leechCount;
    @NotNull
    private final NumberSetting leechChaos;
    @NotNull
    private final NumberSetting leechWidth;
    @NotNull
    private final BooleanSetting leechGlowEnabled;
    @NotNull
    private final NumberSetting leechGlow;
    @NotNull
    private final NumberSetting leechGlowHeight;
    @NotNull
    private final NumberSetting leechCore;
    @NotNull
    private final BooleanSetting leechSparks;
    @NotNull
    private final SeparatorSetting distortSeparator;
    @NotNull
    private final NumberSetting distortStrength;
    @NotNull
    private final NumberSetting distortThickness;
    @NotNull
    private final NumberSetting distortSaturation;
    @NotNull
    private final BooleanSetting distortWarp;
    @NotNull
    private final NumberSetting distortWarpStrength;
    @NotNull
    private final SeparatorSetting glowSeparator;
    @NotNull
    private final BooleanSetting distortGlow;
    @NotNull
    private final NumberSetting distortGlowHeight;
    @NotNull
    private final NumberSetting distortGlowWidth;
    @NotNull
    private final NumberSetting distortGlowTint;
    @NotNull
    private final NumberSetting distortGlowAlpha;
    @NotNull
    private final SeparatorSetting tintSeparator;
    @NotNull
    private final BooleanSetting distortTint;
    @NotNull
    private final NumberSetting distortTintStrength;
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
    private float moduleFade;
    private static final int CLIENT_COLOR_FIRST = ColorEngine.rgba(127, 242, 255, 255);
    private static final int CLIENT_COLOR_SECOND = ColorEngine.rgba(255, 50, 150, 255);
    private static final int DARK_SECOND_COLOR = new Color(16, 16, 16, 75).getRGB();
    @NotNull
    private static final String MODE_NORMAL = "Обычный";
    @NotNull
    private static final String MODE_LEECHES = "Разлом";
    @NotNull
    private static final String COLOR_RAINBOW = "Радуга";
    @NotNull
    private static final String COLOR_CLIENT = "Клиент";
    @NotNull
    private static final String COLOR_CUSTOM = "Свой";
    @NotNull
    private static final String ANIMATION_NORMAL = "Обычная";
    @NotNull
    private static final String ANIMATION_ELASTIC = "Эластичная";
    @NotNull
    private static final String ANIMATION_BACK_OUT = "Назад";
    private static final double WOBBLE_PERIOD = 628.3185307179587;
    private static final float GLOW_SMOKE_INTENSITY = 0.6f;
    @JvmField
    @Nullable
    public static JumpCircle INSTANCE;

    public JumpCircle() {
        super("Jump Effects", "Рисует эффекты разлома при прыжке.", Category.VISUALS);
        String[] stringArray = new String[]{MODE_NORMAL, MODE_LEECHES};
        this.style = (ModeSetting)this.register((Setting)new ModeSetting("Режим", "Обычная волна искажения или неоновый разлом.", MODE_NORMAL, stringArray));
        this.maxTime = (NumberSetting)this.register((Setting)new NumberSetting("Время", "Время жизни круга.", 1950.0, 500.0, 5000.0, 50.0));
        this.range = (NumberSetting)this.register((Setting)new NumberSetting("Размер", "Размер круга.", 1.0, 0.5, 3.0, 0.1));
        stringArray = new String[]{ANIMATION_NORMAL, ANIMATION_ELASTIC, ANIMATION_BACK_OUT};
        this.easingMode = (ModeSetting)this.register((Setting)new ModeSetting("Анимация", "Анимация раскрытия круга.", ANIMATION_ELASTIC, stringArray).visibleWhen(() -> JumpCircle.easingMode$lambda$0(this)));
        this.leechSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting(MODE_LEECHES).visible(() -> JumpCircle.leechSeparator$lambda$0(this)));
        this.leechCount = (NumberSetting)this.register((Setting)new NumberSetting("Количество", "Сколько элементов разлома появляется из точки приземления.", 10.0, 4.0, 16.0, 1.0).visibleWhen(() -> JumpCircle.leechCount$lambda$0(this)));
        this.leechChaos = (NumberSetting)this.register((Setting)new NumberSetting("Хаос", "Насколько криво и хаотично извивается разлом.", 1.0, 0.3, 2.0, 0.1).visibleWhen(() -> JumpCircle.leechChaos$lambda$0(this)));
        this.leechWidth = (NumberSetting)this.register((Setting)new NumberSetting("Толщина тела", "Толщина элементов разлома.", 1.0, 0.5, 2.0, 0.1).visibleWhen(() -> JumpCircle.leechWidth$lambda$0(this)));
        this.leechGlowEnabled = (BooleanSetting)this.register((Setting)new BooleanSetting("Свечение", "Неоновый ореол вокруг разлома.", true).visibleWhen(() -> JumpCircle.leechGlowEnabled$lambda$0(this)));
        this.leechGlow = (NumberSetting)this.register((Setting)new NumberSetting("Сила свечения", "Сила неонового ореола разлома.", 1.0, 0.1, 2.5, 0.1).visibleWhen(() -> JumpCircle.leechGlow$lambda$0(this)));
        this.leechGlowHeight = (NumberSetting)this.register((Setting)new NumberSetting("Высота столба", "Высота объёмного столба света над разломом.", 30.0, 10.0, 100.0, 1.0).visibleWhen(() -> JumpCircle.leechGlowHeight$lambda$0(this)));
        this.leechCore = (NumberSetting)this.register((Setting)new NumberSetting("Белое ядро", "Насколько белая сердцевина разлома: 0% цвет градиента, 100% чисто белая.", 100.0, 0.0, 100.0, 5.0).visibleWhen(() -> JumpCircle.leechCore$lambda$0(this)));
        this.leechSparks = (BooleanSetting)this.register((Setting)new BooleanSetting("Искры", "Мелкие искорки вокруг разлома.", true).visibleWhen(() -> JumpCircle.leechSparks$lambda$0(this)));
        this.distortSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Искажение").visible(() -> JumpCircle.distortSeparator$lambda$0(this)));
        this.distortStrength = (NumberSetting)this.register((Setting)new NumberSetting("Сила искажения", "Насколько сильно волна искривляет мир.", 3.0, 0.2, 3.0, 0.1).visibleWhen(() -> JumpCircle.distortStrength$lambda$0(this)));
        this.distortThickness = (NumberSetting)this.register((Setting)new NumberSetting("Толщина", "Толщина кольца волны (относительно радиуса).", 0.6, 0.3, 0.8, 0.05).visibleWhen(() -> JumpCircle.distortThickness$lambda$0(this)));
        this.distortSaturation = (NumberSetting)this.register((Setting)new NumberSetting("Насыщенность", "Насыщенность цвета внутри ряби: -1 серый, 0 обычный, +1 яркий.", 1.0, -1.0, 1.0, 0.05).visibleWhen(() -> JumpCircle.distortSaturation$lambda$0(this)));
        this.distortWarp = (BooleanSetting)this.register((Setting)new BooleanSetting("Турбулентность", "Добавляет волнистое искривление мира внутри ряби.", true).visibleWhen(() -> JumpCircle.distortWarp$lambda$0(this)));
        this.distortWarpStrength = (NumberSetting)this.register((Setting)new NumberSetting("Сила турбулентности", "Сила внутреннего волнистого искривления.", 0.9, 0.1, 2.0, 0.1).visibleWhen(() -> JumpCircle.distortWarpStrength$lambda$0(this)));
        this.glowSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Свечение").visible(() -> JumpCircle.glowSeparator$lambda$0(this)));
        this.distortGlow = (BooleanSetting)this.register((Setting)new BooleanSetting("Свечение", "Светящийся дым, поднимающийся из кольца и испаряющийся вверх.", true).visibleWhen(() -> JumpCircle.distortGlow$lambda$0(this)));
        this.distortGlowHeight = (NumberSetting)this.register((Setting)new NumberSetting("Высота свечения", "Высота поднимающейся колонны дыма.", 45.0, 15.0, 45.0, 1.0).visibleWhen(() -> JumpCircle.distortGlowHeight$lambda$0(this)));
        this.distortGlowWidth = (NumberSetting)this.register((Setting)new NumberSetting("Ширина свечения", "Толщина дымовой стенки.", 30.0, 10.0, 30.0, 1.0).visibleWhen(() -> JumpCircle.distortGlowWidth$lambda$0(this)));
        this.distortGlowTint = (NumberSetting)this.register((Setting)new NumberSetting("Сила цвета свечения", "Насколько сильно окрашивается свечение: 30% слабо, 100% полный цвет.", 100.0, 30.0, 100.0, 1.0).visibleWhen(() -> JumpCircle.distortGlowTint$lambda$0(this)));
        this.distortGlowAlpha = (NumberSetting)this.register((Setting)new NumberSetting("Прозрачность свечения", "Непрозрачность свечения: 20% слабо, 80% сильно.", 80.0, 20.0, 80.0, 1.0).visibleWhen(() -> JumpCircle.distortGlowAlpha$lambda$0(this)));
        this.tintSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Подкраска").visible(() -> JumpCircle.tintSeparator$lambda$0(this)));
        this.distortTint = (BooleanSetting)this.register((Setting)new BooleanSetting("Подкрашивать цветом", "Подкрашивает волну искажения цветами градиента ниже.", true).visibleWhen(() -> JumpCircle.distortTint$lambda$0(this)));
        this.distortTintStrength = (NumberSetting)this.register((Setting)new NumberSetting("Сила цвета", "Насколько сильно окрашивается волна: 0% нет, 100% полный цвет.", 100.0, 0.0, 100.0, 1.0).visibleWhen(() -> JumpCircle.distortTintStrength$lambda$0(this)));
        this.colorSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Цвет").visible(() -> JumpCircle.colorSeparator$lambda$0(this)));
        stringArray = new String[]{COLOR_RAINBOW, COLOR_CLIENT, COLOR_CUSTOM};
        this.colorMode = (ModeSetting)this.register((Setting)new ModeSetting("Режим цвета", "Режим цвета круга.", COLOR_CLIENT, stringArray));
        this.useSecondColor = (BooleanSetting)this.register((Setting)new BooleanSetting("Второй цвет", "Использовать второй свой цвет.", false));
        this.customColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет", "Основной цвет круга.", new Color(255, 255, 255, 255)));
        this.customSecondColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет 2", "Второй цвет круга.", new Color(ColorEngine.lerpColor(-1, DARK_SECOND_COLOR, 0.7f), true)));
        INSTANCE = this;
        this.colorMode.visibleWhen(() -> JumpCircle._init_$lambda$0(this));
        this.useSecondColor.visibleWhen(() -> JumpCircle._init_$lambda$1(this));
        this.customColor.visibleWhen(() -> JumpCircle._init_$lambda$2(this));
        this.customSecondColor.visibleWhen(() -> JumpCircle._init_$lambda$3(this));
        this.moduleFade = 1.0f;
    }

    private final boolean colorsApply() {
        return this.isLeeches() || this.distortTint.getValue() || this.distortGlow.getValue();
    }

    private final boolean isNormal() {
        return this.style.is(MODE_NORMAL);
    }

    private final boolean isLeeches() {
        return this.style.is(MODE_LEECHES);
    }

    @Override
    public float fadeOutSeconds() {
        return 0.5f;
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onDisable() {
        this.circles.clear();
        JumpDistortRenderer.clear();
        JumpSoulsRenderer.clear();
    }

    @EventHandler
    private final void onJump(JumpEvent event) {
        ClientPlayerEntity player = this.mc.player;
        if (!this.isEnabled() || player == null || !Intrinsics.areEqual((Object)event.getPlayer(), (Object)player)) {
            return;
        }
        this.addCircleForEntity((Entity)player);
    }

    @EventHandler
    private final void onWorldRender(WorldRenderEvent event) {
        if (this.circles.isEmpty()) {
            return;
        }
        float maxTimeValue = Math.max(1.0f, this.maxTime.getFloat());
        int n = ((Collection)this.circles).size() + -1;
        if (0 <= n) {
            do {
                int i;
                JumpRenderer circle;
                if (!((circle = this.circles.get(i = n--)).getDeltaTime(maxTimeValue) > 1.0f) && JumpCircle.Companion.isFiniteAndSafe(circle.getPos().x, circle.getPos().y, circle.getPos().z)) continue;
                this.circles.remove(i);
            } while (0 <= n);
        }
    }

    private final void addCircleForEntity(Entity entity) {
        Vec3d vec3d2 = MathUtils.interpolate(entity).add(0.0, 0.01, 0.0);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"add(...)");
        Vec3d pos = vec3d2;
        if (!JumpCircle.Companion.isFiniteAndSafe(pos.x, pos.y, pos.z)) {
            return;
        }
        this.circles.add(new JumpRenderer(pos));
    }

    public final void onAfterWorld(@Nullable Framebuffer renderTarget, @Nullable Matrix4f positionMatrix, @Nullable Matrix4f projectionMatrix, @Nullable Matrix4f bobFreeProjectionMatrix, @Nullable Camera camera) {
        if (!this.isVisuallyActive() || renderTarget == null || camera == null || positionMatrix == null || projectionMatrix == null || this.circles.isEmpty()) {
            return;
        }
        this.moduleFade = this.visualAlpha();
        if (renderTarget.textureWidth <= 0 || renderTarget.textureHeight <= 0) {
            return;
        }
        long now = System.currentTimeMillis();
        float maxTimeValue = Math.max(1.0f, this.maxTime.getFloat());
        float maxRadius = (float)((double)this.range.getFloat() * 0.75);
        if (this.isLeeches()) {
            this.renderLeeches(renderTarget, positionMatrix, projectionMatrix, camera, now, maxTimeValue, maxRadius);
            return;
        }
        float ringWidth = Math.max(0.15f, maxRadius * this.distortThickness.getFloat() / 2.0f);
        float baseAmp = 0.018f * this.distortStrength.getFloat() * this.moduleFade;
        float aspect = (float)renderTarget.textureWidth / (float)Math.max(1, renderTarget.textureHeight);
        boolean tintOn = this.distortTint.getValue();
        boolean glowOn = this.distortGlow.getValue();
        boolean colorsNeeded = tintOn || glowOn;
        Vec3d vec3d2 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d cam = vec3d2;
        this.invViewProj.set((Matrix4fc)projectionMatrix).mul((Matrix4fc)positionMatrix).invert();
        float[] data = this.uniformScratch;
        int count = 0;
        float gradientIndex = 0.0f;
        boolean staticPalette = colorsNeeded && this.isStaticPalette();
        int staticColor = staticPalette ? this.getColor(0, 1.0f) : 0;
        for (JumpRenderer circle : this.circles) {
            if (count >= 16) break;
            float progress = circle.getDeltaTime(maxTimeValue);
            if (progress < 0.0f || progress >= 1.0f || !JumpCircle.Companion.isFiniteAndSafe(circle.getPos().x, circle.getPos().y, circle.getPos().z)) continue;
            float eased = this.getEasing(progress);
            float fadeInRaw = JumpCircle.Companion.clamp(progress / 0.18f, 0.0f, 1.0f);
            float fadeIn = fadeInRaw * fadeInRaw * (3.0f - 2.0f * fadeInRaw);
            float fadeOut = JumpCircle.Companion.clamp((1.0f - progress) / 0.3f, 0.0f, 1.0f);
            float env = fadeIn * fadeOut;
            float amp = baseAmp * env;
            int base = 28 + count * 12;
            data[base] = (float)(circle.getPos().x - cam.x);
            data[base + 1] = (float)(circle.getPos().y - cam.y);
            data[base + 2] = (float)(circle.getPos().z - cam.z);
            data[base + 3] = eased * maxRadius;
            data[base + 4] = ringWidth;
            data[base + 5] = amp;
            data[base + 6] = maxRadius;
            data[base + 7] = env;
            data[base + 8] = circle.getSeed();
            data[base + 9] = 0.0f;
            data[base + 10] = 0.0f;
            data[base + 11] = 0.0f;
            if (colorsNeeded) {
                int cbase = 220 + count * 4 * 4;
                if (staticPalette) {
                    JumpCircle.Companion.putColor(data, cbase, staticColor);
                    JumpCircle.Companion.putColor(data, cbase + 4, staticColor);
                    JumpCircle.Companion.putColor(data, cbase + 8, staticColor);
                    JumpCircle.Companion.putColor(data, cbase + 12, staticColor);
                } else {
                    int idx = (int)gradientIndex;
                    JumpCircle.Companion.putColor(data, cbase, this.getColor(idx, 1.0f));
                    JumpCircle.Companion.putColor(data, cbase + 4, this.getColor(90 + idx, 1.0f));
                    JumpCircle.Companion.putColor(data, cbase + 8, this.getColor(180 + idx, 1.0f));
                    JumpCircle.Companion.putColor(data, cbase + 12, this.getColor(270 + idx, 1.0f));
                }
            }
            gradientIndex += 45.0f * (1.0f - progress);
            ++count;
        }
        if (count == 0) {
            return;
        }
        data[0] = count;
        data[1] = aspect;
        double wobbleSeconds = (double)now / 1000.0;
        wobbleSeconds -= Math.floor(wobbleSeconds / 628.3185307179587) * 628.3185307179587;
        data[2] = (float)wobbleSeconds;
        data[3] = this.distortWarp.getValue() ? 0.01f * this.distortWarpStrength.getFloat() : 0.0f;
        data[4] = tintOn ? JumpCircle.Companion.clamp(this.distortTintStrength.getFloat() / 100.0f, 0.0f, 1.0f) : 0.0f;
        data[5] = JumpCircle.Companion.clamp(1.0f + this.distortSaturation.getFloat(), 0.0f, 2.0f);
        data[6] = glowOn ? 1.0f : 0.0f;
        data[7] = 0.6f;
        data[8] = JumpCircle.Companion.clamp(this.distortGlowHeight.getFloat() / 100.0f, 0.05f, 5.0f);
        data[9] = JumpCircle.Companion.clamp(this.distortGlowWidth.getFloat() / 100.0f, 0.05f, 5.0f);
        data[10] = JumpCircle.Companion.clamp(this.distortGlowTint.getFloat() / 100.0f, 0.0f, 1.0f);
        data[11] = JumpCircle.Companion.clamp(this.distortGlowAlpha.getFloat() / 100.0f, 0.0f, 1.0f);
        this.invViewProj.get(data, 12);
        JumpDistortRenderer.apply(renderTarget, data);
    }

    @Protect(value=Level.STD)
    private final void renderLeeches(Framebuffer renderTarget, Matrix4f positionMatrix, Matrix4f projectionMatrix, Camera camera, long now, float maxTimeValue, float maxRadius) {
        Vec3d vec3d2 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d cam = vec3d2;
        this.invViewProj.set((Matrix4fc)projectionMatrix).mul((Matrix4fc)positionMatrix).invert();
        float[] data = this.soulsScratch;
        int count = 0;
        float gradientIndex = 0.0f;
        boolean staticPalette = this.isStaticPalette();
        int staticColor = staticPalette ? this.getColor(0, 1.0f) : 0;
        for (JumpRenderer circle : this.circles) {
            if (count >= 16) break;
            float progress = circle.getDeltaTime(maxTimeValue);
            if (progress < 0.0f || progress >= 1.0f || !JumpCircle.Companion.isFiniteAndSafe(circle.getPos().x, circle.getPos().y, circle.getPos().z)) continue;
            float fadeInRaw = JumpCircle.Companion.clamp(progress / 0.12f, 0.0f, 1.0f);
            float fadeIn = fadeInRaw * fadeInRaw * (3.0f - 2.0f * fadeInRaw);
            float fadeOut = JumpCircle.Companion.clamp((1.0f - progress) / 0.08f, 0.0f, 1.0f);
            float env = fadeIn * fadeOut;
            int base = 28 + count * 8;
            data[base] = (float)(circle.getPos().x - cam.x);
            data[base + 1] = (float)(circle.getPos().y - cam.y);
            data[base + 2] = (float)(circle.getPos().z - cam.z);
            data[base + 3] = progress;
            data[base + 4] = maxRadius;
            data[base + 5] = circle.getSeed();
            data[base + 6] = env;
            data[base + 7] = maxRadius * this.leechGlowHeight.getFloat() / 100.0f * 1.2f;
            int cbase = 156 + count * 4 * 4;
            if (staticPalette) {
                JumpCircle.Companion.putColor(data, cbase, staticColor);
                JumpCircle.Companion.putColor(data, cbase + 4, staticColor);
                JumpCircle.Companion.putColor(data, cbase + 8, staticColor);
                JumpCircle.Companion.putColor(data, cbase + 12, staticColor);
            } else {
                int idx = (int)gradientIndex;
                JumpCircle.Companion.putColor(data, cbase, this.getColor(idx, 1.0f));
                JumpCircle.Companion.putColor(data, cbase + 4, this.getColor(90 + idx, 1.0f));
                JumpCircle.Companion.putColor(data, cbase + 8, this.getColor(180 + idx, 1.0f));
                JumpCircle.Companion.putColor(data, cbase + 12, this.getColor(270 + idx, 1.0f));
            }
            gradientIndex += 45.0f * (1.0f - progress);
            ++count;
        }
        if (count == 0) {
            return;
        }
        double wobbleSeconds = (double)now / 1000.0;
        wobbleSeconds -= Math.floor(wobbleSeconds / 628.3185307179587) * 628.3185307179587;
        data[0] = count;
        data[1] = (float)wobbleSeconds;
        data[2] = this.leechCount.getFloat();
        data[3] = this.leechChaos.getFloat();
        data[4] = this.leechWidth.getFloat();
        data[5] = this.leechGlowEnabled.getValue() ? this.leechGlow.getFloat() : 0.0f;
        data[6] = this.leechSparks.getValue() ? 1.0f : 0.0f;
        data[7] = JumpCircle.Companion.clamp(this.leechCore.getFloat() / 100.0f, 0.0f, 1.0f);
        this.invViewProj.get(data, 12);
        JumpSoulsRenderer.apply(renderTarget, data);
    }

    private final boolean isStaticPalette() {
        if (!this.colorMode.is(COLOR_CUSTOM)) {
            return false;
        }
        if (!this.useSecondColor.getValue()) {
            return true;
        }
        return this.customColor.getColor() == this.customSecondColor.getColor();
    }

    @Protect(value=Level.MAX)
    private final float getEasing(float value) {
        float clamped = MathHelper.clamp((float)value, (float)0.0f, (float)1.0f);
        String string = this.easingMode.getSelected();
        return Intrinsics.areEqual((Object)string, (Object)ANIMATION_ELASTIC) ? JumpCircle.Companion.elasticOut(clamped) : (Intrinsics.areEqual((Object)string, (Object)ANIMATION_BACK_OUT) ? JumpCircle.Companion.backOut(clamped) : clamped);
    }

    @Protect(value=Level.MAX)
    private final int getColor(int index, float rawAlpha) {
        float alpha = rawAlpha * this.moduleFade;
        if (this.colorMode.is(COLOR_RAINBOW)) {
            return JumpCircle.Companion.rainbow(8, index, 1.0f, 1.0f, alpha);
        }
        int firstColor = 0;
        int secondColor = 0;
        if (this.colorMode.is(COLOR_CLIENT)) {
            int[] palette = ClientPalette.colors();
            if (palette != null && palette.length >= 2) {
                return ColorEngine.multAlpha(JumpCircle.Companion.paletteFade(8, index, palette), alpha);
            }
            InterfaceModule iface = InterfaceModule.Companion.getInstance();
            if (iface != null) {
                firstColor = iface.clientPrimaryColorOpaque();
                secondColor = iface.usesSecondClientColor() ? iface.clientSecondaryColorOpaque() : firstColor;
            } else {
                firstColor = JumpCircle.Companion.getClientColor();
                secondColor = ColorEngine.lerpColor(firstColor, DARK_SECOND_COLOR, 0.7f);
            }
        } else {
            firstColor = this.customColor.getColor();
            int n = secondColor = this.useSecondColor.getValue() ? this.customSecondColor.getColor() : this.customColor.getColor();
        }
        if (firstColor == secondColor) {
            return ColorEngine.multAlpha(firstColor, alpha);
        }
        return ColorEngine.multAlpha(JumpCircle.Companion.fade(8, index, firstColor, secondColor), alpha);
    }

    private static final Boolean easingMode$lambda$0(JumpCircle this$0) {
        return this$0.isNormal();
    }

    private static final Boolean leechSeparator$lambda$0(JumpCircle this$0) {
        return this$0.isLeeches();
    }

    private static final Boolean leechCount$lambda$0(JumpCircle this$0) {
        return this$0.isLeeches();
    }

    private static final Boolean leechChaos$lambda$0(JumpCircle this$0) {
        return this$0.isLeeches();
    }

    private static final Boolean leechWidth$lambda$0(JumpCircle this$0) {
        return this$0.isLeeches();
    }

    private static final Boolean leechGlowEnabled$lambda$0(JumpCircle this$0) {
        return this$0.isLeeches();
    }

    private static final Boolean leechGlow$lambda$0(JumpCircle this$0) {
        return this$0.isLeeches() && this$0.leechGlowEnabled.getValue();
    }

    private static final Boolean leechGlowHeight$lambda$0(JumpCircle this$0) {
        return this$0.isLeeches() && this$0.leechGlowEnabled.getValue();
    }

    private static final Boolean leechCore$lambda$0(JumpCircle this$0) {
        return this$0.isLeeches();
    }

    private static final Boolean leechSparks$lambda$0(JumpCircle this$0) {
        return this$0.isLeeches();
    }

    private static final Boolean distortSeparator$lambda$0(JumpCircle this$0) {
        return this$0.isNormal();
    }

    private static final Boolean distortStrength$lambda$0(JumpCircle this$0) {
        return this$0.isNormal();
    }

    private static final Boolean distortThickness$lambda$0(JumpCircle this$0) {
        return this$0.isNormal();
    }

    private static final Boolean distortSaturation$lambda$0(JumpCircle this$0) {
        return this$0.isNormal();
    }

    private static final Boolean distortWarp$lambda$0(JumpCircle this$0) {
        return this$0.isNormal();
    }

    private static final Boolean distortWarpStrength$lambda$0(JumpCircle this$0) {
        return this$0.isNormal() && this$0.distortWarp.getValue();
    }

    private static final Boolean glowSeparator$lambda$0(JumpCircle this$0) {
        return this$0.isNormal();
    }

    private static final Boolean distortGlow$lambda$0(JumpCircle this$0) {
        return this$0.isNormal();
    }

    private static final Boolean distortGlowHeight$lambda$0(JumpCircle this$0) {
        return this$0.isNormal() && this$0.distortGlow.getValue();
    }

    private static final Boolean distortGlowWidth$lambda$0(JumpCircle this$0) {
        return this$0.isNormal() && this$0.distortGlow.getValue();
    }

    private static final Boolean distortGlowTint$lambda$0(JumpCircle this$0) {
        return this$0.isNormal() && this$0.distortGlow.getValue();
    }

    private static final Boolean distortGlowAlpha$lambda$0(JumpCircle this$0) {
        return this$0.isNormal() && this$0.distortGlow.getValue();
    }

    private static final Boolean tintSeparator$lambda$0(JumpCircle this$0) {
        return this$0.isNormal();
    }

    private static final Boolean distortTint$lambda$0(JumpCircle this$0) {
        return this$0.isNormal();
    }

    private static final Boolean distortTintStrength$lambda$0(JumpCircle this$0) {
        return this$0.isNormal() && this$0.distortTint.getValue();
    }

    private static final Boolean colorSeparator$lambda$0(JumpCircle this$0) {
        return this$0.colorsApply();
    }

    private static final Boolean _init_$lambda$0(JumpCircle this$0) {
        return this$0.colorsApply();
    }

    private static final Boolean _init_$lambda$1(JumpCircle this$0) {
        return this$0.colorsApply() && this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean _init_$lambda$2(JumpCircle this$0) {
        return this$0.colorsApply() && this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean _init_$lambda$3(JumpCircle this$0) {
        return this$0.colorsApply() && this$0.colorMode.is(COLOR_CUSTOM) && this$0.useSecondColor.getValue();
    }

    @JvmStatic
    @Nullable
    public static final JumpCircle getInstance() {
        return Companion.getInstance();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0013\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J'\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\f\u0010\rJ'\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0019\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ7\u0010!\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\bH\u0002\u00a2\u0006\u0004\b!\u0010\"J/\u0010%\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b%\u0010&J'\u0010)\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010(\u001a\u00020'H\u0002\u00a2\u0006\u0004\b)\u0010*J'\u00100\u001a\u00020/2\u0006\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020+2\u0006\u0010.\u001a\u00020+H\u0002\u00a2\u0006\u0004\b0\u00101J\u0017\u00100\u001a\u00020/2\u0006\u0010\u0016\u001a\u00020+H\u0002\u00a2\u0006\u0004\b0\u00102R\u0014\u00103\u001a\u00020\u00108\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b3\u00104R\u0014\u00105\u001a\u00020\u00108\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00104R\u0014\u00106\u001a\u00020\u00108\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b6\u00104R\u0014\u00108\u001a\u0002078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010:\u001a\u0002078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b:\u00109R\u0014\u0010;\u001a\u0002078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b;\u00109R\u0014\u0010<\u001a\u0002078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b<\u00109R\u0014\u0010=\u001a\u0002078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b=\u00109R\u0014\u0010>\u001a\u0002078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b>\u00109R\u0014\u0010?\u001a\u0002078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b?\u00109R\u0014\u0010@\u001a\u0002078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u00109R\u0014\u0010A\u001a\u00020+8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bA\u0010BR\u0014\u0010C\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bC\u0010DR\u001d\u0010F\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\bE\u00a2\u0006\u0006\n\u0004\bF\u0010G\u00a8\u0006H"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/JumpCircle.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/JumpCircle;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/JumpCircle;", "", "v", "min", "max", "clamp", "(FFF)F", "", "data", "", "offset", "color", "", "putColor", "([FII)V", "value", "elasticOut", "(F)F", "backOut", "getClientColor", "()I", "speed", "index", "saturation", "brightness", "alpha", "rainbow", "(IIFFF)I", "first", "second", "fade", "(IIII)I", "", "palette", "paletteFade", "(II[I)I", "", "x", "y", "z", "", "isFiniteAndSafe", "(DDD)Z", "(D)Z", "CLIENT_COLOR_FIRST", "I", "CLIENT_COLOR_SECOND", "DARK_SECOND_COLOR", "", "MODE_NORMAL", "Ljava/lang/String;", "MODE_LEECHES", "COLOR_RAINBOW", "COLOR_CLIENT", "COLOR_CUSTOM", "ANIMATION_NORMAL", "ANIMATION_ELASTIC", "ANIMATION_BACK_OUT", "WOBBLE_PERIOD", "D", "GLOW_SMOKE_INTENSITY", "F", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/JumpCircle;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final JumpCircle getInstance() {
            JumpCircle module = ModuleManager.Companion.get().get(JumpCircle.class);
            JumpCircle jumpCircle = module;
            if (jumpCircle == null) {
                jumpCircle = INSTANCE;
            }
            return jumpCircle;
        }

        private final float clamp(float v, float min, float max) {
            return Math.max(min, Math.min(max, v));
        }

        private final void putColor(float[] data, int offset, int color) {
            data[offset] = (float)(color >> 16 & 0xFF) / 255.0f;
            data[offset + 1] = (float)(color >> 8 & 0xFF) / 255.0f;
            data[offset + 2] = (float)(color & 0xFF) / 255.0f;
        }

        private final float elasticOut(float value) {
            if (value == 0.0f || value == 1.0f) {
                return value;
            }
            return (float)(Math.pow(2.0, -10.0f * value) * Math.sin((value * 10.0f - 0.75f) * 2.0943952f) + 1.0);
        }

        private final float backOut(float value) {
            return (float)(1.0 + 2.70158 * Math.pow(value - 1.0f, 3.0) + 1.70158 * Math.pow(value - 1.0f, 2.0));
        }

        private final int getClientColor() {
            float wave = (MathHelper.sin((double)((float)System.currentTimeMillis() / 520.0f)) + 1.0f) / 2.0f;
            return ColorEngine.lerpColor(CLIENT_COLOR_FIRST, CLIENT_COLOR_SECOND, wave);
        }

        private final int rainbow(int speed, int index, float saturation, float brightness, float alpha) {
            int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
            int rgb = ColorEngine.rainbow(angle, saturation, brightness);
            return ColorEngine.rgba(rgb >>> 16 & 0xFF, rgb >>> 8 & 0xFF, rgb & 0xFF, Math.round(this.clamp(alpha, 0.0f, 1.0f) * 255.0f));
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

        private final boolean isFiniteAndSafe(double x, double y, double z) {
            return this.isFiniteAndSafe(x) && this.isFiniteAndSafe(y) && this.isFiniteAndSafe(z);
        }

        private final boolean isFiniteAndSafe(double value) {
            return Double.isFinite(value) && Math.abs(value) <= 3.0E7;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0014"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/JumpCircle$JumpRenderer;", "", "Lnet/minecraft/Vec3d;", "pos", "<init>", "(Lnet/minecraft/Vec3d;)V", "", "maxTime", "getDeltaTime", "(F)F", "Lnet/minecraft/Vec3d;", "getPos", "()Lnet/minecraft/Vec3d;", "", "time", "J", "seed", "F", "getSeed", "()F", "rtx.kimiko:kimiko"})
    private static final class JumpRenderer {
        @NotNull
        private final Vec3d pos;
        private final long time;
        private final float seed;

        public JumpRenderer(@NotNull Vec3d pos) {
            Intrinsics.checkNotNullParameter((Object)pos, (String)"pos");
            this.pos = pos;
            this.time = System.currentTimeMillis();
            this.seed = (float)(Math.random() * Math.PI * 2.0);
        }

        @NotNull
        public final Vec3d getPos() {
            return this.pos;
        }

        public final float getSeed() {
            return this.seed;
        }

        public final float getDeltaTime(float maxTime) {
            return (float)(System.currentTimeMillis() - this.time) / maxTime;
        }
    }
}

