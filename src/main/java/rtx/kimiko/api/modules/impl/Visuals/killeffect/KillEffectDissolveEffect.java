/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Quaternionf
 *  org.joml.Vector3f
 */
package rtx.kimiko.api.modules.impl.Visuals.killeffect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.function.IntUnaryOperator;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.LivingEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.modules.impl.Visuals.killeffect.KillEffectModelCapture;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 $2\u00020\u0001:\u0003%&$B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0006\u00a2\u0006\u0004\b\t\u0010\u0003J\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ%\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011\u00a2\u0006\u0004\b\u0013\u0010\u0014J/\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aR$\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\u001c0\u001bj\b\u0012\u0004\u0012\u00020\u001c`\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR$\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\u00040\u001bj\b\u0012\u0004\u0012\u00020\u0004`\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010\u001fR\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010#\u00a8\u0006'"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDissolveEffect;", "", "<init>", "()V", "Lnet/minecraft/LivingEntity;", "entity", "", "queue", "(Lnet/minecraft/LivingEntity;)V", "clear", "", "isIdle", "()Z", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "event", "Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDissolveEffect$Settings;", "settings", "Ljava/util/function/IntUnaryOperator;", "colorProvider", "render", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDissolveEffect$Settings;Ljava/util/function/IntUnaryOperator;)V", "", "partialTick", "", "now", "spawn", "(Lnet/minecraft/LivingEntity;FJLrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDissolveEffect$Settings;)V", "Ljava/util/ArrayList;", "Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDissolveEffect$ModelParticle;", "Lkotlin/collections/ArrayList;", "particles", "Ljava/util/ArrayList;", "pending", "Ljava/util/Random;", "random", "Ljava/util/Random;", "Companion", "Settings", "ModelParticle", "rtx.kimiko:kimiko"})
public final class KillEffectDissolveEffect {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ArrayList<ModelParticle> particles = new ArrayList();
    @NotNull
    private final ArrayList<LivingEntity> pending = new ArrayList();
    @NotNull
    private final Random random = new Random();
    @NotNull
    private static final Identifier GLOW_TEXTURE;
    private static final int MAX_PARTICLES = 4000;

    public final void queue(@Nullable LivingEntity entity) {
        if (entity == null || this.pending.size() >= 8) {
            return;
        }
        this.pending.add(entity);
    }

    public final void clear() {
        this.particles.clear();
        this.pending.clear();
    }

    public final boolean isIdle() {
        return this.particles.isEmpty() && this.pending.isEmpty();
    }

    public final void render(@NotNull WorldRenderEvent event, @NotNull Settings settings, @NotNull IntUnaryOperator colorProvider) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        Intrinsics.checkNotNullParameter((Object)settings, (String)"settings");
        Intrinsics.checkNotNullParameter((Object)colorProvider, (String)"colorProvider");
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        Camera camera = event.getCamera();
        if (minecraft.world == null || camera == null) {
            this.clear();
            return;
        }
        long now = System.currentTimeMillis();
        if (!this.pending.isEmpty()) {
            float partialTick = MathHelper.clamp((float)event.getPartialTicks(), (float)0.0f, (float)1.0f);
            for (LivingEntity entity : this.pending) {
                this.spawn(entity, partialTick, now, settings);
            }
            this.pending.clear();
        }
        if (this.particles.isEmpty()) {
            return;
        }
        long holdMs = Math.max(0L, settings.holdMs());
        long fadeMs = Math.max(1L, settings.fadeMs());
        long maxLifeMs = holdMs + fadeMs;
        this.particles.removeIf(p -> p.isDead(now, maxLifeMs));
        if (this.particles.isEmpty()) {
            return;
        }
        Vec3d vec3d2 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d cameraPos = vec3d2;
        Quaternionf quaternionf = minecraft.gameRenderer.getCamera().getRotation();
        Intrinsics.checkNotNullExpressionValue((Object)quaternionf, (String)"rotation(...)");
        Quaternionf rotation = quaternionf;
        Vector3f right = rotation.transform(new Vector3f(1.0f, 0.0f, 0.0f));
        Vector3f up = rotation.transform(new Vector3f(0.0f, 1.0f, 0.0f));
        VertexConsumerProvider.Immediate immediate2 = minecraft.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        VertexConsumerProvider.Immediate provider = immediate2;
        RenderLayer renderLayer2 = settings.throughWalls() ? ClientPipelines.WORLD_PARTICLES_GLOW_NODEPTH.apply(GLOW_TEXTURE) : ClientPipelines.WORLD_PARTICLES_GLOW.apply(GLOW_TEXTURE);
        Intrinsics.checkNotNull((Object)renderLayer2);
        RenderLayer renderType = renderLayer2;
        VertexConsumer vertexConsumer2 = provider.getBuffer(renderType);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer consumer = vertexConsumer2;
        MatrixStack.Entry entry2 = event.getStack().peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        boolean drew = false;
        Iterator<ModelParticle> iterator = this.particles.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<ModelParticle> iterator3 = iterator;
        while (iterator3.hasNext()) {
            ModelParticle particle = (ModelParticle) (iterator3.next());
            float alpha = particle.alpha(now, holdMs, fadeMs);
            if (alpha <= 0.003921569f) continue;
            Vec3d position = particle.position(now, holdMs, fadeMs, settings);
            float half = particle.size(now, holdMs, fadeMs, settings.size()) * 0.5f;
            int color = ColorEngine.multAlpha(colorProvider.applyAsInt(particle.getColorSeed()), alpha);
            float angle = particle.angle(now) * ((float)Math.PI / 180);
            float sin = (float)Math.sin(angle);
            float cos = (float)Math.cos(angle);
            float rx = (right.x() * cos - up.x() * sin) * half;
            float ry = (right.y() * cos - up.y() * sin) * half;
            float rz = (right.z() * cos - up.z() * sin) * half;
            float ux = (right.x() * sin + up.x() * cos) * half;
            float uy = (right.y() * sin + up.y() * cos) * half;
            float uz = (right.z() * sin + up.z() * cos) * half;
            float cx = (float)(position.x - cameraPos.x);
            float cy = (float)(position.y - cameraPos.y);
            float cz = (float)(position.z - cameraPos.z);
            consumer.vertex(pose, cx - rx - ux, cy - ry - uy, cz - rz - uz).texture(0.0f, 0.0f).color(color);
            consumer.vertex(pose, cx - rx + ux, cy - ry + uy, cz - rz + uz).texture(0.0f, 1.0f).color(color);
            consumer.vertex(pose, cx + rx + ux, cy + ry + uy, cz + rz + uz).texture(1.0f, 1.0f).color(color);
            consumer.vertex(pose, cx + rx - ux, cy + ry - uy, cz + rz - uz).texture(1.0f, 0.0f).color(color);
            drew = true;
        }
        if (drew) {
            provider.draw(renderType);
        }
    }

    private final void spawn(LivingEntity entity, float partialTick, long now, Settings settings) {
        KillEffectModelCapture.CapturedModel model = KillEffectModelCapture.capture(entity, partialTick);
        if (model.isEmpty()) {
            return;
        }
        float chaos = Math.max(0.0f, settings.chaos());
        int budget = 4000 - this.particles.size();
        if (budget <= 0) {
            return;
        }
        int count = Math.min(Math.max(1, settings.count()), budget);
        for (int i = 0; i < count; ++i) {
            Vec3d point = model.sample(this.random);
            Vec3d away = point.subtract(model.center());
            if (away.lengthSquared() < 1.0E-5) {
                away = new Vec3d(this.random.nextDouble() - 0.5, this.random.nextDouble() * 0.4, this.random.nextDouble() - 0.5);
            }
            Vec3d drift = away.normalize().multiply((0.32 + this.random.nextDouble() * 0.58) * (double)chaos).add((this.random.nextDouble() - 0.5) * 0.34 * (double)chaos, this.random.nextDouble() * 0.38 * (double)chaos, (this.random.nextDouble() - 0.5) * 0.34 * (double)chaos);
            this.particles.add(new ModelParticle(point, drift, now, this.random.nextInt(1440) * (this.random.nextBoolean() ? 1 : -1), this.random.nextFloat() * 360.0f, (this.random.nextFloat() - 0.5f) * 210.0f, this.random.nextFloat() * (float)Math.PI * 2.0f, 0.72f + this.random.nextFloat() * 0.65f));
        }
    }


    static {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"textures/particle/glow.png");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        GLOW_TEXTURE = identifier2;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\u0007R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDissolveEffect.Companion;", "", "<init>", "()V", "", "value", "smoothStep", "(F)F", "easeOutCubic", "Lnet/minecraft/Identifier;", "GLOW_TEXTURE", "Lnet/minecraft/Identifier;", "", "MAX_PARTICLES", "I", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float smoothStep(float value) {
            float t = MathHelper.clamp((float)value, (float)0.0f, (float)1.0f);
            return t * t * (3.0f - 2.0f * t);
        }

        private final float easeOutCubic(float value) {
            float inverse = 1.0f - MathHelper.clamp((float)value, (float)0.0f, (float)1.0f);
            return 1.0f - inverse * inverse * inverse;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0082\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\t\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0013\u0010\u0014J%\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0017\u0010\u0018J-\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0019\u00a2\u0006\u0004\b\u001b\u0010\u001cJ%\u0010\u001d\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005\u00a2\u0006\u0004\b\u001d\u0010\u0018J-\u0010\u001f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\t\u00a2\u0006\u0004\b\u001f\u0010 J\u0015\u0010!\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0005\u00a2\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b#\u0010$J\u0010\u0010%\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b%\u0010$J\u0010\u0010&\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b&\u0010'J\u0010\u0010(\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b(\u0010)J\u0010\u0010*\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b*\u0010+J\u0010\u0010,\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b,\u0010+J\u0010\u0010-\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b-\u0010+J\u0010\u0010.\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b.\u0010+J`\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\tH\u00c6\u0001\u00a2\u0006\u0004\b/\u00100J\u001b\u00102\u001a\u00020\u00122\b\u00101\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b2\u00103J\u0011\u00104\u001a\u00020\u0007H\u00d6\u0081\u0004\u00a2\u0006\u0004\b4\u0010)J\u0011\u00106\u001a\u000205H\u00d6\u0081\u0004\u00a2\u0006\u0004\b6\u00107R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u00108\u001a\u0004\b9\u0010$R\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u00108\u001a\u0004\b:\u0010$R\u0017\u0010\u0006\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010;\u001a\u0004\b<\u0010'R\u0017\u0010\b\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\b\u0010=\u001a\u0004\b>\u0010)R\u0017\u0010\n\u001a\u00020\t8\u0006\u00a2\u0006\f\n\u0004\b\n\u0010?\u001a\u0004\b@\u0010+R\u0017\u0010\u000b\u001a\u00020\t8\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010?\u001a\u0004\bA\u0010+R\u0017\u0010\f\u001a\u00020\t8\u0006\u00a2\u0006\f\n\u0004\b\f\u0010?\u001a\u0004\bB\u0010+R\u0017\u0010\r\u001a\u00020\t8\u0006\u00a2\u0006\f\n\u0004\b\r\u0010?\u001a\u0004\bC\u0010+\u00a8\u0006D"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDissolveEffect$ModelParticle;", "", "Lnet/minecraft/Vec3d;", "anchor", "drift", "", "bornMs", "", "colorSeed", "", "startAngle", "spin", "phase", "sizeMul", "<init>", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;JIFFFF)V", "nowMs", "maxLifeMs", "", "isDead", "(JJ)Z", "holdMs", "fadeMs", "evaporation", "(JJJ)F", "Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDissolveEffect$Settings;", "settings", "position", "(JJJLrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDissolveEffect$Settings;)Lnet/minecraft/Vec3d;", "alpha", "baseSize", "size", "(JJJF)F", "angle", "(J)F", "component1", "()Lnet/minecraft/Vec3d;", "component2", "component3", "()J", "component4", "()I", "component5", "()F", "component6", "component7", "component8", "copy", "(Lnet/minecraft/Vec3d;Lnet/minecraft/Vec3d;JIFFFF)Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDissolveEffect$ModelParticle;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/Vec3d;", "getAnchor", "getDrift", "J", "getBornMs", "I", "getColorSeed", "F", "getStartAngle", "getSpin", "getPhase", "getSizeMul", "rtx.kimiko:kimiko"})
    private static final class ModelParticle {
        @NotNull
        private final Vec3d anchor;
        @NotNull
        private final Vec3d drift;
        private final long bornMs;
        private final int colorSeed;
        private final float startAngle;
        private final float spin;
        private final float phase;
        private final float sizeMul;

        public ModelParticle(@NotNull Vec3d anchor, @NotNull Vec3d drift, long bornMs, int colorSeed, float startAngle, float spin, float phase, float sizeMul) {
            Intrinsics.checkNotNullParameter((Object)anchor, (String)"anchor");
            Intrinsics.checkNotNullParameter((Object)drift, (String)"drift");
            this.anchor = anchor;
            this.drift = drift;
            this.bornMs = bornMs;
            this.colorSeed = colorSeed;
            this.startAngle = startAngle;
            this.spin = spin;
            this.phase = phase;
            this.sizeMul = sizeMul;
        }

        @NotNull
        public final Vec3d getAnchor() {
            return this.anchor;
        }

        @NotNull
        public final Vec3d getDrift() {
            return this.drift;
        }

        public final long getBornMs() {
            return this.bornMs;
        }

        public final int getColorSeed() {
            return this.colorSeed;
        }

        public final float getStartAngle() {
            return this.startAngle;
        }

        public final float getSpin() {
            return this.spin;
        }

        public final float getPhase() {
            return this.phase;
        }

        public final float getSizeMul() {
            return this.sizeMul;
        }

        public final boolean isDead(long nowMs, long maxLifeMs) {
            return nowMs - this.bornMs >= maxLifeMs;
        }

        public final float evaporation(long nowMs, long holdMs, long fadeMs) {
            return MathHelper.clamp((float)((float)(nowMs - this.bornMs - holdMs) / (float)fadeMs), (float)0.0f, (float)1.0f);
        }

        @NotNull
        public final Vec3d position(long nowMs, long holdMs, long fadeMs, @NotNull Settings settings) {
            Intrinsics.checkNotNullParameter((Object)settings, (String)"settings");
            float evap = this.evaporation(nowMs, holdMs, fadeMs);
            if (evap <= 0.0f) {
                return this.anchor;
            }
            float chaos = Math.max(0.0f, settings.chaos());
            float eased = Companion.easeOutCubic(evap);
            double wave = Math.sin((double)(nowMs - this.bornMs) * 0.012 + (double)this.phase) * 0.075 * (double)chaos * (double)evap;
            double sideWave = Math.cos((double)(nowMs - this.bornMs) * 0.009 + (double)this.phase * 1.37) * 0.055 * (double)chaos * (double)evap;
            Vec3d vec3d2 = this.anchor.add(this.drift.multiply((double)eased)).add(wave, (double)(settings.rise() * eased * (0.75f + this.sizeMul * 0.35f)), sideWave);
            Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"add(...)");
            return vec3d2;
        }

        public final float alpha(long nowMs, long holdMs, long fadeMs) {
            float fadeIn = MathHelper.clamp((float)((float)(nowMs - this.bornMs) / 80.0f), (float)0.0f, (float)1.0f);
            float evap = this.evaporation(nowMs, holdMs, fadeMs);
            if (evap <= 0.0f) {
                return fadeIn;
            }
            return fadeIn * (1.0f - Companion.smoothStep(evap));
        }

        public final float size(long nowMs, long holdMs, long fadeMs, float baseSize) {
            float evap = this.evaporation(nowMs, holdMs, fadeMs);
            float pulse = 1.0f + (float)Math.sin((float)(nowMs - this.bornMs) * 0.018f + this.phase) * 0.08f;
            float dissolveScale = 1.0f - Companion.smoothStep(evap) * 0.45f;
            return baseSize * this.sizeMul * pulse * dissolveScale;
        }

        public final float angle(long nowMs) {
            return this.startAngle + (float)(nowMs - this.bornMs) / 1000.0f * this.spin;
        }

        @NotNull
        public final Vec3d component1() {
            return this.anchor;
        }

        @NotNull
        public final Vec3d component2() {
            return this.drift;
        }

        public final long component3() {
            return this.bornMs;
        }

        public final int component4() {
            return this.colorSeed;
        }

        public final float component5() {
            return this.startAngle;
        }

        public final float component6() {
            return this.spin;
        }

        public final float component7() {
            return this.phase;
        }

        public final float component8() {
            return this.sizeMul;
        }

        @NotNull
        public final ModelParticle copy(@NotNull Vec3d anchor, @NotNull Vec3d drift, long bornMs, int colorSeed, float startAngle, float spin, float phase, float sizeMul) {
            Intrinsics.checkNotNullParameter((Object)anchor, (String)"anchor");
            Intrinsics.checkNotNullParameter((Object)drift, (String)"drift");
            return new ModelParticle(anchor, drift, bornMs, colorSeed, startAngle, spin, phase, sizeMul);
        }

        public static /* synthetic */ ModelParticle copy$default(ModelParticle modelParticle, Vec3d vec3d2, Vec3d vec3d3, long l, int n, float f, float f2, float f3, float f4, int n2, Object object) {
            if ((n2 & 1) != 0) {
                vec3d2 = modelParticle.anchor;
            }
            if ((n2 & 2) != 0) {
                vec3d3 = modelParticle.drift;
            }
            if ((n2 & 4) != 0) {
                l = modelParticle.bornMs;
            }
            if ((n2 & 8) != 0) {
                n = modelParticle.colorSeed;
            }
            if ((n2 & 0x10) != 0) {
                f = modelParticle.startAngle;
            }
            if ((n2 & 0x20) != 0) {
                f2 = modelParticle.spin;
            }
            if ((n2 & 0x40) != 0) {
                f3 = modelParticle.phase;
            }
            if ((n2 & 0x80) != 0) {
                f4 = modelParticle.sizeMul;
            }
            return modelParticle.copy(vec3d2, vec3d3, l, n, f, f2, f3, f4);
        }

        @NotNull
        public String toString() {
            return "ModelParticle(anchor=" + this.anchor + ", drift=" + this.drift + ", bornMs=" + this.bornMs + ", colorSeed=" + this.colorSeed + ", startAngle=" + this.startAngle + ", spin=" + this.spin + ", phase=" + this.phase + ", sizeMul=" + this.sizeMul + ")";
        }

        public int hashCode() {
            int result = this.anchor.hashCode();
            result = result * 31 + this.drift.hashCode();
            result = result * 31 + Long.hashCode(this.bornMs);
            result = result * 31 + Integer.hashCode(this.colorSeed);
            result = result * 31 + Float.hashCode(this.startAngle);
            result = result * 31 + Float.hashCode(this.spin);
            result = result * 31 + Float.hashCode(this.phase);
            result = result * 31 + Float.hashCode(this.sizeMul);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ModelParticle)) {
                return false;
            }
            ModelParticle modelParticle = (ModelParticle)other;
            if (!Intrinsics.areEqual((Object)this.anchor, (Object)modelParticle.anchor)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.drift, (Object)modelParticle.drift)) {
                return false;
            }
            if (this.bornMs != modelParticle.bornMs) {
                return false;
            }
            if (this.colorSeed != modelParticle.colorSeed) {
                return false;
            }
            if (Float.compare(this.startAngle, modelParticle.startAngle) != 0) {
                return false;
            }
            if (Float.compare(this.spin, modelParticle.spin) != 0) {
                return false;
            }
            if (Float.compare(this.phase, modelParticle.phase) != 0) {
                return false;
            }
            return Float.compare(this.sizeMul, modelParticle.sizeMul) == 0;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0012J\u0010\u0010\u0017\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0012J\u0010\u0010\u0018\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b\u0018\u0010\u0019JV\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u000bH\u00c6\u0001\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001b\u0010\u001d\u001a\u00020\u000b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0011\u0010\u001f\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001f\u0010\u0010J\u0011\u0010!\u001a\u00020 H\u00d6\u0081\u0004\u00a2\u0006\u0004\b!\u0010\"R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b#\u0012\b\b$\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010%\u001a\u0004\b\u0003\u0010\u0010R%\u0010\u0005\u001a\u00020\u00048\u0007z\f\b#\u0012\b\b$\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010&\u001a\u0004\b\u0005\u0010\u0012R%\u0010\u0007\u001a\u00020\u00068\u0007z\f\b#\u0012\b\b$\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010'\u001a\u0004\b\u0007\u0010\u0014R%\u0010\b\u001a\u00020\u00068\u0007z\f\b#\u0012\b\b$\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010'\u001a\u0004\b\b\u0010\u0014R%\u0010\t\u001a\u00020\u00048\u0007z\f\b#\u0012\b\b$\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010&\u001a\u0004\b\t\u0010\u0012R%\u0010\n\u001a\u00020\u00048\u0007z\f\b#\u0012\b\b$\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010&\u001a\u0004\b\n\u0010\u0012R%\u0010\f\u001a\u00020\u000b8\u0007z\f\b#\u0012\b\b$\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010(\u001a\u0004\b\f\u0010\u0019\u00a8\u0006)"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDissolveEffect$Settings;", "", "", "count", "", "size", "", "holdMs", "fadeMs", "rise", "chaos", "", "throughWalls", "<init>", "(IFJJFFZ)V", "component1", "()I", "component2", "()F", "component3", "()J", "component4", "component5", "component6", "component7", "()Z", "copy", "(IFJJFFZ)Lrtx/kimiko/api/modules/impl/Visuals/killeffect/KillEffectDissolveEffect$Settings;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "I", "F", "J", "Z", "rtx.kimiko:kimiko"})
    public static final class Settings {
        private final int count;
        private final float size;
        private final long holdMs;
        private final long fadeMs;
        private final float rise;
        private final float chaos;
        private final boolean throughWalls;

        public Settings(int count, float size, long holdMs, long fadeMs, float rise, float chaos, boolean throughWalls) {
            this.count = count;
            this.size = size;
            this.holdMs = holdMs;
            this.fadeMs = fadeMs;
            this.rise = rise;
            this.chaos = chaos;
            this.throughWalls = throughWalls;
        }

        @JvmName(name="count")
        public final int count() {
            return this.count;
        }

        @JvmName(name="size")
        public final float size() {
            return this.size;
        }

        @JvmName(name="holdMs")
        public final long holdMs() {
            return this.holdMs;
        }

        @JvmName(name="fadeMs")
        public final long fadeMs() {
            return this.fadeMs;
        }

        @JvmName(name="rise")
        public final float rise() {
            return this.rise;
        }

        @JvmName(name="chaos")
        public final float chaos() {
            return this.chaos;
        }

        @JvmName(name="throughWalls")
        public final boolean throughWalls() {
            return this.throughWalls;
        }

        public final int component1() {
            return this.count;
        }

        public final float component2() {
            return this.size;
        }

        public final long component3() {
            return this.holdMs;
        }

        public final long component4() {
            return this.fadeMs;
        }

        public final float component5() {
            return this.rise;
        }

        public final float component6() {
            return this.chaos;
        }

        public final boolean component7() {
            return this.throughWalls;
        }

        @NotNull
        public final Settings copy(int count, float size, long holdMs, long fadeMs, float rise, float chaos, boolean throughWalls) {
            return new Settings(count, size, holdMs, fadeMs, rise, chaos, throughWalls);
        }

        public static /* synthetic */ Settings copy$default(Settings settings, int n, float f, long l, long l2, float f2, float f3, boolean bl, int n2, Object object) {
            if ((n2 & 1) != 0) {
                n = settings.count;
            }
            if ((n2 & 2) != 0) {
                f = settings.size;
            }
            if ((n2 & 4) != 0) {
                l = settings.holdMs;
            }
            if ((n2 & 8) != 0) {
                l2 = settings.fadeMs;
            }
            if ((n2 & 0x10) != 0) {
                f2 = settings.rise;
            }
            if ((n2 & 0x20) != 0) {
                f3 = settings.chaos;
            }
            if ((n2 & 0x40) != 0) {
                bl = settings.throughWalls;
            }
            return settings.copy(n, f, l, l2, f2, f3, bl);
        }

        @NotNull
        public String toString() {
            return "Settings(count=" + this.count + ", size=" + this.size + ", holdMs=" + this.holdMs + ", fadeMs=" + this.fadeMs + ", rise=" + this.rise + ", chaos=" + this.chaos + ", throughWalls=" + this.throughWalls + ")";
        }

        public int hashCode() {
            int result = Integer.hashCode(this.count);
            result = result * 31 + Float.hashCode(this.size);
            result = result * 31 + Long.hashCode(this.holdMs);
            result = result * 31 + Long.hashCode(this.fadeMs);
            result = result * 31 + Float.hashCode(this.rise);
            result = result * 31 + Float.hashCode(this.chaos);
            result = result * 31 + Boolean.hashCode(this.throughWalls);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Settings)) {
                return false;
            }
            Settings settings = (Settings)other;
            if (this.count != settings.count) {
                return false;
            }
            if (Float.compare(this.size, settings.size) != 0) {
                return false;
            }
            if (this.holdMs != settings.holdMs) {
                return false;
            }
            if (this.fadeMs != settings.fadeMs) {
                return false;
            }
            if (Float.compare(this.rise, settings.rise) != 0) {
                return false;
            }
            if (Float.compare(this.chaos, settings.chaos) != 0) {
                return false;
            }
            return this.throughWalls == settings.throughWalls;
        }
    }
}

