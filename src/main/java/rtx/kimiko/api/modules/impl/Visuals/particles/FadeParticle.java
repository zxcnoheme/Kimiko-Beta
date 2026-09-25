/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.Identifier
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Visuals.particles;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0011\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0005\u00a2\u0006\u0004\b\u000b\u0010\fJ\r\u0010\u000e\u001a\u00020\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0018\u0010\u0019R\u0019\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001a\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u001bR\u0019\u0010\t\u001a\u00020\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001a\u00a2\u0006\u0006\n\u0004\b\t\u0010\u001cR\u0019\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001a\u00a2\u0006\u0006\n\u0004\b\n\u0010\u001bR\u001b\u0010\u001e\u001a\u00020\u001d8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u001a\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u001b\u0010 \u001a\u00020\u001d8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u001a\u00a2\u0006\u0006\n\u0004\b \u0010\u001fR\u001b\u0010!\u001a\u00020\u001d8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u001a\u00a2\u0006\u0006\n\u0004\b!\u0010\u001fR\u001b\u0010\"\u001a\u00020\u001d8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u001a\u00a2\u0006\u0006\n\u0004\b\"\u0010\u001fR\u001b\u0010#\u001a\u00020\u001d8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u001a\u00a2\u0006\u0006\n\u0004\b#\u0010\u001fR\u001b\u0010$\u001a\u00020\u001d8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u001a\u00a2\u0006\u0006\n\u0004\b$\u0010\u001fR\u001b\u0010%\u001a\u00020\u001d8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u001a\u00a2\u0006\u0006\n\u0004\b%\u0010\u001fR\u001b\u0010&\u001a\u00020\u001d8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u001a\u00a2\u0006\u0006\n\u0004\b&\u0010\u001fR\u001b\u0010'\u001a\u00020\u001d8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u001a\u00a2\u0006\u0006\n\u0004\b'\u0010\u001fR\u0019\u0010(\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001a\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0019\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001a\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u001bR\u001b\u0010*\u001a\u00020\u00148\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u001a\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u001b\u0010,\u001a\u00020\u00148\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u001a\u00a2\u0006\u0006\n\u0004\b,\u0010+R\u001b\u0010-\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u001a\u00a2\u0006\u0006\n\u0004\b-\u0010\u001b\u00a8\u0006."}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/FadeParticle;", "", "Lnet/minecraft/Vec3d;", "pos", "motion", "", "rotationDeg", "lifetimeMs", "Lnet/minecraft/Identifier;", "texture", "gradientT", "<init>", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;FFLnet/minecraft/Identifier;F)V", "", "beginStep", "()V", "", "now", "alpha01", "(J)F", "", "isDead", "(J)Z", "partialTicks", "renderPos", "(F)Lnet/minecraft/Vec3d;", "Lkotlin/jvm/JvmField;", "F", "Lnet/minecraft/Identifier;", "", "posX", "D", "posY", "posZ", "prevX", "prevY", "prevZ", "motionX", "motionY", "motionZ", "spawnMs", "J", "dead", "Z", "holdCollide", "twist", "rtx.kimiko:kimiko"})
public final class FadeParticle {
    @JvmField
    public final float rotationDeg;
    @JvmField
    @NotNull
    public final Identifier texture;
    @JvmField
    public final float gradientT;
    @JvmField
    public double posX;
    @JvmField
    public double posY;
    @JvmField
    public double posZ;
    @JvmField
    public double prevX;
    @JvmField
    public double prevY;
    @JvmField
    public double prevZ;
    @JvmField
    public double motionX;
    @JvmField
    public double motionY;
    @JvmField
    public double motionZ;
    @JvmField
    public final long spawnMs;
    @JvmField
    public final float lifetimeMs;
    @JvmField
    public boolean dead;
    @JvmField
    public boolean holdCollide;
    @JvmField
    public float twist;

    public FadeParticle(@NotNull Vec3d pos, @NotNull Vec3d motion, float rotationDeg, float lifetimeMs, @NotNull Identifier texture, float gradientT) {
        Intrinsics.checkNotNullParameter((Object)pos, (String)"pos");
        Intrinsics.checkNotNullParameter((Object)motion, (String)"motion");
        Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
        this.rotationDeg = rotationDeg;
        this.texture = texture;
        this.gradientT = gradientT;
        this.posX = pos.x;
        this.posY = pos.y;
        this.posZ = pos.z;
        this.prevX = pos.x;
        this.prevY = pos.y;
        this.prevZ = pos.z;
        this.motionX = motion.x;
        this.motionY = motion.y;
        this.motionZ = motion.z;
        this.spawnMs = System.currentTimeMillis();
        this.lifetimeMs = Math.max(1.0f, lifetimeMs);
    }

    public final void beginStep() {
        this.prevX = this.posX;
        this.prevY = this.posY;
        this.prevZ = this.posZ;
    }

    public final float alpha01(long now) {
        float t = (float)(now - this.spawnMs) / this.lifetimeMs;
        if (t <= 0.0f) {
            return 0.0f;
        }
        if (t <= 1.0f) {
            return t;
        }
        return Math.max(0.0f, 2.0f - t);
    }

    public final boolean isDead(long now) {
        return this.dead || (float)(now - this.spawnMs) >= this.lifetimeMs * 2.0f;
    }

    @NotNull
    public final Vec3d renderPos(float partialTicks) {
        return new Vec3d(this.prevX + (this.posX - this.prevX) * (double)partialTicks, this.prevY + (this.posY - this.prevY) * (double)partialTicks, this.prevZ + (this.posZ - this.prevZ) * (double)partialTicks);
    }
}

