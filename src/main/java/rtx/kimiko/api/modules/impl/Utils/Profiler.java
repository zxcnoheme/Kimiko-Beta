/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.entity.Entity
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.render.WorldRenderer
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils;

import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.entity.Entity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.render.WorldRenderer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.impl.render.HudRenderEvent;
import rtx.kimiko.api.liteapi.Feature;
import rtx.kimiko.api.modules.Category;
import rtx.kimiko.api.modules.Module;
import rtx.kimiko.utils.render.others.profiler.RenderProfiler;
import rtx.kimiko.utils.storage.RepositoryStorage;
import sigil.protect.Level;
import sigil.protect.Protect;

@Feature(value={"profiler"})
@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\b\n\n\u0002\u0010\u0013\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 i2\u00020\u0001:\u0003jkiB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\u0004H\u0015b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\t\u0010\u0003J\u001f\u0010\n\u001a\u00020\u0004H\u0015b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b\n\u0010\u0003J\u001b\u0010\u000e\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0003b\u0002\b\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0015\u001a\u00020\u0004H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\u0014\u00a2\u0006\u0004\b\u0015\u0010\u0003J\u0017\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J/\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u0019J\u0017\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u001f\u0010\u0019J\u001f\u0010 \u001a\u00020\u0004H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\b\u00a2\u0006\u0004\b \u0010\u0003J-\u0010%\u001a\u00020\u00042\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078$\u00a2\u0006\u0004\b%\u0010&J\u001d\u0010'\u001a\u00020\u00042\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!H\u0002\u00a2\u0006\u0004\b'\u0010&J\u001d\u0010(\u001a\u00020\u00042\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!H\u0002\u00a2\u0006\u0004\b(\u0010&J\u001d\u0010)\u001a\u00020\u00042\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!H\u0002\u00a2\u0006\u0004\b)\u0010&J%\u0010+\u001a\u00020\u00042\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!2\u0006\u0010*\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b+\u0010,J\u001d\u00100\u001a\b\u0012\u0004\u0012\u00020\"0/2\u0006\u0010.\u001a\u00020-H\u0002\u00a2\u0006\u0004\b0\u00101J\u000f\u00103\u001a\u000202H\u0002\u00a2\u0006\u0004\b3\u00104J\u000f\u00105\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b5\u00106J\u001f\u00107\u001a\u00020-H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\u0014\u00a2\u0006\u0004\b7\u00108J\u001f\u00109\u001a\u00020-H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\u0014\u00a2\u0006\u0004\b9\u00108J!\u0010:\u001a\u0004\u0018\u00010\"H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\u0014\u00a2\u0006\u0004\b:\u0010;J\u001f\u0010<\u001a\u00020\u0004H\u0003b\u000e\b\u0005\u0012\n\b\u0006\u0012\u0006\b\n0\u00078\u0014\u00a2\u0006\u0004\b<\u0010\u0003R\u0014\u0010>\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020=8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b@\u0010?R\u001a\u0010B\u001a\b\u0012\u0004\u0012\u00020A0/8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0014\u0010E\u001a\u00020D8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bE\u0010FR\u001a\u0010I\u001a\b\u0012\u0004\u0012\u00020H0G8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bI\u0010JR\u0016\u0010K\u001a\u00020-8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010LR\u0016\u0010M\u001a\u00020-8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bM\u0010LR\u0016\u0010N\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bN\u0010OR\u0016\u0010P\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bP\u0010OR\u0016\u0010Q\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bQ\u0010RR\u0016\u0010S\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bS\u0010OR\u0016\u0010T\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bT\u0010OR\u0016\u0010U\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bU\u0010OR\u0016\u0010V\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010OR\u0016\u0010W\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bW\u0010OR\u0016\u0010X\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bX\u0010OR\u0016\u0010Y\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bY\u0010OR\u0016\u0010Z\u001a\u00020-8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bZ\u0010LR\u0016\u0010[\u001a\u00020-8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b[\u0010LR\u0016\u0010\\\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\\\u0010OR\u0016\u0010]\u001a\u00020-8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b]\u0010LR\u0016\u0010^\u001a\u00020-8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b^\u0010LR\u0016\u0010_\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b_\u0010OR\u0018\u0010`\u001a\u0004\u0018\u00010\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b`\u0010aR\u0018\u0010c\u001a\u0004\u0018\u00010b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bc\u0010dR\u0018\u0010e\u001a\u0004\u0018\u00010b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\be\u0010dR\u0016\u0010g\u001a\u00020f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bg\u0010h\u00ca\u0001\u0010\bl\u0012\f\b\u0006\u0012\b\b\fJ\u0004\b\b(m\u00a8\u0006n"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/Profiler;", "Lrtx/kimiko/api/modules/Module;", "<init>", "()V", "", "Lsigil/protect/Protect;", "value", "Lsigil/protect/Level;", "CROWN", "onEnable", "onDisable", "Lrtx/kimiko/api/events/impl/render/HudRenderEvent;", "event", "Lrtx/kimiko/api/events/EventHandler;", "onHud", "(Lrtx/kimiko/api/events/impl/render/HudRenderEvent;)V", "", "frameMs", "push", "(D)V", "MAX", "recomputeBaseline", "", "nowMs", "updateAllocation", "(J)V", "gcCountDelta", "gcPauseDelta", "detectSpike", "(DJJJ)V", "maybeSampleWorld", "sampleSections", "saveReport", "", "", "out", "STD", "appendFrameStats", "(Ljava/util/List;)V", "appendRuntimeStats", "appendWorldStats", "appendThreadStats", "finishedAtMs", "appendSpikes", "(Ljava/util/List;J)V", "", "limit", "", "topThreads", "(I)Ljava/util/List;", "", "sampleGc", "()[J", "gcPauseTotalMs", "()J", "countEntities", "()I", "countParticles", "sectionStats", "()Ljava/lang/String;", "resetStats", "", "frames", "[D", "scratch", "Ljava/lang/management/GarbageCollectorMXBean;", "gcBeans", "Ljava/util/List;", "Ljava/lang/management/ThreadMXBean;", "threadBean", "Ljava/lang/management/ThreadMXBean;", "Ljava/util/Deque;", "Lrtx/kimiko/api/modules/impl/Utils/Profiler$Spike;", "spikes", "Ljava/util/Deque;", "count", "I", "head", "startedAtMs", "J", "lastFrameNanos", "baselineMs", "D", "lastGcCount", "lastGcPauseMs", "lastHeapUsed", "allocAccum", "allocRatePerSec", "allocWindowStart", "lastWorldSample", "baseEntities", "baseParticles", "lastSectionSample", "pcRecent", "puRecent", "pcRecentTime", "lastSectionStats", "Ljava/lang/String;", "Ljava/lang/reflect/Field;", "particleEngineField", "Ljava/lang/reflect/Field;", "particlesMapField", "", "reflectionResolved", "Z", "Companion", "Spike", "ThreadCpu", "Lrtx/kimiko/api/liteapi/Feature;", "profiler", "rtx.kimiko:kimiko"})
public final class Profiler
extends Module {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final double[] frames = new double[512];
    @NotNull
    private final double[] scratch = new double[512];
    @NotNull
    private final List<GarbageCollectorMXBean> gcBeans;
    @NotNull
    private final ThreadMXBean threadBean;
    @NotNull
    private final Deque<Spike> spikes;
    private int count;
    private int head;
    private long startedAtMs;
    private long lastFrameNanos;
    private double baselineMs;
    private long lastGcCount;
    private long lastGcPauseMs;
    private long lastHeapUsed;
    private long allocAccum;
    private long allocRatePerSec;
    private long allocWindowStart;
    private long lastWorldSample;
    private int baseEntities;
    private int baseParticles;
    private long lastSectionSample;
    private int pcRecent;
    private int puRecent;
    private long pcRecentTime;
    @Nullable
    private String lastSectionStats;
    @Nullable
    private Field particleEngineField;
    @Nullable
    private Field particlesMapField;
    private boolean reflectionResolved;
    private static final int CAPACITY = 512;
    private static final int SPIKE_CAPACITY = 512;
    private static final double SPIKE_FLOOR_MS = 5.0;
    private static final double SPIKE_MULTIPLIER = 2.0;
    @NotNull
    private static final DateTimeFormatter REPORT_TIME;

    public Profiler() {
        super("Profiler", "Собирает причины просадок FPS и сохраняет отчёт при выключении.", Category.UTILS);
        List<GarbageCollectorMXBean> list = ManagementFactory.getGarbageCollectorMXBeans();
        Intrinsics.checkNotNullExpressionValue(list, (String)"getGarbageCollectorMXBeans(...)");
        this.gcBeans = list;
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        Intrinsics.checkNotNullExpressionValue((Object)threadMXBean, (String)"getThreadMXBean(...)");
        this.threadBean = threadMXBean;
        this.spikes = new ArrayDeque();
        this.baselineMs = 4.0;
        this.lastGcCount = -1L;
        this.lastGcPauseMs = -1L;
        this.lastHeapUsed = -1L;
        this.baseParticles = -1;
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onEnable() {
        this.resetStats();
        this.startedAtMs = System.currentTimeMillis();
        RenderProfiler.start();
    }

    @Override
    @Protect(value=Level.CROWN)
    protected void onDisable() {
        RenderProfiler.stop();
        this.saveReport();
    }

    @EventHandler
    private final void onHud(HudRenderEvent event) {
        long now = System.nanoTime();
        if (this.lastFrameNanos == 0L) {
            this.lastFrameNanos = now;
            return;
        }
        double frameMs = (double)(now - this.lastFrameNanos) / 1000000.0;
        this.lastFrameNanos = now;
        if (frameMs <= 0.0 || frameMs > 1000.0) {
            return;
        }
        this.push(frameMs);
        this.recomputeBaseline();
        long nowMs = System.currentTimeMillis();
        this.updateAllocation(nowMs);
        long[] gc = this.sampleGc();
        long gcCountDelta = this.lastGcCount < 0L ? 0L : gc[0] - this.lastGcCount;
        long gcPause = this.gcPauseTotalMs();
        long gcPauseDelta = this.lastGcPauseMs < 0L ? 0L : gcPause - this.lastGcPauseMs;
        this.lastGcCount = gc[0];
        this.lastGcPauseMs = gcPause;
        this.maybeSampleWorld(nowMs);
        this.detectSpike(frameMs, gcCountDelta, gcPauseDelta, nowMs);
    }

    private final void push(double frameMs) {
        this.frames[this.head] = frameMs;
        this.head = (this.head + 1) % 512;
        if (this.count < 512) {
            int n = this.count;
            this.count = n + 1;
        }
    }

    @Protect(value=Level.MAX)
    private final void recomputeBaseline() {
        if (this.count == 0) {
            return;
        }
        int n = this.count;
        for (int i = 0; i < n; ++i) {
            this.scratch[i] = this.frames[(this.head - this.count + i + 512) % 512];
        }
        Arrays.sort(this.scratch, 0, this.count);
        this.baselineMs = this.scratch[this.count / 2];
    }

    private final void updateAllocation(long nowMs) {
        long used = Profiler.Companion.usedHeap();
        if (this.lastHeapUsed >= 0L && used > this.lastHeapUsed) {
            this.allocAccum += used - this.lastHeapUsed;
        }
        this.lastHeapUsed = used;
        if (this.allocWindowStart == 0L) {
            this.allocWindowStart = nowMs;
        }
        if (nowMs - this.allocWindowStart >= 1000L) {
            this.allocRatePerSec = this.allocAccum;
            this.allocAccum = 0L;
            this.allocWindowStart = nowMs;
        }
    }

    private final void detectSpike(double frameMs, long gcCountDelta, long gcPauseDelta, long nowMs) {
        if (this.count < 20 || frameMs < 5.0 || frameMs < this.baselineMs * 2.0) {
            return;
        }
        int entities = this.countEntities();
        int particles = this.countParticles();
        int entityDelta = entities - this.baseEntities;
        int particleDelta = particles < 0 || this.baseParticles < 0 ? 0 : particles - this.baseParticles;
        boolean meshActive = nowMs - this.pcRecentTime <= 2000L && (this.pcRecent >= 4 || this.puRecent >= 2);
        long effectiveGcPauseDelta = Math.max(0L, gcPauseDelta);
        String cause = null;
        Object detail = null;
        if (gcCountDelta > 0L && effectiveGcPauseDelta >= 5L && (double)effectiveGcPauseDelta >= 0.35 * frameMs) {
            cause = "Сборка мусора (GC)";
            Locale locale = Locale.ROOT;
            String string = "STW-пауза ~%d мс, аллокации %s/с";
            Object[] objectArray = new Object[]{effectiveGcPauseDelta, Profiler.Companion.mib(this.allocRatePerSec)};
            String string2 = String.format(locale, string, Arrays.copyOf(objectArray, objectArray.length));
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"format(...)");
            detail = string2;
        } else if (meshActive) {
            cause = "Перестройка чанков";
            detail = "очередь pC:" + this.pcRecent + " pU:" + this.puRecent + ", рендер строит геометрию";
        } else if (particleDelta > 800 || particles > 5000) {
            cause = "Частицы";
            detail = "частиц: " + particles + " (+" + particleDelta + ")";
        } else if (entityDelta > 30) {
            cause = "Подгрузка сущностей";
            detail = "сущностей: " + entities + " (+" + entityDelta + ")";
        } else if (this.allocRatePerSec > 1572864000L) {
            cause = "Высокие аллокации";
            detail = "аллокации " + Profiler.Companion.mib(this.allocRatePerSec) + "/с, возможное давление на GC";
        } else {
            cause = "Внешний стоп";
            detail = "GC, чанки и мир спокойны: вероятны ОС, драйвер, фоновые процессы или троттлинг";
        }
        this.spikes.addFirst(new Spike(nowMs, frameMs, this.baselineMs, cause, (String)detail, effectiveGcPauseDelta, this.allocRatePerSec, this.pcRecent, this.puRecent, entities, particles));
        while (this.spikes.size() > 512) {
            this.spikes.removeLast();
        }
    }

    private final void maybeSampleWorld(long nowMs) {
        if (nowMs - this.lastSectionSample >= 200L) {
            this.lastSectionSample = nowMs;
            this.sampleSections(nowMs);
        }
        if (nowMs - this.lastWorldSample < 500L) {
            return;
        }
        this.lastWorldSample = nowMs;
        this.baseEntities = this.countEntities();
        this.baseParticles = this.countParticles();
    }

    private final void sampleSections(long nowMs) {
        String stats;
        String string = this.sectionStats();
        if (string == null) {
            return;
        }
        this.lastSectionStats = stats = string;
        int pc = Profiler.Companion.parseStat(stats, "pC:");
        int pu = Profiler.Companion.parseStat(stats, "pU:");
        if (nowMs - this.pcRecentTime > 2000L) {
            this.pcRecent = 0;
            this.puRecent = 0;
        }
        if (pc >= 0 && (pc >= this.pcRecent || nowMs - this.pcRecentTime > 2000L)) {
            this.pcRecent = pc;
            this.pcRecentTime = nowMs;
        }
        if (pu > this.puRecent) {
            this.puRecent = pu;
            this.pcRecentTime = nowMs;
        }
    }

    @Protect(value=Level.CROWN)
    private final void saveReport() {
        long finishedAtMs = System.currentTimeMillis();
        List<String> out = new ArrayList<>();
        out.add("Отчёт Kimiko Profiler");
        out.add("Начало: " + Instant.ofEpochMilli(this.startedAtMs));
        out.add("Окончание: " + Instant.ofEpochMilli(finishedAtMs));
        out.add("Длительность: " + Math.max(0L, finishedAtMs - this.startedAtMs) / 1000L + " с");
        out.add("");
        this.appendFrameStats(out);
        RenderProfiler.appendReport(out);
        this.appendRuntimeStats(out);
        this.appendWorldStats(out);
        this.appendThreadStats(out);
        this.appendSpikes(out, finishedAtMs);
        Path directory = RepositoryStorage.configRoot().resolve("profiler");
        Path file = directory.resolve("profiler-" + REPORT_TIME.format(Instant.ofEpochMilli(finishedAtMs)) + ".txt");
        try {
            Files.createDirectories(directory, new FileAttribute[0]);
            Files.writeString(file, String.join(System.lineSeparator(), out) + System.lineSeparator(), StandardCharsets.UTF_8);
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    @Protect(value=Level.STD)
    private final void appendFrameStats(List<String> out) {
        out.add("Кадры");
        if (this.count == 0) {
            out.add("Корректных кадров не записано.");
            return;
        }
        double sum = 0.0;
        double minVal = Double.MAX_VALUE;
        double maxVal = 0.0;
        int n = this.count;
        for (int i = 0; i < n; ++i) {
            double frameMs = this.frames[(this.head - this.count + i + 512) % 512];
            sum += frameMs;
            minVal = Math.min(minVal, frameMs);
            maxVal = Math.max(maxVal, frameMs);
        }
        this.recomputeBaseline();
        double average = sum / (double)this.count;
        double p99 = this.scratch[(int)Math.floor(0.99 * (double)(this.count - 1))];
        double p999 = this.scratch[(int)Math.floor(0.999 * (double)(this.count - 1))];
        Locale locale = Locale.ROOT;
        String string = "Выборка: %d; среднее %.1f мс (%.0f FPS); мин. %.1f мс; макс. %.1f мс";
        Object[] objectArray = new Object[]{this.count, average, 1000.0 / average, minVal, maxVal};
        String string2 = String.format(locale, string, Arrays.copyOf(objectArray, objectArray.length));
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"format(...)");
        out.add(string2);
        locale = Locale.ROOT;
        string = "1%% low: %.0f FPS (%.1f мс); 0.1%% low: %.0f FPS (%.1f мс); медиана %.1f мс";
        objectArray = new Object[]{1000.0 / p99, p99, 1000.0 / p999, p999, this.baselineMs};
        String string3 = String.format(locale, string, Arrays.copyOf(objectArray, objectArray.length));
        Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"format(...)");
        out.add(string3);
    }

    private final void appendRuntimeStats(List<String> out) {
        out.add("");
        out.add("Память и GC");
        out.add("Куча: " + Profiler.Companion.mib(Profiler.Companion.usedHeap()) + " / " + Profiler.Companion.mib(Runtime.getRuntime().maxMemory()) + "; аллокации: " + Profiler.Companion.mib(this.allocRatePerSec) + "/с");
        for (GarbageCollectorMXBean bean : this.gcBeans) {
            out.add(bean.getName() + ": " + bean.getCollectionCount() + " сборок, " + bean.getCollectionTime() + " мс всего");
        }
    }

    private final void appendWorldStats(List<String> out) {
        out.add("");
        out.add("Мир");
        if (this.mc.world == null) {
            out.add("В момент сохранения мир не был загружен.");
            return;
        }
        int particles = this.countParticles();
        out.add("Сущности: " + this.countEntities() + "; частицы: " + (particles < 0 ? "недоступно" : Integer.valueOf(particles)));
        String stats = this.lastSectionStats;
        if (stats != null) {
            out.add("Статистика секций: " + stats);
        }
    }

    private final void appendThreadStats(List<String> out) {
        out.add("");
        out.add("Топ JVM-потоков по накопленному CPU-времени");
        for (String thread : this.topThreads(6)) {
            out.add("- " + thread);
        }
    }

    private final void appendSpikes(List<String> out, long finishedAtMs) {
        out.add("");
        out.add("Найдено подозрений: " + this.spikes.size());
        if (this.spikes.isEmpty()) {
            out.add("Просадок, прошедших порог детектора, не найдено.");
            return;
        }
        Iterator<Spike> iterator = this.spikes.iterator();
        Intrinsics.checkNotNullExpressionValue(iterator, (String)"iterator(...)");
        Iterator<Spike> iterator2 = iterator;
        while (iterator2.hasNext()) {
            Spike spike = iterator2.next();
            long ageSeconds = Math.max(0L, finishedAtMs - spike.getTimeMs()) / 1000L;
            Locale locale = Locale.ROOT;
            String string = "- %.1f мс (норма %.1f мс), %s, за %d с до выключения";
            Object[] objectArray = new Object[]{spike.getFrameMs(), spike.getBaselineMs(), spike.getCause(), ageSeconds};
            String string2 = String.format(locale, string, Arrays.copyOf(objectArray, objectArray.length));
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"format(...)");
            out.add(string2);
            locale = Locale.ROOT;
            string = "  %s; пауза GC %d мс; аллокации %s/с; pC%d pU%d; сущности %d; частицы %s";
            objectArray = new Object[]{spike.getDetail(), spike.getGcPauseMs(), Profiler.Companion.mib(spike.getAllocPerSec()), spike.getPc(), spike.getPu(), spike.getEntities(), spike.getParticles() < 0 ? "недоступно" : String.valueOf(spike.getParticles())};
            String string3 = String.format(locale, string, Arrays.copyOf(objectArray, objectArray.length));
            Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"format(...)");
            out.add(string3);
        }
    }

    private final List<String> topThreads(int limit) {
        List<String> result = new ArrayList<>();
        try {
            if (!this.threadBean.isThreadCpuTimeSupported()) {
                result.add("JVM не поддерживает CPU-тайминг потоков.");
                return result;
            }
            if (!this.threadBean.isThreadCpuTimeEnabled()) {
                this.threadBean.setThreadCpuTimeEnabled(true);
            }
            long[] ids = this.threadBean.getAllThreadIds();
            ArrayList<ThreadCpu> threads = new ArrayList<ThreadCpu>();
            Intrinsics.checkNotNull((Object)ids);
            for (long id : ids) {
                long cpuNanos = this.threadBean.getThreadCpuTime(id);
                if (cpuNanos <= 0L) continue;
                ThreadInfo info = this.threadBean.getThreadInfo(id);
                String name = (info != null && info.getThreadName() != null) ? info.getThreadName() : ("#" + id);
                threads.add(new ThreadCpu(name, cpuNanos));
            }
            threads.sort(Comparator.comparingLong(ThreadCpu::getCpuNanos).reversed());
            int n = Math.min(limit, threads.size());
            for (int i = 0; i < n; ++i) {
                ThreadCpu thread = threads.get(i);
                result.add(Profiler.Companion.truncate(thread.getName(), 28) + " " + thread.getCpuNanos() / 1000000L + " ms");
            }
        }
        catch (Throwable throwable) {
            result.add("Не удалось прочитать потоки: " + throwable.getClass().getSimpleName());
        }
        return result;
    }

    private final long[] sampleGc() {
        long count = 0L;
        long time = 0L;
        for (GarbageCollectorMXBean bean : this.gcBeans) {
            long collectionCount = bean.getCollectionCount();
            long collectionTime = bean.getCollectionTime();
            if (collectionCount > 0L) {
                count += collectionCount;
            }
            if (collectionTime <= 0L) continue;
            time += collectionTime;
        }
        return new long[]{count, time};
    }

    private final long gcPauseTotalMs() {
        long time = 0L;
        for (GarbageCollectorMXBean bean : this.gcBeans) {
            long collectionTime;
            String string = (String) (bean.getName());
            Locale locale = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue((Object)locale, (String)"ROOT");
            String string2 = string.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"toLowerCase(...)");
            if (String.valueOf(string2).contains("concurrent") || (collectionTime = bean.getCollectionTime()) <= 0L) continue;
            time += collectionTime;
        }
        return time;
    }

    @Protect(value=Level.MAX)
    private final int countEntities() {
        ClientWorld level = this.mc.world;
        if (level == null) {
            return 0;
        }
        try {
            int entities = 0;
            for (Entity ignored : level.getEntities()) {
                ++entities;
            }
            return entities;
        }
        catch (Throwable t) {
            return -1;
        }
    }

    @Protect(value=Level.MAX)
    private final int countParticles() {
        int n;
        ClientWorld clientWorld3 = this.mc.world;
        if (clientWorld3 == null) {
            return -1;
        }
        ClientWorld level = clientWorld3;
        try {
            Object particles;
            Object particleEngine;
            Field peField;
            if (!this.reflectionResolved) {
                this.particleEngineField = Profiler.Companion.findField(this.mc.getClass(), "particleEngine");
                peField = this.particleEngineField;
                if (peField != null) {
                    peField.setAccessible(true);
                    Object particleEngine2 = peField.get(this.mc);
                    if (particleEngine2 != null) {
                        Field field = this.particlesMapField = Profiler.Companion.findField(particleEngine2.getClass(), "particles");
                        if (field != null) {
                            field.setAccessible(true);
                        }
                    }
                }
                this.reflectionResolved = true;
            }
            Field field = this.particleEngineField;
            if (field == null) {
                return -1;
            }
            peField = field;
            Field field2 = this.particlesMapField;
            if (field2 == null) {
                return -1;
            }
            Field pmField = field2;
            Object object = particleEngine = peField.get(this.mc);
            Object object2 = particles = object == null ? null : pmField.get(object);
            if (!(particles instanceof Map)) {
                return -1;
            }
            int total = 0;
            for (Object value : ((Map)particles).values()) {
                if (!(value instanceof Collection)) continue;
                total += ((Collection)value).size();
            }
            n = total;
        }
        catch (Throwable t) {
            n = -1;
        }
        return n;
    }

    @Protect(value=Level.MAX)
    private final String sectionStats() {
        String string;
        WorldRenderer worldRenderer2 = this.mc.worldRenderer;
        if (worldRenderer2 == null) {
            return null;
        }
        WorldRenderer levelRenderer = worldRenderer2;
        try {
            Object stats;
            Method method = levelRenderer.getClass().getMethod("getSectionStatistics", new Class[0]);
            Object object = stats = method.invoke((Object)levelRenderer, new Object[0]);
            string = object != null ? object.toString() : null;
        }
        catch (Throwable t) {
            string = null;
        }
        return string;
    }

    @Protect(value=Level.MAX)
    private final void resetStats() {
        this.count = 0;
        this.head = 0;
        this.lastFrameNanos = 0L;
        this.baselineMs = 4.0;
        this.lastGcCount = -1L;
        this.lastGcPauseMs = -1L;
        this.lastHeapUsed = -1L;
        this.allocAccum = 0L;
        this.allocRatePerSec = 0L;
        this.allocWindowStart = 0L;
        this.lastWorldSample = 0L;
        this.baseEntities = 0;
        this.baseParticles = -1;
        this.lastSectionSample = 0L;
        this.pcRecent = 0;
        this.puRecent = 0;
        this.pcRecentTime = 0L;
        this.lastSectionStats = null;
        this.spikes.clear();
    }

    private static final long topThreads$lambda$0(ThreadCpu it) {
        return it.getCpuNanos();
    }

    private static final long topThreads$lambda$1(Function1 $tmp0, Object p0) {
        return ((Number)$tmp0.invoke(p0)).longValue();
    }

    static {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss_SSS", Locale.ROOT).withZone(ZoneId.systemDefault());
        Intrinsics.checkNotNullExpressionValue((Object)dateTimeFormatter, (String)"withZone(...)");
        REPORT_TIME = dateTimeFormatter;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J'\u0010\u0018\u001a\u0004\u0018\u00010\u00172\f\u0010\u0015\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001bR\u0014\u0010\u001e\u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b \u0010\u001fR\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010#\u00a8\u0006$"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/Profiler.Companion;", "", "<init>", "()V", "", "usedHeap", "()J", "bytes", "", "mib", "(J)Ljava/lang/String;", "value", "", "maxLength", "truncate", "(Ljava/lang/String;I)Ljava/lang/String;", "stats", "key", "parseStat", "(Ljava/lang/String;Ljava/lang/String;)I", "Ljava/lang/Class;", "type", "name", "Ljava/lang/reflect/Field;", "findField", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;", "CAPACITY", "I", "SPIKE_CAPACITY", "", "SPIKE_FLOOR_MS", "D", "SPIKE_MULTIPLIER", "Ljava/time/format/DateTimeFormatter;", "REPORT_TIME", "Ljava/time/format/DateTimeFormatter;", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final long usedHeap() {
            Runtime runtime = Runtime.getRuntime();
            return runtime.totalMemory() - runtime.freeMemory();
        }

        private final String mib(long bytes) {
            String string;
            double megabytes = (double)bytes / 1048576.0;
            if (megabytes >= 1024.0) {
                Locale locale = Locale.ROOT;
                String string2 = "%.2f GiB";
                Object[] objectArray = new Object[]{megabytes / 1024.0};
                String string3 = String.format(locale, string2, Arrays.copyOf(objectArray, objectArray.length));
                string = string3;
                Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"format(...)");
            } else {
                Locale locale = Locale.ROOT;
                String string4 = "%.0f MiB";
                Object[] objectArray = new Object[]{megabytes};
                String string5 = String.format(locale, string4, Arrays.copyOf(objectArray, objectArray.length));
                string = string5;
                Intrinsics.checkNotNullExpressionValue((Object)string5, (String)"format(...)");
            }
            return string;
        }

        private final String truncate(String value, int maxLength) {
            if (value.length() <= maxLength) {
                return value;
            }
            return value.substring(0, maxLength - 1) + "...";
        }

        private final int parseStat(String stats, String key) {
            int n;
            int index = String.valueOf(stats).indexOf(key);
            if (index < 0) {
                return -1;
            }
            index += key.length();
            while (index < stats.length() && stats.charAt(index) == ' ') {
                ++index;
            }
            int start = index;
            while (index < stats.length() && Character.isDigit(stats.charAt(index))) {
                ++index;
            }
            try {
                int n2;
                if (start == index) {
                    n2 = -1;
                } else {
                    String string = stats.substring(start, index);
                    Intrinsics.checkNotNullExpressionValue((Object)string, (String)"substring(...)");
                    n2 = Integer.parseInt(string);
                }
                n = n2;
            }
            catch (NumberFormatException ignored) {
                n = -1;
            }
            return n;
        }

        private final Field findField(Class<?> type, String name) {
            for (Class<?> current = type; current != null && !Intrinsics.areEqual(current, Object.class); current = current.getSuperclass()) {
                try {
                    return current.getDeclaredField(name);
                }
                catch (NoSuchFieldException noSuchFieldException) {
                    continue;
                }
            }
            return null;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0014\b\u0082\b\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\f\u0012\u0006\u0010\u0010\u001a\u00020\f\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\u0017\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u0007H\u00c6\u0003\u00a2\u0006\u0004\b\u001a\u0010\u0019J\u0010\u0010\u001b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001b\u0010\u0014J\u0010\u0010\u001c\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u001c\u0010\u0014J\u0010\u0010\u001d\u001a\u00020\fH\u00c6\u0003\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\fH\u00c6\u0003\u00a2\u0006\u0004\b\u001f\u0010\u001eJ\u0010\u0010 \u001a\u00020\fH\u00c6\u0003\u00a2\u0006\u0004\b \u0010\u001eJ\u0010\u0010!\u001a\u00020\fH\u00c6\u0003\u00a2\u0006\u0004\b!\u0010\u001eJ~\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\fH\u00c6\u0001\u00a2\u0006\u0004\b\"\u0010#J\u001b\u0010&\u001a\u00020%2\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b&\u0010'J\u0011\u0010(\u001a\u00020\fH\u00d6\u0081\u0004\u00a2\u0006\u0004\b(\u0010\u001eJ\u0011\u0010)\u001a\u00020\u0007H\u00d6\u0081\u0004\u00a2\u0006\u0004\b)\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010*\u001a\u0004\b+\u0010\u0014R\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010,\u001a\u0004\b-\u0010\u0016R\u0017\u0010\u0006\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010,\u001a\u0004\b.\u0010\u0016R\u0017\u0010\b\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\b\u0010/\u001a\u0004\b0\u0010\u0019R\u0017\u0010\t\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\t\u0010/\u001a\u0004\b1\u0010\u0019R\u0017\u0010\n\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\n\u0010*\u001a\u0004\b2\u0010\u0014R\u0017\u0010\u000b\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010*\u001a\u0004\b3\u0010\u0014R\u0017\u0010\r\u001a\u00020\f8\u0006\u00a2\u0006\f\n\u0004\b\r\u00104\u001a\u0004\b5\u0010\u001eR\u0017\u0010\u000e\u001a\u00020\f8\u0006\u00a2\u0006\f\n\u0004\b\u000e\u00104\u001a\u0004\b6\u0010\u001eR\u0017\u0010\u000f\u001a\u00020\f8\u0006\u00a2\u0006\f\n\u0004\b\u000f\u00104\u001a\u0004\b7\u0010\u001eR\u0017\u0010\u0010\u001a\u00020\f8\u0006\u00a2\u0006\f\n\u0004\b\u0010\u00104\u001a\u0004\b8\u0010\u001e\u00a8\u00069"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/Profiler$Spike;", "", "", "timeMs", "", "frameMs", "baselineMs", "", "cause", "detail", "gcPauseMs", "allocPerSec", "", "pc", "pu", "entities", "particles", "<init>", "(JDDLjava/lang/String;Ljava/lang/String;JJIIII)V", "component1", "()J", "component2", "()D", "component3", "component4", "()Ljava/lang/String;", "component5", "component6", "component7", "component8", "()I", "component9", "component10", "component11", "copy", "(JDDLjava/lang/String;Ljava/lang/String;JJIIII)Lrtx/kimiko/api/modules/impl/Utils/Profiler$Spike;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "J", "getTimeMs", "D", "getFrameMs", "getBaselineMs", "Ljava/lang/String;", "getCause", "getDetail", "getGcPauseMs", "getAllocPerSec", "I", "getPc", "getPu", "getEntities", "getParticles", "rtx.kimiko:kimiko"})
    private static final class Spike {
        private final long timeMs;
        private final double frameMs;
        private final double baselineMs;
        @NotNull
        private final String cause;
        @NotNull
        private final String detail;
        private final long gcPauseMs;
        private final long allocPerSec;
        private final int pc;
        private final int pu;
        private final int entities;
        private final int particles;

        public Spike(long timeMs, double frameMs, double baselineMs, @NotNull String cause, @NotNull String detail, long gcPauseMs, long allocPerSec, int pc, int pu, int entities, int particles) {
            Intrinsics.checkNotNullParameter((Object)cause, (String)"cause");
            Intrinsics.checkNotNullParameter((Object)detail, (String)"detail");
            this.timeMs = timeMs;
            this.frameMs = frameMs;
            this.baselineMs = baselineMs;
            this.cause = cause;
            this.detail = detail;
            this.gcPauseMs = gcPauseMs;
            this.allocPerSec = allocPerSec;
            this.pc = pc;
            this.pu = pu;
            this.entities = entities;
            this.particles = particles;
        }

        public final long getTimeMs() {
            return this.timeMs;
        }

        public final double getFrameMs() {
            return this.frameMs;
        }

        public final double getBaselineMs() {
            return this.baselineMs;
        }

        @NotNull
        public final String getCause() {
            return this.cause;
        }

        @NotNull
        public final String getDetail() {
            return this.detail;
        }

        public final long getGcPauseMs() {
            return this.gcPauseMs;
        }

        public final long getAllocPerSec() {
            return this.allocPerSec;
        }

        public final int getPc() {
            return this.pc;
        }

        public final int getPu() {
            return this.pu;
        }

        public final int getEntities() {
            return this.entities;
        }

        public final int getParticles() {
            return this.particles;
        }

        public final long component1() {
            return this.timeMs;
        }

        public final double component2() {
            return this.frameMs;
        }

        public final double component3() {
            return this.baselineMs;
        }

        @NotNull
        public final String component4() {
            return this.cause;
        }

        @NotNull
        public final String component5() {
            return this.detail;
        }

        public final long component6() {
            return this.gcPauseMs;
        }

        public final long component7() {
            return this.allocPerSec;
        }

        public final int component8() {
            return this.pc;
        }

        public final int component9() {
            return this.pu;
        }

        public final int component10() {
            return this.entities;
        }

        public final int component11() {
            return this.particles;
        }

        @NotNull
        public final Spike copy(long timeMs, double frameMs, double baselineMs, @NotNull String cause, @NotNull String detail, long gcPauseMs, long allocPerSec, int pc, int pu, int entities, int particles) {
            Intrinsics.checkNotNullParameter((Object)cause, (String)"cause");
            Intrinsics.checkNotNullParameter((Object)detail, (String)"detail");
            return new Spike(timeMs, frameMs, baselineMs, cause, detail, gcPauseMs, allocPerSec, pc, pu, entities, particles);
        }

        public static /* synthetic */ Spike copy$default(Spike spike, long l, double d, double d2, String string, String string2, long l2, long l3, int n, int n2, int n3, int n4, int n5, Object object) {
            if ((n5 & 1) != 0) {
                l = spike.timeMs;
            }
            if ((n5 & 2) != 0) {
                d = spike.frameMs;
            }
            if ((n5 & 4) != 0) {
                d2 = spike.baselineMs;
            }
            if ((n5 & 8) != 0) {
                string = spike.cause;
            }
            if ((n5 & 0x10) != 0) {
                string2 = spike.detail;
            }
            if ((n5 & 0x20) != 0) {
                l2 = spike.gcPauseMs;
            }
            if ((n5 & 0x40) != 0) {
                l3 = spike.allocPerSec;
            }
            if ((n5 & 0x80) != 0) {
                n = spike.pc;
            }
            if ((n5 & 0x100) != 0) {
                n2 = spike.pu;
            }
            if ((n5 & 0x200) != 0) {
                n3 = spike.entities;
            }
            if ((n5 & 0x400) != 0) {
                n4 = spike.particles;
            }
            return spike.copy(l, d, d2, string, string2, l2, l3, n, n2, n3, n4);
        }

        @NotNull
        public String toString() {
            return "Spike(timeMs=" + this.timeMs + ", frameMs=" + this.frameMs + ", baselineMs=" + this.baselineMs + ", cause=" + this.cause + ", detail=" + this.detail + ", gcPauseMs=" + this.gcPauseMs + ", allocPerSec=" + this.allocPerSec + ", pc=" + this.pc + ", pu=" + this.pu + ", entities=" + this.entities + ", particles=" + this.particles + ")";
        }

        public int hashCode() {
            int result = Long.hashCode(this.timeMs);
            result = result * 31 + Double.hashCode(this.frameMs);
            result = result * 31 + Double.hashCode(this.baselineMs);
            result = result * 31 + this.cause.hashCode();
            result = result * 31 + this.detail.hashCode();
            result = result * 31 + Long.hashCode(this.gcPauseMs);
            result = result * 31 + Long.hashCode(this.allocPerSec);
            result = result * 31 + Integer.hashCode(this.pc);
            result = result * 31 + Integer.hashCode(this.pu);
            result = result * 31 + Integer.hashCode(this.entities);
            result = result * 31 + Integer.hashCode(this.particles);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Spike)) {
                return false;
            }
            Spike spike = (Spike)other;
            if (this.timeMs != spike.timeMs) {
                return false;
            }
            if (Double.compare(this.frameMs, spike.frameMs) != 0) {
                return false;
            }
            if (Double.compare(this.baselineMs, spike.baselineMs) != 0) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.cause, (Object)spike.cause)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.detail, (Object)spike.detail)) {
                return false;
            }
            if (this.gcPauseMs != spike.gcPauseMs) {
                return false;
            }
            if (this.allocPerSec != spike.allocPerSec) {
                return false;
            }
            if (this.pc != spike.pc) {
                return false;
            }
            if (this.pu != spike.pu) {
                return false;
            }
            if (this.entities != spike.entities) {
                return false;
            }
            return this.particles == spike.particles;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ$\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u00020\u0012H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0015\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0015\u0010\tR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u0019\u0010\u000b\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/Profiler$ThreadCpu;", "", "", "name", "", "cpuNanos", "<init>", "(Ljava/lang/String;J)V", "component1", "()Ljava/lang/String;", "component2", "()J", "copy", "(Ljava/lang/String;J)Lrtx/kimiko/api/modules/impl/Utils/Profiler$ThreadCpu;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "Ljava/lang/String;", "getName", "J", "getCpuNanos", "rtx.kimiko:kimiko"})
    private static final class ThreadCpu {
        @NotNull
        private final String name;
        private final long cpuNanos;

        public ThreadCpu(@NotNull String name, long cpuNanos) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            this.name = name;
            this.cpuNanos = cpuNanos;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        public final long getCpuNanos() {
            return this.cpuNanos;
        }

        @NotNull
        public final String component1() {
            return this.name;
        }

        public final long component2() {
            return this.cpuNanos;
        }

        @NotNull
        public final ThreadCpu copy(@NotNull String name, long cpuNanos) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            return new ThreadCpu(name, cpuNanos);
        }

        public static /* synthetic */ ThreadCpu copy$default(ThreadCpu threadCpu, String string, long l, int n, Object object) {
            if ((n & 1) != 0) {
                string = threadCpu.name;
            }
            if ((n & 2) != 0) {
                l = threadCpu.cpuNanos;
            }
            return threadCpu.copy(string, l);
        }

        @NotNull
        public String toString() {
            return "ThreadCpu(name=" + this.name + ", cpuNanos=" + this.cpuNanos + ")";
        }

        public int hashCode() {
            int result = this.name.hashCode();
            result = result * 31 + Long.hashCode(this.cpuNanos);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ThreadCpu)) {
                return false;
            }
            ThreadCpu threadCpu = (ThreadCpu)other;
            if (!Intrinsics.areEqual((Object)this.name, (Object)threadCpu.name)) {
                return false;
            }
            return this.cpuNanos == threadCpu.cpuNanos;
        }
    }
}

