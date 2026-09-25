/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.world.BlockView
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Box
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.shape.VoxelShape
 *  net.minecraft.block.BlockState
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.client.network.ClientPlayerInteractionManager
 *  net.minecraft.client.world.ClientWorld
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.awt.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mixin.accessor.MultiPlayerGameModeAccessor;
import net.minecraft.world.BlockView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.utils.animations.Easings;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.render2d.ClientPalette;
import rtx.kimiko.utils.render.util.world.BlockOverlayRenderer;

@Feature(value={"blockoverlay"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 L2\u00020\u0001:\u0001LB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0003b\u0002\b\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000eH\u0003b\u0002\b\u000b\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0011\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u001f\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010 \u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b \u0010!J\u0017\u0010\"\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b$\u0010\u0003J\u000f\u0010%\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b%\u0010\u0003R\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010*\u001a\u00020)8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010-\u001a\u00020,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u0014\u00100\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0014\u00102\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u00101R\u0018\u00104\u001a\u0004\u0018\u0001038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0018\u00106\u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b6\u00107R\u0018\u00108\u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b8\u00107R\u0016\u00109\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b9\u0010:R\u0016\u0010;\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b;\u0010<R\u0016\u0010=\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b=\u0010<R\u0016\u0010>\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010<R\u0016\u0010@\u001a\u00020?8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b@\u0010AR\u0016\u0010B\u001a\u00020?8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bB\u0010AR\u0016\u0010C\u001a\u00020?8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bC\u0010AR\u0016\u0010D\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bD\u0010:R\u0016\u0010E\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bE\u0010<R\u0016\u0010F\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bF\u0010<R\u0016\u0010G\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bG\u0010<R\u0018\u0010H\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bH\u0010IR\u0018\u0010J\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bJ\u0010IR\u0018\u0010K\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010I\u00ca\u0001\u0010\bM\u0012\f\bN\u0012\b\b\fJ\u0004\b\b(O\u00a8\u0006P"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/BlockOverlay;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "onDisable", "", "fadeOutSeconds", "()F", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "progress", "Lnet/minecraft/Box;", "getAnimatedBox", "(F)Lnet/minecraft/Box;", "alpha", "", "resolveColors", "(F)[I", "", "index", "getColor", "(IF)I", "", "now", "Lnet/minecraft/Vec3d;", "animatedPosition", "(J)Lnet/minecraft/Vec3d;", "activeAlpha", "(F)F", "startFade", "reset", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "colorSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "colorMode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "useSecondColor", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customSecondColor", "Lnet/minecraft/BlockPos;", "targetPos", "Lnet/minecraft/BlockPos;", "movementFrom", "Lnet/minecraft/Vec3d;", "movementTo", "movementStartedAt", "J", "damage", "F", "previousDamage", "previousRawProgress", "", "destroying", "Z", "fading", "skipFadeIn", "fadeStartedAt", "fadeStartAlpha", "lastRenderedAlpha", "lastRenderedProgress", "lastBox", "Lnet/minecraft/Box;", "fadeFromBox", "fadeToBox", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "blockoverlay", "rtx.kimiko:kimiko"})
public final class BlockOverlay
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting colorSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Цвет"));
    @NotNull
    private final ModeSetting colorMode;
    @NotNull
    private final BooleanSetting useSecondColor;
    @NotNull
    private final ColorSetting customColor;
    @NotNull
    private final ColorSetting customSecondColor;
    @Nullable
    private BlockPos targetPos;
    @Nullable
    private Vec3d movementFrom;
    @Nullable
    private Vec3d movementTo;
    private long movementStartedAt;
    private float damage;
    private float previousDamage;
    private float previousRawProgress;
    private boolean destroying;
    private boolean fading;
    private boolean skipFadeIn;
    private long fadeStartedAt;
    private float fadeStartAlpha;
    private float lastRenderedAlpha;
    private float lastRenderedProgress;
    @Nullable
    private Box lastBox;
    @Nullable
    private Box fadeFromBox;
    @Nullable
    private Box fadeToBox;
    private static final long FADE_DURATION_MS = 220L;
    private static final long MOVE_DURATION_MS = 160L;
    private static final int DARK_SECOND_COLOR = new Color(16, 16, 16, 75).getRGB();
    @NotNull
    private static final String COLOR_RAINBOW = "Радуга";
    @NotNull
    private static final String COLOR_CLIENT = "Клиент";
    @NotNull
    private static final String COLOR_CUSTOM = "Свой";

    public BlockOverlay() {
        super("Block Overlay", "Рисует кастомную анимацию разрушения блока.", Category.VISUALS);
        String[] stringArray = new String[]{COLOR_RAINBOW, COLOR_CLIENT, COLOR_CUSTOM};
        this.colorMode = (ModeSetting)this.register((Setting)new ModeSetting("Режим цвета", "Цвет анимации разрушения блока.", COLOR_RAINBOW, stringArray));
        this.useSecondColor = (BooleanSetting)this.register((Setting)new BooleanSetting("Второй цвет", "Использовать второй свой цвет.", false));
        this.customColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет", "Основной цвет анимации.", new Color(255, 255, 255, 255)).visibleWhen(() -> BlockOverlay.customColor$lambda$0(this)));
        this.customSecondColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет 2", "Второй цвет анимации.", new Color(ColorEngine.lerpColor(-1, DARK_SECOND_COLOR, 0.7f), true)).visibleWhen(() -> BlockOverlay.customSecondColor$lambda$0(this)));
        this.useSecondColor.visibleWhen(() -> BlockOverlay._init_$lambda$0(this));
    }

    @Override
    protected void onDisable() {
        this.reset();
    }

    @Override
    public float fadeOutSeconds() {
        return 0.4f;
    }

    @EventHandler
    private final void onTick(TickEvent event) {
        if (!event.isPre()) {
            return;
        }
        this.previousDamage = this.damage;
        ClientPlayerInteractionManager gameMode = this.mc.interactionManager;
        if (this.mc.player == null || this.mc.world == null || gameMode == null) {
            this.reset();
            return;
        }
        MultiPlayerGameModeAccessor accessor = (MultiPlayerGameModeAccessor)gameMode;
        BlockPos currentPos = accessor.kimiko$getDestroyBlockPos();
        float rawProgress = accessor.kimiko$getDestroyProgress();
        boolean activelyBreaking = accessor.kimiko$isDestroying() || rawProgress > 0.0f && !(rawProgress == this.previousRawProgress);
        this.previousRawProgress = rawProgress;
        if (!activelyBreaking || currentPos == null) {
            if (this.destroying && this.lastBox != null) {
                this.startFade();
            } else if (this.fading && System.currentTimeMillis() - this.fadeStartedAt >= 220L) {
                this.reset();
            } else if (!this.fading) {
                this.reset();
            }
            return;
        }
        boolean resumedAfterFade = this.fading;
        this.fading = false;
        if (this.targetPos == null) {
            BlockPos blockPos2 = this.targetPos = currentPos.toImmutable();
            Intrinsics.checkNotNull((Object)blockPos2);
            this.movementTo = this.movementFrom = BlockOverlay.Companion.blockPosition(blockPos2);
            this.movementStartedAt = System.currentTimeMillis() - 160L;
            this.skipFadeIn = false;
        } else if (!Intrinsics.areEqual((Object)this.targetPos, (Object)currentPos)) {
            long now = System.currentTimeMillis();
            this.movementFrom = this.animatedPosition(now);
            BlockPos blockPos3 = this.targetPos = currentPos.toImmutable();
            Intrinsics.checkNotNull((Object)blockPos3);
            this.movementTo = BlockOverlay.Companion.blockPosition(blockPos3);
            this.movementStartedAt = now;
            this.skipFadeIn = !resumedAfterFade;
        } else if (resumedAfterFade) {
            this.skipFadeIn = false;
        }
        this.damage = MathHelper.clamp((float)rawProgress, (float)0.0f, (float)1.0f);
        this.destroying = true;
    }

    @EventHandler
    private final void onWorldRender(WorldRenderEvent event) {
        Box box;
        if (!this.destroying && !this.fading || this.targetPos == null || this.movementFrom == null || this.movementTo == null || this.mc.world == null) {
            return;
        }
        float partialTicks = MathHelper.clamp((float)event.getPartialTicks(), (float)0.0f, (float)1.0f);
        float progress = MathHelper.clamp((float)MathHelper.lerp((float)partialTicks, (float)this.previousDamage, (float)this.damage), (float)0.0f, (float)1.0f);
        float fadeProgress = this.fading ? BlockOverlay.Companion.smoothstep(0.0f, 1.0f, MathHelper.clamp((float)((float)(System.currentTimeMillis() - this.fadeStartedAt) / 220.0f), (float)0.0f, (float)1.0f)) : 0.0f;
        Box box2 = box = this.destroying ? this.getAnimatedBox(progress) : BlockOverlay.Companion.interpolateBox(this.fadeFromBox, this.fadeToBox, fadeProgress);
        if (box == null) {
            if (this.lastBox == null) {
                return;
            }
            this.startFade();
            box = this.lastBox;
        } else if (this.destroying) {
            this.lastBox = box;
        }
        Box box3 = box;
        if (box3 == null) {
            return;
        }
        Box nonNullBox = box3;
        float alpha = (this.fading ? this.fadeStartAlpha * (1.0f - fadeProgress) : this.activeAlpha(progress)) * this.visualAlpha();
        if (alpha <= 0.0f) {
            return;
        }
        if (this.destroying) {
            this.lastRenderedAlpha = alpha;
            this.lastRenderedProgress = progress;
        }
        int[] outline = this.resolveColors(alpha);
        int[] cross = BlockOverlay.Companion.multiplyRgb(outline, 0.2f);
        int[] fill = BlockOverlay.Companion.multiplyRgb(outline, 0.03f);
        Vec3d vec3d2 = event.getCamera() != null ? event.getCamera().getCameraPos() : this.mc.gameRenderer.getCamera().getCameraPos();
        Intrinsics.checkNotNull((Object)vec3d2);
        Vec3d cameraPos = vec3d2;
        VertexConsumerProvider.Immediate immediate2 = this.mc.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        BlockOverlayRenderer.render(immediate2, event.getStack(), cameraPos, nonNullBox, outline, cross, fill);
    }

    private final Box getAnimatedBox(float progress) {
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return null;
        }
        ClientWorld level = clientWorld3;
        BlockPos blockPos2 = this.targetPos;
        if (blockPos2 == null) {
            return null;
        }
        BlockPos pos = blockPos2;
        BlockState blockState2 = level.getBlockState(pos);
        Intrinsics.checkNotNullExpressionValue((Object)blockState2, (String)"getBlockState(...)");
        BlockState state = blockState2;
        VoxelShape voxelShape2 = state.getOutlineShape((BlockView)level, pos);
        Intrinsics.checkNotNullExpressionValue((Object)voxelShape2, (String)"getShape(...)");
        VoxelShape shape = voxelShape2;
        if (shape.isEmpty()) {
            return null;
        }
        Box box2 = shape.getBoundingBox();
        Intrinsics.checkNotNullExpressionValue((Object)box2, (String)"bounds(...)");
        Box local = box2;
        Vec3d position = this.animatedPosition(System.currentTimeMillis());
        Box box3 = local.offset(position);
        Intrinsics.checkNotNullExpressionValue((Object)box3, (String)"move(...)");
        Box box = box3;
        float scale = BlockOverlay.Companion.animatedScale(progress);
        double centerX = (box.minX + box.maxX) * 0.5;
        double centerY = (box.minY + box.maxY) * 0.5;
        double centerZ = (box.minZ + box.maxZ) * 0.5;
        double halfX = box.getLengthX() * (double)scale * 0.5;
        double halfY = box.getLengthY() * (double)scale * 0.5;
        double halfZ = box.getLengthZ() * (double)scale * 0.5;
        return new Box(centerX - halfX, centerY - halfY, centerZ - halfZ, centerX + halfX, centerY + halfY, centerZ + halfZ);
    }

    private final int[] resolveColors(float alpha) {
        int[] nArray = new int[]{this.getColor(0, alpha), this.getColor(90, alpha), this.getColor(180, alpha), this.getColor(270, alpha)};
        return nArray;
    }

    private final int getColor(int index, float alpha) {
        if (this.colorMode.is(COLOR_RAINBOW)) {
            int angle = (int)((System.currentTimeMillis() / 8L + (long)index) % 360L);
            int color = ColorEngine.rainbow(angle, 1.0f, 1.0f);
            return ColorEngine.multAlpha(color | 0xFF000000, alpha);
        }
        int firstColor = 0;
        int secondColor = 0;
        if (this.colorMode.is(COLOR_CLIENT)) {
            int[] palette = ClientPalette.colors();
            if (palette != null && palette.length >= 2) {
                return ColorEngine.multAlpha(BlockOverlay.Companion.paletteFade(8, index, palette), alpha);
            }
            InterfaceModule iface = InterfaceModule.Companion.getInstance();
            if (iface != null) {
                firstColor = iface.clientPrimaryColorOpaque();
                secondColor = iface.usesSecondClientColor() ? iface.clientSecondaryColorOpaque() : firstColor;
            } else {
                firstColor = -1;
                secondColor = ColorEngine.lerpColor(firstColor, DARK_SECOND_COLOR, 0.7f);
            }
        } else {
            firstColor = this.customColor.getColor();
            int n = secondColor = this.useSecondColor.getValue() ? this.customSecondColor.getColor() : firstColor;
        }
        if (firstColor == secondColor) {
            return ColorEngine.multAlpha(firstColor, alpha);
        }
        return ColorEngine.multAlpha(BlockOverlay.Companion.fade(8, index, firstColor, secondColor), alpha);
    }

    private final Vec3d animatedPosition(long now) {
        Vec3d from = this.movementFrom;
        Vec3d to = this.movementTo;
        if (from == null || to == null) {
            Vec3d vec3d2;
            if (this.targetPos != null) {
                BlockPos blockPos2 = this.targetPos;
                Intrinsics.checkNotNull((Object)blockPos2);
                vec3d2 = BlockOverlay.Companion.blockPosition(blockPos2);
            } else {
                Vec3d vec3d3 = Vec3d.ZERO;
                vec3d2 = vec3d3;
                Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"ZERO");
            }
            return vec3d2;
        }
        float elapsed = MathHelper.clamp((float)((float)(now - this.movementStartedAt) / 160.0f), (float)0.0f, (float)1.0f);
        double eased = Easings.CUBIC_OUT.ease(elapsed);
        Vec3d vec3d4 = from.lerp(to, eased);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d4, (String)"lerp(...)");
        return vec3d4;
    }

    private final float activeAlpha(float progress) {
        float fadeOut = 1.0f - BlockOverlay.Companion.smoothstep(0.72f, 1.0f, progress);
        return this.skipFadeIn ? fadeOut : BlockOverlay.Companion.smoothstep(0.0f, 0.15f, progress) * fadeOut;
    }

    private final void startFade() {
        if (this.fading) {
            return;
        }
        this.fadeStartAlpha = Math.max(this.lastRenderedAlpha, this.activeAlpha(this.damage));
        this.fadeStartedAt = System.currentTimeMillis();
        this.fadeFromBox = this.lastBox;
        this.fadeToBox = this.lastBox;
        ClientWorld level = this.mc.world;
        BlockPos pos = this.targetPos;
        if (level != null && pos != null && !level.getBlockState(pos).isAir()) {
            float currentScale = BlockOverlay.Companion.animatedScale(this.lastRenderedProgress);
            float initialScale = BlockOverlay.Companion.animatedScale(0.0f);
            if (this.lastBox != null) {
                Box box2 = this.lastBox;
                Intrinsics.checkNotNull((Object)box2);
                this.fadeToBox = BlockOverlay.Companion.scaleBox(box2, currentScale > 0.0f ? initialScale / currentScale : 1.0f);
            }
        }
        this.fading = true;
        this.destroying = false;
    }

    private final void reset() {
        this.targetPos = null;
        this.movementFrom = null;
        this.movementTo = null;
        this.movementStartedAt = 0L;
        this.damage = 0.0f;
        this.previousDamage = 0.0f;
        this.previousRawProgress = 0.0f;
        this.destroying = false;
        this.fading = false;
        this.skipFadeIn = false;
        this.fadeStartedAt = 0L;
        this.fadeStartAlpha = 0.0f;
        this.lastRenderedAlpha = 0.0f;
        this.lastRenderedProgress = 0.0f;
        this.lastBox = null;
        this.fadeFromBox = null;
        this.fadeToBox = null;
    }

    private static final Boolean customColor$lambda$0(BlockOverlay this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean customSecondColor$lambda$0(BlockOverlay this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM) && this$0.useSecondColor.getValue();
    }

    private static final Boolean _init_$lambda$0(BlockOverlay this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ'\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ/\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0016J\u0017\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ'\u0010 \u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b \u0010!J-\u0010%\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010\"2\b\u0010$\u001a\u0004\u0018\u00010\"2\u0006\u0010\u0017\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b%\u0010&J\u001f\u0010)\u001a\u00020\"2\u0006\u0010'\u001a\u00020\"2\u0006\u0010(\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b)\u0010*R\u0014\u0010,\u001a\u00020+8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u0014\u0010.\u001a\u00020+8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010-R\u0014\u0010/\u001a\u00020\n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0014\u00102\u001a\u0002018\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0014\u00104\u001a\u0002018\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u00103R\u0014\u00105\u001a\u0002018\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b5\u00103\u00a8\u00066"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/BlockOverlay.Companion;", "", "<init>", "()V", "", "colors", "", "factor", "multiplyRgb", "([IF)[I", "", "speed", "index", "palette", "paletteFade", "(II[I)I", "first", "second", "fade", "(IIII)I", "value", "easeInOutQuad", "(F)F", "progress", "animatedScale", "Lnet/minecraft/BlockPos;", "pos", "Lnet/minecraft/Vec3d;", "blockPosition", "(Lnet/minecraft/BlockPos;)Lnet/minecraft/Vec3d;", "start", "end", "smoothstep", "(FFF)F", "Lnet/minecraft/Box;", "from", "to", "interpolateBox", "(Lnet/minecraft/Box;Lnet/minecraft/Box;F)Lnet/minecraft/Box;", "box", "scale", "scaleBox", "(Lnet/minecraft/Box;F)Lnet/minecraft/Box;", "", "FADE_DURATION_MS", "J", "MOVE_DURATION_MS", "DARK_SECOND_COLOR", "I", "", "COLOR_RAINBOW", "Ljava/lang/String;", "COLOR_CLIENT", "COLOR_CUSTOM", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final int[] multiplyRgb(int[] colors, float factor) {
            int[] result = new int[colors.length];
            for (int i = 0; i < colors.length; i++) {
                int color = colors[i];
                int red = Math.round((float)(color >>> 16 & 0xFF) * factor);
                int green = Math.round((float)(color >>> 8 & 0xFF) * factor);
                int blue = Math.round((float)(color & 0xFF) * factor);
                result[i] = ColorEngine.rgba(red, green, blue, ColorEngine.alpha(color));
            }
            return result;
        }

        private final int paletteFade(int speed, int index, int[] palette) {
            int count = palette.length;
            int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
            float position = (float)angle / 360.0f * (float)count;
            int first = (int)position % count;
            int second = (first + 1) % count;
            int firstColor = palette[first] | 0xFF000000;
            int secondColor = palette[second] | 0xFF000000;
            return ColorEngine.lerpColor(firstColor, secondColor, position - (float)Math.floor(position)) | 0xFF000000;
        }

        private final int fade(int speed, int index, int first, int second) {
            int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
            angle = angle >= 180 ? 360 - angle : angle;
            return ColorEngine.lerpColor(first, second, (float)angle / 180.0f);
        }

        private final float easeInOutQuad(float value) {
            float t = MathHelper.clamp((float)value, (float)0.0f, (float)1.0f);
            return t < 0.5f ? 2.0f * t * t : 1.0f - (float)Math.pow(-2.0f * t + 2.0f, 2.0) * 0.5f;
        }

        private final float animatedScale(float progress) {
            float scale = MathHelper.lerp((float)progress, (float)0.175f, (float)Math.min(progress + 0.175f, 1.0f));
            return this.easeInOutQuad(scale);
        }

        private final Vec3d blockPosition(BlockPos pos) {
            return new Vec3d((double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
        }

        private final float smoothstep(float start, float end, float value) {
            float t = MathHelper.clamp((float)((value - start) / (end - start)), (float)0.0f, (float)1.0f);
            return t * t * (3.0f - 2.0f * t);
        }

        private final Box interpolateBox(Box from, Box to, float progress) {
            if (from == null || to == null) {
                return null;
            }
            return new Box(MathHelper.lerp((double)progress, (double)from.minX, (double)to.minX), MathHelper.lerp((double)progress, (double)from.minY, (double)to.minY), MathHelper.lerp((double)progress, (double)from.minZ, (double)to.minZ), MathHelper.lerp((double)progress, (double)from.maxX, (double)to.maxX), MathHelper.lerp((double)progress, (double)from.maxY, (double)to.maxY), MathHelper.lerp((double)progress, (double)from.maxZ, (double)to.maxZ));
        }

        private final Box scaleBox(Box box, float scale) {
            double centerX = (box.minX + box.maxX) * 0.5;
            double centerY = (box.minY + box.maxY) * 0.5;
            double centerZ = (box.minZ + box.maxZ) * 0.5;
            double halfX = box.getLengthX() * (double)scale * 0.5;
            double halfY = box.getLengthY() * (double)scale * 0.5;
            double halfZ = box.getLengthZ() * (double)scale * 0.5;
            return new Box(centerX - halfX, centerY - halfY, centerZ - halfZ, centerX + halfX, centerY + halfY, centerZ + halfZ);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

