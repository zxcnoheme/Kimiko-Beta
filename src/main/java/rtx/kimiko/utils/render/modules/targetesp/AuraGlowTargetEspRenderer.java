/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.RenderLayer
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Quaternionf
 *  org.joml.Vector3f
 */
package rtx.kimiko.utils.render.modules.targetesp;

import java.util.function.Function;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import rtx.kimiko.Kimiko;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.modules.targetesp.TargetEspColorProvider;
import rtx.kimiko.utils.render.modules.targetesp.TargetEspRenderContext;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0013\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J3\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJo\u0010 \u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b \u0010!J#\u0010$\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\"H\u0007b\u0002\b\r\u00a2\u0006\u0004\b$\u0010%J#\u0010)\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020(0&2\u0006\u0010#\u001a\u00020\"H\u0002\u00a2\u0006\u0004\b)\u0010*J\u0017\u0010-\u001a\u00020'2\u0006\u0010,\u001a\u00020+H\u0002\u00a2\u0006\u0004\b-\u0010.R\u0014\u0010/\u001a\u00020'8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u00100R\u0014\u00101\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b1\u00102R\u0014\u00103\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b3\u00102R\u0014\u00104\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u00102R\u0014\u00105\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b5\u00102R\u0014\u00106\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b6\u00107R\u0014\u00108\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b8\u00102R\u0014\u00109\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b9\u00107R\u0014\u0010:\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b:\u00107R\u0014\u0010;\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b;\u00107R\u0014\u0010<\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b<\u00107R\u0014\u0010=\u001a\u00020\u00148\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u00102\u00a8\u0006>"}, d2={"Lrtx/kimiko/utils/render/modules/targetesp/AuraGlowTargetEspRenderer;", "", "<init>", "()V", "Lnet/minecraft/MatrixStack;", "stack", "Lnet/minecraft/VertexConsumerProvider$Immediate;", "provider", "Lrtx/kimiko/utils/render/modules/targetesp/TargetEspRenderContext;", "context", "Lrtx/kimiko/utils/render/modules/targetesp/TargetEspColorProvider;", "colors", "", "Lkotlin/jvm/JvmStatic;", "render", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/VertexConsumerProvider$Immediate;Lrtx/kimiko/utils/render/modules/targetesp/TargetEspRenderContext;Lrtx/kimiko/utils/render/modules/targetesp/TargetEspColorProvider;)V", "Lnet/minecraft/VertexConsumer;", "consumer", "Lnet/minecraft/MatrixStack$Entry;", "pose", "", "cx", "cy", "cz", "rx", "ry", "rz", "ux", "uy", "uz", "", "color", "quad", "(Lnet/minecraft/VertexConsumer;Lnet/minecraft/MatrixStack$Entry;FFFFFFFFFI)V", "", "throughWalls", "endBatch", "(Lnet/minecraft/VertexConsumerProvider$Immediate;Z)V", "Ljava/util/function/Function;", "Lnet/minecraft/Identifier;", "Lnet/minecraft/RenderLayer;", "layer", "(Z)Ljava/util/function/Function;", "", "path", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "GLOW_TEXTURE", "Lnet/minecraft/Identifier;", "BASE_WIDTH", "F", "BASE_HEIGHT", "SIZE", "RADIUS", "STACK", "I", "RING_GAP", "MIN_RING", "MAX_RING", "MIN_STACK", "MAX_STACK", "BASELINE_RING", "rtx.kimiko:kimiko"})
public final class AuraGlowTargetEspRenderer {
    @NotNull
    public static final AuraGlowTargetEspRenderer INSTANCE = new AuraGlowTargetEspRenderer();
    @NotNull
    private static final Identifier GLOW_TEXTURE = INSTANCE.id("textures/particle/glow.png");
    private static final float BASE_WIDTH = 0.6f;
    private static final float BASE_HEIGHT = 1.8f;
    private static final float SIZE = 0.5f;
    private static final float RADIUS = 0.75f;
    private static final int STACK = 10;
    private static final float RING_GAP = 0.157f;
    private static final int MIN_RING = 20;
    private static final int MAX_RING = 180;
    private static final int MIN_STACK = 4;
    private static final int MAX_STACK = 30;
    private static final float BASELINE_RING = 60.03043f;

    private AuraGlowTargetEspRenderer() {
    }

    @JvmStatic
    public static final void render(@NotNull MatrixStack stack, @NotNull VertexConsumerProvider.Immediate provider, @NotNull TargetEspRenderContext context, @NotNull TargetEspColorProvider colors) {
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)provider, (String)"provider");
        Intrinsics.checkNotNullParameter((Object)context, (String)"context");
        Intrinsics.checkNotNullParameter((Object)colors, (String)"colors");
        float fade = context.alpha();
        if (fade <= 0.02f) {
            return;
        }
        VertexConsumer vertexConsumer2 = provider.getBuffer(INSTANCE.layer(context.throughWalls()).apply(GLOW_TEXTURE));
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer consumer = vertexConsumer2;
        float bbWidth = Math.max(0.1f, context.target().getWidth());
        float bbHeight = Math.max(0.1f, context.target().getHeight());
        float widthScale = MathHelper.clamp((float)(bbWidth / 0.6f), (float)0.45f, (float)4.0f);
        float heightScale = MathHelper.clamp((float)(bbHeight / 1.8f), (float)0.45f, (float)3.5f);
        float radius = 0.75f * widthScale;
        float size = 0.5f * (float)Math.sqrt(widthScale);
        float circumference = (float)(Math.PI * 2 * (double)radius);
        int ringCount = MathHelper.clamp((int)Math.round(circumference / (size * 0.157f)), (int)20, (int)180);
        float ringStep = 360.0f / (float)ringCount;
        float layerStep = size * 0.2f;
        float trailLength = bbHeight * 0.55f;
        int stackCount = MathHelper.clamp((int)Math.round(trailLength / layerStep), (int)4, (int)30);
        float baselineSprites = BASELINE_RING * (float)9;
        float actualSprites = ringCount * Math.max(1, stackCount - 1);
        float densityComp = MathHelper.clamp((float)((float)Math.sqrt(baselineSprites / actualSprites)), (float)1.0f, (float)3.0f);
        float spriteBrightness = context.brightness() * densityComp;
        Quaternionf quaternionf = MinecraftClient.getInstance().gameRenderer.getCamera().getRotation();
        Intrinsics.checkNotNullExpressionValue((Object)quaternionf, (String)"rotation(...)");
        Quaternionf cameraRotation = quaternionf;
        Vector3f right = cameraRotation.transform(new Vector3f(1.0f, 0.0f, 0.0f));
        Vector3f up = cameraRotation.transform(new Vector3f(0.0f, context.circleHeight(), 0.0f));
        float half = size * 0.5f;
        float rx = right.x() * half;
        float ry = right.y() * half;
        float rz = right.z() * half;
        float ux = up.x() * half;
        float uy = up.y() * half;
        float uz = up.z() * half;
        float sweep01 = (float)((Math.sin((double)context.frameTimeMs() / 500.0) + 1.0) / 2.0);
        float angleY = sweep01 * bbHeight;
        double stretchMod = -0.5 + Math.cos(sweep01 * 1.8f);
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        for (int s = 0; s < ringCount; ++s) {
            float angle = (float)s * ringStep;
            double radians = Math.toRadians(angle);
            float cx = (float)(Math.cos(radians) * (double)radius);
            float cz = (float)(Math.sin(radians) * (double)radius);
            int colorIndex = (int)angle;
            for (int j = 1; j < stackCount; ++j) {
                int color = colors.color(colorIndex, fade * ((float)j / (float)stackCount) * spriteBrightness);
                if (ColorEngine.alpha(color) <= 1) continue;
                float cy = (float)((double)angleY + (double)((float)j * layerStep) * stretchMod);
                INSTANCE.quad(consumer, pose, cx, cy, cz, rx, ry, rz, ux, uy, uz, color);
            }
        }
    }

    private final void quad(VertexConsumer consumer, MatrixStack.Entry pose, float cx, float cy, float cz, float rx, float ry, float rz, float ux, float uy, float uz, int color) {
        consumer.vertex(pose, cx - rx - ux, cy - ry - uy, cz - rz - uz).texture(0.0f, 0.0f).color(color);
        consumer.vertex(pose, cx - rx + ux, cy - ry + uy, cz - rz + uz).texture(0.0f, 1.0f).color(color);
        consumer.vertex(pose, cx + rx + ux, cy + ry + uy, cz + rz + uz).texture(1.0f, 1.0f).color(color);
        consumer.vertex(pose, cx + rx - ux, cy + ry - uy, cz + rz - uz).texture(1.0f, 0.0f).color(color);
    }

    @JvmStatic
    public static final void endBatch(@NotNull VertexConsumerProvider.Immediate provider, boolean throughWalls) {
        Intrinsics.checkNotNullParameter((Object)provider, (String)"provider");
        provider.draw(INSTANCE.layer(throughWalls).apply(GLOW_TEXTURE));
    }

    private final Function<Identifier, RenderLayer> layer(boolean throughWalls) {
        return throughWalls ? ClientPipelines.TARGET_ESP : ClientPipelines.TARGET_CHAIN;
    }

    private final Identifier id(String path) {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        return identifier2;
    }
}

