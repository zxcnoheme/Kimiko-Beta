/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.opus4j.OpusEncoder$Application
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.party.voice;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.sound.sampled.AudioFormat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mods.voicechat.api.opus.OpusEncoder;
import mods.voicechat.natives.Agc;
import mods.voicechat.natives.Denoiser;
import mods.voicechat.plugins.impl.opus.JavaOpusEncoderImpl;
import mods.voicechat.plugins.impl.opus.NativeOpusEncoderImpl;
import mods.voicechat.voice.common.AudioUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.party.PartyClient;
import rtx.kimiko.api.party.voice.MicCapture;
import rtx.kimiko.api.party.voice.VoiceSpeaker;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0017\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\u0017\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u0000 k2\u00020\u0001:\u0002lkB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003J\r\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0003J\u000f\u0010\u0007\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\u0003J\r\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\u000b\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\b\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\b\u00a2\u0006\u0004\b\u0018\u0010\nJ\u0015\u0010\u0019\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\b\u00a2\u0006\u0004\b\u0019\u0010\u0013J\r\u0010\u001a\u001a\u00020\b\u00a2\u0006\u0004\b\u001a\u0010\nJ\u0015\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\b\u00a2\u0006\u0004\b\u001c\u0010\u0013J\u0015\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\b\u00a2\u0006\u0004\b\u001e\u0010\u0013J\r\u0010\u001f\u001a\u00020\b\u00a2\u0006\u0004\b\u001f\u0010\nJ\u0015\u0010!\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\b\u00a2\u0006\u0004\b!\u0010\u0013J\r\u0010\"\u001a\u00020\b\u00a2\u0006\u0004\b\"\u0010\nJ\u0015\u0010$\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0014\u00a2\u0006\u0004\b$\u0010\u0017J\u0015\u0010%\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0014\u00a2\u0006\u0004\b%\u0010\u0017J\r\u0010&\u001a\u00020\u0004\u00a2\u0006\u0004\b&\u0010\u0003J\r\u0010'\u001a\u00020\u0004\u00a2\u0006\u0004\b'\u0010\u0003J\u0015\u0010)\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\b\u00a2\u0006\u0004\b)\u0010\u0013J\u0015\u0010+\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u0014\u00a2\u0006\u0004\b+\u0010\u0017J\u0017\u0010.\u001a\u00020\u00042\b\u0010-\u001a\u0004\u0018\u00010,\u00a2\u0006\u0004\b.\u0010/J\u0013\u00101\u001a\b\u0012\u0004\u0012\u00020,00\u00a2\u0006\u0004\b1\u00102J\u0017\u00103\u001a\u00020\b2\b\u0010-\u001a\u0004\u0018\u00010,\u00a2\u0006\u0004\b3\u00104J\u000f\u00107\u001a\u00020\u0014H\u0000\u00a2\u0006\u0004\b5\u00106J\u000f\u00108\u001a\u0004\u0018\u00010,\u00a2\u0006\u0004\b8\u00109J\u0013\u0010;\u001a\b\u0012\u0004\u0012\u00020,0:\u00a2\u0006\u0004\b;\u0010<J\u0017\u0010?\u001a\u00020\u00042\u0006\u0010>\u001a\u00020=H\u0016\u00a2\u0006\u0004\b?\u0010@J+\u0010F\u001a\u00020\u00042\b\u0010A\u001a\u0004\u0018\u00010,2\u0006\u0010C\u001a\u00020B2\b\u0010E\u001a\u0004\u0018\u00010DH\u0002\u00a2\u0006\u0004\bF\u0010GR \u0010J\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020I0H8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bJ\u0010KR\u0016\u0010L\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bL\u0010MR\u0016\u0010N\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bN\u0010OR\u0016\u0010P\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bP\u0010MR\u0016\u0010Q\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bQ\u0010RR\u0016\u0010S\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bS\u0010MR\u0016\u0010T\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bT\u0010MR\u0016\u0010U\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bU\u0010MR\u0016\u0010V\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010MR\u0016\u0010W\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bW\u0010MR\u0016\u00107\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b7\u0010RR\u0016\u0010X\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bX\u0010RR\u0018\u00108\u001a\u0004\u0018\u00010,8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b8\u0010YR\u0014\u0010[\u001a\u00020Z8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b[\u0010\\R\u0018\u0010^\u001a\u0004\u0018\u00010]8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b^\u0010_R\u0018\u0010a\u001a\u0004\u0018\u00010`8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\ba\u0010bR\u0018\u0010d\u001a\u0004\u0018\u00010c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bd\u0010eR\u0016\u0010f\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bf\u0010MR\u0016\u0010h\u001a\u00020g8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bh\u0010iR\u0016\u0010C\u001a\u00020B8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bC\u0010j\u00a8\u0006m"}, d2={"Lrtx/kimiko/api/party/voice/PartyVoice;", "Lrtx/kimiko/api/party/voice/MicCapture$Listener;", "<init>", "()V", "", "start", "stop", "closeAudio", "", "isRunning", "()Z", "Lrtx/kimiko/api/party/voice/PartyVoice$ActivationMode;", "m", "setActivationMode", "(Lrtx/kimiko/api/party/voice/PartyVoice$ActivationMode;)V", "activationMode", "()Lrtx/kimiko/api/party/voice/PartyVoice$ActivationMode;", "held", "setPttHeld", "(Z)V", "", "s", "setVadSensitivity", "(F)V", "isTransmitting", "setMuted", "isMuted", "value", "setSuspended", "d", "setDenoise", "isDenoise", "a", "setAgc", "isAgc", "g", "setOutputGain", "setInputGain", "startPushToTalk", "stopPushToTalk", "on", "setEnabled", "gain", "setMicGain", "", "name", "setInputDeviceName", "(Ljava/lang/String;)V", "", "availableInputDevices", "()Ljava/util/List;", "isSpeaking", "(Ljava/lang/String;)Z", "outputGain$rtx_kimiko_kimiko", "()F", "outputGain", "lastError", "()Ljava/lang/String;", "", "talkingNames", "()Ljava/util/Set;", "", "samples", "onMicFrame", "([S)V", "from", "", "seq", "", "opus", "onVoice", "(Ljava/lang/String;I[B)V", "", "Lrtx/kimiko/api/party/voice/VoiceSpeaker;", "speakers", "Ljava/util/Map;", "running", "Z", "mode", "Lrtx/kimiko/api/party/voice/PartyVoice$ActivationMode;", "pttHeld", "vadSensitivity", "F", "actuallyTx", "muted", "suspended", "denoise", "agcEnabled", "inputGain", "Ljava/lang/String;", "", "audioLock", "Ljava/lang/Object;", "Lmods/voicechat/api/opus/OpusEncoder;", "encoder", "Lmods/voicechat/api/opus/OpusEncoder;", "Lmods/voicechat/natives/Denoiser;", "denoiser", "Lmods/voicechat/natives/Denoiser;", "Lmods/voicechat/natives/Agc;", "agc", "Lmods/voicechat/natives/Agc;", "wasTransmitting", "", "vadLastVoiceMs", "J", "I", "Companion", "ActivationMode", "rtx.kimiko:kimiko"})
public final class PartyVoice
implements MicCapture.Listener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Map<String, VoiceSpeaker> speakers = new ConcurrentHashMap();
    private volatile boolean running;
    @NotNull
    private volatile ActivationMode mode = ActivationMode.PTT;
    private volatile boolean pttHeld;
    private volatile float vadSensitivity = 0.5f;
    private volatile boolean actuallyTx;
    private volatile boolean muted;
    private volatile boolean suspended;
    private volatile boolean denoise = true;
    private volatile boolean agcEnabled = true;
    private volatile float outputGain = 1.0f;
    private volatile float inputGain = 1.0f;
    @Nullable
    private volatile String lastError;
    @NotNull
    private final Object audioLock = new Object();
    @Nullable
    private OpusEncoder encoder;
    @Nullable
    private Denoiser denoiser;
    @Nullable
    private Agc agc;
    private boolean wasTransmitting;
    private long vadLastVoiceMs;
    private int seq;
    @JvmField
    @NotNull
    public static final PartyVoice INSTANCE = new PartyVoice();
    @JvmField
    @NotNull
    public static final AudioFormat FORMAT = MicCapture.FORMAT;
    @JvmField
    public static final int FRAME_SAMPLES = MicCapture.FRAME_SAMPLES;
    @JvmField
    public static final int FRAME_BYTES = MicCapture.FRAME_BYTES;
    public static final long TALKING_WINDOW_MS = 350L;
    private static final long VAD_HANGOVER_MS = 300L;
    private static final int AGC_TARGET = AudioUtils.dbSample(-5.0);
    private static final float AGC_SPEECH_PROBABILITY = 0.95f;
    private static final int AGC_INCREMENT = 12;

    private PartyVoice() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final synchronized void start() {
        if (this.running) {
            return;
        }
        this.lastError = null;
        Object object = this.audioLock;
        synchronized (object) {
            boolean bl = false;
            this.encoder = PartyVoice.Companion.createEncoder();
            this.denoiser = PartyVoice.Companion.createDenoiser();
            this.agc = PartyVoice.Companion.createAgc();
            this.wasTransmitting = false;
            Unit unit = Unit.INSTANCE;
        }
        this.running = true;
        PartyClient.INSTANCE.setVoiceSink((arg_0, arg_1, arg_2) -> PartyVoice.start$lambda$1(this, arg_0, arg_1, arg_2));
        MicCapture.subscribe(this);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final synchronized void stop() {
        this.running = false;
        MicCapture.unsubscribe(this);
        this.pttHeld = false;
        this.actuallyTx = false;
        PartyClient.INSTANCE.setVoiceSink(null);
        Object object = this.audioLock;
        synchronized (object) {
            boolean bl = false;
            this.closeAudio();
            Unit unit = Unit.INSTANCE;
        }
        for (VoiceSpeaker s : this.speakers.values()) {
            s.close();
        }
        this.speakers.clear();
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

    public final boolean isRunning() {
        return this.running;
    }

    public final void setActivationMode(@NotNull ActivationMode m) {
        Intrinsics.checkNotNullParameter((Object)((Object)m), (String)"m");
        this.mode = m;
    }

    @NotNull
    public final ActivationMode activationMode() {
        return this.mode;
    }

    public final void setPttHeld(boolean held) {
        this.pttHeld = held;
    }

    public final void setVadSensitivity(float s) {
        this.vadSensitivity = Math.max(0.0f, Math.min(1.0f, s));
    }

    public final boolean isTransmitting() {
        return this.actuallyTx;
    }

    public final void setMuted(boolean m) {
        this.muted = m;
    }

    public final boolean isMuted() {
        return this.muted;
    }

    public final void setSuspended(boolean value) {
        this.suspended = value;
    }

    public final void setDenoise(boolean d) {
        this.denoise = d;
    }

    public final boolean isDenoise() {
        return this.denoise;
    }

    public final void setAgc(boolean a) {
        this.agcEnabled = a;
    }

    public final boolean isAgc() {
        return this.agcEnabled;
    }

    public final void setOutputGain(float g) {
        this.outputGain = Math.max(0.0f, g);
    }

    public final void setInputGain(float g) {
        this.inputGain = Math.max(0.0f, g);
    }

    public final void startPushToTalk() {
        this.setPttHeld(true);
    }

    public final void stopPushToTalk() {
        this.setPttHeld(false);
    }

    public final void setEnabled(boolean on) {
        if (on) {
            this.start();
        } else {
            this.stop();
        }
    }

    public final void setMicGain(float gain) {
        this.setInputGain(gain);
    }

    public final void setInputDeviceName(@Nullable String name) {
        MicCapture.setDevice(name);
    }

    @NotNull
    public final List<String> availableInputDevices() {
        return MicCapture.devices();
    }

    public final boolean isSpeaking(@Nullable String name) {
        return name != null && this.talkingNames().contains(name);
    }

    public final float outputGain$rtx_kimiko_kimiko() {
        return this.outputGain;
    }

    @Nullable
    public final String lastError() {
        String string = this.lastError;
        if (string == null) {
            string = MicCapture.lastError();
        }
        return string;
    }

    @NotNull
    public final Set<String> talkingNames() {
        long now = System.currentTimeMillis();
        HashSet<String> out = new HashSet<String>();
        for (VoiceSpeaker s : this.speakers.values()) {
            if (now - s.lastAudio() > 350L) continue;
            out.add(s.name());
        }
        return out;
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
            OpusEncoder enc;
            block31: {
                block30: {
                    boolean bl = false;
                    enc = this.encoder;
                    if (!this.running) break block30;
                    if (enc != null) break block31;
                }
                return;
            }
            try {
                byte[] opus;
                block33: {
                    block32: {
                        float speechProb = 1.0f;
                        Denoiser currentDenoiser = this.denoiser;
                        if (currentDenoiser != null) {
                            try {
                                speechProb = this.denoise ? currentDenoiser.denoiseInPlace(samples) : currentDenoiser.getSpeechProbability(samples);
                            }
                            catch (Throwable throwable) {
                                // empty catch block
                            }
                        }
                        Agc currentAgc = this.agc;
                        if (this.agcEnabled && currentAgc != null) {
                            Object object2;
                            try {
                                currentAgc.setIncrement(speechProb >= 0.95f ? 12 : 0);
                                object2 = currentAgc.agc(samples);
                            }
                            catch (Throwable ignored) {
// object2 = Unit.INSTANCE;
                            }
                        } else {
                            Companion.applyGain$rtx_kimiko_kimiko(samples, this.inputGain);
                        }
                        boolean shouldSend = false;
                        if (this.mode == ActivationMode.PTT) {
                            shouldSend = this.pttHeld;
                        } else {
                            boolean bl;
                            if (currentDenoiser != null) {
                                bl = speechProb >= this.vadSensitivity;
                            } else {
                                double db = -60.0 + (double)this.vadSensitivity * 50.0;
                                bl = AudioUtils.getHighestAudioLevel(samples) >= db;
                            }
                            boolean voiced = bl;
                            long now = System.currentTimeMillis();
                            if (voiced) {
                                this.vadLastVoiceMs = now;
                            }
                            boolean bl2 = shouldSend = now - this.vadLastVoiceMs <= 300L;
                        }
                        if (!shouldSend || this.suspended || !PartyClient.INSTANCE.isConnected()) {
                            if (this.wasTransmitting) {
                                try {
                                    enc.resetState();
                                }
                                catch (Throwable voiced) {
                                    // empty catch block
                                }
                                this.wasTransmitting = false;
                            }
                            this.actuallyTx = false;
                            return;
                        }
                        this.wasTransmitting = true;
                        this.actuallyTx = true;
                        opus = enc.encode(samples);
                        if (opus == null) break block32;
                        if (!(opus.length == 0)) break block33;
                    }
                    return;
                }
                int n = this.seq;
                this.seq = n + 1;
                int s = n & 0xFFFF;
                byte[] frame = new byte[2 + opus.length];
                frame[0] = (byte)(s >> 8);
                frame[1] = (byte)s;
                System.arraycopy(opus, 0, frame, 2, opus.length);
                PartyClient.INSTANCE.sendVoice(frame);
            }
            catch (Throwable t) {
                this.lastError = String.valueOf(t.getMessage());
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void onVoice(String from, int seq, byte[] opus) {
        if (!this.running || this.muted || from == null || ((CharSequence)from).length() == 0 || opus == null || opus.length == 0) {
            return;
        }
        VoiceSpeaker sp = this.speakers.get(from);
        if (sp == null || sp.isClosed()) {
            sp = new VoiceSpeaker(from);
            this.speakers.put(from, sp);
        }
        sp.enqueue(opus);
    }

    private static final void start$lambda$1(PartyVoice this$0, String from, int seq, byte[] opus) {
        Intrinsics.checkNotNullParameter((Object)from, (String)"from");
        Intrinsics.checkNotNullParameter((Object)opus, (String)"opus");
        this$0.onVoice(from, seq, opus);
    }

    @JvmStatic
    public static final void applyGain$rtx_kimiko_kimiko(@NotNull short[] samples, float gain) {
        Companion.applyGain$rtx_kimiko_kimiko(samples, gain);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2={"Lrtx/kimiko/api/party/voice/PartyVoice$ActivationMode;", "", "<init>", "(Ljava/lang/String;I)V", "PTT", "VOICE", "rtx.kimiko:kimiko"})
    public static enum ActivationMode {
        PTT,
        VOICE;
@NotNull
        public static EnumEntries<ActivationMode> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0017\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ#\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0001b\u0002\b\u0012\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0018\u001a\u00020\u00168\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0017\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0019\u0010\u001b\u001a\u00020\u001a8\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0017\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0019\u0010\u001e\u001a\u00020\u001d8\u0000X\u0081\u0004\u0092\u0002\u0002\b\u0017\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0019\u0010 \u001a\u00020\u001d8\u0000X\u0081\u0004\u0092\u0002\u0002\b\u0017\u00a2\u0006\u0006\n\u0004\b \u0010\u001fR\u0014\u0010\"\u001a\u00020!8\u0000X\u0080T\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020!8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b$\u0010#R\u0014\u0010%\u001a\u00020\u001d8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010\u001fR\u0014\u0010&\u001a\u00020\u000f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020\u001d8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010\u001f\u00a8\u0006)"}, d2={"Lrtx/kimiko/api/party/voice/PartyVoice.Companion;", "", "<init>", "()V", "Lmods/voicechat/natives/Denoiser;", "createDenoiser", "()Lmods/voicechat/natives/Denoiser;", "Lmods/voicechat/natives/Agc;", "createAgc", "()Lmods/voicechat/natives/Agc;", "Lmods/voicechat/api/opus/OpusEncoder;", "createEncoder", "()Lmods/voicechat/api/opus/OpusEncoder;", "", "samples", "", "gain", "", "Lkotlin/jvm/JvmStatic;", "applyGain$rtx_kimiko_kimiko", "([SF)V", "applyGain", "Lrtx/kimiko/api/party/voice/PartyVoice;", "Lkotlin/jvm/JvmField;", "INSTANCE", "Lrtx/kimiko/api/party/voice/PartyVoice;", "Ljavax/sound/sampled/AudioFormat;", "FORMAT", "Ljavax/sound/sampled/AudioFormat;", "", "FRAME_SAMPLES", "I", "FRAME_BYTES", "", "TALKING_WINDOW_MS", "J", "VAD_HANGOVER_MS", "AGC_TARGET", "AGC_SPEECH_PROBABILITY", "F", "AGC_INCREMENT", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
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
            Agc agc;
            try {
                Agc agc2 = new Agc(960, 48000);
                agc2.setTarget(AGC_TARGET);
                agc = agc2;
            }
            catch (Throwable t) {
                agc = null;
            }
            return agc;
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

        @JvmStatic
        public final void applyGain$rtx_kimiko_kimiko(@NotNull short[] samples, float gain) {
            Intrinsics.checkNotNullParameter((Object)samples, (String)"samples");
            if (gain == 1.0f) {
                return;
            }
            int n = samples.length;
            for (int i = 0; i < n; ++i) {
                int v = Math.round((float)samples[i] * gain);
                if (v > Short.MAX_VALUE) {
                    v = Short.MAX_VALUE;
                } else if (v < Short.MIN_VALUE) {
                    v = Short.MIN_VALUE;
                }
                samples[i] = (short)v;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

