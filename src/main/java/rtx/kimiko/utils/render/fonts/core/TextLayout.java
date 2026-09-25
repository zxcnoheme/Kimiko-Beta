/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.fonts.core;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.fonts.core.GlyphAtlasPage;
import rtx.kimiko.utils.render.fonts.core.LayoutGlyph;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000 \u00112\u00020\u0001:\u0002\u0012\u0011B%\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\u0004\b\b\u0010\tJ\u0013\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u00a2\u0006\u0004\b\u0004\u0010\nJ\r\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u000bJ\r\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\u000bJ\r\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u000fR\u0014\u0010\u0007\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u000fR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u0010\u00a8\u0006\u0013"}, d2={"Lrtx/kimiko/utils/render/fonts/core/TextLayout;", "", "", "Lrtx/kimiko/utils/render/fonts/core/TextLayout$Page;", "pages", "", "width", "height", "<init>", "(Ljava/util/List;FF)V", "()Ljava/util/List;", "()F", "", "empty", "()Z", "F", "Ljava/util/List;", "Companion", "Page", "rtx.kimiko:kimiko"})
public final class TextLayout {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final float width;
    private final float height;
    @NotNull
    private final List<Page> pages;
    @JvmField
    @NotNull
    public static final TextLayout EMPTY = new TextLayout(CollectionsKt.emptyList(), 0.0f, 0.0f);

    public TextLayout(@NotNull List<Page> pages, float width, float height) {
        Intrinsics.checkNotNullParameter(pages, (String)"pages");
        this.width = width;
        this.height = height;
        List list = List.copyOf((Collection)pages);
        Intrinsics.checkNotNullExpressionValue(list, (String)"copyOf(...)");
        this.pages = list;
    }

    @NotNull
    public final List<Page> pages() {
        return this.pages;
    }

    public final float width() {
        return this.width;
    }

    public final float height() {
        return this.height;
    }

    public final boolean empty() {
        return this.pages.isEmpty();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/utils/render/fonts/core/TextLayout.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/fonts/core/TextLayout;", "Lkotlin/jvm/JvmField;", "EMPTY", "Lrtx/kimiko/utils/render/fonts/core/TextLayout;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\u0004\b\u0007\u0010\bR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u000b\u001a\u0004\b\u0003\u0010\fR+\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0007z\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\r\u001a\u0004\b\u0006\u0010\u000e\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/utils/render/fonts/core/TextLayout$Page;", "", "Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;", "page", "", "Lrtx/kimiko/utils/render/fonts/core/LayoutGlyph;", "glyphs", "<init>", "(Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;Ljava/util/List;)V", "Lkotlin/jvm/JvmName;", "name", "Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;", "()Lrtx/kimiko/utils/render/fonts/core/GlyphAtlasPage;", "Ljava/util/List;", "()Ljava/util/List;", "rtx.kimiko:kimiko"})
    public static final class Page {
        @NotNull
        private final GlyphAtlasPage page;
        @NotNull
        private final List<LayoutGlyph> glyphs;

        public Page(@NotNull GlyphAtlasPage page, @NotNull List<LayoutGlyph> glyphs) {
            Intrinsics.checkNotNullParameter((Object)page, (String)"page");
            Intrinsics.checkNotNullParameter(glyphs, (String)"glyphs");
            this.page = page;
            List list = List.copyOf((Collection)glyphs);
            Intrinsics.checkNotNullExpressionValue(list, (String)"copyOf(...)");
            this.glyphs = list;
        }

        @JvmName(name="page")
        @NotNull
        public final GlyphAtlasPage page() {
            return this.page;
        }

        @JvmName(name="glyphs")
        @NotNull
        public final List<LayoutGlyph> glyphs() {
            return this.glyphs;
        }
    }
}

