package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.client.sound.PositionedSoundInstance;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Предупреждение износа: броня почти сломана — индикатор сверху экрана
 * + однократный звук предупреждения.
 */
public class DurabilityWarningModule extends Module {
    private final SliderSetting threshold;
    private boolean warned;

    public DurabilityWarningModule() {
        super("Armor Warn NEW", "Броня на грани поломки — индикатор и звук.", Category.DISPLAY);
        this.threshold = (SliderSetting) this.register((Setting) new SliderSetting("Порог", "Доля прочности для предупреждения.").setValue(0.10f).range(0.03f, 0.3f));
        this.warned = false;
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null) {
            return;
        }
        String worst = null;
        for (EquipmentSlot slot : new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}) {
            ItemStack stack = this.mc.player.getEquippedStack(slot);
            if (stack.isEmpty() || stack.getMaxDamage() <= 0) {
                continue;
            }
            float left = 1.0f - (float) stack.getDamage() / (float) stack.getMaxDamage();
            if (left <= this.threshold.getValue()) {
                String name = switch (slot) {
                    case HEAD -> "Шлем";
                    case CHEST -> "Нагрудник";
                    case LEGS -> "Штаны";
                    default -> "Ботинки";
                };
                worst = name + " — " + (stack.getMaxDamage() - stack.getDamage()) + " прочн.";
                break;
            }
        }
        if (worst != null && !this.warned) {
            this.warned = true;
            this.mc.getSoundManager().play(PositionedSoundInstance.ui(SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(), 1.6f, 0.8f));
        }
        if (worst == null) {
            this.warned = false;
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float textW = Fonts.SEMIBOLD.width(worst, 8.0f);
        boolean blink = (System.currentTimeMillis() / 400L) % 2L == 0L;
        Render2D.rect(w * 0.5f - textW * 0.5f - 8.0f, 24.0f, textW + 16.0f, 14.0f, 7.0f,
                blink ? MenuColors.rgba(130, 25, 25, 200) : MenuColors.rgba(90, 18, 18, 170));
        Fonts.SEMIBOLD.draw(worst, w * 0.5f - textW * 0.5f, 27.5f, 8.0f, MenuColors.rgba(255, 130, 130, 240));
        Render2D.flush();
    }
}
