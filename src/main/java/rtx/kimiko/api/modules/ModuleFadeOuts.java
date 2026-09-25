/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Module;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\f\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\f\u0010\rR\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/modules/ModuleFadeOuts;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/Module;", "module", "", "watch", "(Lrtx/kimiko/api/modules/Module;)V", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "Ljava/util/concurrent/CopyOnWriteArrayList;", "active", "Ljava/util/concurrent/CopyOnWriteArrayList;", "rtx.kimiko:kimiko"})
public final class ModuleFadeOuts {
    @NotNull
    public static final ModuleFadeOuts INSTANCE = new ModuleFadeOuts();
    @NotNull
    private static final CopyOnWriteArrayList<Module> active = new CopyOnWriteArrayList();

    private ModuleFadeOuts() {
    }

    public final void watch(@NotNull Module module) {
        Intrinsics.checkNotNullParameter((Object)module, (String)"module");
        active.addIfAbsent(module);
    }

    @EventHandler
    public final void onTick(@NotNull TickEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!event.isPre() || active.isEmpty()) {
            return;
        }
        Iterator<Module> iterator = active.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Module> iterator2 = iterator;
        while (iterator2.hasNext()) {
            Module module = iterator2.next();
            if (module.isEnabled()) {
                active.remove(module);
                continue;
            }
            if (module.isFadingOut() && !(module.visualAlpha() <= 0.001f)) continue;
            module.finishFadeOut$rtx_kimiko_kimiko();
            active.remove(module);
        }
    }

    static {
        EventBus.Companion.get().subscribe(INSTANCE);
    }
}

