/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.render.RenderTickCounter
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.math;

import java.util.concurrent.ThreadLocalRandom;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.render.RenderTickCounter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.animations.fx.Decelerate;
import rtx.kimiko.utils.animations.fx.Direction;
import rtx.kimiko.utils.key.KeyBind;
import rtx.kimiko.utils.key.KeyHelper;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J#\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ#\u0010\b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\u000bJ#\u0010\b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\fH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\rJ\u0013\u0010\u000e\u001a\u00020\fH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000e\u0010\u000fJ+\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0012JC\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001a\u0010\u001bJ#\u0010 \u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001dH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b \u0010!J\u001d\u0010%\u001a\u00020$2\b\u0010#\u001a\u0004\u0018\u00010\"H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b%\u0010&J+\u0010)\u001a\u00020\n2\u0006\u0010'\u001a\u00020\n2\u0006\u0010(\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b)\u0010\u0012J+\u0010)\u001a\u00020\f2\u0006\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b)\u0010*J#\u0010)\u001a\u00020\n2\u0006\u0010'\u001a\u00020\n2\u0006\u0010(\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b)\u0010\u000bJ#\u0010)\u001a\u00020\f2\u0006\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\fH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b)\u0010\rJ'\u0010)\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010+2\b\u0010-\u001a\u0004\u0018\u00010+H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b)\u0010.J\u001d\u0010)\u001a\u00020+2\b\u00100\u001a\u0004\u0018\u00010/H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b)\u00101J\u000f\u00102\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b2\u00103R\u0014\u00104\u001a\u00020\f8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b4\u00105\u00a8\u00066"}, d2={"Lrtx/kimiko/utils/math/MathUtils;", "", "<init>", "()V", "", "min", "max", "Lkotlin/jvm/JvmStatic;", "getRandom", "(II)I", "", "(FF)F", "", "(DD)D", "computeGcd", "()D", "value", "clamp", "(FFF)F", "mouseX", "mouseY", "x", "y", "width", "height", "", "inside", "(DDFFFF)Z", "ms", "Lrtx/kimiko/utils/animations/fx/Direction;", "direction", "Lrtx/kimiko/utils/animations/fx/Decelerate;", "createAnimation", "(ILrtx/kimiko/utils/animations/fx/Direction;)Lrtx/kimiko/utils/animations/fx/Decelerate;", "Lrtx/kimiko/utils/key/KeyBind;", "bind", "", "shortBind", "(Lrtx/kimiko/utils/key/KeyBind;)Ljava/lang/String;", "prev", "to", "interpolate", "(DDD)D", "Lnet/minecraft/Vec3d;", "previous", "current", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;)Lnet/minecraft/Vec3d;", "Lnet/minecraft/Entity;", "entity", "(Lnet/minecraft/Entity;)Lnet/minecraft/Vec3d;", "tickDelta", "()F", "PI2", "D", "rtx.kimiko:kimiko"})
public final class MathUtils {
    @NotNull
    public static final MathUtils INSTANCE = new MathUtils();
    public static final double PI2 = Math.PI * 2;

    private MathUtils() {
    }

    @JvmStatic
    public static final int getRandom(int min, int max) {
        if (min == max) {
            return min;
        }
        int lo = Math.min(min, max);
        int hi = Math.max(min, max);
        return ThreadLocalRandom.current().nextInt(lo, hi + 1);
    }

    @JvmStatic
    public static final float getRandom(float min, float max) {
        return (float)MathUtils.getRandom((double)min, (double)max);
    }

    @JvmStatic
    public static final double getRandom(double min, double max) {
        if (min == max) {
            return min;
        }
        double lo = Math.min(min, max);
        double hi = Math.max(min, max);
        return ThreadLocalRandom.current().nextDouble(lo, hi);
    }

    @JvmStatic
    public static final double computeGcd() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        if (minecraftClient2 == null) {
            return 0.0;
        }
        MinecraftClient client = minecraftClient2;
        if (client.options == null) {
            return 0.0;
        }
        double factor = ((Number)client.options.getMouseSensitivity().getValue()).doubleValue() * 0.6 + 0.2;
        return factor * factor * factor * 1.2;
    }

    @JvmStatic
    public static final float clamp(float value, float min, float max) {
        return value < min ? min : Math.min(value, max);
    }

    @JvmStatic
    public static final boolean inside(double mouseX, double mouseY, float x, float y, float width, float height) {
        return mouseX >= (double)x && mouseX <= (double)(x + width) && mouseY >= (double)y && mouseY <= (double)(y + height);
    }

    @JvmStatic
    @NotNull
    public static final Decelerate createAnimation(int ms, @NotNull Direction direction) {
        Intrinsics.checkNotNullParameter((Object)((Object)direction), (String)"direction");
        Decelerate animation = new Decelerate();
        animation.setMs(ms);
        animation.setValue(1.0);
        animation.setDirection(direction);
        animation.counter.setTime(System.currentTimeMillis() - (long)ms - 1L);
        return animation;
    }

    @JvmStatic
    @NotNull
    public static final String shortBind(@Nullable KeyBind bind) {
        if (bind == null || !bind.isBound()) {
            return "...";
        }
        return KeyHelper.getShortName(bind.getCode());
    }

    @JvmStatic
    public static final float interpolate(float prev, float to, float value) {
        return prev + (to - prev) * value;
    }

    @JvmStatic
    public static final double interpolate(double prev, double to, double value) {
        return prev + (to - prev) * value;
    }

    @JvmStatic
    public static final float interpolate(float prev, float to) {
        return MathHelper.lerp((float)INSTANCE.tickDelta(), (float)prev, (float)to);
    }

    @JvmStatic
    public static final double interpolate(double prev, double to) {
        return MathHelper.lerp((double)INSTANCE.tickDelta(), (double)prev, (double)to);
    }

    @JvmStatic
    @NotNull
    public static final Vec3d interpolate(@Nullable Vec3d previous, @Nullable Vec3d current) {
        if (previous == null || current == null) {
            Vec3d vec3d2 = Vec3d.ZERO;
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
            return vec3d2;
        }
        return new Vec3d(MathUtils.interpolate(previous.x, current.x), MathUtils.interpolate(previous.y, current.y), MathUtils.interpolate(previous.z, current.z));
    }

    @JvmStatic
    @NotNull
    public static final Vec3d interpolate(@Nullable Entity entity) {
        if (entity == null) {
            Vec3d vec3d2 = Vec3d.ZERO;
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"ZERO");
            return vec3d2;
        }
        return new Vec3d(MathUtils.interpolate(entity.lastX, entity.getX()), MathUtils.interpolate(entity.lastY, entity.getY()), MathUtils.interpolate(entity.lastZ, entity.getZ()));
    }

    private final float tickDelta() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        if (minecraftClient2 == null) {
            return 1.0f;
        }
        MinecraftClient client = minecraftClient2;
        RenderTickCounter renderTickCounter2 = client.getRenderTickCounter();
        if (renderTickCounter2 == null) {
            return 1.0f;
        }
        RenderTickCounter tracker = renderTickCounter2;
        return tracker.getTickProgress(false);
    }
}

