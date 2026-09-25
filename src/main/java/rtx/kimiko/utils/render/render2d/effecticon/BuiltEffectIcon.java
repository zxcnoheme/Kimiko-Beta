/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  net.minecraft.entity.effect.StatusEffect
 *  net.minecraft.entity.effect.StatusEffectInstance
 *  net.minecraft.registry.entry.RegistryEntry
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.effecticon;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B7\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\u000b\u0010\fB1\b\u0016\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u00a2\u0006\u0004\b\u000b\u0010\rB+\b\u0016\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u00a2\u0006\u0004\b\u000b\u0010\u0010B3\b\u0016\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\u000b\u0010\u0011R!\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0012\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u0013R\u0019\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0012\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0014R\u0019\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0012\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0014R\u0019\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0012\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0014R\u0019\u0010\n\u001a\u00020\t8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0012\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0015\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/utils/render/render2d/effecticon/BuiltEffectIcon;", "", "Lnet/minecraft/RegistryEntry;", "Lnet/minecraft/StatusEffect;", "effect", "", "x", "y", "size", "", "color", "<init>", "(Lnet/minecraft/RegistryEntry;FFFI)V", "(Lnet/minecraft/RegistryEntry;FFF)V", "Lnet/minecraft/StatusEffectInstance;", "instance", "(Lnet/minecraft/StatusEffectInstance;FFF)V", "(Lnet/minecraft/StatusEffectInstance;FFFI)V", "Lkotlin/jvm/JvmField;", "Lnet/minecraft/RegistryEntry;", "F", "I", "Companion", "rtx.kimiko:kimiko"})
public final class BuiltEffectIcon {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    @Nullable
    public final RegistryEntry<StatusEffect> effect;
    @JvmField
    public final float x;
    @JvmField
    public final float y;
    @JvmField
    public final float size;
    @JvmField
    public final int color;
    @JvmField
    public static final int DEFAULT_COLOR = -1;

    public BuiltEffectIcon(@Nullable RegistryEntry<StatusEffect> effect, float x, float y, float size, int color) {
        this.effect = effect;
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    public BuiltEffectIcon(@Nullable RegistryEntry<StatusEffect> effect, float x, float y, float size) {
        this(effect, x, y, size, DEFAULT_COLOR);
    }

    public BuiltEffectIcon(@Nullable StatusEffectInstance instance, float x, float y, float size) {
        this(instance != null ? instance.getEffectType() : null, x, y, size, DEFAULT_COLOR);
    }

    public BuiltEffectIcon(@Nullable StatusEffectInstance instance, float x, float y, float size, int color) {
        this(instance != null ? instance.getEffectType() : null, x, y, size, color);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087D\u0092\u0002\u0002\b\u0005\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/utils/render/render2d/effecticon/BuiltEffectIcon.Companion;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmField;", "DEFAULT_COLOR", "I", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

