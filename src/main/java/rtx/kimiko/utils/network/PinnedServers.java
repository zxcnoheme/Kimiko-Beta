/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.network;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001d\u0010\u000b\u001a\u00020\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000b\u0010\fR\u001f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\r8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u001f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\r8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000e\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0010\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/utils/network/PinnedServers;", "", "<init>", "()V", "", "address", "", "Lkotlin/jvm/JvmStatic;", "indexOf", "(Ljava/lang/String;)I", "", "isPinned", "(Ljava/lang/String;)Z", "", "Lkotlin/jvm/JvmField;", "NAMES", "[Ljava/lang/String;", "ADDRESSES", "rtx.kimiko:kimiko"})
public final class PinnedServers {
    @NotNull
    public static final PinnedServers INSTANCE = new PinnedServers();
    @JvmField
    @NotNull
    public static final String[] NAMES;
    @JvmField
    @NotNull
    public static final String[] ADDRESSES;

    private PinnedServers() {
    }

    @JvmStatic
    public static final int indexOf(@Nullable String address) {
        CharSequence charSequence = address;
        if (charSequence == null || StringsKt.isBlank((CharSequence)charSequence)) {
            return -1;
        }
        String string = ((Object)StringsKt.trim((CharSequence)address)).toString();
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string2 = string.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
        String normalized = StringsKt.removeSuffix((String)string2, (CharSequence)":25565");
        int n = ADDRESSES.length;
        for (int i = 0; i < n; ++i) {
            if (!Intrinsics.areEqual((Object)ADDRESSES[i], (Object)normalized)) continue;
            return i;
        }
        return -1;
    }

    @JvmStatic
    public static final boolean isPinned(@Nullable String address) {
        return PinnedServers.indexOf(address) >= 0;
    }

    static {
        String[] stringArray = new String[]{};
        NAMES = stringArray;
        stringArray = new String[]{};
        ADDRESSES = stringArray;
    }
}

