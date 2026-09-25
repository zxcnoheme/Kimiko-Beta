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
 *  net.minecraft.client.session.Session
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.crash;

import com.google.gson.JsonObject;
import fun.shape.profile.Profile;
import fun.shape.profile.Role;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.session.Session;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.profile.ProfileIdentity;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001.B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\n\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ!\u0010\u000f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J'\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0018\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u0019J\u000f\u0010\u001c\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u0019J\u000f\u0010\u001d\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u0019J\u0011\u0010\u001e\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u0019J\u001f\u0010\"\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0014\u0010'\u001a\u00020&8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020&8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010(R\u0014\u0010*\u001a\u00020&8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010(R\u0014\u0010,\u001a\u00020+8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010-\u00a8\u0006/"}, d2={"Lrtx/kimiko/utils/crash/CrashReporter;", "", "<init>", "()V", "", "title", "", "throwable", "", "Lkotlin/jvm/JvmStatic;", "report", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "json", "post", "(Ljava/lang/String;)V", "trimmedStack", "(Ljava/lang/String;Ljava/lang/Throwable;)Ljava/lang/String;", "name", "value", "", "inline", "Lcom/google/gson/JsonObject;", "field", "(Ljava/lang/String;Ljava/lang/String;Z)Lcom/google/gson/JsonObject;", "nick", "()Ljava/lang/String;", "uid", "hwid", "role", "mcNick", "mcVersion", "Lrtx/kimiko/utils/crash/CrashReporter$Sup;", "supplier", "fallback", "safe", "(Lrtx/kimiko/utils/crash/CrashReporter$Sup;Ljava/lang/String;)Ljava/lang/String;", "WEBHOOK_B64", "Ljava/lang/String;", "", "MAX_CAUSES", "I", "MAX_FRAMES", "MAX_STACK_CHARS", "Ljava/util/concurrent/atomic/AtomicBoolean;", "SENT", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Sup", "rtx.kimiko:kimiko"})
public final class CrashReporter {
    @NotNull
    public static final CrashReporter INSTANCE = new CrashReporter();
    @NotNull
    private static final String WEBHOOK_B64 = "";
    private static final int MAX_CAUSES = 4;
    private static final int MAX_FRAMES = 12;
    private static final int MAX_STACK_CHARS = 1600;
    @NotNull
    private static final AtomicBoolean SENT = new AtomicBoolean(false);

    private CrashReporter() {
    }

    @JvmStatic
    public static final void report(@Nullable String string, @Nullable Throwable throwable) {
    }

    private final void post(String string) {
    }

    private final String trimmedStack(String title, Throwable throwable) {
        StringBuilder sb = new StringBuilder();
        CharSequence charSequence = title;
        if (!(charSequence == null || StringsKt.isBlank((CharSequence)charSequence))) {
            sb.append(title).append('\n');
        }
        Throwable current = throwable;
        for (int depth = 0; current != null && depth < 4; current = current.getCause(), ++depth) {
            if (depth > 0) {
                sb.append("Caused by: ");
            }
            sb.append(current.getClass().getName());
            String message = current.getMessage();
            CharSequence charSequence2 = message;
            if (!(charSequence2 == null || charSequence2.length() == 0)) {
                sb.append(": ").append(message);
            }
            sb.append('\n');
            StackTraceElement[] stack = current.getStackTrace();
            int frames = Math.min(stack.length, 12);
            for (int i = 0; i < frames; ++i) {
                sb.append("    at ").append(stack[i]).append('\n');
            }
            if (stack.length <= frames) continue;
            sb.append("    ... ещё ").append(stack.length - frames).append('\n');
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        String result = string.replace("```", "'''");
        if (result.length() > 1600) {
            String string2 = result.substring(0, 1600);
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
            result = string2 + "\n…(обрезано)";
        }
        return result;
    }

    private final JsonObject field(String name, String value, boolean inline) {
        JsonObject o = new JsonObject();
        o.addProperty("name", name);
        o.addProperty("value", value);
        o.addProperty("inline", Boolean.valueOf(inline));
        return o;
    }

    private final String nick() {
        String profileName = ProfileIdentity.username(null);
        if (profileName != null) {
            return profileName;
        }
        return this.mcNick();
    }

    private final String uid() {
        int uid = ProfileIdentity.uid();
        return uid > 0 ? String.valueOf(uid) : "?";
    }

    private final String hwid() {
        String string;
        String hwid = Profile.getHwid();
        CharSequence charSequence = hwid;
        if (charSequence == null || StringsKt.isBlank((CharSequence)charSequence)) {
            return "?";
        }
        if (hwid.length() > 64) {
            String string2 = hwid.substring(0, 64);
            string = string2;
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
        } else {
            string = hwid;
        }
        return string;
    }

    private final String role() {
        Role role = Profile.getRole();
        return role != null ? role.name() : "?";
    }

    private final String mcNick() {
        String name;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        Session session2 = mc.getSession();
        String string = name = session2 != null ? session2.getUsername() : null;
        if (name != null) {
            return name;
        }
        return "?";
    }

    private final String mcVersion() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        if (minecraftClient2 == null) {
            return "?";
        }
        MinecraftClient mc = minecraftClient2;
        return mc.getGameVersion();
    }

    private final String safe(Sup supplier, String fallback) {
        try {
            String v = supplier.get();
            return (v == null || StringsKt.isBlank(v)) ? fallback : v;
        }
        catch (Throwable t) {
            return fallback;
        }
    }

    private static final String report$lambda$0() {
        return INSTANCE.nick();
    }

    private static final String report$lambda$1() {
        return INSTANCE.uid();
    }

    private static final String report$lambda$2() {
        return INSTANCE.hwid();
    }

    private static final String report$lambda$3() {
        return INSTANCE.role();
    }

    private static final String report$lambda$4() {
        return INSTANCE.mcNick();
    }

    private static final String report$lambda$5() {
        return INSTANCE.mcVersion();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u00e2\u0080\u0001\u0018\u00002\u00020\u0001J\u0011\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006\u0005\u00c0\u0006\u0001"}, d2={"Lrtx/kimiko/utils/crash/CrashReporter$Sup;", "", "", "get", "()Ljava/lang/String;", "rtx.kimiko:kimiko"})
    private static interface Sup {
        @Nullable
        public String get() throws Throwable;
    }
}

