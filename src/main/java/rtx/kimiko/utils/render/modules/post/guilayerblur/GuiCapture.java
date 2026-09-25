/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.modules.post.guilayerblur;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u001bB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001d\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u000bH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\u0011\u001a\u00020\u0010H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u0010H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0013\u0010\u0012J\u0013\u0010\u0014\u001a\u00020\u0010H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0014\u0010\u0012J\u0013\u0010\u0015\u001a\u00020\u000bH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0015\u0010\u000fJ\u0015\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/utils/render/modules/post/guilayerblur/GuiCapture;", "", "<init>", "()V", "Lrtx/kimiko/utils/render/modules/post/guilayerblur/GuiCapture$Source;", "next", "", "Lkotlin/jvm/JvmStatic;", "bind", "(Lrtx/kimiko/utils/render/modules/post/guilayerblur/GuiCapture$Source;)V", "candidate", "", "isBound", "(Lrtx/kimiko/utils/render/modules/post/guilayerblur/GuiCapture$Source;)Z", "active", "()Z", "", "scale", "()F", "blurRadius", "shatterProgress", "emitPanelBoundary", "", "shatterKeepRect", "()[F", "source", "Lrtx/kimiko/utils/render/modules/post/guilayerblur/GuiCapture$Source;", "Source", "rtx.kimiko:kimiko"})
public final class GuiCapture {
    @NotNull
    public static final GuiCapture INSTANCE = new GuiCapture();
    @Nullable
    private static Source source;

    private GuiCapture() {
    }

    @JvmStatic
    public static final void bind(@Nullable Source next) {
        source = next;
    }

    @JvmStatic
    public static final boolean isBound(@Nullable Source candidate) {
        return candidate != null && source == candidate;
    }

    @JvmStatic
    public static final boolean active() {
        Source source = GuiCapture.source;
        return source != null ? source.captureActive() : false;
    }

    @JvmStatic
    public static final float scale() {
        Source source = GuiCapture.source;
        return source != null ? source.captureScale() : 1.0f;
    }

    @JvmStatic
    public static final float blurRadius() {
        Source source = GuiCapture.source;
        return source != null ? source.captureBlurRadius() : 0.0f;
    }

    @JvmStatic
    public static final float shatterProgress() {
        Source source = GuiCapture.source;
        return source != null ? source.shatterProgress() : 0.0f;
    }

    @JvmStatic
    public static final boolean emitPanelBoundary() {
        Source source = GuiCapture.source;
        return source != null ? source.emitPanelBoundary() : true;
    }

    @JvmStatic
    @Nullable
    public static final float[] shatterKeepRect() {
        Source source = GuiCapture.source;
        return (float[])(source != null ? source.shatterKeepRect() : null);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H&\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0005H&\u00a2\u0006\u0004\b\b\u0010\u0007J\u000f\u0010\t\u001a\u00020\u0005H&\u00a2\u0006\u0004\b\t\u0010\u0007J\u000f\u0010\n\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\n\u0010\u0004J\u0011\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016\u00a2\u0006\u0004\b\f\u0010\r\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006\u000e\u00c0\u0006\u0001"}, d2={"Lrtx/kimiko/utils/render/modules/post/guilayerblur/GuiCapture$Source;", "", "", "captureActive", "()Z", "", "captureScale", "()F", "captureBlurRadius", "shatterProgress", "emitPanelBoundary", "", "shatterKeepRect", "()[F", "rtx.kimiko:kimiko"})
    public static interface Source {
        public boolean captureActive();

        public float captureScale();

        public float captureBlurRadius();

        public float shatterProgress();

        default public boolean emitPanelBoundary() {
            return true;
        }

        @Nullable
        default public float[] shatterKeepRect() {
            return null;
        }
    }
}

