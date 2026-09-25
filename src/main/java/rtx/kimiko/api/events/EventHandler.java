/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.annotation.AnnotationRetention
 *  kotlin.annotation.AnnotationTarget
 *  kotlin.annotation.Retention
 *  kotlin.annotation.Target
 */
package rtx.kimiko.api.events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import sigil.protect.Level;
import sigil.protect.Protect;

@Protect(value=Level.OFF)
@kotlin.annotation.Retention(value=AnnotationRetention.RUNTIME)
@kotlin.annotation.Target(allowedTargets={AnnotationTarget.FUNCTION})
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\b\u0002\u0010\u0003\u001a\u00020\u0002B\u0004\b\u0003\u0010\u0004\u00a2\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0003\u0010\u0006\u00ca\u0001\u000e\b\u0007\u0012\n\b\u0003\u0012\u0006\b\n0\b8\t\u00ca\u0001\u000e\b\n\u0012\n\b\u0003\u0012\u0006\b\n0\u000b8\f\u00ca\u0001\u0012\b\r\u0012\u000e\b\u000e\u0012\n\b\fJ\u0006\b\n0\u000f8\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/events/EventHandler;", "", "", "value", "<init>", "(I)V", "()I", "Lsigil/protect/Protect;", "Lsigil/protect/Level;", "OFF", "Lkotlin/annotation/Retention;", "Lkotlin/annotation/AnnotationRetention;", "RUNTIME", "Lkotlin/annotation/Target;", "allowedTargets", "Lkotlin/annotation/AnnotationTarget;", "FUNCTION", "rtx.kimiko:kimiko"})
public @interface EventHandler {
    public int value() default 2;
}

