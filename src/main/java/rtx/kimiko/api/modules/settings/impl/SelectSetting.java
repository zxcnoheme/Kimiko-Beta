/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.ArraysKt
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.settings.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.modules.settings.Setting;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J#\u0010\t\u001a\u00020\u00002\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0007\"\u00020\u0002H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u001f\u0010\b\u001a\u00020\u00002\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000bH\u0016\u00a2\u0006\u0004\b\b\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0010J\u001d\u0010\u0015\u001a\u00020\u00002\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0019\u0010\u0018J\u0013\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0019\u0010\u001c\u001a\u00020\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u001e\u0010\u0018J\r\u0010\u001f\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001f\u0010\u0018J\u0017\u0010 \u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b \u0010!J\u0017\u0010\"\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\"\u0010!J\u000f\u0010$\u001a\u00020#H\u0002\u00a2\u0006\u0004\b$\u0010%R\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010&R\u0016\u0010\u000f\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010'R\u0016\u0010(\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010'R\u0016\u0010)\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010*\u00a8\u0006+"}, d2={"Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "Lrtx/kimiko/api/modules/settings/Setting;", "", "name", "description", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "", "options", "value", "([Ljava/lang/String;)Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "", "next", "(Ljava/util/List;)Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "option", "selected", "(Ljava/lang/String;)Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "setSelected", "Ljava/util/function/Supplier;", "", "condition", "visible", "(Ljava/util/function/Supplier;)Lrtx/kimiko/api/modules/settings/impl/SelectSetting;", "getSelected", "()Ljava/lang/String;", "getValue", "getOptions", "()Ljava/util/List;", "getDisplayOption", "(Ljava/lang/String;)Ljava/lang/String;", "getDisplaySelected", "getDefaultSelected", "is", "(Ljava/lang/String;)Z", "isSelected", "", "captureDefault", "()V", "Ljava/util/List;", "Ljava/lang/String;", "defaultSelected", "defaultCaptured", "Z", "rtx.kimiko:kimiko"})
public class SelectSetting
extends Setting {
    @NotNull
    private List<String> options;
    @NotNull
    private String selected;
    @NotNull
    private String defaultSelected;
    private boolean defaultCaptured;

    public SelectSetting(@NotNull String name, @NotNull String description) {
        super(name, description);
        this.options = CollectionsKt.emptyList();
        this.selected = "";
        this.defaultSelected = "";
    }

    @NotNull
    public SelectSetting value(String ... options) {
        Intrinsics.checkNotNullParameter((Object)options, (String)"options");
        this.options = Arrays.asList(options);
        String string = (String)CollectionsKt.firstOrNull(this.options);
        if (string == null) {
            string = this.selected = "";
        }
        if (!this.defaultCaptured) {
            this.defaultSelected = this.selected;
        }
        return this;
    }

    @NotNull
    public SelectSetting options(@Nullable List<String> next) {
        Collection collection = next;
        if (collection == null || collection.isEmpty() || Intrinsics.areEqual(this.options, next)) {
            return this;
        }
        String previous = this.selected;
        List list = List.copyOf((Collection)next);
        Intrinsics.checkNotNullExpressionValue(list, (String)"copyOf(...)");
        this.options = list;
        if (!this.options.contains(previous)) {
            this.selected = this.options.get(0);
            if (!this.defaultCaptured) {
                this.defaultSelected = this.selected;
            }
            this.notifyChanged();
        }
        return this;
    }

    @NotNull
    public SelectSetting selected(@NotNull String option) {
        Intrinsics.checkNotNullParameter((Object)option, (String)"option");
        if (this.options.contains(option)) {
            boolean changed = !Intrinsics.areEqual((Object)this.selected, (Object)option);
            this.selected = option;
            this.captureDefault();
            if (changed) {
                this.notifyChanged();
            }
        }
        return this;
    }

    @NotNull
    public SelectSetting setSelected(@NotNull String option) {
        Intrinsics.checkNotNullParameter((Object)option, (String)"option");
        return this.selected(option);
    }

    @NotNull
    public SelectSetting visible(@NotNull Supplier<Boolean> condition) {
        Intrinsics.checkNotNullParameter(condition, (String)"condition");
        this.setVisibilityCondition(condition);
        return this;
    }

    @NotNull
    public String getSelected() {
        return this.selected;
    }

    @NotNull
    public String getValue() {
        return this.selected;
    }

    @NotNull
    public final List<String> getOptions() {
        List<String> list = Collections.unmodifiableList(this.options);
        Intrinsics.checkNotNullExpressionValue(list, (String)"unmodifiableList(...)");
        return list;
    }

    @NotNull
    public String getDisplayOption(@Nullable String option) {
        return I18n.tr(option);
    }

    @NotNull
    public String getDisplaySelected() {
        return I18n.tr(this.selected);
    }

    @NotNull
    public final String getDefaultSelected() {
        return this.defaultSelected;
    }

    public boolean is(@NotNull String option) {
        Intrinsics.checkNotNullParameter((Object)option, (String)"option");
        return Intrinsics.areEqual((Object)this.selected, (Object)option);
    }

    public boolean isSelected(@NotNull String option) {
        Intrinsics.checkNotNullParameter((Object)option, (String)"option");
        return this.is(option);
    }

    private final void captureDefault() {
        if (!this.defaultCaptured) {
            this.defaultSelected = this.selected;
            this.defaultCaptured = true;
        }
    }
}

