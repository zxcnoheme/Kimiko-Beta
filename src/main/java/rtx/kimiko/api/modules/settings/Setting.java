/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.settings;

import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.lang.I18n;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018\u00002\u00020\u0001B\u001b\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0014\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0007J\r\u0010\b\u001a\u00020\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0002\u00a2\u0006\u0004\b\n\u0010\tJ\u000f\u0010\u000b\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u000b\u0010\tJ\u000f\u0010\f\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\f\u0010\tJ\u000f\u0010\u000e\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0013\u001a\u00020\u00122\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0010H\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0012H\u0004\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u001bR\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u001bR\u001e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u001e\u00a8\u0006\u001f"}, d2={"Lrtx/kimiko/api/modules/settings/Setting;", "", "", "name", "description", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getDescription", "getDisplayName", "getDisplayDescription", "", "isVisible", "()Z", "Ljava/util/function/Supplier;", "condition", "", "setVisibilityCondition", "(Ljava/util/function/Supplier;)V", "Ljava/lang/Runnable;", "changeListener", "setChangeListener", "(Ljava/lang/Runnable;)V", "notifyChanged", "()V", "Ljava/lang/String;", "visibilityCondition", "Ljava/util/function/Supplier;", "Ljava/lang/Runnable;", "rtx.kimiko:kimiko"})
public abstract class Setting {
    @NotNull
    private final String name;
    @NotNull
    private final String description;
    @Nullable
    private Supplier<Boolean> visibilityCondition;
    @Nullable
    private Runnable changeListener;

    protected Setting(@NotNull String name, @Nullable String description) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        this.name = name;
        String string = description;
        if (string == null) {
            string = "";
        }
        this.description = string;
    }

    protected Setting(@NotNull String name) {
        this(name, "");
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    @NotNull
    public String getDisplayName() {
        return I18n.tr(this.name);
    }

    @NotNull
    public String getDisplayDescription() {
        return I18n.tr(this.description);
    }

    public boolean isVisible() {
        Supplier<Boolean> supplier = this.visibilityCondition;
        if (supplier == null) {
            return true;
        }
        Supplier<Boolean> condition = supplier;
        return Intrinsics.areEqual((Object)condition.get(), (Object)true);
    }

    protected final void setVisibilityCondition(@Nullable Supplier<Boolean> condition) {
        this.visibilityCondition = condition;
    }

    public final void setChangeListener(@Nullable Runnable changeListener) {
        this.changeListener = changeListener;
    }

    protected final void notifyChanged() {
        block0: {
            Runnable runnable = this.changeListener;
            if (runnable == null) break block0;
            runnable.run();
        }
    }
}

