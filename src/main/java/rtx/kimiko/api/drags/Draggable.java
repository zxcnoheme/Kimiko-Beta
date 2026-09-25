/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.drags;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.drags.DragController;
import rtx.kimiko.api.drags.HudContextMenu;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.ui.settings.Setting;
import rtx.kimiko.utils.animations.HudFadeAnimation;
import rtx.kimiko.utils.render.modules.post.hudlayer.HudLayerRenderer;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u00002\u00020\u0001B!\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\r\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0004\u00a2\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000f\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0011\u0010\rJ\r\u0010\u0012\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0012\u0010\rJ\u000f\u0010\u0013\u001a\u00020\u0004H&\u00a2\u0006\u0004\b\u0013\u0010\rJ\u000f\u0010\u0014\u001a\u00020\u0004H&\u00a2\u0006\u0004\b\u0014\u0010\rJ\u000f\u0010\u0015\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0015\u0010\rJ\u000f\u0010\u0016\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0016\u0010\rJ\u0017\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0017H$\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001c\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0017H\u0000\u00a2\u0006\u0004\b\u001b\u0010\u001aJ\u000f\u0010\u001d\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u001d\u0010\u000bJ\u000f\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\u001f\u0010\"\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b$\u0010%J\u0013\u0010(\u001a\b\u0012\u0004\u0012\u00020'0&\u00a2\u0006\u0004\b(\u0010)J\u0015\u0010*\u001a\b\u0012\u0004\u0012\u00020'0&H\u0014\u00a2\u0006\u0004\b*\u0010)J\u000f\u0010+\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b+\u0010\rJ\u000f\u0010,\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b,\u0010%J\u000f\u0010-\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b-\u0010%J\u0015\u0010/\u001a\b\u0012\u0004\u0012\u00020.0&H\u0016\u00a2\u0006\u0004\b/\u0010)J\u0017\u00102\u001a\u00020\t2\u0006\u00101\u001a\u000200H\u0016\u00a2\u0006\u0004\b2\u00103J\u0017\u00104\u001a\u00020\t2\u0006\u00101\u001a\u000200H\u0016\u00a2\u0006\u0004\b4\u00103J\u0017\u00105\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b5\u0010\u001aJ\u001f\u0010:\u001a\u00020\u001e2\u0006\u00106\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u0004H\u0000\u00a2\u0006\u0004\b8\u00109J\u000f\u0010;\u001a\u00020\u0004H\u0004\u00a2\u0006\u0004\b;\u0010\rJ\u000f\u0010<\u001a\u00020\u0004H\u0004\u00a2\u0006\u0004\b<\u0010\rJ\r\u0010>\u001a\u00020=\u00a2\u0006\u0004\b>\u0010?J\r\u0010A\u001a\u00020@\u00a2\u0006\u0004\bA\u0010BJ\r\u0010C\u001a\u00020\u0002\u00a2\u0006\u0004\bC\u0010%J\u000f\u0010D\u001a\u00020\u001eH\u0016\u00a2\u0006\u0004\bD\u0010 J\u0015\u0010F\u001a\u00020\t2\u0006\u0010E\u001a\u00020\u001e\u00a2\u0006\u0004\bF\u0010GJ\u000f\u0010H\u001a\u00020\u001eH\u0016\u00a2\u0006\u0004\bH\u0010 R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010IR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010JR\u0014\u0010\u0006\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010JR\u0014\u0010K\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bK\u0010LR\u001a\u0010N\u001a\u00020M8\u0004X\u0084\u0004\u00a2\u0006\f\n\u0004\bN\u0010O\u001a\u0004\bP\u0010QR\u0016\u0010E\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bE\u0010RR\u0016\u0010S\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bS\u0010JR\u001e\u0010T\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bT\u0010U\u00a8\u0006V"}, d2={"Lrtx/kimiko/api/drags/Draggable;", "", "", "id", "", "defaultX", "defaultY", "<init>", "(Ljava/lang/String;FF)V", "", "resetToDefault", "()V", "getScale", "()F", "value", "setScale", "(F)V", "scaledWidth", "scaledHeight", "width", "height", "overlayWidth", "overlayHeight", "Lnet/minecraft/DrawContext;", "graphics", "render", "(Lnet/minecraft/DrawContext;)V", "renderNormal$rtx_kimiko_kimiko", "renderNormal", "finishHudFadeFrame", "", "needsFadeLayer", "()Z", "layered", "closeFadeLayer", "(Lnet/minecraft/DrawContext;Z)V", "displayName", "()Ljava/lang/String;", "", "Lrtx/kimiko/api/ui/settings/Setting;", "hudSettings", "()Ljava/util/List;", "buildHudSettings", "screenMargin", "contextTitle", "contextHint", "Lrtx/kimiko/api/drags/HudContextMenu$Item;", "contextItems", "Lcom/google/gson/JsonObject;", "state", "writeState", "(Lcom/google/gson/JsonObject;)V", "readState", "pushScale", "mx", "my", "hitTest$rtx_kimiko_kimiko", "(FF)Z", "hitTest", "getX", "getY", "Lrtx/kimiko/api/drags/DragController;", "getDrag", "()Lrtx/kimiko/api/drags/DragController;", "Lrtx/kimiko/api/drags/Position;", "getPosition", "()Lrtx/kimiko/api/drags/Position;", "getId", "isVisible", "visible", "setVisible", "(Z)V", "isInteractive", "Ljava/lang/String;", "F", "drag", "Lrtx/kimiko/api/drags/DragController;", "Lrtx/kimiko/utils/animations/HudFadeAnimation;", "hudFade", "Lrtx/kimiko/utils/animations/HudFadeAnimation;", "getHudFade", "()Lrtx/kimiko/utils/animations/HudFadeAnimation;", "Z", "scale", "cachedHudSettings", "Ljava/util/List;", "rtx.kimiko:kimiko"})
public abstract class Draggable {
    @NotNull
    private final String id;
    private final float defaultX;
    private final float defaultY;
    @NotNull
    private final DragController drag;
    @NotNull
    private final HudFadeAnimation hudFade;
    private boolean visible;
    private float scale;
    @Nullable
    private List<Setting> cachedHudSettings;

    protected Draggable(@NotNull String id, float defaultX, float defaultY) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        this.id = id;
        this.defaultX = defaultX;
        this.defaultY = defaultY;
        this.drag = new DragController(this.defaultX, this.defaultY);
        this.hudFade = new HudFadeAnimation(0.0f, 1, null);
        this.visible = true;
        this.scale = 1.0f;
    }

    @NotNull
    protected final HudFadeAnimation getHudFade() {
        return this.hudFade;
    }

    public final void resetToDefault() {
        this.drag.setTargetX(this.defaultX);
        this.drag.setTargetY(this.defaultY);
        this.drag.syncToTarget();
        this.scale = 1.0f;
    }

    public final float getScale() {
        return this.scale;
    }

    public final void setScale(float value) {
        if (!(Math.abs(value) <= Float.MAX_VALUE)) {
            return;
        }
        this.scale = RangesKt.coerceIn((float)value, (float)1.0f, (float)1.5f);
    }

    public final float scaledWidth() {
        return this.width() * this.scale;
    }

    public final float scaledHeight() {
        return this.height() * this.scale;
    }

    public abstract float width();

    public abstract float height();

    public float overlayWidth() {
        return this.scaledWidth();
    }

    public float overlayHeight() {
        return this.scaledHeight();
    }

    protected abstract void render(@NotNull DrawContext var1);

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void renderNormal$rtx_kimiko_kimiko(@NotNull DrawContext graphics) {
        boolean scaled;
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        this.hudFade.beginFrame();
        if (!this.visible) {
            this.hudFade.updateTarget(false);
            return;
        }
        boolean layered = this.needsFadeLayer();
        if (layered) {
            HudLayerRenderer.markBegin(graphics);
        }
        this.drag.beginRender$rtx_kimiko_kimiko();
        float angle = this.drag.getTiltAngle();
        boolean bl = scaled = Math.abs(this.scale - 1.0f) > 0.001f;
        if (Math.abs(angle) < 0.01f) {
            if (scaled) {
                this.pushScale(graphics);
            }
            try {
                this.render(graphics);
            }
            finally {
                if (scaled) {
                    graphics.getMatrices().popMatrix();
                }
                this.drag.endRender$rtx_kimiko_kimiko();
                this.finishHudFadeFrame();
                this.closeFadeLayer(graphics, layered);
            }
            return;
        }
        float centerX = this.drag.getRenderX() + this.width() * 0.5f;
        float centerY = this.drag.getRenderY() + this.height() * 0.5f;
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(centerX, centerY);
        graphics.getMatrices().rotate((float)Math.toRadians(angle));
        graphics.getMatrices().translate(-centerX, -centerY);
        if (scaled) {
            this.pushScale(graphics);
        }
        try {
            this.render(graphics);
        }
        finally {
            if (scaled) {
                graphics.getMatrices().popMatrix();
            }
            graphics.getMatrices().popMatrix();
            this.drag.endRender$rtx_kimiko_kimiko();
            this.finishHudFadeFrame();
            this.closeFadeLayer(graphics, layered);
        }
    }

    private final void finishHudFadeFrame() {
        this.hudFade.ensureUpdated(this.isInteractive());
    }

    private final boolean needsFadeLayer() {
        float progress = this.hudFade.get();
        return this.hudFade.isAnimating() || progress > 1.0E-4f && progress < 0.9999f;
    }

    private final void closeFadeLayer(DrawContext graphics, boolean layered) {
        if (!layered) {
            return;
        }
        HudLayerRenderer.markEnd(graphics, this.drag.getRenderX(), this.drag.getRenderY(), this.scaledWidth(), this.scaledHeight(), this.hudFade.get());
    }

    @NotNull
    public String displayName() {
        return this.id;
    }

    @NotNull
    public final List<Setting> hudSettings() {
        if (this.cachedHudSettings == null) {
            this.cachedHudSettings = this.buildHudSettings();
        }
        List<Setting> list = this.cachedHudSettings;
        Intrinsics.checkNotNull(list);
        return list;
    }

    @NotNull
    protected List<Setting> buildHudSettings() {
        return new ArrayList<>();
    }

    public float screenMargin() {
        return 5.0f;
    }

    @NotNull
    public String contextTitle() {
        return this.displayName();
    }

    @NotNull
    public String contextHint() {
        return "";
    }

    @NotNull
    public List<HudContextMenu.Item> contextItems() {
        return CollectionsKt.emptyList();
    }

    public void writeState(@NotNull JsonObject state) {
        Intrinsics.checkNotNullParameter((Object)state, (String)"state");
    }

    public void readState(@NotNull JsonObject state) {
        Intrinsics.checkNotNullParameter((Object)state, (String)"state");
    }

    private final void pushScale(DrawContext graphics) {
        float ox = this.drag.getRenderX();
        float oy = this.drag.getRenderY();
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(ox, oy);
        graphics.getMatrices().scale(this.scale, this.scale);
        graphics.getMatrices().translate(-ox, -oy);
    }

    public final boolean hitTest$rtx_kimiko_kimiko(float mx, float my) {
        return this.drag.isHovered(mx, my, this.scaledWidth(), this.scaledHeight());
    }

    protected final float getX() {
        return this.drag.getRenderX();
    }

    protected final float getY() {
        return this.drag.getRenderY();
    }

    @NotNull
    public final DragController getDrag() {
        return this.drag;
    }

    @NotNull
    public final Position getPosition() {
        return new Position(this.drag.getTargetX(), this.drag.getTargetY());
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public final void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isInteractive() {
        return this.visible;
    }
}

