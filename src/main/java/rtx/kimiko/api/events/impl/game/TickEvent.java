/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.events.impl.game;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.Event;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000 \r2\u00020\u0001:\u0002\u000e\rB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\nJ\r\u0010\u000b\u001a\u00020\b\u00a2\u0006\u0004\b\u000b\u0010\nR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\f\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/api/events/impl/game/TickEvent;", "Lrtx/kimiko/api/events/Event;", "Lrtx/kimiko/api/events/impl/game/TickEvent$Phase;", "phase", "<init>", "(Lrtx/kimiko/api/events/impl/game/TickEvent$Phase;)V", "getPhase", "()Lrtx/kimiko/api/events/impl/game/TickEvent$Phase;", "", "isPre", "()Z", "isPost", "Lrtx/kimiko/api/events/impl/game/TickEvent$Phase;", "Companion", "Phase", "rtx.kimiko:kimiko"})
public final class TickEvent
extends Event {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Phase phase;
    @JvmField
    @NotNull
    public static final TickEvent PRE = new TickEvent(Phase.PRE);
    @JvmField
    @NotNull
    public static final TickEvent POST = new TickEvent(Phase.POST);

    public TickEvent(@NotNull Phase phase) {
        Intrinsics.checkNotNullParameter((Object)((Object)phase), (String)"phase");
        this.phase = phase;
    }

    @NotNull
    public final Phase getPhase() {
        return this.phase;
    }

    public final boolean isPre() {
        return this.phase == Phase.PRE;
    }

    public final boolean isPost() {
        return this.phase == Phase.POST;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u0019\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0007\u00a8\u0006\t"}, d2={"Lrtx/kimiko/api/events/impl/game/TickEvent.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "Lkotlin/jvm/JvmField;", "PRE", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "POST", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2={"Lrtx/kimiko/api/events/impl/game/TickEvent$Phase;", "", "<init>", "(Ljava/lang/String;I)V", "PRE", "POST", "rtx.kimiko:kimiko"})
    public static enum Phase {
        PRE,
        POST;

        @NotNull
        public static EnumEntries<Phase> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

        
    }
}

