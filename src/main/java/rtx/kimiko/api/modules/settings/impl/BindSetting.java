/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.settings.impl;

import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.utils.key.KeyBind;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0015\b\u0016\u0018\u00002\u00020\u0001:\u0001'B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006B#\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\b\u0005\u0010\tJ\u0017\u0010\f\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u0010\u001a\u00020\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0015\u001a\u00020\u00002\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001d\u0010\u0017\u001a\u00020\u00002\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0016J\r\u0010\u0018\u001a\u00020\n\u00a2\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\n\u00a2\u0006\u0004\b\u001a\u0010\u0019J\r\u0010\u001b\u001a\u00020\u000e\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001d\u001a\u00020\u0013\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\r\u0010\u001f\u001a\u00020\u0007\u00a2\u0006\u0004\b\u001f\u0010 R\u0016\u0010!\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010#\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010\"R\u0016\u0010$\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010\u000f\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010&\u00a8\u0006("}, d2={"Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "Lrtx/kimiko/api/modules/settings/Setting;", "", "name", "description", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "Lrtx/kimiko/utils/key/KeyBind;", "bind", "(Ljava/lang/String;Ljava/lang/String;Lrtx/kimiko/utils/key/KeyBind;)V", "", "glfwKey", "setKey", "(I)Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "Lrtx/kimiko/api/modules/settings/impl/BindSetting$Type;", "type", "setType", "(Lrtx/kimiko/api/modules/settings/impl/BindSetting$Type;)Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "Ljava/util/function/Supplier;", "", "condition", "visible", "(Ljava/util/function/Supplier;)Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "visibleWhen", "getKey", "()I", "getDefaultKey", "getType", "()Lrtx/kimiko/api/modules/settings/impl/BindSetting$Type;", "isBound", "()Z", "getValue", "()Lrtx/kimiko/utils/key/KeyBind;", "key", "I", "defaultKey", "defaultCaptured", "Z", "Lrtx/kimiko/api/modules/settings/impl/BindSetting$Type;", "Type", "rtx.kimiko:kimiko"})
public class BindSetting
extends Setting {
    private int key;
    private int defaultKey;
    private boolean defaultCaptured;
    @NotNull
    private Type type;

    public BindSetting(@NotNull String name, @NotNull String description) {
        super(name, description);
        this.key = -1;
        this.defaultKey = -1;
        this.type = Type.TOGGLE;
    }

    public BindSetting(@NotNull String name, @NotNull String description, @Nullable KeyBind bind) {
        this(name, description);
        if (bind != null) {
            this.setKey(bind.getCode());
        }
    }

    @NotNull
    public BindSetting setKey(int glfwKey) {
        boolean changed = this.key != glfwKey;
        this.key = glfwKey;
        if (!this.defaultCaptured) {
            this.defaultKey = glfwKey;
            this.defaultCaptured = true;
        }
        if (changed) {
            this.notifyChanged();
        }
        return this;
    }

    @NotNull
    public BindSetting setType(@Nullable Type type) {
        Type next;
        Type type2 = type;
        if (type2 == null) {
            type2 = Type.TOGGLE;
        }
        if (this.type != (next = type2)) {
            this.type = next;
            this.notifyChanged();
        }
        return this;
    }

    @NotNull
    public BindSetting visible(@NotNull Supplier<Boolean> condition) {
        Intrinsics.checkNotNullParameter(condition, (String)"condition");
        this.setVisibilityCondition(condition);
        return this;
    }

    @NotNull
    public BindSetting visibleWhen(@NotNull Supplier<Boolean> condition) {
        Intrinsics.checkNotNullParameter(condition, (String)"condition");
        return this.visible(condition);
    }

    public final int getKey() {
        return this.key;
    }

    public final int getDefaultKey() {
        return this.defaultKey;
    }

    @NotNull
    public final Type getType() {
        return this.type;
    }

    public final boolean isBound() {
        return this.key != -1;
    }

    @NotNull
    public final KeyBind getValue() {
        return new KeyBind(this.key);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2={"Lrtx/kimiko/api/modules/settings/impl/BindSetting$Type;", "", "<init>", "(Ljava/lang/String;I)V", "HOLD", "TOGGLE", "rtx.kimiko:kimiko"})
    public static enum Type {
        HOLD,
        TOGGLE;
@NotNull
        public static EnumEntries<Type> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }
}

