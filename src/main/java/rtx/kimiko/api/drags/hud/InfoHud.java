/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.network.ClientPlayNetworkHandler
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.PlayerListEntry
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.drags.hud;

import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.events.EventBus;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.InfoModule;
import rtx.kimiko.api.modules.impl.Utils.StreamerMode;
import rtx.kimiko.utils.network.Network;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \n2\u00020\u0001:\u0002\u000b\nB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0003b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\f"}, d2={"Lrtx/kimiko/api/drags/hud/InfoHud;", "", "<init>", "()V", "Lrtx/kimiko/api/events/impl/render/HudRenderEvent;", "event", "", "Lrtx/kimiko/api/events/EventHandler;", "onHud", "(Lrtx/kimiko/api/events/impl/render/HudRenderEvent;)V", "Companion", "Seg", "rtx.kimiko:kimiko"})
public final class InfoHud {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final float SIZE = 7.0f;
    private static final float MARGIN = 5.0f;
    private static final float LINE_GAP = 3.0f;
    private static final int LABEL_COLOR = -3618608;
    private static final int LEFT_VALUE_COLOR = -11665561;
    private static final int RIGHT_VALUE_COLOR = -45747;
    private static final int OUTLINE_COLOR = -16777216;
    private static final float OUTLINE_OFFSET = 0.2f;
    @NotNull
    private static float[] offsetScratch = new float[8];
    @NotNull
    private static final float[][] OUTLINE_DIRS;

    public InfoHud() {
        EventBus.Companion.get().subscribe(this);
    }

    @EventHandler
    private final void onHud(HudRenderEvent event) {
        InfoModule module = ModuleManager.Companion.get().get(InfoModule.class);
        if (module == null || !module.isEnabled()) {
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        ClientPlayerEntity player = mc.player;
        if (player == null || mc.world == null) {
            return;
        }
        float screenW = Position.Companion.screenWidth();
        float screenH = Position.Companion.screenHeight();
        float lineH = 10.0f;
        float bottom = screenH - 5.0f - 7.0f;
        float top = bottom - lineH;
        DrawContext graphics = event.getGraphics();
        Render2D.beginFrame(graphics);
        Seg[] segArray = new Seg[]{new Seg("BPS ", -3618608), new Seg(InfoHud.Companion.bps((PlayerEntity)player), -11665561)};
        Seg[] bps = segArray;
        Seg[] segArray2 = new Seg[]{new Seg("XYZ ", -3618608), new Seg(InfoHud.Companion.coords(player.getX(), player.getY(), player.getZ()), -11665561), new Seg("  " + InfoHud.Companion.otherDim(mc, (PlayerEntity)player), -3618608)};
        Seg[] xyz = segArray2;
        InfoHud.Companion.drawSegs(bps, 5.0f, top);
        InfoHud.Companion.drawSegs(xyz, 5.0f, bottom);
        Seg[] segArray3 = new Seg[]{new Seg("Ping ", -3618608), new Seg(InfoHud.Companion.ping(mc, (PlayerEntity)player) + "ms", -45747)};
        Seg[] ping = segArray3;
        Seg[] segArray4 = new Seg[]{new Seg("FPS ", -3618608), new Seg(String.valueOf(mc.getCurrentFps()), -45747)};
        Seg[] fps = segArray4;
        Seg[] segArray5 = new Seg[2];
        segArray5[0] = new Seg("TPS ", -3618608);
        Locale locale = Locale.ROOT;
        String string = "%.1f";
        Object[] objectArray = new Object[]{Float.valueOf(Network.getTPS())};
        String string2 = String.format(locale, string, Arrays.copyOf(objectArray, objectArray.length));
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"format(...)");
        segArray5[1] = new Seg(string2, -45747);
        Seg[] tps = segArray5;
        float middle = bottom - lineH;
        InfoHud.Companion.drawSegs(fps, screenW - 5.0f - InfoHud.Companion.width(fps), middle - lineH);
        InfoHud.Companion.drawSegs(tps, screenW - 5.0f - InfoHud.Companion.width(tps), middle);
        InfoHud.Companion.drawSegs(ping, screenW - 5.0f - InfoHud.Companion.width(ping), bottom);
        Render2D.flush();
    }

    @JvmStatic
    public static final float reservedRightHeight() {
        return Companion.reservedRightHeight();
    }

    static {
        float[][] fArrayArray = new float[8][];
        float[] fArray = new float[]{-1.0f, -1.0f};
        fArrayArray[0] = fArray;
        fArray = new float[]{0.0f, -1.0f};
        fArrayArray[1] = fArray;
        fArray = new float[]{1.0f, -1.0f};
        fArrayArray[2] = fArray;
        fArray = new float[]{-1.0f, 0.0f};
        fArrayArray[3] = fArray;
        fArray = new float[]{1.0f, 0.0f};
        fArrayArray[4] = fArray;
        fArray = new float[]{-1.0f, 1.0f};
        fArrayArray[5] = fArray;
        fArray = new float[]{0.0f, 1.0f};
        fArrayArray[6] = fArray;
        fArray = new float[]{1.0f, 1.0f};
        fArrayArray[7] = fArray;
        OUTLINE_DIRS = fArrayArray;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0011\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ'\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u001d\u0010\u001d\u001a\u00020\u00042\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ-\u0010 \u001a\u00020\u001f2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b \u0010!J\u001d\u0010#\u001a\u00020\"2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0002\u00a2\u0006\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010&R\u0014\u0010(\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010&R\u0014\u0010)\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010*R\u0014\u0010,\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b,\u0010*R\u0014\u0010-\u001a\u00020\u00178\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010*R\u0014\u0010.\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b.\u0010&R\u0016\u0010/\u001a\u00020\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b/\u00100R\u001a\u00101\u001a\b\u0012\u0004\u0012\u00020\"0\u001a8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b1\u00102\u00a8\u00063"}, d2={"Lrtx/kimiko/api/drags/hud/InfoHud.Companion;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "reservedRightHeight", "()F", "Lnet/minecraft/PlayerEntity;", "player", "", "bps", "(Lnet/minecraft/PlayerEntity;)Ljava/lang/String;", "", "x", "y", "z", "coords", "(DDD)Ljava/lang/String;", "Lnet/minecraft/MinecraftClient;", "mc", "otherDim", "(Lnet/minecraft/MinecraftClient;Lnet/minecraft/PlayerEntity;)Ljava/lang/String;", "", "ping", "(Lnet/minecraft/MinecraftClient;Lnet/minecraft/PlayerEntity;)I", "", "Lrtx/kimiko/api/drags/hud/InfoHud$Seg;", "segs", "width", "([Lrtx/kimiko/api/drags/hud/InfoHud$Seg;)F", "", "drawSegs", "([Lrtx/kimiko/api/drags/hud/InfoHud$Seg;FF)V", "", "segOffsets", "([Lrtx/kimiko/api/drags/hud/InfoHud$Seg;)[F", "SIZE", "F", "MARGIN", "LINE_GAP", "LABEL_COLOR", "I", "LEFT_VALUE_COLOR", "RIGHT_VALUE_COLOR", "OUTLINE_COLOR", "OUTLINE_OFFSET", "offsetScratch", "[F", "OUTLINE_DIRS", "[[F", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final float reservedRightHeight() {
            return 36.0f;
        }

        private final String bps(PlayerEntity player) {
            double dx = player.getX() - player.lastRenderX;
            double dz = player.getZ() - player.lastRenderZ;
            double speed = Math.sqrt(dx * dx + dz * dz) * 20.0;
            Locale locale = Locale.ROOT;
            String string = "%.2f";
            Object[] objectArray = new Object[]{speed};
            String string2 = String.format(locale, string, Arrays.copyOf(objectArray, objectArray.length));
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"format(...)");
            return string2;
        }

        private final String coords(double x, double y, double z) {
            if (StreamerMode.Companion.hideCoords()) {
                return "#, #, #";
            }
            return (int)Math.floor(x) + ", " + (int)Math.floor(y) + ", " + (int)Math.floor(z);
        }

        private final String otherDim(MinecraftClient mc, PlayerEntity player) {
            if (StreamerMode.Companion.hideCoords()) {
                return "[#, #, #]";
            }
            ClientWorld clientWorld2 = mc.world;
            Intrinsics.checkNotNull((Object)clientWorld2);
            boolean nether = clientWorld2.getDimension().coordinateScale() > 1.0;
            double factor = nether ? 8.0 : 0.125;
            int ox = (int)Math.floor(player.getX() * factor);
            int oz = (int)Math.floor(player.getZ() * factor);
            return "[" + ox + ", " + (int)Math.floor(player.getY()) + ", " + oz + "]";
        }

        private final int ping(MinecraftClient mc, PlayerEntity player) {
            ClientPlayNetworkHandler clientPlayNetworkHandler2 = mc.getNetworkHandler();
            if (clientPlayNetworkHandler2 == null) {
                return 0;
            }
            ClientPlayNetworkHandler connection = clientPlayNetworkHandler2;
            PlayerListEntry info = connection.getPlayerListEntry(player.getUuid());
            return info == null ? 0 : Math.max(0, info.getLatency());
        }

        private final float width(Seg[] segs) {
            float w = 0.0f;
            for (Seg seg : segs) {
                w += Fonts.SEMIBOLD.msdfWidth(seg.getText(), 7.0f);
            }
            return w;
        }

        private final void drawSegs(Seg[] segs, float x, float y) {
            int n;
            float[] offsets = this.segOffsets(segs);
            float[][] fArray = OUTLINE_DIRS;
            int n2 = ((Object[])fArray).length;
            for (n = 0; n < n2; ++n) {
                float[] dir = fArray[n];
                float ox = x + dir[0] * 0.2f;
                float oy = y + dir[1] * 0.2f;
                int n3 = segs.length;
                for (int i = 0; i < n3; ++i) {
                    Fonts.SEMIBOLD.msdf(segs[i].getText(), ox + offsets[i], oy, 7.0f, -16777216);
                }
            }
            n = segs.length;
            for (int i = 0; i < n; ++i) {
                Fonts.SEMIBOLD.msdf(segs[i].getText(), x + offsets[i], y, 7.0f, segs[i].getColor());
            }
        }

        private final float[] segOffsets(Seg[] segs) {
            float[] scratch = offsetScratch;
            if (scratch.length < segs.length) {
                scratch = new float[segs.length];
                offsetScratch = scratch;
            }
            float cursor = 0.0f;
            int n = segs.length;
            for (int i = 0; i < n; ++i) {
                scratch[i] = cursor;
                cursor += Fonts.SEMIBOLD.msdfWidth(segs[i].getText(), 7.0f);
            }
            return scratch;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/api/drags/hud/InfoHud$Seg;", "", "", "text", "", "color", "<init>", "(Ljava/lang/String;I)V", "Ljava/lang/String;", "getText", "()Ljava/lang/String;", "I", "getColor", "()I", "rtx.kimiko:kimiko"})
    private static final class Seg {
        @NotNull
        private final String text;
        private final int color;

        public Seg(@NotNull String text, int color) {
            Intrinsics.checkNotNullParameter((Object)text, (String)"text");
            this.text = text;
            this.color = color;
        }

        @NotNull
        public final String getText() {
            return this.text;
        }

        public final int getColor() {
            return this.color;
        }
    }
}

