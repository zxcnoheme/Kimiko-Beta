/*
 * Decompiled with CFR 0.152.
 */
package mods.waveycapes.versionless.util;

import mods.waveycapes.versionless.util.Vector3;

public interface CapePoint {
    public float getLerpX(float var1);

    public float getLerpY(float var1);

    public float getLerpZ(float var1);

    default public Vector3 getLerpedPos(float delta) {
        return new Vector3(this.getLerpX(delta), this.getLerpY(delta), this.getLerpZ(delta));
    }
}

