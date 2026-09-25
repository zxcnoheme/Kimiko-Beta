/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.mob.MobEntity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.passive.AnimalEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.restrict.Server;
import rtx.kimiko.api.modules.restrict.ServerRestrictions;
import rtx.kimiko.api.modules.restrict.ServerRule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.MultiSelectSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.network.Network;

@ServerRule(mode=ServerRule.Mode.ONLY, servers={Server.HW})
@Feature(value={"seeinvisible"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\t\u0010\bJ\r\u0010\n\u001a\u00020\u0006\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017\u00ca\u0001\u001e\b\u0019\u0012\n\b\u0010\u0012\u0006\b\n0\u001a8\u001b\u0012\u000e\b\u001c\u0012\n\b\fJ\u0006\b\n0\u001d8\u001e\u00ca\u0001\u0010\b\u001f\u0012\f\b \u0012\b\b\fJ\u0004\b\b(!\u00a8\u0006\""}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/SeeInvisible;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "Lnet/minecraft/LivingEntity;", "entity", "", "shouldReveal", "(Lnet/minecraft/LivingEntity;)Z", "isTargeted", "isSolid", "()Z", "", "ghostTint", "()I", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "mode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "alpha", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "targets", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "Companion", "Lrtx/kimiko/api/modules/restrict/ServerRule;", "Lrtx/kimiko/api/modules/restrict/ServerRule$Mode;", "ONLY", "servers", "Lrtx/kimiko/api/modules/restrict/Server;", "HW", "Lrtx/kimiko/api/liteapi/Feature;", "value", "seeinvisible", "rtx.kimiko:kimiko"})
public final class SeeInvisible
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ModeSetting mode;
    @NotNull
    private final SliderSetting alpha;
    @NotNull
    private final MultiSelectSetting targets;
    @NotNull
    private static final String MODE_GHOST = "Призрак";
    @NotNull
    private static final String MODE_SOLID = "Полный";
    @NotNull
    private static final String TARGET_PLAYERS = "Игроки";
    @NotNull
    private static final String TARGET_MOBS = "Мобы";
    @NotNull
    private static final String TARGET_ANIMALS = "Животные";
    @JvmField
    @Nullable
    public static SeeInvisible INSTANCE;

    public SeeInvisible() {
        super("See Invisible", "Показывает невидимых игроков в прямой видимости. Работает только на HolyWorld.", Category.VISUALS);
        String[] stringArray = new String[]{MODE_GHOST, MODE_SOLID};
        this.mode = (ModeSetting)this.register((Setting)new ModeSetting("Режим", "Призрак — полупрозрачный силуэт как у ванильной невидимости. Полный — сущность рисуется как обычно.", MODE_GHOST, stringArray));
        this.alpha = (SliderSetting)this.register((Setting)new SliderSetting("Прозрачность", "Насколько плотно рисуется силуэт невидимого. 15% — как в ванильной невидимости.").range(5.0f, 100.0f).increment(5.0f).setValue(45.0f).visible(() -> SeeInvisible.alpha$lambda$0(this)));
        stringArray = new String[]{TARGET_PLAYERS, TARGET_MOBS, TARGET_ANIMALS};
        MultiSelectSetting multiSelectSetting = new MultiSelectSetting("Цели", "Каких невидимых сущностей показывать.").value(stringArray);
        stringArray = new String[]{TARGET_PLAYERS};
        this.targets = (MultiSelectSetting)this.register((Setting)multiSelectSetting.selected(stringArray));
        INSTANCE = this;
    }

    public final boolean shouldReveal(@Nullable LivingEntity entity) {
        ClientPlayerEntity player = this.mc.player;
        if (!this.isEnabled() || entity == null || Intrinsics.areEqual((Object)entity, (Object)player)) {
            return false;
        }
        if (player == null || this.mc.world == null || this.mc.isIntegratedServerRunning()) {
            return false;
        }
        if (entity.isSpectator() || entity.isRemoved()) {
            return false;
        }
        if (!Companion.isOnHolyWorld()) {
            return false;
        }
        if (!this.isTargeted(entity)) {
            return false;
        }
        return player.canSee((Entity)entity);
    }

    private final boolean isTargeted(LivingEntity entity) {
        if (entity instanceof PlayerEntity) {
            return this.targets.isSelected(TARGET_PLAYERS);
        }
        if (entity instanceof AnimalEntity) {
            return this.targets.isSelected(TARGET_ANIMALS);
        }
        return entity instanceof MobEntity && this.targets.isSelected(TARGET_MOBS);
    }

    public final boolean isSolid() {
        return this.mode.is(MODE_SOLID);
    }

    public final int ghostTint() {
        int value = MathHelper.clamp((int)Math.round(this.alpha.getFloat() * 2.55f), (int)1, (int)255);
        return value << 24 | 0xFFFFFF;
    }

    private static final Boolean alpha$lambda$0(SeeInvisible this$0) {
        return this$0.mode.is(MODE_GHOST);
    }

    @JvmStatic
    @Nullable
    public static final SeeInvisible getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final boolean isOnHolyWorld() {
        return Companion.isOnHolyWorld();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\rR\u0014\u0010\u0010\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\rR\u0014\u0010\u0011\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\rR\u001d\u0010\u0013\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0012\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/SeeInvisible.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/SeeInvisible;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/SeeInvisible;", "", "isOnHolyWorld", "()Z", "", "MODE_GHOST", "Ljava/lang/String;", "MODE_SOLID", "TARGET_PLAYERS", "TARGET_MOBS", "TARGET_ANIMALS", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/SeeInvisible;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final SeeInvisible getInstance() {
            SeeInvisible module = ModuleManager.Companion.get().get(SeeInvisible.class);
            SeeInvisible seeInvisible = module;
            if (seeInvisible == null) {
                seeInvisible = INSTANCE;
            }
            return seeInvisible;
        }

        @JvmStatic
        public final boolean isOnHolyWorld() {
            return Network.isHolyWorld() || ServerRestrictions.current().contains((Object)Server.HW);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

