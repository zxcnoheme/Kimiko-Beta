/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  kotlin.text.StringsKt
 *  net.minecraft.client.option.KeyBinding
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.drags.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.StringsKt;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.drags.Draggable;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.impl.Interface.KeyStrokesModule;
import rtx.kimiko.api.ui.settings.Setting;
import rtx.kimiko.api.ui.settings.impl.BoolSetting;
import rtx.kimiko.utils.animations.Easings;
import rtx.kimiko.utils.animations.HudFadeAnimation;
import rtx.kimiko.utils.animations.SmoothAnimation;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.ClientPalette;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 *2\u00020\u0001:\u0001*B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0007\u0010\u0006J\u000f\u0010\t\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0014\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0012H\u0014\u00a2\u0006\u0004\b\u0015\u0010\u0016JY\u0010!\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\b\u0010\u001d\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020'0&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b(\u0010)\u00a8\u0006+"}, d2={"Lrtx/kimiko/api/drags/components/KeyStrokesComp;", "Lrtx/kimiko/api/drags/Draggable;", "<init>", "()V", "", "width", "()F", "height", "", "displayName", "()Ljava/lang/String;", "", "isInteractive", "()Z", "", "Lrtx/kimiko/api/ui/settings/Setting;", "buildHudSettings", "()Ljava/util/List;", "Lnet/minecraft/DrawContext;", "graphics", "", "render", "(Lnet/minecraft/DrawContext;)V", "", "index", "kx", "ky", "kw", "kh", "label", "fontSize", "down", "alpha", "drawKey", "(IFFFFLjava/lang/String;FZF)V", "Lrtx/kimiko/utils/animations/HudFadeAnimation;", "visibility", "Lrtx/kimiko/utils/animations/HudFadeAnimation;", "", "Lrtx/kimiko/utils/animations/SmoothAnimation;", "press", "[Lrtx/kimiko/utils/animations/SmoothAnimation;", "Companion", "rtx.kimiko:kimiko"})
public final class KeyStrokesComp
extends Draggable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final HudFadeAnimation visibility = this.getHudFade();
    @NotNull
    private final SmoothAnimation[] press;
    private static final float KEY = 22.0f;
    private static final float GAP = 2.5f;
    private static final float MOUSE_H = 16.0f;
    private static final float SPACE_H = 14.5f;
    private static final float FALLBACK_RADIUS = 5.0f;
    private static final float WIDTH = 71.0f;
    private static final float KEY_FONT = 8.5f;
    private static final float MOUSE_FONT = 7.0f;
    private static final float SINK = 1.1f;
    private static final int LABEL_IDLE = -4604215;
    private static final int TOP_IDLE = -233038042;
    private static final int BOT_IDLE = -233498851;
    private static final int KEY_W = 0;
    private static final int KEY_A = 1;
    private static final int KEY_S = 2;
    private static final int KEY_D = 3;
    private static final int KEY_LMB = 4;
    private static final int KEY_RMB = 5;
    private static final int KEY_SPACE = 6;

    public KeyStrokesComp() {
        super("keystrokes", 5.0f, 225.0f);
        SmoothAnimation[] smoothAnimationArray = new SmoothAnimation[7];
        for (int i = 0; i < 7; i++) {
            smoothAnimationArray[i] = new SmoothAnimation();
        }
        this.press = smoothAnimationArray;
        this.visibility.set(0.0);
        for (SmoothAnimation animation : this.press) {
            animation.set(0.0);
        }
    }

    @Override
    public float width() {
        return 71.0f;
    }

    @Override
    public float height() {
        KeyStrokesModule module = KeyStrokesComp.Companion.module();
        float h = 46.5f;
        if (module == null || module.showMouse.getValue()) {
            h += 18.5f;
        }
        if (module == null || module.showSpace.getValue()) {
            h += 17.0f;
        }
        return h;
    }

    @Override
    @NotNull
    public String displayName() {
        return "Key Strokes";
    }

    @Override
    public boolean isInteractive() {
        KeyStrokesModule module = KeyStrokesComp.Companion.module();
        return module != null && module.isEnabled();
    }

    @Override
    @NotNull
    protected List<Setting> buildHudSettings() {
        ArrayList<Setting> list = new ArrayList<Setting>(super.buildHudSettings());
        KeyStrokesModule module = KeyStrokesComp.Companion.module();
        if (module != null) {
            list.add(new BoolSetting(module.showMouse));
            list.add(new BoolSetting(module.showCps));
            list.add(new BoolSetting(module.showSpace));
        }
        return list;
    }

    @Override
    protected void render(@NotNull DrawContext graphics) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        KeyStrokesModule module = KeyStrokesComp.Companion.module();
        boolean enabled = module != null && module.isEnabled();
        this.visibility.updateTarget(enabled);
        float alpha = this.visibility.get();
        if (alpha <= 0.01f) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        boolean showMouse = module == null || module.showMouse.getValue();
        boolean showCps = module != null && module.showCps.getValue();
        boolean showSpace = module == null || module.showSpace.getValue();
        float x = this.getX();
        float y = this.getY();
        float w = this.width();
        float h = this.height();
        float scale = 0.92f + alpha * 0.08f;
        float originX = x + w * 0.5f;
        float originY = y + h * 0.5f;
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(originX, originY);
        graphics.getMatrices().scale(scale);
        graphics.getMatrices().translate(-originX, -originY);
        Render2D.beginFrame(graphics);
        float rowY = y;
        KeyBinding keyBinding2 = mc.options.forwardKey;
        Intrinsics.checkNotNullExpressionValue((Object)keyBinding2, (String)"keyUp");
        this.drawKey(0, x + 22.0f + 2.5f, rowY, 22.0f, 22.0f, KeyStrokesComp.Companion.keyLabel(keyBinding2), 8.5f, mc.options.forwardKey.isPressed(), alpha);
        KeyBinding keyBinding3 = mc.options.leftKey;
        Intrinsics.checkNotNullExpressionValue((Object)keyBinding3, (String)"keyLeft");
        this.drawKey(1, x, rowY += 24.5f, 22.0f, 22.0f, KeyStrokesComp.Companion.keyLabel(keyBinding3), 8.5f, mc.options.leftKey.isPressed(), alpha);
        KeyBinding keyBinding4 = mc.options.backKey;
        Intrinsics.checkNotNullExpressionValue((Object)keyBinding4, (String)"keyDown");
        this.drawKey(2, x + 22.0f + 2.5f, rowY, 22.0f, 22.0f, KeyStrokesComp.Companion.keyLabel(keyBinding4), 8.5f, mc.options.backKey.isPressed(), alpha);
        KeyBinding keyBinding5 = mc.options.rightKey;
        Intrinsics.checkNotNullExpressionValue((Object)keyBinding5, (String)"keyRight");
        this.drawKey(3, x + 49.0f, rowY, 22.0f, 22.0f, KeyStrokesComp.Companion.keyLabel(keyBinding5), 8.5f, mc.options.rightKey.isPressed(), alpha);
        rowY += 24.5f;
        if (showMouse) {
            float mouseW = (w - 2.5f) * 0.5f;
            this.drawKey(4, x, rowY, mouseW, 16.0f, KeyStrokesComp.Companion.mouseLabel(module, true, showCps), 7.0f, mc.options.attackKey.isPressed(), alpha);
            this.drawKey(5, x + mouseW + 2.5f, rowY, mouseW, 16.0f, KeyStrokesComp.Companion.mouseLabel(module, false, showCps), 7.0f, mc.options.useKey.isPressed(), alpha);
            rowY += 18.5f;
        }
        if (showSpace) {
            this.drawKey(6, x, rowY, w, 14.5f, null, 0.0f, mc.options.jumpKey.isPressed(), alpha);
        }
        Render2D.flush();
        graphics.getMatrices().popMatrix();
    }

    private final void drawKey(int index, float kx, float ky, float kw, float kh, String label, float fontSize, boolean down, float alpha) {
        SmoothAnimation anim = this.press[index];
        anim.run(down ? 1.0 : 0.0, down ? 0.55 : 0.28, Easings.CUBIC_OUT, true);
        anim.update();
        float t = anim.get();
        float inset = 1.1f * t;
        float rx = kx + inset;
        float ry = ky + inset;
        float rw = kw - inset * 2.0f;
        float rh = kh - inset * 2.0f;
        float radius = Math.min(KeyStrokesComp.Companion.panelRadius(), Math.min(rw, rh) * 0.5f);
        float baseAlpha = alpha * (1.0f - t);
        if (baseAlpha > 0.003921569f) {
            int top = ColorEngine.multAlpha(-233038042, baseAlpha);
            int bot = ColorEngine.multAlpha(-233498851, baseAlpha);
            Render2D.rect(rx, ry, rw, rh, radius, top, top, bot, bot);
        }
        if (t > 0.003921569f) {
            RectUtil.drawClientRect(rx, ry, rw, rh, radius, alpha * t);
        }
        int outlineIdle = ColorEngine.rgba(255, 255, 255, MathKt.roundToInt((float)(26.0f * alpha)));
        int[] accentOutline = ClientPalette.cornerColorsAt(0.5882353f * alpha, rx + rw * 0.5f, ry + rh * 0.5f);
        Render2D.outline(rx, ry, rw, rh, radius, 0.5f, ColorEngine.lerpColor(outlineIdle, accentOutline[0], t), ColorEngine.lerpColor(outlineIdle, accentOutline[1], t), ColorEngine.lerpColor(outlineIdle, accentOutline[2], t), ColorEngine.lerpColor(outlineIdle, accentOutline[3], t));
        int labelColor = ColorEngine.multAlpha(ColorEngine.lerpColor(-4604215, -1, t), alpha);
        if (label == null) {
            float pillW = rw * 0.42f;
            float pillH = 2.5f;
            Render2D.rect(rx + (rw - pillW) * 0.5f, ry + (rh - pillH) * 0.5f, pillW, pillH, pillH * 0.5f, labelColor, labelColor, labelColor, labelColor);
            return;
        }
        float size = fontSize;
        float textW = Fonts.MEDIUM.msdfWidth(label, size);
        while (textW > rw - 6.0f && size > 5.5f) {
            textW = Fonts.MEDIUM.msdfWidth(label, size -= 0.5f);
        }
        Fonts.MEDIUM.msdf(label, rx + (rw - textW) * 0.5f, ry + (rh - size) * 0.5f - size * 0.12f, size, labelColor);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ)\u0010\u0012\u001a\u00020\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0015R\u0014\u0010\u0018\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0015R\u0014\u0010\u0019\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0015R\u0014\u0010\u001a\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0015R\u0014\u0010\u001b\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0015R\u0014\u0010\u001c\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u0015R\u0014\u0010\u001d\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u0015R\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b!\u0010 R\u0014\u0010\"\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010 R\u0014\u0010#\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b#\u0010 R\u0014\u0010$\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010 R\u0014\u0010%\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010 R\u0014\u0010&\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010 R\u0014\u0010'\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010 R\u0014\u0010(\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010 R\u0014\u0010)\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010 \u00a8\u0006*"}, d2={"Lrtx/kimiko/api/drags/components/KeyStrokesComp.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Interface/KeyStrokesModule;", "module", "()Lrtx/kimiko/api/modules/impl/Interface/KeyStrokesModule;", "", "panelRadius", "()F", "Lnet/minecraft/KeyBinding;", "mapping", "", "keyLabel", "(Lnet/minecraft/KeyBinding;)Ljava/lang/String;", "", "left", "showCps", "mouseLabel", "(Lrtx/kimiko/api/modules/impl/Interface/KeyStrokesModule;ZZ)Ljava/lang/String;", "KEY", "F", "GAP", "MOUSE_H", "SPACE_H", "FALLBACK_RADIUS", "WIDTH", "KEY_FONT", "MOUSE_FONT", "SINK", "", "LABEL_IDLE", "I", "TOP_IDLE", "BOT_IDLE", "KEY_W", "KEY_A", "KEY_S", "KEY_D", "KEY_LMB", "KEY_RMB", "KEY_SPACE", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final KeyStrokesModule module() {
            return ModuleManager.Companion.get().get(KeyStrokesModule.class);
        }

        private final float panelRadius() {
            InterfaceModule iface;
            InterfaceModule interfaceModule = iface = InterfaceModule.Companion.getInstance();
            return interfaceModule != null ? interfaceModule.rectCornerRadius.getFloat() : 5.0f;
        }

        private final String keyLabel(KeyBinding mapping) {
            Object id = mapping.getBoundKeyTranslationKey();
            if (id == null || StringsKt.isBlank((CharSequence)((CharSequence)id))) {
                return "?";
            }
            if (String.valueOf(id).startsWith("key.keyboard.")) {
                String string = ((String)id).substring(13);
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
                id = string;
            } else if (String.valueOf(id).startsWith("key.mouse.")) {
                String string = ((String)id).substring(10);
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
                id = "M " + string;
            }
            String string = String.valueOf(id).replace((char)'.', (char)' ');
            Locale locale = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
            String string2 = string.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toUpperCase(...)");
            return string2;
        }

        private final String mouseLabel(KeyStrokesModule module, boolean left, boolean showCps) {
            if (showCps && module != null) {
                int cps;
                int n = cps = left ? module.leftCps() : module.rightCps();
                if (cps > 0) {
                    return cps + " CPS";
                }
            }
            return left ? "LMB" : "RMB";
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

