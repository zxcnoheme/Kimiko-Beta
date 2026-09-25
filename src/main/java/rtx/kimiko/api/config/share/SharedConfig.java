/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.config.share;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dBa\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012R%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u0013\u0012\b\b\u0004\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0003\u0010\u0015R%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b\u0013\u0012\b\b\u0004\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0014\u001a\u0004\b\u0004\u0010\u0015R%\u0010\u0006\u001a\u00020\u00058\u0007z\f\b\u0013\u0012\b\b\u0004\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0016\u001a\u0004\b\u0006\u0010\u0017R%\u0010\u0007\u001a\u00020\u00028\u0007z\f\b\u0013\u0012\b\b\u0004\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0014\u001a\u0004\b\u0007\u0010\u0015R%\u0010\b\u001a\u00020\u00028\u0007z\f\b\u0013\u0012\b\b\u0004\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010\u0014\u001a\u0004\b\b\u0010\u0015R%\u0010\n\u001a\u00020\t8\u0007z\f\b\u0013\u0012\b\b\u0004\u0012\u0004\b\b(\n\u00a2\u0006\f\n\u0004\b\n\u0010\u0018\u001a\u0004\b\n\u0010\u0019R%\u0010\u000b\u001a\u00020\u00058\u0007z\f\b\u0013\u0012\b\b\u0004\u0012\u0004\b\b(\u000b\u00a2\u0006\f\n\u0004\b\u000b\u0010\u0016\u001a\u0004\b\u000b\u0010\u0017R%\u0010\r\u001a\u00020\f8\u0007z\f\b\u0013\u0012\b\b\u0004\u0012\u0004\b\b(\r\u00a2\u0006\f\n\u0004\b\r\u0010\u001a\u001a\u0004\b\r\u0010\u001bR%\u0010\u000e\u001a\u00020\f8\u0007z\f\b\u0013\u0012\b\b\u0004\u0012\u0004\b\b(\u000e\u00a2\u0006\f\n\u0004\b\u000e\u0010\u001a\u001a\u0004\b\u000e\u0010\u001bR%\u0010\u000f\u001a\u00020\u00058\u0007z\f\b\u0013\u0012\b\b\u0004\u0012\u0004\b\b(\u000f\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0016\u001a\u0004\b\u000f\u0010\u0017R%\u0010\u0010\u001a\u00020\u00028\u0007z\f\b\u0013\u0012\b\b\u0004\u0012\u0004\b\b(\u0010\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0014\u001a\u0004\b\u0010\u0010\u0015R\u001f\u0010\u001c\u001a\u00020\t8Gz\f\b\u0013\u0012\b\b\u0004\u0012\u0004\b\b(\u001c\u00a2\u0006\u0006\u001a\u0004\b\u001c\u0010\u0019\u00a8\u0006\u001e"}, d2={"Lrtx/kimiko/api/config/share/SharedConfig;", "", "", "code", "name", "", "ownerUid", "ownerName", "ownerAvatar", "", "premium", "modules", "", "createdAt", "updatedAt", "downloads", "payload", "<init>", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;ZIJJILjava/lang/String;)V", "Lkotlin/jvm/JvmName;", "Ljava/lang/String;", "()Ljava/lang/String;", "I", "()I", "Z", "()Z", "J", "()J", "hasPayload", "Companion", "rtx.kimiko:kimiko"})
public final class SharedConfig {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String code;
    @NotNull
    private final String name;
    private final int ownerUid;
    @NotNull
    private final String ownerName;
    @NotNull
    private final String ownerAvatar;
    private final boolean premium;
    private final int modules;
    private final long createdAt;
    private final long updatedAt;
    private final int downloads;
    @NotNull
    private final String payload;

    private SharedConfig(String code, String name, int ownerUid, String ownerName, String ownerAvatar, boolean premium, int modules, long createdAt, long updatedAt, int downloads, String payload) {
        this.code = code;
        this.name = name;
        this.ownerUid = ownerUid;
        this.ownerName = ownerName;
        this.ownerAvatar = ownerAvatar;
        this.premium = premium;
        this.modules = modules;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.downloads = downloads;
        this.payload = payload;
    }

    @JvmName(name="code")
    @NotNull
    public final String code() {
        return this.code;
    }

    @JvmName(name="name")
    @NotNull
    public final String name() {
        return this.name;
    }

    @JvmName(name="ownerUid")
    public final int ownerUid() {
        return this.ownerUid;
    }

    @JvmName(name="ownerName")
    @NotNull
    public final String ownerName() {
        return this.ownerName;
    }

    @JvmName(name="ownerAvatar")
    @NotNull
    public final String ownerAvatar() {
        return this.ownerAvatar;
    }

    @JvmName(name="premium")
    public final boolean premium() {
        return this.premium;
    }

    @JvmName(name="modules")
    public final int modules() {
        return this.modules;
    }

    @JvmName(name="createdAt")
    public final long createdAt() {
        return this.createdAt;
    }

    @JvmName(name="updatedAt")
    public final long updatedAt() {
        return this.updatedAt;
    }

    @JvmName(name="downloads")
    public final int downloads() {
        return this.downloads;
    }

    @JvmName(name="payload")
    @NotNull
    public final String payload() {
        return this.payload;
    }

    @JvmName(name="hasPayload")
    public final boolean hasPayload() {
        return !StringsKt.isBlank((CharSequence)this.payload);
    }

    @JvmStatic
    @Nullable
    public static final SharedConfig parse(@Nullable JsonObject object) {
        return Companion.parse(object);
    }

    public /* synthetic */ SharedConfig(String code, String name, int ownerUid, String ownerName, String ownerAvatar, boolean premium, int modules, long createdAt, long updatedAt, int downloads, String payload, DefaultConstructorMarker $constructor_marker) {
        this(code, name, ownerUid, ownerName, ownerAvatar, premium, modules, createdAt, updatedAt, downloads, payload);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ'\u0010\r\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ'\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/api/config/share/SharedConfig.Companion;", "", "<init>", "()V", "Lcom/google/gson/JsonObject;", "object", "Lrtx/kimiko/api/config/share/SharedConfig;", "Lkotlin/jvm/JvmStatic;", "parse", "(Lcom/google/gson/JsonObject;)Lrtx/kimiko/api/config/share/SharedConfig;", "", "key", "fallback", "string", "(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "", "number", "(Lcom/google/gson/JsonObject;Ljava/lang/String;J)J", "", "bool", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Z", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final SharedConfig parse(@Nullable JsonObject object) {
            if (object == null) {
                return null;
            }
            String code = this.string(object, "code", "");
            if (StringsKt.isBlank((CharSequence)code)) {
                return null;
            }
            return new SharedConfig(code, this.string(object, "name", "Config"), (int)this.number(object, "ownerUid", 0L), this.string(object, "ownerName", "Player"), this.string(object, "ownerAvatar", ""), this.bool(object, "premium"), (int)this.number(object, "modules", 0L), this.number(object, "createdAt", 0L), this.number(object, "updatedAt", 0L), (int)this.number(object, "downloads", 0L), this.string(object, "payload", ""), null);
        }

        private final String string(JsonObject object, String key, String fallback) {
            String string;
            JsonElement value = object.get(key);
            if (value != null && value.isJsonPrimitive()) {
                String string2 = value.getAsString();
                string = string2;
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getAsString(...)");
            } else {
                string = fallback;
            }
            return string;
        }

        private final long number(JsonObject object, String key, long fallback) {
            JsonElement value = object.get(key);
            if (value == null || !value.isJsonPrimitive() || !value.getAsJsonPrimitive().isNumber()) {
                return fallback;
            }
            return value.getAsLong();
        }

        private final boolean bool(JsonObject object, String key) {
            boolean bl;
            JsonElement value = object.get(key);
            try {
                bl = value != null && value.isJsonPrimitive() && value.getAsBoolean();
            }
            catch (Exception ex) {
                bl = false;
            }
            return bl;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

