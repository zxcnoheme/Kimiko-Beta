/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.opus4j.OpusDecoder
 *  de.maxhenkel.opus4j.UnknownPlatformException
 *  javax.annotation.Nullable
 */
package mods.voicechat.plugins.impl.opus;

import de.maxhenkel.opus4j.UnknownPlatformException;
import java.io.IOException;
import java.lang.ref.Cleaner;
import javax.annotation.Nullable;
import mods.voicechat.api.opus.OpusDecoder;

public class NativeOpusDecoderImpl
implements OpusDecoder {
    private static final Cleaner CLEANER = Cleaner.create();
    private final State state;
    private final Cleaner.Cleanable cleanable;

    public NativeOpusDecoderImpl(int sampleRate, int channels) throws IOException, UnknownPlatformException {
        de.maxhenkel.opus4j.OpusDecoder decoder = new de.maxhenkel.opus4j.OpusDecoder(sampleRate, channels);
        this.state = new State(decoder);
        this.cleanable = CLEANER.register(this, this.state);
    }

    public void setFrameSize(int frameSize) {
        this.state.decoder.setFrameSize(frameSize);
    }

    @Override
    public short[] decode(@Nullable byte[] data) {
        return this.state.decoder.decode(data);
    }

    @Override
    public short[][] decode(byte[] input, int frames) {
        return this.state.decoder.decode(input, frames);
    }

    @Override
    public void resetState() {
        this.state.decoder.resetState();
    }

    @Override
    public boolean isClosed() {
        return this.state.decoder.isClosed();
    }

    @Override
    public void close() {
        this.cleanable.clean();
    }

    private static final class State
    implements Runnable {
        private final de.maxhenkel.opus4j.OpusDecoder decoder;

        private State(de.maxhenkel.opus4j.OpusDecoder decoder) {
            this.decoder = decoder;
        }

        @Override
        public void run() {
            this.decoder.close();
        }
    }
}

