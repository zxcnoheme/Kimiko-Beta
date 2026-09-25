/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Interface;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;

@Feature(value={"interfacecomponentmodule"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00ca\u0001\u0010\b\u0007\u0012\f\b\b\u0012\b\b\fJ\u0004\b\b(\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/InterfaceComponentModule;", "Lrtx/kimiko/api/modules/Module;", "", "name", "description", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "Lrtx/kimiko/api/liteapi/Feature;", "value", "interfacecomponentmodule", "rtx.kimiko:kimiko"})
public abstract class InterfaceComponentModule
extends Module {
    public InterfaceComponentModule(@NotNull String name, @NotNull String description) {
        super(name, description, Category.DISPLAY);
    }
}

