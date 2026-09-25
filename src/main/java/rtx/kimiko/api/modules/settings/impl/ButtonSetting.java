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
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.modules.settings.Setting;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\u000b\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0014\u001a\u00020\u00002\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0017J\r\u0010\u0019\u001a\u00020\r\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001c\u001a\u00020\u001b\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u0016\u0010\u0007\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u001eR\u0016\u0010\u000f\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u001fR\u0016\u0010\n\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\n\u0010 \u00a8\u0006!"}, d2={"Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;", "Lrtx/kimiko/api/modules/settings/Setting;", "", "name", "description", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "label", "(Ljava/lang/String;)Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;", "Ljava/lang/Runnable;", "action", "onClick", "(Ljava/lang/Runnable;)Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;", "", "offset", "labelOffsetX", "(F)Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;", "Ljava/util/function/Supplier;", "", "condition", "visible", "(Ljava/util/function/Supplier;)Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;", "getLabel", "()Ljava/lang/String;", "getDisplayLabel", "getLabelOffsetX", "()F", "", "click", "()V", "Ljava/lang/String;", "F", "Ljava/lang/Runnable;", "rtx.kimiko:kimiko"})
public class ButtonSetting
extends Setting {
    @NotNull
    private String label;
    private float labelOffsetX;
    @NotNull
    private Runnable action;

    public ButtonSetting(@NotNull String name, @NotNull String description) {
        super(name, description);
        this.label = "";
        this.action = ButtonSetting::action$lambda$0;
    }

    @NotNull
    public ButtonSetting label(@Nullable String label) {
        String string = label;
        if (string == null) {
            string = "";
        }
        this.label = string;
        return this;
    }

    @NotNull
    public ButtonSetting onClick(@Nullable Runnable action) {
        Runnable runnable = action;
        if (runnable == null) {
            runnable = ButtonSetting::onClick$lambda$0;
        }
        this.action = runnable;
        return this;
    }

    @NotNull
    public ButtonSetting labelOffsetX(float offset) {
        this.labelOffsetX = offset;
        return this;
    }

    @NotNull
    public ButtonSetting visible(@NotNull Supplier<Boolean> condition) {
        Intrinsics.checkNotNullParameter(condition, (String)"condition");
        this.setVisibilityCondition(condition);
        return this;
    }

    @NotNull
    public final String getLabel() {
        return this.label;
    }

    @NotNull
    public final String getDisplayLabel() {
        return I18n.tr(this.label);
    }

    public final float getLabelOffsetX() {
        return this.labelOffsetX;
    }

    public final void click() {
        this.action.run();
    }

    private static final void action$lambda$0() {
    }

    private static final void onClick$lambda$0() {
    }
}

