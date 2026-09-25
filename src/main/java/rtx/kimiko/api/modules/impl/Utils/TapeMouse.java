/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Utils;

import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import mixin.accessor.MinecraftAccessor;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"tapemouse"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u0004H\u0015b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\t\u0010\u0003J\u001b\u0010\r\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001d\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001c\u00ca\u0001\u0010\b\u001f\u0012\f\b\u0006\u0012\b\b\fJ\u0004\b\b( \u00a8\u0006!"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/TapeMouse;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "onEnable", "Lrtx/kimiko/api/events/impl/render/HudRenderEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onHud", "(Lrtx/kimiko/api/events/impl/render/HudRenderEvent;)V", "", "computeDelay", "()J", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "button", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "cps", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "Ljava/util/Random;", "random", "Ljava/util/Random;", "lastClick", "J", "nextDelayMs", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "tapemouse", "rtx.kimiko:kimiko"})
public final class TapeMouse
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ModeSetting button;
    @NotNull
    private final SliderSetting cps;
    @NotNull
    private final Random random;
    private long lastClick;
    private long nextDelayMs;
    @NotNull
    private static final String LEFT = "ЛКМ";
    @NotNull
    private static final String RIGHT = "ПКМ";

    public TapeMouse() {
        super("Tape Mouse", "Автокликер выбранной кнопкой мыши с настраиваемой скоростью.", Category.UTILS);
        String[] stringArray = new String[]{LEFT, RIGHT};
        this.button = (ModeSetting)this.register((Setting)new ModeSetting("Кнопка", "Какую кнопку мыши автоматически кликать.", LEFT, stringArray));
        this.cps = (SliderSetting)this.register((Setting)new SliderSetting("Скорость", "Кликов в секунду (CPS).").setValue(10.0f).range(1, 20).increment(1));
        this.random = new Random();
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onEnable() {
        this.lastClick = 0L;
        this.nextDelayMs = 0L;
    }

    @EventHandler
    public final void onHud(@NotNull HudRenderEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (this.mc.player == null || this.mc.world == null || this.mc.currentScreen != null || this.mc.getWindow() == null) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - this.lastClick < this.nextDelayMs) {
            return;
        }
        this.lastClick = now;
        this.nextDelayMs = this.computeDelay();
        if (this.button.is(RIGHT)) {
            MinecraftClient minecraftClient2 = this.mc;
            Intrinsics.checkNotNull((Object)minecraftClient2, (String)"null cannot be cast to non-null type mixin.accessor.MinecraftAccessor");
            ((MinecraftAccessor)minecraftClient2).kimiko$startUseItem();
        } else {
            MinecraftClient minecraftClient3 = this.mc;
            Intrinsics.checkNotNull((Object)minecraftClient3, (String)"null cannot be cast to non-null type mixin.accessor.MinecraftAccessor");
            ((MinecraftAccessor)minecraftClient3).kimiko$startAttack();
        }
    }

    private final long computeDelay() {
        double base = 1000.0 / (double)Math.max(1, this.cps.getInt());
        double jitter = 0.8 + this.random.nextDouble() * 0.4;
        return Math.max(1L, MathKt.roundToLong((double)(base * jitter)));
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006\u00a8\u0006\b"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/TapeMouse.Companion;", "", "<init>", "()V", "", "LEFT", "Ljava/lang/String;", "RIGHT", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

