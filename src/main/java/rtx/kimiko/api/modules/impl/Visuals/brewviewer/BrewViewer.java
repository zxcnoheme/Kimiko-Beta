/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.util.Hand
 *  net.minecraft.util.ActionResult
 *  net.minecraft.util.ActionResult.Success
 *  net.minecraft.item.ItemStack
 *  net.minecraft.block.BrewingStandBlock
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.hit.HitResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.block.BlockState
 *  net.minecraft.state.property.Property
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.hit.BlockHitResult
 *  net.minecraft.client.render.Camera
 *  net.minecraft.client.gui.screen.ingame.BrewingStandScreen
 *  net.minecraft.client.network.ClientPlayerInteractionManager
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Vector4f
 */
package rtx.kimiko.api.modules.impl.Visuals.brewviewer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.item.ItemStack;
import net.minecraft.block.BrewingStandBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Property;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.client.render.Camera;
import net.minecraft.client.gui.screen.ingame.BrewingStandScreen;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector4f;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.impl.Visuals.brewviewer.BrewStandCache;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.animations.Decelerate;
import rtx.kimiko.utils.animations.Direction;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Feature(value={"brewviewer"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0001\rB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u00048\u0000X\u0080\u0004\u00a2\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u001a\u0010\u000b\u001a\u00020\u00048\u0000X\u0080\u0004\u00a2\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b\u00ca\u0001\u0010\b\u000e\u0012\f\b\u000f\u0012\b\b\fJ\u0004\b\b(\u0010\u00a8\u0006\u0011"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewViewer;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "autoSync", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "getAutoSync$rtx_kimiko_kimiko", "()Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "showResult", "getShowResult$rtx_kimiko_kimiko", "showTimer", "getShowTimer$rtx_kimiko_kimiko", "Overlay", "Lrtx/kimiko/api/liteapi/Feature;", "value", "brewviewer", "rtx.kimiko:kimiko"})
public final class BrewViewer
extends Module {
    @NotNull
    private final BooleanSetting autoSync = (BooleanSetting)this.register((Setting)new BooleanSetting("Автосинк", "Один раз открыть и закрыть зельеварку ванильным ПКМ, чтобы считать содержимое.", true));
    @NotNull
    private final BooleanSetting showResult = (BooleanSetting)this.register((Setting)new BooleanSetting("Превью результата", "Показывать зелье, которое получится после варки.", true));
    @NotNull
    private final BooleanSetting showTimer = (BooleanSetting)this.register((Setting)new BooleanSetting("Таймер", "Показывать оставшиеся секунды рядом с процентом.", true));

    public BrewViewer() {
        super("Brew Viewer", "Голографическое превью варки при наводке на зельеварку.", Category.VISUALS);
        EventBus.Companion.get().subscribe(new Overlay(this));
    }

    @NotNull
    public final BooleanSetting getAutoSync$rtx_kimiko_kimiko() {
        return this.autoSync;
    }

    @NotNull
    public final BooleanSetting getShowResult$rtx_kimiko_kimiko() {
        return this.showResult;
    }

    @NotNull
    public final BooleanSetting getShowTimer$rtx_kimiko_kimiko() {
        return this.showTimer;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u00c8\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 \u0084\u00012\u00020\u0001:\n\u0085\u0001\u0086\u0001\u0087\u0001\u0088\u0001\u0084\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\n\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0003b\u0002\b\t\u00a2\u0006\u0004\b\n\u0010\u000bJ'\u0010\u0012\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0014\u001a\u0004\u0018\u00010\fH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J+\u0010\u0019\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ!\u0010\u001c\u001a\u00020\u001b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ)\u0010%\u001a\u0004\u0018\u00010$2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020 H\u0002\u00a2\u0006\u0004\b%\u0010&J!\u0010*\u001a\u00020)2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010(\u001a\u00020'H\u0002\u00a2\u0006\u0004\b*\u0010+J1\u00100\u001a\u00020\b2\b\u0010,\u001a\u0004\u0018\u00010$2\u0006\u0010-\u001a\u00020)2\u0006\u0010.\u001a\u00020\u001b2\u0006\u0010/\u001a\u00020 H\u0002\u00a2\u0006\u0004\b0\u00101J7\u00106\u001a\u00020\b2\u000e\u00103\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010$022\u0006\u00104\u001a\u00020 2\u0006\u00105\u001a\u00020'2\u0006\u0010/\u001a\u00020 H\u0002\u00a2\u0006\u0004\b6\u00107JW\u0010@\u001a\u00020\b2\u0006\u00108\u001a\u00020 2\u0006\u00109\u001a\u00020 2\u0006\u0010:\u001a\u00020 2\u0006\u0010;\u001a\u00020 2\u0006\u0010<\u001a\u00020'2\u0006\u0010=\u001a\u00020 2\u0006\u0010>\u001a\u00020 2\u0006\u0010?\u001a\u00020 2\u0006\u0010/\u001a\u00020 H\u0002\u00a2\u0006\u0004\b@\u0010AJG\u0010H\u001a\u00020\b2\u0006\u0010B\u001a\u00020 2\u0006\u0010C\u001a\u00020 2\u0006\u0010D\u001a\u00020 2\u0006\u0010E\u001a\u00020 2\u0006\u0010F\u001a\u00020 2\u0006\u0010G\u001a\u00020 2\u0006\u0010/\u001a\u00020 H\u0002\u00a2\u0006\u0004\bH\u0010IJI\u0010M\u001a\u00020\b2\u0006\u00108\u001a\u00020 2\u0006\u0010J\u001a\u00020 2\u0006\u0010:\u001a\u00020 2\u0006\u0010K\u001a\u00020 2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010L\u001a\u00020 2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\bM\u0010NJA\u0010O\u001a\u00020\b2\u0006\u00108\u001a\u00020 2\u0006\u0010\"\u001a\u00020 2\u0006\u0010:\u001a\u00020 2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010L\u001a\u00020 2\u0006\u0010/\u001a\u00020 H\u0002\u00a2\u0006\u0004\bO\u0010PJ\u001b\u0010R\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020QH\u0003b\u0002\b\t\u00a2\u0006\u0004\bR\u0010SJ7\u0010Y\u001a\u00020\b2\u0006\u0010U\u001a\u00020T2\u0006\u0010-\u001a\u00020)2\u0006\u0010V\u001a\u00020 2\u0006\u0010W\u001a\u00020 2\u0006\u0010X\u001a\u00020 H\u0002\u00a2\u0006\u0004\bY\u0010ZJ\u000f\u0010[\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b[\u0010\u001fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\\R\u0014\u0010^\u001a\u00020]8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b^\u0010_R$\u0010c\u001a\u0012\u0012\u0004\u0012\u00020a0`j\b\u0012\u0004\u0012\u00020a`b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bc\u0010dR$\u0010f\u001a\u0012\u0012\u0004\u0012\u00020e0`j\b\u0012\u0004\u0012\u00020e`b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bf\u0010dR$\u0010h\u001a\u0012\u0012\u0004\u0012\u00020g0`j\b\u0012\u0004\u0012\u00020g`b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bh\u0010dR$\u0010j\u001a\u0012\u0012\u0004\u0012\u00020i0`j\b\u0012\u0004\u0012\u00020i`b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bj\u0010dR\u0016\u0010k\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bk\u0010lR\u0018\u0010m\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bm\u0010nR\u0014\u0010p\u001a\u00020o8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bp\u0010qR\u0016\u0010r\u001a\u00020 8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\br\u0010sR\u0016\u0010u\u001a\u00020t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bu\u0010vR\u0018\u0010x\u001a\u0004\u0018\u00010w8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bx\u0010yR\u0018\u0010{\u001a\u0004\u0018\u00010z8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b{\u0010|R\u0016\u0010}\u001a\u00020 8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b}\u0010sR\u0016\u0010~\u001a\u00020 8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b~\u0010sR\u0018\u0010\u007f\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u007f\u0010nR\u0018\u0010\u0080\u0001\u001a\u00020t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0080\u0001\u0010vR\u0018\u0010\u0081\u0001\u001a\u00020t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0081\u0001\u0010vR\u0018\u0010\u0082\u0001\u001a\u00020t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0082\u0001\u0010vR\u0018\u0010\u0083\u0001\u001a\u00020t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0007\n\u0005\b\u0083\u0001\u0010v\u00a8\u0006\u0089\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewViewer$Overlay;", "", "Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewViewer;", "module", "<init>", "(Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewViewer;)V", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "event", "", "Lrtx/kimiko/api/events/EventHandler;", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "Lnet/minecraft/BlockPos;", "pos", "", "hasBottle", "Lnet/minecraft/ClientWorld;", "level", "readBottles", "(Lnet/minecraft/BlockPos;[ZLnet/minecraft/ClientWorld;)V", "aimedStand", "()Lnet/minecraft/BlockPos;", "aimed", "Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewStandCache$Entry;", "entry", "handleAutoSync", "(Lnet/minecraft/BlockPos;Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewStandCache$Entry;[Z)V", "", "needsSync", "(Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewStandCache$Entry;[Z)Z", "resetSync", "()V", "", "wx", "wy", "wz", "", "project", "(FFF)[F", "", "slot", "Lnet/minecraft/ItemStack;", "displayStack", "(Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewStandCache$Entry;I)Lnet/minecraft/ItemStack;", "p", "stack", "unknown", "alpha", "addSlot", "([FLnet/minecraft/ItemStack;ZF)V", "", "points", "thickness", "rgb", "addPolyline", "(Ljava/util/List;FIF)V", "bx", "y", "bz", "radius", "segments", "fillFraction", "fillThickness", "backThickness", "addRing", "(FFFFIFFFF)V", "ix", "iy", "iz", "sx", "sy", "sz", "addThreads", "(FFFFFFF)V", "by", "t", "progress", "buildScene", "(FFFFLrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewStandCache$Entry;F[Z)V", "addStatusText", "(FFFLrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewStandCache$Entry;FF)V", "Lrtx/kimiko/api/events/impl/render/HudRenderEvent;", "onHudRender", "(Lrtx/kimiko/api/events/impl/render/HudRenderEvent;)V", "Lnet/minecraft/DrawContext;", "graphics", "cx", "cy", "sizePx", "drawItem", "(Lnet/minecraft/DrawContext;Lnet/minecraft/ItemStack;FFF)V", "clearScene", "Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewViewer;", "Lnet/minecraft/MinecraftClient;", "mc", "Lnet/minecraft/MinecraftClient;", "Ljava/util/ArrayList;", "Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewViewer$Overlay$SlotDraw;", "Lkotlin/collections/ArrayList;", "slots", "Ljava/util/ArrayList;", "Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewViewer$Overlay$LineDraw;", "lines", "Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewViewer$Overlay$DotDraw;", "dots", "Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewViewer$Overlay$TextDraw;", "texts", "sceneReady", "Z", "target", "Lnet/minecraft/BlockPos;", "Lrtx/kimiko/utils/animations/Decelerate;", "appear", "Lrtx/kimiko/utils/animations/Decelerate;", "spin", "F", "", "lastNs", "J", "Lorg/joml/Matrix4f;", "mvp", "Lorg/joml/Matrix4f;", "Lnet/minecraft/Vec3d;", "cam", "Lnet/minecraft/Vec3d;", "guiW", "guiH", "syncPos", "syncRequestedAtMs", "syncScreenSeenAtMs", "lastSyncMs", "lastAimedMs", "Companion", "SlotDraw", "LineDraw", "DotDraw", "TextDraw", "rtx.kimiko:kimiko"})
    public static final class Overlay {
        @NotNull
        public static final Companion Companion = new Companion(null);
        @NotNull
        private final BrewViewer module;
        @NotNull
        private final MinecraftClient mc;
        @NotNull
        private final ArrayList<SlotDraw> slots;
        @NotNull
        private final ArrayList<LineDraw> lines;
        @NotNull
        private final ArrayList<DotDraw> dots;
        @NotNull
        private final ArrayList<TextDraw> texts;
        private boolean sceneReady;
        @Nullable
        private BlockPos target;
        @NotNull
        private final Decelerate appear;
        private float spin;
        private long lastNs;
        @Nullable
        private Matrix4f mvp;
        @Nullable
        private Vec3d cam;
        private float guiW;
        private float guiH;
        @Nullable
        private BlockPos syncPos;
        private long syncRequestedAtMs;
        private long syncScreenSeenAtMs;
        private long lastSyncMs;
        private long lastAimedMs;
        private static final float DOME_RADIUS = 1.05f;
        private static final float ORBIT_RADIUS = 0.8f;
        private static final long SYNC_COOLDOWN_MS = 3000L;
        private static final long SYNC_OPEN_TIMEOUT_MS = 1500L;
        private static final long SYNC_HOLD_MS = 120L;
        private static final long HIDE_DELAY_MS = 500L;

        public Overlay(@NotNull BrewViewer module) {
            Intrinsics.checkNotNullParameter((Object)module, (String)"module");
            this.module = module;
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            this.mc = minecraftClient2;
            this.slots = new ArrayList(8);
            this.lines = new ArrayList(220);
            this.dots = new ArrayList(16);
            this.texts = new ArrayList(4);
            this.appear = new Decelerate();
            this.lastNs = System.nanoTime();
            this.syncRequestedAtMs = -1L;
            this.syncScreenSeenAtMs = -1L;
            this.appear.setMs(260);
            this.appear.setValue(1.0);
            this.appear.setDirection(Direction.BACKWARDS);
            this.appear.counter.setTime(System.currentTimeMillis() - (long)10000);
        }

        @EventHandler
        private final void onWorldRender(WorldRenderEvent event) {
            Vec3d vec3d2;
            if (event.isPortalPass()) {
                return;
            }
            long now = System.nanoTime();
            float dt = Math.min((float)(now - this.lastNs) / 1.0E9f, 0.1f);
            this.lastNs = now;
            this.clearScene();
            boolean active = this.module.isEnabled();
            if (active) {
                BrewStandCache.tick(this.mc);
            }
            ClientWorld clientWorld3 = this.mc.world;
            if (clientWorld3 == null) {
                return;
            }
            ClientWorld level = clientWorld3;
            if (this.mc.player == null) {
                return;
            }
            BlockPos aimed = active ? this.aimedStand() : null;
            boolean[] hasBottle = new boolean[3];
            BrewStandCache.Entry aimedEntry = null;
            if (aimed != null) {
                this.readBottles(aimed, hasBottle, level);
                aimedEntry = BrewStandCache.get(aimed);
            }
            if (active) {
                this.handleAutoSync(aimed, aimedEntry, hasBottle);
            } else {
                this.resetSync();
            }
            long nowMs = System.currentTimeMillis();
            if (aimed != null) {
                this.lastAimedMs = nowMs;
                if (Intrinsics.areEqual((Object)aimed, (Object)this.target)) {
                    if (this.appear.getDirection() == Direction.BACKWARDS) {
                        this.appear.setDirection(Direction.FORWARDS);
                        this.appear.counter.resetCounter();
                    }
                } else {
                    this.target = aimed;
                    this.appear.setDirection(Direction.FORWARDS);
                    this.appear.counter.resetCounter();
                }
            } else if (!(this.appear.getDirection() != Direction.FORWARDS || active && nowMs - this.lastAimedMs <= 500L)) {
                this.appear.setDirection(Direction.BACKWARDS);
                this.appear.counter.resetCounter();
            }
            Double d = this.appear.getOutput();
            float t = d != null ? (float)d.doubleValue() : 0.0f;
            BlockPos currentTarget = this.target;
            if (t <= 0.01f || currentTarget == null) {
                if (!active) {
                    this.target = null;
                }
                return;
            }
            this.spin += dt * 0.55f;
            this.spin %= (float)Math.PI * 2;
            this.guiW = Position.Companion.screenWidth();
            this.guiH = Position.Companion.screenHeight();
            Matrix4f projMat = event.getProjectionMatrix();
            if (this.guiW < 1.0f || this.guiH < 1.0f || projMat == null) {
                return;
            }
            if (event.getCamera() == null) {
                vec3d2 = this.mc.gameRenderer.getCamera().getCameraPos();
            } else {
                Camera camera2 = event.getCamera();
                Intrinsics.checkNotNull((Object)camera2);
                vec3d2 = camera2.getCameraPos();
            }
            this.cam = vec3d2;
            this.mvp = new Matrix4f((Matrix4fc)projMat).mul((Matrix4fc)event.getStack().peek().getPositionMatrix());
            BrewStandCache.Entry entry = BrewStandCache.get(currentTarget);
            float progress = BrewStandCache.progress(this.mc, entry);
            if (!Intrinsics.areEqual((Object)currentTarget, (Object)aimed)) {
                this.readBottles(currentTarget, hasBottle, level);
            }
            this.buildScene((float)currentTarget.getX() + 0.5f, currentTarget.getY(), (float)currentTarget.getZ() + 0.5f, t, entry, progress, hasBottle);
            this.sceneReady = true;
        }

        private final void readBottles(BlockPos pos, boolean[] hasBottle, ClientWorld level) {
            BlockState blockState2 = level.getBlockState(pos);
            Intrinsics.checkNotNullExpressionValue((Object)blockState2, (String)"getBlockState(...)");
            BlockState state = blockState2;
            if (state.getBlock() instanceof BrewingStandBlock) {
                for (int i = 0; i < 3; ++i) {
                    Comparable comparable = state.get((Property)BrewingStandBlock.BOTTLE_PROPERTIES[i]);
                    Intrinsics.checkNotNullExpressionValue((Object)comparable, (String)"getValue(...)");
                    hasBottle[i] = (Boolean)comparable;
                }
            }
        }

        private final BlockPos aimedStand() {
            HitResult hit = this.mc.crosshairTarget;
            if (!(hit instanceof BlockHitResult)) {
                return null;
            }
            BlockPos blockPos2 = ((BlockHitResult)hit).getBlockPos();
            Intrinsics.checkNotNullExpressionValue((Object)blockPos2, (String)"getBlockPos(...)");
            BlockPos pos = blockPos2;
            ClientWorld clientWorld3 = this.mc.world;
            if (clientWorld3 == null) {
                return null;
            }
            ClientWorld level = clientWorld3;
            if (!(level.getBlockState(pos).getBlock() instanceof BrewingStandBlock)) {
                return null;
            }
            return pos.toImmutable();
        }

        private final void handleAutoSync(BlockPos aimed, BrewStandCache.Entry entry, boolean[] hasBottle) {
            ClientPlayerEntity player = this.mc.player;
            ClientPlayerInteractionManager gameMode = this.mc.interactionManager;
            if (!this.module.getAutoSync$rtx_kimiko_kimiko().getValue() || gameMode == null || player == null) {
                this.resetSync();
                return;
            }
            long nowMs = System.currentTimeMillis();
            if (this.mc.currentScreen instanceof BrewingStandScreen) {
                if (this.syncPos == null) {
                    return;
                }
                if (this.syncScreenSeenAtMs < 0L) {
                    this.syncScreenSeenAtMs = nowMs;
                }
                if (nowMs - this.syncScreenSeenAtMs >= 120L) {
                    player.closeHandledScreen();
                    this.resetSync();
                }
                return;
            }
            if (this.mc.currentScreen != null) {
                this.resetSync();
                return;
            }
            if (this.syncPos != null) {
                if (nowMs - this.syncRequestedAtMs > 1500L) {
                    this.resetSync();
                }
                return;
            }
            if (aimed == null || !this.needsSync(entry, hasBottle)) {
                return;
            }
            if (nowMs - this.lastSyncMs < 3000L) {
                return;
            }
            HitResult hit = this.mc.crosshairTarget;
            if (player.isSneaking() || !(hit instanceof BlockHitResult)) {
                return;
            }
            ActionResult actionResult2 = gameMode.interactBlock(player, Hand.MAIN_HAND, (BlockHitResult)hit);
            Intrinsics.checkNotNullExpressionValue((Object)actionResult2, (String)"useItemOn(...)");
            ActionResult result = actionResult2;
            if (result instanceof ActionResult.Success) {
                player.swingHand(Hand.MAIN_HAND);
            }
            this.syncPos = aimed;
            this.syncRequestedAtMs = nowMs;
            this.syncScreenSeenAtMs = -1L;
            this.lastSyncMs = nowMs;
        }

        private final boolean needsSync(BrewStandCache.Entry entry, boolean[] hasBottle) {
            if (entry == null) {
                return true;
            }
            if (entry.brewing && !entry.finished) {
                return false;
            }
            for (int i = 0; i < 3; ++i) {
                if (!entry.bottles[i].isEmpty() == hasBottle[i]) continue;
                return true;
            }
            return false;
        }

        private final void resetSync() {
            this.syncPos = null;
            this.syncRequestedAtMs = -1L;
            this.syncScreenSeenAtMs = -1L;
        }

        private final float[] project(float wx, float wy, float wz) {
            Matrix4f matrix4f = this.mvp;
            if (matrix4f == null) {
                return null;
            }
            Matrix4f currentMvp = matrix4f;
            Vec3d vec3d2 = this.cam;
            if (vec3d2 == null) {
                return null;
            }
            Vec3d currentCam = vec3d2;
            Vector4f v = currentMvp.transform(new Vector4f((float)((double)wx - currentCam.x), (float)((double)wy - currentCam.y), (float)((double)wz - currentCam.z), 1.0f));
            if (v.w <= 1.0E-4f) {
                return null;
            }
            float sx = (v.x / v.w * 0.5f + 0.5f) * this.guiW;
            float sy = (1.0f - (v.y / v.w * 0.5f + 0.5f)) * this.guiH;
            float scale = MathHelper.clamp((float)(3.5f / v.w), (float)0.35f, (float)2.0f);
            float[] fArray = new float[]{sx, sy, scale};
            return fArray;
        }

        private final ItemStack displayStack(BrewStandCache.Entry entry, int slot) {
            ItemStack preview;
            if (entry == null) {
                ItemStack itemStack2 = ItemStack.EMPTY;
                Intrinsics.checkNotNullExpressionValue((Object)itemStack2, (String)"EMPTY");
                return itemStack2;
            }
            if (this.module.getShowResult$rtx_kimiko_kimiko().getValue() && entry.brewing && !entry.finished && !(preview = BrewStandCache.previewResult(this.mc, entry, slot)).isEmpty()) {
                return preview;
            }
            ItemStack itemStack3 = entry.bottles[slot];
            Intrinsics.checkNotNullExpressionValue((Object)itemStack3, (String)"get(...)");
            return itemStack3;
        }

        private final void addSlot(float[] p, ItemStack stack, boolean unknown, float alpha) {
            if (p != null) {
                this.slots.add(new SlotDraw(p[0], p[1], p[2], stack, unknown, alpha));
            }
        }

        private final void addPolyline(List<float[]> points, float thickness, int rgb, float alpha) {
            int n = points.size();
            for (int i = 1; i < n; ++i) {
                float[] a = points.get(i - 1);
                float[] b = points.get(i);
                if (a == null || b == null) continue;
                this.lines.add(new LineDraw(a[0], a[1], b[0], b[1], thickness * (a[2] + b[2]) * 0.5f, ColorEngine.multAlpha(rgb, alpha)));
            }
        }

        private final void addRing(float bx, float y, float bz, float radius, int segments, float fillFraction, float fillThickness, float backThickness, float alpha) {
            double segStep = Math.PI * 2 / (double)segments;
            ArrayList<float[]> back = new ArrayList<float[]>(segments + 1);
            int i = 0;
            if (i <= segments) {
                while (true) {
                    double ang = -1.5707963267948966 + (double)i * segStep;
                    back.add(this.project(bx + (float)Math.cos(ang) * radius, y, bz + (float)Math.sin(ang) * radius));
                    if (i == segments) break;
                    ++i;
                }
            }
            this.addPolyline((List<float[]>)back, backThickness, -1, 0.14f * alpha);
            if (fillFraction < 0.0f) {
                return;
            }
            double fillAngle = (double)MathHelper.clamp((float)fillFraction, (float)0.0f, (float)1.0f) * Math.PI * 2.0;
            int full = (int)Math.floor(fillAngle / segStep);
            ArrayList<float[]> fill = new ArrayList<float[]>(full + 2);
            int i2 = 0;
            if (i2 <= full) {
                while (true) {
                    double ang = -1.5707963267948966 + (double)i2 * segStep;
                    fill.add(this.project(bx + (float)Math.cos(ang) * radius, y, bz + (float)Math.sin(ang) * radius));
                    if (i2 == full) break;
                    ++i2;
                }
            }
            if (fillAngle - (double)full * segStep > 0.001) {
                double ang = -1.5707963267948966 + fillAngle;
                fill.add(this.project(bx + (float)Math.cos(ang) * radius, y, bz + (float)Math.sin(ang) * radius));
            }
            if (fill.size() < 2) {
                return;
            }
            int n = fill.size();
            for (i2 = 1; i2 < n; ++i2) {
                float[] a = (float[])fill.get(i2 - 1);
                float[] b = (float[])fill.get(i2);
                if (a == null || b == null) continue;
                this.lines.add(new LineDraw(a[0], a[1], b[0], b[1], fillThickness * (a[2] + b[2]) * 0.5f, ClientAccent.accentAt(235.0f * alpha, a[0], a[1])));
            }
            float[] tip = (float[])fill.get(fill.size() - 1);
            if (tip != null) {
                this.dots.add(new DotDraw(tip[0], tip[1], 2.6f * tip[2], ClientAccent.accentBrightAt(255.0f * alpha, tip[0], tip[1])));
            }
        }

        private final void addThreads(float ix, float iy, float iz, float sx, float sy, float sz, float alpha) {
            ArrayList<float[]> pts = new ArrayList<float[]>(7);
            for (int s = 0; s < 7; ++s) {
                float f = (float)s / 6.0f;
                float lift = (float)Math.sin((double)f * Math.PI) * 0.18f;
                pts.add(this.project(MathHelper.lerp((float)f, (float)ix, (float)sx), MathHelper.lerp((float)f, (float)iy, (float)sy) + lift, MathHelper.lerp((float)f, (float)iz, (float)sz)));
            }
            int n = pts.size();
            for (int i = 1; i < n; ++i) {
                float[] a = (float[])pts.get(i - 1);
                float[] b = (float[])pts.get(i);
                if (a == null || b == null) continue;
                this.lines.add(new LineDraw(a[0], a[1], b[0], b[1], 0.7f * (a[2] + b[2]) * 0.5f, ClientAccent.accentSoftAt(120.0f * alpha, a[0], a[1])));
            }
        }

        private final void buildScene(float bx, float by, float bz, float t, BrewStandCache.Entry entry, float progress, boolean[] hasBottle) {
            float radius = 1.05f * t;
            float baseY = by + 0.45f;
            float zenithY = baseY + radius;
            float ingY = zenithY + 0.16f + (float)Math.sin((double)System.currentTimeMillis() / 420.0) * 0.04f;
            float[] fArray = new float[]{24.0f, 48.0f, 70.0f};
            for (float lat : fArray) {
                double rad = Math.toRadians(lat);
                float r = radius * (float)Math.cos(rad);
                float h = baseY + radius * (float)Math.sin(rad);
                ArrayList<float[]> pts = new ArrayList<float[]>(37);
                for (int i = 0; i < 37; ++i) {
                    double ang = (double)i * 0.17453292519943295;
                    pts.add(this.project(bx + (float)Math.cos(ang) * r, h, bz + (float)Math.sin(ang) * r));
                }
                this.addPolyline((List<float[]>)pts, 0.5f, ClientAccent.accentSoft(255.0f), 0.13f * t);
            }
            for (int m = 0; m < 4; ++m) {
                double base = (double)m * 1.5707963267948966 + (double)this.spin * 0.2;
                ArrayList<float[]> pts = new ArrayList<float[]>(13);
                for (int i = 0; i < 13; ++i) {
                    double rad = Math.toRadians((double)i * 7.333333333333333);
                    float r = radius * (float)Math.cos(rad);
                    float h = baseY + radius * (float)Math.sin(rad);
                    pts.add(this.project(bx + (float)Math.cos(base) * r, h, bz + (float)Math.sin(base) * r));
                }
                this.addPolyline((List<float[]>)pts, 0.5f, ClientAccent.accentSoft(255.0f), 0.1f * t);
            }
            float orbitR = 0.8f * t;
            float slotY = by + 0.95f;
            for (int i = 0; i < 3; ++i) {
                double ang = (double)this.spin + (double)i * 2.0943951023931953;
                float wx = bx + (float)Math.cos(ang) * orbitR;
                float wz = bz + (float)Math.sin(ang) * orbitR;
                ItemStack stack = this.displayStack(entry, i);
                boolean unknown = entry == null && hasBottle[i];
                float slotAlpha = entry != null && !entry.bottles[i].isEmpty() || unknown ? 1.0f : 0.4f;
                this.addSlot(this.project(wx, slotY, wz), stack, unknown, slotAlpha * t);
                if (entry == null || !entry.brewing || entry.finished || entry.bottles[i].isEmpty()) continue;
                this.addThreads(bx, ingY, bz, wx, slotY, wz, t);
            }
            if (entry != null && !entry.ingredient.isEmpty()) {
                this.addSlot(this.project(bx, ingY, bz), entry.ingredient, false, t);
            }
            if (entry != null && entry.brewing && !entry.finished) {
                long now = System.currentTimeMillis();
                int active = Math.max(1, Math.round(MathHelper.clamp((float)progress, (float)0.0f, (float)1.0f) * 10.0f));
                for (int i = 0; i < active; ++i) {
                    float phase = (float)(((double)now / 2400.0 + (double)i * 0.37) % 1.0);
                    double ang = (double)phase * 7.0 + (double)i * 2.4;
                    float r = 0.55f * (1.0f - phase * 0.8f) * t;
                    float wy = by + 0.5f + phase * 0.95f;
                    float[] p = this.project(bx + (float)Math.cos(ang) * r, wy, bz + (float)Math.sin(ang) * r);
                    if (p == null) continue;
                    this.dots.add(new DotDraw(p[0], p[1], (1.2f + (1.0f - phase) * 1.4f) * p[2], ClientAccent.accentAt(180.0f * (1.0f - phase) * t, p[0], p[1])));
                }
            }
            this.addRing(bx, baseY, bz, radius, 40, progress, 1.3f, 0.6f, t);
            this.addStatusText(bx, zenithY + 0.55f, bz, entry, progress, t);
        }

        private final void addStatusText(float bx, float wy, float bz, BrewStandCache.Entry entry, float progress, float alpha) {
            float[] fArray = this.project(bx, wy, bz);
            if (fArray == null) {
                return;
            }
            float[] p = fArray;
            Object label = null;
            boolean accent = false;
            if (entry == null) {
                label = I18n.tr("Синхронизация...");
            } else if (entry.finished) {
                label = I18n.tr("Готово");
                accent = true;
            } else if (progress >= 0.0f) {
                label = Math.round(progress * 100.0f) + "%";
                if (this.module.getShowTimer$rtx_kimiko_kimiko().getValue()) {
                    Object[] objectArray = new Object[]{Float.valueOf(BrewStandCache.secondsLeft(this.mc, entry))};
                    label = (String)label + " \u00b7 " + I18n.tr("%.1fс", objectArray);
                }
                accent = true;
            } else {
                label = I18n.tr("Ожидание");
            }
            this.texts.add(new TextDraw((String)label, p[0], p[1], 6.2f * p[2], ColorEngine.multAlpha(-1, 0.9f * alpha), accent));
        }

        @EventHandler
        private final void onHudRender(HudRenderEvent event) {
            if (!this.sceneReady) {
                this.clearScene();
                return;
            }
            DrawContext graphics = event.getGraphics();
            if (graphics == null) {
                this.clearScene();
                return;
            }
            Render2D.beginFrame(graphics);
            for (LineDraw line : this.lines) {
                Render2D.line(line.getX1(), line.getY1(), line.getX2(), line.getY2(), line.getThickness(), line.getColor());
            }
            for (DotDraw dot : this.dots) {
                Render2D.rect(dot.getX() - dot.getSize() * 0.5f, dot.getY() - dot.getSize() * 0.5f, dot.getSize(), dot.getSize(), dot.getSize() * 0.5f, dot.getColor());
            }
            for (SlotDraw slot : this.slots) {
                float w = 19.0f * slot.getScale();
                float h = 22.0f * slot.getScale();
                RectUtil.drawClientRectFixedRadius(slot.getX() - w * 0.5f, slot.getY() - h * 0.5f, w, h, 6.0f * slot.getScale(), slot.getAlpha(), 0.0f);
                Render2D.outline(slot.getX() - w * 0.5f, slot.getY() - h * 0.5f, w, h, 6.0f * slot.getScale(), 0.6f, ColorEngine.multAlpha(0x22FFFFFF, slot.getAlpha()));
                if (!slot.getUnknown()) continue;
                float qs = 8.0f * slot.getScale();
                float qw = Fonts.MEDIUM.width("?", qs);
                Fonts.MEDIUM.draw("?", slot.getX() - qw * 0.5f, slot.getY() - qs * 0.55f, qs, ColorEngine.multAlpha(-1, 0.65f * slot.getScale()));
            }
            for (TextDraw text : this.texts) {
                float tw = Fonts.MEDIUM.width(text.getText(), text.getSize());
                int color = text.getAccent() ? ClientAccent.accentBrightAt(255.0f * ((float)(text.getColor() >>> 24) / 255.0f), text.getX(), text.getY()) : text.getColor();
                Fonts.MEDIUM.draw(text.getText(), text.getX() - tw * 0.5f, text.getY() - text.getSize() * 0.5f, text.getSize(), color);
            }
            Render2D.flush();
            for (SlotDraw slot : this.slots) {
                if (slot.getUnknown() || slot.getStack().isEmpty() || !(slot.getAlpha() > 0.35f)) continue;
                this.drawItem(graphics, slot.getStack(), slot.getX(), slot.getY(), 14.0f * slot.getScale());
            }
            this.clearScene();
        }

        private final void drawItem(DrawContext graphics, ItemStack stack, float cx, float cy, float sizePx) {
            float conv = Render2DCoordinateSpace.guiIndependentScale();
            graphics.getMatrices().pushMatrix();
            graphics.getMatrices().translate(cx * conv, cy * conv);
            float s = sizePx * conv / 16.0f;
            graphics.getMatrices().scale(s, s);
            graphics.drawItem(stack, -8, -8);
            graphics.getMatrices().popMatrix();
        }

        private final void clearScene() {
            this.slots.clear();
            this.lines.clear();
            this.dots.clear();
            this.texts.clear();
            this.sceneReady = false;
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\nR\u0014\u0010\f\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\nR\u0014\u0010\r\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\n\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewViewer$Overlay.Companion;", "", "<init>", "()V", "", "DOME_RADIUS", "F", "ORBIT_RADIUS", "", "SYNC_COOLDOWN_MS", "J", "SYNC_OPEN_TIMEOUT_MS", "SYNC_HOLD_MS", "HIDE_DELAY_MS", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\u000bJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000bJ\u0010\u0010\u000e\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ8\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00c6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u0016\u001a\u00020\u0006H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0016\u0010\u000fJ\u0011\u0010\u0018\u001a\u00020\u0017H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\u000bR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001a\u001a\u0004\b\u001c\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001d\u0010\u000bR\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b\u001f\u0010\u000f\u00a8\u0006 "}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewViewer$Overlay$DotDraw;", "", "", "x", "y", "size", "", "color", "<init>", "(FFFI)V", "component1", "()F", "component2", "component3", "component4", "()I", "copy", "(FFFI)Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewViewer$Overlay$DotDraw;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "F", "getX", "getY", "getSize", "I", "getColor", "rtx.kimiko:kimiko"})
        private static final class DotDraw {
            private final float x;
            private final float y;
            private final float size;
            private final int color;

            public DotDraw(float x, float y, float size, int color) {
                this.x = x;
                this.y = y;
                this.size = size;
                this.color = color;
            }

            public final float getX() {
                return this.x;
            }

            public final float getY() {
                return this.y;
            }

            public final float getSize() {
                return this.size;
            }

            public final int getColor() {
                return this.color;
            }

            public final float component1() {
                return this.x;
            }

            public final float component2() {
                return this.y;
            }

            public final float component3() {
                return this.size;
            }

            public final int component4() {
                return this.color;
            }

            @NotNull
            public final DotDraw copy(float x, float y, float size, int color) {
                return new DotDraw(x, y, size, color);
            }

            public static /* synthetic */ DotDraw copy$default(DotDraw dotDraw, float f, float f2, float f3, int n, int n2, Object object) {
                if ((n2 & 1) != 0) {
                    f = dotDraw.x;
                }
                if ((n2 & 2) != 0) {
                    f2 = dotDraw.y;
                }
                if ((n2 & 4) != 0) {
                    f3 = dotDraw.size;
                }
                if ((n2 & 8) != 0) {
                    n = dotDraw.color;
                }
                return dotDraw.copy(f, f2, f3, n);
            }

            @NotNull
            public String toString() {
                return "DotDraw(x=" + this.x + ", y=" + this.y + ", size=" + this.size + ", color=" + this.color + ")";
            }

            public int hashCode() {
                int result = Float.hashCode(this.x);
                result = result * 31 + Float.hashCode(this.y);
                result = result * 31 + Float.hashCode(this.size);
                result = result * 31 + Integer.hashCode(this.color);
                return result;
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof DotDraw)) {
                    return false;
                }
                DotDraw dotDraw = (DotDraw)other;
                if (Float.compare(this.x, dotDraw.x) != 0) {
                    return false;
                }
                if (Float.compare(this.y, dotDraw.y) != 0) {
                    return false;
                }
                if (Float.compare(this.size, dotDraw.size) != 0) {
                    return false;
                }
                return this.color == dotDraw.color;
            }
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0082\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\rJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\rJ\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\rJ\u0010\u0010\u0012\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0013JL\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\bH\u00c6\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0011\u0010\u001a\u001a\u00020\bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001a\u0010\u0013J\u0011\u0010\u001c\u001a\u00020\u001bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010\rR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001e\u001a\u0004\b \u0010\rR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b!\u0010\rR\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b\"\u0010\rR\u0017\u0010\u0007\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b#\u0010\rR\u0017\u0010\t\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u0010$\u001a\u0004\b%\u0010\u0013\u00a8\u0006&"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewViewer$Overlay$LineDraw;", "", "", "x1", "y1", "x2", "y2", "thickness", "", "color", "<init>", "(FFFFFI)V", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "()I", "copy", "(FFFFFI)Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewViewer$Overlay$LineDraw;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "F", "getX1", "getY1", "getX2", "getY2", "getThickness", "I", "getColor", "rtx.kimiko:kimiko"})
        private static final class LineDraw {
            private final float x1;
            private final float y1;
            private final float x2;
            private final float y2;
            private final float thickness;
            private final int color;

            public LineDraw(float x1, float y1, float x2, float y2, float thickness, int color) {
                this.x1 = x1;
                this.y1 = y1;
                this.x2 = x2;
                this.y2 = y2;
                this.thickness = thickness;
                this.color = color;
            }

            public final float getX1() {
                return this.x1;
            }

            public final float getY1() {
                return this.y1;
            }

            public final float getX2() {
                return this.x2;
            }

            public final float getY2() {
                return this.y2;
            }

            public final float getThickness() {
                return this.thickness;
            }

            public final int getColor() {
                return this.color;
            }

            public final float component1() {
                return this.x1;
            }

            public final float component2() {
                return this.y1;
            }

            public final float component3() {
                return this.x2;
            }

            public final float component4() {
                return this.y2;
            }

            public final float component5() {
                return this.thickness;
            }

            public final int component6() {
                return this.color;
            }

            @NotNull
            public final LineDraw copy(float x1, float y1, float x2, float y2, float thickness, int color) {
                return new LineDraw(x1, y1, x2, y2, thickness, color);
            }

            public static /* synthetic */ LineDraw copy$default(LineDraw lineDraw, float f, float f2, float f3, float f4, float f5, int n, int n2, Object object) {
                if ((n2 & 1) != 0) {
                    f = lineDraw.x1;
                }
                if ((n2 & 2) != 0) {
                    f2 = lineDraw.y1;
                }
                if ((n2 & 4) != 0) {
                    f3 = lineDraw.x2;
                }
                if ((n2 & 8) != 0) {
                    f4 = lineDraw.y2;
                }
                if ((n2 & 0x10) != 0) {
                    f5 = lineDraw.thickness;
                }
                if ((n2 & 0x20) != 0) {
                    n = lineDraw.color;
                }
                return lineDraw.copy(f, f2, f3, f4, f5, n);
            }

            @NotNull
            public String toString() {
                return "LineDraw(x1=" + this.x1 + ", y1=" + this.y1 + ", x2=" + this.x2 + ", y2=" + this.y2 + ", thickness=" + this.thickness + ", color=" + this.color + ")";
            }

            public int hashCode() {
                int result = Float.hashCode(this.x1);
                result = result * 31 + Float.hashCode(this.y1);
                result = result * 31 + Float.hashCode(this.x2);
                result = result * 31 + Float.hashCode(this.y2);
                result = result * 31 + Float.hashCode(this.thickness);
                result = result * 31 + Integer.hashCode(this.color);
                return result;
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof LineDraw)) {
                    return false;
                }
                LineDraw lineDraw = (LineDraw)other;
                if (Float.compare(this.x1, lineDraw.x1) != 0) {
                    return false;
                }
                if (Float.compare(this.y1, lineDraw.y1) != 0) {
                    return false;
                }
                if (Float.compare(this.x2, lineDraw.x2) != 0) {
                    return false;
                }
                if (Float.compare(this.y2, lineDraw.y2) != 0) {
                    return false;
                }
                if (Float.compare(this.thickness, lineDraw.thickness) != 0) {
                    return false;
                }
                return this.color == lineDraw.color;
            }
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\b\u0082\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u000eJ\u0010\u0010\u0011\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u000eJL\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u0019\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0011\u0010\u001c\u001a\u00020\u001bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0011\u0010\u001f\u001a\u00020\u001eH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001f\u0010 R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010!\u001a\u0004\b\"\u0010\u000eR\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010!\u001a\u0004\b#\u0010\u000eR\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010!\u001a\u0004\b$\u0010\u000eR\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010%\u001a\u0004\b&\u0010\u0012R\u0017\u0010\t\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u0010'\u001a\u0004\b(\u0010\u0014R\u0017\u0010\n\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\n\u0010!\u001a\u0004\b)\u0010\u000e\u00a8\u0006*"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewViewer$Overlay$SlotDraw;", "", "", "x", "y", "scale", "Lnet/minecraft/ItemStack;", "stack", "", "unknown", "alpha", "<init>", "(FFFLnet/minecraft/ItemStack;ZF)V", "component1", "()F", "component2", "component3", "component4", "()Lnet/minecraft/ItemStack;", "component5", "()Z", "component6", "copy", "(FFFLnet/minecraft/ItemStack;ZF)Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewViewer$Overlay$SlotDraw;", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "F", "getX", "getY", "getScale", "Lnet/minecraft/ItemStack;", "getStack", "Z", "getUnknown", "getAlpha", "rtx.kimiko:kimiko"})
        private static final class SlotDraw {
            private final float x;
            private final float y;
            private final float scale;
            @NotNull
            private final ItemStack stack;
            private final boolean unknown;
            private final float alpha;

            public SlotDraw(float x, float y, float scale, @NotNull ItemStack stack, boolean unknown, float alpha) {
                Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
                this.x = x;
                this.y = y;
                this.scale = scale;
                this.stack = stack;
                this.unknown = unknown;
                this.alpha = alpha;
            }

            public final float getX() {
                return this.x;
            }

            public final float getY() {
                return this.y;
            }

            public final float getScale() {
                return this.scale;
            }

            @NotNull
            public final ItemStack getStack() {
                return this.stack;
            }

            public final boolean getUnknown() {
                return this.unknown;
            }

            public final float getAlpha() {
                return this.alpha;
            }

            public final float component1() {
                return this.x;
            }

            public final float component2() {
                return this.y;
            }

            public final float component3() {
                return this.scale;
            }

            @NotNull
            public final ItemStack component4() {
                return this.stack;
            }

            public final boolean component5() {
                return this.unknown;
            }

            public final float component6() {
                return this.alpha;
            }

            @NotNull
            public final SlotDraw copy(float x, float y, float scale, @NotNull ItemStack stack, boolean unknown, float alpha) {
                Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
                return new SlotDraw(x, y, scale, stack, unknown, alpha);
            }

            public static /* synthetic */ SlotDraw copy$default(SlotDraw slotDraw, float f, float f2, float f3, ItemStack itemStack2, boolean bl, float f4, int n, Object object) {
                if ((n & 1) != 0) {
                    f = slotDraw.x;
                }
                if ((n & 2) != 0) {
                    f2 = slotDraw.y;
                }
                if ((n & 4) != 0) {
                    f3 = slotDraw.scale;
                }
                if ((n & 8) != 0) {
                    itemStack2 = slotDraw.stack;
                }
                if ((n & 0x10) != 0) {
                    bl = slotDraw.unknown;
                }
                if ((n & 0x20) != 0) {
                    f4 = slotDraw.alpha;
                }
                return slotDraw.copy(f, f2, f3, itemStack2, bl, f4);
            }

            @NotNull
            public String toString() {
                return "SlotDraw(x=" + this.x + ", y=" + this.y + ", scale=" + this.scale + ", stack=" + this.stack + ", unknown=" + this.unknown + ", alpha=" + this.alpha + ")";
            }

            public int hashCode() {
                int result = Float.hashCode(this.x);
                result = result * 31 + Float.hashCode(this.y);
                result = result * 31 + Float.hashCode(this.scale);
                result = result * 31 + this.stack.hashCode();
                result = result * 31 + Boolean.hashCode(this.unknown);
                result = result * 31 + Float.hashCode(this.alpha);
                return result;
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof SlotDraw)) {
                    return false;
                }
                SlotDraw slotDraw = (SlotDraw)other;
                if (Float.compare(this.x, slotDraw.x) != 0) {
                    return false;
                }
                if (Float.compare(this.y, slotDraw.y) != 0) {
                    return false;
                }
                if (Float.compare(this.scale, slotDraw.scale) != 0) {
                    return false;
                }
                if (!Intrinsics.areEqual((Object)this.stack, (Object)slotDraw.stack)) {
                    return false;
                }
                if (this.unknown != slotDraw.unknown) {
                    return false;
                }
                return Float.compare(this.alpha, slotDraw.alpha) == 0;
            }
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001f\b\u0082\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0011J\u0010\u0010\u0014\u001a\u00020\bH\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\nH\u00c6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0017JL\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\nH\u00c6\u0001\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u001b\u0010\u001b\u001a\u00020\n2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0011\u0010\u001d\u001a\u00020\bH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001d\u0010\u0015J\u0011\u0010\u001e\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001f\u001a\u0004\b \u0010\u000fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010!\u001a\u0004\b\"\u0010\u0011R\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010!\u001a\u0004\b#\u0010\u0011R\u0017\u0010\u0007\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010!\u001a\u0004\b$\u0010\u0011R\u0017\u0010\t\u001a\u00020\b8\u0006\u00a2\u0006\f\n\u0004\b\t\u0010%\u001a\u0004\b&\u0010\u0015R\u0017\u0010\u000b\u001a\u00020\n8\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010'\u001a\u0004\b(\u0010\u0017\u00a8\u0006)"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewViewer$Overlay$TextDraw;", "", "", "text", "", "x", "y", "size", "", "color", "", "accent", "<init>", "(Ljava/lang/String;FFFIZ)V", "component1", "()Ljava/lang/String;", "component2", "()F", "component3", "component4", "component5", "()I", "component6", "()Z", "copy", "(Ljava/lang/String;FFFIZ)Lrtx/kimiko/api/modules/impl/Visuals/brewviewer/BrewViewer$Overlay$TextDraw;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "Ljava/lang/String;", "getText", "F", "getX", "getY", "getSize", "I", "getColor", "Z", "getAccent", "rtx.kimiko:kimiko"})
        private static final class TextDraw {
            @NotNull
            private final String text;
            private final float x;
            private final float y;
            private final float size;
            private final int color;
            private final boolean accent;

            public TextDraw(@NotNull String text, float x, float y, float size, int color, boolean accent) {
                Intrinsics.checkNotNullParameter((Object)text, (String)"text");
                this.text = text;
                this.x = x;
                this.y = y;
                this.size = size;
                this.color = color;
                this.accent = accent;
            }

            @NotNull
            public final String getText() {
                return this.text;
            }

            public final float getX() {
                return this.x;
            }

            public final float getY() {
                return this.y;
            }

            public final float getSize() {
                return this.size;
            }

            public final int getColor() {
                return this.color;
            }

            public final boolean getAccent() {
                return this.accent;
            }

            @NotNull
            public final String component1() {
                return this.text;
            }

            public final float component2() {
                return this.x;
            }

            public final float component3() {
                return this.y;
            }

            public final float component4() {
                return this.size;
            }

            public final int component5() {
                return this.color;
            }

            public final boolean component6() {
                return this.accent;
            }

            @NotNull
            public final TextDraw copy(@NotNull String text, float x, float y, float size, int color, boolean accent) {
                Intrinsics.checkNotNullParameter((Object)text, (String)"text");
                return new TextDraw(text, x, y, size, color, accent);
            }

            public static /* synthetic */ TextDraw copy$default(TextDraw textDraw, String string, float f, float f2, float f3, int n, boolean bl, int n2, Object object) {
                if ((n2 & 1) != 0) {
                    string = textDraw.text;
                }
                if ((n2 & 2) != 0) {
                    f = textDraw.x;
                }
                if ((n2 & 4) != 0) {
                    f2 = textDraw.y;
                }
                if ((n2 & 8) != 0) {
                    f3 = textDraw.size;
                }
                if ((n2 & 0x10) != 0) {
                    n = textDraw.color;
                }
                if ((n2 & 0x20) != 0) {
                    bl = textDraw.accent;
                }
                return textDraw.copy(string, f, f2, f3, n, bl);
            }

            @NotNull
            public String toString() {
                return "TextDraw(text=" + this.text + ", x=" + this.x + ", y=" + this.y + ", size=" + this.size + ", color=" + this.color + ", accent=" + this.accent + ")";
            }

            public int hashCode() {
                int result = this.text.hashCode();
                result = result * 31 + Float.hashCode(this.x);
                result = result * 31 + Float.hashCode(this.y);
                result = result * 31 + Float.hashCode(this.size);
                result = result * 31 + Integer.hashCode(this.color);
                result = result * 31 + Boolean.hashCode(this.accent);
                return result;
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof TextDraw)) {
                    return false;
                }
                TextDraw textDraw = (TextDraw)other;
                if (!Intrinsics.areEqual((Object)this.text, (Object)textDraw.text)) {
                    return false;
                }
                if (Float.compare(this.x, textDraw.x) != 0) {
                    return false;
                }
                if (Float.compare(this.y, textDraw.y) != 0) {
                    return false;
                }
                if (Float.compare(this.size, textDraw.size) != 0) {
                    return false;
                }
                if (this.color != textDraw.color) {
                    return false;
                }
                return this.accent == textDraw.accent;
            }
        }
    }
}

