/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.collections.MapsKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.SourceDebugExtension
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.core.frame;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.core.batch.UiBatch;
import rtx.kimiko.utils.render.core.uniform.UniformLayout;
import rtx.kimiko.utils.render.render2d.arc.ArcBatch;
import rtx.kimiko.utils.render.render2d.arcrect.ArcRectBatch;
import rtx.kimiko.utils.render.render2d.circle.CircleBatch;
import rtx.kimiko.utils.render.render2d.glass.GlassBatch;
import rtx.kimiko.utils.render.render2d.image.ImageBatch;
import rtx.kimiko.utils.render.render2d.line.LineBatch;
import rtx.kimiko.utils.render.render2d.outline.outline360.Outline360Batch;
import rtx.kimiko.utils.render.render2d.outline.outlinedefault.DefaultOutlineBatch;
import rtx.kimiko.utils.render.render2d.outline.outlineglass.GlassOutlineBatch;
import rtx.kimiko.utils.render.render2d.picker.PickerBatch;
import rtx.kimiko.utils.render.render2d.radialglass.RadialGlassBatch;
import rtx.kimiko.utils.render.render2d.rectangle.rectdefault.RectangleBatch;
import rtx.kimiko.utils.render.render2d.rectangle.recthalficon.HalfIconRectangleBatch;
import rtx.kimiko.utils.render.render2d.rectangle.recthalftone.HalftoneRectangleBatch;
import rtx.kimiko.utils.render.render2d.rectangle.tablepanel.TablePanelBatch;
import rtx.kimiko.utils.render.render2d.ripple.RippleBatch;
import rtx.kimiko.utils.render.render2d.sectormask.SectorMaskBatch;
import rtx.kimiko.utils.render.render2d.shape.ShapeBatch;
import rtx.kimiko.utils.render.render2d.shimmer.ShimmerBatch;
import rtx.kimiko.utils.render.render2d.veil.VeilBatch;
import rtx.kimiko.utils.render.render2d.zippy.ZippyBatch;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ#\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\f\u0010\rR.\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\u00048\u0006X\u0087\u0004r\u0002\b\u0006\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u0012\u0004\b\u0011\u0010\u0003\u001a\u0004\b\u0010\u0010\bR$\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\t\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\u00128\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/utils/render/core/frame/PrimitiveRegistry;", "", "<init>", "()V", "", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "Lkotlin/jvm/JvmStatic;", "allLayouts", "()Ljava/util/List;", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "Lrtx/kimiko/utils/render/core/batch/UiBatch;", "ownerOf", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)Lrtx/kimiko/utils/render/core/batch/UiBatch;", "all", "Ljava/util/List;", "getAll", "getAll$annotations", "", "byPipeline", "Ljava/util/Map;", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nPrimitiveRegistry.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PrimitiveRegistry.kt\nrtx/kimiko/utils/render/core/frame/PrimitiveRegistry\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,88:1\n1739#2:89\n1814#2,3:90\n1#3:93\n*S KotlinDebug\n*F\n+ 1 PrimitiveRegistry.kt\nrtx/kimiko/utils/render/core/frame/PrimitiveRegistry\n*L\n83#1:89\n83#1:90,3\n*E\n"})
public final class PrimitiveRegistry {
    @NotNull
    public static final PrimitiveRegistry INSTANCE = new PrimitiveRegistry();
    @NotNull
    private static final List<UiBatch<?>> all;
    @NotNull
    private static final Map<RenderPipeline, UiBatch<?>> byPipeline;

    private PrimitiveRegistry() {
    }

    @NotNull
    public static final List<UiBatch<?>> getAll() {
        return all;
    }

    @JvmStatic
    public static /* synthetic */ void getAll$annotations() {
    }

    /*
     * WARNING - void declaration
     */
    @JvmStatic
    @NotNull
    public static final List<UniformLayout> allLayouts() {
        ArrayList<UniformLayout> destination = new ArrayList<>(all.size());
        for (UiBatch<?> batch : all) {
            destination.add(batch.getLayout());
        }
        return destination;
    }

    @JvmStatic
    @Nullable
    public static final UiBatch<?> ownerOf(@Nullable RenderPipeline pipeline) {
        RenderPipeline renderPipeline = pipeline;
        return renderPipeline == null ? null : byPipeline.get(renderPipeline);
    }

    static {
        UiBatch<?>[] batches = new UiBatch<?>[]{ArcBatch.INSTANCE, ArcRectBatch.INSTANCE, CircleBatch.INSTANCE, LineBatch.INSTANCE, PickerBatch.INSTANCE, ZippyBatch.INSTANCE, TablePanelBatch.INSTANCE, HalftoneRectangleBatch.INSTANCE, DefaultOutlineBatch.INSTANCE, RippleBatch.INSTANCE, ShimmerBatch.INSTANCE, ImageBatch.INSTANCE, HalfIconRectangleBatch.INSTANCE, SectorMaskBatch.INSTANCE, GlassBatch.INSTANCE, ShapeBatch.INSTANCE, RadialGlassBatch.INSTANCE, GlassOutlineBatch.INSTANCE, VeilBatch.INSTANCE, RectangleBatch.INSTANCE, Outline360Batch.INSTANCE};
        all = List.of(batches);
        Map<RenderPipeline, UiBatch<?>> map = new LinkedHashMap<>();
        for (UiBatch<?> batch : all) {
            for (RenderPipeline pipeline : batch.getPipelines()) {
                if (map.containsKey(pipeline)) {
                    throw new IllegalStateException("pipeline already owned by another UiBatch: " + batch.getName());
                }
                map.put(pipeline, batch);
            }
        }
        byPipeline = Collections.unmodifiableMap(map);
    }
}

