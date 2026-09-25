/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.events.impl.module;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.Event;
import rtx.kimiko.api.modules.Module;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\b\u001a\u00020\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0004\u00a2\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\fR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/events/impl/module/ModuleToggleEvent;", "Lrtx/kimiko/api/events/Event;", "Lrtx/kimiko/api/modules/Module;", "module", "", "enabled", "<init>", "(Lrtx/kimiko/api/modules/Module;Z)V", "getModule", "()Lrtx/kimiko/api/modules/Module;", "isEnabled", "()Z", "Lrtx/kimiko/api/modules/Module;", "Z", "rtx.kimiko:kimiko"})
public final class ModuleToggleEvent
extends Event {
    @NotNull
    private final Module module;
    private final boolean enabled;

    public ModuleToggleEvent(@NotNull Module module, boolean enabled) {
        Intrinsics.checkNotNullParameter((Object)module, (String)"module");
        this.module = module;
        this.enabled = enabled;
    }

    @NotNull
    public final Module getModule() {
        return this.module;
    }

    public final boolean isEnabled() {
        return this.enabled;
    }
}

