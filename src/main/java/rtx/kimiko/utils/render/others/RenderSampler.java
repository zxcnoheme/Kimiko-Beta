/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.textures.AddressMode
 *  com.mojang.blaze3d.textures.FilterMode
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  net.minecraft.client.gl.GpuSampler
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.others;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.AddressMode;
import com.mojang.blaze3d.textures.FilterMode;
import java.util.OptionalDouble;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import net.minecraft.client.gl.GpuSampler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\u0007J\u0013\u0010\t\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0007R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010\nR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\n\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/utils/render/others/RenderSampler;", "", "<init>", "()V", "Lnet/minecraft/GpuSampler;", "Lkotlin/jvm/JvmStatic;", "linearRepeat", "()Lnet/minecraft/GpuSampler;", "linear", "nearest", "Lnet/minecraft/GpuSampler;", "rtx.kimiko:kimiko"})
public final class RenderSampler {
    @NotNull
    public static final RenderSampler INSTANCE = new RenderSampler();
    @Nullable
    private static GpuSampler linear;
    @Nullable
    private static GpuSampler nearest;
    @Nullable
    private static GpuSampler linearRepeat;

    private RenderSampler() {
    }

    @JvmStatic
    @NotNull
    public static final GpuSampler linearRepeat() {
        GpuSampler sampler = linearRepeat;
        if (sampler == null) {
            linearRepeat = sampler = RenderSystem.getDevice().createSampler(AddressMode.REPEAT, AddressMode.REPEAT, FilterMode.LINEAR, FilterMode.LINEAR, 1, OptionalDouble.empty());
        }
        return sampler;
    }

    @JvmStatic
    @NotNull
    public static final GpuSampler linear() {
        GpuSampler sampler = linear;
        if (sampler == null) {
            linear = sampler = RenderSystem.getDevice().createSampler(AddressMode.CLAMP_TO_EDGE, AddressMode.CLAMP_TO_EDGE, FilterMode.LINEAR, FilterMode.LINEAR, 1, OptionalDouble.empty());
        }
        return sampler;
    }

    @JvmStatic
    @NotNull
    public static final GpuSampler nearest() {
        GpuSampler sampler = nearest;
        if (sampler == null) {
            nearest = sampler = RenderSystem.getDevice().createSampler(AddressMode.CLAMP_TO_EDGE, AddressMode.CLAMP_TO_EDGE, FilterMode.NEAREST, FilterMode.NEAREST, 1, OptionalDouble.empty());
        }
        return sampler;
    }
}

