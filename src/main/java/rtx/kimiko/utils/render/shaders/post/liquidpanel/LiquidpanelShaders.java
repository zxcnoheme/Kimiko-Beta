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
package rtx.kimiko.utils.render.shaders.post.liquidpanel;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000f\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/utils/render/shaders/post/liquidpanel/LiquidpanelShaders;", "", "<init>", "()V", "", "", "sources", "", "Lkotlin/jvm/JvmStatic;", "register", "(Ljava/util/Map;)V", "key", "source", "(Ljava/lang/String;)Ljava/lang/String;", "s0", "()Ljava/lang/String;", "s1", "rtx.kimiko:kimiko"})
public final class LiquidpanelShaders {
    @NotNull
    public static final LiquidpanelShaders INSTANCE = new LiquidpanelShaders();

    private LiquidpanelShaders() {
    }

    @JvmStatic
    public static final void register(@NotNull Map<String, String> sources) {
        Intrinsics.checkNotNullParameter(sources, (String)"sources");
        sources.put("post/liquidpanel/liquidpanel.glsl", INSTANCE.s0());
        sources.put("post/liquidpanel/liquidpanel.fsh", INSTANCE.s1());
    }

    @JvmStatic
    @NotNull
    public static final String source(@NotNull String key) {
        String string;
        Intrinsics.checkNotNullParameter((Object)key, (String)"key");
        String string2 = key;
        if (Intrinsics.areEqual((Object)string2, (Object)"post/liquidpanel/liquidpanel.glsl")) {
            string = INSTANCE.s0();
        } else if (Intrinsics.areEqual((Object)string2, (Object)"post/liquidpanel/liquidpanel.fsh")) {
            string = INSTANCE.s1();
        } else {
            throw new IllegalArgumentException(key);
        }
        return string;
    }

    private final String s0() {
        Object[] objectArray = new String[]{"//!fragment", "#version 150", "", "uniform sampler2D PanelTex;", "uniform sampler2D SceneTex;", "", "layout(std140) uniform LiquidConfig {", "    vec4 Rect;", "    vec4 Params0;", "    vec4 Params1;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "vec4 sn_permute(vec4 x) { return mod(((x * 34.0) + 1.0) * x, 289.0); }", "vec4 sn_taylorInvSqrt(vec4 r) { return 1.79284291400159 - 0.85373472095314 * r; }", "", "float snoise(vec3 v) {", "    const vec2 C = vec2(1.0 / 6.0, 1.0 / 3.0);", "    const vec4 D = vec4(0.0, 0.5, 1.0, 2.0);", "    vec3 i  = floor(v + dot(v, C.yyy));", "    vec3 x0 = v - i + dot(i, C.xxx);", "    vec3 g = step(x0.yzx, x0.xyz);", "    vec3 l = 1.0 - g;", "    vec3 i1 = min(g.xyz, l.zxy);", "    vec3 i2 = max(g.xyz, l.zxy);", "    vec3 x1 = x0 - i1 + 1.0 * C.xxx;", "    vec3 x2 = x0 - i2 + 2.0 * C.xxx;", "    vec3 x3 = x0 - 1.0 + 3.0 * C.xxx;", "    i = mod(i, 289.0);", "    vec4 p = sn_permute(sn_permute(sn_permute(", "        i.z + vec4(0.0, i1.z, i2.z, 1.0))", "        + i.y + vec4(0.0, i1.y, i2.y, 1.0))", "        + i.x + vec4(0.0, i1.x, i2.x, 1.0));", "    float n_ = 1.0 / 7.0;", "    vec3 ns = n_ * D.wyz - D.xzx;", "    vec4 j = p - 49.0 * floor(p * ns.z * ns.z);", "    vec4 x_ = floor(j * ns.z);", "    vec4 y_ = floor(j - 7.0 * x_);", "    vec4 x = x_ * ns.x + ns.yyyy;", "    vec4 y = y_ * ns.x + ns.yyyy;", "    vec4 h = 1.0 - abs(x) - abs(y);", "    vec4 b0 = vec4(x.xy, y.xy);", "    vec4 b1 = vec4(x.zw, y.zw);", "    vec4 s0 = floor(b0) * 2.0 + 1.0;", "    vec4 s1 = floor(b1) * 2.0 + 1.0;", "    vec4 sh = -step(h, vec4(0.0));", "    vec4 a0 = b0.xzyw + s0.xzyw * sh.xxyy;", "    vec4 a1 = b1.xzyw + s1.xzyw * sh.zzww;", "    vec3 p0 = vec3(a0.xy, h.x);", "    vec3 p1 = vec3(a0.zw, h.y);", "    vec3 p2 = vec3(a1.xy, h.z);", "    vec3 p3 = vec3(a1.zw, h.w);", "    vec4 norm = sn_taylorInvSqrt(vec4(dot(p0, p0), dot(p1, p1), dot(p2, p2), dot(p3, p3)));", "    p0 *= norm.x; p1 *= norm.y; p2 *= norm.z; p3 *= norm.w;", "    vec4 m = max(0.6 - vec4(dot(x0, x0), dot(x1, x1), dot(x2, x2), dot(x3, x3)), 0.0);", "    m = m * m;", "    return 42.0 * dot(m * m, vec4(dot(p0, x0), dot(p1, x1), dot(p2, x2), dot(p3, x3)));", "}", "", "void main() {", "    vec2 local = (texCoord - Rect.xy) / max(Rect.zw, vec2(1e-5));", "    if (local.x < 0.0 || local.x > 1.0 || local.y < 0.0 || local.y > 1.0) {", "        discard;", "    }", "", "    float progress = Params0.x;", "    vec2 anchor = Params0.yz;", "    float time = Params0.w;", "    float softness = max(Params1.x, 1e-3);", "    float noiseScale = Params1.y;", "    float noiseAmp = Params1.z;", "    float viscosity = Params1.w;", "", "    float axis = (abs(local.x - anchor.x) + abs(local.y - anchor.y)) * 0.5;", "    float wob = snoise(vec3(local * noiseScale, time)) * noiseAmp;", "    float front = progress * (1.0 + viscosity) - viscosity * 0.5 + wob;", "    float solid = 1.0 - smoothstep(front - softness, front + softness, axis);", "", "    float drip = (1.0 - solid) * viscosity;", "    vec2 pull = normalize(anchor - local + 1e-4);", "    vec2 panelLocal = clamp(local + pull * drip * 0.06, 0.0, 1.0);", "    vec3 panelCol = texture(PanelTex, Rect.xy + panelLocal * Rect.zw).rgb;", "    vec3 sceneCol = texture(SceneTex, texCoord).rgb;", "", "    fragColor = vec4(mix(sceneCol, panelCol, solid), 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s1() {
        Object[] objectArray = new String[]{"#version 150", "", "uniform sampler2D PanelTex;", "uniform sampler2D SceneTex;", "", "layout(std140) uniform LiquidConfig {", "    vec4 Rect;", "    vec4 Params0;", "    vec4 Params1;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "vec4 sn_permute(vec4 x) { return mod(((x * 34.0) + 1.0) * x, 289.0); }", "vec4 sn_taylorInvSqrt(vec4 r) { return 1.79284291400159 - 0.85373472095314 * r; }", "", "float snoise(vec3 v) {", "    const vec2 C = vec2(1.0 / 6.0, 1.0 / 3.0);", "    const vec4 D = vec4(0.0, 0.5, 1.0, 2.0);", "    vec3 i  = floor(v + dot(v, C.yyy));", "    vec3 x0 = v - i + dot(i, C.xxx);", "    vec3 g = step(x0.yzx, x0.xyz);", "    vec3 l = 1.0 - g;", "    vec3 i1 = min(g.xyz, l.zxy);", "    vec3 i2 = max(g.xyz, l.zxy);", "    vec3 x1 = x0 - i1 + 1.0 * C.xxx;", "    vec3 x2 = x0 - i2 + 2.0 * C.xxx;", "    vec3 x3 = x0 - 1.0 + 3.0 * C.xxx;", "    i = mod(i, 289.0);", "    vec4 p = sn_permute(sn_permute(sn_permute(", "        i.z + vec4(0.0, i1.z, i2.z, 1.0))", "        + i.y + vec4(0.0, i1.y, i2.y, 1.0))", "        + i.x + vec4(0.0, i1.x, i2.x, 1.0));", "    float n_ = 1.0 / 7.0;", "    vec3 ns = n_ * D.wyz - D.xzx;", "    vec4 j = p - 49.0 * floor(p * ns.z * ns.z);", "    vec4 x_ = floor(j * ns.z);", "    vec4 y_ = floor(j - 7.0 * x_);", "    vec4 x = x_ * ns.x + ns.yyyy;", "    vec4 y = y_ * ns.x + ns.yyyy;", "    vec4 h = 1.0 - abs(x) - abs(y);", "    vec4 b0 = vec4(x.xy, y.xy);", "    vec4 b1 = vec4(x.zw, y.zw);", "    vec4 s0 = floor(b0) * 2.0 + 1.0;", "    vec4 s1 = floor(b1) * 2.0 + 1.0;", "    vec4 sh = -step(h, vec4(0.0));", "    vec4 a0 = b0.xzyw + s0.xzyw * sh.xxyy;", "    vec4 a1 = b1.xzyw + s1.xzyw * sh.zzww;", "    vec3 p0 = vec3(a0.xy, h.x);", "    vec3 p1 = vec3(a0.zw, h.y);", "    vec3 p2 = vec3(a1.xy, h.z);", "    vec3 p3 = vec3(a1.zw, h.w);", "    vec4 norm = sn_taylorInvSqrt(vec4(dot(p0, p0), dot(p1, p1), dot(p2, p2), dot(p3, p3)));", "    p0 *= norm.x; p1 *= norm.y; p2 *= norm.z; p3 *= norm.w;", "    vec4 m = max(0.6 - vec4(dot(x0, x0), dot(x1, x1), dot(x2, x2), dot(x3, x3)), 0.0);", "    m = m * m;", "    return 42.0 * dot(m * m, vec4(dot(p0, x0), dot(p1, x1), dot(p2, x2), dot(p3, x3)));", "}", "", "void main() {", "    vec2 local = (texCoord - Rect.xy) / max(Rect.zw, vec2(1e-5));", "    if (local.x < 0.0 || local.x > 1.0 || local.y < 0.0 || local.y > 1.0) {", "        discard;", "    }", "", "    float progress = Params0.x;", "    vec2 anchor = Params0.yz;", "    float time = Params0.w;", "    float softness = max(Params1.x, 1e-3);", "    float noiseScale = Params1.y;", "    float noiseAmp = Params1.z;", "    float viscosity = Params1.w;", "", "    float axis = (abs(local.x - anchor.x) + abs(local.y - anchor.y)) * 0.5;", "    float wob = snoise(vec3(local * noiseScale, time)) * noiseAmp;", "    float front = progress * (1.0 + viscosity) - viscosity * 0.5 + wob;", "    float solid = 1.0 - smoothstep(front - softness, front + softness, axis);", "", "    float drip = (1.0 - solid) * viscosity;", "    vec2 pull = normalize(anchor - local + 1e-4);", "    vec2 panelLocal = clamp(local + pull * drip * 0.06, 0.0, 1.0);", "    vec3 panelCol = texture(PanelTex, Rect.xy + panelLocal * Rect.zw).rgb;", "    vec3 sceneCol = texture(SceneTex, texCoord).rgb;", "", "    fragColor = vec4(mix(sceneCol, panelCol, solid), 1.0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }
}

