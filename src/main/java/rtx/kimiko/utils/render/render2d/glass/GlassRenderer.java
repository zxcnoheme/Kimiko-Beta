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
package rtx.kimiko.utils.render.render2d.glass;

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
import rtx.kimiko.utils.render.others.RoundedScissor;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.render2d.blur.BlurCapture;
import rtx.kimiko.utils.render.render2d.blur.BlurFramebuffer;
import rtx.kimiko.utils.render.render2d.blur.BuiltBlur;
import rtx.kimiko.utils.render.render2d.glass.BuiltGlass;
import rtx.kimiko.utils.render.render2d.glass.GlassRenderState;
import rtx.kimiko.utils.render.util.scissor.ScissorUtil;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u001a2\u00060\u0001j\u0002`\u0002:\u0001\u001aB\t\b\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\b\u0010\tJ!\u0010\f\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0004J#\u0010\u0011\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b\u0011\u0010\rJ\u0017\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0004R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0018\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/utils/render/render2d/glass/GlassRenderer;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "Lnet/minecraft/DrawContext;", "graphics", "", "beginFrame", "(Lnet/minecraft/DrawContext;)V", "Lrtx/kimiko/utils/render/render2d/glass/BuiltGlass;", "glass", "draw", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/utils/render/render2d/glass/BuiltGlass;)V", "enqueue", "(Lrtx/kimiko/utils/render/render2d/glass/BuiltGlass;)V", "flush", "submit", "normalize", "(Lrtx/kimiko/utils/render/render2d/glass/BuiltGlass;)Lrtx/kimiko/utils/render/render2d/glass/BuiltGlass;", "close", "activeGraphics", "Lnet/minecraft/DrawContext;", "", "paramsDirty", "Z", "Companion", "rtx.kimiko:kimiko"})
public final class GlassRenderer
implements AutoCloseable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    private DrawContext activeGraphics;
    private boolean paramsDirty = true;
    @Nullable
    private static volatile GlassRenderer instance;

    private GlassRenderer() {
    }

    public final void beginFrame(@Nullable DrawContext graphics) {
        this.activeGraphics = graphics;
    }

    public final void draw(@Nullable DrawContext graphics, @Nullable BuiltGlass glass) {
        this.beginFrame(graphics);
        this.enqueue(glass);
        this.flush();
    }

    public final void enqueue(@Nullable BuiltGlass glass) {
        this.submit(this.activeGraphics, glass);
    }

    public final void flush() {
        this.activeGraphics = null;
    }

    private final void submit(DrawContext graphics, BuiltGlass glass) {
        if (graphics == null || glass == null || !glass.visible()) {
            return;
        }
        try {
            BuiltGlass clipped = RoundedScissor.isEnabled() && RoundedScissor.fadeTop() > 0.0f ? BuiltGlass.copy$default(glass, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0.0f, false, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0.0f, 0, 0, RoundedScissor.fadeTop(), RoundedScissor.y(), 0x7FFFFF, null) : glass;
            BuiltGlass normalized = this.normalize(clipped);
            Matrix3x2f pose = Render2DCoordinateSpace.pose(graphics);
            BlurCapture capture = new BlurCapture();
            BlurFramebuffer.Companion.getInstance().requestCapture(graphics, new BuiltBlur(normalized.x(), normalized.y(), normalized.width(), normalized.height(), normalized.radiusTopLeft(), normalized.radiusTopRight(), normalized.radiusBottomRight(), normalized.radiusBottomLeft(), 1.0f, normalized.blurRadius(), -1), capture);
            ((GuiGraphicsExtractorAccessor)graphics).kimiko$getGuiRenderState().addSimpleElement((SimpleGuiElementRenderState)new GlassRenderState(pose, normalized, ScissorUtil.current(), capture));
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    private final BuiltGlass normalize(BuiltGlass glass) {
        float maxRadius = Math.max(0.0f, Math.min(glass.width(), glass.height()) * 0.5f);
        float radiusTopLeft = GlassRenderer.Companion.clamp(glass.radiusTopLeft(), 0.0f, maxRadius);
        float radiusTopRight = GlassRenderer.Companion.clamp(glass.radiusTopRight(), 0.0f, maxRadius);
        float radiusBottomRight = GlassRenderer.Companion.clamp(glass.radiusBottomRight(), 0.0f, maxRadius);
        float radiusBottomLeft = GlassRenderer.Companion.clamp(glass.radiusBottomLeft(), 0.0f, maxRadius);
        float globalAlpha = GlassRenderer.Companion.clamp(glass.globalAlpha(), 0.0f, 1.0f);
        float fresnelPower = Math.max(glass.fresnelPower(), 0.001f);
        float baseAlpha = GlassRenderer.Companion.clamp(glass.baseAlpha(), 0.0f, 1.0f);
        float fresnelMix = GlassRenderer.Companion.clamp(glass.fresnelMix(), 0.0f, 1.0f);
        float squirt = Math.max(glass.squirt(), 0.001f);
        float blurRadius = Math.abs(glass.blurRadius()) <= Float.MAX_VALUE ? GlassRenderer.Companion.clamp(glass.blurRadius(), 0.1f, 64.0f) : 0.1f;
        float colorOffset = Math.abs(glass.colorOffset()) <= Float.MAX_VALUE ? glass.colorOffset() : 0.0f;
        colorOffset -= (float)Math.floor(colorOffset);
        if (radiusTopLeft == glass.radiusTopLeft() && radiusTopRight == glass.radiusTopRight() && radiusBottomRight == glass.radiusBottomRight() && radiusBottomLeft == glass.radiusBottomLeft() && globalAlpha == glass.globalAlpha() && fresnelPower == glass.fresnelPower() && baseAlpha == glass.baseAlpha() && fresnelMix == glass.fresnelMix() && squirt == glass.squirt() && blurRadius == glass.blurRadius() && colorOffset == glass.colorOffset()) {
            return glass;
        }
        return new BuiltGlass(glass.x(), glass.y(), glass.width(), glass.height(), radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft, glass.color(), globalAlpha, fresnelPower, glass.fresnelColor(), baseAlpha, glass.fresnelInvert(), fresnelMix, glass.distortStrength(), squirt, glass.z(), blurRadius, glass.secondColor(), colorOffset).withSplitIndex(glass.splitIndex()).withPaletteSlot(glass.paletteSlot());
    }

    @Override
    public void close() {
        this.activeGraphics = null;
    }

    @JvmStatic
    @NotNull
    public static final GlassRenderer getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final void closeInstance() {
        Companion.closeInstance();
    }

    public /* synthetic */ GlassRenderer(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J'\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/utils/render/render2d/glass/GlassRenderer.Companion;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/render2d/glass/GlassRenderer;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/utils/render/render2d/glass/GlassRenderer;", "", "closeInstance", "", "value", "min", "max", "clamp", "(FFF)F", "", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "instance", "Lrtx/kimiko/utils/render/render2d/glass/GlassRenderer;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @JvmStatic
        @NotNull
        public final GlassRenderer getInstance() {
            GlassRenderer local = null;
            local = instance;
            if (local == null) {
                Class<GlassRenderer> clazz = GlassRenderer.class;
                synchronized (clazz) {
                    boolean bl = false;
                    local = instance;
                    if (local == null) {
                        local = new GlassRenderer(null);
                        instance = local;
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            return local;
        }

        @JvmStatic
        public final void closeInstance() {
            GlassRenderer local = instance;
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

