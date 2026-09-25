/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.opus4j.OpusEncoder$Application
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Visuals.portallive;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.SourceDataLine;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mods.voicechat.api.opus.OpusDecoder;
import mods.voicechat.api.opus.OpusEncoder;
import mods.voicechat.natives.Agc;
import mods.voicechat.natives.Denoiser;
import mods.voicechat.plugins.impl.opus.JavaOpusDecoderImpl;
import mods.voicechat.plugins.impl.opus.JavaOpusEncoderImpl;
import mods.voicechat.plugins.impl.opus.NativeOpusDecoderImpl;
import mods.voicechat.plugins.impl.opus.NativeOpusEncoderImpl;
import mods.voicechat.voice.common.AudioUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.party.voice.MicCapture;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0017\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 92\u00020\u0001:\u0002:9B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\u00072\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0007\u00a2\u0006\u0004\b\n\u0010\u0003J\r\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000b\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0017\u001a\u00020\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0019H\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u0003R\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010!\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010#\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010\"R\u0016\u0010$\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010&\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010%R\u001e\u0010'\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0018\u0010*\u001a\u0004\u0018\u00010)8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0018\u0010-\u001a\u0004\u0018\u00010,8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u0018\u00100\u001a\u0004\u0018\u00010/8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0016\u00102\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u0010\"R\u0016\u00104\u001a\u0002038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0018\u00107\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b7\u00108\u00a8\u0006;"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveVoice;", "Lrtx/kimiko/api/party/voice/MicCapture$Listener;", "<init>", "()V", "Ljava/util/function/Consumer;", "", "voiceSink", "", "start", "(Ljava/util/function/Consumer;)V", "stop", "", "isRunning", "()Z", "value", "setActive", "(Z)V", "", "left", "right", "setSpatial", "(FF)V", "opus", "onVoicePayload", "([B)V", "", "samples", "onMicFrame", "([S)V", "closeAudio", "", "audioLock", "Ljava/lang/Object;", "running", "Z", "active", "gainLeft", "F", "gainRight", "sink", "Ljava/util/function/Consumer;", "Lmods/voicechat/api/opus/OpusEncoder;", "encoder", "Lmods/voicechat/api/opus/OpusEncoder;", "Lmods/voicechat/natives/Denoiser;", "denoiser", "Lmods/voicechat/natives/Denoiser;", "Lmods/voicechat/natives/Agc;", "agc", "Lmods/voicechat/natives/Agc;", "wasTransmitting", "", "vadLastVoiceMs", "J", "Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveVoice$PortalSpeaker;", "speaker", "Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveVoice$PortalSpeaker;", "Companion", "PortalSpeaker", "rtx.kimiko:kimiko"})
public final class PortalLiveVoice
implements MicCapture.Listener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Object audioLock = new Object();
    private volatile boolean running;
    private volatile boolean active;
    private volatile float gainLeft = 0.5f;
    private volatile float gainRight = 0.5f;
    @Nullable
    private volatile Consumer<byte[]> sink;
    @Nullable
    private OpusEncoder encoder;
    @Nullable
    private Denoiser denoiser;
    @Nullable
    private Agc agc;
    private boolean wasTransmitting;
    private long vadLastVoiceMs;
    @Nullable
    private volatile PortalSpeaker speaker;
    private static final long VAD_HANGOVER_MS = 300L;
    private static final float VAD_SENSITIVITY = 0.5f;
    private static final int AGC_TARGET = AudioUtils.dbSample(-5.0);
    private static final float AGC_SPEECH_PROBABILITY = 0.95f;
    private static final int AGC_INCREMENT = 12;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final synchronized void start(@Nullable Consumer<byte[]> voiceSink) {
        if (this.running) {
            return;
        }
        this.sink = voiceSink;
        Object object = this.audioLock;
        synchronized (object) {
            boolean bl = false;
            this.encoder = PortalLiveVoice.Companion.createEncoder();
            this.denoiser = PortalLiveVoice.Companion.createDenoiser();
            this.agc = PortalLiveVoice.Companion.createAgc();
            this.wasTransmitting = false;
            Unit unit = Unit.INSTANCE;
        }
        this.running = true;
        MicCapture.subscribe(this);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final synchronized void stop() {
        block4: {
            if (!this.running) {
                return;
            }
            this.running = false;
            this.active = false;
            this.sink = null;
            MicCapture.unsubscribe(this);
            Object object = this.audioLock;
            synchronized (object) {
                boolean bl = false;
                this.closeAudio();
                Unit unit = Unit.INSTANCE;
            }
            PortalSpeaker current = this.speaker;
            this.speaker = null;
            PortalSpeaker portalSpeaker = current;
            if (portalSpeaker == null) break block4;
            portalSpeaker.close();
        }
    }

    public final boolean isRunning() {
        return this.running;
    }

    public final void setActive(boolean value) {
        if (this.active && !value) {
            PortalSpeaker current;
            PortalSpeaker portalSpeaker = current = this.speaker;
            if (portalSpeaker != null) {
                portalSpeaker.clear();
            }
        }
        this.active = value;
    }

    public final void setSpatial(float left, float right) {
        this.gainLeft = Math.max(0.0f, Math.min(1.0f, left));
        this.gainRight = Math.max(0.0f, Math.min(1.0f, right));
    }

    public final void onVoicePayload(@Nullable byte[] opus) {
        if (!this.running || !this.active || opus == null || opus.length == 0) {
            return;
        }
        PortalSpeaker current = this.speaker;
        if (current == null || current.isClosed()) {
            this.speaker = current = new PortalSpeaker(this);
        }
        current.enqueue(opus);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void onMicFrame(@NotNull short[] samples) {
        Intrinsics.checkNotNullParameter((Object)samples, (String)"samples");
        if (!this.running) {
            return;
        }
        Object object = this.audioLock;
        synchronized (object) {
            Consumer<byte[]> out;
            OpusEncoder enc;
            block24: {
                block23: {
                    boolean bl = false;
                    enc = this.encoder;
                    out = this.sink;
                    if (!this.running || enc == null) break block23;
                    if (out != null) break block24;
                }
                return;
            }
            try {
                boolean shouldSend;
                Agc currentAgc;
                float speechProb = 1.0f;
                Denoiser den = this.denoiser;
                if (den != null) {
                    try {
                        speechProb = den.denoiseInPlace(samples);
                    }
                    catch (Throwable throwable) {
                        // empty catch block
                    }
                }
                if ((currentAgc = this.agc) != null) {
                    try {
                        currentAgc.setIncrement(speechProb >= 0.95f ? 12 : 0);
                        currentAgc.agc(samples);
                    }
                    catch (Throwable throwable) {
                        // empty catch block
                    }
                }
                boolean voiced = den != null ? speechProb >= 0.5f : AudioUtils.getHighestAudioLevel(samples) >= -35.0;
                long now = System.currentTimeMillis();
                if (voiced) {
                    this.vadLastVoiceMs = now;
                }
                boolean bl = shouldSend = this.active && now - this.vadLastVoiceMs <= 300L;
                if (!shouldSend) {
                    if (this.wasTransmitting) {
                        try {
                            enc.resetState();
                        }
                        catch (Throwable throwable) {
                            // empty catch block
                        }
                        this.wasTransmitting = false;
                    }
                    return;
                }
                this.wasTransmitting = true;
                byte[] opus = enc.encode(samples);
                if (opus != null && !(opus.length == 0)) {
                    out.accept(opus);
                }
            }
            catch (Throwable throwable) {
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void closeAudio() {
        if (this.encoder != null) {
            try {
                this.encoder.close();
            } catch (Throwable ignored) {
            }
            this.encoder = null;
        }
        if (this.denoiser != null) {
            try {
                this.denoiser.close();
            } catch (Throwable ignored) {
            }
            this.denoiser = null;
        }
        if (this.agc != null) {
            try {
                this.agc.close();
            } catch (Throwable ignored) {
            }
            this.agc = null;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u0011\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0012R\u0014\u0010\u0017\u001a\u00020\u00138\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0015\u00a8\u0006\u0018"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveVoice.Companion;", "", "<init>", "()V", "Lmods/voicechat/api/opus/OpusEncoder;", "createEncoder", "()Lmods/voicechat/api/opus/OpusEncoder;", "Lmods/voicechat/natives/Denoiser;", "createDenoiser", "()Lmods/voicechat/natives/Denoiser;", "Lmods/voicechat/natives/Agc;", "createAgc", "()Lmods/voicechat/natives/Agc;", "", "VAD_HANGOVER_MS", "J", "", "VAD_SENSITIVITY", "F", "", "AGC_TARGET", "I", "AGC_SPEECH_PROBABILITY", "AGC_INCREMENT", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final OpusEncoder createEncoder() {
            OpusEncoder opusEncoder;
            try {
                NativeOpusEncoderImpl enc = new NativeOpusEncoderImpl(48000, 1, de.maxhenkel.opus4j.OpusEncoder.Application.VOIP);
                enc.setMaxPayloadSize(1275);
                opusEncoder = enc;
            }
            catch (Throwable t) {
                opusEncoder = new JavaOpusEncoderImpl(48000, 960, 1275, de.maxhenkel.opus4j.OpusEncoder.Application.VOIP);
            }
            return opusEncoder;
        }

        private final Denoiser createDenoiser() {
            Denoiser denoiser;
            try {
                denoiser = new Denoiser();
            }
            catch (Throwable t) {
                denoiser = null;
            }
            return denoiser;
        }

        private final Agc createAgc() {
            try {
                Agc created = new Agc(960, 48000);
                created.setTarget(AGC_TARGET);
                return created;
            }
            catch (Throwable t) {
                return null;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\b\u0002\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u000b\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u000fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0012R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\u00138\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0019\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001c\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001d\u00a8\u0006\u001f"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveVoice$PortalSpeaker;", "", "Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveVoice;", "owner", "<init>", "(Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveVoice;)V", "", "isClosed", "()Z", "", "opus", "", "enqueue", "([B)V", "clear", "()V", "close", "loop", "Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveVoice;", "Ljava/util/concurrent/BlockingQueue;", "jitter", "Ljava/util/concurrent/BlockingQueue;", "Ljava/lang/Thread;", "thread", "Ljava/lang/Thread;", "closed", "Z", "", "lastAudio", "J", "Companion", "rtx.kimiko:kimiko"})
    private static final class PortalSpeaker {
        @NotNull
        public static final Companion Companion = new Companion(null);
        @NotNull
        private final PortalLiveVoice owner;
        @NotNull
        private final BlockingQueue<byte[]> jitter;
        @NotNull
        private final Thread thread;
        private volatile boolean closed;
        private volatile long lastAudio;
        private static final int PREBUFFER_FRAMES = 3;
        private static final int MAX_DEPTH_FRAMES = 20;
        private static final int MAX_PLC_FRAMES = 5;
        private static final int LINE_BUFFER_FRAMES = 8;
        private static final long IDLE_CLOSE_MS = 12000L;
        private static final int QUEUE_CAP = 64;

        public PortalSpeaker(@NotNull PortalLiveVoice owner) {
            Intrinsics.checkNotNullParameter((Object)owner, (String)"owner");
            this.owner = owner;
            this.jitter = new LinkedBlockingQueue(64);
            this.lastAudio = System.currentTimeMillis();
            this.thread = new Thread(() -> PortalSpeaker._init_$lambda$0(this), "kimiko-portal-voice");
            this.thread.setDaemon(true);
            this.thread.start();
        }

        public final boolean isClosed() {
            return this.closed;
        }

        public final void enqueue(@NotNull byte[] opus) {
            Intrinsics.checkNotNullParameter((Object)opus, (String)"opus");
            if (this.closed) {
                return;
            }
            this.lastAudio = System.currentTimeMillis();
            while (this.jitter.size() >= 20) {
                this.jitter.poll();
            }
            this.jitter.offer(opus);
        }

        public final void clear() {
            this.jitter.clear();
        }

        public final void close() {
            this.closed = true;
            this.thread.interrupt();
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         * Loose catch block
         */
        private final void loop() {
            OpusDecoder decoder = null;
            SourceDataLine line = null;
            try {
                decoder = PortalSpeaker.Companion.createDecoder();
                AudioFormat mono = MicCapture.FORMAT;
                AudioFormat stereo = new AudioFormat(mono.getSampleRate(), 16, 2, true, mono.isBigEndian());
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, stereo);
                Line line2 = AudioSystem.getLine(info);
                Intrinsics.checkNotNull((Object)line2, "null cannot be cast to non-null type javax.sound.sampled.SourceDataLine");
                line = (SourceDataLine)line2;
                line.open(stereo, MicCapture.FRAME_BYTES * 2 * 8);
                line.start();
                byte[] stereoBytes = new byte[0];
                boolean buffering = true;
                int plc = 0;
                while (!this.closed) {
                    if (buffering) {
                        if (this.jitter.size() >= 3) {
                            buffering = false;
                            plc = 0;
                        } else {
                            if (this.jitter.isEmpty() && System.currentTimeMillis() - this.lastAudio > 12000L) break;
                            Thread.sleep(8L);
                            continue;
                        }
                    }
                    byte[] opus = (byte[])this.jitter.poll();
                    short[] pcm = null;
                    if (opus != null) {
                        pcm = decoder.decode(opus);
                        plc = 0;
                    } else if (plc < 5) {
                        pcm = PortalSpeaker.Companion.conceal(decoder);
                        ++plc;
                    } else {
                        buffering = true;
                        continue;
                    }
                    if (pcm == null || pcm.length == 0) continue;
                    float left = this.owner.gainLeft;
                    float right = this.owner.gainRight;
                    if (stereoBytes.length != pcm.length * 4) {
                        stereoBytes = new byte[pcm.length * 4];
                    }
                    int n = pcm.length;
                    for (int i = 0; i < n; ++i) {
                        short l = PortalSpeaker.Companion.clampSample((float)pcm[i] * left);
                        short r = PortalSpeaker.Companion.clampSample((float)pcm[i] * right);
                        int base = i * 4;
                        stereoBytes[base] = (byte)l;
                        stereoBytes[base + 1] = (byte)(l >> 8);
                        stereoBytes[base + 2] = (byte)r;
                        stereoBytes[base + 3] = (byte)(r >> 8);
                    }
                    line.write(stereoBytes, 0, stereoBytes.length);
                }
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            } catch (Throwable ignored) {
            } finally {
                this.closed = true;
                if (line != null) {
                    try {
                        line.stop();
                        line.flush();
                        line.close();
                    } catch (Throwable ignored) {}
                }
                if (decoder != null) {
                    try {
                        decoder.close();
                    } catch (Throwable ignored) {}
                }
            }
        }

        private static final void _init_$lambda$0(PortalSpeaker this$0) {
            this$0.loop();
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0017\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0012R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00108\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0012\u00a8\u0006\u001a"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveVoice$PortalSpeaker.Companion;", "", "<init>", "()V", "", "value", "", "clampSample", "(F)S", "Lmods/voicechat/api/opus/OpusDecoder;", "decoder", "", "conceal", "(Lmods/voicechat/api/opus/OpusDecoder;)[S", "createDecoder", "()Lmods/voicechat/api/opus/OpusDecoder;", "", "PREBUFFER_FRAMES", "I", "MAX_DEPTH_FRAMES", "MAX_PLC_FRAMES", "LINE_BUFFER_FRAMES", "", "IDLE_CLOSE_MS", "J", "QUEUE_CAP", "rtx.kimiko:kimiko"})
        public static final class Companion {
            private Companion() {
            }

            private final short clampSample(float value) {
                if (value > 32767.0f) {
                    return Short.MAX_VALUE;
                }
                if (value < -32768.0f) {
                    return Short.MIN_VALUE;
                }
                return (short)value;
            }

            private final short[] conceal(OpusDecoder decoder) {
                try {
                    short[] result = decoder.decode(null);
                    if (result != null && !(result.length == 0)) {
                        return result;
                    }
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
                return new short[MicCapture.FRAME_SAMPLES];
            }

            private final OpusDecoder createDecoder() {
                try {
                    NativeOpusDecoderImpl dec = new NativeOpusDecoderImpl(48000, 1);
                    dec.setFrameSize(960);
                    return dec;
                }
                catch (Throwable t) {
                    return new JavaOpusDecoderImpl(48000, 960);
                }
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }
}

