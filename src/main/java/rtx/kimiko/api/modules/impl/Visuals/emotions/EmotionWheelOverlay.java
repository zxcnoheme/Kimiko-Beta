/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.emotions;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.IMinecraft;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionWheel;
import rtx.kimiko.utils.render.modules.post.guilayerblur.GuiLayerBlurRenderer;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\n\u0010\u0003J\u0015\u0010\u000b\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\rH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0013"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionWheelOverlay;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionWheel;", "wheel", "", "Lkotlin/jvm/JvmStatic;", "begin", "(Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionWheel;)V", "cancel", "claim", "()Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionWheel;", "Lnet/minecraft/DrawContext;", "graphics", "render", "(Lnet/minecraft/DrawContext;)V", "closing", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionWheel;", "rtx.kimiko:kimiko"})
public final class EmotionWheelOverlay {
    @NotNull
    public static final EmotionWheelOverlay INSTANCE = new EmotionWheelOverlay();
    @Nullable
    private static EmotionWheel closing;

    private EmotionWheelOverlay() {
    }

    @JvmStatic
    public static final void begin(@Nullable EmotionWheel wheel) {
        if (wheel == null) {
            return;
        }
        wheel.close();
        closing = wheel;
    }

    @JvmStatic
    public static final void cancel() {
        EmotionWheel current = closing;
        if (current != null) {
            current.finish();
            closing = null;
        }
    }

    @JvmStatic
    @Nullable
    public static final EmotionWheel claim() {
        EmotionWheel wheel = closing;
        closing = null;
        return wheel;
    }

    @JvmStatic
    public static final void render(@NotNull DrawContext graphics) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        EmotionWheel emotionWheel = closing;
        if (emotionWheel == null) {
            return;
        }
        EmotionWheel wheel = emotionWheel;
        MinecraftClient mc = IMinecraft.mc;
        if (mc.currentScreen != null || mc.world == null || mc.options.hudHidden || wheel.isFinished()) {
            wheel.finish();
            closing = null;
            return;
        }
        Render2D.beginFrame(graphics);
        wheel.render(graphics, false);
        Render2D.flush();
        GuiLayerBlurRenderer.markPanelEnd(graphics);
    }
}

