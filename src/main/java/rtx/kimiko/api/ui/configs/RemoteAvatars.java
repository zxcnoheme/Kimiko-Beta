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
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package rtx.kimiko.api.ui.configs;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import recovery.privacy.NetworkPolicy;
import rtx.kimiko.api.ui.module.DiscordAvatar;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0002\u001b\u001cB\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007b\u0002\b\u0006\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R \u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001d"}, d2={"Lrtx/kimiko/api/ui/configs/RemoteAvatars;", "", "<init>", "()V", "", "url", "Lkotlin/jvm/JvmStatic;", "texture", "(Ljava/lang/String;)Ljava/lang/String;", "Lrtx/kimiko/api/ui/configs/RemoteAvatars$Slot;", "slot", "", "download", "(Ljava/lang/String;Lrtx/kimiko/api/ui/configs/RemoteAvatars$Slot;)V", "Ljava/awt/image/BufferedImage;", "image", "upload", "(Ljava/awt/image/BufferedImage;Lrtx/kimiko/api/ui/configs/RemoteAvatars$Slot;)V", "", "MAX_ENTRIES", "I", "Ljava/util/concurrent/atomic/AtomicInteger;", "IDS", "Ljava/util/concurrent/atomic/AtomicInteger;", "", "CACHE", "Ljava/util/Map;", "State", "Slot", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nRemoteAvatars.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RemoteAvatars.kt\nrtx/kimiko/api/ui/configs/RemoteAvatars\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,118:1\n1#2:119\n*E\n"})
public final class RemoteAvatars {
    @NotNull
    public static final RemoteAvatars INSTANCE = new RemoteAvatars();
    private static final int MAX_ENTRIES = 48;
    @NotNull
    private static final AtomicInteger IDS = new AtomicInteger();
    @NotNull
    private static final Map<String, Slot> CACHE = new ConcurrentHashMap();

    private RemoteAvatars() {
    }

    @JvmStatic
    @Nullable
    public static final String texture(@Nullable String url) {
        if (url == null || StringsKt.isBlank((CharSequence)url)) {
            return null;
        }
        Slot slot = CACHE.get(url);
        if (slot == null) {
            if (CACHE.size() >= 48) {
                return null;
            }
            slot = new Slot();
            Slot existing = CACHE.putIfAbsent(url, slot);
            if (existing == null) {
                INSTANCE.download(url, slot);
            } else {
                slot = existing;
            }
        }
        if (slot.getState() == State.DECODED) {
            BufferedImage image = slot.getDecoded();
            slot.setDecoded(null);
            if (image != null) {
                slot.setState(State.UPLOADING);
                Slot target = slot;
                MinecraftClient.getInstance().execute(() -> RemoteAvatars.texture$lambda$0(image, target));
            }
        }
        return slot.getState() == State.READY ? slot.getTextureId() : null;
    }

    private final void download(String url, Slot slot) {
        Thread thread = new Thread(() -> RemoteAvatars.download$lambda$0(url, slot), "kimiko-config-avatar");
        thread.setDaemon(true);
        thread.start();
    }

    private final void upload(BufferedImage image, Slot slot) {
        try {
            int width = image.getWidth();
            int height = image.getHeight();
            NativeImage nativeImage = new NativeImage(width, height, false);
            for (int y = 0; y < height; ++y) {
                for (int x = 0; x < width; ++x) {
                    nativeImage.setColorArgb(x, y, image.getRGB(x, y));
                }
            }
            int id = IDS.getAndIncrement();
            GpuTexture gpuTexture = RenderSystem.getDevice().createTexture(() -> RemoteAvatars.upload$lambda$0(id), 5, TextureFormat.RGBA8, width, height, 1, 1);
            Intrinsics.checkNotNullExpressionValue((Object)gpuTexture, (String)"createTexture(...)");
            GpuTexture texture = gpuTexture;
            RenderSystem.getDevice().createCommandEncoder().writeToTexture(texture, nativeImage);
            GpuTextureView gpuTextureView = RenderSystem.getDevice().createTextureView(texture);
            Intrinsics.checkNotNullExpressionValue((Object)gpuTextureView, (String)"createTextureView(...)");
            GpuTextureView view = gpuTextureView;
            nativeImage.close();
            Identifier identifier2 = Identifier.of((String)"kimiko", (String)("configs/avatar_" + id));
            Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
            Identifier identifier = identifier2;
            MinecraftClient.getInstance().getTextureManager().registerTexture(identifier, (AbstractTexture)new DiscordAvatar.AvatarTexture(texture, view));
            slot.setTextureId(identifier.toString());
            slot.setState(State.READY);
        }
        catch (Exception ex) {
            slot.setState(State.FAILED);
        }
    }

    private static final void texture$lambda$0(BufferedImage $image, Slot $target) {
        INSTANCE.upload($image, $target);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static final void download$lambda$0(String $url, Slot $slot) {
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
            BufferedImage image = bufferedImage;
            if (image == null) {
                $slot.setState(State.FAILED);
                return;
            }
            $slot.setDecoded(image);
            $slot.setState(State.DECODED);
        }
        catch (Exception ex) {
            $slot.setState(State.FAILED);
        }
    }

    private static final String upload$lambda$0(int $id) {
        return "kimiko_config_avatar_" + $id;
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR$\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018\u00a8\u0006\u0019"}, d2={"Lrtx/kimiko/api/ui/configs/RemoteAvatars$Slot;", "", "<init>", "()V", "Lrtx/kimiko/api/ui/configs/RemoteAvatars$State;", "state", "Lrtx/kimiko/api/ui/configs/RemoteAvatars$State;", "getState", "()Lrtx/kimiko/api/ui/configs/RemoteAvatars$State;", "setState", "(Lrtx/kimiko/api/ui/configs/RemoteAvatars$State;)V", "Ljava/awt/image/BufferedImage;", "decoded", "Ljava/awt/image/BufferedImage;", "getDecoded", "()Ljava/awt/image/BufferedImage;", "setDecoded", "(Ljava/awt/image/BufferedImage;)V", "", "textureId", "Ljava/lang/String;", "getTextureId", "()Ljava/lang/String;", "setTextureId", "(Ljava/lang/String;)V", "rtx.kimiko:kimiko"})
    private static final class Slot {
        @NotNull
        private volatile State state = State.DOWNLOADING;
        @Nullable
        private volatile BufferedImage decoded;
        @Nullable
        private volatile String textureId;

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
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2={"Lrtx/kimiko/api/ui/configs/RemoteAvatars$State;", "", "<init>", "(Ljava/lang/String;I)V", "DOWNLOADING", "DECODED", "UPLOADING", "READY", "FAILED", "rtx.kimiko:kimiko"})
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

