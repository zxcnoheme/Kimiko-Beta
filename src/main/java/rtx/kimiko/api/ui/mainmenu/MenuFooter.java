/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.mainmenu;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.ui.mainmenu.MainMenuTab;
import rtx.kimiko.api.ui.mainmenu.MenuTheme;
import rtx.kimiko.api.ui.window.MainWindow;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003JO\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0014\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0016\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0015\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuFooter;", "", "<init>", "()V", "", "x", "y", "w", "h", "dt", "alpha", "appear", "Lrtx/kimiko/api/ui/mainmenu/MainMenuTab;", "tab", "", "render", "(FFFFFFFLrtx/kimiko/api/ui/mainmenu/MainMenuTab;)V", "hintAlpha", "F", "", "hintText", "Ljava/lang/String;", "hintTarget", "Companion", "rtx.kimiko:kimiko"})
public final class MenuFooter {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private float hintAlpha;
    @NotNull
    private String hintText = "";
    @NotNull
    private String hintTarget = "";
    @NotNull
    private static final String SITE = "kimiko.su";
    private static final float SIZE = 4.5f;

    public final void render(float x, float y, float w, float h, float dt, float alpha, float appear, @Nullable MainMenuTab tab) {
        float slide = MenuTheme.stagger(appear, 2, 3, 0.2f);
        float rise = (1.0f - MenuTheme.ease(slide)) * 12.0f;
        float footAlpha = alpha * MenuTheme.clamp01(slide * 1.4f);
        if (footAlpha <= 0.004f) {
            return;
        }
        float centerY = y + rise + h * 0.5f - 2.79f;
        Fonts.SEMIBOLD.draw(SITE, x, centerY, 4.5f, MenuTheme.accent(190.0f, footAlpha));
        float siteW = Fonts.SEMIBOLD.width(SITE, 4.5f);
        Fonts.MEDIUM.draw("\u00b7", x + siteW + 4.0f, centerY, 4.5f, MenuTheme.inkFaint(footAlpha));
        Fonts.MEDIUM.draw(MainWindow.getMinecraftVersionName(), x + siteW + 9.0f, centerY, 4.5f, MenuTheme.inkMuted(footAlpha));
        String target = (tab != null && tab.hint() != null) ? tab.hint() : "";
        if (!Intrinsics.areEqual(target, this.hintTarget)) {
            this.hintTarget = target;
            this.hintAlpha = 0.0f;
        }
        this.hintAlpha = MenuTheme.approach(this.hintAlpha, 1.0f, dt, 8.0f);
        if (((CharSequence)this.hintTarget).length() > 0) {
            this.hintText = this.hintTarget;
        }
        if (((CharSequence)this.hintText).length() > 0) {
            float hintW = Fonts.MEDIUM.width(this.hintText, 4.5f);
            Fonts.MEDIUM.draw(this.hintText, x + (w - hintW) * 0.5f, centerY + (1.0f - this.hintAlpha) * 3.0f, 4.5f, MenuTheme.inkMuted(footAlpha * this.hintAlpha));
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient minecraft = minecraftClient2;
        String fps = String.valueOf(minecraft.getCurrentFps());
        String label = I18n.tr(" кадров");
        float valueW = Fonts.SEMIBOLD.width(fps, 4.5f);
        float labelW = Fonts.MEDIUM.width(label, 4.5f);
        float right = x + w;
        Fonts.SEMIBOLD.draw(fps, right - valueW - labelW, centerY, 4.5f, MenuTheme.ink(footAlpha * 0.85f));
        Fonts.MEDIUM.draw(label, right - labelW, centerY, 4.5f, MenuTheme.inkFaint(footAlpha));
        Render2D.rect(x, y + rise - 1.5f, w, 0.55f, 0.0f, MenuTheme.white(14.0f, footAlpha));
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuFooter.Companion;", "", "<init>", "()V", "", "SITE", "Ljava/lang/String;", "", "SIZE", "F", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

