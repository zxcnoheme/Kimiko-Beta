/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.util.hit.EntityHitResult
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ButtonSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.modules.settings.impl.TextSetting;
import rtx.kimiko.api.ui.UI;
import rtx.kimiko.api.ui.crosshair.CrosshairEditorScreen;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.outline.outline360.Outline360Range;
import rtx.kimiko.utils.render.render2d.rectangle.rectdefault.BuiltRectangle;

@Feature(value={"crosshair"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0018\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 V2\u00020\u0001:\u0001VB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u001b\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0003b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ'\u0010\u000f\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J5\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0017\u0010\u0018J/\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\r\u0010\u001f\u001a\u00020\u0011\u00a2\u0006\u0004\b\u001f\u0010 J\u0015\u0010!\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0011\u00a2\u0006\u0004\b!\u0010\"J\u000f\u0010#\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b#\u0010\u0003J/\u0010%\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b%\u0010&JO\u0010/\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u000b2\u0006\u0010,\u001a\u00020\u000b2\u0006\u0010-\u001a\u00020\u000b2\u0006\u0010.\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\b/\u00100J?\u00103\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u000b2\u0006\u00102\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b3\u00104R\u0014\u00106\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b6\u00107R\u0014\u00109\u001a\u0002088\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b9\u0010:R\u0014\u0010;\u001a\u0002088\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b;\u0010:R\u0014\u0010<\u001a\u0002088\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u0010:R\u0014\u0010=\u001a\u0002088\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u0010:R\u0014\u0010?\u001a\u00020>8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u0010@R\u0014\u0010A\u001a\u00020>8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bA\u0010@R\u0014\u0010C\u001a\u00020B8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bC\u0010DR\u0014\u0010E\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u00107R\u0014\u0010F\u001a\u0002088\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bF\u0010:R\u0014\u0010G\u001a\u00020>8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010@R\u0014\u0010H\u001a\u00020>8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u0010@R\u0014\u0010J\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010KR\u0016\u0010L\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bL\u0010MR\u0016\u0010N\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bN\u0010MR\u0016\u0010O\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bO\u0010MR\u0016\u0010Q\u001a\u00020P8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bQ\u0010RR\u0016\u0010T\u001a\u00020S8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bT\u0010U\u00ca\u0001\u0010\bW\u0012\f\bX\u0012\b\b\fJ\u0004\b\b(Y\u00a8\u0006Z"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/Crosshair;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "onEnable", "Lrtx/kimiko/api/events/impl/render/HudRenderEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onHudRender", "(Lrtx/kimiko/api/events/impl/render/HudRenderEvent;)V", "", "sw", "sh", "dt", "renderCustom", "(FFF)V", "", "grid", "cx", "cy", "px", "redFactor", "renderGridStyled", "([ZFFFF)V", "", "col", "sampleX", "sampleY", "customPixelColor", "(IFFF)I", "customGrid", "()[Z", "setCustomGrid", "([Z)V", "openEditor", "partial", "renderCross", "(FFFF)V", "x", "y", "size", "thick", "padding", "ind", "offset", "color", "renderMain", "(FFFFFFFI)V", "startX", "startY", "renderCircle", "(FFFFFF)V", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "mode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "attackOffset", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "indent", "size1", "size2", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "drawDot", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "onKrytka", "Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;", "customEditor", "Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;", "customColorMode", "customScale", "customOutline", "customTargetReact", "Lrtx/kimiko/api/modules/settings/impl/TextSetting;", "customPixels", "Lrtx/kimiko/api/modules/settings/impl/TextSetting;", "red", "F", "prevYaw", "prevPitch", "", "prevInit", "Z", "", "lastNs", "J", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "crosshair", "rtx.kimiko:kimiko"})
public final class Crosshair
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ModeSetting mode;
    @NotNull
    private final SliderSetting attackOffset;
    @NotNull
    private final SliderSetting indent;
    @NotNull
    private final SliderSetting size1;
    @NotNull
    private final SliderSetting size2;
    @NotNull
    private final BooleanSetting drawDot;
    @NotNull
    private final BooleanSetting onKrytka;
    @NotNull
    private final ButtonSetting customEditor;
    @NotNull
    private final ModeSetting customColorMode;
    @NotNull
    private final SliderSetting customScale;
    @NotNull
    private final BooleanSetting customOutline;
    @NotNull
    private final BooleanSetting customTargetReact;
    @NotNull
    private final TextSetting customPixels;
    private float red;
    private float prevYaw;
    private float prevPitch;
    private boolean prevInit;
    private long lastNs;
    private static final int BLACK = -16777216;
    public static final int GRID = 15;

    public Crosshair() {
        super("Crosshair", "Изменяет внешний вид прицела.", Category.VISUALS);
        String[] stringArray = new String[]{"Крестик", "Кружок", "Свой"};
        this.mode = (ModeSetting)this.register((Setting)new ModeSetting("Внешний вид", "Тип прицела.", "Крестик", stringArray));
        this.attackOffset = (SliderSetting)this.register((Setting)new SliderSetting("Отступ атаки", "Разброс лучей по кулдауну предмета.").range(0.0f, 20.0f).setValue(10.0f).visible(() -> Crosshair.attackOffset$lambda$0(this)));
        this.indent = (SliderSetting)this.register((Setting)new SliderSetting("Отступ", "Отступ лучей от центра экрана.").range(0.0f, 5.0f).setValue(0.0f).visible(() -> Crosshair.indent$lambda$0(this)));
        this.size1 = (SliderSetting)this.register((Setting)new SliderSetting("Длина", "Длина линий прицела.").range(2.0f, 10.0f).increment(0.5f).setValue(4.0f).visible(() -> Crosshair.size1$lambda$0(this)));
        this.size2 = (SliderSetting)this.register((Setting)new SliderSetting("Толщина", "Толщина линий прицела.").range(1.0f, 4.0f).increment(0.5f).setValue(1.0f).visible(() -> Crosshair.size2$lambda$0(this)));
        this.drawDot = (BooleanSetting)this.register((Setting)new BooleanSetting("Рисовать точку", "Центральная точка под перекрестием.", false).visible(() -> Crosshair.drawDot$lambda$0(this)));
        this.onKrytka = (BooleanSetting)this.register((Setting)new BooleanSetting("Изменять позицию при движении мыши", "Инерция прицела при повороте камеры.", true).visible(() -> Crosshair.onKrytka$lambda$0(this)));
        this.customEditor = (ButtonSetting)this.register((Setting)new ButtonSetting("Редактор", "Нарисовать свой прицел по пикселям.").label("Открыть").onClick(() -> Crosshair.customEditor$lambda$0(this)).visible(() -> Crosshair.customEditor$lambda$1(this)));
        stringArray = new String[]{"Белый", "Тема", "Радуга"};
        this.customColorMode = (ModeSetting)this.register((Setting)new ModeSetting("Цвет", "Цвет пиксельного прицела.", "Белый", stringArray).visibleWhen(() -> Crosshair.customColorMode$lambda$0(this)));
        this.customScale = (SliderSetting)this.register((Setting)new SliderSetting("Масштаб", "Размер одного пикселя прицела.").range(0.5f, 3.0f).increment(0.5f).setValue(1.0f).visible(() -> Crosshair.customScale$lambda$0(this)));
        this.customOutline = (BooleanSetting)this.register((Setting)new BooleanSetting("Обводка", "Тёмная обводка вокруг пикселей.", true).visible(() -> Crosshair.customOutline$lambda$0(this)));
        this.customTargetReact = (BooleanSetting)this.register((Setting)new BooleanSetting("Реакция на цель", "Прицел краснеет при наведении на сущность.", true).visible(() -> Crosshair.customTargetReact$lambda$0(this)));
        this.customPixels = (TextSetting)this.register((Setting)new TextSetting("Пиксели", "Данные пиксельного прицела.").setText(Companion.defaultGrid()).visible(Crosshair::customPixels$lambda$0));
        this.red = 1.0f;
        this.lastNs = System.nanoTime();
    }

    @Override
    protected void onEnable() {
        this.prevInit = false;
    }

    @EventHandler
    private final void onHudRender(HudRenderEvent event) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        if (!this.isEnabled() || !this.mc.options.getPerspective().isFirstPerson()) {
            return;
        }
        DrawContext graphics = event.getGraphics();
        float partial = event.getPartialTick();
        long now = System.nanoTime();
        float dt = Math.min(0.1f, (float)(now - this.lastNs) / 1.0E9f);
        this.lastNs = now;
        float sw = Position.Companion.screenWidth();
        float sh = Position.Companion.screenHeight();
        Render2D.beginFrame(graphics);
        if (this.mode.is("Крестик")) {
            this.renderCross(sw, sh, partial, dt);
        } else if (this.mode.is("Свой")) {
            this.renderCustom(sw, sh, dt);
        } else {
            this.renderCircle(sw * 0.5f, sh * 0.5f, sw, sh, partial, dt);
        }
        Render2D.flush();
    }

    private final void renderCustom(float sw, float sh, float dt) {
        float target = this.customTargetReact.getValue() && this.mc.crosshairTarget instanceof EntityHitResult ? 5.0f : 1.0f;
        this.red += (target - this.red) * Math.min(1.0f, dt * 10.0f);
        this.renderGridStyled(this.customGrid(), sw * 0.5f, sh * 0.5f, this.customScale.getValue(), this.red);
    }

    public final void renderGridStyled(@NotNull boolean[] grid, float cx, float cy, float px, float redFactor) {
        Intrinsics.checkNotNullParameter((Object)grid, (String)"grid");
        float half = (float)15 * px * 0.5f;
        float startX = Math.round(cx - half);
        float startY = Math.round(cy - half);
        if (this.customOutline.getValue()) {
            float o = Math.min(0.5f, px * 0.5f);
            int n = grid.length;
            for (int i = 0; i < n; ++i) {
                if (!grid[i]) continue;
                int r = i / 15;
                int c = i % 15;
                Crosshair.Companion.sharpRect(startX + (float)c * px - o, startY + (float)r * px - o, px + o * 2.0f, px + o * 2.0f, -939524096);
            }
        }
        int n = grid.length;
        for (int i = 0; i < n; ++i) {
            if (!grid[i]) continue;
            int r = i / 15;
            int c = i % 15;
            Crosshair.Companion.sharpRect(startX + (float)c * px, startY + (float)r * px, px, px, this.customPixelColor(c, redFactor, startX + ((float)c + 0.5f) * px, startY + ((float)r + 0.5f) * px));
        }
    }

    private final int customPixelColor(int col, float redFactor, float sampleX, float sampleY) {
        int base = 0;
        if (this.customColorMode.is("Тема")) {
            base = ClientAccent.gradientColorAt(Crosshair.Companion.pingpong((float)col / 14.0f), 255.0f, sampleX, sampleY);
        } else if (this.customColorMode.is("Радуга")) {
            float hue = (float)(System.currentTimeMillis() % 4000L) / 4000.0f + (float)col * 0.03f;
            base = Color.HSBtoRGB(hue - (float)Math.floor(hue), 0.65f, 1.0f);
        } else {
            base = -1;
        }
        float t = Crosshair.Companion.clamp((redFactor - 1.0f) / 4.0f, 0.0f, 1.0f);
        return t <= 0.001f ? base : ClientAccent.mix(base |= 0xFF000000, -719606, t);
    }

    @NotNull
    public final boolean[] customGrid() {
        String s = this.customPixels.getText();
        boolean[] grid = new boolean[225];
        if (s == null || s.length() != grid.length) {
            s = Companion.defaultGrid();
        }
        int n = grid.length;
        for (int i = 0; i < n; ++i) {
            grid[i] = s.charAt(i) == '1';
        }
        return grid;
    }

    public final void setCustomGrid(@NotNull boolean[] grid) {
        Intrinsics.checkNotNullParameter((Object)grid, (String)"grid");
        StringBuilder sb = new StringBuilder(225);
        for (int i = 0; i < 225; ++i) {
            sb.append((char)(i < grid.length && grid[i] ? 49 : 48));
        }
        this.customPixels.setText(sb.toString());
    }

    private final void openEditor() {
        CrosshairEditorScreen editor = new CrosshairEditorScreen(this, UI.INSTANCE);
        if (Intrinsics.areEqual((Object)this.mc.currentScreen, (Object)UI.INSTANCE)) {
            UI.Companion.closeInto(editor);
        } else {
            this.mc.setScreen((Screen)editor);
        }
    }

    private final void renderCross(float sw, float sh, float partial, float dt) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity localPlayer = clientPlayerEntity2;
        float target = this.mc.crosshairTarget instanceof EntityHitResult ? 5.0f : 1.0f;
        float factor = Math.min(1.0f, dt * 10.0f);
        this.red += (target - this.red) * factor;
        int firstColor = Crosshair.Companion.multRedWhite(this.red);
        int secondColor = -16777216;
        float x = sw / 2.0f;
        float y = sh / 2.0f;
        float cooldown = (float)this.attackOffset.getInt() - (float)this.attackOffset.getInt() * localPlayer.getAttackCooldownProgress(partial);
        float size = this.size1.getValue();
        float thick = this.size2.getValue();
        float offset = thick / 2.0f;
        float ind = (float)this.indent.getInt() + cooldown;
        if (this.drawDot.getValue()) {
            Crosshair.Companion.sharpRect(x - 1.0f, y - 1.0f, 2.0f, 2.0f, secondColor);
            Crosshair.Companion.sharpRect(x - 0.5f, y - 0.5f, 1.0f, 1.0f, firstColor);
        }
        this.renderMain(x, y, size, thick, 1.0f, ind, offset, secondColor);
        this.renderMain(x, y, size, thick, 0.0f, ind, offset, firstColor);
    }

    private final void renderMain(float x, float y, float size, float thick, float padding, float ind, float offset, int color) {
        Crosshair.Companion.sharpRect(x - offset - padding / 2.0f, y - size - ind - padding / 2.0f, thick + padding, size + padding, color);
        Crosshair.Companion.sharpRect(x - offset - padding / 2.0f, y + ind - padding / 2.0f, thick + padding, size + padding, color);
        Crosshair.Companion.sharpRect(x - size - ind - padding / 2.0f, y - offset - padding / 2.0f, size + padding, thick + padding, color);
        Crosshair.Companion.sharpRect(x + ind - padding / 2.0f, y - offset - padding / 2.0f, size + padding, thick + padding, color);
    }

    private final void renderCircle(float startX, float startY, float sw, float sh, float partial, float dt) {
        float swingPC;
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity localPlayer = clientPlayerEntity2;
        float posX = startX;
        float posY = startY;
        if (this.onKrytka.getValue()) {
            float yaw = localPlayer.getYaw();
            float pitch = localPlayer.getPitch();
            if (!this.prevInit) {
                this.prevYaw = yaw;
                this.prevPitch = pitch;
                this.prevInit = true;
            }
            float dX = (yaw - this.prevYaw) * 0.5f;
            float dY = (pitch - this.prevPitch) * 0.5f;
            if (Math.abs(dX) <= Float.MAX_VALUE) {
                posX += dX;
            }
            if (Math.abs(dY) <= Float.MAX_VALUE) {
                posY += dY;
            }
            float margin = 50.0f;
            posX = Crosshair.Companion.clamp(posX, margin, sw - margin);
            posY = Crosshair.Companion.clamp(posY, margin, sh - margin);
            float iSpeed = 1.0f - (float)Math.exp(-dt * 3.6f);
            this.prevYaw += (yaw - this.prevYaw) * iSpeed;
            this.prevPitch += (pitch - this.prevPitch) * iSpeed;
        } else {
            this.prevInit = false;
        }
        if (!(Math.abs(posX) <= Float.MAX_VALUE)) {
            posX = sw * 0.5f;
        }
        if (!(Math.abs(posY) <= Float.MAX_VALUE)) {
            posY = sh * 0.5f;
        }
        if (!(Math.abs(swingPC = (swingPC = localPlayer.getHandSwingProgress(partial)) > 0.5f ? 1.0f - swingPC : swingPC) <= Float.MAX_VALUE)) {
            swingPC = 0.0f;
        }
        float rad = 5.0f;
        float thick = 1.6f;
        Render2D.outline360(posX - rad, posY - rad, rad * 2.0f, rad * 2.0f, rad, thick, -938997752, new Outline360Range[0]);
        float end = 360.0f * Math.max(0.0f, 1.0f - swingPC * 2.0f);
        Crosshair.Companion.drawGradientRing(posX, posY, rad, thick, end, 255.0f);
    }

    private static final Boolean attackOffset$lambda$0(Crosshair this$0) {
        return this$0.mode.is("Крестик");
    }

    private static final Boolean indent$lambda$0(Crosshair this$0) {
        return this$0.mode.is("Крестик");
    }

    private static final Boolean size1$lambda$0(Crosshair this$0) {
        return this$0.mode.is("Крестик");
    }

    private static final Boolean size2$lambda$0(Crosshair this$0) {
        return this$0.mode.is("Крестик");
    }

    private static final Boolean drawDot$lambda$0(Crosshair this$0) {
        return this$0.mode.is("Крестик");
    }

    private static final Boolean onKrytka$lambda$0(Crosshair this$0) {
        return this$0.mode.is("Кружок");
    }

    private static final void customEditor$lambda$0(Crosshair this$0) {
        this$0.openEditor();
    }

    private static final Boolean customEditor$lambda$1(Crosshair this$0) {
        return this$0.mode.is("Свой");
    }

    private static final Boolean customColorMode$lambda$0(Crosshair this$0) {
        return this$0.mode.is("Свой");
    }

    private static final Boolean customScale$lambda$0(Crosshair this$0) {
        return this$0.mode.is("Свой");
    }

    private static final Boolean customOutline$lambda$0(Crosshair this$0) {
        return this$0.mode.is("Свой");
    }

    private static final Boolean customTargetReact$lambda$0(Crosshair this$0) {
        return this$0.mode.is("Свой");
    }

    private static final Boolean customPixels$lambda$0() {
        return false;
    }

    @JvmStatic
    @NotNull
    public static final String defaultGrid() {
        return Companion.defaultGrid();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0019\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J7\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J?\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ'\u0010#\u001a\u00020\b2\u0006\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020\r8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b'\u0010&\u00a8\u0006("}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/Crosshair.Companion;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "defaultGrid", "()Ljava/lang/String;", "", "x", "y", "w", "h", "", "color", "", "sharpRect", "(FFFFI)V", "red", "multRedWhite", "(F)I", "cx", "cy", "rad", "thickness", "endDeg", "alpha255", "drawGradientRing", "(FFFFFF)V", "t", "pingpong", "(F)F", "v", "lo", "hi", "clamp", "(FFF)F", "BLACK", "I", "GRID", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        /*
         * Unable to fully structure code
         */
        @JvmStatic
        @NotNull
        public final String defaultGrid() {
            StringBuilder sb = new StringBuilder(225);
            int c = 7;
            for (int i = 0; i < 225; ++i) {
                int row = i / 15;
                int col = i % 15;
                boolean vertical = (col == c) && ((row >= c - 5 && row <= c - 2) || (row >= c + 2 && row <= c + 5));
                boolean horizontal = (row == c) && ((col >= c - 5 && col <= c - 2) || (col >= c + 2 && col <= c + 5));
                boolean dot = (row == c && col == c);
                sb.append((vertical || horizontal || dot) ? '1' : '0');
            }
            return sb.toString();
        }

        private final void sharpRect(float x, float y, float w, float h, int color) {
            if (w <= 0.0f || h <= 0.0f) {
                return;
            }
            Render2D.rect(new BuiltRectangle(x, y, w, h, 0.0f, color).withSmoothness(0.0f));
        }

        private final int multRedWhite(float red) {
            int gb = Math.min(255, Math.round(255.0f / Math.max(1.0E-4f, red)));
            return 0xFFFF0000 | gb << 8 | gb;
        }

        private final void drawGradientRing(float cx, float cy, float rad, float thickness, float endDeg, float alpha255) {
            if (endDeg < 1.0f) {
                return;
            }
            int stops = 16;
            float feather = Math.min(14.0f, endDeg * 0.5f);
            boolean fullCircle = endDeg >= 359.999f;
            float seamOffset = fullCircle ? 360.0f / (float)stops * 0.5f : 0.0f;
            List ranges = new ArrayList(stops);
            for (int i = 0; i < stops; ++i) {
                float a0 = seamOffset + endDeg * (float)i / (float)stops;
                float a1 = seamOffset + endDeg * (float)(i + 1) / (float)stops;
                int colA = ClientAccent.gradientColorAt(this.pingpong((float)i / (float)stops), alpha255, cx, cy);
                int colB = ClientAccent.gradientColorAt(this.pingpong((float)(i + 1) / (float)stops), alpha255, cx, cy);
                float blendIn = !fullCircle && i == 0 ? feather : 0.0f;
                float blendOut = !fullCircle && i == stops - 1 ? feather : 0.0f;
                ranges.add(Outline360Range.Companion.gradient(a0, a1, colA, colB, blendIn, blendOut));
            }
            int defaultColor = fullCircle ? ClientAccent.accentAt(alpha255, cx, cy) : ClientAccent.accentAt(1.0f, cx, cy);
            Render2D.outline360(cx - rad, cy - rad, rad * 2.0f, rad * 2.0f, rad, thickness, defaultColor, ranges);
        }

        private final float pingpong(float t) {
            float f = t * 2.0f;
            return f <= 1.0f ? f : 2.0f - f;
        }

        private final float clamp(float v, float lo, float hi) {
            return Math.max(lo, Math.min(hi, v));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

