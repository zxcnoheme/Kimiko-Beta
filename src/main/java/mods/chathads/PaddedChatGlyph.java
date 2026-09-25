/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.font.GlyphMetrics
 */
package mods.chathads;

import mods.chathads.ChatHeads;
import net.minecraft.client.font.GlyphMetrics;

public class PaddedChatGlyph
implements GlyphMetrics {
    public GlyphMetrics glyphInfo;

    public PaddedChatGlyph(GlyphMetrics original) {
        this.glyphInfo = original;
    }

    public float getAdvance() {
        return this.glyphInfo.getAdvance() + (ChatHeads.customHeadRendering ? 1.0f + 2.0f * ChatHeads.CONFIG.threeDeeNess() : 0.0f);
    }
}

