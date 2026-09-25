/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Pair
 *  kotlin.TuplesKt
 *  kotlin.collections.CollectionsKt
 *  kotlin.collections.MapsKt
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.chat.prefix;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010$\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u0016B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\u0010\r\u001a\u0004\u0018\u00010\tH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u000e\u0010\fJ\u001d\u0010\u000f\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\tH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R \u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/api/chat/prefix/ChatPrefixes;", "", "<init>", "()V", "", "Lrtx/kimiko/api/chat/prefix/ChatPrefixes$Entry;", "Lkotlin/jvm/JvmStatic;", "grantable", "()Ljava/util/List;", "", "id", "byId", "(Ljava/lang/String;)Lrtx/kimiko/api/chat/prefix/ChatPrefixes$Entry;", "role", "forRole", "roleLabel", "(Ljava/lang/String;)Ljava/lang/String;", "GRANTABLE", "Ljava/util/List;", "", "STAFF", "Ljava/util/Map;", "Entry", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nChatPrefixes.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ChatPrefixes.kt\nrtx/kimiko/api/chat/prefix/ChatPrefixes\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,61:1\n296#2,2:62\n*S KotlinDebug\n*F\n+ 1 ChatPrefixes.kt\nrtx/kimiko/api/chat/prefix/ChatPrefixes\n*L\n43#1:62,2\n*E\n"})
public final class ChatPrefixes {
    @NotNull
    public static final ChatPrefixes INSTANCE = new ChatPrefixes();
    @NotNull
    private static final List<Entry> GRANTABLE;
    @NotNull
    private static final Map<String, Entry> STAFF;

    private ChatPrefixes() {
    }

    @JvmStatic
    @NotNull
    public static final List<Entry> grantable() {
        return GRANTABLE;
    }

    @JvmStatic
    @Nullable
    public static final Entry byId(@Nullable String id) {
        Entry entry;
        Object v0;
        block3: {
            CharSequence charSequence = id;
            if (charSequence == null || StringsKt.isBlank((CharSequence)charSequence) || Intrinsics.areEqual((Object)id, (Object)"none")) {
                return null;
            }
            Iterable $this$firstOrNull$iv = GRANTABLE;
            boolean $i$f$firstOrNull = false;
            for (Object element$iv : $this$firstOrNull$iv) {
                Entry it = (Entry)element$iv;
                boolean bl = false;
                if (!Intrinsics.areEqual((Object)it.id(), (Object)id)) continue;
                v0 = element$iv;
                break block3;
            }
            v0 = null;
        }
        if ((entry = (Entry)v0) == null) {
            entry = STAFF.get(id);
        }
        return entry;
    }

    @JvmStatic
    @Nullable
    public static final Entry forRole(@Nullable String role) {
        CharSequence charSequence = role;
        if (charSequence == null || StringsKt.isBlank((CharSequence)charSequence) || Intrinsics.areEqual((Object)role, (Object)"none")) {
            return null;
        }
        return STAFF.get(role);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @JvmStatic
    @NotNull
    public static final String roleLabel(@Nullable String role) {
        String string = role;
        if (string == null) return "Игрок";
        int n = -1;
        switch (string.hashCode()) {
            case 106164915: {
                if (string.equals("owner")) {
                    n = 1;
                }
                break;
            }
            case -1220931666: {
                if (string.equals("helper")) {
                    n = 2;
                }
                break;
            }
            case -2004703995: {
                if (string.equals("moderator")) {
                    n = 3;
                }
                break;
            }
            case 92668751: {
                if (string.equals("admin")) {
                    n = 4;
                }
                break;
            }
        }
        switch (n) {
            case 1: {
                return "Владелец";
            }
            case 4: {
                return "Админ";
            }
            case 3: {
                return "Модератор";
            }
            case 2: {
                return "Помощник";
            }
            default: {
                return "Игрок";
            }
        }
    }

    static {
        GRANTABLE = List.of(new Entry("neon", "NEON", -16718337, -8630785, false), new Entry("sunset", "SUNSET", -30147, -49801, false), new Entry("royal", "ROYAL", -5210881, -10785793, false), new Entry("toxic", "TOXIC", -4915395, -15088771, false), new Entry("gold", "GOLD", -10934, -26081, false), new Entry("ice", "ICE", -6297345, -11891713, false), new Entry("blood", "BLOOD", -42406, -7663826, false), new Entry("candy", "CANDY", -25646, -6591489, false), new Entry("mint", "MINT", -8588336, -12739585, false), new Entry("ember", "EMBER", -15253, -2079445, false), new Entry("void", "VOID", -7563265, -12964742, false), new Entry("aqua", "AQUA", -11665440, -13726721, false), new Entry("vip", "VIP", -8054, -37930, false), new Entry("legend", "LEGEND", -3152, -8585294, false));
        STAFF = Map.of(
            "owner", new Entry("owner", "OWNER", -11702, -42435, true),
            "admin", new Entry("admin", "ADMIN", -38037, -5235713, true),
            "moderator", new Entry("moderator", "MOD", -11688193, -8630785, true),
            "helper", new Entry("helper", "HELPER", -8591200, -13519494, true)
        );
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u000bR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u000e\u001a\u0004\b\u0003\u0010\u000fR%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010\u000e\u001a\u0004\b\u0004\u0010\u000fR%\u0010\u0006\u001a\u00020\u00058\u0007z\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0010\u001a\u0004\b\u0006\u0010\u0011R%\u0010\u0007\u001a\u00020\u00058\u0007z\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0010\u001a\u0004\b\u0007\u0010\u0011R%\u0010\t\u001a\u00020\b8\u0007z\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\t\u00a2\u0006\f\n\u0004\b\t\u0010\u0012\u001a\u0004\b\t\u0010\u0013\u00a8\u0006\u0014"}, d2={"Lrtx/kimiko/api/chat/prefix/ChatPrefixes$Entry;", "", "", "id", "label", "", "from", "to", "", "staff", "<init>", "(Ljava/lang/String;Ljava/lang/String;IIZ)V", "Lkotlin/jvm/JvmName;", "name", "Ljava/lang/String;", "()Ljava/lang/String;", "I", "()I", "Z", "()Z", "rtx.kimiko:kimiko"})
    public static final class Entry {
        @NotNull
        private final String id;
        @NotNull
        private final String label;
        private final int from;
        private final int to;
        private final boolean staff;

        public Entry(@NotNull String id, @NotNull String label, int from, int to, boolean staff) {
            Intrinsics.checkNotNullParameter((Object)id, (String)"id");
            Intrinsics.checkNotNullParameter((Object)label, (String)"label");
            this.id = id;
            this.label = label;
            this.from = from;
            this.to = to;
            this.staff = staff;
        }

        @JvmName(name="id")
        @NotNull
        public final String id() {
            return this.id;
        }

        @JvmName(name="label")
        @NotNull
        public final String label() {
            return this.label;
        }

        @JvmName(name="from")
        public final int from() {
            return this.from;
        }

        @JvmName(name="to")
        public final int to() {
            return this.to;
        }

        @JvmName(name="staff")
        public final boolean staff() {
            return this.staff;
        }
    }
}

