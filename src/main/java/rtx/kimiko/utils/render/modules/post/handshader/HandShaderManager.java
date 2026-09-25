/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.pipeline.RenderPipeline
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  rtx.kimiko.utils.render.modules.post.handshader.HandShaderProgram
 */
package rtx.kimiko.utils.render.modules.post.handshader;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Visuals.ShaderHands;
import rtx.kimiko.utils.render.modules.post.handshader.HandShaderProgram;
import rtx.kimiko.utils.render.modules.post.handshader.HandShaders;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\t\u001a\u0004\u0018\u00010\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\nJ\u0013\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\u0003R\u0018\u0010\r\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/utils/render/modules/post/handshader/HandShaderManager;", "", "<init>", "()V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lkotlin/jvm/JvmStatic;", "activePipeline", "()Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "", "error", "()Ljava/lang/String;", "", "invalidate", "loadedName", "Ljava/lang/String;", "Lrtx/kimiko/utils/render/modules/post/handshader/HandShaderProgram;", "program", "Lrtx/kimiko/utils/render/modules/post/handshader/HandShaderProgram;", "rtx.kimiko:kimiko"})
public final class HandShaderManager {
    @NotNull
    public static final HandShaderManager INSTANCE = new HandShaderManager();
    @Nullable
    private static String loadedName;
    @Nullable
    private static HandShaderProgram program;

    private HandShaderManager() {
    }

    @JvmStatic
    @Nullable
    public static final RenderPipeline activePipeline() {
        HandShaderProgram current;
        ShaderHands shaderHands = ShaderHands.Companion.getInstance();
        if (shaderHands == null) {
            return null;
        }
        ShaderHands hands = shaderHands;
        String name = hands.userShaderName();
        if (((CharSequence)name).length() == 0) {
            HandShaderManager.invalidate();
            return null;
        }
        if (!Intrinsics.areEqual((Object)name, (Object)loadedName)) {
            loadedName = name;
            program = null;
            String code = HandShaders.load(name);
            if (code != null) {
                program = HandShaderProgram.Companion.compile(code, false);
            }
        }
        if ((current = program) == null || current.hasError()) {
            return null;
        }
        return current.ensure();
    }

    @JvmStatic
    @Nullable
    public static final String error() {
        HandShaderProgram handShaderProgram = program;
        return handShaderProgram != null ? handShaderProgram.error() : null;
    }

    @JvmStatic
    public static final void invalidate() {
        loadedName = null;
        program = null;
    }
}

