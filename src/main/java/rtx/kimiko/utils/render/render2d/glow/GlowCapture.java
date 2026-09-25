/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.TextureSetup
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.render2d.glow;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.texture.TextureSetup;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003R\u001b\u0010\b\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0007\u00a2\u0006\u0006\n\u0004\b\b\u0010\tR\u001b\u0010\n\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0007\u00a2\u0006\u0006\n\u0004\b\n\u0010\tR\u001b\u0010\u000b\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0007\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\tR\u001b\u0010\f\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0007\u00a2\u0006\u0006\n\u0004\b\f\u0010\tR\u001b\u0010\u000e\u001a\u00020\r8\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0007\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0011\u001a\u00020\u00108\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0007\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0013"}, d2={"Lrtx/kimiko/utils/render/render2d/glow/GlowCapture;", "", "<init>", "()V", "", "reset", "", "Lkotlin/jvm/JvmField;", "regionU0", "F", "regionV0", "regionUW", "regionVH", "", "index", "I", "Lnet/minecraft/TextureSetup;", "setup", "Lnet/minecraft/TextureSetup;", "rtx.kimiko:kimiko"})
public final class GlowCapture {
    @JvmField
    public float regionU0;
    @JvmField
    public float regionV0;
    @JvmField
    public float regionUW = 1.0f;
    @JvmField
    public float regionVH = 1.0f;
    @JvmField
    public int index;
    @JvmField
    @NotNull
    public TextureSetup setup;

    public GlowCapture() {
        TextureSetup textureSetup2 = TextureSetup.empty();
        Intrinsics.checkNotNullExpressionValue((Object)textureSetup2, (String)"noTexture(...)");
        this.setup = textureSetup2;
    }

    public final void reset() {
        this.regionU0 = 0.0f;
        this.regionV0 = 0.0f;
        this.regionUW = 1.0f;
        this.regionVH = 1.0f;
        TextureSetup textureSetup2 = TextureSetup.empty();
        Intrinsics.checkNotNullExpressionValue((Object)textureSetup2, (String)"noTexture(...)");
        this.setup = textureSetup2;
    }
}

