/*
 * Decompiled with CFR 0.152.
 */
package mods.chathads.config;

import java.util.Map;
import mods.chathads.config.RenderPosition;
import mods.chathads.config.SenderDetection;

public interface ChatHeadsConfig {
    public RenderPosition renderPosition();

    public boolean offsetNonPlayerText();

    public SenderDetection senderDetection();

    public boolean smartHeuristics();

    public boolean handleSystemMessages();

    public boolean drawShadow();

    public Map<String, String> getNameAliases();

    public boolean detectNameAliases();

    public float threeDeeNess();

    public void setThreeDeeNess(float var1);

    public void addNameAlias(String var1, String var2);
}

