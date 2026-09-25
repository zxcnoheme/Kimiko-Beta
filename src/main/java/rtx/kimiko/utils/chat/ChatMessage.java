/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.RangesKt
 *  net.minecraft.util.Formatting
 *  net.minecraft.text.Text
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.text.MutableText
 *  net.minecraft.client.network.ClientPlayerEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.chat;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.util.Formatting;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.modules.impl.Interface.InterfaceModule;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\u0006\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u000bJ\u001b\u0010\u0006\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u000eJ\u001d\u0010\u0010\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u001b\u0010\u000e\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/utils/chat/ChatMessage;", "", "<init>", "()V", "Lnet/minecraft/MutableText;", "Lkotlin/jvm/JvmStatic;", "brandmessage", "()Lnet/minecraft/MutableText;", "", "message", "", "(Ljava/lang/String;)V", "Lnet/minecraft/Text;", "component", "(Lnet/minecraft/Text;)V", "text", "accentGradient", "(Ljava/lang/String;)Lnet/minecraft/MutableText;", "", "accentPalette", "()[I", "palette", "", "t", "", "samplePalette", "([IF)I", "send", "rtx.kimiko:kimiko"})
public final class ChatMessage {
    @NotNull
    public static final ChatMessage INSTANCE = new ChatMessage();

    private ChatMessage() {
    }

    @JvmStatic
    @NotNull
    public static final MutableText brandmessage() {
        return ChatMessage.accentGradient("[Kimiko] ");
    }

    @JvmStatic
    public static final void brandmessage(@NotNull String message) {
        Intrinsics.checkNotNullParameter((Object)message, (String)"message");
        MutableText mutableText2 = ChatMessage.brandmessage().append((Text)Text.literal((String)message).formatted(Formatting.WHITE));
        Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"append(...)");
        INSTANCE.send((Text)mutableText2);
    }

    @JvmStatic
    public static final void brandmessage(@NotNull Text component) {
        Intrinsics.checkNotNullParameter((Object)component, (String)"component");
        MutableText mutableText2 = ChatMessage.brandmessage().append(component);
        Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"append(...)");
        INSTANCE.send((Text)mutableText2);
    }

    @JvmStatic
    @NotNull
    public static final MutableText accentGradient(@Nullable String text) {
        String string = text;
        if (string == null) {
            string = "";
        }
        String value = string;
        int[] palette = INSTANCE.accentPalette();
        MutableText mutableText2 = Text.empty();
        Intrinsics.checkNotNullExpressionValue((Object)mutableText2, (String)"empty(...)");
        MutableText out = mutableText2;
        int length = value.length();
        for (int i = 0; i < length; ++i) {
            float t = length <= 1 ? 0.0f : (float)i / (float)(length - 1);
            out.append((Text)Text.literal((String)String.valueOf(value.charAt(i))).withColor(INSTANCE.samplePalette(palette, t)));
        }
        return out;
    }

    private final int[] accentPalette() {
        try {
            int[] palette;
            InterfaceModule iface = InterfaceModule.Companion.getInstance();
            if (iface != null && (palette = iface.clientPalette()) != null && !(palette.length == 0)) {
                return palette;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        int[] nArray = new int[]{0xFFFFFF};
        return nArray;
    }

    private final int samplePalette(int[] palette, float t) {
        if (palette.length == 1) {
            return palette[0] & 0xFFFFFF;
        }
        float f = RangesKt.coerceIn((float)t, (float)0.0f, (float)1.0f) * (float)(palette.length - 1);
        int idx = Math.min(palette.length - 2, (int)f);
        float k = f - (float)idx;
        int a = palette[idx];
        int b = palette[idx + 1];
        int r = Math.round((float)(a >> 16 & 0xFF) + (float)((b >> 16 & 0xFF) - (a >> 16 & 0xFF)) * k);
        int g = Math.round((float)(a >> 8 & 0xFF) + (float)((b >> 8 & 0xFF) - (a >> 8 & 0xFF)) * k);
        int bl = Math.round((float)(a & 0xFF) + (float)((b & 0xFF) - (a & 0xFF)) * k);
        return r << 16 | g << 8 | bl;
    }

    private final void send(Text component) {
        block0: {
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
            MinecraftClient mc = minecraftClient2;
            ClientPlayerEntity clientPlayerEntity2 = mc.player;
            if (clientPlayerEntity2 == null) break block0;
            clientPlayerEntity2.sendMessage(component, false);
        }
    }
}

