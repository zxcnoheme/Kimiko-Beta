/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Visuals.lootview;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u00a2\u0006\u0004\b\t\u0010\nR\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000b\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\fR\u0019\u0010\u0004\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000b\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\fR\u0019\u0010\u0005\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000b\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\fR\u0019\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000b\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\rR\u0019\u0010\b\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000b\u00a2\u0006\u0006\n\u0004\b\b\u0010\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/lootview/LootShape;", "", "", "xs", "zs", "jitter", "", "color", "count", "<init>", "([F[F[FII)V", "Lkotlin/jvm/JvmField;", "[F", "I", "rtx.kimiko:kimiko"})
public final class LootShape {
    @JvmField
    @NotNull
    public final float[] xs;
    @JvmField
    @NotNull
    public final float[] zs;
    @JvmField
    @NotNull
    public final float[] jitter;
    @JvmField
    public final int color;
    @JvmField
    public final int count;

    public LootShape(@NotNull float[] xs, @NotNull float[] zs, @NotNull float[] jitter, int color, int count) {
        Intrinsics.checkNotNullParameter((Object)xs, (String)"xs");
        Intrinsics.checkNotNullParameter((Object)zs, (String)"zs");
        Intrinsics.checkNotNullParameter((Object)jitter, (String)"jitter");
        this.xs = xs;
        this.zs = zs;
        this.jitter = jitter;
        this.color = color;
        this.count = count;
    }
}

