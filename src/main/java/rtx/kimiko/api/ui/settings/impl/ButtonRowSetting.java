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
import rtx.kimiko.api.modules.settings.impl.ButtonSetting;
import rtx.kimiko.api.ui.settings.RenderHelper;
import rtx.kimiko.api.ui.settings.Setting;
import rtx.kimiko.utils.render.fonts.Fonts;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\r\b\u0016\u0018\u0000  2\u00020\u0001:\u0001 B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u000eJ/\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016J7\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u001b\u0010\u000eJ\u000f\u0010\u001c\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u001c\u0010\bJ\u000f\u0010\u001d\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u001d\u0010\bJ\u000f\u0010\u001e\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u000eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u001f\u00a8\u0006!"}, d2={"Lrtx/kimiko/api/ui/settings/impl/ButtonRowSetting;", "Lrtx/kimiko/api/ui/settings/Setting;", "Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;", "backend", "<init>", "(Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;)V", "", "name", "()Ljava/lang/String;", "", "isVisible", "()Z", "", "height", "()F", "preferredWidth", "x", "y", "w", "alpha", "", "render", "(FFFF)V", "mx", "my", "click", "(FFFFF)Z", "buttonWidth", "safeName", "safeLabel", "safeLabelOffsetX", "Lrtx/kimiko/api/modules/settings/impl/ButtonSetting;", "Companion", "rtx.kimiko:kimiko"})
public class ButtonRowSetting
implements Setting {
    @NotNull
    private static final Companion Companion = new Companion(null);
    @NotNull
    private final ButtonSetting backend;
    private static final float PAD = 4.0f;
    private static final float BH = 12.0f;

    public ButtonRowSetting(@NotNull ButtonSetting backend) {
        Intrinsics.checkNotNullParameter((Object)backend, (String)"backend");
        this.backend = backend;
    }

    @Override
    @NotNull
    public String name() {
        String string;
        try {
            string = this.backend.getName();
        }
        catch (Throwable throwable) {
            string = "";
        }
        return string;
    }

    @Override
    public boolean isVisible() {
        boolean bl;
        try {
            bl = this.backend.isVisible();
        }
        catch (Throwable throwable) {
            bl = true;
        }
        return bl;
    }

    @Override
    public float height() {
        return 16.0f;
    }

    @Override
    public float preferredWidth() {
        return 6.0f + Fonts.MEDIUM.width(this.safeName(), 6.5f) + 6.0f + this.buttonWidth() + 4.0f + 4.0f;
    }

    @Override
    public void render(float x, float y, float w, float alpha) {
        float bw = this.buttonWidth();
        float bx = x + w - bw - 4.0f;
        float by = y + 2.0f;
        RenderHelper.drawName(this.safeName(), x, y, bx - (x + 6.0f) - 4.0f, alpha);
        RenderHelper.drawBtn(bx, by, bw, 12.0f, this.safeLabel(), alpha, this.safeLabelOffsetX());
    }

    @Override
    public boolean click(float x, float y, float w, float mx, float my) {
        float bw = this.buttonWidth();
        float bx = x + w - bw - 4.0f;
        float by = y + 2.0f;
        if (mx >= bx && mx <= bx + bw && my >= by && my <= by + 12.0f) {
            try {
                this.backend.click();
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            return true;
        }
        return false;
    }

    private final float buttonWidth() {
        return Math.max(34.0f, Fonts.MEDIUM.width(this.safeLabel(), 6.0f) + 14.0f);
    }

    private final String safeName() {
        String string;
        try {
            string = this.backend.getDisplayName();
        }
        catch (Throwable throwable) {
            string = "";
        }
        return string;
    }

    private final String safeLabel() {
        String string;
        try {
            string = this.backend.getDisplayLabel();
        }
        catch (Throwable throwable) {
            string = "";
        }
        return string;
    }

    private final float safeLabelOffsetX() {
        float f;
        try {
            f = this.backend.getLabelOffsetX();
        }
        catch (Throwable throwable) {
            f = 0.0f;
        }
        return f;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006\u00a8\u0006\b"}, d2={"Lrtx/kimiko/api/ui/settings/impl/ButtonRowSetting.Companion;", "", "<init>", "()V", "", "PAD", "F", "BH", "rtx.kimiko:kimiko"})
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

