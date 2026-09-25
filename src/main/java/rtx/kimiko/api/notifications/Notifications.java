/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.text.StringsKt
 *  net.minecraft.sound.SoundEvent
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.notifications;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.text.StringsKt;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Interface.NotificationsModule;
import rtx.kimiko.utils.sounds.SoundManager;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J/\u0010\u000b\u001a\u00020\t2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u000b\u0010\fJ9\u0010\u000b\u001a\u00020\t2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u000b\u0010\u000f\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/api/notifications/Notifications;", "", "<init>", "()V", "", "title", "text", "", "stayMs", "", "Lkotlin/jvm/JvmStatic;", "push", "(Ljava/lang/String;Ljava/lang/String;J)V", "Lnet/minecraft/SoundEvent;", "sound", "(Ljava/lang/String;Ljava/lang/String;JLnet/minecraft/SoundEvent;)V", "rtx.kimiko:kimiko"})
public final class Notifications {
    @NotNull
    public static final Notifications INSTANCE = new Notifications();

    private Notifications() {
    }

    @JvmStatic
    public static final void push(@Nullable String title, @Nullable String text, long stayMs) {
        Notifications.push(title, text, stayMs, null);
    }

    @JvmStatic
    public static final void push(@Nullable String title, @Nullable String text, long stayMs, @Nullable SoundEvent sound) {
        if (sound != null) {
            SoundManager.playSoundDirect(sound, 1.0f, 1.0f);
        }
        Object object = title == null || StringsKt.isBlank((CharSequence)title) ? "" : title + ": ";
        String string = text;
        if (string == null) {
            string = "";
        }
        String line = (String)object + string;
        NotificationsModule.Companion.notify(line, stayMs);
    }
}

