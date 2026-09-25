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
package rtx.kimiko.api.drags;

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
@kotlin.annotation.Target(allowedTargets={AnnotationTarget.CLASS})
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00ca\u0001\u000e\b\u0004\u0012\n\b\u0005\u0012\u0006\b\n0\u00068\u0007\u00ca\u0001\u000e\b\b\u0012\n\b\u0005\u0012\u0006\b\n0\t8\n\u00ca\u0001\u0012\b\u000b\u0012\u000e\b\f\u0012\n\b\fJ\u0006\b\n0\r8\u000e\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/api/drags/RenderUnderHand;", "", "<init>", "()V", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "OFF", "Lkotlin/annotation/Retention;", "Lkotlin/annotation/AnnotationRetention;", "RUNTIME", "Lkotlin/annotation/Target;", "allowedTargets", "Lkotlin/annotation/AnnotationTarget;", "CLASS", "rtx.kimiko:kimiko"})
public @interface RenderUnderHand {
}

