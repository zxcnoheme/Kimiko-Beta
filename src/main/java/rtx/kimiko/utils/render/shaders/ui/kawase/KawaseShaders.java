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
package rtx.kimiko.utils.render.shaders.ui.kawase;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000fJ\u000f\u0010\u0012\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u000fJ\u000f\u0010\u0013\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u000f\u00a8\u0006\u0014"}, d2={"Lrtx/kimiko/utils/render/shaders/ui/kawase/KawaseShaders;", "", "<init>", "()V", "", "", "sources", "", "Lkotlin/jvm/JvmStatic;", "register", "(Ljava/util/Map;)V", "key", "source", "(Ljava/lang/String;)Ljava/lang/String;", "s0", "()Ljava/lang/String;", "s1", "s2", "s3", "s4", "rtx.kimiko:kimiko"})
public final class KawaseShaders {
    @NotNull
    public static final KawaseShaders INSTANCE = new KawaseShaders();

    private KawaseShaders() {
    }

    @JvmStatic
    public static final void register(@NotNull Map<String, String> sources) {
        Intrinsics.checkNotNullParameter(sources, (String)"sources");
        sources.put("ui/kawase/down.glsl", INSTANCE.s0());
        sources.put("ui/kawase/down.vsh", INSTANCE.s1());
        sources.put("ui/kawase/down.fsh", INSTANCE.s2());
        sources.put("ui/kawase/up.glsl", INSTANCE.s3());
        sources.put("ui/kawase/up.fsh", INSTANCE.s4());
    }

    @JvmStatic
    @NotNull
    public static final String source(@NotNull String key) {
        Intrinsics.checkNotNullParameter((Object)key, (String)"key");
        return switch (key) {
            case "ui/kawase/down.glsl" -> INSTANCE.s0();
            case "ui/kawase/down.vsh" -> INSTANCE.s1();
            case "ui/kawase/down.fsh" -> INSTANCE.s2();
            case "ui/kawase/up.glsl" -> INSTANCE.s3();
            case "ui/kawase/up.fsh" -> INSTANCE.s4();
            default -> throw new IllegalArgumentException(key);
        };
    }

    private final String s0() {
        Object[] objectArray = new String[]{"//!vertex", "#version 150", "", "in vec3 Position;", "", "out vec2 TexCoord;", "", "void main() {", "    gl_Position = vec4(Position, 1.0);", "    TexCoord = Position.xy * 0.5 + 0.5;", "}", "//!fragment", "#version 150", "", "uniform sampler2D Sampler0;", "", "layout(std140) uniform KawaseParams {", "    vec4 SourceRect;", "    vec4 HalfPixel;", "    vec4 FallbackColor;", "};", "", "in vec2 TexCoord;", "", "out vec4 OutColor;", "", "vec4 sampleSource(vec2 coord) {", "    vec4 color = texture(Sampler0, coord);", "    float luminance = dot(color.rgb, vec3(0.2126, 0.7152, 0.0722));", "    if (color.a < 0.001 && luminance < 0.003) {", "        return vec4(FallbackColor.rgb, 1.0);", "    }", "    return color;", "}", "", "void main() {", "    vec2 sourceCoord = SourceRect.xy + TexCoord * SourceRect.zw;", "    vec4 sum = sampleSource(sourceCoord) * 4.0;", "    sum += sampleSource(sourceCoord - HalfPixel.xy);", "    sum += sampleSource(sourceCoord + HalfPixel.xy);", "    sum += sampleSource(sourceCoord + vec2(HalfPixel.x, -HalfPixel.y));", "    sum += sampleSource(sourceCoord - vec2(HalfPixel.x, -HalfPixel.y));", "    OutColor = vec4((sum / 8.0).rgb, 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s1() {
        Object[] objectArray = new String[]{"#version 150", "", "in vec3 Position;", "", "out vec2 TexCoord;", "", "void main() {", "    gl_Position = vec4(Position, 1.0);", "    TexCoord = Position.xy * 0.5 + 0.5;", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s2() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D Sampler0;", "", "layout(std140) uniform KawaseParams {", "    vec4 SourceRect;", "    vec4 HalfPixel;", "    vec4 FallbackColor;", "};", "", "in vec2 TexCoord;", "", "out vec4 OutColor;", "", "vec4 sampleSource(vec2 coord) {", "    vec4 color = texture(Sampler0, coord);", "    float luminance = dot(color.rgb, vec3(0.2126, 0.7152, 0.0722));", "    if (color.a < 0.001 && luminance < 0.003) {", "        return vec4(FallbackColor.rgb, 1.0);", "    }", "    return color;", "}", "", "void main() {", "    vec2 sourceCoord = SourceRect.xy + TexCoord * SourceRect.zw;", "    vec4 sum = sampleSource(sourceCoord) * 4.0;", "    sum += sampleSource(sourceCoord - HalfPixel.xy);", "    sum += sampleSource(sourceCoord + HalfPixel.xy);", "    sum += sampleSource(sourceCoord + vec2(HalfPixel.x, -HalfPixel.y));", "    sum += sampleSource(sourceCoord - vec2(HalfPixel.x, -HalfPixel.y));", "    OutColor = vec4((sum / 8.0).rgb, 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s3() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D Sampler0;", "", "layout(std140) uniform KawaseParams {", "    vec4 SourceRect;", "    vec4 HalfPixel;", "    vec4 FallbackColor;", "};", "", "in vec2 TexCoord;", "", "out vec4 OutColor;", "", "vec4 sampleSource(vec2 coord) {", "    vec4 color = texture(Sampler0, coord);", "    float luminance = dot(color.rgb, vec3(0.2126, 0.7152, 0.0722));", "    if (color.a < 0.001 && luminance < 0.003) {", "        return vec4(FallbackColor.rgb, 1.0);", "    }", "    return color;", "}", "", "void main() {", "    vec2 sourceCoord = SourceRect.xy + TexCoord * SourceRect.zw;", "    vec4 sum = sampleSource(sourceCoord + vec2(-HalfPixel.x * 2.0, 0.0));", "    sum += sampleSource(sourceCoord + vec2(-HalfPixel.x, HalfPixel.y)) * 2.0;", "    sum += sampleSource(sourceCoord + vec2(0.0, HalfPixel.y * 2.0));", "    sum += sampleSource(sourceCoord + vec2(HalfPixel.x, HalfPixel.y)) * 2.0;", "    sum += sampleSource(sourceCoord + vec2(HalfPixel.x * 2.0, 0.0));", "    sum += sampleSource(sourceCoord + vec2(HalfPixel.x, -HalfPixel.y)) * 2.0;", "    sum += sampleSource(sourceCoord + vec2(0.0, -HalfPixel.y * 2.0));", "    sum += sampleSource(sourceCoord + vec2(-HalfPixel.x, -HalfPixel.y)) * 2.0;", "    OutColor = vec4((sum / 12.0).rgb, 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s4() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D Sampler0;", "", "layout(std140) uniform KawaseParams {", "    vec4 SourceRect;", "    vec4 HalfPixel;", "    vec4 FallbackColor;", "};", "", "in vec2 TexCoord;", "", "out vec4 OutColor;", "", "vec4 sampleSource(vec2 coord) {", "    vec4 color = texture(Sampler0, coord);", "    float luminance = dot(color.rgb, vec3(0.2126, 0.7152, 0.0722));", "    if (color.a < 0.001 && luminance < 0.003) {", "        return vec4(FallbackColor.rgb, 1.0);", "    }", "    return color;", "}", "", "void main() {", "    vec2 sourceCoord = SourceRect.xy + TexCoord * SourceRect.zw;", "    vec4 sum = sampleSource(sourceCoord + vec2(-HalfPixel.x * 2.0, 0.0));", "    sum += sampleSource(sourceCoord + vec2(-HalfPixel.x, HalfPixel.y)) * 2.0;", "    sum += sampleSource(sourceCoord + vec2(0.0, HalfPixel.y * 2.0));", "    sum += sampleSource(sourceCoord + vec2(HalfPixel.x, HalfPixel.y)) * 2.0;", "    sum += sampleSource(sourceCoord + vec2(HalfPixel.x * 2.0, 0.0));", "    sum += sampleSource(sourceCoord + vec2(HalfPixel.x, -HalfPixel.y)) * 2.0;", "    sum += sampleSource(sourceCoord + vec2(0.0, -HalfPixel.y * 2.0));", "    sum += sampleSource(sourceCoord + vec2(-HalfPixel.x, -HalfPixel.y)) * 2.0;", "    OutColor = vec4((sum / 12.0).rgb, 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }
}

