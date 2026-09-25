/*
 * MusicBar HUD component for KimikoSRC
 * Renders system player cover + track title with playback controls.
 */
package rtx.kimiko.api.drags.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.drags.DragSystem;
import rtx.kimiko.api.drags.Draggable;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.input.MouseButtonEvent;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.MusicBarModule;
import rtx.kimiko.api.ui.settings.Setting;
import rtx.kimiko.api.ui.settings.impl.BoolSetting;
import rtx.kimiko.utils.media.MediaPlayer;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0007\u0010\u0006J\u0013\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u0015\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0014\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0011H\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0012J\u001b\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u001aJ\u0011\u0010\u001b\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001c\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/api/drags/components/MusicBarComp;", "Lrtx/kimiko/api/drags/Draggable;", "<init>", "()V", "", "width", "()F", "", "height", "()F", "", "displayName", "()Ljava/lang/String;", "", "Ljava/util/List;", "Lrtx/kimiko/api/ui/settings/Setting;", "buildHudSettings", "()Ljava/util/List;", "", "isInteractive", "()Z", "Lnet/minecraft/client/gui/DrawContext;", "render", "(Lnet/minecraft/client/gui/DrawContext;)V", "", "Lrtx/kimiko/api/events/impl/input/MouseButtonEvent;", "event", "", "onMouseButton", "(Lrtx/kimiko/api/events/impl/input/MouseButtonEvent;)V", "", "shouldShow", "()Z", "Companion", "rtx.kimiko:kimiko"})
public final class MusicBarComp
extends Draggable {
    public static final Companion Companion = new Companion(null);
    private static final float PADDING = 7.0f;
    private static final float ART_SIZE = 13.0f;
    private static final float TITLE_FONT = 7.5f;
    private static final float BUTTON_FONT = 6.0f;
    private static final float RADIUS = 6.6f;
    private static final float ROW_GAP = 5.0f;
    private static final float BTN_W = 10.0f;
    private static final float BTN_H = 8.0f;
    private static final float BTN_GAP = 3.0f;
    private static final float MAX_TEXT_WIDTH = 170.0f;
    private static final float HEIGHT = 40.0f;
    private static final String NOTE_GLYPH = "N";
    private float currentWidth = 150.0f;
    private int hoveredButton = -1;

    public MusicBarComp() {
        super("musicbar", Math.max(10.0f, (Position.Companion.screenWidth() - 230.0f) / 2.0f), 8.0f);
        EventBus.Companion.get().subscribe(this);
    }

    @Override
    public float width() {
        return this.currentWidth;
    }

    @Override
    public float height() {
        return HEIGHT;
    }

    @Override
    @NotNull
    public String displayName() {
        return "MusicBar";
    }

    @Override
    @NotNull
    protected List<Setting> buildHudSettings() {
        ArrayList<Setting> list = new ArrayList<Setting>((Collection)super.buildHudSettings());
        MusicBarModule module = ModuleManager.Companion.get().get(MusicBarModule.class);
        if (module != null) {
            list.add(new BoolSetting(module.showTime));
            list.add(new BoolSetting(module.showArt));
            list.add(new BoolSetting(module.showWave));
        }
        return list;
    }

    @Override
    public boolean isInteractive() {
        return this.shouldShow();
    }

    @Override
    protected void render(@NotNull DrawContext graphics) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        if (!this.shouldShow()) {
            return;
        }
        MusicBarModule module = ModuleManager.Companion.get().get(MusicBarModule.class);
        boolean showArt = module != null && module.showArt.getValue();
        String title = MediaPlayer.getTrack().display();
        if (title.length() == 0) {
            title = MediaPlayer.getTrack().title();
        }
        if (title.length() == 0) {
            return;
        }
        this.updateWidth(title);
        float x = this.getX();
        float y = this.getY();
        float w = this.currentWidth;
        Render2D.beginFrame(graphics);
        RectUtil.drawClientRectFixedRadius(x, y, w, HEIGHT, RADIUS, 1.0f, 0.0f);
        float cx = x + PADDING;
        float rowMid = y + PADDING + ART_SIZE * 0.5f;
        Identifier cover = MediaPlayer.getCover();
        if (showArt && cover != null) {
            Render2D.imageUv(cover.toString(), cx, rowMid - ART_SIZE * 0.5f, ART_SIZE, ART_SIZE, 5.5f, 0.0f, MusicBarComp.Companion.coverUv(this)[0], MusicBarComp.Companion.coverUv(this)[1], MusicBarComp.Companion.coverUv(this)[2], MusicBarComp.Companion.coverUv(this)[3], -1);
            cx += ART_SIZE + 5.0f;
        }
        String trimmed = MusicBarComp.Companion.trimToWidth(this, title);
        float ty = rowMid + 1.0f - TITLE_FONT * 0.5f;
        Fonts.SEMIBOLD.draw(trimmed, cx, ty, TITLE_FONT, -13619152);
        float buttonsY = y + PADDING + ART_SIZE + ROW_GAP;
        float total = 3.0f * BTN_W + 2.0f * BTN_GAP;
        float bx = x + (w - total) * 0.5f;
        this.hoveredButton = -1;
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        String[] glyphs = new String[]{"<", MediaPlayer.isPlaying() ? "||" : "|>", ">"};
        for (int i = 0; i < 3; ++i) {
            float gx = bx + (float)i * (BTN_W + BTN_GAP);
            boolean hovered = mx >= gx && mx <= gx + BTN_W && my >= buttonsY && my <= buttonsY + BTN_H;
            if (hovered) {
                this.hoveredButton = i;
            }
            Render2D.rect(gx, buttonsY, BTN_W, BTN_H, 3.0f, hovered ? -5592406 : -1431655766);
            float gw = Fonts.SEMIBOLD.msdfWidth(glyphs[i], BUTTON_FONT);
            Fonts.SEMIBOLD.draw(glyphs[i], gx + (BTN_W - gw) * 0.5f, buttonsY + (BTN_H - BUTTON_FONT) * 0.5f - 0.5f, BUTTON_FONT, -1);
        }
        Render2D.flush();
    }

    @EventHandler
    public final void onMouseButton(@NotNull MouseButtonEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (event.action != MouseButtonEvent.Action.PRESS || event.button != 0) {
            return;
        }
        if (DragSystem.Companion.get().isDragModeActive()) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        if (minecraftClient2.currentScreen != null) {
            return;
        }
        if (!this.shouldShow()) {
            return;
        }
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        float x = this.getX();
        float y = this.getY();
        float w = this.currentWidth;
        float buttonsY = y + PADDING + ART_SIZE + ROW_GAP;
        float total = 3.0f * BTN_W + 2.0f * BTN_GAP;
        float bx = x + (w - total) * 0.5f;
        for (int i = 0; i < 3; ++i) {
            float gx = bx + (float)i * (BTN_W + BTN_GAP);
            if (mx < gx || mx > gx + BTN_W || my < buttonsY || my > buttonsY + BTN_H) {
                continue;
            }
            if (i == 0) {
                MediaPlayer.previous();
            } else if (i == 1) {
                MediaPlayer.toggle();
            } else {
                MediaPlayer.next();
            }
            event.cancel();
            return;
        }
    }

    private final void updateWidth(String title) {
        float titleW = Math.min(MAX_TEXT_WIDTH, MusicBarComp.Companion.trimToWidth(this, title).length() * TITLE_FONT * 0.6f + 8.0f);
        this.currentWidth = Math.max(120.0f, 2.0f * PADDING + ART_SIZE + 5.0f + titleW);
    }

    private final boolean shouldShow() {
        MusicBarModule module = ModuleManager.Companion.get().get(MusicBarModule.class);
        if (module == null || !module.isEnabled()) {
            return false;
        }
        if (!MediaPlayer.isAvailable() || MediaPlayer.isAdvertisement()) {
            return false;
        }
        return !MediaPlayer.getTrack().isEmpty();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\t\b\u0002\u0010\u0002J\u0013\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u001b\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u0013\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u000b\u0010\r\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/api/drags/components/MusicBarComp$Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/drags/components/MusicBarComp;", "this$0", "", "Lrtx/kimiko/api/drags/components/MusicBarComp;", "trimToWidth", "(Lrtx/kimiko/api/drags/components/MusicBarComp;Ljava/lang/String;)Ljava/lang/String;", "", "Lrtx/kimiko/api/drags/components/MusicBarComp;", "coverUv", "(Lrtx/kimiko/api/drags/components/MusicBarComp;)[F", "", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final String trimToWidth(MusicBarComp comp, String text) {
            if (Fonts.SEMIBOLD.msdfWidth(text, TITLE_FONT) <= MAX_TEXT_WIDTH) {
                return text;
            }
            String result = text;
            while (result.length() > 3 && Fonts.SEMIBOLD.msdfWidth(result + "...", TITLE_FONT) > MAX_TEXT_WIDTH) {
                result = result.substring(0, result.length() - 1);
            }
            return result.length() > 3 ? result + "..." : result;
        }

        private final float[] coverUv(MusicBarComp comp) {
            float u0 = 0.0f;
            float v0 = 0.0f;
            float u1 = 1.0f;
            float v1 = 1.0f;
            int width = MediaPlayer.getCoverWidth();
            int height = MediaPlayer.getCoverHeight();
            if (width > 0 && height > 0) {
                if (width > height) {
                    float crop = (1.0f - (float)height / (float)width) * 0.5f;
                    u0 = crop;
                    u1 = 1.0f - crop;
                } else if (height > width) {
                    float crop = (1.0f - (float)width / (float)height) * 0.5f;
                    v0 = crop;
                    v1 = 1.0f - crop;
                }
            }
            return new float[]{u0, v0, u1, v1};
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}