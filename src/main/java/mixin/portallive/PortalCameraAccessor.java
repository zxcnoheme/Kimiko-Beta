/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.render.Camera
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package mixin.portallive;

import net.minecraft.util.math.Vec3d;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={Camera.class})
public interface PortalCameraAccessor {
    @Invoker(value="setPos")
    public void kimiko$invokeSetPosition(Vec3d var1);

    @Invoker(value="setRotation")
    public void kimiko$invokeSetRotation(float var1, float var2);
}

