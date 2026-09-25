/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.messenger;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.api.chat.prefix.ChatPrefixes;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J)\u0010\t\u001a\u0004\u0018\u00010\u00072\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\t\u0010\nJ\u001d\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007H\u0007b\u0002\b\b\u00a2\u0006\u0004\b\r\u0010\u000eJ5\u0010\u0012\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\fH\u0007b\u0002\b\b\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\f8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\f8\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\f8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0015R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001b"}, d2={"Lrtx/kimiko/api/ui/messenger/ChatPrefixBadge;", "", "<init>", "()V", "", "prefixId", "role", "Lrtx/kimiko/api/chat/prefix/ChatPrefixes$Entry;", "Lkotlin/jvm/JvmStatic;", "resolve", "(Ljava/lang/String;Ljava/lang/String;)Lrtx/kimiko/api/chat/prefix/ChatPrefixes$Entry;", "entry", "", "width", "(Lrtx/kimiko/api/chat/prefix/ChatPrefixes$Entry;)F", "x", "y", "a", "draw", "(Lrtx/kimiko/api/chat/prefix/ChatPrefixes$Entry;FFF)F", "TEXT_SIZE", "F", "HEIGHT", "PAD_X", "", "BG", "I", "rtx.kimiko:kimiko"})
public final class ChatPrefixBadge {
    @NotNull
    public static final ChatPrefixBadge INSTANCE = new ChatPrefixBadge();
    public static final float TEXT_SIZE = 4.4f;
    public static final float HEIGHT = 7.0f;
    private static final float PAD_X = 2.6f;
    private static final int BG = 0x26FFFFFF;

    private ChatPrefixBadge() {
    }

    @JvmStatic
    @Nullable
    public static final ChatPrefixes.Entry resolve(@Nullable String prefixId, @Nullable String role) {
        ChatPrefixes.Entry entry = ChatPrefixes.forRole(role);
        if (entry == null) {
            entry = ChatPrefixes.byId(prefixId);
        }
        return entry;
    }

    @JvmStatic
    public static final float width(@Nullable ChatPrefixes.Entry entry) {
        if (entry == null) {
            return 0.0f;
        }
        return Fonts.SEMIBOLD.width(entry.label(), 4.4f) + 5.2f;
    }

    @JvmStatic
    public static final float draw(@Nullable ChatPrefixes.Entry entry, float x, float y, float a) {
        if (entry == null) {
            return 0.0f;
        }
        float w = ChatPrefixBadge.width(entry);
        float radius = 2.52f;
        Render2D.rect(x, y, w, 7.0f, radius, ColorEngine.multAlpha(0x26FFFFFF, a));
        Render2D.outline(x, y, w, 7.0f, radius, 0.5f, ColorEngine.multAlpha(ColorEngine.multAlpha(entry.from(), 0.55f), a));
        int from = ColorEngine.multAlpha(entry.from(), a);
        int to = ColorEngine.multAlpha(entry.to(), a);
        Fonts.SEMIBOLD.draw(entry.label(), x + 2.6f, y + 1.3f - 0.4f, 4.4f, from, to, to, from);
        return w;
    }
}

