/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Visuals;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;

@Feature(value={"selftag"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00ca\u0001\u0010\b\u0005\u0012\f\b\u0006\u0012\b\b\fJ\u0004\b\b(\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/SelfTag;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "selftag", "rtx.kimiko:kimiko"})
public final class SelfTag
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);

    public SelfTag() {
        super("Self Tag", "Показывает ванильную табличку с ником над вами (от 3-го лица).", Category.VISUALS);
    }

    @JvmStatic
    public static final boolean active() {
        return Companion.active();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/SelfTag.Companion;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "active", "()Z", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final boolean active() {
            SelfTag module = ModuleManager.Companion.get().get(SelfTag.class);
            return module != null && module.isEnabled();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

