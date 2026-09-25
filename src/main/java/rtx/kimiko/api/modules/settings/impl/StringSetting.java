/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.settings.impl;

import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.settings.impl.TextSetting;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\u001d\u0010\r\u001a\u00020\u00002\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016\u00a2\u0006\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/api/modules/settings/impl/StringSetting;", "Lrtx/kimiko/api/modules/settings/impl/TextSetting;", "", "name", "description", "value", "", "maxLength", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "Ljava/util/function/Supplier;", "", "condition", "visibleWhen", "(Ljava/util/function/Supplier;)Lrtx/kimiko/api/modules/settings/impl/StringSetting;", "rtx.kimiko:kimiko"})
public class StringSetting
extends TextSetting {
    public StringSetting(@NotNull String name, @NotNull String description, @Nullable String value, int maxLength) {
        super(name, description);
        this.setText(value);
        this.lengthBounds(0, maxLength);
    }

    @Override
    @NotNull
    public StringSetting visibleWhen(@NotNull Supplier<Boolean> condition) {
        Intrinsics.checkNotNullParameter(condition, (String)"condition");
        this.visible(condition);
        return this;
    }
}

