/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.render.Camera
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Vector4f
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.Camera;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.player.AttackEntityEvent;
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
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.modules.post.hitbubbles.HitBubblesRenderer;
import rtx.kimiko.utils.render.render2d.ClientPalette;

@Feature(value={"hitbubbles"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 ?2\u00020\u0001:\u0002@?B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0014\u00a2\u0006\u0004\b\b\u0010\u0003J\u001b\u0010\f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0003b\u0002\b\u000b\u00a2\u0006\u0004\b\f\u0010\rJ5\u0010\u0015\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\u0017H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001f\u0010\u0006R\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0014\u0010&\u001a\u00020#8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010%R\u0014\u0010'\u001a\u00020#8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010%R\u0014\u0010(\u001a\u00020 8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b(\u0010\"R\u0014\u0010*\u001a\u00020)8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020#8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010%R\u0014\u0010-\u001a\u00020 8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b-\u0010\"R\u0014\u0010.\u001a\u00020#8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b.\u0010%R\u0014\u0010/\u001a\u00020 8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u0010\"R\u0014\u00100\u001a\u00020)8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u0010+R\u0014\u00101\u001a\u00020#8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b1\u0010%R\u0014\u00102\u001a\u00020 8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u0010\"R\u0014\u00104\u001a\u0002038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0014\u00106\u001a\u00020)8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b6\u0010+R\u0014\u00108\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010:\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u00109R\u001a\u0010=\u001a\b\u0012\u0004\u0012\u00020<0;8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u0010>\u00ca\u0001\u0010\bA\u0012\f\bB\u0012\b\b\fJ\u0004\b\b(C\u00a8\u0006D"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/HitBubbles;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "colorsApply", "()Z", "", "onDisable", "Lrtx/kimiko/api/events/impl/player/AttackEntityEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onAttack", "(Lrtx/kimiko/api/events/impl/player/AttackEntityEvent;)V", "Lnet/minecraft/Framebuffer;", "renderTarget", "Lorg/joml/Matrix4f;", "positionMatrix", "projectionMatrix", "Lnet/minecraft/Camera;", "camera", "onAfterWorld", "(Lnet/minecraft/Framebuffer;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/Camera;)V", "", "fadeOutSeconds", "()F", "", "index", "rawAlpha", "getColor", "(IF)I", "usesClientPalette", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "rippleSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "strength", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "size", "duration", "warpSeparator", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "warp", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "warpStrength", "effectSeparator", "saturation", "tintSeparator", "tint", "tintStrength", "colorSeparator", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "colorMode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "useSecondColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customSecondColor", "", "Lrtx/kimiko/api/modules/impl/Visuals/HitBubbles$Ripple;", "ripples", "Ljava/util/List;", "Companion", "Ripple", "Lrtx/kimiko/api/liteapi/Feature;", "value", "hitbubbles", "rtx.kimiko:kimiko"})
public final class HitBubbles
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting rippleSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Волна"));
    @NotNull
    private final NumberSetting strength = (NumberSetting)this.register((Setting)new NumberSetting("Сила", "Насколько сильно волна искривляет мир.", 1.5, 0.2, 3.0, 0.1));
    @NotNull
    private final NumberSetting size = (NumberSetting)this.register((Setting)new NumberSetting("Размер", "Радиус волны как доля экрана.", 0.2, 0.1, 0.6, 0.05));
    @NotNull
    private final NumberSetting duration = (NumberSetting)this.register((Setting)new NumberSetting("Длительность", "Время жизни волны в миллисекундах.", 1000.0, 300.0, 2000.0, 50.0));
    @NotNull
    private final SeparatorSetting warpSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Искривление"));
    @NotNull
    private final BooleanSetting warp = (BooleanSetting)this.register((Setting)new BooleanSetting("Искривление", "Добавляет волнистую турбулентность, искривляющую мир внутри пузыря.", false));
    @NotNull
    private final NumberSetting warpStrength = (NumberSetting)this.register((Setting)new NumberSetting("Сила искривления", "Сила внутреннего волнистого искривления.", 0.5, 0.1, 2.0, 0.1).visibleWhen(() -> HitBubbles.warpStrength$lambda$0(this)));
    @NotNull
    private final SeparatorSetting effectSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Цвет"));
    @NotNull
    private final NumberSetting saturation = (NumberSetting)this.register((Setting)new NumberSetting("Насыщенность", "Насыщенность цвета внутри пузыря: -1 серый, 0 обычный, +1 яркий.", 1.0, -1.0, 1.0, 0.05));
    @NotNull
    private final SeparatorSetting tintSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Подкраска"));
    @NotNull
    private final BooleanSetting tint = (BooleanSetting)this.register((Setting)new BooleanSetting("Подкрашивать цветом", "Подкрашивает область волны хитбабла цветом ниже.", true));
    @NotNull
    private final NumberSetting tintStrength = (NumberSetting)this.register((Setting)new NumberSetting("Сила цвета", "Насколько сильно окрашивается волна: 0% нет, 100% полный цвет.", 100.0, 0.0, 100.0, 1.0).visibleWhen(() -> HitBubbles.tintStrength$lambda$0(this)));
    @NotNull
    private final SeparatorSetting colorSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Цвет волны").visible(() -> HitBubbles.colorSeparator$lambda$0(this)));
    @NotNull
    private final ModeSetting colorMode;
    @NotNull
    private final BooleanSetting useSecondColor;
    @NotNull
    private final ColorSetting customColor;
    @NotNull
    private final ColorSetting customSecondColor;
    @NotNull
    private final List<Ripple> ripples;
    @NotNull
    private static final String COLOR_RAINBOW = "Радуга";
    @NotNull
    private static final String COLOR_CLIENT = "Клиент";
    @NotNull
    private static final String COLOR_CUSTOM = "Свой";
    private static final int DARK_SECOND_COLOR = new Color(16, 16, 16, 75).getRGB();
    @JvmField
    @Nullable
    public static HitBubbles INSTANCE;

    public HitBubbles() {
        super("Hit Bubbles", "Искажает пространство волной в месте удара.", Category.VISUALS);
        String[] stringArray = new String[]{COLOR_RAINBOW, COLOR_CLIENT, COLOR_CUSTOM};
        this.colorMode = (ModeSetting)this.register((Setting)new ModeSetting("Режим цвета", "Режим цвета подкраски.", COLOR_CLIENT, stringArray));
        this.useSecondColor = (BooleanSetting)this.register((Setting)new BooleanSetting("Второй цвет", "Использовать второй свой цвет.", false));
        this.customColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет", "Основной цвет подкраски.", new Color(255, 255, 255, 255)));
        this.customSecondColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет 2", "Второй цвет подкраски.", new Color(ColorEngine.lerpColor(-1, DARK_SECOND_COLOR, 0.7f), true)));
        this.ripples = new ArrayList();
        INSTANCE = this;
        this.colorMode.visibleWhen(() -> HitBubbles._init_$lambda$0(this));
        this.useSecondColor.visibleWhen(() -> HitBubbles._init_$lambda$1(this));
        this.customColor.visibleWhen(() -> HitBubbles._init_$lambda$2(this));
        this.customSecondColor.visibleWhen(() -> HitBubbles._init_$lambda$3(this));
    }

    private final boolean colorsApply() {
        return this.tint.getValue();
    }

    @Override
    protected void onDisable() {
        this.ripples.clear();
    }

    @EventHandler
    private final void onAttack(AttackEntityEvent event) {
        if (!this.isEnabled()) {
            return;
        }
        Entity entity2 = event.getTarget();
        if (entity2 == null) {
            return;
        }
        Entity target = entity2;
        Vec3d vec3d2 = target.getEntityPos().add(0.0, (double)target.getHeight() * 0.5, 0.0);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"add(...)");
        Vec3d pos = vec3d2;
        this.ripples.add(new Ripple(pos, System.currentTimeMillis()));
        while (this.ripples.size() > 16) {
            this.ripples.remove(0);
        }
    }

    public final void onAfterWorld(@Nullable Framebuffer renderTarget, @Nullable Matrix4f positionMatrix, @Nullable Matrix4f projectionMatrix, @Nullable Camera camera) {
        if (renderTarget == null || camera == null || positionMatrix == null || projectionMatrix == null || this.ripples.isEmpty()) {
            return;
        }
        long now = System.currentTimeMillis();
        float lifetime = Math.max(1.0f, this.duration.getFloat());
        float maxRadius = this.size.getFloat();
        float ringWidth = HitBubbles.Companion.clamp(maxRadius * 0.4f, 0.06f, 0.22f);
        float baseAmp = 0.018f * this.strength.getFloat() * this.visualAlpha();
        float aspect = (float)renderTarget.textureWidth / (float)Math.max(1, renderTarget.textureHeight);
        Vec3d vec3d2 = camera.getCameraPos();
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"position(...)");
        Vec3d cam = vec3d2;
        float[] data = new float[156];
        int count = 0;
        Iterator<Ripple> it = this.ripples.iterator();
        while (it.hasNext()) {
            Ripple ripple = it.next();
            float progress = (float)(now - ripple.getSpawnMs()) / lifetime;
            if (progress >= 1.0f) {
                it.remove();
                continue;
            }
            if (count >= 16) continue;
            Vector4f v = new Vector4f((float)(ripple.getPos().x - cam.x), (float)(ripple.getPos().y - cam.y), (float)(ripple.getPos().z - cam.z), 1.0f);
            positionMatrix.transform(v);
            projectionMatrix.transform(v);
            if (v.w <= 1.0E-4f) continue;
            float u = v.x / v.w * 0.5f + 0.5f;
            float sv = v.y / v.w * 0.5f + 0.5f;
            if (u < -0.5f || u > 1.5f || sv < -0.5f || sv > 1.5f) continue;
            float fadeIn = HitBubbles.Companion.clamp(progress / 0.1f, 0.0f, 1.0f);
            float fadeOut = HitBubbles.Companion.clamp((1.0f - progress) / 0.3f, 0.0f, 1.0f);
            float env = fadeIn * fadeOut;
            float amp = baseAmp * env;
            int base = 28 + count * 8;
            data[base] = u;
            data[base + 1] = sv;
            data[base + 2] = progress * maxRadius;
            data[base + 3] = ringWidth;
            data[base + 4] = amp;
            data[base + 5] = env;
            data[base + 6] = maxRadius;
            ++count;
        }
        if (count == 0) {
            return;
        }
        data[0] = count;
        data[1] = aspect;
        data[2] = (float)(now % 100000L) / 1000.0f;
        data[3] = this.warp.getValue() ? 0.01f * this.warpStrength.getFloat() : 0.0f;
        data[4] = HitBubbles.Companion.clamp(1.0f + this.saturation.getFloat(), 0.0f, 2.0f);
        if (this.tint.getValue()) {
            data[5] = HitBubbles.Companion.clamp(this.tintStrength.getFloat() / 100.0f, 0.0f, 1.0f);
            HitBubbles.Companion.putColor(data, 8, this.getColor(0, 1.0f));
            HitBubbles.Companion.putColor(data, 12, this.getColor(90, 1.0f));
            HitBubbles.Companion.putColor(data, 16, this.getColor(180, 1.0f));
            HitBubbles.Companion.putColor(data, 20, this.getColor(270, 1.0f));
            if (this.usesClientPalette()) {
                data[6] = 1.0f;
                data[24] = HitBubbles.Companion.paletteFadeT(8, 0);
                data[25] = HitBubbles.Companion.paletteFadeT(8, 90);
                data[26] = HitBubbles.Companion.paletteFadeT(8, 180);
                data[27] = HitBubbles.Companion.paletteFadeT(8, 270);
            }
        }
        HitBubblesRenderer.apply(renderTarget, data);
    }

    @Override
    public float fadeOutSeconds() {
        return 0.5f;
    }

    private final int getColor(int index, float rawAlpha) {
        float alpha = rawAlpha * this.visualAlpha();
        if (this.colorMode.is(COLOR_RAINBOW)) {
            return HitBubbles.Companion.rainbow(8, index, 1.0f, 1.0f, alpha);
        }
        int firstColor = 0;
        int secondColor = 0;
        if (this.colorMode.is(COLOR_CLIENT)) {
            InterfaceModule iface;
            int[] palette = ClientPalette.colors();
            if (palette != null && palette.length >= 2) {
                return ColorEngine.multAlpha(HitBubbles.Companion.paletteFade(8, index, palette), alpha);
            }
            InterfaceModule interfaceModule = iface = InterfaceModule.Companion.getInstance();
            firstColor = interfaceModule != null ? interfaceModule.clientPrimaryColorOpaque() : -1;
            secondColor = iface != null && iface.usesSecondClientColor() ? iface.clientSecondaryColorOpaque() : firstColor;
        } else {
            firstColor = this.customColor.getColor();
            int n = secondColor = this.useSecondColor.getValue() ? this.customSecondColor.getColor() : this.customColor.getColor();
        }
        if (firstColor == secondColor) {
            return ColorEngine.multAlpha(firstColor, alpha);
        }
        return ColorEngine.multAlpha(HitBubbles.Companion.fade(8, index, firstColor, secondColor), alpha);
    }

    private final boolean usesClientPalette() {
        if (!this.colorMode.is(COLOR_CLIENT)) {
            return false;
        }
        int[] palette = ClientPalette.colors();
        return palette != null && palette.length >= 2;
    }

    private static final Boolean warpStrength$lambda$0(HitBubbles this$0) {
        return this$0.warp.getValue();
    }

    private static final Boolean tintStrength$lambda$0(HitBubbles this$0) {
        return this$0.tint.getValue();
    }

    private static final Boolean colorSeparator$lambda$0(HitBubbles this$0) {
        return this$0.colorsApply();
    }

    private static final Boolean _init_$lambda$0(HitBubbles this$0) {
        return this$0.colorsApply();
    }

    private static final Boolean _init_$lambda$1(HitBubbles this$0) {
        return this$0.colorsApply() && this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean _init_$lambda$2(HitBubbles this$0) {
        return this$0.colorsApply() && this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean _init_$lambda$3(HitBubbles this$0) {
        return this$0.colorsApply() && this$0.colorMode.is(COLOR_CUSTOM) && this$0.useSecondColor.getValue();
    }

    @JvmStatic
    @Nullable
    public static final HitBubbles getInstance() {
        return Companion.getInstance();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J'\u0010\u000e\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ7\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J/\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ'\u0010 \u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b \u0010!J'\u0010%\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b%\u0010&R\u0014\u0010(\u001a\u00020'8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020'8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010)R\u0014\u0010+\u001a\u00020'8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010)R\u0014\u0010,\u001a\u00020\n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u001d\u0010/\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b.\u00a2\u0006\u0006\n\u0004\b/\u00100\u00a8\u00061"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/HitBubbles.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/HitBubbles;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/HitBubbles;", "", "data", "", "offset", "color", "", "putColor", "([FII)V", "speed", "index", "", "saturation", "brightness", "alpha", "rainbow", "(IIFFF)I", "first", "second", "fade", "(IIII)I", "paletteFadeT", "(II)F", "", "palette", "paletteFade", "(II[I)I", "v", "min", "max", "clamp", "(FFF)F", "", "COLOR_RAINBOW", "Ljava/lang/String;", "COLOR_CLIENT", "COLOR_CUSTOM", "DARK_SECOND_COLOR", "I", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/HitBubbles;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final HitBubbles getInstance() {
            HitBubbles module = ModuleManager.Companion.get().get(HitBubbles.class);
            HitBubbles hitBubbles = module;
            if (hitBubbles == null) {
                hitBubbles = INSTANCE;
            }
            return hitBubbles;
        }

        private final void putColor(float[] data, int offset, int color) {
            data[offset] = (float)(color >> 16 & 0xFF) / 255.0f;
            data[offset + 1] = (float)(color >> 8 & 0xFF) / 255.0f;
            data[offset + 2] = (float)(color & 0xFF) / 255.0f;
        }

        private final int rainbow(int speed, int index, float saturation, float brightness, float alpha) {
            int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
            int rgb = ColorEngine.rainbow(angle, saturation, brightness);
            return ColorEngine.rgba(rgb >>> 16 & 0xFF, rgb >>> 8 & 0xFF, rgb & 0xFF, Math.round(this.clamp(alpha, 0.0f, 1.0f) * 255.0f));
        }

        private final int fade(int speed, int index, int first, int second) {
            int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
            angle = angle >= 180 ? 360 - angle : angle;
            return ColorEngine.lerpColor(first, second, (float)angle / 180.0f);
        }

        private final float paletteFadeT(int speed, int index) {
            int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
            return (float)angle / 360.0f;
        }

        private final int paletteFade(int speed, int index, int[] palette) {
            int n = palette.length;
            int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
            float f = (float)angle / 360.0f * (float)n;
            int i = (int)f % n;
            int j = (i + 1) % n;
            int a = palette[i] | 0xFF000000;
            int b = palette[j] | 0xFF000000;
            return ColorEngine.lerpColor(a, b, f - (float)Math.floor(f)) | 0xFF000000;
        }

        private final float clamp(float v, float min, float max) {
            return Math.max(min, Math.min(max, v));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ$\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0016\u001a\u00020\u0015H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u000b\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/HitBubbles$Ripple;", "", "Lnet/minecraft/Vec3d;", "pos", "", "spawnMs", "<init>", "(Lnet/minecraft/Vec3d;J)V", "component1", "()Lnet/minecraft/Vec3d;", "component2", "()J", "copy", "(Lnet/minecraft/Vec3d;J)Lrtx/kimiko/api/modules/impl/Visuals/HitBubbles$Ripple;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lnet/minecraft/Vec3d;", "getPos", "J", "getSpawnMs", "rtx.kimiko:kimiko"})
    private static final class Ripple {
        @NotNull
        private final Vec3d pos;
        private final long spawnMs;

        public Ripple(@NotNull Vec3d pos, long spawnMs) {
            Intrinsics.checkNotNullParameter((Object)pos, (String)"pos");
            this.pos = pos;
            this.spawnMs = spawnMs;
        }

        @NotNull
        public final Vec3d getPos() {
            return this.pos;
        }

        public final long getSpawnMs() {
            return this.spawnMs;
        }

        @NotNull
        public final Vec3d component1() {
            return this.pos;
        }

        public final long component2() {
            return this.spawnMs;
        }

        @NotNull
        public final Ripple copy(@NotNull Vec3d pos, long spawnMs) {
            Intrinsics.checkNotNullParameter((Object)pos, (String)"pos");
            return new Ripple(pos, spawnMs);
        }

        public static /* synthetic */ Ripple copy$default(Ripple ripple, Vec3d vec3d2, long l, int n, Object object) {
            if ((n & 1) != 0) {
                vec3d2 = ripple.pos;
            }
            if ((n & 2) != 0) {
                l = ripple.spawnMs;
            }
            return ripple.copy(vec3d2, l);
        }

        @NotNull
        public String toString() {
            return "Ripple(pos=" + this.pos + ", spawnMs=" + this.spawnMs + ")";
        }

        public int hashCode() {
            int result = this.pos.hashCode();
            result = result * 31 + Long.hashCode(this.spawnMs);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Ripple)) {
                return false;
            }
            Ripple ripple = (Ripple)other;
            if (!Intrinsics.areEqual((Object)this.pos, (Object)ripple.pos)) {
                return false;
            }
            return this.spawnMs == ripple.spawnMs;
        }
    }
}

