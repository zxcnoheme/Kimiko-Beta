/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.io.CloseableKt
 *  kotlin.jvm.JvmName
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.client.texture.NativeImage
 *  net.minecraft.client.texture.NativeImageBackedTexture
 *  net.minecraft.client.texture.AbstractTexture
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.resource.Resource
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package rtx.kimiko.utils.render.fonts.core.msdf;

import java.io.Closeable;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rtx.kimiko.Kimiko;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001*B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0015\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\u0011\u001a\u00020\u0010H\u0007b\u0002\b\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0003J\u000f\u0010\u0012\u001a\u00020\u0010H\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0003J!\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0016\u0010\u0017R\u001c\u0010\u001a\u001a\n \u0019*\u0004\u0018\u00010\u00180\u00188\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00040\u001f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010!R0\u0010$\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\r0\"j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\r`#8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0018\u0010&\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0016\u0010(\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010)\u00a8\u0006+"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfAtlasPack;", "", "<init>", "()V", "", "basePath", "", "Lkotlin/jvm/JvmStatic;", "contains", "(Ljava/lang/String;)Z", "Lnet/minecraft/Identifier;", "texture", "()Lnet/minecraft/Identifier;", "Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfAtlasPack$Placement;", "placement", "(Ljava/lang/String;)Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfAtlasPack$Placement;", "", "invalidate", "build", "Lnet/minecraft/MinecraftClient;", "minecraft", "Lnet/minecraft/NativeImage;", "readImage", "(Lnet/minecraft/MinecraftClient;Ljava/lang/String;)Lnet/minecraft/NativeImage;", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "LOGGER", "Lorg/slf4j/Logger;", "", "PADDING", "I", "", "MEMBERS", "[Ljava/lang/String;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "PLACEMENTS", "Ljava/util/HashMap;", "packedTexture", "Lnet/minecraft/Identifier;", "attempted", "Z", "Placement", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nMsdfAtlasPack.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MsdfAtlasPack.kt\nrtx/kimiko/utils/render/fonts/core/msdf/MsdfAtlasPack\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,135:1\n1#2:136\n*E\n"})
public final class MsdfAtlasPack {
    @NotNull
    public static final MsdfAtlasPack INSTANCE = new MsdfAtlasPack();
    private static final Logger LOGGER = LoggerFactory.getLogger((String)"Kimiko/MSDF");
    private static final int PADDING = 8;
    @NotNull
    private static final String[] MEMBERS;
    @NotNull
    private static final HashMap<String, Placement> PLACEMENTS;
    @Nullable
    private static Identifier packedTexture;
    private static boolean attempted;

    private MsdfAtlasPack() {
    }

    @JvmStatic
    public static final boolean contains(@NotNull String basePath) {
        Intrinsics.checkNotNullParameter((Object)basePath, (String)"basePath");
        for (String member : MEMBERS) {
            if (!Intrinsics.areEqual((Object)member, (Object)basePath)) continue;
            return true;
        }
        return false;
    }

    @JvmStatic
    @Nullable
    public static final Identifier texture() {
        INSTANCE.build();
        return packedTexture;
    }

    @JvmStatic
    @Nullable
    public static final Placement placement(@NotNull String basePath) {
        Intrinsics.checkNotNullParameter((Object)basePath, (String)"basePath");
        INSTANCE.build();
        return PLACEMENTS.get(basePath);
    }

    @JvmStatic
    public static final void invalidate() {
        attempted = false;
        packedTexture = null;
        PLACEMENTS.clear();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final synchronized void build() {
        if (attempted) {
            return;
        }
        attempted = true;
        MinecraftClient minecraft = MinecraftClient.getInstance();
        if (minecraft == null) {
            return;
        }
        LinkedHashMap<String, NativeImage> sources = new LinkedHashMap<>();
        try {
            int width = 0;
            int height = 0;
            for (String member : MEMBERS) {
                NativeImage image = this.readImage(minecraft, member);
                if (image == null) continue;
                sources.put(member, image);
                width = Math.max(width, image.getWidth());
                height += image.getHeight() + 8;
            }
            if (sources.isEmpty()) {
                return;
            }
            NativeImage packed = new NativeImage(width, Math.max(1, height), false);
            int cursorY = 0;
            for (Map.Entry<String, NativeImage> entry : sources.entrySet()) {
                String key = entry.getKey();
                NativeImage image = entry.getValue();
                int n = image.getHeight();
                for (int y = 0; y < n; ++y) {
                    int n2 = image.getWidth();
                    for (int x = 0; x < n2; ++x) {
                        packed.setColorArgb(x, cursorY + y, image.getColorArgb(x, y));
                    }
                }
                PLACEMENTS.put(key, new Placement(0, cursorY, image.getWidth(), image.getHeight(), width, Math.max(1, height)));
                cursorY += image.getHeight() + 8;
            }
            Identifier id = Identifier.of(Kimiko.Companion.namespace(), "fonts/packed_msdf_atlas");
            minecraft.getTextureManager().registerTexture(id, (AbstractTexture)new NativeImageBackedTexture(MsdfAtlasPack::build$lambda$0, packed));
            packedTexture = id;
            Object[] objectArray = new Object[]{sources.size(), width, height};
            LOGGER.info("[MSDF] Packed {} atlases into {}x{}", objectArray);
        }
        catch (Throwable throwable) {
            LOGGER.warn("[MSDF] Atlas packing failed, falling back to per-font textures", throwable);
            packedTexture = null;
            PLACEMENTS.clear();
        }
        finally {
            for (NativeImage image : sources.values()) {
                if (image != null) {
                    image.close();
                }
            }
        }
    }

    private final NativeImage readImage(MinecraftClient minecraft, String basePath) {
        Identifier pngId = Identifier.of(Kimiko.Companion.namespace(), basePath + ".png");
        Optional<Resource> resource = minecraft.getResourceManager().getResource(pngId);
        if (resource.isEmpty()) {
            return null;
        }
        try (InputStream it = resource.get().getInputStream()) {
            return NativeImage.read(it);
        }
        catch (Throwable throwable) {
            LOGGER.warn("[MSDF] Failed to read atlas image {}", (Object)pngId, (Object)throwable);
            return null;
        }
    }

    private static final String build$lambda$0() {
        return "kimiko_packed_msdf_atlas";
    }

    static {
        String[] stringArray = new String[]{"fonts/monsterat/montserrat-medium", "fonts/monsterat/montserrat-semibold", "fonts/monsterat/montserrat-regular", "fonts/kimiko/kimiko", "fonts/i2/i2"};
        MEMBERS = stringArray;
        PLACEMENTS = new HashMap();
    }

    @Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\fJ\u0010\u0010\u000e\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\fJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\fJ\u0010\u0010\u0010\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\fJ\u0010\u0010\u0011\u001a\u00020\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\fJL\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0018\u001a\u00020\u0002H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u0018\u0010\fJ\u0011\u0010\u001a\u001a\u00020\u0019H\u00d6\u0081\u0004\u00a2\u0006\u0004\b\u001a\u0010\u001bR%\u0010\u0003\u001a\u00020\u00028\u0007z\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u0003\u00a2\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u0003\u0010\fR%\u0010\u0004\u001a\u00020\u00028\u0007z\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u0004\u00a2\u0006\f\n\u0004\b\u0004\u0010\u001e\u001a\u0004\b\u0004\u0010\fR%\u0010\u0005\u001a\u00020\u00028\u0007z\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u0005\u00a2\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u0005\u0010\fR%\u0010\u0006\u001a\u00020\u00028\u0007z\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b\u0006\u0010\fR%\u0010\u0007\u001a\u00020\u00028\u0007z\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u0007\u00a2\u0006\f\n\u0004\b\u0007\u0010\u001e\u001a\u0004\b\u0007\u0010\fR%\u0010\b\u001a\u00020\u00028\u0007z\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\b\u00a2\u0006\f\n\u0004\b\b\u0010\u001e\u001a\u0004\b\b\u0010\f\u00a8\u0006\u001f"}, d2={"Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfAtlasPack$Placement;", "", "", "offsetX", "offsetY", "sourceWidth", "sourceHeight", "packedWidth", "packedHeight", "<init>", "(IIIIII)V", "component1", "()I", "component2", "component3", "component4", "component5", "component6", "copy", "(IIIIII)Lrtx/kimiko/utils/render/fonts/core/msdf/MsdfAtlasPack$Placement;", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "", "toString", "()Ljava/lang/String;", "Lkotlin/jvm/JvmName;", "name", "I", "rtx.kimiko:kimiko"})
    public static final class Placement {
        private final int offsetX;
        private final int offsetY;
        private final int sourceWidth;
        private final int sourceHeight;
        private final int packedWidth;
        private final int packedHeight;

        public Placement(int offsetX, int offsetY, int sourceWidth, int sourceHeight, int packedWidth, int packedHeight) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.sourceWidth = sourceWidth;
            this.sourceHeight = sourceHeight;
            this.packedWidth = packedWidth;
            this.packedHeight = packedHeight;
        }

        @JvmName(name="offsetX")
        public final int offsetX() {
            return this.offsetX;
        }

        @JvmName(name="offsetY")
        public final int offsetY() {
            return this.offsetY;
        }

        @JvmName(name="sourceWidth")
        public final int sourceWidth() {
            return this.sourceWidth;
        }

        @JvmName(name="sourceHeight")
        public final int sourceHeight() {
            return this.sourceHeight;
        }

        @JvmName(name="packedWidth")
        public final int packedWidth() {
            return this.packedWidth;
        }

        @JvmName(name="packedHeight")
        public final int packedHeight() {
            return this.packedHeight;
        }

        public final int component1() {
            return this.offsetX;
        }

        public final int component2() {
            return this.offsetY;
        }

        public final int component3() {
            return this.sourceWidth;
        }

        public final int component4() {
            return this.sourceHeight;
        }

        public final int component5() {
            return this.packedWidth;
        }

        public final int component6() {
            return this.packedHeight;
        }

        @NotNull
        public final Placement copy(int offsetX, int offsetY, int sourceWidth, int sourceHeight, int packedWidth, int packedHeight) {
            return new Placement(offsetX, offsetY, sourceWidth, sourceHeight, packedWidth, packedHeight);
        }

        public static /* synthetic */ Placement copy$default(Placement placement, int n, int n2, int n3, int n4, int n5, int n6, int n7, Object object) {
            if ((n7 & 1) != 0) {
                n = placement.offsetX;
            }
            if ((n7 & 2) != 0) {
                n2 = placement.offsetY;
            }
            if ((n7 & 4) != 0) {
                n3 = placement.sourceWidth;
            }
            if ((n7 & 8) != 0) {
                n4 = placement.sourceHeight;
            }
            if ((n7 & 0x10) != 0) {
                n5 = placement.packedWidth;
            }
            if ((n7 & 0x20) != 0) {
                n6 = placement.packedHeight;
            }
            return placement.copy(n, n2, n3, n4, n5, n6);
        }

        @NotNull
        public String toString() {
            return "Placement(offsetX=" + this.offsetX + ", offsetY=" + this.offsetY + ", sourceWidth=" + this.sourceWidth + ", sourceHeight=" + this.sourceHeight + ", packedWidth=" + this.packedWidth + ", packedHeight=" + this.packedHeight + ")";
        }

        public int hashCode() {
            int result = Integer.hashCode(this.offsetX);
            result = result * 31 + Integer.hashCode(this.offsetY);
            result = result * 31 + Integer.hashCode(this.sourceWidth);
            result = result * 31 + Integer.hashCode(this.sourceHeight);
            result = result * 31 + Integer.hashCode(this.packedWidth);
            result = result * 31 + Integer.hashCode(this.packedHeight);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Placement)) {
                return false;
            }
            Placement placement = (Placement)other;
            if (this.offsetX != placement.offsetX) {
                return false;
            }
            if (this.offsetY != placement.offsetY) {
                return false;
            }
            if (this.sourceWidth != placement.sourceWidth) {
                return false;
            }
            if (this.sourceHeight != placement.sourceHeight) {
                return false;
            }
            if (this.packedWidth != placement.packedWidth) {
                return false;
            }
            return this.packedHeight == placement.packedHeight;
        }
    }
}

