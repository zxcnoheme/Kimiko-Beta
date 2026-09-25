/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  net.minecraft.util.Arm
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Interface;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import net.minecraft.util.Arm;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.InterfaceComponentModule;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.others.hud.HotbarItemAnimator;
import rtx.kimiko.utils.render.render2d.ClientPalette;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Feature(value={"customhotbar"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u0017\u0010\b\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0004\b\b\u0010\tR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\fR\u0014\u0010\u0011\u001a\u00020\r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u000f\u00ca\u0001\u0010\b\u0013\u0012\f\b\u0014\u0012\b\b\fJ\u0004\b\b(\u0015\u00a8\u0006\u0016"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/CustomHotbar;", "Lrtx/kimiko/api/modules/impl/Interface/InterfaceComponentModule;", "<init>", "()V", "", "onDisable", "Lnet/minecraft/DrawContext;", "graphics", "render", "(Lnet/minecraft/DrawContext;)V", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "itemAnimation", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "itemTilt", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "useResponse", "slotPitch", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "customhotbar", "rtx.kimiko:kimiko"})
public final class CustomHotbar
extends InterfaceComponentModule {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final BooleanSetting itemAnimation = (BooleanSetting)this.register((Setting)new BooleanSetting("Анимация предмета", "Предмет в выбранном слоте живёт: подрастает и приподнимается.", true));
    @NotNull
    private final SliderSetting itemTilt = (SliderSetting)this.register((Setting)new SliderSetting("Сила наклона", "Насколько сильно предметы кренятся под едущим выделением.").range(0, 150).increment(5).setValue(100.0f).visible(() -> CustomHotbar.itemTilt$lambda$0(this)));
    @NotNull
    private final BooleanSetting useResponse = (BooleanSetting)this.register((Setting)new BooleanSetting("Отклик на действие", "Толчок предмета при ударе и использовании.", true).visible(() -> CustomHotbar.useResponse$lambda$0(this)));
    @NotNull
    private final SliderSetting slotPitch = (SliderSetting)this.register((Setting)new SliderSetting("Отступ между предметами", "Расстояние между центрами предметов в пикселях, 20 — как в ванили.").range(20, 26).increment(1).setValue(22.0f));
    private static final float PANEL_HEIGHT = 22.0f;
    private static final float ITEM_LIFT = 4.0f;
    private static final float CELL_OUTLINE_THICKNESS = 0.5f;
    private static final float CELL_OUTLINE_ALPHA = 0.4117647f;

    public CustomHotbar() {
        super("Custom Hotbar", "Заменяет ванильный хотбар клиентским стеклянным дизайном.");
    }

    @Override
    protected void onDisable() {
        HotbarItemAnimator.reset();
    }

    public final void render(@Nullable DrawContext graphics) {
        ClientPlayerEntity player = this.mc.player;
        if (!this.isEnabled() || graphics == null || player == null) {
            return;
        }
        boolean animated = this.itemAnimation.getValue();
        HotbarItemAnimator.beginFrame((PlayerEntity)player, graphics.getScaledWindowWidth() / 2, this.slotPitch.getFloat(), animated ? 4.0f : 0.0f, animated ? this.itemTilt.getFloat() / 100.0f : 0.0f, animated && this.useResponse.getValue());
        float y = (float)graphics.getScaledWindowHeight() - 22.0f;
        float panelX = HotbarItemAnimator.panelLeft();
        float panelWidth = HotbarItemAnimator.panelWidth();
        float nativeScale = (float)Render2DCoordinateSpace.guiScale() / Render2DCoordinateSpace.designGuiScale();
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().scale(nativeScale);
        Render2D.beginFrame(graphics);
        RectUtil.drawClientRect(panelX, y, panelWidth, 22.0f, CustomHotbar.Companion.cornerRadius(panelWidth, 22.0f), 1.0f);
        float cellSize = 20.0f;
        float cellX = HotbarItemAnimator.selectorCenterX() - cellSize * 0.5f;
        float cellY = y + 1.0f;
        float cellRadius = CustomHotbar.Companion.cornerRadius(cellSize, cellSize);
        RectUtil.drawClientRectNoGlow(cellX, cellY, cellSize, cellSize, cellRadius, 0.9f);
        int[] selectorOutline = ClientPalette.cornerColors(0.4117647f);
        Render2D.outline(cellX, cellY, cellSize, cellSize, cellRadius, 0.5f, selectorOutline[0], selectorOutline[1], selectorOutline[2], selectorOutline[3]);
        if (!player.getOffHandStack().isEmpty()) {
            boolean left = player.getMainArm().getOpposite() == Arm.LEFT;
            float offhandSize = 24.0f;
            float offhandX = HotbarItemAnimator.offhandBoxX(left);
            float offhandY = y - 1.0f;
            float offhandRadius = CustomHotbar.Companion.cornerRadius(offhandSize, offhandSize);
            RectUtil.drawClientRect(offhandX, offhandY, offhandSize, offhandSize, offhandRadius, 1.0f);
            int[] offhandOutline = ClientPalette.cornerColors(0.5647059f);
            Render2D.outline(offhandX, offhandY, offhandSize, offhandSize, offhandRadius, 0.5f, offhandOutline[0], offhandOutline[1], offhandOutline[2], offhandOutline[3]);
        }
        Render2D.flush();
        graphics.getMatrices().popMatrix();
    }

    private static final Boolean itemTilt$lambda$0(CustomHotbar this$0) {
        return this$0.itemAnimation.getValue();
    }

    private static final Boolean useResponse$lambda$0(CustomHotbar this$0) {
        return this$0.itemAnimation.getValue();
    }

    @JvmStatic
    @Nullable
    public static final CustomHotbar getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final boolean isActive() {
        return Companion.isActive();
    }

    @JvmStatic
    public static final float cellCornerRadius() {
        return Companion.cellCornerRadius();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\nJ\u0013\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0013R\u0014\u0010\u0016\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0013\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/CustomHotbar.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Interface/CustomHotbar;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Interface/CustomHotbar;", "", "isActive", "()Z", "", "cellCornerRadius", "()F", "width", "height", "cornerRadius", "(FF)F", "PANEL_HEIGHT", "F", "ITEM_LIFT", "CELL_OUTLINE_THICKNESS", "CELL_OUTLINE_ALPHA", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final CustomHotbar getInstance() {
            return ModuleManager.Companion.get().get(CustomHotbar.class);
        }

        @JvmStatic
        public final boolean isActive() {
            CustomHotbar module = this.getInstance();
            return module != null && module.isEnabled();
        }

        @JvmStatic
        public final float cellCornerRadius() {
            return this.cornerRadius(20.0f, 20.0f);
        }

        private final float cornerRadius(float width, float height) {
            InterfaceModule module = InterfaceModule.Companion.getInstance();
            Object object = module;
            float radius = object != null && (object = ((InterfaceModule)object).rectCornerRadius) != null ? ((SliderSetting)object).getFloat() : 7.0f;
            return Math.min(radius, Math.min(width, height) * 0.5f);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

