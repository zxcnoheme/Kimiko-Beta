/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.party.voice;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.SourceDataLine;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mods.voicechat.api.opus.OpusDecoder;
import mods.voicechat.plugins.impl.opus.JavaOpusDecoderImpl;
import mods.voicechat.plugins.impl.opus.NativeOpusDecoderImpl;
import mods.voicechat.voice.common.AudioUtils;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.party.voice.PartyVoice;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0006J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\r\u00a2\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0013R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0015R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\u00168\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001c\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010\b\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010\u001e\u00a8\u0006 "}, d2={"Lrtx/kimiko/api/party/voice/VoiceSpeaker;", "", "", "name", "<init>", "(Ljava/lang/String;)V", "()Ljava/lang/String;", "", "lastAudio", "()J", "", "isClosed", "()Z", "", "opus", "", "enqueue", "([B)V", "close", "()V", "loop", "Ljava/lang/String;", "Ljava/util/concurrent/BlockingQueue;", "jitter", "Ljava/util/concurrent/BlockingQueue;", "Ljava/lang/Thread;", "thread", "Ljava/lang/Thread;", "closed", "Z", "J", "Companion", "rtx.kimiko:kimiko"})
public final class VoiceSpeaker {
    @NotNull
    private static final Companion Companion = new Companion(null);
    @NotNull
    private final String name;
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

    public VoiceSpeaker(@NotNull String name) {
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        this.name = name;
        this.jitter = new LinkedBlockingQueue(64);
        this.lastAudio = System.currentTimeMillis();
        this.thread = new Thread(() -> VoiceSpeaker._init_$lambda$0(this), "kimiko-voice-spk");
        this.thread.setDaemon(true);
        this.thread.start();
    }

    @NotNull
    public final String name() {
        return this.name;
    }

    public final long lastAudio() {
        return this.lastAudio;
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
            decoder = VoiceSpeaker.Companion.createDecoder();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, PartyVoice.FORMAT);
            Line line2 = AudioSystem.getLine(info);
            Intrinsics.checkNotNull((Object)line2, "null cannot be cast to non-null type javax.sound.sampled.SourceDataLine");
            line = (SourceDataLine)line2;
            line.open(PartyVoice.FORMAT, PartyVoice.FRAME_BYTES * 8);
            line.start();
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
                    pcm = VoiceSpeaker.Companion.conceal(decoder);
                    ++plc;
                } else {
                    buffering = true;
                    continue;
                }
                if (pcm == null || pcm.length == 0) continue;
                PartyVoice.Companion.applyGain$rtx_kimiko_kimiko(pcm, PartyVoice.INSTANCE.outputGain$rtx_kimiko_kimiko());
                byte[] bytes = AudioUtils.shortsToBytes(pcm);
                line.write(bytes, 0, bytes.length);
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

    private static final void _init_$lambda$0(VoiceSpeaker this$0) {
        this$0.loop();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0017\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\rR\u0014\u0010\u0010\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\rR\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\r\u00a8\u0006\u0015"}, d2={"Lrtx/kimiko/api/party/voice/VoiceSpeaker.Companion;", "", "<init>", "()V", "Lmods/voicechat/api/opus/OpusDecoder;", "decoder", "", "conceal", "(Lmods/voicechat/api/opus/OpusDecoder;)[S", "createDecoder", "()Lmods/voicechat/api/opus/OpusDecoder;", "", "PREBUFFER_FRAMES", "I", "MAX_DEPTH_FRAMES", "MAX_PLC_FRAMES", "LINE_BUFFER_FRAMES", "", "IDLE_CLOSE_MS", "J", "QUEUE_CAP", "rtx.kimiko:kimiko"})
    private static final class Companion {
        private Companion() {
        }

        private final short[] conceal(OpusDecoder decoder) {
            try {
                short[] r = decoder.decode(null);
                if (r != null && !(r.length == 0)) {
                    return r;
                }
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            return new short[PartyVoice.FRAME_SAMPLES];
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

