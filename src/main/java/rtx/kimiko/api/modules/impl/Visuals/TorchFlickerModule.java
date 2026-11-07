package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.render2d.Render2D;

import java.util.Random;

/**
 * Мерцание факела: держишь факел в темноте — по экрану плавно колеблется
 * тёплый отсвет, чуть ярче к центру.
 */
public class TorchFlickerModule extends Module {
    private static final Random RANDOM = new Random();

    private final SliderSetting strength;
    private float current;
    private float target;

    public TorchFlickerModule() {
        super("Torch Flicker NEW", "Тёплый живой отсвет факела в руке, когда вокруг темно.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Яркость отсвета.").setValue(0.6f).range(0.1f, 1.0f));
        this.current = 12.0f;
        this.target = 12.0f;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled() || !this.isHoldingTorch()) {
            return;
        }
        this.target = 8.0f + RANDOM.nextInt(13);
        this.current += (this.target - this.current) * 0.35f;
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null || this.mc.world == null) {
            return;
        }
        if (!this.isHoldingTorch() || this.mc.world.getAmbientDarkness() < 2) {
            return;
        }
        float alpha = Math.max(4.0f, this.current) * this.strength.getValue();

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        int edge = MenuColors.rgba(255, 176, 84, Math.max(6, (int) (alpha * 1.15f)));
        int center = MenuColors.rgba(255, 204, 122, Math.max(3, (int) (alpha * 0.55f)));
        // края чуть глубже, к центру светлее
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f, edge, edge, edge, edge);
        float iw = w * 0.55f;
        float ih = h * 0.55f;
        Render2D.rect(w * 0.5f - iw * 0.5f, h * 0.5f - ih * 0.5f, iw, ih, iw * 0.35f,
                center, center, center, center);
        Render2D.flush();
    }

    private boolean isHoldingTorch() {
        if (this.mc.player == null) {
            return false;
        }
        ItemStack main = this.mc.player.getMainHandStack();
        ItemStack off = this.mc.player.getOffHandStack();
        return main.isOf(Items.TORCH) || main.isOf(Items.SOUL_TORCH)
                || off.isOf(Items.TORCH) || off.isOf(Items.SOUL_TORCH);
    }
}
