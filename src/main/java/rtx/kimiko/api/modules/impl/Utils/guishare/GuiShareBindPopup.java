/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils.guishare;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.ui.BindPopup;
import rtx.kimiko.utils.key.KeyBind;
import rtx.kimiko.utils.key.KeyHelper;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0011\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u0000 (2\u00020\u0001:\u0001(BG\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0010J\u0010\u0010\u0015\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0010J\u0010\u0010\u0016\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0010J\u0010\u0010\u0017\u001a\u00020\nH\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\nH\u00c6\u0003\u00a2\u0006\u0004\b\u0019\u0010\u0018J`\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\nH\u00c6\u0001\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001b\u0010\u001e\u001a\u00020\u00022\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0011\u0010!\u001a\u00020 H\u00d6\u0081\u0004\u00a2\u0006\u0004\b!\u0010\"J\u0011\u0010#\u001a\u00020\u0004H\u00d6\u0081\u0004\u00a2\u0006\u0004\b#\u0010\u0012R\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b$\u00a2\u0006\u0006\n\u0004\b\u0003\u0010%R\u0019\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b$\u00a2\u0006\u0006\n\u0004\b\u0005\u0010&R\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b$\u00a2\u0006\u0006\n\u0004\b\u0006\u0010&R\u0019\u0010\u0007\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b$\u00a2\u0006\u0006\n\u0004\b\u0007\u0010%R\u0019\u0010\b\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b$\u00a2\u0006\u0006\n\u0004\b\b\u0010%R\u0019\u0010\t\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b$\u00a2\u0006\u0006\n\u0004\b\t\u0010%R\u0019\u0010\u000b\u001a\u00020\n8\u0006X\u0087\u0004\u0092\u0002\u0002\b$\u00a2\u0006\u0006\n\u0004\b\u000b\u0010'R\u0019\u0010\f\u001a\u00020\n8\u0006X\u0087\u0004\u0092\u0002\u0002\b$\u00a2\u0006\u0006\n\u0004\b\f\u0010'\u00a8\u0006)"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareBindPopup;", "Ljava/lang/Record;", "", "visible", "", "key", "action", "voice", "hold", "listening", "", "x", "y", "<init>", "(ZLjava/lang/String;Ljava/lang/String;ZZZFF)V", "component1", "()Z", "component2", "()Ljava/lang/String;", "component3", "component4", "component5", "component6", "component7", "()F", "component8", "copy", "(ZLjava/lang/String;Ljava/lang/String;ZZZFF)Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareBindPopup;", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Lkotlin/jvm/JvmField;", "Z", "Ljava/lang/String;", "F", "Companion", "rtx.kimiko:kimiko"})
public final class GuiShareBindPopup
{
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    public final boolean visible;
    @JvmField
    @NotNull
    public final String key;
    @JvmField
    @NotNull
    public final String action;
    @JvmField
    public final boolean voice;
    @JvmField
    public final boolean hold;
    @JvmField
    public final boolean listening;
    @JvmField
    public final float x;
    @JvmField
    public final float y;
    @JvmField
    @NotNull
    public static final GuiShareBindPopup HIDDEN = new GuiShareBindPopup(false, "", "", false, false, false, 0.0f, 0.0f);

    public GuiShareBindPopup(boolean visible, @NotNull String key, @NotNull String action, boolean voice, boolean hold, boolean listening, float x, float y) {
        Intrinsics.checkNotNullParameter((Object)key, (String)"key");
        Intrinsics.checkNotNullParameter((Object)action, (String)"action");
        this.visible = visible;
        this.key = key;
        this.action = action;
        this.voice = voice;
        this.hold = hold;
        this.listening = listening;
        this.x = x;
        this.y = y;
    }

    public final boolean component1() {
        return this.visible;
    }

    @NotNull
    public final String component2() {
        return this.key;
    }

    @NotNull
    public final String component3() {
        return this.action;
    }

    public final boolean component4() {
        return this.voice;
    }

    public final boolean component5() {
        return this.hold;
    }

    public final boolean component6() {
        return this.listening;
    }

    public final float component7() {
        return this.x;
    }

    public final float component8() {
        return this.y;
    }

    @NotNull
    public final GuiShareBindPopup copy(boolean visible, @NotNull String key, @NotNull String action, boolean voice, boolean hold, boolean listening, float x, float y) {
        Intrinsics.checkNotNullParameter((Object)key, (String)"key");
        Intrinsics.checkNotNullParameter((Object)action, (String)"action");
        return new GuiShareBindPopup(visible, key, action, voice, hold, listening, x, y);
    }

    public static /* synthetic */ GuiShareBindPopup copy$default(GuiShareBindPopup guiShareBindPopup, boolean bl, String string, String string2, boolean bl2, boolean bl3, boolean bl4, float f, float f2, int n, Object object) {
        if ((n & 1) != 0) {
            bl = guiShareBindPopup.visible;
        }
        if ((n & 2) != 0) {
            string = guiShareBindPopup.key;
        }
        if ((n & 4) != 0) {
            string2 = guiShareBindPopup.action;
        }
        if ((n & 8) != 0) {
            bl2 = guiShareBindPopup.voice;
        }
        if ((n & 0x10) != 0) {
            bl3 = guiShareBindPopup.hold;
        }
        if ((n & 0x20) != 0) {
            bl4 = guiShareBindPopup.listening;
        }
        if ((n & 0x40) != 0) {
            f = guiShareBindPopup.x;
        }
        if ((n & 0x80) != 0) {
            f2 = guiShareBindPopup.y;
        }
        return guiShareBindPopup.copy(bl, string, string2, bl2, bl3, bl4, f, f2);
    }

    @Override
    @NotNull
    public String toString() {
        return "GuiShareBindPopup(visible=" + this.visible + ", key=" + this.key + ", action=" + this.action + ", voice=" + this.voice + ", hold=" + this.hold + ", listening=" + this.listening + ", x=" + this.x + ", y=" + this.y + ")";
    }

    @Override
    public int hashCode() {
        int result = Boolean.hashCode(this.visible);
        result = result * 31 + this.key.hashCode();
        result = result * 31 + this.action.hashCode();
        result = result * 31 + Boolean.hashCode(this.voice);
        result = result * 31 + Boolean.hashCode(this.hold);
        result = result * 31 + Boolean.hashCode(this.listening);
        result = result * 31 + Float.hashCode(this.x);
        result = result * 31 + Float.hashCode(this.y);
        return result;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GuiShareBindPopup)) {
            return false;
        }
        GuiShareBindPopup guiShareBindPopup = (GuiShareBindPopup)other;
        if (this.visible != guiShareBindPopup.visible) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.key, (Object)guiShareBindPopup.key)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.action, (Object)guiShareBindPopup.action)) {
            return false;
        }
        if (this.voice != guiShareBindPopup.voice) {
            return false;
        }
        if (this.hold != guiShareBindPopup.hold) {
            return false;
        }
        if (this.listening != guiShareBindPopup.listening) {
            return false;
        }
        if (Float.compare(this.x, guiShareBindPopup.x) != 0) {
            return false;
        }
        return Float.compare(this.y, guiShareBindPopup.y) == 0;
    }

    @JvmStatic
    @NotNull
    public static final GuiShareBindPopup capture(@Nullable BindPopup popup, float panelX, float panelY, float panelW, float panelH) {
        return Companion.capture(popup, panelX, panelY, panelW, panelH);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J=\u0010\r\u001a\u00020\u000b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0007b\u0002\b\f\u00a2\u0006\u0004\b\r\u0010\u000eR\u0019\u0010\u0010\u001a\u00020\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000f\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareBindPopup.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/ui/BindPopup;", "popup", "", "panelX", "panelY", "panelW", "panelH", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareBindPopup;", "Lkotlin/jvm/JvmStatic;", "capture", "(Lrtx/kimiko/api/ui/BindPopup;FFFF)Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareBindPopup;", "Lkotlin/jvm/JvmField;", "HIDDEN", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareBindPopup;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final GuiShareBindPopup capture(@Nullable BindPopup popup, float panelX, float panelY, float panelW, float panelH) {
            String key;
            if (popup == null || !popup.isVisible() || popup.module() == null || panelW <= 0.0f || panelH <= 0.0f) {
                return HIDDEN;
            }
            Module module = popup.module();
            if (module == null) {
                return HIDDEN;
            }
            Module module2 = module;
            KeyBind bind = module2.getBind();
            String string = key = bind != null && bind.isBound() ? KeyHelper.getShortName(bind.getCode()) : "None";
            if (module2.getBindType() == Module.BindType.VOICE) {
                key = popup.voiceDisplay();
            }
            String action = module2.getBindType() == Module.BindType.VOICE ? popup.voiceActionText() : "";
            return new GuiShareBindPopup(true, key, action, module2.getBindType() == Module.BindType.VOICE, module2.getBindMode() == Module.BindMode.HOLD, popup.isListening(), (popup.x() - panelX) / panelW, (popup.y() - panelY) / panelH);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

