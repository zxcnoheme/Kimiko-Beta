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
 */
package rtx.kimiko.utils.render.modules.post.usersky;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Visuals.Ambience;
import rtx.kimiko.utils.render.modules.post.usersky.UserSkyProgram;
import rtx.kimiko.utils.render.modules.post.usersky.UserSkyShaders;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/utils/render/modules/post/usersky/UserSkyManager;", "", "<init>", "()V", "Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "Lkotlin/jvm/JvmStatic;", "activePipeline", "()Lcom/mojang/blaze3d/pipeline/RenderPipeline;", "", "invalidate", "", "loadedName", "Ljava/lang/String;", "Lrtx/kimiko/utils/render/modules/post/usersky/UserSkyProgram;", "program", "Lrtx/kimiko/utils/render/modules/post/usersky/UserSkyProgram;", "rtx.kimiko:kimiko"})
public final class UserSkyManager {
    @NotNull
    public static final UserSkyManager INSTANCE = new UserSkyManager();
    @Nullable
    private static String loadedName;
    @Nullable
    private static UserSkyProgram program;

    private UserSkyManager() {
    }

    @JvmStatic
    @Nullable
    public static final RenderPipeline activePipeline() {
        UserSkyProgram current;
        Ambience ambience = Ambience.Companion.getInstance();
        if (ambience == null) {
            return null;
        }
        Ambience ambience2 = ambience;
        String name = ambience2.userSkyShaderName();
        if (((CharSequence)name).length() == 0) {
            UserSkyManager.invalidate();
            return null;
        }
        if (!Intrinsics.areEqual((Object)name, (Object)loadedName)) {
            loadedName = name;
            program = null;
            String code = UserSkyShaders.load(name);
            if (code != null) {
                program = UserSkyProgram.Companion.compile(code);
            }
        }
        if ((current = program) == null || current.hasError()) {
            return null;
        }
        return current.ensure();
    }

    @JvmStatic
    public static final void invalidate() {
        loadedName = null;
        program = null;
    }
}

