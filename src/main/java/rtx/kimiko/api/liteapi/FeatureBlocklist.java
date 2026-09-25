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
package rtx.kimiko.api.liteapi;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ%\u0010\f\u001a\u00020\u000b2\u0010\u0010\n\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\tH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u000bH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u000e\u0010\u0003J\u001d\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u0011H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/api/liteapi/FeatureBlocklist;", "", "<init>", "()V", "", "", "Lkotlin/jvm/JvmStatic;", "knownFeatures", "()Ljava/util/Set;", "", "features", "", "applyBlocklist", "(Ljava/util/Collection;)V", "clear", "Lrtx/kimiko/api/modules/Module;", "module", "", "isModuleBlocked", "(Lrtx/kimiko/api/modules/Module;)Z", "hasAny", "()Z", "blocked", "Ljava/util/Set;", "rtx.kimiko:kimiko"})
public final class FeatureBlocklist {
    @NotNull
    public static final FeatureBlocklist INSTANCE = new FeatureBlocklist();
    @NotNull
    private static volatile Set<String> blocked;

    private FeatureBlocklist() {
    }

    @JvmStatic
    @NotNull
    public static final Set<String> knownFeatures() {
        LinkedHashSet out = new LinkedHashSet();
        for (Module m : ModuleManager.Companion.get().getAll()) {
            Feature f = m.getClass().getAnnotation(Feature.class);
            if (f == null) continue;
            String[] stringArray = f.value();
            Collections.addAll(out, Arrays.copyOf(stringArray, stringArray.length));
        }
        return out;
    }

    @JvmStatic
    public static final void applyBlocklist(@Nullable Collection<String> features) {
        if (features == null || features.isEmpty()) {
            Set set = Collections.emptySet();
            Intrinsics.checkNotNullExpressionValue(set, (String)"emptySet(...)");
            blocked = set;
            return;
        }
        HashSet<String> next = new HashSet<String>();
        for (String f : features) {
            if (f == null || StringsKt.isBlank((CharSequence)f)) continue;
            String string = f.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toLowerCase(...)");
            next.add(((Object)StringsKt.trim((CharSequence)string)).toString());
        }
        blocked = next;
    }

    @JvmStatic
    public static final void clear() {
        Set set = Collections.emptySet();
        Intrinsics.checkNotNullExpressionValue(set, (String)"emptySet(...)");
        blocked = set;
    }

    @JvmStatic
    public static final boolean isModuleBlocked(@Nullable Module module) {
        if (module == null) {
            return false;
        }
        Set<String> current = blocked;
        if (current.isEmpty()) {
            return false;
        }
        Feature feature = module.getClass().getAnnotation(Feature.class);
        if (feature == null) {
            return false;
        }
        Feature f = feature;
        for (String id : f.value()) {
            String string = id.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toLowerCase(...)");
            if (!current.contains(((Object)StringsKt.trim((CharSequence)string)).toString())) continue;
            return true;
        }
        return false;
    }

    @JvmStatic
    public static final boolean hasAny() {
        return !((Collection)blocked).isEmpty();
    }

    static {
        Set set = Collections.emptySet();
        Intrinsics.checkNotNullExpressionValue(set, (String)"emptySet(...)");
        blocked = set;
    }
}

