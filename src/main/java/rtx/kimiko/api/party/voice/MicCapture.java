/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.party.voice;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import mods.voicechat.voice.common.AudioUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.lang.I18n;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002-.B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\t\u001a\u00020\u0005H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\t\u0010\nJ\u001d\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0016\u001a\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u0018\u001a\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0018\u0010\u0017J\u0013\u0010\u001a\u001a\u00020\u0019H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001c\u001a\u0004\u0018\u00010\u0005H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u001c\u0010\nR\u0019\u0010\u001f\u001a\u00020\u001d8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0019\u0010\"\u001a\u00020!8\u0006X\u0087D\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0019\u0010$\u001a\u00020!8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001e\u00a2\u0006\u0006\n\u0004\b$\u0010#R\u0014\u0010%\u001a\u00020\u00058\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00140'8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u0010&R\u0016\u0010\t\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\t\u0010&R\u0018\u0010+\u001a\u0004\u0018\u00010*8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u0010,\u00a8\u0006/"}, d2={"Lrtx/kimiko/api/party/voice/MicCapture;", "", "<init>", "()V", "", "", "Lkotlin/jvm/JvmStatic;", "devices", "()Ljava/util/List;", "device", "()Ljava/lang/String;", "next", "", "setDevice", "(Ljava/lang/String;)V", "Ljavax/sound/sampled/DataLine$Info;", "info", "Ljavax/sound/sampled/TargetDataLine;", "openLine", "(Ljavax/sound/sampled/DataLine$Info;)Ljavax/sound/sampled/TargetDataLine;", "Lrtx/kimiko/api/party/voice/MicCapture$Listener;", "listener", "subscribe", "(Lrtx/kimiko/api/party/voice/MicCapture$Listener;)V", "unsubscribe", "", "isCapturing", "()Z", "lastError", "Ljavax/sound/sampled/AudioFormat;", "Lkotlin/jvm/JvmField;", "FORMAT", "Ljavax/sound/sampled/AudioFormat;", "", "FRAME_SAMPLES", "I", "FRAME_BYTES", "DEFAULT_DEVICE", "Ljava/lang/String;", "Ljava/util/concurrent/CopyOnWriteArrayList;", "LISTENERS", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lrtx/kimiko/api/party/voice/MicCapture$Session;", "current", "Lrtx/kimiko/api/party/voice/MicCapture$Session;", "Listener", "Session", "rtx.kimiko:kimiko"})
public final class MicCapture {
    @NotNull
    public static final MicCapture INSTANCE = new MicCapture();
    @JvmField
    @NotNull
    public static final AudioFormat FORMAT = new AudioFormat(48000.0f, 16, 1, true, false);
    @JvmField
    public static final int FRAME_SAMPLES = 960;
    @JvmField
    public static final int FRAME_BYTES = FRAME_SAMPLES * 2;
    @NotNull
    public static final String DEFAULT_DEVICE = "Системный";
    @NotNull
    private static final CopyOnWriteArrayList<Listener> LISTENERS = new CopyOnWriteArrayList();
    @Nullable
    private static volatile String lastError;
    @NotNull
    private static volatile String device;
    @Nullable
    private static Session current;

    private MicCapture() {
    }

    @JvmStatic
    @NotNull
    public static final List<String> devices() {
        return Collections.singletonList("Disabled (privacy build)");
    }

    @JvmStatic
    @NotNull
    public static final String device() {
        return device;
    }

    @JvmStatic
    public static final synchronized void setDevice(@Nullable String next) {
        String resolved;
        String string = resolved = next == null || StringsKt.isBlank((CharSequence)next) ? DEFAULT_DEVICE : next;
        if (Intrinsics.areEqual((Object)resolved, (Object)device)) {
            return;
        }
        device = resolved;
        Session session = current;
        if (session == null) {
            return;
        }
        Session session2 = session;
        session2.stop();
        current = null;
        if (!((Collection)LISTENERS).isEmpty()) {
            lastError = null;
            current = new Session();
        }
    }

    private final TargetDataLine openLine(DataLine.Info info) {
        throw new UnsupportedOperationException("Hardware fingerprinting or microphone access disabled in privacy build");
    }

    @JvmStatic
    public static final synchronized void subscribe(@Nullable Listener listener) {
    }

    @JvmStatic
    public static final synchronized void unsubscribe(@Nullable Listener listener) {
        if (listener == null) {
            return;
        }
        LISTENERS.remove(listener);
        Session session = current;
        if (LISTENERS.isEmpty() && session != null) {
            session.stop();
            current = null;
        }
    }

    @JvmStatic
    public static final synchronized boolean isCapturing() {
        Session session = current;
        return session != null && session.isAlive();
    }

    @JvmStatic
    @Nullable
    public static final String lastError() {
        return lastError;
    }

    static {
        device = DEFAULT_DEVICE;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0017\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006\u0007\u00c0\u0006\u0001"}, d2={"Lrtx/kimiko/api/party/voice/MicCapture$Listener;", "", "", "samples", "", "onMicFrame", "([S)V", "rtx.kimiko:kimiko"})
    public static interface Listener {
        public void onMicFrame(@NotNull short[] var1);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003J\r\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\t\u0010\u0003R\u0016\u0010\n\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2={"Lrtx/kimiko/api/party/voice/MicCapture$Session;", "Ljava/lang/Runnable;", "<init>", "()V", "", "stop", "", "isAlive", "()Z", "run", "stopped", "Z", "Ljava/lang/Thread;", "thread", "Ljava/lang/Thread;", "rtx.kimiko:kimiko"})
    private static final class Session
    implements Runnable {
        private volatile boolean stopped;
        @NotNull
        private final Thread thread = new Thread((Runnable)this, "kimiko-mic-capture");

        public Session() {
            this.thread.setDaemon(true);
            this.thread.start();
        }

        public final void stop() {
            this.stopped = true;
            this.thread.interrupt();
        }

        public final boolean isAlive() {
            return !this.stopped && this.thread.isAlive();
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void run() {
            DataLine.Info info;
            TargetDataLine line;
            block33: {
                line = null;
                info = new DataLine.Info(TargetDataLine.class, FORMAT);
                if (AudioSystem.isLineSupported(info)) break block33;
                lastError = I18n.tr("Микрофон не поддерживает 48kHz моно");
                Class<MicCapture> clazz = MicCapture.class;
                synchronized (clazz) {
                    if (current == this) {
                        current = null;
                    }
                }
                return;
            }
            try {
                line = INSTANCE.openLine(info);
                line.open(FORMAT, FRAME_BYTES * 4);
                line.start();
                byte[] buf = new byte[FRAME_BYTES];
                while (!this.stopped) {
                    Iterator iterator;
                    int read;
                    int r;
                    for (read = 0; read < FRAME_BYTES && !this.stopped && (r = line.read(buf, read, FRAME_BYTES - read)) > 0; read += r) {
                    }
                    if (this.stopped) {
                        break;
                    }
                    if (read < FRAME_BYTES) continue;
                    short[] samples = AudioUtils.bytesToShorts(buf);
                    Iterator<Listener> it = LISTENERS.iterator();
                    while (it.hasNext()) {
                        Listener listener = it.next();
                        try {
                            listener.onMicFrame((short[])samples.clone());
                        }
                        catch (Throwable throwable) {}
                    }
                }
            }
            catch (Throwable t) {
                lastError = String.valueOf(t.getMessage());
            }
            finally {
                if (line != null) {
                    try {
                        line.stop();
                        line.close();
                    }
                    catch (Throwable info2) {}
                }
                Class<MicCapture> clazz = MicCapture.class;
                synchronized (clazz) {
                    if (current == this) {
                        current = null;
                    }
                }
            }
        }
    }
}

