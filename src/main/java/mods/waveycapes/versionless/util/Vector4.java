/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package mods.waveycapes.versionless.util;

import lombok.Generated;
import mods.waveycapes.versionless.util.Vector3;

public class Vector4 {
    public float x;
    public float y;
    public float z;
    public float w;

    public Vector4 clone() {
        return new Vector4(this.x, this.y, this.z, this.w);
    }

    public Vector3 toVec3() {
        return new Vector3(this.x, this.y, this.z);
    }

    @Generated
    public Vector4() {
    }

    @Generated
    public Vector4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Vector4)) {
            return false;
        }
        Vector4 other = (Vector4)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (Float.compare(this.x, other.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, other.y) != 0) {
            return false;
        }
        if (Float.compare(this.z, other.z) != 0) {
            return false;
        }
        return Float.compare(this.w, other.w) == 0;
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Vector4;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.x);
        result = result * 59 + Float.floatToIntBits(this.y);
        result = result * 59 + Float.floatToIntBits(this.z);
        result = result * 59 + Float.floatToIntBits(this.w);
        return result;
    }

    @Generated
    public String toString() {
        return "Vector4(x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", w=" + this.w + ")";
    }
}

