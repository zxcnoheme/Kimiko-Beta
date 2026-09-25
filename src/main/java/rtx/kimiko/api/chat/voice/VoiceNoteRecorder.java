/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.opus4j.OpusEncoder$Application
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.Ref$ObjectRef
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.chat.voice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import mods.voicechat.api.opus.OpusEncoder;
import mods.voicechat.natives.Agc;
import mods.voicechat.natives.Denoiser;
import mods.voicechat.plugins.impl.opus.JavaOpusEncoderImpl;
import mods.voicechat.plugins.impl.opus.NativeOpusEncoderImpl;
import mods.voicechat.voice.common.AudioUtils;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.chat.messenger.MessengerClient;
import rtx.kimiko.api.chat.voice.VoiceNote;
import rtx.kimiko.api.chat.voice.VoiceNoteApi;
import rtx.kimiko.api.lang.I18n;
import rtx.kimiko.api.notifications.Notifications;
import rtx.kimiko.api.party.voice.MicCapture;
import rtx.kimiko.api.party.voice.PartyVoice;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\u0017\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0014\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\u0007J\u0013\u0010\n\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\r\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0016\u0010\u0015J\u001b\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u0013H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0019\u0010\u0003J\u0013\u0010\u001a\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001a\u0010\u0007J\u0013\u0010\u001b\u001a\u00020\u0013H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001b\u0010\u0003J\u0013\u0010\u001c\u001a\u00020\u0013H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001c\u0010\u0003J\u000f\u0010\u001d\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u0007J\u000f\u0010\u001e\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u0003J\u0017\u0010!\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u001fH\u0016\u00a2\u0006\u0004\b!\u0010\"J\u0017\u0010#\u001a\u00020\f2\u0006\u0010 \u001a\u00020\u001fH\u0002\u00a2\u0006\u0004\b#\u0010$J\u0017\u0010&\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b&\u0010'J\u0011\u0010)\u001a\u0004\u0018\u00010(H\u0002\u00a2\u0006\u0004\b)\u0010*J\u0011\u0010,\u001a\u0004\u0018\u00010+H\u0002\u00a2\u0006\u0004\b,\u0010-J\u000f\u0010/\u001a\u00020.H\u0002\u00a2\u0006\u0004\b/\u00100R\u0014\u00101\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b1\u00102R\u0014\u00104\u001a\u0002038\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0014\u00107\u001a\u0002068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b7\u00108R$\u0010<\u001a\u0012\u0012\u0004\u0012\u00020:09j\b\u0012\u0004\u0012\u00020:`;8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b<\u0010=R\u0014\u0010?\u001a\u00020>8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b?\u0010@R\u0016\u0010A\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bA\u0010BR\u0016\u0010C\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bC\u0010BR\u0016\u0010D\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bD\u0010ER\u0016\u0010F\u001a\u0002038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bF\u00105R\u0016\u0010G\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bG\u00102R\u0016\u0010H\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bH\u0010BR\u0016\u0010I\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bI\u0010BR\u0016\u0010J\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bJ\u00102R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0010\u0010KR\u0018\u0010L\u001a\u0004\u0018\u00010.8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bL\u0010MR\u0018\u0010N\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bN\u0010OR\u0018\u0010P\u001a\u0004\u0018\u00010+8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bP\u0010Q\u00a8\u0006R"}, d2={"Lrtx/kimiko/api/chat/voice/VoiceNoteRecorder;", "Lrtx/kimiko/api/party/voice/MicCapture$Listener;", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "isRecording", "()Z", "isUploading", "", "elapsedMs", "()J", "", "level", "()F", "", "lastError", "()Ljava/lang/String;", "value", "", "setDenoise", "(Z)V", "setAgc", "setInputGain", "(F)V", "toggle", "start", "cancel", "finish", "detach", "closeAudio", "", "samples", "onMicFrame", "([S)V", "peakOf", "([S)F", "message", "notice", "(Ljava/lang/String;)V", "Lmods/voicechat/natives/Denoiser;", "createDenoiser", "()Lmods/voicechat/natives/Denoiser;", "Lmods/voicechat/natives/Agc;", "createAgc", "()Lmods/voicechat/natives/Agc;", "Lmods/voicechat/api/opus/OpusEncoder;", "createEncoder", "()Lmods/voicechat/api/opus/OpusEncoder;", "AGC_SPEECH_PROBABILITY", "F", "", "AGC_INCREMENT", "I", "", "lock", "Ljava/lang/Object;", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "frames", "Ljava/util/ArrayList;", "", "levels", "[F", "recording", "Z", "uploading", "startedAt", "J", "levelCount", "smoothLevel", "denoise", "agcEnabled", "inputGain", "Ljava/lang/String;", "encoder", "Lmods/voicechat/api/opus/OpusEncoder;", "denoiser", "Lmods/voicechat/natives/Denoiser;", "agc", "Lmods/voicechat/natives/Agc;", "rtx.kimiko:kimiko"})
public final class VoiceNoteRecorder
implements MicCapture.Listener {
    @NotNull
    public static final VoiceNoteRecorder INSTANCE = new VoiceNoteRecorder();
    private static final float AGC_SPEECH_PROBABILITY = 0.95f;
    private static final int AGC_INCREMENT = 12;
    @NotNull
    private static final Object lock = new Object();
    @NotNull
    private static final ArrayList<byte[]> frames = new ArrayList(3000);
    @NotNull
    private static final float[] levels = new float[3000];
    private static volatile boolean recording;
    private static volatile boolean uploading;
    private static volatile long startedAt;
    private static volatile int levelCount;
    private static volatile float smoothLevel;
    private static volatile boolean denoise;
    private static volatile boolean agcEnabled;
    private static volatile float inputGain;
    @Nullable
    private static volatile String lastError;
    @Nullable
    private static OpusEncoder encoder;
    @Nullable
    private static Denoiser denoiser;
    @Nullable
    private static Agc agc;

    private VoiceNoteRecorder() {
    }

    @JvmStatic
    public static final boolean isRecording() {
        return recording;
    }

    @JvmStatic
    public static final boolean isUploading() {
        return uploading;
    }

    @JvmStatic
    public static final long elapsedMs() {
        if (!recording) {
            return 0L;
        }
        return Math.min(60000L, System.currentTimeMillis() - startedAt);
    }

    @JvmStatic
    public static final float level() {
        return smoothLevel;
    }

    @JvmStatic
    @Nullable
    public static final String lastError() {
        return lastError;
    }

    @JvmStatic
    public static final void setDenoise(boolean value) {
        denoise = value;
    }

    @JvmStatic
    public static final void setAgc(boolean value) {
        agcEnabled = value;
    }

    @JvmStatic
    public static final void setInputGain(float value) {
        inputGain = Math.max(0.0f, value);
    }

    @JvmStatic
    public static final void toggle() {
        if (recording) {
            VoiceNoteRecorder.finish();
        } else {
            VoiceNoteRecorder.start();
        }
    }

    @JvmStatic
    public static final boolean start() {
        return false;
    }

    @JvmStatic
    public static final void cancel() {
        INSTANCE.detach();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @JvmStatic
    public static final void finish() {
        if (!INSTANCE.detach()) {
            return;
        }
        ArrayList captured = null;
        Ref.ObjectRef waveform = new Ref.ObjectRef();
        Object object = lock;
        synchronized (object) {
            captured = new ArrayList(frames);
            float[] fArray = Arrays.copyOf(levels, levelCount);
            Intrinsics.checkNotNullExpressionValue((Object)fArray, (String)"copyOf(...)");
            waveform.element = VoiceNote.resample(fArray, 26);
            frames.clear();
            levelCount = 0;
        }
        int durationMs = ((List)captured).size() * 20;
        if (durationMs < 500) {
            INSTANCE.notice(I18n.tr("Слишком короткое сообщение"));
            return;
        }
        byte[] blob = VoiceNote.pack(captured);
        if (blob.length == 0 || blob.length > 1500000) {
            INSTANCE.notice(I18n.tr("Голосовое не удалось подготовить"));
            return;
        }
        uploading = true;
        Thread worker = new Thread(() -> VoiceNoteRecorder.finish$lambda$1(blob, durationMs, waveform), "kimiko-voice-note-upload");
        worker.setDaemon(true);
        worker.start();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final boolean detach() {
        Object object = lock;
        synchronized (object) {
            block7: {
                boolean bl = false;
                if (recording) break block7;
                boolean bl2 = false;
                return bl2;
            }
            recording = false;
            Unit unit = Unit.INSTANCE;
        }
        MicCapture.unsubscribe(this);
        PartyVoice.INSTANCE.setSuspended(false);
        smoothLevel = 0.0f;
        object = lock;
        synchronized (object) {
            boolean bl = false;
            INSTANCE.closeAudio();
            Unit unit = Unit.INSTANCE;
        }
        return true;
    }

    private final void closeAudio() {
        if (encoder != null) {
            try {
                encoder.close();
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            encoder = null;
        }
        if (denoiser != null) {
            try {
                denoiser.close();
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            denoiser = null;
        }
        if (agc != null) {
            try {
                agc.close();
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            agc = null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void onMicFrame(@NotNull short[] samples) {
        Intrinsics.checkNotNullParameter((Object)samples, (String)"samples");
        if (!recording) {
            return;
        }
        boolean full = false;
        Object object = lock;
        synchronized (object) {
            boolean bl = false;
            OpusEncoder enc = encoder;
            if (!recording || enc == null) {
                return;
            }
            try {
                float speechProb = 1.0f;
                Denoiser currentDenoiser = denoiser;
                if (currentDenoiser != null) {
                    try {
                        speechProb = denoise ? currentDenoiser.denoiseInPlace(samples) : currentDenoiser.getSpeechProbability(samples);
                    }
                    catch (Throwable throwable) {
                        // empty catch block
                    }
                }
                Agc currentAgc = agc;
                if (agcEnabled && currentAgc != null) {
                    Object object2;
                    try {
                        currentAgc.setIncrement(speechProb >= 0.95f ? 12 : 0);
                        object2 = currentAgc.agc(samples);
                    }
                    catch (Throwable ignored) {
// object2 = Unit.INSTANCE;
                    }
                } else {
                    VoiceNote.applyGain(samples, inputGain);
                }
                byte[] opus = enc.encode(samples);
                if (opus != null && !(opus.length == 0) && frames.size() < 3000) {
                    frames.add(opus);
                    if (levelCount < levels.length) {
                        int n = levelCount;
                        levelCount = n + 1;
                        VoiceNoteRecorder.levels[n] = INSTANCE.peakOf(samples);
                    }
                }
                full = frames.size() >= 3000;
            }
            catch (Throwable t) {
                lastError = String.valueOf(t.getMessage());
                full = true;
            }
            Unit unit = Unit.INSTANCE;
        }
        smoothLevel = smoothLevel * 0.7f + this.peakOf(samples) * 0.3f;
        if (full) {
            Thread closer = new Thread(VoiceNoteRecorder::onMicFrame$lambda$1, "kimiko-voice-note-limit");
            closer.setDaemon(true);
            closer.start();
        }
    }

    private final float peakOf(short[] samples) {
        int peak = 0;
        for (short s : samples) {
            int v = Math.abs(s);
            if (v <= peak) continue;
            peak = v;
        }
        return Math.min(1.0f, (float)peak / 32767.0f);
    }

    private final void notice(String message) {
        MinecraftClient.getInstance().execute(() -> VoiceNoteRecorder.notice$lambda$0(message));
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
            Agc instance = new Agc(960, 48000);
            instance.setTarget(AudioUtils.dbSample(-5.0));
            return instance;
        }
        catch (Throwable t) {
            return null;
        }
    }

    private final OpusEncoder createEncoder() {
        OpusEncoder opusEncoder;
        try {
            NativeOpusEncoderImpl enc = new NativeOpusEncoderImpl(48000, 1, de.maxhenkel.opus4j.OpusEncoder.Application.AUDIO);
            enc.setMaxPayloadSize(1275);
            opusEncoder = enc;
        }
        catch (Throwable t) {
            opusEncoder = new JavaOpusEncoderImpl(48000, 960, 1275, de.maxhenkel.opus4j.OpusEncoder.Application.AUDIO);
        }
        return opusEncoder;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static final void finish$lambda$1(byte[] $blob, int $durationMs, Ref.ObjectRef $waveform) {
        try {
            String id = VoiceNoteApi.upload($blob);
            if (id == null) {
                INSTANCE.notice(I18n.tr("Не удалось отправить голосовое"));
            } else {
                MessengerClient.INSTANCE.sendVoice(id, $durationMs, VoiceNote.peaksToBase64((byte[])$waveform.element));
            }
        }
        finally {
            uploading = false;
        }
    }

    private static final void onMicFrame$lambda$1() {
        VoiceNoteRecorder.finish();
    }

    private static final void notice$lambda$0(String $message) {
        Notifications.push(I18n.tr("Голосовое"), $message, 2400L);
    }

    static {
        denoise = true;
        agcEnabled = true;
        inputGain = 1.0f;
    }
}

