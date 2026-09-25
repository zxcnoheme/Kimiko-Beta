/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.jvm.internal.TypeIntrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.drags;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.drags.DragSystem;
import rtx.kimiko.api.drags.Draggable;
import rtx.kimiko.api.drags.HudSettingsPanel;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.drags.SplitRectComp;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.ui.settings.Setting;
import rtx.kimiko.utils.animations.Easing;
import rtx.kimiko.utils.animations.SmoothAnimation;
import rtx.kimiko.utils.render.render2d.ClientSplits;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 22\u00020\u0001:\u0003342B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\t\u0010\bJ\u0017\u0010\n\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\n\u0010\bJ\u0019\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0013\u0010\bJ\u0017\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\u0010\u00a2\u0006\u0004\b\u001b\u0010\u0003J\u0017\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\r\u0010\u001f\u001a\u00020\u0010\u00a2\u0006\u0004\b\u001f\u0010\u0003R$\u0010#\u001a\u0012\u0012\u0004\u0012\u00020!0 j\b\u0012\u0004\u0012\u00020!`\"8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010$R$\u0010'\u001a\u0012\u0012\u0004\u0012\u00020\u00040%j\b\u0012\u0004\u0012\u00020\u0004`&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010(R<\u0010,\u001a*\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0*0)j\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0*`+8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010-R0\u0010.\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001c0)j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001c`+8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b.\u0010-R\u0016\u00100\u001a\u00020/8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b0\u00101\u00a8\u00065"}, d2={"Lrtx/kimiko/api/drags/MitosisController;", "", "<init>", "()V", "Lrtx/kimiko/api/drags/Draggable;", "element", "", "isSplitting", "(Lrtx/kimiko/api/drags/Draggable;)Z", "isAnimating", "split", "parent", "Lrtx/kimiko/api/drags/SplitRectComp;", "newestLiveChild", "(Lrtx/kimiko/api/drags/Draggable;)Lrtx/kimiko/api/drags/SplitRectComp;", "child", "", "forget", "(Lrtx/kimiko/api/drags/Draggable;)V", "startForward", "startMerge", "(Lrtx/kimiko/api/drags/SplitRectComp;)Z", "", "pw", "ph", "resolveRadius", "(FF)F", "beginFrame", "Lrtx/kimiko/api/drags/MitosisController$SplitDraw;", "overrideFor", "(Lrtx/kimiko/api/drags/Draggable;)Lrtx/kimiko/api/drags/MitosisController$SplitDraw;", "stage", "Ljava/util/ArrayList;", "Lrtx/kimiko/api/drags/MitosisController$Split;", "Lkotlin/collections/ArrayList;", "active", "Ljava/util/ArrayList;", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "suppressed", "Ljava/util/HashSet;", "Ljava/util/HashMap;", "Ljava/util/ArrayDeque;", "Lkotlin/collections/HashMap;", "born", "Ljava/util/HashMap;", "overrides", "", "counter", "I", "Companion", "SplitDraw", "Split", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nMitosisController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MitosisController.kt\nrtx/kimiko/api/drags/MitosisController\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,321:1\n460#2,7:322\n*S KotlinDebug\n*F\n+ 1 MitosisController.kt\nrtx/kimiko/api/drags/MitosisController\n*L\n180#1:322,7\n*E\n"})
public final class MitosisController {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ArrayList<Split> active = new ArrayList();
    @NotNull
    private final HashSet<Draggable> suppressed = new HashSet();
    @NotNull
    private final HashMap<Draggable, ArrayDeque<SplitRectComp>> born = new HashMap();
    @NotNull
    private final HashMap<Draggable, SplitDraw> overrides = new HashMap();
    private int counter;
    @NotNull
    private static final MitosisController INSTANCE = new MitosisController();
    private static final boolean ENABLED = false;
    private static final double DURATION_MIN = 0.42;
    private static final double DURATION_MAX = 1.1;
    private static final double DURATION_BASE = 0.25;
    private static final double DURATION_PER_PX = 0.00275;
    @NotNull
    private static final Easing OUT_QUART = MitosisController::OUT_QUART$lambda$0;
    private static final float SMOOTHNESS = 40.0f;
    private static final float DEFAULT_RADIUS = 7.0f;
    private static final float MARGIN = (float)Math.ceil(40.0) + 4.0f;

    private MitosisController() {
    }

    public final boolean isSplitting(@NotNull Draggable element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        return this.suppressed.contains(element);
    }

    public final boolean isAnimating(@NotNull Draggable element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        Iterator<Split> iterator = this.active.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Split> iterator2 = iterator;
        while (iterator2.hasNext()) {
            Split s = (Split) (iterator2.next());
            if (s.getParent() != element && s.getDetach() != element) continue;
            return true;
        }
        return false;
    }

    public final boolean split(@Nullable Draggable element) {
        return false;
    }

    private final SplitRectComp newestLiveChild(Draggable parent) {
        ArrayDeque<SplitRectComp> arrayDeque = this.born.get(parent);
        if (arrayDeque == null) {
            return null;
        }
        ArrayDeque<SplitRectComp> kids = arrayDeque;
        List<Draggable> live = DragSystem.Companion.get().getAll();
        while (!kids.isEmpty()) {
            SplitRectComp top = kids.peek();
            if (live.contains(top)) {
                return top;
            }
            kids.pop();
        }
        this.born.remove(parent);
        return null;
    }

    private final void forget(Draggable child) {
        Iterator<ArrayDeque<SplitRectComp>> iterator = this.born.values().iterator();
        while (iterator.hasNext()) {
            ArrayDeque<SplitRectComp> kids = (ArrayDeque<SplitRectComp>) (iterator.next());
            TypeIntrinsics.asMutableCollection((Object)kids).remove(child);
        }
    }

    private final boolean startForward(Draggable element) {
        if (this.active.size() >= 16) {
            return false;
        }
        float pw = element.width();
        float ph = element.height();
        if (pw <= 0.0f || ph <= 0.0f) {
            return false;
        }
        float px = element.getDrag().getRenderX();
        float py = element.getDrag().getRenderY();
        float screenW = Position.Companion.screenWidth();
        float dir = px + pw * 0.5f < screenW * 0.5f ? 1.0f : -1.0f;
        List<Setting> options = element.hudSettings();
        float cw = 0.0f;
        float ch = 0.0f;
        float childX = 0.0f;
        float childY = 0.0f;
        if (options.isEmpty()) {
            cw = pw;
            ch = ph;
            float travel = pw * 1.25f + 10.0f;
            childX = px + travel * dir;
            childY = py;
        } else {
            cw = 132.0f;
            ch = HudSettingsPanel.height(options.size());
            float gap = 8.0f;
            childX = dir > 0.0f ? px + pw + gap : px - cw - gap;
            childY = py;
        }
        float radius = options.isEmpty() ? this.resolveRadius(pw, ph) : this.resolveRadius(cw, ch);
        Split s = new Split(element, null, px, py, pw, ph, radius, cw, ch, childX, childY);
        s.getAnim().run(1.0, s.getDuration(), OUT_QUART, false);
        this.active.add(s);
        return true;
    }

    private final boolean startMerge(SplitRectComp child) {
        if (this.active.size() >= 16) {
            return false;
        }
        this.forget(child);
        Draggable draggable = child.origin();
        Intrinsics.checkNotNull((Object)draggable);
        Draggable origin = draggable;
        float pw = origin.width();
        float ph = origin.height();
        if (pw <= 0.0f || ph <= 0.0f) {
            return false;
        }
        float px = origin.getDrag().getRenderX();
        float py = origin.getDrag().getRenderY();
        float radius = this.resolveRadius(pw, ph);
        Split s = new Split(origin, child, px, py, pw, ph, radius, child.width(), child.height(), child.getDrag().getRenderX(), child.getDrag().getRenderY());
        s.getAnim().setValue(1.0);
        s.getAnim().run(0.0, s.getDuration(), OUT_QUART, false);
        s.setReversing(true);
        this.active.add(s);
        this.suppressed.add(child);
        return true;
    }

    private final float resolveRadius(float pw, float ph) {
        InterfaceModule module;
        InterfaceModule interfaceModule = module = InterfaceModule.Companion.getInstance();
        float configured = interfaceModule == null ? 7.0f : interfaceModule.rectCornerRadius.getFloat();
        return Math.max(0.0f, Math.min(configured, Math.min(pw, ph) * 0.5f));
    }

    /*
     * WARNING - void declaration
     */
    public final void beginFrame() {
        ClientSplits.reset();
        this.overrides.clear();
        if (this.active.isEmpty()) {
            return;
        }
        Iterator<Split> iterator = this.active.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Split> it = iterator;
        while (it.hasNext()) {
            Split s = (Split) (it.next());
            s.getAnim().update();
            if (!s.getAnim().isFinished()) continue;
            if (s.getDetach() != null) {
                DragSystem.Companion.get().unregister(s.getDetach());
                this.suppressed.remove(s.getDetach());
                this.forget(s.getDetach());
            } else if (!s.getReversing()) {
                Object object;
Map $this$getOrPut$iv = this.born;
                ++this.counter;
                String id = "split_" + this.counter;
                float bornX = s.getParent().getDrag().getRenderX() + (s.getChildMaxX() - s.getPx());
                float bornY = s.getParent().getDrag().getRenderY() + (s.getChildMaxY() - s.getPy());
                SplitRectComp child = new SplitRectComp(id, bornX, bornY, s.getCw(), s.getCh(), s.getRadius(), s.getParent());
                DragSystem.Companion.get().register(child);
                Map map = this.born;
                Draggable key$iv = s.getParent();
                boolean $i$f$getOrPut = false;
                Object value$iv = $this$getOrPut$iv.get(key$iv);
                if (value$iv == null) {
                    boolean bl = false;
                    ArrayDeque answer$iv = new ArrayDeque();
                    $this$getOrPut$iv.put(key$iv, answer$iv);
                    object = answer$iv;
                } else {
                    object = value$iv;
                }
                ((ArrayDeque)object).push(child);
            }
            it.remove();
        }
    }

    @Nullable
    public final SplitDraw overrideFor(@NotNull Draggable element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        return this.overrides.get(element);
    }

    public final void stage() {
        if (this.active.isEmpty()) {
            return;
        }
        Iterator<Split> iterator = this.active.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Split> iterator2 = iterator;
        while (iterator2.hasNext()) {
            float pinch;
            Split s = (Split) (iterator2.next());
            float t = Math.max(0.0f, Math.min(1.0f, s.getAnim().get()));
            float halfW = s.getPw() * 0.5f;
            float halfH = s.getPh() * 0.5f;
            float chHalfW = s.getCw() * 0.5f;
            float chHalfH = s.getCh() * 0.5f;
            float px = 0.0f;
            float py = 0.0f;
            float childMaxX = 0.0f;
            float childMaxY = 0.0f;
            if (s.getDetach() == null) {
                px = s.getParent().getDrag().getRenderX();
                py = s.getParent().getDrag().getRenderY();
                childMaxX = px + (s.getChildMaxX() - s.getPx());
                childMaxY = py + (s.getChildMaxY() - s.getPy());
            } else {
                px = s.getParent().getDrag().getRenderX();
                py = s.getParent().getDrag().getRenderY();
                childMaxX = s.getDetach().getDrag().getRenderX();
                childMaxY = s.getDetach().getDrag().getRenderY();
            }
            float parentCenterX = px + halfW;
            float parentCenterY = py + halfH;
            float targetCenterX = childMaxX + chHalfW;
            float targetCenterY = childMaxY + chHalfH;
            float childCenterX = parentCenterX + (targetCenterX - parentCenterX) * t;
            float childCenterY = parentCenterY + (targetCenterY - parentCenterY) * t;
            float childHalfW = chHalfW * t;
            float childHalfH = chHalfH * t;
            float childRadius = Math.min(s.getRadius(), Math.min(childHalfW, childHalfH));
            float left = Math.min(px, childCenterX - childHalfW) - MARGIN;
            float top = Math.min(py, childCenterY - childHalfH) - MARGIN;
            float right = Math.max(px + s.getPw(), childCenterX + childHalfW) + MARGIN;
            float bottom = Math.max(py + s.getPh(), childCenterY + childHalfH) + MARGIN;
            float aabbW = right - left;
            float aabbH = bottom - top;
            float parentCx = parentCenterX - left;
            float parentCy = parentCenterY - top;
            float childCx = childCenterX - left;
            float childCy = childCenterY - top;
            float ramp = MitosisController.Companion.smoothstep(0.0f, 0.12f, t);
            float k = 40.0f * ramp * (pinch = 1.0f - MitosisController.Companion.smoothstep(0.7f, 1.0f, t));
            int idx = ClientSplits.add(parentCx, parentCy, halfW, halfH, childCx, childCy, childHalfW, childHalfH, childRadius, k);
            if (idx <= 0) continue;
            ((Map)this.overrides).put(s.getParent(), new SplitDraw(left, top, aabbW, aabbH, s.getRadius(), idx, childCenterX - childHalfW, childCenterY - childHalfH, childHalfW * 2.0f, childHalfH * 2.0f, childRadius));
        }
    }

    private static final double OUT_QUART$lambda$0(double value) {
        return 1.0 - Math.pow(1.0 - value, 4.0);
    }

    @JvmStatic
    @NotNull
    public static final MitosisController get() {
        return Companion.get();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J'\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0019R\u0014\u0010\u001c\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u0019R\u0014\u0010\u001e\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010!R\u0014\u0010#\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010!\u00a8\u0006$"}, d2={"Lrtx/kimiko/api/drags/MitosisController.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/drags/MitosisController;", "Lkotlin/jvm/JvmStatic;", "get", "()Lrtx/kimiko/api/drags/MitosisController;", "", "edge0", "edge1", "x", "smoothstep", "(FFF)F", "pw", "ph", "", "durationFor", "(FF)D", "INSTANCE", "Lrtx/kimiko/api/drags/MitosisController;", "", "ENABLED", "Z", "DURATION_MIN", "D", "DURATION_MAX", "DURATION_BASE", "DURATION_PER_PX", "Lrtx/kimiko/utils/animations/Easing;", "OUT_QUART", "Lrtx/kimiko/utils/animations/Easing;", "SMOOTHNESS", "F", "DEFAULT_RADIUS", "MARGIN", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final MitosisController get() {
            return INSTANCE;
        }

        private final float smoothstep(float edge0, float edge1, float x) {
            float t = Math.max(0.0f, Math.min(1.0f, (x - edge0) / (edge1 - edge0)));
            return t * t * (3.0f - 2.0f * t);
        }

        private final double durationFor(float pw, float ph) {
            double maxDim = Math.max(pw, ph);
            return Math.max(0.42, Math.min(1.1, 0.25 + maxDim * 0.00275));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001Ba\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u00a2\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0012\u001a\u00020\u0011\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0014\u001a\u0004\b\u0017\u0010\u0016R\u0017\u0010\u0006\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0007\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0018\u001a\u0004\b\u001b\u0010\u001aR\u0017\u0010\b\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0018\u001a\u0004\b\u001c\u0010\u001aR\u0017\u0010\t\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0018\u001a\u0004\b\u001d\u0010\u001aR\u0017\u0010\n\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\n\u0010\u0018\u001a\u0004\b\u001e\u0010\u001aR\u0017\u0010\u000b\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010\u0018\u001a\u0004\b\u001f\u0010\u001aR\u0017\u0010\f\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\f\u0010\u0018\u001a\u0004\b \u0010\u001aR\u0017\u0010\r\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\r\u0010\u0018\u001a\u0004\b!\u0010\u001aR\u0017\u0010\u000e\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u000e\u0010\u0018\u001a\u0004\b\"\u0010\u001aR\u0017\u0010$\u001a\u00020#8\u0006\u00a2\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'R\u0017\u0010)\u001a\u00020(8\u0006\u00a2\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,R\"\u0010.\u001a\u00020-8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b.\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103\u00a8\u00064"}, d2={"Lrtx/kimiko/api/drags/MitosisController$Split;", "", "Lrtx/kimiko/api/drags/Draggable;", "parent", "detach", "", "px", "py", "pw", "ph", "radius", "cw", "ch", "childMaxX", "childMaxY", "<init>", "(Lrtx/kimiko/api/drags/Draggable;Lrtx/kimiko/api/drags/Draggable;FFFFFFFFF)V", "", "toggle", "()V", "Lrtx/kimiko/api/drags/Draggable;", "getParent", "()Lrtx/kimiko/api/drags/Draggable;", "getDetach", "F", "getPx", "()F", "getPy", "getPw", "getPh", "getRadius", "getCw", "getCh", "getChildMaxX", "getChildMaxY", "", "duration", "D", "getDuration", "()D", "Lrtx/kimiko/utils/animations/SmoothAnimation;", "anim", "Lrtx/kimiko/utils/animations/SmoothAnimation;", "getAnim", "()Lrtx/kimiko/utils/animations/SmoothAnimation;", "", "reversing", "Z", "getReversing", "()Z", "setReversing", "(Z)V", "rtx.kimiko:kimiko"})
    private static final class Split {
        @NotNull
        private final Draggable parent;
        @Nullable
        private final Draggable detach;
        private final float px;
        private final float py;
        private final float pw;
        private final float ph;
        private final float radius;
        private final float cw;
        private final float ch;
        private final float childMaxX;
        private final float childMaxY;
        private final double duration;
        @NotNull
        private final SmoothAnimation anim;
        private boolean reversing;

        public Split(@NotNull Draggable parent, @Nullable Draggable detach, float px, float py, float pw, float ph, float radius, float cw, float ch, float childMaxX, float childMaxY) {
            Intrinsics.checkNotNullParameter((Object)parent, (String)"parent");
            this.parent = parent;
            this.detach = detach;
            this.px = px;
            this.py = py;
            this.pw = pw;
            this.ph = ph;
            this.radius = radius;
            this.cw = cw;
            this.ch = ch;
            this.childMaxX = childMaxX;
            this.childMaxY = childMaxY;
            this.duration = Companion.durationFor(this.pw, this.ph);
            this.anim = new SmoothAnimation();
        }

        @NotNull
        public final Draggable getParent() {
            return this.parent;
        }

        @Nullable
        public final Draggable getDetach() {
            return this.detach;
        }

        public final float getPx() {
            return this.px;
        }

        public final float getPy() {
            return this.py;
        }

        public final float getPw() {
            return this.pw;
        }

        public final float getPh() {
            return this.ph;
        }

        public final float getRadius() {
            return this.radius;
        }

        public final float getCw() {
            return this.cw;
        }

        public final float getCh() {
            return this.ch;
        }

        public final float getChildMaxX() {
            return this.childMaxX;
        }

        public final float getChildMaxY() {
            return this.childMaxY;
        }

        public final double getDuration() {
            return this.duration;
        }

        @NotNull
        public final SmoothAnimation getAnim() {
            return this.anim;
        }

        public final boolean getReversing() {
            return this.reversing;
        }

        public final void setReversing(boolean bl) {
            this.reversing = bl;
        }

        public final void toggle() {
            this.reversing = !this.reversing;
            this.anim.run(this.reversing ? 0.0 : 1.0, this.duration, OUT_QUART, false);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001Ba\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0011\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0012R\u0019\u0010\u0004\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0011\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u0012R\u0019\u0010\u0005\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0011\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0012R\u0019\u0010\u0006\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0011\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0012R\u0019\u0010\u0007\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0011\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0012R\u0019\u0010\t\u001a\u00020\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0011\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0013R\u0019\u0010\n\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0011\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0012R\u0019\u0010\u000b\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0011\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\u0012R\u0019\u0010\f\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0011\u00a2\u0006\u0006\n\u0004\b\f\u0010\u0012R\u0019\u0010\r\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0011\u00a2\u0006\u0006\n\u0004\b\r\u0010\u0012R\u0019\u0010\u000e\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0011\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u0012\u00a8\u0006\u0014"}, d2={"Lrtx/kimiko/api/drags/MitosisController$SplitDraw;", "", "", "x", "y", "width", "height", "radius", "", "index", "childX", "childY", "childW", "childH", "childRadius", "<init>", "(FFFFFIFFFFF)V", "Lkotlin/jvm/JvmField;", "F", "I", "rtx.kimiko:kimiko"})
    public static final class SplitDraw {
        @JvmField
        public final float x;
        @JvmField
        public final float y;
        @JvmField
        public final float width;
        @JvmField
        public final float height;
        @JvmField
        public final float radius;
        @JvmField
        public final int index;
        @JvmField
        public final float childX;
        @JvmField
        public final float childY;
        @JvmField
        public final float childW;
        @JvmField
        public final float childH;
        @JvmField
        public final float childRadius;

        public SplitDraw(float x, float y, float width, float height, float radius, int index, float childX, float childY, float childW, float childH, float childRadius) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.radius = radius;
            this.index = index;
            this.childX = childX;
            this.childY = childY;
            this.childW = childW;
            this.childH = childH;
            this.childRadius = childRadius;
        }
    }
}

