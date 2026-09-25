/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.mainmenu;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.ui.mainmenu.MainMenuTab;
import rtx.kimiko.api.ui.mainmenu.MenuTheme;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0015\u0018\u0000 42\u00020\u0001:\u00014B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J%\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\t\u0010\nJ=\u0010\u0012\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0012\u0010\u0013J?\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u0017\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u001d\u0010\u001a\u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fR\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020 8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010\"R\u0016\u0010$\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010&\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010%R\u0016\u0010'\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b'\u0010%R\u0016\u0010(\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010%R\u0016\u0010)\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010%R\u0016\u0010*\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010%R\u0016\u0010+\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u0010%R\u0016\u0010,\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010%R\u0016\u0010-\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b-\u0010%R\u0016\u0010.\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b.\u0010%R\u0016\u0010/\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b/\u0010%R\u0016\u00100\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0016\u00102\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u0010%R\u0016\u00103\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u0010%\u00a8\u00065"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuDock;", "", "<init>", "()V", "", "x", "y", "height", "", "layout", "(FFF)V", "Lrtx/kimiko/api/ui/mainmenu/MainMenuTab;", "active", "mouseX", "mouseY", "dt", "alpha", "appear", "render", "(Lrtx/kimiko/api/ui/mainmenu/MainMenuTab;FFFFF)V", "centerX", "renderExit", "(FFFFFF)V", "hit", "(FF)Lrtx/kimiko/api/ui/mainmenu/MainMenuTab;", "", "inExit", "(FF)Z", "", "index", "itemCenterY", "(I)F", "", "hover", "[F", "press", "indicatorY", "F", "indicatorStretch", "exitHover", "railX", "railY", "railH", "firstItemY", "exitY", "itemH", "iconSize", "labelSize", "showLabels", "Z", "itemW", "exitH", "Companion", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nMenuDock.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MenuDock.kt\nrtx/kimiko/api/ui/mainmenu/MenuDock\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,217:1\n37#2,2:218\n*S KotlinDebug\n*F\n+ 1 MenuDock.kt\nrtx/kimiko/api/ui/mainmenu/MenuDock\n*L\n205#1:218,2\n*E\n"})
public final class MenuDock {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final float[] hover = new float[TABS.length];
    @NotNull
    private final float[] press = new float[TABS.length];
    private float indicatorY = Float.NaN;
    private float indicatorStretch;
    private float exitHover;
    private float railX;
    private float railY;
    private float railH;
    private float firstItemY;
    private float exitY;
    private float itemH = 38.0f;
    private float iconSize = 12.5f;
    private float labelSize = 4.4f;
    private boolean showLabels = true;
    private float itemW = 40.0f;
    private float exitH = 22.0f;
    @NotNull
    private static final MainMenuTab[] TABS;

    public final void layout(float x, float y, float height) {
        this.railX = x;
        this.railY = y;
        this.railH = height;
        this.itemW = MenuTheme.scaled(40.0f);
        this.exitH = MenuTheme.scaled(22.0f);
        float room = this.railH - this.exitH - MenuTheme.scaled(14.0f);
        this.itemH = Math.min(MenuTheme.scaled(38.0f), room / (float)TABS.length);
        this.itemH = Math.max(MenuTheme.scaled(20.0f), this.itemH);
        this.showLabels = this.itemH >= MenuTheme.scaled(24.0f);
        this.iconSize = Math.max(MenuTheme.scaled(8.5f), Math.min(MenuTheme.scaled(12.5f), this.itemH * (this.showLabels ? 0.36f : 0.54f)));
        this.labelSize = Math.max(MenuTheme.scaled(3.3f), Math.min(MenuTheme.scaled(4.4f), this.itemH * 0.125f));
        float stackH = (float)TABS.length * this.itemH;
        this.firstItemY = this.railY + (room - stackH) * 0.5f;
        if (this.firstItemY < this.railY + 4.0f) {
            this.firstItemY = this.railY + 4.0f;
        }
        this.exitY = this.railY + this.railH - this.exitH - 4.0f;
    }

    public final void render(@NotNull MainMenuTab active, float mouseX, float mouseY, float dt, float alpha, float appear) {
        Intrinsics.checkNotNullParameter((Object)((Object)active), (String)"active");
        float centerX = this.railX + MenuTheme.RAIL_W * 0.5f;
        float activeCenter = this.itemCenterY(MenuDock.Companion.indexOf(active));
        if (Float.isNaN(this.indicatorY)) {
            this.indicatorY = activeCenter;
        }
        float before = this.indicatorY;
        this.indicatorY = MenuTheme.approach(this.indicatorY, activeCenter, dt, 13.0f);
        float speed = Math.abs(this.indicatorY - before) / Math.max(1.0E-4f, dt);
        this.indicatorStretch = MenuTheme.approach(this.indicatorStretch, Math.min(15.0f, speed * 0.05f), dt, 12.0f);
        int n = TABS.length;
        for (int i = 0; i < n; ++i) {
            MainMenuTab tab = TABS[i];
            float itemY = this.itemCenterY(i) - this.itemH * 0.5f;
            float itemX = centerX - this.itemW * 0.5f;
            float slide = MenuTheme.stagger(appear, i, TABS.length + 1, 0.16f);
            float slideEase = MenuTheme.backOut(slide);
            float offsetX = (1.0f - slideEase) * -22.0f;
            float itemAlpha = alpha * MenuTheme.clamp01(slide * 1.35f);
            if (itemAlpha <= 0.004f) continue;
            boolean hovered = appear > 0.9f && mouseX >= this.railX && mouseX <= this.railX + MenuTheme.RAIL_W && mouseY >= itemY && mouseY <= itemY + this.itemH;
            this.hover[i] = MenuTheme.approach(this.hover[i], hovered ? 1.0f : 0.0f, dt, 12.0f);
            this.press[i] = MenuTheme.approach(this.press[i], 0.0f, dt, 9.0f);
            boolean isActive = tab == active;
            float glow = Math.max(this.hover[i] * 0.55f, isActive ? 1.0f : 0.0f);
            float lift = this.hover[i] * 0.6f + (isActive ? 0.4f : 0.0f);
            if (glow > 0.01f) {
                Render2D.rect(itemX + offsetX, itemY + 1.5f, this.itemW, this.itemH - 3.0f, 9.0f, MenuTheme.white(16.0f * glow, itemAlpha));
            }
            float scaledIcon = this.iconSize * (1.0f + this.press[i] * 0.12f + this.hover[i] * 0.05f);
            float iconW = tab.font().msdfWidth(tab.glyph(), scaledIcon);
            float iconX = centerX - iconW * 0.5f + offsetX;
            float iconY = this.showLabels ? itemY + this.itemH * 0.24f - (scaledIcon - this.iconSize) * 0.5f : itemY + (this.itemH - scaledIcon) * 0.5f;
            int iconColor = isActive ? MenuTheme.accentBright(255.0f, itemAlpha) : MenuTheme.white(120.0f + 95.0f * this.hover[i], itemAlpha);
            tab.font().msdf(tab.glyph(), iconX, iconY, scaledIcon, iconColor);
            if (this.showLabels) {
                String label = tab.title();
                float labelW = Fonts.MEDIUM.width(label, this.labelSize);
                int labelColor = isActive ? MenuTheme.accent(230.0f, itemAlpha) : MenuTheme.white(88.0f + 92.0f * this.hover[i], itemAlpha);
                Fonts.MEDIUM.draw(label, centerX - labelW * 0.5f + offsetX, itemY + this.itemH - this.labelSize * 2.6f, this.labelSize, labelColor);
            }
            if (!isActive || !this.showLabels) continue;
            float underline = 9.0f + lift * 3.0f;
            Render2D.rect(centerX - underline * 0.5f + offsetX, itemY + this.itemH - 4.5f, underline, 0.8f, 0.4f, MenuTheme.accent(150.0f, itemAlpha));
        }
        float indicatorAlpha = alpha * MenuTheme.clamp01((appear - 0.25f) / 0.5f);
        if (indicatorAlpha > 0.004f) {
            float height = 16.0f + this.indicatorStretch;
            Render2D.rect(this.railX + 1.5f, this.indicatorY - height * 0.5f, 2.4f, height, 1.2f, MenuTheme.accentBright(255.0f, indicatorAlpha));
            Render2D.rect(this.railX + 1.5f, this.indicatorY - height * 0.5f, 8.0f, height, 1.2f, MenuTheme.accent(45.0f, indicatorAlpha * 0.7f));
        }
        this.renderExit(centerX, mouseX, mouseY, dt, alpha, appear);
    }

    private final void renderExit(float centerX, float mouseX, float mouseY, float dt, float alpha, float appear) {
        float slide = MenuTheme.stagger(appear, TABS.length, TABS.length + 1, 0.16f);
        float itemAlpha = alpha * MenuTheme.clamp01(slide * 1.35f);
        if (itemAlpha <= 0.004f) {
            return;
        }
        float offsetX = (1.0f - MenuTheme.backOut(slide)) * -22.0f;
        boolean hovered = appear > 0.9f && this.inExit(mouseX, mouseY);
        this.exitHover = MenuTheme.approach(this.exitHover, hovered ? 1.0f : 0.0f, dt, 12.0f);
        if (this.exitHover > 0.01f) {
            Render2D.rect(centerX - this.itemW * 0.5f + offsetX, this.exitY, this.itemW, this.exitH, 8.0f, MenuTheme.fade(-1550998, 0.1f * this.exitHover * itemAlpha));
        }
        float size = 10.5f;
        float iconW = Fonts.I2.msdfWidth("п", size);
        int color = MenuTheme.mix(MenuTheme.white(105.0f, itemAlpha), MenuTheme.fade(-36219, itemAlpha), this.exitHover);
        Fonts.I2.msdf("п", centerX - iconW * 0.5f + offsetX, this.exitY + (this.exitH - size) * 0.5f, size, color);
    }

    @Nullable
    public final MainMenuTab hit(float mouseX, float mouseY) {
        if (mouseX < this.railX || mouseX > this.railX + MenuTheme.RAIL_W) {
            return null;
        }
        int n = TABS.length;
        for (int i = 0; i < n; ++i) {
            float itemY = this.itemCenterY(i) - this.itemH * 0.5f;
            if (!(mouseY >= itemY) || !(mouseY <= itemY + this.itemH)) continue;
            this.press[i] = 1.0f;
            return TABS[i];
        }
        return null;
    }

    public final boolean inExit(float mouseX, float mouseY) {
        float centerX = this.railX + MenuTheme.RAIL_W * 0.5f;
        return mouseX >= centerX - this.itemW * 0.5f && mouseX <= centerX + this.itemW * 0.5f && mouseY >= this.exitY && mouseY <= this.exitY + this.exitH;
    }

    private final float itemCenterY(int index) {
        return this.firstItemY + (float)index * this.itemH + this.itemH * 0.5f;
    }

    static {
        TABS = MainMenuTab.values();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\t8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000b\u00a8\u0006\f"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuDock.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/ui/mainmenu/MainMenuTab;", "tab", "", "indexOf", "(Lrtx/kimiko/api/ui/mainmenu/MainMenuTab;)I", "", "TABS", "[Lrtx/kimiko/api/ui/mainmenu/MainMenuTab;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final int indexOf(MainMenuTab tab) {
            int n = TABS.length;
            for (int i = 0; i < n; ++i) {
                if (TABS[i] != tab) continue;
                return i;
            }
            return 0;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

