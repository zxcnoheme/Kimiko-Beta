/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.math.MathKt
 *  kotlin.text.StringsKt
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 */
package rtx.kimiko.api.modules.impl.Utils.guishare;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import kotlin.text.StringsKt;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.modules.impl.Utils.Globals;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareController;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareRemoteState;
import rtx.kimiko.api.modules.impl.Utils.guishare.GuiShareThemeState;
import rtx.kimiko.api.modules.impl.Utils.guishare.RemoteGuiPanel;
import rtx.kimiko.api.modules.impl.Utils.guishare.RemoteGuiPanelRenderer;
import rtx.kimiko.api.modules.impl.Utils.guishare.RemoteTheme;
import rtx.kimiko.api.ui.window.GuiShatterAnimation;
import rtx.kimiko.api.ui.window.WorldGuiCloseAnimation;
import rtx.kimiko.utils.render.modules.post.guilayerblur.GuiLayerBlurRenderer;
import rtx.kimiko.utils.render.modules.post.guimotionblur.GuiMotionBlurRenderer;
import rtx.kimiko.utils.render.render2d.ClientPalette;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;
import rtx.kimiko.utils.render.render2d.blur.BlurFramebuffer;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\t\n\u0002\u0010\u0014\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u00c6\u0002\u0018\u00002\u00020\u0001:\t\u007f\u0080\u0001\u0081\u0001\u0082\u0001\u0083\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ9\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J=\u0010\u001b\u001a\u00020\u001a2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\fH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001b\u0010\u001cJC\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\fH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001f\u0010 J\u001b\u0010\"\u001a\u00020!2\u0006\u0010\u0015\u001a\u00020\u0014H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\"\u0010#J\u0013\u0010$\u001a\u00020!H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b$\u0010\u0003J\u001d\u0010'\u001a\u00020!2\b\u0010&\u001a\u0004\u0018\u00010%H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b'\u0010(J\u001d\u0010)\u001a\u00020!2\b\u0010&\u001a\u0004\u0018\u00010%H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b)\u0010(JG\u00101\u001a\u00020!2\u0006\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020\f2\u0006\u0010,\u001a\u00020\f2\u0006\u0010-\u001a\u00020\f2\u0006\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\f2\u0006\u00100\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b1\u00102J\u000f\u00103\u001a\u00020!H\u0002\u00a2\u0006\u0004\b3\u0010\u0003J\u0013\u00104\u001a\u00020\u001aH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b4\u00105J\u0013\u00106\u001a\u00020\u001aH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b6\u00105J\u0019\u00108\u001a\b\u0012\u0004\u0012\u00020\u000407H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b8\u00109J\u0019\u0010;\u001a\u0004\u0018\u00010:2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b;\u0010<J\u0019\u0010=\u001a\u0004\u0018\u00010:2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b=\u0010<J\u001d\u0010@\u001a\u00020!2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00040>H\u0002\u00a2\u0006\u0004\b@\u0010AJ\u0019\u0010B\u001a\b\u0012\u0004\u0012\u00020\u000407H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bB\u00109J\u0017\u0010C\u001a\u00020\u001a2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\bC\u0010DJ\u0019\u0010E\u001a\u00020\u001a2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\bE\u0010DJ\u001d\u0010F\u001a\u00020!2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\bF\u0010#J\u0019\u0010G\u001a\u00020!2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0002\u00a2\u0006\u0004\bG\u0010#J-\u0010L\u001a\u00020H2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020H072\u0006\u0010J\u001a\u00020\f2\u0006\u0010K\u001a\u00020\fH\u0002\u00a2\u0006\u0004\bL\u0010MJ\u0019\u0010N\u001a\u0004\u0018\u00010H2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\bN\u0010OJ3\u0010T\u001a\u0004\u0018\u00010:2\b\u0010P\u001a\u0004\u0018\u00010:2\u0006\u0010Q\u001a\u00020\f2\u0006\u0010R\u001a\u00020\f2\u0006\u0010S\u001a\u00020\fH\u0002\u00a2\u0006\u0004\bT\u0010UJ\u0017\u0010V\u001a\u00020\u001a2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\bV\u0010DJ\u0015\u0010W\u001a\u00020\u001a2\u0006\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\bW\u0010DJ\u0017\u0010X\u001a\u00020H2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\bX\u0010OJ1\u0010\\\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010Y\u001a\u00020\f2\u0006\u0010Z\u001a\u00020\f2\u0006\u0010[\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\\\u0010]R\u0014\u0010^\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b^\u0010_R\u0014\u0010a\u001a\u00020`8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\ba\u0010bR\u0014\u0010c\u001a\u00020`8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bc\u0010bR\u0014\u0010d\u001a\u00020\u001a8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bd\u0010eR\u0014\u0010f\u001a\u00020\u001a8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bf\u0010eR\u0014\u0010g\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bg\u0010_R\u0014\u0010h\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bh\u0010_R\u0014\u0010i\u001a\u00020`8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bi\u0010bR\u0014\u0010j\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bj\u0010_R\u0014\u0010k\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bk\u0010_R\u0014\u0010l\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\bl\u0010_R$\u0010o\u001a\u0012\u0012\u0004\u0012\u00020\u00040mj\b\u0012\u0004\u0012\u00020\u0004`n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bo\u0010pR$\u0010r\u001a\u0012\u0012\u0004\u0012\u00020q0mj\b\u0012\u0004\u0012\u00020q`n8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\br\u0010pR \u0010t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001a0s8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bt\u0010uR\u0016\u00104\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u0010eR\u0016\u00106\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b6\u0010eR\u0018\u0010w\u001a\u0004\u0018\u00010v8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bw\u0010xR\u0018\u0010y\u001a\u0004\u0018\u00010q8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\by\u0010zR\u0018\u0010{\u001a\u0004\u0018\u00010q8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b{\u0010zR\u0016\u0010|\u001a\u00020`8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b|\u0010bR\u0016\u0010}\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b}\u0010eR\u0014\u0010~\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b~\u0010_\u00a8\u0006\u0084\u0001"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$PanelQuad;", "quad", "Lorg/joml/Matrix4f;", "Lkotlin/jvm/JvmStatic;", "freshMatrix", "(Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$PanelQuad;)Lorg/joml/Matrix4f;", "Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiPanel;", "panel", "", "extraLeft", "extraTop", "cellW", "cellH", "Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$Shatter;", "buildShatter", "(Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiPanel;FFFF)Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$Shatter;", "Lnet/minecraft/DrawContext;", "graphics", "x", "y", "w", "h", "", "beginCardBlurJob", "(Lnet/minecraft/DrawContext;FFFF)Z", "presence", "blurPhase", "recordCardBlurRect", "(FFFFFF)Z", "", "endCardBlurJob", "(Lnet/minecraft/DrawContext;)V", "prepareCardBlurDraw", "Lnet/minecraft/Framebuffer;", "target", "beginCardBlurDraw", "(Lnet/minecraft/Framebuffer;)V", "endCardBlurDraw", "sx", "sy", "scale", "cellX", "cellY", "fbFactorX", "fbFactorY", "beginCardBlurSlot", "(FFFFFFF)V", "endCardBlurSlot", "captureRequested", "()Z", "depthSnapshotWanted", "", "consumeQuads", "()Ljava/util/List;", "Lnet/minecraft/Vec3d;", "quadWorldPos", "(Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$PanelQuad;)Lnet/minecraft/Vec3d;", "panelWorldPos", "", "list", "sortFarthestFirst", "(Ljava/util/List;)V", "peekQuads", "updateViewerSide", "(Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiPanel;)Z", "viewerBehind", "renderPanels", "renderPanelsImpl", "", "cells", "areaW", "areaH", "packSlots", "(Ljava/util/List;FF)[F", "tightPopupCell", "(Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiPanel;)[F", "anchor", "pitch", "yaw", "distance", "offsetForward", "(Lnet/minecraft/Vec3d;FFF)Lnet/minecraft/Vec3d;", "wantsSplit", "popupVisible", "computeCell", "pivotX", "pivotY", "forwardOffset", "buildMatrix", "(Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiPanel;FFF)Lorg/joml/Matrix4f;", "WORLD_WIDTH_BLOCKS", "F", "", "MAX_PANELS", "I", "MAX_SLOTS", "SPLIT_POPUP_SLOTS", "Z", "STACK_BACK_ORDER", "CAPTURE_SCALE_CAP", "SIDE_HYSTERESIS", "MAX_CARD_BLUR_RECTS", "CARD_BLUR_MAX_RADIUS", "CARD_BLUR_BOUNDS_PAD", "POPUP_FORWARD_BLOCKS", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "quads", "Ljava/util/ArrayList;", "Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$CardBlurJob;", "cardBlurJobs", "Ljava/util/WeakHashMap;", "behindSide", "Ljava/util/WeakHashMap;", "Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$CardBlurTransform;", "cardBlurTransform", "Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$CardBlurTransform;", "collectingCardBlurJob", "Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$CardBlurJob;", "activeCardBlurJob", "cardBlurDrawIndex", "renderBroken", "CHAT_CELL_PAD", "SlotBlit", "PanelQuad", "Shatter", "CardBlurTransform", "CardBlurJob", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nRemoteGuiWorld.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RemoteGuiWorld.kt\nrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,741:1\n1#2:742\n*E\n"})
public final class RemoteGuiWorld {
    @NotNull
    public static final RemoteGuiWorld INSTANCE = new RemoteGuiWorld();
    private static final float WORLD_WIDTH_BLOCKS = 1.35f;
    private static final int MAX_PANELS = 4;
    private static final int MAX_SLOTS = 4;
    private static final boolean SPLIT_POPUP_SLOTS = false;
    private static final boolean STACK_BACK_ORDER = true;
    private static final float CAPTURE_SCALE_CAP = 1.5f;
    private static final float SIDE_HYSTERESIS = 0.05f;
    private static final int MAX_CARD_BLUR_RECTS = 64;
    private static final float CARD_BLUR_MAX_RADIUS = 22.0f;
    private static final float CARD_BLUR_BOUNDS_PAD = 48.0f;
    private static final float POPUP_FORWARD_BLOCKS = -0.02f;
    @NotNull
    private static final ArrayList<PanelQuad> quads = new ArrayList();
    @NotNull
    private static final ArrayList<CardBlurJob> cardBlurJobs = new ArrayList();
    @NotNull
    private static final WeakHashMap<RemoteGuiPanel, Boolean> behindSide = new WeakHashMap();
    private static boolean captureRequested;
    private static boolean depthSnapshotWanted;
    @Nullable
    private static CardBlurTransform cardBlurTransform;
    @Nullable
    private static CardBlurJob collectingCardBlurJob;
    @Nullable
    private static CardBlurJob activeCardBlurJob;
    private static int cardBlurDrawIndex;
    private static boolean renderBroken;
    private static final float CHAT_CELL_PAD = 24.0f;

    private RemoteGuiWorld() {
    }

    @JvmStatic
    @Nullable
    public static final Matrix4f freshMatrix(@Nullable PanelQuad quad) {
        Vec3d vec3d2;
        PanelQuad panelQuad = quad;
        if ((panelQuad != null ? panelQuad.panel : null) == null) {
            PanelQuad panelQuad2 = quad;
            return panelQuad2 != null ? panelQuad2.matrix : null;
        }
        RemoteGuiPanel panel = quad.panel;
        GuiShareRemoteState guiShareRemoteState = panel.state();
        if (guiShareRemoteState == null) {
            return quad.matrix;
        }
        GuiShareRemoteState state = guiShareRemoteState;
        if (!WorldGuiCloseAnimation.hasCapturedWorldMatrices()) {
            return quad.matrix;
        }
        if (panel.closeActive()) {
            vec3d2 = panel.resolvedAnchor();
        } else {
            vec3d2 = GuiShareController.liveAnchorFor(state, panel.smoothYaw(), panel.smoothPitch());
            if (vec3d2 == null) {
                return quad.matrix;
            }
        }
        Vec3d anchor = vec3d2;
        Matrix4f matrix = WorldGuiCloseAnimation.buildRemoteMatrix(INSTANCE.offsetForward(anchor, panel.resolvedPitch(), panel.resolvedYaw(), quad.forwardOffset), panel.resolvedYaw(), panel.resolvedPitch(), 0.003139535f, panel.scale(), quad.pivotX, quad.pivotY);
        Matrix4f matrix4f = matrix;
        if (matrix4f == null) {
            matrix4f = quad.matrix;
        }
        return matrix4f;
    }

    private final Shatter buildShatter(RemoteGuiPanel panel, float extraLeft, float extraTop, float cellW, float cellH) {
        if (!panel.shatterActive()) {
            return null;
        }
        float[] rect = panel.shatterRect();
        if (rect.length < 7 || rect[5] <= 1.0f || rect[6] <= 1.0f || rect[2] <= 1.0f || rect[3] <= 1.0f) {
            return null;
        }
        float ox = rect[0] / rect[5];
        float oy = rect[1] / rect[6];
        float ow = rect[2] / rect[5];
        float oh = rect[3] / rect[6];
        if (ow <= 1.0E-5f || oh <= 1.0E-5f) {
            return null;
        }
        float panelW = 430.0f;
        float panelH = 290.0f;
        float glowPad = 80.0f;
        float nameH = 22.0f;
        float scaleX = panelW / cellW / ow;
        float offsetX = (extraLeft + glowPad) / cellW - panelW / cellW * (ox / ow);
        float scaleY = panelH / cellH / oh;
        float offsetY = (extraTop + glowPad + nameH) / cellH - panelH / cellH * (oy / oh);
        return new Shatter(panel.shatterState(), panel.shatterProgressForRender(), panel.closeAlpha(), offsetX, offsetY, scaleX, scaleY);
    }

    @JvmStatic
    public static final boolean beginCardBlurJob(@Nullable DrawContext graphics, float x, float y, float w, float h) {
        CardBlurTransform transform = cardBlurTransform;
        if (graphics == null || transform == null || collectingCardBlurJob != null || w <= 0.5f || h <= 0.5f) {
            return false;
        }
        CardBlurJob job = new CardBlurJob(transform.offsetX + x * transform.scaleX, transform.offsetY + y * transform.scaleY, w * transform.scaleX, h * transform.scaleY, transform.radiusScale);
        cardBlurJobs.add(job);
        collectingCardBlurJob = job;
        GuiLayerBlurRenderer.markRemoteCardBegin(graphics);
        return true;
    }

    @JvmStatic
    public static final boolean recordCardBlurRect(float x, float y, float w, float h, float presence, float blurPhase) {
        CardBlurTransform transform = cardBlurTransform;
        CardBlurJob job = collectingCardBlurJob;
        if (transform == null || job == null) {
            return false;
        }
        return job.add(transform.offsetX + x * transform.scaleX, transform.offsetY + y * transform.scaleY, w * transform.scaleX, h * transform.scaleY, presence, blurPhase);
    }

    @JvmStatic
    public static final void endCardBlurJob(@NotNull DrawContext graphics) {
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        CardBlurJob cardBlurJob = collectingCardBlurJob;
        if (cardBlurJob == null) {
            return;
        }
        CardBlurJob job = cardBlurJob;
        collectingCardBlurJob = null;
        job.finish();
        GuiLayerBlurRenderer.markRemoteCardEnd(graphics);
    }

    @JvmStatic
    public static final void prepareCardBlurDraw() {
        cardBlurDrawIndex = 0;
        activeCardBlurJob = null;
    }

    @JvmStatic
    public static final void beginCardBlurDraw(@Nullable Framebuffer target) {
        CardBlurJob cardBlurJob;
        if (renderBroken) {
            return;
        }
        if (cardBlurDrawIndex < cardBlurJobs.size()) {
            int n = cardBlurDrawIndex;
            cardBlurDrawIndex = n + 1;
            cardBlurJob = cardBlurJobs.get(n);
        } else {
            cardBlurJob = null;
        }
        CardBlurJob cardBlurJob2 = activeCardBlurJob = cardBlurJob;
        boolean bl = cardBlurJob2 != null ? cardBlurJob2.getReady() : false;
        if (bl) {
            CardBlurJob cardBlurJob3 = activeCardBlurJob;
            Intrinsics.checkNotNull((Object)cardBlurJob3);
            GuiMotionBlurRenderer.captureBackground(target, cardBlurJob3.getRadius());
        }
    }

    @JvmStatic
    public static final void endCardBlurDraw(@Nullable Framebuffer target) {
        if (renderBroken) {
            return;
        }
        CardBlurJob job = activeCardBlurJob;
        activeCardBlurJob = null;
        if (job == null || !job.getReady() || target == null) {
            return;
        }
        GuiMotionBlurRenderer.applyWithCopy(target, 1.0f, job.getRadius(), job.getBoundsX(), job.getBoundsY(), job.getBoundsW(), job.getBoundsH(), job.getMask(), job.getCardCount() + 1, job.getBoundsX(), job.getBoundsY(), job.getBoundsW(), job.getBoundsH(), 1.0f, job.getOriginX(), job.getOriginY());
    }

    private final void beginCardBlurSlot(float sx, float sy, float scale, float cellX, float cellY, float fbFactorX, float fbFactorY) {
        cardBlurTransform = new CardBlurTransform((sx - cellX * scale) * fbFactorX, (sy - cellY * scale) * fbFactorY, scale * fbFactorX, scale * fbFactorY, scale);
    }

    private final void endCardBlurSlot() {
        cardBlurTransform = null;
        collectingCardBlurJob = null;
    }

    @JvmStatic
    public static final boolean captureRequested() {
        return captureRequested;
    }

    @JvmStatic
    public static final boolean depthSnapshotWanted() {
        return depthSnapshotWanted;
    }

    @JvmStatic
    @NotNull
    public static final List<PanelQuad> consumeQuads() {
        ArrayList out = new ArrayList(quads);
        quads.clear();
        captureRequested = false;
        INSTANCE.sortFarthestFirst(out);
        return out;
    }

    private final Vec3d quadWorldPos(PanelQuad quad) {
        RemoteGuiPanel panel = quad.panel;
        if (panel.resolvedAnchor() == null) {
            return null;
        }
        return this.offsetForward(panel.resolvedAnchor(), panel.resolvedPitch(), panel.resolvedYaw(), quad.forwardOffset);
    }

    private final Vec3d panelWorldPos(PanelQuad quad) {
        return quad.panel.resolvedAnchor();
    }

    private final void sortFarthestFirst(List<PanelQuad> list) {
        if (list.size() < 2) {
            return;
        }
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null || mc.player == null) {
            return;
        }
        Vec3d eye = mc.player.getEyePos();
        if (eye == null) {
            return;
        }
        list.sort(Comparator.comparingDouble((PanelQuad q) -> {
            Vec3d pos = this.quadWorldPos(q);
            return pos != null ? pos.squaredDistanceTo(eye) : 0.0;
        }).reversed());
    }

    @JvmStatic
    @NotNull
    public static final List<PanelQuad> peekQuads() {
        return quads;
    }

    private final boolean updateViewerSide(RemoteGuiPanel panel) {
        boolean previous = this.viewerBehind(panel);
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        Vec3d anchor = panel.resolvedAnchor();
        if (mc.player == null || anchor == null) {
            return previous;
        }
        ClientPlayerEntity clientPlayerEntity2 = mc.player;
        Intrinsics.checkNotNull((Object)clientPlayerEntity2);
        Vec3d vec3d2 = clientPlayerEntity2.getEyePos().subtract(anchor);
        Intrinsics.checkNotNullExpressionValue((Object)vec3d2, (String)"subtract(...)");
        Vec3d toEye = vec3d2;
        double distance = toEye.length();
        if (distance < 1.0E-4) {
            return previous;
        }
        Vec3d vec3d3 = Vec3d.fromPolar((float)panel.resolvedPitch(), (float)panel.resolvedYaw());
        Intrinsics.checkNotNullExpressionValue((Object)vec3d3, (String)"directionFromRotation(...)");
        Vec3d normal = vec3d3;
        double side = toEye.dotProduct(normal) / distance;
        boolean behind = previous ? side > (double)-0.05f : side > (double)0.05f;
        ((Map)behindSide).put(panel, behind);
        return behind;
    }

    private final boolean viewerBehind(RemoteGuiPanel panel) {
        return panel != null && Intrinsics.areEqual((Object)true, (Object)behindSide.get(panel));
    }

    @JvmStatic
    public static final void renderPanels(@Nullable DrawContext graphics) {
        if (renderBroken) {
            return;
        }
        try {
            INSTANCE.renderPanelsImpl(graphics);
        }
        catch (Throwable throwable) {
            renderBroken = true;
            captureRequested = false;
            depthSnapshotWanted = false;
            try {
                Render2D.flush();
            }
            catch (Throwable throwable2) {
                // empty catch block
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void renderPanelsImpl(DrawContext graphics) {
        float fbFactorX;
        captureRequested = false;
        depthSnapshotWanted = false;
        quads.clear();
        cardBlurJobs.clear();
        cardBlurTransform = null;
        collectingCardBlurJob = null;
        activeCardBlurJob = null;
        cardBlurDrawIndex = 0;
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        if (graphics == null || mc.world == null || mc.player == null) {
            return;
        }
        if (!Globals.Companion.remoteGuisEnabled()) {
            return;
        }
        if (!WorldGuiCloseAnimation.hasCapturedWorldMatrices() || !GuiLayerBlurRenderer.available()) {
            return;
        }
        List<RemoteGuiPanel> panels = GuiShareController.renderablePanels(4);
        if (panels.isEmpty()) {
            return;
        }
        float areaW = Position.Companion.screenWidth();
        float areaH = Position.Companion.screenHeight();
        if (areaW <= 32.0f || areaH <= 32.0f) {
            return;
        }
        int count = panels.size();
        int popupCount = 0;
        for (RemoteGuiPanel panel : panels) {
            if (!this.wantsSplit(panel)) continue;
            ++popupCount;
        }
        boolean splitPopups = false;
        ArrayList<float[]> cells = new ArrayList<float[]>();
        int[] panelSlot = new int[count];
        int[] popupSlot = new int[count];
        for (int i = 0; i < count; ++i) {
            float[] fArray;
            RemoteGuiPanel panel = panels.get(i);
            Object popupCell = null;
            panelSlot[i] = cells.size();
            ArrayList<float[]> arrayList = cells;
            if (popupCell != null) {
                ArrayList<float[]> arrayList2 = arrayList;
                boolean bl = false;
                float[] fArray2 = new float[]{0.0f, 0.0f, 590.0f, 472.0f};
                fArray = fArray2;
                arrayList = arrayList2;
            } else {
                fArray = this.computeCell(panel);
            }
            arrayList.add(fArray);
            if (popupCell != null) {
                popupSlot[i] = cells.size();
                cells.add((float[])popupCell);
                continue;
            }
            popupSlot[i] = -1;
        }
        float[] layout = this.packSlots((List<float[]>)cells, areaW, areaH);
        float scale = layout[cells.size() * 2];
        if (scale <= 0.05f) {
            return;
        }
        captureRequested = true;
        depthSnapshotWanted = true;
        Render2D.beginFrame(graphics);
        GuiLayerBlurRenderer.markRemoteBegin(graphics);
        long now = System.currentTimeMillis();
        float fbFactorY = fbFactorX = Render2DCoordinateSpace.designGuiScale();
        BlurFramebuffer.Companion.beginWorldScope();
        try {
            for (int i = 0; i < count; ++i) {
                RemoteGuiPanel panel = panels.get(i);
                int slot = panelSlot[i];
                Object e = cells.get(slot);
                Intrinsics.checkNotNullExpressionValue(e, (String)"get(...)");
                float[] cell = (float[])e;
                float sx = layout[slot * 2];
                float sy = layout[slot * 2 + 1];
                RemoteTheme theme = panel.theme();
                theme.update(now);
                GuiShareThemeState rawTheme = panel.state().theme;
                ClientPalette.writeRemoteSlot(slot + 1, theme.palette6(), theme.phase(), theme.styleId(), theme.closed(), rawTheme.gradientSweep, rawTheme.gradientPrevStyle);
                boolean splitThis = popupSlot[i] >= 0;
                boolean stackedBack = !splitThis && this.wantsSplit(panel) && this.viewerBehind(panel);
                float cellW = cell[2];
                float cellH = cell[3];
                graphics.getMatrices().pushMatrix();
                graphics.getMatrices().translate(sx, sy);
                graphics.getMatrices().scale(scale, scale);
                Render2D.pushScissor(graphics, 0.0f, 0.0f, cellW, cellH);
                graphics.getMatrices().translate(-cell[0], -cell[1]);
                this.beginCardBlurSlot(sx, sy, scale, cell[0], cell[1], fbFactorX, fbFactorY);
                try {
                    if (splitThis) {
                        RemoteGuiPanelRenderer.render(graphics, panel, slot + 1, 1);
                    } else if (stackedBack) {
                        RemoteGuiPanelRenderer.render(graphics, panel, slot + 1, 2, 1.0f, true);
                        GuiLayerBlurRenderer.markPopupBoundary(graphics);
                        RemoteGuiPanelRenderer.render(graphics, panel, slot + 1, 1, 1.0f, false);
                    } else {
                        RemoteGuiPanelRenderer.render(graphics, panel, slot + 1, 0);
                    }
                }
                finally {
                    this.endCardBlurSlot();
                }
                Render2D.popScissor(graphics);
                graphics.getMatrices().popMatrix();
                panel.resolveAnchor(GuiShareController.liveAnchorFor(panel.state(), panel.smoothYaw(), panel.smoothPitch()));
                this.updateViewerSide(panel);
                float pivotX = 295.0f - cell[0];
                float pivotY = 236.0f - cell[1];
                float panelFbX = sx * fbFactorX;
                float panelFbY = sy * fbFactorY;
                float panelFbW = cellW * scale * fbFactorX;
                float panelFbH = cellH * scale * fbFactorY;
                Matrix4f matrix = this.buildMatrix(panel, pivotX, pivotY, 0.0f);
                if (matrix != null) {
                    quads.add(new PanelQuad(matrix, cellW, cellH, panelFbX, panelFbY, panelFbW, panelFbH, this.buildShatter(panel, -cell[0], -cell[1], cellW, cellH), panel, pivotX, pivotY, 0.0f, null, 1.0f));
                }
                if (!splitThis) continue;
                int pslot = popupSlot[i];
                Object e2 = cells.get(pslot);
                Intrinsics.checkNotNullExpressionValue(e2, (String)"get(...)");
                float[] pcell = (float[])e2;
                float psx = layout[pslot * 2];
                float psy = layout[pslot * 2 + 1];
                float pw = pcell[2];
                float ph = pcell[3];
                ClientPalette.writeRemoteSlot(pslot + 1, theme.palette6(), theme.phase(), theme.styleId(), theme.closed(), rawTheme.gradientSweep, rawTheme.gradientPrevStyle);
                graphics.getMatrices().pushMatrix();
                graphics.getMatrices().translate(psx, psy);
                graphics.getMatrices().scale(scale, scale);
                Render2D.pushScissor(graphics, 0.0f, 0.0f, pw, ph);
                graphics.getMatrices().translate(-pcell[0], -pcell[1]);
                RemoteGuiPanelRenderer.render(graphics, panel, pslot + 1, 2);
                Render2D.popScissor(graphics);
                graphics.getMatrices().popMatrix();
                float ppivotX = 295.0f - pcell[0];
                float ppivotY = 236.0f - pcell[1];
                Matrix4f pmatrix = this.buildMatrix(panel, ppivotX, ppivotY, -0.02f);
                if (pmatrix == null) continue;
                float stepX = scale * fbFactorX;
                float stepY = scale * fbFactorY;
                SlotBlit blit = new SlotBlit(panelFbX + (pcell[0] - cell[0]) * stepX, panelFbY + (pcell[1] - cell[1]) * stepY, pw * stepX, ph * stepY, panelFbX, panelFbY, panelFbW, panelFbH);
                quads.add(new PanelQuad(pmatrix, pw, ph, psx * fbFactorX, psy * fbFactorY, pw * stepX, ph * stepY, null, panel, ppivotX, ppivotY, -0.02f, blit, 1.0f));
            }
        }
        finally {
            BlurFramebuffer.Companion.endWorldScope();
        }
        Render2D.flush();
        GuiLayerBlurRenderer.markPanelEndRemote(graphics);
    }

    private final float[] packSlots(List<float[]> cells, float areaW, float areaH) {
        int n = cells.size();
        float[] out = new float[n * 2 + 1];
        if (n == 0) {
            return out;
        }
        int masks = 1 << n - 1;
        float bestScale = -1.0f;
        int bestMask = 0;
        for (int mask = 0; mask < masks; ++mask) {
            float widthLimit = Float.MAX_VALUE;
            float heightSum = 0.0f;
            int rows = 0;
            float rowW = 0.0f;
            float rowH = 0.0f;
            int rowCells = 0;
            for (int i = 0; i < n; ++i) {
                rowW += cells.get(i)[2];
                rowH = Math.max(rowH, cells.get(i)[3]);
                ++rowCells;
                if (i != n - 1 && (mask & 1 << i) == 0) continue;
                float avail = areaW - 8.0f * (float)rowCells;
                widthLimit = Math.min(widthLimit, avail <= 0.0f || rowW <= 0.0f ? 0.0f : avail / rowW);
                heightSum += rowH;
                ++rows;
                rowW = 0.0f;
                rowH = 0.0f;
                rowCells = 0;
            }
            float availH = areaH - 8.0f * (float)rows;
            float heightLimit = availH <= 0.0f || heightSum <= 0.0f ? 0.0f : availH / heightSum;
            float value = Math.min(1.5f, Math.min(widthLimit, heightLimit));
            if (!(value > bestScale + 1.0E-4f)) continue;
            bestScale = value;
            bestMask = mask;
        }
        float y = 0.0f;
        int start = 0;
        for (int i = 0; i < n; ++i) {
            if (i != n - 1 && (bestMask & 1 << i) == 0) continue;
            float rowH = 0.0f;
            int j = start;
            if (j <= i) {
                while (true) {
                    rowH = Math.max(rowH, cells.get(j)[3]);
                    if (j == i) break;
                    ++j;
                }
            }
            float x = 0.0f;
            int j2 = start;
            if (j2 <= i) {
                while (true) {
                    out[j2 * 2] = x + 4.0f;
                    out[j2 * 2 + 1] = y + 4.0f;
                    x += cells.get(j2)[2] * bestScale + 8.0f;
                    if (j2 == i) break;
                    ++j2;
                }
            }
            y += rowH * bestScale + 8.0f;
            start = i + 1;
        }
        out[n * 2] = bestScale;
        return out;
    }

    private final float[] tightPopupCell(RemoteGuiPanel panel) {
        float[] rect = RemoteGuiPanelRenderer.popupDrawRect(panel);
        if (rect == null || rect[2] <= 1.0f || rect[3] <= 1.0f || rect[2] > 2360.0f || rect[3] > 1888.0f) {
            return null;
        }
        return rect;
    }

    private final Vec3d offsetForward(Vec3d anchor, float pitch, float yaw, float distance) {
        if (anchor == null || distance == 0.0f) {
            return anchor;
        }
        return anchor.add(Vec3d.fromPolar((float)pitch, (float)yaw).multiply((double)distance));
    }

    private final boolean wantsSplit(RemoteGuiPanel panel) {
        return this.popupVisible(panel) && !panel.shatterActive();
    }

    public final boolean popupVisible(@NotNull RemoteGuiPanel panel) {
        Intrinsics.checkNotNullParameter((Object)panel, (String)"panel");
        String popup = panel.popupDisplayed();
        return popup != null && !StringsKt.isBlank((CharSequence)popup) && panel.popupT() > 0.01f || panel.state().bindPopup.visible;
    }

    private final float[] computeCell(RemoteGuiPanel panel) {
        float left = 0.0f;
        float top = 0.0f;
        float right = 590.0f;
        float bottom = 472.0f;
        if (panel.messengerVisible()) {
            right = Math.max(right, RemoteGuiPanelRenderer.INSTANCE.chatRightEdge() + 24.0f);
        }
        if (this.popupVisible(panel)) {
            float[] rect = this.tightPopupCell(panel);
            if (rect != null) {
                left = Math.min(left, rect[0]);
                top = Math.min(top, rect[1]);
                right = Math.max(right, rect[0] + rect[2]);
                bottom = Math.max(bottom, rect[1] + rect[3]);
            } else {
                float px = 80.0f + panel.popupHoldX();
                float py = 80.0f + panel.popupHoldY();
                float pad = 8.0f;
                float reach = 100.0f;
                left = Math.min(left, px - reach - pad);
                top = Math.min(top, py - reach - pad);
                right = Math.max(right, px + 180.0f + reach + pad);
                bottom = Math.max(bottom, py + 290.0f + reach + pad);
            }
        }
        float[] fArray = new float[]{left, top, right - left, bottom - top};
        return fArray;
    }

    private final Matrix4f buildMatrix(RemoteGuiPanel panel, float pivotX, float pivotY, float forwardOffset) {
        float worldScale = 0.003139535f;
        return WorldGuiCloseAnimation.buildRemoteMatrix(this.offsetForward(panel.resolvedAnchor(), panel.resolvedPitch(), panel.resolvedYaw(), forwardOffset), panel.resolvedYaw(), panel.resolvedPitch(), worldScale, panel.scale(), pivotX, pivotY);
    }

    private static final int sortFarthestFirst$lambda$0(Vec3d $eye, PanelQuad a, PanelQuad b) {
        if (Intrinsics.areEqual((Object)a.panel, (Object)b.panel)) {
            if (!INSTANCE.viewerBehind(a.panel)) {
                int overlayB;
                int overlayA = a.blit != null ? 1 : 0;
                int n = overlayB = b.blit != null ? 1 : 0;
                if (overlayA != overlayB) {
                    return Intrinsics.compare((int)overlayA, (int)overlayB);
                }
            }
            Intrinsics.checkNotNull((Object)a);
            Vec3d qa = INSTANCE.quadWorldPos(a);
            Intrinsics.checkNotNull((Object)b);
            Vec3d qb = INSTANCE.quadWorldPos(b);
            if (qa == null || qb == null) {
                return 0;
            }
            return Double.compare(qb.squaredDistanceTo($eye), qa.squaredDistanceTo($eye));
        }
        Intrinsics.checkNotNull((Object)a);
        Vec3d pa = INSTANCE.panelWorldPos(a);
        Intrinsics.checkNotNull((Object)b);
        Vec3d pb = INSTANCE.panelWorldPos(b);
        if (pa == null || pb == null) {
            return 0;
        }
        return Double.compare(pb.squaredDistanceTo($eye), pa.squaredDistanceTo($eye));
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b&\b\u0002\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\b\u0010\tJ=\u0010\u0011\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0019\u0010\u0018R\u0017\u0010\u0005\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u001a\u0010\u0018R\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0016\u001a\u0004\b\u001b\u0010\u0018R\u0017\u0010\u0007\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0016\u001a\u0004\b\u001c\u0010\u0018R\u0017\u0010\u001e\u001a\u00020\u001d8\u0006\u00a2\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\"\u0010#\u001a\u00020\"8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\"\u0010)\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b)\u0010\u0016\u001a\u0004\b*\u0010\u0018\"\u0004\b+\u0010,R\"\u0010-\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b-\u0010\u0016\u001a\u0004\b.\u0010\u0018\"\u0004\b/\u0010,R\"\u00100\u001a\u00020\"8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b0\u0010$\u001a\u0004\b1\u0010&\"\u0004\b2\u0010(R\"\u00103\u001a\u00020\"8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b3\u0010$\u001a\u0004\b4\u0010&\"\u0004\b5\u0010(R\"\u00106\u001a\u00020\"8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b6\u0010$\u001a\u0004\b7\u0010&\"\u0004\b8\u0010(R\"\u00109\u001a\u00020\"8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b9\u0010$\u001a\u0004\b:\u0010&\"\u0004\b;\u0010(R\"\u0010<\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b<\u0010\u0016\u001a\u0004\b=\u0010\u0018\"\u0004\b>\u0010,R\"\u0010?\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b?\u0010\u0016\u001a\u0004\b@\u0010\u0018\"\u0004\bA\u0010,R\"\u0010B\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bB\u0010C\u001a\u0004\bD\u0010E\"\u0004\bF\u0010G\u00a8\u0006H"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$CardBlurJob;", "", "", "zoneX", "zoneY", "zoneW", "zoneH", "radiusScale", "<init>", "(FFFFF)V", "x", "y", "w", "h", "presence", "blurPhase", "", "add", "(FFFFFF)Z", "", "finish", "()V", "F", "getZoneX", "()F", "getZoneY", "getZoneW", "getZoneH", "getRadiusScale", "", "mask", "[F", "getMask", "()[F", "", "cardCount", "I", "getCardCount", "()I", "setCardCount", "(I)V", "maxPhase", "getMaxPhase", "setMaxPhase", "(F)V", "radius", "getRadius", "setRadius", "boundsX", "getBoundsX", "setBoundsX", "boundsY", "getBoundsY", "setBoundsY", "boundsW", "getBoundsW", "setBoundsW", "boundsH", "getBoundsH", "setBoundsH", "originX", "getOriginX", "setOriginX", "originY", "getOriginY", "setOriginY", "ready", "Z", "getReady", "()Z", "setReady", "(Z)V", "rtx.kimiko:kimiko"})
    private static final class CardBlurJob {
        private final float zoneX;
        private final float zoneY;
        private final float zoneW;
        private final float zoneH;
        private final float radiusScale;
        @NotNull
        private final float[] mask;
        private int cardCount;
        private float maxPhase;
        private float radius;
        private int boundsX;
        private int boundsY;
        private int boundsW;
        private int boundsH;
        private float originX;
        private float originY;
        private boolean ready;

        public CardBlurJob(float zoneX, float zoneY, float zoneW, float zoneH, float radiusScale) {
            this.zoneX = zoneX;
            this.zoneY = zoneY;
            this.zoneW = zoneW;
            this.zoneH = zoneH;
            this.radiusScale = radiusScale;
            this.mask = new float[390];
        }

        public final float getZoneX() {
            return this.zoneX;
        }

        public final float getZoneY() {
            return this.zoneY;
        }

        public final float getZoneW() {
            return this.zoneW;
        }

        public final float getZoneH() {
            return this.zoneH;
        }

        public final float getRadiusScale() {
            return this.radiusScale;
        }

        @NotNull
        public final float[] getMask() {
            return this.mask;
        }

        public final int getCardCount() {
            return this.cardCount;
        }

        public final void setCardCount(int n) {
            this.cardCount = n;
        }

        public final float getMaxPhase() {
            return this.maxPhase;
        }

        public final void setMaxPhase(float f) {
            this.maxPhase = f;
        }

        public final float getRadius() {
            return this.radius;
        }

        public final void setRadius(float f) {
            this.radius = f;
        }

        public final int getBoundsX() {
            return this.boundsX;
        }

        public final void setBoundsX(int n) {
            this.boundsX = n;
        }

        public final int getBoundsY() {
            return this.boundsY;
        }

        public final void setBoundsY(int n) {
            this.boundsY = n;
        }

        public final int getBoundsW() {
            return this.boundsW;
        }

        public final void setBoundsW(int n) {
            this.boundsW = n;
        }

        public final int getBoundsH() {
            return this.boundsH;
        }

        public final void setBoundsH(int n) {
            this.boundsH = n;
        }

        public final float getOriginX() {
            return this.originX;
        }

        public final void setOriginX(float f) {
            this.originX = f;
        }

        public final float getOriginY() {
            return this.originY;
        }

        public final void setOriginY(float f) {
            this.originY = f;
        }

        public final boolean getReady() {
            return this.ready;
        }

        public final void setReady(boolean bl) {
            this.ready = bl;
        }

        public final boolean add(float x, float y, float w, float h, float presence, float blurPhase) {
            if (this.cardCount >= 64 || w <= 0.5f || h <= 0.5f || !Float.isFinite(presence) || !Float.isFinite(blurPhase)) {
                return false;
            }
            int off = (this.cardCount + 1) * 6;
            this.mask[off] = x;
            this.mask[off + 1] = y;
            this.mask[off + 2] = w;
            this.mask[off + 3] = h;
            this.mask[off + 4] = Math.max(0.0f, Math.min(1.0f, presence));
            this.mask[off + 5] = Math.max(0.0f, Math.min(1.0f, blurPhase));
            this.maxPhase = Math.max(this.maxPhase, this.mask[off + 5]);
            int n = this.cardCount;
            this.cardCount = n + 1;
            return true;
        }

        public final void finish() {
            if (this.cardCount <= 0 || this.maxPhase <= 0.003f) {
                return;
            }
            this.mask[0] = this.zoneX - 48.0f;
            this.mask[1] = this.zoneY - 48.0f;
            this.mask[2] = this.zoneW + 96.0f;
            this.mask[3] = this.zoneH + 96.0f;
            this.mask[4] = -1.0f;
            this.mask[5] = 0.0f;
            int n = this.cardCount;
            for (int i = 0; i < n; ++i) {
                int off = (i + 1) * 6;
                float[] fArray = this.mask;
                int n2 = off + 5;
                fArray[n2] = fArray[n2] / this.maxPhase;
            }
            this.radius = Math.max(0.5f, 22.0f * this.radiusScale * this.maxPhase);
            float scissorPad = 48.0f + this.radius * 2.0f + 8.0f;
            this.boundsX = MathKt.roundToInt((float)(this.zoneX - scissorPad));
            this.boundsY = MathKt.roundToInt((float)(this.zoneY - scissorPad));
            this.boundsW = MathKt.roundToInt((float)(this.zoneW + scissorPad * 2.0f));
            this.boundsH = MathKt.roundToInt((float)(this.zoneH + scissorPad * 2.0f));
            this.originX = this.zoneX + this.zoneW * 0.5f;
            this.originY = this.zoneY + this.zoneH * 0.5f;
            this.ready = true;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\u000bJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000bJ\u0010\u0010\u000e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000bJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u000bJB\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0011\u0010\u0018\u001a\u00020\u0017H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0011\u0010\u001b\u001a\u00020\u001aH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001b\u0010\u001cR\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001d\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u001eR\u0019\u0010\u0004\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001d\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u001eR\u0019\u0010\u0005\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001d\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u001eR\u0019\u0010\u0006\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001d\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u001eR\u0019\u0010\u0007\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001d\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u001e\u00a8\u0006\u001f"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$CardBlurTransform;", "Ljava/lang/Record;", "", "offsetX", "offsetY", "scaleX", "scaleY", "radiusScale", "<init>", "(FFFFF)V", "component1", "()F", "component2", "component3", "component4", "component5", "copy", "(FFFFF)Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$CardBlurTransform;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmField;", "F", "rtx.kimiko:kimiko"})
    private static final class CardBlurTransform
    {
        @JvmField
        public final float offsetX;
        @JvmField
        public final float offsetY;
        @JvmField
        public final float scaleX;
        @JvmField
        public final float scaleY;
        @JvmField
        public final float radiusScale;

        public CardBlurTransform(float offsetX, float offsetY, float scaleX, float scaleY, float radiusScale) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.scaleX = scaleX;
            this.scaleY = scaleY;
            this.radiusScale = radiusScale;
        }

        public final float component1() {
            return this.offsetX;
        }

        public final float component2() {
            return this.offsetY;
        }

        public final float component3() {
            return this.scaleX;
        }

        public final float component4() {
            return this.scaleY;
        }

        public final float component5() {
            return this.radiusScale;
        }

        @NotNull
        public final CardBlurTransform copy(float offsetX, float offsetY, float scaleX, float scaleY, float radiusScale) {
            return new CardBlurTransform(offsetX, offsetY, scaleX, scaleY, radiusScale);
        }

        public static /* synthetic */ CardBlurTransform copy$default(CardBlurTransform cardBlurTransform, float f, float f2, float f3, float f4, float f5, int n, Object object) {
            if ((n & 1) != 0) {
                f = cardBlurTransform.offsetX;
            }
            if ((n & 2) != 0) {
                f2 = cardBlurTransform.offsetY;
            }
            if ((n & 4) != 0) {
                f3 = cardBlurTransform.scaleX;
            }
            if ((n & 8) != 0) {
                f4 = cardBlurTransform.scaleY;
            }
            if ((n & 0x10) != 0) {
                f5 = cardBlurTransform.radiusScale;
            }
            return cardBlurTransform.copy(f, f2, f3, f4, f5);
        }

        @Override
        @NotNull
        public String toString() {
            return "CardBlurTransform(offsetX=" + this.offsetX + ", offsetY=" + this.offsetY + ", scaleX=" + this.scaleX + ", scaleY=" + this.scaleY + ", radiusScale=" + this.radiusScale + ")";
        }

        @Override
        public int hashCode() {
            int result = Float.hashCode(this.offsetX);
            result = result * 31 + Float.hashCode(this.offsetY);
            result = result * 31 + Float.hashCode(this.scaleX);
            result = result * 31 + Float.hashCode(this.scaleY);
            result = result * 31 + Float.hashCode(this.radiusScale);
            return result;
        }

        @Override
        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CardBlurTransform)) {
                return false;
            }
            CardBlurTransform cardBlurTransform = (CardBlurTransform)other;
            if (Float.compare(this.offsetX, cardBlurTransform.offsetX) != 0) {
                return false;
            }
            if (Float.compare(this.offsetY, cardBlurTransform.offsetY) != 0) {
                return false;
            }
            if (Float.compare(this.scaleX, cardBlurTransform.scaleX) != 0) {
                return false;
            }
            if (Float.compare(this.scaleY, cardBlurTransform.scaleY) != 0) {
                return false;
            }
            return Float.compare(this.radiusScale, cardBlurTransform.radiusScale) == 0;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B{\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\u0004\u0012\u0006\u0010\u0010\u001a\u00020\u0004\u0012\u0006\u0010\u0011\u001a\u00020\u0004\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u0014\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001b\u0010\u001aJ\u0010\u0010\u001c\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001c\u0010\u001aJ\u0010\u0010\u001d\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001d\u0010\u001aJ\u0010\u0010\u001e\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001e\u0010\u001aJ\u0010\u0010\u001f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u001f\u0010\u001aJ\u0012\u0010 \u001a\u0004\u0018\u00010\u000bH\u00c6\u0003\u00a2\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020\rH\u00c6\u0003\u00a2\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b$\u0010\u001aJ\u0010\u0010%\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b%\u0010\u001aJ\u0010\u0010&\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b&\u0010\u001aJ\u0012\u0010'\u001a\u0004\u0018\u00010\u0012H\u00c6\u0003\u00a2\u0006\u0004\b'\u0010(J\u0010\u0010)\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b)\u0010\u001aJ\u00a0\u0001\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00042\b\b\u0002\u0010\u0011\u001a\u00020\u00042\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b*\u0010+J\u001b\u0010/\u001a\u00020.2\b\u0010-\u001a\u0004\u0018\u00010,H\u00d6\u0083\u0004\u00a2\u0006\u0004\b/\u00100J\u0011\u00102\u001a\u000201H\u00d6\u0081\u0004\u00a2\u0006\u0004\b2\u00103J\u0011\u00105\u001a\u000204H\u00d6\u0081\u0004\u00a2\u0006\u0004\b5\u00106R\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b7\u00a2\u0006\u0006\n\u0004\b\u0003\u00108R\u0019\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b7\u00a2\u0006\u0006\n\u0004\b\u0005\u00109R\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b7\u00a2\u0006\u0006\n\u0004\b\u0006\u00109R\u0019\u0010\u0007\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b7\u00a2\u0006\u0006\n\u0004\b\u0007\u00109R\u0019\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b7\u00a2\u0006\u0006\n\u0004\b\b\u00109R\u0019\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b7\u00a2\u0006\u0006\n\u0004\b\t\u00109R\u0019\u0010\n\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b7\u00a2\u0006\u0006\n\u0004\b\n\u00109R\u001b\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004\u0092\u0002\u0002\b7\u00a2\u0006\u0006\n\u0004\b\f\u0010:R\u0019\u0010\u000e\u001a\u00020\r8\u0006X\u0087\u0004\u0092\u0002\u0002\b7\u00a2\u0006\u0006\n\u0004\b\u000e\u0010;R\u0019\u0010\u000f\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b7\u00a2\u0006\u0006\n\u0004\b\u000f\u00109R\u0019\u0010\u0010\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b7\u00a2\u0006\u0006\n\u0004\b\u0010\u00109R\u0019\u0010\u0011\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b7\u00a2\u0006\u0006\n\u0004\b\u0011\u00109R\u001b\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006X\u0087\u0004\u0092\u0002\u0002\b7\u00a2\u0006\u0006\n\u0004\b\u0013\u0010<R\u0019\u0010\u0014\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b7\u00a2\u0006\u0006\n\u0004\b\u0014\u00109\u00a8\u0006="}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$PanelQuad;", "Ljava/lang/Record;", "Lorg/joml/Matrix4f;", "matrix", "", "quadW", "quadH", "fbX", "fbY", "fbW", "fbH", "Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$Shatter;", "shatter", "Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiPanel;", "panel", "pivotX", "pivotY", "forwardOffset", "Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$SlotBlit;", "blit", "opacity", "<init>", "(Lorg/joml/Matrix4f;FFFFFFLrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$Shatter;Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiPanel;FFFLrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$SlotBlit;F)V", "component1", "()Lorg/joml/Matrix4f;", "component2", "()F", "component3", "component4", "component5", "component6", "component7", "component8", "()Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$Shatter;", "component9", "()Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiPanel;", "component10", "component11", "component12", "component13", "()Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$SlotBlit;", "component14", "copy", "(Lorg/joml/Matrix4f;FFFFFFLrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$Shatter;Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiPanel;FFFLrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$SlotBlit;F)Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$PanelQuad;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmField;", "Lorg/joml/Matrix4f;", "F", "Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$Shatter;", "Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiPanel;", "Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$SlotBlit;", "rtx.kimiko:kimiko"})
    public static final class PanelQuad
    {
        @JvmField
        @NotNull
        public final Matrix4f matrix;
        @JvmField
        public final float quadW;
        @JvmField
        public final float quadH;
        @JvmField
        public final float fbX;
        @JvmField
        public final float fbY;
        @JvmField
        public final float fbW;
        @JvmField
        public final float fbH;
        @JvmField
        @Nullable
        public final Shatter shatter;
        @JvmField
        @NotNull
        public final RemoteGuiPanel panel;
        @JvmField
        public final float pivotX;
        @JvmField
        public final float pivotY;
        @JvmField
        public final float forwardOffset;
        @JvmField
        @Nullable
        public final SlotBlit blit;
        @JvmField
        public final float opacity;

        public PanelQuad(@NotNull Matrix4f matrix, float quadW, float quadH, float fbX, float fbY, float fbW, float fbH, @Nullable Shatter shatter, @NotNull RemoteGuiPanel panel, float pivotX, float pivotY, float forwardOffset, @Nullable SlotBlit blit, float opacity) {
            Intrinsics.checkNotNullParameter((Object)matrix, (String)"matrix");
            Intrinsics.checkNotNullParameter((Object)panel, (String)"panel");
            this.matrix = matrix;
            this.quadW = quadW;
            this.quadH = quadH;
            this.fbX = fbX;
            this.fbY = fbY;
            this.fbW = fbW;
            this.fbH = fbH;
            this.shatter = shatter;
            this.panel = panel;
            this.pivotX = pivotX;
            this.pivotY = pivotY;
            this.forwardOffset = forwardOffset;
            this.blit = blit;
            this.opacity = opacity;
        }

        @NotNull
        public final Matrix4f component1() {
            return this.matrix;
        }

        public final float component2() {
            return this.quadW;
        }

        public final float component3() {
            return this.quadH;
        }

        public final float component4() {
            return this.fbX;
        }

        public final float component5() {
            return this.fbY;
        }

        public final float component6() {
            return this.fbW;
        }

        public final float component7() {
            return this.fbH;
        }

        @Nullable
        public final Shatter component8() {
            return this.shatter;
        }

        @NotNull
        public final RemoteGuiPanel component9() {
            return this.panel;
        }

        public final float component10() {
            return this.pivotX;
        }

        public final float component11() {
            return this.pivotY;
        }

        public final float component12() {
            return this.forwardOffset;
        }

        @Nullable
        public final SlotBlit component13() {
            return this.blit;
        }

        public final float component14() {
            return this.opacity;
        }

        @NotNull
        public final PanelQuad copy(@NotNull Matrix4f matrix, float quadW, float quadH, float fbX, float fbY, float fbW, float fbH, @Nullable Shatter shatter, @NotNull RemoteGuiPanel panel, float pivotX, float pivotY, float forwardOffset, @Nullable SlotBlit blit, float opacity) {
            Intrinsics.checkNotNullParameter((Object)matrix, (String)"matrix");
            Intrinsics.checkNotNullParameter((Object)panel, (String)"panel");
            return new PanelQuad(matrix, quadW, quadH, fbX, fbY, fbW, fbH, shatter, panel, pivotX, pivotY, forwardOffset, blit, opacity);
        }

        public static /* synthetic */ PanelQuad copy$default(PanelQuad panelQuad, Matrix4f matrix4f, float f, float f2, float f3, float f4, float f5, float f6, Shatter shatter, RemoteGuiPanel remoteGuiPanel, float f7, float f8, float f9, SlotBlit slotBlit, float f10, int n, Object object) {
            if ((n & 1) != 0) {
                matrix4f = panelQuad.matrix;
            }
            if ((n & 2) != 0) {
                f = panelQuad.quadW;
            }
            if ((n & 4) != 0) {
                f2 = panelQuad.quadH;
            }
            if ((n & 8) != 0) {
                f3 = panelQuad.fbX;
            }
            if ((n & 0x10) != 0) {
                f4 = panelQuad.fbY;
            }
            if ((n & 0x20) != 0) {
                f5 = panelQuad.fbW;
            }
            if ((n & 0x40) != 0) {
                f6 = panelQuad.fbH;
            }
            if ((n & 0x80) != 0) {
                shatter = panelQuad.shatter;
            }
            if ((n & 0x100) != 0) {
                remoteGuiPanel = panelQuad.panel;
            }
            if ((n & 0x200) != 0) {
                f7 = panelQuad.pivotX;
            }
            if ((n & 0x400) != 0) {
                f8 = panelQuad.pivotY;
            }
            if ((n & 0x800) != 0) {
                f9 = panelQuad.forwardOffset;
            }
            if ((n & 0x1000) != 0) {
                slotBlit = panelQuad.blit;
            }
            if ((n & 0x2000) != 0) {
                f10 = panelQuad.opacity;
            }
            return panelQuad.copy(matrix4f, f, f2, f3, f4, f5, f6, shatter, remoteGuiPanel, f7, f8, f9, slotBlit, f10);
        }

        @Override
        @NotNull
        public String toString() {
            return "PanelQuad(matrix=" + this.matrix + ", quadW=" + this.quadW + ", quadH=" + this.quadH + ", fbX=" + this.fbX + ", fbY=" + this.fbY + ", fbW=" + this.fbW + ", fbH=" + this.fbH + ", shatter=" + this.shatter + ", panel=" + this.panel + ", pivotX=" + this.pivotX + ", pivotY=" + this.pivotY + ", forwardOffset=" + this.forwardOffset + ", blit=" + this.blit + ", opacity=" + this.opacity + ")";
        }

        @Override
        public int hashCode() {
            int result = this.matrix.hashCode();
            result = result * 31 + Float.hashCode(this.quadW);
            result = result * 31 + Float.hashCode(this.quadH);
            result = result * 31 + Float.hashCode(this.fbX);
            result = result * 31 + Float.hashCode(this.fbY);
            result = result * 31 + Float.hashCode(this.fbW);
            result = result * 31 + Float.hashCode(this.fbH);
            result = result * 31 + (this.shatter == null ? 0 : this.shatter.hashCode());
            result = result * 31 + this.panel.hashCode();
            result = result * 31 + Float.hashCode(this.pivotX);
            result = result * 31 + Float.hashCode(this.pivotY);
            result = result * 31 + Float.hashCode(this.forwardOffset);
            result = result * 31 + (this.blit == null ? 0 : this.blit.hashCode());
            result = result * 31 + Float.hashCode(this.opacity);
            return result;
        }

        @Override
        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PanelQuad)) {
                return false;
            }
            PanelQuad panelQuad = (PanelQuad)other;
            if (!Intrinsics.areEqual((Object)this.matrix, (Object)panelQuad.matrix)) {
                return false;
            }
            if (Float.compare(this.quadW, panelQuad.quadW) != 0) {
                return false;
            }
            if (Float.compare(this.quadH, panelQuad.quadH) != 0) {
                return false;
            }
            if (Float.compare(this.fbX, panelQuad.fbX) != 0) {
                return false;
            }
            if (Float.compare(this.fbY, panelQuad.fbY) != 0) {
                return false;
            }
            if (Float.compare(this.fbW, panelQuad.fbW) != 0) {
                return false;
            }
            if (Float.compare(this.fbH, panelQuad.fbH) != 0) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.shatter, (Object)panelQuad.shatter)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.panel, (Object)panelQuad.panel)) {
                return false;
            }
            if (Float.compare(this.pivotX, panelQuad.pivotX) != 0) {
                return false;
            }
            if (Float.compare(this.pivotY, panelQuad.pivotY) != 0) {
                return false;
            }
            if (Float.compare(this.forwardOffset, panelQuad.forwardOffset) != 0) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.blit, (Object)panelQuad.blit)) {
                return false;
            }
            return Float.compare(this.opacity, panelQuad.opacity) == 0;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u0010J\u0010\u0010\u0013\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0010J\u0010\u0010\u0014\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0010J\u0010\u0010\u0015\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0010JV\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u001b\u001a\u00020\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0011\u0010\u001e\u001a\u00020\u001dH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0011\u0010!\u001a\u00020 H\u00d6\u0081\u0004\u00a2\u0006\u0004\b!\u0010\"R\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b\u0003\u0010$R\u0019\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b\u0005\u0010%R\u0019\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b\u0006\u0010%R\u0019\u0010\u0007\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b\u0007\u0010%R\u0019\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b\b\u0010%R\u0019\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b\t\u0010%R\u0019\u0010\n\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b\n\u0010%\u00a8\u0006&"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$Shatter;", "Ljava/lang/Record;", "Lrtx/kimiko/api/ui/window/GuiShatterAnimation$State;", "state", "", "progress", "alpha", "offsetX", "offsetY", "scaleX", "scaleY", "<init>", "(Lrtx/kimiko/api/ui/window/GuiShatterAnimation$State;FFFFFF)V", "component1", "()Lrtx/kimiko/api/ui/window/GuiShatterAnimation$State;", "component2", "()F", "component3", "component4", "component5", "component6", "component7", "copy", "(Lrtx/kimiko/api/ui/window/GuiShatterAnimation$State;FFFFFF)Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$Shatter;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmField;", "Lrtx/kimiko/api/ui/window/GuiShatterAnimation$State;", "F", "rtx.kimiko:kimiko"})
    public static final class Shatter
    {
        @JvmField
        @NotNull
        public final GuiShatterAnimation.State state;
        @JvmField
        public final float progress;
        @JvmField
        public final float alpha;
        @JvmField
        public final float offsetX;
        @JvmField
        public final float offsetY;
        @JvmField
        public final float scaleX;
        @JvmField
        public final float scaleY;

        public Shatter(@NotNull GuiShatterAnimation.State state, float progress, float alpha, float offsetX, float offsetY, float scaleX, float scaleY) {
            Intrinsics.checkNotNullParameter((Object)state, (String)"state");
            this.state = state;
            this.progress = progress;
            this.alpha = alpha;
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.scaleX = scaleX;
            this.scaleY = scaleY;
        }

        @NotNull
        public final GuiShatterAnimation.State component1() {
            return this.state;
        }

        public final float component2() {
            return this.progress;
        }

        public final float component3() {
            return this.alpha;
        }

        public final float component4() {
            return this.offsetX;
        }

        public final float component5() {
            return this.offsetY;
        }

        public final float component6() {
            return this.scaleX;
        }

        public final float component7() {
            return this.scaleY;
        }

        @NotNull
        public final Shatter copy(@NotNull GuiShatterAnimation.State state, float progress, float alpha, float offsetX, float offsetY, float scaleX, float scaleY) {
            Intrinsics.checkNotNullParameter((Object)state, (String)"state");
            return new Shatter(state, progress, alpha, offsetX, offsetY, scaleX, scaleY);
        }

        public static /* synthetic */ Shatter copy$default(Shatter shatter, GuiShatterAnimation.State state, float f, float f2, float f3, float f4, float f5, float f6, int n, Object object) {
            if ((n & 1) != 0) {
                state = shatter.state;
            }
            if ((n & 2) != 0) {
                f = shatter.progress;
            }
            if ((n & 4) != 0) {
                f2 = shatter.alpha;
            }
            if ((n & 8) != 0) {
                f3 = shatter.offsetX;
            }
            if ((n & 0x10) != 0) {
                f4 = shatter.offsetY;
            }
            if ((n & 0x20) != 0) {
                f5 = shatter.scaleX;
            }
            if ((n & 0x40) != 0) {
                f6 = shatter.scaleY;
            }
            return shatter.copy(state, f, f2, f3, f4, f5, f6);
        }

        @Override
        @NotNull
        public String toString() {
            return "Shatter(state=" + this.state + ", progress=" + this.progress + ", alpha=" + this.alpha + ", offsetX=" + this.offsetX + ", offsetY=" + this.offsetY + ", scaleX=" + this.scaleX + ", scaleY=" + this.scaleY + ")";
        }

        @Override
        public int hashCode() {
            int result = this.state.hashCode();
            result = result * 31 + Float.hashCode(this.progress);
            result = result * 31 + Float.hashCode(this.alpha);
            result = result * 31 + Float.hashCode(this.offsetX);
            result = result * 31 + Float.hashCode(this.offsetY);
            result = result * 31 + Float.hashCode(this.scaleX);
            result = result * 31 + Float.hashCode(this.scaleY);
            return result;
        }

        @Override
        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Shatter)) {
                return false;
            }
            Shatter shatter = (Shatter)other;
            if (!Intrinsics.areEqual((Object)this.state, (Object)shatter.state)) {
                return false;
            }
            if (Float.compare(this.progress, shatter.progress) != 0) {
                return false;
            }
            if (Float.compare(this.alpha, shatter.alpha) != 0) {
                return false;
            }
            if (Float.compare(this.offsetX, shatter.offsetX) != 0) {
                return false;
            }
            if (Float.compare(this.offsetY, shatter.offsetY) != 0) {
                return false;
            }
            if (Float.compare(this.scaleX, shatter.scaleX) != 0) {
                return false;
            }
            return Float.compare(this.scaleY, shatter.scaleY) == 0;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0015\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u000eJ\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u000eJ\u0010\u0010\u0012\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0012\u0010\u000eJ\u0010\u0010\u0013\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u000eJ\u0010\u0010\u0014\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u000eJ\u0010\u0010\u0015\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u000eJ`\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u001b\u001a\u00020\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0011\u0010\u001e\u001a\u00020\u001dH\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0011\u0010!\u001a\u00020 H\u00d6\u0081\u0004\u00a2\u0006\u0004\b!\u0010\"R\u0019\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b\u0003\u0010$R\u0019\u0010\u0004\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b\u0004\u0010$R\u0019\u0010\u0005\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b\u0005\u0010$R\u0019\u0010\u0006\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b\u0006\u0010$R\u0019\u0010\u0007\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b\u0007\u0010$R\u0019\u0010\b\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b\b\u0010$R\u0019\u0010\t\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b\t\u0010$R\u0019\u0010\n\u001a\u00020\u00028\u0006X\u0087\u0004\u0092\u0002\u0002\b#\u00a2\u0006\u0006\n\u0004\b\n\u0010$\u00a8\u0006%"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$SlotBlit;", "Ljava/lang/Record;", "", "srcX", "srcY", "srcW", "srcH", "clipX", "clipY", "clipW", "clipH", "<init>", "(FFFFFFFF)V", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(FFFFFFFF)Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteGuiWorld$SlotBlit;", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmField;", "F", "rtx.kimiko:kimiko"})
    public static final class SlotBlit
    {
        @JvmField
        public final float srcX;
        @JvmField
        public final float srcY;
        @JvmField
        public final float srcW;
        @JvmField
        public final float srcH;
        @JvmField
        public final float clipX;
        @JvmField
        public final float clipY;
        @JvmField
        public final float clipW;
        @JvmField
        public final float clipH;

        public SlotBlit(float srcX, float srcY, float srcW, float srcH, float clipX, float clipY, float clipW, float clipH) {
            this.srcX = srcX;
            this.srcY = srcY;
            this.srcW = srcW;
            this.srcH = srcH;
            this.clipX = clipX;
            this.clipY = clipY;
            this.clipW = clipW;
            this.clipH = clipH;
        }

        public final float component1() {
            return this.srcX;
        }

        public final float component2() {
            return this.srcY;
        }

        public final float component3() {
            return this.srcW;
        }

        public final float component4() {
            return this.srcH;
        }

        public final float component5() {
            return this.clipX;
        }

        public final float component6() {
            return this.clipY;
        }

        public final float component7() {
            return this.clipW;
        }

        public final float component8() {
            return this.clipH;
        }

        @NotNull
        public final SlotBlit copy(float srcX, float srcY, float srcW, float srcH, float clipX, float clipY, float clipW, float clipH) {
            return new SlotBlit(srcX, srcY, srcW, srcH, clipX, clipY, clipW, clipH);
        }

        public static /* synthetic */ SlotBlit copy$default(SlotBlit slotBlit, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n, Object object) {
            if ((n & 1) != 0) {
                f = slotBlit.srcX;
            }
            if ((n & 2) != 0) {
                f2 = slotBlit.srcY;
            }
            if ((n & 4) != 0) {
                f3 = slotBlit.srcW;
            }
            if ((n & 8) != 0) {
                f4 = slotBlit.srcH;
            }
            if ((n & 0x10) != 0) {
                f5 = slotBlit.clipX;
            }
            if ((n & 0x20) != 0) {
                f6 = slotBlit.clipY;
            }
            if ((n & 0x40) != 0) {
                f7 = slotBlit.clipW;
            }
            if ((n & 0x80) != 0) {
                f8 = slotBlit.clipH;
            }
            return slotBlit.copy(f, f2, f3, f4, f5, f6, f7, f8);
        }

        @Override
        @NotNull
        public String toString() {
            return "SlotBlit(srcX=" + this.srcX + ", srcY=" + this.srcY + ", srcW=" + this.srcW + ", srcH=" + this.srcH + ", clipX=" + this.clipX + ", clipY=" + this.clipY + ", clipW=" + this.clipW + ", clipH=" + this.clipH + ")";
        }

        @Override
        public int hashCode() {
            int result = Float.hashCode(this.srcX);
            result = result * 31 + Float.hashCode(this.srcY);
            result = result * 31 + Float.hashCode(this.srcW);
            result = result * 31 + Float.hashCode(this.srcH);
            result = result * 31 + Float.hashCode(this.clipX);
            result = result * 31 + Float.hashCode(this.clipY);
            result = result * 31 + Float.hashCode(this.clipW);
            result = result * 31 + Float.hashCode(this.clipH);
            return result;
        }

        @Override
        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SlotBlit)) {
                return false;
            }
            SlotBlit slotBlit = (SlotBlit)other;
            if (Float.compare(this.srcX, slotBlit.srcX) != 0) {
                return false;
            }
            if (Float.compare(this.srcY, slotBlit.srcY) != 0) {
                return false;
            }
            if (Float.compare(this.srcW, slotBlit.srcW) != 0) {
                return false;
            }
            if (Float.compare(this.srcH, slotBlit.srcH) != 0) {
                return false;
            }
            if (Float.compare(this.clipX, slotBlit.clipX) != 0) {
                return false;
            }
            if (Float.compare(this.clipY, slotBlit.clipY) != 0) {
                return false;
            }
            if (Float.compare(this.clipW, slotBlit.clipW) != 0) {
                return false;
            }
            return Float.compare(this.clipH, slotBlit.clipH) == 0;
        }
    }
}

