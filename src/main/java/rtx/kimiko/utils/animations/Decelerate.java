/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 */
package rtx.kimiko.utils.animations;

import kotlin.Metadata;
import rtx.kimiko.utils.animations.Animation;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/utils/animations/Decelerate;", "Lrtx/kimiko/utils/animations/Animation;", "<init>", "()V", "", "value", "calculation", "(D)D", "rtx.kimiko:kimiko"})
public class Decelerate
extends Animation {
    @Override
    public double calculation(double value) {
        double x = value / (double)this.ms;
        return 1.0 - (x - 1.0) * (x - 1.0);
    }
}

