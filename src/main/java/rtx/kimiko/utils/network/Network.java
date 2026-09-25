/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.text.Text
 *  net.minecraft.network.packet.Packet
 *  net.minecraft.scoreboard.ScoreboardObjective
 *  net.minecraft.scoreboard.Team
 *  net.minecraft.scoreboard.Scoreboard
 *  net.minecraft.scoreboard.AbstractTeam
 *  net.minecraft.scoreboard.ScoreboardCriterion.RenderType
 *  net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.hud.InGameHud
 *  net.minecraft.client.gui.hud.BossBarHud
 *  net.minecraft.client.gui.hud.ClientBossBar
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.network.ClientPlayNetworkHandler
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.ServerInfo
 *  net.minecraft.scoreboard.ScoreboardDisplaySlot
 *  net.minecraft.scoreboard.ScoreboardEntry
 *  net.minecraft.scoreboard.ReadableScoreboardScore
 *  net.minecraft.scoreboard.ScoreHolder
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package rtx.kimiko.utils.network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import mixin.accessor.BossHealthOverlayAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.network.packet.Packet;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.Team;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.AbstractTeam;
import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.client.gui.hud.ClientBossBar;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.scoreboard.ScoreboardDisplaySlot;
import net.minecraft.scoreboard.ScoreboardEntry;
import net.minecraft.scoreboard.ReadableScoreboardScore;
import net.minecraft.scoreboard.ScoreHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u0003\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J!\u0010\t\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0007H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u000b\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000b\u0010\u0003J\u0013\u0010\r\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u0010\u001a\u00020\u000fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0013\u0010\u0013\u001a\u00020\u0012H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u0012H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0015\u0010\u0014J\u0013\u0010\u0016\u001a\u00020\u0012H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0016\u0010\u0014J\u0013\u0010\u0017\u001a\u00020\u0012H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0017\u0010\u0014J\u0013\u0010\u0018\u001a\u00020\u0012H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0018\u0010\u0014J\u0013\u0010\u0019\u001a\u00020\u0012H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0019\u0010\u0014J\u0013\u0010\u001a\u001a\u00020\u0012H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001a\u0010\u0014J\u0013\u0010\u001b\u001a\u00020\u0012H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001b\u0010\u0014J%\u0010\u001f\u001a\u00020\f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001e\u001a\u00020\u0012H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001f\u0010 J\u0017\u0010#\u001a\u00020\f2\u0006\u0010\"\u001a\u00020!H\u0002\u00a2\u0006\u0004\b#\u0010$J\u0017\u0010'\u001a\u00020\u00122\u0006\u0010&\u001a\u00020%H\u0002\u00a2\u0006\u0004\b'\u0010(J#\u0010,\u001a\u00020+2\b\u0010)\u001a\u0004\u0018\u00010\u000f2\b\u0010*\u001a\u0004\u0018\u00010\u000fH\u0002\u00a2\u0006\u0004\b,\u0010-J\u0017\u0010/\u001a\u00020\u00122\u0006\u0010.\u001a\u00020+H\u0002\u00a2\u0006\u0004\b/\u00100J\u0017\u00102\u001a\u00020\u000f2\u0006\u00101\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b2\u00103J\u001b\u00105\u001a\u00020\u000f2\u0006\u00104\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b5\u00106J\u0013\u00107\u001a\u00020+H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b7\u00108J\u000f\u00109\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b9\u0010\u0011J\u0017\u0010<\u001a\u00020\u00042\u0006\u0010;\u001a\u00020:H\u0002\u00a2\u0006\u0004\b<\u0010=J\u0017\u0010>\u001a\u00020\u00042\u0006\u0010;\u001a\u00020:H\u0002\u00a2\u0006\u0004\b>\u0010=J\u001f\u0010A\u001a\u00020+2\u0006\u0010?\u001a\u00020\u000f2\u0006\u0010@\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\bA\u0010-J\u0019\u0010B\u001a\u00020\u000f2\b\u0010?\u001a\u0004\u0018\u00010\u000fH\u0002\u00a2\u0006\u0004\bB\u00103J\u0019\u0010C\u001a\u00020\u000f2\b\u0010?\u001a\u0004\u0018\u00010\u000fH\u0002\u00a2\u0006\u0004\bC\u00103J\u0019\u0010D\u001a\u00020\u000f2\b\u0010?\u001a\u0004\u0018\u00010\u000fH\u0002\u00a2\u0006\u0004\bD\u00103J\u000f\u0010E\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bE\u0010\u0003J\u000f\u0010F\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bF\u0010\u0003J\u000f\u0010G\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\bG\u0010\u0014J\u001d\u0010J\u001a\u00020\u00042\b\u0010I\u001a\u0004\u0018\u00010HH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\bJ\u0010KJ\u001d\u0010M\u001a\u00020\u00042\b\u0010L\u001a\u0004\u0018\u00010\u000fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\bM\u0010NR\u0014\u0010O\u001a\u00020+8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\bO\u0010PR\u001c\u0010S\u001a\n R*\u0004\u0018\u00010Q0Q8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bS\u0010TR\u0014\u0010U\u001a\u00020:8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bU\u0010VR\u0014\u0010W\u001a\u00020:8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bW\u0010VR\u0014\u0010Y\u001a\u00020X8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bY\u0010ZR\u0016\u0010[\u001a\u00020:8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b[\u0010VR\u0016\u0010\\\u001a\u00020:8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\\\u0010VR\u0016\u0010]\u001a\u00020:8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b]\u0010VR\u0016\u0010^\u001a\u00020:8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b^\u0010VR\u0016\u0010_\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b_\u0010`R\u0016\u0010a\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\ba\u0010bR\u0016\u0010c\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010d\u00a8\u0006e"}, d2={"Lrtx/kimiko/utils/network/Network;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "tick", "Lnet/minecraft/Packet;", "packet", "handlePacket", "(Lnet/minecraft/Packet;)V", "handleTimePacket", "", "getTPS", "()F", "", "getServer", "()Ljava/lang/String;", "", "isPvp", "()Z", "isCopyTime", "isFunTime", "isReallyWorld", "isGulPvP", "isHolyWorld", "isSpookyTime", "isVanilla", "Lnet/minecraft/LivingEntity;", "entity", "includeAbsorption", "getResolvedHealth", "(Lnet/minecraft/LivingEntity;Z)F", "Lnet/minecraft/PlayerEntity;", "player", "resolveServerHealth", "(Lnet/minecraft/PlayerEntity;)F", "Lnet/minecraft/ScoreboardObjective;", "objective", "looksLikeHealth", "(Lnet/minecraft/ScoreboardObjective;)Z", "raw", "playerName", "", "parseHealthFromName", "(Ljava/lang/String;Ljava/lang/String;)I", "cp", "isHeart", "(I)Z", "s", "stripSectionCodes", "(Ljava/lang/String;)Ljava/lang/String;", "hp", "formatHealthValue", "(F)Ljava/lang/String;", "getAnarchyMode", "()I", "detectServer", "", "nowMs", "refreshServer", "(J)V", "updatePvpState", "value", "marker", "extractNumberAfter", "normalizeServerToken", "clean", "safeLower", "resetRuntimeState", "resetTpsState", "isSingleplayerWorld", "", "throwable", "logConnectionFailure", "(Ljava/lang/Throwable;)V", "reason", "logDisconnect", "(Ljava/lang/String;)V", "READ_TIMEOUT_SECONDS", "I", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "CONNECTION_LOG", "Lorg/slf4j/Logger;", "SERVER_DETECT_INTERVAL_MS", "J", "PVP_SCAN_INTERVAL_MS", "Lnet/minecraft/MinecraftClient;", "MC", "Lnet/minecraft/MinecraftClient;", "lastPvpMs", "lastPvpScanMs", "lastTpsPacketNs", "lastServerDetectMs", "singleplayerWorld", "Z", "tps", "F", "server", "Ljava/lang/String;", "rtx.kimiko:kimiko"})
public final class Network {
    @NotNull
    public static final Network INSTANCE = new Network();
    public static final int READ_TIMEOUT_SECONDS = 60;
    private static final Logger CONNECTION_LOG = LoggerFactory.getLogger((String)"Kimiko/Connection");
    private static final long SERVER_DETECT_INTERVAL_MS = 1000L;
    private static final long PVP_SCAN_INTERVAL_MS = 250L;
    @NotNull
    private static final MinecraftClient MC;
    private static long lastPvpMs;
    private static long lastPvpScanMs;
    private static volatile long lastTpsPacketNs;
    private static long lastServerDetectMs;
    private static volatile boolean singleplayerWorld;
    private static volatile float tps;
    @NotNull
    private static volatile String server;

    private Network() {
    }

    @JvmStatic
    public static final void tick() {
        if (Network.MC.player == null || Network.MC.world == null || MC.getNetworkHandler() == null) {
            INSTANCE.resetRuntimeState();
            return;
        }
        singleplayerWorld = INSTANCE.isSingleplayerWorld();
        if (singleplayerWorld) {
            INSTANCE.resetTpsState();
            server = "Vanilla";
            return;
        }
        long now = System.currentTimeMillis();
        INSTANCE.refreshServer(now);
        INSTANCE.updatePvpState(now);
    }

    @JvmStatic
    public static final void handlePacket(@Nullable Packet<?> packet) {
        if (singleplayerWorld) {
            INSTANCE.resetTpsState();
            return;
        }
        if (packet instanceof WorldTimeUpdateS2CPacket) {
            Network.handleTimePacket();
        }
    }

    @JvmStatic
    public static final void handleTimePacket() {
        long now = System.nanoTime();
        if (lastTpsPacketNs != 0L) {
            float raw = 20.0f * (1.0E9f / (float)Math.max(1L, now - lastTpsPacketNs));
            tps = MathHelper.clamp((float)raw, (float)0.0f, (float)20.0f);
        }
        lastTpsPacketNs = now;
    }

    @JvmStatic
    public static final float getTPS() {
        if (Network.MC.player == null || Network.MC.world == null || MC.getNetworkHandler() == null || singleplayerWorld) {
            return 20.0f;
        }
        return tps;
    }

    @JvmStatic
    @NotNull
    public static final String getServer() {
        if (Network.MC.player == null || Network.MC.world == null || MC.getNetworkHandler() == null || singleplayerWorld) {
            return "Vanilla";
        }
        INSTANCE.refreshServer(System.currentTimeMillis());
        return server;
    }

    @JvmStatic
    public static final boolean isPvp() {
        INSTANCE.updatePvpState(System.currentTimeMillis());
        return System.currentTimeMillis() - lastPvpMs <= 500L;
    }

    @JvmStatic
    public static final boolean isCopyTime() {
        String current = Network.getServer();
        return Intrinsics.areEqual((Object)"CopyTime", (Object)current) || Intrinsics.areEqual((Object)"SpookyTime", (Object)current) || Intrinsics.areEqual((Object)"FunTime", (Object)current);
    }

    @JvmStatic
    public static final boolean isFunTime() {
        return Intrinsics.areEqual((Object)"FunTime", (Object)Network.getServer());
    }

    @JvmStatic
    public static final boolean isReallyWorld() {
        return Intrinsics.areEqual((Object)"ReallyWorld", (Object)Network.getServer());
    }

    @JvmStatic
    public static final boolean isGulPvP() {
        return Intrinsics.areEqual((Object)"GulPvP", (Object)Network.getServer());
    }

    @JvmStatic
    public static final boolean isHolyWorld() {
        return Intrinsics.areEqual((Object)"HolyWorld", (Object)Network.getServer());
    }

    @JvmStatic
    public static final boolean isSpookyTime() {
        return Intrinsics.areEqual((Object)"SpookyTime", (Object)Network.getServer());
    }

    @JvmStatic
    public static final boolean isVanilla() {
        return Intrinsics.areEqual((Object)"Vanilla", (Object)Network.getServer());
    }

    @JvmStatic
    public static final float getResolvedHealth(@Nullable LivingEntity entity, boolean includeAbsorption) {
        float serverHealth;
        if (entity == null) {
            return 0.0f;
        }
        if (entity instanceof PlayerEntity && Network.MC.player != null && entity != Network.MC.player && !singleplayerWorld && (serverHealth = INSTANCE.resolveServerHealth((PlayerEntity)entity)) >= 0.0f) {
            return serverHealth;
        }
        float health = Math.max(0.0f, entity.getHealth());
        return includeAbsorption ? health + Math.max(0.0f, entity.getAbsorptionAmount()) : health;
    }

    private final float resolveServerHealth(PlayerEntity player) {
        ReadableScoreboardScore info;
        ClientWorld clientWorld3 = Network.MC.world;
        if (clientWorld3 == null) {
            return -1.0f;
        }
        ClientWorld level = clientWorld3;
        Scoreboard scoreboard2 = level.getScoreboard();
        Intrinsics.checkNotNullExpressionValue((Object)scoreboard2, (String)"getScoreboard(...)");
        Scoreboard scoreboard = scoreboard2;
        ScoreboardObjective below = scoreboard.getObjectiveForSlot(ScoreboardDisplaySlot.BELOW_NAME);
        if (below != null && this.looksLikeHealth(below) && (info = scoreboard.getScore((ScoreHolder)player, below)) != null && info.getScore() > 0) {
            return info.getScore();
        }
        Text text2 = player.getDisplayName();
        int parsed = this.parseHealthFromName(text2 != null ? text2.getString() : null, player.getName().getString());
        return parsed >= 0 ? (float)parsed : -1.0f;
    }

    private final boolean looksLikeHealth(ScoreboardObjective objective) {
        if (objective.getRenderType() == ScoreboardCriterion.RenderType.HEARTS) {
            return true;
        }
        String string = objective.getName() + " " + objective.getDisplayName().getString();
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string2 = string.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
        String name = string2;
        if (String.valueOf(name).contains("health") || String.valueOf(name).contains("heart") || String.valueOf(name).contains("hp") || String.valueOf(name).contains("здоров") || String.valueOf(name).contains("хп") || String.valueOf(name).contains("жизн")) {
            return true;
        }
        int n = ((CharSequence)name).length();
        for (int i = 0; i < n; ++i) {
            if (!this.isHeart(name.codePointAt(i))) continue;
            return true;
        }
        return false;
    }

    private final int parseHealthFromName(String raw, String playerName) {
        int i;
        int cp;
        CharSequence charSequence = raw;
        if (charSequence == null || charSequence.length() == 0) {
            return -1;
        }
        String s = this.stripSectionCodes(raw);
        CharSequence charSequence2 = playerName;
        if (!(charSequence2 == null || charSequence2.length() == 0)) {
            s = String.valueOf(s).replace(playerName, " ");
        }
        String string = s;
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string2 = string.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
        String lower = string2;
        ArrayList<Integer> markers = new ArrayList<Integer>();
        for (i = 0; i < s.length(); i += Character.charCount(cp)) {
            cp = s.codePointAt(i);
            if (!this.isHeart(cp)) continue;
            markers.add(i);
        }
        String[] stringArray = new String[]{"здоров", "health", "heart", "хп", "hp", "жизн"};
        for (String kw : stringArray) {
            int idx = String.valueOf(lower).indexOf(kw);
            while (idx >= 0) {
                markers.add(idx);
                idx = String.valueOf(lower).indexOf(kw, (int)(idx + 1));
            }
        }
        if (markers.isEmpty()) {
            return -1;
        }
        int best = -1;
        int bestDist = Integer.MAX_VALUE;
        i = 0;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                int n;
                int start = i;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    ++i;
                }
                int center = (start + i) / 2;
                try {
                    String string3 = s.substring(start, i);
                    Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"substring(...)");
                    n = Integer.parseInt(string3);
                }
                catch (NumberFormatException ignored) {
                    continue;
                }
                int num = n;
                for (int m : markers) {
                    int d = Math.abs(center - m);
                    if (d < bestDist) {
                        bestDist = d;
                        best = num;
                    }
                }
                continue;
            }
            ++i;
        }
        return best;
    }

    private final boolean isHeart(int cp) {
        return cp == 9829 || cp == 10084 || cp == 9825 || cp == 10083 || cp == 10085 || cp == 10086 || cp == 10087 || cp == 128147 || cp == 128148 || cp == 128149 || cp == 128150 || cp == 128151 || cp == 128152 || cp == 128153 || cp == 128154 || cp == 128155 || cp == 128156 || cp == 128157 || cp == 129505 || cp == 128420 || cp == 129293 || cp == 129294;
    }

    private final String stripSectionCodes(String s) {
        StringBuilder out = new StringBuilder(s.length());
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '\u00a7' && i + 1 < s.length()) {
                i += 2;
                continue;
            }
            out.append(c);
            ++i;
        }
        String string = out.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        return string;
    }

    @JvmStatic
    @NotNull
    public static final String formatHealthValue(float hp) {
        if (hp >= 100.0f) {
            return String.valueOf((int)hp);
        }
        if (hp >= 10.0f) {
            Locale locale = Locale.ROOT;
            String string = "%.1f";
            Object[] objectArray = new Object[]{Float.valueOf(hp)};
            String string2 = String.format(locale, string, Arrays.copyOf(objectArray, objectArray.length));
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"format(...)");
            return string2;
        }
        Locale locale = Locale.ROOT;
        String string = "%.2f";
        Object[] objectArray = new Object[]{Float.valueOf(hp)};
        String string3 = String.format(locale, string, Arrays.copyOf(objectArray, objectArray.length));
        Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"format(...)");
        return string3;
    }

    @JvmStatic
    public static final int getAnarchyMode() {
        ClientWorld clientWorld3 = Network.MC.world;
        if (clientWorld3 == null) {
            return -1;
        }
        ClientWorld level = clientWorld3;
        Scoreboard scoreboard2 = level.getScoreboard();
        Intrinsics.checkNotNullExpressionValue((Object)scoreboard2, (String)"getScoreboard(...)");
        Scoreboard scoreboard = scoreboard2;
        ScoreboardObjective scoreboardObjective2 = scoreboard.getObjectiveForSlot(ScoreboardDisplaySlot.SIDEBAR);
        if (scoreboardObjective2 == null) {
            return -1;
        }
        ScoreboardObjective objective = scoreboardObjective2;
        String header = INSTANCE.clean(objective.getDisplayName().getString());
        int fromHeader = INSTANCE.extractNumberAfter(header, "#");
        if (fromHeader != -1) {
            return fromHeader;
        }
        for (Object e : scoreboard.getScoreboardEntries(objective)) {
            Intrinsics.checkNotNullExpressionValue(e, (String)"next(...)");
            ScoreboardEntry entry = (ScoreboardEntry)e;
            String row = INSTANCE.clean(Team.decorateName((AbstractTeam)((AbstractTeam)scoreboard.getScoreHolderTeam(entry.owner())), (Text)entry.name()).getString());
            int value = INSTANCE.extractNumberAfter(row, "#");
            if (value == -1) continue;
            return value;
        }
        return -1;
    }

    private final String detectServer() {
        ClientPlayNetworkHandler connection = MC.getNetworkHandler();
        if (connection == null || connection.getServerInfo() == null) {
            return "Vanilla";
        }
        ServerInfo serverInfo2 = connection.getServerInfo();
        Intrinsics.checkNotNull((Object)serverInfo2);
        String address = this.safeLower(serverInfo2.address);
        String brand = this.safeLower(connection.getBrand());
        String normalizedBrand = this.normalizeServerToken(connection.getBrand());
        if (String.valueOf(brand).contains("botfilter") || String.valueOf(normalizedBrand).contains("botfilter")) {
            return "FunTime";
        }
        if (String.valueOf(address).contains("spooky") || String.valueOf(address).contains("pookie") || String.valueOf(brand).contains("spooky") || String.valueOf(brand).contains("pookie") || String.valueOf(normalizedBrand).contains("spookycore") || String.valueOf(normalizedBrand).contains("spookytime") || String.valueOf(normalizedBrand).contains("pookietime")) {
            return "SpookyTime";
        }
        if (String.valueOf(address).contains("funtime") || String.valueOf(address).contains("skytime") || String.valueOf(address).contains("space-times") || String.valueOf(address).contains("funsky")) {
            return "CopyTime";
        }
        if (String.valueOf(brand).contains("holyworld") || String.valueOf(normalizedBrand).contains("holyworld") || String.valueOf(brand).contains("vk.com/idwok")) {
            return "HolyWorld";
        }
        if (String.valueOf(address).contains("reallyworld")) {
            return "ReallyWorld";
        }
        if (String.valueOf(address).contains("gulpvp")) {
            return "GulPvP";
        }
        return "Vanilla";
    }

    private final void refreshServer(long nowMs) {
        if (nowMs - lastServerDetectMs < 1000L) {
            return;
        }
        lastServerDetectMs = nowMs;
        server = this.detectServer();
    }

    private final void updatePvpState(long nowMs) {
        if (nowMs - lastPvpScanMs < 250L) {
            return;
        }
        lastPvpScanMs = nowMs;
        InGameHud inGameHud2 = Network.MC.inGameHud;
        if (inGameHud2 == null) {
            return;
        }
        InGameHud gui = inGameHud2;
        BossBarHud bossBarHud2 = gui.getBossBarHud();
        if (bossBarHud2 == null) {
            return;
        }
        BossBarHud overlay = bossBarHud2;
        Map<UUID, ClientBossBar> map = ((BossHealthOverlayAccessor)overlay).kimiko$getEvents();
        Intrinsics.checkNotNullExpressionValue(map, (String)"kimiko$getEvents(...)");
        Map<UUID, ClientBossBar> events = map;
        for (ClientBossBar event : events.values()) {
            String name = this.safeLower(event.getName().getString());
            if (!String.valueOf(name).contains("pvp") && !String.valueOf(name).contains("пвп")) continue;
            lastPvpMs = nowMs;
            return;
        }
    }

    private final int extractNumberAfter(String value, String marker) {
        int n;
        int markerIndex = String.valueOf(value).indexOf(marker);
        if (markerIndex == -1) {
            return -1;
        }
        int start = markerIndex + marker.length();
        StringBuilder digits = new StringBuilder();
        int n2 = value.length();
        for (int i = start; i < n2; ++i) {
            char c = value.charAt(i);
            if (Character.isDigit(c)) {
                digits.append(c);
                continue;
            }
            if (((CharSequence)digits).length() > 0) break;
        }
        if (((CharSequence)digits).length() == 0) {
            return -1;
        }
        try {
            String string = digits.toString();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
            n = Integer.parseInt(string);
        }
        catch (NumberFormatException ignored) {
            n = -1;
        }
        return n;
    }

    private final String normalizeServerToken(String value) {
        CharSequence charSequence = value;
        if (charSequence == null || charSequence.length() == 0) {
            return "";
        }
        StringBuilder out = new StringBuilder(value.length());
        boolean skip = false;
        int n = ((CharSequence)value).length();
        for (int i = 0; i < n; ++i) {
            char c = value.charAt(i);
            if (skip) {
                skip = false;
                continue;
            }
            if (c == '\u00a7') {
                skip = true;
                continue;
            }
            if (c == '\u00c2' || Character.isWhitespace(c)) continue;
            out.append(Character.toLowerCase(c));
        }
        String string = out.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        return string;
    }

    private final String clean(String value) {
        return ((Object)StringsKt.trim((CharSequence)String.valueOf(this.normalizeServerToken(value)).replace((char)'\u00a0', (char)' '))).toString();
    }

    private final String safeLower(String value) {
        String string;
        block3: {
            block2: {
                string = value;
                if (string == null) break block2;
                String string2 = string;
                Locale locale = Locale.ROOT;
                Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
                String string3 = string2.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toLowerCase(...)");
                string = string3;
                if (string3 != null) break block3;
            }
            string = "";
        }
        return string;
    }

    private final void resetRuntimeState() {
        lastPvpMs = 0L;
        lastPvpScanMs = 0L;
        lastServerDetectMs = 0L;
        singleplayerWorld = true;
        this.resetTpsState();
        server = "Vanilla";
    }

    private final void resetTpsState() {
        lastTpsPacketNs = 0L;
        tps = 20.0f;
    }

    private final boolean isSingleplayerWorld() {
        return MC.isIntegratedServerRunning() || MC.isInSingleplayer();
    }

    @JvmStatic
    public static final void logConnectionFailure(@Nullable Throwable throwable) {
        if (throwable == null) {
            return;
        }
        String string = throwable.getClass().getName();
        String string2 = throwable.getMessage();
        if (string2 == null) {
            string2 = "без сообщения";
        }
        CONNECTION_LOG.warn("обрыв связи: {}: {}", (Object)string, (Object)string2);
    }

    @JvmStatic
    public static final void logDisconnect(@Nullable String reason) {
        String string = reason;
        if (string == null) {
            string = "причина не указана";
        }
        CONNECTION_LOG.info("отключение от сервера: {}", (Object)string);
    }

    static {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MC = minecraftClient2;
        singleplayerWorld = true;
        tps = 20.0f;
        server = "Vanilla";
    }
}

