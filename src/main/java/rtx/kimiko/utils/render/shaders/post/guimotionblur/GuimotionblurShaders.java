/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.shaders.post.guimotionblur;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000fJ\u000f\u0010\u0012\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u000fJ\u000f\u0010\u0013\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u000fJ\u000f\u0010\u0014\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u000fJ\u000f\u0010\u0015\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u000fJ\u000f\u0010\u0016\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u000fJ\u000f\u0010\u0017\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u000f\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/utils/render/shaders/post/guimotionblur/GuimotionblurShaders;", "", "<init>", "()V", "", "", "sources", "", "Lkotlin/jvm/JvmStatic;", "register", "(Ljava/util/Map;)V", "key", "source", "(Ljava/lang/String;)Ljava/lang/String;", "s0", "()Ljava/lang/String;", "s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "rtx.kimiko:kimiko"})
public final class GuimotionblurShaders {
    @NotNull
    public static final GuimotionblurShaders INSTANCE = new GuimotionblurShaders();

    private GuimotionblurShaders() {
    }

    @JvmStatic
    public static final void register(@NotNull Map<String, String> sources) {
        Intrinsics.checkNotNullParameter(sources, (String)"sources");
        sources.put("post/guimotionblur/composite.glsl", INSTANCE.s0());
        sources.put("post/guimotionblur/composite.fsh", INSTANCE.s1());
        sources.put("post/guimotionblur/fullscreen.glsl", INSTANCE.s2());
        sources.put("post/guimotionblur/fullscreen.vsh", INSTANCE.s3());
        sources.put("post/guimotionblur/gaussian.glsl", INSTANCE.s4());
        sources.put("post/guimotionblur/gaussian.fsh", INSTANCE.s5());
        sources.put("post/guimotionblur/mask.glsl", INSTANCE.s6());
        sources.put("post/guimotionblur/mask.vsh", INSTANCE.s7());
        sources.put("post/guimotionblur/mask.fsh", INSTANCE.s8());
    }

    @JvmStatic
    @NotNull
    public static final String source(@NotNull String key) {
        Intrinsics.checkNotNullParameter((Object)key, (String)"key");
        return switch (key) {
            case "post/guimotionblur/composite.glsl" -> INSTANCE.s0();
            case "post/guimotionblur/composite.fsh" -> INSTANCE.s1();
            case "post/guimotionblur/fullscreen.glsl" -> INSTANCE.s2();
            case "post/guimotionblur/fullscreen.vsh" -> INSTANCE.s3();
            case "post/guimotionblur/gaussian.glsl" -> INSTANCE.s4();
            case "post/guimotionblur/gaussian.fsh" -> INSTANCE.s5();
            case "post/guimotionblur/mask.glsl" -> INSTANCE.s6();
            case "post/guimotionblur/mask.vsh" -> INSTANCE.s7();
            case "post/guimotionblur/mask.fsh" -> INSTANCE.s8();
            default -> throw new IllegalArgumentException(key);
        };
    }

    private final String s0() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D MotionScene;", "uniform sampler2D MotionBackground;", "uniform sampler2D MotionBlurred;", "uniform sampler2D MotionBackgroundBlurred;", "uniform sampler2D MotionMask;", "", "layout(std140) uniform MotionCompositeParams {", "    vec4 CompositeData;", "    vec4 GuiRectData;", "    vec4 TargetData;", "    vec4 SourceRectData;", "    vec4 TransformData;", "};", "", "in vec2 TexCoord;", "out vec4 OutColor;", "", "float roundedRectMask(vec2 point, vec4 rect, float radius, float feather) {", "    vec2 halfSize = max(rect.zw * 0.5, vec2(0.0));", "    vec2 center = rect.xy + halfSize;", "    float safeRadius = min(radius, min(halfSize.x, halfSize.y));", "    vec2 q = abs(point - center) - halfSize + vec2(safeRadius);", "    float distance = length(max(q, vec2(0.0))) + min(max(q.x, q.y), 0.0) - safeRadius;", "    return 1.0 - smoothstep(-feather, feather, distance);", "}", "", "void main() {", "    float opacity = clamp(CompositeData.x, 0.0, 1.0);", "    float bloom = clamp(CompositeData.y, 0.0, 0.12);", "    float layerBoost = max(CompositeData.z, 1.0);", "    float radius = max(CompositeData.w, 0.0);", "    vec2 targetSize = max(TargetData.xy, vec2(1.0));", "    float feather = max(TargetData.z, 0.5);", "    float scale = max(TargetData.w, 0.0001);", "    vec4 scene = texture(MotionScene, TexCoord);", "    vec4 blurred = texture(MotionBlurred, TexCoord);", "    vec2 fragPx = vec2(TexCoord.x * targetSize.x, (1.0 - TexCoord.y) * targetSize.y);", "", "    if (abs(scale - 1.0) <= 0.0005) {", "", "        vec4 maskSample = clamp(texture(MotionMask, TexCoord), vec4(0.0), vec4(1.0));", "        vec4 background = texture(MotionBackground, TexCoord);", "        vec4 blurredBackground = texture(MotionBackgroundBlurred, TexCoord);", "        vec3 layer = scene.rgb - background.rgb;", "        vec3 blurredLayer = blurred.rgb - blurredBackground.rgb;", "        float layerAlpha = scene.a - background.a;", "        float blurredLayerAlpha = blurred.a - blurredBackground.a;", "", "        float amount = opacity * maskSample.a;", "        vec3 boostedBlur = blurredLayer * mix(1.0, layerBoost, amount);", "        vec3 card = mix(layer, boostedBlur, maskSample.g);", "        vec3 composed = background.rgb + layer * (1.0 - amount) + card * amount * maskSample.r;", "        float cardAlpha = mix(layerAlpha, blurredLayerAlpha, maskSample.g);", "        float composedAlpha = background.a", "                + layerAlpha * (1.0 - amount)", "                + cardAlpha * amount * maskSample.r;", "        OutColor = vec4(clamp(composed, 0.0, 1.0), clamp(composedAlpha, 0.0, 1.0));", "        return;", "    }", "", "    vec4 background = texture(MotionBackground, TexCoord);", "    vec4 blurredBackground = texture(MotionBackgroundBlurred, TexCoord);", "    vec2 sourcePx = TransformData.xy + (fragPx - TransformData.xy) / scale;", "    vec2 sourceUv = clamp(vec2(sourcePx.x / targetSize.x, 1.0 - sourcePx.y / targetSize.y), vec2(0.0), vec2(1.0));", "    vec4 sourceScene = texture(MotionScene, sourceUv);", "    vec4 sourceBackground = texture(MotionBackground, sourceUv);", "    vec4 sourceBlurred = texture(MotionBlurred, sourceUv);", "    vec4 sourceBlurredBackground = texture(MotionBackgroundBlurred, sourceUv);", "", "    vec3 layer = sourceScene.rgb - sourceBackground.rgb;", "    vec3 blurredLayer = sourceBlurred.rgb - sourceBlurredBackground.rgb;", "    vec3 origLayer = scene.rgb - background.rgb;", "", "    float srcContent = smoothstep(0.0015, 0.02, max(max(abs(layer.r), abs(layer.g)), abs(layer.b)));", "    float blurContent = smoothstep(0.0015, 0.02, max(max(abs(blurredLayer.r), abs(blurredLayer.g)), abs(blurredLayer.b)));", "    float scaledContent = max(srcContent, blurContent);", "    float origContent = smoothstep(0.0015, 0.02, max(max(abs(origLayer.r), abs(origLayer.g)), abs(origLayer.b)));", "", "    float amount = opacity;", "    vec3 softLayer = blurredLayer * layerBoost + bloom * smoothstep(0.25, 0.95, max(blurredLayer, vec3(0.0)));", "    vec3 scaledLayer = mix(layer, softLayer, amount) * scaledContent;", "", "    float clearMask = max(origContent, scaledContent);", "    vec3 composed = background.rgb + scaledLayer;", "    OutColor = vec4(clamp(mix(scene.rgb, composed, clearMask), 0.0, 1.0), scene.a);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s1() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D MotionScene;", "uniform sampler2D MotionBackground;", "uniform sampler2D MotionBlurred;", "uniform sampler2D MotionBackgroundBlurred;", "uniform sampler2D MotionMask;", "", "layout(std140) uniform MotionCompositeParams {", "    vec4 CompositeData;", "    vec4 GuiRectData;", "    vec4 TargetData;", "    vec4 SourceRectData;", "    vec4 TransformData;", "};", "", "in vec2 TexCoord;", "out vec4 OutColor;", "", "float roundedRectMask(vec2 point, vec4 rect, float radius, float feather) {", "    vec2 halfSize = max(rect.zw * 0.5, vec2(0.0));", "    vec2 center = rect.xy + halfSize;", "    float safeRadius = min(radius, min(halfSize.x, halfSize.y));", "    vec2 q = abs(point - center) - halfSize + vec2(safeRadius);", "    float distance = length(max(q, vec2(0.0))) + min(max(q.x, q.y), 0.0) - safeRadius;", "    return 1.0 - smoothstep(-feather, feather, distance);", "}", "", "void main() {", "    float opacity = clamp(CompositeData.x, 0.0, 1.0);", "    float bloom = clamp(CompositeData.y, 0.0, 0.12);", "    float layerBoost = max(CompositeData.z, 1.0);", "    float radius = max(CompositeData.w, 0.0);", "    vec2 targetSize = max(TargetData.xy, vec2(1.0));", "    float feather = max(TargetData.z, 0.5);", "    float scale = max(TargetData.w, 0.0001);", "    vec4 scene = texture(MotionScene, TexCoord);", "    vec4 blurred = texture(MotionBlurred, TexCoord);", "    vec2 fragPx = vec2(TexCoord.x * targetSize.x, (1.0 - TexCoord.y) * targetSize.y);", "", "    if (abs(scale - 1.0) <= 0.0005) {", "", "        vec4 maskSample = clamp(texture(MotionMask, TexCoord), vec4(0.0), vec4(1.0));", "        vec4 background = texture(MotionBackground, TexCoord);", "        vec4 blurredBackground = texture(MotionBackgroundBlurred, TexCoord);", "        vec3 layer = scene.rgb - background.rgb;", "        vec3 blurredLayer = blurred.rgb - blurredBackground.rgb;", "        float layerAlpha = scene.a - background.a;", "        float blurredLayerAlpha = blurred.a - blurredBackground.a;", "", "        float amount = opacity * maskSample.a;", "        vec3 boostedBlur = blurredLayer * mix(1.0, layerBoost, amount);", "        vec3 card = mix(layer, boostedBlur, maskSample.g);", "        vec3 composed = background.rgb + layer * (1.0 - amount) + card * amount * maskSample.r;", "        float cardAlpha = mix(layerAlpha, blurredLayerAlpha, maskSample.g);", "        float composedAlpha = background.a", "                + layerAlpha * (1.0 - amount)", "                + cardAlpha * amount * maskSample.r;", "        OutColor = vec4(clamp(composed, 0.0, 1.0), clamp(composedAlpha, 0.0, 1.0));", "        return;", "    }", "", "    vec4 background = texture(MotionBackground, TexCoord);", "    vec4 blurredBackground = texture(MotionBackgroundBlurred, TexCoord);", "    vec2 sourcePx = TransformData.xy + (fragPx - TransformData.xy) / scale;", "    vec2 sourceUv = clamp(vec2(sourcePx.x / targetSize.x, 1.0 - sourcePx.y / targetSize.y), vec2(0.0), vec2(1.0));", "    vec4 sourceScene = texture(MotionScene, sourceUv);", "    vec4 sourceBackground = texture(MotionBackground, sourceUv);", "    vec4 sourceBlurred = texture(MotionBlurred, sourceUv);", "    vec4 sourceBlurredBackground = texture(MotionBackgroundBlurred, sourceUv);", "", "    vec3 layer = sourceScene.rgb - sourceBackground.rgb;", "    vec3 blurredLayer = sourceBlurred.rgb - sourceBlurredBackground.rgb;", "    vec3 origLayer = scene.rgb - background.rgb;", "", "    float srcContent = smoothstep(0.0015, 0.02, max(max(abs(layer.r), abs(layer.g)), abs(layer.b)));", "    float blurContent = smoothstep(0.0015, 0.02, max(max(abs(blurredLayer.r), abs(blurredLayer.g)), abs(blurredLayer.b)));", "    float scaledContent = max(srcContent, blurContent);", "    float origContent = smoothstep(0.0015, 0.02, max(max(abs(origLayer.r), abs(origLayer.g)), abs(origLayer.b)));", "", "    float amount = opacity;", "    vec3 softLayer = blurredLayer * layerBoost + bloom * smoothstep(0.25, 0.95, max(blurredLayer, vec3(0.0)));", "    vec3 scaledLayer = mix(layer, softLayer, amount) * scaledContent;", "", "    float clearMask = max(origContent, scaledContent);", "    vec3 composed = background.rgb + scaledLayer;", "    OutColor = vec4(clamp(mix(scene.rgb, composed, clearMask), 0.0, 1.0), scene.a);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s2() {
        Object[] objectArray = new String[]{"//!vertex", "#version 150", "", "out vec2 TexCoord;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    TexCoord = uvs[gl_VertexID];", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s3() {
        Object[] objectArray = new String[]{"#version 150", "", "out vec2 TexCoord;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    TexCoord = uvs[gl_VertexID];", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s4() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D MotionInput;", "", "layout(std140) uniform MotionBlurParams {", "    vec4 BlurStep;", "};", "", "in vec2 TexCoord;", "out vec4 OutColor;", "", "void main() {", "    vec2 blurStep = BlurStep.xy;", "    vec4 color = vec4(0.0);", "    float totalWeight = 0.0;", "", "    for (int i = -16; i <= 16; i++) {", "        float x = float(i);", "        float weight = exp(-(x * x) / 128.0);", "        vec2 coord = clamp(TexCoord + blurStep * x, vec2(0.0), vec2(1.0));", "        color += texture(MotionInput, coord) * weight;", "        totalWeight += weight;", "    }", "", "    OutColor = color / totalWeight;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s5() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D MotionInput;", "", "layout(std140) uniform MotionBlurParams {", "    vec4 BlurStep;", "};", "", "in vec2 TexCoord;", "out vec4 OutColor;", "", "void main() {", "    vec2 blurStep = BlurStep.xy;", "    vec4 color = vec4(0.0);", "    float totalWeight = 0.0;", "", "    for (int i = -16; i <= 16; i++) {", "        float x = float(i);", "        float weight = exp(-(x * x) / 128.0);", "        vec2 coord = clamp(TexCoord + blurStep * x, vec2(0.0), vec2(1.0));", "        color += texture(MotionInput, coord) * weight;", "        totalWeight += weight;", "    }", "", "    OutColor = color / totalWeight;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s6() {
        Object[] objectArray = new String[]{"//!vertex", "#version 150", "", "in vec3 Position;", "in vec2 UV0;", "in ivec2 UV1;", "in vec4 Color;", "", "out vec2 vLocalPx;", "flat out vec2 vHalfSize;", "flat out float vRadius;", "out vec4 vMaskData;", "", "void main() {", "    gl_Position = vec4(Position.xy, 0.0, 1.0);", "    vLocalPx = UV0;", "    vHalfSize = vec2(UV1) / 16.0;", "    vRadius = Position.z;", "    vMaskData = Color;", "}", "//!fragment", "#version 150", "", "in vec2 vLocalPx;", "flat in vec2 vHalfSize;", "flat in float vRadius;", "in vec4 vMaskData;", "", "out vec4 OutColor;", "", "const float FEATHER = 8.0;", "", "void main() {", "    vec2 p = vLocalPx - vHalfSize;", "    vec2 q = abs(p) - vHalfSize + vec2(vRadius);", "    float d = length(max(q, vec2(0.0))) + min(max(q.x, q.y), 0.0) - vRadius;", "    float coverage = 1.0 - smoothstep(0.0, FEATHER, d);", "    OutColor = vec4(vMaskData.r * coverage, vMaskData.g * coverage, 0.0, coverage);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s7() {
        Object[] objectArray = new String[]{"#version 150", "", "in vec3 Position;", "in vec2 UV0;", "in ivec2 UV1;", "in vec4 Color;", "", "out vec2 vLocalPx;", "flat out vec2 vHalfSize;", "flat out float vRadius;", "out vec4 vMaskData;", "", "void main() {", "    gl_Position = vec4(Position.xy, 0.0, 1.0);", "    vLocalPx = UV0;", "    vHalfSize = vec2(UV1) / 16.0;", "    vRadius = Position.z;", "    vMaskData = Color;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s8() {
        Object[] objectArray = new String[]{"#version 150", "", "in vec2 vLocalPx;", "flat in vec2 vHalfSize;", "flat in float vRadius;", "in vec4 vMaskData;", "", "out vec4 OutColor;", "", "const float FEATHER = 8.0;", "", "void main() {", "    vec2 p = vLocalPx - vHalfSize;", "    vec2 q = abs(p) - vHalfSize + vec2(vRadius);", "    float d = length(max(q, vec2(0.0))) + min(max(q.x, q.y), 0.0) - vRadius;", "    float coverage = 1.0 - smoothstep(0.0, FEATHER, d);", "    OutColor = vec4(vMaskData.r * coverage, vMaskData.g * coverage, 0.0, coverage);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }
}

