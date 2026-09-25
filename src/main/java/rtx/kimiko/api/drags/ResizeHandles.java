/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.math.MathKt
 *  kotlin.ranges.RangesKt
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.drags;

import java.util.IdentityHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.drags.Draggable;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b$\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\u0006\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\nJ+\u0010\u000e\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000e\u0010\u000fJ+\u0010\u0010\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u001b\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0013\u0010\u0014J#\u0010\u0015\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u0012H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0017\u0010\u0003J\u001b\u0010\u0018\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0018\u0010\u0019J;\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\u001f\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u001f\u0010\u0019J7\u0010&\u001a\u00020\u00122\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b&\u0010'J\u001f\u0010)\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010(\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b)\u0010*J\u0017\u0010+\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b+\u0010,J\u001f\u0010.\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010-\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b.\u0010/J\u001f\u00100\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010-\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b0\u0010/J/\u00105\u001a\u00020\u000b2\u0006\u00101\u001a\u00020\u000b2\u0006\u00102\u001a\u00020\u000b2\u0006\u00103\u001a\u00020\u000b2\u0006\u00104\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b5\u00106J\u0017\u00108\u001a\u00020\u000b2\u0006\u00107\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b8\u00109R\u0014\u0010:\u001a\u00020\u000b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010<\u001a\u00020\u000b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b<\u0010;R\u0014\u0010=\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b=\u0010;R\u0014\u0010>\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b>\u0010;R\u0014\u0010?\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b?\u0010;R\u0014\u0010@\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u0010;R\u0014\u0010A\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bA\u0010;R\u0014\u0010B\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bB\u0010;R\u0014\u0010C\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bC\u0010;R\u0014\u0010D\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bD\u0010;R\u0014\u0010F\u001a\u00020E8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bF\u0010GR \u0010I\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020E0H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u0010JR\u0018\u0010\u0011\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0011\u0010KR\u0016\u0010L\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bL\u0010;R\u0016\u0010M\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bM\u0010;R\u0016\u0010O\u001a\u00020N8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bO\u0010PR\u0016\u0010Q\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bQ\u0010;R\u0016\u0010R\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bR\u0010S\u00a8\u0006T"}, d2={"Lrtx/kimiko/api/drags/ResizeHandles;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "isResizing", "()Z", "Lrtx/kimiko/api/drags/Draggable;", "element", "(Lrtx/kimiko/api/drags/Draggable;)Z", "", "mouseX", "mouseY", "inZone", "(Lrtx/kimiko/api/drags/Draggable;FF)Z", "grab", "active", "", "beginFrame", "(Z)V", "tick", "(FF)V", "release", "forget", "(Lrtx/kimiko/api/drags/Draggable;)V", "Lnet/minecraft/DrawContext;", "graphics", "alpha", "render", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/api/drags/Draggable;FFF)V", "layoutDots", "", "index", "cx", "cy", "reach", "offset", "placeDot", "(IFFFF)V", "wanted", "advance", "(Lrtx/kimiko/api/drags/Draggable;Z)F", "cornerRadius", "(Lrtx/kimiko/api/drags/Draggable;)F", "radius", "pivotX", "(Lrtx/kimiko/api/drags/Draggable;F)F", "pivotY", "x0", "y0", "x1", "y1", "distance", "(FFFF)F", "value", "clamp01", "(F)F", "MIN_SCALE", "F", "MAX_SCALE", "DOT_RADIUS", "EDGE_GAP", "FALLBACK_RADIUS", "GRAB_RADIUS", "ZONE", "FADE_SPEED", "DOT_SPACING", "ARC_QUARTER", "", "dots", "[F", "", "HOVER", "Ljava/util/Map;", "Lrtx/kimiko/api/drags/Draggable;", "startDistance", "startScale", "", "lastFrameNanos", "J", "frameDelta", "dragMode", "Z", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nResizeHandles.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ResizeHandles.kt\nrtx/kimiko/api/drags/ResizeHandles\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,207:1\n460#2,7:208\n*S KotlinDebug\n*F\n+ 1 ResizeHandles.kt\nrtx/kimiko/api/drags/ResizeHandles\n*L\n177#1:208,7\n*E\n"})
public final class ResizeHandles {
    @NotNull
    public static final ResizeHandles INSTANCE = new ResizeHandles();
    public static final float MIN_SCALE = 1.0f;
    public static final float MAX_SCALE = 1.5f;
    private static final float DOT_RADIUS = 1.9f;
    private static final float EDGE_GAP = 2.2f;
    private static final float FALLBACK_RADIUS = 7.0f;
    private static final float GRAB_RADIUS = 4.0f;
    private static final float ZONE = 11.0f;
    private static final float FADE_SPEED = 14.0f;
    private static final float DOT_SPACING = 5.6f;
    private static final float ARC_QUARTER = 0.7853982f;
    @NotNull
    private static final float[] dots = new float[6];
    @NotNull
    private static final Map<Draggable, float[]> HOVER = new IdentityHashMap();
    @Nullable
    private static Draggable active;
    private static float startDistance;
    private static float startScale;
    private static long lastFrameNanos;
    private static float frameDelta;
    private static boolean dragMode;

    private ResizeHandles() {
    }

    @JvmStatic
    public static final boolean isResizing() {
        return active != null;
    }

    @JvmStatic
    public static final boolean isResizing(@NotNull Draggable element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        return active == element;
    }

    @JvmStatic
    public static final boolean inZone(@NotNull Draggable element, float mouseX, float mouseY) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        float radius = INSTANCE.cornerRadius(element);
        return INSTANCE.distance(mouseX, mouseY, INSTANCE.pivotX(element, radius), INSTANCE.pivotY(element, radius)) <= radius + 2.2f + 11.0f;
    }

    @JvmStatic
    public static final boolean grab(@NotNull Draggable element, float mouseX, float mouseY) {
        float originY;
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        INSTANCE.layoutDots(element);
        boolean onDot = false;
        for (int i = 0; i < 3; ++i) {
            if (!(INSTANCE.distance(mouseX, mouseY, dots[i * 2], dots[i * 2 + 1]) <= 4.0f)) continue;
            onDot = true;
            break;
        }
        if (!onDot) {
            return false;
        }
        float originX = element.getDrag().getRenderX();
        float current = INSTANCE.distance(mouseX, mouseY, originX, originY = element.getDrag().getRenderY());
        if (current <= 0.5f) {
            return false;
        }
        active = element;
        startDistance = current;
        startScale = element.getScale();
        return true;
    }

    @JvmStatic
    public static final void beginFrame(boolean active) {
        dragMode = active;
        long now = System.nanoTime();
        frameDelta = lastFrameNanos == 0L ? 0.016f : RangesKt.coerceIn((float)((float)(now - lastFrameNanos) / 1.0E9f), (float)0.0f, (float)0.1f);
        lastFrameNanos = now;
    }

    @JvmStatic
    public static final void tick(float mouseX, float mouseY) {
        Draggable draggable = active;
        if (draggable == null) {
            return;
        }
        Draggable element = draggable;
        float current = INSTANCE.distance(mouseX, mouseY, element.getDrag().getRenderX(), element.getDrag().getRenderY());
        if (startDistance <= 0.5f) {
            return;
        }
        element.setScale(startScale * (current / startDistance));
    }

    @JvmStatic
    public static final void release() {
        active = null;
    }

    @JvmStatic
    public static final void forget(@NotNull Draggable element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        HOVER.remove(element);
    }

    @JvmStatic
    public static final void render(@NotNull DrawContext graphics, @NotNull Draggable element, float mouseX, float mouseY, float alpha) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        boolean wanted = dragMode && (ResizeHandles.isResizing(element) || !ResizeHandles.isResizing() && ResizeHandles.inZone(element, mouseX, mouseY));
        float fade = INSTANCE.advance(element, wanted);
        float shown = fade * INSTANCE.clamp01(alpha);
        if (shown <= 0.01f) {
            return;
        }
        INSTANCE.layoutDots(element);
        int center = MathKt.roundToInt((float)((float)230 * shown)) << 24 | 0xFFFFFF;
        int side = MathKt.roundToInt((float)((float)195 * shown)) << 24 | 0xFFFFFF;
        float dotRadius = 1.9f * (0.75f + 0.25f * fade);
        Render2D.circle(dots[2], dots[3], dotRadius, center);
        Render2D.circle(dots[0], dots[1], dotRadius, side);
        Render2D.circle(dots[4], dots[5], dotRadius, side);
    }

    private final void layoutDots(Draggable element) {
        float radius = this.cornerRadius(element);
        float cx = this.pivotX(element, radius);
        float cy = this.pivotY(element, radius);
        float reach = radius + 2.2f;
        this.placeDot(0, cx, cy, reach, -5.6f);
        this.placeDot(1, cx, cy, reach, 0.0f);
        this.placeDot(2, cx, cy, reach, 5.6f);
    }

    private final void placeDot(int index, float cx, float cy, float reach, float offset) {
        float arcHalf = reach * 0.7853982f;
        if (Math.abs(offset) <= arcHalf) {
            float angle = 0.7853982f + offset / reach;
            ResizeHandles.dots[index * 2] = cx + reach * (float)Math.cos(angle);
            ResizeHandles.dots[index * 2 + 1] = cy + reach * (float)Math.sin(angle);
            return;
        }
        float extra = Math.abs(offset) - arcHalf;
        if (offset < 0.0f) {
            ResizeHandles.dots[index * 2] = cx + reach;
            ResizeHandles.dots[index * 2 + 1] = cy - extra;
        } else {
            ResizeHandles.dots[index * 2] = cx - extra;
            ResizeHandles.dots[index * 2 + 1] = cy + reach;
        }
    }

    /*
     * WARNING - void declaration
     */
    private final float advance(Draggable element, boolean wanted) {
        Object object;
Map $this$getOrPut$iv = HOVER;
        float dt = frameDelta;
        Map<Draggable, float[]> map = HOVER;
        Draggable key$iv = element;
        boolean $i$f$getOrPut = false;
        Object value$iv = $this$getOrPut$iv.get(key$iv);
        if (value$iv == null) {
            boolean bl = false;
            float[] answer$iv = new float[1];
            $this$getOrPut$iv.put(key$iv, answer$iv);
            object = answer$iv;
        } else {
            object = value$iv;
        }
        float[] state = (float[])object;
        float target = wanted ? 1.0f : 0.0f;
        state[0] = state[0] + (target - state[0]) * (1.0f - (float)Math.exp(-dt * 14.0f));
        if (Math.abs(target - state[0]) < 0.002f) {
            state[0] = target;
        }
        return this.clamp01(state[0]);
    }

    private final float cornerRadius(Draggable element) {
        InterfaceModule module;
        InterfaceModule interfaceModule = module = InterfaceModule.Companion.getInstance();
        float radius = interfaceModule == null ? 7.0f : interfaceModule.rectCornerRadius.getFloat();
        float limit = Math.min(element.scaledWidth(), element.scaledHeight()) * 0.5f;
        return RangesKt.coerceIn((float)(radius * element.getScale()), (float)0.0f, (float)Math.max(0.0f, limit));
    }

    private final float pivotX(Draggable element, float radius) {
        return element.getDrag().getRenderX() + element.scaledWidth() - radius;
    }

    private final float pivotY(Draggable element, float radius) {
        return element.getDrag().getRenderY() + element.scaledHeight() - radius;
    }

    private final float distance(float x0, float y0, float x1, float y1) {
        float dx = x0 - x1;
        float dy = y0 - y1;
        return (float)Math.sqrt(dx * dx + dy * dy);
    }

    private final float clamp01(float value) {
        return value < 0.0f ? 0.0f : Math.min(value, 1.0f);
    }

    static {
        frameDelta = 0.016f;
    }
}

