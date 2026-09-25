package rtx.kimiko.api.ui.settings.impl;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.modules.impl.Visuals.CustomSwords;
import rtx.kimiko.api.ui.settings.RenderHelper;
import rtx.kimiko.api.ui.settings.Setting;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.animations.Decelerate;
import rtx.kimiko.utils.animations.Direction;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.util.renderitem.RenderItem;
import rtx.kimiko.utils.sounds.Sounds;

public final class SwordPickerWidget implements Setting {
    private static final float ROW_H = 16.0f;
    private static final float GAP = 8.0f;
    private static final float PAD = 6.0f;
    private static final float RADIUS = 10.0f;
    private static final float NAME_SIZE = 6.0f;
    private static final String NAME = "Выбор меча";
    private final CustomSwords module;
    private boolean open;
    private int displayed = -1;
    private float fromOffset;
    private float targetOffset;
    private final Decelerate anim = new Decelerate();
    private final Decelerate slide = new Decelerate();

    public SwordPickerWidget() {
        this.module = CustomSwords.getInstance();
        if (this.module != null) {
            int i = this.module.currentWeaponIndex();
            this.displayed = i;
            this.fromOffset = i;
            this.targetOffset = i;
        }
        this.anim.setMs(200);
        this.anim.setValue(1.0);
        this.anim.setDirection(Direction.BACKWARDS);
        this.anim.counter.setTime(System.currentTimeMillis() - 10000L);
        this.slide.setMs(180);
        this.slide.setValue(1.0);
        this.slide.setDirection(Direction.BACKWARDS);
        this.slide.counter.setTime(System.currentTimeMillis() - 10000L);
        this.open = true;
    }

    @NotNull
    @Override
    public String name() {
        return "Выбор меча";
    }

    @Override
    public float height() {
        return ROW_H;
    }

    @Override
    public float preferredWidth() {
        return Math.max(150.0f, 52.0f + Fonts.MEDIUM.width(NAME, 6.5f) + 52.0f);
    }

    @Override
    public boolean isVisible() {
        return this.module != null;
    }

    @Override
    public void render(float x, float y, float width, float alpha) {
        RenderHelper.drawName(NAME, x, y, Math.max(24.0f, width - 58.0f), alpha);
        float bx = x + width - 50.0f;
        float by = y + 2.0f;
        RenderHelper.drawBtn(bx, by, 48.0f, 12.0f, this.open ? "Свернуть" : "Открыть", alpha);
    }

    @Override
    public boolean click(float x, float y, float width, float mouseX, float mouseY) {
        float bx = x + width - 50.0f;
        float by = y + 2.0f;
        if (mouseX >= bx && mouseX <= bx + 48.0f && mouseY >= by && mouseY <= by + 12.0f) {
            this.open = !this.open;
            Sounds.play(this.open ? "settings_open" : "settings_close");
            this.anim.setDirection(this.open ? Direction.FORWARDS : Direction.BACKWARDS);
            this.anim.counter.resetCounter();
            if (this.open && this.module != null) {
                int i = this.module.currentWeaponIndex();
                this.displayed = i;
                this.fromOffset = i;
                this.targetOffset = i;
                this.slide.setDirection(Direction.BACKWARDS);
                this.slide.setValue(1.0);
                this.slide.counter.setTime(System.currentTimeMillis() - 10000L);
            }
            return true;
        }
        return false;
    }

    @Override
    public void renderOverlay(DrawContext drawContext, float x, float y, float width, float alpha) {
        float f = this.anim.getOutput().floatValue();
        if (f <= 0.01f || this.module == null) {
            return;
        }
        float fade = f * alpha;
        float panelY = y + ROW_H + 1.0f;
        float panelH = this.panelHeight(width);
        float gridW = width - PAD * 2.0f;
        float cell = (gridW - GAP * 2.0f) / 3.0f;
        float pitch = cell + GAP;
        int count = this.module.weaponCount();
        float p = this.slide.getOutput().floatValue();
        float g = this.fromOffset + (this.targetOffset - this.fromOffset) * p;
        float frac = (float)(g - Math.floor(g));
        int center = Math.floorMod((int)Math.floor(g), count);
        if (center != this.displayed) {
            this.displayed = center;
            this.module.selectWeaponIndex(center);
        }
        RenderHelper.drawDropBackground(x, panelY, width, panelH, fade);
        float mx = Position.mouseX();
        float my = Position.mouseY();
        RenderItem.beginFrame(drawContext);
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                boolean centered = row == 1 && col == 1;
                int rel = (row - 1) * 3 + (col - 1);
                int index = Math.floorMod((int)Math.floor(g + rel), count);
                float cx = x + PAD + (float)col * pitch - frac * pitch;
                float cy = panelY + PAD + (float)row * pitch;
                boolean hovered = mx >= cx && mx <= cx + cell && my >= cy && my <= cy + cell;
                int bg = centered ? ClientAccent.accentFill(190.0f * fade) : hovered ? ColorEngine.rgba(30, 32, 44, Math.round(150.0f * fade)) : ColorEngine.rgba(16, 18, 26, Math.round(130.0f * fade));
                int border = centered || hovered ? ClientAccent.accent(255.0f * fade) : ColorEngine.rgba(255, 255, 255, Math.round(36.0f * fade));
                Render2D.rect(cx, cy, cell, cell, RADIUS, bg);
                Render2D.outline(cx, cy, cell, cell, RADIUS, centered || hovered ? 1.6f : 1.2f, border);
                String model = this.module.weaponModel(index);
                float size = centered ? cell * 0.6f : cell * 0.5f;
                float itemX = cx + cell * 0.5f - size * 0.5f;
                float itemY = cy + cell * 0.5f - size * 0.5f;
                CustomSwords.setPreview(model);
                RenderItem.item(CustomSwords.previewStack(new ItemStack(Items.IRON_SWORD), model), itemX, itemY, size);
                CustomSwords.setPreview(null);
            }
        }
        RenderItem.flush();
        String sel = this.module.displayName(this.displayed);
        float sw = Fonts.MEDIUM.width(sel, NAME_SIZE);
        Fonts.MEDIUM.draw(sel, x + width * 0.5f - sw * 0.5f, panelY + PAD + gridW + 3.0f, NAME_SIZE, ColorEngine.rgba(255, 255, 255, Math.round(220.0f * fade)));
    }

    @Override
    public boolean hasOverlay() {
        return this.anim.getOutput().floatValue() > 0.01f;
    }

    @Override
    public boolean isOverlayOpen() {
        return this.open;
    }

    @Override
    public void closeOverlay() {
        if (this.open) {
            this.open = false;
            Sounds.play("settings_close");
            this.anim.setDirection(Direction.BACKWARDS);
            this.anim.counter.resetCounter();
        }
    }

    @Override
    public boolean scrollOverlay(float x, float y, float width, float mouseX, float mouseY, double d) {
        if (!this.open || this.module == null) {
            return false;
        }
        float panelY = y + ROW_H + 1.0f;
        float panelH = this.panelHeight(width);
        if (mouseX < x || mouseX > x + width || mouseY < panelY || mouseY > panelY + panelH) {
            return false;
        }
        if (d == 0.0) {
            return true;
        }
        this.fromOffset = this.fromOffset + (this.targetOffset - this.fromOffset) * this.slide.getOutput().floatValue();
        this.targetOffset += d > 0.0 ? 1.0f : -1.0f;
        this.slide.setDirection(Direction.FORWARDS);
        this.slide.setValue(0.0);
        this.slide.counter.resetCounter();
        return true;
    }

    @Override
    public boolean clickOverlay(float x, float y, float width, float mouseX, float mouseY) {
        if (!this.open || this.anim.getOutput().floatValue() <= 0.1f || this.module == null) {
            return false;
        }
        float panelY = y + ROW_H + 1.0f;
        float panelH = this.panelHeight(width);
        if (mouseX < x || mouseX > x + width || mouseY < panelY || mouseY > panelY + panelH) {
            return false;
        }
        float gridW = width - PAD * 2.0f;
        float cell = (gridW - GAP * 2.0f) / 3.0f;
        float pitch = cell + GAP;
        float p = this.slide.getOutput().floatValue();
        float g = this.fromOffset + (this.targetOffset - this.fromOffset) * p;
        float frac = (float)(g - Math.floor(g));
        int count = this.module.weaponCount();
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                float cx = x + PAD + (float)col * pitch - frac * pitch;
                float cy = panelY + PAD + (float)row * pitch;
                if (mouseX >= cx && mouseX <= cx + cell && mouseY >= cy && mouseY <= cy + cell) {
                    int rel = (row - 1) * 3 + (col - 1);
                    int index = Math.floorMod((int)Math.floor(g + rel), count);
                    this.module.selectWeaponIndex(index);
                    this.fromOffset = index;
                    this.targetOffset = index;
                    this.displayed = index;
                    this.closeOverlay();
                    return true;
                }
            }
        }
        return false;
    }

    private float panelHeight(float width) {
        return width - PAD * 2.0f + 14.0f;
    }
}