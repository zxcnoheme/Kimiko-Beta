/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.mainmenu;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.ui.mainmenu.MenuTheme;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0005\u0004\u0005\u0006\u0007\bB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\t"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuControls;", "", "<init>", "()V", "Control", "Button", "Toggle", "Slider", "Segmented", "rtx.kimiko:kimiko"})
public final class MenuControls {
    @NotNull
    public static final MenuControls INSTANCE = new MenuControls();

    private MenuControls() {
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\u000b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u0003\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u000fJ5\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0018R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0019R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0018\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuControls$Button;", "Lrtx/kimiko/api/ui/mainmenu/MenuControls$Control;", "", "label", "", "primary", "<init>", "(Ljava/lang/String;Z)V", "Lrtx/kimiko/utils/render/fonts/Fonts;", "font", "glyph", "icon", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;)Lrtx/kimiko/api/ui/mainmenu/MenuControls$Button;", "value", "", "(Ljava/lang/String;)V", "", "mouseX", "mouseY", "dt", "alpha", "interactive", "render", "(FFFFZ)V", "Ljava/lang/String;", "Z", "glyphFont", "Lrtx/kimiko/utils/render/fonts/Fonts;", "rtx.kimiko:kimiko"})
    public static final class Button
    extends Control {
        @NotNull
        private String label;
        private final boolean primary;
        @Nullable
        private Fonts glyphFont;
        @Nullable
        private String glyph;

        public Button(@NotNull String label, boolean primary) {
            Intrinsics.checkNotNullParameter((Object)label, (String)"label");
            this.label = label;
            this.primary = primary;
        }

        @NotNull
        public final Button icon(@NotNull Fonts font, @NotNull String glyph) {
            Intrinsics.checkNotNullParameter((Object)((Object)font), (String)"font");
            Intrinsics.checkNotNullParameter((Object)glyph, (String)"glyph");
            this.glyphFont = font;
            this.glyph = glyph;
            return this;
        }

        public final void label(@NotNull String value) {
            Intrinsics.checkNotNullParameter((Object)value, (String)"value");
            this.label = value;
        }

        public final void render(float mouseX, float mouseY, float dt, float alpha, boolean interactive) {
            int textColor;
            float f;
            this.updateHover(mouseX, mouseY, dt, interactive);
            float lift = this.hover * (1.0f - this.press * 0.4f);
            float radius = Math.min(this.h * 0.5f, 9.0f);
            if (this.primary) {
                Render2D.rect(this.x, this.y, this.w, this.h, radius, MenuTheme.accent(150.0f + 60.0f * lift, alpha));
                Render2D.outlineClient$default(this.x, this.y, this.w, this.h, radius, 0.75f, MenuTheme.accentBright(180.0f, alpha), 0.0f, 128, null);
            } else {
                MenuTheme.card(this.x, this.y, this.w, this.h, radius, alpha * (0.62f + 0.3f * lift), lift);
            }
            float textSize = 5.3f;
            float glyphSize = 7.2f;
            String currentGlyph = this.glyph;
            if (currentGlyph == null) {
                f = 0.0f;
            } else {
                Fonts fonts = this.glyphFont;
                Intrinsics.checkNotNull((Object)((Object)fonts));
                f = fonts.msdfWidth(currentGlyph, glyphSize);
            }
            float glyphW = f;
            float gap = currentGlyph == null ? 0.0f : 5.0f;
            float labelW = Fonts.SEMIBOLD.width(this.label, textSize);
            float startX = this.x + (this.w - (glyphW + gap + labelW)) * 0.5f;
            float centerY = this.y + this.h * 0.5f;
            int n = textColor = this.primary ? MenuTheme.white(255.0f, alpha) : MenuTheme.white(190.0f + 65.0f * lift, alpha);
            if (currentGlyph != null) {
                Fonts fonts = this.glyphFont;
                Intrinsics.checkNotNull((Object)((Object)fonts));
                fonts.msdf(currentGlyph, startX, centerY - glyphSize * 0.5f + 0.4f, glyphSize, textColor);
            }
            Fonts.SEMIBOLD.draw(this.label, startX + glyphW + gap, centerY - textSize * 0.62f, textSize, textColor);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J-\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000f\u0010\u0010J/\u0010\u0013\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u000eH\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\t\u00a2\u0006\u0004\b\u0015\u0010\u0003J\r\u0010\u0016\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0005\u001a\u00020\u00048\u0004@\u0004X\u0085\u000e\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0019R\u001b\u0010\u0006\u001a\u00020\u00048\u0004@\u0004X\u0085\u000e\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0019R\u001b\u0010\u0007\u001a\u00020\u00048\u0004@\u0004X\u0085\u000e\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0019R\u001b\u0010\b\u001a\u00020\u00048\u0004@\u0004X\u0085\u000e\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0019R\u001b\u0010\u0016\u001a\u00020\u00048\u0004@\u0004X\u0085\u000e\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0019R\u001b\u0010\u001a\u001a\u00020\u00048\u0004@\u0004X\u0085\u000e\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0019\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuControls$Control;", "", "<init>", "()V", "", "x", "y", "w", "h", "", "bounds", "(FFFF)V", "mouseX", "mouseY", "", "contains", "(FF)Z", "dt", "interactive", "updateHover", "(FFFZ)V", "tap", "hover", "()F", "Lkotlin/jvm/JvmField;", "F", "press", "rtx.kimiko:kimiko"})
    public static class Control {
        @JvmField
        protected float x;
        @JvmField
        protected float y;
        @JvmField
        protected float w;
        @JvmField
        protected float h;
        @JvmField
        protected float hover;
        @JvmField
        protected float press;

        public final void bounds(float x, float y, float w, float h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

        public final boolean contains(float mouseX, float mouseY) {
            return mouseX >= this.x && mouseX <= this.x + this.w && mouseY >= this.y && mouseY <= this.y + this.h;
        }

        protected final void updateHover(float mouseX, float mouseY, float dt, boolean interactive) {
            boolean hovered = interactive && this.contains(mouseX, mouseY);
            this.hover = MenuTheme.approach(this.hover, hovered ? 1.0f : 0.0f, dt, 13.0f);
            this.press = MenuTheme.approach(this.press, 0.0f, dt, 8.0f);
        }

        public final void tap() {
            this.press = 1.0f;
        }

        public final float hover() {
            return this.hover;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0014\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bJ\r\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\tJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0005\u00a2\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u0004\u001a\u00020\u000b2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0004\u0010\u000eJ\u0015\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0011\u0010\u0012J5\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019R\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u001aR\u0016\u0010\u0006\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u001bR\u0016\u0010\u001c\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 \u00a8\u0006!"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuControls$Segmented;", "Lrtx/kimiko/api/ui/mainmenu/MenuControls$Control;", "", "", "options", "", "index", "<init>", "([Ljava/lang/String;I)V", "()I", "next", "", "set", "(I)V", "([Ljava/lang/String;)V", "", "mouseX", "click", "(F)I", "mouseY", "dt", "alpha", "", "interactive", "render", "(FFFFZ)V", "[Ljava/lang/String;", "I", "indicator", "F", "", "optionHover", "[F", "rtx.kimiko:kimiko"})
    public static final class Segmented
    extends Control {
        @NotNull
        private String[] options;
        private int index;
        private float indicator;
        @NotNull
        private final float[] optionHover;

        public Segmented(@NotNull String[] options, int index) {
            Intrinsics.checkNotNullParameter((Object)options, (String)"options");
            this.options = options;
            this.index = Math.max(0, Math.min(this.options.length - 1, index));
            this.indicator = this.index;
            this.optionHover = new float[this.options.length];
        }

        public final int index() {
            return this.index;
        }

        public final void set(int next) {
            this.index = Math.max(0, Math.min(this.options.length - 1, next));
        }

        public final void options(@Nullable String[] next) {
            if (next != null && next.length == this.options.length) {
                this.options = next;
            }
        }

        public final int click(float mouseX) {
            float step = this.w / (float)this.options.length;
            int next = (int)((mouseX - this.x) / Math.max(0.001f, step));
            if ((next = Math.max(0, Math.min(this.options.length - 1, next))) != this.index) {
                this.index = next;
                this.tap();
                Sounds.play("gui_click");
            }
            return this.index;
        }

        public final void render(float mouseX, float mouseY, float dt, float alpha, boolean interactive) {
            this.updateHover(mouseX, mouseY, dt, interactive);
            this.indicator = MenuTheme.approach(this.indicator, this.index, dt, 16.0f);
            float radius = Math.min(this.h * 0.5f, 8.0f);
            Render2D.rect(this.x, this.y, this.w, this.h, radius, MenuTheme.black(70.0f, alpha));
            Render2D.outline(this.x, this.y, this.w, this.h, radius, 0.6f, MenuTheme.white(18.0f, alpha));
            float step = this.w / (float)this.options.length;
            float pillX = this.x + 1.6f + this.indicator * step;
            Render2D.rect(pillX, this.y + 1.6f, step - 3.2f, this.h - 3.2f, radius - 1.2f, MenuTheme.accent(120.0f, alpha));
            float textSize = 4.8f;
            int n = this.options.length;
            for (int i = 0; i < n; ++i) {
                float cellX = this.x + (float)i * step;
                boolean hovered = interactive && mouseX >= cellX && mouseX <= cellX + step && mouseY >= this.y && mouseY <= this.y + this.h;
                this.optionHover[i] = MenuTheme.approach(this.optionHover[i], hovered ? 1.0f : 0.0f, dt, 13.0f);
                float active = MenuTheme.clamp01(1.0f - Math.abs(this.indicator - (float)i));
                float textW = Fonts.SEMIBOLD.width(this.options[i], textSize);
                int color = MenuTheme.mix(MenuTheme.white(105.0f + 55.0f * this.optionHover[i], alpha), MenuTheme.white(255.0f, alpha), active);
                Fonts.SEMIBOLD.draw(this.options[i], cellX + (step - textW) * 0.5f, this.y + this.h * 0.5f - textSize * 0.62f, textSize, color);
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\bJ\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u0003\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\rJ\r\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0012\u0010\fJ\r\u0010\u0013\u001a\u00020\n\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0015\u0010\fJ5\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u000e\u00a2\u0006\u0004\b\u001a\u0010\u001bR\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u001cR\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u001dR\u0016\u0010\u000f\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u001eR\u0016\u0010\u001f\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001d\u00a8\u0006 "}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuControls$Slider;", "Lrtx/kimiko/api/ui/mainmenu/MenuControls$Control;", "", "label", "", "value", "<init>", "(Ljava/lang/String;F)V", "()F", "next", "", "set", "(F)V", "(Ljava/lang/String;)V", "", "dragging", "()Z", "mouseX", "beginDrag", "endDrag", "()V", "drag", "mouseY", "dt", "alpha", "interactive", "render", "(FFFFZ)V", "Ljava/lang/String;", "F", "Z", "display", "rtx.kimiko:kimiko"})
    public static final class Slider
    extends Control {
        @NotNull
        private String label;
        private float value;
        private boolean dragging;
        private float display;

        public Slider(@NotNull String label, float value) {
            Intrinsics.checkNotNullParameter((Object)label, (String)"label");
            this.label = label;
            this.display = this.value = MenuTheme.clamp01(value);
        }

        public final float value() {
            return this.value;
        }

        public final void set(float next) {
            this.value = MenuTheme.clamp01(next);
        }

        public final void label(@NotNull String value) {
            Intrinsics.checkNotNullParameter((Object)value, (String)"value");
            this.label = value;
        }

        public final boolean dragging() {
            return this.dragging;
        }

        public final void beginDrag(float mouseX) {
            this.dragging = true;
            this.drag(mouseX);
        }

        public final void endDrag() {
            this.dragging = false;
        }

        public final void drag(float mouseX) {
            float trackX = this.x;
            float trackW = this.w;
            this.value = MenuTheme.clamp01((mouseX - trackX) / Math.max(1.0f, trackW));
        }

        public final void render(float mouseX, float mouseY, float dt, float alpha, boolean interactive) {
            this.updateHover(mouseX, mouseY, dt, interactive);
            this.display = MenuTheme.approach(this.display, this.value, dt, 18.0f);
            float textSize = 5.0f;
            float labelY = this.y;
            Fonts.MEDIUM.draw(this.label, this.x, labelY, textSize, MenuTheme.inkSoft(alpha * 0.9f));
            String percent = MathKt.roundToInt((float)(this.value * 100.0f)) + "%";
            float percentW = Fonts.SEMIBOLD.width(percent, textSize);
            Fonts.SEMIBOLD.draw(percent, this.x + this.w - percentW, labelY, textSize, MenuTheme.accentBright(215.0f, alpha));
            float trackY = this.y + this.h - 4.0f;
            float trackH = 3.2f;
            Render2D.rect(this.x, trackY, this.w, trackH, trackH * 0.5f, MenuTheme.white(24.0f, alpha));
            float fillW = Math.max(trackH, this.w * this.display);
            Render2D.rect(this.x, trackY, fillW, trackH, trackH * 0.5f, MenuTheme.accent(225.0f, alpha));
            float knobR = 3.1f + this.hover * 0.7f + (this.dragging ? 0.6f : 0.0f);
            float knobX = this.x + Math.max(knobR, Math.min(this.w - knobR, this.w * this.display));
            Render2D.circle(knobX, trackY + trackH * 0.5f, knobR + 1.6f, knobR + 1.6f, MenuTheme.accent(60.0f, alpha));
            Render2D.circle(knobX, trackY + trackH * 0.5f, knobR, knobR, MenuTheme.white(250.0f, alpha));
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\bJ\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u0003\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\rJ\r\u0010\u000e\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000e\u0010\bJ5\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0017R\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0018R\u0016\u0010\u0019\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuControls$Toggle;", "Lrtx/kimiko/api/ui/mainmenu/MenuControls$Control;", "", "label", "", "value", "<init>", "(Ljava/lang/String;Z)V", "()Z", "next", "", "set", "(Z)V", "(Ljava/lang/String;)V", "click", "", "mouseX", "mouseY", "dt", "alpha", "interactive", "render", "(FFFFZ)V", "Ljava/lang/String;", "Z", "knob", "F", "rtx.kimiko:kimiko"})
    public static final class Toggle
    extends Control {
        @NotNull
        private String label;
        private boolean value;
        private float knob;

        public Toggle(@NotNull String label, boolean value) {
            Intrinsics.checkNotNullParameter((Object)label, (String)"label");
            this.label = label;
            this.value = value;
            this.knob = this.value ? 1.0f : 0.0f;
        }

        public final boolean value() {
            return this.value;
        }

        public final void set(boolean next) {
            this.value = next;
        }

        public final void label(@NotNull String value) {
            Intrinsics.checkNotNullParameter((Object)value, (String)"value");
            this.label = value;
        }

        public final boolean click() {
            this.value = !this.value;
            this.tap();
            Sounds.play("gui_click");
            return this.value;
        }

        public final void render(float mouseX, float mouseY, float dt, float alpha, boolean interactive) {
            this.updateHover(mouseX, mouseY, dt, interactive);
            this.knob = MenuTheme.approach(this.knob, this.value ? 1.0f : 0.0f, dt, 15.0f);
            float textSize = 5.2f;
            Fonts.MEDIUM.draw(this.label, this.x, this.y + this.h * 0.5f - textSize * 0.62f, textSize, MenuTheme.ink(alpha * (0.72f + 0.28f * this.hover)));
            float trackW = 20.0f;
            float trackH = 10.5f;
            float trackX = this.x + this.w - trackW;
            float trackY = this.y + (this.h - trackH) * 0.5f;
            int off = MenuTheme.white(30.0f, alpha);
            int on = MenuTheme.accent(210.0f, alpha);
            Render2D.rect(trackX, trackY, trackW, trackH, trackH * 0.5f, MenuTheme.mix(off, on, this.knob));
            Render2D.outline(trackX, trackY, trackW, trackH, trackH * 0.5f, 0.6f, MenuTheme.white(28.0f + 40.0f * this.knob, alpha));
            float knobSize = trackH - 3.0f;
            float knobX = trackX + 1.5f + (trackW - knobSize - 3.0f) * this.knob;
            Render2D.circle(knobX + knobSize * 0.5f, trackY + trackH * 0.5f, knobSize * 0.5f, knobSize * 0.5f, MenuTheme.white(240.0f, alpha));
        }
    }
}

