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
package rtx.kimiko.utils.render.render2d.outline.outlineglass;

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
import rtx.kimiko.utils.render.render2d.outline.outlineglass.BuiltGlassOutline;
import rtx.kimiko.utils.render.render2d.outline.outlineglass.GlassOutlineRenderState;
import rtx.kimiko.utils.render.util.scissor.ScissorUtil;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u001a2\u00060\u0001j\u0002`\u0002:\u0001\u001aB\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\b\u0010\tJ!\u0010\f\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0004J#\u0010\u0011\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b\u0011\u0010\rJ\u0017\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0004R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0018\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/utils/render/render2d/outline/outlineglass/GlassOutlineRenderer;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "Lnet/minecraft/DrawContext;", "graphics", "", "beginFrame", "(Lnet/minecraft/DrawContext;)V", "Lrtx/kimiko/utils/render/render2d/outline/outlineglass/BuiltGlassOutline;", "outline", "draw", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/utils/render/render2d/outline/outlineglass/BuiltGlassOutline;)V", "enqueue", "(Lrtx/kimiko/utils/render/render2d/outline/outlineglass/BuiltGlassOutline;)V", "flush", "submit", "normalize", "(Lrtx/kimiko/utils/render/render2d/outline/outlineglass/BuiltGlassOutline;)Lrtx/kimiko/utils/render/render2d/outline/outlineglass/BuiltGlassOutline;", "close", "activeGraphics", "Lnet/minecraft/DrawContext;", "", "paramsDirty", "Z", "Companion", "rtx.kimiko:kimiko"})
public final class GlassOutlineRenderer
implements AutoCloseable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    private DrawContext activeGraphics;
    private boolean paramsDirty = true;
    @Nullable
    private static volatile GlassOutlineRenderer instance;

    private GlassOutlineRenderer() {
    }

    public final void beginFrame(@Nullable DrawContext graphics) {
        this.activeGraphics = graphics;
    }

    public final void draw(@Nullable DrawContext graphics, @Nullable BuiltGlassOutline outline) {
        this.beginFrame(graphics);
        this.enqueue(outline);
        this.flush();
    }

    public final void enqueue(@Nullable BuiltGlassOutline outline) {
        this.submit(this.activeGraphics, outline);
    }

    public final void flush() {
        this.activeGraphics = null;
    }

    private final void submit(DrawContext graphics, BuiltGlassOutline outline) {
        if (graphics == null || outline == null || !outline.visible()) {
            return;
        }
        try {
            BuiltGlassOutline normalized = this.normalize(outline);
            Matrix3x2f pose = Render2DCoordinateSpace.pose(graphics);
            BlurCapture capture = new BlurCapture();
            BlurFramebuffer.Companion.getInstance().requestCapture(graphics, new BuiltBlur(normalized.x(), normalized.y(), normalized.width(), normalized.height(), normalized.radiusTopLeft(), normalized.radiusTopRight(), normalized.radiusBottomRight(), normalized.radiusBottomLeft(), 1.0f, 30.0f, -1), capture);
            ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState().addSimpleElement((SimpleGuiElementRenderState)new GlassOutlineRenderState(pose, normalized, ScissorUtil.current(), capture));
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    private final BuiltGlassOutline normalize(BuiltGlassOutline outline) {
        float maxRadius = Math.max(0.0f, Math.min(outline.width(), outline.height()) * 0.5f);
        float maxThickness = Math.max(0.0f, Math.min(outline.width(), outline.height()) * 0.5f);
        return new BuiltGlassOutline(outline.x(), outline.y(), outline.width(), outline.height(), GlassOutlineRenderer.Companion.clamp(outline.radiusTopLeft(), 0.0f, maxRadius), GlassOutlineRenderer.Companion.clamp(outline.radiusTopRight(), 0.0f, maxRadius), GlassOutlineRenderer.Companion.clamp(outline.radiusBottomRight(), 0.0f, maxRadius), GlassOutlineRenderer.Companion.clamp(outline.radiusBottomLeft(), 0.0f, maxRadius), GlassOutlineRenderer.Companion.clamp(outline.thickness(), 0.0f, maxThickness), outline.color(), GlassOutlineRenderer.Companion.clamp(outline.globalAlpha(), 0.0f, 1.0f), Math.max(outline.fresnelPower(), 0.001f), outline.fresnelColor(), GlassOutlineRenderer.Companion.clamp(outline.baseAlpha(), 0.0f, 1.0f), outline.fresnelInvert(), GlassOutlineRenderer.Companion.clamp(outline.fresnelMix(), 0.0f, 1.0f), outline.distortStrength(), Math.max(outline.squirt(), 0.001f), Math.max(outline.smoothness(), 0.0f), outline.z());
    }

    @Override
    public void close() {
        this.activeGraphics = null;
    }

    @JvmStatic
    @NotNull
    public static final GlassOutlineRenderer getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final void closeInstance() {
        Companion.closeInstance();
    }

    public /* synthetic */ GlassOutlineRenderer(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J'\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/utils/render/render2d/outline/outlineglass/GlassOutlineRenderer.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/render2d/outline/outlineglass/GlassOutlineRenderer;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/utils/render/render2d/outline/outlineglass/GlassOutlineRenderer;", "", "closeInstance", "", "value", "min", "max", "clamp", "(FFF)F", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "instance", "Lrtx/kimiko/utils/render/render2d/outline/outlineglass/GlassOutlineRenderer;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        @NotNull
        public final GlassOutlineRenderer getInstance() {
            GlassOutlineRenderer local = null;
            local = instance;
            if (local == null) {
                Class<GlassOutlineRenderer> clazz = GlassOutlineRenderer.class;
                synchronized (clazz) {
                    boolean bl = false;
                    local = instance;
                    if (local == null) {
                        local = new GlassOutlineRenderer(null);
                        instance = local;
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            return local;
        }

        @JvmStatic
        public final void closeInstance() {
            GlassOutlineRenderer local = instance;
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

