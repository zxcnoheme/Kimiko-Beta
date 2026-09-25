/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.chat.voice;

import java.util.List;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.SourceDataLine;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import mods.voicechat.api.opus.OpusDecoder;
import mods.voicechat.plugins.impl.opus.JavaOpusDecoderImpl;
import mods.voicechat.plugins.impl.opus.NativeOpusDecoderImpl;
import mods.voicechat.voice.common.AudioUtils;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.chat.voice.VoiceNote;
import rtx.kimiko.api.chat.voice.VoiceNoteApi;
import rtx.kimiko.api.chat.voice.VoiceNoteRecorder;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.notifications.Notifications;
import rtx.kimiko.api.party.voice.MicCapture;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001'B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u001b\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\r\u001a\u00020\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u000fH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0012\u0010\u0011J\u0013\u0010\u0013\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0015\u0010\u0003J%\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u000f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u000fH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0018\u0010\u0019J%\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u000f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u000fH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u001a\u0010\u0019J\u0017\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0018\u0010\"\u001a\u0004\u0018\u00010!8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010$\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010\r\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\r\u0010&\u00a8\u0006("}, d2={"Lrtx/kimiko/api/chat/voice/VoiceNotePlayer;", "", "<init>", "()V", "", "value", "", "Lkotlin/jvm/JvmStatic;", "setVolume", "(F)V", "", "setAutoPlay", "(Z)V", "autoPlay", "()Z", "", "playingId", "()Ljava/lang/String;", "speaker", "progress", "()F", "stop", "id", "from", "toggle", "(Ljava/lang/String;Ljava/lang/String;)V", "play", "message", "notice", "(Ljava/lang/String;)V", "", "LINE_BUFFER_FRAMES", "I", "Lrtx/kimiko/api/chat/voice/VoiceNotePlayer$Session;", "session", "Lrtx/kimiko/api/chat/voice/VoiceNotePlayer$Session;", "volume", "F", "Z", "Session", "rtx.kimiko:kimiko"})
public final class VoiceNotePlayer {
    @NotNull
    public static final VoiceNotePlayer INSTANCE = new VoiceNotePlayer();
    private static final int LINE_BUFFER_FRAMES = 16;
    @Nullable
    private static volatile Session session;
    private static volatile float volume;
    private static volatile boolean autoPlay;

    private VoiceNotePlayer() {
    }

    @JvmStatic
    public static final void setVolume(float value) {
        volume = Math.max(0.0f, value);
    }

    @JvmStatic
    public static final void setAutoPlay(boolean value) {
        autoPlay = value;
    }

    @JvmStatic
    public static final boolean autoPlay() {
        return autoPlay;
    }

    @JvmStatic
    @Nullable
    public static final String playingId() {
        Session current = session;
        return current != null && current.isAlive() ? current.getId() : null;
    }

    @JvmStatic
    @Nullable
    public static final String speaker() {
        Session current = session;
        return current != null && current.isAlive() ? current.getFrom() : null;
    }

    @JvmStatic
    public static final float progress() {
        Session session = VoiceNotePlayer.session;
        if (session == null) {
            return 0.0f;
        }
        Session current = session;
        return current.progress();
    }

    @JvmStatic
    public static final synchronized void stop() {
        block0: {
            Session current = session;
            session = null;
            Session session = current;
            if (session == null) break block0;
            session.stop();
        }
    }

    @JvmStatic
    public static final synchronized void toggle(@NotNull String id, @Nullable String from) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        if (Intrinsics.areEqual((Object)VoiceNotePlayer.playingId(), (Object)id)) {
            VoiceNotePlayer.stop();
            return;
        }
        VoiceNotePlayer.play(id, from);
    }

    @JvmStatic
    public static final synchronized void play(@NotNull String id, @Nullable String from) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        if (!VoiceNote.isId(id)) {
            return;
        }
        if (VoiceNoteRecorder.isRecording()) {
            return;
        }
        VoiceNotePlayer.stop();
        String string = from;
        if (string == null) {
            string = "";
        }
        session = new Session(id, string);
    }

    private final void notice(String message) {
        MinecraftClient.getInstance().execute(() -> VoiceNotePlayer.notice$lambda$0(message));
    }

    private static final void notice$lambda$0(String $message) {
        Notifications.push(I18n.tr("Голосовое"), $message, 2000L);
    }

    static {
        volume = 1.0f;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\r\u0010\u000e\u001a\u00020\r\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u0010\u0010\fJ\u000f\u0010\u0012\u001a\u00020\u0011H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0003\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0004\u001a\u00020\u00028\u0006\u00a2\u0006\f\n\u0004\b\u0004\u0010\u0014\u001a\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001e\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b \u0010\u001f\u00a8\u0006!"}, d2={"Lrtx/kimiko/api/chat/voice/VoiceNotePlayer$Session;", "Ljava/lang/Runnable;", "", "id", "from", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "", "isAlive", "()Z", "", "stop", "()V", "", "progress", "()F", "run", "Lmods/voicechat/api/opus/OpusDecoder;", "createDecoder", "()Lmods/voicechat/api/opus/OpusDecoder;", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getFrom", "Ljava/lang/Thread;", "thread", "Ljava/lang/Thread;", "stopped", "Z", "", "total", "I", "played", "rtx.kimiko:kimiko"})
    private static final class Session
    implements Runnable {
        @NotNull
        private final String id;
        @NotNull
        private final String from;
        @NotNull
        private final Thread thread;
        private volatile boolean stopped;
        private volatile int total;
        private volatile int played;

        public Session(@NotNull String id, @NotNull String from) {
            Intrinsics.checkNotNullParameter((Object)id, (String)"id");
            Intrinsics.checkNotNullParameter((Object)from, (String)"from");
            this.id = id;
            this.from = from;
            this.thread = new Thread((Runnable)this, "kimiko-voice-note-play");
            this.thread.setDaemon(true);
            this.thread.start();
        }

        @NotNull
        public final String getId() {
            return this.id;
        }

        @NotNull
        public final String getFrom() {
            return this.from;
        }

        public final boolean isAlive() {
            return !this.stopped && this.thread.isAlive();
        }

        public final void stop() {
            this.stopped = true;
            this.thread.interrupt();
        }

        public final float progress() {
            int count = this.total;
            if (count <= 0) {
                return 0.0f;
            }
            return Math.min(1.0f, (float)this.played / (float)count);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         * Loose catch block
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public void run() {
            SourceDataLine line = null;
            OpusDecoder decoder = null;
            try {
                byte[] blob = VoiceNoteApi.download(this.id);
                if (blob == null) {
                    INSTANCE.notice(I18n.tr("Голосовое недоступно"));
                    return;
                }
                List<byte[]> frames = VoiceNote.unpack(blob);
                if (frames.isEmpty()) {
                    INSTANCE.notice(I18n.tr("Голосовое повреждено"));
                    return;
                }
                this.total = frames.size();
                decoder = this.createDecoder();
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, MicCapture.FORMAT);
                Line line2 = AudioSystem.getLine(info);
                Intrinsics.checkNotNull((Object)line2, "null cannot be cast to non-null type javax.sound.sampled.SourceDataLine");
                line = (SourceDataLine)line2;
                line.open(MicCapture.FORMAT, MicCapture.FRAME_BYTES * 16);
                line.start();
                for (byte[] frame : frames) {
                    if (this.stopped) break;
                    short[] pcm = decoder.decode(frame);
                    this.played++;
                    if (pcm == null || pcm.length == 0) continue;
                    VoiceNote.applyGain(pcm, volume);
                    byte[] bytes = AudioUtils.shortsToBytes(pcm);
                    line.write(bytes, 0, bytes.length);
                }
                if (!this.stopped) {
                    line.drain();
                }
            } catch (Throwable ignored) {
            } finally {
                this.stopped = true;
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
                synchronized (INSTANCE) {
                    if (session == this) {
                        VoiceNotePlayer.session = null;
                    }
                }
            }
        }

        private final OpusDecoder createDecoder() {
            try {
                NativeOpusDecoderImpl dec = new NativeOpusDecoderImpl(48000, 1);
                dec.setFrameSize(960);
                return dec;
            } catch (Throwable t) {
                return new JavaOpusDecoderImpl(48000, 960);
            }
        }
    }
}

