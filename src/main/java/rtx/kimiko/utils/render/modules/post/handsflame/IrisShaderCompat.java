/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  net.fabricmc.loader.api.FabricLoader
 *  net.irisshaders.iris.api.v0.IrisApi
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.modules.post.handsflame;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import net.fabricmc.loader.api.FabricLoader;
import net.irisshaders.iris.api.v0.IrisApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.render.modules.post.handsflame.HandsFlameRenderer;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u0014B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J\u0013\u0010\n\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u0003J\u0013\u0010\u000b\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000b\u0010\u0003J\u000f\u0010\f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\f\u0010\u0007R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0012\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/utils/render/modules/post/handsflame/IrisShaderCompat;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "isShaderPackInUse", "()Z", "", "renderHandsFlameAfterIrisFinalPass", "beginHandDepthCapture", "endHandDepthCapture", "isIrisLoaded", "irisLoaded", "Ljava/lang/Boolean;", "lastPackInUse", "Z", "", "lastPackInUseCheck", "J", "IrisApiHolder", "rtx.kimiko:kimiko"})
public final class IrisShaderCompat {
    @NotNull
    public static final IrisShaderCompat INSTANCE = new IrisShaderCompat();
    @Nullable
    private static Boolean irisLoaded;
    private static boolean lastPackInUse;
    private static long lastPackInUseCheck;

    private IrisShaderCompat() {
    }

    @JvmStatic
    public static final boolean isShaderPackInUse() {
        if (!INSTANCE.isIrisLoaded()) {
            return false;
        }
        long now = System.currentTimeMillis();
        if (now - lastPackInUseCheck < 1000L) {
            return lastPackInUse;
        }
        lastPackInUseCheck = now;
        lastPackInUse = IrisApiHolder.INSTANCE.isShaderPackInUse();
        return lastPackInUse;
    }

    @JvmStatic
    public static final void renderHandsFlameAfterIrisFinalPass() {
        if (!IrisShaderCompat.isShaderPackInUse()) {
            return;
        }
        HandsFlameRenderer.renderIrisCapturedHandsFlame();
    }

    @JvmStatic
    public static final void beginHandDepthCapture() {
        if (IrisShaderCompat.isShaderPackInUse()) {
            HandsFlameRenderer.beginIrisHandDepthCapture();
        }
    }

    @JvmStatic
    public static final void endHandDepthCapture() {
        if (IrisShaderCompat.isShaderPackInUse()) {
            HandsFlameRenderer.endIrisHandDepthCapture();
        }
    }

    private final boolean isIrisLoaded() {
        Boolean loaded = irisLoaded;
        if (loaded == null) {
            irisLoaded = loaded = Boolean.valueOf(FabricLoader.getInstance().isModLoaded("iris"));
        }
        return loaded;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u00c2\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lrtx/kimiko/utils/render/modules/post/handsflame/IrisShaderCompat$IrisApiHolder;", "", "<init>", "()V", "", "isShaderPackInUse", "()Z", "rtx.kimiko:kimiko"})
    private static final class IrisApiHolder {
        @NotNull
        public static final IrisApiHolder INSTANCE = new IrisApiHolder();

        private IrisApiHolder() {
        }

        public final boolean isShaderPackInUse() {
            boolean bl;
            try {
                bl = IrisApi.getInstance().isShaderPackInUse();
            }
            catch (Throwable ignored) {
                bl = false;
            }
            return bl;
        }
    }
}

