/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.math.MathKt
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui.mainmenu.pages;

import java.util.ArrayList;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.ui.mainmenu.MenuBackground;
import rtx.kimiko.api.ui.mainmenu.MenuBackgrounds;
import rtx.kimiko.api.ui.mainmenu.MenuControls;
import rtx.kimiko.api.ui.mainmenu.MenuPage;
import rtx.kimiko.api.ui.mainmenu.MenuSettings;
import rtx.kimiko.api.ui.mainmenu.MenuTheme;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RoundedScissor;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\n\u0018\u0000 Z2\u00020\u0001:\u0001ZB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\u0006\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u000f\u0010\u0007\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\u0003J?\u0010\u0010\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011J_\u0010\u0018\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019JO\u0010\"\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\"\u0010#JW\u0010(\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\n2\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\n2\u0006\u0010'\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b(\u0010)J\u000f\u0010*\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b*\u0010\u0003J'\u0010-\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010,\u001a\u00020+H\u0016\u00a2\u0006\u0004\b-\u0010.J\u001f\u0010/\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b/\u00100J'\u00101\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010,\u001a\u00020+H\u0016\u00a2\u0006\u0004\b1\u0010.J'\u00104\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u00103\u001a\u000202H\u0016\u00a2\u0006\u0004\b4\u00105J\u0015\u00106\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b6\u00107R\u0016\u00108\u001a\u00020+8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b8\u00109R\u0016\u0010:\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010=\u001a\u00020<8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u0010>R\u0014\u0010?\u001a\u00020<8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u0010>R\u0014\u0010@\u001a\u00020<8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b@\u0010>R\u0014\u0010A\u001a\u00020<8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bA\u0010>R\u0014\u0010B\u001a\u00020<8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010>R\u0014\u0010D\u001a\u00020C8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u0010ER\u0014\u0010G\u001a\u00020F8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010HR\u0014\u0010J\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010KR\u0014\u0010L\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u0010KR$\u0010O\u001a\u0012\u0012\u0004\u0012\u00020\u001a0Mj\b\u0012\u0004\u0012\u00020\u001a`N8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bO\u0010PR\u0016\u0010R\u001a\u00020Q8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bR\u0010SR\u0016\u0010T\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bT\u0010;R\u0016\u0010U\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bU\u0010;R\u0016\u0010V\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010;R\u0016\u0010W\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bW\u0010;R\u0016\u0010X\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bX\u0010;R\u0016\u0010Y\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bY\u0010;\u00a8\u0006["}, d2={"Lrtx/kimiko/api/ui/mainmenu/pages/BackgroundsPage;", "Lrtx/kimiko/api/ui/mainmenu/MenuPage;", "<init>", "()V", "", "onShow", "onHide", "relabel", "Lnet/minecraft/DrawContext;", "graphics", "", "mouseX", "mouseY", "dt", "alpha", "appear", "render", "(Lnet/minecraft/DrawContext;FFFFF)V", "gx", "gy", "gw", "gh", "", "interactive", "renderGallery", "(Lnet/minecraft/DrawContext;FFFFFFFFZ)V", "Lrtx/kimiko/api/ui/mainmenu/MenuBackground;", "background", "active", "tx", "ty", "tw", "th", "hover", "renderTile", "(Lrtx/kimiko/api/ui/mainmenu/MenuBackground;ZFFFFFF)V", "sx", "sy", "sw", "sh", "renderSide", "(FFFFFFFFZ)V", "applySettings", "", "button", "mouseClicked", "(FFI)Z", "clickTile", "(FF)Z", "mouseReleased", "", "delta", "mouseScrolled", "(FFD)Z", "dragSliders", "(F)V", "columns", "I", "sideW", "F", "Lrtx/kimiko/api/ui/mainmenu/MenuControls$Slider;", "blur", "Lrtx/kimiko/api/ui/mainmenu/MenuControls$Slider;", "dim", "vignette", "grain", "parallax", "Lrtx/kimiko/api/ui/mainmenu/MenuControls$Toggle;", "drift", "Lrtx/kimiko/api/ui/mainmenu/MenuControls$Toggle;", "Lrtx/kimiko/api/ui/mainmenu/MenuControls$Segmented;", "atmosphere", "Lrtx/kimiko/api/ui/mainmenu/MenuControls$Segmented;", "Lrtx/kimiko/api/ui/mainmenu/MenuControls$Button;", "folder", "Lrtx/kimiko/api/ui/mainmenu/MenuControls$Button;", "refresh", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "visible", "Ljava/util/ArrayList;", "", "tileHover", "[F", "scroll", "scrollTarget", "contentHeight", "galleryX", "galleryW", "galleryH", "Companion", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nBackgroundsPage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BackgroundsPage.kt\nrtx/kimiko/api/ui/mainmenu/pages/BackgroundsPage\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,480:1\n37#2,2:481\n37#2,2:483\n*S KotlinDebug\n*F\n+ 1 BackgroundsPage.kt\nrtx/kimiko/api/ui/mainmenu/pages/BackgroundsPage\n*L\n52#1:481,2\n88#1:483,2\n*E\n"})
public final class BackgroundsPage
extends MenuPage {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private int columns = 3;
    private float sideW = 210.0f;
    @NotNull
    private final MenuControls.Slider blur;
    @NotNull
    private final MenuControls.Slider dim;
    @NotNull
    private final MenuControls.Slider vignette;
    @NotNull
    private final MenuControls.Slider grain;
    @NotNull
    private final MenuControls.Slider parallax;
    @NotNull
    private final MenuControls.Toggle drift;
    @NotNull
    private final MenuControls.Segmented atmosphere;
    @NotNull
    private final MenuControls.Button folder;
    @NotNull
    private final MenuControls.Button refresh;
    @NotNull
    private final ArrayList<MenuBackground> visible = new ArrayList();
    @NotNull
    private float[] tileHover = new float[0];
    private float scroll;
    private float scrollTarget;
    private float contentHeight;
    private float galleryX;
    private float galleryW;
    private float galleryH;
    private static final float TILE_GAP = 8.0f;
    private static final float SIDE_MIN = 168.0f;

    public BackgroundsPage() {
        this.blur = new MenuControls.Slider(I18n.tr("Размытие"), MenuSettings.blur());
        this.dim = new MenuControls.Slider(I18n.tr("Затемнение"), MenuSettings.dim());
        this.vignette = new MenuControls.Slider(I18n.tr("Виньетка"), MenuSettings.vignette());
        this.grain = new MenuControls.Slider(I18n.tr("Зерно"), MenuSettings.grain());
        this.parallax = new MenuControls.Slider(I18n.tr("Параллакс"), MenuSettings.parallax());
        this.drift = new MenuControls.Toggle(I18n.tr("Живой дрейф"), MenuSettings.drift());
        MenuSettings.Atmosphere[] modes = MenuSettings.Atmosphere.values();
        String[] labels = new String[modes.length];
        for (int i = 0; i < modes.length; i++) {
            labels[i] = modes[i].label();
        }
        this.atmosphere = new MenuControls.Segmented(labels, MenuSettings.atmosphere().ordinal());
        this.folder = new MenuControls.Button(I18n.tr("Папка фонов"), false).icon(Fonts.I2, "E");
        this.refresh = new MenuControls.Button(I18n.tr("Обновить"), false).icon(Fonts.I2, "p");
    }

    @Override
    public void onShow() {
        this.relabel();
        this.blur.set(MenuSettings.blur());
        this.dim.set(MenuSettings.dim());
        this.vignette.set(MenuSettings.vignette());
        this.grain.set(MenuSettings.grain());
        this.parallax.set(MenuSettings.parallax());
        this.drift.set(MenuSettings.drift());
        this.atmosphere.set(MenuSettings.atmosphere().ordinal());
        MenuBackgrounds.rescan();
    }

    @Override
    public void onHide() {
        MenuSettings.save();
    }

    private final void relabel() {
        this.blur.label(I18n.tr("Размытие"));
        this.dim.label(I18n.tr("Затемнение"));
        this.vignette.label(I18n.tr("Виньетка"));
        this.grain.label(I18n.tr("Зерно"));
        this.parallax.label(I18n.tr("Параллакс"));
        this.drift.label(I18n.tr("Живой дрейф"));
        this.folder.label(I18n.tr("Папка фонов"));
        this.refresh.label(I18n.tr("Обновить"));
        MenuSettings.Atmosphere[] modes = MenuSettings.Atmosphere.values();
        String[] labels = new String[modes.length];
        for (int i = 0; i < modes.length; i++) {
            labels[i] = modes[i].label();
        }
        this.atmosphere.options(labels);
    }

    @Override
    public void render(@NotNull DrawContext graphics, float mouseX, float mouseY, float dt, float alpha, float appear) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        if (alpha <= 0.004f) {
            return;
        }
        boolean interactive = appear > 0.85f;
        this.sideW = Math.max(168.0f, Math.min(230.0f, this.w * 0.34f));
        this.galleryX = this.x;
        this.galleryW = this.w - this.sideW - MenuTheme.GAP;
        this.galleryH = this.h;
        this.columns = Math.max(2, Math.min(4, MathKt.roundToInt((float)((this.galleryW - 28.0f) / 118.0f))));
        float sideX = this.x + this.galleryW + MenuTheme.GAP;
        float galleryStagger = MenuTheme.stagger(appear, 0, 3, 0.24f);
        float sideStagger = MenuTheme.stagger(appear, 1, 3, 0.24f);
        this.renderGallery(graphics, this.galleryX, this.y + (1.0f - MenuTheme.ease(galleryStagger)) * 16.0f, this.galleryW, this.galleryH, mouseX, mouseY, dt, alpha * galleryStagger, interactive);
        this.renderSide(sideX, this.y + (1.0f - MenuTheme.ease(sideStagger)) * 20.0f, this.sideW, this.h, mouseX, mouseY, dt, alpha * sideStagger, interactive);
    }

    private final void renderGallery(DrawContext graphics, float gx, float gy, float gw, float gh, float mouseX, float mouseY, float dt, float alpha, boolean interactive) {
        if (alpha <= 0.004f) {
            return;
        }
        MenuTheme.panel(gx, gy, gw, gh, MenuTheme.PANEL_RADIUS, alpha * 0.86f);
        this.visible.clear();
        this.visible.addAll((Collection<MenuBackground>)MenuBackgrounds.all());
        if (this.tileHover.length != this.visible.size()) {
            this.tileHover = new float[this.visible.size()];
        }
        String title = I18n.tr("ГАЛЕРЕЯ");
        Fonts.BOLD.draw(title, gx + 14.0f, gy + 13.0f, 5.6f, MenuTheme.ink(alpha * 0.9f));
        String count = this.visible.size() + I18n.tr(" фонов");
        float countW = Fonts.MEDIUM.width(count, 4.6f);
        Fonts.MEDIUM.draw(count, gx + gw - 14.0f - countW, gy + 14.0f, 4.6f, MenuTheme.inkMuted(alpha));
        MenuTheme.hairline(gx + 14.0f, gy + 26.0f, gw - 28.0f, alpha * 0.7f);
        float listTop = gy + 33.0f;
        float listH = gh - 41.0f;
        float tileW = (gw - 28.0f - 8.0f * (float)(this.columns - 1)) / (float)this.columns;
        float tileH = tileW * 0.62f + 16.0f;
        int rows = (this.visible.size() + this.columns - 1) / this.columns;
        this.contentHeight = (float)rows * (tileH + 8.0f) - 8.0f;
        float maxScroll = Math.max(0.0f, this.contentHeight - listH);
        this.scrollTarget = Math.max(0.0f, Math.min(maxScroll, this.scrollTarget));
        this.scroll = MenuTheme.approach(this.scroll, this.scrollTarget, dt, 14.0f);
        Render2D.pushScissor(graphics, gx + 8.0f, listTop, gw - 16.0f, listH);
        RoundedScissor.push(graphics, gx + 8.0f, listTop, gw - 16.0f, listH, 6.0f, 6.0f, 6.0f, 6.0f);
        MenuBackground selected = MenuBackgrounds.selected();
        int n = ((Collection)this.visible).size();
        for (int i = 0; i < n; ++i) {
            int column = i % this.columns;
            int row = i / this.columns;
            float tx = gx + 14.0f + (float)column * (tileW + 8.0f);
            float ty = listTop + (float)row * (tileH + 8.0f) - this.scroll;
            if (ty + tileH < listTop - 8.0f || ty > listTop + listH + 8.0f) continue;
            MenuBackground background = this.visible.get(i);
            boolean hovered = interactive && this.inside(mouseX, mouseY, tx, ty, tileW, tileH) && mouseY >= listTop && mouseY <= listTop + listH;
            this.tileHover[i] = MenuTheme.approach(this.tileHover[i], hovered ? 1.0f : 0.0f, dt, 12.0f);
            this.renderTile(background, background == selected, tx, ty, tileW, tileH, this.tileHover[i], alpha);
        }
        RoundedScissor.pop();
        Render2D.popScissor(graphics);
        if (maxScroll > 0.5f) {
            float barH = Math.max(18.0f, listH * (listH / Math.max(1.0f, this.contentHeight)));
            float barY = listTop + (listH - barH) * (this.scroll / maxScroll);
            Render2D.rect(gx + gw - 6.5f, listTop, 2.0f, listH, 1.0f, MenuTheme.white(12.0f, alpha));
            Render2D.rect(gx + gw - 6.5f, barY, 2.0f, barH, 1.0f, MenuTheme.accent(150.0f, alpha));
        }
    }

    private final void renderTile(MenuBackground background, boolean active, float tx, float ty, float tw, float th, float hover, float alpha) {
        float imageH = th - 16.0f;
        float radius = 8.0f;
        MenuTheme.card(tx, ty, tw, th, radius, alpha * (0.55f + 0.3f * hover), hover * 0.8f);
        RoundedScissor.push(tx + 1.2f, ty + 1.2f, tw - 2.4f, imageH, radius - 1.0f, radius - 1.0f, 0.0f, 0.0f);
        String texture = background.texture();
        if (texture != null && Render2D.imageReady(texture)) {
            float aspect = background.aspect();
            float boxAspect = (tw - 2.4f) / imageH;
            float drawW = 0.0f;
            float drawH = 0.0f;
            if (aspect >= boxAspect) {
                drawH = imageH * (1.0f + hover * 0.06f);
                drawW = drawH * aspect;
            } else {
                drawW = (tw - 2.4f) * (1.0f + hover * 0.06f);
                drawH = drawW / aspect;
            }
            Render2D.image(texture, tx + 1.2f + (tw - 2.4f - drawW) * 0.5f, ty + 1.2f + (imageH - drawH) * 0.5f, drawW, drawH, 0.0f, MenuTheme.white(205.0f + 50.0f * hover, alpha));
        } else if (background.procedural()) {
            int top = background.kind() == MenuBackground.Kind.AURORA ? MenuTheme.accent(80.0f, alpha) : MenuTheme.accent(48.0f, alpha);
            int bottom = MenuTheme.black(210.0f, alpha);
            Render2D.rect(tx + 1.2f, ty + 1.2f, tw - 2.4f, imageH, 0.0f, top, top, bottom, bottom);
            if (background.kind() == MenuBackground.Kind.AURORA) {
                for (int i = 0; i < 3; ++i) {
                    float bandY = ty + 4.0f + (float)i * 5.5f;
                    Render2D.rect(tx + 3.0f, bandY, tw - 6.0f, 1.6f, 0.8f, MenuTheme.accentBright(120.0f - (float)i * 30.0f, alpha));
                }
            }
        } else {
            Render2D.rect(tx + 1.2f, ty + 1.2f, tw - 2.4f, imageH, 0.0f, MenuTheme.white(10.0f, alpha));
            String label = background.failed() ? "\u00d7" : "…";
            float labelW = Fonts.MEDIUM.width(label, 7.0f);
            Fonts.MEDIUM.draw(label, tx + (tw - labelW) * 0.5f, ty + imageH * 0.5f - 3.5f, 7.0f, MenuTheme.inkMuted(alpha));
        }
        RoundedScissor.pop();
        float nameSize = 4.7f;
        boolean custom = !background.builtIn();
        String tag = I18n.tr("своё");
        float tagW = custom ? Fonts.MEDIUM.width(tag, 4.0f) + 4.0f : 0.0f;
        String name = BackgroundsPage.Companion.fit(I18n.tr(background.name()), nameSize, tw - 12.0f - tagW);
        Fonts.SEMIBOLD.draw(name, tx + 6.0f, ty + th - 11.0f, nameSize, MenuTheme.ink(alpha * (0.82f + 0.18f * hover)));
        if (custom) {
            Fonts.MEDIUM.draw(tag, tx + tw - 6.0f - tagW + 4.0f, ty + th - 10.5f, 4.0f, MenuTheme.accent(180.0f, alpha));
        }
        if (active) {
            Render2D.outlineClient$default(tx, ty, tw, th, radius, 1.1f, MenuTheme.accentBright(230.0f, alpha), 0.0f, 128, null);
            float badge = 12.0f;
            float bx = tx + tw - badge - 5.0f;
            float by = ty + 5.0f;
            Render2D.circle(bx + badge * 0.5f, by + badge * 0.5f, badge * 0.5f, badge * 0.5f, MenuTheme.accent(235.0f, alpha));
            float checkW = Fonts.I2.msdfWidth("d", 6.0f);
            Fonts.I2.msdf("d", bx + (badge - checkW) * 0.5f, by + 3.0f, 6.0f, MenuTheme.white(255.0f, alpha));
        }
    }

    private final void renderSide(float sx, float sy, float sw, float sh, float mouseX, float mouseY, float dt, float alpha, boolean interactive) {
        if (alpha <= 0.004f) {
            return;
        }
        MenuTheme.panel(sx, sy, sw, sh, MenuTheme.PANEL_RADIUS, alpha * 0.9f);
        String title = I18n.tr("ВИД");
        Fonts.BOLD.draw(title, sx + 14.0f, sy + 13.0f, 5.6f, MenuTheme.ink(alpha * 0.9f));
        MenuTheme.hairline(sx + 14.0f, sy + 26.0f, sw - 28.0f, alpha * 0.7f);
        float itemX = sx + 14.0f;
        float itemW = sw - 28.0f;
        float top = sy + 34.0f;
        float bottom = sy + sh - 10.0f;
        float buttonH = Math.max(17.0f, MenuTheme.scaled(21.0f));
        float segH = Math.max(14.0f, MenuTheme.scaled(17.0f));
        float toggleH = Math.max(13.0f, MenuTheme.scaled(16.0f));
        float sliderH = Math.max(12.0f, MenuTheme.scaled(15.0f));
        float hintH = 10.0f;
        float tailH = buttonH + hintH + segH + 9.0f + toggleH + 22.0f;
        float sliderRoom = Math.max(60.0f, bottom - top - tailH);
        float step = Math.max(17.0f, Math.min(MenuTheme.scaled(25.0f), sliderRoom / 5.0f));
        float cursor = top;
        MenuControls.Slider[] sliderArray = new MenuControls.Slider[]{this.blur, this.dim, this.vignette, this.grain, this.parallax};
        for (MenuControls.Slider slider : sliderArray) {
            slider.bounds(itemX, cursor, itemW, sliderH);
            slider.render(mouseX, mouseY, dt, alpha, interactive);
            cursor += step;
        }
        MenuTheme.hairline(itemX, (cursor += 2.0f) - 5.0f, itemW, alpha * 0.5f);
        this.drift.bounds(itemX, cursor, itemW, toggleH);
        this.drift.render(mouseX, mouseY, dt, alpha, interactive);
        String atmoLabel = I18n.tr("Атмосфера");
        Fonts.MEDIUM.draw(atmoLabel, itemX, cursor += toggleH + 7.0f, 4.8f, MenuTheme.inkSoft(alpha * 0.9f));
        this.atmosphere.bounds(itemX, cursor += 9.0f, itemW, segH);
        this.atmosphere.render(mouseX, mouseY, dt, alpha, interactive);
        float buttonY = bottom - buttonH;
        float half = (itemW - 6.0f) * 0.5f;
        this.folder.bounds(itemX, buttonY, half, buttonH);
        this.folder.render(mouseX, mouseY, dt, alpha, interactive);
        this.refresh.bounds(itemX + half + 6.0f, buttonY, half, buttonH);
        this.refresh.render(mouseX, mouseY, dt, alpha, interactive);
        if (buttonY - (cursor + segH) > 13.0f) {
            String hint = I18n.tr("Кинь png или jpg в папку");
            float hintW = Fonts.MEDIUM.width(hint, 4.2f);
            Fonts.MEDIUM.draw(hint, sx + (sw - hintW) * 0.5f, buttonY - 9.5f, 4.2f, MenuTheme.inkFaint(alpha));
        }
        this.dragSliders(mouseX);
        this.applySettings();
    }

    private final void applySettings() {
        MenuSettings.setBlur(this.blur.value());
        MenuSettings.setDim(this.dim.value());
        MenuSettings.setVignette(this.vignette.value());
        MenuSettings.setGrain(this.grain.value());
        MenuSettings.setParallax(this.parallax.value());
        MenuSettings.setDrift(this.drift.value());
        MenuSettings.setAtmosphere((MenuSettings.Atmosphere)((Object)MenuSettings.Atmosphere.getEntries().get(this.atmosphere.index())));
    }

    @Override
    public boolean mouseClicked(float mouseX, float mouseY, int button) {
        if (button != 0) {
            return false;
        }
        if (this.blur.contains(mouseX, mouseY)) {
            this.blur.beginDrag(mouseX);
            Sounds.play("slider");
            return true;
        }
        if (this.dim.contains(mouseX, mouseY)) {
            this.dim.beginDrag(mouseX);
            Sounds.play("slider");
            return true;
        }
        if (this.vignette.contains(mouseX, mouseY)) {
            this.vignette.beginDrag(mouseX);
            Sounds.play("slider");
            return true;
        }
        if (this.grain.contains(mouseX, mouseY)) {
            this.grain.beginDrag(mouseX);
            Sounds.play("slider");
            return true;
        }
        if (this.parallax.contains(mouseX, mouseY)) {
            this.parallax.beginDrag(mouseX);
            Sounds.play("slider");
            return true;
        }
        if (this.drift.contains(mouseX, mouseY)) {
            this.drift.click();
            MenuSettings.setDrift(this.drift.value());
            MenuSettings.save();
            return true;
        }
        if (this.atmosphere.contains(mouseX, mouseY)) {
            this.atmosphere.click(mouseX);
            MenuSettings.setAtmosphere((MenuSettings.Atmosphere)((Object)MenuSettings.Atmosphere.getEntries().get(this.atmosphere.index())));
            MenuSettings.save();
            return true;
        }
        if (this.folder.contains(mouseX, mouseY)) {
            this.folder.tap();
            Sounds.play("gui_click");
            MenuBackgrounds.openFolder();
            return true;
        }
        if (this.refresh.contains(mouseX, mouseY)) {
            this.refresh.tap();
            Sounds.play("gui_click");
            MenuBackgrounds.rescan();
            return true;
        }
        return this.clickTile(mouseX, mouseY);
    }

    private final boolean clickTile(float mouseX, float mouseY) {
        if (this.visible.isEmpty() || mouseX < this.galleryX || mouseX > this.galleryX + this.galleryW) {
            return false;
        }
        float listTop = this.y + 33.0f;
        float listH = this.galleryH - 41.0f;
        if (mouseY < listTop || mouseY > listTop + listH) {
            return false;
        }
        float tileW = (this.galleryW - 28.0f - 8.0f * (float)(this.columns - 1)) / (float)this.columns;
        float tileH = tileW * 0.62f + 16.0f;
        int n = ((Collection)this.visible).size();
        for (int i = 0; i < n; ++i) {
            int column = i % this.columns;
            float tx = this.galleryX + 14.0f + (float)column * (tileW + 8.0f);
            int row = i / this.columns;
            float ty = listTop + (float)row * (tileH + 8.0f) - this.scroll;
            if (!this.inside(mouseX, mouseY, tx, ty, tileW, tileH)) continue;
            MenuBackgrounds.select(this.visible.get(i));
            Sounds.play("select_category");
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseReleased(float mouseX, float mouseY, int button) {
        this.blur.endDrag();
        this.dim.endDrag();
        this.vignette.endDrag();
        this.grain.endDrag();
        this.parallax.endDrag();
        MenuSettings.save();
        return false;
    }

    @Override
    public boolean mouseScrolled(float mouseX, float mouseY, double delta) {
        if (mouseX < this.galleryX || mouseX > this.galleryX + this.galleryW) {
            return false;
        }
        this.scrollTarget -= (float)delta * 34.0f;
        float maxScroll = Math.max(0.0f, this.contentHeight - (this.galleryH - 41.0f));
        this.scrollTarget = Math.max(0.0f, Math.min(maxScroll, this.scrollTarget));
        return true;
    }

    public final void dragSliders(float mouseX) {
        if (this.blur.dragging()) {
            this.blur.drag(mouseX);
        }
        if (this.dim.dragging()) {
            this.dim.drag(mouseX);
        }
        if (this.vignette.dragging()) {
            this.vignette.drag(mouseX);
        }
        if (this.grain.dragging()) {
            this.grain.drag(mouseX);
        }
        if (this.parallax.dragging()) {
            this.parallax.drag(mouseX);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\f\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/ui/mainmenu/pages/BackgroundsPage.Companion;", "", "<init>", "()V", "", "text", "", "size", "maxWidth", "fit", "(Ljava/lang/String;FF)Ljava/lang/String;", "TILE_GAP", "F", "SIDE_MIN", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final String fit(String text, float size, float maxWidth) {
            if (maxWidth <= 4.0f || Fonts.SEMIBOLD.width(text, size) <= maxWidth) {
                return text;
            }
            StringBuilder builder = new StringBuilder();
            char[] cArray = text.toCharArray();
            Intrinsics.checkNotNullExpressionValue((Object)cArray, (String)"toCharArray(...)");
            for (char c : cArray) {
                builder.append(c);
                if (!(Fonts.SEMIBOLD.width(builder + "…", size) > maxWidth)) continue;
                builder.setLength(Math.max(0, builder.length() - 1));
                break;
            }
            return builder + "…";
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

