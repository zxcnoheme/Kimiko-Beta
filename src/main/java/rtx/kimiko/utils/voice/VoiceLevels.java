/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.voice;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\f\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u001d\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006\u00a2\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0006\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\r\u0010\u0011\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0011\u0010\u000fJ\r\u0010\u0012\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0012\u0010\u000fR\u0016\u0010\u0011\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0013R\u0016\u0010\u0012\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0010\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0013R\u0016\u0010\u000e\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u0013R\u0016\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\f\u0010\u0014R\u0016\u0010\u0015\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0014\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/utils/voice/VoiceLevels;", "", "<init>", "()V", "", "reset", "", "rms", "prob", "update", "(FF)V", "", "voiced", "()Z", "gain", "()F", "snr", "noiseDb", "speechDb", "F", "Z", "started", "Companion", "rtx.kimiko:kimiko"})
public final class VoiceLevels {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private float noiseDb = -60.0f;
    private float speechDb = -30.0f;
    private float snr;
    private float gain = 1.0f;
    private boolean voiced;
    private boolean started;
    private static final float TARGET_DB = -16.0f;
    private static final float FLOOR_MIN_DB = -78.0f;
    private static final float FLOOR_MAX_DB = -22.0f;
    private static final float SPEECH_MIN_DB = -58.0f;
    private static final float SPEECH_MAX_DB = -3.0f;
    private static final float FLOOR_FALL = 0.3f;
    private static final float FLOOR_RISE_DB = 0.012f;
    private static final float FLOOR_RISE_VOICED_DB = 0.002f;
    private static final float SPEECH_ATTACK = 0.3f;
    private static final float SPEECH_RELEASE = 0.02f;
    private static final float SNR_ON = 9.0f;
    private static final float SNR_OFF = 5.0f;
    private static final float SNR_ASSIST = 3.0f;
    private static final float MAX_GAIN_DB = 24.0f;
    private static final float GAIN_UP = 0.02f;
    private static final float GAIN_DOWN = 0.25f;

    public final void reset() {
        this.started = false;
        this.voiced = false;
        this.gain = 1.0f;
        this.snr = 0.0f;
    }

    public final void update(float rms, float prob) {
        float db = 20.0f * (float)Math.log10(Math.max(rms, 1.0E-7f));
        if (!this.started) {
            this.started = true;
            this.noiseDb = db;
            this.speechDb = -16.0f;
        }
        this.noiseDb = db < this.noiseDb ? (this.noiseDb += (db - this.noiseDb) * 0.3f) : (this.noiseDb += this.voiced ? 0.002f : 0.012f);
        this.noiseDb = VoiceLevels.Companion.clamp(this.noiseDb, -78.0f, -22.0f);
        this.snr = db - this.noiseDb;
        float gate = this.voiced ? 5.0f : 9.0f;
        boolean bl = this.voiced = this.snr > gate || prob > 0.9f && this.snr > 3.0f;
        if (this.voiced) {
            float rate = db > this.speechDb ? 0.3f : 0.02f;
            this.speechDb += (db - this.speechDb) * rate;
            this.speechDb = VoiceLevels.Companion.clamp(this.speechDb, -58.0f, -3.0f);
        }
        float gainDb = VoiceLevels.Companion.clamp(-16.0f - this.speechDb, 0.0f, 24.0f);
        float target = (float)Math.pow(10.0, (double)gainDb / 20.0);
        this.gain += (target - this.gain) * (target < this.gain ? 0.25f : 0.02f);
    }

    public final boolean voiced() {
        return this.voiced;
    }

    public final float gain() {
        return this.gain;
    }

    public final float snr() {
        return this.snr;
    }

    public final float noiseDb() {
        return this.noiseDb;
    }

    public final float speechDb() {
        return this.speechDb;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0017\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J'\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\u000bR\u0014\u0010\r\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000bR\u0014\u0010\u000e\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000bR\u0014\u0010\u000f\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u000bR\u0014\u0010\u0011\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u000bR\u0014\u0010\u0012\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u000bR\u0014\u0010\u0013\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u000bR\u0014\u0010\u0014\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u000bR\u0014\u0010\u0015\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u000bR\u0014\u0010\u0016\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u000bR\u0014\u0010\u0017\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u000bR\u0014\u0010\u0018\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u000bR\u0014\u0010\u0019\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u000bR\u0014\u0010\u001a\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u000b\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/utils/voice/VoiceLevels.Companion;", "", "<init>", "()V", "", "value", "min", "max", "clamp", "(FFF)F", "TARGET_DB", "F", "FLOOR_MIN_DB", "FLOOR_MAX_DB", "SPEECH_MIN_DB", "SPEECH_MAX_DB", "FLOOR_FALL", "FLOOR_RISE_DB", "FLOOR_RISE_VOICED_DB", "SPEECH_ATTACK", "SPEECH_RELEASE", "SNR_ON", "SNR_OFF", "SNR_ASSIST", "MAX_GAIN_DB", "GAIN_UP", "GAIN_DOWN", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final float clamp(float value, float min, float max) {
            return value < min ? min : (value > max ? max : value);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

