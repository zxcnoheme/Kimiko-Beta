/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.sound.PositionedSoundInstance
 *  net.minecraft.client.sound.SoundInstance
 *  net.minecraft.client.sound.SoundManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.registry.Registry
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.sound.SoundEvent
 *  net.minecraft.sound.SoundCategory
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.ClientPlayerEntity
 *  net.minecraft.registry.Registries
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.sounds;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.entity.Entity;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundCategory;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.registry.Registries;
import org.jetbrains.annotations.NotNull;
import sigil.protect.Level;
import sigil.protect.Protect;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\bE\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J#\u0010\n\u001a\u00020\u0004H\u0007b\u0002\b\u0005b\u000e\b\u0006\u0012\n\b\u0007\u0012\u0006\b\n0\b8\t\u00a2\u0006\u0004\b\n\u0010\u0003J+\u0010\u0010\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0011J+\u0010\u0012\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0011J\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\f\u0010\u0015J'\u0010\u0016\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0003b\u000e\b\u0006\u0012\n\b\u0007\u0012\u0006\b\n0\b8\t\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u0019\u0010\u0019\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0019\u0010\u001b\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001aR\u0019\u0010\u001c\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001aR\u0019\u0010\u001d\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001aR\u0019\u0010\u001e\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001aR\u0019\u0010\u001f\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001aR\u0019\u0010 \u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b \u0010\u001aR\u0019\u0010!\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b!\u0010\u001aR\u0019\u0010\"\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b\"\u0010\u001aR\u0019\u0010#\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b#\u0010\u001aR\u0019\u0010$\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b$\u0010\u001aR\u0019\u0010%\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b%\u0010\u001aR\u0019\u0010&\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b&\u0010\u001aR\u0019\u0010'\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b'\u0010\u001aR\u0019\u0010(\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b(\u0010\u001aR\u0019\u0010)\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b)\u0010\u001aR\u0019\u0010*\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b*\u0010\u001aR\u0019\u0010+\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b+\u0010\u001aR\u0019\u0010,\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b,\u0010\u001aR\u0019\u0010-\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b-\u0010\u001aR\u0019\u0010.\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b.\u0010\u001aR\u0019\u0010/\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b/\u0010\u001aR\u0019\u00100\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b0\u0010\u001aR\u0019\u00101\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b1\u0010\u001aR\u0019\u00102\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b2\u0010\u001aR\u0019\u00103\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b3\u0010\u001aR\u0019\u00104\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b4\u0010\u001aR\u0019\u00105\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b5\u0010\u001aR\u0019\u00106\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b6\u0010\u001aR\u0019\u00107\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b7\u0010\u001aR\u0019\u00108\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b8\u0010\u001aR\u0019\u00109\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b9\u0010\u001aR\u0019\u0010:\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b:\u0010\u001aR\u0019\u0010;\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b;\u0010\u001aR\u0019\u0010<\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b<\u0010\u001aR\u0019\u0010=\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b=\u0010\u001aR\u0019\u0010>\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b>\u0010\u001aR\u0019\u0010?\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b?\u0010\u001aR\u0019\u0010@\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b@\u0010\u001aR\u0019\u0010A\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bA\u0010\u001aR\u0019\u0010B\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bB\u0010\u001aR\u0019\u0010C\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bC\u0010\u001aR\u0019\u0010D\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bD\u0010\u001aR\u0019\u0010E\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bE\u0010\u001aR\u0019\u0010F\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bF\u0010\u001aR\u0019\u0010G\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bG\u0010\u001aR\u0019\u0010H\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bH\u0010\u001aR\u0019\u0010I\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bI\u0010\u001aR\u0019\u0010J\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bJ\u0010\u001aR\u0019\u0010K\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bK\u0010\u001aR\u0019\u0010L\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bL\u0010\u001aR\u0019\u0010M\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bM\u0010\u001aR\u0019\u0010N\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bN\u0010\u001aR\u0019\u0010O\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bO\u0010\u001aR\u0019\u0010P\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bP\u0010\u001aR\u0019\u0010Q\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bQ\u0010\u001aR\u0019\u0010R\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bR\u0010\u001aR\u0019\u0010S\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bS\u0010\u001aR\u0019\u0010T\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bT\u0010\u001aR\u0019\u0010U\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bU\u0010\u001aR\u0019\u0010V\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bV\u0010\u001aR\u0019\u0010W\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bW\u0010\u001aR\u0019\u0010X\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bX\u0010\u001aR\u0019\u0010Y\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bY\u0010\u001aR\u0019\u0010Z\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\bZ\u0010\u001aR\u0019\u0010[\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b[\u0010\u001aR\u0019\u0010\\\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b\\\u0010\u001aR\u0019\u0010]\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0018\u00a2\u0006\u0006\n\u0004\b]\u0010\u001aR\u001a\u0010_\u001a\b\u0012\u0004\u0012\u00020\u000b0^8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b_\u0010`R\u0016\u0010b\u001a\u00020a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bb\u0010c\u00a8\u0006d"}, d2={"Lrtx/kimiko/utils/sounds/SoundManager;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "init", "Lnet/minecraft/SoundEvent;", "sound", "", "volume", "pitch", "playSound", "(Lnet/minecraft/SoundEvent;FF)V", "playSoundDirect", "", "path", "(Ljava/lang/String;)Lnet/minecraft/SoundEvent;", "register", "(Lnet/minecraft/SoundEvent;)V", "Lkotlin/jvm/JvmField;", "MOAN1", "Lnet/minecraft/SoundEvent;", "MOAN2", "MOAN3", "MOAN4", "CRIME", "METALLIC", "MODULE_ENABLE", "MODULE_DISABLE", "ON", "OFF", "MODULE_ENABLE_TYPE2", "MODULE_DISABLE_TYPE2", "SEARCH_TYPING", "SLIDER", "SETTINGS_SLIDER", "SWITCH_CATEGORY", "NO_SETTINGS", "BUTTON_CLICK", "UNKNOWN_COMMAND", "COMMAND_ERROR", "OPEN_GUI", "CLOSE_GUI", "SELECT_CATEGORY", "NOTIFICATION", "NOTIFICATION_LOW", "PLAYER_PING", "MINE", "ACCOUNT_SWITCH", "SWITCH_LEFT", "SWITCH_RIGHT", "SETTINGS_OPEN", "SETTINGS_CLOSE", "GUI_CATEGORY_SELECT", "RIBBIT_AMBIENT", "RIBBIT_STEP", "RIBBIT_HURT", "RIBBIT_DEATH", "NOTIFICATION_DIRECT", "LOW", "PLAYERPING", "ON_DIRECT", "OFF_DIRECT", "FRAG_EFFECT_ECHO_MAIN", "FRAG_EFFECT_KNOCK_MAIN", "FRAG_EFFECT_PULSE", "FRAG_EFFECT_SPARKS_COLLISION", "SWITCH_CATEGORY_SELECT", "SWITCH_LEFT_DIRECT", "SWITCH_RIGHT_DIRECT", "SETTINGS_OPEN_DIRECT", "SETTINGS_CLOSE_DIRECT", "MODULE_SETTINGS_OPEN", "MODULE_SETTINGS_CLOSE", "TOGGLE1_ON", "TOGGLE1_OFF", "RIBBIT_AMBIENT1", "RIBBIT_AMBIENT2", "RIBBIT_AMBIENT3", "RIBBIT_AMBIENT4", "RIBBIT_AMBIENT5", "RIBBIT_STEP1", "RIBBIT_STEP2", "RIBBIT_HURT1", "RIBBIT_HURT2", "RIBBIT_HURT3", "RIBBIT_DEATH1", "RIBBIT_DEATH2", "WASTED", "", "ALL_SOUNDS", "[Lnet/minecraft/SoundEvent;", "", "initialized", "Z", "rtx.kimiko:kimiko"})
public final class SoundManager {
    @NotNull
    public static final SoundManager INSTANCE = new SoundManager();
    @JvmField
    @NotNull
    public static final SoundEvent MOAN1 = INSTANCE.sound("moan1");
    @JvmField
    @NotNull
    public static final SoundEvent MOAN2 = INSTANCE.sound("moan2");
    @JvmField
    @NotNull
    public static final SoundEvent MOAN3 = INSTANCE.sound("moan3");
    @JvmField
    @NotNull
    public static final SoundEvent MOAN4 = INSTANCE.sound("moan4");
    @JvmField
    @NotNull
    public static final SoundEvent CRIME = INSTANCE.sound("crime");
    @JvmField
    @NotNull
    public static final SoundEvent METALLIC = INSTANCE.sound("metallic");
    @JvmField
    @NotNull
    public static final SoundEvent MODULE_ENABLE = INSTANCE.sound("module_enable");
    @JvmField
    @NotNull
    public static final SoundEvent MODULE_DISABLE = INSTANCE.sound("module_disable");
    @JvmField
    @NotNull
    public static final SoundEvent ON = INSTANCE.sound("on");
    @JvmField
    @NotNull
    public static final SoundEvent OFF = INSTANCE.sound("off");
    @JvmField
    @NotNull
    public static final SoundEvent MODULE_ENABLE_TYPE2 = INSTANCE.sound("module_enable_type2");
    @JvmField
    @NotNull
    public static final SoundEvent MODULE_DISABLE_TYPE2 = INSTANCE.sound("module_disable_type2");
    @JvmField
    @NotNull
    public static final SoundEvent SEARCH_TYPING = INSTANCE.sound("searchtyping");
    @JvmField
    @NotNull
    public static final SoundEvent SLIDER = INSTANCE.sound("slider");
    @JvmField
    @NotNull
    public static final SoundEvent SETTINGS_SLIDER = INSTANCE.sound("settings_slider");
    @JvmField
    @NotNull
    public static final SoundEvent SWITCH_CATEGORY = INSTANCE.sound("switchcategory");
    @JvmField
    @NotNull
    public static final SoundEvent NO_SETTINGS = INSTANCE.sound("no-settings");
    @JvmField
    @NotNull
    public static final SoundEvent BUTTON_CLICK = INSTANCE.sound("buttonclick");
    @JvmField
    @NotNull
    public static final SoundEvent UNKNOWN_COMMAND = INSTANCE.sound("unknowncommand");
    @JvmField
    @NotNull
    public static final SoundEvent COMMAND_ERROR = INSTANCE.sound("command_error");
    @JvmField
    @NotNull
    public static final SoundEvent OPEN_GUI = INSTANCE.sound("open_gui");
    @JvmField
    @NotNull
    public static final SoundEvent CLOSE_GUI = INSTANCE.sound("close_gui");
    @JvmField
    @NotNull
    public static final SoundEvent SELECT_CATEGORY = INSTANCE.sound("select_category");
    @JvmField
    @NotNull
    public static final SoundEvent NOTIFICATION = INSTANCE.sound("welcome");
    @JvmField
    @NotNull
    public static final SoundEvent NOTIFICATION_LOW = INSTANCE.sound("notification_low");
    @JvmField
    @NotNull
    public static final SoundEvent PLAYER_PING = INSTANCE.sound("player_ping");
    @JvmField
    @NotNull
    public static final SoundEvent MINE = INSTANCE.sound("mine");
    @JvmField
    @NotNull
    public static final SoundEvent ACCOUNT_SWITCH = INSTANCE.sound("accountswitch");
    @JvmField
    @NotNull
    public static final SoundEvent SWITCH_LEFT = INSTANCE.sound("switch_left");
    @JvmField
    @NotNull
    public static final SoundEvent SWITCH_RIGHT = INSTANCE.sound("switch_right");
    @JvmField
    @NotNull
    public static final SoundEvent SETTINGS_OPEN = INSTANCE.sound("settings_open");
    @JvmField
    @NotNull
    public static final SoundEvent SETTINGS_CLOSE = INSTANCE.sound("settings_close");
    @JvmField
    @NotNull
    public static final SoundEvent GUI_CATEGORY_SELECT = INSTANCE.sound("gui_category_select");
    @JvmField
    @NotNull
    public static final SoundEvent RIBBIT_AMBIENT = INSTANCE.sound("entity.ribbit.ambient");
    @JvmField
    @NotNull
    public static final SoundEvent RIBBIT_STEP = INSTANCE.sound("entity.ribbit.step");
    @JvmField
    @NotNull
    public static final SoundEvent RIBBIT_HURT = INSTANCE.sound("entity.ribbit.hurt");
    @JvmField
    @NotNull
    public static final SoundEvent RIBBIT_DEATH = INSTANCE.sound("entity.ribbit.death");
    @JvmField
    @NotNull
    public static final SoundEvent NOTIFICATION_DIRECT = INSTANCE.sound("notif");
    @JvmField
    @NotNull
    public static final SoundEvent LOW = INSTANCE.sound("low");
    @JvmField
    @NotNull
    public static final SoundEvent PLAYERPING = INSTANCE.sound("playerping");
    @JvmField
    @NotNull
    public static final SoundEvent ON_DIRECT = INSTANCE.sound("on1");
    @JvmField
    @NotNull
    public static final SoundEvent OFF_DIRECT = INSTANCE.sound("off1");
    @JvmField
    @NotNull
    public static final SoundEvent FRAG_EFFECT_ECHO_MAIN = INSTANCE.sound("frag_effect_echo_main");
    @JvmField
    @NotNull
    public static final SoundEvent FRAG_EFFECT_KNOCK_MAIN = INSTANCE.sound("frag_effect_knock_main");
    @JvmField
    @NotNull
    public static final SoundEvent FRAG_EFFECT_PULSE = INSTANCE.sound("frag_effect_pulse");
    @JvmField
    @NotNull
    public static final SoundEvent FRAG_EFFECT_SPARKS_COLLISION = INSTANCE.sound("frag_effect_sparks_collision");
    @JvmField
    @NotNull
    public static final SoundEvent SWITCH_CATEGORY_SELECT = INSTANCE.sound("guicategory_select");
    @JvmField
    @NotNull
    public static final SoundEvent SWITCH_LEFT_DIRECT = INSTANCE.sound("switch-left");
    @JvmField
    @NotNull
    public static final SoundEvent SWITCH_RIGHT_DIRECT = INSTANCE.sound("switch-right");
    @JvmField
    @NotNull
    public static final SoundEvent SETTINGS_OPEN_DIRECT = INSTANCE.sound("settings-open");
    @JvmField
    @NotNull
    public static final SoundEvent SETTINGS_CLOSE_DIRECT = INSTANCE.sound("settings-close");
    @JvmField
    @NotNull
    public static final SoundEvent MODULE_SETTINGS_OPEN = INSTANCE.sound("module_settings_open");
    @JvmField
    @NotNull
    public static final SoundEvent MODULE_SETTINGS_CLOSE = INSTANCE.sound("module_settings_close");
    @JvmField
    @NotNull
    public static final SoundEvent TOGGLE1_ON = INSTANCE.sound("toggle1_on");
    @JvmField
    @NotNull
    public static final SoundEvent TOGGLE1_OFF = INSTANCE.sound("toggle1_off");
    @JvmField
    @NotNull
    public static final SoundEvent RIBBIT_AMBIENT1 = INSTANCE.sound("ribbit_ambient1");
    @JvmField
    @NotNull
    public static final SoundEvent RIBBIT_AMBIENT2 = INSTANCE.sound("ribbit_ambient2");
    @JvmField
    @NotNull
    public static final SoundEvent RIBBIT_AMBIENT3 = INSTANCE.sound("ribbit_ambient3");
    @JvmField
    @NotNull
    public static final SoundEvent RIBBIT_AMBIENT4 = INSTANCE.sound("ribbit_ambient4");
    @JvmField
    @NotNull
    public static final SoundEvent RIBBIT_AMBIENT5 = INSTANCE.sound("ribbit_ambient5");
    @JvmField
    @NotNull
    public static final SoundEvent RIBBIT_STEP1 = INSTANCE.sound("ribbit_step1");
    @JvmField
    @NotNull
    public static final SoundEvent RIBBIT_STEP2 = INSTANCE.sound("ribbit_step2");
    @JvmField
    @NotNull
    public static final SoundEvent RIBBIT_HURT1 = INSTANCE.sound("ribbit_hurt1");
    @JvmField
    @NotNull
    public static final SoundEvent RIBBIT_HURT2 = INSTANCE.sound("ribbit_hurt2");
    @JvmField
    @NotNull
    public static final SoundEvent RIBBIT_HURT3 = INSTANCE.sound("ribbit_hurt3");
    @JvmField
    @NotNull
    public static final SoundEvent RIBBIT_DEATH1 = INSTANCE.sound("ribbit_death1");
    @JvmField
    @NotNull
    public static final SoundEvent RIBBIT_DEATH2 = INSTANCE.sound("ribbit_death2");
    @JvmField
    @NotNull
    public static final SoundEvent WASTED = INSTANCE.sound("wasted");
    @NotNull
    private static final SoundEvent[] ALL_SOUNDS;
    private static boolean initialized;

    private SoundManager() {
    }

    @JvmStatic
    @Protect(value=Level.CROWN)
    public static final void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        for (SoundEvent sound : ALL_SOUNDS) {
            INSTANCE.register(sound);
        }
    }

    @JvmStatic
    public static final void playSound(@NotNull SoundEvent sound, float volume, float pitch) {
        Intrinsics.checkNotNullParameter((Object)sound, (String)"sound");
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        ClientPlayerEntity clientPlayerEntity2 = mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        ClientWorld clientWorld3 = mc.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        level.playSound((Entity)player, player.getBlockPos(), sound, SoundCategory.BLOCKS, volume, pitch);
    }

    @JvmStatic
    public static final void playSoundDirect(@NotNull SoundEvent sound, float volume, float pitch) {
        block0: {
            Intrinsics.checkNotNullParameter((Object)sound, (String)"sound");
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient mc = minecraftClient2;
            net.minecraft.client.sound.SoundManager soundManager2 = mc.getSoundManager();
            if (soundManager2 == null) break block0;
            soundManager2.play((SoundInstance)PositionedSoundInstance.ui((SoundEvent)sound, (float)pitch, (float)volume));
        }
    }

    private final SoundEvent sound(String path) {
        SoundEvent soundEvent2 = SoundEvent.of((Identifier)Identifier.of((String)"kimiko", (String)path));
        Intrinsics.checkNotNullExpressionValue((Object)soundEvent2, (String)"createVariableRangeEvent(...)");
        return soundEvent2;
    }

    @Protect(value=Level.CROWN)
    private final void register(SoundEvent sound) {
        Registry.register((Registry)Registries.SOUND_EVENT, (Identifier)sound.id(), (Object)sound);
    }

    static {
        SoundEvent[] class_3414Array = new SoundEvent[]{MOAN1, MOAN2, MOAN3, MOAN4, CRIME, METALLIC, MODULE_ENABLE, MODULE_DISABLE, ON, OFF, MODULE_ENABLE_TYPE2, MODULE_DISABLE_TYPE2, SEARCH_TYPING, SLIDER, SETTINGS_SLIDER, SWITCH_CATEGORY, NO_SETTINGS, BUTTON_CLICK, UNKNOWN_COMMAND, COMMAND_ERROR, OPEN_GUI, CLOSE_GUI, SELECT_CATEGORY, NOTIFICATION, NOTIFICATION_LOW, PLAYER_PING, MINE, ACCOUNT_SWITCH, SWITCH_LEFT, SWITCH_RIGHT, SETTINGS_OPEN, SETTINGS_CLOSE, GUI_CATEGORY_SELECT, RIBBIT_AMBIENT, RIBBIT_STEP, RIBBIT_HURT, RIBBIT_DEATH, NOTIFICATION_DIRECT, LOW, PLAYERPING, ON_DIRECT, OFF_DIRECT, FRAG_EFFECT_ECHO_MAIN, FRAG_EFFECT_KNOCK_MAIN, FRAG_EFFECT_PULSE, FRAG_EFFECT_SPARKS_COLLISION, SWITCH_CATEGORY_SELECT, SWITCH_LEFT_DIRECT, SWITCH_RIGHT_DIRECT, SETTINGS_OPEN_DIRECT, SETTINGS_CLOSE_DIRECT, MODULE_SETTINGS_OPEN, MODULE_SETTINGS_CLOSE, TOGGLE1_ON, TOGGLE1_OFF, RIBBIT_AMBIENT1, RIBBIT_AMBIENT2, RIBBIT_AMBIENT3, RIBBIT_AMBIENT4, RIBBIT_AMBIENT5, RIBBIT_STEP1, RIBBIT_STEP2, RIBBIT_HURT1, RIBBIT_HURT2, RIBBIT_HURT3, RIBBIT_DEATH1, RIBBIT_DEATH2, WASTED};
        ALL_SOUNDS = class_3414Array;
    }
}

