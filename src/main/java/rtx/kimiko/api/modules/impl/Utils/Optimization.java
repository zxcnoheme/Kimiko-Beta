/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.render.GameRenderer
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.utils.render.modules.optimization.OcclusionCuller;

@Feature(value={"optimization"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010\tR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\r\u0010\f\u00ca\u0001\u0010\b\u000f\u0012\f\b\u0010\u0012\b\b\fJ\u0004\b\b(\u0011\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/Optimization;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "tier", "()I", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "mode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "entityCulling", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "limitRenderDistance", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "optimization", "rtx.kimiko:kimiko"})
public final class Optimization
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ModeSetting mode;
    @NotNull
    private final BooleanSetting entityCulling;
    @NotNull
    private final BooleanSetting limitRenderDistance;
    @NotNull
    private static final int[] RENDER_DISTANCE_CAP;
    @NotNull
    private static final double[] VIEW_SCALE;
    @NotNull
    private static final double[] PARTICLE_DIST_SQ;
    @NotNull
    private static final double[] BLOCK_ENTITY_DIST_SQ;
    @Nullable
    private static Optimization companionInstance;

    public Optimization() {
        super("Optimization", "Поднимает FPS: куллинг сущностей, частиц и тайлов, дистанция, облака.", Category.UTILS);
        String[] stringArray = new String[]{"Низкий", "Средний", "Ультра"};
        this.mode = (ModeSetting)this.register((Setting)new ModeSetting("Режим", "Агрессивность оптимизации.", "Средний", stringArray));
        this.entityCulling = (BooleanSetting)this.register((Setting)new BooleanSetting("Куллинг сущностей", "Не рендерить сущностей, полностью скрытых за блоками.", true));
        this.limitRenderDistance = (BooleanSetting)this.register((Setting)new BooleanSetting("Ограничение прорисовки", "Средний — до 12 чанков, Ультра — до 8.", true).visibleWhen(() -> Optimization.limitRenderDistance$lambda$0(this)));
        companionInstance = this;
    }

    private final int tier() {
        return this.mode.is("Ультра") ? 2 : (this.mode.is("Средний") ? 1 : 0);
    }

    private static final Boolean limitRenderDistance$lambda$0(Optimization this$0) {
        return this$0.tier() >= 1;
    }

    @JvmStatic
    @Nullable
    public static final Optimization getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final int capEffectiveRenderDistance(int original) {
        return Companion.capEffectiveRenderDistance(original);
    }

    @JvmStatic
    public static final double scaleEntityViewScale(double original) {
        return Companion.scaleEntityViewScale(original);
    }

    @JvmStatic
    public static final boolean shaderTransparency(boolean original) {
        return Companion.shaderTransparency(original);
    }

    @JvmStatic
    public static final boolean cullClouds() {
        return Companion.cullClouds();
    }

    @JvmStatic
    public static final boolean hideEntityShadows() {
        return Companion.hideEntityShadows();
    }

    @JvmStatic
    public static final boolean allowParticle(double x, double y, double z) {
        return Companion.allowParticle(x, y, z);
    }

    @JvmStatic
    public static final boolean allowBlockEntity(double distSqToCamera) {
        return Companion.allowBlockEntity(distSqToCamera);
    }

    @JvmStatic
    public static final boolean shouldRenderEntity(boolean vanillaResult, @NotNull Entity entity, double camX, double camY, double camZ) {
        return Companion.shouldRenderEntity(vanillaResult, entity, camX, camY, camZ);
    }

    static {
        RENDER_DISTANCE_CAP = new int[]{0, 12, 8};
        VIEW_SCALE = new double[]{1.0, 0.8, 0.6};
        PARTICLE_DIST_SQ = new double[]{2304.0, 1024.0, 484.0};
        BLOCK_ENTITY_DIST_SQ = new double[]{2304.0, 1296.0, 576.0};
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\r\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u000fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0012\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0014\u0010\nJ\u0013\u0010\u0015\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0015\u0010\nJ+\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u001b\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u000fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001c\u0010\u001dJ;\u0010$\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\b2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b$\u0010%R\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010*\u001a\u00020)8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020)8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010+R\u0014\u0010-\u001a\u00020)8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b-\u0010+R\u0018\u0010.\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b.\u0010/\u00a8\u00060"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/Optimization.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Utils/Optimization;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Utils/Optimization;", "", "active", "()Z", "", "original", "capEffectiveRenderDistance", "(I)I", "", "scaleEntityViewScale", "(D)D", "shaderTransparency", "(Z)Z", "cullClouds", "hideEntityShadows", "x", "y", "z", "allowParticle", "(DDD)Z", "distSqToCamera", "allowBlockEntity", "(D)Z", "vanillaResult", "Lnet/minecraft/Entity;", "entity", "camX", "camY", "camZ", "shouldRenderEntity", "(ZLnet/minecraft/Entity;DDD)Z", "", "RENDER_DISTANCE_CAP", "[I", "", "VIEW_SCALE", "[D", "PARTICLE_DIST_SQ", "BLOCK_ENTITY_DIST_SQ", "companionInstance", "Lrtx/kimiko/api/modules/impl/Utils/Optimization;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final Optimization getInstance() {
            return ModuleManager.Companion.get().get(Optimization.class);
        }

        private final boolean active() {
            Optimization optimization = companionInstance;
            return optimization != null ? optimization.isEnabled() : false;
        }

        @JvmStatic
        public final int capEffectiveRenderDistance(int original) {
            Optimization optimization = companionInstance;
            if (optimization == null) {
                return original;
            }
            Optimization inst = optimization;
            if (!this.active() || !inst.limitRenderDistance.getValue()) {
                return original;
            }
            int cap = RENDER_DISTANCE_CAP[inst.tier()];
            return cap > 0 ? Math.min(original, cap) : original;
        }

        @JvmStatic
        public final double scaleEntityViewScale(double original) {
            Optimization optimization = companionInstance;
            if (optimization == null) {
                return original;
            }
            Optimization inst = optimization;
            return this.active() ? original * VIEW_SCALE[inst.tier()] : original;
        }

        @JvmStatic
        public final boolean shaderTransparency(boolean original) {
            Optimization optimization = companionInstance;
            if (optimization == null) {
                return original;
            }
            Optimization inst = optimization;
            return this.active() && inst.tier() >= 1 ? false : original;
        }

        @JvmStatic
        public final boolean cullClouds() {
            Optimization optimization = companionInstance;
            if (optimization == null) {
                return false;
            }
            Optimization inst = optimization;
            return this.active() && inst.tier() == 2;
        }

        @JvmStatic
        public final boolean hideEntityShadows() {
            Optimization optimization = companionInstance;
            if (optimization == null) {
                return false;
            }
            Optimization inst = optimization;
            return this.active() && inst.tier() >= 1;
        }

        @JvmStatic
        public final boolean allowParticle(double x, double y, double z) {
            Optimization optimization = companionInstance;
            if (optimization == null) {
                return true;
            }
            Optimization inst = optimization;
            if (!this.active()) {
                return true;
            }
            MinecraftClient mc = MinecraftClient.getInstance();
            GameRenderer gr = mc.gameRenderer;
            if (gr == null) {
                return true;
            }
            Camera camera = gr.getCamera();
            if (camera == null) {
                return true;
            }
            Vec3d pos = camera.getCameraPos();
            double dx = x - pos.x;
            double dy = y - pos.y;
            double dz = z - pos.z;
            return dx * dx + dy * dy + dz * dz <= PARTICLE_DIST_SQ[inst.tier()];
        }

        @JvmStatic
        public final boolean allowBlockEntity(double distSqToCamera) {
            Optimization optimization = companionInstance;
            if (optimization == null) {
                return true;
            }
            Optimization inst = optimization;
            return !this.active() || distSqToCamera <= BLOCK_ENTITY_DIST_SQ[inst.tier()];
        }

        @JvmStatic
        public final boolean shouldRenderEntity(boolean vanillaResult, @NotNull Entity entity, double camX, double camY, double camZ) {
            Intrinsics.checkNotNullParameter((Object)entity, (String)"entity");
            Optimization optimization = companionInstance;
            if (optimization == null) {
                return vanillaResult;
            }
            Optimization inst = optimization;
            if (!(vanillaResult && this.active() && inst.entityCulling.getValue())) {
                return vanillaResult;
            }
            return OcclusionCuller.isVisible(entity, camX, camY, camZ, inst.tier());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

