/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.Formatting
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.util.Util
 *  net.minecraft.text.Text
 *  net.minecraft.text.MutableText
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.modules.impl.Utils;

import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Formatting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Util;
import net.minecraft.text.Text;
import net.minecraft.text.MutableText;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.player.AttackEntityEvent;
import rtx.kimiko.api.events.impl.player.TotemPopEvent;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.SliderSetting;
import rtx.kimiko.utils.chat.ChatMessage;

@Feature(value={"taltracker"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000  2\u00020\u0001:\u0001 B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u001b\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001b\u0010\f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u000bH\u0007b\u0002\b\b\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0015R\u0016\u0010\u001b\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001e\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001f\u00ca\u0001\u0010\b!\u0012\f\b\"\u0012\b\b\fJ\u0004\b\b(#\u00a8\u0006$"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/TalTracker;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "onEnable", "Lrtx/kimiko/api/events/impl/player/AttackEntityEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onAttack", "(Lrtx/kimiko/api/events/impl/player/AttackEntityEvent;)V", "Lrtx/kimiko/api/events/impl/player/TotemPopEvent;", "onTotemPop", "(Lrtx/kimiko/api/events/impl/player/TotemPopEvent;)V", "Lnet/minecraft/LivingEntity;", "entity", "", "isCurrentTarget", "(Lnet/minecraft/LivingEntity;)Z", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "onlyTarget", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "memory", "Lrtx/kimiko/api/modules/settings/impl/SliderSetting;", "showSelf", "", "lastTargetId", "I", "", "lastTargetTime", "J", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "taltracker", "rtx.kimiko:kimiko"})
public final class TalTracker
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final BooleanSetting onlyTarget = new BooleanSetting("Только по цели", "Писать только когда тотем лопнул у противника, которого ты бьёшь.", true);
    @NotNull
    private final SliderSetting memory = new SliderSetting("Память цели (сек)", "Сколько секунд цель считается твоей после удара.").range(1, 10).increment(1).setValue(4.0f).visible(() -> TalTracker.memory$lambda$0(this));
    @NotNull
    private final BooleanSetting showSelf = new BooleanSetting("Свои тотемы", "Писать также когда твой собственный тотем лопается.", false);
    private int lastTargetId = -1;
    private long lastTargetTime;
    private static final long DUPLICATE_POP_WINDOW_MS = 150L;
    @NotNull
    private static final HashMap<Integer, Long> LAST_POP_MESSAGES = new HashMap();

    public TalTracker() {
        super("Tal Tracker", "Сообщает в чат когда у противника лопается тотем и был ли он зачарован.", Category.UTILS);
        Setting[] settingArray = new Setting[]{this.onlyTarget, this.memory, this.showSelf};
        this.register(settingArray);
    }

    @Override
    protected void onEnable() {
        LAST_POP_MESSAGES.clear();
    }

    @EventHandler
    public final void onAttack(@NotNull AttackEntityEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (event.isSynthetic()) {
            return;
        }
        Entity target = event.getTarget();
        if (target instanceof LivingEntity) {
            this.lastTargetId = ((LivingEntity)target).getId();
            this.lastTargetTime = System.currentTimeMillis();
        }
    }

    @EventHandler
    public final void onTotemPop(@NotNull TotemPopEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        LivingEntity livingEntity2 = event.getEntity();
        if (livingEntity2 == null) {
            return;
        }
        LivingEntity entity = livingEntity2;
        boolean self = Intrinsics.areEqual((Object)entity, (Object)player);
        if (self && !this.showSelf.getValue()) {
            return;
        }
        if (!self && this.onlyTarget.getValue() && !this.isCurrentTarget(entity)) {
            return;
        }
        long now = Util.getMeasuringTimeMs();
        Long previous = LAST_POP_MESSAGES.put(entity.getId(), now);
        if (previous != null && now - previous < 150L) {
            return;
        }
        String string = entity.getName().getString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getString(...)");
        String name = string;
        boolean enchanted = event.isEnchanted();
        MutableText mutableText2 = Text.literal((String)"» ").formatted(Formatting.DARK_GRAY).append((Text)Text.literal((String)name).formatted(Formatting.WHITE)).append((Text)Text.literal((String)I18n.tr(" потерял тотем. ")).formatted(Formatting.GRAY)).append((Text)Text.literal((String)I18n.tr("Талик: ")).formatted(Formatting.LIGHT_PURPLE)).append((Text)Text.literal((String)(enchanted ? "\u2713" : "\u2717")).formatted(enchanted ? Formatting.GREEN : Formatting.RED));
        Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"append(...)");
        MutableText body = mutableText2;
        ChatMessage.brandmessage((Text)body);
    }

    private final boolean isCurrentTarget(LivingEntity entity) {
        if (entity.getId() != this.lastTargetId) {
            return false;
        }
        long window = (long)this.memory.getInt() * 1000L;
        return System.currentTimeMillis() - this.lastTargetTime <= window;
    }

    private static final Boolean memory$lambda$0(TalTracker this$0) {
        return this$0.onlyTarget.getValue();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R0\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00040\u0007j\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0004`\t8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000b\u00a8\u0006\f"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/TalTracker.Companion;", "", "<init>", "()V", "", "DUPLICATE_POP_WINDOW_MS", "J", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "LAST_POP_MESSAGES", "Ljava/util/HashMap;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

