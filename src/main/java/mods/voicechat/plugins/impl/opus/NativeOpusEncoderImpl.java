/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.opus4j.OpusEncoder
 *  de.maxhenkel.opus4j.OpusEncoder$Application
 *  de.maxhenkel.opus4j.UnknownPlatformException
 */
package mods.voicechat.plugins.impl.opus;

import de.maxhenkel.opus4j.OpusEncoder;
import de.maxhenkel.opus4j.UnknownPlatformException;
import java.io.IOException;
import java.lang.ref.Cleaner;

public class NativeOpusEncoderImpl
implements mods.voicechat.api.opus.OpusEncoder {
    private static final Cleaner CLEANER = Cleaner.create();
    private final State state;
    private final Cleaner.Cleanable cleanable;

    public NativeOpusEncoderImpl(int sampleRate, int channels, OpusEncoder.Application application) throws IOException, UnknownPlatformException {
        OpusEncoder encoder = new OpusEncoder(sampleRate, channels, application);
        encoder.setMaxPacketLossPercentage(0.05f);
        this.state = new State(encoder);
        this.cleanable = CLEANER.register(this, this.state);
    }

    public void setMaxPayloadSize(int maxPayloadSize) {
        this.state.encoder.setMaxPayloadSize(maxPayloadSize);
    }

    @Override
    public byte[] encode(short[] rawAudio) {
        return this.state.encoder.encode(rawAudio);
    }

    @Override
    public void resetState() {
        this.state.encoder.resetState();
    }

    @Override
    public boolean isClosed() {
        return this.state.encoder.isClosed();
    }

    @Override
    public void close() {
        this.cleanable.clean();
    }

    private static final class State
    implements Runnable {
        private final OpusEncoder encoder;

        private State(OpusEncoder encoder) {
            this.encoder = encoder;
        }

        @Override
        public void run() {
            this.encoder.close();
        }
    }
}

