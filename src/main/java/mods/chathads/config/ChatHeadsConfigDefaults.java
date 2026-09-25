/*
 * Decompiled with CFR 0.152.
 */
package mods.chathads.config;

import java.util.LinkedHashMap;
import java.util.Map;
import mods.chathads.config.ChatHeadsConfig;
import mods.chathads.config.RenderPosition;
import mods.chathads.config.SenderDetection;

public class ChatHeadsConfigDefaults
implements ChatHeadsConfig {
    public static final RenderPosition RENDER_POSITION = RenderPosition.BEFORE_NAME;
    public static final boolean OFFSET_NON_PLAYER_TEXT = true;
    public static final SenderDetection SENDER_DETECTION = SenderDetection.UUID_AND_HEURISTIC;
    public static final boolean SMART_HEURISTICS = true;
    public static final boolean HANDLE_SYSTEM_MESSAGES = true;
    public static final boolean DRAW_SHADOW = true;
    public static final float THREE_DEE_NESS = 0.0f;
    public static final boolean DETECT_ALIASES = true;
    public Map<String, String> nameAliases = new LinkedHashMap<String, String>();

    @Override
    public RenderPosition renderPosition() {
        return RENDER_POSITION;
    }

    @Override
    public boolean offsetNonPlayerText() {
        return true;
    }

    @Override
    public SenderDetection senderDetection() {
        return SENDER_DETECTION;
    }

    @Override
    public boolean smartHeuristics() {
        return true;
    }

    @Override
    public boolean handleSystemMessages() {
        return true;
    }

    @Override
    public Map<String, String> getNameAliases() {
        return this.nameAliases;
    }

    @Override
    public boolean detectNameAliases() {
        return true;
    }

    @Override
    public void addNameAlias(String nickname, String profileName) {
        this.nameAliases.put(nickname, profileName);
    }

    @Override
    public boolean drawShadow() {
        return true;
    }

    @Override
    public float threeDeeNess() {
        return 0.0f;
    }

    @Override
    public void setThreeDeeNess(float value) {
        throw new UnsupportedOperationException();
    }
}

