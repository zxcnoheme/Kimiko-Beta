/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.util.Window
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.util.Monitor
 *  net.minecraft.client.util.VideoMode
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.render2d;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.util.Window;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Monitor;
import net.minecraft.client.util.VideoMode;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.ui.UI;
import rtx.kimiko.api.ui.window.GuiShatterAnimation;
import rtx.kimiko.api.ui.window.WorldGuiCloseAnimation;
import rtx.kimiko.utils.render.modules.post.guilayerblur.GuiCapture;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u000f\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u0007H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\tJ\u0013\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u000e\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u000f\u0010\rR\u0014\u0010\u0010\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0011R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0017\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0016R\u0016\u0010\n\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0018R\u0016\u0010\u0019\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0011R\u0016\u0010\u001a\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0016\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/utils/render/render2d/RefreshRateThrottle;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "beginFrame", "", "guiTransitionActive", "()Z", "updateFrame", "", "hz", "()I", "refreshHz", "queryHz", "MIN_HZ", "I", "MAX_HZ", "FALLBACK_HZ", "", "HZ_RECHECK_MS", "J", "lastUpdateNanos", "Z", "cachedHz", "hzCheckedAtMs", "rtx.kimiko:kimiko"})
public final class RefreshRateThrottle {
    @NotNull
    public static final RefreshRateThrottle INSTANCE = new RefreshRateThrottle();
    private static final int MIN_HZ = 24;
    private static final int MAX_HZ = 1000;
    private static final int FALLBACK_HZ = 60;
    private static final long HZ_RECHECK_MS = 1000L;
    private static long lastUpdateNanos;
    private static boolean updateFrame;
    private static int cachedHz;
    private static long hzCheckedAtMs;

    private RefreshRateThrottle() {
    }

    @JvmStatic
    public static final void beginFrame() {
        long interval;
        long now = System.nanoTime();
        if (now - lastUpdateNanos >= (interval = 1000000000L / (long)Math.max(24, INSTANCE.refreshHz())) - interval / 10L) {
            updateFrame = true;
            lastUpdateNanos = now;
        } else {
            updateFrame = INSTANCE.guiTransitionActive();
        }
    }

    private final boolean guiTransitionActive() {
        boolean bl;
        try {
            bl = GuiCapture.active() || UI.Companion.guiCaptureActive() || GuiShatterAnimation.isActive() || WorldGuiCloseAnimation.isActive();
        }
        catch (RuntimeException ignored) {
            bl = true;
        }
        return bl;
    }

    @JvmStatic
    public static final boolean updateFrame() {
        return updateFrame;
    }

    @JvmStatic
    public static final int hz() {
        return INSTANCE.refreshHz();
    }

    private final int refreshHz() {
        long nowMs = System.currentTimeMillis();
        if (nowMs - hzCheckedAtMs < 1000L) {
            return cachedHz;
        }
        hzCheckedAtMs = nowMs;
        cachedHz = this.queryHz();
        return cachedHz;
    }

    private final int queryHz() {
        int n;
        try {
            int n2;
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient minecraft = minecraftClient2;
            Window window = minecraft.getWindow();
            if (window == null) {
                n2 = 60;
            } else {
                int hz;
                VideoMode mode;
                Monitor monitor;
                Monitor monitor2 = monitor = window.getMonitor();
                VideoMode videoMode2 = mode = monitor2 != null ? monitor2.getCurrentVideoMode() : null;
                int n3 = hz = videoMode2 != null ? videoMode2.getRefreshRate() : 0;
                if (hz < 24 || hz > 1000) {
                    hz = window.getRefreshRate();
                }
                n2 = (24 <= hz ? hz < 1001 : false) ? hz : 60;
            }
            n = n2;
        }
        catch (RuntimeException ignored) {
            n = 60;
        }
        return n;
    }

    static {
        updateFrame = true;
        cachedHz = 60;
        hzCheckedAtMs = Long.MIN_VALUE;
    }
}

