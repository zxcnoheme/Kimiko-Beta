/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.util.warmup;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.ui.UI;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.gif.GifRenderer;
import rtx.kimiko.utils.render.render2d.outline.outline360.Outline360Range;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \n2\u00020\u0001:\u0001\nB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/utils/render/util/warmup/Render2DWarmup;", "", "<init>", "()V", "Lrtx/kimiko/api/events/impl/render/HudRenderEvent;", "event", "", "Lrtx/kimiko/api/events/EventHandler;", "onHud", "(Lrtx/kimiko/api/events/impl/render/HudRenderEvent;)V", "Companion", "rtx.kimiko:kimiko"})
public final class Render2DWarmup {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final int WARMUP_FRAMES = 5;
    @NotNull
    private static final Render2DWarmup INSTANCE = new Render2DWarmup();
    private static volatile int framesLeft = 5;
    private static final int FAINT = 0x1FFFFFF;
    private static final int FAINT_ACCENT = 25859059;

    private Render2DWarmup() {
    }

    @EventHandler
    public final void onHud(@NotNull HudRenderEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        Companion.runWarmupFrame(event.getGraphics());
    }

    @JvmStatic
    public static final void init() {
        Companion.init();
    }

    @JvmStatic
    public static final void reset() {
        Companion.reset();
    }

    @JvmStatic
    public static final void runWarmupFrame(@Nullable DrawContext g) {
        Companion.runWarmupFrame(g);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u0013\u0010\u0007\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0007\u0010\u0003J\u001d\u0010\n\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u000eR\u0014\u0010\u0013\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u000eR\u0014\u0010\u0014\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u000e\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/utils/render/util/warmup/Render2DWarmup.Companion;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "init", "reset", "Lnet/minecraft/DrawContext;", "g", "runWarmupFrame", "(Lnet/minecraft/DrawContext;)V", "", "WARMUP_FRAMES", "I", "Lrtx/kimiko/utils/render/util/warmup/Render2DWarmup;", "INSTANCE", "Lrtx/kimiko/utils/render/util/warmup/Render2DWarmup;", "framesLeft", "FAINT", "FAINT_ACCENT", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final void init() {
            EventBus.Companion.get().subscribe(INSTANCE);
        }

        @JvmStatic
        public final void reset() {
            framesLeft = 5;
        }

        @JvmStatic
        public final void runWarmupFrame(@Nullable DrawContext g) {
            if (framesLeft <= 0) {
                return;
            }
            int n = framesLeft;
            framesLeft = n + -1;
            Render2D.beginFrame(g);
            Render2D.blur(-20.0f, 0.0f, 2.0f, 2.0f, 1.0f, 8.0f, 1.0f, 0x1FFFFFF);
            Render2D.glass(-20.0f, 0.0f, 2.0f, 2.0f, 1.0f, 0, 1.0f, 25.0f, 0x1FFFFFF, 0.5f, true, 0.5f, 0.1f, 1.0f, 0.0f);
            float[] fArray = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
            Render2D.glassOutline(-20.0f, 0.0f, 2.0f, 2.0f, fArray, 1.0f, 0, 1.0f, 25.0f, 0x1FFFFFF, 0.5f, true, 0.5f, 0.1f, 1.0f, 0.0f);
            Render2D.rect(-20.0f, 0.0f, 2.0f, 2.0f, 1.0f, 0x1FFFFFF);
            Render2D.rect(-20.0f, 0.0f, 2.0f, 2.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0x1FFFFFF, 0x1FFFFFF, 0x1FFFFFF, 0x1FFFFFF);
            Render2D.outline(-20.0f, 0.0f, 2.0f, 2.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.7f, 0, 25859059, 0, 25859059);
            Render2D.outline360(-20.0f, 0.0f, 2.0f, 2.0f, 1.0f, 1.0f, 0x1FFFFFF, new Outline360Range[0]);
            Render2D.circle(-20.0f, 1.0f, 1.0f, 0x1FFFFFF);
            Render2D.pickerHue(-20.0f, 0.0f, 2.0f, 2.0f, 0.003921569f);
            Render2D.pickerAlpha(-20.0f, 0.0f, 2.0f, 2.0f, 0x1FFFFFF, 0.003921569f);
            Render2D.zippy(-20.0f, 0.0f, 2.0f, 2.0f, 1.0f, 0x1FFFFFF);
            Render2D.halftoneRect(-20.0f, 0.0f, 2.0f, 2.0f, 1.0f, 0x1FFFFFF, 0x1FFFFFF, 1.0f, 2.0f);
            Render2D.ripple(-20.0f, 0.0f, 2.0f, 2.0f, 1.0f, 1.0f, 1.0f, 0.5f, 0x1FFFFFF, 0x1FFFFFF);
            Render2D.shimmer(-20.0f, 0.0f, 2.0f, 2.0f, 0.5f, 0.35f, 0.5f, 0x1FFFFFF);
            Fonts.KIMIKO.msdf("pjri", -20.0f, 0.0f, 6.5f, 0x1FFFFFF);
            Fonts.EVENT_ICONS.msdf("aif", -20.0f, 0.0f, 8.0f, 0x1FFFFFF);
            Fonts.MEDIUM.draw("Ag", -20.0f, 0.0f, 7.0f, 0x1FFFFFF);
            Fonts.MEDIUM.fade("Ag", -20.0f, 0.0f, 5.5f, 0x1FFFFFF, 0.0f, 10.0f, 5.0f, 1.0f, 1.0f);
            Fonts.MEDIUM.shimmer("Ag", -20.0f, 0.0f, 7.0f, 0x1FFFFFF, 0.5f, 0.35f, 0.5f, 0.003921569f);
            GifRenderer.draw(g, -20.0f, 0.0f, 2.0f, 2.0f, 1.0f, "kimiko:gif/kity.gif", 0.003921569f);
            UI.INSTANCE.warmupRender();
            Render2D.flush();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

