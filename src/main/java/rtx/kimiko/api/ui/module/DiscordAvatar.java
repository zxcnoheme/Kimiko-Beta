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
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.StringsKt
 *  net.minecraft.client.texture.NativeImage
 *  net.minecraft.client.texture.AbstractTexture
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.resource.ResourceManager
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.module;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourceManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import recovery.privacy.NetworkPolicy;
import rtx.kimiko.utils.profile.ProfileIdentity;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002\u001d\u001eB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\b\u0010\u0007J\u000f\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\n\u0010\u0003J\u0017\u0010\f\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0010\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0016\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001b\u00a8\u0006\u001f"}, d2={"Lrtx/kimiko/api/ui/module/DiscordAvatar;", "", "<init>", "()V", "", "Lkotlin/jvm/JvmStatic;", "currentUrl", "()Ljava/lang/String;", "texture", "", "reset", "url", "startDownload", "(Ljava/lang/String;)V", "Ljava/awt/image/BufferedImage;", "img", "upload", "(Ljava/awt/image/BufferedImage;)V", "Ljava/util/concurrent/atomic/AtomicInteger;", "ID", "Ljava/util/concurrent/atomic/AtomicInteger;", "Lrtx/kimiko/api/ui/module/DiscordAvatar$State;", "state", "Lrtx/kimiko/api/ui/module/DiscordAvatar$State;", "decoded", "Ljava/awt/image/BufferedImage;", "loadedUrl", "Ljava/lang/String;", "textureId", "State", "AvatarTexture", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nDiscordAvatar.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DiscordAvatar.kt\nrtx/kimiko/api/ui/module/DiscordAvatar\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,146:1\n1#2:147\n*E\n"})
public final class DiscordAvatar {
    @NotNull
    public static final DiscordAvatar INSTANCE = new DiscordAvatar();
    @NotNull
    private static final AtomicInteger ID = new AtomicInteger();
    @NotNull
    private static volatile State state = State.IDLE;
    @Nullable
    private static volatile BufferedImage decoded;
    @Nullable
    private static String loadedUrl;
    @Nullable
    private static String textureId;

    private DiscordAvatar() {
    }

    @JvmStatic
    @Nullable
    public static final String currentUrl() {
        String url = ProfileIdentity.avatarUrl();
        if (url != null) {
            return url;
        }
        return null;
    }

    @JvmStatic
    @Nullable
    public static final String texture() {
        String string = DiscordAvatar.currentUrl();
        if (string == null) {
            return null;
        }
        String url = string;
        if (!Intrinsics.areEqual((Object)url, (Object)loadedUrl)) {
            INSTANCE.reset();
            loadedUrl = url;
            INSTANCE.startDownload(url);
        }
        BufferedImage image = decoded;
        if (state == State.DECODED && image != null) {
            decoded = null;
            state = State.UPLOADING;
            MinecraftClient.getInstance().execute(() -> DiscordAvatar.texture$lambda$0(image));
        }
        return state == State.READY ? textureId : null;
    }

    private final void reset() {
        String existing = textureId;
        if (existing != null) {
            try {
                Identifier rid = Identifier.tryParse((String)existing);
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
        textureId = null;
        decoded = null;
        state = State.IDLE;
    }

    private final void startDownload(String url) {
        state = State.DOWNLOADING;
        Thread thread = new Thread(() -> DiscordAvatar.startDownload$lambda$0(url), "kimiko-discord-avatar");
        thread.setDaemon(true);
        thread.start();
    }

    private final void upload(BufferedImage img) {
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
            GpuTexture gpuTexture = RenderSystem.getDevice().createTexture(() -> DiscordAvatar.upload$lambda$0(uid), 5, TextureFormat.RGBA8, w, h, 1, 1);
            Intrinsics.checkNotNullExpressionValue((Object)gpuTexture, (String)"createTexture(...)");
            GpuTexture gpuTex = gpuTexture;
            RenderSystem.getDevice().createCommandEncoder().writeToTexture(gpuTex, ni);
            GpuTextureView gpuTextureView = RenderSystem.getDevice().createTextureView(gpuTex);
            Intrinsics.checkNotNullExpressionValue((Object)gpuTextureView, (String)"createTextureView(...)");
            GpuTextureView view = gpuTextureView;
            ni.close();
            Identifier identifier2 = Identifier.of((String)"kimiko", (String)("discord/avatar_" + uid));
            Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
            Identifier id = identifier2;
            MinecraftClient.getInstance().getTextureManager().registerTexture(id, (AbstractTexture)new AvatarTexture(gpuTex, view));
            textureId = id.toString();
            state = State.READY;
        }
        catch (Exception ex) {
            state = State.FAILED;
        }
    }

    private static final void texture$lambda$0(BufferedImage $image) {
        INSTANCE.upload($image);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static final void startDownload$lambda$0(String $url) {
        try {
            BufferedImage bufferedImage;
            URLConnection connection = NetworkPolicy.openConnection(URI.create($url).toURL());
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Kimiko)");
            connection.setConnectTimeout(6000);
            connection.setReadTimeout(6000);
            Closeable closeable = NetworkPolicy.input(connection);
            Throwable throwable = null;
            try {
                InputStream it = (InputStream)closeable;
                boolean bl = false;
                bufferedImage = ImageIO.read(it);
            }
            catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            }
            finally {
                CloseableKt.closeFinally((Closeable)closeable, (Throwable)throwable);
            }
            BufferedImage img = bufferedImage;
            if (img != null) {
                decoded = img;
                state = State.DECODED;
            } else {
                state = State.FAILED;
            }
        }
        catch (Exception ex) {
            state = State.FAILED;
        }
    }

    private static final String upload$lambda$0(int $uid) {
        return "kimiko_discord_avatar_" + $uid;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0011R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0012\u00a8\u0006\u0013"}, d2={"Lrtx/kimiko/api/ui/module/DiscordAvatar$AvatarTexture;", "Lnet/minecraft/AbstractTexture;", "Lcom/mojang/blaze3d/textures/GpuTexture;", "gpuTexture", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "gpuTextureView", "<init>", "(Lcom/mojang/blaze3d/textures/GpuTexture;Lcom/mojang/blaze3d/textures/GpuTextureView;)V", "getTexture", "()Lcom/mojang/blaze3d/textures/GpuTexture;", "getTextureView", "()Lcom/mojang/blaze3d/textures/GpuTextureView;", "Lnet/minecraft/ResourceManager;", "resourceManager", "", "load", "(Lnet/minecraft/ResourceManager;)V", "Lcom/mojang/blaze3d/textures/GpuTexture;", "Lcom/mojang/blaze3d/textures/GpuTextureView;", "rtx.kimiko:kimiko"})
    public static final class AvatarTexture
    extends AbstractTexture {
        @NotNull
        private final GpuTexture gpuTexture;
        @NotNull
        private final GpuTextureView gpuTextureView;

        public AvatarTexture(@NotNull GpuTexture gpuTexture, @NotNull GpuTextureView gpuTextureView) {
            Intrinsics.checkNotNullParameter((Object)gpuTexture, (String)"gpuTexture");
            Intrinsics.checkNotNullParameter((Object)gpuTextureView, (String)"gpuTextureView");
            this.gpuTexture = gpuTexture;
            this.gpuTextureView = gpuTextureView;
        }

        @NotNull
        public GpuTexture getGlTexture() {
            return this.gpuTexture;
        }

        @NotNull
        public GpuTextureView getGlTextureView() {
            return this.gpuTextureView;
        }

        public final void load(@NotNull ResourceManager resourceManager) {
            Intrinsics.checkNotNullParameter((Object)resourceManager, (String)"resourceManager");
        }
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t\u00a8\u0006\n"}, d2={"Lrtx/kimiko/api/ui/module/DiscordAvatar$State;", "", "<init>", "(Ljava/lang/String;I)V", "IDLE", "DOWNLOADING", "DECODED", "UPLOADING", "READY", "FAILED", "rtx.kimiko:kimiko"})
    private static enum State {
        IDLE,
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

