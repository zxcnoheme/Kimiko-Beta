/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.client.network.ClientPlayNetworkHandler
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.restrict.Server;
import rtx.kimiko.api.modules.restrict.ServerRule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.MultiSelectSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import sigil.protect.Level;
import sigil.protect.Protect;

@ServerRule(mode=ServerRule.Mode.ONLY, servers={Server.RW})
@Feature(value={"autocommands"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000  2\u00020\u0001:\u0001 B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u0004H\u0015b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\t\u0010\u0003J+\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\fb\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00100\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001e\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001f\u00ca\u0001\u001e\b!\u0012\n\b\"\u0012\u0006\b\n0#8$\u0012\u000e\b%\u0012\n\b\fJ\u0006\b\n0&8'\u00ca\u0001\u0010\b(\u0012\f\b\u0006\u0012\b\b\fJ\u0004\b\b()\u00a8\u0006*"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/AutoCommands;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "onEnable", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "STD", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "", "display", "send", "(Ljava/lang/String;)V", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "commands", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "cooldown", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "", "scheduled", "Ljava/util/List;", "", "nextAt", "[J", "Companion", "Lrtx/kimiko/api/modules/restrict/ServerRule;", "mode", "Lrtx/kimiko/api/modules/restrict/ServerRule$Mode;", "ONLY", "servers", "Lrtx/kimiko/api/modules/restrict/Server;", "RW", "Lrtx/kimiko/api/liteapi/Feature;", "autocommands", "rtx.kimiko:kimiko"})
public final class AutoCommands
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final MultiSelectSetting commands;
    @NotNull
    private final SliderSetting cooldown;
    @NotNull
    private List<String> scheduled;
    @NotNull
    private long[] nextAt;
    @NotNull
    private static final String FIX_ALL = "/fix all";
    @NotNull
    private static final String HEAL = "/heal";
    private static final long STAGGER_MS = 10000L;

    public AutoCommands() {
        super("Auto Commands", "Автоматически отправляет выбранные команды по кулдауну, по очереди.", Category.UTILS);
        String[] stringArray = new String[]{FIX_ALL, HEAL};
        MultiSelectSetting multiSelectSetting = new MultiSelectSetting("Команды", "Какие команды отправлять автоматически.").value(stringArray);
        stringArray = new String[]{FIX_ALL, HEAL};
        this.commands = (MultiSelectSetting)this.register((Setting)multiSelectSetting.selected(stringArray));
        this.cooldown = (SliderSetting)this.register((Setting)new SliderSetting("Кулдаун", "Как часто повторять каждую команду (секунды).").setValue(60.0f).range(60, 360).increment(1));
        this.scheduled = CollectionsKt.emptyList();
        this.nextAt = new long[0];
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onEnable() {
        this.scheduled = CollectionsKt.emptyList();
        this.nextAt = new long[0];
    }

    @EventHandler
    @Protect(value=Level.STD)
    public final void onTick(@NotNull TickEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
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
        if (this.mc.world == null || this.mc.currentScreen != null) {
            return;
        }
        long now = System.currentTimeMillis();
        ArrayList selected = new ArrayList(this.commands.getSelected());
        if (!Intrinsics.areEqual(selected, this.scheduled)) {
            this.scheduled = selected;
            this.nextAt = new long[selected.size()];
            int n = ((Collection)selected).size();
            for (int i = 0; i < n; ++i) {
                this.nextAt[i] = now + (long)i * 10000L;
            }
        }
        if (this.scheduled.isEmpty()) {
            return;
        }
        long cooldownMs = (long)this.cooldown.getInt() * 1000L;
        int n = ((Collection)this.scheduled).size();
        for (int i = 0; i < n; ++i) {
            if (now < this.nextAt[i]) continue;
            this.send(this.scheduled.get(i));
            this.nextAt[i] = now + cooldownMs;
        }
    }

    private final void send(String display) {
        if (display == null) {
            return;
        }
        String cmd = display.startsWith("/") ? display.substring(1) : display;
        cmd = cmd.trim();
        if (!cmd.isEmpty() && this.mc.player != null && this.mc.player.networkHandler != null) {
            this.mc.player.networkHandler.sendChatCommand(cmd);
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/AutoCommands.Companion;", "", "<init>", "()V", "", "FIX_ALL", "Ljava/lang/String;", "HEAL", "", "STAGGER_MS", "J", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

