package rtx.kimiko.api.modules.impl.Utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import rtx.kimiko.api.config.ConfigManager;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BindSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Friends List NEW: список друзей в friends.json; клавиша добавляет/убирает ник
 * из буфера обмена; HUD-карточка показывает друзей в радиусе.
 */
public class FriendsModule extends Module {
    private final BindSetting toggleBind;
    private final SliderSetting radius;
    private final SliderSetting maxLines;
    private final List<String> friends = new ArrayList<>();
    private boolean loaded;
    private boolean lastDown;

    public FriendsModule() {
        super("Friends List NEW", "Список друзей (friends.json): клавиша добавляет ник из буфера обмена, карточка показывает тех, кто рядом.", Category.UTILS);
        this.toggleBind = (BindSetting) this.register((Setting) new BindSetting("Клавиша", "Добавить/убрать ник из буфера обмена.").setKey(-1));
        this.radius = (SliderSetting) this.register((Setting) new SliderSetting("Радиус", "Радиус поиска друзей (блоков).").setValue(32.0f).range(8.0f, 128.0f));
        this.maxLines = (SliderSetting) this.register((Setting) new SliderSetting("Строк в карточке", "Максимум ников на карточке.").setValue(4.0f).range(1.0f, 8.0f));
        this.loaded = false;
        this.lastDown = false;
    }

    @Override
    protected void onEnable() {
        this.load();
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || !this.isEnabled()) {
            return;
        }
        boolean down = this.toggleBind.getValue().isDown(this.mc.getWindow().getHandle());
        if (down && !this.lastDown) {
            this.toggleFromClipboard();
        }
        this.lastDown = down;
    }

    private void toggleFromClipboard() {
        String nick;
        try {
            nick = this.mc.keyboard.getClipboard();
        } catch (Throwable t) {
            nick = "";
        }
        if (nick == null) {
            nick = "";
        }
        nick = nick.trim();
        if (nick.isEmpty() || nick.length() > 32 || nick.contains("\n")) {
            this.chat("§7В буфере обмена нет ника.");
            return;
        }
        if (this.friends.contains(nick)) {
            this.friends.remove(nick);
            this.save();
            this.chat("§cУбран из друзей: §f" + nick);
        } else {
            this.friends.add(nick);
            this.save();
            this.chat("§aДобавлен в друзья: §f" + nick);
        }
    }

    private List<String> nearby() {
        List<String> found = new ArrayList<>();
        if (this.mc.world == null || this.mc.player == null || this.friends.isEmpty()) {
            return found;
        }
        double r2 = this.radius.getValue() * this.radius.getValue();
        int scanned = 0;
        for (Entity e : this.mc.world.getEntities()) {
            if (scanned++ >= 200) {
                break;
            }
            if (!(e instanceof PlayerEntity p) || p == this.mc.player) {
                continue;
            }
            if (p.squaredDistanceTo(this.mc.player.getX(), this.mc.player.getY(), this.mc.player.getZ()) > r2) {
                continue;
            }
            String name = p.getName().getString();
            if (this.friends.contains(name) && !found.contains(name)) {
                found.add(name);
            }
        }
        return found;
    }

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (!this.isEnabled() || this.mc.player == null) {
            return;
        }
        List<String> near = this.nearby();
        if (near.isEmpty()) {
            return;
        }

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        float w = this.mc.getWindow().getScaledWidth() / sf;

        int lines = Math.min(near.size(), Math.max(1, this.maxLines.getInt()));
        float cardW = 150.0f;
        float cardH = 20.0f + lines * 12.0f;
        float x = w - cardW - 14.0f;
        float y = 56.0f;

        Render2D.rect(x, y, cardW, cardH, 9.0f, MenuColors.rgba(10, 14, 11, 200));
        Render2D.outline(x, y, cardW, cardH, 9.0f, 1.0f, MenuColors.rgba(90, 220, 120, 60));
        Fonts.SEMIBOLD.draw("Друзья рядом: " + near.size(), x + 9.0f, y + 6.0f, 8.5f, MenuColors.rgba(130, 235, 150, 235));
        for (int i = 0; i < lines; i++) {
            Fonts.REGULAR.draw(near.get(i), x + 9.0f, y + 20.0f + i * 12.0f, 8.0f, MenuColors.rgba(220, 235, 225, 210));
        }
        Render2D.flush();
    }

    private void chat(String s) {
        if (this.mc.player != null) {
            this.mc.player.sendMessage(Text.literal(s), false);
        }
    }

    // ——— хранение

    private Path file() {
        return ConfigManager.Companion.systemConfigDirectory().resolve("friends.json");
    }

    private void load() {
        if (this.loaded) {
            return;
        }
        this.loaded = true;
        try {
            Path path = this.file();
            if (!Files.exists(path)) {
                return;
            }
            JsonObject root = JsonParser.parseString(Files.readString(path, StandardCharsets.UTF_8)).getAsJsonObject();
            this.friends.clear();
            if (root.has("friends")) {
                for (JsonElement el : root.getAsJsonArray("friends")) {
                    String s = el.getAsString();
                    if (s != null && !s.isBlank()) {
                        this.friends.add(s);
                    }
                }
            }
        } catch (Throwable t) {
            System.out.println("[KIMIKO] Friends: не удалось загрузить friends.json (" + t + ")");
        }
    }

    private void save() {
        try {
            JsonObject root = new JsonObject();
            JsonArray arr = new JsonArray();
            for (String s : this.friends) {
                arr.add(s);
            }
            root.add("friends", arr);
            Path path = this.file();
            Files.createDirectories(path.getParent());
            Files.writeString(path, root.toString(), StandardCharsets.UTF_8);
        } catch (Throwable t) {
            System.out.println("[KIMIKO] Friends: не удалось сохранить friends.json (" + t + ")");
        }
    }
}
