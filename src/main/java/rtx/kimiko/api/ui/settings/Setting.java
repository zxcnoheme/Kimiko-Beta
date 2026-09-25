/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.settings;

import kotlin.Metadata;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H&\u00a2\u0006\u0004\b\u0006\u0010\u0007J/\u0010\r\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H&\u00a2\u0006\u0004\b\r\u0010\u000eJ7\u0010\u0012\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H&\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0007J\u000f\u0010\u0015\u001a\u00020\u0011H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016J9\u0010\u0019\u001a\u00020\f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u0011H\u0016\u00a2\u0006\u0004\b\u001b\u0010\u0016J\u000f\u0010\u001c\u001a\u00020\u0011H\u0016\u00a2\u0006\u0004\b\u001c\u0010\u0016J7\u0010\u001d\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u001d\u0010\u0013J?\u0010 \u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u001eH\u0016\u00a2\u0006\u0004\b \u0010!J\u000f\u0010\"\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b$\u0010#J7\u0010%\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b%\u0010\u0013\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006&\u00c0\u0006\u0001"}, d2={"Lrtx/kimiko/api/ui/settings/Setting;", "", "", "name", "()Ljava/lang/String;", "", "height", "()F", "x", "y", "w", "alpha", "", "render", "(FFFF)V", "mx", "my", "", "click", "(FFFFF)Z", "preferredWidth", "isVisible", "()Z", "Lnet/minecraft/DrawContext;", "graphics", "renderOverlay", "(Lnet/minecraft/DrawContext;FFFF)V", "hasOverlay", "isOverlayOpen", "clickOverlay", "", "delta", "scrollOverlay", "(FFFFFD)Z", "closeOverlay", "()V", "releaseDrag", "middleClick", "rtx.kimiko:kimiko"})
public interface Setting {
    @NotNull
    public String name();

    public float height();

    public void render(float var1, float var2, float var3, float var4);

    public boolean click(float var1, float var2, float var3, float var4, float var5);

    default public float preferredWidth() {
        return 120.0f;
    }

    default public boolean isVisible() {
        return true;
    }

    default public void renderOverlay(@Nullable DrawContext graphics, float x, float y, float w, float alpha) {
    }

    default public boolean hasOverlay() {
        return false;
    }

    default public boolean isOverlayOpen() {
        return false;
    }

    default public boolean clickOverlay(float x, float y, float w, float mx, float my) {
        return false;
    }

    default public boolean scrollOverlay(float x, float y, float w, float mx, float my, double delta) {
        return false;
    }

    default public void closeOverlay() {
    }

    default public void releaseDrag() {
    }

    default public boolean middleClick(float x, float y, float w, float mx, float my) {
        return false;
    }
}

