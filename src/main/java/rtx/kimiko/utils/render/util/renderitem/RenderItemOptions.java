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
package rtx.kimiko.utils.render.util.renderitem;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.util.renderitem.RenderItemGlintMode;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000  2\u00020\u0001:\u0001 B9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u000eJ\u0015\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0013\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0015\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\b\u00a2\u0006\u0004\b\u0015\u0010\u0014J\u0015\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000b\u0010\u000eR%\u0010\t\u001a\u00020\b8\u0007z\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010\u0018\u001a\u0004\b\t\u0010\u0019R%\u0010\n\u001a\u00020\b8\u0007z\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010\u0018\u001a\u0004\b\n\u0010\u0019R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u0003\u0010\u001bR%\u0010\u0005\u001a\u00020\u00048\u0007z\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b\u0005\u0010\u001dR%\u0010\u0007\u001a\u00020\u00068\u0007z\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b\u0007\u0010\u001fR%\u0010\u000b\u001a\u00020\u00028\u0007z\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010\u001a\u001a\u0004\b\u000b\u0010\u001b\u00a8\u0006!"}, d2={"Lrtx/kimiko/utils/render/util/renderitem/RenderItemOptions;", "", "", "alpha", "", "color", "Lrtx/kimiko/utils/render/util/renderitem/RenderItemGlintMode;", "glintMode", "", "showCount", "showDurability", "glintStrength", "<init>", "(FILrtx/kimiko/utils/render/util/renderitem/RenderItemGlintMode;ZZF)V", "(F)Lrtx/kimiko/utils/render/util/renderitem/RenderItemOptions;", "(I)Lrtx/kimiko/utils/render/util/renderitem/RenderItemOptions;", "mode", "glint", "(Lrtx/kimiko/utils/render/util/renderitem/RenderItemGlintMode;)Lrtx/kimiko/utils/render/util/renderitem/RenderItemOptions;", "count", "(Z)Lrtx/kimiko/utils/render/util/renderitem/RenderItemOptions;", "durability", "Lkotlin/jvm/JvmName;", "name", "Z", "()Z", "F", "()F", "I", "()I", "Lrtx/kimiko/utils/render/util/renderitem/RenderItemGlintMode;", "()Lrtx/kimiko/utils/render/util/renderitem/RenderItemGlintMode;", "Companion", "rtx.kimiko:kimiko"})
public final class RenderItemOptions {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final boolean showCount;
    private final boolean showDurability;
    private final float alpha;
    private final int color;
    @NotNull
    private final RenderItemGlintMode glintMode;
    private final float glintStrength;
    @NotNull
    private static final RenderItemOptions DEFAULTS = new RenderItemOptions(1.0f, -1, RenderItemGlintMode.AUTO, true, true, 0.62f);

    public RenderItemOptions(float alpha, int color, @Nullable RenderItemGlintMode glintMode, boolean showCount, boolean showDurability, float glintStrength) {
        this.showCount = showCount;
        this.showDurability = showDurability;
        this.alpha = RenderItemOptions.Companion.clamp(alpha, 0.0f, 1.0f);
        this.color = RenderItemOptions.Companion.normalizeColor(color);
        RenderItemGlintMode renderItemGlintMode = glintMode;
        if (renderItemGlintMode == null) {
            renderItemGlintMode = RenderItemGlintMode.AUTO;
        }
        this.glintMode = renderItemGlintMode;
        this.glintStrength = RenderItemOptions.Companion.clamp(glintStrength, 0.0f, 1.0f);
    }

    @JvmName(name="showCount")
    public final boolean showCount() {
        return this.showCount;
    }

    @JvmName(name="showDurability")
    public final boolean showDurability() {
        return this.showDurability;
    }

    @JvmName(name="alpha")
    public final float alpha() {
        return this.alpha;
    }

    @JvmName(name="color")
    public final int color() {
        return this.color;
    }

    @JvmName(name="glintMode")
    @NotNull
    public final RenderItemGlintMode glintMode() {
        return this.glintMode;
    }

    @JvmName(name="glintStrength")
    public final float glintStrength() {
        return this.glintStrength;
    }

    @NotNull
    public final RenderItemOptions alpha(float alpha) {
        return new RenderItemOptions(alpha, this.color, this.glintMode, this.showCount, this.showDurability, this.glintStrength);
    }

    @NotNull
    public final RenderItemOptions color(int color) {
        return new RenderItemOptions(this.alpha, color, this.glintMode, this.showCount, this.showDurability, this.glintStrength);
    }

    @NotNull
    public final RenderItemOptions glint(@Nullable RenderItemGlintMode mode) {
        return new RenderItemOptions(this.alpha, this.color, mode, this.showCount, this.showDurability, this.glintStrength);
    }

    @NotNull
    public final RenderItemOptions count(boolean showCount) {
        return new RenderItemOptions(this.alpha, this.color, this.glintMode, showCount, this.showDurability, this.glintStrength);
    }

    @NotNull
    public final RenderItemOptions durability(boolean showDurability) {
        return new RenderItemOptions(this.alpha, this.color, this.glintMode, this.showCount, showDurability, this.glintStrength);
    }

    @NotNull
    public final RenderItemOptions glintStrength(float glintStrength) {
        return new RenderItemOptions(this.alpha, this.color, this.glintMode, this.showCount, this.showDurability, glintStrength);
    }

    @JvmStatic
    @NotNull
    public static final RenderItemOptions defaults() {
        return Companion.defaults();
    }

    @JvmStatic
    @NotNull
    public static final RenderItemOptions noDecorations(float alpha) {
        return Companion.noDecorations(alpha);
    }

    @JvmStatic
    @NotNull
    public static final RenderItemOptions decorated(float alpha) {
        return Companion.decorated(alpha);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\u000bJ\u0017\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J'\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/utils/render/util/renderitem/RenderItemOptions.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/util/renderitem/RenderItemOptions;", "Lkotlin/jvm/JvmStatic;", "defaults", "()Lrtx/kimiko/utils/render/util/renderitem/RenderItemOptions;", "", "alpha", "noDecorations", "(F)Lrtx/kimiko/utils/render/util/renderitem/RenderItemOptions;", "decorated", "", "color", "normalizeColor", "(I)I", "value", "min", "max", "clamp", "(FFF)F", "DEFAULTS", "Lrtx/kimiko/utils/render/util/renderitem/RenderItemOptions;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final RenderItemOptions defaults() {
            return DEFAULTS;
        }

        @JvmStatic
        @NotNull
        public final RenderItemOptions noDecorations(float alpha) {
            return DEFAULTS.alpha(alpha).count(false).durability(false);
        }

        @JvmStatic
        @NotNull
        public final RenderItemOptions decorated(float alpha) {
            return DEFAULTS.alpha(alpha).count(true).durability(true);
        }

        private final int normalizeColor(int color) {
            if ((color & 0xFF000000) == 0 && (color & 0xFFFFFF) != 0) {
                return color | 0xFF000000;
            }
            return color;
        }

        private final float clamp(float value, float min, float max) {
            return Math.max(min, Math.min(max, value));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

