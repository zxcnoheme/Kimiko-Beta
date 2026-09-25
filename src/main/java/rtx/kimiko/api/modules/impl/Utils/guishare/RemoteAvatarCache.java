/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.textures.GpuTexture
 *  com.mojang.blaze3d.textures.GpuTextureView
 *  com.mojang.blaze3d.textures.TextureFormat
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.io.CloseableKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.text.StringsKt
 *  net.minecraft.client.texture.NativeImage
 *  net.minecraft.client.texture.AbstractTexture
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.modules.impl.Utils.guishare;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import recovery.privacy.NetworkPolicy;
import rtx.kimiko.api.ui.module.DiscordAvatar;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002()B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\r\u0010\u0003J\u001f\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u0019\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u00148\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001cR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010#R0\u0010&\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0$j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e`%8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010'\u00a8\u0006*"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteAvatarCache;", "", "<init>", "()V", "", "url", "Lkotlin/jvm/JvmStatic;", "texture", "(Ljava/lang/String;)Ljava/lang/String;", "", "allowedUrl", "(Ljava/lang/String;)Z", "", "evictIfNeeded", "Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteAvatarCache$Entry;", "entry", "startDownload", "(Ljava/lang/String;Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteAvatarCache$Entry;)V", "Ljava/awt/image/BufferedImage;", "source", "", "max", "downscale", "(Ljava/awt/image/BufferedImage;I)Ljava/awt/image/BufferedImage;", "img", "upload", "(Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteAvatarCache$Entry;Ljava/awt/image/BufferedImage;)V", "MAX_ENTRIES", "I", "MAX_PIXELS", "", "FAILED_RETRY_MS", "J", "Ljava/util/concurrent/atomic/AtomicInteger;", "ID", "Ljava/util/concurrent/atomic/AtomicInteger;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "entries", "Ljava/util/HashMap;", "State", "Entry", "rtx.kimiko:kimiko"})
public final class RemoteAvatarCache {
    @NotNull
    public static final RemoteAvatarCache INSTANCE = new RemoteAvatarCache();
    private static final int MAX_ENTRIES = 32;
    private static final int MAX_PIXELS = 32;
    private static final long FAILED_RETRY_MS = 30000L;
    @NotNull
    private static final AtomicInteger ID = new AtomicInteger();
    @NotNull
    private static final HashMap<String, Entry> entries = new HashMap();

    private RemoteAvatarCache() {
    }

    @JvmStatic
    @Nullable
    public static final String texture(@Nullable String url) {
        if (url == null || StringsKt.isBlank((CharSequence)url) || !INSTANCE.allowedUrl(url)) {
            return null;
        }
        Entry entry = entries.get(url);
        if (entry == null) {
            INSTANCE.evictIfNeeded();
            entry = new Entry();
            entry.setLastUse(System.nanoTime());
            ((Map)entries).put(url, entry);
            INSTANCE.startDownload(url, entry);
            return null;
        }
        entry.setLastUse(System.nanoTime());
        if (entry.getState() == State.FAILED && System.currentTimeMillis() - entry.getFailedAt() >= 30000L) {
            entry.setState(State.DOWNLOADING);
            INSTANCE.startDownload(url, entry);
            return null;
        }
        if (entry.getState() == State.DECODED && entry.getDecoded() != null) {
            BufferedImage img = entry.getDecoded();
            entry.setDecoded(null);
            entry.setState(State.UPLOADING);
            if (img != null) {
                INSTANCE.upload(entry, img);
            }
        }
        return entry.getState() == State.READY ? entry.getTextureId() : null;
    }

    private final boolean allowedUrl(String url) {
        return String.valueOf(url).startsWith("https://kimiko.tech/") || String.valueOf(url).startsWith("https://cdn.discordapp.com/") || String.valueOf(url).startsWith("https://media.discordapp.net/");
    }

    private final void evictIfNeeded() {
        if (entries.size() < 32) {
            return;
        }
        String oldestKey = null;
        long oldestUse = Long.MAX_VALUE;
        for (Map.Entry<String, Entry> entry : entries.entrySet()) {
            if (entry.getValue().getLastUse() < oldestUse) {
                oldestUse = entry.getValue().getLastUse();
                oldestKey = entry.getKey();
            }
        }
        if (oldestKey == null) {
            return;
        }
        Entry removed = entries.remove(oldestKey);
        String tex = removed != null ? removed.getTextureId() : null;
        if (tex != null) {
            try {
                Identifier rid = Identifier.tryParse((String)tex);
                MinecraftClient minecraftClient2 = MinecraftClient.getInstance();
                Intrinsics.checkNotNullExpressionValue((Object)minecraftClient2, (String)"getInstance(...)");
                MinecraftClient mc = minecraftClient2;
                if (rid != null) {
                    mc.getTextureManager().destroyTexture(rid);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    private final void startDownload(String url, Entry entry) {
        Thread thread = new Thread(() -> RemoteAvatarCache.startDownload$lambda$0(url, entry), "kimiko-remote-avatar");
        thread.setDaemon(true);
        thread.start();
    }

    private final BufferedImage downscale(BufferedImage source, int max) {
        int w = source.getWidth();
        int h = source.getHeight();
        if (w <= max && h <= max) {
            return source;
        }
        float scale = Math.min((float)max / (float)w, (float)max / (float)h);
        int tw = Math.max(1, Math.round((float)w * scale));
        int th = Math.max(1, Math.round((float)h * scale));
        BufferedImage scaled = new BufferedImage(tw, th, 2);
        Graphics2D graphics2D = scaled.createGraphics();
        Intrinsics.checkNotNullExpressionValue((Object)graphics2D, (String)"createGraphics(...)");
        Graphics2D g = graphics2D;
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(source.getScaledInstance(tw, th, 16), 0, 0, null);
        g.dispose();
        return scaled;
    }

    private final void upload(Entry entry, BufferedImage img) {
        try {
            int w = img.getWidth();
            int h = img.getHeight();
            NativeImage ni = new NativeImage(w, h, false);
            for (int py = 0; py < h; ++py) {
                for (int px = 0; px < w; ++px) {
                    ni.setColorArgb(px, py, img.getRGB(px, py));
                }
            }
            int uid = ID.getAndIncrement();
            GpuTexture gpuTexture = RenderSystem.getDevice().createTexture(() -> RemoteAvatarCache.upload$lambda$0(uid), 5, TextureFormat.RGBA8, w, h, 1, 1);
            Intrinsics.checkNotNullExpressionValue((Object)gpuTexture, (String)"createTexture(...)");
            GpuTexture gpuTex = gpuTexture;
            RenderSystem.getDevice().createCommandEncoder().writeToTexture(gpuTex, ni);
            GpuTextureView gpuTextureView = RenderSystem.getDevice().createTextureView(gpuTex);
            Intrinsics.checkNotNullExpressionValue((Object)gpuTextureView, (String)"createTextureView(...)");
            GpuTextureView view = gpuTextureView;
            ni.close();
            Identifier identifier2 = Identifier.of((String)"kimiko", (String)("discord/remote_avatar_" + uid));
            Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
            Identifier id = identifier2;
            MinecraftClient.getInstance().getTextureManager().registerTexture(id, (AbstractTexture)new DiscordAvatar.AvatarTexture(gpuTex, view));
            entry.setTextureId(id.toString());
            entry.setState(State.READY);
        }
        catch (Exception ex) {
            entry.setFailedAt(System.currentTimeMillis());
            entry.setState(State.FAILED);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static final void startDownload$lambda$0(String $url, Entry $entry) {
        try {
            URLConnection connection = NetworkPolicy.openConnection(URI.create($url).toURL());
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Kimiko)");
            connection.setConnectTimeout(6000);
            connection.setReadTimeout(6000);
            BufferedImage img = null;
            Closeable closeable = NetworkPolicy.input(connection);
            Throwable throwable = null;
            try {
                InputStream in = (InputStream)closeable;
                boolean bl = false;
                img = ImageIO.read(in);
                Unit unit = Unit.INSTANCE;
            }
            catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            }
            finally {
                CloseableKt.closeFinally((Closeable)closeable, (Throwable)throwable);
            }
            if (img != null) {
                img = INSTANCE.downscale(img, 32);
                $entry.setDecoded(img);
                $entry.setState(State.DECODED);
            } else {
                $entry.setFailedAt(System.currentTimeMillis());
                $entry.setState(State.FAILED);
            }
        }
        catch (Exception ex) {
            $entry.setFailedAt(System.currentTimeMillis());
            $entry.setState(State.FAILED);
        }
    }

    private static final String upload$lambda$0(int $uid) {
        return "kimiko_remote_avatar_" + $uid;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR$\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\u001a\u001a\u00020\u00198\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\"\u0010 \u001a\u00020\u00198\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b \u0010\u001b\u001a\u0004\b!\u0010\u001d\"\u0004\b\"\u0010\u001f\u00a8\u0006#"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteAvatarCache$Entry;", "", "<init>", "()V", "Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteAvatarCache$State;", "state", "Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteAvatarCache$State;", "getState", "()Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteAvatarCache$State;", "setState", "(Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteAvatarCache$State;)V", "Ljava/awt/image/BufferedImage;", "decoded", "Ljava/awt/image/BufferedImage;", "getDecoded", "()Ljava/awt/image/BufferedImage;", "setDecoded", "(Ljava/awt/image/BufferedImage;)V", "", "textureId", "Ljava/lang/String;", "getTextureId", "()Ljava/lang/String;", "setTextureId", "(Ljava/lang/String;)V", "", "failedAt", "J", "getFailedAt", "()J", "setFailedAt", "(J)V", "lastUse", "getLastUse", "setLastUse", "rtx.kimiko:kimiko"})
    private static final class Entry {
        @NotNull
        private volatile State state = State.DOWNLOADING;
        @Nullable
        private volatile BufferedImage decoded;
        @Nullable
        private volatile String textureId;
        private volatile long failedAt;
        private long lastUse;

        @NotNull
        public final State getState() {
            return this.state;
        }

        public final void setState(@NotNull State state) {
            Intrinsics.checkNotNullParameter((Object)((Object)state), (String)"<set-?>");
            this.state = state;
        }

        @Nullable
        public final BufferedImage getDecoded() {
            return this.decoded;
        }

        public final void setDecoded(@Nullable BufferedImage bufferedImage) {
            this.decoded = bufferedImage;
        }

        @Nullable
        public final String getTextureId() {
            return this.textureId;
        }

        public final void setTextureId(@Nullable String string) {
            this.textureId = string;
        }

        public final long getFailedAt() {
            return this.failedAt;
        }

        public final void setFailedAt(long l) {
            this.failedAt = l;
        }

        public final long getLastUse() {
            return this.lastUse;
        }

        public final void setLastUse(long l) {
            this.lastUse = l;
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2={"Lrtx/kimiko/api/modules/impl/Utils/guishare/RemoteAvatarCache$State;", "", "<init>", "(Ljava/lang/String;I)V", "DOWNLOADING", "DECODED", "UPLOADING", "READY", "FAILED", "rtx.kimiko:kimiko"})
    private static enum State {
        DOWNLOADING,
        DECODED,
        UPLOADING,
        READY,
        FAILED;
@NotNull
        public static EnumEntries<State> getEntries() {
            return EnumEntriesKt.enumEntries(values());
        }

            
    }
}

