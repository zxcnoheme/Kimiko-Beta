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
package rtx.kimiko.utils.render.shaders.post.killdistortion;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000f\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/utils/render/shaders/post/killdistortion/KilldistortionShaders;", "", "<init>", "()V", "", "", "sources", "", "Lkotlin/jvm/JvmStatic;", "register", "(Ljava/util/Map;)V", "key", "source", "(Ljava/lang/String;)Ljava/lang/String;", "s0", "()Ljava/lang/String;", "s1", "s2", "rtx.kimiko:kimiko"})
public final class KilldistortionShaders {
    @NotNull
    public static final KilldistortionShaders INSTANCE = new KilldistortionShaders();

    private KilldistortionShaders() {
    }

    @JvmStatic
    public static final void register(@NotNull Map<String, String> sources) {
        Intrinsics.checkNotNullParameter(sources, (String)"sources");
        sources.put("post/killdistortion/killdistortion.glsl", INSTANCE.s0());
        sources.put("post/killdistortion/killdistortion.vsh", INSTANCE.s1());
        sources.put("post/killdistortion/killdistortion.fsh", INSTANCE.s2());
    }

    @JvmStatic
    @NotNull
    public static final String source(@NotNull String key) {
        Intrinsics.checkNotNullParameter((Object)key, (String)"key");
        return switch (key) {
            case "post/killdistortion/killdistortion.glsl" -> INSTANCE.s0();
            case "post/killdistortion/killdistortion.vsh" -> INSTANCE.s1();
            case "post/killdistortion/killdistortion.fsh" -> INSTANCE.s2();
            default -> throw new IllegalArgumentException(key);
        };
    }

    private final String s0() {
        Object[] objectArray = new String[]{"//!vertex", "#version 150", "", "out vec2 texCoord;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    texCoord = uvs[gl_VertexID];", "}", "//!fragment", "#version 150", "", "uniform sampler2D Scene;", "uniform sampler2D DepthSampler;", "", "layout(std140) uniform KillDistortion {", "", "    vec4 header;", "", "    vec4 params;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "void main() {", "    vec2 uv = texCoord;", "    float aspect = header.w;", "    vec2 center = header.xy;", "    float centerDepth = header.z;", "", "    float radius = params.x;", "    float halfWidth = max(params.y, 1e-4);", "    float strength = params.z;", "    float time = params.w;", "", "    vec2 d = uv - center;", "    d.x *= aspect;", "    float dist = length(d);", "    vec2 dir = dist > 1e-5 ? d / dist : vec2(0.0);", "", "    float band = 1.0 - smoothstep(0.0, halfWidth, abs(dist - radius));", "    if (band <= 0.001) {", "        fragColor = vec4(texture(Scene, uv).rgb, 1.0);", "        return;", "    }", "", "    float sceneDepth = texture(DepthSampler, uv).r;", "    if (sceneDepth + 0.0005 < centerDepth) {", "        fragColor = vec4(texture(Scene, uv).rgb, 1.0);", "        return;", "    }", "", "    float soft = smoothstep(0.0, 1.0, band);", "", "    vec2 tangent = vec2(-dir.y, dir.x);", "    float shimmer = sin((dist * 22.0) - time * 3.0) * 0.18;", "    vec2 offset = (dir * soft + tangent * shimmer * soft) * strength;", "    offset.x /= aspect;", "", "    vec3 col = texture(Scene, uv + offset).rgb;", "", "    float rim = pow(soft, 4.0) * 0.10;", "    col += vec3(rim);", "", "    fragColor = vec4(col, 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s1() {
        Object[] objectArray = new String[]{"#version 150", "", "out vec2 texCoord;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    texCoord = uvs[gl_VertexID];", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s2() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D Scene;", "uniform sampler2D DepthSampler;", "", "layout(std140) uniform KillDistortion {", "", "    vec4 header;", "", "    vec4 params;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "void main() {", "    vec2 uv = texCoord;", "    float aspect = header.w;", "    vec2 center = header.xy;", "    float centerDepth = header.z;", "", "    float radius = params.x;", "    float halfWidth = max(params.y, 1e-4);", "    float strength = params.z;", "    float time = params.w;", "", "    vec2 d = uv - center;", "    d.x *= aspect;", "    float dist = length(d);", "    vec2 dir = dist > 1e-5 ? d / dist : vec2(0.0);", "", "    float band = 1.0 - smoothstep(0.0, halfWidth, abs(dist - radius));", "    if (band <= 0.001) {", "        fragColor = vec4(texture(Scene, uv).rgb, 1.0);", "        return;", "    }", "", "    float sceneDepth = texture(DepthSampler, uv).r;", "    if (sceneDepth + 0.0005 < centerDepth) {", "        fragColor = vec4(texture(Scene, uv).rgb, 1.0);", "        return;", "    }", "", "    float soft = smoothstep(0.0, 1.0, band);", "", "    vec2 tangent = vec2(-dir.y, dir.x);", "    float shimmer = sin((dist * 22.0) - time * 3.0) * 0.18;", "    vec2 offset = (dir * soft + tangent * shimmer * soft) * strength;", "    offset.x /= aspect;", "", "    vec3 col = texture(Scene, uv + offset).rgb;", "", "    float rim = pow(soft, 4.0) * 0.10;", "    col += vec3(rim);", "", "    fragColor = vec4(col, 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }
}

