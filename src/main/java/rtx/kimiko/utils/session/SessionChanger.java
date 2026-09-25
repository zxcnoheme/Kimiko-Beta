/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.Charsets
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.session.Session
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.session;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import mods.acountswiher.ru.vidtu.ias.auth.LoginData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.session.Session;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J#\u0010\t\u001a\u00020\u00072\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001d\u0010\r\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0007b\u0002\b\b\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0011\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u0011\u0010\u0012J9\u0010\u0019\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0018\u001a\u00020\u0017H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u000bH\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001e\u00a8\u0006\u001f"}, d2={"Lrtx/kimiko/utils/session/SessionChanger;", "", "<init>", "()V", "Ljava/util/function/Consumer;", "Lnet/minecraft/Session;", "setter", "", "Lkotlin/jvm/JvmStatic;", "setSessionSetter", "(Ljava/util/function/Consumer;)V", "", "newUsername", "changeUsername", "(Ljava/lang/String;)V", "Lmods/acountswiher/ru/vidtu/ias/auth/LoginData;", "data", "applyLoginData", "(Lmods/acountswiher/ru/vidtu/ias/auth/LoginData;)V", "username", "Ljava/util/UUID;", "uuid", "token", "", "online", "changeSession", "(Ljava/lang/String;Ljava/util/UUID;Ljava/lang/String;Z)V", "getCurrentUsername", "()Ljava/lang/String;", "sessionSetter", "Ljava/util/function/Consumer;", "rtx.kimiko:kimiko"})
public final class SessionChanger {
    @NotNull
    public static final SessionChanger INSTANCE = new SessionChanger();
    @Nullable
    private static Consumer<Session> sessionSetter;

    private SessionChanger() {
    }

    @JvmStatic
    public static final void setSessionSetter(@Nullable Consumer<Session> setter) {
        sessionSetter = setter;
    }

    @JvmStatic
    public static final void changeUsername(@Nullable String newUsername) {
        CharSequence charSequence;
        if (sessionSetter == null || (charSequence = (CharSequence)newUsername) == null || charSequence.length() == 0) {
            return;
        }
        String string = "OfflinePlayer:" + newUsername;
        byte[] byArray = string.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"getBytes(...)");
        UUID uuid = UUID.nameUUIDFromBytes(byArray);
        SessionChanger.changeSession(newUsername, uuid, "", false);
    }

    @JvmStatic
    public static final void applyLoginData(@Nullable LoginData data) {
        if (data == null) {
            return;
        }
        SessionChanger.changeSession(data.name(), data.uuid(), data.token(), data.online());
    }

    @JvmStatic
    public static final void changeSession(@Nullable String username, @Nullable UUID uuid, @Nullable String token, boolean online) {
        Consumer<Session> consumer = sessionSetter;
        if (consumer == null) {
            return;
        }
        Consumer<Session> setter = consumer;
        CharSequence charSequence = username;
        if (charSequence == null || charSequence.length() == 0 || uuid == null) {
            return;
        }
        String string = token;
        if (string == null) {
            string = "";
        }
        Session newSession = new Session(username, uuid, string, Optional.empty(), Optional.empty());
        setter.accept(newSession);
    }

    @JvmStatic
    @NotNull
    public static final String getCurrentUsername() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null || mc.getSession() == null || mc.getSession().getUsername() == null) {
            return "";
        }
        return mc.getSession().getUsername();
    }
}

