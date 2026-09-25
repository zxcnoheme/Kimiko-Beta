/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.events.impl.input;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.CancellableEvent;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\fB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\t\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\nR\u0019\u0010\u0004\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\t\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\nR\u0019\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\t\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u000b\u00a8\u0006\r"}, d2={"Lrtx/kimiko/api/events/impl/input/MouseButtonEvent;", "Lrtx/kimiko/api/events/CancellableEvent;", "", "button", "modifiers", "Lrtx/kimiko/api/events/impl/input/MouseButtonEvent$Action;", "action", "<init>", "(IILrtx/kimiko/api/events/impl/input/MouseButtonEvent$Action;)V", "Lkotlin/jvm/JvmField;", "I", "Lrtx/kimiko/api/events/impl/input/MouseButtonEvent$Action;", "Action", "rtx.kimiko:kimiko"})
public final class MouseButtonEvent
extends CancellableEvent {
    @JvmField
    public final int button;
    @JvmField
    public final int modifiers;
    @JvmField
    @NotNull
    public final Action action;

    public MouseButtonEvent(int button, int modifiers, @NotNull Action action) {
        Intrinsics.checkNotNullParameter((Object)((Object)action), (String)"action");
        this.button = button;
        this.modifiers = modifiers;
        this.action = action;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u0000 \u00042\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0004B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2={"Lrtx/kimiko/api/events/impl/input/MouseButtonEvent$Action;", "", "<init>", "(Ljava/lang/String;I)V", "Companion", "PRESS", "RELEASE", "rtx.kimiko:kimiko"})
    public static enum Action {
        PRESS,
        RELEASE;

        @NotNull
        public static final Companion Companion;
        @NotNull
        public static EnumEntries<Action> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

        @JvmStatic
        @NotNull
        public static final Action of(int glfw) {
            return Companion.of(glfw);
        }

            static {
        Companion = new Companion(null);
    }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/api/events/impl/input/MouseButtonEvent$Action.Companion;", "", "<init>", "()V", "", "glfw", "Lrtx/kimiko/api/events/impl/input/MouseButtonEvent$Action;", "Lkotlin/jvm/JvmStatic;", "of", "(I)Lrtx/kimiko/api/events/impl/input/MouseButtonEvent$Action;", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            @JvmStatic
            @NotNull
            public final Action of(int glfw) {
                return glfw == 1 ? PRESS : RELEASE;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }
}

