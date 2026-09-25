/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.mob.MobEntity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.util.math.Box
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.render.Frustum
 *  net.minecraft.client.option.Perspective
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.AbstractClientPlayerEntity
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
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector4f;
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
import rtx.kimiko.api.modules.settings.impl.MultiSelectSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.modules.post.glowesp.GlowEspRenderer;
import rtx.kimiko.utils.render.render2d.ClientPalette;
import rtx.kimiko.utils.storage.friend.FriendUtils;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"glowesp"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00ae\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 N2\u00020\u0001:\u0001NB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u0004H\u0015b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\t\u0010\u0003J\u000f\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u000b\u0010\fJ'\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014JE\u0010 \u001a\u00020\u00042\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b \u0010!J%\u0010$\u001a\u00020#2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\"\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b$\u0010%J\u000f\u0010&\u001a\u00020#H\u0002\u00a2\u0006\u0004\b&\u0010'J5\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020\u001aH\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078+\u00a2\u0006\u0004\b,\u0010-J\u0019\u00100\u001a\u00020/2\b\u0010.\u001a\u0004\u0018\u00010\u0016H\u0002\u00a2\u0006\u0004\b0\u00101J\u0017\u00104\u001a\u00020/2\u0006\u00103\u001a\u000202H\u0002\u00a2\u0006\u0004\b4\u00105R\u0014\u00107\u001a\u0002068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b7\u00108R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010=\u001a\u00020<8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u0010>R\u0014\u0010?\u001a\u00020<8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u0010>R\u0014\u0010A\u001a\u00020@8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bA\u0010BR\u0014\u0010C\u001a\u00020@8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bC\u0010BR\u0014\u0010E\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010FR\u0014\u0010G\u001a\u0002098\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010;R\u0014\u0010I\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u0010JR\u0014\u0010K\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bK\u0010JR\u0014\u0010L\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u0010JR\u0014\u0010M\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bM\u0010J\u00ca\u0001\u0010\bO\u0012\f\b\u0006\u0012\b\b\fJ\u0004\b\b(P\u00a8\u0006Q"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/GlowEsp;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "onDisable", "", "fadeOutSeconds", "()F", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "event", "STD", "renderGlow", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "", "modeId", "()I", "", "Lnet/minecraft/LivingEntity;", "entities", "Lorg/joml/Matrix4f;", "vp", "Lnet/minecraft/Vec3d;", "cam", "partialTick", "", "out", "depths", "computeRects", "(Ljava/util/List;Lorg/joml/Matrix4f;Lnet/minecraft/Vec3d;F[F[F)V", "fade", "", "computeColors", "(Ljava/util/List;F)[I", "gradientColors", "()[I", "Lnet/minecraft/Frustum;", "frustum", "cameraPos", "MAX", "collectTargets", "(Lnet/minecraft/Frustum;Lnet/minecraft/Vec3d;)Ljava/util/List;", "entity", "", "shouldRenderEntity", "(Lnet/minecraft/LivingEntity;)Z", "Lnet/minecraft/AbstractClientPlayerEntity;", "player", "shouldRenderPlayer", "(Lnet/minecraft/AbstractClientPlayerEntity;)Z", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "targets", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "renderMode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "iterations", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "divider", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "chams", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "outline", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "colorSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "colorMode", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "colorTopLeft", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "colorTopRight", "colorBottomRight", "colorBottomLeft", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "glowesp", "rtx.kimiko:kimiko"})
public final class GlowEsp
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final MultiSelectSetting targets;
    @NotNull
    private final ModeSetting renderMode;
    @NotNull
    private final SliderSetting iterations;
    @NotNull
    private final SliderSetting divider;
    @NotNull
    private final BooleanSetting chams;
    @NotNull
    private final BooleanSetting outline;
    @NotNull
    private final SeparatorSetting colorSeparator;
    @NotNull
    private final ModeSetting colorMode;
    @NotNull
    private final ColorSetting colorTopLeft;
    @NotNull
    private final ColorSetting colorTopRight;
    @NotNull
    private final ColorSetting colorBottomRight;
    @NotNull
    private final ColorSetting colorBottomLeft;
    @NotNull
    private static final String TARGET_PLAYERS = "Игроки";
    @NotNull
    private static final String TARGET_MOBS = "Мобы";
    @NotNull
    private static final String TARGET_SELF = "Себя";
    @NotNull
    private static final String TARGET_FRIENDS = "Друзья";
    @NotNull
    private static final String MODE_OUTER = "Внешний";
    @NotNull
    private static final String MODE_INNER = "Внутренний";
    @NotNull
    private static final String MODE_BOTH = "Внешний и внутренний";
    @NotNull
    private static final String COLOR_RAINBOW = "Радуга";
    @NotNull
    private static final String COLOR_CLIENT = "Клиент";
    @NotNull
    private static final String COLOR_CUSTOM = "Свой";
    private static final double HURT_STEP = 0.3141592653589793;
    @JvmField
    @Nullable
    public static GlowEsp INSTANCE;

    public GlowEsp() {
        super("Shader ESP", "Подсвечивает сущности шейдерным свечением и обводкой.", Category.VISUALS);
        String[] stringArray = new String[]{TARGET_PLAYERS, TARGET_MOBS, TARGET_SELF, TARGET_FRIENDS};
        MultiSelectSetting multiSelectSetting = new MultiSelectSetting("Цели", "Кого подсвечивать через ShaderEsp.").value(stringArray);
        stringArray = new String[]{TARGET_PLAYERS, TARGET_MOBS, TARGET_SELF, TARGET_FRIENDS};
        this.targets = (MultiSelectSetting)this.register((Setting)multiSelectSetting.selected(stringArray));
        stringArray = new String[]{MODE_OUTER, MODE_INNER, MODE_BOTH};
        this.renderMode = (ModeSetting)this.register((Setting)new ModeSetting("Режим", "Где рисовать свечение.", MODE_OUTER, stringArray));
        this.iterations = (SliderSetting)this.register((Setting)new SliderSetting("Сила", "Количество проходов размытия.").range(1.0f, 5.0f).increment(1.0f).setValue(3.0f));
        this.divider = (SliderSetting)this.register((Setting)new SliderSetting("Сила размытия", "Делитель яркости последнего прохода.").range(1.0f, 8.0f).increment(0.1f).setValue(8.0f));
        this.chams = (BooleanSetting)this.register((Setting)new BooleanSetting("Чамсы", "Заливать саму модель цветом.", false));
        this.outline = (BooleanSetting)this.register((Setting)new BooleanSetting("Обводка", "Рисовать обводку вокруг модели.", true));
        this.colorSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Цвет"));
        stringArray = new String[]{COLOR_CLIENT, COLOR_RAINBOW, COLOR_CUSTOM};
        this.colorMode = (ModeSetting)this.register((Setting)new ModeSetting("Режим цвета", "Откуда брать цвета градиента.", COLOR_CLIENT, stringArray));
        this.colorTopLeft = (ColorSetting)this.register((Setting)new ColorSetting("Цвет 1", "Левый верхний угол градиента.", new Color(120, 170, 255, 220)));
        this.colorTopRight = (ColorSetting)this.register((Setting)new ColorSetting("Цвет 2", "Правый верхний угол градиента.", new Color(170, 130, 255, 220)));
        this.colorBottomRight = (ColorSetting)this.register((Setting)new ColorSetting("Цвет 3", "Правый нижний угол градиента.", new Color(255, 130, 200, 220)));
        this.colorBottomLeft = (ColorSetting)this.register((Setting)new ColorSetting("Цвет 4", "Левый нижний угол градиента.", new Color(120, 230, 255, 220)));
        INSTANCE = this;
        this.outline.visibleWhen(() -> GlowEsp._init_$lambda$0(this));
        this.colorTopLeft.visibleWhen(() -> GlowEsp._init_$lambda$1(this));
        this.colorTopRight.visibleWhen(() -> GlowEsp._init_$lambda$2(this));
        this.colorBottomRight.visibleWhen(() -> GlowEsp._init_$lambda$3(this));
        this.colorBottomLeft.visibleWhen(() -> GlowEsp._init_$lambda$4(this));
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onDisable() {
        GlowEspRenderer.clear();
    }

    @Override
    public float fadeOutSeconds() {
        return 0.5f;
    }

    @Protect(value=Level.STD)
    public final void renderGlow(@NotNull WorldRenderEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        ClientPlayerEntity player = this.mc.player;
        ClientWorld level = this.mc.world;
        if (player == null || level == null) {
            return;
        }
        Vec3d vec3d2 = event.getCamera() != null ? event.getCamera().getCameraPos() : this.mc.gameRenderer.getCamera().getCameraPos();
        Intrinsics.checkNotNull((Object)vec3d2);
        Vec3d cameraPos = vec3d2;
        Frustum frustum = new Frustum(event.getPositionMatrix(), event.getProjectionMatrix());
        frustum.setPosition(cameraPos.x, cameraPos.y, cameraPos.z);
        List<LivingEntity> entities = this.collectTargets(frustum, cameraPos);
        if (entities.isEmpty()) {
            return;
        }
        float fade = this.visualAlpha();
        Matrix4f vp = new Matrix4f((Matrix4fc)event.getProjectionMatrix()).mul((Matrix4fc)event.getPositionMatrix());
        float[] rects = new float[entities.size() * 4];
        float[] depths = new float[entities.size()];
        Intrinsics.checkNotNull((Object)vp);
        this.computeRects(entities, vp, cameraPos, event.getPartialTicks(), rects, depths);
        int[] colors = this.computeColors(entities, fade);
        GlowEspRenderer.render(entities, event.getPartialTicks(), event.getStack(), cameraPos, this.modeId(), (int)this.iterations.getFloat(), 4.0f + this.divider.getFloat(), this.chams.getValue(), this.outline.getValue(), rects, depths, colors);
    }

    private final int modeId() {
        return this.renderMode.is(MODE_INNER) ? 1 : (this.renderMode.is(MODE_BOTH) ? 2 : 0);
    }

    private final void computeRects(List<? extends LivingEntity> entities, Matrix4f vp, Vec3d cam, float partialTick, float[] out, float[] depths) {
        Vector4f tmp = new Vector4f();
        int n = ((Collection)entities).size();
        for (int index = 0; index < n; ++index) {
            LivingEntity entity = entities.get(index);
            double dx = MathHelper.lerp((double)partialTick, (double)entity.lastRenderX, (double)entity.getX()) - entity.getX();
            double dy = MathHelper.lerp((double)partialTick, (double)entity.lastRenderY, (double)entity.getY()) - entity.getY();
            double dz = MathHelper.lerp((double)partialTick, (double)entity.lastRenderZ, (double)entity.getZ()) - entity.getZ();
            Box bb = entity.getBoundingBox().offset(dx, dy, dz);
            tmp.set((float)(bb.getCenter().x - cam.x), (float)(bb.getCenter().y - cam.y), (float)(bb.getCenter().z - cam.z), 1.0f);
            vp.transform(tmp);
            depths[index] = tmp.w > 1.0E-4f ? tmp.z / tmp.w * 0.5f + 0.5f : 1.0f;
            float minX = Float.MAX_VALUE;
            float minY = Float.MAX_VALUE;
            float maxX = -3.4028235E38f;
            float maxY = -3.4028235E38f;
            for (int corner = 0; corner < 8; ++corner) {
                double cx = (corner & 1) == 0 ? bb.minX : bb.maxX;
                double cy = (corner & 2) == 0 ? bb.minY : bb.maxY;
                double cz = (corner & 4) == 0 ? bb.minZ : bb.maxZ;
                tmp.set((float)(cx - cam.x), (float)(cy - cam.y), (float)(cz - cam.z), 1.0f);
                vp.transform(tmp);
                if (tmp.w <= 1.0E-4f) continue;
                float u = tmp.x / tmp.w * 0.5f + 0.5f;
                float v = tmp.y / tmp.w * 0.5f + 0.5f;
                minX = Math.min(minX, u);
                minY = Math.min(minY, v);
                maxX = Math.max(maxX, u);
                maxY = Math.max(maxY, v);
            }
            int offset = index * 4;
            if (minX > maxX || minY > maxY) {
                out[offset] = 0.0f;
                out[offset + 1] = 0.0f;
                out[offset + 2] = 1.0f;
                out[offset + 3] = 1.0f;
                continue;
            }
            out[offset] = minX;
            out[offset + 1] = minY;
            out[offset + 2] = Math.max(maxX - minX, 1.0E-4f);
            out[offset + 3] = Math.max(maxY - minY, 1.0E-4f);
        }
    }

    private final int[] computeColors(List<? extends LivingEntity> entities, float fade) {
        int[] base = this.gradientColors();
        int[] out = new int[entities.size() * 4];
        int n = ((Collection)entities).size();
        for (int index = 0; index < n; ++index) {
            LivingEntity entity = entities.get(index);
            boolean friend = entity instanceof AbstractClientPlayerEntity && FriendUtils.isFriend((Entity)entity);
            float hurt = MathHelper.clamp((float)((float)Math.sin((double)entity.hurtTime * 0.3141592653589793)), (float)0.0f, (float)1.0f);
            for (int corner = 0; corner < 4; ++corner) {
                int color;
                int n2 = color = friend ? ColorEngine.rgba(70, 235, 120, ColorEngine.alpha(base[corner])) : base[corner];
                if (hurt > 0.0f) {
                    color = ColorEngine.lerpColor(color, ColorEngine.rgba(255, 60, 60, ColorEngine.alpha(color)), hurt);
                }
                if (fade < 1.0f) {
                    color = ColorEngine.multAlpha(color, fade);
                }
                out[index * 4 + corner] = color;
            }
        }
        return out;
    }

    private final int[] gradientColors() {
        if (this.colorMode.is(COLOR_RAINBOW)) {
            long index = System.currentTimeMillis() / 8L;
            int[] nArray = new int[]{GlowEsp.Companion.rainbow(index), GlowEsp.Companion.rainbow(index + 90L), GlowEsp.Companion.rainbow(index + 180L), GlowEsp.Companion.rainbow(index + 270L)};
            return nArray;
        }
        if (this.colorMode.is(COLOR_CLIENT)) {
            InterfaceModule iface;
            InterfaceModule interfaceModule = iface = InterfaceModule.Companion.getInstance();
            int style = interfaceModule != null ? interfaceModule.gradientStyleId() : 0;
            float base = ClientPalette.phase() * (style == 2 ? 20.0f : 1.0f);
            int[] nArray = new int[]{ClientPalette.loopColor(base) | 0xFF000000, ClientPalette.loopColor(base + 0.25f) | 0xFF000000, ClientPalette.loopColor(base + 0.5f) | 0xFF000000, ClientPalette.loopColor(base + 0.75f) | 0xFF000000};
            return nArray;
        }
        int[] nArray = new int[]{this.colorTopLeft.getColor(), this.colorTopRight.getColor(), this.colorBottomRight.getColor(), this.colorBottomLeft.getColor()};
        return nArray;
    }

    @Protect(value=Level.MAX)
    private final List<LivingEntity> collectTargets(Frustum frustum, Vec3d cameraPos) {
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return CollectionsKt.emptyList();
        }
        ClientWorld level = clientWorld3;
        List result = new ArrayList();
        for (Object t : level.getEntities()) {
            Intrinsics.checkNotNullExpressionValue(t, (String)"next(...)");
            Entity entity = (Entity)t;
            if (!(entity instanceof LivingEntity) || !this.shouldRenderEntity((LivingEntity)entity) || !entity.shouldRender(cameraPos.x, cameraPos.y, cameraPos.z) || !frustum.isVisible(((LivingEntity)entity).getBoundingBox().expand(0.5))) continue;
            result.add(entity);
            if (result.size() < 32) continue;
            break;
        }
        return result;
    }

    private final boolean shouldRenderEntity(LivingEntity entity) {
        if (entity == null || !entity.isAlive() || entity.isRemoved()) {
            return false;
        }
        if (entity instanceof AbstractClientPlayerEntity) {
            return this.shouldRenderPlayer((AbstractClientPlayerEntity)entity);
        }
        if (entity instanceof MobEntity) {
            return this.targets.is(TARGET_MOBS) && !((MobEntity)entity).isSpectator();
        }
        return false;
    }

    private final boolean shouldRenderPlayer(AbstractClientPlayerEntity player) {
        if (player.isSpectator()) {
            return false;
        }
        if (Intrinsics.areEqual((Object)player, (Object)this.mc.player)) {
            return this.targets.is(TARGET_SELF) && this.mc.options.getPerspective() != Perspective.FIRST_PERSON;
        }
        if (!this.targets.is(TARGET_PLAYERS)) {
            return false;
        }
        return this.targets.is(TARGET_FRIENDS) || !FriendUtils.isFriend((Entity)player);
    }

    private static final Boolean _init_$lambda$0(GlowEsp this$0) {
        return !this$0.chams.getValue();
    }

    private static final Boolean _init_$lambda$1(GlowEsp this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean _init_$lambda$2(GlowEsp this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean _init_$lambda$3(GlowEsp this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean _init_$lambda$4(GlowEsp this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM);
    }

    @JvmStatic
    @Nullable
    public static final GlowEsp getInstance() {
        return Companion.getInstance();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u000fR\u0014\u0010\u0012\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u000fR\u0014\u0010\u0013\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u000fR\u0014\u0010\u0014\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u000fR\u0014\u0010\u0015\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u000fR\u0014\u0010\u0016\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u000fR\u0014\u0010\u0017\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u000fR\u0014\u0010\u0018\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u000fR\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u001d\u0010\u001d\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u001c\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001e\u00a8\u0006\u001f"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/GlowEsp.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/GlowEsp;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/GlowEsp;", "", "index", "", "rainbow", "(J)I", "", "TARGET_PLAYERS", "Ljava/lang/String;", "TARGET_MOBS", "TARGET_SELF", "TARGET_FRIENDS", "MODE_OUTER", "MODE_INNER", "MODE_BOTH", "COLOR_RAINBOW", "COLOR_CLIENT", "COLOR_CUSTOM", "", "HURT_STEP", "D", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/GlowEsp;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final GlowEsp getInstance() {
            GlowEsp module = ModuleManager.Companion.get().get(GlowEsp.class);
            GlowEsp glowEsp = module;
            if (glowEsp == null) {
                glowEsp = INSTANCE;
            }
            return glowEsp;
        }

        private final int rainbow(long index) {
            int angle = (int)(index % 360L);
            int rgb = ColorEngine.rainbow(angle, 1.0f, 1.0f);
            return rgb | 0xFF000000;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

