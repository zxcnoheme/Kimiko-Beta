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

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0015\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\b\u001a\u00020\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u0019\u0010\u000b\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u000b\u0010\tJ\u001f\u0010\u000f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0014\u001a\u00020\u00002\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0016\u001a\u00020\u00002\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0015J\r\u0010\u0017\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0019\u0010\u0018J\r\u0010\u001a\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001a\u0010\u0018J\r\u0010\u001b\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001b\u0010\u0018J\r\u0010\u001c\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001c\u0010\u0018J\r\u0010\u001d\u001a\u00020\f\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\r\u0010\u001f\u001a\u00020\f\u00a2\u0006\u0004\b\u001f\u0010\u001eR\u0016\u0010\u0007\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0007\u0010 R\u0016\u0010\n\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\n\u0010 R\u0016\u0010!\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b!\u0010 R\u0016\u0010\"\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010$\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010&\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010%\u00a8\u0006'"}, d2={"Lrtx/kimiko/api/modules/settings/impl/TextSetting;", "Lrtx/kimiko/api/modules/settings/Setting;", "", "name", "description", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "text", "setText", "(Ljava/lang/String;)Lrtx/kimiko/api/modules/settings/impl/TextSetting;", "placeholder", "setPlaceholder", "", "min", "max", "lengthBounds", "(II)Lrtx/kimiko/api/modules/settings/impl/TextSetting;", "Ljava/util/function/Supplier;", "", "condition", "visible", "(Ljava/util/function/Supplier;)Lrtx/kimiko/api/modules/settings/impl/TextSetting;", "visibleWhen", "getDisplayPlaceholder", "()Ljava/lang/String;", "getText", "getValue", "getPlaceholder", "getDefaultText", "getMinLength", "()I", "getMaxLength", "Ljava/lang/String;", "defaultText", "defaultCaptured", "Z", "minLength", "I", "maxLength", "rtx.kimiko:kimiko"})
public class TextSetting
extends Setting {
    @NotNull
    private String text;
    @NotNull
    private String placeholder;
    @NotNull
    private String defaultText;
    private boolean defaultCaptured;
    private int minLength;
    private int maxLength;

    public TextSetting(@NotNull String name, @NotNull String description) {
        super(name, description);
        this.text = "";
        this.placeholder = "";
        this.defaultText = "";
        this.maxLength = Integer.MAX_VALUE;
    }

    @NotNull
    public TextSetting setText(@Nullable String text) {
        String next;
        String string = text;
        if (string == null) {
            string = "";
        }
        boolean changed = !Intrinsics.areEqual((Object)this.text, (Object)(next = string));
        this.text = next;
        if (!this.defaultCaptured) {
            this.defaultText = this.text;
            this.defaultCaptured = true;
        }
        if (changed) {
            this.notifyChanged();
        }
        return this;
    }

    @NotNull
    public TextSetting setPlaceholder(@Nullable String placeholder) {
        String string = placeholder;
        if (string == null) {
            string = "";
        }
        this.placeholder = string;
        return this;
    }

    @NotNull
    public TextSetting lengthBounds(int min, int max) {
        this.minLength = Math.max(0, min);
        this.maxLength = Math.max(this.minLength, max);
        return this;
    }

    @NotNull
    public TextSetting visible(@NotNull Supplier<Boolean> condition) {
        Intrinsics.checkNotNullParameter(condition, (String)"condition");
        this.setVisibilityCondition(condition);
        return this;
    }

    @NotNull
    public TextSetting visibleWhen(@NotNull Supplier<Boolean> condition) {
        Intrinsics.checkNotNullParameter(condition, (String)"condition");
        return this.visible(condition);
    }

    @NotNull
    public final String getDisplayPlaceholder() {
        return I18n.tr(this.placeholder);
    }

    @NotNull
    public final String getText() {
        return this.text;
    }

    @NotNull
    public final String getValue() {
        return this.text;
    }

    @NotNull
    public final String getPlaceholder() {
        return this.placeholder;
    }

    @NotNull
    public final String getDefaultText() {
        return this.defaultText;
    }

    public final int getMinLength() {
        return this.minLength;
    }

    public final int getMaxLength() {
        return this.maxLength;
    }
}

