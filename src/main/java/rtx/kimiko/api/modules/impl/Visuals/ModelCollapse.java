/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.mob.MobEntity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.passive.AnimalEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.player.AttackEntityEvent;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Visuals.modelcollapse.ModelCollapseSimulation;
import rtx.kimiko.api.modules.impl.Visuals.modelcollapse.ModelCollapseSnapshots;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.MultiSelectSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.entity.death.EntityDeathWatcher;
import rtx.kimiko.utils.storage.friend.FriendUtils;

@Feature(value={"modelcollapse"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00b0\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 J2\u00020\u00012\u00020\u0002:\u0002KJB\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u000b\u0010\u0007J\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0011H\u0007b\u0002\b\u0013\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0016H\u0007b\u0002\b\u0013\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u001b\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0019H\u0007b\u0002\b\u0013\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\u000eH\u0014\u00a2\u0006\u0004\b\u001c\u0010\u0004J\u000f\u0010\u001d\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u001d\u0010\u0004J\u000f\u0010\u001f\u001a\u00020\u001eH\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\u0017\u0010!\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b!\u0010\u0010J\u0017\u0010#\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b#\u0010$R\u0014\u0010&\u001a\u00020%8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010'R$\u0010*\u001a\u0012\u0012\u0004\u0012\u00020\f0(j\b\u0012\u0004\u0012\u00020\f`)8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b*\u0010+R \u0010/\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020.0,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u00100R \u00102\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u0002010,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u00100R \u00103\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u0002010,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b3\u00100R\u0014\u00105\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00106R\u0014\u00108\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010;\u001a\u00020:8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b;\u0010<R\u0014\u0010=\u001a\u00020:8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u0010<R\u0014\u0010>\u001a\u00020:8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b>\u0010<R\u0014\u0010?\u001a\u00020:8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u0010<R\u0014\u0010@\u001a\u00020:8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b@\u0010<R\u0014\u0010A\u001a\u00020:8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bA\u0010<R\u0014\u0010C\u001a\u00020B8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bC\u0010DR\u0014\u0010E\u001a\u00020B8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010DR\u0014\u0010F\u001a\u0002048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bF\u00106R\u0014\u0010H\u001a\u00020G8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u0010I\u00ca\u0001\u0010\bL\u0012\f\bM\u0012\b\b\fJ\u0004\b\b(N\u00a8\u0006O"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ModelCollapse;", "Lrtx/kimiko/api/modules/Module;", "Lrtx/kimiko/utils/entity/death/EntityDeathWatcher$Listener;", "<init>", "()V", "", "deathWatchActive", "()Z", "", "deathWatchDistance", "()D", "deathWatchAggressive", "Lnet/minecraft/LivingEntity;", "victim", "", "onEntityDeath", "(Lnet/minecraft/LivingEntity;)V", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "Lrtx/kimiko/api/events/impl/player/AttackEntityEvent;", "onAttack", "(Lrtx/kimiko/api/events/impl/player/AttackEntityEvent;)V", "onDisable", "resetState", "Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$Settings;", "settings", "()Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation$Settings;", "handleDeath", "entity", "matchesTarget", "(Lnet/minecraft/LivingEntity;)Z", "Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation;", "simulation", "Lrtx/kimiko/api/modules/impl/Visuals/modelcollapse/ModelCollapseSimulation;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "pending", "Ljava/util/ArrayList;", "Ljava/util/HashMap;", "", "Lrtx/kimiko/api/modules/impl/Visuals/ModelCollapse$HiddenEntry;", "hidden", "Ljava/util/HashMap;", "", "recentlyCollapsed", "provisional", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "mainSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "breakMode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "waveTime", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "cubeSize", "impulse", "bounce", "lifeTime", "maxDistance", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "hideModel", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "aggressiveDetect", "targetsSeparator", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "targets", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "Companion", "HiddenEntry", "Lrtx/kimiko/api/liteapi/Feature;", "value", "modelcollapse", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nModelCollapse.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ModelCollapse.kt\nrtx/kimiko/api/modules/impl/Visuals/ModelCollapse\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,311:1\n777#2:312\n873#2,2:313\n1739#2:315\n1814#2,3:316\n*S KotlinDebug\n*F\n+ 1 ModelCollapse.kt\nrtx/kimiko/api/modules/impl/Visuals/ModelCollapse\n*L\n135#1:312\n135#1:313,2\n135#1:315\n135#1:316,3\n*E\n"})
public final class ModelCollapse
extends Module
implements EntityDeathWatcher.Listener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ModelCollapseSimulation simulation = new ModelCollapseSimulation();
    @NotNull
    private final ArrayList<LivingEntity> pending = new ArrayList();
    @NotNull
    private final HashMap<Integer, HiddenEntry> hidden = new HashMap();
    @NotNull
    private final HashMap<Integer, Long> recentlyCollapsed = new HashMap();
    @NotNull
    private final HashMap<Integer, Long> provisional = new HashMap();
    @NotNull
    private final SeparatorSetting mainSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Основное"));
    @NotNull
    private final ModeSetting breakMode;
    @NotNull
    private final SliderSetting waveTime;
    @NotNull
    private final SliderSetting cubeSize;
    @NotNull
    private final SliderSetting impulse;
    @NotNull
    private final SliderSetting bounce;
    @NotNull
    private final SliderSetting lifeTime;
    @NotNull
    private final SliderSetting maxDistance;
    @NotNull
    private final BooleanSetting hideModel;
    @NotNull
    private final BooleanSetting aggressiveDetect;
    @NotNull
    private final SeparatorSetting targetsSeparator;
    @NotNull
    private final MultiSelectSetting targets;
    @NotNull
    private static final String MODE_BOTTOM_UP = "Снизу вверх";
    @NotNull
    private static final String MODE_TOP_DOWN = "Сверху вниз";
    @NotNull
    private static final String TARGET_PLAYERS = "Игроки";
    @NotNull
    private static final String TARGET_FRIENDS = "Друзья";
    @NotNull
    private static final String TARGET_MOBS = "Мобы";
    @NotNull
    private static final String TARGET_ANIMALS = "Животные";
    private static final int MAX_PENDING = 8;
    private static final long HIDE_MS = 5000L;
    private static final long COLLAPSE_MEMORY_MS = 5000L;
    private static final long PROVISIONAL_MS = 700L;
    private static final long VANISH_SNAPSHOT_MS = 700L;
    private static final double UNHIDE_MOVE_SQ = 6.25;
    @JvmField
    @Nullable
    public static ModelCollapse INSTANCE;

    public ModelCollapse() {
        super("Model Collapse", "Разрывает модель убитой сущности на мелкие кубики, которые рассыпаются с физикой и коллизией.", Category.VISUALS);
        String[] stringArray = new String[]{MODE_BOTTOM_UP, MODE_TOP_DOWN};
        this.breakMode = (ModeSetting)this.register((Setting)new ModeSetting("Раскол", "Направление волны, которой модель разбивается на кубики.", MODE_BOTTOM_UP, stringArray));
        this.waveTime = (SliderSetting)this.register((Setting)new SliderSetting("Волна", "За сколько миллисекунд волна раскола проходит по модели.").range(100.0f, 1000.0f).increment(25.0f).setValue(350.0f));
        this.cubeSize = (SliderSetting)this.register((Setting)new SliderSetting("Размер кубиков", "Размер одного осколка модели. Меньше = детальнее и дороже по FPS.").range(0.03f, 0.12f).increment(0.005f).setValue(0.055f));
        this.impulse = (SliderSetting)this.register((Setting)new SliderSetting("Разлёт", "Сила, с которой осколки разлетаются от модели в момент смерти.").range(0.0f, 3.0f).increment(0.05f).setValue(1.0f));
        this.bounce = (SliderSetting)this.register((Setting)new SliderSetting("Отскок", "Насколько сильно осколки отскакивают от блоков, %.").range(0.0f, 80.0f).increment(5.0f).setValue(35.0f));
        this.lifeTime = (SliderSetting)this.register((Setting)new SliderSetting("Время жизни", "Сколько секунд россыпь лежит на земле перед исчезновением.").range(1.0f, 15.0f).increment(0.5f).setValue(6.0f));
        this.maxDistance = (SliderSetting)this.register((Setting)new SliderSetting("Дальность", "Максимальная дистанция до смерти, на которой запускается эффект.").range(16.0f, 128.0f).increment(8.0f).setValue(64.0f));
        this.hideModel = (BooleanSetting)this.register((Setting)new BooleanSetting("Скрывать модель", "Прятать умирающую модель, оставляя на её месте только осколки.", true));
        this.aggressiveDetect = (BooleanSetting)this.register((Setting)new BooleanSetting("Агрессивный детект", "Засчитывать килл, если цель исчезла сразу после твоего удара, даже без подтверждений (звук смерти, дроп, опыт, респавн). Для серверов без анимации смерти.", true));
        this.targetsSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Цели"));
        stringArray = new String[]{TARGET_PLAYERS, TARGET_FRIENDS, TARGET_MOBS, TARGET_ANIMALS};
        MultiSelectSetting multiSelectSetting = new MultiSelectSetting("Цели", "Какие сущности рассыпаются на кубики при смерти.").value(stringArray);
        stringArray = new String[]{TARGET_PLAYERS, TARGET_FRIENDS, TARGET_MOBS, TARGET_ANIMALS};
        this.targets = (MultiSelectSetting)this.register((Setting)multiSelectSetting.selected(stringArray));
        INSTANCE = this;
        EntityDeathWatcher.register(this);
    }

    @Override
    public boolean deathWatchActive() {
        return this.isEnabled();
    }

    @Override
    public double deathWatchDistance() {
        return this.maxDistance.getFloat();
    }

    @Override
    public boolean deathWatchAggressive() {
        return this.aggressiveDetect.getValue();
    }

    @Override
    public void onEntityDeath(@NotNull LivingEntity victim) {
        Intrinsics.checkNotNullParameter((Object)victim, (String)"victim");
        this.handleDeath(victim);
    }

    @EventHandler
    public final void onTick(@NotNull TickEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!event.isPost()) {
            return;
        }
        if (this.mc.player == null || this.mc.world == null) {
            this.resetState();
        }
    }

    /*
     * WARNING - void declaration
     */
    @EventHandler
    public final void onWorldRender(@NotNull WorldRenderEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (event.isPortalPass()) {
            return;
        }
        if (this.mc.player == null || this.mc.world == null) {
            this.resetState();
            return;
        }
        long now = System.currentTimeMillis();
        float partialTick = MathHelper.clamp((float)event.getPartialTicks(), (float)0.0f, (float)1.0f);
        if (!this.pending.isEmpty()) {
            Iterator<LivingEntity> iterator = this.pending.iterator();
            Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
            Iterator<LivingEntity> iterator2 = iterator;
            while (iterator2.hasNext()) {
                LivingEntity entity = (LivingEntity) (iterator2.next());
                this.provisional.remove(entity.getId());
                if (this.simulation.release(entity.getId()) || this.simulation.spawn(entity, partialTick, this.settings())) continue;
                this.hidden.remove(entity.getId());
            }
            this.pending.clear();
        }
        if (!this.provisional.isEmpty()) {
            List<Integer> stale = new ArrayList<>();
            for (Map.Entry<Integer, Long> entry : this.provisional.entrySet()) {
                if (now - entry.getValue() > 700L) {
                    stale.add(entry.getKey());
                }
            }
            for (int id : stale) {
                this.provisional.remove(id);
                ClientWorld clientWorld3 = this.mc.world;
                Intrinsics.checkNotNull((Object)clientWorld3);
                if (clientWorld3.getEntityById(id) == null) {
                    this.simulation.release(id);
                    ((Map)this.recentlyCollapsed).put(id, now);
                    continue;
                }
                this.simulation.dropFrozen(id);
                this.hidden.remove(id);
            }
        }
        double distance = this.maxDistance.getFloat();
        ModelCollapseSnapshots.INSTANCE.tick(partialTick, distance, this::matchesTarget);
        ModelCollapseSnapshots.INSTANCE.collectVanished(700L, (id, snapshot) -> ModelCollapse.onWorldRender$lambda$3(this, distance, now, id, snapshot));
        this.hidden.values().removeIf(entry -> entry.getUntil() < now);
        this.recentlyCollapsed.values().removeIf(at -> now - at > 5000L);
        if (!this.simulation.isIdle()) {
            this.simulation.renderAndStep(event, this.settings());
        }
    }

    @EventHandler
    public final void onAttack(@NotNull AttackEntityEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!this.isEnabled()) {
            return;
        }
        Entity target = event.getTarget();
        if (target instanceof LivingEntity && target != this.mc.player) {
            ModelCollapseSnapshots.noteAttack((LivingEntity)target);
        }
    }

    @Override
    protected void onDisable() {
        this.resetState();
    }

    private final void resetState() {
        this.pending.clear();
        this.hidden.clear();
        this.recentlyCollapsed.clear();
        this.provisional.clear();
        ModelCollapseSnapshots.clear();
        this.simulation.clear();
    }

    private final ModelCollapseSimulation.Settings settings() {
        return new ModelCollapseSimulation.Settings(this.cubeSize.getFloat(), this.impulse.getFloat(), Math.round(this.lifeTime.getFloat() * 1000.0f), this.bounce.getFloat() / 100.0f, Math.round(this.waveTime.getFloat()), this.breakMode.is(MODE_TOP_DOWN));
    }

    private final void handleDeath(LivingEntity victim) {
        ClientPlayerEntity player = this.mc.player;
        ClientWorld level = this.mc.world;
        if (player == null || level == null || Intrinsics.areEqual((Object)victim, (Object)player) || !Intrinsics.areEqual((Object)victim.getEntityWorld(), (Object)level)) {
            return;
        }
        long now = System.currentTimeMillis();
        if (this.recentlyCollapsed.containsKey(victim.getId())) {
            return;
        }
        double distance = this.maxDistance.getFloat();
        if (victim.squaredDistanceTo((Entity)player) > distance * distance) {
            return;
        }
        if (!this.matchesTarget(victim) || this.pending.size() >= 8) {
            return;
        }
        ((Map)this.recentlyCollapsed).put(victim.getId(), now);
        this.pending.add(victim);
        if (this.hideModel.getValue()) {
            ((Map)this.hidden).put(victim.getId(), new HiddenEntry(now + 5000L, victim.getX(), victim.getY(), victim.getZ()));
        }
    }

    private final boolean matchesTarget(LivingEntity entity) {
        if (entity instanceof PlayerEntity) {
            if (FriendUtils.isFriend((Entity)entity)) {
                return this.targets.is(TARGET_FRIENDS);
            }
            return this.targets.is(TARGET_PLAYERS);
        }
        if (entity instanceof AnimalEntity) {
            return this.targets.is(TARGET_ANIMALS);
        }
        if (entity instanceof MobEntity) {
            return this.targets.is(TARGET_MOBS);
        }
        return false;
    }

    private static final boolean onWorldRender$lambda$2(ModelCollapse this$0, LivingEntity entity) {
        Intrinsics.checkNotNullParameter((Object)entity, (String)"entity");
        return this$0.matchesTarget(entity);
    }

    private static final boolean onWorldRender$lambda$3(ModelCollapse this$0, double $distance, long $now, int id, ModelCollapseSnapshots.Snapshot snapshot) {
        boolean bl;
        Intrinsics.checkNotNullParameter((Object)snapshot, (String)"snapshot");
        if (this$0.recentlyCollapsed.containsKey(id) || this$0.provisional.containsKey(id)) {
            bl = false;
        } else {
            ClientPlayerEntity clientPlayerEntity2 = this$0.mc.player;
            Intrinsics.checkNotNull((Object)clientPlayerEntity2);
            if (clientPlayerEntity2.squaredDistanceTo(snapshot.getX(), snapshot.getY(), snapshot.getZ()) > $distance * $distance) {
                bl = false;
            } else if (!this$0.simulation.spawnFrozen(id, snapshot, this$0.settings())) {
                bl = false;
            } else {
                ((Map)this$0.provisional).put(id, $now);
                if (this$0.hideModel.getValue()) {
                    ((Map)this$0.hidden).put(id, new HiddenEntry($now + 5000L, snapshot.getX(), snapshot.getY(), snapshot.getZ()));
                }
                bl = true;
            }
        }
        return bl;
    }

    private static final boolean onWorldRender$lambda$4(long $now, HiddenEntry entry) {
        Intrinsics.checkNotNullParameter((Object)entry, (String)"entry");
        return entry.getUntil() < $now;
    }

    private static final boolean onWorldRender$lambda$5(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    private static final boolean onWorldRender$lambda$6(long $now, Long at) {
        Intrinsics.checkNotNullParameter((Object)at, (String)"at");
        return $now - at > 5000L;
    }

    private static final boolean onWorldRender$lambda$7(Function1 $tmp0, Object p0) {
        return (Boolean)$tmp0.invoke(p0);
    }

    @JvmStatic
    @Nullable
    public static final ModelCollapse getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    @Nullable
    public static final ModelCollapse getInstanceIfReady() {
        return Companion.getInstanceIfReady();
    }

    @JvmStatic
    public static final void notifyEntityDied(@Nullable LivingEntity victim) {
        Companion.notifyEntityDied(victim);
    }

    @JvmStatic
    public static final boolean shouldHideEntity(@NotNull LivingEntity entity) {
        return Companion.shouldHideEntity(entity);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\u0007J\u001d\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0014R\u0014\u0010\u0017\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0014R\u0014\u0010\u0018\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0014R\u0014\u0010\u0019\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0014R\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001e\u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010\u001fR\u0014\u0010!\u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b!\u0010\u001fR\u0014\u0010\"\u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010\u001fR\u0014\u0010$\u001a\u00020#8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u001d\u0010'\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b&\u00a2\u0006\u0006\n\u0004\b'\u0010(\u00a8\u0006)"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ModelCollapse.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/ModelCollapse;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/ModelCollapse;", "getInstanceIfReady", "Lnet/minecraft/LivingEntity;", "victim", "", "notifyEntityDied", "(Lnet/minecraft/LivingEntity;)V", "entity", "", "shouldHideEntity", "(Lnet/minecraft/LivingEntity;)Z", "", "MODE_BOTTOM_UP", "Ljava/lang/String;", "MODE_TOP_DOWN", "TARGET_PLAYERS", "TARGET_FRIENDS", "TARGET_MOBS", "TARGET_ANIMALS", "", "MAX_PENDING", "I", "", "HIDE_MS", "J", "COLLAPSE_MEMORY_MS", "PROVISIONAL_MS", "VANISH_SNAPSHOT_MS", "", "UNHIDE_MOVE_SQ", "D", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/ModelCollapse;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final ModelCollapse getInstance() {
            ModelCollapse module = ModuleManager.Companion.get().get(ModelCollapse.class);
            ModelCollapse modelCollapse = module;
            if (modelCollapse == null) {
                modelCollapse = INSTANCE;
            }
            return modelCollapse;
        }

        @JvmStatic
        @Nullable
        public final ModelCollapse getInstanceIfReady() {
            ModelCollapse modelCollapse;
            try {
                modelCollapse = this.getInstance();
            }
            catch (RuntimeException ignored) {
                modelCollapse = null;
            }
            return modelCollapse;
        }

        @JvmStatic
        public final void notifyEntityDied(@Nullable LivingEntity victim) {
            ModelCollapse module = this.getInstanceIfReady();
            if (module == null || !module.isEnabled() || victim == null) {
                return;
            }
            module.handleDeath(victim);
        }

        @JvmStatic
        public final boolean shouldHideEntity(@NotNull LivingEntity entity) {
            Intrinsics.checkNotNullParameter((Object)entity, (String)"entity");
            ModelCollapse module = this.getInstanceIfReady();
            if (module == null || !module.isEnabled()) {
                return false;
            }
            HiddenEntry hiddenEntry = (HiddenEntry)module.hidden.get(entity.getId());
            if (hiddenEntry == null) {
                return false;
            }
            HiddenEntry entry = hiddenEntry;
            if (System.currentTimeMillis() >= entry.getUntil()) {
                return false;
            }
            if (!entity.isRemoved() && entity.squaredDistanceTo(entry.getX(), entry.getY(), entry.getZ()) > 6.25) {
                module.hidden.remove(entity.getId());
                return false;
            }
            return true;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000e\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\b\u0010\tR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\r\u001a\u0004\b\u0010\u0010\u000fR\u0017\u0010\u0007\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\r\u001a\u0004\b\u0011\u0010\u000f\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ModelCollapse$HiddenEntry;", "", "", "until", "", "x", "y", "z", "<init>", "(JDDD)V", "J", "getUntil", "()J", "D", "getX", "()D", "getY", "getZ", "rtx.kimiko:kimiko"})
    private static final class HiddenEntry {
        private final long until;
        private final double x;
        private final double y;
        private final double z;

        public HiddenEntry(long until, double x, double y, double z) {
            this.until = until;
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public final long getUntil() {
            return this.until;
        }

        public final double getX() {
            return this.x;
        }

        public final double getY() {
            return this.y;
        }

        public final double getZ() {
            return this.z;
        }
    }
}

