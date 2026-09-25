/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.CompiledRenderPipeline
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  com.mojang.blaze3d.pipeline.RenderPipeline$Snippet
 *  com.mojang.blaze3d.platform.DepthTestFunction
 *  com.mojang.blaze3d.shaders.ShaderType
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.vertex.VertexFormat$DrawMode
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.client.gl.UniformType
 *  net.minecraft.client.gl.ShaderSourceGetter
 *  net.minecraft.client.render.VertexFormats
 *  net.minecraft.util.Identifier
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.opengl.GL20C
 */
package rtx.kimiko.utils.render.modules.post.usersky;

import com.mojang.blaze3d.pipeline.CompiledRenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.shaders.ShaderType;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.gl.UniformType;
import net.minecraft.client.gl.ShaderSourceGetter;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL20C;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.utils.render.shaders.EmbeddedShaders;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B'\b\u0002\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0004\b\u0007\u0010\rJ\u000f\u0010\u000e\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0010R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0011R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0012R\u0016\u0010\u0013\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0016"}, d2={"Lrtx/kimiko/utils/render/modules/post/usersky/UserSkyProgram;", "", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "pipeline", "Lnet/minecraft/ShaderSourceGetter;", "source", "", "error", "<init>", "(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/ShaderSourceGetter;Ljava/lang/String;)V", "", "hasError", "()Z", "()Ljava/lang/String;", "ensure", "()Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lnet/minecraft/ShaderSourceGetter;", "Ljava/lang/String;", "broken", "Z", "Companion", "rtx.kimiko:kimiko"})
public final class UserSkyProgram {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    private final RenderPipeline pipeline;
    @Nullable
    private final ShaderSourceGetter source;
    @Nullable
    private final String error;
    private boolean broken;
    @NotNull
    private static final Pattern MAIN_IMAGE;
    @NotNull
    private static final Pattern OWN_MAIN;
    private static int serial;
    @NotNull
    private static final String HEADER;
    @NotNull
    private static final String FOOTER;

    private UserSkyProgram(RenderPipeline pipeline, ShaderSourceGetter source, String error) {
        this.pipeline = pipeline;
        this.source = source;
        this.error = error;
    }

    public final boolean hasError() {
        return this.error != null || this.broken;
    }

    @Nullable
    public final String error() {
        if (this.error != null) {
            return this.error;
        }
        return this.broken ? I18n.tr("Ошибка сборки пайплайна.") : null;
    }

    @Nullable
    public final RenderPipeline ensure() {
        RenderPipeline renderPipeline;
        if (this.pipeline == null || this.broken) {
            return null;
        }
        try {
            RenderPipeline renderPipeline2;
            CompiledRenderPipeline compiledRenderPipeline = RenderSystem.getDevice().precompilePipeline(this.pipeline, this.source);
            Intrinsics.checkNotNullExpressionValue((Object)compiledRenderPipeline, (String)"precompilePipeline(...)");
            CompiledRenderPipeline compiled = compiledRenderPipeline;
            if (!compiled.isValid()) {
                this.broken = true;
                renderPipeline2 = null;
            } else {
                renderPipeline2 = this.pipeline;
            }
            renderPipeline = renderPipeline2;
        }
        catch (Throwable throwable) {
            this.broken = true;
            renderPipeline = null;
        }
        return renderPipeline;
    }

    @JvmStatic
    @NotNull
    public static final UserSkyProgram compile(@Nullable String userCode) {
        return Companion.compile(userCode);
    }

    public /* synthetic */ UserSkyProgram(RenderPipeline pipeline, ShaderSourceGetter source, String error, DefaultConstructorMarker $constructor_marker) {
        this(pipeline, source, error);
    }

    static {
        Pattern pattern = Pattern.compile("\\bmainImage\\s*\\(");
        Intrinsics.checkNotNullExpressionValue((Object)pattern, (String)"compile(...)");
        MAIN_IMAGE = pattern;
        Pattern pattern2 = Pattern.compile("\\bvoid\\s+main\\s*\\(");
        Intrinsics.checkNotNullExpressionValue((Object)pattern2, (String)"compile(...)");
        OWN_MAIN = pattern2;
        Object[] objectArray = new String[]{"#version 150", "", "layout(std140) uniform SkyParams {", "    mat4 invViewProj;", "    vec4 misc;", "    vec4 skyColor;", "    vec4 skyColor2;", "    vec4 taa;", "    mat4 prevViewProj;", "    vec4 skyExtra;", "};", "", "in vec2 texCoord;", "out vec4 fragColor;", "", "#define iTime (misc.x)", "#define iResolution vec3(misc.y, misc.z, 1.0)", "#define iTimeDelta 0.016", "#define iFrame int(misc.x * 60.0)", "#define iMouse vec4(0.0)", "", "vec3 skyDirection() {", "    vec2 kmk_ndc = texCoord * 2.0 - 1.0;", "    vec4 kmk_far = invViewProj * vec4(kmk_ndc, 1.0, 1.0);", "    vec4 kmk_near = invViewProj * vec4(kmk_ndc, -1.0, 1.0);", "    return normalize(kmk_far.xyz / kmk_far.w - kmk_near.xyz / kmk_near.w);", "}", "", "vec2 skyPanoramaUv() {", "    vec3 kmk_dir = skyDirection();", "    float kmk_u = atan(kmk_dir.z, kmk_dir.x) * 0.15915494 + 0.5;", "    float kmk_v = asin(clamp(kmk_dir.y, -1.0, 1.0)) * 0.31830989 + 0.5;", "    return vec2(kmk_u, kmk_v);", "}", "", "vec3 kimikoColor() { return skyColor.rgb; }", "vec3 kimikoColor2() { return skyColor2.rgb; }", "float kimikoBrightness() { return misc.w; }", "", "#line 1"};
        HEADER = String.join("\n", (CharSequence[]) objectArray);
        objectArray = new String[]{"", "void main() {", "#if defined(KIMIKO_PANORAMA) && !defined(KIMIKO_SCREEN_SPACE)", "    vec2 kmk_fragCoord = skyPanoramaUv() * iResolution.xy;", "#else", "    vec2 kmk_fragCoord = texCoord * iResolution.xy;", "#endif", "    vec4 kmk_color = vec4(0.0);", "    mainImage(kmk_color, kmk_fragCoord);", "    fragColor = vec4(clamp(kmk_color.rgb, vec3(0.0), vec3(4.0)) * misc.w, 1.0);", "}"};
        FOOTER = String.join("\n", (CharSequence[]) objectArray);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u000b\u0010\tJ\u0019\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0015R\u0016\u0010\u0018\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001b\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/utils/render/modules/post/usersky/UserSkyProgram.Companion;", "", "<init>", "()V", "", "userCode", "Lrtx/kimiko/utils/render/modules/post/usersky/UserSkyProgram;", "Lkotlin/jvm/JvmStatic;", "compile", "(Ljava/lang/String;)Lrtx/kimiko/utils/render/modules/post/usersky/UserSkyProgram;", "error", "failed", "source", "validate", "(Ljava/lang/String;)Ljava/lang/String;", "path", "Lnet/minecraft/Identifier;", "id", "(Ljava/lang/String;)Lnet/minecraft/Identifier;", "Ljava/util/regex/Pattern;", "MAIN_IMAGE", "Ljava/util/regex/Pattern;", "OWN_MAIN", "", "serial", "I", "HEADER", "Ljava/lang/String;", "FOOTER", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final UserSkyProgram compile(@Nullable String userCode) {
            if (userCode == null || StringsKt.isBlank((CharSequence)userCode)) {
                return this.failed(I18n.tr("Шейдер пустой."));
            }
            if (OWN_MAIN.matcher(userCode).find()) {
                return this.failed(I18n.tr("Убери свой main() — он добавляется автоматически. Пиши логику в mainImage(out vec4 fragColor, in vec2 fragCoord)."));
            }
            if (!MAIN_IMAGE.matcher(userCode).find()) {
                return this.failed(I18n.tr("Нужна функция mainImage(out vec4 fragColor, in vec2 fragCoord)."));
            }
            String full = HEADER + "\n" + userCode + FOOTER;
            String log = this.validate(full);
            if (log != null) {
                return this.failed(log);
            }
            serial = serial + 1;
            int id = serial;
            String fragPath = "post/usersky/user" + id;
            RenderPipeline pipeline = null;
            try {
                RenderPipeline renderPipeline = RenderPipeline.builder((RenderPipeline.Snippet[])new RenderPipeline.Snippet[0]).withLocation(this.id("pipeline/" + fragPath)).withVertexShader(this.id("post/customsky/customsky")).withFragmentShader(this.id(fragPath)).withVertexFormat(VertexFormats.EMPTY, VertexFormat.DrawMode.TRIANGLES).withUniform("SkyParams", UniformType.UNIFORM_BUFFER).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).withDepthWrite(false).withCull(false).build();
                Intrinsics.checkNotNullExpressionValue((Object)renderPipeline, (String)"build(...)");
                pipeline = renderPipeline;
            }
            catch (Throwable throwable) {
                return this.failed(I18n.tr("Не удалось создать пайплайн."));
            }
            ShaderSourceGetter source = (arg_0, arg_1) -> Companion.compile$lambda$0(fragPath, full, arg_0, arg_1);
            return new UserSkyProgram(pipeline, source, null, null);
        }

        private final UserSkyProgram failed(String error) {
            return new UserSkyProgram(null, null, error, null);
        }

        private final String validate(String source) {
            String string;
            if (!RenderSystem.isOnRenderThread()) {
                return null;
            }
            try {
                String string2;
                int shader = GL20C.glCreateShader((int)35632);
                if (shader == 0) {
                    string2 = null;
                } else {
                    GL20C.glShaderSource((int)shader, (CharSequence)source);
                    GL20C.glCompileShader((int)shader);
                    int status = GL20C.glGetShaderi((int)shader, (int)35713);
                    String log = GL20C.glGetShaderInfoLog((int)shader, (int)8192);
                    GL20C.glDeleteShader((int)shader);
                    string2 = status == 1 ? null : (log == null || StringsKt.isBlank((CharSequence)log) ? I18n.tr("Ошибка компиляции шейдера.") : ((Object)StringsKt.trim((CharSequence)log)).toString());
                }
                string = string2;
            }
            catch (Throwable throwable) {
                string = null;
            }
            return string;
        }

        private final Identifier id(String path) {
            Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)path);
            Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
            return identifier2;
        }

        private static final String compile$lambda$0(String $fragPath, String $full, Identifier rid, ShaderType type) {
            Intrinsics.checkNotNullParameter((Object)rid, (String)"rid");
            Intrinsics.checkNotNullParameter((Object)type, (String)"type");
            String string = rid.getPath();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getPath(...)");
            String path = string;
            if (String.valueOf(path).startsWith("shaders/")) {
                String string2 = path.substring(8);
                Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"substring(...)");
                path = string2;
            }
            if (String.valueOf(path).endsWith(".vsh") || String.valueOf(path).endsWith(".fsh")) {
                String string3 = path.substring(0, path.length() - 4);
                Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"substring(...)");
                path = string3;
            }
            return Intrinsics.areEqual((Object)path, (Object)$fragPath) ? $full : EmbeddedShaders.SOURCES.get(path + (type == ShaderType.VERTEX ? ".vsh" : ".fsh"));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

