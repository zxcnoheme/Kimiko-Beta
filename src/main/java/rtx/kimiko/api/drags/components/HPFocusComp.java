/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.client.gl.Framebuffer
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.drags.components;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.LivingEntity;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.drags.DragSystem;
import rtx.kimiko.api.drags.Draggable;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.HPFocus;
import rtx.kimiko.utils.animations.HudFadeAnimation;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.network.Network;
import rtx.kimiko.utils.render.modules.post.hpfocus.HPFocusRenderer;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.Render2DCoordinateSpace;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\n\u0010\tJ\u000f\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0014\u00a2\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/api/drags/components/HPFocusComp;", "Lrtx/kimiko/api/drags/Draggable;", "<init>", "()V", "", "displayName", "()Ljava/lang/String;", "", "width", "()F", "height", "", "isInteractive", "()Z", "Lnet/minecraft/DrawContext;", "graphics", "", "render", "(Lnet/minecraft/DrawContext;)V", "Lrtx/kimiko/utils/animations/HudFadeAnimation;", "visibility", "Lrtx/kimiko/utils/animations/HudFadeAnimation;", "Companion", "rtx.kimiko:kimiko"})
public final class HPFocusComp
extends Draggable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final HudFadeAnimation visibility = this.getHudFade();
    private static final float BASE_HEIGHT = 46.0f;
    private static final float RADIUS = 5.0f;
    private static final float BORDER = 2.0f;
    private static final float DEFAULT_ASPECT = 3.8461537f;

    public HPFocusComp() {
        super("hpfocus", 342.0f, 40.0f);
        this.visibility.set(0.0);
    }

    @Override
    @NotNull
    public String displayName() {
        return "HP Focus";
    }

    @Override
    public float width() {
        return HPFocusComp.Companion.sizeFor(HPFocusComp.Companion.module())[0];
    }

    @Override
    public float height() {
        return HPFocusComp.Companion.sizeFor(HPFocusComp.Companion.module())[1];
    }

    @Override
    public boolean isInteractive() {
        HPFocus module = HPFocusComp.Companion.module();
        return module != null && module.isEnabled();
    }

    @Override
    protected void render(@NotNull DrawContext graphics) {
        boolean bl;
        boolean hideGui;
        Intrinsics.checkNotNullParameter((Object)graphics, (String)"graphics");
        HPFocus module = HPFocusComp.Companion.module();
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        boolean enabled = module != null && module.isEnabled();
        boolean dragMode = DragSystem.Companion.get().isDragModeActive();
        boolean bl2 = hideGui = mc.options != null && mc.options.hudHidden;
        if (!enabled || hideGui || mc.player == null) {
            bl = false;
        } else if (dragMode) {
            bl = true;
        } else if (mc.player.isCreative() || mc.player.isSpectator() || !mc.player.isAlive()) {
            bl = false;
            this.visibility.set(0.0);
        } else {
            float f = Network.getResolvedHealth((LivingEntity)mc.player, true);
            HPFocus hPFocus = module;
            Intrinsics.checkNotNull((Object)hPFocus);
            bl = f <= hPFocus.hpThresholdHp();
        }
        boolean wantVisible = bl;
        this.visibility.updateTarget(wantVisible);
        float alpha = this.visibility.get();
        if (alpha <= 0.01f) {
            return;
        }
        HPFocusRenderer.requestCapture();
        float[] size = HPFocusComp.Companion.sizeFor(module);
        float w = size[0];
        float h = size[1];
        float x = this.getX();
        float y = this.getY();
        Render2D.beginFrame(graphics);
        RectUtil.drawClientRect(x, y, w, h, 5.0f, alpha);
        float innerW = w - 4.0f;
        float innerH = h - 4.0f;
        if (innerW > 1.0f && innerH > 1.0f && HPFocusRenderer.hasCapture()) {
            Framebuffer framebuffer2 = mc.getFramebuffer();
            Intrinsics.checkNotNullExpressionValue((Object)framebuffer2, (String)"getMainRenderTarget(...)");
            Framebuffer main = framebuffer2;
            if (main.textureWidth > 0 && main.textureHeight > 0) {
                float fbW = main.textureWidth;
                float fbH = main.textureHeight;
                int guiScale = Render2DCoordinateSpace.guiScale();
                HPFocus hPFocus = module;
                Intrinsics.checkNotNull((Object)hPFocus);
                float halfSrcW = hPFocus.captureWidthGui() * (float)guiScale * 0.5f;
                float u0 = HPFocusComp.Companion.clamp01((fbW * 0.5f - halfSrcW) / fbW);
                float u1 = HPFocusComp.Companion.clamp01((fbW * 0.5f + halfSrcW) / fbW);
                float v0 = HPFocusComp.Companion.clamp01(module.captureHeightGui() * (float)guiScale / fbH);
                float v1 = 0.0f;
                Render2D.imageUv("kimiko:hpfocus_scene", x + 2.0f, y + 2.0f, innerW, innerH, Math.max(0.0f, 3.0f), 1.0f, u0, v0, u1, v1, ColorEngine.multAlpha(-1, alpha));
            }
        }
        Render2D.flush();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\b\u001a\u00020\u00072\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u000fR\u0014\u0010\u0012\u001a\u00020\n8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u000f\u00a8\u0006\u0013"}, d2={"Lrtx/kimiko/api/drags/components/HPFocusComp.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Interface/HPFocus;", "module", "()Lrtx/kimiko/api/modules/impl/Interface/HPFocus;", "", "sizeFor", "(Lrtx/kimiko/api/modules/impl/Interface/HPFocus;)[F", "", "value", "clamp01", "(F)F", "BASE_HEIGHT", "F", "RADIUS", "BORDER", "DEFAULT_ASPECT", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final HPFocus module() {
            return ModuleManager.Companion.get().get(HPFocus.class);
        }

        private final float[] sizeFor(HPFocus module) {
            HPFocus hPFocus = module;
            float scale = hPFocus != null ? hPFocus.scale() : 1.3f;
            HPFocus hPFocus2 = module;
            float cw = hPFocus2 != null ? hPFocus2.captureWidthGui() : 200.0f;
            HPFocus hPFocus3 = module;
            float ch = hPFocus3 != null ? hPFocus3.captureHeightGui() : 52.0f;
            float aspect = ch > 0.5f ? cw / ch : 3.8461537f;
            float h = 46.0f * scale;
            float[] fArray = new float[]{h * aspect, h};
            return fArray;
        }

        private final float clamp01(float value) {
            return Math.max(0.0f, Math.min(1.0f, value));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

