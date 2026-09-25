/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils.guishare;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.config.ConfigLibrary;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.ui.ClientLanguage;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u0000 \"2\u00020\u0001:\u0001\"B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0007\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0007\u00a2\u0006\u0004\b\u000e\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\rJ\u0010\u0010\u0015\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\rJB\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007H\u00c6\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u001a\u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0011\u0010\u001c\u001a\u00020\u0005H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\u0013J\u0011\u0010\u001d\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001d\u0010\u0010R\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u001fR\u0019\u0010\u0004\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u001fR\u0019\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u0006\u0010 R\u0019\u0010\b\u001a\u00020\u00078\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\b\u0010!R\u0019\u0010\t\u001a\u00020\u00078\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\t\u0010!\u00a8\u0006#"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareConfigRow;", "Ljava/lang/Record;", "", "name", "subtitle", "", "kind", "", "cloud", "active", "<init>", "(Ljava/lang/String;Ljava/lang/String;IZZ)V", "premium", "()Z", "readOnly", "component1", "()Ljava/lang/String;", "component2", "component3", "()I", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;IZZ)Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareConfigRow;", "", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "Lkotlin/jvm/JvmField;", "Ljava/lang/String;", "I", "Z", "Companion", "rtx.kimiko:kimiko"})
public final class GuiShareConfigRow
{
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public final String name;
    @JvmField
    @NotNull
    public final String subtitle;
    @JvmField
    public final int kind;
    @JvmField
    public final boolean cloud;
    @JvmField
    public final boolean active;
    public static final int KIND_LOCAL = 0;
    public static final int KIND_REMOTE = 1;
    public static final int KIND_PREMIUM = 2;
    public static final int MAX_ROWS = 24;
    public static final int MAX_NAME = 32;
    public static final int MAX_SUBTITLE = 48;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public GuiShareConfigRow(@NotNull String name, @NotNull String subtitle, int kind, boolean cloud, boolean active) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter((Object)subtitle, (String)"subtitle");
        this.name = name;
        this.subtitle = subtitle;
        this.kind = kind;
        this.cloud = cloud;
        this.active = active;
    }

    public final boolean premium() {
        return this.kind == 2;
    }

    public final boolean readOnly() {
        return this.kind != 0;
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final String component2() {
        return this.subtitle;
    }

    public final int component3() {
        return this.kind;
    }

    public final boolean component4() {
        return this.cloud;
    }

    public final boolean component5() {
        return this.active;
    }

    @NotNull
    public final GuiShareConfigRow copy(@NotNull String name, @NotNull String subtitle, int kind, boolean cloud, boolean active) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter((Object)subtitle, (String)"subtitle");
        return new GuiShareConfigRow(name, subtitle, kind, cloud, active);
    }

    public static /* synthetic */ GuiShareConfigRow copy$default(GuiShareConfigRow guiShareConfigRow, String string, String string2, int n, boolean bl, boolean bl2, int n2, Object object) {
        if ((n2 & 1) != 0) {
            string = guiShareConfigRow.name;
        }
        if ((n2 & 2) != 0) {
            string2 = guiShareConfigRow.subtitle;
        }
        if ((n2 & 4) != 0) {
            n = guiShareConfigRow.kind;
        }
        if ((n2 & 8) != 0) {
            bl = guiShareConfigRow.cloud;
        }
        if ((n2 & 0x10) != 0) {
            bl2 = guiShareConfigRow.active;
        }
        return guiShareConfigRow.copy(string, string2, n, bl, bl2);
    }

    @Override
    @NotNull
    public String toString() {
        return "GuiShareConfigRow(name=" + this.name + ", subtitle=" + this.subtitle + ", kind=" + this.kind + ", cloud=" + this.cloud + ", active=" + this.active + ")";
    }

    @Override
    public int hashCode() {
        int result = this.name.hashCode();
        result = result * 31 + this.subtitle.hashCode();
        result = result * 31 + Integer.hashCode(this.kind);
        result = result * 31 + Boolean.hashCode(this.cloud);
        result = result * 31 + Boolean.hashCode(this.active);
        return result;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GuiShareConfigRow)) {
            return false;
        }
        GuiShareConfigRow guiShareConfigRow = (GuiShareConfigRow)other;
        if (!Intrinsics.areEqual((Object)this.name, (Object)guiShareConfigRow.name)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.subtitle, (Object)guiShareConfigRow.subtitle)) {
            return false;
        }
        if (this.kind != guiShareConfigRow.kind) {
            return false;
        }
        if (this.cloud != guiShareConfigRow.cloud) {
            return false;
        }
        return this.active == guiShareConfigRow.active;
    }

    @JvmStatic
    @NotNull
    public static final List<GuiShareConfigRow> capture() {
        return Companion.capture();
    }

    @JvmStatic
    public static final int kindOf(@Nullable ConfigLibrary.Kind kind) {
        return Companion.kindOf(kind);
    }

    @JvmStatic
    public static final int clampKind(int kind) {
        return Companion.clampKind(kind);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ3\u0010\u0010\u001a\u00020\u000f2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010!\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\u00148\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\u00148\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b%\u0010$R\u0014\u0010&\u001a\u00020\u00148\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b&\u0010$R\u0014\u0010'\u001a\u00020\u00148\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b'\u0010$R\u0014\u0010(\u001a\u00020\u00148\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b(\u0010$R\u0014\u0010)\u001a\u00020\u00148\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b)\u0010$R\u001c\u0010,\u001a\n +*\u0004\u0018\u00010*0*8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010-\u00a8\u0006."}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareConfigRow.Companion;", "", "<init>", "()V", "", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareConfigRow;", "Lkotlin/jvm/JvmStatic;", "capture", "()Ljava/util/List;", "", "out", "Lrtx/kimiko/api/config/ConfigLibrary$Entry;", "entries", "", "activeKey", "", "collect", "(Ljava/util/List;Ljava/util/List;Ljava/lang/String;)V", "Lrtx/kimiko/api/config/ConfigLibrary$Kind;", "kind", "", "kindOf", "(Lrtx/kimiko/api/config/ConfigLibrary$Kind;)I", "clampKind", "(I)I", "entry", "subtitle", "(Lrtx/kimiko/api/config/ConfigLibrary$Entry;)Ljava/lang/String;", "", "timestamp", "date", "(J)Ljava/lang/String;", "count", "plural", "(I)Ljava/lang/String;", "KIND_LOCAL", "I", "KIND_REMOTE", "KIND_PREMIUM", "MAX_ROWS", "MAX_NAME", "MAX_SUBTITLE", "Ljava/time/format/DateTimeFormatter;", "kotlin.jvm.PlatformType", "DATE_FORMAT", "Ljava/time/format/DateTimeFormatter;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final List<GuiShareConfigRow> capture() {
            ConfigLibrary library = ConfigLibrary.Companion.get();
            ArrayList out = new ArrayList();
            String active = library.activeKey();
            this.collect(out, library.localEntries(), active);
            this.collect(out, library.remoteEntries(), active);
            this.collect(out, library.premiumEntries(), active);
            return out;
        }

        private final void collect(List<GuiShareConfigRow> out, List<ConfigLibrary.Entry> entries, String activeKey) {
            for (ConfigLibrary.Entry entry : entries) {
                if (out.size() >= 24) {
                    return;
                }
                String string = entry.name();
                if (string == null) {
                    string = "";
                }
                out.add(new GuiShareConfigRow(string, this.subtitle(entry), this.kindOf(entry.kind()), entry.cloud(), Intrinsics.areEqual((Object)activeKey, (Object)entry.key())));
            }
        }

        @JvmStatic
        public final int kindOf(@Nullable ConfigLibrary.Kind kind) {
            if (kind == null) {
                return 0;
            }
            return switch (WhenMappings.$EnumSwitchMapping$0[kind.ordinal()]) {
                case 1 -> 1;
                case 2 -> 2;
                default -> 0;
            };
        }

        @JvmStatic
        public final int clampKind(int kind) {
            return kind < 0 || kind > 2 ? 0 : kind;
        }

        private final String subtitle(ConfigLibrary.Entry entry) {
            StringBuilder builder = new StringBuilder();
            if (entry.premium()) {
                Object[] objectArray = new Object[]{entry.ownerName()};
                builder.append(I18n.tr("от %s", objectArray)).append(" \u00b7 ");
            }
            builder.append(this.date(entry.updatedAt()));
            builder.append(" \u00b7 ").append(entry.modules()).append(' ').append(this.plural(entry.modules()));
            String string = builder.toString();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
            return string;
        }

        private final String date(long timestamp) {
            String string;
            if (timestamp <= 0L) {
                return I18n.tr("не сохранён");
            }
            try {
                string = Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).format(DATE_FORMAT);
                Intrinsics.checkNotNull((Object)string);
            }
            catch (Exception ex) {
                string = "—";
            }
            return string;
        }

        private final String plural(int count) {
            boolean slavic;
            String code = ClientLanguage.code();
            boolean bl = slavic = Intrinsics.areEqual((Object)code, (Object)"ru") || Intrinsics.areEqual((Object)code, (Object)"uk") || Intrinsics.areEqual((Object)code, (Object)"pl");
            if (!slavic) {
                return count == 1 ? I18n.tr("модуль") : I18n.tr("модулей");
            }
            int mod100 = count % 100;
            int mod10 = count % 10;
            boolean bl2 = 11 <= mod100 ? mod100 < 15 : false;
            if (bl2) {
                return I18n.tr("модулей");
            }
            if (mod10 == 1) {
                return I18n.tr("модуль");
            }
            boolean bl3 = 2 <= mod10 ? mod10 < 5 : false;
            if (bl3) {
                return I18n.tr("модуля");
            }
            return I18n.tr("модулей");
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @Metadata(mv={2, 4, 0}, k=3, xi=48)
        public static final class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] nArray = new int[ConfigLibrary.Kind.values().length];
                try {
                    nArray[ConfigLibrary.Kind.REMOTE.ordinal()] = 1;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                try {
                    nArray[ConfigLibrary.Kind.PREMIUM.ordinal()] = 2;
                }
                catch (NoSuchFieldError noSuchFieldError) {
                    // empty catch block
                }
                $EnumSwitchMapping$0 = nArray;
            }
        }
    }
}

