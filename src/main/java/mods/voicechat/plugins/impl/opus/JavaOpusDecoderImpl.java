/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.concentus.OpusDecoder
 *  org.concentus.OpusException
 */
package mods.voicechat.plugins.impl.opus;

import javax.annotation.Nullable;
import mods.voicechat.api.opus.OpusDecoder;
import org.concentus.OpusException;

public class JavaOpusDecoderImpl
implements OpusDecoder {
    protected org.concentus.OpusDecoder opusDecoder;
    protected short[] buffer;
    protected int sampleRate;

    public JavaOpusDecoderImpl(int sampleRate, int frameSize) {
        this.sampleRate = sampleRate;
        this.buffer = new short[frameSize];
        this.open();
    }

    private void open() {
        if (this.opusDecoder != null) {
            return;
        }
        try {
            this.opusDecoder = new org.concentus.OpusDecoder(this.sampleRate, 1);
        }
        catch (OpusException e) {
            throw new IllegalStateException("Failed to create Opus decoder", e);
        }
    }

    @Override
    public short[] decode(@Nullable byte[] data) {
        int result;
        if (this.isClosed()) {
            throw new IllegalStateException("Decoder is closed");
        }
        try {
            result = data == null || data.length == 0 ? this.opusDecoder.decode(null, 0, 0, this.buffer, 0, this.buffer.length, true) : this.opusDecoder.decode(data, 0, data.length, this.buffer, 0, this.buffer.length, false);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to decode audio", e);
        }
        short[] audio = new short[result];
        System.arraycopy(this.buffer, 0, audio, 0, result);
        return audio;
    }

    @Override
    public short[][] decode(byte[] data, int frames) {
        if (this.isClosed()) {
            throw new IllegalStateException("Decoder is closed");
        }
        if (frames <= 0) {
            throw new IllegalArgumentException("Frames must be greater than 0");
        }
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("Data must not be null or empty");
        }
        short[][] recovered = new short[frames][];
        try {
            int result;
            if (frames > 2) {
                for (int i = 0; i < frames - 2; ++i) {
                    result = this.opusDecoder.decode(null, 0, 0, this.buffer, 0, this.buffer.length, true);
                    short[] audio = new short[result];
                    System.arraycopy(this.buffer, 0, audio, 0, result);
                    recovered[i] = audio;
                }
            }
            if (frames > 1) {
                result = this.opusDecoder.decode(data, 0, data.length, this.buffer, 0, this.buffer.length, true);
                short[] audio = new short[result];
                System.arraycopy(this.buffer, 0, audio, 0, result);
                recovered[frames - 2] = audio;
            }
            result = this.opusDecoder.decode(data, 0, data.length, this.buffer, 0, this.buffer.length, false);
            short[] audio = new short[result];
            System.arraycopy(this.buffer, 0, audio, 0, result);
            recovered[frames - 1] = audio;
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to decode audio", e);
        }
        return recovered;
    }

    @Override
    public boolean isClosed() {
        return this.opusDecoder == null;
    }

    @Override
    public void close() {
        if (this.isClosed()) {
            return;
        }
        this.opusDecoder = null;
    }

    @Override
    public void resetState() {
        if (this.isClosed()) {
            throw new IllegalStateException("Decoder is closed");
        }
        this.opusDecoder.resetState();
    }
}

