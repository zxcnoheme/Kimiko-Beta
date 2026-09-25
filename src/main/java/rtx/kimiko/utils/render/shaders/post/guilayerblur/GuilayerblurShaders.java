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
package rtx.kimiko.utils.render.shaders.post.guilayerblur;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000fJ\u000f\u0010\u0012\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u000fJ\u000f\u0010\u0013\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u000fJ\u000f\u0010\u0014\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u000fJ\u000f\u0010\u0015\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u000fJ\u000f\u0010\u0016\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u000fJ\u000f\u0010\u0017\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u000fJ\u000f\u0010\u0018\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u000fJ\u000f\u0010\u0019\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u000fJ\u000f\u0010\u001a\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u000fJ\u000f\u0010\u001b\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u000fJ\u000f\u0010\u001c\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u000fJ\u000f\u0010\u001d\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u000fJ\u000f\u0010\u001e\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u000fJ\u000f\u0010\u001f\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u001f\u0010\u000fJ\u000f\u0010 \u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b \u0010\u000fJ\u000f\u0010!\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b!\u0010\u000fJ\u000f\u0010\"\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\"\u0010\u000fJ\u000f\u0010#\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b#\u0010\u000f\u00a8\u0006$"}, d2={"Lrtx/kimiko/utils/render/shaders/post/guilayerblur/GuilayerblurShaders;", "", "<init>", "()V", "", "", "sources", "", "Lkotlin/jvm/JvmStatic;", "register", "(Ljava/util/Map;)V", "key", "source", "(Ljava/lang/String;)Ljava/lang/String;", "s0", "()Ljava/lang/String;", "s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "s12", "s13", "s14", "s15", "s16", "s17", "s18", "s19", "s20", "rtx.kimiko:kimiko"})
public final class GuilayerblurShaders {
    @NotNull
    public static final GuilayerblurShaders INSTANCE = new GuilayerblurShaders();

    private GuilayerblurShaders() {
    }

    @JvmStatic
    public static final void register(@NotNull Map<String, String> sources) {
        Intrinsics.checkNotNullParameter(sources, (String)"sources");
        sources.put("post/guilayerblur/composite.glsl", INSTANCE.s0());
        sources.put("post/guilayerblur/composite.fsh", INSTANCE.s1());
        sources.put("post/guilayerblur/composite_world_occluded.glsl", INSTANCE.s2());
        sources.put("post/guilayerblur/composite_world_occluded.fsh", INSTANCE.s3());
        sources.put("post/guilayerblur/fullscreen.glsl", INSTANCE.s4());
        sources.put("post/guilayerblur/fullscreen.vsh", INSTANCE.s5());
        sources.put("post/guilayerblur/gaussian.glsl", INSTANCE.s6());
        sources.put("post/guilayerblur/gaussian.fsh", INSTANCE.s7());
        sources.put("post/guilayerblur/shard.glsl", INSTANCE.s8());
        sources.put("post/guilayerblur/shard.vsh", INSTANCE.s9());
        sources.put("post/guilayerblur/shard.fsh", INSTANCE.s10());
        sources.put("post/guilayerblur/shard_occluded.glsl", INSTANCE.s11());
        sources.put("post/guilayerblur/shard_occluded.fsh", INSTANCE.s12());
        sources.put("post/guilayerblur/slot_blit.glsl", INSTANCE.s13());
        sources.put("post/guilayerblur/slot_blit.fsh", INSTANCE.s14());
        sources.put("post/guilayerblur/world_backdrop.glsl", INSTANCE.s15());
        sources.put("post/guilayerblur/world_backdrop.fsh", INSTANCE.s16());
        sources.put("post/guilayerblur/world_backdrop_slots.glsl", INSTANCE.s17());
        sources.put("post/guilayerblur/world_backdrop_slots.fsh", INSTANCE.s18());
        sources.put("post/guilayerblur/world_quad.glsl", INSTANCE.s19());
        sources.put("post/guilayerblur/world_quad.vsh", INSTANCE.s20());
    }

    @JvmStatic
    @NotNull
    public static final String source(@NotNull String key) {
        Intrinsics.checkNotNullParameter((Object)key, (String)"key");
        return switch (key) {
            case "post/guilayerblur/composite.glsl" -> INSTANCE.s0();
            case "post/guilayerblur/composite.fsh" -> INSTANCE.s1();
            case "post/guilayerblur/composite_world_occluded.glsl" -> INSTANCE.s2();
            case "post/guilayerblur/composite_world_occluded.fsh" -> INSTANCE.s3();
            case "post/guilayerblur/fullscreen.glsl" -> INSTANCE.s4();
            case "post/guilayerblur/fullscreen.vsh" -> INSTANCE.s5();
            case "post/guilayerblur/gaussian.glsl" -> INSTANCE.s6();
            case "post/guilayerblur/gaussian.fsh" -> INSTANCE.s7();
            case "post/guilayerblur/shard.glsl" -> INSTANCE.s8();
            case "post/guilayerblur/shard.vsh" -> INSTANCE.s9();
            case "post/guilayerblur/shard.fsh" -> INSTANCE.s10();
            case "post/guilayerblur/shard_occluded.glsl" -> INSTANCE.s11();
            case "post/guilayerblur/shard_occluded.fsh" -> INSTANCE.s12();
            case "post/guilayerblur/slot_blit.glsl" -> INSTANCE.s13();
            case "post/guilayerblur/slot_blit.fsh" -> INSTANCE.s14();
            case "post/guilayerblur/world_backdrop.glsl" -> INSTANCE.s15();
            case "post/guilayerblur/world_backdrop.fsh" -> INSTANCE.s16();
            case "post/guilayerblur/world_backdrop_slots.glsl" -> INSTANCE.s17();
            case "post/guilayerblur/world_backdrop_slots.fsh" -> INSTANCE.s18();
            case "post/guilayerblur/world_quad.glsl" -> INSTANCE.s19();
            case "post/guilayerblur/world_quad.vsh" -> INSTANCE.s20();
            default -> throw new IllegalArgumentException(key);
        };
    }

    private final String s0() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D uGui;", "", "layout(std140) uniform CompositeData {", "    vec4 Params;", "};", "", "in vec2 texCoord;", "out vec4 outColor;", "", "void main() {", "", "    vec2 uv = vec2(0.5) + (texCoord - vec2(0.5)) * Params.x;", "    if (uv.x < 0.0 || uv.x > 1.0 || uv.y < 0.0 || uv.y > 1.0) {", "        outColor = vec4(0.0);", "        return;", "    }", "    outColor = texture(uGui, uv) * Params.y;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s1() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D uGui;", "", "layout(std140) uniform CompositeData {", "    vec4 Params;", "};", "", "in vec2 texCoord;", "out vec4 outColor;", "", "void main() {", "", "    vec2 uv = vec2(0.5) + (texCoord - vec2(0.5)) * Params.x;", "    if (uv.x < 0.0 || uv.x > 1.0 || uv.y < 0.0 || uv.y > 1.0) {", "        outColor = vec4(0.0);", "        return;", "    }", "    outColor = texture(uGui, uv) * Params.y;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s2() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D uGui;", "uniform sampler2D uDepth;", "uniform sampler2D uHandDepth;", "", "layout(std140) uniform CompositeData {", "    vec4 Params;", "};", "", "in vec2 texCoord;", "out vec4 outColor;", "", "void main() {", "    ivec2 depthPixel = ivec2(gl_FragCoord.xy);", "    float sceneDepth = min(texelFetch(uDepth, depthPixel, 0).r, texelFetch(uHandDepth, depthPixel, 0).r);", "    if (sceneDepth < gl_FragCoord.z - 0.000001) {", "        discard;", "    }", "    vec2 uv = vec2(0.5) + (texCoord - vec2(0.5)) * Params.x;", "    if (uv.x < 0.0 || uv.x > 1.0 || uv.y < 0.0 || uv.y > 1.0) {", "        outColor = vec4(0.0);", "        return;", "    }", "    outColor = texture(uGui, uv) * Params.y;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s3() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D uGui;", "uniform sampler2D uDepth;", "uniform sampler2D uHandDepth;", "", "layout(std140) uniform CompositeData {", "    vec4 Params;", "};", "", "in vec2 texCoord;", "out vec4 outColor;", "", "void main() {", "    ivec2 depthPixel = ivec2(gl_FragCoord.xy);", "    float sceneDepth = min(texelFetch(uDepth, depthPixel, 0).r, texelFetch(uHandDepth, depthPixel, 0).r);", "    if (sceneDepth < gl_FragCoord.z - 0.000001) {", "        discard;", "    }", "    vec2 uv = vec2(0.5) + (texCoord - vec2(0.5)) * Params.x;", "    if (uv.x < 0.0 || uv.x > 1.0 || uv.y < 0.0 || uv.y > 1.0) {", "        outColor = vec4(0.0);", "        return;", "    }", "    outColor = texture(uGui, uv) * Params.y;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s4() {
        Object[] objectArray = new String[]{"//!vertex", "#version 150", "", "out vec2 texCoord;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    texCoord = uvs[gl_VertexID];", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s5() {
        Object[] objectArray = new String[]{"#version 150", "", "out vec2 texCoord;", "", "void main() {", "    vec2 positions[6] = vec2[](", "        vec2(-1.0, -1.0),", "        vec2(1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, -1.0),", "        vec2(1.0, 1.0),", "        vec2(-1.0, 1.0)", "    );", "", "    vec2 uvs[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    gl_Position = vec4(positions[gl_VertexID], 0.0, 1.0);", "    texCoord = uvs[gl_VertexID];", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s6() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D uInput;", "", "layout(std140) uniform BlurData {", "    vec4 Step;", "};", "", "in vec2 texCoord;", "out vec4 outColor;", "", "void main() {", "    vec2 step = Step.xy;", "    vec4 color = vec4(0.0);", "    float total = 0.0;", "    for (int i = -16; i <= 16; i++) {", "        float x = float(i);", "        float w = exp(-(x * x) / 128.0);", "        vec2 uv = clamp(texCoord + step * x, vec2(0.0), vec2(1.0));", "        color += texture(uInput, uv) * w;", "        total += w;", "    }", "    outColor = color / total;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s7() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D uInput;", "", "layout(std140) uniform BlurData {", "    vec4 Step;", "};", "", "in vec2 texCoord;", "out vec4 outColor;", "", "void main() {", "    vec2 step = Step.xy;", "    vec4 color = vec4(0.0);", "    float total = 0.0;", "    for (int i = -16; i <= 16; i++) {", "        float x = float(i);", "        float w = exp(-(x * x) / 128.0);", "        vec2 uv = clamp(texCoord + step * x, vec2(0.0), vec2(1.0));", "        color += texture(uInput, uv) * w;", "        total += w;", "    }", "    outColor = color / total;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s8() {
        Object[] objectArray = new String[]{"//!vertex", "#version 150", "", "in vec3 Position;", "in vec2 UV0;", "in vec4 Color;", "", "layout(std140) uniform WorldQuadData {", "    mat4 WorldMat;", "    vec4 QuadParams;", "    vec4 UvRect;", "};", "", "out vec2 texCoord;", "out vec4 vColor;", "", "void main() {", "    if (QuadParams.z > 0.5) {", "        gl_Position = WorldMat * vec4(Position.xy * QuadParams.xy, Position.z * QuadParams.y, 1.0);", "    } else {", "        gl_Position = vec4(Position.x * 2.0 - 1.0, 1.0 - Position.y * 2.0, 0.0, 1.0);", "    }", "    texCoord = UV0;", "    vColor = Color;", "}", "//!fragment", "#version 150", "", "uniform sampler2D uGui;", "", "in vec2 texCoord;", "in vec4 vColor;", "out vec4 outColor;", "", "void main() {", "    if (texCoord.x < 0.0 || texCoord.x > 1.0 || texCoord.y < 0.0 || texCoord.y > 1.0) {", "        outColor = vec4(0.0);", "        return;", "    }", "    outColor = texture(uGui, texCoord) * vColor.a;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s9() {
        Object[] objectArray = new String[]{"#version 150", "", "in vec3 Position;", "in vec2 UV0;", "in vec4 Color;", "", "layout(std140) uniform WorldQuadData {", "    mat4 WorldMat;", "    vec4 QuadParams;", "    vec4 UvRect;", "};", "", "out vec2 texCoord;", "out vec4 vColor;", "", "void main() {", "    if (QuadParams.z > 0.5) {", "        gl_Position = WorldMat * vec4(Position.xy * QuadParams.xy, Position.z * QuadParams.y, 1.0);", "    } else {", "        gl_Position = vec4(Position.x * 2.0 - 1.0, 1.0 - Position.y * 2.0, 0.0, 1.0);", "    }", "    texCoord = UV0;", "    vColor = Color;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s10() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D uGui;", "", "in vec2 texCoord;", "in vec4 vColor;", "out vec4 outColor;", "", "void main() {", "    if (texCoord.x < 0.0 || texCoord.x > 1.0 || texCoord.y < 0.0 || texCoord.y > 1.0) {", "        outColor = vec4(0.0);", "        return;", "    }", "    outColor = texture(uGui, texCoord) * vColor.a;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s11() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D uGui;", "uniform sampler2D uDepth;", "uniform sampler2D uHandDepth;", "", "in vec2 texCoord;", "in vec4 vColor;", "out vec4 outColor;", "", "void main() {", "    ivec2 depthPixel = ivec2(gl_FragCoord.xy);", "    float sceneDepth = min(texelFetch(uDepth, depthPixel, 0).r, texelFetch(uHandDepth, depthPixel, 0).r);", "    if (sceneDepth < gl_FragCoord.z - 0.000001) {", "        discard;", "    }", "    if (texCoord.x < 0.0 || texCoord.x > 1.0 || texCoord.y < 0.0 || texCoord.y > 1.0) {", "        discard;", "    }", "    outColor = texture(uGui, texCoord) * vColor.a;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s12() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D uGui;", "uniform sampler2D uDepth;", "uniform sampler2D uHandDepth;", "", "in vec2 texCoord;", "in vec4 vColor;", "out vec4 outColor;", "", "void main() {", "    ivec2 depthPixel = ivec2(gl_FragCoord.xy);", "    float sceneDepth = min(texelFetch(uDepth, depthPixel, 0).r, texelFetch(uHandDepth, depthPixel, 0).r);", "    if (sceneDepth < gl_FragCoord.z - 0.000001) {", "        discard;", "    }", "    if (texCoord.x < 0.0 || texCoord.x > 1.0 || texCoord.y < 0.0 || texCoord.y > 1.0) {", "        discard;", "    }", "    outColor = texture(uGui, texCoord) * vColor.a;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s13() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D uGui;", "", "layout(std140) uniform SlotBlitData {", "    vec4 DstRect;", "    vec4 SrcRect;", "    vec4 ClipRect;", "    vec4 BlitMeta;", "};", "", "in vec2 texCoord;", "out vec4 outColor;", "", "void main() {", "    vec2 pixel = vec2(texCoord.x * BlitMeta.x, (1.0 - texCoord.y) * BlitMeta.y);", "    if (pixel.x < DstRect.x || pixel.y < DstRect.y", "            || pixel.x > DstRect.x + DstRect.z || pixel.y > DstRect.y + DstRect.w) {", "        discard;", "    }", "    vec2 local = (pixel - DstRect.xy) / max(DstRect.zw, vec2(1.0));", "    vec2 src = SrcRect.xy + local * SrcRect.zw;", "    if (src.x < ClipRect.x || src.y < ClipRect.y", "            || src.x > ClipRect.x + ClipRect.z || src.y > ClipRect.y + ClipRect.w) {", "        discard;", "    }", "    vec2 uv = vec2(src.x / BlitMeta.x, 1.0 - src.y / BlitMeta.y);", "    outColor = texture(uGui, uv);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s14() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D uGui;", "", "layout(std140) uniform SlotBlitData {", "    vec4 DstRect;", "    vec4 SrcRect;", "    vec4 ClipRect;", "    vec4 BlitMeta;", "};", "", "in vec2 texCoord;", "out vec4 outColor;", "", "void main() {", "    vec2 pixel = vec2(texCoord.x * BlitMeta.x, (1.0 - texCoord.y) * BlitMeta.y);", "    if (pixel.x < DstRect.x || pixel.y < DstRect.y", "            || pixel.x > DstRect.x + DstRect.z || pixel.y > DstRect.y + DstRect.w) {", "        discard;", "    }", "    vec2 local = (pixel - DstRect.xy) / max(DstRect.zw, vec2(1.0));", "    vec2 src = SrcRect.xy + local * SrcRect.zw;", "    if (src.x < ClipRect.x || src.y < ClipRect.y", "            || src.x > ClipRect.x + ClipRect.z || src.y > ClipRect.y + ClipRect.w) {", "        discard;", "    }", "    vec2 uv = vec2(src.x / BlitMeta.x, 1.0 - src.y / BlitMeta.y);", "    outColor = texture(uGui, uv);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s15() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D uGui;", "", "layout(std140) uniform WorldQuadData {", "    mat4 WorldMat;", "    vec4 QuadParams;", "};", "", "in vec2 texCoord;", "out vec4 outColor;", "", "void main() {", "    vec2 pixel = vec2(texCoord.x * QuadParams.x, (1.0 - texCoord.y) * QuadParams.y);", "    vec4 clip = WorldMat * vec4(pixel, 0.0, 1.0);", "    if (clip.w <= 0.05) {", "        outColor = texture(uGui, texCoord);", "        return;", "    }", "    vec2 ndc = clip.xy / clip.w;", "    vec2 uv = clamp(ndc * 0.5 + vec2(0.5), vec2(0.0), vec2(1.0));", "    outColor = texture(uGui, uv);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s16() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D uGui;", "", "layout(std140) uniform WorldQuadData {", "    mat4 WorldMat;", "    vec4 QuadParams;", "};", "", "in vec2 texCoord;", "out vec4 outColor;", "", "void main() {", "    vec2 pixel = vec2(texCoord.x * QuadParams.x, (1.0 - texCoord.y) * QuadParams.y);", "    vec4 clip = WorldMat * vec4(pixel, 0.0, 1.0);", "    if (clip.w <= 0.05) {", "        outColor = texture(uGui, texCoord);", "        return;", "    }", "    vec2 ndc = clip.xy / clip.w;", "    vec2 uv = clamp(ndc * 0.5 + vec2(0.5), vec2(0.0), vec2(1.0));", "    outColor = texture(uGui, uv);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s17() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D uGui;", "", "layout(std140) uniform WorldWarpData {", "    vec4 Meta;", "    mat4 Mats[5];", "    vec4 SlotRect[5];", "    vec4 SlotLocal[5];", "};", "", "in vec2 texCoord;", "out vec4 outColor;", "", "void main() {", "    vec2 pixel = vec2(texCoord.x * Meta.y, (1.0 - texCoord.y) * Meta.z);", "    outColor = texture(uGui, texCoord);", "    int count = int(Meta.x + 0.5);", "    for (int i = 0; i < 5; i++) {", "        if (i >= count) {", "            break;", "        }", "        vec4 rect = SlotRect[i];", "        if (pixel.x < rect.x || pixel.y < rect.y || pixel.x > rect.x + rect.z || pixel.y > rect.y + rect.w) {", "            continue;", "        }", "        vec2 local = (pixel - rect.xy) * SlotLocal[i].xy;", "        vec4 clip = Mats[i] * vec4(local, 0.0, 1.0);", "        if (clip.w <= 0.05) {", "            break;", "        }", "        vec2 uv = clamp(clip.xy / clip.w * 0.5 + vec2(0.5), vec2(0.0), vec2(1.0));", "        outColor = texture(uGui, uv);", "        break;", "    }", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s18() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D uGui;", "", "layout(std140) uniform WorldWarpData {", "    vec4 Meta;", "    mat4 Mats[5];", "    vec4 SlotRect[5];", "    vec4 SlotLocal[5];", "};", "", "in vec2 texCoord;", "out vec4 outColor;", "", "void main() {", "    vec2 pixel = vec2(texCoord.x * Meta.y, (1.0 - texCoord.y) * Meta.z);", "    outColor = texture(uGui, texCoord);", "    int count = int(Meta.x + 0.5);", "    for (int i = 0; i < 5; i++) {", "        if (i >= count) {", "            break;", "        }", "        vec4 rect = SlotRect[i];", "        if (pixel.x < rect.x || pixel.y < rect.y || pixel.x > rect.x + rect.z || pixel.y > rect.y + rect.w) {", "            continue;", "        }", "        vec2 local = (pixel - rect.xy) * SlotLocal[i].xy;", "        vec4 clip = Mats[i] * vec4(local, 0.0, 1.0);", "        if (clip.w <= 0.05) {", "            break;", "        }", "        vec2 uv = clamp(clip.xy / clip.w * 0.5 + vec2(0.5), vec2(0.0), vec2(1.0));", "        outColor = texture(uGui, uv);", "        break;", "    }", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s19() {
        Object[] objectArray = new String[]{"//!vertex", "#version 150", "", "layout(std140) uniform WorldQuadData {", "    mat4 WorldMat;", "    vec4 QuadParams;", "    vec4 UvRect;", "};", "", "out vec2 texCoord;", "", "void main() {", "    vec2 corners[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    vec2 corner = corners[gl_VertexID];", "    gl_Position = WorldMat * vec4(corner * QuadParams.xy, 0.0, 1.0);", "    texCoord = vec2(mix(UvRect.x, UvRect.z, corner.x), mix(UvRect.y, UvRect.w, corner.y));", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s20() {
        Object[] objectArray = new String[]{"#version 150", "", "layout(std140) uniform WorldQuadData {", "    mat4 WorldMat;", "    vec4 QuadParams;", "    vec4 UvRect;", "};", "", "out vec2 texCoord;", "", "void main() {", "    vec2 corners[6] = vec2[](", "        vec2(0.0, 0.0),", "        vec2(1.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 0.0),", "        vec2(1.0, 1.0),", "        vec2(0.0, 1.0)", "    );", "", "    vec2 corner = corners[gl_VertexID];", "    gl_Position = WorldMat * vec4(corner * QuadParams.xy, 0.0, 1.0);", "    texCoord = vec2(mix(UvRect.x, UvRect.z, corner.x), mix(UvRect.y, UvRect.w, corner.y));", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }
}

