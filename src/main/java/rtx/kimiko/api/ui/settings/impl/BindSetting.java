/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui.settings.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.ui.settings.RenderHelper;
import rtx.kimiko.api.ui.settings.Setting;
import rtx.kimiko.utils.key.KeyBind;
import rtx.kimiko.utils.render.fonts.Fonts;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\r\u0010\u0010\u001a\u00020\t\u00a2\u0006\u0004\b\u0010\u0010\u000bJ\u0015\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\t\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0015\u00a2\u0006\u0004\b\u0017\u0010\u0018J/\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u001d\u0010\u001eJ7\u0010!\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\f2\u0006\u0010 \u001a\u00020\fH\u0016\u00a2\u0006\u0004\b!\u0010\"R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010#R\u0016\u0010$\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010%\u00a8\u0006&"}, d2={"Lrtx/kimiko/api/ui/settings/impl/BindSetting;", "Lrtx/kimiko/api/ui/settings/Setting;", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "backend", "<init>", "(Lrtx/kimiko/api/modules/settings/impl/BindSetting;)V", "", "name", "()Ljava/lang/String;", "", "isVisible", "()Z", "", "height", "()F", "preferredWidth", "isListening", "l", "", "setListening", "(Z)V", "", "k", "setKey", "(I)V", "x", "y", "w", "alpha", "render", "(FFFF)V", "mx", "my", "click", "(FFFFF)Z", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "listening", "Z", "rtx.kimiko:kimiko"})
public class BindSetting
implements Setting {
    @NotNull
    private final rtx.kimiko.api.modules.settings.impl.BindSetting backend;
    private boolean listening;

    public BindSetting(@NotNull rtx.kimiko.api.modules.settings.impl.BindSetting backend) {
        Intrinsics.checkNotNullParameter((Object)backend, (String)"backend");
        this.backend = backend;
    }

    @Override
    @NotNull
    public String name() {
        return this.backend.getName();
    }

    @Override
    public boolean isVisible() {
        return this.backend.isVisible();
    }

    @Override
    public float height() {
        return 16.0f;
    }

    @Override
    public float preferredWidth() {
        String keyText = new KeyBind(this.backend.getKey()).getDisplayName();
        return 6.0f + Fonts.MEDIUM.width(this.backend.getDisplayName(), 6.5f) + 6.0f + Fonts.MEDIUM.width(keyText, 6.0f) + 10.0f + 8.0f;
    }

    public final boolean isListening() {
        return this.listening;
    }

    public final void setListening(boolean l) {
        this.listening = l;
    }

    public final void setKey(int k) {
        this.backend.setKey(k);
    }

    @Override
    public void render(float x, float y, float w, float alpha) {
        String keyText = this.listening ? "Press key..." : new KeyBind(this.backend.getKey()).getDisplayName();
        float keyW = Fonts.MEDIUM.width(keyText, 6.0f) + (float)10;
        float btnX = x + w - keyW - (float)4;
        float btnY = y + 2.0f;
        RenderHelper.drawName(this.backend.getDisplayName(), x, y, btnX - (x + 6.0f) - 4.0f, alpha);
        RenderHelper.drawBtn(btnX, btnY, keyW, 12.0f, keyText, alpha);
    }

    @Override
    public boolean click(float x, float y, float w, float mx, float my) {
        String keyText = this.listening ? "Press key..." : new KeyBind(this.backend.getKey()).getDisplayName();
        float keyW = Fonts.MEDIUM.width(keyText, 6.0f) + (float)10;
        float btnX = x + w - keyW - (float)4;
        float btnY = y + 2.0f;
        if (mx >= btnX && mx <= btnX + keyW && my >= btnY && my <= btnY + 12.0f) {
            this.listening = !this.listening;
            return true;
        }
        return false;
    }
}

