package rtx.kimiko.api.modules.impl.Utils;

import net.minecraft.util.math.MathHelper;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BindSetting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;

/**
 * Копировать координаты: клавиша кладёт XYZ в буфер обмена.
 */
public class CopyCoordsModule extends Module {
    private final BindSetting bind;
    private final ModeSetting format;
    private final BooleanSetting withDirection;
    private boolean lastDown;

    public CopyCoordsModule() {
        super("Copy Coords NEW", "Клавиша копирует твои координаты в буфер обмена.", Category.UTILS);
        this.bind = (BindSetting) this.register((Setting) new BindSetting("Клавиша", "Клавиша копирования координат.").setKey(-1));
        this.format = (ModeSetting) this.register((Setting) new ModeSetting("Формат", "Формат копирования.", "x y z", "x y z", "x: y: z:", "/tp x y z"));
        this.withDirection = (BooleanSetting) this.register((Setting) new BooleanSetting("Сторона света", "Добавить компасное направление.", false));
        this.lastDown = false;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled() || this.mc.player == null) {
            return;
        }
        boolean down = this.bind.getValue().isDown(this.mc.getWindow().getHandle());
        if (down && !this.lastDown) {
            int x = MathHelper.floor(this.mc.player.getX());
            int y = MathHelper.floor(this.mc.player.getY());
            int z = MathHelper.floor(this.mc.player.getZ());
            String out;
            switch (this.format.getValue()) {
                case "x: y: z:" -> out = "x: " + x + "  y: " + y + "  z: " + z;
                case "/tp x y z" -> out = "/tp " + x + " " + y + " " + z;
                default -> out = x + " " + y + " " + z;
            }
            if (this.withDirection.getValue()) {
                out = out + "  (" + compassDirection() + ")";
            }
            this.mc.keyboard.setClipboard(out);
        }
        this.lastDown = down;
    }

    private String compassDirection() {
        float yaw = MathHelper.wrapDegrees(this.mc.player.getYaw());
        String[] dirs = {"Юг", "Юго-Запад", "Запад", "Северо-Запад", "Север", "Северо-Восток", "Восток", "Юго-Восток"};
        int idx = MathHelper.floor((yaw + 360.0f + 22.5f) / 45.0f) % 8;
        return dirs[idx];
    }
}
