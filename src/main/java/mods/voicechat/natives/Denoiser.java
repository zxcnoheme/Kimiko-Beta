/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.rnnoise4j.Denoiser
 *  de.maxhenkel.rnnoise4j.UnknownPlatformException
 */
package mods.voicechat.natives;

import de.maxhenkel.rnnoise4j.UnknownPlatformException;
import java.io.IOException;
import java.lang.ref.Cleaner;

public class Denoiser
implements AutoCloseable {
    private static final Cleaner CLEANER = Cleaner.create();
    private final State state;
    private final Cleaner.Cleanable cleanable;

    public Denoiser() throws IOException, UnknownPlatformException {
        de.maxhenkel.rnnoise4j.Denoiser denoiser = new de.maxhenkel.rnnoise4j.Denoiser();
        this.state = new State(denoiser);
        this.cleanable = CLEANER.register(this, this.state);
    }

    public int getFrameSize() {
        return this.state.denoiser.getFrameSize();
    }

    public short[] denoise(short[] input) {
        return this.state.denoiser.denoise(input);
    }

    public float denoiseInPlace(short[] input) {
        return this.state.denoiser.denoiseInPlace(input);
    }

    public float getSpeechProbability(short[] input) {
        return this.state.denoiser.getSpeechProbability(input);
    }

    @Override
    public void close() {
        this.cleanable.clean();
    }

    public boolean isClosed() {
        return this.state.denoiser.isClosed();
    }

    private static final class State
    implements Runnable {
        private final de.maxhenkel.rnnoise4j.Denoiser denoiser;

        private State(de.maxhenkel.rnnoise4j.Denoiser denoiser) {
            this.denoiser = denoiser;
        }

        @Override
        public void run() {
            this.denoiser.close();
        }
    }
}

