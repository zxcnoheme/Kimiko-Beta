/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui.messenger;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.chat.messenger.MessengerClient;
import rtx.kimiko.api.chat.voice.VoiceNote;
import rtx.kimiko.api.chat.voice.VoiceNotePlayer;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.color.ColorEngine;
import rtx.kimiko.utils.render.fonts.Fonts;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ;\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000f\u0010\u0010J;\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u000eH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ'\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b\u001f\u0010 J'\u0010!\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0018H\u0002\u00a2\u0006\u0004\b!\u0010 R\u0014\u0010\"\u001a\u00020\u00068\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020\u00068\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b$\u0010#R\u0014\u0010%\u001a\u00020\u00188\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b'\u0010#R\u0014\u0010(\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b(\u0010#R\u0014\u0010)\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b)\u0010#R\u0014\u0010*\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b*\u0010#R\u0014\u0010+\u001a\u00020\u00068\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b+\u0010#R\u0014\u0010,\u001a\u00020\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010#\u00a8\u0006-"}, d2={"Lrtx/kimiko/api/ui/messenger/VoiceBubble;", "", "<init>", "()V", "Lrtx/kimiko/api/chat/messenger/MessengerClient$Message;", "msg", "", "Lkotlin/jvm/JvmStatic;", "contentWidth", "(Lrtx/kimiko/api/chat/messenger/MessengerClient$Message;)F", "x", "y", "mx", "my", "", "buttonHit", "(Lrtx/kimiko/api/chat/messenger/MessengerClient$Message;FFFF)Z", "a", "mine", "", "render", "(Lrtx/kimiko/api/chat/messenger/MessengerClient$Message;FFFZ)V", "", "peaks", "", "index", "levelAt", "([BI)F", "cx", "cy", "color", "drawPlay", "(FFI)V", "drawStop", "HEIGHT", "F", "BUTTON", "BAR_COUNT", "I", "BAR_W", "BAR_GAP", "BAR_MIN", "GAP", "TIME_SIZE", "WAVE_W", "rtx.kimiko:kimiko"})
public final class VoiceBubble {
    @NotNull
    public static final VoiceBubble INSTANCE = new VoiceBubble();
    public static final float HEIGHT = 13.0f;
    public static final float BUTTON = 12.0f;
    private static final int BAR_COUNT = 24;
    private static final float BAR_W = 1.5f;
    private static final float BAR_GAP = 1.1f;
    private static final float BAR_MIN = 1.5f;
    private static final float GAP = 3.5f;
    private static final float TIME_SIZE = 4.6f;
    private static final float WAVE_W = 61.300003f;

    private VoiceBubble() {
    }

    @JvmStatic
    public static final float contentWidth(@NotNull MessengerClient.Message msg) {
        Intrinsics.checkNotNullParameter((Object)msg, (String)"msg");
        return 15.5f + WAVE_W + 3.5f + Fonts.MEDIUM.width(VoiceNote.formatDuration(msg.voiceDur()), 4.6f);
    }

    @JvmStatic
    public static final boolean buttonHit(@NotNull MessengerClient.Message msg, float x, float y, float mx, float my) {
        Intrinsics.checkNotNullParameter((Object)msg, (String)"msg");
        return msg.voice() && mx >= x && mx <= x + 12.0f && my >= y && my <= y + 13.0f;
    }

    @JvmStatic
    public static final void render(@NotNull MessengerClient.Message msg, float x, float y, float a, boolean mine) {
        Intrinsics.checkNotNullParameter((Object)msg, (String)"msg");
        boolean playing = Intrinsics.areEqual((Object)VoiceNotePlayer.playingId(), (Object)msg.voiceId());
        float progress = playing ? VoiceNotePlayer.progress() : 0.0f;
        float centerY = y + 6.5f;
        int accent = mine ? ColorEngine.multAlpha(-1, 0.92f * a) : ColorEngine.multAlpha(ClientAccent.accentAt(255.0f, x, y), a);
        int idle = ColorEngine.multAlpha(-1, (mine ? 0.42f : 0.34f) * a);
        Render2D.circle(x + 6.0f, centerY, 6.0f, ColorEngine.multAlpha(accent, 0.22f));
        Render2D.circle(x + 6.0f, centerY, 5.4f, ColorEngine.multAlpha(accent, 0.12f));
        if (playing) {
            INSTANCE.drawStop(x + 6.0f, centerY, accent);
        } else {
            INSTANCE.drawPlay(x + 6.0f, centerY, accent);
        }
        byte[] peaks = VoiceNote.peaksFromBase64(msg.voicePeaks());
        float barX = x + 12.0f + 3.5f;
        for (int i = 0; i < 24; ++i) {
            float level = INSTANCE.levelAt(peaks, i);
            float h = Math.max(1.5f, level * 11.0f);
            int color = progress > 0.0f && (float)i / (float)24 < progress ? accent : idle;
            Render2D.rect(barX, centerY - h * 0.5f, 1.5f, h, 0.75f, color);
            barX += 2.6f;
        }
        String time = VoiceNote.formatDuration(msg.voiceDur());
        Fonts.MEDIUM.draw(time, barX + 3.5f - 1.1f, centerY - 2.3f - 0.3f, 4.6f, ColorEngine.multAlpha(-1, 0.55f * a));
    }

    private final float levelAt(byte[] peaks, int index) {
        if (peaks.length == 0) {
            return 0.35f;
        }
        int at = Math.min(peaks.length - 1, index * peaks.length / 24);
        return (float)(peaks[at] & 0xFF) / 255.0f;
    }

    private final void drawPlay(float cx, float cy, int color) {
        float h = 4.6f;
        float w = 4.0f;
        int steps = 8;
        float left = cx - w * 0.4f;
        for (int i = 0; i < steps; ++i) {
            float t = (float)i / (float)steps;
            float sliceH = h * (1.0f - t);
            Render2D.rect(left + w * t, cy - sliceH * 0.5f, w / (float)steps + 0.35f, sliceH, 0.0f, color);
        }
    }

    private final void drawStop(float cx, float cy, int color) {
        float s = 3.6f;
        Render2D.rect(cx - s * 0.5f, cy - s * 0.5f, s, s, 0.8f, color);
    }
}

