/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.Regex
 *  kotlin.text.StringsKt
 *  net.minecraft.network.packet.Packet
 *  net.minecraft.client.network.ClientPlayNetworkHandler
 *  net.minecraft.network.packet.s2c.play.GameMessageS2CPacket
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.minecraft.network.packet.Packet;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.network.PacketEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.restrict.Server;
import rtx.kimiko.api.modules.restrict.ServerRule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.MultiModeSetting;
import rtx.kimiko.api.modules.settings.impl.NumberSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.api.modules.settings.impl.TextSetting;
import rtx.kimiko.utils.storage.friend.FriendUtils;
import rtx.kimiko.utils.time.StopWatch;

@ServerRule(mode=ServerRule.Mode.BLOCK, servers={Server.ST})
@Feature(value={"autoduel"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 C2\u00020\u0001:\u0001CB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\u0006\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u001b\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0003b\u0002\b\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\r\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\fH\u0003b\u0002\b\t\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J!\u0010\u0014\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0011\u0010\u001b\u001a\u0004\u0018\u00010\u000fH\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\u0017\u0010#\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020!H\u0002\u00a2\u0006\u0004\b#\u0010$R\u0014\u0010&\u001a\u00020%8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0014\u0010)\u001a\u00020(8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020%8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b+\u0010'R\u0014\u0010-\u001a\u00020,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u0014\u0010/\u001a\u00020,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u0010.R\u0014\u00100\u001a\u00020%8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u0010'R\u0014\u00102\u001a\u0002018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0014\u00104\u001a\u0002018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u00103R\u0014\u00105\u001a\u00020(8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u0010*R\u0014\u00107\u001a\u0002068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b7\u00108R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010<\u001a\u0002018\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u00103R\u0014\u0010>\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b@\u0010?R\u0018\u0010A\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bA\u0010B\u00ca\u0001\u001e\bD\u0012\n\b&\u0012\u0006\b\n0E8F\u0012\u000e\bG\u0012\n\b\fJ\u0006\b\n0H8I\u00ca\u0001\u0010\bJ\u0012\f\bK\u0012\b\b\fJ\u0004\b\b(L\u00a8\u0006M"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/AutoDuel;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "onEnable", "onDisable", "Lrtx/kimiko/api/events/impl/network/PacketEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onPacket", "(Lrtx/kimiko/api/events/impl/network/PacketEvent;)V", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "", "raw", "lower", "parseDuelInvite", "(Ljava/lang/String;Ljava/lang/String;)V", "extractSender", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "extractKit", "(Ljava/lang/String;)Ljava/lang/String;", "", "extractBet", "(Ljava/lang/String;)J", "pickTargetNick", "()Ljava/lang/String;", "nick", "", "isFriend", "(Ljava/lang/String;)Z", "Lnet/minecraft/GameMessageS2CPacket;", "packet", "isOverlay", "(Lnet/minecraft/GameMessageS2CPacket;)Z", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "mode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "sendSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "targetMode", "Lrtx/kimiko/api/modules/settings/impl/TextSetting;", "targetNick", "Lrtx/kimiko/api/modules/settings/impl/TextSetting;", "customNicks", "kit", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "money", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "sendDelay", "acceptSeparator", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "friendsOnly", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Lrtx/kimiko/api/modules/settings/impl/MultiModeSetting;", "allowedKits", "Lrtx/kimiko/api/modules/settings/impl/MultiModeSetting;", "maxMoney", "Lrtx/kimiko/utils/time/StopWatch;", "sendTimer", "Lrtx/kimiko/utils/time/StopWatch;", "acceptTimer", "pendingAcceptCommand", "Ljava/lang/String;", "Companion", "Lrtx/kimiko/api/modules/restrict/ServerRule;", "Lrtx/kimiko/api/modules/restrict/ServerRule$Mode;", "BLOCK", "servers", "Lrtx/kimiko/api/modules/restrict/Server;", "ST", "Lrtx/kimiko/api/liteapi/Feature;", "value", "autoduel", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nAutoDuel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AutoDuel.kt\nrtx/kimiko/api/modules/impl/Utils/AutoDuel\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,246:1\n1#2:247\n1739#3:248\n1814#3,3:249\n777#3:252\n873#3,2:253\n*S KotlinDebug\n*F\n+ 1 AutoDuel.kt\nrtx/kimiko/api/modules/impl/Utils/AutoDuel\n*L\n204#1:248\n204#1:249,3\n204#1:252\n204#1:253,2\n*E\n"})
public final class AutoDuel
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ModeSetting mode;
    @NotNull
    private final SeparatorSetting sendSeparator;
    @NotNull
    private final ModeSetting targetMode;
    @NotNull
    private final TextSetting targetNick;
    @NotNull
    private final TextSetting customNicks;
    @NotNull
    private final ModeSetting kit;
    @NotNull
    private final NumberSetting money;
    @NotNull
    private final NumberSetting sendDelay;
    @NotNull
    private final SeparatorSetting acceptSeparator;
    @NotNull
    private final BooleanSetting friendsOnly;
    @NotNull
    private final MultiModeSetting allowedKits;
    @NotNull
    private final NumberSetting maxMoney;
    @NotNull
    private final StopWatch sendTimer;
    @NotNull
    private final StopWatch acceptTimer;
    @Nullable
    private String pendingAcceptCommand;
    @NotNull
    private static final Regex REGEX_SPACES = new Regex("\\s+");
    @NotNull
    private static final Regex REGEX_NON_ALPHANUM = new Regex("[^A-Za-z0-9_]");
    @NotNull
    private static final Regex REGEX_NON_DIGITS = new Regex("[^0-9]");
    @NotNull
    private static final String MODE_ACCEPT = "Принимать";
    @NotNull
    private static final String MODE_SEND = "Отправлять";
    @NotNull
    private static final String TARGET_FRIENDS = "Друзья";
    @NotNull
    private static final String TARGET_NICK = "Никнейм";
    @NotNull
    private static final String TARGET_CUSTOM = "Свои ники";
    @NotNull
    private static final String KIT_SHIELD = "Щит";
    @NotNull
    private static final String KIT_SWORD = "Меч";
    @NotNull
    private static final String KIT_NETHERITE = "Незеритка";
    @NotNull
    private static final String KIT_BOW = "Лук";
    @NotNull
    private static final String KIT_POTIONS = "Зелья";

    public AutoDuel() {
        super("Auto Duel", "Автоматически отправляет или принимает дуэли.", Category.UTILS);
        String[] stringArray = new String[]{MODE_ACCEPT, MODE_SEND};
        this.mode = (ModeSetting)this.register((Setting)new ModeSetting("Режим", "Что делать: отправлять дуэли или только принимать.", MODE_ACCEPT, stringArray));
        this.sendSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Отправка").visibleWhen(() -> AutoDuel.sendSeparator$lambda$0(this)));
        stringArray = new String[]{TARGET_FRIENDS, TARGET_NICK, TARGET_CUSTOM};
        this.targetMode = (ModeSetting)this.register((Setting)new ModeSetting("Цель", "Кому кидать дуэли.", TARGET_FRIENDS, stringArray).visibleWhen(() -> AutoDuel.targetMode$lambda$0(this)));
        this.targetNick = (TextSetting)this.register((Setting)new TextSetting(TARGET_NICK, "Никнейм игрока для /duel <ник>.").setPlaceholder("Ник").visibleWhen(() -> AutoDuel.targetNick$lambda$0(this)));
        this.customNicks = (TextSetting)this.register((Setting)new TextSetting(TARGET_CUSTOM, "Ники через запятую: Player1, Player2").setPlaceholder("Ник1, Ник2").visibleWhen(() -> AutoDuel.customNicks$lambda$0(this)));
        stringArray = new String[]{KIT_SHIELD, KIT_SWORD, KIT_NETHERITE, KIT_BOW, KIT_POTIONS};
        this.kit = (ModeSetting)this.register((Setting)new ModeSetting("Кит", "Какой набор выбирать.", KIT_SHIELD, stringArray).visibleWhen(() -> AutoDuel.kit$lambda$0(this)));
        this.money = (NumberSetting)this.register((Setting)new NumberSetting("Ставка ($)", "Сумма ставки на дуэль.", 0.0, 0.0, 1000000.0, 500.0).visibleWhen(() -> AutoDuel.money$lambda$0(this)));
        this.sendDelay = (NumberSetting)this.register((Setting)new NumberSetting("Задержка отправки (сек)", "Интервал между повторными /duel.", 5.0, 2.0, 30.0, 1.0).visibleWhen(() -> AutoDuel.sendDelay$lambda$0(this)));
        this.acceptSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Приём"));
        this.friendsOnly = (BooleanSetting)this.register((Setting)new BooleanSetting("Только друзья", "Принимать дуэли только от друзей.", false));
        stringArray = new String[]{KIT_SHIELD, KIT_SWORD, KIT_NETHERITE, KIT_BOW, KIT_POTIONS};
        String[] stringArray2 = stringArray;
        stringArray = new String[]{KIT_SHIELD, KIT_SWORD, KIT_NETHERITE, KIT_BOW, KIT_POTIONS};
        this.allowedKits = (MultiModeSetting)this.register((Setting)new MultiModeSetting("Разрешённые киты", "Принимать только указанные киты.", stringArray2, stringArray));
        this.maxMoney = (NumberSetting)this.register((Setting)new NumberSetting("Макс. ставка ($)", "Максимально допустимая ставка для авто-приёма (0 = без лимита).", 0.0, 0.0, 1000000.0, 1000.0));
        this.sendTimer = new StopWatch();
        this.acceptTimer = new StopWatch();
    }

    @Override
    protected void onEnable() {
        this.sendTimer.reset();
        this.acceptTimer.reset();
        this.pendingAcceptCommand = null;
    }

    @Override
    protected void onDisable() {
        this.pendingAcceptCommand = null;
    }

    @EventHandler
    private final void onPacket(PacketEvent event) {
        String message;
        if (!event.isReceive()) {
            return;
        }
        Packet<?> packet2 = event.getPacket();
        GameMessageS2CPacket gameMessageS2CPacket2 = packet2 instanceof GameMessageS2CPacket ? (GameMessageS2CPacket)packet2 : null;
        if (gameMessageS2CPacket2 == null) {
            return;
        }
        GameMessageS2CPacket packet = gameMessageS2CPacket2;
        if (this.isOverlay(packet)) {
            return;
        }
        String string = packet.content().getString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getString(...)");
        String string2 = message = string;
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string3 = string2.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toLowerCase(...)");
        String lower = string3;
        this.parseDuelInvite(message, lower);
    }

    @EventHandler
    private final void onTick(TickEvent event) {
        if (!event.isPre()) {
            return;
        }
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        ClientPlayNetworkHandler clientPlayNetworkHandler2 = player.networkHandler;
        if (clientPlayNetworkHandler2 == null) {
            return;
        }
        ClientPlayNetworkHandler conn = clientPlayNetworkHandler2;
        String cmd = this.pendingAcceptCommand;
        if (cmd != null && this.acceptTimer.finished(600.0)) {
            conn.sendChatCommand(cmd);
            this.pendingAcceptCommand = null;
            this.acceptTimer.reset();
            return;
        }
        if (!this.mode.is(MODE_SEND)) {
            return;
        }
        double intervalMs = (double)this.sendDelay.getValue() * 1000.0;
        if (!this.sendTimer.finished(intervalMs)) {
            return;
        }
        String string = this.pickTargetNick();
        if (string == null) {
            return;
        }
        String target = string;
        String string2 = this.kit.getValue();
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string3 = string2.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toLowerCase(...)");
        String kitName = string3;
        long bet = (long)this.money.getValue();
        String duelCmd = bet > 0L ? "duel " + target + " " + kitName + " " + bet : "duel " + target + " " + kitName;
        conn.sendChatCommand(duelCmd);
        this.sendTimer.reset();
    }

    private final void parseDuelInvite(String raw, String lower) {
        if (!(String.valueOf(lower).contains("дуэль") || String.valueOf(lower).contains("duel") || String.valueOf(lower).contains("вызвал вас"))) {
            return;
        }
        String string = this.extractSender(raw, lower);
        if (string == null) {
            return;
        }
        String sender = string;
        if (this.friendsOnly.getValue() && !this.isFriend(sender)) {
            return;
        }
        String kitFound = this.extractKit(lower);
        if (kitFound != null && !this.allowedKits.isSelected(kitFound)) {
            return;
        }
        long bet = this.extractBet(lower);
        long maxBet = (long)this.maxMoney.getValue();
        if (maxBet > 0L && bet > maxBet) {
            return;
        }
        this.pendingAcceptCommand = "duel accept " + sender;
        this.acceptTimer.reset();
    }

    private final String extractSender(String raw, String lower) {
        String[] stringArray = new String[]{"игрок ", "вызвал вас", "вас на дуэль", "дуэль от "};
        for (String pattern : stringArray) {
            int idx = lower.indexOf(pattern);
            if (idx == -1) continue;
            String string2 = raw.substring(idx + pattern.length());
            String sub = string2.trim();
            List<String> parts = REGEX_SPACES.split((CharSequence)sub, 0);
            if (parts.isEmpty()) continue;
            String candidate = REGEX_NON_ALPHANUM.replace((CharSequence)parts.get(0), "");
            int n2 = candidate.length();
            if (n2 < 3 || n2 >= 17) continue;
            return candidate;
        }
        return null;
    }

    private final String extractKit(String lower) {
        if (String.valueOf(lower).contains("щит") || String.valueOf(lower).contains("shield")) {
            return KIT_SHIELD;
        }
        if (String.valueOf(lower).contains("меч") || String.valueOf(lower).contains("sword")) {
            return KIT_SWORD;
        }
        if (String.valueOf(lower).contains("незерит") || String.valueOf(lower).contains("netherite")) {
            return KIT_NETHERITE;
        }
        if (String.valueOf(lower).contains("лук") || String.valueOf(lower).contains("bow")) {
            return KIT_BOW;
        }
        if (String.valueOf(lower).contains("зелья") || String.valueOf(lower).contains("potions")) {
            return KIT_POTIONS;
        }
        return null;
    }

    private final long extractBet(String lower) {
        int idx = String.valueOf(lower).indexOf("$");
        if (idx != -1) {
            String string = lower.substring(Math.max(0, idx - 10), idx);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            CharSequence charSequence = string;
            Regex regex = REGEX_NON_DIGITS;
            String string2 = "";
            String numStr = regex.replace(charSequence, string2);
            if (((CharSequence)numStr).length() > 0) {
                Long l = StringsKt.toLongOrNull((String)numStr);
                return l != null ? l : 0L;
            }
        }
        return 0L;
    }

    private final String pickTargetNick() {
        return switch (this.targetMode.getValue()) {
            case TARGET_FRIENDS -> {
                List<String> friends = FriendUtils.friends();
                if (friends.isEmpty()) {
                    yield null;
                }
                yield friends.get(ThreadLocalRandom.current().nextInt(friends.size()));
            }
            case TARGET_NICK -> {
                String nickVal = this.targetNick.getValue().trim();
                yield nickVal.isEmpty() ? null : nickVal;
            }
            case TARGET_CUSTOM -> {
                String raw = this.customNicks.getValue();
                ArrayList<String> nicks = new ArrayList<>();
                for (String part : raw.split(",")) {
                    String trimmed = part.trim();
                    if (!trimmed.isEmpty()) {
                        nicks.add(trimmed);
                    }
                }
                if (nicks.isEmpty()) {
                    yield null;
                }
                yield nicks.get(ThreadLocalRandom.current().nextInt(nicks.size()));
            }
            default -> null;
        };
    }

    private final boolean isFriend(String nick) {
        for (String f : FriendUtils.friends()) {
            if (f == null || !StringsKt.equals((String)f, (String)nick, (boolean)true)) continue;
            return true;
        }
        return false;
    }

    private final boolean isOverlay(GameMessageS2CPacket packet) {
        boolean bl;
        try {
            bl = packet.overlay();
        }
        catch (Throwable t) {
            bl = false;
        }
        return bl;
    }

    private static final Boolean sendSeparator$lambda$0(AutoDuel this$0) {
        return this$0.mode.is(MODE_SEND);
    }

    private static final Boolean targetMode$lambda$0(AutoDuel this$0) {
        return this$0.mode.is(MODE_SEND);
    }

    private static final Boolean targetNick$lambda$0(AutoDuel this$0) {
        return this$0.mode.is(MODE_SEND) && this$0.targetMode.is(TARGET_NICK);
    }

    private static final Boolean customNicks$lambda$0(AutoDuel this$0) {
        return this$0.mode.is(MODE_SEND) && this$0.targetMode.is(TARGET_CUSTOM);
    }

    private static final Boolean kit$lambda$0(AutoDuel this$0) {
        return this$0.mode.is(MODE_SEND);
    }

    private static final Boolean money$lambda$0(AutoDuel this$0) {
        return this$0.mode.is(MODE_SEND);
    }

    private static final Boolean sendDelay$lambda$0(AutoDuel this$0) {
        return this$0.mode.is(MODE_SEND);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\u000bR\u0014\u0010\r\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000bR\u0014\u0010\u000e\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000bR\u0014\u0010\u000f\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000bR\u0014\u0010\u0011\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u000bR\u0014\u0010\u0012\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u000bR\u0014\u0010\u0013\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u000bR\u0014\u0010\u0014\u001a\u00020\t8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u000b\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/AutoDuel.Companion;", "", "<init>", "()V", "Lkotlin/text/Regex;", "REGEX_SPACES", "Lkotlin/text/Regex;", "REGEX_NON_ALPHANUM", "REGEX_NON_DIGITS", "", "MODE_ACCEPT", "Ljava/lang/String;", "MODE_SEND", "TARGET_FRIENDS", "TARGET_NICK", "TARGET_CUSTOM", "KIT_SHIELD", "KIT_SWORD", "KIT_NETHERITE", "KIT_BOW", "KIT_POTIONS", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

