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
package rtx.kimiko.api.liteapi;

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
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002\"\u00020\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u00028\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0007\u00ca\u0001\u000e\b\b\u0012\n\b\u0004\u0012\u0006\b\n0\t8\n\u00ca\u0001\u000e\b\u000b\u0012\n\b\u0004\u0012\u0006\b\n0\f8\r\u00ca\u0001\u0012\b\u000e\u0012\u000e\b\u000f\u0012\n\b\fJ\u0006\b\n0\u00108\u0011\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/api/liteapi/Feature;", "", "", "", "value", "<init>", "(Lkotlin/Array;)V", "()[Ljava/lang/String;", "Lsigil/protect/Protect;", "Lsigil/protect/Level;", "OFF", "Lkotlin/annotation/Retention;", "Lkotlin/annotation/AnnotationRetention;", "RUNTIME", "Lkotlin/annotation/Target;", "allowedTargets", "Lkotlin/annotation/AnnotationTarget;", "CLASS", "rtx.kimiko:kimiko"})
public @interface Feature {
    public String[] value();
}

