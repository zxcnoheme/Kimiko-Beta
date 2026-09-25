/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.core.uniform;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.core.uniform.UniformLayout;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0018\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\u001d\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0011\u0010\u0012J%\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\f\u00a2\u0006\u0004\b\u0015\u0010\u0016J5\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\f\u00a2\u0006\u0004\b\u0019\u0010\u001aJ%\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u001c\u0010\u001dJ=\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\f\u00a2\u0006\u0004\b\u001f\u0010 J\u001d\u0010\"\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010!\u001a\u00020\u0006\u00a2\u0006\u0004\b\"\u0010\u0012R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010#R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010$R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010%\u00a8\u0006&"}, d2={"Lrtx/kimiko/utils/render/core/uniform/UniformWriter;", "", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "layout", "Ljava/nio/ByteBuffer;", "buffer", "", "baseOffset", "<init>", "(Lrtx/kimiko/utils/render/core/uniform/UniformLayout;Ljava/nio/ByteBuffer;I)V", "", "field", "", "value", "", "putFloat", "(Ljava/lang/String;F)V", "putInt", "(Ljava/lang/String;I)V", "x", "y", "putVec2", "(Ljava/lang/String;FF)V", "z", "w", "putVec4", "(Ljava/lang/String;FFFF)V", "componentIndex", "putFloatAt", "(Ljava/lang/String;IF)V", "index", "putVec4At", "(Ljava/lang/String;IFFFF)V", "argb", "putColor", "Lrtx/kimiko/utils/render/core/uniform/UniformLayout;", "Ljava/nio/ByteBuffer;", "I", "rtx.kimiko:kimiko"})
public final class UniformWriter {
    @NotNull
    private final UniformLayout layout;
    @NotNull
    private final ByteBuffer buffer;
    private final int baseOffset;

    public UniformWriter(@NotNull UniformLayout layout, @NotNull ByteBuffer buffer, int baseOffset) {
        Intrinsics.checkNotNullParameter((Object)layout, (String)"layout");
        Intrinsics.checkNotNullParameter((Object)buffer, (String)"buffer");
        this.layout = layout;
        this.buffer = buffer;
        this.baseOffset = baseOffset;
    }

    public final void putFloat(@NotNull String field, float value) {
        Intrinsics.checkNotNullParameter((Object)field, (String)"field");
        this.buffer.putFloat(this.baseOffset + this.layout.offsetOf(field), value);
    }

    public final void putInt(@NotNull String field, int value) {
        Intrinsics.checkNotNullParameter((Object)field, (String)"field");
        this.buffer.putInt(this.baseOffset + this.layout.offsetOf(field), value);
    }

    public final void putVec2(@NotNull String field, float x, float y) {
        Intrinsics.checkNotNullParameter((Object)field, (String)"field");
        int offset = this.baseOffset + this.layout.offsetOf(field);
        this.buffer.putFloat(offset, x);
        this.buffer.putFloat(offset + 4, y);
    }

    public final void putVec4(@NotNull String field, float x, float y, float z, float w) {
        Intrinsics.checkNotNullParameter((Object)field, (String)"field");
        int offset = this.baseOffset + this.layout.offsetOf(field);
        this.buffer.putFloat(offset, x);
        this.buffer.putFloat(offset + 4, y);
        this.buffer.putFloat(offset + 8, z);
        this.buffer.putFloat(offset + 12, w);
    }

    public final void putFloatAt(@NotNull String field, int componentIndex, float value) {
        Intrinsics.checkNotNullParameter((Object)field, (String)"field");
        this.buffer.putFloat(this.baseOffset + this.layout.offsetOf(field) + componentIndex * 4, value);
    }

    public final void putVec4At(@NotNull String field, int index, float x, float y, float z, float w) {
        Intrinsics.checkNotNullParameter((Object)field, (String)"field");
        int offset = this.baseOffset + this.layout.offsetOf(field, index);
        this.buffer.putFloat(offset, x);
        this.buffer.putFloat(offset + 4, y);
        this.buffer.putFloat(offset + 8, z);
        this.buffer.putFloat(offset + 12, w);
    }

    public final void putColor(@NotNull String field, int argb) {
        Intrinsics.checkNotNullParameter((Object)field, (String)"field");
        this.putVec4(field, (float)(argb >>> 16 & 0xFF) / 255.0f, (float)(argb >>> 8 & 0xFF) / 255.0f, (float)(argb & 0xFF) / 255.0f, (float)(argb >>> 24 & 0xFF) / 255.0f);
    }
}

