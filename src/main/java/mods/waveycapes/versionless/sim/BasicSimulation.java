/*
 * Decompiled with CFR 0.152.
 */
package mods.waveycapes.versionless.sim;

import java.util.List;
import mods.waveycapes.versionless.util.CapePoint;
import mods.waveycapes.versionless.util.Vector3;

public interface BasicSimulation {
    public void simulate();

    public void setGravityDirection(Vector3 var1);

    public float getGravity();

    public void setGravity(float var1);

    public boolean isSneaking();

    public void setSneaking(boolean var1);

    public boolean init(int var1);

    public boolean empty();

    public void applyMovement(Vector3 var1);

    public List<? extends CapePoint> getPoints();
}

