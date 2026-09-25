/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmOverloads
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.drags.components;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.drags.DragSystem;
import rtx.kimiko.api.drags.Draggable;
import rtx.kimiko.api.drags.components.HudHeaderRenderer;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.utils.animations.Easing;
import rtx.kimiko.utils.animations.HudFadeAnimation;
import rtx.kimiko.utils.animations.SmoothAnimation;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.util.renderitem.RenderItem;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\r\b&\u0018\u0000 Z2\u00020\u0001:\u0005[\\]^ZB9\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH$\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0011\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0014\u00a2\u0006\u0004\b\u0013\u0010\u000eJ\u000f\u0010\u0014\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0015J\u000f\u0010\u0018\u001a\u00020\u0017H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\bH\u0014\u00a2\u0006\u0004\b\u001a\u0010\u0015J\u0017\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001bH\u0014\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b \u0010\u0019J\u000f\u0010!\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b!\u0010\"J\u000f\u0010#\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b#\u0010\"J\u0017\u0010%\u001a\u00020\b2\u0006\u0010$\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b%\u0010&J'\u0010*\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020\b2\u0006\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b*\u0010+J\u000f\u0010,\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b,\u0010\"J7\u0010.\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020\b2\u0006\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020\b2\u0006\u0010-\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b.\u0010/J\u000f\u00100\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b0\u0010\u0019J\u000f\u00101\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b1\u0010\u0015J\u000f\u00102\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b2\u0010\"J\u000f\u00103\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b3\u0010\u0015J\u000f\u00104\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b4\u0010\u0015J\u000f\u00105\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b5\u0010\u0015J\u000f\u00107\u001a\u000206H\u0002\u00a2\u0006\u0004\b7\u00108J\u000f\u00109\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b9\u0010\u0015R\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010:R\u001c\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010;R \u0010?\u001a\u000e\u0012\u0004\u0012\u00020=\u0012\u0004\u0012\u00020>0<8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u0010@R\u0014\u0010B\u001a\u00020A8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0014\u0010E\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010FR\u0014\u0010G\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010FR\u0014\u0010H\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u0010FR\u0016\u0010I\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bI\u0010JR\u0016\u0010K\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010JR\u0016\u0010L\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bL\u0010JR\u0016\u0010N\u001a\u00020M8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bN\u0010OR\u0016\u0010P\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bP\u0010QR\u0016\u0010S\u001a\u00020R8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bS\u0010TR\u0016\u0010U\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bU\u0010QR\u0016\u0010V\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010QR\u0016\u0010W\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bW\u0010QR\u0018\u0010X\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bX\u0010:R\u0018\u00107\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b7\u0010Y\u00a8\u0006_"}, d2={"Lrtx/kimiko/api/drags/components/ListHudComp;", "Lrtx/kimiko/api/drags/Draggable;", "", "id", "title", "Ljava/lang/Class;", "Lrtx/kimiko/api/modules/Module;", "moduleType", "", "defaultX", "defaultY", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Class;FF)V", "displayName", "()Ljava/lang/String;", "", "Lrtx/kimiko/api/drags/components/ListHudComp$Row;", "collectRows", "()Ljava/util/List;", "headerIconGlyph", "width", "()F", "height", "", "isInteractive", "()Z", "iconSlotSize", "Lnet/minecraft/DrawContext;", "graphics", "", "render", "(Lnet/minecraft/DrawContext;)V", "computeTargetVisible", "syncRows", "()V", "refreshMetrics", "progress", "contentTopPadding", "(F)F", "x", "y", "alpha", "renderDivider", "(FFF)V", "updateDividerAlpha", "clipRadius", "drawRows", "(Lnet/minecraft/DrawContext;FFFF)V", "shouldShow", "dividerProgress", "updateHeaderIcon", "headerIconProgress", "headerIconWidth", "headerMinWidth", "Lrtx/kimiko/api/drags/components/HudHeaderRenderer;", "newHeaderRenderer", "()Lrtx/kimiko/api/drags/components/HudHeaderRenderer;", "titleWidth", "Ljava/lang/String;", "Ljava/lang/Class;", "Ljava/util/LinkedHashMap;", "", "Lrtx/kimiko/api/drags/components/ListHudComp$RowState;", "rows", "Ljava/util/LinkedHashMap;", "Lrtx/kimiko/utils/animations/HudFadeAnimation;", "visibility", "Lrtx/kimiko/utils/animations/HudFadeAnimation;", "Lrtx/kimiko/utils/animations/SmoothAnimation;", "widthAnimation", "Lrtx/kimiko/utils/animations/SmoothAnimation;", "dividerAlpha", "headerIconAnim", "lastDividerVisible", "Z", "lastHeaderIconVisible", "everHadContent", "", "sizeCollapsedAtMs", "J", "titleWidthCache", "F", "", "activeCount", "I", "rowsProgress", "currentWidth", "currentHeight", "newHeaderGlyph", "Lrtx/kimiko/api/drags/components/HudHeaderRenderer;", "Companion", "IconDrawer", "AlphaPulse", "Row", "RowState", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nListHudComp.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListHudComp.kt\nrtx/kimiko/api/drags/components/ListHudComp\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,585:1\n460#2,7:586\n*S KotlinDebug\n*F\n+ 1 ListHudComp.kt\nrtx/kimiko/api/drags/components/ListHudComp\n*L\n222#1:586,7\n*E\n"})
public abstract class ListHudComp
extends Draggable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String title;
    @NotNull
    private final Class<? extends Module> moduleType;
    @NotNull
    private final LinkedHashMap<Object, RowState> rows;
    @NotNull
    private final HudFadeAnimation visibility;
    @NotNull
    private final SmoothAnimation widthAnimation;
    @NotNull
    private final SmoothAnimation dividerAlpha;
    @NotNull
    private final SmoothAnimation headerIconAnim;
    private boolean lastDividerVisible;
    private boolean lastHeaderIconVisible;
    private boolean everHadContent;
    private long sizeCollapsedAtMs;
    private float titleWidthCache;
    private int activeCount;
    private float rowsProgress;
    private float currentWidth;
    private float currentHeight;
    @Nullable
    private String newHeaderGlyph;
    @Nullable
    private HudHeaderRenderer newHeaderRenderer;
    private static final float HEADER_HEIGHT = 22.0f;
    private static final float CONTENT_TOP = 5.0f;
    private static final float PAD_X = 9.0f;
    private static final float PAD_BOTTOM = 5.0f;
    @NotNull
    private static final String ROW_CENTER_REFERENCE = "[H]";
    private static final float ROW_HEIGHT = 11.5f;
    private static final float TITLE_SIZE = 8.2f;
    private static final float ROW_SIZE = 6.5f;
    private static final float VALUE_GAP = 10.0f;
    private static final float HEADER_EXTRA_WIDTH = 10.0f;
    private static final float NAME_SLIDE = 7.0f;
    private static final float VALUE_SLIDE = 7.0f;
    private static final float ICON_SIZE = 8.5f;
    private static final float ICON_GAP = 4.0f;
    private static final float HEADER_ICON_SIZE = 7.0f;
    private static final float HEADER_ICON_GAP = 6.0f;
    private static final float HEADER_ICON_SLIDE = 5.0f;
    private static final double HEADER_ICON_IN_SECONDS = 0.25;
    private static final double HEADER_ICON_OUT_SECONDS = 0.2;
    private static final float RADIUS = 7.0f;
    private static final float DIVIDER_HEIGHT = 0.5f;
    private static final float ROW_CLIP_PAD_Y = 2.0f;
    private static final double ROW_IN_SECONDS = 0.22;
    private static final double ROW_OUT_SECONDS = 0.18;
    private static final double WIDTH_SECONDS = 0.24;
    private static final double DIVIDER_IN_SECONDS = 0.24;
    private static final double DIVIDER_OUT_SECONDS = 0.14;
    private static final float MIN_TEXT_ALPHA = 0.003921569f;
    private static final float MIN_DIVIDER_ALPHA = 0.003921569f;
    private static final long FADE_OUT_DELAY_MS = 100L;
    @NotNull
    private static final Easing ROW_EASING = ListHudComp::ROW_EASING$lambda$0;
    private static final int ROW_COLOR = -3355444;
    private static final int VALUE_COLOR = -3355444;
    private static final int DIVIDER_COLOR = -1997220937;

    protected ListHudComp(@NotNull String id, @NotNull String title, @NotNull Class<? extends Module> moduleType, float defaultX, float defaultY) {
        super(id, defaultX, defaultY);
        this.title = title;
        this.moduleType = moduleType;
        this.rows = new LinkedHashMap();
        this.visibility = this.getHudFade();
        this.widthAnimation = new SmoothAnimation();
        this.dividerAlpha = new SmoothAnimation();
        this.headerIconAnim = new SmoothAnimation();
        this.titleWidthCache = -1.0f;
        this.currentWidth = 60.0f;
        this.currentHeight = 22.0f;
        this.visibility.set(0.0);
        this.widthAnimation.set(this.currentWidth);
        this.dividerAlpha.set(0.0);
        this.headerIconAnim.set(0.0);
    }

    @Override
    @NotNull
    public String displayName() {
        return this.title;
    }

    @NotNull
    protected abstract List<Row> collectRows();

    @Nullable
    protected String headerIconGlyph() {
        return null;
    }

    @Override
    public float width() {
        return this.currentWidth;
    }

    @Override
    public float height() {
        return this.currentHeight;
    }

    @Override
    public boolean isInteractive() {
        return this.shouldShow();
    }

    protected float iconSlotSize() {
        return 8.5f;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected void render(@NotNull DrawContext graphics) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        this.syncRows();
        this.updateDividerAlpha();
        this.updateHeaderIcon();
        this.refreshMetrics();
        this.widthAnimation.update();
        boolean targetVisible = this.computeTargetVisible();
        this.visibility.updateTarget(targetVisible);
        float alpha = this.visibility.get();
        if (targetVisible && alpha <= 0.01f) {
            alpha = 0.01f;
        }
        if (alpha <= 0.01f && !targetVisible) {
            return;
        }
        this.currentWidth = Math.max(this.headerMinWidth(), this.widthAnimation.get());
        float x = this.getX();
        float y = this.getY();
        float scale = 0.96f + alpha * 0.04f;
        float originX = x + this.currentWidth * 0.5f;
        float originY = y + this.currentHeight * 0.5f;
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(originX, originY);
        graphics.getMatrices().scale(scale);
        graphics.getMatrices().translate(-originX, -originY);
        RenderItem.beginFrame(graphics);
        Render2D.beginFrame(graphics);
        RectUtil.drawClientRect(x, y, this.currentWidth, this.currentHeight, 7.0f, alpha);
        float clipRadius = ListHudComp.Companion.clientRectRadius(this.currentWidth, this.currentHeight);
        if (HudHeaderRenderer.Companion.isNewMode()) {
            this.newHeaderRenderer().render(x, y, this.currentWidth, 22.0f, alpha, this.headerIconProgress());
        } else {
            float headerClipHeight = 13.2f;
            float headerClipY = y + (22.0f - headerClipHeight) * 0.5f;
            if (ListHudComp.Companion.pushRoundedContentClip(graphics, x, y, this.currentWidth, this.currentHeight, clipRadius, headerClipY, headerClipHeight)) {
                try {
                    float iconT = this.headerIconProgress();
                    float[] titleBounds = ListHudComp.Companion.textBounds(Fonts.SEMIBOLD, this.title, 8.2f);
                    float titleVisualWidth = titleBounds[2] - titleBounds[0];
                    float headerCenterY = y + 11.0f;
                    float titleY = headerCenterY - (titleBounds[1] + titleBounds[3]) * 0.5f;
                    float centeredX = x + (this.currentWidth - titleVisualWidth) * 0.5f - titleBounds[0];
                    float leftX = x + 9.0f - titleBounds[0];
                    float titleX = centeredX + (leftX - centeredX) * iconT;
                    ListHudComp.Companion.drawText(Fonts.SEMIBOLD, this.title, titleX, titleY, 8.2f, ListHudComp.Companion.titleColor(titleX + (titleBounds[0] + titleBounds[2]) * 0.5f, headerCenterY), alpha);
                    String glyph = this.headerIconGlyph();
                    if (glyph != null && iconT > 0.003921569f) {
                        float[] iconBounds = ListHudComp.Companion.textBounds(Fonts.KIMIKO, glyph, 7.0f);
                        float iconX = x + this.currentWidth - 9.0f - iconBounds[2] + 5.0f * (1.0f - iconT);
                        float iconY = headerCenterY - (iconBounds[1] + iconBounds[3]) * 0.5f;
                        Fonts.KIMIKO.msdf(glyph, iconX, iconY, 7.0f, ColorEngine.multAlpha(ListHudComp.Companion.titleColor(iconX + (iconBounds[0] + iconBounds[2]) * 0.5f, headerCenterY), alpha * iconT * 0.8f));
                    }
                }
                finally {
                    Render2D.popScissor(graphics);
                }
            }
        }
        this.renderDivider(x, y, alpha);
        this.drawRows(graphics, x, y, alpha, clipRadius);
        Render2D.flush();
        RenderItem.flush();
        graphics.getMatrices().popMatrix();
    }

    private final boolean computeTargetVisible() {
        boolean shrinking;
        boolean hasContent;
        boolean bl = hasContent = this.shouldShow() && (this.activeCount > 0 || DragSystem.Companion.get().isDragModeActive());
        if (hasContent) {
            this.everHadContent = true;
            this.sizeCollapsedAtMs = 0L;
            return true;
        }
        if (!this.everHadContent) {
            return false;
        }
        boolean bl2 = shrinking = this.rowsProgress > 0.001f || this.dividerProgress() > 0.003921569f || this.currentHeight > 22.5f || Math.abs((double)this.widthAnimation.get() - this.widthAnimation.getToValue()) > 0.5;
        if (shrinking) {
            this.sizeCollapsedAtMs = 0L;
            return true;
        }
        if (this.sizeCollapsedAtMs == 0L) {
            this.sizeCollapsedAtMs = System.currentTimeMillis();
        }
        return System.currentTimeMillis() - this.sizeCollapsedAtMs < 100L;
    }

    /*
     * WARNING - void declaration
     */
    private final void syncRows() {
        List<Row> current = this.shouldShow() ? this.collectRows() : CollectionsKt.emptyList();
        for (RowState state : this.rows.values()) {
            state.setActive(false);
        }
        this.activeCount = 0;
        for (Row row : current) {
            Object rowId = row.id();
            if (rowId == null) continue;
            RowState state = this.rows.computeIfAbsent(rowId, k -> new RowState());
            state.setActive(true);
            String string = row.name();
            if (string == null) {
                string = "";
            }
            state.setName(string);
            String string2 = row.value();
            if (string2 == null) {
                string2 = "";
            }
            state.setValue(string2);
            state.setIcon(row.icon());
            state.setPulse(row.pulse());
            state.setColor(row.color());
            this.activeCount++;
        }
        this.rowsProgress = 0.0f;
        Iterator<Map.Entry<Object, RowState>> iterator2 = this.rows.entrySet().iterator();
        while (iterator2.hasNext()) {
            RowState state = iterator2.next().getValue();
            if (state.getActive() != state.getLastActive()) {
                state.getAnimation().run(state.getActive() ? 1.0 : 0.0, state.getActive() ? 0.22 : 0.18, ROW_EASING, false);
                state.setLastActive(state.getActive());
            }
            state.getAnimation().update();
            float progress = ListHudComp.Companion.rowProgress(state);
            this.rowsProgress += progress;
            if (state.getActive() || !(progress <= 0.001f)) continue;
            iterator2.remove();
        }
        this.rowsProgress = ListHudComp.Companion.clamp(this.rowsProgress, 0.0f, 1.0f);
    }

    private final void refreshMetrics() {
        float width = this.headerMinWidth();
        float height = 22.0f;
        float dividerProgress = this.dividerProgress();
        if (dividerProgress > 0.001f) {
            height += this.contentTopPadding(dividerProgress);
        }
        Iterator<RowState> iterator = this.rows.values().iterator();
        while (iterator.hasNext()) {
            RowState state = (RowState) (iterator.next());
            float progress = ListHudComp.Companion.rowProgress(state);
            if (progress <= 0.001f && !state.getActive()) continue;
            float iconColumn = state.getIcon() != null ? this.iconSlotSize() + 4.0f : 0.0f;
            float nameWidth = Fonts.MEDIUM.msdfWidth(state.getName(), 6.5f);
            float valueWidth = Fonts.MEDIUM.msdfWidth(state.getValue(), 6.5f);
            width = Math.max(width, 18.0f + iconColumn + nameWidth + 10.0f + valueWidth);
            height += 11.5f * progress;
        }
        if (dividerProgress > 0.001f) {
            height += 5.0f * dividerProgress;
        }
        float targetWidth = Math.max(width, this.headerMinWidth());
        if (Math.abs(this.widthAnimation.getToValue() - (double)targetWidth) > 0.25) {
            this.widthAnimation.run(targetWidth, 0.24, ROW_EASING, false);
        }
        this.currentHeight = Math.max(22.0f, height);
    }

    private final float contentTopPadding(float progress) {
        if (HudHeaderRenderer.Companion.isNewMode()) {
            return 0.0f;
        }
        return 5.5f * progress;
    }

    private final void renderDivider(float x, float y, float alpha) {
        if (HudHeaderRenderer.Companion.isNewMode()) {
            return;
        }
        float dividerT = ListHudComp.Companion.smoothstep(this.dividerProgress());
        float maxWidth = Math.max(0.0f, this.currentWidth - 18.0f);
        float dividerWidth = maxWidth * dividerT;
        if (dividerT <= 0.003921569f || dividerWidth <= 0.25f) {
            return;
        }
        Render2D.rect(x + (this.currentWidth - dividerWidth) * 0.5f, y + 22.0f, dividerWidth, 0.5f, 0.0f, ColorEngine.multAlpha(-1997220937, alpha * dividerT));
    }

    private final void updateDividerAlpha() {
        boolean dividerVisible;
        boolean bl = dividerVisible = this.activeCount > 0;
        if (dividerVisible != this.lastDividerVisible) {
            this.dividerAlpha.run(dividerVisible ? 1.0 : 0.0, dividerVisible ? 0.24 : 0.14, ROW_EASING, false);
            this.lastDividerVisible = dividerVisible;
        }
        this.dividerAlpha.update();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void drawRows(DrawContext graphics, float x, float y, float alpha, float clipRadius) {
        float dividerProgress = this.dividerProgress();
        float rowY = y + 22.0f + this.contentTopPadding(dividerProgress);
        float[] rowReference = ListHudComp.Companion.textBounds(Fonts.MEDIUM, ROW_CENTER_REFERENCE, 6.5f);
        float rowTextCenterOffset = (rowReference[1] + rowReference[3]) * 0.5f;
        Iterator<RowState> iterator = this.rows.values().iterator();
        while (iterator.hasNext()) {
            float f;
            RowState state = (RowState) (iterator.next());
            float progress = ListHudComp.Companion.rowProgress(state);
            if (progress <= 0.001f) continue;
            if (state.getPulse() != null) {
                AlphaPulse alphaPulse = state.getPulse();
                Intrinsics.checkNotNull((Object)alphaPulse);
                f = ListHudComp.Companion.clamp(alphaPulse.alpha(), 0.0f, 1.0f);
            } else {
                f = 1.0f;
            }
            float pulse = f;
            float rowAlpha = alpha * pulse;
            float nameProgress = progress;
            float valueProgress = ListHudComp.Companion.delayedProgress(progress, 0.12f);
            float iconColumn = state.getIcon() != null ? this.iconSlotSize() + 4.0f : 0.0f;
            float valueWidth = Fonts.MEDIUM.msdfWidth(state.getValue(), 6.5f);
            float nameX = x + 9.0f + iconColumn - 7.0f * (1.0f - nameProgress);
            float valueX = x + this.currentWidth - 9.0f - valueWidth + 7.0f * (1.0f - valueProgress);
            float rowSlotHeight = 11.5f * progress;
            float rowCenterY = rowY + rowSlotHeight * 0.5f;
            float clipHeight = 15.5f * progress;
            float clipY = rowCenterY - clipHeight * 0.5f;
            float textY = rowCenterY - rowTextCenterOffset;
            if (ListHudComp.Companion.pushRoundedContentClip(graphics, x, y, this.currentWidth, this.currentHeight, clipRadius, clipY, clipHeight)) {
                try {
                    graphics.getMatrices().pushMatrix();
                    try {
                        graphics.getMatrices().translate(0.0f, rowCenterY);
                        graphics.getMatrices().scale(1.0f, progress);
                        graphics.getMatrices().translate(0.0f, -rowCenterY);
                        IconDrawer icon = state.getIcon();
                        if (icon != null) {
                            float iconY = rowCenterY - this.iconSlotSize() * 0.5f;
                            icon.draw(graphics, x + 9.0f, iconY, this.iconSlotSize(), rowAlpha * nameProgress);
                        }
                        int nameColor = state.getColor() != 0 ? state.getColor() : -3355444;
                        int valueColor = state.getColor() != 0 ? state.getColor() : -3355444;
                        ListHudComp.Companion.drawText(Fonts.MEDIUM, state.getName(), nameX, textY, 6.5f, nameColor, rowAlpha * nameProgress);
                        ListHudComp.Companion.drawText(Fonts.MEDIUM, state.getValue(), valueX, textY, 6.5f, valueColor, rowAlpha * valueProgress);
                    }
                    finally {
                        graphics.getMatrices().popMatrix();
                    }
                }
                finally {
                    Render2D.popScissor(graphics);
                }
            }
            rowY += rowSlotHeight;
        }
    }

    private final boolean shouldShow() {
        Module module = ModuleManager.Companion.get().get(this.moduleType);
        return module != null && module.isEnabled();
    }

    private final float dividerProgress() {
        return ListHudComp.Companion.clamp(this.dividerAlpha.get(), 0.0f, 1.0f);
    }

    private final void updateHeaderIcon() {
        boolean iconVisible;
        boolean bl = iconVisible = this.headerIconGlyph() != null && ListHudComp.Companion.hudIconsEnabled();
        if (iconVisible != this.lastHeaderIconVisible) {
            this.headerIconAnim.run(iconVisible ? 1.0 : 0.0, iconVisible ? 0.25 : 0.2, ROW_EASING, false);
            this.lastHeaderIconVisible = iconVisible;
        }
        this.headerIconAnim.update();
    }

    private final float headerIconProgress() {
        return ListHudComp.Companion.clamp(this.headerIconAnim.get(), 0.0f, 1.0f);
    }

    private final float headerIconWidth() {
        String string = this.headerIconGlyph();
        if (string == null) {
            return 0.0f;
        }
        String glyph = string;
        float[] bounds = ListHudComp.Companion.textBounds(Fonts.KIMIKO, glyph, 7.0f);
        return bounds[2] - bounds[0];
    }

    private final float headerMinWidth() {
        float base = this.titleWidth() + 10.0f;
        if (HudHeaderRenderer.Companion.isNewMode()) {
            return Math.max(base, this.newHeaderRenderer().minimumWidth());
        }
        float iconT = this.headerIconProgress();
        if (iconT <= 0.001f) {
            return base;
        }
        float[] titleBounds = ListHudComp.Companion.textBounds(Fonts.SEMIBOLD, this.title, 8.2f);
        float withIcon = 18.0f + (titleBounds[2] - titleBounds[0]) + 6.0f + this.headerIconWidth();
        return base + Math.max(0.0f, withIcon - base) * iconT;
    }

    private final HudHeaderRenderer newHeaderRenderer() {
        HudHeaderRenderer hudHeaderRenderer;
        String glyph = this.headerIconGlyph();
        HudHeaderRenderer cached = this.newHeaderRenderer;
        if (cached != null && Intrinsics.areEqual((Object)this.newHeaderGlyph, (Object)glyph)) {
            return cached;
        }
        HudHeaderRenderer it = hudHeaderRenderer = new HudHeaderRenderer(this.title, glyph, 8.2f);
        boolean bl = false;
        this.newHeaderGlyph = glyph;
        this.newHeaderRenderer = it;
        return hudHeaderRenderer;
    }

    private final float titleWidth() {
        if (this.titleWidthCache <= 0.0f) {
            this.titleWidthCache = Fonts.SEMIBOLD.msdfWidth(this.title, 8.2f);
        }
        return this.titleWidthCache;
    }

    private static final double ROW_EASING$lambda$0(double value) {
        return value * value * (3.0 - 2.0 * value);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u00e6\u0080\u0001\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006\u0005\u00c0\u0006\u0001"}, d2={"Lrtx/kimiko/api/drags/components/ListHudComp$AlphaPulse;", "", "", "alpha", "()F", "rtx.kimiko:kimiko"})
    public static interface AlphaPulse {
        public float alpha();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b,\n\u0002\u0010\u0006\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ'\u0010\u0012\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u001f\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJI\u0010\"\u001a\u00020!2\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\"\u0010#JO\u0010-\u001a\u00020\t2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b-\u0010.J7\u00101\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b1\u00102J\u001f\u00104\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u00062\u0006\u00103\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b4\u0010\u001cJ\u001f\u00107\u001a\u00020\u00062\u0006\u00105\u001a\u00020\u00062\u0006\u00106\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b7\u0010\u001cJ\u0017\u00108\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b8\u00109J'\u0010<\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010:\u001a\u00020\u00062\u0006\u0010;\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b<\u0010=R\u0014\u0010>\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u0010?R\u0014\u0010A\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bA\u0010?R\u0014\u0010B\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bB\u0010?R\u0014\u0010C\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bC\u0010DR\u0014\u0010E\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bE\u0010?R\u0014\u0010F\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bF\u0010?R\u0014\u0010G\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bG\u0010?R\u0014\u0010H\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bH\u0010?R\u0014\u0010I\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bI\u0010?R\u0014\u0010J\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bJ\u0010?R\u0014\u0010K\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bK\u0010?R\u0014\u0010L\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bL\u0010?R\u0014\u0010M\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bM\u0010?R\u0014\u0010N\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bN\u0010?R\u0014\u0010O\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bO\u0010?R\u0014\u0010P\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bP\u0010?R\u0014\u0010R\u001a\u00020Q8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bR\u0010SR\u0014\u0010T\u001a\u00020Q8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bT\u0010SR\u0014\u0010U\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bU\u0010?R\u0014\u0010V\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bV\u0010?R\u0014\u0010W\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bW\u0010?R\u0014\u0010X\u001a\u00020Q8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bX\u0010SR\u0014\u0010Y\u001a\u00020Q8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bY\u0010SR\u0014\u0010Z\u001a\u00020Q8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bZ\u0010SR\u0014\u0010[\u001a\u00020Q8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b[\u0010SR\u0014\u0010\\\u001a\u00020Q8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\\\u0010SR\u0014\u0010]\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b]\u0010?R\u0014\u0010^\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b^\u0010?R\u0014\u0010`\u001a\u00020_8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b`\u0010aR\u0014\u0010c\u001a\u00020b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bc\u0010dR\u0014\u0010e\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\be\u0010fR\u0014\u0010g\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bg\u0010fR\u0014\u0010h\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bh\u0010f\u00a8\u0006i"}, d2={"Lrtx/kimiko/api/drags/components/ListHudComp.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/drags/components/ListHudComp$RowState;", "state", "", "rowProgress", "(Lrtx/kimiko/api/drags/components/ListHudComp$RowState;)F", "", "hudIconsEnabled", "()Z", "Lrtx/kimiko/utils/render/fonts/Fonts;", "font", "", "text", "size", "", "textBounds", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;F)[F", "sampleX", "sampleY", "", "titleColor", "(FF)I", "value", "delay", "delayedProgress", "(FF)F", "x", "y", "color", "alpha", "", "drawText", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;FFFIF)V", "Lnet/minecraft/DrawContext;", "graphics", "rectX", "rectY", "rectW", "rectH", "radius", "contentY", "contentH", "pushRoundedContentClip", "(Lnet/minecraft/DrawContext;FFFFFFF)Z", "top", "bottom", "roundedHorizontalInset", "(FFFFF)F", "distanceFromCenter", "cornerInset", "width", "height", "clientRectRadius", "smoothstep", "(F)F", "min", "max", "clamp", "(FFF)F", "HEADER_HEIGHT", "F", "CONTENT_TOP", "PAD_X", "PAD_BOTTOM", "ROW_CENTER_REFERENCE", "Ljava/lang/String;", "ROW_HEIGHT", "TITLE_SIZE", "ROW_SIZE", "VALUE_GAP", "HEADER_EXTRA_WIDTH", "NAME_SLIDE", "VALUE_SLIDE", "ICON_SIZE", "ICON_GAP", "HEADER_ICON_SIZE", "HEADER_ICON_GAP", "HEADER_ICON_SLIDE", "", "HEADER_ICON_IN_SECONDS", "D", "HEADER_ICON_OUT_SECONDS", "RADIUS", "DIVIDER_HEIGHT", "ROW_CLIP_PAD_Y", "ROW_IN_SECONDS", "ROW_OUT_SECONDS", "WIDTH_SECONDS", "DIVIDER_IN_SECONDS", "DIVIDER_OUT_SECONDS", "MIN_TEXT_ALPHA", "MIN_DIVIDER_ALPHA", "", "FADE_OUT_DELAY_MS", "J", "Lrtx/kimiko/utils/animations/Easing;", "ROW_EASING", "Lrtx/kimiko/utils/animations/Easing;", "ROW_COLOR", "I", "VALUE_COLOR", "DIVIDER_COLOR", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float rowProgress(RowState state) {
            return this.clamp(state.getAnimation().get(), 0.0f, 1.0f);
        }

        private final boolean hudIconsEnabled() {
            InterfaceModule module = InterfaceModule.Companion.getInstance();
            return module != null && module.hudIcons.getValue();
        }

        private final float[] textBounds(Fonts font, String text, float size) {
            float[] bounds = font.msdfBounds(text, size);
            if (bounds != null && bounds.length >= 4 && bounds[3] - bounds[1] > 0.0f) {
                return bounds;
            }
            float[] fArray = new float[]{0.0f, 0.0f, font.msdfWidth(text, size), size};
            return fArray;
        }

        private final int titleColor(float sampleX, float sampleY) {
            InterfaceModule module = InterfaceModule.Companion.getInstance();
            int client = module == null ? -1 : module.clientPrimaryColorOpaqueAt(sampleX, sampleY);
            return ColorEngine.lerpColor(-1, client, 0.1f);
        }

        private final float delayedProgress(float value, float delay) {
            if (value <= delay) {
                return 0.0f;
            }
            return this.clamp((value - delay) / (1.0f - delay), 0.0f, 1.0f);
        }

        private final void drawText(Fonts font, String text, float x, float y, float size, int color, float alpha) {
            float a = this.clamp(alpha, 0.0f, 1.0f);
            if (a <= 0.003921569f || text == null || ((CharSequence)text).length() == 0) {
                return;
            }
            font.msdf(text, x, y, size, ColorEngine.multAlpha(color, a));
        }

        private final boolean pushRoundedContentClip(DrawContext graphics, float rectX, float rectY, float rectW, float rectH, float radius, float contentY, float contentH) {
            float top = Math.max(rectY, contentY);
            float bottom = Math.min(rectY + rectH, contentY + contentH);
            if (rectW <= 0.5f || rectH <= 0.5f || bottom <= top) {
                return false;
            }
            float inset = this.roundedHorizontalInset(rectY, rectH, radius, top, bottom);
            float clipX = rectX + inset;
            float clipW = rectW - inset * 2.0f;
            if (clipW <= 0.5f) {
                return false;
            }
            Render2D.pushScissor(graphics, clipX, top, clipW, bottom - top);
            return true;
        }

        private final float roundedHorizontalInset(float rectY, float rectH, float radius, float top, float bottom) {
            float r = Math.max(0.0f, Math.min(radius, rectH * 0.5f));
            if (r <= 0.0f) {
                return 0.0f;
            }
            float rectBottom = rectY + rectH;
            float inset = 0.0f;
            if (top < rectY + r) {
                inset = Math.max(inset, this.cornerInset(r, rectY + r - Math.max(rectY, top)));
            }
            if (bottom > rectBottom - r) {
                inset = Math.max(inset, this.cornerInset(r, Math.min(rectBottom, bottom) - (rectBottom - r)));
            }
            return inset;
        }

        private final float cornerInset(float radius, float distanceFromCenter) {
            float d = this.clamp(distanceFromCenter, 0.0f, radius);
            return radius - (float)Math.sqrt(Math.max(0.0f, radius * radius - d * d));
        }

        private final float clientRectRadius(float width, float height) {
            InterfaceModule module;
            InterfaceModule interfaceModule = module = InterfaceModule.Companion.getInstance();
            float configuredRadius = interfaceModule == null ? 7.0f : interfaceModule.rectCornerRadius.getFloat();
            return Math.max(0.0f, Math.min(configuredRadius, Math.min(width, height) * 0.5f));
        }

        private final float smoothstep(float value) {
            float t = this.clamp(value, 0.0f, 1.0f);
            return t * t * (3.0f - 2.0f * t);
        }

        private final float clamp(float value, float min, float max) {
            return Math.max(min, Math.min(max, value));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u00e6\u0080\u0001\u0018\u00002\u00020\u0001J7\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H&\u00a2\u0006\u0004\b\n\u0010\u000b\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006\f\u00c0\u0006\u0001"}, d2={"Lrtx/kimiko/api/drags/components/ListHudComp$IconDrawer;", "", "Lnet/minecraft/DrawContext;", "graphics", "", "x", "y", "size", "alpha", "", "draw", "(Lnet/minecraft/DrawContext;FFFF)V", "rtx.kimiko:kimiko"})
    public static interface IconDrawer {
        public void draw(@NotNull DrawContext var1, float var2, float var3, float var4, float var5);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001BK\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u001a\u0002\b\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0012J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\nH\u00c6\u0003\u00a2\u0006\u0004\b\u0018\u0010\u0019JV\u0010\u001a\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u000b\u001a\u00020\nH\u00c6\u0001\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001b\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0011\u0010 \u001a\u00020\nH\u00d6\u0081\u0004\u00a2\u0006\u0004\b \u0010\u0019J\u0011\u0010!\u001a\u00020\u0003H\u00d6\u0081\u0004\u00a2\u0006\u0004\b!\u0010\u0012R'\u0010\u0002\u001a\u0004\u0018\u00010\u00018\u0007z\f\b\"\u0012\b\b\u0004\u0012\u0004\b\b(\u0002\u00a2\u0006\f\n\u0004\b\u0002\u0010#\u001a\u0004\b\u0002\u0010\u0010R'\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0007z\f\b\"\u0012\b\b\u0004\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010$\u001a\u0004\b\u0004\u0010\u0012R'\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0007z\f\b\"\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010$\u001a\u0004\b\u0005\u0010\u0012R'\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0007z\f\b\"\u0012\b\b\u0004\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010%\u001a\u0004\b\u0007\u0010\u0015R'\u0010\t\u001a\u0004\u0018\u00010\b8\u0007z\f\b\"\u0012\b\b\u0004\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010&\u001a\u0004\b\t\u0010\u0017R%\u0010\u000b\u001a\u00020\n8\u0007z\f\b\"\u0012\b\b\u0004\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010'\u001a\u0004\b\u000b\u0010\u0019\u00a8\u0006("}, d2={"Lrtx/kimiko/api/drags/components/ListHudComp$Row;", "", "id", "", "name", "value", "Lrtx/kimiko/api/drags/components/ListHudComp$IconDrawer;", "icon", "Lrtx/kimiko/api/drags/components/ListHudComp$AlphaPulse;", "pulse", "", "color", "Lkotlin/jvm/JvmOverloads;", "<init>", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Lrtx/kimiko/api/drags/components/ListHudComp$IconDrawer;Lrtx/kimiko/api/drags/components/ListHudComp$AlphaPulse;I)V", "component1", "()Ljava/lang/Object;", "component2", "()Ljava/lang/String;", "component3", "component4", "()Lrtx/kimiko/api/drags/components/ListHudComp$IconDrawer;", "component5", "()Lrtx/kimiko/api/drags/components/ListHudComp$AlphaPulse;", "component6", "()I", "copy", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Lrtx/kimiko/api/drags/components/ListHudComp$IconDrawer;Lrtx/kimiko/api/drags/components/ListHudComp$AlphaPulse;I)Lrtx/kimiko/api/drags/components/ListHudComp$Row;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "Lkotlin/jvm/JvmName;", "Ljava/lang/Object;", "Ljava/lang/String;", "Lrtx/kimiko/api/drags/components/ListHudComp$IconDrawer;", "Lrtx/kimiko/api/drags/components/ListHudComp$AlphaPulse;", "I", "rtx.kimiko:kimiko"})
    public static final class Row {
        @Nullable
        private final Object id;
        @Nullable
        private final String name;
        @Nullable
        private final String value;
        @Nullable
        private final IconDrawer icon;
        @Nullable
        private final AlphaPulse pulse;
        private final int color;

        @JvmOverloads
        public Row(@Nullable Object id, @Nullable String name, @Nullable String value, @Nullable IconDrawer icon, @Nullable AlphaPulse pulse, int color) {
            this.id = id;
            this.name = name;
            this.value = value;
            this.icon = icon;
            this.pulse = pulse;
            this.color = color;
        }

        public /* synthetic */ Row(Object object, String string, String string2, IconDrawer iconDrawer, AlphaPulse alphaPulse, int n, int n2, DefaultConstructorMarker defaultConstructorMarker) {
            this(object, string, string2, iconDrawer, ((n2 & 0x10) != 0 ? null : alphaPulse), ((n2 & 0x20) != 0 ? 0 : n));
        }

        @JvmName(name="id")
        @Nullable
        public final Object id() {
            return this.id;
        }

        @JvmName(name="name")
        @Nullable
        public final String name() {
            return this.name;
        }

        @JvmName(name="value")
        @Nullable
        public final String value() {
            return this.value;
        }

        @JvmName(name="icon")
        @Nullable
        public final IconDrawer icon() {
            return this.icon;
        }

        @JvmName(name="pulse")
        @Nullable
        public final AlphaPulse pulse() {
            return this.pulse;
        }

        @JvmName(name="color")
        public final int color() {
            return this.color;
        }

        @Nullable
        public final Object component1() {
            return this.id;
        }

        @Nullable
        public final String component2() {
            return this.name;
        }

        @Nullable
        public final String component3() {
            return this.value;
        }

        @Nullable
        public final IconDrawer component4() {
            return this.icon;
        }

        @Nullable
        public final AlphaPulse component5() {
            return this.pulse;
        }

        public final int component6() {
            return this.color;
        }

        @NotNull
        public final Row copy(@Nullable Object id, @Nullable String name, @Nullable String value, @Nullable IconDrawer icon, @Nullable AlphaPulse pulse, int color) {
            return new Row(id, name, value, icon, pulse, color);
        }

        public static /* synthetic */ Row copy$default(Row row, Object object, String string, String string2, IconDrawer iconDrawer, AlphaPulse alphaPulse, int n, int n2, Object object2) {
            if ((n2 & 1) != 0) {
                object = row.id;
            }
            if ((n2 & 2) != 0) {
                string = row.name;
            }
            if ((n2 & 4) != 0) {
                string2 = row.value;
            }
            if ((n2 & 8) != 0) {
                iconDrawer = row.icon;
            }
            if ((n2 & 0x10) != 0) {
                alphaPulse = row.pulse;
            }
            if ((n2 & 0x20) != 0) {
                n = row.color;
            }
            return row.copy(object, string, string2, iconDrawer, alphaPulse, n);
        }

        @NotNull
        public String toString() {
            return "Row(id=" + this.id + ", name=" + this.name + ", value=" + this.value + ", icon=" + this.icon + ", pulse=" + this.pulse + ", color=" + this.color + ")";
        }

        public int hashCode() {
            int result = this.id == null ? 0 : this.id.hashCode();
            result = result * 31 + (this.name == null ? 0 : this.name.hashCode());
            result = result * 31 + (this.value == null ? 0 : this.value.hashCode());
            result = result * 31 + (this.icon == null ? 0 : this.icon.hashCode());
            result = result * 31 + (this.pulse == null ? 0 : this.pulse.hashCode());
            result = result * 31 + Integer.hashCode(this.color);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Row)) {
                return false;
            }
            Row row = (Row)other;
            if (!Intrinsics.areEqual((Object)this.id, (Object)row.id)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.name, (Object)row.name)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.value, (Object)row.value)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.icon, (Object)row.icon)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.pulse, (Object)row.pulse)) {
                return false;
            }
            return this.color == row.color;
        }

        @JvmOverloads
        public Row(@Nullable Object id, @Nullable String name, @Nullable String value, @Nullable IconDrawer icon, @Nullable AlphaPulse pulse) {
            this(id, name, value, icon, pulse, 0, 32, null);
        }

        @JvmOverloads
        public Row(@Nullable Object id, @Nullable String name, @Nullable String value, @Nullable IconDrawer icon) {
            this(id, name, value, icon, null, 0, 48, null);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\"\u0010\n\u001a\u00020\t8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0010\u001a\u00020\t8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\"\u0010\u0014\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\"\u0010\u001a\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001a\u0010\u0015\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R$\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R$\u0010%\u001a\u0004\u0018\u00010$8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\"\u0010,\u001a\u00020+8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101\u00a8\u00062"}, d2={"Lrtx/kimiko/api/drags/components/ListHudComp$RowState;", "", "<init>", "()V", "Lrtx/kimiko/utils/animations/SmoothAnimation;", "animation", "Lrtx/kimiko/utils/animations/SmoothAnimation;", "getAnimation", "()Lrtx/kimiko/utils/animations/SmoothAnimation;", "", "active", "Z", "getActive", "()Z", "setActive", "(Z)V", "lastActive", "getLastActive", "setLastActive", "", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "value", "getValue", "setValue", "Lrtx/kimiko/api/drags/components/ListHudComp$IconDrawer;", "icon", "Lrtx/kimiko/api/drags/components/ListHudComp$IconDrawer;", "getIcon", "()Lrtx/kimiko/api/drags/components/ListHudComp$IconDrawer;", "setIcon", "(Lrtx/kimiko/api/drags/components/ListHudComp$IconDrawer;)V", "Lrtx/kimiko/api/drags/components/ListHudComp$AlphaPulse;", "pulse", "Lrtx/kimiko/api/drags/components/ListHudComp$AlphaPulse;", "getPulse", "()Lrtx/kimiko/api/drags/components/ListHudComp$AlphaPulse;", "setPulse", "(Lrtx/kimiko/api/drags/components/ListHudComp$AlphaPulse;)V", "", "color", "I", "getColor", "()I", "setColor", "(I)V", "rtx.kimiko:kimiko"})
    private static final class RowState {
        @NotNull
        private final SmoothAnimation animation = new SmoothAnimation();
        private boolean active;
        private boolean lastActive;
        @NotNull
        private String name = "";
        @NotNull
        private String value = "";
        @Nullable
        private IconDrawer icon;
        @Nullable
        private AlphaPulse pulse;
        private int color;

        public RowState() {
            this.animation.set(0.0);
        }

        @NotNull
        public final SmoothAnimation getAnimation() {
            return this.animation;
        }

        public final boolean getActive() {
            return this.active;
        }

        public final void setActive(boolean bl) {
            this.active = bl;
        }

        public final boolean getLastActive() {
            return this.lastActive;
        }

        public final void setLastActive(boolean bl) {
            this.lastActive = bl;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        public final void setName(@NotNull String string) {
            Intrinsics.checkNotNullParameter((Object)string, (String)"<set-?>");
            this.name = string;
        }

        @NotNull
        public final String getValue() {
            return this.value;
        }

        public final void setValue(@NotNull String string) {
            Intrinsics.checkNotNullParameter((Object)string, (String)"<set-?>");
            this.value = string;
        }

        @Nullable
        public final IconDrawer getIcon() {
            return this.icon;
        }

        public final void setIcon(@Nullable IconDrawer iconDrawer) {
            this.icon = iconDrawer;
        }

        @Nullable
        public final AlphaPulse getPulse() {
            return this.pulse;
        }

        public final void setPulse(@Nullable AlphaPulse alphaPulse) {
            this.pulse = alphaPulse;
        }

        public final int getColor() {
            return this.color;
        }

        public final void setColor(int n) {
            this.color = n;
        }
    }
}

