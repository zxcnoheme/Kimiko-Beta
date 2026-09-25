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
import rtx.kimiko.api.modules.settings.Setting;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006B!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0005\u0010\tJ\u0017\u0010\n\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0007\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0007\u00a2\u0006\u0004\b\u000e\u0010\rJ\r\u0010\u000f\u001a\u00020\u0007\u00a2\u0006\u0004\b\u000f\u0010\rJ\r\u0010\u0010\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0014\u001a\u00020\u00002\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0016\u001a\u00020\u00002\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012H\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0015R\u0016\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0017R\u0016\u0010\u0018\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0017R\u0016\u0010\u0019\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0017\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Lrtx/kimiko/api/modules/settings/Setting;", "", "name", "description", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "", "value", "(Ljava/lang/String;Ljava/lang/String;Z)V", "setValue", "(Z)Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "getValue", "()Z", "isValue", "getDefaultValue", "toggle", "()Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Ljava/util/function/Supplier;", "condition", "visible", "(Ljava/util/function/Supplier;)Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "visibleWhen", "Z", "defaultValue", "defaultCaptured", "rtx.kimiko:kimiko"})
public class BooleanSetting
extends Setting {
    private boolean value;
    private boolean defaultValue;
    private boolean defaultCaptured;

    public BooleanSetting(@NotNull String name, @NotNull String description) {
        super(name, description);
    }

    public BooleanSetting(@NotNull String name, @NotNull String description, boolean value) {
        this(name, description);
        this.setValue(value);
    }

    @NotNull
    public BooleanSetting setValue(boolean value) {
        boolean changed = this.value != value;
        this.value = value;
        if (!this.defaultCaptured) {
            this.defaultValue = value;
            this.defaultCaptured = true;
        }
        if (changed) {
            this.notifyChanged();
        }
        return this;
    }

    public final boolean getValue() {
        return this.value;
    }

    public final boolean isValue() {
        return this.value;
    }

    public final boolean getDefaultValue() {
        return this.defaultValue;
    }

    @NotNull
    public final BooleanSetting toggle() {
        return this.setValue(!this.value);
    }

    @NotNull
    public BooleanSetting visible(@NotNull Supplier<Boolean> condition) {
        Intrinsics.checkNotNullParameter(condition, (String)"condition");
        this.setVisibilityCondition(condition);
        return this;
    }

    @NotNull
    public BooleanSetting visibleWhen(@NotNull Supplier<Boolean> condition) {
        Intrinsics.checkNotNullParameter(condition, (String)"condition");
        return this.visible(condition);
    }
}

