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
package rtx.kimiko.utils.render.shaders.post.fogblur;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000fJ\u000f\u0010\u0012\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u000fJ\u000f\u0010\u0013\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u000fJ\u000f\u0010\u0014\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u000f\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/utils/render/shaders/post/fogblur/FogblurShaders;", "", "<init>", "()V", "", "", "sources", "", "Lkotlin/jvm/JvmStatic;", "register", "(Ljava/util/Map;)V", "key", "source", "(Ljava/lang/String;)Ljava/lang/String;", "s0", "()Ljava/lang/String;", "s1", "s2", "s3", "s4", "s5", "rtx.kimiko:kimiko"})
public final class FogblurShaders {
    @NotNull
    public static final FogblurShaders INSTANCE = new FogblurShaders();

    private FogblurShaders() {
    }

    @JvmStatic
    public static final void register(@NotNull Map<String, String> sources) {
        Intrinsics.checkNotNullParameter(sources, (String)"sources");
        sources.put("post/fogblur/composite.fsh", INSTANCE.s0());
        sources.put("post/fogblur/fogblur.vsh", INSTANCE.s1());
        sources.put("post/fogblur/kawase.vsh", INSTANCE.s2());
        sources.put("post/fogblur/kawase_down.fsh", INSTANCE.s3());
        sources.put("post/fogblur/kawase_up.fsh", INSTANCE.s4());
        sources.put("post/fogblur/prepare.fsh", INSTANCE.s5());
    }

    @JvmStatic
    @NotNull
    public static final String source(@NotNull String key) {
        Intrinsics.checkNotNullParameter((Object)key, (String)"key");
        return switch (key) {
            case "post/fogblur/composite.fsh" -> INSTANCE.s0();
            case "post/fogblur/fogblur.vsh" -> INSTANCE.s1();
            case "post/fogblur/kawase.vsh" -> INSTANCE.s2();
            case "post/fogblur/kawase_down.fsh" -> INSTANCE.s3();
            case "post/fogblur/kawase_up.fsh" -> INSTANCE.s4();
            case "post/fogblur/prepare.fsh" -> INSTANCE.s5();
            default -> throw new IllegalArgumentException(key);
        };
    }

    private final String s0() {
        Object[] objectArray = new String[]{"#version 150", "", "#moj_import <kimiko:theme_wave.glsl>", "", "uniform sampler2D BlurMediumSampler;", "uniform sampler2D BlurStrongSampler;", "uniform sampler2D DepthSampler;", "", "layout(std140) uniform FogBlurData {", "    vec4 BlurData;", "    vec4 FogData;", "    vec4 Reserved;", "    vec4 TintExtra;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "float linearizeDepth(float depth, float nearPlane, float farPlane) {", "    return (2.0 * nearPlane * farPlane) / (farPlane + nearPlane - depth * (farPlane - nearPlane));", "}", "", "float hazeFor(float depth) {", "    if (depth >= 0.99995) {", "        return 1.0;", "    }", "    float linearDistance = linearizeDepth(depth, FogData.x, FogData.y) / FogData.y;", "    return smoothstep(FogData.z, FogData.w, linearDistance);", "}", "", "vec3 unpackFog(vec4 premultiplied) {", "    return premultiplied.rgb / max(premultiplied.a, 0.0001);", "}", "", "void main() {", "    float haze = hazeFor(texture(DepthSampler, texCoord).r);", "    if (haze <= 0.002) {", "        fragColor = vec4(0.0);", "        return;", "    }", "", "    vec3 medium = unpackFog(texture(BlurMediumSampler, texCoord));", "    vec3 strong = unpackFog(texture(BlurStrongSampler, texCoord));", "    vec3 blurred = mix(medium, strong, haze * haze);", "", "    vec3 tint = TintExtra.x > 0.5 ? kimikoClientPrimary(gl_FragCoord.xy) : Reserved.rgb;", "    blurred = mix(blurred, tint, clamp(Reserved.a, 0.0, 1.0) * haze);", "    fragColor = vec4(blurred, clamp(haze * BlurData.w, 0.0, 1.0));", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s1() {
        Object[] objectArray = new String[]{"#version 150", "", "out vec2 texCoord;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    texCoord = uvs[gl_VertexID];", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s2() {
        Object[] objectArray = new String[]{"#version 150", "", "out vec2 TexCoord;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    TexCoord = uvs[gl_VertexID];", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s3() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D Sampler0;", "", "layout(std140) uniform KawaseParams {", "    vec4 SourceRect;", "    vec4 HalfPixel;", "    vec4 FallbackColor;", "};", "", "in vec2 TexCoord;", "", "out vec4 OutColor;", "", "vec4 sampleSource(vec2 coord) {", "    return texture(Sampler0, clamp(coord, vec2(0.0), vec2(1.0)));", "}", "", "void main() {", "    vec2 sourceCoord = SourceRect.xy + TexCoord * SourceRect.zw;", "    vec4 sum = sampleSource(sourceCoord) * 4.0;", "    sum += sampleSource(sourceCoord - HalfPixel.xy);", "    sum += sampleSource(sourceCoord + HalfPixel.xy);", "    sum += sampleSource(sourceCoord + vec2(HalfPixel.x, -HalfPixel.y));", "    sum += sampleSource(sourceCoord - vec2(HalfPixel.x, -HalfPixel.y));", "    OutColor = sum / 8.0;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s4() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D Sampler0;", "", "layout(std140) uniform KawaseParams {", "    vec4 SourceRect;", "    vec4 HalfPixel;", "    vec4 FallbackColor;", "};", "", "in vec2 TexCoord;", "", "out vec4 OutColor;", "", "vec4 sampleSource(vec2 coord) {", "    return texture(Sampler0, clamp(coord, vec2(0.0), vec2(1.0)));", "}", "", "void main() {", "    vec2 sourceCoord = SourceRect.xy + TexCoord * SourceRect.zw;", "    vec4 sum = sampleSource(sourceCoord + vec2(-HalfPixel.x * 2.0, 0.0));", "    sum += sampleSource(sourceCoord + vec2(-HalfPixel.x, HalfPixel.y)) * 2.0;", "    sum += sampleSource(sourceCoord + vec2(0.0, HalfPixel.y * 2.0));", "    sum += sampleSource(sourceCoord + vec2(HalfPixel.x, HalfPixel.y)) * 2.0;", "    sum += sampleSource(sourceCoord + vec2(HalfPixel.x * 2.0, 0.0));", "    sum += sampleSource(sourceCoord + vec2(HalfPixel.x, -HalfPixel.y)) * 2.0;", "    sum += sampleSource(sourceCoord + vec2(0.0, -HalfPixel.y * 2.0));", "    sum += sampleSource(sourceCoord + vec2(-HalfPixel.x, -HalfPixel.y)) * 2.0;", "    OutColor = sum / 12.0;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s5() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D SceneSampler;", "uniform sampler2D DepthSampler;", "", "layout(std140) uniform FogBlurData {", "    vec4 BlurData;", "    vec4 FogData;", "    vec4 Reserved;", "    vec4 TintExtra;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "float linearizeDepth(float depth, float nearPlane, float farPlane) {", "    return (2.0 * nearPlane * farPlane) / (farPlane + nearPlane - depth * (farPlane - nearPlane));", "}", "", "float hazeFor(float depth) {", "    if (depth >= 0.99995) {", "        return 1.0;", "    }", "    float linearDistance = linearizeDepth(depth, FogData.x, FogData.y) / FogData.y;", "    return smoothstep(FogData.z, FogData.w, linearDistance);", "}", "", "void main() {", "    float haze = hazeFor(texture(DepthSampler, texCoord).r);", "    fragColor = vec4(texture(SceneSampler, texCoord).rgb * haze, haze);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }
}

