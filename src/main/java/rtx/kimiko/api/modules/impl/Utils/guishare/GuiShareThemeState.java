/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils.guishare;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.render.render2d.ClientPalette;
import rtx.kimiko.utils.render.render2d.GradientSweep;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u001d\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\b\u0018\u0000 F2\u00020\u0001:\u0001FB\u009f\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u000b\u0012\u0006\u0010\u0011\u001a\u00020\u000b\u0012\u0006\u0010\u0012\u001a\u00020\u000b\u0012\u0006\u0010\u0013\u001a\u00020\u000b\u0012\u0006\u0010\u0014\u001a\u00020\u0007\u0012\u0006\u0010\u0015\u001a\u00020\u000b\u0012\u0006\u0010\u0016\u001a\u00020\u000b\u0012\u0006\u0010\u0017\u001a\u00020\u000b\u0012\u0006\u0010\u0018\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001b\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\"\u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010 H\u0096\u0082\u0004\u00a2\u0006\u0004\b\"\u0010#J\u0011\u0010$\u001a\u00020\u0002H\u0096\u0080\u0004\u00a2\u0006\u0004\b$\u0010%J\u0010\u0010&\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b&\u0010%J\u0010\u0010'\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b'\u0010(J\u0010\u0010)\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b)\u0010(J\u0010\u0010*\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b*\u0010+J\u0010\u0010,\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b,\u0010+J\u0010\u0010-\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b-\u0010%J\u0010\u0010.\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b.\u0010/J\u0010\u00100\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b0\u0010/J\u0010\u00101\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b1\u0010/J\u0010\u00102\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b2\u0010/J\u0010\u00103\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b3\u0010/J\u0010\u00104\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b4\u0010/J\u0010\u00105\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b5\u0010/J\u0010\u00106\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b6\u0010/J\u0010\u00107\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b7\u0010+J\u0010\u00108\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b8\u0010/J\u0010\u00109\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b9\u0010/J\u0010\u0010:\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b:\u0010/J\u0010\u0010;\u001a\u00020\u000bH\u00c6\u0003\u00a2\u0006\u0004\b;\u0010/J\u00ce\u0001\u0010<\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000b2\b\b\u0002\u0010\u0010\u001a\u00020\u000b2\b\b\u0002\u0010\u0011\u001a\u00020\u000b2\b\b\u0002\u0010\u0012\u001a\u00020\u000b2\b\b\u0002\u0010\u0013\u001a\u00020\u000b2\b\b\u0002\u0010\u0014\u001a\u00020\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u000bH\u00c6\u0001\u00a2\u0006\u0004\b<\u0010=J\u0011\u0010?\u001a\u00020>H\u00d6\u0081\u0004\u00a2\u0006\u0004\b?\u0010@R\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\bA\u00a2\u0006\u0006\n\u0004\b\u0003\u0010BR\u0019\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\bA\u00a2\u0006\u0006\n\u0004\b\u0005\u0010CR\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\bA\u00a2\u0006\u0006\n\u0004\b\u0006\u0010CR\u0019\u0010\b\u001a\u00020\u00078\u0006X\u0087\u0004\u0092\u0002\u0002\bA\u00a2\u0006\u0006\n\u0004\b\b\u0010DR\u0019\u0010\t\u001a\u00020\u00078\u0006X\u0087\u0004\u0092\u0002\u0002\bA\u00a2\u0006\u0006\n\u0004\b\t\u0010DR\u0019\u0010\n\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\bA\u00a2\u0006\u0006\n\u0004\b\n\u0010BR\u0019\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\bA\u00a2\u0006\u0006\n\u0004\b\f\u0010ER\u0019\u0010\r\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\bA\u00a2\u0006\u0006\n\u0004\b\r\u0010ER\u0019\u0010\u000e\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\bA\u00a2\u0006\u0006\n\u0004\b\u000e\u0010ER\u0019\u0010\u000f\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\bA\u00a2\u0006\u0006\n\u0004\b\u000f\u0010ER\u0019\u0010\u0010\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\bA\u00a2\u0006\u0006\n\u0004\b\u0010\u0010ER\u0019\u0010\u0011\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\bA\u00a2\u0006\u0006\n\u0004\b\u0011\u0010ER\u0019\u0010\u0012\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\bA\u00a2\u0006\u0006\n\u0004\b\u0012\u0010ER\u0019\u0010\u0013\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\bA\u00a2\u0006\u0006\n\u0004\b\u0013\u0010ER\u0019\u0010\u0014\u001a\u00020\u00078\u0006X\u0087\u0004\u0092\u0002\u0002\bA\u00a2\u0006\u0006\n\u0004\b\u0014\u0010DR\u0019\u0010\u0015\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\bA\u00a2\u0006\u0006\n\u0004\b\u0015\u0010ER\u0019\u0010\u0016\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\bA\u00a2\u0006\u0006\n\u0004\b\u0016\u0010ER\u0019\u0010\u0017\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\bA\u00a2\u0006\u0006\n\u0004\b\u0017\u0010ER\u0019\u0010\u0018\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\bA\u00a2\u0006\u0006\n\u0004\b\u0018\u0010E\u00a8\u0006G"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;", "Ljava/lang/Record;", "", "mode", "", "shades", "palette", "", "movement", "usesSecond", "gradientStyleId", "", "rainbowSpeed", "rainbowSpread", "rainbowSaturation", "cornerRadius", "backdropBlur", "refraction", "edgeStrength", "edgeSharpness", "glow", "glowIntensity", "glowRadius", "gradientSweep", "gradientPrevStyle", "<init>", "(I[I[IZZIFFFFFFFFZFFFF)V", "Lcom/google/gson/JsonObject;", "packet", "", "writeTo", "(Lcom/google/gson/JsonObject;)V", "", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "component1", "component2", "()[I", "component3", "component4", "()Z", "component5", "component6", "component7", "()F", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "copy", "(I[I[IZZIFFFFFFFFZFFFF)Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmField;", "I", "[I", "Z", "F", "Companion", "rtx.kimiko:kimiko"})
public final class GuiShareThemeState
{
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    public final int mode;
    @JvmField
    @NotNull
    public final int[] shades;
    @JvmField
    @NotNull
    public final int[] palette;
    @JvmField
    public final boolean movement;
    @JvmField
    public final boolean usesSecond;
    @JvmField
    public final int gradientStyleId;
    @JvmField
    public final float rainbowSpeed;
    @JvmField
    public final float rainbowSpread;
    @JvmField
    public final float rainbowSaturation;
    @JvmField
    public final float cornerRadius;
    @JvmField
    public final float backdropBlur;
    @JvmField
    public final float refraction;
    @JvmField
    public final float edgeStrength;
    @JvmField
    public final float edgeSharpness;
    @JvmField
    public final boolean glow;
    @JvmField
    public final float glowIntensity;
    @JvmField
    public final float glowRadius;
    @JvmField
    public final float gradientSweep;
    @JvmField
    public final float gradientPrevStyle;
    @JvmField
    @NotNull
    public static final GuiShareThemeState DEFAULTS;

    public GuiShareThemeState(int mode, @NotNull int[] shades, @NotNull int[] palette, boolean movement, boolean usesSecond, int gradientStyleId, float rainbowSpeed, float rainbowSpread, float rainbowSaturation, float cornerRadius, float backdropBlur, float refraction, float edgeStrength, float edgeSharpness, boolean glow, float glowIntensity, float glowRadius, float gradientSweep, float gradientPrevStyle) {
        Intrinsics.checkNotNullParameter((Object)shades, (String)"shades");
        Intrinsics.checkNotNullParameter((Object)palette, (String)"palette");
        this.mode = mode;
        this.shades = shades;
        this.palette = palette;
        this.movement = movement;
        this.usesSecond = usesSecond;
        this.gradientStyleId = gradientStyleId;
        this.rainbowSpeed = rainbowSpeed;
        this.rainbowSpread = rainbowSpread;
        this.rainbowSaturation = rainbowSaturation;
        this.cornerRadius = cornerRadius;
        this.backdropBlur = backdropBlur;
        this.refraction = refraction;
        this.edgeStrength = edgeStrength;
        this.edgeSharpness = edgeSharpness;
        this.glow = glow;
        this.glowIntensity = glowIntensity;
        this.glowRadius = glowRadius;
        this.gradientSweep = gradientSweep;
        this.gradientPrevStyle = gradientPrevStyle;
    }

    public final void writeTo(@NotNull JsonObject packet) {
        Intrinsics.checkNotNullParameter((Object)packet, (String)"packet");
        packet.addProperty("cm", (Number)this.mode);
        JsonArray cs = new JsonArray();
        int[] nArray = this.shades;
        int n = nArray.length;
        for (int i = 0; i < n; ++i) {
            int shade = nArray[i];
            cs.add((Number)(shade & 0xFFFFFF));
        }
        packet.add("cs", (JsonElement)cs);
        if (this.mode != 2 && !(this.palette.length == 0)) {
            JsonArray cp = new JsonArray();
            for (int stop : this.palette) {
                cp.add((Number)(stop & 0xFFFFFF));
            }
            packet.add("cp", (JsonElement)cp);
        }
        packet.addProperty("cv", Boolean.valueOf(this.movement));
        packet.addProperty("c2", Boolean.valueOf(this.usesSecond));
        packet.addProperty("cg", (Number)this.gradientStyleId);
        packet.addProperty("zw", (Number)Float.valueOf(this.gradientSweep));
        packet.addProperty("zp", (Number)Float.valueOf(this.gradientPrevStyle));
        packet.addProperty("rs", (Number)Float.valueOf(this.rainbowSpeed));
        packet.addProperty("rd", (Number)Float.valueOf(this.rainbowSpread));
        packet.addProperty("ra", (Number)Float.valueOf(this.rainbowSaturation));
        packet.addProperty("cr", (Number)Float.valueOf(this.cornerRadius));
        packet.addProperty("cb", (Number)Float.valueOf(this.backdropBlur));
        packet.addProperty("cf", (Number)Float.valueOf(this.refraction));
        packet.addProperty("ce", (Number)Float.valueOf(this.edgeStrength));
        packet.addProperty("ck", (Number)Float.valueOf(this.edgeSharpness));
        packet.addProperty("gw", Boolean.valueOf(this.glow));
        packet.addProperty("gi", (Number)Float.valueOf(this.glowIntensity));
        packet.addProperty("gr", (Number)Float.valueOf(this.glowRadius));
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        Object object = other;
        if (!Intrinsics.areEqual(this.getClass(), object != null ? object.getClass() : null)) {
            return false;
        }
        Object object2 = other;
        Intrinsics.checkNotNull((Object)object2, (String)"null cannot be cast to non-null type rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareThemeState");
        GuiShareThemeState cfr_ignored_0 = (GuiShareThemeState)object2;
        if (this.mode != ((GuiShareThemeState)other).mode) {
            return false;
        }
        if (!Arrays.equals(this.shades, ((GuiShareThemeState)other).shades)) {
            return false;
        }
        if (!Arrays.equals(this.palette, ((GuiShareThemeState)other).palette)) {
            return false;
        }
        if (this.movement != ((GuiShareThemeState)other).movement) {
            return false;
        }
        if (this.usesSecond != ((GuiShareThemeState)other).usesSecond) {
            return false;
        }
        if (this.gradientStyleId != ((GuiShareThemeState)other).gradientStyleId) {
            return false;
        }
        if (!(this.rainbowSpeed == ((GuiShareThemeState)other).rainbowSpeed)) {
            return false;
        }
        if (!(this.rainbowSpread == ((GuiShareThemeState)other).rainbowSpread)) {
            return false;
        }
        if (!(this.rainbowSaturation == ((GuiShareThemeState)other).rainbowSaturation)) {
            return false;
        }
        if (!(this.cornerRadius == ((GuiShareThemeState)other).cornerRadius)) {
            return false;
        }
        if (!(this.backdropBlur == ((GuiShareThemeState)other).backdropBlur)) {
            return false;
        }
        if (!(this.refraction == ((GuiShareThemeState)other).refraction)) {
            return false;
        }
        if (!(this.edgeStrength == ((GuiShareThemeState)other).edgeStrength)) {
            return false;
        }
        if (!(this.edgeSharpness == ((GuiShareThemeState)other).edgeSharpness)) {
            return false;
        }
        if (this.glow != ((GuiShareThemeState)other).glow) {
            return false;
        }
        if (!(this.glowIntensity == ((GuiShareThemeState)other).glowIntensity)) {
            return false;
        }
        if (!(this.glowRadius == ((GuiShareThemeState)other).glowRadius)) {
            return false;
        }
        if (!(this.gradientSweep == ((GuiShareThemeState)other).gradientSweep)) {
            return false;
        }
        return this.gradientPrevStyle == ((GuiShareThemeState)other).gradientPrevStyle;
    }

    @Override
    public int hashCode() {
        int result = this.mode;
        result = 31 * result + Arrays.hashCode(this.shades);
        result = 31 * result + Arrays.hashCode(this.palette);
        result = 31 * result + Boolean.hashCode(this.movement);
        result = 31 * result + Boolean.hashCode(this.usesSecond);
        result = 31 * result + this.gradientStyleId;
        result = 31 * result + Float.hashCode(this.rainbowSpeed);
        result = 31 * result + Float.hashCode(this.rainbowSpread);
        result = 31 * result + Float.hashCode(this.rainbowSaturation);
        result = 31 * result + Float.hashCode(this.cornerRadius);
        result = 31 * result + Float.hashCode(this.backdropBlur);
        result = 31 * result + Float.hashCode(this.refraction);
        result = 31 * result + Float.hashCode(this.edgeStrength);
        result = 31 * result + Float.hashCode(this.edgeSharpness);
        result = 31 * result + Boolean.hashCode(this.glow);
        result = 31 * result + Float.hashCode(this.glowIntensity);
        result = 31 * result + Float.hashCode(this.glowRadius);
        result = 31 * result + Float.hashCode(this.gradientSweep);
        result = 31 * result + Float.hashCode(this.gradientPrevStyle);
        return result;
    }

    public final int component1() {
        return this.mode;
    }

    @NotNull
    public final int[] component2() {
        return this.shades;
    }

    @NotNull
    public final int[] component3() {
        return this.palette;
    }

    public final boolean component4() {
        return this.movement;
    }

    public final boolean component5() {
        return this.usesSecond;
    }

    public final int component6() {
        return this.gradientStyleId;
    }

    public final float component7() {
        return this.rainbowSpeed;
    }

    public final float component8() {
        return this.rainbowSpread;
    }

    public final float component9() {
        return this.rainbowSaturation;
    }

    public final float component10() {
        return this.cornerRadius;
    }

    public final float component11() {
        return this.backdropBlur;
    }

    public final float component12() {
        return this.refraction;
    }

    public final float component13() {
        return this.edgeStrength;
    }

    public final float component14() {
        return this.edgeSharpness;
    }

    public final boolean component15() {
        return this.glow;
    }

    public final float component16() {
        return this.glowIntensity;
    }

    public final float component17() {
        return this.glowRadius;
    }

    public final float component18() {
        return this.gradientSweep;
    }

    public final float component19() {
        return this.gradientPrevStyle;
    }

    @NotNull
    public final GuiShareThemeState copy(int mode, @NotNull int[] shades, @NotNull int[] palette, boolean movement, boolean usesSecond, int gradientStyleId, float rainbowSpeed, float rainbowSpread, float rainbowSaturation, float cornerRadius, float backdropBlur, float refraction, float edgeStrength, float edgeSharpness, boolean glow, float glowIntensity, float glowRadius, float gradientSweep, float gradientPrevStyle) {
        Intrinsics.checkNotNullParameter((Object)shades, (String)"shades");
        Intrinsics.checkNotNullParameter((Object)palette, (String)"palette");
        return new GuiShareThemeState(mode, shades, palette, movement, usesSecond, gradientStyleId, rainbowSpeed, rainbowSpread, rainbowSaturation, cornerRadius, backdropBlur, refraction, edgeStrength, edgeSharpness, glow, glowIntensity, glowRadius, gradientSweep, gradientPrevStyle);
    }

    public static /* synthetic */ GuiShareThemeState copy$default(GuiShareThemeState guiShareThemeState, int n, int[] nArray, int[] nArray2, boolean bl, boolean bl2, int n2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, boolean bl3, float f9, float f10, float f11, float f12, int n3, Object object) {
        if ((n3 & 1) != 0) {
            n = guiShareThemeState.mode;
        }
        if ((n3 & 2) != 0) {
            nArray = guiShareThemeState.shades;
        }
        if ((n3 & 4) != 0) {
            nArray2 = guiShareThemeState.palette;
        }
        if ((n3 & 8) != 0) {
            bl = guiShareThemeState.movement;
        }
        if ((n3 & 0x10) != 0) {
            bl2 = guiShareThemeState.usesSecond;
        }
        if ((n3 & 0x20) != 0) {
            n2 = guiShareThemeState.gradientStyleId;
        }
        if ((n3 & 0x40) != 0) {
            f = guiShareThemeState.rainbowSpeed;
        }
        if ((n3 & 0x80) != 0) {
            f2 = guiShareThemeState.rainbowSpread;
        }
        if ((n3 & 0x100) != 0) {
            f3 = guiShareThemeState.rainbowSaturation;
        }
        if ((n3 & 0x200) != 0) {
            f4 = guiShareThemeState.cornerRadius;
        }
        if ((n3 & 0x400) != 0) {
            f5 = guiShareThemeState.backdropBlur;
        }
        if ((n3 & 0x800) != 0) {
            f6 = guiShareThemeState.refraction;
        }
        if ((n3 & 0x1000) != 0) {
            f7 = guiShareThemeState.edgeStrength;
        }
        if ((n3 & 0x2000) != 0) {
            f8 = guiShareThemeState.edgeSharpness;
        }
        if ((n3 & 0x4000) != 0) {
            bl3 = guiShareThemeState.glow;
        }
        if ((n3 & 0x8000) != 0) {
            f9 = guiShareThemeState.glowIntensity;
        }
        if ((n3 & 0x10000) != 0) {
            f10 = guiShareThemeState.glowRadius;
        }
        if ((n3 & 0x20000) != 0) {
            f11 = guiShareThemeState.gradientSweep;
        }
        if ((n3 & 0x40000) != 0) {
            f12 = guiShareThemeState.gradientPrevStyle;
        }
        return guiShareThemeState.copy(n, nArray, nArray2, bl, bl2, n2, f, f2, f3, f4, f5, f6, f7, f8, bl3, f9, f10, f11, f12);
    }

    @Override
    @NotNull
    public String toString() {
        return "GuiShareThemeState(mode=" + this.mode + ", shades=" + Arrays.toString(this.shades) + ", palette=" + Arrays.toString(this.palette) + ", movement=" + this.movement + ", usesSecond=" + this.usesSecond + ", gradientStyleId=" + this.gradientStyleId + ", rainbowSpeed=" + this.rainbowSpeed + ", rainbowSpread=" + this.rainbowSpread + ", rainbowSaturation=" + this.rainbowSaturation + ", cornerRadius=" + this.cornerRadius + ", backdropBlur=" + this.backdropBlur + ", refraction=" + this.refraction + ", edgeStrength=" + this.edgeStrength + ", edgeSharpness=" + this.edgeSharpness + ", glow=" + this.glow + ", glowIntensity=" + this.glowIntensity + ", glowRadius=" + this.glowRadius + ", gradientSweep=" + this.gradientSweep + ", gradientPrevStyle=" + this.gradientPrevStyle + ")";
    }

    @JvmStatic
    @NotNull
    public static final GuiShareThemeState capture() {
        return Companion.capture();
    }

    @JvmStatic
    @NotNull
    public static final GuiShareThemeState fromJson(@Nullable JsonObject state) {
        return Companion.fromJson(state);
    }

    static {
        int[] nArray = new int[]{14542847};
        DEFAULTS = new GuiShareThemeState(1, ClientAccent.shadesFromCustom(-857872385, -855690602, false), nArray, false, false, 1, 1.0f, 0.18f, 0.85f, 7.0f, 18.0f, 0.3f, 0.18f, 55.0f, false, 0.6f, 15.0f, -1.0f, 1.0f);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\n\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u000bJ'\u0010\u0011\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J'\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ'\u0010 \u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b \u0010!J'\u0010\"\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\"\u0010#R\u0019\u0010%\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b$\u00a2\u0006\u0006\n\u0004\b%\u0010&\u00a8\u0006'"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;", "Lkotlin/jvm/JvmStatic;", "capture", "()Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;", "Lcom/google/gson/JsonObject;", "state", "fromJson", "(Lcom/google/gson/JsonObject;)Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;", "", "key", "", "maxCount", "", "readColors", "(Lcom/google/gson/JsonObject;Ljava/lang/String;I)[I", "objectObj", "fallback", "readInt", "(Lcom/google/gson/JsonObject;Ljava/lang/String;I)I", "", "readFloat", "(Lcom/google/gson/JsonObject;Ljava/lang/String;F)F", "", "readBool", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Z", "value", "min", "max", "clampInt", "(III)I", "clampFloat", "(FFF)F", "Lkotlin/jvm/JvmField;", "DEFAULTS", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareThemeState;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final GuiShareThemeState capture() {
            try {
                int[] source;
                InterfaceModule interfaceModule = InterfaceModule.Companion.getInstance();
                if (interfaceModule == null) {
                    return DEFAULTS;
                }
                InterfaceModule module = interfaceModule;
                int[] nArray = new int[]{ClientAccent.accent(255.0f) & 0xFFFFFF, ClientAccent.accentBright(255.0f) & 0xFFFFFF, ClientAccent.accentSoft(255.0f) & 0xFFFFFF, ClientAccent.accentFill(255.0f) & 0xFFFFFF, ClientAccent.toggleOn(255.0f) & 0xFFFFFF, ClientAccent.gradientA(255.0f) & 0xFFFFFF, ClientAccent.gradientB(255.0f) & 0xFFFFFF};
                int[] shades = nArray;
                int mode = module.isThemeClientColor() ? 0 : (module.isCustomClientColor() ? 1 : 2);
                int[] palette = new int[]{};
                if (mode != 2 && (source = module.clientPalette()) != null && !(source.length == 0)) {
                    int count = Math.min(source.length, 9);
                    palette = new int[count];
                    for (int i = 0; i < count; ++i) {
                        palette[i] = source[i] & 0xFFFFFF;
                    }
                }
                return new GuiShareThemeState(mode, shades, palette, module.clientColorMovement(), module.usesSecondClientColor(), module.gradientStyleId(), module.rainbowSpeed.getFloat(), module.rainbowSpread.getFloat(), module.rainbowSaturation.getFloat(), module.rectCornerRadius.getFloat(), module.rectBackdropBlur.getFloat(), module.rectRefractionStrength.getFloat(), module.rectEdgeStrength.getFloat(), module.rectEdgeSharpness.getFloat(), module.rectGlow.getValue(), module.rectGlowIntensity.getFloat(), module.rectGlowRadius.getFloat(), GradientSweep.progress(), ClientPalette.prevStyle());
            }
            catch (Throwable throwable) {
                return DEFAULTS;
            }
        }

        @JvmStatic
        @NotNull
        public final GuiShareThemeState fromJson(@Nullable JsonObject state) {
            GuiShareThemeState guiShareThemeState;
            if (state == null || !state.has("cm")) {
                return DEFAULTS;
            }
            try {
                int[] shades = this.readColors(state, "cs", 7);
                if (shades.length < 7) {
                    shades = GuiShareThemeState.DEFAULTS.shades;
                }
                int[] palette = this.readColors(state, "cp", 9);
                guiShareThemeState = new GuiShareThemeState(this.clampInt(this.readInt(state, "cm", 1), 0, 2), shades, palette, this.readBool(state, "cv"), this.readBool(state, "c2"), this.clampInt(this.readInt(state, "cg", 1), 0, 2), this.clampFloat(this.readFloat(state, "rs", 1.0f), 0.1f, 4.0f), this.clampFloat(this.readFloat(state, "rd", 0.18f), 0.02f, 0.5f), this.clampFloat(this.readFloat(state, "ra", 0.85f), 0.3f, 1.0f), this.clampFloat(this.readFloat(state, "cr", 7.0f), 2.0f, 10.0f), this.clampFloat(this.readFloat(state, "cb", 18.0f), 0.0f, 64.0f), this.clampFloat(this.readFloat(state, "cf", 0.3f), 0.0f, 0.8f), this.clampFloat(this.readFloat(state, "ce", 0.18f), 0.0f, 1.0f), this.clampFloat(this.readFloat(state, "ck", 55.0f), 2.0f, 100.0f), this.readBool(state, "gw"), this.clampFloat(this.readFloat(state, "gi", 0.6f), 0.0f, 2.0f), this.clampFloat(this.readFloat(state, "gr", 15.0f), 15.0f, 70.0f), this.clampFloat(this.readFloat(state, "zw", -1.0f), -1.0f, 1.0f), this.clampFloat(this.readFloat(state, "zp", 1.0f), 0.0f, 2.0f));
            }
            catch (Exception exception) {
                guiShareThemeState = DEFAULTS;
            }
            return guiShareThemeState;
        }

        private final int[] readColors(JsonObject state, String key, int maxCount) {
            if (!state.has(key) || !state.get(key).isJsonArray()) {
                return new int[0];
            }
            JsonArray array = state.getAsJsonArray(key);
            int count = Math.min(array.size(), maxCount);
            int[] out = new int[count];
            for (int i = 0; i < count; ++i) {
                JsonElement element = array.get(i);
                int value = 0;
                if (element.isJsonPrimitive()) {
                    try {
                        value = element.getAsInt();
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
                out[i] = this.clampInt(value, 0, 0xFFFFFF);
            }
            return out;
        }

        private final int readInt(JsonObject objectObj, String key, int fallback) {
            if (objectObj.has(key) && objectObj.get(key).isJsonPrimitive()) {
                try {
                    return objectObj.get(key).getAsInt();
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
            return fallback;
        }

        private final float readFloat(JsonObject objectObj, String key, float fallback) {
            if (objectObj.has(key) && objectObj.get(key).isJsonPrimitive()) {
                try {
                    float value = objectObj.get(key).getAsFloat();
                    if (Float.isFinite(value)) {
                        return value;
                    }
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
            return fallback;
        }

        private final boolean readBool(JsonObject objectObj, String key) {
            if (objectObj.has(key) && objectObj.get(key).isJsonPrimitive()) {
                try {
                    return objectObj.get(key).getAsBoolean();
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
            return false;
        }

        private final int clampInt(int value, int min, int max) {
            return Math.max(min, Math.min(max, value));
        }

        private final float clampFloat(float value, float min, float max) {
            return Math.max(min, Math.min(max, value));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

