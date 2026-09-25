/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.io.CloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.lang;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.ui.ClientLanguage;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u001d\u0010\t\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\nJ5\u0010\t\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00072\u0016\u0010\f\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000b\"\u0004\u0018\u00010\u0001H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\rJ\u001d\u0010\u000f\u001a\u00020\u000e2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J3\u0010\u0016\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0014j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00152\u0006\u0010\u0011\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017R0\u0010\u0018\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0014j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R2\u0010\u001a\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0014j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007`\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0019R\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001c\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/api/lang/I18n;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "invalidate", "", "text", "tr", "(Ljava/lang/String;)Ljava/lang/String;", "", "args", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "", "has", "(Ljava/lang/String;)Z", "code", "reload", "(Ljava/lang/String;)V", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "read", "(Ljava/lang/String;)Ljava/util/HashMap;", "EMPTY", "Ljava/util/HashMap;", "table", "loadedCode", "Ljava/lang/String;", "rtx.kimiko:kimiko"})
public final class I18n {
    @NotNull
    public static final I18n INSTANCE = new I18n();
    @NotNull
    private static final HashMap<String, String> EMPTY = new HashMap(0);
    @NotNull
    private static volatile HashMap<String, String> table = EMPTY;
    @Nullable
    private static volatile String loadedCode;

    private I18n() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void invalidate() {
        I18n i18n = INSTANCE;
        synchronized (i18n) {
            boolean bl = false;
            table = EMPTY;
            loadedCode = null;
            Unit unit = Unit.INSTANCE;
        }
    }

    @JvmStatic
    @NotNull
    public static final String tr(@Nullable String text) {
        String string;
        if (text == null || ((CharSequence)text).length() == 0) {
            return "";
        }
        String code = ClientLanguage.code();
        if (Intrinsics.areEqual((Object)code, (Object)"ru")) {
            return text;
        }
        if (!Intrinsics.areEqual((Object)loadedCode, (Object)code)) {
            INSTANCE.reload(code);
        }
        if ((string = table.get(text)) == null) {
            string = text;
        }
        return string;
    }

    @JvmStatic
    @NotNull
    public static final String tr(@Nullable String text, Object ... args) {
        String string;
        Intrinsics.checkNotNullParameter((Object)args, (String)"args");
        String template = I18n.tr(text);
        if (args.length == 0) {
            return template;
        }
        try {
            Locale locale = Locale.ROOT;
            Object[] objectArray = Arrays.copyOf(args, args.length);
            String string2 = String.format(locale, template, Arrays.copyOf(objectArray, objectArray.length));
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"format(...)");
            string = string2;
        }
        catch (Throwable ignored) {
            String string3;
            try {
                Locale locale = Locale.ROOT;
                String string4 = text;
                if (string4 == null) {
                    string4 = "";
                }
                String string5 = string4;
                Object[] objectArray = Arrays.copyOf(args, args.length);
                String string6 = String.format(locale, string5, Arrays.copyOf(objectArray, objectArray.length));
                Intrinsics.checkNotNullExpressionValue((Object)string6, (String)"format(...)");
                string3 = string6;
            }
            catch (Throwable second) {
                string3 = template;
            }
            string = string3;
        }
        return string;
    }

    @JvmStatic
    public static final boolean has(@Nullable String text) {
        if (text == null || ((CharSequence)text).length() == 0) {
            return false;
        }
        String code = ClientLanguage.code();
        if (Intrinsics.areEqual((Object)code, (Object)"ru")) {
            return true;
        }
        if (!Intrinsics.areEqual((Object)loadedCode, (Object)code)) {
            INSTANCE.reload(code);
        }
        return table.containsKey(text);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void reload(String code) {
        I18n i18n = this;
        synchronized (i18n) {
            boolean bl = false;
            if (Intrinsics.areEqual((Object)loadedCode, (Object)code)) {
                return;
            }
            table = INSTANCE.read(code);
            loadedCode = code;
            Unit unit = Unit.INSTANCE;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final HashMap<String, String> read(String code) {
        HashMap<String, String> result = new HashMap<String, String>(4096);
        try {
            InputStream inputStream = I18n.class.getResourceAsStream("/assets/kimiko/lang/" + code + ".json");
            if (inputStream == null) {
                return result;
            }
            InputStream stream = inputStream;
            Closeable closeable = stream;
            Throwable throwable = null;
            try {
                InputStream input = (InputStream)closeable;
                boolean bl = false;
                Closeable closeable2 = new InputStreamReader(input, StandardCharsets.UTF_8);
                Throwable throwable2 = null;
                try {
                    InputStreamReader reader = (InputStreamReader)closeable2;
                    boolean bl2 = false;
                    JsonElement root = JsonParser.parseReader((Reader)reader);
                    if (!root.isJsonObject()) {
                        HashMap<String, String> hashMap = result;
                        HashMap<String, String> hashMap2 = hashMap;
                        return hashMap2;
                    }
                    JsonObject jsonObject = root.getAsJsonObject();
                    Intrinsics.checkNotNullExpressionValue((Object)jsonObject, (String)"getAsJsonObject(...)");
                    JsonObject json = jsonObject;
                    for (Map.Entry entry : json.entrySet()) {
                        Intrinsics.checkNotNull((Object)entry);
                        String key = (String)entry.getKey();
                        JsonElement value = (JsonElement)entry.getValue();
                        if (!value.isJsonPrimitive()) continue;
                        String translated = value.getAsString();
                        Intrinsics.checkNotNull((Object)translated);
                        if (!(((CharSequence)translated).length() > 0)) continue;
                        ((Map)result).put(key, translated);
                    }
                    Unit unit = Unit.INSTANCE;
                }
                catch (Throwable throwable3) {
                    throwable2 = throwable3;
                    throw throwable3;
                }
                finally {
                    CloseableKt.closeFinally((Closeable)closeable2, (Throwable)throwable2);
                }
                Unit unit = Unit.INSTANCE;
            }
            catch (Throwable throwable4) {
                throwable = throwable4;
                throw throwable4;
            }
            finally {
                CloseableKt.closeFinally((Closeable)closeable, (Throwable)throwable);
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return result;
    }
}

