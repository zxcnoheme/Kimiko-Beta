/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.NoWhenBranchMatchedException
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.game.TickEvent;
import rtx.kimiko.api.events.impl.input.HotBarScrollEvent;
import rtx.kimiko.api.events.impl.input.KeyPressEvent;
import rtx.kimiko.api.events.impl.input.MouseButtonEvent;
import rtx.kimiko.api.events.impl.module.ModuleToggleEvent;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.impl.Interface.ArmorModule;
import rtx.kimiko.api.modules.impl.Interface.ArrayListModule;
import rtx.kimiko.api.modules.impl.Interface.ClickGui;
import rtx.kimiko.api.modules.impl.Interface.MinimapModule;
import rtx.kimiko.api.modules.impl.Interface.CooldownsModule;
import rtx.kimiko.api.modules.impl.Interface.CustomHotbar;
import rtx.kimiko.api.modules.impl.Interface.HPFocus;
import rtx.kimiko.api.modules.impl.Interface.HotKeysModule;
import rtx.kimiko.api.modules.impl.Interface.InfoModule;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.impl.Interface.InventoryModule;
import rtx.kimiko.api.modules.impl.Interface.KeyStrokesModule;
import rtx.kimiko.api.modules.impl.Interface.MediaPlayerModule;
import rtx.kimiko.api.modules.impl.Interface.MusicBarModule;
import rtx.kimiko.api.modules.impl.Interface.NotificationsModule;
import rtx.kimiko.api.modules.impl.Interface.PotionsModule;
import rtx.kimiko.api.modules.impl.Interface.TargetHudModule;
import rtx.kimiko.api.modules.impl.Interface.WatermarkModule;
import rtx.kimiko.api.modules.impl.Utils.AutoCommands;
import rtx.kimiko.api.modules.impl.Utils.AutoDuel;
import rtx.kimiko.api.modules.impl.Utils.AutoResell;
import rtx.kimiko.api.modules.impl.Utils.AutoSprint;
import rtx.kimiko.api.modules.impl.Utils.AutoSwap;
import rtx.kimiko.api.modules.impl.Utils.AutoTpAccept;
import rtx.kimiko.api.modules.impl.Utils.CameraSettings;
import rtx.kimiko.api.modules.impl.Utils.Cards;
import rtx.kimiko.api.modules.impl.Utils.ClickPearl;
import rtx.kimiko.api.modules.impl.Utils.ClientSounds;
import rtx.kimiko.api.modules.impl.Utils.CrystalOptimizer;
import rtx.kimiko.api.modules.impl.Utils.DeathCoords;
import rtx.kimiko.api.modules.impl.Utils.ElytraSwap;
import rtx.kimiko.api.modules.impl.Utils.FastExp;
import rtx.kimiko.api.modules.impl.Utils.Freelook;
import rtx.kimiko.api.modules.impl.Utils.Globals;
import rtx.kimiko.api.modules.impl.Utils.HandSwap;
import rtx.kimiko.api.modules.impl.Utils.HitSound;
import rtx.kimiko.api.modules.impl.Utils.HolyWorldHelper;
import rtx.kimiko.api.modules.impl.Utils.ItemScroller;
import rtx.kimiko.api.modules.impl.Utils.Optimization;
import rtx.kimiko.api.modules.impl.Utils.Party;
import rtx.kimiko.api.modules.impl.Utils.ShulkerPreview;
import rtx.kimiko.api.modules.impl.Utils.StreamerMode;
import rtx.kimiko.api.modules.impl.Utils.TalTracker;
import rtx.kimiko.api.modules.impl.Utils.TapeMouse;
import rtx.kimiko.api.modules.impl.Utils.VoiceControl;
import rtx.kimiko.api.modules.impl.Visuals.Ambience;
import rtx.kimiko.api.modules.impl.Visuals.AspectRatio;
import rtx.kimiko.api.modules.impl.Visuals.BetterMinecraft;
import rtx.kimiko.api.modules.impl.Visuals.BlobShadowsModule;
import rtx.kimiko.api.modules.impl.Visuals.BlockShardsModule;
import rtx.kimiko.api.modules.impl.Visuals.ColdBreathModule;
import rtx.kimiko.api.modules.impl.Visuals.CloudShadowsModule;
import rtx.kimiko.api.modules.impl.Visuals.DeepStarsModule;
import rtx.kimiko.api.modules.impl.Visuals.DustMotesModule;
import rtx.kimiko.api.modules.impl.Visuals.FallingLeavesModule;
import rtx.kimiko.api.modules.impl.Visuals.FirefliesModule;
import rtx.kimiko.api.modules.impl.Visuals.FootprintsModule;
import rtx.kimiko.api.modules.impl.Visuals.FrostOverlayModule;
import rtx.kimiko.api.modules.impl.Visuals.GoldenHourModule;
import rtx.kimiko.api.modules.impl.Visuals.HitPulseModule;
import rtx.kimiko.api.modules.impl.Visuals.LowHealthHazeModule;
import rtx.kimiko.api.modules.impl.Visuals.MagmaSteamModule;
import rtx.kimiko.api.modules.impl.Visuals.MoonGlintModule;
import rtx.kimiko.api.modules.impl.Visuals.NightComfortModule;
import rtx.kimiko.api.modules.impl.Visuals.PortalSparksModule;
import rtx.kimiko.api.modules.impl.Visuals.PremiumOutlineModule;
import rtx.kimiko.api.modules.impl.Visuals.ScorchDecalsModule;
import rtx.kimiko.api.modules.impl.Visuals.UnderwaterOverlayModule;
import rtx.kimiko.api.modules.impl.Visuals.WaterSplashModule;
import rtx.kimiko.api.modules.impl.Visuals.WaypointsModule;
import rtx.kimiko.api.modules.impl.Visuals.WetGroundModule;
import rtx.kimiko.api.modules.impl.Visuals.WindGustsModule;
import rtx.kimiko.api.modules.impl.Visuals.BlockOverlay;
import rtx.kimiko.api.modules.impl.Visuals.ChinaHat;
import rtx.kimiko.api.modules.impl.Visuals.Crosshair;
import rtx.kimiko.api.modules.impl.Visuals.CustomPet;
import rtx.kimiko.api.modules.impl.Visuals.CustomSwords;
import rtx.kimiko.api.modules.impl.Visuals.Emotions;
import rtx.kimiko.api.modules.impl.Visuals.ExplosionWave;
import rtx.kimiko.api.modules.impl.Visuals.FakePlayer;
import rtx.kimiko.api.modules.impl.Visuals.FogBlur;
import rtx.kimiko.api.modules.impl.Visuals.GlassVapor;
import rtx.kimiko.api.modules.impl.Visuals.GlowEsp;
import rtx.kimiko.api.modules.impl.Visuals.HitBubbles;
import rtx.kimiko.api.modules.impl.Visuals.HitColor;
import rtx.kimiko.api.modules.impl.Visuals.HitParticles;
import rtx.kimiko.api.modules.impl.Visuals.Hitboxes;
import rtx.kimiko.api.modules.impl.Visuals.HpCounter;
import rtx.kimiko.api.modules.impl.Visuals.ItemHighlight;
import rtx.kimiko.api.modules.impl.Visuals.ItemPhysics;
import rtx.kimiko.api.modules.impl.Visuals.JumpCircle;
import rtx.kimiko.api.modules.impl.Visuals.KillEffect;
import rtx.kimiko.api.modules.impl.Visuals.LootView;
import rtx.kimiko.api.modules.impl.Visuals.LyricsTextModule;
import rtx.kimiko.api.modules.impl.Visuals.ModelCollapse;
import rtx.kimiko.api.modules.impl.Visuals.NameTags;
import rtx.kimiko.api.modules.impl.Visuals.NoRender;
import rtx.kimiko.api.modules.impl.Visuals.PortalLive;
import rtx.kimiko.api.modules.impl.Visuals.ProjectileHelper;
import rtx.kimiko.api.modules.impl.Visuals.SeeInvisible;
import rtx.kimiko.api.modules.impl.Visuals.SelfPredictions;
import rtx.kimiko.api.modules.impl.Visuals.SelfTag;
import rtx.kimiko.api.modules.impl.Visuals.ShaderHands;
import rtx.kimiko.api.modules.impl.Visuals.SwingAnimation;
import rtx.kimiko.api.modules.impl.Visuals.TargetESP;
import rtx.kimiko.api.modules.impl.Visuals.Trails;
import rtx.kimiko.api.modules.impl.Visuals.ViewModel;
import rtx.kimiko.api.modules.impl.Visuals.WastedDeath;
import rtx.kimiko.api.modules.impl.Visuals.WorldParticles;
import rtx.kimiko.api.modules.impl.Visuals.brewviewer.BrewViewer;
import rtx.kimiko.api.modules.impl.Visuals.cosmetics.CosmeticSiteState;
import rtx.kimiko.api.modules.restrict.Server;
import rtx.kimiko.api.modules.restrict.ServerRestrictions;
import rtx.kimiko.utils.key.KeyBind;
import sigil.protect.Level;
import sigil.protect.Protect;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00ac\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 ^2\u00020\u0001:\u0001^B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u0004H\u0007b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\t\u0010\u0003J\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u0010\u001a\u00020\u00042\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\u0004\b\u0010\u0010\u0011J3\u0010\u0014\u001a\u00020\u00042\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\u0012\"\u00020\u000eH\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\u0014\u0010\u0015J'\u0010\u0019\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0016*\u00020\u000e2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0013\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u001b\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u001e\u001a\u00020\u001d\u00a2\u0006\u0004\b\u001f\u0010 J\u0013\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\u0004\b!\u0010\u001cJ\u0017\u0010$\u001a\u0004\u0018\u00010\u000e2\u0006\u0010#\u001a\u00020\"\u00a2\u0006\u0004\b$\u0010%J+\u0010*\u001a\u00020\u00042\u0006\u0010'\u001a\u00020&H\u0007b\u0002\b(b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078)\u00a2\u0006\u0004\b*\u0010+J\u001b\u0010-\u001a\u00020\u00042\u0006\u0010'\u001a\u00020,H\u0007b\u0002\b(\u00a2\u0006\u0004\b-\u0010.J\u0015\u00101\u001a\u0002002\u0006\u0010/\u001a\u00020\u000e\u00a2\u0006\u0004\b1\u00102J\u0015\u00103\u001a\u00020\u00042\u0006\u0010/\u001a\u00020\u000e\u00a2\u0006\u0004\b3\u00104J\u001b\u00106\u001a\u00020\u00042\u0006\u0010'\u001a\u000205H\u0007b\u0002\b(\u00a2\u0006\u0004\b6\u00107J\u001b\u00109\u001a\u00020\u00042\u0006\u0010'\u001a\u000208H\u0007b\u0002\b(\u00a2\u0006\u0004\b9\u0010:J\u001b\u0010<\u001a\u00020\u00042\u0006\u0010'\u001a\u00020;H\u0007b\u0002\b(\u00a2\u0006\u0004\b<\u0010=J\u000f\u0010>\u001a\u000200H\u0002\u00a2\u0006\u0004\b>\u0010?J'\u0010A\u001a\u0002002\u0006\u0010@\u001a\u00020\nH\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\bA\u0010BJ/\u0010D\u001a\u0002002\u0006\u0010@\u001a\u00020\n2\u0006\u0010C\u001a\u000200H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\bD\u0010EJ/\u0010G\u001a\u0002002\u0006\u0010F\u001a\u00020\n2\u0006\u0010C\u001a\u000200H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\bG\u0010EJ/\u0010H\u001a\u0002002\u0006\u0010/\u001a\u00020\u000e2\u0006\u0010C\u001a\u000200H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\bH\u0010IR$\u0010L\u001a\u0012\u0012\u0004\u0012\u00020\u000e0Jj\b\u0012\u0004\u0012\u00020\u000e`K8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bL\u0010MR\u001a\u0010N\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bN\u0010OR(\u0010Q\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\u0017\u0012\u0004\u0012\u00020\u000e0P8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bQ\u0010RR\u001a\u0010T\u001a\b\u0012\u0004\u0012\u00020\u000e0S8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bT\u0010UR\u0014\u0010W\u001a\u00020V8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bW\u0010XR\u001c\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bY\u0010OR\u0016\u0010Z\u001a\u0002008\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bZ\u0010[R$\u0010\\\u001a\u0012\u0012\u0004\u0012\u00020\u000e0Jj\b\u0012\u0004\u0012\u00020\u000e`K8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\\\u0010MR\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000b\u0010]\u00a8\u0006_"}, d2={"Lrtx/kimiko/api/modules/ModuleManager;", "", "<init>", "()V", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "init", "", "revision", "()I", "", "Lrtx/kimiko/api/modules/Module;", "next", "replaceScripted", "(Ljava/util/List;)V", "", "mods", "register", "([Lrtx/kimiko/api/modules/Module;)V", "T", "Ljava/lang/Class;", "type", "get", "(Ljava/lang/Class;)Lrtx/kimiko/api/modules/Module;", "getAll", "()Ljava/util/List;", "Lrtx/kimiko/api/modules/Category;", "category", "getByCategory", "(Lrtx/kimiko/api/modules/Category;)Ljava/util/List;", "getEnabled", "", "name", "findByName", "(Ljava/lang/String;)Lrtx/kimiko/api/modules/Module;", "Lrtx/kimiko/api/events/impl/game/TickEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "STD", "onTick", "(Lrtx/kimiko/api/events/impl/game/TickEvent;)V", "Lrtx/kimiko/api/events/impl/module/ModuleToggleEvent;", "onModuleToggle", "(Lrtx/kimiko/api/events/impl/module/ModuleToggleEvent;)V", "module", "", "isSuspended", "(Lrtx/kimiko/api/modules/Module;)Z", "clearSuspension", "(Lrtx/kimiko/api/modules/Module;)V", "Lrtx/kimiko/api/events/impl/input/KeyPressEvent;", "onKey", "(Lrtx/kimiko/api/events/impl/input/KeyPressEvent;)V", "Lrtx/kimiko/api/events/impl/input/MouseButtonEvent;", "onMouse", "(Lrtx/kimiko/api/events/impl/input/MouseButtonEvent;)V", "Lrtx/kimiko/api/events/impl/input/HotBarScrollEvent;", "onScroll", "(Lrtx/kimiko/api/events/impl/input/HotBarScrollEvent;)V", "shouldIgnoreBinds", "()Z", "code", "toggleBound", "(I)Z", "pressed", "applyKeyBound", "(IZ)Z", "button", "applyMouseBound", "applyBind", "(Lrtx/kimiko/api/modules/Module;Z)Z", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "modules", "Ljava/util/ArrayList;", "readOnlyModules", "Ljava/util/List;", "Ljava/util/IdentityHashMap;", "moduleMap", "Ljava/util/IdentityHashMap;", "", "suspended", "Ljava/util/Set;", "Lnet/minecraft/MinecraftClient;", "mc", "Lnet/minecraft/MinecraftClient;", "cachedEnabledList", "enabledDirty", "Z", "scripted", "I", "Companion", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nModuleManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ModuleManager.kt\nrtx/kimiko/api/modules/ModuleManager\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,358:1\n296#2,2:359\n777#2:361\n873#2,2:362\n777#2:364\n873#2,2:365\n296#2,2:367\n*S KotlinDebug\n*F\n+ 1 ModuleManager.kt\nrtx/kimiko/api/modules/ModuleManager\n*L\n188#1:359,2\n197#1:361\n197#1:362,2\n201#1:364\n201#1:365,2\n208#1:367,2\n*E\n"})
public final class ModuleManager {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ArrayList<Module> modules = new ArrayList();
    @NotNull
    private final List<Module> readOnlyModules;
    @NotNull
    private final IdentityHashMap<Class<? extends Module>, Module> moduleMap;
    @NotNull
    private final Set<Module> suspended;
    @NotNull
    private final MinecraftClient mc;
    @NotNull
    private volatile List<Module> cachedEnabledList;
    private volatile boolean enabledDirty;
    @NotNull
    private final ArrayList<Module> scripted;
    private volatile int revision;
    @NotNull
    private static final ModuleManager INSTANCE = new ModuleManager();

    private ModuleManager() {
        List list = Collections.unmodifiableList((List)this.modules);
        Intrinsics.checkNotNullExpressionValue(list, (String)"unmodifiableList(...)");
        this.readOnlyModules = list;
        this.moduleMap = new IdentityHashMap();
        Set set = Collections.newSetFromMap(new IdentityHashMap());
        Intrinsics.checkNotNullExpressionValue(set, (String)"newSetFromMap(...)");
        this.suspended = set;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        this.mc = minecraftClient2;
        this.cachedEnabledList = CollectionsKt.emptyList();
        this.enabledDirty = true;
        this.scripted = new ArrayList();
    }

    @Protect(value=Level.CROWN)
    public final void init() {
        Module[] moduleArray = new Module[]{new HitSound(), new AutoSprint(), new Ambience(), new BetterMinecraft(), new AspectRatio(), new ClickGui(), new CustomHotbar(), new FogBlur(), new GlassVapor(), new HitBubbles(), new HpCounter(), new HitColor(), new Hitboxes(), new SelfTag(), new InterfaceModule(), new NotificationsModule(), new WatermarkModule(), new HotKeysModule(), new TargetHudModule(), new PotionsModule(), new MediaPlayerModule(), new MusicBarModule(), new CooldownsModule(), new InfoModule(), new ArrayListModule(), new ArmorModule(), new InventoryModule(), new HPFocus(), new KeyStrokesModule(), new ViewModel(), new Freelook(), new HandSwap(), new VoiceControl(), new CameraSettings(), new ItemPhysics(), new CustomSwords(), new JumpCircle(), new ExplosionWave(), new Crosshair(), new KillEffect(), new ModelCollapse(), new NameTags(), new GlowEsp(), new SeeInvisible(), new ChinaHat(), new Emotions(), new BlockOverlay(), new FakePlayer(), new ShaderHands(), new ItemHighlight(), new LootView(), new NoRender(), new WorldParticles(), new LyricsTextModule(), new HitParticles(), new SelfPredictions(), new ProjectileHelper(), new SwingAnimation(), new TargetESP(), new WastedDeath(), new BrewViewer(), new Trails(), new ShulkerPreview(), new AutoDuel(), new AutoResell(), new AutoTpAccept(), new ClientSounds(), new DeathCoords(), new ClickPearl(), new HolyWorldHelper(), new TalTracker(), new ItemScroller(), new StreamerMode(), new ElytraSwap(), new FastExp(), new AutoSwap(), new AutoCommands(), new TapeMouse(), new CrystalOptimizer(), new Optimization(), new Globals(), new MinimapModule(), new WaypointsModule(), new BlobShadowsModule(), new BlockShardsModule(), new ScorchDecalsModule(), new FirefliesModule(), new FallingLeavesModule(), new ColdBreathModule(), new PortalSparksModule(), new NightComfortModule(), new GoldenHourModule(), new WetGroundModule(), new FootprintsModule(), new PremiumOutlineModule(), new UnderwaterOverlayModule(), new HitPulseModule(), new LowHealthHazeModule(), new DustMotesModule(), new WaterSplashModule(), new WindGustsModule(), new DeepStarsModule(), new CloudShadowsModule(), new FrostOverlayModule(), new MagmaSteamModule(), new MoonGlintModule()};
        this.register(moduleArray);
        EventBus.Companion.get().subscribe(this);
        CosmeticSiteState.Companion.get().start();
        new CustomPet().enable();
    }

    public final int revision() {
        return this.revision;
    }

    public final void replaceScripted(@NotNull List<? extends Module> next) {
        Intrinsics.checkNotNullParameter(next, (String)"next");
        for (Module module : this.scripted) {
            if (module.isEnabled()) {
                module.setEnabled(false);
            }
            this.modules.remove(module);
            this.moduleMap.remove(module.getClass());
        }
        this.scripted.clear();
        for (Module module : next) {
            this.modules.add(module);
            this.scripted.add(module);
        }
        this.enabledDirty = true;
        int n = this.revision;
        this.revision = n + 1;
    }

    @Protect(value=Level.CROWN)
    private final void register(Module ... mods) {
        for (Module mod : mods) {
            this.modules.add(mod);
            ((Map)this.moduleMap).put(mod.getClass(), mod);
        }
        this.enabledDirty = true;
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public final <T extends Module> T get(@NotNull Class<T> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        Module cached = this.moduleMap.get(type);
        if (cached != null) {
            return (T)cached;
        }
        for (Module mod : this.modules) {
            if (type.isInstance(mod)) {
                this.moduleMap.put(type, mod);
                return (T)mod;
            }
        }
        return null;
    }

    @NotNull
    public final List<Module> getAll() {
        return this.readOnlyModules;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public final List<Module> getByCategory(@NotNull Category category) {
        Intrinsics.checkNotNullParameter((Object)category, "category");
        List<Module> result = new ArrayList<>();
        for (Module m : this.modules) {
            if (m.getCategory() == category) {
                result.add(m);
            }
        }
        return result;
    }

    @NotNull
    public final List<Module> getEnabled() {
        if (this.enabledDirty) {
            List<Module> result = new ArrayList<>();
            for (Module m : this.modules) {
                if (m.isEnabled()) {
                    result.add(m);
                }
            }
            List<Module> list = Collections.unmodifiableList(result);
            Intrinsics.checkNotNullExpressionValue(list, "unmodifiableList(...)");
            this.cachedEnabledList = list;
            this.enabledDirty = false;
        }
        return this.cachedEnabledList;
    }

    @Nullable
    public final Module findByName(@NotNull String name) {
        Intrinsics.checkNotNullParameter((Object)name, "name");
        for (Module mod : this.modules) {
            if (StringsKt.equals(mod.getName(), name, true)) {
                return mod;
            }
        }
        return null;
    }

    @EventHandler
    @Protect(value=Level.STD)
    public final void onTick(@NotNull TickEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (!event.isPre()) {
            return;
        }
        if (this.suspended.isEmpty() && !ServerRestrictions.onRemoteServer()) {
            return;
        }
        EnumSet<Server> here = ServerRestrictions.current();
        for (Module module : this.modules) {
            if (module.isEnabled()) {
                if (ServerRestrictions.isHiddenBy(module, here)) {
                    module.disableInstant();
                    this.suspended.add(module);
                    continue;
                }
                String reason = ServerRestrictions.blockReason(module, here);
                if (reason == null) continue;
                module.disableInstant();
                this.suspended.add(module);
                ServerRestrictions.notifyOnce(module, reason);
                continue;
            }
            if (!this.suspended.contains(module) || ServerRestrictions.isHiddenBy(module, here) || ServerRestrictions.blockReason(module, here) != null) continue;
            module.enable();
        }
    }

    @EventHandler
    public final void onModuleToggle(@NotNull ModuleToggleEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (event.isEnabled()) {
            this.suspended.remove(event.getModule());
        }
        this.enabledDirty = true;
    }

    public final boolean isSuspended(@NotNull Module module) {
        Intrinsics.checkNotNullParameter((Object)module, (String)"module");
        return this.suspended.contains(module);
    }

    public final void clearSuspension(@NotNull Module module) {
        Intrinsics.checkNotNullParameter((Object)module, (String)"module");
        this.suspended.remove(module);
    }

    @EventHandler
    public final void onKey(@NotNull KeyPressEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (this.shouldIgnoreBinds()) {
            return;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[event.action.ordinal()]) {
            case 1: {
                this.applyKeyBound(event.keyCode, true);
                break;
            }
            case 2: {
                this.applyKeyBound(event.keyCode, false);
            }
        }
    }

    @EventHandler
    public final void onMouse(@NotNull MouseButtonEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (this.shouldIgnoreBinds()) {
            return;
        }
        switch (WhenMappings.$EnumSwitchMapping$1[event.action.ordinal()]) {
            case 1: {
                boolean bl = this.applyMouseBound(event.button, true);
                break;
            }
            case 2: {
                boolean bl = this.applyMouseBound(event.button, false);
                break;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    @EventHandler
    public final void onScroll(@NotNull HotBarScrollEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        if (this.shouldIgnoreBinds()) {
            return;
        }
        double vertical = event.getVertical();
        int code = vertical > 0.0 ? 1000 : (vertical < 0.0 ? 1001 : -1);
        if (code != -1 && this.toggleBound(code)) {
            event.cancel();
        }
    }

    private final boolean shouldIgnoreBinds() {
        return this.mc.currentScreen != null;
    }

    @Protect(value=Level.CROWN)
    private final boolean toggleBound(int code) {
        boolean toggled = false;
        for (Module module : this.modules) {
            if (module instanceof ClickGui || module.getBindType() != Module.BindType.KEY) continue;
            KeyBind bind = module.getBind();
            if (!bind.isBound() || bind.getCode() != code) continue;
            module.toggle();
            toggled = true;
        }
        return toggled;
    }

    @Protect(value=Level.CROWN)
    private final boolean applyKeyBound(int code, boolean pressed) {
        boolean any = false;
        for (Module module : this.modules) {
            if (module instanceof ClickGui || module.getBindType() != Module.BindType.KEY) continue;
            KeyBind bind = module.getBind();
            if (!bind.isBound() || bind.getCode() != code) continue;
            any = this.applyBind(module, pressed) || any;
        }
        return any;
    }

    @Protect(value=Level.CROWN)
    private final boolean applyMouseBound(int button, boolean pressed) {
        boolean any = false;
        for (Module module : this.modules) {
            if (module.getBindType() != Module.BindType.KEY) continue;
            KeyBind bind = module.getBind();
            if (!bind.isBound() || !bind.matchesMouseButton(button)) continue;
            any = this.applyBind(module, pressed) || any;
        }
        return any;
    }

    @Protect(value=Level.CROWN)
    private final boolean applyBind(Module module, boolean pressed) {
        if (module.getBindMode() == Module.BindMode.HOLD) {
            module.setEnabled(pressed);
            return true;
        }
        if (pressed) {
            module.toggle();
            return true;
        }
        return false;
    }

    @JvmStatic
    @NotNull
    public static final ModuleManager get() {
        return Companion.get();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/api/modules/ModuleManager.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/ModuleManager;", "Lkotlin/jvm/JvmStatic;", "get", "()Lrtx/kimiko/api/modules/ModuleManager;", "INSTANCE", "Lrtx/kimiko/api/modules/ModuleManager;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final ModuleManager get() {
            return INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=3, xi=48)
    public static final class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] nArray = new int[KeyPressEvent.Action.values().length];
            try {
                nArray[KeyPressEvent.Action.PRESS.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[KeyPressEvent.Action.RELEASE.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$0 = nArray;
            nArray = new int[MouseButtonEvent.Action.values().length];
            try {
                nArray[MouseButtonEvent.Action.PRESS.ordinal()] = 1;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            try {
                nArray[MouseButtonEvent.Action.RELEASE.ordinal()] = 2;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
            $EnumSwitchMapping$1 = nArray;
        }
    }
}

