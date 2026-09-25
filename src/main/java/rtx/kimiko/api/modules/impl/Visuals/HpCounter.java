/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Vector4f
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

@Feature(value={"hpcounter"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 32\u00020\u0001:\u0003453B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0014\u00a2\u0006\u0004\b\b\u0010\u0003J\u001b\u0010\f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0003b\u0002\b\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000eH\u0003b\u0002\b\u000b\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0012\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0011H\u0003b\u0002\b\u000b\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018JA\u0010#\u001a\u0004\u0018\u00010\"2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b#\u0010$R \u0010'\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00040%8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u001a\u0010+\u001a\b\u0012\u0004\u0012\u00020*0)8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b+\u0010,R\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00020-0)8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b.\u0010,R\u0014\u0010/\u001a\u00020\"8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0016\u00101\u001a\u00020&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b1\u00102\u00ca\u0001\u0010\b6\u0012\f\b7\u0012\b\b\fJ\u0004\b\b(8\u00a8\u00069"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/HpCounter;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "fadeOutSeconds", "()F", "", "onDisable", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "Lrtx/kimiko/api/events/impl/render/HudRenderEvent;", "onHud", "(Lrtx/kimiko/api/events/impl/render/HudRenderEvent;)V", "Lnet/minecraft/PlayerEntity;", "player", "", "isVisible", "(Lnet/minecraft/PlayerEntity;)Z", "Lorg/joml/Matrix4f;", "positionMatrix", "projectionMatrix", "Lnet/minecraft/Vec3d;", "cameraPos", "", "x", "y", "z", "Lorg/joml/Vector4f;", "project", "(Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/Vec3d;DDD)Lorg/joml/Vector4f;", "", "", "healthByEntity", "Ljava/util/Map;", "", "Lrtx/kimiko/api/modules/impl/Visuals/HpCounter$FloatingNumber;", "numbers", "Ljava/util/List;", "Lrtx/kimiko/api/modules/impl/Visuals/HpCounter$ScreenNumber;", "screenNumbers", "projectionScratch", "Lorg/joml/Vector4f;", "nextOffset", "I", "Companion", "FloatingNumber", "ScreenNumber", "Lrtx/kimiko/api/liteapi/Feature;", "value", "hpcounter", "rtx.kimiko:kimiko"})
public final class HpCounter
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Map<Integer, Float> healthByEntity = new HashMap();
    @NotNull
    private final List<FloatingNumber> numbers = new ArrayList();
    @NotNull
    private final List<ScreenNumber> screenNumbers = new ArrayList();
    @NotNull
    private final Vector4f projectionScratch = new Vector4f();
    private int nextOffset;
    private static final long LIFETIME_MS = 900L;
    private static final int DAMAGE_COLOR = -46531;
    private static final int HEAL_COLOR = -12659877;
    @NotNull
    private static final Vec3d[] OFFSETS;

    public HpCounter() {
        super("HP Counter", "Показывает урон и лечение видимых игроков.", Category.VISUALS);
    }

    @Override
    public float fadeOutSeconds() {
        return 0.5f;
    }

    @Override
    protected void onDisable() {
        this.healthByEntity.clear();
        this.numbers.clear();
        this.screenNumbers.clear();
        this.nextOffset = 0;
    }

    @EventHandler
    private final void onTick(TickEvent event) {
        if (!event.isPre()) {
            return;
        }
        ClientPlayerEntity player = this.mc.player;
        ClientWorld level = this.mc.world;
        if (player == null || level == null) {
            this.healthByEntity.clear();
            this.numbers.clear();
            return;
        }
        long now = System.currentTimeMillis();
        for (AbstractClientPlayerEntity other : level.getPlayers()) {
            if (Intrinsics.areEqual((Object)other, (Object)player)) continue;
            float health = other.getHealth();
            Float previous = this.healthByEntity.put(other.getId(), Float.valueOf(health));
            if (previous == null || Math.abs(health - previous.floatValue()) < 0.01f || !this.isVisible((PlayerEntity)other)) continue;
            float change = health - previous.floatValue();
            int n = this.nextOffset++;
            Vec3d offset = OFFSETS[n % OFFSETS.length];
            Vec3d position = other.getEntityPos().add(offset.x, (double)other.getHeight() * 0.55 + offset.y, offset.z);
            this.numbers.add(new FloatingNumber(position, Math.abs(change), change > 0.0f, now));
        }
        this.healthByEntity.keySet().removeIf(id -> level.getEntityById(id.intValue()) == null);
        this.numbers.removeIf(number -> now - number.getCreatedAtMs() >= 900L);
    }

    @EventHandler
    private final void onWorldRender(WorldRenderEvent event) {
        if (event.isPortalPass()) {
            return;
        }
        this.screenNumbers.clear();
        ClientPlayerEntity player = this.mc.player;
        ClientWorld level = this.mc.world;
        if (player == null || level == null || this.numbers.isEmpty()) {
            return;
        }
        long now = System.currentTimeMillis();
        Vec3d vec3d2 = event.getCamera() == null ? this.mc.gameRenderer.getCamera().getCameraPos() : event.getCamera().getCameraPos();
        Intrinsics.checkNotNull((Object)vec3d2);
        Vec3d cameraPos = vec3d2;
        Matrix4f positionMatrix = event.getPositionMatrix();
        Matrix4f projectionMatrix = event.getProjectionMatrix();
        float screenWidth = Position.Companion.screenWidth();
        float screenHeight = Position.Companion.screenHeight();
        for (FloatingNumber number : this.numbers) {
            float progress = MathHelper.clamp((float)((float)(now - number.getCreatedAtMs()) / 900.0f), (float)0.0f, (float)1.0f);
            Vec3d pos = number.getPosition().add(0.0, (double)progress * 0.65, 0.0);
            Vector4f projected = this.project(positionMatrix, projectionMatrix, cameraPos, pos.x, pos.y, pos.z);
            if (projected == null) continue;
            float x = (projected.x / projected.w * 0.5f + 0.5f) * screenWidth;
            float screenY = (1.0f - (projected.y / projected.w * 0.5f + 0.5f)) * screenHeight;
            if (!Float.isFinite(x) || !Float.isFinite(screenY)) continue;
            float distance = (float)Math.sqrt(cameraPos.squaredDistanceTo(number.getPosition()));
            this.screenNumbers.add(new ScreenNumber(number, x, screenY, progress, MathHelper.clamp((float)(7.0f / distance), (float)0.38f, (float)1.05f)));
        }
    }

    @EventHandler
    private final void onHud(HudRenderEvent event) {
        if (this.screenNumbers.isEmpty()) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        for (ScreenNumber screenNumber : this.screenNumbers) {
            FloatingNumber number = screenNumber.getNumber();
            float alpha = this.visualAlpha() * HpCounter.Companion.smooth(MathHelper.clamp((float)(screenNumber.getProgress() / 0.14f), (float)0.0f, (float)1.0f)) * HpCounter.Companion.smooth(MathHelper.clamp((float)((1.0f - screenNumber.getProgress()) / 0.35f), (float)0.0f, (float)1.0f));
            float size = 10.0f * screenNumber.getDistanceScale() * (0.88f + 0.12f * alpha);
            String text = (number.getHeal() ? "+" : "-") + HpCounter.Companion.format(number.getAmount());
            float width = Fonts.SEMIBOLD.msdfWidth(text, size);
            float x = screenNumber.getX() - width * 0.5f;
            int color = ColorEngine.multAlpha(number.getHeal() ? -12659877 : -46531, alpha);
            Fonts.SEMIBOLD.msdf(text, x + 0.7f, screenNumber.getY() + 0.7f, size, ColorEngine.multAlpha(-16777216, alpha * 0.55f));
            Fonts.SEMIBOLD.msdf(text, x, screenNumber.getY(), size, color);
        }
        Render2D.flush();
    }

    private final boolean isVisible(PlayerEntity player) {
        ClientPlayerEntity localPlayer = this.mc.player;
        return player.isAlive() && !player.isInvisible() && localPlayer != null && localPlayer.canSee((Entity)player);
    }

    private final Vector4f project(Matrix4f positionMatrix, Matrix4f projectionMatrix, Vec3d cameraPos, double x, double y, double z) {
        Vector4f vector = this.projectionScratch.set((float)(x - cameraPos.x), (float)(y - cameraPos.y), (float)(z - cameraPos.z), 1.0f);
        positionMatrix.transform(vector);
        projectionMatrix.transform(vector);
        return vector.w > 1.0E-4f ? vector : null;
    }

    static {
        Vec3d[] class_243Array = new Vec3d[]{new Vec3d(-0.36, -0.08, 0.12), new Vec3d(-0.12, 0.1, -0.3), new Vec3d(0.22, -0.14, -0.22), new Vec3d(0.4, 0.04, 0.09), new Vec3d(0.11, 0.16, 0.33), new Vec3d(-0.28, -0.18, 0.27), new Vec3d(0.34, -0.03, -0.12), new Vec3d(-0.05, 0.22, 0.05)};
        OFFSETS = class_243Array;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0010R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00128\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015\u00a8\u0006\u0016"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/HpCounter.Companion;", "", "<init>", "()V", "", "value", "smooth", "(F)F", "", "format", "(F)Ljava/lang/String;", "", "LIFETIME_MS", "J", "", "DAMAGE_COLOR", "I", "HEAL_COLOR", "", "Lnet/minecraft/Vec3d;", "OFFSETS", "[Lnet/minecraft/Vec3d;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float smooth(float value) {
            return value * value * (3.0f - 2.0f * value);
        }

        private final String format(float value) {
            if (Math.abs(value - (float)Math.round(value)) < 0.05f) {
                String string = Integer.toString(Math.round(value));
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
                return string;
            }
            Locale locale = Locale.ROOT;
            String string = "%.1f";
            Object[] objectArray = new Object[]{Float.valueOf(value)};
            String string2 = String.format(locale, string, Arrays.copyOf(objectArray, objectArray.length));
            return string2.replace('.', ',');
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013J8\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\bH\u00c6\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0017\u001a\u00020\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0011\u0010\u001a\u001a\u00020\u0019H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0011\u0010\u001d\u001a\u00020\u001cH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001f\u001a\u0004\b \u0010\rR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010!\u001a\u0004\b\"\u0010\u000fR\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010#\u001a\u0004\b$\u0010\u0011R\u0017\u0010\t\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u0010%\u001a\u0004\b&\u0010\u0013\u00a8\u0006'"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/HpCounter$FloatingNumber;", "", "Lnet/minecraft/Vec3d;", "position", "", "amount", "", "heal", "", "createdAtMs", "<init>", "(Lnet/minecraft/Vec3d;FZJ)V", "component1", "()Lnet/minecraft/Vec3d;", "component2", "()F", "component3", "()Z", "component4", "()J", "copy", "(Lnet/minecraft/Vec3d;FZJ)Lrtx/kimiko/api/modules/impl/Visuals/HpCounter$FloatingNumber;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/Vec3d;", "getPosition", "F", "getAmount", "Z", "getHeal", "J", "getCreatedAtMs", "rtx.kimiko:kimiko"})
    private static final class FloatingNumber {
        @NotNull
        private final Vec3d position;
        private final float amount;
        private final boolean heal;
        private final long createdAtMs;

        public FloatingNumber(@NotNull Vec3d position, float amount, boolean heal, long createdAtMs) {
            Intrinsics.checkNotNullParameter((Object)position, (String)"position");
            this.position = position;
            this.amount = amount;
            this.heal = heal;
            this.createdAtMs = createdAtMs;
        }

        @NotNull
        public final Vec3d getPosition() {
            return this.position;
        }

        public final float getAmount() {
            return this.amount;
        }

        public final boolean getHeal() {
            return this.heal;
        }

        public final long getCreatedAtMs() {
            return this.createdAtMs;
        }

        @NotNull
        public final Vec3d component1() {
            return this.position;
        }

        public final float component2() {
            return this.amount;
        }

        public final boolean component3() {
            return this.heal;
        }

        public final long component4() {
            return this.createdAtMs;
        }

        @NotNull
        public final FloatingNumber copy(@NotNull Vec3d position, float amount, boolean heal, long createdAtMs) {
            Intrinsics.checkNotNullParameter((Object)position, (String)"position");
            return new FloatingNumber(position, amount, heal, createdAtMs);
        }

        public static /* synthetic */ FloatingNumber copy$default(FloatingNumber floatingNumber, Vec3d vec3d2, float f, boolean bl, long l, int n, Object object) {
            if ((n & 1) != 0) {
                vec3d2 = floatingNumber.position;
            }
            if ((n & 2) != 0) {
                f = floatingNumber.amount;
            }
            if ((n & 4) != 0) {
                bl = floatingNumber.heal;
            }
            if ((n & 8) != 0) {
                l = floatingNumber.createdAtMs;
            }
            return floatingNumber.copy(vec3d2, f, bl, l);
        }

        @NotNull
        public String toString() {
            return "FloatingNumber(position=" + this.position + ", amount=" + this.amount + ", heal=" + this.heal + ", createdAtMs=" + this.createdAtMs + ")";
        }

        public int hashCode() {
            int result = this.position.hashCode();
            result = result * 31 + Float.hashCode(this.amount);
            result = result * 31 + Boolean.hashCode(this.heal);
            result = result * 31 + Long.hashCode(this.createdAtMs);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FloatingNumber)) {
                return false;
            }
            FloatingNumber floatingNumber = (FloatingNumber)other;
            if (!Intrinsics.areEqual((Object)this.position, (Object)floatingNumber.position)) {
                return false;
            }
            if (Float.compare(this.amount, floatingNumber.amount) != 0) {
                return false;
            }
            if (this.heal != floatingNumber.heal) {
                return false;
            }
            return this.createdAtMs == floatingNumber.createdAtMs;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0082\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u000eJ\u0010\u0010\u0011\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u000eJB\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0019\u001a\u00020\u0018H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0011\u0010\u001c\u001a\u00020\u001bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010\fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010 \u001a\u0004\b!\u0010\u000eR\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010 \u001a\u0004\b\"\u0010\u000eR\u0017\u0010\u0007\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010 \u001a\u0004\b#\u0010\u000eR\u0017\u0010\b\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\b\u0010 \u001a\u0004\b$\u0010\u000e\u00a8\u0006%"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/HpCounter$ScreenNumber;", "", "Lrtx/kimiko/api/modules/impl/Visuals/HpCounter$FloatingNumber;", "number", "", "x", "y", "progress", "distanceScale", "<init>", "(Lrtx/kimiko/api/modules/impl/Visuals/HpCounter$FloatingNumber;FFFF)V", "component1", "()Lrtx/kimiko/api/modules/impl/Visuals/HpCounter$FloatingNumber;", "component2", "()F", "component3", "component4", "component5", "copy", "(Lrtx/kimiko/api/modules/impl/Visuals/HpCounter$FloatingNumber;FFFF)Lrtx/kimiko/api/modules/impl/Visuals/HpCounter$ScreenNumber;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lrtx/kimiko/api/modules/impl/Visuals/HpCounter$FloatingNumber;", "getNumber", "F", "getX", "getY", "getProgress", "getDistanceScale", "rtx.kimiko:kimiko"})
    private static final class ScreenNumber {
        @NotNull
        private final FloatingNumber number;
        private final float x;
        private final float y;
        private final float progress;
        private final float distanceScale;

        public ScreenNumber(@NotNull FloatingNumber number, float x, float y, float progress, float distanceScale) {
            Intrinsics.checkNotNullParameter((Object)number, (String)"number");
            this.number = number;
            this.x = x;
            this.y = y;
            this.progress = progress;
            this.distanceScale = distanceScale;
        }

        @NotNull
        public final FloatingNumber getNumber() {
            return this.number;
        }

        public final float getX() {
            return this.x;
        }

        public final float getY() {
            return this.y;
        }

        public final float getProgress() {
            return this.progress;
        }

        public final float getDistanceScale() {
            return this.distanceScale;
        }

        @NotNull
        public final FloatingNumber component1() {
            return this.number;
        }

        public final float component2() {
            return this.x;
        }

        public final float component3() {
            return this.y;
        }

        public final float component4() {
            return this.progress;
        }

        public final float component5() {
            return this.distanceScale;
        }

        @NotNull
        public final ScreenNumber copy(@NotNull FloatingNumber number, float x, float y, float progress, float distanceScale) {
            Intrinsics.checkNotNullParameter((Object)number, (String)"number");
            return new ScreenNumber(number, x, y, progress, distanceScale);
        }

        public static /* synthetic */ ScreenNumber copy$default(ScreenNumber screenNumber, FloatingNumber floatingNumber, float f, float f2, float f3, float f4, int n, Object object) {
            if ((n & 1) != 0) {
                floatingNumber = screenNumber.number;
            }
            if ((n & 2) != 0) {
                f = screenNumber.x;
            }
            if ((n & 4) != 0) {
                f2 = screenNumber.y;
            }
            if ((n & 8) != 0) {
                f3 = screenNumber.progress;
            }
            if ((n & 0x10) != 0) {
                f4 = screenNumber.distanceScale;
            }
            return screenNumber.copy(floatingNumber, f, f2, f3, f4);
        }

        @NotNull
        public String toString() {
            return "ScreenNumber(number=" + this.number + ", x=" + this.x + ", y=" + this.y + ", progress=" + this.progress + ", distanceScale=" + this.distanceScale + ")";
        }

        public int hashCode() {
            int result = this.number.hashCode();
            result = result * 31 + Float.hashCode(this.x);
            result = result * 31 + Float.hashCode(this.y);
            result = result * 31 + Float.hashCode(this.progress);
            result = result * 31 + Float.hashCode(this.distanceScale);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ScreenNumber)) {
                return false;
            }
            ScreenNumber screenNumber = (ScreenNumber)other;
            if (!Intrinsics.areEqual((Object)this.number, (Object)screenNumber.number)) {
                return false;
            }
            if (Float.compare(this.x, screenNumber.x) != 0) {
                return false;
            }
            if (Float.compare(this.y, screenNumber.y) != 0) {
                return false;
            }
            if (Float.compare(this.progress, screenNumber.progress) != 0) {
                return false;
            }
            return Float.compare(this.distanceScale, screenNumber.distanceScale) == 0;
        }
    }
}

