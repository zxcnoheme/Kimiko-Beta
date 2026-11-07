package rtx.kimiko.api.modules.impl.Visuals;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Вход в Незер: при смене измерения экран мягко накрывает
 * фиолетовая вспышка, которая плавно гаснет.
 */
public class NetherEntryModule extends Module {
    private static final long FLASH_MS = 800L;
    private static final int FLASH_COLOR = 0x7828C8;
    private static final int MAX_ALPHA = 120;

    private Identifier lastDim;
    private long flashStart;

    public NetherEntryModule() {
        super("Nether Entry FX NEW", "Фиолетовая вспышка-оверлей при смене измерения.", Category.VISUALS);
        this.flashStart = 0L;
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (this.mc.world == null) {
            this.lastDim = null;
            return;
        }
        Identifier dim = this.mc.world.getRegistryKey().getValue();
        if (this.lastDim == null) {
            this.lastDim = dim;
            return;
        }
        if (!dim.equals(this.lastDim)) {
            this.lastDim = dim;
            this.flashStart = System.currentTimeMillis();
        }
        if (this.flashStart == 0L) {
            return;
        }
        long age = System.currentTimeMillis() - this.flashStart;
        if (age > FLASH_MS) {
            return;
        }
        float t = (float) age / (float) FLASH_MS;
        float ease = 1.0f - (1.0f - t) * (1.0f - t);
        int a = (int) ((1.0f - ease) * MAX_ALPHA);
        if (a <= 0) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        Render2D.rect(0.0f, 0.0f, this.mc.getWindow().getScaledWidth() / sf,
                this.mc.getWindow().getScaledHeight() / sf, 0.0f, (a << 24) | FLASH_COLOR);
        Render2D.flush();
    }
}
