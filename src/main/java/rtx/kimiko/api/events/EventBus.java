/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.collections.ArraysKt
 *  kotlin.collections.CollectionsKt
 *  kotlin.comparisons.ComparisonsKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package rtx.kimiko.api.events;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rtx.kimiko.api.events.CancellableEvent;
import rtx.kimiko.api.events.Event;
import rtx.kimiko.api.events.EventHandler;
import rtx.kimiko.api.events.Priority;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001b2\u00020\u0001:\u0003\u001c\u001d\u001bB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0001\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0001\u00a2\u0006\u0004\b\b\u0010\u0007J\u001f\u0010\f\u001a\u00028\u0000\"\b\b\u0000\u0010\n*\u00020\t2\u0006\u0010\u000b\u001a\u00028\u0000\u00a2\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u0011\u001a\u00020\u00102\u000e\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\u000e\u00a2\u0006\u0004\b\u0011\u0010\u0012R*\u0010\u0016\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001e"}, d2={"Lrtx/kimiko/api/events/EventBus;", "", "<init>", "()V", "listener", "", "subscribe", "(Ljava/lang/Object;)V", "unsubscribe", "Lrtx/kimiko/api/events/Event;", "E", "event", "post", "(Lrtx/kimiko/api/events/Event;)Lrtx/kimiko/api/events/Event;", "Ljava/lang/Class;", "type", "", "hasListeners", "(Ljava/lang/Class;)Z", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lrtx/kimiko/api/events/EventBus$Binding;", "bindings", "Ljava/util/concurrent/ConcurrentHashMap;", "Ljava/util/concurrent/atomic/AtomicInteger;", "insertionCounter", "Ljava/util/concurrent/atomic/AtomicInteger;", "Companion", "HandlerSpec", "Binding", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nEventBus.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EventBus.kt\nrtx/kimiko/api/events/EventBus\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,171:1\n37#2,2:172\n37#2,2:177\n3938#3:174\n4474#3,2:175\n*S KotlinDebug\n*F\n+ 1 EventBus.kt\nrtx/kimiko/api/events/EventBus\n*L\n34#1:172,2\n46#1:177,2\n41#1:174\n41#1:175,2\n*E\n"})
public final class EventBus {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ConcurrentHashMap<Class<?>, Binding[]> bindings = new ConcurrentHashMap();
    @NotNull
    private final AtomicInteger insertionCounter = new AtomicInteger();
    @NotNull
    private static final EventBus INSTANCE = new EventBus();
    @NotNull
    private static final ConcurrentHashMap<Class<?>, List<HandlerSpec>> SPEC_CACHE = new ConcurrentHashMap();
    @NotNull
    private static final Logger LOGGER;
    private static final ConcurrentHashMap.KeySetView<String, Boolean> REPORTED;
    private static volatile boolean cancelledGetterBroken;
    @Nullable
    private static volatile Field cancelledField;

    private EventBus() {
    }

    public final synchronized void subscribe(@NotNull Object listener) {
        Intrinsics.checkNotNullParameter((Object)listener, (String)"listener");
        List<HandlerSpec> specs = SPEC_CACHE.computeIfAbsent(listener.getClass(), EventBus.Companion::scanHandlers);
        if (specs.isEmpty()) {
            return;
        }
        for (HandlerSpec spec : specs) {
            MethodHandle methodHandle = spec.getHandle().bindTo(listener);
            Intrinsics.checkNotNullExpressionValue((Object)methodHandle, (String)"bindTo(...)");
            Binding binding = new Binding(listener, methodHandle, spec.getPriority(), this.insertionCounter.getAndIncrement());
            Binding[] current = this.bindings.get(spec.getEventType());
            ArrayList<Binding> list2 = current != null ? new ArrayList<Binding>(Arrays.asList(current)) : new ArrayList<Binding>();
            list2.add(binding);
            list2.sort(Comparator.comparingInt((Binding b) -> b.getPriority().ordinal()).thenComparingInt(Binding::getOrder));
            this.bindings.put(spec.getEventType(), list2.toArray(new Binding[0]));
        }
    }

    /*
     * WARNING - void declaration
     */
    public final synchronized void unsubscribe(@NotNull Object listener) {
        Intrinsics.checkNotNullParameter((Object)listener, (String)"listener");
        for (Map.Entry<Class<?>, Binding[]> entry : ((Map<Class<?>, Binding[]>)this.bindings).entrySet()) {
            Class<?> eventType = entry.getKey();
            Binding[] current = entry.getValue();
            ArrayList<Binding> list = new ArrayList<>();
            for (Binding b : current) {
                if (b.getOwner() != listener) {
                    list.add(b);
                }
            }
            if (list.size() == current.length) continue;
            if (list.isEmpty()) {
                this.bindings.remove(eventType);
                continue;
            }
            this.bindings.put(eventType, list.toArray(new Binding[0]));
        }
    }

    @NotNull
    public final <E extends Event> E post(@NotNull E event) {
        Intrinsics.checkNotNullParameter(event, (String)"event");
        Binding[] bindingArray = this.bindings.get(event.getClass());
        if (bindingArray == null) {
            return event;
        }
        Binding[] handlers = bindingArray;
        int len = handlers.length;
        if (len == 0) {
            return event;
        }
        CancellableEvent cancellableEvent = event instanceof CancellableEvent ? (CancellableEvent)event : null;
        for (int i = 0; i < len; ++i) {
            Binding binding = handlers[i];
            if (cancellableEvent != null && binding.getPriority() != Priority.MONITOR && EventBus.Companion.safeIsCancelled(cancellableEvent)) continue;
            binding.invoke(event);
        }
        return event;
    }

    public final boolean hasListeners(@NotNull Class<? extends Event> type) {
        Intrinsics.checkNotNullParameter(type, (String)"type");
        Binding[] handlers = this.bindings.get(type);
        return handlers != null && !(handlers.length == 0);
    }

    private static final List subscribe$lambda$0(Class it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        return EventBus.Companion.scanHandlers(it);
    }

    private static final List subscribe$lambda$1(Function1 $tmp0, Object p0) {
        return (List)$tmp0.invoke(p0);
    }

    @JvmStatic
    @NotNull
    public static final EventBus get() {
        return Companion.get();
    }

    static {
        Logger logger = LoggerFactory.getLogger((String)"Kimiko/EventBus");
        Intrinsics.checkNotNullExpressionValue((Object)logger, (String)"getLogger(...)");
        LOGGER = logger;
        REPORTED = ConcurrentHashMap.newKeySet();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\t\u0010\nJ\u0015\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0002\u001a\u00020\u00018\u0006\u00a2\u0006\f\n\u0004\b\u0002\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\u0013R\u0017\u0010\u0006\u001a\u00020\u00058\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\b\u001a\u00020\u00078\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/events/EventBus$Binding;", "", "owner", "Ljava/lang/invoke/MethodHandle;", "handle", "Lrtx/kimiko/api/events/Priority;", "priority", "", "order", "<init>", "(Ljava/lang/Object;Ljava/lang/invoke/MethodHandle;Lrtx/kimiko/api/events/Priority;I)V", "Lrtx/kimiko/api/events/Event;", "event", "", "invoke", "(Lrtx/kimiko/api/events/Event;)V", "Ljava/lang/Object;", "getOwner", "()Ljava/lang/Object;", "Ljava/lang/invoke/MethodHandle;", "Lrtx/kimiko/api/events/Priority;", "getPriority", "()Lrtx/kimiko/api/events/Priority;", "I", "getOrder", "()I", "rtx.kimiko:kimiko"})
    private static final class Binding {
        @NotNull
        private final Object owner;
        @NotNull
        private final MethodHandle handle;
        @NotNull
        private final Priority priority;
        private final int order;

        public Binding(@NotNull Object owner, @NotNull MethodHandle handle, @NotNull Priority priority, int order) {
            Intrinsics.checkNotNullParameter((Object)owner, (String)"owner");
            Intrinsics.checkNotNullParameter((Object)handle, (String)"handle");
            Intrinsics.checkNotNullParameter((Object)((Object)priority), (String)"priority");
            this.owner = owner;
            this.handle = handle;
            this.priority = priority;
            this.order = order;
        }

        @NotNull
        public final Object getOwner() {
            return this.owner;
        }

        @NotNull
        public final Priority getPriority() {
            return this.priority;
        }

        public final int getOrder() {
            return this.order;
        }

        public final void invoke(@NotNull Event event) {
            Intrinsics.checkNotNullParameter((Object)event, (String)"event");
            try {
                this.handle.invoke(event);
            }
            catch (Throwable throwable) {
                Companion.reportOnce(this.owner, event, throwable);
            }
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ'\u0010\u000e\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\u0012\u001a\u00020\u0010H\u0007b\u0002\b\u0011\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J!\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u00108\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R*\u0010\"\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b0!8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010%\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010&RT\u0010*\u001aB\u0012\f\u0012\n )*\u0004\u0018\u00010(0(\u0012\f\u0012\n )*\u0004\u0018\u00010\u00060\u0006 )* \u0012\f\u0012\n )*\u0004\u0018\u00010(0(\u0012\f\u0012\n )*\u0004\u0018\u00010\u00060\u0006\u0018\u00010'0'8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010,\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u0018\u0010/\u001a\u0004\u0018\u00010.8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b/\u00100\u00a8\u00061"}, d2={"Lrtx/kimiko/api/events/EventBus.Companion;", "", "<init>", "()V", "Lrtx/kimiko/api/events/CancellableEvent;", "event", "", "safeIsCancelled", "(Lrtx/kimiko/api/events/CancellableEvent;)Z", "owner", "Lrtx/kimiko/api/events/Event;", "", "throwable", "", "reportOnce", "(Ljava/lang/Object;Lrtx/kimiko/api/events/Event;Ljava/lang/Throwable;)V", "Lrtx/kimiko/api/events/EventBus;", "Lkotlin/jvm/JvmStatic;", "get", "()Lrtx/kimiko/api/events/EventBus;", "", "level", "Lrtx/kimiko/api/events/Priority;", "priorityOf", "(I)Lrtx/kimiko/api/events/Priority;", "Ljava/lang/Class;", "clazz", "", "Lrtx/kimiko/api/events/EventBus$HandlerSpec;", "scanHandlers", "(Ljava/lang/Class;)Ljava/util/List;", "INSTANCE", "Lrtx/kimiko/api/events/EventBus;", "Ljava/util/concurrent/ConcurrentHashMap;", "SPEC_CACHE", "Ljava/util/concurrent/ConcurrentHashMap;", "Lorg/slf4j/Logger;", "LOGGER", "Lorg/slf4j/Logger;", "Ljava/util/concurrent/ConcurrentHashMap$KeySetView;", "", "kotlin.jvm.PlatformType", "REPORTED", "Ljava/util/concurrent/ConcurrentHashMap$KeySetView;", "cancelledGetterBroken", "Z", "Ljava/lang/reflect/Field;", "cancelledField", "Ljava/lang/reflect/Field;", "rtx.kimiko:kimiko"})
    @SourceDebugExtension(value={"SMAP\nEventBus.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EventBus.kt\nrtx/kimiko/api/events/EventBus.Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,171:1\n1#2:172\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        private final boolean safeIsCancelled(CancellableEvent event) {
            if (!cancelledGetterBroken) {
                try {
                    return event.isCancelled();
                }
                catch (Throwable throwable) {
                    cancelledGetterBroken = true;
                }
            }
            try {
                Field field = cancelledField;
                if (field == null) {
                    field = CancellableEvent.class.getDeclaredField("cancelled");
                    field.setAccessible(true);
                    cancelledField = field;
                }
                return field.getBoolean(event);
            }
            catch (Throwable throwable) {
                return false;
            }
        }

        private final void reportOnce(Object owner, Event event, Throwable throwable) {
            String key = owner.getClass().getName() + "#" + event.getClass().getName() + "#" + throwable.getClass().getName();
            if (!REPORTED.add(key)) {
                return;
            }
            Object[] objectArray = new Object[]{owner.getClass().getSimpleName(), event.getClass().getSimpleName(), throwable};
            LOGGER.error("handler {} threw on {} (reported once)", objectArray);
        }

        @JvmStatic
        @NotNull
        public final EventBus get() {
            return INSTANCE;
        }

        private final Priority priorityOf(int level) {
            Object object;
            List list = (List)Priority.getEntries();
            boolean bl = 0 <= level ? level < list.size() : false;
            if (bl) {
                object = list.get(level);
            } else {
                int it = level;
                boolean bl2 = false;
                object = Priority.NORMAL;
            }
            return (Priority)((Object)object);
        }

        private final List<HandlerSpec> scanHandlers(Class<?> clazz) {
            ArrayList<HandlerSpec> specs = new ArrayList<HandlerSpec>();
            for (Class<?> current = clazz; current != null && current != Object.class; current = current.getSuperclass()) {
                Method[] methodArray = current.getDeclaredMethods();
                for (Method method : methodArray) {
                    EventHandler annotation = method.getAnnotation(EventHandler.class);
                    if (annotation == null || method.getParameterCount() != 1) continue;
                    Class<?> paramType = method.getParameterTypes()[0];
                    if (!Event.class.isAssignableFrom(paramType)) continue;
                    try {
                        method.setAccessible(true);
                        MethodHandle handle = MethodHandles.lookup().unreflect(method);
                        specs.add(new HandlerSpec(handle, paramType, this.priorityOf(annotation.value())));
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
            }
            return List.copyOf(specs);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0082\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0014\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u0004H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u000fJ2\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\f\b\u0002\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u00c6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u0017\u001a\u00020\u0016H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0011\u0010\u001a\u001a\u00020\u0019H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u000bR\u001b\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00048\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010\rR\u0017\u0010\u0007\u001a\u00020\u00068\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010 \u001a\u0004\b!\u0010\u000f\u00a8\u0006\""}, d2={"Lrtx/kimiko/api/events/EventBus$HandlerSpec;", "", "Ljava/lang/invoke/MethodHandle;", "handle", "Ljava/lang/Class;", "eventType", "Lrtx/kimiko/api/events/Priority;", "priority", "<init>", "(Ljava/lang/invoke/MethodHandle;Ljava/lang/Class;Lrtx/kimiko/api/events/Priority;)V", "component1", "()Ljava/lang/invoke/MethodHandle;", "component2", "()Ljava/lang/Class;", "component3", "()Lrtx/kimiko/api/events/Priority;", "copy", "(Ljava/lang/invoke/MethodHandle;Ljava/lang/Class;Lrtx/kimiko/api/events/Priority;)Lrtx/kimiko/api/events/EventBus$HandlerSpec;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Ljava/lang/invoke/MethodHandle;", "getHandle", "Ljava/lang/Class;", "getEventType", "Lrtx/kimiko/api/events/Priority;", "getPriority", "rtx.kimiko:kimiko"})
    private static final class HandlerSpec {
        @NotNull
        private final MethodHandle handle;
        @NotNull
        private final Class<?> eventType;
        @NotNull
        private final Priority priority;

        public HandlerSpec(@NotNull MethodHandle handle, @NotNull Class<?> eventType, @NotNull Priority priority) {
            Intrinsics.checkNotNullParameter((Object)handle, (String)"handle");
            Intrinsics.checkNotNullParameter(eventType, (String)"eventType");
            Intrinsics.checkNotNullParameter((Object)((Object)priority), (String)"priority");
            this.handle = handle;
            this.eventType = eventType;
            this.priority = priority;
        }

        @NotNull
        public final MethodHandle getHandle() {
            return this.handle;
        }

        @NotNull
        public final Class<?> getEventType() {
            return this.eventType;
        }

        @NotNull
        public final Priority getPriority() {
            return this.priority;
        }

        @NotNull
        public final MethodHandle component1() {
            return this.handle;
        }

        @NotNull
        public final Class<?> component2() {
            return this.eventType;
        }

        @NotNull
        public final Priority component3() {
            return this.priority;
        }

        @NotNull
        public final HandlerSpec copy(@NotNull MethodHandle handle, @NotNull Class<?> eventType, @NotNull Priority priority) {
            Intrinsics.checkNotNullParameter((Object)handle, (String)"handle");
            Intrinsics.checkNotNullParameter(eventType, (String)"eventType");
            Intrinsics.checkNotNullParameter((Object)((Object)priority), (String)"priority");
            return new HandlerSpec(handle, eventType, priority);
        }

        public static /* synthetic */ HandlerSpec copy$default(HandlerSpec handlerSpec, MethodHandle methodHandle, Class clazz, Priority priority, int n, Object object) {
            if ((n & 1) != 0) {
                methodHandle = handlerSpec.handle;
            }
            if ((n & 2) != 0) {
                clazz = handlerSpec.eventType;
            }
            if ((n & 4) != 0) {
                priority = handlerSpec.priority;
            }
            return handlerSpec.copy(methodHandle, clazz, priority);
        }

        @NotNull
        public String toString() {
            return "HandlerSpec(handle=" + this.handle + ", eventType=" + this.eventType + ", priority=" + this.priority + ")";
        }

        public int hashCode() {
            int result = this.handle.hashCode();
            result = result * 31 + this.eventType.hashCode();
            result = result * 31 + this.priority.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HandlerSpec)) {
                return false;
            }
            HandlerSpec handlerSpec = (HandlerSpec)other;
            if (!Intrinsics.areEqual((Object)this.handle, (Object)handlerSpec.handle)) {
                return false;
            }
            if (!Intrinsics.areEqual(this.eventType, handlerSpec.eventType)) {
                return false;
            }
            return this.priority == handlerSpec.priority;
        }
    }
}

