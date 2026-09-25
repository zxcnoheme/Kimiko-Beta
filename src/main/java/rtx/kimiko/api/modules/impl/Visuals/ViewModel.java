/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.mojang.blaze3d.systems.RenderSystem
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.util.Window
 *  net.minecraft.util.Hand
 *  net.minecraft.util.Arm
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.screen.ChatScreen
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Vector4f
 */
package rtx.kimiko.api.modules.impl.Visuals;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.systems.RenderSystem;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.util.Window;
import net.minecraft.util.Hand;
import net.minecraft.util.Arm;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector4f;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.input.HotBarScrollEvent;
import rtx.kimiko.api.events.impl.input.MouseButtonEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.utils.animations.Easings;
import rtx.kimiko.utils.animations.SmoothAnimation;
import rtx.kimiko.utils.render.modules.post.shaderhands.ShaderHandsRenderer;
import rtx.kimiko.utils.storage.RepositoryStorage;

@Feature(value={"viewmodel"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 Q2\u00020\u0001:\u0001QB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\u0006\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u000f\u0010\u0007\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u0007\u0010\u0003J\r\u0010\b\u001a\u00020\u0004\u00a2\u0006\u0004\b\b\u0010\u0003J\u001b\u0010\f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0003b\u0002\b\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000eH\u0003b\u0002\b\u000b\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0012\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0011H\u0003b\u0002\b\u000b\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u0019J\u001f\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001bH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b \u0010\u0003R\u0016\u0010!\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010#\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010\"R\u0016\u0010$\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010\"R\u0016\u0010%\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u0010\"R\u0016\u0010&\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010\"R\u0016\u0010'\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b'\u0010\"R\u0016\u0010(\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010\"R\u0016\u0010)\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010\"R\u0016\u0010*\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010\"R\u0016\u0010+\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u0010\"R\u0014\u0010-\u001a\u00020,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u0014\u0010/\u001a\u00020,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u0010.R\u0016\u00101\u001a\u0002008\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b1\u00102R\u0018\u00103\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u00104R\u0016\u00106\u001a\u0002058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b6\u00107R\u0016\u00108\u001a\u0002008\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b8\u00102R\u0016\u00109\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b9\u0010\"R\u0016\u0010:\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b:\u0010\"R\u0016\u0010;\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b;\u0010\"R\u0016\u0010<\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u0010\"R\u0016\u0010=\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b=\u0010\"R\u0016\u0010>\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010\"R\u0016\u0010?\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b?\u0010\"R\u0016\u0010@\u001a\u0002008\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b@\u00102R\u0018\u0010B\u001a\u0004\u0018\u00010A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0018\u0010D\u001a\u0004\u0018\u00010A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bD\u0010CR\u0016\u0010E\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bE\u0010\"R\u0016\u0010F\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bF\u0010\"R\u0016\u0010G\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bG\u0010\"R\u0016\u0010H\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bH\u0010\"R\u0016\u0010I\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bI\u0010\"R\u0016\u0010J\u001a\u0002008\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bJ\u00102R\u0016\u0010K\u001a\u0002008\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u00102R\u0018\u0010M\u001a\u0004\u0018\u00010L8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bM\u0010NR\u0014\u0010O\u001a\u00020,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bO\u0010.R\u0018\u0010P\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bP\u00104\u00ca\u0001\u0010\bR\u0012\f\bS\u0012\b\b\fJ\u0004\b\b(T\u00a8\u0006U"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ViewModel;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "load", "save", "onDisable", "resetLayout", "Lrtx/kimiko/api/events/impl/input/MouseButtonEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onMouse", "(Lrtx/kimiko/api/events/impl/input/MouseButtonEvent;)V", "Lrtx/kimiko/api/events/impl/input/HotBarScrollEvent;", "onScroll", "(Lrtx/kimiko/api/events/impl/input/HotBarScrollEvent;)V", "Lrtx/kimiko/api/events/impl/render/HudRenderEvent;", "onHud", "(Lrtx/kimiko/api/events/impl/render/HudRenderEvent;)V", "Lnet/minecraft/MatrixStack;", "matrices", "Lnet/minecraft/Hand;", "hand", "captureTransform", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/Hand;)V", "captureScalePivot", "", "mx", "my", "pickGrabbedHand", "(FF)Lnet/minecraft/Hand;", "handleMainHandSwap", "mainOffsetX", "F", "mainOffsetY", "offOffsetX", "offOffsetY", "mainTargetX", "mainTargetY", "offTargetX", "offTargetY", "mainScaleTarget", "offScaleTarget", "Lrtx/kimiko/utils/animations/SmoothAnimation;", "mainScaleAnim", "Lrtx/kimiko/utils/animations/SmoothAnimation;", "offScaleAnim", "", "loaded", "Z", "activeHand", "Lnet/minecraft/Hand;", "", "lastClickMs", "J", "holding", "grabCursorX", "grabCursorY", "grabOffsetX", "grabOffsetY", "grabScale", "grabPivotX", "grabPivotY", "grabPivotValid", "Lorg/joml/Matrix4f;", "mainA", "Lorg/joml/Matrix4f;", "offA", "grabDepth", "mainPivotX", "mainPivotY", "offPivotX", "offPivotY", "mainPivotValid", "offPivotValid", "Lnet/minecraft/Arm;", "lastMainArm", "Lnet/minecraft/Arm;", "outlineAnim", "outlineHand", "Companion", "Lrtx/kimiko/api/liteapi/Feature;", "value", "viewmodel", "rtx.kimiko:kimiko"})
public final class ViewModel
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private float mainOffsetX;
    private float mainOffsetY;
    private float offOffsetX;
    private float offOffsetY;
    private float mainTargetX;
    private float mainTargetY;
    private float offTargetX;
    private float offTargetY;
    private float mainScaleTarget = 1.0f;
    private float offScaleTarget = 1.0f;
    @NotNull
    private final SmoothAnimation mainScaleAnim = new SmoothAnimation();
    @NotNull
    private final SmoothAnimation offScaleAnim = new SmoothAnimation();
    private boolean loaded;
    @Nullable
    private Hand activeHand;
    private long lastClickMs;
    private boolean holding;
    private float grabCursorX;
    private float grabCursorY;
    private float grabOffsetX;
    private float grabOffsetY;
    private float grabScale = 1.0f;
    private float grabPivotX;
    private float grabPivotY;
    private boolean grabPivotValid;
    @Nullable
    private Matrix4f mainA;
    @Nullable
    private Matrix4f offA;
    private float grabDepth = 0.72f;
    private float mainPivotX;
    private float mainPivotY;
    private float offPivotX;
    private float offPivotY;
    private boolean mainPivotValid;
    private boolean offPivotValid;
    @Nullable
    private Arm lastMainArm;
    @NotNull
    private final SmoothAnimation outlineAnim = new SmoothAnimation();
    @Nullable
    private Hand outlineHand;
    private static final double SCALE_ANIM_SECONDS = 0.2;
    private static final float FALLBACK_DEPTH = 0.72f;
    private static final double OUTLINE_IN_SECONDS = 0.22;
    private static final double OUTLINE_OUT_SECONDS = 0.3;
    private static final float DRAG_UNITS_PER_PIXEL = 3.2f;
    private static final float SCALE_STEP = 0.22f;
    private static final float SCALE_MIN = 0.2f;
    private static final float SCALE_MAX = 5.0f;
    private static final float FOLLOW = 0.45f;
    private static final long DOUBLE_CLICK_MS = 300L;
    @NotNull
    private static final String SAVE_FILE = "viewmodel";
    private static final float CENTROID_X = 0.0f;
    private static final float CENTROID_Y = 0.0f;
    private static final float CENTROID_Z = 0.0f;
    @JvmField
    @Nullable
    public static ViewModel INSTANCE;

    public ViewModel() {
        super("View Model", "Дважды кликните по руке в чате, чтобы перетащить её, и колёсиком измените размер.", Category.VISUALS);
        INSTANCE = this;
        this.outlineAnim.set(0.0);
        this.mainScaleAnim.set(1.0);
        this.offScaleAnim.set(1.0);
    }

    private final void load() {
        if (this.loaded) {
            return;
        }
        this.loaded = true;
        JsonObject jsonObject = RepositoryStorage.readObject(SAVE_FILE);
        if (jsonObject == null) {
            return;
        }
        JsonObject root = jsonObject;
        this.mainOffsetX = this.mainTargetX = ViewModel.Companion.readFloat(root, "mainX", 0.0f);
        this.mainOffsetY = this.mainTargetY = ViewModel.Companion.readFloat(root, "mainY", 0.0f);
        this.offOffsetX = this.offTargetX = ViewModel.Companion.readFloat(root, "offX", 0.0f);
        this.offOffsetY = this.offTargetY = ViewModel.Companion.readFloat(root, "offY", 0.0f);
        this.mainScaleTarget = ViewModel.Companion.clamp(ViewModel.Companion.readFloat(root, "mainScale", 1.0f), 0.2f, 5.0f);
        this.offScaleTarget = ViewModel.Companion.clamp(ViewModel.Companion.readFloat(root, "offScale", 1.0f), 0.2f, 5.0f);
        this.mainScaleAnim.set(this.mainScaleTarget);
        this.offScaleAnim.set(this.offScaleTarget);
    }

    private final void save() {
        JsonObject root = new JsonObject();
        root.addProperty("mainX", (Number)Float.valueOf(this.mainTargetX));
        root.addProperty("mainY", (Number)Float.valueOf(this.mainTargetY));
        root.addProperty("offX", (Number)Float.valueOf(this.offTargetX));
        root.addProperty("offY", (Number)Float.valueOf(this.offTargetY));
        root.addProperty("mainScale", (Number)Float.valueOf(this.mainScaleTarget));
        root.addProperty("offScale", (Number)Float.valueOf(this.offScaleTarget));
        RepositoryStorage.write(SAVE_FILE, root);
    }

    @Override
    protected void onDisable() {
        this.activeHand = null;
        this.holding = false;
    }

    public final void resetLayout() {
        this.loaded = true;
        this.holding = false;
        this.activeHand = null;
        this.mainOffsetY = 0.0f;
        this.mainOffsetX = 0.0f;
        this.mainTargetY = 0.0f;
        this.mainTargetX = 0.0f;
        this.offOffsetY = 0.0f;
        this.offOffsetX = 0.0f;
        this.offTargetY = 0.0f;
        this.offTargetX = 0.0f;
        this.offScaleTarget = 1.0f;
        this.mainScaleTarget = 1.0f;
        SmoothAnimation.run$default(this.mainScaleAnim, 1.0, 0.2, Easings.CUBIC_OUT, false, 8, null);
        SmoothAnimation.run$default(this.offScaleAnim, 1.0, 0.2, Easings.CUBIC_OUT, false, 8, null);
        this.save();
    }

    @EventHandler
    private final void onMouse(MouseButtonEvent event) {
        if (!this.isEnabled() || !Companion.inChat() || event.button != 0) {
            return;
        }
        if (event.action == MouseButtonEvent.Action.PRESS) {
            long now = System.currentTimeMillis();
            boolean doubleClick = now - this.lastClickMs <= 300L;
            this.lastClickMs = now;
            if (!doubleClick) {
                return;
            }
            if (!ShaderHandsRenderer.isHandCoveredAt(Position.Companion.mouseX(), Position.Companion.mouseY(), Position.Companion.screenWidth(), Position.Companion.screenHeight())) {
                return;
            }
            this.activeHand = this.pickGrabbedHand(Position.Companion.mouseX(), Position.Companion.mouseY());
            this.holding = true;
            boolean main = this.activeHand == Hand.MAIN_HAND;
            this.grabCursorX = Position.Companion.mouseX();
            this.grabCursorY = Position.Companion.mouseY();
            this.grabOffsetX = main ? this.mainTargetX : this.offTargetX;
            this.grabOffsetY = main ? this.mainTargetY : this.offTargetY;
            float depth = ShaderHandsRenderer.handDepthAt(this.grabCursorX, this.grabCursorY, Position.Companion.screenWidth(), Position.Companion.screenHeight());
            this.grabDepth = depth > 0.0f ? depth : 0.72f;
            this.grabScale = main ? this.mainScaleTarget : this.offScaleTarget;
            this.grabPivotValid = main ? this.mainPivotValid : this.offPivotValid;
            this.grabPivotX = main ? this.mainPivotX : this.offPivotX;
            this.grabPivotY = main ? this.mainPivotY : this.offPivotY;
            event.cancel();
        } else if (event.action == MouseButtonEvent.Action.RELEASE) {
            this.holding = false;
            this.save();
        }
    }

    @EventHandler
    private final void onScroll(HotBarScrollEvent event) {
        float delta;
        if (!this.isEnabled() || !Companion.inChat() || this.activeHand == null || event.getVertical() == 0.0) {
            return;
        }
        float f = delta = event.getVertical() > 0.0 ? 0.22f : -0.22f;
        if (this.activeHand == Hand.MAIN_HAND) {
            this.mainScaleTarget = ViewModel.Companion.clamp(this.mainScaleTarget + delta, 0.2f, 5.0f);
            SmoothAnimation.run$default(this.mainScaleAnim, this.mainScaleTarget, 0.2, Easings.CUBIC_OUT, false, 8, null);
        } else {
            this.offScaleTarget = ViewModel.Companion.clamp(this.offScaleTarget + delta, 0.2f, 5.0f);
            SmoothAnimation.run$default(this.offScaleAnim, this.offScaleTarget, 0.2, Easings.CUBIC_OUT, false, 8, null);
        }
        this.save();
        event.cancel();
    }

    @EventHandler
    private final void onHud(HudRenderEvent event) {
        boolean show;
        boolean active;
        this.load();
        this.handleMainHandSwap();
        boolean bl = active = this.isEnabled() && Companion.inChat();
        if (!active) {
            this.activeHand = null;
            this.holding = false;
        }
        boolean bl2 = show = active && this.holding && this.activeHand != null;
        if (show) {
            this.outlineHand = this.activeHand;
        }
        this.outlineAnim.run(show ? 1.0 : 0.0, show ? 0.22 : 0.3, Easings.CUBIC_OUT, true);
        this.outlineAnim.update();
        if (!show && (double)this.outlineAnim.get() <= 0.001) {
            this.outlineHand = null;
        }
        this.mainScaleAnim.update();
        this.offScaleAnim.update();
        if (active && this.holding && this.activeHand != null) {
            float det;
            boolean main = this.activeHand == Hand.MAIN_HAND;
            Matrix4f a = main ? this.mainA : this.offA;
            float guiW = Position.Companion.screenWidth();
            float guiH = Position.Companion.screenHeight();
            float ndcX = guiW > 1.0f ? this.grabCursorX / guiW * 2.0f - 1.0f : 0.0f;
            float ndcY = guiH > 1.0f ? 1.0f - this.grabCursorY / guiH * 2.0f : 0.0f;
            Matrix4f matrix4f = a;
            float[] j = matrix4f != null ? ViewModel.Companion.jacobianAt(matrix4f, this.grabDepth, ndcX, ndcY, guiW, guiH) : null;
            float scale = main ? this.mainScaleAnim.get() : this.offScaleAnim.get();
            float curX = Position.Companion.mouseX();
            float curY = Position.Companion.mouseY();
            float tx = 0.0f;
            float ty = 0.0f;
            float f = det = j != null ? j[0] * j[3] - j[2] * j[1] : 0.0f;
            if (j != null && Math.abs(det) > 1.0E-4f) {
                float r = this.grabScale > 1.0E-4f ? scale / this.grabScale : 1.0f;
                float pivotX = this.grabPivotValid ? this.grabPivotX : this.grabCursorX;
                float pivotY = this.grabPivotValid ? this.grabPivotY : this.grabCursorY;
                float dx = curX - pivotX - r * (this.grabCursorX - pivotX);
                float dy = curY - pivotY - r * (this.grabCursorY - pivotY);
                tx = this.grabOffsetX + (j[3] * dx - j[2] * dy) / det;
                ty = this.grabOffsetY + (-j[1] * dx + j[0] * dy) / det;
            } else {
                float sh = Math.max(1.0f, Position.Companion.screenHeight());
                float k = 3.2f / sh;
                tx = this.grabOffsetX + (curX - this.grabCursorX) * k;
                ty = this.grabOffsetY - (curY - this.grabCursorY) * k;
            }
            if (main) {
                this.mainTargetX = tx;
                this.mainTargetY = ty;
            } else {
                this.offTargetX = tx;
                this.offTargetY = ty;
            }
        }
        boolean snapMain = active && this.holding && this.activeHand == Hand.MAIN_HAND;
        boolean snapOff = active && this.holding && this.activeHand == Hand.OFF_HAND;
        this.mainOffsetX += snapMain ? this.mainTargetX - this.mainOffsetX : (this.mainTargetX - this.mainOffsetX) * 0.45f;
        this.mainOffsetY += snapMain ? this.mainTargetY - this.mainOffsetY : (this.mainTargetY - this.mainOffsetY) * 0.45f;
        this.offOffsetX += snapOff ? this.offTargetX - this.offOffsetX : (this.offTargetX - this.offOffsetX) * 0.45f;
        this.offOffsetY += snapOff ? this.offTargetY - this.offOffsetY : (this.offTargetY - this.offOffsetY) * 0.45f;
    }

    private final void captureTransform(MatrixStack matrices, Hand hand) {
        float guiW = Position.Companion.screenWidth();
        float guiH = Position.Companion.screenHeight();
        if (guiW < 1.0f || guiH < 1.0f) {
            return;
        }
        Matrix4f a = ViewModel.Companion.handProjection().mul((Matrix4fc)RenderSystem.getModelViewMatrix()).mul((Matrix4fc)matrices.peek().getPositionMatrix());
        if (hand == Hand.MAIN_HAND) {
            this.mainA = a;
        } else {
            this.offA = a;
        }
    }

    private final void captureScalePivot(MatrixStack matrices, Hand hand) {
        float guiW = Position.Companion.screenWidth();
        float guiH = Position.Companion.screenHeight();
        if (guiW < 1.0f || guiH < 1.0f) {
            return;
        }
        Matrix4f mvp = ViewModel.Companion.handProjection().mul((Matrix4fc)RenderSystem.getModelViewMatrix()).mul((Matrix4fc)matrices.peek().getPositionMatrix());
        float[] s0 = new float[2];
        Intrinsics.checkNotNull((Object)mvp);
        if (!ViewModel.Companion.project(mvp, 0.0f, 0.0f, 0.0f, guiW, guiH, s0)) {
            return;
        }
        if (hand == Hand.MAIN_HAND) {
            this.mainPivotX = s0[0];
            this.mainPivotY = s0[1];
            this.mainPivotValid = true;
        } else {
            this.offPivotX = s0[0];
            this.offPivotY = s0[1];
            this.offPivotValid = true;
        }
    }

    private final Hand pickGrabbedHand(float mx, float my) {
        boolean mv = this.mainPivotValid;
        boolean ov = this.offPivotValid;
        if (mv && ov) {
            return ViewModel.Companion.dist2(mx, my, this.mainPivotX, this.mainPivotY) <= ViewModel.Companion.dist2(mx, my, this.offPivotX, this.offPivotY) ? Hand.MAIN_HAND : Hand.OFF_HAND;
        }
        if (mv) {
            return Hand.MAIN_HAND;
        }
        if (ov) {
            return Hand.OFF_HAND;
        }
        boolean clickRight = mx >= Position.Companion.screenWidth() * 0.5f;
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        Arm mainArm = player != null ? player.getMainArm() : MinecraftClient.getInstance().options.getMainArm().getValue();
        boolean mainOnRight = mainArm == Arm.RIGHT;
        return clickRight == mainOnRight ? Hand.MAIN_HAND : Hand.OFF_HAND;
    }

    private final void handleMainHandSwap() {
        ClientPlayerEntity clientPlayerEntity2 = MinecraftClient.getInstance().player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity player = clientPlayerEntity2;
        Arm arm2 = player.getMainArm();
        Intrinsics.checkNotNullExpressionValue((Object)arm2, (String)"getMainArm(...)");
        Arm current = arm2;
        if (this.lastMainArm == null) {
            this.lastMainArm = current;
            return;
        }
        if (current == this.lastMainArm) {
            return;
        }
        this.lastMainArm = current;
        this.mainOffsetX = this.mainTargetX = -this.mainTargetX;
        this.offOffsetX = this.offTargetX = -this.offTargetX;
        this.save();
    }

    @JvmStatic
    @Nullable
    public static final ViewModel getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final boolean inChat() {
        return Companion.inChat();
    }

    @JvmStatic
    public static final boolean wantsHandMask() {
        return Companion.wantsHandMask();
    }

    @JvmStatic
    public static final boolean suppressEatAnimation() {
        return Companion.suppressEatAnimation();
    }

    @JvmStatic
    public static final float handScale(@NotNull Hand hand) {
        return Companion.handScale(hand);
    }

    @JvmStatic
    public static final float outlineAlpha(@NotNull Hand hand) {
        return Companion.outlineAlpha(hand);
    }

    @JvmStatic
    public static final boolean apply(@Nullable MatrixStack matrices, @Nullable Hand hand) {
        return Companion.apply(matrices, hand);
    }

    @JvmStatic
    public static final boolean applyScale(@Nullable MatrixStack matrices, @Nullable Hand hand) {
        return Companion.applyScale(matrices, hand);
    }

    @JvmStatic
    public static final void beginHandFrame() {
        Companion.beginHandFrame();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b\u0011\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u000b\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000b\u0010\nJ\u0013\u0010\f\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\f\u0010\nJ\u001b\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0011J'\u0010\u0015\u001a\u00020\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0015\u0010\u0016J'\u0010\u0017\u001a\u00020\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0017\u0010\u0016J\u0013\u0010\u0019\u001a\u00020\u0018H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0019\u0010\u0003J'\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u001f\u0010 J'\u0010$\u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b$\u0010%JA\u0010.\u001a\u0004\u0018\u00010-2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b.\u0010/J\u000f\u00100\u001a\u00020&H\u0002\u00a2\u0006\u0004\b0\u00101JG\u00107\u001a\u00020\b2\u0006\u00102\u001a\u00020&2\u0006\u00103\u001a\u00020\u000f2\u0006\u00104\u001a\u00020\u000f2\u0006\u00105\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020\u000f2\u0006\u00106\u001a\u00020-H\u0002\u00a2\u0006\u0004\b7\u00108J/\u0010=\u001a\u00020\u000f2\u0006\u00109\u001a\u00020\u000f2\u0006\u0010:\u001a\u00020\u000f2\u0006\u0010;\u001a\u00020\u000f2\u0006\u0010<\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b=\u0010>R\u0014\u0010@\u001a\u00020?8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b@\u0010AR\u0014\u0010B\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0014\u0010D\u001a\u00020?8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bD\u0010AR\u0014\u0010E\u001a\u00020?8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bE\u0010AR\u0014\u0010F\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bF\u0010CR\u0014\u0010G\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bG\u0010CR\u0014\u0010H\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bH\u0010CR\u0014\u0010I\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bI\u0010CR\u0014\u0010J\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bJ\u0010CR\u0014\u0010L\u001a\u00020K8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bL\u0010MR\u0014\u0010N\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bN\u0010OR\u0014\u0010P\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bP\u0010CR\u0014\u0010Q\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bQ\u0010CR\u0014\u0010R\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bR\u0010CR\u001d\u0010T\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\bS\u00a2\u0006\u0006\n\u0004\bT\u0010U\u00a8\u0006V"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ViewModel.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/ViewModel;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/ViewModel;", "", "inChat", "()Z", "wantsHandMask", "suppressEatAnimation", "Lnet/minecraft/Hand;", "hand", "", "handScale", "(Lnet/minecraft/Hand;)F", "outlineAlpha", "Lnet/minecraft/MatrixStack;", "matrices", "apply", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/Hand;)Z", "applyScale", "", "beginHandFrame", "Lcom/google/gson/JsonObject;", "root", "", "key", "fallback", "readFloat", "(Lcom/google/gson/JsonObject;Ljava/lang/String;F)F", "v", "min", "max", "clamp", "(FFF)F", "Lorg/joml/Matrix4f;", "a", "depth", "ndcX", "ndcY", "guiW", "guiH", "", "jacobianAt", "(Lorg/joml/Matrix4f;FFFFF)[F", "handProjection", "()Lorg/joml/Matrix4f;", "mvp", "x", "y", "z", "out", "project", "(Lorg/joml/Matrix4f;FFFFF[F)Z", "ax", "ay", "bx", "by", "dist2", "(FFFF)F", "", "SCALE_ANIM_SECONDS", "D", "FALLBACK_DEPTH", "F", "OUTLINE_IN_SECONDS", "OUTLINE_OUT_SECONDS", "DRAG_UNITS_PER_PIXEL", "SCALE_STEP", "SCALE_MIN", "SCALE_MAX", "FOLLOW", "", "DOUBLE_CLICK_MS", "J", "SAVE_FILE", "Ljava/lang/String;", "CENTROID_X", "CENTROID_Y", "CENTROID_Z", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/ViewModel;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final ViewModel getInstance() {
            ViewModel module = ModuleManager.Companion.get().get(ViewModel.class);
            ViewModel viewModel = module;
            if (viewModel == null) {
                viewModel = INSTANCE;
            }
            return viewModel;
        }

        @JvmStatic
        public final boolean inChat() {
            return MinecraftClient.getInstance().currentScreen instanceof ChatScreen;
        }

        @JvmStatic
        public final boolean wantsHandMask() {
            ViewModel module = this.getInstance();
            return module != null && module.isEnabled() && this.inChat();
        }

        @JvmStatic
        public final boolean suppressEatAnimation() {
            ViewModel module = this.getInstance();
            return module != null && module.isEnabled();
        }

        @JvmStatic
        public final float handScale(@NotNull Hand hand) {
            Intrinsics.checkNotNullParameter((Object)hand, (String)"hand");
            ViewModel viewModel = this.getInstance();
            if (viewModel == null) {
                return 1.0f;
            }
            ViewModel module = viewModel;
            module.load();
            return hand == Hand.MAIN_HAND ? module.mainScaleAnim.get() : module.offScaleAnim.get();
        }

        @JvmStatic
        public final float outlineAlpha(@NotNull Hand hand) {
            Intrinsics.checkNotNullParameter((Object)hand, (String)"hand");
            ViewModel module = this.getInstance();
            if (module == null || module.outlineHand != hand) {
                return 0.0f;
            }
            return Math.max(0.0f, Math.min(1.0f, module.outlineAnim.get()));
        }

        @JvmStatic
        public final boolean apply(@Nullable MatrixStack matrices, @Nullable Hand hand) {
            ViewModel viewModel = this.getInstance();
            if (viewModel == null) {
                return false;
            }
            ViewModel module = viewModel;
            module.load();
            module.handleMainHandSwap();
            if (!module.isEnabled() || matrices == null || hand == null) {
                return false;
            }
            module.captureTransform(matrices, hand);
            boolean main = hand == Hand.MAIN_HAND;
            float ox = main ? module.mainOffsetX : module.offOffsetX;
            float oy = main ? module.mainOffsetY : module.offOffsetY;
            if (ox == 0.0f && oy == 0.0f) {
                return false;
            }
            matrices.translate(ox, oy, 0.0f);
            return true;
        }

        @JvmStatic
        public final boolean applyScale(@Nullable MatrixStack matrices, @Nullable Hand hand) {
            ViewModel module = this.getInstance();
            if (module == null || !module.isEnabled() || matrices == null || hand == null) {
                return false;
            }
            module.load();
            module.captureScalePivot(matrices, hand);
            boolean main = hand == Hand.MAIN_HAND;
            float scale = main ? module.mainScaleAnim.get() : module.offScaleAnim.get();
            if (scale == 1.0f) {
                return false;
            }
            matrices.translate(0.0f, 0.0f, 0.0f);
            matrices.scale(scale, scale, scale);
            matrices.translate(-0.0f, -0.0f, -0.0f);
            return true;
        }

        @JvmStatic
        public final void beginHandFrame() {
            ViewModel module = this.getInstance();
            if (module != null) {
                module.mainPivotValid = false;
                module.offPivotValid = false;
                module.load();
                module.handleMainHandSwap();
            }
        }

        private final float readFloat(JsonObject root, String key, float fallback) {
            float f;
            try {
                f = root.has(key) ? root.get(key).getAsFloat() : fallback;
            }
            catch (RuntimeException e) {
                f = fallback;
            }
            return f;
        }

        private final float clamp(float v, float min, float max) {
            return Math.max(min, Math.min(max, v));
        }

        private final float[] jacobianAt(Matrix4f a, float depth, float ndcX, float ndcY, float guiW, float guiH) {
            if (guiW < 1.0f || guiH < 1.0f || depth < 1.0E-4f) {
                return null;
            }
            float c0x = a.m00();
            float c0y = a.m01();
            float c0w = a.m03();
            float c1x = a.m10();
            float c1y = a.m11();
            float c1w = a.m13();
            float halfW = guiW * 0.5f;
            float halfH = guiH * 0.5f;
            float ax = (c0x - ndcX * c0w) / depth * halfW;
            float ay = -(c0y - ndcY * c0w) / depth * halfH;
            float bx = (c1x - ndcX * c1w) / depth * halfW;
            float by = -(c1y - ndcY * c1w) / depth * halfH;
            float[] fArray = new float[]{ax, ay, bx, by};
            return fArray;
        }

        private final Matrix4f handProjection() {
            Window window2 = MinecraftClient.getInstance().getWindow();
            Intrinsics.checkNotNullExpressionValue((Object)window2, (String)"getWindow(...)");
            Window window = window2;
            float aspect = (float)window.getFramebufferWidth() / (float)Math.max(1, window.getFramebufferHeight());
            float fovRad = 1.2217305f;
            Matrix4f matrix4f = new Matrix4f().perspective(fovRad, aspect, 0.05f, 100.0f);
            Intrinsics.checkNotNullExpressionValue((Object)matrix4f, (String)"perspective(...)");
            return matrix4f;
        }

        private final boolean project(Matrix4f mvp, float x, float y, float z, float guiW, float guiH, float[] out) {
            Vector4f v = mvp.transform(new Vector4f(x, y, z, 1.0f));
            if (Math.abs(v.w) < 1.0E-6f) {
                return false;
            }
            float ndcX = v.x / v.w;
            float ndcY = v.y / v.w;
            out[0] = (ndcX * 0.5f + 0.5f) * guiW;
            out[1] = (1.0f - (ndcY * 0.5f + 0.5f)) * guiH;
            return true;
        }

        private final float dist2(float ax, float ay, float bx, float by) {
            float dx = ax - bx;
            float dy = ay - by;
            return dx * dx + dy * dy;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

