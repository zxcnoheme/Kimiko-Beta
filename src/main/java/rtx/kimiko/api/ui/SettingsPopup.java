/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  kotlin.ranges.RangesKt
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui;

import java.awt.Color;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.drags.DragController;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.ui.ScrollBar;
import rtx.kimiko.api.ui.UI;
import rtx.kimiko.api.ui.settings.Setting;
import rtx.kimiko.api.ui.settings.SettingsFactory;
import rtx.kimiko.api.ui.settings.impl.SeparatorSetting;
import rtx.kimiko.utils.animations.Decelerate;
import rtx.kimiko.utils.animations.Direction;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.others.RoundedScissor;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u0000 m2\u00020\u0001:\u0001mB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\u0006J\u0017\u0010\n\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u000f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f\u00a2\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\f\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0017\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0016\u001a\u00020\u0015\u00a2\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\b\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001c\u001a\u0004\u0018\u00010\u001b\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001e\u001a\u00020\f\u00a2\u0006\u0004\b\u001e\u0010\u0012J\r\u0010\u001f\u001a\u00020\f\u00a2\u0006\u0004\b\u001f\u0010\u0012J\r\u0010 \u001a\u00020\f\u00a2\u0006\u0004\b \u0010\u0012J\u0013\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!\u00a2\u0006\u0004\b#\u0010$JE\u0010,\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\f2\u0006\u0010)\u001a\u00020\f2\u0006\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020\f\u00a2\u0006\u0004\b,\u0010-J\u001d\u00101\u001a\u0002002\u0006\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\f\u00a2\u0006\u0004\b1\u00102J\r\u00103\u001a\u000200\u00a2\u0006\u0004\b3\u0010\u0003J\r\u00104\u001a\u000200\u00a2\u0006\u0004\b4\u0010\u0003J\u0017\u00103\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b3\u00106J\u000f\u00107\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b7\u0010\u0012J\u000f\u00108\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b8\u0010\u0012J\u000f\u00109\u001a\u000200H\u0002\u00a2\u0006\u0004\b9\u0010\u0003J\u000f\u0010:\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b:\u0010\u0012J\u001d\u0010>\u001a\u0002002\u0006\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020\f\u00a2\u0006\u0004\b>\u0010?J\u001f\u0010C\u001a\u0002002\u0006\u0010@\u001a\u00020\"2\u0006\u0010B\u001a\u00020AH\u0002\u00a2\u0006\u0004\bC\u0010DJ\u001d\u0010E\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f\u00a2\u0006\u0004\bE\u0010\u0010J\u001f\u0010F\u001a\u0002002\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0002\u00a2\u0006\u0004\bF\u00102J\u001d\u0010G\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f\u00a2\u0006\u0004\bG\u0010\u0010J%\u0010J\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010I\u001a\u00020H\u00a2\u0006\u0004\bJ\u0010KJ\r\u0010L\u001a\u00020\u0004\u00a2\u0006\u0004\bL\u0010\u0006J\r\u0010M\u001a\u000200\u00a2\u0006\u0004\bM\u0010\u0003J\u0017\u0010O\u001a\u0002002\u0006\u0010N\u001a\u00020\"H\u0002\u00a2\u0006\u0004\bO\u0010PJ\r\u0010Q\u001a\u000200\u00a2\u0006\u0004\bQ\u0010\u0003R\u0014\u0010S\u001a\u00020R8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bS\u0010TR\u0014\u0010V\u001a\u00020U8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bV\u0010WR\u0018\u0010X\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bX\u0010YR\u0016\u0010Z\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bZ\u0010[R\u0016\u0010\\\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\\\u0010]R\u0016\u0010^\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b^\u0010]R\u0016\u0010_\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b_\u0010]R\u0016\u0010`\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b`\u0010]R\u0016\u0010a\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\ba\u0010]R\u0016\u0010b\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bb\u0010]R\u0016\u0010c\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010]R\u0016\u0010J\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bJ\u0010]R\u0016\u0010d\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bd\u0010]R\u0016\u0010e\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\be\u0010[R\u0014\u0010g\u001a\u00020f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bg\u0010hR\u0016\u0010j\u001a\u00020i8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bj\u0010kR\u001c\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010l\u00a8\u0006n"}, d2={"Lrtx/kimiko/api/ui/SettingsPopup;", "", "<init>", "()V", "", "isOpen", "()Z", "isVisible", "", "name", "isOpenFor", "(Ljava/lang/String;)Z", "", "mx", "my", "contains", "(FF)Z", "blurPhase", "()F", "", "dst", "", "offset", "writeBlurRect", "([FI)Z", "shareModuleName", "()Ljava/lang/String;", "Lrtx/kimiko/api/modules/Module;", "shareModule", "()Lrtx/kimiko/api/modules/Module;", "shareScroll", "shareX", "shareY", "", "Lrtx/kimiko/api/ui/settings/Setting;", "widgets", "()Ljava/util/List;", "mod", "anchorX", "anchorYIn", "zoneX", "zoneY", "zoneW", "zoneH", "toggle", "(Lrtx/kimiko/api/modules/Module;FFFFFF)Z", "dx", "dy", "", "shift", "(FF)V", "close", "closeSilent", "userAction", "(Z)Z", "contentHeight", "topPad", "layout", "bodyY", "Lnet/minecraft/DrawContext;", "graphics", "alpha", "render", "(Lnet/minecraft/DrawContext;F)V", "setting", "", "throwable", "handleWidgetFailure", "(Lrtx/kimiko/api/ui/settings/Setting;Ljava/lang/Throwable;)V", "click", "beginDrag", "middleClick", "", "delta", "scroll", "(FFD)Z", "hasOpenOverlay", "closeOverlays", "except", "closeOtherOverlays", "(Lrtx/kimiko/api/ui/settings/Setting;)V", "releaseDrags", "Lrtx/kimiko/utils/animations/Decelerate;", "anim", "Lrtx/kimiko/utils/animations/Decelerate;", "Lrtx/kimiko/api/ui/ScrollBar;", "scrollBar", "Lrtx/kimiko/api/ui/ScrollBar;", "module", "Lrtx/kimiko/api/modules/Module;", "open", "Z", "width", "F", "px", "py", "height", "bodyViewH", "anchorY", "maxBodyH", "scrollTarget", "draggingMove", "Lrtx/kimiko/api/drags/DragController;", "drag", "Lrtx/kimiko/api/drags/DragController;", "", "lastNs", "J", "Ljava/util/List;", "Companion", "rtx.kimiko:kimiko"})
public final class SettingsPopup {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Decelerate anim = new Decelerate();
    @NotNull
    private final ScrollBar scrollBar = new ScrollBar();
    @Nullable
    private Module module;
    private boolean open;
    private float width = 180.0f;
    private float px;
    private float py;
    private float height;
    private float bodyViewH;
    private float anchorY;
    private float maxBodyH;
    private float scroll;
    private float scrollTarget;
    private boolean draggingMove;
    @NotNull
    private final DragController drag = new DragController(0.0f, 0.0f);
    private long lastNs = System.nanoTime();
    @NotNull
    private List<Setting> widgets = Collections.emptyList();
    private static final float MIN_WIDTH = 110.0f;
    private static final float MAX_WIDTH = 180.0f;
    private static final float RADIUS = 8.0f;
    private static final float SIDE_PAD = 5.0f;
    private static final float TOP_PAD = 7.0f;
    private static final float BOTTOM_PAD = 7.0f;
    private static final float EDGE = 4.0f;

    public SettingsPopup() {
        this.anim.setMs(220);
        this.anim.setValue(1.0);
        this.anim.setDirection(Direction.BACKWARDS);
        this.anim.counter.setTime(System.currentTimeMillis() - (long)10000);
    }

    public final boolean isOpen() {
        return this.open;
    }

    public final boolean isVisible() {
        Double out = this.anim.getOutput();
        return this.module != null && out != null && out > 0.01;
    }

    public final boolean isOpenFor(@Nullable String name) {
        Module mod = this.module;
        return this.open && mod != null && name != null && Intrinsics.areEqual((Object)mod.getName(), (Object)name);
    }

    public final boolean contains(float mx, float my) {
        return this.isVisible() && mx >= this.px && mx <= this.px + this.width && my >= this.py && my <= this.py + this.height;
    }

    public final float blurPhase() {
        if (this.module == null) {
            return 0.0f;
        }
        Double d = this.anim.getOutput();
        float t = (float)(d != null ? d : 0.0);
        if (t <= 0.01f) {
            return 0.0f;
        }
        return RangesKt.coerceIn((float)(1.0f - t), (float)0.0f, (float)1.0f);
    }

    public final boolean writeBlurRect(@Nullable float[] dst, int offset) {
        if (this.module == null || dst == null || offset + 6 > dst.length) {
            return false;
        }
        Double d = this.anim.getOutput();
        float t = (float)(d != null ? d : 0.0);
        if (t <= 0.01f) {
            return false;
        }
        dst[offset] = this.px;
        dst[offset + 1] = this.py + (1.0f - t) * 4.0f;
        dst[offset + 2] = this.width;
        dst[offset + 3] = this.height;
        dst[offset + 4] = 1.0f;
        dst[offset + 5] = RangesKt.coerceIn((float)(1.0f - t), (float)0.0f, (float)1.0f);
        return true;
    }

    @NotNull
    public final String shareModuleName() {
        Module mod = this.module;
        return this.open && mod != null ? mod.getName() : "";
    }

    @Nullable
    public final Module shareModule() {
        return this.open ? this.module : null;
    }

    public final float shareScroll() {
        return this.scroll;
    }

    public final float shareX() {
        return this.px;
    }

    public final float shareY() {
        return this.py;
    }

    @NotNull
    public final List<Setting> widgets() {
        return this.open ? this.widgets : Collections.emptyList();
    }

    public final boolean toggle(@NotNull Module mod, float anchorX, float anchorYIn, float zoneX, float zoneY, float zoneW, float zoneH) {
        Intrinsics.checkNotNullParameter((Object)mod, (String)"mod");
        if (this.isOpenFor(mod.getName())) {
            this.close();
            return false;
        }
        this.module = mod;
        this.widgets = SettingsFactory.build(mod);
        this.open = true;
        this.scroll = 0.0f;
        this.scrollTarget = 0.0f;
        this.draggingMove = false;
        this.maxBodyH = zoneH - 7.0f - 7.0f - 8.0f;
        float pref = 0.0f;
        for (Setting setting : this.widgets) {
            pref = Math.max(pref, setting.preferredWidth());
        }
        this.width = RangesKt.coerceIn((float)(pref + 10.0f + 4.0f), (float)110.0f, (float)180.0f);
        this.anchorY = anchorYIn;
        this.px = Position.Companion.clampX(anchorX + 2.5f, this.width);
        this.layout();
        this.anchorY = Position.Companion.clampY(this.anchorY, this.height);
        this.layout();
        this.drag.release();
        this.drag.setTargetX(this.px);
        this.drag.setTargetY(this.anchorY);
        this.drag.syncToTarget();
        this.anim.setDirection(Direction.FORWARDS);
        this.anim.counter.resetCounter();
        return true;
    }

    public final void shift(float dx, float dy) {
        if (this.module == null || this.draggingMove) {
            return;
        }
        this.px += dx;
        this.py += dy;
        this.anchorY += dy;
        this.drag.setTargetX(this.drag.getTargetX() + dx);
        this.drag.setTargetY(this.drag.getTargetY() + dy);
        this.drag.syncToTarget();
    }

    public final void close() {
        if (this.close(true)) {
            Sounds.play("module_settings_close");
        }
    }

    public final void closeSilent() {
        this.close(false);
    }

    private final boolean close(boolean userAction) {
        if (!this.open) {
            return false;
        }
        this.open = false;
        this.draggingMove = false;
        this.drag.release();
        this.closeOverlays();
        this.anim.setDirection(Direction.BACKWARDS);
        this.anim.counter.resetCounter();
        return userAction;
    }

    private final float contentHeight() {
        float total = 0.0f;
        int n = 0;
        for (Setting setting : this.widgets) {
            if (!setting.isVisible()) continue;
            total += setting.height() + 4.0f;
            ++n;
        }
        return n == 0 ? 0.0f : total - 4.0f;
    }

    private final float topPad() {
        for (Setting setting : this.widgets) {
            if (!setting.isVisible()) continue;
            return setting instanceof SeparatorSetting ? 2.0f : 7.0f;
        }
        return 7.0f;
    }

    private final void layout() {
        float contentH = this.contentHeight();
        this.bodyViewH = Math.min(contentH, this.maxBodyH);
        this.height = this.topPad() + this.bodyViewH + 7.0f;
        this.py = this.anchorY;
        float maxScroll = Math.max(0.0f, contentH - this.bodyViewH);
        this.scrollTarget = RangesKt.coerceIn((float)this.scrollTarget, (float)0.0f, (float)maxScroll);
        this.scroll = RangesKt.coerceIn((float)this.scroll, (float)0.0f, (float)maxScroll);
    }

    private final float bodyY() {
        return this.py + this.topPad();
    }

    public final void render(@NotNull DrawContext graphics, float alpha) {
        boolean bl = false;
        boolean tilted;
        boolean tiltEnabled;
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        if (this.module == null) {
            return;
        }
        Double d = this.anim.getOutput();
        float t = (float)(d != null ? d : 0.0);
        if (t <= 0.01f) {
            if (!this.open) {
                this.module = null;
                this.widgets = CollectionsKt.emptyList();
            }
            return;
        }
        float a = t * alpha;
        long now = System.nanoTime();
        float dt = Math.min(0.1f, (float)(now - this.lastNs) / 1.0E9f);
        this.lastNs = now;
        InterfaceModule iface = InterfaceModule.Companion.getInstance();
        boolean directDrag = iface != null && iface.dragStyle.is("Обычный");
        boolean bl2 = tiltEnabled = directDrag && iface.dragTilt.getValue();
        if (this.draggingMove) {
            this.drag.tick(Position.Companion.mouseX(), Position.Companion.mouseY(), this.width, this.height, directDrag);
        }
        this.drag.updateTilt(Position.Companion.mouseX(), tiltEnabled);
        this.px = this.drag.getRenderX();
        this.anchorY = this.drag.getRenderY();
        this.layout();
        this.scroll += (this.scrollTarget - this.scroll) * (1.0f - (float)Math.exp(-dt * 16.0f));
        if (Math.abs(this.scrollTarget - this.scroll) < 0.05f) {
            this.scroll = this.scrollTarget;
        }
        float drawY = this.py + (1.0f - t) * 4.0f;
        float tilt = this.drag.getTiltAngle();
        boolean bl3 = tilted = Math.abs(tilt) > 0.01f;
        if (tilted) {
            float centerX = this.px + this.width * 0.5f;
            float centerY = drawY + this.height * 0.5f;
            graphics.getMatrices().pushMatrix();
            graphics.getMatrices().translate(centerX, centerY);
            graphics.getMatrices().rotate((float)Math.toRadians(tilt));
            graphics.getMatrices().translate(-centerX, -centerY);
        }
        RectUtil.drawClientWindow(this.px, drawY, this.width, this.height, 8.0f, a);
        float windowRadius = RectUtil.clientWindowRadius(8.0f, this.width, this.height);
        Render2D.outline(this.px, drawY, this.width, this.height, windowRadius, 0.6f, SettingsPopup.Companion.rgba(255, 255, 255, (float)24 * a));
        float bodyY = drawY + this.topPad();
        float clipInset = tilted ? (float)Math.abs(Math.sin(Math.toRadians(tilt))) * this.width : 0.0f;
        RoundedScissor.push(graphics, this.px, bodyY, this.width, this.bodyViewH, 0.0f, 0.0f, 0.0f, 0.0f);
        Render2D.pushScissor(graphics, this.px, bodyY - clipInset, this.width, this.bodyViewH + clipInset * 2.0f);
        float yy = bodyY - this.scroll;
        for (Setting setting : this.widgets) {
            if (!setting.isVisible()) continue;
            float sh = setting.height();
            if (yy + sh >= bodyY - 6.0f && yy <= bodyY + this.bodyViewH + 6.0f) {
                try {
                    setting.render(this.px + 5.0f, yy, this.width - 10.0f, a);
                }
                catch (Throwable throwable) {
                    this.handleWidgetFailure(setting, throwable);
                }
            }
            yy += sh + 4.0f;
        }
        Render2D.popScissor(graphics);
        RoundedScissor.pop();
        float contentH = this.contentHeight();
        if (contentH > this.bodyViewH + 0.5f) {
            float f = this.scrollBar.render(this.px + this.width - 4.5f, bodyY, this.bodyViewH, this.bodyViewH, contentH, this.scroll, a);
            if (this.scrollBar.isDragging()) {
                this.scroll = f;
                this.scrollTarget = f;
            }
        }
        bl = false;
        for (Setting setting : this.widgets) {
            if (!setting.isVisible() || !setting.hasOverlay()) continue;
            bl = true;
            break;
        }
        if (bl && !UI.Companion.guiCaptureActive()) {
            ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState().createNewRootLayer();
        }
        float oy = bodyY - this.scroll;
        for (Setting setting : this.widgets) {
            if (!setting.isVisible()) continue;
            if (setting.hasOverlay()) {
                try {
                    setting.renderOverlay(graphics, this.px + 5.0f, oy, this.width - 10.0f, a);
                }
                catch (Throwable throwable) {
                    this.handleWidgetFailure(setting, throwable);
                }
            }
            oy += setting.height() + 4.0f;
        }
        if (tilted) {
            graphics.getMatrices().popMatrix();
        }
        this.drag.renderOverlay(graphics, this.width, this.height, this.width, this.height);
    }

    private final void handleWidgetFailure(Setting setting, Throwable throwable) {
        try {
            Render2D.flush();
        }
        catch (Throwable throwable2) {
            // empty catch block
        }
    }

    public final boolean click(float mx, float my) {
        if (!this.open || this.module == null) {
            return false;
        }
        boolean anyOverlay = false;
        float oy = this.bodyY() - this.scroll;
        for (Setting setting : this.widgets) {
            if (!setting.isVisible()) continue;
            if (setting.isOverlayOpen()) {
                anyOverlay = true;
                if (setting.clickOverlay(this.px + 5.0f, oy, this.width - 10.0f, mx, my)) {
                    return true;
                }
            }
            oy += setting.height() + 4.0f;
        }
        if (anyOverlay) {
            this.closeOverlays();
            return true;
        }
        if (!this.contains(mx, my)) {
            this.close();
            return true;
        }
        if (this.scrollBar.tryGrab(mx, my)) {
            return true;
        }
        if (my >= this.py + this.topPad()) {
            float yy = this.bodyY() - this.scroll;
            for (Setting setting : this.widgets) {
                if (!setting.isVisible()) continue;
                float sh = setting.height();
                if (my >= yy && my < yy + sh) {
                    boolean handled;
                    if (setting instanceof SeparatorSetting || !(handled = setting.click(this.px + 5.0f, yy, this.width - 10.0f, mx, my))) break;
                    if (setting.isOverlayOpen()) {
                        this.closeOtherOverlays(setting);
                    }
                    return true;
                }
                yy += sh + 4.0f;
            }
        }
        this.beginDrag(mx, my);
        return true;
    }

    private final void beginDrag(float mx, float my) {
        this.drag.setTargetX(this.px);
        this.drag.setTargetY(this.anchorY);
        this.draggingMove = this.drag.tryGrab(mx, my, this.width, this.height);
    }

    public final boolean middleClick(float mx, float my) {
        if (!this.open || this.module == null || !this.contains(mx, my)) {
            return false;
        }
        float yy = this.bodyY() - this.scroll;
        for (Setting setting : this.widgets) {
            if (!setting.isVisible()) continue;
            float sh = setting.height();
            if (my >= yy && my < yy + sh) {
                setting.middleClick(this.px + 5.0f, yy, this.width - 10.0f, mx, my);
                return true;
            }
            yy += sh + 4.0f;
        }
        return true;
    }

    public final boolean scroll(float mx, float my, double delta) {
        if (!this.open || this.module == null) {
            return false;
        }
        float oy = this.bodyY() - this.scroll;
        for (Setting setting : this.widgets) {
            if (!setting.isVisible()) continue;
            if (setting.isOverlayOpen() && setting.scrollOverlay(this.px + 5.0f, oy, this.width - 10.0f, mx, my, delta)) {
                return true;
            }
            oy += setting.height() + 4.0f;
        }
        if (this.contains(mx, my)) {
            float maxScroll = Math.max(0.0f, this.contentHeight() - this.bodyViewH);
            this.scrollTarget = RangesKt.coerceIn((float)(this.scrollTarget - (float)delta * 12.0f), (float)0.0f, (float)maxScroll);
            return true;
        }
        return false;
    }

    public final boolean hasOpenOverlay() {
        for (Setting setting : this.widgets) {
            if (!setting.isOverlayOpen()) continue;
            return true;
        }
        return false;
    }

    public final void closeOverlays() {
        for (Setting setting : this.widgets) {
            setting.closeOverlay();
        }
    }

    private final void closeOtherOverlays(Setting except) {
        for (Setting setting : this.widgets) {
            if (Intrinsics.areEqual((Object)setting, (Object)except)) continue;
            setting.closeOverlay();
        }
    }

    public final void releaseDrags() {
        this.draggingMove = false;
        this.drag.release();
        this.scrollBar.release();
        for (Setting setting : this.widgets) {
            setting.releaseDrag();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J/\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\rR\u0014\u0010\u000f\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\rR\u0014\u0010\u0010\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\rR\u0014\u0010\u0011\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\rR\u0014\u0010\u0012\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\rR\u0014\u0010\u0013\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\r\u00a8\u0006\u0014"}, d2={"Lrtx/kimiko/api/ui/SettingsPopup.Companion;", "", "<init>", "()V", "", "r", "g", "b", "", "a", "rgba", "(IIIF)I", "MIN_WIDTH", "F", "MAX_WIDTH", "RADIUS", "SIDE_PAD", "TOP_PAD", "BOTTOM_PAD", "EDGE", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final int rgba(int r, int g, int b, float a) {
            int alpha = RangesKt.coerceIn((int)MathKt.roundToInt((float)a), (int)0, (int)255);
            if (alpha <= 0) {
                return 0;
            }
            return new Color(r, g, b, alpha).getRGB();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

