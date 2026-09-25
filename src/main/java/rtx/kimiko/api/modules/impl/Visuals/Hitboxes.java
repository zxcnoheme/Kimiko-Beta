/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.mob.MobEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.util.math.Box
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.client.render.Frustum
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.impl.Visuals.custompet.entity.CustomPetEntity;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.MultiSelectSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.modules.optimization.OcclusionCuller;
import rtx.kimiko.utils.render.util.world.WorldShapeRenderer;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"hitboxes"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 &2\u00020\u0001:\u0001&B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0003b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u000b\u0010\fJ)\u0010\u0014\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0003b\u000e\b\u0010\u0012\n\b\u0011\u0012\u0006\b\n0\u00128\u0013\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010!R\u001a\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\"8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b$\u0010%\u00ca\u0001\u0010\b'\u0012\f\b\u0011\u0012\b\b\fJ\u0004\b\b((\u00a8\u0006)"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/Hitboxes;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "event", "", "Lrtx/kimiko/api/events/EventHandler;", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "", "fadeOutSeconds", "()F", "Lnet/minecraft/Entity;", "entity", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "STD", "shouldRender", "(Lnet/minecraft/Entity;)Z", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "targets", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "mode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "color", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "fillOpacity", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "", "Lnet/minecraft/Box;", "boxes", "Ljava/util/List;", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "hitboxes", "rtx.kimiko:kimiko"})
public final class Hitboxes
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final MultiSelectSetting targets;
    @NotNull
    private final ModeSetting mode;
    @NotNull
    private final ColorSetting color;
    @NotNull
    private final SliderSetting fillOpacity;
    @NotNull
    private final List<Box> boxes;
    @NotNull
    private static final String TARGET_PLAYERS = "Игроки";
    @NotNull
    private static final String TARGET_MOBS = "Мобы";
    @NotNull
    private static final String TARGET_SELF = "Себя";
    @NotNull
    private static final String MODE_OUTLINE = "Контур";
    @NotNull
    private static final String MODE_FILL = "Заливка";
    @NotNull
    private static final String MODE_BOTH = "Оба";

    public Hitboxes() {
        super("Hitboxes", "Окрашивает хитбоксы сущностей.", Category.VISUALS);
        String[] stringArray = new String[]{TARGET_PLAYERS, TARGET_MOBS, TARGET_SELF};
        MultiSelectSetting multiSelectSetting = new MultiSelectSetting("Цели", "Чьи хитбоксы рисовать.").value(stringArray);
        stringArray = new String[]{TARGET_PLAYERS, TARGET_MOBS};
        this.targets = (MultiSelectSetting)this.register((Setting)multiSelectSetting.selected(stringArray));
        stringArray = new String[]{MODE_OUTLINE, MODE_FILL, MODE_BOTH};
        this.mode = (ModeSetting)this.register((Setting)new ModeSetting("Режим", "Контур, заливка или оба.", MODE_BOTH, stringArray));
        this.color = (ColorSetting)this.register((Setting)new ColorSetting("Цвет", "Цвет хитбокса.", new Color(70, 170, 255, 255)));
        this.fillOpacity = (SliderSetting)this.register((Setting)new SliderSetting("Прозрачность заливки", "Сила заливки.").range(0.0f, 1.0f).increment(0.05f).setValue(0.18f).visible(() -> Hitboxes.fillOpacity$lambda$0(this)));
        this.boxes = new ArrayList(64);
    }

    @EventHandler
    private final void onWorldRender(WorldRenderEvent event) {
        ClientPlayerEntity player = this.mc.player;
        ClientWorld level = this.mc.world;
        if (player == null || level == null) {
            return;
        }
        Vec3d vec3d2 = event.getCamera() != null ? event.getCamera().getCameraPos() : this.mc.gameRenderer.getCamera().getCameraPos();
        Intrinsics.checkNotNull((Object)vec3d2);
        Vec3d cameraPos = vec3d2;
        double maxDistanceSqr = 16384.0;
        Frustum frustum = new Frustum(event.getPositionMatrix(), event.getProjectionMatrix());
        frustum.setPosition(cameraPos.x, cameraPos.y, cameraPos.z);
        float partialTicks = event.getPartialTicks();
        this.boxes.clear();
        for (Entity entity : level.getEntities()) {
            double dz;
            double dy;
            if (!this.shouldRender(entity) || player.squaredDistanceTo(entity) > maxDistanceSqr) continue;
            Box box = entity.getBoundingBox();
            double dx = MathHelper.lerp((double)partialTicks, (double)entity.lastRenderX, (double)entity.getX()) - entity.getX();
            Box rendered = new Box(box.minX + dx - 0.002, box.minY + (dy = MathHelper.lerp((double)partialTicks, (double)entity.lastRenderY, (double)entity.getY()) - entity.getY()) - 0.002, box.minZ + (dz = MathHelper.lerp((double)partialTicks, (double)entity.lastRenderZ, (double)entity.getZ()) - entity.getZ()) - 0.002, box.maxX + dx + 0.002, box.maxY + dy + 0.002, box.maxZ + dz + 0.002);
            if (!frustum.isVisible(rendered) || !OcclusionCuller.isExposed(entity, cameraPos.x, cameraPos.y, cameraPos.z)) continue;
            this.boxes.add(rendered);
        }
        if (this.boxes.isEmpty()) {
            return;
        }
        float fade = this.visualAlpha();
        int outline = this.mode.is(MODE_FILL) ? 0 : ColorEngine.multAlpha(this.color.getColor(), fade);
        int fill = this.mode.is(MODE_OUTLINE) ? 0 : ColorEngine.multAlpha(this.color.getColor(), this.fillOpacity.getFloat() * fade);
        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate2 = this.mc.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        WorldShapeRenderer.boxes(immediate2, stack, cameraPos, this.boxes, fill, outline, 1.0f);
    }

    @Override
    public float fadeOutSeconds() {
        return 0.5f;
    }

    @Protect(value=Level.STD)
    private final boolean shouldRender(Entity entity) {
        ClientPlayerEntity player = this.mc.player;
        if (entity == null || entity instanceof CustomPetEntity || entity.isRemoved() || entity.isSpectator()) {
            return false;
        }
        if (Intrinsics.areEqual((Object)entity, (Object)player)) {
            return this.targets.is(TARGET_SELF) && !this.mc.options.getPerspective().isFirstPerson();
        }
        if (player != null && entity.isInvisible() && entity.isInvisibleTo((PlayerEntity)player)) {
            return false;
        }
        if (entity instanceof PlayerEntity) {
            return this.targets.is(TARGET_PLAYERS);
        }
        return entity instanceof MobEntity && this.targets.is(TARGET_MOBS);
    }

    private static final Boolean fillOpacity$lambda$0(Hitboxes this$0) {
        return !this$0.mode.is(MODE_OUTLINE);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\u0006\u00a8\u0006\f"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/Hitboxes.Companion;", "", "<init>", "()V", "", "TARGET_PLAYERS", "Ljava/lang/String;", "TARGET_MOBS", "TARGET_SELF", "MODE_OUTLINE", "MODE_FILL", "MODE_BOTH", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

