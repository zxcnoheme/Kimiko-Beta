/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.ArraysKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.network.ClientPlayNetworkHandler
 *  net.minecraft.client.network.ServerInfo
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.restrict;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ServerInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.liteapi.FeatureBlocklist;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.impl.Interface.NotificationsModule;
import rtx.kimiko.api.modules.restrict.Server;
import rtx.kimiko.api.modules.restrict.ServerRestrictions;
import rtx.kimiko.api.modules.restrict.ServerRule;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001,\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0010J-\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\u0010\t\u001a\u0004\u0018\u00010\b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0016\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0016\u0010\u0017J+\u0010\u0018\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0018\u0010\u0019J+\u0010\u001a\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001a\u0010\u0019J\u001b\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u0013H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001d\u0010\u001eJ'\u0010\u001f\u001a\u00020\u001c2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0013H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001f\u0010 J\u001d\u0010#\u001a\u00020\u00132\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0!H\u0002\u00a2\u0006\u0004\b#\u0010$J\u0019\u0010&\u001a\u00020\u00132\b\u0010%\u001a\u0004\u0018\u00010\u0013H\u0002\u00a2\u0006\u0004\b&\u0010'R\u0014\u0010)\u001a\u00020(8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020(8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010*R\u0014\u0010-\u001a\u00020,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u001c\u0010/\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0016\u00101\u001a\u00020(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b1\u0010*R\u0016\u00102\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0016\u00104\u001a\u00020(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u0010*R\u001a\u00105\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00100R$\u00108\u001a\u0012\u0012\u0004\u0012\u00020\u001306j\b\u0012\u0004\u0012\u00020\u0013`78\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00109\u00a8\u0006:"}, d2={"Lrtx/kimiko/api/modules/restrict/ServerRestrictions;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "onRemoteServer", "()Z", "Lrtx/kimiko/api/modules/Module;", "module", "Lrtx/kimiko/api/modules/restrict/ServerRule;", "ruleOf", "(Lrtx/kimiko/api/modules/Module;)Lrtx/kimiko/api/modules/restrict/ServerRule;", "Ljava/util/EnumSet;", "Lrtx/kimiko/api/modules/restrict/Server;", "current", "()Ljava/util/EnumSet;", "computeCurrent", "here", "", "blockReason", "(Lrtx/kimiko/api/modules/Module;Ljava/util/EnumSet;)Ljava/lang/String;", "isBlocked", "(Lrtx/kimiko/api/modules/Module;)Z", "isBlockedBy", "(Lrtx/kimiko/api/modules/Module;Ljava/util/EnumSet;)Z", "isHiddenBy", "message", "", "notify", "(Ljava/lang/String;)V", "notifyOnce", "(Lrtx/kimiko/api/modules/Module;Ljava/lang/String;)V", "", "servers", "displayList", "([Lrtx/kimiko/api/modules/restrict/Server;)Ljava/lang/String;", "value", "normalize", "(Ljava/lang/String;)Ljava/lang/String;", "", "SESSION_GRACE_MS", "J", "CURRENT_CACHE_MS", "rtx/kimiko/api/modules/restrict/ServerRestrictions$RULE_CACHE$1", "RULE_CACHE", "Lrtx/kimiko/api/modules/restrict/ServerRestrictions$RULE_CACHE$1;", "cachedCurrent", "Ljava/util/EnumSet;", "cachedCurrentMs", "sessionKey", "Ljava/lang/String;", "sessionSeenMs", "sessionServers", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "notified", "Ljava/util/HashSet;", "rtx.kimiko:kimiko"})
public final class ServerRestrictions {
    @NotNull
    public static final ServerRestrictions INSTANCE = new ServerRestrictions();
    private static final long SESSION_GRACE_MS = 20000L;
    private static final long CURRENT_CACHE_MS = 250L;
    @NotNull
    private static final ClassValue<ServerRule[]> RULE_CACHE = new ClassValue<ServerRule[]>(){

        protected ServerRule[] computeValue(Class<?> type) {
            ServerRule[] serverRuleArray;
            Intrinsics.checkNotNullParameter(type, (String)"type");
            ServerRule rule = type.getAnnotation(ServerRule.class);
            if (rule == null) {
                serverRuleArray = new ServerRule[]{};
            } else {
                ServerRule[] serverRuleArray2 = new ServerRule[]{rule};
                serverRuleArray = serverRuleArray2;
            }
            return serverRuleArray;
        }
    };
    @NotNull
    private static EnumSet<Server> cachedCurrent;
    private static long cachedCurrentMs;
    @NotNull
    private static String sessionKey;
    private static long sessionSeenMs;
    @NotNull
    private static final EnumSet<Server> sessionServers;
    @NotNull
    private static final HashSet<String> notified;

    private ServerRestrictions() {
    }

    @JvmStatic
    public static final boolean onRemoteServer() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        if (minecraftClient2 == null) {
            return false;
        }
        MinecraftClient mc = minecraftClient2;
        if (mc.isInSingleplayer() || mc.isIntegratedServerRunning()) {
            return false;
        }
        return mc.getNetworkHandler() != null;
    }

    private final ServerRule ruleOf(Module module) {
        Object t = RULE_CACHE.get(module.getClass());
        Intrinsics.checkNotNullExpressionValue(t, (String)"get(...)");
        return (ServerRule)ArraysKt.firstOrNull((Object[])((Object[])t));
    }

    @JvmStatic
    @NotNull
    public static final EnumSet<Server> current() {
        long now = System.currentTimeMillis();
        long l = now - cachedCurrentMs;
        boolean bl = 0L <= l ? l < 250L : false;
        if (bl) {
            return cachedCurrent;
        }
        cachedCurrentMs = now;
        cachedCurrent = INSTANCE.computeCurrent();
        return cachedCurrent;
    }

    private final EnumSet<Server> computeCurrent() {
        EnumSet<Server> enumSet = EnumSet.noneOf(Server.class);
        Intrinsics.checkNotNullExpressionValue(enumSet, (String)"noneOf(...)");
        EnumSet<Server> matched = enumSet;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        if (minecraftClient2 == null) {
            return matched;
        }
        MinecraftClient mc = minecraftClient2;
        if (!ServerRestrictions.onRemoteServer()) {
            return matched;
        }
        String ip = "";
        String brand = "";
        ClientPlayNetworkHandler connection = mc.getNetworkHandler();
        if (connection != null) {
            ServerInfo data = connection.getServerInfo();
            if (data != null && data.address != null) {
                String string = data.address;
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"ip");
                ip = string;
            }
            brand = this.normalize(connection.getBrand());
        }
        if (((CharSequence)ip).length() == 0 && mc.getCurrentServerEntry() != null) {
            ServerInfo serverInfo2 = mc.getCurrentServerEntry();
            if ((serverInfo2 != null ? serverInfo2.address : null) != null) {
                ServerInfo serverInfo3 = mc.getCurrentServerEntry();
                Intrinsics.checkNotNull((Object)serverInfo3);
                String string = serverInfo3.address;
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"ip");
                ip = string;
            }
        }
        String string = ip.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toLowerCase(...)");
        String ipLower = ((Object)StringsKt.trim((CharSequence)string)).toString();
        String ipNorm = this.normalize(ipLower);
        if (((CharSequence)ipLower).length() == 0 && ((CharSequence)brand).length() == 0) {
            EnumSet<Server> enumSet2 = EnumSet.copyOf(sessionServers);
            Intrinsics.checkNotNullExpressionValue(enumSet2, (String)"copyOf(...)");
            return enumSet2;
        }
        for (Server server : Server.getEntries()) {
            if (!server.matches(ipLower, ipNorm, brand)) continue;
            matched.add(server);
        }
        String key = ((CharSequence)ipLower).length() == 0 ? brand : ipLower;
        long now = System.currentTimeMillis();
        if (!Intrinsics.areEqual((Object)key, (Object)sessionKey) || now - sessionSeenMs > 20000L) {
            sessionKey = key;
            notified.clear();
            sessionServers.clear();
            sessionServers.addAll((Collection<Server>)matched);
        } else if (!((Collection)matched).isEmpty()) {
            sessionServers.clear();
            sessionServers.addAll((Collection<Server>)matched);
        } else {
            matched.addAll((Collection<Server>)sessionServers);
        }
        sessionSeenMs = now;
        return matched;
    }

    @JvmStatic
    @Nullable
    public static final String blockReason(@Nullable Module module, @NotNull EnumSet<Server> here) {
        Intrinsics.checkNotNullParameter(here, (String)"here");
        if (module == null) {
            return null;
        }
        if (!ServerRestrictions.onRemoteServer()) {
            return null;
        }
        if (FeatureBlocklist.isModuleBlocked(module)) {
            Object[] objectArray = new Object[]{module.getName()};
            return I18n.tr("%s заблокирован сервером", objectArray);
        }
        return null;
    }

    @JvmStatic
    public static final boolean isBlocked(@Nullable Module module) {
        return ServerRestrictions.blockReason(module, ServerRestrictions.current()) != null;
    }

    @JvmStatic
    public static final boolean isBlockedBy(@Nullable Module module, @NotNull EnumSet<Server> here) {
        Intrinsics.checkNotNullParameter(here, (String)"here");
        return ServerRestrictions.blockReason(module, here) != null;
    }

    @JvmStatic
    public static final boolean isHiddenBy(@Nullable Module module, @NotNull EnumSet<Server> here) {
        Intrinsics.checkNotNullParameter(here, (String)"here");
        return false;
    }

    @JvmStatic
    public static final void notify(@NotNull String message) {
        Intrinsics.checkNotNullParameter((Object)message, (String)"message");
        NotificationsModule.Companion.notify(message, 2500L);
    }

    @JvmStatic
    public static final void notifyOnce(@Nullable Module module, @Nullable String message) {
        if (module == null || message == null) {
            return;
        }
        if (!notified.add(module.getName() + "|" + message)) {
            return;
        }
        ServerRestrictions.notify(message);
    }

    private final String displayList(Server[] servers) {
        StringBuilder sb = new StringBuilder();
        int n = servers.length;
        for (int i = 0; i < n; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(servers[i].display());
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        return string;
    }

    private final String normalize(String value) {
        if (value == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(value.length());
        int n = ((CharSequence)value).length();
        for (int i = 0; i < n; ++i) {
            char c = Character.toLowerCase(value.charAt(i));
            boolean bl = 'a' <= c ? c < '{' : false;
            if (!bl) continue;
            sb.append(c);
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        return string;
    }

    static {
        EnumSet<Server> enumSet = EnumSet.noneOf(Server.class);
        Intrinsics.checkNotNullExpressionValue(enumSet, (String)"noneOf(...)");
        cachedCurrent = enumSet;
        sessionKey = "";
        EnumSet<Server> enumSet2 = EnumSet.noneOf(Server.class);
        Intrinsics.checkNotNullExpressionValue(enumSet2, (String)"noneOf(...)");
        sessionServers = enumSet2;
        notified = new HashSet();
    }
}

