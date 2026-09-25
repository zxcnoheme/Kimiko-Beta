/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bf\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006\u0003\u00c0\u0006\u0001"}, d2={"Lrtx/kimiko/IMinecraft;", "", "Companion", "rtx.kimiko:kimiko"})
public interface IMinecraft {
    @NotNull
    public static final Companion Companion = rtx.kimiko.IMinecraft.Companion.$$INSTANCE;
    @JvmField
    @NotNull
    public static final MinecraftClient mc = MinecraftClient.getInstance();

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0001\u00a8\u0006\b"}, d2={"Lrtx/kimiko/IMinecraft.Companion;", "", "<init>", "()V", "Lnet/minecraft/MinecraftClient;", "Lkotlin/jvm/JvmField;", "mc", "Lnet/minecraft/MinecraftClient;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE;

        private Companion() {
        }

        static {
            $$INSTANCE = new Companion();
        }
    }
}

