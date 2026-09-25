/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.screen.ChatScreen
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.drags;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.config.ConfigManager;
import rtx.kimiko.api.drags.DragController;
import rtx.kimiko.api.drags.Draggable;
import rtx.kimiko.api.drags.HudContextMenu;
import rtx.kimiko.api.drags.HudSettingsPanel;
import rtx.kimiko.api.drags.MitosisController;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.drags.ResizeHandles;
import rtx.kimiko.api.drags.SplitRectComp;
import rtx.kimiko.api.drags.components.ArmorComp;
import rtx.kimiko.api.drags.components.ArrayListComp;
import rtx.kimiko.api.drags.components.BossBarComp;
import rtx.kimiko.api.drags.components.CooldownsComp;
import rtx.kimiko.api.drags.components.HPFocusComp;
import rtx.kimiko.api.drags.components.HotKeysComp;
import rtx.kimiko.api.drags.components.InventoryComp;
import rtx.kimiko.api.drags.components.KeyStrokesComp;
import rtx.kimiko.api.drags.components.MusicBarComp;
import rtx.kimiko.api.drags.components.PotionsComp;
import rtx.kimiko.api.drags.components.ScoreboardComp;
import rtx.kimiko.api.drags.components.TargetHudComp;
import rtx.kimiko.api.drags.components.WatermarkComp;
import rtx.kimiko.api.drags.hud.InfoHud;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.CloseScreenEvent;
import rtx.kimiko.api.events.impl.input.MouseButtonEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.ui.settings.Setting;
import rtx.kimiko.utils.render.modules.post.hitbubbles.HitBubblesRenderer;
import rtx.kimiko.utils.render.modules.post.hudlayer.HudLayerRenderer;
import rtx.kimiko.utils.render.others.LoadingVisualGuard;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0004\u0018\u0000 52\u00020\u0001:\u00015B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u0015\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\u0015\u0010\n\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\n\u0010\tJ\u0013\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\u000e\u0010\tJ\r\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0016\u001a\u00020\u0015\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001a\u001a\u00020\u00042\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001b\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001cH\u0007b\u0002\b\u001e\u00a2\u0006\u0004\b\u001f\u0010 J\u001f\u0010#\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020!H\u0002\u00a2\u0006\u0004\b#\u0010$J\u000f\u0010%\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b%\u0010\u0003J\u001b\u0010'\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020&H\u0007b\u0002\b\u001e\u00a2\u0006\u0004\b'\u0010(J\u001b\u0010*\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020)H\u0007b\u0002\b\u001e\u00a2\u0006\u0004\b*\u0010+R$\u0010.\u001a\u0012\u0012\u0004\u0012\u00020\u00060,j\b\u0012\u0004\u0012\u00020\u0006`-8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b.\u0010/R\u0018\u00100\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0014\u00103\u001a\u0002028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b3\u00104\u00a8\u00066"}, d2={"Lrtx/kimiko/api/drags/DragSystem;", "", "<init>", "()V", "", "init", "Lrtx/kimiko/api/drags/Draggable;", "element", "register", "(Lrtx/kimiko/api/drags/Draggable;)V", "unregister", "", "getAll", "()Ljava/util/List;", "bringToFront", "Lcom/google/gson/JsonObject;", "writeDrags", "()Lcom/google/gson/JsonObject;", "drags", "applyDrags", "(Lcom/google/gson/JsonObject;)V", "", "isDragModeActive", "()Z", "Lnet/minecraft/Framebuffer;", "target", "applyDragDistortion", "(Lnet/minecraft/Framebuffer;)V", "Lrtx/kimiko/api/events/impl/render/HudRenderEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onHud", "(Lrtx/kimiko/api/events/impl/render/HudRenderEvent;)V", "", "throwable", "handleRenderFailure", "(Lrtx/kimiko/api/drags/Draggable;Ljava/lang/Throwable;)V", "releaseInterruptedDrag", "Lrtx/kimiko/api/events/impl/game/CloseScreenEvent;", "onCloseScreen", "(Lrtx/kimiko/api/events/impl/game/CloseScreenEvent;)V", "Lrtx/kimiko/api/events/impl/input/MouseButtonEvent;", "onMouseButton", "(Lrtx/kimiko/api/events/impl/input/MouseButtonEvent;)V", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "elements", "Ljava/util/ArrayList;", "activeDrag", "Lrtx/kimiko/api/drags/Draggable;", "", "distortScratch", "[F", "Companion", "rtx.kimiko:kimiko"})
public final class DragSystem {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ArrayList<Draggable> elements = new ArrayList();
    @Nullable
    private Draggable activeDrag;
    @NotNull
    private final float[] distortScratch = new float[156];
    @NotNull
    private static final DragSystem INSTANCE = new DragSystem();

    private DragSystem() {
    }

    public final void init() {
        this.register(new WatermarkComp());
        this.register(new MusicBarComp());
        this.register(new HotKeysComp());
        this.register(new TargetHudComp());
        this.register(new PotionsComp());
        this.register(new CooldownsComp());
        this.register(new ArrayListComp());
        this.register(new ArmorComp());
        this.register(new InventoryComp());
        this.register(new HPFocusComp());
        this.register(new KeyStrokesComp());
        this.register(new ScoreboardComp());
        this.register(new BossBarComp());
        new InfoHud();
        ConfigManager.Companion.applyActiveDrags();
        EventBus.Companion.get().subscribe(this);
    }

    public final void register(@NotNull Draggable element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        this.elements.add(element);
    }

    public final void unregister(@NotNull Draggable element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        this.elements.remove(element);
        if (this.activeDrag == element) {
            this.activeDrag = null;
        }
    }

    @NotNull
    public final List<Draggable> getAll() {
        List<Draggable> list = Collections.unmodifiableList((List)this.elements);
        Intrinsics.checkNotNullExpressionValue(list, (String)"unmodifiableList(...)");
        return list;
    }

    public final void bringToFront(@NotNull Draggable element) {
        Intrinsics.checkNotNullParameter((Object)element, (String)"element");
        int index = this.elements.indexOf(element);
        if (index < 0 || index == this.elements.size() - 1) {
            return;
        }
        this.elements.remove(index);
        this.elements.add(element);
    }

    @NotNull
    public final JsonObject writeDrags() {
        JsonObject drags = new JsonObject();
        for (Draggable element : this.elements) {
            JsonObject pos = new JsonObject();
            pos.addProperty("x", (Number)Float.valueOf(element.getDrag().getDesiredX()));
            pos.addProperty("y", (Number)Float.valueOf(element.getDrag().getDesiredY()));
            pos.addProperty("scale", (Number)Float.valueOf(element.getScale()));
            element.writeState(pos);
            drags.add(element.getId(), (JsonElement)pos);
        }
        return drags;
    }

    public final void applyDrags(@Nullable JsonObject drags) {
        if (drags == null) {
            return;
        }
        for (Draggable element : this.elements) {
            JsonElement entry = drags.has(element.getId()) ? drags.get(element.getId()) : null;
            if (entry == null || !entry.isJsonObject()) continue;
            JsonObject pos = entry.getAsJsonObject();
            try {
                if (pos.has("x")) {
                    element.getDrag().setTargetX(pos.get("x").getAsFloat());
                }
                if (pos.has("y")) {
                    element.getDrag().setTargetY(pos.get("y").getAsFloat());
                }
                if (pos.has("scale")) {
                    element.setScale(pos.get("scale").getAsFloat());
                }
                Intrinsics.checkNotNull((Object)pos);
                element.readState(pos);
                element.getDrag().syncToTarget();
            }
            catch (Exception exception) {}
        }
    }

    public final boolean isDragModeActive() {
        return MinecraftClient.getInstance().currentScreen instanceof ChatScreen;
    }

    public final void applyDragDistortion(@Nullable Framebuffer target) {
        if (target == null || !this.isDragModeActive() || target.textureWidth <= 0 || target.textureHeight <= 0) {
            return;
        }
        InterfaceModule interfaceModule = InterfaceModule.Companion.getInstance();
        if (interfaceModule != null && (interfaceModule.dragStyle.is("Обычный") || !interfaceModule.dragWaves.getValue())) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.getWindow() == null) {
            return;
        }
        float guiW = Math.max(1.0f, Position.Companion.screenWidth());
        float guiH = Math.max(1.0f, Position.Companion.screenHeight());
        float aspect = (float)target.textureWidth / (float)Math.max(1, target.textureHeight);
        long now = System.currentTimeMillis();
        float phase = DragController.Companion.pulsePhase();
        float fadeIn = Math.max(0.0f, Math.min(1.0f, phase / 0.12f));
        float env = fadeIn * (1.0f - phase);
        float amp = 0.02f * env;
        float[] data = this.distortScratch;
        int count = 0;
        for (Draggable element : this.elements) {
            if (count >= 16) break;
            if (!element.getDrag().isDragging()) continue;
            float w = element.width();
            float h = element.height();
            float cx = element.getDrag().getRenderX() + w * 0.5f;
            float cy = element.getDrag().getRenderY() + h * 0.5f;
            float u = cx / guiW;
            float v = 1.0f - cy / guiH;
            float elemR = Math.max(w, h) * 0.5f / guiH;
            float ringWidth = Math.max(0.04f, Math.min(0.15f, elemR * 0.6f));
            float ringRadius = phase * (elemR + 0.1f);
            int base = 28 + count * 8;
            data[base] = u;
            data[base + 1] = v;
            data[base + 2] = ringRadius;
            data[base + 3] = ringWidth;
            data[base + 4] = amp;
            data[base + 5] = env;
            data[base + 6] = elemR;
            data[base + 7] = 0.0f;
            ++count;
        }
        if (count == 0) {
            return;
        }
        data[0] = count;
        data[1] = aspect;
        data[2] = (float)(now % 100000L) / 1000.0f;
        data[3] = 0.0f;
        data[4] = 1.0f;
        HitBubblesRenderer.apply(target, data);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Unable to fully structure code
     */
    @EventHandler
    public final void onHud(@NotNull HudRenderEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        HudLayerRenderer.beginFrame();
        if (LoadingVisualGuard.shouldSuppressHud(MinecraftClient.getInstance())) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        boolean dragMode = this.isDragModeActive();
        if (!dragMode) {
            this.releaseInterruptedDrag();
        }
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        InterfaceModule interfaceModule = InterfaceModule.Companion.getInstance();
        boolean directDrag = interfaceModule != null && interfaceModule.dragStyle.is("Обычный");
        boolean dragTiltEnabled = directDrag && interfaceModule != null && interfaceModule.dragTilt.getValue();
        ResizeHandles.beginFrame(dragMode);
        if (dragMode) {
            ResizeHandles.tick(mx, my);
        } else {
            ResizeHandles.release();
        }
        MitosisController mitosis = MitosisController.Companion.get();
        mitosis.beginFrame();
        mitosis.stage();
        if (dragMode) {
            for (Draggable element : this.elements) {
                if (!element.isInteractive() || mitosis.isSplitting(element)) continue;
                element.getDrag().tick(mx, my, element.overlayWidth(), element.overlayHeight(), directDrag, element.screenMargin());
            }
        }
        for (Draggable element : this.elements) {
            element.getDrag().updateTilt(mx, dragTiltEnabled);
        }
        for (Draggable element : this.elements) {
            DragController drag = element.getDrag();
            if (drag.isDragging() || mitosis.isSplitting(element)) continue;
            drag.applyScreenClamp(element.scaledWidth(), element.scaledHeight(), element.screenMargin());
        }
        for (Draggable element : this.elements) {
            if (mitosis.isSplitting(element)) continue;
            MitosisController.SplitDraw draw = mitosis.overrideFor(element);
            if (draw != null) {
                RectUtil.armSplitOverride(draw.x, draw.y, draw.width, draw.height, draw.radius, draw.index, draw.childX, draw.childY, draw.childW, draw.childH, draw.childRadius);
                try {
                    element.renderNormal$rtx_kimiko_kimiko(graphics);
                }
                catch (Throwable throwable) {
                    this.handleRenderFailure(element, throwable);
                }
                finally {
                    RectUtil.clearSplitOverride();
                }
                continue;
            }
            try {
                element.renderNormal$rtx_kimiko_kimiko(graphics);
            }
            catch (Throwable throwable) {
                this.handleRenderFailure(element, throwable);
            }
        }
        if (!dragMode) {
            HudContextMenu.close();
        }
        if (dragMode) {
            HudContextMenu.render(graphics);
            Render2D.beginFrame(graphics);
            for (Draggable element : this.elements) {
                if (!element.isInteractive() || mitosis.isSplitting(element)) continue;
                try {
                    element.getDrag().renderOverlay(graphics, element.scaledWidth(), element.scaledHeight(), element.overlayWidth(), element.overlayHeight());
                }
                catch (Throwable throwable) {
                    this.handleRenderFailure(element, throwable);
                }
            }
            Render2D.flush();
        }
        Render2D.beginFrame(graphics);
        for (Draggable element : this.elements) {
            if (!element.isInteractive() || mitosis.isSplitting(element)) continue;
            try {
                ResizeHandles.render(graphics, element, mx, my, 1.0f);
            }
            catch (Throwable throwable) {
                this.handleRenderFailure(element, throwable);
            }
        }
        Render2D.flush();
    }

    private final void handleRenderFailure(Draggable element, Throwable throwable) {
        try {
            RectUtil.clearSplitOverride();
            Render2D.flush();
        }
        catch (Throwable throwable2) {
            // empty catch block
        }
    }

    private final void releaseInterruptedDrag() {
        boolean released = false;
        Draggable current = this.activeDrag;
        if (current != null) {
            current.getDrag().release();
            this.activeDrag = null;
            released = true;
        }
        for (Draggable element : this.elements) {
            if (!element.getDrag().isDragging()) continue;
            element.getDrag().release();
            released = true;
        }
        if (released) {
            ConfigManager.Companion.markDirty();
        }
    }

    @EventHandler
    public final void onCloseScreen(@NotNull CloseScreenEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!(event.getScreen() instanceof ChatScreen)) {
            return;
        }
        Draggable current = this.activeDrag;
        if (current != null) {
            current.getDrag().release();
            this.activeDrag = null;
            ConfigManager.Companion.markDirty();
        }
    }

    @EventHandler
    public final void onMouseButton(@NotNull MouseButtonEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!this.isDragModeActive()) {
            return;
        }
        float mx = Position.Companion.mouseX();
        float my = Position.Companion.mouseY();
        if (event.action == MouseButtonEvent.Action.PRESS && event.button == 1 && this.activeDrag != null) {
            Draggable draggable = this.activeDrag;
            Intrinsics.checkNotNull((Object)draggable);
            draggable.getDrag().cancel();
            this.activeDrag = null;
            ConfigManager.Companion.markDirty();
            event.cancel();
            return;
        }
        if (event.action == MouseButtonEvent.Action.PRESS && event.button == 1) {
            for (int i = this.elements.size() - 1; i >= 0; --i) {
                Draggable element = this.elements.get(i);
                if (!element.isInteractive() || !element.hitTest$rtx_kimiko_kimiko(mx, my)) continue;
                if (HudContextMenu.open(element)) {
                    event.cancel();
                    return;
                }
                if (!MitosisController.Companion.get().split(element)) continue;
                event.cancel();
                return;
            }
            HudContextMenu.close();
            return;
        }
        if (event.button != 0) {
            return;
        }
        if (event.action == MouseButtonEvent.Action.PRESS) {
            if (HudContextMenu.isOpen() && HudContextMenu.click(mx, my)) {
                event.cancel();
                return;
            }
            MitosisController grab = MitosisController.Companion.get();
            for (int i = this.elements.size() - 1; i >= 0; --i) {
                Draggable element = this.elements.get(i);
                if (!element.isInteractive() || grab.isAnimating(element)) continue;
                if (element instanceof SplitRectComp && ((SplitRectComp)element).origin() != null) {
                    Draggable draggable = ((SplitRectComp)element).origin();
                    Intrinsics.checkNotNull((Object)draggable);
                    List<Setting> opts = draggable.hudSettings();
                    if (!((Collection)opts).isEmpty() && HudSettingsPanel.click(mx, my, element.getDrag().getRenderX(), element.getDrag().getRenderY(), ((SplitRectComp)element).width(), opts)) {
                        ConfigManager.Companion.markDirty();
                        event.cancel();
                        return;
                    }
                }
                if (ResizeHandles.grab(element, mx, my)) {
                    this.bringToFront(element);
                    event.cancel();
                    return;
                }
                if (!element.getDrag().tryGrab(mx, my, element.scaledWidth(), element.scaledHeight())) continue;
                this.activeDrag = element;
                this.bringToFront(element);
                event.cancel();
                return;
            }
        } else if (event.action == MouseButtonEvent.Action.RELEASE) {
            if (ResizeHandles.isResizing()) {
                ResizeHandles.release();
                ConfigManager.Companion.markDirty();
                event.cancel();
                return;
            }
            if (this.activeDrag != null) {
                Draggable draggable = this.activeDrag;
                Intrinsics.checkNotNull((Object)draggable);
                draggable.getDrag().release();
                this.activeDrag = null;
                ConfigManager.Companion.markDirty();
                event.cancel();
            }
        }
    }

    @JvmStatic
    @NotNull
    public static final DragSystem get() {
        return Companion.get();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/api/drags/DragSystem.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/drags/DragSystem;", "Lkotlin/jvm/JvmStatic;", "get", "()Lrtx/kimiko/api/drags/DragSystem;", "INSTANCE", "Lrtx/kimiko/api/drags/DragSystem;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final DragSystem get() {
            return INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

