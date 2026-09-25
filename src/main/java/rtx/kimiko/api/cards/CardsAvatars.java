/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.cards;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.cards.CardsClient;
import rtx.kimiko.api.ui.configs.RemoteAvatars;
import rtx.kimiko.api.ui.module.DiscordAvatar;
import rtx.kimiko.utils.profile.ProfileIdentity;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J)\u0010\n\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u000bJ)\u0010\f\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\u000bJ\u001d\u0010\u000e\u001a\u00020\r2\b\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0007J\u0013\u0010\u0012\u001a\u00020\u0011H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0014"}, d2={"Lrtx/kimiko/api/cards/CardsAvatars;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "selfUrl", "()Ljava/lang/String;", "playerId", "declaredUrl", "urlFor", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "texture", "", "isSelf", "(Ljava/lang/String;)Z", "selfTexture", "", "uid", "()I", "rtx.kimiko:kimiko"})
public final class CardsAvatars {
    @NotNull
    public static final CardsAvatars INSTANCE = new CardsAvatars();

    private CardsAvatars() {
    }

    @JvmStatic
    @Nullable
    public static final String selfUrl() {
        String url = DiscordAvatar.currentUrl();
        return url == null || StringsKt.isBlank((CharSequence)url) ? null : url;
    }

    @JvmStatic
    @Nullable
    public static final String urlFor(@Nullable String playerId, @Nullable String declaredUrl) {
        if (declaredUrl != null && !StringsKt.isBlank((CharSequence)declaredUrl)) {
            return declaredUrl;
        }
        if (playerId == null || StringsKt.isBlank((CharSequence)playerId)) {
            return null;
        }
        try {
            int uid = Integer.parseInt(((Object)StringsKt.trim((CharSequence)playerId)).toString());
            if (uid > 0) {
                return "https://kimiko.tech/api/account/avatar/" + uid;
            }
        }
        catch (NumberFormatException numberFormatException) {
            // empty catch block
        }
        return null;
    }

    @JvmStatic
    @Nullable
    public static final String texture(@Nullable String playerId, @Nullable String declaredUrl) {
        String url;
        String own;
        if (CardsAvatars.isSelf(playerId) && (own = DiscordAvatar.texture()) != null) {
            return own;
        }
        String string = url = CardsAvatars.urlFor(playerId, declaredUrl);
        return string == null ? null : RemoteAvatars.texture(string);
    }

    @JvmStatic
    public static final boolean isSelf(@Nullable String playerId) {
        if (playerId == null || StringsKt.isBlank((CharSequence)playerId)) {
            return false;
        }
        return Intrinsics.areEqual((Object)playerId, (Object)CardsClient.INSTANCE.selfId());
    }

    @JvmStatic
    @Nullable
    public static final String selfTexture() {
        return DiscordAvatar.texture();
    }

    @JvmStatic
    public static final int uid() {
        return ProfileIdentity.uid();
    }
}

