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

public class StickSimulation3d
implements BasicSimulation {
    public List<Point> points = new ArrayList<Point>();
    public List<Stick> sticks = new ArrayList<Stick>();
    public Vector3 gravityDirection = new Vector3(0.0f, -1.0f, 0.0f);
    public float gravity = 0.0f;
    public int numIterations = 3;
    private float maxBend = 20.0f;
    public boolean sneaking = false;

    @Override
    public boolean init(int partCount) {
        if (this.points.size() != partCount) {
            this.points.clear();
            this.sticks.clear();
            for (int i = 0; i < partCount; ++i) {
                Point point = new Point();
                point.position.y = -i;
                point.position.x = -i;
                point.locked = i == 0;
                this.points.add(point);
                if (i <= 0) continue;
                this.sticks.add(new Stick(this.points.get(i - 1), point, 1.0f));
            }
            return true;
        }
        return false;
    }

    @Override
    public void simulate() {
        this.applyGravity();
        this.preventClipping();
        this.preventSelfClipping();
        this.applyMotion();
        this.preventSelfClipping();
        this.preventHardBends();
        this.limitLength();
    }

    private void applyGravity() {
        float deltaTime = 0.05f;
        Vector3 down = this.gravityDirection.clone().mul(this.gravity * deltaTime);
        Vector3 tmp = new Vector3(0.0f, 0.0f, 0.0f);
        for (Point p : this.points) {
            if (p.locked) continue;
            tmp.copy(p.position);
            p.position.add(down);
            p.prevPosition.copy(tmp);
        }
    }

    private void applyMotion() {
        for (int i = 0; i < this.numIterations; ++i) {
            for (int x = this.sticks.size() - 1; x >= 0; --x) {
                Stick stick = this.sticks.get(x);
                Vector3 a = stick.pointA.position;
                Vector3 b = stick.pointB.position;
                float cx = (a.x + b.x) * 0.5f;
                float cy = (a.y + b.y) * 0.5f;
                float cz = (a.z + b.z) * 0.5f;
                float dx = a.x - b.x;
                float dy = a.y - b.y;
                float dz = a.z - b.z;
                float len = (float)Math.sqrt(dx * dx + dy * dy + dz * dz);
                if (len > 1.0E-6f) {
                    dx /= len;
                    dy /= len;
                    dz /= len;
                }
                float half = stick.length / 2.0f;
                if (!stick.pointA.locked) {
                    a.x = cx + dx * half;
                    a.y = cy + dy * half;
                    a.z = cz + dz * half;
                }
                if (stick.pointB.locked) continue;
                b.x = cx - dx * half;
                b.y = cy - dy * half;
                b.z = cz - dz * half;
            }
        }
    }

    private void limitLength() {
        for (int x = 0; x < this.sticks.size(); ++x) {
            Stick stick = this.sticks.get(x);
            if (stick.pointB.locked) continue;
            Vector3 a = stick.pointA.position;
            Vector3 b = stick.pointB.position;
            float dx = a.x - b.x;
            float dy = a.y - b.y;
            float dz = a.z - b.z;
            float len = (float)Math.sqrt(dx * dx + dy * dy + dz * dz);
            if (len > 1.0E-6f) {
                dx /= len;
                dy /= len;
                dz /= len;
            }
            b.x = a.x - dx * stick.length;
            b.y = a.y - dy * stick.length;
            b.z = a.z - dz * stick.length;
        }
    }

    private void preventSelfClipping() {
        boolean clipped;
        int runs = 0;
        int count = this.points.size();
        do {
            clipped = false;
            for (int a = 0; a < count; ++a) {
                Point pA = this.points.get(a);
                Vector3 va = pA.position;
                for (int b = a + 1; b < count; ++b) {
                    Point pB = this.points.get(b);
                    Vector3 vb = pB.position;
                    float dx = va.x - vb.x;
                    float dy = va.y - vb.y;
                    float dz = va.z - vb.z;
                    float sq = dx * dx + dy * dy + dz * dz;
                    if (sq >= 0.99f) continue;
                    clipped = true;
                    ++runs;
                    float len = (float)Math.sqrt(sq);
                    if (len > 1.0E-6f) {
                        dx /= len;
                        dy /= len;
                        dz /= len;
                    }
                    float cx = (va.x + vb.x) * 0.5f;
                    float cy = (va.y + vb.y) * 0.5f;
                    float cz = (va.z + vb.z) * 0.5f;
                    if (!pA.locked) {
                        va.x = cx + dx * 0.5f;
                        va.y = cy + dy * 0.5f;
                        va.z = cz + dz * 0.5f;
                    }
                    if (pB.locked) continue;
                    vb.x = cx - dx * 0.5f;
                    vb.y = cy - dy * 0.5f;
                    vb.z = cz - dz * 0.5f;
                }
            }
        } while (clipped && runs < 32);
    }

    private void preventHardBends() {
        for (int i = 1; i < this.points.size() - 2; ++i) {
            Vector3 replacement;
            double angle = this.getAngle(this.points.get((int)i).position, this.points.get((int)(i - 1)).position, this.points.get((int)(i + 1)).position);
            if (angle < (double)(-this.maxBend)) {
                this.points.get((int)(i + 1)).position = replacement = this.getReplacement(this.points.get((int)i).position, this.points.get((int)(i - 1)).position, -this.maxBend * 2.0f);
            }
            if (!(angle > (double)this.maxBend)) continue;
            this.points.get((int)(i + 1)).position = replacement = this.getReplacement(this.points.get((int)i).position, this.points.get((int)(i - 1)).position, this.maxBend * 2.0f);
        }
    }

    private void preventClipping() {
        Point basePoint = this.points.get(0);
        for (int i = 1; i < this.points.size(); ++i) {
            float maxZ;
            float z;
            Point p = this.points.get(i);
            if (p.position.x - basePoint.position.x > 0.0f) {
                p.position.x = basePoint.position.x;
            }
            if ((z = basePoint.position.z - p.position.z) > (maxZ = (float)i / (float)this.points.size() * ((float)i / (float)this.points.size()) * 5.0f)) {
                p.position.z = basePoint.position.z - maxZ;
            }
            if (!(z < -maxZ)) continue;
            p.position.z = basePoint.position.z + maxZ;
        }
    }

    private Vector3 getReplacement(Vector3 middle, Vector3 prev, double target) {
        Vector3 dir = middle.clone().subtract(prev);
        dir.rotateDegrees((float)target).add(middle);
        return dir;
    }

    private double getAngle(Vector3 a, Vector3 b, Vector3 c) {
        float abx = b.x - a.x;
        float aby = b.y - a.y;
        float cbx = b.x - c.x;
        float cby = b.y - c.y;
        float dot = abx * cbx + aby * cby;
        float cross = abx * cby - aby * cbx;
        double alpha = Mth.atan2(cross, dot);
        return alpha * 180.0 / Math.PI;
    }

    @Override
    public void setGravityDirection(Vector3 gravityDirection) {
        this.gravityDirection = gravityDirection;
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
    public boolean empty() {
        return this.sticks.isEmpty();
    }

    @Override
    public void applyMovement(Vector3 movement) {
        this.points.get((int)0).prevPosition.copy(this.points.get((int)0).position);
        this.points.get((int)0).position.add(movement);
    }

    @Override
    public List<Point> getPoints() {
        return this.points;
    }

    public static class Point
    implements CapePoint {
        public Vector3 position = new Vector3(0.0f, 0.0f, 0.0f);
        public Vector3 prevPosition = new Vector3(0.0f, 0.0f, 0.0f);
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
            return Mth.lerp(delta, this.prevPosition.z, this.position.z);
        }
    }

    public static class Stick {
        public Point pointA;
        public Point pointB;
        public float length;

        @Generated
        public Stick(Point pointA, Point pointB, float length) {
            this.pointA = pointA;
            this.pointB = pointB;
            this.length = length;
        }
    }
}

