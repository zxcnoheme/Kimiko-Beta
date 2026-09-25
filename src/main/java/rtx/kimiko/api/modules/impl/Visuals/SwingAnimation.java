/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  fun.shape.sdk.Fold
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.Hand
 *  net.minecraft.entity.effect.StatusEffectUtil
 *  net.minecraft.entity.effect.StatusEffectInstance
 *  net.minecraft.entity.effect.StatusEffects
 *  net.minecraft.util.Arm
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.item.CrossbowItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.TridentItem
 *  net.minecraft.util.hit.HitResult
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.hit.EntityHitResult
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.network.ClientPlayerEntity
 *  net.minecraft.util.math.RotationAxis
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Quaternionfc
 */
package rtx.kimiko.api.modules.impl.Visuals;

import fun.shape.sdk.Fold;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mixin.accessor.LivingEntityAccessor;
import net.minecraft.util.Hand;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Arm;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.util.hit.HitResult;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionfc;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.NumberSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.api.ui.UI;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"swinganimation"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u0000 82\u00020\u0001:\u00018B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001f\u0010\u000e\u001a\u00020\u0006H\u0015b\u000e\b\n\u0012\n\b\u000b\u0012\u0006\b\n0\f8\r\u00a2\u0006\u0004\b\u000e\u0010\u0003J\u0017\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012JG\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0017H\u0003b\u000e\b\n\u0012\n\b\u000b\u0012\u0006\b\n0\f8\r\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010 \u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b \u0010\u001fR\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010%\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010(\u001a\u00020'8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0014\u0010+\u001a\u00020*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b+\u0010,R\u0014\u0010-\u001a\u00020*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b-\u0010,R\u0014\u0010.\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b.\u0010&R\u0016\u0010/\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0016\u00102\u001a\u0002018\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0016\u00104\u001a\u0002018\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u00103R\u0016\u00105\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b5\u00106R\u0016\u00107\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b7\u00106\u00ca\u0001\u0010\b9\u0012\f\b\u000b\u0012\b\b\fJ\u0004\b\b(:\u00ca\u0001\u0002\b;\u00a8\u0006<"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/SwingAnimation;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "onDisable", "", "now", "triggerLocalPreview", "(J)V", "Lnet/minecraft/MatrixStack;", "matrices", "", "handOffset", "", "swingProgress", "anim", "strength", "applyRealismPreset", "(Lnet/minecraft/MatrixStack;IFFF)V", "x", "easeOutElastic", "(F)F", "easeOutBack", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "animationSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "autoSwing", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "swingType", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "hitStrength", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "swingSpeed", "onlyOnTarget", "realismSlashSide", "I", "", "realismSlashReady", "Z", "previewWasOpen", "nextPreviewAt", "J", "previewSwingUntil", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "swinganimation", "Lfun/shape/sdk/Fold;", "rtx.kimiko:kimiko"})
@Fold
public final class SwingAnimation
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting animationSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Анимация"));
    @NotNull
    private final BooleanSetting autoSwing = (BooleanSetting)this.register((Setting)new BooleanSetting("Авто взмах", "Показывать локальный предпросмотр взмаха в настройках каждые две секунды.", false));
    @NotNull
    private final ModeSetting swingType;
    @NotNull
    private final NumberSetting hitStrength;
    @NotNull
    private final NumberSetting swingSpeed;
    @NotNull
    private final BooleanSetting onlyOnTarget;
    private int realismSlashSide;
    private boolean realismSlashReady;
    private boolean previewWasOpen;
    private long nextPreviewAt;
    private long previewSwingUntil;
    private static final long AUTO_SWING_INTERVAL_MS = 1000L;
    private static final float BASE_X = 0.56f;
    private static final float BASE_Y = -0.52f;
    private static final float BASE_Z = -0.72f;
    @JvmField
    @Nullable
    public static SwingAnimation INSTANCE;

    public SwingAnimation() {
        super("Swing Animation", "Кастомные анимации взмаха от первого лица.", Category.VISUALS);
        String[] stringArray = new String[]{"Взмах", "Взмах 2", "Сдвиг", "Слом", "Выпад", "Копьё", "Вниз", "Желе", "Шлепок", "Бонк", "Круг", "Реализм", "Стандарт"};
        this.swingType = (ModeSetting)this.register((Setting)new ModeSetting("Тип", "Пресет анимации взмаха.", "Взмах", stringArray));
        this.hitStrength = (NumberSetting)this.register((Setting)new NumberSetting("Сила", "Сила анимации взмаха.", 1.0, 0.5, 3.0, 0.05));
        this.swingSpeed = (NumberSetting)this.register((Setting)new NumberSetting("Длительность", "Множитель длительности анимации взмаха.", 1.0, 0.5, 4.0, 0.05));
        this.onlyOnTarget = (BooleanSetting)this.register((Setting)new BooleanSetting("Только при наводке", "Проигрывать анимацию только когда прицел наведён на существо.", false));
        this.realismSlashSide = -1;
        this.realismSlashReady = true;
        INSTANCE = this;
    }

    @EventHandler
    public final void onTick(@NotNull TickEvent event) {
        boolean previewOpen;
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        ClientPlayerEntity player = this.mc.player;
        if (!event.isPost() || player == null) {
            return;
        }
        boolean bl = previewOpen = this.autoSwing.getValue() && UI.Companion.isSettingsOpenFor(this.getName());
        if (!previewOpen) {
            this.previewWasOpen = false;
            this.nextPreviewAt = 0L;
            return;
        }
        long now = System.currentTimeMillis();
        if (!this.previewWasOpen || now >= this.nextPreviewAt) {
            this.triggerLocalPreview(now);
            this.nextPreviewAt = now + 1000L;
        }
        this.previewWasOpen = true;
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onDisable() {
        this.previewWasOpen = false;
        this.nextPreviewAt = 0L;
        this.previewSwingUntil = 0L;
    }

    private final void triggerLocalPreview(long now) {
        Integer duration;
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        LivingEntityAccessor livingEntityAccessor = clientPlayerEntity2 instanceof LivingEntityAccessor ? (LivingEntityAccessor)clientPlayerEntity2 : null;
        if (livingEntityAccessor == null) {
            return;
        }
        LivingEntityAccessor player = livingEntityAccessor;
        player.kimiko$setSwingTime(-1);
        player.kimiko$setSwinging(true);
        player.kimiko$setSwingingArm(Hand.MAIN_HAND);
        Integer n = duration = Companion.currentSwingDuration();
        this.previewSwingUntil = now + (n == null ? 400L : (long)n.intValue() * 50L + 100L);
    }

    @Protect(value=Level.CROWN)
    private final void applyRealismPreset(MatrixStack matrices, int handOffset, float swingProgress, float anim, float strength) {
        if (swingProgress < 0.12f && this.realismSlashReady) {
            this.realismSlashSide *= -1;
            this.realismSlashReady = false;
        } else if (swingProgress > 0.78f) {
            this.realismSlashReady = true;
        }
        int slashSide = this.realismSlashSide;
        float sideTilt = (float)(handOffset * slashSide) * 22.0f * anim * MathHelper.clamp((float)strength, (float)0.5f, (float)2.0f);
        float strikeRotation = -90.0f * anim * (strength * 0.3f);
        float bounceRotation = 45.0f * this.easeOutElastic(swingProgress * swingProgress) * anim;
        matrices.scale(1.0f, 1.0f, anim + 1.0f);
        matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(sideTilt));
        matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(strikeRotation));
        matrices.multiply((Quaternionfc)RotationAxis.NEGATIVE_X.rotationDegrees(bounceRotation));
    }

    private final float easeOutElastic(float x) {
        if (x == 0.0f) {
            return 0.0f;
        }
        if (x == 1.0f) {
            return 1.0f;
        }
        double c4 = 2.0943951023931953;
        return (float)(Math.pow(2.0, -10.0f * x) * Math.sin(((double)(x * 10.0f) - 0.75) * c4) + 1.0);
    }

    private final float easeOutBack(float x) {
        float c1 = 1.70158f;
        float c3 = c1 + 1.0f;
        return 1.0f + c3 * (float)Math.pow(x - 1.0f, 3.0) + c1 * (float)Math.pow(x - 1.0f, 2.0);
    }

    @JvmStatic
    @Nullable
    public static final SwingAnimation getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    @Nullable
    public static final Integer currentSwingDuration() {
        return Companion.currentSwingDuration();
    }

    @JvmStatic
    public static final boolean applyAnimation(@Nullable MatrixStack matrices, @NotNull Hand hand, float swingProgress) {
        return Companion.applyAnimation(matrices, hand, swingProgress);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\t\u001a\u0004\u0018\u00010\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\nJ-\u0010\u0012\u001a\u00020\u00112\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0018R\u001d\u0010\u001c\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u001b\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001d\u00a8\u0006\u001e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/SwingAnimation.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/SwingAnimation;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/SwingAnimation;", "", "currentSwingDuration", "()Ljava/lang/Integer;", "Lnet/minecraft/MatrixStack;", "matrices", "Lnet/minecraft/Hand;", "hand", "", "swingProgress", "", "applyAnimation", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/Hand;F)Z", "", "AUTO_SWING_INTERVAL_MS", "J", "BASE_X", "F", "BASE_Y", "BASE_Z", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/SwingAnimation;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final SwingAnimation getInstance() {
            SwingAnimation module = ModuleManager.Companion.get().get(SwingAnimation.class);
            SwingAnimation swingAnimation = module;
            if (swingAnimation == null) {
                swingAnimation = INSTANCE;
            }
            return swingAnimation;
        }

        @JvmStatic
        @Nullable
        public final Integer currentSwingDuration() {
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient mc = minecraftClient2;
            SwingAnimation module = this.getInstance();
            ClientPlayerEntity player = mc.player;
            if (module == null || !module.isEnabled() || player == null || module.swingType.is("Стандарт")) {
                return null;
            }
            float animation = module.swingSpeed.getFloat();
            if (StatusEffectUtil.hasHaste((LivingEntity)((LivingEntity)player))) {
                animation *= (float)(6 - (1 + StatusEffectUtil.getHasteAmplifier((LivingEntity)((LivingEntity)player))));
            } else {
                StatusEffectInstance effect = player.getStatusEffect(StatusEffects.MINING_FATIGUE);
                animation *= player.hasStatusEffect(StatusEffects.MINING_FATIGUE) && effect != null ? (float)(6 + (1 + effect.getAmplifier()) * 2) : 6.0f;
            }
            return Math.max(1, (int)animation);
        }

        @JvmStatic
        public final boolean applyAnimation(@Nullable MatrixStack matrices, @NotNull Hand hand, float swingProgress) {
            ClientPlayerEntity player;
            SwingAnimation module;
            block49: {
                block50: {
                    Intrinsics.checkNotNullParameter((Object)hand, (String)"hand");
                    MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
                    Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
                    MinecraftClient mc = minecraftClient2;
                    module = this.getInstance();
                    player = mc.player;
                    if (module == null || !module.isEnabled() || player == null || matrices == null) {
                        return false;
                    }
                    if (module.swingType.is("Стандарт")) {
                        return false;
                    }
                    ItemStack itemStack2 = player.getStackInHand(hand);
                    Intrinsics.checkNotNullExpressionValue((Object)itemStack2, (String)"getItemInHand(...)");
                    ItemStack stack = itemStack2;
                    if (stack.getItem() instanceof TridentItem || stack.getItem() instanceof CrossbowItem) {
                        return false;
                    }
                    if (player.isUsingItem() && player.getActiveHand() == hand) {
                        return false;
                    }
                    if (hand != Hand.MAIN_HAND) {
                        return false;
                    }
                    if (!module.onlyOnTarget.getValue() || System.currentTimeMillis() < module.previewSwingUntil) break block49;
                    if (!(mc.crosshairTarget instanceof EntityHitResult)) break block50;
                    HitResult hitResult2 = mc.crosshairTarget;
                    Intrinsics.checkNotNull((Object)hitResult2, (String)"null cannot be cast to non-null type net.minecraft.world.phys.EntityHitResult");
                    if (((EntityHitResult)hitResult2).getEntity() instanceof LivingEntity) break block49;
                }
                return false;
            }
            Arm arm2 = player.getMainArm();
            Intrinsics.checkNotNullExpressionValue((Object)arm2, (String)"getMainArm(...)");
            Arm mainArm = arm2;
            int handOffset = mainArm == Arm.RIGHT ? 1 : -1;
            float sin1 = (float)Math.sin(swingProgress * swingProgress * (float)Math.PI);
            float sin2 = (float)Math.sin(MathHelper.sqrt((float)swingProgress) * (float)Math.PI);
            float anim = (float)Math.sin((double)swingProgress * Math.PI);
            float strength = module.hitStrength.getFloat();
            matrices.translate((double)((float)handOffset * 0.56f), (double)-0.52f, (double)-0.72f);
            switch (module.swingType.getSelected()) {
                case "Взмах 2": {
                    matrices.scale(1.0f, 1.0f, anim + 1.0f);
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(-90.0f * anim * (strength * 0.3f)));
                    break;
                }
                case "Сдвиг": {
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees((float)handOffset * 90.0f));
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees((float)handOffset * -60.0f));
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(-90.0f - 20.0f * anim * strength));
                    break;
                }
                case "Слом": {
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees((float)handOffset * 90.0f));
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees((float)handOffset * -30.0f));
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(-90.0f - 20.0f * anim * strength));
                    break;
                }
                case "Выпад": {
                    matrices.translate(0.0, 0.0, (double)(-anim * 0.2f));
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees((float)handOffset * 45.0f));
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees((float)handOffset * sin1 * -20.0f));
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees((float)handOffset * sin2 * -20.0f));
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(sin2 * -MathHelper.clamp((float)(strength * 55.0f), (float)55.0f, (float)80.0f)));
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees((float)handOffset * 10.0f));
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(-80.0f));
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees((float)handOffset * 60.0f));
                    break;
                }
                case "Копьё": {
                    matrices.translate(0.0, 0.0, (double)(-anim * 0.2f));
                    matrices.scale(1.0f, 1.0f, anim * (strength * 0.35f) + 1.0f);
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(-90.0f));
                    break;
                }
                case "Вниз": {
                    matrices.translate((double)(-anim * 0.5f), (double)(anim * 0.25f), 0.0);
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees((float)handOffset * 65.0f));
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(-110.0f - 80.0f * anim));
                    break;
                }
                case "Желе": {
                    matrices.translate(0.0, 0.0, (double)(-anim * 0.2f));
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees((float)handOffset * 35.0f));
                    matrices.scale(1.0f, 1.0f, anim * (swingProgress * 0.65f * strength * 0.2f) + 1.0f);
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(-90.0f));
                    break;
                }
                case "Шлепок": {
                    matrices.multiply((Quaternionfc)RotationAxis.NEGATIVE_X.rotationDegrees(sin2 * 55.0f));
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(sin2 * sin1 * -120.0f * strength));
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees((float)handOffset * (sin2 * 60.0f)));
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(sin2 * 55.0f));
                    break;
                }
                case "Бонк": {
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(15.0f * (float)handOffset));
                    float eased = module.easeOutElastic(swingProgress * swingProgress);
                    matrices.multiply((Quaternionfc)RotationAxis.NEGATIVE_X.rotationDegrees(95.0f * eased));
                    break;
                }
                case "Круг": {
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(15.0f * (float)handOffset));
                    float mix = MathHelper.lerp((float)(1.0f - swingProgress), (float)swingProgress, (float)(swingProgress * 0.5f));
                    float flip = module.easeOutBack(mix);
                    matrices.multiply((Quaternionfc)RotationAxis.NEGATIVE_X.rotationDegrees(360.0f * flip + 45.0f));
                    break;
                }
                case "Реализм": {
                    module.applyRealismPreset(matrices, handOffset, swingProgress, anim, strength);
                    break;
                }
                case "Взмах": {
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees((float)handOffset * (45.0f + sin1 * -20.0f)));
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees((float)handOffset * sin2 * -20.0f));
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(sin2 * -strength * 22.0f));
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees((float)handOffset * -45.0f));
                    break;
                }
                default: {
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(45.0f * (float)handOffset));
                    matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(-45.0f * (float)handOffset));
                }
            }
            return true;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

