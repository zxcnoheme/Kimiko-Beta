/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.drags;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0007J\r\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0007J-\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\r\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\r\u0010\u0006R\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u000eR\u0016\u0010\u0004\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u000e\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/api/drags/Position;", "", "", "x", "y", "<init>", "(FF)V", "()F", "elemWidth", "elemHeight", "", "set", "(FFFF)V", "setRaw", "F", "Companion", "rtx.kimiko:kimiko"})
public final class Position {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private float x;
    private float y;
    public static final float SCREEN_MARGIN = 5.0f;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public final float x() {
        return this.x;
    }

    public final float y() {
        return this.y;
    }

    public final void set(float x, float y, float elemWidth, float elemHeight) {
        this.x = Companion.clampX(x, elemWidth);
        this.y = Companion.clampY(y, elemHeight);
    }

    public final void setRaw(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @JvmStatic
    public static final float clampX(float x, float width) {
        return Companion.clampX(x, width);
    }

    @JvmStatic
    public static final float clampY(float y, float height) {
        return Companion.clampY(y, height);
    }

    @JvmStatic
    public static final float clampX(float x, float width, float margin) {
        return Companion.clampX(x, width, margin);
    }

    @JvmStatic
    public static final float clampY(float y, float height, float margin) {
        return Companion.clampY(y, height, margin);
    }

    @JvmStatic
    public static final float screenWidth() {
        return Companion.screenWidth();
    }

    @JvmStatic
    public static final float screenHeight() {
        return Companion.screenHeight();
    }

    @JvmStatic
    public static final float mouseX() {
        return Companion.mouseX();
    }

    @JvmStatic
    public static final float mouseY() {
        return Companion.mouseY();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J#\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ#\u0010\f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\f\u0010\tJ+\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\u000eJ+\u0010\f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\f\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0010J\u0013\u0010\u0012\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0012\u0010\u0010J\u0013\u0010\u0013\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0013\u0010\u0010R\u0014\u0010\u0014\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015\u00a8\u0006\u0016"}, d2={"Lrtx/kimiko/api/drags/Position.Companion;", "", "<init>", "()V", "", "x", "width", "Lkotlin/jvm/JvmStatic;", "clampX", "(FF)F", "y", "height", "clampY", "margin", "(FFF)F", "screenWidth", "()F", "screenHeight", "mouseX", "mouseY", "SCREEN_MARGIN", "F", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final float clampX(float x, float width) {
            return this.clampX(x, width, 5.0f);
        }

        @JvmStatic
        public final float clampY(float y, float height) {
            return this.clampY(y, height, 5.0f);
        }

        @JvmStatic
        public final float clampX(float x, float width, float margin) {
            float zoom = Render2DCoordinateSpace.uiZoom();
            float center = this.screenWidth() * 0.5f;
            float half = center / zoom;
            float minValue = center - half + margin;
            float maxValue = Math.max(minValue, center + half - width - margin);
            return Math.max(minValue, Math.min(maxValue, x));
        }

        @JvmStatic
        public final float clampY(float y, float height, float margin) {
            float zoom = Render2DCoordinateSpace.uiZoom();
            float center = this.screenHeight() * 0.5f;
            float half = center / zoom;
            float minValue = center - half + margin;
            float maxValue = Math.max(minValue, center + half - height - margin);
            return Math.max(minValue, Math.min(maxValue, y));
        }

        @JvmStatic
        public final float screenWidth() {
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient mc = minecraftClient2;
            if (mc.getWindow() == null) {
                return 960.0f;
            }
            return (float)mc.getWindow().getFramebufferWidth() / Render2DCoordinateSpace.designGuiScale();
        }

        @JvmStatic
        public final float screenHeight() {
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient mc = minecraftClient2;
            if (mc.getWindow() == null) {
                return 540.0f;
            }
            return (float)mc.getWindow().getFramebufferHeight() / Render2DCoordinateSpace.designGuiScale();
        }

        @JvmStatic
        public final float mouseX() {
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            if (minecraftClient2 == null) {
                return 0.0f;
            }
            MinecraftClient mc = minecraftClient2;
            double scaled = mc.mouse.getScaledX(mc.getWindow());
            return (float)Render2DCoordinateSpace.designXFromGui(scaled);
        }

        @JvmStatic
        public final float mouseY() {
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            if (minecraftClient2 == null) {
                return 0.0f;
            }
            MinecraftClient mc = minecraftClient2;
            double scaled = mc.mouse.getScaledY(mc.getWindow());
            return (float)Render2DCoordinateSpace.designYFromGui(scaled);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

