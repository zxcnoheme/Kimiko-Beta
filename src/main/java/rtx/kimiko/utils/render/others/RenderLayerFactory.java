/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.RenderSetup
 *  net.minecraft.client.render.RenderSetup.Builder
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.util.Identifier
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.others;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.RenderSetup;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J+\u0010\f\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\f\u0010\rJ5\u0010\f\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\f\u0010\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/utils/render/others/RenderLayerFactory;", "", "<init>", "()V", "", "name", "", "expectedBufferSize", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "Lnet/minecraft/RenderLayer;", "Lkotlin/jvm/JvmStatic;", "create", "(Ljava/lang/String;ILcom/mojang/blaze3d/pipeline/RenderPipeline;)Lnet/minecraft/RenderLayer;", "Lnet/minecraft/Identifier;", "texture", "(Ljava/lang/String;ILcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/Identifier;)Lnet/minecraft/RenderLayer;", "rtx.kimiko:kimiko"})
public final class RenderLayerFactory {
    @NotNull
    public static final RenderLayerFactory INSTANCE = new RenderLayerFactory();

    private RenderLayerFactory() {
    }

    @JvmStatic
    @NotNull
    public static final RenderLayer create(@NotNull String name, int expectedBufferSize, @NotNull RenderPipeline pipeline) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter((Object)pipeline, (String)"pipeline");
        return RenderLayerFactory.create(name, expectedBufferSize, pipeline, null);
    }

    @JvmStatic
    @NotNull
    public static final RenderLayer create(@NotNull String name, int expectedBufferSize, @NotNull RenderPipeline pipeline, @Nullable Identifier texture) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter((Object)pipeline, (String)"pipeline");
        RenderSetup.Builder builder2 = RenderSetup.builder((RenderPipeline)pipeline);
        Intrinsics.checkNotNullExpressionValue((Object)builder2, (String)"builder(...)");
        RenderSetup.Builder builder = builder2;
        if (texture != null) {
            builder.texture("Sampler0", texture);
        }
        RenderLayer renderLayer2 = RenderLayer.of((String)name, (RenderSetup)builder.build());
        Intrinsics.checkNotNullExpressionValue((Object)renderLayer2, (String)"create(...)");
        return renderLayer2;
    }
}

