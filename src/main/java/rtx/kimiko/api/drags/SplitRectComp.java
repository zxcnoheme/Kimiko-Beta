/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.drags;

import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.drags.Draggable;
import rtx.kimiko.api.drags.HudSettingsPanel;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.ui.settings.Setting;
import rtx.kimiko.utils.animations.Easing;
import rtx.kimiko.utils.animations.SmoothAnimation;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \"2\u00020\u0001:\u0001\"BA\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\n\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\u0004\b\n\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\r\u0010\t\u001a\u00020\u0004\u00a2\u0006\u0004\b\t\u0010\u000fJ\u0015\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0014\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0014\u00a2\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u001aR\u0014\u0010\b\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010\u001aR\u0014\u0010\t\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010\u001aR\u0016\u0010\n\u001a\u0004\u0018\u00010\u00018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\n\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010 \u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b \u0010!\u00a8\u0006#"}, d2={"Lrtx/kimiko/api/drags/SplitRectComp;", "Lrtx/kimiko/api/drags/Draggable;", "", "id", "", "x", "y", "w", "h", "radius", "origin", "<init>", "(Ljava/lang/String;FFFFFLrtx/kimiko/api/drags/Draggable;)V", "()Lrtx/kimiko/api/drags/Draggable;", "width", "()F", "height", "", "Lrtx/kimiko/api/ui/settings/Setting;", "buildHudSettings", "()Ljava/util/List;", "Lnet/minecraft/DrawContext;", "graphics", "", "render", "(Lnet/minecraft/DrawContext;)V", "F", "Lrtx/kimiko/api/drags/Draggable;", "Lrtx/kimiko/utils/animations/SmoothAnimation;", "reveal", "Lrtx/kimiko/utils/animations/SmoothAnimation;", "", "revealStarted", "Z", "Companion", "rtx.kimiko:kimiko"})
public final class SplitRectComp
extends Draggable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final float w;
    private final float h;
    private final float radius;
    @Nullable
    private final Draggable origin;
    @NotNull
    private final SmoothAnimation reveal;
    private boolean revealStarted;
    @NotNull
    private static final Easing OUT_QUART = SplitRectComp::OUT_QUART$lambda$0;

    public SplitRectComp(@NotNull String id, float x, float y, float w, float h, float radius, @Nullable Draggable origin) {
        super(id, x, y);
        this.w = w;
        this.h = h;
        this.radius = radius;
        this.origin = origin;
        this.reveal = new SmoothAnimation();
    }

    @Nullable
    public final Draggable origin() {
        return this.origin;
    }

    @Override
    public float width() {
        return this.w;
    }

    @Override
    public float height() {
        return this.h;
    }

    public final float radius() {
        return this.radius;
    }

    @Override
    @NotNull
    protected List<Setting> buildHudSettings() {
        return CollectionsKt.emptyList();
    }

    @Override
    protected void render(@NotNull DrawContext graphics) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        List<Setting> settings = this.origin != null ? this.origin.hudSettings() : Collections.emptyList();
        if (settings.isEmpty()) {
            Render2D.beginFrame(graphics);
            RectUtil.drawClientRect(this.getX(), this.getY(), this.w, this.h, this.radius, 1.0f);
            Render2D.flush();
            return;
        }
        if (!this.revealStarted) {
            this.reveal.run(1.0, 0.45, OUT_QUART, false);
            this.revealStarted = true;
        }
        this.reveal.update();
        float t = this.reveal.get();
        if (t >= 0.999f) {
            float f = this.getX();
            float f2 = this.getY();
            Draggable draggable = this.origin;
            Intrinsics.checkNotNull((Object)draggable);
            HudSettingsPanel.render(graphics, f, f2, this.w, this.h, this.radius, I18n.tr(draggable.displayName()), settings, 1.0f, true);
        } else {
            float f = this.getX();
            float f3 = this.getY();
            Draggable draggable = this.origin;
            Intrinsics.checkNotNull((Object)draggable);
            HudSettingsPanel.renderReveal(graphics, f, f3, this.w, this.h, this.radius, I18n.tr(draggable.displayName()), settings, t);
        }
    }

    private static final double OUT_QUART$lambda$0(double value) {
        return 1.0 - Math.pow(1.0 - value, 4.0);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lrtx/kimiko/api/drags/SplitRectComp.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/animations/Easing;", "OUT_QUART", "Lrtx/kimiko/utils/animations/Easing;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

