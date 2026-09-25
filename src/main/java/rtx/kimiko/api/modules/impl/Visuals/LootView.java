/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ItemEntity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.client.world.ClientWorld
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Visuals.ItemPhysics;
import rtx.kimiko.api.modules.impl.Visuals.lootview.LootShape;
import rtx.kimiko.api.modules.impl.Visuals.lootview.LootViewShapes;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"lootview"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 -2\u00020\u0001:\u0001-B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u0004H\u0015b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\t\u0010\u0003J\u001b\u0010\r\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0003b\u0002\b\f\u00a2\u0006\u0004\b\r\u0010\u000eJw\u0010 \u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\u0019H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b \u0010!R\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\"8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010$R\u0014\u0010&\u001a\u00020\"8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010$R\u0014\u0010(\u001a\u00020'8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0014\u0010+\u001a\u00020*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b+\u0010,\u00ca\u0001\u0010\b.\u0012\f\b\u0006\u0012\b\b\fJ\u0004\b\b(/\u00a8\u00060"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/LootView;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "STD", "onDisable", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "Lnet/minecraft/MatrixStack$Entry;", "pose", "Lnet/minecraft/VertexConsumer;", "consumer", "Lnet/minecraft/ItemEntity;", "entity", "", "camX", "camY", "camZ", "", "partialTicks", "time", "", "pointCount", "fade", "distanceToCamera", "renderNeedles", "(Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/VertexConsumer;Lnet/minecraft/ItemEntity;DDDFFIFF)V", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "distance", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "needleHeight", "silhouetteScale", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "density", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "throughWalls", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "lootview", "rtx.kimiko:kimiko"})
public final class LootView
extends Module {
    @NotNull
    public static final Companion Companion;
    @NotNull
    private final SliderSetting distance = (SliderSetting)this.register((Setting)new SliderSetting("Дистанция", "Дальность отображения игл.").range(8, 64).increment(1).setValue(32.0f));
    @NotNull
    private final SliderSetting needleHeight = (SliderSetting)this.register((Setting)new SliderSetting("Высота игл", "Высота игл в блоках.").range(0.4f, 1.6f).increment(0.05f).setValue(1.0f));
    @NotNull
    private final SliderSetting silhouetteScale = (SliderSetting)this.register((Setting)new SliderSetting("Масштаб", "Размер силуэта на земле.").range(0.5f, 1.5f).increment(0.05f).setValue(1.0f));
    @NotNull
    private final ModeSetting density;
    @NotNull
    private final BooleanSetting throughWalls;
    private static final float TAU = (float)Math.PI * 2;
    private static final int HEAD_SIDES = 8;
    @NotNull
    private static final float[] HEAD_COS;
    @NotNull
    private static final float[] HEAD_SIN;
    private static final int SPIKE_SIDES = 8;
    @NotNull
    private static final float[] SPIKE_COS;
    @NotNull
    private static final float[] SPIKE_SIN;
    @NotNull
    private static final String DENSITY_LOW = "Низкая";
    @NotNull
    private static final String DENSITY_MEDIUM = "Средняя";
    @NotNull
    private static final String DENSITY_HIGH = "Высокая";
    @JvmField
    @Nullable
    public static LootView INSTANCE;

    public LootView() {
        super("Loot View", "Рисует мерцающие иглы по силуэту выброшенных предметов.", Category.VISUALS);
        String[] stringArray = new String[]{DENSITY_LOW, DENSITY_MEDIUM, DENSITY_HIGH};
        this.density = (ModeSetting)this.register((Setting)new ModeSetting("Плотность", "Количество игл на предмет.", DENSITY_MEDIUM, stringArray));
        this.throughWalls = (BooleanSetting)this.register((Setting)new BooleanSetting("Сквозь стены", "Показывать иглы за препятствиями.", false));
        INSTANCE = this;
    }

    @Override
    @Protect(value=Level.STD)
    protected void onDisable() {
        LootViewShapes.INSTANCE.clear();
    }

    @EventHandler
    private final void onWorldRender(WorldRenderEvent event) {
        Camera camera;
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        if (this.mc.player == null) {
            return;
        }
        Camera camera2 = camera = event.getCamera();
        Vec3d vec3d2 = camera2 != null ? camera2.getCameraPos() : this.mc.gameRenderer.getCamera().getCameraPos();
        Intrinsics.checkNotNull((Object)vec3d2);
        Vec3d cam = vec3d2;
        float partialTicks = event.getPartialTicks();
        double maxDist = this.distance.getFloat();
        double maxDistSqr = maxDist * maxDist;
        int pointCount = this.density.is(DENSITY_LOW) ? 7 : (this.density.is(DENSITY_HIGH) ? 15 : 11);
        RenderLayer renderType = this.throughWalls.getValue() ? ClientPipelines.LOOT_VIEW_NODEPTH : ClientPipelines.LOOT_VIEW;
        VertexConsumerProvider.Immediate immediate2 = this.mc.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        VertexConsumerProvider.Immediate provider = immediate2;
        MatrixStack.Entry entry2 = event.getStack().peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        float time = (float)(System.nanoTime() / 1000000L % 3600000L) / 1000.0f;
        VertexConsumer consumer = null;
        for (Entity entity : level.getEntities()) {
            double distSqr;
            if (!(entity instanceof ItemEntity) || entity.isRemoved()) continue;
            ItemEntity itemEntity = (ItemEntity)entity;
            ItemStack stack = itemEntity.getStack();
            if (stack == null || stack.isEmpty() || (distSqr = entity.squaredDistanceTo(cam.x, cam.y, cam.z)) > maxDistSqr) continue;
            if (consumer == null) {
                consumer = provider.getBuffer(renderType);
            }
            double actualDistance = Math.sqrt(distSqr);
            float fade = MathHelper.clamp((float)((float)(maxDist - actualDistance) / ((float)maxDist * 0.2f)), (float)0.0f, (float)1.0f);
            this.renderNeedles(pose, consumer, itemEntity, cam.x, cam.y, cam.z, partialTicks, time, pointCount, fade, (float)actualDistance);
        }
        if (consumer != null) {
            provider.draw(renderType);
        }
    }

    @Protect(value=Level.STD)
    private final void renderNeedles(MatrixStack.Entry pose, VertexConsumer consumer, ItemEntity entity, double camX, double camY, double camZ, float partialTicks, float time, int pointCount, float fade, float distanceToCamera) {
        ItemStack itemStack2 = entity.getStack();
        Intrinsics.checkNotNullExpressionValue((Object)itemStack2, (String)"getItem(...)");
        LootShape shape = LootViewShapes.INSTANCE.get(itemStack2, pointCount);
        Vec3d vec3d2 = entity.getLerpedPos(partialTicks);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"getPosition(...)");
        Vec3d pos = vec3d2;
        ItemPhysics itemPhysics = ItemPhysics.Companion.getInstance();
        boolean flat = itemPhysics != null && itemPhysics.isEnabled() && itemPhysics.isNormalMode() && entity.isOnGround();
        float baseX = (float)(pos.x - camX);
        float baseY = (float)(pos.y - camY) + (flat ? 0.05f : 0.02f);
        float baseZ = (float)(pos.z - camZ);
        float grow = MathHelper.clamp((float)(((float)entity.getItemAge() + partialTicks) / 12.0f), (float)0.0f, (float)1.0f);
        float growEase = 1.0f - (1.0f - grow) * (1.0f - grow) * (1.0f - grow);
        float height = this.needleHeight.getFloat() * 1.2f * growEase;
        float scale = this.silhouetteScale.getFloat() * (0.4f + 0.6f * growEase);
        if (flat && itemPhysics != null) {
            scale *= itemPhysics.groundItemScale();
        }
        float seedBase = (float)(entity.getId() & 0xFFFF) * 0.137f;
        int color = shape.color;
        float distanceFactor = MathHelper.clamp((float)(distanceToCamera / 16.0f), (float)0.0f, (float)4.0f);
        float adaptiveThickness = 0.01275f * (1.0f + distanceFactor * 0.045f);
        float minimumThickness = distanceToCamera * 0.0012f;
        float baseSpikeRadius = Math.max(adaptiveThickness, minimumThickness);
        int n = shape.count;
        for (int i = 0; i < n; ++i) {
            int k;
            int j;
            float period = 1.1f + 0.5f * LootView.Companion.hash((float)i * 7.31f + seedBase);
            float local = time / period + LootView.Companion.hash((float)i * 3.7f + seedBase) * 4.0f;
            float cycle = (float)Math.floor(local);
            float p = local - cycle;
            float env = p < 0.3f ? p / 0.3f : (p > 0.7f ? (1.0f - p) / 0.3f : 1.0f);
            env *= env * (3.0f - 2.0f * env);
            if ((env = LootView.Companion.smoothstep(0.0f, 1.0f, env)) <= 0.015f) continue;
            float spread = 0.175f;
            float jx = (LootView.Companion.hash((float)i * 12.9898f + cycle * 78.233f + seedBase) - 0.5f) * spread;
            float jz = (LootView.Companion.hash((float)i * 45.164f + cycle * 94.673f + seedBase) - 0.5f) * spread;
            float wx = baseX + shape.xs[i] * scale + jx;
            float wz = baseZ + shape.zs[i] * scale + jz;
            float hRand = 0.7f + 0.6f * LootView.Companion.hash((float)i * 27.17f + cycle * 17.929f + seedBase);
            float h = height * shape.jitter[i] * hRand * env;
            if (h <= 0.01f) continue;
            float alpha = 0.9f * env * fade;
            float headRadius = 0.041399997f * (0.4f + 0.6f * env);
            float spikeRadius = baseSpikeRadius * (0.5f + 0.5f * env);
            int headCenterColor = ColorEngine.multAlpha(color, Math.min(1.0f, alpha * 1.25f));
            int headEdgeColor = ColorEngine.multAlpha(color, alpha * 0.3f);
            int baseColor = ColorEngine.multAlpha(color, alpha);
            int apexColor = ColorEngine.multAlpha(ColorEngine.lerpColor(color, -1, 0.45f), alpha * 0.2f);
            float headY = baseY + 0.004f;
            float apexY = baseY + h;
            for (j = 0; j < 8; ++j) {
                k = (j + 1) % 8;
                float ex0 = wx + HEAD_COS[j] * headRadius;
                float ez0 = wz + HEAD_SIN[j] * headRadius;
                float ex1 = wx + HEAD_COS[k] * headRadius;
                float ez1 = wz + HEAD_SIN[k] * headRadius;
                consumer.vertex(pose, wx, headY, wz).color(headCenterColor);
                consumer.vertex(pose, ex0, headY, ez0).color(headEdgeColor);
                consumer.vertex(pose, ex1, headY, ez1).color(headEdgeColor);
                consumer.vertex(pose, ex1, headY, ez1).color(headEdgeColor);
            }
            for (j = 0; j < 8; ++j) {
                k = (j + 1) % 8;
                float bx0 = wx + SPIKE_COS[j] * spikeRadius;
                float bz0 = wz + SPIKE_SIN[j] * spikeRadius;
                float bx1 = wx + SPIKE_COS[k] * spikeRadius;
                float bz1 = wz + SPIKE_SIN[k] * spikeRadius;
                consumer.vertex(pose, bx0, baseY, bz0).color(baseColor);
                consumer.vertex(pose, bx1, baseY, bz1).color(baseColor);
                consumer.vertex(pose, wx, apexY, wz).color(apexColor);
                consumer.vertex(pose, wx, apexY, wz).color(apexColor);
            }
            float haloRadius = spikeRadius * 2.8f;
            int haloBaseColor = ColorEngine.multAlpha(color, alpha * 0.16f);
            int haloApexColor = ColorEngine.multAlpha(color, 0.0f);
            float haloApexY = baseY + h * 0.92f;
            for (int j2 = 0; j2 < 8; ++j2) {
                int k2 = (j2 + 1) % 8;
                float bx0 = wx + SPIKE_COS[j2] * haloRadius;
                float bz0 = wz + SPIKE_SIN[j2] * haloRadius;
                float bx1 = wx + SPIKE_COS[k2] * haloRadius;
                float bz1 = wz + SPIKE_SIN[k2] * haloRadius;
                consumer.vertex(pose, bx0, baseY, bz0).color(haloBaseColor);
                consumer.vertex(pose, bx1, baseY, bz1).color(haloBaseColor);
                consumer.vertex(pose, wx, haloApexY, wz).color(haloApexColor);
                consumer.vertex(pose, wx, haloApexY, wz).color(haloApexColor);
            }
        }
    }

    @JvmStatic
    @Nullable
    public static final LootView getInstance() {
        return Companion.getInstance();
    }

    static {
        int n;
        Companion = new Companion(null);
        int n2 = 0;
        float[] fArray = new float[8];
        while (n2 < 8) {
            n = n2++;
            fArray[n] = (float)Math.cos((float)Math.PI * 2 * (float)n / (float)8);
        }
        HEAD_COS = fArray;
        n2 = 0;
        fArray = new float[8];
        while (n2 < 8) {
            n = n2++;
            fArray[n] = (float)Math.sin((float)Math.PI * 2 * (float)n / (float)8);
        }
        HEAD_SIN = fArray;
        n2 = 0;
        fArray = new float[8];
        while (n2 < 8) {
            n = n2++;
            fArray[n] = (float)Math.cos((float)Math.PI * 2 * (float)n / (float)8);
        }
        SPIKE_COS = fArray;
        n2 = 0;
        fArray = new float[8];
        while (n2 < 8) {
            n = n2++;
            fArray[n] = (float)Math.sin((float)Math.PI * 2 * (float)n / (float)8);
        }
        SPIKE_SIN = fArray;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J'\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u000e\u001a\u0004\u0018\u00010\fH\u0007b\u0002\b\r\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0014R\u0014\u0010\u001a\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0017R\u0014\u0010\u001b\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0017R\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001eR\u0014\u0010 \u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010\u001eR\u001d\u0010\"\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b!\u00a2\u0006\u0006\n\u0004\b\"\u0010#\u00a8\u0006$"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/LootView.Companion;", "", "<init>", "()V", "", "value", "hash", "(F)F", "edge0", "edge1", "smoothstep", "(FFF)F", "Lrtx/kimiko/api/modules/impl/Visuals/LootView;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/LootView;", "TAU", "F", "", "HEAD_SIDES", "I", "", "HEAD_COS", "[F", "HEAD_SIN", "SPIKE_SIDES", "SPIKE_COS", "SPIKE_SIN", "", "DENSITY_LOW", "Ljava/lang/String;", "DENSITY_MEDIUM", "DENSITY_HIGH", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/LootView;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float hash(float value) {
            float s = (float)Math.sin(value) * 43758.547f;
            return s - (float)Math.floor(s);
        }

        private final float smoothstep(float edge0, float edge1, float value) {
            float t = MathHelper.clamp((float)((value - edge0) / (edge1 - edge0)), (float)0.0f, (float)1.0f);
            return t * t * (3.0f - 2.0f * t);
        }

        @JvmStatic
        @Nullable
        public final LootView getInstance() {
            LootView module = ModuleManager.Companion.get().get(LootView.class);
            LootView lootView = module;
            if (lootView == null) {
                lootView = INSTANCE;
            }
            return lootView;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

