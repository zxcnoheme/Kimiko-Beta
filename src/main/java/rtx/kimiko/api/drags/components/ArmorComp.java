/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.EquipmentSlot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.Items
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.font.TextRenderer
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 */
package rtx.kimiko.api.drags.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import rtx.kimiko.api.drags.DragSystem;
import rtx.kimiko.api.drags.Draggable;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.ArmorModule;
import rtx.kimiko.utils.animations.HudFadeAnimation;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 /2\u00020\u0001:\u0001/B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\n\u0010\tJ\u000f\u0010\u000b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u000b\u0010\tJ\u000f\u0010\f\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\f\u0010\tJ\u000f\u0010\u000e\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0014\u00a2\u0006\u0004\b\u0013\u0010\u0014J/\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001f\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010!\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b!\u0010 R\u0016\u0010\"\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\"\u0010 R\u0016\u0010#\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010%\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u0010$R\u0016\u0010&\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0016\u0010(\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010'R\u0016\u0010)\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010'R\u0016\u0010*\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010'R\u001c\u0010-\u001a\b\u0012\u0004\u0012\u00020,0+8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b-\u0010.\u00a8\u00060"}, d2={"Lrtx/kimiko/api/drags/components/ArmorComp;", "Lrtx/kimiko/api/drags/Draggable;", "<init>", "()V", "", "displayName", "()Ljava/lang/String;", "", "width", "()F", "height", "overlayWidth", "overlayHeight", "", "isInteractive", "()Z", "Lnet/minecraft/DrawContext;", "graphics", "", "render", "(Lnet/minecraft/DrawContext;)V", "dragging", "fits", "shortLen", "screenW", "updateSide", "(ZZFF)V", "Lrtx/kimiko/utils/animations/HudFadeAnimation;", "visibility", "Lrtx/kimiko/utils/animations/HudFadeAnimation;", "", "side", "I", "previewSide", "sideBeforeDrag", "wasDragging", "Z", "sideResolved", "widthValue", "F", "heightValue", "previewWidth", "previewHeight", "", "Lnet/minecraft/ItemStack;", "shownItems", "Ljava/util/List;", "Companion", "rtx.kimiko:kimiko"})
public final class ArmorComp
extends Draggable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final HudFadeAnimation visibility = this.getHudFade();
    private int side;
    private int previewSide;
    private int sideBeforeDrag;
    private boolean wasDragging;
    private boolean sideResolved;
    private float widthValue = 20.0f;
    private float heightValue = 20.0f;
    private float previewWidth = this.widthValue;
    private float previewHeight = this.heightValue;
    @NotNull
    private List<ItemStack> shownItems = CollectionsKt.emptyList();
    private static final float ITEM = 16.0f;
    private static final float PAD = 2.0f;
    private static final float RADIUS = 4.0f;
    private static final float EDGE_ENTER = 16.0f;
    private static final float EDGE_EXIT = 26.0f;
    @NotNull
    private static final EquipmentSlot[] ARMOR;

    public ArmorComp() {
        super("armor", 166.0f, 5.0f);
        this.visibility.set(0.0);
    }

    @Override
    @NotNull
    public String displayName() {
        return "Armor";
    }

    @Override
    public float width() {
        return this.widthValue;
    }

    @Override
    public float height() {
        return this.heightValue;
    }

    @Override
    public float overlayWidth() {
        return this.previewWidth;
    }

    @Override
    public float overlayHeight() {
        return this.previewHeight;
    }

    @Override
    public boolean isInteractive() {
        return ArmorComp.Companion.componentEnabled();
    }

    @Override
    protected void render(@NotNull DrawContext graphics) {
        List live;
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        boolean enabled = ArmorComp.Companion.componentEnabled();
        boolean dragMode = DragSystem.Companion.get().isDragModeActive();
        List list = live = enabled ? ArmorComp.Companion.collectItems() : CollectionsKt.emptyList();
        if (live.isEmpty() && enabled && dragMode) {
            live = ArmorComp.Companion.previewItems();
        }
        if (!((Collection)live).isEmpty()) {
            this.shownItems = live;
        }
        boolean targetVisible = enabled && !((Collection)live).isEmpty();
        this.visibility.updateTarget(targetVisible);
        float alpha = this.visibility.get();
        if (alpha <= 0.01f || this.shownItems.isEmpty()) {
            return;
        }
        List<ItemStack> items = this.shownItems;
        float longLen = 4.0f + (float)items.size() * 16.0f;
        float shortLen = 20.0f;
        float screenW = Math.max(1.0f, Position.Companion.screenWidth());
        boolean fits = longLen <= screenW - 10.0f;
        this.updateSide(this.getDrag().isDragging(), fits, shortLen, screenW);
        this.widthValue = ArmorComp.Companion.orientWidth(this.side, longLen, shortLen);
        this.heightValue = ArmorComp.Companion.orientHeight(this.side, longLen, shortLen);
        this.previewWidth = ArmorComp.Companion.orientWidth(this.previewSide, longLen, shortLen);
        this.previewHeight = ArmorComp.Companion.orientHeight(this.previewSide, longLen, shortLen);
        boolean vertical = this.side != 0;
        float x = this.getX();
        float y = this.getY();
        float scale = 0.96f + alpha * 0.04f;
        float originX = x + this.widthValue * 0.5f;
        float originY = y + this.heightValue * 0.5f;
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(originX, originY);
        graphics.getMatrices().scale(scale);
        graphics.getMatrices().translate(-originX, -originY);
        Render2D.beginFrame(graphics);
        RectUtil.drawClientRect(x, y, this.widthValue, this.heightValue, 4.0f, alpha);
        Render2D.flush();
        TextRenderer textRenderer2 = MinecraftClient.getInstance().textRenderer;
        Intrinsics.checkNotNullExpressionValue((Object)textRenderer2, (String)"font");
        TextRenderer font = textRenderer2;
        float half = 8.0f;
        float itemScale = 1.0f * Math.max(0.0f, Math.min(1.0f, alpha));
        float offset = 0.0f;
        for (ItemStack stack : items) {
            float itemX = x + 2.0f + (vertical ? 0.0f : offset);
            float itemY = y + 2.0f + (vertical ? offset : 0.0f);
            graphics.getMatrices().pushMatrix();
            Matrix3x2fStack matrix3x2fStack = graphics.getMatrices();
            Intrinsics.checkNotNullExpressionValue((Object)matrix3x2fStack, (String)"pose(...)");
            Render2DCoordinateSpace.applyGuiScaleIndependence((Matrix3x2f)matrix3x2fStack);
            graphics.getMatrices().translate(itemX + half, itemY + half);
            graphics.getMatrices().scale(itemScale, itemScale);
            graphics.getMatrices().translate(-8.0f, -8.0f);
            graphics.drawItem(stack, 0, 0);
            graphics.drawStackOverlay(font, stack, 0, 0);
            graphics.getMatrices().popMatrix();
            offset += 16.0f;
        }
        graphics.getMatrices().popMatrix();
    }

    private final void updateSide(boolean dragging, boolean fits, float shortLen, float screenW) {
        boolean justStarted = !this.wasDragging && dragging;
        boolean justReleased = this.wasDragging && !dragging;
        this.wasDragging = dragging;
        if (justStarted) {
            this.sideBeforeDrag = this.side;
        }
        if (dragging) {
            int next = fits ? ArmorComp.Companion.edgeSide(Position.Companion.mouseX(), screenW, this.previewSide) : 0;
            if (next != 0 != (this.previewSide != 0)) {
                this.getDrag().swapGrabOffset();
            }
            this.previewSide = next;
            this.sideResolved = true;
            return;
        }
        if (justReleased) {
            this.side = this.getDrag().wasCancelled() ? this.sideBeforeDrag : this.previewSide;
        } else if (!this.sideResolved) {
            this.side = fits ? ArmorComp.Companion.dockFromPosition(this.getDrag().getTargetX(), shortLen, screenW) : 0;
            this.sideResolved = true;
        }
        this.previewSide = this.side;
        if (this.side > 0) {
            this.getDrag().setTargetX(screenW - shortLen - 5.0f);
        } else if (this.side < 0) {
            this.getDrag().setTargetX(5.0f);
        }
    }

    static {
        EquipmentSlot[] class_1304Array = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
        ARMOR = class_1304Array;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u0015\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002\u00a2\u0006\u0004\b\u000b\u0010\nJ'\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J'\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ'\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001dR\u0014\u0010 \u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010\u001dR\u0014\u0010!\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b!\u0010\u001dR\u001a\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\"8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b$\u0010%\u00a8\u0006&"}, d2={"Lrtx/kimiko/api/drags/components/ArmorComp.Companion;", "", "<init>", "()V", "", "componentEnabled", "()Z", "", "Lnet/minecraft/ItemStack;", "collectItems", "()Ljava/util/List;", "previewItems", "", "mx", "screenW", "", "current", "edgeSide", "(FFI)I", "x", "shortLen", "dockFromPosition", "(FFF)I", "side", "longLen", "orientWidth", "(IFF)F", "orientHeight", "ITEM", "F", "PAD", "RADIUS", "EDGE_ENTER", "EDGE_EXIT", "", "Lnet/minecraft/EquipmentSlot;", "ARMOR", "[Lnet/minecraft/EquipmentSlot;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final boolean componentEnabled() {
            ArmorModule module = ModuleManager.Companion.get().get(ArmorModule.class);
            return module != null && module.isEnabled();
        }

        private final List<ItemStack> collectItems() {
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient mc = minecraftClient2;
            ArrayList<ItemStack> items = new ArrayList<ItemStack>(ARMOR.length);
            ClientPlayerEntity player = mc.player;
            if (player != null) {
                for (EquipmentSlot slot : ARMOR) {
                    ItemStack stack = (ItemStack) (player.getEquippedStack(slot));
                    if (stack.isEmpty()) continue;
                    items.add(stack);
                }
            }
            return items;
        }

        private final List<ItemStack> previewItems() {
            ArrayList<ItemStack> items = new ArrayList<ItemStack>(4);
            items.add(Items.DIAMOND_HELMET.getDefaultStack());
            items.add(Items.DIAMOND_CHESTPLATE.getDefaultStack());
            items.add(Items.DIAMOND_LEGGINGS.getDefaultStack());
            items.add(Items.DIAMOND_BOOTS.getDefaultStack());
            return items;
        }

        private final int edgeSide(float mx, float screenW, int current) {
            if (current > 0) {
                return mx < screenW - 26.0f ? 0 : 1;
            }
            if (current < 0) {
                return mx > 26.0f ? 0 : -1;
            }
            if (mx >= screenW - 16.0f) {
                return 1;
            }
            if (mx <= 16.0f) {
                return -1;
            }
            return 0;
        }

        private final int dockFromPosition(float x, float shortLen, float screenW) {
            if (x >= screenW - shortLen - 5.0f - 1.0f) {
                return 1;
            }
            if (x <= 6.0f) {
                return -1;
            }
            return 0;
        }

        private final float orientWidth(int side, float longLen, float shortLen) {
            return side != 0 ? shortLen : longLen;
        }

        private final float orientHeight(int side, float longLen, float shortLen) {
            return side != 0 ? longLen : shortLen;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

