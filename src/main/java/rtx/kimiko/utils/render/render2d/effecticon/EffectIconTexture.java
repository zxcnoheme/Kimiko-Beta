/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.TextureSetup
 *  net.minecraft.util.Identifier
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.render2d.effecticon;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.texture.TextureSetup;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\b\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\tR\u0019\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\b\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\n\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/utils/render/render2d/effecticon/EffectIconTexture;", "", "Lnet/minecraft/Identifier;", "id", "Lnet/minecraft/TextureSetup;", "setup", "<init>", "(Lnet/minecraft/Identifier;Lnet/minecraft/TextureSetup;)V", "Lkotlin/jvm/JvmField;", "Lnet/minecraft/Identifier;", "Lnet/minecraft/TextureSetup;", "rtx.kimiko:kimiko"})
public final class EffectIconTexture {
    @JvmField
    @NotNull
    public final Identifier id;
    @JvmField
    @NotNull
    public final TextureSetup setup;

    public EffectIconTexture(@NotNull Identifier id, @NotNull TextureSetup setup) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        Intrinsics.checkNotNullParameter((Object)setup, (String)"setup");
        this.id = id;
        this.setup = setup;
    }
}

