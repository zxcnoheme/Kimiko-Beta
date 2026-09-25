/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.gui.Click
 *  net.minecraft.text.Text
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.text.MutableText
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Visuals.emotions;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.Click;
import net.minecraft.text.Text;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.MutableText;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.IMinecraft;
import rtx.kimiko.api.modules.impl.Visuals.Emotions;
import rtx.kimiko.api.modules.impl.Visuals.emotions.Emotion;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionPlayback;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionWheel;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionWheelOverlay;
import rtx.kimiko.api.ui.BaseScreen;
import rtx.kimiko.utils.sounds.Sounds;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\u0018\u0000 ,2\u00020\u0001:\u0001,B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0014\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\f\u0010\u000bJ/\u0010\u0014\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0012H\u0014\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u0016\u0010\u000bJ\u000f\u0010\u0017\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u0017\u0010\u000bJ\u000f\u0010\u0018\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u0018\u0010\u000bJ\u001f\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001bH\u0016\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\u001bH\u0016\u00a2\u0006\u0004\b\u001f\u0010 R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010!R\u0016\u0010#\u001a\u00020\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0014\u0010&\u001a\u00020%8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0016\u0010(\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010)R\u0016\u0010+\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u0010)\u00a8\u0006-"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionWheelScreen;", "Lrtx/kimiko/api/ui/BaseScreen;", "Lrtx/kimiko/api/modules/impl/Visuals/Emotions;", "module", "", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;", "emotions", "<init>", "(Lrtx/kimiko/api/modules/impl/Visuals/Emotions;Ljava/util/List;)V", "", "init", "()V", "onClose", "Lnet/minecraft/DrawContext;", "graphics", "", "mouseX", "mouseY", "", "partialTick", "renderScreen", "(Lnet/minecraft/DrawContext;IIF)V", "updateInput", "commit", "dismiss", "Lnet/minecraft/Click;", "event", "", "doubleClick", "mouseClicked", "(Lnet/minecraft/Click;Z)Z", "isPauseScreen", "()Z", "Lrtx/kimiko/api/modules/impl/Visuals/Emotions;", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionWheel;", "wheel", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionWheel;", "", "openedAt", "J", "sticky", "Z", "repressed", "dismissed", "Companion", "rtx.kimiko:kimiko"})
public final class EmotionWheelScreen
extends BaseScreen {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Emotions module;
    @NotNull
    private EmotionWheel wheel;
    private final long openedAt;
    private boolean sticky;
    private boolean repressed;
    private boolean dismissed;
    private static final long STICKY_WINDOW_MS = 190L;

    public EmotionWheelScreen(@NotNull Emotions module, @NotNull List<Emotion> emotions) {
        super((Text)Text.literal("Эмоции"));
        Intrinsics.checkNotNullParameter((Object)module, (String)"module");
        Intrinsics.checkNotNullParameter(emotions, (String)"emotions");
        this.module = module;
        this.wheel = new EmotionWheel(emotions);
        this.openedAt = System.currentTimeMillis();
    }

    protected void init() {
        EmotionWheel previous = EmotionWheelOverlay.claim();
        if (previous != null && Intrinsics.areEqual(previous.emotions(), this.wheel.emotions())) {
            this.wheel = previous;
        } else {
            EmotionWheel emotionWheel = previous;
            if (emotionWheel != null) {
                emotionWheel.finish();
            }
        }
        this.wheel.open();
        Sounds.play("gui_open");
    }

    public void close() {
        this.dismiss();
    }

    @Override
    protected void renderScreen(@NotNull DrawContext graphics, int mouseX, int mouseY, float partialTick) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        if (this.wheel.emotions().isEmpty()) {
            this.dismiss();
            return;
        }
        this.wheel.updateHover();
        this.updateInput();
        this.wheel.render(graphics, !this.sticky);
    }

    private final void updateInput() {
        if (this.dismissed) {
            return;
        }
        if (this.module.isMenuKeyDown()) {
            this.repressed = this.sticky;
            return;
        }
        if (this.sticky) {
            if (this.repressed) {
                this.commit();
            }
            return;
        }
        if (System.currentTimeMillis() - this.openedAt <= 190L) {
            this.sticky = true;
            return;
        }
        this.commit();
    }

    private final void commit() {
        if (this.dismissed) {
            return;
        }
        Emotion selected = this.wheel.hoveredEmotion();
        if (selected != null) {
            this.module.playEmotion(selected);
        } else {
            EmotionPlayback.stop();
        }
        this.dismiss();
    }

    private final void dismiss() {
        if (this.dismissed) {
            return;
        }
        this.dismissed = true;
        EmotionWheelOverlay.begin(this.wheel);
        Sounds.play("gui_close");
        if (Intrinsics.areEqual((Object)IMinecraft.mc.currentScreen, (Object)((Object)this))) {
            IMinecraft.mc.setScreen(null);
        }
    }

    public boolean mouseClicked(@NotNull Click event, boolean doubleClick) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (event.button() == 1) {
            this.dismiss();
            return true;
        }
        if (event.button() == 0) {
            this.commit();
            return true;
        }
        return super.mouseClicked(event, doubleClick);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionWheelScreen.Companion;", "", "<init>", "()V", "", "STICKY_WINDOW_MS", "J", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

