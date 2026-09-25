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
package rtx.kimiko.utils.render.shaders.post.themeshock;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000f\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/utils/render/shaders/post/themeshock/ThemeshockShaders;", "", "<init>", "()V", "", "", "sources", "", "Lkotlin/jvm/JvmStatic;", "register", "(Ljava/util/Map;)V", "key", "source", "(Ljava/lang/String;)Ljava/lang/String;", "s0", "()Ljava/lang/String;", "s1", "s2", "rtx.kimiko:kimiko"})
public final class ThemeshockShaders {
    @NotNull
    public static final ThemeshockShaders INSTANCE = new ThemeshockShaders();

    private ThemeshockShaders() {
    }

    @JvmStatic
    public static final void register(@NotNull Map<String, String> sources) {
        Intrinsics.checkNotNullParameter(sources, (String)"sources");
        sources.put("post/themeshock/themeshock.glsl", INSTANCE.s0());
        sources.put("post/themeshock/themeshock.vsh", INSTANCE.s1());
        sources.put("post/themeshock/themeshock.fsh", INSTANCE.s2());
    }

    @JvmStatic
    @NotNull
    public static final String source(@NotNull String key) {
        Intrinsics.checkNotNullParameter((Object)key, (String)"key");
        return switch (key) {
            case "post/themeshock/themeshock.glsl" -> INSTANCE.s0();
            case "post/themeshock/themeshock.vsh" -> INSTANCE.s1();
            case "post/themeshock/themeshock.fsh" -> INSTANCE.s2();
            default -> throw new IllegalArgumentException(key);
        };
    }

    private final String s0() {
        Object[] objectArray = new String[]{"//!vertex", "#version 150", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "}", "//!fragment", "#version 150", "", "layout(std140) uniform ShockwaveParams {", "    vec4 ShockCenterProgress;", "    vec4 ShockScreenSize;", "};", "", "uniform sampler2D Sampler0;", "", "out vec4 fragColor;", "", "#define PI 3.14159265359", "", "#define force 0.08", "#define aberrationOffset 0.006", "#define flashIntensity 2.5", "", "float easeInOutSine(float t) {", "    return 0.5 - 0.5 * cos(t * PI);", "}", "", "void main() {", "    vec2 screenUV = gl_FragCoord.xy / ShockScreenSize.xy;", "    vec2 center = ShockCenterProgress.xy;", "    float progress = ShockCenterProgress.z;", "    float strength = ShockCenterProgress.w;", "", "    if (progress >= 0.999 || strength <= 0.001) {", "        discard;", "    }", "", "    float thickness = max(ShockScreenSize.z, 0.001);", "    float feathering = max(ShockScreenSize.w, 0.001);", "", "    float aspectRatio = ShockScreenSize.x / ShockScreenSize.y;", "    vec2 aspectVec = vec2(aspectRatio, 1.0);", "", "    vec2 delta = (screenUV - center) * aspectVec;", "    float dist = length(delta);", "", "    vec2 maxCorner = max(center, vec2(1.0) - center) * aspectVec;", "    float maxRadius = length(maxCorner) + thickness + feathering + 0.25;", "", "    float pos = easeInOutSine(progress) * maxRadius;", "", "    float innerBound = smoothstep(", "        pos - thickness - feathering,", "        pos - thickness,", "        dist", "    );", "    float outerBound = smoothstep(", "        pos - feathering,", "        pos,", "        dist", "    );", "    float shapeMask = innerBound - outerBound;", "", "    if (shapeMask <= 0.002) {", "        discard;", "    }", "", "    vec2 r = (dist > 1e-5) ? (delta / dist) : vec2(0.0);", "    vec2 displacement = (r / aspectVec) * force;", "", "    vec2 uvR = screenUV - (displacement - vec2(aberrationOffset)) * shapeMask;", "    vec2 uvG = screenUV - displacement * shapeMask;", "    vec2 uvB = screenUV - (displacement + vec2(aberrationOffset)) * shapeMask;", "", "    float rChannel = texture(Sampler0, uvR).r;", "    float gChannel = texture(Sampler0, uvG).g;", "    float bChannel = texture(Sampler0, uvB).b;", "", "    vec3 color = vec3(rChannel, gChannel, bChannel);", "", "    float invPost = max(0.0, 1.0 - (pos / maxRadius));", "    float flashOpacity = invPost * invPost * invPost * invPost;", "    float flashMask = 1.0 - innerBound;", "    color += flashIntensity * color * flashMask * flashOpacity;", "", "    fragColor = vec4(color, shapeMask * strength);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s1() {
        Object[] objectArray = new String[]{"#version 150", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s2() {
        Object[] objectArray = new String[]{"#version 150", "", "layout(std140) uniform ShockwaveParams {", "    vec4 ShockCenterProgress;", "    vec4 ShockScreenSize;", "};", "", "uniform sampler2D Sampler0;", "", "out vec4 fragColor;", "", "#define PI 3.14159265359", "", "#define force 0.08", "#define aberrationOffset 0.006", "#define flashIntensity 2.5", "", "float easeInOutSine(float t) {", "    return 0.5 - 0.5 * cos(t * PI);", "}", "", "void main() {", "    vec2 screenUV = gl_FragCoord.xy / ShockScreenSize.xy;", "    vec2 center = ShockCenterProgress.xy;", "    float progress = ShockCenterProgress.z;", "    float strength = ShockCenterProgress.w;", "", "    if (progress >= 0.999 || strength <= 0.001) {", "        discard;", "    }", "", "    float thickness = max(ShockScreenSize.z, 0.001);", "    float feathering = max(ShockScreenSize.w, 0.001);", "", "    float aspectRatio = ShockScreenSize.x / ShockScreenSize.y;", "    vec2 aspectVec = vec2(aspectRatio, 1.0);", "", "    vec2 delta = (screenUV - center) * aspectVec;", "    float dist = length(delta);", "", "    vec2 maxCorner = max(center, vec2(1.0) - center) * aspectVec;", "    float maxRadius = length(maxCorner) + thickness + feathering + 0.25;", "", "    float pos = easeInOutSine(progress) * maxRadius;", "", "    float innerBound = smoothstep(", "        pos - thickness - feathering,", "        pos - thickness,", "        dist", "    );", "    float outerBound = smoothstep(", "        pos - feathering,", "        pos,", "        dist", "    );", "    float shapeMask = innerBound - outerBound;", "", "    if (shapeMask <= 0.002) {", "        discard;", "    }", "", "    vec2 r = (dist > 1e-5) ? (delta / dist) : vec2(0.0);", "    vec2 displacement = (r / aspectVec) * force;", "", "    vec2 uvR = screenUV - (displacement - vec2(aberrationOffset)) * shapeMask;", "    vec2 uvG = screenUV - displacement * shapeMask;", "    vec2 uvB = screenUV - (displacement + vec2(aberrationOffset)) * shapeMask;", "", "    float rChannel = texture(Sampler0, uvR).r;", "    float gChannel = texture(Sampler0, uvG).g;", "    float bChannel = texture(Sampler0, uvB).b;", "", "    vec3 color = vec3(rChannel, gChannel, bChannel);", "", "    float invPost = max(0.0, 1.0 - (pos / maxRadius));", "    float flashOpacity = invPost * invPost * invPost * invPost;", "    float flashMask = 1.0 - innerBound;", "    color += flashIntensity * color * flashMask * flashOpacity;", "", "    fragColor = vec4(color, shapeMask * strength);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }
}

