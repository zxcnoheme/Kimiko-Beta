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
package rtx.kimiko.utils.render.shaders.effects.lyrics_text;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000fJ\u000f\u0010\u0012\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u000fJ\u000f\u0010\u0013\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u000fJ\u000f\u0010\u0014\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u000fJ\u000f\u0010\u0015\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u000f\u00a8\u0006\u0016"}, d2={"Lrtx/kimiko/utils/render/shaders/effects/lyrics_text/LyricsTextShaders;", "", "<init>", "()V", "", "", "sources", "", "Lkotlin/jvm/JvmStatic;", "register", "(Ljava/util/Map;)V", "key", "source", "(Ljava/lang/String;)Ljava/lang/String;", "s0", "()Ljava/lang/String;", "s1", "s2", "s3", "s4", "s5", "s6", "rtx.kimiko:kimiko"})
public final class LyricsTextShaders {
    @NotNull
    public static final LyricsTextShaders INSTANCE = new LyricsTextShaders();

    private LyricsTextShaders() {
    }

    @JvmStatic
    public static final void register(@NotNull Map<String, String> sources) {
        Intrinsics.checkNotNullParameter(sources, (String)"sources");
        sources.put("effects/lyrics_text/lyrics_glow.glsl", INSTANCE.s0());
        sources.put("effects/lyrics_text/lyrics_glow.fsh", INSTANCE.s1());
        sources.put("effects/lyrics_text/lyrics_glow_composite.glsl", INSTANCE.s2());
        sources.put("effects/lyrics_text/lyrics_glow_composite.fsh", INSTANCE.s3());
        sources.put("effects/lyrics_text/lyrics_text.glsl", INSTANCE.s4());
        sources.put("effects/lyrics_text/lyrics_text.vsh", INSTANCE.s5());
        sources.put("effects/lyrics_text/lyrics_text.fsh", INSTANCE.s6());
    }

    @JvmStatic
    @NotNull
    public static final String source(@NotNull String key) {
        Intrinsics.checkNotNullParameter((Object)key, (String)"key");
        return switch (key) {
            case "effects/lyrics_text/lyrics_glow.glsl" -> INSTANCE.s0();
            case "effects/lyrics_text/lyrics_glow.fsh" -> INSTANCE.s1();
            case "effects/lyrics_text/lyrics_glow_composite.glsl" -> INSTANCE.s2();
            case "effects/lyrics_text/lyrics_glow_composite.fsh" -> INSTANCE.s3();
            case "effects/lyrics_text/lyrics_text.glsl" -> INSTANCE.s4();
            case "effects/lyrics_text/lyrics_text.vsh" -> INSTANCE.s5();
            case "effects/lyrics_text/lyrics_text.fsh" -> INSTANCE.s6();
            default -> throw new IllegalArgumentException(key);
        };
    }

    private final String s0() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D Atlas;", "", "layout(std140) uniform LyricsTextData {", "    mat4 ViewProjection;", "    vec4 Palette;", "};", "", "layout(std140) uniform LyricsGlyphArray {", "    vec4 GlyphBounds[256];", "    vec4 GlyphEffect[256];", "};", "", "in vec2 atlasUv;", "in vec4 atlasBounds;", "in vec4 glyphEffect;", "", "out vec4 fragColor;", "", "float median(float r, float g, float b) {", "    return max(min(r, g), min(max(r, g), b));", "}", "", "void main() {", "    vec2 inside = step(atlasBounds.xy, atlasUv) * step(atlasUv, atlasBounds.zw);", "    vec4 texel = texture(Atlas, atlasUv);", "    vec2 atlasSize = vec2(textureSize(Atlas, 0));", "    vec2 unitRange = vec2(4.0) / atlasSize;", "    vec2 footprint = abs(dFdx(atlasUv)) + abs(dFdy(atlasUv));", "    float screenRange = max(0.5 * dot(unitRange, vec2(1.0) / max(footprint, vec2(1.0e-6))), 1.0);", "    float shape = clamp((median(texel.r, texel.g, texel.b) - 0.5) * screenRange + 0.5, 0.0, 1.0);", "    float heat = clamp(glyphEffect.y, 0.0, 1.0);", "    float eased = heat * heat * (3.0 - 2.0 * heat);", "    float energy = shape * inside.x * inside.y * eased * glyphEffect.z;", "    if (energy <= 0.003) {", "        discard;", "    }", "    fragColor = vec4(vec3(energy), energy);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s1() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D Atlas;", "", "layout(std140) uniform LyricsTextData {", "    mat4 ViewProjection;", "    vec4 Palette;", "};", "", "layout(std140) uniform LyricsGlyphArray {", "    vec4 GlyphBounds[256];", "    vec4 GlyphEffect[256];", "};", "", "in vec2 atlasUv;", "in vec4 atlasBounds;", "in vec4 glyphEffect;", "", "out vec4 fragColor;", "", "float median(float r, float g, float b) {", "    return max(min(r, g), min(max(r, g), b));", "}", "", "void main() {", "    vec2 inside = step(atlasBounds.xy, atlasUv) * step(atlasUv, atlasBounds.zw);", "    vec4 texel = texture(Atlas, atlasUv);", "    vec2 atlasSize = vec2(textureSize(Atlas, 0));", "    vec2 unitRange = vec2(4.0) / atlasSize;", "    vec2 footprint = abs(dFdx(atlasUv)) + abs(dFdy(atlasUv));", "    float screenRange = max(0.5 * dot(unitRange, vec2(1.0) / max(footprint, vec2(1.0e-6))), 1.0);", "    float shape = clamp((median(texel.r, texel.g, texel.b) - 0.5) * screenRange + 0.5, 0.0, 1.0);", "    float heat = clamp(glyphEffect.y, 0.0, 1.0);", "    float eased = heat * heat * (3.0 - 2.0 * heat);", "    float energy = shape * inside.x * inside.y * eased * glyphEffect.z;", "    if (energy <= 0.003) {", "        discard;", "    }", "    fragColor = vec4(vec3(energy), energy);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s2() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D Sampler0;", "", "layout(std140) uniform LyricsGlowParams {", "    vec4 Params;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "void main() {", "    vec3 bloom = texture(Sampler0, texCoord).rgb;", "    float strength = Params.x;", "    vec3 lifted = bloom * strength;", "    fragColor = vec4(lifted / (1.0 + lifted), 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s3() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D Sampler0;", "", "layout(std140) uniform LyricsGlowParams {", "    vec4 Params;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "void main() {", "    vec3 bloom = texture(Sampler0, texCoord).rgb;", "    float strength = Params.x;", "    vec3 lifted = bloom * strength;", "    fragColor = vec4(lifted / (1.0 + lifted), 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s4() {
        Object[] objectArray = new String[]{"//!vertex", "#version 150", "", "layout(std140) uniform LyricsTextData {", "    mat4 ViewProjection;", "    vec4 Palette;", "};", "", "layout(std140) uniform LyricsGlyphArray {", "    vec4 GlyphBounds[256];", "    vec4 GlyphEffect[256];", "};", "", "in vec3 Position;", "in vec2 UV0;", "in float LineWidth;", "", "out vec2 atlasUv;", "out vec4 atlasBounds;", "out vec4 glyphEffect;", "", "void main() {", "    int slot = int(LineWidth + 0.5);", "    gl_Position = ViewProjection * vec4(Position, 1.0);", "    atlasUv = UV0;", "    atlasBounds = GlyphBounds[slot];", "    glyphEffect = GlyphEffect[slot];", "}", "//!fragment", "#version 150", "", "uniform sampler2D Atlas;", "", "layout(std140) uniform LyricsTextData {", "    mat4 ViewProjection;", "    vec4 Palette;", "};", "", "in vec2 atlasUv;", "in vec4 atlasBounds;", "in vec4 glyphEffect;", "", "out vec4 fragColor;", "", "const vec2 INNER_RING[8] = vec2[](", "    vec2(0.5500, 0.0000),", "    vec2(0.3889, 0.3889),", "    vec2(0.0000, 0.5500),", "    vec2(-0.3889, 0.3889),", "    vec2(-0.5500, 0.0000),", "    vec2(-0.3889, -0.3889),", "    vec2(0.0000, -0.5500),", "    vec2(0.3889, -0.3889)", ");", "", "const vec2 OUTER_RING[8] = vec2[](", "    vec2(0.9239, 0.3827),", "    vec2(0.3827, 0.9239),", "    vec2(-0.3827, 0.9239),", "    vec2(-0.9239, 0.3827),", "    vec2(-0.9239, -0.3827),", "    vec2(-0.3827, -0.9239),", "    vec2(0.3827, -0.9239),", "    vec2(0.9239, -0.3827)", ");", "", "const float CENTER_WEIGHT = 0.42;", "const float INNER_WEIGHT = 0.62;", "const float OUTER_WEIGHT = 0.24;", "const float BLUR_GAIN = 1.35;", "const float EPSILON = 0.0000015;", "const float DISTANCE_RANGE = 4.0;", "", "float screenRange = 1.0;", "", "float median(float r, float g, float b) {", "    return max(min(r, g), min(max(r, g), b));", "}", "", "float coverage(vec2 uv) {", "    vec2 inside = step(atlasBounds.xy, uv) * step(uv, atlasBounds.zw);", "    vec4 texel = texture(Atlas, uv);", "    float distance = median(texel.r, texel.g, texel.b);", "    float shape = clamp((distance - 0.5) * screenRange + 0.5, 0.0, 1.0);", "    return shape * inside.x * inside.y;", "}", "", "void main() {", "    vec2 atlasSize = vec2(textureSize(Atlas, 0));", "    vec2 unitRange = vec2(DISTANCE_RANGE) / atlasSize;", "    vec2 footprint = abs(dFdx(atlasUv)) + abs(dFdy(atlasUv));", "    screenRange = max(0.5 * dot(unitRange, vec2(1.0) / max(footprint, vec2(1.0e-6))), 1.0);", "", "    float radius = glyphEffect.x;", "    float shape;", "", "    if (radius <= EPSILON) {", "        shape = coverage(atlasUv);", "    } else {", "        float sum = coverage(atlasUv) * CENTER_WEIGHT;", "        for (int index = 0; index < 8; index++) {", "            sum += coverage(atlasUv + INNER_RING[index] * radius) * INNER_WEIGHT;", "            sum += coverage(atlasUv + OUTER_RING[index] * radius) * OUTER_WEIGHT;", "        }", "        shape = sum / (CENTER_WEIGHT + 8.0 * (INNER_WEIGHT + OUTER_WEIGHT));", "        shape *= 1.0 + BLUR_GAIN * min(1.0, radius / max(Palette.w, EPSILON));", "    }", "    shape = clamp(shape, 0.0, 1.0);", "", "    float heat = clamp(glyphEffect.y, 0.0, 1.0);", "    float eased = heat * heat * (3.0 - 2.0 * heat);", "    float alpha = min(shape * glyphEffect.z * (1.0 + eased * Palette.y), 1.0);", "", "    float halo = 0.0;", "    if (eased > 0.004) {", "        vec2 insideCenter = step(atlasBounds.xy, atlasUv) * step(atlasUv, atlasBounds.zw);", "        vec4 centerTexel = texture(Atlas, atlasUv);", "        float field = median(centerTexel.r, centerTexel.g, centerTexel.b) * insideCenter.x * insideCenter.y;", "        float ring = smoothstep(0.0, 0.5, field);", "        halo = ring * ring * eased * Palette.z * glyphEffect.z;", "    }", "", "    if (alpha <= 0.004 && halo <= 0.004) {", "        discard;", "    }", "", "    vec3 tint = mix(vec3(Palette.x), vec3(1.0), eased);", "    vec3 glow = vec3(halo * (1.0 - alpha) * 0.85);", "    fragColor = vec4(tint * alpha + glow, alpha);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s5() {
        Object[] objectArray = new String[]{"#version 150", "", "layout(std140) uniform LyricsTextData {", "    mat4 ViewProjection;", "    vec4 Palette;", "};", "", "layout(std140) uniform LyricsGlyphArray {", "    vec4 GlyphBounds[256];", "    vec4 GlyphEffect[256];", "};", "", "in vec3 Position;", "in vec2 UV0;", "in float LineWidth;", "", "out vec2 atlasUv;", "out vec4 atlasBounds;", "out vec4 glyphEffect;", "", "void main() {", "    int slot = int(LineWidth + 0.5);", "    gl_Position = ViewProjection * vec4(Position, 1.0);", "    atlasUv = UV0;", "    atlasBounds = GlyphBounds[slot];", "    glyphEffect = GlyphEffect[slot];", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s6() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D Atlas;", "", "layout(std140) uniform LyricsTextData {", "    mat4 ViewProjection;", "    vec4 Palette;", "};", "", "in vec2 atlasUv;", "in vec4 atlasBounds;", "in vec4 glyphEffect;", "", "out vec4 fragColor;", "", "const vec2 INNER_RING[8] = vec2[](", "    vec2(0.5500, 0.0000),", "    vec2(0.3889, 0.3889),", "    vec2(0.0000, 0.5500),", "    vec2(-0.3889, 0.3889),", "    vec2(-0.5500, 0.0000),", "    vec2(-0.3889, -0.3889),", "    vec2(0.0000, -0.5500),", "    vec2(0.3889, -0.3889)", ");", "", "const vec2 OUTER_RING[8] = vec2[](", "    vec2(0.9239, 0.3827),", "    vec2(0.3827, 0.9239),", "    vec2(-0.3827, 0.9239),", "    vec2(-0.9239, 0.3827),", "    vec2(-0.9239, -0.3827),", "    vec2(-0.3827, -0.9239),", "    vec2(0.3827, -0.9239),", "    vec2(0.9239, -0.3827)", ");", "", "const float CENTER_WEIGHT = 0.42;", "const float INNER_WEIGHT = 0.62;", "const float OUTER_WEIGHT = 0.24;", "const float BLUR_GAIN = 1.35;", "const float EPSILON = 0.0000015;", "const float DISTANCE_RANGE = 4.0;", "", "float screenRange = 1.0;", "", "float median(float r, float g, float b) {", "    return max(min(r, g), min(max(r, g), b));", "}", "", "float coverage(vec2 uv) {", "    vec2 inside = step(atlasBounds.xy, uv) * step(uv, atlasBounds.zw);", "    vec4 texel = texture(Atlas, uv);", "    float distance = median(texel.r, texel.g, texel.b);", "    float shape = clamp((distance - 0.5) * screenRange + 0.5, 0.0, 1.0);", "    return shape * inside.x * inside.y;", "}", "", "void main() {", "    vec2 atlasSize = vec2(textureSize(Atlas, 0));", "    vec2 unitRange = vec2(DISTANCE_RANGE) / atlasSize;", "    vec2 footprint = abs(dFdx(atlasUv)) + abs(dFdy(atlasUv));", "    screenRange = max(0.5 * dot(unitRange, vec2(1.0) / max(footprint, vec2(1.0e-6))), 1.0);", "", "    float radius = glyphEffect.x;", "    float shape;", "", "    if (radius <= EPSILON) {", "        shape = coverage(atlasUv);", "    } else {", "        float sum = coverage(atlasUv) * CENTER_WEIGHT;", "        for (int index = 0; index < 8; index++) {", "            sum += coverage(atlasUv + INNER_RING[index] * radius) * INNER_WEIGHT;", "            sum += coverage(atlasUv + OUTER_RING[index] * radius) * OUTER_WEIGHT;", "        }", "        shape = sum / (CENTER_WEIGHT + 8.0 * (INNER_WEIGHT + OUTER_WEIGHT));", "        shape *= 1.0 + BLUR_GAIN * min(1.0, radius / max(Palette.w, EPSILON));", "    }", "    shape = clamp(shape, 0.0, 1.0);", "", "    float heat = clamp(glyphEffect.y, 0.0, 1.0);", "    float eased = heat * heat * (3.0 - 2.0 * heat);", "    float alpha = min(shape * glyphEffect.z * (1.0 + eased * Palette.y), 1.0);", "", "    float halo = 0.0;", "    if (eased > 0.004) {", "        vec2 insideCenter = step(atlasBounds.xy, atlasUv) * step(atlasUv, atlasBounds.zw);", "        vec4 centerTexel = texture(Atlas, atlasUv);", "        float field = median(centerTexel.r, centerTexel.g, centerTexel.b) * insideCenter.x * insideCenter.y;", "        float ring = smoothstep(0.0, 0.5, field);", "        halo = ring * ring * eased * Palette.z * glyphEffect.z;", "    }", "", "    if (alpha <= 0.004 && halo <= 0.004) {", "        discard;", "    }", "", "    vec3 tint = mix(vec3(Palette.x), vec3(1.0), eased);", "    vec3 glow = vec3(halo * (1.0 - alpha) * 0.85);", "    fragColor = vec4(tint * alpha + glow, alpha);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }
}

