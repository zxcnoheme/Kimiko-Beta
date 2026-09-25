/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.fonts.core;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.fonts.core.GlyphAtlasPage;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\u0004\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0012J\u0010\u0010\u0015\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0012J\u0010\u0010\u0016\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0012J\u0010\u0010\u0017\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0012J\u0010\u0010\u0018\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0018\u0010\u0012J\u0010\u0010\u0019\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0019\u0010\u0012Jj\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001b\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0011\u0010!\u001a\u00020 H\u00d6\u0081\u0004\u00a2\u0006\u0004\b!\u0010\"J\u0011\u0010$\u001a\u00020#H\u00d6\u0081\u0004\u00a2\u0006\u0004\b$\u0010%R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b&\u0012\b\b'\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010(\u001a\u0004\b\u0003\u0010\u0010R%\u0010\u0005\u001a\u00020\u00048\u0007z\f\b&\u0012\b\b'\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010)\u001a\u0004\b\u0005\u0010\u0012R%\u0010\u0006\u001a\u00020\u00048\u0007z\f\b&\u0012\b\b'\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010)\u001a\u0004\b\u0006\u0010\u0012R%\u0010\u0007\u001a\u00020\u00048\u0007z\f\b&\u0012\b\b'\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010)\u001a\u0004\b\u0007\u0010\u0012R%\u0010\b\u001a\u00020\u00048\u0007z\f\b&\u0012\b\b'\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010)\u001a\u0004\b\b\u0010\u0012R%\u0010\t\u001a\u00020\u00048\u0007z\f\b&\u0012\b\b'\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010)\u001a\u0004\b\t\u0010\u0012R%\u0010\n\u001a\u00020\u00048\u0007z\f\b&\u0012\b\b'\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010)\u001a\u0004\b\n\u0010\u0012R%\u0010\u000b\u001a\u00020\u00048\u0007z\f\b&\u0012\b\b'\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010)\u001a\u0004\b\u000b\u0010\u0012R%\u0010\f\u001a\u00020\u00048\u0007z\f\b&\u0012\b\b'\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010)\u001a\u0004\b\f\u0010\u0012\u00a8\u0006*"}, d2={"Lrtx/kimiko/utils/render/fonts/core/LayoutGlyph;", "", "Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;", "page", "", "x0", "y0", "x1", "y1", "u0", "v0", "u1", "v1", "<init>", "(Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;FFFFFFFF)V", "component1", "()Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;", "component2", "()F", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;FFFFFFFF)Lrtx/kimiko/utils/render/fonts/core/LayoutGlyph;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;", "F", "rtx.kimiko:kimiko"})
public final class LayoutGlyph {
    @NotNull
    private final GlyphAtlasPage page;
    private final float x0;
    private final float y0;
    private final float x1;
    private final float y1;
    private final float u0;
    private final float v0;
    private final float u1;
    private final float v1;

    public LayoutGlyph(@NotNull GlyphAtlasPage page, float x0, float y0, float x1, float y1, float u0, float v0, float u1, float v1) {
        Intrinsics.checkNotNullParameter((Object)page, (String)"page");
        this.page = page;
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.u0 = u0;
        this.v0 = v0;
        this.u1 = u1;
        this.v1 = v1;
    }

    @JvmName(name="page")
    @NotNull
    public final GlyphAtlasPage page() {
        return this.page;
    }

    @JvmName(name="x0")
    public final float x0() {
        return this.x0;
    }

    @JvmName(name="y0")
    public final float y0() {
        return this.y0;
    }

    @JvmName(name="x1")
    public final float x1() {
        return this.x1;
    }

    @JvmName(name="y1")
    public final float y1() {
        return this.y1;
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

    @NotNull
    public final GlyphAtlasPage component1() {
        return this.page;
    }

    public final float component2() {
        return this.x0;
    }

    public final float component3() {
        return this.y0;
    }

    public final float component4() {
        return this.x1;
    }

    public final float component5() {
        return this.y1;
    }

    public final float component6() {
        return this.u0;
    }

    public final float component7() {
        return this.v0;
    }

    public final float component8() {
        return this.u1;
    }

    public final float component9() {
        return this.v1;
    }

    @NotNull
    public final LayoutGlyph copy(@NotNull GlyphAtlasPage page, float x0, float y0, float x1, float y1, float u0, float v0, float u1, float v1) {
        Intrinsics.checkNotNullParameter((Object)page, (String)"page");
        return new LayoutGlyph(page, x0, y0, x1, y1, u0, v0, u1, v1);
    }

    public static /* synthetic */ LayoutGlyph copy$default(LayoutGlyph layoutGlyph, GlyphAtlasPage glyphAtlasPage, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n, Object object) {
        if ((n & 1) != 0) {
            glyphAtlasPage = layoutGlyph.page;
        }
        if ((n & 2) != 0) {
            f = layoutGlyph.x0;
        }
        if ((n & 4) != 0) {
            f2 = layoutGlyph.y0;
        }
        if ((n & 8) != 0) {
            f3 = layoutGlyph.x1;
        }
        if ((n & 0x10) != 0) {
            f4 = layoutGlyph.y1;
        }
        if ((n & 0x20) != 0) {
            f5 = layoutGlyph.u0;
        }
        if ((n & 0x40) != 0) {
            f6 = layoutGlyph.v0;
        }
        if ((n & 0x80) != 0) {
            f7 = layoutGlyph.u1;
        }
        if ((n & 0x100) != 0) {
            f8 = layoutGlyph.v1;
        }
        return layoutGlyph.copy(glyphAtlasPage, f, f2, f3, f4, f5, f6, f7, f8);
    }

    @NotNull
    public String toString() {
        return "LayoutGlyph(page=" + this.page + ", x0=" + this.x0 + ", y0=" + this.y0 + ", x1=" + this.x1 + ", y1=" + this.y1 + ", u0=" + this.u0 + ", v0=" + this.v0 + ", u1=" + this.u1 + ", v1=" + this.v1 + ")";
    }

    public int hashCode() {
        int result = this.page.hashCode();
        result = result * 31 + Float.hashCode(this.x0);
        result = result * 31 + Float.hashCode(this.y0);
        result = result * 31 + Float.hashCode(this.x1);
        result = result * 31 + Float.hashCode(this.y1);
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
        if (!(other instanceof LayoutGlyph)) {
            return false;
        }
        LayoutGlyph layoutGlyph = (LayoutGlyph)other;
        if (!Intrinsics.areEqual((Object)this.page, (Object)layoutGlyph.page)) {
            return false;
        }
        if (Float.compare(this.x0, layoutGlyph.x0) != 0) {
            return false;
        }
        if (Float.compare(this.y0, layoutGlyph.y0) != 0) {
            return false;
        }
        if (Float.compare(this.x1, layoutGlyph.x1) != 0) {
            return false;
        }
        if (Float.compare(this.y1, layoutGlyph.y1) != 0) {
            return false;
        }
        if (Float.compare(this.u0, layoutGlyph.u0) != 0) {
            return false;
        }
        if (Float.compare(this.v0, layoutGlyph.v0) != 0) {
            return false;
        }
        if (Float.compare(this.u1, layoutGlyph.u1) != 0) {
            return false;
        }
        return Float.compare(this.v1, layoutGlyph.v1) == 0;
    }
}

