/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.AbstractTexture
 *  net.minecraft.client.texture.TextureManager
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.fonts.core.msdf;

import com.mojang.blaze3d.textures.GpuTextureView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfFont;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfFonts;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfGlyph;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002\u0015\u0016B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J/\u0010\f\u001a\u00020\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\f\u0010\rJ/\u0010\u000e\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u000b\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextGeometry;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/fonts/Fonts;", "fontName", "", "text", "", "size", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextGeometry$Layout;", "Lkotlin/jvm/JvmStatic;", "layout", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;F)Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextGeometry$Layout;", "width", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;F)F", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfFont;", "font", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "atlasView", "(Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfFont;)Lcom/mojang/blaze3d/textures/GpuTextureView;", "Quad", "Layout", "rtx.kimiko:kimiko"})
public final class MsdfTextGeometry {
    @NotNull
    public static final MsdfTextGeometry INSTANCE = new MsdfTextGeometry();

    private MsdfTextGeometry() {
    }

    @JvmStatic
    @NotNull
    public static final Layout layout(@Nullable Fonts fontName, @Nullable String text, float size) {
        CharSequence charSequence;
        MsdfFont font = MsdfFonts.get(fontName);
        if (font == null || (charSequence = (CharSequence)text) == null || charSequence.length() == 0 || size <= 0.0f) {
            return Layout.EMPTY;
        }
        GpuTextureView gpuTextureView = INSTANCE.atlasView(font);
        if (gpuTextureView == null) {
            return Layout.EMPTY;
        }
        GpuTextureView atlas = gpuTextureView;
        ArrayList<Quad> quads = new ArrayList<Quad>(text.length());
        float baseline = font.ascent(size);
        float penX = 0.0f;
        int prev = -1;
        int index = 0;
        while (index < text.length()) {
            int codePoint = text.codePointAt(index);
            index += Character.charCount(codePoint);
            if (codePoint == 10) continue;
            MsdfGlyph glyph = font.glyph(codePoint);
            if (prev != -1) {
                penX += font.kerning(prev, codePoint) * size;
            }
            if (glyph.drawable()) {
                quads.add(new Quad(penX + glyph.planeLeft() * size, baseline - glyph.planeTop() * size, penX + glyph.planeRight() * size, baseline - glyph.planeBottom() * size, glyph.u0(), glyph.v0(), glyph.u1(), glyph.v1(), codePoint));
            }
            penX += glyph.advance() * size;
            prev = codePoint;
        }
        return new Layout((List<Quad>)quads, font.width(text, size), font.lineHeight(size), atlas, font.atlasWidth(), font.atlasHeight());
    }

    @JvmStatic
    public static final float width(@Nullable Fonts fontName, @Nullable String text, float size) {
        CharSequence charSequence;
        MsdfFont font = MsdfFonts.get(fontName);
        return font == null || (charSequence = (CharSequence)text) == null || charSequence.length() == 0 ? 0.0f : font.width(text, size);
    }

    private final GpuTextureView atlasView(MsdfFont font) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        if (minecraftClient2 == null) {
            return null;
        }
        MinecraftClient minecraft = minecraftClient2;
        TextureManager textureManager2 = minecraft.getTextureManager();
        if (textureManager2 == null) {
            return null;
        }
        TextureManager manager = textureManager2;
        AbstractTexture abstractTexture3 = manager.getTexture(font.atlasTexture());
        Intrinsics.checkNotNullExpressionValue((Object)abstractTexture3, (String)"getTexture(...)");
        AbstractTexture texture = abstractTexture3;
        return texture.getGlTextureView();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB?\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u00a2\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0011R%\u0010\u0006\u001a\u00020\u00058\u0007z\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0014\u001a\u0004\b\u0006\u0010\u0015R%\u0010\u0007\u001a\u00020\u00058\u0007z\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0014\u001a\u0004\b\u0007\u0010\u0015R'\u0010\t\u001a\u0004\u0018\u00010\b8\u0007z\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010\u0016\u001a\u0004\b\t\u0010\u0017R%\u0010\u000b\u001a\u00020\n8\u0007z\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010\u0018\u001a\u0004\b\u000b\u0010\u0019R%\u0010\f\u001a\u00020\n8\u0007z\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010\u0018\u001a\u0004\b\f\u0010\u0019R+\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0007z\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001a\u001a\u0004\b\u0004\u0010\u001b\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextGeometry$Layout;", "", "", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextGeometry$Quad;", "quads", "", "width", "height", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "atlas", "", "atlasWidth", "atlasHeight", "<init>", "(Ljava/util/List;FFLcom/mojang/blaze3d/textures/GpuTextureView;II)V", "", "empty", "()Z", "Lkotlin/jvm/JvmName;", "name", "F", "()F", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "()Lcom/mojang/blaze3d/textures/GpuTextureView;", "I", "()I", "Ljava/util/List;", "()Ljava/util/List;", "Companion", "rtx.kimiko:kimiko"})
    public static final class Layout {
        @NotNull
        public static final Companion Companion = new Companion(null);
        private final float width;
        private final float height;
        @Nullable
        private final GpuTextureView atlas;
        private final int atlasWidth;
        private final int atlasHeight;
        @NotNull
        private final List<Quad> quads;
        @JvmField
        @NotNull
        public static final Layout EMPTY = new Layout(CollectionsKt.emptyList(), 0.0f, 0.0f, null, 1, 1);

        public Layout(@NotNull List<Quad> quads, float width, float height, @Nullable GpuTextureView atlas, int atlasWidth, int atlasHeight) {
            Intrinsics.checkNotNullParameter(quads, (String)"quads");
            this.width = width;
            this.height = height;
            this.atlas = atlas;
            this.atlasWidth = atlasWidth;
            this.atlasHeight = atlasHeight;
            List list = List.copyOf((Collection)quads);
            Intrinsics.checkNotNullExpressionValue(list, (String)"copyOf(...)");
            this.quads = list;
        }

        @JvmName(name="width")
        public final float width() {
            return this.width;
        }

        @JvmName(name="height")
        public final float height() {
            return this.height;
        }

        @JvmName(name="atlas")
        @Nullable
        public final GpuTextureView atlas() {
            return this.atlas;
        }

        @JvmName(name="atlasWidth")
        public final int atlasWidth() {
            return this.atlasWidth;
        }

        @JvmName(name="atlasHeight")
        public final int atlasHeight() {
            return this.atlasHeight;
        }

        @JvmName(name="quads")
        @NotNull
        public final List<Quad> quads() {
            return this.quads;
        }

        public final boolean empty() {
            return this.quads.isEmpty() || this.atlas == null || this.height <= 0.0f;
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextGeometry$Layout.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextGeometry$Layout;", "Lkotlin/jvm/JvmField;", "EMPTY", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextGeometry$Layout;", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0010J\u0010\u0010\u0013\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0010J\u0010\u0010\u0014\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0010J\u0010\u0010\u0015\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0010J\u0010\u0010\u0016\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0010J\u0010\u0010\u0017\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0010J\u0010\u0010\u0018\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b\u0018\u0010\u0019Jj\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u000bH\u00c6\u0001\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001b\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0011\u0010 \u001a\u00020\u000bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b \u0010\u0019J\u0011\u0010\"\u001a\u00020!H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\"\u0010#R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b$\u0012\b\b%\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010&\u001a\u0004\b\u0003\u0010\u0010R%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b$\u0012\b\b%\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010&\u001a\u0004\b\u0004\u0010\u0010R%\u0010\u0005\u001a\u00020\u00028\u0007z\f\b$\u0012\b\b%\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010&\u001a\u0004\b\u0005\u0010\u0010R%\u0010\u0006\u001a\u00020\u00028\u0007z\f\b$\u0012\b\b%\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010&\u001a\u0004\b\u0006\u0010\u0010R%\u0010\u0007\u001a\u00020\u00028\u0007z\f\b$\u0012\b\b%\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010&\u001a\u0004\b\u0007\u0010\u0010R%\u0010\b\u001a\u00020\u00028\u0007z\f\b$\u0012\b\b%\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010&\u001a\u0004\b\b\u0010\u0010R%\u0010\t\u001a\u00020\u00028\u0007z\f\b$\u0012\b\b%\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010&\u001a\u0004\b\t\u0010\u0010R%\u0010\n\u001a\u00020\u00028\u0007z\f\b$\u0012\b\b%\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010&\u001a\u0004\b\n\u0010\u0010R%\u0010\f\u001a\u00020\u000b8\u0007z\f\b$\u0012\b\b%\u0012\u0004\b\b(\f\u00a2\u0006\f\n\u0004\b\f\u0010'\u001a\u0004\b\f\u0010\u0019\u00a8\u0006("}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextGeometry$Quad;", "", "", "x0", "y0", "x1", "y1", "u0", "v0", "u1", "v1", "", "codePoint", "<init>", "(FFFFFFFFI)V", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "()I", "copy", "(FFFFFFFFI)Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfTextGeometry$Quad;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "F", "I", "rtx.kimiko:kimiko"})
    public static final class Quad {
        private final float x0;
        private final float y0;
        private final float x1;
        private final float y1;
        private final float u0;
        private final float v0;
        private final float u1;
        private final float v1;
        private final int codePoint;

        public Quad(float x0, float y0, float x1, float y1, float u0, float v0, float u1, float v1, int codePoint) {
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x1;
            this.y1 = y1;
            this.u0 = u0;
            this.v0 = v0;
            this.u1 = u1;
            this.v1 = v1;
            this.codePoint = codePoint;
        }

        public /* synthetic */ Quad(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n, int n2, DefaultConstructorMarker defaultConstructorMarker) {
            this(f, f2, f3, f4, f5, f6, f7, f8, ((n2 & 0x100) != 0 ? 0 : n));
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

        @JvmName(name="codePoint")
        public final int codePoint() {
            return this.codePoint;
        }

        public final float component1() {
            return this.x0;
        }

        public final float component2() {
            return this.y0;
        }

        public final float component3() {
            return this.x1;
        }

        public final float component4() {
            return this.y1;
        }

        public final float component5() {
            return this.u0;
        }

        public final float component6() {
            return this.v0;
        }

        public final float component7() {
            return this.u1;
        }

        public final float component8() {
            return this.v1;
        }

        public final int component9() {
            return this.codePoint;
        }

        @NotNull
        public final Quad copy(float x0, float y0, float x1, float y1, float u0, float v0, float u1, float v1, int codePoint) {
            return new Quad(x0, y0, x1, y1, u0, v0, u1, v1, codePoint);
        }

        public static /* synthetic */ Quad copy$default(Quad quad, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n, int n2, Object object) {
            if ((n2 & 1) != 0) {
                f = quad.x0;
            }
            if ((n2 & 2) != 0) {
                f2 = quad.y0;
            }
            if ((n2 & 4) != 0) {
                f3 = quad.x1;
            }
            if ((n2 & 8) != 0) {
                f4 = quad.y1;
            }
            if ((n2 & 0x10) != 0) {
                f5 = quad.u0;
            }
            if ((n2 & 0x20) != 0) {
                f6 = quad.v0;
            }
            if ((n2 & 0x40) != 0) {
                f7 = quad.u1;
            }
            if ((n2 & 0x80) != 0) {
                f8 = quad.v1;
            }
            if ((n2 & 0x100) != 0) {
                n = quad.codePoint;
            }
            return quad.copy(f, f2, f3, f4, f5, f6, f7, f8, n);
        }

        @NotNull
        public String toString() {
            return "Quad(x0=" + this.x0 + ", y0=" + this.y0 + ", x1=" + this.x1 + ", y1=" + this.y1 + ", u0=" + this.u0 + ", v0=" + this.v0 + ", u1=" + this.u1 + ", v1=" + this.v1 + ", codePoint=" + this.codePoint + ")";
        }

        public int hashCode() {
            int result = Float.hashCode(this.x0);
            result = result * 31 + Float.hashCode(this.y0);
            result = result * 31 + Float.hashCode(this.x1);
            result = result * 31 + Float.hashCode(this.y1);
            result = result * 31 + Float.hashCode(this.u0);
            result = result * 31 + Float.hashCode(this.v0);
            result = result * 31 + Float.hashCode(this.u1);
            result = result * 31 + Float.hashCode(this.v1);
            result = result * 31 + Integer.hashCode(this.codePoint);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Quad)) {
                return false;
            }
            Quad quad = (Quad)other;
            if (Float.compare(this.x0, quad.x0) != 0) {
                return false;
            }
            if (Float.compare(this.y0, quad.y0) != 0) {
                return false;
            }
            if (Float.compare(this.x1, quad.x1) != 0) {
                return false;
            }
            if (Float.compare(this.y1, quad.y1) != 0) {
                return false;
            }
            if (Float.compare(this.u0, quad.u0) != 0) {
                return false;
            }
            if (Float.compare(this.v0, quad.v0) != 0) {
                return false;
            }
            if (Float.compare(this.u1, quad.u1) != 0) {
                return false;
            }
            if (Float.compare(this.v1, quad.v1) != 0) {
                return false;
            }
            return this.codePoint == quad.codePoint;
        }
    }
}

