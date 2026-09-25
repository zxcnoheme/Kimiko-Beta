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
package rtx.kimiko.utils.render.shaders;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000fJ\u000f\u0010\u0012\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u000f\u00a8\u0006\u0013"}, d2={"Lrtx/kimiko/utils/render/shaders/RootShaders;", "", "<init>", "()V", "", "", "sources", "", "Lkotlin/jvm/JvmStatic;", "register", "(Ljava/util/Map;)V", "key", "source", "(Ljava/lang/String;)Ljava/lang/String;", "s0", "()Ljava/lang/String;", "s1", "s2", "s3", "rtx.kimiko:kimiko"})
public final class RootShaders {
    @NotNull
    public static final RootShaders INSTANCE = new RootShaders();

    private RootShaders() {
    }

    @JvmStatic
    public static final void register(@NotNull Map<String, String> sources) {
        Intrinsics.checkNotNullParameter(sources, (String)"sources");
        sources.put("frag_effect_scan_fragment.glsl", INSTANCE.s0());
        sources.put("frag_effect_scan_fragment.fsh", INSTANCE.s1());
        sources.put("frag_effect_scan_vertex.glsl", INSTANCE.s2());
        sources.put("frag_effect_scan_vertex.vsh", INSTANCE.s3());
    }

    @JvmStatic
    @NotNull
    public static final String source(@NotNull String key) {
        Intrinsics.checkNotNullParameter((Object)key, (String)"key");
        return switch (key) {
            case "frag_effect_scan_fragment.glsl" -> INSTANCE.s0();
            case "frag_effect_scan_fragment.fsh" -> INSTANCE.s1();
            case "frag_effect_scan_vertex.glsl" -> INSTANCE.s2();
            case "frag_effect_scan_vertex.vsh" -> INSTANCE.s3();
            default -> throw new IllegalArgumentException(key);
        };
    }

    private final String s0() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "layout(std140) uniform Uniforms {", "    mat4 uInvProjection;", "    mat4 uInvView;", "    vec4 uCameraPosRadius;", "    vec4 uCenterWidth;", "    vec4 uOuterColor;", "    vec4 uMidColor;", "    vec4 uInnerColor;", "    vec4 uScanlineColor;", "    vec4 uMeta;", "};", "", "in vec2 vUV;", "out vec4 fragColor;", "", "uniform sampler2D DepthSampler;", "", "float scanlines() {", "    return sin(gl_FragCoord.y) * 0.5 + 0.5;", "}", "", "vec3 reconstructWorldPos(float depth) {", "    float z = depth * 2.0 - 1.0;", "    vec4 clipSpacePosition = vec4(vUV * 2.0 - 1.0, z, 1.0);", "    vec4 viewSpacePosition = uInvProjection * clipSpacePosition;", "    viewSpacePosition /= max(viewSpacePosition.w, 0.0001);", "    vec4 worldSpacePosition = uInvView * viewSpacePosition;", "    return uCameraPosRadius.xyz + worldSpacePosition.xyz;", "}", "", "void main() {", "    float depth = texture(DepthSampler, vUV).r;", "    if (depth >= 1.0) {", "        discard;", "    }", "", "    float radius = uCameraPosRadius.w;", "    float width = max(uCenterWidth.w, 0.0001);", "    float dist = distance(reconstructWorldPos(depth), uCenterWidth.xyz);", "    if (dist >= radius || dist <= radius - width) {", "        discard;", "    }", "", "    float diff = 1.0 - (radius - dist) / width;", "    vec4 edge = mix(uMidColor, uOuterColor, pow(diff, uMeta.x));", "    vec4 color = mix(uInnerColor, edge, diff) + scanlines() * uScanlineColor;", "    color *= diff;", "    fragColor = color;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s1() {
        Object[] objectArray = new String[]{"#version 150", "", "layout(std140) uniform Uniforms {", "    mat4 uInvProjection;", "    mat4 uInvView;", "    vec4 uCameraPosRadius;", "    vec4 uCenterWidth;", "    vec4 uOuterColor;", "    vec4 uMidColor;", "    vec4 uInnerColor;", "    vec4 uScanlineColor;", "    vec4 uMeta;", "};", "", "in vec2 vUV;", "out vec4 fragColor;", "", "uniform sampler2D DepthSampler;", "", "float scanlines() {", "    return sin(gl_FragCoord.y) * 0.5 + 0.5;", "}", "", "vec3 reconstructWorldPos(float depth) {", "    float z = depth * 2.0 - 1.0;", "    vec4 clipSpacePosition = vec4(vUV * 2.0 - 1.0, z, 1.0);", "    vec4 viewSpacePosition = uInvProjection * clipSpacePosition;", "    viewSpacePosition /= max(viewSpacePosition.w, 0.0001);", "    vec4 worldSpacePosition = uInvView * viewSpacePosition;", "    return uCameraPosRadius.xyz + worldSpacePosition.xyz;", "}", "", "void main() {", "    float depth = texture(DepthSampler, vUV).r;", "    if (depth >= 1.0) {", "        discard;", "    }", "", "    float radius = uCameraPosRadius.w;", "    float width = max(uCenterWidth.w, 0.0001);", "    float dist = distance(reconstructWorldPos(depth), uCenterWidth.xyz);", "    if (dist >= radius || dist <= radius - width) {", "        discard;", "    }", "", "    float diff = 1.0 - (radius - dist) / width;", "    vec4 edge = mix(uMidColor, uOuterColor, pow(diff, uMeta.x));", "    vec4 color = mix(uInnerColor, edge, diff) + scanlines() * uScanlineColor;", "    color *= diff;", "    fragColor = color;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s2() {
        Object[] objectArray = new String[]{"//!vertex", "#version 150", "", "out vec2 vUV;", "", "void main() {", "    vec2 vertices[4] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "    int indices[6] = int[](0, 1, 2, 2, 3, 0);", "    vec2 vertex = vertices[indices[gl_VertexID % 6]];", "    gl_Position = vec4(vertex.x * 2.0 - 1.0, 1.0 - vertex.y * 2.0, 0.0, 1.0);", "    vUV = vec2(vertex.x, 1.0 - vertex.y);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s3() {
        Object[] objectArray = new String[]{"#version 150", "", "out vec2 vUV;", "", "void main() {", "    vec2 vertices[4] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "    int indices[6] = int[](0, 1, 2, 2, 3, 0);", "    vec2 vertex = vertices[indices[gl_VertexID % 6]];", "    gl_Position = vec4(vertex.x * 2.0 - 1.0, 1.0 - vertex.y * 2.0, 0.0, 1.0);", "    vUV = vec2(vertex.x, 1.0 - vertex.y);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }
}

