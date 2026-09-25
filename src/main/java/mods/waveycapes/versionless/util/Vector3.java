/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package mods.waveycapes.versionless.util;

import lombok.Generated;
import mods.waveycapes.versionless.util.Mth;

public class Vector3 {
    public float x;
    public float y;
    public float z;

    public Vector3 clone() {
        return new Vector3(this.x, this.y, this.z);
    }

    public void copy(Vector3 vec) {
        this.x = vec.x;
        this.y = vec.y;
        this.z = vec.z;
    }

    public Vector3 add(Vector3 vec) {
        this.x += vec.x;
        this.y += vec.y;
        this.z += vec.z;
        return this;
    }

    public Vector3 add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Vector3 subtract(Vector3 vec) {
        this.x -= vec.x;
        this.y -= vec.y;
        this.z -= vec.z;
        return this;
    }

    public Vector3 div(float amount) {
        this.x /= amount;
        this.y /= amount;
        this.z /= amount;
        return this;
    }

    public Vector3 mul(float amount) {
        this.x *= amount;
        this.y *= amount;
        this.z *= amount;
        return this;
    }

    public Vector3 cross(Vector3 vec) {
        float f = this.x;
        float g = this.y;
        float h = this.z;
        float i = vec.x;
        float j = vec.y;
        float k = vec.z;
        this.x = g * k - h * j;
        this.y = h * i - f * k;
        this.z = f * j - g * i;
        return this;
    }

    public Vector3 normalize() {
        float f = Mth.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        if (f < 1.0E-4f) {
            this.x = 0.0f;
            this.y = 0.0f;
            this.z = 0.0f;
        } else {
            this.x /= f;
            this.y /= f;
            this.z /= f;
        }
        return this;
    }

    public Vector3 rotateDegrees(float deg) {
        float ox = this.x;
        float oy = this.y;
        deg = (float)Math.toRadians(deg);
        this.x = Mth.cos(deg) * ox - Mth.sin(deg) * oy;
        this.y = Mth.sin(deg) * ox + Mth.cos(deg) * oy;
        return this;
    }

    public float sqrMagnitude() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    @Generated
    public Vector3() {
    }

    @Generated
    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Vector3)) {
            return false;
        }
        Vector3 other = (Vector3)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (Float.compare(this.x, other.x) != 0) {
            return false;
        }
        if (Float.compare(this.y, other.y) != 0) {
            return false;
        }
        return Float.compare(this.z, other.z) == 0;
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof Vector3;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.x);
        result = result * 59 + Float.floatToIntBits(this.y);
        result = result * 59 + Float.floatToIntBits(this.z);
        return result;
    }

    @Generated
    public String toString() {
        return "Vector3(x=" + this.x + ", y=" + this.y + ", z=" + this.z + ")";
    }
}

