package rtx.kimiko.api.modules.impl.Visuals.cosmetics;

import java.util.List;
import net.minecraft.client.gui.ScreenRect;
import net.minecraft.client.gui.render.state.special.SpecialGuiElementRenderState;
import org.joml.Quaternionf;
import org.jetbrains.annotations.Nullable;

/**
 * Спец-элемент GUI: атлас 3D-превью косметик — все видимые плитки категории
 * рендерятся в ОДИН фреймбуфер (у спец-рендерера один текстурный слот, поэтому
 * по элементу на модель нельзя — все получили бы последнюю отрисованную модель).
 * entries(u,v) — смещение центра плитки от центра области, в GUI-пикселях.
 */
public final class CosmeticPreviewState implements SpecialGuiElementRenderState {

    /** builtin — конкретная встроенная косметика (если плитка не внешняя); id у разных категорий могут совпадать. */
    public record Entry(String cosmeticId, @Nullable CosmeticType builtin, Quaternionf rotation, float u, float v, float scale, float alpha) {
    }

    public final List<Entry> entries;
    private final int x0Val;
    private final int y0Val;
    private final int x1Val;
    private final int y1Val;
    @Nullable
    private final ScreenRect scissorAreaVal;
    private final ScreenRect boundsVal;

    public CosmeticPreviewState(List<Entry> entries, int x0, int y0, int x1, int y1, @Nullable ScreenRect scissorArea) {
        this.entries = entries;
        this.x0Val = x0;
        this.y0Val = y0;
        this.x1Val = x1;
        this.y1Val = y1;
        this.scissorAreaVal = scissorArea;
        ScreenRect rect = SpecialGuiElementRenderState.createBounds(x0, y0, x1, y1, scissorArea);
        if (rect == null) {
            rect = new ScreenRect(x0, y0, Math.max(0, x1 - x0), Math.max(0, y1 - y0));
        }
        this.boundsVal = rect;
    }

    @Override
    public int x1() {
        return this.x0Val;
    }

    @Override
    public int y1() {
        return this.y0Val;
    }

    @Override
    public int x2() {
        return this.x1Val;
    }

    @Override
    public int y2() {
        return this.y1Val;
    }

    @Override
    public float scale() {
        return 1.0f;
    }

    @Nullable
    @Override
    public ScreenRect scissorArea() {
        return this.scissorAreaVal;
    }

    @Override
    public ScreenRect bounds() {
        return this.boundsVal;
    }
}
