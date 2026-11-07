package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Deep Water Fade: глубоко под водой экран плавно темнеет синим —
 * чем дальше глаза под поверхностью, тем сильнее.
 */
public class DeepWaterFadeModule extends Module {
    private static final int MAX_DEPTH = 12;
    private static final int MIN_DEPTH = 8;

    private final SliderSetting strength;

    public DeepWaterFadeModule() {
        super("Deep Water Fade NEW", "Экран темнеет синим на большой глубине под водой.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Насколько сильно темнеет экран.").setValue(1.0f).range(0.2f, 2.0f));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || !this.mc.player.isSubmergedInWater()) {
            return;
        }
        BlockPos eye = BlockPos.ofFloored(this.mc.player.getX(), this.mc.player.getEyeY(), this.mc.player.getZ());
        if (world.getBlockState(eye).getFluidState().isEmpty()) {
            return;
        }
        // глубина: поднимаемся от уровня глаз, пока жидкость (до 12 блоков)
        int depth = 0;
        for (int i = 1; i <= MAX_DEPTH; i++) {
            if (world.getBlockState(eye.up(i)).getFluidState().isEmpty()) {
                break;
            }
            depth++;
        }
        if (depth < MIN_DEPTH) {
            return;
        }
        float a = (depth / (float) MAX_DEPTH) * 0.45f * this.strength.getValue();
        int alpha = (int) (Math.min(1.0f, a) * 255.0f);
        if (alpha <= 0) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        Render2D.rect(0.0f, 0.0f, w, h, 0.0f, MenuColors.rgba(4, 16, 36, alpha));
        Render2D.flush();
    }
}
