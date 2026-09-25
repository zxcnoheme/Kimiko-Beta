/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.util.renderitem;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.util.renderitem.CachedItemQuad;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0080\b\u0018\u00002\u00020\u0001B%\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\u0004\b\b\u0010\tJ\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\rJ4\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005H\u00c6\u0001\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0012\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0015\u001a\u00020\u0014H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0011\u0010\u0018\u001a\u00020\u0017H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0018\u0010\u0019R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001a\u001a\u0004\b\u001b\u0010\u000bR\u0017\u0010\u0006\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001c\u001a\u0004\b\u001d\u0010\rR\u0017\u0010\u0007\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001c\u001a\u0004\b\u001e\u0010\r\u00a8\u0006\u001f"}, d2={"Lrtx/kimiko/utils/render/util/renderitem/CachedItemGeometry;", "", "", "Lrtx/kimiko/utils/render/util/renderitem/CachedItemQuad;", "quads", "", "animated", "specialRenderer", "<init>", "(Ljava/util/List;ZZ)V", "component1", "()Ljava/util/List;", "component2", "()Z", "component3", "copy", "(Ljava/util/List;ZZ)Lrtx/kimiko/utils/render/util/renderitem/CachedItemGeometry;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Ljava/util/List;", "getQuads", "Z", "getAnimated", "getSpecialRenderer", "rtx.kimiko:kimiko"})
public final class CachedItemGeometry {
    @NotNull
    private final List<CachedItemQuad> quads;
    private final boolean animated;
    private final boolean specialRenderer;

    public CachedItemGeometry(@NotNull List<CachedItemQuad> quads, boolean animated, boolean specialRenderer) {
        Intrinsics.checkNotNullParameter(quads, (String)"quads");
        this.quads = quads;
        this.animated = animated;
        this.specialRenderer = specialRenderer;
    }

    @NotNull
    public final List<CachedItemQuad> getQuads() {
        return this.quads;
    }

    public final boolean getAnimated() {
        return this.animated;
    }

    public final boolean getSpecialRenderer() {
        return this.specialRenderer;
    }

    @NotNull
    public final List<CachedItemQuad> component1() {
        return this.quads;
    }

    public final boolean component2() {
        return this.animated;
    }

    public final boolean component3() {
        return this.specialRenderer;
    }

    @NotNull
    public final CachedItemGeometry copy(@NotNull List<CachedItemQuad> quads, boolean animated, boolean specialRenderer) {
        Intrinsics.checkNotNullParameter(quads, (String)"quads");
        return new CachedItemGeometry(quads, animated, specialRenderer);
    }

    public static /* synthetic */ CachedItemGeometry copy$default(CachedItemGeometry cachedItemGeometry, List list, boolean bl, boolean bl2, int n, Object object) {
        if ((n & 1) != 0) {
            list = cachedItemGeometry.quads;
        }
        if ((n & 2) != 0) {
            bl = cachedItemGeometry.animated;
        }
        if ((n & 4) != 0) {
            bl2 = cachedItemGeometry.specialRenderer;
        }
        return cachedItemGeometry.copy(list, bl, bl2);
    }

    @NotNull
    public String toString() {
        return "CachedItemGeometry(quads=" + this.quads + ", animated=" + this.animated + ", specialRenderer=" + this.specialRenderer + ")";
    }

    public int hashCode() {
        int result = ((Object)this.quads).hashCode();
        result = result * 31 + Boolean.hashCode(this.animated);
        result = result * 31 + Boolean.hashCode(this.specialRenderer);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CachedItemGeometry)) {
            return false;
        }
        CachedItemGeometry cachedItemGeometry = (CachedItemGeometry)other;
        if (!Intrinsics.areEqual(this.quads, cachedItemGeometry.quads)) {
            return false;
        }
        if (this.animated != cachedItemGeometry.animated) {
            return false;
        }
        return this.specialRenderer == cachedItemGeometry.specialRenderer;
    }
}

