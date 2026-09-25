/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.EquipmentSlot
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.client.util.math.MatrixStack
 *  net.minecraft.client.util.math.MatrixStack.Entry
 *  net.minecraft.client.render.VertexConsumer
 *  net.minecraft.client.render.VertexConsumerProvider.Immediate
 *  net.minecraft.client.option.Perspective
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.AbstractClientPlayerEntity
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.lwjgl.opengl.GL11
 */
package rtx.kimiko.api.modules.impl.Visuals;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.lwjgl.opengl.GL11;
import rtx.kimiko.api.events.impl.render.WorldRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;
import rtx.kimiko.api.modules.settings.Setting;
import rtx.kimiko.api.modules.settings.impl.BooleanSetting;
import rtx.kimiko.api.modules.settings.impl.ColorSetting;
import rtx.kimiko.api.modules.settings.impl.ModeSetting;
import rtx.kimiko.api.modules.settings.impl.SeparatorSetting;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.others.pipeline.ClientPipelines;
import rtx.kimiko.utils.render.render2d.ClientPalette;
import rtx.kimiko.utils.storage.friend.FriendUtils;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"chinahat"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 L2\u00020\u0001:\u0002MLB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u0004H\u0015b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\t\u0010\u0003J\u000f\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0016\u0010\u0017J'\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0018H\u0007b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\u001a\u00a2\u0006\u0004\b\u001b\u0010\u001cJ7\u0010!\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010\u0014\u001a\u00020\u0013H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\u001a\u00a2\u0006\u0004\b!\u0010\"JG\u0010(\u001a\u00020\u00042\u0006\u0010$\u001a\u00020#2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\n2\u0006\u0010'\u001a\u00020\nH\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\u001a\u00a2\u0006\u0004\b(\u0010)J\u0017\u0010*\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b*\u0010+J\u0017\u0010,\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b,\u0010\u0017J/\u00100\u001a\u00020\r2\u0006\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020\nH\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078/\u00a2\u0006\u0004\b0\u00101R\u0014\u00103\u001a\u0002028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b3\u00104R\u0014\u00106\u001a\u0002058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b6\u00107R\u0014\u00108\u001a\u0002028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00104R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010=\u001a\u00020<8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u0010>R\u0014\u0010?\u001a\u00020<8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u0010>R \u0010B\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020A0@8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0014\u0010E\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010FR\u0014\u0010G\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bG\u0010FR\u0014\u0010H\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bH\u0010FR\u0014\u0010I\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u0010FR\u0014\u0010J\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010FR\u0014\u0010K\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bK\u0010F\u00ca\u0001\u0010\bN\u0012\f\b\u0006\u0012\b\b\fJ\u0004\b\b(O\u00a8\u0006P"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ChinaHat;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "onDisable", "", "fadeOutSeconds", "()F", "", "playerId", "Lorg/joml/Matrix4f;", "matrix", "captureTransform", "(ILorg/joml/Matrix4f;)V", "Lnet/minecraft/AbstractClientPlayerEntity;", "player", "", "shouldRender", "(Lnet/minecraft/AbstractClientPlayerEntity;)Z", "Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;", "event", "STD", "renderAfterPostEffects", "(Lrtx/kimiko/api/events/impl/render/WorldRenderEvent;)V", "Lnet/minecraft/MatrixStack;", "stack", "Lnet/minecraft/VertexConsumer;", "body", "renderHat", "(Lnet/minecraft/MatrixStack;Lnet/minecraft/VertexConsumer;Lnet/minecraft/AbstractClientPlayerEntity;)V", "Lnet/minecraft/MatrixStack$Entry;", "pose", "ringRadius", "ringY", "tubeR", "emitOutlineTube", "(Lnet/minecraft/MatrixStack$Entry;Lnet/minecraft/VertexConsumer;FFF)V", "getYOffset", "(Lnet/minecraft/AbstractClientPlayerEntity;)F", "hasVisibleHelmet", "index", "rawAlpha", "MAX", "modeColor", "(IF)I", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "renderMode", "Lrtx/kimiko/api/modules/settings/impl/ModeSetting;", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "colorSeparator", "Lrtx/kimiko/api/modules/settings/impl/SeparatorSetting;", "colorMode", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "useSecondColor", "Lrtx/kimiko/api/modules/settings/impl/BooleanSetting;", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customColor", "Lrtx/kimiko/api/modules/settings/impl/ColorSetting;", "customSecondColor", "", "Lrtx/kimiko/api/modules/impl/Visuals/ChinaHat$HatTransform;", "hatTransforms", "Ljava/util/Map;", "", "scratchPx", "[F", "scratchPy", "scratchPz", "scratchCx", "scratchCy", "scratchCz", "Companion", "HatTransform", "Lrtx/kimiko/api/liteapi/Feature;", "chinahat", "rtx.kimiko:kimiko"})
public final class ChinaHat
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ModeSetting renderMode;
    @NotNull
    private final SeparatorSetting colorSeparator;
    @NotNull
    private final ModeSetting colorMode;
    @NotNull
    private final BooleanSetting useSecondColor;
    @NotNull
    private final ColorSetting customColor;
    @NotNull
    private final ColorSetting customSecondColor;
    @NotNull
    private final Map<Integer, HatTransform> hatTransforms;
    @NotNull
    private final float[] scratchPx;
    @NotNull
    private final float[] scratchPy;
    @NotNull
    private final float[] scratchPz;
    @NotNull
    private final float[] scratchCx;
    @NotNull
    private final float[] scratchCy;
    @NotNull
    private final float[] scratchCz;
    private static final int SEGMENTS = 144;
    private static final int OUTLINE_CROSS_SIDES = 16;
    private static final float TAU = (float)Math.PI * 2;
    private static final int DARK_SECOND_COLOR = new Color(16, 16, 16, 75).getRGB();
    private static final long TRANSFORM_TTL_MS = 250L;
    private static final float HAT_SCALE = 1.0f;
    private static final float OUTLINE_WIDTH = 2.5f;
    @NotNull
    private static final String MODE_SELF = "Только себя";
    @NotNull
    private static final String MODE_FRIENDS = "Себя и друзей";
    @NotNull
    private static final String MODE_ALL = "Всех";
    @NotNull
    private static final String COLOR_RAINBOW = "Радуга";
    @NotNull
    private static final String COLOR_CLIENT = "Клиент";
    @NotNull
    private static final String COLOR_CUSTOM = "Свой";
    @JvmField
    @Nullable
    public static ChinaHat INSTANCE;

    public ChinaHat() {
        super("China Hat", "Рисует китайскую шляпу-конус на игроках.", Category.VISUALS);
        String[] stringArray = new String[]{MODE_SELF, MODE_FRIENDS, MODE_ALL};
        this.renderMode = (ModeSetting)this.register((Setting)new ModeSetting("Режим отображения", "На ком рисовать шляпу.", MODE_FRIENDS, stringArray));
        this.colorSeparator = (SeparatorSetting)this.register((Setting)new SeparatorSetting("Цвет"));
        stringArray = new String[]{COLOR_RAINBOW, COLOR_CLIENT, COLOR_CUSTOM};
        this.colorMode = (ModeSetting)this.register((Setting)new ModeSetting("Режим цвета", "Режим цвета шляпы.", COLOR_CLIENT, stringArray));
        this.useSecondColor = (BooleanSetting)this.register((Setting)new BooleanSetting("Второй цвет", "Использовать второй цвет в режиме «Свой».", false));
        this.customColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет", "Основной цвет шляпы.", new Color(255, 255, 255, 255)));
        this.customSecondColor = (ColorSetting)this.register((Setting)new ColorSetting("Цвет 2", "Второй цвет шляпы.", new Color(ColorEngine.lerpColor(-1, DARK_SECOND_COLOR, 0.7f), true)));
        this.hatTransforms = new HashMap();
        this.scratchPx = new float[16];
        this.scratchPy = new float[16];
        this.scratchPz = new float[16];
        this.scratchCx = new float[16];
        this.scratchCy = new float[16];
        this.scratchCz = new float[16];
        INSTANCE = this;
        this.useSecondColor.visibleWhen(() -> ChinaHat._init_$lambda$0(this));
        this.customColor.visibleWhen(() -> ChinaHat._init_$lambda$1(this));
        this.customSecondColor.visibleWhen(() -> ChinaHat._init_$lambda$2(this));
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onDisable() {
        this.hatTransforms.clear();
    }

    @Override
    public float fadeOutSeconds() {
        return 0.5f;
    }

    public final void captureTransform(int playerId, @NotNull Matrix4f matrix) {
        Intrinsics.checkNotNullParameter((Object)matrix, (String)"matrix");
        this.hatTransforms.put(playerId, new HatTransform(matrix, System.currentTimeMillis()));
    }

    public final boolean shouldRender(@NotNull AbstractClientPlayerEntity player) {
        Intrinsics.checkNotNullParameter((Object)player, (String)"player");
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return false;
        }
        ClientPlayerEntity localPlayer = clientPlayerEntity2;
        if (this.renderMode.is(MODE_ALL)) {
            return true;
        }
        if (this.renderMode.is(MODE_FRIENDS)) {
            return Intrinsics.areEqual((Object)player, (Object)localPlayer) || FriendUtils.isFriend(player.getName().getString());
        }
        return Intrinsics.areEqual((Object)player, (Object)localPlayer);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Protect(value=Level.STD)
    public final void renderAfterPostEffects(@NotNull WorldRenderEvent event) {
        Intrinsics.checkNotNullParameter((Object)event, (String)"event");
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return;
        }
        ClientWorld level = clientWorld3;
        ClientPlayerEntity clientPlayerEntity2 = this.mc.player;
        if (clientPlayerEntity2 == null) {
            return;
        }
        ClientPlayerEntity localPlayer = clientPlayerEntity2;
        float partialTicks = event.getPartialTicks();
        Vec3d vec3d2 = event.getCamera() != null ? event.getCamera().getCameraPos() : this.mc.gameRenderer.getCamera().getCameraPos();
        Intrinsics.checkNotNull((Object)vec3d2);
        Vec3d cam = vec3d2;
        MatrixStack stack = event.getStack();
        VertexConsumerProvider.Immediate immediate2 = this.mc.getBufferBuilders().getEntityVertexConsumers();
        Intrinsics.checkNotNullExpressionValue((Object)immediate2, (String)"bufferSource(...)");
        VertexConsumerProvider.Immediate provider = immediate2;
        VertexConsumer vertexConsumer2 = provider.getBuffer(ClientPipelines.CHINA_HAT);
        Intrinsics.checkNotNullExpressionValue((Object)vertexConsumer2, (String)"getBuffer(...)");
        VertexConsumer consumer = vertexConsumer2;
        long now = System.currentTimeMillis();
        boolean rendered = false;
        for (AbstractClientPlayerEntity entity : level.getPlayers()) {
            if (!this.shouldRender(entity) || entity.isInvisible() || entity.isSpectator() || entity.isBaby() || Intrinsics.areEqual((Object)entity, (Object)localPlayer) && !event.isPortalPass() && this.mc.options.getPerspective() == Perspective.FIRST_PERSON) continue;
            HatTransform transform = this.hatTransforms.get(entity.getId());
            if (transform == null || now - transform.getTime() > 250L) continue;
            Vec3d pos = entity.getLerpedPos(partialTicks);
            stack.push();
            stack.translate(pos.x - cam.x, pos.y - cam.y, pos.z - cam.z);
            stack.multiplyPositionMatrix((Matrix4fc)transform.getMatrix());
            this.renderHat(stack, consumer, entity);
            stack.pop();
            rendered = true;
        }
        if (rendered) {
            GL11.glEnable((int)2881);
            GL11.glHint((int)3155, (int)4354);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            try {
                provider.draw(ClientPipelines.CHINA_HAT);
            }
            finally {
                GL11.glDisable((int)2881);
                GL11.glDisable((int)2848);
            }
        }
    }

    @Protect(value=Level.STD)
    private final void renderHat(MatrixStack stack, VertexConsumer body, AbstractClientPlayerEntity player) {
        stack.push();
        stack.translate(0.0f, this.getYOffset(player), 0.0f);
        MatrixStack.Entry entry2 = stack.peek();
        Intrinsics.checkNotNullExpressionValue((Object)entry2, (String)"last(...)");
        MatrixStack.Entry pose = entry2;
        float scale = 1.0f;
        float radius = Math.max(0.3f, player.getWidth()) * scale;
        float baseY = 0.0f;
        float apexY = 0.3f * scale;
        int apexColor = this.modeColor(0, 1.0f);
        for (int i = 0; i < 144; ++i) {
            float t0 = (float)i / 144.0f;
            float t1 = (float)(i + 1) / 144.0f;
            float a0 = t0 * ((float)Math.PI * 2);
            float a1 = t1 * ((float)Math.PI * 2);
            float x0 = MathHelper.sin((double)a0) * radius;
            float z0 = -MathHelper.cos((double)a0) * radius;
            float x1 = MathHelper.sin((double)a1) * radius;
            float z1 = -MathHelper.cos((double)a1) * radius;
            int c0 = this.modeColor((int)(t0 * 720.0f), 0.5f);
            int c1 = this.modeColor((int)(t1 * 720.0f), 0.5f);
            body.vertex(pose, x0, baseY, z0).color(c0);
            body.vertex(pose, x1, baseY, z1).color(c1);
            body.vertex(pose, 0.0f, apexY, 0.0f).color(apexColor);
            body.vertex(pose, 0.0f, apexY, 0.0f).color(apexColor);
        }
        float tubeR = 0.010000001f;
        this.emitOutlineTube(pose, body, radius, baseY, tubeR);
        stack.pop();
    }

    @Protect(value=Level.STD)
    private final void emitOutlineTube(MatrixStack.Entry pose, VertexConsumer body, float ringRadius, float ringY, float tubeR) {
        float[] px = this.scratchPx;
        float[] py = this.scratchPy;
        float[] pz = this.scratchPz;
        float[] cx = this.scratchCx;
        float[] cy = this.scratchCy;
        float[] cz = this.scratchCz;
        boolean hasPrev = false;
        for (int i = 0; i < 145; ++i) {
            int j;
            float t = (float)i / 144.0f;
            float ang = t * ((float)Math.PI * 2);
            float dirX = MathHelper.sin((double)ang);
            float dirZ = -MathHelper.cos((double)ang);
            float centerX = dirX * ringRadius;
            float centerZ = dirZ * ringRadius;
            int color = this.modeColor((int)(t * 720.0f), 1.0f);
            for (j = 0; j < 16; ++j) {
                float phi = (float)Math.PI * 2 * (float)j / (float)16;
                float cosP = MathHelper.cos((double)phi);
                float sinP = MathHelper.sin((double)phi);
                cx[j] = centerX + tubeR * cosP * dirX;
                cy[j] = ringY + tubeR * sinP;
                cz[j] = centerZ + tubeR * cosP * dirZ;
            }
            if (hasPrev) {
                for (j = 0; j < 16; ++j) {
                    int n = (j + 1) % 16;
                    body.vertex(pose, px[j], py[j], pz[j]).color(color);
                    body.vertex(pose, px[n], py[n], pz[n]).color(color);
                    body.vertex(pose, cx[n], cy[n], cz[n]).color(color);
                    body.vertex(pose, cx[j], cy[j], cz[j]).color(color);
                }
            }
            System.arraycopy(cx, 0, px, 0, 16);
            System.arraycopy(cy, 0, py, 0, 16);
            System.arraycopy(cz, 0, pz, 0, 16);
            hasPrev = true;
        }
    }

    private final float getYOffset(AbstractClientPlayerEntity player) {
        boolean flying = player.getAbilities().flying;
        boolean swimming = player.isSwimming();
        boolean helmet = this.hasVisibleHelmet(player);
        if (player.isInSneakingPose() && !flying && !swimming) {
            return helmet ? 0.39f : 0.28f;
        }
        return helmet ? 0.49f : 0.38f;
    }

    private final boolean hasVisibleHelmet(AbstractClientPlayerEntity player) {
        return !player.getEquippedStack(EquipmentSlot.HEAD).isEmpty();
    }

    @Protect(value=Level.MAX)
    private final int modeColor(int index, float rawAlpha) {
        float alpha = rawAlpha * this.visualAlpha();
        if (this.colorMode.is(COLOR_RAINBOW)) {
            return ChinaHat.Companion.rainbow(8, index, 1.0f, 1.0f, alpha);
        }
        int firstColor = 0;
        int secondColor = 0;
        if (this.colorMode.is(COLOR_CLIENT)) {
            int[] palette = ClientPalette.colors();
            if (palette != null && palette.length >= 2) {
                return ColorEngine.multAlpha(ChinaHat.Companion.paletteFade(8, index, palette), alpha);
            }
            InterfaceModule iface = InterfaceModule.Companion.getInstance();
            if (iface != null) {
                firstColor = iface.clientPrimaryColorOpaque();
                secondColor = iface.usesSecondClientColor() ? iface.clientSecondaryColorOpaque() : firstColor;
            } else {
                firstColor = -1;
                secondColor = ColorEngine.lerpColor(firstColor, DARK_SECOND_COLOR, 0.7f);
            }
        } else {
            firstColor = this.customColor.getColor();
            int n = secondColor = this.useSecondColor.getValue() ? this.customSecondColor.getColor() : this.customColor.getColor();
        }
        if (firstColor == secondColor) {
            return ColorEngine.multAlpha(firstColor, alpha);
        }
        return ColorEngine.multAlpha(ChinaHat.Companion.fade(8, index, firstColor, secondColor), alpha);
    }

    private static final Boolean _init_$lambda$0(ChinaHat this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean _init_$lambda$1(ChinaHat this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM);
    }

    private static final Boolean _init_$lambda$2(ChinaHat this$0) {
        return this$0.colorMode.is(COLOR_CUSTOM) && this$0.useSecondColor.getValue();
    }

    @JvmStatic
    @Nullable
    public static final ChinaHat getInstance() {
        return Companion.getInstance();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u0015\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J7\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J/\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J'\u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001aR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\"\u0010\u001dR\u0014\u0010#\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b#\u0010\u001dR\u0014\u0010%\u001a\u00020$8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020$8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010&R\u0014\u0010(\u001a\u00020$8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010&R\u0014\u0010)\u001a\u00020$8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010&R\u0014\u0010*\u001a\u00020$8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010&R\u0014\u0010+\u001a\u00020$8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010&R\u001d\u0010-\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u0092\u0002\u0002\b,\u00a2\u0006\u0006\n\u0004\b-\u0010.\u00a8\u0006/"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ChinaHat.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Visuals/ChinaHat;", "Lkotlin/jvm/JvmStatic;", "getInstance", "()Lrtx/kimiko/api/modules/impl/Visuals/ChinaHat;", "", "speed", "index", "", "saturation", "brightness", "alpha", "rainbow", "(IIFFF)I", "first", "second", "fade", "(IIII)I", "", "palette", "paletteFade", "(II[I)I", "SEGMENTS", "I", "OUTLINE_CROSS_SIDES", "TAU", "F", "DARK_SECOND_COLOR", "", "TRANSFORM_TTL_MS", "J", "HAT_SCALE", "OUTLINE_WIDTH", "", "MODE_SELF", "Ljava/lang/String;", "MODE_FRIENDS", "MODE_ALL", "COLOR_RAINBOW", "COLOR_CLIENT", "COLOR_CUSTOM", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/modules/impl/Visuals/ChinaHat;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @Nullable
        public final ChinaHat getInstance() {
            ChinaHat module = ModuleManager.Companion.get().get(ChinaHat.class);
            ChinaHat chinaHat = module;
            if (chinaHat == null) {
                chinaHat = INSTANCE;
            }
            return chinaHat;
        }

        private final int rainbow(int speed, int index, float saturation, float brightness, float alpha) {
            int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
            int rgb = ColorEngine.rainbow(angle, saturation, brightness);
            return ColorEngine.rgba(rgb >>> 16 & 0xFF, rgb >>> 8 & 0xFF, rgb & 0xFF, Math.round(MathHelper.clamp((float)alpha, (float)0.0f, (float)1.0f) * 255.0f));
        }

        private final int fade(int speed, int index, int first, int second) {
            int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
            angle = angle >= 180 ? 360 - angle : angle;
            return ColorEngine.lerpColor(first, second, (float)angle / 180.0f);
        }

        private final int paletteFade(int speed, int index, int[] palette) {
            int n = palette.length;
            int angle = (int)((System.currentTimeMillis() / (long)Math.max(1, speed) + (long)index) % 360L);
            float f = (float)angle / 360.0f * (float)n;
            int i = (int)f % n;
            int j = (i + 1) % n;
            int a = palette[i] | 0xFF000000;
            int b = palette[j] | 0xFF000000;
            return ColorEngine.lerpColor(a, b, f - (float)Math.floor(f)) | 0xFF000000;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/ChinaHat$HatTransform;", "", "Lorg/joml/Matrix4f;", "matrix", "", "time", "<init>", "(Lorg/joml/Matrix4f;J)V", "Lorg/joml/Matrix4f;", "getMatrix", "()Lorg/joml/Matrix4f;", "J", "getTime", "()J", "rtx.kimiko:kimiko"})
    private static final class HatTransform {
        @NotNull
        private final Matrix4f matrix;
        private final long time;

        public HatTransform(@NotNull Matrix4f matrix, long time) {
            Intrinsics.checkNotNullParameter((Object)matrix, (String)"matrix");
            this.matrix = matrix;
            this.time = time;
        }

        @NotNull
        public final Matrix4f getMatrix() {
            return this.matrix;
        }

        public final long getTime() {
            return this.time;
        }
    }
}

