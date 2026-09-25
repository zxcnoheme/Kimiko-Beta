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
package rtx.kimiko.utils.render.render2d.radialglass;

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
import rtx.kimiko.utils.render.render2d.radialglass.BuiltRadialGlass;
import rtx.kimiko.utils.render.render2d.radialglass.RadialGlassRenderState;
import rtx.kimiko.utils.render.util.scissor.ScissorUtil;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u00192\u00060\u0001j\u0002`\u0002:\u0001\u0019B\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0007\u00a2\u0006\u0004\b\u000e\u0010\u0004J#\u0010\u000f\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0004R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0017\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/utils/render/render2d/radialglass/RadialGlassRenderer;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "Lnet/minecraft/DrawContext;", "graphics", "", "beginFrame", "(Lnet/minecraft/DrawContext;)V", "Lrtx/kimiko/utils/render/render2d/radialglass/BuiltRadialGlass;", "sector", "enqueue", "(Lrtx/kimiko/utils/render/render2d/radialglass/BuiltRadialGlass;)V", "flush", "submit", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/utils/render/render2d/radialglass/BuiltRadialGlass;)V", "normalize", "(Lrtx/kimiko/utils/render/render2d/radialglass/BuiltRadialGlass;)Lrtx/kimiko/utils/render/render2d/radialglass/BuiltRadialGlass;", "close", "activeGraphics", "Lnet/minecraft/DrawContext;", "", "paramsDirty", "Z", "Companion", "rtx.kimiko:kimiko"})
public final class RadialGlassRenderer
implements AutoCloseable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    private DrawContext activeGraphics;
    private boolean paramsDirty = true;
    @Nullable
    private static volatile RadialGlassRenderer instance;

    private RadialGlassRenderer() {
    }

    public final void beginFrame(@Nullable DrawContext graphics) {
        this.activeGraphics = graphics;
    }

    public final void enqueue(@Nullable BuiltRadialGlass sector) {
        this.submit(this.activeGraphics, sector);
    }

    public final void flush() {
        this.activeGraphics = null;
    }

    private final void submit(DrawContext graphics, BuiltRadialGlass sector) {
        if (graphics == null || sector == null || !sector.visible()) {
            return;
        }
        try {
            BuiltRadialGlass normalized = this.normalize(sector);
            Matrix3x2f pose = Render2DCoordinateSpace.pose(graphics);
            float extent = normalized.extent();
            BlurCapture capture = new BlurCapture();
            BlurFramebuffer.Companion.getInstance().requestCapture(graphics, new BuiltBlur(normalized.centerX() - extent, normalized.centerY() - extent, extent * 2.0f, extent * 2.0f, extent, extent, extent, extent, 1.0f, normalized.blurRadius(), -1), capture);
            ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState().addSimpleElement((SimpleGuiElementRenderState)new RadialGlassRenderState(pose, normalized, ScissorUtil.current(), capture));
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    private final BuiltRadialGlass normalize(BuiltRadialGlass sector) {
        float outer = Math.max(sector.outerRadius(), 0.0f);
        float inner = RadialGlassRenderer.Companion.clamp(sector.innerRadius(), 0.0f, outer);
        float halfAngle = RadialGlassRenderer.Companion.clamp(sector.halfAngle(), 0.0f, 1.45f);
        float ringHalf = (outer - inner) * 0.5f;
        float arcHalf = (inner + ringHalf) * (float)Math.tan(Math.min(halfAngle, 1.3f));
        float corner = RadialGlassRenderer.Companion.clamp(sector.corner(), 0.0f, Math.max(0.0f, Math.min(ringHalf, arcHalf) - 0.1f));
        float blurRadius = Math.abs(sector.blurRadius()) <= Float.MAX_VALUE ? RadialGlassRenderer.Companion.clamp(sector.blurRadius(), 0.1f, 64.0f) : 0.1f;
        float colorOffset = Math.abs(sector.colorOffset()) <= Float.MAX_VALUE ? sector.colorOffset() : 0.0f;
        colorOffset -= (float)Math.floor(colorOffset);
        return new BuiltRadialGlass(sector.centerX(), sector.centerY(), inner, outer, sector.midAngle(), halfAngle, corner, Math.max(sector.feather(), 0.2f), sector.color(), sector.secondColor(), colorOffset, RadialGlassRenderer.Companion.clamp(sector.globalAlpha(), 0.0f, 1.0f), Math.max(sector.fresnelPower(), 0.001f), sector.fresnelColor(), RadialGlassRenderer.Companion.clamp(sector.baseAlpha(), 0.0f, 1.0f), sector.fresnelInvert(), RadialGlassRenderer.Companion.clamp(sector.fresnelMix(), 0.0f, 1.0f), sector.distortStrength(), blurRadius, sector.highlightColor(), RadialGlassRenderer.Companion.clamp(sector.highlight(), 0.0f, 1.0f), sector.z());
    }

    @Override
    public void close() {
        this.activeGraphics = null;
    }

    @JvmStatic
    @NotNull
    public static final RadialGlassRenderer getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final void closeInstance() {
        Companion.closeInstance();
    }

    public /* synthetic */ RadialGlassRenderer(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J'\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/utils/render/render2d/radialglass/RadialGlassRenderer.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/render2d/radialglass/RadialGlassRenderer;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/utils/render/render2d/radialglass/RadialGlassRenderer;", "", "closeInstance", "", "value", "min", "max", "clamp", "(FFF)F", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "instance", "Lrtx/kimiko/utils/render/render2d/radialglass/RadialGlassRenderer;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        @NotNull
        public final RadialGlassRenderer getInstance() {
            RadialGlassRenderer local = null;
            local = instance;
            if (local == null) {
                Class<RadialGlassRenderer> clazz = RadialGlassRenderer.class;
                synchronized (clazz) {
                    boolean bl = false;
                    local = instance;
                    if (local == null) {
                        local = new RadialGlassRenderer(null);
                        instance = local;
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            return local;
        }

        @JvmStatic
        public final void closeInstance() {
            RadialGlassRenderer local = instance;
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

