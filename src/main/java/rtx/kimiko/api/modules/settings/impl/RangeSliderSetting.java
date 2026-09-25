/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.settings.impl;

import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.settings.Setting;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b%\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\n\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\r\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u001f\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u000bJ\u001f\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u0013H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u0016\u0010\u000eJ\u0017\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0013H\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u001b\u001a\u00020\u00002\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u001d\u0010\u001d\u001a\u00020\u00002\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0016\u00a2\u0006\u0004\b\u001d\u0010\u001cJ\u0019\u0010\u001e\u001a\u00020\u00002\b\u0010\u001e\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\r\u0010 \u001a\u00020\u0007\u00a2\u0006\u0004\b \u0010!J\r\u0010\"\u001a\u00020\u0007\u00a2\u0006\u0004\b\"\u0010!J\r\u0010#\u001a\u00020\u0013\u00a2\u0006\u0004\b#\u0010$J\r\u0010%\u001a\u00020\u0013\u00a2\u0006\u0004\b%\u0010$J\r\u0010&\u001a\u00020\u0007\u00a2\u0006\u0004\b&\u0010!J\r\u0010'\u001a\u00020\u0007\u00a2\u0006\u0004\b'\u0010!J\r\u0010(\u001a\u00020\u0007\u00a2\u0006\u0004\b(\u0010!J\r\u0010)\u001a\u00020\u0019\u00a2\u0006\u0004\b)\u0010*J\r\u0010+\u001a\u00020\u0002\u00a2\u0006\u0004\b+\u0010,J\r\u0010-\u001a\u00020\u0007\u00a2\u0006\u0004\b-\u0010!J\r\u0010.\u001a\u00020\u0007\u00a2\u0006\u0004\b.\u0010!J\r\u0010/\u001a\u00020\u0007\u00a2\u0006\u0004\b/\u0010!J\r\u00100\u001a\u00020\u0007\u00a2\u0006\u0004\b0\u0010!J\u0015\u00102\u001a\u00020\u00072\u0006\u00101\u001a\u00020\u0007\u00a2\u0006\u0004\b2\u00103J\u0017\u00105\u001a\u00020\u00072\u0006\u00104\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b5\u00103J\u0017\u00101\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b1\u00103R\u0016\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u00106R\u0016\u0010\t\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\t\u00106R\u0016\u0010\u0010\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0010\u00106R\u0016\u0010\u0011\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0011\u00106R\u0016\u0010\u0016\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0016\u00106R\u0016\u00107\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b7\u00108R\u0016\u00109\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b9\u00108R\u0016\u0010\u001e\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010:R\u0016\u0010;\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b;\u00106R\u0016\u0010<\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u00106R\u0016\u0010=\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b=\u00108\u00a8\u0006>"}, d2={"Lrtx/kimiko/api/modules/settings/impl/RangeSliderSetting;", "Lrtx/kimiko/api/modules/settings/Setting;", "", "name", "description", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "", "minValue", "maxValue", "setValue", "(FF)Lrtx/kimiko/api/modules/settings/impl/RangeSliderSetting;", "value", "setMinValue", "(F)Lrtx/kimiko/api/modules/settings/impl/RangeSliderSetting;", "setMaxValue", "min", "max", "range", "", "(II)Lrtx/kimiko/api/modules/settings/impl/RangeSliderSetting;", "step", "increment", "(I)Lrtx/kimiko/api/modules/settings/impl/RangeSliderSetting;", "Ljava/util/function/Supplier;", "", "condition", "visible", "(Ljava/util/function/Supplier;)Lrtx/kimiko/api/modules/settings/impl/RangeSliderSetting;", "visibleWhen", "suffix", "(Ljava/lang/String;)Lrtx/kimiko/api/modules/settings/impl/RangeSliderSetting;", "getMinValue", "()F", "getMaxValue", "getMinInt", "()I", "getMaxInt", "getMin", "getMax", "getIncrement", "isInteger", "()Z", "getSuffix", "()Ljava/lang/String;", "getDefaultMinValue", "getDefaultMaxValue", "getMinProgress", "getMaxProgress", "progress", "valueAtProgress", "(F)F", "raw", "clamp", "F", "integer", "Z", "rangeConfigured", "Ljava/lang/String;", "defaultMinValue", "defaultMaxValue", "defaultCaptured", "rtx.kimiko:kimiko"})
public class RangeSliderSetting
extends Setting {
    private float minValue;
    private float maxValue;
    private float min;
    private float max;
    private float increment;
    private boolean integer;
    private boolean rangeConfigured;
    @NotNull
    private String suffix;
    private float defaultMinValue;
    private float defaultMaxValue;
    private boolean defaultCaptured;

    public RangeSliderSetting(@NotNull String name, @NotNull String description) {
        super(name, description);
        this.suffix = "";
    }

    @NotNull
    public RangeSliderSetting setValue(float minValue, float maxValue) {
        float first = this.clamp(minValue);
        float second = this.clamp(maxValue);
        float low = Math.min(first, second);
        float high = Math.max(first, second);
        boolean changed = Float.compare(this.minValue, low) != 0 || Float.compare(this.maxValue, high) != 0;
        this.minValue = low;
        this.maxValue = high;
        if (!this.defaultCaptured) {
            this.defaultMinValue = this.minValue;
            this.defaultMaxValue = this.maxValue;
            this.defaultCaptured = true;
        }
        if (changed) {
            this.notifyChanged();
        }
        return this;
    }

    @NotNull
    public RangeSliderSetting setMinValue(float value) {
        return this.setValue(value, this.maxValue);
    }

    @NotNull
    public RangeSliderSetting setMaxValue(float value) {
        return this.setValue(this.minValue, value);
    }

    @NotNull
    public RangeSliderSetting range(float min, float max) {
        this.min = min;
        this.max = max;
        this.rangeConfigured = true;
        return this.setValue(this.minValue, this.maxValue);
    }

    @NotNull
    public RangeSliderSetting range(int min, int max) {
        this.integer = true;
        if (this.increment <= 0.0f) {
            this.increment = 1.0f;
        }
        return this.range((float)min, (float)max);
    }

    @NotNull
    public RangeSliderSetting increment(float step) {
        this.increment = Math.max(0.0f, step);
        return this.setValue(this.minValue, this.maxValue);
    }

    @NotNull
    public RangeSliderSetting increment(int step) {
        return this.increment((float)step);
    }

    @NotNull
    public RangeSliderSetting visible(@NotNull Supplier<Boolean> condition) {
        Intrinsics.checkNotNullParameter(condition, (String)"condition");
        this.setVisibilityCondition(condition);
        return this;
    }

    @NotNull
    public RangeSliderSetting visibleWhen(@NotNull Supplier<Boolean> condition) {
        Intrinsics.checkNotNullParameter(condition, (String)"condition");
        return this.visible(condition);
    }

    @NotNull
    public RangeSliderSetting suffix(@Nullable String suffix) {
        String string = suffix;
        if (string == null) {
            string = "";
        }
        this.suffix = string;
        return this;
    }

    public final float getMinValue() {
        return this.minValue;
    }

    public final float getMaxValue() {
        return this.maxValue;
    }

    public final int getMinInt() {
        return Math.round(this.minValue);
    }

    public final int getMaxInt() {
        return Math.round(this.maxValue);
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

    @NotNull
    public final String getSuffix() {
        return this.suffix;
    }

    public final float getDefaultMinValue() {
        return this.defaultCaptured ? this.defaultMinValue : this.min;
    }

    public final float getDefaultMaxValue() {
        return this.defaultCaptured ? this.defaultMaxValue : this.max;
    }

    public final float getMinProgress() {
        return this.progress(this.minValue);
    }

    public final float getMaxProgress() {
        return this.progress(this.maxValue);
    }

    public final float valueAtProgress(float progress) {
        float clamped = RangesKt.coerceIn((float)progress, (float)0.0f, (float)1.0f);
        return this.clamp(this.min + clamped * (this.max - this.min));
    }

    private final float clamp(float raw) {
        float high;
        float low;
        float value = raw;
        if (this.rangeConfigured) {
            low = Math.min(this.min, this.max);
            high = Math.max(this.min, this.max);
            value = Math.max(low, Math.min(high, value));
        }
        if (this.increment > 0.0f) {
            float origin = this.rangeConfigured ? Math.min(this.min, this.max) : 0.0f;
            value = origin + (float)Math.round((value - origin) / this.increment) * this.increment;
        }
        if (this.integer) {
            value = Math.round(value);
        }
        if (this.rangeConfigured) {
            low = Math.min(this.min, this.max);
            high = Math.max(this.min, this.max);
            value = Math.max(low, Math.min(high, value));
        }
        return value;
    }

    private final float progress(float value) {
        float span = this.max - this.min;
        if (Math.abs(span) < 1.0E-6f) {
            return 0.0f;
        }
        return RangesKt.coerceIn((float)((value - this.min) / span), (float)0.0f, (float)1.0f);
    }
}

