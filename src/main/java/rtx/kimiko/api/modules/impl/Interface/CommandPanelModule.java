package rtx.kimiko.api.modules.impl.Interface;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.input.KeyInput;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;
import rtx.kimiko.api.config.ConfigManager;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
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
 * Command Panel NEW: список команд из commands.json в конфиг-каталоге.
 * Клавиша открывает компактную панель; клик по строке отправляет команду.
 * Поддерживаются подстановки %coords% (координаты) и %aim% (yaw/pitch).
 */
public class CommandPanelModule extends Module {
    private final BindSetting bind;
    private final SliderSetting width;
    private final List<String> commands = new ArrayList<>();
    private boolean lastDown;

    public CommandPanelModule() {
        super("Command Panel NEW", "Панель быстрых команд из commands.json. %coords% и %aim% подставляются автоматически.", Category.DISPLAY);
        this.bind = (BindSetting) this.register((Setting) new BindSetting("Клавиша", "Клавиша открытия панели.").setKey(-1));
        this.width = (SliderSetting) this.register((Setting) new SliderSetting("Ширина", "Ширина панели.").setValue(180.0f).range(120.0f, 320.0f));
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
        boolean down = this.bind.getValue().isDown(this.mc.getWindow().getHandle());
        if (down && !this.lastDown) {
            this.load();
            if (this.mc.currentScreen == null && !this.commands.isEmpty()) {
                this.mc.setScreen(new CommandPanelScreen(this));
            }
        }
        this.lastDown = down;
    }

    List<String> commands() {
        return this.commands;
    }

    float panelWidth() {
        return this.width.getValue();
    }

    // ——— подстановки

    String substitute(String raw) {
        String out = raw;
        if (out.contains("%coords%") && this.mc.player != null) {
            out = out.replace("%coords%", MathHelper.floor(this.mc.player.getX()) + " "
                    + MathHelper.floor(this.mc.player.getY()) + " "
                    + MathHelper.floor(this.mc.player.getZ()));
        }
        if (out.contains("%aim%") && this.mc.player != null) {
            out = out.replace("%aim%", Math.round(MathHelper.wrapDegrees(this.mc.player.getYaw())) + " "
                    + Math.round(this.mc.player.getPitch()));
        }
        return out;
    }

    void execute(String raw) {
        String cmd = this.substitute(raw).trim();
        if (cmd.isEmpty()) {
            return;
        }
        try {
            if (this.mc.player != null && this.mc.player.networkHandler != null) {
                if (cmd.startsWith("/")) {
                    this.mc.player.networkHandler.sendChatCommand(cmd.substring(1));
                } else {
                    this.mc.player.networkHandler.sendChatMessage(cmd);
                }
            }
        } catch (Throwable t) {
            System.out.println("[KIMIKO] CommandPanel: не удалось отправить '" + cmd + "': " + t);
        }
    }

    // ——— хранение

    private Path file() {
        return ConfigManager.Companion.systemConfigDirectory().resolve("commands.json");
    }

    private void load() {
        try {
            Path path = this.file();
            this.commands.clear();
            if (!Files.exists(path)) {
                this.commands.add("tell %coords% — мои координаты");
                this.commands.add("spawn");
                return;
            }
            JsonElement root = JsonParser.parseString(Files.readString(path, StandardCharsets.UTF_8));
            if (root.isJsonArray()) {
                for (JsonElement el : root.getAsJsonArray()) {
                    String s = el.getAsString();
                    if (s != null && !s.isBlank()) {
                        this.commands.add(s);
                    }
                }
            }
        } catch (Throwable t) {
            System.out.println("[KIMIKO] CommandPanel: не удалось прочитать commands.json (" + t + ")");
        }
    }

    // ——— экран-панель

    static final class CommandPanelScreen extends Screen {
        private final CommandPanelModule module;
        private float scaleFix = 1.0f;
        private final float[] rowX = new float[64];
        private final float[] rowY = new float[64];
        private final float[] rowW = new float[64];
        private final float[] rowH = new float[64];
        private int rows;
        private float panelX;
        private float panelY;
        private float panelW;
        private float panelH;

        CommandPanelScreen(CommandPanelModule module) {
            super(Text.literal("KimikoCommandPanel"));
            this.module = module;
        }

        @Override
        protected void init() {
            this.rows = 0;
        }

        @Override
        public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        }

        @Override
        public boolean shouldPause() {
            return false;
        }

        @Override
        public void close() {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc != null) {
                mc.setScreen(null);
            }
        }

        @Override
        public void render(DrawContext context, int mouseX, int mouseY, float delta) {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc == null || mc.getWindow() == null) {
                return;
            }
            this.scaleFix = 2.0f / (float) mc.getWindow().getScaleFactor();
            int sw = (int) (mc.getWindow().getScaledWidth() / this.scaleFix);
            int sh = (int) (mc.getWindow().getScaledHeight() / this.scaleFix);

            Render2D.beginFrame(context);
            Render2D.rect(0.0f, 0.0f, sw, sh, 0.0f, MenuColors.rgba(5, 6, 10, 90));

            List<String> cmds = this.module.commands();
            int count = Math.min(cmds.size(), 64);
            float w = this.module.panelWidth();
            float rowH = 22.0f;
            float pad = 8.0f;
            float h = pad * 2.0f + 24.0f + count * (rowH + 4.0f);
            float x = (sw - w) * 0.5f;
            float y = (sh - h) * 0.5f;

            this.panelX = x;
            this.panelY = y;
            this.panelW = w;
            this.panelH = h;

            Render2D.blur(x, y, w, h, 12.0f, 22.0f);
            Render2D.rect(x, y, w, h, 12.0f, MenuColors.rgba(11, 13, 19, 225));
            Render2D.outline(x, y, w, h, 12.0f, 1.0f, MenuColors.rgba(255, 255, 255, 26));

            Fonts.SEMIBOLD.draw("Команды", x + pad, y + pad, 11.0f, MenuColors.rgba(255, 255, 255, 235));
            Fonts.REGULAR.draw("Клик — отправить · Esc — закрыть", x + pad, y + pad + 12.5f, 7.0f, MenuColors.rgba(255, 255, 255, 120));

            this.rows = 0;
            float cy = y + pad + 24.0f;
            float mx = (float) (mouseX / this.scaleFix);
            float my = (float) (mouseY / this.scaleFix);
            for (int i = 0; i < count; i++) {
                String raw = cmds.get(i);
                String shown = this.module.substitute(raw);
                boolean hov = PanelHit.hovered(mx, my, x + pad, cy, w - pad * 2.0f, rowH);
                Render2D.rect(x + pad, cy, w - pad * 2.0f, rowH, 7.0f,
                        hov ? MenuColors.rgba(255, 255, 255, 16) : MenuColors.rgba(255, 255, 255, 7));
                Fonts.SEMIBOLD.draw(shown, x + pad + 8.0f, cy + (rowH - 8.5f) * 0.5f, 8.5f,
                        hov ? MenuColors.rgba(255, 255, 255, 250) : MenuColors.rgba(235, 240, 250, 190));
                if (this.rows < 64) {
                    this.rowX[this.rows] = x + pad;
                    this.rowY[this.rows] = cy;
                    this.rowW[this.rows] = w - pad * 2.0f;
                    this.rowH[this.rows] = rowH;
                    this.rows++;
                }
                cy += rowH + 4.0f;
            }
            Render2D.flush();
        }

        @Override
        public boolean mouseClicked(Click click, boolean doubled) {
            float mx = (float) (click.x() / this.scaleFix);
            float my = (float) (click.y() / this.scaleFix);
            if (click.button() == 0) {
                if (this.panelW > 0.0f && !this.isInPanel(mx, my)) {
                    this.close();
                    return true;
                }
                List<String> cmds = this.module.commands();
                int max = Math.min(this.rows, Math.min(cmds.size(), 64));
                for (int i = 0; i < max; i++) {
                    if (PanelHit.hovered(mx, my, this.rowX[i], this.rowY[i], this.rowW[i], this.rowH[i])) {
                        this.module.execute(cmds.get(i));
                        this.close();
                        return true;
                    }
                }
            }
            return super.mouseClicked(click, doubled);
        }

        @Override
        public boolean keyPressed(KeyInput input) {
            if (input.key() == GLFW.GLFW_KEY_ESCAPE) {
                this.close();
                return true;
            }
            return super.keyPressed(input);
        }

        private boolean isInPanel(float mx, float my) {
            return mx >= this.panelX && mx <= this.panelX + this.panelW
                    && my >= this.panelY && my <= this.panelY + this.panelH;
        }
    }

    /** Простейшая проверка попадания курсора в прямоугольник. */
    private static final class PanelHit {
        static boolean hovered(float mx, float my, float x, float y, float w, float h) {
            return mx >= x && mx <= x + w && my >= y && my <= y + h;
        }
    }
}
