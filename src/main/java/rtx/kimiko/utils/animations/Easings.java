/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.animations;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.animations.Easing;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u0019\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0007R\u0019\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0007R\u0019\u0010\n\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0007R\u0019\u0010\u000b\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\u0007R\u0019\u0010\f\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\f\u0010\u0007R\u0019\u0010\r\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\r\u0010\u0007R\u0019\u0010\u000e\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u0007\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/utils/animations/Easings;", "", "<init>", "()V", "Lrtx/kimiko/utils/animations/Easing;", "Lkotlin/jvm/JvmField;", "LINEAR", "Lrtx/kimiko/utils/animations/Easing;", "QUAD_OUT", "CUBIC_OUT", "EXPO_IN", "EXPO_OUT", "EXPO_IN_OUT", "SINE_OUT", "BACK_OUT", "rtx.kimiko:kimiko"})
public final class Easings {
    @NotNull
    public static final Easings INSTANCE = new Easings();
    @JvmField
    @NotNull
    public static final Easing LINEAR = Easings::LINEAR$lambda$0;
    @JvmField
    @NotNull
    public static final Easing QUAD_OUT = Easings::QUAD_OUT$lambda$0;
    @JvmField
    @NotNull
    public static final Easing CUBIC_OUT = Easings::CUBIC_OUT$lambda$0;
    @JvmField
    @NotNull
    public static final Easing EXPO_IN = Easings::EXPO_IN$lambda$0;
    @JvmField
    @NotNull
    public static final Easing EXPO_OUT = Easings::EXPO_OUT$lambda$0;
    @JvmField
    @NotNull
    public static final Easing EXPO_IN_OUT = Easings::EXPO_IN_OUT$lambda$0;
    @JvmField
    @NotNull
    public static final Easing SINE_OUT = Easings::SINE_OUT$lambda$0;
    @JvmField
    @NotNull
    public static final Easing BACK_OUT = Easings::BACK_OUT$lambda$0;

    private Easings() {
    }

    private static final double LINEAR$lambda$0(double value) {
        return value;
    }

    private static final double QUAD_OUT$lambda$0(double value) {
        return 1.0 - Math.pow(1.0 - value, 2.0);
    }

    private static final double CUBIC_OUT$lambda$0(double value) {
        return 1.0 - Math.pow(1.0 - value, 3.0);
    }

    private static final double EXPO_IN$lambda$0(double value) {
        return value == 0.0 ? 0.0 : Math.pow(2.0, 10.0 * value - 10.0);
    }

    private static final double EXPO_OUT$lambda$0(double value) {
        return value == 1.0 ? 1.0 : 1.0 - Math.pow(2.0, -10.0 * value);
    }

    private static final double EXPO_IN_OUT$lambda$0(double value) {
        return value == 0.0 || value == 1.0 ? value : (value < 0.5 ? Math.pow(2.0, 20.0 * value - 10.0) / 2.0 : (2.0 - Math.pow(2.0, -20.0 * value + 10.0)) / 2.0);
    }

    private static final double SINE_OUT$lambda$0(double value) {
        return Math.sin(value * Math.PI / 2.0);
    }

    private static final double BACK_OUT$lambda$0(double value) {
        double c1 = 1.70158;
        double c3 = c1 + 1.0;
        return 1.0 + c3 * Math.pow(value - 1.0, 3.0) + c1 * Math.pow(value - 1.0, 2.0);
    }
}

