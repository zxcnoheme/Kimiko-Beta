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
package rtx.kimiko.utils.render.shaders.post.wasted;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000f\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/utils/render/shaders/post/wasted/WastedShaders;", "", "<init>", "()V", "", "", "sources", "", "Lkotlin/jvm/JvmStatic;", "register", "(Ljava/util/Map;)V", "key", "source", "(Ljava/lang/String;)Ljava/lang/String;", "s0", "()Ljava/lang/String;", "s1", "s2", "rtx.kimiko:kimiko"})
public final class WastedShaders {
    @NotNull
    public static final WastedShaders INSTANCE = new WastedShaders();

    private WastedShaders() {
    }

    @JvmStatic
    public static final void register(@NotNull Map<String, String> sources) {
        Intrinsics.checkNotNullParameter(sources, (String)"sources");
        sources.put("post/wasted/wasted.glsl", INSTANCE.s0());
        sources.put("post/wasted/wasted.vsh", INSTANCE.s1());
        sources.put("post/wasted/wasted.fsh", INSTANCE.s2());
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
            case "post/wasted/wasted.glsl" -> INSTANCE.s0();
            case "post/wasted/wasted.vsh" -> INSTANCE.s1();
            case "post/wasted/wasted.fsh" -> INSTANCE.s2();
            default -> throw new IllegalArgumentException(key);
        };
    }

    private final String s0() {
        Object[] objectArray = new String[]{"//!vertex", "#version 150", "", "out vec2 texCoord;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    texCoord = uvs[gl_VertexID];", "}", "//!fragment", "#version 150", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "uniform sampler2D Sampler0;", "", "layout(std140) uniform WastedData {", "    vec4 params;", "    vec4 tint;", "    vec4 extra;", "};", "", "#define PROGRESS (params.x)", "#define TIME (params.y)", "#define ASPECT (params.z)", "#define STRENGTH (params.w)", "#define FLASH (extra.x)", "#define RADIAL (extra.y)", "", "float hash12(vec2 p) {", "    vec3 p3 = fract(vec3(p.xyx) * 0.1031);", "    p3 += dot(p3, p3.yzx + 33.33);", "    return fract((p3.x + p3.y) * p3.z);", "}", "", "void main() {", "    vec2 uv = texCoord;", "    vec2 centered = uv - 0.5;", "    centered.x *= ASPECT;", "    float dist = length(centered);", "", "    float squeeze = 1.0 - 0.045 * STRENGTH;", "    vec2 zoomed = (uv - 0.5) * squeeze + 0.5;", "", "    vec3 scene;", "    float radial = RADIAL * (0.25 + 0.75 * smoothstep(0.0, 0.8, dist));", "    if (radial > 0.002) {", "        vec2 toCenter = zoomed - 0.5;", "        vec3 sum = vec3(0.0);", "        float total = 0.0;", "        for (int i = 0; i < 8; i++) {", "            float step = float(i) / 7.0;", "            float weight = 1.0 - step * 0.6;", "            vec2 sampleUv = 0.5 + toCenter * (1.0 - step * 0.16 * radial);", "            sum += texture(Sampler0, sampleUv).rgb * weight;", "            total += weight;", "        }", "        scene = sum / total;", "    } else {", "        scene = texture(Sampler0, zoomed).rgb;", "    }", "", "    float blurAmount = smoothstep(0.22, 0.75, dist) * STRENGTH;", "    if (blurAmount > 0.001) {", "        vec2 texel = vec2(1.0 / max(1.0, ASPECT * 640.0), 1.0 / 640.0) * (2.5 * blurAmount);", "        vec3 sum = scene;", "        sum += texture(Sampler0, zoomed + vec2(texel.x, 0.0)).rgb;", "        sum += texture(Sampler0, zoomed - vec2(texel.x, 0.0)).rgb;", "        sum += texture(Sampler0, zoomed + vec2(0.0, texel.y)).rgb;", "        sum += texture(Sampler0, zoomed - vec2(0.0, texel.y)).rgb;", "        sum += texture(Sampler0, zoomed + texel).rgb;", "        sum += texture(Sampler0, zoomed - texel).rgb;", "        scene = mix(scene, sum / 7.0, blurAmount);", "    }", "", "    float luma = dot(scene, vec3(0.299, 0.587, 0.114));", "    vec3 graded = mix(scene, vec3(luma), STRENGTH);", "", "    float contrast = 1.0 + 0.55 * STRENGTH;", "    graded = clamp((graded - 0.5) * contrast + 0.5, 0.0, 1.0);", "    graded = mix(graded, graded * tint.rgb, STRENGTH * tint.a);", "", "    float vignette = smoothstep(0.95, 0.25, dist);", "    graded *= mix(1.0, vignette, STRENGTH * 0.9);", "", "    float grain = hash12(uv * 720.0 + vec2(TIME * 37.0, TIME * 17.0)) - 0.5;", "    graded += grain * 0.055 * STRENGTH;", "", "    graded += vec3(1.0, 0.97, 0.93) * FLASH;", "    graded = mix(graded, vec3(1.0), FLASH * 0.55);", "", "    float fade = 1.0 - smoothstep(0.82, 1.0, PROGRESS);", "    graded *= mix(1.0, fade, STRENGTH);", "", "    fragColor = vec4(clamp(graded, 0.0, 1.0), 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s1() {
        Object[] objectArray = new String[]{"#version 150", "", "out vec2 texCoord;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    texCoord = uvs[gl_VertexID];", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s2() {
        Object[] objectArray = new String[]{"#version 150", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "uniform sampler2D Sampler0;", "", "layout(std140) uniform WastedData {", "    vec4 params;", "    vec4 tint;", "    vec4 extra;", "};", "", "#define PROGRESS (params.x)", "#define TIME (params.y)", "#define ASPECT (params.z)", "#define STRENGTH (params.w)", "#define FLASH (extra.x)", "#define RADIAL (extra.y)", "", "float hash12(vec2 p) {", "    vec3 p3 = fract(vec3(p.xyx) * 0.1031);", "    p3 += dot(p3, p3.yzx + 33.33);", "    return fract((p3.x + p3.y) * p3.z);", "}", "", "void main() {", "    vec2 uv = texCoord;", "    vec2 centered = uv - 0.5;", "    centered.x *= ASPECT;", "    float dist = length(centered);", "", "    float squeeze = 1.0 - 0.045 * STRENGTH;", "    vec2 zoomed = (uv - 0.5) * squeeze + 0.5;", "", "    vec3 scene;", "    float radial = RADIAL * (0.25 + 0.75 * smoothstep(0.0, 0.8, dist));", "    if (radial > 0.002) {", "        vec2 toCenter = zoomed - 0.5;", "        vec3 sum = vec3(0.0);", "        float total = 0.0;", "        for (int i = 0; i < 8; i++) {", "            float step = float(i) / 7.0;", "            float weight = 1.0 - step * 0.6;", "            vec2 sampleUv = 0.5 + toCenter * (1.0 - step * 0.16 * radial);", "            sum += texture(Sampler0, sampleUv).rgb * weight;", "            total += weight;", "        }", "        scene = sum / total;", "    } else {", "        scene = texture(Sampler0, zoomed).rgb;", "    }", "", "    float blurAmount = smoothstep(0.22, 0.75, dist) * STRENGTH;", "    if (blurAmount > 0.001) {", "        vec2 texel = vec2(1.0 / max(1.0, ASPECT * 640.0), 1.0 / 640.0) * (2.5 * blurAmount);", "        vec3 sum = scene;", "        sum += texture(Sampler0, zoomed + vec2(texel.x, 0.0)).rgb;", "        sum += texture(Sampler0, zoomed - vec2(texel.x, 0.0)).rgb;", "        sum += texture(Sampler0, zoomed + vec2(0.0, texel.y)).rgb;", "        sum += texture(Sampler0, zoomed - vec2(0.0, texel.y)).rgb;", "        sum += texture(Sampler0, zoomed + texel).rgb;", "        sum += texture(Sampler0, zoomed - texel).rgb;", "        scene = mix(scene, sum / 7.0, blurAmount);", "    }", "", "    float luma = dot(scene, vec3(0.299, 0.587, 0.114));", "    vec3 graded = mix(scene, vec3(luma), STRENGTH);", "", "    float contrast = 1.0 + 0.55 * STRENGTH;", "    graded = clamp((graded - 0.5) * contrast + 0.5, 0.0, 1.0);", "    graded = mix(graded, graded * tint.rgb, STRENGTH * tint.a);", "", "    float vignette = smoothstep(0.95, 0.25, dist);", "    graded *= mix(1.0, vignette, STRENGTH * 0.9);", "", "    float grain = hash12(uv * 720.0 + vec2(TIME * 37.0, TIME * 17.0)) - 0.5;", "    graded += grain * 0.055 * STRENGTH;", "", "    graded += vec3(1.0, 0.97, 0.93) * FLASH;", "    graded = mix(graded, vec3(1.0), FLASH * 0.55);", "", "    float fade = 1.0 - smoothstep(0.82, 1.0, PROGRESS);", "    graded *= mix(1.0, fade, STRENGTH);", "", "    fragColor = vec4(clamp(graded, 0.0, 1.0), 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }
}

