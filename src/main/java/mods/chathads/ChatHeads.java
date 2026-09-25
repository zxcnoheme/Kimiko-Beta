/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  net.minecraft.client.render.entity.PlayerEntityRenderer
 *  net.minecraft.client.texture.NativeImage
 *  net.minecraft.client.gl.RenderPipelines
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.text.Text
 *  net.minecraft.text.TranslatableTextContent
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.gui.hud.ChatHudLine
 *  net.minecraft.client.gui.hud.ChatHudLine$Visible
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.client.gui.DrawContext
 *  net.minecraft.client.gui.hud.ChatHud$Backend
 *  net.minecraft.text.MutableText
 *  net.minecraft.client.network.ClientPlayNetworkHandler
 *  net.minecraft.client.world.ClientWorld
 *  net.minecraft.client.network.PlayerListEntry
 *  net.minecraft.text.TextContent
 *  net.minecraft.network.message.SignedMessage
 *  net.minecraft.util.math.ColorHelper
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix3x2fStack
 */
package mods.chathads;

import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;
import mods.chathads.ComponentProcessor;
import mods.chathads.HeadData;
import mods.chathads.config.ChatHeadsConfig;
import mods.chathads.config.ChatHeadsConfigDefaults;
import mods.chathads.config.RenderPosition;
import mods.chathads.config.SenderDetection;
import mods.chathads.mixininterface.HeadRenderable;
import mods.chathads.mixininterface.Ownable;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Identifier;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.MutableText;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.TextContent;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.util.math.ColorHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2fStack;

public class ChatHeads {
    public static final String MOD_ID = "chat_heads";
    public static final Pattern FORMAT_REGEX = Pattern.compile("\u00a7.");
    public static final Logger LOGGER = LogManager.getLogger((String)"chat_heads");
    public static final Identifier DISABLE_RESOURCE = Identifier.of((String)"chat_heads", (String)"disable");
    public static ChatHeadsConfig CONFIG = new ChatHeadsConfigDefaults();
    @NotNull
    public static HeadData lastSenderData = HeadData.EMPTY;
    public static boolean refreshing;
    @NotNull
    public static HeadData lineData;
    @NotNull
    public static HeadData refreshingLineData;
    public static volatile boolean serverSentUuid;
    public static volatile boolean serverDisabledChatHeads;
    public static final Set<Identifier> blendedHeadTextures;
    public static DrawContext guiGraphics;
    public static ChatHud.Backend chatGraphicsAccess;
    public static boolean customHeadRendering;

    public static void init() {
    }

    @NotNull
    public static HeadData getLineData() {
        return refreshing ? refreshingLineData : lineData;
    }

    public static void setLineData(@NotNull HeadData headData) {
        if (refreshing) {
            refreshingLineData = headData;
        } else {
            lineData = headData;
        }
    }

    public static void autoDetectAlias(Text message) {
        String text = message.getString();
        int i = text.indexOf(" ");
        if (i == -1) {
            return;
        }
        String nickname = text.substring(0, i);
        if (!text.substring(i).startsWith(" is ")) {
            return;
        }
        String profileName = text.substring(i + " is ".length());
        if (profileName.contains(" ")) {
            return;
        }
        CONFIG.addNameAlias(nickname, profileName);
    }

    public static Text handleAddedMessage(Text originalMessage, @Nullable PlayerListEntry playerInfo) {
        HeadData headData;
        if (CONFIG.detectNameAliases()) {
            ChatHeads.autoDetectAlias(originalMessage);
        }
        lastSenderData = HeadData.EMPTY;
        if (serverDisabledChatHeads) {
            return originalMessage;
        }
        boolean forceHeuristic = ChatHeads.isShowcaseItemMessage(originalMessage);
        if (CONFIG.senderDetection() == SenderDetection.HEURISTIC_ONLY || forceHeuristic) {
            playerInfo = null;
        } else if (playerInfo != null) {
            serverSentUuid = true;
        } else if (CONFIG.senderDetection() == SenderDetection.UUID_ONLY || serverSentUuid && CONFIG.smartHeuristics()) {
            return originalMessage;
        }
        Pair<Text, HeadData> messageAndData = ChatHeads.detectPlayerAndAddChatHead(originalMessage, playerInfo);
        if (messageAndData == null) {
            return originalMessage;
        }
        Text decoratedMessage = (Text)messageAndData.getFirst();
        lastSenderData = headData = (HeadData)messageAndData.getSecond();
        if (CONFIG.renderPosition() == RenderPosition.BEFORE_LINE) {
            return originalMessage;
        }
        return decoratedMessage;
    }

    @Nullable
    public static Pair<Text, HeadData> detectPlayerAndAddChatHead(Text message, @Nullable PlayerListEntry givenPlayerInfo) {
        ClientPlayNetworkHandler connection = MinecraftClient.getInstance().getNetworkHandler();
        if (connection == null) {
            return null;
        }
        PlayerInfoCache playerInfoCache = new PlayerInfoCache(connection);
        if (givenPlayerInfo != null) {
            playerInfoCache.addProfileName(givenPlayerInfo);
        } else {
            playerInfoCache.collectProfileNames();
        }
        ArrayList<Text> split = ComponentProcessor.split(message);
        if (ComponentProcessor.containsPlayerSprite(split)) {
            return null;
        }
        PlayerListEntry foundPlayerInfo = ComponentProcessor.addChatHeadForClickTellCommand(split, playerInfoCache);
        if (foundPlayerInfo != null) {
            return new Pair((Object)ComponentProcessor.join(split), (Object)HeadData.of(foundPlayerInfo));
        }
        if (givenPlayerInfo != null) {
            playerInfoCache.add(givenPlayerInfo);
        } else {
            playerInfoCache.collectAllNames();
        }
        foundPlayerInfo = ComponentProcessor.addChatHeadForPlayerName(split, playerInfoCache);
        if (foundPlayerInfo != null) {
            return new Pair((Object)ComponentProcessor.join(split), (Object)HeadData.of(foundPlayerInfo));
        }
        if (givenPlayerInfo != null) {
            MutableText chatHead = ComponentProcessor.createChatHeadComponent(givenPlayerInfo, message);
            MutableText decorated = Text.empty().append((Text)chatHead).append(message);
            return new Pair((Object)decorated, (Object)HeadData.of(givenPlayerInfo));
        }
        return null;
    }

    private static boolean isShowcaseItemMessage(Text message) {
        TranslatableTextContent contents;
        TextContent textContent2 = message.getContent();
        return textContent2 instanceof TranslatableTextContent && Objects.equals((contents = (TranslatableTextContent)textContent2).getKey(), "showcaseitem.misc.shared_item");
    }

    @NotNull
    public static HeadData getHeadData(@NotNull ChatHudLine.Visible guiMessage) {
        return ((HeadRenderable)(Object)guiMessage).chatheads$getHeadData();
    }

    @NotNull
    public static HeadData getHeadData(@NotNull ChatHudLine guiMessage) {
        return ((HeadRenderable)(Object)guiMessage).chatheads$getHeadData();
    }

    @Nullable
    public static PlayerListEntry getOwner(@NotNull SignedMessage message) {
        return ((Ownable)(Object)message).chatheads$getOwner();
    }

    public static void setHeadData(@NotNull ChatHudLine guiMessage, @NotNull HeadData data) {
        ((HeadRenderable)(Object)guiMessage).chatheads$setHeadData(data);
    }

    public static void setOwner(@NotNull SignedMessage message, PlayerListEntry owner) {
        ((Ownable)(Object)message).chatheads$setOwner(owner);
    }

    public static boolean offsetChat(@NotNull HeadData headData) {
        if (CONFIG.renderPosition() != RenderPosition.BEFORE_LINE) {
            return false;
        }
        return headData != HeadData.EMPTY || CONFIG.offsetNonPlayerText() && !serverDisabledChatHeads;
    }

    public static int getChatOffset(@NotNull HeadData headData) {
        return ChatHeads.offsetChat(headData) ? ChatHeads.headWidth() : 0;
    }

    public static int getTextWidthDifference(@NotNull ChatHudLine.Visible guiMessage) {
        return ChatHeads.getTextWidthDifference(ChatHeads.getHeadData(guiMessage));
    }

    public static int getTextWidthDifference(@NotNull HeadData headData) {
        if (CONFIG.renderPosition() != RenderPosition.BEFORE_LINE) {
            return 0;
        }
        return headData != HeadData.EMPTY || ChatHeads.offsetChat(headData) ? ChatHeads.headWidth() : 0;
    }

    public static int headWidth() {
        return ChatHeads.headWidth(CONFIG.drawShadow());
    }

    public static int headWidth(boolean drawShadow) {
        return 10 + (drawShadow ? 1 : 0);
    }

    @NotNull
    public static HeadData scanForPlayerName(@NotNull String message, PlayerInfoCache playerInfoCache) {
        Map<Integer, List<String>> namesByFirstCharacter = playerInfoCache.createNamesByFirstCharacterMap();
        boolean insideWord = false;
        int[] messageSeq = message.codePoints().toArray();
        for (int i = 0; i < messageSeq.length; ++i) {
            int c = messageSeq[i];
            if (insideWord && ChatHeads.isWordCharacter(c)) continue;
            for (String name : namesByFirstCharacter.getOrDefault(c, List.of())) {
                boolean nameIsFollowedByWord;
                int[] nameSeq = name.codePoints().toArray();
                if (i + nameSeq.length - 1 >= messageSeq.length) continue;
                boolean nameEndsAsWord = ChatHeads.isWordCharacter(nameSeq[nameSeq.length - 1]);
                boolean bl = nameIsFollowedByWord = i + nameSeq.length < messageSeq.length && ChatHeads.isWordCharacter(messageSeq[i + nameSeq.length]);
                if (nameEndsAsWord && nameIsFollowedByWord || !ChatHeads.containsSubsequenceAt(messageSeq, i, nameSeq)) continue;
                return new HeadData(playerInfoCache.get(name), i);
            }
            insideWord = ChatHeads.isWordCharacter(c);
        }
        return HeadData.EMPTY;
    }

    private static boolean isWordCharacter(int codePoint) {
        return Character.isLetterOrDigit(codePoint) || codePoint == 95 || Character.getNumericValue(codePoint) != -1;
    }

    private static boolean containsSubsequenceAt(int[] sequence, int startIndex, int[] subsequence) {
        for (int j = 0; j < subsequence.length; ++j) {
            if (sequence[startIndex + j] == subsequence[j]) continue;
            return false;
        }
        return true;
    }

    public static NativeImage extractBlendedHead(NativeImage skin) {
        boolean isLegacy = skin.getWidth() / 2 == skin.getHeight();
        int xScale = skin.getWidth() / 64;
        int yScale = skin.getHeight() / (isLegacy ? 32 : 64);
        NativeImage head = new NativeImage(8 * xScale, 8 * yScale, false);
        for (int y = 0; y < head.getHeight(); ++y) {
            for (int x = 0; x < head.getWidth(); ++x) {
                int headColor = skin.getColorArgb(8 * xScale + x, 8 * yScale + y);
                int hatColor = skin.getColorArgb(40 * xScale + x, 8 * yScale + y);
                head.setColorArgb(x, y, ChatHeads.blendColors(headColor, hatColor));
            }
        }
        return head;
    }

    public static int blendColors(int color1, int color2) {
        float a1 = (float)ColorHelper.getAlpha((int)color1) / 255.0f;
        float r1 = (float)ColorHelper.getRed((int)color1) / 255.0f;
        float g1 = (float)ColorHelper.getGreen((int)color1) / 255.0f;
        float b1 = (float)ColorHelper.getBlue((int)color1) / 255.0f;
        float a2 = (float)ColorHelper.getAlpha((int)color2) / 255.0f;
        float r2 = (float)ColorHelper.getRed((int)color2) / 255.0f;
        float g2 = (float)ColorHelper.getGreen((int)color2) / 255.0f;
        float b2 = (float)ColorHelper.getBlue((int)color2) / 255.0f;
        float a3 = a2 * a2 + (1.0f - a2) * a1;
        float r3 = a2 * r2 + (1.0f - a2) * r1;
        float g3 = a2 * g2 + (1.0f - a2) * g1;
        float b3 = a2 * b2 + (1.0f - a2) * b1;
        return ColorHelper.getArgb((int)((int)Math.clamp(a3 * 255.0f, 0.0f, 255.0f)), (int)((int)Math.clamp(r3 * 255.0f, 0.0f, 255.0f)), (int)((int)Math.clamp(g3 * 255.0f, 0.0f, 255.0f)), (int)((int)Math.clamp(b3 * 255.0f, 0.0f, 255.0f)));
    }

    public static Identifier getBlendedHeadLocation(Identifier skinLocation) {
        return Identifier.of((String)MOD_ID, (String)skinLocation.getPath());
    }

    public static void renderChatHead(DrawContext guiGraphics, int x, int y, PlayerListEntry owner, float opacity) {
        ChatHeads.renderChatHead(guiGraphics, x, y, owner, opacity, CONFIG.drawShadow());
    }

    public static void renderChatHead(DrawContext guiGraphics, int x, int y, PlayerListEntry owner, float opacity, boolean drawShadow) {
        boolean threeDee;
        Identifier skinLocation = owner.getSkinTextures().body().texturePath();
        int color = ColorHelper.getWhite((float)opacity);
        int shadowColor = ColorHelper.scaleRgb((int)color, (float)0.25f);
        int shadowOffset = drawShadow ? -1 : 0;
        ClientWorld level = MinecraftClient.getInstance().world;
        PlayerEntity player = level != null ? level.getPlayerByUuid(owner.getProfile().id()) : null;
        boolean upsideDown = player != null && PlayerEntityRenderer.shouldFlipUpsideDown((PlayerEntity)player);
        boolean showHat = owner.shouldShowHat();
        int yOffset = upsideDown ? 8 : 0;
        int yDirection = upsideDown ? -1 : 1;
        boolean bl = threeDee = CONFIG.threeDeeNess() != 0.0f;
        if (showHat && !threeDee && blendedHeadTextures.contains(skinLocation)) {
            if (drawShadow) {
                guiGraphics.drawTexture(RenderPipelines.GUI_TEXTURED, ChatHeads.getBlendedHeadLocation(skinLocation), x + 1, y, 0.0f, (float)yOffset, 8, 8, 8, yDirection * 8, 8, 8, shadowColor);
            }
            guiGraphics.drawTexture(RenderPipelines.GUI_TEXTURED, ChatHeads.getBlendedHeadLocation(skinLocation), x, y + shadowOffset, 0.0f, (float)yOffset, 8, 8, 8, yDirection * 8, 8, 8, color);
        } else {
            Matrix3x2fStack pose = guiGraphics.getMatrices();
            BiConsumer<Integer, Integer> pushAndScale = (x0, y0) -> pose.pushMatrix().scaleAround(1.0f + CONFIG.threeDeeNess() * 0.25f, (float)x0.intValue() + 4.0f, (float)y0.intValue() + 4.0f);
            if (drawShadow) {
                guiGraphics.drawTexture(RenderPipelines.GUI_TEXTURED, skinLocation, x + 1, y, 8.0f, (float)(8 + yOffset), 8, 8, 8, yDirection * 8, 64, 64, shadowColor);
                if (showHat) {
                    if (threeDee) {
                        pushAndScale.accept(x + 1, y);
                    }
                    guiGraphics.drawTexture(RenderPipelines.GUI_TEXTURED, skinLocation, x + 1, y, 40.0f, (float)(8 + yOffset), 8, 8, 8, yDirection * 8, 64, 64, shadowColor);
                    if (threeDee) {
                        pose.popMatrix();
                    }
                }
            }
            guiGraphics.drawTexture(RenderPipelines.GUI_TEXTURED, skinLocation, x, y + shadowOffset, 8.0f, (float)(8 + yOffset), 8, 8, 8, yDirection * 8, 64, 64, color);
            if (showHat) {
                if (threeDee) {
                    pushAndScale.accept(x, y + shadowOffset);
                }
                guiGraphics.drawTexture(RenderPipelines.GUI_TEXTURED, skinLocation, x, y + shadowOffset, 40.0f, (float)(8 + yOffset), 8, 8, 8, yDirection * 8, 64, 64, color);
                if (threeDee) {
                    pose.popMatrix();
                }
            }
        }
    }

    static {
        lineData = HeadData.EMPTY;
        refreshingLineData = HeadData.EMPTY;
        serverSentUuid = false;
        serverDisabledChatHeads = false;
        blendedHeadTextures = new HashSet<Identifier>();
        guiGraphics = null;
        chatGraphicsAccess = null;
    }

    public static class PlayerInfoCache {
        private final ClientPlayNetworkHandler connection;
        private final Map<String, PlayerListEntry> playerInfos = new HashMap<String, PlayerListEntry>();
        private boolean collectedProfileNames = false;
        private boolean collectedEverything = false;

        public PlayerInfoCache(@NotNull ClientPlayNetworkHandler connection) {
            this.connection = connection;
        }

        public void collectProfileNames() {
            if (this.collectedProfileNames) {
                return;
            }
            this.collectedProfileNames = true;
            for (PlayerListEntry playerInfo : this.connection.getPlayerList()) {
                this.addProfileName(playerInfo);
            }
        }

        private void addProfileName(PlayerListEntry playerInfo) {
            String profileName = FORMAT_REGEX.matcher(playerInfo.getProfile().name()).replaceAll("");
            if (profileName.isEmpty()) {
                return;
            }
            this.playerInfos.put(profileName, playerInfo);
        }

        public void collectAllNames() {
            if (this.collectedEverything) {
                return;
            }
            this.collectedEverything = true;
            this.collectProfileNames();
            for (PlayerListEntry playerInfo : this.connection.getPlayerList()) {
                this.addDisplayName(playerInfo);
            }
            this.addNameAliases();
        }

        private void addNameAliases() {
            for (Map.Entry<String, String> entry : CONFIG.getNameAliases().entrySet()) {
                PlayerListEntry playerInfo = this.playerInfos.get(entry.getValue());
                if (playerInfo == null) continue;
                this.playerInfos.putIfAbsent(entry.getKey(), playerInfo);
            }
        }

        private void addDisplayName(PlayerListEntry playerInfo) {
            if (playerInfo.getDisplayName() != null) {
                String displayName = FORMAT_REGEX.matcher(playerInfo.getDisplayName().getString()).replaceAll("");
                if (displayName.isEmpty()) {
                    return;
                }
                this.playerInfos.putIfAbsent(displayName, playerInfo);
            }
        }

        public void add(PlayerListEntry playerInfo) {
            this.addProfileName(playerInfo);
            this.addDisplayName(playerInfo);
            this.addNameAliases();
        }

        public Map<Integer, List<String>> createNamesByFirstCharacterMap() {
            HashMap<Integer, List<String>> namesByFirstCharacter = new HashMap<Integer, List<String>>();
            for (String name : this.playerInfos.keySet()) {
                namesByFirstCharacter.compute(name.codePointAt(0), (key, value) -> {
                    if (value == null) {
                        value = new ArrayList<String>();
                    }
                    value.add(name);
                    return value;
                });
            }
            return namesByFirstCharacter;
        }

        @Nullable
        public PlayerListEntry get(@NotNull String name) {
            return this.playerInfos.get(name);
        }

        public Set<String> getNames() {
            return this.playerInfos.keySet();
        }
    }
}

