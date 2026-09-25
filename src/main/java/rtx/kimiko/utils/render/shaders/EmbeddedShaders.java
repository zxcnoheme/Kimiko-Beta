/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.shaders;

import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.shaders.RootShaders;
import rtx.kimiko.utils.render.shaders.core.CoreShaders;
import rtx.kimiko.utils.render.shaders.effects.frag_effect_scan.FragEffectScanShaders;
import rtx.kimiko.utils.render.shaders.effects.hands_flame.HandsFlameShaders;
import rtx.kimiko.utils.render.shaders.effects.hands_hologram.HandsHologramShaders;
import rtx.kimiko.utils.render.shaders.effects.lyrics_text.LyricsTextShaders;
import rtx.kimiko.utils.render.shaders.include.IncludeShaders;
import rtx.kimiko.utils.render.shaders.post.ambiencefog.AmbiencefogShaders;
import rtx.kimiko.utils.render.shaders.post.customsky.CustomskyShaders;
import rtx.kimiko.utils.render.shaders.post.explosionwave.ExplosionwaveShaders;
import rtx.kimiko.utils.render.shaders.post.fogblur.FogblurShaders;
import rtx.kimiko.utils.render.shaders.post.glassvapor.GlassvaporShaders;
import rtx.kimiko.utils.render.shaders.post.glowesp.GlowespShaders;
import rtx.kimiko.utils.render.shaders.post.groundreflect.GroundreflectShaders;
import rtx.kimiko.utils.render.shaders.post.guilayerblur.GuilayerblurShaders;
import rtx.kimiko.utils.render.shaders.post.guimotionblur.GuimotionblurShaders;
import rtx.kimiko.utils.render.shaders.post.hitbubbles.HitbubblesShaders;
import rtx.kimiko.utils.render.shaders.post.hpfocus.HpfocusShaders;
import rtx.kimiko.utils.render.shaders.post.hudlayer.HudlayerShaders;
import rtx.kimiko.utils.render.shaders.post.itemoutline.ItemoutlineShaders;
import rtx.kimiko.utils.render.shaders.post.jumpdistort.JumpdistortShaders;
import rtx.kimiko.utils.render.shaders.post.jumpsouls.JumpsoulsShaders;
import rtx.kimiko.utils.render.shaders.post.killdistortion.KilldistortionShaders;
import rtx.kimiko.utils.render.shaders.post.liquidpanel.LiquidpanelShaders;
import rtx.kimiko.utils.render.shaders.post.saturation.SaturationShaders;
import rtx.kimiko.utils.render.shaders.post.scarglass.ScarglassShaders;
import rtx.kimiko.utils.render.shaders.post.shaderhands.ShaderhandsShaders;
import rtx.kimiko.utils.render.shaders.post.targetcircle.TargetcircleShaders;
import rtx.kimiko.utils.render.shaders.post.themeshock.ThemeshockShaders;
import rtx.kimiko.utils.render.shaders.post.trailglass.TrailglassShaders;
import rtx.kimiko.utils.render.shaders.post.wasted.WastedShaders;
import rtx.kimiko.utils.render.shaders.ui.batched_blur.BatchedBlurShaders;
import rtx.kimiko.utils.render.shaders.ui.glass.GlassShaders;
import rtx.kimiko.utils.render.shaders.ui.glow.GlowShaders;
import rtx.kimiko.utils.render.shaders.ui.kawase.KawaseShaders;
import rtx.kimiko.utils.render.shaders.ui.radialglass.RadialglassShaders;
import rtx.kimiko.utils.render.shaders.ui.sectormask.SectormaskShaders;
import rtx.kimiko.utils.render.shaders.ui.shape.ShapeShaders;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R%\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2={"Lrtx/kimiko/utils/render/shaders/EmbeddedShaders;", "", "<init>", "()V", "", "", "Lkotlin/jvm/JvmField;", "SOURCES", "Ljava/util/Map;", "rtx.kimiko:kimiko"})
public final class EmbeddedShaders {
    @NotNull
    public static final EmbeddedShaders INSTANCE = new EmbeddedShaders();
    @JvmField
    @NotNull
    public static final Map<String, String> SOURCES = new HashMap();

    private EmbeddedShaders() {
    }

    static {
        RootShaders.register(SOURCES);
        CoreShaders.register(SOURCES);
        FragEffectScanShaders.register(SOURCES);
        HandsFlameShaders.register(SOURCES);
        HandsHologramShaders.register(SOURCES);
        LyricsTextShaders.register(SOURCES);
        IncludeShaders.register(SOURCES);
        AmbiencefogShaders.register(SOURCES);
        CustomskyShaders.register(SOURCES);
        ExplosionwaveShaders.register(SOURCES);
        FogblurShaders.register(SOURCES);
        GlassvaporShaders.register(SOURCES);
        GlowespShaders.register(SOURCES);
        GroundreflectShaders.register(SOURCES);
        GuilayerblurShaders.register(SOURCES);
        GuimotionblurShaders.register(SOURCES);
        HitbubblesShaders.register(SOURCES);
        HpfocusShaders.register(SOURCES);
        HudlayerShaders.register(SOURCES);
        ItemoutlineShaders.register(SOURCES);
        JumpdistortShaders.register(SOURCES);
        JumpsoulsShaders.register(SOURCES);
        KilldistortionShaders.register(SOURCES);
        LiquidpanelShaders.register(SOURCES);
        SaturationShaders.register(SOURCES);
        ScarglassShaders.register(SOURCES);
        ShaderhandsShaders.register(SOURCES);
        TargetcircleShaders.register(SOURCES);
        ThemeshockShaders.register(SOURCES);
        TrailglassShaders.register(SOURCES);
        WastedShaders.register(SOURCES);
        BatchedBlurShaders.register(SOURCES);
        GlassShaders.register(SOURCES);
        GlowShaders.register(SOURCES);
        KawaseShaders.register(SOURCES);
        RadialglassShaders.register(SOURCES);
        SectormaskShaders.register(SOURCES);
        ShapeShaders.register(SOURCES);
    }
}

