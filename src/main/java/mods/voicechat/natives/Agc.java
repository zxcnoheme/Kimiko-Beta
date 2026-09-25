/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  de.maxhenkel.speex4j.AutomaticGainControl
 *  de.maxhenkel.speex4j.UnknownPlatformException
 */
package mods.voicechat.natives;

import de.maxhenkel.speex4j.AutomaticGainControl;
import de.maxhenkel.speex4j.UnknownPlatformException;
import java.io.IOException;
import java.lang.ref.Cleaner;

public class Agc
implements AutoCloseable {
    private static final Cleaner CLEANER = Cleaner.create();
    private final State state;
    private final Cleaner.Cleanable cleanable;

    public Agc(int frameSize, int sampleRate) throws IOException, UnknownPlatformException {
        AutomaticGainControl agc = new AutomaticGainControl(frameSize, sampleRate);
        this.state = new State(agc);
        this.cleanable = CLEANER.register(this, this.state);
    }

    public void setTarget(int target) {
        this.state.agc.setTarget(target);
    }

    public int getTarget() {
        return this.state.agc.getTarget();
    }

    public void setMaxGain(int maxGain) {
        this.state.agc.setMaxGain(maxGain);
    }

    public int getMaxGain() {
        return this.state.agc.getMaxGain();
    }

    public void setIncrement(int increment) {
        this.state.agc.setIncrement(increment);
    }

    public int getIncrement() {
        return this.state.agc.getIncrement();
    }

    public void setDecrement(int decrement) {
        this.state.agc.setDecrement(decrement);
    }

    public int getDecrement() {
        return this.state.agc.getDecrement();
    }

    public void setVadProbStart(int probStart) {
        this.state.agc.setVadProbStart(probStart);
    }

    public int getVadProbStart() {
        return this.state.agc.getVadProbStart();
    }

    public void setVadProbContinue(int probContinue) {
        this.state.agc.setVadProbContinue(probContinue);
    }

    public int getVadProbContinue() {
        return this.state.agc.getVadProbContinue();
    }

    public boolean agc(short[] input) {
        return this.state.agc.agc(input);
    }

    @Override
    public void close() {
        this.cleanable.clean();
    }

    public boolean isClosed() {
        return this.state.agc.isClosed();
    }

    private static final class State
    implements Runnable {
        private final AutomaticGainControl agc;

        private State(AutomaticGainControl agc) {
            this.agc = agc;
        }

        @Override
        public void run() {
            this.agc.close();
        }
    }
}

