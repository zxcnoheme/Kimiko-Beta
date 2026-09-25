/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Interface;

import java.util.ArrayDeque;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.input.MouseButtonEvent;
import rtx.kimiko.api.modules.impl.Interface.InterfaceComponentModule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0003b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\n\u00a2\u0006\u0004\b\r\u0010\fR\u0019\u0010\u0010\u001a\u00020\u000e8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000f\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0012\u001a\u00020\u000e8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000f\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0011R\u0019\u0010\u0013\u001a\u00020\u000e8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000f\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0017\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/KeyStrokesModule;", "Lrtx/kimiko/api/modules/impl/Interface/InterfaceComponentModule;", "<init>", "()V", "Lrtx/kimiko/api/events/impl/input/MouseButtonEvent;", "event", "", "Lrtx/kimiko/api/events/EventHandler;", "onMouseButton", "(Lrtx/kimiko/api/events/impl/input/MouseButtonEvent;)V", "", "leftCps", "()I", "rightCps", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Lkotlin/jvm/JvmField;", "showMouse", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "showCps", "showSpace", "Ljava/util/ArrayDeque;", "", "leftClicks", "Ljava/util/ArrayDeque;", "rightClicks", "Companion", "rtx.kimiko:kimiko"})
public final class KeyStrokesModule
extends InterfaceComponentModule {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public final BooleanSetting showMouse = (BooleanSetting)this.register((Setting)new BooleanSetting("Кнопки мыши", "Показывать ЛКМ/ПКМ.", true));
    @JvmField
    @NotNull
    public final BooleanSetting showCps = (BooleanSetting)this.register((Setting)new BooleanSetting("CPS", "Показывать клики в секунду на кнопках мыши.", true));
    @JvmField
    @NotNull
    public final BooleanSetting showSpace = (BooleanSetting)this.register((Setting)new BooleanSetting("Пробел", "Показывать полоску пробела.", true));
    @NotNull
    private final ArrayDeque<Long> leftClicks = new ArrayDeque();
    @NotNull
    private final ArrayDeque<Long> rightClicks = new ArrayDeque();
    private static final long CPS_WINDOW_MS = 1000L;

    public KeyStrokesModule() {
        super("Key Strokes", "Нажатия клавиш движения и кликов: перетаскиваемый блок.");
        this.showCps.visibleWhen(() -> KeyStrokesModule._init_$lambda$0(this));
    }

    @EventHandler
    private final void onMouseButton(MouseButtonEvent event) {
        if (event.action != MouseButtonEvent.Action.PRESS || this.mc.currentScreen != null) {
            return;
        }
        long now = System.currentTimeMillis();
        if (event.button == 0) {
            this.leftClicks.addLast(now);
        } else if (event.button == 1) {
            this.rightClicks.addLast(now);
        }
    }

    public final int leftCps() {
        return KeyStrokesModule.Companion.prune(this.leftClicks);
    }

    public final int rightCps() {
        return KeyStrokesModule.Companion.prune(this.rightClicks);
    }

    private static final Boolean _init_$lambda$0(KeyStrokesModule this$0) {
        return this$0.showMouse.getValue();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00072\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00058\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000b\u00a8\u0006\f"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/KeyStrokesModule.Companion;", "", "<init>", "()V", "Ljava/util/ArrayDeque;", "", "clicks", "", "prune", "(Ljava/util/ArrayDeque;)I", "CPS_WINDOW_MS", "J", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final int prune(ArrayDeque<Long> clicks) {
            long cutoff = System.currentTimeMillis() - 1000L;
            while (!clicks.isEmpty() && ((Number)clicks.peekFirst()).longValue() < cutoff) {
                clicks.pollFirst();
            }
            return clicks.size();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

