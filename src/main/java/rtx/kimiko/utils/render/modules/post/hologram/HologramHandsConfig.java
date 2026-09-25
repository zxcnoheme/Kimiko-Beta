/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.modules.post.hologram;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J[\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0007b\u0002\b\u0011\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0014\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0015R\u001b\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0014\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0016R\u001b\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0014\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0017R\u001b\u0010\n\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0014\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0017R\u001b\u0010\u000b\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0014\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\u0017R\u001b\u0010\f\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0014\u00a2\u0006\u0006\n\u0004\b\f\u0010\u0017R\u001b\u0010\r\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0014\u00a2\u0006\u0006\n\u0004\b\r\u0010\u0017R\u001b\u0010\u000e\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0014\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u0017R\u001b\u0010\u000f\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0014\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0017\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/utils/render/modules/post/hologram/HologramHandsConfig;", "", "<init>", "()V", "", "enabled", "", "color", "", "opacity", "transparency", "scanlines", "scanSpeed", "glitch", "flicker", "glow", "", "Lkotlin/jvm/JvmStatic;", "configure", "(ZIFFFFFFF)V", "Lkotlin/jvm/JvmField;", "Z", "I", "F", "rtx.kimiko:kimiko"})
public final class HologramHandsConfig {
    @NotNull
    public static final HologramHandsConfig INSTANCE = new HologramHandsConfig();
    @JvmField
    public static boolean enabled;
    @JvmField
    public static int color;
    @JvmField
    public static float opacity;
    @JvmField
    public static float transparency;
    @JvmField
    public static float scanlines;
    @JvmField
    public static float scanSpeed;
    @JvmField
    public static float glitch;
    @JvmField
    public static float flicker;
    @JvmField
    public static float glow;

    private HologramHandsConfig() {
    }

    @JvmStatic
    public static final void configure(boolean enabled, int color, float opacity, float transparency, float scanlines, float scanSpeed, float glitch, float flicker, float glow) {
        HologramHandsConfig.enabled = enabled;
        HologramHandsConfig.color = color;
        HologramHandsConfig.opacity = opacity;
        HologramHandsConfig.transparency = transparency;
        HologramHandsConfig.scanlines = scanlines;
        HologramHandsConfig.scanSpeed = scanSpeed;
        HologramHandsConfig.glitch = glitch;
        HologramHandsConfig.flicker = flicker;
        HologramHandsConfig.glow = glow;
    }

    static {
        color = -11480321;
        opacity = 0.85f;
        transparency = 0.35f;
        scanlines = 1.1f;
        scanSpeed = 1.0f;
        glitch = 0.45f;
        flicker = 0.4f;
        glow = 0.9f;
    }
}

