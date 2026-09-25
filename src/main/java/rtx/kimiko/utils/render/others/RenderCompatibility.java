/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.opengl.GL11
 */
package rtx.kimiko.utils.render.others;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0015\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J\u0013\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\r\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u0007J\u0013\u0010\u000e\u001a\u00020\nH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000e\u0010\fJ\u001b\u0010\u0010\u001a\u0004\u0018\u00010\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0007J\u0017\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0019\u0010\u0017\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0019\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001d\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001aR\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001cR\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001cR\u0016\u0010 \u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010!R\u0014\u0010#\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b#\u0010!R\u0018\u0010$\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010\u001cR\u0016\u0010%\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u0010!R\u0014\u0010&\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010!R\u0014\u0010'\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010!\u00a8\u0006("}, d2={"Lrtx/kimiko/utils/render/others/RenderCompatibility;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "useSafeWorldEffects", "()Z", "", "primeFromCurrentContext", "", "getSafeWorldEffectsReason", "()Ljava/lang/String;", "shouldDisableFragEffectScanShader", "getDisableFragEffectScanShaderReason", "value", "readManualOverride", "(Ljava/lang/String;)Ljava/lang/Boolean;", "detectFragEffectScanShaderBlocklist", "", "token", "readGlString", "(I)Ljava/lang/String;", "normalize", "(Ljava/lang/String;)Ljava/lang/String;", "safeWorldOverrideResolved", "Z", "safeWorldOverride", "Ljava/lang/Boolean;", "fragScanOverrideResolved", "fragScanOverride", "safeWorldEffects", "safeWorldEffectsReason", "Ljava/lang/String;", "SAFE_WORLD_EFFECTS_PROPERTY", "SAFE_WORLD_EFFECTS_ENV", "disableFragEffectScanShader", "disableFragEffectScanShaderReason", "DISABLE_FRAG_EFFECT_SCAN_PROPERTY", "DISABLE_FRAG_EFFECT_SCAN_ENV", "rtx.kimiko:kimiko"})
public final class RenderCompatibility {
    @NotNull
    public static final RenderCompatibility INSTANCE = new RenderCompatibility();
    private static boolean safeWorldOverrideResolved;
    @Nullable
    private static Boolean safeWorldOverride;
    private static boolean fragScanOverrideResolved;
    @Nullable
    private static Boolean fragScanOverride;
    @Nullable
    private static Boolean safeWorldEffects;
    @NotNull
    private static String safeWorldEffectsReason;
    @NotNull
    private static final String SAFE_WORLD_EFFECTS_PROPERTY = "kimiko.safeWorldEffects";
    @NotNull
    private static final String SAFE_WORLD_EFFECTS_ENV = "KIMIKO_SAFE_WORLD_EFFECTS";
    @Nullable
    private static Boolean disableFragEffectScanShader;
    @NotNull
    private static String disableFragEffectScanShaderReason;
    @NotNull
    private static final String DISABLE_FRAG_EFFECT_SCAN_PROPERTY = "kimiko.disableFragEffectScanShader";
    @NotNull
    private static final String DISABLE_FRAG_EFFECT_SCAN_ENV = "KIMIKO_DISABLE_FRAG_EFFECT_SCAN_SHADER";

    private RenderCompatibility() {
    }

    @JvmStatic
    public static final boolean useSafeWorldEffects() {
        Boolean manualOverride;
        if (!safeWorldOverrideResolved) {
            Boolean resolved = INSTANCE.readManualOverride(System.getProperty(SAFE_WORLD_EFFECTS_PROPERTY));
            if (resolved == null) {
                resolved = INSTANCE.readManualOverride(System.getenv(SAFE_WORLD_EFFECTS_ENV));
            }
            safeWorldOverride = resolved;
            safeWorldOverrideResolved = true;
        }
        if ((manualOverride = safeWorldOverride) != null) {
            safeWorldEffects = manualOverride;
            safeWorldEffectsReason = "manual override";
            return manualOverride;
        }
        if (safeWorldEffects == null) {
            safeWorldEffects = false;
            safeWorldEffectsReason = "default-path";
        }
        Boolean bl = safeWorldEffects;
        Intrinsics.checkNotNull((Object)bl);
        return bl;
    }

    @JvmStatic
    public static final void primeFromCurrentContext() {
        RenderCompatibility.useSafeWorldEffects();
        RenderCompatibility.shouldDisableFragEffectScanShader();
    }

    @JvmStatic
    @NotNull
    public static final String getSafeWorldEffectsReason() {
        return safeWorldEffectsReason;
    }

    @JvmStatic
    public static final boolean shouldDisableFragEffectScanShader() {
        Boolean manualOverride;
        if (!fragScanOverrideResolved) {
            Boolean resolved = INSTANCE.readManualOverride(System.getProperty(DISABLE_FRAG_EFFECT_SCAN_PROPERTY));
            if (resolved == null) {
                resolved = INSTANCE.readManualOverride(System.getenv(DISABLE_FRAG_EFFECT_SCAN_ENV));
            }
            fragScanOverride = resolved;
            fragScanOverrideResolved = true;
        }
        if ((manualOverride = fragScanOverride) != null) {
            disableFragEffectScanShader = manualOverride;
            disableFragEffectScanShaderReason = "manual override";
            return manualOverride;
        }
        if (disableFragEffectScanShader == null) {
            disableFragEffectScanShader = INSTANCE.detectFragEffectScanShaderBlocklist();
        }
        Boolean bl = disableFragEffectScanShader;
        Intrinsics.checkNotNull((Object)bl);
        return bl;
    }

    @JvmStatic
    @NotNull
    public static final String getDisableFragEffectScanShaderReason() {
        return disableFragEffectScanShaderReason;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final Boolean readManualOverride(String value) {
        if (value == null) {
            return null;
        }
        String string = ((Object)StringsKt.trim((CharSequence)value)).toString();
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"getDefault(...)");
        String string2 = string.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
        String normalized = string2;
        switch (normalized.hashCode()) {
            case 49: {
                if (normalized.equals("1")) return true;
                break;
            }
            case 119527: {
                if (normalized.equals("yes")) return true;
                break;
            }
            case 3569038: {
                if (normalized.equals("true")) return true;
                break;
            }
            case 3551: {
                if (!normalized.equals("on")) break;
                return true;
            }
        }
        switch (normalized.hashCode()) {
            case 48: {
                if (normalized.equals("0")) return false;
                return null;
            }
            case 3521: {
                if (normalized.equals("no")) return false;
                return null;
            }
            case 97196323: {
                if (normalized.equals("false")) return false;
                return null;
            }
            case 109935: {
                if (!normalized.equals("off")) return null;
                return false;
            }
        }
        return null;
    }

    private final boolean detectFragEffectScanShaderBlocklist() {
        if (!RenderSystem.isOnRenderThread()) {
            disableFragEffectScanShaderReason = "render thread unavailable";
            return false;
        }
        String vendor = this.readGlString(7936);
        String renderer = this.readGlString(7937);
        String version = this.readGlString(7938);
        disableFragEffectScanShaderReason = "safe depth-copy shader path enabled: vendor=" + vendor + ", renderer=" + renderer + ", version=" + version;
        return false;
    }

    private final String readGlString(int token) {
        String string;
        try {
            string = this.normalize(GL11.glGetString((int)token));
        }
        catch (Throwable ignored) {
            string = "";
        }
        return string;
    }

    private final String normalize(String value) {
        String string;
        block3: {
            block2: {
                string = value;
                if (string == null || (string = ((Object)StringsKt.trim((CharSequence)string)).toString()) == null) break block2;
                String string2 = string;
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"getDefault(...)");
                String string3 = string2.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toLowerCase(...)");
                string = string3;
                if (string3 != null) break block3;
            }
            string = "";
        }
        return string;
    }

    static {
        safeWorldEffectsReason = "unresolved";
        disableFragEffectScanShaderReason = "unresolved";
    }
}

