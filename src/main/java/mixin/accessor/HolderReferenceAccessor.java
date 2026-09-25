/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.registry.entry.RegistryEntry$Reference
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package mixin.accessor;

import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={RegistryEntry.Reference.class})
public interface HolderReferenceAccessor<T> {
    @Invoker(value="setValue")
    public void kimiko$bindValue(T var1);
}

