/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.fonts.core;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.fonts.core.GlyphAtlasPage;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u0000 02\u00020\u0001:\u00010BY\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0013\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0019\u0010\u0018J\u0010\u0010\u001a\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001a\u0010\u0018J\u0010\u0010\u001b\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001b\u0010\u0018J\u0010\u0010\u001c\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001c\u0010\u0018J\u0010\u0010\u001d\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001d\u0010\u0018J\u0010\u0010\u001e\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001e\u0010\u0018J\u0010\u0010\u001f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001f\u0010\u0018J\u0010\u0010 \u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b \u0010\u0018Jv\u0010!\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b!\u0010\"J\u001b\u0010$\u001a\u00020\u00102\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b$\u0010%J\u0011\u0010'\u001a\u00020&H\u00d6\u0081\u0004\u00a2\u0006\u0004\b'\u0010(J\u0011\u0010*\u001a\u00020)H\u00d6\u0081\u0004\u00a2\u0006\u0004\b*\u0010+R'\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0007z\f\b,\u0012\b\b-\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010.\u001a\u0004\b\u0003\u0010\u0016R%\u0010\u0005\u001a\u00020\u00048\u0007z\f\b,\u0012\b\b-\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010/\u001a\u0004\b\u0005\u0010\u0018R%\u0010\u0006\u001a\u00020\u00048\u0007z\f\b,\u0012\b\b-\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010/\u001a\u0004\b\u0006\u0010\u0018R%\u0010\u0007\u001a\u00020\u00048\u0007z\f\b,\u0012\b\b-\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010/\u001a\u0004\b\u0007\u0010\u0018R%\u0010\b\u001a\u00020\u00048\u0007z\f\b,\u0012\b\b-\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010/\u001a\u0004\b\b\u0010\u0018R%\u0010\t\u001a\u00020\u00048\u0007z\f\b,\u0012\b\b-\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010/\u001a\u0004\b\t\u0010\u0018R%\u0010\n\u001a\u00020\u00048\u0007z\f\b,\u0012\b\b-\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010/\u001a\u0004\b\n\u0010\u0018R%\u0010\u000b\u001a\u00020\u00048\u0007z\f\b,\u0012\b\b-\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010/\u001a\u0004\b\u000b\u0010\u0018R%\u0010\f\u001a\u00020\u00048\u0007z\f\b,\u0012\b\b-\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010/\u001a\u0004\b\f\u0010\u0018R%\u0010\r\u001a\u00020\u00048\u0007z\f\b,\u0012\b\b-\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u0010/\u001a\u0004\b\r\u0010\u0018\u00a8\u00061"}, d2={"Lrtx/kimiko/utils/render/fonts/core/GlyphInfo;", "", "Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;", "page", "", "u0", "v0", "u1", "v1", "xOffset", "yOffset", "width", "height", "advance", "<init>", "(Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;FFFFFFFFF)V", "", "drawable", "()Z", "withAdvance", "(F)Lrtx/kimiko/utils/render/fonts/core/GlyphInfo;", "component1", "()Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;", "component2", "()F", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;FFFFFFFFF)Lrtx/kimiko/utils/render/fonts/core/GlyphInfo;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;", "F", "Companion", "rtx.kimiko:kimiko"})
public final class GlyphInfo {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    private final GlyphAtlasPage page;
    private final float u0;
    private final float v0;
    private final float u1;
    private final float v1;
    private final float xOffset;
    private final float yOffset;
    private final float width;
    private final float height;
    private final float advance;

    public GlyphInfo(@Nullable GlyphAtlasPage page, float u0, float v0, float u1, float v1, float xOffset, float yOffset, float width, float height, float advance) {
        this.page = page;
        this.u0 = u0;
        this.v0 = v0;
        this.u1 = u1;
        this.v1 = v1;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.width = width;
        this.height = height;
        this.advance = advance;
    }

    @JvmName(name="page")
    @Nullable
    public final GlyphAtlasPage page() {
        return this.page;
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

    @JvmName(name="xOffset")
    public final float xOffset() {
        return this.xOffset;
    }

    @JvmName(name="yOffset")
    public final float yOffset() {
        return this.yOffset;
    }

    @JvmName(name="width")
    public final float width() {
        return this.width;
    }

    @JvmName(name="height")
    public final float height() {
        return this.height;
    }

    @JvmName(name="advance")
    public final float advance() {
        return this.advance;
    }

    public final boolean drawable() {
        return this.page != null && this.width > 0.0f && this.height > 0.0f;
    }

    @NotNull
    public final GlyphInfo withAdvance(float advance) {
        return new GlyphInfo(this.page, this.u0, this.v0, this.u1, this.v1, this.xOffset, this.yOffset, this.width, this.height, advance);
    }

    @Nullable
    public final GlyphAtlasPage component1() {
        return this.page;
    }

    public final float component2() {
        return this.u0;
    }

    public final float component3() {
        return this.v0;
    }

    public final float component4() {
        return this.u1;
    }

    public final float component5() {
        return this.v1;
    }

    public final float component6() {
        return this.xOffset;
    }

    public final float component7() {
        return this.yOffset;
    }

    public final float component8() {
        return this.width;
    }

    public final float component9() {
        return this.height;
    }

    public final float component10() {
        return this.advance;
    }

    @NotNull
    public final GlyphInfo copy(@Nullable GlyphAtlasPage page, float u0, float v0, float u1, float v1, float xOffset, float yOffset, float width, float height, float advance) {
        return new GlyphInfo(page, u0, v0, u1, v1, xOffset, yOffset, width, height, advance);
    }

    public static /* synthetic */ GlyphInfo copy$default(GlyphInfo glyphInfo, GlyphAtlasPage glyphAtlasPage, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int n, Object object) {
        if ((n & 1) != 0) {
            glyphAtlasPage = glyphInfo.page;
        }
        if ((n & 2) != 0) {
            f = glyphInfo.u0;
        }
        if ((n & 4) != 0) {
            f2 = glyphInfo.v0;
        }
        if ((n & 8) != 0) {
            f3 = glyphInfo.u1;
        }
        if ((n & 0x10) != 0) {
            f4 = glyphInfo.v1;
        }
        if ((n & 0x20) != 0) {
            f5 = glyphInfo.xOffset;
        }
        if ((n & 0x40) != 0) {
            f6 = glyphInfo.yOffset;
        }
        if ((n & 0x80) != 0) {
            f7 = glyphInfo.width;
        }
        if ((n & 0x100) != 0) {
            f8 = glyphInfo.height;
        }
        if ((n & 0x200) != 0) {
            f9 = glyphInfo.advance;
        }
        return glyphInfo.copy(glyphAtlasPage, f, f2, f3, f4, f5, f6, f7, f8, f9);
    }

    @NotNull
    public String toString() {
        return "GlyphInfo(page=" + this.page + ", u0=" + this.u0 + ", v0=" + this.v0 + ", u1=" + this.u1 + ", v1=" + this.v1 + ", xOffset=" + this.xOffset + ", yOffset=" + this.yOffset + ", width=" + this.width + ", height=" + this.height + ", advance=" + this.advance + ")";
    }

    public int hashCode() {
        int result = this.page == null ? 0 : this.page.hashCode();
        result = result * 31 + Float.hashCode(this.u0);
        result = result * 31 + Float.hashCode(this.v0);
        result = result * 31 + Float.hashCode(this.u1);
        result = result * 31 + Float.hashCode(this.v1);
        result = result * 31 + Float.hashCode(this.xOffset);
        result = result * 31 + Float.hashCode(this.yOffset);
        result = result * 31 + Float.hashCode(this.width);
        result = result * 31 + Float.hashCode(this.height);
        result = result * 31 + Float.hashCode(this.advance);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GlyphInfo)) {
            return false;
        }
        GlyphInfo glyphInfo = (GlyphInfo)other;
        if (!Intrinsics.areEqual((Object)this.page, (Object)glyphInfo.page)) {
            return false;
        }
        if (Float.compare(this.u0, glyphInfo.u0) != 0) {
            return false;
        }
        if (Float.compare(this.v0, glyphInfo.v0) != 0) {
            return false;
        }
        if (Float.compare(this.u1, glyphInfo.u1) != 0) {
            return false;
        }
        if (Float.compare(this.v1, glyphInfo.v1) != 0) {
            return false;
        }
        if (Float.compare(this.xOffset, glyphInfo.xOffset) != 0) {
            return false;
        }
        if (Float.compare(this.yOffset, glyphInfo.yOffset) != 0) {
            return false;
        }
        if (Float.compare(this.width, glyphInfo.width) != 0) {
            return false;
        }
        if (Float.compare(this.height, glyphInfo.height) != 0) {
            return false;
        }
        return Float.compare(this.advance, glyphInfo.advance) == 0;
    }

    @JvmStatic
    @NotNull
    public static final GlyphInfo empty(float advance) {
        return Companion.empty(advance);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/utils/render/fonts/core/GlyphInfo.Companion;", "", "<init>", "()V", "", "advance", "Lrtx/kimiko/utils/render/fonts/core/GlyphInfo;", "Lkotlin/jvm/JvmStatic;", "empty", "(F)Lrtx/kimiko/utils/render/fonts/core/GlyphInfo;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final GlyphInfo empty(float advance) {
            return new GlyphInfo(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, advance);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

