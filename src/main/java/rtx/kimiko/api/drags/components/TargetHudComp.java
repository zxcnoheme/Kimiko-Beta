/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.math.MathKt
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.util.Formatting
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EquipmentSlot
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.consume.UseAction
 *  net.minecraft.util.hit.HitResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.text.Text
 *  net.minecraft.text.Style
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.font.TextRenderer
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.util.hit.EntityHitResult
 *  net.minecraft.text.TextVisitFactory
 *  net.minecraft.text.MutableText
 *  net.minecraft.text.TextColor
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 *  org.joml.Vector4f
 */
package rtx.kimiko.api.drags.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.util.Formatting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.text.Text;
import net.minecraft.text.Style;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.text.TextVisitFactory;
import net.minecraft.text.MutableText;
import net.minecraft.text.TextColor;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import org.joml.Vector4f;
import rtx.kimiko.api.drags.DragSystem;
import rtx.kimiko.api.drags.Draggable;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.impl.Interface.TargetHudModule;
import rtx.kimiko.api.modules.impl.Visuals.NameTags;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.animations.HudFadeAnimation;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.entity.EntityVisibility;
import rtx.kimiko.utils.network.Network;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.modules.rank.ReallyWorldRanks;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.ClientPalette;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.render2d.arcrect.BuiltArcRect;
import rtx.kimiko.utils.render.render2d.outline.outline360.Outline360Range;
import rtx.kimiko.utils.render.render2d.rectangle.rectdefault.BuiltRectangle;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u0014\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 |2\u00020\u0001:\u0002}|B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\n\u0010\tJ\u000f\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0014\u00a2\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J'\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0016J'\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001cH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010!\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b!\u0010\"J/\u0010&\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u00072\u0006\u0010%\u001a\u00020$H\u0002\u00a2\u0006\u0004\b&\u0010'J'\u0010(\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b(\u0010\u0016J\u001f\u0010)\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0013\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b)\u0010*J'\u0010,\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b,\u0010\u0016J\u000f\u0010-\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b-\u0010\u0003J\u001b\u00101\u001a\u00020\u00102\u0006\u0010/\u001a\u00020.H\u0003b\u0002\b0\u00a2\u0006\u0004\b1\u00102J\u0017\u00103\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b3\u00104JO\u00109\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u00105\u001a\u00020\u00072\u0006\u00106\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u00072\u0006\u00107\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u00108\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b9\u0010:JG\u0010;\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u00105\u001a\u00020\u00072\u0006\u00106\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u00072\u0006\u00107\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b;\u0010<J?\u0010?\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010=\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010>\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b?\u0010@J'\u0010A\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\bA\u0010BJ/\u0010E\u001a\u00020\u00102\u0006\u00105\u001a\u00020\u00072\u0006\u00106\u001a\u00020\u00072\u0006\u0010C\u001a\u00020\u00072\u0006\u0010D\u001a\u00020$H\u0002\u00a2\u0006\u0004\bE\u0010'J\u001f\u0010F\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\bF\u0010GR\u0014\u0010I\u001a\u00020H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u0010JR$\u0010N\u001a\u0012\u0012\u0004\u0012\u00020L0Kj\b\u0012\u0004\u0012\u00020L`M8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bN\u0010OR\u0014\u0010Q\u001a\u00020P8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bQ\u0010RR\u0018\u0010S\u001a\u0004\u0018\u00010\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bS\u0010TR\u0016\u0010V\u001a\u00020U8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010WR\u0016\u0010X\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bX\u0010YR\u0016\u0010Z\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bZ\u0010YR\u0016\u0010[\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b[\u0010\\R\u0016\u0010]\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b]\u0010\\R\u0016\u0010^\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b^\u0010YR\u0016\u0010_\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b_\u0010YR\u0016\u0010`\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b`\u0010YR\u0016\u0010a\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\ba\u0010YR\u0016\u0010b\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bb\u0010YR\u0014\u0010d\u001a\u00020c8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bd\u0010eR\u0016\u0010f\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bf\u0010YR\u0016\u0010g\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bg\u0010YR\u0016\u0010h\u001a\u00020U8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bh\u0010WR\u0016\u0010i\u001a\u00020$8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bi\u0010jR\u0016\u0010k\u001a\u00020$8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bk\u0010jR\u0016\u0010l\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bl\u0010YR\u0016\u0010m\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bm\u0010YR\u0016\u0010o\u001a\u00020n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bo\u0010pR\u0016\u0010q\u001a\u00020$8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bq\u0010jR\u0014\u0010s\u001a\u00020r8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bs\u0010tR\u0016\u0010u\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bu\u0010YR\u0016\u0010v\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bv\u0010YR\u0016\u0010w\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bw\u0010YR\u0016\u0010x\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bx\u0010yR\u0016\u0010z\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bz\u0010YR\u0016\u0010{\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b{\u0010Y\u00a8\u0006~"}, d2={"Lrtx/kimiko/api/drags/components/TargetHudComp;", "Lrtx/kimiko/api/drags/Draggable;", "<init>", "()V", "", "displayName", "()Ljava/lang/String;", "", "width", "()F", "height", "", "isInteractive", "()Z", "Lnet/minecraft/DrawContext;", "graphics", "", "render", "(Lnet/minecraft/DrawContext;)V", "dt", "alpha", "renderContent", "(Lnet/minecraft/DrawContext;FF)V", "renderCircle", "centerX", "centerY", "drawHealthRing", "(FFF)V", "Lnet/minecraft/LivingEntity;", "entity", "resetHealthTracking", "(Lnet/minecraft/LivingEntity;)V", "health", "updateHpCounter", "(FF)V", "size", "", "color", "drawHpCounter", "(FFFI)V", "renderNew", "updateUseIndicator", "(Lnet/minecraft/LivingEntity;F)V", "panelH", "renderUseIndicator", "pinCenterOnResize", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onWorldRender", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "updateFollow", "(F)V", "x", "y", "radius", "questionLift", "drawHeadBackground", "(Lnet/minecraft/DrawContext;Lnet/minecraft/LivingEntity;FFFFFF)V", "drawHeadForeground", "(Lnet/minecraft/DrawContext;Lnet/minecraft/LivingEntity;FFFFF)V", "topY", "progress", "drawArmorCentered", "(Lnet/minecraft/DrawContext;Lnet/minecraft/LivingEntity;FFFF)V", "updateHitParticles", "(Lnet/minecraft/LivingEntity;FF)V", "maxDistance", "count", "spawnHeadParticles", "drawHitParticles", "(Lnet/minecraft/DrawContext;F)V", "Lrtx/kimiko/utils/animations/HudFadeAnimation;", "visibility", "Lrtx/kimiko/utils/animations/HudFadeAnimation;", "Ljava/util/ArrayList;", "Lrtx/kimiko/api/drags/components/TargetHudComp$HeadParticle;", "Lkotlin/collections/ArrayList;", "headParticles", "Ljava/util/ArrayList;", "Ljava/util/Random;", "random", "Ljava/util/Random;", "target", "Lnet/minecraft/LivingEntity;", "", "lastSeenMs", "J", "healthDisplay", "F", "hpMaxRef", "hpText", "Ljava/lang/String;", "hpPrevText", "hpRoll", "hpRollDir", "hpValue", "hpGrayDisplay", "armorProgress", "", "slotAppear", "[F", "currentWidth", "lastPinnedWidth", "lastNs", "particleEntityId", "I", "lastParticleHurtTime", "useSlide", "useArcDisplay", "Lnet/minecraft/ItemStack;", "lastUseItem", "Lnet/minecraft/ItemStack;", "useEntityId", "Lorg/joml/Vector4f;", "followScratch", "Lorg/joml/Vector4f;", "followScreenX", "followScreenY", "followHalfH", "followProjectionValid", "Z", "followOffsetX", "followOffsetY", "Companion", "HeadParticle", "rtx.kimiko:kimiko"})
public final class TargetHudComp
extends Draggable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final HudFadeAnimation visibility = this.getHudFade();
    @NotNull
    private final ArrayList<HeadParticle> headParticles = new ArrayList();
    @NotNull
    private final Random random = new Random();
    @Nullable
    private LivingEntity target;
    private long lastSeenMs;
    private float healthDisplay;
    private float hpMaxRef;
    @NotNull
    private String hpText = "";
    @NotNull
    private String hpPrevText = "";
    private float hpRoll = 1.0f;
    private float hpRollDir = -1.0f;
    private float hpValue = -1.0f;
    private float hpGrayDisplay = 1.0f;
    private float armorProgress = 1.0f;
    @NotNull
    private final float[] slotAppear = new float[NEW_SLOTS.length];
    private float currentWidth = 120.0f;
    private float lastPinnedWidth = Float.NaN;
    private long lastNs = System.nanoTime();
    private int particleEntityId = Integer.MIN_VALUE;
    private int lastParticleHurtTime = -1;
    private float useSlide;
    private float useArcDisplay;
    @NotNull
    private ItemStack lastUseItem;
    private int useEntityId;
    @NotNull
    private final Vector4f followScratch;
    private float followScreenX;
    private float followScreenY;
    private float followHalfH;
    private boolean followProjectionValid;
    private float followOffsetX;
    private float followOffsetY;
    private static final float PAD = 6.0f;
    private static final float HEAD = 22.0f;
    private static final float HAT_SCALE = 1.18f;
    private static final float GAP = 7.0f;
    private static final float BAR_H = 3.0f;
    private static final float NAME_SIZE = 8.0f;
    private static final float INFO_SIZE = 6.0f;
    private static final float RADIUS = 7.0f;
    private static final float MIN_CONTENT_W = 74.0f;
    private static final float HEIGHT = 34.0f;
    private static final long HOLD_MS = 1500L;
    private static final int NAME_COLOR = -1;
    private static final int INFO_COLOR = -3618608;
    private static final int BAR_BG_COLOR = -14935006;
    private static final int BAR_BG_ALPHA = 36;
    private static final float CIRCLE_HEAD = 24.0f;
    private static final float CIRCLE_HEIGHT = 36.0f;
    private static final float CIRCLE_MIN_CONTENT = 62.0f;
    private static final float CIRCLE_TEXT_GAP = 2.5f;
    private static final float CIRCLE_RING_RADIUS = 10.5f;
    private static final float CIRCLE_RING_THICKNESS = 2.2f;
    private static final float CIRCLE_HP_SIZE = 9.0f;
    private static final float HP_ROLL_SECONDS = 0.22f;
    private static final float NEW_PAD = 3.0f;
    private static final float NEW_GAP = 3.0f;
    private static final float NEW_HEAD = 15.0f;
    private static final float NEW_HEAD_RADIUS = 6.0f;
    private static final float NEW_ICON = 8.0f;
    private static final float NEW_ICON_GAP = 3.0f;
    private static final float NEW_HEAD_ITEMS_GAP = 3.0f;
    private static final float NEW_BAR_H = 8.0f;
    private static final float NEW_BAR_RADIUS = 2.0f;
    private static final float NEW_MIN_CONTENT = 78.0f;
    private static final float NEW_TEXT_EDGE_PAD = 2.0f;
    private static final float NEW_BOTTOM_PAD = 5.0f;
    private static final float NEW_HEIGHT = 45.0f;
    @NotNull
    private static final EquipmentSlot[] NEW_SLOTS;
    @NotNull
    private static final EquipmentSlot[] ARMOR_SLOTS;
    private static final float ARMOR_SCALE = 0.5f;
    private static final float ARMOR_ICON = 8.0f;
    private static final float ARMOR_GAP = 3.0f;
    private static final float CLASSIC_ARMOR_EXTRA = 11.0f;
    private static final float NEW_ARMOR_COLLAPSE = 10.0f;
    private static final float USE_SIZE = 24.0f;
    private static final float USE_GAP = 4.0f;
    private static final float USE_ICON = 16.0f;
    private static final float USE_RING_THICK = 1.6f;
    private static final int PARTICLE_COUNT = 12;
    private static final float PARTICLE_MOVE_SPEED = 36.0f;
    private static final float PARTICLE_LIFETIME_JITTER = 0.2f;
    private static final float PARTICLE_FADE_IN = 0.15f;
    private static final int HP_GREEN = 3530826;
    private static final int HP_ORANGE = 16751136;
    private static final int HP_RED = 16722480;

    public TargetHudComp() {
        super("targethud", 5.0f, 150.0f);
        ItemStack itemStack2 = ItemStack.EMPTY;
        Intrinsics.checkNotNullExpressionValue((Object)itemStack2, (String)"EMPTY");
        this.lastUseItem = itemStack2;
        this.useEntityId = Integer.MIN_VALUE;
        this.followScratch = new Vector4f();
        this.visibility.set(0.0);
        EventBus.Companion.get().subscribe(this);
    }

    @Override
    @NotNull
    public String displayName() {
        return "Target HUD";
    }

    @Override
    public float width() {
        return this.currentWidth;
    }

    @Override
    public float height() {
        if (TargetHudComp.Companion.isCircleMode()) {
            return 36.0f;
        }
        if (TargetHudComp.Companion.isNewMode()) {
            return 45.0f - 10.0f * (1.0f - TargetHudComp.Companion.easeInOutCubic(this.armorProgress));
        }
        return 34.0f + 11.0f * TargetHudComp.Companion.easeInOutCubic(this.armorProgress);
    }

    @Override
    public boolean isInteractive() {
        return TargetHudComp.Companion.componentEnabled();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected void render(@NotNull DrawContext graphics) {
        boolean followShift;
        LivingEntity current;
        LivingEntity live;
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        long now = System.nanoTime();
        float dt = Math.min(0.1f, (float)(now - this.lastNs) / 1.0E9f);
        this.lastNs = now;
        boolean dragMode = DragSystem.Companion.get().isDragModeActive();
        LivingEntity livingEntity2 = live = dragMode ? (LivingEntity)MinecraftClient.getInstance().player : TargetHudComp.Companion.hoveredTarget();
        if (live != null) {
            if (this.target != live) {
                this.resetHealthTracking(live);
            }
            this.target = live;
            this.lastSeenMs = System.currentTimeMillis();
        }
        boolean targetHidden = (current = this.target) != null && !dragMode && EntityVisibility.isHidden((Entity)current);
        boolean targetDead = current != null && !dragMode && (current.isRemoved() || !current.isAlive() || current.getHealth() <= 0.0f);
        boolean held = current != null && !targetHidden && !targetDead && (dragMode || System.currentTimeMillis() - this.lastSeenMs <= 1500L);
        boolean targetVisible = TargetHudComp.Companion.componentEnabled() && held;
        this.visibility.updateTarget(targetVisible);
        this.updateFollow(dt);
        float alpha = this.visibility.get();
        if (alpha <= 0.01f) {
            if (!targetVisible) {
                this.target = null;
            }
            return;
        }
        if (this.target == null) {
            return;
        }
        boolean bl = followShift = Math.abs(this.followOffsetX) > 0.01f || Math.abs(this.followOffsetY) > 0.01f;
        if (followShift) {
            graphics.getMatrices().pushMatrix();
            graphics.getMatrices().translate(this.followOffsetX, this.followOffsetY);
        }
        try {
            this.renderContent(graphics, dt, alpha);
        }
        finally {
            if (followShift) {
                graphics.getMatrices().popMatrix();
            }
        }
    }

    private final void renderContent(DrawContext graphics, float dt, float alpha) {
        if (TargetHudComp.Companion.isCircleMode()) {
            this.renderCircle(graphics, dt, alpha);
            return;
        }
        if (TargetHudComp.Companion.isNewMode()) {
            this.renderNew(graphics, dt, alpha);
            return;
        }
        LivingEntity livingEntity2 = this.target;
        Intrinsics.checkNotNull((Object)livingEntity2);
        LivingEntity entity = livingEntity2;
        boolean armorOn = TargetHudComp.Companion.armorEnabled() && TargetHudComp.Companion.armorPieceCount(entity) > 0;
        float classicRate = armorOn ? 6.0f : 10.0f;
        this.armorProgress += ((armorOn ? 1.0f : 0.0f) - this.armorProgress) * (1.0f - (float)Math.exp(-dt * classicRate));
        this.armorProgress = TargetHudComp.Companion.clamp01(this.armorProgress);
        float classicT = TargetHudComp.Companion.easeInOutCubic(this.armorProgress);
        float armorExtra = 11.0f * classicT;
        float panelH = 34.0f + armorExtra;
        float headTop = this.getY() + 6.0f + armorExtra * 0.5f;
        float headCenterX = this.getX() + 6.0f + 11.0f;
        float headCenterY = headTop + 11.0f;
        this.updateHitParticles(entity, headCenterX, headCenterY);
        Text name = TargetHudComp.Companion.resolveName(entity);
        float maxHealth = Math.max(1.0f, entity.getMaxHealth());
        float health = Network.getResolvedHealth(entity, true);
        float ratio = Math.max(0.0f, Math.min(1.0f, health / maxHealth));
        this.healthDisplay += (ratio - this.healthDisplay) * (1.0f - (float)Math.exp(-dt * 12.0f));
        String infoText = MathKt.roundToInt((float)(ratio * 100.0f)) + "% - " + TargetHudComp.Companion.distanceText(entity) + "m";
        float nameWidth = TargetHudComp.Companion.coloredWidth(name, 8.0f);
        float infoWidth = Fonts.MEDIUM.msdfWidth(infoText, 6.0f);
        float contentW = Math.max(74.0f, Math.max(nameWidth, infoWidth));
        this.currentWidth = 35.0f + contentW + 6.0f;
        this.pinCenterOnResize();
        float x = this.getX();
        float y = this.getY();
        float scale = 0.94f + alpha * 0.06f;
        float originX = x + this.currentWidth * 0.5f;
        float originY = y + panelH * 0.5f;
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(originX, originY);
        graphics.getMatrices().scale(scale);
        graphics.getMatrices().translate(-originX, -originY);
        this.updateUseIndicator(entity, dt);
        this.renderUseIndicator(graphics, alpha, panelH);
        Render2D.beginFrame(graphics);
        RectUtil.drawClientRect(x, y, this.currentWidth, panelH, 7.0f, alpha);
        Render2D.flush();
        Render2D.beginFrame(graphics);
        float contentX = x + 6.0f + 22.0f + 7.0f;
        float centerX = contentX + contentW * 0.5f;
        float armorRowH = 8.0f * classicT;
        float gapV = (22.0f + armorExtra - 8.0f - armorRowH - 3.0f - 6.0f) / (2.0f + classicT);
        float nameY = y + 6.0f;
        float armorY = nameY + 8.0f + gapV * classicT;
        float barY = armorY + armorRowH + gapV;
        float infoY = barY + 3.0f + gapV;
        TargetHudComp.Companion.drawColoredName(name, centerX - nameWidth * 0.5f, nameY, 8.0f, -1, alpha * 0.85f);
        float barX = contentX;
        float barW = contentW;
        float classicBarRadius = TargetHudComp.Companion.barRadius(3.0f);
        Render2D.rect(barX, barY + 1.0f, barW, 3.0f, classicBarRadius, MathKt.roundToInt((float)((float)36 * TargetHudComp.Companion.clamp01(alpha))) << 24);
        float fillW = barW * this.healthDisplay;
        TargetHudComp.Companion.drawBarFill(barX, barY + 1.0f, fillW, barW, 3.0f, classicBarRadius, this.healthDisplay, alpha);
        Fonts.MEDIUM.msdf(infoText, centerX - infoWidth * 0.5f, infoY, 6.0f, ColorEngine.multAlpha(-3618608, alpha));
        this.drawHeadBackground(graphics, entity, x + 6.0f + 1.0f, headTop, 22.0f, 11.0f, alpha, 0.0f);
        Render2D.flush();
        Render2D.beginFrame(graphics);
        this.drawHitParticles(graphics, alpha);
        Render2D.flush();
        Render2D.beginFrame(graphics);
        this.drawHeadForeground(graphics, entity, x + 6.0f + 1.0f, headTop, 22.0f, 11.0f, alpha);
        Render2D.flush();
        if (this.armorProgress > 0.02f) {
            this.drawArmorCentered(graphics, entity, centerX, armorY + 1.0f, alpha, this.armorProgress);
        }
        graphics.getMatrices().popMatrix();
    }

    private final void renderCircle(DrawContext graphics, float dt, float alpha) {
        float health;
        LivingEntity livingEntity2 = this.target;
        Intrinsics.checkNotNull((Object)livingEntity2);
        LivingEntity entity = livingEntity2;
        float panelH = 36.0f;
        ArrayList<ItemStack> slots = new ArrayList<ItemStack>(NEW_SLOTS.length);
        boolean anyItem = false;
        for (EquipmentSlot slot : NEW_SLOTS) {
            ItemStack stack = (ItemStack) (entity.getEquippedStack(slot));
            slots.add(stack);
            anyItem |= !stack.isEmpty();
        }
        boolean armorOn = TargetHudComp.Companion.armorEnabled() && anyItem;
        float armorRate = armorOn ? 6.0f : 10.0f;
        this.armorProgress += ((armorOn ? 1.0f : 0.0f) - this.armorProgress) * (1.0f - (float)Math.exp(-dt * armorRate));
        this.armorProgress = TargetHudComp.Companion.clamp01(this.armorProgress);
        float layoutT = TargetHudComp.Companion.easeInOutCubic(this.armorProgress);
        float widthT = TargetHudComp.Companion.easeOutCubic(TargetHudComp.Companion.clamp01(this.armorProgress / 0.6f));
        Text name = TargetHudComp.Companion.resolveName(entity);
        float maxHealth = Math.max(1.0f, entity.getMaxHealth());
        float healthFloor = Math.max(maxHealth, health = Network.getResolvedHealth(entity, true));
        if (this.hpMaxRef < healthFloor) {
            this.hpMaxRef = healthFloor;
        } else if (health <= maxHealth && health >= this.hpValue) {
            this.hpMaxRef += (healthFloor - this.hpMaxRef) * (1.0f - (float)Math.exp(-dt * 1.5f));
        }
        float ratio = TargetHudComp.Companion.clamp01(health / Math.max(1.0f, this.hpMaxRef));
        this.healthDisplay += (ratio - this.healthDisplay) * (1.0f - (float)Math.exp(-dt * 12.0f));
        this.updateHpCounter(health, dt);
        float itemsW = (float)slots.size() * 8.0f + (float)(slots.size() - 1) * 3.0f;
        float nameWidth = TargetHudComp.Companion.coloredWidth(name, 8.0f);
        float contentW = Math.max(62.0f, Math.max(nameWidth, (itemsW + 2.0f) * widthT));
        this.currentWidth = 37.0f + contentW + 7.0f + 21.0f + 6.0f;
        this.pinCenterOnResize();
        float x = this.getX();
        float y = this.getY();
        float headX = x + 6.0f;
        float headY = y + 6.0f;
        float headRadius = 12.0f;
        this.updateHitParticles(entity, headX + headRadius, headY + headRadius);
        float scale = 0.94f + alpha * 0.06f;
        float originX = x + this.currentWidth * 0.5f;
        float originY = y + panelH * 0.5f;
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(originX, originY);
        graphics.getMatrices().scale(scale);
        graphics.getMatrices().translate(-originX, -originY);
        this.updateUseIndicator(entity, dt);
        this.renderUseIndicator(graphics, alpha, panelH);
        Render2D.beginFrame(graphics);
        RectUtil.drawClientRect(x, y, this.currentWidth, panelH, 7.0f, alpha);
        Render2D.flush();
        Render2D.beginFrame(graphics);
        float contentX = headX + 24.0f + 7.0f;
        float cellSize = 10.0f;
        float[] nameBounds = TargetHudComp.Companion.coloredBounds(name, 8.0f);
        float nameTop = nameBounds[1];
        float nameGlyphH = nameBounds[3] - nameBounds[1];
        float blockH = nameGlyphH + (2.5f + cellSize) * layoutT;
        float blockTop = y + (panelH - blockH) * 0.5f;
        float nameY = blockTop - nameTop;
        float cellsTop = blockTop + nameGlyphH + 2.5f;
        float itemsStartX = contentX + (contentW - itemsW) * 0.5f;
        float rowCenterY = cellsTop + cellSize * 0.5f;
        TargetHudComp.Companion.drawColoredName(name, contentX + (contentW - nameWidth) * 0.5f, nameY, 8.0f, -1, alpha * 0.85f);
        float appearK = 1.0f - (float)Math.exp(-dt * 14.0f);
        float vanishK = 1.0f - (float)Math.exp(-dt * 30.0f);
        int slotCount = Math.max(1, slots.size() - 1);
        int n = ((Collection)slots).size();
        for (int i = 0; i < n; ++i) {
            float start = 0.35f + 0.25f * ((float)i / (float)slotCount);
            boolean want = this.armorProgress > start;
            this.slotAppear[i] = TargetHudComp.Companion.clamp01(this.slotAppear[i] + ((want ? 1.0f : 0.0f) - this.slotAppear[i]) * (want ? appearK : vanishK));
        }
        if (this.armorProgress > 0.02f) {
            float cellBgX = itemsStartX;
            int start = ((Collection)slots).size();
            for (int i = 0; i < start; ++i) {
                float cellT = TargetHudComp.Companion.easeOutCubic(this.slotAppear[i]);
                if (cellT > 0.01f) {
                    int cellBg = MathKt.roundToInt((float)((float)36 * TargetHudComp.Companion.clamp01(alpha) * cellT)) << 24;
                    Render2D.rect(cellBgX - 1.0f, cellsTop, cellSize, cellSize, 2.0f * cellT, cellBg);
                }
                cellBgX += 11.0f;
            }
        }
        float ringX = x + this.currentWidth - 6.0f - 10.5f;
        float ringY = y + panelH * 0.5f;
        this.drawHealthRing(ringX, ringY, alpha);
        float innerRadius = Math.max(1.0f, 9.4f);
        float hpSize = 9.0f;
        float[] hpTextBounds = TargetHudComp.Companion.safeBounds(Fonts.SMALL_PIXEL.msdfBounds(this.hpText, hpSize));
        float hpFullWidth = hpTextBounds[2] - hpTextBounds[0];
        float hpLimit = innerRadius * 2.0f * 0.86f;
        if (hpFullWidth > hpLimit && hpFullWidth > 0.001f) {
            hpSize *= hpLimit / hpFullWidth;
        }
        Render2D.pushScissor(graphics, ringX - innerRadius, ringY - innerRadius, innerRadius * 2.0f, innerRadius * 2.0f);
        this.drawHpCounter(ringX, ringY, hpSize, ColorEngine.multAlpha(-1, alpha * 0.9f));
        Render2D.popScissor(graphics);
        this.drawHeadBackground(graphics, entity, headX, headY, 24.0f, headRadius, alpha, 0.0f);
        Render2D.flush();
        Render2D.beginFrame(graphics);
        this.drawHitParticles(graphics, alpha);
        Render2D.flush();
        Render2D.beginFrame(graphics);
        this.drawHeadForeground(graphics, entity, headX, headY, 24.0f, headRadius, alpha);
        Render2D.flush();
        if (this.armorProgress > 0.02f) {
            float half = 4.0f;
            float iconBaseScale = 0.5f * TargetHudComp.Companion.clamp01(alpha);
            TextRenderer textRenderer2 = MinecraftClient.getInstance().textRenderer;
            Intrinsics.checkNotNullExpressionValue((Object)textRenderer2, (String)"font");
            TextRenderer font = textRenderer2;
            float cellX = itemsStartX;
            int n2 = ((Collection)slots).size();
            for (int i = 0; i < n2; ++i) {
                float iconT;
                Object e = slots.get(i);
                Intrinsics.checkNotNullExpressionValue(e, (String)"get(...)");
                ItemStack stack = (ItemStack)e;
                if (!stack.isEmpty() && (iconT = TargetHudComp.Companion.clamp01((this.slotAppear[i] - 0.35f) / 0.65f)) > 0.01f) {
                    float iconScale = iconBaseScale * TargetHudComp.Companion.easeOutBack(iconT);
                    graphics.getMatrices().pushMatrix();
                    Matrix3x2fStack matrix3x2fStack = graphics.getMatrices();
                    Intrinsics.checkNotNullExpressionValue((Object)matrix3x2fStack, (String)"pose(...)");
                    Render2DCoordinateSpace.applyGuiScaleIndependence((Matrix3x2f)matrix3x2fStack);
                    graphics.getMatrices().translate(cellX + half, rowCenterY);
                    graphics.getMatrices().scale(iconScale, iconScale);
                    graphics.getMatrices().translate(-8.0f, -8.0f);
                    graphics.drawItem(stack, 0, 0);
                    graphics.drawStackOverlay(font, stack, 0, 0);
                    graphics.getMatrices().popMatrix();
                }
                cellX += 11.0f;
            }
        }
        graphics.getMatrices().popMatrix();
    }

    private final void drawHealthRing(float centerX, float centerY, float alpha) {
        float a = TargetHudComp.Companion.clamp01(alpha);
        float radius = 10.5f;
        float thickness = 2.2f;
        Render2D.arcRect(new BuiltArcRect(centerX, centerY, radius, thickness, 0.0f, 360.0f, MathKt.roundToInt((float)((float)36 * a)) << 24));
        float rawSweep = 360.0f * TargetHudComp.Companion.clamp01(this.healthDisplay);
        if (rawSweep <= 0.02f) {
            return;
        }
        float capSweep = (float)Math.toDegrees(thickness / radius);
        float tiny = TargetHudComp.Companion.clamp01(rawSweep / Math.max(0.001f, capSweep));
        float fillAlpha = a * tiny;
        if (fillAlpha <= 0.004f) {
            return;
        }
        float sweep = Math.max(rawSweep, capSweep);
        RectUtil.drawClientArc(centerX, centerY, radius, thickness * (1.0f + 1.4f * tiny), 0.0f, sweep, fillAlpha * 0.16f, 1.0f + 1.6f * tiny);
        RectUtil.drawClientArc(centerX, centerY, radius, thickness, 0.0f, sweep, fillAlpha);
    }

    private final void resetHealthTracking(LivingEntity entity) {
        float maxHealth = Math.max(1.0f, entity.getMaxHealth());
        float health = Network.getResolvedHealth(entity, true);
        this.hpMaxRef = Math.max(maxHealth, health);
        this.healthDisplay = TargetHudComp.Companion.clamp01(health / this.hpMaxRef);
        this.hpValue = -1.0f;
        this.hpText = "";
        this.hpPrevText = "";
        this.hpRoll = 1.0f;
    }

    private final void updateHpCounter(float health, float dt) {
        float value = Math.max(0.0f, health);
        String text = TargetHudComp.Companion.formatHp(value);
        if (!Intrinsics.areEqual((Object)text, (Object)this.hpText)) {
            this.hpPrevText = this.hpText;
            this.hpRollDir = this.hpValue >= 0.0f && value > this.hpValue ? 1.0f : -1.0f;
            this.hpRoll = ((CharSequence)this.hpText).length() == 0 ? 1.0f : 0.0f;
            this.hpText = text;
        }
        this.hpValue = value;
        if (this.hpRoll < 1.0f) {
            this.hpRoll = Math.min(1.0f, this.hpRoll + dt / 0.22f);
        }
    }

    private final void drawHpCounter(float centerX, float centerY, float size, int color) {
        String text = this.hpText;
        if (((CharSequence)text).length() == 0) {
            return;
        }
        Fonts font = Fonts.SMALL_PIXEL;
        float[] digitBounds = TargetHudComp.Companion.safeBounds(font.msdfBounds("0", size));
        float lineH = Math.max(size, digitBounds[3] - digitBounds[1]);
        float baseY = centerY - (digitBounds[1] + digitBounds[3]) * 0.5f;
        float progress = TargetHudComp.Companion.easeOutCubic(TargetHudComp.Companion.clamp01(this.hpRoll));
        String previous = this.hpPrevText;
        if (progress >= 1.0f || ((CharSequence)previous).length() == 0 || Intrinsics.areEqual((Object)previous, (Object)text)) {
            font.msdf(text, TargetHudComp.Companion.inkLeft(font, text, size, centerX), baseY, size, color);
            return;
        }
        float slide = lineH * 1.15f;
        float enter = this.hpRollDir * (1.0f - progress) * slide;
        float leave = -this.hpRollDir * progress * slide;
        int enterColor = ColorEngine.multAlpha(color, progress);
        int leaveColor = ColorEngine.multAlpha(color, 1.0f - progress);
        if (previous.length() != text.length()) {
            font.msdf(previous, TargetHudComp.Companion.inkLeft(font, previous, size, centerX), baseY + leave, size, leaveColor);
            font.msdf(text, TargetHudComp.Companion.inkLeft(font, text, size, centerX), baseY + enter, size, enterColor);
            return;
        }
        float left = TargetHudComp.Companion.inkLeft(font, text, size, centerX);
        int n = ((CharSequence)text).length();
        for (int i = 0; i < n; ++i) {
            String string = text.substring(0, i);
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
            float columnX = left + font.msdfWidth(string, size);
            String digit = String.valueOf(text.charAt(i));
            if (text.charAt(i) == previous.charAt(i)) {
                font.msdf(digit, columnX, baseY, size, color);
                continue;
            }
            font.msdf(String.valueOf(previous.charAt(i)), columnX, baseY + leave, size, leaveColor);
            font.msdf(digit, columnX, baseY + enter, size, enterColor);
        }
    }

    private final void renderNew(DrawContext graphics, float dt, float alpha) {
        LivingEntity livingEntity2 = this.target;
        Intrinsics.checkNotNull((Object)livingEntity2);
        LivingEntity entity = livingEntity2;
        float x = this.getX();
        float y = this.getY();
        ArrayList<ItemStack> slots = new ArrayList<ItemStack>(NEW_SLOTS.length);
        boolean anyItem = false;
        for (EquipmentSlot slot : NEW_SLOTS) {
            ItemStack stack = (ItemStack) (entity.getEquippedStack(slot));
            slots.add(stack);
            anyItem |= !stack.isEmpty();
        }
        boolean armorOn = TargetHudComp.Companion.armorEnabled() && anyItem;
        float armorRate = armorOn ? 6.0f : 10.0f;
        this.armorProgress += ((armorOn ? 1.0f : 0.0f) - this.armorProgress) * (1.0f - (float)Math.exp(-dt * armorRate));
        this.armorProgress = TargetHudComp.Companion.clamp01(this.armorProgress);
        float layoutT = TargetHudComp.Companion.easeInOutCubic(this.armorProgress);
        float panelH = 45.0f - 10.0f * (1.0f - layoutT);
        float barY = y + panelH - 5.0f - 8.0f;
        float headX = x + 3.0f;
        float headY = y + 3.0f;
        float headSize = barY - 3.0f - headY;
        float headFull = 26.0f;
        float headRadius = Math.max(4.5f, 6.0f * TargetHudComp.Companion.clamp01(headSize / Math.max(headFull, 1.0f)));
        InterfaceModule interfaceModule = InterfaceModule.Companion.getInstance();
        if (interfaceModule != null && interfaceModule.rectCornerRadius.getFloat() > 6.0f) {
            headRadius = Math.max(headRadius, Math.min(interfaceModule.rectCornerRadius.getFloat(), headSize * 0.5f));
        }
        this.updateHitParticles(entity, headX + headSize * 0.5f, headY + headSize * 0.5f);
        Text name = TargetHudComp.Companion.resolveName(entity);
        float maxHealth = Math.max(1.0f, entity.getMaxHealth());
        float health = Network.getResolvedHealth(entity, true);
        float ratio = TargetHudComp.Companion.clamp01(health / maxHealth);
        this.healthDisplay += (ratio - this.healthDisplay) * (1.0f - (float)Math.exp(-dt * 12.0f));
        float itemsW = (float)slots.size() * 8.0f + (float)(slots.size() - 1) * 3.0f;
        float nameSpanW = TargetHudComp.Companion.coloredWidth(name, 8.0f);
        float rightX = headX + headSize + 3.0f;
        float widthT = TargetHudComp.Companion.easeOutCubic(TargetHudComp.Companion.clamp01(this.armorProgress / 0.6f));
        float armorRowW = (itemsW + 2.0f) * widthT;
        float rightW = Math.max(nameSpanW, armorRowW);
        this.currentWidth = Math.max(84.0f, rightX - x + rightW + 3.0f);
        this.pinCenterOnResize();
        x = this.getX();
        y = this.getY();
        barY = y + panelH - 5.0f - 8.0f;
        headX = x + 3.0f;
        headY = y + 3.0f;
        rightX = headX + headSize + 3.0f;
        float scale = 0.94f + alpha * 0.06f;
        float originX = x + this.currentWidth * 0.5f;
        float originY = y + panelH * 0.5f;
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(originX, originY);
        graphics.getMatrices().scale(scale);
        graphics.getMatrices().translate(-originX, -originY);
        this.updateUseIndicator(entity, dt);
        this.renderUseIndicator(graphics, alpha, panelH);
        Render2D.beginFrame(graphics);
        RectUtil.drawClientRect(x, y, this.currentWidth, panelH, 7.0f, alpha);
        Render2D.flush();
        Render2D.beginFrame(graphics);
        float half = 4.0f;
        float cellSize = 10.0f;
        float cellsTop = headY + headSize - cellSize;
        float rowCenterY = cellsTop + cellSize * 0.5f;
        float itemsStartX = rightX + 1.0f;
        float nameYNoArmor = headY + headSize * 0.5f - 4.0f;
        float nameYArmor = (headY + cellsTop - 8.0f) * 0.5f;
        float nameY = TargetHudComp.Companion.lerp(nameYNoArmor, nameYArmor, layoutT) - 1.5f * (1.0f - layoutT);
        float nameX = rightX;
        TargetHudComp.Companion.drawColoredName(name, nameX, nameY, 8.0f, -1, alpha * 0.85f);
        float barX = x + 3.0f;
        float barW = this.currentWidth - 6.0f;
        float newBarRadius = TargetHudComp.Companion.barRadius(8.0f);
        Render2D.rect(barX, barY, barW, 8.0f, newBarRadius, MathKt.roundToInt((float)((float)36 * TargetHudComp.Companion.clamp01(alpha))) << 24);
        float fillW = barW * this.healthDisplay;
        TargetHudComp.Companion.drawBarFill(barX, barY, fillW, barW, 8.0f, newBarRadius, this.healthDisplay, alpha);
        String hpText = MathKt.roundToInt((float)Math.max(0.0f, health)) + "HP";
        float hpW = Fonts.SMALL_PIXEL.msdfWidth(hpText, 6.0f);
        float centered = barX + (barW - hpW) * 0.5f + 0.75f;
        float followEdge = barX + fillW - hpW - 2.0f;
        float hpX = Math.max(Math.min(centered, followEdge), barX + 2.0f);
        float hpY = barY + 1.0f - 0.3f;
        float cover = hpW <= 0.0f ? 0.0f : TargetHudComp.Companion.clamp01((barX + fillW - hpX) / hpW);
        float textT = TargetHudComp.Companion.clamp01((hpX + hpW * 0.5f - barX) / Math.max(barW, 1.0f));
        int fillRgb = TargetHudComp.Companion.barFillColorAt(textT, this.healthDisplay, hpX + hpW * 0.5f, barY + 4.0f);
        int bgRgb = ColorEngine.lerpColor(-14935006, fillRgb, cover);
        float lum = (0.2126f * (float)(bgRgb >> 16 & 0xFF) + 0.7152f * (float)(bgRgb >> 8 & 0xFF) + 0.0722f * (float)(bgRgb & 0xFF)) / 255.0f;
        float grayTarget = TargetHudComp.Companion.clamp01(1.0f - lum * 1.35f);
        this.hpGrayDisplay += (grayTarget - this.hpGrayDisplay) * (1.0f - (float)Math.exp(-dt * 10.0f));
        int gray = MathKt.roundToInt((float)(TargetHudComp.Companion.clamp01(this.hpGrayDisplay) * 255.0f));
        int hpColor = 0xFF000000 | gray << 16 | gray << 8 | gray;
        Fonts.SMALL_PIXEL.msdf(hpText, hpX, hpY, 6.0f, ColorEngine.multAlpha(hpColor, alpha));
        float panelRightLimit = x + this.currentWidth - 3.0f + 1.0f;
        int slotCount = Math.max(1, slots.size() - 1);
        float appearK = 1.0f - (float)Math.exp(-dt * 14.0f);
        float vanishK = 1.0f - (float)Math.exp(-dt * 30.0f);
        float gateX = itemsStartX;
        int n = ((Collection)slots).size();
        for (int i = 0; i < n; ++i) {
            float start = 0.35f + 0.25f * ((float)i / (float)slotCount);
            boolean fits = gateX - 1.0f + cellSize <= panelRightLimit;
            boolean want = this.armorProgress > start && fits;
            this.slotAppear[i] = TargetHudComp.Companion.clamp01(this.slotAppear[i] + ((want ? 1.0f : 0.0f) - this.slotAppear[i]) * (want ? appearK : vanishK));
            gateX += 11.0f;
        }
        if (this.armorProgress > 0.02f) {
            float cellBgX = itemsStartX;
            int start = ((Collection)slots).size();
            for (int i = 0; i < start; ++i) {
                float cellT = TargetHudComp.Companion.easeOutCubic(this.slotAppear[i]);
                if (cellT > 0.01f) {
                    int cellBg = MathKt.roundToInt((float)((float)36 * TargetHudComp.Companion.clamp01(alpha) * cellT)) << 24;
                    Render2D.rect(cellBgX - 1.0f, cellsTop, cellSize, cellSize, 2.0f * cellT, cellBg);
                }
                cellBgX += 11.0f;
            }
        }
        this.drawHeadBackground(graphics, entity, headX, headY, headSize, headRadius, alpha, 1.0f * (1.0f - layoutT));
        Render2D.flush();
        Render2D.beginFrame(graphics);
        this.drawHitParticles(graphics, alpha);
        Render2D.flush();
        Render2D.beginFrame(graphics);
        this.drawHeadForeground(graphics, entity, headX, headY, headSize, headRadius, alpha);
        Render2D.flush();
        float iconBaseScale = 0.5f * TargetHudComp.Companion.clamp01(alpha);
        TextRenderer textRenderer2 = MinecraftClient.getInstance().textRenderer;
        Intrinsics.checkNotNullExpressionValue((Object)textRenderer2, (String)"font");
        TextRenderer font = textRenderer2;
        float cellX = itemsStartX;
        int n2 = ((Collection)slots).size();
        for (int i = 0; i < n2; ++i) {
            float iconT;
            Object e = slots.get(i);
            Intrinsics.checkNotNullExpressionValue(e, (String)"get(...)");
            ItemStack stack = (ItemStack)e;
            if (!stack.isEmpty() && this.armorProgress > 0.02f && (iconT = TargetHudComp.Companion.clamp01((this.slotAppear[i] - 0.35f) / 0.65f)) > 0.01f) {
                float iconScale = iconBaseScale * TargetHudComp.Companion.easeOutBack(iconT);
                graphics.getMatrices().pushMatrix();
                Matrix3x2fStack matrix3x2fStack = graphics.getMatrices();
                Intrinsics.checkNotNullExpressionValue((Object)matrix3x2fStack, (String)"pose(...)");
                Render2DCoordinateSpace.applyGuiScaleIndependence((Matrix3x2f)matrix3x2fStack);
                graphics.getMatrices().translate(cellX + half, rowCenterY);
                graphics.getMatrices().scale(iconScale, iconScale);
                graphics.getMatrices().translate(-8.0f, -8.0f);
                graphics.drawItem(stack, 0, 0);
                graphics.drawStackOverlay(font, stack, 0, 0);
                graphics.getMatrices().popMatrix();
            }
            cellX += 11.0f;
        }
        graphics.getMatrices().popMatrix();
    }

    private final void updateUseIndicator(LivingEntity entity, float dt) {
        boolean using;
        if (entity.getId() != this.useEntityId) {
            this.useEntityId = entity.getId();
            this.useSlide = 0.0f;
            this.useArcDisplay = 0.0f;
            ItemStack itemStack2 = ItemStack.EMPTY;
            Intrinsics.checkNotNullExpressionValue((Object)itemStack2, (String)"EMPTY");
            this.lastUseItem = itemStack2;
        }
        ItemStack active = TargetHudComp.Companion.activeConsumable(entity);
        boolean bl = using = TargetHudComp.Companion.useItemEnabled() && !active.isEmpty();
        if (using && !ItemStack.areItemsAndComponentsEqual((ItemStack)this.lastUseItem, (ItemStack)active)) {
            ItemStack itemStack3 = active.copy();
            Intrinsics.checkNotNullExpressionValue((Object)itemStack3, (String)"copy(...)");
            this.lastUseItem = itemStack3;
        }
        float rate = using ? 12.0f : 9.0f;
        this.useSlide += ((using ? 1.0f : 0.0f) - this.useSlide) * (1.0f - (float)Math.exp(-dt * rate));
        this.useSlide = TargetHudComp.Companion.clamp01(this.useSlide);
        if (using) {
            float progress = TargetHudComp.Companion.useProgress(entity, active);
            this.useArcDisplay = progress < this.useArcDisplay - 0.25f ? progress : (this.useArcDisplay += (progress - this.useArcDisplay) * (1.0f - (float)Math.exp(-dt * 14.0f)));
        }
    }

    private final void renderUseIndicator(DrawContext graphics, float alpha, float panelH) {
        float t = TargetHudComp.Companion.easeOutCubic(this.useSlide);
        if (t <= 0.02f || this.lastUseItem.isEmpty()) {
            return;
        }
        float a = TargetHudComp.Companion.clamp01(alpha * t);
        float x = MathKt.roundToInt((float)(this.getX() - 28.0f * t));
        float y = MathKt.roundToInt((float)(this.getY() + (panelH - 24.0f) * 0.5f));
        Render2D.beginFrame(graphics);
        RectUtil.drawClientRectFixedRadiusNoGlow(x, y, 24.0f, 24.0f, 12.0f, a);
        float cx = x + 12.0f;
        float cy = y + 12.0f;
        float ringRad = 11.0f;
        Render2D.outline360(cx - ringRad, cy - ringRad, ringRad * 2.0f, ringRad * 2.0f, ringRad, 1.6f, ColorEngine.multAlpha(-938997752, a), new Outline360Range[0]);
        TargetHudComp.Companion.drawUseRing(cx, cy, ringRad, 1.6f, TargetHudComp.Companion.clamp01(this.useArcDisplay) * 360.0f, 255.0f * a);
        Render2D.flush();
        float iconT = TargetHudComp.Companion.easeOutCubic(TargetHudComp.Companion.clamp01((t - 0.4f) / 0.6f));
        if (iconT > 0.01f) {
            float iconX = MathKt.roundToInt((float)(x + 4.0f));
            float iconY = MathKt.roundToInt((float)(y + 4.0f));
            graphics.getMatrices().pushMatrix();
            Matrix3x2fStack matrix3x2fStack = graphics.getMatrices();
            Intrinsics.checkNotNullExpressionValue((Object)matrix3x2fStack, (String)"pose(...)");
            Render2DCoordinateSpace.applyGuiScaleIndependence((Matrix3x2f)matrix3x2fStack);
            graphics.getMatrices().translate(iconX + 8.0f, iconY + 8.0f);
            graphics.getMatrices().scale(iconT, iconT);
            graphics.getMatrices().translate(-8.0f, -8.0f);
            graphics.drawItem(this.lastUseItem, 0, 0);
            graphics.getMatrices().popMatrix();
        }
    }

    private final void pinCenterOnResize() {
        float delta;
        if (!Float.isNaN(this.lastPinnedWidth) && !this.getDrag().isDragging() && Math.abs(delta = this.currentWidth - this.lastPinnedWidth) > 1.0E-4f) {
            this.getDrag().setTargetX(this.getDrag().getTargetX() - delta * 0.5f);
            this.getDrag().syncToTarget();
        }
        this.lastPinnedWidth = this.currentWidth;
    }

    @EventHandler
    private final void onWorldRender(WorldRenderEvent event) {
        float topY;
        if (event.isPortalPass()) {
            return;
        }
        this.followProjectionValid = false;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        LivingEntity entity = this.target;
        if (!TargetHudComp.Companion.followEnabled() || entity == null || mc.player == null || entity == mc.player) {
            return;
        }
        float partialTicks = event.getPartialTicks();
        Vec3d vec3d2 = event.getCamera() == null ? mc.gameRenderer.getCamera().getCameraPos() : event.getCamera().getCameraPos();
        Intrinsics.checkNotNull((Object)vec3d2);
        Vec3d cameraPos = vec3d2;
        Vec3d vec3d3 = entity.getLerpedPos(partialTicks);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"getPosition(...)");
        Vec3d pos = vec3d3;
        this.followScratch.set((float)(pos.x - cameraPos.x), (float)(pos.y + (double)((entity.getHeight() + 0.4f) * 0.5f) - cameraPos.y), (float)(pos.z - cameraPos.z), 1.0f);
        event.getPositionMatrix().transform(this.followScratch);
        event.getProjectionMatrix().transform(this.followScratch);
        if (this.followScratch.w <= 1.0E-4f) {
            return;
        }
        float screenX = (this.followScratch.x / this.followScratch.w * 0.5f + 0.5f) * Position.Companion.screenWidth();
        float screenY = (1.0f - (this.followScratch.y / this.followScratch.w * 0.5f + 0.5f)) * Position.Companion.screenHeight();
        if (Float.isNaN(screenX) || Float.isNaN(screenY)) {
            return;
        }
        float halfH = 40.0f;
        this.followScratch.set((float)(pos.x - cameraPos.x), (float)(pos.y + (double)entity.getHeight() + (double)0.4f - cameraPos.y), (float)(pos.z - cameraPos.z), 1.0f);
        event.getPositionMatrix().transform(this.followScratch);
        event.getProjectionMatrix().transform(this.followScratch);
        if (this.followScratch.w > 1.0E-4f && !Float.isNaN(topY = (1.0f - (this.followScratch.y / this.followScratch.w * 0.5f + 0.5f)) * Position.Companion.screenHeight())) {
            halfH = Math.abs(screenY - topY);
        }
        this.followScreenX = screenX;
        this.followScreenY = screenY;
        this.followHalfH = halfH;
        this.followProjectionValid = true;
    }

    private final void updateFollow(float dt) {
        boolean active = TargetHudComp.Companion.followEnabled() && this.followProjectionValid && this.target != null && this.target != MinecraftClient.getInstance().player && !DragSystem.Companion.get().isDragModeActive();
        float targetOffsetX = 0.0f;
        float targetOffsetY = 0.0f;
        if (active) {
            boolean onScreen;
            float panelW = this.width();
            float panelH = this.height();
            float sw = Position.Companion.screenWidth();
            float sh = Position.Companion.screenHeight();
            float cx = this.followScreenX;
            float cy = this.followScreenY;
            boolean bl = onScreen = cx > -sw * 0.1f && cx < sw * 1.1f && cy > -sh * 0.1f && cy < sh * 1.1f;
            if (onScreen) {
                float halfH = Math.max(this.followHalfH, 14.0f);
                float halfW = halfH * 0.45f;
                float sideGap = 10.0f;
                float belowGap = 8.0f;
                float nx = cx / sw;
                float side = Math.min(1.0f, Math.abs(nx - 0.5f) / 0.15f);
                float sideX = nx < 0.5f ? cx + halfW + sideGap : cx - halfW - sideGap - panelW;
                float sideY = cy - panelH * 0.5f;
                float belowX = cx - panelW * 0.5f;
                float belowY = cy + halfH + belowGap;
                float followX = belowX + (sideX - belowX) * side;
                float followY = belowY + (sideY - belowY) * side;
                followX = Position.Companion.clampX(followX, panelW);
                followY = Position.Companion.clampY(followY, panelH);
                targetOffsetX = followX - this.getX();
                targetOffsetY = followY - this.getY();
            }
        }
        float k = 1.0f - (float)Math.exp(-dt * 12.0f);
        this.followOffsetX += (targetOffsetX - this.followOffsetX) * k;
        this.followOffsetY += (targetOffsetY - this.followOffsetY) * k;
        if (!active && Math.abs(this.followOffsetX) < 0.5f && Math.abs(this.followOffsetY) < 0.5f) {
            this.followOffsetX = 0.0f;
            this.followOffsetY = 0.0f;
        }
    }

    private final void drawHeadBackground(DrawContext graphics, LivingEntity entity, float x, float y, float size, float radius, float alpha, float questionLift) {
        if (entity instanceof AbstractClientPlayerEntity) {
            return;
        }
        Render2D.rect(x, y, size, size, radius, radius, radius, radius, ColorEngine.multAlpha(-14935006, alpha * 0.5f));
        float qw = Fonts.SEMIBOLD.msdfWidth("?", 8.0f);
        Fonts.SEMIBOLD.msdf("?", x + (size - qw) * 0.5f, y + (size - 8.0f) * 0.45f - questionLift, 8.0f, ColorEngine.multAlpha(-1, alpha * 0.7f));
    }

    private final void drawHeadForeground(DrawContext graphics, LivingEntity entity, float x, float y, float size, float radius, float alpha) {
        if (!(entity instanceof AbstractClientPlayerEntity)) {
            return;
        }
        Identifier identifier2 = ((AbstractClientPlayerEntity)entity).getSkin().body().texturePath();
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"texturePath(...)");
        Identifier skinId = identifier2;
        int color = Math.max(0, Math.min(255, MathKt.roundToInt((float)(alpha * 255.0f)))) << 24 | 0xFFFFFF;
        color = TargetHudComp.Companion.tintHurt(color, entity);
        String string = skinId.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        String skinTex = string;
        if (Render2D.imageReady(skinTex)) {
            Render2D.imageUvNearest(skinTex, x, y, size, radius, radius, radius, radius, 0.5f, 0.125f, 0.125f, 0.25f, 0.25f, color);
            float uvSpan = 0.105932206f;
            float uvInset = (0.125f - uvSpan) * 0.5f;
            Render2D.imageUvNearest(skinTex, x, y, size, radius, radius, radius, radius, 0.5f, 0.625f + uvInset, 0.125f + uvInset, 0.75f - uvInset, 0.25f - uvInset, color);
        } else {
            graphics.drawTexture(RenderPipelines.GUI_TEXTURED, skinId, (int)x, (int)y, 8.0f, 8.0f, (int)size, (int)size, 8, 8, 64, 64, color);
            graphics.drawTexture(RenderPipelines.GUI_TEXTURED, skinId, (int)x, (int)y, 40.0f, 8.0f, (int)size, (int)size, 8, 8, 64, 64, color);
        }
    }

    private final void drawArmorCentered(DrawContext graphics, LivingEntity entity, float centerX, float topY, float alpha, float progress) {
        if (TargetHudComp.Companion.armorPieceCount(entity) <= 0) {
            return;
        }
        float nativeSize = 16.0f;
        float scale = 8.0f / nativeSize;
        ArrayList<ItemStack> slots = new ArrayList<ItemStack>(ARMOR_SLOTS.length);
        for (EquipmentSlot slot : ARMOR_SLOTS) {
            slots.add(entity.getEquippedStack(slot));
        }
        float rowW = (float)slots.size() * 8.0f + (float)(slots.size() - 1) * 3.0f;
        float startX = centerX - rowW * 0.5f;
        float a = Math.max(0.0f, Math.min(1.0f, alpha));
        int slotCount = Math.max(1, slots.size() - 1);
        Render2D.beginFrame(graphics);
        int n = ((Collection)slots).size();
        for (int i = 0; i < n; ++i) {
            float start = 0.35f + 0.25f * ((float)i / (float)slotCount);
            float cellT = TargetHudComp.Companion.easeOutCubic((progress - start) / 0.3f);
            if (!(cellT > 0.01f)) continue;
            float cellX = startX + (float)i * 11.0f;
            int cellBg = MathKt.roundToInt((float)((float)36 * a * cellT)) << 24;
            Render2D.rect(cellX - 1.0f, topY - 1.0f, 10.0f, 10.0f, 2.0f * cellT, cellBg);
        }
        Render2D.flush();
        float half = 4.0f;
        int n2 = ((Collection)slots).size();
        for (int i = 0; i < n2; ++i) {
            float start;
            float iconT;
            Object e = slots.get(i);
            Intrinsics.checkNotNullExpressionValue(e, (String)"get(...)");
            ItemStack stack = (ItemStack)e;
            if (stack.isEmpty() || (iconT = TargetHudComp.Companion.clamp01((progress - (start = 0.45f + 0.25f * ((float)i / (float)slotCount))) / 0.3f)) <= 0.01f) continue;
            float animScale = scale * a * TargetHudComp.Companion.easeOutBack(iconT);
            float cellX = startX + (float)i * 11.0f;
            graphics.getMatrices().pushMatrix();
            Matrix3x2fStack matrix3x2fStack = graphics.getMatrices();
            Intrinsics.checkNotNullExpressionValue((Object)matrix3x2fStack, (String)"pose(...)");
            Render2DCoordinateSpace.applyGuiScaleIndependence((Matrix3x2f)matrix3x2fStack);
            graphics.getMatrices().translate(cellX + half, topY + half);
            graphics.getMatrices().scale(animScale, animScale);
            graphics.getMatrices().translate(-8.0f, -8.0f);
            graphics.drawItem(stack, 0, 0);
            graphics.getMatrices().popMatrix();
        }
    }

    private final void updateHitParticles(LivingEntity entity, float centerX, float centerY) {
        int hurtTime;
        if (entity.getId() != this.particleEntityId) {
            this.particleEntityId = entity.getId();
            this.lastParticleHurtTime = entity.hurtTime;
            this.headParticles.clear();
        }
        if ((hurtTime = entity.hurtTime) > this.lastParticleHurtTime && hurtTime > 0) {
            this.spawnHeadParticles(centerX, centerY, 38.5f, 12);
        }
        this.lastParticleHurtTime = hurtTime;
    }

    private final void spawnHeadParticles(float x, float y, float maxDistance, int count) {
        long now = System.currentTimeMillis();
        for (int i = 0; i < count; ++i) {
            float distance = (0.3333f + this.random.nextFloat() * 0.6667f) * maxDistance;
            float radians = (float)Math.toRadians(this.random.nextFloat() * 360.0f);
            float xOff = (float)Math.sin(radians) * distance;
            float yOff = -((float)Math.cos(radians)) * distance;
            long baseLife = Math.max(120L, (long)MathKt.roundToInt((float)(distance / 36.0f * 1000.0f)));
            float jitter = 1.0f + (this.random.nextFloat() * 2.0f - 1.0f) * 0.2f;
            long life = Math.max(120L, (long)MathKt.roundToInt((float)((float)baseLife * jitter)));
            float gradient = count <= 1 ? 0.0f : (float)i / (float)(count - 1);
            this.headParticles.add(new HeadParticle(x, y, xOff, yOff, now, life, gradient));
        }
    }

    private final void drawHitParticles(DrawContext graphics, float alpha) {
        if (this.headParticles.isEmpty()) {
            return;
        }
        long now = System.currentTimeMillis();
        Iterator<HeadParticle> iterator = this.headParticles.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<HeadParticle> it = iterator;
        while (it.hasNext()) {
            HeadParticle p = (HeadParticle) (it.next());
            float progress = p.progress(now);
            if (progress >= 1.0f) {
                it.remove();
                continue;
            }
            float pAlpha = TargetHudComp.Companion.getParticleAlpha(progress) * alpha;
            if (pAlpha <= 0.004f) continue;
            float px = p.getStartX() + p.getOffsetX() * progress;
            float py = p.getStartY() + p.getOffsetY() * progress;
            float radius = 1.0f * (0.85f + (1.0f - progress) * 0.15f);
            int color = ClientAccent.gradientColorAt(p.getGradient(), 255.0f, px, py) & 0xFFFFFF;
            Render2D.circle(px, py, radius, ColorEngine.multAlpha(color, pAlpha));
        }
    }

    static {
        EquipmentSlot[] class_1304Array = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND};
        NEW_SLOTS = class_1304Array;
        class_1304Array = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND};
        ARMOR_SLOTS = class_1304Array;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\t\n\u0002\b\u001c\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u000f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J?\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b \u0010\u001fJ\u000f\u0010!\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b!\u0010\u001fJ\u0011\u0010#\u001a\u0004\u0018\u00010\"H\u0002\u00a2\u0006\u0004\b#\u0010$J\u000f\u0010%\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b%\u0010\u001fJ\u0017\u0010'\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b'\u0010\u001cJO\u00100\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u00042\u0006\u0010/\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b0\u00101J/\u00105\u001a\u0002042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b5\u00106J\u001f\u00109\u001a\u0002042\u0006\u00108\u001a\u0002072\u0006\u0010\u001a\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b9\u0010:J\u0017\u0010<\u001a\u00020\u00042\u0006\u0010;\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b<\u0010\u001cJ'\u0010?\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u00042\u0006\u0010>\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b?\u0010@J\u0017\u0010A\u001a\u00020\u00042\u0006\u0010;\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bA\u0010\u001cJ\u0017\u0010B\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bB\u0010\u001cJ\u0017\u0010C\u001a\u00020\u00042\u0006\u0010;\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bC\u0010\u001cJ\u0017\u0010E\u001a\u00020\u00062\u0006\u0010D\u001a\u00020\tH\u0002\u00a2\u0006\u0004\bE\u0010FJ\u0017\u0010G\u001a\u0002042\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\bG\u0010HJ\u000f\u0010I\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\bI\u0010\u001fJ\u001f\u0010K\u001a\u0002042\u0006\u0010J\u001a\u0002042\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\bK\u0010LJ\u0017\u0010N\u001a\u0002042\u0006\u0010M\u001a\u000204H\u0002\u00a2\u0006\u0004\bN\u0010OJ\u0017\u0010Q\u001a\u00020\u00062\u0006\u0010P\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\bQ\u0010RJ\u0017\u0010T\u001a\u00020S2\u0006\u0010D\u001a\u00020\tH\u0002\u00a2\u0006\u0004\bT\u0010UJ\u001f\u0010W\u001a\u00020\u00062\u0006\u0010P\u001a\u00020\u00062\u0006\u0010V\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\bW\u0010XJ\u001f\u0010\\\u001a\u00020[2\u0006\u0010Y\u001a\u00020S2\u0006\u0010Z\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\\\u0010]J/\u0010a\u001a\u00020\u00042\u0006\u0010_\u001a\u00020^2\u0006\u0010Y\u001a\u00020\u00062\u0006\u0010Z\u001a\u00020\u00042\u0006\u0010`\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\ba\u0010bJ\u0017\u0010d\u001a\u00020[2\u0006\u0010c\u001a\u00020[H\u0002\u00a2\u0006\u0004\bd\u0010eJ\u001f\u0010f\u001a\u00020\u00042\u0006\u0010Y\u001a\u00020S2\u0006\u0010Z\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bf\u0010gJ?\u0010j\u001a\u00020\u00172\u0006\u0010Y\u001a\u00020S2\u0006\u0010h\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020\u00042\u0006\u0010i\u001a\u0002042\u0006\u0010/\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bj\u0010kJ\u0017\u0010l\u001a\u0002042\u0006\u0010;\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bl\u0010mJ\u001f\u0010p\u001a\u0002042\u0006\u0010n\u001a\u0002042\u0006\u0010o\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bp\u0010qJ\u0017\u0010s\u001a\u00020\u00042\u0006\u0010r\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\bs\u0010\u001cJ\u0011\u0010t\u001a\u0004\u0018\u00010\tH\u0002\u00a2\u0006\u0004\bt\u0010uJ\u000f\u0010v\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\bv\u0010\u001fR\u0014\u0010w\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bw\u0010xR\u0014\u0010y\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\by\u0010xR\u0014\u0010z\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bz\u0010xR\u0014\u0010{\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b{\u0010xR\u0014\u0010|\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b|\u0010xR\u0014\u0010}\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b}\u0010xR\u0014\u0010~\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b~\u0010xR\u0014\u0010\u007f\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u007f\u0010xR\u0016\u0010\u0080\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0080\u0001\u0010xR\u0016\u0010\u0081\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0081\u0001\u0010xR\u0018\u0010\u0083\u0001\u001a\u00030\u0082\u00018\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0083\u0001\u0010\u0084\u0001R\u0017\u0010\u0085\u0001\u001a\u0002048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0085\u0001\u0010\u0086\u0001R\u0017\u0010\u0087\u0001\u001a\u0002048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0087\u0001\u0010\u0086\u0001R\u0017\u0010\u0088\u0001\u001a\u0002048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0088\u0001\u0010\u0086\u0001R\u0017\u0010\u0089\u0001\u001a\u0002048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u0089\u0001\u0010\u0086\u0001R\u0016\u0010\u008a\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u008a\u0001\u0010xR\u0016\u0010\u008b\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u008b\u0001\u0010xR\u0016\u0010\u008c\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u008c\u0001\u0010xR\u0016\u0010\u008d\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u008d\u0001\u0010xR\u0016\u0010\u008e\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u008e\u0001\u0010xR\u0016\u0010\u008f\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u008f\u0001\u0010xR\u0016\u0010\u0090\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0090\u0001\u0010xR\u0016\u0010\u0091\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0091\u0001\u0010xR\u0016\u0010\u0092\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0092\u0001\u0010xR\u0016\u0010\u0093\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0093\u0001\u0010xR\u0016\u0010\u0094\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0094\u0001\u0010xR\u0016\u0010\u0095\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0095\u0001\u0010xR\u0016\u0010\u0096\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0096\u0001\u0010xR\u0016\u0010\u0097\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0097\u0001\u0010xR\u0016\u0010\u0098\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0098\u0001\u0010xR\u0016\u0010\u0099\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u0099\u0001\u0010xR\u0016\u0010\u009a\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u009a\u0001\u0010xR\u0016\u0010\u009b\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u009b\u0001\u0010xR\u0016\u0010\u009c\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u009c\u0001\u0010xR\u0016\u0010\u009d\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u009d\u0001\u0010xR\u0016\u0010\u009e\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u009e\u0001\u0010xR\u001f\u0010\u00a1\u0001\u001a\n\u0012\u0005\u0012\u00030\u00a0\u00010\u009f\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a1\u0001\u0010\u00a2\u0001R\u001f\u0010\u00a3\u0001\u001a\n\u0012\u0005\u0012\u00030\u00a0\u00010\u009f\u00018\u0002X\u0082\u0004\u00a2\u0006\b\n\u0006\b\u00a3\u0001\u0010\u00a2\u0001R\u0016\u0010\u00a4\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u00a4\u0001\u0010xR\u0016\u0010\u00a5\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u00a5\u0001\u0010xR\u0016\u0010\u00a6\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u00a6\u0001\u0010xR\u0016\u0010\u00a7\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u00a7\u0001\u0010xR\u0016\u0010\u00a8\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u00a8\u0001\u0010xR\u0016\u0010\u00a9\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u00a9\u0001\u0010xR\u0016\u0010\u00aa\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u00aa\u0001\u0010xR\u0016\u0010«\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b«\u0001\u0010xR\u0016\u0010\u00ac\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u00ac\u0001\u0010xR\u0017\u0010\u00ad\u0001\u001a\u0002048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00ad\u0001\u0010\u0086\u0001R\u0016\u0010\u00ae\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u00ae\u0001\u0010xR\u0016\u0010\u00af\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u00af\u0001\u0010xR\u0016\u0010\u00b0\u0001\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0007\n\u0005\b\u00b0\u0001\u0010xR\u0017\u0010\u00b1\u0001\u001a\u0002048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00b1\u0001\u0010\u0086\u0001R\u0017\u0010\u00b2\u0001\u001a\u0002048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00b2\u0001\u0010\u0086\u0001R\u0017\u0010\u00b3\u0001\u001a\u0002048\u0002X\u0082T\u00a2\u0006\b\n\u0006\b\u00b3\u0001\u0010\u0086\u0001\u00a8\u0006\u00b4\u0001"}, d2={"Lrtx/kimiko/api/drags/components/TargetHudComp.Companion;", "", "<init>", "()V", "", "health", "", "formatHp", "(F)Ljava/lang/String;", "Lnet/minecraft/LivingEntity;", "entity", "Lnet/minecraft/ItemStack;", "activeConsumable", "(Lnet/minecraft/LivingEntity;)Lnet/minecraft/ItemStack;", "stack", "useProgress", "(Lnet/minecraft/LivingEntity;Lnet/minecraft/ItemStack;)F", "cx", "cy", "rad", "thickness", "endDeg", "alpha255", "", "drawUseRing", "(FFFFFF)V", "t", "pingpong", "(F)F", "", "useItemEnabled", "()Z", "isCircleMode", "isNewMode", "Lrtx/kimiko/api/modules/impl/Interface/TargetHudModule;", "hudModule", "()Lrtx/kimiko/api/modules/impl/Interface/TargetHudModule;", "followEnabled", "barH", "barRadius", "x", "y", "w", "trackW", "h", "radius", "ratio", "alpha", "drawBarFill", "(FFFFFFFF)V", "sampleX", "sampleY", "", "barFillColorAt", "(FFFF)I", "", "palette", "sampleBarPalette", "([IF)I", "value", "clamp01", "a", "b", "lerp", "(FFF)F", "easeInOutCubic", "easeOutCubic", "easeOutBack", "target", "distanceText", "(Lnet/minecraft/LivingEntity;)Ljava/lang/String;", "armorPieceCount", "(Lnet/minecraft/LivingEntity;)I", "armorEnabled", "color", "tintHurt", "(ILnet/minecraft/LivingEntity;)I", "v", "clamp255", "(I)I", "raw", "stripCodes", "(Ljava/lang/String;)Ljava/lang/String;", "Lnet/minecraft/Text;", "resolveName", "(Lnet/minecraft/LivingEntity;)Lnet/minecraft/Text;", "stylized", "normalize", "(Ljava/lang/String;Z)Ljava/lang/String;", "text", "size", "", "coloredBounds", "(Lnet/minecraft/Text;F)[F", "Lrtx/kimiko/utils/render/fonts/Fonts;", "font", "centerX", "inkLeft", "(Lrtx/kimiko/utils/render/fonts/Fonts;Ljava/lang/String;FF)F", "bounds", "safeBounds", "([F)[F", "coloredWidth", "(Lnet/minecraft/Text;F)F", "leftX", "defaultColor", "drawColoredName", "(Lnet/minecraft/Text;FFFIF)V", "healthColor", "(F)I", "rgb", "factor", "darken", "(IF)I", "progress", "getParticleAlpha", "hoveredTarget", "()Lnet/minecraft/LivingEntity;", "componentEnabled", "PAD", "F", "HEAD", "HAT_SCALE", "GAP", "BAR_H", "NAME_SIZE", "INFO_SIZE", "RADIUS", "MIN_CONTENT_W", "HEIGHT", "", "HOLD_MS", "J", "NAME_COLOR", "I", "INFO_COLOR", "BAR_BG_COLOR", "BAR_BG_ALPHA", "CIRCLE_HEAD", "CIRCLE_HEIGHT", "CIRCLE_MIN_CONTENT", "CIRCLE_TEXT_GAP", "CIRCLE_RING_RADIUS", "CIRCLE_RING_THICKNESS", "CIRCLE_HP_SIZE", "HP_ROLL_SECONDS", "NEW_PAD", "NEW_GAP", "NEW_HEAD", "NEW_HEAD_RADIUS", "NEW_ICON", "NEW_ICON_GAP", "NEW_HEAD_ITEMS_GAP", "NEW_BAR_H", "NEW_BAR_RADIUS", "NEW_MIN_CONTENT", "NEW_TEXT_EDGE_PAD", "NEW_BOTTOM_PAD", "NEW_HEIGHT", "", "Lnet/minecraft/EquipmentSlot;", "NEW_SLOTS", "[Lnet/minecraft/EquipmentSlot;", "ARMOR_SLOTS", "ARMOR_SCALE", "ARMOR_ICON", "ARMOR_GAP", "CLASSIC_ARMOR_EXTRA", "NEW_ARMOR_COLLAPSE", "USE_SIZE", "USE_GAP", "USE_ICON", "USE_RING_THICK", "PARTICLE_COUNT", "PARTICLE_MOVE_SPEED", "PARTICLE_LIFETIME_JITTER", "PARTICLE_FADE_IN", "HP_GREEN", "HP_ORANGE", "HP_RED", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final String formatHp(float health) {
            if (health <= 0.0f) {
                return "0";
            }
            return String.valueOf(Math.max(1, MathKt.roundToInt((float)health)));
        }

        private final ItemStack activeConsumable(LivingEntity entity) {
            if (!entity.isUsingItem()) {
                ItemStack itemStack2 = ItemStack.EMPTY;
                Intrinsics.checkNotNullExpressionValue((Object)itemStack2, (String)"EMPTY");
                return itemStack2;
            }
            ItemStack itemStack3 = entity.getActiveItem();
            Intrinsics.checkNotNullExpressionValue((Object)itemStack3, (String)"getUseItem(...)");
            ItemStack stack = itemStack3;
            if (stack.isEmpty()) {
                ItemStack itemStack4 = ItemStack.EMPTY;
                Intrinsics.checkNotNullExpressionValue((Object)itemStack4, (String)"EMPTY");
                return itemStack4;
            }
            UseAction useAction2 = stack.getUseAction();
            Intrinsics.checkNotNullExpressionValue((Object)useAction2, (String)"getUseAnimation(...)");
            UseAction anim = useAction2;
            if (anim != UseAction.EAT && anim != UseAction.DRINK) {
                ItemStack itemStack5 = ItemStack.EMPTY;
                Intrinsics.checkNotNullExpressionValue((Object)itemStack5, (String)"EMPTY");
                return itemStack5;
            }
            return stack;
        }

        private final float useProgress(LivingEntity entity, ItemStack stack) {
            int duration = stack.getMaxUseTime(entity);
            if (duration <= 0) {
                return 0.0f;
            }
            return this.clamp01((float)(duration - entity.getItemUseTimeLeft()) / (float)duration);
        }

        private final void drawUseRing(float cx, float cy, float rad, float thickness, float endDeg, float alpha255) {
            if (endDeg < 1.0f) {
                return;
            }
            int stops = 16;
            float feather = Math.min(14.0f, endDeg * 0.5f);
            boolean fullCircle = endDeg >= 359.999f;
            float seamOffset = fullCircle ? 360.0f / (float)stops * 0.5f : 0.0f;
            ArrayList<Outline360Range> ranges = new ArrayList<Outline360Range>(stops);
            for (int i = 0; i < stops; ++i) {
                float a0 = seamOffset + endDeg * (float)i / (float)stops;
                float a1 = seamOffset + endDeg * (float)(i + 1) / (float)stops;
                int colA = ClientAccent.gradientColorAt(this.pingpong((float)i / (float)stops), alpha255, cx, cy);
                int colB = ClientAccent.gradientColorAt(this.pingpong((float)(i + 1) / (float)stops), alpha255, cx, cy);
                float blendIn = !fullCircle && i == 0 ? feather : 0.0f;
                float blendOut = !fullCircle && i == stops - 1 ? feather : 0.0f;
                ranges.add(Outline360Range.Companion.gradient(a0, a1, colA, colB, blendIn, blendOut));
            }
            int defaultColor = fullCircle ? ClientAccent.accentAt(alpha255, cx, cy) : ClientAccent.accentAt(1.0f, cx, cy);
            Render2D.outline360(cx - rad, cy - rad, rad * 2.0f, rad * 2.0f, rad, thickness, defaultColor, (List<Outline360Range>)ranges);
        }

        private final float pingpong(float t) {
            float f = t * 2.0f;
            return f <= 1.0f ? f : 2.0f - f;
        }

        private final boolean useItemEnabled() {
            TargetHudModule module = this.hudModule();
            return module != null && module.showUseItem();
        }

        private final boolean isCircleMode() {
            TargetHudModule module = this.hudModule();
            return module != null && module.isCircleMode();
        }

        private final boolean isNewMode() {
            TargetHudModule module = this.hudModule();
            return module != null && module.isNewMode();
        }

        private final TargetHudModule hudModule() {
            return ModuleManager.Companion.get().get(TargetHudModule.class);
        }

        private final boolean followEnabled() {
            TargetHudModule module = this.hudModule();
            return module != null && module.followTarget();
        }

        private final float barRadius(float barH) {
            InterfaceModule im;
            InterfaceModule interfaceModule = im = InterfaceModule.Companion.getInstance();
            float r = interfaceModule == null ? barH * 0.5f : interfaceModule.rectCornerRadius.getFloat();
            return Math.max(0.0f, Math.min(r, barH * 0.5f));
        }

        private final void drawBarFill(float x, float y, float w, float trackW, float h, float radius, float ratio, float alpha) {
            if (w <= 0.5f) {
                return;
            }
            float cornerR = Math.min(radius, h * 0.5f);
            float lensSmooth = -0.7f;
            float a = this.clamp01(alpha);
            int aCol = MathKt.roundToInt((float)((float)235 * a)) << 24;
            TargetHudModule module = this.hudModule();
            if (module != null && module.barWhite()) {
                int c = MathKt.roundToInt((float)((float)215 * a)) << 24 | 0xD7D7D7;
                Render2D.rect(new BuiltRectangle(x, y, w, h, cornerR, c).withSmoothness(lensSmooth));
                return;
            }
            if (module != null && module.barClient()) {
                Render2D.rect(new BuiltRectangle(x, y, w, h, cornerR, -1).withPaletteGradient(3, 1.0f, this.clamp01(235.0f * a / 255.0f)).withSmoothness(lensSmooth));
                return;
            }
            int base = this.healthColor(ratio) & 0xFFFFFF;
            int left = aCol | this.darken(base, 0.45f);
            int right = aCol | base;
            Render2D.rect(new BuiltRectangle(x, y, w, h, cornerR, cornerR, cornerR, cornerR, left, right, right, left, lensSmooth));
        }

        private final int barFillColorAt(float t, float ratio, float sampleX, float sampleY) {
            TargetHudModule module = this.hudModule();
            if (module != null && module.barWhite()) {
                return -2631721;
            }
            if (module != null && module.barClient()) {
                return 0xFF000000 | ClientPalette.loopColorAt(this.clamp01(t) + ClientPalette.scrollPhase(), sampleX, sampleY) & 0xFFFFFF;
            }
            int base = this.healthColor(ratio) & 0xFFFFFF;
            return 0xFF000000 | ColorEngine.lerpColor(this.darken(base, 0.45f), base, this.clamp01(t));
        }

        private final int sampleBarPalette(int[] palette, float t) {
            int n = palette.length;
            if (n <= 1) {
                return n == 1 ? palette[0] : 0xFFFFFF;
            }
            float f = this.clamp01(t) * (float)(n - 1);
            int i = (int)f;
            if (i > n - 2) {
                i = n - 2;
            }
            return ColorEngine.lerpColor(palette[i] | 0xFF000000, palette[i + 1] | 0xFF000000, f - (float)i);
        }

        private final float clamp01(float value) {
            return Math.max(0.0f, Math.min(1.0f, value));
        }

        private final float lerp(float a, float b, float t) {
            return a + (b - a) * this.clamp01(t);
        }

        private final float easeInOutCubic(float value) {
            float t = this.clamp01(value);
            return t < 0.5f ? 4.0f * t * t * t : 1.0f - (float)Math.pow(-2.0f * t + 2.0f, 3.0) / 2.0f;
        }

        private final float easeOutCubic(float t) {
            float inv = 1.0f - this.clamp01(t);
            return 1.0f - inv * inv * inv;
        }

        private final float easeOutBack(float value) {
            float t = this.clamp01(value);
            float c1 = 1.70158f;
            float c3 = c1 + 1.0f;
            float m = t - 1.0f;
            return 1.0f + c3 * m * m * m + c1 * m * m;
        }

        private final String distanceText(LivingEntity target) {
            ClientPlayerEntity self;
            ClientPlayerEntity clientPlayerEntity2 = self = MinecraftClient.getInstance().player;
            float dist = clientPlayerEntity2 != null ? clientPlayerEntity2.distanceTo((Entity)target) : 0.0f;
            return String.valueOf(MathKt.roundToInt((float)dist));
        }

        private final int armorPieceCount(LivingEntity entity) {
            if (!this.armorEnabled()) {
                return 0;
            }
            int count = 0;
            for (EquipmentSlot slot : ARMOR_SLOTS) {
                if (entity.getEquippedStack(slot).isEmpty()) continue;
                ++count;
            }
            return count;
        }

        private final boolean armorEnabled() {
            TargetHudModule module = ModuleManager.Companion.get().get(TargetHudModule.class);
            return module != null && module.showArmor();
        }

        private final int tintHurt(int color, LivingEntity entity) {
            int hurtTime = entity.hurtTime;
            if (hurtTime <= 0) {
                return color;
            }
            float intensity = Math.min(1.0f, (float)hurtTime / 10.0f);
            int a = color >>> 24;
            int r = color >> 16 & 0xFF;
            int g = color >> 8 & 0xFF;
            int b = color & 0xFF;
            r = MathKt.roundToInt((float)((float)r + (float)(255 - r) * intensity * 0.35f));
            g = MathKt.roundToInt((float)((float)g * (1.0f - intensity * 0.65f)));
            b = MathKt.roundToInt((float)((float)b * (1.0f - intensity * 0.65f)));
            return a << 24 | this.clamp255(r) << 16 | this.clamp255(g) << 8 | this.clamp255(b);
        }

        private final int clamp255(int v) {
            return v < 0 ? 0 : Math.min(v, 255);
        }

        private final String stripCodes(String raw) {
            String s = Formatting.strip((String)raw);
            String string = s;
            if (string == null) {
                string = "";
            }
            return string;
        }

        private final Text resolveName(LivingEntity target) {
            Text stripped;
            Text resolved;
            if (target instanceof PlayerEntity && (resolved = NameTags.Companion.resolveDisplayName((PlayerEntity)target)) != null && (stripped = ReallyWorldRanks.stripGlyphs(resolved)) != null) {
                return stripped;
            }
            MutableText mutableText2 = Text.literal((String)target.getName().getString());
            Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"literal(...)");
            return (Text)mutableText2;
        }

        private final String normalize(String raw, boolean stylized) {
            StringBuilder sb = new StringBuilder();
            raw.codePoints().forEach(arg_0 -> Companion.normalize$lambda$0(sb, stylized, arg_0));
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
            return string;
        }

        private final float[] coloredBounds(Text text, float size) {
            boolean stylized = NameTags.Companion.isStylized(text.getString());
            String string = text.getString();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getString(...)");
            return this.safeBounds(Fonts.SEMIBOLD.msdfBounds(this.normalize(this.stripCodes(string), stylized), size));
        }

        private final float inkLeft(Fonts font, String text, float size, float centerX) {
            float[] bounds = this.safeBounds(font.msdfBounds(text, size));
            return centerX - (bounds[0] + bounds[2]) * 0.5f;
        }

        private final float[] safeBounds(float[] bounds) {
            float[] fArray;
            if (bounds.length >= 4) {
                fArray = bounds;
            } else {
                float[] fArray2 = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
                fArray = fArray2;
            }
            return fArray;
        }

        private final float coloredWidth(Text text, float size) {
            boolean stylized = NameTags.Companion.isStylized(text.getString());
            float[] fArray = new float[]{0.0f};
            float[] width = fArray;
            text.visit((arg_0, arg_1) -> Companion.coloredWidth$lambda$0(width, stylized, size, arg_0, arg_1), Style.EMPTY);
            return width[0];
        }

        private final void drawColoredName(Text text, float leftX, float y, float size, int defaultColor, float alpha) {
            boolean stylized = NameTags.Companion.isStylized(text.getString());
            StringBuilder run = new StringBuilder();
            float[] fArray = new float[]{leftX};
            float[] cursor = fArray;
            int[] nArray = new int[]{defaultColor};
            int[] cur = nArray;
            text.visit((arg_0, arg_1) -> Companion.drawColoredName$lambda$0(defaultColor, cur, run, cursor, y, size, alpha, stylized, arg_0, arg_1), Style.EMPTY);
            if (((CharSequence)run).length() > 0) {
                Fonts.SEMIBOLD.msdf(run.toString(), cursor[0], y, size, ColorEngine.multAlpha(cur[0], alpha));
            }
        }

        private final int healthColor(float value) {
            float ratio = Math.max(0.0f, Math.min(1.0f, value));
            if (ratio >= 0.8f) {
                return 3530826;
            }
            if (ratio >= 0.4f) {
                float t = (ratio - 0.4f) / 0.4f;
                return ColorEngine.lerpColor(16751136, 3530826, t);
            }
            float t = ratio / 0.4f;
            return ColorEngine.lerpColor(16722480, 16751136, t);
        }

        private final int darken(int rgb, float factor) {
            float keep = 1.0f - Math.max(0.0f, Math.min(1.0f, factor));
            int r = MathKt.roundToInt((float)((float)(rgb >> 16 & 0xFF) * keep));
            int g = MathKt.roundToInt((float)((float)(rgb >> 8 & 0xFF) * keep));
            int b = MathKt.roundToInt((float)((float)(rgb & 0xFF) * keep));
            return this.clamp255(r) << 16 | this.clamp255(g) << 8 | this.clamp255(b);
        }

        private final float getParticleAlpha(float progress) {
            float clamped = Math.max(0.0f, Math.min(1.0f, progress));
            if (clamped <= 0.15f) {
                return clamped / 0.15f;
            }
            return Math.max(0.0f, Math.min(1.0f, 1.0f - (clamped - 0.15f) / 0.85f));
        }

        private final LivingEntity hoveredTarget() {
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient mc = minecraftClient2;
            HitResult hitResult2 = mc.crosshairTarget;
            EntityHitResult entityHitResult2 = hitResult2 instanceof EntityHitResult ? (EntityHitResult)hitResult2 : null;
            if (entityHitResult2 == null) {
                return null;
            }
            EntityHitResult hit = entityHitResult2;
            Entity entity2 = hit.getEntity();
            Intrinsics.checkNotNullExpressionValue((Object)entity2, (String)"getEntity(...)");
            Entity entity = entity2;
            if (!(entity instanceof LivingEntity) || entity == mc.player) {
                return null;
            }
            if (!((LivingEntity)entity).isAlive() || ((LivingEntity)entity).isRemoved() || ((LivingEntity)entity).getHealth() <= 0.0f) {
                return null;
            }
            if (EntityVisibility.isHidden(entity)) {
                return null;
            }
            return (LivingEntity)entity;
        }

        private final boolean componentEnabled() {
            TargetHudModule module = ModuleManager.Companion.get().get(TargetHudModule.class);
            return module != null && module.isEnabled();
        }

        private static final void normalize$lambda$0(StringBuilder $sb, boolean $stylized, int cp) {
            $sb.append(NameTags.Companion.displayGlyphCovered(cp, $stylized));
        }

        private static final Optional coloredWidth$lambda$0(float[] $width, boolean $stylized, float $size, Style style2, String str) {
            Intrinsics.checkNotNullParameter((Object)style2, (String)"<unused var>");
            Intrinsics.checkNotNullParameter((Object)str, (String)"str");
            $width[0] = $width[0] + Fonts.SEMIBOLD.msdfWidth(Companion.normalize(Companion.stripCodes(str), $stylized), $size);
            return Optional.empty();
        }

        private static final boolean drawColoredName$lambda$0$0(int $defaultColor, int[] $cur, StringBuilder $run, float[] $cursor, float $y, float $size, float $alpha, boolean $stylized, int n, Style charStyle, int cp) {
            int col;
            Intrinsics.checkNotNullParameter((Object)charStyle, (String)"charStyle");
            if (charStyle.getColor() != null) {
                TextColor textColor2 = charStyle.getColor();
                Intrinsics.checkNotNull((Object)textColor2);
                col = 0xFF000000 | textColor2.getRgb();
            } else {
                col = $defaultColor;
            }
            if (col != $cur[0] && ((CharSequence)$run).length() > 0) {
                String string = $run.toString();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
                String s = string;
                Fonts.SEMIBOLD.msdf(s, $cursor[0], $y, $size, ColorEngine.multAlpha($cur[0], $alpha));
                $cursor[0] = $cursor[0] + Fonts.SEMIBOLD.msdfWidth(s, $size);
                $run.setLength(0);
            }
            $cur[0] = col;
            $run.append(NameTags.Companion.displayGlyphCovered(cp, $stylized));
            return true;
        }

        private static final Optional drawColoredName$lambda$0(int $defaultColor, int[] $cur, StringBuilder $run, float[] $cursor, float $y, float $size, float $alpha, boolean $stylized, Style style, String str) {
            Intrinsics.checkNotNullParameter((Object)style, (String)"style");
            Intrinsics.checkNotNullParameter((Object)str, (String)"str");
            TextVisitFactory.visitFormatted((String)str, (Style)style, (arg_0, arg_1, arg_2) -> Companion.drawColoredName$lambda$0$0($defaultColor, $cur, $run, $cursor, $y, $size, $alpha, $stylized, arg_0, arg_1, arg_2));
            return Optional.empty();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0014\b\u0002\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000e\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0007\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0010\u001a\u0004\b\u0013\u0010\u0012R\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0010\u001a\u0004\b\u0015\u0010\u0012R\u0017\u0010\b\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\t\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0016\u001a\u0004\b\u0019\u0010\u0018R\u0017\u0010\n\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\n\u0010\u0010\u001a\u0004\b\u001a\u0010\u0012\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/api/drags/components/TargetHudComp$HeadParticle;", "", "", "startX", "startY", "offsetX", "offsetY", "", "startMs", "lifeMs", "gradient", "<init>", "(FFFFJJF)V", "now", "progress", "(J)F", "F", "getStartX", "()F", "getStartY", "getOffsetX", "getOffsetY", "J", "getStartMs", "()J", "getLifeMs", "getGradient", "rtx.kimiko:kimiko"})
    private static final class HeadParticle {
        private final float startX;
        private final float startY;
        private final float offsetX;
        private final float offsetY;
        private final long startMs;
        private final long lifeMs;
        private final float gradient;

        public HeadParticle(float startX, float startY, float offsetX, float offsetY, long startMs, long lifeMs, float gradient) {
            this.startX = startX;
            this.startY = startY;
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.startMs = startMs;
            this.lifeMs = lifeMs;
            this.gradient = gradient;
        }

        public final float getStartX() {
            return this.startX;
        }

        public final float getStartY() {
            return this.startY;
        }

        public final float getOffsetX() {
            return this.offsetX;
        }

        public final float getOffsetY() {
            return this.offsetY;
        }

        public final long getStartMs() {
            return this.startMs;
        }

        public final long getLifeMs() {
            return this.lifeMs;
        }

        public final float getGradient() {
            return this.gradient;
        }

        public final float progress(long now) {
            return Math.max(0.0f, Math.min(1.0f, (float)(now - this.startMs) / (float)this.lifeMs));
        }
    }
}

