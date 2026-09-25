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
package rtx.kimiko.utils.render.shaders.post.ambiencefog;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000f\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/utils/render/shaders/post/ambiencefog/AmbiencefogShaders;", "", "<init>", "()V", "", "", "sources", "", "Lkotlin/jvm/JvmStatic;", "register", "(Ljava/util/Map;)V", "key", "source", "(Ljava/lang/String;)Ljava/lang/String;", "s0", "()Ljava/lang/String;", "s1", "s2", "rtx.kimiko:kimiko"})
public final class AmbiencefogShaders {
    @NotNull
    public static final AmbiencefogShaders INSTANCE = new AmbiencefogShaders();

    private AmbiencefogShaders() {
    }

    @JvmStatic
    public static final void register(@NotNull Map<String, String> sources) {
        Intrinsics.checkNotNullParameter(sources, (String)"sources");
        sources.put("post/ambiencefog/ambiencefog.glsl", INSTANCE.s0());
        sources.put("post/ambiencefog/ambiencefog.vsh", INSTANCE.s1());
        sources.put("post/ambiencefog/ambiencefog.fsh", INSTANCE.s2());
    }

    @JvmStatic
    @NotNull
    public static final String source(@NotNull String key) {
        Intrinsics.checkNotNullParameter((Object)key, (String)"key");
        return switch (key) {
            case "post/ambiencefog/ambiencefog.glsl" -> INSTANCE.s0();
            case "post/ambiencefog/ambiencefog.vsh" -> INSTANCE.s1();
            case "post/ambiencefog/ambiencefog.fsh" -> INSTANCE.s2();
            default -> throw new IllegalArgumentException(key);
        };
    }

    private final String s0() {
        Object[] objectArray = new String[]{"//!vertex", "#version 150", "", "out vec2 texCoord;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    texCoord = uvs[gl_VertexID];", "}", "//!fragment", "#version 150", "", "uniform sampler2D Scene;", "uniform sampler2D DepthSampler;", "", "layout(std140) uniform FogParams {", "    vec4 header;", "    vec4 header2;", "    vec4 camPos;", "    vec4 fogColor;", "    mat4 invViewProj;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "vec3 worldFromDepth(vec2 uv, float depth) {", "    vec4 clip = vec4(uv * 2.0 - 1.0, depth * 2.0 - 1.0, 1.0);", "    vec4 world = invViewProj * clip;", "    return world.xyz / world.w;", "}", "", "float hash12(vec2 p) {", "    vec3 a = fract(p.xyx * vec3(123.34, 234.34, 345.65));", "    a += dot(a, a + 34.45);", "    return fract(a.x * a.y);", "}", "", "float vnoise(vec2 p) {", "    vec2 id = floor(p);", "    vec2 f = fract(p);", "    vec2 u = f * f * (3.0 - 2.0 * f);", "    float a = hash12(id);", "    float b = hash12(id + vec2(1.0, 0.0));", "    float c = hash12(id + vec2(0.0, 1.0));", "    float d = hash12(id + vec2(1.0, 1.0));", "    return mix(mix(a, b, u.x), mix(c, d, u.x), u.y);", "}", "", "void main() {", "    vec2 uv = texCoord;", "    vec4 scene = texture(Scene, uv);", "    float density = header.x;", "    float time = header.y;", "    float falloff = max(header.z, 0.0006);", "    float baseY = header.w;", "    float layerStrength = header2.x;", "    float skyHaze = header2.y;", "    float farDist = max(header2.z, 64.0);", "", "    if (density <= 0.00001) {", "        fragColor = vec4(scene.rgb, 1.0);", "        return;", "    }", "", "    float dScene = texture(DepthSampler, uv).r;", "    bool sky = dScene >= 1.0;", "", "    vec3 rel = worldFromDepth(uv, sky ? 0.9998 : dScene);", "    float relLen = length(rel);", "    vec3 rayDir = rel / max(relLen, 1e-4);", "    float dist = sky ? farDist * mix(0.5, 2.5, skyHaze) : relLen;", "", "    float camY = camPos.y;", "    float deltaY = rayDir.y * dist;", "    float h0 = clamp((camY - baseY) * falloff, -6.0, 12.0);", "    float f0 = exp(-h0);", "    float integ;", "    if (abs(deltaY) > 0.01) {", "        float he = clamp(deltaY * falloff, -12.0, 12.0);", "        integ = f0 * (1.0 - exp(-he)) / he;", "    } else {", "        integ = f0;", "    }", "    integ = min(integ, 6.0);", "", "    float layerMod = 1.0;", "    float layerTaper = 1.0 - smoothstep(90.0, 220.0, dist);", "    if (layerStrength > 0.001 && layerTaper > 0.001) {", "        vec2 np = (camPos.xz + rayDir.xz * min(dist, 140.0) * 0.6) * 0.014", "                + vec2(time * 0.026, time * 0.017);", "        float n = vnoise(np) * 0.65 + vnoise(np * 2.7 + vec2(13.7, 7.1)) * 0.35;", "        layerMod = mix(1.0, 0.45 + 1.2 * n, layerStrength * layerTaper);", "    }", "", "    float fogAmount = clamp(1.0 - exp(-density * integ * layerMod * dist), 0.0, 1.0);", "    if (fogAmount <= 0.002) {", "        fragColor = vec4(scene.rgb, 1.0);", "        return;", "    }", "", "    fragColor = vec4(mix(scene.rgb, fogColor.rgb, fogAmount), 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s1() {
        Object[] objectArray = new String[]{"#version 150", "", "out vec2 texCoord;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    texCoord = uvs[gl_VertexID];", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s2() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D Scene;", "uniform sampler2D DepthSampler;", "", "layout(std140) uniform FogParams {", "    vec4 header;", "    vec4 header2;", "    vec4 camPos;", "    vec4 fogColor;", "    mat4 invViewProj;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "vec3 worldFromDepth(vec2 uv, float depth) {", "    vec4 clip = vec4(uv * 2.0 - 1.0, depth * 2.0 - 1.0, 1.0);", "    vec4 world = invViewProj * clip;", "    return world.xyz / world.w;", "}", "", "float hash12(vec2 p) {", "    vec3 a = fract(p.xyx * vec3(123.34, 234.34, 345.65));", "    a += dot(a, a + 34.45);", "    return fract(a.x * a.y);", "}", "", "float vnoise(vec2 p) {", "    vec2 id = floor(p);", "    vec2 f = fract(p);", "    vec2 u = f * f * (3.0 - 2.0 * f);", "    float a = hash12(id);", "    float b = hash12(id + vec2(1.0, 0.0));", "    float c = hash12(id + vec2(0.0, 1.0));", "    float d = hash12(id + vec2(1.0, 1.0));", "    return mix(mix(a, b, u.x), mix(c, d, u.x), u.y);", "}", "", "void main() {", "    vec2 uv = texCoord;", "    vec4 scene = texture(Scene, uv);", "    float density = header.x;", "    float time = header.y;", "    float falloff = max(header.z, 0.0006);", "    float baseY = header.w;", "    float layerStrength = header2.x;", "    float skyHaze = header2.y;", "    float farDist = max(header2.z, 64.0);", "", "    if (density <= 0.00001) {", "        fragColor = vec4(scene.rgb, 1.0);", "        return;", "    }", "", "    float dScene = texture(DepthSampler, uv).r;", "    bool sky = dScene >= 1.0;", "", "    vec3 rel = worldFromDepth(uv, sky ? 0.9998 : dScene);", "    float relLen = length(rel);", "    vec3 rayDir = rel / max(relLen, 1e-4);", "    float dist = sky ? farDist * mix(0.5, 2.5, skyHaze) : relLen;", "", "    float camY = camPos.y;", "    float deltaY = rayDir.y * dist;", "    float h0 = clamp((camY - baseY) * falloff, -6.0, 12.0);", "    float f0 = exp(-h0);", "    float integ;", "    if (abs(deltaY) > 0.01) {", "        float he = clamp(deltaY * falloff, -12.0, 12.0);", "        integ = f0 * (1.0 - exp(-he)) / he;", "    } else {", "        integ = f0;", "    }", "    integ = min(integ, 6.0);", "", "    float layerMod = 1.0;", "    float layerTaper = 1.0 - smoothstep(90.0, 220.0, dist);", "    if (layerStrength > 0.001 && layerTaper > 0.001) {", "        vec2 np = (camPos.xz + rayDir.xz * min(dist, 140.0) * 0.6) * 0.014", "                + vec2(time * 0.026, time * 0.017);", "        float n = vnoise(np) * 0.65 + vnoise(np * 2.7 + vec2(13.7, 7.1)) * 0.35;", "        layerMod = mix(1.0, 0.45 + 1.2 * n, layerStrength * layerTaper);", "    }", "", "    float fogAmount = clamp(1.0 - exp(-density * integ * layerMod * dist), 0.0, 1.0);", "    if (fogAmount <= 0.002) {", "        fragColor = vec4(scene.rgb, 1.0);", "        return;", "    }", "", "    fragColor = vec4(mix(scene.rgb, fogColor.rgb, fogAmount), 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }
}

