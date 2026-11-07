package rtx.kimiko.api.ui.cosmetics;

import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.render.state.GuiRenderState;
import org.joml.Quaternionf;
import rtx.kimiko.api.modules.impl.Visuals.cosmetics.BedrockModel;
import rtx.kimiko.api.modules.impl.Visuals.cosmetics.BundledCosmetics;
import rtx.kimiko.api.modules.impl.Visuals.cosmetics.CosmeticPreviewState;
import rtx.kimiko.api.modules.impl.Visuals.cosmetics.CosmeticPreviews;
import rtx.kimiko.api.modules.impl.Visuals.cosmetics.CosmeticSettings;
import rtx.kimiko.api.modules.impl.Visuals.cosmetics.CosmeticType;
import rtx.kimiko.api.modules.impl.Visuals.cosmetics.ExternalCosmetic;
import rtx.kimiko.api.modules.impl.Visuals.cosmetics.PreviewModel;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.util.scissor.ScissorUtil;
import rtx.kimiko.utils.sounds.Sounds;

import java.util.ArrayList;
import java.util.List;

/**
 * Вкладка «Cosmetic» в клик-гуи: сверху мини-табы категорий, ниже — сетка плиток
 * с 3D-превью косметики и названием. Клик — надеть/снять.
 * Источники: встроенные CosmeticType + вшитые в ресурсы BundledCosmetics (model.json).
 */
public final class CosmeticsRenderer {
    /** Табы вкладки Cosmetic. */
    private static final String[] TAB_TITLES = {"Крылья", "Голова", "Плечо", "Питомцы", "Наспине", "Одежда", "Эффекты"};

    private int currentCat = 0;
    private float scroll;
    private float scrollTarget;
    private float appearT;
    private boolean open;
    private long lastNs = System.nanoTime();

    /** Универсальный элемент плитки: встроенный CosmeticType или внешний ExternalCosmetic. */
    private record Item(CosmeticType builtin, ExternalCosmetic external) {
        String id() {
            return this.builtin != null ? this.builtin.id : "ext:" + this.external.key();
        }

        String title() {
            return this.builtin != null ? this.builtin.title : this.external.title;
        }
    }

    private record Hit(String id, float x, float y, float w, float h) {
    }

    private final List<Hit> tabHits = new ArrayList<>();
    private final List<Hit> tileHits = new ArrayList<>();

    private static final float PAD = 8.0f;
    private static final float GAP = 6.0f;
    private static final float TAB_H = 22.0f;
    private static final int COLS = 3;

    public void open() {
        this.open = true;
        this.scroll = 0.0f;
        this.scrollTarget = 0.0f;
        this.lastNs = System.nanoTime();
        BundledCosmetics.all(); // прогрев сканирования ресурсов
    }

    public void finishTransition() {
    }

    public void setAppearComposite(boolean composite) {
    }

    public void resetCardBlur() {
    }

    public int cardBlurCount() {
        return 0;
    }

    public float cardBlurMaxPhase() {
        return 0.0f;
    }

    public float currentScroll() {
        return this.scroll;
    }

    /** Плитки активного таба: встроенные + внешние вшитые. */
    private List<Item> items() {
        List<Item> list = new ArrayList<>();
        switch (this.currentCat) {
            case 0 -> {
                for (CosmeticType t : CosmeticType.values()) {
                    if (t.cat == CosmeticType.Cat.WINGS) {
                        list.add(new Item(t, null));
                    }
                }
                for (ExternalCosmetic c : BundledCosmetics.all()) {
                    if (c.resourceCategory.equals("wings")) {
                        list.add(new Item(null, c));
                    }
                }
            }
            case 1 -> {
                for (CosmeticType t : CosmeticType.values()) {
                    if (t.cat == CosmeticType.Cat.HEAD) {
                        list.add(new Item(t, null));
                    }
                }
                for (ExternalCosmetic c : BundledCosmetics.all()) {
                    if (c.slot == ExternalCosmetic.Slot.HEAD) {
                        list.add(new Item(null, c));
                    }
                }
            }
            case 2 -> {
                for (CosmeticType t : CosmeticType.values()) {
                    if (t.cat == CosmeticType.Cat.SHOULDER) {
                        list.add(new Item(t, null));
                    }
                }
                for (ExternalCosmetic c : BundledCosmetics.all()) {
                    if (c.slot == ExternalCosmetic.Slot.SHOULDER) {
                        list.add(new Item(null, c));
                    }
                }
            }
            case 3 -> {
                for (CosmeticType t : CosmeticType.values()) {
                    if (t.cat == CosmeticType.Cat.PETS) {
                        list.add(new Item(t, null));
                    }
                }
                for (ExternalCosmetic c : BundledCosmetics.all()) {
                    if (c.slot == ExternalCosmetic.Slot.PET) {
                        list.add(new Item(null, c));
                    }
                }
            }
            case 4 -> {
                for (ExternalCosmetic c : BundledCosmetics.all()) {
                    if (c.inCat("cape", "back", "bodywear")) {
                        list.add(new Item(null, c));
                    }
                }
            }
            case 5 -> {
                for (ExternalCosmetic c : BundledCosmetics.all()) {
                    if (c.inCat("full_body", "top", "pants", "shoes", "skirt", "arms")) {
                        list.add(new Item(null, c));
                    }
                }
            }
            default -> {
                for (ExternalCosmetic c : BundledCosmetics.all()) {
                    if (c.resourceCategory.equals("effect")) {
                        list.add(new Item(null, c));
                    }
                }
            }
        }
        return list;
    }

    private float contentHeight(float tileH, int count) {
        int rows = (count + COLS - 1) / COLS;
        return rows * (tileH + GAP) - GAP;
    }

    public void render(DrawContext g, float x, float y, float w, float alpha, float slideAlpha, float dt) {
        if (alpha <= 0.004f) {
            return;
        }
        long now = System.nanoTime();
        float step = Math.min(0.1f, (now - this.lastNs) / 1.0E9f);
        this.lastNs = now;
        this.appearT = Math.min(1.0f, this.appearT + step * 5.0f);
        this.open = true;
        this.scroll += (this.scrollTarget - this.scroll) * (1.0f - (float) Math.exp(-step * 16.0f));

        float listX = x + 117.0f;
        float listY = y + 5.0f;
        float listW = w - 122.0f;
        float listH = 280.0f;
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        float slideOff = (1.0f - slideAlpha) * 8.0f;

        // мини-табы категорий
        float tabW = (listW - PAD * 2.0f - GAP * (TAB_TITLES.length - 1)) / TAB_TITLES.length;
        float tabY = listY + PAD + slideOff;
        this.tabHits.clear();
        for (int i = 0; i < TAB_TITLES.length; ++i) {
            boolean sel = i == this.currentCat;
            float tx = listX + PAD + (float) i * (tabW + GAP);
            float hover = mx >= tx && mx <= tx + tabW && my >= tabY && my <= tabY + TAB_H ? 1.0f : 0.0f;
            int bg = MenuColors.rgba(255, 255, 255, Math.min(255, (int) ((sel ? 26 : (10 + 8 * hover)) * alpha)));
            Render2D.rect(tx, tabY, tabW, TAB_H, 6.0f, bg);
            if (sel) {
                Render2D.outline(tx, tabY, tabW, TAB_H, 6.0f, 0.9f, MenuColors.rgba(255, 255, 255, Math.min(255, (int) (64 * alpha))));
            }
            String title = TAB_TITLES[i];
            float tw = Fonts.SEMIBOLD.width(title, 8.0f);
            if (tw > tabW - 4.0f) {
                title = title.substring(0, Math.max(1, title.length() - 1));
                tw = Fonts.SEMIBOLD.width(title + "…", 8.0f);
            }
            float tw2 = Fonts.SEMIBOLD.width(title, 8.0f);
            int ta = (int) ((sel ? 240 : 150) + (sel ? 0 : 60 * hover));
            Fonts.SEMIBOLD.draw(title, tx + (tabW - tw2) / 2.0f, tabY + (TAB_H - 8.0f) / 2.0f, 8.0f,
                    MenuColors.rgba(255, 255, 255, Math.min(255, (int) (ta * alpha))));
            this.tabHits.add(new Hit("tab" + i, tx, tabY, tabW, TAB_H));
        }

        // сетка плиток
        List<Item> items = this.items();
        int count = items.size();
        float tileW = (listW - PAD * 2.0f - GAP * (COLS - 1)) / COLS;
        float tileH = 108.0f;
        float gridY = tabY + TAB_H + 8.0f;
        float contentH = this.contentHeight(tileH, count);
        float maxScroll = Math.max(0.0f, contentH - (listH - (gridY - listY) - PAD));
        this.scrollTarget = Math.max(0.0f, Math.min(maxScroll, this.scrollTarget));
        this.scroll = Math.max(0.0f, Math.min(maxScroll, this.scroll));
        CosmeticSettings settings = CosmeticSettings.get();
        String catKey = "tab" + this.currentCat;
        String selected = settings.tabSelected(catKey);
        long ms = System.currentTimeMillis();
        float yaw = (float) (ms % 14000L) * (360.0f / 14000.0f);

        Render2D.pushScissor(g, listX, gridY - 2.0f, listW, listH - (gridY - listY) - 2.0f);
        float scTop = gridY - 2.0f;
        float scH = listH - (gridY - listY) - 2.0f;
        ScissorUtil.push(listX, scTop, listW, scH);
        int gx0 = Render2DCoordinateSpace.toGuiIntX(listX);
        int gy0 = Render2DCoordinateSpace.toGuiIntY(scTop);
        int gx1 = Render2DCoordinateSpace.toGuiIntX(listX + listW);
        int gy1 = Render2DCoordinateSpace.toGuiIntY(scTop + scH);
        float atlasCx = (Render2DCoordinateSpace.toGuiX(listX) + Render2DCoordinateSpace.toGuiX(listX + listW)) * 0.5f;
        float atlasCy = (Render2DCoordinateSpace.toGuiY(scTop) + Render2DCoordinateSpace.toGuiY(scTop + scH)) * 0.5f;
        this.tileHits.clear();
        List<CosmeticPreviewState.Entry> entries = new ArrayList<>();
        for (int idx = 0; idx < count; ++idx) {
            Item item = items.get(idx);
            int col = idx % COLS;
            int row = idx / COLS;
            float tx = listX + PAD + (float) col * (tileW + GAP);
            float ty = gridY + (float) row * (tileH + GAP) + slideOff - this.scroll;
            if (ty + tileH < gridY - 4.0f || ty > gridY + listH + 4.0f) {
                continue;
            }
            boolean sel = item.id().equals(selected);
            boolean hover = mx >= tx && mx <= tx + tileW && my >= ty && my <= ty + tileH;
            this.drawTile(item, tx, ty, tileW, tileH, alpha, sel, hover);
            this.addPreviewEntry(entries, item, tx, ty, tileW, tileH, alpha, yaw, atlasCx, atlasCy);
            this.tileHits.add(new Hit(item.id(), tx, ty, tileW, tileH));
        }
        if (!entries.isEmpty() && gx1 > gx0 && gy1 > gy0) {
            GuiRenderState rs = ((GuiGraphicsExtractorAccessor) g).kimiko$getGuiRenderState();
            rs.addSpecialElement(new CosmeticPreviewState(entries, gx0, gy0, gx1, gy1, ScissorUtil.current()));
        }
        ScissorUtil.pop();
        Render2D.popScissor(g);

        // скроллбар
        if (maxScroll > 0.5f) {
            float trackX = listX + listW - 5.0f;
            float trackY = gridY;
            float trackH = listH - (gridY - listY) - PAD;
            float thumbH = Math.max(22.0f, trackH * (trackH / (trackH + maxScroll)));
            float thumbY = trackY + (trackH - thumbH) * (this.scroll / maxScroll);
            Render2D.rect(trackX, trackY, 3.0f, trackH, 1.5f, MenuColors.rgba(255, 255, 255, Math.min(255, (int) (14 * alpha))));
            Render2D.rect(trackX, thumbY, 3.0f, thumbH, 1.5f, MenuColors.rgba(255, 255, 255, Math.min(255, (int) (90 * alpha))));
        }
    }

    private void drawTile(Item item, float x, float y, float tw, float th,
                          float alpha, boolean selected, boolean hover) {
        int bg = MenuColors.rgba(255, 255, 255, Math.min(255, (int) ((selected ? 28 : (hover ? 15 : 9)) * alpha)));
        Render2D.rect(x, y, tw, th, 8.0f, bg);
        if (selected) {
            Render2D.outline(x, y, tw, th, 8.0f, 1.4f, MenuColors.rgba(255, 208, 92, (int) (225 * alpha)));
        } else if (hover) {
            Render2D.outline(x, y, tw, th, 8.0f, 1.0f, MenuColors.rgba(255, 255, 255, Math.min(255, (int) (64 * alpha))));
        }
        // фон области превью (модель рисуется отдельным спец-элементом поверх)
        Render2D.rect(x + 6.0f, y + 6.0f, tw - 12.0f, th - 30.0f, 6.0f,
                MenuColors.rgba(255, 255, 255, Math.min(255, (int) (7 * alpha))));
        // название
        String name = item.title();
        float nw = Fonts.SEMIBOLD.width(name, 7.5f);
        if (nw > tw - 10.0f) {
            while (name.length() > 1 && Fonts.SEMIBOLD.width(name + "…", 7.5f) > tw - 10.0f) {
                name = name.substring(0, name.length() - 1);
            }
            name = name + "…";
            nw = Fonts.SEMIBOLD.width(name, 7.5f);
        }
        Fonts.SEMIBOLD.draw(name, x + (tw - nw) / 2.0f, y + th - 17.0f, 7.5f,
                MenuColors.rgba(255, 255, 255, Math.min(255, (int) ((selected ? 240 : 150) * alpha))));
    }

    /** Добавляет запись превью в общий атлас: смещение от центра атласа и масштаб в GUI-пикселях. */
    private void addPreviewEntry(List<CosmeticPreviewState.Entry> entries, Item item,
                                 float tx, float ty, float tileW, float tileH, float alpha, float yaw,
                                 float atlasCx, float atlasCy) {
        PreviewModel model;
        if (item.builtin != null) {
            model = CosmeticPreviews.model(item.builtin);
        } else {
            model = item.external.preview();
        }
        if (model == null || model.quads.isEmpty()) {
            return;
        }
        float pvX = tx + 6.0f;
        float pvY = ty + 6.0f;
        float pvW = tileW - 12.0f;
        float pvH = tileH - 30.0f;
        if (pvW <= 2.0f || pvH <= 2.0f) {
            return;
        }
        float inner = Math.min(pvW, pvH) * 0.86f;
        float scale = Render2DCoordinateSpace.toGui(inner) / Math.max(0.01f, model.maxDim());
        float tileCx = Render2DCoordinateSpace.toGuiX(pvX + pvW * 0.5f);
        float tileCy = Render2DCoordinateSpace.toGuiY(pvY + pvH * 0.5f);
        Quaternionf rotation = new Quaternionf()
                .rotateZ((float) Math.PI)
                .rotateX(-0.21f)
                .rotateY((float) Math.toRadians(yaw) + (item.builtin != null ? item.builtin.ordinal() * 0.8f : 0.0f));
        entries.add(new CosmeticPreviewState.Entry(item.id(), rotation,
                tileCx - atlasCx, tileCy - atlasCy, scale, Math.min(1.0f, alpha)));
    }

    /** true — клик поглощён. */
    public boolean click(float x, float y, float w, float mx, float my) {
        for (Hit h : this.tabHits) {
            if (mx >= h.x() && mx <= h.x() + h.w() && my >= h.y() && my <= h.y() + h.h()) {
                this.currentCat = Integer.parseInt(h.id().substring(3));
                this.scroll = 0.0f;
                this.scrollTarget = 0.0f;
                Sounds.play("select_category");
                return true;
            }
        }
        for (Hit h : this.tileHits) {
            if (mx >= h.x() && mx <= h.x() + h.w() && my >= h.y() && my <= h.y() + h.h()) {
                CosmeticSettings settings = CosmeticSettings.get();
                String catKey = "tab" + this.currentCat;
                if (h.id().equals(settings.tabSelected(catKey))) {
                    settings.tabClear(catKey);
                } else {
                    settings.tabSelect(catKey, h.id());
                }
                Sounds.play("gui_click");
                return true;
            }
        }
        return false;
    }

    public void scroll(double amount, float viewH) {
        this.scrollTarget -= (float) amount * 24.0f;
    }
}
