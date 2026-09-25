/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.util.voronoi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u0000 (2\u00020\u0001:\u0003)*(B7\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0004\b\n\u0010\u000bB1\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\n\u0010\u000eJ\u0019\u0010\u0010\u001a\f\u0012\b\u0012\u00060\u000fR\u00020\u00000\u0007H\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011JG\u0010\u0012\u001a\f\u0012\b\u0012\u00060\u000fR\u00020\u00000\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00022\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013J;\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J/\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ9\u0010 \u001a\u0004\u0018\u00010\b2\u0006\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b \u0010!R\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\"\u00a2\u0006\u0006\n\u0004\b\u0003\u0010#R\u0019\u0010\u0004\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\"\u00a2\u0006\u0006\n\u0004\b\u0004\u0010#R\u0019\u0010\u0005\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\"\u00a2\u0006\u0006\n\u0004\b\u0005\u0010#R\u0019\u0010\u0006\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\"\u00a2\u0006\u0006\n\u0004\b\u0006\u0010#R\u0019\u0010$\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\"\u00a2\u0006\u0006\n\u0004\b$\u0010#R\u0019\u0010%\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\"\u00a2\u0006\u0006\n\u0004\b%\u0010#R\u001e\u0010&\u001a\f\u0012\b\u0012\u00060\u000fR\u00020\u00000\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010'\u00a8\u0006+"}, d2={"Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad;", "", "", "x", "y", "x2", "y2", "", "Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad$Vec2f;", "points", "<init>", "(FFFFLjava/util/List;)V", "", "countOfPoints", "(FFFFI)V", "Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad$Polygon;", "getPolygons", "()Ljava/util/List;", "getVoronoiPolygons", "(FFFFLjava/util/List;)Ljava/util/List;", "polygon", "a", "b", "c", "clipPolygon", "(Ljava/util/List;FFF)Ljava/util/List;", "p", "", "isInside", "(Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad$Vec2f;FFF)Z", "p1", "p2", "findIntersection", "(Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad$Vec2f;Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad$Vec2f;FFF)Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad$Vec2f;", "Lkotlin/jvm/JvmField;", "F", "cx", "cy", "polygons", "Ljava/util/List;", "Companion", "Vec2f", "Polygon", "rtx.kimiko:kimiko"})
public class VoronoiOfQuad {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    public final float x;
    @JvmField
    public final float y;
    @JvmField
    public final float x2;
    @JvmField
    public final float y2;
    @JvmField
    public final float cx;
    @JvmField
    public final float cy;
    @NotNull
    private final List<Polygon> polygons;
    @NotNull
    private static final Random RANDOM = new Random();
    private static final int CANDIDATES_PER_POINT = 24;

    public VoronoiOfQuad(float x, float y, float x2, float y2, @NotNull List<Vec2f> points) {
        Intrinsics.checkNotNullParameter(points, (String)"points");
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.cx = x + (x2 - x) / 2.0f;
        this.cy = y + (y2 - y) / 2.0f;
        this.polygons = this.getVoronoiPolygons(x, y, x2, y2, points);
    }

    public VoronoiOfQuad(float x, float y, float x2, float y2, int countOfPoints) {
        this(x, y, x2, y2, VoronoiOfQuad.Companion.genPointsInBounds(x, y, x2, y2, countOfPoints));
    }

    @NotNull
    public List<Polygon> getPolygons() {
        return this.polygons;
    }

    @NotNull
    public List<Polygon> getVoronoiPolygons(float x, float y, float x2, float y2, @NotNull List<Vec2f> points) {
        Intrinsics.checkNotNullParameter(points, (String)"points");
        ArrayList<Polygon> result = new ArrayList<Polygon>();
        Vec2f[] vec2fArray = new Vec2f[]{new Vec2f(x, y), new Vec2f(x2, y), new Vec2f(x2, y2), new Vec2f(x, y2)};
        List<Vec2f> boundsRectVecList = Arrays.asList(vec2fArray);
        int countOfPoints = points.size();
        for (int iLevel0 = 0; iLevel0 < countOfPoints; ++iLevel0) {
            float c;
            float b;
            float a;
            List<Vec2f> current = new ArrayList(boundsRectVecList);
            for (int iLevel1 = 0; !(iLevel1 >= countOfPoints || iLevel1 != iLevel0 && (current = this.clipPolygon(current, a = 2.0f * (points.get((int)iLevel1).x - points.get((int)iLevel0).x), b = 2.0f * (points.get((int)iLevel1).y - points.get((int)iLevel0).y), c = points.get((int)iLevel0).x * points.get((int)iLevel0).x + points.get((int)iLevel0).y * points.get((int)iLevel0).y - (points.get((int)iLevel1).x * points.get((int)iLevel1).x + points.get((int)iLevel1).y * points.get((int)iLevel1).y))).isEmpty()); ++iLevel1) {
            }
            if (current.size() < 3) continue;
            result.add(new Polygon(new ArrayList<Vec2f>((Collection)current)));
        }
        return result;
    }

    private final List<Vec2f> clipPolygon(List<Vec2f> polygon, float a, float b, float c) {
        ArrayList<Vec2f> clipped = new ArrayList<Vec2f>();
        int n = polygon.size();
        for (int i = 0; i < n; ++i) {
            Vec2f intersect;
            Vec2f p1 = polygon.get(i);
            Vec2f p2 = polygon.get((i + 1) % n);
            boolean s1 = this.isInside(p1, a, b, c);
            boolean s2 = this.isInside(p2, a, b, c);
            if (s1) {
                if (!s2) {
                    intersect = this.findIntersection(p1, p2, a, b, c);
                    if (intersect == null) continue;
                    clipped.add(intersect);
                    continue;
                }
                clipped.add(p2);
                continue;
            }
            if (!s2) continue;
            intersect = this.findIntersection(p1, p2, a, b, c);
            if (intersect != null) {
                clipped.add(intersect);
            }
            clipped.add(p2);
        }
        return clipped;
    }

    private final boolean isInside(Vec2f p, float a, float b, float c) {
        return a * p.x + b * p.y + c <= 0.0f;
    }

    private final Vec2f findIntersection(Vec2f p1, Vec2f p2, float a, float b, float c) {
        float dx = p2.x - p1.x;
        float dy = p2.y - p1.y;
        float denom = a * dx + b * dy;
        if ((double)Math.abs(denom) < 1.0E-4) {
            return null;
        }
        float num = -(a * p1.x + b * p1.y + c);
        float t = num / denom;
        return t >= 0.0f && t <= 1.0f ? new Vec2f(p1.x + t * dx, p1.y + t * dy) : null;
    }

    @JvmStatic
    @NotNull
    public static final VoronoiOfQuad spread(float x, float y, float x2, float y2, float seedX, float seedY, float seedX2, float seedY2, int countOfPoints, float aspect, float relaxStrength, float jitterStrength, float minDistanceFactor) {
        return Companion.spread(x, y, x2, y2, seedX, seedY, seedX2, seedY2, countOfPoints, aspect, relaxStrength, jitterStrength, minDistanceFactor);
    }

    @JvmStatic
    @NotNull
    public static final VoronoiOfQuad spread(float x, float y, float x2, float y2, float seedX, float seedY, float seedX2, float seedY2, int countOfPoints, float aspect, float relaxStrength, float jitterStrength, float minDistanceFactor, @NotNull Random random) {
        return Companion.spread(x, y, x2, y2, seedX, seedY, seedX2, seedY2, countOfPoints, aspect, relaxStrength, jitterStrength, minDistanceFactor, random);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J{\u0010\u0018\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0007b\u0002\b\u0017\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0083\u0001\u0010\u0018\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001aH\u0007b\u0002\b\u0017\u00a2\u0006\u0004\b\u0018\u0010\u001cJ=\u0010!\u001a\u00020\u00042\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010 \u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b!\u0010\"J'\u0010%\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b%\u0010&JU\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001aH\u0002\u00a2\u0006\u0004\b(\u0010)J=\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u0014\u0010.\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010/\u00a8\u00060"}, d2={"Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad.Companion;", "", "<init>", "()V", "", "value", "wrapDegrees", "(F)F", "x", "y", "x2", "y2", "seedX", "seedY", "seedX2", "seedY2", "", "countOfPoints", "aspect", "relaxStrength", "jitterStrength", "minDistanceFactor", "Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad;", "Lkotlin/jvm/JvmStatic;", "spread", "(FFFFFFFFIFFFF)Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad;", "Ljava/util/Random;", "random", "(FFFFFFFFIFFFFLjava/util/Random;)Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad;", "", "Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad$Vec2f;", "points", "skipIndex", "nearestDistance", "(Ljava/util/List;IFFF)F", "min", "max", "clamp", "(FFF)F", "minDistance", "genSpreadPointsInBounds", "(FFFFIFFLjava/util/Random;)Ljava/util/List;", "genPointsInBounds", "(FFFFI)Ljava/util/List;", "RANDOM", "Ljava/util/Random;", "CANDIDATES_PER_POINT", "I", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float wrapDegrees(float value) {
            float wrapped = value % 360.0f;
            if (wrapped >= 180.0f) {
                wrapped -= 360.0f;
            }
            if (wrapped < -180.0f) {
                wrapped += 360.0f;
            }
            return wrapped;
        }

        @JvmStatic
        @NotNull
        public final VoronoiOfQuad spread(float x, float y, float x2, float y2, float seedX, float seedY, float seedX2, float seedY2, int countOfPoints, float aspect, float relaxStrength, float jitterStrength, float minDistanceFactor) {
            return this.spread(x, y, x2, y2, seedX, seedY, seedX2, seedY2, countOfPoints, aspect, relaxStrength, jitterStrength, minDistanceFactor, RANDOM);
        }

        @JvmStatic
        @NotNull
        public final VoronoiOfQuad spread(float x, float y, float x2, float y2, float seedX, float seedY, float seedX2, float seedY2, int countOfPoints, float aspect, float relaxStrength, float jitterStrength, float minDistanceFactor, @NotNull Random random) {
            List<Polygon> polygons;
            Intrinsics.checkNotNullParameter((Object)random, (String)"random");
            float spacing = (float)Math.sqrt((seedX2 - seedX) * aspect * (seedY2 - seedY) / (float)Math.max(1, countOfPoints));
            float minDistance = spacing * minDistanceFactor;
            List<Vec2f> points = this.genSpreadPointsInBounds(seedX, seedY, seedX2, seedY2, countOfPoints, aspect, minDistance, random);
            if (relaxStrength > 0.001f && (polygons = new VoronoiOfQuad(x, y, x2, y2, points).getPolygons()).size() == points.size()) {
                int n = ((Collection)points).size();
                for (int i = 0; i < n; ++i) {
                    Vec2f point = points.get(i);
                    Vec2f centroid = polygons.get((int)i).center;
                    point.x = this.clamp(point.x + (centroid.x - point.x) * relaxStrength, seedX, seedX2);
                    point.y = this.clamp(point.y + (centroid.y - point.y) * relaxStrength, seedY, seedY2);
                }
            }
            if (jitterStrength > 0.001f) {
                float offsetX = spacing * jitterStrength / aspect;
                float offsetY = spacing * jitterStrength;
                int n = ((Collection)points).size();
                for (int i = 0; i < n; ++i) {
                    float movedY;
                    Vec2f point = points.get(i);
                    float movedX = this.clamp(point.x + (random.nextFloat() * 2.0f - 1.0f) * offsetX, seedX, seedX2);
                    if (!(this.nearestDistance(points, i, movedX, movedY = this.clamp(point.y + (random.nextFloat() * 2.0f - 1.0f) * offsetY, seedY, seedY2), aspect) >= minDistance * minDistance)) continue;
                    point.x = movedX;
                    point.y = movedY;
                }
            }
            return new VoronoiOfQuad(x, y, x2, y2, points);
        }

        private final float nearestDistance(List<Vec2f> points, int skipIndex, float x, float y, float aspect) {
            float nearest = Float.MAX_VALUE;
            int n = ((Collection)points).size();
            for (int i = 0; i < n; ++i) {
                if (i == skipIndex) continue;
                Vec2f other = points.get(i);
                float dx = (x - other.x) * aspect;
                float dy = y - other.y;
                nearest = Math.min(nearest, dx * dx + dy * dy);
            }
            return nearest;
        }

        private final float clamp(float value, float min, float max) {
            return Math.max(min, Math.min(max, value));
        }

        private final List<Vec2f> genSpreadPointsInBounds(float x, float y, float x2, float y2, int countOfPoints, float aspect, float minDistance, Random random) {
            ArrayList<Vec2f> points = new ArrayList<Vec2f>(countOfPoints);
            float minDistanceSq = minDistance * minDistance;
            for (int i = 0; i < countOfPoints; ++i) {
                Vec2f best = null;
                float bestDistance = -1.0f;
                for (int candidate = 0; candidate < 24; ++candidate) {
                    Vec2f probe = new Vec2f(x + random.nextFloat() * (x2 - x), y + random.nextFloat() * (y2 - y));
                    if (points.isEmpty()) {
                        best = probe;
                        bestDistance = Float.MAX_VALUE;
                        break;
                    }
                    float nearest = this.nearestDistance((List<Vec2f>)points, -1, probe.x, probe.y, aspect);
                    if (nearest > bestDistance) {
                        bestDistance = nearest;
                        best = probe;
                    }
                    if (bestDistance >= minDistanceSq) break;
                }
                if (best == null || !(bestDistance >= minDistanceSq)) continue;
                points.add(best);
            }
            return points;
        }

        private final List<Vec2f> genPointsInBounds(float x, float y, float x2, float y2, int countOfPoints) {
            ArrayList<Vec2f> points = new ArrayList<Vec2f>(countOfPoints);
            for (int i = 0; i < countOfPoints; ++i) {
                points.add(new Vec2f(x + RANDOM.nextFloat() * (x2 - x), y + RANDOM.nextFloat() * (y2 - y)));
            }
            return points;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0096\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0011\u0010\t\u001a\u00060\u0000R\u00020\b\u00a2\u0006\u0004\b\t\u0010\nJ\u0013\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ!\u0010\u0013\u001a\u00060\u0000R\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0013\u0010\u0014J!\u0010\u0017\u001a\u00060\u0000R\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0017\u0010\u0014J\u0019\u0010\u0019\u001a\u00060\u0000R\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0019\u0010\u001aJ!\u0010\u001d\u001a\u00060\u0000R\u00020\b2\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0010\u00a2\u0006\u0004\b\u001d\u0010\u0014J!\u0010\u001e\u001a\u00060\u0000R\u00020\b2\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u0010\u00a2\u0006\u0004\b\u001e\u0010\u0014J\u001d\u0010!\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u0010\u00a2\u0006\u0004\b!\u0010\"J)\u0010$\u001a\u00060\u0000R\u00020\b2\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u0010\u00a2\u0006\u0004\b$\u0010%J)\u0010&\u001a\u00060\u0000R\u00020\b2\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u0010\u00a2\u0006\u0004\b&\u0010%J)\u0010(\u001a\u00060\u0000R\u00020\b2\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u0010\u00a2\u0006\u0004\b(\u0010%J)\u0010)\u001a\u00060\u0000R\u00020\b2\u0006\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u0010\u00a2\u0006\u0004\b)\u0010%R)\u0010+\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b*\u00a2\u0006\u0006\n\u0004\b+\u0010,R\u0019\u0010-\u001a\u00020\u00038\u0006X\u0087\u0004\u0092\u0002\u0002\b*\u00a2\u0006\u0006\n\u0004\b-\u0010.\u00a8\u0006/"}, d2={"Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad$Polygon;", "", "Ljava/util/ArrayList;", "Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad$Vec2f;", "Lkotlin/collections/ArrayList;", "listVertices", "<init>", "(Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad;Ljava/util/ArrayList;)V", "Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad;", "copy", "()Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad$Polygon;", "", "getAllVertices", "()Ljava/util/List;", "getCenter", "()Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad$Vec2f;", "", "mX", "mY", "moveXY", "(FF)Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad$Polygon;", "xIn", "yIn", "setPolygonMidPos", "angle360", "rotateAngleOfCenter", "(F)Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad$Polygon;", "angleDegrees", "aspect", "rotateAroundCenter", "twirlAroundCenter", "x", "y", "distanceToAtCenter", "(FF)F", "offset", "translateToPosLoc", "(FFF)Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad$Polygon;", "translateAwayPosLoc", "mul", "rotateAtXOfAngleAwayPos", "rotateAtYOfAngleAwayPos", "Lkotlin/jvm/JvmField;", "list", "Ljava/util/ArrayList;", "center", "Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad$Vec2f;", "rtx.kimiko:kimiko"})
    public class Polygon {
        @JvmField
        @NotNull
        public final ArrayList<Vec2f> list;
        @JvmField
        @NotNull
        public final Vec2f center;

        public Polygon(ArrayList<Vec2f> listVertices) {
            Intrinsics.checkNotNullParameter(listVertices, (String)"listVertices");
            this.list = listVertices;
            this.center = this.getCenter();
        }

        @NotNull
        public final Polygon copy() {
            ArrayList<Vec2f> copied = new ArrayList<Vec2f>(this.list.size());
            Iterator<Vec2f> iterator = this.list.iterator();
            Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
            Iterator<Vec2f> iterator2 = iterator;
            while (iterator2.hasNext()) {
                Vec2f vec = (Vec2f) (iterator2.next());
                copied.add(new Vec2f(vec.x, vec.y));
            }
            return new Polygon(copied);
        }

        @NotNull
        public final List<Vec2f> getAllVertices() {
            return this.list;
        }

        @NotNull
        public final Vec2f getCenter() {
            Vec2f result = new Vec2f(0.0f, 0.0f);
            Iterator<Vec2f> iterator = this.list.iterator();
            Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
            Iterator<Vec2f> iterator2 = iterator;
            while (iterator2.hasNext()) {
                Vec2f vec = (Vec2f) (iterator2.next());
                result.x += vec.x;
                result.y += vec.y;
            }
            int size = Math.max(1, this.list.size());
            result.x /= (float)size;
            result.y /= (float)size;
            return result;
        }

        @NotNull
        public final Polygon moveXY(float mX, float mY) {
            this.center.x += mX;
            this.center.y += mY;
            for (Vec2f vec : this.list) {
                vec.x += mX;
                vec.y += mY;
            }
            return this;
        }

        @NotNull
        public final Polygon setPolygonMidPos(float xIn, float yIn) {
            float dx = xIn - this.center.x;
            float dy = yIn - this.center.y;
            if (dx == 0.0f && dy == 0.0f) {
                return this;
            }
            return this.moveXY(dx, dy);
        }

        @NotNull
        public final Polygon rotateAngleOfCenter(float angle360) {
            if (angle360 == 0.0f) {
                return this;
            }
            Iterator<Vec2f> iterator = this.list.iterator();
            Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
            Iterator<Vec2f> iterator2 = iterator;
            while (iterator2.hasNext()) {
                Vec2f vec = (Vec2f) (iterator2.next());
                float dx = vec.x - this.center.x;
                float dy = vec.y - this.center.y;
                float radianAngle = (float)Math.toRadians(Math.toDegrees(Math.atan2(dy, dx)) + (double)angle360 - (double)90.0f);
                float dst = (float)Math.sqrt(dx * dx + dy * dy);
                vec.x = this.center.x - (float)Math.sin(radianAngle) * dst;
                vec.y = this.center.y + (float)Math.cos(radianAngle) * dst;
            }
            return this;
        }

        @NotNull
        public final Polygon rotateAroundCenter(float angleDegrees, float aspect) {
            if (angleDegrees == 0.0f) {
                return this;
            }
            float angle = (float)Math.toRadians(angleDegrees);
            float s = (float)Math.sin(angle);
            float c = (float)Math.cos(angle);
            Iterator<Vec2f> iterator = this.list.iterator();
            Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
            Iterator<Vec2f> iterator2 = iterator;
            while (iterator2.hasNext()) {
                Vec2f vec = (Vec2f) (iterator2.next());
                float dx = (vec.x - this.center.x) * aspect;
                float dy = vec.y - this.center.y;
                vec.x = this.center.x + (dx * c - dy * s) / aspect;
                vec.y = this.center.y + (dx * s + dy * c);
            }
            return this;
        }

        @NotNull
        public final Polygon twirlAroundCenter(float angleDegrees, float aspect) {
            float dy;
            float dx;
            if (angleDegrees == 0.0f) {
                return this;
            }
            float maxDist = 0.0f;
            for (Vec2f vec : this.list) {
                dx = (vec.x - this.center.x) * aspect;
                dy = vec.y - this.center.y;
                maxDist = Math.max(maxDist, (float)Math.sqrt(dx * dx + dy * dy));
            }
            if (maxDist <= 1.0E-6f) {
                return this;
            }
            for (Vec2f vec : this.list) {
                dx = (vec.x - this.center.x) * aspect;
                dy = vec.y - this.center.y;
                float dst = (float)Math.sqrt(dx * dx + dy * dy);
                float angle = (float)Math.toRadians(angleDegrees * (dst / maxDist));
                float s = (float)Math.sin(angle);
                float c = (float)Math.cos(angle);
                float rx = dx * c - dy * s;
                float ry = dx * s + dy * c;
                vec.x = this.center.x + rx / aspect;
                vec.y = this.center.y + ry;
            }
            return this;
        }

        public final float distanceToAtCenter(float x, float y) {
            float dx = this.center.x - x;
            float dy = this.center.y - y;
            return (float)Math.sqrt(dx * dx + dy * dy);
        }

        @NotNull
        public final Polygon translateToPosLoc(float x, float y, float offset) {
            float dx = this.center.x - x;
            float dy = this.center.y - y;
            float angleRadian = (float)(Math.atan2(dy, dx) + Math.toRadians(90.0));
            return this.moveXY(-((float)Math.sin(angleRadian)) * offset, (float)Math.cos(angleRadian) * offset);
        }

        @NotNull
        public final Polygon translateAwayPosLoc(float x, float y, float offset) {
            return this.translateToPosLoc(x, y, -offset);
        }

        @NotNull
        public final Polygon rotateAtXOfAngleAwayPos(float x, float y, float mul) {
            float dx = this.center.x - x;
            float dy = this.center.y - y;
            return this.rotateAngleOfCenter(Companion.wrapDegrees(dy * mul * (float)(dx > 0.0f ? 1 : -1)));
        }

        @NotNull
        public final Polygon rotateAtYOfAngleAwayPos(float x, float y, float mul) {
            float dx = this.center.x - x;
            float dy = this.center.y - y;
            return this.rotateAngleOfCenter(Companion.wrapDegrees(dx * mul * (float)(dy < 0.0f ? 1 : -1)));
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\b\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u001d\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000b\u0010\tR\u001b\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\f\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\rR\u001b\u0010\u0004\u001a\u00020\u00028\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\f\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/utils/render/util/voronoi/VoronoiOfQuad$Vec2f;", "", "", "x", "y", "<init>", "(FF)V", "x2", "getUVTexX", "(FF)F", "y2", "getUVTexY", "Lkotlin/jvm/JvmField;", "F", "rtx.kimiko:kimiko"})
    public static final class Vec2f {
        @JvmField
        public float x;
        @JvmField
        public float y;

        public Vec2f(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public final float getUVTexX(float x, float x2) {
            return (this.x - x) / (x2 - x);
        }

        public final float getUVTexY(float y, float y2) {
            return 1.0f - (y2 - this.y) / (y2 - y);
        }
    }
}

