/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.option.GameOptions
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.option.Perspective
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.option.GameOptions;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.option.Perspective;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.input.HotBarScrollEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BindSetting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.NumberSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.utils.animations.fx.Decelerate;
import rtx.kimiko.utils.animations.fx.Direction;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 72\u00020\u0001:\u00017B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\t\u0010\bJ\u001f\u0010\f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u000e\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u000e\u0010\rJ\u001b\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fH\u0007b\u0002\b\u0011\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0003J\u000f\u0010\u0015\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0003J\u001b\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0016H\u0007b\u0002\b\u0011\u00a2\u0006\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\u00198\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010\u001bR\u0014\u0010&\u001a\u00020\u001c8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010\u001eR\u0014\u0010(\u001a\u00020'8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010,\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010+R\u0016\u0010-\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b-\u0010+R\u0016\u0010/\u001a\u00020.8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0014\u00101\u001a\u00020'8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b1\u0010)R\u0016\u00102\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u0010+R\u0016\u00103\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u0010+R\u0016\u00105\u001a\u0002048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b5\u00106\u00a8\u00068"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/CameraSettings;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "onDisable", "", "liveFov", "()F", "liveDistance", "from", "to", "startZoomAnim", "(FF)V", "startF5Anim", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "updateZoom", "updateSmoothF5", "Lrtx/kimiko/api/events/impl/input/HotBarScrollEvent;", "onScroll", "(Lrtx/kimiko/api/events/impl/input/HotBarScrollEvent;)V", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "zoomSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "zoomEnabled", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "zoomKey", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "startZoom", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "f5Separator", "smoothF5", "Lrtx/kimiko/utils/animations/fx/Decelerate;", "zoomAnim", "Lrtx/kimiko/utils/animations/fx/Decelerate;", "zoomFrom", "F", "zoomTo", "zoomTarget", "", "zoomActive", "Z", "f5Anim", "f5From", "f5To", "Lnet/minecraft/Perspective;", "lastCameraType", "Lnet/minecraft/Perspective;", "Companion", "rtx.kimiko:kimiko"})
public final class CameraSettings
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting zoomSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Приближение"));
    @NotNull
    private final BooleanSetting zoomEnabled = (BooleanSetting)this.register((Setting)new BooleanSetting("Приближение", "Плавный зум камеры по клавише. Внутри зума крутите колесо, чтобы приближать/отдалять.", true));
    @NotNull
    private final BindSetting zoomKey = (BindSetting)this.register((Setting)new BindSetting("Клавиша приближения", "Зажмите, чтобы приблизить камеру.").setType(BindSetting.Type.HOLD).visibleWhen(() -> CameraSettings.zoomKey$lambda$0(this)));
    @NotNull
    private final NumberSetting startZoom = (NumberSetting)this.register((Setting)new NumberSetting("Стартовый зум", "Насколько приближает при нажатии (меньше — сильнее).", 0.55, 0.2, 0.9, 0.01).visibleWhen(() -> CameraSettings.startZoom$lambda$0(this)));
    @NotNull
    private final SeparatorSetting f5Separator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Плавный F5"));
    @NotNull
    private final BooleanSetting smoothF5 = (BooleanSetting)this.register((Setting)new BooleanSetting("Плавный F5", "Плавно переключает камеры (F5) через зум дистанции.", false));
    @NotNull
    private final Decelerate zoomAnim = new Decelerate();
    private float zoomFrom = 1.0f;
    private float zoomTo = 1.0f;
    private float zoomTarget = 1.0f;
    private boolean zoomActive;
    @NotNull
    private final Decelerate f5Anim = new Decelerate();
    private float f5From = 1.0f;
    private float f5To = 1.0f;
    @NotNull
    private Perspective lastCameraType = Perspective.FIRST_PERSON;
    private static final long ZOOM_MS = 180L;
    private static final long F5_MS = 220L;
    private static final double SCROLL_STEP = 0.08;
    private static final float ZOOM_MIN = 0.2f;
    private static final float ZOOM_MAX = 1.0f;
    @Nullable
    private static CameraSettings companionInstance;

    public CameraSettings() {
        super("Camera Settings", "Плавный зум камеры и плавное переключение F5.", Category.UTILS);
        companionInstance = this;
        try {
            this.zoomAnim.setMs(180L);
            this.zoomAnim.setValue(1.0);
            this.f5Anim.setMs(500L);
            this.f5Anim.setValue(1.0);
        }
        catch (Throwable throwable) {
        }
    }

    @Override
    protected void onDisable() {
        this.zoomFrom = 1.0f;
        this.zoomTo = 1.0f;
        this.zoomTarget = 1.0f;
        this.f5From = 1.0f;
        this.f5To = 1.0f;
        this.zoomActive = false;
    }

    private final float liveFov() {
        return MathHelper.lerp((float)CameraSettings.Companion.easedProgress(this.zoomAnim), (float)this.zoomFrom, (float)this.zoomTo);
    }

    private final float liveDistance() {
        return MathHelper.lerp((float)CameraSettings.Companion.easedProgress(this.f5Anim), (float)this.f5From, (float)this.f5To);
    }

    private final void startZoomAnim(float from, float to) {
        this.zoomFrom = from;
        this.zoomTo = to;
        try {
            this.zoomAnim.setDirection(Direction.IN);
            this.zoomAnim.setValue(1.0);
            this.zoomAnim.counter.reset();
        }
        catch (Throwable t) {
            this.zoomFrom = to;
        }
    }

    private final void startF5Anim(float from, float to) {
        this.f5From = from;
        this.f5To = to;
        try {
            this.f5Anim.setDirection(Direction.IN);
            this.f5Anim.setValue(1.0);
            this.f5Anim.counter.reset();
        }
        catch (Throwable t) {
            this.f5From = to;
        }
    }

    @EventHandler
    public final void onTick(@NotNull TickEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!event.isPre()) {
            return;
        }
        this.updateZoom();
        this.updateSmoothF5();
    }

    private final void updateZoom() {
        boolean held;
        if (this.mc.player == null || this.mc.world == null || this.mc.currentScreen != null || !this.zoomEnabled.getValue()) {
            if (this.zoomActive || !(this.zoomTo == 1.0f)) {
                this.startZoomAnim(this.liveFov(), 1.0f);
            }
            this.zoomActive = false;
            return;
        }
        boolean bl = held = this.zoomKey.isBound() && this.zoomKey.getValue().isDown(this.mc.getWindow().getHandle());
        if (held && !this.zoomActive) {
            this.zoomTarget = MathHelper.clamp((float)this.startZoom.getFloat(), (float)0.2f, (float)1.0f);
            this.startZoomAnim(this.liveFov(), this.zoomTarget);
        } else if (!held && this.zoomActive) {
            this.startZoomAnim(this.liveFov(), 1.0f);
        }
        this.zoomActive = held;
    }

    private final void updateSmoothF5() {
        GameOptions gameOptions2 = this.mc.options;
        Intrinsics.checkNotNullExpressionValue((Object)gameOptions2, (String)"options");
        GameOptions options = gameOptions2;
        if (!this.smoothF5.getValue()) {
            this.f5From = 1.0f;
            this.f5To = 1.0f;
            Perspective perspective2 = options.getPerspective();
            Intrinsics.checkNotNullExpressionValue((Object)perspective2, (String)"getCameraType(...)");
            this.lastCameraType = perspective2;
            return;
        }
        Perspective perspective3 = options.getPerspective();
        Intrinsics.checkNotNullExpressionValue((Object)perspective3, (String)"getCameraType(...)");
        Perspective current = perspective3;
        if (current != this.lastCameraType) {
            if (current == Perspective.FIRST_PERSON) {
                this.f5From = 1.0f;
                this.f5To = 1.0f;
            } else {
                this.startF5Anim(0.2f, 1.0f);
            }
            this.lastCameraType = current;
        }
    }

    @EventHandler
    public final void onScroll(@NotNull HotBarScrollEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!(this.isEnabled() && this.zoomEnabled.getValue() && this.zoomActive)) {
            return;
        }
        this.zoomTarget = MathHelper.clamp((float)((float)((double)this.zoomTarget - event.getVertical() * 0.08)), (float)0.2f, (float)1.0f);
        this.startZoomAnim(this.liveFov(), this.zoomTarget);
        event.cancel();
    }

    private static final Boolean zoomKey$lambda$0(CameraSettings this$0) {
        return this$0.zoomEnabled.getValue();
    }

    private static final Boolean startZoom$lambda$0(CameraSettings this$0) {
        return this$0.zoomEnabled.getValue();
    }

    @JvmStatic
    public static final float getFovScale() {
        return Companion.getFovScale();
    }

    @JvmStatic
    public static final float getCameraDistanceScale() {
        return Companion.getCameraDistanceScale();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000fR\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0015R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/CameraSettings.Companion;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "getFovScale", "()F", "getCameraDistanceScale", "Lrtx/kimiko/utils/animations/fx/Decelerate;", "anim", "easedProgress", "(Lrtx/kimiko/utils/animations/fx/Decelerate;)F", "", "ZOOM_MS", "J", "F5_MS", "", "SCROLL_STEP", "D", "ZOOM_MIN", "F", "ZOOM_MAX", "Lrtx/kimiko/api/modules/impl/Utils/CameraSettings;", "companionInstance", "Lrtx/kimiko/api/modules/impl/Utils/CameraSettings;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final float getFovScale() {
            CameraSettings inst = companionInstance;
            if (inst == null || !inst.isEnabled() || !inst.zoomEnabled.getValue()) {
                return 1.0f;
            }
            return inst.liveFov();
        }

        @JvmStatic
        public final float getCameraDistanceScale() {
            CameraSettings inst = companionInstance;
            if (inst == null || !inst.isEnabled() || !inst.smoothF5.getValue()) {
                return 1.0f;
            }
            return inst.liveDistance();
        }

        private final float easedProgress(Decelerate anim) {
            float f;
            try {
                f = (float)Math.min(Math.max(anim.getValue(), 0.0), 1.0);
            }
            catch (Throwable t) {
                f = 1.0f;
            }
            return f;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

