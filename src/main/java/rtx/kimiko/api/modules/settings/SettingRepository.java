/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.settings.Setting;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J!\u0010\b\u001a\u00020\u00072\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\"\u00020\u0005\u00a2\u0006\u0004\b\b\u0010\tJ\u0013\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000e\u001a\u00020\r\u00a2\u0006\u0004\b\u000f\u0010\u0010J/\u0010\u000f\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0011*\u00020\u00052\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012\u00a2\u0006\u0004\b\u000f\u0010\u0014J\r\u0010\u0016\u001a\u00020\u0015\u00a2\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0019\u001a\u00020\u0018\u00a2\u0006\u0004\b\u0019\u0010\u001aR$\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u001bj\b\u0012\u0004\u0012\u00020\u0005`\u001c8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001e\u00a8\u0006\u001f"}, d2={"Lrtx/kimiko/api/modules/settings/SettingRepository;", "", "<init>", "()V", "", "Lrtx/kimiko/api/modules/settings/Setting;", "s", "", "add", "([Lrtx/kimiko/api/modules/settings/Setting;)V", "", "all", "()Ljava/util/List;", "", "name", "get", "(Ljava/lang/String;)Lrtx/kimiko/api/modules/settings/Setting;", "T", "Ljava/lang/Class;", "type", "(Ljava/lang/String;Ljava/lang/Class;)Lrtx/kimiko/api/modules/settings/Setting;", "", "isEmpty", "()Z", "", "size", "()I", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "settings", "Ljava/util/ArrayList;", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nSettingRepository.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SettingRepository.kt\nrtx/kimiko/api/modules/settings/SettingRepository\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,25:1\n296#2,2:26\n*S KotlinDebug\n*F\n+ 1 SettingRepository.kt\nrtx/kimiko/api/modules/settings/SettingRepository\n*L\n13#1:26,2\n*E\n"})
public final class SettingRepository {
    @NotNull
    private final ArrayList<Setting> settings = new ArrayList();

    public final void add(Setting ... s) {
        Intrinsics.checkNotNullParameter((Object)s, (String)"s");
        CollectionsKt.addAll((Collection)this.settings, (Object[])s);
    }

    @NotNull
    public final List<Setting> all() {
        List<Setting> list = Collections.unmodifiableList((List)this.settings);
        Intrinsics.checkNotNullExpressionValue(list, (String)"unmodifiableList(...)");
        return list;
    }

    @Nullable
    public final Setting get(@NotNull String name) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        for (Setting s : this.settings) {
            if (StringsKt.equals(s.getName(), name, true)) {
                return s;
            }
        }
        return null;
    }

    @Nullable
    public final <T extends Setting> T get(@NotNull String name, @NotNull Class<T> type) {
        Setting setting;
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter(type, (String)"type");
        Setting found = this.get(name);
        if (type.isInstance(found)) {
            Intrinsics.checkNotNull((Object)found, (String)"null cannot be cast to non-null type T of rtx.kimiko.api.modules.settings.SettingRepository.get");
            setting = found;
        } else {
            setting = null;
        }
        return (T)setting;
    }

    public final boolean isEmpty() {
        return this.settings.isEmpty();
    }

    public final int size() {
        return this.settings.size();
    }
}

