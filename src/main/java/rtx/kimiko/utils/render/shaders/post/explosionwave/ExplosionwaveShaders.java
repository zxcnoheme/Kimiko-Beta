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
package rtx.kimiko.utils.render.shaders.post.explosionwave;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000f\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/utils/render/shaders/post/explosionwave/ExplosionwaveShaders;", "", "<init>", "()V", "", "", "sources", "", "Lkotlin/jvm/JvmStatic;", "register", "(Ljava/util/Map;)V", "key", "source", "(Ljava/lang/String;)Ljava/lang/String;", "s0", "()Ljava/lang/String;", "s1", "s2", "rtx.kimiko:kimiko"})
public final class ExplosionwaveShaders {
    @NotNull
    public static final ExplosionwaveShaders INSTANCE = new ExplosionwaveShaders();

    private ExplosionwaveShaders() {
    }

    @JvmStatic
    public static final void register(@NotNull Map<String, String> sources) {
        Intrinsics.checkNotNullParameter(sources, (String)"sources");
        sources.put("post/explosionwave/explosionwave.glsl", INSTANCE.s0());
        sources.put("post/explosionwave/explosionwave.vsh", INSTANCE.s1());
        sources.put("post/explosionwave/explosionwave.fsh", INSTANCE.s2());
    }

    @JvmStatic
    @NotNull
    public static final String source(@NotNull String key) {
        Intrinsics.checkNotNullParameter((Object)key, (String)"key");
        return switch (key) {
            case "post/explosionwave/explosionwave.glsl" -> INSTANCE.s0();
            case "post/explosionwave/explosionwave.vsh" -> INSTANCE.s1();
            case "post/explosionwave/explosionwave.fsh" -> INSTANCE.s2();
            default -> throw new IllegalArgumentException(key);
        };
    }

    private final String s0() {
        Object[] objectArray = new String[]{"//!vertex", "#version 150", "", "out vec2 texCoord;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    texCoord = uvs[gl_VertexID];", "}", "//!fragment", "#version 150", "", "uniform sampler2D Scene;", "uniform sampler2D DepthSampler;", "", "layout(std140) uniform Waves {", "    vec4 header;", "    vec4 header2;", "    mat4 invViewProj;", "    vec4 data[24];", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "const float PI = 3.14159265;", "", "vec3 worldFromDepth(vec2 uv, float depth) {", "    vec4 clip = vec4(uv * 2.0 - 1.0, depth * 2.0 - 1.0, 1.0);", "    vec4 world = invViewProj * clip;", "    return world.xyz / world.w;", "}", "", "void main() {", "    vec2 uv = texCoord;", "    int count = int(header.x + 0.5);", "    float aspect = header.y;", "    float chroma = header.z;", "    float globalFlash = header.w;", "    float rimGlowStrength = header2.x;", "", "    float sceneDepth = texture(DepthSampler, uv).r;", "    bool hasScene = sceneDepth < 1.0;", "    vec3 scenePos = worldFromDepth(uv, hasScene ? sceneDepth : 1.0);", "    float sceneDist = hasScene ? length(scenePos) : 1e9;", "    vec3 rayDir = normalize(scenePos);", "", "    vec2 offset = vec2(0.0);", "    float influence = 0.0;", "    float rimFlash = 0.0;", "", "    for (int i = 0; i < count; i++) {", "        vec3 center = data[i * 3].xyz;", "        float radius = data[i * 3].w;", "        float thickness = max(data[i * 3 + 1].x, 1e-3);", "        float amp = data[i * 3 + 1].y;", "        float env = data[i * 3 + 1].z;", "        float flash = data[i * 3 + 1].w;", "        vec2 cuv = data[i * 3 + 2].xy;", "        float valid = data[i * 3 + 2].z;", "        float distCam = data[i * 3 + 2].w;", "", "        float tc = dot(center, rayDir);", "        float w;", "        vec2 dirScreen;", "        if (tc > 0.0 && valid > 0.5) {", "            float b = length(center - rayDir * tc);", "            float chord = sqrt(max(radius * radius - b * b, 0.0));", "            float tHit = tc - chord;", "            if (hasScene && sceneDist + 0.5 < tHit) {", "                continue;", "            }", "            w = (b - radius) / thickness;", "            vec2 d = (uv - cuv) * vec2(aspect, 1.0);", "            float dl = length(d);", "            dirScreen = dl > 1e-5 ? d / dl : vec2(0.0);", "        } else {", "            w = (distCam - radius) / thickness;", "            vec2 d = (uv - vec2(0.5)) * vec2(aspect, 1.0);", "            float dl = length(d);", "            dirScreen = dl > 1e-5 ? d / dl : vec2(0.0);", "        }", "", "        if (abs(w) >= 1.0) {", "            continue;", "        }", "", "        float band = smoothstep(0.0, 1.0, 1.0 - abs(w)) * env;", "        if (band <= 0.0) {", "            continue;", "        }", "", "        float wave = sin(w * PI) * (1.0 - abs(w));", "        vec2 sd = dirScreen;", "        sd.x /= aspect;", "        offset += sd * wave * amp;", "", "        influence = influence + band - influence * band;", "        rimFlash += flash * band;", "    }", "", "    vec3 col;", "    if (influence > 0.001 && chroma > 0.0) {", "        float spread = chroma * clamp(influence, 0.0, 1.0);", "        col.r = texture(Scene, uv + offset * (1.0 + spread)).r;", "        col.g = texture(Scene, uv + offset).g;", "        col.b = texture(Scene, uv + offset * (1.0 - spread)).b;", "    } else {", "        col = texture(Scene, uv + offset).rgb;", "    }", "", "    if (rimFlash > 0.001) {", "        col += vec3(1.0, 0.96, 0.88) * (rimFlash * rimGlowStrength);", "    }", "", "    if (globalFlash > 0.001) {", "        col = mix(col, vec3(1.0, 0.98, 0.92), clamp(globalFlash, 0.0, 1.0));", "    }", "", "    fragColor = vec4(min(col, vec3(1.0)), 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s1() {
        Object[] objectArray = new String[]{"#version 150", "", "out vec2 texCoord;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    texCoord = uvs[gl_VertexID];", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s2() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D Scene;", "uniform sampler2D DepthSampler;", "", "layout(std140) uniform Waves {", "    vec4 header;", "    vec4 header2;", "    mat4 invViewProj;", "    vec4 data[24];", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "const float PI = 3.14159265;", "", "vec3 worldFromDepth(vec2 uv, float depth) {", "    vec4 clip = vec4(uv * 2.0 - 1.0, depth * 2.0 - 1.0, 1.0);", "    vec4 world = invViewProj * clip;", "    return world.xyz / world.w;", "}", "", "void main() {", "    vec2 uv = texCoord;", "    int count = int(header.x + 0.5);", "    float aspect = header.y;", "    float chroma = header.z;", "    float globalFlash = header.w;", "    float rimGlowStrength = header2.x;", "", "    float sceneDepth = texture(DepthSampler, uv).r;", "    bool hasScene = sceneDepth < 1.0;", "    vec3 scenePos = worldFromDepth(uv, hasScene ? sceneDepth : 1.0);", "    float sceneDist = hasScene ? length(scenePos) : 1e9;", "    vec3 rayDir = normalize(scenePos);", "", "    vec2 offset = vec2(0.0);", "    float influence = 0.0;", "    float rimFlash = 0.0;", "", "    for (int i = 0; i < count; i++) {", "        vec3 center = data[i * 3].xyz;", "        float radius = data[i * 3].w;", "        float thickness = max(data[i * 3 + 1].x, 1e-3);", "        float amp = data[i * 3 + 1].y;", "        float env = data[i * 3 + 1].z;", "        float flash = data[i * 3 + 1].w;", "        vec2 cuv = data[i * 3 + 2].xy;", "        float valid = data[i * 3 + 2].z;", "        float distCam = data[i * 3 + 2].w;", "", "        float tc = dot(center, rayDir);", "        float w;", "        vec2 dirScreen;", "        if (tc > 0.0 && valid > 0.5) {", "            float b = length(center - rayDir * tc);", "            float chord = sqrt(max(radius * radius - b * b, 0.0));", "            float tHit = tc - chord;", "            if (hasScene && sceneDist + 0.5 < tHit) {", "                continue;", "            }", "            w = (b - radius) / thickness;", "            vec2 d = (uv - cuv) * vec2(aspect, 1.0);", "            float dl = length(d);", "            dirScreen = dl > 1e-5 ? d / dl : vec2(0.0);", "        } else {", "            w = (distCam - radius) / thickness;", "            vec2 d = (uv - vec2(0.5)) * vec2(aspect, 1.0);", "            float dl = length(d);", "            dirScreen = dl > 1e-5 ? d / dl : vec2(0.0);", "        }", "", "        if (abs(w) >= 1.0) {", "            continue;", "        }", "", "        float band = smoothstep(0.0, 1.0, 1.0 - abs(w)) * env;", "        if (band <= 0.0) {", "            continue;", "        }", "", "        float wave = sin(w * PI) * (1.0 - abs(w));", "        vec2 sd = dirScreen;", "        sd.x /= aspect;", "        offset += sd * wave * amp;", "", "        influence = influence + band - influence * band;", "        rimFlash += flash * band;", "    }", "", "    vec3 col;", "    if (influence > 0.001 && chroma > 0.0) {", "        float spread = chroma * clamp(influence, 0.0, 1.0);", "        col.r = texture(Scene, uv + offset * (1.0 + spread)).r;", "        col.g = texture(Scene, uv + offset).g;", "        col.b = texture(Scene, uv + offset * (1.0 - spread)).b;", "    } else {", "        col = texture(Scene, uv + offset).rgb;", "    }", "", "    if (rimFlash > 0.001) {", "        col += vec3(1.0, 0.96, 0.88) * (rimFlash * rimGlowStrength);", "    }", "", "    if (globalFlash > 0.001) {", "        col = mix(col, vec3(1.0, 0.98, 0.92), clamp(globalFlash, 0.0, 1.0));", "    }", "", "    fragColor = vec4(min(col, vec3(1.0)), 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }
}

