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
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.config.share;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Locale;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001f\u0010\u000b\u001a\u0004\u0018\u00010\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u000e\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0013"}, d2={"Lrtx/kimiko/api/config/share/ConfigCodec;", "", "<init>", "()V", "Lcom/google/gson/JsonObject;", "root", "", "Lkotlin/jvm/JvmStatic;", "encode", "(Lcom/google/gson/JsonObject;)Ljava/lang/String;", "payload", "decode", "(Ljava/lang/String;)Lcom/google/gson/JsonObject;", "input", "normalizeCode", "(Ljava/lang/String;)Ljava/lang/String;", "", "MAX_DECODED_BYTES", "I", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nConfigCodec.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConfigCodec.kt\nrtx/kimiko/api/config/share/ConfigCodec\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,80:1\n1#2:81\n*E\n"})
public final class ConfigCodec {
    @NotNull
    public static final ConfigCodec INSTANCE = new ConfigCodec();
    private static final int MAX_DECODED_BYTES = 0x400000;

    private ConfigCodec() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    @Nullable
    public static final String encode(@Nullable JsonObject root) {
        String string;
        if (root == null) {
            return null;
        }
        try {
            String string2 = root.toString();
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toString(...)");
            String string3 = string2;
            Charset charset = StandardCharsets.UTF_8;
            Intrinsics.checkNotNullExpressionValue((Object)charset, (String)"UTF_8");
            byte[] byArray = string3.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"getBytes(...)");
            byte[] raw = byArray;
            ByteArrayOutputStream out = new ByteArrayOutputStream(Math.max(64, raw.length / 4));
            Closeable closeable = new GZIPOutputStream(out);
            Throwable throwable = null;
            try {
                GZIPOutputStream gzip = (GZIPOutputStream)closeable;
                boolean bl = false;
                gzip.write(raw);
                Unit unit = Unit.INSTANCE;
            }
            catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            }
            finally {
                CloseableKt.closeFinally((Closeable)closeable, (Throwable)throwable);
            }
            string = Base64.getUrlEncoder().withoutPadding().encodeToString(out.toByteArray());
        }
        catch (Exception ex) {
            string = null;
        }
        return string;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    @Nullable
    public static final JsonObject decode(@Nullable String payload) {
        JsonObject jsonObject;
        if (payload == null || StringsKt.isBlank((CharSequence)payload)) {
            return null;
        }
        try {
            byte[] compressed = Base64.getUrlDecoder().decode(((Object)StringsKt.trim((CharSequence)payload)).toString());
            ByteArrayOutputStream out = new ByteArrayOutputStream(Math.max(64, compressed.length * 4));
            byte[] buffer = new byte[8192];
            boolean overflow = false;
            Closeable closeable = new GZIPInputStream(new ByteArrayInputStream(compressed));
            Throwable throwable = null;
            try {
                block10: {
                    GZIPInputStream gzip = (GZIPInputStream)closeable;
                    boolean bl = false;
                    int read = 0;
                    do {
                        int n;
                        int it = n = gzip.read(buffer);
                        boolean bl2 = false;
                        read = it;
                        if (n <= 0) break block10;
                        out.write(buffer, 0, read);
                    } while (out.size() <= 0x400000);
                    overflow = true;
                }
                Unit unit = Unit.INSTANCE;
            }
            catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            }
            finally {
                CloseableKt.closeFinally((Closeable)closeable, (Throwable)throwable);
            }
            if (overflow) {
                return null;
            }
            JsonElement parsed = JsonParser.parseString((String)out.toString(StandardCharsets.UTF_8));
            jsonObject = parsed != null && parsed.isJsonObject() ? parsed.getAsJsonObject() : null;
        }
        catch (Exception ex) {
            jsonObject = null;
        }
        return jsonObject;
    }

    @JvmStatic
    @NotNull
    public static final String normalizeCode(@Nullable String input) {
        if (input == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder(8);
        String string = ((Object)StringsKt.trim((CharSequence)input)).toString();
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string2 = string.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toUpperCase(...)");
        char[] cArray = string2.toCharArray();
        Intrinsics.checkNotNullExpressionValue((Object)cArray, (String)"toCharArray(...)");
        for (char c : cArray) {
            if (Character.isLetterOrDigit(c)) {
                builder.append(c);
            }
            if (builder.length() >= 8) break;
        }
        String string3 = builder.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toString(...)");
        return string3;
    }
}

