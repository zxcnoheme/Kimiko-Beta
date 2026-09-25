/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.storage.macro;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\nJ\u0010\u0010\f\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ.\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00c6\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0014\u001a\u00020\u0005H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0014\u0010\rJ\u0011\u0010\u0015\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0015\u0010\nR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u0016\u0012\b\b\u0003\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0003\u0010\nR%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b\u0016\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u0004\u0010\nR%\u0010\u0006\u001a\u00020\u00058\u0007z\f\b\u0016\u0012\b\b\u0003\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0018\u001a\u0004\b\u0006\u0010\r\u00a8\u0006\u0019"}, d2={"Lrtx/kimiko/utils/storage/macro/Macro;", "", "", "name", "message", "", "key", "<init>", "(Ljava/lang/String;Ljava/lang/String;I)V", "component1", "()Ljava/lang/String;", "component2", "component3", "()I", "copy", "(Ljava/lang/String;Ljava/lang/String;I)Lrtx/kimiko/utils/storage/macro/Macro;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "Lkotlin/jvm/JvmName;", "Ljava/lang/String;", "I", "rtx.kimiko:kimiko"})
public final class Macro {
    @NotNull
    private final String name;
    @NotNull
    private final String message;
    private final int key;

    public Macro(@NotNull String name, @NotNull String message, int key) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter((Object)message, (String)"message");
        this.name = name;
        this.message = message;
        this.key = key;
    }

    @JvmName(name="name")
    @NotNull
    public final String name() {
        return this.name;
    }

    @JvmName(name="message")
    @NotNull
    public final String message() {
        return this.message;
    }

    @JvmName(name="key")
    public final int key() {
        return this.key;
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final String component2() {
        return this.message;
    }

    public final int component3() {
        return this.key;
    }

    @NotNull
    public final Macro copy(@NotNull String name, @NotNull String message, int key) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter((Object)message, (String)"message");
        return new Macro(name, message, key);
    }

    public static /* synthetic */ Macro copy$default(Macro macro, String string, String string2, int n, int n2, Object object) {
        if ((n2 & 1) != 0) {
            string = macro.name;
        }
        if ((n2 & 2) != 0) {
            string2 = macro.message;
        }
        if ((n2 & 4) != 0) {
            n = macro.key;
        }
        return macro.copy(string, string2, n);
    }

    @NotNull
    public String toString() {
        return "Macro(name=" + this.name + ", message=" + this.message + ", key=" + this.key + ")";
    }

    public int hashCode() {
        int result = this.name.hashCode();
        result = result * 31 + this.message.hashCode();
        result = result * 31 + Integer.hashCode(this.key);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Macro)) {
            return false;
        }
        Macro macro = (Macro)other;
        if (!Intrinsics.areEqual((Object)this.name, (Object)macro.name)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.message, (Object)macro.message)) {
            return false;
        }
        return this.key == macro.key;
    }
}

