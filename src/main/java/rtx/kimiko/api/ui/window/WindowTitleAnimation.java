/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.window;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.profile.ProfileIdentity;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\u0003J\u000f\u0010\t\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\t\u0010\u0003R\u0016\u0010\n\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\f\u0010\u000bR\u0016\u0010\u000e\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000fR\u0016\u0010\u0012\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u000b\u00a8\u0006\u0016"}, d2={"Lrtx/kimiko/api/ui/window/WindowTitleAnimation;", "", "<init>", "()V", "", "currentTitle", "()Ljava/lang/String;", "", "tick", "push", "visible", "Ljava/lang/String;", "target", "", "erasing", "Z", "uidPhase", "", "nextStepAt", "J", "lastPushed", "Companion", "rtx.kimiko:kimiko"})
public final class WindowTitleAnimation {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private String visible = "";
    @NotNull
    private String target = "";
    private boolean erasing;
    private boolean uidPhase;
    private long nextStepAt;
    @Nullable
    private String lastPushed;
    private static final long STEP_MS = 45L;
    private static final long HOLD_MS = 4000L;
    private static final int ERASE_PER_STEP = 2;
    @NotNull
    private static final WindowTitleAnimation INSTANCE = new WindowTitleAnimation();

    private WindowTitleAnimation() {
    }

    @NotNull
    public final String currentTitle() {
        return "Kimiko v1.5  \u2758  " + this.visible;
    }

    public final void tick() {
        long now = System.currentTimeMillis();
        if (now < this.nextStepAt) {
            this.push();
            return;
        }
        String full = WindowTitleAnimation.Companion.phaseText(this.uidPhase);
        if (((CharSequence)this.target).length() == 0) {
            this.target = full;
        }
        if (this.erasing) {
            if (((CharSequence)this.visible).length() == 0) {
                this.uidPhase = !this.uidPhase;
                this.target = WindowTitleAnimation.Companion.phaseText(this.uidPhase);
                this.erasing = false;
                String string = this.target.substring(0, Math.min(1, this.target.length()));
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
                this.visible = string;
            } else {
                String string = this.visible.substring(0, Math.max(0, this.visible.length() - 2));
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
                this.visible = string;
            }
            this.nextStepAt = now + 45L;
        } else {
            if (!Intrinsics.areEqual((Object)this.target, (Object)full) && ((CharSequence)this.visible).length() == 0) {
                this.target = full;
            }
            if (this.visible.length() >= this.target.length()) {
                this.visible = this.target;
                this.erasing = true;
                this.nextStepAt = now + 4000L;
            } else {
                String string = this.target.substring(0, this.visible.length() + 1);
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
                this.visible = string;
                this.nextStepAt = now + 45L;
            }
        }
        this.push();
    }

    private final void push() {
        String title = this.currentTitle();
        if (Intrinsics.areEqual((Object)title, (Object)this.lastPushed)) {
            return;
        }
        this.lastPushed = title;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        if (minecraft.getWindow() != null) {
            minecraft.getWindow().setTitle(title);
        }
    }

    @JvmStatic
    @NotNull
    public static final WindowTitleAnimation get() {
        return Companion.get();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/ui/window/WindowTitleAnimation.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/ui/window/WindowTitleAnimation;", "Lkotlin/jvm/JvmStatic;", "get", "()Lrtx/kimiko/api/ui/window/WindowTitleAnimation;", "", "uid", "", "phaseText", "(Z)Ljava/lang/String;", "profileName", "()Ljava/lang/String;", "", "safeUid", "()I", "", "STEP_MS", "J", "HOLD_MS", "ERASE_PER_STEP", "I", "INSTANCE", "Lrtx/kimiko/api/ui/window/WindowTitleAnimation;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final WindowTitleAnimation get() {
            return INSTANCE;
        }

        private final String phaseText(boolean uid) {
            if (uid) {
                int id = this.safeUid();
                return id > 0 ? "UID " + id : this.profileName() + "'s Profile";
            }
            return this.profileName() + "'s Profile";
        }

        private final String profileName() {
            String string;
            try {
                String string2 = ProfileIdentity.username("Guest");
                if (string2 == null) {
                    string2 = "Guest";
                }
                string = string2;
            }
            catch (Throwable throwable) {
                string = "Guest";
            }
            return string;
        }

        private final int safeUid() {
            int n;
            try {
                n = ProfileIdentity.uid();
            }
            catch (Throwable throwable) {
                n = 0;
            }
            return n;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

