/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui.mainmenu;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.ui.mainmenu.MenuTheme;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0010\u0018\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J+\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u000b\u0010\fJ#\u0010\u000f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0007b\u0002\b\n\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00168\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00168\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0018R\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u001b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001dR\u0016\u0010\u001f\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u0013\u00a8\u0006 "}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuRipples;", "", "<init>", "()V", "", "x", "y", "", "accent", "", "Lkotlin/jvm/JvmStatic;", "spawn", "(FFZ)V", "dt", "alpha", "render", "(FF)V", "", "MAX", "I", "LIFE", "F", "", "rx", "[F", "ry", "age", "", "alive", "[Z", "accented", "cursor", "rtx.kimiko:kimiko"})
public final class MenuRipples {
    @NotNull
    public static final MenuRipples INSTANCE = new MenuRipples();
    private static final int MAX = 6;
    private static final float LIFE = 0.72f;
    @NotNull
    private static final float[] rx = new float[6];
    @NotNull
    private static final float[] ry = new float[6];
    @NotNull
    private static final float[] age = new float[6];
    @NotNull
    private static final boolean[] alive = new boolean[6];
    @NotNull
    private static final boolean[] accented = new boolean[6];
    private static int cursor;

    private MenuRipples() {
    }

    @JvmStatic
    public static final void spawn(float x, float y, boolean accent) {
        int slot = cursor;
        cursor = (cursor + 1) % 6;
        MenuRipples.rx[slot] = x;
        MenuRipples.ry[slot] = y;
        MenuRipples.age[slot] = 0.0f;
        MenuRipples.alive[slot] = true;
        MenuRipples.accented[slot] = accent;
    }

    @JvmStatic
    public static final void render(float dt, float alpha) {
        for (int i = 0; i < 6; ++i) {
            if (!alive[i]) continue;
            float[] fArray = age;
            int n = i;
            fArray[n] = fArray[n] + dt;
            if (age[i] >= 0.72f) {
                MenuRipples.alive[i] = false;
                continue;
            }
            float t = age[i] / 0.72f;
            float eased = 1.0f - (1.0f - t) * (1.0f - t) * (1.0f - t);
            float radius = 6.0f + eased * 46.0f;
            float fade = (1.0f - t) * (1.0f - t);
            float thickness = 1.6f * (1.0f - t) + 0.35f;
            int color = accented[i] ? MenuTheme.accentBright(190.0f * fade, alpha) : MenuTheme.white(150.0f * fade, alpha);
            Render2D.circleOutline(rx[i], ry[i], radius, thickness, color);
            if (!(t < 0.35f)) continue;
            float glowFade = 1.0f - t / 0.35f;
            Render2D.circle(rx[i], ry[i], radius * 0.85f, radius * 0.85f, accented[i] ? MenuTheme.accent(30.0f * glowFade, alpha) : MenuTheme.white(22.0f * glowFade, alpha));
        }
    }
}

