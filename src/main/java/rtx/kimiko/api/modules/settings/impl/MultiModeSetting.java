/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.settings.impl;

import java.util.Arrays;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.modules.settings.impl.MultiSelectSetting;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005\u0012\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0005\"\u00020\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u0010\u001a\u00020\u00002\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eH\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/api/modules/settings/impl/MultiModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "", "name", "description", "", "options", "selected", "<init>", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;)V", "option", "", "isSelected", "(Ljava/lang/String;)Z", "Ljava/util/function/Supplier;", "condition", "visibleWhen", "(Ljava/util/function/Supplier;)Lrtx/kimiko/api/modules/settings/impl/MultiModeSetting;", "rtx.kimiko:kimiko"})
public class MultiModeSetting
extends MultiSelectSetting {
    public MultiModeSetting(@NotNull String name, @NotNull String description, @NotNull String[] options, String ... selected) {
        super(name, description);
        this.value(Arrays.copyOf(options, options.length));
        this.selected(Arrays.copyOf(selected, selected.length));
    }

    @Override
    public boolean isSelected(@NotNull String option) {
        Intrinsics.checkNotNullParameter((Object)option, (String)"option");
        return this.is(option);
    }

    @Override
    @NotNull
    public MultiModeSetting visibleWhen(@NotNull Supplier<Boolean> condition) {
        Intrinsics.checkNotNullParameter(condition, (String)"condition");
        this.visible(condition);
        return this;
    }
}

