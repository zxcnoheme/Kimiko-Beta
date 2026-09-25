/*
 * Decompiled with CFR 0.152.
 */
package mods.voicechat.api.opus;

public interface OpusEncoder {
    public byte[] encode(short[] var1);

    public void resetState();

    public boolean isClosed();

    public void close();
}

