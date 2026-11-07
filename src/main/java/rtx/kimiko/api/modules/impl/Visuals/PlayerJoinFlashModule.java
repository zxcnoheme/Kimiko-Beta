package rtx.kimiko.api.modules.impl.Visuals;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.MathHelper;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.utils.render.render2d.Render2D;

/**
 * Вспышка на присоединение: когда на сервер заходит новый игрок —
 * короткая зелёная вспышка-оверлей и счастливые частицы вокруг него.
 */
public class PlayerJoinFlashModule extends Module {
    private static final long FLASH_MS = 400L;
    private static final int FLASH_COLOR = 0x46E06B;
    private static final int MAX_ALPHA = 80;

    private final BooleanSetting onlyFlash;
    private final Set<String> knownPlayers = new HashSet<>();
    private boolean initialized;
    private long flashStart;
    private int tickCounter;

    public PlayerJoinFlashModule() {
        super("Player Join Flash NEW", "Зелёная вспышка и частицы при заходе нового игрока.", Category.VISUALS);
        this.onlyFlash = (BooleanSetting) this.register((Setting) new BooleanSetting("Только вспышка", "Показывать только вспышку, без частиц."));
        this.initialized = false;
        this.flashStart = 0L;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        if (this.mc.world == null || this.mc.player == null) {
            this.knownPlayers.clear();
            this.initialized = false;
            return;
        }
        if (++this.tickCounter % 10 != 0) {
            return;
        }
        for (AbstractClientPlayerEntity other : this.mc.world.getPlayers()) {
            if (other == this.mc.player) {
                continue;
            }
            String name = other.getName().getString();
            if (this.knownPlayers.contains(name)) {
                continue;
            }
            this.knownPlayers.add(name);
            if (!this.initialized) {
                continue;
            }
            this.flashStart = System.currentTimeMillis();
            if (this.onlyFlash.getValue() || !other.isAlive()) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                double ang = Math.random() * Math.PI * 2.0;
                double r = 0.3 + Math.random() * 0.5;
                this.mc.world.addParticleClient(ParticleTypes.HAPPY_VILLAGER,
                        other.getX() + MathHelper.cos((float) ang) * r,
                        other.getY() + 0.5 + Math.random() * 1.3,
                        other.getZ() + MathHelper.sin((float) ang) * r,
                        (Math.random() - 0.5) * 0.02, 0.02, (Math.random() - 0.5) * 0.02);
            }
        }
        this.initialized = true;
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.flashStart == 0L) {
            return;
        }
        long age = System.currentTimeMillis() - this.flashStart;
        if (age > FLASH_MS) {
            return;
        }
        float t = (float) age / (float) FLASH_MS;
        int a = (int) ((1.0f - t) * MAX_ALPHA);
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
