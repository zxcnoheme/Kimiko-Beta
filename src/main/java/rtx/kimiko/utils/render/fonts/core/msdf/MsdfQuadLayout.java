/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.VertexConsumer
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.fonts.core.msdf;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.VertexConsumer;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfFont;
import rtx.kimiko.utils.render.fonts.core.msdf.MsdfGlyph;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003JK\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0007b\u0002\b\u0011\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0019\u0010\u0014\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015Jc\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0007b\u0002\b\u0011\u00a2\u0006\u0004\b\u0012\u0010\u001a\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfQuadLayout;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfFont;", "font", "", "text", "", "x", "y", "size", "", "color", "Lnet/minecraft/VertexConsumer;", "out", "", "Lkotlin/jvm/JvmStatic;", "layout", "(Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfFont;Ljava/lang/String;FFFILnet/minecraft/VertexConsumer;)V", "unitRange", "(Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfFont;)F", "colorTopLeft", "colorTopRight", "colorBottomRight", "colorBottomLeft", "(Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfFont;Ljava/lang/String;FFFIIIILnet/minecraft/VertexConsumer;)V", "rtx.kimiko:kimiko"})
public final class MsdfQuadLayout {
    @NotNull
    public static final MsdfQuadLayout INSTANCE = new MsdfQuadLayout();

    private MsdfQuadLayout() {
    }

    @JvmStatic
    public static final void layout(@NotNull MsdfFont font, @NotNull String text, float x, float y, float size, int color, @NotNull VertexConsumer out) {
        Intrinsics.checkNotNullParameter((Object)font, (String)"font");
        Intrinsics.checkNotNullParameter((Object)text, (String)"text");
        Intrinsics.checkNotNullParameter((Object)out, (String)"out");
        MsdfQuadLayout.layout(font, text, x, y, size, color, color, color, color, out);
    }

    private final float unitRange(MsdfFont font) {
        return font == null || font.atlasWidth() <= 0 ? 0.0f : 4.0f / (float)font.atlasWidth();
    }

    @JvmStatic
    public static final void layout(@NotNull MsdfFont font, @NotNull String text, float x, float y, float size, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, @NotNull VertexConsumer out) {
        Intrinsics.checkNotNullParameter((Object)font, (String)"font");
        Intrinsics.checkNotNullParameter((Object)text, (String)"text");
        Intrinsics.checkNotNullParameter((Object)out, (String)"out");
        float baseline = y + font.ascent(size);
        float range = INSTANCE.unitRange(font);
        float penX = x;
        int prev = -1;
        int i = 0;
        while (i < text.length()) {
            int cp = text.codePointAt(i);
            i += Character.charCount(cp);
            if (cp == 10) {
                penX = x;
                baseline += font.lineHeight(size);
                prev = -1;
                continue;
            }
            MsdfGlyph glyph = font.glyph(cp);
            if (prev != -1) {
                penX += font.kerning(prev, cp) * size;
            }
            if (glyph.drawable()) {
                float x0 = penX + glyph.planeLeft() * size;
                float x1 = penX + glyph.planeRight() * size;
                float y0 = baseline - glyph.planeTop() * size;
                float y1 = baseline - glyph.planeBottom() * size;
                out.vertex(x0, y0, 0.0f).texture(glyph.u0(), glyph.v0()).color(colorTopLeft).lineWidth(range);
                out.vertex(x0, y1, 0.0f).texture(glyph.u0(), glyph.v1()).color(colorBottomLeft).lineWidth(range);
                out.vertex(x1, y1, 0.0f).texture(glyph.u1(), glyph.v1()).color(colorBottomRight).lineWidth(range);
                out.vertex(x1, y0, 0.0f).texture(glyph.u1(), glyph.v0()).color(colorTopRight).lineWidth(range);
            }
            penX += glyph.advance() * size;
            prev = cp;
        }
    }
}

