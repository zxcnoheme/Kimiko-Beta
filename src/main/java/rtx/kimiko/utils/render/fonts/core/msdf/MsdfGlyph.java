/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.fonts.core.msdf;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u001c\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u0000 +2\u00020\u0001:\u0001+BW\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0013J\u0010\u0010\u0016\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0013J\u0010\u0010\u0017\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0013J\u0010\u0010\u0018\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0018\u0010\u0013J\u0010\u0010\u0019\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0019\u0010\u0013J\u0010\u0010\u001a\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001a\u0010\u0013J\u0010\u0010\u001b\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001b\u0010\u0013Jt\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001f\u001a\u00020\u00022\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001f\u0010 J\u0011\u0010\"\u001a\u00020!H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\"\u0010#J\u0011\u0010%\u001a\u00020$H\u00d6\u0081\u0004\u00a2\u0006\u0004\b%\u0010&R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b'\u0012\b\b(\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010)\u001a\u0004\b\u0003\u0010\u0011R%\u0010\u0005\u001a\u00020\u00048\u0007z\f\b'\u0012\b\b(\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010*\u001a\u0004\b\u0005\u0010\u0013R%\u0010\u0006\u001a\u00020\u00048\u0007z\f\b'\u0012\b\b(\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010*\u001a\u0004\b\u0006\u0010\u0013R%\u0010\u0007\u001a\u00020\u00048\u0007z\f\b'\u0012\b\b(\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010*\u001a\u0004\b\u0007\u0010\u0013R%\u0010\b\u001a\u00020\u00048\u0007z\f\b'\u0012\b\b(\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010*\u001a\u0004\b\b\u0010\u0013R%\u0010\t\u001a\u00020\u00048\u0007z\f\b'\u0012\b\b(\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010*\u001a\u0004\b\t\u0010\u0013R%\u0010\n\u001a\u00020\u00048\u0007z\f\b'\u0012\b\b(\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010*\u001a\u0004\b\n\u0010\u0013R%\u0010\u000b\u001a\u00020\u00048\u0007z\f\b'\u0012\b\b(\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010*\u001a\u0004\b\u000b\u0010\u0013R%\u0010\f\u001a\u00020\u00048\u0007z\f\b'\u0012\b\b(\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010*\u001a\u0004\b\f\u0010\u0013R%\u0010\r\u001a\u00020\u00048\u0007z\f\b'\u0012\b\b(\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u0010*\u001a\u0004\b\r\u0010\u0013\u00a8\u0006,"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfGlyph;", "", "", "drawable", "", "advance", "planeLeft", "planeTop", "planeRight", "planeBottom", "u0", "v0", "u1", "v1", "<init>", "(ZFFFFFFFFF)V", "component1", "()Z", "component2", "()F", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(ZFFFFFFFFF)Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfGlyph;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "Z", "F", "Companion", "rtx.kimiko:kimiko"})
public final class MsdfGlyph {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final boolean drawable;
    private final float advance;
    private final float planeLeft;
    private final float planeTop;
    private final float planeRight;
    private final float planeBottom;
    private final float u0;
    private final float v0;
    private final float u1;
    private final float v1;

    public MsdfGlyph(boolean drawable, float advance, float planeLeft, float planeTop, float planeRight, float planeBottom, float u0, float v0, float u1, float v1) {
        this.drawable = drawable;
        this.advance = advance;
        this.planeLeft = planeLeft;
        this.planeTop = planeTop;
        this.planeRight = planeRight;
        this.planeBottom = planeBottom;
        this.u0 = u0;
        this.v0 = v0;
        this.u1 = u1;
        this.v1 = v1;
    }

    @JvmName(name="drawable")
    public final boolean drawable() {
        return this.drawable;
    }

    @JvmName(name="advance")
    public final float advance() {
        return this.advance;
    }

    @JvmName(name="planeLeft")
    public final float planeLeft() {
        return this.planeLeft;
    }

    @JvmName(name="planeTop")
    public final float planeTop() {
        return this.planeTop;
    }

    @JvmName(name="planeRight")
    public final float planeRight() {
        return this.planeRight;
    }

    @JvmName(name="planeBottom")
    public final float planeBottom() {
        return this.planeBottom;
    }

    @JvmName(name="u0")
    public final float u0() {
        return this.u0;
    }

    @JvmName(name="v0")
    public final float v0() {
        return this.v0;
    }

    @JvmName(name="u1")
    public final float u1() {
        return this.u1;
    }

    @JvmName(name="v1")
    public final float v1() {
        return this.v1;
    }

    public final boolean component1() {
        return this.drawable;
    }

    public final float component2() {
        return this.advance;
    }

    public final float component3() {
        return this.planeLeft;
    }

    public final float component4() {
        return this.planeTop;
    }

    public final float component5() {
        return this.planeRight;
    }

    public final float component6() {
        return this.planeBottom;
    }

    public final float component7() {
        return this.u0;
    }

    public final float component8() {
        return this.v0;
    }

    public final float component9() {
        return this.u1;
    }

    public final float component10() {
        return this.v1;
    }

    @NotNull
    public final MsdfGlyph copy(boolean drawable, float advance, float planeLeft, float planeTop, float planeRight, float planeBottom, float u0, float v0, float u1, float v1) {
        return new MsdfGlyph(drawable, advance, planeLeft, planeTop, planeRight, planeBottom, u0, v0, u1, v1);
    }

    public static /* synthetic */ MsdfGlyph copy$default(MsdfGlyph msdfGlyph, boolean bl, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int n, Object object) {
        if ((n & 1) != 0) {
            bl = msdfGlyph.drawable;
        }
        if ((n & 2) != 0) {
            f = msdfGlyph.advance;
        }
        if ((n & 4) != 0) {
            f2 = msdfGlyph.planeLeft;
        }
        if ((n & 8) != 0) {
            f3 = msdfGlyph.planeTop;
        }
        if ((n & 0x10) != 0) {
            f4 = msdfGlyph.planeRight;
        }
        if ((n & 0x20) != 0) {
            f5 = msdfGlyph.planeBottom;
        }
        if ((n & 0x40) != 0) {
            f6 = msdfGlyph.u0;
        }
        if ((n & 0x80) != 0) {
            f7 = msdfGlyph.v0;
        }
        if ((n & 0x100) != 0) {
            f8 = msdfGlyph.u1;
        }
        if ((n & 0x200) != 0) {
            f9 = msdfGlyph.v1;
        }
        return msdfGlyph.copy(bl, f, f2, f3, f4, f5, f6, f7, f8, f9);
    }

    @NotNull
    public String toString() {
        return "MsdfGlyph(drawable=" + this.drawable + ", advance=" + this.advance + ", planeLeft=" + this.planeLeft + ", planeTop=" + this.planeTop + ", planeRight=" + this.planeRight + ", planeBottom=" + this.planeBottom + ", u0=" + this.u0 + ", v0=" + this.v0 + ", u1=" + this.u1 + ", v1=" + this.v1 + ")";
    }

    public int hashCode() {
        int result = Boolean.hashCode(this.drawable);
        result = result * 31 + Float.hashCode(this.advance);
        result = result * 31 + Float.hashCode(this.planeLeft);
        result = result * 31 + Float.hashCode(this.planeTop);
        result = result * 31 + Float.hashCode(this.planeRight);
        result = result * 31 + Float.hashCode(this.planeBottom);
        result = result * 31 + Float.hashCode(this.u0);
        result = result * 31 + Float.hashCode(this.v0);
        result = result * 31 + Float.hashCode(this.u1);
        result = result * 31 + Float.hashCode(this.v1);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MsdfGlyph)) {
            return false;
        }
        MsdfGlyph msdfGlyph = (MsdfGlyph)other;
        if (this.drawable != msdfGlyph.drawable) {
            return false;
        }
        if (Float.compare(this.advance, msdfGlyph.advance) != 0) {
            return false;
        }
        if (Float.compare(this.planeLeft, msdfGlyph.planeLeft) != 0) {
            return false;
        }
        if (Float.compare(this.planeTop, msdfGlyph.planeTop) != 0) {
            return false;
        }
        if (Float.compare(this.planeRight, msdfGlyph.planeRight) != 0) {
            return false;
        }
        if (Float.compare(this.planeBottom, msdfGlyph.planeBottom) != 0) {
            return false;
        }
        if (Float.compare(this.u0, msdfGlyph.u0) != 0) {
            return false;
        }
        if (Float.compare(this.v0, msdfGlyph.v0) != 0) {
            return false;
        }
        if (Float.compare(this.u1, msdfGlyph.u1) != 0) {
            return false;
        }
        return Float.compare(this.v1, msdfGlyph.v1) == 0;
    }

    @JvmStatic
    @NotNull
    public static final MsdfGlyph nonDrawable(float advance) {
        return Companion.nonDrawable(advance);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfGlyph.Companion;", "", "<init>", "()V", "", "advance", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfGlyph;", "Lkotlin/jvm/JvmStatic;", "nonDrawable", "(F)Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfGlyph;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final MsdfGlyph nonDrawable(float advance) {
            return new MsdfGlyph(false, advance, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

