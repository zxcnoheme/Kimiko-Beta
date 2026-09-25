/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  net.minecraft.entity.Entity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.entity;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/utils/entity/EntityVisibility;", "", "<init>", "()V", "Lnet/minecraft/Entity;", "entity", "", "Lkotlin/jvm/JvmStatic;", "isHidden", "(Lnet/minecraft/Entity;)Z", "rtx.kimiko:kimiko"})
public final class EntityVisibility {
    @NotNull
    public static final EntityVisibility INSTANCE = new EntityVisibility();

    private EntityVisibility() {
    }

    @JvmStatic
    public static final boolean isHidden(@Nullable Entity entity) {
        return entity == null || entity.isRemoved() || entity.isSpectator() || entity.isInvisible();
    }
}

