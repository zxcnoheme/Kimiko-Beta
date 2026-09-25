/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.ranges.RangesKt
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui.window;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.drags.Position;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0016\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\u0007J\u0013\u0010\n\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\f\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\u000bJ#\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\u000fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0003J\u0013\u0010\u0013\u001a\u00020\u000fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0013\u0010\u0003J+\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001aR\u0016\u0010\u001d\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001aR\u0016\u0010\u001e\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001aR\u0016\u0010\u001f\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001aR\u0016\u0010 \u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b \u0010\u001aR\u0016\u0010!\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b!\u0010\u001aR\u0016\u0010\"\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\"\u0010\u001aR\u0016\u0010#\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010$\u00a8\u0006%"}, d2={"Lrtx/kimiko/api/ui/window/PanelDrag;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "x", "()F", "y", "", "isDragging", "()Z", "isMoved", "mouseX", "mouseY", "", "begin", "(FF)V", "end", "reset", "panelWidth", "panelHeight", "dt", "update", "(FFF)V", "EDGE_MARGIN", "F", "FOLLOW_RATE", "RETURN_RATE", "xPos", "yPos", "targetX", "targetY", "grabX", "grabY", "dragging", "Z", "rtx.kimiko:kimiko"})
public final class PanelDrag {
    @NotNull
    public static final PanelDrag INSTANCE = new PanelDrag();
    private static final float EDGE_MARGIN = 6.0f;
    private static final float FOLLOW_RATE = 32.0f;
    private static final float RETURN_RATE = 12.0f;
    private static float xPos;
    private static float yPos;
    private static float targetX;
    private static float targetY;
    private static float grabX;
    private static float grabY;
    private static boolean dragging;

    private PanelDrag() {
    }

    @JvmStatic
    public static final float x() {
        return xPos;
    }

    @JvmStatic
    public static final float y() {
        return yPos;
    }

    @JvmStatic
    public static final boolean isDragging() {
        return dragging;
    }

    @JvmStatic
    public static final boolean isMoved() {
        return Math.abs(targetX) > 0.01f || Math.abs(targetY) > 0.01f || Math.abs(xPos) > 0.01f || Math.abs(yPos) > 0.01f;
    }

    @JvmStatic
    public static final void begin(float mouseX, float mouseY) {
        dragging = true;
        grabX = mouseX - targetX;
        grabY = mouseY - targetY;
    }

    @JvmStatic
    public static final void end() {
        dragging = false;
    }

    @JvmStatic
    public static final void reset() {
        dragging = false;
        targetX = 0.0f;
        targetY = 0.0f;
    }

    @JvmStatic
    public static final void update(float panelWidth, float panelHeight, float dt) {
        if (dragging) {
            targetX = Position.Companion.mouseX() - grabX;
            targetY = Position.Companion.mouseY() - grabY;
        }
        float limitX = Math.max(0.0f, (Position.Companion.screenWidth() - panelWidth) * 0.5f - 6.0f);
        float limitY = Math.max(0.0f, (Position.Companion.screenHeight() - panelHeight) * 0.5f - 6.0f);
        targetX = RangesKt.coerceIn((float)targetX, (float)(-limitX), (float)limitX);
        targetY = RangesKt.coerceIn((float)targetY, (float)(-limitY), (float)limitY);
        float k = 1.0f - (float)Math.exp(-dt * (dragging ? 32.0f : 12.0f));
        xPos += (targetX - xPos) * k;
        yPos += (targetY - yPos) * k;
        if (Math.abs(targetX - xPos) < 0.02f) {
            xPos = targetX;
        }
        if (Math.abs(targetY - yPos) < 0.02f) {
            yPos = targetY;
        }
    }
}

