/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.util.math.MathHelper
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Visuals.particles;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.modules.impl.Visuals.particles.FadeParticle;
import rtx.kimiko.api.modules.impl.Visuals.particles.WorldParticleUtil;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0011\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J3\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0016J\u001f\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001aR\u0014\u0010\u001d\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001aR\u0014\u0010\u001e\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001aR\u0014\u0010\u001f\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001aR\u0014\u0010 \u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b \u0010\u001aR\u0014\u0010!\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b!\u0010\u001aR\u0014\u0010\"\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\"\u0010\u001aR\u0014\u0010#\u001a\u00020\b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b#\u0010\u001aR\u001f\u0010&\u001a\b\u0012\u0004\u0012\u00020\b0$8\u0006X\u0087\u0004\u0092\u0002\u0002\b%\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010)\u00a8\u0006*"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/ParticleCollision;", "", "<init>", "()V", "Lnet/minecraft/MinecraftClient;", "client", "Lrtx/kimiko/api/modules/impl/Visuals/particles/FadeParticle;", "particle", "", "mode", "", "gravityValue", "", "Lkotlin/jvm/JvmStatic;", "apply", "(Lnet/minecraft/MinecraftClient;Lrtx/kimiko/api/modules/impl/Visuals/particles/FadeParticle;Ljava/lang/String;F)Z", "isRicochet", "(Ljava/lang/String;)Z", "", "jumpMultiplier", "(Ljava/lang/String;)D", "collidesX", "(Lnet/minecraft/MinecraftClient;Lrtx/kimiko/api/modules/impl/Visuals/particles/FadeParticle;)Z", "collidesY", "collidesZ", "RICOCHET", "Ljava/lang/String;", "STRONG_RICOCHET", "VERY_STRONG_RICOCHET", "WEAK_RICOCHET", "VERY_WEAK_RICOCHET", "IGNORE", "STUCK", "FATAL", "SLIDE", "SLIDE_SLOW", "", "Lkotlin/jvm/JvmField;", "MODES", "[Ljava/lang/String;", "SPEED_LIMIT", "D", "rtx.kimiko:kimiko"})
public final class ParticleCollision {
    @NotNull
    public static final ParticleCollision INSTANCE = new ParticleCollision();
    @NotNull
    public static final String RICOCHET = "Отскок";
    @NotNull
    public static final String STRONG_RICOCHET = "Сильный отскок";
    @NotNull
    public static final String VERY_STRONG_RICOCHET = "Очень сильный отскок";
    @NotNull
    public static final String WEAK_RICOCHET = "Слабый отскок";
    @NotNull
    public static final String VERY_WEAK_RICOCHET = "Очень слабый отскок";
    @NotNull
    public static final String IGNORE = "Игнорировать";
    @NotNull
    public static final String STUCK = "Прилипание";
    @NotNull
    public static final String FATAL = "Исчезновение";
    @NotNull
    public static final String SLIDE = "Скольжение";
    @NotNull
    public static final String SLIDE_SLOW = "Медленное скольжение";
    @JvmField
    @NotNull
    public static final String[] MODES;
    private static final double SPEED_LIMIT = 5.0;

    private ParticleCollision() {
    }

    @JvmStatic
    public static final boolean apply(@NotNull MinecraftClient client, @NotNull FadeParticle particle, @NotNull String mode, float gravityValue) {
        Intrinsics.checkNotNullParameter((Object)client, (String)"client");
        Intrinsics.checkNotNullParameter((Object)particle, (String)"particle");
        Intrinsics.checkNotNullParameter((Object)mode, (String)"mode");
        boolean cancelMove = false;
        if (!Intrinsics.areEqual((Object)IGNORE, (Object)mode)) {
            if (Intrinsics.areEqual((Object)FATAL, (Object)mode) || Intrinsics.areEqual((Object)STUCK, (Object)mode)) {
                if (particle.holdCollide || INSTANCE.collidesX(client, particle) || INSTANCE.collidesY(client, particle) || INSTANCE.collidesZ(client, particle)) {
                    particle.motionX = 0.0;
                    particle.motionY = 0.0;
                    particle.motionZ = 0.0;
                    particle.holdCollide = true;
                    if (Intrinsics.areEqual((Object)FATAL, (Object)mode)) {
                        particle.dead = true;
                    }
                    cancelMove = true;
                }
            } else {
                boolean ricochet = INSTANCE.isRicochet(mode);
                double jumpMul = INSTANCE.jumpMultiplier(mode);
                double slideMul = ricochet ? 1.0 : (Intrinsics.areEqual((Object)SLIDE_SLOW, (Object)mode) ? 0.875 : 1.0);
                boolean anyCollide = false;
                if (particle.holdCollide || INSTANCE.collidesY(client, particle)) {
                    if (!(particle.motionY == 0.0)) {
                        double d = particle.motionY = ricochet ? -particle.motionY * jumpMul / (Math.max((double)gravityValue, 0.0) + 1.0) * Math.min(Math.abs(particle.motionY) / 0.2, 1.0) : 0.0;
                        if (ricochet && particle.motionY > 0.0 && particle.motionY < 0.02) {
                            particle.motionY = 0.0;
                        }
                    }
                    if (!ricochet) {
                        particle.motionX *= slideMul;
                        particle.motionZ *= slideMul;
                    }
                    anyCollide = true;
                }
                if (particle.holdCollide || INSTANCE.collidesX(client, particle)) {
                    double d = particle.motionX = ricochet ? -particle.motionX * jumpMul : 0.0;
                    if (!ricochet) {
                        particle.motionZ *= slideMul;
                    }
                    anyCollide = true;
                }
                if (particle.holdCollide || INSTANCE.collidesZ(client, particle)) {
                    double d = particle.motionZ = ricochet ? -particle.motionZ * jumpMul : 0.0;
                    if (!ricochet) {
                        particle.motionX *= slideMul;
                    }
                    anyCollide = true;
                }
                if (anyCollide) {
                    double speedNow = Math.sqrt(particle.motionX * particle.motionX + particle.motionY * particle.motionY + particle.motionZ * particle.motionZ);
                    particle.holdCollide = speedNow < 0.008;
                }
            }
        }
        particle.motionX = MathHelper.clamp((double)particle.motionX, (double)-5.0, (double)5.0);
        particle.motionY = MathHelper.clamp((double)particle.motionY, (double)-5.0, (double)5.0);
        particle.motionZ = MathHelper.clamp((double)particle.motionZ, (double)-5.0, (double)5.0);
        return cancelMove;
    }

    private final boolean isRicochet(String mode) {
        return Intrinsics.areEqual((Object)RICOCHET, (Object)mode) || Intrinsics.areEqual((Object)STRONG_RICOCHET, (Object)mode) || Intrinsics.areEqual((Object)VERY_STRONG_RICOCHET, (Object)mode) || Intrinsics.areEqual((Object)WEAK_RICOCHET, (Object)mode) || Intrinsics.areEqual((Object)VERY_WEAK_RICOCHET, (Object)mode);
    }

    private final double jumpMultiplier(String mode) {
        if (Intrinsics.areEqual((Object)VERY_WEAK_RICOCHET, (Object)mode)) {
            return 0.5;
        }
        if (Intrinsics.areEqual((Object)WEAK_RICOCHET, (Object)mode)) {
            return 0.75;
        }
        if (Intrinsics.areEqual((Object)STRONG_RICOCHET, (Object)mode)) {
            return 1.5;
        }
        if (Intrinsics.areEqual((Object)VERY_STRONG_RICOCHET, (Object)mode)) {
            return 2.0;
        }
        return 1.0;
    }

    private final boolean collidesX(MinecraftClient client, FadeParticle particle) {
        return WorldParticleUtil.isSolidCollisionBlock(client, particle.posX + particle.motionX, particle.posY, particle.posZ);
    }

    private final boolean collidesY(MinecraftClient client, FadeParticle particle) {
        return WorldParticleUtil.isSolidCollisionBlock(client, particle.posX, particle.posY + particle.motionY, particle.posZ);
    }

    private final boolean collidesZ(MinecraftClient client, FadeParticle particle) {
        return WorldParticleUtil.isSolidCollisionBlock(client, particle.posX, particle.posY, particle.posZ + particle.motionZ);
    }

    static {
        String[] stringArray = new String[]{RICOCHET, STRONG_RICOCHET, VERY_STRONG_RICOCHET, WEAK_RICOCHET, VERY_WEAK_RICOCHET, IGNORE, STUCK, FATAL, SLIDE, SLIDE_SLOW};
        MODES = stringArray;
    }
}

