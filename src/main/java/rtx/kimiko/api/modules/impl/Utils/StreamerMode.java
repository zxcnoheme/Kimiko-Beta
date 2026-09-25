/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.collections.SetsKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.text.Text
 *  net.minecraft.client.session.Session
 *  net.minecraft.text.MutableText
 *  net.minecraft.client.network.PlayerListEntry
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import com.mojang.authlib.GameProfile;
import net.minecraft.text.Text;
import net.minecraft.client.session.Session;
import net.minecraft.text.MutableText;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.TextFactoryEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.impl.Utils.StreamerMode;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.MultiModeSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.api.modules.settings.impl.TextSetting;
import rtx.kimiko.api.party.PartyClient;
import rtx.kimiko.api.party.PartyMember;
import rtx.kimiko.utils.network.Network;
import rtx.kimiko.utils.render.modules.rank.ReallyWorldRanks;
import rtx.kimiko.utils.storage.friend.FriendUtils;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"streamermode", "nameprotect"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 .2\u00020\u0001:\u0001.B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0011\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0011\u0010\u0010\u001a\u0004\u0018\u00010\rH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ%\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\n0\u0011H\u0003b\u000e\b\u0012\u0012\n\b\u0013\u0012\u0006\b\n0\u00148\u0015\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010\u001aR\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010\u001aR\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0018\u0010)\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010,\u001a\u00020+8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010-\u00ca\u0001\u0016\b/\u0012\u0012\b\u0013\u0012\u000e\b\fJ\u0004\b\b(0J\u0004\b\b(1\u00a8\u00062"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/StreamerMode;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "Lrtx/kimiko/api/events/impl/render/TextFactoryEvent;", "event", "", "Lrtx/kimiko/api/events/EventHandler;", "onTextFactory", "(Lrtx/kimiko/api/events/impl/render/TextFactoryEvent;)V", "", "replacement", "()Ljava/lang/String;", "Ljava/util/regex/Pattern;", "protectedPattern", "()Ljava/util/regex/Pattern;", "buildPattern", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "MAX", "onlineFriends", "()Ljava/util/Set;", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "namesSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/MultiModeSetting;", "hideWho", "Lrtx/kimiko/api/modules/settings/impl/MultiModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/TextSetting;", "replaceName", "Lrtx/kimiko/api/modules/settings/impl/TextSetting;", "coordsSeparator", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "hideCoordsSetting", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "rankSeparator", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "customRank", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "cachedPattern", "Ljava/util/regex/Pattern;", "", "patternCacheAt", "J", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "streamermode", "nameprotect", "rtx.kimiko:kimiko"})
public final class StreamerMode
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting namesSeparator = new SeparatorSetting("Ник");
    @NotNull
    private final MultiModeSetting hideWho;
    @NotNull
    private final TextSetting replaceName;
    @NotNull
    private final SeparatorSetting coordsSeparator;
    @NotNull
    private final BooleanSetting hideCoordsSetting;
    @NotNull
    private final SeparatorSetting rankSeparator;
    @NotNull
    private final ModeSetting customRank;
    @Nullable
    private Pattern cachedPattern;
    private long patternCacheAt;
    @NotNull
    public static final String HIDE_SELF = "Себя";
    @NotNull
    public static final String HIDE_FRIENDS = "Друзей";
    @NotNull
    public static final String HIDE_PARTY = "Пати";
    @Nullable
    private static StreamerMode companionInstance;

    public StreamerMode() {
        super("Streamer Mode", "Скрывает ваш ник, ники друзей/пати и координаты в отображаемом тексте.", Category.UTILS);
        String[] targets = new String[]{HIDE_SELF, HIDE_FRIENDS, HIDE_PARTY};
        this.hideWho = new MultiModeSetting("Кого скрывать", "Какие имена маскировать", targets, targets);
        this.replaceName = new TextSetting("Заменять имена на", "Текст для подмены скрываемых имён").setPlaceholder("Protected").lengthBounds(0, 32).visible(() -> StreamerMode.replaceName$lambda$0(this));
        this.coordsSeparator = new SeparatorSetting("Координаты");
        this.hideCoordsSetting = new BooleanSetting("Скрыть координаты", "Маскировать координаты в HUD и F3 (#, #, #).", true);
        this.rankSeparator = new SeparatorSetting("Ранг ReallyWorld");
        String[] rankOptions = StreamerMode.Companion.buildRankOptions();
        this.customRank = new ModeSetting("Кастомный ранг", "Показывать выбранный донат ReallyWorld вместо своего (только у вас).", "Выкл", Arrays.copyOf(rankOptions, rankOptions.length)).visibleWhen(StreamerMode::customRank$lambda$0);
        this.register(this.namesSeparator, this.hideWho, this.replaceName, this.coordsSeparator, this.hideCoordsSetting, this.rankSeparator, this.customRank);
        companionInstance = this;
    }

    @EventHandler
    public final void onTextFactory(@NotNull TextFactoryEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        String text = event.getText();
        CharSequence charSequence = text;
        if (charSequence == null || charSequence.length() == 0) {
            return;
        }
        Pattern pattern = this.protectedPattern();
        if (pattern == null) {
            return;
        }
        Pattern pattern2 = pattern;
        String masked = pattern2.matcher(text).replaceAll(Matcher.quoteReplacement(this.replacement()));
        if (!Intrinsics.areEqual((Object)masked, (Object)text)) {
            event.setText(masked);
        }
    }

    private final String replacement() {
        String value = this.replaceName.getValue();
        CharSequence charSequence = value;
        return charSequence == null || charSequence.length() == 0 ? "Protected" : value;
    }

    private final Pattern protectedPattern() {
        long now = System.currentTimeMillis();
        if (now - this.patternCacheAt < 400L) {
            return this.cachedPattern;
        }
        this.patternCacheAt = now;
        this.cachedPattern = this.buildPattern();
        return this.cachedPattern;
    }

    private final Pattern buildPattern() {
        Session session2 = this.mc.getSession();
        String self1 = session2 != null ? session2.getUsername() : null;
        ClientPlayerEntity player = this.mc.player;
        String self2 = player != null && player.getGameProfile() != null ? player.getGameProfile().name() : null;
        String self = self1 != null ? self1 : self2;
        ArrayList<String> names = new ArrayList<>();
        if (this.hideWho.isSelected(HIDE_SELF)) {
            StreamerMode.Companion.addName(names, self1, null);
            StreamerMode.Companion.addName(names, self2, null);
        }
        if (this.hideWho.isSelected(HIDE_FRIENDS)) {
            for (String name : this.onlineFriends()) {
                StreamerMode.Companion.addName(names, name, self);
            }
        }
        if (this.hideWho.isSelected(HIDE_PARTY)) {
            for (PartyMember member : PartyClient.INSTANCE.snapshot().members()) {
                StreamerMode.Companion.addName(names, member.name(), self);
            }
        }
        if (names.isEmpty()) {
            return null;
        }
        names.sort(Comparator.comparingInt(String::length).reversed());
        StringBuilder sb = new StringBuilder("(?i)(?<![A-Za-z0-9_])(");
        int n = ((Collection)names).size();
        for (int i = 0; i < n; ++i) {
            if (i > 0) {
                sb.append('|');
            }
            sb.append(Pattern.quote((String)names.get(i)));
        }
        sb.append(")(?![A-Za-z0-9_])");
        return Pattern.compile(sb.toString());
    }

    @Protect(value=Level.MAX)
    private final Set<String> onlineFriends() {
        List<String> friends = FriendUtils.getFriendNames();
        ClientPlayerEntity player = this.mc.player;
        if (player == null || player.networkHandler == null) {
            return SetsKt.emptySet();
        }
        ClientPlayNetworkHandler conn = player.networkHandler;
        if (friends.isEmpty()) {
            return SetsKt.emptySet();
        }
        HashSet<String> friendsLower = new HashSet<>();
        for (String friend : friends) {
            if (friend == null || StringsKt.isBlank(friend)) continue;
            friendsLower.add(friend.toLowerCase(Locale.ROOT));
        }
        HashSet<String> online = new HashSet<>();
        for (PlayerListEntry info : conn.getPlayerList()) {
            GameProfile profile = info.getProfile();
            if (profile == null || profile.name() == null) continue;
            String name = profile.name();
            if (friendsLower.contains(name.toLowerCase(Locale.ROOT))) {
                online.add(name);
            }
        }
        return online;
    }

    private static final Boolean replaceName$lambda$0(StreamerMode this$0) {
        return !((Collection)this$0.hideWho.getSelected()).isEmpty();
    }

    private static final Boolean customRank$lambda$0() {
        return Network.isReallyWorld();
    }

    private static final int buildPattern$lambda$0(Function1 $tmp0, Object p0) {
        return ((Number)$tmp0.invoke(p0)).intValue();
    }

    @JvmStatic
    public static final boolean active() {
        return Companion.active();
    }

    @JvmStatic
    public static final boolean hideCoords() {
        return Companion.hideCoords();
    }

    @JvmStatic
    @NotNull
    public static final Text applySelfRank(@NotNull Text name) {
        return Companion.applySelfRank(name);
    }

    @JvmStatic
    @NotNull
    public static final Text applySelfRankInChat(@Nullable Text message) {
        return Companion.applySelfRankInChat(message);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\n\u001a\u00020\bH\u0007b\u0002\b\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\f\u001a\u00020\bH\u0007b\u0002\b\t\u00a2\u0006\u0004\b\f\u0010\u000bJ\u001b\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0007b\u0002\b\t\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0012\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\rH\u0007b\u0002\b\t\u00a2\u0006\u0004\b\u0012\u0010\u0010J1\u0010\u0017\u001a\u00020\u00162\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\b\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00058\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00058\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u00058\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001aR\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001f\u00a8\u0006 "}, d2={"Lrtx/kimiko/api/modules/impl/Utils/StreamerMode.Companion;", "", "<init>", "()V", "", "", "buildRankOptions", "()[Ljava/lang/String;", "", "Lkotlin/jvm/JvmStatic;", "active", "()Z", "hideCoords", "Lnet/minecraft/Text;", "name", "applySelfRank", "(Lnet/minecraft/Text;)Lnet/minecraft/Text;", "message", "applySelfRankInChat", "", "names", "excludeSelf", "", "addName", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "HIDE_SELF", "Ljava/lang/String;", "HIDE_FRIENDS", "HIDE_PARTY", "Lrtx/kimiko/api/modules/impl/Utils/StreamerMode;", "companionInstance", "Lrtx/kimiko/api/modules/impl/Utils/StreamerMode;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final String[] buildRankOptions() {
            List<String> labels = ReallyWorldRanks.orderedLabels();
            int n = 0;
            int n2 = labels.size() + 1;
            String[] stringArray = new String[n2];
            while (n < n2) {
                int n3 = n++;
                stringArray[n3] = "";
            }
            String[] options = stringArray;
            options[0] = "Выкл";
            n2 = ((Collection)labels).size();
            for (int i = 0; i < n2; ++i) {
                options[i + 1] = labels.get(i);
            }
            return options;
        }

        @JvmStatic
        public final boolean active() {
            StreamerMode streamerMode = companionInstance;
            return streamerMode != null ? streamerMode.isEnabled() : false;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @JvmStatic
        public final boolean hideCoords() {
            if (!this.active()) return false;
            StreamerMode streamerMode = companionInstance;
            Object object = streamerMode;
            if (streamerMode == null) return false;
            BooleanSetting booleanSetting = ((StreamerMode)object).hideCoordsSetting;
            object = booleanSetting;
            if (booleanSetting == null) return false;
            if (!((BooleanSetting)object).getValue()) return false;
            return true;
        }

        @JvmStatic
        @NotNull
        public final Text applySelfRank(@NotNull Text name) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            StreamerMode streamerMode = companionInstance;
            if (streamerMode == null) {
                return name;
            }
            StreamerMode inst = streamerMode;
            if (!this.active()) {
                return name;
            }
            String label = inst.customRank.getSelected();
            if (((CharSequence)label).length() == 0 || Intrinsics.areEqual((Object)label, (Object)"Выкл")) {
                return name;
            }
            Text text2 = ReallyWorldRanks.applySelfRank(name, label);
            if (text2 == null) {
                text2 = name;
            }
            return text2;
        }

        @JvmStatic
        @NotNull
        public final Text applySelfRankInChat(@Nullable Text message) {
            if (message == null) {
                MutableText mutableText2 = Text.empty();
                Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"empty(...)");
                return (Text)mutableText2;
            }
            StreamerMode streamerMode = companionInstance;
            if (streamerMode == null) {
                return message;
            }
            StreamerMode inst = streamerMode;
            if (!this.active()) {
                return message;
            }
            String label = inst.customRank.getSelected();
            if (((CharSequence)label).length() == 0 || Intrinsics.areEqual((Object)label, (Object)"Выкл")) {
                return message;
            }
            ClientPlayerEntity player = inst.mc.player;
            String nick = player != null && player.getGameProfile() != null ? player.getGameProfile().name() : null;
            CharSequence charSequence = nick;
            if (charSequence == null || StringsKt.isBlank((CharSequence)charSequence) || !ReallyWorldRanks.glyphPrecedesNick(message.getString(), nick)) {
                return message;
            }
            Text text2 = ReallyWorldRanks.applySelfRank(message, label);
            if (text2 == null) {
                text2 = message;
            }
            return text2;
        }

        private final void addName(List<String> names, String name, String excludeSelf) {
            CharSequence charSequence = name;
            if (charSequence == null || StringsKt.isBlank((CharSequence)charSequence)) {
                return;
            }
            if (excludeSelf != null && StringsKt.equals((String)name, (String)excludeSelf, (boolean)true)) {
                return;
            }
            for (String existing : names) {
                if (!StringsKt.equals((String)existing, (String)name, (boolean)true)) continue;
                return;
            }
            names.add(name);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

