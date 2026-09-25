/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.client.util.Window
 *  net.minecraft.client.gui.render.state.special.SpecialGuiElementRenderState
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fc
 */
package rtx.kimiko.api.modules.impl.Visuals.emotions;

import java.awt.Color;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.util.Window;
import net.minecraft.client.gui.render.state.special.SpecialGuiElementRenderState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.modules.impl.Visuals.emotions.Emotion;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionPlayback;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionPreviewState;
import rtx.kimiko.api.ui.settings.RenderHelper;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.api.ui.window.PanelAnimation;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.util.scissor.ScissorUtil;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0014\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\u0018\u0000 ?2\u00020\u0001:\u0001?B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0007\u00a2\u0006\u0004\b\n\u0010\tJ\r\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u000b\u00a2\u0006\u0004\b\u000e\u0010\rJ\r\u0010\u000f\u001a\u00020\u0007\u00a2\u0006\u0004\b\u000f\u0010\tJ\u000f\u0010\u0010\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0010\u0010\tJ\u0013\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0018\u0010\tJ\u001d\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u000b\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u001f\u0010!\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b!\u0010\"J7\u0010'\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b'\u0010(J/\u0010*\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b*\u0010+J?\u0010-\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b-\u0010.J\u000f\u0010/\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b/\u00100J\u000f\u00101\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b1\u00100R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u00102R\u0014\u00104\u001a\u0002038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0014\u00107\u001a\u0002068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b7\u00108R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0016\u0010<\u001a\u0002098\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u0010;R\u0016\u0010=\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b=\u0010>\u00a8\u0006@"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionWheel;", "", "", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;", "emotionsList", "<init>", "(Ljava/util/List;)V", "", "open", "()V", "close", "", "isClosing", "()Z", "isFinished", "finish", "updatePanelRect", "emotions", "()Ljava/util/List;", "", "hovered", "()I", "hoveredEmotion", "()Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;", "updateHover", "Lnet/minecraft/DrawContext;", "graphics", "holdHint", "render", "(Lnet/minecraft/DrawContext;Z)V", "", "radius", "sweepDegrees", "sectorArc", "(FF)F", "cx", "cy", "ring", "alpha", "renderHeader", "(FFFFZ)V", "scale", "renderCenter", "(FFFF)V", "time", "submitPreviews", "(Lnet/minecraft/DrawContext;FFFFF)V", "wheelScale", "()F", "fitScale", "Ljava/util/List;", "", "hoverAnim", "[F", "Lrtx/kimiko/api/ui/window/PanelAnimation;", "anim", "Lrtx/kimiko/api/ui/window/PanelAnimation;", "", "startNanos", "J", "lastNanos", "hoveredIndex", "I", "Companion", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nEmotionWheel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EmotionWheel.kt\nrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionWheel\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,327:1\n1#2:328\n*E\n"})
public final class EmotionWheel {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final List<Emotion> emotionsList;
    @NotNull
    private final float[] hoverAnim;
    @NotNull
    private final PanelAnimation anim;
    private final long startNanos;
    private long lastNanos;
    private int hoveredIndex;
    private static final float TAU = (float)Math.PI * 2;
    private static final float INNER_RADIUS = 54.0f;
    private static final float OUTER_RADIUS = 132.0f;
    private static final float GAP_DEGREES = 2.6f;
    private static final float SECTOR_CORNER = 9.0f;
    private static final float HOVER_GROW = 3.5f;
    private static final float HOVER_ZOOM = 0.1f;
    private static final float PREVIEW_FILL = 1.46f;
    private static final float PREVIEW_DROP = 0.38f;
    private static final float MIN_SECTOR_ARC = 52.0f;
    private static final float LABEL_STRIP = 15.0f;
    private static final float DEAD_ZONE = 48.0f;
    private static final float CENTER_DIAMETER = 92.0f;

    public EmotionWheel(@NotNull List<Emotion> emotionsList) {
        Intrinsics.checkNotNullParameter(emotionsList, (String)"emotionsList");
        this.emotionsList = emotionsList;
        this.hoverAnim = new float[this.emotionsList.size()];
        this.anim = new PanelAnimation(true, true);
        this.startNanos = System.nanoTime();
        this.lastNanos = System.nanoTime();
        this.hoveredIndex = -1;
    }

    public final void open() {
        this.updatePanelRect();
        this.anim.open();
    }

    public final void close() {
        if (!this.anim.isClosing()) {
            this.updatePanelRect();
            this.anim.close();
        }
    }

    public final boolean isClosing() {
        return this.anim.isClosing();
    }

    public final boolean isFinished() {
        return this.anim.isCloseFinished();
    }

    public final void finish() {
        this.anim.finish();
    }

    private final void updatePanelRect() {
        float radius = 132.0f * this.fitScale();
        this.anim.setPanelRect(Position.Companion.screenWidth() * 0.5f - radius, Position.Companion.screenHeight() * 0.5f - radius, radius * 2.0f, radius * 2.0f);
    }

    @NotNull
    public final List<Emotion> emotions() {
        return this.emotionsList;
    }

    public final int hovered() {
        return this.hoveredIndex;
    }

    @Nullable
    public final Emotion hoveredEmotion() {
        int n = ((Collection)this.emotionsList).size();
        int n2 = this.hoveredIndex;
        return (0 <= n2 ? n2 < n : false) ? this.emotionsList.get(this.hoveredIndex) : null;
    }

    public final void updateHover() {
        float dead;
        float dy;
        if (this.anim.isClosing() || this.emotionsList.isEmpty()) {
            return;
        }
        float cx = Position.Companion.screenWidth() * 0.5f;
        float cy = Position.Companion.screenHeight() * 0.5f;
        float dx = Position.Companion.mouseX() - cx;
        if (dx * dx + (dy = Position.Companion.mouseY() - cy) * dy < (dead = 48.0f * this.fitScale()) * dead) {
            this.hoveredIndex = -1;
            return;
        }
        float sweep = 360.0f / (float)this.emotionsList.size();
        float angle = (float)Math.toDegrees(Math.atan2(dx, -dy));
        if (angle < 0.0f) {
            angle += 360.0f;
        }
        this.hoveredIndex = Math.round(angle / sweep) % this.emotionsList.size();
    }

    public final void render(@NotNull DrawContext graphics, boolean holdHint) {
        int i;
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        if (this.emotionsList.isEmpty()) {
            return;
        }
        this.anim.updateFrame();
        long now = System.nanoTime();
        float dt = Math.min(0.1f, (float)(now - this.lastNanos) / 1.0E9f);
        this.lastNanos = now;
        float alpha = this.anim.contentAlpha();
        if (alpha <= 0.004f) {
            return;
        }
        boolean captured = this.anim.captureActive();
        float motionScale = captured ? 1.0f : this.anim.motion().scale();
        float scale = motionScale * (0.86f + 0.14f * alpha) * this.fitScale();
        float cx = Position.Companion.screenWidth() * 0.5f;
        float cy = Position.Companion.screenHeight() * 0.5f;
        float sweep = 360.0f / (float)this.emotionsList.size();
        float time = (float)(now - this.startNanos) / 1.0E9f;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        Window window2 = mc.getWindow();
        Intrinsics.checkNotNullExpressionValue((Object)window2, (String)"getWindow(...)");
        Window window = window2;
        Render2D.rect(-5.0f, -5.0f, window.getFramebufferWidth(), window.getFramebufferHeight(), 0.0f, EmotionWheel.Companion.color(0, 0, 0, 110, this.anim.dimAlpha()));
        this.anim.beginCaptureStratum(graphics);
        this.renderHeader(cx, cy, 132.0f * scale, alpha, holdHint);
        int n = ((Collection)this.emotionsList).size();
        for (i = 0; i < n; ++i) {
            float target = !this.anim.isClosing() && i == this.hoveredIndex ? 1.0f : 0.0f;
            float[] fArray = this.hoverAnim;
            int n2 = i;
            fArray[n2] = fArray[n2] + (target - this.hoverAnim[i]) * (1.0f - (float)Math.exp(-dt * 15.0f));
        }
        n = ((Collection)this.emotionsList).size();
        for (i = 0; i < n; ++i) {
            float hover = this.hoverAnim[i];
            float mid = sweep * (float)i;
            float inner = (54.0f - hover) * scale;
            float outer = (132.0f + hover * 3.5f) * scale;
            RectUtil.drawClientSector(cx, cy, inner, outer, mid, sweep - 2.6f, 9.0f * scale, alpha);
        }
        if (alpha > 0.35f) {
            this.submitPreviews(graphics, cx, cy, scale, alpha, time);
            Render2D.flush();
            Render2D.beginFrame(graphics);
            ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState().createNewRootLayer();
        }
        this.renderCenter(cx, cy, scale, alpha);
    }

    private final float sectorArc(float radius, float sweepDegrees) {
        return radius * 2.0f * (float)Math.sin(Math.toRadians(sweepDegrees) * 0.5);
    }

    private final void renderHeader(float cx, float cy, float ring, float alpha, boolean holdHint) {
        String title = I18n.tr("Выбери нужную эмоцию");
        String hint = holdHint ? I18n.tr("Выберите эмоцию и отпустите клавишу") : I18n.tr("Кликните по эмоции \u00b7 ПКМ чтобы закрыть");
        float titleSize = 10.5f;
        float hintSize = 6.0f;
        float titleW = Fonts.SEMIBOLD.width(title, titleSize);
        float hintW = Fonts.MEDIUM.width(hint, hintSize);
        float top = cy - ring - 34.0f;
        Fonts.SEMIBOLD.draw(title, cx - titleW * 0.5f, top, titleSize, EmotionWheel.Companion.color(255, 255, 255, 240, alpha));
        Fonts.MEDIUM.draw(hint, cx - hintW * 0.5f, top + titleSize + 4.0f, hintSize, EmotionWheel.Companion.color(255, 255, 255, 130, alpha));
    }

    private final void renderCenter(float cx, float cy, float scale, float alpha) {
        Emotion selected = this.anim.isClosing() ? null : this.hoveredEmotion();
        String label;
        if (selected != null && selected.displayName() != null) {
            label = I18n.tr(selected.displayName());
        } else {
            label = EmotionPlayback.isPlaying() ? I18n.tr("Остановить") : I18n.tr("Отмена");
        }
        float diameter = 92.0f * scale;
        float x = cx - diameter * 0.5f;
        float y = cy - diameter * 0.5f;
        RectUtil.drawClientRectFixedRadius(x, y, diameter, diameter, diameter * 0.5f, alpha, 0.0f);
        RenderHelper.drawPanelBg(x + 4.0f * scale, y + 4.0f * scale, diameter - 8.0f * scale, diameter - 8.0f * scale, (diameter - 8.0f * scale) * 0.5f, alpha);
        float labelSize = 7.5f * scale;
        float maxLabelWidth = diameter - 16.0f * scale;
        float labelW = Fonts.SEMIBOLD.width((String)label, labelSize);
        if (labelW > maxLabelWidth) {
            labelSize *= maxLabelWidth / labelW;
            labelW = maxLabelWidth;
        }
        int labelColor = selected != null ? EmotionWheel.Companion.color(255, 255, 255, 240, alpha) : ClientAccent.accentSoftAt(210.0f * alpha, cx, cy);
        Fonts.SEMIBOLD.draw(label, cx - labelW * 0.5f, cy - labelSize * 0.5f - 3.0f * scale, labelSize, labelColor);
        String sub = selected != null ? I18n.tr("Выбрано") : I18n.tr("Отпустите");
        float subSize = 5.2f * scale;
        float subW = Fonts.MEDIUM.width(sub, subSize);
        Fonts.MEDIUM.draw(sub, cx - subW * 0.5f, cy + 7.0f * scale, subSize, EmotionWheel.Companion.color(255, 255, 255, 115, alpha));
    }

    private final void submitPreviews(DrawContext graphics, float cx, float cy, float scale, float alpha, float time) {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.player == null) {
            return;
        }
        float hoverPeak = 0.0f;
        int hoverIndex = -1;
        int n = this.hoverAnim.length;
        for (int i = 0; i < n; ++i) {
            if (!(this.hoverAnim[i] > hoverPeak)) continue;
            hoverPeak = this.hoverAnim[i];
            hoverIndex = i;
        }
        float outer = 135.5f * scale;
        float ringMid = 93.0f * scale;
        float band = 78.0f * scale;
        float modelHeight = band - 15.0f * scale;
        float extent = outer + 2.0f;
        float designX = cx - extent;
        float designY = cy - extent;
        float designSize = extent * 2.0f;
        float guiPerDesign = Render2DCoordinateSpace.guiIndependentScale();
        float modelScale = Render2DCoordinateSpace.toGui(modelHeight) / 1.46f;
        float sweep = 360.0f / (float)this.emotionsList.size();
        float cellWidthDesign = Math.max(this.sectorArc(ringMid, sweep - 2.6f), modelScale / guiPerDesign * 1.8f);
        float cellHeightDesign = band + modelScale / guiPerDesign * 0.9f;
        float cellWidth = cellWidthDesign * guiPerDesign;
        float cellHeight = cellHeightDesign * guiPerDesign;
        int columns = (int)Math.ceil(Math.sqrt(this.emotionsList.size()));
        int rows = (int)Math.ceil((double)this.emotionsList.size() / (double)columns);
        int x0 = Render2DCoordinateSpace.toGuiInt(designX);
        int y0 = Render2DCoordinateSpace.toGuiInt(designY);
        int x1 = x0 + Math.max(1, Math.round(cellWidth * (float)columns));
        int y1 = y0 + Math.max(1, Math.round(cellHeight * (float)rows));
        EmotionPreviewState state = new EmotionPreviewState(this.emotionsList, time, ringMid, modelScale, 0.38f, 54.0f * scale, 132.0f * scale, (float)Math.toRadians(2.6f), 9.0f * scale, hoverPeak > 0.02f ? hoverIndex : -1, hoverPeak * 3.5f * scale, hoverPeak * scale, 1.0f + hoverPeak * 0.1f, alpha, designX, designY, designSize, cellWidth, cellHeight, cellWidthDesign, cellHeightDesign, columns, rows, new Matrix3x2f((Matrix3x2fc)graphics.getMatrices()), x0, y0, x1, y1, ScissorUtil.current());
        Intrinsics.checkNotNull((Object)graphics, (String)"null cannot be cast to non-null type mixin.accessor.GuiGraphicsExtractorAccessor");
        ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState().addSpecialElement((SpecialGuiElementRenderState)state);
    }

    private final float wheelScale() {
        float ringMid = 93.0f;
        float arc = this.sectorArc(ringMid, 360.0f / (float)this.emotionsList.size());
        return arc >= 52.0f ? 1.0f : 52.0f / arc;
    }

    private final float fitScale() {
        float outer = 132.0f * this.wheelScale();
        float fit = Math.min(Position.Companion.screenHeight() * 0.5f / (outer + 48.0f), Position.Companion.screenWidth() * 0.5f / (outer + 10.0f));
        return this.wheelScale() * Math.min(1.0f, fit);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0012\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J7\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u000eR\u0014\u0010\u0012\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u000eR\u0014\u0010\u0013\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u000eR\u0014\u0010\u0014\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u000eR\u0014\u0010\u0015\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u000eR\u0014\u0010\u0016\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u000eR\u0014\u0010\u0017\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u000eR\u0014\u0010\u0018\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u000eR\u0014\u0010\u0019\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u000eR\u0014\u0010\u001a\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u000e\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionWheel.Companion;", "", "<init>", "()V", "", "r", "g", "b", "a", "", "mult", "color", "(IIIIF)I", "TAU", "F", "INNER_RADIUS", "OUTER_RADIUS", "GAP_DEGREES", "SECTOR_CORNER", "HOVER_GROW", "HOVER_ZOOM", "PREVIEW_FILL", "PREVIEW_DROP", "MIN_SECTOR_ARC", "LABEL_STRIP", "DEAD_ZONE", "CENTER_DIAMETER", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final int color(int r, int g, int b, int a, float mult) {
            int alpha = Math.max(0, Math.min(255, Math.round((float)a * mult)));
            return alpha <= 0 ? 0 : new Color(r, g, b, alpha).getRGB();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

