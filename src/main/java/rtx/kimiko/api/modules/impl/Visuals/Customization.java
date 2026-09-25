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
 *  net.minecraft.client.render.command.OrderedRenderCommandQueue
 *  net.minecraft.entity.EquipmentSlot
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.option.Perspective
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  rtx.kimiko.api.modules.impl.Visuals.customization.FeatheredWingsRenderer
 */
package rtx.kimiko.api.modules.impl.Visuals;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Visuals.cosmetics.CosmeticSiteState;
import rtx.kimiko.api.modules.impl.Visuals.customization.CrownRenderer;
import rtx.kimiko.api.modules.impl.Visuals.customization.CustomizationSyncClient;
import rtx.kimiko.api.modules.impl.Visuals.customization.FeatheredWingsRenderer;
import rtx.kimiko.api.modules.impl.Visuals.customization.GoldenHaloRenderer;
import rtx.kimiko.api.modules.impl.Visuals.customization.KanekiKaguneRenderer;
import rtx.kimiko.api.modules.impl.Visuals.customization.RoyalWingsRenderer;
import rtx.kimiko.api.modules.impl.Visuals.customization.SeraphEyeWingsRenderer;
import rtx.kimiko.api.modules.impl.Visuals.customization.ShoulderGoatRenderer;
import sigil.protect.Level;
import sigil.protect.Protect;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000b\u0018\u0000 D2\u00020\u0001:\u0001DB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\u0006J\u0017\u0010\n\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0004\b\n\u0010\u000bJ/\u0010\u0013\u001a\u00020\u00122\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0015\u0010\u0006J\u0017\u0010\u0016\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0004\b\u0016\u0010\u000bJ\u0017\u0010\u0017\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0004\b\u0017\u0010\u000bJ\u0017\u0010\u0018\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0004\b\u0018\u0010\u000bJ/\u0010\u0019\u001a\u00020\u00122\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0019\u0010\u0014J\u0017\u0010\u001a\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0004\b\u001a\u0010\u000bJ\u0017\u0010\u001b\u001a\u00020\u00102\b\u0010\t\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0019\u0010\u001d\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002\u00a2\u0006\u0004\b\u001d\u0010\u000bJ/\u0010\u001e\u001a\u00020\u00122\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010\u00a2\u0006\u0004\b\u001e\u0010\u0014JG\u0010#\u001a\u00020\u00122\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020 \u00a2\u0006\u0004\b#\u0010$J\u001f\u0010)\u001a\u00020\u0012H\u0015b\u000e\b%\u0012\n\b&\u0012\u0006\b\n0'8(\u00a2\u0006\u0004\b)\u0010\u0003J\u001f\u0010*\u001a\u00020\u0012H\u0015b\u000e\b%\u0012\n\b&\u0012\u0006\b\n0'8(\u00a2\u0006\u0004\b*\u0010\u0003J+\u0010/\u001a\u00020\u00122\u0006\u0010,\u001a\u00020+H\u0007b\u0002\b-b\u000e\b%\u0012\n\b&\u0012\u0006\b\n0'8.\u00a2\u0006\u0004\b/\u00100J\u0017\u00102\u001a\u0002012\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b2\u00103J'\u00105\u001a\u00020 2\u0006\u0010\t\u001a\u00020\bH\u0003b\u000e\b%\u0012\n\b&\u0012\u0006\b\n0'84\u00a2\u0006\u0004\b5\u00106R\u0014\u00108\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00109R\u0016\u0010;\u001a\u00020:8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b;\u0010<R\u0016\u0010=\u001a\u00020:8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b=\u0010<R\u0016\u0010>\u001a\u0002018\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0016\u0010@\u001a\u0002018\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b@\u0010?R\u0016\u0010A\u001a\u0002018\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bA\u0010?R\u0016\u0010B\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bB\u0010C\u00a8\u0006E"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/Customization;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "crownEnabled", "()Z", "hatEnabled", "Lnet/minecraft/AbstractClientPlayerEntity;", "player", "goatEnabledFor", "(Lnet/minecraft/AbstractClientPlayerEntity;)Z", "Lnet/minecraft/MatrixStack;", "stack", "Lnet/minecraft/OrderedRenderCommandQueue;", "collector", "", "packedLight", "", "submitGoat", "(Lnet/minecraft/AbstractClientPlayerEntity;Lnet/minecraft/MatrixStack;Lnet/minecraft/OrderedRenderCommandQueue;I)V", "wingsEnabled", "crownEnabledFor", "hatEnabledFor", "haloVisibleFor", "submitHalo", "wingsEnabledFor", "flagsFor", "(Lnet/minecraft/AbstractClientPlayerEntity;)I", "canRender", "submitCrown", "fallFlying", "", "walkSpeed", "attackTime", "submitWings", "(Lnet/minecraft/AbstractClientPlayerEntity;Lnet/minecraft/MatrixStack;Lnet/minecraft/OrderedRenderCommandQueue;IZFF)V", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "onEnable", "onDisable", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "STD", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "", "bodyModelFor", "(Lnet/minecraft/AbstractClientPlayerEntity;)Ljava/lang/String;", "MAX", "headYOffset", "(Lnet/minecraft/AbstractClientPlayerEntity;)F", "Lrtx/kimiko/api/modules/impl/Visuals/customization/CustomizationSyncClient;", "syncClient", "Lrtx/kimiko/api/modules/impl/Visuals/customization/CustomizationSyncClient;", "", "nextConnectAttemptAt", "J", "nextPushAt", "publishedUsername", "Ljava/lang/String;", "publishedHeadAccessory", "publishedBodyModel", "publishedWings", "Z", "Companion", "rtx.kimiko:kimiko"})
public final class Customization
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final CustomizationSyncClient syncClient = new CustomizationSyncClient();
    private long nextConnectAttemptAt;
    private long nextPushAt;
    @NotNull
    private String publishedUsername = "";
    @NotNull
    private String publishedHeadAccessory = "";
    @NotNull
    private String publishedBodyModel = "";
    private boolean publishedWings;
    public static final int FLAG_CROWN = 1;
    public static final int FLAG_HAT = 2;
    public static final int FLAG_HALO = 4;
    public static final int FLAG_WINGS = 8;
    public static final int FLAG_GOAT = 16;
    @NotNull
    private static final String SYNC_KAGUNE = "kagune";
    @NotNull
    private static final String SYNC_SERAPH = "seraph";
    @NotNull
    private static final String SYNC_FEATHERED = "feathered";
    @NotNull
    private static final String SYNC_HOST = "31.77.145.146";
    private static final int SYNC_PORT = 32117;
    private static final long CONNECT_RETRY_MS = 5000L;
    private static final long HEARTBEAT_MS = 10000L;
    @JvmField
    @Nullable
    public static Customization INSTANCE;

    public Customization() {
        super("Customization", "Кастомизация скина: головной убор, крылья и питомец.", Category.VISUALS);
        INSTANCE = this;
    }

    public final boolean crownEnabled() {
        return this.isEnabled() && Intrinsics.areEqual((Object)"crown", (Object)Customization.Companion.site().headAccessory());
    }

    public final boolean hatEnabled() {
        return this.isEnabled() && Intrinsics.areEqual((Object)"hat", (Object)Customization.Companion.site().headAccessory());
    }

    public final boolean goatEnabledFor(@Nullable AbstractClientPlayerEntity player) {
        return this.canRender(player) && Intrinsics.areEqual((Object)player, (Object)this.mc.player) && Customization.Companion.site().goatOn();
    }

    public final void submitGoat(@Nullable AbstractClientPlayerEntity player, @NotNull MatrixStack stack, @NotNull OrderedRenderCommandQueue collector, int packedLight) {
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)collector, (String)"collector");
        if (player == null) {
            return;
        }
        ShoulderGoatRenderer.render(player, stack, collector, packedLight);
    }

    public final boolean wingsEnabled() {
        return this.isEnabled() && Customization.Companion.site().wingsOn();
    }

    public final boolean crownEnabledFor(@Nullable AbstractClientPlayerEntity player) {
        if (!this.canRender(player) || player == null) {
            return false;
        }
        if (Intrinsics.areEqual((Object)player, (Object)this.mc.player)) {
            return Intrinsics.areEqual((Object)"crown", (Object)Customization.Companion.site().headAccessory());
        }
        CustomizationSyncClient.RemoteState state = this.syncClient.getRemoteState(player.getGameProfile().name());
        return state != null && state.crown();
    }

    public final boolean hatEnabledFor(@Nullable AbstractClientPlayerEntity player) {
        if (!this.canRender(player) || player == null) {
            return false;
        }
        if (Intrinsics.areEqual((Object)player, (Object)this.mc.player)) {
            return Intrinsics.areEqual((Object)"hat", (Object)Customization.Companion.site().headAccessory());
        }
        CustomizationSyncClient.RemoteState state = this.syncClient.getRemoteState(player.getGameProfile().name());
        return state != null && state.hat();
    }

    public final boolean haloVisibleFor(@Nullable AbstractClientPlayerEntity player) {
        CustomizationSyncClient.RemoteState state;
        if (player == null) {
            return false;
        }
        boolean selected = Intrinsics.areEqual((Object)player, (Object)this.mc.player) ? Intrinsics.areEqual((Object)"halo", (Object)Customization.Companion.site().headAccessory()) : (state = this.syncClient.getRemoteState(player.getGameProfile().name())) != null && state.halo();
        return GoldenHaloRenderer.shouldRender(player, selected && this.canRender(player));
    }

    public final void submitHalo(@Nullable AbstractClientPlayerEntity player, @NotNull MatrixStack stack, @NotNull OrderedRenderCommandQueue collector, int packedLight) {
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)collector, (String)"collector");
        if (player == null) {
            return;
        }
        boolean helmet = !player.getEquippedStack(EquipmentSlot.HEAD).isEmpty();
        GoldenHaloRenderer.render(player, stack, collector, packedLight, helmet);
    }

    public final boolean wingsEnabledFor(@Nullable AbstractClientPlayerEntity player) {
        if (!this.canRender(player) || player == null) {
            return false;
        }
        if (Intrinsics.areEqual((Object)player, (Object)this.mc.player)) {
            return Customization.Companion.site().wingsOn();
        }
        CustomizationSyncClient.RemoteState state = this.syncClient.getRemoteState(player.getGameProfile().name());
        return state != null && state.wings();
    }

    public final int flagsFor(@Nullable AbstractClientPlayerEntity player) {
        CustomizationSyncClient.RemoteState remote;
        if (player == null) {
            return 0;
        }
        boolean allowed = this.canRender(player);
        boolean self = Intrinsics.areEqual((Object)player, (Object)this.mc.player);
        String accessory = self ? Customization.Companion.site().headAccessory() : null;
        CustomizationSyncClient.RemoteState remoteState = remote = self ? null : this.syncClient.getRemoteState(player.getGameProfile().name());
        boolean haloSelected = self ? Intrinsics.areEqual((Object)"halo", (Object)accessory) : remote != null && remote.halo();
        int flags = 0;
        if (GoldenHaloRenderer.shouldRender(player, haloSelected && allowed)) {
            flags |= 4;
        }
        if (!allowed) {
            return flags;
        }
        if (self) {
            if (Intrinsics.areEqual((Object)"crown", (Object)accessory)) {
                flags |= 1;
            }
            if (Intrinsics.areEqual((Object)"hat", (Object)accessory)) {
                flags |= 2;
            }
            if (Customization.Companion.site().wingsOn()) {
                flags |= 8;
            }
            if (Customization.Companion.site().goatOn()) {
                flags |= 0x10;
            }
        } else if (remote != null) {
            if (remote.crown()) {
                flags |= 1;
            }
            if (remote.hat()) {
                flags |= 2;
            }
            if (remote.wings()) {
                flags |= 8;
            }
        }
        return flags;
    }

    private final boolean canRender(AbstractClientPlayerEntity player) {
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return false;
        }
        ClientPlayerEntity localPlayer = clientPlayerEntity2;
        if (player == null) {
            return false;
        }
        return this.isEnabled() && (!Intrinsics.areEqual((Object)player, (Object)localPlayer) || this.mc.options.getPerspective() != Perspective.FIRST_PERSON) && !player.isInvisible() && !player.isSpectator() && !player.isBaby();
    }

    public final void submitCrown(@Nullable AbstractClientPlayerEntity player, @NotNull MatrixStack stack, @NotNull OrderedRenderCommandQueue collector, int packedLight) {
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)collector, (String)"collector");
        if (player == null) {
            return;
        }
        CrownRenderer.submit(stack, collector, packedLight, this.headYOffset(player));
    }

    public final void submitWings(@Nullable AbstractClientPlayerEntity player, @NotNull MatrixStack stack, @NotNull OrderedRenderCommandQueue collector, int packedLight, boolean fallFlying, float walkSpeed, float attackTime) {
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)collector, (String)"collector");
        if (player == null) {
            return;
        }
        boolean flying = fallFlying || player.getAbilities().flying;
        String bodyModel = this.bodyModelFor(player);
        if (Intrinsics.areEqual((Object)SYNC_KAGUNE, (Object)bodyModel)) {
            KanekiKaguneRenderer.render(player, stack, collector, packedLight, flying, walkSpeed, attackTime);
        } else if (Intrinsics.areEqual((Object)SYNC_SERAPH, (Object)bodyModel)) {
            SeraphEyeWingsRenderer.render(player, stack, collector, packedLight, flying, walkSpeed, attackTime);
        } else if (Intrinsics.areEqual((Object)SYNC_FEATHERED, (Object)bodyModel)) {
            FeatheredWingsRenderer.render((AbstractClientPlayerEntity)player, (MatrixStack)stack, (OrderedRenderCommandQueue)collector, (int)packedLight, (boolean)flying, (float)walkSpeed, (float)attackTime);
        } else {
            RoyalWingsRenderer.render(stack, collector, packedLight, flying, walkSpeed, player.getVelocity().y);
        }
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onEnable() {
        this.nextConnectAttemptAt = 0L;
        this.nextPushAt = 0L;
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onDisable() {
        ShoulderGoatRenderer.reset();
        GoldenHaloRenderer.reset();
        FeatheredWingsRenderer.reset();
        this.syncClient.disconnect("disabled");
        this.publishedUsername = "";
        this.publishedBodyModel = "";
    }

    @EventHandler
    @Protect(value=Level.STD)
    public final void onTick(@NotNull TickEvent event) {
        boolean changed;
        String bodyModel;
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!event.isPre()) {
            return;
        }
        ClientPlayerEntity player = this.mc.player;
        if (player == null || this.mc.world == null) {
            if (this.syncClient.isConnected() || this.syncClient.isConnecting()) {
                this.syncClient.disconnect("no_world");
            }
            this.publishedUsername = "";
            return;
        }
        String username = player.getGameProfile().name();
        Intrinsics.checkNotNull((Object)username);
        if (StringsKt.isBlank((CharSequence)username)) {
            return;
        }
        if (!StringsKt.isBlank((CharSequence)this.publishedUsername) && !StringsKt.equals((String)this.publishedUsername, (String)username, (boolean)true)) {
            this.syncClient.disconnect("identity_changed");
            this.publishedUsername = "";
            this.nextConnectAttemptAt = 0L;
        }
        String headAccessory = Customization.Companion.site().headAccessory();
        boolean wings = Customization.Companion.site().wingsOn();
        String string = bodyModel = wings ? Customization.Companion.site().bodyModel() : "none";
        if (Intrinsics.areEqual((Object)SYNC_FEATHERED, (Object)bodyModel)) {
            FeatheredWingsRenderer.preload();
        }
        this.syncClient.setLocalState(username, headAccessory, wings, bodyModel);
        long now = System.currentTimeMillis();
        if (!this.syncClient.isConnected() && !this.syncClient.isConnecting() && now >= this.nextConnectAttemptAt) {
            this.syncClient.connect(SYNC_HOST, 32117);
            this.nextConnectAttemptAt = now + 5000L;
        }
        boolean bl = changed = !StringsKt.equals((String)username, (String)this.publishedUsername, (boolean)true) || !Intrinsics.areEqual((Object)headAccessory, (Object)this.publishedHeadAccessory) || !Intrinsics.areEqual((Object)bodyModel, (Object)this.publishedBodyModel) || wings != this.publishedWings;
        if (this.syncClient.isConnected() && (changed || now >= this.nextPushAt)) {
            this.syncClient.pushState();
            this.publishedUsername = username;
            this.publishedHeadAccessory = headAccessory;
            this.publishedBodyModel = bodyModel;
            this.publishedWings = wings;
            this.nextPushAt = now + 10000L;
        }
    }

    private final String bodyModelFor(AbstractClientPlayerEntity player) {
        if (Intrinsics.areEqual((Object)player, (Object)this.mc.player)) {
            return Customization.Companion.site().bodyModel();
        }
        CustomizationSyncClient.RemoteState state = this.syncClient.getRemoteState(player.getGameProfile().name());
        return state != null && state.bodyModel() != null ? state.bodyModel() : "royal";
    }

    @Protect(value=Level.MAX)
    private final float headYOffset(AbstractClientPlayerEntity player) {
        boolean helmet;
        boolean flying = player.getAbilities().flying;
        boolean swimming = player.isSwimming();
        boolean bl = helmet = !player.getEquippedStack(EquipmentSlot.HEAD).isEmpty();
        if (player.isInSneakingPose() && !flying && !swimming) {
            return helmet ? 0.38f : 0.28f;
        }
        return helmet ? 0.48f : 0.34f;
    }

    @JvmStatic
    @Nullable
    public static final Customization getInstance() {
        return Companion.getInstance();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\u000b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\rR\u0014\u0010\u0010\u001a\u00020\u000b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\rR\u0014\u0010\u0011\u001a\u00020\u000b8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\rR\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0014R\u0014\u0010\u0017\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0014R\u0014\u0010\u0018\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\rR\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00198\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001bR\u001d\u0010\u001e\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b\u001d\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001f\u00a8\u0006 "}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/Customization.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/Customization;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/Customization;", "Lrtx/kimiko/api/modules/impl/Visuals/cosmetics/CosmeticSiteState;", "site", "()Lrtx/kimiko/api/modules/impl/Visuals/cosmetics/CosmeticSiteState;", "", "FLAG_CROWN", "I", "FLAG_HAT", "FLAG_HALO", "FLAG_WINGS", "FLAG_GOAT", "", "SYNC_KAGUNE", "Ljava/lang/String;", "SYNC_SERAPH", "SYNC_FEATHERED", "SYNC_HOST", "SYNC_PORT", "", "CONNECT_RETRY_MS", "J", "HEARTBEAT_MS", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/Customization;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final Customization getInstance() {
            Customization cached = INSTANCE;
            if (cached != null) {
                return cached;
            }
            return ModuleManager.Companion.get().get(Customization.class);
        }

        private final CosmeticSiteState site() {
            return CosmeticSiteState.Companion.get();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

