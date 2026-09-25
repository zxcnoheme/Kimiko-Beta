/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.fabricmc.loader.api.FabricLoader
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.profile;

import fun.shape.profile.Profile;
import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\r\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\r\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u000eJ!\u0010\u0011\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0011\u0010\u0013\u001a\u0004\u0018\u00010\bH\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0015\u0010\u0014J\u0011\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u0019\u001a\u0004\u0018\u00010\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0019\u0010\u0014R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b!\u0010 R\u0014\u0010\"\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010 R\u0014\u0010#\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b#\u0010 R\u0018\u0010$\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010&\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010\u001e\u00a8\u0006'"}, d2={"Lrtx/kimiko/utils/profile/ProfileIdentity;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "development", "()Z", "", "fallback", "username", "(Ljava/lang/String;)Ljava/lang/String;", "", "uid", "()I", "property", "env", "setting", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "minecraftName", "()Ljava/lang/String;", "token", "Ljava/lang/reflect/Field;", "resolveTokenField", "()Ljava/lang/reflect/Field;", "avatarUrl", "", "TOKEN_FIELDS", "[Ljava/lang/String;", "DEV", "Z", "DEV_NAME_PROPERTY", "Ljava/lang/String;", "DEV_NAME_ENV", "DEV_UID_PROPERTY", "DEV_UID_ENV", "tokenField", "Ljava/lang/reflect/Field;", "tokenResolved", "rtx.kimiko:kimiko"})
public final class ProfileIdentity {
    @NotNull
    public static final ProfileIdentity INSTANCE = new ProfileIdentity();
    @NotNull
    private static final String[] TOKEN_FIELDS;
    private static final boolean DEV;
    @NotNull
    private static final String DEV_NAME_PROPERTY = "kimiko.dev.name";
    @NotNull
    private static final String DEV_NAME_ENV = "KIMIKO_DEV_NAME";
    @NotNull
    private static final String DEV_UID_PROPERTY = "kimiko.dev.uid";
    @NotNull
    private static final String DEV_UID_ENV = "KIMIKO_DEV_UID";
    @Nullable
    private static volatile Field tokenField;
    private static volatile boolean tokenResolved;

    private ProfileIdentity() {
    }

    @JvmStatic
    public static final boolean development() {
        return DEV;
    }

    @JvmStatic
    @Nullable
    public static final String username(@Nullable String fallback) {
        if (DEV) {
            String pinned = INSTANCE.setting(DEV_NAME_PROPERTY, DEV_NAME_ENV);
            if (pinned != null) {
                return pinned;
            }
            String local = INSTANCE.minecraftName();
            String string = local;
            if (string == null) {
                string = fallback;
            }
            return string;
        }
        String name = Profile.getUsername();
        CharSequence charSequence = name;
        if (!(charSequence == null || StringsKt.isBlank((CharSequence)charSequence)) && !StringsKt.equals((String)"Guest", (String)name, (boolean)true)) {
            return name;
        }
        return fallback;
    }

    @JvmStatic
    public static final int uid() {
        if (DEV) {
            String pinned = INSTANCE.setting(DEV_UID_PROPERTY, DEV_UID_ENV);
            if (pinned != null) {
                try {
                    return Math.max(0, Integer.parseInt(pinned));
                }
                catch (NumberFormatException numberFormatException) {
                    // empty catch block
                }
            }
            return 0;
        }
        return Math.max(0, Profile.getUid());
    }

    private final String setting(String property, String env) {
        CharSequence value = System.getProperty(property, "");
        CharSequence charSequence = value;
        if (!(charSequence == null || StringsKt.isBlank((CharSequence)charSequence))) {
            return ((Object)StringsKt.trim((CharSequence)value)).toString();
        }
        try {
            charSequence = System.getenv(env);
        }
        catch (Throwable ignored) {
            charSequence = null;
        }
        value = charSequence;
        charSequence = value;
        return !(charSequence == null || StringsKt.isBlank((CharSequence)charSequence)) ? ((Object)StringsKt.trim((CharSequence)value)).toString() : null;
    }

    private final String minecraftName() {
        try {
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient minecraft = minecraftClient2;
            if (minecraft.getSession() != null) {
                String string = minecraft.getSession().getUsername();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getName(...)");
                String name = string;
                if (!StringsKt.isBlank((CharSequence)name)) {
                    return ((Object)StringsKt.trim((CharSequence)name)).toString();
                }
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return null;
    }

    @JvmStatic
    @NotNull
    public static final String token() {
        String string;
        String property = System.getProperty("kimiko.auth.token", "");
        CharSequence charSequence = property;
        if (!(charSequence == null || StringsKt.isBlank((CharSequence)charSequence))) {
            return ((Object)StringsKt.trim((CharSequence)property)).toString();
        }
        Field field = INSTANCE.resolveTokenField();
        if (field == null) {
            return "";
        }
        Field field2 = field;
        try {
            Object value = field2.get(null);
            string = value instanceof String && !StringsKt.isBlank((CharSequence)((CharSequence)value)) ? ((Object)StringsKt.trim((CharSequence)((String)value))).toString() : "";
        }
        catch (Throwable ignored) {
            string = "";
        }
        return string;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private final Field resolveTokenField() {
        if (tokenResolved) {
            return tokenField;
        }
        Class<ProfileIdentity> clazz = ProfileIdentity.class;
        synchronized (clazz) {
            boolean bl = false;
            if (tokenResolved) {
                return tokenField;
            }
            tokenResolved = true;
            String[] stringArray = TOKEN_FIELDS;
            int n = 0;
            int n2 = stringArray.length;
            while (n < n2) {
                String name = stringArray[n];
                try {
                    Field field = Profile.class.getDeclaredField(name);
                    if (Intrinsics.areEqual(String.class, field.getType())) {
                        field.setAccessible(true);
                        tokenField = field;
                        return tokenField;
                    }
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
                ++n;
            }
            return null;
        }
    }

    @JvmStatic
    @Nullable
    public static final String avatarUrl() {
        String url = Profile.getAvatarUrl();
        CharSequence charSequence = url;
        if (!(charSequence == null || StringsKt.isBlank((CharSequence)charSequence))) {
            return url;
        }
        int id = ProfileIdentity.uid();
        if (id > 0) {
            return "https://kimiko.tech/api/account/avatar/" + id;
        }
        return null;
    }

    static {
        String[] stringArray = new String[]{"token", "authToken", "accessToken", "cfgToken"};
        TOKEN_FIELDS = stringArray;
        DEV = FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}

