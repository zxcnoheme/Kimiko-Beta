/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.opus4j.OpusEncoder$Application
 *  org.concentus.OpusApplication
 *  org.concentus.OpusEncoder
 */
package mods.voicechat.plugins.impl.opus;

import mods.voicechat.api.opus.OpusEncoder;
import org.concentus.OpusApplication;

public class JavaOpusEncoderImpl
implements OpusEncoder {
    protected org.concentus.OpusEncoder opusEncoder;
    protected byte[] buffer;
    protected int sampleRate;
    protected int frameSize;
    protected de.maxhenkel.opus4j.OpusEncoder.Application application;

    public JavaOpusEncoderImpl(int sampleRate, int frameSize, int maxPayloadSize, de.maxhenkel.opus4j.OpusEncoder.Application application) {
        this.sampleRate = sampleRate;
        this.frameSize = frameSize;
        this.application = application;
        this.buffer = new byte[maxPayloadSize];
        this.open();
    }

    private void open() {
        if (this.opusEncoder != null) {
            return;
        }
        try {
            this.opusEncoder = new org.concentus.OpusEncoder(this.sampleRate, 1, JavaOpusEncoderImpl.getApplication(this.application));
            this.opusEncoder.setUseInbandFEC(true);
            this.opusEncoder.setPacketLossPercent(5);
        }
        catch (Exception e) {
            throw new IllegalStateException("Failed to create Opus encoder", e);
        }
    }

    @Override
    public byte[] encode(short[] rawAudio) {
        int result;
        if (this.isClosed()) {
            throw new IllegalStateException("Encoder is closed");
        }
        try {
            result = this.opusEncoder.encode(rawAudio, 0, this.frameSize, this.buffer, 0, this.buffer.length);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to encode audio", e);
        }
        if (result < 0) {
            throw new RuntimeException("Failed to encode audio data");
        }
        byte[] audio = new byte[result];
        System.arraycopy(this.buffer, 0, audio, 0, result);
        return audio;
    }

    @Override
    public void resetState() {
        if (this.isClosed()) {
            throw new IllegalStateException("Encoder is closed");
        }
        this.opusEncoder.resetState();
    }

    @Override
    public boolean isClosed() {
        return this.opusEncoder == null;
    }

    @Override
    public void close() {
        if (this.isClosed()) {
            return;
        }
        this.opusEncoder = null;
    }

    public static OpusApplication getApplication(de.maxhenkel.opus4j.OpusEncoder.Application application) {
        switch (application) {
            default: {
                return OpusApplication.OPUS_APPLICATION_VOIP;
            }
            case AUDIO: {
                return OpusApplication.OPUS_APPLICATION_AUDIO;
            }
            case LOW_DELAY: 
        }
        return OpusApplication.OPUS_APPLICATION_RESTRICTED_LOWDELAY;
    }
}

