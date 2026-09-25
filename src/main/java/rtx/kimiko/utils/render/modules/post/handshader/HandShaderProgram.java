package rtx.kimiko.utils.render.modules.post.handshader;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HandShaderProgram {
    @NotNull
    public static final Companion Companion = new Companion(null);

    @Nullable
    private final String error;
    @Nullable
    private final RenderPipeline pipeline;

    public HandShaderProgram(@Nullable RenderPipeline pipeline, @Nullable String error) {
        this.pipeline = pipeline;
        this.error = error;
    }

    public boolean hasError() {
        return this.error != null;
    }

    @Nullable
    public String error() {
        return this.error;
    }

    @Nullable
    public RenderPipeline ensure() {
        return this.pipeline;
    }

    public static final class Companion {
        private Companion() {}
        public /* synthetic */ Companion(DefaultConstructorMarker m) { this(); }

        @NotNull
        public HandShaderProgram compile(@Nullable String code, boolean preview) {
            return new HandShaderProgram(null, null);
        }
    }
}
