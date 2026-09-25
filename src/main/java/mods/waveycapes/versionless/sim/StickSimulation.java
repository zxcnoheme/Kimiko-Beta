/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package mods.waveycapes.versionless.sim;

import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import mods.waveycapes.versionless.sim.BasicSimulation;
import mods.waveycapes.versionless.util.CapePoint;
import mods.waveycapes.versionless.util.Mth;
import mods.waveycapes.versionless.util.Vector3;

public class StickSimulation
implements BasicSimulation {
    public List<Point> points = new ArrayList<Point>();
    public List<Stick> sticks = new ArrayList<Stick>();
    public Vector2 gravityDirection = new Vector2(0.0f, -1.0f);
    public float gravity = 0.0f;
    public int numIterations = 3;
    private float maxBend = 5.0f;
    public boolean sneaking = false;

    @Override
    public boolean init(int partCount) {
        if (this.points.size() != partCount) {
            this.points.clear();
            this.sticks.clear();
            for (int i = 0; i < partCount; ++i) {
                Point point = new Point();
                point.position.y = -i;
                point.locked = i == 0;
                this.points.add(point);
                if (i <= 0) continue;
                this.sticks.add(new Stick(this.points.get(i - 1), point, 1.0f));
            }
            return true;
        }
        return false;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void simulate() {
        float deltaTime = 0.05f;
        Vector2 down = this.gravityDirection.clone().mul(this.gravity * deltaTime);
        Vector2 tmp = new Vector2(0.0f, 0.0f);
        for (Point point : this.points) {
            if (point.locked) continue;
            tmp.copy(point.position);
            point.position.add(down);
            point.prevPosition.copy(tmp);
        }
        Point basePoint = this.points.get(0);
        for (Point p : this.points) {
            if (p == basePoint || !(p.position.x - basePoint.position.x > 0.0f)) continue;
            p.position.x = basePoint.position.x;
        }
        int var5_8 = this.points.size() - 2;
        while (var5_8 >= 1) {
            Vector2 replacement;
            double abs;
            double angle = this.getAngle(this.points.get(var5_8).position, this.points.get(var5_8 - 1).position, this.points.get(var5_8 + 1).position);
            if ((angle *= 57.2958) > 360.0) {
                angle -= 360.0;
            }
            if (angle < -360.0) {
                angle += 360.0;
            }
            if ((abs = Math.abs(angle)) < (double)(180.0f - this.maxBend)) {
                this.points.get(var5_8 + 1).position = replacement = this.getReplacement(this.points.get(var5_8).position, this.points.get(var5_8 - 1).position, angle, 180.0f - this.maxBend + 1.0f);
            }
            if (abs > (double)(180.0f + this.maxBend)) {
                this.points.get(var5_8 + 1).position = replacement = this.getReplacement(this.points.get(var5_8).position, this.points.get(var5_8 - 1).position, angle, 180.0f + this.maxBend - 1.0f);
            }
            --var5_8;
        }
        int var5_10 = 0;
        while (var5_10 < this.numIterations) {
            for (int x = this.sticks.size() - 1; x >= 0; --x) {
                Stick stick = this.sticks.get(x);
                Vector2 stickCentre = stick.pointA.position.clone().add(stick.pointB.position).div(2.0f);
                Vector2 stickDir = stick.pointA.position.clone().subtract(stick.pointB.position).normalize();
                if (!stick.pointA.locked) {
                    stick.pointA.position = stickCentre.clone().add(stickDir.clone().mul(stick.length / 2.0f));
                }
                if (stick.pointB.locked) continue;
                stick.pointB.position = stickCentre.clone().subtract(stickDir.clone().mul(stick.length / 2.0f));
            }
            ++var5_10;
        }
        int var5_12 = 0;
        while (var5_12 < this.sticks.size()) {
            Stick stick = this.sticks.get(var5_12);
            Vector2 stickDir = stick.pointA.position.clone().subtract(stick.pointB.position).normalize();
            if (!stick.pointB.locked) {
                stick.pointB.position = stick.pointA.position.clone().subtract(stickDir.mul(stick.length));
            }
            ++var5_12;
        }
    }

    private Vector2 getReplacement(Vector2 middle, Vector2 prev, double angle, double target) {
        double theta = target / 57.2958;
        float x = prev.x - middle.x;
        float y = prev.y - middle.y;
        if (angle < 0.0) {
            theta *= -1.0;
        }
        double cs = Math.cos(theta);
        double sn = Math.sin(theta);
        return new Vector2((float)((double)x * cs - (double)y * sn + (double)middle.x), (float)((double)x * sn + (double)y * cs + (double)middle.y));
    }

    private double getAngle(Vector2 middle, Vector2 prev, Vector2 next) {
        return Math.atan2(next.y - middle.y, next.x - middle.x) - Math.atan2(prev.y - middle.y, prev.x - middle.x);
    }

    @Override
    public void setGravityDirection(Vector3 gravityDirection) {
        this.gravityDirection.x = gravityDirection.x;
        this.gravityDirection.y = gravityDirection.y;
    }

    @Override
    public float getGravity() {
        return this.gravity;
    }

    @Override
    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    @Override
    public boolean isSneaking() {
        return this.sneaking;
    }

    @Override
    public void setSneaking(boolean sneaking) {
        this.sneaking = sneaking;
    }

    @Override
    public void applyMovement(Vector3 movement) {
        this.points.get((int)0).prevPosition.copy(this.points.get((int)0).position);
        this.points.get((int)0).position.add(new Vector2(movement.x, movement.y));
    }

    @Override
    public boolean empty() {
        return this.sticks.isEmpty();
    }

    @Override
    public List<Point> getPoints() {
        return this.points;
    }

    public static class Vector2 {
        public float x;
        public float y;

        public Vector2 clone() {
            return new Vector2(this.x, this.y);
        }

        public void copy(Vector2 vec) {
            this.x = vec.x;
            this.y = vec.y;
        }

        public Vector2 add(Vector2 vec) {
            this.x += vec.x;
            this.y += vec.y;
            return this;
        }

        public Vector2 subtract(Vector2 vec) {
            this.x -= vec.x;
            this.y -= vec.y;
            return this;
        }

        public Vector2 div(float amount) {
            this.x /= amount;
            this.y /= amount;
            return this;
        }

        public Vector2 mul(float amount) {
            this.x *= amount;
            this.y *= amount;
            return this;
        }

        public Vector2 normalize() {
            float f = Mth.sqrt(this.x * this.x + this.y * this.y);
            if (f < 1.0E-4f) {
                this.x = 0.0f;
                this.y = 0.0f;
            } else {
                this.x /= f;
                this.y /= f;
            }
            return this;
        }

        public Vector2 rotateDegrees(float deg) {
            float ox = this.x;
            float oy = this.y;
            deg = (float)Math.toRadians(deg);
            this.x = Mth.cos(deg) * ox - Mth.sin(deg) * oy;
            this.y = Mth.sin(deg) * ox + Mth.cos(deg) * oy;
            return this;
        }

        @Generated
        public Vector2(float x, float y) {
            this.x = x;
            this.y = y;
        }

        @Generated
        public String toString() {
            return "StickSimulation.Vector2(x=" + this.x + ", y=" + this.y + ")";
        }

        @Generated
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Vector2)) {
                return false;
            }
            Vector2 other = (Vector2)o;
            if (!other.canEqual(this)) {
                return false;
            }
            if (Float.compare(this.x, other.x) != 0) {
                return false;
            }
            return Float.compare(this.y, other.y) == 0;
        }

        @Generated
        protected boolean canEqual(Object other) {
            return other instanceof Vector2;
        }

        @Generated
        public int hashCode() {
            int PRIME = 59;
            int result = 1;
            result = result * 59 + Float.floatToIntBits(this.x);
            result = result * 59 + Float.floatToIntBits(this.y);
            return result;
        }
    }

    public static class Point
    implements CapePoint {
        public Vector2 position = new Vector2(0.0f, 0.0f);
        public Vector2 prevPosition = new Vector2(0.0f, 0.0f);
        public boolean locked;

        @Override
        public float getLerpX(float delta) {
            return Mth.lerp(delta, this.prevPosition.x, this.position.x);
        }

        @Override
        public float getLerpY(float delta) {
            return Mth.lerp(delta, this.prevPosition.y, this.position.y);
        }

        @Override
        public float getLerpZ(float delta) {
            return 0.0f;
        }
    }

    public static class Stick {
        public Point pointA;
        public Point pointB;
        public float length;

        public Stick(Point pointA, Point pointB, float length) {
            this.pointA = pointA;
            this.pointB = pointB;
            this.length = length;
        }
    }
}

