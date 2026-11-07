package rtx.kimiko.api.ui.mainmenu;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.resource.Resource;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rtx.kimiko.Kimiko;

/**
 * Один фон главного меню. Декодирование и ресайз целиком идут на фоновом воркере,
 * на рендер-потоке выполняется только быстрая заливка текстуры в GPU.
 */
public final class MenuBackground {
    @NotNull
    public static final Companion Companion = new Companion();
    @NotNull
    private final String id;
    @NotNull
    private final String name;
    @NotNull
    private final Kind kind;
    @NotNull
    private final String source;
    private final boolean builtIn;

    private final Object lock = new Object();
    private State stage;
    private volatile float aspect;
    @Nullable
    private BufferedImage pendingPreview;
    @Nullable
    private BufferedImage pendingFull;
    @Nullable
    private BufferedImage pendingBlur;
    @Nullable
    private volatile BufferedImage blurSource;
    @Nullable
    private volatile String previewTexture;
    @Nullable
    private volatile String fullTexture;
    @Nullable
    private volatile String blurTexture;
    private boolean uploadedPreview;
    private boolean uploadedFull;
    private boolean uploadedBlur;
    private volatile boolean wantFull;
    private volatile boolean wantBlur;
    private boolean fullInFlight;

    @NotNull
    private static final AtomicInteger UPLOAD_ID = new AtomicInteger();
    private static final int BLUR_MAX = 220;
    private static final int BLUR_PASSES = 4;
    private static final int PREVIEW_MAX = 640;
    private static final ThreadPoolExecutor DECODERS;

    public MenuBackground(@NotNull String id, @NotNull String name, @NotNull Kind kind, @NotNull String source, boolean builtIn) {
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.source = source;
        this.builtIn = builtIn;
        this.stage = State.IDLE;
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
        return this.stage == State.FAILED;
    }

    public final boolean ready() {
        return this.stage == State.READY;
    }

    public final boolean loading() {
        return this.stage == State.QUEUED || this.stage == State.DECODING
                || this.stage == State.DECODED || this.stage == State.APPLYING;
    }

    public final boolean procedural() {
        return this.kind == Kind.AURORA || this.kind == Kind.GRADIENT;
    }

    /** Мелкая (до 640px) копия — для превью в галерее. */
    @Nullable
    public final String preview() {
        if (this.procedural() || this.failed()) {
            return null;
        }
        this.pump();
        return this.previewTexture;
    }

    /** Полный размер — для полноэкранного фона главного меню. */
    @Nullable
    public final String texture() {
        if (this.procedural() || this.failed()) {
            return null;
        }
        this.wantFull = true;
        this.pump();
        if (this.fullTexture != null) {
            return this.fullTexture;
        }
        this.requestFullUpload();
        return null;
    }

    @Nullable
    public final String blurred() {
        if (this.procedural() || this.failed()) {
            return null;
        }
        this.wantBlur = true;
        this.pump();
        if (this.blurTexture != null) {
            return this.blurTexture;
        }
        if (this.stage == State.READY && this.blurSource != null) {
            MinecraftClient.getInstance().execute(() -> MenuBackground.this.uploadBlurNow());
        }
        return null;
    }

    private final void pump() {
        synchronized (this.lock) {
            if (this.stage == State.IDLE) {
                this.stage = State.QUEUED;
                MenuBackground.DECODERS.execute(() -> MenuBackground.this.decodeTask());
                return;
            }
            if (this.stage == State.DECODED) {
                this.stage = State.APPLYING;
                MinecraftClient.getInstance().execute(() -> MenuBackground.this.apply());
            }
        }
    }

    private final void decodeTask() {
        synchronized (this.lock) {
            if (this.stage != State.QUEUED) {
                return;
            }
            this.stage = State.DECODING;
        }
        try {
            BufferedImage image = this.readSource();
            if (image == null) {
                synchronized (this.lock) {
                    this.stage = State.FAILED;
                }
                return;
            }
            this.aspect = Math.max(0.2f, (float) image.getWidth() / (float) Math.max(1, image.getHeight()));
            BufferedImage preview = Companion.scaleTo(image, PREVIEW_MAX);
            BufferedImage blurImage = Companion.blurCopy(image);
            synchronized (this.lock) {
                this.blurSource = blurImage;
                this.pendingBlur = blurImage;
                this.pendingPreview = preview;
                if (this.wantFull) {
                    this.pendingFull = image;
                }
                this.stage = State.DECODED;
            }
            MinecraftClient.getInstance().execute(() -> MenuBackground.this.pump());
        }
        catch (Throwable ignored) {
            synchronized (this.lock) {
                this.stage = State.FAILED;
            }
        }
    }

    private final void apply() {
        BufferedImage preview;
        BufferedImage full;
        BufferedImage blur;
        synchronized (this.lock) {
            if (this.stage != State.APPLYING) {
                return;
            }
            preview = this.pendingPreview;
            full = this.pendingFull;
            blur = this.pendingBlur;
            this.pendingPreview = null;
            this.pendingFull = null;
            this.pendingBlur = null;
            if (preview == null && !this.uploadedPreview) {
                this.stage = State.FAILED;
                return;
            }
        }
        try {
            if (preview != null && !this.uploadedPreview) {
                this.previewTexture = Companion.upload(preview, "prev");
                this.uploadedPreview = true;
            }
            if (full != null && this.wantFull && !this.uploadedFull) {
                this.fullTexture = Companion.upload(full, "sharp");
                this.uploadedFull = true;
            }
            if (blur != null && this.wantBlur && !this.uploadedBlur) {
                this.blurTexture = Companion.upload(blur, "blur");
                this.uploadedBlur = true;
            }
            synchronized (this.lock) {
                this.stage = State.READY;
            }
        }
        catch (Throwable ignored) {
            synchronized (this.lock) {
                this.stage = State.FAILED;
            }
        }
    }

    /** Полный размер понадобился уже после готовности — дозагружаем в фоне. */
    private final void requestFullUpload() {
        synchronized (this.lock) {
            if (this.stage != State.READY || this.uploadedFull || this.fullInFlight) {
                return;
            }
            this.fullInFlight = true;
        }
        MenuBackground.DECODERS.execute(() -> MenuBackground.this.decodeFullTask());
    }

    private final void decodeFullTask() {
        try {
            BufferedImage image = this.readSource();
            if (image == null) {
                this.fullInFlight = false;
                return;
            }
            synchronized (this.lock) {
                this.pendingFull = image;
                this.stage = State.DECODED;
            }
            MinecraftClient.getInstance().execute(() -> MenuBackground.this.applyPendingPieces());
        }
        catch (Throwable ignored) {
            this.fullInFlight = false;
        }
    }

    /** Догружает всё, что «попросили», после того как декод уже был готов. */
    private final void applyPendingPieces() {
        BufferedImage full;
        synchronized (this.lock) {
            if (this.stage != State.DECODED) {
                return;
            }
            full = this.pendingFull;
            this.pendingFull = null;
            if (full == null) {
                this.fullInFlight = false;
                return;
            }
            this.stage = State.APPLYING;
        }
        try {
            if (!this.uploadedFull) {
                this.fullTexture = Companion.upload(full, "sharp");
                this.uploadedFull = true;
            }
            synchronized (this.lock) {
                this.stage = State.READY;
            }
        }
        catch (Throwable ignored) {
            synchronized (this.lock) {
                this.stage = State.FAILED;
            }
        }
        finally {
            this.fullInFlight = false;
        }
    }

    /** Размытая копия (мелкая) — выгружается мгновенно, безопасно вызывать из кадра. */
    private final void uploadBlurNow() {
        if (this.stage != State.READY || this.uploadedBlur) {
            return;
        }
        BufferedImage blur = this.blurSource;
        if (blur == null) {
            return;
        }
        try {
            this.blurTexture = Companion.upload(blur, "blur");
            this.uploadedBlur = true;
        }
        catch (Throwable ignored) {
        }
    }

    private final BufferedImage readSource() {
        if (this.kind == Kind.USER) {
            Path path = Path.of(this.source, new String[0]);
            if (!Files.isRegularFile(path, new LinkOption[0])) {
                return null;
            }
            try (InputStream it = Files.newInputStream(path, new OpenOption[0])) {
                return ImageIO.read(it);
            }
            catch (IOException e) {
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
        try (InputStream it = ((Resource) resource.get()).getInputStream()) {
            return ImageIO.read(it);
        }
        catch (IOException e) {
            return null;
        }
    }

    public static final class Companion {
        private Companion() {
        }

        /** Вписывает картинку так, чтобы длинная сторона не превосходила max. */
        private final BufferedImage scaleTo(BufferedImage source, int max) {
            int w = source.getWidth();
            int h = source.getHeight();
            float scale = Math.min(1.0f, (float) max / (float) Math.max(w, h));
            int tw = Math.max(8, (int) Math.rint((float) w * scale));
            int th = Math.max(8, (int) Math.rint((float) h * scale));
            if (tw == w && th == h) {
                return source;
            }
            BufferedImage out = new BufferedImage(tw, th, 2);
            Graphics2D g = out.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            g.drawImage(source, 0, 0, tw, th, null);
            g.dispose();
            return out;
        }

        private final BufferedImage blurCopy(BufferedImage source) {
            BufferedImage small = this.scaleTo(source, BLUR_MAX);
            int tw = small.getWidth();
            int th = small.getHeight();
            int[] pixels = small.getRGB(0, 0, tw, th, null, 0, tw);
            int[] temp = new int[pixels.length];
            for (int pass = 0; pass < BLUR_PASSES; ++pass) {
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
            try {
                for (int y = 0; y < h; ++y) {
                    for (int x = 0; x < w; ++x) {
                        nativeImage.setColorArgb(x, y, image.getRGB(x, y));
                    }
                }
                int uid = UPLOAD_ID.getAndIncrement();
                GpuTexture gpuTexture = RenderSystem.getDevice().createTexture(() -> Companion.upload$lambda$0(uid), 5, TextureFormat.RGBA8, w, h, 1, 1);
                RenderSystem.getDevice().createCommandEncoder().writeToTexture(gpuTexture, nativeImage);
                GpuTextureView view = RenderSystem.getDevice().createTextureView(gpuTexture);
                Identifier id = Identifier.of(Kimiko.Companion.namespace(), "menu/bg_" + tag + "_" + uid);
                MinecraftClient.getInstance().getTextureManager().registerTexture(id, (AbstractTexture) new MenuTextures.SimpleTexture(gpuTexture, view));
                return id.toString();
            }
            finally {
                nativeImage.close();
            }
        }

        private static final String upload$lambda$0(int $uid) {
            return "kimiko_menu_bg_" + $uid;
        }
    }

    public static enum Kind {
        IMAGE,
        USER,
        AURORA,
        GRADIENT
    }

    private static enum State {
        IDLE,
        QUEUED,
        DECODING,
        DECODED,
        APPLYING,
        READY,
        FAILED
    }

    private static final class DecodeFactory implements ThreadFactory {
        @Override
        @NotNull
        public Thread newThread(@NotNull Runnable r) {
            Thread thread = new Thread(r, "kimiko-menu-bg-decode");
            thread.setDaemon(true);
            return thread;
        }
    }

    static {
        DecodeFactory factory = new DecodeFactory();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), factory);
        executor.allowCoreThreadTimeOut(true);
        DECODERS = executor;
    }
}
