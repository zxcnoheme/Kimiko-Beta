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
package rtx.kimiko.utils.render.shaders.post.itemoutline;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000fJ\u000f\u0010\u0012\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u000fJ\u000f\u0010\u0013\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u000fJ\u000f\u0010\u0014\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u000fJ\u000f\u0010\u0015\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u000fJ\u000f\u0010\u0016\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u000fJ\u000f\u0010\u0017\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u000fJ\u000f\u0010\u0018\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u000fJ\u000f\u0010\u0019\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u000fJ\u000f\u0010\u001a\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u000fJ\u000f\u0010\u001b\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u000fJ\u000f\u0010\u001c\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u000f\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/utils/render/shaders/post/itemoutline/ItemoutlineShaders;", "", "<init>", "()V", "", "", "sources", "", "Lkotlin/jvm/JvmStatic;", "register", "(Ljava/util/Map;)V", "key", "source", "(Ljava/lang/String;)Ljava/lang/String;", "s0", "()Ljava/lang/String;", "s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "s12", "s13", "rtx.kimiko:kimiko"})
public final class ItemoutlineShaders {
    @NotNull
    public static final ItemoutlineShaders INSTANCE = new ItemoutlineShaders();

    private ItemoutlineShaders() {
    }

    @JvmStatic
    public static final void register(@NotNull Map<String, String> sources) {
        Intrinsics.checkNotNullParameter(sources, (String)"sources");
        sources.put("post/itemoutline/blur.glsl", INSTANCE.s0());
        sources.put("post/itemoutline/blur.fsh", INSTANCE.s1());
        sources.put("post/itemoutline/composite.glsl", INSTANCE.s2());
        sources.put("post/itemoutline/composite.fsh", INSTANCE.s3());
        sources.put("post/itemoutline/dt_h.glsl", INSTANCE.s4());
        sources.put("post/itemoutline/dt_h.fsh", INSTANCE.s5());
        sources.put("post/itemoutline/dt_v.glsl", INSTANCE.s6());
        sources.put("post/itemoutline/dt_v.fsh", INSTANCE.s7());
        sources.put("post/itemoutline/isoline.glsl", INSTANCE.s8());
        sources.put("post/itemoutline/isoline.fsh", INSTANCE.s9());
        sources.put("post/itemoutline/itemoutline.glsl", INSTANCE.s10());
        sources.put("post/itemoutline/itemoutline.vsh", INSTANCE.s11());
        sources.put("post/itemoutline/sobel.glsl", INSTANCE.s12());
        sources.put("post/itemoutline/sobel.fsh", INSTANCE.s13());
    }

    @JvmStatic
    @NotNull
    public static final String source(@NotNull String key) {
        Intrinsics.checkNotNullParameter((Object)key, (String)"key");
        return switch (key) {
            case "post/itemoutline/blur.glsl" -> INSTANCE.s0();
            case "post/itemoutline/blur.fsh" -> INSTANCE.s1();
            case "post/itemoutline/composite.glsl" -> INSTANCE.s2();
            case "post/itemoutline/composite.fsh" -> INSTANCE.s3();
            case "post/itemoutline/dt_h.glsl" -> INSTANCE.s4();
            case "post/itemoutline/dt_h.fsh" -> INSTANCE.s5();
            case "post/itemoutline/dt_v.glsl" -> INSTANCE.s6();
            case "post/itemoutline/dt_v.fsh" -> INSTANCE.s7();
            case "post/itemoutline/isoline.glsl" -> INSTANCE.s8();
            case "post/itemoutline/isoline.fsh" -> INSTANCE.s9();
            case "post/itemoutline/itemoutline.glsl" -> INSTANCE.s10();
            case "post/itemoutline/itemoutline.vsh" -> INSTANCE.s11();
            case "post/itemoutline/sobel.glsl" -> INSTANCE.s12();
            case "post/itemoutline/sobel.fsh" -> INSTANCE.s13();
            default -> throw new IllegalArgumentException(key);
        };
    }

    private final String s0() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D InSampler;", "", "layout(std140) uniform OutlineInfo {", "    vec2 InSize;", "    vec2 BlurDir;", "};", "", "layout(std140) uniform BlurConfig {", "    float Radius;", "    float Pad0;", "    float Pad1;", "    float Pad2;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "void main() {", "    vec2 oneTexel = 1.0 / InSize;", "    vec2 sampleStep = oneTexel * BlurDir;", "", "    vec4 blurred = vec4(0.0);", "    float radius = max(Radius, 1.0);", "    float total = 0.0;", "    for (float a = -radius; a <= radius; a += 1.0) {", "", "        float w = 1.0 - abs(a) / (radius + 1.0);", "        blurred += texture(InSampler, texCoord + sampleStep * a) * w;", "        total += w;", "    }", "    fragColor = blurred / max(total, 0.0001);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s1() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D InSampler;", "", "layout(std140) uniform OutlineInfo {", "    vec2 InSize;", "    vec2 BlurDir;", "};", "", "layout(std140) uniform BlurConfig {", "    float Radius;", "    float Pad0;", "    float Pad1;", "    float Pad2;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "void main() {", "    vec2 oneTexel = 1.0 / InSize;", "    vec2 sampleStep = oneTexel * BlurDir;", "", "    vec4 blurred = vec4(0.0);", "    float radius = max(Radius, 1.0);", "    float total = 0.0;", "    for (float a = -radius; a <= radius; a += 1.0) {", "", "        float w = 1.0 - abs(a) / (radius + 1.0);", "        blurred += texture(InSampler, texCoord + sampleStep * a) * w;", "        total += w;", "    }", "    fragColor = blurred / max(total, 0.0001);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s2() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D InSampler;", "", "layout(std140) uniform CompositeConfig {", "    vec4 Color;", "    float Alpha;", "    float Pad0;", "    float Pad1;", "    float Pad2;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "void main() {", "    vec4 rim = texture(InSampler, texCoord);", "", "    float a = clamp(rim.a, 0.0, 1.0) * clamp(Alpha, 0.0, 1.0);", "    if (a <= 0.001) {", "        discard;", "    }", "    fragColor = vec4(Color.rgb, a);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s3() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D InSampler;", "", "layout(std140) uniform CompositeConfig {", "    vec4 Color;", "    float Alpha;", "    float Pad0;", "    float Pad1;", "    float Pad2;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "void main() {", "    vec4 rim = texture(InSampler, texCoord);", "", "    float a = clamp(rim.a, 0.0, 1.0) * clamp(Alpha, 0.0, 1.0);", "    if (a <= 0.001) {", "        discard;", "    }", "    fragColor = vec4(Color.rgb, a);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s4() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D MaskTex;", "", "layout(std140) uniform DtConfig {", "    vec2 TexelSize;", "    float MaxDist;", "    float Radius;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "void main() {", "    if (texture(MaskTex, texCoord).a > 0.003) {", "        fragColor = vec4(0.0, 0.0, 0.0, 1.0);", "        return;", "    }", "    float best = MaxDist;", "    int r = int(Radius);", "    for (int i = 1; i <= 96; i++) {", "        if (i > r) break;", "        float fi = float(i);", "        if (fi >= best) break;", "        if (texture(MaskTex, texCoord + vec2(TexelSize.x * fi, 0.0)).a > 0.003", "                || texture(MaskTex, texCoord - vec2(TexelSize.x * fi, 0.0)).a > 0.003) {", "            best = fi;", "            break;", "        }", "    }", "    fragColor = vec4(best / MaxDist, 0.0, 0.0, 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s5() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D MaskTex;", "", "layout(std140) uniform DtConfig {", "    vec2 TexelSize;", "    float MaxDist;", "    float Radius;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "void main() {", "    if (texture(MaskTex, texCoord).a > 0.003) {", "        fragColor = vec4(0.0, 0.0, 0.0, 1.0);", "        return;", "    }", "    float best = MaxDist;", "    int r = int(Radius);", "    for (int i = 1; i <= 96; i++) {", "        if (i > r) break;", "        float fi = float(i);", "        if (fi >= best) break;", "        if (texture(MaskTex, texCoord + vec2(TexelSize.x * fi, 0.0)).a > 0.003", "                || texture(MaskTex, texCoord - vec2(TexelSize.x * fi, 0.0)).a > 0.003) {", "            best = fi;", "            break;", "        }", "    }", "    fragColor = vec4(best / MaxDist, 0.0, 0.0, 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s6() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D DistH;", "", "layout(std140) uniform DtConfig {", "    vec2 TexelSize;", "    float MaxDist;", "    float Radius;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "void main() {", "    float best = texture(DistH, texCoord).r * MaxDist;", "    int r = int(Radius);", "    for (int j = 1; j <= 96; j++) {", "        if (j > r) break;", "        float fj = float(j);", "        if (fj >= best) break;", "        float dhp = texture(DistH, texCoord + vec2(0.0, TexelSize.y * fj)).r * MaxDist;", "        best = min(best, sqrt(dhp * dhp + fj * fj));", "        float dhn = texture(DistH, texCoord - vec2(0.0, TexelSize.y * fj)).r * MaxDist;", "        best = min(best, sqrt(dhn * dhn + fj * fj));", "    }", "    fragColor = vec4(best / MaxDist, 0.0, 0.0, 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s7() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D DistH;", "", "layout(std140) uniform DtConfig {", "    vec2 TexelSize;", "    float MaxDist;", "    float Radius;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "void main() {", "    float best = texture(DistH, texCoord).r * MaxDist;", "    int r = int(Radius);", "    for (int j = 1; j <= 96; j++) {", "        if (j > r) break;", "        float fj = float(j);", "        if (fj >= best) break;", "        float dhp = texture(DistH, texCoord + vec2(0.0, TexelSize.y * fj)).r * MaxDist;", "        best = min(best, sqrt(dhp * dhp + fj * fj));", "        float dhn = texture(DistH, texCoord - vec2(0.0, TexelSize.y * fj)).r * MaxDist;", "        best = min(best, sqrt(dhn * dhn + fj * fj));", "    }", "    fragColor = vec4(best / MaxDist, 0.0, 0.0, 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s8() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D DistTex;", "", "layout(std140) uniform IsoConfig {", "    float MaxDist;", "    float Thickness;", "    float Alpha;", "    float Phase;", "    vec4 Levels;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "const float TAU = 6.2831853;", "", "void main() {", "    float d = texture(DistTex, texCoord).r * MaxDist;", "    float halfT = Thickness * 0.5;", "    float aa = clamp(fwidth(d), 0.6, 2.0);", "    float outA = 0.0;", "    for (int k = 0; k < 3; k++) {", "        float dd = abs(d - Levels[k]);", "        float lineK = clamp((halfT - dd) / aa + 0.5, 0.0, 1.0);", "        float waveK = 0.5 + 0.5 * sin((Phase - float(k) / 3.0) * TAU);", "        outA = max(outA, lineK * waveK);", "    }", "    float a = outA * Alpha;", "    if (a <= 0.002) {", "        discard;", "    }", "    fragColor = vec4(1.0, 1.0, 1.0, a);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s9() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D DistTex;", "", "layout(std140) uniform IsoConfig {", "    float MaxDist;", "    float Thickness;", "    float Alpha;", "    float Phase;", "    vec4 Levels;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "const float TAU = 6.2831853;", "", "void main() {", "    float d = texture(DistTex, texCoord).r * MaxDist;", "    float halfT = Thickness * 0.5;", "    float aa = clamp(fwidth(d), 0.6, 2.0);", "    float outA = 0.0;", "    for (int k = 0; k < 3; k++) {", "        float dd = abs(d - Levels[k]);", "        float lineK = clamp((halfT - dd) / aa + 0.5, 0.0, 1.0);", "        float waveK = 0.5 + 0.5 * sin((Phase - float(k) / 3.0) * TAU);", "        outA = max(outA, lineK * waveK);", "    }", "    float a = outA * Alpha;", "    if (a <= 0.002) {", "        discard;", "    }", "    fragColor = vec4(1.0, 1.0, 1.0, a);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s10() {
        Object[] objectArray = new String[]{"//!vertex", "#version 150", "", "out vec2 texCoord;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    texCoord = uvs[gl_VertexID];", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s11() {
        Object[] objectArray = new String[]{"#version 150", "", "out vec2 texCoord;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    texCoord = uvs[gl_VertexID];", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s12() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D InSampler;", "", "layout(std140) uniform OutlineInfo {", "    vec2 InSize;", "    vec2 Unused0;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "void main() {", "    vec2 oneTexel = 1.0 / InSize;", "", "    vec4 center = texture(InSampler, texCoord);", "    vec4 left   = texture(InSampler, texCoord - vec2(oneTexel.x, 0.0));", "    vec4 right  = texture(InSampler, texCoord + vec2(oneTexel.x, 0.0));", "    vec4 up     = texture(InSampler, texCoord - vec2(0.0, oneTexel.y));", "    vec4 down   = texture(InSampler, texCoord + vec2(0.0, oneTexel.y));", "", "    float leftDiff  = abs(center.a - left.a);", "    float rightDiff = abs(center.a - right.a);", "    float upDiff    = abs(center.a - up.a);", "    float downDiff  = abs(center.a - down.a);", "    float edge = clamp(leftDiff + rightDiff + upDiff + downDiff, 0.0, 1.0);", "", "    vec3 col = center.rgb * center.a", "             + left.rgb * left.a + right.rgb * right.a", "             + up.rgb * up.a + down.rgb * down.a;", "", "    fragColor = vec4(col * 0.2, edge);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s13() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D InSampler;", "", "layout(std140) uniform OutlineInfo {", "    vec2 InSize;", "    vec2 Unused0;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "void main() {", "    vec2 oneTexel = 1.0 / InSize;", "", "    vec4 center = texture(InSampler, texCoord);", "    vec4 left   = texture(InSampler, texCoord - vec2(oneTexel.x, 0.0));", "    vec4 right  = texture(InSampler, texCoord + vec2(oneTexel.x, 0.0));", "    vec4 up     = texture(InSampler, texCoord - vec2(0.0, oneTexel.y));", "    vec4 down   = texture(InSampler, texCoord + vec2(0.0, oneTexel.y));", "", "    float leftDiff  = abs(center.a - left.a);", "    float rightDiff = abs(center.a - right.a);", "    float upDiff    = abs(center.a - up.a);", "    float downDiff  = abs(center.a - down.a);", "    float edge = clamp(leftDiff + rightDiff + upDiff + downDiff, 0.0, 1.0);", "", "    vec3 col = center.rgb * center.a", "             + left.rgb * left.a + right.rgb * right.a", "             + up.rgb * up.a + down.rgb * down.a;", "", "    fragColor = vec4(col * 0.2, edge);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }
}

