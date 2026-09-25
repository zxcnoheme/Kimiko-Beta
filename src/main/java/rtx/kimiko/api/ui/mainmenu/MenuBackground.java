/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.textures.GpuTexture
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  com.mojang.blaze3d.textures.TextureFormat
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.io.CloseableKt
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.math.MathKt
 *  net.minecraft.client.texture.NativeImage
 *  net.minecraft.client.texture.AbstractTexture
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.resource.Resource
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.mainmenu;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.ui.mainmenu.MenuTextures;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 .2\u00020\u0001:\u0003/0.B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\fJ\r\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\fJ\r\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\rJ\r\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0007\u0010\fJ\r\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\u000eJ\r\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\b\u00a2\u0006\u0004\b\u0012\u0010\u000eJ\r\u0010\u0013\u001a\u00020\b\u00a2\u0006\u0004\b\u0013\u0010\u000eJ\r\u0010\u0014\u001a\u00020\b\u00a2\u0006\u0004\b\u0014\u0010\u000eJ\u000f\u0010\u0015\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0015\u0010\fJ\u000f\u0010\u0016\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0016\u0010\fJ\u000f\u0010\u0018\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0017H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u0019J\u0011\u0010\u001c\u001a\u0004\u0018\u00010\u001bH\u0002\u00a2\u0006\u0004\b\u001c\u0010\u001dJ!\u0010 \u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001bH\u0002\u00a2\u0006\u0004\b \u0010!R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\"R\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0004\u0010\"R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010#R\u0014\u0010\u0007\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\"R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010$R\u0016\u0010&\u001a\u00020%8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0018\u0010(\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0018\u0010*\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010)R\u0018\u0010+\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u0010\"R\u0018\u0010,\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010\"R\u0016\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0010\u0010-\u00a8\u00061"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuBackground;", "", "", "id", "name", "Lrtx/kimiko/api/ui/mainmenu/MenuBackground$Kind;", "kind", "source", "", "builtIn", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lrtx/kimiko/api/ui/mainmenu/MenuBackground$Kind;Ljava/lang/String;Z)V", "()Ljava/lang/String;", "()Lrtx/kimiko/api/ui/mainmenu/MenuBackground$Kind;", "()Z", "", "aspect", "()F", "failed", "ready", "procedural", "texture", "blurred", "", "pump", "()V", "decode", "Ljava/awt/image/BufferedImage;", "readSource", "()Ljava/awt/image/BufferedImage;", "sharp", "blur", "uploadAll", "(Ljava/awt/image/BufferedImage;Ljava/awt/image/BufferedImage;)V", "Ljava/lang/String;", "Lrtx/kimiko/api/ui/mainmenu/MenuBackground$Kind;", "Z", "Lrtx/kimiko/api/ui/mainmenu/MenuBackground$State;", "state", "Lrtx/kimiko/api/ui/mainmenu/MenuBackground$State;", "pending", "Ljava/awt/image/BufferedImage;", "pendingBlur", "sharpTexture", "blurTexture", "F", "Companion", "Kind", "State", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nMenuBackground.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MenuBackground.kt\nrtx/kimiko/api/ui/mainmenu/MenuBackground\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,234:1\n1#2:235\n*E\n"})
public final class MenuBackground {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String id;
    @NotNull
    private final String name;
    @NotNull
    private final Kind kind;
    @NotNull
    private final String source;
    private final boolean builtIn;
    @NotNull
    private volatile State state;
    @Nullable
    private volatile BufferedImage pending;
    @Nullable
    private volatile BufferedImage pendingBlur;
    @Nullable
    private String sharpTexture;
    @Nullable
    private String blurTexture;
    private float aspect;
    @NotNull
    private static final AtomicInteger UPLOAD_ID = new AtomicInteger();
    private static final int BLUR_MAX = 220;
    private static final int BLUR_PASSES = 4;

    public MenuBackground(@NotNull String id, @NotNull String name, @NotNull Kind kind, @NotNull String source, boolean builtIn) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        Intrinsics.checkNotNullParameter((Object)name, (String)"name");
        Intrinsics.checkNotNullParameter((Object)((Object)kind), (String)"kind");
        Intrinsics.checkNotNullParameter((Object)source, (String)"source");
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.source = source;
        this.builtIn = builtIn;
        this.state = State.IDLE;
        this.aspect = 1.7777778f;
    }

    @NotNull
    public final String id() {
        return this.id;
    }

    @NotNull
    public final String name() {
        return this.name;
    }

    @NotNull
    public final Kind kind() {
        return this.kind;
    }

    @NotNull
    public final String source() {
        return this.source;
    }

    public final boolean builtIn() {
        return this.builtIn;
    }

    public final float aspect() {
        return this.aspect;
    }

    public final boolean failed() {
        return this.state == State.FAILED;
    }

    public final boolean ready() {
        return this.state == State.READY;
    }

    public final boolean procedural() {
        return this.kind == Kind.AURORA || this.kind == Kind.GRADIENT;
    }

    @Nullable
    public final String texture() {
        if (this.procedural()) {
            return null;
        }
        this.pump();
        return this.state == State.READY ? this.sharpTexture : null;
    }

    @Nullable
    public final String blurred() {
        if (this.procedural()) {
            return null;
        }
        this.pump();
        return this.state == State.READY ? this.blurTexture : null;
    }

    private final void pump() {
        if (this.state == State.IDLE) {
            this.state = State.LOADING;
            Thread thread = new Thread(() -> MenuBackground.pump$lambda$0(this), "kimiko-menu-bg");
            thread.setDaemon(true);
            thread.start();
            return;
        }
        if (this.state == State.DECODED) {
            BufferedImage sharp = this.pending;
            BufferedImage blur = this.pendingBlur;
            this.pending = null;
            this.pendingBlur = null;
            if (sharp == null) {
                this.state = State.FAILED;
                return;
            }
            this.state = State.LOADING;
            MinecraftClient.getInstance().execute(() -> MenuBackground.pump$lambda$1(this, sharp, blur));
        }
    }

    private final void decode() {
        try {
            BufferedImage image = this.readSource();
            if (image == null) {
                this.state = State.FAILED;
                return;
            }
            this.aspect = Math.max(0.2f, (float)image.getWidth() / (float)Math.max(1, image.getHeight()));
            this.pendingBlur = MenuBackground.Companion.blurCopy(image);
            this.pending = image;
            this.state = State.DECODED;
        }
        catch (Throwable ignored) {
            this.state = State.FAILED;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final BufferedImage readSource() {
        if (this.kind == Kind.USER) {
            Path path = Path.of(this.source, new String[0]);
            if (!Files.isRegularFile(path, new LinkOption[0])) {
                return null;
            }
            try (InputStream it = Files.newInputStream(path, new OpenOption[0])) {
                return ImageIO.read(it);
            } catch (IOException e) {
                return null;
            }
        }
        Identifier identifier = Identifier.tryParse(this.source);
        if (identifier == null) {
            return null;
        }
        Optional<Resource> resource = MinecraftClient.getInstance().getResourceManager().getResource(identifier);
        if (resource.isEmpty()) {
            return null;
        }
        try (InputStream it = resource.get().getInputStream()) {
            return ImageIO.read(it);
        } catch (IOException e) {
            return null;
        }
    }

    private final void uploadAll(BufferedImage sharp, BufferedImage blur) {
        try {
            this.sharpTexture = MenuBackground.Companion.upload(sharp, "sharp");
            BufferedImage bufferedImage = blur;
            this.blurTexture = bufferedImage == null ? this.sharpTexture : MenuBackground.Companion.upload(bufferedImage, "blur");
            this.state = State.READY;
        }
        catch (Throwable ignored) {
            this.state = State.FAILED;
        }
    }

    private static final void pump$lambda$0(MenuBackground this$0) {
        this$0.decode();
    }

    private static final void pump$lambda$1(MenuBackground this$0, BufferedImage $sharp, BufferedImage $blur) {
        this$0.uploadAll($sharp, $blur);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J7\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00178\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u000b8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001b\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuBackground.Companion;", "", "<init>", "()V", "Ljava/awt/image/BufferedImage;", "source", "blurCopy", "(Ljava/awt/image/BufferedImage;)Ljava/awt/image/BufferedImage;", "", "src", "dst", "", "w", "h", "radius", "", "boxBlur", "([I[IIII)V", "image", "", "tag", "upload", "(Ljava/awt/image/BufferedImage;Ljava/lang/String;)Ljava/lang/String;", "Ljava/util/concurrent/atomic/AtomicInteger;", "UPLOAD_ID", "Ljava/util/concurrent/atomic/AtomicInteger;", "BLUR_MAX", "I", "BLUR_PASSES", "rtx.kimiko:kimiko"})
    public static final class Companion {
        private Companion() {
        }

        private final BufferedImage blurCopy(BufferedImage source) {
            int w = source.getWidth();
            int h = source.getHeight();
            float scale = Math.min(1.0f, (float)220 / (float)Math.max(w, h));
            int tw = Math.max(8, MathKt.roundToInt((float)((float)w * scale)));
            int th = Math.max(8, MathKt.roundToInt((float)((float)h * scale)));
            BufferedImage small = new BufferedImage(tw, th, 2);
            Graphics2D g = small.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(source, 0, 0, tw, th, null);
            g.dispose();
            int[] pixels = small.getRGB(0, 0, tw, th, null, 0, tw);
            int[] temp = new int[pixels.length];
            for (int pass = 0; pass < 4; ++pass) {
                Intrinsics.checkNotNull((Object)pixels);
                this.boxBlur(pixels, temp, tw, th, 2);
                this.boxBlur(temp, pixels, th, tw, 2);
            }
            small.setRGB(0, 0, tw, th, pixels, 0, tw);
            return small;
        }

        private final void boxBlur(int[] src, int[] dst, int w, int h, int radius) {
            int span = radius * 2 + 1;
            for (int y = 0; y < h; ++y) {
                int row = y * w;
                for (int x = 0; x < w; ++x) {
                    int r = 0;
                    int g = 0;
                    int b = 0;
                    int k = -radius;
                    if (k <= radius) {
                        while (true) {
                            int sx = Math.max(0, Math.min(w - 1, x + k));
                            int c = src[row + sx];
                            r += c >> 16 & 0xFF;
                            g += c >> 8 & 0xFF;
                            b += c & 0xFF;
                            if (k == radius) break;
                            ++k;
                        }
                    }
                    dst[x * h + y] = 0xFF000000 | r / span << 16 | g / span << 8 | b / span;
                }
            }
        }

        private final String upload(BufferedImage image, String tag) {
            int w = image.getWidth();
            int h = image.getHeight();
            NativeImage nativeImage = new NativeImage(w, h, false);
            for (int y = 0; y < h; ++y) {
                for (int x = 0; x < w; ++x) {
                    nativeImage.setColorArgb(x, y, image.getRGB(x, y));
                }
            }
            int uid = UPLOAD_ID.getAndIncrement();
            GpuTexture gpuTexture = RenderSystem.getDevice().createTexture(() -> Companion.upload$lambda$0(uid), 5, TextureFormat.RGBA8, w, h, 1, 1);
            Intrinsics.checkNotNullExpressionValue((Object)gpuTexture, (String)"createTexture(...)");
            GpuTexture texture = gpuTexture;
            RenderSystem.getDevice().createCommandEncoder().writeToTexture(texture, nativeImage);
            GpuTextureView gpuTextureView = RenderSystem.getDevice().createTextureView(texture);
            Intrinsics.checkNotNullExpressionValue((Object)gpuTextureView, (String)"createTextureView(...)");
            GpuTextureView view = gpuTextureView;
            nativeImage.close();
            Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)("menu/bg_" + tag + "_" + uid));
            Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
            Identifier id = identifier2;
            MinecraftClient.getInstance().getTextureManager().registerTexture(id, (AbstractTexture)new MenuTextures.SimpleTexture(texture, view));
            String string = id.toString();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
            return string;
        }

        private static final String upload$lambda$0(int $uid) {
            return "kimiko_menu_bg_" + $uid;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuBackground$Kind;", "", "<init>", "(Ljava/lang/String;I)V", "IMAGE", "USER", "AURORA", "GRADIENT", "rtx.kimiko:kimiko"})
    public static enum Kind {
        IMAGE,
        USER,
        AURORA,
        GRADIENT;
@NotNull
        public static EnumEntries<Kind> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2={"Lrtx/kimiko/api/ui/mainmenu/MenuBackground$State;", "", "<init>", "(Ljava/lang/String;I)V", "IDLE", "LOADING", "DECODED", "READY", "FAILED", "rtx.kimiko:kimiko"})
    private static enum State {
        IDLE,
        LOADING,
        DECODED,
        READY,
        FAILED;
@NotNull
        public static EnumEntries<State> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }
}

