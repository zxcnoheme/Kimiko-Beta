package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.util.math.MathHelper;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BindSetting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import net.minecraft.text.Text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Timestamp Screenshot Helper NEW: клавиша кладёт в буфер обмена имя следующего
 * ванильного скриншота (F2) вместе с сервером и координатами — удобно вести
 * архив скринов по имени файла.
 */
public class ScreenshotHelperModule extends Module {
    private static final DateTimeFormatter STAMP = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm.ss");

    private final BindSetting bind;
    private final BooleanSetting withServer;
    private final BooleanSetting withCoords;
    private final ModeSetting extra;
    private boolean lastDown;

    public ScreenshotHelperModule() {
        super("Timestamp Screenshot Helper NEW", "Клавиша копирует имя следующего скриншота (F2) с сервером и координатами.", Category.UTILS);
        this.bind = (BindSetting) this.register((Setting) new BindSetting("Клавиша", "Клавиша копирования имени скриншота.").setKey(-1));
        this.withServer = (BooleanSetting) this.register((Setting) new BooleanSetting("Сервер", "Добавить адрес сервера.", true));
        this.withCoords = (BooleanSetting) this.register((Setting) new BooleanSetting("Координаты", "Добавить координаты.", true));
        this.extra = (ModeSetting) this.register((Setting) new ModeSetting("Формат", "Разделитель данных.", " | ", " | ", " — "));
        this.lastDown = false;
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        boolean down = this.bind.getValue().isDown(this.mc.getWindow().getHandle());
        if (down && !this.lastDown) {
            String file = "screenshot_" + LocalDateTime.now().format(STAMP) + ".png";
            StringBuilder sb = new StringBuilder(file);
            if (this.withServer.getValue()) {
                sb.append(this.extra.getValue())
                        .append(this.mc.getCurrentServerEntry() != null && this.mc.getCurrentServerEntry().address != null
                                ? this.mc.getCurrentServerEntry().address : "одиночная игра");
            }
            if (this.withCoords.getValue() && this.mc.player != null) {
                sb.append(this.extra.getValue())
                        .append(MathHelper.floor(this.mc.player.getX())).append(' ')
                        .append(MathHelper.floor(this.mc.player.getY())).append(' ')
                        .append(MathHelper.floor(this.mc.player.getZ()));
            }
            this.mc.keyboard.setClipboard(sb.toString());
            if (this.mc.player != null) {
                this.mc.player.sendMessage(Text.literal("§aИмя скриншота скопировано: §f" + file), false);
            }
        }
        this.lastDown = down;
    }
}
