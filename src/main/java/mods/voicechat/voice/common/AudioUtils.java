/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.voice.common;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

public class AudioUtils {
    public static final int SAMPLE_RATE = 48000;
    public static final int FRAME_SIZE = 960;
    public static final int MAX_OPUS_PAYLOAD_SIZE = 1275;
    public static final double LOWEST_DB = -127.0;
    private static final float FLOAT_SHORT_SCALE = 32767.0f;
    private static final float FLOAT_SHORT_SCALING_FACTOR = 3.051851E-5f;
    private static final float FLOAT_CLIP = 32766.0f;

    public static short[] bytesToShorts(byte[] bytes) {
        if (bytes.length % 2 != 0) {
            throw new IllegalArgumentException("Input bytes need to be divisible by 2");
        }
        ShortBuffer sb = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer();
        short[] out = new short[sb.remaining()];
        sb.get(out);
        return out;
    }

    public static byte[] shortsToBytes(short[] shorts) {
        ByteBuffer bb = ByteBuffer.allocate(shorts.length * 2).order(ByteOrder.LITTLE_ENDIAN);
        for (short s : shorts) {
            bb.putShort(s);
        }
        return bb.array();
    }

    public static short[] floatsToShortsNormalized(float[] audioData) {
        short[] shortAudioData = new short[audioData.length];
        for (int i = 0; i < audioData.length; ++i) {
            shortAudioData[i] = (short)Math.max(Math.min(audioData[i] * 32767.0f, 32766.0f), -32767.0f);
        }
        return shortAudioData;
    }

    public static float[] shortsToFloatsNormalized(short[] audioData) {
        float[] floatAudioData = new float[audioData.length];
        for (int i = 0; i < audioData.length; ++i) {
            floatAudioData[i] = (float)audioData[i] * 3.051851E-5f;
        }
        return floatAudioData;
    }

    public static short[] stereoFloatsToMonoShortsNormalized(float[] audioData) {
        short[] shortAudioData = new short[audioData.length / 2];
        for (int i = 0; i < audioData.length; i += 2) {
            shortAudioData[i / 2] = (short)Math.max(Math.min((audioData[i] + audioData[i + 1]) / 2.0f * 32767.0f, 32766.0f), -32767.0f);
        }
        return shortAudioData;
    }

    public static short[] floatsToShorts(float[] floats) {
        float max = -32768.0f;
        float min = 32767.0f;
        for (int i = 0; i < floats.length; ++i) {
            if (floats[i] > max) {
                max = floats[i];
            }
            if (!(floats[i] < min)) continue;
            min = floats[i];
        }
        float scale = Math.min(1.0f, 32766.0f / Math.max(Math.abs(max), Math.abs(min)));
        short[] shorts = new short[floats.length];
        for (int i = 0; i < floats.length; ++i) {
            shorts[i] = Float.valueOf(floats[i] * scale).shortValue();
        }
        return shorts;
    }

    public static float[] shortsToFloats(short[] shorts) {
        float[] floats = new float[shorts.length];
        for (int i = 0; i < shorts.length; ++i) {
            floats[i] = Short.valueOf(shorts[i]).floatValue();
        }
        return floats;
    }

    public static byte[] floatsToBytes(float[] floats) {
        byte[] bytes = new byte[floats.length * 2];
        for (int i = 0; i < floats.length; ++i) {
            short x = Float.valueOf(floats[i]).shortValue();
            bytes[i * 2] = (byte)(x & 0xFF);
            bytes[i * 2 + 1] = (byte)((x & 0xFF00) >> 8);
        }
        return bytes;
    }

    public static float[] bytesToFloats(byte[] bytes) {
        float[] floats = new float[bytes.length / 2];
        for (int i = 0; i < bytes.length / 2; ++i) {
            floats[i] = (bytes[i * 2 + 1] & 0x80) != 0 ? (float)(Short.MIN_VALUE + ((bytes[i * 2 + 1] & 0x7F) << 8) | bytes[i * 2] & 0xFF) : (float)(bytes[i * 2 + 1] << 8 & 0xFF00 | bytes[i * 2] & 0xFF);
        }
        return floats;
    }

    public static double getHighestAudioLevel(short[] samples) {
        int highest = 0;
        for (short sample : samples) {
            if (Math.abs(sample) <= highest) continue;
            highest = (short)Math.abs(sample);
        }
        return AudioUtils.sampleDb((short)highest);
    }

    public static int dbSample(double dbfs) {
        double value = 32768.0 * Math.pow(10.0, dbfs / 20.0);
        long rounded = Math.round(value);
        if (rounded < 0L) {
            return 0;
        }
        if (rounded > 32768L) {
            return 32768;
        }
        return (int)rounded;
    }

    public static double sampleDb(short sample) {
        if (sample == 0) {
            return -127.0;
        }
        int mag = Math.abs(sample);
        double norm = (double)mag / 32768.0;
        double db = 20.0 * Math.log10(norm);
        if (!Double.isFinite(db)) {
            return -127.0;
        }
        if (db > 0.0) {
            db = 0.0;
        }
        if (db < -127.0) {
            db = -127.0;
        }
        return db;
    }

    public static double dbToLinear(double db) {
        return Math.pow(10.0, db / 20.0);
    }

    public static double linearToDb(double multiplier) {
        if (multiplier < 0.001) {
            return -127.0;
        }
        return 20.0 * Math.log10(multiplier);
    }

    public static boolean isAboveThreshold(short[] samples, double threshold) {
        return AudioUtils.getHighestAudioLevel(samples) > threshold;
    }

    public static short[] combineAudio(Iterable<short[]> audioParts) {
        short[] result = new short[960];
        for (int i = 0; i < result.length; ++i) {
            int sample = 0;
            for (short[] audio : audioParts) {
                if (audio == null) {
                    sample += 0;
                    continue;
                }
                sample += audio[i];
            }
            result[i] = sample > Short.MAX_VALUE ? Short.MAX_VALUE : (sample < Short.MIN_VALUE ? Short.MIN_VALUE : (short)sample);
        }
        return result;
    }

    public static double dbToPerc(double db) {
        db = Math.min(Math.max(db, -127.0), 0.0);
        return (db + Math.abs(-127.0)) / Math.abs(-127.0);
    }

    public static double percToDb(double perc) {
        perc = Math.min(Math.max(perc, 0.0), 1.0);
        return (1.0 - perc) * -127.0;
    }
}

