/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui.settings.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.ui.settings.Setting;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\t\b\u0016\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u000bJ/\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016J7\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u001b\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/api/ui/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/ui/settings/Setting;", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "backend", "<init>", "(Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;)V", "", "name", "()Ljava/lang/String;", "", "height", "()F", "", "isVisible", "()Z", "preferredWidth", "x", "y", "w", "alpha", "", "render", "(FFFF)V", "mx", "my", "click", "(FFFFF)Z", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Companion", "rtx.kimiko:kimiko"})
public class SeparatorSetting
implements Setting {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final rtx.kimiko.api.modules.settings.impl.SeparatorSetting backend;
    public static final float HEIGHT = 18.0f;

    public SeparatorSetting(@NotNull rtx.kimiko.api.modules.settings.impl.SeparatorSetting backend) {
        Intrinsics.checkNotNullParameter((Object)backend, (String)"backend");
        this.backend = backend;
    }

    @Override
    @NotNull
    public String name() {
        return this.backend.getName();
    }

    @Override
    public float height() {
        return 18.0f;
    }

    @Override
    public boolean isVisible() {
        return this.backend.isVisible();
    }

    @Override
    public float preferredWidth() {
        return Fonts.MEDIUM.width(this.backend.getDisplayName(), 6.0f) + 48.0f;
    }

    @Override
    public void render(float x, float y, float w, float alpha) {
        float maxTitleW;
        float size;
        String title = this.backend.getDisplayName();
        float titleW = Fonts.MEDIUM.width(title, size = 6.0f);
        if (titleW > (maxTitleW = Math.max(8.0f, w - 24.0f))) {
            titleW = maxTitleW;
        }
        float centerX = x + w / 2.0f;
        float textX = centerX - titleW / 2.0f;
        float textY = y + 9.0f - size / 2.0f;
        float lineY = y + 9.0f;
        float gap = 6.0f;
        float edge = 4.0f;
        int textColor = ClientAccent.accentSoftAt((float)210 * alpha, centerX, textY);
        int lineColor = ClientAccent.accentSoftAt((float)55 * alpha, centerX, lineY);
        float leftX = x + edge;
        float leftW = Math.max(0.0f, textX - gap - leftX);
        float rightX = textX + titleW + gap;
        float rightW = Math.max(0.0f, x + w - edge - rightX);
        if (leftW > 1.0f) {
            Render2D.rect(leftX, lineY, leftW, 1.0f, 0.5f, lineColor);
        }
        if (rightW > 1.0f) {
            Render2D.rect(rightX, lineY, rightW, 1.0f, 0.5f, lineColor);
        }
        Fonts.MEDIUM.draw(title, textX, textY, size, textColor);
    }

    @Override
    public boolean click(float x, float y, float w, float mx, float my) {
        return false;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2={"Lrtx/kimiko/api/ui/settings/impl/SeparatorSetting.Companion;", "", "<init>", "()V", "", "HEIGHT", "F", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

