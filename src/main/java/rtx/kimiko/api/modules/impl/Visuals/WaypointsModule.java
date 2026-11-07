package rtx.kimiko.api.modules.impl.Visuals;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import rtx.kimiko.api.config.ConfigManager;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.impl.BindSetting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Waypoints: 3D-маркеры сквозь стены с именем и живой дистанцией.
 * Клавиша — поставить точку под собой, вторая — удалить ближайшую;
 * опционально авто-точка на месте смерти. Хранение — per-сервер JSON.
 */
public class WaypointsModule extends Module {
    /** Ссылка для интеграций (мини-карта и т.п.). */
    public static WaypointsModule INSTANCE;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final int[] PALETTE = {
            0xFFFF5252, 0xFF4CFF6E, 0xFF4CA8FF, 0xFFFFD24C,
            0xFFB44CFF, 0xFFFF8A4C, 0xFF4CFFE1, 0xFFFF4CA8
    };

    private final BooleanSetting autoDeath;
    private final BooleanSetting showName;
    private final BooleanSetting showDist;
    private final SliderSetting maxDist;
    private final BindSetting addBind;
    private final BindSetting removeBind;

    private final Map<String, List<Waypoint>> storage = new LinkedHashMap<>();
    private int paletteCursor;
    private boolean lastAddDown;
    private boolean lastRemoveDown;
    private boolean deathMarked;

    /** Маркеры, спроецированные из мира (заполняется в WorldRender, рисуется в HUD). */
    private static final List<Marker> projected = new ArrayList<>();

    public WaypointsModule() {
        super("Waypoints NEW", "3D-метки на карте мира: видны сквозь стены, с именем и дистанцией. Клавиша ставит точку под вами.", Category.VISUALS);
        this.autoDeath = (BooleanSetting) this.register((Setting) new BooleanSetting("Точка на смерти", "Автоматически ставить вейпоинт там, где вы умерли.", true));
        this.showName = (BooleanSetting) this.register((Setting) new BooleanSetting("Название", "Показывать название точки.", true));
        this.showDist = (BooleanSetting) this.register((Setting) new BooleanSetting("Дистанция", "Показывать дистанцию в блоках.", true));
        this.maxDist = (SliderSetting) this.register((Setting) new SliderSetting("Радиус видимости", "Точки дальше этого расстояния не рисуются.").setValue(512.0f).range(64.0f, 2048.0f));
        this.addBind = (BindSetting) this.register((Setting) new BindSetting("Поставить точку", "Клавиша: создать вейпоинт в текущей позиции.").setKey(-1));
        this.removeBind = (BindSetting) this.register((Setting) new BindSetting("Удалить ближайшую", "Клавиша: удалить ближайший вейпоинт (в пределах 32 блоков).").setKey(-1));
        this.load();
        INSTANCE = this;
    }

    // ——— данные

    public static final class Waypoint {
        public String name = "Точка";
        public double x;
        public double y;
        public double z;
        public int color = 0xFF4CFF6E;
        public String dimension = "minecraft:overworld";
    }

    private static final class Marker {
        float x;
        float y;
        float dist;
        int color;
        String name;
    }

    private String serverKey() {
        ServerInfo entry = this.mc.getCurrentServerEntry();
        return entry != null && entry.address != null ? entry.address : "singleplayer";
    }

    public String dimensionKey() {
        return this.mc.world != null ? this.mc.world.getRegistryKey().getValue().toString() : "minecraft:overworld";
    }

    public List<Waypoint> current() {
        return this.storage.computeIfAbsent(this.serverKey(), k -> new ArrayList<>());
    }

    private void addWaypoint(String name, double x, double y, double z) {
        Waypoint wp = new Waypoint();
        wp.name = name;
        wp.x = x;
        wp.y = y;
        wp.z = z;
        wp.color = PALETTE[this.paletteCursor++ % PALETTE.length] | 0xFF000000;
        wp.dimension = this.dimensionKey();
        this.current().add(wp);
        this.save();
    }

    private void removeNearest() {
        if (this.mc.player == null) {
            return;
        }
        List<Waypoint> list = this.current();
        double best = 32.0 * 32.0;
        Waypoint target = null;
        for (Waypoint wp : list) {
            if (!wp.dimension.equals(this.dimensionKey())) {
                continue;
            }
            double d = (wp.x - this.mc.player.getX()) * (wp.x - this.mc.player.getX())
                    + (wp.y - this.mc.player.getY()) * (wp.y - this.mc.player.getY())
                    + (wp.z - this.mc.player.getZ()) * (wp.z - this.mc.player.getZ());
            if (d < best) {
                best = d;
                target = wp;
            }
        }
        if (target != null) {
            list.remove(target);
            this.save();
        }
    }

    // ——— ввод и тики

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre() || this.mc.world == null || this.mc.player == null) {
            return;
        }
        long window = this.mc.getWindow().getHandle();
        boolean addDown = this.addBind.getValue().isDown(window);
        if (addDown && !this.lastAddDown) {
            this.addWaypoint("Точка " + (this.current().size() + 1),
                    MathHelper.floor(this.mc.player.getX()) + 0.5,
                    this.mc.player.getY(),
                    MathHelper.floor(this.mc.player.getZ()) + 0.5);
        }
        this.lastAddDown = addDown;

        boolean removeDown = this.removeBind.getValue().isDown(window);
        if (removeDown && !this.lastRemoveDown) {
            this.removeNearest();
        }
        this.lastRemoveDown = removeDown;

        if (this.autoDeath.getValue()) {
            if (this.mc.player.isDead() && !this.deathMarked) {
                this.deathMarked = true;
                this.addWaypoint("Смерть",
                        MathHelper.floor(this.mc.player.getX()) + 0.5,
                        this.mc.player.getY(),
                        MathHelper.floor(this.mc.player.getZ()) + 0.5);
            } else if (!this.mc.player.isDead()) {
                this.deathMarked = false;
            }
        }
    }

    // ——— проекция из мира

    private final Matrix4f viewProj = new Matrix4f();
    private final Vector4f clip = new Vector4f();

    @EventHandler
    private void onWorldRender(WorldRenderEvent event) {
        projected.clear();
        if (!this.isEnabled() || this.mc.world == null || this.mc.player == null || event.isPortalPass()) {
            return;
        }
        float designW = this.designWidth();
        float designH = this.designHeight();
        Vec3d cam = event.getCamera().getCameraPos();
        this.viewProj.set(event.getProjectionMatrix()).mul(event.getPositionMatrix());
        double maxDist = this.maxDist.getValue();
        float limit = this.mc.getWindow().getScaledWidth() * 4.0f;

        for (Waypoint wp : this.current()) {
            if (!wp.dimension.equals(this.dimensionKey())) {
                continue;
            }
            double dx = wp.x - cam.x;
            double dy = wp.y - cam.y;
            double dz = wp.z - cam.z;
            double distSq = dx * dx + dy * dy + dz * dz;
            if (distSq > maxDist * maxDist) {
                continue;
            }
            this.clip.set((float) dx, (float) (dy + 2.2), (float) dz, 1.0f).mul(this.viewProj);
            float w = this.clip.w();
            if (w < 0.1f) {
                continue;
            }
            float ndcX = this.clip.x() / w;
            float ndcY = this.clip.y() / w;
            if (ndcX < -1.2f || ndcX > 1.2f || ndcY < -1.2f || ndcY > 1.2f) {
                continue;
            }
            Marker m = new Marker();
            m.x = (ndcX * 0.5f + 0.5f) * designW;
            m.y = (1.0f - (ndcY * 0.5f + 0.5f)) * designH;
            m.dist = MathHelper.sqrt((float) distSq);
            m.color = wp.color;
            m.name = wp.name;
            projected.add(m);
            if (projected.size() >= 64) {
                break;
            }
        }
    }

    // ——— отрисовка

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (projected.isEmpty() || this.mc.world == null || this.mc.player == null) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        float alpha = 1.0f;
        for (Marker m : projected) {
            float s = MathHelper.clamp(160.0f / (m.dist * 0.10f + 10.0f), 1.6f, 5.5f);
            // подложка-контур и точка
            Render2D.circle(m.x, m.y, s + 1.6f, 18, MenuColors.rgba(10, 12, 18, (int) (190 * alpha)));
            Render2D.circle(m.x, m.y, s, 18, m.color);
            // линия-ножка вниз
            Render2D.rect(m.x - 0.7f, m.y, 1.4f, 5.0f, 0.7f, m.color);
            if (this.showName.getValue()) {
                float tw = Fonts.SEMIBOLD.width(m.name, 7.5f);
                Fonts.SEMIBOLD.draw(m.name, m.x - tw * 0.5f, m.y - s - 10.5f, 7.5f,
                        MenuColors.rgba(255, 255, 255, (int) (245 * alpha)));
            }
            if (this.showDist.getValue()) {
                String distText = Math.round(m.dist) + " м";
                float dw = Fonts.REGULAR.width(distText, 6.5f);
                Fonts.REGULAR.draw(distText, m.x - dw * 0.5f, m.y + s + 3.0f, 6.5f,
                        MenuColors.rgba(255, 255, 255, (int) (160 * alpha)));
            }
        }
        Render2D.flush();
    }

    private float designWidth() {
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        return this.mc.getWindow().getScaledWidth() / sf;
    }

    private float designHeight() {
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        return this.mc.getWindow().getScaledHeight() / sf;
    }

    // ——— хранение

    private Path file() {
        return ConfigManager.Companion.systemConfigDirectory().resolve("waypoints.json");
    }

    private void load() {
        try {
            Path path = this.file();
            if (!Files.exists(path)) {
                return;
            }
            JsonObject root = JsonParser.parseString(Files.readString(path, StandardCharsets.UTF_8)).getAsJsonObject();
            if (!root.has("servers")) {
                return;
            }
            for (Map.Entry<String, JsonElement> e : root.getAsJsonObject("servers").entrySet()) {
                List<Waypoint> list = new ArrayList<>();
                for (JsonElement el : e.getValue().getAsJsonArray()) {
                    try {
                        list.add(GSON.fromJson(el, Waypoint.class));
                    } catch (Throwable ignored) {
                    }
                }
                this.storage.put(e.getKey(), list);
            }
        } catch (Throwable t) {
            System.out.println("[KIMIKO] Waypoints: не удалось загрузить точки (" + t + ")");
        }
    }

    private void save() {
        try {
            JsonObject root = new JsonObject();
            JsonObject servers = new JsonObject();
            for (Map.Entry<String, List<Waypoint>> e : this.storage.entrySet()) {
                JsonArray arr = new JsonArray();
                for (Waypoint wp : e.getValue()) {
                    arr.add(GSON.toJsonTree(wp));
                }
                servers.add(e.getKey(), arr);
            }
            root.add("servers", servers);
            Path path = this.file();
            Files.createDirectories(path.getParent());
            Files.writeString(path, GSON.toJson(root), StandardCharsets.UTF_8);
        } catch (Throwable t) {
            System.out.println("[KIMIKO] Waypoints: не удалось сохранить точки (" + t + ")");
        }
    }
}
