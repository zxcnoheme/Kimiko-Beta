/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  net.minecraft.util.math.MathHelper
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Visuals.particles.dashlines;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b.\u0018\u0000 E2\u00020\u0001:\u0001EB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003Jm\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\b\u00a2\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0019\u001a\u00020\u0018\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\b\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fR\"\u0010 \u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\"\u0010&\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b&\u0010!\u001a\u0004\b'\u0010#\"\u0004\b(\u0010%R\"\u0010)\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b)\u0010!\u001a\u0004\b*\u0010#\"\u0004\b+\u0010%R\"\u0010,\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010\u001c\"\u0004\b/\u0010\u0017R\"\u00100\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b0\u0010-\u001a\u0004\b1\u0010\u001c\"\u0004\b2\u0010\u0017R\"\u00103\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b3\u0010-\u001a\u0004\b4\u0010\u001c\"\u0004\b5\u0010\u0017R\"\u0010\f\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\f\u0010-\u001a\u0004\b6\u0010\u001c\"\u0004\b7\u0010\u0017R\"\u0010\r\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\r\u0010-\u001a\u0004\b8\u0010\u001c\"\u0004\b9\u0010\u0017R\"\u0010\u000e\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010-\u001a\u0004\b:\u0010\u001c\"\u0004\b;\u0010\u0017R\"\u0010\u000f\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000f\u0010-\u001a\u0004\b<\u0010\u001c\"\u0004\b=\u0010\u0017R\"\u0010\u0010\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0010\u0010-\u001a\u0004\b>\u0010\u001c\"\u0004\b?\u0010\u0017R\"\u0010@\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b@\u0010-\u001a\u0004\bA\u0010\u001c\"\u0004\bB\u0010\u0017R\"\u0010\u0011\u001a\u00020\b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0011\u0010-\u001a\u0004\bC\u0010\u001c\"\u0004\bD\u0010\u0017\u00a8\u0006F"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/dashlines/DashLine;", "", "<init>", "()V", "", "x", "y", "z", "", "dx", "dy", "dz", "speed", "length", "halfWidth", "brightness", "bellyBias", "life", "", "launch", "(DDDFFFFFFFFF)V", "dt", "step", "(F)V", "", "expired", "()Z", "fade", "()F", "value", "smoothstep", "(F)F", "posX", "D", "getPosX", "()D", "setPosX", "(D)V", "posY", "getPosY", "setPosY", "posZ", "getPosZ", "setPosZ", "dirX", "F", "getDirX", "setDirX", "dirY", "getDirY", "setDirY", "dirZ", "getDirZ", "setDirZ", "getSpeed", "setSpeed", "getLength", "setLength", "getHalfWidth", "setHalfWidth", "getBrightness", "setBrightness", "getBellyBias", "setBellyBias", "age", "getAge", "setAge", "getLife", "setLife", "Companion", "rtx.kimiko:kimiko"})
public final class DashLine {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private double posX;
    private double posY;
    private double posZ;
    private float dirX;
    private float dirY;
    private float dirZ = 1.0f;
    private float speed;
    private float length = 1.0f;
    private float halfWidth = 0.02f;
    private float brightness = 1.0f;
    private float bellyBias = 0.5f;
    private float age;
    private float life = 1.0f;
    private static final float FADE_IN = 0.1f;
    private static final float FADE_OUT = 0.38f;

    public final double getPosX() {
        return this.posX;
    }

    public final void setPosX(double d) {
        this.posX = d;
    }

    public final double getPosY() {
        return this.posY;
    }

    public final void setPosY(double d) {
        this.posY = d;
    }

    public final double getPosZ() {
        return this.posZ;
    }

    public final void setPosZ(double d) {
        this.posZ = d;
    }

    public final float getDirX() {
        return this.dirX;
    }

    public final void setDirX(float f) {
        this.dirX = f;
    }

    public final float getDirY() {
        return this.dirY;
    }

    public final void setDirY(float f) {
        this.dirY = f;
    }

    public final float getDirZ() {
        return this.dirZ;
    }

    public final void setDirZ(float f) {
        this.dirZ = f;
    }

    public final float getSpeed() {
        return this.speed;
    }

    public final void setSpeed(float f) {
        this.speed = f;
    }

    public final float getLength() {
        return this.length;
    }

    public final void setLength(float f) {
        this.length = f;
    }

    public final float getHalfWidth() {
        return this.halfWidth;
    }

    public final void setHalfWidth(float f) {
        this.halfWidth = f;
    }

    public final float getBrightness() {
        return this.brightness;
    }

    public final void setBrightness(float f) {
        this.brightness = f;
    }

    public final float getBellyBias() {
        return this.bellyBias;
    }

    public final void setBellyBias(float f) {
        this.bellyBias = f;
    }

    public final float getAge() {
        return this.age;
    }

    public final void setAge(float f) {
        this.age = f;
    }

    public final float getLife() {
        return this.life;
    }

    public final void setLife(float f) {
        this.life = f;
    }

    public final void launch(double x, double y, double z, float dx, float dy, float dz, float speed, float length, float halfWidth, float brightness, float bellyBias, float life) {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.dirX = dx;
        this.dirY = dy;
        this.dirZ = dz;
        this.speed = speed;
        this.length = length;
        this.halfWidth = halfWidth;
        this.brightness = brightness;
        this.bellyBias = bellyBias;
        this.life = Math.max(0.15f, life);
        this.age = 0.0f;
    }

    public final void step(float dt) {
        this.age += dt;
        double travel = this.speed * dt;
        this.posX += (double)this.dirX * travel;
        this.posY += (double)this.dirY * travel;
        this.posZ += (double)this.dirZ * travel;
    }

    public final boolean expired() {
        return this.age >= this.life;
    }

    public final float fade() {
        float t = MathHelper.clamp((float)(this.age / this.life), (float)0.0f, (float)1.0f);
        float rise = MathHelper.clamp((float)(this.age / 0.1f), (float)0.0f, (float)1.0f);
        float fall = MathHelper.clamp((float)((1.0f - t) / 0.38f), (float)0.0f, (float)1.0f);
        return this.smoothstep(rise) * this.smoothstep(fall) * this.brightness;
    }

    private final float smoothstep(float value) {
        return value * value * (3.0f - 2.0f * value);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006\u00a8\u0006\b"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/dashlines/DashLine.Companion;", "", "<init>", "()V", "", "FADE_IN", "F", "FADE_OUT", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

