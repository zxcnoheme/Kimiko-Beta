/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.LivingEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.modules.targetesp;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\u0006\u0010\u000e\u001a\u00020\u0004\u0012\u0006\u0010\u000f\u001a\u00020\u0004\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0018\u0010\u0017J\u0010\u0010\u0019\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b\u001d\u0010\u001cJ\u0010\u0010\u001e\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001e\u0010\u0017J\u0010\u0010\u001f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001f\u0010\u0017J\u0010\u0010 \u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b \u0010\u0017J\u0010\u0010!\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b!\u0010\u0017J\u0010\u0010\"\u001a\u00020\u0010H\u00c6\u0003\u00a2\u0006\u0004\b\"\u0010#J~\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0011\u001a\u00020\u0010H\u00c6\u0001\u00a2\u0006\u0004\b$\u0010%J\u001b\u0010'\u001a\u00020\u00102\b\u0010&\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b'\u0010(J\u0011\u0010)\u001a\u00020\tH\u00d6\u0081\u0004\u00a2\u0006\u0004\b)\u0010\u001cJ\u0011\u0010+\u001a\u00020*H\u00d6\u0081\u0004\u00a2\u0006\u0004\b+\u0010,R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b-\u0012\b\b.\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010/\u001a\u0004\b\u0003\u0010\u0015R%\u0010\u0005\u001a\u00020\u00048\u0007z\f\b-\u0012\b\b.\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u00100\u001a\u0004\b\u0005\u0010\u0017R%\u0010\u0006\u001a\u00020\u00048\u0007z\f\b-\u0012\b\b.\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u00100\u001a\u0004\b\u0006\u0010\u0017R%\u0010\b\u001a\u00020\u00078\u0007z\f\b-\u0012\b\b.\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u00101\u001a\u0004\b\b\u0010\u001aR%\u0010\n\u001a\u00020\t8\u0007z\f\b-\u0012\b\b.\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u00102\u001a\u0004\b\n\u0010\u001cR%\u0010\u000b\u001a\u00020\t8\u0007z\f\b-\u0012\b\b.\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u00102\u001a\u0004\b\u000b\u0010\u001cR%\u0010\f\u001a\u00020\u00048\u0007z\f\b-\u0012\b\b.\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u00100\u001a\u0004\b\f\u0010\u0017R%\u0010\r\u001a\u00020\u00048\u0007z\f\b-\u0012\b\b.\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u00100\u001a\u0004\b\r\u0010\u0017R%\u0010\u000e\u001a\u00020\u00048\u0007z\f\b-\u0012\b\b.\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u00100\u001a\u0004\b\u000e\u0010\u0017R%\u0010\u000f\u001a\u00020\u00048\u0007z\f\b-\u0012\b\b.\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u00100\u001a\u0004\b\u000f\u0010\u0017R%\u0010\u0011\u001a\u00020\u00108\u0007z\f\b-\u0012\b\b.\u0012\u0004\b\b(\u0011\u00a2\u0006\f\n\u0004\b\u0011\u00103\u001a\u0004\b\u0011\u0010#\u00a8\u00064"}, d2={"Lrtx/kimiko/utils/render/modules/targetesp/TargetEspRenderContext;", "", "Lnet/minecraft/LivingEntity;", "target", "", "alpha", "partialTicks", "", "frameTimeMs", "", "primaryColor", "secondaryColor", "hurtProgress", "chainImpactProgress", "circleHeight", "brightness", "", "throughWalls", "<init>", "(Lnet/minecraft/LivingEntity;FFJIIFFFFZ)V", "component1", "()Lnet/minecraft/LivingEntity;", "component2", "()F", "component3", "component4", "()J", "component5", "()I", "component6", "component7", "component8", "component9", "component10", "component11", "()Z", "copy", "(Lnet/minecraft/LivingEntity;FFJIIFFFFZ)Lrtx/kimiko/utils/render/modules/targetesp/TargetEspRenderContext;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "Lnet/minecraft/LivingEntity;", "F", "J", "I", "Z", "rtx.kimiko:kimiko"})
public final class TargetEspRenderContext {
    @NotNull
    private final LivingEntity target;
    private final float alpha;
    private final float partialTicks;
    private final long frameTimeMs;
    private final int primaryColor;
    private final int secondaryColor;
    private final float hurtProgress;
    private final float chainImpactProgress;
    private final float circleHeight;
    private final float brightness;
    private final boolean throughWalls;

    public TargetEspRenderContext(@NotNull LivingEntity target, float alpha, float partialTicks, long frameTimeMs, int primaryColor, int secondaryColor, float hurtProgress, float chainImpactProgress, float circleHeight, float brightness, boolean throughWalls) {
        Intrinsics.checkNotNullParameter((Object)target, (String)"target");
        this.target = target;
        this.alpha = alpha;
        this.partialTicks = partialTicks;
        this.frameTimeMs = frameTimeMs;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.hurtProgress = hurtProgress;
        this.chainImpactProgress = chainImpactProgress;
        this.circleHeight = circleHeight;
        this.brightness = brightness;
        this.throughWalls = throughWalls;
    }

    @JvmName(name="target")
    @NotNull
    public final LivingEntity target() {
        return this.target;
    }

    @JvmName(name="alpha")
    public final float alpha() {
        return this.alpha;
    }

    @JvmName(name="partialTicks")
    public final float partialTicks() {
        return this.partialTicks;
    }

    @JvmName(name="frameTimeMs")
    public final long frameTimeMs() {
        return this.frameTimeMs;
    }

    @JvmName(name="primaryColor")
    public final int primaryColor() {
        return this.primaryColor;
    }

    @JvmName(name="secondaryColor")
    public final int secondaryColor() {
        return this.secondaryColor;
    }

    @JvmName(name="hurtProgress")
    public final float hurtProgress() {
        return this.hurtProgress;
    }

    @JvmName(name="chainImpactProgress")
    public final float chainImpactProgress() {
        return this.chainImpactProgress;
    }

    @JvmName(name="circleHeight")
    public final float circleHeight() {
        return this.circleHeight;
    }

    @JvmName(name="brightness")
    public final float brightness() {
        return this.brightness;
    }

    @JvmName(name="throughWalls")
    public final boolean throughWalls() {
        return this.throughWalls;
    }

    @NotNull
    public final LivingEntity component1() {
        return this.target;
    }

    public final float component2() {
        return this.alpha;
    }

    public final float component3() {
        return this.partialTicks;
    }

    public final long component4() {
        return this.frameTimeMs;
    }

    public final int component5() {
        return this.primaryColor;
    }

    public final int component6() {
        return this.secondaryColor;
    }

    public final float component7() {
        return this.hurtProgress;
    }

    public final float component8() {
        return this.chainImpactProgress;
    }

    public final float component9() {
        return this.circleHeight;
    }

    public final float component10() {
        return this.brightness;
    }

    public final boolean component11() {
        return this.throughWalls;
    }

    @NotNull
    public final TargetEspRenderContext copy(@NotNull LivingEntity target, float alpha, float partialTicks, long frameTimeMs, int primaryColor, int secondaryColor, float hurtProgress, float chainImpactProgress, float circleHeight, float brightness, boolean throughWalls) {
        Intrinsics.checkNotNullParameter((Object)target, (String)"target");
        return new TargetEspRenderContext(target, alpha, partialTicks, frameTimeMs, primaryColor, secondaryColor, hurtProgress, chainImpactProgress, circleHeight, brightness, throughWalls);
    }

    public static /* synthetic */ TargetEspRenderContext copy$default(TargetEspRenderContext targetEspRenderContext, LivingEntity livingEntity2, float f, float f2, long l, int n, int n2, float f3, float f4, float f5, float f6, boolean bl, int n3, Object object) {
        if ((n3 & 1) != 0) {
            livingEntity2 = targetEspRenderContext.target;
        }
        if ((n3 & 2) != 0) {
            f = targetEspRenderContext.alpha;
        }
        if ((n3 & 4) != 0) {
            f2 = targetEspRenderContext.partialTicks;
        }
        if ((n3 & 8) != 0) {
            l = targetEspRenderContext.frameTimeMs;
        }
        if ((n3 & 0x10) != 0) {
            n = targetEspRenderContext.primaryColor;
        }
        if ((n3 & 0x20) != 0) {
            n2 = targetEspRenderContext.secondaryColor;
        }
        if ((n3 & 0x40) != 0) {
            f3 = targetEspRenderContext.hurtProgress;
        }
        if ((n3 & 0x80) != 0) {
            f4 = targetEspRenderContext.chainImpactProgress;
        }
        if ((n3 & 0x100) != 0) {
            f5 = targetEspRenderContext.circleHeight;
        }
        if ((n3 & 0x200) != 0) {
            f6 = targetEspRenderContext.brightness;
        }
        if ((n3 & 0x400) != 0) {
            bl = targetEspRenderContext.throughWalls;
        }
        return targetEspRenderContext.copy(livingEntity2, f, f2, l, n, n2, f3, f4, f5, f6, bl);
    }

    @NotNull
    public String toString() {
        return "TargetEspRenderContext(target=" + this.target + ", alpha=" + this.alpha + ", partialTicks=" + this.partialTicks + ", frameTimeMs=" + this.frameTimeMs + ", primaryColor=" + this.primaryColor + ", secondaryColor=" + this.secondaryColor + ", hurtProgress=" + this.hurtProgress + ", chainImpactProgress=" + this.chainImpactProgress + ", circleHeight=" + this.circleHeight + ", brightness=" + this.brightness + ", throughWalls=" + this.throughWalls + ")";
    }

    public int hashCode() {
        int result = this.target.hashCode();
        result = result * 31 + Float.hashCode(this.alpha);
        result = result * 31 + Float.hashCode(this.partialTicks);
        result = result * 31 + Long.hashCode(this.frameTimeMs);
        result = result * 31 + Integer.hashCode(this.primaryColor);
        result = result * 31 + Integer.hashCode(this.secondaryColor);
        result = result * 31 + Float.hashCode(this.hurtProgress);
        result = result * 31 + Float.hashCode(this.chainImpactProgress);
        result = result * 31 + Float.hashCode(this.circleHeight);
        result = result * 31 + Float.hashCode(this.brightness);
        result = result * 31 + Boolean.hashCode(this.throughWalls);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TargetEspRenderContext)) {
            return false;
        }
        TargetEspRenderContext targetEspRenderContext = (TargetEspRenderContext)other;
        if (!Intrinsics.areEqual((Object)this.target, (Object)targetEspRenderContext.target)) {
            return false;
        }
        if (Float.compare(this.alpha, targetEspRenderContext.alpha) != 0) {
            return false;
        }
        if (Float.compare(this.partialTicks, targetEspRenderContext.partialTicks) != 0) {
            return false;
        }
        if (this.frameTimeMs != targetEspRenderContext.frameTimeMs) {
            return false;
        }
        if (this.primaryColor != targetEspRenderContext.primaryColor) {
            return false;
        }
        if (this.secondaryColor != targetEspRenderContext.secondaryColor) {
            return false;
        }
        if (Float.compare(this.hurtProgress, targetEspRenderContext.hurtProgress) != 0) {
            return false;
        }
        if (Float.compare(this.chainImpactProgress, targetEspRenderContext.chainImpactProgress) != 0) {
            return false;
        }
        if (Float.compare(this.circleHeight, targetEspRenderContext.circleHeight) != 0) {
            return false;
        }
        if (Float.compare(this.brightness, targetEspRenderContext.brightness) != 0) {
            return false;
        }
        return this.throughWalls == targetEspRenderContext.throughWalls;
    }
}

