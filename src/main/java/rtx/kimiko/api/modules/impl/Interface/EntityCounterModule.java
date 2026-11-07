package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
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
 * Entity Counter NEW: HUD-карточка со счётчиками мобов, предметов и игроков
 * вокруг игрока.
 */
public class EntityCounterModule extends Module {
    private static final int SCAN_LIMIT = 100;

    private final SliderSetting radius;

    public EntityCounterModule() {
        super("Entity Counter NEW", "Счётчик мобов, предметов и игроков в радиусе вокруг тебя.", Category.DISPLAY);
        this.radius = (SliderSetting) this.register((Setting) new SliderSetting("Радиус", "Радиус подсчёта (блоков).").setValue(16.0f).range(4.0f, 48.0f));
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.world == null || this.mc.player == null) {
            return;
        }

        double r2 = this.radius.getValue() * this.radius.getValue();
        double px = this.mc.player.getX();
        double py = this.mc.player.getY();
        double pz = this.mc.player.getZ();

        int mobs = 0;
        int items = 0;
        int players = 0;
        int scanned = 0;
        for (Entity e : this.mc.world.getEntities()) {
            if (scanned++ >= SCAN_LIMIT) {
                break;
            }
            if (e == this.mc.player) {
                continue;
            }
            if (e.squaredDistanceTo(px, py, pz) > r2) {
                continue;
            }
            if (e instanceof ItemEntity) {
                items++;
            } else if (e instanceof PlayerEntity) {
                players++;
            } else if (e instanceof LivingEntity) {
                mobs++;
            }
        }

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;

        String title = "Сущности · " + (int) this.radius.getValue() + "м";
        float cardW = 124.0f;
        float cardH = 40.0f;
        float x = w - cardW - 14.0f;
        float y = 96.0f;

        Render2D.rect(x, y, cardW, cardH, 9.0f, MenuColors.rgba(10, 12, 18, 200));
        Render2D.outline(x, y, cardW, cardH, 9.0f, 1.0f, MenuColors.rgba(255, 255, 255, 24));
        Fonts.SEMIBOLD.draw(title, x + 9.0f, y + 5.0f, 8.0f, MenuColors.rgba(235, 240, 250, 215));

        Fonts.SEMIBOLD.draw("Мобы " + mobs, x + 9.0f, y + 17.0f, 7.5f, MenuColors.rgba(255, 150, 150, 220));
        Fonts.SEMIBOLD.draw("Предметы " + items, x + 9.0f, y + 27.0f, 7.5f, MenuColors.rgba(250, 210, 130, 220));
        Fonts.SEMIBOLD.draw("Игроки " + players, x + 9.0f, y + 37.0f, 7.5f, MenuColors.rgba(150, 200, 255, 220));
        Render2D.flush();
    }
}
