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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.modules.settings.Setting;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006B-\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0007\"\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\tJ#\u0010\n\u001a\u00020\u00002\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0007\"\u00020\u0002H\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ#\u0010\f\u001a\u00020\u00002\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0007\"\u00020\u0002H\u0016\u00a2\u0006\u0004\b\f\u0010\u000bJ\u0017\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0014\u001a\u00020\u00002\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0016\u001a\u00020\u00002\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0015J\u0019\u0010\u0018\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0013\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00020\u001a\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0019\u0010\u001d\u001a\u00020\u00022\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0013\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00020\u001a\u00a2\u0006\u0004\b\u001f\u0010\u001cJ\r\u0010 \u001a\u00020\u0012\u00a2\u0006\u0004\b \u0010!J\u0013\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00020\"\u00a2\u0006\u0004\b#\u0010$J\u0013\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00020\u001a\u00a2\u0006\u0004\b%\u0010\u001cJ\r\u0010&\u001a\u00020\r\u00a2\u0006\u0004\b&\u0010'J\u0017\u0010(\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b(\u0010\u0019J\u0017\u0010)\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b)\u0010\u0019R\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010*R\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020+8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\f\u0010*R\u001c\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00020+8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010*R\u0016\u0010-\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u0016\u0010\u000f\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010/\u00a8\u00060"}, d2={"Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "Lrtx/kimiko/api/modules/settings/Setting;", "", "name", "description", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "", "options", "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V", "value", "([Ljava/lang/String;)Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "selected", "", "min", "minSelectedCount", "(I)Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "Ljava/util/function/Supplier;", "", "condition", "visible", "(Ljava/util/function/Supplier;)Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "visibleWhen", "option", "toggle", "(Ljava/lang/String;)Z", "", "getOptions", "()Ljava/util/List;", "getDisplayOption", "(Ljava/lang/String;)Ljava/lang/String;", "getSelected", "isSelectionEmpty", "()Z", "", "getValue", "()Ljava/util/Set;", "getDefaultSelected", "getMinSelectedCount", "()I", "is", "isSelected", "Ljava/util/List;", "", "defaultSelected", "defaultCaptured", "Z", "I", "rtx.kimiko:kimiko"})
public class MultiSelectSetting
extends Setting {
    @NotNull
    private List<String> options;
    @NotNull
    private List<String> selected;
    @NotNull
    private List<String> defaultSelected;
    private boolean defaultCaptured;
    private int minSelectedCount;

    public MultiSelectSetting(@NotNull String name, @NotNull String description) {
        super(name, description);
        this.options = CollectionsKt.emptyList();
        this.selected = new ArrayList();
        this.defaultSelected = new ArrayList();
    }

    public MultiSelectSetting(@NotNull String name, @NotNull String description, String ... options) {
        this(name, description);
        this.value(Arrays.copyOf(options, options.length));
    }

    @NotNull
    public MultiSelectSetting value(String ... options) {
        Intrinsics.checkNotNullParameter((Object)options, "options");
        this.options = Arrays.asList(options);
        return this;
    }

    @NotNull
    public MultiSelectSetting selected(String ... selected) {
        Intrinsics.checkNotNullParameter((Object)selected, "selected");
        ArrayList<String> next = new ArrayList<>(Arrays.asList(selected));
        boolean changed = !Intrinsics.areEqual(this.selected, next);
        this.selected = next;
        if (!this.defaultCaptured) {
            this.defaultSelected = new ArrayList(this.selected);
            this.defaultCaptured = true;
        }
        if (changed) {
            this.notifyChanged();
        }
        return this;
    }

    @NotNull
    public MultiSelectSetting minSelectedCount(int min) {
        this.minSelectedCount = Math.max(0, min);
        return this;
    }

    @NotNull
    public MultiSelectSetting visible(@NotNull Supplier<Boolean> condition) {
        Intrinsics.checkNotNullParameter(condition, (String)"condition");
        this.setVisibilityCondition(condition);
        return this;
    }

    @NotNull
    public MultiSelectSetting visibleWhen(@NotNull Supplier<Boolean> condition) {
        Intrinsics.checkNotNullParameter(condition, (String)"condition");
        return this.visible(condition);
    }

    public boolean toggle(@Nullable String option) {
        if (option == null || !this.options.contains(option)) {
            return false;
        }
        if (this.selected.contains(option)) {
            if (this.selected.size() <= this.minSelectedCount) {
                return false;
            }
            this.selected.remove(option);
        } else {
            this.selected.add(option);
        }
        this.notifyChanged();
        return true;
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
    public final List<String> getSelected() {
        List<String> list = Collections.unmodifiableList(this.selected);
        Intrinsics.checkNotNullExpressionValue(list, (String)"unmodifiableList(...)");
        return list;
    }

    public final boolean isSelectionEmpty() {
        return this.selected.isEmpty();
    }

    @NotNull
    public final Set<String> getValue() {
        return new LinkedHashSet(this.selected);
    }

    @NotNull
    public final List<String> getDefaultSelected() {
        List<String> list = Collections.unmodifiableList(this.defaultSelected);
        Intrinsics.checkNotNullExpressionValue(list, (String)"unmodifiableList(...)");
        return list;
    }

    public final int getMinSelectedCount() {
        return this.minSelectedCount;
    }

    public boolean is(@NotNull String option) {
        Intrinsics.checkNotNullParameter((Object)option, (String)"option");
        return this.selected.contains(option);
    }

    public boolean isSelected(@NotNull String option) {
        Intrinsics.checkNotNullParameter((Object)option, (String)"option");
        return this.is(option);
    }
}

