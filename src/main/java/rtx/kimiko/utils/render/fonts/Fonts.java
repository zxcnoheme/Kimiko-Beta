/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.fonts;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b)\n\u0002\u0010\u0014\n\u0002\b\u001e\b\u0086\u0081\u0002\u0018\u0000 F2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001FB/\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0005\"\u00020\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\tJ\u000f\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0004\u0010\tJ\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ7\u0010\u0015\u001a\u00020\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0015\u0010\u0016JA\u0010\u0015\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0015\u0010\u0019JO\u0010\u0015\u001a\u00020\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0015\u0010\u001dJO\u0010\u0015\u001a\u00020\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u0012\u00a2\u0006\u0004\b\u0015\u0010\"Jg\u0010\u0015\u001a\u00020\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000e\u00a2\u0006\u0004\b\u0015\u0010#J_\u0010)\u001a\u00020\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\n2\u0006\u0010(\u001a\u00020\n\u00a2\u0006\u0004\b)\u0010*J_\u0010)\u001a\u00020\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u000e\u00a2\u0006\u0004\b)\u0010-J7\u0010.\u001a\u00020\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\u0004\b.\u0010\u0016JO\u0010.\u001a\u00020\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000e\u00a2\u0006\u0004\b.\u0010\u001dJO\u0010.\u001a\u00020\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u0012\u00a2\u0006\u0004\b.\u0010\"J_\u0010/\u001a\u00020\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u000e\u00a2\u0006\u0004\b/\u0010-J?\u00102\u001a\u00020\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u00100\u001a\u00020\u00122\u0006\u00101\u001a\u00020\u000e\u00a2\u0006\u0004\b2\u00103JG\u00102\u001a\u00020\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u00104\u001a\u00020\u00122\u0006\u00105\u001a\u00020\u00122\u0006\u00101\u001a\u00020\u000e\u00a2\u0006\u0004\b2\u00106JO\u0010;\u001a\u00020\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u00107\u001a\u00020\u000e2\u0006\u00108\u001a\u00020\u000e2\u0006\u00109\u001a\u00020\u000e2\u0006\u0010:\u001a\u00020\u000e\u00a2\u0006\u0004\b;\u0010<JW\u0010;\u001a\u00020\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u00107\u001a\u00020\u000e2\u0006\u00108\u001a\u00020\u000e2\u0006\u00109\u001a\u00020\u000e2\u0006\u0010:\u001a\u00020\u000e\u00a2\u0006\u0004\b;\u0010=J\u001f\u0010>\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0011\u001a\u00020\u000e\u00a2\u0006\u0004\b>\u0010?J\u001f\u0010@\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0011\u001a\u00020\u000e\u00a2\u0006\u0004\b@\u0010?J\u001f\u0010B\u001a\u00020A2\b\u0010\r\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0011\u001a\u00020\u000e\u00a2\u0006\u0004\bB\u0010CR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010DR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010DR\u001c\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010Ej\u0002\bGj\u0002\bHj\u0002\bIj\u0002\bJj\u0002\bKj\u0002\bLj\u0002\bMj\u0002\bNj\u0002\bOj\u0002\bPj\u0002\bQj\u0002\bRj\u0002\bSj\u0002\bTj\u0002\bUj\u0002\bVj\u0002\bWj\u0002\bXj\u0002\bYj\u0002\bZj\u0002\b[j\u0002\b\\j\u0002\b]j\u0002\b^\u00a8\u0006_"}, d2={"Lrtx/kimiko/utils/render/fonts/Fonts;", "", "", "id", "atlas", "", "aliases", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V", "()Ljava/lang/String;", "", "vector", "()Z", "text", "", "x", "y", "size", "", "color", "", "draw", "(Ljava/lang/String;FFFI)V", "Lnet/minecraft/DrawContext;", "graphics", "(Lnet/minecraft/DrawContext;Ljava/lang/String;FFFI)V", "rotationDegrees", "rotationOriginX", "rotationOriginY", "(Ljava/lang/String;FFFIFFF)V", "colorTopLeft", "colorTopRight", "colorBottomRight", "colorBottomLeft", "(Ljava/lang/String;FFFIIII)V", "(Ljava/lang/String;FFFIIIIFFF)V", "fadeLeftX", "fadeRightX", "fadeWidth", "fadeLeft", "fadeRight", "fade", "(Ljava/lang/String;FFFIFFFZZ)V", "fadeLeftStrength", "fadeRightStrength", "(Ljava/lang/String;FFFIFFFFF)V", "msdf", "msdfFade", "baseColor", "phase", "wave", "(Ljava/lang/String;FFFIF)V", "topColor", "bottomColor", "(Ljava/lang/String;FFFIIF)V", "progress", "halfWidth", "intensity", "opacity", "shimmer", "(Ljava/lang/String;FFFFFFF)V", "(Ljava/lang/String;FFFIFFFF)V", "width", "(Ljava/lang/String;F)F", "msdfWidth", "", "msdfBounds", "(Ljava/lang/String;F)[F", "Ljava/lang/String;", "[Ljava/lang/String;", "Companion", "REGULAR", "MEDIUM", "SEMIBOLD", "BOLD", "EXTRABOLD", "BLACK", "LIGHT", "EXTRALIGHT", "THIN", "SF", "SF_MEDIUM", "SF_BOLD", "KIMIKO", "SEND", "I2", "ICONS", "EVENT_ICONS", "INV_ICONS", "MEDIA_ICONS", "HEART", "SMALL_PIXEL", "MANASCO", "MAINMENU", "SEMI_BOLD", "rtx.kimiko:kimiko"})
public enum Fonts {
        REGULAR("montserrat-regular", "fonts/monsterat/montserrat-regular", new String[]{"montserrat"}),
        MEDIUM("montserrat-medium", "fonts/monsterat/montserrat-medium", new String[0]),
        SEMIBOLD("montserrat-semibold", "fonts/monsterat/montserrat-semibold", new String[]{"montserrat-semi-bold"}),
        BOLD("montserrat-bold", "fonts/monsterat/montserrat-bold", new String[0]),
        EXTRABOLD("montserrat-extrabold", "fonts/monsterat/montserrat-extrabold", new String[]{"montserrat-extra-bold"}),
        BLACK("montserrat-black", "fonts/monsterat/montserrat-black", new String[0]),
        LIGHT("montserrat-light", "fonts/monsterat/montserrat-light", new String[0]),
        EXTRALIGHT("montserrat-extralight", "fonts/monsterat/montserrat-extralight", new String[]{"montserrat-extra-light"}),
        THIN("montserrat-thin", "fonts/monsterat/montserrat-thin", new String[0]),
        SF("sf-regular", "fonts/sf-pro/sf-pro-regular", new String[]{"sf"}),
        SF_MEDIUM("sf-medium", "fonts/sf-pro/sf-pro-medium", new String[0]),
        SF_BOLD("sf-bold", "fonts/sf-pro/sf-pro-bold", new String[0]),
        KIMIKO("kimiko", "fonts/kimiko/kimiko", new String[0]),
        SEND("send", "fonts/send/kimiko", new String[0]),
        I2("i2", "fonts/i2/i2", new String[0]),
        ICONS("icons", "fonts/icons/icons", new String[0]),
        EVENT_ICONS("event-icons", "fonts/event-icons/event-icons", new String[]{"events-icons", "eventicons"}),
        INV_ICONS("inv-icons", "fonts/inv-icons/inv-icons", new String[]{"inventory-icons", "invicons"}),
        MEDIA_ICONS("media-icons", "fonts/media-icons/media-icons", new String[]{"mediaicons"}),
        HEART("heart", "fonts/heart/heart", new String[0]),
        SMALL_PIXEL("small-pixel", "fonts/smallpixel/small-pixel", new String[0]),
        MANASCO("manasco", "fonts/manasco/manasco", new String[0]),
        MAINMENU("mainmenu", "fonts/mainmenu/mainmenu", new String[0]),
        SEMI_BOLD("semi_bold", null, new String[0]);
    @NotNull
    public static final Companion Companion;
    @NotNull
    private final String id;
    @Nullable
    private final String atlas;
    @NotNull
    private final String[] aliases;
    @JvmField
    @NotNull
    public static final Fonts DEFAULT;
    @NotNull
    private static final HashMap<String, Fonts> LOOKUP;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private Fonts(String id, String atlas, String ... aliases) {
        this.id = id;
        this.atlas = atlas;
        this.aliases = aliases;
    }

    @NotNull
    public final String id() {
        return this.id;
    }

    @Nullable
    public final String atlas() {
        return this.atlas;
    }

    public final boolean vector() {
        return this.atlas != null;
    }

    public final void draw(@Nullable String text, float x, float y, float size, int color) {
        Render2D.text(this, text, x, y, size, color);
    }

    public final void draw(@Nullable DrawContext graphics, @Nullable String text, float x, float y, float size, int color) {
        Render2D.text(graphics, this, text, x, y, size, color);
    }

    public final void draw(@Nullable String text, float x, float y, float size, int color, float rotationDegrees, float rotationOriginX, float rotationOriginY) {
        Render2D.text(this, text, x, y, size, color, rotationDegrees, rotationOriginX, rotationOriginY);
    }

    public final void draw(@Nullable String text, float x, float y, float size, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft) {
        Render2D.text(this, text, x, y, size, colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft);
    }

    public final void draw(@Nullable String text, float x, float y, float size, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft, float rotationDegrees, float rotationOriginX, float rotationOriginY) {
        Render2D.text(this, text, x, y, size, colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft, rotationDegrees, rotationOriginX, rotationOriginY);
    }

    public final void fade(@Nullable String text, float x, float y, float size, int color, float fadeLeftX, float fadeRightX, float fadeWidth, boolean fadeLeft, boolean fadeRight) {
        Render2D.textFade(this, text, x, y, size, color, fadeLeftX, fadeRightX, fadeWidth, fadeLeft, fadeRight);
    }

    public final void fade(@Nullable String text, float x, float y, float size, int color, float fadeLeftX, float fadeRightX, float fadeWidth, float fadeLeftStrength, float fadeRightStrength) {
        Render2D.textFade(this, text, x, y, size, color, fadeLeftX, fadeRightX, fadeWidth, fadeLeftStrength, fadeRightStrength);
    }

    public final void msdf(@Nullable String text, float x, float y, float size, int color) {
        Render2D.msdfText(this, text, x, y, size, color);
    }

    public final void msdf(@Nullable String text, float x, float y, float size, int color, float rotationDegrees, float rotationOriginX, float rotationOriginY) {
        Render2D.msdfText(this, text, x, y, size, color, rotationDegrees, rotationOriginX, rotationOriginY);
    }

    public final void msdf(@Nullable String text, float x, float y, float size, int colorTopLeft, int colorTopRight, int colorBottomRight, int colorBottomLeft) {
        Render2D.msdfText(this, text, x, y, size, colorTopLeft, colorTopRight, colorBottomRight, colorBottomLeft);
    }

    public final void msdfFade(@Nullable String text, float x, float y, float size, int color, float fadeLeftX, float fadeRightX, float fadeWidth, float fadeLeftStrength, float fadeRightStrength) {
        Render2D.msdfTextFade(this, text, x, y, size, color, fadeLeftX, fadeRightX, fadeWidth, fadeLeftStrength, fadeRightStrength);
    }

    public final void wave(@Nullable String text, float x, float y, float size, int baseColor, float phase) {
        Render2D.msdfWave(this, text, x, y, size, baseColor, phase);
    }

    public final void wave(@Nullable String text, float x, float y, float size, int topColor, int bottomColor, float phase) {
        Render2D.msdfWave(this, text, x, y, size, topColor, bottomColor, phase);
    }

    public final void shimmer(@Nullable String text, float x, float y, float size, float progress, float halfWidth, float intensity, float opacity) {
        Render2D.msdfShimmer(this, text, x, y, size, progress, halfWidth, intensity, opacity);
    }

    public final void shimmer(@Nullable String text, float x, float y, float size, int color, float progress, float halfWidth, float intensity, float opacity) {
        Render2D.msdfShimmer(this, text, x, y, size, color, progress, halfWidth, intensity, opacity);
    }

    public final float width(@Nullable String text, float size) {
        return Render2D.textWidth(this, text, size);
    }

    public final float msdfWidth(@Nullable String text, float size) {
        return Render2D.msdfWidth(this, text, size);
    }

    @NotNull
    public final float[] msdfBounds(@Nullable String text, float size) {
        return Render2D.msdfBounds(this, text, size);
    }

    

    

    @NotNull
    public static EnumEntries<Fonts> getEntries() {
        return EnumEntriesKt.enumEntries(values());
    }

    @JvmStatic
    @NotNull
    public static final Fonts of(@Nullable String name) {
        return Companion.of(name);
    }

    @JvmStatic
    @Nullable
    public static final Fonts of(@Nullable String name, @Nullable Fonts fallback) {
        return Companion.of(name, fallback);
    }

    static {
        Companion = new Companion(null);
        DEFAULT = REGULAR;
        LOOKUP = new HashMap<>();
        for (Fonts fonts : Fonts.values()) {
            LOOKUP.put(Fonts.Companion.key(fonts.name()), fonts);
            LOOKUP.put(Fonts.Companion.key(fonts.id), fonts);
            for (String alias : fonts.aliases) {
                LOOKUP.put(Fonts.Companion.key(alias), fonts);
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ)\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\u000bJ\u0017\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\f\u0010\rR\u0019\u0010\u000f\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R0\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u0011j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0006`\u00128\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/utils/render/fonts/Fonts.Companion;", "", "<init>", "()V", "", "name", "Lrtx/kimiko/utils/render/fonts/Fonts;", "Lkotlin/jvm/JvmStatic;", "of", "(Ljava/lang/String;)Lrtx/kimiko/utils/render/fonts/Fonts;", "fallback", "(Ljava/lang/String;Lrtx/kimiko/utils/render/fonts/Fonts;)Lrtx/kimiko/utils/render/fonts/Fonts;", "key", "(Ljava/lang/String;)Ljava/lang/String;", "Lkotlin/jvm/JvmField;", "DEFAULT", "Lrtx/kimiko/utils/render/fonts/Fonts;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "LOOKUP", "Ljava/util/HashMap;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final Fonts of(@Nullable String name) {
            Fonts fonts = this.of(name, DEFAULT);
            Intrinsics.checkNotNull((Object)((Object)fonts));
            return fonts;
        }

        @JvmStatic
        @Nullable
        public final Fonts of(@Nullable String name, @Nullable Fonts fallback) {
            CharSequence charSequence = name;
            if (charSequence == null || StringsKt.isBlank((CharSequence)charSequence)) {
                return fallback;
            }
            Fonts fonts = (Fonts)((Object)LOOKUP.get(this.key(name)));
            if (fonts == null) {
                fonts = fallback;
            }
            return fonts;
        }

        private final String key(String name) {
            return name.trim().toLowerCase(Locale.ROOT).replace('-', '_').replace(' ', '_');
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

