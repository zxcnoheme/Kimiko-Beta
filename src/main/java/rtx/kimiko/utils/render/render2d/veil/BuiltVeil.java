/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.veil;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aBI\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\f\u001a\u00020\b\u00a2\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0012\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0013R\u0019\u0010\u0004\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0012\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u0013R\u0019\u0010\u0005\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0012\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0013R\u0019\u0010\u0006\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0012\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0013R\u0019\u0010\t\u001a\u00020\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0012\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0014R\u0019\u0010\u0007\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0012\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0013R\u0019\u0010\f\u001a\u00020\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0012\u00a2\u0006\u0006\n\u0004\b\f\u0010\u0014R\u0019\u0010\u000b\u001a\u00020\n8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0012\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\u0015R\u0019\u0010\u0016\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0012\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0013R\u0019\u0010\u0017\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0012\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0013R\u0019\u0010\u0018\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0012\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0013R\u0019\u0010\u0019\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0012\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0013\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/utils/render/render2d/veil/BuiltVeil;", "", "", "panelX", "panelY", "panelW", "panelH", "panelRadius", "", "color", "", "rects", "rectCount", "<init>", "(FFFFFI[FI)V", "", "visible", "()Z", "Lkotlin/jvm/JvmField;", "F", "I", "[F", "boundsX", "boundsY", "boundsW", "boundsH", "Companion", "rtx.kimiko:kimiko"})
public final class BuiltVeil {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    public final float panelX;
    @JvmField
    public final float panelY;
    @JvmField
    public final float panelW;
    @JvmField
    public final float panelH;
    @JvmField
    public final int color;
    @JvmField
    public final float panelRadius;
    @JvmField
    public final int rectCount;
    @JvmField
    @NotNull
    public final float[] rects;
    @JvmField
    public final float boundsX;
    @JvmField
    public final float boundsY;
    @JvmField
    public final float boundsW;
    @JvmField
    public final float boundsH;
    public static final int MAX_RECTS = 40;
    public static final int FLOATS_PER_RECT = 6;

    public BuiltVeil(float panelX, float panelY, float panelW, float panelH, float panelRadius, int color, @Nullable float[] rects, int rectCount) {
        this.panelX = panelX;
        this.panelY = panelY;
        this.panelW = panelW;
        this.panelH = panelH;
        this.color = color;
        this.panelRadius = Math.max(0.0f, panelRadius);
        this.rectCount = Math.max(0, Math.min(rectCount, 40));
        float[] fArray = rects;
        if (rects == null) {
            fArray = new float[]{};
        }
        this.rects = fArray;
        float minX = this.panelX;
        float minY = this.panelY;
        float maxX = this.panelX + this.panelW;
        float maxY = this.panelY + this.panelH;
        int n = this.rectCount;
        for (int i = 0; i < n; ++i) {
            int o = i * 6;
            float cx = this.rects[o];
            float cy = this.rects[o + 1];
            float hw = this.rects[o + 2];
            float hh = this.rects[o + 3];
            float r = (float)Math.sqrt(hw * hw + hh * hh) + 1.0f;
            minX = Math.min(minX, cx - r);
            minY = Math.min(minY, cy - r);
            maxX = Math.max(maxX, cx + r);
            maxY = Math.max(maxY, cy + r);
        }
        this.boundsX = minX - 2.0f;
        this.boundsY = minY - 2.0f;
        this.boundsW = maxX - minX + 4.0f;
        this.boundsH = maxY - minY + 4.0f;
    }

    public final boolean visible() {
        return this.panelW > 0.0f && this.panelH > 0.0f && (this.color >>> 24 & 0xFF) > 0;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006\u00a8\u0006\b"}, d2={"Lrtx/kimiko/utils/render/render2d/veil/BuiltVeil.Companion;", "", "<init>", "()V", "", "MAX_RECTS", "I", "FLOATS_PER_RECT", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

