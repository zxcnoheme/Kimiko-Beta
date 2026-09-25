/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.others;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0019\u0010\u000b\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u0002\u00a2\u0006\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2={"Lrtx/kimiko/utils/render/others/LoadingVisualGuard;", "", "<init>", "()V", "Lnet/minecraft/MinecraftClient;", "client", "", "Lkotlin/jvm/JvmStatic;", "shouldSuppressHud", "(Lnet/minecraft/MinecraftClient;)Z", "visual", "isLoadingVisual", "(Ljava/lang/Object;)Z", "rtx.kimiko:kimiko"})
public final class LoadingVisualGuard {
    @NotNull
    public static final LoadingVisualGuard INSTANCE = new LoadingVisualGuard();

    private LoadingVisualGuard() {
    }

    @JvmStatic
    public static final boolean shouldSuppressHud(@Nullable MinecraftClient client) {
        if (client == null) {
            return false;
        }
        return INSTANCE.isLoadingVisual(client.currentScreen) || INSTANCE.isLoadingVisual(client.getOverlay());
    }

    private final boolean isLoadingVisual(Object visual) {
        if (visual == null) {
            return false;
        }
        String string = visual.getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getSimpleName(...)");
        String string2 = string;
        Locale locale = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
        String string3 = string2.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"toLowerCase(...)");
        String className = string3;
        String string4 = visual.getClass().getName();
        Intrinsics.checkNotNullExpressionValue((Object)string4, (String)"getName(...)");
        String string5 = string4;
        Locale locale2 = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue((Object)locale2, (String)"ROOT");
        String string6 = string5.toLowerCase(locale2);
        Intrinsics.checkNotNullExpressionValue((Object)string6, (String)"toLowerCase(...)");
        String fullName = string6;
        return String.valueOf(className).contains("loading") || String.valueOf(className).contains("progress") || String.valueOf(className).contains("connect") || String.valueOf(className).contains("downloading") || String.valueOf(className).contains("terrain") || String.valueOf(className).contains("generating") || String.valueOf(className).contains("saving") || String.valueOf(className).contains("reload") || String.valueOf(className).contains("resource") || String.valueOf(className).contains("pack") || String.valueOf(className).contains("receiving") || String.valueOf(className).contains("level") || String.valueOf(className).contains("message") || String.valueOf(fullName).contains("mojang");
    }
}

