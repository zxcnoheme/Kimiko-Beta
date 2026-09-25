/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package rtx.kimiko.utils.render.render2d;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J#\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nR\u001c\u0010\r\u001a\n \f*\u0004\u0018\u00010\u000b0\u000b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/utils/render/render2d/BatchOverflow;", "", "<init>", "()V", "", "renderer", "", "capacity", "Lkotlin/jvm/JvmStatic;", "drop", "(Ljava/lang/String;I)I", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "LOGGER", "Lorg/slf4j/Logger;", "", "WARNED", "Ljava/util/Set;", "rtx.kimiko:kimiko"})
public final class BatchOverflow {
    @NotNull
    public static final BatchOverflow INSTANCE = new BatchOverflow();
    private static final Logger LOGGER = LoggerFactory.getLogger((String)"Kimiko/Render");
    @NotNull
    private static final Set<String> WARNED;

    private BatchOverflow() {
    }

    @JvmStatic
    public static final int drop(@NotNull String renderer, int capacity) {
        Intrinsics.checkNotNullParameter((Object)renderer, (String)"renderer");
        if (WARNED.add(renderer)) {
            LOGGER.warn("[render] {} batch is full at {} elements per frame; extra elements are not drawn", (Object)renderer, (Object)capacity);
        }
        return -1;
    }

    static {
        ConcurrentHashMap.KeySetView keySetView = ConcurrentHashMap.newKeySet();
        Intrinsics.checkNotNullExpressionValue(keySetView, (String)"newKeySet(...)");
        WARNED = keySetView;
    }
}

