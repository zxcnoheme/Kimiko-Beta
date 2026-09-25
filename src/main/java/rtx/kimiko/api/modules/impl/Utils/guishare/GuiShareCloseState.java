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

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u001b\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u0000 22\u00020\u0001:\u00012Bg\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\u0006\u0010\u000e\u001a\u00020\u0004\u0012\u0006\u0010\u000f\u001a\u00020\u0004\u0012\u0006\u0010\u0010\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0018\u0010\u0016J\u0010\u0010\u0019\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001b\u0010\u0016J\u0010\u0010\u001c\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001c\u0010\u0016J\u0010\u0010\u001d\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001d\u0010\u0016J\u0010\u0010\u001e\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001e\u0010\u0016J\u0010\u0010\u001f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001f\u0010\u0016J\u0010\u0010 \u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b \u0010\u0016J\u0010\u0010!\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b!\u0010\u0016J\u0088\u0001\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\"\u0010#J\u001b\u0010&\u001a\u00020\u00022\b\u0010%\u001a\u0004\u0018\u00010$H\u00d6\u0083\u0004\u00a2\u0006\u0004\b&\u0010'J\u0011\u0010)\u001a\u00020(H\u00d6\u0081\u0004\u00a2\u0006\u0004\b)\u0010*J\u0011\u0010,\u001a\u00020+H\u00d6\u0081\u0004\u00a2\u0006\u0004\b,\u0010-R\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b.\u00a2\u0006\u0006\n\u0004\b\u0003\u0010/R\u0019\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b.\u00a2\u0006\u0006\n\u0004\b\u0005\u00100R\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b.\u00a2\u0006\u0006\n\u0004\b\u0006\u00100R\u0019\u0010\u0007\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b.\u00a2\u0006\u0006\n\u0004\b\u0007\u00100R\u0019\u0010\t\u001a\u00020\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b.\u00a2\u0006\u0006\n\u0004\b\t\u00101R\u0019\u0010\n\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b.\u00a2\u0006\u0006\n\u0004\b\n\u00100R\u0019\u0010\u000b\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b.\u00a2\u0006\u0006\n\u0004\b\u000b\u00100R\u0019\u0010\f\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b.\u00a2\u0006\u0006\n\u0004\b\f\u00100R\u0019\u0010\r\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b.\u00a2\u0006\u0006\n\u0004\b\r\u00100R\u0019\u0010\u000e\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b.\u00a2\u0006\u0006\n\u0004\b\u000e\u00100R\u0019\u0010\u000f\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b.\u00a2\u0006\u0006\n\u0004\b\u000f\u00100R\u0019\u0010\u0010\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b.\u00a2\u0006\u0006\n\u0004\b\u0010\u00100\u00a8\u00063"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareCloseState;", "Ljava/lang/Record;", "", "active", "", "openness", "shatterProgress", "screenScale", "", "seed", "rectX", "rectY", "rectW", "rectH", "rectPad", "screenW", "screenH", "<init>", "(ZFFFJFFFFFFF)V", "component1", "()Z", "component2", "()F", "component3", "component4", "component5", "()J", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "(ZFFFJFFFFFFF)Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareCloseState;", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmField;", "Z", "F", "J", "Companion", "rtx.kimiko:kimiko"})
public final class GuiShareCloseState
{
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    public final boolean active;
    @JvmField
    public final float openness;
    @JvmField
    public final float shatterProgress;
    @JvmField
    public final float screenScale;
    @JvmField
    public final long seed;
    @JvmField
    public final float rectX;
    @JvmField
    public final float rectY;
    @JvmField
    public final float rectW;
    @JvmField
    public final float rectH;
    @JvmField
    public final float rectPad;
    @JvmField
    public final float screenW;
    @JvmField
    public final float screenH;
    @JvmField
    @NotNull
    public static final GuiShareCloseState IDLE = new GuiShareCloseState(false, 1.0f, 0.0f, 1.0f, 0L, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);

    public GuiShareCloseState(boolean active, float openness, float shatterProgress, float screenScale, long seed, float rectX, float rectY, float rectW, float rectH, float rectPad, float screenW, float screenH) {
        this.active = active;
        this.openness = openness;
        this.shatterProgress = shatterProgress;
        this.screenScale = screenScale;
        this.seed = seed;
        this.rectX = rectX;
        this.rectY = rectY;
        this.rectW = rectW;
        this.rectH = rectH;
        this.rectPad = rectPad;
        this.screenW = screenW;
        this.screenH = screenH;
    }

    public final boolean component1() {
        return this.active;
    }

    public final float component2() {
        return this.openness;
    }

    public final float component3() {
        return this.shatterProgress;
    }

    public final float component4() {
        return this.screenScale;
    }

    public final long component5() {
        return this.seed;
    }

    public final float component6() {
        return this.rectX;
    }

    public final float component7() {
        return this.rectY;
    }

    public final float component8() {
        return this.rectW;
    }

    public final float component9() {
        return this.rectH;
    }

    public final float component10() {
        return this.rectPad;
    }

    public final float component11() {
        return this.screenW;
    }

    public final float component12() {
        return this.screenH;
    }

    @NotNull
    public final GuiShareCloseState copy(boolean active, float openness, float shatterProgress, float screenScale, long seed, float rectX, float rectY, float rectW, float rectH, float rectPad, float screenW, float screenH) {
        return new GuiShareCloseState(active, openness, shatterProgress, screenScale, seed, rectX, rectY, rectW, rectH, rectPad, screenW, screenH);
    }

    public static /* synthetic */ GuiShareCloseState copy$default(GuiShareCloseState guiShareCloseState, boolean bl, float f, float f2, float f3, long l, float f4, float f5, float f6, float f7, float f8, float f9, float f10, int n, Object object) {
        if ((n & 1) != 0) {
            bl = guiShareCloseState.active;
        }
        if ((n & 2) != 0) {
            f = guiShareCloseState.openness;
        }
        if ((n & 4) != 0) {
            f2 = guiShareCloseState.shatterProgress;
        }
        if ((n & 8) != 0) {
            f3 = guiShareCloseState.screenScale;
        }
        if ((n & 0x10) != 0) {
            l = guiShareCloseState.seed;
        }
        if ((n & 0x20) != 0) {
            f4 = guiShareCloseState.rectX;
        }
        if ((n & 0x40) != 0) {
            f5 = guiShareCloseState.rectY;
        }
        if ((n & 0x80) != 0) {
            f6 = guiShareCloseState.rectW;
        }
        if ((n & 0x100) != 0) {
            f7 = guiShareCloseState.rectH;
        }
        if ((n & 0x200) != 0) {
            f8 = guiShareCloseState.rectPad;
        }
        if ((n & 0x400) != 0) {
            f9 = guiShareCloseState.screenW;
        }
        if ((n & 0x800) != 0) {
            f10 = guiShareCloseState.screenH;
        }
        return guiShareCloseState.copy(bl, f, f2, f3, l, f4, f5, f6, f7, f8, f9, f10);
    }

    @Override
    @NotNull
    public String toString() {
        return "GuiShareCloseState(active=" + this.active + ", openness=" + this.openness + ", shatterProgress=" + this.shatterProgress + ", screenScale=" + this.screenScale + ", seed=" + this.seed + ", rectX=" + this.rectX + ", rectY=" + this.rectY + ", rectW=" + this.rectW + ", rectH=" + this.rectH + ", rectPad=" + this.rectPad + ", screenW=" + this.screenW + ", screenH=" + this.screenH + ")";
    }

    @Override
    public int hashCode() {
        int result = Boolean.hashCode(this.active);
        result = result * 31 + Float.hashCode(this.openness);
        result = result * 31 + Float.hashCode(this.shatterProgress);
        result = result * 31 + Float.hashCode(this.screenScale);
        result = result * 31 + Long.hashCode(this.seed);
        result = result * 31 + Float.hashCode(this.rectX);
        result = result * 31 + Float.hashCode(this.rectY);
        result = result * 31 + Float.hashCode(this.rectW);
        result = result * 31 + Float.hashCode(this.rectH);
        result = result * 31 + Float.hashCode(this.rectPad);
        result = result * 31 + Float.hashCode(this.screenW);
        result = result * 31 + Float.hashCode(this.screenH);
        return result;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GuiShareCloseState)) {
            return false;
        }
        GuiShareCloseState guiShareCloseState = (GuiShareCloseState)other;
        if (this.active != guiShareCloseState.active) {
            return false;
        }
        if (Float.compare(this.openness, guiShareCloseState.openness) != 0) {
            return false;
        }
        if (Float.compare(this.shatterProgress, guiShareCloseState.shatterProgress) != 0) {
            return false;
        }
        if (Float.compare(this.screenScale, guiShareCloseState.screenScale) != 0) {
            return false;
        }
        if (this.seed != guiShareCloseState.seed) {
            return false;
        }
        if (Float.compare(this.rectX, guiShareCloseState.rectX) != 0) {
            return false;
        }
        if (Float.compare(this.rectY, guiShareCloseState.rectY) != 0) {
            return false;
        }
        if (Float.compare(this.rectW, guiShareCloseState.rectW) != 0) {
            return false;
        }
        if (Float.compare(this.rectH, guiShareCloseState.rectH) != 0) {
            return false;
        }
        if (Float.compare(this.rectPad, guiShareCloseState.rectPad) != 0) {
            return false;
        }
        if (Float.compare(this.screenW, guiShareCloseState.screenW) != 0) {
            return false;
        }
        return Float.compare(this.screenH, guiShareCloseState.screenH) == 0;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareCloseState.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareCloseState;", "Lkotlin/jvm/JvmField;", "IDLE", "Lrtx/kimiko/api/modules/impl/Utils/guishare/GuiShareCloseState;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

