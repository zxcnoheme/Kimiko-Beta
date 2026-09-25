/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  org.jetbrains.annotations.NotNull
 */
package rtx.kimiko.api.ui.logo;

import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import rtx.kimiko.api.ui.logo.DashAtlas;
import rtx.kimiko.api.ui.theme.ClientAccent;
import rtx.kimiko.utils.render.render2d.Render2D;
import rtx.kimiko.utils.render.render2d.image.ImageRenderer;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 ?2\u00020\u0001:\u0002@?B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003J\r\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJE\u0010\u0011\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t\u00a2\u0006\u0004\b\u0011\u0010\u0012JE\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\t\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\t\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0015\u0010!\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\t\u00a2\u0006\u0004\b!\u0010\u001fJ\u001f\u0010%\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b%\u0010&JG\u0010'\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b'\u0010\u0012J\u000f\u0010(\u001a\u00020\"H\u0002\u00a2\u0006\u0004\b(\u0010)J\u0017\u0010+\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b+\u0010,J\u0017\u0010-\u001a\u00020\t2\u0006\u0010$\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b-\u0010.J\u0017\u00100\u001a\u00020\t2\u0006\u0010/\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b0\u0010.J\u001f\u00103\u001a\u00020\u00152\u0006\u00101\u001a\u00020\u00152\u0006\u00102\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b3\u00104R\u001a\u00106\u001a\b\u0012\u0004\u0012\u00020\"058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b6\u00107R\u0016\u00108\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b8\u00109R\u0016\u0010:\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010=\u001a\u00020<8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b=\u0010>\u00a8\u0006A"}, d2={"Lrtx/kimiko/api/ui/logo/LogoTrail;", "", "<init>", "()V", "", "clear", "", "idle", "()Z", "", "fromX", "fromY", "toX", "toY", "velocityX", "velocityY", "energy", "emit", "(FFFFFFF)V", "x", "y", "", "count", "power", "dirX", "dirY", "cone", "burst", "(FFIFFFF)V", "dt", "update", "(F)V", "alpha", "render", "Lrtx/kimiko/api/ui/logo/LogoTrail$Dash;", "dash", "t", "spriteOf", "(Lrtx/kimiko/api/ui/logo/LogoTrail$Dash;F)I", "spawn", "obtain", "()Lrtx/kimiko/api/ui/logo/LogoTrail$Dash;", "index", "drop", "(I)V", "fadeCurve", "(F)F", "value", "smoothstep", "color", "amount", "mixWhite", "(IF)I", "", "dashes", "[Lrtx/kimiko/api/ui/logo/LogoTrail$Dash;", "dashCount", "I", "emitCarry", "F", "Ljava/util/Random;", "random", "Ljava/util/Random;", "Companion", "Dash", "rtx.kimiko:kimiko"})
public final class LogoTrail {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Dash[] dashes;
    private int dashCount;
    private float emitCarry;
    @NotNull
    private final Random random;
    private static final int MAX_DASHES = 110;
    private static final int MAX_PER_FRAME = 8;
    private static final float EMIT_STEP = 3.8f;
    private static final float INHERIT = 0.22f;
    private static final float DRAG = 3.0f;
    private static final float MIN_SIZE = 5.0f;
    private static final float SIZE_SPREAD = 7.0f;
    private static final float FADE_IN = 0.16f;
    private static final float FADE_OUT = 0.6f;
    private static final float TWO_PI = (float)Math.PI * 2;

    public LogoTrail() {
        Dash[] dashArray = new Dash[110];
        for (int i = 0; i < 110; i++) {
            dashArray[i] = new Dash();
        }
        this.dashes = dashArray;
        this.random = new Random(24301L);
    }

    public final void clear() {
        this.dashCount = 0;
        this.emitCarry = 0.0f;
    }

    public final boolean idle() {
        return this.dashCount == 0;
    }

    public final void emit(float fromX, float fromY, float toX, float toY, float velocityX, float velocityY, float energy) {
        if (energy <= 0.02f) {
            return;
        }
        float dx = toX - fromX;
        float dy = toY - fromY;
        float travelled = (float)Math.sqrt(dx * dx + dy * dy);
        if (travelled < 0.01f) {
            return;
        }
        float dirX = dx / travelled;
        float dirY = dy / travelled;
        float step = 3.8f / (0.4f + 0.6f * energy);
        this.emitCarry += travelled;
        for (int guard = 0; this.emitCarry >= step && guard < 8; ++guard) {
            this.emitCarry -= step;
            float back = Math.min(1.0f, this.emitCarry / travelled);
            this.spawn(toX - dx * back, toY - dy * back, dirX, dirY, velocityX * 0.22f, velocityY * 0.22f, energy);
        }
        if (this.emitCarry > step) {
            this.emitCarry = step;
        }
    }

    public final void burst(float x, float y, int count, float power, float dirX, float dirY, float cone) {
        float base = dirX * dirX + dirY * dirY < 1.0E-4f ? 0.0f : (float)Math.atan2(dirY, dirX);
        for (int i = 0; i < count; ++i) {
            float angle = cone >= 6.2331853f ? this.random.nextFloat() * ((float)Math.PI * 2) : base + (this.random.nextFloat() - 0.5f) * cone;
            float speed = power * (0.3f + this.random.nextFloat() * 0.9f);
            float ax = (float)Math.cos(angle);
            float ay = (float)Math.sin(angle);
            this.spawn(x + ax * 2.0f, y + ay * 2.0f, ax, ay, ax * speed, ay * speed, 1.0f);
        }
    }

    public final void update(float dt) {
        int i = 0;
        while (i < this.dashCount) {
            Dash d = this.dashes[i];
            d.setAge(d.getAge() + dt);
            if (d.getAge() >= d.getLife()) {
                this.drop(i);
                continue;
            }
            float drag = (float)Math.exp(-dt * 3.0f);
            d.setVx(d.getVx() * drag);
            d.setVy(d.getVy() * drag);
            d.setX(d.getX() + d.getVx() * dt);
            d.setY(d.getY() + d.getVy() * dt);
            d.setRotation(d.getRotation() + d.getSpin() * dt);
            ++i;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void render(float alpha) {
        if (alpha <= 0.004f || this.dashCount == 0) {
            return;
        }
        ImageRenderer.Companion.setAdditive(true);
        try {
            int n = this.dashCount;
            for (int i = 0; i < n; ++i) {
                Dash d = this.dashes[i];
                float t = d.getAge() / d.getLife();
                float fade = this.fadeCurve(t) * d.getBright() * alpha;
                if (fade <= 0.006f) continue;
                int sprite = this.spriteOf(d, t);
                float grow = 0.62f + 0.38f * (1.0f - t);
                float height = d.getSize() * grow;
                float width = height * DashAtlas.INSTANCE.getASPECT()[sprite];
                float diagonal = (float)Math.sqrt(width * width + height * height);
                float bloomSize = diagonal * (1.15f + d.getBloom() * (1.0f - t));
                int bloomColor = this.mixWhite(ClientAccent.gradientColorAt(d.getColorT(), (50.0f + 44.0f * (1.0f - t)) * fade, d.getX(), d.getY()), 0.15f);
                Render2D.imageUv("dashtrail/dashcubics.png", d.getX() - bloomSize * 0.5f, d.getY() - bloomSize * 0.5f, bloomSize, bloomSize, 0.0f, 0.0f, DashAtlas.INSTANCE.getU0()[135], DashAtlas.INSTANCE.getV0()[135], DashAtlas.INSTANCE.getU1()[135], DashAtlas.INSTANCE.getV1()[135], bloomColor);
                int color = ClientAccent.gradientColorAt(d.getColorT(), 245.0f * fade, d.getX(), d.getY());
                Render2D.imageUv("dashtrail/dashcubics.png", d.getX() - width * 0.5f, d.getY() - height * 0.5f, width, height, 0.0f, 0.0f, DashAtlas.INSTANCE.getU0()[sprite], DashAtlas.INSTANCE.getV0()[sprite], DashAtlas.INSTANCE.getU1()[sprite], DashAtlas.INSTANCE.getV1()[sprite], color, d.getRotation(), d.getX(), d.getY());
            }
        }
        finally {
            ImageRenderer.Companion.setAdditive(false);
        }
    }

    private final int spriteOf(Dash dash, float t) {
        if (dash.getGroup() < 0) {
            return dash.getSprite();
        }
        int count = DashAtlas.INSTANCE.getGROUP_COUNT()[dash.getGroup()];
        int frame = (int)(t * (float)count);
        if (frame < 0) {
            frame = 0;
        }
        if (frame >= count) {
            frame = count - 1;
        }
        return DashAtlas.INSTANCE.getGROUP_FIRST()[dash.getGroup()] + frame;
    }

    private final void spawn(float x, float y, float dirX, float dirY, float velocityX, float velocityY, float energy) {
        boolean animated;
        Dash d = this.obtain();
        boolean bl = animated = this.random.nextInt(100) > 40;
        if (animated && !(DashAtlas.INSTANCE.getGROUP_COUNT().length == 0)) {
            d.setGroup(this.random.nextInt(DashAtlas.INSTANCE.getGROUP_COUNT().length));
            d.setSprite(DashAtlas.INSTANCE.getGROUP_FIRST()[d.getGroup()]);
        } else {
            d.setGroup(-1);
            d.setSprite(this.random.nextInt(21));
        }
        d.setX(x + (this.random.nextFloat() - 0.5f) * 4.5f);
        d.setY(y + (this.random.nextFloat() - 0.5f) * 4.5f);
        d.setSize((5.0f + this.random.nextFloat() * 7.0f) * (0.6f + 0.4f * energy));
        d.setRotation((float)Math.toDegrees((float)Math.atan2(dirY, dirX)) + (this.random.nextFloat() - 0.5f) * 26.0f);
        d.setSpin((this.random.nextFloat() - 0.5f) * 180.0f);
        d.setLife(0.34f + this.random.nextFloat() * 0.46f);
        d.setAge(0.0f);
        d.setColorT(this.random.nextFloat());
        d.setBright(0.6f + this.random.nextFloat() * 0.4f);
        d.setBloom(1.1f + this.random.nextFloat() * 1.0f);
        float perpX = -dirY;
        float perpY = dirX;
        float drift = (this.random.nextFloat() - 0.5f) * 34.0f;
        d.setVx(velocityX + perpX * drift);
        d.setVy(velocityY + perpY * drift);
    }

    private final Dash obtain() {
        if (this.dashCount < 110) {
            int n = this.dashCount;
            this.dashCount = n + 1;
            return this.dashes[n];
        }
        int worst = 0;
        float worstT = -1.0f;
        int n = this.dashCount;
        for (int i = 0; i < n; ++i) {
            float t = this.dashes[i].getAge() / this.dashes[i].getLife();
            if (!(t > worstT)) continue;
            worstT = t;
            worst = i;
        }
        return this.dashes[worst];
    }

    private final void drop(int index) {
        int last = this.dashCount - 1;
        Dash tmp = this.dashes[index];
        this.dashes[index] = this.dashes[last];
        this.dashes[last] = tmp;
        this.dashCount = last;
    }

    private final float fadeCurve(float t) {
        float rise = this.smoothstep(Math.min(1.0f, t / 0.16f));
        float fall = this.smoothstep(Math.min(1.0f, (1.0f - t) / 0.6f));
        return Math.max(0.0f, rise * fall);
    }

    private final float smoothstep(float value) {
        float t = value < 0.0f ? 0.0f : (value > 1.0f ? 1.0f : value);
        return t * t * (3.0f - 2.0f * t);
    }

    private final int mixWhite(int color, float amount) {
        int a = color >>> 24 & 0xFF;
        int r = color >> 16 & 0xFF;
        int g = color >> 8 & 0xFF;
        int b = color & 0xFF;
        r += (int)((float)(255 - r) * amount);
        g += (int)((float)(255 - g) * amount);
        b += (int)((float)(255 - b) * amount);
        return a << 24 | r << 16 | g << 8 | b;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\nR\u0014\u0010\f\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\f\u0010\nR\u0014\u0010\r\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\r\u0010\nR\u0014\u0010\u000e\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\nR\u0014\u0010\u000f\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\nR\u0014\u0010\u0010\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\nR\u0014\u0010\u0011\u001a\u00020\b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\n\u00a8\u0006\u0012"}, d2={"Lrtx/kimiko/api/ui/logo/LogoTrail.Companion;", "", "<init>", "()V", "", "MAX_DASHES", "I", "MAX_PER_FRAME", "", "EMIT_STEP", "F", "INHERIT", "DRAG", "MIN_SIZE", "SIZE_SPREAD", "FADE_IN", "FADE_OUT", "TWO_PI", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0019\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\u000b\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\"\u0010\u000e\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u0006\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR\"\u0010\u0011\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\u0006\u001a\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\nR\"\u0010\u0014\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0014\u0010\u0006\u001a\u0004\b\u0015\u0010\b\"\u0004\b\u0016\u0010\nR\"\u0010\u0017\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0017\u0010\u0006\u001a\u0004\b\u0018\u0010\b\"\u0004\b\u0019\u0010\nR\"\u0010\u001a\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001a\u0010\u0006\u001a\u0004\b\u001b\u0010\b\"\u0004\b\u001c\u0010\nR\"\u0010\u001e\u001a\u00020\u001d8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\"\u0010$\u001a\u00020\u001d8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b$\u0010\u001f\u001a\u0004\b%\u0010!\"\u0004\b&\u0010#R\"\u0010'\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b'\u0010\u0006\u001a\u0004\b(\u0010\b\"\u0004\b)\u0010\nR\"\u0010*\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b*\u0010\u0006\u001a\u0004\b+\u0010\b\"\u0004\b,\u0010\nR\"\u0010-\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b-\u0010\u0006\u001a\u0004\b.\u0010\b\"\u0004\b/\u0010\nR\"\u00100\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b0\u0010\u0006\u001a\u0004\b1\u0010\b\"\u0004\b2\u0010\nR\"\u00103\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b3\u0010\u0006\u001a\u0004\b4\u0010\b\"\u0004\b5\u0010\n\u00a8\u00066"}, d2={"Lrtx/kimiko/api/ui/logo/LogoTrail$Dash;", "", "<init>", "()V", "", "x", "F", "getX", "()F", "setX", "(F)V", "y", "getY", "setY", "vx", "getVx", "setVx", "vy", "getVy", "setVy", "rotation", "getRotation", "setRotation", "spin", "getSpin", "setSpin", "size", "getSize", "setSize", "", "sprite", "I", "getSprite", "()I", "setSprite", "(I)V", "group", "getGroup", "setGroup", "colorT", "getColorT", "setColorT", "bright", "getBright", "setBright", "bloom", "getBloom", "setBloom", "age", "getAge", "setAge", "life", "getLife", "setLife", "rtx.kimiko:kimiko"})
    private static final class Dash {
        private float x;
        private float y;
        private float vx;
        private float vy;
        private float rotation;
        private float spin;
        private float size;
        private int sprite;
        private int group = -1;
        private float colorT;
        private float bright = 1.0f;
        private float bloom = 1.0f;
        private float age;
        private float life = 1.0f;

        public final float getX() {
            return this.x;
        }

        public final void setX(float f) {
            this.x = f;
        }

        public final float getY() {
            return this.y;
        }

        public final void setY(float f) {
            this.y = f;
        }

        public final float getVx() {
            return this.vx;
        }

        public final void setVx(float f) {
            this.vx = f;
        }

        public final float getVy() {
            return this.vy;
        }

        public final void setVy(float f) {
            this.vy = f;
        }

        public final float getRotation() {
            return this.rotation;
        }

        public final void setRotation(float f) {
            this.rotation = f;
        }

        public final float getSpin() {
            return this.spin;
        }

        public final void setSpin(float f) {
            this.spin = f;
        }

        public final float getSize() {
            return this.size;
        }

        public final void setSize(float f) {
            this.size = f;
        }

        public final int getSprite() {
            return this.sprite;
        }

        public final void setSprite(int n) {
            this.sprite = n;
        }

        public final int getGroup() {
            return this.group;
        }

        public final void setGroup(int n) {
            this.group = n;
        }

        public final float getColorT() {
            return this.colorT;
        }

        public final void setColorT(float f) {
            this.colorT = f;
        }

        public final float getBright() {
            return this.bright;
        }

        public final void setBright(float f) {
            this.bright = f;
        }

        public final float getBloom() {
            return this.bloom;
        }

        public final void setBloom(float f) {
            this.bloom = f;
        }

        public final float getAge() {
            return this.age;
        }

        public final void setAge(float f) {
            this.age = f;
        }

        public final float getLife() {
            return this.life;
        }

        public final void setLife(float f) {
            this.life = f;
        }
    }
}

