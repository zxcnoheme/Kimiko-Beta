/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.BlendFunction
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Builder
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Snippet
 *  com.mojang.blaze3d.platform.DepthTestFunction
 *  com.mojang.blaze3d.vertex.VertexFormat$DrawMode
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gl.UniformType
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.util.Util
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.others.pipeline;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.util.Util;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.render.modules.post.trailecho.TrailEchoGlass;
import rtx.kimiko.utils.render.others.RenderCompatibility;
import rtx.kimiko.utils.render.others.RenderLayerFactory;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b6\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J'\u0010\r\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ#\u0010\u0012\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0007b\u0002\b\u0011\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR%\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00100\u001d8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010\u001cR%\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00100\u001d8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\"\u0010 R\u0014\u0010#\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010\u001cR%\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00100\u001d8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b$\u0010 R\u0014\u0010%\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010\u001cR%\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00100\u001d8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b&\u0010 R\u0014\u0010'\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010\u001cR%\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00100\u001d8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b(\u0010 R\u0014\u0010)\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b)\u0010\u001cR\u0019\u0010*\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010\u001cR\u0019\u0010-\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b-\u0010+R\u0014\u0010.\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b.\u0010\u001cR\u0019\u0010/\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b/\u0010+R\u0014\u00100\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u0010\u001cR\u0019\u00101\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b1\u0010+R\u0014\u00102\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u0010\u001cR\u0019\u00103\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b3\u0010+R\u0014\u00104\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u0010\u001cR\u0019\u00105\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b5\u0010+R\u0014\u00106\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b6\u0010\u001cR\u0019\u00107\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b7\u0010+R\u0014\u00108\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u0010\u001cR%\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00100\u001d8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b9\u0010 R\u0014\u0010:\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u0010\u001cR%\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00100\u001d8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b;\u0010 R\u0014\u0010<\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u0010\u001cR\u0019\u0010=\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b=\u0010+R\u0014\u0010>\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b>\u0010\u001cR\u0019\u0010?\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b?\u0010+R\u0014\u0010@\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b@\u0010\u001cR%\u0010A\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00100\u001d8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\bA\u0010 R\u0014\u0010B\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010\u001cR\u0019\u0010C\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\bC\u0010+R\u0014\u0010D\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bD\u0010\u001cR%\u0010E\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00100\u001d8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\bE\u0010 R\u0014\u0010F\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bF\u0010\u001cR\u0019\u0010G\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\bG\u0010+R\u0014\u0010H\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u0010\u001cR\u0019\u0010I\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\bI\u0010+R\u0014\u0010J\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010\u001cR\u0019\u0010K\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\bK\u0010+R\u0014\u0010L\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u0010\u001cR\u0019\u0010M\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\bM\u0010+R\u0014\u0010N\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bN\u0010\u001cR\u0019\u0010O\u001a\u00020\u00108\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\bO\u0010+R\u0014\u0010P\u001a\u00020\u00108\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bP\u0010+R\u0014\u0010Q\u001a\u00020\u00108\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bQ\u0010+R\u0014\u0010R\u001a\u00020\u00108\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bR\u0010+R\u0014\u0010S\u001a\u00020\u00108\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bS\u0010+\u00a8\u0006T"}, d2={"Lrtx/kimiko/utils/render/others/pipeline/ClientPipelines;", "", "<init>", "()V", "Lcom/mojang/blaze3d/pipeline/BlendFunction;", "worldBlend", "()Lcom/mojang/blaze3d/pipeline/BlendFunction;", "", "name", "", "glow", "depth", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "trailEnergyPipeline", "(Ljava/lang/String;ZZ)Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "throughWalls", "Lnet/minecraft/RenderLayer;", "Lkotlin/jvm/JvmStatic;", "trailEnergy", "(ZZ)Lnet/minecraft/RenderLayer;", "pipeline", "register", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline;)Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "TARGET_ESP_PIPELINE", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Ljava/util/function/Function;", "Lkotlin/jvm/JvmField;", "TARGET_ESP", "Ljava/util/function/Function;", "TARGET_GHOST_PIPELINE", "TARGET_GHOST", "TARGET_CHAIN_PIPELINE", "TARGET_CHAIN", "BLOOM_ESP_PIPELINE", "BLOOM_ESP", "MARKER_PIPELINE", "MARKER", "WORLD_PARTICLES_COLOR_PIPELINE", "WORLD_PARTICLES_COLOR", "Lnet/minecraft/RenderLayer;", "TARGET_CIRCLE_PIPELINE", "TARGET_CIRCLE_NODEPTH", "CHINA_HAT_PIPELINE", "CHINA_HAT", "BLOCK_OVERLAY_PIPELINE", "BLOCK_OVERLAY", "SKELETON_PIPELINE", "SKELETON", "TRAJECTORY_LINE_PIPELINE", "TRAJECTORY_LINE", "PROJECTILE_TRIS_PIPELINE", "PROJECTILE_TRIS", "WORLD_PARTICLES_GLOW_PIPELINE", "WORLD_PARTICLES_GLOW", "PORTAL_VIDEO_PIPELINE", "PORTAL_VIDEO", "PORTAL_WAIT_PIPELINE", "PORTAL_WAIT", "PORTAL_RIM_PIPELINE", "PORTAL_RIM", "PORTAL_SHARD_PIPELINE", "PORTAL_SHARD", "PORTAL_SHARD_RIM_PIPELINE", "PORTAL_SHARD_RIM", "WORLD_PARTICLES_GLOW_NODEPTH_PIPELINE", "WORLD_PARTICLES_GLOW_NODEPTH", "TRAIL_ECHO_GLASS_PIPELINE", "TRAIL_ECHO_GLASS", "TARGET_WARHEAD_GLASS_NODEPTH_PIPELINE", "TARGET_WARHEAD_GLASS_NODEPTH", "DASH_LINES_GLOW_PIPELINE", "DASH_LINES_GLOW", "LOOT_VIEW_PIPELINE", "LOOT_VIEW", "LOOT_VIEW_NODEPTH_PIPELINE", "LOOT_VIEW_NODEPTH", "TRAIL_ENERGY", "TRAIL_ENERGY_NODEPTH", "TRAIL_ENERGY_GLOW", "TRAIL_ENERGY_GLOW_NODEPTH", "rtx.kimiko:kimiko"})
public final class ClientPipelines {
    @NotNull
    public static final ClientPipelines INSTANCE = new ClientPipelines();
    @NotNull
    private static final RenderPipeline TARGET_ESP_PIPELINE;
    @JvmField
    @NotNull
    public static final Function<Identifier, RenderLayer> TARGET_ESP;
    @NotNull
    private static final RenderPipeline TARGET_GHOST_PIPELINE;
    @JvmField
    @NotNull
    public static final Function<Identifier, RenderLayer> TARGET_GHOST;
    @NotNull
    private static final RenderPipeline TARGET_CHAIN_PIPELINE;
    @JvmField
    @NotNull
    public static final Function<Identifier, RenderLayer> TARGET_CHAIN;
    @NotNull
    private static final RenderPipeline BLOOM_ESP_PIPELINE;
    @JvmField
    @NotNull
    public static final Function<Identifier, RenderLayer> BLOOM_ESP;
    @NotNull
    private static final RenderPipeline MARKER_PIPELINE;
    @JvmField
    @NotNull
    public static final Function<Identifier, RenderLayer> MARKER;
    @NotNull
    private static final RenderPipeline WORLD_PARTICLES_COLOR_PIPELINE;
    @JvmField
    @NotNull
    public static final RenderLayer WORLD_PARTICLES_COLOR;
    @NotNull
    private static final RenderPipeline TARGET_CIRCLE_PIPELINE;
    @JvmField
    @NotNull
    public static final RenderLayer TARGET_CIRCLE_NODEPTH;
    @NotNull
    private static final RenderPipeline CHINA_HAT_PIPELINE;
    @JvmField
    @NotNull
    public static final RenderLayer CHINA_HAT;
    @NotNull
    private static final RenderPipeline BLOCK_OVERLAY_PIPELINE;
    @JvmField
    @NotNull
    public static final RenderLayer BLOCK_OVERLAY;
    @NotNull
    private static final RenderPipeline SKELETON_PIPELINE;
    @JvmField
    @NotNull
    public static final RenderLayer SKELETON;
    @NotNull
    private static final RenderPipeline TRAJECTORY_LINE_PIPELINE;
    @JvmField
    @NotNull
    public static final RenderLayer TRAJECTORY_LINE;
    @NotNull
    private static final RenderPipeline PROJECTILE_TRIS_PIPELINE;
    @JvmField
    @NotNull
    public static final RenderLayer PROJECTILE_TRIS;
    @NotNull
    private static final RenderPipeline WORLD_PARTICLES_GLOW_PIPELINE;
    @JvmField
    @NotNull
    public static final Function<Identifier, RenderLayer> WORLD_PARTICLES_GLOW;
    @NotNull
    private static final RenderPipeline PORTAL_VIDEO_PIPELINE;
    @JvmField
    @NotNull
    public static final Function<Identifier, RenderLayer> PORTAL_VIDEO;
    @NotNull
    private static final RenderPipeline PORTAL_WAIT_PIPELINE;
    @JvmField
    @NotNull
    public static final RenderLayer PORTAL_WAIT;
    @NotNull
    private static final RenderPipeline PORTAL_RIM_PIPELINE;
    @JvmField
    @NotNull
    public static final RenderLayer PORTAL_RIM;
    @NotNull
    private static final RenderPipeline PORTAL_SHARD_PIPELINE;
    @JvmField
    @NotNull
    public static final Function<Identifier, RenderLayer> PORTAL_SHARD;
    @NotNull
    private static final RenderPipeline PORTAL_SHARD_RIM_PIPELINE;
    @JvmField
    @NotNull
    public static final RenderLayer PORTAL_SHARD_RIM;
    @NotNull
    private static final RenderPipeline WORLD_PARTICLES_GLOW_NODEPTH_PIPELINE;
    @JvmField
    @NotNull
    public static final Function<Identifier, RenderLayer> WORLD_PARTICLES_GLOW_NODEPTH;
    @NotNull
    private static final RenderPipeline TRAIL_ECHO_GLASS_PIPELINE;
    @JvmField
    @NotNull
    public static final RenderLayer TRAIL_ECHO_GLASS;
    @NotNull
    private static final RenderPipeline TARGET_WARHEAD_GLASS_NODEPTH_PIPELINE;
    @JvmField
    @NotNull
    public static final RenderLayer TARGET_WARHEAD_GLASS_NODEPTH;
    @NotNull
    private static final RenderPipeline DASH_LINES_GLOW_PIPELINE;
    @JvmField
    @NotNull
    public static final RenderLayer DASH_LINES_GLOW;
    @NotNull
    private static final RenderPipeline LOOT_VIEW_PIPELINE;
    @JvmField
    @NotNull
    public static final RenderLayer LOOT_VIEW;
    @NotNull
    private static final RenderPipeline LOOT_VIEW_NODEPTH_PIPELINE;
    @JvmField
    @NotNull
    public static final RenderLayer LOOT_VIEW_NODEPTH;
    @NotNull
    private static final RenderLayer TRAIL_ENERGY;
    @NotNull
    private static final RenderLayer TRAIL_ENERGY_NODEPTH;
    @NotNull
    private static final RenderLayer TRAIL_ENERGY_GLOW;
    @NotNull
    private static final RenderLayer TRAIL_ENERGY_GLOW_NODEPTH;

    private ClientPipelines() {
    }

    private final BlendFunction worldBlend() {
        BlendFunction blendFunction;
        if (RenderCompatibility.useSafeWorldEffects()) {
            BlendFunction blendFunction2 = BlendFunction.TRANSLUCENT;
            blendFunction = blendFunction2;
            Intrinsics.checkNotNullExpressionValue((Object)blendFunction2, (String)"TRANSLUCENT");
        } else {
            BlendFunction blendFunction3 = BlendFunction.LIGHTNING;
            blendFunction = blendFunction3;
            Intrinsics.checkNotNullExpressionValue((Object)blendFunction3, (String)"LIGHTNING");
        }
        return blendFunction;
    }

    private final RenderPipeline trailEnergyPipeline(String name, boolean glow, boolean depth) {
        BlendFunction blendFunction;
        RenderPipeline.Builder builder = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(this.id("pipeline/" + name)).withVertexShader("core/position_color").withFragmentShader("core/position_color").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER);
        if (glow) {
            blendFunction = this.worldBlend();
        } else {
            BlendFunction blendFunction2 = BlendFunction.TRANSLUCENT;
            blendFunction = blendFunction2;
            Intrinsics.checkNotNullExpressionValue((Object)blendFunction2, (String)"TRANSLUCENT");
        }
        RenderPipeline renderPipeline = builder.withBlend(blendFunction).withDepthTestFunction(depth ? DepthTestFunction.LEQUAL_DEPTH_TEST : DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline, (String)"build(...)");
        return this.register(renderPipeline);
    }

    @JvmStatic
    @NotNull
    public static final RenderLayer trailEnergy(boolean glow, boolean throughWalls) {
        return glow && throughWalls ? TRAIL_ENERGY_GLOW_NODEPTH : (glow ? TRAIL_ENERGY_GLOW : (throughWalls ? TRAIL_ENERGY_NODEPTH : TRAIL_ENERGY));
    }

    private final RenderPipeline register(RenderPipeline pipeline) {
        RenderPipeline renderPipeline = RenderPipelines.register((RenderPipeline)pipeline);
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline, (String)"register(...)");
        return renderPipeline;
    }

    private final Identifier id(String path) {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        return identifier2;
    }

    private static final RenderLayer TARGET_ESP$lambda$0(Identifier texture) {
        Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
        return RenderLayerFactory.create("kimiko_target_esp", 1536, TARGET_ESP_PIPELINE, texture);
    }

    private static final RenderLayer TARGET_GHOST$lambda$0(Identifier texture) {
        Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
        return RenderLayerFactory.create("kimiko_target_ghost", 1536, TARGET_GHOST_PIPELINE, texture);
    }

    private static final RenderLayer TARGET_CHAIN$lambda$0(Identifier texture) {
        Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
        return RenderLayerFactory.create("kimiko_target_chain", 1536, TARGET_CHAIN_PIPELINE, texture);
    }

    private static final RenderLayer BLOOM_ESP$lambda$0(Identifier texture) {
        Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
        return RenderLayerFactory.create("kimiko_bloom_esp", 2048, BLOOM_ESP_PIPELINE, texture);
    }

    private static final RenderLayer MARKER$lambda$0(Identifier texture) {
        Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
        return RenderLayerFactory.create("kimiko_marker", 1536, MARKER_PIPELINE, texture);
    }

    private static final RenderLayer WORLD_PARTICLES_GLOW$lambda$0(Identifier texture) {
        Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
        return RenderLayerFactory.create("kimiko_world_particles_glow", 2048, WORLD_PARTICLES_GLOW_PIPELINE, texture);
    }

    private static final RenderLayer PORTAL_VIDEO$lambda$0(Identifier texture) {
        Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
        return RenderLayerFactory.create("kimiko_portal_video", 2048, PORTAL_VIDEO_PIPELINE, texture);
    }

    private static final RenderLayer PORTAL_SHARD$lambda$0(Identifier texture) {
        Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
        return RenderLayerFactory.create("kimiko_portal_shard", 8192, PORTAL_SHARD_PIPELINE, texture);
    }

    private static final RenderLayer WORLD_PARTICLES_GLOW_NODEPTH$lambda$0(Identifier texture) {
        Intrinsics.checkNotNullParameter((Object)texture, (String)"texture");
        return RenderLayerFactory.create("kimiko_world_particles_glow_nodepth", 2048, WORLD_PARTICLES_GLOW_NODEPTH_PIPELINE, texture);
    }

    static {
        RenderPipeline renderPipeline = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/target_esp")).withVertexShader("core/position_tex_color").withFragmentShader("core/position_tex_color").withSampler("Sampler0").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(INSTANCE.worldBlend()).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline, (String)"build(...)");
        TARGET_ESP_PIPELINE = INSTANCE.register(renderPipeline);
        Function function = Util.memoize(ClientPipelines::TARGET_ESP$lambda$0);
        Intrinsics.checkNotNullExpressionValue((Object)function, (String)"memoize(...)");
        TARGET_ESP = function;
        RenderPipeline renderPipeline2 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/target_ghost")).withVertexShader("core/position_tex_color").withFragmentShader("core/position_tex_color").withSampler("Sampler0").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(INSTANCE.worldBlend()).withDepthTestFunction(DepthTestFunction.LESS_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline2, (String)"build(...)");
        TARGET_GHOST_PIPELINE = INSTANCE.register(renderPipeline2);
        Function function2 = Util.memoize(ClientPipelines::TARGET_GHOST$lambda$0);
        Intrinsics.checkNotNullExpressionValue((Object)function2, (String)"memoize(...)");
        TARGET_GHOST = function2;
        RenderPipeline renderPipeline3 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/target_chain")).withVertexShader("core/position_tex_color").withFragmentShader("core/position_tex_color").withSampler("Sampler0").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(INSTANCE.worldBlend()).withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline3, (String)"build(...)");
        TARGET_CHAIN_PIPELINE = INSTANCE.register(renderPipeline3);
        Function function3 = Util.memoize(ClientPipelines::TARGET_CHAIN$lambda$0);
        Intrinsics.checkNotNullExpressionValue((Object)function3, (String)"memoize(...)");
        TARGET_CHAIN = function3;
        RenderPipeline renderPipeline4 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/bloom_esp")).withVertexShader("core/position_tex_color").withFragmentShader("core/position_tex_color").withSampler("Sampler0").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(INSTANCE.worldBlend()).withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline4, (String)"build(...)");
        BLOOM_ESP_PIPELINE = INSTANCE.register(renderPipeline4);
        Function function4 = Util.memoize(ClientPipelines::BLOOM_ESP$lambda$0);
        Intrinsics.checkNotNullExpressionValue((Object)function4, (String)"memoize(...)");
        BLOOM_ESP = function4;
        RenderPipeline renderPipeline5 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/marker")).withVertexShader("core/position_tex_color").withFragmentShader("core/position_tex_color").withSampler("Sampler0").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withCull(false).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline5, (String)"build(...)");
        MARKER_PIPELINE = INSTANCE.register(renderPipeline5);
        Function function5 = Util.memoize(ClientPipelines::MARKER$lambda$0);
        Intrinsics.checkNotNullExpressionValue((Object)function5, (String)"memoize(...)");
        MARKER = function5;
        RenderPipeline renderPipeline6 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/world_particles_color")).withVertexShader("core/position_color").withFragmentShader("core/position_color").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(INSTANCE.worldBlend()).withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline6, (String)"build(...)");
        WORLD_PARTICLES_COLOR_PIPELINE = INSTANCE.register(renderPipeline6);
        WORLD_PARTICLES_COLOR = RenderLayerFactory.create("kimiko_world_particles_color", 2048, WORLD_PARTICLES_COLOR_PIPELINE);
        RenderPipeline renderPipeline7 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/target_circle")).withVertexShader("core/position_color").withFragmentShader("core/position_color").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(INSTANCE.worldBlend()).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline7, (String)"build(...)");
        TARGET_CIRCLE_PIPELINE = INSTANCE.register(renderPipeline7);
        TARGET_CIRCLE_NODEPTH = RenderLayerFactory.create("kimiko_target_circle", 2048, TARGET_CIRCLE_PIPELINE);
        RenderPipeline renderPipeline8 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/china_hat")).withVertexShader("core/position_color").withFragmentShader("core/position_color").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline8, (String)"build(...)");
        CHINA_HAT_PIPELINE = INSTANCE.register(renderPipeline8);
        CHINA_HAT = RenderLayerFactory.create("kimiko_china_hat", 4096, CHINA_HAT_PIPELINE);
        RenderPipeline renderPipeline9 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/block_overlay")).withVertexShader("core/position_color").withFragmentShader("core/position_color").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(INSTANCE.worldBlend()).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline9, (String)"build(...)");
        BLOCK_OVERLAY_PIPELINE = INSTANCE.register(renderPipeline9);
        BLOCK_OVERLAY = RenderLayerFactory.create("kimiko_block_overlay", 16384, BLOCK_OVERLAY_PIPELINE);
        RenderPipeline renderPipeline10 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/skeleton")).withVertexShader("core/position_color").withFragmentShader("core/position_color").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(INSTANCE.worldBlend()).withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline10, (String)"build(...)");
        SKELETON_PIPELINE = INSTANCE.register(renderPipeline10);
        SKELETON = RenderLayerFactory.create("kimiko_skeleton", 2048, SKELETON_PIPELINE);
        RenderPipeline renderPipeline11 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/trajectory_line")).withVertexShader("core/position_color").withFragmentShader("core/position_color").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(INSTANCE.worldBlend()).withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.DEBUG_LINES).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline11, (String)"build(...)");
        TRAJECTORY_LINE_PIPELINE = INSTANCE.register(renderPipeline11);
        TRAJECTORY_LINE = RenderLayerFactory.create("kimiko_trajectory_line", 8192, TRAJECTORY_LINE_PIPELINE);
        RenderPipeline renderPipeline12 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/projectile_tris")).withVertexShader("core/position_color").withFragmentShader("core/position_color").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.TRIANGLES).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline12, (String)"build(...)");
        PROJECTILE_TRIS_PIPELINE = INSTANCE.register(renderPipeline12);
        PROJECTILE_TRIS = RenderLayerFactory.create("kimiko_projectile_tris", 8192, PROJECTILE_TRIS_PIPELINE);
        RenderPipeline renderPipeline13 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/world_particles_glow")).withVertexShader("core/position_tex_color").withFragmentShader("core/position_tex_color").withSampler("Sampler0").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(BlendFunction.LIGHTNING).withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline13, (String)"build(...)");
        WORLD_PARTICLES_GLOW_PIPELINE = INSTANCE.register(renderPipeline13);
        Function function6 = Util.memoize(ClientPipelines::WORLD_PARTICLES_GLOW$lambda$0);
        Intrinsics.checkNotNullExpressionValue((Object)function6, (String)"memoize(...)");
        WORLD_PARTICLES_GLOW = function6;
        RenderPipeline renderPipeline14 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/portal_video")).withVertexShader(INSTANCE.id("core/portal_live")).withFragmentShader(INSTANCE.id("core/portal_live_video")).withSampler("Sampler0").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline14, (String)"build(...)");
        PORTAL_VIDEO_PIPELINE = INSTANCE.register(renderPipeline14);
        Function function7 = Util.memoize(ClientPipelines::PORTAL_VIDEO$lambda$0);
        Intrinsics.checkNotNullExpressionValue((Object)function7, (String)"memoize(...)");
        PORTAL_VIDEO = function7;
        RenderPipeline renderPipeline15 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/portal_wait")).withVertexShader(INSTANCE.id("core/portal_live")).withFragmentShader(INSTANCE.id("core/portal_live_wait")).withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline15, (String)"build(...)");
        PORTAL_WAIT_PIPELINE = INSTANCE.register(renderPipeline15);
        PORTAL_WAIT = RenderLayerFactory.create("kimiko_portal_wait", 256, PORTAL_WAIT_PIPELINE);
        RenderPipeline renderPipeline16 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/portal_rim")).withVertexShader(INSTANCE.id("core/portal_live")).withFragmentShader(INSTANCE.id("core/portal_live_rim")).withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(BlendFunction.LIGHTNING).withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline16, (String)"build(...)");
        PORTAL_RIM_PIPELINE = INSTANCE.register(renderPipeline16);
        PORTAL_RIM = RenderLayerFactory.create("kimiko_portal_rim", 8192, PORTAL_RIM_PIPELINE);
        RenderPipeline renderPipeline17 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/portal_shard")).withVertexShader(INSTANCE.id("core/portal_live")).withFragmentShader(INSTANCE.id("core/portal_live_shard")).withSampler("Sampler0").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.TRIANGLES).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline17, (String)"build(...)");
        PORTAL_SHARD_PIPELINE = INSTANCE.register(renderPipeline17);
        Function function8 = Util.memoize(ClientPipelines::PORTAL_SHARD$lambda$0);
        Intrinsics.checkNotNullExpressionValue((Object)function8, (String)"memoize(...)");
        PORTAL_SHARD = function8;
        RenderPipeline renderPipeline18 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/portal_shard_rim")).withVertexShader(INSTANCE.id("core/portal_live")).withFragmentShader(INSTANCE.id("core/portal_live_shard_rim")).withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(BlendFunction.LIGHTNING).withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.TRIANGLES).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline18, (String)"build(...)");
        PORTAL_SHARD_RIM_PIPELINE = INSTANCE.register(renderPipeline18);
        PORTAL_SHARD_RIM = RenderLayerFactory.create("kimiko_portal_shard_rim", 8192, PORTAL_SHARD_RIM_PIPELINE);
        RenderPipeline renderPipeline19 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/world_particles_glow_nodepth")).withVertexShader("core/position_tex_color").withFragmentShader("core/position_tex_color").withSampler("Sampler0").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(BlendFunction.LIGHTNING).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline19, (String)"build(...)");
        WORLD_PARTICLES_GLOW_NODEPTH_PIPELINE = INSTANCE.register(renderPipeline19);
        Function function9 = Util.memoize(ClientPipelines::WORLD_PARTICLES_GLOW_NODEPTH$lambda$0);
        Intrinsics.checkNotNullExpressionValue((Object)function9, (String)"memoize(...)");
        WORLD_PARTICLES_GLOW_NODEPTH = function9;
        RenderPipeline renderPipeline20 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/trail_echo_glass")).withVertexShader(INSTANCE.id("core/trail_echo_glass")).withFragmentShader(INSTANCE.id("core/trail_echo_glass")).withSampler("Sampler0").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline20, (String)"build(...)");
        TRAIL_ECHO_GLASS_PIPELINE = INSTANCE.register(renderPipeline20);
        TRAIL_ECHO_GLASS = RenderLayerFactory.create("kimiko_trail_echo_glass", 16384, TRAIL_ECHO_GLASS_PIPELINE, TrailEchoGlass.TEXTURE_ID);
        RenderPipeline renderPipeline21 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/target_warhead_glass_nodepth")).withVertexShader(INSTANCE.id("core/trail_echo_glass")).withFragmentShader(INSTANCE.id("core/trail_echo_glass")).withSampler("Sampler0").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(BlendFunction.TRANSLUCENT).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline21, (String)"build(...)");
        TARGET_WARHEAD_GLASS_NODEPTH_PIPELINE = INSTANCE.register(renderPipeline21);
        TARGET_WARHEAD_GLASS_NODEPTH = RenderLayerFactory.create("kimiko_target_warhead_glass_nodepth", 16384, TARGET_WARHEAD_GLASS_NODEPTH_PIPELINE, TrailEchoGlass.TEXTURE_ID);
        RenderPipeline renderPipeline22 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/dash_lines_glow")).withVertexShader("core/position_color").withFragmentShader("core/position_color").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(INSTANCE.worldBlend()).withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline22, (String)"build(...)");
        DASH_LINES_GLOW_PIPELINE = INSTANCE.register(renderPipeline22);
        DASH_LINES_GLOW = RenderLayerFactory.create("kimiko_dash_lines_glow", 8192, DASH_LINES_GLOW_PIPELINE);
        RenderPipeline renderPipeline23 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/loot_view")).withVertexShader("core/position_color").withFragmentShader("core/position_color").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(INSTANCE.worldBlend()).withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline23, (String)"build(...)");
        LOOT_VIEW_PIPELINE = INSTANCE.register(renderPipeline23);
        LOOT_VIEW = RenderLayerFactory.create("kimiko_loot_view", 16384, LOOT_VIEW_PIPELINE);
        RenderPipeline renderPipeline24 = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(INSTANCE.id("pipeline/loot_view_nodepth")).withVertexShader("core/position_color").withFragmentShader("core/position_color").withUniform("DynamicTransforms", UniformType.UNIFORM_BUFFER).withUniform("Projection", UniformType.UNIFORM_BUFFER).withBlend(INSTANCE.worldBlend()).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).withVertexFormat(VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.QUADS).build();
        Intrinsics.checkNotNullExpressionValue((Object)renderPipeline24, (String)"build(...)");
        LOOT_VIEW_NODEPTH_PIPELINE = INSTANCE.register(renderPipeline24);
        LOOT_VIEW_NODEPTH = RenderLayerFactory.create("kimiko_loot_view_nodepth", 16384, LOOT_VIEW_NODEPTH_PIPELINE);
        TRAIL_ENERGY = RenderLayerFactory.create("kimiko_trail_energy", 65536, INSTANCE.trailEnergyPipeline("trail_energy", false, true));
        TRAIL_ENERGY_NODEPTH = RenderLayerFactory.create("kimiko_trail_energy_nodepth", 65536, INSTANCE.trailEnergyPipeline("trail_energy_nodepth", false, false));
        TRAIL_ENERGY_GLOW = RenderLayerFactory.create("kimiko_trail_energy_glow", 65536, INSTANCE.trailEnergyPipeline("trail_energy_glow", true, true));
        TRAIL_ENERGY_GLOW_NODEPTH = RenderLayerFactory.create("kimiko_trail_energy_glow_nodepth", 65536, INSTANCE.trailEnergyPipeline("trail_energy_glow_nodepth", true, false));
    }
}

