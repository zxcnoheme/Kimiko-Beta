/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  kotlin.Metadata
 *  kotlin.NoWhenBranchMatchedException
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.math.MathKt
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.drags.components;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.drags.Draggable;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.ArrayListModule;
import rtx.kimiko.api.modules.impl.Interface.ClickGui;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.animations.Easing;
import rtx.kimiko.utils.animations.HudFadeAnimation;
import rtx.kimiko.utils.animations.SmoothAnimation;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00a2\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 f2\u00020\u0001:\u0001fB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\t\u0010\bJ\u000f\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0012\u001a\u00020\u0011H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0014H\u0014\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0013J\u000f\u0010\u0019\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u0013J\u000f\u0010\u001a\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u0003J\u001d\u0010\u001e\u001a\u00020\u00062\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010!\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b!\u0010\"J\u0015\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001c0#H\u0002\u00a2\u0006\u0004\b$\u0010%J\u000f\u0010&\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b&\u0010\u0003J\u0017\u0010'\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b'\u0010(J'\u0010-\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00112\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b-\u0010.J\u0017\u00101\u001a\u00020\r2\u0006\u00100\u001a\u00020/H\u0002\u00a2\u0006\u0004\b1\u00102J\u0017\u00104\u001a\u00020\r2\u0006\u00103\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b4\u0010(JQ\u0010<\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00142\b\u00106\u001a\u0004\u0018\u0001052\u0006\u00107\u001a\u00020\r2\u0006\u00108\u001a\u00020\r2\u0006\u00103\u001a\u00020\r2\u0006\u00109\u001a\u00020\r2\u0006\u0010:\u001a\u00020/2\u0006\u0010;\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b<\u0010=J\u0017\u0010>\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b>\u0010\"J\u000f\u0010?\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b?\u0010\u0013R \u0010B\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020A0@8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010CR \u0010D\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00110@8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u0010CR$\u0010G\u001a\u0012\u0012\u0004\u0012\u00020\u001c0Ej\b\u0012\u0004\u0012\u00020\u001c`F8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010HR \u0010I\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\r0@8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u0010CR\u0014\u0010K\u001a\u00020J8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bK\u0010LR\u0016\u0010M\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bM\u0010NR\u0016\u0010O\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bO\u0010NR\u0016\u0010Q\u001a\u00020P8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bQ\u0010RR\u0016\u0010S\u001a\u00020P8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bS\u0010RR\u0016\u0010T\u001a\u00020/8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bT\u0010UR\u0016\u0010V\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010WR\u0016\u0010X\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bX\u0010WR\u0016\u0010Z\u001a\u00020Y8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bZ\u0010[R\u0016\u0010\\\u001a\u00020*8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\\\u0010]R\u0016\u0010^\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b^\u0010NR\u0016\u0010_\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b_\u0010NR\u0016\u0010`\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b`\u0010NR\u0016\u0010a\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\ba\u0010WR$\u0010d\u001a\u0012\u0012\u0004\u0012\u00020\u001c0bj\b\u0012\u0004\u0012\u00020\u001c`c8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bd\u0010e\u00a8\u0006g"}, d2={"Lrtx/kimiko/api/drags/components/ArrayListComp;", "Lrtx/kimiko/api/drags/Draggable;", "<init>", "()V", "Lcom/google/gson/JsonObject;", "state", "", "writeState", "(Lcom/google/gson/JsonObject;)V", "readState", "", "displayName", "()Ljava/lang/String;", "", "width", "()F", "height", "", "isInteractive", "()Z", "Lnet/minecraft/DrawContext;", "graphics", "render", "(Lnet/minecraft/DrawContext;)V", "isRightHalf", "isBottomHalf", "updateOrientation", "", "Lrtx/kimiko/api/modules/Module;", "rows", "refreshMetrics", "(Ljava/util/List;)V", "module", "moduleWidth", "(Lrtx/kimiko/api/modules/Module;)F", "", "updateRows", "()Ljava/util/List;", "keepRightDock", "rightDockX", "(F)F", "wanted", "", "dt", "resumed", "updateIconReveal", "(ZJZ)V", "", "index", "iconProgress", "(I)F", "progress", "iconSlot", "Lrtx/kimiko/api/modules/Category;", "category", "centerX", "centerY", "outward", "base", "wave", "drawCategoryIcon", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/api/modules/Category;FFFFIF)V", "rowProgress", "shouldShow", "", "Lrtx/kimiko/utils/animations/SmoothAnimation;", "rowAnimations", "Ljava/util/Map;", "rowTargets", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "rowBuffer", "Ljava/util/ArrayList;", "moduleWidthCache", "Lrtx/kimiko/utils/animations/HudFadeAnimation;", "visibility", "Lrtx/kimiko/utils/animations/HudFadeAnimation;", "currentWidth", "F", "currentHeight", "", "rowWidths", "[F", "rowHeights", "rowCountMetric", "I", "leftAlignedState", "Z", "bottomAnchoredState", "", "wavePhase", "D", "lastWaveNow", "J", "iconHead", "lastPinnedHeight", "lastPinnedWidth", "dockedRight", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "widthDescending", "Ljava/util/Comparator;", "Companion", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nArrayListComp.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ArrayListComp.kt\nrtx/kimiko/api/drags/components/ArrayListComp\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,488:1\n460#2,7:489\n*S KotlinDebug\n*F\n+ 1 ArrayListComp.kt\nrtx/kimiko/api/drags/components/ArrayListComp\n*L\n246#1:489,7\n*E\n"})
public final class ArrayListComp
extends Draggable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Map<Module, SmoothAnimation> rowAnimations = new IdentityHashMap();
    @NotNull
    private final Map<Module, Boolean> rowTargets = new IdentityHashMap();
    @NotNull
    private final ArrayList<Module> rowBuffer = new ArrayList();
    @NotNull
    private final Map<Module, Float> moduleWidthCache = new IdentityHashMap();
    @NotNull
    private final HudFadeAnimation visibility = this.getHudFade();
    private float currentWidth = 1.0f;
    private float currentHeight = 9.7f;
    @NotNull
    private float[] rowWidths = new float[0];
    @NotNull
    private float[] rowHeights = new float[0];
    private int rowCountMetric;
    private boolean leftAlignedState = true;
    private boolean bottomAnchoredState;
    private double wavePhase;
    private long lastWaveNow;
    private float iconHead;
    private float lastPinnedHeight = Float.NaN;
    private float lastPinnedWidth = Float.NaN;
    private boolean dockedRight;
    @NotNull
    private final Comparator<Module> widthDescending;
    private static final float ROW_SIZE = 7.2f;
    private static final float ROW_HEIGHT = 8.7f;
    private static final float PAD_X = 1.5f;
    private static final float PAD_Y_UP = 0.0f;
    private static final float PAD_Y_DOWN = 1.0f;
    private static final float RADIUS = 3.0f;
    private static final float MIN_WIDTH = 1.0f;
    private static final double ROW_IN_SECONDS = 0.22;
    private static final double ROW_OUT_SECONDS = 0.16;
    private static final float MIN_TEXT_ALPHA = 0.003921569f;
    private static final float ICON_SIZE = 6.4f;
    private static final float ICON_GAP = 3.0f;
    private static final float ICON_SLIDE = 6.5f;
    private static final float ICON_SPIN = 170.0f;
    private static final float ICON_MIN_SCALE = 0.15f;
    private static final float ICON_BREATH = 0.07f;
    private static final float ICON_STAGGER = 0.22f;
    private static final long RENDER_RESUME_GAP_MS = 250L;
    private static final float DOCK_EPSILON = 1.5f;
    private static final float ICON_IN_SECONDS = 0.34f;
    private static final float ICON_OUT_SECONDS = 0.22f;
    private static final float WAVE_FREQ = 0.05f;
    private static final float RAINBOW_WAVE_HUE_SHIFT = 0.1f;
    @NotNull
    private static final Easing ROW_EASING = ArrayListComp::ROW_EASING$lambda$0;
    private static float iconAdvanceCache;

    public ArrayListComp() {
        super("arraylist", 888.0f, 5.0f);
        this.visibility.set(0.0);
        this.widthDescending = (arg_0, arg_1) -> ArrayListComp.widthDescending$lambda$0(this, arg_0, arg_1);
    }

    @Override
    public void writeState(@NotNull JsonObject state) {
        Intrinsics.checkNotNullParameter((Object)state, (String)"state");
        state.addProperty("dockRight", Boolean.valueOf(this.dockedRight));
    }

    @Override
    public void readState(@NotNull JsonObject state) {
        Intrinsics.checkNotNullParameter((Object)state, (String)"state");
        if (state.has("dockRight")) {
            this.dockedRight = state.get("dockRight").getAsBoolean();
        }
    }

    @Override
    @NotNull
    public String displayName() {
        return "Array List";
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

    @Override
    protected void render(@NotNull DrawContext graphics) {
        float heightDelta;
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        this.updateOrientation();
        boolean bottomAnchored = this.bottomAnchoredState;
        List<Module> rows = this.updateRows();
        if (bottomAnchored) {
            CollectionsKt.reverse(rows);
        }
        ArrayListModule alm = ModuleManager.Companion.get().get(ArrayListModule.class);
        long now = System.currentTimeMillis();
        long gap = this.lastWaveNow == 0L ? Long.MAX_VALUE : now - this.lastWaveNow;
        long dt = this.lastWaveNow == 0L ? 0L : Math.max(0L, Math.min(100L, gap));
        this.lastWaveNow = now;
        ArrayListModule arrayListModule = alm;
        double waveSpeed = arrayListModule == null ? 0.0 : arrayListModule.waveSpeed();
        this.wavePhase = (this.wavePhase + (double)dt * waveSpeed) % (Math.PI * 2);
        float phase = (float)this.wavePhase;
        this.updateIconReveal(alm != null && alm.categoryIcons(), dt, gap > 250L);
        this.refreshMetrics(rows);
        if (!Float.isNaN(this.lastPinnedHeight) && bottomAnchored && !this.getDrag().isDragging() && Math.abs(heightDelta = this.currentHeight - this.lastPinnedHeight) > 1.0E-4f) {
            this.getDrag().adjustY(-heightDelta);
            this.getDrag().syncToTarget();
            this.getDrag().shiftRenderLock$rtx_kimiko_kimiko(0.0f, -heightDelta);
        }
        this.lastPinnedHeight = this.currentHeight;
        this.keepRightDock();
        boolean targetVisible = this.shouldShow() && !((Collection)rows).isEmpty();
        this.visibility.updateTarget(targetVisible);
        float alpha = this.visibility.get();
        if (targetVisible && alpha <= 0.01f) {
            alpha = 0.01f;
        }
        if (alpha <= 0.01f && !targetVisible) {
            return;
        }
        float x = this.getX();
        float y = this.getY();
        boolean leftAligned = this.leftAlignedState;
        float scale = 0.92f + alpha * 0.08f;
        float originX = x + this.currentWidth * 0.5f;
        float originY = y + this.currentHeight * 0.5f;
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(originX, originY);
        graphics.getMatrices().scale(scale);
        graphics.getMatrices().translate(-originX, -originY);
        Render2D.beginFrame(graphics);
        float plateCenterX = x + this.currentWidth * 0.5f;
        float plateCenterY = y + this.currentHeight * 0.5f;
        InterfaceModule im = InterfaceModule.Companion.getInstance();
        int waveColorA = 0;
        int waveColorB = 0;
        if (im != null && im.isRainbowClientColor()) {
            waveColorA = ClientAccent.rainbowFlow(0.0f, 255.0f);
            waveColorB = ClientAccent.rainbowFlow(-0.1f, 255.0f);
        } else {
            int colB;
            int colA = ClientAccent.gradientAAt(255.0f, plateCenterX, plateCenterY);
            if ((colA & 0xFFFFFF) == ((colB = ClientAccent.gradientBAt(255.0f, plateCenterX, plateCenterY)) & 0xFFFFFF)) {
                colB = ArrayListComp.Companion.mixWhite(colA, 0.3f);
            }
            waveColorA = colA;
            waveColorB = colB;
        }
        if (this.rowCountMetric > 0) {
            RectUtil.drawClientShape(x, y, this.rowWidths, this.rowHeights, this.rowCountMetric, 0.0f, 1.0f, 3.0f, alpha, leftAligned, bottomAnchored, 0.05f, phase, waveColorA, waveColorB);
        } else {
            RectUtil.drawClientRect(x, y, this.currentWidth, this.currentHeight, 3.0f, alpha);
        }
        int drawn = 0;
        float rowY = y + 0.0f;
        for (Module module : rows) {
            float progress = this.rowProgress(module);
            if (progress <= 0.001f) continue;
            float rowH = 8.7f * progress;
            float iconT = this.iconProgress(drawn);
            float iconWidth = this.iconSlot(iconT);
            float textWidth = Fonts.MEDIUM.msdfWidth(module.getName(), 7.2f);
            float textX = leftAligned ? x + 1.5f + iconWidth : x + this.currentWidth - 1.5f - iconWidth - textWidth;
            float textY = rowY + (rowH - 7.2f) * 0.5f - 0.3f;
            float rowAlpha = ArrayListComp.Companion.clamp(alpha * progress, 0.0f, 1.0f);
            float wave = 0.5f + 0.5f * (float)Math.sin((rowY + rowH * 0.5f) * 0.05f - phase);
            int base = ColorEngine.multAlpha(ClientAccent.mix(waveColorA, waveColorB, wave), rowAlpha);
            ArrayListComp.Companion.drawGradientText(module.getName(), textX, textY, rowAlpha, base);
            if (iconT > 0.001f) {
                float iconCenterX = leftAligned ? x + 1.5f + ArrayListComp.Companion.iconAdvance() * 0.5f : x + this.currentWidth - 1.5f - ArrayListComp.Companion.iconAdvance() * 0.5f;
                this.drawCategoryIcon(graphics, module.getCategory(), iconCenterX, rowY + rowH * 0.5f, iconT, leftAligned ? -1.0f : 1.0f, base, wave);
            }
            ++drawn;
            rowY += rowH;
        }
        Render2D.flush();
        graphics.getMatrices().popMatrix();
    }

    private final boolean isRightHalf() {
        float guiWidth = Math.max(1.0f, Position.Companion.screenWidth());
        return this.getX() + this.currentWidth * 0.5f > guiWidth * 0.5f;
    }

    private final boolean isBottomHalf() {
        float guiHeight = Math.max(1.0f, Position.Companion.screenHeight());
        return this.getY() + this.currentHeight * 0.5f > guiHeight * 0.5f;
    }

    private final void updateOrientation() {
        this.leftAlignedState = !this.isRightHalf();
        this.bottomAnchoredState = this.isBottomHalf();
    }

    private final void refreshMetrics(List<? extends Module> rows) {
        if (this.rowWidths.length < rows.size()) {
            this.rowWidths = new float[rows.size()];
            this.rowHeights = new float[rows.size()];
        }
        float width = 0.0f;
        float height = 0.0f;
        int idx = 0;
        for (Module module : rows) {
            float progress = this.rowProgress(module);
            if (progress <= 0.001f) continue;
            float rowW = this.moduleWidth(module) + this.iconSlot(this.iconProgress(idx)) + 3.0f;
            float rowH = 8.7f * progress;
            width = Math.max(width, rowW);
            height += rowH;
            this.rowWidths[idx] = rowW;
            this.rowHeights[idx] = rowH;
            ++idx;
        }
        this.rowCountMetric = idx;
        this.currentWidth = Math.max(width, 1.0f);
        this.currentHeight = height + 0.0f + 1.0f;
    }

    private final float moduleWidth(Module module) {
        Float f = this.moduleWidthCache.computeIfAbsent(module, m -> Fonts.MEDIUM.msdfWidth(m.getName(), 7.2f));
        return f != null ? f.floatValue() : 0.0f;
    }

    /*
     * WARNING - void declaration
     */
    private final List<Module> updateRows() {
        this.rowBuffer.clear();
        for (Module module : ModuleManager.Companion.get().getAll()) {
            Object object;
Map $this$getOrPut$iv = this.rowAnimations;
            boolean on = ArrayListComp.Companion.isListed(module);
            Map<Module, SmoothAnimation> map = this.rowAnimations;
            Module key$iv = module;
            boolean $i$f$getOrPut = false;
            Object value$iv = $this$getOrPut$iv.get(key$iv);
            if (value$iv == null) {
                boolean bl = false;
                SmoothAnimation created = new SmoothAnimation();
                created.set(0.0);
                SmoothAnimation answer$iv = created;
                $this$getOrPut$iv.put(key$iv, answer$iv);
                object = answer$iv;
            } else {
                object = value$iv;
            }
            SmoothAnimation animation = (SmoothAnimation)object;
            Boolean previous = this.rowTargets.get(module);
            if (previous == null || !Intrinsics.areEqual((Object)previous, (Object)on)) {
                animation.run(on ? 1.0 : 0.0, on ? 0.22 : 0.16, ROW_EASING, false);
                this.rowTargets.put(module, on);
            }
            animation.update();
            if (ArrayListComp.Companion.isExcluded(module) || !(ArrayListComp.Companion.clamp(animation.get(), 0.0f, 1.0f) > 0.001f)) continue;
            this.rowBuffer.add(module);
        }
        CollectionsKt.sortWith((List)this.rowBuffer, this.widthDescending);
        return this.rowBuffer;
    }

    private final void keepRightDock() {
        float delta;
        if (this.getDrag().isDragging()) {
            this.lastPinnedWidth = this.currentWidth;
            this.dockedRight = false;
            return;
        }
        float targetX = this.getDrag().getTargetX();
        float dockX = this.rightDockX(this.currentWidth);
        if (!this.dockedRight) {
            boolean bl = this.dockedRight = Math.abs(targetX - dockX) <= 1.5f || !Float.isNaN(this.lastPinnedWidth) && Math.abs(targetX - this.rightDockX(this.lastPinnedWidth)) <= 1.5f;
        }
        if (this.dockedRight && Math.abs(delta = dockX - targetX) > 1.0E-4f) {
            this.getDrag().setTargetX(dockX);
            this.getDrag().syncToTarget();
            this.getDrag().shiftRenderLock$rtx_kimiko_kimiko(delta, 0.0f);
        }
        this.lastPinnedWidth = this.currentWidth;
    }

    private final float rightDockX(float width) {
        return Position.Companion.clampX(Float.MAX_VALUE, width * this.getScale(), this.screenMargin());
    }

    private final void updateIconReveal(boolean wanted, long dt, boolean resumed) {
        float target = 1.0f + 0.22f * (float)Math.max(0, this.rowCountMetric - 1);
        if (resumed || this.visibility.get() < 1.0f) {
            this.iconHead = wanted ? target : 0.0f;
            return;
        }
        float step = (float)dt / 1000.0f / (wanted ? 0.34f : 0.22f);
        this.iconHead = ArrayListComp.Companion.clamp(wanted ? this.iconHead + step : this.iconHead - step, 0.0f, target);
    }

    private final float iconProgress(int index) {
        return ArrayListComp.Companion.clamp(this.iconHead - (float)index * 0.22f, 0.0f, 1.0f);
    }

    private final float iconSlot(float progress) {
        if (progress <= 0.001f) {
            return 0.0f;
        }
        return (ArrayListComp.Companion.iconAdvance() + 3.0f) * ArrayListComp.Companion.smoothstep(progress);
    }

    private final void drawCategoryIcon(DrawContext graphics, Category category, float centerX, float centerY, float progress, float outward, int base, float wave) {
        String string = ArrayListComp.Companion.iconGlyph(category);
        if (string == null) {
            return;
        }
        String glyph = string;
        float appear = ArrayListComp.Companion.smoothstep(ArrayListComp.Companion.clamp(progress * 1.6f, 0.0f, 1.0f));
        if (appear <= 0.003921569f) {
            return;
        }
        float ease = ArrayListComp.Companion.easeOutBack(progress);
        float settle = ArrayListComp.Companion.smoothstep(ArrayListComp.Companion.clamp((progress - 0.55f) / 0.45f, 0.0f, 1.0f));
        float iconX = centerX + outward * 6.5f * (1.0f - ease);
        float spin = 170.0f * (1.0f - ease) * outward;
        float scale = 0.15f + 0.85f * ease + 0.07f * wave * settle;
        float[] bounds = Fonts.KIMIKO.msdfBounds(glyph, 6.4f);
        float drawX = 0.0f;
        float drawY = 0.0f;
        if (bounds.length >= 4 && bounds[3] - bounds[1] > 0.0f) {
            drawX = iconX - (bounds[0] + bounds[2]) * 0.5f;
            drawY = centerY - (bounds[1] + bounds[3]) * 0.5f;
        } else {
            drawX = iconX - Fonts.KIMIKO.msdfWidth(glyph, 6.4f) * 0.5f;
            drawY = centerY - 3.2f;
        }
        float echo = 1.0f - progress;
        if (echo > 0.01f) {
            ArrayListComp.Companion.drawGlyph(graphics, glyph, drawX, drawY, iconX, centerY, spin * 0.45f, scale * (1.0f + 0.9f * echo), ColorEngine.multAlpha(base, appear * echo * 0.3f));
        }
        ArrayListComp.Companion.drawGlyph(graphics, glyph, drawX, drawY, iconX, centerY, spin, scale, ColorEngine.multAlpha(base, appear));
    }

    private final float rowProgress(Module module) {
        SmoothAnimation animation;
        SmoothAnimation smoothAnimation = animation = this.rowAnimations.get(module);
        return smoothAnimation == null ? 0.0f : ArrayListComp.Companion.clamp(smoothAnimation.get(), 0.0f, 1.0f);
    }

    private final boolean shouldShow() {
        ArrayListModule module = ModuleManager.Companion.get().get(ArrayListModule.class);
        return module != null && module.isEnabled();
    }


    private static final int widthDescending$lambda$0(ArrayListComp this$0, Module a, Module b) {
        Intrinsics.checkNotNull((Object)b);
        float f = this$0.moduleWidth(b);
        Intrinsics.checkNotNull((Object)a);
        return Float.compare(f, this$0.moduleWidth(a));
    }

    private static final double ROW_EASING$lambda$0(double value) {
        return value * value * (3.0 - 2.0 * value);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\t\u0010\bJ9\u0010\u0013\u001a\u00020\u00122\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u0019\u001a\u0004\u0018\u00010\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJW\u0010%\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\f2\u0006\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b%\u0010&J\u0017\u0010(\u001a\u00020\f2\u0006\u0010'\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b(\u0010)J\u0017\u0010*\u001a\u00020\f2\u0006\u0010'\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b*\u0010)J\u001f\u0010,\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u00102\u0006\u0010+\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b,\u0010-J'\u00100\u001a\u00020\f2\u0006\u0010'\u001a\u00020\f2\u0006\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b0\u00101R\u0014\u00102\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0014\u00104\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u00103R\u0014\u00105\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b5\u00103R\u0014\u00106\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b6\u00103R\u0014\u00107\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b7\u00103R\u0014\u00108\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b8\u00103R\u0014\u00109\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b9\u00103R\u0014\u0010;\u001a\u00020:8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b;\u0010<R\u0014\u0010=\u001a\u00020:8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b=\u0010<R\u0014\u0010>\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b>\u00103R\u0014\u0010?\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b?\u00103R\u0014\u0010@\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u00103R\u0014\u0010A\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bA\u00103R\u0014\u0010B\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bB\u00103R\u0014\u0010C\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bC\u00103R\u0014\u0010D\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bD\u00103R\u0014\u0010E\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bE\u00103R\u0014\u0010G\u001a\u00020F8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bG\u0010HR\u0014\u0010I\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bI\u00103R\u0014\u0010J\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bJ\u00103R\u0014\u0010K\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bK\u00103R\u0014\u0010L\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bL\u00103R\u0014\u0010M\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bM\u00103R\u0014\u0010O\u001a\u00020N8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bO\u0010PR\u0016\u0010Q\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bQ\u00103\u00a8\u0006R"}, d2={"Lrtx/kimiko/api/drags/components/ArrayListComp.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/Module;", "module", "", "isListed", "(Lrtx/kimiko/api/modules/Module;)Z", "isExcluded", "", "text", "", "x", "y", "alpha", "", "base", "", "drawGradientText", "(Ljava/lang/String;FFFI)V", "iconAdvance", "()F", "Lrtx/kimiko/api/modules/Category;", "category", "iconGlyph", "(Lrtx/kimiko/api/modules/Category;)Ljava/lang/String;", "Lnet/minecraft/DrawContext;", "graphics", "glyph", "drawX", "drawY", "pivotX", "pivotY", "spinDegrees", "scale", "color", "drawGlyph", "(Lnet/minecraft/DrawContext;Ljava/lang/String;FFFFFFI)V", "value", "easeOutBack", "(F)F", "smoothstep", "t", "mixWhite", "(IF)I", "min", "max", "clamp", "(FFF)F", "ROW_SIZE", "F", "ROW_HEIGHT", "PAD_X", "PAD_Y_UP", "PAD_Y_DOWN", "RADIUS", "MIN_WIDTH", "", "ROW_IN_SECONDS", "D", "ROW_OUT_SECONDS", "MIN_TEXT_ALPHA", "ICON_SIZE", "ICON_GAP", "ICON_SLIDE", "ICON_SPIN", "ICON_MIN_SCALE", "ICON_BREATH", "ICON_STAGGER", "", "RENDER_RESUME_GAP_MS", "J", "DOCK_EPSILON", "ICON_IN_SECONDS", "ICON_OUT_SECONDS", "WAVE_FREQ", "RAINBOW_WAVE_HUE_SHIFT", "Lrtx/kimiko/utils/animations/Easing;", "ROW_EASING", "Lrtx/kimiko/utils/animations/Easing;", "iconAdvanceCache", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final boolean isListed(Module module) {
            if (module == null || !module.isEnabled() || this.isExcluded(module)) {
                return false;
            }
            ArrayListModule list = ModuleManager.Companion.get().get(ArrayListModule.class);
            return list == null || list.categoryShown(module.getCategory());
        }

        private final boolean isExcluded(Module module) {
            return module instanceof ClickGui;
        }

        private final void drawGradientText(String text, float x, float y, float alpha, int base) {
            float a = this.clamp(alpha, 0.0f, 1.0f);
            if (a <= 0.003921569f || text == null || ((CharSequence)text).length() == 0) {
                return;
            }
            Fonts.MEDIUM.msdf(text, x, y, 7.2f, base);
        }

        private final float iconAdvance() {
            if (iconAdvanceCache > 0.0f) {
                return iconAdvanceCache;
            }
            float widest = 0.0f;
            for (Category category : Category.getEntries()) {
                String glyph = this.iconGlyph(category);
                if (glyph == null) continue;
                widest = Math.max(widest, Fonts.KIMIKO.msdfWidth(glyph, 6.4f));
            }
            if (widest > 0.0f) {
                iconAdvanceCache = Math.max(6.4f, widest);
            }
            return Math.max(6.4f, widest);
        }

        private final String iconGlyph(Category category) {
            Category category2 = category;
            return switch (category2 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[category2.ordinal()]) {
                case 1 -> "p";
                case 2 -> "j";
                case 3 -> "r";
                case 4 -> "i";
                case 5 -> "w";
                case 6 -> "B";
                case -1 -> null;
                default -> throw new NoWhenBranchMatchedException();
            };
        }

        private final void drawGlyph(DrawContext graphics, String glyph, float drawX, float drawY, float pivotX, float pivotY, float spinDegrees, float scale, int color) {
            graphics.getMatrices().pushMatrix();
            graphics.getMatrices().translate(pivotX, pivotY);
            graphics.getMatrices().rotate((float)Math.toRadians(spinDegrees));
            graphics.getMatrices().scale(scale);
            graphics.getMatrices().translate(-pivotX, -pivotY);
            Fonts.KIMIKO.msdf(glyph, drawX, drawY, 6.4f, color);
            graphics.getMatrices().popMatrix();
        }

        private final float easeOutBack(float value) {
            float t = this.clamp(value, 0.0f, 1.0f) - 1.0f;
            return 1.0f + 2.70158f * t * t * t + 1.70158f * t * t;
        }

        private final float smoothstep(float value) {
            float t = this.clamp(value, 0.0f, 1.0f);
            return t * t * (3.0f - 2.0f * t);
        }

        private final int mixWhite(int color, float t) {
            int a = color >>> 24 & 0xFF;
            int r = color >> 16 & 0xFF;
            int g = color >> 8 & 0xFF;
            int b = color & 0xFF;
            r += MathKt.roundToInt((float)((float)(255 - r) * t));
            g += MathKt.roundToInt((float)((float)(255 - g) * t));
            b += MathKt.roundToInt((float)((float)(255 - b) * t));
            return a << 24 | r << 16 | g << 8 | b;
        }

        private final float clamp(float value, float min, float max) {
            return Math.max(min, Math.min(max, value));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @Metadata(mv={2, 4, 0}, k=3, xi=48)
        public static final class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] nArray = new int[Category.values().length];
                try {
                    nArray[Category.VISUALS.ordinal()] = 1;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Category.DISPLAY.ordinal()] = 2;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Category.UTILS.ordinal()] = 3;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Category.EVENTS.ordinal()] = 4;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Category.CONFIGS.ordinal()] = 5;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[Category.THEMES.ordinal()] = 6;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                $EnumSwitchMapping$0 = nArray;
            }
        }
    }
}

