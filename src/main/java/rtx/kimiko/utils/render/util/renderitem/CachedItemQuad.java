/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.Identifier
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.util.renderitem;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b \n\u0002\u0010\u000e\n\u0002\b\u001a\b\u0080\b\u0018\u00002\u00020\u0001B\u00a1\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\u0006\u0010\u000e\u001a\u00020\u0004\u0012\u0006\u0010\u000f\u001a\u00020\u0004\u0012\u0006\u0010\u0010\u001a\u00020\u0004\u0012\u0006\u0010\u0011\u001a\u00020\u0004\u0012\u0006\u0010\u0012\u001a\u00020\u0004\u0012\u0006\u0010\u0013\u001a\u00020\u0004\u0012\u0006\u0010\u0014\u001a\u00020\u0004\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001f\u0010\u001eJ\u0010\u0010 \u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b \u0010\u001eJ\u0010\u0010!\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b!\u0010\u001eJ\u0010\u0010\"\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\"\u0010\u001eJ\u0010\u0010#\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b#\u0010\u001eJ\u0010\u0010$\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b$\u0010\u001eJ\u0010\u0010%\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b%\u0010\u001eJ\u0010\u0010&\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b&\u0010\u001eJ\u0010\u0010'\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b'\u0010\u001eJ\u0010\u0010(\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b(\u0010\u001eJ\u0010\u0010)\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b)\u0010\u001eJ\u0010\u0010*\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b*\u0010\u001eJ\u0010\u0010+\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b+\u0010\u001eJ\u0010\u0010,\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b,\u0010\u001eJ\u0010\u0010-\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b-\u0010\u001eJ\u0010\u0010.\u001a\u00020\u0015H\u00c6\u0003\u00a2\u0006\u0004\b.\u0010/J\u0010\u00100\u001a\u00020\u0017H\u00c6\u0003\u00a2\u0006\u0004\b0\u00101J\u00d0\u0001\u00102\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00042\b\b\u0002\u0010\u0011\u001a\u00020\u00042\b\b\u0002\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u00042\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u00c6\u0001\u00a2\u0006\u0004\b2\u00103J\u001b\u00105\u001a\u00020\u00172\b\u00104\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b5\u00106J\u0011\u00107\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\b7\u0010/J\u0011\u00109\u001a\u000208H\u00d6\u0081\u0004\u00a2\u0006\u0004\b9\u0010:R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010;\u001a\u0004\b<\u0010\u001cR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010=\u001a\u0004\b>\u0010\u001eR\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010=\u001a\u0004\b?\u0010\u001eR\u0017\u0010\u0007\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010=\u001a\u0004\b@\u0010\u001eR\u0017\u0010\b\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\b\u0010=\u001a\u0004\bA\u0010\u001eR\u0017\u0010\t\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\t\u0010=\u001a\u0004\bB\u0010\u001eR\u0017\u0010\n\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\n\u0010=\u001a\u0004\bC\u0010\u001eR\u0017\u0010\u000b\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010=\u001a\u0004\bD\u0010\u001eR\u0017\u0010\f\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\f\u0010=\u001a\u0004\bE\u0010\u001eR\u0017\u0010\r\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\r\u0010=\u001a\u0004\bF\u0010\u001eR\u0017\u0010\u000e\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u000e\u0010=\u001a\u0004\bG\u0010\u001eR\u0017\u0010\u000f\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u000f\u0010=\u001a\u0004\bH\u0010\u001eR\u0017\u0010\u0010\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0010\u0010=\u001a\u0004\bI\u0010\u001eR\u0017\u0010\u0011\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010=\u001a\u0004\bJ\u0010\u001eR\u0017\u0010\u0012\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0012\u0010=\u001a\u0004\bK\u0010\u001eR\u0017\u0010\u0013\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0013\u0010=\u001a\u0004\bL\u0010\u001eR\u0017\u0010\u0014\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0014\u0010=\u001a\u0004\bM\u0010\u001eR\u0017\u0010\u0016\u001a\u00020\u00158\u0006\u00a2\u0006\f\n\u0004\b\u0016\u0010N\u001a\u0004\bO\u0010/R\u0017\u0010\u0018\u001a\u00020\u00178\u0006\u00a2\u0006\f\n\u0004\b\u0018\u0010P\u001a\u0004\bQ\u00101\u00a8\u0006R"}, d2={"Lrtx/kimiko/utils/render/util/renderitem/CachedItemQuad;", "", "Lnet/minecraft/Identifier;", "atlas", "", "x0", "y0", "u0", "v0", "x1", "y1", "u1", "v1", "x2", "y2", "u2", "v2", "x3", "y3", "u3", "v3", "", "tint", "", "foil", "<init>", "(Lnet/minecraft/Identifier;FFFFFFFFFFFFFFFFIZ)V", "component1", "()Lnet/minecraft/Identifier;", "component2", "()F", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "()I", "component19", "()Z", "copy", "(Lnet/minecraft/Identifier;FFFFFFFFFFFFFFFFIZ)Lrtx/kimiko/utils/render/util/renderitem/CachedItemQuad;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/Identifier;", "getAtlas", "F", "getX0", "getY0", "getU0", "getV0", "getX1", "getY1", "getU1", "getV1", "getX2", "getY2", "getU2", "getV2", "getX3", "getY3", "getU3", "getV3", "I", "getTint", "Z", "getFoil", "rtx.kimiko:kimiko"})
public final class CachedItemQuad {
    @Nullable
    private final Identifier atlas;
    private final float x0;
    private final float y0;
    private final float u0;
    private final float v0;
    private final float x1;
    private final float y1;
    private final float u1;
    private final float v1;
    private final float x2;
    private final float y2;
    private final float u2;
    private final float v2;
    private final float x3;
    private final float y3;
    private final float u3;
    private final float v3;
    private final int tint;
    private final boolean foil;

    public CachedItemQuad(@Nullable Identifier atlas, float x0, float y0, float u0, float v0, float x1, float y1, float u1, float v1, float x2, float y2, float u2, float v2, float x3, float y3, float u3, float v3, int tint, boolean foil) {
        this.atlas = atlas;
        this.x0 = x0;
        this.y0 = y0;
        this.u0 = u0;
        this.v0 = v0;
        this.x1 = x1;
        this.y1 = y1;
        this.u1 = u1;
        this.v1 = v1;
        this.x2 = x2;
        this.y2 = y2;
        this.u2 = u2;
        this.v2 = v2;
        this.x3 = x3;
        this.y3 = y3;
        this.u3 = u3;
        this.v3 = v3;
        this.tint = tint;
        this.foil = foil;
    }

    @Nullable
    public final Identifier getAtlas() {
        return this.atlas;
    }

    public final float getX0() {
        return this.x0;
    }

    public final float getY0() {
        return this.y0;
    }

    public final float getU0() {
        return this.u0;
    }

    public final float getV0() {
        return this.v0;
    }

    public final float getX1() {
        return this.x1;
    }

    public final float getY1() {
        return this.y1;
    }

    public final float getU1() {
        return this.u1;
    }

    public final float getV1() {
        return this.v1;
    }

    public final float getX2() {
        return this.x2;
    }

    public final float getY2() {
        return this.y2;
    }

    public final float getU2() {
        return this.u2;
    }

    public final float getV2() {
        return this.v2;
    }

    public final float getX3() {
        return this.x3;
    }

    public final float getY3() {
        return this.y3;
    }

    public final float getU3() {
        return this.u3;
    }

    public final float getV3() {
        return this.v3;
    }

    public final int getTint() {
        return this.tint;
    }

    public final boolean getFoil() {
        return this.foil;
    }

    @Nullable
    public final Identifier component1() {
        return this.atlas;
    }

    public final float component2() {
        return this.x0;
    }

    public final float component3() {
        return this.y0;
    }

    public final float component4() {
        return this.u0;
    }

    public final float component5() {
        return this.v0;
    }

    public final float component6() {
        return this.x1;
    }

    public final float component7() {
        return this.y1;
    }

    public final float component8() {
        return this.u1;
    }

    public final float component9() {
        return this.v1;
    }

    public final float component10() {
        return this.x2;
    }

    public final float component11() {
        return this.y2;
    }

    public final float component12() {
        return this.u2;
    }

    public final float component13() {
        return this.v2;
    }

    public final float component14() {
        return this.x3;
    }

    public final float component15() {
        return this.y3;
    }

    public final float component16() {
        return this.u3;
    }

    public final float component17() {
        return this.v3;
    }

    public final int component18() {
        return this.tint;
    }

    public final boolean component19() {
        return this.foil;
    }

    @NotNull
    public final CachedItemQuad copy(@Nullable Identifier atlas, float x0, float y0, float u0, float v0, float x1, float y1, float u1, float v1, float x2, float y2, float u2, float v2, float x3, float y3, float u3, float v3, int tint, boolean foil) {
        return new CachedItemQuad(atlas, x0, y0, u0, v0, x1, y1, u1, v1, x2, y2, u2, v2, x3, y3, u3, v3, tint, foil);
    }

    public static /* synthetic */ CachedItemQuad copy$default(CachedItemQuad cachedItemQuad, Identifier identifier2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, int n, boolean bl, int n2, Object object) {
        if ((n2 & 1) != 0) {
            identifier2 = cachedItemQuad.atlas;
        }
        if ((n2 & 2) != 0) {
            f = cachedItemQuad.x0;
        }
        if ((n2 & 4) != 0) {
            f2 = cachedItemQuad.y0;
        }
        if ((n2 & 8) != 0) {
            f3 = cachedItemQuad.u0;
        }
        if ((n2 & 0x10) != 0) {
            f4 = cachedItemQuad.v0;
        }
        if ((n2 & 0x20) != 0) {
            f5 = cachedItemQuad.x1;
        }
        if ((n2 & 0x40) != 0) {
            f6 = cachedItemQuad.y1;
        }
        if ((n2 & 0x80) != 0) {
            f7 = cachedItemQuad.u1;
        }
        if ((n2 & 0x100) != 0) {
            f8 = cachedItemQuad.v1;
        }
        if ((n2 & 0x200) != 0) {
            f9 = cachedItemQuad.x2;
        }
        if ((n2 & 0x400) != 0) {
            f10 = cachedItemQuad.y2;
        }
        if ((n2 & 0x800) != 0) {
            f11 = cachedItemQuad.u2;
        }
        if ((n2 & 0x1000) != 0) {
            f12 = cachedItemQuad.v2;
        }
        if ((n2 & 0x2000) != 0) {
            f13 = cachedItemQuad.x3;
        }
        if ((n2 & 0x4000) != 0) {
            f14 = cachedItemQuad.y3;
        }
        if ((n2 & 0x8000) != 0) {
            f15 = cachedItemQuad.u3;
        }
        if ((n2 & 0x10000) != 0) {
            f16 = cachedItemQuad.v3;
        }
        if ((n2 & 0x20000) != 0) {
            n = cachedItemQuad.tint;
        }
        if ((n2 & 0x40000) != 0) {
            bl = cachedItemQuad.foil;
        }
        return cachedItemQuad.copy(identifier2, f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, n, bl);
    }

    @NotNull
    public String toString() {
        return "CachedItemQuad(atlas=" + this.atlas + ", x0=" + this.x0 + ", y0=" + this.y0 + ", u0=" + this.u0 + ", v0=" + this.v0 + ", x1=" + this.x1 + ", y1=" + this.y1 + ", u1=" + this.u1 + ", v1=" + this.v1 + ", x2=" + this.x2 + ", y2=" + this.y2 + ", u2=" + this.u2 + ", v2=" + this.v2 + ", x3=" + this.x3 + ", y3=" + this.y3 + ", u3=" + this.u3 + ", v3=" + this.v3 + ", tint=" + this.tint + ", foil=" + this.foil + ")";
    }

    public int hashCode() {
        int result = this.atlas == null ? 0 : this.atlas.hashCode();
        result = result * 31 + Float.hashCode(this.x0);
        result = result * 31 + Float.hashCode(this.y0);
        result = result * 31 + Float.hashCode(this.u0);
        result = result * 31 + Float.hashCode(this.v0);
        result = result * 31 + Float.hashCode(this.x1);
        result = result * 31 + Float.hashCode(this.y1);
        result = result * 31 + Float.hashCode(this.u1);
        result = result * 31 + Float.hashCode(this.v1);
        result = result * 31 + Float.hashCode(this.x2);
        result = result * 31 + Float.hashCode(this.y2);
        result = result * 31 + Float.hashCode(this.u2);
        result = result * 31 + Float.hashCode(this.v2);
        result = result * 31 + Float.hashCode(this.x3);
        result = result * 31 + Float.hashCode(this.y3);
        result = result * 31 + Float.hashCode(this.u3);
        result = result * 31 + Float.hashCode(this.v3);
        result = result * 31 + Integer.hashCode(this.tint);
        result = result * 31 + Boolean.hashCode(this.foil);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CachedItemQuad)) {
            return false;
        }
        CachedItemQuad cachedItemQuad = (CachedItemQuad)other;
        if (!Intrinsics.areEqual((Object)this.atlas, (Object)cachedItemQuad.atlas)) {
            return false;
        }
        if (Float.compare(this.x0, cachedItemQuad.x0) != 0) {
            return false;
        }
        if (Float.compare(this.y0, cachedItemQuad.y0) != 0) {
            return false;
        }
        if (Float.compare(this.u0, cachedItemQuad.u0) != 0) {
            return false;
        }
        if (Float.compare(this.v0, cachedItemQuad.v0) != 0) {
            return false;
        }
        if (Float.compare(this.x1, cachedItemQuad.x1) != 0) {
            return false;
        }
        if (Float.compare(this.y1, cachedItemQuad.y1) != 0) {
            return false;
        }
        if (Float.compare(this.u1, cachedItemQuad.u1) != 0) {
            return false;
        }
        if (Float.compare(this.v1, cachedItemQuad.v1) != 0) {
            return false;
        }
        if (Float.compare(this.x2, cachedItemQuad.x2) != 0) {
            return false;
        }
        if (Float.compare(this.y2, cachedItemQuad.y2) != 0) {
            return false;
        }
        if (Float.compare(this.u2, cachedItemQuad.u2) != 0) {
            return false;
        }
        if (Float.compare(this.v2, cachedItemQuad.v2) != 0) {
            return false;
        }
        if (Float.compare(this.x3, cachedItemQuad.x3) != 0) {
            return false;
        }
        if (Float.compare(this.y3, cachedItemQuad.y3) != 0) {
            return false;
        }
        if (Float.compare(this.u3, cachedItemQuad.u3) != 0) {
            return false;
        }
        if (Float.compare(this.v3, cachedItemQuad.v3) != 0) {
            return false;
        }
        if (this.tint != cachedItemQuad.tint) {
            return false;
        }
        return this.foil == cachedItemQuad.foil;
    }
}

