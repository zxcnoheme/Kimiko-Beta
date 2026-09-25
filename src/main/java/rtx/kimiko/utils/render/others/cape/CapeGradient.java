/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.io.CloseableKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  net.minecraft.client.texture.NativeImage
 *  net.minecraft.client.texture.NativeImageBackedTexture
 *  net.minecraft.client.texture.AbstractTexture
 *  net.minecraft.util.AssetInfo.TextureAssetInfo
 *  net.minecraft.util.AssetInfo.TextureAsset
 *  net.minecraft.util.Identifier
 *  net.minecraft.client.MinecraftClient
 *  net.minecraft.resource.Resource
 *  net.minecraft.util.math.ColorHelper
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.system.MemoryUtil
 */
package rtx.kimiko.utils.render.others.cape;

import java.io.Closeable;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.util.AssetInfo;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import net.minecraft.util.math.ColorHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.MemoryUtil;
import rtx.kimiko.Kimiko;
import rtx.kimiko.api.ui.theme.ClientAccent;

@Metadata(mv={2, 4, 0}, k=1, xi=48, d1={"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0004H\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\t\u001a\u00020\bH\u0007b\u0002\b\u0005\u00a2\u0006\u0004\b\t\u0010\u0003J\u000f\u0010\n\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\n\u0010\u0003J\u000f\u0010\u000b\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u000b\u0010\u0003J\u0017\u0010\u000e\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0003J\u000f\u0010\u0011\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0003J\u000f\u0010\u0013\u001a\u00020\u0012H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00158\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0017R\u0019\u0010\u001b\u001a\u00020\u00048\u0006X\u0087\u0004\u0092\u0002\u0002\b\u001a\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u00128\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0018\u0010\"\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\"\u0010\u001cR\u0018\u0010$\u001a\u0004\u0018\u00010#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0018\u0010&\u001a\u0004\u0018\u00010#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010%R\u0018\u0010'\u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b'\u0010!R\u0016\u0010(\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b(\u0010\u001eR\u0016\u0010)\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010\u001eR\u0016\u0010*\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010\u001eR\u0016\u0010+\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u0010\u001eR\u0016\u0010,\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010\u001eR\u0016\u0010-\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b-\u0010\u001eR\u0016\u0010.\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b.\u0010/R\u0018\u00100\u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b0\u0010!R\u0018\u00102\u001a\u0004\u0018\u0001018\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u00103R\u0018\u00104\u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u0010!R\u0014\u00105\u001a\u00020\u001f8\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b5\u0010!R\u0016\u00107\u001a\u0002068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b7\u00108R\u0016\u00109\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b9\u0010\u001eR\u0016\u0010:\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b:\u0010/R\u0016\u0010;\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b;\u0010/\u00a8\u0006<"}, d2={"Lrtx/kimiko/utils/render/others/cape/CapeGradient;", "", "<init>", "()V", "Lnet/minecraft/AssetInfo$TextureAsset;", "Lkotlin/jvm/JvmStatic;", "asset", "()Lnet/minecraft/AssetInfo$TextureAsset;", "", "tick", "ensureInit", "buildDynamicIndex", "", "force", "recolor", "(Z)V", "buildShadeLut", "paintPixels", "", "colorSignature", "()I", "Lnet/minecraft/Identifier;", "BASE_TEXTURE", "Lnet/minecraft/Identifier;", "DYNAMIC_TEXTURE", "DYNAMIC_ASSET_ID", "Lkotlin/jvm/JvmField;", "STATIC_ASSET", "Lnet/minecraft/AssetInfo$TextureAsset;", "LUT_SIZE", "I", "", "gradientLut", "[I", "dynamicAsset", "Lnet/minecraft/NativeImage;", "base", "Lnet/minecraft/NativeImage;", "work", "basePixels", "baseW", "baseH", "bx0", "by0", "bx1", "by1", "projUseX", "Z", "projIdx", "Lnet/minecraft/NativeImageBackedTexture;", "texture", "Lnet/minecraft/NativeImageBackedTexture;", "packedPixels", "shadeLut", "", "lastUpdateMs", "J", "lastSig", "initialized", "failed", "rtx.kimiko:kimiko"})
@SourceDebugExtension(value={"SMAP\nCapeGradient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CapeGradient.kt\nrtx/kimiko/utils/render/others/cape/CapeGradient\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,246:1\n1#2:247\n*E\n"})
public final class CapeGradient {
    @NotNull
    public static final CapeGradient INSTANCE = new CapeGradient();
    @NotNull
    private static final Identifier BASE_TEXTURE;
    @NotNull
    private static final Identifier DYNAMIC_TEXTURE;
    @NotNull
    private static final Identifier DYNAMIC_ASSET_ID;
    @JvmField
    @NotNull
    public static final AssetInfo.TextureAsset STATIC_ASSET;
    private static final int LUT_SIZE = 256;
    @NotNull
    private static final int[] gradientLut;
    @Nullable
    private static AssetInfo.TextureAsset dynamicAsset;
    @Nullable
    private static NativeImage base;
    @Nullable
    private static NativeImage work;
    @Nullable
    private static int[] basePixels;
    private static int baseW;
    private static int baseH;
    private static int bx0;
    private static int by0;
    private static int bx1;
    private static int by1;
    private static boolean projUseX;
    @Nullable
    private static int[] projIdx;
    @Nullable
    private static NativeImageBackedTexture texture;
    @Nullable
    private static int[] packedPixels;
    @NotNull
    private static final int[] shadeLut;
    private static long lastUpdateMs;
    private static int lastSig;
    private static boolean initialized;
    private static boolean failed;

    private CapeGradient() {
    }

    @JvmStatic
    @NotNull
    public static final AssetInfo.TextureAsset asset() {
        INSTANCE.ensureInit();
        AssetInfo.TextureAsset dynamic = dynamicAsset;
        return !failed && dynamic != null ? dynamic : STATIC_ASSET;
    }

    @JvmStatic
    public static final void tick() {
        INSTANCE.ensureInit();
        if (failed || texture == null) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - lastUpdateMs < 40L) {
            return;
        }
        lastUpdateMs = now;
        INSTANCE.recolor(false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void ensureInit() {
        if (initialized || failed) {
            return;
        }
        initialized = true;
        try {
            NativeImageBackedTexture dynamicTexture;
            NativeImage canvas;
            int p;
            int n2;
            MinecraftClient mc = MinecraftClient.getInstance();
            Resource res = mc.getResourceManager().getResourceOrThrow(BASE_TEXTURE);
            NativeImage image;
            try (InputStream is = res.getInputStream()) {
                image = NativeImage.read(is);
            }
            base = image;
            baseW = image.getWidth();
            baseH = image.getHeight();
            int[] pixels = new int[baseW * baseH];
            basePixels = pixels;
            bx0 = baseW;
            by0 = baseH;
            bx1 = 0;
            by1 = 0;
            int n3 = baseH;
            for (int y = 0; y < n3; ++y) {
                n2 = baseW;
                for (int x = 0; x < n2; ++x) {
                    pixels[y * CapeGradient.baseW + x] = p = image.getColorArgb(x, y);
                    if (ColorHelper.getAlpha((int)p) < 8 || Math.max(ColorHelper.getRed((int)p), Math.max(ColorHelper.getGreen((int)p), ColorHelper.getBlue((int)p))) <= 40) continue;
                    if (x < bx0) {
                        bx0 = x;
                    }
                    if (x > bx1) {
                        bx1 = x;
                    }
                    if (y < by0) {
                        by0 = y;
                    }
                    if (y <= by1) continue;
                    by1 = y;
                }
            }
            if (bx1 < bx0 || by1 < by0) {
                bx0 = 0;
                by0 = 0;
                bx1 = baseW - 1;
                by1 = baseH - 1;
            }
            projUseX = bx1 - bx0 >= by1 - by0;
            int axisLen = Math.max(1, projUseX ? baseW : baseH);
            int[] hist = new int[axisLen];
            int totalBright = 0;
            p = baseH;
            for (int y = 0; y < p; ++y) {
                int n4 = baseW;
                for (int x = 0; x < n4; ++x) {
                    int p2 = pixels[y * baseW + x];
                    if (ColorHelper.getAlpha((int)p2) < 8 || Math.max(ColorHelper.getRed((int)p2), Math.max(ColorHelper.getGreen((int)p2), ColorHelper.getBlue((int)p2))) <= 40) continue;
                    int n5 = projUseX ? x : y;
                    int n6 = hist[n5];
                    hist[n5] = n6 + 1;
                    ++totalBright;
                }
            }
            int[] idxTable = new int[axisLen];
            projIdx = idxTable;
            if (totalBright > 0) {
                int cum = 0;
                for (int v = 0; v < axisLen; ++v) {
                    float t = ((float)(cum += hist[v]) - (float)hist[v] * 0.5f) / (float)totalBright;
                    int idx = Math.round(t * (float)255);
                    idxTable[v] = Math.max(0, Math.min(255, idx));
                }
            }
            this.buildDynamicIndex();
            work = canvas = new NativeImage(baseW, baseH, false);
            texture = dynamicTexture = new NativeImageBackedTexture(CapeGradient::ensureInit$lambda$1, canvas);
            mc.getTextureManager().registerTexture(DYNAMIC_TEXTURE, (AbstractTexture)dynamicTexture);
            dynamicAsset = (AssetInfo.TextureAsset)new AssetInfo.TextureAssetInfo(DYNAMIC_ASSET_ID, DYNAMIC_TEXTURE);
            this.recolor(true);
        }
        catch (Throwable throwable) {
            failed = true;
        }
    }

    private final void buildDynamicIndex() {
        if (basePixels == null) {
            return;
        }
        int[] pixels = basePixels;
        int total = baseW * baseH;
        int[] packed = new int[total];
        packedPixels = packed;
        int[] idxTable = projIdx;
        int n = baseH;
        for (int y = 0; y < n; ++y) {
            int row = y * baseW;
            int rowIdx = idxTable == null || idxTable.length == 0 || projUseX ? 0 : idxTable[y];
            int n2 = baseW;
            for (int x = 0; x < n2; ++x) {
                int p = pixels[row + x];
                int a = ColorHelper.getAlpha((int)p);
                int lum = Math.max(ColorHelper.getRed((int)p), Math.max(ColorHelper.getGreen((int)p), ColorHelper.getBlue((int)p)));
                int idx = idxTable == null || idxTable.length == 0 ? 0 : (projUseX ? idxTable[x] : rowIdx);
                packed[row + x] = a << 24 | idx << 8 | lum;
            }
        }
    }

    private final void recolor(boolean force) {
        if (work == null || texture == null || basePixels == null) {
            return;
        }
        int sig = this.colorSignature();
        if (!force && sig == lastSig) {
            return;
        }
        lastSig = sig;
        int[] pal = ClientAccent.currentPalette();
        int cA = pal[0] & 0xFFFFFF;
        int cB = pal[pal.length - 1] & 0xFFFFFF;
        int rA = cA >> 16 & 0xFF;
        int gA = cA >> 8 & 0xFF;
        int bA = cA & 0xFF;
        int rB = cB >> 16 & 0xFF;
        int gB = cB >> 8 & 0xFF;
        int bB = cB & 0xFF;
        for (int i = 0; i < 256; ++i) {
            float t = (float)i / 255.0f;
            int r = Math.round((float)rA + (float)(rB - rA) * t);
            int g = Math.round((float)gA + (float)(gB - gA) * t);
            int b = Math.round((float)bA + (float)(bB - bA) * t);
            CapeGradient.gradientLut[i] = r << 16 | g << 8 | b;
        }
        this.buildShadeLut();
        try {
            this.paintPixels();
            NativeImageBackedTexture nativeImageBackedTexture2 = texture;
            Intrinsics.checkNotNull((Object)nativeImageBackedTexture2);
            nativeImageBackedTexture2.upload();
        }
        catch (Throwable throwable) {
            failed = true;
        }
    }

    private final void buildShadeLut() {
        for (int idx = 0; idx < 256; ++idx) {
            int grad = gradientLut[idx];
            int gr = grad >> 16 & 0xFF;
            int gg = grad >> 8 & 0xFF;
            int gb = grad & 0xFF;
            int base = idx << 8;
            for (int lum = 0; lum < 256; ++lum) {
                CapeGradient.shadeLut[base + lum] = lum * gb / 255 << 16 | lum * gg / 255 << 8 | lum * gr / 255;
            }
        }
    }

    private final void paintPixels() {
        NativeImage nativeImage2 = work;
        if (nativeImage2 == null) {
            return;
        }
        NativeImage canvas = nativeImage2;
        long pointer = canvas.imageId();
        if (packedPixels == null) {
            return;
        }
        int[] packed = packedPixels;
        int[] lut = shadeLut;
        int total = packed.length;
        for (int i = 0; i < total; ++i) {
            int info = packed[i];
            MemoryUtil.memPutInt((long)(pointer + ((long)i << 2)), (int)(info & 0xFF000000 | lut[info & 0xFFFF]));
        }
    }

    private final int colorSignature() {
        int[] pal = ClientAccent.currentPalette();
        return (pal[0] & 0xFFFFFF) * 31 + (pal[pal.length - 1] & 0xFFFFFF);
    }

    private static final String ensureInit$lambda$1() {
        return "kimiko_cape_gradient";
    }

    static {
        Identifier identifier2 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"textures/capes/cape.png");
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        BASE_TEXTURE = identifier2;
        Identifier identifier3 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"dynamic/cape_gradient");
        Intrinsics.checkNotNullExpressionValue((Object)identifier3, (String)"fromNamespaceAndPath(...)");
        DYNAMIC_TEXTURE = identifier3;
        Identifier identifier4 = Identifier.of((String)Kimiko.Companion.namespace(), (String)"capes/cape_gradient");
        Intrinsics.checkNotNullExpressionValue((Object)identifier4, (String)"fromNamespaceAndPath(...)");
        DYNAMIC_ASSET_ID = identifier4;
        STATIC_ASSET = (AssetInfo.TextureAsset)new AssetInfo.TextureAssetInfo(Identifier.of((String)Kimiko.Companion.namespace(), (String)"capes/cape"), BASE_TEXTURE);
        gradientLut = new int[256];
        shadeLut = new int[65536];
        lastSig = Integer.MIN_VALUE;
    }
}

