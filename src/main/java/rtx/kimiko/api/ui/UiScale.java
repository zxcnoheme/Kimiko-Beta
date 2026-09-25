/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Interface.ClickGui;
import rtx.kimiko.api.modules.settings.impl.SelectSetting;
import rtx.kimiko.api.ui.UI;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\u0007J\u0013\u0010\n\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\f\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\u000bJ\u001d\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0011H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0016\u001a\u00020\u00112\b\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u001f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00040\u001b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001c\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010%\u001a\u00020$8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010(R\u0016\u0010*\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010#R\u0016\u0010+\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u0010(R\u0016\u0010,\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010(R\u0016\u0010-\u001a\u00020$8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b-\u0010&\u00a8\u0006."}, d2={"Lrtx/kimiko/api/ui/UiScale;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "defaultLabel", "()Ljava/lang/String;", "currentLabel", "", "target", "()F", "zoom", "label", "", "select", "(Ljava/lang/String;)V", "", "delta", "", "shift", "(I)Z", "indexOf", "(Ljava/lang/String;)I", "t", "backOut", "(F)F", "", "Lkotlin/jvm/JvmField;", "LABELS", "[Ljava/lang/String;", "", "ZOOMS", "[F", "DEFAULT_INDEX", "I", "", "ANIM_MS", "J", "BACK_C1", "F", "BACK_C3", "index", "animFrom", "animTo", "animStartedAt", "rtx.kimiko:kimiko"})
public final class UiScale {
    @NotNull
    public static final UiScale INSTANCE = new UiScale();
    @JvmField
    @NotNull
    public static final String[] LABELS;
    @NotNull
    private static final float[] ZOOMS;
    private static final int DEFAULT_INDEX = 1;
    private static final long ANIM_MS = 285L;
    private static final float BACK_C1 = 1.70158f;
    private static final float BACK_C3 = 2.70158f;
    private static int index;
    private static float animFrom;
    private static float animTo;
    private static long animStartedAt;

    private UiScale() {
    }

    @JvmStatic
    @NotNull
    public static final String defaultLabel() {
        return LABELS[1];
    }

    @JvmStatic
    @NotNull
    public static final String currentLabel() {
        return LABELS[index];
    }

    @JvmStatic
    public static final float target() {
        return ZOOMS[index];
    }

    @JvmStatic
    public static final float zoom() {
        float delta;
        float goal = UiScale.target();
        if (Math.abs(animTo - goal) > 1.0E-4f) {
            animTo = goal;
        }
        if (Math.abs(delta = animTo - animFrom) <= 1.0E-4f) {
            animFrom = animTo;
            return animTo;
        }
        long elapsed = Math.max(0L, System.currentTimeMillis() - animStartedAt);
        float t = Math.min(1.0f, (float)elapsed / 285.0f);
        if (t >= 1.0f) {
            animFrom = animTo;
            return animTo;
        }
        return animFrom + delta * INSTANCE.backOut(t);
    }

    @JvmStatic
    public static final void select(@Nullable String label) {
        int next = UiScale.indexOf(label);
        if (next < 0 || next == index) {
            return;
        }
        float from = UiScale.zoom();
        index = next;
        if (UI.Companion.isOpen()) {
            animFrom = from;
            animTo = UiScale.target();
            animStartedAt = System.currentTimeMillis();
        } else {
            animTo = animFrom = UiScale.target();
        }
    }

    @JvmStatic
    public static final boolean shift(int delta) {
        if (delta == 0) {
            return false;
        }
        int next = index + (delta > 0 ? 1 : -1);
        if (next < 0 || next >= LABELS.length) {
            return false;
        }
        ClickGui module = ClickGui.Companion.getInstance();
        Object object = module;
        if (object != null && (object = ((ClickGui)object).scale) != null) {
            ((SelectSetting)object).setSelected(LABELS[next]);
        }
        UiScale.select(LABELS[next]);
        Sounds.play(delta > 0 ? "gui_scale_up" : "gui_scale_down");
        return true;
    }

    @JvmStatic
    public static final int indexOf(@Nullable String label) {
        if (label == null) {
            return -1;
        }
        int n = LABELS.length;
        for (int i = 0; i < n; ++i) {
            if (!Intrinsics.areEqual((Object)LABELS[i], (Object)label)) continue;
            return i;
        }
        return -1;
    }

    private final float backOut(float t) {
        float p = t - 1.0f;
        return 1.0f + 2.70158f * p * p * p + 1.70158f * p * p;
    }

    static {
        LABELS = new String[]{"75%", "100%", "125%", "150%"};
        ZOOMS = new float[]{0.75f, 1.0f, 1.25f, 1.5f};
        index = 1;
        animFrom = ZOOMS[1];
        animTo = ZOOMS[1];
    }
}

