/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.font.TextRenderer
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.others;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J3\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0007J\u000f\u0010\u0012\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0007R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001aR\u0014\u0010\u001d\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u0018\u00a8\u0006\u001e"}, d2={"Lrtx/kimiko/utils/render/others/TabBadgeRenderer;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "extraWidth", "()I", "Lnet/minecraft/DrawContext;", "graphics", "Lnet/minecraft/TextRenderer;", "font", "x", "y", "", "drawBadge", "(Lnet/minecraft/DrawContext;Lnet/minecraft/TextRenderer;II)V", "textShift", "advance", "", "GLYPH", "Ljava/lang/String;", "", "SIZE", "F", "LEFT", "I", "RIGHT", "BADGE_X", "BADGE_Y", "rtx.kimiko:kimiko"})
public final class TabBadgeRenderer {
    @NotNull
    public static final TabBadgeRenderer INSTANCE = new TabBadgeRenderer();
    @NotNull
    private static final String GLYPH = "x";
    private static final float SIZE = 7.0f;
    private static final int LEFT = -7695373;
    private static final int RIGHT = -4938241;
    private static final int BADGE_X = 3;
    private static final float BADGE_Y = -0.5f;

    private TabBadgeRenderer() {
    }

    @JvmStatic
    public static final int extraWidth() {
        return Math.max(0, INSTANCE.textShift());
    }

    @JvmStatic
    public static final void drawBadge(@NotNull DrawContext graphics, @NotNull TextRenderer font, int x, int y) {
        float guiPerDesign;
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        Intrinsics.checkNotNullParameter((Object)font, (String)"font");
        float badgeX = x + 3;
        float badgeY = (float)y + -0.5f;
        float[] bounds = Fonts.KIMIKO.msdfBounds(GLYPH, 7.0f);
        if (bounds.length >= 4) {
            float visibleWidth = Math.max(0.0f, bounds[2] - bounds[0]);
            float visibleHeight = Math.max(0.0f, bounds[3] - bounds[1]);
            badgeX += ((float)INSTANCE.advance() - visibleWidth) * 0.5f - bounds[0];
            badgeY += ((float)font.fontHeight - visibleHeight) * 0.5f - bounds[1];
        }
        if ((guiPerDesign = Render2DCoordinateSpace.guiIndependentScale()) <= 0.0f) {
            return;
        }
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().scale(1.0f / guiPerDesign, 1.0f / guiPerDesign);
        Render2D.beginFrame(graphics);
        Fonts.KIMIKO.msdf(GLYPH, badgeX, badgeY, 7.0f, -7695373, -4938241, -4938241, -7695373);
        Render2D.flush();
        graphics.getMatrices().popMatrix();
    }

    private final int textShift() {
        return 3 + this.advance();
    }

    private final int advance() {
        float width = Fonts.KIMIKO.msdfWidth(GLYPH, 7.0f);
        return (int)Math.ceil(Math.max(0.0f, width));
    }
}

