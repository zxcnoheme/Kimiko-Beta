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

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0019\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u001f\u0010\r\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\r\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\b\r\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u0012\u0010\nJ\u0017\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0017\u001a\u00020\u00002\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001aJ\u000f\u0010\u001c\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001e\u001a\u00020\u0007\u00a2\u0006\u0004\b\u001e\u0010\u001aJ\r\u0010\u001f\u001a\u00020\u0007\u00a2\u0006\u0004\b\u001f\u0010\u001aJ\r\u0010 \u001a\u00020\u0007\u00a2\u0006\u0004\b \u0010\u001aJ\r\u0010!\u001a\u00020\u0015\u00a2\u0006\u0004\b!\u0010\"J\r\u0010#\u001a\u00020\u0007\u00a2\u0006\u0004\b#\u0010\u001aJ\r\u0010$\u001a\u00020\u0007\u00a2\u0006\u0004\b$\u0010\u001aJ\u0017\u0010&\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b&\u0010'R\u0016\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010(R\u0016\u0010\u000b\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000b\u0010(R\u0016\u0010\f\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\f\u0010(R\u0016\u0010\u0012\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0012\u0010(R\u0016\u0010)\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010+\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u0010*R\u0016\u0010,\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010(R\u0016\u0010-\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b-\u0010*\u00a8\u0006."}, d2={"Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "Lrtx/kimiko/api/modules/settings/Setting;", "", "name", "description", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "", "value", "setValue", "(F)Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "min", "max", "range", "(FF)Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "", "(II)Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "step", "increment", "(I)Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "Ljava/util/function/Supplier;", "", "condition", "visible", "(Ljava/util/function/Supplier;)Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "getValue", "()F", "getFloat", "getInt", "()I", "getMin", "getMax", "getIncrement", "isInteger", "()Z", "getDefaultValue", "getProgress", "raw", "clamp", "(F)F", "F", "integer", "Z", "rangeConfigured", "defaultValue", "defaultCaptured", "rtx.kimiko:kimiko"})
public class SliderSetting
extends Setting {
    private float value;
    private float min;
    private float max;
    private float increment;
    private boolean integer;
    private boolean rangeConfigured;
    private float defaultValue;
    private boolean defaultCaptured;

    public SliderSetting(@NotNull String name, @NotNull String description) {
        super(name, description);
    }

    @NotNull
    public SliderSetting setValue(float value) {
        float next = this.clamp(value);
        boolean changed = Float.compare(this.value, next) != 0;
        this.value = next;
        if (!this.defaultCaptured) {
            this.defaultValue = this.value;
            this.defaultCaptured = true;
        }
        if (changed) {
            this.notifyChanged();
        }
        return this;
    }

    @NotNull
    public SliderSetting range(float min, float max) {
        this.min = min;
        this.max = max;
        this.rangeConfigured = true;
        this.value = this.clamp(this.value);
        return this;
    }

    @NotNull
    public SliderSetting range(int min, int max) {
        this.integer = true;
        this.min = min;
        this.max = max;
        this.rangeConfigured = true;
        if (this.increment <= 0.0f) {
            this.increment = 1.0f;
        }
        this.value = this.clamp(this.value);
        return this;
    }

    @NotNull
    public SliderSetting increment(float step) {
        this.increment = Math.max(0.0f, step);
        this.value = this.clamp(this.value);
        return this;
    }

    @NotNull
    public SliderSetting increment(int step) {
        return this.increment((float)step);
    }

    @NotNull
    public SliderSetting visible(@NotNull Supplier<Boolean> condition) {
        Intrinsics.checkNotNullParameter(condition, (String)"condition");
        this.setVisibilityCondition(condition);
        return this;
    }

    public float getValue() {
        return this.value;
    }

    public float getFloat() {
        return this.value;
    }

    public int getInt() {
        return Math.round(this.value);
    }

    public final float getMin() {
        return this.min;
    }

    public final float getMax() {
        return this.max;
    }

    public final float getIncrement() {
        return this.increment;
    }

    public final boolean isInteger() {
        return this.integer;
    }

    public final float getDefaultValue() {
        return this.defaultValue;
    }

    public final float getProgress() {
        if (!this.rangeConfigured || this.max == this.min) {
            return 0.0f;
        }
        return (this.value - this.min) / (this.max - this.min);
    }

    private final float clamp(float raw) {
        float hi;
        float lo;
        float v = raw;
        if (this.rangeConfigured) {
            lo = Math.min(this.min, this.max);
            hi = Math.max(this.min, this.max);
            v = Math.max(lo, Math.min(hi, v));
        }
        if (this.increment > 0.0f) {
            float origin = this.rangeConfigured ? this.min : 0.0f;
            v = origin + (float)Math.round((v - origin) / this.increment) * this.increment;
        }
        if (this.integer) {
            v = Math.round(v);
        }
        if (this.rangeConfigured) {
            lo = Math.min(this.min, this.max);
            hi = Math.max(this.min, this.max);
            v = Math.max(lo, Math.min(hi, v));
        }
        return v;
    }
}

