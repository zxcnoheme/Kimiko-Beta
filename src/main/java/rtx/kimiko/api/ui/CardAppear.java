/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.animations.Animation;
import rtx.kimiko.utils.animations.Decelerate;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u0000 22\u00020\u0001:\u00012B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0006\u00a2\u0006\u0004\b\t\u0010\u0003J\u0015\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000b\u0010\bJ\r\u0010\f\u001a\u00020\u0004\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0006\u00a2\u0006\u0004\b\u000e\u0010\u0003J\r\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0016\u001a\u00020\u0015\u00a2\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0018\u0010\rJ\u0015\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u0012\u00a2\u0006\u0004\b\u001a\u0010\u001bJ=\u0010\"\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u0015\u00a2\u0006\u0004\b\"\u0010#R0\u0010'\u001a\u001e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020%0$j\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020%`&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0010\u0010)R\u0016\u0010\u0013\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010*R\u0016\u0010\u0016\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0016\u0010+R\u0016\u0010\f\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\f\u0010,R\u0016\u0010-\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b-\u0010*R\u0016\u0010/\u001a\u00020.8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0016\u00101\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b1\u0010,\u00a8\u00063"}, d2={"Lrtx/kimiko/api/ui/CardAppear;", "", "<init>", "()V", "", "quick", "", "restart", "(Z)V", "frameDone", "value", "setComposite", "composite", "()Z", "resetRects", "", "rects", "()[F", "", "count", "()I", "", "maxPhase", "()F", "hasWork", "row", "progress", "(I)F", "x", "y", "w", "h", "presence", "phase", "push", "(FFFFFF)V", "Ljava/util/HashMap;", "Lrtx/kimiko/utils/animations/Decelerate;", "Lkotlin/collections/HashMap;", "anims", "Ljava/util/HashMap;", "[F", "I", "F", "Z", "fadeMs", "", "baseMs", "J", "initialFrame", "Companion", "rtx.kimiko:kimiko"})
public final class CardAppear {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final HashMap<Integer, Decelerate> anims = new HashMap();
    @NotNull
    private final float[] rects = new float[192];
    private int count;
    private float maxPhase;
    private boolean composite;
    private int fadeMs = 320;
    private long baseMs;
    private boolean initialFrame;
    public static final int MAX_CARDS = 32;
    public static final float SLIDE_PX = 6.0f;
    private static final int FADE_MS = 380;
    private static final int QUICK_FADE_MS = 320;

    public final void restart(boolean quick) {
        this.anims.clear();
        this.fadeMs = quick ? 320 : 380;
        this.baseMs = System.currentTimeMillis();
        this.initialFrame = true;
    }

    public final void frameDone() {
        this.initialFrame = false;
    }

    public final void setComposite(boolean value) {
        this.composite = value;
    }

    public final boolean composite() {
        return this.composite;
    }

    public final void resetRects() {
        this.count = 0;
        this.maxPhase = 0.0f;
    }

    @NotNull
    public final float[] rects() {
        return this.rects;
    }

    public final int count() {
        return this.count;
    }

    public final float maxPhase() {
        return this.maxPhase;
    }

    public final boolean hasWork() {
        if (this.initialFrame) {
            return true;
        }
        Iterator<Decelerate> iterator = this.anims.values().iterator();
        while (iterator.hasNext()) {
            Decelerate anim = (Decelerate) (iterator.next());
            Double d = anim.getOutput();
            double d2 = d != null ? d : 0.0;
            if (!((float)d2 < 0.999f)) continue;
            return true;
        }
        return false;
    }

    public final float progress(int row) {
        Decelerate anim = this.anims.get(row);
        if (anim == null) {
            long start = this.initialFrame ? this.baseMs : System.currentTimeMillis();
            Animation animation = new Decelerate().setMs(this.fadeMs).setValue(1.0);
            Intrinsics.checkNotNull((Object)animation, (String)"null cannot be cast to non-null type rtx.kimiko.utils.animations.Decelerate");
            anim = (Decelerate)animation;
            anim.counter.setTime(start);
            ((Map)this.anims).put(row, anim);
        }
        Double d = anim.getOutput();
        float value = (float)(d != null ? d : 0.0);
        return value < 0.0f ? 0.0f : Math.min(value, 1.0f);
    }

    public final void push(float x, float y, float w, float h, float presence, float phase) {
        if (this.count >= 32) {
            return;
        }
        int offset = this.count * 6;
        this.rects[offset] = x;
        this.rects[offset + 1] = y;
        this.rects[offset + 2] = w;
        this.rects[offset + 3] = h;
        this.rects[offset + 4] = presence;
        this.rects[offset + 5] = phase;
        int n = this.count;
        this.count = n + 1;
        this.maxPhase = Math.max(this.maxPhase, phase);
    }

    @JvmStatic
    public static final float settle(float progress) {
        return Companion.settle(progress);
    }

    @JvmStatic
    public static final float blurPhase(float progress) {
        return Companion.blurPhase(progress);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\t\u0010\bR\u0014\u0010\u000b\u001a\u00020\n8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\fR\u0014\u0010\u0010\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\f\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/ui/CardAppear.Companion;", "", "<init>", "()V", "", "progress", "Lkotlin/jvm/JvmStatic;", "settle", "(F)F", "blurPhase", "", "MAX_CARDS", "I", "SLIDE_PX", "F", "FADE_MS", "QUICK_FADE_MS", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final float settle(float progress) {
            return Math.min(1.0f, progress / 0.5f);
        }

        @JvmStatic
        public final float blurPhase(float progress) {
            float fade = progress < 0.5f ? 0.0f : (progress - 0.5f) * 2.0f;
            return 1.0f - fade * fade * (3.0f - 2.0f * fade);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

