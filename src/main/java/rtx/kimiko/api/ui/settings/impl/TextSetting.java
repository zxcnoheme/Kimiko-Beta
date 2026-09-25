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
 *  org.lwjgl.glfw.GLFW
 */
package rtx.kimiko.api.ui.settings.impl;

import java.awt.Color;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import rtx.kimiko.api.ui.settings.RenderHelper;
import rtx.kimiko.api.ui.settings.Setting;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u000b\b\u0016\u0018\u0000 &2\u00020\u0001:\u0001&B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\r\u0010\u0010\u001a\u00020\t\u00a2\u0006\u0004\b\u0010\u0010\u000bJ/\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0017J7\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001c\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0015\u0010!\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001c\u00a2\u0006\u0004\b!\u0010\"J\u000f\u0010#\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b#\u0010$R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010%\u00a8\u0006'"}, d2={"Lrtx/kimiko/api/ui/settings/impl/TextSetting;", "Lrtx/kimiko/api/ui/settings/Setting;", "Lrtx/kimiko/api/modules/settings/impl/TextSetting;", "backend", "<init>", "(Lrtx/kimiko/api/modules/settings/impl/TextSetting;)V", "", "name", "()Ljava/lang/String;", "", "isVisible", "()Z", "", "height", "()F", "preferredWidth", "isFocused", "x", "y", "w", "alpha", "", "render", "(FFFF)V", "mx", "my", "click", "(FFFFF)Z", "", "codepoint", "typeChar", "(I)V", "key", "typeKey", "(I)Z", "paste", "()V", "Lrtx/kimiko/api/modules/settings/impl/TextSetting;", "Companion", "rtx.kimiko:kimiko"})
public class TextSetting
implements Setting {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final rtx.kimiko.api.modules.settings.impl.TextSetting backend;
    private static final float FIELD_W = 92.0f;
    private static final float FONT_SIZE = 6.0f;
    @Nullable
    private static TextSetting focused;

    public TextSetting(@NotNull rtx.kimiko.api.modules.settings.impl.TextSetting backend) {
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
        return 6.0f + Fonts.MEDIUM.width(this.backend.getDisplayName(), 6.5f) + 6.0f + 92.0f + 8.0f;
    }

    public final boolean isFocused() {
        return focused == this;
    }

    @Override
    public void render(float x, float y, float w, float alpha) {
        float fieldH = 12.0f;
        float fieldX = x + w - 92.0f - 4.0f;
        float fieldY = y + (16.0f - fieldH) * 0.5f;
        boolean focus = this.isFocused();
        RenderHelper.drawName(this.backend.getDisplayName(), x, y, fieldX - (x + 6.0f) - 4.0f, alpha);
        Render2D.rect(fieldX, fieldY, 92.0f, fieldH, 3.0f, new Color(0, 0, 0, (int)(60.0f * alpha)).getRGB());
        if (focus) {
            Render2D.outline(fieldX, fieldY, 92.0f, fieldH, 3.0f, 0.6f, ClientAccent.accentBrightAt(190.0f * alpha, fieldX + 46.0f, fieldY + fieldH * 0.5f));
        }
        String value = this.backend.getValue();
        boolean empty = value == null || value.isEmpty();
        String shown = empty ? (this.backend.getPlaceholder().isEmpty() ? "..." : this.backend.getDisplayPlaceholder()) : value;
        float maxTextW = 82.0f;
        String draw = shown;
        while (draw.length() > 1 && Fonts.MEDIUM.width(draw, 6.0f) > maxTextW) {
            draw = draw.substring(1);
        }
        int textColor = empty ? new Color(255, 255, 255, (int)((float)110 * alpha)).getRGB() : ClientAccent.accentSoftAt((float)220 * alpha, fieldX + (float)5, fieldY + 2.5f);
        Fonts.MEDIUM.draw(draw, fieldX + (float)5, fieldY + 2.5f, 6.0f, textColor);
        if (focus && System.currentTimeMillis() / 500L % 2L == 0L) {
            float caretX = fieldX + (float)5 + (empty ? 0.0f : Fonts.MEDIUM.width(draw, 6.0f)) + 0.5f;
            Render2D.rect(caretX, fieldY + 2.0f, 0.8f, fieldH - 4.0f, 0.0f, ClientAccent.accentBrightAt((float)220 * alpha, caretX, fieldY + fieldH * 0.5f));
        }
    }

    @Override
    public boolean click(float x, float y, float w, float mx, float my) {
        float fieldH = 12.0f;
        float fieldX = x + w - 92.0f - (float)4;
        float fieldY = y + (16.0f - fieldH) * 0.5f;
        if (mx >= fieldX && mx <= fieldX + 92.0f && my >= fieldY && my <= fieldY + fieldH) {
            focused = this.isFocused() ? null : this;
            return true;
        }
        if (this.isFocused()) {
            focused = null;
        }
        return false;
    }

    public final void typeChar(int codepoint) {
        if (Character.isISOControl(codepoint)) {
            return;
        }
        String value = this.backend.getValue();
        if (value.length() >= this.backend.getMaxLength()) {
            return;
        }
        char[] cArray = Character.toChars(codepoint);
        Intrinsics.checkNotNullExpressionValue((Object)cArray, (String)"toChars(...)");
        char[] cArray2 = cArray;
        this.backend.setText(value + new String(cArray2));
    }

    public final boolean typeKey(int key) {
        if (key == 86 && TextSetting.Companion.ctrlHeld()) {
            this.paste();
            return true;
        }
        return switch (key) {
            case 259 -> {
                String value = this.backend.getValue();
                if (((CharSequence)value).length() > 0) {
                    String v0 = value.substring(0, value.length() - 1);
                    Intrinsics.checkNotNullExpressionValue((Object)v0, (String)"substring(...)");
                    this.backend.setText(v0);
                }
                yield true;
            }
            case 256, 257, 335 -> {
                focused = null;
                yield true;
            }
            default -> false;
        };
    }

    private final void paste() {
        try {
            String string = MinecraftClient.getInstance().keyboard.getClipboard();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getClipboard(...)");
            String clipboard = string;
            if (((CharSequence)clipboard).length() == 0) {
                return;
            }
            StringBuilder builder = new StringBuilder(this.backend.getValue());
            for (int i = 0; i < clipboard.length() && builder.length() < this.backend.getMaxLength(); ++i) {
                char c = clipboard.charAt(i);
                if (Character.isISOControl(c)) continue;
                builder.append(c);
            }
            this.backend.setText(builder.toString());
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    @JvmStatic
    public static final void unfocusAll() {
        Companion.unfocusAll();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u000f\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\b\u0010\tR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\fR\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/ui/settings/impl/TextSetting.Companion;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "unfocusAll", "", "ctrlHeld", "()Z", "", "FIELD_W", "F", "FONT_SIZE", "Lrtx/kimiko/api/ui/settings/impl/TextSetting;", "focused", "Lrtx/kimiko/api/ui/settings/impl/TextSetting;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final void unfocusAll() {
            focused = null;
        }

        private final boolean ctrlHeld() {
            long window = MinecraftClient.getInstance().getWindow().getHandle();
            return GLFW.glfwGetKey((long)window, (int)341) == 1 || GLFW.glfwGetKey((long)window, (int)345) == 1 || GLFW.glfwGetKey((long)window, (int)343) == 1 || GLFW.glfwGetKey((long)window, (int)347) == 1;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

