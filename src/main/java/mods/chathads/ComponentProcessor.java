/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.datafixers.util.Pair
 *  net.minecraft.text.ObjectTextContent
 *  net.minecraft.text.object.TextObjectContents
 *  net.minecraft.text.object.PlayerTextObjectContents
 *  net.minecraft.util.Formatting
 *  net.minecraft.text.ClickEvent
 *  net.minecraft.text.ClickEvent$SuggestCommand
 *  net.minecraft.text.Text
 *  net.minecraft.text.Style
 *  net.minecraft.text.TranslatableTextContent
 *  net.minecraft.text.TextVisitFactory
 *  net.minecraft.text.MutableText
 *  net.minecraft.client.network.PlayerListEntry
 *  net.minecraft.text.TextContent
 *  net.minecraft.text.PlainTextContent
 *  net.minecraft.component.type.ProfileComponent
 *  org.jetbrains.annotations.Nullable
 */
package mods.chathads;

import com.mojang.authlib.GameProfile;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import mods.chathads.ChatHeads;
import mods.chathads.HeadData;
import net.minecraft.text.ObjectTextContent;
import net.minecraft.text.object.TextObjectContents;
import net.minecraft.text.object.PlayerTextObjectContents;
import net.minecraft.util.Formatting;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Text;
import net.minecraft.text.Style;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.text.TextVisitFactory;
import net.minecraft.text.MutableText;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.TextContent;
import net.minecraft.text.PlainTextContent;
import net.minecraft.component.type.ProfileComponent;
import org.jetbrains.annotations.Nullable;

public class ComponentProcessor {
    public static void walkTree(Text component, Style currentStyle, StyledComponentConsumer consumer) {
        currentStyle = component.getStyle().withParent(currentStyle);
        consumer.accept(component, currentStyle);
        for (Text sibling : component.getSiblings()) {
            ComponentProcessor.walkTree(sibling, currentStyle, consumer);
        }
    }

    public static void walkTree(Text component, StyledComponentConsumer consumer) {
        ComponentProcessor.walkTree(component, Style.EMPTY, consumer);
    }

    public static ArrayList<Text> split(Text component) {
        ArrayList<Text> components = new ArrayList<Text>();
        ComponentProcessor.walkTree(component, (c, cStyle) -> {
            TextContent patt0$temp = c.getContent();
            if (patt0$temp instanceof PlainTextContent) {
                PlainTextContent literal = (PlainTextContent)patt0$temp;
                Style[] prevStyle = new Style[]{cStyle};
                StringBuilder elementCodec = new StringBuilder();
                TextVisitFactory.visitFormatted((String)literal.string(), (Style)prevStyle[0], (index, style, cp) -> {
                    if (!style.equals((Object)prevStyle[0])) {
                        if (!elementCodec.isEmpty()) {
                            components.add((Text)Text.literal((String)elementCodec.toString()).setStyle(prevStyle[0]));
                        }
                        elementCodec.setLength(0);
                        prevStyle[0] = style;
                    }
                    elementCodec.appendCodePoint(cp);
                    return true;
                });
                if (!elementCodec.isEmpty()) {
                    components.add((Text)Text.literal((String)elementCodec.toString()).setStyle(prevStyle[0]));
                }
            } else {
                MutableText copy = c.copyContentOnly().setStyle(cStyle);
                components.add((Text)copy);
            }
        });
        return components;
    }

    public static Text join(List<Text> components) {
        if (components.size() == 1) {
            return components.getFirst();
        }
        MutableText combined = Text.empty();
        for (Text c : components) {
            combined.getSiblings().add(c);
        }
        return combined;
    }

    public static void walkLiteralSequencesAndTranslatables(ArrayList<Text> components, Predicate<FoundLiteralSequence> literalsCallback, Predicate<FoundTranslatable> translatableCallback) {
        StringBuilder textSequence = new StringBuilder();
        int startIndex = -1;
        int endIndex = -1;
        for (int i = 0; i < components.size(); ++i) {
            TranslatableTextContent translatableContents;
            TextContent contents = components.get(i).getContent();
            if (contents instanceof PlainTextContent) {
                PlainTextContent textContents = (PlainTextContent)contents;
                if (startIndex == -1) {
                    startIndex = i;
                }
                endIndex = i;
                textSequence.append(textContents.string());
                continue;
            }
            if (!textSequence.isEmpty() && literalsCallback.test(new FoundLiteralSequence(textSequence.toString(), startIndex, endIndex))) {
                return;
            }
            textSequence = new StringBuilder();
            startIndex = -1;
            if (!(contents instanceof TranslatableTextContent) || !translatableCallback.test(new FoundTranslatable(translatableContents = (TranslatableTextContent)contents, i))) continue;
            return;
        }
        endIndex = components.size() - 1;
        if (!textSequence.isEmpty() && literalsCallback.test(new FoundLiteralSequence(textSequence.toString(), startIndex, endIndex))) {
            return;
        }
    }

    public static String codePointSubstring(String string, int start) {
        return string.substring(string.offsetByCodePoints(0, start));
    }

    public static String codePointSubstring(String string, int start, int end) {
        int i = string.offsetByCodePoints(0, start);
        int j = string.offsetByCodePoints(i, end - start);
        return string.substring(i, j);
    }

    @Nullable
    public static Pair<MutableText, MutableText> splitLiteral(Text literal, int i) {
        if (i == 0) {
            return null;
        }
        String text = ((PlainTextContent)literal.getContent()).string();
        String leftText = ComponentProcessor.codePointSubstring(text, 0, i);
        String rightText = ComponentProcessor.codePointSubstring(text, i);
        MutableText left = Text.literal((String)leftText).setStyle(literal.getStyle());
        MutableText right = Text.literal((String)rightText).setStyle(literal.getStyle());
        literal.getSiblings().forEach(arg_0 -> ((MutableText)right).append(arg_0));
        return new Pair((Object)left, (Object)right);
    }

    @Nullable
    public static PlayerListEntry addChatHeadForClickTellCommand(ArrayList<Text> components, ChatHeads.PlayerInfoCache playerInfoCache) {
        for (int i = 0; i < components.size(); ++i) {
            final int finalI = i;
            Text c = components.get(i);
            String profileName = ComponentProcessor.getTellReceiver(c);
            if (profileName != null) {
                PlayerListEntry playerInfo2 = playerInfoCache.get(profileName);
                if (playerInfo2 != null) {
                    MutableText chatHead = ComponentProcessor.createChatHeadComponent(playerInfo2, c);
                    MutableText decorated = Text.empty().append((Text)chatHead).append(c);
                    components.set(i, (Text)decorated);
                    return playerInfo2;
                }
            }
            TextContent chatHead = c.getContent();
            if (chatHead instanceof TranslatableTextContent translatable) {
                PlayerListEntry playerInfo = ComponentProcessor.processTranslatableArguments(
                    c,
                    translatable,
                    splitArg -> ComponentProcessor.addChatHeadForClickTellCommand(splitArg, playerInfoCache),
                    decorated -> components.set(finalI, decorated)
                );
                if (playerInfo != null) {
                    return playerInfo;
                }
            }
        }
        return null;
    }

    @Nullable
    public static <T> T processTranslatableArguments(Text translatable, TranslatableTextContent contents, Function<ArrayList<Text>, @Nullable T> processSplitArg, Consumer<Text> processedCallback) {
        Object[] args = contents.getArgs();
        int argsLength = Objects.equals(contents.getKey(), "chat.type.text") ? 1 : args.length;
        for (int i = 0; i < argsLength; ++i) {
            Text argComponent;
            ArrayList<Text> splitArg;
            T returnValue;
            Object object = args[i];
            if (object instanceof String) {
                String text = (String)object;
                args[i] = Text.literal((String)text);
            }
            if (!((object = args[i]) instanceof Text) || (returnValue = processSplitArg.apply(splitArg = ComponentProcessor.split(argComponent = (Text)object))) == null) continue;
            Text processedArg = ComponentProcessor.join(splitArg);
            Object[] processedArgs = Arrays.copyOf(args, args.length);
            processedArgs[i] = processedArg;
            MutableText processed = Text.translatableWithFallback((String)contents.getKey(), (String)contents.getFallback(), (Object[])processedArgs);
            processed.setStyle(translatable.getStyle());
            translatable.getSiblings().forEach(arg_0 -> ((MutableText)processed).append(arg_0));
            processedCallback.accept((Text)processed);
            return returnValue;
        }
        return null;
    }

    public static PlayerListEntry addChatHeadForPlayerName(ArrayList<Text> components, ChatHeads.PlayerInfoCache playerInfoCache) {
        PlayerListEntry[] returnValue = new PlayerListEntry[1];
        ComponentProcessor.walkLiteralSequencesAndTranslatables(components, literals -> {
            HeadData headData = ChatHeads.scanForPlayerName(literals.text, playerInfoCache);
            if (headData == HeadData.EMPTY) {
                return false;
            }
            int codePointIndex = headData.codePointIndex;
            for (int i = literals.startIndex; i <= literals.endIndex; ++i) {
                MutableText decorated;
                Text literal = (Text)components.get(i);
                PlainTextContent contents = (PlainTextContent)literal.getContent();
                int codePointCount = (int)contents.string().codePoints().count();
                if (codePointIndex >= codePointCount) {
                    codePointIndex -= codePointCount;
                    continue;
                }
                MutableText chatHead = ComponentProcessor.createChatHeadComponent(headData.playerInfo, literal);
                Pair<MutableText, MutableText> pair = ComponentProcessor.splitLiteral(literal, codePointIndex);
                if (pair == null) {
                    decorated = Text.empty().append((Text)chatHead).append(literal);
                } else {
                    MutableText left = (MutableText)pair.getFirst();
                    MutableText right = (MutableText)pair.getSecond();
                    decorated = Text.empty().append((Text)left).append((Text)chatHead).append((Text)right);
                }
                components.set(i, (Text)decorated);
                returnValue[0] = headData.playerInfo;
                return true;
            }
            return false;
        }, foundTranslatable -> {
            TranslatableTextContent contents;
            Text translatable = (Text)components.get(foundTranslatable.index);
            PlayerListEntry foundPlayerInfo = ComponentProcessor.processTranslatableArguments(translatable, contents = foundTranslatable.contents, splitArg -> ComponentProcessor.addChatHeadForPlayerName(splitArg, playerInfoCache), decorated -> components.set(foundTranslatable.index, (Text)decorated));
            if (foundPlayerInfo != null) {
                returnValue[0] = foundPlayerInfo;
                return true;
            }
            return false;
        });
        return returnValue[0];
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean containsPlayerSprite(ArrayList<Text> components) {
        Iterator<Text> iterator = components.iterator();
        while (true) {
            if (!iterator.hasNext()) {
                return false;
            }
            Text c = iterator.next();
            TextContent textContent2 = c.getContent();
            if (!(textContent2 instanceof ObjectTextContent)) continue;
            ObjectTextContent objectTextContent2 = (ObjectTextContent)textContent2;
            try {
                // empty try
            }
            catch (Throwable throwable) {
                throw new MatchException(throwable.toString(), throwable);
            }
            TextObjectContents textObjectContents2 = objectTextContent2.contents();
            TextObjectContents objectInfo = textObjectContents2;
            if (objectInfo instanceof PlayerTextObjectContents) break;
        }
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Nullable
    public static String getTellReceiver(Text component) {
        ClickEvent clickEvent2 = component.getStyle().getClickEvent();
        if (!(clickEvent2 instanceof ClickEvent.SuggestCommand)) return null;
        ClickEvent.SuggestCommand suggestCommand2 = (ClickEvent.SuggestCommand)clickEvent2;
        try {
            String string;
            String command = string = suggestCommand2.command();
            if (command == null) return null;
            if (!command.startsWith("/tell ")) return null;
            return command.substring("/tell ".length()).trim();
        }
        catch (Throwable throwable) {
            throw new MatchException(throwable.toString(), throwable);
        }
    }

    public static MutableText createChatHeadComponent(PlayerListEntry playerInfo) {
        return Text.object((TextObjectContents)new PlayerTextObjectContents(ProfileComponent.ofStatic((GameProfile)playerInfo.getProfile()), playerInfo.shouldShowHat())).formatted(Formatting.WHITE);
    }

    public static MutableText createChatHeadComponent(PlayerListEntry playerInfo, Text message) {
        MutableText chatHead = ComponentProcessor.createChatHeadComponent(playerInfo);
        if (message.getStyle().isStrikethrough()) {
            return chatHead.formatted(Formatting.STRIKETHROUGH);
        }
        return chatHead;
    }


    public static interface StyledComponentConsumer {
        public void accept(Text var1, Style var2);
    }

    public record FoundLiteralSequence(String text, int startIndex, int endIndex) {
    }

    public record FoundTranslatable(TranslatableTextContent contents, int index) {
    }
}

