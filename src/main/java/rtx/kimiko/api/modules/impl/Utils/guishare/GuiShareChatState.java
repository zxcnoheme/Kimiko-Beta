/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils.guishare;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u0000 !2\u00020\u0001:\u0001!B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u00a2\u0006\u0004\b\t\u0010\nJ\r\u0010\u000b\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\fJ\u0010\u0010\u000e\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0011J8\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006H\u00c6\u0001\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u001b\u0010\u0017\u001a\u00020\u00022\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0011\u0010\u0019\u001a\u00020\u0006H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0019\u0010\u0011J\u0011\u0010\u001b\u001a\u00020\u001aH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001b\u0010\u001cR\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001d\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u001eR\u0019\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001d\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u001fR\u0019\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001d\u00a2\u0006\u0006\n\u0004\b\u0007\u0010 R\u0019\u0010\b\u001a\u00020\u00068\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001d\u00a2\u0006\u0006\n\u0004\b\b\u0010 \u00a8\u0006\""}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareChatState;", "Ljava/lang/Record;", "", "messengerOpen", "", "messengerScroll", "", "headerModal", "selectedCount", "<init>", "(ZFII)V", "headerModalOpen", "()Z", "component1", "component2", "()F", "component3", "()I", "component4", "copy", "(ZFII)Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareChatState;", "", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmField;", "Z", "F", "I", "Companion", "rtx.kimiko:kimiko"})
public final class GuiShareChatState
{
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    public final boolean messengerOpen;
    @JvmField
    public final float messengerScroll;
    @JvmField
    public final int headerModal;
    @JvmField
    public final int selectedCount;
    @JvmField
    @NotNull
    public static final GuiShareChatState HIDDEN = new GuiShareChatState(false, 0.0f, -1, 0);

    public GuiShareChatState(boolean messengerOpen, float messengerScroll, int headerModal, int selectedCount) {
        this.messengerOpen = messengerOpen;
        this.messengerScroll = messengerScroll;
        this.headerModal = headerModal;
        this.selectedCount = selectedCount;
    }

    public final boolean headerModalOpen() {
        return this.headerModal >= 0;
    }

    public final boolean component1() {
        return this.messengerOpen;
    }

    public final float component2() {
        return this.messengerScroll;
    }

    public final int component3() {
        return this.headerModal;
    }

    public final int component4() {
        return this.selectedCount;
    }

    @NotNull
    public final GuiShareChatState copy(boolean messengerOpen, float messengerScroll, int headerModal, int selectedCount) {
        return new GuiShareChatState(messengerOpen, messengerScroll, headerModal, selectedCount);
    }

    public static /* synthetic */ GuiShareChatState copy$default(GuiShareChatState guiShareChatState, boolean bl, float f, int n, int n2, int n3, Object object) {
        if ((n3 & 1) != 0) {
            bl = guiShareChatState.messengerOpen;
        }
        if ((n3 & 2) != 0) {
            f = guiShareChatState.messengerScroll;
        }
        if ((n3 & 4) != 0) {
            n = guiShareChatState.headerModal;
        }
        if ((n3 & 8) != 0) {
            n2 = guiShareChatState.selectedCount;
        }
        return guiShareChatState.copy(bl, f, n, n2);
    }

    @Override
    @NotNull
    public String toString() {
        return "GuiShareChatState(messengerOpen=" + this.messengerOpen + ", messengerScroll=" + this.messengerScroll + ", headerModal=" + this.headerModal + ", selectedCount=" + this.selectedCount + ")";
    }

    @Override
    public int hashCode() {
        int result = Boolean.hashCode(this.messengerOpen);
        result = result * 31 + Float.hashCode(this.messengerScroll);
        result = result * 31 + Integer.hashCode(this.headerModal);
        result = result * 31 + Integer.hashCode(this.selectedCount);
        return result;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GuiShareChatState)) {
            return false;
        }
        GuiShareChatState guiShareChatState = (GuiShareChatState)other;
        if (this.messengerOpen != guiShareChatState.messengerOpen) {
            return false;
        }
        if (Float.compare(this.messengerScroll, guiShareChatState.messengerScroll) != 0) {
            return false;
        }
        if (this.headerModal != guiShareChatState.headerModal) {
            return false;
        }
        return this.selectedCount == guiShareChatState.selectedCount;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareChatState.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareChatState;", "Lkotlin/jvm/JvmField;", "HIDDEN", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareChatState;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

