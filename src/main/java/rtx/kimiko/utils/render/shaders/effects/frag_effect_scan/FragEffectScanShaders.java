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
package rtx.kimiko.utils.render.shaders.effects.frag_effect_scan;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000f\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/utils/render/shaders/effects/frag_effect_scan/FragEffectScanShaders;", "", "<init>", "()V", "", "", "sources", "", "Lkotlin/jvm/JvmStatic;", "register", "(Ljava/util/Map;)V", "key", "source", "(Ljava/lang/String;)Ljava/lang/String;", "s0", "()Ljava/lang/String;", "s1", "s2", "rtx.kimiko:kimiko"})
public final class FragEffectScanShaders {
    @NotNull
    public static final FragEffectScanShaders INSTANCE = new FragEffectScanShaders();

    private FragEffectScanShaders() {
    }

    @JvmStatic
    public static final void register(@NotNull Map<String, String> sources) {
        Intrinsics.checkNotNullParameter(sources, (String)"sources");
        sources.put("effects/frag_effect_scan/frag_effect_scan.glsl", INSTANCE.s0());
        sources.put("effects/frag_effect_scan/frag_effect_scan.vsh", INSTANCE.s1());
        sources.put("effects/frag_effect_scan/frag_effect_scan.fsh", INSTANCE.s2());
    }

    @JvmStatic
    @NotNull
    public static final String source(@NotNull String key) {
        Intrinsics.checkNotNullParameter((Object)key, (String)"key");
        return switch (key) {
            case "effects/frag_effect_scan/frag_effect_scan.glsl" -> INSTANCE.s0();
            case "effects/frag_effect_scan/frag_effect_scan.vsh" -> INSTANCE.s1();
            case "effects/frag_effect_scan/frag_effect_scan.fsh" -> INSTANCE.s2();
            default -> throw new IllegalArgumentException(key);
        };
    }

    private final String s0() {
        Object[] objectArray = new String[]{"//!vertex", "#version 150", "", "out vec2 vUV;", "", "void main() {", "    vec2 vertices[4] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "    int indices[6] = int[](0, 1, 2, 2, 3, 0);", "    vec2 vertex = vertices[indices[gl_VertexID % 6]];", "    gl_Position = vec4(vertex.x * 2.0 - 1.0, 1.0 - vertex.y * 2.0, 0.0, 1.0);", "    vUV = vec2(vertex.x, 1.0 - vertex.y);", "}", "//!fragment", "#version 150", "", "layout(std140) uniform Uniforms {", "    mat4 uInvProjection;", "    mat4 uInvView;", "    vec4 uCameraPosRadius;", "    vec4 uCenterWidth;", "    vec4 uOuterColor;", "    vec4 uMidColor;", "    vec4 uInnerColor;", "    vec4 uScanlineColor;", "    vec4 uMeta;", "};", "", "in vec2 vUV;", "out vec4 fragColor;", "", "uniform sampler2D DepthSampler;", "", "float scanlines() {", "    return sin(gl_FragCoord.y) * 0.5 + 0.5;", "}", "", "float hash12(vec2 value) {", "    vec3 p = fract(vec3(value.xyx) * 0.1031);", "    p += dot(p, p.yzx + 33.33);", "    return fract((p.x + p.y) * p.z);", "}", "", "vec2 surfaceProjection(vec3 worldPosition) {", "    vec3 dx = dFdx(worldPosition);", "    vec3 dy = dFdy(worldPosition);", "    vec3 faceNormal = cross(dx, dy);", "    vec3 normal = abs(faceNormal) / max(length(faceNormal), 0.0001);", "    if (normal.y >= normal.x && normal.y >= normal.z) {", "        return worldPosition.xz;", "    }", "    if (normal.x >= normal.z) {", "        return worldPosition.zy;", "    }", "    return worldPosition.xy;", "}", "", "vec3 reconstructWorldPos(float depth) {", "    float z = depth * 2.0 - 1.0;", "    vec4 clipSpacePosition = vec4(vUV * 2.0 - 1.0, z, 1.0);", "    vec4 viewSpacePosition = uInvProjection * clipSpacePosition;", "    viewSpacePosition /= max(viewSpacePosition.w, 0.0001);", "    vec4 worldSpacePosition = uInvView * viewSpacePosition;", "    return uCameraPosRadius.xyz + worldSpacePosition.xyz;", "}", "", "void main() {", "    float depth = texture(DepthSampler, vUV).r;", "    if (depth >= 1.0) {", "        discard;", "    }", "", "    vec3 worldPosition = reconstructWorldPos(depth);", "    float radius = uCameraPosRadius.w;", "    float width = max(uCenterWidth.w, 0.0001);", "    float flick = clamp(uMeta.z, 0.0, 1.0);", "    float cellSize = mix(0.27, 0.18, flick);", "", "    vec2 surfacePosition = surfaceProjection(worldPosition);", "    vec2 cell = floor(surfacePosition / cellSize);", "    vec2 cellUv = fract(surfacePosition / cellSize);", "    float cellNoise = hash12(cell);", "    float cellNoise2 = hash12(cell + vec2(floor(uMeta.y * 18.0), floor(uMeta.y * 11.0)));", "", "    float dist = length(worldPosition - uCenterWidth.xyz);", "    if (dist >= radius || dist <= radius - width) {", "        discard;", "    }", "", "    float diff = clamp(1.0 - (radius - dist) / width, 0.0, 1.0);", "    float edgePower = max(1.35, uMeta.x * 0.085);", "    float front = pow(diff, edgePower);", "    float circularEdge = 1.0 - smoothstep(radius - max(cellSize * 0.85, 0.16), radius, dist);", "    float circularSpread = smoothstep(0.015, 0.13, diff);", "    float trail = smoothstep(0.035, 0.46, diff) * (1.0 - smoothstep(0.90, 1.0, uMeta.y));", "", "    vec2 squareUv = abs(cellUv - vec2(0.5));", "    float squareCore = 1.0 - smoothstep(0.38, 0.50, max(squareUv.x, squareUv.y));", "    float softCell = mix(0.72, 1.0, squareCore);", "    float corruption = max(smoothstep(cellNoise * 0.14, 0.88, diff), trail * (0.42 + cellNoise2 * 0.28));", "    float pulse = 0.72 + 0.28 * cellNoise2;", "    float sparks = step(0.82, cellNoise2) * squareCore * front;", "", "    vec4 infection = mix(uInnerColor, uMidColor, corruption);", "    vec4 edge = mix(infection, uOuterColor, front);", "    float squareMask = max(softCell * corruption * pulse, circularSpread * 0.62);", "    float intensity = squareMask * 0.92 + front * 1.05 + trail * softCell * 0.36 + sparks * 0.42;", "", "    vec4 color = edge * intensity;", "    color += uMidColor * trail * softCell * 0.28;", "    color += uScanlineColor * scanlines() * (front * 0.68 + trail * 0.24 + sparks * 0.34) * squareCore;", "    color *= (0.42 + diff * 0.72) * circularEdge;", "    fragColor = color;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s1() {
        Object[] objectArray = new String[]{"#version 150", "", "out vec2 vUV;", "", "void main() {", "    vec2 vertices[4] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "    int indices[6] = int[](0, 1, 2, 2, 3, 0);", "    vec2 vertex = vertices[indices[gl_VertexID % 6]];", "    gl_Position = vec4(vertex.x * 2.0 - 1.0, 1.0 - vertex.y * 2.0, 0.0, 1.0);", "    vUV = vec2(vertex.x, 1.0 - vertex.y);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s2() {
        Object[] objectArray = new String[]{"#version 150", "", "layout(std140) uniform Uniforms {", "    mat4 uInvProjection;", "    mat4 uInvView;", "    vec4 uCameraPosRadius;", "    vec4 uCenterWidth;", "    vec4 uOuterColor;", "    vec4 uMidColor;", "    vec4 uInnerColor;", "    vec4 uScanlineColor;", "    vec4 uMeta;", "};", "", "in vec2 vUV;", "out vec4 fragColor;", "", "uniform sampler2D DepthSampler;", "", "float scanlines() {", "    return sin(gl_FragCoord.y) * 0.5 + 0.5;", "}", "", "float hash12(vec2 value) {", "    vec3 p = fract(vec3(value.xyx) * 0.1031);", "    p += dot(p, p.yzx + 33.33);", "    return fract((p.x + p.y) * p.z);", "}", "", "vec2 surfaceProjection(vec3 worldPosition) {", "    vec3 dx = dFdx(worldPosition);", "    vec3 dy = dFdy(worldPosition);", "    vec3 faceNormal = cross(dx, dy);", "    vec3 normal = abs(faceNormal) / max(length(faceNormal), 0.0001);", "    if (normal.y >= normal.x && normal.y >= normal.z) {", "        return worldPosition.xz;", "    }", "    if (normal.x >= normal.z) {", "        return worldPosition.zy;", "    }", "    return worldPosition.xy;", "}", "", "vec3 reconstructWorldPos(float depth) {", "    float z = depth * 2.0 - 1.0;", "    vec4 clipSpacePosition = vec4(vUV * 2.0 - 1.0, z, 1.0);", "    vec4 viewSpacePosition = uInvProjection * clipSpacePosition;", "    viewSpacePosition /= max(viewSpacePosition.w, 0.0001);", "    vec4 worldSpacePosition = uInvView * viewSpacePosition;", "    return uCameraPosRadius.xyz + worldSpacePosition.xyz;", "}", "", "void main() {", "    float depth = texture(DepthSampler, vUV).r;", "    if (depth >= 1.0) {", "        discard;", "    }", "", "    vec3 worldPosition = reconstructWorldPos(depth);", "    float radius = uCameraPosRadius.w;", "    float width = max(uCenterWidth.w, 0.0001);", "    float flick = clamp(uMeta.z, 0.0, 1.0);", "    float cellSize = mix(0.27, 0.18, flick);", "", "    vec2 surfacePosition = surfaceProjection(worldPosition);", "    vec2 cell = floor(surfacePosition / cellSize);", "    vec2 cellUv = fract(surfacePosition / cellSize);", "    float cellNoise = hash12(cell);", "    float cellNoise2 = hash12(cell + vec2(floor(uMeta.y * 18.0), floor(uMeta.y * 11.0)));", "", "    float dist = length(worldPosition - uCenterWidth.xyz);", "    if (dist >= radius || dist <= radius - width) {", "        discard;", "    }", "", "    float diff = clamp(1.0 - (radius - dist) / width, 0.0, 1.0);", "    float edgePower = max(1.35, uMeta.x * 0.085);", "    float front = pow(diff, edgePower);", "    float circularEdge = 1.0 - smoothstep(radius - max(cellSize * 0.85, 0.16), radius, dist);", "    float circularSpread = smoothstep(0.015, 0.13, diff);", "    float trail = smoothstep(0.035, 0.46, diff) * (1.0 - smoothstep(0.90, 1.0, uMeta.y));", "", "    vec2 squareUv = abs(cellUv - vec2(0.5));", "    float squareCore = 1.0 - smoothstep(0.38, 0.50, max(squareUv.x, squareUv.y));", "    float softCell = mix(0.72, 1.0, squareCore);", "    float corruption = max(smoothstep(cellNoise * 0.14, 0.88, diff), trail * (0.42 + cellNoise2 * 0.28));", "    float pulse = 0.72 + 0.28 * cellNoise2;", "    float sparks = step(0.82, cellNoise2) * squareCore * front;", "", "    vec4 infection = mix(uInnerColor, uMidColor, corruption);", "    vec4 edge = mix(infection, uOuterColor, front);", "    float squareMask = max(softCell * corruption * pulse, circularSpread * 0.62);", "    float intensity = squareMask * 0.92 + front * 1.05 + trail * softCell * 0.36 + sparks * 0.42;", "", "    vec4 color = edge * intensity;", "    color += uMidColor * trail * softCell * 0.28;", "    color += uScanlineColor * scanlines() * (front * 0.68 + trail * 0.24 + sparks * 0.34) * squareCore;", "    color *= (0.42 + diff * 0.72) * circularEdge;", "    fragColor = color;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }
}

