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
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  net.minecraft.client.texture.NativeImage
 *  net.minecraft.client.texture.AbstractTexture
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.resource.ResourceManager
 *  net.minecraft.client.gui.DrawContext
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.utils.render.render2d.gif;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourceManager;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Node;
import rtx.kimiko.utils.render.render2d.Render2D;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u000201B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJU\u0010\u0013\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\fH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u0006H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0015\u0010\u0003J\u0017\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J!\u0010\u001d\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u001eJ!\u0010\"\u001a\u00020 2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00042\u0006\u0010!\u001a\u00020 H\u0002\u00a2\u0006\u0004\b\"\u0010#R0\u0010'\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020%0$j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020%`&8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010*\u001a\u00020)8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u001c\u0010.\u001a\n -*\u0004\u0018\u00010,0,8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b.\u0010/\u00a8\u00062"}, d2={"Lrtx/kimiko/utils/render/render2d/gif/GifRenderer;", "", "<init>", "()V", "", "path", "", "Lkotlin/jvm/JvmStatic;", "preload", "(Ljava/lang/String;)V", "Lnet/minecraft/DrawContext;", "graphics", "", "x", "y", "w", "h", "radius", "alpha", "draw", "(Lnet/minecraft/DrawContext;FFFFFLjava/lang/String;F)V", "shutdown", "Ljava/awt/image/BufferedImage;", "src", "copyImage", "(Ljava/awt/image/BufferedImage;)Ljava/awt/image/BufferedImage;", "Ljavax/imageio/metadata/IIOMetadataNode;", "root", "name", "findNode", "(Ljavax/imageio/metadata/IIOMetadataNode;Ljava/lang/String;)Ljavax/imageio/metadata/IIOMetadataNode;", "s", "", "fallback", "parseInt", "(Ljava/lang/String;I)I", "Ljava/util/HashMap;", "Lrtx/kimiko/utils/render/render2d/gif/GifRenderer$GifAnimation;", "Lkotlin/collections/HashMap;", "CACHE", "Ljava/util/HashMap;", "Ljava/util/concurrent/atomic/AtomicInteger;", "ID_COUNTER", "Ljava/util/concurrent/atomic/AtomicInteger;", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "LOADER", "Ljava/util/concurrent/ExecutorService;", "GifAnimation", "GifFrameTexture", "rtx.kimiko:kimiko"})
public final class GifRenderer {
    @NotNull
    public static final GifRenderer INSTANCE = new GifRenderer();
    @NotNull
    private static final HashMap<String, GifAnimation> CACHE = new HashMap();
    @NotNull
    private static final AtomicInteger ID_COUNTER = new AtomicInteger(0);
    private static final ExecutorService LOADER = Executors.newSingleThreadExecutor(GifRenderer::LOADER$lambda$0);

    private GifRenderer() {
    }

    @JvmStatic
    public static final void preload(@NotNull String path) {
        Intrinsics.checkNotNullParameter((Object)path, (String)"path");
        CACHE.computeIfAbsent(path, p -> {
            GifAnimation anim = new GifAnimation();
            anim.startLoad(p);
            return anim;
        });
    }

    @JvmStatic
    public static final void draw(@Nullable DrawContext graphics, float x, float y, float w, float h, float radius, @NotNull String path, float alpha) {
        Intrinsics.checkNotNullParameter((Object)path, (String)"path");
        GifAnimation anim = CACHE.computeIfAbsent(path, p -> {
            GifAnimation a = new GifAnimation();
            a.startLoad(p);
            return a;
        });
        if (anim.getState() != GifAnimation.State.GPU_READY) {
            return;
        }
        String frameId = anim.getCurrentFrameId();
        if (frameId == null) {
            return;
        }
        int a = Math.max(0, Math.min(255, (int)(alpha * (float)255)));
        Render2D.image(frameId, x, y, w, h, radius, a << 24 | 0xFFFFFF);
    }

    @JvmStatic
    public static final void shutdown() {
        LOADER.shutdownNow();
        Iterator<GifAnimation> iterator = CACHE.values().iterator();
        while (iterator.hasNext()) {
            GifAnimation anim = (GifAnimation) (iterator.next());
            anim.dispose();
        }
        CACHE.clear();
    }

    private final BufferedImage copyImage(BufferedImage src) {
        BufferedImage copy = new BufferedImage(src.getWidth(), src.getHeight(), 2);
        Graphics2D graphics2D = copy.createGraphics();
        Intrinsics.checkNotNullExpressionValue((Object)graphics2D, (String)"createGraphics(...)");
        Graphics2D g = graphics2D;
        g.drawImage((Image)src, 0, 0, null);
        g.dispose();
        return copy;
    }

    private final IIOMetadataNode findNode(IIOMetadataNode root, String name) {
        int i;
        int n = root.getLength();
        for (i = 0; i < n; ++i) {
            if (!StringsKt.equals((String)root.item(i).getNodeName(), (String)name, (boolean)true)) continue;
            Node node = root.item(i);
            Intrinsics.checkNotNull((Object)node, (String)"null cannot be cast to non-null type javax.imageio.metadata.IIOMetadataNode");
            return (IIOMetadataNode)node;
        }
        n = root.getLength();
        for (i = 0; i < n; ++i) {
            IIOMetadataNode found;
            Node child = root.item(i);
            if (!(child instanceof IIOMetadataNode) || (found = this.findNode((IIOMetadataNode)child, name)) == null) continue;
            return found;
        }
        return null;
    }

    private final int parseInt(String s, int fallback) {
        int n;
        if (s == null) {
            return fallback;
        }
        try {
            n = Integer.parseInt(s);
        }
        catch (Exception e) {
            n = fallback;
        }
        return n;
    }

    private static final Thread LOADER$lambda$0(Runnable r) {
        Thread t = new Thread(r, "kimiko-gif-loader");
        t.setDaemon(true);
        return t;
    }

    private static final GifAnimation preload$lambda$0(String p) {
        Intrinsics.checkNotNullParameter((Object)p, (String)"p");
        GifAnimation anim = new GifAnimation();
        anim.startLoad(p);
        return anim;
    }

    private static final GifAnimation preload$lambda$1(Function1 $tmp0, Object p0) {
        return (GifAnimation)$tmp0.invoke(p0);
    }

    private static final GifAnimation draw$lambda$0(String p) {
        Intrinsics.checkNotNullParameter((Object)p, (String)"p");
        GifAnimation a = new GifAnimation();
        a.startLoad(p);
        return a;
    }

    private static final GifAnimation draw$lambda$1(Function1 $tmp0, Object p0) {
        return (GifAnimation)$tmp0.invoke(p0);
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001:\u00011B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\t\u0010\bJ\u000f\u0010\n\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\n\u0010\u0003J\u0017\u0010\r\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u0006\u00a2\u0006\u0004\b\u0011\u0010\u0003R\"\u0010\u0013\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R'\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u001a0\u0019j\b\u0012\u0004\u0012\u00020\u001a`\u001b8\u0006\u00a2\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR'\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0019j\b\u0012\u0004\u0012\u00020\u000b`\u001b8\u0006\u00a2\u0006\f\n\u0004\b \u0010\u001d\u001a\u0004\b!\u0010\u001fR'\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0019j\b\u0012\u0004\u0012\u00020\u0004`\u001b8\u0006\u00a2\u0006\f\n\u0004\b\"\u0010\u001d\u001a\u0004\b#\u0010\u001fR'\u0010$\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0019j\b\u0012\u0004\u0012\u00020\u000b`\u001b8\u0006\u00a2\u0006\f\n\u0004\b$\u0010\u001d\u001a\u0004\b%\u0010\u001fR'\u0010'\u001a\u0012\u0012\u0004\u0012\u00020&0\u0019j\b\u0012\u0004\u0012\u00020&`\u001b8\u0006\u00a2\u0006\f\n\u0004\b'\u0010\u001d\u001a\u0004\b(\u0010\u001fR'\u0010*\u001a\u0012\u0012\u0004\u0012\u00020)0\u0019j\b\u0012\u0004\u0012\u00020)`\u001b8\u0006\u00a2\u0006\f\n\u0004\b*\u0010\u001d\u001a\u0004\b+\u0010\u001fR\"\u0010,\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u0010\u000e\u00a8\u00062"}, d2={"Lrtx/kimiko/utils/render/render2d/gif/GifRenderer$GifAnimation;", "", "<init>", "()V", "", "path", "", "startLoad", "(Ljava/lang/String;)V", "decodeGif", "scheduleGpuUploads", "", "idx", "uploadSingleFrame", "(I)V", "getCurrentFrameId", "()Ljava/lang/String;", "dispose", "Lrtx/kimiko/utils/render/render2d/gif/GifRenderer$GifAnimation$State;", "state", "Lrtx/kimiko/utils/render/render2d/gif/GifRenderer$GifAnimation$State;", "getState", "()Lrtx/kimiko/utils/render/render2d/gif/GifRenderer$GifAnimation$State;", "setState", "(Lrtx/kimiko/utils/render/render2d/gif/GifRenderer$GifAnimation$State;)V", "Ljava/util/ArrayList;", "Ljava/awt/image/BufferedImage;", "Lkotlin/collections/ArrayList;", "rawFrames", "Ljava/util/ArrayList;", "getRawFrames", "()Ljava/util/ArrayList;", "rawDelays", "getRawDelays", "frames", "getFrames", "frameDelays", "getFrameDelays", "Lcom/mojang/blaze3d/textures/GpuTexture;", "gpuTextures", "getGpuTextures", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "gpuViews", "getGpuViews", "totalDuration", "I", "getTotalDuration", "()I", "setTotalDuration", "State", "rtx.kimiko:kimiko"})
    @SourceDebugExtension(value={"SMAP\nGifRenderer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GifRenderer.kt\nrtx/kimiko/utils/render/render2d/gif/GifRenderer$GifAnimation\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,296:1\n2068#2,2:297\n2068#2,2:299\n*S KotlinDebug\n*F\n+ 1 GifRenderer.kt\nrtx/kimiko/utils/render/render2d/gif/GifRenderer$GifAnimation\n*L\n240#1:297,2\n241#1:299,2\n*E\n"})
    private static final class GifAnimation {
        @NotNull
        private volatile State state = State.IDLE;
        @NotNull
        private final ArrayList<BufferedImage> rawFrames = new ArrayList();
        @NotNull
        private final ArrayList<Integer> rawDelays = new ArrayList();
        @NotNull
        private final ArrayList<String> frames = new ArrayList();
        @NotNull
        private final ArrayList<Integer> frameDelays = new ArrayList();
        @NotNull
        private final ArrayList<GpuTexture> gpuTextures = new ArrayList();
        @NotNull
        private final ArrayList<GpuTextureView> gpuViews = new ArrayList();
        private int totalDuration;

        @NotNull
        public final State getState() {
            return this.state;
        }

        public final void setState(@NotNull State state) {
            Intrinsics.checkNotNullParameter((Object)((Object)state), (String)"<set-?>");
            this.state = state;
        }

        @NotNull
        public final ArrayList<BufferedImage> getRawFrames() {
            return this.rawFrames;
        }

        @NotNull
        public final ArrayList<Integer> getRawDelays() {
            return this.rawDelays;
        }

        @NotNull
        public final ArrayList<String> getFrames() {
            return this.frames;
        }

        @NotNull
        public final ArrayList<Integer> getFrameDelays() {
            return this.frameDelays;
        }

        @NotNull
        public final ArrayList<GpuTexture> getGpuTextures() {
            return this.gpuTextures;
        }

        @NotNull
        public final ArrayList<GpuTextureView> getGpuViews() {
            return this.gpuViews;
        }

        public final int getTotalDuration() {
            return this.totalDuration;
        }

        public final void setTotalDuration(int n) {
            this.totalDuration = n;
        }

        public final void startLoad(@NotNull String path) {
            Intrinsics.checkNotNullParameter((Object)path, (String)"path");
            this.state = State.LOADING;
            LOADER.submit(() -> GifAnimation.startLoad$lambda$0(this, path));
        }

        private final void decodeGif(String path) throws Exception {
            String string;
            if (path != null && path.contains(":")) {
                String[] parts = path.split(":", 2);
                string = "/assets/" + parts[0] + "/" + parts[1];
            } else {
                string = "/assets/kimiko/" + path;
            }
            String resourcePath = string;
            InputStream is = GifRenderer.class.getResourceAsStream(resourcePath);
            if (is == null) {
                this.state = State.IDLE;
                return;
            }
            ImageInputStream iis = ImageIO.createImageInputStream(is);
            ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
            reader.setInput(iis, false, false);
            int gifW = reader.getWidth(0);
            int gifH = reader.getHeight(0);
            int count = reader.getNumImages(true);
            BufferedImage canvas = new BufferedImage(gifW, gifH, 2);
            Graphics2D g = canvas.createGraphics();
            g.setBackground(new Color(0, 0, 0, 0));
            BufferedImage saved = null;
            for (int i = 0; i < count; ++i) {
                int fy;
                IIOMetadata meta = reader.getImageMetadata(i);
                Node node = meta.getAsTree(meta.getNativeMetadataFormatName());
                Intrinsics.checkNotNull((Object)node, (String)"null cannot be cast to non-null type javax.imageio.metadata.IIOMetadataNode");
                IIOMetadataNode root = (IIOMetadataNode)node;
                IIOMetadataNode gce = INSTANCE.findNode(root, "GraphicControlExtension");
                IIOMetadataNode desc = INSTANCE.findNode(root, "ImageDescriptor");
                Object object = gce;
                if (object == null || (object = ((IIOMetadataNode)object).getAttribute("disposalMethod")) == null) {
                    object = "none";
                }
                Object disposal = object;
                int delay = 100;
                if (gce != null) {
                    try {
                        String string2 = (String) (gce.getAttribute("delayTime"));
                        delay = Integer.parseInt(string2) * 10;
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
                if (delay <= 0) {
                    delay = 100;
                }
                IIOMetadataNode iIOMetadataNode = desc;
                int fx = iIOMetadataNode != null ? INSTANCE.parseInt(iIOMetadataNode.getAttribute("imageLeftPosition"), 0) : 0;
                IIOMetadataNode iIOMetadataNode2 = desc;
                int n = fy = iIOMetadataNode2 != null ? INSTANCE.parseInt(iIOMetadataNode2.getAttribute("imageTopPosition"), 0) : 0;
                if (Intrinsics.areEqual((Object)"restoreToPrevious", (Object)disposal)) {
                    saved = INSTANCE.copyImage(canvas);
                }
                BufferedImage frame = reader.read(i);
                g.setComposite(AlphaComposite.SrcOver);
                g.drawImage((Image)frame, fx, fy, null);
                this.rawFrames.add(INSTANCE.copyImage(canvas));
                this.rawDelays.add(delay);
                if (Intrinsics.areEqual((Object)"restoreToBackgroundColor", (Object)disposal)) {
                    g.setComposite(AlphaComposite.Clear);
                    g.fillRect(fx, fy, frame.getWidth(), frame.getHeight());
                    continue;
                }
                if (!Intrinsics.areEqual((Object)"restoreToPrevious", (Object)disposal) || saved == null) continue;
                g.setComposite(AlphaComposite.Src);
                g.drawImage((Image)saved, 0, 0, null);
            }
            g.dispose();
            reader.dispose();
            iis.close();
            is.close();
            this.state = State.FRAMES_READY;
            this.scheduleGpuUploads();
        }

        private final void scheduleGpuUploads() {
            MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
            if (minecraftClient2 == null) {
                return;
            }
            MinecraftClient mc = minecraftClient2;
            int i = 0;
            int n = ((Collection)this.rawFrames).size();
            while (i < n) {
                int idx = i++;
                mc.execute(() -> GifAnimation.scheduleGpuUploads$lambda$0(this, idx));
            }
            mc.execute(() -> GifAnimation.scheduleGpuUploads$lambda$1(this));
        }

        private final void uploadSingleFrame(int idx) {
            try {
                if (idx >= this.rawFrames.size()) {
                    return;
                }
                BufferedImage bufferedImage = this.rawFrames.get(idx);
                Intrinsics.checkNotNullExpressionValue((Object)bufferedImage, (String)"get(...)");
                BufferedImage img = bufferedImage;
                int fw = img.getWidth();
                int fh = img.getHeight();
                NativeImage ni = new NativeImage(fw, fh, false);
                for (int py = 0; py < fh; ++py) {
                    for (int px = 0; px < fw; ++px) {
                        ni.setColorArgb(px, py, img.getRGB(px, py));
                    }
                }
                int uid = ID_COUNTER.getAndIncrement();
                GpuTexture gpuTexture = RenderSystem.getDevice().createTexture(() -> GifAnimation.uploadSingleFrame$lambda$0(uid), 5, TextureFormat.RGBA8, fw, fh, 1, 1);
                Intrinsics.checkNotNullExpressionValue((Object)gpuTexture, (String)"createTexture(...)");
                GpuTexture gpuTex = gpuTexture;
                RenderSystem.getDevice().createCommandEncoder().writeToTexture(gpuTex, ni);
                GpuTextureView gpuTextureView = RenderSystem.getDevice().createTextureView(gpuTex);
                Intrinsics.checkNotNullExpressionValue((Object)gpuTextureView, (String)"createTextureView(...)");
                GpuTextureView view = gpuTextureView;
                ni.close();
                Identifier identifier2 = Identifier.of((String)"kimiko", (String)("gif/frame_" + uid));
                Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
                Identifier texId = identifier2;
                MinecraftClient.getInstance().getTextureManager().registerTexture(texId, (AbstractTexture)new GifFrameTexture(gpuTex, view, fw, fh));
                this.frames.add(texId.toString());
                this.frameDelays.add(this.rawDelays.get(idx));
                this.gpuTextures.add(gpuTex);
                this.gpuViews.add(view);
                Integer n = this.rawDelays.get(idx);
                Intrinsics.checkNotNullExpressionValue((Object)n, (String)"get(...)");
                this.totalDuration += ((Number)n).intValue();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }

        @Nullable
        public final String getCurrentFrameId() {
            if (this.frames.isEmpty()) {
                return null;
            }
            if (this.totalDuration == 0) {
                return this.frames.get(0);
            }
            long time = System.currentTimeMillis() % (long)this.totalDuration;
            long elapsed = 0L;
            int n = ((Collection)this.frames).size();
            for (int i = 0; i < n; ++i) {
                Integer n2 = this.frameDelays.get(i);
                Intrinsics.checkNotNullExpressionValue((Object)n2, (String)"get(...)");
                if (time >= (elapsed += ((Number)n2).longValue())) continue;
                return this.frames.get(i);
            }
            return this.frames.get(this.frames.size() - 1);
        }

        public final void dispose() {
            MinecraftClient mc = MinecraftClient.getInstance();
            for (String id : this.frames) {
                try {
                    Identifier rid = Identifier.tryParse(id);
                    if (rid != null) {
                        mc.getTextureManager().destroyTexture(rid);
                    }
                } catch (Exception ignored) {}
            }
            for (GpuTextureView view : this.gpuViews) {
                view.close();
            }
            for (GpuTexture tex : this.gpuTextures) {
                tex.close();
            }
            this.frames.clear();
            this.frameDelays.clear();
            this.gpuTextures.clear();
            this.gpuViews.clear();
            this.rawFrames.clear();
            this.rawDelays.clear();
        }

        private static final void startLoad$lambda$0(GifAnimation this$0, String $path) {
            try {
                this$0.decodeGif($path);
            }
            catch (Exception e) {
                this$0.state = State.IDLE;
            }
        }

        private static final void scheduleGpuUploads$lambda$0(GifAnimation this$0, int $idx) {
            this$0.uploadSingleFrame($idx);
        }

        private static final void scheduleGpuUploads$lambda$1(GifAnimation this$0) {
            this$0.rawFrames.clear();
            this$0.state = State.GPU_READY;
        }

        private static final String uploadSingleFrame$lambda$0(int $uid) {
            return "kimiko_gif_" + $uid;
        }

        @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2={"Lrtx/kimiko/utils/render/render2d/gif/GifRenderer$GifAnimation$State;", "", "<init>", "(Ljava/lang/String;I)V", "IDLE", "LOADING", "FRAMES_READY", "GPU_READY", "rtx.kimiko:kimiko"})
        public static enum State {
        IDLE,
        LOADING,
        FRAMES_READY,
        GPU_READY;
@NotNull
            public static EnumEntries<State> getEntries() {
                return EnumEntriesKt.enumEntries(values());
            }

                
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0014R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0015R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0016R\u0014\u0010\b\u001a\u00020\u00068\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0016\u00a8\u0006\u0017"}, d2={"Lrtx/kimiko/utils/render/render2d/gif/GifRenderer$GifFrameTexture;", "Lnet/minecraft/AbstractTexture;", "Lcom/mojang/blaze3d/textures/GpuTexture;", "gpuTexture", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "gpuTextureView", "", "width", "height", "<init>", "(Lcom/mojang/blaze3d/textures/GpuTexture;Lcom/mojang/blaze3d/textures/GpuTextureView;II)V", "getTexture", "()Lcom/mojang/blaze3d/textures/GpuTexture;", "getTextureView", "()Lcom/mojang/blaze3d/textures/GpuTextureView;", "Lnet/minecraft/ResourceManager;", "resourceManager", "", "load", "(Lnet/minecraft/ResourceManager;)V", "Lcom/mojang/blaze3d/textures/GpuTexture;", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "I", "rtx.kimiko:kimiko"})
    public static final class GifFrameTexture
    extends AbstractTexture {
        @NotNull
        private final GpuTexture gpuTexture;
        @NotNull
        private final GpuTextureView gpuTextureView;
        private final int width;
        private final int height;

        public GifFrameTexture(@NotNull GpuTexture gpuTexture, @NotNull GpuTextureView gpuTextureView, int width, int height) {
            Intrinsics.checkNotNullParameter((Object)gpuTexture, (String)"gpuTexture");
            Intrinsics.checkNotNullParameter((Object)gpuTextureView, (String)"gpuTextureView");
            this.gpuTexture = gpuTexture;
            this.gpuTextureView = gpuTextureView;
            this.width = width;
            this.height = height;
        }

        @NotNull
        public GpuTexture getGlTexture() {
            return this.gpuTexture;
        }

        @NotNull
        public GpuTextureView getGlTextureView() {
            return this.gpuTextureView;
        }

        public final void load(@Nullable ResourceManager resourceManager) {
        }
    }
}

