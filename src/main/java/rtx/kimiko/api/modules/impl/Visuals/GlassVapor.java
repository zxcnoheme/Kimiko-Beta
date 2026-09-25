/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmOverloads
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.option.GameOptions
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Vector4f
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.option.GameOptions;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector4f;
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
import rtx.kimiko.utils.render.modules.post.glassvapor.GlassVaporRenderer;
import rtx.kimiko.utils.render.render2d.ClientPalette;

@Feature(value={"glassvapor"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00a8\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 y2\u00020\u0001:\u0002zyB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0014\u00a2\u0006\u0004\b\b\u0010\u0003JE\u0010\u0013\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b\u0013\u0010\u0014J'\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u008f\u0001\u0010.\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020\u00102\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b.\u0010/J/\u00103\u001a\u00020\u00102\u0006\u00100\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b3\u00104J'\u00105\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b5\u00106J\u007f\u0010?\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u00107\u001a\u00020\u00042\u0006\u00108\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u00042\u0006\u0010:\u001a\u00020\u00042\u0006\u0010;\u001a\u00020\u00042\u0006\u0010<\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u00042\u0006\u0010>\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020\u00102\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b?\u0010@J\u000f\u0010A\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\bA\u0010BJ\u0017\u0010D\u001a\u00020\u001c2\u0006\u0010C\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\bD\u0010ER\u0014\u0010G\u001a\u00020F8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010HR\u0014\u0010J\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010KR\u0014\u0010L\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u0010KR\u0014\u0010M\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bM\u0010KR\u0014\u0010N\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bN\u0010KR\u0014\u0010O\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bO\u0010KR\u0014\u0010P\u001a\u00020F8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bP\u0010HR\u0014\u0010Q\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bQ\u0010KR\u0014\u0010R\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bR\u0010KR\u0014\u0010S\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bS\u0010KR\u0014\u0010T\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bT\u0010KR\u0014\u0010U\u001a\u00020F8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bU\u0010HR\u0014\u0010V\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bV\u0010KR\u0014\u0010X\u001a\u00020W8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bX\u0010YR\u0014\u0010[\u001a\u00020Z8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b[\u0010\\R\u0014\u0010^\u001a\u00020]8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b^\u0010_R\u0014\u0010`\u001a\u00020]8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b`\u0010_R\u001a\u0010c\u001a\b\u0012\u0004\u0012\u00020b0a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bc\u0010dR\u0014\u0010f\u001a\u00020e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bf\u0010gR\u0014\u0010h\u001a\u00020\u000b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bh\u0010iR\u0014\u0010k\u001a\u00020j8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bk\u0010lR\u0014\u0010m\u001a\u00020j8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bm\u0010lR\u0018\u0010n\u001a\u0004\u0018\u00010\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bn\u0010oR\u0018\u0010p\u001a\u0004\u0018\u00010\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bp\u0010oR\u0016\u0010q\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bq\u0010rR\u0016\u0010s\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bs\u0010rR\u0016\u0010t\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bt\u0010uR\u0016\u0010v\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bv\u0010uR\u0016\u0010w\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bw\u0010uR\u0016\u0010x\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bx\u0010u\u00ca\u0001\u0010\b{\u0012\f\b|\u0012\b\b\fJ\u0004\b\b(}\u00a8\u0006~"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/GlassVapor;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "fadeOutSeconds", "()F", "", "onDisable", "Lnet/minecraft/Framebuffer;", "renderTarget", "Lorg/joml/Matrix4f;", "positionMatrix", "projectionMatrix", "Lnet/minecraft/Camera;", "camera", "", "simulate", "Lkotlin/jvm/JvmOverloads;", "onAfterWorld", "(Lnet/minecraft/Framebuffer;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/Camera;Z)V", "Lnet/minecraft/Vec3d;", "pos", "moving", "", "now", "spawnDrops", "(Lnet/minecraft/Vec3d;ZJ)V", "", "count", "cam", "", "wx", "wy", "wz", "ax", "ay", "az", "envIn", "halfExtent", "colorIndex", "paletteOn", "phase", "wobble", "shine", "far", "packEllipsoid", "(ILnet/minecraft/Vec3d;DDDFFFFFIZFFFF)I", "relX", "relY", "relZ", "projectCenter", "(FFFF)Z", "axisU", "(FFF)F", "cu", "cv", "lin", "env", "e1x", "e1y", "e2x", "e2y", "write", "(IFFFFFFFFIZFFF)I", "usesClientPalette", "()Z", "index", "getColor", "(I)I", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "dropsSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "density", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "spawnRate", "blobSize", "riseHeight", "lifetime", "glassSeparator", "distort", "ripple", "rim", "chroma", "colorSeparator", "tintStrength", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "colorMode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "useSecondColor", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customSecondColor", "", "Lrtx/kimiko/api/modules/impl/Visuals/GlassVapor$Drop;", "drops", "Ljava/util/List;", "", "data", "[F", "viewProj", "Lorg/joml/Matrix4f;", "Lorg/joml/Vector4f;", "centerClip", "Lorg/joml/Vector4f;", "axisClip", "lastPos", "Lnet/minecraft/Vec3d;", "lastSpawnPos", "nextBurstMs", "J", "seq", "centerU", "F", "centerV", "centerLin", "axisV", "Companion", "Drop", "Lrtx/kimiko/api/liteapi/Feature;", "value", "glassvapor", "rtx.kimiko:kimiko"})
public final class GlassVapor
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting dropsSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Капли"));
    @NotNull
    private final NumberSetting density = (NumberSetting)this.register((Setting)new NumberSetting("Плотность", "Как густо капли ложатся вдоль пути.", 5.0, 1.0, 12.0, 1.0));
    @NotNull
    private final NumberSetting spawnRate = (NumberSetting)this.register((Setting)new NumberSetting("Капель в секунду", "Сколько капель в секунду прорывается из земли.", 14.0, 4.0, 30.0, 1.0));
    @NotNull
    private final NumberSetting blobSize = (NumberSetting)this.register((Setting)new NumberSetting("Размер", "Размер капли жидкого стекла.", 0.3, 0.15, 0.55, 0.01));
    @NotNull
    private final NumberSetting riseHeight = (NumberSetting)this.register((Setting)new NumberSetting("Высота подъёма", "На какую высоту испаряется капля.", 1.6, 0.6, 3.0, 0.1));
    @NotNull
    private final NumberSetting lifetime = (NumberSetting)this.register((Setting)new NumberSetting("Время подъёма", "Время подъёма и испарения капли в миллисекундах.", 1600.0, 900.0, 3000.0, 50.0));
    @NotNull
    private final SeparatorSetting glassSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Стекло"));
    @NotNull
    private final NumberSetting distort = (NumberSetting)this.register((Setting)new NumberSetting("Искривление", "Сила линзы жидкого стекла.", 1.0, 0.2, 3.0, 0.1));
    @NotNull
    private final NumberSetting ripple = (NumberSetting)this.register((Setting)new NumberSetting("Рябь", "Сила шумовой ряби внутри стекла.", 1.0, 0.0, 3.0, 0.1));
    @NotNull
    private final NumberSetting rim = (NumberSetting)this.register((Setting)new NumberSetting("Блик", "Яркость кромки и бликов.", 0.7, 0.0, 1.5, 0.05));
    @NotNull
    private final NumberSetting chroma = (NumberSetting)this.register((Setting)new NumberSetting("Хрома", "Хроматическое расщепление на гранях.", 0.35, 0.0, 1.0, 0.05));
    @NotNull
    private final SeparatorSetting colorSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Цвет"));
    @NotNull
    private final NumberSetting tintStrength = (NumberSetting)this.register((Setting)new NumberSetting("Подкраска", "Сила подкраски стекла цветом.", 45.0, 0.0, 100.0, 1.0));
    @NotNull
    private final ModeSetting colorMode;
    @NotNull
    private final BooleanSetting useSecondColor;
    @NotNull
    private final ColorSetting customColor;
    @NotNull
    private final ColorSetting customSecondColor;
    @NotNull
    private final List<Drop> drops;
    @NotNull
    private final float[] data;
    @NotNull
    private final Matrix4f viewProj;
    @NotNull
    private final Vector4f centerClip;
    @NotNull
    private final Vector4f axisClip;
    @Nullable
    private Vec3d lastPos;
    @Nullable
    private Vec3d lastSpawnPos;
    private long nextBurstMs;
    private long seq;
    private float centerU;
    private float centerV;
    private float centerLin;
    private float axisV;
    @NotNull
    private static final String COLOR_RAINBOW = "Радуга";
    @NotNull
    private static final String COLOR_CLIENT = "Клиент";
    @NotNull
    private static final String COLOR_CUSTOM = "Свой";
    private static final int CLIENT_COLOR_FIRST = ColorEngine.rgba(127, 242, 255, 255);
    private static final int CLIENT_COLOR_SECOND = ColorEngine.rgba(255, 50, 150, 255);
    private static final int DARK_SECOND_COLOR = new Color(16, 16, 16, 75).getRGB();
    private static final float RADIUS_SCALE = 1.85f;
    private static final float NEAR = 0.05f;
    private static final int COLOR_INDEX_STEP = 30;
    @JvmField
    @Nullable
    public static GlassVapor INSTANCE;

    public GlassVapor() {
        super("Glass Vapor", "Жидкое стекло: капли вытягиваются из пола и испаряются вверх.", Category.VISUALS);
        String[] stringArray = new String[]{COLOR_RAINBOW, COLOR_CLIENT, COLOR_CUSTOM};
        this.colorMode = (ModeSetting)this.register((Setting)new ModeSetting("Режим цвета", "Режим цвета стекла.", COLOR_CLIENT, stringArray));
        this.useSecondColor = (BooleanSetting)this.register((Setting)new BooleanSetting("Второй цвет", "Использовать второй свой цвет.", false).visibleWhen(() -> GlassVapor.useSecondColor$lambda$0(this)));
        this.customColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет", "Основной свой цвет стекла.", new Color(160, 220, 255, 255)).visibleWhen(() -> GlassVapor.customColor$lambda$0(this)));
        this.customSecondColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет 2", "Второй свой цвет стекла.", new Color(ColorEngine.lerpColor(-1, DARK_SECOND_COLOR, 0.7f), true)).visibleWhen(() -> GlassVapor.customSecondColor$lambda$0(this)));
        this.drops = new ArrayList();
        this.data = new float[3856];
        this.viewProj = new Matrix4f();
        this.centerClip = new Vector4f();
        this.axisClip = new Vector4f();
        INSTANCE = this;
    }

    @Override
    public float fadeOutSeconds() {
        return 0.6f;
    }

    @Override
    protected void onDisable() {
        this.drops.clear();
        this.lastPos = null;
        this.lastSpawnPos = null;
        this.nextBurstMs = 0L;
        GlassVaporRenderer.clear();
    }

    @JvmOverloads
    public final void onAfterWorld(@Nullable Framebuffer renderTarget, @Nullable Matrix4f positionMatrix, @Nullable Matrix4f projectionMatrix, @Nullable Camera camera, boolean simulate) {
        ClientPlayerEntity player = this.mc.player;
        ClientWorld level = this.mc.world;
        if (renderTarget == null || camera == null || positionMatrix == null || projectionMatrix == null || player == null || level == null) {
            this.drops.clear();
            this.lastPos = null;
            this.lastSpawnPos = null;
            return;
        }
        long now = System.currentTimeMillis();
        Vec3d pos = MathUtils.interpolate((Entity)player);
        if (!(Double.isFinite(pos.x) && Double.isFinite(pos.y) && Double.isFinite(pos.z))) {
            return;
        }
        boolean onGround = player.isOnGround();
        boolean moving = false;
        Vec3d prevPos = this.lastPos;
        if (prevPos != null) {
            double dx = pos.x - prevPos.x;
            double dz = pos.z - prevPos.z;
            boolean bl = moving = onGround && dx * dx + dz * dz > 1.0E-6;
        }
        if (simulate) {
            this.lastPos = pos;
            if (this.isEnabled()) {
                this.spawnDrops(pos, moving, now);
            }
            this.drops.removeIf(d -> (float)(now - d.getSpawnMs()) >= d.getDelayMs() + d.getBurstMs() + d.getRiseMs());
        }
        if (this.drops.isEmpty()) {
            return;
        }
        this.viewProj.set((Matrix4fc)projectionMatrix).mul((Matrix4fc)positionMatrix);
        Vec3d vec3d2 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d cam = vec3d2;
        GameOptions gameOptions2 = this.mc.options;
        Intrinsics.checkNotNullExpressionValue((Object)gameOptions2, (String)"options");
        GameOptions options = gameOptions2;
        float far = Math.max(192.0f, (float)(((Number)options.getViewDistance().getValue()).intValue() + 1) * 16.0f);
        boolean paletteOn = this.usesClientPalette();
        Arrays.fill(this.data, 0.0f);
        int count = 0;
        float riseH = this.riseHeight.getFloat();
        int n = ((Collection)this.drops).size() + -1;
        if (0 <= n) {
            do {
                int i = n--;
                if (count >= 240) break;
                Drop d = this.drops.get(i);
                float age = now - d.getSpawnMs();
                if (age < d.getDelayMs()) continue;
                float dropBase = d.getDropR();
                float rx = 0.0f;
                float ry = 0.0f;
                float h = 0.0f;
                float env = 0.0f;
                float wobbleT = 0.0f;
                if (age < d.getDelayMs() + d.getBurstMs()) {
                    float b = GlassVapor.Companion.smooth((age - d.getDelayMs()) / d.getBurstMs());
                    rx = dropBase * b;
                    ry = rx * (0.55f + 0.3f * b);
                    h = ry * 0.55f;
                    env = GlassVapor.Companion.smooth(Math.min(b * 2.0f, 1.0f));
                    wobbleT = 0.15f * b;
                } else {
                    float r = (age - d.getDelayMs() - d.getBurstMs()) / d.getRiseMs();
                    rx = dropBase * (1.0f - 0.45f * r);
                    float stretchCurve = (float)Math.sin(Math.PI * (double)GlassVapor.Companion.smooth(Math.min(r * 1.2f, 1.0f)));
                    ry = rx * (0.85f + 1.15f * stretchCurve);
                    h = dropBase * 0.85f * 0.55f + riseH * d.getRiseMul() * (float)Math.pow(r, 1.3);
                    env = 1.0f - GlassVapor.Companion.smooth(MathHelper.clamp((float)((r - 0.72f) / 0.28f), (float)0.0f, (float)1.0f));
                    wobbleT = 0.15f + 0.85f * r;
                }
                if (env <= 0.01f || rx <= 0.01f) continue;
                float wobT = (float)now * 0.001f;
                float wx = (float)(Math.sin(wobT * d.getWobSpeed() + d.getPhase()) * (double)d.getWobAmp() * (double)wobbleT);
                float wz = (float)(Math.cos(wobT * d.getWobSpeed() * 0.83f + d.getPhase() * 1.7f) * (double)d.getWobAmp() * (double)wobbleT);
                float rxS = rx * 1.85f;
                float ryS = ry * 1.85f;
                count = this.packEllipsoid(count, cam, d.getX() + (double)wx, d.getY() + (double)h, d.getZ() + (double)wz, rxS, ryS, rxS, env, Math.max(rxS, ryS) * 0.55f, d.getColorIndex(), paletteOn, d.getPhase(), 0.1f, 1.0f, far);
            } while (0 <= n);
        }
        if (count == 0) {
            return;
        }
        this.data[0] = count;
        this.data[1] = (float)renderTarget.textureWidth / (float)Math.max(1, renderTarget.textureHeight);
        this.data[2] = (float)(now % 100000L) / 1000.0f;
        float moduleFade = this.visualAlpha();
        this.data[3] = 0.016f * this.distort.getFloat() * moduleFade;
        this.data[4] = this.rim.getFloat() * moduleFade;
        this.data[5] = MathHelper.clamp((float)(this.tintStrength.getFloat() / 100.0f), (float)0.0f, (float)1.0f) * moduleFade;
        this.data[6] = 0.008f * this.ripple.getFloat() * moduleFade;
        this.data[7] = 1.0f;
        this.data[8] = 0.05f;
        this.data[9] = far;
        this.data[10] = this.chroma.getFloat() * 0.6f;
        this.data[11] = paletteOn ? 1.0f : 0.0f;
        GlassVaporRenderer.apply(renderTarget, this.data);
    }

    public static /* synthetic */ void onAfterWorld$default(GlassVapor glassVapor, Framebuffer framebuffer2, Matrix4f matrix4f, Matrix4f matrix4f2, Camera camera2, boolean bl, int n, Object object) {
        if ((n & 0x10) != 0) {
            bl = true;
        }
        glassVapor.onAfterWorld(framebuffer2, matrix4f, matrix4f2, camera2, bl);
    }

    private final void spawnDrops(Vec3d pos, boolean moving, long now) {
        if (!moving) {
            return;
        }
        float spacing = MathHelper.clamp((float)(1.5f / Math.max(1.0f, this.density.getFloat())), (float)0.1f, (float)1.5f);
        Vec3d prevSpawn = this.lastSpawnPos;
        if (prevSpawn != null && prevSpawn.squaredDistanceTo(pos) < (double)(spacing * spacing)) {
            return;
        }
        this.lastSpawnPos = pos;
        long l = this.seq;
        this.seq = l + 1L;
        long seed = l;
        long minStep = (long)(1000.0f / Math.max(1.0f, this.spawnRate.getFloat()));
        long burstAt = Math.max(now + (long)(40.0f + 160.0f * GlassVapor.Companion.hashf(seed + 2L)), this.nextBurstMs);
        if (burstAt - now > 350L) {
            return;
        }
        this.nextBurstMs = burstAt + (long)((float)minStep * (0.85f + 0.3f * GlassVapor.Companion.hashf(seed + 11L)));
        Drop d = new Drop();
        d.setX(pos.x + (double)(GlassVapor.Companion.hashf(seed) - 0.5f) * 0.9);
        d.setY(pos.y + 0.02);
        d.setZ(pos.z + (double)(GlassVapor.Companion.hashf(seed + 1L) - 0.5f) * 0.9);
        d.setSpawnMs(now);
        d.setDelayMs(burstAt - now);
        d.setBurstMs(320.0f * (0.8f + 0.4f * GlassVapor.Companion.hashf(seed + 3L)));
        d.setRiseMs(this.lifetime.getFloat() * (0.85f + 0.3f * GlassVapor.Companion.hashf(seed + 4L)));
        d.setDropR(this.blobSize.getFloat() * (0.75f + 0.5f * GlassVapor.Companion.hashf(seed + 6L)));
        d.setColorIndex((int)(seed * (long)30 % 360L));
        d.setRiseMul(0.85f + GlassVapor.Companion.hashf(seed + 7L) * 0.35f);
        d.setWobAmp(0.1f + GlassVapor.Companion.hashf(seed + 8L) * 0.12f);
        d.setWobSpeed(1.6f + GlassVapor.Companion.hashf(seed + 9L) * 1.8f);
        d.setPhase(GlassVapor.Companion.hashf(seed + 10L) * ((float)Math.PI * 2));
        this.drops.add(d);
    }

    private final int packEllipsoid(int count, Vec3d cam, double wx, double wy, double wz, float ax, float ay, float az, float envIn, float halfExtent, int colorIndex, boolean paletteOn, float phase, float wobble, float shine, float far) {
        float env = envIn;
        float relX = (float)(wx - cam.x);
        float relY = (float)(wy - cam.y);
        float relZ = (float)(wz - cam.z);
        if ((env *= GlassVapor.Companion.nearFade(relX, relY, relZ, halfExtent)) <= 0.01f || !this.projectCenter(relX, relY, relZ, far)) {
            return count;
        }
        float cu = this.centerU;
        float cv = this.centerV;
        float lin = this.centerLin;
        float e1x = this.axisU(ax, 0.0f, 0.0f);
        float e1y = this.axisV;
        float e2x = this.axisU(0.0f, ay, 0.0f);
        float e2y = this.axisV;
        float e3x = this.axisU(0.0f, 0.0f, az);
        float e3y = this.axisV;
        float bxx = e1x * e1x + e2x * e2x + e3x * e3x;
        float bxy = e1x * e1y + e2x * e2y + e3x * e3y;
        float byy = e1y * e1y + e2y * e2y + e3y * e3y;
        float mid = 0.5f * (bxx + byy);
        float disc = (float)Math.sqrt(0.25f * (bxx - byy) * (bxx - byy) + bxy * bxy);
        float l1sq = mid + disc;
        float l2sq = Math.max(mid - disc, 1.0E-12f);
        float l1 = (float)Math.sqrt(l1sq);
        float l2 = (float)Math.sqrt(l2sq);
        if (l1 < 1.0E-5f || l2 < 1.0E-5f) {
            return count;
        }
        if ((env *= GlassVapor.Companion.sizeFade(l1, l2)) <= 0.01f) {
            return count;
        }
        float vx = 0.0f;
        float vy = 0.0f;
        if (Math.abs(bxy) > 1.0E-10f) {
            vx = bxy;
            vy = l1sq - bxx;
        } else if (bxx >= byy) {
            vx = 1.0f;
            vy = 0.0f;
        } else {
            vx = 0.0f;
            vy = 1.0f;
        }
        float vl = (float)Math.sqrt(vx * vx + vy * vy);
        if (vl < 1.0E-12f) {
            vx = 1.0f;
            vy = 0.0f;
            vl = 1.0f;
        }
        float p1x = (vx /= vl) * l1;
        float p1y = (vy /= vl) * l1;
        float p2x = -vy * l2;
        float p2y = vx * l2;
        if (!GlassVapor.Companion.cullCenter(cu, cv, l1, l2)) {
            return count;
        }
        return this.write(count, cu, cv, lin, env, p1x, p1y, p2x, p2y, colorIndex, paletteOn, phase, wobble, shine);
    }

    private final boolean projectCenter(float relX, float relY, float relZ, float far) {
        this.centerClip.set(relX, relY, relZ, 1.0f);
        this.viewProj.transform(this.centerClip);
        if (this.centerClip.w <= 0.05f) {
            return false;
        }
        float iw = 1.0f / this.centerClip.w;
        this.centerU = this.centerClip.x * iw * 0.5f + 0.5f;
        this.centerV = this.centerClip.y * iw * 0.5f + 0.5f;
        this.centerLin = GlassVapor.Companion.linDepth(this.centerClip.z * iw * 0.5f + 0.5f, 0.05f, far);
        return true;
    }

    private final float axisU(float ax, float ay, float az) {
        this.axisClip.set(ax, ay, az, 0.0f);
        this.viewProj.transform(this.axisClip);
        float iw2 = 1.0f / (this.centerClip.w * this.centerClip.w);
        float u = 0.5f * (this.axisClip.x * this.centerClip.w - this.centerClip.x * this.axisClip.w) * iw2;
        this.axisV = 0.5f * (this.axisClip.y * this.centerClip.w - this.centerClip.y * this.axisClip.w) * iw2;
        return u;
    }

    private final int write(int count, float cu, float cv, float lin, float env, float e1x, float e1y, float e2x, float e2y, int colorIndex, boolean paletteOn, float phase, float wobble, float shine) {
        int base = 16 + count * 16;
        this.data[base] = cu;
        this.data[base + 1] = cv;
        this.data[base + 2] = lin;
        this.data[base + 3] = env;
        this.data[base + 4] = e1x;
        this.data[base + 5] = e1y;
        this.data[base + 6] = e2x;
        this.data[base + 7] = e2y;
        this.data[base + 8] = paletteOn ? GlassVapor.Companion.paletteFadeT(8, colorIndex) : 0.0f;
        this.data[base + 9] = phase;
        this.data[base + 10] = wobble;
        this.data[base + 11] = shine;
        if (!paletteOn) {
            int color = this.getColor(colorIndex);
            this.data[base + 12] = (float)(color >> 16 & 0xFF) / 255.0f;
            this.data[base + 13] = (float)(color >> 8 & 0xFF) / 255.0f;
            this.data[base + 14] = (float)(color & 0xFF) / 255.0f;
        }
        return count + 1;
    }

    private final boolean usesClientPalette() {
        if (!this.colorMode.is(COLOR_CLIENT)) {
            return false;
        }
        int[] palette = ClientPalette.colors();
        return palette != null && palette.length >= 2;
    }

    private final int getColor(int index) {
        if (this.colorMode.is(COLOR_RAINBOW)) {
            return GlassVapor.Companion.rainbow(8, index, 1.0f, 1.0f);
        }
        int firstColor = 0;
        int secondColor = 0;
        if (this.colorMode.is(COLOR_CLIENT)) {
            int[] palette = ClientPalette.colors();
            if (palette != null && palette.length >= 2) {
                return GlassVapor.Companion.paletteFade(8, index, palette);
            }
            InterfaceModule iface = InterfaceModule.Companion.getInstance();
            if (iface != null) {
                firstColor = iface.clientPrimaryColorOpaque();
                secondColor = iface.usesSecondClientColor() ? iface.clientSecondaryColorOpaque() : firstColor;
            } else {
                firstColor = GlassVapor.Companion.getClientColor();
                secondColor = ColorEngine.lerpColor(firstColor, DARK_SECOND_COLOR, 0.7f);
            }
        } else {
            firstColor = this.customColor.getColor();
            int n = secondColor = this.useSecondColor.getValue() ? this.customSecondColor.getColor() : this.customColor.getColor();
        }
        if (firstColor == secondColor) {
            return firstColor;
        }
        return GlassVapor.Companion.fade(8, index, firstColor, secondColor);
    }

    @JvmOverloads
    public final void onAfterWorld(@Nullable Framebuffer renderTarget, @Nullable Matrix4f positionMatrix, @Nullable Matrix4f projectionMatrix, @Nullable Camera camera) {
        GlassVapor.onAfterWorld$default(this, renderTarget, positionMatrix, projectionMatrix, camera, false, 16, null);
    }

    private static final Boolean useSecondColor$lambda$0(GlassVapor this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean customColor$lambda$0(GlassVapor this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean customSecondColor$lambda$0(GlassVapor this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM) && this$0.useSecondColor.getValue();
    }


    @JvmStatic
    @Nullable
    public static final GlassVapor getInstance() {
        return Companion.getInstance();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u0015\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J/\u0010\u000e\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ/\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0016\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ/\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u001f\u0010 J/\u0010#\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00182\u0006\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b#\u0010$J'\u0010'\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00182\u0006\u0010&\u001a\u00020%H\u0002\u00a2\u0006\u0004\b'\u0010(J\u001f\u0010)\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b)\u0010*J'\u0010.\u001a\u00020\b2\u0006\u0010+\u001a\u00020\b2\u0006\u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b.\u0010/J\u0017\u00101\u001a\u00020\b2\u0006\u00100\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b1\u00102J\u0017\u00105\u001a\u00020\b2\u0006\u00104\u001a\u000203H\u0002\u00a2\u0006\u0004\b5\u00106R\u0014\u00108\u001a\u0002078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010:\u001a\u0002078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b:\u00109R\u0014\u0010;\u001a\u0002078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b;\u00109R\u0014\u0010<\u001a\u00020\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u0010=R\u0014\u0010>\u001a\u00020\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b>\u0010=R\u0014\u0010?\u001a\u00020\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u0010=R\u0014\u0010@\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u0010AR\u0014\u0010B\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bB\u0010AR\u0014\u0010C\u001a\u00020\u00188\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bC\u0010=R\u001d\u0010E\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\bD\u00a2\u0006\u0006\n\u0004\bE\u0010F\u00a8\u0006G"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/GlassVapor.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/GlassVapor;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/GlassVapor;", "", "cu", "cv", "l1", "l2", "", "cullCenter", "(FFFF)Z", "relX", "relY", "relZ", "halfExtent", "nearFade", "(FFFF)F", "sizeFade", "(FF)F", "", "getClientColor", "()I", "speed", "index", "saturation", "brightness", "rainbow", "(IIFF)I", "first", "second", "fade", "(IIII)I", "", "palette", "paletteFade", "(II[I)I", "paletteFadeT", "(II)F", "d", "near", "far", "linDepth", "(FFF)F", "x", "smooth", "(F)F", "", "nIn", "hashf", "(J)F", "", "COLOR_RAINBOW", "Ljava/lang/String;", "COLOR_CLIENT", "COLOR_CUSTOM", "CLIENT_COLOR_FIRST", "I", "CLIENT_COLOR_SECOND", "DARK_SECOND_COLOR", "RADIUS_SCALE", "F", "NEAR", "COLOR_INDEX_STEP", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/GlassVapor;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final GlassVapor getInstance() {
            GlassVapor module = ModuleManager.Companion.get().get(GlassVapor.class);
            GlassVapor glassVapor = module;
            if (glassVapor == null) {
                glassVapor = INSTANCE;
            }
            return glassVapor;
        }

        private final boolean cullCenter(float cu, float cv, float l1, float l2) {
            float ext = (l1 + l2) * 1.5f + 0.05f;
            return cu >= -ext && cu <= 1.0f + ext && cv >= -ext && cv <= 1.0f + ext;
        }

        private final float nearFade(float relX, float relY, float relZ, float halfExtent) {
            float dist = (float)Math.sqrt(relX * relX + relY * relY + relZ * relZ) - halfExtent;
            return this.smooth(MathHelper.clamp((float)((dist - 0.6f) / 0.75f), (float)0.0f, (float)1.0f));
        }

        private final float sizeFade(float l1, float l2) {
            float largest = Math.max(l1, l2);
            return 1.0f - this.smooth(MathHelper.clamp((float)((largest - 0.5f) / 0.35f), (float)0.0f, (float)1.0f));
        }

        private final int getClientColor() {
            float wave = (MathHelper.sin((double)((float)System.currentTimeMillis() / 520.0f)) + 1.0f) / 2.0f;
            return ColorEngine.lerpColor(CLIENT_COLOR_FIRST, CLIENT_COLOR_SECOND, wave);
        }

        private final int rainbow(int speed, int index, float saturation, float brightness) {
            int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
            return ColorEngine.rainbow(angle, saturation, brightness) | 0xFF000000;
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

        private final float paletteFadeT(int speed, int index) {
            int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
            return (float)angle / 360.0f;
        }

        private final float linDepth(float d, float near, float far) {
            float z = d * 2.0f - 1.0f;
            return 2.0f * near * far / (far + near - z * (far - near));
        }

        private final float smooth(float x) {
            return x * x * (3.0f - 2.0f * x);
        }

        private final float hashf(long nIn) {
            long n = nIn;
            n = (n ^ n >>> 33) * -336448155523654707L;
            n ^= n >>> 33;
            return (float)(n >>> 8 & 0xFFFFFFL) / 1.6777216E7f;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0013\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\u000b\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\"\u0010\u000e\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u0006\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR\"\u0010\u0012\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\u0019\u001a\u00020\u00188\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\"\u0010\u001f\u001a\u00020\u00188\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001f\u0010\u001a\u001a\u0004\b \u0010\u001c\"\u0004\b!\u0010\u001eR\"\u0010\"\u001a\u00020\u00188\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\"\u0010\u001a\u001a\u0004\b#\u0010\u001c\"\u0004\b$\u0010\u001eR\"\u0010%\u001a\u00020\u00188\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b%\u0010\u001a\u001a\u0004\b&\u0010\u001c\"\u0004\b'\u0010\u001eR\"\u0010)\u001a\u00020(8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\"\u0010/\u001a\u00020\u00188\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b/\u0010\u001a\u001a\u0004\b0\u0010\u001c\"\u0004\b1\u0010\u001eR\"\u00102\u001a\u00020\u00188\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b2\u0010\u001a\u001a\u0004\b3\u0010\u001c\"\u0004\b4\u0010\u001eR\"\u00105\u001a\u00020\u00188\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b5\u0010\u001a\u001a\u0004\b6\u0010\u001c\"\u0004\b7\u0010\u001eR\"\u00108\u001a\u00020\u00188\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b8\u0010\u001a\u001a\u0004\b9\u0010\u001c\"\u0004\b:\u0010\u001e\u00a8\u0006;"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/GlassVapor$Drop;", "", "<init>", "()V", "", "x", "D", "getX", "()D", "setX", "(D)V", "y", "getY", "setY", "z", "getZ", "setZ", "", "spawnMs", "J", "getSpawnMs", "()J", "setSpawnMs", "(J)V", "", "delayMs", "F", "getDelayMs", "()F", "setDelayMs", "(F)V", "burstMs", "getBurstMs", "setBurstMs", "riseMs", "getRiseMs", "setRiseMs", "dropR", "getDropR", "setDropR", "", "colorIndex", "I", "getColorIndex", "()I", "setColorIndex", "(I)V", "riseMul", "getRiseMul", "setRiseMul", "wobAmp", "getWobAmp", "setWobAmp", "wobSpeed", "getWobSpeed", "setWobSpeed", "phase", "getPhase", "setPhase", "rtx.kimiko:kimiko"})
    private static final class Drop {
        private double x;
        private double y;
        private double z;
        private long spawnMs;
        private float delayMs;
        private float burstMs;
        private float riseMs;
        private float dropR;
        private int colorIndex;
        private float riseMul;
        private float wobAmp;
        private float wobSpeed;
        private float phase;

        public final double getX() {
            return this.x;
        }

        public final void setX(double d) {
            this.x = d;
        }

        public final double getY() {
            return this.y;
        }

        public final void setY(double d) {
            this.y = d;
        }

        public final double getZ() {
            return this.z;
        }

        public final void setZ(double d) {
            this.z = d;
        }

        public final long getSpawnMs() {
            return this.spawnMs;
        }

        public final void setSpawnMs(long l) {
            this.spawnMs = l;
        }

        public final float getDelayMs() {
            return this.delayMs;
        }

        public final void setDelayMs(float f) {
            this.delayMs = f;
        }

        public final float getBurstMs() {
            return this.burstMs;
        }

        public final void setBurstMs(float f) {
            this.burstMs = f;
        }

        public final float getRiseMs() {
            return this.riseMs;
        }

        public final void setRiseMs(float f) {
            this.riseMs = f;
        }

        public final float getDropR() {
            return this.dropR;
        }

        public final void setDropR(float f) {
            this.dropR = f;
        }

        public final int getColorIndex() {
            return this.colorIndex;
        }

        public final void setColorIndex(int n) {
            this.colorIndex = n;
        }

        public final float getRiseMul() {
            return this.riseMul;
        }

        public final void setRiseMul(float f) {
            this.riseMul = f;
        }

        public final float getWobAmp() {
            return this.wobAmp;
        }

        public final void setWobAmp(float f) {
            this.wobAmp = f;
        }

        public final float getWobSpeed() {
            return this.wobSpeed;
        }

        public final void setWobSpeed(float f) {
            this.wobSpeed = f;
        }

        public final float getPhase() {
            return this.phase;
        }

        public final void setPhase(float f) {
            this.phase = f;
        }
    }
}

