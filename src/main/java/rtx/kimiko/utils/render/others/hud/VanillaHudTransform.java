/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.others.hud;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.drags.Draggable;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J?\u0010\u000e\u001a\u00020\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bH\u0007b\u0002\b\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0011\u001a\u00020\u00102\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\r\u00a2\u0006\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0013"}, d2={"Lrtx/kimiko/utils/render/others/hud/VanillaHudTransform;", "", "<init>", "()V", "Lnet/minecraft/DrawContext;", "graphics", "Lrtx/kimiko/api/drags/Draggable;", "element", "", "vanillaLeft", "vanillaTop", "scale", "", "Lkotlin/jvm/JvmStatic;", "push", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/api/drags/Draggable;FFF)Z", "", "pop", "(Lnet/minecraft/DrawContext;)V", "rtx.kimiko:kimiko"})
public final class VanillaHudTransform {
    @NotNull
    public static final VanillaHudTransform INSTANCE = new VanillaHudTransform();

    private VanillaHudTransform() {
    }

    @JvmStatic
    public static final boolean push(@Nullable DrawContext graphics, @Nullable Draggable element, float vanillaLeft, float vanillaTop, float scale) {
        if (graphics == null || element == null) {
            return false;
        }
        float targetX = Math.round(Render2DCoordinateSpace.toGuiX(element.getDrag().getRenderX()));
        float targetY = Math.round(Render2DCoordinateSpace.toGuiY(element.getDrag().getRenderY()));
        float total = scale * element.getScale();
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(targetX, targetY);
        graphics.getMatrices().scale(total, total);
        graphics.getMatrices().translate(-vanillaLeft, -vanillaTop);
        return true;
    }

    @JvmStatic
    public static final void pop(@Nullable DrawContext graphics) {
        if (graphics != null && graphics.getMatrices() != null) {
            graphics.getMatrices().popMatrix();
        }
    }
}

