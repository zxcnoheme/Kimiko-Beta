package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Ореол факела: когда в руке факел и вокруг темно — тёплый мягкий
 * радиальный ореол в центре экрана. Соул-факел светится голубым.
 * Ореол собран из концентрических кругов с нарастающей альфой —
 * получается тот же градиент, что у процедурной текстуры-круга 64×64.
 */
public class HandLightLiteModule extends Module {
    private static final int RINGS = 6;

    private final SliderSetting strength;

    public HandLightLiteModule() {
        super("Torch Halo NEW", "Тёплый ореол в центре экрана, когда держишь факел в темноте. Соул-факел — голубой.", Category.VISUALS);
        this.strength = (SliderSetting) this.register((Setting) new SliderSetting("Сила", "Яркость ореола.").setValue(1.0f).range(0.2f, 2.0f));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || world.getAmbientDarkness() < 2) {
            return;
        }
        ItemStack main = this.mc.player.getMainHandStack();
        ItemStack off = this.mc.player.getOffHandStack();
        boolean torch = main.isOf(Items.TORCH) || off.isOf(Items.TORCH);
        boolean soul = main.isOf(Items.SOUL_TORCH) || off.isOf(Items.SOUL_TORCH);
        if (!torch && !soul) {
            return;
        }
        float pulse = 0.10f + 0.06f * (0.5f + 0.5f * (float) Math.sin(System.currentTimeMillis() * 0.0028));
        float maxAlpha = pulse * this.strength.getValue();

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;
        float h = this.mc.getWindow().getScaledHeight() / sf;
        float cx = w * 0.5f;
        float cy = h * 0.5f;
        float outer = Math.min(w, h) * 0.42f;
        int r = soul ? 122 : 255;
        int g = soul ? 200 : 176;
        int b = soul ? 255 : 92;
        for (int i = 0; i < RINGS; i++) {
            float t = i / (float) (RINGS - 1);
            float radius = outer * (1.0f - 0.70f * t);
            float alpha = maxAlpha * t * t * 0.45f;
            if (alpha <= 0.002f) {
                continue;
            }
            Render2D.circle(cx, cy, radius, 32, MenuColors.rgba(r, g, b, (int) (alpha * 255.0f)));
        }
        Render2D.flush();
    }
}
