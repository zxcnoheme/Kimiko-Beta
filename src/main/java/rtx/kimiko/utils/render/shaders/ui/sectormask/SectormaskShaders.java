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
package rtx.kimiko.utils.render.shaders.ui.sectormask;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\t\u001a\u00020\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000f\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/utils/render/shaders/ui/sectormask/SectormaskShaders;", "", "<init>", "()V", "", "", "sources", "", "Lkotlin/jvm/JvmStatic;", "register", "(Ljava/util/Map;)V", "key", "source", "(Ljava/lang/String;)Ljava/lang/String;", "s0", "()Ljava/lang/String;", "s1", "s2", "rtx.kimiko:kimiko"})
public final class SectormaskShaders {
    @NotNull
    public static final SectormaskShaders INSTANCE = new SectormaskShaders();

    private SectormaskShaders() {
    }

    @JvmStatic
    public static final void register(@NotNull Map<String, String> sources) {
        Intrinsics.checkNotNullParameter(sources, (String)"sources");
        sources.put("ui/sectormask/sectormask.glsl", INSTANCE.s0());
        sources.put("ui/sectormask/sectormask.vsh", INSTANCE.s1());
        sources.put("ui/sectormask/sectormask.fsh", INSTANCE.s2());
    }

    @JvmStatic
    @NotNull
    public static final String source(@NotNull String key) {
        Intrinsics.checkNotNullParameter((Object)key, (String)"key");
        return switch (key) {
            case "ui/sectormask/sectormask.glsl" -> INSTANCE.s0();
            case "ui/sectormask/sectormask.vsh" -> INSTANCE.s1();
            case "ui/sectormask/sectormask.fsh" -> INSTANCE.s2();
            default -> throw new IllegalArgumentException(key);
        };
    }

    private final String s0() {
        Object[] objectArray = new String[]{"//!vertex", "#version 150", "", "#moj_import <minecraft:dynamictransforms.glsl>", "#moj_import <minecraft:projection.glsl>", "", "in vec3 Position;", "in vec4 Color;", "in float LineWidth;", "", "out vec2 FragCoord;", "flat out int QuadIndex;", "", "void main() {", "    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);", "    FragCoord = Color.rg;", "    QuadIndex = max(int(LineWidth + 0.5) - 1, 0);", "}", "//!fragment", "#version 150", "", "in vec2 FragCoord;", "flat in int QuadIndex;", "", "uniform sampler2D Sampler0;", "", "layout(std140) uniform SectorMaskParamsArray {", "    vec4 params[40];", "};", "", "out vec4 OutColor;", "", "const float TAU = 6.28318530718;", "", "void main() {", "    int base = QuadIndex * 5;", "    vec4 shape = params[base];", "    vec4 style = params[base + 1];", "    vec4 hover = params[base + 2];", "    vec4 grid = params[base + 3];", "    vec4 extra = params[base + 4];", "", "    float outerRadius = shape.x;", "    float innerRadius = shape.y;", "    float count = max(shape.z, 1.0);", "    float gap = max(shape.w, 0.0);", "", "    float corner = max(style.x, 0.0);", "    float feather = max(style.y, 0.2);", "    float extent = max(style.z, 1.0);", "    float globalAlpha = clamp(style.w, 0.0, 1.0);", "", "    int hoverIndex = int(hover.x + 0.5);", "    float hoverGrow = hover.y;", "    float hoverShrink = hover.z;", "    float ringRadius = hover.w;", "", "    float cellWidth = max(grid.x, 0.001);", "    float cellHeight = max(grid.y, 0.001);", "    float columns = max(grid.z, 1.0);", "    float rows = max(grid.w, 1.0);", "", "    vec2 coord = clamp(FragCoord, vec2(0.0), vec2(1.0));", "    vec2 local = (coord - vec2(0.5)) * (extent * 2.0);", "    vec2 p = vec2(local.x, -local.y);", "", "    float sweep = TAU / count;", "    float angle = atan(p.x, p.y);", "    if (angle < 0.0) {", "        angle += TAU;", "    }", "", "    int index = int(floor(angle / sweep + 0.5));", "    if (index >= int(count + 0.5)) {", "        index = 0;", "    }", "    float midAngle = float(index) * sweep;", "", "    float outer = outerRadius;", "    float inner = innerRadius;", "    float ringLocal = ringRadius;", "    float zoom = 1.0;", "    if (hoverIndex >= 0 && index == hoverIndex) {", "        outer += hoverGrow;", "        inner -= hoverShrink;", "        ringLocal += (hoverGrow - hoverShrink) * 0.5;", "        zoom = max(extra.x, 0.05);", "    }", "", "    float halfAngle = max(sweep * 0.5 - gap * 0.5, 0.001);", "", "    float ca = cos(midAngle);", "    float sa = sin(midAngle);", "    vec2 q = vec2(p.x * ca - p.y * sa, p.x * sa + p.y * ca);", "", "    float ringMid = (outer + inner) * 0.5;", "    float ringHalf = max((outer - inner) * 0.5, 0.001);", "    float ring = abs(length(q) - ringMid) - ringHalf;", "    float wedge = abs(q.x) * cos(halfAngle) - q.y * sin(halfAngle);", "", "    float rr = min(corner, ringHalf - 0.05);", "    rr = max(rr, 0.0);", "    vec2 dd = vec2(ring + rr, wedge + rr);", "    float signedEdge = min(max(dd.x, dd.y), 0.0) + length(max(dd, vec2(0.0))) - rr;", "", "    float mask = 1.0 - smoothstep(-feather, feather, signedEdge);", "    if (mask <= 0.001) {", "        discard;", "    }", "", "    vec2 cellCenter = vec2(sin(midAngle), cos(midAngle)) * ringLocal;", "    vec2 offset = p - cellCenter;", "    vec2 cellUv = vec2(offset.x / (cellWidth * zoom) + 0.5, 0.5 - offset.y / (cellHeight * zoom));", "    if (cellUv.x < 0.0 || cellUv.x > 1.0 || cellUv.y < 0.0 || cellUv.y > 1.0) {", "        discard;", "    }", "", "    float column = mod(float(index), columns);", "    float row = floor(float(index) / columns);", "    vec2 atlasUv = (vec2(column, row) + cellUv) / vec2(columns, rows);", "", "    vec4 texColor = texture(Sampler0, vec2(atlasUv.x, 1.0 - atlasUv.y));", "    OutColor = texColor * (mask * globalAlpha);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s1() {
        Object[] objectArray = new String[]{"#version 150", "", "#moj_import <minecraft:dynamictransforms.glsl>", "#moj_import <minecraft:projection.glsl>", "", "in vec3 Position;", "in vec4 Color;", "in float LineWidth;", "", "out vec2 FragCoord;", "flat out int QuadIndex;", "", "void main() {", "    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);", "    FragCoord = Color.rg;", "    QuadIndex = max(int(LineWidth + 0.5) - 1, 0);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }

    private final String s2() {
        Object[] objectArray = new String[]{"#version 150", "", "in vec2 FragCoord;", "flat in int QuadIndex;", "", "uniform sampler2D Sampler0;", "", "layout(std140) uniform SectorMaskParamsArray {", "    vec4 params[40];", "};", "", "out vec4 OutColor;", "", "const float TAU = 6.28318530718;", "", "void main() {", "    int base = QuadIndex * 5;", "    vec4 shape = params[base];", "    vec4 style = params[base + 1];", "    vec4 hover = params[base + 2];", "    vec4 grid = params[base + 3];", "    vec4 extra = params[base + 4];", "", "    float outerRadius = shape.x;", "    float innerRadius = shape.y;", "    float count = max(shape.z, 1.0);", "    float gap = max(shape.w, 0.0);", "", "    float corner = max(style.x, 0.0);", "    float feather = max(style.y, 0.2);", "    float extent = max(style.z, 1.0);", "    float globalAlpha = clamp(style.w, 0.0, 1.0);", "", "    int hoverIndex = int(hover.x + 0.5);", "    float hoverGrow = hover.y;", "    float hoverShrink = hover.z;", "    float ringRadius = hover.w;", "", "    float cellWidth = max(grid.x, 0.001);", "    float cellHeight = max(grid.y, 0.001);", "    float columns = max(grid.z, 1.0);", "    float rows = max(grid.w, 1.0);", "", "    vec2 coord = clamp(FragCoord, vec2(0.0), vec2(1.0));", "    vec2 local = (coord - vec2(0.5)) * (extent * 2.0);", "    vec2 p = vec2(local.x, -local.y);", "", "    float sweep = TAU / count;", "    float angle = atan(p.x, p.y);", "    if (angle < 0.0) {", "        angle += TAU;", "    }", "", "    int index = int(floor(angle / sweep + 0.5));", "    if (index >= int(count + 0.5)) {", "        index = 0;", "    }", "    float midAngle = float(index) * sweep;", "", "    float outer = outerRadius;", "    float inner = innerRadius;", "    float ringLocal = ringRadius;", "    float zoom = 1.0;", "    if (hoverIndex >= 0 && index == hoverIndex) {", "        outer += hoverGrow;", "        inner -= hoverShrink;", "        ringLocal += (hoverGrow - hoverShrink) * 0.5;", "        zoom = max(extra.x, 0.05);", "    }", "", "    float halfAngle = max(sweep * 0.5 - gap * 0.5, 0.001);", "", "    float ca = cos(midAngle);", "    float sa = sin(midAngle);", "    vec2 q = vec2(p.x * ca - p.y * sa, p.x * sa + p.y * ca);", "", "    float ringMid = (outer + inner) * 0.5;", "    float ringHalf = max((outer - inner) * 0.5, 0.001);", "    float ring = abs(length(q) - ringMid) - ringHalf;", "    float wedge = abs(q.x) * cos(halfAngle) - q.y * sin(halfAngle);", "", "    float rr = min(corner, ringHalf - 0.05);", "    rr = max(rr, 0.0);", "    vec2 dd = vec2(ring + rr, wedge + rr);", "    float signedEdge = min(max(dd.x, dd.y), 0.0) + length(max(dd, vec2(0.0))) - rr;", "", "    float mask = 1.0 - smoothstep(-feather, feather, signedEdge);", "    if (mask <= 0.001) {", "        discard;", "    }", "", "    vec2 cellCenter = vec2(sin(midAngle), cos(midAngle)) * ringLocal;", "    vec2 offset = p - cellCenter;", "    vec2 cellUv = vec2(offset.x / (cellWidth * zoom) + 0.5, 0.5 - offset.y / (cellHeight * zoom));", "    if (cellUv.x < 0.0 || cellUv.x > 1.0 || cellUv.y < 0.0 || cellUv.y > 1.0) {", "        discard;", "    }", "", "    float column = mod(float(index), columns);", "    float row = floor(float(index) / columns);", "    vec2 atlasUv = (vec2(column, row) + cellUv) / vec2(columns, rows);", "", "    vec4 texColor = texture(Sampler0, vec2(atlasUv.x, 1.0 - atlasUv.y));", "    OutColor = texColor * (mask * globalAlpha);", "}"};
        return String.join("\n", (CharSequence[]) objectArray);
    }
}

