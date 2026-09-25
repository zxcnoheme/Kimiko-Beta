/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.settings.impl;

import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u000f\u001a\u00020\u00002\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "", "name", "description", "", "value", "min", "max", "increment", "<init>", "(Ljava/lang/String;Ljava/lang/String;DDDD)V", "Ljava/util/function/Supplier;", "", "condition", "visibleWhen", "(Ljava/util/function/Supplier;)Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "rtx.kimiko:kimiko"})
public class NumberSetting
extends SliderSetting {
    public NumberSetting(@NotNull String name, @NotNull String description, double value, double min, double max, double increment) {
        super(name, description);
        this.range((float)min, (float)max);
        this.increment((float)increment);
        this.setValue((float)value);
    }

    @NotNull
    public NumberSetting visibleWhen(@NotNull Supplier<Boolean> condition) {
        Intrinsics.checkNotNullParameter(condition, (String)"condition");
        this.visible(condition);
        return this;
    }
}

