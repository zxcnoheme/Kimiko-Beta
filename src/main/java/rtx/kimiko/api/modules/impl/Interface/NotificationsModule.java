/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.math.MathKt
 *  kotlin.text.StringsKt
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.screen.ChatScreen
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Interface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.config.ConfigManager;
import rtx.kimiko.api.drags.Position;
import rtx.kimiko.api.drags.hud.InfoHud;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.module.ModuleToggleEvent;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.api.modules.ModuleManager;
import rtx.kimiko.api.modules.impl.Interface.InfoModule;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.others.RectUtil;
import rtx.kimiko.utils.render.render2d.Render2D;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"notificationsmodule"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000 <2\u00020\u0001:\u0004=>?<B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u0004H\u0015b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\t\u0010\u0003J\u001b\u0010\r\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\nH\u0003b\u0002\b\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u000fH\u0003b\u0002\b\f\u00a2\u0006\u0004\b\u0010\u0010\u0011JO\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u0016H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\u001b\u00a2\u0006\u0004\b\u001c\u0010\u001dJ5\u0010&\u001a\u00020\u00042\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020!2\u0006\u0010%\u001a\u00020$H\u0002\u00a2\u0006\u0004\b&\u0010'J'\u0010)\u001a\u00020\u00042\u0006\u0010#\u001a\u00020!H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078(\u00a2\u0006\u0004\b)\u0010*R\u001a\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00140+8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u001a\u00100\u001a\b\u0012\u0004\u0012\u00020/0.8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0018\u0010%\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u00102R\u0016\u00103\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u00104R\u001c\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u001e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u00106R\u0014\u00108\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010:\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b:\u00109R\u0014\u0010;\u001a\u0002078\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b;\u00109\u00ca\u0001\u0010\b@\u0012\f\b\u0006\u0012\b\b\fJ\u0004\b\b(A\u00a8\u0006B"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "onDisable", "Lrtx/kimiko/api/events/impl/module/ModuleToggleEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onModuleToggle", "(Lrtx/kimiko/api/events/impl/module/ModuleToggleEvent;)V", "Lrtx/kimiko/api/events/impl/render/HudRenderEvent;", "onHud", "(Lrtx/kimiko/api/events/impl/render/HudRenderEvent;)V", "Lnet/minecraft/DrawContext;", "graphics", "Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Notif;", "notif", "", "rightX", "slotBottom", "rowH", "env", "STD", "render", "(Lnet/minecraft/DrawContext;Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Notif;FFFF)V", "", "Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Seg;", "segs", "", "hold", "now", "", "example", "spawn", "([Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Seg;JJZ)V", "MAX", "manageExample", "(J)V", "", "notifs", "Ljava/util/List;", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Pending;", "pending", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Notif;", "chatOffset", "F", "frameNotif", "[Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Notif;", "", "frameSlotBottom", "[F", "frameEnv", "glowSpans", "Companion", "Seg", "Pending", "Notif", "Lrtx/kimiko/api/liteapi/Feature;", "notificationsmodule", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nNotificationsModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NotificationsModule.kt\nrtx/kimiko/api/modules/impl/Interface/NotificationsModule\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,331:1\n1#2:332\n*E\n"})
public class NotificationsModule
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final List<Notif> notifs = new ArrayList();
    @NotNull
    private final ConcurrentLinkedQueue<Pending> pending = new ConcurrentLinkedQueue();
    @Nullable
    private Notif example;
    private float chatOffset;
    @NotNull
    private final Notif[] frameNotif = new Notif[32];
    @NotNull
    private final float[] frameSlotBottom = new float[32];
    @NotNull
    private final float[] frameEnv = new float[32];
    @NotNull
    private final float[] glowSpans = new float[128];
    private static final float TEXT_SIZE = 7.0f;
    private static final float PAD_X = 7.0f;
    private static final float PAD_Y = 4.0f;
    private static final float EDGE = 6.0f;
    private static final float GAP = 4.0f;
    private static final float MAX_RADIUS = 6.0f;
    private static final int MAX_VISIBLE = 6;
    private static final long IN_MS = 250L;
    private static final long OUT_MS = 250L;
    private static final long DEFAULT_HOLD_MS = 2500L;
    private static final long TOGGLE_HOLD_MS = 1500L;
    @NotNull
    private static final String METRIC_REF = "АНmT";
    private static final int WHITE = -1;
    private static final int GREEN = -11141291;
    private static final int RED = -43691;
    private static final float OUTLINE = 0.5f;
    private static final float OUTLINE_DARK = 0.25f;
    @Nullable
    private static NotificationsModule companionInstance;
    private static float cachedInkMid;
    private static final int FRAME_CAP = 32;

    public NotificationsModule() {
        super("Notifications", "Тосты-уведомления в правом нижнем углу.", Category.DISPLAY);
        companionInstance = this;
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onDisable() {
        this.notifs.clear();
        this.pending.clear();
        this.example = null;
    }

    @EventHandler
    private final void onModuleToggle(ModuleToggleEvent event) {
        if (ConfigManager.Companion.isLoading()) {
            return;
        }
        Module module = event.getModule();
        if (Intrinsics.areEqual((Object)module, (Object)this)) {
            return;
        }
        boolean on = event.isEnabled();
        Seg[] segArray = new Seg[]{new Seg(module.getName(), -1), new Seg(on ? " enabled" : " disabled", on ? -11141291 : -43691)};
        Seg[] segs = segArray;
        this.pending.add(new Pending(segs, 1500L));
    }

    @EventHandler
    private final void onHud(HudRenderEvent event) {
        boolean infoReserved;
        long now = System.currentTimeMillis();
        Pending entry;
        while ((entry = this.pending.poll()) != null) {
            this.spawn(entry.getSegs(), entry.getHold(), now, false);
        }
        this.manageExample(now);
        if (this.notifs.isEmpty()) {
            return;
        }
        float guiW = Math.max(1.0f, Position.Companion.screenWidth());
        float guiH = Math.max(1.0f, Position.Companion.screenHeight());
        boolean chatOpen = this.mc.currentScreen instanceof ChatScreen;
        float rowH = 15.0f;
        float step = rowH + 4.0f;
        float rightX = guiW - 6.0f;
        float bottomReserve = 6.0f;
        InfoModule info = ModuleManager.Companion.get().get(InfoModule.class);
        boolean bl = infoReserved = info != null && info.isEnabled();
        if (infoReserved) {
            bottomReserve = Math.max(bottomReserve, InfoHud.Companion.reservedRightHeight());
        }
        float chatTarget = chatOpen && !infoReserved ? 14.0f : 0.0f;
        this.chatOffset += (chatTarget - this.chatOffset) * 0.25f;
        float cursorBottom = guiH - bottomReserve - this.chatOffset;
        DrawContext graphics = event.getGraphics();
        int frameCount = 0;
        int spanCount = 0;
        float minL = Float.MAX_VALUE;
        float minT = Float.MAX_VALUE;
        float maxR = -3.4028235E38f;
        float maxB = -3.4028235E38f;
        float maxEnv = 0.0f;
        Iterator<Notif> iterator = this.notifs.iterator();
        while (iterator.hasNext()) {
            float outT;
            Notif notif = iterator.next();
            long age = now - notif.getBorn();
            if (!notif.getRemoving() && !notif.getExample() && age >= notif.getHold()) {
                notif.setRemoving(true);
                notif.setRemoveStart(now);
            }
            float inT = NotificationsModule.Companion.clamp01((float)age / (float)250L);
            float f = outT = notif.getRemoving() ? NotificationsModule.Companion.clamp01((float)(now - notif.getRemoveStart()) / (float)250L) : 0.0f;
            if (outT >= 1.0f) {
                iterator.remove();
                continue;
            }
            float env = NotificationsModule.Companion.easeOutQuad(inT) * (1.0f - NotificationsModule.Companion.easeInQuad(outT));
            float slotBottom = cursorBottom;
            cursorBottom -= step * env;
            if (env <= 0.004f || frameCount >= 32) continue;
            this.frameNotif[frameCount] = notif;
            this.frameSlotBottom[frameCount] = slotBottom;
            this.frameEnv[frameCount] = env;
            ++frameCount;
            float boxW = NotificationsModule.Companion.segsWidth(notif.getSegs()) + 14.0f;
            float boxX = rightX - boxW;
            float squeeze = Math.max(env, 1.0E-4f);
            float top = slotBottom - rowH * squeeze;
            int s = spanCount * 4;
            this.glowSpans[s] = boxX;
            this.glowSpans[s + 1] = boxX + boxW;
            this.glowSpans[s + 2] = top;
            this.glowSpans[s + 3] = slotBottom;
            ++spanCount;
            minL = Math.min(minL, boxX);
            maxR = Math.max(maxR, boxX + boxW);
            minT = Math.min(minT, top);
            maxB = Math.max(maxB, slotBottom);
            maxEnv = Math.max(maxEnv, env);
        }
        Render2D.beginFrame(graphics);
        if (spanCount > 0) {
            int n = spanCount;
            for (int i = 0; i < n; ++i) {
                int s = i * 4;
                float[] fArray = this.glowSpans;
                fArray[s] = fArray[s] - minL;
                fArray = this.glowSpans;
                int n2 = s + 1;
                fArray[n2] = fArray[n2] - minL;
                fArray = this.glowSpans;
                n2 = s + 2;
                fArray[n2] = fArray[n2] - minT;
                fArray = this.glowSpans;
                n2 = s + 3;
                fArray[n2] = fArray[n2] - minT;
            }
            RectUtil.drawClientGlowSpans(minL, minT, maxR - minL, maxB - minT, maxEnv, this.glowSpans, spanCount);
        }
        int n = frameCount;
        for (int i = 0; i < n; ++i) {
            Notif fn = this.frameNotif[i];
            if (fn != null) {
                this.render(graphics, fn, rightX, this.frameSlotBottom[i], rowH, this.frameEnv[i]);
            }
            this.frameNotif[i] = null;
        }
        Render2D.flush();
    }

    @Protect(value=Level.STD)
    private final void render(DrawContext graphics, Notif notif, float rightX, float slotBottom, float rowH, float env) {
        float alpha = NotificationsModule.Companion.clamp01(env);
        if (alpha <= 0.004f) {
            return;
        }
        float textWidth = NotificationsModule.Companion.segsWidth(notif.getSegs());
        float boxW = textWidth + 14.0f;
        float boxX = rightX - boxW;
        float pillTop = slotBottom - rowH;
        float radius = Math.min(6.0f, Math.min(boxW, rowH) * 0.5f);
        float squeeze = Math.max(env, 1.0E-4f);
        graphics.getMatrices().pushMatrix();
        graphics.getMatrices().translate(0.0f, slotBottom);
        graphics.getMatrices().scale(1.0f, squeeze);
        graphics.getMatrices().translate(0.0f, -slotBottom);
        RectUtil.drawClientRectNoGlow(boxX, pillTop, boxW, rowH, radius, alpha);
        float textY = pillTop + rowH * 0.5f - NotificationsModule.Companion.inkMid();
        float cursor = boxX + 7.0f;
        for (Seg seg : notif.getSegs()) {
            NotificationsModule.Companion.drawOutlined(seg.getText(), cursor, textY, ColorEngine.multAlpha(seg.getColor(), alpha));
            cursor += Fonts.BOLD.msdfWidth(seg.getText(), 7.0f);
        }
        graphics.getMatrices().popMatrix();
    }

    private final void spawn(Seg[] segs, long hold, long now, boolean example) {
        Notif notif2;
        int alive = 0;
        for (Notif n : this.notifs) {
            if (n.getRemoving()) continue;
            ++alive;
        }
        for (int i = this.notifs.size() - 1; i >= 0 && alive >= 6; --i) {
            notif2 = this.notifs.get(i);
            if (notif2.getRemoving()) continue;
            notif2.setRemoving(true);
            notif2.setRemoveStart(now);
            --alive;
        }
        notif2 = new Notif(segs, now, hold);
        notif2.setExample(example);
        this.notifs.add(0, notif2);
        if (example) {
            this.example = notif2;
        }
    }

    @Protect(value=Level.MAX)
    private final void manageExample(long now) {
        boolean exampleAlive;
        boolean chatOpen = this.mc.currentScreen instanceof ChatScreen;
        int realAlive = 0;
        for (Notif notif : this.notifs) {
            if (notif.getExample() || notif.getRemoving()) continue;
            ++realAlive;
        }
        Notif ex = this.example;
        boolean bl = exampleAlive = ex != null && this.notifs.contains(ex) && !ex.getRemoving();
        if (chatOpen && realAlive == 0) {
            if (!exampleAlive) {
                Seg[] segArray = new Seg[]{new Seg("Module", -1), new Seg(" enabled", -11141291)};
                this.spawn(segArray, 2500L, now, true);
            }
        } else if (exampleAlive && ex != null) {
            ex.setRemoving(true);
            ex.setRemoveStart(now);
        }
    }

    @JvmStatic
    public static final void notify(@Nullable String text, long holdMs) {
        Companion.notify(text, holdMs);
    }

    static {
        cachedInkMid = Float.NaN;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J%\u0010\n\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u0012\u001a\u00020\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J/\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001f\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u001f\u0010 J\u0017\u0010\"\u001a\u00020\f2\u0006\u0010!\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\"\u0010 J\u0017\u0010#\u001a\u00020\f2\u0006\u0010!\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b#\u0010 R\u0014\u0010$\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0014\u0010&\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010%R\u0014\u0010'\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010%R\u0014\u0010(\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010%R\u0014\u0010)\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010%R\u0014\u0010*\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010%R\u0014\u0010+\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010,R\u0014\u0010-\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u0014\u0010/\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b/\u0010.R\u0014\u00100\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b0\u0010.R\u0014\u00101\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b1\u0010.R\u0014\u00102\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0014\u00104\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u0010,R\u0014\u00105\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b5\u0010,R\u0014\u00106\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b6\u0010,R\u0014\u00107\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b7\u0010%R\u0014\u00108\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b8\u0010%R\u0018\u0010:\u001a\u0004\u0018\u0001098\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0016\u0010<\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b<\u0010%R\u0014\u0010=\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b=\u0010,\u00a8\u0006>"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule.Companion;", "", "<init>", "()V", "", "text", "", "holdMs", "", "Lkotlin/jvm/JvmStatic;", "notify", "(Ljava/lang/String;J)V", "", "inkMid", "()F", "", "Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Seg;", "segs", "segsWidth", "([Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Seg;)F", "x", "y", "", "color", "drawOutlined", "(Ljava/lang/String;FFI)V", "argb", "factor", "darken", "(IF)I", "v", "clamp01", "(F)F", "t", "easeOutQuad", "easeInQuad", "TEXT_SIZE", "F", "PAD_X", "PAD_Y", "EDGE", "GAP", "MAX_RADIUS", "MAX_VISIBLE", "I", "IN_MS", "J", "OUT_MS", "DEFAULT_HOLD_MS", "TOGGLE_HOLD_MS", "METRIC_REF", "Ljava/lang/String;", "WHITE", "GREEN", "RED", "OUTLINE", "OUTLINE_DARK", "Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule;", "companionInstance", "Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule;", "cachedInkMid", "FRAME_CAP", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        public final void notify(@Nullable String text, long holdMs) {
            if (ConfigManager.Companion.isLoading()) {
                return;
            }
            NotificationsModule module = companionInstance;
            if (module == null) {
                module = ModuleManager.Companion.get().get(NotificationsModule.class);
            }
            if (module != null && module.isEnabled() && text != null && !StringsKt.isBlank(text)) {
                Seg[] segArray = new Seg[]{new Seg(text, -1)};
                module.pending.add(new Pending(segArray, Math.max(500L, holdMs)));
            }
        }

        private final float inkMid() {
            if (Float.isNaN(cachedInkMid)) {
                float[] ref = Fonts.BOLD.msdfBounds(NotificationsModule.METRIC_REF, 7.0f);
                cachedInkMid = (ref[1] + ref[3]) * 0.5f;
            }
            return cachedInkMid;
        }

        private final float segsWidth(Seg[] segs) {
            float w = 0.0f;
            for (Seg seg : segs) {
                w += Fonts.BOLD.msdfWidth(seg.getText(), 7.0f);
            }
            return w;
        }

        private final void drawOutlined(String text, float x, float y, int color) {
            int dark = this.darken(color, 0.25f);
            Fonts.BOLD.msdf(text, x, y + 0.5f, 7.0f, dark);
            Fonts.BOLD.msdf(text, x, y - 0.5f, 7.0f, dark);
            Fonts.BOLD.msdf(text, x + 0.5f, y, 7.0f, dark);
            Fonts.BOLD.msdf(text, x - 0.5f, y, 7.0f, dark);
            Fonts.BOLD.msdf(text, x, y, 7.0f, color);
        }

        private final int darken(int argb, float factor) {
            int a = argb >>> 24 & 0xFF;
            int r = MathKt.roundToInt((float)((float)(argb >> 16 & 0xFF) * factor));
            int g = MathKt.roundToInt((float)((float)(argb >> 8 & 0xFF) * factor));
            int b = MathKt.roundToInt((float)((float)(argb & 0xFF) * factor));
            return a << 24 | r << 16 | g << 8 | b;
        }

        private final float clamp01(float v) {
            return v < 0.0f ? 0.0f : Math.min(v, 1.0f);
        }

        private final float easeOutQuad(float t) {
            float inv = 1.0f - this.clamp01(t);
            return 1.0f - inv * inv;
        }

        private final float easeInQuad(float t) {
            float c = this.clamp01(t);
            return c * c;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0002\u0018\u00002\u00020\u0001B%\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\u0004\b\b\u0010\tR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0006\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0007\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\r\u001a\u0004\b\u0010\u0010\u000fR\"\u0010\u0012\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\u0018\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\"\u0010\u001b\u001a\u00020\u00058\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001b\u0010\r\u001a\u0004\b\u001c\u0010\u000f\"\u0004\b\u001d\u0010\u001e\u00a8\u0006\u001f"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Notif;", "", "", "Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Seg;", "segs", "", "born", "hold", "<init>", "([Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Seg;JJ)V", "[Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Seg;", "getSegs", "()[Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Seg;", "J", "getBorn", "()J", "getHold", "", "removing", "Z", "getRemoving", "()Z", "setRemoving", "(Z)V", "example", "getExample", "setExample", "removeStart", "getRemoveStart", "setRemoveStart", "(J)V", "rtx.kimiko:kimiko"})
    private static final class Notif {
        @NotNull
        private final Seg[] segs;
        private final long born;
        private final long hold;
        private boolean removing;
        private boolean example;
        private long removeStart;

        public Notif(@NotNull Seg[] segs, long born, long hold) {
            Intrinsics.checkNotNullParameter((Object)segs, (String)"segs");
            this.segs = segs;
            this.born = born;
            this.hold = hold;
        }

        @NotNull
        public final Seg[] getSegs() {
            return this.segs;
        }

        public final long getBorn() {
            return this.born;
        }

        public final long getHold() {
            return this.hold;
        }

        public final boolean getRemoving() {
            return this.removing;
        }

        public final void setRemoving(boolean bl) {
            this.removing = bl;
        }

        public final boolean getExample() {
            return this.example;
        }

        public final void setExample(boolean bl) {
            this.example = bl;
        }

        public final long getRemoveStart() {
            return this.removeStart;
        }

        public final void setRemoveStart(long l) {
            this.removeStart = l;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ*\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00c6\u0001\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0011\u0010\u0014\u001a\u00020\u0013H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u0017\u001a\u00020\u0016H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0019\u001a\u0004\b\u001a\u0010\nR\u0017\u0010\u0006\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001b\u001a\u0004\b\u001c\u0010\f\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Pending;", "", "", "Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Seg;", "segs", "", "hold", "<init>", "([Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Seg;J)V", "component1", "()[Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Seg;", "component2", "()J", "copy", "([Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Seg;J)Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Pending;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "[Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Seg;", "getSegs", "J", "getHold", "rtx.kimiko:kimiko"})
    private static final class Pending {
        @NotNull
        private final Seg[] segs;
        private final long hold;

        public Pending(@NotNull Seg[] segs, long hold) {
            Intrinsics.checkNotNullParameter((Object)segs, (String)"segs");
            this.segs = segs;
            this.hold = hold;
        }

        @NotNull
        public final Seg[] getSegs() {
            return this.segs;
        }

        public final long getHold() {
            return this.hold;
        }

        @NotNull
        public final Seg[] component1() {
            return this.segs;
        }

        public final long component2() {
            return this.hold;
        }

        @NotNull
        public final Pending copy(@NotNull Seg[] segs, long hold) {
            Intrinsics.checkNotNullParameter((Object)segs, (String)"segs");
            return new Pending(segs, hold);
        }

        public static /* synthetic */ Pending copy$default(Pending pending, Seg[] segArray, long l, int n, Object object) {
            if ((n & 1) != 0) {
                segArray = pending.segs;
            }
            if ((n & 2) != 0) {
                l = pending.hold;
            }
            return pending.copy(segArray, l);
        }

        @NotNull
        public String toString() {
            return "Pending(segs=" + Arrays.toString(this.segs) + ", hold=" + this.hold + ")";
        }

        public int hashCode() {
            int result = Arrays.hashCode(this.segs);
            result = result * 31 + Long.hashCode(this.hold);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Pending)) {
                return false;
            }
            Pending pending = (Pending)other;
            if (!Intrinsics.areEqual((Object)this.segs, (Object)pending.segs)) {
                return false;
            }
            return this.hold == pending.hold;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\t\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ$\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0012\u001a\u00020\u0004H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0012\u0010\u000bJ\u0011\u0010\u0013\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\tR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0017\u0010\u000b\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Seg;", "", "", "text", "", "color", "<init>", "(Ljava/lang/String;I)V", "component1", "()Ljava/lang/String;", "component2", "()I", "copy", "(Ljava/lang/String;I)Lrtx/kimiko/api/modules/impl/Interface/NotificationsModule$Seg;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "Ljava/lang/String;", "getText", "I", "getColor", "rtx.kimiko:kimiko"})
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

        @NotNull
        public final String component1() {
            return this.text;
        }

        public final int component2() {
            return this.color;
        }

        @NotNull
        public final Seg copy(@NotNull String text, int color) {
            Intrinsics.checkNotNullParameter((Object)text, (String)"text");
            return new Seg(text, color);
        }

        public static /* synthetic */ Seg copy$default(Seg seg, String string, int n, int n2, Object object) {
            if ((n2 & 1) != 0) {
                string = seg.text;
            }
            if ((n2 & 2) != 0) {
                n = seg.color;
            }
            return seg.copy(string, n);
        }

        @NotNull
        public String toString() {
            return "Seg(text=" + this.text + ", color=" + this.color + ")";
        }

        public int hashCode() {
            int result = this.text.hashCode();
            result = result * 31 + Integer.hashCode(this.color);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Seg)) {
                return false;
            }
            Seg seg = (Seg)other;
            if (!Intrinsics.areEqual((Object)this.text, (Object)seg.text)) {
                return false;
            }
            return this.color == seg.color;
        }
    }
}

