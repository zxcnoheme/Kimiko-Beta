/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.screen.Screen
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import java.awt.Color;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ButtonSetting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.SelectSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.api.ui.UI;
import rtx.kimiko.api.ui.handshader.HandShaderEditorScreen;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.modules.post.handsflame.HandsFlameRenderer;
import rtx.kimiko.utils.render.modules.post.handshader.HandShaderManager;
import rtx.kimiko.utils.render.modules.post.handshader.HandShaders;
import rtx.kimiko.utils.render.modules.post.hologram.HologramHandsConfig;
import rtx.kimiko.utils.render.modules.post.hologram.HologramHandsRenderer;
import rtx.kimiko.utils.render.modules.post.shaderhands.ShaderHandsRenderer;

@Feature(value={"shaderhands"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 k2\u00020\u0001:\u0001kB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\u0003J\r\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\r\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\t\u00a2\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0012\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0003J\u000f\u0010\u0014\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0006J\u000f\u0010\u0015\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0006J\u000f\u0010\u0016\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0006J\u000f\u0010\u0017\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0006J\u000f\u0010\u0018\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0006J\u000f\u0010\u0019\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u0006J\u000f\u0010\u001a\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u0006J\u000f\u0010\u001b\u001a\u00020\u0007H\u0014\u00a2\u0006\u0004\b\u001b\u0010\u0003J\u000f\u0010\u001c\u001a\u00020\u0007H\u0014\u00a2\u0006\u0004\b\u001c\u0010\u0003J\u001b\u0010 \u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001dH\u0003b\u0002\b\u001f\u00a2\u0006\u0004\b \u0010!J\u000f\u0010\"\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\"\u0010\u0003J\u000f\u0010#\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b#\u0010\u0011J\u000f\u0010$\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b$\u0010\u0003J\u000f\u0010%\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b%\u0010\u0011J\u000f\u0010'\u001a\u00020&H\u0002\u00a2\u0006\u0004\b'\u0010(J\u0011\u0010*\u001a\u0004\u0018\u00010)H\u0002\u00a2\u0006\u0004\b*\u0010+J\u0017\u0010-\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b-\u0010.R\u0014\u00100\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0014\u00103\u001a\u0002028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b3\u00104R\u0014\u00106\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b6\u00107R\u0014\u00108\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00107R\u0014\u00109\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b9\u00107R\u0014\u0010:\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u00107R\u0014\u0010;\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b;\u00101R\u0014\u0010=\u001a\u00020<8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u0010>R\u0014\u0010?\u001a\u0002028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u00104R\u0014\u0010A\u001a\u00020@8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bA\u0010BR\u0014\u0010C\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bC\u00107R\u0014\u0010D\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u00107R\u0014\u0010E\u001a\u0002028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u00104R\u0014\u0010F\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bF\u00107R\u0014\u0010G\u001a\u00020@8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010BR\u0014\u0010H\u001a\u0002028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u00104R\u0014\u0010J\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010KR\u0014\u0010L\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u0010KR\u0014\u0010M\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bM\u00101R\u0014\u0010N\u001a\u0002028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bN\u00104R\u0014\u0010O\u001a\u0002028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bO\u00104R\u0014\u0010P\u001a\u00020@8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bP\u0010BR\u0014\u0010Q\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bQ\u0010KR\u0014\u0010R\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bR\u00107R\u0014\u0010S\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bS\u00107R\u0014\u0010T\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bT\u00107R\u0014\u0010U\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bU\u00107R\u0014\u0010V\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bV\u00107R\u0014\u0010W\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bW\u00101R\u0014\u0010X\u001a\u00020@8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bX\u0010BR\u0014\u0010Y\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bY\u0010KR\u0014\u0010Z\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bZ\u00107R\u0014\u0010[\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b[\u00107R\u0014\u0010\\\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\\\u00107R\u0014\u0010]\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b]\u00107R\u0014\u0010^\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b^\u00107R\u0014\u0010_\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b_\u00107R\u0014\u0010`\u001a\u00020/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b`\u00101R\u0014\u0010a\u001a\u00020@8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\ba\u0010BR\u0014\u0010c\u001a\u00020b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bc\u0010dR\u0014\u0010e\u001a\u00020@8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\be\u0010BR\u0014\u0010f\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bf\u0010KR\u0014\u0010g\u001a\u00020I8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bg\u0010KR\u0016\u0010i\u001a\u00020h8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bi\u0010j\u00ca\u0001\u0010\bl\u0012\f\bm\u0012\b\b\fJ\u0004\b\b(n\u00a8\u0006o"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ShaderHands;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "userShaderVisible", "()Z", "", "refreshUserShaderOptions", "", "userShaderName", "()Ljava/lang/String;", "name", "applyUserShader", "(Ljava/lang/String;)V", "", "userShaderColor", "()I", "userShaderColor2", "openShaderEditor", "isGlassOn", "isGlassCompatible", "isUserMode", "isOldMode", "isNewMode", "isHoloMode", "isFlameSettingsVisible", "onEnable", "onDisable", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "syncFlameRenderer", "resolveFlameColor", "syncHologram", "resolveHoloColor", "", "glowGradientColors", "()[I", "", "glowThemeParams", "()[F", "index", "glowColorAt", "(I)I", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "glassSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "glass", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "glassSaturation", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "glassWhite", "glassDistort", "glassTint", "glowSeparator", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "renderMode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "glow", "Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "glowMode", "Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "radius", "glowStrength", "blending", "outlineWidth", "glowColorMode", "glowSecondColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "glowColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "glowColor2", "flameSeparator", "flame", "onlyItems", "flameColorMode", "flameColor", "flameStrength", "flameRiseSpeed", "flameWobble", "flameLength", "flameBrightness", "holoSeparator", "holoColorMode", "holoColor", "holoOpacity", "holoTransparency", "holoScanlines", "holoScanSpeed", "holoGlitch", "holoFlicker", "userSeparator", "userShader", "Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;", "userEditor", "Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;", "userColorMode", "userColor", "userColor2", "", "lastShaderScanMs", "J", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "shaderhands", "rtx.kimiko:kimiko"})
public final class ShaderHands
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting glassSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Стекло").visible(() -> ShaderHands.glassSeparator$lambda$0(this)));
    @NotNull
    private final BooleanSetting glass = (BooleanSetting)this.register((Setting)new BooleanSetting("Стекло", "Стеклянные руки: отражение мира сквозь модель с искажением.", true).visible(() -> ShaderHands.glass$lambda$0(this)));
    @NotNull
    private final SliderSetting glassSaturation = (SliderSetting)this.register((Setting)new SliderSetting("Сатурация стекла", "Насыщенность отражения мира в руках.").range(0.0f, 3.0f).increment(0.05f).setValue(1.45f).visible(() -> ShaderHands.glassSaturation$lambda$0(this)));
    @NotNull
    private final SliderSetting glassWhite = (SliderSetting)this.register((Setting)new SliderSetting("Яркость стекла", "Подъём к белому: 0 = тёмное, 1 = белое.").range(0.0f, 1.0f).increment(0.02f).setValue(0.78f).visible(() -> ShaderHands.glassWhite$lambda$0(this)));
    @NotNull
    private final SliderSetting glassDistort = (SliderSetting)this.register((Setting)new SliderSetting("Искажение стекла", "Сила искажения отражения. 0 = ровное стекло.").range(0.0f, 0.05f).increment(0.001f).setValue(0.012f).visible(() -> ShaderHands.glassDistort$lambda$0(this)));
    @NotNull
    private final SliderSetting glassTint = (SliderSetting)this.register((Setting)new SliderSetting("Подкрас стекла", "Подкраска отражения цветом клиента.").range(0.0f, 1.0f).increment(0.02f).setValue(0.22f).visible(() -> ShaderHands.glassTint$lambda$0(this)));
    @NotNull
    private final SeparatorSetting glowSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Свечение"));
    @NotNull
    private final ModeSetting renderMode;
    @NotNull
    private final BooleanSetting glow;
    @NotNull
    private final SelectSetting glowMode;
    @NotNull
    private final SliderSetting radius;
    @NotNull
    private final SliderSetting glowStrength;
    @NotNull
    private final BooleanSetting blending;
    @NotNull
    private final SliderSetting outlineWidth;
    @NotNull
    private final SelectSetting glowColorMode;
    @NotNull
    private final BooleanSetting glowSecondColor;
    @NotNull
    private final ColorSetting glowColor;
    @NotNull
    private final ColorSetting glowColor2;
    @NotNull
    private final SeparatorSetting flameSeparator;
    @NotNull
    private final BooleanSetting flame;
    @NotNull
    private final BooleanSetting onlyItems;
    @NotNull
    private final SelectSetting flameColorMode;
    @NotNull
    private final ColorSetting flameColor;
    @NotNull
    private final SliderSetting flameStrength;
    @NotNull
    private final SliderSetting flameRiseSpeed;
    @NotNull
    private final SliderSetting flameWobble;
    @NotNull
    private final SliderSetting flameLength;
    @NotNull
    private final SliderSetting flameBrightness;
    @NotNull
    private final SeparatorSetting holoSeparator;
    @NotNull
    private final SelectSetting holoColorMode;
    @NotNull
    private final ColorSetting holoColor;
    @NotNull
    private final SliderSetting holoOpacity;
    @NotNull
    private final SliderSetting holoTransparency;
    @NotNull
    private final SliderSetting holoScanlines;
    @NotNull
    private final SliderSetting holoScanSpeed;
    @NotNull
    private final SliderSetting holoGlitch;
    @NotNull
    private final SliderSetting holoFlicker;
    @NotNull
    private final SeparatorSetting userSeparator;
    @NotNull
    private final SelectSetting userShader;
    @NotNull
    private final ButtonSetting userEditor;
    @NotNull
    private final SelectSetting userColorMode;
    @NotNull
    private final ColorSetting userColor;
    @NotNull
    private final ColorSetting userColor2;
    private long lastShaderScanMs;
    private static final int DEFAULT_COLOR = -10785543;
    @NotNull
    private static final String MODE_OLD = "Старый";
    @NotNull
    private static final String MODE_NEW = "Новый";
    @NotNull
    private static final String MODE_HOLO = "Голограмма";
    @NotNull
    private static final String MODE_USER = "Свой шейдер";
    @NotNull
    private static final String NO_SHADERS = "Нет шейдеров";
    private static final long SHADER_RESCAN_MS = 1200L;
    @NotNull
    private static final String GLOW_ONLY = "Свечение";
    @NotNull
    private static final String OUTLINE_ONLY = "Обводка";
    @NotNull
    private static final String GLOW_OUTLINE = "Свечение + обводка";
    @NotNull
    private static final String COLOR_ITEM = "Предмет";
    @NotNull
    private static final String COLOR_CUSTOM = "Свой";
    @NotNull
    private static final String COLOR_CLIENT = "Клиент";
    @NotNull
    private static final String COLOR_RAINBOW = "Радуга";
    @NotNull
    private static final float[] GLOW_THEME_PARAMS = new float[6];
    @JvmField
    @Nullable
    public static ShaderHands INSTANCE;

    public ShaderHands() {
        super("Shader Hands", "Шейдерные руки: стекло (чекбокс) + свечение — Старый (глоу/обводка), Новый (пламя) или свой GLSL.", Category.VISUALS);
        String[] stringArray = new String[]{MODE_OLD, MODE_NEW, MODE_HOLO, MODE_USER};
        this.renderMode = (ModeSetting)this.register((Setting)new ModeSetting("Режим свечения", "Старый — глоу/обводка вокруг рук, Новый — шейдерное пламя, Голограмма — светящаяся проекция руки, Свой шейдер — твой GLSL поверх силуэта рук. Стекло совместимо со Старым и Новым.", MODE_OLD, stringArray));
        this.glow = (BooleanSetting)this.register((Setting)new BooleanSetting(GLOW_ONLY, "Рисовать свечение/обводку вокруг рук (стиль GlowEsp).", false).visible(() -> ShaderHands.glow$lambda$0(this)));
        stringArray = new String[]{GLOW_ONLY, OUTLINE_ONLY, GLOW_OUTLINE};
        this.glowMode = (SelectSetting)this.register((Setting)new ModeSetting("Режим", "Что рисовать: только глоу, только обводку или вместе.", GLOW_ONLY, stringArray).visible(() -> ShaderHands.glowMode$lambda$0(this)));
        this.radius = (SliderSetting)this.register((Setting)new SliderSetting("Радиус свечения", "Радиус свечения вокруг рук.").range(1.0f, 12.0f).increment(1.0f).setValue(6.0f).visible(() -> ShaderHands.radius$lambda$0(this)));
        this.glowStrength = (SliderSetting)this.register((Setting)new SliderSetting("Сила свечения", "Яркость внешнего свечения.").range(0.1f, 3.0f).increment(0.05f).setValue(1.8f).visible(() -> ShaderHands.glowStrength$lambda$0(this)));
        this.blending = (BooleanSetting)this.register((Setting)new BooleanSetting("Смешивание", "Аддитивно подмешивать свечение к сцене (как свет/bloom).", false).visible(() -> ShaderHands.blending$lambda$0(this)));
        this.outlineWidth = (SliderSetting)this.register((Setting)new SliderSetting("Ширина контура", "Толщина обводки вокруг рук.").range(0.1f, 0.5f).increment(0.05f).setValue(0.3f).visible(() -> ShaderHands.outlineWidth$lambda$0(this)));
        stringArray = new String[]{COLOR_RAINBOW, COLOR_CLIENT, COLOR_CUSTOM};
        this.glowColorMode = (SelectSetting)this.register((Setting)new ModeSetting("Режим цвета", "Цвет свечения/обводки рук: радуга, цвет клиента или свой.", COLOR_CLIENT, stringArray).visible(() -> ShaderHands.glowColorMode$lambda$0(this)));
        this.glowSecondColor = (BooleanSetting)this.register((Setting)new BooleanSetting("Второй цвет", "Использовать второй свой цвет (градиент).", false).visible(() -> ShaderHands.glowSecondColor$lambda$0(this)));
        this.glowColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет", "Основной цвет свечения рук.", new Color(91, 108, 249, 255)).visible(() -> ShaderHands.glowColor$lambda$0(this)));
        this.glowColor2 = (ColorSetting)this.register((Setting)new ColorSetting("Цвет 2", "Второй цвет свечения рук.", new Color(255, 50, 150, 255)).visible(() -> ShaderHands.glowColor2$lambda$0(this)));
        this.flameSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Пламя").visible(() -> ShaderHands.flameSeparator$lambda$0(this)));
        this.flame = (BooleanSetting)this.register((Setting)new BooleanSetting("Пламя", "Включает шейдерное пламя вокруг рук.", true).visible(() -> ShaderHands.flame$lambda$0(this)));
        this.onlyItems = (BooleanSetting)this.register((Setting)new BooleanSetting("Только с предметом", "Рисует пламя только когда предмет в руке.", false).visible(() -> ShaderHands.onlyItems$lambda$0(this)));
        stringArray = new String[]{COLOR_ITEM, COLOR_CUSTOM, COLOR_CLIENT, COLOR_RAINBOW};
        this.flameColorMode = (SelectSetting)this.register((Setting)new ModeSetting("Режим цвета", "Режим цвета пламени рук.", COLOR_ITEM, stringArray).visible(() -> ShaderHands.flameColorMode$lambda$0(this)));
        this.flameColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет", "Свой цвет пламени рук.", new Color(255, 255, 255, 255)).visible(() -> ShaderHands.flameColor$lambda$0(this)));
        this.flameStrength = (SliderSetting)this.register((Setting)new SliderSetting("Сила", "Интенсивность пламени рук.").range(0.0f, 2.0f).increment(0.05f).setValue(0.85f).visible(() -> ShaderHands.flameStrength$lambda$0(this)));
        this.flameRiseSpeed = (SliderSetting)this.register((Setting)new SliderSetting("Скорость подъема", "Скорость подъема пламени рук.").range(0.0f, 2.0f).increment(0.05f).setValue(0.0f).visible(() -> ShaderHands.flameRiseSpeed$lambda$0(this)));
        this.flameWobble = (SliderSetting)this.register((Setting)new SliderSetting("Колебание", "Боковая турбулентность пламени рук.").range(0.0f, 2.0f).increment(0.05f).setValue(0.65f).visible(() -> ShaderHands.flameWobble$lambda$0(this)));
        this.flameLength = (SliderSetting)this.register((Setting)new SliderSetting("Длина", "Длина следа пламени рук.").range(0.1f, 2.5f).increment(0.05f).setValue(0.95f).visible(() -> ShaderHands.flameLength$lambda$0(this)));
        this.flameBrightness = (SliderSetting)this.register((Setting)new SliderSetting("Яркость", "Яркость пламени рук.").range(0.0f, 2.0f).increment(0.05f).setValue(0.9f).visible(() -> ShaderHands.flameBrightness$lambda$0(this)));
        this.holoSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting(MODE_HOLO).visible(() -> ShaderHands.holoSeparator$lambda$0(this)));
        stringArray = new String[]{COLOR_CLIENT, COLOR_CUSTOM, COLOR_RAINBOW};
        this.holoColorMode = (SelectSetting)this.register((Setting)new ModeSetting("Режим цвета", "Цвет проекции голограммы.", COLOR_CUSTOM, stringArray).visible(() -> ShaderHands.holoColorMode$lambda$0(this)));
        this.holoColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет", "Свой цвет голограммы.", new Color(255, 255, 255, 255)).visible(() -> ShaderHands.holoColor$lambda$0(this)));
        this.holoOpacity = (SliderSetting)this.register((Setting)new SliderSetting("Заливка цветом", "Насколько цвет голограммы перекрывает родной цвет предмета.").range(0.0f, 1.0f).increment(0.02f).setValue(1.0f).visible(() -> ShaderHands.holoOpacity$lambda$0(this)));
        this.holoTransparency = (SliderSetting)this.register((Setting)new SliderSetting("Прозрачность", "Насколько сквозь руку виден мир (0 — плотная, 1 — призрак).").range(0.0f, 1.0f).increment(0.02f).setValue(0.0f).visible(() -> ShaderHands.holoTransparency$lambda$0(this)));
        this.holoScanlines = (SliderSetting)this.register((Setting)new SliderSetting("Полосы", "Частота бегущих вверх полос развёртки (0 — выключить).").range(0.0f, 4.0f).increment(0.05f).setValue(4.0f).visible(() -> ShaderHands.holoScanlines$lambda$0(this)));
        this.holoScanSpeed = (SliderSetting)this.register((Setting)new SliderSetting("Скорость полос", "Скорость движения полос вверх.").range(0.0f, 4.0f).increment(0.05f).setValue(4.0f).visible(() -> ShaderHands.holoScanSpeed$lambda$0(this)));
        this.holoGlitch = (SliderSetting)this.register((Setting)new SliderSetting("Глитч", "Сдвиг строк + хроматическая аберрация (0 — выключить).").range(0.0f, 1.0f).increment(0.02f).setValue(0.52f).visible(() -> ShaderHands.holoGlitch$lambda$0(this)));
        this.holoFlicker = (SliderSetting)this.register((Setting)new SliderSetting("Мерцание", "Мерцание яркости проекции.").range(0.0f, 1.0f).increment(0.02f).setValue(0.2f).visible(() -> ShaderHands.holoFlicker$lambda$0(this)));
        this.userSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting(MODE_USER).visible(() -> ShaderHands.userSeparator$lambda$0(this)));
        this.userShader = (SelectSetting)this.register((Setting)new SelectSetting("Шейдер", "Какой свой шейдер рисовать на руках.").visible(() -> ShaderHands.userShader$lambda$0(this)));
        this.userEditor = (ButtonSetting)this.register((Setting)new ButtonSetting("Редактор шейдеров", "Написать свой GLSL-шейдер рук.").label("Открыть").visible(() -> ShaderHands.userEditor$lambda$0(this)));
        stringArray = new String[]{COLOR_CLIENT, COLOR_CUSTOM, COLOR_RAINBOW};
        this.userColorMode = (SelectSetting)this.register((Setting)new ModeSetting("Режим цвета", "Что вернут kimikoColor() и kimikoColor2() внутри шейдера.", COLOR_CLIENT, stringArray).visible(() -> ShaderHands.userColorMode$lambda$0(this)));
        this.userColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет", "Основной цвет для шейдера рук.", new Color(91, 108, 249, 255)).visible(() -> ShaderHands.userColor$lambda$0(this)));
        this.userColor2 = (ColorSetting)this.register((Setting)new ColorSetting("Цвет 2", "Второй цвет для шейдера рук.", new Color(255, 50, 150, 255)).visible(() -> ShaderHands.userColor2$lambda$0(this)));
        INSTANCE = this;
        this.userEditor.onClick(() -> ShaderHands._init_$lambda$0(this));
        this.refreshUserShaderOptions();
    }

    private final boolean userShaderVisible() {
        if (!this.isUserMode()) {
            return false;
        }
        long now = System.currentTimeMillis();
        if (now - this.lastShaderScanMs >= 1200L) {
            this.refreshUserShaderOptions();
        }
        return true;
    }

    public final void refreshUserShaderOptions() {
        this.lastShaderScanMs = System.currentTimeMillis();
        List<String> names = HandShaders.list();
        this.userShader.options(names.isEmpty() ? List.of(NO_SHADERS) : names);
    }

    @NotNull
    public final String userShaderName() {
        if (!this.isUserMode()) {
            return "";
        }
        String selected = this.userShader.getSelected();
        CharSequence charSequence = selected;
        return charSequence == null || charSequence.length() == 0 || Intrinsics.areEqual((Object)selected, (Object)NO_SHADERS) ? "" : selected;
    }

    public final void applyUserShader(@NotNull String name) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        this.refreshUserShaderOptions();
        this.renderMode.setSelected(MODE_USER);
        this.userShader.setSelected(name);
        HandShaderManager.invalidate();
        if (!this.isEnabled()) {
            this.enable();
        }
    }

    public final int userShaderColor() {
        if (this.userColorMode.is(COLOR_CUSTOM)) {
            return ColorEngine.withAlpha(this.userColor.getColorOpaque(), 255);
        }
        if (this.userColorMode.is(COLOR_RAINBOW)) {
            float hue = (float)(System.currentTimeMillis() % 4000L) / 4000.0f;
            return 0xFF000000 | Color.HSBtoRGB(hue, 0.72f, 1.0f) & 0xFFFFFF;
        }
        return ShaderHands.Companion.baseColor();
    }

    public final int userShaderColor2() {
        if (this.userColorMode.is(COLOR_CUSTOM)) {
            return ColorEngine.withAlpha(this.userColor2.getColorOpaque(), 255);
        }
        if (this.userColorMode.is(COLOR_RAINBOW)) {
            float hue = (float)((System.currentTimeMillis() + 1300L) % 4000L) / 4000.0f;
            return 0xFF000000 | Color.HSBtoRGB(hue, 0.72f, 1.0f) & 0xFFFFFF;
        }
        InterfaceModule iface = InterfaceModule.Companion.getInstance();
        if (iface != null && iface.usesSecondClientColor()) {
            return iface.clientSecondaryColorOpaque();
        }
        return ShaderHands.Companion.baseColor();
    }

    private final void openShaderEditor() {
        HandShaderEditorScreen editor = new HandShaderEditorScreen(UI.INSTANCE);
        if (this.mc.currentScreen == UI.INSTANCE) {
            UI.Companion.closeInto(editor);
        } else {
            this.mc.setScreen((Screen)editor);
        }
    }

    private final boolean isGlassOn() {
        return this.glass.getValue() && this.isGlassCompatible();
    }

    private final boolean isGlassCompatible() {
        return !this.isHoloMode() && !this.isUserMode();
    }

    private final boolean isUserMode() {
        return this.renderMode.is(MODE_USER);
    }

    private final boolean isOldMode() {
        return this.renderMode.is(MODE_OLD);
    }

    private final boolean isNewMode() {
        return this.renderMode.is(MODE_NEW);
    }

    private final boolean isHoloMode() {
        return this.renderMode.is(MODE_HOLO);
    }

    private final boolean isFlameSettingsVisible() {
        return this.isNewMode() && this.flame.getValue();
    }

    @Override
    protected void onEnable() {
        this.syncFlameRenderer();
        this.syncHologram();
    }

    @Override
    protected void onDisable() {
        HandsFlameRenderer.setFlameEnabled(false);
        HandsFlameRenderer.shutdown();
        ShaderHandsRenderer.clear();
        HologramHandsConfig.enabled = false;
        HologramHandsRenderer.shutdown();
    }

    @EventHandler
    private final void onTick(TickEvent event) {
        if (event.isPre()) {
            this.syncFlameRenderer();
            this.syncHologram();
        }
    }

    private final void syncFlameRenderer() {
        if (!this.isNewMode()) {
            HandsFlameRenderer.setFlameEnabled(false);
            return;
        }
        HandsFlameRenderer.setFlameEnabled(this.flame.getValue());
        HandsFlameRenderer.configure(this.flameStrength.getFloat(), this.flameRiseSpeed.getFloat(), this.flameWobble.getFloat(), this.flameLength.getFloat(), this.flameBrightness.getFloat(), this.flameColorMode.is(COLOR_ITEM) ? 0 : 1, this.resolveFlameColor(), this.onlyItems.getValue(), this.flameColorMode.is(COLOR_CLIENT));
    }

    private final int resolveFlameColor() {
        if (this.flameColorMode.is(COLOR_CUSTOM)) {
            return ColorEngine.withAlpha(this.flameColor.getColorOpaque(), 255);
        }
        if (this.flameColorMode.is(COLOR_CLIENT)) {
            return ShaderHands.Companion.baseColor();
        }
        if (this.flameColorMode.is(COLOR_RAINBOW)) {
            float hue = (float)(System.currentTimeMillis() % 4000L) / 4000.0f;
            return 0xFF000000 | Color.HSBtoRGB(hue, 0.72f, 1.0f) & 0xFFFFFF;
        }
        return ColorEngine.withAlpha(this.flameColor.getColorOpaque(), 255);
    }

    private final void syncHologram() {
        if (!this.isHoloMode()) {
            HologramHandsConfig.enabled = false;
            return;
        }
        HologramHandsConfig.configure(true, this.resolveHoloColor(), this.holoOpacity.getFloat(), this.holoTransparency.getFloat(), this.holoScanlines.getFloat(), this.holoScanSpeed.getFloat(), this.holoGlitch.getFloat(), this.holoFlicker.getFloat(), 0.0f);
    }

    private final int resolveHoloColor() {
        if (this.holoColorMode.is(COLOR_RAINBOW)) {
            float hue = (float)(System.currentTimeMillis() % 3000L) / 3000.0f;
            return 0xFF000000 | Color.HSBtoRGB(hue, 0.7f, 1.0f) & 0xFFFFFF;
        }
        if (this.holoColorMode.is(COLOR_CLIENT)) {
            return ShaderHands.Companion.baseColor();
        }
        return this.holoColor.getColorOpaque() | 0xFF000000;
    }

    private final int[] glowGradientColors() {
        int[] nArray = new int[]{this.glowColorAt(0), this.glowColorAt(90), this.glowColorAt(180), this.glowColorAt(270)};
        return nArray;
    }

    private final float[] glowThemeParams() {
        if (!this.glowColorMode.is(COLOR_CLIENT)) {
            return null;
        }
        InterfaceModule interfaceModule = InterfaceModule.Companion.getInstance();
        if (interfaceModule == null) {
            return null;
        }
        InterfaceModule iface = interfaceModule;
        ShaderHands.GLOW_THEME_PARAMS[0] = ShaderHands.Companion.glowFadeFactor(0);
        ShaderHands.GLOW_THEME_PARAMS[1] = ShaderHands.Companion.glowFadeFactor(90);
        ShaderHands.GLOW_THEME_PARAMS[2] = ShaderHands.Companion.glowFadeFactor(180);
        ShaderHands.GLOW_THEME_PARAMS[3] = ShaderHands.Companion.glowFadeFactor(270);
        ShaderHands.GLOW_THEME_PARAMS[4] = 1.0f;
        ShaderHands.GLOW_THEME_PARAMS[5] = iface.usesSecondClientColor() ? 1.0f : 0.0f;
        return GLOW_THEME_PARAMS;
    }

    private final int glowColorAt(int index) {
        if (this.glowColorMode.is(COLOR_RAINBOW)) {
            return ShaderHands.Companion.glowRainbow(index);
        }
        int first = 0;
        int second = 0;
        if (this.glowColorMode.is(COLOR_CUSTOM)) {
            first = ColorEngine.withAlpha(this.glowColor.getColorOpaque(), 255);
            second = this.glowSecondColor.getValue() ? ColorEngine.withAlpha(this.glowColor2.getColorOpaque(), 255) : first;
        } else {
            InterfaceModule iface = InterfaceModule.Companion.getInstance();
            if (iface != null) {
                first = iface.clientPrimaryColorOpaque();
                second = iface.usesSecondClientColor() ? iface.clientSecondaryColorOpaque() : first;
            } else {
                first = -10785543;
                second = -10785543;
            }
        }
        if (first == second) {
            return first | 0xFF000000;
        }
        return ShaderHands.Companion.glowFade(index, first, second) | 0xFF000000;
    }

    private static final Boolean glassSeparator$lambda$0(ShaderHands this$0) {
        return this$0.isGlassCompatible();
    }

    private static final Boolean glass$lambda$0(ShaderHands this$0) {
        return this$0.isGlassCompatible();
    }

    private static final Boolean glassSaturation$lambda$0(ShaderHands this$0) {
        return this$0.isGlassOn();
    }

    private static final Boolean glassWhite$lambda$0(ShaderHands this$0) {
        return this$0.isGlassOn();
    }

    private static final Boolean glassDistort$lambda$0(ShaderHands this$0) {
        return this$0.isGlassOn();
    }

    private static final Boolean glassTint$lambda$0(ShaderHands this$0) {
        return this$0.isGlassOn();
    }

    private static final Boolean glow$lambda$0(ShaderHands this$0) {
        return this$0.isOldMode();
    }

    private static final Boolean glowMode$lambda$0(ShaderHands this$0) {
        return this$0.isOldMode() && this$0.glow.getValue();
    }

    private static final Boolean radius$lambda$0(ShaderHands this$0) {
        return this$0.isOldMode() && this$0.glow.getValue();
    }

    private static final Boolean glowStrength$lambda$0(ShaderHands this$0) {
        return this$0.isOldMode() && this$0.glow.getValue();
    }

    private static final Boolean blending$lambda$0(ShaderHands this$0) {
        return this$0.isOldMode() && this$0.glow.getValue();
    }

    private static final Boolean outlineWidth$lambda$0(ShaderHands this$0) {
        return this$0.isOldMode() && this$0.glow.getValue() && !this$0.glowMode.is(GLOW_ONLY);
    }

    private static final Boolean glowColorMode$lambda$0(ShaderHands this$0) {
        return this$0.isOldMode() && this$0.glow.getValue();
    }

    private static final Boolean glowSecondColor$lambda$0(ShaderHands this$0) {
        return this$0.isOldMode() && this$0.glow.getValue() && this$0.glowColorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean glowColor$lambda$0(ShaderHands this$0) {
        return this$0.isOldMode() && this$0.glow.getValue() && this$0.glowColorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean glowColor2$lambda$0(ShaderHands this$0) {
        return this$0.isOldMode() && this$0.glow.getValue() && this$0.glowColorMode.is(COLOR_CUSTOM) && this$0.glowSecondColor.getValue();
    }

    private static final Boolean flameSeparator$lambda$0(ShaderHands this$0) {
        return this$0.isNewMode();
    }

    private static final Boolean flame$lambda$0(ShaderHands this$0) {
        return this$0.isNewMode();
    }

    private static final Boolean onlyItems$lambda$0(ShaderHands this$0) {
        return this$0.isFlameSettingsVisible();
    }

    private static final Boolean flameColorMode$lambda$0(ShaderHands this$0) {
        return this$0.isFlameSettingsVisible();
    }

    private static final Boolean flameColor$lambda$0(ShaderHands this$0) {
        return this$0.isFlameSettingsVisible() && this$0.flameColorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean flameStrength$lambda$0(ShaderHands this$0) {
        return this$0.isFlameSettingsVisible();
    }

    private static final Boolean flameRiseSpeed$lambda$0(ShaderHands this$0) {
        return this$0.isFlameSettingsVisible();
    }

    private static final Boolean flameWobble$lambda$0(ShaderHands this$0) {
        return this$0.isFlameSettingsVisible();
    }

    private static final Boolean flameLength$lambda$0(ShaderHands this$0) {
        return this$0.isFlameSettingsVisible();
    }

    private static final Boolean flameBrightness$lambda$0(ShaderHands this$0) {
        return this$0.isFlameSettingsVisible();
    }

    private static final Boolean holoSeparator$lambda$0(ShaderHands this$0) {
        return this$0.isHoloMode();
    }

    private static final Boolean holoColorMode$lambda$0(ShaderHands this$0) {
        return this$0.isHoloMode();
    }

    private static final Boolean holoColor$lambda$0(ShaderHands this$0) {
        return this$0.isHoloMode() && this$0.holoColorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean holoOpacity$lambda$0(ShaderHands this$0) {
        return this$0.isHoloMode();
    }

    private static final Boolean holoTransparency$lambda$0(ShaderHands this$0) {
        return this$0.isHoloMode();
    }

    private static final Boolean holoScanlines$lambda$0(ShaderHands this$0) {
        return this$0.isHoloMode();
    }

    private static final Boolean holoScanSpeed$lambda$0(ShaderHands this$0) {
        return this$0.isHoloMode() && this$0.holoScanlines.getFloat() > 0.001f;
    }

    private static final Boolean holoGlitch$lambda$0(ShaderHands this$0) {
        return this$0.isHoloMode();
    }

    private static final Boolean holoFlicker$lambda$0(ShaderHands this$0) {
        return this$0.isHoloMode();
    }

    private static final Boolean userSeparator$lambda$0(ShaderHands this$0) {
        return this$0.isUserMode();
    }

    private static final Boolean userShader$lambda$0(ShaderHands this$0) {
        return this$0.userShaderVisible();
    }

    private static final Boolean userEditor$lambda$0(ShaderHands this$0) {
        return this$0.isUserMode();
    }

    private static final Boolean userColorMode$lambda$0(ShaderHands this$0) {
        return this$0.isUserMode();
    }

    private static final Boolean userColor$lambda$0(ShaderHands this$0) {
        return this$0.isUserMode() && this$0.userColorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean userColor2$lambda$0(ShaderHands this$0) {
        return this$0.isUserMode() && this$0.userColorMode.is(COLOR_CUSTOM);
    }

    private static final void _init_$lambda$0(ShaderHands this$0) {
        this$0.openShaderEditor();
    }

    @JvmStatic
    @Nullable
    public static final ShaderHands getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final boolean isActive() {
        return Companion.isActive();
    }

    @JvmStatic
    public static final boolean isOldModeActive() {
        return Companion.isOldModeActive();
    }

    @JvmStatic
    public static final boolean isUserModeActive() {
        return Companion.isUserModeActive();
    }

    @JvmStatic
    public static final boolean isCaptureModeActive() {
        return Companion.isCaptureModeActive();
    }

    @JvmStatic
    public static final boolean wantsSceneBlur() {
        return Companion.wantsSceneBlur();
    }

    @JvmStatic
    public static final boolean isNewModeActive() {
        return Companion.isNewModeActive();
    }

    @JvmStatic
    public static final void composite() {
        Companion.composite();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u000b\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000b\u0010\nJ\u0013\u0010\f\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\nJ\u0013\u0010\r\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\nJ\u0013\u0010\u000e\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000e\u0010\nJ\u0013\u0010\u000f\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000f\u0010\nJ\u0013\u0010\u0011\u001a\u00020\u0010H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0011\u0010\u0003J\u000f\u0010\u0013\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ'\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010#R\u0014\u0010%\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010#R\u0014\u0010&\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010#R\u0014\u0010'\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010#R\u0014\u0010)\u001a\u00020(8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010#R\u0014\u0010,\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010#R\u0014\u0010-\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010#R\u0014\u0010.\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010#R\u0014\u0010/\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b/\u0010#R\u0014\u00100\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b0\u0010#R\u0014\u00101\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b1\u0010#R\u0014\u00103\u001a\u0002028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b3\u00104R\u001d\u00106\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b5\u00a2\u0006\u0006\n\u0004\b6\u00107\u00a8\u00068"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ShaderHands.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/ShaderHands;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/ShaderHands;", "", "isActive", "()Z", "isOldModeActive", "isUserModeActive", "isCaptureModeActive", "wantsSceneBlur", "isNewModeActive", "", "composite", "", "baseColor", "()I", "index", "", "glowFadeFactor", "(I)F", "glowRainbow", "(I)I", "first", "second", "glowFade", "(III)I", "DEFAULT_COLOR", "I", "", "MODE_OLD", "Ljava/lang/String;", "MODE_NEW", "MODE_HOLO", "MODE_USER", "NO_SHADERS", "", "SHADER_RESCAN_MS", "J", "GLOW_ONLY", "OUTLINE_ONLY", "GLOW_OUTLINE", "COLOR_ITEM", "COLOR_CUSTOM", "COLOR_CLIENT", "COLOR_RAINBOW", "", "GLOW_THEME_PARAMS", "[F", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/ShaderHands;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final ShaderHands getInstance() {
            ShaderHands module = ModuleManager.Companion.get().get(ShaderHands.class);
            ShaderHands shaderHands = module;
            if (shaderHands == null) {
                shaderHands = INSTANCE;
            }
            return shaderHands;
        }

        @JvmStatic
        public final boolean isActive() {
            ShaderHands module = this.getInstance();
            return module != null && module.isEnabled();
        }

        @JvmStatic
        public final boolean isOldModeActive() {
            ShaderHands module = this.getInstance();
            return module != null && module.isEnabled() && (module.isGlassOn() || module.isOldMode() && module.glow.getValue());
        }

        @JvmStatic
        public final boolean isUserModeActive() {
            ShaderHands module = this.getInstance();
            return module != null && module.isEnabled() && module.isUserMode() && !(((CharSequence)module.userShaderName()).length() == 0);
        }

        @JvmStatic
        public final boolean isCaptureModeActive() {
            return this.isOldModeActive() || this.isUserModeActive();
        }

        @JvmStatic
        public final boolean wantsSceneBlur() {
            ShaderHands module = this.getInstance();
            return module != null && module.isEnabled() && (module.isGlassOn() || this.isUserModeActive());
        }

        @JvmStatic
        public final boolean isNewModeActive() {
            ShaderHands module = this.getInstance();
            return module != null && module.isEnabled() && module.isNewMode() && module.flame.getValue();
        }

        @JvmStatic
        public final void composite() {
            boolean glowOn;
            ShaderHands module = this.getInstance();
            if (module == null || !module.isEnabled()) {
                return;
            }
            if (module.isUserMode()) {
                RenderPipeline pipeline;
                RenderPipeline renderPipeline = pipeline = ((CharSequence)module.userShaderName()).length() == 0 ? null : HandShaderManager.activePipeline();
                if (pipeline == null) {
                    ShaderHandsRenderer.compositePlain();
                } else {
                    ShaderHandsRenderer.compositeUserShader(pipeline, module.userShaderColor(), module.userShaderColor2());
                }
                return;
            }
            boolean glassOn = module.isGlassOn();
            boolean bl = glowOn = module.isOldMode() && module.glow.getValue();
            if (!glassOn && !glowOn) {
                return;
            }
            int mode = module.glowMode.is(ShaderHands.OUTLINE_ONLY) ? 1 : (module.glowMode.is(ShaderHands.GLOW_OUTLINE) ? 2 : 0);
            ShaderHandsRenderer.setGlowThemeParams(module.glowThemeParams());
            ShaderHandsRenderer.composite(this.baseColor(), module.glowGradientColors(), glassOn, glowOn, mode, module.radius.getFloat(), module.outlineWidth.getFloat(), module.glowStrength.getFloat(), module.blending.getValue(), module.glassSaturation.getFloat(), module.glassWhite.getFloat(), module.glassDistort.getFloat(), module.glassTint.getFloat());
        }

        private final int baseColor() {
            InterfaceModule iface;
            InterfaceModule interfaceModule = iface = InterfaceModule.Companion.getInstance();
            return interfaceModule != null ? interfaceModule.clientPrimaryColorOpaque() : -10785543;
        }

        private final float glowFadeFactor(int index) {
            int angle = (int)((System.currentTimeMillis() / 8L + (long)index) % 360L);
            angle = angle >= 180 ? 360 - angle : angle;
            return (float)angle / 180.0f;
        }

        private final int glowRainbow(int index) {
            int angle = (int)((System.currentTimeMillis() / 8L + (long)index) % 360L);
            int rgb = ColorEngine.rainbow(angle, 1.0f, 1.0f);
            return 0xFF000000 | rgb & 0xFFFFFF;
        }

        private final int glowFade(int index, int first, int second) {
            int angle = (int)((System.currentTimeMillis() / 8L + (long)index) % 360L);
            angle = angle >= 180 ? 360 - angle : angle;
            return ColorEngine.lerpColor(first, second, (float)angle / 180.0f);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

