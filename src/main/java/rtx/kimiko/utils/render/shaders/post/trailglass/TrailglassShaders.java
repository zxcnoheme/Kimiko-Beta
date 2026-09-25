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
package rtx.kimiko.utils.render.shaders.post.trailglass;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000f\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/utils/render/shaders/post/trailglass/TrailglassShaders;", "", "<init>", "()V", "", "", "sources", "", "Lkotlin/jvm/JvmStatic;", "register", "(Ljava/util/Map;)V", "key", "source", "(Ljava/lang/String;)Ljava/lang/String;", "s0", "()Ljava/lang/String;", "s1", "s2", "rtx.kimiko:kimiko"})
public final class TrailglassShaders {
    @NotNull
    public static final TrailglassShaders INSTANCE = new TrailglassShaders();

    private TrailglassShaders() {
    }

    @JvmStatic
    public static final void register(@NotNull Map<String, String> sources) {
        Intrinsics.checkNotNullParameter(sources, (String)"sources");
        sources.put("post/trailglass/trailglass.glsl", INSTANCE.s0());
        sources.put("post/trailglass/trailglass.vsh", INSTANCE.s1());
        sources.put("post/trailglass/trailglass.fsh", INSTANCE.s2());
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
            case "post/trailglass/trailglass.glsl" -> INSTANCE.s0();
            case "post/trailglass/trailglass.vsh" -> INSTANCE.s1();
            case "post/trailglass/trailglass.fsh" -> INSTANCE.s2();
            default -> throw new IllegalArgumentException(key);
        };
    }

    private final String s0() {
        Object[] objectArray = new String[]{"//!vertex", "#version 150", "", "out vec2 texCoord;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    texCoord = uvs[gl_VertexID];", "}", "//!fragment", "#version 150", "", "uniform sampler2D Scene;", "uniform sampler2D DepthSampler;", "", "layout(std140) uniform TrailGlass {", "    vec4 header;", "    vec4 header2;", "    vec4 data[32];", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "void main() {", "    vec2 uv = texCoord;", "    int count = int(header.x + 0.5);", "    float aspect = header.y;", "    float time = header.z;", "    float strength = header.w;", "    float tintAmount = header2.x;", "    float sceneDepth = texture(DepthSampler, uv).r;", "", "    float bestCov = 0.0;", "    vec3 bestTint = vec3(0.0);", "    for (int i = 0; i < count - 1; i++) {", "        vec4 a = data[i * 2];", "        vec4 b = data[(i + 1) * 2];", "        vec4 ea = data[i * 2 + 1];", "        vec4 eb = data[(i + 1) * 2 + 1];", "", "        vec2 pa = uv - a.xy;        pa.x *= aspect;", "        vec2 ba = b.xy - a.xy;      ba.x *= aspect;", "        float t = clamp(dot(pa, ba) / max(dot(ba, ba), 1e-6), 0.0, 1.0);", "        float dist = length(pa - ba * t);", "", "        float radius = mix(a.z, b.z, t);", "        float env = mix(ea.x, eb.x, t);", "        float depth = mix(a.w, b.w, t);", "", "        if (sceneDepth + 0.0005 < depth) {", "            continue;", "        }", "", "        float cov = (1.0 - smoothstep(radius * 0.5, radius, dist)) * env;", "        if (cov > bestCov) {", "            bestCov = cov;", "            bestTint = mix(ea.yzw, eb.yzw, t);", "        }", "    }", "", "    vec2 offset = vec2(0.0);", "    if (bestCov > 0.001) {", "        vec2 p = uv * vec2(aspect, 1.0) * 9.0;", "        vec2 turb = vec2(", "            sin(p.y * 1.6 + time * 2.2) + 0.5 * sin(p.y * 3.3 - time * 1.5) + 0.25 * sin(p.x * 5.7 + time * 0.9),", "            cos(p.x * 1.6 - time * 2.2) + 0.5 * cos(p.x * 3.3 + time * 1.5) + 0.25 * cos(p.y * 5.7 - time * 0.9)", "        );", "        turb.x /= aspect;", "        offset = turb * strength * bestCov;", "    }", "", "    vec3 col = texture(Scene, uv + offset).rgb;", "    if (tintAmount > 0.001 && bestCov > 0.001) {", "        col = mix(col, bestTint, tintAmount * bestCov);", "    }", "    fragColor = vec4(col, 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s1() {
        Object[] objectArray = new String[]{"#version 150", "", "out vec2 texCoord;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    texCoord = uvs[gl_VertexID];", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s2() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D Scene;", "uniform sampler2D DepthSampler;", "", "layout(std140) uniform TrailGlass {", "    vec4 header;", "    vec4 header2;", "    vec4 data[32];", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "void main() {", "    vec2 uv = texCoord;", "    int count = int(header.x + 0.5);", "    float aspect = header.y;", "    float time = header.z;", "    float strength = header.w;", "    float tintAmount = header2.x;", "    float sceneDepth = texture(DepthSampler, uv).r;", "", "    float bestCov = 0.0;", "    vec3 bestTint = vec3(0.0);", "    for (int i = 0; i < count - 1; i++) {", "        vec4 a = data[i * 2];", "        vec4 b = data[(i + 1) * 2];", "        vec4 ea = data[i * 2 + 1];", "        vec4 eb = data[(i + 1) * 2 + 1];", "", "        vec2 pa = uv - a.xy;        pa.x *= aspect;", "        vec2 ba = b.xy - a.xy;      ba.x *= aspect;", "        float t = clamp(dot(pa, ba) / max(dot(ba, ba), 1e-6), 0.0, 1.0);", "        float dist = length(pa - ba * t);", "", "        float radius = mix(a.z, b.z, t);", "        float env = mix(ea.x, eb.x, t);", "        float depth = mix(a.w, b.w, t);", "", "        if (sceneDepth + 0.0005 < depth) {", "            continue;", "        }", "", "        float cov = (1.0 - smoothstep(radius * 0.5, radius, dist)) * env;", "        if (cov > bestCov) {", "            bestCov = cov;", "            bestTint = mix(ea.yzw, eb.yzw, t);", "        }", "    }", "", "    vec2 offset = vec2(0.0);", "    if (bestCov > 0.001) {", "        vec2 p = uv * vec2(aspect, 1.0) * 9.0;", "        vec2 turb = vec2(", "            sin(p.y * 1.6 + time * 2.2) + 0.5 * sin(p.y * 3.3 - time * 1.5) + 0.25 * sin(p.x * 5.7 + time * 0.9),", "            cos(p.x * 1.6 - time * 2.2) + 0.5 * cos(p.x * 3.3 + time * 1.5) + 0.25 * cos(p.y * 5.7 - time * 0.9)", "        );", "        turb.x /= aspect;", "        offset = turb * strength * bestCov;", "    }", "", "    vec3 col = texture(Scene, uv + offset).rgb;", "    if (tintAmount > 0.001 && bestCov > 0.001) {", "        col = mix(col, bestTint, tintAmount * bestCov);", "    }", "    fragColor = vec4(col, 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }
}

