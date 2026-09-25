/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.client.util.Window
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.client.gui.screen.Screen
 *  net.minecraft.client.option.Perspective
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.util.Window;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Visuals.emotions.Emotion;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionPlayback;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionRemoteState;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionSyncClient;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionWheelOverlay;
import rtx.kimiko.api.modules.impl.Visuals.emotions.EmotionWheelScreen;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BindSetting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.MultiSelectSetting;
import rtx.kimiko.api.modules.settings.impl.NumberSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.utils.profile.ProfileIdentity;

@Feature(value={"emotions"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 ;2\u00020\u0001:\u0001;B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0005\u0010\u0003J\r\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0010H\u0007b\u0002\b\u0012\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0015\u0010\bJ\u000f\u0010\u0016\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0003J\u0019\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0018\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u0003J\u000f\u0010\u001d\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u0003R\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010%\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020\u001e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010 R\u0014\u0010)\u001a\u00020(8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010,\u001a\u00020+8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u0014\u0010.\u001a\u00020+8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b.\u0010-R\u0014\u0010/\u001a\u00020+8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u0010-R\u0018\u00101\u001a\u0004\u0018\u0001008\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b1\u00102R\u0014\u00104\u001a\u0002038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0016\u00107\u001a\u0002068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b7\u00108R\u0016\u00109\u001a\u0002068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b9\u00108R\u0016\u0010:\u001a\u0002068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b:\u00108\u00ca\u0001\u0010\b<\u0012\f\b=\u0012\b\b\fJ\u0004\b\b(>\u00a8\u0006?"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/Emotions;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "onDisable", "", "isMenuKeyDown", "()Z", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;", "emotion", "playEmotion", "(Lrtx/kimiko/api/modules/impl/Visuals/emotions/Emotion;)V", "", "wheel", "()Ljava/util/List;", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "isBusy", "tickSync", "", "name", "Lnet/minecraft/PlayerEntity;", "findPlayer", "(Ljava/lang/String;)Lnet/minecraft/PlayerEntity;", "applyCamera", "restoreCamera", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "menuSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "menuKey", "Lrtx/kimiko/api/modules/settings/impl/BindSetting;", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "wheelEmotions", "Lrtx/kimiko/api/modules/settings/impl/MultiSelectSetting;", "playbackSeparator", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "speed", "Lrtx/kimiko/api/modules/settings/impl/NumberSetting;", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "looping", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "thirdPerson", "stopOnMove", "Lnet/minecraft/Perspective;", "previousCamera", "Lnet/minecraft/Perspective;", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionSyncClient;", "sync", "Lrtx/kimiko/api/modules/impl/Visuals/emotions/EmotionSyncClient;", "", "nextConnectAt", "J", "nextPushAt", "localStartedAt", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "emotions", "rtx.kimiko:kimiko"})
public final class Emotions
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final SeparatorSetting menuSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Меню"));
    @NotNull
    private final BindSetting menuKey = (BindSetting)this.register((Setting)new BindSetting("Клавиша меню", "Зажмите, чтобы открыть колесо эмоций.").setType(BindSetting.Type.HOLD).setKey(66));
    @NotNull
    private final MultiSelectSetting wheelEmotions;
    @NotNull
    private final SeparatorSetting playbackSeparator;
    @NotNull
    private final NumberSetting speed;
    @NotNull
    private final BooleanSetting looping;
    @NotNull
    private final BooleanSetting thirdPerson;
    @NotNull
    private final BooleanSetting stopOnMove;
    @Nullable
    private Perspective previousCamera;
    @NotNull
    private final EmotionSyncClient sync;
    private long nextConnectAt;
    private long nextPushAt;
    private long localStartedAt;
    @NotNull
    private static final String SYNC_HOST = "31.77.145.146";
    private static final int SYNC_PORT = 32123;
    private static final long CONNECT_RETRY_MS = 5000L;
    private static final long PUSH_MS = 150L;
    @JvmField
    @Nullable
    public static Emotions INSTANCE;

    public Emotions() {
        super("Emotions", "Круговое меню эмоций с анимацией модели игрока.", Category.VISUALS);
        String[] stringArray = Emotion.Companion.displayNames();
        MultiSelectSetting multiSelectSetting = new MultiSelectSetting("Эмоции", "Какие эмоции показывать в круговом меню.").value(Arrays.copyOf(stringArray, stringArray.length));
        stringArray = Emotion.Companion.displayNames();
        this.wheelEmotions = (MultiSelectSetting)this.register((Setting)multiSelectSetting.selected(Arrays.copyOf(stringArray, stringArray.length)).minSelectedCount(2));
        this.playbackSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Проигрывание"));
        this.speed = (NumberSetting)this.register((Setting)new NumberSetting("Скорость", "Множитель скорости анимации.", 1.0, 0.25, 2.5, 0.05));
        this.looping = (BooleanSetting)this.register((Setting)new BooleanSetting("Зациклить", "Проигрывать эмоцию по кругу, пока её не остановят.", true));
        this.thirdPerson = (BooleanSetting)this.register((Setting)new BooleanSetting("Вид от третьего лица", "Переключать камеру, пока проигрывается эмоция.", true));
        this.stopOnMove = (BooleanSetting)this.register((Setting)new BooleanSetting("Прерывать при движении", "Останавливать эмоцию при ходьбе, прыжке или атаке.", true));
        this.sync = new EmotionSyncClient();
        INSTANCE = this;
    }

    @Override
    protected void onDisable() {
        EmotionPlayback.cancel();
        EmotionPlayback.clearRemote();
        this.sync.disconnect();
        EmotionWheelOverlay.cancel();
        this.restoreCamera();
        if (this.mc.currentScreen instanceof EmotionWheelScreen) {
            this.mc.setScreen(null);
        }
    }

    public final boolean isMenuKeyDown() {
        Window window2 = this.mc.getWindow();
        if (window2 == null) {
            return false;
        }
        Window window = window2;
        return this.menuKey.isBound() && this.menuKey.getValue().isDown(window);
    }

    public final void playEmotion(@NotNull Emotion emotion) {
        Intrinsics.checkNotNullParameter((Object)((Object)emotion), (String)"emotion");
        EmotionPlayback.setSpeed(this.speed.getFloat());
        EmotionPlayback.setLooping(this.looping.getValue());
        EmotionPlayback.play(emotion);
        this.localStartedAt = System.currentTimeMillis();
        this.applyCamera();
    }

    @NotNull
    public final List<Emotion> wheel() {
        List selected = new ArrayList();
        for (Emotion emotion : Emotion.values()) {
            if (!this.wheelEmotions.is(emotion.displayName())) continue;
            selected.add(emotion);
        }
        return selected;
    }

    @EventHandler
    public final void onTick(@NotNull TickEvent event) {
        List<Emotion> wheel;
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!event.isPre()) {
            return;
        }
        if (this.mc.player == null || this.mc.world == null) {
            EmotionPlayback.cancel();
            EmotionPlayback.clearRemote();
            this.sync.disconnect();
            this.restoreCamera();
            return;
        }
        EmotionPlayback.setSpeed(this.speed.getFloat());
        EmotionPlayback.setLooping(this.looping.getValue());
        EmotionPlayback.update();
        this.tickSync();
        if (this.mc.currentScreen == null && this.isMenuKeyDown() && !((Collection)(wheel = this.wheel())).isEmpty()) {
            this.mc.setScreen((Screen)new EmotionWheelScreen(this, wheel));
        }
        if (EmotionPlayback.isPlaying()) {
            if (this.stopOnMove.getValue() && this.isBusy()) {
                EmotionPlayback.stop();
            } else {
                this.applyCamera();
            }
        }
        if (EmotionPlayback.active() == null) {
            this.restoreCamera();
        }
    }

    private final boolean isBusy() {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return false;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        if (!(player.sidewaysSpeed == 0.0f) || !(player.forwardSpeed == 0.0f)) {
            return true;
        }
        if (this.mc.options.jumpKey.isPressed()) {
            return true;
        }
        return player.handSwinging || player.isUsingItem() || player.isGliding() || player.isSwimming();
    }

    private final void tickSync() {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        String name = player.getGameProfile().name();
        Intrinsics.checkNotNull((Object)name);
        if (StringsKt.isBlank((CharSequence)name)) {
            return;
        }
        int uid = ProfileIdentity.uid();
        String identity = uid > 0 ? "uid:" + uid : "name:" + name;
        String string = level.getRegistryKey().getValue().toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        String world = string;
        this.sync.setLocalState(identity, name, world, EmotionPlayback.active(), this.localStartedAt, this.speed.getFloat(), this.looping.getValue());
        long now = System.currentTimeMillis();
        if (!this.sync.isConnected() && !this.sync.isConnecting() && now >= this.nextConnectAt) {
            this.sync.connect(SYNC_HOST, 32123);
            this.nextConnectAt = now + 5000L;
        }
        if (now >= this.nextPushAt) {
            this.sync.push();
            this.nextPushAt = now + 150L;
        }
        EmotionPlayback.clearRemote();
        for (EmotionRemoteState state : this.sync.snapshot().values()) {
            PlayerEntity foundPlayer;
            if (!Intrinsics.areEqual((Object)world, (Object)state.world()) || StringsKt.equals((String)state.minecraftUsername(), (String)name, (boolean)true) || (foundPlayer = this.findPlayer(state.minecraftUsername())) == null) continue;
            EmotionPlayback.setRemote(foundPlayer.getUuid(), state.emotion(), state.startedAt(), state.speed(), state.looping());
        }
    }

    private final PlayerEntity findPlayer(String name) {
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return null;
        }
        ClientWorld level = clientWorld3;
        for (Object e : level.getPlayers()) {
            Intrinsics.checkNotNullExpressionValue(e, (String)"next(...)");
            AbstractClientPlayerEntity player = (AbstractClientPlayerEntity)e;
            if (!StringsKt.equals((String)player.getGameProfile().name(), (String)name, (boolean)true)) continue;
            return (PlayerEntity)player;
        }
        return null;
    }

    private final void applyCamera() {
        if (!this.thirdPerson.getValue() || this.previousCamera != null) {
            return;
        }
        this.previousCamera = this.mc.options.getPerspective();
        if (this.previousCamera == Perspective.FIRST_PERSON) {
            this.mc.options.setPerspective(Perspective.THIRD_PERSON_BACK);
        }
    }

    private final void restoreCamera() {
        Perspective perspective2 = this.previousCamera;
        if (perspective2 == null) {
            return;
        }
        Perspective prev = perspective2;
        this.mc.options.setPerspective(prev);
        this.previousCamera = null;
    }

    @JvmStatic
    @Nullable
    public static final Emotions getInstance() {
        return Companion.getInstance();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u000e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0010R\u001d\u0010\u0013\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u0012\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/Emotions.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/Emotions;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/Emotions;", "", "SYNC_HOST", "Ljava/lang/String;", "", "SYNC_PORT", "I", "", "CONNECT_RETRY_MS", "J", "PUSH_MS", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/Emotions;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final Emotions getInstance() {
            Emotions module = ModuleManager.Companion.get().get(Emotions.class);
            Emotions emotions = module;
            if (emotions == null) {
                emotions = INSTANCE;
            }
            return emotions;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

