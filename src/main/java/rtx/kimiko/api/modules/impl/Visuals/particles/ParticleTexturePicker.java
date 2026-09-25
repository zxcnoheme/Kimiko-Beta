/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.Identifier
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Visuals.particles;

import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.modules.impl.Visuals.particles.ParticleConstants;
import rtx.kimiko.api.modules.impl.Visuals.particles.ParticleTexture;
import rtx.kimiko.api.modules.settings.impl.SelectSetting;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ#\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00058\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0014"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/ParticleTexturePicker;", "", "<init>", "()V", "", "", "Lkotlin/jvm/JvmStatic;", "modeOptions", "()[Ljava/lang/String;", "Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "mode", "Ljava/util/Random;", "random", "Lnet/minecraft/Identifier;", "pick", "(Lrtx/kimiko/api/modules/settings/impl/SelectSetting;Ljava/util/Random;)Lnet/minecraft/Identifier;", "SHOW_ALL", "Ljava/lang/String;", "MODE_OPTIONS", "[Ljava/lang/String;", "rtx.kimiko:kimiko"})
public final class ParticleTexturePicker {
    @NotNull
    public static final ParticleTexturePicker INSTANCE = new ParticleTexturePicker();
    @NotNull
    public static final String SHOW_ALL = "Отображать всё";
    @NotNull
    private static final String[] MODE_OPTIONS;

    private ParticleTexturePicker() {
    }

    @JvmStatic
    @NotNull
    public static final String[] modeOptions() {
        return (String[])MODE_OPTIONS.clone();
    }

    @JvmStatic
    @NotNull
    public static final Identifier pick(@NotNull SelectSetting mode, @NotNull Random random) {
        Intrinsics.checkNotNullParameter((Object)mode, (String)"mode");
        Intrinsics.checkNotNullParameter((Object)random, (String)"random");
        for (ParticleTexture texture : ParticleConstants.TEXTURES) {
            if (!mode.is(texture.getMode())) continue;
            return texture.getId();
        }
        ParticleTexture[] all = ParticleConstants.TEXTURES;
        return all[random.nextInt(all.length)].getId();
    }

    static {
        String[] stringArray = new String[]{SHOW_ALL, "Точка", "Звезда", "Молния", "Крест", "Корона", "Сердце", "Линия", "Ромб", "Доллар", "Снежинка", "Треугольник"};
        MODE_OPTIONS = stringArray;
    }
}

