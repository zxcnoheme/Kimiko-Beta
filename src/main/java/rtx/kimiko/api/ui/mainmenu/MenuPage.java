/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui.mainmenu;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\f\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J/\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ?\u0010\u0013\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0004H&\u00a2\u0006\u0004\b\u0013\u0010\u0014J'\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0015H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019J'\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0015H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u0019J'\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001bH\u0016\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010 \u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u0015H\u0016\u00a2\u0006\u0004\b \u0010!J\u0017\u0010$\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\"H\u0016\u00a2\u0006\u0004\b$\u0010%J\u000f\u0010&\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b&\u0010\u0003J\u000f\u0010'\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b'\u0010\u0003J?\u0010,\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0004H\u0004\u00a2\u0006\u0004\b,\u0010-R\u001b\u0010\u0005\u001a\u00020\u00048\u0004@\u0004X\u0085\u000e\u0092\u0002\u0002\b.\u00a2\u0006\u0006\n\u0004\b\u0005\u0010/R\u001b\u0010\u0006\u001a\u00020\u00048\u0004@\u0004X\u0085\u000e\u0092\u0002\u0002\b.\u00a2\u0006\u0006\n\u0004\b\u0006\u0010/R\u001b\u0010\u0007\u001a\u00020\u00048\u0004@\u0004X\u0085\u000e\u0092\u0002\u0002\b.\u00a2\u0006\u0006\n\u0004\b\u0007\u0010/R\u001b\u0010\b\u001a\u00020\u00048\u0004@\u0004X\u0085\u000e\u0092\u0002\u0002\b.\u00a2\u0006\u0006\n\u0004\b\b\u0010/\u00a8\u00060"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuPage;", "", "<init>", "()V", "", "x", "y", "w", "h", "", "layout", "(FFFF)V", "Lnet/minecraft/DrawContext;", "graphics", "mouseX", "mouseY", "dt", "alpha", "appear", "render", "(Lnet/minecraft/DrawContext;FFFFF)V", "", "button", "", "mouseClicked", "(FFI)Z", "mouseReleased", "", "delta", "mouseScrolled", "(FFD)Z", "key", "keyPressed", "(I)Z", "", "codepoint", "charTyped", "(C)Z", "onShow", "onHide", "rx", "ry", "rw", "rh", "inside", "(FFFFFF)Z", "Lkotlin/jvm/JvmField;", "F", "rtx.kimiko:kimiko"})
public abstract class MenuPage {
    @JvmField
    protected float x;
    @JvmField
    protected float y;
    @JvmField
    protected float w;
    @JvmField
    protected float h;

    public void layout(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public abstract void render(@NotNull DrawContext var1, float var2, float var3, float var4, float var5, float var6);

    public boolean mouseClicked(float mouseX, float mouseY, int button) {
        return false;
    }

    public boolean mouseReleased(float mouseX, float mouseY, int button) {
        return false;
    }

    public boolean mouseScrolled(float mouseX, float mouseY, double delta) {
        return false;
    }

    public boolean keyPressed(int key) {
        return false;
    }

    public boolean charTyped(char codepoint) {
        return false;
    }

    public void onShow() {
    }

    public void onHide() {
    }

    protected final boolean inside(float mouseX, float mouseY, float rx, float ry, float rw, float rh) {
        return mouseX >= rx && mouseX <= rx + rw && mouseY >= ry && mouseY <= ry + rh;
    }
}

