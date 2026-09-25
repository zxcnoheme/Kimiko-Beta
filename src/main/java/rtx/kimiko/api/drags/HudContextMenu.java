/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.drags;

import java.awt.Color;
import java.util.Collection;
import java.util.List;
import java.util.function.BooleanSupplier;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.config.ConfigManager;
import rtx.kimiko.api.drags.Draggable;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.ui.theme.AccentGradient;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001AB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\t\u001a\u0004\u0018\u00010\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\nJ\u0013\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\u0003J\u001d\u0010\u000e\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u000f\u0010\u0012\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0014H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ#\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u0011H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010\"\u001a\u00020\u00042\u0006\u0010!\u001a\u00020 H\u0002\u00a2\u0006\u0004\b\"\u0010#J7\u0010*\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$2\u0006\u0010'\u001a\u00020$2\u0006\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u0014\u0010.\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010-R\u0014\u0010/\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b/\u0010-R\u0014\u00100\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b0\u0010-R\u0014\u00101\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b1\u0010-R\u0014\u00102\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u0010-R\u0014\u00103\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b3\u0010-R\u0014\u00104\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u0010-R\u0014\u00105\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b5\u0010-R\u0018\u0010\t\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\t\u00106R\u001c\u00108\u001a\b\u0012\u0004\u0012\u00020 078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b8\u00109R\u0016\u0010;\u001a\u00020:8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b;\u0010<R\u0016\u0010=\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b=\u0010-R\u0016\u0010>\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010-R\u0016\u0010?\u001a\u00020$8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b?\u0010@\u00a8\u0006B"}, d2={"Lrtx/kimiko/api/drags/HudContextMenu;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "isOpen", "()Z", "Lrtx/kimiko/api/drags/Draggable;", "owner", "()Lrtx/kimiko/api/drags/Draggable;", "", "close", "element", "open", "(Lrtx/kimiko/api/drags/Draggable;)Z", "layout", "", "height", "()F", "Lnet/minecraft/DrawContext;", "graphics", "render", "(Lnet/minecraft/DrawContext;)V", "target", "alpha", "drawElementGhost", "(Lrtx/kimiko/api/drags/Draggable;F)V", "mx", "my", "click", "(FF)Z", "Lrtx/kimiko/api/drags/HudContextMenu$Item;", "item", "safeSelected", "(Lrtx/kimiko/api/drags/HudContextMenu$Item;)Z", "", "r", "g", "b", "a", "mult", "color", "(IIIIF)I", "WIDTH", "F", "PANEL_RADIUS", "PAD", "TITLE_H", "HINT_H", "ROW_H", "ROW_GAP", "GAP_TO_ELEMENT", "OPEN_MS", "Lrtx/kimiko/api/drags/Draggable;", "", "items", "Ljava/util/List;", "", "openedAtMs", "J", "menuX", "menuY", "hovered", "I", "Item", "rtx.kimiko:kimiko"})
public final class HudContextMenu {
    @NotNull
    public static final HudContextMenu INSTANCE = new HudContextMenu();
    private static final float WIDTH = 96.0f;
    private static final float PANEL_RADIUS = 3.0f;
    private static final float PAD = 4.0f;
    private static final float TITLE_H = 11.0f;
    private static final float HINT_H = 8.5f;
    private static final float ROW_H = 13.0f;
    private static final float ROW_GAP = 1.5f;
    private static final float GAP_TO_ELEMENT = 6.0f;
    private static final float OPEN_MS = 140.0f;
    @Nullable
    private static Draggable owner;
    @NotNull
    private static List<Item> items;
    private static long openedAtMs;
    private static float menuX;
    private static float menuY;
    private static int hovered;

    private HudContextMenu() {
    }

    @JvmStatic
    public static final boolean isOpen() {
        return owner != null;
    }

    @JvmStatic
    @Nullable
    public static final Draggable owner() {
        return owner;
    }

    @JvmStatic
    public static final void close() {
        owner = null;
        items = CollectionsKt.emptyList();
        hovered = -1;
    }

    @JvmStatic
    public static final boolean open(@Nullable Draggable element) {
        if (element == null) {
            return false;
        }
        if (owner == element) {
            HudContextMenu.close();
            return true;
        }
        List<Item> next = element.contextItems();
        if (next.isEmpty()) {
            return false;
        }
        owner = element;
        items = next;
        openedAtMs = System.currentTimeMillis();
        INSTANCE.layout();
        return true;
    }

    private final void layout() {
        Draggable draggable = owner;
        if (draggable == null) {
            return;
        }
        Draggable target = draggable;
        float h = this.height();
        float ex = target.getDrag().getRenderX();
        float ey = target.getDrag().getRenderY();
        float ew = target.width();
        float screenW = Position.Companion.screenWidth();
        float screenH = Position.Companion.screenHeight();
        float right = ex + ew + 6.0f;
        menuX = right + 96.0f <= screenW - 2.0f ? right : ex - 96.0f - 6.0f;
        menuX = Math.max(2.0f, Math.min(menuX, screenW - 96.0f - 2.0f));
        menuY = Math.max(2.0f, Math.min(ey, screenH - h - 2.0f));
    }

    private final float height() {
        return 19.5f + (float)items.size() * 14.5f + 4.0f;
    }

    @JvmStatic
    public static final void render(@NotNull DrawContext graphics) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        Draggable draggable = owner;
        if (draggable == null) {
            return;
        }
        Draggable target = draggable;
        if (!target.isInteractive()) {
            HudContextMenu.close();
            return;
        }
        INSTANCE.layout();
        float t = Math.min(1.0f, (float)(System.currentTimeMillis() - openedAtMs) / 140.0f);
        float alpha = t * t * (3.0f - 2.0f * t);
        float h = INSTANCE.height();
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        Render2D.beginFrame(graphics);
        INSTANCE.drawElementGhost(target, alpha);
        RectUtil.drawClientRectFixedRadius(menuX, menuY, 96.0f, h, 3.0f, alpha, 0.0f);
        float markX = menuX + 4.0f + 1.0f;
        float titleY = menuY + 4.0f - 0.5f;
        Render2D.rect(markX, titleY + 1.0f, 2.0f, 5.5f, 1.0f, ClientAccent.accentBrightAt((float)230 * alpha, markX, titleY));
        Fonts.SEMIBOLD.draw(I18n.tr(target.contextTitle()), markX + 6.0f, titleY, 6.5f, INSTANCE.color(255, 255, 255, 235, alpha));
        String hint = I18n.tr(target.contextHint());
        if (((CharSequence)hint).length() > 0) {
            Fonts.MEDIUM.draw(hint, menuX + 4.0f, menuY + 11.0f + 0.5f, 4.6f, INSTANCE.color(255, 255, 255, 105, alpha));
        }
        float rowY = menuY + 11.0f + 8.5f;
        hovered = -1;
        int n = ((Collection)items).size();
        for (int i = 0; i < n; ++i) {
            boolean isHovered;
            Item item = items.get(i);
            boolean isSelected = INSTANCE.safeSelected(item);
            boolean bl = isHovered = mx >= menuX + 4.0f && mx <= menuX + 96.0f - 4.0f && my >= rowY && my <= rowY + 13.0f;
            if (isHovered) {
                hovered = i;
            }
            float rx = menuX + 4.0f;
            float rw = 88.0f;
            if (isSelected) {
                AccentGradient.fillHorizontal(rx, rowY, rw, 13.0f, 3.5f, 3.5f, 3.5f, 3.5f, (float)220 * alpha);
            } else if (isHovered) {
                Render2D.rect(rx, rowY, rw, 13.0f, 3.5f, INSTANCE.color(255, 255, 255, 26, alpha));
            }
            int textColor = isSelected ? INSTANCE.color(255, 255, 255, 245, alpha) : INSTANCE.color(255, 255, 255, isHovered ? 220 : 165, alpha);
            Fonts.MEDIUM.draw(I18n.tr(item.label()), rx + 6.0f, rowY + 3.5f - 0.5f, 5.5f, textColor);
            rowY += 14.5f;
        }
        Render2D.flush();
    }

    private final void drawElementGhost(Draggable target, float alpha) {
        float x = target.getDrag().getRenderX();
        float y = target.getDrag().getRenderY();
        float w = target.width();
        float h = target.height();
        if (w <= 0.0f || h <= 0.0f) {
            return;
        }
        Render2D.outline(x, y, w, h, 3.0f, 0.6f, ClientAccent.accentBrightAt((float)150 * alpha, x + w * 0.5f, y + h * 0.5f));
    }

    @JvmStatic
    public static final boolean click(float mx, float my) {
        if (owner == null) {
            return false;
        }
        float h = INSTANCE.height();
        if (mx < menuX || mx > menuX + 96.0f || my < menuY || my > menuY + h) {
            HudContextMenu.close();
            return false;
        }
        if (hovered >= 0 && hovered < items.size()) {
            try {
                items.get(hovered).action().run();
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            ConfigManager.Companion.markDirty();
        }
        return true;
    }

    private final boolean safeSelected(Item item) {
        boolean bl;
        try {
            bl = item.selected().getAsBoolean();
        }
        catch (Throwable throwable) {
            bl = false;
        }
        return bl;
    }

    private final int color(int r, int g, int b, int a, float mult) {
        int fa = Math.max(0, Math.min(255, MathKt.roundToInt((float)((float)a * mult))));
        return fa <= 0 ? 0 : new Color(r, g, b, fa).getRGB();
    }

    static {
        items = CollectionsKt.emptyList();
        hovered = -1;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ.\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00c6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u0017\u001a\u00020\u0016H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0011\u0010\u0019\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0019\u0010\u000bR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u0003\u0010\u000bR%\u0010\u0005\u001a\u00020\u00048\u0007z\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001d\u001a\u0004\b\u0005\u0010\rR%\u0010\u0007\u001a\u00020\u00068\u0007z\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b\u0007\u0010\u000f\u00a8\u0006\u001f"}, d2={"Lrtx/kimiko/api/drags/HudContextMenu$Item;", "", "", "label", "Ljava/util/function/BooleanSupplier;", "selected", "Ljava/lang/Runnable;", "action", "<init>", "(Ljava/lang/String;Ljava/util/function/BooleanSupplier;Ljava/lang/Runnable;)V", "component1", "()Ljava/lang/String;", "component2", "()Ljava/util/function/BooleanSupplier;", "component3", "()Ljava/lang/Runnable;", "copy", "(Ljava/lang/String;Ljava/util/function/BooleanSupplier;Ljava/lang/Runnable;)Lrtx/kimiko/api/drags/HudContextMenu$Item;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Lkotlin/jvm/JvmName;", "name", "Ljava/lang/String;", "Ljava/util/function/BooleanSupplier;", "Ljava/lang/Runnable;", "rtx.kimiko:kimiko"})
    public static final class Item {
        @NotNull
        private final String label;
        @NotNull
        private final BooleanSupplier selected;
        @NotNull
        private final Runnable action;

        public Item(@NotNull String label, @NotNull BooleanSupplier selected, @NotNull Runnable action) {
            Intrinsics.checkNotNullParameter((Object)label, (String)"label");
            Intrinsics.checkNotNullParameter((Object)selected, (String)"selected");
            Intrinsics.checkNotNullParameter((Object)action, (String)"action");
            this.label = label;
            this.selected = selected;
            this.action = action;
        }

        @JvmName(name="label")
        @NotNull
        public final String label() {
            return this.label;
        }

        @JvmName(name="selected")
        @NotNull
        public final BooleanSupplier selected() {
            return this.selected;
        }

        @JvmName(name="action")
        @NotNull
        public final Runnable action() {
            return this.action;
        }

        @NotNull
        public final String component1() {
            return this.label;
        }

        @NotNull
        public final BooleanSupplier component2() {
            return this.selected;
        }

        @NotNull
        public final Runnable component3() {
            return this.action;
        }

        @NotNull
        public final Item copy(@NotNull String label, @NotNull BooleanSupplier selected, @NotNull Runnable action) {
            Intrinsics.checkNotNullParameter((Object)label, (String)"label");
            Intrinsics.checkNotNullParameter((Object)selected, (String)"selected");
            Intrinsics.checkNotNullParameter((Object)action, (String)"action");
            return new Item(label, selected, action);
        }

        public static /* synthetic */ Item copy$default(Item item, String string, BooleanSupplier booleanSupplier, Runnable runnable, int n, Object object) {
            if ((n & 1) != 0) {
                string = item.label;
            }
            if ((n & 2) != 0) {
                booleanSupplier = item.selected;
            }
            if ((n & 4) != 0) {
                runnable = item.action;
            }
            return item.copy(string, booleanSupplier, runnable);
        }

        @NotNull
        public String toString() {
            return "Item(label=" + this.label + ", selected=" + this.selected + ", action=" + this.action + ")";
        }

        public int hashCode() {
            int result = this.label.hashCode();
            result = result * 31 + this.selected.hashCode();
            result = result * 31 + this.action.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Item)) {
                return false;
            }
            Item item = (Item)other;
            if (!Intrinsics.areEqual((Object)this.label, (Object)item.label)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.selected, (Object)item.selected)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.action, (Object)item.action);
        }
    }
}

