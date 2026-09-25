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
package rtx.kimiko.utils.render.shaders.post.hudlayer;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000fJ\u000f\u0010\u0012\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u000fJ\u000f\u0010\u0013\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u000fJ\u000f\u0010\u0014\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u000f\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/utils/render/shaders/post/hudlayer/HudlayerShaders;", "", "<init>", "()V", "", "", "sources", "", "Lkotlin/jvm/JvmStatic;", "register", "(Ljava/util/Map;)V", "key", "source", "(Ljava/lang/String;)Ljava/lang/String;", "s0", "()Ljava/lang/String;", "s1", "s2", "s3", "s4", "s5", "rtx.kimiko:kimiko"})
public final class HudlayerShaders {
    @NotNull
    public static final HudlayerShaders INSTANCE = new HudlayerShaders();

    private HudlayerShaders() {
    }

    @JvmStatic
    public static final void register(@NotNull Map<String, String> sources) {
        Intrinsics.checkNotNullParameter(sources, (String)"sources");
        sources.put("post/hudlayer/composite.glsl", INSTANCE.s0());
        sources.put("post/hudlayer/composite.fsh", INSTANCE.s1());
        sources.put("post/hudlayer/fullscreen.glsl", INSTANCE.s2());
        sources.put("post/hudlayer/fullscreen.vsh", INSTANCE.s3());
        sources.put("post/hudlayer/gaussian.glsl", INSTANCE.s4());
        sources.put("post/hudlayer/gaussian.fsh", INSTANCE.s5());
    }

    @JvmStatic
    @NotNull
    public static final String source(@NotNull String key) {
        Intrinsics.checkNotNullParameter((Object)key, (String)"key");
        return switch (key) {
            case "post/hudlayer/composite.glsl" -> INSTANCE.s0();
            case "post/hudlayer/composite.fsh" -> INSTANCE.s1();
            case "post/hudlayer/fullscreen.glsl" -> INSTANCE.s2();
            case "post/hudlayer/fullscreen.vsh" -> INSTANCE.s3();
            case "post/hudlayer/gaussian.glsl" -> INSTANCE.s4();
            case "post/hudlayer/gaussian.fsh" -> INSTANCE.s5();
            default -> throw new IllegalArgumentException(key);
        };
    }

    private final String s0() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D uLayer;", "", "layout(std140) uniform HudCompositeData {", "    vec4 Params;", "};", "", "in vec2 texCoord;", "out vec4 outColor;", "", "void main() {", "    outColor = texture(uLayer, texCoord) * Params.x;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s1() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D uLayer;", "", "layout(std140) uniform HudCompositeData {", "    vec4 Params;", "};", "", "in vec2 texCoord;", "out vec4 outColor;", "", "void main() {", "    outColor = texture(uLayer, texCoord) * Params.x;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s2() {
        Object[] objectArray = new String[]{"//!vertex", "#version 150", "", "out vec2 texCoord;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    texCoord = uvs[gl_VertexID];", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s3() {
        Object[] objectArray = new String[]{"#version 150", "", "out vec2 texCoord;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    texCoord = uvs[gl_VertexID];", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s4() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D uInput;", "", "layout(std140) uniform HudBlurData {", "    vec4 Params;", "};", "", "in vec2 texCoord;", "out vec4 outColor;", "", "void main() {", "    vec2 direction = Params.xy;", "    float sigma = Params.z;", "    int radius = int(Params.w);", "", "    if (sigma <= 0.0001) {", "        outColor = texture(uInput, texCoord);", "        return;", "    }", "", "    float twoSigmaSq = 2.0 * sigma * sigma;", "    float invDenom = 1.0 / (3.14159265 * twoSigmaSq);", "    float reach = ceil(3.0 * sigma);", "", "    vec4 acc = texture(uInput, texCoord) * invDenom;", "    float weightSum = invDenom;", "", "    for (int i = 1; i <= 32; i++) {", "        if (i > radius) {", "            break;", "        }", "        if (float(i) > reach) {", "            break;", "        }", "        float w = exp(-float(i * i) / twoSigmaSq) * invDenom;", "        vec2 off = direction * float(i);", "        acc += texture(uInput, texCoord + off) * w;", "        acc += texture(uInput, texCoord - off) * w;", "        weightSum += w * 2.0;", "    }", "", "    outColor = acc / weightSum;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s5() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D uInput;", "", "layout(std140) uniform HudBlurData {", "    vec4 Params;", "};", "", "in vec2 texCoord;", "out vec4 outColor;", "", "void main() {", "    vec2 direction = Params.xy;", "    float sigma = Params.z;", "    int radius = int(Params.w);", "", "    if (sigma <= 0.0001) {", "        outColor = texture(uInput, texCoord);", "        return;", "    }", "", "    float twoSigmaSq = 2.0 * sigma * sigma;", "    float invDenom = 1.0 / (3.14159265 * twoSigmaSq);", "    float reach = ceil(3.0 * sigma);", "", "    vec4 acc = texture(uInput, texCoord) * invDenom;", "    float weightSum = invDenom;", "", "    for (int i = 1; i <= 32; i++) {", "        if (i > radius) {", "            break;", "        }", "        if (float(i) > reach) {", "            break;", "        }", "        float w = exp(-float(i * i) / twoSigmaSq) * invDenom;", "        vec2 off = direction * float(i);", "        acc += texture(uInput, texCoord + off) * w;", "        acc += texture(uInput, texCoord - off) * w;", "        weightSum += w * 2.0;", "    }", "", "    outColor = acc / weightSum;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }
}

