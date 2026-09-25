/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Visuals.particles.dashlines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.modules.impl.Visuals.particles.WorldParticleUtil;
import rtx.kimiko.api.modules.impl.Visuals.particles.dashlines.DashLine;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0006\n\u0002\b\u000b\u0018\u0000 G2\u00020\u0001:\u0001GB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\u0003J]\u0010\u0019\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0017\u00a2\u0006\u0004\b\u0019\u0010\u001aJ/\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\bH\u0002\u00a2\u0006\u0004\b \u0010\u0003J\u000f\u0010!\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b!\u0010\u0003J'\u0010\"\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\"\u0010#JW\u0010$\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b$\u0010%JG\u0010(\u001a\u00020\u00172\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b(\u0010)R\u001a\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00050*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b+\u0010,R\u001a\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b-\u0010,R\u0014\u0010/\u001a\u00020.8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0016\u00101\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b1\u00102R\u0016\u00103\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u00102R\u0016\u00104\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u00102R\u0016\u00105\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b5\u00102R\u0016\u00106\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b6\u00102R\u0016\u00107\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b7\u00102R\u0016\u00108\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b8\u00102R\u0016\u00109\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b9\u00102R\u0016\u0010:\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b:\u00102R\u0016\u0010;\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b;\u00102R\u0016\u0010<\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u00102R\u0016\u0010>\u001a\u00020=8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0016\u0010@\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b@\u00102R\u0016\u0010A\u001a\u00020=8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bA\u0010?R\u0016\u0010B\u001a\u00020=8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bB\u0010?R\u0016\u0010C\u001a\u00020=8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bC\u0010?R\u0016\u0010D\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bD\u0010ER\u0016\u0010F\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bF\u00102\u00a8\u0006H"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/dashlines/DashLineField;", "", "<init>", "()V", "", "Lrtx/kimiko/api/modules/impl/Visuals/particles/dashlines/DashLine;", "alive", "()Ljava/util/List;", "", "clear", "Lnet/minecraft/MinecraftClient;", "client", "Lnet/minecraft/Vec3d;", "center", "", "frameSeconds", "", "targetCount", "radius", "lengthScale", "widthScale", "speedScale", "spreadDegrees", "", "motionOnly", "update", "(Lnet/minecraft/MinecraftClient;Lnet/minecraft/Vec3d;FIFFFFFZ)V", "position", "yaw", "dt", "trackMotion", "(Lnet/minecraft/Vec3d;FFZ)V", "normalizeFlow", "buildBasis", "stepLines", "(FLnet/minecraft/Vec3d;F)V", "spawnLines", "(Lnet/minecraft/MinecraftClient;Lnet/minecraft/Vec3d;FIFFFFF)V", "streakSpeed", "tilt", "emit", "(Lnet/minecraft/MinecraftClient;Lnet/minecraft/Vec3d;FFFFF)Z", "Ljava/util/ArrayList;", "lines", "Ljava/util/ArrayList;", "pool", "Ljava/util/Random;", "random", "Ljava/util/Random;", "flowX", "F", "flowY", "flowZ", "sideX", "sideY", "sideZ", "upX", "upY", "upZ", "speed", "intensity", "", "travelAccum", "D", "timeAccum", "lastX", "lastY", "lastZ", "hasLast", "Z", "spawnAccum", "Companion", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nDashLineField.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DashLineField.kt\nrtx/kimiko/api/modules/impl/Visuals/particles/dashlines/DashLineField\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,329:1\n1#2:330\n*E\n"})
public final class DashLineField {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ArrayList<DashLine> lines = new ArrayList();
    @NotNull
    private final ArrayList<DashLine> pool = new ArrayList();
    @NotNull
    private final Random random = new Random();
    private float flowX;
    private float flowY;
    private float flowZ = 1.0f;
    private float sideX = 1.0f;
    private float sideY;
    private float sideZ;
    private float upX;
    private float upY = 1.0f;
    private float upZ;
    private float speed;
    private float intensity;
    private double travelAccum;
    private float timeAccum;
    private double lastX;
    private double lastY;
    private double lastZ;
    private boolean hasLast;
    private float spawnAccum;
    private static final float TAU = (float)Math.PI * 2;
    private static final float MAX_FRAME_SECONDS = 0.2f;
    private static final float SAMPLE_WINDOW = 0.1f;
    private static final float SPEED_BLEND = 0.5f;
    private static final float HEADING_BLEND = 9.0f;
    private static final float HEADING_FLOOR = 0.35f;
    private static final float VERTICAL_DAMP = 0.35f;
    private static final float INTENSITY_BLEND = 5.0f;
    private static final float IDLE_INTENSITY = 0.3f;
    private static final float SPEED_FLOOR = 1.1f;
    private static final float SPEED_FULL = 5.6f;
    private static final float SPEED_BASE = 7.0f;
    private static final float SPEED_GAIN = 2.4f;
    private static final float SPEED_MIN = 0.82f;
    private static final float SPEED_SPAN = 0.42f;
    private static final float LENGTH_BASE = 1.7f;
    private static final float LENGTH_MIN = 0.5f;
    private static final float LENGTH_SPAN = 1.1f;
    private static final float WIDTH_BASE = 0.022f;
    private static final float WIDTH_MIN = 0.55f;
    private static final float WIDTH_SPAN = 0.95f;
    private static final float BRIGHT_MIN = 0.55f;
    private static final float BELLY_MIN = 0.16f;
    private static final float BELLY_SPAN = 0.2f;
    private static final float LATERAL_NEAR = 0.22f;
    private static final float AHEAD_NEAR = 0.55f;
    private static final float AHEAD_SPAN = 1.15f;
    private static final float AHEAD_PAD = 1.6f;
    private static final float VERTICAL_TILT = 0.75f;
    private static final float LEAVE_FACTOR = 1.45f;
    private static final float LEAVE_PAD = 4.0f;
    private static final float SPAWN_RATE = 5.5f;
    private static final int SPAWN_BURST = 12;
    private static final int HARD_LIMIT = 260;
    private static final float MIN_LIFE = 0.35f;
    private static final float MAX_LIFE = 3.0f;

    @NotNull
    public final List<DashLine> alive() {
        return this.lines;
    }

    public final void clear() {
        this.pool.addAll((Collection<DashLine>)this.lines);
        this.lines.clear();
        this.hasLast = false;
        this.speed = 0.0f;
        this.intensity = 0.0f;
        this.travelAccum = 0.0;
        this.timeAccum = 0.0f;
        this.spawnAccum = 0.0f;
    }

    public final void update(@NotNull MinecraftClient client, @NotNull Vec3d center, float frameSeconds, int targetCount, float radius, float lengthScale, float widthScale, float speedScale, float spreadDegrees, boolean motionOnly) {
        Intrinsics.checkNotNullParameter((Object)client, (String)"client");
        Intrinsics.checkNotNullParameter((Object)center, (String)"center");
        ClientPlayerEntity player = client.player;
        if (client.world == null || player == null) {
            this.clear();
            return;
        }
        float dt = MathHelper.clamp((float)frameSeconds, (float)0.0f, (float)0.2f);
        Vec3d vec3d2 = player.getEntityPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        this.trackMotion(vec3d2, player.getYaw(), dt, motionOnly);
        this.stepLines(dt, center, radius);
        this.spawnLines(client, center, dt, targetCount, radius, lengthScale, widthScale, speedScale, spreadDegrees);
    }

    private final void trackMotion(Vec3d position, float yaw, float dt, boolean motionOnly) {
        float moveLength;
        if (!this.hasLast) {
            this.lastX = position.x;
            this.lastY = position.y;
            this.lastZ = position.z;
            this.hasLast = true;
            return;
        }
        double dx = position.x - this.lastX;
        double dy = position.y - this.lastY;
        double dz = position.z - this.lastZ;
        this.lastX = position.x;
        this.lastY = position.y;
        this.lastZ = position.z;
        this.travelAccum += Math.sqrt(dx * dx + dy * dy + dz * dz);
        this.timeAccum += dt;
        if (this.timeAccum >= 0.1f) {
            float sampled = (float)(this.travelAccum / (double)this.timeAccum);
            this.speed += (sampled - this.speed) * 0.5f;
            this.travelAccum = 0.0;
            this.timeAccum = 0.0f;
        }
        if ((moveLength = (float)Math.sqrt(dx * dx + dy * dy + dz * dz)) > 1.0E-4f && this.speed > 0.35f) {
            float blend = MathHelper.clamp((float)(9.0f * dt), (float)0.0f, (float)1.0f);
            this.flowX += ((float)(dx / (double)moveLength) - this.flowX) * blend;
            this.flowY += ((float)(dy / (double)moveLength) * 0.35f - this.flowY) * blend;
            this.flowZ += ((float)(dz / (double)moveLength) - this.flowZ) * blend;
        } else if (this.speed <= 0.35f) {
            double radians = Math.toRadians(-yaw);
            float blend = MathHelper.clamp((float)(4.5f * dt), (float)0.0f, (float)1.0f);
            this.flowX += ((float)Math.sin(radians) - this.flowX) * blend;
            this.flowY += (0.0f - this.flowY) * blend;
            this.flowZ += ((float)Math.cos(radians) - this.flowZ) * blend;
        }
        this.normalizeFlow();
        this.buildBasis();
        float target = MathHelper.clamp((float)((this.speed - 1.1f) / 4.5f), (float)0.0f, (float)1.0f);
        float floor = motionOnly ? 0.0f : 0.3f;
        this.intensity += (Math.max(target, floor) - this.intensity) * MathHelper.clamp((float)(5.0f * dt), (float)0.0f, (float)1.0f);
    }

    private final void normalizeFlow() {
        float length = (float)Math.sqrt(this.flowX * this.flowX + this.flowY * this.flowY + this.flowZ * this.flowZ);
        if (length < 1.0E-4f) {
            this.flowX = 0.0f;
            this.flowY = 0.0f;
            this.flowZ = 1.0f;
            return;
        }
        this.flowX /= length;
        this.flowY /= length;
        this.flowZ /= length;
    }

    private final void buildBasis() {
        this.sideX = this.flowZ;
        this.sideY = 0.0f;
        this.sideZ = -this.flowX;
        float length = (float)Math.sqrt(this.sideX * this.sideX + this.sideZ * this.sideZ);
        if (length < 1.0E-4f) {
            this.sideX = 1.0f;
            this.sideY = 0.0f;
            this.sideZ = 0.0f;
            length = 1.0f;
        }
        this.sideX /= length;
        this.sideZ /= length;
        this.upX = this.flowY * this.sideZ - this.flowZ * this.sideY;
        this.upY = this.flowZ * this.sideX - this.flowX * this.sideZ;
        this.upZ = this.flowX * this.sideY - this.flowY * this.sideX;
        float upLength = (float)Math.sqrt(this.upX * this.upX + this.upY * this.upY + this.upZ * this.upZ);
        if (upLength < 1.0E-4f) {
            this.upX = 0.0f;
            this.upY = 1.0f;
            this.upZ = 0.0f;
            return;
        }
        this.upX /= upLength;
        this.upY /= upLength;
        this.upZ /= upLength;
    }

    private final void stepLines(float dt, Vec3d center, float radius) {
        float it = radius * 1.45f + 4.0f;
        boolean bl = false;
        float leaveSq = it * it;
        int write = 0;
        int n = ((Collection)this.lines).size();
        for (int index = 0; index < n; ++index) {
            boolean gone;
            DashLine line = (DashLine) (this.lines.get(index));
            line.step(dt);
            double dx = line.getPosX() - center.x;
            double dy = line.getPosY() - center.y;
            double dz = line.getPosZ() - center.z;
            boolean bl2 = gone = line.expired() || dx * dx + dy * dy + dz * dz > (double)leaveSq;
            if (gone) {
                this.pool.add(line);
                continue;
            }
            this.lines.set(write, line);
            ++write;
        }
        while (this.lines.size() > write) {
            this.lines.remove(this.lines.size() - 1);
        }
    }

    private final void spawnLines(MinecraftClient client, Vec3d center, float dt, int targetCount, float radius, float lengthScale, float widthScale, float speedScale, float spreadDegrees) {
        int wanted = Math.round((float)targetCount * this.intensity);
        if (wanted <= 0) {
            this.spawnAccum = 0.0f;
            return;
        }
        int missing = wanted - this.lines.size();
        if (missing <= 0) {
            return;
        }
        this.spawnAccum += (float)missing * 5.5f * dt;
        int budget = (int)this.spawnAccum;
        if (budget <= 0) {
            return;
        }
        this.spawnAccum -= (float)budget;
        budget = Math.min(budget, Math.min(missing, 12));
        float streakSpeed = (7.0f + this.speed * 2.4f) * speedScale;
        float tilt = (float)Math.tan(Math.toRadians(spreadDegrees));
        int spawned = 0;
        for (int attempts = 0; spawned < budget && attempts < budget * 3 && this.lines.size() < 260; ++attempts) {
            if (!this.emit(client, center, radius, lengthScale, widthScale, streakSpeed, tilt)) continue;
            ++spawned;
        }
    }

    private final boolean emit(MinecraftClient client, Vec3d center, float radius, float lengthScale, float widthScale, float streakSpeed, float tilt) {
        DashLine dashLine;
        float dz;
        float dy;
        float jitterUp;
        double z;
        double y;
        float offsetSin;
        float offsetCos;
        float angle = this.random.nextFloat() * ((float)Math.PI * 2);
        float lateral = radius * (0.22f + this.random.nextFloat() * 0.78f);
        float ahead = radius * (0.55f + this.random.nextFloat() * 1.15f) + 1.6f;
        double x = center.x + (double)(this.flowX * ahead + this.sideX * (offsetCos = (float)Math.cos(angle) * lateral) + this.upX * (offsetSin = (float)Math.sin(angle) * lateral));
        if (WorldParticleUtil.isSolidCollisionBlock(client, x, y = center.y + (double)(this.flowY * ahead + this.sideY * offsetCos + this.upY * offsetSin), z = center.z + (double)(this.flowZ * ahead + this.sideZ * offsetCos + this.upZ * offsetSin))) {
            return false;
        }
        float jitterSide = (this.random.nextFloat() * 2.0f - 1.0f) * tilt;
        float dx = -this.flowX + this.sideX * jitterSide + this.upX * (jitterUp = (this.random.nextFloat() * 2.0f - 1.0f) * tilt * 0.75f);
        float length = (float)Math.sqrt(dx * dx + (dy = -this.flowY + this.sideY * jitterSide + this.upY * jitterUp) * dy + (dz = -this.flowZ + this.sideZ * jitterSide + this.upZ * jitterUp) * dz);
        if (length < 1.0E-4f) {
            return false;
        }
        dx /= length;
        dy /= length;
        dz /= length;
        float roll = this.random.nextFloat();
        float streak = 1.7f * lengthScale * (0.5f + roll * 1.1f) * (0.65f + 0.55f * this.intensity);
        float width = 0.022f * widthScale * (0.55f + this.random.nextFloat() * 0.95f);
        float brightness = 0.55f + this.random.nextFloat() * 0.45f;
        float belly = 0.16f + this.random.nextFloat() * 0.2f;
        float travel = ahead + radius * 1.45f + streak;
        float life = MathHelper.clamp((float)(travel / Math.max(1.0f, streakSpeed)), (float)0.35f, (float)3.0f);
        if (this.pool.isEmpty()) {
            dashLine = new DashLine();
        } else {
            DashLine dashLine2 = this.pool.remove(this.pool.size() - 1);
            Intrinsics.checkNotNullExpressionValue((Object)dashLine2, (String)"removeAt(...)");
            dashLine = dashLine2;
        }
        DashLine line = dashLine;
        line.launch(x, y, z, dx, dy, dz, streakSpeed * (0.82f + this.random.nextFloat() * 0.42f), streak, width, brightness, belly, life);
        this.lines.add(line);
        return true;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b!\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\u0006R\u0014\u0010\f\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u0006R\u0014\u0010\u000e\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0006R\u0014\u0010\u0010\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0006R\u0014\u0010\u0012\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0006R\u0014\u0010\u0014\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0006R\u0014\u0010\u0015\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0006R\u0014\u0010\u0016\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0006R\u0014\u0010\u0017\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0006R\u0014\u0010\u0018\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0006R\u0014\u0010\u0019\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0006R\u0014\u0010\u001a\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0006R\u0014\u0010\u001b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0006R\u0014\u0010\u001c\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u0006R\u0014\u0010\u001d\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u0006R\u0014\u0010\u001e\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u0006R\u0014\u0010\u001f\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u0006R\u0014\u0010 \u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010\u0006R\u0014\u0010!\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b!\u0010\u0006R\u0014\u0010\"\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010\u0006R\u0014\u0010#\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b#\u0010\u0006R\u0014\u0010$\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010\u0006R\u0014\u0010%\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010\u0006R\u0014\u0010'\u001a\u00020&8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020&8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010(R\u0014\u0010*\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010\u0006R\u0014\u0010+\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010\u0006\u00a8\u0006,"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/particles/dashlines/DashLineField.Companion;", "", "<init>", "()V", "", "TAU", "F", "MAX_FRAME_SECONDS", "SAMPLE_WINDOW", "SPEED_BLEND", "HEADING_BLEND", "HEADING_FLOOR", "VERTICAL_DAMP", "INTENSITY_BLEND", "IDLE_INTENSITY", "SPEED_FLOOR", "SPEED_FULL", "SPEED_BASE", "SPEED_GAIN", "SPEED_MIN", "SPEED_SPAN", "LENGTH_BASE", "LENGTH_MIN", "LENGTH_SPAN", "WIDTH_BASE", "WIDTH_MIN", "WIDTH_SPAN", "BRIGHT_MIN", "BELLY_MIN", "BELLY_SPAN", "LATERAL_NEAR", "AHEAD_NEAR", "AHEAD_SPAN", "AHEAD_PAD", "VERTICAL_TILT", "LEAVE_FACTOR", "LEAVE_PAD", "SPAWN_RATE", "", "SPAWN_BURST", "I", "HARD_LIMIT", "MIN_LIFE", "MAX_LIFE", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

