/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.render.chunk.ChunkBuilder$BuiltChunk
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package mixin;

import net.minecraft.client.render.chunk.ChunkBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ChunkBuilder.BuiltChunk.class})
public abstract class SectionFadeMixin {
    @Inject(method={"method_76298"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void kimiko$noChunkFade(long time, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(Float.valueOf(1.0f));
    }

    @ModifyVariable(method={"method_76548"}, at=@At(value="HEAD"), argsOnly=true, require=0)
    private long kimiko$zeroFadeDuration(long duration) {
        return 0L;
    }
}

