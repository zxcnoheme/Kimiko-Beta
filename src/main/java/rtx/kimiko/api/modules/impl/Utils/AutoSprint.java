/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.PlayerInput
 *  net.minecraft.client.util.Window
 *  net.minecraft.entity.effect.StatusEffects
 *  net.minecraft.client.option.KeyBinding
 *  net.minecraft.client.option.GameOptions
 *  net.minecraft.client.util.InputUtil
 *  net.minecraft.client.util.InputUtil.Key
 *  net.minecraft.client.util.InputUtil.Type
 *  net.minecraft.client.input.Input
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.lwjgl.glfw.GLFW
 */
package rtx.kimiko.api.modules.impl.Utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.PlayerInput;
import net.minecraft.client.util.Window;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.input.InputEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"autosprint"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\r\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u0010\u001a\u00020\u0006H\u0015b\u000e\b\f\u0012\n\b\r\u0012\u0006\b\n0\u000e8\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u001b\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0011H\u0007b\u0002\b\u0013\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0016H\u0007b\u0002\b\u0013\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u0003J\u0017\u0010 \u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b \u0010!J\u0017\u0010$\u001a\u00020\t2\u0006\u0010#\u001a\u00020\"H\u0002\u00a2\u0006\u0004\b$\u0010%R\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010)\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010+\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u0010,\u00ca\u0001\u0010\b-\u0012\f\b\r\u0012\b\b\fJ\u0004\b\b(.\u00a8\u0006/"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/AutoSprint;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "ms", "", "suppressFor", "(J)V", "", "suppressed", "()Z", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "onDisable", "Lrtx/kimiko/api/events/impl/input/InputEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onInput", "(Lrtx/kimiko/api/events/impl/input/InputEvent;)V", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "Lnet/minecraft/ClientPlayerEntity;", "player", "forwardHeld", "(Lnet/minecraft/ClientPlayerEntity;)Z", "allowsSprint", "releaseSprintKey", "down", "setSprintKey", "(Z)V", "Lnet/minecraft/KeyBinding;", "key", "isPressed", "(Lnet/minecraft/KeyBinding;)Z", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "keepSprint", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "heldByUs", "Z", "suppressUntil", "J", "Lrtx/kimiko/api/liteapi/Feature;", "autosprint", "rtx.kimiko:kimiko"})
public final class AutoSprint
extends Module {
    @NotNull
    private final BooleanSetting keepSprint = (BooleanSetting)this.register((Setting)new BooleanSetting("Сохранять спринт", "Не отпускать спринт при столкновениях.", true));
    private boolean heldByUs;
    private volatile long suppressUntil;

    public AutoSprint() {
        super("Auto Sprint", "Удерживает спринт при движении вперёд", Category.UTILS);
    }

    public final void suppressFor(long ms) {
        long until = System.currentTimeMillis() + ms;
        if (until > this.suppressUntil) {
            this.suppressUntil = until;
        }
    }

    public final boolean suppressed() {
        return System.currentTimeMillis() < this.suppressUntil;
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onDisable() {
        this.releaseSprintKey();
    }

    @EventHandler
    public final void onInput(@NotNull InputEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        PlayerInput input = event.getInput();
        if (input.sprint() || !input.forward()) {
            return;
        }
        if (!this.allowsSprint(player)) {
            return;
        }
        event.setSprinting(true);
    }

    @EventHandler
    public final void onTick(@NotNull TickEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!event.isPre()) {
            return;
        }
        ClientPlayerEntity player = this.mc.player;
        if (player == null || this.mc.options == null || this.mc.getWindow() == null) {
            this.releaseSprintKey();
            return;
        }
        if (!this.forwardHeld(player) || !this.allowsSprint(player)) {
            this.releaseSprintKey();
            return;
        }
        this.setSprintKey(true);
        this.heldByUs = true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean forwardHeld(ClientPlayerEntity player) {
        PlayerInput keyPresses;
        Input input2 = player.input;
        Object object = keyPresses = input2 != null ? input2.playerInput : null;
        if (keyPresses != null && keyPresses.forward()) {
            return true;
        }
        if (this.mc.currentScreen != null) return false;
        KeyBinding keyBinding2 = this.mc.options.forwardKey;
        Intrinsics.checkNotNullExpressionValue((Object)keyBinding2, (String)"keyUp");
        if (!this.isPressed(keyBinding2)) return false;
        return true;
    }

    private final boolean allowsSprint(ClientPlayerEntity player) {
        if (this.suppressed()) {
            return false;
        }
        if (player.getHungerManager().getFoodLevel() <= 6) {
            return false;
        }
        if (player.hasStatusEffect(StatusEffects.BLINDNESS)) {
            return false;
        }
        return this.keepSprint.getValue() || !player.horizontalCollision;
    }

    private final void releaseSprintKey() {
        if (this.heldByUs) {
            this.heldByUs = false;
            if (this.mc.options != null && this.mc.getWindow() != null) {
                KeyBinding keyBinding2 = this.mc.options.sprintKey;
                Intrinsics.checkNotNullExpressionValue((Object)keyBinding2, (String)"keySprint");
                this.setSprintKey(this.isPressed(keyBinding2));
            }
        }
    }

    private final void setSprintKey(boolean down) {
        if (this.mc.options == null || this.mc.options.sprintKey == null) {
            return;
        }
        KeyBinding key = this.mc.options.sprintKey;
        if (key.isPressed() == down) {
            return;
        }
        key.setPressed(down);
        if (key.isPressed() != down) {
            key.setPressed(true);
        }
    }

    private final boolean isPressed(KeyBinding key) {
        if (key.isUnbound()) {
            return false;
        }
        InputUtil.Key key2 = InputUtil.fromTranslationKey((String)key.getBoundKeyTranslationKey());
        Intrinsics.checkNotNullExpressionValue((Object)key2, (String)"getKey(...)");
        InputUtil.Key inputKey = key2;
        long handle = this.mc.getWindow().getHandle();
        return switch (WhenMappings.$EnumSwitchMapping$0[inputKey.getCategory().ordinal()]) {
            case 1 -> InputUtil.isKeyPressed((Window)this.mc.getWindow(), (int)inputKey.getCode());
            case 2 -> {
                if (GLFW.glfwGetMouseButton((long)handle, (int)inputKey.getCode()) == 1) {
                    yield true;
                }
                yield false;
            }
            default -> false;
        };
    }

    @Metadata(mv={2, 4, 0}, k=3, xi=48)
    public static final class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] nArray = new int[InputUtil.Type.values().length];
            try {
                nArray[InputUtil.Type.KEYSYM.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[InputUtil.Type.MOUSE.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$0 = nArray;
        }
    }
}

