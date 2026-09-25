/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.network.ClientPlayNetworkHandler
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.storage.macro;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.chat.commands.CommandManager;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.input.KeyPressEvent;
import rtx.kimiko.utils.storage.macro.Macro;
import rtx.kimiko.utils.storage.macro.MacroRepository;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ!\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/utils/storage/macro/MacroHandler;", "", "<init>", "()V", "Lrtx/kimiko/api/events/impl/input/KeyPressEvent;", "event", "", "Lrtx/kimiko/api/events/EventHandler;", "onKey", "(Lrtx/kimiko/api/events/impl/input/KeyPressEvent;)V", "Lnet/minecraft/MinecraftClient;", "mc", "", "message", "run", "(Lnet/minecraft/MinecraftClient;Ljava/lang/String;)V", "Companion", "rtx.kimiko:kimiko"})
public final class MacroHandler {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final MacroHandler INSTANCE = new MacroHandler();

    private MacroHandler() {
    }

    @EventHandler
    public final void onKey(@NotNull KeyPressEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (event.action != KeyPressEvent.Action.PRESS) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (mc.player == null || mc.currentScreen != null) {
            return;
        }
        for (Macro macro : MacroRepository.Companion.getInstance().getMacroList()) {
            if (macro.key() != event.keyCode) continue;
            this.run(mc, macro.message());
        }
    }

    private final void run(MinecraftClient mc, String message) {
        CharSequence charSequence = message;
        if (charSequence == null || StringsKt.isBlank((CharSequence)charSequence)) {
            return;
        }
        CommandManager commands = CommandManager.Companion.get();
        if (commands.isClientCommand(message)) {
            String string = message.substring(commands.getPrefix().length());
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            commands.executeRaw(string);
        } else if (String.valueOf(message).startsWith("/")) {
            ClientPlayerEntity clientPlayerEntity2 = mc.player;
            Intrinsics.checkNotNull((Object)clientPlayerEntity2);
            ClientPlayNetworkHandler clientPlayNetworkHandler2 = clientPlayerEntity2.networkHandler;
            String string = message.substring(1);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            clientPlayNetworkHandler2.sendChatCommand(string);
        } else {
            ClientPlayerEntity clientPlayerEntity3 = mc.player;
            Intrinsics.checkNotNull((Object)clientPlayerEntity3);
            clientPlayerEntity3.networkHandler.sendChatMessage(message);
        }
    }

    @JvmStatic
    public static final void init() {
        Companion.init();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/utils/storage/macro/MacroHandler.Companion;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "init", "Lrtx/kimiko/utils/storage/macro/MacroHandler;", "INSTANCE", "Lrtx/kimiko/utils/storage/macro/MacroHandler;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final void init() {
            EventBus.Companion.get().subscribe(INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

