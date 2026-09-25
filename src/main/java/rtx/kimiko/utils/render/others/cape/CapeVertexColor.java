/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.ranges.RangesKt
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.utils.render.others.cape;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.utils.render.render2d.ClientPalette;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J+\u0010\n\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0004H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\f\u0010\rJ+\u0010\u0010\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0007b\u0002\b\t\u00a2\u0006\u0004\b\u0010\u0010\u000bJ\u0017\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0011\u0010\rJ\u001f\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0013J'\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001b\u00a8\u0006\u001c"}, d2={"Lrtx/kimiko/utils/render/others/cape/CapeVertexColor;", "", "<init>", "()V", "", "x", "y", "alpha", "", "Lkotlin/jvm/JvmStatic;", "at", "(FFF)I", "plain", "(F)I", "u", "v", "atUv", "horizontal", "square", "(FF)I", "blobs", "first", "second", "value", "mix", "(IIF)I", "clamp", "(F)F", "rtx.kimiko:kimiko"})
public final class CapeVertexColor {
    @NotNull
    public static final CapeVertexColor INSTANCE = new CapeVertexColor();

    private CapeVertexColor() {
    }

    @JvmStatic
    public static final int at(float x, float y, float alpha) {
        float clampedX = INSTANCE.clamp(x);
        float clampedY = INSTANCE.clamp(y);
        int style = Math.round(ClientPalette.styleId());
        int color = switch (style) {
            case 1 -> INSTANCE.blobs(clampedX, clampedY);
            case 2 -> INSTANCE.square(clampedX, clampedY);
            default -> INSTANCE.horizontal(clampedX);
        };
        int alphaByte = RangesKt.coerceIn((int)Math.round(alpha * 255.0f), (int)0, (int)255);
        return alphaByte << 24 | color;
    }

    @JvmStatic
    public static final int plain(float alpha) {
        int alphaByte = RangesKt.coerceIn((int)Math.round(alpha * 255.0f), (int)0, (int)255);
        return alphaByte << 24 | 0xFFFFFF;
    }

    @JvmStatic
    public static final int atUv(float u, float v, float alpha) {
        float x = u >= 0.015625f && u <= 0.171875f ? (u - 0.015625f) * 6.4f : (u >= 0.1875f && u <= 0.34375f ? (0.34375f - u) * 6.4f : 0.5f);
        float y = (v - 0.03125f) * 2.0f;
        return CapeVertexColor.at(x, y, alpha);
    }

    private final int horizontal(float x) {
        return ClientPalette.loopColor(ClientPalette.scrollPhase() + x * 0.5f);
    }

    private final int square(float x, float y) {
        float phase = ClientPalette.scrollPhase();
        int top = this.mix(ClientPalette.loopColor(phase), ClientPalette.loopColor(phase + 0.25f), x);
        int bottom = this.mix(ClientPalette.loopColor(phase + 0.75f), ClientPalette.loopColor(phase + 0.5f), x);
        return this.mix(top, bottom, y);
    }

    private final int blobs(float x, float y) {
        float angle = ClientPalette.scrollPhase() * ((float)Math.PI * 2);
        float red = 0.0f;
        float green = 0.0f;
        float blue = 0.0f;
        float total = 0.0f;
        for (int i = 0; i < 6; ++i) {
            float kx = (i & 1) == 0 ? 1.0f : 2.0f;
            float ky = (i & 1) == 0 ? 2.0f : 1.0f;
            float px = 0.5f + 0.34f * (float)Math.sin(angle * kx + (float)i * 2.3999f);
            float py = 0.5f + 0.34f * (float)Math.cos(angle * ky - (float)i * 1.618f);
            float dx = (x - px) * 0.625f;
            float dy = y - py;
            float weight = 1.0f / ((dx * dx + dy * dy) * 5.0f + 0.06f);
            int color = ClientPalette.colors()[i] & 0xFFFFFF;
            red += (float)(color >>> 16 & 0xFF) * weight;
            green += (float)(color >>> 8 & 0xFF) * weight;
            blue += (float)(color & 0xFF) * weight;
            total += weight;
        }
        return Math.round(red / total) << 16 | Math.round(green / total) << 8 | Math.round(blue / total);
    }

    private final int mix(int first, int second, float value) {
        float t = this.clamp(value);
        int red = Math.round((float)(first >>> 16 & 0xFF) + (float)((second >>> 16 & 0xFF) - (first >>> 16 & 0xFF)) * t);
        int green = Math.round((float)(first >>> 8 & 0xFF) + (float)((second >>> 8 & 0xFF) - (first >>> 8 & 0xFF)) * t);
        int blue = Math.round((float)(first & 0xFF) + (float)((second & 0xFF) - (first & 0xFF)) * t);
        return red << 16 | green << 8 | blue;
    }

    private final float clamp(float value) {
        return Math.max(0.0f, Math.min(1.0f, value));
    }
}

