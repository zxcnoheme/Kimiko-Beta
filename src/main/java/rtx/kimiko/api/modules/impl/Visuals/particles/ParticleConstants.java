/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.Identifier
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Visuals.particles;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.modules.impl.Visuals.particles.ParticleTexture;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\f\u0010\u000bR\u0014\u0010\r\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000bR\u0014\u0010\u000e\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000bR\u0014\u0010\u000f\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000bR\u0014\u0010\u0011\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u000bR\u0014\u0010\u0012\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u000bR\u0014\u0010\u0013\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u000bR\u0014\u0010\u0014\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u000bR\u0014\u0010\u0015\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u000bR\u0014\u0010\u0016\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u000bR\u001f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\u00178\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/ParticleConstants;", "", "<init>", "()V", "", "mode", "path", "Lrtx/kimiko/api/modules/impl/Visuals/particles/ParticleTexture;", "particle", "(Ljava/lang/String;Ljava/lang/String;)Lrtx/kimiko/api/modules/impl/Visuals/particles/ParticleTexture;", "ROOT", "Ljava/lang/String;", "TEXTURE_CROSS", "TEXTURE_CROWN", "TEXTURE_DOLLAR", "TEXTURE_HEART", "TEXTURE_LIGHTNING", "TEXTURE_LINE", "TEXTURE_POINT", "TEXTURE_RHOMBUS", "TEXTURE_SNOWFLAKE", "TEXTURE_STAR", "TEXTURE_TRIANGLE", "", "Lkotlin/jvm/JvmField;", "TEXTURES", "[Lrtx/kimiko/api/modules/impl/Visuals/particles/ParticleTexture;", "rtx.kimiko:kimiko"})
public final class ParticleConstants {
    @NotNull
    public static final ParticleConstants INSTANCE = new ParticleConstants();
    @NotNull
    public static final String ROOT = "textures/features/particles/";
    @NotNull
    public static final String TEXTURE_CROSS = "Крест";
    @NotNull
    public static final String TEXTURE_CROWN = "Корона";
    @NotNull
    public static final String TEXTURE_DOLLAR = "Доллар";
    @NotNull
    public static final String TEXTURE_HEART = "Сердце";
    @NotNull
    public static final String TEXTURE_LIGHTNING = "Молния";
    @NotNull
    public static final String TEXTURE_LINE = "Линия";
    @NotNull
    public static final String TEXTURE_POINT = "Точка";
    @NotNull
    public static final String TEXTURE_RHOMBUS = "Ромб";
    @NotNull
    public static final String TEXTURE_SNOWFLAKE = "Снежинка";
    @NotNull
    public static final String TEXTURE_STAR = "Звезда";
    @NotNull
    public static final String TEXTURE_TRIANGLE = "Треугольник";
    @JvmField
    @NotNull
    public static final ParticleTexture[] TEXTURES;

    private ParticleConstants() {
    }

    private final ParticleTexture particle(String mode, String path) {
        Identifier identifier2 = Identifier.of((String)"kimiko", (String)(ROOT + path));
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        return new ParticleTexture(mode, identifier2);
    }

    static {
        ParticleTexture[] particleTextureArray = new ParticleTexture[]{INSTANCE.particle(TEXTURE_CROSS, "cross.png"), INSTANCE.particle(TEXTURE_CROWN, "crown.png"), INSTANCE.particle(TEXTURE_DOLLAR, "dollar.png"), INSTANCE.particle(TEXTURE_HEART, "heart.png"), INSTANCE.particle(TEXTURE_LIGHTNING, "lighting.png"), INSTANCE.particle(TEXTURE_LINE, "line.png"), INSTANCE.particle(TEXTURE_POINT, "point.png"), INSTANCE.particle(TEXTURE_RHOMBUS, "rhombus.png"), INSTANCE.particle(TEXTURE_SNOWFLAKE, "snowflake.png"), INSTANCE.particle(TEXTURE_STAR, "star.png"), INSTANCE.particle(TEXTURE_TRIANGLE, "triangle.png")};
        TEXTURES = particleTextureArray;
    }
}

