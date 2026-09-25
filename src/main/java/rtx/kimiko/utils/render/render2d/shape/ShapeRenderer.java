/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.render.state.SimpleGuiElementRenderState
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 */
package rtx.kimiko.utils.render.render2d.shape;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mixin.accessor.GuiGraphicsExtractorAccessor;
import net.minecraft.client.gui.render.state.SimpleGuiElementRenderState;
import net.minecraft.util.Identifier;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.render2d.blur.BlurCapture;
import rtx.kimiko.utils.render.render2d.blur.BlurFramebuffer;
import rtx.kimiko.utils.render.render2d.blur.BuiltBlur;
import rtx.kimiko.utils.render.render2d.shape.BuiltShape;
import rtx.kimiko.utils.render.render2d.shape.ShapeRenderState;
import rtx.kimiko.utils.render.util.scissor.ScissorUtil;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u001b2\u00060\u0001j\u0002`\u0002:\u0001\u001bB\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\b\u0010\tJ!\u0010\f\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0004J#\u0010\u0011\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b\u0011\u0010\rJ\u0017\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0004R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0019\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/utils/render/render2d/shape/ShapeRenderer;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "Lnet/minecraft/DrawContext;", "graphics", "", "beginFrame", "(Lnet/minecraft/DrawContext;)V", "Lrtx/kimiko/utils/render/render2d/shape/BuiltShape;", "shape", "draw", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/utils/render/render2d/shape/BuiltShape;)V", "enqueue", "(Lrtx/kimiko/utils/render/render2d/shape/BuiltShape;)V", "flush", "submit", "s", "normalize", "(Lrtx/kimiko/utils/render/render2d/shape/BuiltShape;)Lrtx/kimiko/utils/render/render2d/shape/BuiltShape;", "close", "activeGraphics", "Lnet/minecraft/DrawContext;", "", "paramsDirty", "Z", "Companion", "rtx.kimiko:kimiko"})
public final class ShapeRenderer
implements AutoCloseable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    private DrawContext activeGraphics;
    private boolean paramsDirty = true;
    public static final int MAX_SPANS = 64;
    @Nullable
    private static volatile ShapeRenderer instance;

    private ShapeRenderer() {
    }

    public final void beginFrame(@Nullable DrawContext graphics) {
        this.activeGraphics = graphics;
    }

    public final void draw(@Nullable DrawContext graphics, @Nullable BuiltShape shape) {
        this.beginFrame(graphics);
        this.enqueue(shape);
        this.flush();
    }

    public final void enqueue(@Nullable BuiltShape shape) {
        this.submit(this.activeGraphics, shape);
    }

    public final void flush() {
        this.activeGraphics = null;
    }

    private final void submit(DrawContext graphics, BuiltShape shape) {
        if (graphics == null || shape == null || !shape.visible()) {
            return;
        }
        try {
            BuiltShape normalized = this.normalize(shape);
            Matrix3x2f pose = Render2DCoordinateSpace.pose(graphics);
            BlurCapture capture = new BlurCapture();
            BlurFramebuffer.Companion.getInstance().requestCapture(graphics, new BuiltBlur(normalized.x(), normalized.y(), normalized.width(), normalized.height(), normalized.radiusTopLeft(), normalized.radiusTopRight(), normalized.radiusBottomRight(), normalized.radiusBottomLeft(), 1.0f, normalized.blurRadius(), -1), capture);
            ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState().addSimpleElement((SimpleGuiElementRenderState)new ShapeRenderState(pose, normalized, ScissorUtil.current(), capture));
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    private final BuiltShape normalize(BuiltShape s) {
        float maxRadius = Math.max(0.0f, Math.min(s.width(), s.height()) * 0.5f);
        float rTL = ShapeRenderer.Companion.clamp(s.radiusTopLeft(), 0.0f, maxRadius);
        float rTR = ShapeRenderer.Companion.clamp(s.radiusTopRight(), 0.0f, maxRadius);
        float rBR = ShapeRenderer.Companion.clamp(s.radiusBottomRight(), 0.0f, maxRadius);
        float rBL = ShapeRenderer.Companion.clamp(s.radiusBottomLeft(), 0.0f, maxRadius);
        float globalAlpha = ShapeRenderer.Companion.clamp(s.globalAlpha(), 0.0f, 1.0f);
        float fresnelPower = Math.max(s.fresnelPower(), 0.001f);
        float baseAlpha = ShapeRenderer.Companion.clamp(s.baseAlpha(), 0.0f, 1.0f);
        float fresnelMix = ShapeRenderer.Companion.clamp(s.fresnelMix(), 0.0f, 1.0f);
        float squirt = Math.max(s.squirt(), 0.001f);
        float blurRadius = Math.abs(s.blurRadius()) <= Float.MAX_VALUE ? ShapeRenderer.Companion.clamp(s.blurRadius(), 0.1f, 64.0f) : 0.1f;
        float colorOffset = Math.abs(s.colorOffset()) <= Float.MAX_VALUE ? s.colorOffset() : 0.0f;
        colorOffset -= (float)Math.floor(colorOffset);
        if (rTL == s.radiusTopLeft() && rTR == s.radiusTopRight() && rBR == s.radiusBottomRight() && rBL == s.radiusBottomLeft() && globalAlpha == s.globalAlpha() && fresnelPower == s.fresnelPower() && baseAlpha == s.baseAlpha() && fresnelMix == s.fresnelMix() && squirt == s.squirt() && blurRadius == s.blurRadius() && colorOffset == s.colorOffset()) {
            return s;
        }
        return BuiltShape.copy$default(s, 0.0f, 0.0f, 0.0f, 0.0f, null, 0, 0.0f, rTL, rTR, rBR, rBL, 0, globalAlpha, fresnelPower, 0, baseAlpha, false, fresnelMix, 0.0f, squirt, 0.0f, blurRadius, 0, colorOffset, 0.0f, 0.0f, 0, 0.0f, 0.0f, false, 1062553727, null);
    }

    @Override
    public void close() {
        this.activeGraphics = null;
    }

    @JvmStatic
    @NotNull
    public static final ShapeRenderer getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final void closeInstance() {
        Companion.closeInstance();
    }

    public /* synthetic */ ShapeRenderer(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J'\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/utils/render/render2d/shape/ShapeRenderer.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/render2d/shape/ShapeRenderer;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/utils/render/render2d/shape/ShapeRenderer;", "", "closeInstance", "", "value", "min", "max", "clamp", "(FFF)F", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "", "MAX_SPANS", "I", "instance", "Lrtx/kimiko/utils/render/render2d/shape/ShapeRenderer;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        @NotNull
        public final ShapeRenderer getInstance() {
            ShapeRenderer local = null;
            local = instance;
            if (local == null) {
                Class<ShapeRenderer> clazz = ShapeRenderer.class;
                synchronized (clazz) {
                    boolean bl = false;
                    local = instance;
                    if (local == null) {
                        local = new ShapeRenderer(null);
                        instance = local;
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            return local;
        }

        @JvmStatic
        public final void closeInstance() {
            ShapeRenderer local = instance;
            if (local != null) {
                local.close();
                instance = null;
            }
        }

        private final float clamp(float value, float min, float max) {
            return Math.max(min, Math.min(max, value));
        }

        private final Identifier id(String path) {
            Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
            Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
            return identifier2;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

