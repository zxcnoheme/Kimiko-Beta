/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.settings.impl;

import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.utils.animations.AnimationUtil;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 @2\u00020\u0001:\u0001@B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\n\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ'\u0010\n\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\n\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u0011\u0010\u000fJ/\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u000bJ\u0017\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u001c\u0010\u000fJ\u001d\u0010 \u001a\u00020\u00002\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0016\u00a2\u0006\u0004\b \u0010!J\r\u0010\"\u001a\u00020\u0007\u00a2\u0006\u0004\b\"\u0010#J\r\u0010$\u001a\u00020\u0007\u00a2\u0006\u0004\b$\u0010#J\r\u0010%\u001a\u00020\u0007\u00a2\u0006\u0004\b%\u0010#J\r\u0010&\u001a\u00020\u0007\u00a2\u0006\u0004\b&\u0010#J\r\u0010'\u001a\u00020\u0007\u00a2\u0006\u0004\b'\u0010#J\r\u0010(\u001a\u00020\u0007\u00a2\u0006\u0004\b(\u0010#J\r\u0010)\u001a\u00020\u0007\u00a2\u0006\u0004\b)\u0010#J\r\u0010*\u001a\u00020\u0007\u00a2\u0006\u0004\b*\u0010#J\r\u0010+\u001a\u00020\u0007\u00a2\u0006\u0004\b+\u0010#J\r\u0010,\u001a\u00020\u0007\u00a2\u0006\u0004\b,\u0010#J\r\u0010-\u001a\u00020\u0007\u00a2\u0006\u0004\b-\u0010#J\r\u0010.\u001a\u00020\u0007\u00a2\u0006\u0004\b.\u0010#J\r\u0010/\u001a\u00020\u0007\u00a2\u0006\u0004\b/\u0010#J\r\u00100\u001a\u00020\u001e\u00a2\u0006\u0004\b0\u00101J\r\u00102\u001a\u00020\u001e\u00a2\u0006\u0004\b2\u00101J\r\u00103\u001a\u00020\u001e\u00a2\u0006\u0004\b3\u00101J\r\u00104\u001a\u00020\u0007\u00a2\u0006\u0004\b4\u0010#R\u0016\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u00105R\u0016\u0010\t\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\t\u00105R\u0016\u0010\f\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\f\u00105R\u0016\u0010\u0012\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0012\u00105R\u0016\u0010\u0013\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u00105R\u0016\u0010\u0014\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0014\u00105R\u0016\u0010\u0015\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0015\u00105R\u0016\u0010\u0018\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0018\u00105R\u0016\u0010\u0019\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u00105R\u0016\u00106\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b6\u00105R\u0016\u00107\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b7\u00105R\u0016\u00108\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b8\u00105R\u0016\u00109\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b9\u0010:R\u0016\u0010;\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b;\u0010:R\u0016\u0010<\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u0010:R\u0016\u0010\u001c\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u00105R\u0014\u0010>\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b>\u0010?\u00a8\u0006A"}, d2={"Lrtx/kimiko/api/modules/settings/impl/PositionSettings;", "Lrtx/kimiko/api/modules/settings/Setting;", "", "name", "description", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "", "x", "y", "setValue", "(FF)Lrtx/kimiko/api/modules/settings/impl/PositionSettings;", "z", "(FFF)Lrtx/kimiko/api/modules/settings/impl/PositionSettings;", "setX", "(F)Lrtx/kimiko/api/modules/settings/impl/PositionSettings;", "setY", "setZ", "minX", "maxX", "minY", "maxY", "range", "(FFFF)Lrtx/kimiko/api/modules/settings/impl/PositionSettings;", "minZ", "maxZ", "rangeZ", "step", "zStep", "Ljava/util/function/Supplier;", "", "condition", "visible", "(Ljava/util/function/Supplier;)Lrtx/kimiko/api/modules/settings/impl/PositionSettings;", "getX", "()F", "getY", "getZ", "getAnimatedZ", "getMinX", "getMaxX", "getMinY", "getMaxY", "getMinZ", "getMaxZ", "getDefaultX", "getDefaultY", "getDefaultZ", "isDefaultXCaptured", "()Z", "isDefaultYCaptured", "isDefaultZCaptured", "getZStep", "F", "defaultX", "defaultY", "defaultZ", "defaultXCaptured", "Z", "defaultYCaptured", "defaultZCaptured", "Lrtx/kimiko/utils/animations/AnimationUtil;", "zAnimation", "Lrtx/kimiko/utils/animations/AnimationUtil;", "Companion", "rtx.kimiko:kimiko"})
public class PositionSettings
extends Setting {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private float x;
    private float y;
    private float z;
    private float minX;
    private float maxX;
    private float minY;
    private float maxY;
    private float minZ;
    private float maxZ;
    private float defaultX;
    private float defaultY;
    private float defaultZ;
    private boolean defaultXCaptured;
    private boolean defaultYCaptured;
    private boolean defaultZCaptured;
    private float zStep;
    @NotNull
    private final AnimationUtil zAnimation;

    public PositionSettings(@NotNull String name, @NotNull String description) {
        super(name, description);
        this.zStep = 0.05f;
        this.zAnimation = new AnimationUtil(0.0f, 0.0f, 0.18f, AnimationUtil.Easing.CUBIC_OUT);
    }

    @NotNull
    public PositionSettings setValue(float x, float y) {
        this.setX(x);
        this.setY(y);
        return this;
    }

    @NotNull
    public PositionSettings setValue(float x, float y, float z) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
        return this;
    }

    @NotNull
    public PositionSettings setX(float x) {
        float next = PositionSettings.Companion.clamp(x, this.minX, this.maxX);
        boolean changed = Float.compare(this.x, next) != 0;
        this.x = next;
        if (!this.defaultXCaptured) {
            this.defaultX = this.x;
            this.defaultXCaptured = true;
        }
        if (changed) {
            this.notifyChanged();
        }
        return this;
    }

    @NotNull
    public PositionSettings setY(float y) {
        float next = PositionSettings.Companion.clamp(y, this.minY, this.maxY);
        boolean changed = Float.compare(this.y, next) != 0;
        this.y = next;
        if (!this.defaultYCaptured) {
            this.defaultY = this.y;
            this.defaultYCaptured = true;
        }
        if (changed) {
            this.notifyChanged();
        }
        return this;
    }

    @NotNull
    public PositionSettings setZ(float z) {
        float next = PositionSettings.Companion.clamp(z, this.minZ, this.maxZ);
        boolean changed = Float.compare(this.z, next) != 0;
        this.z = next;
        if (!this.defaultZCaptured) {
            this.defaultZ = this.z;
            this.defaultZCaptured = true;
            this.zAnimation.setAnim(this.z);
        }
        this.zAnimation.setTo(this.z);
        if (changed) {
            this.notifyChanged();
        }
        return this;
    }

    @NotNull
    public PositionSettings range(float minX, float maxX, float minY, float maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.x = PositionSettings.Companion.clamp(this.x, minX, maxX);
        this.y = PositionSettings.Companion.clamp(this.y, minY, maxY);
        return this;
    }

    @NotNull
    public PositionSettings rangeZ(float minZ, float maxZ) {
        this.minZ = minZ;
        this.maxZ = maxZ;
        this.z = PositionSettings.Companion.clamp(this.z, minZ, maxZ);
        this.zAnimation.setTo(this.z);
        return this;
    }

    @NotNull
    public PositionSettings zStep(float step) {
        this.zStep = Math.max(0.001f, Math.abs(step));
        return this;
    }

    @NotNull
    public PositionSettings visible(@NotNull Supplier<Boolean> condition) {
        Intrinsics.checkNotNullParameter(condition, (String)"condition");
        this.setVisibilityCondition(condition);
        return this;
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }

    public final float getZ() {
        return this.z;
    }

    public final float getAnimatedZ() {
        this.zAnimation.setTo(this.z);
        return this.zAnimation.getAnim();
    }

    public final float getMinX() {
        return this.minX;
    }

    public final float getMaxX() {
        return this.maxX;
    }

    public final float getMinY() {
        return this.minY;
    }

    public final float getMaxY() {
        return this.maxY;
    }

    public final float getMinZ() {
        return this.minZ;
    }

    public final float getMaxZ() {
        return this.maxZ;
    }

    public final float getDefaultX() {
        return this.defaultX;
    }

    public final float getDefaultY() {
        return this.defaultY;
    }

    public final float getDefaultZ() {
        return this.defaultZ;
    }

    public final boolean isDefaultXCaptured() {
        return this.defaultXCaptured;
    }

    public final boolean isDefaultYCaptured() {
        return this.defaultYCaptured;
    }

    public final boolean isDefaultZCaptured() {
        return this.defaultZCaptured;
    }

    public final float getZStep() {
        return this.zStep;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/api/modules/settings/impl/PositionSettings.Companion;", "", "<init>", "()V", "", "value", "min", "max", "clamp", "(FFF)F", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float clamp(float value, float min, float max) {
            float lo = Math.min(min, max);
            float hi = Math.max(min, max);
            return Math.max(lo, Math.min(hi, value));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

