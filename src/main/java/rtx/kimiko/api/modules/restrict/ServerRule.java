/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.annotation.AnnotationRetention
 *  kotlin.annotation.AnnotationTarget
 *  kotlin.annotation.Retention
 *  kotlin.annotation.Target
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.restrict;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.modules.restrict.Server;
import sigil.protect.Level;
import sigil.protect.Protect;

@Protect(value=Level.OFF)
@kotlin.annotation.Retention(value=AnnotationRetention.RUNTIME)
@kotlin.annotation.Target(allowedTargets={AnnotationTarget.CLASS})
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001:\u0001\fB'\u0012\u0010\b\u0002\u0010\u0004\u001a\u00020\u0002B\u0006\b\n0\u00028\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\nR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\u000b\u00ca\u0001\u000e\b\r\u0012\n\b\u000e\u0012\u0006\b\n0\u000f8\u0010\u00ca\u0001\u000e\b\u0011\u0012\n\b\u000e\u0012\u0006\b\n0\u00128\u0013\u00ca\u0001\u0012\b\u0014\u0012\u000e\b\u0015\u0012\n\b\fJ\u0006\b\n0\u00168\u0017\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/api/modules/restrict/ServerRule;", "", "Lrtx/kimiko/api/modules/restrict/ServerRule$Mode;", "BLOCK", "mode", "", "Lrtx/kimiko/api/modules/restrict/Server;", "servers", "<init>", "(Lrtx/kimiko/api/modules/restrict/ServerRule$Mode;Lkotlin/Array;)V", "()Lrtx/kimiko/api/modules/restrict/ServerRule$Mode;", "()[Lrtx/kimiko/api/modules/restrict/Server;", "Mode", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "OFF", "Lkotlin/annotation/Retention;", "Lkotlin/annotation/AnnotationRetention;", "RUNTIME", "Lkotlin/annotation/Target;", "allowedTargets", "Lkotlin/annotation/AnnotationTarget;", "CLASS", "rtx.kimiko:kimiko"})
public @interface ServerRule {
    public Mode mode() default Mode.BLOCK;

    public Server[] servers();

    @Protect(value=Level.OFF)
    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00ca\u0001\u000e\b\u0007\u0012\n\b\b\u0012\u0006\b\n0\t8\n\u00a8\u0006\u000b"}, d2={"Lrtx/kimiko/api/modules/restrict/ServerRule$Mode;", "", "<init>", "(Ljava/lang/String;I)V", "ONLY", "BLOCK", "HIDE", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "OFF", "rtx.kimiko:kimiko"})
    public static enum Mode {
        ONLY,
        BLOCK,
        HIDE;

        @NotNull
        public static EnumEntries<Mode> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

        
    }
}

