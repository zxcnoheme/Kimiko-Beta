/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.util.Window
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 */
package rtx.kimiko.api.modules.impl.Visuals;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.util.Window;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.NumberSetting;

@Feature(value={"aspectratio"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006\u00ca\u0001\u0010\b\b\u0012\f\b\t\u0012\b\b\fJ\u0004\b\b(\n\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/AspectRatio;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "ratio", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "aspectratio", "rtx.kimiko:kimiko"})
public final class AspectRatio
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final NumberSetting ratio = (NumberSetting)this.register((Setting)new NumberSetting("Соотношение", "Настройка значения соотношения сторон.", 1.0, 0.1, 2.0, 0.01));
    @Nullable
    private static AspectRatio instance;

    public AspectRatio() {
        super("Aspect Ratio", "Изменяет соотношение сторон проекции мира.", Category.VISUALS);
        instance = this;
    }

    @Nullable
    public static final AspectRatio getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final float resolveRatio(float currentWidth, float currentHeight) {
        return Companion.resolveRatio(currentWidth, currentHeight);
    }

    @JvmStatic
    @NotNull
    public static final Matrix4f copyAdjusted(@NotNull Matrix4fc original) {
        return Companion.copyAdjusted(original);
    }

    @JvmStatic
    public static final void apply(@Nullable Matrix4f matrix) {
        Companion.apply(matrix);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J#\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001b\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\fH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0012R2\u0010\u0015\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006@BX\u0087\u000er\u0002\b\u0007\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u0012\u0004\b\u0019\u0010\u0003\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/AspectRatio.Companion;", "", "<init>", "()V", "", "currentWidth", "currentHeight", "Lkotlin/jvm/JvmStatic;", "resolveRatio", "(FF)F", "Lorg/joml/Matrix4fc;", "original", "Lorg/joml/Matrix4f;", "copyAdjusted", "(Lorg/joml/Matrix4fc;)Lorg/joml/Matrix4f;", "matrix", "", "apply", "(Lorg/joml/Matrix4f;)V", "Lrtx/kimiko/api/modules/impl/Visuals/AspectRatio;", "value", "instance", "Lrtx/kimiko/api/modules/impl/Visuals/AspectRatio;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/AspectRatio;", "getInstance$annotations", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @Nullable
        public final AspectRatio getInstance() {
            return instance;
        }

        @JvmStatic
        public static /* synthetic */ void getInstance$annotations() {
        }

        @JvmStatic
        public final float resolveRatio(float currentWidth, float currentHeight) {
            AspectRatio module = this.getInstance();
            if (module == null || !module.isEnabled() || currentHeight <= 0.0f) {
                return currentWidth;
            }
            return currentHeight * module.ratio.getFloat();
        }

        @JvmStatic
        @NotNull
        public final Matrix4f copyAdjusted(@NotNull Matrix4fc original) {
            Intrinsics.checkNotNullParameter((Object)original, (String)"original");
            Matrix4f matrix = new Matrix4f(original);
            this.apply(matrix);
            return matrix;
        }

        @JvmStatic
        public final void apply(@Nullable Matrix4f matrix) {
            AspectRatio module = this.getInstance();
            if (module == null || !module.isEnabled() || matrix == null) {
                return;
            }
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            if (minecraftClient2 == null) {
                return;
            }
            MinecraftClient client = minecraftClient2;
            Window window2 = client.getWindow();
            if (window2 == null) {
                return;
            }
            Window window = window2;
            float width = window.getFramebufferWidth();
            float height = window.getFramebufferHeight();
            if (width <= 0.0f || height <= 0.0f) {
                return;
            }
            float adjustedWidth = this.resolveRatio(width, height);
            float currentAspect = width / height;
            float targetAspect = adjustedWidth / height;
            if (!(Math.abs(currentAspect) <= Float.MAX_VALUE) || !(Math.abs(targetAspect) <= Float.MAX_VALUE) || targetAspect <= 0.0f) {
                return;
            }
            matrix.m00(matrix.m00() * (currentAspect / targetAspect));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

