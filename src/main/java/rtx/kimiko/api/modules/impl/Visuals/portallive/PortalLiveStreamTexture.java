/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.io.CloseableKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraft.client.texture.NativeImage
 *  net.minecraft.client.texture.NativeImageBackedTexture
 *  net.minecraft.client.texture.AbstractTexture
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.system.MemoryUtil
 */
package rtx.kimiko.api.modules.impl.Visuals.portallive;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.MemoryUtil;
import rtx.kimiko.Kimiko;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\b\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\u0007J\u0013\u0010\n\u001a\u00020\tH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\r\u001a\u00020\fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u0010\u001a\u00020\u000fH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0003J\u0017\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u0014H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001a\u0010\u0003J\u0013\u0010\u001b\u001a\u00020\u0014H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u001b\u0010\u0003J\u000f\u0010\u001c\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b\u001c\u0010\u0003J\u000f\u0010\u001e\u001a\u00020\u001dH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fR\u0019\u0010\"\u001a\u00020 8\u0006X\u0087\u0004\u0092\u0002\u0002\b!\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010%\u001a\u00020$8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0018\u0010'\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u001c\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120)8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010-\u001a\u00020,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u0014\u0010/\u001a\u00020,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b/\u0010.R\u0018\u00101\u001a\u0004\u0018\u0001008\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b1\u00102R\u0018\u00104\u001a\u0004\u0018\u0001038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0018\u00107\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b7\u00108R\u0018\u0010:\u001a\u0004\u0018\u0001098\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0018\u0010=\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b=\u0010>R\u0018\u0010@\u001a\u0004\u0018\u00010?8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b@\u0010AR\u0016\u0010B\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0016\u0010D\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bD\u0010CR\u0016\u0010E\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bE\u0010FR\u0016\u0010G\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bG\u0010FR\u0016\u0010H\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bH\u0010IR\u0016\u0010J\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bJ\u0010FR\u0016\u0010K\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010L\u00a8\u0006M"}, d2={"Lrtx/kimiko/api/modules/impl/Visuals/portallive/PortalLiveStreamTexture;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "receivedCount", "()I", "shownCount", "", "lastError", "()Ljava/lang/String;", "", "hasFrame", "()Z", "", "lastFrameAtMs", "()J", "", "jpeg", "", "submit", "([B)V", "drain", "decode", "([B)Z", "reset", "shutdown", "upload", "Ljava/util/concurrent/ExecutorService;", "ensureDecoder", "()Ljava/util/concurrent/ExecutorService;", "Lnet/minecraft/Identifier;", "Lkotlin/jvm/JvmField;", "TEXTURE_ID", "Lnet/minecraft/Identifier;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "decoderBusy", "Ljava/util/concurrent/atomic/AtomicBoolean;", "decoder", "Ljava/util/concurrent/ExecutorService;", "Ljava/util/concurrent/atomic/AtomicReference;", "pendingJpeg", "Ljava/util/concurrent/atomic/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicInteger;", "receivedCountVal", "Ljava/util/concurrent/atomic/AtomicInteger;", "shownCountVal", "Lnet/minecraft/NativeImage;", "work", "Lnet/minecraft/NativeImage;", "Lnet/minecraft/NativeImageBackedTexture;", "texture", "Lnet/minecraft/NativeImageBackedTexture;", "Ljavax/imageio/ImageReader;", "jpegReader", "Ljavax/imageio/ImageReader;", "Ljava/awt/image/BufferedImage;", "decodeTarget", "Ljava/awt/image/BufferedImage;", "", "pixels", "[I", "Ljava/nio/ByteBuffer;", "staging", "Ljava/nio/ByteBuffer;", "frameWidth", "I", "frameHeight", "sizeChanged", "Z", "hasFrameVal", "lastFrameAtMsVal", "J", "failed", "lastErrorVal", "Ljava/lang/String;", "rtx.kimiko:kimiko"})
public final class PortalLiveStreamTexture {
    @NotNull
    public static final PortalLiveStreamTexture INSTANCE = new PortalLiveStreamTexture();
    @JvmField
    @NotNull
    public static final Identifier TEXTURE_ID;
    @NotNull
    private static final AtomicBoolean decoderBusy;
    @Nullable
    private static volatile ExecutorService decoder;
    @NotNull
    private static final AtomicReference<byte[]> pendingJpeg;
    @NotNull
    private static final AtomicInteger receivedCountVal;
    @NotNull
    private static final AtomicInteger shownCountVal;
    @Nullable
    private static NativeImage work;
    @Nullable
    private static NativeImageBackedTexture texture;
    @Nullable
    private static ImageReader jpegReader;
    @Nullable
    private static BufferedImage decodeTarget;
    @Nullable
    private static int[] pixels;
    @Nullable
    private static ByteBuffer staging;
    private static int frameWidth;
    private static int frameHeight;
    private static boolean sizeChanged;
    private static volatile boolean hasFrameVal;
    private static volatile long lastFrameAtMsVal;
    private static boolean failed;
    @NotNull
    private static volatile String lastErrorVal;

    private PortalLiveStreamTexture() {
    }

    @JvmStatic
    public static final int receivedCount() {
        return receivedCountVal.get();
    }

    @JvmStatic
    public static final int shownCount() {
        return shownCountVal.get();
    }

    @JvmStatic
    @NotNull
    public static final String lastError() {
        return lastErrorVal;
    }

    @JvmStatic
    public static final boolean hasFrame() {
        return hasFrameVal && !failed;
    }

    @JvmStatic
    public static final long lastFrameAtMs() {
        return lastFrameAtMsVal;
    }

    @JvmStatic
    public static final void submit(@Nullable byte[] jpeg) {
        if (failed || jpeg == null || jpeg.length == 0) {
            return;
        }
        receivedCountVal.incrementAndGet();
        pendingJpeg.set(jpeg);
        INSTANCE.drain();
    }

    private final void drain() {
        if (failed || !decoderBusy.compareAndSet(false, true)) {
            return;
        }
        byte[] jpeg = pendingJpeg.getAndSet(null);
        if (jpeg == null) {
            decoderBusy.set(false);
            return;
        }
        ExecutorService service = this.ensureDecoder();
        try {
            service.execute(() -> PortalLiveStreamTexture.drain$lambda$0(jpeg));
        }
        catch (Throwable throwable) {
            decoderBusy.set(false);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final boolean decode(byte[] jpeg) {
        BufferedImage image = null;
        try {
            if (jpegReader == null) {
                Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
                if (!readers.hasNext()) {
                    lastErrorVal = "decode: нет ридера";
                    return false;
                }
                jpegReader = readers.next();
            }
            ImageReader imageReader = jpegReader;
            if (imageReader == null) {
                return false;
            }
            ImageReader reader = imageReader;
            Closeable closeable = new MemoryCacheImageInputStream(new ByteArrayInputStream(jpeg));
            Throwable throwable = null;
            try {
                MemoryCacheImageInputStream input = (MemoryCacheImageInputStream)closeable;
                boolean bl = false;
                reader.setInput(input, true, true);
                int width = reader.getWidth(0);
                int height = reader.getHeight(0);
                if (width <= 0 || height <= 0 || width > 4096 || height > 4096) {
                    lastErrorVal = "size: " + width + "x" + height;
                    boolean bl2 = false;
                    return bl2;
                }
                ImageReadParam param = reader.getDefaultReadParam();
                BufferedImage target = decodeTarget;
                if (target != null && target.getWidth() == width && target.getHeight() == height) {
                    param.setDestination(target);
                }
                image = reader.read(0, param);
// input = Unit.INSTANCE;
            }
            catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            }
            finally {
                CloseableKt.closeFinally((Closeable)closeable, (Throwable)throwable);
            }
        }
        catch (Throwable throwable) {
            decodeTarget = null;
            lastErrorVal = "decode: " + throwable;
            return false;
        }
        if (image == null) {
            lastErrorVal = "decode: null";
            return false;
        }
        int width = image.getWidth();
        int height = image.getHeight();
        ByteBuffer currentStaging = staging;
        if (currentStaging == null || frameWidth != width || frameHeight != height) {
            if (currentStaging != null) {
                MemoryUtil.memFree((Buffer)currentStaging);
            }
            pixels = null;
            staging = MemoryUtil.memAlloc((int)(width * height * 4));
            frameWidth = width;
            frameHeight = height;
            sizeChanged = true;
        }
        ByteBuffer byteBuffer = staging;
        if (byteBuffer == null) {
            return false;
        }
        ByteBuffer buf = byteBuffer;
        int total = width * height;
        DataBuffer db = image.getRaster().getDataBuffer();
        if (image.getType() == 5 && db instanceof DataBufferByte) {
            byte[] src = ((DataBufferByte)db).getData();
            int i = 0;
            int s = 0;
            int o = 0;
            while (i < total) {
                buf.put(o, src[s + 2]);
                buf.put(o + 1, src[s + 1]);
                buf.put(o + 2, src[s]);
                buf.put(o + 3, (byte)-1);
                ++i;
                s += 3;
                o += 4;
            }
            decodeTarget = image;
        } else {
            int[] px = pixels;
            if (px == null || px.length != total) {
                pixels = px = new int[total];
            }
            image.getRGB(0, 0, width, height, px, 0, width);
            int i = 0;
            int o = 0;
            while (i < total) {
                int argb = px[i];
                buf.put(o, (byte)(argb >> 16 & 0xFF));
                buf.put(o + 1, (byte)(argb >> 8 & 0xFF));
                buf.put(o + 2, (byte)(argb & 0xFF));
                buf.put(o + 3, (byte)-1);
                ++i;
                o += 4;
            }
            decodeTarget = null;
        }
        lastErrorVal = "";
        return true;
    }

    @JvmStatic
    public static final void reset() {
        hasFrameVal = false;
        failed = false;
        lastErrorVal = "";
        pendingJpeg.set(null);
        receivedCountVal.set(0);
        shownCountVal.set(0);
    }

    @JvmStatic
    public static final void shutdown() {
        ExecutorService current = decoder;
        decoder = null;
        ExecutorService executorService = current;
        if (executorService != null) {
            executorService.shutdownNow();
        }
        decoderBusy.set(false);
        hasFrameVal = false;
        failed = false;
    }

    private final void upload() {
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        NativeImage w = work;
        NativeImageBackedTexture tex = texture;
        if (w == null || tex == null || sizeChanged) {
            sizeChanged = false;
            if (tex != null) {
                mc.getTextureManager().destroyTexture(TEXTURE_ID);
            }
            work = w = new NativeImage(frameWidth, frameHeight, false);
            texture = tex = new NativeImageBackedTexture(PortalLiveStreamTexture::upload$lambda$0, w);
            mc.getTextureManager().registerTexture(TEXTURE_ID, (AbstractTexture)tex);
        }
        ByteBuffer byteBuffer = staging;
        if (byteBuffer == null) {
            return;
        }
        ByteBuffer currentStaging = byteBuffer;
        long bytes = (long)frameWidth * (long)frameHeight * 4L;
        MemoryUtil.memCopy((long)MemoryUtil.memAddress((ByteBuffer)currentStaging), (long)w.imageId(), (long)bytes);
        tex.upload();
        hasFrameVal = true;
        lastFrameAtMsVal = System.currentTimeMillis();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final ExecutorService ensureDecoder() {
        ExecutorService current = null;
        current = decoder;
        if (current == null || current.isShutdown()) {
            Class<PortalLiveStreamTexture> clazz = PortalLiveStreamTexture.class;
            synchronized (clazz) {
                boolean bl = false;
                current = decoder;
                if (current == null || current.isShutdown()) {
                    decoder = current = Executors.newSingleThreadExecutor(PortalLiveStreamTexture::ensureDecoder$lambda$0$0);
                }
                Unit unit = Unit.INSTANCE;
            }
        }
        ExecutorService executorService = current;
        Intrinsics.checkNotNull((Object)executorService);
        return executorService;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static final void drain$lambda$0$0() {
        try {
            INSTANCE.upload();
            shownCountVal.incrementAndGet();
        }
        catch (Throwable throwable) {
            failed = true;
            lastErrorVal = "upload: " + throwable;
        }
        finally {
            decoderBusy.set(false);
            INSTANCE.drain();
        }
    }

    private static final void drain$lambda$0(byte[] $jpeg) {
        if (!INSTANCE.decode($jpeg)) {
            decoderBusy.set(false);
            INSTANCE.drain();
            return;
        }
        MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
        MinecraftClient mc = minecraftClient2;
        mc.execute(PortalLiveStreamTexture::drain$lambda$0$0);
    }

    private static final String upload$lambda$0() {
        return "kimiko_portal_live_stream";
    }

    private static final Thread ensureDecoder$lambda$0$0(Runnable r) {
        Thread thread = new Thread(r, "kimiko-portal-decode");
        thread.setDaemon(true);
        return thread;
    }

    static {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"dynamic/portal_live_stream");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        TEXTURE_ID = identifier2;
        decoderBusy = new AtomicBoolean(false);
        pendingJpeg = new AtomicReference();
        receivedCountVal = new AtomicInteger();
        shownCountVal = new AtomicInteger();
        lastErrorVal = "";
    }
}

