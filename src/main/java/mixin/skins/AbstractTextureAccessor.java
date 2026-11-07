package mixin.skins;

import com.mojang.blaze3d.textures.GpuTextureView;
import net.minecraft.client.texture.AbstractTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Доступ к view GPU-текстуры: динамические текстуры Kimiko (скины, миниатюры)
 * создаются вручную, и vanilla-пайплайн сам view им не назначает.
 */
@Mixin(AbstractTexture.class)
public interface AbstractTextureAccessor {
    @Accessor("glTextureView")
    void kimiko$setView(GpuTextureView view);
}
