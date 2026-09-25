/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.fabricmc.api.ClientModInitializer
 *  net.fabricmc.api.ModInitializer
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.manager.Manager;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \b2\u00020\u00012\u00020\u0002:\u0001\bB\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0004J\u000f\u0010\u0007\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0007\u0010\u0004\u00a8\u0006\t"}, d2={"Lrtx/kimiko/Kimiko;", "Lnet/fabricmc/api/ModInitializer;", "Lnet/fabricmc/api/ClientModInitializer;", "<init>", "()V", "", "onInitialize", "onInitializeClient", "Companion", "rtx.kimiko:kimiko"})
public final class Kimiko
implements ModInitializer,
ClientModInitializer {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String CLIENT_NAME = "Kimiko";
    @NotNull
    public static final String CLIENT_VERSION = "v1.5";

    public void onInitialize() {
    }

    public void onInitializeClient() {
        Manager.init();
    }

    @JvmStatic
    @NotNull
    public static final String namespace() {
        return Companion.namespace();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00048\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\n\u0010\t\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/Kimiko.Companion;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "namespace", "()Ljava/lang/String;", "CLIENT_NAME", "Ljava/lang/String;", "CLIENT_VERSION", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final String namespace() {
            String string = Kimiko.CLIENT_NAME;
            Locale locale = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
            String string2 = string.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
            return string2;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

