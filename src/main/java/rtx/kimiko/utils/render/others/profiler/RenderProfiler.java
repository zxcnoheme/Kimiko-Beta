/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.CommandEncoder
 *  com.mojang.blaze3d.systems.GpuQuery
 *  com.mojang.blaze3d.systems.RenderSystem
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.comparisons.ComparisonsKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.others.profiler;

import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuQuery;
import com.mojang.blaze3d.systems.RenderSystem;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.OptionalLong;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.utils.storage.RepositoryStorage;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0004MNOPB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J\u0013\u0010\n\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u0003J\u001b\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u000f\u0010\u0003J\u000f\u0010\u0010\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u001d\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\f\u001a\u00020\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0015\u001a\u00020\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0011H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0003J\u000f\u0010\u0018\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0003J\u0017\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u0019H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ!\u0010\u001f\u001a\u00020\b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001dH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001f\u0010 J\u001d\u0010!\u001a\u00020\b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001dH\u0002\u00a2\u0006\u0004\b!\u0010 J\u001f\u0010&\u001a\u00020%2\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b&\u0010'J\u001f\u0010(\u001a\u00020%2\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b(\u0010'J\u001d\u0010+\u001a\u00020%2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020%0)H\u0002\u00a2\u0006\u0004\b+\u0010,J-\u0010-\u001a\u00020\b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001d2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020\"H\u0002\u00a2\u0006\u0004\b-\u0010.J\u0015\u0010/\u001a\u0004\u0018\u00010\u000bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b/\u00100R\u0014\u00102\u001a\u0002018\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0016\u00104\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u00105R\u001a\u00108\u001a\b\u0012\u0004\u0012\u000207068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00109R0\u0010=\u001a\u001e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020;0:j\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020;`<8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u0010>R0\u0010?\u001a\u001e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\"0:j\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\"`<8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u0010>R\u0016\u0010@\u001a\u0002018\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b@\u00103R\u0016\u0010B\u001a\u00020A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0016\u0010D\u001a\u00020A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bD\u0010CR\u0016\u0010F\u001a\u0004\u0018\u00010E8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bF\u0010GR\u0016\u0010H\u001a\u00020A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bH\u0010CR\u0016\u0010I\u001a\u00020A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bI\u0010CR\u0014\u0010K\u001a\u00020J8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bK\u0010L\u00a8\u0006Q"}, d2={"Lrtx/kimiko/utils/render/others/profiler/RenderProfiler;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "isActive", "()Z", "", "start", "stop", "", "name", "mark", "(Ljava/lang/String;)V", "beginFrame", "sampleAllocation", "Lrtx/kimiko/utils/render/others/profiler/RenderProfiler$Scope;", "begin", "(Ljava/lang/String;)Lrtx/kimiko/utils/render/others/profiler/RenderProfiler$Scope;", "scope", "end", "(Lrtx/kimiko/utils/render/others/profiler/RenderProfiler$Scope;)V", "drain", "drainAll", "Lcom/mojang/blaze3d/systems/GpuQuery;", "query", "closeQuietly", "(Lcom/mojang/blaze3d/systems/GpuQuery;)V", "", "out", "appendReport", "(Ljava/util/List;)V", "appendSummary", "Lrtx/kimiko/utils/render/others/profiler/RenderProfiler$Section;", "section", "pass", "", "perFrameGpu", "(Lrtx/kimiko/utils/render/others/profiler/RenderProfiler$Section;Ljava/lang/String;)D", "perFrameCpu", "", "values", "median", "(Ljava/util/List;)D", "appendSection", "(Ljava/util/List;Ljava/lang/String;Lrtx/kimiko/utils/render/others/profiler/RenderProfiler$Section;)V", "saveReport", "()Ljava/lang/String;", "", "MAX_PENDING", "I", "active", "Z", "Ljava/util/ArrayDeque;", "Lrtx/kimiko/utils/render/others/profiler/RenderProfiler$Pending;", "pending", "Ljava/util/ArrayDeque;", "Ljava/util/LinkedHashMap;", "Lrtx/kimiko/utils/render/others/profiler/RenderProfiler$Stat;", "Lkotlin/collections/LinkedHashMap;", "stats", "Ljava/util/LinkedHashMap;", "sections", "openScopes", "", "frames", "J", "startedNanos", "Lcom/sun/management/ThreadMXBean;", "threadBean", "Lcom/sun/management/ThreadMXBean;", "lastAllocBytes", "allocBytes", "Ljava/time/format/DateTimeFormatter;", "REPORT_TIME", "Ljava/time/format/DateTimeFormatter;", "Scope", "Pending", "Section", "Stat", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nRenderProfiler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RenderProfiler.kt\nrtx/kimiko/utils/render/others/profiler/RenderProfiler\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,362:1\n460#2,7:363\n460#2,7:370\n460#2,7:377\n460#2,7:384\n1739#3:391\n1814#3,3:392\n1739#3:395\n1814#3,3:396\n1544#3:399\n1633#3,5:400\n1233#3:405\n1739#3:406\n1814#3,3:407\n1739#3:410\n1814#3,3:411\n1233#3:414\n777#3:415\n873#3,2:416\n777#3:419\n873#3,2:420\n1#4:418\n*S KotlinDebug\n*F\n+ 1 RenderProfiler.kt\nrtx/kimiko/utils/render/others/profiler/RenderProfiler\n*L\n145#1:363,7\n164#1:370,7\n182#1:377,7\n223#1:384,7\n229#1:391\n229#1:392,3\n230#1:395\n230#1:396,3\n243#1:399\n243#1:400,5\n244#1:405\n248#1:406\n248#1:407,3\n249#1:410\n249#1:411,3\n311#1:414\n327#1:415\n327#1:416,2\n328#1:419\n328#1:420,2\n*E\n"})
public final class RenderProfiler {
    @NotNull
    public static final RenderProfiler INSTANCE = new RenderProfiler();
    private static final int MAX_PENDING = 256;
    private static volatile boolean active;
    @NotNull
    private static final ArrayDeque<Pending> pending;
    @NotNull
    private static final LinkedHashMap<String, Stat> stats;
    @NotNull
    private static final LinkedHashMap<String, Section> sections;
    private static int openScopes;
    private static long frames;
    private static long startedNanos;
    @Nullable
    private static final com.sun.management.ThreadMXBean threadBean;
    private static long lastAllocBytes;
    private static long allocBytes;
    @NotNull
    private static final DateTimeFormatter REPORT_TIME;

    private RenderProfiler() {
    }

    @JvmStatic
    public static final boolean isActive() {
        return active;
    }

    @JvmStatic
    public static final void start() {
        INSTANCE.drainAll();
        sections.clear();
        stats.clear();
        frames = 0L;
        openScopes = 0;
        startedNanos = System.nanoTime();
        allocBytes = 0L;
        lastAllocBytes = -1L;
        active = true;
    }

    @JvmStatic
    public static final void stop() {
        active = false;
        INSTANCE.drainAll();
    }

    @JvmStatic
    public static final void mark(@NotNull String name) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        if (!active) {
            return;
        }
        INSTANCE.drain();
        double seconds = (double)(System.nanoTime() - startedNanos) / 1.0E9;
        ((Map)sections).put(name, new Section(frames, seconds, allocBytes, new LinkedHashMap(stats)));
        stats.clear();
        frames = 0L;
        allocBytes = 0L;
        startedNanos = System.nanoTime();
    }

    @JvmStatic
    public static final void beginFrame() {
        if (!active) {
            return;
        }
        long l = frames;
        frames = l + 1L;
        openScopes = 0;
        INSTANCE.sampleAllocation();
        INSTANCE.drain();
    }

    private final void sampleAllocation() {
        long l;
        com.sun.management.ThreadMXBean threadMXBean = threadBean;
        if (threadMXBean == null) {
            return;
        }
        com.sun.management.ThreadMXBean bean = threadMXBean;
        try {
            l = bean.getCurrentThreadAllocatedBytes();
        }
        catch (Throwable throwable) {
            return;
        }
        long now = l;
        if (now < 0L) {
            return;
        }
        l = lastAllocBytes;
        boolean bl = 0L <= l ? l <= now : false;
        if (bl) {
            allocBytes += now - lastAllocBytes;
        }
        lastAllocBytes = now;
    }

    @JvmStatic
    @Nullable
    public static final Scope begin(@NotNull String name) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        if (!active) {
            return null;
        }
        long cpuStart = System.nanoTime();
        if (openScopes > 0) {
            return new Scope(name, null, null, cpuStart);
        }
        CommandEncoder encoder = null;
        GpuQuery query = null;
        try {
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            Intrinsics.checkNotNullExpressionValue((Object)commandEncoder, (String)"createCommandEncoder(...)");
            encoder = commandEncoder;
            GpuQuery gpuQuery = encoder.timerQueryBegin();
            Intrinsics.checkNotNullExpressionValue((Object)gpuQuery, (String)"timerQueryBegin(...)");
            query = gpuQuery;
        }
        catch (Throwable throwable) {
            return new Scope(name, null, null, cpuStart);
        }
        int n = openScopes;
        openScopes = n + 1;
        return new Scope(name, encoder, query, cpuStart);
    }

    /*
     * WARNING - void declaration
     */
    @JvmStatic
    public static final void end(@Nullable Scope scope) {
        if (scope == null) {
            return;
        }
        Stat stat = stats.computeIfAbsent(scope.getName$rtx_kimiko_kimiko(), k -> new Stat());
        stat.setCpuNanos(stat.getCpuNanos() + (System.nanoTime() - scope.getCpuStart$rtx_kimiko_kimiko()));
        stat.setCalls(stat.getCalls() + 1L);
        GpuQuery gpuQuery = scope.getQuery$rtx_kimiko_kimiko();
        if (gpuQuery == null) {
            return;
        }
        GpuQuery query = gpuQuery;
        if (openScopes > 0) {
            openScopes--;
        }
        CommandEncoder encoder = scope.getEncoder$rtx_kimiko_kimiko();
        if (encoder == null) {
            INSTANCE.closeQuietly(query);
            return;
        }
        try {
            encoder.timerQueryEnd(query);
        }
        catch (Throwable throwable) {
            INSTANCE.closeQuietly(query);
            return;
        }
        if (pending.size() >= 256) {
            Pending oldest = pending.removeFirst();
            Stat stat2 = stats.computeIfAbsent(oldest.getName(), k -> new Stat());
            stat2.setDropped(stat2.getDropped() + 1L);
            INSTANCE.closeQuietly(oldest.getQuery());
        }
        pending.addLast(new Pending(scope.getName$rtx_kimiko_kimiko(), query));
    }

    private final void drain() {
        Pending head;
        while ((head = pending.peekFirst()) != null) {
            OptionalLong value;
            try {
                value = head.getQuery().getValue();
            }
            catch (Throwable throwable) {
                pending.removeFirst();
                this.closeQuietly(head.getQuery());
                continue;
            }
            if (value == null || !value.isPresent()) {
                return;
            }
            pending.removeFirst();
            Stat stat = stats.computeIfAbsent(head.getName(), k -> new Stat());
            stat.setGpuNanos(stat.getGpuNanos() + value.getAsLong());
            stat.setGpuSamples(stat.getGpuSamples() + 1L);
            this.closeQuietly(head.getQuery());
        }
    }

    private final void drainAll() {
        this.drain();
        while (!((Collection)pending).isEmpty()) {
            this.closeQuietly(pending.removeFirst().getQuery());
        }
    }

    private final void closeQuietly(GpuQuery query) {
        try {
            query.close();
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    @JvmStatic
    public static final void appendReport(@NotNull List<String> out) {
        Intrinsics.checkNotNullParameter(out, (String)"out");
        if (!((Map)stats).isEmpty()) {
            RenderProfiler.mark("итог");
        }
        out.add("Рендер: пассы");
        if (sections.isEmpty()) {
            out.add("Замеров не записано.");
            out.add("");
            return;
        }
        for (Map.Entry<String, Section> entry : sections.entrySet()) {
            String name = entry.getKey();
            Section section = entry.getValue();
            INSTANCE.appendSection(out, name, section);
        }
        INSTANCE.appendSummary(out);
    }

    /*
     * WARNING - void declaration
     */
    private final void appendSummary(List<String> out) {
        LinkedHashMap<String, List<Section>> groups = new LinkedHashMap<>();
        for (Map.Entry<String, Section> entry : sections.entrySet()) {
            String name = entry.getKey();
            Section section = entry.getValue();
            String base = (name.contains(String.valueOf('#')) ? name.substring(0, name.indexOf('#')) : name);
            groups.computeIfAbsent(base, k -> new ArrayList<>()).add(section);
        }
        for (Map.Entry<String, List<Section>> entry : groups.entrySet()) {
            String base = entry.getKey();
            List<Section> runs = entry.getValue();
            if (runs.size() < 2) continue;
            out.add("");
            out.add("[" + base + "] медиана по " + runs.size() + " повторам");
            List<Double> fps = new ArrayList<>(runs.size());
            for (Section s : runs) {
                fps.add(s.getSeconds() > 0.0 ? (double)s.getFrames() / s.getSeconds() : 0.0);
            }
            List<Double> alloc = new ArrayList<>(runs.size());
            for (Section s : runs) {
                alloc.add(s.getFrames() > 0L ? (double)s.getAllocBytes() / 1024.0 / (double)s.getFrames() : 0.0);
            }
            out.add(String.format(Locale.ROOT, "fps: %.1f (%.1f..%.1f)   аллокация: %.1f КиБ/кадр (%.1f..%.1f)", this.median(fps), Collections.min(fps), Collections.max(fps), this.median(alloc), Collections.min(alloc), Collections.max(alloc)));
            out.add(String.format(Locale.ROOT, "%-28s %10s %18s %10s", "пасс", "gpu/кадр", "разброс", "cpu/кадр"));
            HashSet<String> passesSet = new HashSet<>();
            for (Section s : runs) {
                passesSet.addAll(s.getStats().keySet());
            }
            List<String> ordered = new ArrayList<>(passesSet);
            ordered.sort((a, b) -> {
                ArrayList<Double> bGpu = new ArrayList<>(runs.size());
                for (Section s : runs) {
                    bGpu.add(RenderProfiler.INSTANCE.perFrameGpu(s, b));
                }
                ArrayList<Double> aGpu = new ArrayList<>(runs.size());
                for (Section s : runs) {
                    aGpu.add(RenderProfiler.INSTANCE.perFrameGpu(s, a));
                }
                return Double.compare(RenderProfiler.INSTANCE.median(bGpu), RenderProfiler.INSTANCE.median(aGpu));
            });
            for (String pass : ordered) {
                ArrayList<Double> gpu = new ArrayList<>(runs.size());
                ArrayList<Double> cpu = new ArrayList<>(runs.size());
                for (Section s : runs) {
                    gpu.add(INSTANCE.perFrameGpu(s, pass));
                    cpu.add(INSTANCE.perFrameCpu(s, pass));
                }
                out.add(String.format(Locale.ROOT, "%-28s %9.3f  %7.3f..%-7.3f %9.3f", pass, this.median(gpu), Collections.min(gpu), Collections.max(gpu), this.median(cpu)));
            }
        }
    }

    private final double perFrameGpu(Section section, String pass) {
        if (section.getFrames() <= 0L) {
            return 0.0;
        }
        Stat stat = section.getStats().get(pass);
        return (double)(stat != null ? stat.getGpuNanos() : 0L) / 1000000.0 / (double)section.getFrames();
    }

    private final double perFrameCpu(Section section, String pass) {
        if (section.getFrames() <= 0L) {
            return 0.0;
        }
        Stat stat = section.getStats().get(pass);
        return (double)(stat != null ? stat.getCpuNanos() : 0L) / 1000000.0 / (double)section.getFrames();
    }

    private final double median(List<Double> values) {
        if (values.isEmpty()) {
            return 0.0;
        }
        List<Double> sorted = CollectionsKt.sorted((Iterable)values);
        int middle = sorted.size() / 2;
        return sorted.size() % 2 == 1 ? ((Number)sorted.get(middle)).doubleValue() : (((Number)sorted.get(middle - 1)).doubleValue() + ((Number)sorted.get(middle)).doubleValue()) / 2.0;
    }

    private final void appendSection(List<String> out, String name, Section section) {
        long frameCount = section.getFrames();
        out.add("");
        out.add("[" + name + "]");
        if (frameCount <= 0L) {
            out.add("Кадров не записано.");
            return;
        }
        out.add(String.format(Locale.ROOT, "Кадров: %d за %.1f с (%.1f fps)", frameCount, section.getSeconds(), section.getSeconds() > 0.0 ? (double)frameCount / section.getSeconds() : 0.0));
        out.add(String.format(Locale.ROOT, "Аллокация рендер-потока: %.1f КиБ/кадр", (double)section.getAllocBytes() / 1024.0 / (double)frameCount));
        if (section.getStats().isEmpty()) {
            out.add("Ни один размеченный пасс не выполнялся.");
            return;
        }
        out.add(String.format(Locale.ROOT, "%-28s %10s %11s %10s %9s", "пасс", "gpu/кадр", "gpu/вызов", "cpu/кадр", "вызовов"));
        double gpuTotal = 0.0;
        double cpuTotal = 0.0;
        List<Map.Entry<String, Stat>> rows = new ArrayList<>(section.getStats().entrySet());
        rows.sort((a, b) -> Long.compare(b.getValue().getGpuNanos(), a.getValue().getGpuNanos()));
        for (Map.Entry<String, Stat> entry : rows) {
            String pass = entry.getKey();
            Stat stat = entry.getValue();
            double gpuPerFrame = (double)stat.getGpuNanos() / 1000000.0 / (double)frameCount;
            double gpuPerCall = stat.getGpuSamples() > 0L ? (double)stat.getGpuNanos() / 1000000.0 / (double)stat.getGpuSamples() : 0.0;
            double cpuPerFrame = (double)stat.getCpuNanos() / 1000000.0 / (double)frameCount;
            gpuTotal += gpuPerFrame;
            cpuTotal += cpuPerFrame;
            out.add(String.format(Locale.ROOT, "%-28s %9.3f %10.3f %9.3f %9d", pass, gpuPerFrame, gpuPerCall, cpuPerFrame, stat.getCalls()));
        }
        out.add(String.format(Locale.ROOT, "%-28s %9.3f %10s %9.3f", "ИТОГО", gpuTotal, "", cpuTotal));
        long uiNanos = 0L;
        long worldNanos = 0L;
        long dropped = 0L;
        for (Map.Entry<String, Stat> entry : rows) {
            if (entry.getKey().startsWith("ui.")) {
                uiNanos += entry.getValue().getGpuNanos();
            }
            if (entry.getKey().startsWith("world.")) {
                worldNanos += entry.getValue().getGpuNanos();
            }
            dropped += entry.getValue().getDropped();
        }
        double ui = (double)uiNanos / 1000000.0 / (double)frameCount;
        double world = (double)worldNanos / 1000000.0 / (double)frameCount;
        out.add(String.format(Locale.ROOT, "Интерфейс: %.3f мс/кадр, мир: %.3f мс/кадр", ui, world));
        if (dropped > 0L) {
            out.add("Потеряно замеров (переполнение очереди): " + dropped);
        }
    }

    @JvmStatic
    @Nullable
    public static final String saveReport() {
        String string;
        ArrayList<String> out = new ArrayList<>();
        out.add("Отчёт Kimiko RenderProfiler");
        out.add("Время: " + Instant.now());
        RenderProfiler.appendReport(out);
        Path directory = RepositoryStorage.configRoot().resolve("profiler");
        Path file = directory.resolve("render-" + REPORT_TIME.format(Instant.now()) + ".txt");
        try {
            Files.createDirectories(directory, new FileAttribute[0]);
            Files.writeString(file, String.join(System.lineSeparator(), out) + System.lineSeparator(), StandardCharsets.UTF_8);
            string = file.toString();
        }
        catch (IOException ignored) {
            string = null;
        }
        return string;
    }

    public static final /* synthetic */ double access$median(RenderProfiler $this, List values) {
        return $this.median(values);
    }

    public static final /* synthetic */ double access$perFrameGpu(RenderProfiler $this, Section section, String pass) {
        return $this.perFrameGpu(section, pass);
    }

    static {
        pending = new ArrayDeque();
        stats = new LinkedHashMap();
        sections = new LinkedHashMap();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        threadBean = threadMXBean instanceof com.sun.management.ThreadMXBean ? (com.sun.management.ThreadMXBean)threadMXBean : null;
        lastAllocBytes = -1L;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss").withZone(ZoneId.systemDefault());
        Intrinsics.checkNotNullExpressionValue((Object)dateTimeFormatter, (String)"withZone(...)");
        REPORT_TIME = dateTimeFormatter;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2={"Lrtx/kimiko/utils/render/others/profiler/RenderProfiler$Pending;", "", "", "name", "Lcom/mojang/blaze3d/systems/GpuQuery;", "query", "<init>", "(Ljava/lang/String;Lcom/mojang/blaze3d/systems/GpuQuery;)V", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "Lcom/mojang/blaze3d/systems/GpuQuery;", "getQuery", "()Lcom/mojang/blaze3d/systems/GpuQuery;", "rtx.kimiko:kimiko"})
    private static final class Pending {
        @NotNull
        private final String name;
        @NotNull
        private final GpuQuery query;

        public Pending(@NotNull String name, @NotNull GpuQuery query) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            Intrinsics.checkNotNullParameter((Object)query, (String)"query");
            this.name = name;
            this.query = query;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        @NotNull
        public final GpuQuery getQuery() {
            return this.query;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\u0018\u00002\u00020\u0001B-\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u000bR\u001a\u0010\u0003\u001a\u00020\u00028\u0000X\u0080\u0004\u00a2\u0006\f\n\u0004\b\u0003\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0000X\u0080\u0004\u00a2\u0006\f\n\u0004\b\u0005\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0000X\u0080\u0004\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\t\u001a\u00020\b8\u0000X\u0080\u0004\u00a2\u0006\f\n\u0004\b\t\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/utils/render/others/profiler/RenderProfiler$Scope;", "", "", "name", "Lcom/mojang/blaze3d/systems/CommandEncoder;", "encoder", "Lcom/mojang/blaze3d/systems/GpuQuery;", "query", "", "cpuStart", "<init>", "(Ljava/lang/String;Lcom/mojang/blaze3d/systems/CommandEncoder;Lcom/mojang/blaze3d/systems/GpuQuery;J)V", "Ljava/lang/String;", "getName$rtx_kimiko_kimiko", "()Ljava/lang/String;", "Lcom/mojang/blaze3d/systems/CommandEncoder;", "getEncoder$rtx_kimiko_kimiko", "()Lcom/mojang/blaze3d/systems/CommandEncoder;", "Lcom/mojang/blaze3d/systems/GpuQuery;", "getQuery$rtx_kimiko_kimiko", "()Lcom/mojang/blaze3d/systems/GpuQuery;", "J", "getCpuStart$rtx_kimiko_kimiko", "()J", "rtx.kimiko:kimiko"})
    public static final class Scope {
        @NotNull
        private final String name;
        @Nullable
        private final CommandEncoder encoder;
        @Nullable
        private final GpuQuery query;
        private final long cpuStart;

        public Scope(@NotNull String name, @Nullable CommandEncoder encoder, @Nullable GpuQuery query, long cpuStart) {
            Intrinsics.checkNotNullParameter((Object)name, (String)"name");
            this.name = name;
            this.encoder = encoder;
            this.query = query;
            this.cpuStart = cpuStart;
        }

        @NotNull
        public final String getName$rtx_kimiko_kimiko() {
            return this.name;
        }

        @Nullable
        public final CommandEncoder getEncoder$rtx_kimiko_kimiko() {
            return this.encoder;
        }

        @Nullable
        public final GpuQuery getQuery$rtx_kimiko_kimiko() {
            return this.query;
        }

        public final long getCpuStart$rtx_kimiko_kimiko() {
            return this.cpuStart;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0002\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u00a2\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0006\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\r\u001a\u0004\b\u0013\u0010\u000fR#\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00078\u0006\u00a2\u0006\f\n\u0004\b\n\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/utils/render/others/profiler/RenderProfiler$Section;", "", "", "frames", "", "seconds", "allocBytes", "", "", "Lrtx/kimiko/utils/render/others/profiler/RenderProfiler$Stat;", "stats", "<init>", "(JDJLjava/util/Map;)V", "J", "getFrames", "()J", "D", "getSeconds", "()D", "getAllocBytes", "Ljava/util/Map;", "getStats", "()Ljava/util/Map;", "rtx.kimiko:kimiko"})
    private static final class Section {
        private final long frames;
        private final double seconds;
        private final long allocBytes;
        @NotNull
        private final Map<String, Stat> stats;

        public Section(long frames, double seconds, long allocBytes, @NotNull Map<String, Stat> stats) {
            Intrinsics.checkNotNullParameter(stats, (String)"stats");
            this.frames = frames;
            this.seconds = seconds;
            this.allocBytes = allocBytes;
            this.stats = stats;
        }

        public final long getFrames() {
            return this.frames;
        }

        public final double getSeconds() {
            return this.seconds;
        }

        public final long getAllocBytes() {
            return this.allocBytes;
        }

        @NotNull
        public final Map<String, Stat> getStats() {
            return this.stats;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0013\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\u000b\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\"\u0010\u000e\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u0006\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR\"\u0010\u0011\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\u0006\u001a\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\nR\"\u0010\u0014\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0014\u0010\u0006\u001a\u0004\b\u0015\u0010\b\"\u0004\b\u0016\u0010\n\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/utils/render/others/profiler/RenderProfiler$Stat;", "", "<init>", "()V", "", "gpuNanos", "J", "getGpuNanos", "()J", "setGpuNanos", "(J)V", "cpuNanos", "getCpuNanos", "setCpuNanos", "calls", "getCalls", "setCalls", "gpuSamples", "getGpuSamples", "setGpuSamples", "dropped", "getDropped", "setDropped", "rtx.kimiko:kimiko"})
    private static final class Stat {
        private long gpuNanos;
        private long cpuNanos;
        private long calls;
        private long gpuSamples;
        private long dropped;

        public final long getGpuNanos() {
            return this.gpuNanos;
        }

        public final void setGpuNanos(long l) {
            this.gpuNanos = l;
        }

        public final long getCpuNanos() {
            return this.cpuNanos;
        }

        public final void setCpuNanos(long l) {
            this.cpuNanos = l;
        }

        public final long getCalls() {
            return this.calls;
        }

        public final void setCalls(long l) {
            this.calls = l;
        }

        public final long getGpuSamples() {
            return this.gpuSamples;
        }

        public final void setGpuSamples(long l) {
            this.gpuSamples = l;
        }

        public final long getDropped() {
            return this.dropped;
        }

        public final void setDropped(long l) {
            this.dropped = l;
        }
    }
}

