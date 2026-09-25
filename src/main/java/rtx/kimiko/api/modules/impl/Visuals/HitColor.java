/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.render.entity.state.LivingEntityRenderState
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.awt.Color;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"hitcolor"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u0004H\u0015b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\t\u0010\u0003R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\f\u00ca\u0001\u0010\b\u000e\u0012\f\b\u0006\u0012\b\b\fJ\u0004\b\b(\u000f\u00a8\u0006\u0010"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/HitColor;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "onDisable", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "color", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "hitcolor", "rtx.kimiko:kimiko"})
public final class HitColor
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ColorSetting color = (ColorSetting)this.register((Setting)new ColorSetting("Цвет", "Цвет игрока при получении урона.", new Color(255, 110, 110, 255)));
    @NotNull
    private static final Map<LivingEntityRenderState, Integer> renderTints;
    @JvmField
    @Nullable
    public static HitColor INSTANCE;

    public HitColor() {
        super("Hit Color", "Меняет цвет игрока при получении урона.", Category.VISUALS);
        INSTANCE = this;
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onDisable() {
        renderTints.clear();
    }

    @JvmStatic
    public static final boolean shouldTint(@Nullable LivingEntity entity) {
        return Companion.shouldTint(entity);
    }

    @JvmStatic
    public static final int color() {
        return Companion.color();
    }

    @JvmStatic
    public static final void captureTint(@NotNull LivingEntityRenderState state, @Nullable LivingEntity entity) {
        Companion.captureTint(state, entity);
    }

    @JvmStatic
    @Nullable
    public static final Integer tintFor(@NotNull LivingEntityRenderState state) {
        return Companion.tintFor(state);
    }

    static {
        Map map = Collections.synchronizedMap(new WeakHashMap());
        Intrinsics.checkNotNullExpressionValue(map, (String)"synchronizedMap(...)");
        renderTints = map;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0013\u0010\u000b\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ%\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0012\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\rH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015R \u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\u00168\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u001d\u0010\u001b\u001a\u0004\u0018\u00010\u00198\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u001a\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001c\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/HitColor.Companion;", "", "<init>", "()V", "Lnet/minecraft/LivingEntity;", "entity", "", "Lkotlin/jvm/JvmStatic;", "shouldTint", "(Lnet/minecraft/LivingEntity;)Z", "", "color", "()I", "Lnet/minecraft/LivingEntityRenderState;", "state", "", "captureTint", "(Lnet/minecraft/LivingEntityRenderState;Lnet/minecraft/LivingEntity;)V", "tintFor", "(Lnet/minecraft/LivingEntityRenderState;)Ljava/lang/Integer;", "isActive", "()Z", "", "renderTints", "Ljava/util/Map;", "Lrtx/kimiko/api/modules/impl/Visuals/HitColor;", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/HitColor;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final boolean shouldTint(@Nullable LivingEntity entity) {
            return this.isActive() && entity instanceof PlayerEntity && ((PlayerEntity)entity).hurtTime > 0;
        }

        @JvmStatic
        public final int color() {
            HitColor inst = INSTANCE;
            Object object = inst;
            return object != null && (object = ((HitColor)object).color) != null ? ((ColorSetting)object).getColor() : -1;
        }

        @JvmStatic
        public final void captureTint(@NotNull LivingEntityRenderState state, @Nullable LivingEntity entity) {
            Intrinsics.checkNotNullParameter((Object)state, (String)"state");
            if (!this.isActive()) {
                return;
            }
            if (this.shouldTint(entity)) {
                renderTints.put(state, this.color());
            } else if (!renderTints.isEmpty()) {
                renderTints.remove(state);
            }
        }

        @JvmStatic
        @Nullable
        public final Integer tintFor(@NotNull LivingEntityRenderState state) {
            Intrinsics.checkNotNullParameter((Object)state, (String)"state");
            return this.isActive() ? (Integer)renderTints.get(state) : null;
        }

        private final boolean isActive() {
            HitColor inst = INSTANCE;
            return inst != null && inst.isEnabled();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

