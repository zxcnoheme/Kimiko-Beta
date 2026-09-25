/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  net.minecraft.client.gl.Framebuffer
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.awt.Color;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import net.minecraft.client.gl.Framebuffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.NumberSetting;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.modules.post.fogblur.FogBlurRenderer;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"fogblur"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 )2\u00020\u0001:\u0001)B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0004\u00a2\u0006\u0004\b\n\u0010\u0006J\r\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0013\u001a\u00020\u000eH\u0015b\u000e\b\u000f\u0012\n\b\u0010\u0012\u0006\b\n0\u00118\u0012\u00a2\u0006\u0004\b\u0013\u0010\u0003J\u000f\u0010\u0014\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u0014\u0010\tJ\u0017\u0010\u0017\u001a\u00020\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0019\u0010\rR\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001cR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010%\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010&R\u0014\u0010(\u001a\u00020\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b(\u0010\u001c\u00ca\u0001\u0010\b*\u0012\f\b\u0010\u0012\b\b\fJ\u0004\b\b(+\u00a8\u0006,"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/FogBlur;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "hasCustomFogDistance", "()Z", "", "getFogDistanceFactor", "()F", "hasCustomFogColor", "", "getCustomFogColor", "()I", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "onDisable", "fadeOutSeconds", "Lnet/minecraft/Framebuffer;", "renderTarget", "onAfterTranslucent", "(Lnet/minecraft/Framebuffer;)V", "resolveTintColor", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "fogStrength", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "fogDistance", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "colorMode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "useSecondColor", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customSecondColor", "colorOpacity", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "fogblur", "rtx.kimiko:kimiko"})
public final class FogBlur
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final NumberSetting fogStrength = (NumberSetting)this.register((Setting)new NumberSetting("Сила размытия", "Сила размытия для эффекта тумана", 20.0, 1.0, 20.0, 1.0));
    @NotNull
    private final NumberSetting fogDistance = (NumberSetting)this.register((Setting)new NumberSetting("Дистанция тумана", "На какой дистанции начинается эффект тумана", 200.0, 0.0, 200.0, 5.0));
    @NotNull
    private final ModeSetting colorMode;
    @NotNull
    private final BooleanSetting useSecondColor;
    @NotNull
    private final ColorSetting customColor;
    @NotNull
    private final ColorSetting customSecondColor;
    @NotNull
    private final NumberSetting colorOpacity;
    @NotNull
    private static final String COLOR_RAINBOW = "Радуга";
    @NotNull
    private static final String COLOR_CLIENT = "Клиент";
    @NotNull
    private static final String COLOR_CUSTOM = "Кастом";
    @JvmField
    @Nullable
    public static FogBlur INSTANCE;

    public FogBlur() {
        super("Fog Blur", "Размывает удалённые пиксели мира по глубине, создавая эффект тумана.", Category.VISUALS);
        String[] stringArray = new String[]{COLOR_RAINBOW, COLOR_CLIENT, COLOR_CUSTOM};
        this.colorMode = (ModeSetting)this.register((Setting)new ModeSetting("Режим цвета", "Режим окрашивания тумана", COLOR_CLIENT, stringArray));
        this.useSecondColor = (BooleanSetting)this.register((Setting)new BooleanSetting("Второй цвет", "Использовать второй цвет в режиме Кастом", false).visibleWhen(() -> FogBlur.useSecondColor$lambda$0(this)));
        this.customColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет", "Основной цвет тумана", new Color(255, 255, 255)).visibleWhen(() -> FogBlur.customColor$lambda$0(this)));
        this.customSecondColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет 2", "Второй цвет тумана", new Color(87, 87, 87, 129)).visibleWhen(() -> FogBlur.customSecondColor$lambda$0(this)));
        this.colorOpacity = (NumberSetting)this.register((Setting)new NumberSetting("Прозрачность цвета", "Прозрачность окрашивания тумана", 1.0, 1.0, 100.0, 1.0));
        INSTANCE = this;
    }

    public final boolean hasCustomFogDistance() {
        return false;
    }

    public final float getFogDistanceFactor() {
        return 1.0f;
    }

    public final boolean hasCustomFogColor() {
        return false;
    }

    public final int getCustomFogColor() {
        return -1;
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onDisable() {
        FogBlurRenderer.clear();
    }

    @Override
    public float fadeOutSeconds() {
        return 0.8f;
    }

    public final void onAfterTranslucent(@Nullable Framebuffer renderTarget) {
        if (this.mc.player == null || this.mc.world == null || this.mc.gameRenderer == null || renderTarget == null) {
            return;
        }
        if (FogBlurRenderer.isDisabledAfterError()) {
            return;
        }
        float fade = this.visualAlpha();
        int tint = this.resolveTintColor();
        float mix = this.colorOpacity.getFloat() / 100.0f * fade;
        FogBlurRenderer.setBlurTint((float)(tint >> 16 & 0xFF) / 255.0f, (float)(tint >> 8 & 0xFF) / 255.0f, (float)(tint & 0xFF) / 255.0f, mix, this.colorMode.is(COLOR_CLIENT));
        FogBlurRenderer.apply(renderTarget, this.fogStrength.getFloat() * fade, Math.max(1.0f, this.fogDistance.getFloat()), 100.0f, 0.52f, 2);
    }

    private final int resolveTintColor() {
        if (this.colorMode.is(COLOR_CLIENT)) {
            InterfaceModule iface;
            InterfaceModule interfaceModule = iface = InterfaceModule.Companion.getInstance();
            return interfaceModule != null ? interfaceModule.clientPrimaryColorOpaque() : -1;
        }
        if (this.colorMode.is(COLOR_CUSTOM)) {
            int first = this.customColor.getColor();
            if (!this.useSecondColor.getValue()) {
                return first;
            }
            float wave = 0.5f + 0.5f * (float)Math.sin((double)System.currentTimeMillis() / 1200.0);
            return ColorEngine.lerpColor(first, this.customSecondColor.getColor(), wave);
        }
        return ClientAccent.rainbowFlow(0.0f, 255.0f);
    }

    private static final Boolean useSecondColor$lambda$0(FogBlur this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean customColor$lambda$0(FogBlur this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean customSecondColor$lambda$0(FogBlur this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM) && this$0.useSecondColor.getValue();
    }

    @JvmStatic
    @Nullable
    public static final FogBlur getInstance() {
        return Companion.getInstance();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\nR\u0014\u0010\f\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\nR\u001d\u0010\u000e\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\r\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/FogBlur.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/FogBlur;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/FogBlur;", "", "COLOR_RAINBOW", "Ljava/lang/String;", "COLOR_CLIENT", "COLOR_CUSTOM", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/FogBlur;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final FogBlur getInstance() {
            FogBlur module = ModuleManager.Companion.get().get(FogBlur.class);
            FogBlur fogBlur = module;
            if (fogBlur == null) {
                fogBlur = INSTANCE;
            }
            return fogBlur;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

