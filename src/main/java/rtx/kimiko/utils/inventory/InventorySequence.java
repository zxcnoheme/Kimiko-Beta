/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.inventory;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001\u001bB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\n\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u0003J\r\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\f\u00a2\u0006\u0004\b\u0011\u0010\u0003J\r\u0010\u0012\u001a\u00020\f\u00a2\u0006\u0004\b\u0012\u0010\u0003R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0017\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0019\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/utils/inventory/InventorySequence;", "", "<init>", "()V", "Ljava/lang/Runnable;", "action", "then", "(Ljava/lang/Runnable;)Lrtx/kimiko/utils/inventory/InventorySequence;", "", "delayMs", "thenAfter", "(JLjava/lang/Runnable;)Lrtx/kimiko/utils/inventory/InventorySequence;", "", "start", "", "isRunning", "()Z", "cancel", "tick", "Ljava/util/Deque;", "Lrtx/kimiko/utils/inventory/InventorySequence$Step;", "steps", "Ljava/util/Deque;", "readyAt", "J", "started", "Z", "Step", "rtx.kimiko:kimiko"})
public final class InventorySequence {
    @NotNull
    private final Deque<Step> steps = new ArrayDeque();
    private long readyAt;
    private boolean started;

    @NotNull
    public final InventorySequence then(@Nullable Runnable action) {
        return this.thenAfter(0L, action);
    }

    @NotNull
    public final InventorySequence thenAfter(long delayMs, @Nullable Runnable action) {
        if (action != null) {
            this.steps.add(new Step(action, Math.max(0L, delayMs)));
        }
        return this;
    }

    public final void start() {
        this.started = true;
        this.readyAt = System.currentTimeMillis();
    }

    public final boolean isRunning() {
        return this.started && !((Collection)this.steps).isEmpty();
    }

    public final void cancel() {
        this.steps.clear();
        this.started = false;
    }

    public final void tick() {
        if (!this.started) {
            return;
        }
        long now = System.currentTimeMillis();
        while (!((Collection)this.steps).isEmpty() && now >= this.readyAt) {
            Step step = this.steps.poll();
            step.getAction().run();
            Step next = this.steps.peek();
            if (next == null) {
                this.started = false;
                continue;
            }
            this.readyAt = now + next.getDelayMs();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ$\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0016\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u000b\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/utils/inventory/InventorySequence$Step;", "", "Ljava/lang/Runnable;", "action", "", "delayMs", "<init>", "(Ljava/lang/Runnable;J)V", "component1", "()Ljava/lang/Runnable;", "component2", "()J", "copy", "(Ljava/lang/Runnable;J)Lrtx/kimiko/utils/inventory/InventorySequence$Step;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Ljava/lang/Runnable;", "getAction", "J", "getDelayMs", "rtx.kimiko:kimiko"})
    private static final class Step {
        @NotNull
        private final Runnable action;
        private final long delayMs;

        public Step(@NotNull Runnable action, long delayMs) {
            Intrinsics.checkNotNullParameter((Object)action, (String)"action");
            this.action = action;
            this.delayMs = delayMs;
        }

        @NotNull
        public final Runnable getAction() {
            return this.action;
        }

        public final long getDelayMs() {
            return this.delayMs;
        }

        @NotNull
        public final Runnable component1() {
            return this.action;
        }

        public final long component2() {
            return this.delayMs;
        }

        @NotNull
        public final Step copy(@NotNull Runnable action, long delayMs) {
            Intrinsics.checkNotNullParameter((Object)action, (String)"action");
            return new Step(action, delayMs);
        }

        public static /* synthetic */ Step copy$default(Step step, Runnable runnable, long l, int n, Object object) {
            if ((n & 1) != 0) {
                runnable = step.action;
            }
            if ((n & 2) != 0) {
                l = step.delayMs;
            }
            return step.copy(runnable, l);
        }

        @NotNull
        public String toString() {
            return "Step(action=" + this.action + ", delayMs=" + this.delayMs + ")";
        }

        public int hashCode() {
            int result = this.action.hashCode();
            result = result * 31 + Long.hashCode(this.delayMs);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Step)) {
                return false;
            }
            Step step = (Step)other;
            if (!Intrinsics.areEqual((Object)this.action, (Object)step.action)) {
                return false;
            }
            return this.delayMs == step.delayMs;
        }
    }
}

