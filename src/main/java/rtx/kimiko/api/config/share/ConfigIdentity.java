/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.config.share;

import com.google.gson.JsonObject;
import java.util.concurrent.ThreadLocalRandom;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.profile.ProfileIdentity;
import rtx.kimiko.utils.storage.RepositoryStorage;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\nJ\u0013\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000e\u0010\rJ\u0013\u0010\u000f\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000f\u0010\rJ\u000f\u0010\u0010\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0010\u0010\nR\u0014\u0010\u0011\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0014R\u0016\u0010\u0010\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0014\u00a8\u0006\u0016"}, d2={"Lrtx/kimiko/api/config/share/ConfigIdentity;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "fromProfile", "()Z", "", "uid", "()I", "", "username", "()Ljava/lang/String;", "token", "avatarUrl", "localUid", "STORE", "Ljava/lang/String;", "LOCAL_UID_MIN", "I", "LOCAL_UID_MAX", "rtx.kimiko:kimiko"})
public final class ConfigIdentity {
    @NotNull
    public static final ConfigIdentity INSTANCE = new ConfigIdentity();
    @NotNull
    private static final String STORE = "configid";
    private static final int LOCAL_UID_MIN = 1000000000;
    private static final int LOCAL_UID_MAX = 2000000000;
    private static volatile int localUid;

    private ConfigIdentity() {
    }

    @JvmStatic
    public static final boolean fromProfile() {
        return ProfileIdentity.uid() > 0;
    }

    @JvmStatic
    public static final int uid() {
        int profile = ProfileIdentity.uid();
        return profile > 0 ? profile : INSTANCE.localUid();
    }

    @JvmStatic
    @NotNull
    public static final String username() {
        String profile;
        String string = ProfileIdentity.username("");
        if (string == null) {
            string = "";
        }
        if (!StringsKt.isBlank((CharSequence)(profile = string))) {
            return profile;
        }
        try {
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient minecraft = minecraftClient2;
            if (minecraft.getSession() != null) {
                String string2 = minecraft.getSession().getUsername();
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getName(...)");
                String name = string2;
                if (!StringsKt.isBlank((CharSequence)name)) {
                    return name;
                }
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return "Player";
    }

    @JvmStatic
    @NotNull
    public static final String token() {
        String string;
        try {
            string = ProfileIdentity.token();
        }
        catch (Throwable ignored) {
            string = "";
        }
        return string;
    }

    @JvmStatic
    @NotNull
    public static final String avatarUrl() {
        String url = ProfileIdentity.avatarUrl();
        String string = url;
        if (string == null) {
            string = "";
        }
        return string;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final int localUid() {
        int cached = localUid;
        if (cached > 0) {
            return cached;
        }
        ConfigIdentity configIdentity = this;
        synchronized (configIdentity) {
            JsonObject root2;
            block11: {
                boolean bl = false;
                if (localUid <= 0) break block11;
                int n = localUid;
                return n;
            }
            int stored = 0;
            try {
                root2 = RepositoryStorage.readObject(STORE);
                if (root2.has("uid") && root2.get("uid").isJsonPrimitive()) {
                    stored = root2.get("uid").getAsInt();
                }
            }
            catch (Exception ignored) {
                // empty catch block
            }
            if (stored < 1000000000 || stored >= 2000000000) {
                stored = ThreadLocalRandom.current().nextInt(1000000000, 2000000000);
                try {
                    root2 = new JsonObject();
                    root2.addProperty("uid", (Number)stored);
                    RepositoryStorage.write(STORE, root2);
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
            localUid = stored;
            int n = stored;
            return n;
        }
    }
}

