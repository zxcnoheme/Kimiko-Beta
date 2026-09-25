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
package rtx.kimiko.utils.render.shaders.ui.batched_blur;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000f\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/utils/render/shaders/ui/batched_blur/BatchedBlurShaders;", "", "<init>", "()V", "", "", "sources", "", "Lkotlin/jvm/JvmStatic;", "register", "(Ljava/util/Map;)V", "key", "source", "(Ljava/lang/String;)Ljava/lang/String;", "s0", "()Ljava/lang/String;", "s1", "s2", "rtx.kimiko:kimiko"})
public final class BatchedBlurShaders {
    @NotNull
    public static final BatchedBlurShaders INSTANCE = new BatchedBlurShaders();

    private BatchedBlurShaders() {
    }

    @JvmStatic
    public static final void register(@NotNull Map<String, String> sources) {
        Intrinsics.checkNotNullParameter(sources, (String)"sources");
        sources.put("ui/batched_blur/batched_blur.glsl", INSTANCE.s0());
        sources.put("ui/batched_blur/batched_blur.vsh", INSTANCE.s1());
        sources.put("ui/batched_blur/batched_blur.fsh", INSTANCE.s2());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @JvmStatic
    @NotNull
    public static final String source(@NotNull String key) {
        Intrinsics.checkNotNullParameter((Object)key, (String)"key");
        return switch (key) {
            case "ui/batched_blur/batched_blur.glsl" -> INSTANCE.s0();
            case "ui/batched_blur/batched_blur.vsh" -> INSTANCE.s1();
            case "ui/batched_blur/batched_blur.fsh" -> INSTANCE.s2();
            default -> throw new IllegalArgumentException(key);
        };
    }

    private final String s0() {
        Object[] objectArray = new String[]{"//!vertex", "#version 150", "", "#moj_import <kimiko:releon_common.glsl>", "", "#moj_import <minecraft:dynamictransforms.glsl>", "#moj_import <minecraft:projection.glsl>", "", "in vec3 Position;", "in vec4 Color;", "in float LineWidth;", "", "out vec2 FragCoord;", "out vec4 FragColor;", "flat out int QuadIndex;", "", "void main() {", "    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);", "    FragCoord = rvertexcoord(gl_VertexID);", "    FragColor = Color;", "    QuadIndex = max(int(LineWidth + 0.5) - 1, 0);", "}", "//!fragment", "#version 150", "", "#moj_import <kimiko:releon_common.glsl>", "", "in vec2 FragCoord;", "in vec4 FragColor;", "flat in int QuadIndex;", "", "uniform sampler2D Sampler0;", "", "layout(std140) uniform BlurParamsArray {", "    vec4 params[2048];", "};", "", "out vec4 OutColor;", "", "void main() {", "    int idx = QuadIndex;", "    vec4 Radius = params[idx * 3];", "    vec4 sizeSmooth = params[idx * 3 + 1];", "    vec4 reg = params[idx * 3 + 2];", "    vec2 Size = sizeSmooth.xy;", "    float Smoothness = sizeSmooth.z;", "", "    vec2 texCoord = clamp((gl_FragCoord.xy - reg.xy) / max(reg.zw, vec2(1.0)), vec2(0.0), vec2(1.0));", "    vec3 blurred = texture(Sampler0, texCoord).rgb;", "", "    float tintStrength = clamp(FragColor.a * (1.0 - dot(FragColor.rgb, vec3(0.299, 0.587, 0.114))) * 0.72, 0.0, 0.82);", "    vec4 color = vec4(mix(blurred, FragColor.rgb, tintStrength), FragColor.a);", "    color.a *= ralpha(Size, FragCoord, Radius, Smoothness);", "", "    if (color.a == 0.0) { discard; }", "    OutColor = color;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s1() {
        Object[] objectArray = new String[]{"#version 150", "", "#moj_import <kimiko:releon_common.glsl>", "", "#moj_import <minecraft:dynamictransforms.glsl>", "#moj_import <minecraft:projection.glsl>", "", "in vec3 Position;", "in vec4 Color;", "in float LineWidth;", "", "out vec2 FragCoord;", "out vec4 FragColor;", "flat out int QuadIndex;", "", "void main() {", "    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);", "    FragCoord = rvertexcoord(gl_VertexID);", "    FragColor = Color;", "    QuadIndex = max(int(LineWidth + 0.5) - 1, 0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s2() {
        Object[] objectArray = new String[]{"#version 150", "", "#moj_import <kimiko:releon_common.glsl>", "", "in vec2 FragCoord;", "in vec4 FragColor;", "flat in int QuadIndex;", "", "uniform sampler2D Sampler0;", "", "layout(std140) uniform BlurParamsArray {", "    vec4 params[2048];", "};", "", "out vec4 OutColor;", "", "void main() {", "    int idx = QuadIndex;", "    vec4 Radius = params[idx * 3];", "    vec4 sizeSmooth = params[idx * 3 + 1];", "    vec4 reg = params[idx * 3 + 2];", "    vec2 Size = sizeSmooth.xy;", "    float Smoothness = sizeSmooth.z;", "", "    vec2 texCoord = clamp((gl_FragCoord.xy - reg.xy) / max(reg.zw, vec2(1.0)), vec2(0.0), vec2(1.0));", "    vec3 blurred = texture(Sampler0, texCoord).rgb;", "", "    float tintStrength = clamp(FragColor.a * (1.0 - dot(FragColor.rgb, vec3(0.299, 0.587, 0.114))) * 0.72, 0.0, 0.82);", "    vec4 color = vec4(mix(blurred, FragColor.rgb, tintStrength), FragColor.a);", "    color.a *= ralpha(Size, FragCoord, Radius, Smoothness);", "", "    if (color.a == 0.0) { discard; }", "    OutColor = color;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }
}

