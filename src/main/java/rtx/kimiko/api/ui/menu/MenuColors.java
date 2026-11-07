package rtx.kimiko.api.ui.menu;

public final class MenuColors {
    private MenuColors() {
    }

    public static int rgba(int r, int g, int b, int a) {
        return (clamp(a) << 24) | (clamp(r) << 16) | (clamp(g) << 8) | clamp(b);
    }

    public static int withAlpha(int color, int alpha) {
        return (color & 0x00FFFFFF) | (clamp(alpha) << 24);
    }

    public static int withAlpha(int color, float alpha) {
        return withAlpha(color, (int) (alpha * 255.0f));
    }

    private static int clamp(int v) {
        return v < 0 ? 0 : Math.min(v, 255);
    }
}
