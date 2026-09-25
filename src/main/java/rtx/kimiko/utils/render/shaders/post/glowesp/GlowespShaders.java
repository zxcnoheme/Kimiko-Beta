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
package rtx.kimiko.utils.render.shaders.post.glowesp;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000fJ\u000f\u0010\u0012\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u000fJ\u000f\u0010\u0013\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u000fJ\u000f\u0010\u0014\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u000fJ\u000f\u0010\u0015\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u000fJ\u000f\u0010\u0016\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u000fJ\u000f\u0010\u0017\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u000fJ\u000f\u0010\u0018\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u000fJ\u000f\u0010\u0019\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u000fJ\u000f\u0010\u001a\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u000fJ\u000f\u0010\u001b\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u000fJ\u000f\u0010\u001c\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u000f\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/utils/render/shaders/post/glowesp/GlowespShaders;", "", "<init>", "()V", "", "", "sources", "", "Lkotlin/jvm/JvmStatic;", "register", "(Ljava/util/Map;)V", "key", "source", "(Ljava/lang/String;)Ljava/lang/String;", "s0", "()Ljava/lang/String;", "s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "s12", "s13", "rtx.kimiko:kimiko"})
public final class GlowespShaders {
    @NotNull
    public static final GlowespShaders INSTANCE = new GlowespShaders();

    private GlowespShaders() {
    }

    @JvmStatic
    public static final void register(@NotNull Map<String, String> sources) {
        Intrinsics.checkNotNullParameter(sources, (String)"sources");
        sources.put("post/glowesp/blit.glsl", INSTANCE.s0());
        sources.put("post/glowesp/blit.fsh", INSTANCE.s1());
        sources.put("post/glowesp/chams.glsl", INSTANCE.s2());
        sources.put("post/glowesp/chams.fsh", INSTANCE.s3());
        sources.put("post/glowesp/glowesp.glsl", INSTANCE.s4());
        sources.put("post/glowesp/glowesp.vsh", INSTANCE.s5());
        sources.put("post/glowesp/kawase_down.glsl", INSTANCE.s6());
        sources.put("post/glowesp/kawase_down.fsh", INSTANCE.s7());
        sources.put("post/glowesp/kawase_up.glsl", INSTANCE.s8());
        sources.put("post/glowesp/kawase_up.fsh", INSTANCE.s9());
        sources.put("post/glowesp/occlude.glsl", INSTANCE.s10());
        sources.put("post/glowesp/occlude.fsh", INSTANCE.s11());
        sources.put("post/glowesp/outline.glsl", INSTANCE.s12());
        sources.put("post/glowesp/outline.fsh", INSTANCE.s13());
    }

    @JvmStatic
    @NotNull
    public static final String source(@NotNull String key) {
        Intrinsics.checkNotNullParameter((Object)key, (String)"key");
        return switch (key) {
            case "post/glowesp/blit.glsl" -> INSTANCE.s0();
            case "post/glowesp/blit.fsh" -> INSTANCE.s1();
            case "post/glowesp/chams.glsl" -> INSTANCE.s2();
            case "post/glowesp/chams.fsh" -> INSTANCE.s3();
            case "post/glowesp/glowesp.glsl" -> INSTANCE.s4();
            case "post/glowesp/glowesp.vsh" -> INSTANCE.s5();
            case "post/glowesp/kawase_down.glsl" -> INSTANCE.s6();
            case "post/glowesp/kawase_down.fsh" -> INSTANCE.s7();
            case "post/glowesp/kawase_up.glsl" -> INSTANCE.s8();
            case "post/glowesp/kawase_up.fsh" -> INSTANCE.s9();
            case "post/glowesp/occlude.glsl" -> INSTANCE.s10();
            case "post/glowesp/occlude.fsh" -> INSTANCE.s11();
            case "post/glowesp/outline.glsl" -> INSTANCE.s12();
            case "post/glowesp/outline.fsh" -> INSTANCE.s13();
            default -> throw new IllegalArgumentException(key);
        };
    }

    private final String s0() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D InTexture;", "", "in vec2 uv;", "out vec4 color;", "", "void main() {", "    color = texture(InTexture, uv);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s1() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D InTexture;", "", "in vec2 uv;", "out vec4 color;", "", "void main() {", "    color = texture(InTexture, uv);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s2() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D SilhouetteTex;", "uniform sampler2D EntityDepth;", "", "layout(std140) uniform ChamsParams {", "    vec4 Params0;", "    vec4 Rects[32];", "    vec4 Meta[32];", "    vec4 Colors[128];", "};", "", "in vec2 uv;", "out vec4 color;", "", "float linearize(float d, float near, float far) {", "    return (near * far) / max(far - d * (far - near), 1e-6);", "}", "", "vec4 quadGradient(vec2 coord, vec4 topLeft, vec4 topRight, vec4 bottomRight, vec4 bottomLeft) {", "    vec4 top = mix(topLeft, topRight, coord.x);", "    vec4 bottom = mix(bottomLeft, bottomRight, coord.x);", "    return mix(top, bottom, coord.y);", "}", "", "void main() {", "    float coverage = texture(SilhouetteTex, uv).a;", "    if (coverage <= 0.0) {", "        discard;", "    }", "", "    int count = int(Params0.x + 0.5);", "    float near = Params0.y;", "    float far = Params0.z;", "    bool useDepth = Params0.w > 0.5;", "", "    float pixelDepth = useDepth ? linearize(texture(EntityDepth, uv).r, near, far) : 0.0;", "", "    int nearest = -1;", "    int inside = -1;", "    float nearestScore = 1.0e9;", "    float insideScore = 1.0e9;", "", "    for (int i = 0; i < count; i++) {", "        vec4 rect = Rects[i];", "        float score = useDepth", "            ? abs(pixelDepth - linearize(Meta[i].x, near, far))", "            : rect.z * rect.w;", "", "        if (score < nearestScore) {", "            nearestScore = score;", "            nearest = i;", "        }", "", "        if (uv.x >= rect.x && uv.x <= rect.x + rect.z && uv.y >= rect.y && uv.y <= rect.y + rect.w", "            && score < insideScore) {", "            insideScore = score;", "            inside = i;", "        }", "    }", "", "    int pick = inside >= 0 ? inside : nearest;", "", "    vec2 coord = vec2(0.5);", "    int base = 0;", "    if (pick >= 0) {", "        vec4 rect = Rects[pick];", "        coord = (uv - rect.xy) / max(rect.zw, vec2(1.0e-5));", "        base = pick * 4;", "    }", "    coord = clamp(vec2(coord.x, 1.0 - coord.y), vec2(0.0), vec2(1.0));", "", "    vec4 gradient = quadGradient(coord, Colors[base], Colors[base + 1], Colors[base + 2], Colors[base + 3]);", "    color = vec4(gradient.rgb, coverage * gradient.a);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s3() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D SilhouetteTex;", "uniform sampler2D EntityDepth;", "", "layout(std140) uniform ChamsParams {", "    vec4 Params0;", "    vec4 Rects[32];", "    vec4 Meta[32];", "    vec4 Colors[128];", "};", "", "in vec2 uv;", "out vec4 color;", "", "float linearize(float d, float near, float far) {", "    return (near * far) / max(far - d * (far - near), 1e-6);", "}", "", "vec4 quadGradient(vec2 coord, vec4 topLeft, vec4 topRight, vec4 bottomRight, vec4 bottomLeft) {", "    vec4 top = mix(topLeft, topRight, coord.x);", "    vec4 bottom = mix(bottomLeft, bottomRight, coord.x);", "    return mix(top, bottom, coord.y);", "}", "", "void main() {", "    float coverage = texture(SilhouetteTex, uv).a;", "    if (coverage <= 0.0) {", "        discard;", "    }", "", "    int count = int(Params0.x + 0.5);", "    float near = Params0.y;", "    float far = Params0.z;", "    bool useDepth = Params0.w > 0.5;", "", "    float pixelDepth = useDepth ? linearize(texture(EntityDepth, uv).r, near, far) : 0.0;", "", "    int nearest = -1;", "    int inside = -1;", "    float nearestScore = 1.0e9;", "    float insideScore = 1.0e9;", "", "    for (int i = 0; i < count; i++) {", "        vec4 rect = Rects[i];", "        float score = useDepth", "            ? abs(pixelDepth - linearize(Meta[i].x, near, far))", "            : rect.z * rect.w;", "", "        if (score < nearestScore) {", "            nearestScore = score;", "            nearest = i;", "        }", "", "        if (uv.x >= rect.x && uv.x <= rect.x + rect.z && uv.y >= rect.y && uv.y <= rect.y + rect.w", "            && score < insideScore) {", "            insideScore = score;", "            inside = i;", "        }", "    }", "", "    int pick = inside >= 0 ? inside : nearest;", "", "    vec2 coord = vec2(0.5);", "    int base = 0;", "    if (pick >= 0) {", "        vec4 rect = Rects[pick];", "        coord = (uv - rect.xy) / max(rect.zw, vec2(1.0e-5));", "        base = pick * 4;", "    }", "    coord = clamp(vec2(coord.x, 1.0 - coord.y), vec2(0.0), vec2(1.0));", "", "    vec4 gradient = quadGradient(coord, Colors[base], Colors[base + 1], Colors[base + 2], Colors[base + 3]);", "    color = vec4(gradient.rgb, coverage * gradient.a);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s4() {
        Object[] objectArray = new String[]{"//!vertex", "#version 150", "", "out vec2 uv;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    uv = uvs[gl_VertexID];", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s5() {
        Object[] objectArray = new String[]{"#version 150", "", "out vec2 uv;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    uv = uvs[gl_VertexID];", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s6() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D InTexture;", "", "layout(std140) uniform KawaseParams {", "    vec4 Params0;", "    vec4 Params1;", "};", "", "in vec2 uv;", "out vec4 color;", "", "void main() {", "    vec2 offset = Params0.xy;", "    vec2 halfpixel = Params0.zw;", "", "    vec4 sum = texture(InTexture, uv);", "    sum.rgb *= sum.a;", "    sum *= 4.0;", "", "    vec2 offsets[4] = vec2[](", "        vec2(-1.0, 1.0),", "        vec2(1.0, 1.0),", "        vec2(1.0, -1.0),", "        vec2(-1.0, -1.0)", "    );", "", "    for (int i = 0; i < 4; ++i) {", "        vec4 smp = texture(InTexture, uv + offsets[i] * halfpixel * offset);", "        smp.rgb *= smp.a;", "        sum += smp;", "    }", "", "    vec4 result = sum / 8.0;", "    color = vec4(result.rgb / max(result.a, 0.001), result.a);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s7() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D InTexture;", "", "layout(std140) uniform KawaseParams {", "    vec4 Params0;", "    vec4 Params1;", "};", "", "in vec2 uv;", "out vec4 color;", "", "void main() {", "    vec2 offset = Params0.xy;", "    vec2 halfpixel = Params0.zw;", "", "    vec4 sum = texture(InTexture, uv);", "    sum.rgb *= sum.a;", "    sum *= 4.0;", "", "    vec2 offsets[4] = vec2[](", "        vec2(-1.0, 1.0),", "        vec2(1.0, 1.0),", "        vec2(1.0, -1.0),", "        vec2(-1.0, -1.0)", "    );", "", "    for (int i = 0; i < 4; ++i) {", "        vec4 smp = texture(InTexture, uv + offsets[i] * halfpixel * offset);", "        smp.rgb *= smp.a;", "        sum += smp;", "    }", "", "    vec4 result = sum / 8.0;", "    color = vec4(result.rgb / max(result.a, 0.001), result.a);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s8() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D InTexture;", "uniform sampler2D CheckTexture;", "", "layout(std140) uniform KawaseParams {", "    vec4 Params0;", "    vec4 Params1;", "};", "", "in vec2 uv;", "out vec4 color;", "", "void main() {", "    vec2 offset = Params0.xy;", "    vec2 halfpixel = Params0.zw;", "    float divider = Params1.z;", "    float check = Params1.w;", "", "    vec4 sum = vec4(0.0);", "", "    vec2 offsets[8] = vec2[](", "        vec2(-2.0, 0.0),", "        vec2(-1.0, 1.0),", "        vec2(0.0, 2.0),", "        vec2(1.0, 1.0),", "        vec2(2.0, 0.0),", "        vec2(1.0, -1.0),", "        vec2(0.0, -2.0),", "        vec2(-1.0, -1.0)", "    );", "    float weights[8] = float[](1.0, 2.0, 1.0, 2.0, 1.0, 2.0, 1.0, 2.0);", "", "    for (int i = 0; i < 8; ++i) {", "        vec4 smp = texture(InTexture, uv + offsets[i] * halfpixel * offset);", "        smp.rgb *= smp.a;", "        sum += smp * weights[i];", "    }", "", "    vec4 result = sum / max(divider, 0.001);", "    color = vec4(", "        result.rgb / max(result.a, 0.001),", "        mix(result.a, result.a * (1.0 - texture(CheckTexture, uv).a), check)", "    );", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s9() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D InTexture;", "uniform sampler2D CheckTexture;", "", "layout(std140) uniform KawaseParams {", "    vec4 Params0;", "    vec4 Params1;", "};", "", "in vec2 uv;", "out vec4 color;", "", "void main() {", "    vec2 offset = Params0.xy;", "    vec2 halfpixel = Params0.zw;", "    float divider = Params1.z;", "    float check = Params1.w;", "", "    vec4 sum = vec4(0.0);", "", "    vec2 offsets[8] = vec2[](", "        vec2(-2.0, 0.0),", "        vec2(-1.0, 1.0),", "        vec2(0.0, 2.0),", "        vec2(1.0, 1.0),", "        vec2(2.0, 0.0),", "        vec2(1.0, -1.0),", "        vec2(0.0, -2.0),", "        vec2(-1.0, -1.0)", "    );", "    float weights[8] = float[](1.0, 2.0, 1.0, 2.0, 1.0, 2.0, 1.0, 2.0);", "", "    for (int i = 0; i < 8; ++i) {", "        vec4 smp = texture(InTexture, uv + offsets[i] * halfpixel * offset);", "        smp.rgb *= smp.a;", "        sum += smp * weights[i];", "    }", "", "    vec4 result = sum / max(divider, 0.001);", "    color = vec4(", "        result.rgb / max(result.a, 0.001),", "        mix(result.a, result.a * (1.0 - texture(CheckTexture, uv).a), check)", "    );", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s10() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D SilhouetteTex;", "uniform sampler2D EntityDepth;", "uniform sampler2D SceneDepth;", "", "layout(std140) uniform OccludeParams {", "    vec4 P;", "};", "", "in vec2 uv;", "out vec4 color;", "", "float linearize(float d, float near, float far) {", "    return (near * far) / max(far - d * (far - near), 1e-6);", "}", "", "void main() {", "    vec4 silhouette = texture(SilhouetteTex, uv);", "    if (silhouette.a <= 0.0) {", "        discard;", "    }", "", "    float eRaw = texture(EntityDepth, uv).r;", "    if (eRaw >= 0.999999) {", "        discard;", "    }", "", "    float e = linearize(eRaw, P.x, P.y);", "    float s = linearize(texture(SceneDepth, uv).r, P.x, P.y);", "", "    float effBias = P.z + max(e, s) * P.w;", "    float visible = step(e, s + effBias);", "    if (visible <= 0.0) {", "        discard;", "    }", "", "    color = vec4(silhouette.rgb, silhouette.a);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s11() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D SilhouetteTex;", "uniform sampler2D EntityDepth;", "uniform sampler2D SceneDepth;", "", "layout(std140) uniform OccludeParams {", "    vec4 P;", "};", "", "in vec2 uv;", "out vec4 color;", "", "float linearize(float d, float near, float far) {", "    return (near * far) / max(far - d * (far - near), 1e-6);", "}", "", "void main() {", "    vec4 silhouette = texture(SilhouetteTex, uv);", "    if (silhouette.a <= 0.0) {", "        discard;", "    }", "", "    float eRaw = texture(EntityDepth, uv).r;", "    if (eRaw >= 0.999999) {", "        discard;", "    }", "", "    float e = linearize(eRaw, P.x, P.y);", "    float s = linearize(texture(SceneDepth, uv).r, P.x, P.y);", "", "    float effBias = P.z + max(e, s) * P.w;", "    float visible = step(e, s + effBias);", "    if (visible <= 0.0) {", "        discard;", "    }", "", "    color = vec4(silhouette.rgb, silhouette.a);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s12() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D TextureIn;", "uniform sampler2D TextureToCheck;", "", "layout(std140) uniform OutlineParams {", "    vec4 Params0;", "    vec4 Params1;", "};", "", "in vec2 uv;", "out vec4 color;", "", "void main() {", "    vec2 texelSize = Params0.xy;", "    vec2 direction = Params0.zw;", "    float size = Params1.x;", "    vec2 offset = direction * texelSize;", "", "    if (direction.y == 1.0) {", "        if (texture(TextureToCheck, uv).a != 0.0) {", "            discard;", "        }", "    }", "", "    vec4 innerAlpha = texture(TextureIn, uv);", "    innerAlpha *= innerAlpha.a;", "", "    for (float r = 1.0; r <= size; r++) {", "        vec4 first = texture(TextureIn, uv + offset * r);", "        vec4 second = texture(TextureIn, uv - offset * r);", "        first.rgb *= first.a;", "        second.rgb *= second.a;", "        innerAlpha += (first + second) * r;", "    }", "", "    color = vec4(", "        innerAlpha.rgb / max(innerAlpha.a, 0.0001),", "        mix(innerAlpha.a, 1.0 - exp(-innerAlpha.a), step(0.0, direction.y))", "    );", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s13() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D TextureIn;", "uniform sampler2D TextureToCheck;", "", "layout(std140) uniform OutlineParams {", "    vec4 Params0;", "    vec4 Params1;", "};", "", "in vec2 uv;", "out vec4 color;", "", "void main() {", "    vec2 texelSize = Params0.xy;", "    vec2 direction = Params0.zw;", "    float size = Params1.x;", "    vec2 offset = direction * texelSize;", "", "    if (direction.y == 1.0) {", "        if (texture(TextureToCheck, uv).a != 0.0) {", "            discard;", "        }", "    }", "", "    vec4 innerAlpha = texture(TextureIn, uv);", "    innerAlpha *= innerAlpha.a;", "", "    for (float r = 1.0; r <= size; r++) {", "        vec4 first = texture(TextureIn, uv + offset * r);", "        vec4 second = texture(TextureIn, uv - offset * r);", "        first.rgb *= first.a;", "        second.rgb *= second.a;", "        innerAlpha += (first + second) * r;", "    }", "", "    color = vec4(", "        innerAlpha.rgb / max(innerAlpha.a, 0.0001),", "        mix(innerAlpha.a, 1.0 - exp(-innerAlpha.a), step(0.0, direction.y))", "    );", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }
}

