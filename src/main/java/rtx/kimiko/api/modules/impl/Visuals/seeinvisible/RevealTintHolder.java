/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Visuals.seeinvisible;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u0000 \t2\u00020\u0001:\u0001\tJ\u000f\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0007\u0010\b\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006\n\u00c0\u0006\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/seeinvisible/RevealTintHolder;", "", "", "kimiko$getRevealTint", "()I", "tint", "", "kimiko$setRevealTint", "(I)V", "Companion", "rtx.kimiko:kimiko"})
public interface RevealTintHolder {
    @NotNull
    public static final Companion Companion = rtx.kimiko.api.modules.impl.Visuals.seeinvisible.RevealTintHolder.Companion.$$INSTANCE;
    public static final int NO_TINT = -1;

    public int kimiko$getRevealTint();

    public void kimiko$setRevealTint(int var1);

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/seeinvisible/RevealTintHolder.Companion;", "", "<init>", "()V", "", "NO_TINT", "I", "rtx.kimiko:kimiko"})
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE;
        public static final int NO_TINT = -1;

        private Companion() {
        }

        static {
            $$INSTANCE = new Companion();
        }
    }
}

