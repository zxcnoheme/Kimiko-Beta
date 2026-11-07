package rtx.kimiko.api.modules.impl.Interface;

import net.minecraft.block.MapColor;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.world.ClientChunkManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Heightmap;
import net.minecraft.world.chunk.WorldChunk;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.ui.menu.MenuColors;
import rtx.kimiko.api.modules.impl.Visuals.WaypointsModule;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

import java.util.HashMap;
import java.util.Map;

/**
 * Мини-карта: рендер прогруженных чанков (верхний блок + затемнение по высоте),
 * зум, слои (игроки / мобы / вейпоинты), координаты и биом под картой.
 * Текстура 256×256 покрывает 256×256 блоков, окно вида скользит по ней.
 */
public class MinimapModule extends Module {
    private static final int TEX = 256;
    private static final Identifier TEX_ID = Identifier.of("kimiko", "textures/ui/minimap.png");
    private static final int SCAN_RADIUS_CHUNKS = 9;
    private static final int EMPTY_COLOR = 0x5C0A0D14;

    private final ModeSetting shape;
    private final ModeSetting zoom;
    private final ModeSetting corner;
    private final BooleanSetting showPlayers;
    private final BooleanSetting showMobs;
    private final BooleanSetting showWaypoints;
    private final BooleanSetting showInfo;
    private final BooleanSetting nightTint;

    private final Map<Long, int[]> chunkColors = new HashMap<>();
    private int scanCursor;
    private int lastScanCX = Integer.MIN_VALUE;
    private int lastScanCZ = Integer.MIN_VALUE;
    private int tickCounter;

    private NativeImage image;
    private NativeImageBackedTexture texture;
    private boolean textureFailed;
    private long lastBuildMs;
    private float anchorX;
    private float anchorZ;

    public MinimapModule() {
        super("Minimap NEW", "Мини-карта: рельеф прогруженных чанков, игроки, мобы и вейпоинты. Колесо мыши менять масштаб нельзя — выберите приближение в настройках.", Category.DISPLAY);
        this.shape = (ModeSetting) this.register((Setting) new ModeSetting("Форма", "Форма карты.", "Круг", "Круг", "Квадрат"));
        this.zoom = (ModeSetting) this.register((Setting) new ModeSetting("Приближение", "Сколько блоков видно по ширине карты.", "Средне", "Близко", "Средне", "Далеко"));
        this.corner = (ModeSetting) this.register((Setting) new ModeSetting("Позиция", "Угол экрана.", "Правый верх", "Правый верх", "Левый верх"));
        this.showPlayers = (BooleanSetting) this.register((Setting) new BooleanSetting("Игроки", "Белые точки — другие игроки.", true));
        this.showMobs = (BooleanSetting) this.register((Setting) new BooleanSetting("Мобы", "Красные точки — живые существа.", false));
        this.showWaypoints = (BooleanSetting) this.register((Setting) new BooleanSetting("Вейпоинты", "Цветные точки — ваши вейпоинты (модуль Waypoints).", true));
        this.showInfo = (BooleanSetting) this.register((Setting) new BooleanSetting("Координаты и биом", "Подпись под картой.", true));
        this.nightTint = (BooleanSetting) this.register((Setting) new BooleanSetting("Ночной тон", "Затемнять карту ночью.", true));
    }

    private int viewRadiusBlocks() {
        if (this.zoom.is("Близко")) {
            return 96;
        }
        if (this.zoom.is("Далеко")) {
            return 192;
        }
        return 144;
    }

    // ——— скан чанков (по тикам, понемногу)

    @EventHandler
    private void onTick(TickEvent event) {
        if (!event.isPre()) {
            return;
        }
        ClientWorld world = this.mc.world;
        if (world == null || this.mc.player == null) {
            if (!this.chunkColors.isEmpty()) {
                this.chunkColors.clear();
            }
            this.scanCursor = 0;
            this.lastScanCX = Integer.MIN_VALUE;
            return;
        }
        if (++this.tickCounter % 2 != 0) {
            return;
        }
        int pcx = MathHelper.floor(this.mc.player.getX()) >> 4;
        int pcz = MathHelper.floor(this.mc.player.getZ()) >> 4;
        if (pcx != this.lastScanCX || pcz != this.lastScanCZ) {
            this.lastScanCX = pcx;
            this.lastScanCZ = pcz;
            this.scanCursor = 0;
        }
        if (this.chunkColors.size() > 1200) {
            this.chunkColors.clear();
        }
        int grid = SCAN_RADIUS_CHUNKS * 2 + 1;
        int done = 0;
        int attempts = 0;
        while (done < 3 && attempts < 240) {
            attempts++;
            if (this.scanCursor >= grid * grid) {
                this.scanCursor = 0;
            }
            int idx = this.scanCursor++;
            int gx = idx % grid - SCAN_RADIUS_CHUNKS;
            int gz = idx / grid - SCAN_RADIUS_CHUNKS;
            long key = ChunkPos.toLong(pcx + gx, pcz + gz);
            if (this.chunkColors.containsKey(key)) {
                continue;
            }
            this.scanChunk(pcx + gx, pcz + gz);
            if (this.chunkColors.containsKey(key)) {
                done++;
            }
        }
    }

    private void scanChunk(int cx, int cz) {
        ClientWorld world = this.mc.world;
        if (world == null) {
            return;
        }
        ClientChunkManager chunks = (ClientChunkManager) world.getChunkManager();
        WorldChunk chunk = chunks.getWorldChunk(cx, cz);
        if (chunk == null) {
            return;
        }
        Heightmap heightmap = chunk.getHeightmap(Heightmap.Type.WORLD_SURFACE);
        if (heightmap == null) {
            return;
        }
        int[] columns = new int[256];
        int baseX = cx << 4;
        int baseZ = cz << 4;
        int bottomY = world.getBottomY();
        boolean night = this.nightTint.getValue() && world.getAmbientDarkness() >= 3;
        for (int z = 0; z < 16; z++) {
            for (int x = 0; x < 16; x++) {
                int y = heightmap.get(x, z);
                int col = EMPTY_COLOR;
                if (y > bottomY) {
                    BlockPos pos = new BlockPos(baseX + x, y - 1, baseZ + z);
                    MapColor mapColor = chunk.getBlockState(pos).getMapColor(world, pos);
                    float shade = 0.62f + 0.38f * MathHelper.clamp((y - 56.0f) / 48.0f, 0.0f, 1.0f);
                    if (night) {
                        shade *= 0.62f;
                    }
                    col = shadeColor(mapColor.color, shade) | 0xFF000000;
                }
                columns[(z << 4) | x] = col;
            }
        }
        this.chunkColors.put(ChunkPos.toLong(cx, cz), columns);
    }

    private static int shadeColor(int rgb, float factor) {
        int r = MathHelper.clamp((int) ((rgb >> 16 & 0xFF) * factor), 0, 255);
        int g = MathHelper.clamp((int) ((rgb >> 8 & 0xFF) * factor), 0, 255);
        int b = MathHelper.clamp((int) ((rgb & 0xFF) * factor), 0, 255);
        return (r << 16) | (g << 8) | b;
    }

    // ——— текстура и отрисовка

    @EventHandler
    private void onHudRender(HudRenderEvent event) {
        if (this.textureFailed || this.mc.world == null || this.mc.player == null) {
            return;
        }
        long now = System.currentTimeMillis();
        if (this.textureReady() && now - this.lastBuildMs >= 250L) {
            this.anchorX = MathHelper.floor(this.mc.player.getX());
            this.anchorZ = MathHelper.floor(this.mc.player.getZ());
            this.rebuildTexture();
            this.lastBuildMs = now;
        }

        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);

        float size = 150.0f;
        float margin = 16.0f;
        boolean right = this.corner.is("Правый верх");
        float x = right ? (designWidth() - size - margin) : margin;
        float y = margin;
        boolean circle = this.shape.is("Круг");
        float radius = circle ? size * 0.5f : 10.0f;

        // подложка/кольцо
        if (circle) {
            Render2D.circle(x + radius, y + radius, radius + 2.0f, 48, MenuColors.rgba(9, 11, 17, 210));
        } else {
            Render2D.rect(x - 2.0f, y - 2.0f, size + 4.0f, size + 4.0f, radius + 2.0f, MenuColors.rgba(9, 11, 17, 210));
        }

        // карта
        if (this.textureReady()) {
            float halfSpan = this.viewRadiusBlocks() * 0.5f;
            float u0 = ((float) this.mc.player.getX() - this.anchorX) / TEX + 0.5f - halfSpan / TEX;
            float v0 = ((float) this.mc.player.getZ() - this.anchorZ) / TEX + 0.5f - halfSpan / TEX;
            float span = this.viewRadiusBlocks() / (float) TEX;
            Render2D.imageUvNearest(TEX_ID.toString(), x, y, size, size, radius, 0.0f,
                    u0, v0, u0 + span, v0 + span, -1);
        } else {
            Render2D.rect(x, y, size, size, radius, MenuColors.rgba(16, 19, 27, 200));
        }

        float px = (float) this.mc.player.getX();
        float pz = (float) this.mc.player.getZ();
        float vr = this.viewRadiusBlocks();

        // маркеры сущностей
        if (this.showPlayers.getValue()) {
            for (PlayerEntity p : this.mc.world.getPlayers()) {
                if (p == this.mc.player) {
                    continue;
                }
                this.drawMapDot(x, y, size, vr, (float) p.getX(), (float) p.getZ(), 2.2f, 0xFFFFFFFF);
            }
        }
        if (this.showMobs.getValue()) {
            int drawn = 0;
            for (Entity e : this.mc.world.getEntities()) {
                if (drawn >= 80) {
                    break;
                }
                if (!(e instanceof LivingEntity) || e instanceof PlayerEntity || !e.isAlive()) {
                    continue;
                }
                this.drawMapDot(x, y, size, vr, (float) e.getX(), (float) e.getZ(), 1.8f, 0xFFFF6B6B);
                drawn++;
            }
        }
        if (this.showWaypoints.getValue()) {
            WaypointsModule waypoints = WaypointsModule.INSTANCE;
            if (waypoints != null) {
                String dim = waypoints.dimensionKey();
                for (WaypointsModule.Waypoint wp : waypoints.current()) {
                    if (!wp.dimension.equals(dim)) {
                        continue;
                    }
                    this.drawMapDot(x, y, size, vr, (float) wp.x, (float) wp.z, 2.4f, wp.color);
                }
            }
        }

        // игрок: точка + курс-линия по взгляду
        float cx = x + size * 0.5f;
        float cy = y + size * 0.5f;
        float yawRad = (float) Math.toRadians(this.mc.player.getYaw());
        float dirX = -MathHelper.sin(yawRad);
        float dirZ = MathHelper.cos(yawRad);
        Render2D.circle(cx, cy, 3.2f, 14, 0xFFF4F7FF);
        for (int i = 1; i <= 4; i++) {
            float t = i * 4.5f;
            int a = 150 - i * 30;
            Render2D.circle(cx + dirX * t, cy + dirZ * t, 2.4f - i * 0.4f, 8,
                    MenuColors.rgba(244, 247, 255, Math.max(30, a)));
        }

        Render2D.flush();

        // ——— подписи поверх (шрифты рисуются после flush, тем же батчем beginFrame? — отдельный проход)
        Render2D.beginFrame(graphics);
        Fonts.BOLD.draw("С", cx - Fonts.BOLD.width("С", 8.0f) * 0.5f, y - 11.0f, 8.0f,
                MenuColors.rgba(255, 255, 255, 200));
        if (this.showInfo.getValue()) {
            String coords = "X " + MathHelper.floor(px) + "  Y " + MathHelper.floor(this.mc.player.getY())
                    + "  Z " + MathHelper.floor(pz);
            Fonts.SEMIBOLD.draw(coords, x + size - Fonts.SEMIBOLD.width(coords, 7.0f), y + size + 5.0f, 7.0f,
                    MenuColors.rgba(255, 255, 255, 175));
            String biome = biomeName();
            if (!biome.isEmpty()) {
                Fonts.REGULAR.draw(biome, x, y + size + 5.0f, 7.0f,
                        MenuColors.rgba(255, 255, 255, 120));
            }
        }
        Render2D.flush();
    }

    private void drawMapDot(float mapX, float mapY, float size, float viewRadius, float wx, float wz, float dotR, int color) {
        float px = (float) this.mc.player.getX();
        float pz = (float) this.mc.player.getZ();
        float mx = mapX + size * ((wx - px) / viewRadius + 0.5f);
        float my = mapY + size * ((wz - pz) / viewRadius + 0.5f);
        if (mx < mapX - 2.0f || mx > mapX + size + 2.0f || my < mapY - 2.0f || my > mapY + size + 2.0f) {
            return;
        }
        Render2D.circle(mx, my, dotR + 0.9f, 10, 0xE60A0C12);
        Render2D.circle(mx, my, dotR, 10, color);
    }

    private String biomeName() {
        try {
            return this.mc.world.getBiome(this.mc.player.getBlockPos())
                    .getKey()
                    .map(key -> {
                        String path = key.getValue().getPath();
                        if (path.isEmpty()) {
                            return "";
                        }
                        String[] parts = path.replace('_', ' ').split(" ");
                        StringBuilder sb = new StringBuilder();
                        for (String part : parts) {
                            if (!part.isEmpty()) {
                                if (sb.length() > 0) {
                                    sb.append(' ');
                                }
                                sb.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1));
                            }
                        }
                        return sb.toString();
                    })
                    .orElse("");
        } catch (Throwable ignored) {
            return "";
        }
    }

    private float designWidth() {
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        return this.mc.getWindow().getScaledWidth() / sf;
    }

    private float designHeight() {
        float sf = 2.0f / (float) this.mc.getWindow().getScaleFactor();
        return this.mc.getWindow().getScaledHeight() / sf;
    }

    // ——— GPU-текстура

    private boolean textureReady() {
        return this.texture != null;
    }

    private void ensureTexture() {
        if (this.texture != null || this.textureFailed) {
            return;
        }
        try {
            this.image = new NativeImage(TEX, TEX, false);
            for (int py = 0; py < TEX; py++) {
                for (int px = 0; px < TEX; px++) {
                    this.image.setColorArgb(px, py, EMPTY_COLOR | 0xFF000000);
                }
            }
            this.texture = new NativeImageBackedTexture(() -> "Kimiko minimap", this.image);
            this.texture.upload();
            if (this.texture.getGlTextureView() == null && this.texture.getGlTexture() != null) {
                com.mojang.blaze3d.textures.GpuTextureView view =
                        com.mojang.blaze3d.systems.RenderSystem.getDevice().createTextureView(this.texture.getGlTexture());
                ((mixin.skins.AbstractTextureAccessor) (Object) this.texture).kimiko$setView(view);
            }
            this.mc.getTextureManager().registerTexture(TEX_ID, this.texture);
        } catch (Throwable t) {
            this.textureFailed = true;
            System.out.println("[KIMIKO] Minimap: не удалось создать текстуру (" + t + ")");
        }
    }

    private void rebuildTexture() {
        this.ensureTexture();
        if (this.texture == null) {
            return;
        }
        int baseX = (int) this.anchorX - TEX / 2;
        int baseZ = (int) this.anchorZ - TEX / 2;
        for (int py = 0; py < TEX; py++) {
            int wz = baseZ + py;
            int zLocal = (wz & 15) << 4;
            int cz = wz >> 4;
            for (int px = 0; px < TEX; px++) {
                int wx = baseX + px;
                int[] cols = this.chunkColors.get(ChunkPos.toLong(wx >> 4, cz));
                this.image.setColorArgb(px, py, cols != null ? cols[zLocal | (wx & 15)] : EMPTY_COLOR | 0xFF000000);
            }
        }
        this.texture.upload();
    }
}
