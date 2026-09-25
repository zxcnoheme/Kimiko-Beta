/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.key;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001d\u0010\u000b\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\r\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\r\u0010\tJ\u0019\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u000eH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0014\u0010\u0013J\u001b\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00040\u0015H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u0015H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0017J\u001b\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u0015H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u0017R \u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00040\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR \u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001bR \u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001b\u00a8\u0006\u001e"}, d2={"Lrtx/kimiko/utils/key/KeyHelper;", "", "<init>", "()V", "", "code", "", "Lkotlin/jvm/JvmStatic;", "getShortName", "(I)Ljava/lang/String;", "name", "getKeyCode", "(Ljava/lang/String;)I", "getKeyName", "", "getAllKeyNames", "()Ljava/util/List;", "", "isMouse", "(I)Z", "isScroll", "", "buildNameMap", "()Ljava/util/Map;", "buildCodeMap", "buildShortMap", "NAME_TO_CODE", "Ljava/util/Map;", "CODE_TO_NAME", "CODE_TO_SHORT", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nKeyHelper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 KeyHelper.kt\nrtx/kimiko/utils/key/KeyHelper\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,223:1\n221#2,2:224\n*S KotlinDebug\n*F\n+ 1 KeyHelper.kt\nrtx/kimiko/utils/key/KeyHelper\n*L\n168#1:224,2\n*E\n"})
public final class KeyHelper {
    @NotNull
    public static final KeyHelper INSTANCE = new KeyHelper();
    @NotNull
    private static final Map<String, Integer> NAME_TO_CODE = INSTANCE.buildNameMap();
    @NotNull
    private static final Map<Integer, String> CODE_TO_NAME = INSTANCE.buildCodeMap();
    @NotNull
    private static final Map<Integer, String> CODE_TO_SHORT = INSTANCE.buildShortMap();

    private KeyHelper() {
    }

    @JvmStatic
    @NotNull
    public static final String getShortName(int code) {
        if (code == -1 || code < 0) {
            return "NONE";
        }
        boolean bl = 0 <= code ? code < 8 : false;
        if (bl) {
            return "M" + (code - 0 + 1);
        }
        boolean bl2 = 290 <= code ? code < 315 : false;
        if (bl2) {
            return "F" + (code - 290 + 1);
        }
        boolean bl3 = 320 <= code ? code < 330 : false;
        if (bl3) {
            return "NUM" + (code - 320);
        }
        String string = CODE_TO_SHORT.get(code);
        if (string == null) {
            string = KeyHelper.getKeyName(code);
        }
        return string;
    }

    @JvmStatic
    public static final int getKeyCode(@Nullable String name) {
        CharSequence charSequence = name;
        if (charSequence == null || StringsKt.isBlank((CharSequence)charSequence)) {
            return -1;
        }
        String string = ((Object)StringsKt.trim((CharSequence)name)).toString().toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toUpperCase(...)");
        Integer n = NAME_TO_CODE.get(string);
        return n != null ? n : -1;
    }

    @JvmStatic
    @NotNull
    public static final String getKeyName(int code) {
        if (code == -1) {
            return "NONE";
        }
        if (code == 1000) {
            return "SCROLL_UP";
        }
        if (code == 1001) {
            return "SCROLL_DOWN";
        }
        if (code == 1002) {
            return "MMB";
        }
        String name = CODE_TO_NAME.get(code);
        return name != null ? name : "KEY_" + code;
    }

    @JvmStatic
    @NotNull
    public static final List<String> getAllKeyNames() {
        return new ArrayList(NAME_TO_CODE.keySet());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @JvmStatic
    public static final boolean isMouse(int code) {
        if (code == 1002) return true;
        if (0 > code) return false;
        if (code >= 8) return false;
        return true;
    }

    @JvmStatic
    public static final boolean isScroll(int code) {
        return code == 1000 || code == 1001;
    }

    private final Map<String, Integer> buildNameMap() {
        int i;
        LinkedHashMap map = new LinkedHashMap();
        ((Map)map).put("MOUSE1", 0);
        ((Map)map).put("MOUSE2", 1);
        ((Map)map).put("MOUSE3", 2);
        ((Map)map).put("MOUSE4", 3);
        ((Map)map).put("MOUSE5", 4);
        ((Map)map).put("MOUSE6", 5);
        ((Map)map).put("MOUSE7", 6);
        ((Map)map).put("MOUSE8", 7);
        ((Map)map).put("LMB", 0);
        ((Map)map).put("RMB", 1);
        ((Map)map).put("MMB", 2);
        ((Map)map).put("MIDDLE_MOUSE", 2);
        ((Map)map).put("MOUSE_MIDDLE", 2);
        ((Map)map).put("WHEEL", 2);
        ((Map)map).put("MOUSE_WHEEL", 2);
        ((Map)map).put("SCROLL_UP", 1000);
        ((Map)map).put("SCROLLUP", 1000);
        ((Map)map).put("WHEEL_UP", 1000);
        ((Map)map).put("MOUSE_WHEEL_UP", 1000);
        ((Map)map).put("SCROLL_DOWN", 1001);
        ((Map)map).put("SCROLLDOWN", 1001);
        ((Map)map).put("WHEEL_DOWN", 1001);
        ((Map)map).put("MOUSE_WHEEL_DOWN", 1001);
        for (i = 0; i < 26; ++i) {
            ((Map)map).put(String.valueOf((char)(65 + i)), 65 + i);
        }
        ((Map)map).put("0", 48);
        ((Map)map).put("1", 49);
        ((Map)map).put("2", 50);
        ((Map)map).put("3", 51);
        ((Map)map).put("4", 52);
        ((Map)map).put("5", 53);
        ((Map)map).put("6", 54);
        ((Map)map).put("7", 55);
        ((Map)map).put("8", 56);
        ((Map)map).put("9", 57);
        for (i = 1; i < 26; ++i) {
            ((Map)map).put("F" + i, 290 + i - 1);
        }
        ((Map)map).put("SPACE", 32);
        ((Map)map).put("ESCAPE", 256);
        ((Map)map).put("ESC", 256);
        ((Map)map).put("ENTER", 257);
        ((Map)map).put("TAB", 258);
        ((Map)map).put("BACKSPACE", 259);
        ((Map)map).put("INSERT", 260);
        ((Map)map).put("DELETE", 261);
        ((Map)map).put("DEL", 261);
        ((Map)map).put("RIGHT", 262);
        ((Map)map).put("LEFT", 263);
        ((Map)map).put("DOWN", 264);
        ((Map)map).put("UP", 265);
        ((Map)map).put("PAGE_UP", 266);
        ((Map)map).put("PAGE_DOWN", 267);
        ((Map)map).put("HOME", 268);
        ((Map)map).put("END", 269);
        ((Map)map).put("CAPS_LOCK", 280);
        ((Map)map).put("SCROLL_LOCK", 281);
        ((Map)map).put("NUM_LOCK", 282);
        ((Map)map).put("PRINT_SCREEN", 283);
        ((Map)map).put("PAUSE", 284);
        ((Map)map).put("LEFT_SHIFT", 340);
        ((Map)map).put("LSHIFT", 340);
        ((Map)map).put("LEFT_CONTROL", 341);
        ((Map)map).put("LCTRL", 341);
        ((Map)map).put("LEFT_ALT", 342);
        ((Map)map).put("LALT", 342);
        ((Map)map).put("RIGHT_SHIFT", 344);
        ((Map)map).put("RSHIFT", 344);
        ((Map)map).put("RIGHT_CONTROL", 345);
        ((Map)map).put("RCTRL", 345);
        ((Map)map).put("RIGHT_ALT", 346);
        ((Map)map).put("RALT", 346);
        ((Map)map).put("LEFT_SUPER", 343);
        ((Map)map).put("LSUPER", 343);
        ((Map)map).put("RIGHT_SUPER", 347);
        ((Map)map).put("RSUPER", 347);
        ((Map)map).put("MENU", 348);
        ((Map)map).put("GRAVE", 96);
        ((Map)map).put("MINUS", 45);
        ((Map)map).put("EQUAL", 61);
        ((Map)map).put("SEMICOLON", 59);
        ((Map)map).put("APOSTROPHE", 39);
        ((Map)map).put("COMMA", 44);
        ((Map)map).put("PERIOD", 46);
        ((Map)map).put("SLASH", 47);
        ((Map)map).put("BACKSLASH", 92);
        ((Map)map).put("NUM0", 320);
        ((Map)map).put("NUM1", 321);
        ((Map)map).put("NUM2", 322);
        ((Map)map).put("NUM3", 323);
        ((Map)map).put("NUM4", 324);
        ((Map)map).put("NUM5", 325);
        ((Map)map).put("NUM6", 326);
        ((Map)map).put("NUM7", 327);
        ((Map)map).put("NUM8", 328);
        ((Map)map).put("NUM9", 329);
        ((Map)map).put("NUM_DECIMAL", 330);
        ((Map)map).put("NUM_DIVIDE", 331);
        ((Map)map).put("NUM_MULTIPLY", 332);
        ((Map)map).put("NUM_SUBTRACT", 333);
        ((Map)map).put("NUM_ADD", 334);
        ((Map)map).put("NUM_ENTER", 335);
        ((Map)map).put("NUM_EQUAL", 336);
        return map;
    }

    private final Map<Integer, String> buildCodeMap() {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        Map<String, Integer> $this$forEach$iv = this.buildNameMap();
        boolean $i$f$forEach = false;
        Iterator<Map.Entry<String, Integer>> iterator = $this$forEach$iv.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> element$iv;
            Map.Entry<String, Integer> entry = element$iv = iterator.next();
            boolean bl = false;
            String name = entry.getKey();
            int code = ((Number)entry.getValue()).intValue();
            map.putIfAbsent(code, name);
        }
        ((Map)map).put(256, "ESC");
        ((Map)map).put(257, "ENT");
        ((Map)map).put(259, "BKSP");
        ((Map)map).put(260, "INS");
        ((Map)map).put(261, "DEL");
        ((Map)map).put(266, "PGUP");
        ((Map)map).put(267, "PGDN");
        ((Map)map).put(280, "CAPS");
        ((Map)map).put(281, "SCRLK");
        ((Map)map).put(282, "NUMLK");
        ((Map)map).put(283, "PRTSC");
        ((Map)map).put(343, "LWIN");
        ((Map)map).put(347, "RWIN");
        ((Map)map).put(96, "`");
        ((Map)map).put(45, "-");
        ((Map)map).put(61, "=");
        ((Map)map).put(91, "[");
        ((Map)map).put(93, "]");
        ((Map)map).put(92, "\\");
        ((Map)map).put(59, ";");
        ((Map)map).put(39, "'");
        ((Map)map).put(44, ",");
        ((Map)map).put(46, ".");
        ((Map)map).put(47, "/");
        ((Map)map).put(330, "NUM.");
        ((Map)map).put(331, "NUM/");
        ((Map)map).put(332, "NUM*");
        ((Map)map).put(333, "NUM-");
        ((Map)map).put(334, "NUM+");
        ((Map)map).put(335, "NUMENT");
        ((Map)map).put(336, "NUM=");
        ((Map)map).put(161, "WORLD1");
        ((Map)map).put(162, "WORLD2");
        ((Map)map).put(1000, "SU");
        ((Map)map).put(1001, "SD");
        ((Map)map).put(1002, "M3");
        return map;
    }

    private final Map<Integer, String> buildShortMap() {
        HashMap map = new HashMap();
        ((Map)map).put(344, "RSH");
        ((Map)map).put(340, "LSH");
        ((Map)map).put(345, "RCT");
        ((Map)map).put(341, "LCT");
        ((Map)map).put(346, "RALT");
        ((Map)map).put(342, "LALT");
        ((Map)map).put(32, "SPC");
        ((Map)map).put(1000, "SU");
        ((Map)map).put(1001, "SD");
        ((Map)map).put(1002, "M3");
        return map;
    }
}

